/*    */ package almacen2.data;
/*    */ 
/*    */ import internationalization.Mensajes;
/*    */ import java.util.ArrayList;
/*    */ import javax.swing.table.AbstractTableModel;
/*    */ 
/*    */ public class PIOListTableModel extends AbstractTableModel
/*    */ {
/*    */   private ArrayList<PIOListObject> objetos;
/*    */ 
/*    */   public PIOListTableModel(ArrayList<PIOListObject> objetos)
/*    */   {
/* 14 */     this.objetos = objetos;
/*    */   }
/*    */ 
/*    */   public int getColumnCount() {
/* 18 */     return 4;
/*    */   }
/*    */ 
/*    */   public String getColumnName(int columnIndex)
/*    */   {
/*    */     String columnName;
/* 25 */     if (columnIndex == 0) {
/* 26 */       columnName = Mensajes.getString("fecha");
/*    */     }
/*    */     else
/*    */     {
/*    */     
/* 27 */       if (columnIndex == 1) {
/* 28 */         columnName = Mensajes.getString("unidades");
/*    */       }
/*    */       else
/*    */       {
/*    */       
/* 29 */         if (columnIndex == 2) {
/* 30 */           columnName = Mensajes.getString("importe");
/*    */         }
/*    */         else
/*    */         {
/*    */         
/* 31 */           if (columnIndex == 3)
/* 32 */             columnName = Mensajes.getString("almacen");
/*    */           else
/* 34 */             throw new IndexOutOfBoundsException();
/*    */         }
/*    */       }
/*    */     }
/*    */  
/* 37 */     return columnName;
/*    */   }
/*    */ 
/*    */   public int getRowCount()
/*    */   {
/* 42 */     return this.objetos.size();
/*    */   }
/*    */ 
/*    */   public Object getValueAt(int rowIndex, int columnIndex) {
/* 46 */     Object valor = null;
/* 47 */     PIOListObject objeto = (PIOListObject)this.objetos.get(rowIndex);
/*    */ 
/* 49 */     if (columnIndex == 0)
/* 50 */       valor = objeto.getFecha();
/* 51 */     else if (columnIndex == 1)
/* 52 */       valor = objeto.getUnidades();
/* 53 */     else if (columnIndex == 2)
/* 54 */       valor = objeto.getImporte();
/* 55 */     else if (columnIndex == 3)
/* 56 */       valor = Integer.valueOf(objeto.getAlmacen());
/*    */     else {
/* 58 */       throw new IndexOutOfBoundsException();
/*    */     }
/* 60 */     return valor;
/*    */   }
/*    */ 
/*    */   public void removeRow(int row) {
/* 64 */     this.objetos.remove(row);
/*    */   }
/*    */ 
/*    */   public void addRow(PIOListObject objeto) {
/* 68 */     this.objetos.add(objeto);
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.data.PIOListTableModel
 * JD-Core Version:    0.6.2
 */