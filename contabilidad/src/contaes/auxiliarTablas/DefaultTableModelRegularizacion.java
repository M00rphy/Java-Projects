/*    */ package contaes.auxiliarTablas;
/*    */ 
/*    */ import contaes.manejoDatos.TipoSubcuenta;
/*    */ import internationalization.Mensajes;
/*    */ import java.util.Vector;
/*    */ import javax.swing.table.DefaultTableModel;
/*    */ 
/*    */ public class DefaultTableModelRegularizacion extends DefaultTableModel
/*    */ {
/* 18 */   boolean[] canEdit = { false, false, true, true };
/*    */ 
/* 22 */   Class[] types = { TipoSubcuenta.class, Double.class, TipoSubcuenta.class, Double.class };
/*    */ 
/*    */   public DefaultTableModelRegularizacion(Object[][] data, Object[] columnNames)
/*    */   {
/* 30 */     super(data, columnNames);
/*    */   }
/*    */ 
/*    */   public DefaultTableModelRegularizacion(Vector data, Vector columnNames) {
/* 34 */     super(data, columnNames);
/*    */   }
/*    */ 
/*    */   public DefaultTableModelRegularizacion(Object[] columnNames, int rowCount) {
/* 38 */     super(columnNames, rowCount);
/*    */   }
/*    */ 
/*    */   public DefaultTableModelRegularizacion(Vector columnNames, int rowCount) {
/* 42 */     super(columnNames, rowCount);
/*    */   }
/*    */ 
/*    */   public DefaultTableModelRegularizacion(int rowCount, int columnCount) {
/* 46 */     super(rowCount, columnCount);
/*    */   }
/*    */ 
/*    */   public DefaultTableModelRegularizacion()
/*    */   {
/*    */   }
/*    */ 
/*    */   public boolean isCellEditable(int row, int column) {
/* 54 */     return this.canEdit[column];
/*    */   }
/*    */ 
/*    */   public Class getColumnClass(int columnIndex)
/*    */   {
/* 59 */     return this.types[columnIndex];
/*    */   }
/*    */ 
/*    */   public int getColumnCount()
/*    */   {
/* 64 */     return 4;
/*    */   }
/*    */ 
/*    */   public String getColumnName(int columnIndex)
/*    */   {
/*    */     String columnName;
/* 71 */     if ((columnIndex == 0) || (columnIndex == 2)) {
/* 72 */       columnName = Mensajes.getString("subcuenta");
/*    */     }
/*    */     else
/*    */     {
/*    */      
/* 73 */       if ((columnIndex == 1) || (columnIndex == 3))
/* 74 */         columnName = Mensajes.getString("importe");
/*    */       else
/* 76 */         throw new IndexOutOfBoundsException();
/*    */     }
/*    */   
/* 78 */     return columnName;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.auxiliarTablas.DefaultTableModelRegularizacion
 * JD-Core Version:    0.6.2
 */