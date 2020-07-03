/*     */ package contaes.informes.view;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.MarcoInicio;
/*     */ import contaes.Puente;
/*     */ import contaes.auxiliar.CampoCuenta;
/*     */ import contaes.calendario.ICalendarTextField;
/*     */ import contaes.dialogosAuxiliares.MostrarCuentas;
/*     */ import contaes.informes.control.DistribucionPartidasControl;
/*     */ import contaes.manejoDatos.ManejoSubcuentas;
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Frame;
/*     */ import java.awt.Point;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.KeyAdapter;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.ButtonGroup;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JRadioButton;
/*     */ import javax.swing.text.JTextComponent;
/*     */ import org.jdesktop.layout.GroupLayout;
/*     */ import org.jdesktop.layout.GroupLayout.ParallelGroup;
/*     */ import org.jdesktop.layout.GroupLayout.SequentialGroup;
/*     */ 
/*     */ public class DistribucionPartidasJDialog extends JDialog
/*     */ {
/*     */   ManejoSubcuentas mS;
/*     */   private JButton botonCrearAgrupado;
/*     */   private JButton botonCrearSubcuentas;
/*     */   private JRadioButton botonDebe;
/*     */   private JRadioButton botonHaber;
/*     */   private JRadioButton botonSaldo;
/*     */   private ButtonGroup buttonGroup1;
/*     */   private ButtonGroup buttonGroup2;
/*     */   private CampoCuenta campoCuentaFin;
/*     */   private CampoCuenta campoCuentaIni;
/*     */   private ICalendarTextField campoFechaFin;
/*     */   private ICalendarTextField campoFechaIni;
/*     */   private JRadioButton grupo1;
/*     */   private JRadioButton grupo2;
/*     */   private JRadioButton grupo3;
/*     */   private JRadioButton grupo5;
/*     */   private JRadioButton grupo6;
/*     */   private JRadioButton grupo7;
/*     */   private JButton jButton1;
/*     */   private JCheckBox jCheckBox1;
/*     */   private JCheckBox jCheckBox2;
/*     */   private JLabel jLabel1;
/*     */   private JLabel jLabel2;
/*     */   private JLabel jLabel3;
/*     */   private JLabel jLabel4;
/*     */   private JPanel jPanel1;
/*     */   private JPanel jPanel2;
/*     */ 
/*     */   public DistribucionPartidasJDialog(Frame parent, boolean modal)
/*     */   {
/*  36 */     super(parent, modal);
/*  37 */     this.mS = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  38 */     initComponents();
/*  39 */     this.campoFechaIni.setComponente(this.campoFechaFin);
/*  40 */     this.campoFechaFin.setComponente(this.grupo1);
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/*  52 */     this.buttonGroup1 = new ButtonGroup();
/*  53 */     this.buttonGroup2 = new ButtonGroup();
/*  54 */     this.jLabel1 = new JLabel();
/*  55 */     this.campoFechaIni = new ICalendarTextField();
/*  56 */     this.jLabel2 = new JLabel();
/*  57 */     this.campoFechaFin = new ICalendarTextField();
/*  58 */     this.jPanel1 = new JPanel();
/*  59 */     this.grupo1 = new JRadioButton();
/*  60 */     this.grupo2 = new JRadioButton();
/*  61 */     this.grupo3 = new JRadioButton();
/*  62 */     this.grupo5 = new JRadioButton();
/*  63 */     this.grupo6 = new JRadioButton();
/*  64 */     this.grupo7 = new JRadioButton();
/*  65 */     this.botonCrearAgrupado = new JButton();
/*  66 */     this.jCheckBox1 = new JCheckBox();
/*  67 */     this.jPanel2 = new JPanel();
/*  68 */     this.jLabel3 = new JLabel();
/*  69 */     this.campoCuentaIni = new CampoCuenta(this.mS);
/*  70 */     this.jLabel4 = new JLabel();
/*  71 */     this.campoCuentaFin = new CampoCuenta(this.mS);
/*  72 */     this.botonCrearSubcuentas = new JButton();
/*  73 */     this.jCheckBox2 = new JCheckBox();
/*  74 */     this.botonDebe = new JRadioButton();
/*  75 */     this.botonHaber = new JRadioButton();
/*  76 */     this.botonSaldo = new JRadioButton();
/*  77 */     this.jButton1 = new JButton();
/*     */ 
/*  79 */     setDefaultCloseOperation(2);
/*  80 */     ResourceBundle bundle = ResourceBundle.getBundle("internationalization/Mensajes");
/*  81 */     setTitle(bundle.getString("distribucionPartidas"));
/*     */ 
/*  83 */     this.jLabel1.setText(bundle.getString("fechaIni"));
/*     */ 
/*  85 */     this.jLabel2.setText(bundle.getString("fechaFin"));
/*     */ 
/*  87 */     this.jPanel1.setBorder(BorderFactory.createTitledBorder(bundle.getString("agrupado")));
/*     */ 
/*  89 */     this.buttonGroup1.add(this.grupo1);
/*  90 */     this.grupo1.setSelected(true);
/*  91 */     this.grupo1.setText(bundle.getString("grupo1PGC"));
/*     */ 
/*  93 */     this.buttonGroup1.add(this.grupo2);
/*  94 */     this.grupo2.setText(bundle.getString("grupo2PGC"));
/*     */ 
/*  96 */     this.buttonGroup1.add(this.grupo3);
/*  97 */     this.grupo3.setText(bundle.getString("grupo3PGC"));
/*     */ 
/*  99 */     this.buttonGroup1.add(this.grupo5);
/* 100 */     this.grupo5.setText(bundle.getString("grupo5PGC"));
/*     */ 
/* 102 */     this.buttonGroup1.add(this.grupo6);
/* 103 */     this.grupo6.setText(bundle.getString("grupo6PGC"));
/*     */ 
/* 105 */     this.buttonGroup1.add(this.grupo7);
/* 106 */     this.grupo7.setText(bundle.getString("grupo7PGC"));
/*     */ 
/* 108 */     this.botonCrearAgrupado.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/Mlistado.png")));
/* 109 */     this.botonCrearAgrupado.setText(bundle.getString("crear"));
/* 110 */     this.botonCrearAgrupado.setHorizontalTextPosition(2);
/* 111 */     this.botonCrearAgrupado.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 113 */         DistribucionPartidasJDialog.this.botonCrearAgrupadoActionPerformed(evt);
/*     */       }
/*     */     });
/* 117 */     this.jCheckBox1.setSelected(true);
/* 118 */     this.jCheckBox1.setText(bundle.getString("nopartidascero"));
/*     */ 
/* 120 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 121 */     this.jPanel1.setLayout(jPanel1Layout);
/* 122 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().add(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(jPanel1Layout.createParallelGroup(1).add(this.grupo1).add(this.grupo2).add(this.grupo3)).addPreferredGap(0).add(jPanel1Layout.createParallelGroup(1).add(this.grupo7).add(this.grupo6).add(this.grupo5))).add(jPanel1Layout.createSequentialGroup().add(43, 43, 43).add(this.botonCrearAgrupado).add(18, 18, 18).add(this.jCheckBox1))).addContainerGap(-1, 32767)));
/*     */ 
/* 144 */     jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().add(jPanel1Layout.createParallelGroup(3).add(this.grupo1).add(this.grupo5)).addPreferredGap(1).add(jPanel1Layout.createParallelGroup(3).add(this.grupo2).add(this.grupo6)).addPreferredGap(1).add(jPanel1Layout.createParallelGroup(3).add(this.grupo3).add(this.grupo7)).add(18, 18, 18).add(jPanel1Layout.createParallelGroup(3).add(this.botonCrearAgrupado).add(this.jCheckBox1)).addContainerGap(-1, 32767)));
/*     */ 
/* 165 */     this.jPanel2.setBorder(BorderFactory.createTitledBorder(bundle.getString("subcuentas")));
/*     */ 
/* 167 */     this.jLabel3.setText(bundle.getString("ctaIni"));
/*     */ 
/* 169 */     this.campoCuentaIni.addKeyListener(new KeyAdapter() {
/*     */       public void keyPressed(KeyEvent evt) {
/* 171 */         DistribucionPartidasJDialog.this.campoCuentaIniKeyPressed(evt);
/*     */       }
/*     */     });
/* 175 */     this.jLabel4.setText(bundle.getString("ctaFin"));
/*     */ 
/* 177 */     this.campoCuentaFin.addKeyListener(new KeyAdapter() {
/*     */       public void keyPressed(KeyEvent evt) {
/* 179 */         DistribucionPartidasJDialog.this.campoCuentaFinKeyPressed(evt);
/*     */       }
/*     */     });
/* 183 */     this.botonCrearSubcuentas.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/Mlistado.png")));
/* 184 */     this.botonCrearSubcuentas.setText(bundle.getString("crear"));
/* 185 */     this.botonCrearSubcuentas.setHorizontalTextPosition(2);
/* 186 */     this.botonCrearSubcuentas.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 188 */         DistribucionPartidasJDialog.this.botonCrearSubcuentasActionPerformed(evt);
/*     */       }
/*     */     });
/* 192 */     this.jCheckBox2.setSelected(true);
/* 193 */     this.jCheckBox2.setText(bundle.getString("nopartidascero"));
/*     */ 
/* 195 */     this.buttonGroup2.add(this.botonDebe);
/* 196 */     this.botonDebe.setText(bundle.getString("debe"));
/*     */ 
/* 198 */     this.buttonGroup2.add(this.botonHaber);
/* 199 */     this.botonHaber.setText(bundle.getString("haber"));
/*     */ 
/* 201 */     this.buttonGroup2.add(this.botonSaldo);
/* 202 */     this.botonSaldo.setSelected(true);
/* 203 */     this.botonSaldo.setText(bundle.getString("saldo"));
/*     */ 
/* 205 */     GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
/* 206 */     this.jPanel2.setLayout(jPanel2Layout);
/* 207 */     jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(1).add(jPanel2Layout.createSequentialGroup().addContainerGap().add(jPanel2Layout.createParallelGroup(1).add(jPanel2Layout.createSequentialGroup().add(jPanel2Layout.createParallelGroup(1).add(this.jLabel3).add(this.campoCuentaIni, -2, 130, -2)).add(33, 33, 33).add(jPanel2Layout.createParallelGroup(1).add(this.jLabel4).add(this.campoCuentaFin, -2, 130, -2))).add(jPanel2Layout.createSequentialGroup().add(jPanel2Layout.createParallelGroup(1).add(jPanel2Layout.createSequentialGroup().add(21, 21, 21).add(this.botonCrearSubcuentas)).add(this.botonDebe)).addPreferredGap(0).add(jPanel2Layout.createParallelGroup(1).add(jPanel2Layout.createSequentialGroup().add(this.botonHaber).add(18, 18, 18).add(this.botonSaldo)).add(this.jCheckBox2)))).addContainerGap(33, 32767)));
/*     */ 
/* 235 */     jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(1).add(jPanel2Layout.createSequentialGroup().add(jPanel2Layout.createParallelGroup(3).add(this.jLabel3).add(this.jLabel4)).addPreferredGap(0).add(jPanel2Layout.createParallelGroup(3).add(this.campoCuentaIni, -2, -1, -2).add(this.campoCuentaFin, -2, -1, -2)).addPreferredGap(0, -1, 32767).add(jPanel2Layout.createParallelGroup(3).add(this.botonDebe).add(this.botonHaber).add(this.botonSaldo)).add(18, 18, 18).add(jPanel2Layout.createParallelGroup(3).add(this.botonCrearSubcuentas).add(this.jCheckBox2)).addContainerGap()));
/*     */ 
/* 257 */     this.jButton1.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/cancel.png")));
/* 258 */     this.jButton1.setText(bundle.getString("cancelar"));
/* 259 */     this.jButton1.setHorizontalTextPosition(2);
/* 260 */     this.jButton1.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 262 */         DistribucionPartidasJDialog.this.jButton1ActionPerformed(evt);
/*     */       }
/*     */     });
/* 266 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 267 */     getContentPane().setLayout(layout);
/* 268 */     layout.setHorizontalGroup(layout.createParallelGroup(1).add(2, layout.createSequentialGroup().addContainerGap().add(layout.createParallelGroup(2).add(1, this.jPanel2, -1, -1, 32767).add(this.jButton1).add(1, this.jPanel1, -1, -1, 32767).add(1, layout.createSequentialGroup().add(layout.createParallelGroup(1).add(this.jLabel1).add(this.campoFechaIni, -2, 140, -2)).add(30, 30, 30).add(layout.createParallelGroup(1).add(this.jLabel2).add(this.campoFechaFin, -2, 140, -2)))).addContainerGap()));
/*     */ 
/* 286 */     layout.setVerticalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(layout.createParallelGroup(3).add(this.jLabel1).add(this.jLabel2)).addPreferredGap(0).add(layout.createParallelGroup(1).add(this.campoFechaIni, -2, -1, -2).add(this.campoFechaFin, -2, -1, -2)).addPreferredGap(1).add(this.jPanel1, -2, -1, -2).addPreferredGap(1).add(this.jPanel2, -2, -1, -2).addPreferredGap(0, -1, 32767).add(this.jButton1).addContainerGap()));
/*     */ 
/* 306 */     pack();
/*     */   }
/*     */ 
/*     */   private void botonCrearAgrupadoActionPerformed(ActionEvent evt) {
/* 310 */     Date fechaIni = this.campoFechaIni.getDate();
/* 311 */     Date fechaFin = this.campoFechaFin.getDate();
/* 312 */     if ((fechaIni != null) && (fechaFin != null)) {
/* 313 */       boolean positivo = true;
/* 314 */       int grupo = -1;
/* 315 */       String titulo1 = getTitle() + ": ";
/* 316 */       if (this.grupo1.isSelected()) {
/* 317 */         grupo = 1;
/* 318 */         positivo = false;
/* 319 */         titulo1 = titulo1 + this.grupo1.getText();
/*     */       }
/* 321 */       else if (this.grupo2.isSelected()) {
/* 322 */         grupo = 2;
/*     */ 
/* 324 */         titulo1 = titulo1 + this.grupo2.getText();
/*     */       }
/* 326 */       else if (this.grupo3.isSelected()) {
/* 327 */         grupo = 3;
/*     */ 
/* 329 */         titulo1 = titulo1 + this.grupo3.getText();
/*     */       }
/* 331 */       else if (this.grupo5.isSelected()) {
/* 332 */         grupo = 5;
/*     */ 
/* 334 */         titulo1 = titulo1 + this.grupo5.getText();
/*     */       }
/* 336 */       else if (this.grupo6.isSelected()) {
/* 337 */         grupo = 6;
/*     */ 
/* 339 */         titulo1 = titulo1 + this.grupo6.getText();
/*     */       }
/* 341 */       else if (this.grupo7.isSelected()) {
/* 342 */         grupo = 7;
/* 343 */         positivo = false;
/* 344 */         titulo1 = titulo1 + this.grupo7.getText();
/*     */       }
/* 346 */       if (grupo != -1) {
/* 347 */         DistribucionPartidasControl dPC = new DistribucionPartidasControl(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 348 */         SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
/* 349 */         String titulo2 = sdf.format(fechaIni) + " " + Mensajes.getString("a") + " " + sdf.format(fechaFin);
/* 350 */         DistribucionPartidasJFrame marco = new DistribucionPartidasJFrame(titulo1, titulo2, dPC.listado(Integer.valueOf(grupo), positivo, fechaIni, fechaFin, this.jCheckBox1.isSelected()));
/* 351 */         Inicio.frame.mostrarMarcoExterno(marco);
/* 352 */         dPC.cerrarRs();
/* 353 */         dispose();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void botonCrearSubcuentasActionPerformed(ActionEvent evt) {
/* 359 */     Date fechaIni = this.campoFechaIni.getDate();
/* 360 */     Date fechaFin = this.campoFechaFin.getDate();
/* 361 */     if ((fechaIni != null) && (fechaFin != null)) {
/* 362 */       String subIniTxt = this.campoCuentaIni.getText();
/* 363 */       String subFinTxt = this.campoCuentaFin.getText();
/* 364 */       if ((!subIniTxt.equals("")) && (!subFinTxt.equals(""))) {
/* 365 */         Integer subcuentaInicial = Integer.valueOf(Integer.parseInt(subIniTxt));
/* 366 */         Integer subcuentaFinal = Integer.valueOf(Integer.parseInt(subFinTxt));
/* 367 */         int opcion = 0;
/* 368 */         if (this.botonDebe.isSelected())
/* 369 */           opcion = 1;
/* 370 */         else if (this.botonHaber.isSelected()) {
/* 371 */           opcion = 2;
/*     */         }
/* 373 */         String titulo1 = getTitle() + ": ";
/* 374 */         titulo1 = titulo1 + this.campoCuentaIni.getNombre() + " " + Mensajes.getString("a") + " " + this.campoCuentaFin.getNombre();
/* 375 */         DistribucionPartidasControl dPC = new DistribucionPartidasControl(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 376 */         SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
/* 377 */         String titulo2 = sdf.format(fechaIni) + " " + Mensajes.getString("a") + " " + sdf.format(fechaFin);
/* 378 */         DistribucionPartidasJFrame marco = new DistribucionPartidasJFrame(titulo1, titulo2, dPC.listadoSubcuentas(subcuentaInicial, subcuentaFinal, fechaIni, fechaFin, this.jCheckBox2.isSelected(), opcion));
/* 379 */         Inicio.frame.mostrarMarcoExterno(marco);
/* 380 */         dPC.cerrarRs();
/* 381 */         dispose();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void teclaPulsada(KeyEvent arg0) {
/* 387 */     int tecla = arg0.getKeyCode();
/* 388 */     Object campo = arg0.getSource();
/* 389 */     if ((arg0.isAltDown()) && (tecla == 67)) {
/* 390 */       MostrarCuentas dlg2 = new MostrarCuentas(this, Mensajes.getString("cuentas"), true);
/* 391 */       Dimension dlgSize = dlg2.getPreferredSize();
/* 392 */       Dimension frmSize = getSize();
/* 393 */       Point loc = getLocation();
/* 394 */       dlg2.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
/*     */ 
/* 396 */       dlg2.setVisible(true);
/* 397 */       if (!dlg2.Seleccion().equals(""))
/* 398 */         ((JTextComponent)campo).setText(dlg2.Seleccion()); 
/*     */     }
/*     */   }
/*     */ 
/* 402 */   private void jButton1ActionPerformed(ActionEvent evt) { dispose(); }
/*     */ 
/*     */   private void campoCuentaIniKeyPressed(KeyEvent evt)
/*     */   {
/* 406 */     teclaPulsada(evt);
/*     */   }
/*     */ 
/*     */   private void campoCuentaFinKeyPressed(KeyEvent evt) {
/* 410 */     teclaPulsada(evt);
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.informes.view.DistribucionPartidasJDialog
 * JD-Core Version:    0.6.2
 */