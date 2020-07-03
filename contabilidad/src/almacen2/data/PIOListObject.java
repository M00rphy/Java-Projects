/*    */ package almacen2.data;
/*    */ 
/*    */ public class PIOListObject
/*    */ {
/*    */   private String fecha;
/*    */   private String unidades;
/*    */   private double importe;
/*    */   private int almacen;
/*    */ 
/*    */   public PIOListObject(String fecha, String unidades, double importe, int almacen)
/*    */   {
/* 12 */     this.fecha = fecha;
/* 13 */     this.unidades = unidades;
/* 14 */     this.importe = importe;
/* 15 */     this.almacen = almacen;
/*    */   }
/*    */ 
/*    */   public String getFecha() {
/* 19 */     return this.fecha;
/*    */   }
/*    */ 
/*    */   public String getUnidades() {
/* 23 */     return this.unidades;
/*    */   }
/*    */ 
/*    */   public Double getImporte() {
/* 27 */     return Double.valueOf(this.importe);
/*    */   }
/*    */ 
/*    */   public int getAlmacen() {
/* 31 */     return this.almacen;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.data.PIOListObject
 * JD-Core Version:    0.6.2
 */