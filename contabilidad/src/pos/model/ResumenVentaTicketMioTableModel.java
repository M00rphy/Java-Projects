/*     */ package pos.model;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.table.AbstractTableModel;
/*     */ 
/*     */ public class ResumenVentaTicketMioTableModel extends AbstractTableModel
/*     */ {
/*     */   private ArrayList<ResumenVentaTicketMio> objetos;
/*     */ 
/*     */   public ResumenVentaTicketMioTableModel(ArrayList<ResumenVentaTicketMio> lineas)
/*     */   {
/*  19 */     this.objetos = lineas;
/*     */   }
/*     */ 
/*     */   public ResumenVentaTicketMioTableModel() {
/*  23 */     this.objetos = new ArrayList();
/*     */   }
/*     */ 
/*     */   public int getRowCount()
/*     */   {
/*  28 */     return this.objetos.size();
/*     */   }
/*     */ 
/*     */   public int getColumnCount()
/*     */   {
/*  33 */     return 10;
/*     */   }
/*     */ 
/*     */   public Object getValueAt(int rowIndex, int columnIndex)
/*     */   {
/*  38 */     Object valor = null;
/*  39 */     ResumenVentaTicketMio objeto = (ResumenVentaTicketMio)this.objetos.get(rowIndex);
/*  40 */     if (columnIndex == 0) {
/*  41 */       if (objeto.getTicket() != -1)
/*  42 */         valor = Integer.valueOf(objeto.getTicket());
/*     */       else
/*  44 */         valor = "";
/*  45 */     } else if (columnIndex == 1) {
/*  46 */       if (objeto.getFecha() != null)
/*  47 */         valor = objeto.getFecha();
/*     */       else
/*  49 */         valor = "";
/*  50 */     } else if (columnIndex == 2)
/*  51 */       valor = objeto.getDescripcion();
/*  52 */     else if (columnIndex == 3)
/*  53 */       valor = Integer.valueOf(objeto.getUnidades());
/*  54 */     else if (columnIndex == 4)
/*  55 */       valor = Double.valueOf(objeto.getImporte());
/*  56 */     else if (columnIndex == 5)
/*  57 */       valor = Double.valueOf(objeto.getTotal());
/*  58 */     else if (columnIndex == 6) {
/*  59 */       if (objeto.getPlazos() != -1)
/*  60 */         valor = Integer.valueOf(objeto.getPlazos());
/*     */       else
/*  62 */         valor = "";
/*  63 */     } else if (columnIndex == 7) {
/*  64 */       if (objeto.getMedioPago() != null)
/*  65 */         valor = objeto.getMedioPago();
/*     */       else
/*  67 */         valor = "";
/*  68 */     } else if (columnIndex == 8) {
/*  69 */       if (objeto.isCerrado() == 0) {
/*  70 */         valor = Boolean.valueOf(false);
/*     */       }
/*     */       else
/*  73 */         valor = Boolean.valueOf(true);
/*     */     }
/*  75 */     else if (columnIndex == 9)
/*  76 */       valor = objeto.getNota();
/*     */     else {
/*  78 */       throw new IndexOutOfBoundsException();
/*     */     }
/*  80 */     return valor;
/*     */   }
/*     */ 
/*     */   public ResumenVentaTicketMio getObjectAt(int rowIndex) {
/*  84 */     ResumenVentaTicketMio objeto = (ResumenVentaTicketMio)this.objetos.get(rowIndex);
/*  85 */     return objeto;
/*     */   }
/*     */ 
/*     */   public ArrayList<ResumenVentaTicketMio> getObjetos() {
/*  89 */     return this.objetos;
/*     */   }
/*     */ 
/*     */   public String getColumnName(int columnIndex)
/*     */   {
/*     */     String columnName;
/*  96 */     if (columnIndex == 0) {
/*  97 */       columnName = "Ticket";
/*     */     }
/*     */     else
/*     */     {
/*     */     
/*  98 */       if (columnIndex == 1) {
/*  99 */         columnName = "Fecha";
/*     */       }
/*     */       else
/*     */       {
/*     */     
/* 100 */         if (columnIndex == 2) {
/* 101 */           columnName = "Descripción";
/*     */         }
/*     */         else
/*     */         {
/*     */          
/* 102 */           if (columnIndex == 3) {
/* 103 */             columnName = "Unidades";
/*     */           }
/*     */           else
/*     */           {
/*     */          
/* 104 */             if (columnIndex == 4) {
/* 105 */               columnName = "Precio";
/*     */             }
/*     */             else
/*     */             {
/*     */             
/* 106 */               if (columnIndex == 5) {
/* 107 */                 columnName = "Total";
/*     */               }
/*     */               else
/*     */               {
/*     */               
/* 108 */                 if (columnIndex == 6) {
/* 109 */                   columnName = "Pl";
/*     */                 }
/*     */                 else
/*     */                 {
/*     */                  
/* 110 */                   if (columnIndex == 7) {
/* 111 */                     columnName = "Medio P.";
/*     */                   }
/*     */                   else
/*     */                   {
/*     */                 
/* 112 */                     if (columnIndex == 8) {
/* 113 */                       columnName = "Cl";
/*     */                     }
/*     */                     else
/*     */                     {
/*     */                       
/* 114 */                       if (columnIndex == 9)
/* 115 */                         columnName = "Nota";
/*     */                       else
/* 117 */                         throw new IndexOutOfBoundsException();
/*     */                     }
/*     */                   }
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */    
/* 119 */     return columnName;
/*     */   }
/*     */ 
/*     */   public void removeRow(int row) {
/* 123 */     this.objetos.remove(row);
/*     */   }
/*     */ 
/*     */   public void addRow(ResumenVentaTicketMio objeto) {
/* 127 */     this.objetos.add(objeto);
/*     */   }
/*     */ 
/*     */   public void addRow(ResumenVentaTicketMio objeto, int pos) {
/* 131 */     this.objetos.add(pos, objeto);
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.model.ResumenVentaTicketMioTableModel
 * JD-Core Version:    0.6.2
 */