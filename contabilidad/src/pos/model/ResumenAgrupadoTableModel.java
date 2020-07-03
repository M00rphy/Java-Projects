/*    */ package pos.model;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import javax.swing.table.AbstractTableModel;
/*    */ 
/*    */ public class ResumenAgrupadoTableModel extends AbstractTableModel
/*    */ {
/*    */   ArrayList<ResumenAgrupado> objetos;
/*    */ 
/*    */   public ResumenAgrupadoTableModel(ArrayList<ResumenAgrupado> objetos)
/*    */   {
/* 20 */     this.objetos = objetos;
/*    */   }
/*    */ 
/*    */    public int getRowCount() {
/* 24 */     return this.objetos.size();
/*    */   }
/*    */ 
/*    */   public int getColumnCount() {
/* 28 */     return 2;
/*    */   }
/*    */ 
/*    */   public Object getValueAt(int rowIndex, int columnIndex) {
/* 32 */     Object valor = null;
/* 33 */     ResumenAgrupado objeto = (ResumenAgrupado)this.objetos.get(rowIndex);
/* 34 */     if (columnIndex == 0)
/* 35 */       valor = objeto.getNombre();
/* 36 */     else if (columnIndex == 1)
/* 37 */       valor = Double.valueOf(objeto.getImporte());
/*    */     else {
/* 39 */       throw new IndexOutOfBoundsException();
/*    */     }
/* 41 */     return valor;
/*    */   }
/*    */ 
/*    */   public ArrayList<ResumenAgrupado> getObjetos() {
/* 45 */     return this.objetos;
/*    */   }
/*    */ 
/*    */   public String getColumnName(int columnIndex)
/*    */   {
/*    */     String columnName;
/* 52 */     if (columnIndex == 0) {
/* 53 */       columnName = "Concepto";
/*    */     }
/*    */     else
/*    */     {
/*    */     
/* 54 */       if (columnIndex == 1)
/* 55 */         columnName = "Importe";
/*    */       else
/* 57 */         throw new IndexOutOfBoundsException();
/*    */     }
/*    */     
/* 59 */     return columnName;
/*    */   }
/*    */ 
/*    */   public void removeRow(int row) {
/* 63 */     this.objetos.remove(row);
/*    */   }
/*    */ 
/*    */   public void addRow(ResumenAgrupado objeto) {
/* 67 */     this.objetos.add(objeto);
/*    */   }
/*    */ 
/*    */   public void addRow(ResumenAgrupado objeto, int pos) {
/* 71 */     this.objetos.add(pos, objeto);
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.model.ResumenAgrupadoTableModel
 * JD-Core Version:    0.6.2
 */