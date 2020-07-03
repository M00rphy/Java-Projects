/*     */ package contaes.manejoDatos;
/*     */ 
/*     */ public class TipoSubcuenta
/*     */ {
/*     */   private int codigo;
/*     */   private String nombre;
/*     */   private String gbalance;
/*     */   private double debe;
/*     */   private double haber;
/*     */   private double saldo;
/*     */ 
/*     */   public TipoSubcuenta()
/*     */   {
/*     */   }
/*     */ 
/*     */   public TipoSubcuenta(int codigo, String nombre, String gbalance, double debe, double haber, double saldo)
/*     */   {
/*  28 */     this.codigo = codigo;
/*  29 */     this.nombre = nombre;
/*  30 */     this.gbalance = gbalance;
/*  31 */     this.debe = debe;
/*  32 */     this.haber = haber;
/*  33 */     this.saldo = saldo;
/*     */   }
/*     */ 
/*     */   public int getCodigo()
/*     */   {
/*  40 */     return this.codigo;
/*     */   }
/*     */ 
/*     */   public void setCodigo(int codigo)
/*     */   {
/*  47 */     this.codigo = codigo;
/*     */   }
/*     */ 
/*     */   public double getDebe()
/*     */   {
/*  54 */     return this.debe;
/*     */   }
/*     */ 
/*     */   public void setDebe(double debe)
/*     */   {
/*  61 */     this.debe = debe;
/*     */   }
/*     */ 
/*     */   public String getGbalance()
/*     */   {
/*  68 */     return this.gbalance;
/*     */   }
/*     */ 
/*     */   public void setGbalance(String gbalance)
/*     */   {
/*  75 */     this.gbalance = gbalance;
/*     */   }
/*     */ 
/*     */   public double getHaber()
/*     */   {
/*  82 */     return this.haber;
/*     */   }
/*     */ 
/*     */   public void setHaber(double haber)
/*     */   {
/*  89 */     this.haber = haber;
/*     */   }
/*     */ 
/*     */   public String getNombre()
/*     */   {
/*  96 */     return this.nombre;
/*     */   }
/*     */ 
/*     */   public void setNombre(String nombre)
/*     */   {
/* 103 */     this.nombre = nombre;
/*     */   }
/*     */ 
/*     */   public double getSaldo()
/*     */   {
/* 110 */     return this.saldo;
/*     */   }
/*     */ 
/*     */   public void setSaldo(double saldo)
/*     */   {
/* 117 */     this.saldo = saldo;
/*     */   }
/*     */ 
/*     */   public String toString() {
/* 121 */     return this.codigo + " - " + this.nombre;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 126 */     if (obj == null) {
/* 127 */       return false;
/*     */     }
/* 129 */     if (getClass() != obj.getClass()) {
/* 130 */       return false;
/*     */     }
/* 132 */     TipoSubcuenta other = (TipoSubcuenta)obj;
/* 133 */     if (this.codigo != other.codigo) {
/* 134 */       return false;
/*     */     }
/* 136 */     return true;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 141 */     int hash = 7;
/* 142 */     hash = 23 * hash + this.codigo;
/* 143 */     return hash;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.TipoSubcuenta
 * JD-Core Version:    0.6.2
 */