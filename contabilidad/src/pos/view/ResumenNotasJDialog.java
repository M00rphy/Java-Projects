/*     */ package pos.view;
/*     */ 
/*     */ import java.awt.Container;
/*     */ import java.awt.Frame;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.GroupLayout.Alignment;
/*     */ import javax.swing.GroupLayout.ParallelGroup;
/*     */ import javax.swing.GroupLayout.SequentialGroup;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTextArea;
import javax.swing.LayoutStyle;
/*     */ import javax.swing.LayoutStyle.ComponentPlacement;
/*     */ 
/*     */ public class ResumenNotasJDialog extends JDialog
/*     */ {
/*     */   private JButton jButton1;
/*     */   private JPanel jPanel1;
/*     */   private JScrollPane jScrollPane1;
/*     */   private JTextArea jTextArea1;
/*     */ 
/*     */   public ResumenNotasJDialog(Frame parent, boolean modal, String nota)
/*     */   {
/*  22 */     super(parent, modal);
/*  23 */     initComponents();
/*  24 */     this.jTextArea1.setText(nota);
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/*  36 */     this.jPanel1 = new JPanel();
/*  37 */     this.jScrollPane1 = new JScrollPane();
/*  38 */     this.jTextArea1 = new JTextArea();
/*  39 */     this.jButton1 = new JButton();
/*     */ 
/*  41 */     setDefaultCloseOperation(2);
/*  42 */     ResourceBundle bundle = ResourceBundle.getBundle("internationalization/Mensajes");
/*  43 */     setTitle(bundle.getString("nota"));
/*     */ 
/*  45 */     this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/*     */ 
/*  47 */     this.jTextArea1.setColumns(20);
/*  48 */     this.jTextArea1.setEditable(false);
/*  49 */     this.jTextArea1.setRows(5);
/*  50 */     this.jScrollPane1.setViewportView(this.jTextArea1);
/*     */ 
/*  52 */     this.jButton1.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/button_ok.png")));
/*  53 */     this.jButton1.setText(bundle.getString("bien"));
/*  54 */     this.jButton1.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/*  56 */         ResumenNotasJDialog.this.jButton1ActionPerformed(evt);
/*     */       }
/*     */     });
/*  60 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/*  61 */     this.jPanel1.setLayout(jPanel1Layout);
/*  62 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGap(6, 6, 6).addComponent(this.jButton1)).addComponent(this.jScrollPane1, -1, 308, 32767)).addContainerGap()));
/*     */ 
/*  73 */     jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(this.jScrollPane1, -2, 121, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jButton1, -2, 35, -2).addContainerGap(-1, 32767)));
/*     */ 
/*  83 */     GroupLayout layout = new GroupLayout(getContentPane());
/*  84 */     getContentPane().setLayout(layout);
/*  85 */     layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -1, -1, 32767).addContainerGap()));
/*     */ 
/*  92 */     layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -1, -1, 32767).addContainerGap()));
/*     */ 
/* 100 */     pack();
/*     */   }
/*     */ 
/*     */   private void jButton1ActionPerformed(ActionEvent evt) {
/* 104 */     dispose();
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.view.ResumenNotasJDialog
 * JD-Core Version:    0.6.2
 */