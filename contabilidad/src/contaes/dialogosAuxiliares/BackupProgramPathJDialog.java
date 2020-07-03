/*     */ package contaes.dialogosAuxiliares;
/*     */ 
/*     */ import contaes.manejoDatos.auxiliar.ConfiguracionBean;
/*     */ import java.awt.Container;
/*     */ import java.awt.Frame;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Properties;
/*     */ import java.util.ResourceBundle;
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
/*     */ public class BackupProgramPathJDialog extends JDialog
/*     */ {
/*     */   private JButton jButton1;
/*     */   private JButton jButton2;
/*     */   private JLabel jLabel1;
/*     */   private JPanel jPanel1;
/*     */   private JTextField jTextField1;
/*     */ 
/*     */   public BackupProgramPathJDialog(Frame parent, boolean modal)
/*     */   {
/*  26 */     super(parent, modal);
/*  27 */     initComponents();
/*  28 */     this.jTextField1.setText(getDefaultPath());
/*     */   }
/*     */ 
/*     */   private String getDefaultPath() {
/*  32 */     String path = "/usr/local/mysql/bin/";
/*  33 */     ConfiguracionBean c = new ConfiguracionBean();
/*  34 */     ArrayList lista = c.getConfig("<backprogrampath>");
/*  35 */     if (lista.isEmpty()) {
/*  36 */       Properties sistema = System.getProperties();
/*  37 */       String sisOp = sistema.getProperty("os.name").substring(0, 3);
/*  38 */       if (sisOp.equals("Win")) {
/*  39 */         path = "C:\\Archivos de programa\\MySQL\\MySQL Server 5.1\\bin";
/*     */       }
/*  41 */       else if (sisOp.equals("Lin"))
/*  42 */         path = "";
/*     */     }
/*     */     else
/*     */     {
/*  46 */       path = (String)lista.get(0);
/*     */     }
/*  48 */     return path;
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/*  60 */     this.jPanel1 = new JPanel();
/*  61 */     this.jLabel1 = new JLabel();
/*  62 */     this.jTextField1 = new JTextField();
/*  63 */     this.jButton1 = new JButton();
/*  64 */     this.jButton2 = new JButton();
/*     */ 
/*  66 */     setDefaultCloseOperation(2);
/*  67 */     setTitle("Ruta MySQL");
/*     */ 
/*  69 */     this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/*     */ 
/*  71 */     this.jLabel1.setText("Introduzca la ruta a la carpeta de su instalación de MySQL:");
/*     */ 
/*  73 */     this.jButton1.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/save16.png")));
/*  74 */     this.jButton1.setText("Guardar");
/*  75 */     this.jButton1.setHorizontalTextPosition(2);
/*  76 */     this.jButton1.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/*  78 */         BackupProgramPathJDialog.this.jButton1ActionPerformed(evt);
/*     */       }
/*     */     });
/*  82 */     this.jButton2.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/cancel.png")));
/*  83 */     ResourceBundle bundle = ResourceBundle.getBundle("internationalization/Mensajes");
/*  84 */     this.jButton2.setText(bundle.getString("cancelar"));
/*  85 */     this.jButton2.setHorizontalTextPosition(2);
/*  86 */     this.jButton2.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/*  88 */         BackupProgramPathJDialog.this.jButton2ActionPerformed(evt);
/*     */       }
/*     */     });
/*  92 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/*  93 */     this.jPanel1.setLayout(jPanel1Layout);
/*  94 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jTextField1, -1, 367, 32767).addComponent(this.jLabel1, -1, -1, 32767).addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup().addComponent(this.jButton1).addGap(18, 18, 18).addComponent(this.jButton2))).addContainerGap(11, 32767)));
/*     */ 
/* 107 */     jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jTextField1, -2, -1, -2).addGap(18, 18, 18).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jButton2).addComponent(this.jButton1)).addContainerGap(7, 32767)));
/*     */ 
/* 121 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 122 */     getContentPane().setLayout(layout);
/* 123 */     layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -1, -1, 32767).addContainerGap()));
/*     */ 
/* 130 */     layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -1, -1, 32767).addContainerGap()));
/*     */ 
/* 138 */     pack();
/*     */   }
/*     */ 
/*     */   private void jButton1ActionPerformed(ActionEvent evt) {
/* 142 */     ConfiguracionBean c = new ConfiguracionBean();
/* 143 */     String newPath = this.jTextField1.getText();
/* 144 */     if (!newPath.equals("")) {
/* 145 */       ArrayList lista = new ArrayList();
/* 146 */       lista.add(newPath);
/* 147 */       c.saveConfig("<backprogrampath>", lista);
/* 148 */       dispose();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void jButton2ActionPerformed(ActionEvent evt) {
/* 153 */     dispose();
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.dialogosAuxiliares.BackupProgramPathJDialog
 * JD-Core Version:    0.6.2
 */