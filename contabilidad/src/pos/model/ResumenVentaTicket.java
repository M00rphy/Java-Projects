/*     */ package pos.model;
/*     */ 
/*     */ public class ResumenVentaTicket
/*     */ {
/*     */   private int idTicket;
/*     */   private int ticket;
/*     */   private String descripcion;
/*     */   private int unidades;
/*     */   private double importe;
/*     */   private double total;
/*     */   private String medioPago;
/*     */   private int plazos;
/*     */   private int idProducto;
/*     */   private int almacen;
/*     */   private int cliente;
/*     */   private int idMedioPago;
/*     */   private int cerrado;
/*     */   private String nota;
/*     */ 
/*     */   public ResumenVentaTicket(int idTicket, int ticket, String descripcion, int unidades, double importe, double total, String medioPago, int plazos, int idProducto, int almacen, int cliente, int idMedioPago, int cerrado, String nota)
/*     */   {
/*  30 */     this.idTicket = idTicket;
/*  31 */     this.ticket = ticket;
/*  32 */     this.descripcion = descripcion;
/*  33 */     this.unidades = unidades;
/*  34 */     this.importe = importe;
/*  35 */     this.total = total;
/*  36 */     this.medioPago = medioPago;
/*  37 */     this.plazos = plazos;
/*  38 */     this.idProducto = idProducto;
/*  39 */     this.almacen = almacen;
/*  40 */     this.cliente = cliente;
/*  41 */     this.idMedioPago = idMedioPago;
/*  42 */     this.cerrado = cerrado;
/*  43 */     this.nota = nota;
/*     */   }
/*     */ 
/*     */   public String getNota() {
/*  47 */     return this.nota;
/*     */   }
/*     */ 
/*     */   public void setNota(String nota) {
/*  51 */     this.nota = nota;
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
/*     */   public int getIdTicket() {
/*  63 */     return this.idTicket;
/*     */   }
/*     */ 
/*     */   public void setIdTicket(int idTicket) {
/*  67 */     this.idTicket = idTicket;
/*     */   }
/*     */ 
/*     */   public double getImporte() {
/*  71 */     return this.importe;
/*     */   }
/*     */ 
/*     */   public void setImporte(double importe) {
/*  75 */     this.importe = importe;
/*     */   }
/*     */ 
/*     */   public String getMedioPago() {
/*  79 */     return this.medioPago;
/*     */   }
/*     */ 
/*     */   public void setMedioPago(String medioPago) {
/*  83 */     this.medioPago = medioPago;
/*     */   }
/*     */ 
/*     */   public int getPlazos() {
/*  87 */     return this.plazos;
/*     */   }
/*     */ 
/*     */   public void setPlazos(int plazos) {
/*  91 */     this.plazos = plazos;
/*     */   }
/*     */ 
/*     */   public double getTotal() {
/*  95 */     return this.total;
/*     */   }
/*     */ 
/*     */   public void setTotal(double total) {
/*  99 */     this.total = total;
/*     */   }
/*     */ 
/*     */   public int getUnidades() {
/* 103 */     return this.unidades;
/*     */   }
/*     */ 
/*     */   public void setUnidades(int unidades) {
/* 107 */     this.unidades = unidades;
/*     */   }
/*     */ 
/*     */   public int isCerrado() {
/* 111 */     return this.cerrado;
/*     */   }
/*     */ 
/*     */   public void setCerrado(int cerrado) {
/* 115 */     this.cerrado = cerrado;
/*     */   }
/*     */ 
/*     */   public int getCliente() {
/* 119 */     return this.cliente;
/*     */   }
/*     */ 
/*     */   public void setCliente(int cliente) {
/* 123 */     this.cliente = cliente;
/*     */   }
/*     */ 
/*     */   public int getAlmacen() {
/* 127 */     return this.almacen;
/*     */   }
/*     */ 
/*     */   public void setAlmacen(int almacen) {
/* 131 */     this.almacen = almacen;
/*     */   }
/*     */ 
/*     */   public int getIdProducto() {
/* 135 */     return this.idProducto;
/*     */   }
/*     */ 
/*     */   public void setIdProducto(int idProducto) {
/* 139 */     this.idProducto = idProducto;
/*     */   }
/*     */ 
/*     */   public int getIdMedioPago() {
/* 143 */     return this.idMedioPago;
/*     */   }
/*     */ 
/*     */   public void setIdMedioPago(int idMedioPago) {
/* 147 */     this.idMedioPago = idMedioPago;
/*     */   }
/*     */ 
/*     */   public int getTicket() {
/* 151 */     return this.ticket;
/*     */   }
/*     */ 
/*     */   public void setTicket(int ticket) {
/* 155 */     this.ticket = ticket;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.model.ResumenVentaTicket
 * JD-Core Version:    0.6.2
 */