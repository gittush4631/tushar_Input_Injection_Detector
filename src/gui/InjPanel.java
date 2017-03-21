package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JViewport;
import prog.Arg;

public class InjPanel extends JPanel {
	private GridBagLayout layout;
	private GridBagConstraints c;
	private JScrollPane scrollPane;
	public CustomTableModel tableModel;
	public JTable table;
	private MainFrame parent;
	
	private JLabel result;
	private JButton testArea;
	private JTextArea textresult;
	private JButton testAllButton;
	private JButton testGetButton;
	private JButton testPostButton;
	private JButton press;
	public static long test_start_time;
	
	public InjPanel(MainFrame parent) {
		this.parent = parent;
		init();
	}

	public void init() {
		this.layout = new GridBagLayout();
		this.c = new GridBagConstraints();
		setLayout(this.layout);
		setBorder(BorderFactory.createTitledBorder("Injected result"));
		this.c.anchor = 19;
		this.c.fill = 1;

		this.testAllButton = new JButton("(Un)Select All parameters");
		this.testAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < InjPanel.this.tableModel.getRowCount(); i++) {
					InjPanel.this.tableModel.getRow(i).setTest(
							!InjPanel.this.tableModel.getRow(i).getTest());
					InjPanel.this.repaint();
				}
			}
		});
		this.testGetButton = new JButton("(Un)Select GET parameters");
		this.testGetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < InjPanel.this.tableModel.getRowCount(); i++) {
					if (InjPanel.this.tableModel.getRow(i).getMethod() == 0) {
						InjPanel.this.tableModel.getRow(i).setTest(
								!InjPanel.this.tableModel.getRow(i).getTest());
						InjPanel.this.repaint();
					}
				}
			}
		});
		this.testPostButton = new JButton("(Un)Select POST parameters");
		this.testPostButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < InjPanel.this.tableModel.getRowCount(); i++) {
					if (InjPanel.this.tableModel.getRow(i).getMethod() == 1) {
						InjPanel.this.tableModel.getRow(i).setTest(
								!InjPanel.this.tableModel.getRow(i).getTest());
						InjPanel.this.repaint();
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
				int rowNb = InjPanel.this.table.getSelectedRow();
				if (rowNb >= 0) {
					Arg a = InjPanel.this.tableModel.getRow(rowNb);
					InjPanel.this.parent.getEPanel().setParams(
							a.getPageLink().substring(0,
									a.getPageLink().lastIndexOf('?')),
							a.getName(), a.getValue(), a.getRest(),
							a.getVulnType(), a.getMethod());
				}
				if (InjPanel.this.table.getSelectedColumn() == 3) {
					InjPanel.this.tableModel.getRow(
							InjPanel.this.table.getSelectedRow()).setTest(
							!InjPanel.this.tableModel.getRow(
									InjPanel.this.table.getSelectedRow())
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
		this.layout.setConstraints(this.testAllButton, this.c);
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
		this.testArea = new JButton("Test parameters for SQL injection");
		
		this.testArea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				test_start_time = System.currentTimeMillis();
				InjPanel.this.parent.testSQL();
				InjPanel.this.parent.setStatus("Initialising tests...");
			}
		});

		this.press=new JButton("press");
		//press.setSize(10, 5);
		this.add(press);
		this.press.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//CrawlerPanel c=new CrawlerPanel(null, null, null);
				String url=CrawlerPanel.ur2;
				String ipaddr=url.substring(7);
				String a[]=ipaddr.split(":");
				String ipad=a[0];
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con;
					con = DriverManager.getConnection("jdbc:mysql://"+ipad+":3306/cpabe" , "root", "root");
					PreparedStatement psmt;
					psmt = con.prepareStatement("select * from registration");
					ResultSet rs = psmt.executeQuery();
					while(rs.next()){
			            textresult.append("\t"+rs.getString(1)+"\t");
			            textresult.append(rs.getString(2)+"\t");
			            textresult.append(rs.getString(3)+"\t");
			            textresult.append(rs.getString(4)+"\t");
			            textresult.append(rs.getString(5)+"\t");
			            textresult.append(rs.getString(6)+"\t"+"\n");
			            
			        }
			        rs.close();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		this.result=new JLabel("Result :");
		this.textresult = new JTextArea();
		
		press.setVisible(true);
		textresult.setPreferredSize(new Dimension(1000, 500));
//		this.stopButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				InjPanel.this.parent.stopSQL();
//				InjPanel.this.parent.setStatus("Stopped injecting");
//			}
//		});
		this.c.gridwidth = 1;
		this.c.gridy = 2;
		this.c.weighty = 0.0D;
		this.c.fill = 0;
		this.layout.setConstraints(this.testArea, this.c);
		this.c.gridx = 2;
		this.layout.setConstraints(this.textresult, this.c);
		this.c.gridx = 0;
		this.c.gridy = 3;

		this.c.gridwidth = 3;

		//add(this.testAllButton);
		//add(this.testGetButton);
		//add(this.testPostButton);
		//add(this.scrollPane);
		//add(this.testButton);
		
		//add(this.result);
		add(this.textresult);
	}

	public void addParam(Arg a) {
		this.tableModel.addRow(a);

		this.scrollPane.setPreferredSize(this.table.getSize());
		this.scrollPane.revalidate();
	}
}
