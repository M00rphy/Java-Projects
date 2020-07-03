/*     */ package contaes.empresas;
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
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ 
/*     */ public class CrearUsuarioJDialog extends JDialog
/*     */ {
/*  20 */   private String usuario = "";
/*  21 */   private String contras = "";
/*  22 */   private String dirIP = "localhost:3306";
/*  23 */   private boolean crear = false;
/*     */   private JTextField campoIp;
/*     */   private JTextField campoPassw;
/*     */   private JTextField campoUsuario;
/*     */   private JButton jButton1;
/*     */   private JButton jButton2;
/*     */   private JLabel jLabel1;
/*     */   private JLabel jLabel2;
/*     */   private JLabel jLabel3;
/*     */   private JPanel jPanel1;
/*     */ 
/*     */   public CrearUsuarioJDialog(Frame parent, boolean modal)
/*     */   {
/*  27 */     super(parent, modal);
/*  28 */     initComponents();
/*  29 */     this.campoIp.setText(this.dirIP);
/*     */   }
/*     */ 
/*     */   public String getContras() {
/*  33 */     return this.contras;
/*     */   }
/*     */ 
/*     */   public boolean isCrear() {
/*  37 */     return this.crear;
/*     */   }
/*     */ 
/*     */   public String getDirIP() {
/*  41 */     return this.dirIP;
/*     */   }
/*     */ 
/*     */   public String getUsuario() {
/*  45 */     return this.usuario;
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/*  57 */     this.jPanel1 = new JPanel();
/*  58 */     this.jLabel1 = new JLabel();
/*  59 */     this.jLabel2 = new JLabel();
/*  60 */     this.jLabel3 = new JLabel();
/*  61 */     this.campoUsuario = new JTextField();
/*  62 */     this.campoPassw = new JTextField();
/*  63 */     this.campoIp = new JTextField();
/*  64 */     this.jButton1 = new JButton();
/*  65 */     this.jButton2 = new JButton();
/*     */ 
/*  67 */     setDefaultCloseOperation(2);
/*  68 */     ResourceBundle bundle = ResourceBundle.getBundle("internationalization/Mensajes");
/*  69 */     setTitle(bundle.getString("usuario"));
/*     */ 
/*  71 */     this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/*     */ 
/*  73 */     this.jLabel1.setText(bundle.getString("usuario"));
/*     */ 
/*  75 */     this.jLabel2.setText(bundle.getString("passw"));
/*     */ 
/*  77 */     this.jLabel3.setText(bundle.getString("DirIP"));
/*     */ 
/*  79 */     this.jButton1.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/MgestionUsu.png")));
/*  80 */     this.jButton1.setText(bundle.getString("crear"));
/*  81 */     this.jButton1.setHorizontalAlignment(10);
/*  82 */     this.jButton1.setHorizontalTextPosition(2);
/*  83 */     this.jButton1.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/*  85 */         CrearUsuarioJDialog.this.jButton1ActionPerformed(evt);
/*     */       }
/*     */     });
/*  89 */     this.jButton2.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/cancel.png")));
/*  90 */     this.jButton2.setText(bundle.getString("cancelar"));
/*  91 */     this.jButton2.setHorizontalTextPosition(2);
/*  92 */     this.jButton2.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/*  94 */         CrearUsuarioJDialog.this.jButton2ActionPerformed(evt);
/*     */       }
/*     */     });
/*  98 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/*  99 */     this.jPanel1.setLayout(jPanel1Layout);
/* 100 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel3).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel2).addComponent(this.jLabel1)).addGap(26, 26, 26).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.campoIp).addComponent(this.campoPassw).addComponent(this.campoUsuario, -1, 162, 32767))))).addGroup(jPanel1Layout.createSequentialGroup().addGap(47, 47, 47).addComponent(this.jButton1).addGap(38, 38, 38).addComponent(this.jButton2))).addContainerGap(-1, 32767)));
/*     */ 
/* 124 */     jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel1).addComponent(this.campoUsuario, -2, -1, -2)).addGap(18, 18, 18).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel2).addComponent(this.campoPassw, -2, -1, -2)).addGap(18, 18, 18).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel3).addComponent(this.campoIp, -2, -1, -2)).addGap(26, 26, 26).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jButton1).addComponent(this.jButton2)).addContainerGap(-1, 32767)));
/*     */ 
/* 146 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 147 */     getContentPane().setLayout(layout);
/* 148 */     layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -1, -1, 32767).addContainerGap()));
/*     */ 
/* 155 */     layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -1, -1, 32767).addContainerGap()));
/*     */ 
/* 163 */     pack();
/*     */   }
/*     */ 
/*     */   private void jButton1ActionPerformed(ActionEvent evt) {
/* 167 */     this.usuario = this.campoUsuario.getText();
/* 168 */     this.contras = this.campoPassw.getText();
/* 169 */     this.dirIP = this.campoIp.getText();
/* 170 */     if (!this.usuario.equals(""))
/* 171 */       this.crear = true;
/*     */     else
/* 173 */       this.crear = false;
/* 174 */     dispose();
/*     */   }
/*     */ 
/*     */   private void jButton2ActionPerformed(ActionEvent evt) {
/* 178 */     this.crear = false;
/* 179 */     dispose();
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.empresas.CrearUsuarioJDialog
 * JD-Core Version:    0.6.2
 */