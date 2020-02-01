package hu.bme.aut.jdbc.gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import hu.bme.aut.jdbc.domain.Book;

public class EditDialog extends JDialog {
    private static final long serialVersionUID = 1L;

    private JTextField titleField = new JTextField();
    private JTextField authorField = new JTextField();
    private JTextArea descArea = new JTextArea(10, getWidth());
    private JLabel errLabel = new JLabel();
    private ButtonGroup buttonGroup = new ButtonGroup();
    private JRadioButton printRadio = new JRadioButton("Printed");
    private JRadioButton ebookRadio = new JRadioButton("Ebook");
    private Long idBeingEdited = null;

    class EditDialogActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            boolean validates = validateData();
            if (validates)
                setVisible(false);
        }
    }

    {
        setLayout(new GridBagLayout());

        JPanel dataPanel = new JPanel();
        dataPanel.setLayout(new GridLayout(0, 2));
        dataPanel.setBorder(BorderFactory.createTitledBorder("Adatok:"));
        dataPanel.add(new JLabel("Cím:"));
        dataPanel.add(titleField);
        dataPanel.add(new JLabel("Szerző:"));
        dataPanel.add(authorField);

        JPanel typePanel = new JPanel();
        typePanel.setLayout(new GridLayout(0, 1));
        typePanel.setBorder(BorderFactory.createTitledBorder("Típus:"));
        printRadio.setActionCommand("PRINTED");
        buttonGroup.add(printRadio);
        typePanel.add(printRadio);
        ebookRadio.setActionCommand("EBOOK");
        buttonGroup.add(ebookRadio);
        typePanel.add(ebookRadio);

        JPanel descPanel = new JPanel();
        descPanel.setLayout(new CardLayout());
        descPanel.setBorder(BorderFactory.createTitledBorder("Leírás:"));
        descPanel.add(new JScrollPane(descArea));

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new EditDialogActionListener());
        errLabel.setMinimumSize(new Dimension(50, 10));
        errLabel.setForeground(new Color(255, 0, 0));

        this.add(dataPanel, new GridBagConstraints(0, 0, 1, 1, 8, 4,
                GridBagConstraints.FIRST_LINE_START,
                GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        this.add(typePanel, new GridBagConstraints(1, 0, 1, 1, 2, 4,
                GridBagConstraints.FIRST_LINE_START,
                GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        this.add(descPanel, new GridBagConstraints(0, 1, 2, 2, 8, 8,
                GridBagConstraints.FIRST_LINE_START,
                GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        this.add(errLabel, new GridBagConstraints(0, 3, 1, 2, 1, 1,
                GridBagConstraints.FIRST_LINE_START,
                GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        this.add(okButton, new GridBagConstraints(1, 3, 1, 1, 1, 1,
                GridBagConstraints.FIRST_LINE_START,
                GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        setMinimumSize(new Dimension(600, 400));
    }

    public EditDialog(JFrame owner) {
        super(owner, true);
    }

    public EditDialog(JFrame owner, Book entry) {
        super(owner, true);
        idBeingEdited = entry.getId();
        titleField.setText(entry.getTitle());
        authorField.setText(entry.getAuthor());
        switch (entry.getType()) {
            case PRINTED:
                buttonGroup.setSelected(printRadio.getModel(), true);
                break;
            case EBOOK:
                buttonGroup.setSelected(ebookRadio.getModel(), true);
                break;
        }
        descArea.setText(entry.getDesc());
    }

    private boolean validateData() {
        String msg = null;

        if (titleField.getText().equals(""))
            msg = "Adja meg a címet!";
        else if (authorField.getText().equals(""))
            msg = "Adja meg a szerzőt!";
        else if (buttonGroup.getSelection() == null)
            msg = "Adja meg a kiadvány típusát!";

        if (msg != null) {
            errLabel.setText(msg);
            return false;
        }
        return true;
    }

    public Book parseForm() {
        Book entry = new Book();
        entry.setId(idBeingEdited);
        entry.setTitle(titleField.getText());
        entry.setAuthor(authorField.getText());
        entry.setType(Book.EntryType.valueOf(buttonGroup.getSelection()
                .getActionCommand()));
        entry.setDesc(descArea.getText());
        return entry;
    }
}
