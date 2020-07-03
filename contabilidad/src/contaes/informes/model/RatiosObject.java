/*    */ package contaes.informes.model;
/*    */ 
/*    */ public class RatiosObject
/*    */ {
/*    */   private String nombre;
/*    */   private Double actual;
/*    */   private Double anterior;
/*    */ 
/*    */   public RatiosObject(String nombre, Double actual, Double anterior)
/*    */   {
/* 19 */     this.nombre = nombre;
/* 20 */     this.actual = actual;
/* 21 */     this.anterior = anterior;
/*    */   }
/*    */ 
/*    */   public Double getActual() {
/* 25 */     return this.actual;
/*    */   }
/*    */ 
/*    */   public void setActual(Double actual) {
/* 29 */     this.actual = actual;
/*    */   }
/*    */ 
/*    */   public String getNombre() {
/* 33 */     return this.nombre;
/*    */   }
/*    */ 
/*    */   public void setNombre(String nombre) {
/* 37 */     this.nombre = nombre;
/*    */   }
/*    */ 
/*    */   public Double getAnterior() {
/* 41 */     return this.anterior;
/*    */   }
/*    */ 
/*    */   public void setAnterior(Double anterior) {
/* 45 */     this.anterior = anterior;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.informes.model.RatiosObject
 * JD-Core Version:    0.6.2
 */