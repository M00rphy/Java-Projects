/*     */ package contaes.dialogosAuxiliares;
/*     */ 
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Frame;
/*     */ import java.awt.Toolkit;
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
import javax.swing.LayoutStyle;
/*     */ import javax.swing.LayoutStyle.ComponentPlacement;
/*     */ 
/*     */ public class DonacionesJDialog extends JDialog
/*     */ {
/*     */   private JButton jButton1;
/*     */   private JLabel jLabel1;
/*     */   private JLabel jLabel2;
/*     */   private JLabel jLabel3;
/*     */   private JLabel jLabel4;
/*     */   private JPanel jPanel1;
/*     */ 
/*     */   public DonacionesJDialog(Frame parent, boolean modal)
/*     */   {
/*  24 */     super(parent, modal);
/*  25 */     initComponents();
/*     */   }
/*     */ 
/*     */   public static void recordarDonaciones() {
/*  29 */     DonacionesJDialog dlg = new DonacionesJDialog(new Frame(), true);
/*  30 */     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/*  31 */     Dimension frameSize = dlg.getSize();
/*  32 */     if (frameSize.height > screenSize.height) {
/*  33 */       frameSize.height = screenSize.height;
/*     */     }
/*  35 */     if (frameSize.width > screenSize.width) {
/*  36 */       frameSize.width = screenSize.width;
/*     */     }
/*  38 */     dlg.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
/*     */ 
/*  40 */     dlg.pack();
/*  41 */     dlg.setVisible(true);
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/*  53 */     this.jPanel1 = new JPanel();
/*  54 */     this.jLabel1 = new JLabel();
/*  55 */     this.jLabel2 = new JLabel();
/*  56 */     this.jLabel3 = new JLabel();
/*  57 */     this.jLabel4 = new JLabel();
/*  58 */     this.jButton1 = new JButton();
/*     */ 
/*  60 */     setDefaultCloseOperation(2);
/*  61 */     setTitle("Recordatorio");
/*     */ 
/*  63 */     this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/*     */ 
/*  65 */     this.jLabel1.setText("Si te gusta el programa y es de utilidad para ti,");
/*     */ 
/*  67 */     this.jLabel2.setText("Puedes hacerme una donación a través de mi");
/*     */ 
/*  69 */     this.jLabel3.setText("página web (búscala en el menú de ayuda).");
/*     */ 
/*  71 */     this.jLabel4.setText("Muchas gracias y que seas muy feliz.");
/*     */ 
/*  73 */     this.jButton1.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/about.png")));
/*  74 */     this.jButton1.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/*  76 */         DonacionesJDialog.this.jButton1ActionPerformed(evt);
/*     */       }
/*     */     });
/*  80 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/*  81 */     this.jPanel1.setLayout(jPanel1Layout);
/*  82 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGap(28, 28, 28).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel1, -2, 305, -2).addComponent(this.jLabel2, -1, 305, 32767).addComponent(this.jLabel3).addComponent(this.jLabel4, -2, 269, -2)).addContainerGap(-1, 32767)).addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup().addContainerGap(104, 32767).addComponent(this.jButton1, -2, 132, -2).addGap(103, 103, 103)));
/*     */ 
/*  97 */     jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGap(19, 19, 19).addComponent(this.jLabel1).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jLabel2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jLabel3).addGap(18, 18, 18).addComponent(this.jLabel4).addGap(18, 18, 18).addComponent(this.jButton1).addContainerGap(-1, 32767)));
/*     */ 
/* 113 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 114 */     getContentPane().setLayout(layout);
/* 115 */     layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -1, -1, 32767).addContainerGap()));
/*     */ 
/* 122 */     layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -1, -1, 32767).addContainerGap()));
/*     */ 
/* 130 */     pack();
/*     */   }
/*     */ 
/*     */   private void jButton1ActionPerformed(ActionEvent evt) {
/* 134 */     dispose();
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.dialogosAuxiliares.DonacionesJDialog
 * JD-Core Version:    0.6.2
 */