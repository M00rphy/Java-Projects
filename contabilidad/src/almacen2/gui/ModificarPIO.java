/*     */ package almacen2.gui;
/*     */ 
/*     */ import almacen2.data.AlmacenInterno;
/*     */ import almacen2.data.PIOObject;
/*     */ import contaes.Inicio;
/*     */ import contaes.MarcoInicio;
/*     */ import contaes.auxiliar.DocNumeros;
/*     */ import contaes.calendario.ICalendarTextField;
/*     */ import facturacion.control.AlmacenControl;
/*     */ import java.awt.Color;
/*     */ import java.awt.Container;
/*     */ import java.awt.Frame;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
import java.util.List;
/*     */ import java.util.ResourceBundle;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.DefaultComboBoxModel;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.GroupLayout.Alignment;
/*     */ import javax.swing.GroupLayout.ParallelGroup;
/*     */ import javax.swing.GroupLayout.SequentialGroup;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
import javax.swing.LayoutStyle;
/*     */ import javax.swing.LayoutStyle.ComponentPlacement;
/*     */ 
/*     */ public class ModificarPIO extends JDialog
/*     */ {
/*     */   PIOObject objeto;
/*     */   String referencia;
/*  35 */   private DefaultComboBoxModel modeloComboAlmacenes = new DefaultComboBoxModel();
/*     */   private JButton botonCancelar;
/*     */   private JButton botonOK;
/*     */   private ICalendarTextField campoFecha;
/*     */   private JTextField campoImporte;
/*     */   private JLabel campoReferencia;
/*     */   private JComboBox comboAlmacenes;
/*     */   private JLabel jLabel1;
/*     */   private JLabel jLabel3;
/*     */   private JPanel jPanel1;
/*     */ 
/*     */   public ModificarPIO(Frame parent, boolean modal, PIOObject objeto, String referencia)
/*     */   {
/*  39 */     super(parent, modal);
/*  40 */     this.objeto = objeto;
/*  41 */     this.referencia = referencia;
/*  42 */     initComponents();
/*  43 */     llenarCombo();
/*  44 */     colocarObjeto();
/*     */   }
/*     */ 
/*     */   private void llenarCombo() {
/*     */     try {
/*  49 */       AlmacenControl aC = new AlmacenControl();
/*  50 */       ArrayList almacenes = aC.getAlmacenes();
/*  51 */       aC.cerrarConexion();
/*  52 */       for (AlmacenInterno almacenInterno :(List<AlmacenInterno>) almacenes) {
/*  53 */         this.modeloComboAlmacenes.addElement(almacenInterno);
/*     */       }
/*  55 */       this.comboAlmacenes.setModel(this.modeloComboAlmacenes);
/*     */     }
/*     */     catch (Exception ex) {
/*  58 */       Logger.getLogger(ModificarPIO.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void colocarObjeto() {
/*  63 */     this.campoReferencia.setText(this.referencia);
/*  64 */     int year = Integer.parseInt(this.objeto.getFecha().substring(6));
/*  65 */     int month = Integer.parseInt(this.objeto.getFecha().substring(3, 5));
/*  66 */     int day = Integer.parseInt(this.objeto.getFecha().substring(0, 2));
/*  67 */     GregorianCalendar fecha = new GregorianCalendar(year, month - 1, day);
/*  68 */     this.campoFecha.setDate(fecha.getTime());
/*  69 */     this.campoImporte.setText(Double.toString(this.objeto.getImporte()));
/*  70 */     for (int i = 0; i < this.comboAlmacenes.getItemCount(); i++) {
/*  71 */       AlmacenInterno almacen = (AlmacenInterno)this.comboAlmacenes.getItemAt(i);
/*  72 */       if (almacen.getId().intValue() == this.objeto.getAlmacen()) {
/*  73 */         this.comboAlmacenes.setSelectedIndex(i);
/*  74 */         break;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/*  88 */     this.jPanel1 = new JPanel();
/*  89 */     this.jLabel1 = new JLabel();
/*  90 */     this.campoReferencia = new JLabel();
/*  91 */     this.campoFecha = new ICalendarTextField();
/*  92 */     this.jLabel3 = new JLabel();
/*  93 */     this.campoImporte = new JTextField();
/*  94 */     this.comboAlmacenes = new JComboBox();
/*  95 */     this.botonOK = new JButton();
/*  96 */     this.botonCancelar = new JButton();
/*     */ 
/*  98 */     setDefaultCloseOperation(2);
/*  99 */     setTitle("Modificar registro");
/*     */ 
/* 101 */     this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/*     */ 
/* 103 */     ResourceBundle bundle = ResourceBundle.getBundle("internationalization/Mensajes");
/* 104 */     this.jLabel1.setText(bundle.getString("referencia"));
/*     */ 
/* 106 */     this.campoReferencia.setBackground(Color.white);
/*     */ 
/* 108 */     this.jLabel3.setText(bundle.getString("importe"));
/*     */ 
/* 110 */     this.campoImporte.setDocument(new DocNumeros());
/*     */ 
/* 112 */     this.botonOK.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/ok.png")));
/* 113 */     this.botonOK.setText(bundle.getString("modificar"));
/* 114 */     this.botonOK.setHorizontalTextPosition(2);
/* 115 */     this.botonOK.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 117 */         ModificarPIO.this.botonOKActionPerformed(evt);
/*     */       }
/*     */     });
/* 121 */     this.botonCancelar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/cancel.png")));
/* 122 */     this.botonCancelar.setText(bundle.getString("cancelar"));
/* 123 */     this.botonCancelar.setHorizontalTextPosition(2);
/* 124 */     this.botonCancelar.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 126 */         ModificarPIO.this.botonCancelarActionPerformed(evt);
/*     */       }
/*     */     });
/* 130 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 131 */     this.jPanel1.setLayout(jPanel1Layout);
/* 132 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.jLabel1, -2, 78, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.campoReferencia, -1, 130, 32767)).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.botonOK).addGap(34, 34, 34).addComponent(this.botonCancelar)).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false).addComponent(this.comboAlmacenes, GroupLayout.Alignment.LEADING, 0, -1, 32767).addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup().addComponent(this.jLabel3).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.campoImporte, -2, 153, -2))))).addGroup(jPanel1Layout.createSequentialGroup().addGap(42, 42, 42).addComponent(this.campoFecha, -2, 133, -2))).addContainerGap()));
/*     */ 
/* 158 */     jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.jLabel1).addComponent(this.campoReferencia, -2, 25, -2)).addGap(18, 18, 18).addComponent(this.campoFecha, -2, -1, -2).addGap(18, 18, 18).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel3).addComponent(this.campoImporte, -2, -1, -2)).addGap(18, 18, 18).addComponent(this.comboAlmacenes, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.botonOK).addComponent(this.botonCancelar)).addContainerGap(-1, 32767)));
/*     */ 
/* 180 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 181 */     getContentPane().setLayout(layout);
/* 182 */     layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -1, -1, 32767).addContainerGap()));
/*     */ 
/* 189 */     layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -1, -1, 32767).addContainerGap()));
/*     */ 
/* 197 */     pack();
/*     */   }
/*     */ 
/*     */   private void botonOKActionPerformed(ActionEvent evt) {
/* 201 */     Date newFecha = this.campoFecha.getDate();
/* 202 */     if (newFecha != null) {
/* 203 */       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/* 204 */       this.objeto.setFecha(sdf.format(newFecha));
/*     */     }
/* 206 */     if (!this.campoImporte.getText().equals("")) {
/* 207 */       Double importe = Double.valueOf(this.campoImporte.getText());
/* 208 */       this.objeto.setImporte(importe.doubleValue());
/*     */     }
/* 210 */     AlmacenInterno almacen = (AlmacenInterno)this.comboAlmacenes.getSelectedItem();
/* 211 */     if (almacen != null) {
/* 212 */       this.objeto.setAlmacen(almacen.getId().intValue());
/*     */     }
/* 214 */     Inicio.frame.modificarPIO(this.referencia, this.objeto);
/* 215 */     Inicio.frame.updateStockAlmacen();
/* 216 */     dispose();
/*     */   }
/*     */ 
/*     */   private void botonCancelarActionPerformed(ActionEvent evt) {
/* 220 */     dispose();
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.gui.ModificarPIO
 * JD-Core Version:    0.6.2
 */