package gui;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import prog.Arg;

public class CustomTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	Vector<Arg> rows;
	protected String[] columnNames = { "Page", "Parameter", "Rest", "Test" };

	public CustomTableModel(Vector<Arg> rows) {
		this.rows = rows;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void clearTable() {
		this.rows = new Vector();
	}

	public int getColumnCount() {
		return 4;
	}

	public int getRowCount() {
		return this.rows.size();
	}

	public Arg getRow(int row) {
		return (Arg) this.rows.get(row);
	}

	public String getColumnName(int col) {
		return this.columnNames[col];
	}

	public static Set<String> links = new HashSet<>();

	public void addRow(Arg row) {
		this.rows.addElement(row);
		setValueAt(row.getPageLink(), getRowCount(), 0);
		// System.out.println("row.getPageLink(): " + row.getPageLink());
		links.add(row.getPageLink());
		setValueAt(row.getName(), getRowCount(), 1);
		setValueAt(row.getRest(), getRowCount(), 2);
		setValueAt(Boolean.valueOf(true), getRowCount(), 3);
	}

	public boolean isCellEditable(int row, int col) {
		if (col > 2) {
			return true;
		}
		return false;
	}

	public int argPos(Arg a) {
		int count = 0;
		for (Arg arg : this.rows) {
			if ((a.getPageBase().equals(arg.getPageBase()))
					&& (a.getName().equals(arg.getName()))
					&& (a.getRest().equals(arg.getRest()))) {
				return count;
			}
			count++;
		}
		return -1;
	}

	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	public Object getValueAt(int row, int col) {
		Arg curr = (Arg) this.rows.get(row);
		switch (col) {
		case 0:
			return curr.getPageLink().subSequence(0,
					curr.getPageLink().lastIndexOf('?'));
		case 1:
			return curr.getName() + "=" + curr.getValues();
		case 2:
			return curr.getRest();
		case 3:
			return Boolean.valueOf(curr.getTest());
		}
		return Integer.valueOf(0);
	}
}
