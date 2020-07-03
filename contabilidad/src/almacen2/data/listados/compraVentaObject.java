/*    */ package almacen2.data.listados;
/*    */ 
/*    */ public class compraVentaObject
/*    */ {
/*    */   private String fecha;
/*    */   private String referencia;
/*    */   private String descripcion;
/*    */   private int es;
/*    */   private double importe;
/*    */   private double pLista;
/*    */ 
/*    */   public compraVentaObject(String fecha, String referencia, String descripcion, int es, double importe, double pLista)
/*    */   {
/* 15 */     this.fecha = fecha;
/* 16 */     this.referencia = referencia;
/* 17 */     this.descripcion = descripcion;
/* 18 */     this.es = es;
/* 19 */     this.importe = importe;
/* 20 */     this.pLista = pLista;
/*    */   }
/*    */ 
/*    */   public String getFecha() {
/* 24 */     return this.fecha;
/*    */   }
/*    */ 
/*    */   public String getReferencia() {
/* 28 */     return this.referencia;
/*    */   }
/*    */ 
/*    */   public String getDescripcion() {
/* 32 */     return this.descripcion;
/*    */   }
/*    */ 
/*    */   public int getEs() {
/* 36 */     return this.es;
/*    */   }
/*    */ 
/*    */   public double getImporte() {
/* 40 */     return this.importe;
/*    */   }
/*    */ 
/*    */   public double getPLista() {
/* 44 */     return this.pLista;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.data.listados.compraVentaObject
 * JD-Core Version:    0.6.2
 */