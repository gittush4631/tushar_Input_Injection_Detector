package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
//import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import prog.Arg;
import prog.Column;
import prog.CrawlingThread;
import prog.CustomCrawler;
import prog.TestingThread;
import prog.WebPage;
import websphinx.Crawler;
import websphinx.Link;

import com.cluster.Publisher;
import com.cluster.Subscriber;

public class MainFrame extends JFrame {
	JTabbedPane tabs;
	CrawlerPanel cPanel;
	FindPanel fPanel;
	ExploitPanel ePanel;
	//InjectedResult gPanel;
	InjPanel ipanel;
	GraphPanel graphpan;
	
	JLabel statusLabel;
	AboutPanel aPanel;
	GridBagLayout layout;
	GridBagConstraints c;
	DefaultMutableTreeNode rootNode;
	DefaultTreeModel treeModel;
	JTree tree;
	JScrollPane treeView;
	Vector<WebPage> pages;
	Vector<Arg> args;
	Vector<Column> columns;
	CrawlingThread runningCrawl;
	TestingThread runningTest;
	private Component xpanel;
	//private XmlInjection XmlInjection;

	public MainFrame() {
		init();
	}

	public void init() {
		setTitle("Input Injection Detection");
		setDefaultCloseOperation(3);
		this.pages = new Vector();
		this.args = new Vector();
		this.columns = new Vector();

		this.layout = new GridBagLayout();
		this.getContentPane().setBackground(Color.DARK_GRAY);
		getContentPane().setLayout(this.layout);
		this.c = new GridBagConstraints();
		this.tabs = new JTabbedPane();
		this.rootNode = new DefaultMutableTreeNode("");
		this.treeModel = new DefaultTreeModel(this.rootNode);
		this.cPanel = new CrawlerPanel(this, this.treeModel, this.rootNode);
		this.fPanel = new FindPanel(this);
		this.ePanel = new ExploitPanel(this);
		//this.pPanel = new XmlInjection(this);
		//this.gPanel= new InjectedResult(this);
		this.ipanel=new InjPanel(this);
		this.xpanel=new Xmlpanel(this);
		this.graphpan =new GraphPanel();
		//this.XmlInjection= new XmlInjection(this);
		
		fPanel.setBackground(Color.CYAN);
		ipanel.setBackground(Color.CYAN);
		ePanel.setBackground(Color.CYAN);
		xpanel.setBackground(Color.CYAN);
		
		
		this.aPanel = new AboutPanel(this);
		aPanel.setBackground(Color.CYAN);
		this.tabs.addTab("Crawler", this.cPanel);
		cPanel.setBackground(Color.CYAN);
		
		this.tabs.addTab("Set SQL injection", this.fPanel);
//		this.tabs.addTab("SQL Exploiter", this.ePanel);
		this.tabs.addTab("Injected Result", this.ipanel);
		this.tabs.addTab("XML injection", this.xpanel);
		//this.tabs.addTab("Graphs", this.graphpan);
		//this.tabs.addTab("Xml Inj", this.XmlInjection);
		this.tabs.setAlignmentX(1.0F);
		this.c.anchor = 23;
		this.c.fill = 1;
		this.c.weightx = 1.0D;
		this.c.weighty = 1.0D;
		this.layout.setConstraints(this.tabs, this.c);
		this.c.weighty = 0.0D;
		this.c.gridy = 1;
		this.statusLabel = new JLabel("Idle");
		this.layout.setConstraints(this.statusLabel, this.c);
		getContentPane().add(this.tabs);
		getContentPane().add(this.statusLabel);
		pack();
	}

	public void setStatus(String status) {
		this.statusLabel.setText(status);
	}

	public Vector<Arg> getArgs() {
		return this.args;
	}

//	public static void main(String[] args) {
//		new MainFrame().setVisible(true);
//	}

	public void startCrawl() {
		try {
			this.rootNode.removeAllChildren();
			this.rootNode.setUserObject(this.cPanel.urlField.getText());
			this.treeModel.reload();
			this.fPanel.tableModel.clearTable();
			this.args = new Vector();
			
//			CustomCrawler c = new CustomCrawler(this, this.treeModel,
//					this.rootNode, this.pages);
						
			CustomCrawler c = new CustomCrawler(this, this.treeModel,
					this.rootNode, this.pages);
			c.setRoot(new Link(this.cPanel.urlField.getText()));
			c.setLinkType(Crawler.SUBTREE);

			this.runningCrawl = new CrawlingThread(this, c);
			this.runningCrawl.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void stopCrawl() {
		if (this.runningCrawl != null)
			this.runningCrawl.stopit();
	}

	public void testSQL() {
		this.runningTest = new TestingThread(this.args, this);
		this.runningTest.start();
	}

	public void stopSQL() {
		if (this.runningTest != null)
			this.runningTest.stopit();
	}

	public void addPage(WebPage p) {
		Vector<Arg> params = p.getParams();

		for (Arg va : params) {
			int pos = argPos(va);

			if (pos < 0) {
				this.args.addElement(va);
				this.fPanel.addParam(va);
				this.fPanel.table.revalidate();
				this.fPanel.repaint();
			} else {
				((Arg) this.args.get(pos)).addValue(va.getValue());
				this.fPanel.table.revalidate();
				this.fPanel.repaint();
			}
		}
	}

	public int argPos(Arg a) {
		int count = 0;
		for (Arg arg : this.args) {
			if ((a.getPageBase().equals(arg.getPageBase()))
					&& (a.getName().equals(arg.getName()))
					&& (a.getRest().equals(arg.getRest()))) {
				return count;
			}
			count++;
		}
		return -1;
	}

	public void addColumn(Column c) {
		this.columns.addElement(c);
	}

	public CrawlerPanel getCPanel() {
		return this.cPanel;
	}

	public FindPanel getFPanel() {
		return this.fPanel;
	}

	public ExploitPanel getEPanel() {
		return this.ePanel;
	}
	public InjPanel getGpanel(){
		return this.ipanel;
	}
}
