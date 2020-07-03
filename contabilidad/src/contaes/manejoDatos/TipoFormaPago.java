/*     */ package contaes.manejoDatos;
/*     */ 
/*     */ public class TipoFormaPago
/*     */ {
/*     */   private Integer diaFijoPago;
/*     */   private Integer diasEntrePagos;
/*     */   private Integer diasPrimerPago;
/*     */   private Integer numeroPagos;
/*     */   private String pago;
/*     */   private Integer idFormaPago;
/*     */ 
/*     */   public TipoFormaPago()
/*     */   {
/*     */   }
/*     */ 
/*     */   public TipoFormaPago(Integer idFormaPago, String pago, Integer diaFijoPago, Integer diasEntrePagos, Integer diasPrimerPago, Integer numeroPagos)
/*     */   {
/*  26 */     this.diaFijoPago = diaFijoPago;
/*  27 */     this.diasEntrePagos = diasEntrePagos;
/*  28 */     this.diasPrimerPago = diasPrimerPago;
/*  29 */     this.idFormaPago = idFormaPago;
/*  30 */     this.numeroPagos = numeroPagos;
/*  31 */     this.pago = pago;
/*     */   }
/*     */ 
/*     */   public Integer getDiaFijoPago() {
/*  35 */     return this.diaFijoPago;
/*     */   }
/*     */ 
/*     */   public void setDiaFijoPago(Integer diaFijoPago) {
/*  39 */     this.diaFijoPago = diaFijoPago;
/*     */   }
/*     */ 
/*     */   public Integer getDiasEntrePagos() {
/*  43 */     return this.diasEntrePagos;
/*     */   }
/*     */ 
/*     */   public void setDiasEntrePagos(Integer diasEntrePagos) {
/*  47 */     this.diasEntrePagos = diasEntrePagos;
/*     */   }
/*     */ 
/*     */   public Integer getDiasPrimerPago() {
/*  51 */     return this.diasPrimerPago;
/*     */   }
/*     */ 
/*     */   public void setDiasPrimerPago(Integer diasPrimerPago) {
/*  55 */     this.diasPrimerPago = diasPrimerPago;
/*     */   }
/*     */ 
/*     */   public Integer getIdFormaPago() {
/*  59 */     return this.idFormaPago;
/*     */   }
/*     */ 
/*     */   public void setIdFormaPago(Integer idFormaPago) {
/*  63 */     this.idFormaPago = idFormaPago;
/*     */   }
/*     */ 
/*     */   public Integer getNumeroPagos() {
/*  67 */     return this.numeroPagos;
/*     */   }
/*     */ 
/*     */   public void setNumeroPagos(Integer numeroPagos) {
/*  71 */     this.numeroPagos = numeroPagos;
/*     */   }
/*     */ 
/*     */   public String getPago() {
/*  75 */     return this.pago;
/*     */   }
/*     */ 
/*     */   public void setPago(String pago) {
/*  79 */     this.pago = pago;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/*  84 */     if (obj == null) {
/*  85 */       return false;
/*     */     }
/*  87 */     if (getClass() != obj.getClass()) {
/*  88 */       return false;
/*     */     }
/*  90 */     TipoFormaPago other = (TipoFormaPago)obj;
/*  91 */     if ((this.idFormaPago != other.idFormaPago) && ((this.idFormaPago == null) || (!this.idFormaPago.equals(other.idFormaPago)))) {
/*  92 */       return false;
/*     */     }
/*  94 */     if (this.pago == null ? other.pago != null : !this.pago.equals(other.pago)) {
/*  95 */       return false;
/*     */     }
/*  97 */     return true;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 102 */     int hash = 5;
/* 103 */     return hash;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 108 */     return this.pago;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.TipoFormaPago
 * JD-Core Version:    0.6.2
 */