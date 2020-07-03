/*      */ package contaes;
/*      */ 
/*      */ import contaes.auxiliar.CampoCuenta;
/*      */ import contaes.auxiliar.CyclingSpinnerListModel;
/*      */ import contaes.auxiliar.DocNumPositivos;
/*      */ import contaes.auxiliar.DocTreintaCarac;
/*      */ import contaes.auxiliar.DocVeinteCarac;
/*      */ import contaes.auxiliarTablas.CentroColorRenderer;
/*      */ import contaes.auxiliarTablas.DefaultTableModelNotEditable;
/*      */ import contaes.auxiliarTablas.GeneralColorRenderer;
/*      */ import contaes.auxiliarTablas.ImporteColorRenderer;
/*      */ import contaes.calendario.ICalendarTextField;
/*      */ import contaes.dialogosAuxiliares.MostrarConceptos;
/*      */ import contaes.dialogosAuxiliares.MostrarCuentas;
/*      */ import contaes.dialogosFunciones.Calculadora;
/*      */ import contaes.manejoDatos.ManejoApuntes;
/*      */ import contaes.manejoDatos.ManejoAsientos;
/*      */ import contaes.manejoDatos.ManejoSubcuentas;
/*      */ import contaes.manejoDatos.ManejoVencimientos;
/*      */ import contaes.manejoDatos.TipoApunte;
/*      */ import contaes.manejoDatos.TipoAsiento;
/*      */ import contaes.manejoDatos.TipoSubcuenta;
/*      */ import contaes.manejoDatos.funciones.ComprobarDato;
/*      */ import internationalization.Mensajes;
/*      */ import java.awt.BorderLayout;
/*      */ import java.awt.Color;
/*      */ import java.awt.Container;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.Font;
/*      */ import java.awt.GridBagConstraints;
/*      */ import java.awt.GridBagLayout;
/*      */ import java.awt.Insets;
/*      */ import java.awt.Point;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.ActionListener;
/*      */ import java.awt.event.FocusEvent;
/*      */ import java.awt.event.FocusListener;
/*      */ import java.awt.event.KeyAdapter;
/*      */ import java.awt.event.KeyEvent;
/*      */ import java.awt.event.MouseAdapter;
/*      */ import java.awt.event.MouseEvent;
/*      */ import java.text.DecimalFormat;
/*      */ import java.text.DecimalFormatSymbols;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Date;
/*      */ import java.util.GregorianCalendar;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedList;
/*      */ import javax.swing.BorderFactory;
/*      */ import javax.swing.ImageIcon;
/*      */ import javax.swing.JButton;
/*      */ import javax.swing.JLabel;
/*      */ import javax.swing.JMenuItem;
/*      */ import javax.swing.JOptionPane;
/*      */ import javax.swing.JPanel;
/*      */ import javax.swing.JPopupMenu;
/*      */ import javax.swing.JScrollPane;
/*      */ import javax.swing.JSpinner;
/*      */ import javax.swing.JTable;
/*      */ import javax.swing.JTextField;
/*      */ import javax.swing.JToolBar;
/*      */ import javax.swing.JViewport;
/*      */ import javax.swing.ListSelectionModel;
/*      */ import javax.swing.SpinnerModel;
/*      */ import javax.swing.event.InternalFrameAdapter;
/*      */ import javax.swing.event.InternalFrameEvent;
/*      */ import javax.swing.event.ListSelectionEvent;
/*      */ import javax.swing.event.ListSelectionListener;
/*      */ import javax.swing.table.TableColumn;
/*      */ import javax.swing.table.TableColumnModel;
/*      */ 
/*      */ public final class Asientos extends MarcoInternoAsientosFacturas
/*      */ {
/*   94 */   private ManejoVencimientos vencimientoM = new ManejoVencimientos(Inicio.getCEmpresa());
/*      */ 
/*   96 */   private double debeTotal = 0.0D; private double haberTotal = 0.0D; private double saldoTotal = 0.0D;
/*      */ 
/*   98 */   private int selectedRow = 0;
/*   99 */   private int id_asto = -1;
/*      */ 
/*  101 */   DefaultTableModelNotEditable modelo = null;
/*  102 */   JTable tabla = null;
/*  103 */   TableColumn columna = null;
/*      */ 
/*  105 */   ImageIcon iconoIntro = createImageIcon("/contaes/iconos/ok.png");
/*  106 */   ImageIcon iconoRedo = createImageIcon("/contaes/iconos/redo.png");
/*  107 */   ImageIcon iconoNuevo = createImageIcon("/contaes/iconos/new.png");
/*  108 */   ImageIcon iconoGenerar = createImageIcon("/contaes/iconos/save16.png");
/*  109 */   ImageIcon iconoModif = createImageIcon("/contaes/iconos/edit.png");
/*  110 */   ImageIcon iconoBorrar = createImageIcon("/contaes/iconos/delete.png");
/*  111 */   ImageIcon iconoDatos = createImageIcon("/contaes/iconos/MdatosCta.png");
/*      */ 
/*  113 */   JToolBar barraHerramientas = new JToolBar();
/*  114 */   JPanel jPanel = new JPanel();
/*  115 */   JPanel jPanel1 = new JPanel();
/*  116 */   JPanel jPanel2 = new JPanel();
/*  117 */   JPanel jPanel3 = new JPanel();
/*  118 */   JScrollPane jScrollPane = new JScrollPane();
/*  119 */   BorderLayout borderLayout = new BorderLayout();
/*  120 */   GridBagLayout gridBagLayout1 = new GridBagLayout();
/*  121 */   GridBagLayout gridBagLayout2 = new GridBagLayout();
/*  122 */   JLabel jLabel1 = new JLabel();
/*  123 */   JLabel jLabel2 = new JLabel();
/*  124 */   JLabel jLabel3 = new JLabel();
/*  125 */   JLabel jLabel4 = new JLabel();
/*  126 */   ICalendarTextField fecha = new ICalendarTextField();
/*  127 */   JTextField numeroAsiento = new JTextField();
/*  128 */   JTextField nombreCuenta = new JTextField();
/*  129 */   JTextField saldoCuenta = new JTextField();
/*  130 */   JTextField debeAsiento = new JTextField();
/*  131 */   JTextField haberAsiento = new JTextField();
/*  132 */   JTextField saldoAsiento = new JTextField();
/*  133 */   JTextField documento = new JTextField();
/*  134 */   CampoCuenta cuenta = new CampoCuenta(this.subcuentaM);
/*  135 */   JTextField concepto = new JTextField();
/*  136 */   JTextField importe = new JTextField();
/*  137 */   JTextField marca = new JTextField();
/*  138 */   JButton crearAsiento = new JButton();
/*  139 */   JButton cancelar = new JButton();
/*  140 */   JButton insertarApunte = new JButton();
/*  141 */   JButton modificarApunte = new JButton();
/*  142 */   String[] debeHaber = { Mensajes.getString("debeA"), Mensajes.getString("haberA") };
/*  143 */   CyclingSpinnerListModel modeloDH = new CyclingSpinnerListModel(this.debeHaber);
/*      */ 
/*  145 */   JSpinner jSpinnerDH = new JSpinner(this.modeloDH);
/*      */   DecimalFormat fn;
/*      */   private Acciones escuchaAccion;
/*  148 */   JPopupMenu menuEmergente = new JPopupMenu();
/*  149 */   JMenuItem jMenuItem = null;
/*  150 */   JMenuItem jMenuItem1 = null;
/*  151 */   JMenuItem jMenuItem2 = null;
/*      */ 
/*      */   public Asientos() {
/*  154 */     super(Mensajes.getString("introAsto"));
/*      */     try
/*      */     {
/*  157 */       initialize();
/*      */     } catch (Exception exception) {
/*  159 */       exception.printStackTrace();
/*      */     }
/*      */   }
/*      */ 
/*      */   private void initialize()
/*      */     throws Exception
/*      */   {
/*  170 */     TeclaPulsada escuchaTecla = new TeclaPulsada();
/*  171 */     this.escuchaAccion = new Acciones();
/*  172 */     AccionesRaton escuchaRaton = new AccionesRaton();
/*      */ 
/*  174 */     addInternalFrameListener(new InternalFrameAdapter()
/*      */     {
/*      */       public void internalFrameDeactivated(InternalFrameEvent e)
/*      */       {
/*  178 */         Asientos.this.marcoDesactivado();
/*      */       }
/*      */ 
/*      */       public void internalFrameActivated(InternalFrameEvent e)
/*      */       {
/*  183 */         Asientos.this.marcoActivado();
/*      */       }
/*      */ 
/*      */       public void internalFrameClosed(InternalFrameEvent e)
/*      */       {
/*  188 */         Inicio.frame.eliminarMarcoMenu(Mensajes.getString("introAsto"));
/*      */       }
/*      */     });
/*  192 */     DecimalFormatSymbols formato = new DecimalFormatSymbols();
/*  193 */     formato.setDecimalSeparator(',');
/*  194 */     formato.setPerMill('.');
/*  195 */     this.fn = new DecimalFormat("#,###,##0.00", formato);
/*      */ 
/*  197 */     this.menuEmergente.add(getJMenuItem());
/*  198 */     this.menuEmergente.add(getJMenuItem1());
/*  199 */     this.menuEmergente.add(getJMenuItem2());
/*      */ 
/*  201 */     addBotones(this.barraHerramientas);
/*  202 */     this.barraHerramientas.setRollover(true);
/*      */ 
/*  204 */     int inset = 40;
/*      */ 
/*  206 */     setBounds(inset / 2, inset / 2, 590, 430);
/*  207 */     setFrameIcon(new ImageIcon(getClass().getResource("/contaes/iconos/A18.png")));
/*  208 */     getContentPane().setLayout(this.borderLayout);
/*  209 */     this.jPanel.setLayout(this.gridBagLayout1);
/*  210 */     this.jPanel.setBorder(BorderFactory.createEtchedBorder(1));
/*  211 */     String[] columnas = { Mensajes.getString("cuenta"), Mensajes.getString("concepto"), Mensajes.getString("debeHaberA"), Mensajes.getString("importe") };
/*  212 */     this.modelo = new DefaultTableModelNotEditable(columnas, 0);
/*  213 */     this.tabla = new JTable(this.modelo);
/*  214 */     this.tabla.addMouseListener(escuchaRaton);
/*  215 */     this.tabla.setSelectionMode(0);
/*  216 */     this.tabla.setPreferredScrollableViewportSize(new Dimension(442, 70));
/*  217 */     ListSelectionModel rowSM = this.tabla.getSelectionModel();
/*  218 */     rowSM.addListSelectionListener(new ListSelectionListener()
/*      */     {
/*      */       public void valueChanged(ListSelectionEvent arg0) {
/*  221 */         Asientos.this.asientosSeleccionTabla(arg0);
/*      */       }
/*      */     });
/*  225 */     this.columna = this.tabla.getColumnModel().getColumn(0);
/*  226 */     this.columna.setPreferredWidth(85);
/*      */ 
/*  228 */     this.columna = this.tabla.getColumnModel().getColumn(1);
/*  229 */     this.columna.setPreferredWidth(250);
/*  230 */     this.columna = this.tabla.getColumnModel().getColumn(2);
/*  231 */     this.columna.setPreferredWidth(27);
/*  232 */     this.columna.setCellRenderer(new CentroColorRenderer());
/*  233 */     this.columna = this.tabla.getColumnModel().getColumn(3);
/*  234 */     this.columna.setPreferredWidth(80);
/*  235 */     this.columna.setCellRenderer(new ImporteColorRenderer());
/*  236 */     this.tabla.setDefaultRenderer(Object.class, new GeneralColorRenderer());
/*  237 */     this.jPanel1.setLayout(null);
/*  238 */     this.jLabel1.setText(Mensajes.getString("asiento"));
/*  239 */     this.jLabel1.setBounds(new Rectangle(11, 15, 56, 15));
/*  240 */     this.jLabel1.addMouseListener(escuchaRaton);
/*  241 */     this.numeroAsiento.setBackground(new Color(255, 235, 235));
/*  242 */     this.numeroAsiento.setEnabled(false);
/*  243 */     this.numeroAsiento.setDisabledTextColor(Color.black);
/*  244 */     this.numeroAsiento.setEditable(false);
/*  245 */     this.numeroAsiento.setText("");
/*  246 */     this.numeroAsiento.setBounds(new Rectangle(70, 13, 60, 19));
/*  247 */     this.jLabel2.setText(Mensajes.getString("fecha"));
/*  248 */     this.jLabel2.setBounds(new Rectangle(141, 15, 45, 15));
/*  249 */     this.jLabel2.setLabelFor(this.fecha);
/*  250 */     this.fecha.setDate(null);
/*  251 */     this.fecha.setMinimumSize(new Dimension(100, 26));
/*  252 */     this.fecha.setBounds(new Rectangle(189, 9, 119, 26));
/*  253 */     this.fecha.setToolTipText(Mensajes.getString("formatoFecha"));
/*  254 */     this.fecha.setComponente(this.documento);
/*  255 */     this.jLabel3.setText(Mensajes.getString("marca"));
/*  256 */     this.jLabel3.setBounds(new Rectangle(470, 15, 45, 15));
/*  257 */     this.jLabel3.setToolTipText(Mensajes.getString("marcatt"));
/*  258 */     this.jLabel3.addMouseListener(escuchaRaton);
/*  259 */     this.marca.setText("");
/*  260 */     this.marca.setBounds(new Rectangle(520, 13, 20, 19));
/*  261 */     this.marca.setToolTipText(Mensajes.getString("marcaTT"));
/*  262 */     this.marca.addKeyListener(escuchaTecla);
/*  263 */     this.jLabel4.setText(Mensajes.getString("doc"));
/*  264 */     this.jLabel4.setBounds(new Rectangle(330, 15, 40, 15));
/*  265 */     this.documento.setText("");
/*  266 */     this.documento.setBounds(new Rectangle(372, 13, 85, 19));
/*  267 */     this.documento.setDocument(new DocVeinteCarac());
/*  268 */     this.documento.addKeyListener(escuchaTecla);
/*  269 */     this.jPanel2.setLayout(this.gridBagLayout2);
/*  270 */     this.jPanel2.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3), BorderFactory.createBevelBorder(1)));
/*  271 */     this.haberAsiento.setBackground(new Color(255, 235, 235));
/*  272 */     this.haberAsiento.setEnabled(false);
/*  273 */     this.haberAsiento.setDisabledTextColor(Color.black);
/*  274 */     this.haberAsiento.setEditable(false);
/*  275 */     this.haberAsiento.setFocusable(false);
/*  276 */     this.haberAsiento.setText("");
/*  277 */     this.haberAsiento.setHorizontalAlignment(4);
/*  278 */     this.saldoAsiento.setBackground(new Color(255, 235, 235));
/*  279 */     this.saldoAsiento.setEnabled(false);
/*  280 */     this.saldoAsiento.setDisabledTextColor(Color.black);
/*  281 */     this.saldoAsiento.setEditable(false);
/*  282 */     this.saldoAsiento.setFocusable(false);
/*  283 */     this.saldoAsiento.setText("");
/*  284 */     this.saldoAsiento.setHorizontalAlignment(4);
/*  285 */     this.crearAsiento.setActionCommand("crear_asto");
/*  286 */     this.crearAsiento.setText(Mensajes.getString("generar"));
/*  287 */     this.crearAsiento.setIcon(this.iconoGenerar);
/*  288 */     this.crearAsiento.setVerticalTextPosition(0);
/*  289 */     this.crearAsiento.setHorizontalTextPosition(2);
/*  290 */     this.crearAsiento.addActionListener(this.escuchaAccion);
/*  291 */     this.cancelar.setText(Mensajes.getString("nuevo"));
/*  292 */     this.cancelar.setIcon(this.iconoNuevo);
/*  293 */     this.cancelar.setVerticalTextPosition(0);
/*  294 */     this.cancelar.setHorizontalTextPosition(2);
/*  295 */     this.cancelar.setToolTipText(Mensajes.getString("nuevoAstott"));
/*  296 */     this.cancelar.addActionListener(this.escuchaAccion);
/*  297 */     this.jPanel3.setLayout(null);
/*  298 */     this.cuenta.setText("");
/*  299 */     this.cuenta.setBounds(new Rectangle(43, 188, 75, 19));
/*  300 */     this.cuenta.addKeyListener(escuchaTecla);
/*  301 */     this.cuenta.setToolTipText(Mensajes.getString("abrirCuentastt"));
/*  302 */     this.cuenta.addMouseListener(escuchaRaton);
/*  303 */     this.cuenta.addFocusListener(new FocusListener()
/*      */     {
/*      */       public void focusGained(FocusEvent arg0) {
/*      */       }
/*      */ 
/*      */       public void focusLost(FocusEvent arg0) {
/*  309 */         Asientos.this.colocarDatosCuenta(Asientos.this.cuenta.getText());
/*      */       }
/*      */     });
/*  312 */     this.concepto.setText("");
/*  313 */     this.concepto.setDocument(new DocTreintaCarac());
/*  314 */     this.concepto.setToolTipText(Mensajes.getString("abrirConceptostt"));
/*  315 */     this.concepto.setBounds(new Rectangle(121, 188, 240, 19));
/*  316 */     this.concepto.addKeyListener(escuchaTecla);
/*  317 */     this.concepto.addMouseListener(escuchaRaton);
/*  318 */     this.concepto.addFocusListener(new FocusListener()
/*      */     {
/*      */       public void focusGained(FocusEvent arg0) {
/*  321 */         Asientos.this.concepto.selectAll();
/*      */       }
/*      */ 
/*      */       public void focusLost(FocusEvent arg0)
/*      */       {
/*      */       }
/*      */     });
/*  327 */     this.importe.setText("");
/*  328 */     this.importe.setDocument(new DocNumPositivos());
/*  329 */     this.importe.setBounds(new Rectangle(409, 188, 69, 19));
/*  330 */     this.importe.setToolTipText(Mensajes.getString("importestt"));
/*  331 */     this.importe.addKeyListener(escuchaTecla);
/*  332 */     this.importe.addMouseListener(escuchaRaton);
/*  333 */     this.importe.addFocusListener(new FocusListener()
/*      */     {
/*      */       public void focusGained(FocusEvent arg0) {
/*  336 */         Asientos.this.importe.selectAll();
/*      */       }
/*      */ 
/*      */       public void focusLost(FocusEvent arg0)
/*      */       {
/*      */       }
/*      */     });
/*  342 */     this.insertarApunte.setBounds(new Rectangle(481, 185, 25, 25));
/*  343 */     this.insertarApunte.setActionCommand("insertar");
/*  344 */     this.insertarApunte.setIcon(this.iconoIntro);
/*  345 */     this.insertarApunte.setToolTipText(Mensajes.getString("introAptt"));
/*      */ 
/*  347 */     this.insertarApunte.addActionListener(this.escuchaAccion);
/*  348 */     this.jSpinnerDH.setFont(new Font("Dialog", 0, 12));
/*  349 */     this.jSpinnerDH.setBounds(new Rectangle(364, 185, 42, 24));
/*  350 */     this.jSpinnerDH.addKeyListener(escuchaTecla);
/*  351 */     this.nombreCuenta.setBackground(new Color(255, 235, 235));
/*  352 */     this.nombreCuenta.setEnabled(false);
/*  353 */     this.nombreCuenta.setDisabledTextColor(Color.black);
/*  354 */     this.nombreCuenta.setEditable(false);
/*  355 */     this.nombreCuenta.setFocusable(false);
/*  356 */     this.saldoCuenta.setBackground(new Color(255, 235, 235));
/*  357 */     this.saldoCuenta.setEnabled(false);
/*  358 */     this.saldoCuenta.setDisabledTextColor(Color.black);
/*  359 */     this.saldoCuenta.setEditable(false);
/*  360 */     this.saldoCuenta.setFocusable(false);
/*  361 */     this.saldoCuenta.setHorizontalAlignment(4);
/*  362 */     this.debeAsiento.setBackground(new Color(255, 235, 235));
/*  363 */     this.debeAsiento.setEnabled(false);
/*  364 */     this.debeAsiento.setDisabledTextColor(Color.black);
/*  365 */     this.debeAsiento.setEditable(false);
/*  366 */     this.debeAsiento.setFocusable(false);
/*  367 */     this.debeAsiento.setHorizontalAlignment(4);
/*  368 */     this.jScrollPane.setBounds(new Rectangle(34, 9, 483, 164));
/*  369 */     this.jScrollPane.setBorder(BorderFactory.createLoweredBevelBorder());
/*  370 */     this.modificarApunte.setBounds(new Rectangle(529, 74, 32, 32));
/*  371 */     this.modificarApunte.setToolTipText(Mensajes.getString("modifAptt"));
/*  372 */     this.modificarApunte.setActionCommand("Modificar");
/*  373 */     this.modificarApunte.setIcon(this.iconoRedo);
/*  374 */     this.modificarApunte.addActionListener(this.escuchaAccion);
/*  375 */     this.jPanel.add(this.jPanel1, new GridBagConstraints(0, 0, 1, 1, 1.0D, 1.0D, 10, 1, new Insets(1, -1, 0, 1), 579, 42));
/*      */ 
/*  378 */     this.jPanel1.add(this.numeroAsiento);
/*  379 */     this.jPanel1.add(this.jLabel1);
/*  380 */     this.jPanel1.add(this.jLabel2);
/*  381 */     this.jPanel1.add(this.fecha);
/*  382 */     this.jPanel1.add(this.jLabel4);
/*  383 */     this.jPanel1.add(this.documento);
/*  384 */     this.jPanel1.add(this.marca);
/*  385 */     this.jPanel1.add(this.jLabel3);
/*  386 */     this.jPanel.add(this.jPanel3, new GridBagConstraints(0, 1, 1, 1, 1.0D, 1.0D, 10, 1, new Insets(0, -1, 0, 1), 578, 216));
/*      */ 
/*  389 */     this.jPanel3.add(this.concepto);
/*  390 */     this.jPanel3.add(this.cuenta);
/*  391 */     this.jPanel3.add(this.jSpinnerDH);
/*  392 */     this.jPanel3.add(this.importe);
/*  393 */     this.jPanel3.add(this.insertarApunte);
/*  394 */     this.jPanel3.add(this.jScrollPane);
/*  395 */     this.jPanel3.add(this.modificarApunte);
/*  396 */     this.jScrollPane.getViewport().add(this.tabla);
/*  397 */     this.jPanel2.add(this.nombreCuenta, new GridBagConstraints(0, 0, 1, 1, 1.0D, 0.0D, 17, 2, new Insets(17, 17, 0, 0), 226, 0));
/*      */ 
/*  399 */     this.jPanel.add(this.jPanel2, new GridBagConstraints(0, 2, 1, 1, 1.0D, 1.0D, 10, 2, new Insets(0, -1, 1, 1), 578, 0));
/*      */ 
/*  402 */     this.jPanel2.add(this.cancelar, new GridBagConstraints(0, 1, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(10, 159, 25, 0), 21, 0));
/*      */ 
/*  404 */     this.jPanel2.add(this.crearAsiento, new GridBagConstraints(1, 1, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(10, 15, 25, -20), 9, 0));
/*      */ 
/*  408 */     this.jPanel2.add(this.saldoAsiento, new GridBagConstraints(2, 1, 2, 1, 1.0D, 0.0D, 17, 2, new Insets(13, 60, 25, 60), 76, 0));
/*      */ 
/*  410 */     this.jPanel2.add(this.debeAsiento, new GridBagConstraints(2, 0, 1, 1, 1.0D, 0.0D, 17, 2, new Insets(17, 20, 0, 0), 76, 0));
/*      */ 
/*  414 */     this.jPanel2.add(this.haberAsiento, new GridBagConstraints(3, 0, 1, 1, 1.0D, 0.0D, 17, 2, new Insets(17, 15, 0, 25), 76, 0));
/*      */ 
/*  416 */     this.jPanel2.add(this.saldoCuenta, new GridBagConstraints(1, 0, 1, 1, 1.0D, 0.0D, 17, 2, new Insets(17, 21, 0, 0), 85, 0));
/*      */ 
/*  420 */     getContentPane().add(this.jPanel, "Center");
/*  421 */     getContentPane().add(this.barraHerramientas, "First");
/*      */   }
/*      */ 
/*      */   private JMenuItem getJMenuItem2()
/*      */   {
/*  430 */     if (this.jMenuItem2 == null) {
/*  431 */       this.jMenuItem2 = new JMenuItem();
/*  432 */       this.jMenuItem2.setText(Mensajes.getString("datosCuenta"));
/*  433 */       this.jMenuItem2.setIcon(this.iconoDatos);
/*  434 */       this.jMenuItem2.addActionListener(this.escuchaAccion);
/*      */     }
/*  436 */     return this.jMenuItem2;
/*      */   }
/*      */ 
/*      */   private JMenuItem getJMenuItem1()
/*      */   {
/*  445 */     if (this.jMenuItem1 == null) {
/*  446 */       this.jMenuItem1 = new JMenuItem();
/*  447 */       this.jMenuItem1.setText(Mensajes.getString("suprimir"));
/*  448 */       this.jMenuItem1.setIcon(this.iconoBorrar);
/*  449 */       this.jMenuItem1.addActionListener(this.escuchaAccion);
/*      */     }
/*  451 */     return this.jMenuItem1;
/*      */   }
/*      */ 
/*      */   private JMenuItem getJMenuItem()
/*      */   {
/*  460 */     if (this.jMenuItem == null) {
/*  461 */       this.jMenuItem = new JMenuItem();
/*  462 */       this.jMenuItem.setText(Mensajes.getString("modificar"));
/*  463 */       this.jMenuItem.setIcon(this.iconoModif);
/*  464 */       this.jMenuItem.addActionListener(this.escuchaAccion);
/*      */     }
/*  466 */     return this.jMenuItem;
/*      */   }
/*      */ 
/*      */   public void mostrarDatosCuenta(String cadena, String dh, double importe, int NuevoAsiento)
/*      */   {
/*  478 */     TipoSubcuenta cuentaTemp = this.subcuentaM.datos(Integer.parseInt(cadena));
/*  479 */     double debe = 0.0D; double haber = 0.0D; double saldo = 0.0D;
/*  480 */     if ((dh.equals(Mensajes.getString("debeA"))) && (NuevoAsiento == 0))
/*  481 */       debe = importe;
/*  482 */     else if ((dh.equals(Mensajes.getString("haberA"))) && (NuevoAsiento == 0)) {
/*  483 */       haber = importe;
/*      */     }
/*  485 */     debe += cuentaTemp.getDebe();
/*  486 */     haber += cuentaTemp.getHaber();
/*  487 */     saldo = debe - haber;
/*  488 */     String datos = NuevoAsiento == 0 ? Mensajes.getString("datosInclApte") + ":\n" : "";
/*  489 */     datos = datos + Mensajes.getString("cuenta") + ":\n " + cadena + " - " + cuentaTemp.getNombre() + "\n" + Mensajes.getString("debe") + ":  " + this.fn.format(debe) + "\n" + Mensajes.getString("haber") + ": " + this.fn.format(haber) + "\n" + Mensajes.getString("saldo") + ": " + this.fn.format(saldo);
/*      */ 
/*  495 */     JOptionPane.showMessageDialog(getContentPane(), datos, Mensajes.getString("datosCuenta"), 1);
/*      */   }
/*      */ 
/*      */   private void vaciar()
/*      */   {
/*  505 */     this.numeroAsiento.setText("");
/*  506 */     this.documento.setText("");
/*  507 */     this.marca.setText("");
/*  508 */     int i = this.modelo.getRowCount();
/*  509 */     for (int x = 0; x < i; x++) {
/*  510 */       this.modelo.removeRow(0);
/*      */     }
/*  512 */     this.cuenta.setText("");
/*  513 */     this.concepto.setText("");
/*  514 */     this.importe.setText("");
/*  515 */     this.jSpinnerDH.setValue(Mensajes.getString("debeA"));
/*  516 */     this.debeTotal = 0.0D;
/*  517 */     this.haberTotal = 0.0D;
/*  518 */     this.saldoTotal = 0.0D;
/*  519 */     this.debeAsiento.setText("");
/*  520 */     this.haberAsiento.setText("");
/*  521 */     this.saldoAsiento.setText("");
/*  522 */     this.nombreCuenta.setText("");
/*  523 */     this.saldoCuenta.setText("");
/*      */   }
/*      */ 
/*      */   private double calcular_saldos_asiento(String debeHaber, double cuanto)
/*      */   {
/*  533 */     if (debeHaber.equals(Mensajes.getString("debeA"))) {
/*  534 */       this.debeTotal += cuanto;
/*  535 */       this.debeAsiento.setText(this.fn.format(this.debeTotal));
/*      */     } else {
/*  537 */       this.haberTotal += cuanto;
/*  538 */       this.haberAsiento.setText(this.fn.format(this.haberTotal));
/*      */     }
/*  540 */     this.saldoTotal = (this.debeTotal - this.haberTotal);
/*  541 */     this.saldoAsiento.setText(this.fn.format(this.saldoTotal));
/*  542 */     return this.saldoTotal;
/*      */   }
/*      */ 
/*      */   private void crearAsiento()
/*      */   {
/*  630 */     if (!this.numeroAsiento.getText().equals("")) {
/*  631 */       if (((this.saldoTotal > 0.005D ? 1 : 0) | (this.saldoTotal < -0.005D ? 1 : 0)) == 0) {
/*  632 */         int i = this.modelo.getRowCount();
/*  633 */         if (i > 0) {
/*  634 */           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/*  635 */           Date fecha_d = this.fecha.getDate();
/*  636 */           String fecha_bd = fecha_d != null ? sdf.format(fecha_d) : "";
/*  637 */           if (comprobarFecha(fecha_bd)) {
/*  638 */             String marca1 = this.marca.getText().length() > 1 ? this.marca.getText().substring(0, 1).toUpperCase() : this.marca.getText().toUpperCase();
/*  639 */             if ((!marca1.equals("E")) && (!marca1.equals("R")))
/*      */             {
/*  641 */               if ((Inicio.p.getAsiento() == -1) && (this.id_asto != -1)) {
/*  642 */                 this.asientoM.modificar(this.id_asto, fecha_bd, this.documento.getText(), marca1);
/*      */ 
/*  644 */                 LinkedList idApuntes = new LinkedList();
/*  645 */                 idApuntes.addAll(this.asientoM.apuntesEnAsiento(this.id_asto));
/*  646 */                 for (Iterator i$ = idApuntes.iterator(); i$.hasNext(); ) { int x = ((Integer)i$.next()).intValue();
/*  647 */                   this.apunteM.borrar(x);
/*      */                 }
/*  649 */                 Inicio.p.setAsiento(0);
/*      */               }
/*      */               else
/*      */               {
/*  653 */                 int newNum = this.asientoM.nuevoNumero();
/*  654 */                 this.id_asto = this.asientoM.crear(newNum, fecha_bd, this.documento.getText(), marca1);
/*      */ 
/*  656 */                 if ((marca1.equals("P")) || (marca1.equals("Q"))) {
/*  657 */                   boolean emitida = marca1.equals("Q");
/*  658 */                   ArrayList listaV = this.vencimientoM.listaFechaFactura(emitida, this.documento.getText(), fecha_d);
/*  659 */                   if (!listaV.isEmpty()) {
/*  660 */                     this.vencimientoM.realizar(emitida, ((Integer)listaV.get(0)).intValue());
/*  661 */                     if (emitida)
/*  662 */                       Inicio.frame.renovarTabla(2);
/*      */                     else {
/*  664 */                       Inicio.frame.renovarTabla(1);
/*      */                     }
/*      */ 
/*      */                   }
/*      */ 
/*      */                 }
/*      */ 
/*      */               }
/*      */ 
/*  686 */               for (int x = 0; x < i; x++) {
/*  687 */                 int cuentaT = Integer.parseInt(this.tabla.getValueAt(x, 0).toString());
/*  688 */                 String conceptoT = this.tabla.getValueAt(x, 1).toString();
/*  689 */                 String DH = this.tabla.getValueAt(x, 2).toString();
/*  690 */                 double importeT = Double.parseDouble(this.tabla.getValueAt(x, 3).toString());
/*  691 */                 this.apunteM.crear(this.id_asto, cuentaT, conceptoT, DH, importeT);
/*      */               }
/*  693 */               Inicio.frame.renovarTabla(0);
/*  694 */               vaciar();
/*  695 */               nuevoNumeroAsiento();
/*      */             }
/*      */             else {
/*  698 */               mostrarMensaje("No es posible editar las facturas");
/*      */             }
/*      */           }
/*      */           else {
/*  702 */             mostrarMensaje("Fecha no correcta");
/*      */           }
/*      */         } else {
/*  705 */           mostrarMensaje("No hay apuntes");
/*      */         }
/*      */       }
/*      */       else {
/*  709 */         mostrarMensaje(Mensajes.getString("astDescuad"));
/*      */       }
/*      */     }
/*      */     else
/*  713 */       mostrarMensaje("Falta el número de asiento");
/*      */   }
/*      */ 
/*      */   private void mostrarMensaje(String mensaje)
/*      */   {
/*  718 */     JOptionPane.showMessageDialog(getContentPane(), mensaje);
/*      */   }
/*      */ 
/*      */   private void nuevoNumeroAsiento()
/*      */   {
/*  727 */     int numero = this.asientoM.nuevoNumero();
/*  728 */     this.numeroAsiento.setText(String.valueOf(numero));
/*  729 */     this.fecha.requestFocus();
/*      */   }
/*      */ 
/*      */   private void insertaApunte()
/*      */   {
/*  738 */     ComprobarDato dato = new ComprobarDato(this.importe.getText());
/*  739 */     if (!dato.esDoble()) {
/*  740 */       JOptionPane.showMessageDialog(getContentPane(), Mensajes.getString("importNoNum"));
/*  741 */     } else if (!this.cuenta.getText().equals(""))
/*      */     {
/*  743 */       Object[] apunte = { new Integer(Integer.parseInt(this.cuenta.getText())), this.concepto.getText(), this.jSpinnerDH.getValue(), new Double(Double.parseDouble(this.importe.getText())) };
/*      */ 
/*  745 */       this.modelo.addRow(apunte);
/*  746 */       this.tabla.changeSelection(this.tabla.getRowCount() - 1, 0, true, true);
/*  747 */       double saldo = calcular_saldos_asiento(this.jSpinnerDH.getValue().toString(), Double.parseDouble(this.importe.getText()));
/*  748 */       this.cuenta.setText("");
/*      */ 
/*  751 */       colocarSaldo(saldo);
/*  752 */       this.cuenta.requestFocus();
/*      */     } else {
/*  754 */       JOptionPane.showMessageDialog(getContentPane(), Mensajes.getString("indicaCta"));
/*  755 */       this.cuenta.requestFocus();
/*      */     }
/*      */   }
/*      */ 
/*      */   private void colocarDatosCuenta(String codigo)
/*      */   {
/*  778 */     this.nombreCuenta.setText(this.cuenta.getNombre());
/*  779 */     this.saldoCuenta.setText(this.fn.format(this.cuenta.getSaldo()));
/*      */   }
/*      */ 
/*      */   private void modificarBorrarApunte(boolean modificar)
/*      */   {
/*  791 */     if (this.selectedRow == -1) {
/*  792 */       return;
/*      */     }
/*  794 */     double valor = Double.parseDouble(this.tabla.getValueAt(this.selectedRow, 3).toString());
/*  795 */     if (modificar) {
/*  796 */       this.cuenta.setText(this.tabla.getValueAt(this.selectedRow, 0).toString());
/*  797 */       this.concepto.setText(this.tabla.getValueAt(this.selectedRow, 1).toString());
/*  798 */       this.jSpinnerDH.setValue(this.tabla.getValueAt(this.selectedRow, 2).toString());
/*  799 */       this.importe.setText(this.tabla.getValueAt(this.selectedRow, 3).toString());
/*      */     }
/*      */ 
/*  802 */     if (this.tabla.getValueAt(this.selectedRow, 2).toString().equals(Mensajes.getString("debeA"))) {
/*  803 */       this.debeTotal -= valor;
/*  804 */       this.debeAsiento.setText(this.fn.format(this.debeTotal));
/*      */     } else {
/*  806 */       this.haberTotal -= valor;
/*  807 */       this.haberAsiento.setText(this.fn.format(this.haberTotal));
/*      */     }
/*  809 */     this.saldoTotal = (this.debeTotal - this.haberTotal);
/*  810 */     this.saldoAsiento.setText(this.fn.format(this.saldoTotal));
/*  811 */     this.modelo.removeRow(this.selectedRow);
/*  812 */     this.cuenta.requestFocus();
/*      */   }
/*      */ 
/*      */   private void colocarSaldo(double saldo) {
/*  816 */     if (saldo >= 0.0D) {
/*  817 */       this.jSpinnerDH.getModel().setValue("H");
/*      */     } else {
/*  819 */       saldo = -1.0D * saldo;
/*  820 */       this.jSpinnerDH.getModel().setValue("D");
/*      */     }
/*  822 */     this.importe.setText(Double.toString(saldo));
/*      */   }
/*      */ 
/*      */   private void marcoActivado() {
/*  826 */     if (Inicio.p.getAsiento() > 0)
/*      */     {
/*  828 */       colocarAsiento(Inicio.p.getAsiento());
/*      */     }
/*  830 */     if (Inicio.p.getControl() == 1) {
/*  831 */       String cCuenta = Inicio.p.getCuentaStr();
/*  832 */       if (!cCuenta.equals("Error")) {
/*  833 */         this.cuenta.setText(cCuenta);
/*      */       }
/*  835 */       this.cuenta.requestFocus();
/*  836 */       Inicio.p.setControl(0);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void colocarAsiento(int numAsiento) {
/*  841 */     TipoAsiento asiento = this.asientoM.datos(true, numAsiento);
/*  842 */     if (asiento == null) {
/*  843 */       return;
/*      */     }
/*  845 */     vaciar();
/*  846 */     this.id_asto = asiento.getId_asiento();
/*  847 */     this.numeroAsiento.setText(String.valueOf(asiento.getNumero()));
/*  848 */     String cad = asiento.getFecha();
/*  849 */     int year = Integer.parseInt(cad.substring(0, 4));
/*  850 */     int month = Integer.parseInt(cad.substring(5, 7)) - 1;
/*  851 */     int date = Integer.parseInt(cad.substring(8));
/*  852 */     GregorianCalendar calendario = new GregorianCalendar();
/*  853 */     calendario.set(year, month, date);
/*  854 */     this.fecha.setCalendar(calendario);
/*  855 */     this.documento.setText(asiento.getDocumento());
/*  856 */     this.marca.setText(asiento.getMarca());
/*  857 */     LinkedList idApuntes = new LinkedList();
/*  858 */     idApuntes.addAll(this.asientoM.apuntesEnAsiento(this.id_asto));
/*      */ 
/*  860 */     for (Iterator i$ = idApuntes.iterator(); i$.hasNext(); ) { int x = ((Integer)i$.next()).intValue();
/*      */ 
/*  862 */       TipoApunte apunte = this.apunteM.datos(x);
/*  863 */       Object[] apte = { new Integer(apunte.getCuenta()), apunte.getConcepto(), apunte.getDH(), new Double(apunte.getImporte()) };
/*      */ 
/*  866 */       this.modelo.addRow(apte);
/*  867 */       calcular_saldos_asiento(apunte.getDH(), apunte.getImporte());
/*      */     }
/*  869 */     Inicio.p.setAsiento(-1);
/*      */   }
/*      */ 
/*      */   private void marcoDesactivado()
/*      */   {
/*  875 */     if ((Inicio.p.getControl() != 1) && (this.tabla.getRowCount() == 0))
/*  876 */       this.numeroAsiento.setText("");
/*      */   }
/*      */ 
/*      */   private void asientosSeleccionTabla(ListSelectionEvent e)
/*      */   {
/*  883 */     if (e.getValueIsAdjusting()) {
/*  884 */       return;
/*      */     }
/*  886 */     ListSelectionModel lsm = (ListSelectionModel)e.getSource();
/*  887 */     if (!lsm.isSelectionEmpty())
/*      */     {
/*  890 */       this.selectedRow = lsm.getMinSelectionIndex();
/*      */     }
/*      */   }
/*      */ 
/*      */   private void mostrarMarcas()
/*      */   {
/*  896 */     JOptionPane.showMessageDialog(getContentPane(), Mensajes.getString("marcaInfo"));
/*      */   }
/*      */ 
/*      */   protected void colocarDatos(int opcion) {
/*  900 */     int asiento = -1;
/*  901 */     switch (opcion) {
/*      */     case 0:
/*  903 */       asiento = this.asientoM.primero();
/*  904 */       if (asiento != -1)
/*  905 */         colocarAsiento(asiento); break;
/*      */     case 1:
/*  909 */       asiento = this.asientoM.ultimo();
/*  910 */       if (asiento != -1)
/*  911 */         colocarAsiento(asiento); break;
/*      */     case 2:
/*  915 */       asiento = anteriorSiguiente(0);
/*  916 */       if (asiento != -1)
/*  917 */         colocarAsiento(asiento); break;
/*      */     case 3:
/*  921 */       asiento = anteriorSiguiente(1);
/*  922 */       if (asiento != -1)
/*  923 */         colocarAsiento(asiento);
/*      */       break;
/*      */     }
/*      */   }
/*      */ 
/*      */   private int anteriorSiguiente(int cual)
/*      */   {
/*  930 */     int asiento = -1;
/*  931 */     String asientoS = this.numeroAsiento.getText();
/*  932 */     if (!asientoS.equals("")) {
/*  933 */       asiento = Integer.parseInt(asientoS);
/*  934 */       switch (cual) {
/*      */       case 0:
/*  936 */         asiento--;
/*  937 */         break;
/*      */       case 1:
/*  939 */         asiento++;
/*      */       }
/*      */     }
/*      */ 
/*  943 */     return asiento;
/*      */   }
/*      */ 
/*      */   private void mostrarConceptos()
/*      */   {
/*  950 */     MostrarConceptos dlg2 = new MostrarConceptos(Inicio.frame, Mensajes.getString("conceptos"), true);
/*  951 */     Dimension dlgSize = dlg2.getPreferredSize();
/*  952 */     Dimension frmSize = getSize();
/*  953 */     Point loc = getLocation();
/*  954 */     dlg2.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
/*      */ 
/*  956 */     dlg2.setVisible(true);
/*  957 */     if (!dlg2.Seleccion().equals(""))
/*  958 */       this.concepto.setText(dlg2.Seleccion());
/*      */   }
/*      */ 
/*      */   public void cerrarConexion()
/*      */   {
/*      */   }
/*      */ 
/*      */   private class AccionesRaton extends MouseAdapter
/*      */   {
/*      */     private AccionesRaton()
/*      */     {
/*      */     }
/*      */ 
/*      */     public void mouseClicked(MouseEvent e)
/*      */     {
/* 1072 */       Object fuente = e.getSource();
/* 1073 */       if (e.getButton() == 3) {
/* 1074 */         if (fuente == Asientos.this.cuenta) {
/* 1075 */           Asientos.this.activar_seleccion_cuenta(1);
/* 1076 */         } else if (fuente == Asientos.this.tabla) {
/* 1077 */           Asientos.this.tabla.changeSelection(Asientos.this.tabla.rowAtPoint(new Point(e.getX(), e.getY())), Asientos.this.tabla.columnAtPoint(new Point(e.getX(), e.getY())), false, false);
/*      */ 
/* 1081 */           Asientos.this.menuEmergente.show(e.getComponent(), e.getX(), e.getY());
/*      */         }
/* 1083 */         else if (fuente == Asientos.this.concepto) {
/* 1084 */           Asientos.this.mostrarConceptos();
/* 1085 */         } else if (fuente == Asientos.this.importe) {
/* 1086 */           Inicio.calculadora.colocaOrigen(Asientos.this.importe);
/* 1087 */           Inicio.calculadora.setVisible(true);
/*      */         }
/* 1089 */       } else if (fuente == Asientos.this.jLabel3)
/* 1090 */         Asientos.this.mostrarMarcas();
/* 1091 */       else if (fuente == Asientos.this.jLabel1)
/* 1092 */         Asientos.this.nuevoNumeroAsiento();
/*      */     }
/*      */   }
/*      */ 
/*      */   private class Acciones
/*      */     implements ActionListener
/*      */   {
/*      */     private Acciones()
/*      */     {
/*      */     }
/*      */ 
/*      */     public void actionPerformed(ActionEvent e)
/*      */     {
/* 1044 */       Object fuente = e.getSource();
/* 1045 */       if (fuente == Asientos.this.modificarApunte) {
/* 1046 */         Asientos.this.modificarBorrarApunte(true);
/* 1047 */       } else if (fuente == Asientos.this.insertarApunte) {
/* 1048 */         Asientos.this.insertaApunte();
/* 1049 */       } else if (fuente == Asientos.this.cancelar) {
/* 1050 */         Inicio.p.setAsiento(0);
/* 1051 */         Asientos.this.vaciar();
/* 1052 */         Asientos.this.nuevoNumeroAsiento();
/* 1053 */       } else if (fuente == Asientos.this.crearAsiento) {
/* 1054 */         Asientos.this.crearAsiento();
/* 1055 */       } else if (fuente == Asientos.this.jMenuItem) {
/* 1056 */         Asientos.this.modificarBorrarApunte(true);
/* 1057 */       } else if (fuente == Asientos.this.jMenuItem1) {
/* 1058 */         Asientos.this.modificarBorrarApunte(false);
/* 1059 */       } else if (fuente == Asientos.this.jMenuItem2) {
/* 1060 */         Asientos.this.mostrarDatosCuenta(Asientos.this.tabla.getValueAt(Asientos.this.selectedRow, 0).toString(), Asientos.this.tabla.getValueAt(Asientos.this.selectedRow, 2).toString(), Double.parseDouble(Asientos.this.tabla.getValueAt(Asientos.this.selectedRow, 3).toString()), Inicio.p.getAsiento());
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private class TeclaPulsada extends KeyAdapter
/*      */   {
/*      */     private TeclaPulsada()
/*      */     {
/*      */     }
/*      */ 
/*      */     public void keyPressed(KeyEvent e)
/*      */     {
/*  966 */       int tecla = e.getKeyCode();
/*  967 */       Object campo = e.getSource();
/*  968 */       if (e.isAltDown()) {
/*  969 */         if (campo == Asientos.this.cuenta) {
/*  970 */           if (tecla == 82) {
/*  971 */             if (Asientos.this.tabla.getRowCount() > 0) {
/*  972 */               Asientos.this.cuenta.setText(Asientos.this.tabla.getValueAt(Asientos.this.tabla.getRowCount() - 1, 0).toString());
/*  973 */               Asientos.this.concepto.requestFocus();
/*      */             }
/*  975 */           } else if (tecla == 67) {
/*  976 */             if (Asientos.this.cuenta.getText().equals(""))
/*  977 */               Asientos.this.activar_seleccion_cuenta(1);
/*      */           }
/*  979 */           else if ((tecla == 83) && 
/*  980 */             (Asientos.this.cuenta.getText().equals(""))) {
/*  981 */             MostrarCuentas dlg2 = new MostrarCuentas(Inicio.frame, Mensajes.getString("conceptos"), true);
/*  982 */             Dimension dlgSize = dlg2.getPreferredSize();
/*  983 */             Dimension frmSize = Asientos.this.getSize();
/*  984 */             Point loc = Asientos.this.getLocation();
/*  985 */             dlg2.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
/*      */ 
/*  987 */             dlg2.setVisible(true);
/*  988 */             if (!dlg2.Seleccion().equals("")) {
/*  989 */               Asientos.this.cuenta.setText(dlg2.Seleccion());
/*      */             }
/*      */           }
/*      */         }
/*  993 */         else if (campo == Asientos.this.concepto) {
/*  994 */           if (tecla == 82) {
/*  995 */             if (Asientos.this.tabla.getRowCount() > 0) {
/*  996 */               Asientos.this.concepto.setText(Asientos.this.tabla.getValueAt(Asientos.this.tabla.getRowCount() - 1, 1).toString());
/*  997 */               Asientos.this.jSpinnerDH.requestFocus();
/*      */             }
/*  999 */           } else if (tecla == 67)
/* 1000 */             Asientos.this.mostrarConceptos();
/*      */         }
/* 1002 */         else if (campo == Asientos.this.importe) {
/* 1003 */           if (tecla == 82) {
/* 1004 */             if (Asientos.this.tabla.getRowCount() > 0) {
/* 1005 */               Asientos.this.importe.setText(Asientos.this.tabla.getValueAt(Asientos.this.tabla.getRowCount() - 1, 3).toString());
/* 1006 */               Asientos.this.insertarApunte.requestFocus();
/*      */             }
/* 1008 */           } else if (tecla == 84) {
/* 1009 */             Asientos.this.importe.setText(Asientos.this.saldoTotal < 0.0D ? String.valueOf(-Asientos.this.saldoTotal) : String.valueOf(Asientos.this.saldoTotal));
/*      */           }
/* 1011 */           else if (tecla == 67) {
/* 1012 */             Inicio.calculadora.colocaOrigen(Asientos.this.importe);
/* 1013 */             Inicio.calculadora.setVisible(true);
/* 1014 */           } else if (tecla == 80) {
/* 1015 */             Asientos.this.importe.setText(Inicio.calculadora.getResultado());
/*      */           }
/*      */         }
/*      */       }
/*      */ 
/* 1020 */       if (tecla == 10)
/* 1021 */         if (campo == Asientos.this.documento)
/* 1022 */           Asientos.this.marca.requestFocus();
/* 1023 */         else if (campo == Asientos.this.marca)
/* 1024 */           Asientos.this.cuenta.requestFocus();
/* 1025 */         else if (campo == Asientos.this.cuenta)
/* 1026 */           Asientos.this.concepto.requestFocus();
/* 1027 */         else if (campo == Asientos.this.concepto)
/* 1028 */           Asientos.this.jSpinnerDH.requestFocus();
/* 1029 */         else if (campo == Asientos.this.jSpinnerDH)
/* 1030 */           Asientos.this.importe.requestFocus();
/* 1031 */         else if (campo == Asientos.this.importe)
/* 1032 */           Asientos.this.insertarApunte.requestFocus();
/*      */     }
/*      */   }
/*      */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.Asientos
 * JD-Core Version:    0.6.2
 */