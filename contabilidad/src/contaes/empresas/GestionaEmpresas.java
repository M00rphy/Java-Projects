/*     */ package contaes.empresas;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.Puente;
/*     */ import contaes.auxiliar.DocDiezCarac;
/*     */ import contaes.manejoDatos.ManejoEmpresas;
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.Frame;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Insets;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.LinkedList;
import java.util.List;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ 
/*     */ public class GestionaEmpresas extends JDialog
/*     */ {
/*  51 */   private JPanel jContentPane = null;
/*  52 */   private JLabel jLabel = null;
/*  53 */   private JComboBox listaEmpresas = null;
/*  54 */   private JTextField cNombre = null;
/*  55 */   private JLabel jLabel1 = null;
/*  56 */   private JLabel jLabel2 = null;
/*  57 */   private JLabel jLabel3 = null;
/*  58 */   private JLabel jLabel4 = null;
/*  59 */   private JLabel jLabel5 = null;
/*  60 */   private JTextField cNIF = null;
/*  61 */   private JTextField cDireccion = null;
/*  62 */   private JTextField cLocalidad = null;
/*  63 */   private JTextField cProvincia = null;
/*  64 */   private JTextField cTelefono = null;
/*  65 */   private JLabel jLabel6 = null;
/*  66 */   private JLabel jLabel7 = null;
/*  67 */   private JTextField cFax = null;
/*  68 */   private JTextField cCP = null;
/*  69 */   private JButton bNueva = null;
/*  70 */   private JPanel jPanel = null;
/*  71 */   private JButton bBorrar = null;
/*  72 */   private JButton bSalir = null;
/*  73 */   private JButton bModificar = null;
/*  74 */   private boolean esNueva = false;
/*  75 */   private JLabel jLabel8 = null;
/*  76 */   private JTextField cPrejifo = null;
/*  77 */   private ManejoEmpresas empresaM = null;
/*  78 */   private Acciones escuchaAccion = null;
/*     */ 
/*     */   public GestionaEmpresas()
/*     */   {
/*  84 */     this(new Frame(), Mensajes.getString("gesEmp"), false);
/*     */   }
/*     */ 
/*     */   public GestionaEmpresas(Frame owner, String title, boolean modal)
/*     */   {
/*  95 */     super(owner, title, modal);
/*  96 */     this.empresaM = new ManejoEmpresas(Inicio.getCGeneral(), Inicio.p.getEmpresa());
/*  97 */     this.escuchaAccion = new Acciones();
/*  98 */     initialize();
/*     */   }
/*     */ 
/*     */   private void initialize()
/*     */   {
/* 107 */     setSize(420, 300);
/* 108 */     setContentPane(getJContentPane());
/* 109 */     if (this.listaEmpresas.getItemCount() > 0)
/* 110 */       cambiaEmpresa(this.listaEmpresas.getSelectedItem().toString());
/*     */   }
/*     */ 
/*     */   private JPanel getJContentPane()
/*     */   {
/* 120 */     if (this.jContentPane == null) {
/* 121 */       GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
/* 122 */       gridBagConstraints21.fill = 2;
/* 123 */       gridBagConstraints21.gridy = 2;
/* 124 */       gridBagConstraints21.weightx = 1.0D;
/* 125 */       gridBagConstraints21.insets = new Insets(3, 10, 2, 80);
/* 126 */       gridBagConstraints21.gridx = 3;
/* 127 */       GridBagConstraints gridBagConstraints110 = new GridBagConstraints();
/* 128 */       gridBagConstraints110.gridx = 2;
/* 129 */       gridBagConstraints110.anchor = 13;
/* 130 */       gridBagConstraints110.insets = new Insets(3, 5, 2, 0);
/* 131 */       gridBagConstraints110.gridy = 2;
/* 132 */       this.jLabel8 = new JLabel();
/* 133 */       this.jLabel8.setText(Mensajes.getString("prefijo"));
/* 134 */       GridBagConstraints gridBagConstraints18 = new GridBagConstraints();
/* 135 */       gridBagConstraints18.fill = 2;
/* 136 */       gridBagConstraints18.gridy = 1;
/* 137 */       gridBagConstraints18.weightx = 1.0D;
/* 138 */       gridBagConstraints18.anchor = 17;
/* 139 */       gridBagConstraints18.insets = new Insets(3, 10, 2, 150);
/* 140 */       gridBagConstraints18.gridwidth = 4;
/* 141 */       gridBagConstraints18.gridx = 0;
/* 142 */       GridBagConstraints gridBagConstraints17 = new GridBagConstraints();
/* 143 */       gridBagConstraints17.gridx = 0;
/* 144 */       gridBagConstraints17.gridwidth = 4;
/* 145 */       gridBagConstraints17.insets = new Insets(10, 10, 10, 10);
/* 146 */       gridBagConstraints17.fill = 2;
/* 147 */       gridBagConstraints17.gridy = 7;
/* 148 */       GridBagConstraints gridBagConstraints16 = new GridBagConstraints();
/* 149 */       gridBagConstraints16.gridx = 3;
/* 150 */       gridBagConstraints16.anchor = 10;
/* 151 */       gridBagConstraints16.insets = new Insets(0, 0, 0, 0);
/* 152 */       gridBagConstraints16.gridy = 1;
/* 153 */       GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
/* 154 */       gridBagConstraints15.fill = 2;
/* 155 */       gridBagConstraints15.gridy = 5;
/* 156 */       gridBagConstraints15.weightx = 1.0D;
/* 157 */       gridBagConstraints15.insets = new Insets(3, 10, 2, 80);
/* 158 */       gridBagConstraints15.gridx = 3;
/* 159 */       GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
/* 160 */       gridBagConstraints14.fill = 2;
/* 161 */       gridBagConstraints14.gridy = 6;
/* 162 */       gridBagConstraints14.weightx = 1.0D;
/* 163 */       gridBagConstraints14.insets = new Insets(3, 10, 2, 70);
/* 164 */       gridBagConstraints14.gridx = 3;
/* 165 */       GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
/* 166 */       gridBagConstraints13.gridx = 2;
/* 167 */       gridBagConstraints13.insets = new Insets(3, 10, 2, 0);
/* 168 */       gridBagConstraints13.gridy = 5;
/* 169 */       this.jLabel7 = new JLabel();
/* 170 */       this.jLabel7.setText(Mensajes.getString("cp"));
/* 171 */       GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
/* 172 */       gridBagConstraints12.gridx = 2;
/* 173 */       gridBagConstraints12.insets = new Insets(3, 10, 2, 0);
/* 174 */       gridBagConstraints12.gridy = 6;
/* 175 */       this.jLabel6 = new JLabel();
/* 176 */       this.jLabel6.setText(Mensajes.getString("fax"));
/* 177 */       GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
/* 178 */       gridBagConstraints11.fill = 2;
/* 179 */       gridBagConstraints11.gridy = 6;
/* 180 */       gridBagConstraints11.weightx = 1.0D;
/* 181 */       gridBagConstraints11.insets = new Insets(3, 10, 2, 0);
/* 182 */       gridBagConstraints11.gridx = 1;
/* 183 */       GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
/* 184 */       gridBagConstraints10.fill = 2;
/* 185 */       gridBagConstraints10.gridy = 5;
/* 186 */       gridBagConstraints10.weightx = 1.0D;
/* 187 */       gridBagConstraints10.insets = new Insets(3, 10, 2, 0);
/* 188 */       gridBagConstraints10.gridx = 1;
/* 189 */       GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
/* 190 */       gridBagConstraints9.fill = 2;
/* 191 */       gridBagConstraints9.gridy = 4;
/* 192 */       gridBagConstraints9.weightx = 1.0D;
/* 193 */       gridBagConstraints9.insets = new Insets(3, 10, 2, 0);
/* 194 */       gridBagConstraints9.gridwidth = 2;
/* 195 */       gridBagConstraints9.gridx = 1;
/* 196 */       GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
/* 197 */       gridBagConstraints8.fill = 2;
/* 198 */       gridBagConstraints8.gridy = 3;
/* 199 */       gridBagConstraints8.weightx = 1.0D;
/* 200 */       gridBagConstraints8.insets = new Insets(3, 10, 2, 30);
/* 201 */       gridBagConstraints8.gridwidth = 3;
/* 202 */       gridBagConstraints8.gridx = 1;
/* 203 */       GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
/* 204 */       gridBagConstraints7.fill = 2;
/* 205 */       gridBagConstraints7.gridy = 2;
/* 206 */       gridBagConstraints7.weightx = 1.0D;
/* 207 */       gridBagConstraints7.insets = new Insets(3, 10, 2, 0);
/* 208 */       gridBagConstraints7.gridx = 1;
/* 209 */       GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
/* 210 */       gridBagConstraints6.gridx = 0;
/* 211 */       gridBagConstraints6.anchor = 17;
/* 212 */       gridBagConstraints6.insets = new Insets(3, 10, 2, 0);
/* 213 */       gridBagConstraints6.gridy = 6;
/* 214 */       this.jLabel5 = new JLabel();
/* 215 */       this.jLabel5.setText(Mensajes.getString("telefono"));
/* 216 */       GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
/* 217 */       gridBagConstraints5.gridx = 0;
/* 218 */       gridBagConstraints5.anchor = 17;
/* 219 */       gridBagConstraints5.insets = new Insets(3, 10, 2, 0);
/* 220 */       gridBagConstraints5.gridy = 5;
/* 221 */       this.jLabel4 = new JLabel();
/* 222 */       this.jLabel4.setText(Mensajes.getString("provincia"));
/* 223 */       GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
/* 224 */       gridBagConstraints4.gridx = 0;
/* 225 */       gridBagConstraints4.anchor = 17;
/* 226 */       gridBagConstraints4.insets = new Insets(3, 10, 2, 0);
/* 227 */       gridBagConstraints4.gridy = 4;
/* 228 */       this.jLabel3 = new JLabel();
/* 229 */       this.jLabel3.setText(Mensajes.getString("localidad"));
/* 230 */       GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
/* 231 */       gridBagConstraints3.gridx = 0;
/* 232 */       gridBagConstraints3.anchor = 17;
/* 233 */       gridBagConstraints3.insets = new Insets(3, 10, 2, 0);
/* 234 */       gridBagConstraints3.gridy = 3;
/* 235 */       this.jLabel2 = new JLabel();
/* 236 */       this.jLabel2.setText(Mensajes.getString("direccion"));
/* 237 */       GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
/* 238 */       gridBagConstraints2.gridx = 0;
/* 239 */       gridBagConstraints2.anchor = 17;
/* 240 */       gridBagConstraints2.insets = new Insets(3, 10, 2, 0);
/* 241 */       gridBagConstraints2.gridy = 2;
/* 242 */       this.jLabel1 = new JLabel();
/* 243 */       this.jLabel1.setText(Mensajes.getString("NIF"));
/* 244 */       GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
/* 245 */       gridBagConstraints1.fill = 2;
/* 246 */       gridBagConstraints1.gridy = 1;
/* 247 */       gridBagConstraints1.weightx = 1.0D;
/* 248 */       gridBagConstraints1.anchor = 17;
/* 249 */       gridBagConstraints1.insets = new Insets(3, 10, 2, 150);
/* 250 */       gridBagConstraints1.gridwidth = 4;
/* 251 */       gridBagConstraints1.gridx = 0;
/* 252 */       GridBagConstraints gridBagConstraints = new GridBagConstraints();
/* 253 */       gridBagConstraints.gridx = 0;
/* 254 */       gridBagConstraints.anchor = 17;
/* 255 */       gridBagConstraints.insets = new Insets(10, 10, 0, 0);
/* 256 */       gridBagConstraints.gridy = 0;
/* 257 */       this.jLabel = new JLabel();
/* 258 */       this.jLabel.setText(Mensajes.getString("nombre"));
/* 259 */       this.jContentPane = new JPanel();
/* 260 */       this.jContentPane.setLayout(new GridBagLayout());
/* 261 */       this.jContentPane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createEtchedBorder(1)));
/*     */ 
/* 263 */       this.jContentPane.add(this.jLabel, gridBagConstraints);
/* 264 */       this.jContentPane.add(getListaEmpresas(), gridBagConstraints1);
/* 265 */       this.jContentPane.add(getCNombre(), gridBagConstraints18);
/* 266 */       this.jContentPane.add(this.jLabel1, gridBagConstraints2);
/* 267 */       this.jContentPane.add(this.jLabel2, gridBagConstraints3);
/* 268 */       this.jContentPane.add(this.jLabel3, gridBagConstraints4);
/* 269 */       this.jContentPane.add(this.jLabel4, gridBagConstraints5);
/* 270 */       this.jContentPane.add(this.jLabel5, gridBagConstraints6);
/* 271 */       this.jContentPane.add(getCNIF(), gridBagConstraints7);
/* 272 */       this.jContentPane.add(getCDireccion(), gridBagConstraints8);
/* 273 */       this.jContentPane.add(getCLocalidad(), gridBagConstraints9);
/* 274 */       this.jContentPane.add(getCProvincia(), gridBagConstraints10);
/* 275 */       this.jContentPane.add(getCTelefono(), gridBagConstraints11);
/* 276 */       this.jContentPane.add(this.jLabel6, gridBagConstraints12);
/* 277 */       this.jContentPane.add(this.jLabel7, gridBagConstraints13);
/* 278 */       this.jContentPane.add(getCFax(), gridBagConstraints14);
/* 279 */       this.jContentPane.add(getCCP(), gridBagConstraints15);
/* 280 */       this.jContentPane.add(getBNueva(), gridBagConstraints16);
/* 281 */       this.jContentPane.add(getJPanel(), gridBagConstraints17);
/* 282 */       this.jContentPane.add(this.jLabel8, gridBagConstraints110);
/* 283 */       this.jContentPane.add(getCPrejifo(), gridBagConstraints21);
/*     */     }
/* 285 */     return this.jContentPane;
/*     */   }
/*     */ 
/*     */   private JComboBox getListaEmpresas()
/*     */   {
/* 294 */     if (this.listaEmpresas == null) {
/* 295 */       this.listaEmpresas = new JComboBox();
/* 296 */       LinkedList lista = new LinkedList();
/* 297 */       lista.addAll(this.empresaM.listaEmpresas());
/* 298 */       if (lista.size() > 0) {
/* 299 */         for (String x : (List<String>) lista) {
/* 300 */           this.listaEmpresas.addItem(x);
/*     */         }
/*     */       }
/* 303 */       this.listaEmpresas.addActionListener(this.escuchaAccion);
/*     */     }
/* 305 */     return this.listaEmpresas;
/*     */   }
/*     */ 
/*     */   private JTextField getCNIF()
/*     */   {
/* 314 */     if (this.cNIF == null) {
/* 315 */       this.cNIF = new JTextField(7);
/*     */     }
/* 317 */     return this.cNIF;
/*     */   }
/*     */ 
/*     */   private JTextField getCDireccion()
/*     */   {
/* 326 */     if (this.cDireccion == null) {
/* 327 */       this.cDireccion = new JTextField();
/*     */     }
/* 329 */     return this.cDireccion;
/*     */   }
/*     */ 
/*     */   private JTextField getCLocalidad()
/*     */   {
/* 338 */     if (this.cLocalidad == null) {
/* 339 */       this.cLocalidad = new JTextField();
/*     */     }
/* 341 */     return this.cLocalidad;
/*     */   }
/*     */ 
/*     */   private JTextField getCProvincia()
/*     */   {
/* 350 */     if (this.cProvincia == null) {
/* 351 */       this.cProvincia = new JTextField();
/*     */     }
/* 353 */     return this.cProvincia;
/*     */   }
/*     */ 
/*     */   private JTextField getCTelefono()
/*     */   {
/* 362 */     if (this.cTelefono == null) {
/* 363 */       this.cTelefono = new JTextField();
/*     */     }
/* 365 */     return this.cTelefono;
/*     */   }
/*     */ 
/*     */   private JTextField getCFax()
/*     */   {
/* 374 */     if (this.cFax == null) {
/* 375 */       this.cFax = new JTextField(7);
/*     */     }
/* 377 */     return this.cFax;
/*     */   }
/*     */ 
/*     */   private JTextField getCCP()
/*     */   {
/* 386 */     if (this.cCP == null) {
/* 387 */       this.cCP = new JTextField();
/*     */     }
/* 389 */     return this.cCP;
/*     */   }
/*     */ 
/*     */   private JButton getBNueva()
/*     */   {
/* 398 */     if (this.bNueva == null) {
/* 399 */       this.bNueva = new JButton();
/* 400 */       this.bNueva.setText(Mensajes.getString("nueva"));
/* 401 */       this.bNueva.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/new.png")));
/* 402 */       this.bNueva.setHorizontalTextPosition(2);
/* 403 */       this.bNueva.addActionListener(this.escuchaAccion);
/*     */     }
/* 405 */     return this.bNueva;
/*     */   }
/*     */ 
/*     */   private JPanel getJPanel()
/*     */   {
/* 414 */     if (this.jPanel == null) {
/* 415 */       GridBagConstraints gridBagConstraints20 = new GridBagConstraints();
/* 416 */       gridBagConstraints20.gridx = 0;
/* 417 */       gridBagConstraints20.insets = new Insets(5, 10, 0, 10);
/* 418 */       gridBagConstraints20.gridy = 0;
/* 419 */       GridBagConstraints gridBagConstraints19 = new GridBagConstraints();
/* 420 */       gridBagConstraints19.gridx = 2;
/* 421 */       gridBagConstraints19.insets = new Insets(5, 10, 0, 10);
/* 422 */       gridBagConstraints19.gridy = 0;
/* 423 */       GridBagConstraints gridBagConstraints18 = new GridBagConstraints();
/* 424 */       gridBagConstraints18.gridx = 1;
/* 425 */       gridBagConstraints18.ipadx = 0;
/* 426 */       gridBagConstraints18.insets = new Insets(5, 10, 0, 10);
/* 427 */       gridBagConstraints18.gridy = 0;
/* 428 */       this.jPanel = new JPanel();
/* 429 */       this.jPanel.setLayout(new GridBagLayout());
/* 430 */       this.jPanel.add(getBBorrar(), gridBagConstraints18);
/* 431 */       this.jPanel.add(getBSalir(), gridBagConstraints19);
/* 432 */       this.jPanel.add(getBModificar(), gridBagConstraints20);
/*     */     }
/* 434 */     return this.jPanel;
/*     */   }
/*     */ 
/*     */   private JButton getBBorrar()
/*     */   {
/* 443 */     if (this.bBorrar == null) {
/* 444 */       this.bBorrar = new JButton();
/* 445 */       this.bBorrar.setText(Mensajes.getString("borrar"));
/* 446 */       this.bBorrar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/delete.png")));
/* 447 */       this.bBorrar.setHorizontalTextPosition(2);
/* 448 */       this.bBorrar.addActionListener(this.escuchaAccion);
/*     */     }
/* 450 */     return this.bBorrar;
/*     */   }
/*     */ 
/*     */   private JButton getBSalir()
/*     */   {
/* 459 */     if (this.bSalir == null) {
/* 460 */       this.bSalir = new JButton();
/* 461 */       this.bSalir.setText(Mensajes.getString("salir"));
/* 462 */       this.bSalir.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/cancel.png")));
/* 463 */       this.bSalir.setHorizontalTextPosition(2);
/* 464 */       this.bSalir.addActionListener(this.escuchaAccion);
/*     */     }
/* 466 */     return this.bSalir;
/*     */   }
/*     */ 
/*     */   private JButton getBModificar()
/*     */   {
/* 475 */     if (this.bModificar == null) {
/* 476 */       this.bModificar = new JButton();
/* 477 */       this.bModificar.setText(Mensajes.getString("modificar"));
/* 478 */       this.bModificar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/edit.png")));
/* 479 */       this.bModificar.setHorizontalTextPosition(2);
/* 480 */       this.bModificar.addActionListener(this.escuchaAccion);
/*     */     }
/* 482 */     return this.bModificar;
/*     */   }
/*     */ 
/*     */   public JTextField getCNombre()
/*     */   {
/* 491 */     if (this.cNombre == null) {
/* 492 */       this.cNombre = new JTextField();
/* 493 */       this.cNombre.setEnabled(false);
/* 494 */       this.cNombre.setVisible(false);
/*     */     }
/* 496 */     return this.cNombre;
/*     */   }
/*     */ 
/*     */   private void limpiarDialogo()
/*     */   {
/* 504 */     this.cNombre.setText("");
/* 505 */     this.cNIF.setText("");
/* 506 */     this.cDireccion.setText("");
/* 507 */     this.cLocalidad.setText("");
/* 508 */     this.cProvincia.setText("");
/* 509 */     this.cCP.setText("");
/* 510 */     this.cTelefono.setText("");
/* 511 */     this.cFax.setText("");
/* 512 */     this.cPrejifo.setText("");
/*     */   }
/*     */ 
/*     */   private void cambiaEmpresa(String empresa)
/*     */   {
/* 522 */     String[] datos = this.empresaM.datosEmpresa(empresa);
/* 523 */     if (datos != null) {
/* 524 */       this.cNIF.setText(datos[0]);
/* 525 */       this.cDireccion.setText(datos[1]);
/* 526 */       this.cLocalidad.setText(datos[2]);
/* 527 */       this.cProvincia.setText(datos[3]);
/* 528 */       this.cCP.setText(datos[4]);
/* 529 */       this.cTelefono.setText(datos[5]);
/* 530 */       this.cFax.setText(datos[6]);
/* 531 */       this.cPrejifo.setText(datos[7]);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void creaEmpresa()
/*     */   {
/* 542 */     String[] datos = { this.cNombre.getText(), this.cNIF.getText(), this.cDireccion.getText(), this.cLocalidad.getText(), this.cProvincia.getText(), this.cCP.getText(), this.cTelefono.getText(), this.cFax.getText(), this.cPrejifo.getText() };
/*     */ 
/* 553 */     if (this.esNueva) {
/* 554 */       int id = this.empresaM.getIdEmpresa(datos[0]);
/* 555 */       if (id == -1) {
/* 556 */         this.empresaM.crearEmpresa(datos);
/* 557 */         this.esNueva = false;
/* 558 */         this.listaEmpresas.addItem(this.cNombre.getText());
/* 559 */         limpiarDialogo();
/* 560 */         this.listaEmpresas.setEnabled(true);
/* 561 */         this.listaEmpresas.setVisible(true);
/* 562 */         this.cNombre.setVisible(false);
/* 563 */         this.cNombre.setEnabled(false);
/* 564 */         this.bModificar.setText(Mensajes.getString("modificar"));
/* 565 */         this.bModificar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/edit.png")));
/* 566 */         this.listaEmpresas.setSelectedIndex(0);
/* 567 */         this.listaEmpresas.requestFocus();
/*     */       }
/*     */       else {
/* 570 */         JOptionPane.showMessageDialog(getContentPane(), "Ya existe una empresa con ese nombre");
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 575 */       datos[0] = this.listaEmpresas.getSelectedItem().toString();
/* 576 */       this.empresaM.modificarEmpresa(datos);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void borraEmpresa(String empresa)
/*     */   {
/* 587 */     if (this.empresaM.borrarEmpresa(empresa)) {
/* 588 */       limpiarDialogo();
/* 589 */       this.listaEmpresas.removeItem(empresa);
/* 590 */       this.listaEmpresas.setSelectedIndex(0);
/* 591 */       this.listaEmpresas.requestFocus();
/*     */     } else {
/* 593 */       JOptionPane.showMessageDialog(getContentPane(), Mensajes.getString("empEnUso"));
/*     */     }
/*     */   }
/*     */ 
/*     */   private JTextField getCPrejifo()
/*     */   {
/* 604 */     if (this.cPrejifo == null) {
/* 605 */       this.cPrejifo = new JTextField();
/* 606 */       this.cPrejifo.setDocument(new DocDiezCarac());
/* 607 */       this.cPrejifo.setToolTipText("<html>" + Mensajes.getString("prefijott1") + "<br>" + Mensajes.getString("prefijott2") + " <b>" + Mensajes.getString("menu33") + "</b>");
/*     */     }
/*     */ 
/* 610 */     return this.cPrejifo;
/*     */   }
/*     */   private class Acciones implements ActionListener {
/*     */     private Acciones() {
/*     */     }
/*     */     public void actionPerformed(ActionEvent arg0) {
/* 616 */       Object fuente = arg0.getSource();
/* 617 */       if (fuente == GestionaEmpresas.this.bSalir) {
/* 618 */         GestionaEmpresas.this.dispose();
/* 619 */       } else if (fuente == GestionaEmpresas.this.bModificar) {
/* 620 */         GestionaEmpresas.this.creaEmpresa();
/* 621 */       } else if (fuente == GestionaEmpresas.this.bBorrar) {
/* 622 */         GestionaEmpresas.this.borraEmpresa(GestionaEmpresas.this.listaEmpresas.getSelectedItem().toString());
/* 623 */       } else if (fuente == GestionaEmpresas.this.bNueva) {
/* 624 */         GestionaEmpresas.this.bModificar.setText(Mensajes.getString("crear"));
/* 625 */         GestionaEmpresas.this.bModificar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/new.png")));
/* 626 */         GestionaEmpresas.this.esNueva = true;
/* 627 */         GestionaEmpresas.this.limpiarDialogo();
/* 628 */         GestionaEmpresas.this.listaEmpresas.setEnabled(false);
/* 629 */         GestionaEmpresas.this.listaEmpresas.setVisible(false);
/* 630 */         GestionaEmpresas.this.cNombre.setEnabled(true);
/* 631 */         GestionaEmpresas.this.cNombre.setVisible(true);
/* 632 */         GestionaEmpresas.this.cNombre.requestFocus();
/* 633 */       } else if (fuente == GestionaEmpresas.this.listaEmpresas) {
/* 634 */         GestionaEmpresas.this.cambiaEmpresa(GestionaEmpresas.this.listaEmpresas.getSelectedItem().toString());
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.empresas.GestionaEmpresas
 * JD-Core Version:    0.6.2
 */