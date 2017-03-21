 package gui;

 import java.awt.Container;
 import java.awt.Dimension;
 import java.awt.FlowLayout;
 import javax.swing.JDialog;
 import javax.swing.JScrollPane;
 import javax.swing.JTextArea;

 public class FileContentDialog extends JDialog
 {
   private MainFrame parent;
   private String content;
   private JTextArea contentArea;
   private JScrollPane scrollPane;

   public FileContentDialog(MainFrame parent, String filename, String content)
   {
     this.parent = parent;
     this.content = content;
     setVisible(true);
     setTitle("Contents of " + filename);
     init();
     pack();
   }

   public void init() {
     this.contentArea = new JTextArea(this.content);
     this.scrollPane = new JScrollPane(this.contentArea);
     this.scrollPane.setPreferredSize(new Dimension(600, 600));
     getContentPane().setLayout(new FlowLayout());
     add(this.scrollPane);
   }
 }
