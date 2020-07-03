/*     */ package contaes.informes.view;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.MarcoInicio;
/*     */ import contaes.Puente;
/*     */ import contaes.auxiliar.CampoCuenta;
/*     */ import contaes.calendario.ICalendarTextField;
/*     */ import contaes.dialogosAuxiliares.MostrarCuentas;
/*     */ import contaes.informes.control.ContrapartidasControl;
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
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JRadioButton;
/*     */ import javax.swing.text.JTextComponent;
/*     */ import org.jdesktop.layout.GroupLayout;
/*     */ import org.jdesktop.layout.GroupLayout.ParallelGroup;
/*     */ import org.jdesktop.layout.GroupLayout.SequentialGroup;
/*     */ 
/*     */ public class ContrapartidasJDialog extends JDialog
/*     */ {
/*     */   ManejoSubcuentas mS;
/*     */   private JRadioButton botonDebe;
/*     */   private JRadioButton botonHaber;
/*     */   private ButtonGroup buttonGroup1;
/*     */   private CampoCuenta campoCuenta;
/*     */   private ICalendarTextField campoFechaFin;
/*     */   private ICalendarTextField campoFechaIni;
/*     */   private JButton jButton1;
/*     */   private JButton jButton2;
/*     */   private JLabel jLabel1;
/*     */   private JLabel jLabel2;
/*     */   private JLabel jLabel3;
/*     */   private JPanel jPanel1;
/*     */ 
/*     */   public ContrapartidasJDialog(Frame parent, boolean modal)
/*     */   {
/*  36 */     super(parent, modal);
/*  37 */     this.mS = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  38 */     initComponents();
/*  39 */     this.campoFechaIni.setComponente(this.campoFechaFin);
/*  40 */     this.campoFechaFin.setComponente(this.campoCuenta);
/*     */   }
/*     */ 
/*     */   private void teclaPulsada(KeyEvent arg0) {
/*  44 */     int tecla = arg0.getKeyCode();
/*  45 */     Object campo = arg0.getSource();
/*  46 */     if ((arg0.isAltDown()) && (tecla == 67)) {
/*  47 */       MostrarCuentas dlg2 = new MostrarCuentas(this, Mensajes.getString("cuentas"), true);
/*  48 */       Dimension dlgSize = dlg2.getPreferredSize();
/*  49 */       Dimension frmSize = getSize();
/*  50 */       Point loc = getLocation();
/*  51 */       dlg2.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
/*     */ 
/*  53 */       dlg2.setVisible(true);
/*  54 */       if (!dlg2.Seleccion().equals(""))
/*  55 */         ((JTextComponent)campo).setText(dlg2.Seleccion());
/*     */     }
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/*  85 */     this.buttonGroup1 = new ButtonGroup();
/*  86 */     this.jPanel1 = new JPanel();
/*  87 */     this.jLabel1 = new JLabel();
/*  88 */     this.campoFechaIni = new ICalendarTextField();
/*  89 */     this.jLabel3 = new JLabel();
/*  90 */     this.campoCuenta = new CampoCuenta(this.mS);
/*  91 */     this.botonDebe = new JRadioButton();
/*  92 */     this.botonHaber = new JRadioButton();
/*  93 */     this.jButton1 = new JButton();
/*  94 */     this.jButton2 = new JButton();
/*  95 */     this.jLabel2 = new JLabel();
/*  96 */     this.campoFechaFin = new ICalendarTextField();
/*     */ 
/*  98 */     setDefaultCloseOperation(2);
/*  99 */     ResourceBundle bundle = ResourceBundle.getBundle("internationalization/Mensajes");
/* 100 */     setTitle(bundle.getString("origenDestino"));
/*     */ 
/* 102 */     this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/*     */ 
/* 104 */     this.jLabel1.setText(bundle.getString("fechaIni"));
/*     */ 
/* 106 */     this.jLabel3.setText(bundle.getString("subcuenta"));
/*     */ 
/* 108 */     this.campoCuenta.addKeyListener(new KeyAdapter() {
/*     */       public void keyPressed(KeyEvent evt) {
/* 110 */         ContrapartidasJDialog.this.campoCuentaKeyPressed(evt);
/*     */       }
/*     */     });
/* 114 */     this.buttonGroup1.add(this.botonDebe);
/* 115 */     this.botonDebe.setSelected(true);
/* 116 */     this.botonDebe.setText(bundle.getString("origenes"));
/*     */ 
/* 118 */     this.buttonGroup1.add(this.botonHaber);
/* 119 */     this.botonHaber.setText(bundle.getString("destinos"));
/*     */ 
/* 121 */     this.jButton1.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/Mlistado.png")));
/* 122 */     this.jButton1.setText(bundle.getString("crear"));
/* 123 */     this.jButton1.setHorizontalTextPosition(2);
/* 124 */     this.jButton1.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 126 */         ContrapartidasJDialog.this.jButton1ActionPerformed(evt);
/*     */       }
/*     */     });
/* 130 */     this.jButton2.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/cancel.png")));
/* 131 */     this.jButton2.setText(bundle.getString("cancelar"));
/* 132 */     this.jButton2.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 134 */         ContrapartidasJDialog.this.jButton2ActionPerformed(evt);
/*     */       }
/*     */     });
/* 138 */     this.jLabel2.setText(bundle.getString("fechaFin"));
/*     */ 
/* 140 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 141 */     this.jPanel1.setLayout(jPanel1Layout);
/* 142 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().add(jPanel1Layout.createParallelGroup(1).add(this.botonHaber).add(this.botonDebe).add(jPanel1Layout.createSequentialGroup().add(this.jLabel3).addPreferredGap(1).add(this.campoCuenta, -2, 149, -2)).add(jPanel1Layout.createSequentialGroup().add(jPanel1Layout.createParallelGroup(1).add(this.campoFechaIni, -2, 130, -2).add(this.jLabel1)).addPreferredGap(0).add(jPanel1Layout.createParallelGroup(1).add(this.jLabel2).add(this.campoFechaFin, -2, 130, -2)))).addPreferredGap(0, -1, 32767)).add(jPanel1Layout.createSequentialGroup().add(this.jButton1).add(43, 43, 43).add(this.jButton2).addPreferredGap(0))).add(8, 8, 8)));
/*     */ 
/* 171 */     jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(jPanel1Layout.createParallelGroup(2).add(this.jLabel1).add(this.jLabel2)).addPreferredGap(0).add(jPanel1Layout.createParallelGroup(2).add(this.campoFechaIni, -2, -1, -2).add(this.campoFechaFin, -2, -1, -2)).add(18, 18, 18).add(jPanel1Layout.createParallelGroup(3).add(this.jLabel3).add(this.campoCuenta, -2, -1, -2)).addPreferredGap(1).add(this.botonDebe).addPreferredGap(0).add(this.botonHaber).add(18, 18, 18).add(jPanel1Layout.createParallelGroup(3).add(this.jButton1).add(this.jButton2)).addContainerGap(-1, 32767)));
/*     */ 
/* 197 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 198 */     getContentPane().setLayout(layout);
/* 199 */     layout.setHorizontalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(this.jPanel1, -2, -1, -2).addContainerGap(-1, 32767)));
/*     */ 
/* 206 */     layout.setVerticalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(this.jPanel1, -1, -1, 32767).addContainerGap()));
/*     */ 
/* 214 */     pack();
/*     */   }
/*     */ 
/*     */   private void jButton1ActionPerformed(ActionEvent evt) {
/* 218 */     Date fechaIni = this.campoFechaIni.getDate();
/* 219 */     Date fechaFin = this.campoFechaFin.getDate();
/* 220 */     String subctaTxt = this.campoCuenta.getText();
/* 221 */     if ((fechaIni != null) && (fechaFin != null) && (!subctaTxt.equals(""))) {
/* 222 */       int codigo = Integer.parseInt(subctaTxt);
/* 223 */       boolean debe = this.botonDebe.isSelected();
/* 224 */       SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
/* 225 */       String titulo2 = sdf.format(fechaIni) + " " + Mensajes.getString("a") + " " + sdf.format(fechaFin);
/* 226 */       String titulo1 = debe ? Mensajes.getString("origenR") : Mensajes.getString("destinoR");
/* 227 */       titulo1 = titulo1 + " : " + this.campoCuenta.getNombre();
/* 228 */       ContrapartidasControl cC = new ContrapartidasControl();
/* 229 */       ContrapartidasJFrame marco = new ContrapartidasJFrame(titulo1, titulo2, cC.listado(Integer.valueOf(codigo), fechaIni, fechaFin, debe));
/* 230 */       Inicio.frame.mostrarMarcoExterno(marco);
/*     */ 
/* 232 */       cC.cerrarRs();
/* 233 */       dispose();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void jButton2ActionPerformed(ActionEvent evt)
/*     */   {
/* 239 */     dispose();
/*     */   }
/*     */ 
/*     */   private void campoCuentaKeyPressed(KeyEvent evt) {
/* 243 */     teclaPulsada(evt);
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.informes.view.ContrapartidasJDialog
 * JD-Core Version:    0.6.2
 */