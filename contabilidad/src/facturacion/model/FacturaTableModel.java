/*    */ package facturacion.model;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import javax.swing.table.AbstractTableModel;
/*    */ 
/*    */ public class FacturaTableModel extends AbstractTableModel
/*    */ {
/*    */   private ArrayList<Factura> objetos;
/*    */ 
/*    */   public FacturaTableModel(ArrayList<Factura> objetos)
/*    */   {
/* 20 */     this.objetos = objetos;
/*    */   }
/*    */ 
/*    */   public int getRowCount() {
/* 24 */     return this.objetos.size();
/*    */   }
/*    */ 
/*    */   public int getColumnCount() {
/* 28 */     return 10;
/*    */   }
/*    */ 
/*    */   public Object getValueAt(int rowIndex, int columnIndex) {
/* 32 */     Object valor = null;
/* 33 */     Factura objeto = (Factura)this.objetos.get(rowIndex);
/* 34 */     if (columnIndex == 0)
/* 35 */       valor = objeto.getId();
/* 36 */     else if (columnIndex == 1)
/* 37 */       valor = objeto.getNumero();
/* 38 */     else if (columnIndex == 2)
/* 39 */       valor = objeto.getCliente();
/* 40 */     else if (columnIndex == 3)
/* 41 */       valor = objeto.getFecha();
/* 42 */     else if (columnIndex == 4)
/* 43 */       valor = objeto.getRetencion();
/* 44 */     else if (columnIndex == 5)
/* 45 */       valor = Boolean.valueOf(objeto.isRecargo());
/* 46 */     else if (columnIndex == 6)
/* 47 */       valor = objeto.getFormaPago();
/* 48 */     else if (columnIndex == 7)
/* 49 */       valor = objeto.getBase();
/* 50 */     else if (columnIndex == 8)
/* 51 */       valor = objeto.getIva();
/* 52 */     else if (columnIndex == 9)
/* 53 */       valor = objeto.getTotal();
/*    */     else {
/* 55 */       throw new IndexOutOfBoundsException();
/*    */     }
/* 57 */     return valor;
/*    */   }
/*    */ 
/*    */   public String getColumnName(int columnIndex)
/*    */   {
/*    */     String columnName;
/* 64 */     if (columnIndex == 0) {
/* 65 */       columnName = "id";
/*    */     }
/*    */     else
/*    */     {
/*    */       
/* 66 */       if (columnIndex == 1) {
/* 67 */         columnName = "Número";
/*    */       }
/*    */       else
/*    */       {
/*    */       
/* 68 */         if (columnIndex == 2) {
/* 69 */           columnName = "Cliente";
/*    */         }
/*    */         else
/*    */         {
/*    */        
/* 70 */           if (columnIndex == 3) {
/* 71 */             columnName = "Fecha";
/*    */           }
/*    */           else
/*    */           {
/*    */             
/* 72 */             if (columnIndex == 1) {
/* 73 */               columnName = "Retención";
/*    */             }
/*    */             else
/*    */             {
/*    */              
/* 74 */               if (columnIndex == 2) {
/* 75 */                 columnName = "Recargo";
/*    */               }
/*    */               else
/*    */               {
/*    */              
/* 76 */                 if (columnIndex == 3) {
/* 77 */                   columnName = "Forma Pago";
/*    */                 }
/*    */                 else
/*    */                 {
/*    */                
/* 78 */                   if (columnIndex == 1) {
/* 79 */                     columnName = "Base";
/*    */                   }
/*    */                   else
/*    */                   {
/*    */                     
/* 80 */                     if (columnIndex == 2) {
/* 81 */                       columnName = "Iva";
/*    */                     }
/*    */                     else
/*    */                     {
/*    */                     
/* 82 */                       if (columnIndex == 3)
/* 83 */                         columnName = "Total";
/*    */                       else
/* 85 */                         throw new IndexOutOfBoundsException();
/*    */                     }
/*    */                   }
/*    */                 }
/*    */               }
/*    */             }
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */    
/* 87 */     return columnName;
/*    */   }
/*    */ 
/*    */   public void removeRow(int row) {
/* 91 */     this.objetos.remove(row);
/*    */   }
/*    */ 
/*    */   public void addRow(Factura objeto) {
/* 95 */     this.objetos.add(objeto);
/*    */   }
/*    */ 
/*    */   public void addRow(Factura objeto, int pos) {
/* 99 */     this.objetos.add(pos, objeto);
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     facturacion.model.FacturaTableModel
 * JD-Core Version:    0.6.2
 */