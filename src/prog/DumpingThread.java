 package prog;

 import gui.ExploitPanel;

 public class DumpingThread extends Thread {
   private ExploitPanel ep;
   private TableRow row;

   public DumpingThread(ExploitPanel ep, TableRow row) {
     this.ep = ep;
     this.row = row;
   }

   public void run() {
     this.ep.dumpTable(this.row);
   }
 }
