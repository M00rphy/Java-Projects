/*     */ package contaes.dialogosAuxiliares;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.Puente;
/*     */ import contaes.auxiliar.CampoCuenta;
/*     */ import contaes.manejoDatos.ManejoSubcuentas;
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Frame;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Point;
/*     */ import java.awt.event.KeyEvent;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.text.JTextComponent;
/*     */ 
/*     */ public class BusquedaParaCuentas extends BusquedaBase
/*     */ {
/*  46 */   private JPanel jContentPane = null;
/*  47 */   private JLabel jLabel = null;
/*  48 */   private CampoCuenta comienzo = null;
/*  49 */   private CampoCuenta finaliza = null;
/*     */   private ManejoSubcuentas subcuentaM;
/*     */ 
/*     */   public BusquedaParaCuentas()
/*     */   {
/*  56 */     this(new Frame(), Mensajes.getString("selBu"), true);
/*     */   }
/*     */ 
/*     */   public BusquedaParaCuentas(Frame owner, String title, boolean modal) {
/*  60 */     super(owner, title, modal);
/*  61 */     initialize();
/*     */   }
/*     */ 
/*     */   private void initialize()
/*     */   {
/*  70 */     this.subcuentaM = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  71 */     setSize(300, 200);
/*  72 */     setContentPane(getJContentPane());
/*     */   }
/*     */ 
/*     */   private JPanel getJContentPane()
/*     */   {
/*  81 */     if (this.jContentPane == null) {
/*  82 */       GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
/*  83 */       gridBagConstraints6.fill = 2;
/*  84 */       gridBagConstraints6.gridy = 3;
/*  85 */       gridBagConstraints6.weightx = 1.0D;
/*  86 */       gridBagConstraints6.insets = new Insets(10, 5, 0, 5);
/*  87 */       gridBagConstraints6.gridx = 0;
/*  88 */       GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
/*  89 */       gridBagConstraints5.gridx = 1;
/*  90 */       gridBagConstraints5.insets = new Insets(0, 0, 0, 0);
/*  91 */       gridBagConstraints5.anchor = 10;
/*  92 */       gridBagConstraints5.gridy = 1;
/*  93 */       GridBagConstraints gridBagConstraints41 = new GridBagConstraints();
/*  94 */       gridBagConstraints41.gridx = 0;
/*  95 */       gridBagConstraints41.insets = new Insets(5, 0, 5, 0);
/*  96 */       gridBagConstraints41.gridwidth = 2;
/*  97 */       gridBagConstraints41.gridy = 5;
/*  98 */       GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
/*  99 */       gridBagConstraints31.gridx = 1;
/* 100 */       gridBagConstraints31.insets = new Insets(7, 0, 0, 0);
/* 101 */       gridBagConstraints31.gridy = 4;
/* 102 */       GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
/* 103 */       gridBagConstraints21.gridx = 0;
/* 104 */       gridBagConstraints21.insets = new Insets(7, 0, 0, 0);
/* 105 */       gridBagConstraints21.gridy = 4;
/* 106 */       GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
/* 107 */       gridBagConstraints11.fill = 2;
/* 108 */       gridBagConstraints11.gridy = 3;
/* 109 */       gridBagConstraints11.weightx = 1.0D;
/* 110 */       gridBagConstraints11.insets = new Insets(10, 5, 0, 5);
/* 111 */       gridBagConstraints11.gridx = 1;
/* 112 */       GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
/* 113 */       gridBagConstraints4.fill = 2;
/* 114 */       gridBagConstraints4.gridy = 3;
/* 115 */       gridBagConstraints4.weightx = 1.0D;
/* 116 */       gridBagConstraints4.insets = new Insets(10, 5, 0, 5);
/* 117 */       gridBagConstraints4.gridx = 0;
/* 118 */       GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
/* 119 */       gridBagConstraints3.gridx = 1;
/* 120 */       gridBagConstraints3.insets = new Insets(3, 0, 0, 0);
/* 121 */       gridBagConstraints3.anchor = 10;
/* 122 */       gridBagConstraints3.gridy = 2;
/* 123 */       GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
/* 124 */       gridBagConstraints2.gridx = 0;
/* 125 */       gridBagConstraints2.insets = new Insets(3, 0, 0, 0);
/* 126 */       gridBagConstraints2.anchor = 10;
/* 127 */       gridBagConstraints2.gridy = 2;
/* 128 */       GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
/* 129 */       gridBagConstraints1.gridx = 0;
/* 130 */       gridBagConstraints1.insets = new Insets(3, 0, 0, 0);
/* 131 */       gridBagConstraints1.anchor = 10;
/* 132 */       gridBagConstraints1.gridy = 1;
/* 133 */       GridBagConstraints gridBagConstraints = new GridBagConstraints();
/* 134 */       gridBagConstraints.gridx = 0;
/* 135 */       gridBagConstraints.insets = new Insets(3, 0, 0, 0);
/* 136 */       gridBagConstraints.anchor = 10;
/* 137 */       gridBagConstraints.gridy = 0;
/* 138 */       this.jLabel = new JLabel();
/* 139 */       this.jLabel.setText(Mensajes.getString("patronBusq"));
/* 140 */       this.jContentPane = new JPanel();
/* 141 */       this.jContentPane.setLayout(new GridBagLayout());
/* 142 */       this.jContentPane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createEtchedBorder(1)));
/* 143 */       this.jContentPane.add(this.jLabel, gridBagConstraints);
/* 144 */       this.jContentPane.add(this.jLabel1, gridBagConstraints1);
/* 145 */       this.jContentPane.add(this.jLabel2, gridBagConstraints2);
/* 146 */       this.jContentPane.add(this.jLabel3, gridBagConstraints3);
/* 147 */       this.jContentPane.add(getComienzo(), gridBagConstraints4);
/* 148 */       this.jContentPane.add(getFinaliza(), gridBagConstraints11);
/* 149 */       this.jContentPane.add(getBuscar(), gridBagConstraints21);
/* 150 */       this.jContentPane.add(getSelecc(), gridBagConstraints31);
/* 151 */       this.jContentPane.add(getCancel(), gridBagConstraints41);
/* 152 */       this.jContentPane.add(this.jLabel4, gridBagConstraints5);
/* 153 */       this.jContentPane.add(getComienzo(), gridBagConstraints6);
/*     */     }
/* 155 */     return this.jContentPane;
/*     */   }
/*     */ 
/*     */   private CampoCuenta getComienzo()
/*     */   {
/* 164 */     if (this.comienzo == null) {
/* 165 */       this.comienzo = new CampoCuenta(this.subcuentaM);
/*     */     }
/* 167 */     this.comienzo.addKeyListener(this.escuchaTecla);
/* 168 */     return this.comienzo;
/*     */   }
/*     */ 
/*     */   private CampoCuenta getFinaliza()
/*     */   {
/* 177 */     if (this.finaliza == null) {
/* 178 */       this.finaliza = new CampoCuenta(this.subcuentaM);
/*     */     }
/* 180 */     this.finaliza.addKeyListener(this.escuchaTecla);
/* 181 */     return this.finaliza;
/*     */   }
/*     */ 
/*     */   protected void cambiaFoco(KeyEvent arg0) {
/* 185 */     int tecla = arg0.getKeyCode();
/* 186 */     Object campo = arg0.getSource();
/* 187 */     if (tecla == 10) {
/* 188 */       if (campo == this.comienzo) {
/* 189 */         this.finaliza.requestFocus();
/*     */       }
/* 191 */       else if (campo == this.finaliza) {
/* 192 */         this.buscar.requestFocus();
/*     */       }
/*     */     }
/* 195 */     else if ((arg0.isAltDown()) && (tecla == 67)) {
/* 196 */       String completar = AltC();
/* 197 */       if (!completar.equals(""))
/* 198 */         ((JTextComponent)campo).setText(completar);
/*     */     }
/*     */   }
/*     */ 
/*     */   private String AltC() {
/* 203 */     MostrarCuentas dlg2 = new MostrarCuentas(this, Mensajes.getString("cuentas"), true);
/* 204 */     Dimension dlgSize = dlg2.getPreferredSize();
/* 205 */     Dimension frmSize = getSize();
/* 206 */     Point loc = getLocation();
/* 207 */     dlg2.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
/*     */ 
/* 209 */     dlg2.setVisible(true);
/* 210 */     return dlg2.Seleccion();
/*     */   }
/*     */ 
/*     */   protected void buscar() {
/* 214 */     setInicio(this.comienzo.getText());
/* 215 */     if (!getInicio().equals("")) {
/* 216 */       setAccion(true);
/* 217 */       setBusqueda(true);
/*     */     }
/* 219 */     dispose();
/*     */   }
/*     */ 
/*     */   protected void seleccionar() {
/* 223 */     setInicio(this.comienzo.getText());
/* 224 */     setFin(this.finaliza.getText());
/* 225 */     if (!getInicio().equals("")) {
/* 226 */       setAccion(true);
/* 227 */       setBusqueda(false);
/*     */     }
/* 229 */     dispose();
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.dialogosAuxiliares.BusquedaParaCuentas
 * JD-Core Version:    0.6.2
 */