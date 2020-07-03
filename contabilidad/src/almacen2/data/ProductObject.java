/*     */ package almacen2.data;
/*     */ 
/*     */ public class ProductObject
/*     */ {
/*     */   private int id;
/*     */   private String referencia;
/*     */   private String descripcion;
/*     */   private int proveedor;
/*     */   private int familia;
/*     */   private double coste;
/*     */   private double pvp;
/*     */   private String imagen;
/*     */   private int stockMinimo;
/*     */   private int pedidoMinimo;
/*     */   private int idtipoiva;
/*     */ 
/*     */   public ProductObject(int id, String referencia, String descripcion, int proveedor, int familia, double coste, double pvp, String imagen, int sMin, int pMin, int idtipoiva)
/*     */   {
/*  21 */     this.id = id;
/*  22 */     this.referencia = referencia;
/*  23 */     this.descripcion = descripcion;
/*  24 */     this.proveedor = proveedor;
/*  25 */     this.familia = familia;
/*  26 */     this.coste = coste;
/*  27 */     this.pvp = pvp;
/*  28 */     this.imagen = imagen;
/*  29 */     this.stockMinimo = sMin;
/*  30 */     this.pedidoMinimo = pMin;
/*  31 */     this.idtipoiva = idtipoiva;
/*     */   }
/*     */ 
/*     */   public ProductObject()
/*     */   {
/*     */   }
/*     */ 
/*     */   public int getId() {
/*  39 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(int id) {
/*  43 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public String getReferencia() {
/*  47 */     return this.referencia;
/*     */   }
/*     */ 
/*     */   public void setReferencia(String referencia) {
/*  51 */     this.referencia = referencia;
/*     */   }
/*     */ 
/*     */   public String getDescripcion() {
/*  55 */     return this.descripcion;
/*     */   }
/*     */ 
/*     */   public void setDescripcion(String descripcion) {
/*  59 */     this.descripcion = descripcion;
/*     */   }
/*     */ 
/*     */   public int getProveedor() {
/*  63 */     return this.proveedor;
/*     */   }
/*     */ 
/*     */   public void setProveedor(int proveedor) {
/*  67 */     this.proveedor = proveedor;
/*     */   }
/*     */ 
/*     */   public int getFamilia() {
/*  71 */     return this.familia;
/*     */   }
/*     */ 
/*     */   public void setFamilia(int familia) {
/*  75 */     this.familia = familia;
/*     */   }
/*     */ 
/*     */   public double getCoste() {
/*  79 */     return this.coste;
/*     */   }
/*     */ 
/*     */   public void setCoste(double coste) {
/*  83 */     this.coste = coste;
/*     */   }
/*     */ 
/*     */   public double getPvp() {
/*  87 */     return this.pvp;
/*     */   }
/*     */ 
/*     */   public void setPvp(double pvp) {
/*  91 */     this.pvp = pvp;
/*     */   }
/*     */ 
/*     */   public String getImagen() {
/*  95 */     return this.imagen;
/*     */   }
/*     */ 
/*     */   public void setImagen(String imagen) {
/*  99 */     this.imagen = imagen;
/*     */   }
/*     */ 
/*     */   public int getPedidoMinimo() {
/* 103 */     return this.pedidoMinimo;
/*     */   }
/*     */ 
/*     */   public void setPedidoMinimo(int pedidoMinimo) {
/* 107 */     this.pedidoMinimo = pedidoMinimo;
/*     */   }
/*     */ 
/*     */   public int getStockMinimo() {
/* 111 */     return this.stockMinimo;
/*     */   }
/*     */ 
/*     */   public void setStockMinimo(int stockMinimo) {
/* 115 */     this.stockMinimo = stockMinimo;
/*     */   }
/*     */ 
/*     */   public int getIdtipoiva() {
/* 119 */     return this.idtipoiva;
/*     */   }
/*     */ 
/*     */   public void setIdtipoiva(int idtipoiva) {
/* 123 */     this.idtipoiva = idtipoiva;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.data.ProductObject
 * JD-Core Version:    0.6.2
 */