/*    */ package facturacion.model;
/*    */ 
/*    */ import contaes.manejoDatos.TipoFormaPago;
/*    */ import contaes.manejoDatos.TipoSubcuenta;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class FacturaTotal extends Factura
/*    */ {
/*    */   private ArrayList<LineaFactura> lineas;
/*    */ 
/*    */   public FacturaTotal(Integer id, String numero, TipoSubcuenta cliente, Date fecha, Double retencion, boolean recargo, TipoFormaPago formaPago, Double base, Double iva, boolean contabilizada, boolean isAlmacenada, ArrayList<LineaFactura> lineas)
/*    */   {
/* 22 */     super(id, numero, cliente, fecha, retencion, recargo, formaPago, base, iva, contabilizada, isAlmacenada);
/* 23 */     this.lineas = lineas;
/*    */   }
/*    */ 
/*    */   public ArrayList<LineaFactura> getLineas() {
/* 27 */     return this.lineas;
/*    */   }
/*    */ 
/*    */   public void setLineas(ArrayList<LineaFactura> lineas) {
/* 31 */     this.lineas = lineas;
/*    */   }
/*    */ 
/*    */   public Factura getFacturaSimple() {
/* 35 */     Factura factura = new Factura(getId(), getNumero(), getCliente(), getFecha(), getRetencion(), isRecargo(), getFormaPago(), getBase(), getIva(), isContabilizada(), isIsAlmacenada());
/*    */ 
/* 38 */     return factura;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     facturacion.model.FacturaTotal
 * JD-Core Version:    0.6.2
 */