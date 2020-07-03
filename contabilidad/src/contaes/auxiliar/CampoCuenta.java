/*     */ package contaes.auxiliar;
/*     */ 
/*     */ import contaes.manejoDatos.ManejoSubcuentas;
/*     */ import java.awt.event.FocusEvent;
/*     */ import java.awt.event.FocusListener;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.text.Document;
/*     */ 
/*     */ public class CampoCuenta extends JTextField
/*     */   implements FocusListener
/*     */ {
/*  36 */   private String nombre = null;
/*  37 */   private double saldo = 0.0D;
/*  38 */   private Integer origen = null;
/*     */   private ComprobarCuenta comprobar;
/*     */ 
/*     */   public CampoCuenta()
/*     */   {
/*     */   }
/*     */ 
/*     */   public CampoCuenta(ManejoSubcuentas subcuentaM)
/*     */   {
/*  47 */     initialize(subcuentaM);
/*     */   }
/*     */ 
/*     */   public CampoCuenta(int arg0, ManejoSubcuentas subcuentaM) {
/*  51 */     super(arg0);
/*  52 */     initialize(subcuentaM);
/*     */   }
/*     */ 
/*     */   public CampoCuenta(String arg0, ManejoSubcuentas subcuentaM) {
/*  56 */     super(arg0);
/*  57 */     initialize(subcuentaM);
/*     */   }
/*     */ 
/*     */   public CampoCuenta(String arg0, int arg1, ManejoSubcuentas subcuentaM) {
/*  61 */     super(arg0, arg1);
/*  62 */     initialize(subcuentaM);
/*     */   }
/*     */ 
/*     */   public CampoCuenta(Document arg0, String arg1, int arg2, ManejoSubcuentas subcuentaM) {
/*  66 */     super(arg0, arg1, arg2);
/*  67 */     initialize(subcuentaM);
/*     */   }
/*     */ 
/*     */   private void initialize(ManejoSubcuentas subcuentaM) {
/*  71 */     this.comprobar = new ComprobarCuenta(subcuentaM);
/*  72 */     setInputVerifier(this.comprobar);
/*  73 */     addFocusListener(this);
/*     */   }
/*     */ 
/*     */   public void focusGained(FocusEvent arg0)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void focusLost(FocusEvent arg0) {
/*  81 */     setNombre(this.comprobar.getNombre());
/*  82 */     setToolTipText(this.comprobar.getNombre());
/*  83 */     setSaldo(this.comprobar.getSaldo());
/*  84 */     setOrigen(this.comprobar.getOrigen());
/*     */   }
/*     */ 
/*     */   public String getNombre() {
/*  88 */     return this.nombre;
/*     */   }
/*     */ 
/*     */   private void setNombre(String nombreCuenta) {
/*  92 */     this.nombre = nombreCuenta;
/*     */   }
/*     */ 
/*     */   public double getSaldo() {
/*  96 */     return this.saldo;
/*     */   }
/*     */ 
/*     */   private void setSaldo(double saldo) {
/* 100 */     this.saldo = saldo;
/*     */   }
/*     */ 
/*     */   public Integer getOrigen() {
/* 104 */     return this.origen;
/*     */   }
/*     */ 
/*     */   public void setOrigen(Integer origen) {
/* 108 */     this.origen = origen;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.auxiliar.CampoCuenta
 * JD-Core Version:    0.6.2
 */