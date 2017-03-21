package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import prog.Arg;
import prog.WebPage;

public class CrawlerPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	JPanel targetPanel;
	GridBagLayout targetLayout;
	JPanel resultsPanel;
	GridBagLayout resultsLayout;
	GridBagLayout layout;
	GridBagConstraints c;
	JLabel urlLabel;
public	JTextField urlField;
	JButton urlButton;
	JButton stopButton;
	JTextArea crawlArea;
	DefaultMutableTreeNode rootNode;
	DefaultTreeModel treeModel;
	JTree tree;
	JScrollPane treeView;
	JLabel pagesCrawledLabel;
	JLabel pagesLeftLabel;
	Vector<WebPage> pages;
	Vector<Arg> vArgs;
	MainFrame parent;
	
	public static String ur2;
	public static long crawl_start_time;
	public static long crawl_end_time;
	public static long crawl_total_time;
	
	public CrawlerPanel(MainFrame parent, DefaultTreeModel treeModel,
			DefaultMutableTreeNode rootNode) {
		this.parent = parent;
		this.rootNode = rootNode;
		this.treeModel = treeModel;
		init();
	}

	public void init() {
		this.layout = new GridBagLayout();
		this.c = new GridBagConstraints();

		setLayout(this.layout);
		this.targetLayout = new GridBagLayout();
		this.targetPanel = new JPanel();
		targetPanel.setBackground(Color.CYAN);
		this.targetPanel.setBorder(BorderFactory.createTitledBorder("Target"));
		this.targetPanel.setLayout(this.targetLayout);
		this.resultsLayout = new GridBagLayout();
		this.resultsPanel = new JPanel();
		resultsPanel.setBackground(Color.CYAN);
		this.resultsPanel
				.setBorder(BorderFactory.createTitledBorder("Results"));
		this.resultsPanel.setLayout(this.resultsLayout);

		this.urlLabel = new JLabel("URL : ");
		this.urlField = new JTextField();
		this.urlField.setPreferredSize(new Dimension(200, 20));
		this.urlButton = new JButton("Start Crawling!");
		this.urlButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crawl_start_time = System.currentTimeMillis();
				CrawlerPanel.this.parent.startCrawl();
				
			}
		});
		this.stopButton = new JButton("Stop Crawling!");
		this.stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrawlerPanel.this.parent.stopCrawl();
				crawl_end_time = System.currentTimeMillis();
				crawl_total_time = crawl_end_time - crawl_start_time;
			}
		});
		this.c.anchor = 23;
		this.c.fill = 1;
		this.c.gridx = 0;
		this.c.gridy = 0;
		this.targetLayout.setConstraints(this.urlLabel, this.c);
		this.c.gridx = 1;
		this.c.weightx = 1.0D;
		this.targetLayout.setConstraints(this.urlField, this.c);
		this.c.gridx = 2;
		this.c.weightx = 0.0D;
		this.targetLayout.setConstraints(this.urlButton, this.c);
		this.c.gridx = 3;
		this.targetLayout.setConstraints(this.stopButton, this.c);

		this.c.gridx = 0;
		this.c.gridy = 1;
		this.c.gridwidth = 4;

		this.c = new GridBagConstraints();
		this.c.fill = 1;
		this.c.anchor = 23;
		this.c.gridx = 0;
		this.c.gridy = 0;
		this.c.weightx = 1.0D;
		this.c.weighty = 1.0D;
		this.c.gridwidth = 2;
		this.tree = new JTree(this.treeModel);
		this.treeView = new JScrollPane(this.tree);
		this.resultsLayout.setConstraints(this.treeView, this.c);
		this.pagesCrawledLabel = new JLabel("Pages crawled : 0");
		this.pagesLeftLabel = new JLabel("Pages remaining : 0");
		this.c.gridwidth = 1;
		this.c.gridy = 1;
		this.c.weighty = 0.0D;
		this.resultsLayout.setConstraints(this.pagesCrawledLabel, this.c);
		this.c.gridx = 1;
		this.resultsLayout.setConstraints(this.pagesLeftLabel, this.c);

		this.targetPanel.add(this.urlLabel);
		this.targetPanel.add(this.urlField);
		this.targetPanel.add(this.urlButton);
		this.targetPanel.add(this.stopButton);
		this.resultsPanel.add(this.treeView);
		this.resultsPanel.add(this.pagesCrawledLabel);
		this.resultsPanel.add(this.pagesLeftLabel);

		this.c.gridx = 0;
		this.c.gridy = 0;
		this.c.fill = 1;
		this.c.weighty = 0.0D;
		this.layout.setConstraints(this.targetPanel, this.c);
		this.c.gridy = 1;
		this.c.weighty = 1.0D;
		this.layout.setConstraints(this.resultsPanel, this.c);
		
		urlField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ur2=urlField.getText();
				System.out.println(ur2);
			}
		});
		
		
		add(this.targetPanel);
		add(this.resultsPanel);
	}

	public void setNbPagesVisited(int nb) {
		this.pagesCrawledLabel.setText("Pages crawled : " + String.valueOf(nb));
	}

	public void setNbPagesLeft(int nb) {
		this.pagesLeftLabel.setText("Pages left : " + String.valueOf(nb));
	}
}
