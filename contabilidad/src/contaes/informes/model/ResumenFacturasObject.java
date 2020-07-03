/*    */ package contaes.informes.model;
/*    */ 
/*    */ public class ResumenFacturasObject
/*    */ {
/*    */   private String mes;
/*    */   private String nombre;
/*    */   private Double importe;
/*    */ 
/*    */   public ResumenFacturasObject(String mes, String nombre, Double importe)
/*    */   {
/* 19 */     this.mes = mes;
/* 20 */     this.nombre = nombre;
/* 21 */     this.importe = importe;
/*    */   }
/*    */ 
/*    */   public Double getImporte() {
/* 25 */     return this.importe;
/*    */   }
/*    */ 
/*    */   public void setImporte(Double importe) {
/* 29 */     this.importe = importe;
/*    */   }
/*    */ 
/*    */   public String getMes() {
/* 33 */     return this.mes;
/*    */   }
/*    */ 
/*    */   public void setMes(String mes) {
/* 37 */     this.mes = mes;
/*    */   }
/*    */ 
/*    */   public String getNombre() {
/* 41 */     return this.nombre;
/*    */   }
/*    */ 
/*    */   public void setNombre(String nombre) {
/* 45 */     this.nombre = nombre;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.informes.model.ResumenFacturasObject
 * JD-Core Version:    0.6.2
 */