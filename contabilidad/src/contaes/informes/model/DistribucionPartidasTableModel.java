/*    */ package contaes.informes.model;
/*    */ 
/*    */ import internationalization.Mensajes;
/*    */ import java.util.ArrayList;
/*    */ import javax.swing.table.AbstractTableModel;
/*    */ 
/*    */ public class DistribucionPartidasTableModel extends AbstractTableModel
/*    */ {
/*    */   private ArrayList<DistribucionPartidasObject> objetos;
/*    */ 
/*    */   public DistribucionPartidasTableModel(ArrayList<DistribucionPartidasObject> objetos)
/*    */   {
/* 14 */     this.objetos = objetos;
/*    */   }
/*    */ 
/*    */   public int getColumnCount() {
/* 18 */     return 3;
/*    */   }
/*    */ 
/*    */   public String getColumnName(int columnIndex)
/*    */   {
/*    */     String columnName;
/* 25 */     if (columnIndex == 0) {
/* 26 */       columnName = Mensajes.getString("nombre");
/*    */     }
/*    */     else
/*    */     {
/*    */     
/* 27 */       if (columnIndex == 1) {
/* 28 */         columnName = Mensajes.getString("importe");
/*    */       }
/*    */       else
/*    */       {
/*    */       
/* 29 */         if (columnIndex == 2)
/* 30 */           columnName = "%";
/*    */         else
/* 32 */           throw new IndexOutOfBoundsException();
/*    */       }
/*    */     }
/*    */    
/* 34 */     return columnName;
/*    */   }
/*    */ 
/*    */   public int getRowCount() {
/* 38 */     return this.objetos.size();
/*    */   }
/*    */ 
/*    */   public Object getValueAt(int rowIndex, int columnIndex) {
/* 42 */     Object valor = null;
/* 43 */     DistribucionPartidasObject objeto = (DistribucionPartidasObject)this.objetos.get(rowIndex);
/* 44 */     if (columnIndex == 0)
/* 45 */       valor = objeto.getNombre();
/* 46 */     else if (columnIndex == 1)
/* 47 */       valor = objeto.getImporte();
/* 48 */     else if (columnIndex == 2)
/* 49 */       valor = objeto.getPorcentaje();
/*    */     else {
/* 51 */       throw new IndexOutOfBoundsException();
/*    */     }
/* 53 */     return valor;
/*    */   }
/*    */ 
/*    */   public void removeRow(int row) {
/* 57 */     this.objetos.remove(row);
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.informes.model.DistribucionPartidasTableModel
 * JD-Core Version:    0.6.2
 */