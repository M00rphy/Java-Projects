/*     */ package pos.view;
/*     */ 
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Frame;
/*     */ import java.awt.Toolkit;
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
import javax.swing.LayoutStyle;
/*     */ import javax.swing.LayoutStyle.ComponentPlacement;
/*     */ import pos.view.editors.JEditorKeys;
/*     */ import pos.view.editors.JEditorPassword;
/*     */ 
/*     */ public class UserPasswordJDialog extends JDialog
/*     */ {
/*  23 */   String passw = "";
/*     */   private JButton jButton1;
/*     */   private JEditorKeys jEditorKeys1;
/*     */   private JEditorPassword jEditorPassword1;
/*     */   private JPanel jPanel1;
/*     */ 
/*     */   public UserPasswordJDialog(Frame parent, boolean modal)
/*     */   {
/*  27 */     super(parent, modal);
/*  28 */     initComponents();
/*     */ 
/*  30 */     this.jEditorPassword1.addEditorKeys(this.jEditorKeys1);
/*  31 */     this.jEditorPassword1.activate();
/*     */   }
/*     */ 
/*     */   public static String obtenerObjeto(Frame parent, boolean modal)
/*     */   {
/*  36 */     UserPasswordJDialog dlg = new UserPasswordJDialog(parent, modal);
/*  37 */     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/*  38 */     Dimension frameSize = dlg.getSize();
/*  39 */     if (frameSize.height > screenSize.height) {
/*  40 */       frameSize.height = screenSize.height;
/*     */     }
/*  42 */     if (frameSize.width > screenSize.width) {
/*  43 */       frameSize.width = screenSize.width;
/*     */     }
/*  45 */     dlg.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
/*     */ 
/*  48 */     dlg.setVisible(true);
/*  49 */     return dlg.getPassw();
/*     */   }
/*     */ 
/*     */   public String getPassw() {
/*  53 */     return this.passw;
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/*  65 */     this.jPanel1 = new JPanel();
/*  66 */     this.jEditorPassword1 = new JEditorPassword();
/*  67 */     this.jEditorKeys1 = new JEditorKeys();
/*  68 */     this.jButton1 = new JButton();
/*     */ 
/*  70 */     setDefaultCloseOperation(2);
/*  71 */     ResourceBundle bundle = ResourceBundle.getBundle("internationalization/Mensajes");
/*  72 */     setTitle(bundle.getString("passw"));
/*     */ 
/*  74 */     this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/*     */ 
/*  76 */     this.jButton1.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/button_ok.png")));
/*  77 */     this.jButton1.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/*  79 */         UserPasswordJDialog.this.jButton1ActionPerformed(evt);
/*     */       }
/*     */     });
/*  83 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/*  84 */     this.jPanel1.setLayout(jPanel1Layout);
/*  85 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.jEditorPassword1, -2, 196, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.jButton1, -2, 35, -2)).addComponent(this.jEditorKeys1, -2, -1, -2)).addContainerGap(-1, 32767)));
/*     */ 
/*  97 */     jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jEditorPassword1, -2, -1, -2).addComponent(this.jButton1, -2, 35, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jEditorKeys1, -2, -1, -2).addContainerGap(-1, 32767)));
/*     */ 
/* 109 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 110 */     getContentPane().setLayout(layout);
/* 111 */     layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -1, -1, 32767).addContainerGap()));
/*     */ 
/* 118 */     layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -1, -1, 32767).addContainerGap()));
/*     */ 
/* 126 */     pack();
/*     */   }
/*     */ 
/*     */   private void jButton1ActionPerformed(ActionEvent evt) {
/* 130 */     this.passw = this.jEditorPassword1.getPassword();
/* 131 */     dispose();
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.view.UserPasswordJDialog
 * JD-Core Version:    0.6.2
 */