/*    */ package contaes.manejoDatos;
/*    */ 
/*    */ import internationalization.Mensajes;
/*    */ import java.util.ArrayList;
/*    */ import javax.swing.table.AbstractTableModel;
/*    */ 
/*    */ public class InformeProductosTableModel extends AbstractTableModel
/*    */ {
/*    */   private ArrayList<TipoInformeProductos> objetos;
/*    */ 
/*    */   public InformeProductosTableModel(ArrayList<TipoInformeProductos> objetos)
/*    */   {
/* 14 */     this.objetos = objetos;
/*    */   }
/*    */ 
/*    */   public int getColumnCount()
/*    */   {
/* 19 */     return 5;
/*    */   }
/*    */ 
/*    */   public String getColumnName(int columnIndex)
/*    */   {
/*    */     String columnName;
/* 26 */     if (columnIndex == 0) {
/* 27 */       columnName = Mensajes.getString("referencia");
/*    */     }
/*    */     else
/*    */     {
/*    */     
/* 28 */       if (columnIndex == 1) {
/* 29 */         columnName = Mensajes.getString("descripcion");
/*    */       }
/*    */       else
/*    */       {
/*    */       
/* 30 */         if (columnIndex == 2) {
/* 31 */           columnName = Mensajes.getString("unidades");
/*    */         }
/*    */         else
/*    */         {
/*    */          
/* 32 */           if (columnIndex == 3) {
/* 33 */             columnName = Mensajes.getString("coste");
/*    */           }
/*    */           else
/*    */           {
/*    */           
/* 34 */             if (columnIndex == 4)
/* 35 */               columnName = Mensajes.getString("pvp");
/*    */             else
/* 37 */               throw new IndexOutOfBoundsException();
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */     
/* 39 */     return columnName;
/*    */   }
/*    */ 
/*    */   public int getRowCount()
/*    */   {
/* 44 */     return this.objetos.size();
/*    */   }
/*    */ 
/*    */   public Object getValueAt(int rowIndex, int columnIndex)
/*    */   {
/* 49 */     Object valor = null;
/* 50 */     TipoInformeProductos objeto = (TipoInformeProductos)this.objetos.get(rowIndex);
/* 51 */     if (columnIndex == 0)
/* 52 */       valor = objeto.getReferencia();
/* 53 */     else if (columnIndex == 1)
/* 54 */       valor = objeto.getDescripcion();
/* 55 */     else if (columnIndex == 2)
/* 56 */       valor = Integer.valueOf(objeto.getUnidades());
/* 57 */     else if (columnIndex == 3)
/* 58 */       valor = Double.valueOf(objeto.getCoste());
/* 59 */     else if (columnIndex == 4)
/* 60 */       valor = Double.valueOf(objeto.getPvp());
/*    */     else {
/* 62 */       throw new IndexOutOfBoundsException();
/*    */     }
/* 64 */     return valor;
/*    */   }
/*    */ 
/*    */   public void removeRow(int row) {
/* 68 */     this.objetos.remove(row);
/*    */   }
/*    */ 
/*    */   public void addRow(TipoInformeProductos objeto) {
/* 72 */     this.objetos.add(objeto);
/*    */   }
/*    */ 
/*    */   public void addRow(TipoInformeProductos objeto, int pos) {
/* 76 */     this.objetos.add(pos, objeto);
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.InformeProductosTableModel
 * JD-Core Version:    0.6.2
 */