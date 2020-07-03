/*     */ package facturacion.view;
/*     */ 
/*     */ import almacen2.data.AlmacenInterno;
/*     */ import facturacion.control.AlmacenControl;
/*     */ import java.awt.Container;
/*     */ import java.awt.Frame;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.ArrayList;
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
import javax.swing.LayoutStyle;
/*     */ import javax.swing.LayoutStyle.ComponentPlacement;
/*     */ 
/*     */ public class ActualizarAlmacenJDialog extends JDialog
/*     */ {
/*  27 */   int idAlmacen = -1;
/*     */ 
/*  29 */   private DefaultComboBoxModel modeloComboAlmacenes = new DefaultComboBoxModel();
/*     */   private JComboBox comboAlmacenes;
/*     */   private JButton jButtonCancel;
/*     */   private JButton jButtonOk;
/*     */   private JLabel jLabel1;
/*     */   private JLabel jLabel2;
/*     */   private JPanel jPanel1;
/*     */ 
/*     */   public ActualizarAlmacenJDialog(Frame parent, boolean modal)
/*     */   {
/*  33 */     super(parent, modal);
/*  34 */     initComponents();
/*  35 */     llenarCombo();
/*     */   }
/*     */ 
/*     */   private void llenarCombo() {
/*     */     try {
/*  40 */       AlmacenControl aC = new AlmacenControl();
/*  41 */       ArrayList almacenes = aC.getAlmacenes();
/*  42 */       aC.cerrarConexion();
/*  43 */       for (AlmacenInterno almacenInterno :(List<AlmacenInterno>) almacenes) {
/*  44 */         this.modeloComboAlmacenes.addElement(almacenInterno);
/*     */       }
/*  46 */       this.comboAlmacenes.setModel(this.modeloComboAlmacenes);
/*     */     }
/*     */     catch (Exception ex) {
/*  49 */       Logger.getLogger(ActualizarAlmacenJDialog.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/*     */   }
/*     */ 
/*     */   public int getIdAlmacen() {
/*  54 */     return this.idAlmacen;
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/*  66 */     this.jPanel1 = new JPanel();
/*  67 */     this.jLabel1 = new JLabel();
/*  68 */     this.jLabel2 = new JLabel();
/*  69 */     this.comboAlmacenes = new JComboBox();
/*  70 */     this.jButtonOk = new JButton();
/*  71 */     this.jButtonCancel = new JButton();
/*     */ 
/*  73 */     setDefaultCloseOperation(2);
/*  74 */     ResourceBundle bundle = ResourceBundle.getBundle("internationalization/Mensajes");
/*  75 */     setTitle(bundle.getString("almacen"));
/*     */ 
/*  77 */     this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/*     */ 
/*  79 */     this.jLabel1.setText(bundle.getString("updatealmacen"));
/*     */ 
/*  81 */     this.jLabel2.setText(bundle.getString("almacen"));
/*     */ 
/*  83 */     this.jButtonOk.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/addProduct.png")));
/*  84 */     this.jButtonOk.setText(bundle.getString("actualizar"));
/*  85 */     this.jButtonOk.setHorizontalTextPosition(2);
/*  86 */     this.jButtonOk.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/*  88 */         ActualizarAlmacenJDialog.this.jButtonOkActionPerformed(evt);
/*     */       }
/*     */     });
/*  92 */     this.jButtonCancel.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/button_cancel.png")));
/*  93 */     this.jButtonCancel.setText(bundle.getString("noactualizar"));
/*  94 */     this.jButtonCancel.setHorizontalTextPosition(2);
/*  95 */     this.jButtonCancel.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/*  97 */         ActualizarAlmacenJDialog.this.jButtonCancelActionPerformed(evt);
/*     */       }
/*     */     });
/* 101 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 102 */     this.jPanel1.setLayout(jPanel1Layout);
/* 103 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.comboAlmacenes, 0, 242, 32767).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.jButtonOk).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 32, 32767).addComponent(this.jButtonCancel))).addContainerGap()).addGroup(jPanel1Layout.createSequentialGroup().addGap(21, 21, 21).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.jLabel2).addContainerGap()).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.jLabel1, -1, -1, 32767).addGap(19, 19, 19)))));
/*     */ 
/* 124 */     jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel1).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jLabel2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.comboAlmacenes, -2, -1, -2).addGap(18, 18, 18).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jButtonOk).addComponent(this.jButtonCancel)).addContainerGap(-1, 32767)));
/*     */ 
/* 140 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 141 */     getContentPane().setLayout(layout);
/* 142 */     layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -1, -1, 32767).addContainerGap()));
/*     */ 
/* 149 */     layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -2, -1, -2).addContainerGap(-1, 32767)));
/*     */ 
/* 157 */     pack();
/*     */   }
/*     */ 
/*     */   private void jButtonOkActionPerformed(ActionEvent evt) {
/* 161 */     AlmacenInterno almacen = (AlmacenInterno)this.comboAlmacenes.getSelectedItem();
/* 162 */     if (almacen != null) {
/* 163 */       this.idAlmacen = almacen.getId().intValue();
/*     */     }
/* 165 */     dispose();
/*     */   }
/*     */ 
/*     */   private void jButtonCancelActionPerformed(ActionEvent evt) {
/* 169 */     this.idAlmacen = -1;
/* 170 */     dispose();
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     facturacion.view.ActualizarAlmacenJDialog
 * JD-Core Version:    0.6.2
 */