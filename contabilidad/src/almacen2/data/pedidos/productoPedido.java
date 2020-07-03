/*    */ package almacen2.data.pedidos;
/*    */ 
/*    */ public class productoPedido
/*    */ {
/*    */   int id;
/*    */   String referencia;
/*    */   Double importe;
/*    */   boolean recibido;
/*    */   int unidades;
/*    */ 
/*    */   public productoPedido()
/*    */   {
/*    */   }
/*    */ 
/*    */   public productoPedido(int id, String referencia, Double importe, boolean recibido, int unidades)
/*    */   {
/* 17 */     this.id = id;
/* 18 */     this.referencia = referencia;
/* 19 */     this.importe = importe;
/* 20 */     this.recibido = recibido;
/* 21 */     this.unidades = unidades;
/*    */   }
/*    */ 
/*    */   public int getId() {
/* 25 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(int id) {
/* 29 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public String getReferencia() {
/* 33 */     return this.referencia;
/*    */   }
/*    */ 
/*    */   public void setReferencia(String referencia) {
/* 37 */     this.referencia = referencia;
/*    */   }
/*    */ 
/*    */   public Double getImporte() {
/* 41 */     return this.importe;
/*    */   }
/*    */ 
/*    */   public void setImporte(Double importe) {
/* 45 */     this.importe = importe;
/*    */   }
/*    */ 
/*    */   public boolean isRecibido() {
/* 49 */     return this.recibido;
/*    */   }
/*    */ 
/*    */   public void setRecibido(boolean recibido) {
/* 53 */     this.recibido = recibido;
/*    */   }
/*    */ 
/*    */   public int getUnidades() {
/* 57 */     return this.unidades;
/*    */   }
/*    */ 
/*    */   public void setUnidades(int unidades) {
/* 61 */     this.unidades = unidades;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.data.pedidos.productoPedido
 * JD-Core Version:    0.6.2
 */