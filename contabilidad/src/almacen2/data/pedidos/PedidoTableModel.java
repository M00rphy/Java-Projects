/*    */ package almacen2.data.pedidos;
/*    */ 
/*    */ import internationalization.Mensajes;
/*    */ import java.util.ArrayList;
/*    */ import javax.swing.table.AbstractTableModel;
/*    */ 
/*    */ public class PedidoTableModel extends AbstractTableModel
/*    */ {
/*    */   private ArrayList<LineaPedidoObject> objetos;
/*    */   private boolean compras;
/*    */ 
/*    */   public PedidoTableModel(ArrayList<LineaPedidoObject> objetos, boolean compras)
/*    */   {
/* 15 */     this.objetos = objetos;
/* 16 */     this.compras = compras;
/*    */   }
/*    */ 
/*    */   public int getColumnCount() {
/* 20 */     return 6;
/*    */   }
/*    */ 
/*    */   public String getColumnName(int columnIndex)
/*    */   {
/*    */     String columnName;
/* 27 */     if (columnIndex == 0) {
/* 28 */       columnName = Mensajes.getString("id");
/*    */     }
/*    */     else
/*    */     {
/*    */     
/* 29 */       if (columnIndex == 1) {
/* 30 */         columnName = Mensajes.getString("referencia");
/*    */       }
/*    */       else
/*    */       {
/*    */         
/* 31 */         if (columnIndex == 2) {
/* 32 */           columnName = Mensajes.getString("descripcion");
/*    */         }
/*    */         else
/*    */         {
/*    */          
/* 33 */           if (columnIndex == 3) {
/* 34 */             columnName = Mensajes.getString("unidades");
/*    */           }
/*    */           else
/*    */           {
/*    */          
/* 35 */             if ((columnIndex == 4) && (this.compras)) {
/* 36 */               columnName = Mensajes.getString("coste");
/*    */             }
/*    */             else
/*    */             {
/*    */              
/* 37 */               if ((columnIndex == 4) && (!this.compras)) {
/* 38 */                 columnName = Mensajes.getString("importe");
/*    */               }
/*    */               else
/*    */               {
/*    */              
/* 39 */                 if ((columnIndex == 5) && (this.compras)) {
/* 40 */                   columnName = Mensajes.getString("recibido");
/*    */                 }
/*    */                 else
/*    */                 {
/*    */                  
/* 41 */                   if ((columnIndex == 5) && (!this.compras))
/* 42 */                     columnName = Mensajes.getString("enviado");
/*    */                   else
/* 44 */                     throw new IndexOutOfBoundsException();
/*    */                 }
/*    */               }
/*    */             }
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */    
/* 46 */     return columnName;
/*    */   }
/*    */ 
/*    */   public int getRowCount() {
/* 50 */     return this.objetos.size();
/*    */   }
/*    */ 
/*    */   public Object getValueAt(int rowIndex, int columnIndex) {
/* 54 */     Object valor = null;
/* 55 */     LineaPedidoObject objeto = (LineaPedidoObject)this.objetos.get(rowIndex);
/* 56 */     if (columnIndex == 0)
/* 57 */       valor = Integer.valueOf(objeto.getId());
/* 58 */     else if (columnIndex == 1)
/* 59 */       valor = objeto.getReferencia();
/* 60 */     else if (columnIndex == 2)
/* 61 */       valor = objeto.getDescripcion();
/* 62 */     else if (columnIndex == 3)
/* 63 */       valor = Integer.valueOf(objeto.getUnidades());
/* 64 */     else if (columnIndex == 4)
/* 65 */       valor = Double.valueOf(objeto.getCoste());
/* 66 */     else if (columnIndex == 5)
/* 67 */       valor = Boolean.valueOf(objeto.isRecibido());
/*    */     else {
/* 69 */       throw new IndexOutOfBoundsException();
/*    */     }
/* 71 */     return valor;
/*    */   }
/*    */ 
/*    */   public void removeRow(int row) {
/* 75 */     this.objetos.remove(row);
/*    */   }
/*    */ 
/*    */   public void addRow(LineaPedidoObject objeto) {
/* 79 */     this.objetos.add(objeto);
/*    */   }
/*    */ 
/*    */   public void addRow(LineaPedidoObject objeto, int pos) {
/* 83 */     this.objetos.add(pos, objeto);
/*    */   }
/*    */ 
/*    */   public void changeState(int rowIndex) {
/* 87 */     LineaPedidoObject objeto = (LineaPedidoObject)this.objetos.get(rowIndex);
/* 88 */     Boolean state = Boolean.valueOf(objeto.isRecibido());
/* 89 */     objeto.setRecibido(!state.booleanValue());
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.data.pedidos.PedidoTableModel
 * JD-Core Version:    0.6.2
 */