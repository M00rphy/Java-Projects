/*      */ package contaes;
/*      */ 
/*      */ import contaes.auxiliar.AlinearCadena;
/*      */ import contaes.auxiliarTablas.BooleanColorRenderer;
/*      */ import contaes.auxiliarTablas.DerechaColorRenderer;
/*      */ import contaes.auxiliarTablas.FechaColorRenderer;
/*      */ import contaes.auxiliarTablas.GeneralColorRenderer;
/*      */ import contaes.auxiliarTablas.ImporteColorRenderer;
/*      */ import contaes.auxiliarTablas.ScrollableTableModel;
/*      */ import contaes.auxiliarTablas.TablaToCsv;
/*      */ import contaes.auxiliarTablas.TableSorter;
/*      */ import contaes.dialogosAuxiliares.CrearVencimientosV;
/*      */ import contaes.dialogosAuxiliares.MostrarFuentes;
/*      */ import contaes.dialogosAuxiliares.PagaVencimientos;
/*      */ import contaes.dialogosAuxiliares.PrintPreview;
/*      */ import contaes.manejoDatos.ManejoApuntes;
/*      */ import contaes.manejoDatos.ManejoAsientos;
/*      */ import contaes.manejoDatos.ManejoTablaVencimientos;
/*      */ import contaes.manejoDatos.ManejoVencimientos;
/*      */ import contaes.manejoDatos.TipoVencimiento;
/*      */ import contaes.manejoDatos.auxiliar.AddExtension;
/*      */ import contaes.manejoDatos.auxiliar.FechaHoy;
/*      */ import contaes.manejoDatos.auxiliar.FinLinea;
/*      */ import contaes.manejoDatos.auxiliar.GrabarFichero;
/*      */ import internationalization.Mensajes;
/*      */ import java.awt.BorderLayout;
/*      */ import java.awt.Color;
/*      */ import java.awt.Container;
/*      */ import java.awt.Cursor;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.Font;
/*      */ import java.awt.FontMetrics;
/*      */ import java.awt.Graphics;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.GridBagConstraints;
/*      */ import java.awt.GridBagLayout;
/*      */ import java.awt.Point;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.ActionListener;
/*      */ import java.awt.event.MouseAdapter;
/*      */ import java.awt.event.MouseEvent;
/*      */ import java.awt.font.FontRenderContext;
/*      */ import java.awt.font.TextLayout;
/*      */ import java.awt.print.PageFormat;
/*      */ import java.awt.print.Printable;
/*      */ import java.awt.print.PrinterException;
/*      */ import java.awt.print.PrinterJob;
/*      */ import java.io.File;
/*      */ import java.io.PrintStream;
/*      */ import java.sql.SQLException;
/*      */ import java.text.DecimalFormat;
/*      */ import java.text.DecimalFormatSymbols;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Date;
/*      */ import java.util.GregorianCalendar;
/*      */ import java.util.List;
/*      */ import javax.swing.BorderFactory;
/*      */ import javax.swing.ImageIcon;
/*      */ import javax.swing.JButton;
/*      */ import javax.swing.JCheckBox;
/*      */ import javax.swing.JFileChooser;
/*      */ import javax.swing.JLabel;
/*      */ import javax.swing.JMenuItem;
/*      */ import javax.swing.JOptionPane;
/*      */ import javax.swing.JPanel;
/*      */ import javax.swing.JPopupMenu;
/*      */ import javax.swing.JScrollPane;
/*      */ import javax.swing.JTable;
/*      */ import javax.swing.JTextField;
/*      */ import javax.swing.JToolBar;
/*      */ import javax.swing.JViewport;
/*      */ import javax.swing.ListSelectionModel;
/*      */ import javax.swing.border.CompoundBorder;
/*      */ import javax.swing.event.InternalFrameAdapter;
/*      */ import javax.swing.event.InternalFrameEvent;
/*      */ import javax.swing.event.ListSelectionEvent;
/*      */ import javax.swing.event.ListSelectionListener;
/*      */ import javax.swing.table.JTableHeader;
/*      */ import javax.swing.table.TableColumn;
/*      */ import javax.swing.table.TableColumnModel;
/*      */ 
/*      */ public class TablaVencimientos extends MarcoInternoTablas
/*      */   implements Printable, ActionListener
/*      */ {
/*      */   private static final String FECHA = "fecha";
/*      */   private static final String FACTURA = "factura";
/*      */   private static final String FFACTURA = "ffactura";
/*      */   private static final String CUENTA = "cuenta";
/*      */   private static final String CUENTAPAGO = "cuentapago";
/*      */   private static final String IMPORTE = "importe";
/*      */   private static final String PAGADO = "pagado";
/*      */   private static final String COMPLETO = "completo";
/*      */   private static final String REPETIR = "repetir";
/*      */   private static final String SELECCIONAR = "seleccionar";
/*      */   private static final String CREAR = "crear";
/*      */   private static final String BORRAR = "borrar";
/*      */   private static final String PAGAR = "pagar";
/*      */   private static final String IMPRIMIR = "imprimir";
/*      */   private static final String VISTAPREVIA = "vistaprevia";
/*      */   private static final String FUENTES = "fuentes";
/*      */   private static final String EXPORTAR = "exportar";
/*      */   private static final String UNDIA = "undia";
/*      */   private static final String UNASEMANA = "unasemana";
/*      */   private static final String UNMES = "unmes";
/*      */   private static final String CSV = "csv";
/*  129 */   private JPopupMenu menuEmergente = new JPopupMenu();
/*  130 */   private JMenuItem jMenuItem = null;
/*  131 */   private JMenuItem jMenuItem1 = null;
/*  132 */   private JMenuItem jMenuItem2 = null;
/*  133 */   private JMenuItem jMenuItem3 = null;
/*  134 */   protected int m_maxNumPage = 1;
/*  135 */   private BorderLayout borderLayout1 = new BorderLayout();
/*  136 */   private GridBagLayout gbLayout = new GridBagLayout();
/*  137 */   private JToolBar barra = new JToolBar();
/*  138 */   private JScrollPane scrollPane = new JScrollPane();
/*  139 */   private JPanel datosCuentas = new JPanel();
/*  140 */   private JButton selec = new JButton();
/*  141 */   private JButton crear = new JButton();
/*  142 */   private JButton borrar = new JButton();
/*  143 */   private JButton pagar = new JButton();
/*  144 */   private JCheckBox queFecha = new JCheckBox(Mensajes.getString("ponfechahoy"));
/*  145 */   private JLabel totalE = new JLabel(new StringBuilder().append(Mensajes.getString("total")).append(":").toString());
/*  146 */   private JTextField total = new JTextField();
/*  147 */   private TableSorter tabla_ord = new TableSorter();
/*      */   private JTable tabla;
/*  149 */   private TableColumn columna = null;
/*      */   private ManejoTablaVencimientos datos;
/*      */   private ScrollableTableModel model;
/*  152 */   private ImageIcon iconoSelec = createImageIcon("/contaes/iconos/edit.png");
/*  153 */   private ImageIcon iconoCrear = createImageIcon("/contaes/iconos/new.png");
/*  154 */   private ImageIcon iconoBorrar = createImageIcon("/contaes/iconos/delete.png");
/*  155 */   private ImageIcon iconoPagar = createImageIcon("/contaes/iconos/importe16.png");
/*      */ 
/*      */   public TablaVencimientos() {
/*  158 */     this(Mensajes.getString("menu35"));
/*      */   }
/*      */ 
/*      */   public TablaVencimientos(String title) {
/*  162 */     super(title);
/*  163 */     this.datos = new ManejoTablaVencimientos(Inicio.p.getEmpresa(), Inicio.p.getEjercicio(), pagar());
/*      */     try {
/*  165 */       initialize();
/*  166 */       this.total.setText(this.fn.format(totalImporte()));
/*      */     } catch (Exception exception) {
/*  168 */       exception.printStackTrace();
/*      */     }
/*      */   }
/*      */ 
/*      */   private void initialize() throws Exception {
/*  173 */     DecimalFormatSymbols formato = new DecimalFormatSymbols();
/*  174 */     formato.setDecimalSeparator(',');
/*  175 */     formato.setPerMill('.');
/*  176 */     this.fn = new DecimalFormat("#,###,##0.00", formato);
/*      */ 
/*  178 */     this.menuEmergente.add(getJMenuItem());
/*  179 */     this.menuEmergente.add(getJMenuItem1());
/*  180 */     this.menuEmergente.add(getJMenuItem2());
/*  181 */     this.menuEmergente.add(getJMenuItem3());
/*      */ 
/*  183 */     setFrameIcon(new ImageIcon(getClass().getResource(icono())));
/*  184 */     Rectangle parentBounds = Inicio.frame.localizacion();
/*  185 */     int ancho = parentBounds.width < 900 ? parentBounds.width : 790;
/*  186 */     int alto = parentBounds.height < 580 ? parentBounds.height : 530;
/*  187 */     setBounds(10, 50, ancho, alto);
/*  188 */     getContentPane().setLayout(this.borderLayout1);
/*  189 */     addInternalFrameListener(new InternalFrameAdapter()
/*      */     {
/*      */       public void internalFrameOpened(InternalFrameEvent arg0) {
/*  192 */         super.internalFrameOpened(arg0);
/*  193 */         TablaVencimientos.this.marcoActivado();
/*      */       }
/*      */ 
/*      */       public void internalFrameClosing(InternalFrameEvent e) {
/*  197 */         TablaVencimientos.this.datos.cerrarConexion();
/*  198 */         super.internalFrameClosing(e);
/*      */       }
/*      */ 
/*      */       public void internalFrameDeactivated(InternalFrameEvent e) {
/*  202 */         TablaVencimientos.this.marcoDesactivado();
/*      */       }
/*      */ 
/*      */       public void internalFrameActivated(InternalFrameEvent e) {
/*  206 */         TablaVencimientos.this.marcoActivado();
/*      */       }
/*      */ 
/*      */       public void internalFrameClosed(InternalFrameEvent e) {
/*  210 */         Inicio.frame.eliminarMarcoMenu(TablaVencimientos.this.getTitle());
/*      */       }
/*      */     });
/*  213 */     this.nombres_col.add("");
/*  214 */     this.nombres_col.add(Mensajes.getString("fecha"));
/*  215 */     this.nombres_col.add(Mensajes.getString("factura"));
/*  216 */     this.nombres_col.add(Mensajes.getString("fechaFactura"));
/*  217 */     this.nombres_col.add(sAcreedor());
/*  218 */     this.nombres_col.add(Mensajes.getString("importe"));
/*  219 */     this.nombres_col.add(sCuentaAbono());
/*  220 */     this.nombres_col.add(Mensajes.getString("num"));
/*  221 */     this.nombres_col.add(sPagado());
/*  222 */     this.model = new ScrollableTableModel(this.datos.resultado(), this.nombres_col);
/*  223 */     this.tabla_ord.setTableModel(this.model);
/*  224 */     this.tabla = new JTable(this.tabla_ord);
/*  225 */     this.tabla_ord.setTableHeader(this.tabla.getTableHeader());
/*  226 */     this.tabla.getTableHeader().setToolTipText(Mensajes.getString("tableHeadertt"));
/*  227 */     this.tabla.addMouseListener(new MouseAdapter()
/*      */     {
/*      */       public void mouseClicked(MouseEvent e) {
/*  230 */         if (e.getButton() == 3) {
/*  231 */           TablaVencimientos.this.tabla.changeSelection(TablaVencimientos.this.tabla.rowAtPoint(new Point(e.getX(), e.getY())), TablaVencimientos.this.tabla.columnAtPoint(new Point(e.getX(), e.getY())), false, false);
/*      */ 
/*  235 */           TablaVencimientos.this.menuEmergente.show(e.getComponent(), e.getX(), e.getY());
/*      */         }
/*      */       }
/*      */     });
/*  240 */     ListSelectionModel rowSM = this.tabla.getSelectionModel();
/*  241 */     rowSM.addListSelectionListener(new ListSelectionListener()
/*      */     {
/*      */       public void valueChanged(ListSelectionEvent arg0) {
/*  244 */         TablaVencimientos.this.vencimientosSeleccionTabla(arg0);
/*      */       }
/*      */     });
/*  247 */     this.anchoTabla = (ancho - 40);
/*  248 */     this.columna = this.tabla.getColumnModel().getColumn(0);
/*  249 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.01D));
/*  250 */     this.columna.setCellRenderer(new DerechaColorRenderer());
/*  251 */     this.columna = this.tabla.getColumnModel().getColumn(1);
/*  252 */     this.columna.setCellRenderer(new FechaColorRenderer());
/*  253 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.13D));
/*  254 */     this.columna = this.tabla.getColumnModel().getColumn(2);
/*  255 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.11D));
/*  256 */     this.columna = this.tabla.getColumnModel().getColumn(3);
/*  257 */     this.columna.setCellRenderer(new FechaColorRenderer());
/*  258 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.13D));
/*  259 */     this.columna = this.tabla.getColumnModel().getColumn(4);
/*  260 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.31D));
/*  261 */     this.columna = this.tabla.getColumnModel().getColumn(5);
/*  262 */     this.columna.setCellRenderer(new ImporteColorRenderer());
/*  263 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.12D));
/*  264 */     this.columna = this.tabla.getColumnModel().getColumn(6);
/*  265 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.11D));
/*  266 */     this.columna.setCellRenderer(new DerechaColorRenderer());
/*  267 */     this.columna = this.tabla.getColumnModel().getColumn(7);
/*  268 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.04D));
/*  269 */     this.columna.setCellRenderer(new DerechaColorRenderer());
/*  270 */     this.columna = this.tabla.getColumnModel().getColumn(8);
/*  271 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.05D));
/*  272 */     this.columna.setCellRenderer(new BooleanColorRenderer());
/*      */ 
/*  274 */     this.tabla.setDefaultRenderer(Object.class, new GeneralColorRenderer());
/*      */ 
/*  276 */     this.scrollPane.getViewport().add(this.tabla);
/*  277 */     this.scrollPane.setBorder(new CompoundBorder(BorderFactory.createLoweredBevelBorder(), BorderFactory.createMatteBorder(20, 20, 20, 20, Color.white)));
/*      */ 
/*  280 */     addBotones(this.barra);
/*  281 */     this.barra.setRollover(true);
/*      */ 
/*  283 */     this.selec.setText(Mensajes.getString("modificar"));
/*  284 */     this.selec.setIcon(this.iconoSelec);
/*  285 */     this.selec.setVerticalTextPosition(0);
/*  286 */     this.selec.setHorizontalTextPosition(2);
/*  287 */     this.selec.setActionCommand("seleccionar");
/*  288 */     this.selec.addActionListener(this);
/*  289 */     this.crear.setText(Mensajes.getString("nuevo"));
/*  290 */     this.crear.setIcon(this.iconoCrear);
/*  291 */     this.crear.setVerticalTextPosition(0);
/*  292 */     this.crear.setHorizontalTextPosition(2);
/*  293 */     this.crear.setActionCommand("crear");
/*  294 */     this.crear.addActionListener(this);
/*  295 */     this.borrar.setText(Mensajes.getString("borrar"));
/*  296 */     this.borrar.setIcon(this.iconoBorrar);
/*  297 */     this.borrar.setVerticalTextPosition(0);
/*  298 */     this.borrar.setHorizontalTextPosition(2);
/*  299 */     this.borrar.setActionCommand("borrar");
/*  300 */     this.borrar.addActionListener(this);
/*  301 */     this.pagar.setText(sPagar());
/*  302 */     this.pagar.setIcon(this.iconoPagar);
/*  303 */     this.pagar.setVerticalTextPosition(0);
/*  304 */     this.pagar.setHorizontalTextPosition(2);
/*  305 */     this.pagar.setActionCommand("pagar");
/*  306 */     this.pagar.addActionListener(this);
/*  307 */     this.datosCuentas.setBorder(BorderFactory.createEmptyBorder(5, 50, 10, 50));
/*  308 */     this.datosCuentas.setLayout(this.gbLayout);
/*  309 */     this.total.setEditable(false);
/*  310 */     this.total.setHorizontalAlignment(4);
/*  311 */     GridBagConstraints cons = new GridBagConstraints();
/*  312 */     cons.gridx = 2;
/*  313 */     cons.gridy = 0;
/*  314 */     cons.anchor = 10;
/*  315 */     cons.insets.right = 30;
/*  316 */     cons.insets.left = 30;
/*  317 */     this.gbLayout.setConstraints(this.totalE, cons);
/*  318 */     this.datosCuentas.add(this.totalE);
/*      */ 
/*  320 */     cons.gridx = 3;
/*  321 */     cons.gridwidth = 2;
/*  322 */     cons.fill = 2;
/*  323 */     cons.insets.bottom = 5;
/*  324 */     this.gbLayout.setConstraints(this.total, cons);
/*  325 */     this.datosCuentas.add(this.total);
/*      */ 
/*  327 */     cons.gridwidth = 1;
/*  328 */     cons.gridx = 0;
/*  329 */     cons.gridy = 1;
/*  330 */     cons.fill = 0;
/*  331 */     cons.insets.bottom = 0;
/*  332 */     this.gbLayout.setConstraints(this.crear, cons);
/*  333 */     this.datosCuentas.add(this.crear);
/*      */ 
/*  335 */     cons.gridx = 1;
/*  336 */     this.gbLayout.setConstraints(this.selec, cons);
/*  337 */     this.datosCuentas.add(this.selec);
/*      */ 
/*  339 */     cons.gridx = 2;
/*  340 */     this.gbLayout.setConstraints(this.borrar, cons);
/*  341 */     this.datosCuentas.add(this.borrar);
/*      */ 
/*  343 */     cons.gridx = 3;
/*  344 */     cons.insets.right = 10;
/*  345 */     this.gbLayout.setConstraints(this.pagar, cons);
/*  346 */     this.datosCuentas.add(this.pagar);
/*      */ 
/*  348 */     cons.gridx = 4;
/*  349 */     cons.insets.left = 5;
/*      */ 
/*  351 */     this.gbLayout.setConstraints(this.queFecha, cons);
/*  352 */     this.datosCuentas.add(this.queFecha);
/*      */ 
/*  354 */     getContentPane().add(this.scrollPane, "Center");
/*  355 */     getContentPane().add(this.barra, "First");
/*  356 */     getContentPane().add(this.datosCuentas, "Last");
/*  357 */     this.tabla.changeSelection(this.tabla.getRowCount() - 1, 0, true, true);
/*      */   }
/*      */ 
/*      */   protected boolean pagar()
/*      */   {
/*  366 */     return true;
/*      */   }
/*      */ 
/*      */   protected String sCuentaAbono()
/*      */   {
/*  375 */     return Mensajes.getString("cuentaAbono");
/*      */   }
/*      */ 
/*      */   protected String sAcreedor()
/*      */   {
/*  384 */     return Mensajes.getString("acreedor");
/*      */   }
/*      */ 
/*      */   protected String sPagado()
/*      */   {
/*  393 */     return Mensajes.getString("pagado");
/*      */   }
/*      */ 
/*      */   protected String sPagar()
/*      */   {
/*  402 */     return Mensajes.getString("pagar");
/*      */   }
/*      */ 
/*      */   protected String sPago()
/*      */   {
/*  411 */     return Mensajes.getString("pago");
/*      */   }
/*      */ 
/*      */   protected String icono()
/*      */   {
/*  420 */     return "/contaes/iconos/Vp18.png";
/*      */   }
/*      */ 
/*      */   protected String marca()
/*      */   {
/*  429 */     return "P";
/*      */   }
/*      */ 
/*      */   public void actionPerformed(ActionEvent e) {
/*  433 */     String cmd = e.getActionCommand();
/*      */ 
/*  436 */     if ("seleccionar".equals(cmd)) {
/*  437 */       String num = id_selec();
/*  438 */       if (!num.equals("Error"))
/*  439 */         modificaCreaVencimiento(Integer.parseInt(num));
/*      */     }
/*  441 */     else if ("crear".equals(cmd)) {
/*  442 */       modificaCreaVencimiento(-1);
/*  443 */     } else if ("borrar".equals(cmd)) {
/*  444 */       eliminarVencimiento();
/*  445 */     } else if ("pagar".equals(cmd)) {
/*  446 */       pagarVencimientos();
/*  447 */     } else if ("repetir".equals(cmd)) {
/*  448 */       if ((this.Buscar) && (!this.comienzo.equals("")))
/*  449 */         buscarDato(this.lastSearch);
/*      */     }
/*  451 */     else if ("imprimir".equals(cmd)) {
/*  452 */       imprimirTabla();
/*  453 */     } else if ("vistaprevia".equals(cmd)) {
/*  454 */       new PrintPreview(this);
/*  455 */     } else if ("fuentes".equals(cmd)) {
/*  456 */       String fuentePrevia = this.tabla.getFont().getFamily();
/*  457 */       int tamanoPrevio = this.tabla.getFont().getSize();
/*  458 */       MostrarFuentes dlg = new MostrarFuentes(Inicio.frame, Mensajes.getString("fuentes"), true, fuentePrevia, tamanoPrevio);
/*      */ 
/*  460 */       mostrarDialogo(dlg);
/*  461 */       if (dlg.isCambiar())
/*  462 */         this.tabla.setFont(new Font(dlg.getFuente(), 0, dlg.getTamano()));
/*      */     }
/*  464 */     else if ("exportar".equals(cmd)) {
/*  465 */       exportarA_archivo();
/*      */     }
/*      */     else
/*      */     {
/*      */       TablaToCsv object;
/*  466 */       if ("csv".equals(cmd)) {
/*  467 */         object = new TablaToCsv(this.model, "Vencimientos");
/*      */       } else {
/*  469 */         if ("pagado".equals(cmd)) {
/*  470 */           selPagado();
/*  471 */           this.Accion = true;
/*  472 */         } else if ("fecha".equals(cmd)) {
/*  473 */           dialogoSeleccion(true, 1);
/*  474 */           if ((this.Accion) && (this.Buscar))
/*  475 */             buscarDato(1);
/*      */           else
/*  477 */             this.datos.seleccionEstandar(this.comienzo, this.fin, 3);
/*      */         }
/*  479 */         else if ("ffactura".equals(cmd)) {
/*  480 */           dialogoSeleccion(true, 1);
/*  481 */           if ((this.Accion) && (this.Buscar))
/*  482 */             buscarDato(3);
/*      */           else
/*  484 */             this.datos.seleccionEstandar(this.comienzo, this.fin, 1);
/*      */         }
/*  486 */         else if ("cuenta".equals(cmd)) {
/*  487 */           dialogoSeleccion(true, 0);
/*  488 */           if ((this.Accion) && (this.Buscar))
/*  489 */             buscarDato(4);
/*      */           else
/*  491 */             this.datos.seleccionEstandar(this.comienzo, this.fin, 2);
/*      */         }
/*  493 */         else if ("factura".equals(cmd)) {
/*  494 */           dialogoSeleccion(false, 2);
/*  495 */           if ((this.Accion) && (this.Buscar))
/*  496 */             buscarDato(2);
/*      */           else
/*  498 */             this.datos.seleccionTexto("v.factura", this.comienzo);
/*      */         }
/*  500 */         else if ("importe".equals(cmd)) {
/*  501 */           dialogoSeleccion(true, 2);
/*  502 */           if ((this.Accion) && (this.Buscar))
/*  503 */             buscarDato(5);
/*      */           else
/*  505 */             this.datos.seleccionEstandar(this.comienzo, this.fin, 4);
/*      */         }
/*  507 */         else if ("cuentapago".equals(cmd)) {
/*  508 */           dialogoSeleccion(true, 0);
/*  509 */           if ((this.Accion) && (this.Buscar))
/*  510 */             buscarDato(6);
/*      */           else
/*  512 */             this.datos.seleccionEstandar(this.comienzo, this.fin, 5);
/*      */         }
/*  514 */         else if ("completo".equals(cmd)) {
/*  515 */           this.Accion = true;
/*  516 */           this.datos.seleccionTotal();
/*  517 */         } else if ("undia".equals(cmd)) {
/*  518 */           this.Accion = true;
/*  519 */           this.datos.seleccionFija(0);
/*  520 */         } else if ("unasemana".equals(cmd)) {
/*  521 */           this.Accion = true;
/*  522 */           this.datos.seleccionFija(1);
/*  523 */         } else if ("unmes".equals(cmd)) {
/*  524 */           this.Accion = true;
/*  525 */           this.datos.seleccionFija(2);
/*      */         }
/*  527 */         if (this.Accion) {
/*  528 */           vencimientosRenovarTabla(true);
/*      */         }
/*  530 */         this.Accion = false;
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private void pagarVencimientos()
/*      */   {
/*  540 */     if (!cuentaPago().equals("Error")) {
/*  541 */       Date fechaDePago = this.queFecha.isSelected() ? new Date() : fechaPago();
/*  542 */       PagaVencimientos dlg = new PagaVencimientos(Inicio.frame, new StringBuilder().append(Mensajes.getString("fecha")).append(" y ").append(Mensajes.getString("ctaPago")).toString(), true, cuentaPago(), fechaDePago);
/*  543 */       mostrarDialogo(dlg);
/*  544 */       int cuentaPago = dlg.enCuenta();
/*  545 */       if (cuentaPago != -1) {
/*  546 */         String fechaPago = dlg.enfecha();
/*  547 */         boolean crearA = dlg.crearA();
/*  548 */         for (int x = this.selectedRow; x <= this.lastSelectedRow; x++) {
/*  549 */           if (this.tabla.isRowSelected(x)) {
/*  550 */             String num = id_fila(x);
/*  551 */             if (!num.equals("Error")) {
/*  552 */               pagarV(num, fechaPago, cuentaPago, crearA);
/*      */             }
/*      */           }
/*      */         }
/*  556 */         vencimientosRenovarTabla(false);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private void eliminarVencimiento()
/*      */   {
/*  565 */     int borro = JOptionPane.showConfirmDialog(getContentPane(), Mensajes.getString("confBorrarVtos"), Mensajes.getString("confirma"), 0);
/*      */ 
/*  568 */     if (borro == 0) {
/*  569 */       for (int x = this.selectedRow; x <= this.lastSelectedRow; x++) {
/*  570 */         if (this.tabla.isRowSelected(x)) {
/*  571 */           String num = id_fila(x);
/*  572 */           if (!num.equals("Error")) {
/*  573 */             borrarV(num);
/*      */           }
/*      */         }
/*      */       }
/*  577 */       vencimientosRenovarTabla(true);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void addBotones(JToolBar toolBar) {
/*  582 */     JButton button = null;
/*      */ 
/*  585 */     button = makeNavigationButton("save", "exportar", Mensajes.getString("saveAstt"), Mensajes.getString("saveAs"));
/*      */ 
/*  588 */     toolBar.add(button);
/*      */ 
/*  590 */     button = makeNavigationButton("csv24", "csv", Mensajes.getString("saveAsCsvtt"), Mensajes.getString("saveAsCsv"));
/*      */ 
/*  593 */     toolBar.add(button);
/*  594 */     toolBar.addSeparator(new Dimension(5, 0));
/*      */ 
/*  597 */     button = makeNavigationButton("fonts", "fuentes", Mensajes.getString("selFonttt"), Mensajes.getString("fuentes"));
/*      */ 
/*  600 */     toolBar.add(button);
/*      */ 
/*  603 */     button = makeNavigationButton("print24", "imprimir", Mensajes.getString("printtt"), Mensajes.getString("print"));
/*      */ 
/*  606 */     toolBar.add(button);
/*      */ 
/*  608 */     button = makeNavigationButton("preview", "vistaprevia", Mensajes.getString("previewtt"), Mensajes.getString("preview"));
/*      */ 
/*  611 */     toolBar.add(button);
/*      */ 
/*  613 */     toolBar.addSeparator(new Dimension(20, 0));
/*      */ 
/*  616 */     button = makeNavigationButton("date32", "fecha", Mensajes.getString("selBuFechatt"), Mensajes.getString("fecha"));
/*      */ 
/*  619 */     toolBar.add(button);
/*  620 */     toolBar.addSeparator(new Dimension(10, 0));
/*      */ 
/*  622 */     button = makeNavigationButton("concepto", "factura", Mensajes.getString("selBuFactt"), Mensajes.getString("factura"));
/*      */ 
/*  625 */     toolBar.add(button);
/*  626 */     toolBar.addSeparator(new Dimension(10, 0));
/*      */ 
/*  628 */     button = makeNavigationButton("date32", "ffactura", new StringBuilder().append(Mensajes.getString("selBuFechatt")).append(" ").append(Mensajes.getString("factura")).toString(), new StringBuilder().append(Mensajes.getString("fecha")).append(" ").append(Mensajes.getString("factura")).toString());
/*      */ 
/*  631 */     toolBar.add(button);
/*  632 */     toolBar.addSeparator(new Dimension(10, 0));
/*      */ 
/*  634 */     button = makeNavigationButton("cuentas", "cuenta", Mensajes.getString("selBuCtastt"), Mensajes.getString("cuentas"));
/*      */ 
/*  637 */     toolBar.add(button);
/*  638 */     toolBar.addSeparator(new Dimension(10, 0));
/*      */ 
/*  640 */     button = makeNavigationButton("importe", "importe", Mensajes.getString("selBuImptt"), Mensajes.getString("importe"));
/*      */ 
/*  643 */     toolBar.add(button);
/*  644 */     toolBar.addSeparator(new Dimension(10, 0));
/*      */ 
/*  646 */     button = makeNavigationButton("cuentas", "cuentapago", new StringBuilder().append(Mensajes.getString("selBuCtastt")).append(" ").append(sPago()).toString(), new StringBuilder().append(Mensajes.getString("cuentas")).append(" ").append(sPago()).toString());
/*      */ 
/*  649 */     toolBar.add(button);
/*  650 */     toolBar.addSeparator(new Dimension(10, 0));
/*      */ 
/*  652 */     button = makeNavigationButton("bell", "pagado", Mensajes.getString("selBuPagado"), sPagado());
/*      */ 
/*  655 */     toolBar.add(button);
/*  656 */     toolBar.addSeparator(new Dimension(10, 0));
/*      */ 
/*  658 */     button = makeNavigationButton("retable", "completo", Mensajes.getString("listCompletott"), Mensajes.getString("completo"));
/*      */ 
/*  661 */     toolBar.add(button);
/*  662 */     toolBar.addSeparator(new Dimension(10, 0));
/*      */ 
/*  664 */     button = makeNavigationButton("refind", "repetir", Mensajes.getString("reFindtt"), Mensajes.getString("repetir"));
/*      */ 
/*  667 */     toolBar.add(button);
/*      */ 
/*  669 */     toolBar.addSeparator(new Dimension(20, 0));
/*      */ 
/*  671 */     button = makeNavigationButton("cal_dia", "undia", Mensajes.getString("aundia"), "");
/*      */ 
/*  673 */     toolBar.add(button);
/*      */ 
/*  676 */     button = makeNavigationButton("cal_semana", "unasemana", Mensajes.getString("aunasemana"), "");
/*      */ 
/*  678 */     toolBar.add(button);
/*      */ 
/*  681 */     button = makeNavigationButton("cal_mes", "unmes", Mensajes.getString("aunmes"), "");
/*      */ 
/*  683 */     toolBar.add(button);
/*      */   }
/*      */ 
/*      */   private JButton makeNavigationButton(String imageName, String actionCommand, String toolTipText, String altText)
/*      */   {
/*  690 */     JButton button = makeNavigationButton(imageName, toolTipText, altText);
/*  691 */     button.setActionCommand(actionCommand);
/*  692 */     button.addActionListener(this);
/*  693 */     return button;
/*      */   }
/*      */ 
/*      */   private void selPagado()
/*      */   {
/*  702 */     Object[] options = { Mensajes.getString("pagados"), Mensajes.getString("noPagados") };
/*      */ 
/*  704 */     int n = JOptionPane.showOptionDialog(getContentPane(), Mensajes.getString("dlgSelPagNoPag"), new StringBuilder().append(Mensajes.getString("pagados")).append("/").append(Mensajes.getString("noPagados")).toString(), 0, 3, null, options, options[0]);
/*      */ 
/*  712 */     if (n == 0)
/*  713 */       this.datos.seleccionaPagado(true);
/*      */     else
/*  715 */       this.datos.seleccionaPagado(false);
/*      */   }
/*      */ 
/*      */   private void buscarDato(int campo)
/*      */   {
/*  725 */     this.Accion = false;
/*  726 */     this.lastSearch = campo;
/*  727 */     String patron = this.comienzo.toUpperCase();
/*  728 */     int numFilas = this.tabla.getRowCount(); int x = 0;
/*  729 */     for (x = this.selectedRow + 1; x < numFilas; x++) {
/*  730 */       if (this.tabla.getValueAt(x, campo).toString().toUpperCase().indexOf(patron) != -1) {
/*  731 */         this.tabla.changeSelection(x, 0, false, false);
/*  732 */         break;
/*      */       }
/*      */     }
/*  735 */     if (x == numFilas) {
/*  736 */       int volver = JOptionPane.showConfirmDialog(getContentPane(), Mensajes.getString("finTabla"), Mensajes.getString("confirma"), 0);
/*      */ 
/*  739 */       if (volver == 0) {
/*  740 */         this.tabla.changeSelection(0, 0, false, false);
/*  741 */         if (this.tabla.getValueAt(0, campo).toString().toUpperCase().indexOf(patron) != -1) {
/*  742 */           return;
/*      */         }
/*  744 */         buscarDato(this.lastSearch);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private double totalImporte()
/*      */   {
/*  755 */     double total = 0.0D;
/*  756 */     int numFilas = this.tabla.getRowCount();
/*  757 */     for (int x = 0; x < numFilas; x++) {
/*  758 */       String importe = this.tabla.getValueAt(x, 5).toString();
/*  759 */       total += Double.parseDouble(importe);
/*      */     }
/*  761 */     return total;
/*      */   }
/*      */ 
/*      */   private String id_selec()
/*      */   {
/*  770 */     if (this.selectedRow != -1) {
/*  771 */       return this.tabla.getValueAt(this.selectedRow, 0).toString();
/*      */     }
/*  773 */     return "Error";
/*      */   }
/*      */ 
/*      */   private String id_fila(int fila)
/*      */   {
/*  783 */     return this.tabla.getValueAt(fila, 0).toString();
/*      */   }
/*      */ 
/*      */   private String cuentaPago()
/*      */   {
/*  792 */     if (this.selectedRow != -1) {
/*  793 */       return this.tabla.getValueAt(this.selectedRow, 6).toString();
/*      */     }
/*  795 */     return "Error";
/*      */   }
/*      */ 
/*      */   private Date fechaPago() {
/*  799 */     if (this.selectedRow != -1) {
/*  800 */       String fecha = this.tabla.getValueAt(this.selectedRow, 1).toString();
/*  801 */       int year = Integer.parseInt(fecha.substring(0, 4));
/*  802 */       int month = Integer.parseInt(fecha.substring(5, 7)) - 1;
/*  803 */       int date = Integer.parseInt(fecha.substring(8));
/*  804 */       GregorianCalendar calendario = new GregorianCalendar();
/*  805 */       calendario.set(year, month, date);
/*  806 */       return calendario.getTime();
/*      */     }
/*  808 */     return new Date();
/*      */   }
/*      */ 
/*      */   private void borrarV(String id)
/*      */   {
/*      */     try
/*      */     {
/*  818 */       int idInt = Integer.parseInt(id);
/*  819 */       ManejoVencimientos vencimientoM = new ManejoVencimientos(Inicio.getCEmpresa());
/*  820 */       vencimientoM.borrar(!pagar(), idInt);
/*      */     } catch (NumberFormatException e) {
/*  822 */       e.printStackTrace();
/*      */     }
/*      */   }
/*      */ 
/*      */   private void pagarV(String id, String fecha, int cuentaP, boolean crearAsiento)
/*      */   {
/*  835 */     if (!fecha.substring(0, 4).equals(Inicio.p.getEjercicio())) {
/*  836 */       JOptionPane.showMessageDialog(getContentPane(), Mensajes.getString("ejercicioNoCorrecto"));
/*  837 */       return;
/*      */     }
/*      */     try {
/*  840 */       int idInt = Integer.parseInt(id);
/*  841 */       ManejoVencimientos vencimientoM = new ManejoVencimientos(Inicio.getCEmpresa());
/*  842 */       boolean yaRealizado = vencimientoM.isRealizado(!pagar(), idInt);
/*  843 */       if (yaRealizado) {
/*  844 */         TipoVencimiento vcto = vencimientoM.datos(!pagar(), idInt);
/*  845 */         String mensaje = new StringBuilder().append("El vencimiento:\n").append(vcto.getFactura()).append(" ; ").append(vcto.getFecha()).append(" ; ").append(Double.toString(vcto.getImporte())).append("\n").append("está ya realizado. ¿Desea continuar?").toString();
/*      */ 
/*  848 */         int volver = JOptionPane.showConfirmDialog(getContentPane(), mensaje, Mensajes.getString("confirma"), 0);
/*      */ 
/*  851 */         if (volver == 1) {
/*  852 */           return;
/*      */         }
/*      */       }
/*  855 */       vencimientoM.realizar(!pagar(), idInt);
/*  856 */       if (crearAsiento) {
/*  857 */         String documento = vencimientoM.datos(!pagar(), idInt).getFactura();
/*  858 */         ManejoAsientos asientoM = new ManejoAsientos(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  859 */         ManejoApuntes apunteM = new ManejoApuntes(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  860 */         int numeroAs = asientoM.nuevoNumero();
/*  861 */         if (numeroAs != -1) {
/*  862 */           int idAs = asientoM.crear(numeroAs, fecha, documento, marca());
/*  863 */           if (idAs != -1) {
/*  864 */             int cuenta = vencimientoM.datos(!pagar(), idInt).getCuenta();
/*  865 */             double importe = vencimientoM.datos(!pagar(), idInt).getImporte();
/*  866 */             String concepto = new StringBuilder().append(sPago()).append(" ").append(vencimientoM.datos(!pagar(), idInt).getNum()).append(", ").append(Mensajes.getString("factura")).append(" ").append(vencimientoM.datos(!pagar(), idInt).getFactura()).toString();
/*      */ 
/*  870 */             if (concepto.length() > 30) {
/*  871 */               concepto = concepto.substring(0, 30);
/*      */             }
/*  873 */             String DH1 = pagar() ? Mensajes.getString("debeA") : Mensajes.getString("haberA");
/*  874 */             String DH2 = pagar() ? Mensajes.getString("haberA") : Mensajes.getString("debeA");
/*  875 */             apunteM.crear(idAs, cuenta, concepto, DH1, importe);
/*  876 */             apunteM.crear(idAs, cuentaP, concepto, DH2, importe);
/*  877 */             Inicio.frame.renovarTabla(0);
/*      */           }
/*      */         }
/*      */       }
/*      */     } catch (NumberFormatException e) {
/*  882 */       e.printStackTrace();
/*      */     }
/*      */   }
/*      */ 
/*      */   private void vencimientosRenovarTabla(boolean fin) {
/*  887 */     if (this.datos.conexionCerrada()) {
/*  888 */       return;
/*      */     }
/*  890 */     this.tabla.removeAll();
/*      */     try {
/*  892 */       this.model = new ScrollableTableModel(this.datos.resultado(), this.nombres_col);
/*      */     } catch (SQLException e) {
/*  894 */       e.printStackTrace();
/*      */     }
/*  896 */     this.tabla_ord.setTableModel(this.model);
/*  897 */     this.tabla.setModel(this.tabla_ord);
/*  898 */     ListSelectionModel rowSM = this.tabla.getSelectionModel();
/*  899 */     rowSM.addListSelectionListener(new ListSelectionListener()
/*      */     {
/*      */       public void valueChanged(ListSelectionEvent arg0) {
/*  902 */         TablaVencimientos.this.vencimientosSeleccionTabla(arg0);
/*      */       }
/*      */     });
/*  905 */     this.columna = this.tabla.getColumnModel().getColumn(0);
/*  906 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.01D));
/*  907 */     this.columna.setCellRenderer(new DerechaColorRenderer());
/*  908 */     this.columna = this.tabla.getColumnModel().getColumn(1);
/*  909 */     this.columna.setCellRenderer(new FechaColorRenderer());
/*  910 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.13D));
/*  911 */     this.columna = this.tabla.getColumnModel().getColumn(2);
/*  912 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.11D));
/*  913 */     this.columna = this.tabla.getColumnModel().getColumn(3);
/*  914 */     this.columna.setCellRenderer(new FechaColorRenderer());
/*  915 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.13D));
/*  916 */     this.columna = this.tabla.getColumnModel().getColumn(4);
/*  917 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.31D));
/*  918 */     this.columna = this.tabla.getColumnModel().getColumn(5);
/*  919 */     this.columna.setCellRenderer(new ImporteColorRenderer());
/*  920 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.12D));
/*  921 */     this.columna = this.tabla.getColumnModel().getColumn(6);
/*  922 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.11D));
/*  923 */     this.columna.setCellRenderer(new DerechaColorRenderer());
/*  924 */     this.columna = this.tabla.getColumnModel().getColumn(7);
/*  925 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.04D));
/*  926 */     this.columna.setCellRenderer(new DerechaColorRenderer());
/*  927 */     this.columna = this.tabla.getColumnModel().getColumn(8);
/*  928 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.05D));
/*  929 */     this.columna.setCellRenderer(new BooleanColorRenderer());
/*  930 */     this.tabla.setDefaultRenderer(Object.class, new GeneralColorRenderer());
/*  931 */     this.total.setText(this.fn.format(totalImporte()));
/*  932 */     if (fin)
/*  933 */       this.tabla.changeSelection(this.tabla.getRowCount() - 1, 0, true, true);
/*      */   }
/*      */ 
/*      */   private void vencimientosSeleccionTabla(ListSelectionEvent e)
/*      */   {
/*  938 */     SeleccionTabla(e);
/*      */   }
/*      */ 
/*      */   private void modificaCreaVencimiento(int id)
/*      */   {
/*  947 */     String titulo = new StringBuilder().append(Mensajes.getString("modificar")).append(" ").append(Mensajes.getString("vencimiento")).toString();
/*  948 */     if (id == -1) {
/*  949 */       titulo = new StringBuilder().append(Mensajes.getString("crear")).append(" ").append(Mensajes.getString("vencimiento")).toString();
/*      */     }
/*  951 */     CrearVencimientosV dlg = new CrearVencimientosV(Inicio.frame, titulo, false, id, pagar());
/*  952 */     mostrarDialogo(dlg);
/*      */   }
/*      */ 
/*      */   private JMenuItem getJMenuItem()
/*      */   {
/*  961 */     if (this.jMenuItem == null) {
/*  962 */       this.jMenuItem = new JMenuItem();
/*  963 */       this.jMenuItem.setText(Mensajes.getString("nuevo"));
/*  964 */       this.jMenuItem.setIcon(this.iconoCrear);
/*  965 */       this.jMenuItem.addActionListener(new ActionListener()
/*      */       {
/*      */         public void actionPerformed(ActionEvent e) {
/*  968 */           TablaVencimientos.this.modificaCreaVencimiento(-1);
/*      */         }
/*      */       });
/*      */     }
/*  972 */     return this.jMenuItem;
/*      */   }
/*      */ 
/*      */   private JMenuItem getJMenuItem1()
/*      */   {
/*  981 */     if (this.jMenuItem1 == null) {
/*  982 */       this.jMenuItem1 = new JMenuItem();
/*  983 */       this.jMenuItem1.setText(Mensajes.getString("modificar"));
/*  984 */       this.jMenuItem1.setIcon(this.iconoSelec);
/*  985 */       this.jMenuItem1.addActionListener(new ActionListener()
/*      */       {
/*      */         public void actionPerformed(ActionEvent e) {
/*  988 */           String num = TablaVencimientos.this.id_selec();
/*  989 */           if (!num.equals("Error")) {
/*  990 */             TablaVencimientos.this.modificaCreaVencimiento(Integer.parseInt(num));
/*      */           }
/*      */         }
/*      */       });
/*      */     }
/*  995 */     return this.jMenuItem1;
/*      */   }
/*      */ 
/*      */   private JMenuItem getJMenuItem2()
/*      */   {
/* 1004 */     if (this.jMenuItem2 == null) {
/* 1005 */       this.jMenuItem2 = new JMenuItem();
/* 1006 */       this.jMenuItem2.setText(Mensajes.getString("borrar"));
/* 1007 */       this.jMenuItem2.setIcon(this.iconoBorrar);
/* 1008 */       this.jMenuItem2.addActionListener(new ActionListener()
/*      */       {
/*      */         public void actionPerformed(ActionEvent e) {
/* 1011 */           TablaVencimientos.this.eliminarVencimiento();
/*      */         }
/*      */       });
/*      */     }
/* 1015 */     return this.jMenuItem2;
/*      */   }
/*      */ 
/*      */   private JMenuItem getJMenuItem3()
/*      */   {
/* 1024 */     if (this.jMenuItem3 == null) {
/* 1025 */       this.jMenuItem3 = new JMenuItem();
/* 1026 */       this.jMenuItem3.setText(sPagar());
/* 1027 */       this.jMenuItem3.setIcon(this.iconoPagar);
/* 1028 */       this.jMenuItem3.addActionListener(new ActionListener()
/*      */       {
/*      */         public void actionPerformed(ActionEvent e) {
/* 1031 */           TablaVencimientos.this.pagarVencimientos();
/*      */         }
/*      */       });
/*      */     }
/* 1035 */     return this.jMenuItem3;
/*      */   }
/*      */ 
/*      */   private void marcoActivado() {
/* 1039 */     if (this.datos.conexionCerrada())
/* 1040 */       this.datos.abrirConexion();
/*      */   }
/*      */ 
/*      */   private void marcoDesactivado()
/*      */   {
/*      */   }
/*      */ 
/*      */   public void renovarTabla() {
/* 1048 */     vencimientosRenovarTabla(false);
/*      */   }
/*      */ 
/*      */   public ArrayList<TipoVencimiento> getSeleccionados() {
/* 1052 */     ArrayList lista = new ArrayList();
/* 1053 */     ManejoVencimientos mV = new ManejoVencimientos(Inicio.getCEmpresa());
/* 1054 */     for (int x = this.selectedRow; x <= this.lastSelectedRow; x++) {
/* 1055 */       if (this.tabla.isRowSelected(x)) {
/* 1056 */         String id = id_fila(x);
/* 1057 */         TipoVencimiento v = mV.datos(true, Integer.parseInt(id));
/* 1058 */         if (v != null) {
/* 1059 */           v.setFecha(fechaBdToCSB(v.getFecha()));
/* 1060 */           v.setFechaf(fechaBdToCSB(v.getFechaf()));
/* 1061 */           lista.add(v);
/*      */         }
/*      */       }
/*      */     }
/* 1065 */     return lista;
/*      */   }
/*      */ 
/*      */   private String fechaBdToCSB(String fecha)
/*      */   {
/* 1070 */     String fechaCsb = new StringBuilder().append(fecha.substring(8)).append(fecha.substring(5, 7)).append(fecha.substring(2, 4)).toString();
/* 1071 */     return fechaCsb;
/*      */   }
/*      */ 
/*      */   private void exportarA_archivo() {
/* 1075 */     String EOL = FinLinea.get();
/* 1076 */     AlinearCadena alinea = new AlinearCadena();
/* 1077 */     JFileChooser fc = new JFileChooser();
/* 1078 */     fc.setSelectedFile(new File("Vencimientos.txt"));
/* 1079 */     int retorno = fc.showSaveDialog(Inicio.frame);
/* 1080 */     if (retorno == 0) {
/* 1081 */       FechaHoy hoy = new FechaHoy();
/* 1082 */       File archivo = fc.getSelectedFile();
/* 1083 */       archivo = AddExtension.txt(archivo);
/* 1084 */       GrabarFichero salida = new GrabarFichero(archivo);
/* 1085 */       salida.insertar(new StringBuilder().append(Mensajes.getString("listVenci")).append("      ").append(hoy.getFecha()).append(EOL).toString());
/*      */ 
/* 1087 */       int filas = this.tabla.getRowCount(); int columnas = this.tabla.getColumnCount();
/* 1088 */       int[] x = new int[columnas];
/* 1089 */       String cadena = "";
/* 1090 */       for (int nCol = 0; nCol < columnas; nCol++) {
/* 1091 */         boolean izq = true;
/* 1092 */         this.columna = this.tabla.getColumnModel().getColumn(nCol);
/* 1093 */         String titulo = (String)this.columna.getIdentifier();
/* 1094 */         if (titulo.equals("")) {
/* 1095 */           x[nCol] = 0;
/* 1096 */         } else if (titulo.equals(Mensajes.getString("fecha"))) {
/* 1097 */           x[nCol] = 11;
/* 1098 */         } else if (titulo.equals(Mensajes.getString("factura"))) {
/* 1099 */           x[nCol] = 15;
/* 1100 */         } else if (titulo.equals(Mensajes.getString("fechaFactura"))) {
/* 1101 */           x[nCol] = 11;
/* 1102 */         } else if (titulo.equals(sAcreedor())) {
/* 1103 */           x[nCol] = 35;
/* 1104 */         } else if (titulo.equals(Mensajes.getString("importe"))) {
/* 1105 */           x[nCol] = 12;
/* 1106 */           izq = false;
/* 1107 */         } else if (titulo.equals(sCuentaAbono())) {
/* 1108 */           x[nCol] = 10;
/* 1109 */         } else if (titulo.equals(Mensajes.getString("num"))) {
/* 1110 */           x[nCol] = 4;
/* 1111 */         } else if (titulo.equals(sPagado())) {
/* 1112 */           x[nCol] = 3;
/*      */         }
/* 1114 */         if (izq)
/* 1115 */           cadena = new StringBuilder().append(cadena).append(alinea.Izquierda(titulo, x[nCol])).toString();
/*      */         else {
/* 1117 */           cadena = new StringBuilder().append(cadena).append(alinea.Derecha(titulo, x[nCol])).toString();
/*      */         }
/*      */       }
/* 1120 */       salida.insertar(new StringBuilder().append(cadena).append(EOL).toString());
/* 1121 */       cadena = "";
/* 1122 */       for (int nCol = 0; nCol < columnas; nCol++) {
/* 1123 */         for (int nCarac = 0; nCarac < x[nCol]; nCarac++) {
/* 1124 */           cadena = new StringBuilder().append(cadena).append("-").toString();
/*      */         }
/*      */       }
/* 1127 */       salida.insertar(new StringBuilder().append(cadena).append(EOL).toString());
/*      */ 
/* 1129 */       for (int nRow = 0; nRow < filas; nRow++) {
/* 1130 */         cadena = "";
/* 1131 */         for (int nCol = 0; nCol < columnas; nCol++) {
/* 1132 */           int col = this.tabla.getColumnModel().getColumn(nCol).getModelIndex();
/* 1133 */           Object obj = this.tabla_ord.getValueAt(nRow, col);
/* 1134 */           if ((obj instanceof Double)) {
/* 1135 */             String str = this.fn.format(obj);
/* 1136 */             cadena = new StringBuilder().append(cadena).append(alinea.Derecha(str, x[nCol])).toString();
/* 1137 */           } else if ((obj instanceof Date)) {
/* 1138 */             String cad = obj.toString();
/* 1139 */             int year = Integer.parseInt(cad.substring(0, 4));
/* 1140 */             int month = Integer.parseInt(cad.substring(5, 7)) - 1;
/* 1141 */             int date = Integer.parseInt(cad.substring(8));
/* 1142 */             GregorianCalendar calendario = new GregorianCalendar();
/* 1143 */             calendario.set(year, month, date);
/* 1144 */             SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
/* 1145 */             String str = sdf.format(calendario.getTime());
/* 1146 */             cadena = new StringBuilder().append(cadena).append(alinea.Izquierda(str, x[nCol])).toString();
/*      */           } else {
/* 1148 */             String str = obj.toString();
/* 1149 */             if (str.equals("false")) {
/* 1150 */               str = new StringBuilder().append(" ").append(Mensajes.getString("no")).toString();
/*      */             }
/* 1152 */             if (str.equals("true")) {
/* 1153 */               str = new StringBuilder().append(" ").append(Mensajes.getString("yes")).toString();
/*      */             }
/* 1155 */             cadena = new StringBuilder().append(cadena).append(alinea.Izquierda(str, x[nCol])).toString();
/*      */           }
/*      */         }
/* 1158 */         salida.insertar(new StringBuilder().append(cadena).append(EOL).toString());
/*      */       }
/* 1160 */       salida.insertar(EOL);
/* 1161 */       salida.insertar(alinea.Derecha(new StringBuilder().append("Total:   ").append(this.fn.format(totalImporte())).toString(), 82));
/* 1162 */       salida.cerrar();
/*      */     }
/*      */   }
/*      */ 
/*      */   private void imprimirTabla() {
/* 1167 */     PrinterJob printerJob = PrinterJob.getPrinterJob();
/* 1168 */     PageFormat userFormat = printerJob.pageDialog(printerJob.defaultPage());
/* 1169 */     printerJob.setPrintable(this, userFormat);
/* 1170 */     if (printerJob.printDialog()) {
/* 1171 */       this.m_maxNumPage = 1;
/* 1172 */       setCursor(Cursor.getPredefinedCursor(3));
/*      */       try
/*      */       {
/* 1175 */         printerJob.print();
/*      */       } catch (PrinterException e) {
/* 1177 */         System.err.println(new StringBuilder().append(Mensajes.getString("errPrint")).append(": ").append(e).toString());
/*      */       }
/* 1179 */       setCursor(Cursor.getPredefinedCursor(0));
/*      */     }
/*      */   }
/*      */ 
/*      */   public int print(Graphics pg, PageFormat pageFormat, int pageIndex)
/*      */     throws PrinterException
/*      */   {
/* 1186 */     if (pageIndex >= this.m_maxNumPage) {
/* 1187 */       return 1;
/*      */     }
/*      */ 
/* 1190 */     FechaHoy hoy = new FechaHoy();
/* 1191 */     int anchoImporte = 0;
/*      */ 
/* 1193 */     pg.translate((int)pageFormat.getImageableX(), (int)pageFormat.getImageableY());
/*      */ 
/* 1195 */     int wPage = 0;
/* 1196 */     int hPage = 0;
/* 1197 */     if (pageFormat.getOrientation() == 1) {
/* 1198 */       wPage = (int)pageFormat.getImageableWidth();
/* 1199 */       hPage = (int)pageFormat.getImageableHeight();
/*      */     } else {
/* 1201 */       wPage = (int)pageFormat.getImageableWidth();
/* 1202 */       wPage += wPage / 2;
/* 1203 */       hPage = (int)pageFormat.getImageableHeight();
/* 1204 */       pg.setClip(0, 0, wPage, hPage);
/*      */     }
/* 1206 */     int y = 0;
/*      */ 
/* 1208 */     pg.setFont(this.tabla.getFont().deriveFont(1, this.tabla.getFont().getSize2D() + 2.0F));
/* 1209 */     pg.setColor(Color.black);
/*      */ 
/* 1211 */     FontMetrics fm = pg.getFontMetrics();
/* 1212 */     y += fm.getAscent();
/* 1213 */     pg.drawString(new StringBuilder().append(getTitle()).append("     ").append(hoy.getFecha()).toString(), 0, y);
/* 1214 */     y += 20;
/* 1215 */     Font headerFont = this.tabla.getFont().deriveFont(1);
/*      */ 
/* 1217 */     pg.setFont(headerFont);
/* 1218 */     fm = pg.getFontMetrics();
/*      */ 
/* 1220 */     TableColumnModel colModel = this.tabla.getColumnModel();
/* 1221 */     int nColumns = colModel.getColumnCount();
/* 1222 */     int[] x = new int[nColumns];
/* 1223 */     x[0] = 0;
/*      */ 
/* 1225 */     int h = fm.getAscent();
/* 1226 */     y += h;
/*      */ 
/* 1230 */     for (int nCol = 0; nCol < nColumns; nCol++) {
/* 1231 */       TableColumn tk = colModel.getColumn(nCol);
/* 1232 */       int width = tk.getWidth();
/* 1233 */       if (x[nCol] + width > wPage) {
/* 1234 */         nColumns = nCol;
/* 1235 */         break;
/*      */       }
/* 1237 */       if (nCol + 1 < nColumns) {
/* 1238 */         x[(nCol + 1)] = (x[nCol] + width);
/*      */       }
/* 1240 */       String title = (String)tk.getIdentifier();
/* 1241 */       if (title.equals(Mensajes.getString("importe"))) {
/* 1242 */         anchoImporte = tk.getWidth() * 80 / 100;
/*      */       }
/* 1244 */       pg.drawString(title, x[nCol], y);
/*      */     }
/*      */ 
/* 1247 */     pg.setFont(this.tabla.getFont());
/* 1248 */     fm = pg.getFontMetrics();
/*      */ 
/* 1250 */     int header = y;
/* 1251 */     h = fm.getHeight();
/* 1252 */     int rowH = Math.max((int)(h * 1.5D), 10);
/* 1253 */     int rowPerPage = (hPage - header) / rowH;
/* 1254 */     this.m_maxNumPage = Math.max((int)Math.ceil(this.tabla.getRowCount() / rowPerPage), 1);
/*      */ 
/* 1258 */     int iniRow = pageIndex * rowPerPage;
/* 1259 */     int endRow = Math.min(this.tabla.getRowCount(), iniRow + rowPerPage);
/*      */ 
/* 1262 */     for (int nRow = iniRow; nRow < endRow; nRow++) {
/* 1263 */       y += h;
/* 1264 */       for (int nCol = 0; nCol < nColumns; nCol++) {
/* 1265 */         pg.setColor(Color.black);
/* 1266 */         int col = this.tabla.getColumnModel().getColumn(nCol).getModelIndex();
/* 1267 */         Object obj = this.tabla_ord.getValueAt(nRow, col);
/* 1268 */         String str = "";
/* 1269 */         if (obj != null) {
/* 1270 */           str = obj.toString();
/*      */         }
/* 1272 */         if (str.equals("false")) {
/* 1273 */           str = Mensajes.getString("no");
/*      */         }
/* 1275 */         if (str.equals("true")) {
/* 1276 */           str = Mensajes.getString("yes");
/*      */         }
/* 1278 */         if ((obj instanceof Double)) {
/* 1279 */           str = this.fn.format(obj);
/*      */ 
/* 1281 */           FontRenderContext frc = ((Graphics2D)pg).getFontRenderContext();
/* 1282 */           TextLayout layout = new TextLayout(str, this.tabla.getFont(), frc);
/* 1283 */           float w = layout.getAdvance();
/* 1284 */           pg.drawString(str, x[nCol] + (anchoImporte - (int)w), y);
/* 1285 */         } else if ((obj instanceof Date)) {
/* 1286 */           String cad = obj.toString();
/* 1287 */           int year = Integer.parseInt(cad.substring(0, 4));
/* 1288 */           int month = Integer.parseInt(cad.substring(5, 7)) - 1;
/* 1289 */           int date = Integer.parseInt(cad.substring(8));
/* 1290 */           GregorianCalendar calendario = new GregorianCalendar();
/* 1291 */           calendario.set(year, month, date);
/* 1292 */           SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
/* 1293 */           str = sdf.format(calendario.getTime());
/* 1294 */           pg.drawString(str, x[nCol], y);
/*      */         } else {
/* 1296 */           pg.drawString(str, x[nCol], y);
/*      */         }
/*      */       }
/*      */     }
/* 1300 */     if (pageIndex == this.m_maxNumPage - 1) {
/* 1301 */       y += 20;
/* 1302 */       pg.drawString(new StringBuilder().append(Mensajes.getString("total")).append(":").toString(), 250, y);
/* 1303 */       pg.drawString(this.total.getText(), 310, y);
/*      */     }
/* 1305 */     System.gc();
/* 1306 */     return 0;
/*      */   }
/*      */ 
/*      */   public void cerrarConexion()
/*      */   {
/* 1311 */     if (!this.datos.conexionCerrada())
/* 1312 */       this.datos.cerrarConexion();
/*      */   }
/*      */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.TablaVencimientos
 * JD-Core Version:    0.6.2
 */