package hu.bme.aut.jdbc.gui;

import hu.bme.aut.jdbc.domain.Book;
import hu.bme.aut.jdbc.domain.BookDao;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow implements Runnable {

    private JFrame mainWindow;
    private BookDao bookDao = new BookDao();
    private BookTableModel tableModel = new BookTableModel(bookDao.findAll());
    private JTable table = new JTable(tableModel);

    class CatalogActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ActionCommand cmd = ActionCommand.valueOf(e.getActionCommand());
            if (cmd == null)
                return;

            switch (cmd) {
                case DELETE_ENTRY:
                    int row = table.getSelectedRow();
                    Book entryToRemove = tableModel.get(row);
                    bookDao.delete(entryToRemove);
                    tableModel.remove(row);
                    break;
                case DETAILS_ENTRY:
                    int rowToUpdate = table.getSelectedRow();
                    Book toUpdate = tableModel.get(rowToUpdate);
                    EditDialog editDialog = new EditDialog(mainWindow, toUpdate);
                    editDialog.setVisible(true);
                    Book modifiedEntry = editDialog.parseForm();
                    bookDao.save(modifiedEntry);
                    tableModel.set(rowToUpdate, modifiedEntry);
                    break;
                case EXIT:
                    try {
                        bookDao.close();
                    } catch (Exception ignored) {
                    }
                    System.exit(0);
                    break;
                case NEW_ENTRY:
                    EditDialog newDialog = new EditDialog(mainWindow);
                    newDialog.setVisible(true);
                    Book newEntry = newDialog.parseForm();
                    bookDao.persist(newEntry);
                    tableModel.newCatalog(bookDao.findAll());
                    break;
            }
        }
    }

    @Override
    public void run() {
        mainWindow = new JFrame("Lemezkatalógus");

        ActionListener actionListener = new CatalogActionListener();

        JMenu fileMenu = new JMenu("Fájl");
        JMenuItem fileExitMenu = new JMenuItem("Kilépés");
        fileExitMenu.setActionCommand(ActionCommand.EXIT.toString());

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        mainWindow.setJMenuBar(menuBar);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setMinimumSize(new Dimension(750, 550));
        table.setFillsViewportHeight(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowSorter(new TableRowSorter<BookTableModel>(tableModel));
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, new GridBagConstraints(0, 0, 3, 1, 4, 80,
                GridBagConstraints.FIRST_LINE_START,
                GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 10, 10));
        JButton newButton = new JButton("Új...");
        newButton.setActionCommand(ActionCommand.NEW_ENTRY.toString());
        newButton.addActionListener(actionListener);
        JButton deleteButton = new JButton("Töröl");
        deleteButton.setActionCommand(ActionCommand.DELETE_ENTRY.toString());
        deleteButton.addActionListener(actionListener);
        JButton detailsButton = new JButton("Módosít...");
        detailsButton.setActionCommand(ActionCommand.DETAILS_ENTRY.toString());
        detailsButton.addActionListener(actionListener);
        panel.add(newButton, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.FIRST_LINE_START,
                GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 10, 10));
        panel.add(deleteButton, new GridBagConstraints(1, 1, 1, 1, 1, 1,
                GridBagConstraints.FIRST_LINE_START,
                GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 10, 10));
        panel.add(detailsButton, new GridBagConstraints(2, 1, 1, 1, 1, 1,
                GridBagConstraints.FIRST_LINE_START,
                GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 10, 10));

        mainWindow.add(panel);
        mainWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        mainWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                try {
                    bookDao.close();
                } catch (Exception ignored) {
                }
                System.exit(0);
            }
        });
        mainWindow.setMinimumSize(new Dimension(800, 600));
        mainWindow.setVisible(true);
    }
}