/*    */ package almacen2.data.pedidos;
/*    */ 
/*    */ import internationalization.Mensajes;
/*    */ import java.util.ArrayList;
/*    */ import javax.swing.table.AbstractTableModel;
/*    */ 
/*    */ public class BusquedaTableModel extends AbstractTableModel
/*    */ {
/*    */   private ArrayList<BusquedaObject> objetos;
/*    */ 
/*    */   public BusquedaTableModel(ArrayList<BusquedaObject> objetos)
/*    */   {
/* 14 */     this.objetos = objetos;
/*    */   }
/*    */ 
/*    */   public int getColumnCount() {
/* 18 */     return 6;
/*    */   }
/*    */ 
/*    */   public String getColumnName(int columnIndex)
/*    */   {
/*    */     String columnName;
/* 25 */     if (columnIndex == 0) {
/* 26 */       columnName = "id";
/*    */     }
/*    */     else
/*    */     {
/*    */       
/* 27 */       if (columnIndex == 1) {
/* 28 */         columnName = Mensajes.getString("codigo");
/*    */       }
/*    */       else
/*    */       {
/*    */         
/* 29 */         if (columnIndex == 2) {
/* 30 */           columnName = Mensajes.getString("fecha");
/*    */         }
/*    */         else
/*    */         {
/*    */           
/* 31 */           if (columnIndex == 3) {
/* 32 */             columnName = Mensajes.getString("proveedor");
/*    */           }
/*    */           else
/*    */           {
/*    */           
/* 33 */             if (columnIndex == 4) {
/* 34 */               columnName = Mensajes.getString("unidades");
/*    */             }
/*    */             else
/*    */             {
/*    */              
/* 35 */               if (columnIndex == 5)
/* 36 */                 columnName = Mensajes.getString("coste");
/*    */               else
/* 38 */                 throw new IndexOutOfBoundsException();
/*    */             }
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */    
/* 40 */     return columnName;
/*    */   }
/*    */ 
/*    */   public int getRowCount() {
/* 44 */     return this.objetos.size();
/*    */   }
/*    */ 
/*    */   public Object getValueAt(int rowIndex, int columnIndex) {
/* 48 */     Object valor = null;
/* 49 */     BusquedaObject objeto = (BusquedaObject)this.objetos.get(rowIndex);
/* 50 */     if (columnIndex == 0)
/* 51 */       valor = objeto.getId();
/* 52 */     else if (columnIndex == 1)
/* 53 */       valor = objeto.getCodigo();
/* 54 */     else if (columnIndex == 2)
/* 55 */       valor = objeto.getFecha();
/* 56 */     else if (columnIndex == 3)
/* 57 */       valor = objeto.getProveedor();
/* 58 */     else if (columnIndex == 4)
/* 59 */       valor = Integer.valueOf(objeto.getSumaPiezas());
/* 60 */     else if (columnIndex == 5)
/* 61 */       valor = Double.valueOf(objeto.getSumaCoste());
/*    */     else {
/* 63 */       throw new IndexOutOfBoundsException();
/*    */     }
/* 65 */     return valor;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.data.pedidos.BusquedaTableModel
 * JD-Core Version:    0.6.2
 */