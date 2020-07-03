/*     */ package contaes.cuentas;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.Puente;
/*     */ import contaes.auxiliar.CampoCuenta;
/*     */ import contaes.dialogosAuxiliares.MostrarCuentas;
/*     */ import contaes.manejoDatos.ManejoSubcuentas;
/*     */ import contaes.manejoDatos.TipoSubcuenta;
/*     */ import contaes.manejoDatos.auxiliar.AcumuladosMensuales;
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Frame;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.HeadlessException;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Point;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.KeyListener;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.text.JTextComponent;
/*     */ 
/*     */ public class ComparativaCuentasDlg extends JDialog
/*     */   implements KeyListener
/*     */ {
/*  55 */   private JPanel jContentPane = null;
/*  56 */   private JLabel jLabel = null;
/*  57 */   private CampoCuenta cuenta1 = null;
/*  58 */   private JLabel jLabel1 = null;
/*  59 */   private JLabel jLabel2 = null;
/*  60 */   private JLabel jLabel3 = null;
/*  61 */   private CampoCuenta cuenta2 = null;
/*  62 */   private CampoCuenta cuenta3 = null;
/*  63 */   private CampoCuenta cuenta4 = null;
/*  64 */   private JButton bAceptar = null;
/*  65 */   private JButton bCancelar = null;
/*  66 */   private AcumuladosMensuales[] acCuenta = null;
/*  67 */   private String[] cuenta = null;
/*  68 */   private String[] nombreCuenta = null;
/*     */   private int nCta;
/*  70 */   private boolean hayDatos = false;
/*     */   private ManejoSubcuentas subcuentaM;
/*     */ 
/*     */   public ComparativaCuentasDlg()
/*     */     throws HeadlessException
/*     */   {
/*  75 */     initialize();
/*     */   }
/*     */ 
/*     */   public ComparativaCuentasDlg(Frame arg0, boolean arg1) throws HeadlessException
/*     */   {
/*  80 */     super(arg0, arg1);
/*  81 */     initialize();
/*     */   }
/*     */ 
/*     */   private void initialize()
/*     */   {
/*  90 */     this.subcuentaM = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  91 */     setSize(300, 200);
/*  92 */     setTitle(Mensajes.getString("comparativaGrafica"));
/*  93 */     setContentPane(getJContentPane());
/*     */   }
/*     */ 
/*     */   private JPanel getJContentPane()
/*     */   {
/* 102 */     if (this.jContentPane == null) {
/* 103 */       GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
/* 104 */       gridBagConstraints9.gridx = 1;
/* 105 */       gridBagConstraints9.insets = new Insets(10, 10, 10, 10);
/* 106 */       gridBagConstraints9.gridy = 4;
/* 107 */       GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
/* 108 */       gridBagConstraints8.gridx = 0;
/* 109 */       gridBagConstraints8.insets = new Insets(10, 10, 10, 10);
/* 110 */       gridBagConstraints8.gridy = 4;
/* 111 */       GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
/* 112 */       gridBagConstraints7.fill = 2;
/* 113 */       gridBagConstraints7.gridy = 3;
/* 114 */       gridBagConstraints7.weightx = 1.0D;
/* 115 */       gridBagConstraints7.insets = new Insets(5, 10, 5, 5);
/* 116 */       gridBagConstraints7.gridx = 1;
/* 117 */       GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
/* 118 */       gridBagConstraints6.fill = 2;
/* 119 */       gridBagConstraints6.gridy = 2;
/* 120 */       gridBagConstraints6.weightx = 1.0D;
/* 121 */       gridBagConstraints6.insets = new Insets(5, 10, 5, 5);
/* 122 */       gridBagConstraints6.gridx = 1;
/* 123 */       GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
/* 124 */       gridBagConstraints5.fill = 2;
/* 125 */       gridBagConstraints5.gridy = 1;
/* 126 */       gridBagConstraints5.weightx = 1.0D;
/* 127 */       gridBagConstraints5.insets = new Insets(5, 10, 5, 5);
/* 128 */       gridBagConstraints5.gridx = 1;
/* 129 */       GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
/* 130 */       gridBagConstraints4.gridx = 0;
/* 131 */       gridBagConstraints4.insets = new Insets(5, 5, 5, 10);
/* 132 */       gridBagConstraints4.gridy = 3;
/* 133 */       this.jLabel3 = new JLabel();
/* 134 */       this.jLabel3.setText(Mensajes.getString("cuartaA") + " " + Mensajes.getString("cuenta"));
/* 135 */       GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
/* 136 */       gridBagConstraints3.gridx = 0;
/* 137 */       gridBagConstraints3.insets = new Insets(5, 5, 5, 10);
/* 138 */       gridBagConstraints3.gridy = 2;
/* 139 */       this.jLabel2 = new JLabel();
/* 140 */       this.jLabel2.setText(Mensajes.getString("terceraA") + " " + Mensajes.getString("cuenta"));
/* 141 */       GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
/* 142 */       gridBagConstraints2.gridx = 0;
/* 143 */       gridBagConstraints2.insets = new Insets(5, 5, 5, 10);
/* 144 */       gridBagConstraints2.gridy = 1;
/* 145 */       this.jLabel1 = new JLabel();
/* 146 */       this.jLabel1.setText(Mensajes.getString("segundaA") + " " + Mensajes.getString("cuenta"));
/* 147 */       GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
/* 148 */       gridBagConstraints1.fill = 2;
/* 149 */       gridBagConstraints1.gridy = 0;
/* 150 */       gridBagConstraints1.weightx = 1.0D;
/* 151 */       gridBagConstraints1.insets = new Insets(10, 10, 5, 5);
/* 152 */       gridBagConstraints1.gridx = 1;
/* 153 */       GridBagConstraints gridBagConstraints = new GridBagConstraints();
/* 154 */       gridBagConstraints.gridx = 0;
/* 155 */       gridBagConstraints.insets = new Insets(10, 5, 5, 10);
/* 156 */       gridBagConstraints.gridy = 0;
/* 157 */       this.jLabel = new JLabel();
/* 158 */       this.jLabel.setText(Mensajes.getString("primeraA") + " " + Mensajes.getString("cuenta"));
/* 159 */       this.jContentPane = new JPanel();
/* 160 */       this.jContentPane.setLayout(new GridBagLayout());
/*     */ 
/* 163 */       this.jContentPane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createEtchedBorder(1)));
/* 164 */       this.jContentPane.add(this.jLabel, gridBagConstraints);
/* 165 */       this.jContentPane.add(getCuenta1(), gridBagConstraints1);
/* 166 */       this.jContentPane.add(this.jLabel1, gridBagConstraints2);
/* 167 */       this.jContentPane.add(this.jLabel2, gridBagConstraints3);
/* 168 */       this.jContentPane.add(this.jLabel3, gridBagConstraints4);
/* 169 */       this.jContentPane.add(getCuenta2(), gridBagConstraints5);
/* 170 */       this.jContentPane.add(getCuenta3(), gridBagConstraints6);
/* 171 */       this.jContentPane.add(getCuenta4(), gridBagConstraints7);
/* 172 */       this.jContentPane.add(getBAceptar(), gridBagConstraints8);
/* 173 */       this.jContentPane.add(getBCancelar(), gridBagConstraints9);
/*     */     }
/* 175 */     return this.jContentPane;
/*     */   }
/*     */ 
/*     */   private CampoCuenta getCuenta1()
/*     */   {
/* 184 */     if (this.cuenta1 == null) {
/* 185 */       this.cuenta1 = new CampoCuenta(this.subcuentaM);
/* 186 */       this.cuenta1.addKeyListener(this);
/*     */     }
/* 188 */     return this.cuenta1;
/*     */   }
/*     */ 
/*     */   private CampoCuenta getCuenta2()
/*     */   {
/* 197 */     if (this.cuenta2 == null) {
/* 198 */       this.cuenta2 = new CampoCuenta(this.subcuentaM);
/* 199 */       this.cuenta2.addKeyListener(this);
/*     */     }
/* 201 */     return this.cuenta2;
/*     */   }
/*     */ 
/*     */   private CampoCuenta getCuenta3()
/*     */   {
/* 210 */     if (this.cuenta3 == null) {
/* 211 */       this.cuenta3 = new CampoCuenta(this.subcuentaM);
/* 212 */       this.cuenta3.addKeyListener(this);
/*     */     }
/* 214 */     return this.cuenta3;
/*     */   }
/*     */ 
/*     */   private CampoCuenta getCuenta4()
/*     */   {
/* 223 */     if (this.cuenta4 == null) {
/* 224 */       this.cuenta4 = new CampoCuenta(this.subcuentaM);
/* 225 */       this.cuenta4.addKeyListener(this);
/*     */     }
/* 227 */     return this.cuenta4;
/*     */   }
/*     */ 
/*     */   private JButton getBAceptar()
/*     */   {
/* 236 */     if (this.bAceptar == null) {
/* 237 */       this.bAceptar = new JButton();
/* 238 */       this.bAceptar.setHorizontalTextPosition(2);
/* 239 */       this.bAceptar.setText(Mensajes.getString("aceptar"));
/* 240 */       this.bAceptar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/ok.png")));
/* 241 */       this.bAceptar.addActionListener(new ActionListener()
/*     */       {
/*     */         public void actionPerformed(ActionEvent e)
/*     */         {
/* 245 */           ComparativaCuentasDlg.this.crearDatos();
/* 246 */           ComparativaCuentasDlg.this.dispose();
/*     */         }
/*     */       });
/*     */     }
/* 250 */     return this.bAceptar;
/*     */   }
/*     */ 
/*     */   private JButton getBCancelar()
/*     */   {
/* 259 */     if (this.bCancelar == null) {
/* 260 */       this.bCancelar = new JButton();
/* 261 */       this.bCancelar.setText(Mensajes.getString("cancelar"));
/* 262 */       this.bCancelar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/cancel.png")));
/* 263 */       this.bCancelar.setHorizontalTextPosition(2);
/* 264 */       this.bCancelar.addActionListener(new ActionListener()
/*     */       {
/*     */         public void actionPerformed(ActionEvent e)
/*     */         {
/* 268 */           ComparativaCuentasDlg.this.dispose();
/*     */         }
/*     */       });
/*     */     }
/* 272 */     return this.bCancelar;
/*     */   }
/*     */ 
/*     */   private void crearDatos()
/*     */   {
/* 280 */     this.nCta = 0;
/* 281 */     this.cuenta = new String[4];
/* 282 */     this.nombreCuenta = new String[4];
/*     */ 
/* 284 */     TipoSubcuenta subcTemp = null;
/* 285 */     String temporal = this.cuenta1.getText();
/* 286 */     if (!temporal.equals("")) {
/* 287 */       subcTemp = this.subcuentaM.datos(Integer.parseInt(temporal));
/* 288 */       if (subcTemp != null) {
/* 289 */         this.nombreCuenta[this.nCta] = (subcTemp.getNombre().length() > 20 ? subcTemp.getNombre().substring(0, 15) : subcTemp.getNombre());
/* 290 */         this.cuenta[this.nCta] = temporal;
/* 291 */         this.nCta += 1;
/*     */       }
/* 293 */       subcTemp = null;
/*     */     }
/* 295 */     temporal = this.cuenta2.getText();
/* 296 */     if (!temporal.equals("")) {
/* 297 */       subcTemp = this.subcuentaM.datos(Integer.parseInt(temporal));
/* 298 */       if (subcTemp != null) {
/* 299 */         this.nombreCuenta[this.nCta] = (subcTemp.getNombre().length() > 20 ? subcTemp.getNombre().substring(0, 15) : subcTemp.getNombre());
/* 300 */         this.cuenta[this.nCta] = temporal;
/* 301 */         this.nCta += 1;
/*     */       }
/* 303 */       subcTemp = null;
/*     */     }
/* 305 */     temporal = this.cuenta3.getText();
/* 306 */     if (!temporal.equals("")) {
/* 307 */       subcTemp = this.subcuentaM.datos(Integer.parseInt(temporal));
/* 308 */       if (subcTemp != null) {
/* 309 */         this.nombreCuenta[this.nCta] = (subcTemp.getNombre().length() > 20 ? subcTemp.getNombre().substring(0, 15) : subcTemp.getNombre());
/* 310 */         this.cuenta[this.nCta] = temporal;
/* 311 */         this.nCta += 1;
/*     */       }
/* 313 */       subcTemp = null;
/*     */     }
/* 315 */     temporal = this.cuenta4.getText();
/* 316 */     if (!temporal.equals("")) {
/* 317 */       subcTemp = this.subcuentaM.datos(Integer.parseInt(temporal));
/* 318 */       if (subcTemp != null) {
/* 319 */         this.nombreCuenta[this.nCta] = (subcTemp.getNombre().length() > 20 ? subcTemp.getNombre().substring(0, 15) : subcTemp.getNombre());
/* 320 */         this.cuenta[this.nCta] = temporal;
/* 321 */         this.nCta += 1;
/*     */       }
/*     */     }
/* 324 */     if (this.nCta > 0) {
/* 325 */       this.acCuenta = new AcumuladosMensuales[this.nCta];
/* 326 */       for (int x = 0; x < this.nCta; x++) {
/* 327 */         this.acCuenta[x] = new AcumuladosMensuales(Inicio.getCEmpresa(), this.cuenta[x], Inicio.p.getEjercicio());
/*     */       }
/* 329 */       this.hayDatos = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean isHayDatos()
/*     */   {
/* 337 */     return this.hayDatos;
/*     */   }
/*     */ 
/*     */   public int getNCta()
/*     */   {
/* 344 */     return this.nCta;
/*     */   }
/*     */ 
/*     */   public String[] getCuenta()
/*     */   {
/* 351 */     return this.cuenta;
/*     */   }
/*     */ 
/*     */   public AcumuladosMensuales[] getAcCuenta()
/*     */   {
/* 358 */     return this.acCuenta;
/*     */   }
/*     */ 
/*     */   public double[][] getDatosSaldos()
/*     */   {
/* 367 */     double[][] datos = new double[this.nCta][14];
/* 368 */     for (int x = 0; x < this.nCta; x++) {
/* 369 */       for (int y = 0; y < 14; y++)
/*     */       {
/* 371 */         datos[x][y] = this.acCuenta[x].getS()[y];
/*     */       }
/*     */     }
/* 374 */     return datos;
/*     */   }
/*     */ 
/*     */   public String[] getNombreCuenta() {
/* 378 */     return this.nombreCuenta;
/*     */   }
/*     */ 
/*     */   public void keyPressed(KeyEvent arg0)
/*     */   {
/* 383 */     int tecla = arg0.getKeyCode();
/* 384 */     Object campo = arg0.getSource();
/* 385 */     if ((arg0.isAltDown()) && (tecla == 67)) {
/* 386 */       MostrarCuentas dlg2 = new MostrarCuentas(this, Mensajes.getString("cuentas"), true);
/* 387 */       Dimension dlgSize = dlg2.getPreferredSize();
/* 388 */       Dimension frmSize = getSize();
/* 389 */       Point loc = getLocation();
/* 390 */       dlg2.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
/*     */ 
/* 392 */       dlg2.setVisible(true);
/* 393 */       if (!dlg2.Seleccion().equals("")) {
/* 394 */         ((JTextComponent)campo).setText(dlg2.Seleccion());
/*     */       }
/*     */     }
/* 397 */     if (tecla == 10)
/* 398 */       if (campo == this.cuenta1)
/* 399 */         this.cuenta2.requestFocus();
/* 400 */       else if (campo == this.cuenta2)
/* 401 */         this.cuenta3.requestFocus();
/* 402 */       else if (campo == this.cuenta3)
/* 403 */         this.cuenta4.requestFocus();
/* 404 */       else if (campo == this.cuenta4)
/* 405 */         this.bAceptar.requestFocus();
/*     */   }
/*     */ 
/*     */   public void keyReleased(KeyEvent arg0)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void keyTyped(KeyEvent arg0)
/*     */   {
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.cuentas.ComparativaCuentasDlg
 * JD-Core Version:    0.6.2
 */