/*    */ package contaes.manejoDatos.auxiliar;
/*    */ 
/*    */ import java.util.Properties;
/*    */ 
/*    */ public class FinLinea
/*    */ {
/*    */   public static String get()
/*    */   {
/*  8 */     Properties sistema = System.getProperties();
/*  9 */     String sisOp = sistema.getProperty("os.name").substring(0, 3);
/* 10 */     String FinLinea = sisOp.equals("Win") ? "\r\n" : "\n";
/* 11 */     return FinLinea;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.auxiliar.FinLinea
 * JD-Core Version:    0.6.2
 */