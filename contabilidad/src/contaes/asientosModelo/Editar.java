/*     */ package contaes.asientosModelo;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.MarcoInicio;
/*     */ import contaes.Puente;
/*     */ import contaes.auxiliar.CampoCuenta;
/*     */ import contaes.auxiliar.CyclingSpinnerListModel;
/*     */ import contaes.auxiliar.DocCincuentaCar;
/*     */ import contaes.auxiliar.DocNumPositivos;
/*     */ import contaes.auxiliar.DocTreintaCarac;
/*     */ import contaes.auxiliarTablas.CentroColorRenderer;
/*     */ import contaes.auxiliarTablas.DefaultTableModelNotEditable;
/*     */ import contaes.auxiliarTablas.ImporteColorRenderer;
/*     */ import contaes.auxiliarTablas.ToolTipNombreCuentaColor;
/*     */ import contaes.dialogosAuxiliares.MostrarConceptos;
/*     */ import contaes.dialogosAuxiliares.MostrarCuentas;
/*     */ import contaes.dialogosFunciones.Calculadora;
/*     */ import contaes.manejoDatos.ManejoAsientosModelo;
/*     */ import contaes.manejoDatos.ManejoSubcuentas;
/*     */ import contaes.manejoDatos.TipoApunteModelo;
/*     */ import contaes.manejoDatos.TipoAsientoModelo;
/*     */ import contaes.manejoDatos.TipoSubcuenta;
/*     */ import contaes.manejoDatos.funciones.ComprobarDato;
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Frame;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.FocusAdapter;
/*     */ import java.awt.event.FocusEvent;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.KeyListener;
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.DecimalFormatSymbols;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.DefaultComboBoxModel;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JSpinner;
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.JViewport;
/*     */ import javax.swing.ListSelectionModel;
/*     */ import javax.swing.border.SoftBevelBorder;
/*     */ import javax.swing.event.ListSelectionEvent;
/*     */ import javax.swing.event.ListSelectionListener;
/*     */ import javax.swing.table.TableColumn;
/*     */ import javax.swing.table.TableColumnModel;
/*     */ import javax.swing.text.JTextComponent;
/*     */ 
/*     */ public class Editar extends JDialog
/*     */   implements KeyListener
/*     */ {
/*  79 */   private JPanel jContentPane = null;
/*  80 */   private JPanel panelArriba = null;
/*  81 */   private JLabel jLabel1 = null;
/*  82 */   private JLabel jLabel2 = null;
/*  83 */   private JTextField descripcion = null;
/*  84 */   private JTextField concepto = null;
/*  85 */   private JLabel jLabel3 = null;
/*  86 */   private JTextField marca = null;
/*  87 */   private JPanel panelAbajo = null;
/*  88 */   private JSpinner DH = null;
/*  89 */   private JTextField porcentaje = null;
/*  90 */   private JLabel jLabel5 = null;
/*  91 */   private JTextField descuadre = null;
/*  92 */   private JTextField nombreCuenta = null;
/*  93 */   private JButton Intro = null;
/*  94 */   private JButton bCrear = null;
/*  95 */   private JButton bCancelar = null;
/*  96 */   private JComboBox cuenta = null;
/*  97 */   private CampoCuenta subcuenta = null;
/*  98 */   private JPanel panelCentro = null;
/*  99 */   private JButton bModif = null;
/* 100 */   private JScrollPane scrollPane = null;
/* 101 */   private DefaultTableModelNotEditable modelo = null;
/* 102 */   private JTable tabla = null;
/* 103 */   private TableColumn columna = null;
/* 104 */   private String textoBoton = "";
/*     */   private ManejoAsientosModelo manejoAM;
/*     */   private ManejoSubcuentas mS;
/* 107 */   int id = -1;
/* 108 */   int selectedRow = 0;
/*     */   DecimalFormat fn;
/* 110 */   private double saldoTotal = 0.0D;
/*     */ 
/*     */   public Editar(Frame owner)
/*     */   {
/* 116 */     super(owner, false);
/* 117 */     this.textoBoton = Mensajes.getString("crear");
/* 118 */     this.id = -1;
/* 119 */     initialize();
/*     */   }
/*     */ 
/*     */   public Editar(Frame owner, int ID) {
/* 123 */     super(owner, false);
/* 124 */     this.textoBoton = Mensajes.getString("modificar");
/* 125 */     this.id = ID;
/* 126 */     initialize();
/*     */   }
/*     */ 
/*     */   private void initialize()
/*     */   {
/* 135 */     this.manejoAM = new ManejoAsientosModelo(Inicio.getCGeneral());
/* 136 */     this.mS = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 137 */     DecimalFormatSymbols formato = new DecimalFormatSymbols();
/* 138 */     formato.setDecimalSeparator(',');
/* 139 */     formato.setPerMill('.');
/* 140 */     this.fn = new DecimalFormat("#,###,##0.00", formato);
/*     */ 
/* 142 */     setSize(460, 400);
/* 143 */     setTitle(Mensajes.getString("edAstoModel"));
/* 144 */     setContentPane(getJContentPane());
/*     */   }
/*     */ 
/*     */   private JPanel getJContentPane()
/*     */   {
/* 153 */     if (this.jContentPane == null) {
/* 154 */       this.jContentPane = new JPanel();
/* 155 */       this.jContentPane.setLayout(new BorderLayout());
/* 156 */       this.jContentPane.add(getPanelArriba(), "North");
/* 157 */       this.jContentPane.add(getPanelAbajo(), "South");
/* 158 */       this.jContentPane.add(getPanelCentro(), "Center");
/*     */     }
/* 160 */     if (this.id != -1) {
/* 161 */       colocarDatos(this.id);
/*     */     }
/* 163 */     return this.jContentPane;
/*     */   }
/*     */ 
/*     */   private JPanel getPanelArriba()
/*     */   {
/* 172 */     if (this.panelArriba == null) {
/* 173 */       this.jLabel3 = new JLabel();
/* 174 */       this.jLabel3.setText(Mensajes.getString("marca"));
/* 175 */       this.jLabel3.setBounds(new Rectangle(348, 25, 50, 16));
/* 176 */       this.jLabel2 = new JLabel();
/* 177 */       this.jLabel2.setText(Mensajes.getString("concepto"));
/* 178 */       this.jLabel2.setBounds(new Rectangle(17, 48, 75, 16));
/* 179 */       this.jLabel1 = new JLabel();
/* 180 */       this.jLabel1.setText(Mensajes.getString("descripcion"));
/* 181 */       this.jLabel1.setBounds(new Rectangle(17, 18, 85, 16));
/* 182 */       this.panelArriba = new JPanel();
/* 183 */       this.panelArriba.setLayout(null);
/* 184 */       this.panelArriba.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), new SoftBevelBorder(1)));
/* 185 */       this.panelArriba.setPreferredSize(new Dimension(162, 80));
/* 186 */       this.panelArriba.add(this.jLabel1, null);
/* 187 */       this.panelArriba.add(this.jLabel2, null);
/* 188 */       this.panelArriba.add(getDescripcion(), null);
/* 189 */       this.panelArriba.add(getConcepto(), null);
/* 190 */       this.panelArriba.add(this.jLabel3, null);
/* 191 */       this.panelArriba.add(getMarca(), null);
/*     */     }
/* 193 */     return this.panelArriba;
/*     */   }
/*     */ 
/*     */   private JTextField getDescripcion()
/*     */   {
/* 202 */     if (this.descripcion == null) {
/* 203 */       this.descripcion = new JTextField();
/* 204 */       this.descripcion.setDocument(new DocCincuentaCar());
/* 205 */       this.descripcion.addKeyListener(this);
/* 206 */       this.descripcion.setBounds(new Rectangle(105, 16, 217, 20));
/*     */     }
/* 208 */     return this.descripcion;
/*     */   }
/*     */ 
/*     */   private JTextField getConcepto()
/*     */   {
/* 217 */     if (this.concepto == null) {
/* 218 */       this.concepto = new JTextField();
/* 219 */       this.concepto.setDocument(new DocTreintaCarac());
/* 220 */       this.concepto.addKeyListener(this);
/* 221 */       this.concepto.setBounds(new Rectangle(105, 46, 247, 20));
/*     */     }
/* 223 */     return this.concepto;
/*     */   }
/*     */ 
/*     */   private JTextField getMarca()
/*     */   {
/* 232 */     if (this.marca == null) {
/* 233 */       this.marca = new JTextField();
/* 234 */       this.marca.setToolTipText(Mensajes.getString("marcaTT"));
/* 235 */       this.marca.addKeyListener(this);
/* 236 */       this.marca.setBounds(new Rectangle(401, 23, 21, 20));
/*     */     }
/* 238 */     return this.marca;
/*     */   }
/*     */ 
/*     */   private JPanel getPanelAbajo()
/*     */   {
/* 247 */     if (this.panelAbajo == null) {
/* 248 */       GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
/* 249 */       gridBagConstraints6.fill = 2;
/* 250 */       gridBagConstraints6.gridy = 0;
/* 251 */       gridBagConstraints6.weightx = 1.0D;
/* 252 */       gridBagConstraints6.insets = new Insets(0, 5, 0, 0);
/* 253 */       gridBagConstraints6.gridx = 0;
/* 254 */       GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
/* 255 */       gridBagConstraints14.gridx = 2;
/* 256 */       gridBagConstraints14.insets = new Insets(0, 0, 5, 0);
/* 257 */       gridBagConstraints14.gridy = 3;
/* 258 */       GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
/* 259 */       gridBagConstraints13.gridx = 0;
/* 260 */       gridBagConstraints13.insets = new Insets(0, 0, 5, 0);
/* 261 */       gridBagConstraints13.gridy = 3;
/* 262 */       GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
/* 263 */       gridBagConstraints5.gridx = 3;
/* 264 */       gridBagConstraints5.insets = new Insets(0, 9, 0, 9);
/* 265 */       gridBagConstraints5.gridy = 0;
/* 266 */       GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
/* 267 */       gridBagConstraints12.fill = 2;
/* 268 */       gridBagConstraints12.gridy = 1;
/* 269 */       gridBagConstraints12.weightx = 1.0D;
/* 270 */       gridBagConstraints12.insets = new Insets(5, 5, 5, 50);
/* 271 */       gridBagConstraints12.gridwidth = 3;
/* 272 */       gridBagConstraints12.gridx = 0;
/* 273 */       GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
/* 274 */       gridBagConstraints10.fill = 2;
/* 275 */       gridBagConstraints10.gridy = 2;
/* 276 */       gridBagConstraints10.weightx = 1.0D;
/* 277 */       gridBagConstraints10.gridwidth = 1;
/* 278 */       gridBagConstraints10.insets = new Insets(5, 0, 5, 0);
/* 279 */       gridBagConstraints10.gridx = 1;
/* 280 */       GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
/* 281 */       gridBagConstraints9.gridx = 0;
/* 282 */       gridBagConstraints9.anchor = 13;
/* 283 */       gridBagConstraints9.insets = new Insets(5, 0, 5, 10);
/* 284 */       gridBagConstraints9.gridy = 2;
/* 285 */       this.jLabel5 = new JLabel();
/* 286 */       this.jLabel5.setText(Mensajes.getString("descuadre"));
/* 287 */       GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
/* 288 */       gridBagConstraints8.fill = 2;
/* 289 */       gridBagConstraints8.gridy = 0;
/* 290 */       gridBagConstraints8.weightx = 1.0D;
/* 291 */       gridBagConstraints8.insets = new Insets(5, 5, 5, 40);
/* 292 */       gridBagConstraints8.gridx = 2;
/* 293 */       GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
/* 294 */       gridBagConstraints7.fill = 2;
/* 295 */       gridBagConstraints7.gridy = 0;
/* 296 */       gridBagConstraints7.weightx = 1.0D;
/* 297 */       gridBagConstraints7.insets = new Insets(5, 10, 5, 10);
/* 298 */       gridBagConstraints7.gridx = 1;
/* 299 */       this.panelAbajo = new JPanel();
/* 300 */       this.panelAbajo.setLayout(new GridBagLayout());
/* 301 */       this.panelAbajo.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10), new SoftBevelBorder(1)));
/* 302 */       this.panelAbajo.add(getDH(), gridBagConstraints7);
/* 303 */       this.panelAbajo.add(getPorcentaje(), gridBagConstraints8);
/* 304 */       this.panelAbajo.add(this.jLabel5, gridBagConstraints9);
/* 305 */       this.panelAbajo.add(getDescuadre(), gridBagConstraints10);
/* 306 */       this.panelAbajo.add(getIntro(), gridBagConstraints5);
/* 307 */       this.panelAbajo.add(getBCrear(), gridBagConstraints13);
/* 308 */       this.panelAbajo.add(getBCancelar(), gridBagConstraints14);
/* 309 */       this.panelAbajo.add(getSubcuenta(), gridBagConstraints6);
/* 310 */       this.panelAbajo.add(getNombreCuenta(), gridBagConstraints12);
/*     */     }
/* 312 */     return this.panelAbajo;
/*     */   }
/*     */ 
/*     */   private JSpinner getDH()
/*     */   {
/* 321 */     if (this.DH == null) {
/* 322 */       String[] DoH = { Mensajes.getString("debeA"), Mensajes.getString("haberA") };
/* 323 */       CyclingSpinnerListModel ModeloDH = new CyclingSpinnerListModel(DoH);
/* 324 */       this.DH = new JSpinner(ModeloDH);
/* 325 */       this.DH.addKeyListener(this);
/*     */     }
/* 327 */     return this.DH;
/*     */   }
/*     */ 
/*     */   private JTextField getPorcentaje()
/*     */   {
/* 336 */     if (this.porcentaje == null) {
/* 337 */       this.porcentaje = new JTextField();
/* 338 */       this.porcentaje.setDocument(new DocNumPositivos());
/* 339 */       this.porcentaje.setToolTipText(Mensajes.getString("porcentt"));
/* 340 */       this.porcentaje.addKeyListener(this);
/*     */     }
/* 342 */     return this.porcentaje;
/*     */   }
/*     */ 
/*     */   private JTextField getDescuadre()
/*     */   {
/* 351 */     if (this.descuadre == null) {
/* 352 */       this.descuadre = new JTextField();
/*     */     }
/* 354 */     return this.descuadre;
/*     */   }
/*     */ 
/*     */   private JTextField getNombreCuenta()
/*     */   {
/* 363 */     if (this.nombreCuenta == null) {
/* 364 */       this.nombreCuenta = new JTextField();
/* 365 */       this.nombreCuenta.setEditable(false);
/* 366 */       this.nombreCuenta.setBackground(new Color(230, 230, 230));
/*     */     }
/*     */ 
/* 369 */     return this.nombreCuenta;
/*     */   }
/*     */ 
/*     */   private JButton getIntro()
/*     */   {
/* 378 */     if (this.Intro == null) {
/* 379 */       this.Intro = new JButton();
/* 380 */       this.Intro.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/ok.png")));
/* 381 */       this.Intro.setToolTipText(Mensajes.getString("introAptt"));
/*     */ 
/* 383 */       this.Intro.setPreferredSize(new Dimension(32, 26));
/* 384 */       this.Intro.addActionListener(new ActionListener()
/*     */       {
/*     */         public void actionPerformed(ActionEvent e) {
/* 387 */           Editar.this.introducirApunte();
/*     */         }
/*     */       });
/*     */     }
/* 391 */     return this.Intro;
/*     */   }
/*     */ 
/*     */   private JButton getBCrear()
/*     */   {
/* 400 */     if (this.bCrear == null) {
/* 401 */       this.bCrear = new JButton();
/* 402 */       this.bCrear.setText(this.textoBoton);
/* 403 */       this.bCrear.setHorizontalTextPosition(2);
/* 404 */       this.bCrear.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/new.png")));
/* 405 */       this.bCrear.addActionListener(new ActionListener()
/*     */       {
/*     */         public void actionPerformed(ActionEvent e) {
/* 408 */           if (Editar.this.saldoTotal != 0.0D) {
/* 409 */             JOptionPane.showMessageDialog(Editar.this.getContentPane(), Mensajes.getString("astDescuad"));
/*     */           } else {
/* 411 */             if (Editar.this.id == -1)
/* 412 */               Editar.this.crearAsiento();
/*     */             else {
/* 414 */               Editar.this.modificarAsiento(Editar.this.id);
/*     */             }
/* 416 */             Inicio.frame.renovarTabla(4);
/* 417 */             Editar.this.dispose();
/*     */           }
/*     */         }
/*     */       });
/*     */     }
/* 422 */     return this.bCrear;
/*     */   }
/*     */ 
/*     */   private JButton getBCancelar()
/*     */   {
/* 431 */     if (this.bCancelar == null) {
/* 432 */       this.bCancelar = new JButton();
/* 433 */       this.bCancelar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/cancel.png")));
/* 434 */       this.bCancelar.setText(Mensajes.getString("cancelar"));
/* 435 */       this.bCancelar.setHorizontalTextPosition(2);
/* 436 */       this.bCancelar.addActionListener(new ActionListener()
/*     */       {
/*     */         public void actionPerformed(ActionEvent e) {
/* 439 */           Editar.this.dispose();
/*     */         }
/*     */       });
/*     */     }
/* 443 */     return this.bCancelar;
/*     */   }
/*     */ 
/*     */   private JComboBox getCuenta()
/*     */   {
/* 452 */     if (this.cuenta == null) {
/* 453 */       this.cuenta = new JComboBox();
/* 454 */       this.cuenta.setPreferredSize(new Dimension(30, 22));
/* 455 */       ManejoSubcuentas cuentaM = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 456 */       LinkedList lista = cuentaM.listadoSubcuentas(10000000, 80000000);
/* 457 */       if (lista.size() > 0) {
/* 458 */         DefaultComboBoxModel modeloC = new DefaultComboBoxModel();
/* 459 */         for (Iterator i$ = lista.iterator(); i$.hasNext(); ) { int x = ((Integer)i$.next()).intValue();
/* 460 */           modeloC.addElement(String.valueOf(x));
/*     */         }
/* 462 */         this.cuenta.setModel(modeloC);
/*     */       }
/* 464 */       this.cuenta.addKeyListener(this);
/* 465 */       this.cuenta.addActionListener(new ActionListener()
/*     */       {
/*     */         public void actionPerformed(ActionEvent e) {
/* 468 */           Editar.this.nombreCuenta.setText(Editar.this.nombreDeCuenta((String)Editar.this.cuenta.getSelectedItem()));
/*     */         }
/*     */       });
/*     */     }
/* 472 */     return this.cuenta;
/*     */   }
/*     */ 
/*     */   private CampoCuenta getSubcuenta() {
/* 476 */     if (this.subcuenta == null) {
/* 477 */       this.subcuenta = new CampoCuenta(this.mS);
/* 478 */       this.subcuenta.setPreferredSize(new Dimension(30, 22));
/* 479 */       this.subcuenta.addKeyListener(this);
/* 480 */       this.subcuenta.addFocusListener(new FocusAdapter()
/*     */       {
/*     */         public void focusLost(FocusEvent e)
/*     */         {
/* 484 */           Editar.this.nombreCuenta.setText(Editar.this.subcuenta.getNombre());
/*     */         }
/*     */       });
/*     */     }
/* 488 */     return this.subcuenta;
/*     */   }
/*     */ 
/*     */   private JPanel getPanelCentro()
/*     */   {
/* 497 */     if (this.panelCentro == null) {
/* 498 */       this.panelCentro = new JPanel();
/* 499 */       this.panelCentro.setLayout(null);
/* 500 */       this.panelCentro.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
/* 501 */       this.panelCentro.setPreferredSize(new Dimension(79, 42));
/* 502 */       this.panelCentro.add(getBModif(), null);
/* 503 */       this.panelCentro.add(getScrollPane(), null);
/*     */     }
/* 505 */     return this.panelCentro;
/*     */   }
/*     */ 
/*     */   private JButton getBModif()
/*     */   {
/* 514 */     if (this.bModif == null) {
/* 515 */       this.bModif = new JButton();
/* 516 */       this.bModif.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/redo.png")));
/* 517 */       this.bModif.setToolTipText(Mensajes.getString("modifAptt"));
/* 518 */       this.bModif.setBounds(new Rectangle(395, 60, 32, 32));
/* 519 */       this.bModif.setPreferredSize(new Dimension(32, 32));
/* 520 */       this.bModif.addActionListener(new ActionListener()
/*     */       {
/*     */         public void actionPerformed(ActionEvent e) {
/* 523 */           Editar.this.modificarApunte();
/*     */         }
/*     */       });
/*     */     }
/* 527 */     return this.bModif;
/*     */   }
/*     */ 
/*     */   private JScrollPane getScrollPane()
/*     */   {
/* 536 */     if (this.scrollPane == null) {
/* 537 */       this.scrollPane = new JScrollPane();
/* 538 */       this.scrollPane.setBounds(new Rectangle(10, 5, 375, 131));
/* 539 */       this.scrollPane.getViewport().add(getTabla());
/*     */     }
/* 541 */     return this.scrollPane;
/*     */   }
/*     */ 
/*     */   private JTable getTabla()
/*     */   {
/* 550 */     if (this.tabla == null) {
/* 551 */       String[] columnas = { Mensajes.getString("cuenta"), Mensajes.getString("debeHaberA"), "% " + Mensajes.getString("importe") };
/* 552 */       this.modelo = new DefaultTableModelNotEditable(columnas, 0);
/* 553 */       this.tabla = new JTable(this.modelo);
/* 554 */       this.tabla.setSelectionMode(0);
/* 555 */       this.tabla.setPreferredScrollableViewportSize(new Dimension(442, 70));
/* 556 */       this.tabla.addMouseListener(new MouseAdapter()
/*     */       {
/*     */         public void mouseClicked(MouseEvent e)
/*     */         {
/* 560 */           if (e.getButton() == 3)
/* 561 */             Editar.this.modificarApunte();
/*     */         }
/*     */       });
/* 565 */       ListSelectionModel rowSM = this.tabla.getSelectionModel();
/* 566 */       rowSM.addListSelectionListener(new ListSelectionListener()
/*     */       {
/*     */         public void valueChanged(ListSelectionEvent e)
/*     */         {
/* 570 */           if (e.getValueIsAdjusting()) {
/* 571 */             return;
/*     */           }
/* 573 */           ListSelectionModel lsm = (ListSelectionModel)e.getSource();
/* 574 */           if (!lsm.isSelectionEmpty())
/*     */           {
/* 577 */             Editar.this.selectedRow = lsm.getMinSelectionIndex();
/*     */           }
/*     */         }
/*     */       });
/* 583 */       this.columna = this.tabla.getColumnModel().getColumn(0);
/* 584 */       this.columna.setPreferredWidth(85);
/* 585 */       this.columna.setCellRenderer(new ToolTipNombreCuentaColor());
/* 586 */       this.columna = this.tabla.getColumnModel().getColumn(1);
/* 587 */       this.columna.setPreferredWidth(27);
/* 588 */       this.columna.setCellRenderer(new CentroColorRenderer());
/* 589 */       this.columna = this.tabla.getColumnModel().getColumn(2);
/* 590 */       this.columna.setPreferredWidth(80);
/* 591 */       this.columna.setCellRenderer(new ImporteColorRenderer());
/*     */     }
/* 593 */     return this.tabla;
/*     */   }
/*     */ 
/*     */   protected void colocarDatos(int id)
/*     */   {
/* 603 */     TipoAsientoModelo asientoM = this.manejoAM.datosAsiento(id);
/* 604 */     if (asientoM == null) {
/* 605 */       return;
/*     */     }
/* 607 */     LinkedList listaAp = new LinkedList();
/* 608 */     listaAp.addAll(this.manejoAM.listadoApuntes(id));
/*     */ 
/* 610 */     if (listaAp.size() == 0) {
/* 611 */       return;
/*     */     }
/* 613 */     this.descripcion.setText(asientoM.getDescripcion());
/* 614 */     this.concepto.setText(asientoM.getConcepto());
/* 615 */     this.marca.setText(asientoM.getMarca());
/* 616 */     for (Iterator i$ = listaAp.iterator(); i$.hasNext(); ) { int x = ((Integer)i$.next()).intValue();
/*     */ 
/* 618 */       String debeHaber = this.manejoAM.datosApunte(x).getCA();
/* 619 */       double cuanto = this.manejoAM.datosApunte(x).getImporte();
/* 620 */       Object[] apunte = { new Integer(this.manejoAM.datosApunte(x).getCuenta()), debeHaber, new Double(cuanto) };
/*     */ 
/* 625 */       this.modelo.addRow(apunte);
/* 626 */       calcularSaldosAsiento(debeHaber, cuanto);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected String nombreDeCuenta(String numero)
/*     */   {
/* 637 */     ManejoSubcuentas cuentaM = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*     */     try {
/* 639 */       int numCuenta = Integer.parseInt(this.cuenta.getSelectedItem().toString());
/* 640 */       return cuentaM.datos(numCuenta).getNombre();
/*     */     } catch (NumberFormatException e) {
/* 642 */       e.printStackTrace();
/*     */     }
/* 644 */     return "";
/*     */   }
/*     */ 
/*     */   protected void introducirApunte()
/*     */   {
/* 652 */     ComprobarDato dato = new ComprobarDato(this.porcentaje.getText());
/* 653 */     String subc = this.subcuenta.getText();
/* 654 */     if (!subc.equals("")) {
/* 655 */       if (dato.esDoble())
/*     */       {
/* 659 */         Object[] apunte = { new Integer(Integer.parseInt(this.subcuenta.getText())), this.DH.getValue(), new Double(Double.parseDouble(this.porcentaje.getText())) };
/*     */ 
/* 661 */         this.modelo.addRow(apunte);
/* 662 */         this.tabla.changeSelection(this.tabla.getRowCount() - 1, 0, true, true);
/* 663 */         calcularSaldosAsiento(this.DH.getValue().toString(), Double.parseDouble(this.porcentaje.getText()));
/*     */ 
/* 665 */         this.porcentaje.setText("");
/* 666 */         this.subcuenta.setText("");
/* 667 */         this.subcuenta.requestFocus();
/*     */       } else {
/* 669 */         JOptionPane.showMessageDialog(getContentPane(), Mensajes.getString("importNoNum"));
/*     */       }
/*     */     }
/* 672 */     else JOptionPane.showMessageDialog(getContentPane(), "No ha introducido subcuenta");
/*     */   }
/*     */ 
/*     */   protected void modificarApunte()
/*     */   {
/* 683 */     this.subcuenta.setText(this.tabla.getValueAt(this.selectedRow, 0).toString());
/* 684 */     this.DH.setValue(this.tabla.getValueAt(this.selectedRow, 1).toString());
/* 685 */     this.porcentaje.setText(this.tabla.getValueAt(this.selectedRow, 2).toString());
/* 686 */     if (this.DH.getValue().equals(Mensajes.getString("debeA")))
/* 687 */       this.saldoTotal -= Double.parseDouble(this.porcentaje.getText());
/*     */     else {
/* 689 */       this.saldoTotal += Double.parseDouble(this.porcentaje.getText());
/*     */     }
/* 691 */     this.descuadre.setText(this.fn.format(this.saldoTotal));
/* 692 */     this.modelo.removeRow(this.selectedRow);
/*     */   }
/*     */ 
/*     */   private void calcularSaldosAsiento(String debeHaber, double cuanto)
/*     */   {
/* 702 */     if (debeHaber.equals(Mensajes.getString("debeA")))
/* 703 */       this.saldoTotal += cuanto;
/*     */     else {
/* 705 */       this.saldoTotal -= cuanto;
/*     */     }
/* 707 */     this.descuadre.setText(this.fn.format(this.saldoTotal));
/*     */   }
/*     */ 
/*     */   private void crearAsiento()
/*     */   {
/* 715 */     this.id = this.manejoAM.crearAsiento(this.descripcion.getText(), this.concepto.getText(), this.marca.getText());
/*     */ 
/* 718 */     if (this.id != -1)
/* 719 */       crearApuntes(this.id);
/*     */   }
/*     */ 
/*     */   private void modificarAsiento(int id)
/*     */   {
/* 730 */     this.manejoAM.modificarAsiento(id, this.descripcion.getText(), this.concepto.getText(), this.marca.getText());
/*     */ 
/* 732 */     crearApuntes(id);
/*     */   }
/*     */ 
/*     */   private void crearApuntes(int id)
/*     */   {
/* 741 */     int i = this.modelo.getRowCount();
/* 742 */     for (int x = 0; x < i; x++)
/* 743 */       this.manejoAM.crearApunte(id, Integer.parseInt(this.tabla.getValueAt(x, 0).toString()), Double.parseDouble(this.tabla.getValueAt(x, 2).toString()), this.tabla.getValueAt(x, 1).toString());
/*     */   }
/*     */ 
/*     */   public void keyPressed(KeyEvent e)
/*     */   {
/* 751 */     int tecla = e.getKeyCode();
/* 752 */     Object campo = e.getSource();
/* 753 */     if (e.isAltDown()) {
/* 754 */       if (campo == this.porcentaje) {
/* 755 */         if (tecla == 67) {
/* 756 */           Inicio.calculadora.colocaOrigen(this.porcentaje);
/* 757 */           Inicio.calculadora.setVisible(true);
/* 758 */         } else if (tecla == 80) {
/* 759 */           this.porcentaje.setText(Inicio.calculadora.getResultado());
/*     */         }
/*     */       }
/* 762 */       if ((campo == this.concepto) && (tecla == 67)) {
/* 763 */         MostrarConceptos dlg2 = new MostrarConceptos(this, Mensajes.getString("conceptos"), true);
/* 764 */         Dimension dlgSize = dlg2.getPreferredSize();
/* 765 */         Dimension frmSize = getSize();
/* 766 */         Point loc = getLocation();
/* 767 */         dlg2.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
/*     */ 
/* 769 */         dlg2.setVisible(true);
/* 770 */         if (!dlg2.Seleccion().equals("")) {
/* 771 */           this.concepto.setText(dlg2.Seleccion());
/*     */         }
/*     */       }
/* 774 */       if ((campo == this.subcuenta) && (tecla == 67)) {
/* 775 */         MostrarCuentas dlg2 = new MostrarCuentas(this, Mensajes.getString("cuentas"), true);
/* 776 */         Dimension dlgSize = dlg2.getPreferredSize();
/* 777 */         Dimension frmSize = getSize();
/* 778 */         Point loc = getLocation();
/* 779 */         dlg2.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
/*     */ 
/* 781 */         dlg2.setVisible(true);
/* 782 */         if (!dlg2.Seleccion().equals("")) {
/* 783 */           ((JTextComponent)campo).setText(dlg2.Seleccion());
/*     */         }
/*     */       }
/*     */     }
/* 787 */     if (tecla == 10)
/* 788 */       if (campo == this.descripcion)
/* 789 */         this.marca.requestFocus();
/* 790 */       else if (campo == this.marca)
/* 791 */         this.concepto.requestFocus();
/* 792 */       else if (campo == this.concepto)
/* 793 */         this.subcuenta.requestFocus();
/* 794 */       else if (campo == this.cuenta)
/* 795 */         this.DH.requestFocus();
/* 796 */       else if (campo == this.subcuenta)
/* 797 */         this.DH.requestFocus();
/* 798 */       else if (campo == this.DH)
/* 799 */         this.porcentaje.requestFocus();
/* 800 */       else if (campo == this.porcentaje)
/* 801 */         this.Intro.requestFocus();
/*     */   }
/*     */ 
/*     */   public void keyReleased(KeyEvent arg0)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void keyTyped(KeyEvent arg0)
/*     */   {
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.asientosModelo.Editar
 * JD-Core Version:    0.6.2
 */