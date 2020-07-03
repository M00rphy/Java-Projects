/*     */ package contaes.manejoDatos;
/*     */ 
/*     */ public class TipoApunte
/*     */ {
/*     */   private int id_apunte;
/*     */   private int id_asiento;
/*     */   private int cuenta;
/*     */   private String concepto;
/*     */   private String DH;
/*     */   private double importe;
/*     */ 
/*     */   public TipoApunte()
/*     */   {
/*     */   }
/*     */ 
/*     */   public TipoApunte(int id_apunte, int id_asiento, int cuenta, String concepto, String dh, double importe)
/*     */   {
/*  28 */     this.id_apunte = id_apunte;
/*  29 */     this.id_asiento = id_asiento;
/*  30 */     this.cuenta = cuenta;
/*  31 */     this.concepto = concepto;
/*  32 */     this.DH = dh;
/*  33 */     this.importe = importe;
/*     */   }
/*     */ 
/*     */   public String getConcepto()
/*     */   {
/*  40 */     return this.concepto;
/*     */   }
/*     */ 
/*     */   public void setConcepto(String concepto)
/*     */   {
/*  47 */     this.concepto = concepto;
/*     */   }
/*     */ 
/*     */   public int getCuenta()
/*     */   {
/*  54 */     return this.cuenta;
/*     */   }
/*     */ 
/*     */   public void setCuenta(int cuenta)
/*     */   {
/*  61 */     this.cuenta = cuenta;
/*     */   }
/*     */ 
/*     */   public String getDH()
/*     */   {
/*  68 */     return this.DH;
/*     */   }
/*     */ 
/*     */   public void setDH(String dh)
/*     */   {
/*  75 */     this.DH = dh;
/*     */   }
/*     */ 
/*     */   public int getId_apunte()
/*     */   {
/*  82 */     return this.id_apunte;
/*     */   }
/*     */ 
/*     */   public void setId_apunte(int id_apunte)
/*     */   {
/*  89 */     this.id_apunte = id_apunte;
/*     */   }
/*     */ 
/*     */   public int getId_asiento()
/*     */   {
/*  96 */     return this.id_asiento;
/*     */   }
/*     */ 
/*     */   public void setId_asiento(int id_asiento)
/*     */   {
/* 103 */     this.id_asiento = id_asiento;
/*     */   }
/*     */ 
/*     */   public double getImporte()
/*     */   {
/* 110 */     return this.importe;
/*     */   }
/*     */ 
/*     */   public void setImporte(double importe)
/*     */   {
/* 117 */     this.importe = importe;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 122 */     return this.cuenta + ", " + this.concepto + ", " + this.DH + ", " + this.importe;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.TipoApunte
 * JD-Core Version:    0.6.2
 */