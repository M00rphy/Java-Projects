/*     */ package pos.model;
/*     */ 
/*     */ public class VentaPOS
/*     */ {
/*     */   private Integer id;
/*     */   private int idTicket;
/*     */   private int idProducto;
/*     */   private String descripcion;
/*     */   private int unidades;
/*     */   private double importe;
/*     */   private double total;
/*     */   private String nota;
/*     */ 
/*     */   public VentaPOS(Integer id, int idTicket, int idProducto, String descripcion, int unidades, double importe, String nota)
/*     */   {
/*  49 */     this.id = id;
/*  50 */     this.idTicket = idTicket;
/*  51 */     this.idProducto = idProducto;
/*  52 */     this.descripcion = descripcion;
/*  53 */     this.unidades = unidades;
/*  54 */     this.importe = importe;
/*  55 */     this.total = (unidades * importe);
/*  56 */     this.nota = nota;
/*     */   }
/*     */ 
/*     */   public VentaPOS(VentaPOS venta) {
/*  60 */     this.id = venta.getId();
/*  61 */     this.idTicket = venta.getIdTicket();
/*  62 */     this.idProducto = venta.getIdProducto();
/*  63 */     this.descripcion = venta.getDescripcion();
/*  64 */     this.unidades = venta.getUnidades();
/*  65 */     this.importe = venta.getImporte();
/*  66 */     this.total = (venta.getUnidades() * venta.getImporte());
/*  67 */     this.nota = venta.getNota();
/*     */   }
/*     */ 
/*     */   public String getNota() {
/*  71 */     return this.nota;
/*     */   }
/*     */ 
/*     */   public void setNota(String nota) {
/*  75 */     this.nota = nota;
/*     */   }
/*     */ 
/*     */   public String getDescripcion() {
/*  79 */     return this.descripcion;
/*     */   }
/*     */ 
/*     */   public void setDescripcion(String descripcion) {
/*  83 */     this.descripcion = descripcion;
/*     */   }
/*     */ 
/*     */   public Integer getId() {
/*  87 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/*  91 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public int getIdProducto() {
/*  95 */     return this.idProducto;
/*     */   }
/*     */ 
/*     */   public void setIdProducto(int idProducto) {
/*  99 */     this.idProducto = idProducto;
/*     */   }
/*     */ 
/*     */   public int getIdTicket() {
/* 103 */     return this.idTicket;
/*     */   }
/*     */ 
/*     */   public void setIdTicket(int idTicket) {
/* 107 */     this.idTicket = idTicket;
/*     */   }
/*     */ 
/*     */   public double getImporte() {
/* 111 */     return this.importe;
/*     */   }
/*     */ 
/*     */   public void setImporte(double importe) {
/* 115 */     this.importe = importe;
/*     */   }
/*     */ 
/*     */   public int getUnidades() {
/* 119 */     return this.unidades;
/*     */   }
/*     */ 
/*     */   public void setUnidades(int unidades) {
/* 123 */     this.unidades = unidades;
/*     */   }
/*     */ 
/*     */   public double getTotal() {
/* 127 */     return this.total;
/*     */   }
/*     */ 
/*     */   public void setTotal(double total) {
/* 131 */     this.total = total;
/*     */   }
/*     */ 
/*     */   public void recalcularTotal() {
/* 135 */     this.total = (this.unidades * this.importe);
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 140 */     if (obj == null) {
/* 141 */       return false;
/*     */     }
/* 143 */     if (getClass() != obj.getClass()) {
/* 144 */       return false;
/*     */     }
/* 146 */     VentaPOS other = (VentaPOS)obj;
/* 147 */     if ((this.id != other.id) && ((this.id == null) || (!this.id.equals(other.id)))) {
/* 148 */       return false;
/*     */     }
/* 150 */     if (this.descripcion == null ? other.descripcion != null : !this.descripcion.equals(other.descripcion)) {
/* 151 */       return false;
/*     */     }
/* 153 */     if (this.unidades != other.unidades) {
/* 154 */       return false;
/*     */     }
/* 156 */     if (this.importe != other.importe) {
/* 157 */       return false;
/*     */     }
/* 159 */     return true;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 164 */     int hash = 7;
/* 165 */     hash = 67 * hash + (this.id != null ? this.id.hashCode() : 0);
/* 166 */     hash = 67 * hash + this.idTicket;
/* 167 */     hash = 67 * hash + this.idProducto;
/* 168 */     return hash;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.model.VentaPOS
 * JD-Core Version:    0.6.2
 */