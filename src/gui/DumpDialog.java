 package gui;

 import java.awt.Container;
 import java.awt.GridBagConstraints;
 import java.awt.GridBagLayout;
 import javax.swing.JDialog;
 import javax.swing.JScrollPane;
 import javax.swing.JTable;
 import javax.swing.table.DefaultTableModel;
 import prog.TableRow;



 public class DumpDialog
   extends JDialog
 {
   private MainFrame parent;
   private TableRow row;
   private JTable table;
   private DefaultTableModel tableModel;
   private JScrollPane scrollPane;
   private GridBagLayout layout;
   private GridBagConstraints c;

   public DumpDialog(MainFrame parent, TableRow row)
   {
     this.parent = parent;
     this.row = row;
     setVisible(true);
     setTitle("Content of " + row.getSchemaName() + "." + row.getTableName());
     init();
     pack();
   }

   public void init() {
     this.layout = new GridBagLayout();
     this.c = new GridBagConstraints();
     this.tableModel = new DefaultTableModel();
     this.tableModel.setColumnIdentifiers(this.row.getColNames());
     this.table = new JTable(this.tableModel);
     this.scrollPane = new JScrollPane(this.table);
     this.c.weightx = 1.0D;
     this.c.weighty = 1.0D;
     this.c.fill = 1;
     this.layout.setConstraints(this.scrollPane, this.c);
     getContentPane().setLayout(this.layout);
     add(this.scrollPane);
   }

   public void addResult(String res) {
     String[] cols = res.split("\\x7C\\x7C\\x7C");
     this.tableModel.addRow(cols);
   }
 }
