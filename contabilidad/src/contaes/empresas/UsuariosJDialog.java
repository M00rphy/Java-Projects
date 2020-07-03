/*     */ package contaes.empresas;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.Puente;
/*     */ import contaes.manejoDatos.auxiliar.ConfiguracionBean;
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Frame;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.KeyAdapter;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.util.ArrayList;
import java.util.List;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.BorderFactory;
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
/*     */ import javax.swing.JPasswordField;
/*     */ import javax.swing.JTextField;
import javax.swing.LayoutStyle;
/*     */ import javax.swing.LayoutStyle.ComponentPlacement;
/*     */ import pos.view.UserPasswordJDialog;
/*     */ 
/*     */ public class UsuariosJDialog extends JDialog
/*     */ {
/*  27 */   private ArrayList<String> nombres = new ArrayList();
/*  28 */   private ArrayList<String> contras = new ArrayList();
/*  29 */   private ArrayList<String> dirip = new ArrayList();
/*  30 */   DesEncrypter encrypter = new DesEncrypter("juanmiguel");
/*  31 */   private boolean modoPOS = false;
/*     */   private JButton Borrar;
/*     */   private JPasswordField cPassword;
/*     */   private JTextField cdirIP;
/*     */   private JButton jButton1;
/*     */   private JComboBox jComboBox1;
/*     */   private JLabel jLabel1;
/*     */   private JLabel jLabel2;
/*     */   private JLabel jLabel3;
/*     */   private JPanel jPanel1;
/*     */   private JPanel jPanel2;
/*     */   private JButton seleccionar;
/*     */ 
/*     */   public UsuariosJDialog(boolean modoPOS)
/*     */   {
/*  35 */     super(new Frame(), true);
/*  36 */     this.modoPOS = modoPOS;
/*  37 */     initComponents();
/*  38 */     llenarListaUsuarios();
/*  39 */     this.jComboBox1.setSelectedIndex(0);
/*  40 */     this.cdirIP.setText((String)this.dirip.get(0));
/*  41 */     if (modoPOS) {
/*  42 */       this.jLabel2.setText("");
/*  43 */       this.cPassword.setEnabled(false);
/*  44 */       this.cPassword.setVisible(false);
/*  45 */       this.jButton1.setEnabled(false);
/*     */     }
/*     */     else
/*     */     {
/*  49 */       this.cPassword.requestFocus();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void seleccionDeUsuario(boolean modoPOS) {
/*  54 */     UsuariosJDialog dlg = new UsuariosJDialog(modoPOS);
/*  55 */     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/*  56 */     Dimension frameSize = dlg.getSize();
/*  57 */     if (frameSize.height > screenSize.height) {
/*  58 */       frameSize.height = screenSize.height;
/*     */     }
/*  60 */     if (frameSize.width > screenSize.width) {
/*  61 */       frameSize.width = screenSize.width;
/*     */     }
/*  63 */     dlg.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
/*     */ 
/*  65 */     dlg.pack();
/*  66 */     dlg.setVisible(true);
/*     */   }
/*     */ 
/*     */   private void llenarListaUsuarios()
/*     */   {
/*  74 */     ConfiguracionBean config = new ConfiguracionBean();
/*  75 */     this.nombres = config.getConfig("<usuarios>");
/*  76 */     ArrayList contrasE = config.getConfig("<controles>");
/*  77 */     this.dirip = config.getConfig("<direccionip>");
/*     */ 
/*  79 */     this.jComboBox1.removeAllItems();
/*     */ 
/*  81 */     for (String string : this.nombres) {
/*  82 */       this.jComboBox1.addItem(string);
/*     */     }
/*  84 */     for (String string :(List<String>)  contrasE)
/*  85 */       this.contras.add(this.encrypter.decrypt(string));
/*     */   }
/*     */ 
/*     */   private void addUsuario()
/*     */   {
/*  94 */     CrearUsuarioJDialog dlg = new CrearUsuarioJDialog(null, true);
/*  95 */     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/*  96 */     Dimension frameSize = dlg.getSize();
/*  97 */     if (frameSize.height > screenSize.height) {
/*  98 */       frameSize.height = screenSize.height;
/*     */     }
/* 100 */     if (frameSize.width > screenSize.width) {
/* 101 */       frameSize.width = screenSize.width;
/*     */     }
/* 103 */     dlg.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
/*     */ 
/* 105 */     dlg.pack();
/* 106 */     dlg.setVisible(true);
/* 107 */     if (dlg.isCrear()) {
/* 108 */       String nombre = dlg.getUsuario();
/* 109 */       String password = dlg.getContras();
/* 110 */       String direccion = dlg.getDirIP();
/* 111 */       this.nombres.add(nombre);
/* 112 */       this.jComboBox1.addItem(nombre);
/* 113 */       this.contras.add(new String(password));
/* 114 */       this.dirip.add(direccion);
/* 115 */       guardarLista();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void seleccionarUsuario()
/*     */   {
/* 124 */     if (!aceptar()) {
/* 125 */       JOptionPane.showMessageDialog(getContentPane(), Mensajes.getString("usuEt3"));
/*     */ 
/* 127 */       this.cPassword.setText("");
/*     */     } else {
/* 129 */       dispose();
/*     */     }
/*     */   }
/*     */ 
/*     */   private boolean aceptar()
/*     */   {
/* 139 */     String passw = "";
/* 140 */     if (this.modoPOS) {
/* 141 */       passw = UserPasswordJDialog.obtenerObjeto(new Frame(), true);
/*     */     }
/*     */     else {
/* 144 */       char[] contr = null;
/*     */       try {
/* 146 */         contr = this.cPassword.getPassword();
/*     */       } catch (NullPointerException nexc) {
/* 148 */         nexc.printStackTrace();
/*     */       }
/*     */ 
/* 151 */       if (contr != null) {
/* 152 */         passw = new String(contr);
/*     */       }
/*     */     }
/* 155 */     String nombreUsuario = this.jComboBox1.getSelectedItem().toString();
/* 156 */     String direccionIP = this.cdirIP.getText();
/* 157 */     if (((String)this.contras.get(this.jComboBox1.getSelectedIndex())).equals(passw)) {
/* 158 */       Inicio.p.setUsuario(nombreUsuario);
/* 159 */       Inicio.p.setPassword(passw);
/* 160 */       Inicio.p.setDireccionIP(direccionIP);
/* 161 */       return true;
/*     */     }
/* 163 */     return false;
/*     */   }
/*     */ 
/*     */   private void borrarUsuario()
/*     */   {
/* 172 */     if (this.jComboBox1.getItemCount() <= 1) {
/* 173 */       JOptionPane.showMessageDialog(getContentPane(), "La lista de usuarios no puede quedar vacía", Mensajes.getString("informacion"), 0);
/*     */ 
/* 175 */       return;
/*     */     }
/* 177 */     int confirma = JOptionPane.showConfirmDialog(getContentPane(), Mensajes.getString("usuEt4") + "\n" + Mensajes.getString("usuEt5"), Mensajes.getString("confirma"), 0);
/*     */ 
/* 181 */     if (confirma == 0) {
/* 182 */       int index = this.jComboBox1.getSelectedIndex();
/* 183 */       this.jComboBox1.removeItemAt(index);
/* 184 */       this.nombres.remove(index);
/* 185 */       this.contras.remove(index);
/* 186 */       this.dirip.remove(index);
/* 187 */       guardarLista();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void guardarLista()
/*     */   {
/* 197 */     ArrayList contraE = new ArrayList();
/*     */ 
/* 201 */     for (String string : this.contras) {
/* 202 */       contraE.add(this.encrypter.encrypt(string));
/*     */     }
/* 204 */     ConfiguracionBean config = new ConfiguracionBean();
/* 205 */     config.saveConfig("<usuarios>", this.nombres);
/* 206 */     config.saveConfig("<controles>", contraE);
/* 207 */     config.saveConfig("<direccionip>", this.dirip);
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/* 219 */     this.jPanel1 = new JPanel();
/* 220 */     this.jLabel1 = new JLabel();
/* 221 */     this.jComboBox1 = new JComboBox();
/* 222 */     this.jLabel2 = new JLabel();
/* 223 */     this.cPassword = new JPasswordField();
/* 224 */     this.jLabel3 = new JLabel();
/* 225 */     this.cdirIP = new JTextField();
/* 226 */     this.seleccionar = new JButton();
/* 227 */     this.jPanel2 = new JPanel();
/* 228 */     this.jButton1 = new JButton();
/* 229 */     this.Borrar = new JButton();
/*     */ 
/* 231 */     setDefaultCloseOperation(2);
/* 232 */     ResourceBundle bundle = ResourceBundle.getBundle("internationalization/Mensajes");
/* 233 */     setTitle(bundle.getString("welcome"));
/*     */ 
/* 235 */     this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/*     */ 
/* 237 */     this.jLabel1.setText(bundle.getString("usuario"));
/*     */ 
/* 239 */     this.jComboBox1.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 241 */         UsuariosJDialog.this.jComboBox1ActionPerformed(evt);
/*     */       }
/*     */     });
/* 245 */     this.jLabel2.setText(bundle.getString("passw"));
/*     */ 
/* 247 */     this.cPassword.addKeyListener(new KeyAdapter() {
/*     */       public void keyPressed(KeyEvent evt) {
/* 249 */         UsuariosJDialog.this.cPasswordKeyPressed(evt);
/*     */       }
/*     */     });
/* 253 */     this.jLabel3.setText(bundle.getString("DirIP"));
/*     */ 
/* 255 */     this.seleccionar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/apply.png")));
/* 256 */     this.seleccionar.setText(bundle.getString("seleccionar"));
/* 257 */     this.seleccionar.setHorizontalTextPosition(2);
/* 258 */     this.seleccionar.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 260 */         UsuariosJDialog.this.seleccionarActionPerformed(evt);
/*     */       }
/*     */     });
/* 264 */     this.jPanel2.setBorder(BorderFactory.createEtchedBorder());
/*     */ 
/* 266 */     this.jButton1.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/new.png")));
/* 267 */     this.jButton1.setText(bundle.getString("add"));
/* 268 */     this.jButton1.setHorizontalTextPosition(2);
/* 269 */     this.jButton1.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 271 */         UsuariosJDialog.this.jButton1ActionPerformed(evt);
/*     */       }
/*     */     });
/* 275 */     this.Borrar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/delete.png")));
/* 276 */     this.Borrar.setText(bundle.getString("borrar"));
/* 277 */     this.Borrar.setHorizontalTextPosition(2);
/* 278 */     this.Borrar.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 280 */         UsuariosJDialog.this.BorrarActionPerformed(evt);
/*     */       }
/*     */     });
/* 284 */     GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
/* 285 */     this.jPanel2.setLayout(jPanel2Layout);
/* 286 */     jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addComponent(this.jButton1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 24, 32767).addComponent(this.Borrar).addContainerGap()));
/*     */ 
/* 295 */     jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jButton1).addComponent(this.Borrar)).addContainerGap(-1, 32767)));
/*     */ 
/* 305 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 306 */     this.jPanel1.setLayout(jPanel1Layout);
/* 307 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGap(6, 6, 6).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel2).addComponent(this.jLabel1).addComponent(this.jLabel3)).addGap(21, 21, 21).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.jComboBox1, 0, -1, 32767).addComponent(this.cPassword).addGroup(jPanel1Layout.createSequentialGroup().addGap(6, 6, 6).addComponent(this.cdirIP, -2, 143, -2)))).addGroup(jPanel1Layout.createSequentialGroup().addGap(26, 26, 26).addComponent(this.seleccionar)).addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel2, -2, -1, -2))).addContainerGap(19, 32767)));
/*     */ 
/* 332 */     jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel1).addComponent(this.jComboBox1, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel2).addComponent(this.cPassword, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel3).addComponent(this.cdirIP, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.seleccionar).addGap(18, 18, 18).addComponent(this.jPanel2, -2, -1, -2).addGap(105, 105, 105)));
/*     */ 
/* 354 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 355 */     getContentPane().setLayout(layout);
/* 356 */     layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -1, -1, 32767).addContainerGap()));
/*     */ 
/* 363 */     layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -2, 235, -2).addContainerGap(-1, 32767)));
/*     */ 
/* 371 */     pack();
/*     */   }
/*     */ 
/*     */   private void jButton1ActionPerformed(ActionEvent evt) {
/* 375 */     addUsuario();
/*     */   }
/*     */ 
/*     */   private void jComboBox1ActionPerformed(ActionEvent evt) {
/* 379 */     int indice = this.jComboBox1.getSelectedIndex();
/* 380 */     if (indice != -1) {
/* 381 */       this.cdirIP.setText((String)this.dirip.get(indice));
/* 382 */       this.cPassword.setText("");
/* 383 */       this.cPassword.requestFocusInWindow();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void seleccionarActionPerformed(ActionEvent evt) {
/* 388 */     seleccionarUsuario();
/*     */   }
/*     */ 
/*     */   private void BorrarActionPerformed(ActionEvent evt) {
/* 392 */     borrarUsuario();
/*     */   }
/*     */ 
/*     */   private void cPasswordKeyPressed(KeyEvent evt) {
/* 396 */     if (evt.getKeyCode() == 10)
/* 397 */       seleccionarUsuario();
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.empresas.UsuariosJDialog
 * JD-Core Version:    0.6.2
 */