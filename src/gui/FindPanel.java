package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import prog.Arg;

public class FindPanel extends JPanel {
	private GridBagLayout layout;
	private GridBagConstraints c;
	private JScrollPane scrollPane;
	public CustomTableModel tableModel;
	public JTable table;
	private MainFrame parent;
	private JButton testButton;
	private JButton stopButton;
	private JButton testAllButton;
	private JButton testGetButton;
	private JButton testPostButton;
	public static long test_start_time;
	
	public FindPanel(MainFrame parent) {
		this.parent = parent;
		init();
	}

	public void init() {
		this.layout = new GridBagLayout();
		this.c = new GridBagConstraints();
		setLayout(this.layout);
		setBorder(BorderFactory.createTitledBorder("Page parameters"));
		this.c.anchor = 19;
		this.c.fill = 1;

		this.testAllButton = new JButton("(Un)Select All parameters");
		testAllButton.setPreferredSize(new Dimension(250, 25));
	
		this.testAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < FindPanel.this.tableModel.getRowCount(); i++) {
					FindPanel.this.tableModel.getRow(i).setTest(
							!FindPanel.this.tableModel.getRow(i).getTest());
					FindPanel.this.repaint();
				}
			}
		});
		this.testGetButton = new JButton("(Un)Select GET parameters");
		this.testGetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < FindPanel.this.tableModel.getRowCount(); i++) {
					if (FindPanel.this.tableModel.getRow(i).getMethod() == 0) {
						FindPanel.this.tableModel.getRow(i).setTest(
								!FindPanel.this.tableModel.getRow(i).getTest());
						FindPanel.this.repaint();
					}
				}
			}
		});
		this.testPostButton = new JButton("(Un)Select POST parameters");
		this.testPostButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < FindPanel.this.tableModel.getRowCount(); i++) {
					if (FindPanel.this.tableModel.getRow(i).getMethod() == 1) {
						FindPanel.this.tableModel.getRow(i).setTest(
								!FindPanel.this.tableModel.getRow(i).getTest());
						FindPanel.this.repaint();
					}

				}
			}
		});
		Vector<String> colNames = new Vector();
		colNames.addElement("Page");
		colNames.addElement("Argument");
		colNames.addElement("Rest");

		this.tableModel = new CustomTableModel(this.parent.getArgs());

		this.table = new JTable(this.tableModel);
		this.table.setFillsViewportHeight(true);
		this.table.setDefaultRenderer(Object.class,
				new CustomTableCellRenderer(this.tableModel));

		this.table.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				int rowNb = FindPanel.this.table.getSelectedRow();
				if (rowNb >= 0) {
					Arg a = FindPanel.this.tableModel.getRow(rowNb);
					FindPanel.this.parent.getEPanel().setParams(
							a.getPageLink().substring(0,
									a.getPageLink().lastIndexOf('?')),
							a.getName(), a.getValue(), a.getRest(),
							a.getVulnType(), a.getMethod());
				}
				if (FindPanel.this.table.getSelectedColumn() == 3) {
					FindPanel.this.tableModel.getRow(
							FindPanel.this.table.getSelectedRow()).setTest(
							!FindPanel.this.tableModel.getRow(
									FindPanel.this.table.getSelectedRow())
									.getTest());
				}
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
			}
		});
		this.scrollPane = new JScrollPane();
		this.scrollPane.getViewport().add(this.table);
		this.c.fill = 2;
		this.c.gridx = 0;
		this.c.gridy = 0;
		//this.layout.setConstraints(this.testAllButton, this.c);
		this.c.gridx = 1;
		this.layout.setConstraints(this.testGetButton, this.c);
		this.c.gridx = 2;
		this.layout.setConstraints(this.testPostButton, this.c);
		this.c.weightx = 1.0D;
		this.c.weighty = 1.0D;
		this.c.gridy = 1;
		this.c.gridwidth = 3;
		this.c.gridx = 0;
		this.c.fill = 1;
		this.layout.setConstraints(this.scrollPane, this.c);
		this.testButton = new JButton("Test parameters for SQL injection");
		this.testButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				test_start_time = System.currentTimeMillis();
				FindPanel.this.parent.testSQL();
				FindPanel.this.parent.setStatus("Initialising tests...");
			}
		});
		this.stopButton = new JButton("Stop injections");
		this.stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FindPanel.this.parent.stopSQL();
				FindPanel.this.parent.setStatus("Stopped injecting");
			}
		});
		this.c.gridwidth = 1;
		this.c.gridy = 2;
		this.c.weighty = 0.0D;
		this.c.fill = 0;
		this.layout.setConstraints(this.testButton, this.c);
		this.c.gridx = 2;
		this.layout.setConstraints(this.stopButton, this.c);
		this.c.gridx = 0;
		this.c.gridy = 3;

		this.c.gridwidth = 3;

		add(this.testAllButton);
		//add(this.testGetButton);
		//add(this.testPostButton);
		add(this.scrollPane);
		add(this.testButton);
		add(this.stopButton);
	}

	public void addParam(Arg a) {
		this.tableModel.addRow(a);

		this.scrollPane.setPreferredSize(this.table.getSize());
		this.scrollPane.revalidate();
	}
}
