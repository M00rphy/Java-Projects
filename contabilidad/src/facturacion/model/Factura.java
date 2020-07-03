/*     */ package facturacion.model;
/*     */ 
/*     */ import contaes.manejoDatos.TipoFormaPago;
/*     */ import contaes.manejoDatos.TipoSubcuenta;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class Factura
/*     */ {
/*     */   private Integer id;
/*     */   private String numero;
/*     */   private TipoSubcuenta cliente;
/*     */   private Date fecha;
/*     */   private Double retencion;
/*     */   private boolean recargo;
/*     */   private TipoFormaPago formaPago;
/*     */   private Double base;
/*     */   private Double iva;
/*     */   private Double total;
/*     */   private boolean contabilizada;
/*     */   private boolean isAlmacenada;
/*     */ 
/*     */   public Factura(Integer id, String numero, TipoSubcuenta cliente, Date fecha, Double retencion, boolean recargo, TipoFormaPago formaPago, Double base, Double iva, boolean contabilizada, boolean isAlmacenada)
/*     */   {
/*  32 */     this.id = id;
/*  33 */     this.numero = numero;
/*  34 */     this.cliente = cliente;
/*  35 */     this.fecha = fecha;
/*  36 */     this.retencion = retencion;
/*  37 */     this.recargo = recargo;
/*  38 */     this.formaPago = formaPago;
/*  39 */     this.base = base;
/*  40 */     this.iva = iva;
/*  41 */     this.total = Double.valueOf(this.base.doubleValue() + this.iva.doubleValue());
/*  42 */     this.contabilizada = contabilizada;
/*  43 */     this.isAlmacenada = isAlmacenada;
/*     */   }
/*     */ 
/*     */   public TipoSubcuenta getCliente() {
/*  47 */     return this.cliente;
/*     */   }
/*     */ 
/*     */   public void setCliente(TipoSubcuenta cliente) {
/*  51 */     this.cliente = cliente;
/*     */   }
/*     */ 
/*     */   public Date getFecha() {
/*  55 */     return this.fecha;
/*     */   }
/*     */ 
/*     */   public void setFecha(Date fecha) {
/*  59 */     this.fecha = fecha;
/*     */   }
/*     */ 
/*     */   public TipoFormaPago getFormaPago() {
/*  63 */     return this.formaPago;
/*     */   }
/*     */ 
/*     */   public void setFormaPago(TipoFormaPago formaPago) {
/*  67 */     this.formaPago = formaPago;
/*     */   }
/*     */ 
/*     */   public Integer getId() {
/*  71 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/*  75 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public String getNumero() {
/*  79 */     return this.numero;
/*     */   }
/*     */ 
/*     */   public void setNumero(String numero) {
/*  83 */     this.numero = numero;
/*     */   }
/*     */ 
/*     */   public boolean isRecargo() {
/*  87 */     return this.recargo;
/*     */   }
/*     */ 
/*     */   public void setRecargo(boolean recargo) {
/*  91 */     this.recargo = recargo;
/*     */   }
/*     */ 
/*     */   public Double getRetencion() {
/*  95 */     return this.retencion;
/*     */   }
/*     */ 
/*     */   public void setRetencion(Double retencion) {
/*  99 */     this.retencion = retencion;
/*     */   }
/*     */ 
/*     */   public Double getBase() {
/* 103 */     return this.base;
/*     */   }
/*     */ 
/*     */   public void setBase(Double base) {
/* 107 */     this.base = base;
/*     */   }
/*     */ 
/*     */   public Double getIva() {
/* 111 */     return this.iva;
/*     */   }
/*     */ 
/*     */   public void setIva(Double iva) {
/* 115 */     this.iva = iva;
/*     */   }
/*     */ 
/*     */   public Double getTotal() {
/* 119 */     return this.total;
/*     */   }
/*     */ 
/*     */   public void setTotal(Double total) {
/* 123 */     this.total = total;
/*     */   }
/*     */ 
/*     */   public boolean isContabilizada() {
/* 127 */     return this.contabilizada;
/*     */   }
/*     */ 
/*     */   public void setContabilizada(boolean contabilizada) {
/* 131 */     this.contabilizada = contabilizada;
/*     */   }
/*     */ 
/*     */   public boolean isIsAlmacenada() {
/* 135 */     return this.isAlmacenada;
/*     */   }
/*     */ 
/*     */   public void setIsAlmacenada(boolean isAlmacenada) {
/* 139 */     this.isAlmacenada = isAlmacenada;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 144 */     return this.numero + "; " + this.cliente.getNombre();
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     facturacion.model.Factura
 * JD-Core Version:    0.6.2
 */