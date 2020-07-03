/*    */ package contaes.informes.model;
/*    */ 
/*    */ import internationalization.Mensajes;
/*    */ import java.util.ArrayList;
/*    */ import javax.swing.table.AbstractTableModel;
/*    */ 
/*    */ public class ResumenFacturasTableModel extends AbstractTableModel
/*    */ {
/*    */   private ArrayList<ResumenFacturasObject> objetos;
/*    */ 
/*    */   public ResumenFacturasTableModel(ArrayList<ResumenFacturasObject> objetos)
/*    */   {
/* 15 */     this.objetos = objetos;
/*    */   }
/*    */ 
/*    */   public int getColumnCount() {
/* 19 */     return 3;
/*    */   }
/*    */ 
/*    */   public String getColumnName(int columnIndex)
/*    */   {
/*    */     String columnName;
/* 26 */     if (columnIndex == 0) {
/* 27 */       columnName = Mensajes.getString("mes");
/*    */     }
/*    */     else
/*    */     {
/*    */     
/* 28 */       if (columnIndex == 1) {
/* 29 */         columnName = Mensajes.getString("nombre");
/*    */       }
/*    */       else
/*    */       {
/*    */         
/* 30 */         if (columnIndex == 2)
/* 31 */           columnName = Mensajes.getString("importe");
/*    */         else
/* 33 */           throw new IndexOutOfBoundsException();
/*    */       }
/*    */     }
/*    */    
/* 35 */     return columnName;
/*    */   }
/*    */ 
/*    */   public int getRowCount() {
/* 39 */     return this.objetos.size();
/*    */   }
/*    */ 
/*    */   public Object getValueAt(int rowIndex, int columnIndex) {
/* 43 */     Object valor = null;
/* 44 */     ResumenFacturasObject objeto = (ResumenFacturasObject)this.objetos.get(rowIndex);
/* 45 */     if (columnIndex == 0)
/* 46 */       valor = objeto.getMes();
/* 47 */     else if (columnIndex == 1)
/* 48 */       valor = objeto.getNombre();
/* 49 */     else if (columnIndex == 2)
/* 50 */       valor = objeto.getImporte();
/*    */     else {
/* 52 */       throw new IndexOutOfBoundsException();
/*    */     }
/* 54 */     return valor;
/*    */   }
/*    */ 
/*    */   public void removeRow(int row) {
/* 58 */     this.objetos.remove(row);
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.informes.model.ResumenFacturasTableModel
 * JD-Core Version:    0.6.2
 */