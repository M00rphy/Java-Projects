/*     */ package contaes.auxiliar;
/*     */ 
/*     */ import contaes.manejoDatos.ManejoSubcuentas;
/*     */ import contaes.manejoDatos.TipoSubcuenta;
/*     */ import javax.swing.InputVerifier;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JTextField;
/*     */ 
/*     */ public class ComprobarCuenta extends InputVerifier
/*     */ {
/*     */   private ManejoSubcuentas subcuentaM;
/*  40 */   private String nombre = null;
/*  41 */   private double saldo = 0.0D;
/*  42 */   private Integer origen = null;
/*     */ 
/*     */   public ComprobarCuenta(ManejoSubcuentas subcuentaM)
/*     */   {
/*  38 */     this.subcuentaM = subcuentaM;
/*     */   }
/*     */ 
/*     */   public boolean verify(JComponent arg0)
/*     */   {
/*  45 */     JTextField campo = (JTextField)arg0;
/*  46 */     String numCuentaPrevio = campo.getText();
/*  47 */     if (numCuentaPrevio.equals("")) {
/*  48 */       setNombre("");
/*  49 */       setSaldo(0.0D);
/*  50 */       return true;
/*     */     }
/*  52 */     campo.setText(completarCuenta(numCuentaPrevio));
/*  53 */     TipoSubcuenta cuentaA = this.subcuentaM.datos(Integer.parseInt(campo.getText()));
/*  54 */     if (cuentaA == null) {
/*  55 */       if (campo.getText().equals("0")) {
/*  56 */         campo.setText(numCuentaPrevio);
/*     */       }
/*  58 */       return false;
/*     */     }
/*  60 */     setNombre(cuentaA.getNombre());
/*  61 */     setSaldo(cuentaA.getSaldo());
/*  62 */     setOrigen(this.subcuentaM.getOrigen(Integer.parseInt(campo.getText())));
/*  63 */     return true;
/*     */   }
/*     */ 
/*     */   private String completarCuenta(String texto) {
/*  67 */     int largo = texto.length();
/*  68 */     if (largo > 0) {
/*  69 */       if (esNumero(texto.substring(0, 1))) {
/*  70 */         if (largo <= 8) {
/*  71 */           String ceros = "";
/*  72 */           for (int x = largo; x < 9; x++) {
/*  73 */             ceros = ceros + "0";
/*     */           }
/*  75 */           if (texto.indexOf(".") > 0)
/*  76 */             texto = texto.replaceFirst("[.]", ceros);
/*     */           else
/*  78 */             texto = (texto + ceros).substring(0, 8);
/*     */         }
/*     */       }
/*     */       else {
/*  82 */         texto = this.subcuentaM.buscarNumero(texto);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  94 */     return texto;
/*     */   }
/*     */ 
/*     */   public String getNombre() {
/*  98 */     return this.nombre;
/*     */   }
/*     */ 
/*     */   private void setNombre(String nombre) {
/* 102 */     this.nombre = nombre;
/*     */   }
/*     */ 
/*     */   public double getSaldo() {
/* 106 */     return this.saldo;
/*     */   }
/*     */ 
/*     */   private void setSaldo(double saldo) {
/* 110 */     this.saldo = saldo;
/*     */   }
/*     */ 
/*     */   public Integer getOrigen() {
/* 114 */     return this.origen;
/*     */   }
/*     */ 
/*     */   public void setOrigen(Integer origen) {
/* 118 */     this.origen = origen;
/*     */   }
/*     */ 
/*     */   private boolean esNumero(String cadena) {
/*     */     try {
/* 123 */       Integer.parseInt(cadena);
/* 124 */       return true; } catch (NumberFormatException nfe) {
/*     */     }
/* 126 */     return false;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.auxiliar.ComprobarCuenta
 * JD-Core Version:    0.6.2
 */