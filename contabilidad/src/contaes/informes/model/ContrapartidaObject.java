/*    */ package contaes.informes.model;
/*    */ 
/*    */ import contaes.manejoDatos.TipoSubcuenta;
/*    */ 
/*    */ public class ContrapartidaObject
/*    */ {
/*    */   TipoSubcuenta subcuenta;
/*    */   Double importe;
/*    */ 
/*    */   public ContrapartidaObject(TipoSubcuenta subcuenta, Double importe)
/*    */   {
/* 20 */     this.subcuenta = subcuenta;
/* 21 */     this.importe = importe;
/*    */   }
/*    */ 
/*    */   public Double getImporte() {
/* 25 */     return this.importe;
/*    */   }
/*    */ 
/*    */   public void setImporte(Double importe) {
/* 29 */     this.importe = importe;
/*    */   }
/*    */ 
/*    */   public TipoSubcuenta getSubcuenta() {
/* 33 */     return this.subcuenta;
/*    */   }
/*    */ 
/*    */   public void setSubcuenta(TipoSubcuenta subcuenta) {
/* 37 */     this.subcuenta = subcuenta;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.informes.model.ContrapartidaObject
 * JD-Core Version:    0.6.2
 */