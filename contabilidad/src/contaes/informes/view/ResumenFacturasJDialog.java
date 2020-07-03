/*     */ package contaes.informes.view;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.MarcoInicio;
/*     */ import contaes.Puente;
/*     */ import contaes.calendario.ICalendarTextField;
/*     */ import contaes.informes.control.ResumenFacturasControl;
/*     */ import contaes.manejoDatos.ManejoSubcuentas;
/*     */ import contaes.manejoDatos.TipoSubcuenta;
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.Container;
/*     */ import java.awt.Frame;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
import java.util.List;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.DefaultComboBoxModel;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import org.jdesktop.layout.GroupLayout;
/*     */ import org.jdesktop.layout.GroupLayout.ParallelGroup;
/*     */ import org.jdesktop.layout.GroupLayout.SequentialGroup;
/*     */ 
/*     */ public class ResumenFacturasJDialog extends JDialog
/*     */ {
/*     */   boolean ventas;
/*  32 */   DefaultComboBoxModel modeloInicio = new DefaultComboBoxModel();
/*  33 */   DefaultComboBoxModel modeloFin = new DefaultComboBoxModel();
/*     */   private JComboBox comboFinal;
/*     */   private JComboBox comboInicio;
/*     */   private ICalendarTextField fechaFinal;
/*     */   private ICalendarTextField fechaInicial;
/*     */   private JButton jButton1;
/*     */   private JButton jButton2;
/*     */   private JLabel jLabel1;
/*     */   private JLabel jLabel2;
/*     */   private JLabel jLabel3;
/*     */   private JLabel jLabel4;
/*     */   private JPanel jPanel1;
/*     */ 
/*     */   public ResumenFacturasJDialog(Frame parent, boolean modal, boolean ventas)
/*     */   {
/*  37 */     super(parent, modal);
/*  38 */     this.ventas = ventas;
/*  39 */     initComponents();
/*  40 */     setModeloCombos();
/*  41 */     this.fechaInicial.setComponente(this.fechaFinal);
/*  42 */     this.fechaFinal.setComponente(this.jButton1);
/*     */   }
/*     */ 
/*     */   private void setModeloCombos() {
/*  46 */     int cuentaIni = this.ventas ? 43000000 : 40000000;
/*  47 */     int cuentaFin = this.ventas ? 44999999 : 41999999;
/*  48 */     ArrayList listaIni = new ArrayList();
/*  49 */     ManejoSubcuentas mS = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  50 */     listaIni = mS.listaSubcuentas(cuentaIni, cuentaFin);
/*  51 */     for (TipoSubcuenta tipoSubcuenta :(List<TipoSubcuenta>) listaIni) {
/*  52 */       this.modeloInicio.addElement(tipoSubcuenta);
/*  53 */       this.modeloFin.addElement(tipoSubcuenta);
/*     */     }
/*  55 */     this.comboInicio.setModel(this.modeloInicio);
/*  56 */     this.comboFinal.setModel(this.modeloFin);
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/*  68 */     this.jPanel1 = new JPanel();
/*  69 */     this.jLabel1 = new JLabel();
/*  70 */     this.comboInicio = new JComboBox();
/*  71 */     this.jLabel2 = new JLabel();
/*  72 */     this.comboFinal = new JComboBox();
/*  73 */     this.jLabel3 = new JLabel();
/*  74 */     this.fechaInicial = new ICalendarTextField();
/*  75 */     this.jLabel4 = new JLabel();
/*  76 */     this.fechaFinal = new ICalendarTextField();
/*  77 */     this.jButton1 = new JButton();
/*  78 */     this.jButton2 = new JButton();
/*     */ 
/*  80 */     setDefaultCloseOperation(2);
/*  81 */     setTitle("Resumen de facturas mensuales");
/*     */ 
/*  83 */     this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/*     */ 
/*  85 */     this.jLabel1.setText("Subcuenta inicial");
/*     */ 
/*  87 */     this.jLabel2.setText("Subcuenta final");
/*     */ 
/*  89 */     this.jLabel3.setText("Fecha inicial");
/*     */ 
/*  91 */     this.jLabel4.setText("Fecha final");
/*     */ 
/*  93 */     this.jButton1.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/Mlistado.png")));
/*  94 */     this.jButton1.setText("Generar informe");
/*  95 */     this.jButton1.setHorizontalTextPosition(2);
/*  96 */     this.jButton1.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/*  98 */         ResumenFacturasJDialog.this.jButton1ActionPerformed(evt);
/*     */       }
/*     */     });
/* 102 */     this.jButton2.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/cancel.png")));
/* 103 */     this.jButton2.setText("Cancelar");
/* 104 */     this.jButton2.setHorizontalTextPosition(2);
/* 105 */     this.jButton2.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 107 */         ResumenFacturasJDialog.this.jButton2ActionPerformed(evt);
/*     */       }
/*     */     });
/* 111 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 112 */     this.jPanel1.setLayout(jPanel1Layout);
/* 113 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().add(this.jButton1).addPreferredGap(0, 68, 32767).add(this.jButton2).add(31, 31, 31)).add(jPanel1Layout.createSequentialGroup().add(this.jLabel1).addPreferredGap(0, 195, 32767)).add(jPanel1Layout.createSequentialGroup().add(this.jLabel2).addPreferredGap(0, 205, 32767)).add(2, jPanel1Layout.createSequentialGroup().add(jPanel1Layout.createParallelGroup(1).add(this.jLabel3).add(this.fechaInicial, -2, 134, -2)).addPreferredGap(0, 40, 32767).add(jPanel1Layout.createParallelGroup(1).add(this.fechaFinal, -2, 128, -2).add(this.jLabel4)).addPreferredGap(0, -1, 32767)).add(jPanel1Layout.createSequentialGroup().add(jPanel1Layout.createParallelGroup(2, false).add(1, this.comboFinal, 0, -1, 32767).add(1, this.comboInicio, 0, 284, 32767)).addPreferredGap(0))).add(14, 14, 14)));
/*     */ 
/* 145 */     jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(this.jLabel1).addPreferredGap(0).add(this.comboInicio, -2, -1, -2).addPreferredGap(1).add(this.jLabel2).addPreferredGap(0).add(this.comboFinal, -2, -1, -2).add(18, 18, 18).add(jPanel1Layout.createParallelGroup(3).add(this.jLabel3).add(this.jLabel4)).addPreferredGap(0).add(jPanel1Layout.createParallelGroup(2).add(this.fechaInicial, -2, -1, -2).add(this.fechaFinal, -2, -1, -2)).add(18, 18, 18).add(jPanel1Layout.createParallelGroup(3).add(this.jButton1).add(this.jButton2)).addContainerGap(19, 32767)));
/*     */ 
/* 171 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 172 */     getContentPane().setLayout(layout);
/* 173 */     layout.setHorizontalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(this.jPanel1, -2, -1, -2).addContainerGap(-1, 32767)));
/*     */ 
/* 180 */     layout.setVerticalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(this.jPanel1, -2, -1, -2).addContainerGap(22, 32767)));
/*     */ 
/* 188 */     pack();
/*     */   }
/*     */ 
/*     */   private void jButton1ActionPerformed(ActionEvent evt)
/*     */   {
/* 193 */     TipoSubcuenta cuentaInicio = (TipoSubcuenta)this.comboInicio.getSelectedItem();
/* 194 */     TipoSubcuenta cuentaFin = (TipoSubcuenta)this.comboFinal.getSelectedItem();
/* 195 */     Date fechaIni = this.fechaInicial.getDate();
/* 196 */     Date fechaFin = this.fechaFinal.getDate();
/* 197 */     if ((cuentaInicio != null) && (cuentaFin != null) && (fechaIni != null) && (fechaFin != null)) {
/* 198 */       ResumenFacturasControl rF = new ResumenFacturasControl(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 199 */       ArrayList array = rF.listado(cuentaInicio.getCodigo(), cuentaFin.getCodigo(), fechaIni, fechaFin, this.ventas);
/* 200 */       String titulo1 = new StringBuilder().append(Mensajes.getString("resumenfacturas")).append(" ").toString();
/* 201 */       titulo1 = new StringBuilder().append(titulo1).append(this.ventas ? Mensajes.getString("clientes") : Mensajes.getString("proveedores")).toString();
/* 202 */       SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
/* 203 */       String titulo2 = new StringBuilder().append(sdf.format(fechaIni)).append(" ").append(Mensajes.getString("a")).append(" ").append(sdf.format(fechaFin)).toString();
/* 204 */       ResumenFacturasJFrame marco = new ResumenFacturasJFrame(titulo1, titulo2, array);
/* 205 */       Inicio.frame.mostrarMarcoExterno(marco);
/* 206 */       rF.cerrarRs();
/* 207 */       dispose();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void jButton2ActionPerformed(ActionEvent evt)
/*     */   {
/* 213 */     dispose();
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.informes.view.ResumenFacturasJDialog
 * JD-Core Version:    0.6.2
 */