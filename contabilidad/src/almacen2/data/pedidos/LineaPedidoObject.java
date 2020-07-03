/*    */ package almacen2.data.pedidos;
/*    */ 
/*    */ public class LineaPedidoObject
/*    */ {
/*    */   private int id;
/*    */   private String referencia;
/*    */   private String descripcion;
/*    */   private int unidades;
/*    */   private double coste;
/*    */   private boolean recibido;
/*    */ 
/*    */   public LineaPedidoObject(int id, String referencia, String descripcion, int unidades, double coste, boolean recibido)
/*    */   {
/* 16 */     this.id = id;
/* 17 */     this.referencia = referencia;
/* 18 */     this.descripcion = descripcion;
/* 19 */     this.coste = coste;
/* 20 */     this.recibido = recibido;
/* 21 */     this.unidades = unidades;
/*    */   }
/*    */ 
/*    */   public int getId() {
/* 25 */     return this.id;
/*    */   }
/*    */ 
/*    */   public String getReferencia() {
/* 29 */     return this.referencia;
/*    */   }
/*    */ 
/*    */   public String getDescripcion() {
/* 33 */     return this.descripcion;
/*    */   }
/*    */ 
/*    */   public double getCoste() {
/* 37 */     return this.coste;
/*    */   }
/*    */ 
/*    */   public boolean isRecibido() {
/* 41 */     return this.recibido;
/*    */   }
/*    */ 
/*    */   public void setRecibido(boolean recibido) {
/* 45 */     this.recibido = recibido;
/*    */   }
/*    */ 
/*    */   public int getUnidades() {
/* 49 */     return this.unidades;
/*    */   }
/*    */ 
/*    */   public void setUnidades(int unidades) {
/* 53 */     this.unidades = unidades;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 58 */     return this.id + ": " + this.referencia + ", " + this.descripcion + ", " + this.coste + ", " + this.recibido;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.data.pedidos.LineaPedidoObject
 * JD-Core Version:    0.6.2
 */