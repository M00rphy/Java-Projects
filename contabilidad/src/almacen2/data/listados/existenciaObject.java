/*    */ package almacen2.data.listados;
/*    */ 
/*    */ public class existenciaObject
/*    */ {
/*    */   private String referencia;
/*    */   private String descripcion;
/*    */   private int unidades;
/*    */   private double coste;
/*    */ 
/*    */   public existenciaObject(String referencia, String descripcion, int unidades, double coste)
/*    */   {
/* 13 */     this.referencia = referencia;
/* 14 */     this.descripcion = descripcion;
/* 15 */     this.unidades = unidades;
/* 16 */     this.coste = coste;
/*    */   }
/*    */ 
/*    */   public String getReferencia() {
/* 20 */     return this.referencia;
/*    */   }
/*    */ 
/*    */   public String getDescripcion() {
/* 24 */     return this.descripcion;
/*    */   }
/*    */ 
/*    */   public int getUnidades() {
/* 28 */     return this.unidades;
/*    */   }
/*    */ 
/*    */   public double getCoste() {
/* 32 */     return this.coste;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.data.listados.existenciaObject
 * JD-Core Version:    0.6.2
 */