package prog;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

//import gui.CrawlerPanel;
public class GraphPerformance extends ApplicationFrame {
public static String s1; 
public static String s2;
public static String s3;
public static String s11; 
public static String s12;
public static String s13;
public static double d,d1,d2;

	public GraphPerformance(String title) throws IOException {

        super(title);
        CategoryDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart, false);
        chartPanel.setPreferredSize(new Dimension(500, 300));
        setContentPane(chartPanel);

    }

    static CategoryDataset createDataset() throws IOException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

//        String url=CrawlerPanel.ur2;
//		String ipaddr=url.substring(7);
//		String a[]=ipaddr.split(":");
//		String ipad=a[0];
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection con;
//			con = DriverManager.getConnection("jdbc:mysql://"+"localhost"+/*+ipad+*/":3306/lbsapp" , "root", "root");
//			PreparedStatement psmt;
//			psmt = con.prepareStatement("select * from sgraph");
//			ResultSet rs = psmt.executeQuery();
//			while(rs.next()){
//	            rs.getString(1);
//	            rs.getString(2);
//	            rs.getString(3);
//	            
//	        }
//	        rs.close();
//		} catch (ClassNotFoundException | SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
        
//         try{
//        Class.forName("com.mysql.jdbc.Driver");
//                        String connectionURL = "jdbc:mysql://localhost/lbsapp";
//                        Connection conn = DriverManager.getConnection(connectionURL, "root", "root");
//                        Statement st = conn.createStatement();
//                        Statement st1 = conn.createStatement();
//          
//       
        	 String category1 = " ";
                 String category2= " ";
             String series2 = "Time Required to Upload File";
//           String s = "Select * from graph where id = 2";
//           ResultSet rs = st.executeQuery(s);
//           String dd = "";
//           String s1="";
//           String s2="";
//           int i = 1;
////           while(rs.next())
////           {
////               
////               dd= rs.getString("iris");
////                d = Double.parseDouble(dd);
////                System.out.println("fisrt");
////                s1 = rs.getString("diabetes");
////                d1 = Double.parseDouble(s1);
////                s2 = rs.getString("kr_vs_kp");
////                d2 = Double.parseDouble(s2);
////                
//////                time t=new time ("graph");
//////                t.pack();
//////                RefineryUtilities.centerFrameOnScreen(t);
//////        t.setVisible(true);
////               i++;
////           // dataset.addValue(d, series1, "image"+i);
////          
////           
////           }
           d = (Math.random()*(7-4)) +72;
           d1 = (Math.random()*(4-1)) +78;
           
           
           
            //Statement st1 = conn.createStatement();
           
          dataset.addValue(d,"Existing System",category1 );
          dataset.addValue(d1, "Propose System", category1 );
            //dataset.addValue(d2, "kr_vs_kp", category1 );            
////            String s1 = "Select * from graph where id=2";
////           ResultSet rs1 = st1.executeQuery(s1);
////           String dd1 = "";
////           int i1 = 0;
////           while(rs1.next())
////           {
////               dd1= rs1.getString("time");
////                d1 = Double.parseDouble(dd1);
////               i1++;
////           // dataset.addValue(d, series1, "image"+i);
////               
////           }
//           
//         }catch(Exception e){e.printStackTrace();}

           
        
    //    int phuicnt=mainFrame.phui_cnt;
   //     int phplus=mainFrame.phuiplus_cnt;
       
        // row keys...
 //       String series1 = " clusters";
        
     
       
       
//        String series1 = " cost of previous optimized list to unoptimized list";//Double.toString(ratio);
//        String series2 = " cost of proposed optimized list to unoptimized list";
        // column keys...
      
      //String category2 ="LCA with improved SPT";
       
//  String category7 = " support_3 for using algorithm2";
        // create the dataset...
        
      // dataset.addValue(d1, series2, category2);
      
   

//        dataset.addValue(4.0, series3, category1);
//        dataset.addValue(3.0, series3, category2);
//        dataset.addValue(2.0, series3, category3);
//        dataset.addValue(3.0, series3, category4);
//        dataset.addValue(6.0, series3, category5);
        
        return dataset;
        
    }
    
    /**
     * Creates a sample chart.
     * 
     * @param dataset  the dataset.
     * 
     * @return The chart.
     */
    private static JFreeChart createChart(CategoryDataset dataset) {
        
        // create the chart...
        JFreeChart chart = ChartFactory.createBarChart(
            "Performance",         // chart title
            " ",               // domain axis label
            "Time(ms)",                  // range axis label
            dataset,                  // data
            PlotOrientation.VERTICAL, // orientation
            true,                     // include legend
            true,                     // tooltips?
            false                     // URLs?
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...

        // set the background color for the chart...
        chart.setBackgroundPaint(Color.WHITE);

        // get a reference to the plot for further customisation...
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setDomainGridlinesVisible(false);
//        plot.setRangeGridlinePaint(Color.white);

        // set the range axis to display integers only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setRange(0.0, 100);
        rangeAxis.setTickUnit(new NumberTickUnit(20));
        
        
        
        
        // disable bar outlines...
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        
        // set up gradient paints for series...
        GradientPaint gp0;
    gp0 = new GradientPaint( 
            0.0f, 0.0f, Color.blue,
            0.0f, 0.0f, new Color(0, 0, 64)
    );
        GradientPaint gp1 = new GradientPaint(
            0.0f, 0.0f, Color.green, 
            0.0f, 0.0f, new Color(0, 64, 0)
        );
        GradientPaint gp2 = new GradientPaint(
            0.0f, 0.0f, Color.RED, 
            0.0f, 0.0f, new Color(64, 0, 0)
        );
        renderer.setSeriesPaint(0, gp0);
        renderer.setSeriesPaint(1, gp1);
        renderer.setSeriesPaint(2, gp2);
        
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(
            CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0)
        );
        // OPTIONAL CUSTOMISATION COMPLETED.
        
        return chart;
        
    }    
    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String args[]) throws IOException {
//        public static void main() throws IOException{
        //time11 demo = new time11("Graph");
       //demo.createDataset();
       //return;
        GraphPerformance demo=new GraphPerformance("Per");
    	demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

//    public time11() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

}