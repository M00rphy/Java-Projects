/*    */ package facturacion.model;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import javax.swing.table.AbstractTableModel;
/*    */ 
/*    */ public class ProductoTableModel extends AbstractTableModel
/*    */ {
/*    */   private ArrayList<Producto> objetos;
/*    */ 
/*    */   public ProductoTableModel(ArrayList<Producto> productos)
/*    */   {
/* 20 */     this.objetos = productos;
/*    */   }
/*    */ 
/*    */   public int getRowCount() {
/* 24 */     return this.objetos.size();
/*    */   }
/*    */ 
/*    */   public int getColumnCount() {
/* 28 */     return 4;
/*    */   }
/*    */ 
/*    */   public Object getValueAt(int rowIndex, int columnIndex) {
/* 32 */     Object valor = null;
/* 33 */     Producto objeto = (Producto)this.objetos.get(rowIndex);
/* 34 */     if (columnIndex == 0)
/* 35 */       valor = objeto.getId();
/* 36 */     else if (columnIndex == 1)
/* 37 */       valor = objeto.getDescripcion();
/* 38 */     else if (columnIndex == 2)
/* 39 */       valor = objeto.getSubcuenta();
/* 40 */     else if (columnIndex == 3)
/* 41 */       valor = objeto.getPrecio();
/*    */     else {
/* 43 */       throw new IndexOutOfBoundsException();
/*    */     }
/* 45 */     return valor;
/*    */   }
/*    */ 
/*    */   public String getColumnName(int columnIndex)
/*    */   {
/*    */     String columnName;
/* 52 */     if (columnIndex == 0) {
/* 53 */       columnName = "idProducto";
/*    */     }
/*    */     else
/*    */     {
/*    */   
/* 54 */       if (columnIndex == 1) {
/* 55 */         columnName = "Descripción";
/*    */       }
/*    */       else
/*    */       {
/*    */       
/* 56 */         if (columnIndex == 2) {
/* 57 */           columnName = "Subcuenta";
/*    */         }
/*    */         else
/*    */         {
/*    */          
/* 58 */           if (columnIndex == 3)
/* 59 */             columnName = "Precio";
/*    */           else
/* 61 */             throw new IndexOutOfBoundsException();
/*    */         }
/*    */       }
/*    */     }
/*    */     
/* 63 */     return columnName;
/*    */   }
/*    */ 
/*    */   public void removeRow(int row) {
/* 67 */     this.objetos.remove(row);
/*    */   }
/*    */ 
/*    */   public void addRow(Producto objeto) {
/* 71 */     this.objetos.add(objeto);
/*    */   }
/*    */ 
/*    */   public void addRow(Producto objeto, int pos) {
/* 75 */     this.objetos.add(pos, objeto);
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     facturacion.model.ProductoTableModel
 * JD-Core Version:    0.6.2
 */