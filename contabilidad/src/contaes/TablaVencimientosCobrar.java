/*    */ package contaes;
/*    */ 
/*    */ import internationalization.Mensajes;
/*    */ 
/*    */ public class TablaVencimientosCobrar extends TablaVencimientos
/*    */ {
/*    */   public TablaVencimientosCobrar()
/*    */   {
/* 32 */     super(Mensajes.getString("menu35a"));
/*    */   }
/*    */ 
/*    */   protected boolean pagar()
/*    */   {
/* 39 */     return false;
/*    */   }
/*    */ 
/*    */   protected String sAcreedor()
/*    */   {
/* 46 */     return Mensajes.getString("deudor");
/*    */   }
/*    */ 
/*    */   protected String sCuentaAbono()
/*    */   {
/* 53 */     return Mensajes.getString("cuentaCargo");
/*    */   }
/*    */ 
/*    */   protected String sPagado()
/*    */   {
/* 60 */     return Mensajes.getString("cobrado");
/*    */   }
/*    */ 
/*    */   protected String sPagar()
/*    */   {
/* 67 */     return Mensajes.getString("cobrar");
/*    */   }
/*    */ 
/*    */   protected String icono()
/*    */   {
/* 74 */     return "/contaes/iconos/Vc18.png";
/*    */   }
/*    */ 
/*    */   protected String marca()
/*    */   {
/* 81 */     return "Q";
/*    */   }
/*    */ 
/*    */   protected String sPago()
/*    */   {
/* 88 */     return Mensajes.getString("cobro");
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.TablaVencimientosCobrar
 * JD-Core Version:    0.6.2
 */