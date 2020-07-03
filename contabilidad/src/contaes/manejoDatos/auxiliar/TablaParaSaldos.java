/*    */ package contaes.manejoDatos.auxiliar;
/*    */ 
/*    */ public class TablaParaSaldos
/*    */ {
/*    */   private int cuenta;
/*    */   private String DH;
/*    */   private double importe;
/*    */ 
/*    */   public TablaParaSaldos()
/*    */   {
/* 33 */     this.cuenta = 0;
/* 34 */     this.DH = "";
/* 35 */     this.importe = 0.0D;
/*    */   }
/*    */ 
/*    */   public TablaParaSaldos(int a, String b, double c) {
/* 39 */     this.cuenta = a;
/* 40 */     this.DH = b;
/* 41 */     this.importe = c;
/*    */   }
/*    */ 
/*    */   public void setCuenta(int a) {
/* 45 */     this.cuenta = a;
/*    */   }
/*    */   public void setDH(String a) {
/* 48 */     this.DH = a;
/*    */   }
/*    */   public void setImporte(double a) {
/* 51 */     this.importe = a;
/*    */   }
/*    */ 
/*    */   public int getCuenta() {
/* 55 */     return this.cuenta;
/*    */   }
/*    */   public String getDH() {
/* 58 */     return this.DH;
/*    */   }
/*    */   public double getImporte() {
/* 61 */     return this.importe;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.auxiliar.TablaParaSaldos
 * JD-Core Version:    0.6.2
 */