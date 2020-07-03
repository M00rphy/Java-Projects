/*    */ package almacen2.data.listados;
/*    */ 
/*    */ import internationalization.Mensajes;
/*    */ import java.util.ArrayList;
/*    */ import javax.swing.table.AbstractTableModel;
/*    */ 
/*    */ public class AgrupadoTableModel extends AbstractTableModel
/*    */ {
/*    */   private ArrayList<AgrupadoObject> objetos;
/*    */ 
/*    */   public AgrupadoTableModel(ArrayList<AgrupadoObject> objetos)
/*    */   {
/* 14 */     this.objetos = objetos;
/*    */   }
/*    */ 
/*    */   public int getColumnCount() {
/* 18 */     return 5;
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
/* 28 */         columnName = Mensajes.getString("compras") + " " + Mensajes.getString("unidades");
/*    */       }
/*    */       else
/*    */       {
/*    */    
/* 29 */         if (columnIndex == 2) {
/* 30 */           columnName = Mensajes.getString("compras") + " " + Mensajes.getString("importe");
/*    */         }
/*    */         else
/*    */         {
/*    */     
/* 31 */           if (columnIndex == 3) {
/* 32 */             columnName = Mensajes.getString("ventas") + " " + Mensajes.getString("unidades");
/*    */           }
/*    */           else
/*    */           {
/*    */            
/* 33 */             if (columnIndex == 4)
/* 34 */               columnName = Mensajes.getString("ventas") + " " + Mensajes.getString("importe");
/*    */             else
/* 36 */               throw new IndexOutOfBoundsException();
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */ 
/* 38 */     return columnName;
/*    */   }
/*    */ 
/*    */   public int getRowCount() {
/* 42 */     return this.objetos.size();
/*    */   }
/*    */ 
/*    */   public Object getValueAt(int rowIndex, int columnIndex) {
/* 46 */     Object valor = null;
/* 47 */     AgrupadoObject objeto = (AgrupadoObject)this.objetos.get(rowIndex);
/* 48 */     if (columnIndex == 0)
/* 49 */       valor = objeto.getNombre();
/* 50 */     else if (columnIndex == 1)
/* 51 */       valor = objeto.getUnidadesCompra();
/* 52 */     else if (columnIndex == 2)
/* 53 */       valor = objeto.getTotalCompra();
/* 54 */     else if (columnIndex == 3)
/* 55 */       valor = objeto.getUnidadesVenta();
/* 56 */     else if (columnIndex == 4)
/* 57 */       valor = objeto.getTotalVenta();
/*    */     else {
/* 59 */       throw new IndexOutOfBoundsException();
/*    */     }
/* 61 */     return valor;
/*    */   }
/*    */ 
/*    */   public void removeRow(int row) {
/* 65 */     this.objetos.remove(row);
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.data.listados.AgrupadoTableModel
 * JD-Core Version:    0.6.2
 */