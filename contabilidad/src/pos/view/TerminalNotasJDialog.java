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
/*     */ import pos.view.editors.JEditorString;
/*     */ 
/*     */ public class TerminalNotasJDialog extends JDialog
/*     */ {
/*     */   String nota;
/*     */   private JEditorString campoTexto;
/*     */   private JButton jButton1;
/*     */   private JButton jButton2;
/*     */   private JEditorKeys jEditorKeys1;
/*     */   private JPanel jPanel1;
/*     */ 
/*     */   public TerminalNotasJDialog(Frame parent, boolean modal, String nota)
/*     */   {
/*  27 */     super(parent, modal);
/*  28 */     initComponents();
/*     */ 
/*  30 */     this.nota = nota;
/*  31 */     this.campoTexto.addEditorKeys(this.jEditorKeys1);
/*  32 */     this.campoTexto.activate();
/*  33 */     this.campoTexto.setText(nota);
/*     */   }
/*     */ 
/*     */   public static String obtenerObjeto(Frame parent, boolean modal, String nota) {
/*  37 */     TerminalNotasJDialog dlg = new TerminalNotasJDialog(parent, modal, nota);
/*  38 */     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/*  39 */     Dimension frameSize = dlg.getSize();
/*  40 */     if (frameSize.height > screenSize.height) {
/*  41 */       frameSize.height = screenSize.height;
/*     */     }
/*  43 */     if (frameSize.width > screenSize.width) {
/*  44 */       frameSize.width = screenSize.width;
/*     */     }
/*  46 */     dlg.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
/*     */ 
/*  49 */     dlg.setVisible(true);
/*  50 */     return dlg.getNota();
/*     */   }
/*     */ 
/*     */   public String getNota() {
/*  54 */     return this.nota;
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/*  66 */     this.jPanel1 = new JPanel();
/*  67 */     this.campoTexto = new JEditorString();
/*  68 */     this.jEditorKeys1 = new JEditorKeys();
/*  69 */     this.jButton1 = new JButton();
/*  70 */     this.jButton2 = new JButton();
/*     */ 
/*  72 */     setDefaultCloseOperation(2);
/*  73 */     ResourceBundle bundle = ResourceBundle.getBundle("internationalization/Mensajes");
/*  74 */     setTitle(bundle.getString("nota"));
/*     */ 
/*  76 */     this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/*     */ 
/*  78 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/*  79 */     this.jPanel1.setLayout(jPanel1Layout);
/*  80 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(this.campoTexto, -1, 264, 32767).addContainerGap()));
/*     */ 
/*  87 */     jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(this.campoTexto, -1, 84, 32767).addContainerGap()));
/*     */ 
/*  95 */     this.jButton1.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/button_ok.png")));
/*  96 */     this.jButton1.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/*  98 */         TerminalNotasJDialog.this.jButton1ActionPerformed(evt);
/*     */       }
/*     */     });
/* 102 */     this.jButton2.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/button_cancel.png")));
/* 103 */     this.jButton2.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 105 */         TerminalNotasJDialog.this.jButton2ActionPerformed(evt);
/*     */       }
/*     */     });
/* 109 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 110 */     getContentPane().setLayout(layout);
/* 111 */     layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false).addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -1, -1, 32767)).addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup().addGap(12, 12, 12).addComponent(this.jEditorKeys1, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jButton2, -2, 50, -2).addComponent(this.jButton1, -2, 50, -2)))).addContainerGap(-1, 32767)));
/*     */ 
/* 127 */     layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jEditorKeys1, -2, -1, -2)).addGroup(layout.createSequentialGroup().addGap(26, 26, 26).addComponent(this.jButton1, -2, 50, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jButton2, -2, 50, -2))).addContainerGap(-1, 32767)));
/*     */ 
/* 145 */     pack();
/*     */   }
/*     */ 
/*     */   private void jButton1ActionPerformed(ActionEvent evt) {
/* 149 */     this.nota = this.campoTexto.getText();
/* 150 */     dispose();
/*     */   }
/*     */ 
/*     */   private void jButton2ActionPerformed(ActionEvent evt) {
/* 154 */     this.nota = null;
/* 155 */     dispose();
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.view.TerminalNotasJDialog
 * JD-Core Version:    0.6.2
 */