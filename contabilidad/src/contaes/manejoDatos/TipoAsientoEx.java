/*    */ package contaes.manejoDatos;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class TipoAsientoEx extends TipoAsiento
/*    */ {
/* 16 */   ArrayList<TipoApunte> apuntes = new ArrayList();
/* 17 */   TipoFacturaExtended factura = new TipoFacturaExtended();
/*    */ 
/*    */   public TipoAsientoEx(int id_asiento, int numero, String fecha, String documento, String marca, ArrayList<TipoApunte> apuntes, TipoFacturaExtended factura)
/*    */   {
/* 21 */     super(id_asiento, numero, fecha, documento, marca);
/* 22 */     this.apuntes = apuntes;
/* 23 */     this.factura = factura;
/*    */   }
/*    */ 
/*    */   public TipoAsientoEx() {
/*    */   }
/*    */ 
/*    */   public ArrayList<TipoApunte> getApuntes() {
/* 30 */     return this.apuntes;
/*    */   }
/*    */ 
/*    */   public void setApuntes(ArrayList<TipoApunte> apuntes) {
/* 34 */     this.apuntes = apuntes;
/*    */   }
/*    */ 
/*    */   public TipoFacturaExtended getFactura() {
/* 38 */     return this.factura;
/*    */   }
/*    */ 
/*    */   public void setFactura(TipoFacturaExtended factura) {
/* 42 */     this.factura = factura;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 47 */     return this.id_asiento + " - " + this.numero + " - " + this.fecha;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.TipoAsientoEx
 * JD-Core Version:    0.6.2
 */