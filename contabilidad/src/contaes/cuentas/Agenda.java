/*     */ package contaes.cuentas;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.Puente;
/*     */ import contaes.listados.Listado;
/*     */ import contaes.listados.ListadoTerceros;
/*     */ import contaes.manejoDatos.ManejoAgenda;
/*     */ import contaes.manejoDatos.ManejoFormasPago;
/*     */ import contaes.manejoDatos.ManejoPaises;
/*     */ import contaes.manejoDatos.ManejoSubcuentas;
/*     */ import contaes.manejoDatos.TipoFormaPago;
/*     */ import contaes.manejoDatos.TipoPais;
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.ArrayList;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.DefaultComboBoxModel;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.border.CompoundBorder;
/*     */ 
/*     */ public class Agenda extends JPanel
/*     */ {
/*  66 */   private ManejoAgenda agendaM = new ManejoAgenda(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  67 */   private String cuenta = "";
/*  68 */   BorderLayout borderlayout = new BorderLayout();
/*  69 */   GridBagLayout layout = new GridBagLayout();
/*  70 */   JPanel panel = new JPanel();
/*  71 */   JLabel label1 = new JLabel();
/*  72 */   JLabel label2 = new JLabel();
/*  73 */   JLabel label3 = new JLabel();
/*  74 */   JLabel label4 = new JLabel();
/*  75 */   JLabel label5 = new JLabel();
/*  76 */   JLabel label6 = new JLabel();
/*  77 */   JLabel label7 = new JLabel();
/*  78 */   JLabel label8 = new JLabel();
/*  79 */   JLabel label9 = new JLabel();
/*  80 */   JLabel label10 = new JLabel();
/*  81 */   private int inset = 124;
/*  82 */   private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/*  83 */   private int ancho_campo = (this.screenSize.width - this.inset * 2) / 6;
/*  84 */   JTextField campotxt1 = new JTextField(this.ancho_campo);
/*  85 */   JTextField campotxt2 = new JTextField(this.ancho_campo);
/*  86 */   JTextField campotxt3 = new JTextField(this.ancho_campo);
/*  87 */   JTextField campotxt4 = new JTextField(this.ancho_campo);
/*  88 */   JTextField campotxt5 = new JTextField(this.ancho_campo);
/*  89 */   JTextField campotxt6 = new JTextField(this.ancho_campo);
/*  90 */   JTextField campotxt7 = new JTextField(this.ancho_campo);
/*  91 */   JTextField campotxt8 = new JTextField(this.ancho_campo);
/*  92 */   JTextField campotxt9 = new JTextField(this.ancho_campo);
/*  93 */   JTextField campotxt10 = new JTextField(this.ancho_campo);
/*  94 */   JButton boton = new JButton();
/*  95 */   JButton bListado = new JButton();
/*     */ 
/*  97 */   JPanel panel1 = new JPanel();
/*  98 */   JLabel jLabel1 = new JLabel();
/*  99 */   JLabel jLabel2 = new JLabel();
/* 100 */   JLabel jLabel3 = new JLabel();
/* 101 */   JLabel jLabel4 = new JLabel();
/* 102 */   JLabel jLabel5 = new JLabel();
/* 103 */   JLabel jLabel6 = new JLabel();
/* 104 */   JLabel jLabel7 = new JLabel();
/* 105 */   JLabel jLabel8 = new JLabel();
/* 106 */   JTextField cuentaBanco = new JTextField();
/* 107 */   JComboBox cajaFormasPago = new JComboBox();
/* 108 */   JComboBox cajaCuentas = new JComboBox();
/* 109 */   JComboBox cajaPaises = new JComboBox();
/*     */ 
/*     */   public Agenda()
/*     */   {
/* 116 */     initialize();
/*     */   }
/*     */ 
/*     */   private void initialize()
/*     */   {
/* 124 */     this.boton.addActionListener(new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/* 127 */         Agenda.this.modificarFicha();
/*     */       }
/*     */     });
/* 130 */     this.bListado.addActionListener(new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/* 133 */         Agenda.this.hacerListado();
/*     */       }
/*     */     });
/* 139 */     this.cajaCuentas.setModel(modeloListaCuentas());
/* 140 */     this.cajaFormasPago.setModel(modeloFormasPago());
/* 141 */     this.cajaPaises.setModel(modeloPaises());
/*     */ 
/* 143 */     this.jLabel6 = new JLabel();
/* 144 */     this.jLabel6.setText("Forma de pago");
/* 145 */     this.jLabel5 = new JLabel();
/* 146 */     this.jLabel5.setText(Mensajes.getString("ctaPago"));
/* 147 */     this.jLabel7 = new JLabel();
/* 148 */     this.jLabel7.setText("Cuenta bancaria");
/* 149 */     GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
/* 150 */     gridBagConstraints9.fill = 3;
/* 151 */     gridBagConstraints9.gridy = 1;
/*     */ 
/* 153 */     gridBagConstraints9.fill = 1;
/* 154 */     gridBagConstraints9.gridwidth = 2;
/* 155 */     gridBagConstraints9.insets = new Insets(0, 0, 5, 0);
/* 156 */     gridBagConstraints9.gridx = 2;
/* 157 */     GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
/* 158 */     gridBagConstraints8.gridx = 0;
/* 159 */     gridBagConstraints8.gridwidth = 2;
/* 160 */     gridBagConstraints8.insets = new Insets(0, 0, 5, 0);
/* 161 */     gridBagConstraints8.gridy = 1;
/* 162 */     GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
/* 163 */     gridBagConstraints7.fill = 3;
/* 164 */     gridBagConstraints7.gridy = 0;
/* 165 */     gridBagConstraints7.weightx = 1.0D;
/* 166 */     gridBagConstraints7.ipadx = 25;
/* 167 */     gridBagConstraints7.insets = new Insets(0, 0, 5, 0);
/* 168 */     gridBagConstraints7.gridx = 3;
/* 169 */     GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
/* 170 */     gridBagConstraints6.fill = 3;
/* 171 */     gridBagConstraints6.gridy = 0;
/* 172 */     gridBagConstraints6.weightx = 1.0D;
/* 173 */     gridBagConstraints6.ipadx = 25;
/* 174 */     gridBagConstraints6.insets = new Insets(0, 0, 5, 0);
/* 175 */     gridBagConstraints6.gridx = 2;
/* 176 */     GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
/* 177 */     gridBagConstraints5.fill = 3;
/* 178 */     gridBagConstraints5.gridy = 0;
/* 179 */     gridBagConstraints5.weightx = 1.0D;
/* 180 */     gridBagConstraints5.ipadx = 25;
/* 181 */     gridBagConstraints5.insets = new Insets(0, 0, 5, 0);
/* 182 */     gridBagConstraints5.gridx = 1;
/* 183 */     GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
/* 184 */     gridBagConstraints4.fill = 3;
/* 185 */     gridBagConstraints4.gridy = 0;
/* 186 */     gridBagConstraints4.weightx = 1.0D;
/* 187 */     gridBagConstraints4.ipadx = 25;
/* 188 */     gridBagConstraints4.insets = new Insets(0, 0, 5, 0);
/* 189 */     gridBagConstraints4.gridx = 0;
/* 190 */     this.panel1.setLayout(new GridBagLayout());
/* 191 */     this.panel1.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createTitledBorder(null, "Vencimientos", 0, 0, new Font("Dialog", 1, 12), new Color(51, 51, 51))));
/* 192 */     this.panel1.add(this.jLabel5, gridBagConstraints4);
/* 193 */     this.panel1.add(this.cajaCuentas, gridBagConstraints5);
/* 194 */     this.panel1.add(this.jLabel6, gridBagConstraints6);
/* 195 */     this.panel1.add(this.cajaFormasPago, gridBagConstraints7);
/* 196 */     this.panel1.add(this.jLabel7, gridBagConstraints8);
/* 197 */     this.panel1.add(this.cuentaBanco, gridBagConstraints9);
/*     */ 
/* 200 */     GridBagConstraints cons = new GridBagConstraints();
/* 201 */     setLayout(this.borderlayout);
/* 202 */     this.panel.setLayout(this.layout);
/* 203 */     cons.anchor = 13;
/* 204 */     cons.insets.top = 7;
/* 205 */     cons.insets.left = 30;
/* 206 */     cons.insets.right = 5;
/* 207 */     this.label1.setText(Mensajes.getString("codigo"));
/* 208 */     cons.gridx = 0;
/* 209 */     cons.gridy = 0;
/* 210 */     cons.gridwidth = 1;
/* 211 */     cons.gridheight = 1;
/* 212 */     this.layout.setConstraints(this.label1, cons);
/* 213 */     this.panel.add(this.label1);
/* 214 */     this.label2.setText(Mensajes.getString("nombre"));
/* 215 */     cons.gridx = 0;
/* 216 */     cons.gridy = 1;
/* 217 */     cons.gridwidth = 1;
/* 218 */     cons.gridheight = 1;
/* 219 */     this.layout.setConstraints(this.label2, cons);
/* 220 */     this.panel.add(this.label2);
/* 221 */     this.label3.setText(Mensajes.getString("NIF"));
/* 222 */     cons.gridx = 0;
/* 223 */     cons.gridy = 2;
/* 224 */     cons.gridwidth = 1;
/* 225 */     cons.gridheight = 1;
/* 226 */     this.layout.setConstraints(this.label3, cons);
/* 227 */     this.panel.add(this.label3);
/* 228 */     this.label4.setText(Mensajes.getString("direccion") + " ");
/* 229 */     cons.gridx = 0;
/* 230 */     cons.gridy = 3;
/* 231 */     cons.gridwidth = 1;
/* 232 */     cons.gridheight = 1;
/* 233 */     this.layout.setConstraints(this.label4, cons);
/* 234 */     this.panel.add(this.label4);
/* 235 */     this.label5.setText(Mensajes.getString("cp"));
/* 236 */     cons.gridx = 0;
/* 237 */     cons.gridy = 4;
/* 238 */     cons.gridwidth = 1;
/* 239 */     cons.gridheight = 1;
/* 240 */     this.layout.setConstraints(this.label5, cons);
/* 241 */     this.panel.add(this.label5);
/* 242 */     this.label6.setText(Mensajes.getString("localidad"));
/* 243 */     cons.gridx = 2;
/* 244 */     cons.gridy = 4;
/* 245 */     cons.gridwidth = 1;
/* 246 */     cons.gridheight = 1;
/* 247 */     this.layout.setConstraints(this.label6, cons);
/* 248 */     this.panel.add(this.label6);
/* 249 */     this.label7.setText(Mensajes.getString("provincia") + " ");
/* 250 */     cons.gridx = 0;
/* 251 */     cons.gridy = 5;
/* 252 */     cons.gridwidth = 1;
/* 253 */     cons.gridheight = 1;
/* 254 */     this.layout.setConstraints(this.label7, cons);
/* 255 */     this.panel.add(this.label7);
/* 256 */     this.label8.setText(Mensajes.getString("telefono"));
/* 257 */     cons.gridx = 0;
/* 258 */     cons.gridy = 6;
/* 259 */     cons.gridwidth = 1;
/* 260 */     cons.gridheight = 1;
/* 261 */     this.layout.setConstraints(this.label8, cons);
/* 262 */     this.panel.add(this.label8);
/* 263 */     this.label9.setText(Mensajes.getString("fax"));
/* 264 */     cons.gridx = 2;
/* 265 */     cons.gridy = 6;
/* 266 */     cons.gridwidth = 1;
/* 267 */     cons.gridheight = 1;
/* 268 */     this.layout.setConstraints(this.label9, cons);
/* 269 */     this.panel.add(this.label9);
/* 270 */     this.label10.setText(Mensajes.getString("email"));
/* 271 */     cons.gridx = 0;
/* 272 */     cons.gridy = 7;
/* 273 */     cons.gridwidth = 1;
/* 274 */     cons.gridheight = 1;
/* 275 */     this.layout.setConstraints(this.label10, cons);
/* 276 */     this.panel.add(this.label10);
/* 277 */     this.jLabel8.setText(Mensajes.getString("pais"));
/* 278 */     cons.gridx = 2;
/* 279 */     cons.gridy = 5;
/* 280 */     cons.gridwidth = 1;
/* 281 */     cons.gridheight = 1;
/* 282 */     this.layout.setConstraints(this.jLabel8, cons);
/* 283 */     this.panel.add(this.jLabel8);
/*     */ 
/* 286 */     cons.fill = 1;
/* 287 */     cons.weightx = 1.0D;
/* 288 */     cons.insets.left = 0;
/* 289 */     cons.insets.right = 0;
/*     */ 
/* 291 */     cons.gridx = 1;
/* 292 */     cons.gridy = 0;
/* 293 */     cons.gridwidth = 1;
/* 294 */     cons.gridheight = 1;
/*     */ 
/* 296 */     this.layout.setConstraints(this.campotxt1, cons);
/* 297 */     this.panel.add(this.campotxt1);
/*     */ 
/* 299 */     cons.gridx = 1;
/* 300 */     cons.gridy = 1;
/* 301 */     cons.gridwidth = 3;
/* 302 */     cons.gridheight = 1;
/* 303 */     this.layout.setConstraints(this.campotxt2, cons);
/* 304 */     this.panel.add(this.campotxt2);
/*     */ 
/* 306 */     cons.gridx = 1;
/* 307 */     cons.gridy = 2;
/* 308 */     cons.gridwidth = 1;
/* 309 */     cons.gridheight = 1;
/* 310 */     this.layout.setConstraints(this.campotxt3, cons);
/* 311 */     this.panel.add(this.campotxt3);
/*     */ 
/* 313 */     cons.gridx = 1;
/* 314 */     cons.gridy = 3;
/* 315 */     cons.gridwidth = 3;
/* 316 */     cons.gridheight = 1;
/* 317 */     this.layout.setConstraints(this.campotxt4, cons);
/* 318 */     this.panel.add(this.campotxt4);
/*     */ 
/* 320 */     cons.gridx = 1;
/* 321 */     cons.gridy = 4;
/* 322 */     cons.gridwidth = 1;
/* 323 */     cons.gridheight = 1;
/* 324 */     this.layout.setConstraints(this.campotxt5, cons);
/* 325 */     this.panel.add(this.campotxt5);
/*     */ 
/* 327 */     cons.gridx = 3;
/* 328 */     cons.gridy = 4;
/* 329 */     cons.gridwidth = 1;
/* 330 */     cons.gridheight = 1;
/* 331 */     this.layout.setConstraints(this.campotxt6, cons);
/* 332 */     this.panel.add(this.campotxt6);
/*     */ 
/* 334 */     cons.gridx = 1;
/* 335 */     cons.gridy = 5;
/* 336 */     cons.gridwidth = 1;
/* 337 */     cons.gridheight = 1;
/* 338 */     this.layout.setConstraints(this.campotxt7, cons);
/* 339 */     this.panel.add(this.campotxt7);
/*     */ 
/* 341 */     cons.gridx = 3;
/* 342 */     cons.gridy = 5;
/* 343 */     cons.gridwidth = 1;
/* 344 */     cons.gridheight = 1;
/* 345 */     this.layout.setConstraints(this.cajaPaises, cons);
/* 346 */     this.panel.add(this.cajaPaises);
/*     */ 
/* 348 */     cons.gridx = 1;
/* 349 */     cons.gridy = 6;
/* 350 */     cons.gridwidth = 1;
/* 351 */     cons.gridheight = 1;
/* 352 */     this.layout.setConstraints(this.campotxt8, cons);
/* 353 */     this.panel.add(this.campotxt8);
/*     */ 
/* 355 */     cons.gridx = 3;
/* 356 */     cons.gridy = 6;
/* 357 */     cons.gridwidth = 1;
/* 358 */     cons.gridheight = 1;
/* 359 */     this.layout.setConstraints(this.campotxt9, cons);
/* 360 */     this.panel.add(this.campotxt9);
/*     */ 
/* 362 */     cons.gridx = 1;
/* 363 */     cons.gridy = 7;
/* 364 */     cons.gridwidth = 2;
/* 365 */     cons.gridheight = 1;
/* 366 */     this.layout.setConstraints(this.campotxt10, cons);
/* 367 */     this.panel.add(this.campotxt10);
/*     */ 
/* 370 */     cons.gridx = 0;
/* 371 */     cons.gridy = 8;
/* 372 */     cons.gridwidth = 4;
/* 373 */     this.layout.setConstraints(this.panel1, cons);
/* 374 */     this.panel.add(this.panel1);
/*     */ 
/* 377 */     cons.fill = 0;
/* 378 */     cons.anchor = 10;
/* 379 */     cons.weightx = 0.0D;
/* 380 */     cons.insets.top = 10;
/* 381 */     this.boton.setText(Mensajes.getString("modificar"));
/* 382 */     this.boton.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/edit.png")));
/* 383 */     this.boton.setVerticalTextPosition(0);
/* 384 */     this.boton.setHorizontalTextPosition(2);
/* 385 */     cons.gridx = 0;
/* 386 */     cons.gridy = 9;
/* 387 */     cons.gridwidth = 2;
/* 388 */     cons.gridheight = 1;
/* 389 */     this.layout.setConstraints(this.boton, cons);
/* 390 */     this.panel.add(this.boton);
/* 391 */     this.bListado.setHorizontalTextPosition(2);
/* 392 */     this.bListado.setText(Mensajes.getString("listado"));
/* 393 */     this.bListado.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/mayor.png")));
/* 394 */     cons.gridx = 2;
/* 395 */     this.layout.setConstraints(this.bListado, cons);
/* 396 */     this.panel.add(this.bListado);
/*     */ 
/* 398 */     this.panel.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(10, 90, 10, 100), new CompoundBorder(BorderFactory.createLoweredBevelBorder(), BorderFactory.createEmptyBorder(10, 0, 10, 20))));
/*     */ 
/* 402 */     add(this.panel, "Center");
/* 403 */     this.campotxt1.setEditable(false);
/* 404 */     this.campotxt2.setEditable(false);
/*     */   }
/*     */ 
/*     */   private void modificarFicha()
/*     */   {
/* 413 */     boolean exito = false;
/* 414 */     int formaP = -1;
/* 415 */     int cuentaP = -1;
/* 416 */     if (this.cajaFormasPago.getSelectedItem() != null) {
/* 417 */       TipoFormaPago fP = (TipoFormaPago)this.cajaFormasPago.getSelectedItem();
/* 418 */       formaP = fP.getIdFormaPago().intValue();
/*     */     }
/* 420 */     if (this.cajaCuentas.getSelectedItem() != null) {
/* 421 */       cuentaP = ((Integer)this.cajaCuentas.getSelectedItem()).intValue();
/*     */     }
/* 423 */     TipoPais pais = (TipoPais)this.cajaPaises.getSelectedItem();
/* 424 */     if (this.agendaM.isExistenDatos())
/*     */     {
/* 426 */       exito = this.agendaM.actualizar(new String[] { this.cuenta, this.campotxt3.getText(), this.campotxt4.getText(), this.campotxt5.getText(), this.campotxt6.getText(), this.campotxt7.getText(), this.campotxt8.getText(), this.campotxt9.getText(), this.campotxt10.getText(), formaP != -1 ? Integer.toString(formaP) : "", cuentaP != -1 ? Integer.toString(cuentaP) : "", this.cuentaBanco.getText(), Integer.toString(pais.getId()) });
/*     */     }
/*     */     else
/*     */     {
/* 443 */       exito = this.agendaM.crear(new String[] { this.cuenta, this.campotxt3.getText(), this.campotxt4.getText(), this.campotxt5.getText(), this.campotxt6.getText(), this.campotxt7.getText(), this.campotxt8.getText(), this.campotxt9.getText(), this.campotxt10.getText(), formaP != -1 ? Integer.toString(formaP) : "", cuentaP != -1 ? Integer.toString(cuentaP) : "", this.cuentaBanco.getText(), Integer.toString(pais.getId()) });
/*     */     }
/*     */ 
/* 459 */     String mensaje = Mensajes.getString("errorActBD") + "\n" + Mensajes.getString("reviseDatos");
/* 460 */     JOptionPane.showMessageDialog(Inicio.frame, mensaje, "", 1);
/*     */   }
/*     */ 
/*     */   public void colocarDatos(String ccodigo)
/*     */   {
/* 470 */     if (this.cuenta.equals(ccodigo)) {
/* 471 */       return;
/*     */     }
/* 473 */     this.campotxt3.setText("");
/* 474 */     this.campotxt4.setText("");
/* 475 */     this.campotxt5.setText("");
/* 476 */     this.campotxt6.setText("");
/* 477 */     this.campotxt7.setText("");
/* 478 */     this.campotxt8.setText("");
/* 479 */     this.campotxt9.setText("");
/* 480 */     this.campotxt10.setText("");
/* 481 */     this.cuentaBanco.setText("");
/* 482 */     this.cajaCuentas.setSelectedIndex(0);
/* 483 */     this.cajaFormasPago.setSelectedIndex(0);
/* 484 */     this.cuenta = ccodigo;
/* 485 */     this.campotxt1.setText(ccodigo);
/* 486 */     String[] datos = this.agendaM.datos(ccodigo);
/* 487 */     this.campotxt2.setText(datos[1]);
/* 488 */     if (this.agendaM.isExistenDatos()) {
/* 489 */       this.campotxt3.setText(datos[2]);
/* 490 */       this.campotxt4.setText(datos[3]);
/* 491 */       this.campotxt5.setText(datos[4]);
/* 492 */       this.campotxt6.setText(datos[5]);
/* 493 */       this.campotxt7.setText(datos[6]);
/* 494 */       this.campotxt8.setText(datos[7]);
/* 495 */       this.campotxt9.setText(datos[8]);
/* 496 */       this.campotxt10.setText(datos[9]);
/* 497 */       if (datos[10] != null) {
/* 498 */         TipoFormaPago fPago = new ManejoFormasPago(Inicio.getCGeneral()).getFormaPago(Integer.parseInt(datos[10]));
/* 499 */         if (fPago != null) {
/* 500 */           this.cajaFormasPago.setSelectedItem(fPago);
/*     */         }
/*     */       }
/* 503 */       if (datos[11] != null) {
/* 504 */         Integer ctaP = Integer.valueOf(Integer.parseInt(datos[11]));
/* 505 */         this.cajaCuentas.setSelectedItem(ctaP);
/*     */       }
/* 507 */       if (!datos[12].equals("NO")) {
/* 508 */         this.cuentaBanco.setText(datos[12]);
/*     */       }
/* 510 */       int idPais = Integer.parseInt(datos[13]);
/* 511 */       selectPais(idPais);
/*     */     }
/*     */   }
/*     */ 
/*     */   private String cadenaCampo(JTextField campo)
/*     */   {
/* 525 */     String cadena = campo.getText();
/* 526 */     if (cadena.length() == 1) {
/* 527 */       cadena = "0" + cadena;
/*     */     }
/* 529 */     if (cadena.length() > 2) {
/* 530 */       cadena = cadena.substring(0, 2);
/*     */     }
/* 532 */     return cadena;
/*     */   }
/*     */ 
/*     */   private void hacerListado() {
/* 536 */     ListadoTerceros clase = new ListadoTerceros();
/* 537 */     if (clase.isListar()) {
/* 538 */       List listado = clase.getListado();
/* 539 */       Listado marcoListado = new Listado(clase.getTitulo(), listado);
/* 540 */       marcoListado.validate();
/* 541 */       Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/* 542 */       Dimension frmSize = marcoListado.getSize();
/* 543 */       if (frmSize.height > screenSize.height) {
/* 544 */         frmSize.height = screenSize.height;
/*     */       }
/* 546 */       if (frmSize.width > screenSize.width) {
/* 547 */         frmSize.width = screenSize.width;
/*     */       }
/* 549 */       marcoListado.setLocation((screenSize.width - frmSize.width) / 2, (screenSize.height - frmSize.height) / 2);
/*     */ 
/* 551 */       marcoListado.setVisible(true);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void renovarModeloCuentas()
/*     */   {
/* 564 */     this.cajaCuentas.setModel(modeloListaCuentas());
/*     */   }
/*     */ 
/*     */   private DefaultComboBoxModel modeloListaCuentas() {
/* 568 */     DefaultComboBoxModel modelo = new DefaultComboBoxModel();
/* 569 */     ManejoSubcuentas subcuentaM = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 570 */     LinkedList listaCuenta = subcuentaM.listadoSubcuentas(57000000, 57999999);
/* 571 */     modelo.addElement(null);
/* 572 */     for (Integer x :(List<Integer>) listaCuenta) {
/* 573 */       modelo.addElement(x);
/*     */     }
/* 575 */     subcuentaM.cerrarRs();
/* 576 */     return modelo;
/*     */   }
/*     */ 
/*     */   public void renovarModeloFormasPago() {
/* 580 */     this.cajaFormasPago.setModel(modeloFormasPago());
/*     */   }
/*     */ 
/*     */   private DefaultComboBoxModel modeloFormasPago() {
/* 584 */     DefaultComboBoxModel modelo = new DefaultComboBoxModel();
/* 585 */     ManejoFormasPago mFP = new ManejoFormasPago(Inicio.getCGeneral());
/* 586 */     ArrayList listaFormas = mFP.getFormasPago();
/* 587 */     modelo.addElement(null);
/* 588 */     for (TipoFormaPago fPago :(List<TipoFormaPago>) listaFormas) {
/* 589 */       modelo.addElement(fPago);
/*     */     }
/* 591 */     mFP.cerrarRs();
/* 592 */     return modelo;
/*     */   }
/*     */ 
/*     */   public void renovarModeloPaises() {
/* 596 */     this.cajaPaises.setModel(modeloPaises());
/*     */   }
/*     */ 
/*     */   private DefaultComboBoxModel modeloPaises() {
/* 600 */     DefaultComboBoxModel modelo = new DefaultComboBoxModel();
/* 601 */     ManejoPaises mP = new ManejoPaises(Inicio.getCGeneral());
/* 602 */     ArrayList listaPaises = mP.getTodosPaises();
/* 603 */     for (TipoPais tipoPais : (List<TipoPais>) listaPaises) {
/* 604 */       modelo.addElement(tipoPais);
/*     */     }
/* 606 */     mP.cerrarRs();
/* 607 */     return modelo;
/*     */   }
/*     */ 
/*     */   private void selectPais(int id) {
/* 611 */     DefaultComboBoxModel modelo = (DefaultComboBoxModel)this.cajaPaises.getModel();
/* 612 */     for (int i = 0; i < modelo.getSize(); i++) {
/* 613 */       TipoPais pais = (TipoPais)modelo.getElementAt(i);
/* 614 */       if (pais.getId() == id) {
/* 615 */         this.cajaPaises.setSelectedIndex(i);
/* 616 */         break;
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.cuentas.Agenda
 * JD-Core Version:    0.6.2
 */