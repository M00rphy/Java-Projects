/*    */ package almacen2.data.pedidos;
/*    */ 
/*    */ public class datosProducto
/*    */ {
/*    */   String descripcion;
/*    */   double importe;
/*    */ 
/*    */   public datosProducto()
/*    */   {
/*    */   }
/*    */ 
/*    */   public datosProducto(String descripcion, double importe)
/*    */   {
/* 14 */     this.descripcion = descripcion;
/* 15 */     this.importe = importe;
/*    */   }
/*    */ 
/*    */   public String getDescripcion() {
/* 19 */     return this.descripcion;
/*    */   }
/*    */ 
/*    */   public void setDescripcion(String descripcion) {
/* 23 */     this.descripcion = descripcion;
/*    */   }
/*    */ 
/*    */   public double getImporte() {
/* 27 */     return this.importe;
/*    */   }
/*    */ 
/*    */   public void setImporte(double importe) {
/* 31 */     this.importe = importe;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.data.pedidos.datosProducto
 * JD-Core Version:    0.6.2
 */