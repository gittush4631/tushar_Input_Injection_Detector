package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.naming.InitialContext;
import javax.swing.DefaultBoundedRangeModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.jfree.chart.ChartPanel;
import org.jfree.ui.RefineryUtilities;

import prog.GraphEfficiency;
import prog.GraphPerformance;
import prog.GraphSecurity;
import prog.time11;

public class GraphPanel extends JPanel{

	//private JComboBox<time11> c;
	public static JPanel gp;
	private JButton graph1;
	private JButton graphEff;
	private JButton graphPer;
	private JButton graphSec;
	private JTextArea ta1;
	private JPanel panel;
	
	time11 t1 ;
	GraphEfficiency Ef;
	GraphPerformance Per;
	GraphSecurity Sec;
	
	public GraphPanel() {
		// TODO Auto-generated constructor stub
		try {
			
			Ef=new GraphEfficiency("Efficiency");
			Per =new GraphPerformance("Performance");
			Sec =new GraphSecurity("Security");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//this.c=new JComboBox<time11>();
		//this.add(c);
		//c.addItem(t1);
		//gp.add(t1);
		panel = new JPanel();
		
		graph1=new JButton("Graph1");
		graph1.setBounds(50, 50, 250, 20);
		
		graphEff=new JButton(" Efficiency");
		
		graphPer=new JButton("Performance");
		graphSec=new JButton("  Security  ");

		this.add(panel);
		
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
				      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				   	  .addGap(100)
		//		      .addComponent(graph1)
				      .addGap(100)
    				  .addComponent(graphEff)
    				  .addGap(100)
    				  .addComponent(graphPer)
    				  .addGap(100)
    				  .addComponent(graphSec)
				      )				      
				);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGap(50)
//				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//						.addComponent(graph1)
//						   .addGap(25))   
				.addGap(50)
   			   .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				   .addComponent(graphEff)
				   .addGap(25))	
			   .addGap(50)
		       .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(graphPer)
				.addGap(50))
			   .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(graphSec))
				    );
		
	graph1.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//t1.main(null);
			try {
				t1=new time11("g");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			t1.pack();
			RefineryUtilities.centerFrameOnScreen(t1);
		    t1.setVisible(true);
		//t1.dispose();	
		}
	});

	graphEff.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Ef.pack();
			RefineryUtilities.centerFrameOnScreen(Ef);
		    Ef.setVisible(true);
		}
	});
	
	graphPer.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Per.pack();
			RefineryUtilities.centerFrameOnScreen(Per);
		    Per.setVisible(true);
		}
	});
	
	graphSec.addActionListener(new ActionListener() {		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Sec.pack();
			RefineryUtilities.centerFrameOnScreen(Sec);
		    Sec.setVisible(true);
		}
	});
	
	}	
}