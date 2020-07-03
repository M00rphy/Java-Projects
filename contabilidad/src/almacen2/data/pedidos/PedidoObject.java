/*    */ package almacen2.data.pedidos;
/*    */ 
/*    */ public class PedidoObject
/*    */ {
/*    */   private Integer id;
/*    */   private String codigo;
/*    */   private String fecha;
/*    */   private int proveedor;
/*    */ 
/*    */   public PedidoObject(Integer id, String codigo, String fecha, int proveedor)
/*    */   {
/* 12 */     this.id = id;
/* 13 */     this.codigo = codigo;
/* 14 */     this.fecha = fecha;
/* 15 */     this.proveedor = proveedor;
/*    */   }
/*    */ 
/*    */   public Integer getId() {
/* 19 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Integer id) {
/* 23 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public String getCodigo() {
/* 27 */     return this.codigo;
/*    */   }
/*    */ 
/*    */   public String getFecha() {
/* 31 */     return this.fecha;
/*    */   }
/*    */ 
/*    */   public int getProveedor() {
/* 35 */     return this.proveedor;
/*    */   }
/*    */ 
/*    */   public String toString() {
/* 39 */     return getCodigo() + ": " + getFecha() + ", " + getProveedor();
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.data.pedidos.PedidoObject
 * JD-Core Version:    0.6.2
 */