/*     */ package pos.model;
/*     */ 
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class Ticket
/*     */ {
/*     */   private Integer id;
/*     */   private int numero;
/*     */   private Date fecha;
/*     */   private int almacen;
/*     */   private int medioPago;
/*     */   private int plazos;
/*     */   private int vendedor;
/*     */   private int cliente;
/*     */   private int cerrado;
/*     */   private Integer idplazoanterior;
/*     */ 
/*     */   public Ticket(Integer id, int numero, Date fecha, int almacen, int medioPago, int plazos, int vendedor, int cliente, int cerrado, Integer idplazoanterior)
/*     */   {
/*  47 */     this.id = id;
/*  48 */     this.numero = numero;
/*  49 */     this.fecha = fecha;
/*  50 */     this.almacen = almacen;
/*  51 */     this.medioPago = medioPago;
/*  52 */     this.plazos = plazos;
/*  53 */     this.vendedor = vendedor;
/*  54 */     this.cliente = cliente;
/*  55 */     this.cerrado = cerrado;
/*  56 */     this.idplazoanterior = idplazoanterior;
/*     */   }
/*     */ 
/*     */   public int getAlmacen() {
/*  60 */     return this.almacen;
/*     */   }
/*     */ 
/*     */   public void setAlmacen(int almacen) {
/*  64 */     this.almacen = almacen;
/*     */   }
/*     */ 
/*     */   public int isCerrado() {
/*  68 */     return this.cerrado;
/*     */   }
/*     */ 
/*     */   public void setCerrado(int cerrado) {
/*  72 */     this.cerrado = cerrado;
/*     */   }
/*     */ 
/*     */   public Date getFecha() {
/*  76 */     return this.fecha;
/*     */   }
/*     */ 
/*     */   public void setFecha(Date fecha) {
/*  80 */     this.fecha = fecha;
/*     */   }
/*     */ 
/*     */   public Integer getId() {
/*  84 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/*  88 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public int getVendedor() {
/*  92 */     return this.vendedor;
/*     */   }
/*     */ 
/*     */   public void setVendedor(int vendedor) {
/*  96 */     this.vendedor = vendedor;
/*     */   }
/*     */ 
/*     */   public int getCliente() {
/* 100 */     return this.cliente;
/*     */   }
/*     */ 
/*     */   public void setCliente(int cliente) {
/* 104 */     this.cliente = cliente;
/*     */   }
/*     */ 
/*     */   public int getMedioPago() {
/* 108 */     return this.medioPago;
/*     */   }
/*     */ 
/*     */   public void setMedioPago(int medioPago) {
/* 112 */     this.medioPago = medioPago;
/*     */   }
/*     */ 
/*     */   public int getNumero() {
/* 116 */     return this.numero;
/*     */   }
/*     */ 
/*     */   public void setNumero(int numero) {
/* 120 */     this.numero = numero;
/*     */   }
/*     */ 
/*     */   public int getPlazos() {
/* 124 */     return this.plazos;
/*     */   }
/*     */ 
/*     */   public void setPlazos(int plazos) {
/* 128 */     this.plazos = plazos;
/*     */   }
/*     */ 
/*     */   public Integer getIdplazoanterior() {
/* 132 */     return this.idplazoanterior;
/*     */   }
/*     */ 
/*     */   public void setIdplazoanterior(Integer idplazoanterior) {
/* 136 */     this.idplazoanterior = idplazoanterior;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 141 */     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
/* 142 */     return Integer.toString(this.numero) + " " + sdf.format(this.fecha);
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.model.Ticket
 * JD-Core Version:    0.6.2
 */