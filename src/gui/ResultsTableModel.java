 package gui;

 import java.util.Vector;
 import javax.swing.table.AbstractTableModel;
 import prog.TableRow;










 public class ResultsTableModel
   extends AbstractTableModel
 {
   Vector<TableRow> rows;
   Vector<DumpButton> dumpButtons;
   ExploitPanel parent;
   protected String[] columnNames = {
     "Schema Name", "Table Name", "Columns", "" };


   public ResultsTableModel(ExploitPanel parent)
   {
     this.parent = parent;
     this.rows = new Vector();
     this.dumpButtons = new Vector();
   }



   public int getColumnCount()
   {
     return 4;
   }


   public int getRowCount()
   {
     return this.rows.size();
   }

   public TableRow getRow(int row) {
     return (TableRow)this.rows.get(row);
   }

   public String getColumnName(int col) {
     return this.columnNames[col];
   }

   public void addRow(TableRow row) {
     int r = findRow(row);
     if (r > -1) {
       ((TableRow)this.rows.get(r)).addColumn((String)row.getColNames().get(0));
     }
     else {
       this.rows.addElement(row);
       DumpButton button = new DumpButton(row);
       button.addMouseListener(new SpecialMouseListener(row, this.parent));
       this.dumpButtons.addElement(button);
     }
   }


   public void removeAll()
   {
     this.rows.removeAllElements();
     this.dumpButtons.removeAllElements();
   }

   public int findRow(TableRow c) {
     for (TableRow curr : this.rows) {
       if ((curr.getSchemaName().equals(c.getSchemaName())) && (curr.getTableName().equals(c.getTableName()))) {
         return this.rows.indexOf(curr);
       }
     }
     return -1;
   }

   public Class getColumnClass(int column) { return getValueAt(0, column).getClass(); }


   public Object getValueAt(int row, int col)
   {
     TableRow curr = (TableRow)this.rows.get(row);
     switch (col) {
     case 0:
       return curr.getSchemaName();
     case 1:
       return curr.getTableName();
     case 2:
       return curr.getColNames();
     case 3:
       return this.dumpButtons.get(row);
     }
     return Integer.valueOf(0);
   }
 }
