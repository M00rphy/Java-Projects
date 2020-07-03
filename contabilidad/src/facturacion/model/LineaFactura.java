/*     */ package facturacion.model;
/*     */ 
/*     */ import contaes.manejoDatos.TipoIVA;
/*     */ 
/*     */ public class LineaFactura
/*     */ {
/*     */   private Integer id;
/*     */   private Integer idFactura;
/*     */   private Producto idProducto;
/*     */   private String concepto;
/*     */   private Double base;
/*     */   private TipoIVA tipoIva;
/*     */   private Double total;
/*     */   private Double unidades;
/*     */ 
/*     */   public LineaFactura(Integer id, Integer idFactura, Producto idProducto, String concepto, Double base, TipoIVA tipoIva, Double total, Double unidades)
/*     */   {
/*  26 */     this.id = id;
/*  27 */     this.idFactura = idFactura;
/*  28 */     this.idProducto = idProducto;
/*  29 */     this.concepto = concepto;
/*  30 */     this.base = base;
/*  31 */     this.tipoIva = tipoIva;
/*  32 */     this.total = total;
/*  33 */     this.unidades = unidades;
/*     */   }
/*     */ 
/*     */   public Double getBase() {
/*  37 */     return this.base;
/*     */   }
/*     */ 
/*     */   public void setBase(Double base) {
/*  41 */     this.base = base;
/*     */   }
/*     */ 
/*     */   public String getConcepto() {
/*  45 */     return this.concepto;
/*     */   }
/*     */ 
/*     */   public void setConcepto(String concepto) {
/*  49 */     this.concepto = concepto;
/*     */   }
/*     */ 
/*     */   public Integer getId() {
/*  53 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/*  57 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public Integer getIdFactura() {
/*  61 */     return this.idFactura;
/*     */   }
/*     */ 
/*     */   public void setIdFactura(Integer idFactura) {
/*  65 */     this.idFactura = idFactura;
/*     */   }
/*     */ 
/*     */   public Producto getIdProducto() {
/*  69 */     return this.idProducto;
/*     */   }
/*     */ 
/*     */   public void setIdProducto(Producto idProducto) {
/*  73 */     this.idProducto = idProducto;
/*     */   }
/*     */ 
/*     */   public TipoIVA getTipoIva() {
/*  77 */     return this.tipoIva;
/*     */   }
/*     */ 
/*     */   public void setTipoIva(TipoIVA tipoIva) {
/*  81 */     this.tipoIva = tipoIva;
/*     */   }
/*     */ 
/*     */   public Double getTotal() {
/*  85 */     return this.total;
/*     */   }
/*     */ 
/*     */   public void setTotal(Double total) {
/*  89 */     this.total = total;
/*     */   }
/*     */ 
/*     */   public Double getUnidades() {
/*  93 */     return this.unidades;
/*     */   }
/*     */ 
/*     */   public void setUnidades(Double unidades) {
/*  97 */     this.unidades = unidades;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 102 */     return this.concepto + " - " + this.total.toString() + " (" + this.idFactura.toString() + ")";
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     facturacion.model.LineaFactura
 * JD-Core Version:    0.6.2
 */