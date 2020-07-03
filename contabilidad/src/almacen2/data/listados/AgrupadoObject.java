/*    */ package almacen2.data.listados;
/*    */ 
/*    */ public class AgrupadoObject
/*    */ {
/*    */   private String nombre;
/*    */   private Double unidadesCompra;
/*    */   private Double totalCompra;
/*    */   private Double unidadesVenta;
/*    */   private Double totalVenta;
/*    */ 
/*    */   public AgrupadoObject(String nombre, Double unidadesCompra, Double totalCompra, Double unidadesVenta, Double totalVenta)
/*    */   {
/* 21 */     this.nombre = nombre;
/* 22 */     this.unidadesCompra = unidadesCompra;
/* 23 */     this.totalCompra = totalCompra;
/* 24 */     this.unidadesVenta = unidadesVenta;
/* 25 */     this.totalVenta = totalVenta;
/*    */   }
/*    */ 
/*    */   public String getNombre() {
/* 29 */     return this.nombre;
/*    */   }
/*    */ 
/*    */   public void setNombre(String nombre) {
/* 33 */     this.nombre = nombre;
/*    */   }
/*    */ 
/*    */   public Double getTotalCompra() {
/* 37 */     return this.totalCompra;
/*    */   }
/*    */ 
/*    */   public void setTotalCompra(Double totalCompra) {
/* 41 */     this.totalCompra = totalCompra;
/*    */   }
/*    */ 
/*    */   public Double getTotalVenta() {
/* 45 */     return this.totalVenta;
/*    */   }
/*    */ 
/*    */   public void setTotalVenta(Double totalVenta) {
/* 49 */     this.totalVenta = totalVenta;
/*    */   }
/*    */ 
/*    */   public Double getUnidadesCompra() {
/* 53 */     return this.unidadesCompra;
/*    */   }
/*    */ 
/*    */   public void setUnidadesCompra(Double unidadesCompra) {
/* 57 */     this.unidadesCompra = unidadesCompra;
/*    */   }
/*    */ 
/*    */   public Double getUnidadesVenta() {
/* 61 */     return this.unidadesVenta;
/*    */   }
/*    */ 
/*    */   public void setUnidadesVenta(Double unidadesVenta) {
/* 65 */     this.unidadesVenta = unidadesVenta;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.data.listados.AgrupadoObject
 * JD-Core Version:    0.6.2
 */