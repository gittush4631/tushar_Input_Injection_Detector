 package gui;

 import javax.swing.JButton;
 import prog.TableRow;



 public class DumpButton
   extends JButton
 {
   private TableRow row;

   public DumpButton(TableRow row)
   {
     super("Dump !");
     this.row = row;
   }

   public TableRow getRow() {
     return this.row;
   }
 }
