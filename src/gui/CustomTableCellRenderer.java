package gui;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import prog.Arg;

public class CustomTableCellRenderer extends DefaultTableCellRenderer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	CustomTableModel tableModel;

	public CustomTableCellRenderer(CustomTableModel tableModel) {
		this.tableModel = tableModel;
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		Component comp = super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, row, column);
		if (this.tableModel.getRow(row).isVulnerable()) {
//			System.out.println("Vulnerable: " + this.tableModel.getRow(row));
			comp.setBackground(Color.RED);
		} else if (this.tableModel.getRow(row).getMethod() == 1) {
			comp.setBackground(Color.decode("#c9c9c9"));
		} else
			comp.setBackground(Color.decode("#f8f8f8"));
		return comp;
	}
}
