 package gui;

 import java.awt.Component;
 import javax.swing.JTable;
 import javax.swing.table.TableCellRenderer;






 public class CustomCellRenderer
   implements TableCellRenderer
 {
   private TableCellRenderer __defaultRenderer;

   public CustomCellRenderer(TableCellRenderer renderer)
   {
     this.__defaultRenderer = renderer;
   }





   public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
   {
     if ((value instanceof Component))
       return (Component)value;
     return this.__defaultRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
   }
 }
