/*     */ package pos.model;
/*     */ 
/*     */ import java.util.Date;
/*     */ 
/*     */ public class ResumenVentaTicketMio
/*     */ {
/*     */   private Date fecha;
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
/*     */   public ResumenVentaTicketMio(Date fecha, int idTicket, int ticket, String descripcion, int unidades, double importe, double total, String medioPago, int plazos, int idProducto, int almacen, int cliente, int idMedioPago, int cerrado, String nota)
/*     */   {
/*  33 */     this.fecha = fecha;
/*  34 */     this.idTicket = idTicket;
/*  35 */     this.ticket = ticket;
/*  36 */     this.descripcion = descripcion;
/*  37 */     this.unidades = unidades;
/*  38 */     this.importe = importe;
/*  39 */     this.total = total;
/*  40 */     this.medioPago = medioPago;
/*  41 */     this.plazos = plazos;
/*  42 */     this.idProducto = idProducto;
/*  43 */     this.almacen = almacen;
/*  44 */     this.cliente = cliente;
/*  45 */     this.idMedioPago = idMedioPago;
/*  46 */     this.cerrado = cerrado;
/*  47 */     this.nota = nota;
/*     */   }
/*     */ 
/*     */   public String getNota() {
/*  51 */     return this.nota;
/*     */   }
/*     */ 
/*     */   public void setNota(String nota) {
/*  55 */     this.nota = nota;
/*     */   }
/*     */ 
/*     */   public Date getFecha() {
/*  59 */     return this.fecha;
/*     */   }
/*     */ 
/*     */   public void setFecha(Date fecha) {
/*  63 */     this.fecha = fecha;
/*     */   }
/*     */ 
/*     */   public String getDescripcion() {
/*  67 */     return this.descripcion;
/*     */   }
/*     */ 
/*     */   public void setDescripcion(String descripcion) {
/*  71 */     this.descripcion = descripcion;
/*     */   }
/*     */ 
/*     */   public int getIdTicket() {
/*  75 */     return this.idTicket;
/*     */   }
/*     */ 
/*     */   public void setIdTicket(int idTicket) {
/*  79 */     this.idTicket = idTicket;
/*     */   }
/*     */ 
/*     */   public double getImporte() {
/*  83 */     return this.importe;
/*     */   }
/*     */ 
/*     */   public void setImporte(double importe) {
/*  87 */     this.importe = importe;
/*     */   }
/*     */ 
/*     */   public String getMedioPago() {
/*  91 */     return this.medioPago;
/*     */   }
/*     */ 
/*     */   public void setMedioPago(String medioPago) {
/*  95 */     this.medioPago = medioPago;
/*     */   }
/*     */ 
/*     */   public int getPlazos() {
/*  99 */     return this.plazos;
/*     */   }
/*     */ 
/*     */   public void setPlazos(int plazos) {
/* 103 */     this.plazos = plazos;
/*     */   }
/*     */ 
/*     */   public double getTotal() {
/* 107 */     return this.total;
/*     */   }
/*     */ 
/*     */   public void setTotal(double total) {
/* 111 */     this.total = total;
/*     */   }
/*     */ 
/*     */   public int getUnidades() {
/* 115 */     return this.unidades;
/*     */   }
/*     */ 
/*     */   public void setUnidades(int unidades) {
/* 119 */     this.unidades = unidades;
/*     */   }
/*     */ 
/*     */   public int isCerrado() {
/* 123 */     return this.cerrado;
/*     */   }
/*     */ 
/*     */   public void setCerrado(int cerrado) {
/* 127 */     this.cerrado = cerrado;
/*     */   }
/*     */ 
/*     */   public int getCliente() {
/* 131 */     return this.cliente;
/*     */   }
/*     */ 
/*     */   public void setCliente(int cliente) {
/* 135 */     this.cliente = cliente;
/*     */   }
/*     */ 
/*     */   public int getAlmacen() {
/* 139 */     return this.almacen;
/*     */   }
/*     */ 
/*     */   public void setAlmacen(int almacen) {
/* 143 */     this.almacen = almacen;
/*     */   }
/*     */ 
/*     */   public int getIdProducto() {
/* 147 */     return this.idProducto;
/*     */   }
/*     */ 
/*     */   public void setIdProducto(int idProducto) {
/* 151 */     this.idProducto = idProducto;
/*     */   }
/*     */ 
/*     */   public int getIdMedioPago() {
/* 155 */     return this.idMedioPago;
/*     */   }
/*     */ 
/*     */   public void setIdMedioPago(int idMedioPago) {
/* 159 */     this.idMedioPago = idMedioPago;
/*     */   }
/*     */ 
/*     */   public int getTicket() {
/* 163 */     return this.ticket;
/*     */   }
/*     */ 
/*     */   public void setTicket(int ticket) {
/* 167 */     this.ticket = ticket;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.model.ResumenVentaTicketMio
 * JD-Core Version:    0.6.2
 */