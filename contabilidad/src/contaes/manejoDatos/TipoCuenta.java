/*    */ package contaes.manejoDatos;
/*    */ 
/*    */ import contaes.auxiliar.AlinearCadena;
/*    */ 
/*    */ public class TipoCuenta
/*    */ {
/*    */   private int codigo;
/*    */   private String nombre;
/*    */   private String gbalance;
/*    */ 
/*    */   public TipoCuenta(int codigo, String nombre, String gbalance)
/*    */   {
/* 12 */     this.codigo = codigo;
/* 13 */     this.nombre = nombre;
/* 14 */     this.gbalance = gbalance;
/*    */   }
/*    */ 
/*    */   public int getCodigo() {
/* 18 */     return this.codigo;
/*    */   }
/*    */ 
/*    */   public String getGbalance() {
/* 22 */     return this.gbalance;
/*    */   }
/*    */ 
/*    */   public String getNombre() {
/* 26 */     return this.nombre;
/*    */   }
/*    */ 
/*    */   public String toString() {
/* 30 */     return getCodigo() + ": " + new AlinearCadena().Izquierda(getNombre(), 70) + " " + getGbalance();
/*    */   }
/*    */ 
/*    */   public int getParent() {
/* 34 */     int p = getCodigo();
/* 35 */     String ps = String.valueOf(p);
/* 36 */     if (ps.length() > 1) {
/* 37 */       ps = ps.substring(0, ps.length() - 1);
/* 38 */       p = Integer.parseInt(ps);
/*    */     } else {
/* 40 */       p = 0;
/* 41 */     }return p;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.TipoCuenta
 * JD-Core Version:    0.6.2
 */