 package gui;

 import java.awt.event.MouseEvent;
 import javax.swing.JButton;
 import javax.swing.JTable;
 import javax.swing.SwingUtilities;
 import javax.swing.table.TableColumnModel;

 class JTableButtonMouseListener implements java.awt.event.MouseListener
 {
   private JTable __table;

   private void __forwardEventToButton(MouseEvent e)
   {
     TableColumnModel columnModel = this.__table.getColumnModel();
     int column = columnModel.getColumnIndexAtX(e.getX());
     int row = e.getY() / this.__table.getRowHeight();




     if ((row >= this.__table.getRowCount()) || (row < 0) ||
       (column >= this.__table.getColumnCount()) || (column < 0)) {
       return;
     }
     Object value = this.__table.getValueAt(row, column);

     if (!(value instanceof JButton)) {
       return;
     }
     JButton button = (JButton)value;

     MouseEvent buttonEvent =
       SwingUtilities.convertMouseEvent(this.__table, e, button);
     button.dispatchEvent(buttonEvent);



     this.__table.repaint();
   }

   public JTableButtonMouseListener(JTable table) {
     this.__table = table;
   }

   public void mouseClicked(MouseEvent e)
   {
     __forwardEventToButton(e);
   }

   public void mouseEntered(MouseEvent e) {
     __forwardEventToButton(e);
   }

   public void mouseExited(MouseEvent e) {
     __forwardEventToButton(e);
   }

   public void mousePressed(MouseEvent e) {
     __forwardEventToButton(e);
   }

   public void mouseReleased(MouseEvent e) {
     __forwardEventToButton(e);
   }
 }
