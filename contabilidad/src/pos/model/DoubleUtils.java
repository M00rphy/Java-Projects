/*    */ package pos.model;
/*    */ 
/*    */ public class DoubleUtils
/*    */ {
/*    */   public static double fixDecimals(Number value)
/*    */   {
/* 15 */     return Math.rint(value.doubleValue() * 1000000.0D) / 1000000.0D;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.model.DoubleUtils
 * JD-Core Version:    0.6.2
 */