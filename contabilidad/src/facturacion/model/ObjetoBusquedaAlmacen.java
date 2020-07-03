/*    */ package facturacion.model;
/*    */ 
/*    */ public class ObjetoBusquedaAlmacen
/*    */ {
/*    */   private String referencia;
/*    */   private String familia;
/*    */   private String proveedor;
/*    */   private String descripcion;
/*    */   private double coste;
/*    */   private double precio;
/*    */   private int stock;
/*    */ 
/*    */   public ObjetoBusquedaAlmacen(String referencia, String familia, String proveedor, String descripcion, double coste, double precio, int stock)
/*    */   {
/* 16 */     this.referencia = referencia;
/* 17 */     this.familia = familia;
/* 18 */     this.proveedor = proveedor;
/* 19 */     this.descripcion = descripcion;
/* 20 */     this.coste = coste;
/* 21 */     this.precio = precio;
/* 22 */     this.stock = stock;
/*    */   }
/*    */ 
/*    */   public String getReferencia() {
/* 26 */     return this.referencia;
/*    */   }
/*    */ 
/*    */   public String getFamilia() {
/* 30 */     return this.familia;
/*    */   }
/*    */ 
/*    */   public String getProveedor() {
/* 34 */     return this.proveedor;
/*    */   }
/*    */ 
/*    */   public String getDescripcion() {
/* 38 */     return this.descripcion;
/*    */   }
/*    */ 
/*    */   public double getCoste() {
/* 42 */     return this.coste;
/*    */   }
/*    */ 
/*    */   public double getPrecio() {
/* 46 */     return this.precio;
/*    */   }
/*    */ 
/*    */   public int getStock() {
/* 50 */     return this.stock;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     facturacion.model.ObjetoBusquedaAlmacen
 * JD-Core Version:    0.6.2
 */