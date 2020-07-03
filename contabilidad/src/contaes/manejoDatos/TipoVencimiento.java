/*     */ package contaes.manejoDatos;
/*     */ 
/*     */ public class TipoVencimiento
/*     */ {
/*     */   private int id;
/*     */   private String fecha;
/*     */   private int ejercicio;
/*     */   private String factura;
/*     */   private String fechaf;
/*     */   private int cuenta;
/*     */   private double importe;
/*     */   private String num;
/*     */   private boolean pagado;
/*     */   private int cuentap;
/*     */ 
/*     */   public TipoVencimiento()
/*     */   {
/*     */   }
/*     */ 
/*     */   public TipoVencimiento(int id, String fecha, int ejercicio, String factura, String fechaf, int cuenta, double importe, String num, boolean pagado, int cuentap)
/*     */   {
/*  37 */     this.id = id;
/*  38 */     this.fecha = fecha;
/*  39 */     this.ejercicio = ejercicio;
/*  40 */     this.factura = factura;
/*  41 */     this.fechaf = fechaf;
/*  42 */     this.cuenta = cuenta;
/*  43 */     this.importe = importe;
/*  44 */     this.num = num;
/*  45 */     this.pagado = pagado;
/*  46 */     this.cuentap = cuentap;
/*     */   }
/*     */ 
/*     */   public int getCuenta()
/*     */   {
/*  53 */     return this.cuenta;
/*     */   }
/*     */ 
/*     */   public void setCuenta(int cuenta)
/*     */   {
/*  60 */     this.cuenta = cuenta;
/*     */   }
/*     */ 
/*     */   public int getCuentap()
/*     */   {
/*  67 */     return this.cuentap;
/*     */   }
/*     */ 
/*     */   public void setCuentap(int cuentap)
/*     */   {
/*  74 */     this.cuentap = cuentap;
/*     */   }
/*     */ 
/*     */   public int getEjercicio()
/*     */   {
/*  81 */     return this.ejercicio;
/*     */   }
/*     */ 
/*     */   public void setEjercicio(int ejercicio)
/*     */   {
/*  88 */     this.ejercicio = ejercicio;
/*     */   }
/*     */ 
/*     */   public String getFactura()
/*     */   {
/*  95 */     return this.factura;
/*     */   }
/*     */ 
/*     */   public void setFactura(String factura)
/*     */   {
/* 102 */     this.factura = factura;
/*     */   }
/*     */ 
/*     */   public String getFecha()
/*     */   {
/* 109 */     return this.fecha;
/*     */   }
/*     */ 
/*     */   public void setFecha(String fecha)
/*     */   {
/* 116 */     this.fecha = fecha;
/*     */   }
/*     */ 
/*     */   public String getFechaf()
/*     */   {
/* 123 */     return this.fechaf;
/*     */   }
/*     */ 
/*     */   public void setFechaf(String fechaf)
/*     */   {
/* 130 */     this.fechaf = fechaf;
/*     */   }
/*     */ 
/*     */   public int getId()
/*     */   {
/* 137 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(int id)
/*     */   {
/* 144 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public double getImporte()
/*     */   {
/* 151 */     return this.importe;
/*     */   }
/*     */ 
/*     */   public void setImporte(double importe)
/*     */   {
/* 158 */     this.importe = importe;
/*     */   }
/*     */ 
/*     */   public String getNum()
/*     */   {
/* 165 */     return this.num;
/*     */   }
/*     */ 
/*     */   public void setNum(String num)
/*     */   {
/* 172 */     this.num = num;
/*     */   }
/*     */ 
/*     */   public boolean isPagado()
/*     */   {
/* 179 */     return this.pagado;
/*     */   }
/*     */ 
/*     */   public void setPagado(boolean pagado)
/*     */   {
/* 186 */     this.pagado = pagado;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.TipoVencimiento
 * JD-Core Version:    0.6.2
 */