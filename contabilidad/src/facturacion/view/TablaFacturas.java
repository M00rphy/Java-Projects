/*      */ package facturacion.view;
/*      */ 
/*      */ import contaes.Inicio;
/*      */ import contaes.MarcoInicio;
/*      */ import contaes.MarcoInternoTablas;
/*      */ import contaes.Puente;
/*      */ import contaes.auxiliar.AlinearCadena;
/*      */ import contaes.auxiliarTablas.BooleanColorRenderer;
/*      */ import contaes.auxiliarTablas.FechaColorRenderer;
/*      */ import contaes.auxiliarTablas.GeneralColorRenderer;
/*      */ import contaes.auxiliarTablas.ImporteColorRenderer;
/*      */ import contaes.auxiliarTablas.ScrollableTableModel;
/*      */ import contaes.auxiliarTablas.TablaToCsv;
/*      */ import contaes.auxiliarTablas.TableSorter;
/*      */ import contaes.dialogosAuxiliares.GetInvoiceNumber;
/*      */ import contaes.dialogosAuxiliares.MostrarFuentes;
/*      */ import contaes.dialogosAuxiliares.PrintPreview;
/*      */ import contaes.manejoDatos.ManejoFacturas;
/*      */ import contaes.manejoDatos.auxiliar.AddExtension;
/*      */ import contaes.manejoDatos.auxiliar.FechaHoy;
/*      */ import contaes.manejoDatos.auxiliar.FinLinea;
/*      */ import contaes.manejoDatos.auxiliar.GrabarFichero;
/*      */ import facturacion.control.AlmacenControl;
/*      */ import facturacion.control.FacturaControl;
/*      */ import facturacion.control.LineaFacturaControl;
/*      */ import facturacion.control.TablaFacturasControl;
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
/*      */ import java.util.logging.Level;
/*      */ import java.util.logging.Logger;
/*      */ import javax.swing.BorderFactory;
/*      */ import javax.swing.ImageIcon;
/*      */ import javax.swing.JButton;
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
/*      */ public class TablaFacturas extends MarcoInternoTablas
/*      */   implements Printable, ActionListener
/*      */ {
/*      */   private static final String FECHA = "fecha";
/*      */   private static final String NUMERO = "factura";
/*      */   private static final String CLIENTE = "cliente";
/*      */   private static final String BASE = "base";
/*      */   private static final String IMPUESTOS = "impuestos";
/*      */   private static final String RECARGO = "recargo";
/*      */   private static final String CONTABILIZADA = "contabilizadas";
/*      */   private static final String COMPLETO = "completo";
/*      */   private static final String REPETIR = "repetir";
/*      */   private static final String SELECCIONAR = "seleccionar";
/*      */   private static final String BORRAR = "borrar";
/*      */   private static final String IMPRIMIR = "imprimir";
/*      */   private static final String VISTAPREVIA = "vistaprevia";
/*      */   private static final String FUENTES = "fuentes";
/*      */   private static final String EXPORTAR = "exportar";
/*      */   private static final String CSV = "csv";
/*      */   private static final String CONTABILIZAR = "contabilizar";
/*  127 */   private JPopupMenu menuEmergente = new JPopupMenu();
/*  128 */   private JMenuItem jMenuItem1 = null;
/*  129 */   private JMenuItem jMenuItem2 = null;
/*  130 */   protected int m_maxNumPage = 1;
/*  131 */   private BorderLayout borderLayout1 = new BorderLayout();
/*  132 */   private GridBagLayout gbLayout = new GridBagLayout();
/*  133 */   private JToolBar barra = new JToolBar();
/*  134 */   private JScrollPane scrollPane = new JScrollPane();
/*  135 */   private JPanel datosCuentas = new JPanel();
/*  136 */   private JButton selec = new JButton();
/*  137 */   private JButton borrar = new JButton();
/*  138 */   private JButton contabilizar = new JButton();
/*  139 */   private JLabel totalE = new JLabel(Mensajes.getString("total") + ":");
/*  140 */   private JTextField total = new JTextField();
/*  141 */   private TableSorter tablaOrdenable = new TableSorter();
/*      */   private JTable tabla;
/*  143 */   private TableColumn columna = null;
/*      */   private TablaFacturasControl datos;
/*      */   private ScrollableTableModel model;
/*  146 */   private ImageIcon iconoSelec = createImageIcon("/contaes/iconos/edit.png");
/*  147 */   private ImageIcon iconoCrear = createImageIcon("/contaes/iconos/gestionFacturas.png");
/*  148 */   private ImageIcon iconoBorrar = createImageIcon("/contaes/iconos/delete.png");
/*  149 */   private ImageIcon iconoPagar = createImageIcon("/contaes/iconos/importe16.png");
/*  150 */   private boolean ventas = true;
/*      */ 
/*      */   public TablaFacturas() {
/*  153 */     this("Facturación - Gestión Ventas");
/*      */   }
/*      */ 
/*      */   public TablaFacturas(String title) {
/*  157 */     super(title);
/*  158 */     this.datos = new TablaFacturasControl(Inicio.p.getEmpresa(), Inicio.p.getEjercicio(), this.ventas);
/*      */     try {
/*  160 */       initialize();
/*  161 */       this.total.setText(this.fn.format(totalImporte()));
/*      */     } catch (Exception exception) {
/*  163 */       exception.printStackTrace();
/*      */     }
/*      */   }
/*      */ 
/*      */   private void initialize() throws Exception {
/*  168 */     DecimalFormatSymbols formato = new DecimalFormatSymbols();
/*  169 */     formato.setDecimalSeparator(',');
/*  170 */     formato.setPerMill('.');
/*  171 */     this.fn = new DecimalFormat("#,###,##0.00", formato);
/*      */ 
/*  174 */     this.menuEmergente.add(getJMenuItem1());
/*  175 */     this.menuEmergente.add(getJMenuItem2());
/*      */ 
/*  178 */     setFrameIcon(new ImageIcon(getClass().getResource(icono())));
/*  179 */     Rectangle parentBounds = Inicio.frame.localizacion();
/*  180 */     int ancho = parentBounds.width < 900 ? parentBounds.width : 790;
/*  181 */     int alto = parentBounds.height < 580 ? parentBounds.height : 530;
/*  182 */     setBounds(10, 50, ancho, alto);
/*  183 */     getContentPane().setLayout(this.borderLayout1);
/*  184 */     addInternalFrameListener(new InternalFrameAdapter()
/*      */     {
/*      */       public void internalFrameOpened(InternalFrameEvent arg0)
/*      */       {
/*  188 */         super.internalFrameOpened(arg0);
/*  189 */         TablaFacturas.this.marcoActivado();
/*      */       }
/*      */ 
/*      */       public void internalFrameClosing(InternalFrameEvent e)
/*      */       {
/*  194 */         TablaFacturas.this.datos.cerrarConexion();
/*  195 */         super.internalFrameClosing(e);
/*      */       }
/*      */ 
/*      */       public void internalFrameDeactivated(InternalFrameEvent e)
/*      */       {
/*  200 */         TablaFacturas.this.marcoDesactivado();
/*      */       }
/*      */ 
/*      */       public void internalFrameActivated(InternalFrameEvent e)
/*      */       {
/*  205 */         TablaFacturas.this.marcoActivado();
/*      */       }
/*      */ 
/*      */       public void internalFrameClosed(InternalFrameEvent e)
/*      */       {
/*  210 */         Inicio.frame.eliminarMarcoMenu(TablaFacturas.this.getTitle());
/*      */       }
/*      */     });
/*  213 */     this.nombres_col.add("");
/*  214 */     this.nombres_col.add("Número");
/*  215 */     this.nombres_col.add("Fecha");
/*  216 */     this.nombres_col.add("Cliente");
/*  217 */     this.nombres_col.add("Base");
/*  218 */     this.nombres_col.add("Impuestos");
/*  219 */     this.nombres_col.add("Total");
/*  220 */     this.nombres_col.add("Recargo");
/*  221 */     this.nombres_col.add("Contabilizada");
/*  222 */     this.model = new ScrollableTableModel(this.datos.resultado(), this.nombres_col);
/*  223 */     this.tablaOrdenable.setTableModel(this.model);
/*  224 */     this.tabla = new JTable(this.tablaOrdenable);
/*  225 */     this.tablaOrdenable.setTableHeader(this.tabla.getTableHeader());
/*  226 */     this.tabla.getTableHeader().setToolTipText(Mensajes.getString("tableHeadertt"));
/*  227 */     this.tabla.addMouseListener(new MouseAdapter()
/*      */     {
/*      */       public void mouseClicked(MouseEvent e)
/*      */       {
/*  231 */         if (e.getButton() == 3) {
/*  232 */           TablaFacturas.this.tabla.changeSelection(TablaFacturas.this.tabla.rowAtPoint(new Point(e.getX(), e.getY())), TablaFacturas.this.tabla.columnAtPoint(new Point(e.getX(), e.getY())), false, false);
/*      */ 
/*  236 */           TablaFacturas.this.menuEmergente.show(e.getComponent(), e.getX(), e.getY());
/*      */         }
/*      */       }
/*      */     });
/*  241 */     ListSelectionModel rowSM = this.tabla.getSelectionModel();
/*  242 */     rowSM.addListSelectionListener(new ListSelectionListener()
/*      */     {
/*      */       public void valueChanged(ListSelectionEvent arg0)
/*      */       {
/*  246 */         TablaFacturas.this.facturasSeleccionTabla(arg0);
/*      */       }
/*      */     });
/*  249 */     this.anchoTabla = (ancho - 40);
/*  250 */     this.columna = this.tabla.getColumnModel().getColumn(1);
/*  251 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.2D));
/*  252 */     this.columna.setCellRenderer(new GeneralColorRenderer());
/*  253 */     this.columna = this.tabla.getColumnModel().getColumn(2);
/*  254 */     this.columna.setCellRenderer(new FechaColorRenderer());
/*  255 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.13D));
/*  256 */     this.columna = this.tabla.getColumnModel().getColumn(3);
/*  257 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.21D));
/*  258 */     this.columna.setCellRenderer(new GeneralColorRenderer());
/*  259 */     this.columna = this.tabla.getColumnModel().getColumn(4);
/*  260 */     this.columna.setCellRenderer(new ImporteColorRenderer());
/*  261 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.12D));
/*  262 */     this.columna = this.tabla.getColumnModel().getColumn(5);
/*  263 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.12D));
/*  264 */     this.columna.setCellRenderer(new ImporteColorRenderer());
/*  265 */     this.columna = this.tabla.getColumnModel().getColumn(6);
/*  266 */     this.columna.setCellRenderer(new ImporteColorRenderer());
/*  267 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.12D));
/*  268 */     this.columna = this.tabla.getColumnModel().getColumn(7);
/*  269 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.05D));
/*  270 */     this.columna.setCellRenderer(new BooleanColorRenderer());
/*  271 */     this.columna = this.tabla.getColumnModel().getColumn(8);
/*  272 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.05D));
/*  273 */     this.columna.setCellRenderer(new BooleanColorRenderer());
/*      */ 
/*  275 */     this.columna = this.tabla.getColumnModel().getColumn(0);
/*  276 */     this.tabla.removeColumn(this.columna);
/*      */ 
/*  280 */     this.scrollPane.getViewport().add(this.tabla);
/*  281 */     this.scrollPane.setBorder(new CompoundBorder(BorderFactory.createLoweredBevelBorder(), BorderFactory.createMatteBorder(10, 10, 10, 10, Color.white)));
/*      */ 
/*  284 */     addBotones(this.barra);
/*  285 */     this.barra.setRollover(true);
/*      */ 
/*  287 */     this.selec.setText(Mensajes.getString("modificar"));
/*  288 */     this.selec.setIcon(this.iconoSelec);
/*  289 */     this.selec.setVerticalTextPosition(0);
/*  290 */     this.selec.setHorizontalTextPosition(2);
/*  291 */     this.selec.setActionCommand("seleccionar");
/*  292 */     this.selec.addActionListener(this);
/*  293 */     this.borrar.setText(Mensajes.getString("borrar"));
/*  294 */     this.borrar.setIcon(this.iconoBorrar);
/*  295 */     this.borrar.setVerticalTextPosition(0);
/*  296 */     this.borrar.setHorizontalTextPosition(2);
/*  297 */     this.borrar.setActionCommand("borrar");
/*  298 */     this.borrar.addActionListener(this);
/*  299 */     this.contabilizar.setText(Mensajes.getString("contabilizar"));
/*  300 */     this.contabilizar.setIcon(this.iconoCrear);
/*  301 */     this.contabilizar.setVerticalTextPosition(0);
/*  302 */     this.contabilizar.setHorizontalTextPosition(2);
/*  303 */     this.contabilizar.setActionCommand("contabilizar");
/*  304 */     this.contabilizar.addActionListener(this);
/*  305 */     this.datosCuentas.setBorder(BorderFactory.createEmptyBorder(5, 50, 10, 50));
/*  306 */     this.datosCuentas.setLayout(this.gbLayout);
/*  307 */     this.total.setEditable(false);
/*  308 */     this.total.setHorizontalAlignment(4);
/*  309 */     GridBagConstraints cons = new GridBagConstraints();
/*  310 */     cons.gridx = 2;
/*  311 */     cons.gridy = 0;
/*  312 */     cons.anchor = 10;
/*  313 */     cons.insets.right = 30;
/*  314 */     cons.insets.left = 30;
/*  315 */     this.gbLayout.setConstraints(this.totalE, cons);
/*  316 */     this.datosCuentas.add(this.totalE);
/*      */ 
/*  318 */     cons.gridx = 3;
/*  319 */     cons.gridwidth = 2;
/*  320 */     cons.fill = 2;
/*  321 */     cons.insets.bottom = 5;
/*  322 */     this.gbLayout.setConstraints(this.total, cons);
/*  323 */     this.datosCuentas.add(this.total);
/*      */ 
/*  325 */     cons.gridwidth = 1;
/*  326 */     cons.gridx = 0;
/*  327 */     cons.gridy = 1;
/*  328 */     cons.fill = 0;
/*  329 */     cons.insets.bottom = 0;
/*  330 */     this.gbLayout.setConstraints(this.selec, cons);
/*  331 */     this.datosCuentas.add(this.selec);
/*      */ 
/*  333 */     cons.gridx = 2;
/*  334 */     this.gbLayout.setConstraints(this.borrar, cons);
/*  335 */     this.datosCuentas.add(this.borrar);
/*      */ 
/*  337 */     cons.gridx = 3;
/*  338 */     this.gbLayout.setConstraints(this.contabilizar, cons);
/*  339 */     this.datosCuentas.add(this.contabilizar);
/*      */ 
/*  341 */     getContentPane().add(this.scrollPane, "Center");
/*  342 */     getContentPane().add(this.barra, "First");
/*  343 */     getContentPane().add(this.datosCuentas, "Last");
/*  344 */     this.tabla.changeSelection(this.tabla.getRowCount() - 1, 0, true, true);
/*      */   }
/*      */ 
/*      */   protected String icono()
/*      */   {
/*  353 */     return "/contaes/iconos/facturaGes18.png";
/*      */   }
/*      */ 
/*      */   public void actionPerformed(ActionEvent e)
/*      */   {
/*  358 */     String cmd = e.getActionCommand();
/*      */ 
/*  361 */     if ("seleccionar".equals(cmd)) {
/*  362 */       String numeroFactura = getNumeroFactura(this.selectedRow);
/*  363 */       if ((numeroFactura != null) && (!numeroFactura.equals("")))
/*  364 */         modificarFactura(numeroFactura);
/*      */     }
/*  366 */     else if ("borrar".equals(cmd)) {
/*  367 */       eliminarFacturas();
/*  368 */     } else if ("repetir".equals(cmd)) {
/*  369 */       if ((this.Buscar) && (!this.comienzo.equals("")))
/*  370 */         buscarDato(this.lastSearch);
/*      */     }
/*  372 */     else if ("imprimir".equals(cmd)) {
/*  373 */       imprimirTabla();
/*  374 */     } else if ("vistaprevia".equals(cmd)) {
/*  375 */       new PrintPreview(this);
/*  376 */     } else if ("fuentes".equals(cmd)) {
/*  377 */       String fuentePrevia = this.tabla.getFont().getFamily();
/*  378 */       int tamanoPrevio = this.tabla.getFont().getSize();
/*  379 */       MostrarFuentes dlg = new MostrarFuentes(Inicio.frame, Mensajes.getString("fuentes"), true, fuentePrevia, tamanoPrevio);
/*      */ 
/*  381 */       mostrarDialogo(dlg);
/*  382 */       if (dlg.isCambiar())
/*  383 */         this.tabla.setFont(new Font(dlg.getFuente(), 0, dlg.getTamano()));
/*      */     }
/*  385 */     else if ("exportar".equals(cmd)) {
/*  386 */       exportarA_archivo();
/*      */     }
/*      */     else
/*      */     {
/*      */       TablaToCsv object;
/*  387 */       if ("csv".equals(cmd)) {
/*  388 */         object = new TablaToCsv(this.model, "Facturación - ventas");
/*  389 */       } else if ("contabilizar".equals(cmd)) {
/*  390 */         contabilizarFacturas();
/*      */       }
/*      */       else {
/*  393 */         if ("recargo".equals(cmd)) {
/*  394 */           selRecargo();
/*  395 */           this.Accion = true;
/*  396 */         } else if ("contabilizadas".equals(cmd)) {
/*  397 */           selContabilizadas();
/*  398 */           this.Accion = true;
/*  399 */         } else if ("fecha".equals(cmd)) {
/*  400 */           dialogoSeleccion(true, 1);
/*  401 */           if ((this.Accion) && (this.Buscar))
/*  402 */             buscarDato(2);
/*      */           else
/*  404 */             this.datos.seleccionEstandar(this.comienzo, this.fin, 3);
/*      */         }
/*  406 */         else if ("cliente".equals(cmd)) {
/*  407 */           dialogoSeleccion(true, 0);
/*  408 */           if ((this.Accion) && (this.Buscar))
/*  409 */             buscarDato(3);
/*      */           else
/*  411 */             this.datos.seleccionEstandar(this.comienzo, this.fin, 2);
/*      */         }
/*  413 */         else if ("factura".equals(cmd)) {
/*  414 */           dialogoSeleccion(false, 2);
/*  415 */           if ((this.Accion) && (this.Buscar))
/*  416 */             buscarDato(1);
/*      */           else
/*  418 */             this.datos.seleccionTexto("f.numero", this.comienzo);
/*      */         }
/*  420 */         else if ("base".equals(cmd)) {
/*  421 */           dialogoSeleccion(true, 2);
/*  422 */           if ((this.Accion) && (this.Buscar))
/*  423 */             buscarDato(4);
/*      */           else
/*  425 */             this.datos.seleccionEstandar(this.comienzo, this.fin, 4);
/*      */         }
/*  427 */         else if ("impuestos".equals(cmd)) {
/*  428 */           dialogoSeleccion(true, 2);
/*  429 */           if ((this.Accion) && (this.Buscar))
/*  430 */             buscarDato(5);
/*      */           else
/*  432 */             this.datos.seleccionEstandar(this.comienzo, this.fin, 5);
/*      */         }
/*  434 */         else if ("base".equals(cmd)) {
/*  435 */           dialogoSeleccion(true, 2);
/*  436 */           if ((this.Accion) && (this.Buscar)) {
/*  437 */             buscarDato(6);
/*      */           }
/*      */         }
/*  440 */         else if ("completo".equals(cmd)) {
/*  441 */           this.Accion = true;
/*  442 */           this.datos.seleccionTotal();
/*      */         }
/*  444 */         if (this.Accion) {
/*  445 */           facturasRenovarTabla(true);
/*      */         }
/*  447 */         this.Accion = false;
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*  452 */   private void modificarFactura(String numeroFactura) { Inicio.p.setFacturacion(numeroFactura);
/*  453 */     Inicio.frame.modificarElemento(2); }
/*      */ 
/*      */   private void eliminarFactura(String numeroFactura)
/*      */   {
/*  457 */     Boolean isContabilizada = Boolean.valueOf(false);
/*      */     try {
/*  459 */       FacturaControl fC = new FacturaControl(Inicio.getCFacturacion(), this.ventas);
/*  460 */       Factura factura = fC.facturaEjercicio(numeroFactura);
/*  461 */       isContabilizada = Boolean.valueOf(factura.isContabilizada());
/*  462 */       ArrayList lineas = new ArrayList();
/*  463 */       if (factura != null) {
/*  464 */         fC.borrar(factura);
/*  465 */         fC.cerrarRs();
/*  466 */         LineaFacturaControl lFc = new LineaFacturaControl(Inicio.getCFacturacion(), this.ventas);
/*  467 */         lineas = lFc.lineas(factura.getId());
/*  468 */         for (LineaFactura lineaFactura : (List<LineaFactura>) lineas) {
/*  469 */           lFc.borrar(lineaFactura);
/*      */         }
/*  471 */         lFc.cerrarRs();
/*      */       }
/*  473 */       if (isContabilizada.booleanValue()) {
/*  474 */         GetInvoiceNumber dlg = new GetInvoiceNumber(Inicio.frame, true, true, factura.getNumero());
/*  475 */         mostrarDialogo(dlg);
/*  476 */         String temp = dlg.getNumeroFactura();
/*  477 */         if (!temp.equals("FALSE")) {
/*  478 */           ManejoFacturas mF = new ManejoFacturas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  479 */           mF.borrar(this.ventas, temp);
/*  480 */           mF.cerrarRs();
/*  481 */           AlmacenControl aC = new AlmacenControl();
/*  482 */           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/*  483 */           String date = sdf.format(factura.getFecha());
/*  484 */           for (LineaFactura linea : (List<LineaFactura>)lineas) {
/*  485 */             for (int i = 0; i < linea.getUnidades().intValue(); i++) {
/*  486 */               String referencia = linea.getIdProducto().getReferencia();
/*  487 */               double importe = linea.getBase().doubleValue() / linea.getUnidades().doubleValue();
/*  488 */               importe = doubleTwoDecimals(Double.valueOf(importe)).doubleValue();
/*  489 */               int io = linea.getBase().doubleValue() >= 0.0D ? -1 : 1;
/*  490 */               aC.suprimirPIO(referencia, date, importe, io);
/*      */             }
/*      */           }
/*  493 */           Inicio.frame.renovarTabla(5);
/*      */         }
/*      */       }
/*      */     }
/*      */     catch (Exception ex) {
/*  498 */       Logger.getLogger(TablaFacturas.class.getName()).log(Level.SEVERE, null, ex);
/*      */     }
/*      */   }
/*      */ 
/*      */   private Double doubleTwoDecimals(Double number) {
/*  503 */     if (number != null) {
/*  504 */       BigDecimal dec = new BigDecimal(number.doubleValue());
/*  505 */       return Double.valueOf(dec.setScale(2, 4).doubleValue());
/*      */     }
/*  507 */     return Double.valueOf(-1.0D);
/*      */   }
/*      */ 
/*      */   private void eliminarFacturas()
/*      */   {
/*  514 */     int borro = JOptionPane.showConfirmDialog(getContentPane(), "Seguro que desea suprimir las facturas seleccionadas", Mensajes.getString("confirma"), 0);
/*      */ 
/*  517 */     if (borro == 0) {
/*  518 */       for (int x = this.selectedRow; x <= this.lastSelectedRow; x++) {
/*  519 */         if (this.tabla.isRowSelected(x)) {
/*  520 */           String numeroFactura = getNumeroFactura(x);
/*  521 */           if ((numeroFactura != null) && (!numeroFactura.equals(""))) {
/*  522 */             eliminarFactura(numeroFactura);
/*      */           }
/*      */         }
/*      */       }
/*  526 */       facturasRenovarTabla(true);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void contabilizarFacturas() {
/*  531 */     ArrayList numeros = new ArrayList();
/*  532 */     for (int x = this.selectedRow; x <= this.lastSelectedRow; x++) {
/*  533 */       if (this.tabla.isRowSelected(x)) {
/*  534 */         String numeroFactura = getNumeroFactura(x);
/*  535 */         if ((numeroFactura != null) && (!numeroFactura.equals(""))) {
/*  536 */           numeros.add(numeroFactura);
/*      */         }
/*      */       }
/*      */     }
/*  540 */     if (!numeros.isEmpty()) {
/*  541 */       ContabilizarMultiplesFacturasJDialog dlg = new ContabilizarMultiplesFacturasJDialog(Inicio.frame, true, numeros, this.ventas);
/*      */ 
/*  543 */       Inicio.frame.mostrarDialogo(dlg);
/*  544 */       facturasRenovarTabla(true);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void addBotones(JToolBar toolBar) {
/*  549 */     JButton button = null;
/*      */ 
/*  552 */     button = makeNavigationButton("save", "exportar", Mensajes.getString("saveAstt"), Mensajes.getString("saveAs"));
/*      */ 
/*  555 */     toolBar.add(button);
/*      */ 
/*  557 */     button = makeNavigationButton("csv24", "csv", Mensajes.getString("saveAsCsvtt"), Mensajes.getString("saveAsCsv"));
/*      */ 
/*  560 */     toolBar.add(button);
/*  561 */     toolBar.addSeparator(new Dimension(5, 0));
/*      */ 
/*  564 */     button = makeNavigationButton("fonts", "fuentes", Mensajes.getString("selFonttt"), Mensajes.getString("fuentes"));
/*      */ 
/*  567 */     toolBar.add(button);
/*      */ 
/*  570 */     button = makeNavigationButton("print24", "imprimir", Mensajes.getString("printtt"), Mensajes.getString("print"));
/*      */ 
/*  573 */     toolBar.add(button);
/*      */ 
/*  575 */     button = makeNavigationButton("preview", "vistaprevia", Mensajes.getString("previewtt"), Mensajes.getString("preview"));
/*      */ 
/*  578 */     toolBar.add(button);
/*      */ 
/*  580 */     toolBar.addSeparator(new Dimension(20, 0));
/*      */ 
/*  583 */     button = makeNavigationButton("concepto", "factura", Mensajes.getString("selBuFactt"), Mensajes.getString("factura"));
/*      */ 
/*  586 */     toolBar.add(button);
/*  587 */     toolBar.addSeparator(new Dimension(10, 0));
/*      */ 
/*  589 */     button = makeNavigationButton("date32", "fecha", Mensajes.getString("selBuFechatt"), Mensajes.getString("fecha"));
/*      */ 
/*  592 */     toolBar.add(button);
/*  593 */     toolBar.addSeparator(new Dimension(10, 0));
/*      */ 
/*  596 */     button = makeNavigationButton("cuentas", "cliente", Mensajes.getString("selBuCtastt"), Mensajes.getString("cuentas"));
/*      */ 
/*  599 */     toolBar.add(button);
/*  600 */     toolBar.addSeparator(new Dimension(10, 0));
/*      */ 
/*  602 */     button = makeNavigationButton("importe", "base", "Selección-búsqueda por base imponible", Mensajes.getString("importe"));
/*      */ 
/*  605 */     toolBar.add(button);
/*  606 */     toolBar.addSeparator(new Dimension(10, 0));
/*      */ 
/*  608 */     button = makeNavigationButton("importe", "impuestos", "Selección-búsqueda por impuestos", Mensajes.getString("importe"));
/*      */ 
/*  611 */     toolBar.add(button);
/*  612 */     toolBar.addSeparator(new Dimension(10, 0));
/*      */ 
/*  614 */     button = makeNavigationButton("bell", "recargo", "Listar factuas con/sin recargo de equivalencia", "Recargo");
/*      */ 
/*  617 */     toolBar.add(button);
/*  618 */     toolBar.addSeparator(new Dimension(10, 0));
/*      */ 
/*  620 */     button = makeNavigationButton("bell", "contabilizadas", "Listar facturas contabilizadas o no contabilizadas", "Contabilizadas");
/*      */ 
/*  623 */     toolBar.add(button);
/*  624 */     toolBar.addSeparator(new Dimension(10, 0));
/*      */ 
/*  626 */     button = makeNavigationButton("retable", "completo", Mensajes.getString("listCompletott"), Mensajes.getString("completo"));
/*      */ 
/*  629 */     toolBar.add(button);
/*  630 */     toolBar.addSeparator(new Dimension(10, 0));
/*      */ 
/*  632 */     button = makeNavigationButton("refind", "repetir", Mensajes.getString("reFindtt"), Mensajes.getString("repetir"));
/*      */ 
/*  635 */     toolBar.add(button);
/*      */   }
/*      */ 
/*      */   private JButton makeNavigationButton(String imageName, String actionCommand, String toolTipText, String altText)
/*      */   {
/*  642 */     JButton button = makeNavigationButton(imageName, toolTipText, altText);
/*  643 */     button.setActionCommand(actionCommand);
/*  644 */     button.addActionListener(this);
/*  645 */     return button;
/*      */   }
/*      */ 
/*      */   private void selRecargo()
/*      */   {
/*  654 */     Object[] options = { "Con recargo", "Sin recargo" };
/*  655 */     int n = JOptionPane.showOptionDialog(getContentPane(), "Selección de facturas por Recargo de equivalencia", "Recargo de equivalencia", 0, 3, null, options, options[0]);
/*      */ 
/*  663 */     if (n == 0)
/*  664 */       this.datos.seleccionaRecargo(true);
/*      */     else
/*  666 */       this.datos.seleccionaRecargo(false);
/*      */   }
/*      */ 
/*      */   private void selContabilizadas()
/*      */   {
/*  671 */     Object[] options = { "Contabilizadas", "No contabilizadas" };
/*  672 */     int n = JOptionPane.showOptionDialog(getContentPane(), "Selección de facturas contabilizadas", "Facturas contabilizadas", 0, 3, null, options, options[0]);
/*      */ 
/*  680 */     if (n == 0)
/*  681 */       this.datos.seleccionaContabilizada(true);
/*      */     else
/*  683 */       this.datos.seleccionaContabilizada(false);
/*      */   }
/*      */ 
/*      */   private void buscarDato(int campo)
/*      */   {
/*  693 */     this.Accion = false;
/*  694 */     this.lastSearch = campo;
/*  695 */     String patron = this.comienzo.toUpperCase();
/*  696 */     int numFilas = this.tabla.getRowCount(); int x = 0;
/*  697 */     for (x = this.selectedRow + 1; x < numFilas; x++) {
/*  698 */       if (this.tabla.getValueAt(x, campo).toString().toUpperCase().indexOf(patron) != -1) {
/*  699 */         this.tabla.changeSelection(x, 0, false, false);
/*  700 */         break;
/*      */       }
/*      */     }
/*  703 */     if (x == numFilas) {
/*  704 */       int volver = JOptionPane.showConfirmDialog(getContentPane(), Mensajes.getString("finTabla"), Mensajes.getString("confirma"), 0);
/*      */ 
/*  707 */       if (volver == 0) {
/*  708 */         this.tabla.changeSelection(0, 0, false, false);
/*  709 */         if (this.tabla.getValueAt(0, campo).toString().toUpperCase().indexOf(patron) != -1) {
/*  710 */           return;
/*      */         }
/*  712 */         buscarDato(this.lastSearch);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private double totalImporte()
/*      */   {
/*  723 */     double suma = 0.0D;
/*  724 */     int numFilas = this.tabla.getRowCount();
/*  725 */     for (int x = 0; x < numFilas; x++) {
/*  726 */       String importe = this.tabla.getValueAt(x, 5).toString();
/*  727 */       suma += Double.parseDouble(importe);
/*      */     }
/*  729 */     return suma;
/*      */   }
/*      */ 
/*      */   private String getNumeroFactura(int fila)
/*      */   {
/*  739 */     String numero = null;
/*  740 */     if ((fila >= 0) && (fila < this.tabla.getRowCount())) {
/*  741 */       numero = this.tabla.getValueAt(fila, 0).toString();
/*      */     }
/*  743 */     return numero;
/*      */   }
/*      */ 
/*      */   private void facturasRenovarTabla(boolean fin) {
/*  747 */     if (this.datos.conexionCerrada()) {
/*  748 */       return;
/*      */     }
/*  750 */     this.tabla.removeAll();
/*      */     try {
/*  752 */       this.model = new ScrollableTableModel(this.datos.resultado(), this.nombres_col);
/*      */     } catch (SQLException e) {
/*  754 */       e.printStackTrace();
/*      */     }
/*  756 */     this.tablaOrdenable.setTableModel(this.model);
/*  757 */     this.tabla.setModel(this.tablaOrdenable);
/*  758 */     ListSelectionModel rowSM = this.tabla.getSelectionModel();
/*  759 */     rowSM.addListSelectionListener(new ListSelectionListener()
/*      */     {
/*      */       public void valueChanged(ListSelectionEvent arg0) {
/*  762 */         TablaFacturas.this.facturasSeleccionTabla(arg0);
/*      */       }
/*      */     });
/*  767 */     this.columna = this.tabla.getColumnModel().getColumn(1);
/*  768 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.2D));
/*  769 */     this.columna.setCellRenderer(new GeneralColorRenderer());
/*  770 */     this.columna = this.tabla.getColumnModel().getColumn(2);
/*  771 */     this.columna.setCellRenderer(new FechaColorRenderer());
/*  772 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.13D));
/*  773 */     this.columna = this.tabla.getColumnModel().getColumn(3);
/*  774 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.21D));
/*  775 */     this.columna.setCellRenderer(new GeneralColorRenderer());
/*  776 */     this.columna = this.tabla.getColumnModel().getColumn(4);
/*  777 */     this.columna.setCellRenderer(new ImporteColorRenderer());
/*  778 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.12D));
/*  779 */     this.columna = this.tabla.getColumnModel().getColumn(5);
/*  780 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.12D));
/*  781 */     this.columna.setCellRenderer(new ImporteColorRenderer());
/*  782 */     this.columna = this.tabla.getColumnModel().getColumn(6);
/*  783 */     this.columna.setCellRenderer(new ImporteColorRenderer());
/*  784 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.12D));
/*  785 */     this.columna = this.tabla.getColumnModel().getColumn(7);
/*  786 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.05D));
/*  787 */     this.columna.setCellRenderer(new BooleanColorRenderer());
/*  788 */     this.columna = this.tabla.getColumnModel().getColumn(8);
/*  789 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.05D));
/*  790 */     this.columna.setCellRenderer(new BooleanColorRenderer());
/*      */ 
/*  792 */     this.columna = this.tabla.getColumnModel().getColumn(0);
/*  793 */     this.tabla.removeColumn(this.columna);
/*      */ 
/*  795 */     this.total.setText(this.fn.format(totalImporte()));
/*  796 */     if (fin)
/*  797 */       this.tabla.changeSelection(this.tabla.getRowCount() - 1, 0, true, true);
/*      */   }
/*      */ 
/*      */   private void facturasSeleccionTabla(ListSelectionEvent e)
/*      */   {
/*  802 */     SeleccionTabla(e);
/*      */   }
/*      */ 
/*      */   private JMenuItem getJMenuItem1()
/*      */   {
/*  811 */     if (this.jMenuItem1 == null) {
/*  812 */       this.jMenuItem1 = new JMenuItem();
/*  813 */       this.jMenuItem1.setText(Mensajes.getString("modificar"));
/*  814 */       this.jMenuItem1.setIcon(this.iconoSelec);
/*  815 */       this.jMenuItem1.addActionListener(new ActionListener()
/*      */       {
/*      */         public void actionPerformed(ActionEvent e) {
/*  818 */           String numeroFactura = TablaFacturas.this.getNumeroFactura(TablaFacturas.this.selectedRow);
/*  819 */           if ((numeroFactura != null) && (!numeroFactura.equals(""))) {
/*  820 */             TablaFacturas.this.modificarFactura(numeroFactura);
/*      */           }
/*      */         }
/*      */       });
/*      */     }
/*  825 */     return this.jMenuItem1;
/*      */   }
/*      */ 
/*      */   private JMenuItem getJMenuItem2()
/*      */   {
/*  834 */     if (this.jMenuItem2 == null) {
/*  835 */       this.jMenuItem2 = new JMenuItem();
/*  836 */       this.jMenuItem2.setText(Mensajes.getString("borrar"));
/*  837 */       this.jMenuItem2.setIcon(this.iconoBorrar);
/*  838 */       this.jMenuItem2.addActionListener(new ActionListener()
/*      */       {
/*      */         public void actionPerformed(ActionEvent e) {
/*  841 */           TablaFacturas.this.eliminarFacturas();
/*      */         }
/*      */       });
/*      */     }
/*  845 */     return this.jMenuItem2;
/*      */   }
/*      */ 
/*      */   private void marcoActivado() {
/*  849 */     if (this.datos.conexionCerrada())
/*  850 */       this.datos.abrirConexion();
/*      */   }
/*      */ 
/*      */   private void marcoDesactivado()
/*      */   {
/*      */   }
/*      */ 
/*      */   public void renovarTabla() {
/*  858 */     facturasRenovarTabla(false);
/*      */   }
/*      */ 
/*      */   private void exportarA_archivo() {
/*  862 */     String EOL = FinLinea.get();
/*  863 */     AlinearCadena alinea = new AlinearCadena();
/*  864 */     JFileChooser fc = new JFileChooser();
/*  865 */     fc.setSelectedFile(new File("FacturacionVentas.txt"));
/*  866 */     int retorno = fc.showSaveDialog(Inicio.frame);
/*  867 */     if (retorno == 0) {
/*  868 */       FechaHoy hoy = new FechaHoy();
/*  869 */       File archivo = fc.getSelectedFile();
/*  870 */       archivo = AddExtension.txt(archivo);
/*  871 */       GrabarFichero salida = new GrabarFichero(archivo);
/*  872 */       salida.insertar("Facturación: listado de facturas    " + hoy.getFecha() + EOL);
/*      */ 
/*  874 */       int filas = this.tabla.getRowCount(); int columnas = this.tabla.getColumnCount();
/*  875 */       int[] x = new int[columnas];
/*  876 */       String cadena = "";
/*  877 */       for (int nCol = 0; nCol < columnas; nCol++) {
/*  878 */         boolean izq = true;
/*  879 */         this.columna = this.tabla.getColumnModel().getColumn(nCol);
/*  880 */         String titulo = (String)this.columna.getIdentifier();
/*  881 */         if (titulo.equals("")) {
/*  882 */           x[nCol] = 0;
/*  883 */         } else if (titulo.equals("Fecha")) {
/*  884 */           x[nCol] = 11;
/*  885 */         } else if (titulo.equals("Número")) {
/*  886 */           x[nCol] = 15;
/*  887 */         } else if (titulo.equals("Cliente")) {
/*  888 */           x[nCol] = 35;
/*  889 */         } else if (titulo.equals("Base")) {
/*  890 */           x[nCol] = 12;
/*  891 */           izq = false;
/*  892 */         } else if (titulo.equals("Impuestos")) {
/*  893 */           x[nCol] = 12;
/*  894 */           izq = false;
/*  895 */         } else if (titulo.equals("Total")) {
/*  896 */           x[nCol] = 12;
/*  897 */           izq = false;
/*  898 */         } else if (titulo.equals("Recargo")) {
/*  899 */           x[nCol] = 3;
/*  900 */         } else if (titulo.equals("Contabilizada")) {
/*  901 */           x[nCol] = 3;
/*      */         }
/*  903 */         if (izq)
/*  904 */           cadena = cadena + alinea.Izquierda(titulo, x[nCol]);
/*      */         else {
/*  906 */           cadena = cadena + alinea.Derecha(titulo, x[nCol]);
/*      */         }
/*      */       }
/*  909 */       salida.insertar(cadena + EOL);
/*  910 */       cadena = "";
/*  911 */       for (int nCol = 0; nCol < columnas; nCol++) {
/*  912 */         for (int nCarac = 0; nCarac < x[nCol]; nCarac++) {
/*  913 */           cadena = cadena + "-";
/*      */         }
/*      */       }
/*  916 */       salida.insertar(cadena + EOL);
/*      */ 
/*  918 */       for (int nRow = 0; nRow < filas; nRow++) {
/*  919 */         cadena = "";
/*  920 */         for (int nCol = 0; nCol < columnas; nCol++) {
/*  921 */           int col = this.tabla.getColumnModel().getColumn(nCol).getModelIndex();
/*  922 */           Object obj = this.tablaOrdenable.getValueAt(nRow, col);
/*  923 */           if ((obj instanceof Double)) {
/*  924 */             String str = this.fn.format(obj);
/*  925 */             cadena = cadena + alinea.Derecha(str, x[nCol]);
/*  926 */           } else if ((obj instanceof Date)) {
/*  927 */             String cad = obj.toString();
/*  928 */             int year = Integer.parseInt(cad.substring(0, 4));
/*  929 */             int month = Integer.parseInt(cad.substring(5, 7)) - 1;
/*  930 */             int date = Integer.parseInt(cad.substring(8));
/*  931 */             GregorianCalendar calendario = new GregorianCalendar();
/*  932 */             calendario.set(year, month, date);
/*  933 */             SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
/*  934 */             String str = sdf.format(calendario.getTime());
/*  935 */             cadena = cadena + alinea.Izquierda(str, x[nCol]);
/*      */           } else {
/*  937 */             String str = obj.toString();
/*  938 */             if (str.equals("false")) {
/*  939 */               str = " " + Mensajes.getString("no");
/*      */             }
/*  941 */             if (str.equals("true")) {
/*  942 */               str = " " + Mensajes.getString("yes");
/*      */             }
/*  944 */             cadena = cadena + alinea.Izquierda(str, x[nCol]);
/*      */           }
/*      */         }
/*  947 */         salida.insertar(cadena + EOL);
/*      */       }
/*  949 */       salida.insertar(EOL);
/*  950 */       salida.insertar(alinea.Derecha("Total:   " + this.fn.format(totalImporte()), 82));
/*  951 */       salida.cerrar();
/*      */     }
/*      */   }
/*      */ 
/*      */   private void imprimirTabla() {
/*  956 */     PrinterJob printerJob = PrinterJob.getPrinterJob();
/*  957 */     PageFormat userFormat = printerJob.pageDialog(printerJob.defaultPage());
/*  958 */     printerJob.setPrintable(this, userFormat);
/*  959 */     if (printerJob.printDialog()) {
/*  960 */       this.m_maxNumPage = 1;
/*  961 */       setCursor(Cursor.getPredefinedCursor(3));
/*      */       try
/*      */       {
/*  964 */         printerJob.print();
/*      */       } catch (PrinterException e) {
/*  966 */         System.err.println(Mensajes.getString("errPrint") + ": " + e);
/*      */       }
/*  968 */       setCursor(Cursor.getPredefinedCursor(0));
/*      */     }
/*      */   }
/*      */ 
/*      */   public int print(Graphics pg, PageFormat pageFormat, int pageIndex)
/*      */     throws PrinterException
/*      */   {
/*  975 */     if (pageIndex >= this.m_maxNumPage) {
/*  976 */       return 1;
/*      */     }
/*      */ 
/*  979 */     FechaHoy hoy = new FechaHoy();
/*  980 */     int anchoImporte = 0;
/*      */ 
/*  982 */     pg.translate((int)pageFormat.getImageableX(), (int)pageFormat.getImageableY());
/*      */ 
/*  984 */     int wPage = 0;
/*  985 */     int hPage = 0;
/*  986 */     if (pageFormat.getOrientation() == 1) {
/*  987 */       wPage = (int)pageFormat.getImageableWidth();
/*  988 */       hPage = (int)pageFormat.getImageableHeight();
/*      */     } else {
/*  990 */       wPage = (int)pageFormat.getImageableWidth();
/*  991 */       wPage += wPage / 2;
/*  992 */       hPage = (int)pageFormat.getImageableHeight();
/*  993 */       pg.setClip(0, 0, wPage, hPage);
/*      */     }
/*  995 */     int y = 0;
/*      */ 
/*  997 */     pg.setFont(this.tabla.getFont().deriveFont(1, this.tabla.getFont().getSize2D() + 2.0F));
/*  998 */     pg.setColor(Color.black);
/*      */ 
/* 1000 */     FontMetrics fm = pg.getFontMetrics();
/* 1001 */     y += fm.getAscent();
/* 1002 */     pg.drawString(getTitle() + "     " + hoy.getFecha(), 0, y);
/* 1003 */     y += 20;
/* 1004 */     Font headerFont = this.tabla.getFont().deriveFont(1);
/* 1005 */     pg.setFont(headerFont);
/* 1006 */     fm = pg.getFontMetrics();
/*      */ 
/* 1008 */     TableColumnModel colModel = this.tabla.getColumnModel();
/* 1009 */     int nColumns = colModel.getColumnCount();
/* 1010 */     int[] x = new int[nColumns];
/* 1011 */     x[0] = 0;
/*      */ 
/* 1013 */     int h = fm.getAscent();
/* 1014 */     y += h;
/*      */ 
/* 1018 */     for (int nCol = 0; nCol < nColumns; nCol++) {
/* 1019 */       TableColumn tk = colModel.getColumn(nCol);
/* 1020 */       int width = tk.getWidth();
/* 1021 */       if (x[nCol] + width > wPage) {
/* 1022 */         nColumns = nCol;
/* 1023 */         break;
/*      */       }
/* 1025 */       if (nCol + 1 < nColumns) {
/* 1026 */         x[(nCol + 1)] = (x[nCol] + width);
/*      */       }
/* 1028 */       String titulo = (String)tk.getIdentifier();
/* 1029 */       if ((titulo.equals("Base")) || (titulo.equals("Impuestos")) || (titulo.equals("Total"))) {
/* 1030 */         anchoImporte = tk.getWidth() * 80 / 100;
/*      */       }
/* 1032 */       pg.drawString(titulo, x[nCol], y);
/*      */     }
/*      */ 
/* 1035 */     pg.setFont(this.tabla.getFont());
/* 1036 */     fm = pg.getFontMetrics();
/*      */ 
/* 1038 */     int header = y;
/* 1039 */     h = fm.getHeight();
/* 1040 */     int rowH = Math.max((int)(h * 1.5D), 10);
/* 1041 */     int rowPerPage = (hPage - header) / rowH;
/* 1042 */     this.m_maxNumPage = Math.max((int)Math.ceil(this.tabla.getRowCount() / rowPerPage), 1);
/*      */ 
/* 1046 */     int iniRow = pageIndex * rowPerPage;
/* 1047 */     int endRow = Math.min(this.tabla.getRowCount(), iniRow + rowPerPage);
/*      */ 
/* 1050 */     for (int nRow = iniRow; nRow < endRow; nRow++) {
/* 1051 */       y += h;
/* 1052 */       for (int nCol = 0; nCol < nColumns; nCol++) {
/* 1053 */         pg.setColor(Color.black);
/* 1054 */         int col = this.tabla.getColumnModel().getColumn(nCol).getModelIndex();
/* 1055 */         Object obj = this.tablaOrdenable.getValueAt(nRow, col);
/* 1056 */         String str = "";
/* 1057 */         if (obj != null) {
/* 1058 */           str = obj.toString();
/*      */         }
/* 1060 */         if (str.equals("false")) {
/* 1061 */           str = Mensajes.getString("no");
/*      */         }
/* 1063 */         if (str.equals("true")) {
/* 1064 */           str = Mensajes.getString("yes");
/*      */         }
/* 1066 */         if ((obj instanceof Double)) {
/* 1067 */           str = this.fn.format(obj);
/*      */ 
/* 1069 */           FontRenderContext frc = ((Graphics2D)pg).getFontRenderContext();
/* 1070 */           TextLayout layout = new TextLayout(str, this.tabla.getFont(), frc);
/* 1071 */           float w = layout.getAdvance();
/* 1072 */           pg.drawString(str, x[nCol] + (anchoImporte - (int)w), y);
/* 1073 */         } else if ((obj instanceof Date)) {
/* 1074 */           String cad = obj.toString();
/* 1075 */           int year = Integer.parseInt(cad.substring(0, 4));
/* 1076 */           int month = Integer.parseInt(cad.substring(5, 7)) - 1;
/* 1077 */           int date = Integer.parseInt(cad.substring(8));
/* 1078 */           GregorianCalendar calendario = new GregorianCalendar();
/* 1079 */           calendario.set(year, month, date);
/* 1080 */           SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
/* 1081 */           str = sdf.format(calendario.getTime());
/* 1082 */           pg.drawString(str, x[nCol], y);
/*      */         } else {
/* 1084 */           pg.drawString(str, x[nCol], y);
/*      */         }
/*      */       }
/*      */     }
/* 1088 */     if (pageIndex == this.m_maxNumPage - 1) {
/* 1089 */       y += 20;
/* 1090 */       pg.drawString(Mensajes.getString("total") + ":", 250, y);
/* 1091 */       pg.drawString(this.total.getText(), 310, y);
/*      */     }
/* 1093 */     System.gc();
/* 1094 */     return 0;
/*      */   }
/*      */ 
/*      */   public void cerrarConexion()
/*      */   {
/* 1099 */     if (!this.datos.conexionCerrada())
/* 1100 */       this.datos.cerrarConexion();
/*      */   }
/*      */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     facturacion.view.TablaFacturas
 * JD-Core Version:    0.6.2
 */