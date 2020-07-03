/*     */ package pos.model;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.table.AbstractTableModel;
/*     */ 
/*     */ public class LineaVentaPOSTableModel extends AbstractTableModel
/*     */ {
/*     */   private ArrayList<VentaPOS> objetos;
/*     */ 
/*     */   public LineaVentaPOSTableModel(ArrayList<VentaPOS> VentaPOSs)
/*     */   {
/*  19 */     this.objetos = VentaPOSs;
/*     */   }
/*     */ 
/*     */   public LineaVentaPOSTableModel() {
/*  23 */     this.objetos = new ArrayList();
/*     */   }
/*     */ 
/*     */   public int getRowCount() {
/*  27 */     return this.objetos.size();
/*     */   }
/*     */ 
/*     */   public int getColumnCount() {
/*  31 */     return 6;
/*     */   }
/*     */ 
/*     */   public Object getValueAt(int rowIndex, int columnIndex) {
/*  35 */     Object valor = null;
/*  36 */     VentaPOS objeto = (VentaPOS)this.objetos.get(rowIndex);
/*  37 */     if (columnIndex == 0)
/*  38 */       valor = objeto.getId();
/*  39 */     else if (columnIndex == 1)
/*  40 */       valor = objeto.getDescripcion();
/*  41 */     else if (columnIndex == 2)
/*  42 */       valor = Integer.valueOf(objeto.getUnidades());
/*  43 */     else if (columnIndex == 3)
/*  44 */       valor = Double.valueOf(objeto.getImporte());
/*  45 */     else if (columnIndex == 4)
/*  46 */       valor = Double.valueOf(objeto.getTotal());
/*  47 */     else if (columnIndex == 5) {
/*  48 */       if (objeto.getNota().equals("")) {
/*  49 */         valor = Boolean.valueOf(false);
/*     */       }
/*     */       else
/*  52 */         valor = Boolean.valueOf(true);
/*     */     }
/*     */     else {
/*  55 */       throw new IndexOutOfBoundsException();
/*     */     }
/*  57 */     return valor;
/*     */   }
/*     */ 
/*     */   public VentaPOS getObjectAt(int rowIndex) {
/*  61 */     VentaPOS objeto = (VentaPOS)this.objetos.get(rowIndex);
/*  62 */     return objeto;
/*     */   }
/*     */ 
/*     */   public ArrayList<VentaPOS> getObjetos() {
/*  66 */     return this.objetos;
/*     */   }
/*     */ 
/*     */   public String getColumnName(int columnIndex)
/*     */   {
/*     */     String columnName;
/*  73 */     if (columnIndex == 0) {
/*  74 */       columnName = "id";
/*     */     }
/*     */     else
/*     */     {
/*     */      
/*  75 */       if (columnIndex == 1) {
/*  76 */         columnName = "Descripción";
/*     */       }
/*     */       else
/*     */       {
/*     */        
/*  77 */         if (columnIndex == 2) {
/*  78 */           columnName = "Unidades";
/*     */         }
/*     */         else
/*     */         {
/*     */           
/*  79 */           if (columnIndex == 3) {
/*  80 */             columnName = "Precio";
/*     */           }
/*     */           else
/*     */           {
/*     */            
/*  81 */             if (columnIndex == 4) {
/*  82 */               columnName = "Total";
/*     */             }
/*     */             else
/*     */             {
/*     */               
/*  83 */               if (columnIndex == 5)
/*  84 */                 columnName = "N";
/*     */               else
/*  86 */                 throw new IndexOutOfBoundsException();
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*  88 */     return columnName;
/*     */   }
/*     */ 
/*     */   public void removeRow(int row) {
/*  92 */     this.objetos.remove(row);
/*     */   }
/*     */ 
/*     */   public void addRow(VentaPOS objeto) {
/*  96 */     this.objetos.add(objeto);
/*     */   }
/*     */ 
/*     */   public void addRow(VentaPOS objeto, int pos) {
/* 100 */     this.objetos.add(pos, objeto);
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.model.LineaVentaPOSTableModel
 * JD-Core Version:    0.6.2
 */