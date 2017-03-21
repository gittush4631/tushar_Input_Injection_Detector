 package prog;

 import java.util.Vector;

 public class Column {
   String dbName;
   String tableName;
   String colName;
   Vector<String> values;

   public Column(String dbName, String tableName, String colName) { this.dbName = dbName;
     this.tableName = tableName;
     this.colName = colName;
     this.values = new Vector<String>();
   }

   public String getDbName() {
     return this.dbName;
   }

   public String getTableName() {
     return this.tableName;
   }

   public String getColName() {
     return this.colName;
   }

   public void addValue(String s) {
     this.values.addElement(s);
   }

   public void setCols(String s) {
     this.colName = s;
   }
 }
