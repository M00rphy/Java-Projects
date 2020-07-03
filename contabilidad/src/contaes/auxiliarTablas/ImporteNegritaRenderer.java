/*    */ package contaes.auxiliarTablas;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Font;
/*    */ import java.text.DecimalFormat;
/*    */ import java.text.DecimalFormatSymbols;
/*    */ import javax.swing.table.DefaultTableCellRenderer;
/*    */ 
/*    */ public class ImporteNegritaRenderer extends DefaultTableCellRenderer
/*    */ {
/*    */   public ImporteNegritaRenderer()
/*    */   {
/* 41 */     setBackground(Color.WHITE);
/*    */   }
/*    */ 
/*    */   public void setValue(Object value)
/*    */   {
/* 46 */     setFont(getFont().deriveFont(1));
/* 47 */     if ((value != null) && ((value instanceof Number))) {
/* 48 */       setHorizontalAlignment(4);
/* 49 */       DecimalFormatSymbols formato = new DecimalFormatSymbols();
/* 50 */       formato.setDecimalSeparator(',');
/* 51 */       formato.setPerMill('.');
/* 52 */       DecimalFormat fn = new DecimalFormat("#,###,##0.00", formato);
/* 53 */       Number numberValue = (Number)value;
/* 54 */       value = fn.format(numberValue.doubleValue());
/*    */     }
/* 56 */     super.setValue(value);
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.auxiliarTablas.ImporteNegritaRenderer
 * JD-Core Version:    0.6.2
 */