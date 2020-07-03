/*     */ package almacen2.gui;
/*     */ 
/*     */ import almacen2.data.AlmacenInterno;
/*     */ import contaes.Inicio;
/*     */ import contaes.auxiliar.DocDigitos;
/*     */ import java.awt.Container;
/*     */ import java.awt.Frame;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.ArrayList;
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
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
import javax.swing.LayoutStyle;
/*     */ import javax.swing.LayoutStyle.ComponentPlacement;
/*     */ 
/*     */ public class TraspasarProductosJDialog extends JDialog
/*     */ {
/*  25 */   private int idAlmacenDestino = -1;
/*  26 */   private int unidades = -1;
/*  27 */   private int stockAlmacenOrigen = 0;
/*     */   private ArrayList<AlmacenInterno> almacenes;
/*     */   private JButton botonCancelar;
/*     */   private JButton botonTraspasar;
/*     */   private JTextField campoUnidades;
/*     */   private JComboBox comboAlmacenes;
/*     */   private JLabel jLabel1;
/*     */   private JLabel jLabel2;
/*     */   private JPanel jPanel1;
/*     */ 
/*     */   public TraspasarProductosJDialog(Frame parent, boolean modal, ArrayList<AlmacenInterno> almacenes, int stockAlmacenOrigen)
/*     */   {
/*  32 */     super(parent, modal);
/*  33 */     this.almacenes = almacenes;
/*  34 */     this.stockAlmacenOrigen = stockAlmacenOrigen;
/*  35 */     initComponents();
/*  36 */     setComboModel();
/*     */   }
/*     */ 
/*     */   private void setComboModel() {
/*  40 */     DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
/*  41 */     for (AlmacenInterno almacenInterno : this.almacenes) {
/*  42 */       comboModel.addElement(almacenInterno);
/*     */     }
/*  44 */     this.comboAlmacenes.setModel(comboModel);
/*     */   }
/*     */ 
/*     */   public int getIdAlmacenDestino() {
/*  48 */     return this.idAlmacenDestino;
/*     */   }
/*     */ 
/*     */   public int getUnidades() {
/*  52 */     return this.unidades;
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/*  64 */     this.jPanel1 = new JPanel();
/*  65 */     this.jLabel1 = new JLabel();
/*  66 */     this.comboAlmacenes = new JComboBox();
/*  67 */     this.jLabel2 = new JLabel();
/*  68 */     this.campoUnidades = new JTextField();
/*  69 */     this.botonTraspasar = new JButton();
/*  70 */     this.botonCancelar = new JButton();
/*     */ 
/*  72 */     setDefaultCloseOperation(2);
/*  73 */     setTitle("Traspaso entre almacenes");
/*     */ 
/*  75 */     this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/*     */ 
/*  77 */     this.jLabel1.setText("Seleccione el almacén de destino:");
/*     */ 
/*  79 */     this.jLabel2.setText("Número de unidades a traspasar:");
/*     */ 
/*  81 */     this.campoUnidades.setDocument(new DocDigitos());
/*     */ 
/*  83 */     this.botonTraspasar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/2rightarrow.png")));
/*  84 */     this.botonTraspasar.setText("Traspasar");
/*  85 */     this.botonTraspasar.setHorizontalTextPosition(2);
/*  86 */     this.botonTraspasar.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/*  88 */         TraspasarProductosJDialog.this.botonTraspasarActionPerformed(evt);
/*     */       }
/*     */     });
/*  92 */     this.botonCancelar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/cancel.png")));
/*  93 */     ResourceBundle bundle = ResourceBundle.getBundle("internationalization/Mensajes");
/*  94 */     this.botonCancelar.setText(bundle.getString("cancelar"));
/*  95 */     this.botonCancelar.setHorizontalTextPosition(2);
/*  96 */     this.botonCancelar.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/*  98 */         TraspasarProductosJDialog.this.botonCancelarActionPerformed(evt);
/*     */       }
/*     */     });
/* 102 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 103 */     this.jPanel1.setLayout(jPanel1Layout);
/* 104 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGap(6, 6, 6).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.campoUnidades, -2, 137, -2).addComponent(this.jLabel2)).addContainerGap()).addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.comboAlmacenes, GroupLayout.Alignment.LEADING, 0, 211, 32767).addComponent(this.jLabel1, -1, -1, 32767)).addGap(167, 167, 167)).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.botonTraspasar).addGap(18, 18, 18).addComponent(this.botonCancelar).addContainerGap(37, 32767)))));
/*     */ 
/* 126 */     jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.comboAlmacenes, -2, -1, -2).addGap(12, 12, 12).addComponent(this.jLabel2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.campoUnidades, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.botonTraspasar).addComponent(this.botonCancelar)).addContainerGap(-1, 32767)));
/*     */ 
/* 144 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 145 */     getContentPane().setLayout(layout);
/* 146 */     layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -2, 238, -2).addContainerGap(-1, 32767)));
/*     */ 
/* 153 */     layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -1, -1, 32767).addContainerGap()));
/*     */ 
/* 161 */     pack();
/*     */   }
/*     */ 
/*     */   private void botonTraspasarActionPerformed(ActionEvent evt) {
/* 165 */     if (!this.campoUnidades.getText().equals("")) {
/* 166 */       this.unidades = Integer.parseInt(this.campoUnidades.getText());
/* 167 */       if (this.unidades > this.stockAlmacenOrigen) {
/* 168 */         JOptionPane.showMessageDialog(Inicio.frame, "El número de unidades introducido es\nsuperior al stock del almacén de origen.", "Error", 0);
/*     */ 
/* 173 */         this.unidades = -1;
/* 174 */         this.campoUnidades.requestFocusInWindow();
/* 175 */         return;
/*     */       }
/*     */     }
/* 178 */     if (this.comboAlmacenes.getSelectedIndex() != -1) {
/* 179 */       AlmacenInterno almacen = (AlmacenInterno)this.comboAlmacenes.getSelectedItem();
/* 180 */       this.idAlmacenDestino = almacen.getId().intValue();
/*     */     }
/* 182 */     dispose();
/*     */   }
/*     */ 
/*     */   private void botonCancelarActionPerformed(ActionEvent evt) {
/* 186 */     this.idAlmacenDestino = -1;
/* 187 */     this.unidades = -1;
/* 188 */     dispose();
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.gui.TraspasarProductosJDialog
 * JD-Core Version:    0.6.2
 */