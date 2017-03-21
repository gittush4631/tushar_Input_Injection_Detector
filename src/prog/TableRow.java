 package prog;

 import java.util.Vector;

 public class TableRow
 {
   String schemaName;
   String tableName;
   Vector<String> colNames;

   public TableRow(String schemaName, String tableName, String colName)
   {
     this.schemaName = schemaName;
     this.tableName = tableName;
     this.colNames = new Vector();
     this.colNames.addElement(colName);
   }

   public void addColumn(String name)
   {
     this.colNames.addElement(name);
   }

   public String getSchemaName() { return this.schemaName; }

   public String getTableName() {
     return this.tableName;
   }

   public Vector<String> getColNames() { return this.colNames; }
 }
