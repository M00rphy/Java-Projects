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
/*      */ import contaes.dialogosAuxiliares.GenerarVencimientos;
/*      */ import contaes.dialogosAuxiliares.GetInvoiceNumber;
/*      */ import contaes.dialogosAuxiliares.MostrarFuentes;
/*      */ import contaes.dialogosAuxiliares.PagaVencimientos;
/*      */ import contaes.dialogosAuxiliares.PrintPreview;
/*      */ import contaes.manejoDatos.ManejoApuntes;
/*      */ import contaes.manejoDatos.ManejoAsientos;
/*      */ import contaes.manejoDatos.ManejoFacturas;
/*      */ import contaes.manejoDatos.ManejoSubcuentas;
/*      */ import contaes.manejoDatos.ManejoTablaFacturasRecibidas;
/*      */ import contaes.manejoDatos.TipoFactura;
/*      */ import contaes.manejoDatos.TipoSubcuenta;
/*      */ import contaes.manejoDatos.auxiliar.AddExtension;
/*      */ import contaes.manejoDatos.auxiliar.FechaHoy;
/*      */ import contaes.manejoDatos.auxiliar.FinLinea;
/*      */ import contaes.manejoDatos.auxiliar.GrabarFichero;
/*      */ import facturacion.control.AlmacenControl;
/*      */ import facturacion.control.FacturaControl;
/*      */ import facturacion.control.LineaFacturaControl;
/*      */ import facturacion.model.Factura;
/*      */ import facturacion.model.LineaFactura;
/*      */ import facturacion.model.Producto;
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
/*      */ import java.awt.Toolkit;
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
/*      */ import java.math.BigDecimal;
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
/*      */ public class TablaFacturasRecibidas extends MarcoInternoTablas
/*      */   implements Printable, ActionListener
/*      */ {
/*      */   private static final String FACTURA = "factura";
/*      */   private static final String FECHA = "fecha";
/*      */   private static final String CUENTA = "cuenta";
/*      */   private static final String ASIENTO = "asiento";
/*      */   private static final String BASE = "base";
/*      */   private static final String IVA = "iva";
/*      */   private static final String TOTAL = "total";
/*      */   private static final String IMPORTACION = "importacion";
/*      */   private static final String COMPLETO = "completo";
/*      */   private static final String REPETIR = "repetir";
/*      */   private static final String CREAR = "crear";
/*      */   private static final String BORRAR = "borrar";
/*      */   private static final String PAGAR = "pagar";
/*      */   private static final String MODIFICAR = "modificar";
/*      */   private static final String IMPRIMIR = "imprimir";
/*      */   private static final String VISTAPREVIA = "vistaprevia";
/*      */   private static final String FUENTES = "fuentes";
/*      */   private static final String EXPORTAR = "exportar";
/*      */   private static final String CSV = "csv";
/*  139 */   private JPopupMenu menuEmergente = new JPopupMenu();
/*  140 */   private JMenuItem jMenuItem = null;
/*  141 */   private JMenuItem jMenuItem1 = null;
/*  142 */   private JMenuItem jMenuItem2 = null;
/*  143 */   private JMenuItem jMenuItem3 = null;
/*      */ 
/*  145 */   private BorderLayout borderLayout1 = new BorderLayout();
/*  146 */   private GridBagLayout gbLayout = new GridBagLayout();
/*  147 */   private JToolBar barra = new JToolBar();
/*  148 */   private JScrollPane scrollPane = new JScrollPane();
/*  149 */   private JPanel datosCuentas = new JPanel();
/*  150 */   private JButton crearVencimientos = new JButton();
/*  151 */   private JButton borrar = new JButton();
/*  152 */   private JButton pagar = new JButton();
/*  153 */   private JButton modificar = new JButton();
/*  154 */   private JCheckBox queFecha = new JCheckBox(Mensajes.getString("ponfechahoy"));
/*  155 */   private JLabel totalE = new JLabel(Mensajes.getString("total") + ":");
/*  156 */   private JTextField total = new JTextField();
/*  157 */   private TableSorter tabla_ord = new TableSorter();
/*      */   private JTable tabla;
/*  159 */   private TableColumn columna = null;
/*      */   private ManejoTablaFacturasRecibidas datos;
/*      */   private ScrollableTableModel model;
/*  162 */   private JLabel infoCuenta = new JLabel();
/*  163 */   private ImageIcon iconoCrear = createImageIcon("/contaes/iconos/date.png");
/*  164 */   private ImageIcon iconoBorrar = createImageIcon("/contaes/iconos/delete.png");
/*  165 */   private ImageIcon iconoPagar = createImageIcon("/contaes/iconos/importe16.png");
/*  166 */   private ImageIcon iconoModificar = createImageIcon("/contaes/iconos/edit.png");
/*      */ 
/*      */   public TablaFacturasRecibidas() {
/*  169 */     this(Mensajes.getString("menu31b2"));
/*      */   }
/*      */ 
/*      */   public TablaFacturasRecibidas(String title) {
/*  173 */     super(title);
/*  174 */     this.datos = new ManejoTablaFacturasRecibidas(Inicio.p.getEmpresa(), Inicio.p.getEjercicio());
/*      */     try {
/*  176 */       initialize();
/*  177 */       this.total.setText(this.fn.format(totalImporte()));
/*      */     } catch (Exception exception) {
/*  179 */       exception.printStackTrace();
/*      */     }
/*      */   }
/*      */ 
/*      */   private void initialize() throws Exception {
/*  184 */     DecimalFormatSymbols formato = new DecimalFormatSymbols();
/*  185 */     formato.setDecimalSeparator(',');
/*  186 */     formato.setPerMill('.');
/*  187 */     this.fn = new DecimalFormat("#,###,##0.00", formato);
/*      */ 
/*  189 */     this.menuEmergente.add(getJMenuItem3());
/*  190 */     this.menuEmergente.add(getJMenuItem());
/*  191 */     this.menuEmergente.add(getJMenuItem1());
/*  192 */     this.menuEmergente.add(getJMenuItem2());
/*      */ 
/*  194 */     setFrameIcon(new ImageIcon(getClass().getResource(icono())));
/*  195 */     Rectangle parentBounds = Inicio.frame.localizacion();
/*  196 */     int ancho = parentBounds.width < 900 ? parentBounds.width : 790;
/*  197 */     int alto = parentBounds.height < 580 ? parentBounds.height : 530;
/*  198 */     setBounds(10, 50, ancho, alto);
/*  199 */     getContentPane().setLayout(this.borderLayout1);
/*  200 */     addInternalFrameListener(new InternalFrameAdapter()
/*      */     {
/*      */       public void internalFrameOpened(InternalFrameEvent arg0)
/*      */       {
/*  204 */         super.internalFrameOpened(arg0);
/*  205 */         TablaFacturasRecibidas.this.marcoActivado();
/*      */       }
/*      */ 
/*      */       public void internalFrameClosing(InternalFrameEvent e)
/*      */       {
/*  210 */         TablaFacturasRecibidas.this.datos.cerrarConexion();
/*  211 */         super.internalFrameClosing(e);
/*      */       }
/*      */ 
/*      */       public void internalFrameDeactivated(InternalFrameEvent e)
/*      */       {
/*  216 */         TablaFacturasRecibidas.this.marcoDesactivado();
/*      */       }
/*      */ 
/*      */       public void internalFrameActivated(InternalFrameEvent e)
/*      */       {
/*  221 */         TablaFacturasRecibidas.this.marcoActivado();
/*      */       }
/*      */ 
/*      */       public void internalFrameClosed(InternalFrameEvent e)
/*      */       {
/*  226 */         Inicio.frame.eliminarMarcoMenu(TablaFacturasRecibidas.this.getTitle());
/*      */       }
/*      */     });
/*  230 */     this.nombres_col.add(Mensajes.getString("numero"));
/*  231 */     this.nombres_col.add(Mensajes.getString("fecha"));
/*  232 */     this.nombres_col.add(Mensajes.getString("cuenta"));
/*  233 */     this.nombres_col.add(Mensajes.getString("asiento"));
/*  234 */     this.nombres_col.add(Mensajes.getString("concepto"));
/*  235 */     this.nombres_col.add(Mensajes.getString("base"));
/*  236 */     this.nombres_col.add(Mensajes.getString("IVAs"));
/*  237 */     this.nombres_col.add(Mensajes.getString("total"));
/*  238 */     this.nombres_col.add("Exterior");
/*  239 */     this.model = new ScrollableTableModel(this.datos.resultado(), this.nombres_col);
/*  240 */     this.tabla_ord.setTableModel(this.model);
/*  241 */     this.tabla = new JTable(this.tabla_ord);
/*  242 */     this.tabla_ord.setTableHeader(this.tabla.getTableHeader());
/*  243 */     this.tabla.getTableHeader().setToolTipText(Mensajes.getString("tableHeadertt"));
/*  244 */     this.tabla.addMouseListener(new MouseAdapter()
/*      */     {
/*      */       public void mouseClicked(MouseEvent e)
/*      */       {
/*  248 */         if (e.getButton() == 3) {
/*  249 */           TablaFacturasRecibidas.this.tabla.changeSelection(TablaFacturasRecibidas.this.tabla.rowAtPoint(new Point(e.getX(), e.getY())), TablaFacturasRecibidas.this.tabla.columnAtPoint(new Point(e.getX(), e.getY())), false, false);
/*      */ 
/*  253 */           TablaFacturasRecibidas.this.menuEmergente.show(e.getComponent(), e.getX(), e.getY());
/*      */         }
/*      */       }
/*      */     });
/*  258 */     ListSelectionModel rowSM = this.tabla.getSelectionModel();
/*  259 */     rowSM.addListSelectionListener(new ListSelectionListener()
/*      */     {
/*      */       public void valueChanged(ListSelectionEvent arg0) {
/*  262 */         TablaFacturasRecibidas.this.facturasSeleccionTabla(arg0);
/*      */       }
/*      */     });
/*  265 */     this.columna = this.tabla.getColumnModel().getColumn(0);
/*  266 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.11D));
/*  267 */     this.columna.setCellRenderer(new DerechaColorRenderer());
/*  268 */     this.columna = this.tabla.getColumnModel().getColumn(1);
/*  269 */     this.columna.setCellRenderer(new FechaColorRenderer());
/*  270 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.11D));
/*  271 */     this.columna = this.tabla.getColumnModel().getColumn(2);
/*  272 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.11D));
/*  273 */     this.columna.setCellRenderer(new DerechaColorRenderer());
/*  274 */     this.columna = this.tabla.getColumnModel().getColumn(3);
/*      */ 
/*  276 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.08D));
/*  277 */     this.columna.setCellRenderer(new DerechaColorRenderer());
/*  278 */     this.columna = this.tabla.getColumnModel().getColumn(4);
/*  279 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.2D));
/*  280 */     this.columna = this.tabla.getColumnModel().getColumn(5);
/*  281 */     this.columna.setCellRenderer(new ImporteColorRenderer());
/*  282 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.11D));
/*  283 */     this.columna = this.tabla.getColumnModel().getColumn(6);
/*  284 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.11D));
/*  285 */     this.columna.setCellRenderer(new ImporteColorRenderer());
/*  286 */     this.columna = this.tabla.getColumnModel().getColumn(7);
/*  287 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.12D));
/*  288 */     this.columna.setCellRenderer(new ImporteColorRenderer());
/*  289 */     this.columna = this.tabla.getColumnModel().getColumn(8);
/*  290 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.05D));
/*  291 */     this.columna.setCellRenderer(new BooleanColorRenderer());
/*  292 */     this.tabla.setDefaultRenderer(Object.class, new GeneralColorRenderer());
/*      */ 
/*  294 */     this.scrollPane.getViewport().add(this.tabla);
/*  295 */     this.scrollPane.setBorder(new CompoundBorder(BorderFactory.createLoweredBevelBorder(), BorderFactory.createMatteBorder(20, 20, 20, 20, Color.white)));
/*      */ 
/*  298 */     addBotones(this.barra);
/*  299 */     this.barra.setRollover(true);
/*      */ 
/*  301 */     this.crearVencimientos.setText(Mensajes.getString("vencimientos"));
/*  302 */     this.crearVencimientos.setIcon(this.iconoCrear);
/*  303 */     this.crearVencimientos.setVerticalTextPosition(0);
/*  304 */     this.crearVencimientos.setHorizontalTextPosition(2);
/*  305 */     this.crearVencimientos.setActionCommand("crear");
/*  306 */     this.crearVencimientos.addActionListener(this);
/*  307 */     this.borrar.setText(Mensajes.getString("borrar"));
/*  308 */     this.borrar.setIcon(this.iconoBorrar);
/*  309 */     this.borrar.setVerticalTextPosition(0);
/*  310 */     this.borrar.setHorizontalTextPosition(2);
/*  311 */     this.borrar.setActionCommand("borrar");
/*  312 */     this.borrar.addActionListener(this);
/*  313 */     this.pagar.setText(sPagar());
/*  314 */     this.pagar.setIcon(this.iconoPagar);
/*  315 */     this.pagar.setVerticalTextPosition(0);
/*  316 */     this.pagar.setHorizontalTextPosition(2);
/*  317 */     this.pagar.setActionCommand("pagar");
/*  318 */     this.pagar.addActionListener(this);
/*  319 */     this.modificar.setText(Mensajes.getString("modificar"));
/*  320 */     this.modificar.setIcon(this.iconoModificar);
/*  321 */     this.modificar.setVerticalTextPosition(0);
/*  322 */     this.modificar.setHorizontalTextPosition(2);
/*  323 */     this.modificar.setActionCommand("modificar");
/*  324 */     this.modificar.addActionListener(this);
/*  325 */     this.datosCuentas.setBorder(BorderFactory.createEmptyBorder(5, 50, 10, 50));
/*  326 */     this.datosCuentas.setLayout(this.gbLayout);
/*  327 */     this.total.setEditable(false);
/*  328 */     this.total.setHorizontalAlignment(4);
/*  329 */     GridBagConstraints cons = new GridBagConstraints();
/*  330 */     cons.gridy = 0;
/*  331 */     cons.gridx = 0;
/*  332 */     cons.gridwidth = 2;
/*  333 */     this.gbLayout.setConstraints(this.infoCuenta, cons);
/*  334 */     this.datosCuentas.add(this.infoCuenta);
/*      */ 
/*  336 */     cons.gridwidth = 1;
/*  337 */     cons.gridx = 2;
/*  338 */     cons.anchor = 10;
/*  339 */     cons.insets.right = 30;
/*  340 */     cons.insets.left = 30;
/*  341 */     this.gbLayout.setConstraints(this.totalE, cons);
/*  342 */     this.datosCuentas.add(this.totalE);
/*      */ 
/*  344 */     cons.gridx = 3;
/*  345 */     cons.gridwidth = 2;
/*  346 */     cons.fill = 2;
/*  347 */     cons.insets.bottom = 5;
/*  348 */     this.gbLayout.setConstraints(this.total, cons);
/*  349 */     this.datosCuentas.add(this.total);
/*      */ 
/*  351 */     cons.gridwidth = 1;
/*  352 */     cons.gridx = 0;
/*  353 */     cons.gridy = 1;
/*  354 */     cons.fill = 0;
/*  355 */     cons.insets.bottom = 0;
/*  356 */     cons.insets.right = 20;
/*  357 */     cons.insets.left = 20;
/*  358 */     this.gbLayout.setConstraints(this.modificar, cons);
/*  359 */     this.datosCuentas.add(this.modificar);
/*      */ 
/*  361 */     cons.gridx = 1;
/*  362 */     this.gbLayout.setConstraints(this.borrar, cons);
/*  363 */     this.datosCuentas.add(this.borrar);
/*      */ 
/*  365 */     cons.gridx = 2;
/*  366 */     this.gbLayout.setConstraints(this.crearVencimientos, cons);
/*  367 */     this.datosCuentas.add(this.crearVencimientos);
/*      */ 
/*  369 */     cons.gridx = 3;
/*  370 */     cons.insets.right = 10;
/*  371 */     this.gbLayout.setConstraints(this.pagar, cons);
/*  372 */     this.datosCuentas.add(this.pagar);
/*      */ 
/*  374 */     cons.gridx = 4;
/*  375 */     cons.insets.left = 5;
/*      */ 
/*  377 */     this.gbLayout.setConstraints(this.queFecha, cons);
/*  378 */     this.datosCuentas.add(this.queFecha);
/*      */ 
/*  380 */     getContentPane().add(this.scrollPane, "Center");
/*  381 */     getContentPane().add(this.barra, "First");
/*  382 */     getContentPane().add(this.datosCuentas, "Last");
/*  383 */     this.tabla.changeSelection(this.tabla.getRowCount() - 1, 0, true, true);
/*      */   }
/*      */ 
/*      */   protected boolean esEmitida()
/*      */   {
/*  392 */     return false;
/*      */   }
/*      */ 
/*      */   protected String sCuentaAbono()
/*      */   {
/*  401 */     return Mensajes.getString("cuentaAbono");
/*      */   }
/*      */ 
/*      */   protected String sAcreedor()
/*      */   {
/*  410 */     return Mensajes.getString("Acreedor");
/*      */   }
/*      */ 
/*      */   protected String sPagado()
/*      */   {
/*  419 */     return Mensajes.getString("pagado");
/*      */   }
/*      */ 
/*      */   protected String sPagar()
/*      */   {
/*  428 */     return Mensajes.getString("pagar");
/*      */   }
/*      */ 
/*      */   protected String sPago()
/*      */   {
/*  437 */     return Mensajes.getString("pago");
/*      */   }
/*      */ 
/*      */   protected String icono()
/*      */   {
/*  446 */     return "/contaes/iconos/gestionRecibidas18.png";
/*      */   }
/*      */ 
/*      */   protected String marca()
/*      */   {
/*  455 */     return "P";
/*      */   }
/*      */ 
/*      */   public void actionPerformed(ActionEvent e) {
/*  459 */     String cmd = e.getActionCommand();
/*      */ 
/*  462 */     if ("crear".equals(cmd)) {
/*  463 */       CrearVencimiento();
/*  464 */     } else if ("borrar".equals(cmd)) {
/*  465 */       eliminarFactura();
/*  466 */     } else if ("pagar".equals(cmd)) {
/*  467 */       pagarCobrarFactura();
/*  468 */     } else if ("modificar".equals(cmd)) {
/*  469 */       modificarFactura();
/*  470 */     } else if ("repetir".equals(cmd)) {
/*  471 */       if ((this.Buscar) && (!this.comienzo.equals("")))
/*  472 */         buscarDato(this.lastSearch);
/*      */     }
/*  474 */     else if ("imprimir".equals(cmd)) {
/*  475 */       imprimirTabla();
/*  476 */     } else if ("vistaprevia".equals(cmd)) {
/*  477 */       new PrintPreview(this);
/*  478 */     } else if ("fuentes".equals(cmd)) {
/*  479 */       String fuentePrevia = this.tabla.getFont().getFamily();
/*  480 */       int tamanoPrevio = this.tabla.getFont().getSize();
/*  481 */       MostrarFuentes dlg = new MostrarFuentes(Inicio.frame, Mensajes.getString("fuentes"), true, fuentePrevia, tamanoPrevio);
/*      */ 
/*  483 */       mostrarDialogo(dlg);
/*  484 */       if (dlg.isCambiar())
/*  485 */         this.tabla.setFont(new Font(dlg.getFuente(), 0, dlg.getTamano()));
/*      */     }
/*  487 */     else if ("exportar".equals(cmd)) {
/*  488 */       exportarA_archivo();
/*      */     }
/*      */     else
/*      */     {
/*      */       TablaToCsv object;
/*  489 */       if ("csv".equals(cmd)) {
/*  490 */         object = new TablaToCsv(this.model, "Facturas recibidas");
/*      */       }
/*      */       else
/*      */       {
/*  497 */         if ("factura".equals(cmd)) {
/*  498 */           dialogoSeleccion(false, 2);
/*  499 */           if ((this.Accion) && (this.Buscar))
/*  500 */             buscarDato(0);
/*      */           else
/*  502 */             this.datos.seleccionTexto("f.numero", this.comienzo);
/*      */         }
/*  504 */         else if ("fecha".equals(cmd)) {
/*  505 */           dialogoSeleccion(true, 1);
/*  506 */           if ((this.Accion) && (this.Buscar))
/*  507 */             buscarDato(1);
/*      */           else
/*  509 */             this.datos.seleccionEstandar(this.comienzo, this.fin, 3);
/*      */         }
/*  511 */         else if ("cuenta".equals(cmd)) {
/*  512 */           dialogoSeleccion(true, 0);
/*  513 */           if ((this.Accion) && (this.Buscar))
/*  514 */             buscarDato(2);
/*      */           else
/*  516 */             this.datos.seleccionEstandar(this.comienzo, this.fin, 2);
/*      */         }
/*  518 */         else if ("asiento".equals(cmd)) {
/*  519 */           dialogoSeleccion(true, 2);
/*  520 */           if ((this.Accion) && (this.Buscar))
/*  521 */             buscarDato(3);
/*      */           else
/*  523 */             this.datos.seleccionEstandar(this.comienzo, this.fin, 1);
/*      */         }
/*  525 */         else if ("base".equals(cmd)) {
/*  526 */           dialogoSeleccion(true, 2);
/*  527 */           if ((this.Accion) && (this.Buscar))
/*  528 */             buscarDato(5);
/*      */           else
/*  530 */             this.datos.seleccionEstandar(this.comienzo, this.fin, 4);
/*      */         }
/*  532 */         else if ("iva".equals(cmd)) {
/*  533 */           dialogoSeleccion(true, 2);
/*  534 */           if ((this.Accion) && (this.Buscar))
/*  535 */             buscarDato(6);
/*      */           else
/*  537 */             this.datos.seleccionEstandar(this.comienzo, this.fin, 5);
/*      */         }
/*  539 */         else if ("total".equals(cmd)) {
/*  540 */           dialogoSeleccion(true, 2);
/*  541 */           if ((this.Accion) && (this.Buscar))
/*  542 */             buscarDato(7);
/*      */           else
/*  544 */             this.datos.seleccionEstandar(this.comienzo, this.fin, 6);
/*      */         }
/*  546 */         else if ("importacion".equals(cmd)) {
/*  547 */           seleccionarImportaciones();
/*  548 */           this.Accion = true;
/*  549 */         } else if ("completo".equals(cmd)) {
/*  550 */           this.Accion = true;
/*  551 */           this.datos.seleccionTotal();
/*      */         }
/*  553 */         if (this.Accion) {
/*  554 */           refrescarTabla(true);
/*      */         }
/*  556 */         this.Accion = false;
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*  561 */   private void CrearVencimiento() { SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
/*  562 */     String fecha_bd = sdf.format(fechaPago());
/*  563 */     if (this.selectedRow != -1) {
/*  564 */       String numero = this.tabla_ord.getValueAt(this.selectedRow, 0).toString();
/*  565 */       String cuenta = this.tabla_ord.getValueAt(this.selectedRow, 2).toString();
/*  566 */       double totalFactura = ((Double)this.tabla_ord.getValueAt(this.selectedRow, 7)).doubleValue();
/*  567 */       TipoFactura factura = new TipoFactura();
/*  568 */       factura.setNumero(numero);
/*  569 */       factura.setFecha(fecha_bd);
/*  570 */       factura.setTotal(totalFactura);
/*  571 */       factura.setCuenta(Integer.parseInt(cuenta));
/*  572 */       GenerarVencimientos crear = new GenerarVencimientos(Inicio.frame, Mensajes.getString("menu35"), true, factura, !esEmitida());
/*      */ 
/*  575 */       Dimension dlgSize = crear.getSize();
/*  576 */       Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/*  577 */       crear.setLocation((screenSize.width - dlgSize.width) / 2, (screenSize.height - dlgSize.height) / 2);
/*      */ 
/*  579 */       crear.setVisible(true);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void pagarCobrarFactura()
/*      */   {
/*  586 */     Date fechaDePago = this.queFecha.isSelected() ? new Date() : fechaPago();
/*  587 */     PagaVencimientos dlg = new PagaVencimientos(Inicio.frame, Mensajes.getString("fecha") + " y " + Mensajes.getString("ctaPago"), fechaDePago, true);
/*  588 */     mostrarDialogo(dlg);
/*  589 */     int cuentaPago = dlg.enCuenta();
/*  590 */     if (cuentaPago != -1) {
/*  591 */       String fechaPago = dlg.enfecha();
/*      */ 
/*  593 */       for (int x = this.selectedRow; x <= this.lastSelectedRow; x++)
/*  594 */         if (this.tabla.isRowSelected(x)) {
/*  595 */           String num = id_fila(x);
/*  596 */           if (!num.equals("Error")) {
/*  597 */             int cuenta = Integer.parseInt(this.tabla_ord.getValueAt(x, 2).toString());
/*  598 */             double importe = Double.parseDouble(this.tabla_ord.getValueAt(x, 7).toString());
/*  599 */             pagarFactura(num, fechaPago, cuenta, cuentaPago, importe);
/*      */           }
/*      */         }
/*      */     }
/*      */   }
/*      */ 
/*      */   private void eliminarFactura()
/*      */   {
/*  607 */     int borro = JOptionPane.showConfirmDialog(getContentPane(), Mensajes.getString("confBorrarFacturas"), Mensajes.getString("confirma"), 0);
/*      */ 
/*  610 */     if (borro == 0) {
/*  611 */       for (int x = this.selectedRow; x <= this.lastSelectedRow; x++) {
/*  612 */         if (this.tabla.isRowSelected(x)) {
/*  613 */           String num = id_fila(x);
/*  614 */           if (!num.equals("Error")) {
/*  615 */             borrarFactura(num);
/*      */           }
/*      */         }
/*      */       }
/*  619 */       refrescarTabla(true);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void modificarFactura()
/*      */   {
/*  625 */     if (this.selectedRow != -1) {
/*  626 */       String num = "R" + id_fila(this.selectedRow);
/*  627 */       Inicio.p.setFactura(num);
/*  628 */       Inicio.frame.modificarElemento(1);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void addBotones(JToolBar toolBar)
/*      */   {
/*  651 */     JButton button = null;
/*      */ 
/*  654 */     button = makeNavigationButton("save", "exportar", Mensajes.getString("saveAstt"), Mensajes.getString("saveAs"));
/*      */ 
/*  657 */     toolBar.add(button);
/*      */ 
/*  659 */     button = makeNavigationButton("csv24", "csv", Mensajes.getString("saveAsCsvtt"), Mensajes.getString("saveAsCsv"));
/*      */ 
/*  662 */     toolBar.add(button);
/*  663 */     toolBar.addSeparator(new Dimension(5, 0));
/*      */ 
/*  665 */     button = makeNavigationButton("fonts", "fuentes", Mensajes.getString("selFonttt"), Mensajes.getString("fuentes"));
/*      */ 
/*  668 */     toolBar.add(button);
/*      */ 
/*  671 */     button = makeNavigationButton("print24", "imprimir", Mensajes.getString("printtt"), Mensajes.getString("print"));
/*      */ 
/*  674 */     toolBar.add(button);
/*      */ 
/*  676 */     button = makeNavigationButton("preview", "vistaprevia", Mensajes.getString("previewtt"), Mensajes.getString("preview"));
/*      */ 
/*  679 */     toolBar.add(button);
/*      */ 
/*  681 */     toolBar.addSeparator(new Dimension(20, 0));
/*      */ 
/*  684 */     button = makeNavigationButton("concepto", "factura", Mensajes.getString("selBuFactt"), Mensajes.getString("factura"));
/*      */ 
/*  687 */     toolBar.add(button);
/*  688 */     toolBar.addSeparator(new Dimension(10, 0));
/*      */ 
/*  690 */     button = makeNavigationButton("date32", "fecha", Mensajes.getString("selBuFechatt"), Mensajes.getString("fecha"));
/*      */ 
/*  693 */     toolBar.add(button);
/*  694 */     toolBar.addSeparator(new Dimension(10, 0));
/*      */ 
/*  696 */     button = makeNavigationButton("cuentas", "cuenta", Mensajes.getString("selBuCtastt"), Mensajes.getString("cuentas"));
/*      */ 
/*  699 */     toolBar.add(button);
/*  700 */     toolBar.addSeparator(new Dimension(10, 0));
/*      */ 
/*  702 */     button = makeNavigationButton("asiento", "asiento", Mensajes.getString("selBuNumAstt"), Mensajes.getString("asiento"));
/*      */ 
/*  705 */     toolBar.add(button);
/*  706 */     toolBar.addSeparator(new Dimension(10, 0));
/*      */ 
/*  708 */     button = makeNavigationButton("importe", "base", Mensajes.getString("Selección-Búsqueda por base imponible"), Mensajes.getString("importe"));
/*      */ 
/*  711 */     toolBar.add(button);
/*  712 */     toolBar.addSeparator(new Dimension(10, 0));
/*      */ 
/*  714 */     button = makeNavigationButton("importe", "iva", Mensajes.getString("Selección-Búsqueda por importe de IVA"), Mensajes.getString("importe"));
/*      */ 
/*  717 */     toolBar.add(button);
/*  718 */     toolBar.addSeparator(new Dimension(10, 0));
/*      */ 
/*  720 */     button = makeNavigationButton("importe", "total", Mensajes.getString("Selección-Búsqueda por total de la factura"), Mensajes.getString("importe"));
/*      */ 
/*  723 */     toolBar.add(button);
/*  724 */     toolBar.addSeparator(new Dimension(10, 0));
/*      */ 
/*  726 */     button = makeNavigationButton("bell", "importacion", "Selecciona importaciones u operaciones interiores", "Importaciones");
/*      */ 
/*  729 */     toolBar.add(button);
/*  730 */     toolBar.addSeparator(new Dimension(10, 0));
/*      */ 
/*  732 */     button = makeNavigationButton("retable", "completo", Mensajes.getString("listCompletott"), Mensajes.getString("completo"));
/*      */ 
/*  735 */     toolBar.add(button);
/*  736 */     toolBar.addSeparator(new Dimension(10, 0));
/*      */ 
/*  738 */     button = makeNavigationButton("refind", "repetir", Mensajes.getString("reFindtt"), Mensajes.getString("repetir"));
/*      */ 
/*  741 */     toolBar.add(button);
/*      */   }
/*      */ 
/*      */   private JButton makeNavigationButton(String imageName, String actionCommand, String toolTipText, String altText)
/*      */   {
/*  748 */     JButton button = makeNavigationButton(imageName, toolTipText, altText);
/*  749 */     button.setActionCommand(actionCommand);
/*  750 */     button.addActionListener(this);
/*  751 */     return button;
/*      */   }
/*      */ 
/*      */   private void seleccionarImportaciones()
/*      */   {
/*  760 */     Object[] options = { "Exteriores", "Interiores" };
/*  761 */     int n = JOptionPane.showOptionDialog(getContentPane(), "Seleccione que tipo de facturas desea listar\n(Exteriores: Importaciones e intracomunitarias)", "Origen de las operaciones", 0, 3, null, options, options[0]);
/*      */ 
/*  770 */     if (n == 0)
/*  771 */       this.datos.seleccionarImportaciones(true);
/*      */     else
/*  773 */       this.datos.seleccionarImportaciones(false);
/*      */   }
/*      */ 
/*      */   private void buscarDato(int campo)
/*      */   {
/*  783 */     this.Accion = false;
/*  784 */     this.lastSearch = campo;
/*  785 */     String patron = this.comienzo.toUpperCase();
/*  786 */     int numFilas = this.tabla.getRowCount(); int x = 0;
/*  787 */     for (x = this.selectedRow + 1; x < numFilas; x++) {
/*  788 */       if (this.tabla.getValueAt(x, campo).toString().toUpperCase().indexOf(patron) != -1) {
/*  789 */         this.tabla.changeSelection(x, 0, false, false);
/*  790 */         break;
/*      */       }
/*      */     }
/*  793 */     if (x == numFilas) {
/*  794 */       int volver = JOptionPane.showConfirmDialog(getContentPane(), Mensajes.getString("finTabla"), Mensajes.getString("confirma"), 0);
/*      */ 
/*  797 */       if (volver == 0) {
/*  798 */         this.tabla.changeSelection(0, 0, false, false);
/*  799 */         if (this.tabla.getValueAt(0, campo).toString().toUpperCase().indexOf(patron) != -1) {
/*  800 */           return;
/*      */         }
/*  802 */         buscarDato(this.lastSearch);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private double totalImporte()
/*      */   {
/*  813 */     double total = 0.0D;
/*  814 */     int numFilas = this.tabla.getRowCount();
/*  815 */     for (int x = 0; x < numFilas; x++) {
/*  816 */       String importe = this.tabla_ord.getValueAt(x, 7).toString();
/*  817 */       total += Double.parseDouble(importe);
/*      */     }
/*  819 */     return total;
/*      */   }
/*      */ 
/*      */   private String id_fila(int fila)
/*      */   {
/*  829 */     return this.tabla_ord.getValueAt(fila, 0).toString();
/*      */   }
/*      */ 
/*      */   private Date fechaPago() {
/*  833 */     if (this.selectedRow != -1) {
/*  834 */       String fecha = this.tabla_ord.getValueAt(this.selectedRow, 1).toString();
/*  835 */       int year = Integer.parseInt(fecha.substring(0, 4));
/*  836 */       int month = Integer.parseInt(fecha.substring(5, 7)) - 1;
/*  837 */       int date = Integer.parseInt(fecha.substring(8));
/*  838 */       GregorianCalendar calendario = new GregorianCalendar();
/*  839 */       calendario.set(year, month, date);
/*  840 */       return calendario.getTime();
/*      */     }
/*  842 */     return new Date();
/*      */   }
/*      */ 
/*      */   private void borrarFactura(String id)
/*      */   {
/*      */     try
/*      */     {
/*  852 */       ManejoFacturas mF = new ManejoFacturas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  853 */       mF.borrar(esEmitida(), id);
/*      */ 
/*  855 */       GetInvoiceNumber dlg = new GetInvoiceNumber(Inicio.frame, true, false, id);
/*  856 */       mostrarDialogo(dlg);
/*  857 */       String temp = dlg.getNumeroFactura();
/*  858 */       if (!temp.equals("FALSE")) {
/*      */         try {
/*  860 */           FacturaControl fC = new FacturaControl(Inicio.getCFacturacion(), false);
/*  861 */           Factura factura = fC.facturaEjercicio(temp);
/*  862 */           if (factura != null) {
/*  863 */             fC.borrar(factura);
/*  864 */             fC.cerrarRs();
/*  865 */             LineaFacturaControl lFc = new LineaFacturaControl(Inicio.getCFacturacion(), false);
/*  866 */             ArrayList lineas = lFc.lineas(factura.getId());
/*  867 */             for (LineaFactura lineaFactura :(List<LineaFactura>)  lineas) {
/*  868 */               lFc.borrar(lineaFactura);
/*      */             }
/*  870 */             lFc.cerrarRs();
/*  871 */             AlmacenControl aC = new AlmacenControl();
/*  872 */             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/*  873 */             String date = sdf.format(factura.getFecha());
/*  874 */             for (LineaFactura linea : (List<LineaFactura>) lineas) {
/*  875 */               for (int i = 0; i < linea.getUnidades().intValue(); i++) {
/*  876 */                 String referencia = linea.getIdProducto().getReferencia();
/*  877 */                 double importe = linea.getBase().doubleValue() / linea.getUnidades().doubleValue();
/*  878 */                 importe = doubleTwoDecimals(Double.valueOf(importe)).doubleValue();
/*  879 */                 int io = linea.getBase().doubleValue() >= 0.0D ? 1 : -1;
/*  880 */                 aC.suprimirPIO(referencia, date, importe, io);
/*      */               }
/*      */             }
/*  883 */             Inicio.frame.renovarTabla(8);
/*      */           }
/*      */         } catch (Exception ex) {
/*  886 */           ex.printStackTrace();
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*      */     }
/*      */     catch (NumberFormatException e)
/*      */     {
/*  903 */       e.printStackTrace();
/*      */     }
/*      */   }
/*      */ 
/*      */   private Double doubleTwoDecimals(Double number) {
/*  908 */     if (number != null) {
/*  909 */       BigDecimal dec = new BigDecimal(number.doubleValue());
/*  910 */       return Double.valueOf(dec.setScale(2, 4).doubleValue());
/*      */     }
/*  912 */     return Double.valueOf(-1.0D);
/*      */   }
/*      */ 
/*      */   private void pagarFactura(String id, String fecha, int cuenta, int cuentaPago, double importe)
/*      */   {
/*  924 */     if (!fecha.substring(0, 4).equals(Inicio.p.getEjercicio())) {
/*  925 */       JOptionPane.showMessageDialog(getContentPane(), Mensajes.getString("ejercicioNoCorrecto"));
/*  926 */       return;
/*      */     }
/*      */     try {
/*  929 */       String documento = id;
/*  930 */       ManejoAsientos asientoM = new ManejoAsientos(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  931 */       ManejoApuntes apunteM = new ManejoApuntes(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  932 */       int continuar = 0;
/*  933 */       if (asientoM.existeAsiento(null, null, id, "P") != -1) {
/*  934 */         continuar = JOptionPane.showConfirmDialog(getContentPane(), "Ya existe un asiento de pago para esta factura\n¿Desea continuar?", Mensajes.getString("confirma"), 0);
/*      */       }
/*      */ 
/*  938 */       if (continuar == 0) {
/*  939 */         int numeroAs = asientoM.nuevoNumero();
/*  940 */         if (numeroAs != -1) {
/*  941 */           int idAs = asientoM.crear(numeroAs, fecha, documento, marca());
/*  942 */           if (idAs != -1) {
/*  943 */             String concepto = sPago() + " " + Mensajes.getString("factura") + " " + id;
/*  944 */             if (concepto.length() > 30) {
/*  945 */               concepto = concepto.substring(0, 30);
/*      */             }
/*  947 */             String DH1 = esEmitida() ? Mensajes.getString("debeA") : Mensajes.getString("haberA");
/*  948 */             String DH2 = esEmitida() ? Mensajes.getString("haberA") : Mensajes.getString("debeA");
/*  949 */             apunteM.crear(idAs, cuenta, concepto, DH2, importe);
/*  950 */             apunteM.crear(idAs, cuentaPago, concepto, DH1, importe);
/*  951 */             Inicio.frame.renovarTabla(0);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     catch (NumberFormatException e) {
/*  957 */       e.printStackTrace();
/*      */     }
/*      */   }
/*      */ 
/*      */   private void refrescarTabla(boolean fin) {
/*  962 */     if (this.datos.conexionCerrada()) {
/*  963 */       return;
/*      */     }
/*  965 */     this.tabla.removeAll();
/*      */     try {
/*  967 */       this.model = new ScrollableTableModel(this.datos.resultado(), this.nombres_col);
/*      */     } catch (SQLException e) {
/*  969 */       e.printStackTrace();
/*      */     }
/*  971 */     this.tabla_ord.setTableModel(this.model);
/*  972 */     this.tabla.setModel(this.tabla_ord);
/*  973 */     ListSelectionModel rowSM = this.tabla.getSelectionModel();
/*  974 */     rowSM.addListSelectionListener(new ListSelectionListener()
/*      */     {
/*      */       public void valueChanged(ListSelectionEvent arg0) {
/*  977 */         TablaFacturasRecibidas.this.facturasSeleccionTabla(arg0);
/*      */       }
/*      */     });
/*  980 */     this.columna = this.tabla.getColumnModel().getColumn(0);
/*  981 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.11D));
/*  982 */     this.columna.setCellRenderer(new DerechaColorRenderer());
/*  983 */     this.columna = this.tabla.getColumnModel().getColumn(1);
/*  984 */     this.columna.setCellRenderer(new FechaColorRenderer());
/*  985 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.11D));
/*  986 */     this.columna = this.tabla.getColumnModel().getColumn(2);
/*  987 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.11D));
/*  988 */     this.columna.setCellRenderer(new DerechaColorRenderer());
/*  989 */     this.columna = this.tabla.getColumnModel().getColumn(3);
/*      */ 
/*  991 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.08D));
/*  992 */     this.columna.setCellRenderer(new DerechaColorRenderer());
/*  993 */     this.columna = this.tabla.getColumnModel().getColumn(4);
/*  994 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.2D));
/*  995 */     this.columna = this.tabla.getColumnModel().getColumn(5);
/*  996 */     this.columna.setCellRenderer(new ImporteColorRenderer());
/*  997 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.11D));
/*  998 */     this.columna = this.tabla.getColumnModel().getColumn(6);
/*  999 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.11D));
/* 1000 */     this.columna.setCellRenderer(new ImporteColorRenderer());
/* 1001 */     this.columna = this.tabla.getColumnModel().getColumn(7);
/* 1002 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.012D));
/* 1003 */     this.columna.setCellRenderer(new ImporteColorRenderer());
/* 1004 */     this.columna = this.tabla.getColumnModel().getColumn(8);
/* 1005 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.05D));
/* 1006 */     this.columna.setCellRenderer(new BooleanColorRenderer());
/* 1007 */     this.tabla.setDefaultRenderer(Object.class, new GeneralColorRenderer());
/* 1008 */     this.total.setText(this.fn.format(totalImporte()));
/* 1009 */     if (fin)
/* 1010 */       this.tabla.changeSelection(this.tabla.getRowCount() - 1, 0, true, true);
/*      */   }
/*      */ 
/*      */   private void facturasSeleccionTabla(ListSelectionEvent e)
/*      */   {
/* 1015 */     SeleccionTabla(e);
/* 1016 */     if (this.selectedRow > this.tabla.getRowCount() - 1) {
/* 1017 */       this.selectedRow = (this.tabla.getRowCount() - 1);
/*      */     }
/* 1019 */     if (this.selectedRow != -1) {
/* 1020 */       int cuenta = Integer.parseInt(this.tabla_ord.getValueAt(this.selectedRow, 2).toString());
/* 1021 */       this.infoCuenta.setText(datosCuenta(cuenta));
/*      */     }
/*      */   }
/*      */ 
/*      */   private String datosCuenta(int cuenta) {
/* 1026 */     String datos = "";
/* 1027 */     ManejoSubcuentas mS = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 1028 */     TipoSubcuenta subcuenta = new TipoSubcuenta();
/* 1029 */     subcuenta = mS.datos(cuenta);
/* 1030 */     datos = subcuenta.getNombre() + " : " + this.fn.format(subcuenta.getSaldo());
/* 1031 */     return datos;
/*      */   }
/*      */ 
/*      */   private JMenuItem getJMenuItem()
/*      */   {
/* 1040 */     if (this.jMenuItem == null) {
/* 1041 */       this.jMenuItem = new JMenuItem();
/* 1042 */       this.jMenuItem.setText(Mensajes.getString("borrar"));
/* 1043 */       this.jMenuItem.setIcon(this.iconoBorrar);
/* 1044 */       this.jMenuItem.addActionListener(new ActionListener()
/*      */       {
/*      */         public void actionPerformed(ActionEvent e) {
/* 1047 */           TablaFacturasRecibidas.this.eliminarFactura();
/*      */         }
/*      */       });
/*      */     }
/* 1051 */     return this.jMenuItem;
/*      */   }
/*      */ 
/*      */   private JMenuItem getJMenuItem1()
/*      */   {
/* 1060 */     if (this.jMenuItem1 == null) {
/* 1061 */       this.jMenuItem1 = new JMenuItem();
/* 1062 */       this.jMenuItem1.setText(sPagar());
/* 1063 */       this.jMenuItem1.setIcon(this.iconoPagar);
/* 1064 */       this.jMenuItem1.addActionListener(new ActionListener()
/*      */       {
/*      */         public void actionPerformed(ActionEvent e) {
/* 1067 */           TablaFacturasRecibidas.this.pagarCobrarFactura();
/*      */         }
/*      */       });
/*      */     }
/* 1071 */     return this.jMenuItem1;
/*      */   }
/*      */ 
/*      */   private JMenuItem getJMenuItem2() {
/* 1075 */     if (this.jMenuItem2 == null) {
/* 1076 */       this.jMenuItem2 = new JMenuItem();
/* 1077 */       this.jMenuItem2.setText(Mensajes.getString("vencimientos"));
/* 1078 */       this.jMenuItem2.setIcon(this.iconoCrear);
/* 1079 */       this.jMenuItem2.addActionListener(new ActionListener()
/*      */       {
/*      */         public void actionPerformed(ActionEvent e) {
/* 1082 */           TablaFacturasRecibidas.this.CrearVencimiento();
/*      */         }
/*      */       });
/*      */     }
/* 1086 */     return this.jMenuItem2;
/*      */   }
/*      */ 
/*      */   private JMenuItem getJMenuItem3() {
/* 1090 */     if (this.jMenuItem3 == null) {
/* 1091 */       this.jMenuItem3 = new JMenuItem();
/* 1092 */       this.jMenuItem3.setText(Mensajes.getString("modificar"));
/* 1093 */       this.jMenuItem3.setIcon(this.iconoModificar);
/* 1094 */       this.jMenuItem3.addActionListener(new ActionListener()
/*      */       {
/*      */         public void actionPerformed(ActionEvent e) {
/* 1097 */           TablaFacturasRecibidas.this.modificarFactura();
/*      */         }
/*      */       });
/*      */     }
/* 1101 */     return this.jMenuItem3;
/*      */   }
/*      */ 
/*      */   private void marcoActivado() {
/* 1105 */     if (this.datos.conexionCerrada())
/* 1106 */       this.datos.abrirConexion();
/*      */   }
/*      */ 
/*      */   private void marcoDesactivado()
/*      */   {
/*      */   }
/*      */ 
/*      */   public void renovarTabla() {
/* 1114 */     refrescarTabla(false);
/*      */   }
/*      */ 
/*      */   private void exportarA_archivo() {
/* 1118 */     String EOL = FinLinea.get();
/* 1119 */     AlinearCadena alinea = new AlinearCadena();
/* 1120 */     JFileChooser fc = new JFileChooser();
/* 1121 */     fc.setSelectedFile(new File("FacturasRecibidas.txt"));
/* 1122 */     int retorno = fc.showSaveDialog(Inicio.frame);
/* 1123 */     if (retorno == 0) {
/* 1124 */       FechaHoy hoy = new FechaHoy();
/* 1125 */       File archivo = fc.getSelectedFile();
/* 1126 */       archivo = AddExtension.txt(archivo);
/* 1127 */       GrabarFichero salida = new GrabarFichero(archivo);
/* 1128 */       salida.insertar("Listado de facturas recibidas      " + hoy.getFecha() + EOL);
/*      */ 
/* 1130 */       int filas = this.tabla.getRowCount(); int columnas = this.tabla.getColumnCount();
/* 1131 */       int[] x = new int[columnas];
/* 1132 */       String cadena = "";
/* 1133 */       for (int nCol = 0; nCol < columnas; nCol++) {
/* 1134 */         boolean izq = true;
/* 1135 */         this.columna = this.tabla.getColumnModel().getColumn(nCol);
/* 1136 */         String titulo = (String)this.columna.getIdentifier();
/* 1137 */         if (titulo.equals(Mensajes.getString("numero"))) {
/* 1138 */           x[nCol] = 20;
/* 1139 */         } else if (titulo.equals(Mensajes.getString("fecha"))) {
/* 1140 */           x[nCol] = 11;
/* 1141 */         } else if (titulo.equals(Mensajes.getString("cuenta"))) {
/* 1142 */           x[nCol] = 10;
/* 1143 */         } else if (titulo.equals(Mensajes.getString("asiento"))) {
/* 1144 */           x[nCol] = 11;
/* 1145 */         } else if (titulo.equals(Mensajes.getString("concepto"))) {
/* 1146 */           x[nCol] = 30;
/* 1147 */         } else if (titulo.equals(Mensajes.getString("base"))) {
/* 1148 */           x[nCol] = 12;
/* 1149 */           izq = false;
/* 1150 */         } else if (titulo.equals(Mensajes.getString("IVAs"))) {
/* 1151 */           x[nCol] = 12;
/* 1152 */           izq = false;
/* 1153 */         } else if (titulo.equals(Mensajes.getString("total"))) {
/* 1154 */           x[nCol] = 12;
/* 1155 */           izq = false;
/* 1156 */         } else if (titulo.equals("Exterior")) {
/* 1157 */           x[nCol] = 3;
/*      */         }
/* 1159 */         if (izq)
/* 1160 */           cadena = cadena + alinea.Izquierda(titulo, x[nCol]);
/*      */         else {
/* 1162 */           cadena = cadena + alinea.Derecha(titulo, x[nCol]);
/*      */         }
/*      */       }
/* 1165 */       salida.insertar(cadena + EOL);
/* 1166 */       cadena = "";
/* 1167 */       for (int nCol = 0; nCol < columnas; nCol++) {
/* 1168 */         for (int nCarac = 0; nCarac < x[nCol]; nCarac++) {
/* 1169 */           cadena = cadena + "-";
/*      */         }
/*      */       }
/* 1172 */       salida.insertar(cadena + EOL);
/*      */ 
/* 1174 */       for (int nRow = 0; nRow < filas; nRow++) {
/* 1175 */         cadena = "";
/* 1176 */         for (int nCol = 0; nCol < columnas; nCol++) {
/* 1177 */           int col = this.tabla.getColumnModel().getColumn(nCol).getModelIndex();
/* 1178 */           Object obj = this.tabla_ord.getValueAt(nRow, col);
/* 1179 */           if ((obj instanceof Double)) {
/* 1180 */             String str = this.fn.format(obj);
/* 1181 */             cadena = cadena + alinea.Derecha(str, x[nCol]);
/* 1182 */           } else if ((obj instanceof Date)) {
/* 1183 */             String cad = obj.toString();
/* 1184 */             int year = Integer.parseInt(cad.substring(0, 4));
/* 1185 */             int month = Integer.parseInt(cad.substring(5, 7)) - 1;
/* 1186 */             int date = Integer.parseInt(cad.substring(8));
/* 1187 */             GregorianCalendar calendario = new GregorianCalendar();
/* 1188 */             calendario.set(year, month, date);
/* 1189 */             SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
/* 1190 */             String str = sdf.format(calendario.getTime());
/* 1191 */             cadena = cadena + alinea.Izquierda(str, x[nCol]);
/*      */           } else {
/* 1193 */             String str = obj.toString();
/* 1194 */             if (str.equals("false")) {
/* 1195 */               str = " " + Mensajes.getString("no");
/*      */             }
/* 1197 */             if (str.equals("true")) {
/* 1198 */               str = " " + Mensajes.getString("yes");
/*      */             }
/* 1200 */             cadena = cadena + alinea.Izquierda(str, x[nCol]);
/*      */           }
/*      */         }
/* 1203 */         salida.insertar(cadena + EOL);
/*      */       }
/* 1205 */       salida.insertar(EOL);
/* 1206 */       salida.insertar(alinea.Derecha("Total:   " + this.fn.format(totalImporte()), 82));
/* 1207 */       salida.cerrar();
/*      */     }
/*      */   }
/*      */ 
/*      */   private void imprimirTabla() {
/* 1212 */     PrinterJob printerJob = PrinterJob.getPrinterJob();
/* 1213 */     PageFormat userFormat = printerJob.pageDialog(printerJob.defaultPage());
/* 1214 */     printerJob.setPrintable(this, userFormat);
/* 1215 */     if (printerJob.printDialog()) {
/* 1216 */       this.m_maxNumPage = 1;
/* 1217 */       setCursor(Cursor.getPredefinedCursor(3));
/*      */       try
/*      */       {
/* 1220 */         printerJob.print();
/*      */       } catch (PrinterException e) {
/* 1222 */         System.err.println(Mensajes.getString("errPrint") + ": " + e);
/*      */       }
/* 1224 */       setCursor(Cursor.getPredefinedCursor(0));
/*      */     }
/*      */   }
/*      */ 
/*      */   public int print(Graphics pg, PageFormat pageFormat, int pageIndex)
/*      */     throws PrinterException
/*      */   {
/* 1231 */     if (pageIndex >= this.m_maxNumPage) {
/* 1232 */       return 1;
/*      */     }
/*      */ 
/* 1235 */     FechaHoy hoy = new FechaHoy();
/* 1236 */     int anchoImporte = 0;
/*      */ 
/* 1238 */     pg.translate((int)pageFormat.getImageableX(), (int)pageFormat.getImageableY());
/*      */ 
/* 1240 */     int wPage = 0;
/* 1241 */     int hPage = 0;
/* 1242 */     if (pageFormat.getOrientation() == 1) {
/* 1243 */       wPage = (int)pageFormat.getImageableWidth();
/* 1244 */       hPage = (int)pageFormat.getImageableHeight();
/*      */     } else {
/* 1246 */       wPage = (int)pageFormat.getImageableWidth();
/* 1247 */       wPage += wPage / 2;
/* 1248 */       hPage = (int)pageFormat.getImageableHeight();
/* 1249 */       pg.setClip(0, 0, wPage, hPage);
/*      */     }
/* 1251 */     int y = 0;
/*      */ 
/* 1253 */     pg.setFont(this.tabla.getFont().deriveFont(1, this.tabla.getFont().getSize2D() + 2.0F));
/* 1254 */     pg.setColor(Color.black);
/*      */ 
/* 1256 */     FontMetrics fm = pg.getFontMetrics();
/* 1257 */     y += fm.getAscent();
/* 1258 */     pg.drawString(getTitle() + "     " + hoy.getFecha(), 0, y);
/* 1259 */     y += 20;
/* 1260 */     Font headerFont = this.tabla.getFont().deriveFont(1);
/* 1261 */     pg.setFont(headerFont);
/* 1262 */     fm = pg.getFontMetrics();
/*      */ 
/* 1264 */     TableColumnModel colModel = this.tabla.getColumnModel();
/* 1265 */     int nColumns = colModel.getColumnCount();
/* 1266 */     int[] x = new int[nColumns];
/* 1267 */     x[0] = 0;
/*      */ 
/* 1269 */     int h = fm.getAscent();
/* 1270 */     y += h;
/*      */ 
/* 1274 */     for (int nCol = 0; nCol < nColumns; nCol++) {
/* 1275 */       TableColumn tk = colModel.getColumn(nCol);
/* 1276 */       int width = tk.getWidth();
/* 1277 */       if (x[nCol] + width > wPage) {
/* 1278 */         nColumns = nCol;
/* 1279 */         break;
/*      */       }
/* 1281 */       if (nCol + 1 < nColumns) {
/* 1282 */         x[(nCol + 1)] = (x[nCol] + width);
/*      */       }
/* 1284 */       String title = (String)tk.getIdentifier();
/* 1285 */       if (title.equals(Mensajes.getString("importe"))) {
/* 1286 */         anchoImporte = tk.getWidth() * 80 / 100;
/*      */       }
/* 1288 */       pg.drawString(title, x[nCol], y);
/*      */     }
/*      */ 
/* 1291 */     pg.setFont(this.tabla.getFont());
/* 1292 */     fm = pg.getFontMetrics();
/*      */ 
/* 1294 */     int header = y;
/* 1295 */     h = fm.getHeight();
/* 1296 */     int rowH = Math.max((int)(h * 1.5D), 10);
/* 1297 */     int rowPerPage = (hPage - header) / rowH;
/* 1298 */     this.m_maxNumPage = Math.max((int)Math.ceil(this.tabla.getRowCount() / rowPerPage), 1);
/*      */ 
/* 1302 */     int iniRow = pageIndex * rowPerPage;
/* 1303 */     int endRow = Math.min(this.tabla.getRowCount(), iniRow + rowPerPage);
/*      */ 
/* 1306 */     for (int nRow = iniRow; nRow < endRow; nRow++) {
/* 1307 */       y += h;
/* 1308 */       for (int nCol = 0; nCol < nColumns; nCol++) {
/* 1309 */         pg.setColor(Color.black);
/* 1310 */         int col = this.tabla.getColumnModel().getColumn(nCol).getModelIndex();
/* 1311 */         Object obj = this.tabla_ord.getValueAt(nRow, col);
/* 1312 */         String str = "";
/* 1313 */         if (obj != null) {
/* 1314 */           str = obj.toString();
/*      */         }
/* 1316 */         if (str.equals("false")) {
/* 1317 */           str = Mensajes.getString("no");
/*      */         }
/* 1319 */         if (str.equals("true")) {
/* 1320 */           str = Mensajes.getString("yes");
/*      */         }
/* 1322 */         if ((obj instanceof Double)) {
/* 1323 */           str = this.fn.format(obj);
/*      */ 
/* 1325 */           FontRenderContext frc = ((Graphics2D)pg).getFontRenderContext();
/* 1326 */           TextLayout layout = new TextLayout(str, this.tabla.getFont(), frc);
/* 1327 */           float w = layout.getAdvance();
/* 1328 */           pg.drawString(str, x[nCol] + (anchoImporte - (int)w), y);
/* 1329 */         } else if ((obj instanceof Date)) {
/* 1330 */           String cad = obj.toString();
/* 1331 */           int year = Integer.parseInt(cad.substring(0, 4));
/* 1332 */           int month = Integer.parseInt(cad.substring(5, 7)) - 1;
/* 1333 */           int date = Integer.parseInt(cad.substring(8));
/* 1334 */           GregorianCalendar calendario = new GregorianCalendar();
/* 1335 */           calendario.set(year, month, date);
/* 1336 */           SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
/* 1337 */           str = sdf.format(calendario.getTime());
/* 1338 */           pg.drawString(str, x[nCol], y);
/*      */         } else {
/* 1340 */           pg.drawString(str, x[nCol], y);
/*      */         }
/*      */       }
/*      */     }
/* 1344 */     if (pageIndex == this.m_maxNumPage - 1) {
/* 1345 */       y += 20;
/* 1346 */       pg.drawString(Mensajes.getString("total") + ":", 250, y);
/* 1347 */       pg.drawString(this.total.getText(), 310, y);
/*      */     }
/* 1349 */     System.gc();
/* 1350 */     return 0;
/*      */   }
/*      */ 
/*      */   public void cerrarConexion()
/*      */   {
/* 1355 */     if (!this.datos.conexionCerrada())
/* 1356 */       this.datos.cerrarConexion();
/*      */   }
/*      */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.TablaFacturasRecibidas
 * JD-Core Version:    0.6.2
 */