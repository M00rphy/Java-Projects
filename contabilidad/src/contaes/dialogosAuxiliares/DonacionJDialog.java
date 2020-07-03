/*     */ package contaes.dialogosAuxiliares;
/*     */ 
/*     */ import java.awt.Container;
/*     */ import java.awt.Frame;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import org.jdesktop.layout.GroupLayout;
/*     */ import org.jdesktop.layout.GroupLayout.ParallelGroup;
/*     */ import org.jdesktop.layout.GroupLayout.SequentialGroup;
/*     */ 
/*     */ public class DonacionJDialog extends JDialog
/*     */ {
/*     */   private JButton jButton1;
/*     */   private JLabel jLabel1;
/*     */   private JLabel jLabel2;
/*     */   private JLabel jLabel3;
/*     */   private JLabel jLabel4;
/*     */   private JLabel jLabel5;
/*     */   private JLabel jLabel6;
/*     */   private JPanel jPanel1;
/*     */ 
/*     */   public DonacionJDialog(Frame parent, boolean modal, String title)
/*     */   {
/*  22 */     super(parent, modal);
/*  23 */     initComponents();
/*  24 */     setTitle(title);
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/*  36 */     this.jPanel1 = new JPanel();
/*  37 */     this.jLabel1 = new JLabel();
/*  38 */     this.jLabel2 = new JLabel();
/*  39 */     this.jLabel3 = new JLabel();
/*  40 */     this.jLabel4 = new JLabel();
/*  41 */     this.jLabel5 = new JLabel();
/*  42 */     this.jButton1 = new JButton();
/*  43 */     this.jLabel6 = new JLabel();
/*     */ 
/*  45 */     setDefaultCloseOperation(2);
/*     */ 
/*  47 */     this.jPanel1.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3), BorderFactory.createEtchedBorder()));
/*     */ 
/*  49 */     this.jLabel1.setText("Esta característica está reservada.");
/*     */ 
/*  51 */     this.jLabel2.setText("Si deseas activarla, hazme una donación");
/*     */ 
/*  53 */     this.jLabel3.setText("(mínimo 50,00 €).");
/*     */ 
/*  55 */     this.jLabel4.setText("Y mándame un correo para que te envíe");
/*     */ 
/*  57 */     this.jLabel5.setText("un número de serie para habilitarla.");
/*     */ 
/*  59 */     this.jButton1.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/about.png")));
/*  60 */     this.jButton1.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/*  62 */         DonacionJDialog.this.jButton1ActionPerformed(evt);
/*     */       }
/*     */     });
/*  66 */     this.jLabel6.setText("¡ Muchas gracias !");
/*     */ 
/*  68 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/*  69 */     this.jPanel1.setLayout(jPanel1Layout);
/*  70 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().add(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(jPanel1Layout.createParallelGroup(1).add(this.jLabel1).add(this.jLabel2).add(this.jLabel3).add(this.jLabel4).add(this.jLabel5).add(this.jLabel6))).add(jPanel1Layout.createSequentialGroup().add(67, 67, 67).add(this.jButton1, -2, 132, -2))).addContainerGap(-1, 32767)));
/*     */ 
/*  88 */     jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(this.jLabel1).addPreferredGap(0).add(this.jLabel2).addPreferredGap(0).add(this.jLabel3).addPreferredGap(0).add(this.jLabel4).addPreferredGap(0).add(this.jLabel5).addPreferredGap(0).add(this.jLabel6).add(18, 18, 18).add(this.jButton1).addContainerGap(-1, 32767)));
/*     */ 
/* 108 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 109 */     getContentPane().setLayout(layout);
/* 110 */     layout.setHorizontalGroup(layout.createParallelGroup(1).add(this.jPanel1, -2, -1, -2));
/*     */ 
/* 114 */     layout.setVerticalGroup(layout.createParallelGroup(1).add(this.jPanel1, -2, -1, -2));
/*     */ 
/* 119 */     pack();
/*     */   }
/*     */ 
/*     */   private void jButton1ActionPerformed(ActionEvent evt) {
/* 123 */     dispose();
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.dialogosAuxiliares.DonacionJDialog
 * JD-Core Version:    0.6.2
 */