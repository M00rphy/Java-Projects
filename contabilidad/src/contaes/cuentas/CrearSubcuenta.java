/*     */ package contaes.cuentas;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.MarcoInicio;
/*     */ import contaes.Puente;
/*     */ import contaes.auxiliar.DocNumCuenta;
/*     */ import contaes.manejoDatos.ManejoSubcuentas;
/*     */ import contaes.manejoDatos.funciones.ComprobarDato;
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.Frame;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Insets;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ 
/*     */ public class CrearSubcuenta extends JDialog
/*     */ {
/*  46 */   private JPanel jContentPane = null;
/*  47 */   private JLabel jLabel = null;
/*  48 */   private JTextField cCodigo = null;
/*  49 */   private JLabel jLabel1 = null;
/*  50 */   private JTextField cNombre = null;
/*  51 */   private JLabel jLabel2 = null;
/*  52 */   private JTextField cCodBal = null;
/*  53 */   private JButton bCrear = null;
/*  54 */   private JButton bCancelar = null;
/*     */   private boolean crear;
/*     */   private ManejoSubcuentas subcuentaM;
/*  57 */   private String subcuenta = null;
/*  58 */   private boolean cambios = false;
/*     */ 
/*     */   public CrearSubcuenta(Frame owner, String title, boolean modal, boolean crear)
/*     */   {
/*  64 */     super(owner, title, modal);
/*  65 */     this.crear = crear;
/*  66 */     initialize();
/*     */   }
/*     */ 
/*     */   public CrearSubcuenta(boolean crear)
/*     */   {
/*  76 */     this(new Frame(), Mensajes.getString("crear") + "/" + Mensajes.getString("modificar") + " " + Mensajes.getString("subcuenta"), true, crear);
/*     */   }
/*     */ 
/*     */   private void initialize()
/*     */   {
/*  85 */     this.subcuentaM = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  86 */     setSize(290, 255);
/*  87 */     setContentPane(getJContentPane());
/*     */   }
/*     */ 
/*     */   private JPanel getJContentPane()
/*     */   {
/*  96 */     if (this.jContentPane == null) {
/*  97 */       GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
/*  98 */       gridBagConstraints7.gridx = 1;
/*  99 */       gridBagConstraints7.insets = new Insets(20, 0, 10, 30);
/* 100 */       gridBagConstraints7.gridy = 6;
/* 101 */       GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
/* 102 */       gridBagConstraints6.gridx = 0;
/* 103 */       gridBagConstraints6.insets = new Insets(20, 0, 10, 0);
/* 104 */       gridBagConstraints6.gridy = 6;
/* 105 */       GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
/* 106 */       gridBagConstraints5.fill = 2;
/* 107 */       gridBagConstraints5.gridy = 5;
/* 108 */       gridBagConstraints5.weightx = 1.0D;
/* 109 */       gridBagConstraints5.insets = new Insets(0, 30, 0, 40);
/* 110 */       gridBagConstraints5.anchor = 17;
/* 111 */       gridBagConstraints5.gridx = 0;
/* 112 */       GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
/* 113 */       gridBagConstraints4.gridx = 0;
/* 114 */       gridBagConstraints4.anchor = 17;
/* 115 */       gridBagConstraints4.insets = new Insets(10, 30, 5, 0);
/* 116 */       gridBagConstraints4.gridy = 4;
/* 117 */       this.jLabel2 = new JLabel();
/* 118 */       this.jLabel2.setText(Mensajes.getString("codBalance") + ":");
/* 119 */       GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
/* 120 */       gridBagConstraints3.fill = 2;
/* 121 */       gridBagConstraints3.gridy = 3;
/* 122 */       gridBagConstraints3.weightx = 1.0D;
/* 123 */       gridBagConstraints3.insets = new Insets(0, 30, 0, 40);
/* 124 */       gridBagConstraints3.anchor = 17;
/* 125 */       gridBagConstraints3.gridwidth = 2;
/* 126 */       gridBagConstraints3.gridx = 0;
/* 127 */       GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
/* 128 */       gridBagConstraints2.gridx = 0;
/* 129 */       gridBagConstraints2.anchor = 17;
/* 130 */       gridBagConstraints2.insets = new Insets(10, 30, 5, 0);
/* 131 */       gridBagConstraints2.gridy = 2;
/* 132 */       this.jLabel1 = new JLabel();
/* 133 */       this.jLabel1.setText(Mensajes.getString("nombre") + ":");
/* 134 */       GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
/* 135 */       gridBagConstraints1.fill = 2;
/* 136 */       gridBagConstraints1.gridy = 1;
/* 137 */       gridBagConstraints1.weightx = 1.0D;
/* 138 */       gridBagConstraints1.insets = new Insets(0, 30, 0, 40);
/* 139 */       gridBagConstraints1.anchor = 17;
/* 140 */       gridBagConstraints1.gridx = 0;
/* 141 */       GridBagConstraints gridBagConstraints = new GridBagConstraints();
/* 142 */       gridBagConstraints.gridx = 0;
/* 143 */       gridBagConstraints.anchor = 17;
/* 144 */       gridBagConstraints.insets = new Insets(10, 30, 5, 0);
/* 145 */       gridBagConstraints.gridy = 0;
/* 146 */       this.jLabel = new JLabel();
/* 147 */       this.jLabel.setText(Mensajes.getString("codigo") + ":");
/* 148 */       this.jContentPane = new JPanel();
/* 149 */       this.jContentPane.setLayout(new GridBagLayout());
/* 150 */       this.jContentPane.add(this.jLabel, gridBagConstraints);
/* 151 */       this.jContentPane.add(getCCodigo(), gridBagConstraints1);
/* 152 */       this.jContentPane.add(this.jLabel1, gridBagConstraints2);
/* 153 */       this.jContentPane.add(getCNombre(), gridBagConstraints3);
/* 154 */       this.jContentPane.add(this.jLabel2, gridBagConstraints4);
/* 155 */       this.jContentPane.add(getCCodBal(), gridBagConstraints5);
/* 156 */       this.jContentPane.add(getBCrear(), gridBagConstraints6);
/* 157 */       this.jContentPane.add(getBCancelar(), gridBagConstraints7);
/*     */     }
/* 159 */     return this.jContentPane;
/*     */   }
/*     */ 
/*     */   private JTextField getCCodigo()
/*     */   {
/* 168 */     if (this.cCodigo == null) {
/* 169 */       this.cCodigo = new JTextField();
/* 170 */       this.cCodigo.setDocument(new DocNumCuenta());
/*     */     }
/* 172 */     return this.cCodigo;
/*     */   }
/*     */ 
/*     */   private JTextField getCNombre()
/*     */   {
/* 181 */     if (this.cNombre == null) {
/* 182 */       this.cNombre = new JTextField();
/*     */     }
/* 184 */     return this.cNombre;
/*     */   }
/*     */ 
/*     */   private JTextField getCCodBal()
/*     */   {
/* 193 */     if (this.cCodBal == null) {
/* 194 */       this.cCodBal = new JTextField();
/*     */     }
/* 196 */     return this.cCodBal;
/*     */   }
/*     */ 
/*     */   private JButton getBCrear()
/*     */   {
/* 205 */     if (this.bCrear == null) {
/* 206 */       this.bCrear = new JButton();
/* 207 */       this.bCrear.setHorizontalTextPosition(2);
/* 208 */       if (this.crear) {
/* 209 */         this.bCrear.setText(Mensajes.getString("crear"));
/* 210 */         this.bCrear.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/new.png")));
/*     */       } else {
/* 212 */         this.bCrear.setText(Mensajes.getString("modificar"));
/* 213 */         this.bCrear.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/edit.png")));
/*     */       }
/* 215 */       this.bCrear.addActionListener(new ActionListener()
/*     */       {
/*     */         public void actionPerformed(ActionEvent e) {
/* 218 */           CrearSubcuenta.this.crearFicha();
/*     */         }
/*     */       });
/*     */     }
/* 222 */     return this.bCrear;
/*     */   }
/*     */ 
/*     */   private JButton getBCancelar()
/*     */   {
/* 231 */     if (this.bCancelar == null) {
/* 232 */       this.bCancelar = new JButton();
/* 233 */       this.bCancelar.setText(Mensajes.getString("cancelar"));
/* 234 */       this.bCancelar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/cancel.png")));
/* 235 */       this.bCancelar.setHorizontalTextPosition(2);
/* 236 */       this.bCancelar.addActionListener(new ActionListener()
/*     */       {
/*     */         public void actionPerformed(ActionEvent e) {
/* 239 */           CrearSubcuenta.this.dispose();
/*     */         }
/*     */       });
/*     */     }
/* 243 */     return this.bCancelar;
/*     */   }
/*     */ 
/*     */   private void crearFicha()
/*     */   {
/* 251 */     ComprobarDato dato = new ComprobarDato(this.cCodigo.getText());
/* 252 */     if ((dato.esEntero()) && (this.cCodigo.getText().length() == 8)) {
/* 253 */       if (this.crear) {
/* 254 */         if (this.subcuentaM.crear(Integer.parseInt(this.cCodigo.getText()), this.cNombre.getText(), this.cCodBal.getText()))
/*     */         {
/* 257 */           this.subcuenta = this.cCodigo.getText();
/*     */         }
/*     */       } else {
/* 260 */         this.subcuentaM.modificar(Integer.parseInt(this.cCodigo.getText()), this.cNombre.getText(), this.cCodBal.getText());
/*     */ 
/* 263 */         this.subcuenta = this.cCodigo.getText();
/*     */       }
/* 265 */       this.cambios = true;
/* 266 */       Inicio.frame.renovarTabla(3);
/*     */     }
/* 268 */     dispose();
/*     */   }
/*     */ 
/*     */   public String getSubcuenta() {
/* 272 */     return this.subcuenta;
/*     */   }
/*     */ 
/*     */   public void datos(String numero, String nombre, String cBal)
/*     */   {
/* 283 */     this.cCodigo.setText((numero + "00000000").substring(0, 8));
/* 284 */     this.cNombre.setText(nombre);
/* 285 */     this.cCodBal.setText(cBal);
/*     */   }
/*     */ 
/*     */   public boolean isCambios() {
/* 289 */     return this.cambios;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.cuentas.CrearSubcuenta
 * JD-Core Version:    0.6.2
 */