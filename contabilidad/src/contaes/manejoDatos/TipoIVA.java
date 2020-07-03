/*     */ package contaes.manejoDatos;
/*     */ 
/*     */ public class TipoIVA
/*     */ {
/*     */   private int id;
/*     */   private String nombre;
/*     */   private double iva;
/*     */   private double re;
/*     */   private int cuentaRep;
/*     */   private int cuentaSop;
/*     */   private int cuentaRE;
/*     */   private int cuentaRepIntra;
/*     */   private int cuentaSopIntra;
/*     */   private int cuentaImport;
/*     */ 
/*     */   public TipoIVA(int id, String nombre, double iva, double re, int cuentaRep, int cuentaSop, int cuentaRE, int cuentaRepIntra, int cuentaSopIntra, int cuentaImport)
/*     */   {
/*  26 */     this.id = id;
/*  27 */     this.nombre = nombre;
/*  28 */     this.iva = iva;
/*  29 */     this.re = re;
/*  30 */     this.cuentaRep = cuentaRep;
/*  31 */     this.cuentaSop = cuentaSop;
/*  32 */     this.cuentaRE = cuentaRE;
/*  33 */     this.cuentaRepIntra = cuentaRepIntra;
/*  34 */     this.cuentaSopIntra = cuentaSopIntra;
/*  35 */     this.cuentaImport = cuentaImport;
/*     */   }
/*     */ 
/*     */   public TipoIVA()
/*     */   {
/*     */   }
/*     */ 
/*     */   public int getCuentaImport() {
/*  43 */     return this.cuentaImport;
/*     */   }
/*     */ 
/*     */   public void setCuentaImport(int cuentaImport) {
/*  47 */     this.cuentaImport = cuentaImport;
/*     */   }
/*     */ 
/*     */   public int getCuentaRE() {
/*  51 */     return this.cuentaRE;
/*     */   }
/*     */ 
/*     */   public void setCuentaRE(int cuentaRE) {
/*  55 */     this.cuentaRE = cuentaRE;
/*     */   }
/*     */ 
/*     */   public int getCuentaRep() {
/*  59 */     return this.cuentaRep;
/*     */   }
/*     */ 
/*     */   public void setCuentaRep(int cuentaRep) {
/*  63 */     this.cuentaRep = cuentaRep;
/*     */   }
/*     */ 
/*     */   public int getCuentaRepIntra() {
/*  67 */     return this.cuentaRepIntra;
/*     */   }
/*     */ 
/*     */   public void setCuentaRepIntra(int cuentaRepIntra) {
/*  71 */     this.cuentaRepIntra = cuentaRepIntra;
/*     */   }
/*     */ 
/*     */   public int getCuentaSop() {
/*  75 */     return this.cuentaSop;
/*     */   }
/*     */ 
/*     */   public void setCuentaSop(int cuentaSop) {
/*  79 */     this.cuentaSop = cuentaSop;
/*     */   }
/*     */ 
/*     */   public int getCuentaSopIntra() {
/*  83 */     return this.cuentaSopIntra;
/*     */   }
/*     */ 
/*     */   public void setCuentaSopIntra(int cuentaSopIntra) {
/*  87 */     this.cuentaSopIntra = cuentaSopIntra;
/*     */   }
/*     */ 
/*     */   public int getId() {
/*  91 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(int id) {
/*  95 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public double getIva() {
/*  99 */     return this.iva;
/*     */   }
/*     */ 
/*     */   public void setIva(double iva) {
/* 103 */     this.iva = iva;
/*     */   }
/*     */ 
/*     */   public String getNombre() {
/* 107 */     return this.nombre;
/*     */   }
/*     */ 
/*     */   public void setNombre(String nombre) {
/* 111 */     this.nombre = nombre;
/*     */   }
/*     */ 
/*     */   public double getRe() {
/* 115 */     return this.re;
/*     */   }
/*     */ 
/*     */   public void setRe(double re) {
/* 119 */     this.re = re;
/*     */   }
/*     */ 
/*     */   public String toString() {
/* 123 */     return this.nombre;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 128 */     if (obj == null) {
/* 129 */       return false;
/*     */     }
/* 131 */     if (getClass() != obj.getClass()) {
/* 132 */       return false;
/*     */     }
/* 134 */     TipoIVA other = (TipoIVA)obj;
/* 135 */     if (this.id != other.id) {
/* 136 */       return false;
/*     */     }
/* 138 */     if (this.nombre == null ? other.nombre != null : !this.nombre.equals(other.nombre)) {
/* 139 */       return false;
/*     */     }
/* 141 */     return true;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 146 */     int hash = 5;
/* 147 */     hash = 79 * hash + this.id;
/* 148 */     return hash;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.TipoIVA
 * JD-Core Version:    0.6.2
 */