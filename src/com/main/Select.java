package com.main;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;
import javax.swing.GroupLayout;

import gui.MainFrame;

public class Select extends JFrame{
	
	JPanel mainpanel =new JPanel();
	JLabel headlable =new JLabel("Welcome To Input Injection Detection");
	JLabel sublab =new JLabel("Select Method For Operations");

	JButton jiid=new JButton( " Using JIID ");
	JButton cjiid=new JButton(" Using CJIID ");

	public Select(){
		init();
	}

	private void init() {
		setTitle("JAVA Input Injection Detector : JIID");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainpanel.setPreferredSize(new Dimension(500, 300));
		mainpanel.setBackground(Color.CYAN);;
		
		GroupLayout layout = new GroupLayout(mainpanel);
		mainpanel.setLayout(layout);
		
		
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGap(150)      
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				   	  .addGap(100)
				      .addComponent(headlable)
				      .addGap(100)
				      .addComponent(sublab)
				      .addGap(20)
    				  .addComponent(jiid)
    				  .addGap(20)
    				  .addComponent(cjiid)
				      )		      
				);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGap(50)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(headlable)
						   .addGap(0))
				.addGap(30)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(sublab)
						.addGap(0)
				        )	
				.addGap(30)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(jiid)
						.addGap(0)
				        )	
				
				.addGap(30)
			    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(cjiid))
	    	    );
		
		
		this.add(mainpanel);
		this.pack();
		this.setLocationRelativeTo(null);
	
	jiid.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			new MainFrame().setVisible(true);
		}
	});
	
	cjiid.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			new MainFrame().setVisible(true);
		}
	});
	
	}
	
	
	
	public static void main(String[] args) {
		new Select().setVisible(true);
	}
}