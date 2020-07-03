/*     */ package contaes.manejoDatos;
/*     */ 
/*     */ public class TipoFactura
/*     */ {
/*     */   private String numero;
/*     */   private String fecha;
/*     */   private int cuenta;
/*     */   private int id_asiento;
/*     */   private String concepto;
/*     */   private double base;
/*     */   private double iva;
/*     */   private double re;
/*     */   private double total;
/*     */ 
/*     */   public TipoFactura()
/*     */   {
/*     */   }
/*     */ 
/*     */   public TipoFactura(String numero, String fecha, int cuenta, int id_asiento, String concepto, double base, double iva, double re, double total)
/*     */   {
/*  33 */     this.numero = numero;
/*  34 */     this.fecha = fecha;
/*  35 */     this.cuenta = cuenta;
/*  36 */     this.id_asiento = id_asiento;
/*  37 */     this.concepto = concepto;
/*  38 */     this.base = base;
/*  39 */     this.iva = iva;
/*  40 */     this.re = re;
/*  41 */     this.total = total;
/*     */   }
/*     */ 
/*     */   public double getBase()
/*     */   {
/*  48 */     return this.base;
/*     */   }
/*     */ 
/*     */   public void setBase(double base)
/*     */   {
/*  55 */     this.base = base;
/*     */   }
/*     */ 
/*     */   public String getConcepto()
/*     */   {
/*  62 */     return this.concepto;
/*     */   }
/*     */ 
/*     */   public void setConcepto(String concepto)
/*     */   {
/*  69 */     this.concepto = concepto;
/*     */   }
/*     */ 
/*     */   public int getCuenta()
/*     */   {
/*  76 */     return this.cuenta;
/*     */   }
/*     */ 
/*     */   public void setCuenta(int cuenta)
/*     */   {
/*  83 */     this.cuenta = cuenta;
/*     */   }
/*     */ 
/*     */   public String getFecha()
/*     */   {
/*  90 */     return this.fecha;
/*     */   }
/*     */ 
/*     */   public void setFecha(String fecha)
/*     */   {
/*  97 */     this.fecha = fecha;
/*     */   }
/*     */ 
/*     */   public int getId_asiento()
/*     */   {
/* 104 */     return this.id_asiento;
/*     */   }
/*     */ 
/*     */   public void setId_asiento(int id_asiento)
/*     */   {
/* 111 */     this.id_asiento = id_asiento;
/*     */   }
/*     */ 
/*     */   public double getIva()
/*     */   {
/* 118 */     return this.iva;
/*     */   }
/*     */ 
/*     */   public void setIva(double iva)
/*     */   {
/* 125 */     this.iva = iva;
/*     */   }
/*     */ 
/*     */   public double getRe() {
/* 129 */     return this.re;
/*     */   }
/*     */ 
/*     */   public void setRe(double re) {
/* 133 */     this.re = re;
/*     */   }
/*     */ 
/*     */   public String getNumero()
/*     */   {
/* 140 */     return this.numero;
/*     */   }
/*     */ 
/*     */   public void setNumero(String numero)
/*     */   {
/* 147 */     this.numero = numero;
/*     */   }
/*     */ 
/*     */   public double getTotal()
/*     */   {
/* 154 */     return this.total;
/*     */   }
/*     */ 
/*     */   public void setTotal(double total)
/*     */   {
/* 161 */     this.total = total;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.TipoFactura
 * JD-Core Version:    0.6.2
 */