/*    */ package contaes.auxiliarTablas;
/*    */ 
/*    */ import java.text.DecimalFormat;
/*    */ import java.text.DecimalFormatSymbols;
/*    */ 
/*    */ public class ImporteColorRenderer extends ColumnaColorRenderer
/*    */ {
/*    */   protected Object valor(Object value)
/*    */   {
/* 43 */     if ((value != null) && ((value instanceof Number))) {
/* 44 */       DecimalFormatSymbols formato = new DecimalFormatSymbols();
/* 45 */       formato.setDecimalSeparator(',');
/* 46 */       formato.setPerMill('.');
/* 47 */       DecimalFormat fn = new DecimalFormat("#,###,##0.00", formato);
/* 48 */       Number numberValue = (Number)value;
/* 49 */       value = fn.format(numberValue.doubleValue());
/*    */     }
/* 51 */     return value;
/*    */   }
/*    */ 
/*    */   protected int alineacion() {
/* 55 */     return 4;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.auxiliarTablas.ImporteColorRenderer
 * JD-Core Version:    0.6.2
 */