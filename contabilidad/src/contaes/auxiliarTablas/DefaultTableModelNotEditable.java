/*    */ package contaes.auxiliarTablas;
/*    */ 
/*    */ import java.util.Vector;
/*    */ import javax.swing.table.DefaultTableModel;
/*    */ 
/*    */ public class DefaultTableModelNotEditable extends DefaultTableModel
/*    */ {
/*    */   public DefaultTableModelNotEditable(Object[][] data, Object[] columnNames)
/*    */   {
/* 18 */     super(data, columnNames);
/*    */   }
/*    */ 
/*    */   public DefaultTableModelNotEditable(Vector data, Vector columnNames) {
/* 22 */     super(data, columnNames);
/*    */   }
/*    */ 
/*    */   public DefaultTableModelNotEditable(Object[] columnNames, int rowCount) {
/* 26 */     super(columnNames, rowCount);
/*    */   }
/*    */ 
/*    */   public DefaultTableModelNotEditable(Vector columnNames, int rowCount) {
/* 30 */     super(columnNames, rowCount);
/*    */   }
/*    */ 
/*    */   public DefaultTableModelNotEditable(int rowCount, int columnCount) {
/* 34 */     super(rowCount, columnCount);
/*    */   }
/*    */ 
/*    */   public DefaultTableModelNotEditable()
/*    */   {
/*    */   }
/*    */ 
/*    */   public boolean isCellEditable(int row, int column) {
/* 42 */     return false;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.auxiliarTablas.DefaultTableModelNotEditable
 * JD-Core Version:    0.6.2
 */