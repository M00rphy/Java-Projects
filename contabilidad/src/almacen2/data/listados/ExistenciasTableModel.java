/*    */ package almacen2.data.listados;
/*    */ 
/*    */ import internationalization.Mensajes;
/*    */ import java.util.ArrayList;
/*    */ import javax.swing.table.AbstractTableModel;
/*    */ 
/*    */ public class ExistenciasTableModel extends AbstractTableModel
/*    */ {
/*    */   private ArrayList<existenciaObject> objetos;
/*    */ 
/*    */   public ExistenciasTableModel(ArrayList<existenciaObject> objetos)
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
/* 26 */       columnName = Mensajes.getString("referencia");
/*    */     }
/*    */     else
/*    */     {
/*    */      
/* 27 */       if (columnIndex == 1) {
/* 28 */         columnName = Mensajes.getString("descripcion");
/*    */       }
/*    */       else
/*    */       {
/*    */       
/* 29 */         if (columnIndex == 2) {
/* 30 */           columnName = Mensajes.getString("unidades");
/*    */         }
/*    */         else
/*    */         {
/*    */         
/* 31 */           if (columnIndex == 3)
/* 32 */             columnName = Mensajes.getString("coste");
/*    */           else
/* 34 */             throw new IndexOutOfBoundsException();
/*    */         }
/*    */       }
/*    */     }
/*    */     
/* 36 */     return columnName;
/*    */   }
/*    */ 
/*    */   public int getRowCount() {
/* 40 */     return this.objetos.size();
/*    */   }
/*    */ 
/*    */   public Object getValueAt(int rowIndex, int columnIndex) {
/* 44 */     Object valor = null;
/* 45 */     existenciaObject objeto = (existenciaObject)this.objetos.get(rowIndex);
/* 46 */     if (columnIndex == 0)
/* 47 */       valor = objeto.getReferencia();
/* 48 */     else if (columnIndex == 1)
/* 49 */       valor = objeto.getDescripcion();
/* 50 */     else if (columnIndex == 2)
/* 51 */       valor = Integer.valueOf(objeto.getUnidades());
/* 52 */     else if (columnIndex == 3)
/* 53 */       valor = Double.valueOf(objeto.getCoste());
/*    */     else {
/* 55 */       throw new IndexOutOfBoundsException();
/*    */     }
/* 57 */     return valor;
/*    */   }
/*    */ 
/*    */   public void removeRow(int row) {
/* 61 */     this.objetos.remove(row);
/*    */   }
/*    */ 
/*    */   public void addRow(existenciaObject objeto) {
/* 65 */     this.objetos.add(objeto);
/*    */   }
/*    */ 
/*    */   public void addRow(existenciaObject objeto, int pos) {
/* 69 */     this.objetos.add(pos, objeto);
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.data.listados.ExistenciasTableModel
 * JD-Core Version:    0.6.2
 */