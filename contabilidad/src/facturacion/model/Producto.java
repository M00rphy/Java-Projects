/*    */ package facturacion.model;
/*    */ 
/*    */ import contaes.manejoDatos.TipoSubcuenta;
/*    */ 
/*    */ public class Producto
/*    */ {
/*    */   private Integer id;
/*    */   private String referencia;
/*    */   private String descripcion;
/*    */   private TipoSubcuenta subcuenta;
/*    */   private Double precio;
/*    */ 
/*    */   public Producto(Integer id, String referencia, String descripcion, TipoSubcuenta subcuenta, Double precio)
/*    */   {
/* 23 */     this.id = id;
/* 24 */     this.referencia = referencia;
/* 25 */     this.descripcion = descripcion;
/* 26 */     this.subcuenta = subcuenta;
/* 27 */     this.precio = precio;
/*    */   }
/*    */ 
/*    */   public String getReferencia() {
/* 31 */     return this.referencia;
/*    */   }
/*    */ 
/*    */   public void setReferencia(String referencia) {
/* 35 */     this.referencia = referencia;
/*    */   }
/*    */ 
/*    */   public String getDescripcion() {
/* 39 */     return this.descripcion;
/*    */   }
/*    */ 
/*    */   public void setDescripcion(String descripcion) {
/* 43 */     this.descripcion = descripcion;
/*    */   }
/*    */ 
/*    */   public Integer getId() {
/* 47 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Integer id) {
/* 51 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public TipoSubcuenta getSubcuenta() {
/* 55 */     return this.subcuenta;
/*    */   }
/*    */ 
/*    */   public void setSubcuenta(TipoSubcuenta subcuenta) {
/* 59 */     this.subcuenta = subcuenta;
/*    */   }
/*    */ 
/*    */   public Double getPrecio() {
/* 63 */     return this.precio;
/*    */   }
/*    */ 
/*    */   public void setPrecio(Double precio) {
/* 67 */     this.precio = precio;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 72 */     return this.descripcion;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     facturacion.model.Producto
 * JD-Core Version:    0.6.2
 */