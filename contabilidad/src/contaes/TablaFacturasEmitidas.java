/*      */ package contaes;
/*      */ 
/*      */ import contaes.auxiliar.AlinearCadena;
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
/*      */ import contaes.dialogosAuxiliares.PagoTarjeta;
/*      */ import contaes.dialogosAuxiliares.PrintPreview;
/*      */ import contaes.manejoDatos.ManejoApuntes;
/*      */ import contaes.manejoDatos.ManejoAsientos;
/*      */ import contaes.manejoDatos.ManejoFacturas;
/*      */ import contaes.manejoDatos.ManejoSubcuentas;
/*      */ import contaes.manejoDatos.ManejoTablaFacturasEmitidas;
/*      */ import contaes.manejoDatos.TipoFactura;
/*      */ import contaes.manejoDatos.TipoSubcuenta;
/*      */ import contaes.manejoDatos.auxiliar.AddExtension;
/*      */ import contaes.manejoDatos.auxiliar.FechaHoy;
/*      */ import contaes.manejoDatos.auxiliar.FinLinea;
/*      */ import contaes.manejoDatos.auxiliar.GrabarFichero;
/*      */ import contaes.manejoDatos.auxiliar.LeerFichero;
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
/*      */ import java.io.IOException;
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
/*      */ public class TablaFacturasEmitidas extends MarcoInternoTablas
/*      */   implements Printable, ActionListener
/*      */ {
/*      */   private static final String FACTURA = "factura";
/*      */   private static final String FECHA = "fecha";
/*      */   private static final String CUENTA = "cuenta";
/*      */   private static final String ASIENTO = "asiento";
/*      */   private static final String BASE = "base";
/*      */   private static final String IVA = "iva";
/*      */   private static final String TOTAL = "total";
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
/*  141 */   private JPopupMenu menuEmergente = new JPopupMenu();
/*  142 */   private JMenuItem jMenuItem = null;
/*  143 */   private JMenuItem jMenuItem1 = null;
/*  144 */   private JMenuItem jMenuItem2 = null;
/*  145 */   private JMenuItem jMenuItem3 = null;
/*      */ 
/*  147 */   private BorderLayout borderLayout1 = new BorderLayout();
/*  148 */   private GridBagLayout gbLayout = new GridBagLayout();
/*  149 */   private JToolBar barra = new JToolBar();
/*  150 */   private JScrollPane scrollPane = new JScrollPane();
/*  151 */   private JPanel datosCuentas = new JPanel();
/*  152 */   private JButton crearVencimientos = new JButton();
/*  153 */   private JButton borrar = new JButton();
/*  154 */   private JButton pagar = new JButton();
/*  155 */   private JButton modificar = new JButton();
/*  156 */   private JCheckBox queFecha = new JCheckBox(Mensajes.getString("ponfechahoy"));
/*  157 */   private JLabel totalE = new JLabel(Mensajes.getString("total") + ":");
/*  158 */   private JTextField total = new JTextField();
/*  159 */   private TableSorter tabla_ord = new TableSorter();
/*      */   private JTable tabla;
/*  161 */   private TableColumn columna = null;
/*      */   private ManejoTablaFacturasEmitidas datos;
/*      */   private ScrollableTableModel model;
/*  164 */   private JLabel infoCuenta = new JLabel();
/*  165 */   private ImageIcon iconoCrear = createImageIcon("/contaes/iconos/date.png");
/*  166 */   private ImageIcon iconoBorrar = createImageIcon("/contaes/iconos/delete.png");
/*  167 */   private ImageIcon iconoPagar = createImageIcon("/contaes/iconos/importe16.png");
/*  168 */   private ImageIcon iconoModificar = createImageIcon("/contaes/iconos/edit.png");
/*      */ 
/*      */   public TablaFacturasEmitidas() {
/*  171 */     this(Mensajes.getString("menu31b1"));
/*      */   }
/*      */ 
/*      */   public TablaFacturasEmitidas(String title) {
/*  175 */     super(title);
/*  176 */     this.datos = new ManejoTablaFacturasEmitidas(Inicio.p.getEmpresa(), Inicio.p.getEjercicio());
/*      */     try {
/*  178 */       initialize();
/*  179 */       this.total.setText(this.fn.format(totalImporte()));
/*      */     } catch (Exception exception) {
/*  181 */       exception.printStackTrace();
/*      */     }
/*      */   }
/*      */ 
/*      */   private void initialize() throws Exception {
/*  186 */     DecimalFormatSymbols formato = new DecimalFormatSymbols();
/*  187 */     formato.setDecimalSeparator(',');
/*  188 */     formato.setPerMill('.');
/*  189 */     this.fn = new DecimalFormat("#,###,##0.00", formato);
/*      */ 
/*  191 */     this.menuEmergente.add(getJMenuItem3());
/*  192 */     this.menuEmergente.add(getJMenuItem());
/*  193 */     this.menuEmergente.add(getJMenuItem1());
/*  194 */     this.menuEmergente.add(getJMenuItem2());
/*      */ 
/*  196 */     setFrameIcon(new ImageIcon(getClass().getResource(icono())));
/*  197 */     Rectangle parentBounds = Inicio.frame.localizacion();
/*  198 */     int ancho = parentBounds.width < 900 ? parentBounds.width : 790;
/*  199 */     int alto = parentBounds.height < 580 ? parentBounds.height : 530;
/*  200 */     setBounds(10, 50, ancho, alto);
/*  201 */     getContentPane().setLayout(this.borderLayout1);
/*  202 */     addInternalFrameListener(new InternalFrameAdapter()
/*      */     {
/*      */       public void internalFrameOpened(InternalFrameEvent arg0)
/*      */       {
/*  206 */         super.internalFrameOpened(arg0);
/*  207 */         TablaFacturasEmitidas.this.marcoActivado();
/*      */       }
/*      */ 
/*      */       public void internalFrameClosing(InternalFrameEvent e)
/*      */       {
/*  212 */         TablaFacturasEmitidas.this.datos.cerrarConexion();
/*  213 */         super.internalFrameClosing(e);
/*      */       }
/*      */ 
/*      */       public void internalFrameDeactivated(InternalFrameEvent e)
/*      */       {
/*  218 */         TablaFacturasEmitidas.this.marcoDesactivado();
/*      */       }
/*      */ 
/*      */       public void internalFrameActivated(InternalFrameEvent e)
/*      */       {
/*  223 */         TablaFacturasEmitidas.this.marcoActivado();
/*      */       }
/*      */ 
/*      */       public void internalFrameClosed(InternalFrameEvent e)
/*      */       {
/*  228 */         Inicio.frame.eliminarMarcoMenu(TablaFacturasEmitidas.this.getTitle());
/*      */       }
/*      */     });
/*  232 */     this.nombres_col.add(Mensajes.getString("numero"));
/*  233 */     this.nombres_col.add(Mensajes.getString("fecha"));
/*  234 */     this.nombres_col.add(Mensajes.getString("cuenta"));
/*  235 */     this.nombres_col.add(Mensajes.getString("asiento"));
/*  236 */     this.nombres_col.add(Mensajes.getString("concepto"));
/*  237 */     this.nombres_col.add(Mensajes.getString("base"));
/*  238 */     this.nombres_col.add(Mensajes.getString("IVAs"));
/*  239 */     this.nombres_col.add(Mensajes.getString("total"));
/*      */ 
/*  241 */     this.model = new ScrollableTableModel(this.datos.resultado(), this.nombres_col);
/*  242 */     this.tabla_ord.setTableModel(this.model);
/*  243 */     this.tabla = new JTable(this.tabla_ord);
/*  244 */     this.tabla_ord.setTableHeader(this.tabla.getTableHeader());
/*  245 */     this.tabla.getTableHeader().setToolTipText(Mensajes.getString("tableHeadertt"));
/*  246 */     this.tabla.addMouseListener(new MouseAdapter()
/*      */     {
/*      */       public void mouseClicked(MouseEvent e)
/*      */       {
/*  250 */         if (e.getButton() == 3) {
/*  251 */           TablaFacturasEmitidas.this.tabla.changeSelection(TablaFacturasEmitidas.this.tabla.rowAtPoint(new Point(e.getX(), e.getY())), TablaFacturasEmitidas.this.tabla.columnAtPoint(new Point(e.getX(), e.getY())), false, false);
/*      */ 
/*  255 */           TablaFacturasEmitidas.this.menuEmergente.show(e.getComponent(), e.getX(), e.getY());
/*      */         }
/*      */       }
/*      */     });
/*  260 */     ListSelectionModel rowSM = this.tabla.getSelectionModel();
/*  261 */     rowSM.addListSelectionListener(new ListSelectionListener()
/*      */     {
/*      */       public void valueChanged(ListSelectionEvent arg0) {
/*  264 */         TablaFacturasEmitidas.this.facturasSeleccionTabla(arg0);
/*      */       }
/*      */     });
/*  267 */     this.columna = this.tabla.getColumnModel().getColumn(0);
/*  268 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.12D));
/*  269 */     this.columna.setCellRenderer(new DerechaColorRenderer());
/*  270 */     this.columna = this.tabla.getColumnModel().getColumn(1);
/*  271 */     this.columna.setCellRenderer(new FechaColorRenderer());
/*  272 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.12D));
/*  273 */     this.columna = this.tabla.getColumnModel().getColumn(2);
/*  274 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.12D));
/*  275 */     this.columna.setCellRenderer(new DerechaColorRenderer());
/*  276 */     this.columna = this.tabla.getColumnModel().getColumn(3);
/*      */ 
/*  278 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.08D));
/*  279 */     this.columna.setCellRenderer(new DerechaColorRenderer());
/*  280 */     this.columna = this.tabla.getColumnModel().getColumn(4);
/*  281 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.2D));
/*  282 */     this.columna = this.tabla.getColumnModel().getColumn(5);
/*  283 */     this.columna.setCellRenderer(new ImporteColorRenderer());
/*  284 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.12D));
/*  285 */     this.columna = this.tabla.getColumnModel().getColumn(6);
/*  286 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.12D));
/*  287 */     this.columna.setCellRenderer(new ImporteColorRenderer());
/*  288 */     this.columna = this.tabla.getColumnModel().getColumn(7);
/*  289 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.12D));
/*  290 */     this.columna.setCellRenderer(new ImporteColorRenderer());
/*  291 */     this.tabla.setDefaultRenderer(Object.class, new GeneralColorRenderer());
/*      */ 
/*  293 */     this.scrollPane.getViewport().add(this.tabla);
/*  294 */     this.scrollPane.setBorder(new CompoundBorder(BorderFactory.createLoweredBevelBorder(), BorderFactory.createMatteBorder(20, 20, 20, 20, Color.white)));
/*      */ 
/*  297 */     addBotones(this.barra);
/*  298 */     this.barra.setRollover(true);
/*      */ 
/*  300 */     this.crearVencimientos.setText(Mensajes.getString("vencimientos"));
/*  301 */     this.crearVencimientos.setIcon(this.iconoCrear);
/*  302 */     this.crearVencimientos.setVerticalTextPosition(0);
/*  303 */     this.crearVencimientos.setHorizontalTextPosition(2);
/*  304 */     this.crearVencimientos.setActionCommand("crear");
/*  305 */     this.crearVencimientos.addActionListener(this);
/*  306 */     this.borrar.setText(Mensajes.getString("borrar"));
/*  307 */     this.borrar.setIcon(this.iconoBorrar);
/*  308 */     this.borrar.setVerticalTextPosition(0);
/*  309 */     this.borrar.setHorizontalTextPosition(2);
/*  310 */     this.borrar.setActionCommand("borrar");
/*  311 */     this.borrar.addActionListener(this);
/*  312 */     this.pagar.setText(sPagar());
/*  313 */     this.pagar.setIcon(this.iconoPagar);
/*  314 */     this.pagar.setVerticalTextPosition(0);
/*  315 */     this.pagar.setHorizontalTextPosition(2);
/*  316 */     this.pagar.setActionCommand("pagar");
/*  317 */     this.pagar.addActionListener(this);
/*  318 */     this.modificar.setText(Mensajes.getString("modificar"));
/*  319 */     this.modificar.setIcon(this.iconoModificar);
/*  320 */     this.modificar.setVerticalTextPosition(0);
/*  321 */     this.modificar.setHorizontalTextPosition(2);
/*  322 */     this.modificar.setActionCommand("modificar");
/*  323 */     this.modificar.addActionListener(this);
/*  324 */     this.datosCuentas.setBorder(BorderFactory.createEmptyBorder(5, 50, 10, 50));
/*  325 */     this.datosCuentas.setLayout(this.gbLayout);
/*  326 */     this.total.setEditable(false);
/*  327 */     this.total.setHorizontalAlignment(4);
/*  328 */     GridBagConstraints cons = new GridBagConstraints();
/*  329 */     cons.gridy = 0;
/*  330 */     cons.gridx = 0;
/*  331 */     cons.gridwidth = 2;
/*  332 */     this.gbLayout.setConstraints(this.infoCuenta, cons);
/*  333 */     this.datosCuentas.add(this.infoCuenta);
/*      */ 
/*  335 */     cons.gridwidth = 1;
/*  336 */     cons.gridx = 2;
/*  337 */     cons.anchor = 10;
/*  338 */     cons.insets.right = 30;
/*  339 */     cons.insets.left = 30;
/*  340 */     this.gbLayout.setConstraints(this.totalE, cons);
/*  341 */     this.datosCuentas.add(this.totalE);
/*      */ 
/*  343 */     cons.gridx = 3;
/*  344 */     cons.gridwidth = 2;
/*  345 */     cons.fill = 2;
/*  346 */     cons.insets.bottom = 5;
/*  347 */     this.gbLayout.setConstraints(this.total, cons);
/*  348 */     this.datosCuentas.add(this.total);
/*      */ 
/*  350 */     cons.gridwidth = 1;
/*  351 */     cons.gridx = 0;
/*  352 */     cons.gridy = 1;
/*  353 */     cons.fill = 0;
/*  354 */     cons.insets.bottom = 0;
/*  355 */     cons.insets.right = 30;
/*  356 */     cons.insets.left = 30;
/*  357 */     this.gbLayout.setConstraints(this.modificar, cons);
/*  358 */     this.datosCuentas.add(this.modificar);
/*      */ 
/*  360 */     cons.gridx = 1;
/*  361 */     this.gbLayout.setConstraints(this.borrar, cons);
/*  362 */     this.datosCuentas.add(this.borrar);
/*      */ 
/*  364 */     cons.gridx = 2;
/*  365 */     this.gbLayout.setConstraints(this.crearVencimientos, cons);
/*  366 */     this.datosCuentas.add(this.crearVencimientos);
/*      */ 
/*  368 */     cons.gridx = 3;
/*  369 */     cons.insets.right = 10;
/*  370 */     this.gbLayout.setConstraints(this.pagar, cons);
/*  371 */     this.datosCuentas.add(this.pagar);
/*      */ 
/*  373 */     cons.gridx = 4;
/*  374 */     cons.insets.left = 5;
/*      */ 
/*  376 */     this.gbLayout.setConstraints(this.queFecha, cons);
/*  377 */     this.datosCuentas.add(this.queFecha);
/*      */ 
/*  379 */     getContentPane().add(this.scrollPane, "Center");
/*  380 */     getContentPane().add(this.barra, "First");
/*  381 */     getContentPane().add(this.datosCuentas, "Last");
/*  382 */     this.tabla.changeSelection(this.tabla.getRowCount() - 1, 0, true, true);
/*      */   }
/*      */ 
/*      */   protected boolean esEmitida()
/*      */   {
/*  391 */     return true;
/*      */   }
/*      */ 
/*      */   protected String sCuentaAbono()
/*      */   {
/*  400 */     return Mensajes.getString("cuentaCargo");
/*      */   }
/*      */ 
/*      */   protected String sAcreedor()
/*      */   {
/*  409 */     return Mensajes.getString("Deudor");
/*      */   }
/*      */ 
/*      */   protected String sPagado()
/*      */   {
/*  418 */     return Mensajes.getString("cobrado");
/*      */   }
/*      */ 
/*      */   protected String sPagar()
/*      */   {
/*  427 */     return Mensajes.getString("cobrar");
/*      */   }
/*      */ 
/*      */   protected String sPago()
/*      */   {
/*  436 */     return Mensajes.getString("cobro");
/*      */   }
/*      */ 
/*      */   protected String icono()
/*      */   {
/*  445 */     return "/contaes/iconos/gestionEmitidas18.png";
/*      */   }
/*      */ 
/*      */   protected String marca()
/*      */   {
/*  454 */     return "Q";
/*      */   }
/*      */ 
/*      */   public void actionPerformed(ActionEvent e) {
/*  458 */     String cmd = e.getActionCommand();
/*      */ 
/*  461 */     if ("crear".equals(cmd)) {
/*  462 */       CrearVencimiento();
/*  463 */     } else if ("borrar".equals(cmd)) {
/*  464 */       eliminarFactura();
/*  465 */     } else if ("pagar".equals(cmd)) {
/*  466 */       pagarCobrarFactura();
/*  467 */     } else if ("modificar".equals(cmd)) {
/*  468 */       modificarFactura();
/*  469 */     } else if ("repetir".equals(cmd)) {
/*  470 */       if ((this.Buscar) && (!this.comienzo.equals("")))
/*  471 */         buscarDato(this.lastSearch);
/*      */     }
/*  473 */     else if ("imprimir".equals(cmd)) {
/*  474 */       imprimirTabla();
/*  475 */     } else if ("vistaprevia".equals(cmd)) {
/*  476 */       new PrintPreview(this);
/*  477 */     } else if ("fuentes".equals(cmd)) {
/*  478 */       String fuentePrevia = this.tabla.getFont().getFamily();
/*  479 */       int tamanoPrevio = this.tabla.getFont().getSize();
/*  480 */       MostrarFuentes dlg = new MostrarFuentes(Inicio.frame, Mensajes.getString("fuentes"), true, fuentePrevia, tamanoPrevio);
/*      */ 
/*  482 */       mostrarDialogo(dlg);
/*  483 */       if (dlg.isCambiar())
/*  484 */         this.tabla.setFont(new Font(dlg.getFuente(), 0, dlg.getTamano()));
/*      */     }
/*  486 */     else if ("exportar".equals(cmd)) {
/*  487 */       exportarA_archivo();
/*      */     }
/*      */     else
/*      */     {
/*      */       TablaToCsv object;
/*  488 */       if ("csv".equals(cmd)) {
/*  489 */         object = new TablaToCsv(this.model, "Facturas emitidas");
/*      */       }
/*      */       else
/*      */       {
/*  496 */         if ("factura".equals(cmd)) {
/*  497 */           dialogoSeleccion(false, 2);
/*  498 */           if ((this.Accion) && (this.Buscar))
/*  499 */             buscarDato(0);
/*      */           else
/*  501 */             this.datos.seleccionTexto("f.numero", this.comienzo);
/*      */         }
/*  503 */         else if ("fecha".equals(cmd)) {
/*  504 */           dialogoSeleccion(true, 1);
/*  505 */           if ((this.Accion) && (this.Buscar))
/*  506 */             buscarDato(1);
/*      */           else
/*  508 */             this.datos.seleccionEstandar(this.comienzo, this.fin, 3);
/*      */         }
/*  510 */         else if ("cuenta".equals(cmd)) {
/*  511 */           dialogoSeleccion(true, 0);
/*  512 */           if ((this.Accion) && (this.Buscar))
/*  513 */             buscarDato(2);
/*      */           else
/*  515 */             this.datos.seleccionEstandar(this.comienzo, this.fin, 2);
/*      */         }
/*  517 */         else if ("asiento".equals(cmd)) {
/*  518 */           dialogoSeleccion(true, 2);
/*  519 */           if ((this.Accion) && (this.Buscar))
/*  520 */             buscarDato(3);
/*      */           else
/*  522 */             this.datos.seleccionEstandar(this.comienzo, this.fin, 1);
/*      */         }
/*  524 */         else if ("base".equals(cmd)) {
/*  525 */           dialogoSeleccion(true, 2);
/*  526 */           if ((this.Accion) && (this.Buscar))
/*  527 */             buscarDato(5);
/*      */           else
/*  529 */             this.datos.seleccionEstandar(this.comienzo, this.fin, 4);
/*      */         }
/*  531 */         else if ("iva".equals(cmd)) {
/*  532 */           dialogoSeleccion(true, 2);
/*  533 */           if ((this.Accion) && (this.Buscar))
/*  534 */             buscarDato(6);
/*      */           else
/*  536 */             this.datos.seleccionEstandar(this.comienzo, this.fin, 5);
/*      */         }
/*  538 */         else if ("total".equals(cmd)) {
/*  539 */           dialogoSeleccion(true, 2);
/*  540 */           if ((this.Accion) && (this.Buscar))
/*  541 */             buscarDato(7);
/*      */           else
/*  543 */             this.datos.seleccionEstandar(this.comienzo, this.fin, 6);
/*      */         }
/*  545 */         else if ("completo".equals(cmd)) {
/*  546 */           this.Accion = true;
/*  547 */           this.datos.seleccionTotal();
/*      */         }
/*  549 */         if (this.Accion) {
/*  550 */           refrescarTabla(true);
/*      */         }
/*  552 */         this.Accion = false;
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*  557 */   private void CrearVencimiento() { SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
/*  558 */     String fecha_bd = sdf.format(fechaPago());
/*  559 */     if (this.selectedRow != -1) {
/*  560 */       String numero = this.tabla_ord.getValueAt(this.selectedRow, 0).toString();
/*  561 */       String cuenta = this.tabla_ord.getValueAt(this.selectedRow, 2).toString();
/*  562 */       double totalFactura = ((Double)this.tabla_ord.getValueAt(this.selectedRow, 7)).doubleValue();
/*  563 */       TipoFactura factura = new TipoFactura();
/*  564 */       factura.setNumero(numero);
/*  565 */       factura.setFecha(fecha_bd);
/*  566 */       factura.setTotal(totalFactura);
/*  567 */       factura.setCuenta(Integer.parseInt(cuenta));
/*  568 */       GenerarVencimientos crear = new GenerarVencimientos(Inicio.frame, Mensajes.getString("menu35"), true, factura, !esEmitida());
/*      */ 
/*  571 */       Dimension dlgSize = crear.getSize();
/*  572 */       Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/*  573 */       crear.setLocation((screenSize.width - dlgSize.width) / 2, (screenSize.height - dlgSize.height) / 2);
/*      */ 
/*  575 */       crear.setVisible(true);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void pagarCobrarFactura()
/*      */   {
/*  582 */     Date fechaDePago = this.queFecha.isSelected() ? new Date() : fechaPago();
/*  583 */     PagaVencimientos dlg = new PagaVencimientos(Inicio.frame, Mensajes.getString("fecha") + " y " + Mensajes.getString("ctaPago"), fechaDePago, true);
/*  584 */     mostrarDialogo(dlg);
/*  585 */     int cuentaPago = dlg.enCuenta();
/*  586 */     if (cuentaPago != -1) {
/*  587 */       String fechaPago = dlg.enfecha();
/*      */ 
/*  589 */       for (int x = this.selectedRow; x <= this.lastSelectedRow; x++)
/*  590 */         if (this.tabla.isRowSelected(x)) {
/*  591 */           String num = id_fila(x);
/*  592 */           if (!num.equals("Error")) {
/*  593 */             int cuenta = Integer.parseInt(this.tabla_ord.getValueAt(x, 2).toString());
/*  594 */             double importe = Double.parseDouble(this.tabla_ord.getValueAt(x, 7).toString());
/*  595 */             pagarFactura(num, fechaPago, cuenta, cuentaPago, importe);
/*      */           }
/*      */         }
/*      */     }
/*      */   }
/*      */ 
/*      */   private void eliminarFactura()
/*      */   {
/*  603 */     int borro = JOptionPane.showConfirmDialog(getContentPane(), Mensajes.getString("confBorrarFacturas"), Mensajes.getString("confirma"), 0);
/*      */ 
/*  606 */     if (borro == 0) {
/*  607 */       for (int x = this.selectedRow; x <= this.lastSelectedRow; x++) {
/*  608 */         if (this.tabla.isRowSelected(x)) {
/*  609 */           String num = id_fila(x);
/*  610 */           if (!num.equals("Error")) {
/*  611 */             borrarFactura(num);
/*      */           }
/*      */         }
/*      */       }
/*  615 */       refrescarTabla(true);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void modificarFactura()
/*      */   {
/*  621 */     if (this.selectedRow != -1) {
/*  622 */       String num = "E" + id_fila(this.selectedRow);
/*  623 */       Inicio.p.setFactura(num);
/*  624 */       Inicio.frame.modificarElemento(1);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void addBotones(JToolBar toolBar)
/*      */   {
/*  647 */     JButton button = null;
/*      */ 
/*  650 */     button = makeNavigationButton("save", "exportar", Mensajes.getString("saveAstt"), Mensajes.getString("saveAs"));
/*      */ 
/*  653 */     toolBar.add(button);
/*      */ 
/*  655 */     button = makeNavigationButton("csv24", "csv", Mensajes.getString("saveAsCsvtt"), Mensajes.getString("saveAsCsv"));
/*      */ 
/*  658 */     toolBar.add(button);
/*  659 */     toolBar.addSeparator(new Dimension(5, 0));
/*      */ 
/*  662 */     button = makeNavigationButton("fonts", "fuentes", Mensajes.getString("selFonttt"), Mensajes.getString("fuentes"));
/*      */ 
/*  665 */     toolBar.add(button);
/*      */ 
/*  668 */     button = makeNavigationButton("print24", "imprimir", Mensajes.getString("printtt"), Mensajes.getString("print"));
/*      */ 
/*  671 */     toolBar.add(button);
/*      */ 
/*  673 */     button = makeNavigationButton("preview", "vistaprevia", Mensajes.getString("previewtt"), Mensajes.getString("preview"));
/*      */ 
/*  676 */     toolBar.add(button);
/*      */ 
/*  678 */     toolBar.addSeparator(new Dimension(20, 0));
/*      */ 
/*  681 */     button = makeNavigationButton("concepto", "factura", Mensajes.getString("selBuFactt"), Mensajes.getString("factura"));
/*      */ 
/*  684 */     toolBar.add(button);
/*  685 */     toolBar.addSeparator(new Dimension(10, 0));
/*      */ 
/*  687 */     button = makeNavigationButton("date32", "fecha", Mensajes.getString("selBuFechatt"), Mensajes.getString("fecha"));
/*      */ 
/*  690 */     toolBar.add(button);
/*  691 */     toolBar.addSeparator(new Dimension(10, 0));
/*      */ 
/*  693 */     button = makeNavigationButton("cuentas", "cuenta", Mensajes.getString("selBuCtastt"), Mensajes.getString("cuentas"));
/*      */ 
/*  696 */     toolBar.add(button);
/*  697 */     toolBar.addSeparator(new Dimension(10, 0));
/*      */ 
/*  699 */     button = makeNavigationButton("asiento", "asiento", Mensajes.getString("selBuNumAstt"), Mensajes.getString("asiento"));
/*      */ 
/*  702 */     toolBar.add(button);
/*  703 */     toolBar.addSeparator(new Dimension(10, 0));
/*      */ 
/*  705 */     button = makeNavigationButton("importe", "base", Mensajes.getString("Selección-Búsqueda por base imponible"), Mensajes.getString("importe"));
/*      */ 
/*  708 */     toolBar.add(button);
/*  709 */     toolBar.addSeparator(new Dimension(10, 0));
/*      */ 
/*  711 */     button = makeNavigationButton("importe", "iva", Mensajes.getString("Selección-Búsqueda por importe de IVA"), Mensajes.getString("importe"));
/*      */ 
/*  714 */     toolBar.add(button);
/*  715 */     toolBar.addSeparator(new Dimension(10, 0));
/*      */ 
/*  717 */     button = makeNavigationButton("importe", "total", Mensajes.getString("Selección-Búsqueda por total de la factura"), Mensajes.getString("importe"));
/*      */ 
/*  720 */     toolBar.add(button);
/*  721 */     toolBar.addSeparator(new Dimension(10, 0));
/*      */ 
/*  723 */     button = makeNavigationButton("retable", "completo", Mensajes.getString("listCompletott"), Mensajes.getString("completo"));
/*      */ 
/*  726 */     toolBar.add(button);
/*  727 */     toolBar.addSeparator(new Dimension(10, 0));
/*      */ 
/*  729 */     button = makeNavigationButton("refind", "repetir", Mensajes.getString("reFindtt"), Mensajes.getString("repetir"));
/*      */ 
/*  732 */     toolBar.add(button);
/*      */   }
/*      */ 
/*      */   private JButton makeNavigationButton(String imageName, String actionCommand, String toolTipText, String altText)
/*      */   {
/*  739 */     JButton button = makeNavigationButton(imageName, toolTipText, altText);
/*  740 */     button.setActionCommand(actionCommand);
/*  741 */     button.addActionListener(this);
/*  742 */     return button;
/*      */   }
/*      */ 
/*      */   private void buscarDato(int campo)
/*      */   {
/*  770 */     this.Accion = false;
/*  771 */     this.lastSearch = campo;
/*  772 */     String patron = this.comienzo.toUpperCase();
/*  773 */     int numFilas = this.tabla.getRowCount(); int x = 0;
/*  774 */     for (x = this.selectedRow + 1; x < numFilas; x++) {
/*  775 */       if (this.tabla.getValueAt(x, campo).toString().toUpperCase().indexOf(patron) != -1) {
/*  776 */         this.tabla.changeSelection(x, 0, false, false);
/*  777 */         break;
/*      */       }
/*      */     }
/*  780 */     if (x == numFilas) {
/*  781 */       int volver = JOptionPane.showConfirmDialog(getContentPane(), Mensajes.getString("finTabla"), Mensajes.getString("confirma"), 0);
/*      */ 
/*  784 */       if (volver == 0) {
/*  785 */         this.tabla.changeSelection(0, 0, false, false);
/*  786 */         if (this.tabla.getValueAt(0, campo).toString().toUpperCase().indexOf(patron) != -1) {
/*  787 */           return;
/*      */         }
/*  789 */         buscarDato(this.lastSearch);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private double totalImporte()
/*      */   {
/*  800 */     double totalI = 0.0D;
/*  801 */     int numFilas = this.tabla.getRowCount();
/*  802 */     for (int x = 0; x < numFilas; x++) {
/*  803 */       String importe = this.tabla_ord.getValueAt(x, 7).toString();
/*  804 */       totalI += Double.parseDouble(importe);
/*      */     }
/*  806 */     return totalI;
/*      */   }
/*      */ 
/*      */   private String id_fila(int fila)
/*      */   {
/*  816 */     return this.tabla_ord.getValueAt(fila, 0).toString();
/*      */   }
/*      */ 
/*      */   private Date fechaPago() {
/*  820 */     if (this.selectedRow != -1) {
/*  821 */       String fecha = this.tabla_ord.getValueAt(this.selectedRow, 1).toString();
/*  822 */       int year = Integer.parseInt(fecha.substring(0, 4));
/*  823 */       int month = Integer.parseInt(fecha.substring(5, 7)) - 1;
/*  824 */       int date = Integer.parseInt(fecha.substring(8));
/*  825 */       GregorianCalendar calendario = new GregorianCalendar();
/*  826 */       calendario.set(year, month, date);
/*  827 */       return calendario.getTime();
/*      */     }
/*  829 */     return new Date();
/*      */   }
/*      */ 
/*      */   private void borrarFactura(String id)
/*      */   {
/*      */     try
/*      */     {
/*  839 */       ManejoFacturas mF = new ManejoFacturas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  840 */       mF.borrar(esEmitida(), id);
/*      */ 
/*  842 */       GetInvoiceNumber dlg = new GetInvoiceNumber(Inicio.frame, true, false, id);
/*  843 */       mostrarDialogo(dlg);
/*  844 */       String temp = dlg.getNumeroFactura();
/*  845 */       if (!temp.equals("FALSE")) {
/*      */         try {
/*  847 */           FacturaControl fC = new FacturaControl(Inicio.getCFacturacion(), true);
/*  848 */           Factura factura = fC.facturaEjercicio(temp);
/*  849 */           if (factura != null) {
/*  850 */             fC.borrar(factura);
/*  851 */             fC.cerrarRs();
/*  852 */             LineaFacturaControl lFc = new LineaFacturaControl(Inicio.getCFacturacion(), true);
/*  853 */             ArrayList lineas = lFc.lineas(factura.getId());
/*  854 */             for (LineaFactura lineaFactura : (List<LineaFactura>) lineas) {
/*  855 */               lFc.borrar(lineaFactura);
/*      */             }
/*  857 */             lFc.cerrarRs();
/*  858 */             AlmacenControl aC = new AlmacenControl();
/*  859 */             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/*  860 */             String date = sdf.format(factura.getFecha());
/*  861 */             for (LineaFactura linea :(List<LineaFactura>) lineas) {
/*  862 */               for (int i = 0; i < linea.getUnidades().intValue(); i++) {
/*  863 */                 String referencia = linea.getIdProducto().getReferencia();
/*  864 */                 double importe = linea.getBase().doubleValue() / linea.getUnidades().doubleValue();
/*  865 */                 importe = doubleTwoDecimals(Double.valueOf(importe)).doubleValue();
/*  866 */                 int io = linea.getBase().doubleValue() >= 0.0D ? -1 : 1;
/*  867 */                 aC.suprimirPIO(referencia, date, importe, io);
/*      */               }
/*      */             }
/*  870 */             Inicio.frame.renovarTabla(7);
/*      */           }
/*      */         } catch (Exception ex) {
/*  873 */           ex.printStackTrace();
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*      */     }
/*      */     catch (NumberFormatException e)
/*      */     {
/*  890 */       e.printStackTrace();
/*      */     }
/*      */   }
/*      */ 
/*      */   private Double doubleTwoDecimals(Double number) {
/*  895 */     if (number != null) {
/*  896 */       BigDecimal dec = new BigDecimal(number.doubleValue());
/*  897 */       return Double.valueOf(dec.setScale(2, 4).doubleValue());
/*      */     }
/*  899 */     return Double.valueOf(-1.0D);
/*      */   }
/*      */ 
/*      */   private void pagarFactura(String id, String fecha, int cuenta, int cuentaPago, double importe)
/*      */   {
/*  911 */     if (!fecha.substring(0, 4).equals(Inicio.p.getEjercicio())) {
/*  912 */       JOptionPane.showMessageDialog(getContentPane(), Mensajes.getString("ejercicioNoCorrecto"));
/*  913 */       return;
/*      */     }
/*      */     try {
/*  916 */       String documento = id;
/*  917 */       ManejoAsientos asientoM = new ManejoAsientos(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  918 */       ManejoApuntes apunteM = new ManejoApuntes(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  919 */       int continuar = 0;
/*  920 */       if (asientoM.existeAsiento(null, null, id, "Q") != -1) {
/*  921 */         continuar = JOptionPane.showConfirmDialog(getContentPane(), "Ya existe un asiento de cobro para esta factura\n¿Desea continuar?", Mensajes.getString("confirma"), 0);
/*      */       }
/*      */ 
/*  925 */       if (continuar == 0) {
/*  926 */         int numeroAs = asientoM.nuevoNumero();
/*  927 */         if (numeroAs != -1) {
/*  928 */           int idAs = asientoM.crear(numeroAs, fecha, documento, marca());
/*  929 */           if (idAs != -1) {
/*  930 */             String concepto = sPago() + " " + Mensajes.getString("factura") + " " + id;
/*  931 */             if (concepto.length() > 30) {
/*  932 */               concepto = concepto.substring(0, 30);
/*      */             }
/*  934 */             String DH1 = esEmitida() ? Mensajes.getString("debeA") : Mensajes.getString("haberA");
/*  935 */             String DH2 = esEmitida() ? Mensajes.getString("haberA") : Mensajes.getString("debeA");
/*  936 */             apunteM.crear(idAs, cuenta, concepto, DH2, importe);
/*  937 */             apunteM.crear(idAs, cuentaPago, concepto, DH1, importe);
/*      */ 
/*  939 */             String cliente = "";
/*  940 */             boolean enArchivo = false;
/*  941 */             int ctaGasto = -1;
/*  942 */             double porcent = 0.0D;
/*      */             try
/*      */             {
/*  945 */               LeerFichero ventasTPV = new LeerFichero("configdir/ventasTPV.ini");
/*  946 */               while (!ventasTPV.eof()) {
/*  947 */                 String linea = ventasTPV.leer();
/*  948 */                 int uno = linea.indexOf(";");
/*  949 */                 int dos = linea.indexOf(";", uno + 1);
/*  950 */                 String nombre = linea.substring(0, uno);
/*  951 */                 if (cuentaPago == Integer.parseInt(nombre)) {
/*  952 */                   enArchivo = true;
/*  953 */                   ctaGasto = Integer.parseInt(linea.substring(uno + 1, dos));
/*  954 */                   porcent = Double.parseDouble(linea.substring(dos + 1, linea.lastIndexOf(";"))) / 100.0D;
/*  955 */                   cliente = linea.substring(linea.lastIndexOf(";") + 1);
/*  956 */                   break;
/*      */                 }
/*      */               }
/*      */             } catch (IOException e) {
/*  960 */               e.printStackTrace();
/*      */             }
/*  962 */             if (!enArchivo) {
/*  963 */               cliente = "*";
/*  964 */               PagoTarjeta dlg = new PagoTarjeta(Inicio.frame, Mensajes.getString("comPagoTjta"), true);
/*  965 */               Dimension dlgSize = dlg.getPreferredSize();
/*  966 */               Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/*  967 */               dlg.setLocation((screenSize.width - dlgSize.width) / 2, (screenSize.height - dlgSize.height) / 2);
/*      */ 
/*  969 */               dlg.setVisible(true);
/*  970 */               porcent = dlg.conComision() / 100.0D;
/*  971 */               ctaGasto = dlg.enCuenta();
/*      */             }
/*  973 */             if (((cliente.equals("*")) || (cliente.equals(Integer.toString(cuenta)))) && 
/*  974 */               (ctaGasto != -1) && (porcent != 0.0D)) {
/*  975 */               double comTarjeta = (importe >= 0.0D ? importe : -1.0D * importe) * porcent;
/*  976 */               apunteM.crear(idAs, cuentaPago, concepto, Mensajes.getString("haberA"), comTarjeta);
/*  977 */               apunteM.crear(idAs, ctaGasto, concepto, Mensajes.getString("debeA"), comTarjeta);
/*      */             }
/*      */ 
/*  981 */             Inicio.frame.renovarTabla(0);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     catch (NumberFormatException e) {
/*  987 */       e.printStackTrace();
/*      */     }
/*      */   }
/*      */ 
/*      */   private void refrescarTabla(boolean fin) {
/*  992 */     if (this.datos.conexionCerrada()) {
/*  993 */       return;
/*      */     }
/*  995 */     this.tabla.removeAll();
/*      */     try {
/*  997 */       this.model = new ScrollableTableModel(this.datos.resultado(), this.nombres_col);
/*      */     } catch (SQLException e) {
/*  999 */       e.printStackTrace();
/*      */     }
/* 1001 */     this.tabla_ord.setTableModel(this.model);
/* 1002 */     this.tabla.setModel(this.tabla_ord);
/* 1003 */     ListSelectionModel rowSM = this.tabla.getSelectionModel();
/* 1004 */     rowSM.addListSelectionListener(new ListSelectionListener()
/*      */     {
/*      */       public void valueChanged(ListSelectionEvent arg0) {
/* 1007 */         TablaFacturasEmitidas.this.facturasSeleccionTabla(arg0);
/*      */       }
/*      */     });
/* 1010 */     this.columna = this.tabla.getColumnModel().getColumn(0);
/* 1011 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.12D));
/* 1012 */     this.columna.setCellRenderer(new DerechaColorRenderer());
/* 1013 */     this.columna = this.tabla.getColumnModel().getColumn(1);
/* 1014 */     this.columna.setCellRenderer(new FechaColorRenderer());
/* 1015 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.12D));
/* 1016 */     this.columna = this.tabla.getColumnModel().getColumn(2);
/* 1017 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.12D));
/* 1018 */     this.columna.setCellRenderer(new DerechaColorRenderer());
/* 1019 */     this.columna = this.tabla.getColumnModel().getColumn(3);
/*      */ 
/* 1021 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.08D));
/* 1022 */     this.columna.setCellRenderer(new DerechaColorRenderer());
/* 1023 */     this.columna = this.tabla.getColumnModel().getColumn(4);
/* 1024 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.2D));
/* 1025 */     this.columna = this.tabla.getColumnModel().getColumn(5);
/* 1026 */     this.columna.setCellRenderer(new ImporteColorRenderer());
/* 1027 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.12D));
/* 1028 */     this.columna = this.tabla.getColumnModel().getColumn(6);
/* 1029 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.12D));
/* 1030 */     this.columna.setCellRenderer(new ImporteColorRenderer());
/* 1031 */     this.columna = this.tabla.getColumnModel().getColumn(7);
/* 1032 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.12D));
/* 1033 */     this.columna.setCellRenderer(new ImporteColorRenderer());
/* 1034 */     this.tabla.setDefaultRenderer(Object.class, new GeneralColorRenderer());
/* 1035 */     this.total.setText(this.fn.format(totalImporte()));
/* 1036 */     if (fin)
/* 1037 */       this.tabla.changeSelection(this.tabla.getRowCount() - 1, 0, true, true);
/*      */   }
/*      */ 
/*      */   private void facturasSeleccionTabla(ListSelectionEvent e)
/*      */   {
/* 1042 */     SeleccionTabla(e);
/* 1043 */     if (this.selectedRow > this.tabla.getRowCount() - 1) {
/* 1044 */       this.selectedRow = (this.tabla.getRowCount() - 1);
/*      */     }
/* 1046 */     if (this.selectedRow != -1) {
/* 1047 */       int cuenta = Integer.parseInt(this.tabla_ord.getValueAt(this.selectedRow, 2).toString());
/* 1048 */       this.infoCuenta.setText(datosCuenta(cuenta));
/*      */     }
/*      */   }
/*      */ 
/*      */   private String datosCuenta(int cuenta) {
/* 1053 */     String datosC = "";
/* 1054 */     ManejoSubcuentas mS = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 1055 */     TipoSubcuenta subcuenta = new TipoSubcuenta();
/* 1056 */     subcuenta = mS.datos(cuenta);
/* 1057 */     datosC = subcuenta.getNombre() + " : " + this.fn.format(subcuenta.getSaldo());
/* 1058 */     return datosC;
/*      */   }
/*      */ 
/*      */   private JMenuItem getJMenuItem()
/*      */   {
/* 1067 */     if (this.jMenuItem == null) {
/* 1068 */       this.jMenuItem = new JMenuItem();
/* 1069 */       this.jMenuItem.setText(Mensajes.getString("borrar"));
/* 1070 */       this.jMenuItem.setIcon(this.iconoBorrar);
/* 1071 */       this.jMenuItem.addActionListener(new ActionListener()
/*      */       {
/*      */         public void actionPerformed(ActionEvent e) {
/* 1074 */           TablaFacturasEmitidas.this.eliminarFactura();
/*      */         }
/*      */       });
/*      */     }
/* 1078 */     return this.jMenuItem;
/*      */   }
/*      */ 
/*      */   private JMenuItem getJMenuItem1()
/*      */   {
/* 1087 */     if (this.jMenuItem1 == null) {
/* 1088 */       this.jMenuItem1 = new JMenuItem();
/* 1089 */       this.jMenuItem1.setText(sPagar());
/* 1090 */       this.jMenuItem1.setIcon(this.iconoPagar);
/* 1091 */       this.jMenuItem1.addActionListener(new ActionListener()
/*      */       {
/*      */         public void actionPerformed(ActionEvent e) {
/* 1094 */           TablaFacturasEmitidas.this.pagarCobrarFactura();
/*      */         }
/*      */       });
/*      */     }
/* 1098 */     return this.jMenuItem1;
/*      */   }
/*      */ 
/*      */   private JMenuItem getJMenuItem2() {
/* 1102 */     if (this.jMenuItem2 == null) {
/* 1103 */       this.jMenuItem2 = new JMenuItem();
/* 1104 */       this.jMenuItem2.setText(Mensajes.getString("vencimientos"));
/* 1105 */       this.jMenuItem2.setIcon(this.iconoCrear);
/* 1106 */       this.jMenuItem2.addActionListener(new ActionListener()
/*      */       {
/*      */         public void actionPerformed(ActionEvent e) {
/* 1109 */           TablaFacturasEmitidas.this.CrearVencimiento();
/*      */         }
/*      */       });
/*      */     }
/* 1113 */     return this.jMenuItem2;
/*      */   }
/*      */ 
/*      */   private JMenuItem getJMenuItem3() {
/* 1117 */     if (this.jMenuItem3 == null) {
/* 1118 */       this.jMenuItem3 = new JMenuItem();
/* 1119 */       this.jMenuItem3.setText(Mensajes.getString("modificar"));
/* 1120 */       this.jMenuItem3.setIcon(this.iconoModificar);
/* 1121 */       this.jMenuItem3.addActionListener(new ActionListener()
/*      */       {
/*      */         public void actionPerformed(ActionEvent e) {
/* 1124 */           TablaFacturasEmitidas.this.modificarFactura();
/*      */         }
/*      */       });
/*      */     }
/* 1128 */     return this.jMenuItem3;
/*      */   }
/*      */ 
/*      */   private void marcoActivado() {
/* 1132 */     if (this.datos.conexionCerrada())
/* 1133 */       this.datos.abrirConexion();
/*      */   }
/*      */ 
/*      */   private void marcoDesactivado()
/*      */   {
/*      */   }
/*      */ 
/*      */   public void renovarTabla() {
/* 1141 */     refrescarTabla(false);
/*      */   }
/*      */ 
/*      */   private void exportarA_archivo() {
/* 1145 */     String EOL = FinLinea.get();
/* 1146 */     AlinearCadena alinea = new AlinearCadena();
/* 1147 */     JFileChooser fc = new JFileChooser();
/* 1148 */     fc.setSelectedFile(new File("FacturasEmitidas.txt"));
/* 1149 */     int retorno = fc.showSaveDialog(Inicio.frame);
/* 1150 */     if (retorno == 0) {
/* 1151 */       FechaHoy hoy = new FechaHoy();
/* 1152 */       File archivo = fc.getSelectedFile();
/* 1153 */       archivo = AddExtension.txt(archivo);
/* 1154 */       GrabarFichero salida = new GrabarFichero(archivo);
/* 1155 */       salida.insertar("Listado de facturas emitidas      " + hoy.getFecha() + EOL);
/*      */ 
/* 1157 */       int filas = this.tabla.getRowCount(); int columnas = this.tabla.getColumnCount();
/* 1158 */       int[] x = new int[columnas];
/* 1159 */       String cadena = "";
/* 1160 */       for (int nCol = 0; nCol < columnas; nCol++) {
/* 1161 */         boolean izq = true;
/* 1162 */         this.columna = this.tabla.getColumnModel().getColumn(nCol);
/* 1163 */         String titulo = (String)this.columna.getIdentifier();
/* 1164 */         if (titulo.equals(Mensajes.getString("numero"))) {
/* 1165 */           x[nCol] = 20;
/* 1166 */         } else if (titulo.equals(Mensajes.getString("fecha"))) {
/* 1167 */           x[nCol] = 11;
/* 1168 */         } else if (titulo.equals(Mensajes.getString("cuenta"))) {
/* 1169 */           x[nCol] = 10;
/* 1170 */         } else if (titulo.equals(Mensajes.getString("asiento"))) {
/* 1171 */           x[nCol] = 11;
/* 1172 */         } else if (titulo.equals(Mensajes.getString("concepto"))) {
/* 1173 */           x[nCol] = 30;
/* 1174 */         } else if (titulo.equals(Mensajes.getString("base"))) {
/* 1175 */           x[nCol] = 12;
/* 1176 */           izq = false;
/* 1177 */         } else if (titulo.equals(Mensajes.getString("IVAs"))) {
/* 1178 */           x[nCol] = 12;
/* 1179 */           izq = false;
/* 1180 */         } else if (titulo.equals(Mensajes.getString("total"))) {
/* 1181 */           x[nCol] = 12;
/* 1182 */           izq = false;
/*      */         }
/*      */ 
/* 1185 */         if (izq)
/* 1186 */           cadena = cadena + alinea.Izquierda(titulo, x[nCol]);
/*      */         else {
/* 1188 */           cadena = cadena + alinea.Derecha(titulo, x[nCol]);
/*      */         }
/*      */       }
/* 1191 */       salida.insertar(cadena + EOL);
/* 1192 */       cadena = "";
/* 1193 */       for (int nCol = 0; nCol < columnas; nCol++) {
/* 1194 */         for (int nCarac = 0; nCarac < x[nCol]; nCarac++) {
/* 1195 */           cadena = cadena + "-";
/*      */         }
/*      */       }
/* 1198 */       salida.insertar(cadena + EOL);
/*      */ 
/* 1200 */       for (int nRow = 0; nRow < filas; nRow++) {
/* 1201 */         cadena = "";
/* 1202 */         for (int nCol = 0; nCol < columnas; nCol++) {
/* 1203 */           int col = this.tabla.getColumnModel().getColumn(nCol).getModelIndex();
/* 1204 */           Object obj = this.tabla_ord.getValueAt(nRow, col);
/* 1205 */           if ((obj instanceof Double)) {
/* 1206 */             String str = this.fn.format(obj);
/* 1207 */             cadena = cadena + alinea.Derecha(str, x[nCol]);
/* 1208 */           } else if ((obj instanceof Date)) {
/* 1209 */             String cad = obj.toString();
/* 1210 */             int year = Integer.parseInt(cad.substring(0, 4));
/* 1211 */             int month = Integer.parseInt(cad.substring(5, 7)) - 1;
/* 1212 */             int date = Integer.parseInt(cad.substring(8));
/* 1213 */             GregorianCalendar calendario = new GregorianCalendar();
/* 1214 */             calendario.set(year, month, date);
/* 1215 */             SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
/* 1216 */             String str = sdf.format(calendario.getTime());
/* 1217 */             cadena = cadena + alinea.Izquierda(str, x[nCol]);
/*      */           } else {
/* 1219 */             String str = obj.toString();
/* 1220 */             if (str.equals("false")) {
/* 1221 */               str = " " + Mensajes.getString("no");
/*      */             }
/* 1223 */             if (str.equals("true")) {
/* 1224 */               str = " " + Mensajes.getString("yes");
/*      */             }
/* 1226 */             cadena = cadena + alinea.Izquierda(str, x[nCol]);
/*      */           }
/*      */         }
/* 1229 */         salida.insertar(cadena + EOL);
/*      */       }
/* 1231 */       salida.insertar(EOL);
/* 1232 */       salida.insertar(alinea.Derecha("Total:   " + this.fn.format(totalImporte()), 82));
/* 1233 */       salida.cerrar();
/*      */     }
/*      */   }
/*      */ 
/*      */   private void imprimirTabla() {
/* 1238 */     PrinterJob printerJob = PrinterJob.getPrinterJob();
/* 1239 */     PageFormat userFormat = printerJob.pageDialog(printerJob.defaultPage());
/* 1240 */     printerJob.setPrintable(this, userFormat);
/* 1241 */     if (printerJob.printDialog()) {
/* 1242 */       this.m_maxNumPage = 1;
/* 1243 */       setCursor(Cursor.getPredefinedCursor(3));
/*      */       try
/*      */       {
/* 1246 */         printerJob.print();
/*      */       } catch (PrinterException e) {
/* 1248 */         System.err.println(Mensajes.getString("errPrint") + ": " + e);
/*      */       }
/* 1250 */       setCursor(Cursor.getPredefinedCursor(0));
/*      */     }
/*      */   }
/*      */ 
/*      */   public int print(Graphics pg, PageFormat pageFormat, int pageIndex)
/*      */     throws PrinterException
/*      */   {
/* 1257 */     if (pageIndex >= this.m_maxNumPage) {
/* 1258 */       return 1;
/*      */     }
/*      */ 
/* 1261 */     FechaHoy hoy = new FechaHoy();
/* 1262 */     int anchoImporte = 0;
/*      */ 
/* 1264 */     pg.translate((int)pageFormat.getImageableX(), (int)pageFormat.getImageableY());
/*      */ 
/* 1266 */     int wPage = 0;
/* 1267 */     int hPage = 0;
/* 1268 */     if (pageFormat.getOrientation() == 1) {
/* 1269 */       wPage = (int)pageFormat.getImageableWidth();
/* 1270 */       hPage = (int)pageFormat.getImageableHeight();
/*      */     } else {
/* 1272 */       wPage = (int)pageFormat.getImageableWidth();
/* 1273 */       wPage += wPage / 2;
/* 1274 */       hPage = (int)pageFormat.getImageableHeight();
/* 1275 */       pg.setClip(0, 0, wPage, hPage);
/*      */     }
/* 1277 */     int y = 0;
/*      */ 
/* 1279 */     pg.setFont(this.tabla.getFont().deriveFont(1, this.tabla.getFont().getSize2D() + 2.0F));
/* 1280 */     pg.setColor(Color.black);
/*      */ 
/* 1282 */     FontMetrics fm = pg.getFontMetrics();
/* 1283 */     y += fm.getAscent();
/* 1284 */     pg.drawString(getTitle() + "     " + hoy.getFecha(), 0, y);
/* 1285 */     y += 20;
/* 1286 */     Font headerFont = this.tabla.getFont().deriveFont(1);
/* 1287 */     pg.setFont(headerFont);
/* 1288 */     fm = pg.getFontMetrics();
/*      */ 
/* 1290 */     TableColumnModel colModel = this.tabla.getColumnModel();
/* 1291 */     int nColumns = colModel.getColumnCount();
/* 1292 */     int[] x = new int[nColumns];
/* 1293 */     x[0] = 0;
/*      */ 
/* 1295 */     int h = fm.getAscent();
/* 1296 */     y += h;
/*      */ 
/* 1300 */     for (int nCol = 0; nCol < nColumns; nCol++) {
/* 1301 */       TableColumn tk = colModel.getColumn(nCol);
/* 1302 */       int width = tk.getWidth();
/* 1303 */       if (x[nCol] + width > wPage) {
/* 1304 */         nColumns = nCol;
/* 1305 */         break;
/*      */       }
/* 1307 */       if (nCol + 1 < nColumns) {
/* 1308 */         x[(nCol + 1)] = (x[nCol] + width);
/*      */       }
/* 1310 */       String titleP = (String)tk.getIdentifier();
/* 1311 */       if (titleP.equals(Mensajes.getString("importe"))) {
/* 1312 */         anchoImporte = tk.getWidth() * 80 / 100;
/*      */       }
/* 1314 */       pg.drawString(titleP, x[nCol], y);
/*      */     }
/*      */ 
/* 1317 */     pg.setFont(this.tabla.getFont());
/* 1318 */     fm = pg.getFontMetrics();
/*      */ 
/* 1320 */     int header = y;
/* 1321 */     h = fm.getHeight();
/* 1322 */     int rowH = Math.max((int)(h * 1.5D), 10);
/* 1323 */     int rowPerPage = (hPage - header) / rowH;
/* 1324 */     this.m_maxNumPage = Math.max((int)Math.ceil(this.tabla.getRowCount() / rowPerPage), 1);
/*      */ 
/* 1328 */     int iniRow = pageIndex * rowPerPage;
/* 1329 */     int endRow = Math.min(this.tabla.getRowCount(), iniRow + rowPerPage);
/*      */ 
/* 1332 */     for (int nRow = iniRow; nRow < endRow; nRow++) {
/* 1333 */       y += h;
/* 1334 */       for (int nCol = 0; nCol < nColumns; nCol++) {
/* 1335 */         pg.setColor(Color.black);
/* 1336 */         int col = this.tabla.getColumnModel().getColumn(nCol).getModelIndex();
/* 1337 */         Object obj = this.tabla_ord.getValueAt(nRow, col);
/* 1338 */         String str = "";
/* 1339 */         if (obj != null) {
/* 1340 */           str = obj.toString();
/*      */         }
/* 1342 */         if (str.equals("false")) {
/* 1343 */           str = Mensajes.getString("no");
/*      */         }
/* 1345 */         if (str.equals("true")) {
/* 1346 */           str = Mensajes.getString("yes");
/*      */         }
/* 1348 */         if ((obj instanceof Double)) {
/* 1349 */           str = this.fn.format(obj);
/*      */ 
/* 1351 */           FontRenderContext frc = ((Graphics2D)pg).getFontRenderContext();
/* 1352 */           TextLayout layout = new TextLayout(str, this.tabla.getFont(), frc);
/* 1353 */           float w = layout.getAdvance();
/* 1354 */           pg.drawString(str, x[nCol] + (anchoImporte - (int)w), y);
/* 1355 */         } else if ((obj instanceof Date)) {
/* 1356 */           String cad = obj.toString();
/* 1357 */           int year = Integer.parseInt(cad.substring(0, 4));
/* 1358 */           int month = Integer.parseInt(cad.substring(5, 7)) - 1;
/* 1359 */           int date = Integer.parseInt(cad.substring(8));
/* 1360 */           GregorianCalendar calendario = new GregorianCalendar();
/* 1361 */           calendario.set(year, month, date);
/* 1362 */           SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
/* 1363 */           str = sdf.format(calendario.getTime());
/* 1364 */           pg.drawString(str, x[nCol], y);
/*      */         } else {
/* 1366 */           pg.drawString(str, x[nCol], y);
/*      */         }
/*      */       }
/*      */     }
/* 1370 */     if (pageIndex == this.m_maxNumPage - 1) {
/* 1371 */       y += 20;
/* 1372 */       pg.drawString(Mensajes.getString("total") + ":", 250, y);
/* 1373 */       pg.drawString(this.total.getText(), 310, y);
/*      */     }
/* 1375 */     System.gc();
/* 1376 */     return 0;
/*      */   }
/*      */ 
/*      */   public void cerrarConexion()
/*      */   {
/* 1381 */     if (!this.datos.conexionCerrada())
/* 1382 */       this.datos.cerrarConexion();
/*      */   }
/*      */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.TablaFacturasEmitidas
 * JD-Core Version:    0.6.2
 */