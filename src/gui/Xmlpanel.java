package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Xmlpanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel uname;
	private JLabel pass;
	private JTextField tunm;
	private JTextField tpass;
	private JButton submit;
	private JButton Show;
	private JTextField attrib;
	
	public Xmlpanel(MainFrame mainFrame) {
	
	uname=new JLabel("Enter userName");
	uname.setPreferredSize(new Dimension(200, 30));
	uname.setBorder(new LineBorder(Color.black));
	//uname.setBounds(10, 10,250,30);
	
	pass=new JLabel("Password");
	pass.setPreferredSize(new Dimension(200, 30));
	//pass.setBounds(50, 100, 250, 30);
	pass.setBorder(new LineBorder(Color.black));
	
	tunm=new JTextField();
	tunm.setPreferredSize(new Dimension(200, 30));
	//tunm.setBounds(150, 150, 250, 30);
	
	tpass=new JTextField();
	tpass.setPreferredSize(new Dimension(200, 30));
	
	submit =new JButton("Check injection");
	submit.setPreferredSize(new Dimension(200, 30));
	Show  =new JButton("Show injection");
	Show.setPreferredSize(new Dimension(200, 30));
	
	attrib=new JTextField();
	attrib.setPreferredSize(new Dimension(200, 30));
	
	this.add(attrib);
	this.add(uname);
	this.add(tunm);
	this.add(pass);
	this.add(tpass);
	this.add(submit);
	
	this.submit.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String unm=tunm.getText(),ps=tpass.getText();
			
			// TODO Auto-generated method stub
//			String url=CrawlerPanel.ur2;
//			String ipaddr=url.substring(7);
//			String a[]=ipaddr.split(":");
//			String ipad=a[0];
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con;
				con = DriverManager.getConnection("jdbc:mysql://"+"localhost"+/*+ipad+*/":3306/cpabe" , "root", "root");
				PreparedStatement psmt;
				psmt = con.prepareStatement("select * from registration");
				ResultSet rs = psmt.executeQuery();
				while(rs.next()){
					String id=rs.getString(1);
					String fname=rs.getString(2);
		            String lname=rs.getString(3);
		            String uname=rs.getString(4);
		            String pass=rs.getString(5);
		            String ip=rs.getString(6);
		            
		        }
		        rs.close();
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				 File f=new File("cars.xml"); 
		         
				 DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		         Document doc;
		         if(f.exists()){ doc=dBuilder.parse(f);
		         	Element root=doc.getDocumentElement();
		         

			         // root element     user_tag
			         //Element rootElement = doc.createElement("users");
			         //doc.appendChild(rootElement);

			         //  supercars element
			         Element supercar = doc.createElement("user");
			         root.appendChild(supercar);

			         // setting attribute to element
			         
			         Attr attr = doc.createAttribute("company");
			         String at=attrib.getText();
			         attr.setValue(at.toString());
			         supercar.setAttributeNode(attr);

			         // carname element
			         Element carname = doc.createElement("username");
//			         Attr attrType = doc.createAttribute("type");
		//	         attrType.setValue("");
			//         carname.setAttributeNode(attrType);
			         carname.appendChild(
			         doc.createTextNode(unm));
			         supercar.appendChild(carname);

			         Element carname1 = doc.createElement("pass");
//			         Attr attrType1 = doc.createAttribute("type");
		//	         attrType1.setValue("pass");
			//         carname1.setAttributeNode(attrType1);
			         carname1.appendChild(doc.createTextNode(ps));
			         supercar.appendChild(carname1);

			         // write the content into xml file
			         TransformerFactory transformerFactory = TransformerFactory.newInstance();
			         Transformer transformer = transformerFactory.newTransformer();
			         DOMSource source = new DOMSource(doc);
			        
			         StreamResult result =  new StreamResult(f);
			         transformer.transform(source, result);
			         // Output to console for testing
			         StreamResult consoleResult = new StreamResult(System.out);
			         transformer.transform(source, consoleResult);

		         
		         }
		         else {
		        	  doc = dBuilder.newDocument();
		        	  

				         // root element     user_tag
				         Element rootElement = doc.createElement("users");
				         doc.appendChild(rootElement);

				         //  supercars element
				         Element supercar = doc.createElement("user");
				         rootElement.appendChild(supercar);

				         // setting attribute to element
//				         Attr attr = doc.createAttribute("company");
	//			         attr.setValue("Ferrari");
		//		         supercar.setAttributeNode(attr);

				         // carname element
				         Element carname = doc.createElement("username");
//				         Attr attrType = doc.createAttribute("type");
			//	         attrType.setValue("");
				//         carname.setAttributeNode(attrType);
				         carname.appendChild(
				         doc.createTextNode(unm));
				         supercar.appendChild(carname);

				         Element carname1 = doc.createElement("pass");
//				         Attr attrType1 = doc.createAttribute("type");
			//	         attrType1.setValue("pass");
				//         carname1.setAttributeNode(attrType1);
				         carname1.appendChild(doc.createTextNode(ps));
				         supercar.appendChild(carname1);

				         // write the content into xml file
				         TransformerFactory transformerFactory = TransformerFactory.newInstance();
				         Transformer transformer = transformerFactory.newTransformer();
				         DOMSource source = new DOMSource(doc);
				        
				         StreamResult result =  new StreamResult(f);
				         transformer.transform(source, result);
				         // Output to console for testing
				         StreamResult consoleResult = new StreamResult(System.out);
				         transformer.transform(source, consoleResult);

		         }
		      } catch (Exception e) {
		         e.printStackTrace();
		      }					
		}
	});
	
	}
}
