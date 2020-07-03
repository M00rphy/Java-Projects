/*     */ package pos.model;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.table.AbstractTableModel;
/*     */ 
/*     */ public class ResumenVentaTicketTableModel extends AbstractTableModel
/*     */ {
/*     */   private ArrayList<ResumenVentaTicket> objetos;
/*     */ 
/*     */   public ResumenVentaTicketTableModel(ArrayList<ResumenVentaTicket> lineas)
/*     */   {
/*  19 */     this.objetos = lineas;
/*     */   }
/*     */ 
/*     */   public ResumenVentaTicketTableModel() {
/*  23 */     this.objetos = new ArrayList();
/*     */   }
/*     */ 
/*     */   public int getRowCount() {
/*  27 */     return this.objetos.size();
/*     */   }
/*     */ 
/*     */   public int getColumnCount() {
/*  31 */     return 8;
/*     */   }
/*     */ 
/*     */   public Object getValueAt(int rowIndex, int columnIndex) {
/*  35 */     Object valor = null;
/*  36 */     ResumenVentaTicket objeto = (ResumenVentaTicket)this.objetos.get(rowIndex);
/*  37 */     if (columnIndex == 0)
/*  38 */       valor = Integer.valueOf(objeto.getTicket());
/*  39 */     else if (columnIndex == 1)
/*  40 */       valor = objeto.getDescripcion();
/*  41 */     else if (columnIndex == 2)
/*  42 */       valor = Integer.valueOf(objeto.getUnidades());
/*  43 */     else if (columnIndex == 3)
/*  44 */       valor = Double.valueOf(objeto.getImporte());
/*  45 */     else if (columnIndex == 4)
/*  46 */       valor = Double.valueOf(objeto.getTotal());
/*  47 */     else if (columnIndex == 5)
/*  48 */       valor = objeto.getMedioPago();
/*  49 */     else if (columnIndex == 6)
/*  50 */       valor = Integer.valueOf(objeto.getPlazos());
/*  51 */     else if (columnIndex == 7) {
/*  52 */       if (objeto.getNota().equals("")) {
/*  53 */         valor = Boolean.valueOf(false);
/*     */       }
/*     */       else
/*  56 */         valor = Boolean.valueOf(true);
/*     */     }
/*     */     else {
/*  59 */       throw new IndexOutOfBoundsException();
/*     */     }
/*  61 */     return valor;
/*     */   }
/*     */ 
/*     */   public ResumenVentaTicket getObjectAt(int rowIndex) {
/*  65 */     ResumenVentaTicket objeto = (ResumenVentaTicket)this.objetos.get(rowIndex);
/*  66 */     return objeto;
/*     */   }
/*     */ 
/*     */   public ArrayList<ResumenVentaTicket> getObjetos() {
/*  70 */     return this.objetos;
/*     */   }
/*     */ 
/*     */   public String getColumnName(int columnIndex)
/*     */   {
/*     */     String columnName;
/*  77 */     if (columnIndex == 0) {
/*  78 */       columnName = "Ticket";
/*     */     }
/*     */     else
/*     */     {
/*     */      
/*  79 */       if (columnIndex == 1) {
/*  80 */         columnName = "Descripción";
/*     */       }
/*     */       else
/*     */       {
/*     */      
/*  81 */         if (columnIndex == 2) {
/*  82 */           columnName = "Unidades";
/*     */         }
/*     */         else
/*     */         {
/*     */           
/*  83 */           if (columnIndex == 3) {
/*  84 */             columnName = "Precio";
/*     */           }
/*     */           else
/*     */           {
/*     */            
/*  85 */             if (columnIndex == 4) {
/*  86 */               columnName = "Total";
/*     */             }
/*     */             else
/*     */             {
/*     */               
/*  87 */               if (columnIndex == 5) {
/*  88 */                 columnName = "Pago";
/*     */               }
/*     */               else
/*     */               {
/*     */               
/*  89 */                 if (columnIndex == 6) {
/*  90 */                   columnName = "Pl";
/*     */                 }
/*     */                 else
/*     */                 {
/*     */                 
/*  91 */                   if (columnIndex == 7)
/*  92 */                     columnName = "N";
/*     */                   else
/*  94 */                     throw new IndexOutOfBoundsException();
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*  96 */     return columnName;
/*     */   }
/*     */ 
/*     */   public void removeRow(int row) {
/* 100 */     this.objetos.remove(row);
/*     */   }
/*     */ 
/*     */   public void addRow(ResumenVentaTicket objeto) {
/* 104 */     this.objetos.add(objeto);
/*     */   }
/*     */ 
/*     */   public void addRow(ResumenVentaTicket objeto, int pos) {
/* 108 */     this.objetos.add(pos, objeto);
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.model.ResumenVentaTicketTableModel
 * JD-Core Version:    0.6.2
 */