/*     */ package contaes.dialogosAuxiliares;
/*     */ 
/*     */ import java.awt.Container;
/*     */ import java.awt.Frame;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.GroupLayout.Alignment;
/*     */ import javax.swing.GroupLayout.ParallelGroup;
/*     */ import javax.swing.GroupLayout.SequentialGroup;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
import javax.swing.LayoutStyle;
/*     */ import javax.swing.LayoutStyle.ComponentPlacement;
/*     */ 
/*     */ public class GetInvoiceNumber extends JDialog
/*     */ {
/*     */   private String numeroFactura;
/*     */   private JTextField campoNumero;
/*     */   private JLabel etiqueta;
/*     */   private JButton jButton1;
/*     */   private JButton jButton2;
/*     */   private JLabel jLabel1;
/*     */   private JPanel jPanel1;
/*     */ 
/*     */   public GetInvoiceNumber(Frame parent, boolean modal, boolean contable, String numero)
/*     */   {
/*  24 */     super(parent, modal);
/*  25 */     initComponents();
/*  26 */     if (!contable) {
/*  27 */       this.etiqueta.setText("en facturación");
/*     */     }
/*  29 */     this.campoNumero.setText(numero);
/*     */   }
/*     */ 
/*     */   public String getNumeroFactura() {
/*  33 */     return this.numeroFactura;
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/*  45 */     this.jPanel1 = new JPanel();
/*  46 */     this.jLabel1 = new JLabel();
/*  47 */     this.etiqueta = new JLabel();
/*  48 */     this.campoNumero = new JTextField();
/*  49 */     this.jButton1 = new JButton();
/*  50 */     this.jButton2 = new JButton();
/*     */ 
/*  52 */     setDefaultCloseOperation(2);
/*     */ 
/*  54 */     this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/*     */ 
/*  56 */     this.jLabel1.setText("Eliminar factura número");
/*     */ 
/*  58 */     this.etiqueta.setText("en contabilidad");
/*     */ 
/*  60 */     this.jButton1.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/apply.png")));
/*  61 */     this.jButton1.setText("Borrar");
/*  62 */     this.jButton1.setHorizontalTextPosition(2);
/*  63 */     this.jButton1.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/*  65 */         GetInvoiceNumber.this.jButton1ActionPerformed(evt);
/*     */       }
/*     */     });
/*  69 */     this.jButton2.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/cancel.png")));
/*  70 */     this.jButton2.setText("Cancelar");
/*  71 */     this.jButton2.setHorizontalTextPosition(2);
/*  72 */     this.jButton2.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/*  74 */         GetInvoiceNumber.this.jButton2ActionPerformed(evt);
/*     */       }
/*     */     });
/*  78 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/*  79 */     this.jPanel1.setLayout(jPanel1Layout);
/*  80 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGap(6, 6, 6).addComponent(this.etiqueta, -2, 150, -2).addContainerGap()).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel1).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.jButton1).addGap(18, 18, 18).addComponent(this.jButton2))).addGap(37, 37, 37)).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.campoNumero, -1, 185, 32767).addGap(23, 23, 23))))));
/*     */ 
/* 102 */     jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.campoNumero, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.etiqueta).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jButton1).addComponent(this.jButton2)).addContainerGap(-1, 32767)));
/*     */ 
/* 118 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 119 */     getContentPane().setLayout(layout);
/* 120 */     layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -1, -1, 32767).addContainerGap()));
/*     */ 
/* 127 */     layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -1, -1, 32767).addContainerGap()));
/*     */ 
/* 135 */     pack();
/*     */   }
/*     */ 
/*     */   private void jButton1ActionPerformed(ActionEvent evt) {
/* 139 */     if (!this.campoNumero.getText().equals("")) {
/* 140 */       this.numeroFactura = this.campoNumero.getText();
/* 141 */       dispose();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void jButton2ActionPerformed(ActionEvent evt) {
/* 146 */     this.numeroFactura = "FALSE";
/* 147 */     dispose();
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.dialogosAuxiliares.GetInvoiceNumber
 * JD-Core Version:    0.6.2
 */