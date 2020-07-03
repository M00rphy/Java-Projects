/*    */ package contaes.manejoDatos.auxiliar;
/*    */ 
/*    */ public class Registro
/*    */ {
/*    */   private static boolean registrado;
/*    */ 
/*    */   public static boolean isRegistrado()
/*    */   {
/* 17 */     ConfiguracionBean c = new ConfiguracionBean();
/* 18 */     String str = c.getConfigStr("<mouse>");
/* 19 */     if ((str != null) && (str.equals("1\n"))) {
/* 20 */       registrado = true;
/*    */     }
/*    */     else {
/* 23 */       registrado = false;
/*    */     }
/* 25 */     return registrado;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.auxiliar.Registro
 * JD-Core Version:    0.6.2
 */