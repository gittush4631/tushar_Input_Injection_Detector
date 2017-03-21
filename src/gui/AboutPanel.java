package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AboutPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private MainFrame parent;
	private JLabel label;
	private GridBagLayout layout;
	private GridBagConstraints c;
	private ImageIcon image;
	private JLabel imageLabel;

	public AboutPanel(MainFrame parent) {
		this.setParent(parent);
		init();
	}

	public void init() {
		this.layout = new GridBagLayout();
		this.c = new GridBagConstraints();
		setLayout(this.layout);
		this.c.weightx = 1.0D;
		this.c.weighty = 1.0D;
		this.label = new JLabel(
				"<html><center><font size=+5>Mini MySqlat0r</font><br/>version <b>0.5</b><br/><br/>powered by SCRT.</center></html>");
		this.layout.setConstraints(this.label, this.c);
		this.image = new ImageIcon(
				AboutPanel.class.getResource("logo_scrt_low.jpg"));
		this.c.gridy = 1;
		this.imageLabel = new JLabel(this.image, 0);
		this.layout.setConstraints(this.imageLabel, this.c);
		add(this.label);
		add(this.imageLabel);
	}

	public MainFrame getParent() {
		return parent;
	}

	public void setParent(MainFrame parent) {
		this.parent = parent;
	}
}
