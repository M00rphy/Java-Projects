/*    */ package contaes.informes.model;
/*    */ 
/*    */ public class DistribucionPartidasObject
/*    */ {
/*    */   private String nombre;
/*    */   private Double importe;
/*    */   private Double porcentaje;
/*    */ 
/*    */   public DistribucionPartidasObject(String nombre, Double importe, Double porcentaje)
/*    */   {
/* 19 */     this.nombre = nombre;
/* 20 */     this.importe = importe;
/* 21 */     this.porcentaje = porcentaje;
/*    */   }
/*    */ 
/*    */   public DistribucionPartidasObject(String nombre, Double importe) {
/* 25 */     this.nombre = nombre;
/* 26 */     this.importe = importe;
/*    */   }
/*    */ 
/*    */   public Double getImporte() {
/* 30 */     return this.importe;
/*    */   }
/*    */ 
/*    */   public void setImporte(Double importe) {
/* 34 */     this.importe = importe;
/*    */   }
/*    */ 
/*    */   public String getNombre() {
/* 38 */     return this.nombre;
/*    */   }
/*    */ 
/*    */   public void setNombre(String nombre) {
/* 42 */     this.nombre = nombre;
/*    */   }
/*    */ 
/*    */   public Double getPorcentaje() {
/* 46 */     return this.porcentaje;
/*    */   }
/*    */ 
/*    */   public void setPorcentaje(Double porcentaje) {
/* 50 */     this.porcentaje = porcentaje;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.informes.model.DistribucionPartidasObject
 * JD-Core Version:    0.6.2
 */