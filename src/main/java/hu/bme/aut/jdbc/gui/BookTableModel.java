package hu.bme.aut.jdbc.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import hu.bme.aut.jdbc.domain.Book;

public class BookTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private List<Book> list;
	private String[] colNames = { "Cím", "Szerző", "Típus",
			"Leírás" };

	public BookTableModel() {
		this.list = new ArrayList<>();
	}

	public BookTableModel(List<Book> list) {
		this.list = list;
	}

	@Override
	public int getColumnCount() {
		return colNames.length;
	}

	@Override
	public int getRowCount() {
		return list.size();
	}

	@Override
	public Object getValueAt(int entryNo, int propNo) {
		Book e = list.get(entryNo);
		switch (propNo) {
		case 0:
			return e.getTitle();
		case 1:
			return e.getAuthor();
		case 2:
			return e.getType().toString();
		case 3:
			return e.getDesc();
		}
		return null;
	}

	@Override
	public String getColumnName(int column) {
		return colNames[column];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	public void remove(int idx) {
		list.remove(idx);
		fireTableRowsDeleted(idx, idx);
	}

	public Book get(int idx) {
		return list.get(idx);
	}

	public void set(int idx, Book entry) {
		list.set(idx, entry);
		fireTableRowsUpdated(idx, idx);
	}

	public void clear() {
		list.clear();
		fireTableDataChanged();
	}

	public void add(Book entry) {
		list.add(entry);
		fireTableRowsInserted(list.size() - 2, list.size() - 2);
	}

	public void newCatalog(List<Book> list) {
		this.list = list;
		fireTableDataChanged();
	}

	public List<Book> getCatalog() {
		return list;
	}
}
