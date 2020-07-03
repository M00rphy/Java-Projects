/*    */ package contaes.manejoDatos;
/*    */ 
/*    */ public class TipoInformeProductos
/*    */ {
/*    */   private String referencia;
/*    */   private String descripcion;
/*    */   private int unidades;
/*    */   private double coste;
/*    */   private double pvp;
/*    */ 
/*    */   public TipoInformeProductos(String referencia, String descripcion, int unidades, double coste, double pvp)
/*    */   {
/* 21 */     this.referencia = referencia;
/* 22 */     this.descripcion = descripcion;
/* 23 */     this.unidades = unidades;
/* 24 */     this.coste = coste;
/* 25 */     this.pvp = pvp;
/*    */   }
/*    */ 
/*    */   public double getCoste() {
/* 29 */     return this.coste;
/*    */   }
/*    */ 
/*    */   public String getDescripcion() {
/* 33 */     return this.descripcion;
/*    */   }
/*    */ 
/*    */   public double getPvp() {
/* 37 */     return this.pvp;
/*    */   }
/*    */ 
/*    */   public String getReferencia() {
/* 41 */     return this.referencia;
/*    */   }
/*    */ 
/*    */   public int getUnidades() {
/* 45 */     return this.unidades;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.TipoInformeProductos
 * JD-Core Version:    0.6.2
 */