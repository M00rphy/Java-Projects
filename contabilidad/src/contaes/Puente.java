/*     */ package contaes;
/*     */ 
/*     */ public class Puente
/*     */ {
/*     */   private int asiento;
/*     */   private int control;
/*     */   private int empresa;
/*     */   private String prefijo;
/*     */   private String cuentaStr;
/*     */   private String factura;
/*     */   private String usuario;
/*     */   private String password;
/*     */   private String direccionIP;
/*     */   private String ejercicio;
/*     */   private String facturacion;
/*     */   private boolean modoPOS;
/*     */   private boolean registro;
/*     */ 
/*     */   public Puente()
/*     */   {
/*  33 */     this.control = 0;
/*     */ 
/*  40 */     this.asiento = 0;
/*  41 */     this.empresa = 1;
/*  42 */     this.usuario = "";
/*  43 */     this.password = "";
/*  44 */     this.direccionIP = "";
/*  45 */     this.ejercicio = "";
/*  46 */     this.cuentaStr = "";
/*  47 */     this.factura = "NEW";
/*  48 */     this.prefijo = "";
/*  49 */     this.facturacion = "";
/*  50 */     this.modoPOS = false;
/*  51 */     this.registro = false;
/*     */   }
/*     */ 
/*     */   public int getAsiento()
/*     */   {
/*  58 */     return this.asiento;
/*     */   }
/*     */ 
/*     */   public void setAsiento(int asiento)
/*     */   {
/*  65 */     this.asiento = asiento;
/*     */   }
/*     */ 
/*     */   public int getControl()
/*     */   {
/*  72 */     return this.control;
/*     */   }
/*     */ 
/*     */   public void setControl(int control)
/*     */   {
/*  79 */     this.control = control;
/*     */   }
/*     */ 
/*     */   public String getCuentaStr()
/*     */   {
/*  86 */     return this.cuentaStr;
/*     */   }
/*     */ 
/*     */   public void setCuentaStr(String cuentaStr)
/*     */   {
/*  93 */     this.cuentaStr = cuentaStr;
/*     */   }
/*     */ 
/*     */   public String getEjercicio()
/*     */   {
/* 100 */     return this.ejercicio;
/*     */   }
/*     */ 
/*     */   public void setEjercicio(String ejercicio)
/*     */   {
/* 107 */     this.ejercicio = ejercicio;
/*     */   }
/*     */ 
/*     */   public int getEmpresa()
/*     */   {
/* 114 */     return this.empresa;
/*     */   }
/*     */ 
/*     */   public void setEmpresa(int empresa)
/*     */   {
/* 121 */     this.empresa = empresa;
/*     */   }
/*     */ 
/*     */   public String getFactura()
/*     */   {
/* 128 */     return this.factura;
/*     */   }
/*     */ 
/*     */   public void setFactura(String factura)
/*     */   {
/* 135 */     this.factura = factura;
/*     */   }
/*     */ 
/*     */   public String getPassword()
/*     */   {
/* 142 */     return this.password;
/*     */   }
/*     */ 
/*     */   public void setPassword(String password)
/*     */   {
/* 149 */     this.password = password;
/*     */   }
/*     */ 
/*     */   public String getDireccionIP() {
/* 153 */     return this.direccionIP;
/*     */   }
/*     */ 
/*     */   public void setDireccionIP(String direccionIP) {
/* 157 */     this.direccionIP = direccionIP;
/*     */   }
/*     */ 
/*     */   public String getPrefijo()
/*     */   {
/* 164 */     return this.prefijo;
/*     */   }
/*     */ 
/*     */   public void setPrefijo(String prefijo)
/*     */   {
/* 171 */     this.prefijo = prefijo;
/*     */   }
/*     */ 
/*     */   public String getUsuario()
/*     */   {
/* 178 */     return this.usuario;
/*     */   }
/*     */ 
/*     */   public void setUsuario(String usuario)
/*     */   {
/* 185 */     this.usuario = usuario;
/*     */   }
/*     */ 
/*     */   public String getFacturacion() {
/* 189 */     return this.facturacion;
/*     */   }
/*     */ 
/*     */   public void setFacturacion(String facturacion) {
/* 193 */     this.facturacion = facturacion;
/*     */   }
/*     */ 
/*     */   public boolean isModoPOS() {
/* 197 */     return this.modoPOS;
/*     */   }
/*     */ 
/*     */   public void setModoPOS(boolean modoPOS) {
/* 201 */     this.modoPOS = modoPOS;
/*     */   }
/*     */ 
/*     */   public boolean isRegistro() {
/* 205 */     return this.registro;
/*     */   }
/*     */ 
/*     */   public void setRegistro(boolean registro) {
/* 209 */     this.registro = registro;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.Puente
 * JD-Core Version:    0.6.2
 */