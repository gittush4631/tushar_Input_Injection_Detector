 package gui;

 import java.awt.event.MouseEvent;
 import prog.DumpingThread;
 import prog.TableRow;

 public class SpecialMouseListener implements java.awt.event.MouseListener
 {
   TableRow r;
   ExploitPanel parent;

   public SpecialMouseListener(TableRow r, ExploitPanel parent)
   {
     this.r = r;
     this.parent = parent;
   }

   public void mouseClicked(MouseEvent e)
   {
     new DumpingThread(this.parent, this.r).start();
   }

   public void mouseEntered(MouseEvent e) {}

   public void mouseExited(MouseEvent e) {}

   public void mousePressed(MouseEvent e) {}

   public void mouseReleased(MouseEvent e) {}
 }
