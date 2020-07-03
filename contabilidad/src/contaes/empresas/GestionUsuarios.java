/*     */ package contaes.empresas;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.Puente;
/*     */ import contaes.manejoDatos.auxiliar.GrabarFichero;
/*     */ import contaes.manejoDatos.auxiliar.LeerFichero;
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.Container;
/*     */ import java.awt.Frame;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.KeyAdapter;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.io.IOException;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JPasswordField;
/*     */ import javax.swing.JTextField;
/*     */ 
/*     */ public class GestionUsuarios extends JDialog
/*     */ {
/*  54 */   private boolean llamaInicio = false;
/*  55 */   int i = 0;
/*  56 */   String[] usuarios = new String[10];
/*  57 */   String[] psswords = new String[10];
/*  58 */   DesEncrypter encrypter = new DesEncrypter("juanmiguel");
/*  59 */   JPanel panel1 = new JPanel();
/*  60 */   JTextField nuevoUsuario = new JTextField();
/*  61 */   JPasswordField cPassword = new JPasswordField();
/*  62 */   JLabel ePassword = new JLabel();
/*  63 */   JLabel eUsuario = new JLabel();
/*  64 */   JButton anhadir = new JButton();
/*  65 */   JComboBox jComboBox1 = new JComboBox();
/*  66 */   JButton seleccionar = new JButton();
/*  67 */   JButton Borrar = new JButton();
/*  68 */   GridBagLayout gridBagLayout1 = new GridBagLayout();
/*     */ 
/*     */   public GestionUsuarios(Frame owner, String title, boolean modal)
/*     */   {
/*  78 */     this(owner, title, modal, false);
/*     */   }
/*     */ 
/*     */   public GestionUsuarios(Frame owner, String title, boolean modal, boolean llama) {
/*  82 */     super(owner, title, modal);
/*  83 */     this.llamaInicio = true;
/*     */     try {
/*  85 */       setDefaultCloseOperation(2);
/*  86 */       llenarListaUsuarios();
/*  87 */       initialize();
/*  88 */       pack();
/*  89 */       this.cPassword.requestFocus();
/*     */     }
/*     */     catch (Exception exception) {
/*  92 */       exception.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public GestionUsuarios(boolean llama)
/*     */   {
/* 101 */     this(new Frame(), Mensajes.getString("welcome"), true, llama);
/*     */   }
/*     */ 
/*     */   private void initialize()
/*     */     throws Exception
/*     */   {
/* 111 */     this.panel1.setLayout(this.gridBagLayout1);
/* 112 */     this.cPassword.addKeyListener(new KeyAdapter() {
/*     */       public void keyPressed(KeyEvent e) {
/* 114 */         if (e.getKeyCode() == 10)
/* 115 */           GestionUsuarios.this.seleccionarUsuario();
/*     */       }
/*     */     });
/* 119 */     this.nuevoUsuario.setText("");
/* 120 */     this.nuevoUsuario.setToolTipText(Mensajes.getString("usuEt1"));
/* 121 */     this.anhadir.setText(Mensajes.getString("add"));
/* 122 */     this.anhadir.setToolTipText(Mensajes.getString("usuEt2"));
/* 123 */     this.anhadir.setHorizontalTextPosition(2);
/* 124 */     this.anhadir.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/new.png")));
/* 125 */     this.anhadir.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 127 */         GestionUsuarios.this.anhadirUsuario();
/*     */       }
/*     */     });
/* 130 */     this.seleccionar.setText(Mensajes.getString("seleccionar"));
/* 131 */     this.seleccionar.setHorizontalTextPosition(2);
/* 132 */     this.seleccionar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/ok.png")));
/* 133 */     this.seleccionar.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 135 */         GestionUsuarios.this.seleccionarUsuario();
/*     */       }
/*     */     });
/* 138 */     this.Borrar.setText(Mensajes.getString("borrar"));
/* 139 */     this.Borrar.setHorizontalTextPosition(2);
/* 140 */     this.Borrar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/delete.png")));
/* 141 */     this.Borrar.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 143 */         GestionUsuarios.this.borrarUsuario();
/*     */       }
/*     */     });
/* 146 */     this.ePassword.setText(Mensajes.getString("passw"));
/* 147 */     this.eUsuario.setText(Mensajes.getString("usuario"));
/*     */ 
/* 149 */     GridBagConstraints cons = new GridBagConstraints();
/* 150 */     cons.insets.bottom = 7;
/* 151 */     cons.insets.top = 7;
/* 152 */     cons.insets.left = 7;
/* 153 */     cons.insets.right = 7;
/* 154 */     cons.gridx = 0;
/* 155 */     cons.gridy = 0;
/* 156 */     cons.fill = 2;
/* 157 */     cons.weighty = 1.0D;
/* 158 */     this.gridBagLayout1.setConstraints(this.nuevoUsuario, cons);
/* 159 */     this.panel1.add(this.nuevoUsuario);
/* 160 */     cons.fill = 0;
/* 161 */     cons.weighty = 0.0D;
/* 162 */     cons.gridx = 1;
/* 163 */     this.gridBagLayout1.setConstraints(this.anhadir, cons);
/* 164 */     this.panel1.add(this.anhadir);
/* 165 */     cons.gridy = 3;
/* 166 */     cons.gridx = 0;
/* 167 */     this.gridBagLayout1.setConstraints(this.seleccionar, cons);
/* 168 */     this.panel1.add(this.seleccionar);
/* 169 */     cons.gridx = 1;
/* 170 */     this.gridBagLayout1.setConstraints(this.Borrar, cons);
/* 171 */     this.panel1.add(this.Borrar);
/* 172 */     cons.fill = 2;
/* 173 */     cons.gridy = 1;
/* 174 */     cons.gridx = 1;
/* 175 */     cons.weighty = 1.0D;
/* 176 */     this.gridBagLayout1.setConstraints(this.jComboBox1, cons);
/* 177 */     this.panel1.add(this.jComboBox1);
/* 178 */     cons.gridy = 2;
/* 179 */     cons.gridx = 1;
/* 180 */     this.gridBagLayout1.setConstraints(this.cPassword, cons);
/* 181 */     this.panel1.add(this.cPassword);
/* 182 */     cons.gridx = 0;
/* 183 */     cons.weighty = 0.0D;
/* 184 */     cons.fill = 0;
/* 185 */     this.gridBagLayout1.setConstraints(this.ePassword, cons);
/* 186 */     this.panel1.add(this.ePassword);
/* 187 */     cons.gridy = 1;
/* 188 */     cons.gridx = 0;
/* 189 */     this.gridBagLayout1.setConstraints(this.eUsuario, cons);
/* 190 */     this.panel1.add(this.eUsuario);
/* 191 */     this.panel1.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createEtchedBorder(1)));
/*     */ 
/* 193 */     getContentPane().add(this.panel1);
/*     */   }
/*     */ 
/*     */   private void llenarListaUsuarios()
/*     */   {
/* 201 */     this.jComboBox1.removeAllItems();
/* 202 */     this.i = -1;
/*     */     try {
/* 204 */       LeerFichero entrada = new LeerFichero("configdir/Usuarios.txt");
/*     */       do
/* 206 */         this.usuarios[(++this.i)] = entrada.leer();
/* 207 */       while ((this.usuarios[this.i] != null) && (this.i < 10));
/* 208 */       this.i -= 1;
/* 209 */       entrada.cerrar();
/* 210 */       for (int x = 0; x <= this.i; x++) {
/* 211 */         this.jComboBox1.addItem(this.usuarios[x]);
/*     */       }
/* 213 */       if (this.i >= 0) {
/* 214 */         entrada = new LeerFichero("configdir/Controles.txt");
/* 215 */         for (int x = 0; x <= this.i; x++) {
/* 216 */           this.psswords[x] = this.encrypter.decrypt(entrada.leer());
/*     */         }
/* 218 */         entrada.cerrar();
/*     */       }
/*     */     } catch (IOException e) {
/* 221 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void anhadirUsuario()
/*     */   {
/* 246 */     this.i += 1;
/* 247 */     this.usuarios[this.i] = this.nuevoUsuario.getText();
/* 248 */     this.jComboBox1.addItem(this.usuarios[this.i]);
/* 249 */     this.nuevoUsuario.setText("");
/* 250 */     char[] contr = null;
/*     */     try {
/* 252 */       contr = this.cPassword.getPassword();
/*     */     } catch (NullPointerException nexc) {
/*     */     }
/* 255 */     if (contr != null)
/* 256 */       this.psswords[this.i] = new String(contr);
/* 257 */     else this.psswords[this.i] = "";
/* 258 */     this.cPassword.setText("");
/* 259 */     guardarLista();
/*     */   }
/*     */ 
/*     */   private void seleccionarUsuario()
/*     */   {
/* 267 */     if (aceptar()) {
/* 268 */       if (this.llamaInicio);
/* 274 */       dispose();
/*     */     } else {
/* 276 */       JOptionPane.showMessageDialog(getContentPane(), Mensajes.getString("usuEt3"));
/*     */     }
/* 278 */     this.cPassword.setText("");
/*     */   }
/*     */ 
/*     */   private boolean aceptar()
/*     */   {
/* 287 */     char[] contr = null;
/*     */     try {
/* 289 */       contr = this.cPassword.getPassword();
/*     */     } catch (NullPointerException nexc) {
/*     */     }
/* 292 */     String contras = "";
/* 293 */     if (contr != null) contras = new String(contr);
/* 294 */     if (this.psswords[this.jComboBox1.getSelectedIndex()].equals(contras)) {
/* 295 */       Inicio.p.setUsuario(this.jComboBox1.getItemAt(this.jComboBox1.getSelectedIndex()).toString());
/* 296 */       Inicio.p.setPassword(this.psswords[this.jComboBox1.getSelectedIndex()]);
/* 297 */       return true;
/*     */     }
/* 299 */     return false;
/*     */   }
/*     */ 
/*     */   private void borrarUsuario()
/*     */   {
/* 307 */     int confirma = JOptionPane.showConfirmDialog(getContentPane(), Mensajes.getString("usuEt4") + "\n" + Mensajes.getString("usuEt5"), Mensajes.getString("confirma"), 0);
/*     */ 
/* 311 */     if (confirma == 0) {
/* 312 */       int numUsu = this.jComboBox1.getSelectedIndex();
/* 313 */       this.jComboBox1.removeItemAt(numUsu);
/* 314 */       this.i = (this.jComboBox1.getItemCount() - 1);
/* 315 */       for (int x = 0; x <= this.i; x++) {
/* 316 */         this.usuarios[x] = this.jComboBox1.getItemAt(x).toString();
/*     */       }
/* 318 */       int y = 0;
/* 319 */       for (int x = 0; x <= this.i + 1; x++) {
/* 320 */         if (x != numUsu) {
/* 321 */           this.psswords[y] = this.psswords[x];
/* 322 */           y++;
/*     */         }
/*     */       }
/* 325 */       guardarLista();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void guardarLista()
/*     */   {
/* 335 */     GrabarFichero sal = new GrabarFichero("configdir/Usuarios.txt");
/* 336 */     for (int x = 0; x <= this.i; x++) {
/* 337 */       sal.insertar(this.usuarios[x] + "\n");
/*     */     }
/* 339 */     sal.cerrar();
/* 340 */     sal = new GrabarFichero("configdir/Controles.txt");
/* 341 */     for (int x = 0; x <= this.i; x++) {
/* 342 */       sal.insertar(this.encrypter.encrypt(this.psswords[x]) + "\n");
/*     */     }
/* 344 */     sal.cerrar();
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.empresas.GestionUsuarios
 * JD-Core Version:    0.6.2
 */