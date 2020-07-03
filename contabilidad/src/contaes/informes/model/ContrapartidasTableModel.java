/*    */ package contaes.informes.model;
/*    */ 
/*    */ import internationalization.Mensajes;
/*    */ import java.util.ArrayList;
/*    */ import javax.swing.table.AbstractTableModel;
/*    */ 
/*    */ public class ContrapartidasTableModel extends AbstractTableModel
/*    */ {
/*    */   private ArrayList<ContrapartidaObject> objetos;
/*    */ 
/*    */   public ContrapartidasTableModel(ArrayList<ContrapartidaObject> objetos)
/*    */   {
/* 14 */     this.objetos = objetos;
/*    */   }
/*    */ 
/*    */   public int getColumnCount() {
/* 18 */     return 2;
/*    */   }
/*    */ 
/*    */   public String getColumnName(int columnIndex)
/*    */   {
/*    */     String columnName;
/* 25 */     if (columnIndex == 0) {
/* 26 */       columnName = Mensajes.getString("subcuenta");
/*    */     }
/*    */     else
/*    */     {
/*    */     
/* 27 */       if (columnIndex == 1)
/* 28 */         columnName = Mensajes.getString("importe");
/*    */       else
/* 30 */         throw new IndexOutOfBoundsException();
/*    */     }
/*    */     
/* 32 */     return columnName;
/*    */   }
/*    */ 
/*    */   public int getRowCount() {
/* 36 */     return this.objetos.size();
/*    */   }
/*    */ 
/*    */   public Object getValueAt(int rowIndex, int columnIndex) {
/* 40 */     Object valor = null;
/* 41 */     ContrapartidaObject objeto = (ContrapartidaObject)this.objetos.get(rowIndex);
/* 42 */     if (columnIndex == 0)
/* 43 */       valor = objeto.getSubcuenta();
/* 44 */     else if (columnIndex == 1)
/* 45 */       valor = objeto.getImporte();
/*    */     else {
/* 47 */       throw new IndexOutOfBoundsException();
/*    */     }
/* 49 */     return valor;
/*    */   }
/*    */ 
/*    */   public void removeRow(int row) {
/* 53 */     this.objetos.remove(row);
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.informes.model.ContrapartidasTableModel
 * JD-Core Version:    0.6.2
 */