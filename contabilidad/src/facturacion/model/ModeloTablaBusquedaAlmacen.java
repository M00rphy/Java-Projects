/*    */ package facturacion.model;
/*    */ 
/*    */ import internationalization.Mensajes;
/*    */ import java.util.ArrayList;
/*    */ import javax.swing.table.AbstractTableModel;
/*    */ 
/*    */ public class ModeloTablaBusquedaAlmacen extends AbstractTableModel
/*    */ {
/*    */   private ArrayList<ObjetoBusquedaAlmacen> objetos;
/*    */ 
/*    */   public ModeloTablaBusquedaAlmacen(ArrayList<ObjetoBusquedaAlmacen> objetos)
/*    */   {
/* 14 */     this.objetos = objetos;
/*    */   }
/*    */ 
/*    */   public int getColumnCount() {
/* 18 */     return 7;
/*    */   }
/*    */ 
/*    */   public String getColumnName(int columnIndex)
/*    */   {
/*    */     String columnName;
/* 25 */     if (columnIndex == 0) {
/* 26 */       columnName = Mensajes.getString("referencia");
/*    */     }
/*    */     else
/*    */     {
/*    */  
/* 27 */       if (columnIndex == 1) {
/* 28 */         columnName = Mensajes.getString("familia");
/*    */       }
/*    */       else
/*    */       {
/*    */      
/* 29 */         if (columnIndex == 2) {
/* 30 */           columnName = Mensajes.getString("proveedor");
/*    */         }
/*    */         else
/*    */         {
/*    */     
/* 31 */           if (columnIndex == 3) {
/* 32 */             columnName = Mensajes.getString("descripcion");
/*    */           }
/*    */           else
/*    */           {
/*    */           
/* 33 */             if (columnIndex == 4) {
/* 34 */               columnName = Mensajes.getString("coste");
/*    */             }
/*    */             else
/*    */             {
/*    */              
/* 35 */               if (columnIndex == 5) {
/* 36 */                 columnName = Mensajes.getString("pvp");
/*    */               }
/*    */               else
/*    */               {
/*    */                
/* 37 */                 if (columnIndex == 6)
/* 38 */                   columnName = Mensajes.getString("stck");
/*    */                 else
/* 40 */                   throw new IndexOutOfBoundsException();
/*    */               }
/*    */             }
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */     
/* 42 */     return columnName;
/*    */   }
/*    */ 
/*    */   public int getRowCount() {
/* 46 */     return this.objetos.size();
/*    */   }
/*    */ 
/*    */   public Object getValueAt(int rowIndex, int columnIndex) {
/* 50 */     Object valor = null;
/* 51 */     ObjetoBusquedaAlmacen objeto = (ObjetoBusquedaAlmacen)this.objetos.get(rowIndex);
/* 52 */     if (columnIndex == 0)
/* 53 */       valor = objeto.getReferencia();
/* 54 */     else if (columnIndex == 1)
/* 55 */       valor = objeto.getFamilia();
/* 56 */     else if (columnIndex == 2)
/* 57 */       valor = objeto.getProveedor();
/* 58 */     else if (columnIndex == 3)
/* 59 */       valor = objeto.getDescripcion();
/* 60 */     else if (columnIndex == 4)
/* 61 */       valor = Double.valueOf(objeto.getCoste());
/* 62 */     else if (columnIndex == 5)
/* 63 */       valor = Double.valueOf(objeto.getPrecio());
/* 64 */     else if (columnIndex == 6)
/* 65 */       valor = Integer.valueOf(objeto.getStock());
/*    */     else {
/* 67 */       throw new IndexOutOfBoundsException();
/*    */     }
/* 69 */     return valor;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     facturacion.model.ModeloTablaBusquedaAlmacen
 * JD-Core Version:    0.6.2
 */