package prog;

import gui.MainFrame;

import java.util.Enumeration;
import java.util.Vector;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import websphinx.Page;

public class CustomCrawler extends websphinx.Crawler {
	
	private static final long serialVersionUID = 1L;
	DefaultTreeModel treeModel;
	DefaultMutableTreeNode rootNode;
	Vector<WebPage> pages;
	Vector<String> treeDone;
	MainFrame parent;

	public CustomCrawler(MainFrame parent, DefaultTreeModel treeModel,
			DefaultMutableTreeNode rootNode, Vector<WebPage> pages) {
		this.treeModel = treeModel;
		this.rootNode = rootNode;
		this.pages = pages;
		this.parent = parent;

		this.treeDone = new Vector();
	}

	public void visit(Page p) {
//		System.out.println("URL: " + p.toURL() + " : Pages Left: " + getPagesLeft());
		System.out.println(p.toURL() + " : " + getPagesLeft());
		this.parent.getCPanel().setNbPagesVisited(getPagesVisited());
		this.parent.getCPanel().setNbPagesLeft(getPagesLeft() - 1);
		WebPage wp = new WebPage(p);
		this.parent.addPage(wp);
		this.pages.add(wp);

		String s = p.toURL().replaceFirst("https?://[^/]+/?", "");
//		String s = p.toURL().replaceFirst("https?://[fname]+/?", "");

		addNodes(s, this.rootNode);
		System.out.println(p.toURL());
		this.parent.setStatus("Crawling " + p.toURL() + " ...");
		System.out.println(s);
		p.discardContent();
	}

	public void addNodes(String s, DefaultMutableTreeNode node) {
		String rest;
		String current;
		if (s.indexOf('/') > 0) {
			current = s.substring(0, s.indexOf('/'));
			rest = s.substring(s.indexOf('/') + 1);
		} else {
			current = s;
			rest = "";
		}

		DefaultMutableTreeNode newNode = getNewNode(current, node);
		if (!rest.equals("")) {
			addNodes(rest, newNode);
		}
		if (node.getIndex(newNode) < 0)
			this.treeModel.insertNodeInto(newNode, node, node.getChildCount());
	}

	public DefaultMutableTreeNode getNewNode(String name,
			DefaultMutableTreeNode node) {
		Enumeration e = node.children();
		while (e.hasMoreElements()) {
			DefaultMutableTreeNode cur = (DefaultMutableTreeNode) e
					.nextElement();
			String testName = (String) cur.getUserObject();
			if (testName.equals(name)) {
				return cur;
			}
		}
		return new DefaultMutableTreeNode(name);
	}
}
