/*    */ package contaes.manejoDatos;
/*    */ 
/*    */ public class TipoFacturaExtended extends TipoFactura
/*    */ {
/*    */   private int importacion;
/*    */ 
/*    */   public TipoFacturaExtended(String numero, String fecha, int cuenta, int id_asiento, String concepto, double base, double iva, double total, int importacion)
/*    */   {
/* 17 */     super(numero, fecha, cuenta, id_asiento, concepto, base, iva, 0.0D, total);
/* 18 */     this.importacion = importacion;
/*    */   }
/*    */ 
/*    */   public TipoFacturaExtended()
/*    */   {
/*    */   }
/*    */ 
/*    */   public int getImportacion() {
/* 26 */     return this.importacion;
/*    */   }
/*    */ 
/*    */   public void setImportacion(int importacion) {
/* 30 */     this.importacion = importacion;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.TipoFacturaExtended
 * JD-Core Version:    0.6.2
 */