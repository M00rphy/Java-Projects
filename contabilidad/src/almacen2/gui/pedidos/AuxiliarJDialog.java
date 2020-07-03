/*     */ package almacen2.gui.pedidos;
/*     */ 
/*     */ import almacen2.data.AlmacenInterno;
/*     */ import contaes.calendario.ICalendarTextField;
/*     */ import java.awt.Container;
/*     */ import java.awt.Frame;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.ResourceBundle;
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
import javax.swing.LayoutStyle;
/*     */ import javax.swing.LayoutStyle.ComponentPlacement;
/*     */ 
/*     */ public class AuxiliarJDialog extends JDialog
/*     */ {
/*     */   boolean compras;
/*     */   boolean aceptado;
/*     */   String fecha;
/*     */   int almacen;
/*     */   private ArrayList<AlmacenInterno> almacenes;
/*     */   private ICalendarTextField campoFecha;
/*     */   private JComboBox comboAlmacenes;
/*     */   private JButton jButton1;
/*     */   private JButton jButton2;
/*     */   private JLabel jLabel1;
/*     */   private JLabel jLabel2;
/*     */   private JLabel jLabel3;
/*     */   private JPanel jPanel1;
/*     */ 
/*     */   public AuxiliarJDialog(Frame parent, boolean modal, boolean compras, ArrayList<AlmacenInterno> almacenes)
/*     */   {
/*  35 */     super(parent, modal);
/*  36 */     this.compras = compras;
/*  37 */     this.almacenes = almacenes;
/*  38 */     initComponents();
/*  39 */     if (!compras) {
/*  40 */       ResourceBundle bundle = ResourceBundle.getBundle("internationalization/Mensajes");
/*  41 */       this.jLabel1.setText(bundle.getString("enviadosdosDLG"));
/*     */     }
/*     */ 
/*  44 */     DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
/*  45 */     for (AlmacenInterno almacenInterno : almacenes) {
/*  46 */       modeloCombo.addElement(almacenInterno);
/*     */     }
/*  48 */     this.comboAlmacenes.setModel(modeloCombo);
/*     */   }
/*     */ 
/*     */   public boolean isAceptado() {
/*  52 */     return this.aceptado;
/*     */   }
/*     */ 
/*     */   public int getAlmacen() {
/*  56 */     return this.almacen;
/*     */   }
/*     */ 
/*     */   public String getFecha() {
/*  60 */     return this.fecha;
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/*  72 */     this.jPanel1 = new JPanel();
/*  73 */     this.jLabel1 = new JLabel();
/*  74 */     this.jLabel2 = new JLabel();
/*  75 */     this.campoFecha = new ICalendarTextField();
/*  76 */     this.jLabel3 = new JLabel();
/*  77 */     this.comboAlmacenes = new JComboBox();
/*  78 */     this.jButton1 = new JButton();
/*  79 */     this.jButton2 = new JButton();
/*     */ 
/*  81 */     setDefaultCloseOperation(2);
/*     */ 
/*  83 */     this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/*     */ 
/*  85 */     ResourceBundle bundle = ResourceBundle.getBundle("internationalization/Mensajes");
/*  86 */     this.jLabel1.setText(bundle.getString("recibidosDLG"));
/*     */ 
/*  88 */     this.jLabel2.setText(bundle.getString("fecha"));
/*     */ 
/*  90 */     this.jLabel3.setText(bundle.getString("almacen"));
/*     */ 
/*  92 */     this.jButton1.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/apply.png")));
/*  93 */     this.jButton1.setText(bundle.getString("aceptar"));
/*  94 */     this.jButton1.setHorizontalTextPosition(2);
/*  95 */     this.jButton1.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/*  97 */         AuxiliarJDialog.this.jButton1ActionPerformed(evt);
/*     */       }
/*     */     });
/* 101 */     this.jButton2.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/cancel.png")));
/* 102 */     this.jButton2.setText(bundle.getString("cancelar"));
/* 103 */     this.jButton2.setHorizontalTextPosition(2);
/* 104 */     this.jButton2.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 106 */         AuxiliarJDialog.this.jButton2ActionPerformed(evt);
/*     */       }
/*     */     });
/* 110 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 111 */     this.jPanel1.setLayout(jPanel1Layout);
/* 112 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel1, -1, 380, 32767)).addGroup(jPanel1Layout.createSequentialGroup().addGap(27, 27, 27).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel3).addComponent(this.jLabel2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.campoFecha, -2, 158, -2).addComponent(this.comboAlmacenes, -2, 207, -2))).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.jButton1).addGap(44, 44, 44).addComponent(this.jButton2))))).addContainerGap()));
/*     */ 
/* 136 */     jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.jLabel2).addComponent(this.campoFecha, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel3).addComponent(this.comboAlmacenes, -2, -1, -2)).addGap(18, 18, 18).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jButton1).addComponent(this.jButton2)).addContainerGap(-1, 32767)));
/*     */ 
/* 156 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 157 */     getContentPane().setLayout(layout);
/* 158 */     layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -1, -1, 32767).addContainerGap()));
/*     */ 
/* 165 */     layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -1, -1, 32767).addContainerGap()));
/*     */ 
/* 173 */     pack();
/*     */   }
/*     */ 
/*     */   private void jButton1ActionPerformed(ActionEvent evt)
/*     */   {
/* 178 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/* 179 */     Date fecha_d = this.campoFecha.getDate();
/* 180 */     if (fecha_d == null) fecha_d = new Date();
/* 181 */     this.fecha = sdf.format(fecha_d);
/* 182 */     this.almacen = 1;
/* 183 */     AlmacenInterno alma = (AlmacenInterno)this.comboAlmacenes.getSelectedItem();
/* 184 */     if (alma != null) {
/* 185 */       this.almacen = alma.getId().intValue();
/*     */     }
/* 187 */     this.aceptado = true;
/* 188 */     dispose();
/*     */   }
/*     */ 
/*     */   private void jButton2ActionPerformed(ActionEvent evt)
/*     */   {
/* 193 */     this.aceptado = false;
/* 194 */     dispose();
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.gui.pedidos.AuxiliarJDialog
 * JD-Core Version:    0.6.2
 */