/*      */ package contaes;
/*      */ 
/*      */ import contaes.auxiliar.AlinearCadena;
/*      */ import contaes.auxiliarTablas.CentroColorRenderer;
/*      */ import contaes.auxiliarTablas.DerechaColorRenderer;
/*      */ import contaes.auxiliarTablas.FechaColorRenderer;
/*      */ import contaes.auxiliarTablas.GeneralColorRenderer;
/*      */ import contaes.auxiliarTablas.ImporteColorRenderer;
/*      */ import contaes.auxiliarTablas.ScrollableTableModel;
/*      */ import contaes.auxiliarTablas.TablaToCsv;
/*      */ import contaes.auxiliarTablas.TableSorter;
/*      */ import contaes.dialogosAuxiliares.MostrarFuentes;
/*      */ import contaes.dialogosAuxiliares.PrintPreview;
/*      */ import contaes.manejoDatos.ManejoAsientos;
/*      */ import contaes.manejoDatos.ManejoFacturas;
/*      */ import contaes.manejoDatos.ManejoSubcuentas;
/*      */ import contaes.manejoDatos.ManejoTablaDiario;
/*      */ import contaes.manejoDatos.ManejoVencimientos;
/*      */ import contaes.manejoDatos.TipoAsiento;
/*      */ import contaes.manejoDatos.TipoSubcuenta;
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
/*      */ import java.util.Date;
/*      */ import java.util.GregorianCalendar;
/*      */ import java.util.List;
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
/*      */ public class TablaDiario extends MarcoInternoTablas
/*      */   implements Printable, ActionListener
/*      */ {
/*      */   private static final String ASIENTO = "asiento";
/*      */   private static final String FECHA = "fecha";
/*      */   private static final String CUENTA = "cuenta";
/*      */   private static final String CONCEPTO = "concepto";
/*      */   private static final String DOCU = "docu";
/*      */   private static final String IMPORTE = "importe";
/*      */   private static final String MARCA = "marca";
/*      */   private static final String COMPLETO = "completo";
/*      */   private static final String REPETIR = "repetir";
/*      */   private static final String SELECCIONAR = "seleccionar";
/*      */   private static final String BORRAR = "borrar";
/*      */   private static final String IMPRIMIR = "imprimir";
/*      */   private static final String VISTAPREVIA = "vistaprevia";
/*      */   private static final String FUENTES = "fuentes";
/*      */   private static final String EXPORTAR = "exportar";
/*      */   private static final String CSV = "csv";
/*      */   private ManejoTablaDiario datos;
/*  121 */   private JPopupMenu menuEmergente = new JPopupMenu();
/*  122 */   private JMenuItem jMenuItem = null;
/*  123 */   private JMenuItem jMenuItem1 = null;
/*      */ 
/*  125 */   private BorderLayout borderLayout1 = new BorderLayout();
/*  126 */   private GridBagLayout gbLayout = new GridBagLayout();
/*  127 */   private JToolBar barra = new JToolBar();
/*  128 */   private JScrollPane scrollPane = new JScrollPane();
/*  129 */   private JPanel datosCuentas = new JPanel();
/*  130 */   private JLabel eDebe = new JLabel(Mensajes.getString("debe"));
/*  131 */   private JLabel eHaber = new JLabel(Mensajes.getString("haber"));
/*  132 */   private JLabel eSaldo = new JLabel(Mensajes.getString("saldo"));
/*  133 */   private JLabel eCuenta = new JLabel(Mensajes.getString("datosCuenta"));
/*  134 */   private JTextField nombre = new JTextField();
/*  135 */   private JTextField saldo = new JTextField();
/*  136 */   private JTextField cDebe = new JTextField();
/*  137 */   private JTextField cHaber = new JTextField();
/*  138 */   private JTextField cSaldo = new JTextField();
/*  139 */   private JButton selec = new JButton();
/*  140 */   private JButton borrar = new JButton();
/*  141 */   private TableSorter tabla_ord = new TableSorter();
/*      */   private JTable tabla;
/*  143 */   private TableColumn columna = null;
/*      */   private ScrollableTableModel model;
/*  145 */   private double totalDebe = 0.0D; private double totalHaber = 0.0D; private double totalSaldo = 0.0D;
/*  146 */   private ImageIcon iconoModif = createImageIcon("/contaes/iconos/edit.png");
/*  147 */   private ImageIcon iconoBorrar = createImageIcon("/contaes/iconos/delete.png");
/*      */ 
/*      */   public TablaDiario(Dimension parentDim) {
/*  150 */     super(Mensajes.getString("diario"));
/*  151 */     this.datos = new ManejoTablaDiario(Inicio.p.getEmpresa(), Inicio.p.getEjercicio());
/*      */     try {
/*  153 */       initialize();
/*      */     } catch (Exception exception) {
/*  155 */       exception.printStackTrace();
/*      */     }
/*      */   }
/*      */ 
/*      */   private void initialize() throws Exception {
/*  160 */     DecimalFormatSymbols formato = new DecimalFormatSymbols();
/*  161 */     formato.setDecimalSeparator(',');
/*  162 */     formato.setPerMill('.');
/*  163 */     this.fn = new DecimalFormat("#,###,##0.00", formato);
/*      */ 
/*  165 */     this.menuEmergente.add(getJMenuItem());
/*  166 */     this.menuEmergente.add(getJMenuItem1());
/*      */ 
/*  168 */     setFrameIcon(new ImageIcon(getClass().getResource("/contaes/iconos/D18.png")));
/*  169 */     Rectangle parentBounds = Inicio.frame.localizacion();
/*  170 */     int ancho = parentBounds.width < 880 ? parentBounds.width : 800;
/*  171 */     int alto = parentBounds.height < 580 ? parentBounds.height : 550;
/*  172 */     setBounds(10, 30, ancho, alto);
/*  173 */     getContentPane().setLayout(this.borderLayout1);
/*  174 */     addInternalFrameListener(new InternalFrameAdapter()
/*      */     {
/*      */       public void internalFrameOpened(InternalFrameEvent arg0)
/*      */       {
/*  178 */         super.internalFrameOpened(arg0);
/*  179 */         TablaDiario.this.marcoActivado();
/*      */       }
/*      */ 
/*      */       public void internalFrameClosing(InternalFrameEvent arg0)
/*      */       {
/*  184 */         TablaDiario.this.datos.cerrarConexion();
/*  185 */         super.internalFrameClosing(arg0);
/*      */       }
/*      */ 
/*      */       public void internalFrameDeactivated(InternalFrameEvent e)
/*      */       {
/*  190 */         TablaDiario.this.marcoDesactivado();
/*      */       }
/*      */ 
/*      */       public void internalFrameActivated(InternalFrameEvent e)
/*      */       {
/*  195 */         TablaDiario.this.marcoActivado();
/*      */       }
/*      */ 
/*      */       public void internalFrameClosed(InternalFrameEvent e)
/*      */       {
/*  200 */         Inicio.frame.eliminarMarcoMenu(Mensajes.getString("diario"));
/*      */       }
/*      */     });
/*  203 */     this.nombres_col.add(Mensajes.getString("astoA"));
/*  204 */     this.nombres_col.add(Mensajes.getString("fecha"));
/*  205 */     this.nombres_col.add(Mensajes.getString("doc"));
/*  206 */     this.nombres_col.add(Mensajes.getString("cuenta"));
/*  207 */     this.nombres_col.add(Mensajes.getString("concepto"));
/*  208 */     this.nombres_col.add(Mensajes.getString("debeHaberA"));
/*  209 */     this.nombres_col.add(Mensajes.getString("importe"));
/*  210 */     this.nombres_col.add(Mensajes.getString("marcaA"));
/*  211 */     setCursor(Cursor.getPredefinedCursor(3));
/*      */ 
/*  213 */     this.model = new ScrollableTableModel(this.datos.resultado(), this.nombres_col);
/*  214 */     this.tabla_ord.setTableModel(this.model);
/*  215 */     this.tabla = new JTable(this.tabla_ord);
/*  216 */     this.tabla_ord.setTableHeader(this.tabla.getTableHeader());
/*  217 */     this.tabla.getTableHeader().setToolTipText(Mensajes.getString("tableHeadertt"));
/*  218 */     this.tabla.setSelectionMode(0);
/*  219 */     this.tabla.addMouseListener(new MouseAdapter()
/*      */     {
/*      */       public void mouseClicked(MouseEvent e)
/*      */       {
/*  223 */         if (e.getButton() == 3) {
/*  224 */           TablaDiario.this.tabla.changeSelection(TablaDiario.this.tabla.rowAtPoint(new Point(e.getX(), e.getY())), TablaDiario.this.tabla.columnAtPoint(new Point(e.getX(), e.getY())), false, false);
/*      */ 
/*  228 */           TablaDiario.this.menuEmergente.show(e.getComponent(), e.getX(), e.getY());
/*      */         }
/*      */       }
/*      */     });
/*  233 */     ListSelectionModel rowSM = this.tabla.getSelectionModel();
/*  234 */     rowSM.addListSelectionListener(new ListSelectionListener()
/*      */     {
/*      */       public void valueChanged(ListSelectionEvent arg0) {
/*  237 */         TablaDiario.this.diarioSeleccionTabla(arg0);
/*      */       }
/*      */     });
/*  240 */     this.anchoTabla = (ancho - 40);
/*  241 */     this.columna = this.tabla.getColumnModel().getColumn(0);
/*  242 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.06D));
/*  243 */     this.columna.setCellRenderer(new DerechaColorRenderer());
/*  244 */     this.columna = this.tabla.getColumnModel().getColumn(1);
/*  245 */     this.columna.setCellRenderer(new FechaColorRenderer());
/*  246 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.13D));
/*  247 */     this.columna = this.tabla.getColumnModel().getColumn(2);
/*  248 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.11D));
/*  249 */     this.columna = this.tabla.getColumnModel().getColumn(3);
/*  250 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.11D));
/*  251 */     this.columna.setCellRenderer(new DerechaColorRenderer());
/*  252 */     this.columna = this.tabla.getColumnModel().getColumn(4);
/*  253 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.36D));
/*  254 */     this.columna = this.tabla.getColumnModel().getColumn(5);
/*  255 */     this.columna.setCellRenderer(new CentroColorRenderer());
/*  256 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.05D));
/*  257 */     this.columna = this.tabla.getColumnModel().getColumn(6);
/*  258 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.15D));
/*  259 */     this.columna.setCellRenderer(new ImporteColorRenderer());
/*  260 */     this.columna = this.tabla.getColumnModel().getColumn(7);
/*  261 */     this.columna.setCellRenderer(new CentroColorRenderer());
/*  262 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.03D));
/*      */ 
/*  264 */     this.tabla.setDefaultRenderer(Object.class, new GeneralColorRenderer());
/*      */ 
/*  267 */     this.scrollPane.getViewport().add(this.tabla);
/*      */ 
/*  271 */     this.scrollPane.setBorder(new CompoundBorder(BorderFactory.createLoweredBevelBorder(), BorderFactory.createMatteBorder(20, 20, 20, 20, Color.white)));
/*      */ 
/*  274 */     addBotones(this.barra);
/*  275 */     this.barra.setRollover(true);
/*      */ 
/*  277 */     this.selec.setText(Mensajes.getString("modificar"));
/*  278 */     this.selec.setIcon(this.iconoModif);
/*  279 */     this.selec.setToolTipText(Mensajes.getString("modifAstott"));
/*  280 */     this.selec.setVerticalTextPosition(0);
/*  281 */     this.selec.setHorizontalTextPosition(2);
/*  282 */     this.selec.setActionCommand("seleccionar");
/*  283 */     this.selec.addActionListener(this);
/*  284 */     this.borrar.setText(Mensajes.getString("suprimir"));
/*  285 */     this.borrar.setIcon(this.iconoBorrar);
/*  286 */     this.borrar.setToolTipText(Mensajes.getString("suprAstott"));
/*  287 */     this.borrar.setVerticalTextPosition(0);
/*  288 */     this.borrar.setHorizontalTextPosition(2);
/*  289 */     this.borrar.setActionCommand("borrar");
/*  290 */     this.borrar.addActionListener(this);
/*  291 */     this.datosCuentas.setBorder(BorderFactory.createEmptyBorder(5, 20, 10, 20));
/*  292 */     this.datosCuentas.setLayout(this.gbLayout);
/*  293 */     GridBagConstraints cons = new GridBagConstraints();
/*  294 */     cons.insets.bottom = 0;
/*  295 */     cons.insets.top = 0;
/*  296 */     cons.insets.right = -160;
/*  297 */     cons.anchor = 17;
/*  298 */     cons.gridx = 0;
/*  299 */     cons.gridy = 0;
/*  300 */     this.gbLayout.setConstraints(this.eCuenta, cons);
/*  301 */     this.datosCuentas.add(this.eCuenta);
/*  302 */     cons.weightx = 1.0D;
/*  303 */     cons.gridy = 1;
/*  304 */     cons.fill = 2;
/*  305 */     this.gbLayout.setConstraints(this.nombre, cons);
/*  306 */     this.datosCuentas.add(this.nombre);
/*  307 */     cons.insets.right = -30;
/*  308 */     cons.gridy = 2;
/*  309 */     this.saldo.setHorizontalAlignment(4);
/*  310 */     this.gbLayout.setConstraints(this.saldo, cons);
/*  311 */     this.datosCuentas.add(this.saldo);
/*      */ 
/*  313 */     cons.insets.left = 200;
/*  314 */     cons.insets.right = -20;
/*  315 */     cons.gridx = 1;
/*  316 */     cons.gridy = 0;
/*  317 */     cons.weightx = 0.0D;
/*  318 */     cons.anchor = 10;
/*  319 */     cons.fill = 0;
/*  320 */     this.gbLayout.setConstraints(this.selec, cons);
/*  321 */     this.datosCuentas.add(this.selec);
/*  322 */     cons.gridy = 1;
/*  323 */     cons.gridheight = 2;
/*  324 */     this.gbLayout.setConstraints(this.borrar, cons);
/*  325 */     this.datosCuentas.add(this.borrar);
/*      */ 
/*  327 */     cons.gridheight = 1;
/*  328 */     cons.insets.right = 0;
/*  329 */     cons.insets.left = 110;
/*  330 */     cons.gridx = 2;
/*  331 */     cons.gridy = 0;
/*  332 */     cons.weightx = 0.0D;
/*  333 */     cons.anchor = 13;
/*  334 */     cons.fill = 2;
/*  335 */     this.gbLayout.setConstraints(this.eDebe, cons);
/*  336 */     this.datosCuentas.add(this.eDebe);
/*  337 */     cons.gridy = 1;
/*  338 */     this.gbLayout.setConstraints(this.eHaber, cons);
/*  339 */     this.datosCuentas.add(this.eHaber);
/*  340 */     cons.gridy = 2;
/*  341 */     cons.insets.top = 3;
/*  342 */     this.gbLayout.setConstraints(this.eSaldo, cons);
/*  343 */     this.datosCuentas.add(this.eSaldo);
/*      */ 
/*  345 */     cons.insets.top = 0;
/*  346 */     cons.insets.left = 20;
/*  347 */     cons.insets.right = 30;
/*  348 */     cons.fill = 2;
/*  349 */     cons.anchor = 17;
/*  350 */     cons.weightx = 1.0D;
/*  351 */     cons.gridx = 3;
/*  352 */     cons.gridy = 0;
/*  353 */     this.cDebe.setHorizontalAlignment(4);
/*  354 */     this.gbLayout.setConstraints(this.cDebe, cons);
/*  355 */     this.datosCuentas.add(this.cDebe);
/*  356 */     cons.gridy = 1;
/*  357 */     this.cHaber.setHorizontalAlignment(4);
/*  358 */     this.gbLayout.setConstraints(this.cHaber, cons);
/*  359 */     this.datosCuentas.add(this.cHaber);
/*  360 */     cons.gridy = 2;
/*  361 */     cons.insets.top = 3;
/*  362 */     this.cSaldo.setHorizontalAlignment(4);
/*  363 */     this.gbLayout.setConstraints(this.cSaldo, cons);
/*  364 */     this.datosCuentas.add(this.cSaldo);
/*      */ 
/*  366 */     getContentPane().add(this.scrollPane, "Center");
/*  367 */     getContentPane().add(this.barra, "First");
/*  368 */     getContentPane().add(this.datosCuentas, "Last");
/*  369 */     importesTotales();
/*  370 */     setCursor(Cursor.getPredefinedCursor(0));
/*  371 */     this.tabla.changeSelection(this.tabla.getRowCount() - 1, 0, true, true);
/*      */   }
/*      */ 
/*      */   public void actionPerformed(ActionEvent e) {
/*  375 */     String cmd = e.getActionCommand();
/*      */ 
/*  378 */     if ("seleccionar".equals(cmd)) {
/*  379 */       modificarAsiento();
/*  380 */     } else if ("borrar".equals(cmd)) {
/*  381 */       borrarAsiento();
/*  382 */     } else if ("repetir".equals(cmd)) {
/*  383 */       if ((this.Buscar) && (!this.comienzo.equals("")))
/*  384 */         buscarDato(this.lastSearch);
/*      */     }
/*  386 */     else if ("imprimir".equals(cmd)) {
/*  387 */       imprimirTabla();
/*  388 */     } else if ("vistaprevia".equals(cmd)) {
/*  389 */       new PrintPreview(this);
/*  390 */     } else if ("fuentes".equals(cmd)) {
/*  391 */       String fuentePrevia = this.tabla.getFont().getFamily();
/*  392 */       int tamanoPrevio = this.tabla.getFont().getSize();
/*  393 */       MostrarFuentes dlg = new MostrarFuentes(Inicio.frame, Mensajes.getString("fuentes"), true, fuentePrevia, tamanoPrevio);
/*      */ 
/*  395 */       mostrarDialogo(dlg);
/*  396 */       if (dlg.isCambiar())
/*  397 */         this.tabla.setFont(new Font(dlg.getFuente(), 0, dlg.getTamano()));
/*      */     }
/*  399 */     else if ("exportar".equals(cmd)) {
/*  400 */       exportarA_archivo();
/*      */     }
/*      */     else
/*      */     {
/*      */       TablaToCsv object;
/*  401 */       if ("csv".equals(cmd)) {
/*  402 */         object = new TablaToCsv(this.model, "Diario");
/*      */       } else {
/*  404 */         if ("asiento".equals(cmd)) {
/*  405 */           dialogoSeleccion(true, 2);
/*  406 */           if ((this.Accion) && (this.Buscar))
/*  407 */             buscarDato(0);
/*      */           else
/*  409 */             this.datos.seleccionEstandar(this.comienzo, this.fin, 1);
/*      */         }
/*  411 */         else if ("fecha".equals(cmd)) {
/*  412 */           dialogoSeleccion(true, 1);
/*  413 */           if ((this.Accion) && (this.Buscar))
/*  414 */             buscarDato(1);
/*      */           else
/*  416 */             this.datos.seleccionEstandar(this.comienzo, this.fin, 3);
/*      */         }
/*  418 */         else if ("docu".equals(cmd)) {
/*  419 */           dialogoSeleccion(false, 2);
/*  420 */           if ((this.Accion) && (this.Buscar))
/*  421 */             buscarDato(2);
/*      */           else
/*  423 */             this.datos.seleccionTexto("ast.documento", this.comienzo);
/*      */         }
/*  425 */         else if ("cuenta".equals(cmd)) {
/*  426 */           dialogoSeleccion(true, 0);
/*  427 */           if ((this.Accion) && (this.Buscar))
/*  428 */             buscarDato(3);
/*      */           else
/*  430 */             this.datos.seleccionEstandar(this.comienzo, this.fin, 2);
/*      */         }
/*  432 */         else if ("concepto".equals(cmd)) {
/*  433 */           dialogoSeleccion(false, 2);
/*  434 */           if ((this.Accion) && (this.Buscar))
/*  435 */             buscarDato(4);
/*      */           else
/*  437 */             this.datos.seleccionTexto("apt.concepto", this.comienzo);
/*      */         }
/*  439 */         else if ("importe".equals(cmd)) {
/*  440 */           dialogoSeleccion(true, 2);
/*  441 */           if ((this.Accion) && (this.Buscar))
/*  442 */             buscarDato(6);
/*      */           else
/*  444 */             this.datos.seleccionEstandar(this.comienzo, this.fin, 4);
/*      */         }
/*  446 */         else if ("marca".equals(cmd)) {
/*  447 */           dialogoSeleccion(false, 2);
/*  448 */           if ((this.Accion) && (this.Buscar))
/*  449 */             buscarDato(7);
/*      */           else
/*  451 */             this.datos.seleccionEstandar(this.comienzo, "", 5);
/*      */         }
/*  453 */         else if ("completo".equals(cmd)) {
/*  454 */           this.Accion = true;
/*  455 */           this.datos.seleccionTotal();
/*      */         }
/*  457 */         if (this.Accion) {
/*  458 */           diario_renovarTabla();
/*      */         }
/*  460 */         this.Accion = false;
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private void borrarAsiento()
/*      */   {
/*  468 */     int num = Integer.parseInt(asiento_selec());
/*  469 */     if (borrarAsiento(num)) {
/*  470 */       JOptionPane.showMessageDialog(getContentPane(), Mensajes.getString("asiento") + " " + num + "\n" + Mensajes.getString("DelWsucces"));
/*      */ 
/*  472 */       diario_renovarTabla();
/*      */     }
/*      */   }
/*      */ 
/*      */   private void modificarAsiento()
/*      */   {
/*  481 */     String num = asiento_selec();
/*      */ 
/*  483 */     if (!num.equals("Error")) {
/*  484 */       String EoR = "";
/*  485 */       if (this.tabla.getValueAt(this.selectedRow, 7) != null) {
/*  486 */         EoR = this.tabla.getValueAt(this.selectedRow, 7).toString();
/*      */       }
/*  488 */       if ((EoR.equals("E")) || (EoR.equals("R"))) {
/*  489 */         Inicio.p.setFactura(EoR + this.tabla.getValueAt(this.selectedRow, 2).toString());
/*  490 */         Inicio.frame.modificarElemento(1);
/*      */       }
/*      */       else {
/*  493 */         Inicio.p.setAsiento(Integer.parseInt(num));
/*  494 */         Inicio.frame.modificarElemento(0);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private void addBotones(JToolBar toolBar)
/*      */   {
/*  518 */     JButton button = null;
/*      */ 
/*  521 */     button = makeNavigationButton("save", "exportar", Mensajes.getString("saveAstt"), Mensajes.getString("saveAs"));
/*      */ 
/*  524 */     toolBar.add(button);
/*      */ 
/*  526 */     button = makeNavigationButton("csv24", "csv", Mensajes.getString("saveAsCsvtt"), Mensajes.getString("saveAsCsv"));
/*      */ 
/*  529 */     toolBar.add(button);
/*  530 */     toolBar.addSeparator(new Dimension(5, 0));
/*      */ 
/*  533 */     button = makeNavigationButton("fonts", "fuentes", Mensajes.getString("selFonttt"), Mensajes.getString("fuentes"));
/*      */ 
/*  536 */     toolBar.add(button);
/*      */ 
/*  539 */     button = makeNavigationButton("print24", "imprimir", Mensajes.getString("printtt"), Mensajes.getString("print"));
/*      */ 
/*  545 */     button = makeNavigationButton("preview", "vistaprevia", Mensajes.getString("previewtt"), Mensajes.getString("preview"));
/*      */ 
/*  548 */     toolBar.add(button);
/*      */ 
/*  550 */     toolBar.addSeparator(new Dimension(20, 0));
/*      */ 
/*  553 */     button = makeNavigationButton("asiento", "asiento", Mensajes.getString("selBuNumAstt"), Mensajes.getString("asiento"));
/*      */ 
/*  556 */     toolBar.add(button);
/*  557 */     toolBar.addSeparator(new Dimension(10, 0));
/*      */ 
/*  559 */     button = makeNavigationButton("date32", "fecha", Mensajes.getString("selBuFechatt"), Mensajes.getString("fecha"));
/*      */ 
/*  562 */     toolBar.add(button);
/*  563 */     toolBar.addSeparator(new Dimension(10, 0));
/*      */ 
/*  565 */     button = makeNavigationButton("docu", "docu", Mensajes.getString("selBuDoctt"), Mensajes.getString("documento"));
/*      */ 
/*  568 */     toolBar.add(button);
/*  569 */     toolBar.addSeparator(new Dimension(10, 0));
/*      */ 
/*  571 */     button = makeNavigationButton("cuentas", "cuenta", Mensajes.getString("selBuCtastt"), Mensajes.getString("cuentas"));
/*      */ 
/*  574 */     toolBar.add(button);
/*  575 */     toolBar.addSeparator(new Dimension(10, 0));
/*      */ 
/*  577 */     button = makeNavigationButton("concepto", "concepto", Mensajes.getString("selBuContt"), Mensajes.getString("concepto"));
/*      */ 
/*  580 */     toolBar.add(button);
/*  581 */     toolBar.addSeparator(new Dimension(10, 0));
/*      */ 
/*  583 */     button = makeNavigationButton("importe", "importe", Mensajes.getString("selBuImptt"), Mensajes.getString("importe"));
/*      */ 
/*  586 */     toolBar.add(button);
/*  587 */     toolBar.addSeparator(new Dimension(10, 0));
/*      */ 
/*  589 */     button = makeNavigationButton("marca", "marca", Mensajes.getString("selBuMarcatt"), Mensajes.getString("marca"));
/*      */ 
/*  592 */     toolBar.add(button);
/*  593 */     toolBar.addSeparator(new Dimension(10, 0));
/*      */ 
/*  595 */     button = makeNavigationButton("retable", "completo", Mensajes.getString("listCompletott"), Mensajes.getString("completo"));
/*      */ 
/*  598 */     toolBar.add(button);
/*  599 */     toolBar.addSeparator(new Dimension(10, 0));
/*      */ 
/*  601 */     button = makeNavigationButton("refind", "repetir", Mensajes.getString("reFindtt"), Mensajes.getString("repetir"));
/*      */ 
/*  604 */     toolBar.add(button);
/*      */   }
/*      */ 
/*      */   private JButton makeNavigationButton(String imageName, String actionCommand, String toolTipText, String altText)
/*      */   {
/*  611 */     JButton button = makeNavigationButton(imageName, toolTipText, altText);
/*  612 */     button.setActionCommand(actionCommand);
/*  613 */     button.addActionListener(this);
/*  614 */     return button;
/*      */   }
/*      */ 
/*      */   private void buscarDato(int campo)
/*      */   {
/*  623 */     this.Accion = false;
/*  624 */     this.lastSearch = campo;
/*  625 */     String patron = this.comienzo.toUpperCase();
/*  626 */     int numFilas = this.tabla.getRowCount(); int x = 0;
/*  627 */     for (x = this.selectedRow + 1; x < numFilas; x++) {
/*  628 */       if (campo == 6) {
/*  629 */         double valorTabla = ((Double)this.tabla.getValueAt(x, campo)).doubleValue();
/*  630 */         if (Math.rint(valorTabla * 100.0D) / 100.0D == Math.rint(Double.parseDouble(patron) * 100.0D) / 100.0D) {
/*  631 */           this.tabla.changeSelection(x, 0, false, false);
/*  632 */           break;
/*      */         }
/*  634 */       } else if (this.tabla.getValueAt(x, campo).toString().toUpperCase().indexOf(patron) != -1) {
/*  635 */         this.tabla.changeSelection(x, 0, false, false);
/*  636 */         break;
/*      */       }
/*      */     }
/*  639 */     if (x == numFilas) {
/*  640 */       int volver = JOptionPane.showConfirmDialog(getContentPane(), Mensajes.getString("finTabla"), Mensajes.getString("confirma"), 0);
/*      */ 
/*  643 */       if (volver == 0) {
/*  644 */         this.tabla.changeSelection(0, 0, false, false);
/*  645 */         if (this.tabla.getValueAt(0, campo).toString().toUpperCase().indexOf(patron) != -1) {
/*  646 */           return;
/*      */         }
/*  648 */         buscarDato(this.lastSearch);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private String asiento_selec()
/*      */   {
/*  659 */     if (this.selectedRow != -1) {
/*  660 */       return this.tabla.getValueAt(this.selectedRow, 0).toString();
/*      */     }
/*  662 */     return "Error";
/*      */   }
/*      */ 
/*      */   private void diario_renovarTabla()
/*      */   {
/*  671 */     if (this.datos.conexionCerrada()) {
/*  672 */       return;
/*      */     }
/*  674 */     setCursor(Cursor.getPredefinedCursor(3));
/*  675 */     this.tabla.removeAll();
/*      */     try {
/*  677 */       this.model = new ScrollableTableModel(this.datos.resultado(), this.nombres_col);
/*      */     } catch (SQLException e) {
/*  679 */       e.printStackTrace();
/*      */     }
/*  681 */     this.tabla_ord.setTableModel(this.model);
/*  682 */     this.tabla.setModel(this.tabla_ord);
/*  683 */     this.tabla.setSelectionMode(0);
/*  684 */     ListSelectionModel rowSM = this.tabla.getSelectionModel();
/*  685 */     rowSM.addListSelectionListener(new ListSelectionListener()
/*      */     {
/*      */       public void valueChanged(ListSelectionEvent arg0) {
/*  688 */         TablaDiario.this.diarioSeleccionTabla(arg0);
/*      */       }
/*      */     });
/*  691 */     this.columna = this.tabla.getColumnModel().getColumn(0);
/*  692 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.06D));
/*  693 */     this.columna.setCellRenderer(new DerechaColorRenderer());
/*  694 */     this.columna = this.tabla.getColumnModel().getColumn(1);
/*  695 */     this.columna.setCellRenderer(new FechaColorRenderer());
/*  696 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.13D));
/*  697 */     this.columna = this.tabla.getColumnModel().getColumn(2);
/*  698 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.11D));
/*  699 */     this.columna = this.tabla.getColumnModel().getColumn(3);
/*  700 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.11D));
/*  701 */     this.columna.setCellRenderer(new DerechaColorRenderer());
/*  702 */     this.columna = this.tabla.getColumnModel().getColumn(4);
/*  703 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.36D));
/*  704 */     this.columna = this.tabla.getColumnModel().getColumn(5);
/*  705 */     this.columna.setCellRenderer(new CentroColorRenderer());
/*  706 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.05D));
/*  707 */     this.columna = this.tabla.getColumnModel().getColumn(6);
/*  708 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.15D));
/*  709 */     this.columna.setCellRenderer(new ImporteColorRenderer());
/*  710 */     this.columna = this.tabla.getColumnModel().getColumn(7);
/*  711 */     this.columna.setCellRenderer(new CentroColorRenderer());
/*  712 */     this.columna.setPreferredWidth((int)(this.anchoTabla * 0.03D));
/*  713 */     this.tabla.setDefaultRenderer(Object.class, new GeneralColorRenderer());
/*  714 */     importesTotales();
/*  715 */     setCursor(Cursor.getPredefinedCursor(0));
/*  716 */     this.tabla.changeSelection(this.tabla.getRowCount() - 1, 0, true, true);
/*      */   }
/*      */ 
/*      */   private void diarioSeleccionTabla(ListSelectionEvent e)
/*      */   {
/*  727 */     SeleccionTabla(e);
/*  728 */     if (this.selectedRow > this.tabla.getRowCount() - 1) {
/*  729 */       this.selectedRow = (this.tabla.getRowCount() - 1);
/*      */     }
/*  731 */     if (this.selectedRow != -1)
/*  732 */       colocar_datosCuenta(this.tabla.getValueAt(this.selectedRow, 3).toString());
/*      */   }
/*      */ 
/*      */   private void colocar_datosCuenta(String cuenta)
/*      */   {
/*      */     try
/*      */     {
/*  744 */       int codigo = Integer.parseInt(cuenta);
/*  745 */       ManejoSubcuentas subcuentaM = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  746 */       if (subcuentaM.datos(codigo) != null) {
/*  747 */         this.nombre.setText(subcuentaM.datos(codigo).getNombre());
/*  748 */         this.saldo.setText(this.fn.format(subcuentaM.datos(codigo).getSaldo()));
/*      */       }
/*      */     } catch (NumberFormatException e) {
/*  751 */       e.printStackTrace();
/*      */     }
/*      */   }
/*      */ 
/*      */   private boolean borrarAsiento(int numero)
/*      */   {
/*  762 */     ManejoAsientos asientoM = new ManejoAsientos(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  763 */     int id_asto = asientoM.datos(true, numero).getId_asiento();
/*  764 */     String marca = asientoM.datos(true, numero).getMarca();
/*  765 */     String documento = (marca.equals("E")) || (marca.equals("R")) ? asientoM.datos(true, numero).getDocumento() : "";
/*  766 */     asientoM.borrar(id_asto);
/*  767 */     if ((marca.equals("E")) || (marca.equals("R"))) {
/*  768 */       boolean emitida = marca.equals("E");
/*  769 */       ManejoFacturas facturaM = new ManejoFacturas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  770 */       int[] listaV = facturaM.vencimientosDeFactura(emitida, documento);
/*  771 */       facturaM.borrar(emitida, documento);
/*  772 */       if (listaV.length > 0) {
/*  773 */         ManejoVencimientos vencimientoM = new ManejoVencimientos(Inicio.getCEmpresa());
/*  774 */         for (int x = 0; x < listaV.length; x++) {
/*  775 */           vencimientoM.borrar(emitida, listaV[x]);
/*      */         }
/*      */       }
/*      */     }
/*  779 */     return true;
/*      */   }
/*      */ 
/*      */   private void importesTotales()
/*      */   {
/*  788 */     this.totalDebe = 0.0D;
/*  789 */     this.totalHaber = 0.0D;
/*  790 */     this.totalSaldo = 0.0D;
/*  791 */     int numFilas = this.tabla.getRowCount();
/*  792 */     for (int x = 0; x < numFilas; x++) {
/*  793 */       String DH = this.tabla.getValueAt(x, 5).toString();
/*  794 */       String importe = this.tabla.getValueAt(x, 6).toString();
/*  795 */       if (DH.equals(Mensajes.getString("debeA")))
/*  796 */         this.totalDebe += Double.parseDouble(importe);
/*      */       else {
/*  798 */         this.totalHaber += Double.parseDouble(importe);
/*      */       }
/*      */     }
/*  801 */     this.totalSaldo = (this.totalDebe - this.totalHaber);
/*  802 */     this.cDebe.setText(this.fn.format(this.totalDebe));
/*  803 */     this.cHaber.setText(this.fn.format(this.totalHaber));
/*  804 */     this.cSaldo.setText(this.fn.format(this.totalSaldo));
/*      */   }
/*      */ 
/*      */   private JMenuItem getJMenuItem()
/*      */   {
/*  813 */     if (this.jMenuItem == null) {
/*  814 */       this.jMenuItem = new JMenuItem();
/*  815 */       this.jMenuItem.setText(Mensajes.getString("modificar"));
/*  816 */       this.jMenuItem.setIcon(this.iconoModif);
/*  817 */       this.jMenuItem.setToolTipText(Mensajes.getString("modifAstott"));
/*  818 */       this.jMenuItem.addActionListener(new ActionListener()
/*      */       {
/*      */         public void actionPerformed(ActionEvent e) {
/*  821 */           TablaDiario.this.modificarAsiento();
/*      */         }
/*      */       });
/*      */     }
/*  825 */     return this.jMenuItem;
/*      */   }
/*      */ 
/*      */   private JMenuItem getJMenuItem1()
/*      */   {
/*  834 */     if (this.jMenuItem1 == null) {
/*  835 */       this.jMenuItem1 = new JMenuItem();
/*  836 */       this.jMenuItem1.setText(Mensajes.getString("suprimir"));
/*  837 */       this.jMenuItem1.setIcon(this.iconoBorrar);
/*  838 */       this.jMenuItem1.setToolTipText(Mensajes.getString("suprAstott"));
/*  839 */       this.jMenuItem1.addActionListener(new ActionListener()
/*      */       {
/*      */         public void actionPerformed(ActionEvent e) {
/*  842 */           TablaDiario.this.borrarAsiento();
/*      */         }
/*      */       });
/*      */     }
/*  846 */     return this.jMenuItem1;
/*      */   }
/*      */ 
/*      */   private void marcoActivado() {
/*  850 */     if (this.datos.conexionCerrada())
/*  851 */       this.datos.abrirConexion();
/*      */   }
/*      */ 
/*      */   private void marcoDesactivado()
/*      */   {
/*      */   }
/*      */ 
/*      */   public void cerrarConexion() {
/*  859 */     if (!this.datos.conexionCerrada())
/*  860 */       this.datos.cerrarConexion();
/*      */   }
/*      */ 
/*      */   public void renovarTabla()
/*      */   {
/*  865 */     diario_renovarTabla();
/*      */   }
/*      */ 
/*      */   protected void exportarA_archivo()
/*      */   {
/*  873 */     String EOL = FinLinea.get();
/*  874 */     AlinearCadena alinea = new AlinearCadena();
/*  875 */     JFileChooser fc = new JFileChooser();
/*  876 */     fc.setSelectedFile(new File("diario.txt"));
/*  877 */     int retorno = fc.showSaveDialog(Inicio.frame);
/*  878 */     if (retorno == 0) {
/*  879 */       FechaHoy hoy = new FechaHoy();
/*  880 */       File archivo = fc.getSelectedFile();
/*  881 */       archivo = AddExtension.txt(archivo);
/*  882 */       GrabarFichero salida = new GrabarFichero(archivo);
/*  883 */       salida.insertar(Mensajes.getString("listaDiario") + "     " + hoy.getFecha() + EOL);
/*      */ 
/*  885 */       int filas = this.tabla.getRowCount(); int columnas = this.tabla.getColumnCount();
/*  886 */       int[] x = new int[columnas];
/*  887 */       String cadena = "";
/*  888 */       for (int nCol = 0; nCol < columnas; nCol++) {
/*  889 */         boolean izq = true;
/*  890 */         this.columna = this.tabla.getColumnModel().getColumn(nCol);
/*  891 */         String titulo = (String)this.columna.getIdentifier();
/*  892 */         if (titulo.equals(Mensajes.getString("astoA"))) {
/*  893 */           x[nCol] = 5;
/*  894 */         } else if (titulo.equals(Mensajes.getString("fecha"))) {
/*  895 */           x[nCol] = 11;
/*  896 */         } else if (titulo.equals(Mensajes.getString("doc"))) {
/*  897 */           x[nCol] = 15;
/*  898 */         } else if (titulo.equals(Mensajes.getString("cuenta"))) {
/*  899 */           x[nCol] = 9;
/*  900 */         } else if (titulo.equals(Mensajes.getString("concepto"))) {
/*  901 */           x[nCol] = 35;
/*  902 */         } else if (titulo.equals(Mensajes.getString("debeHaberA"))) {
/*  903 */           x[nCol] = 3;
/*  904 */         } else if (titulo.equals(Mensajes.getString("importe"))) {
/*  905 */           x[nCol] = 12;
/*  906 */           izq = false;
/*  907 */         } else if (titulo.equals(Mensajes.getString("marcaA"))) {
/*  908 */           x[nCol] = 2;
/*      */         }
/*  910 */         if (izq)
/*  911 */           cadena = cadena + alinea.Izquierda(titulo, x[nCol]);
/*      */         else {
/*  913 */           cadena = cadena + alinea.Derecha(titulo, x[nCol]);
/*      */         }
/*      */       }
/*  916 */       salida.insertar(cadena + EOL);
/*  917 */       cadena = "";
/*  918 */       for (int nCol = 0; nCol < columnas; nCol++) {
/*  919 */         for (int nCarac = 0; nCarac < x[nCol]; nCarac++) {
/*  920 */           cadena = cadena + "-";
/*      */         }
/*      */       }
/*  923 */       salida.insertar(cadena + EOL);
/*      */ 
/*  925 */       for (int nRow = 0; nRow < filas; nRow++) {
/*  926 */         cadena = "";
/*  927 */         for (int nCol = 0; nCol < columnas; nCol++) {
/*  928 */           int col = this.tabla.getColumnModel().getColumn(nCol).getModelIndex();
/*  929 */           Object obj = this.tabla_ord.getValueAt(nRow, col);
/*  930 */           if ((obj instanceof Double)) {
/*  931 */             String str = this.fn.format(obj);
/*  932 */             cadena = cadena + alinea.Derecha(str, x[nCol]);
/*  933 */           } else if ((obj instanceof Date)) {
/*  934 */             String cad = obj.toString();
/*  935 */             int year = Integer.parseInt(cad.substring(0, 4));
/*  936 */             int month = Integer.parseInt(cad.substring(5, 7)) - 1;
/*  937 */             int date = Integer.parseInt(cad.substring(8));
/*  938 */             GregorianCalendar calendario = new GregorianCalendar();
/*  939 */             calendario.set(year, month, date);
/*  940 */             SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
/*  941 */             String str = sdf.format(calendario.getTime());
/*  942 */             cadena = cadena + alinea.Izquierda(str, x[nCol]);
/*      */           } else {
/*  944 */             String str = obj.toString();
/*  945 */             cadena = cadena + alinea.Izquierda(str, x[nCol]);
/*      */           }
/*      */         }
/*  948 */         salida.insertar(cadena + EOL);
/*      */       }
/*  950 */       salida.insertar(EOL);
/*  951 */       salida.insertar(alinea.Derecha("Totales:   Debe: " + alinea.Derecha(this.fn.format(this.totalDebe), 12) + "   Haber: " + alinea.Derecha(this.fn.format(this.totalHaber), 12) + "   Saldo: " + alinea.Derecha(this.fn.format(this.totalSaldo), 12), 85));
/*      */ 
/*  955 */       salida.cerrar();
/*      */     }
/*      */   }
/*      */ 
/*      */   private void imprimirTabla() {
/*  960 */     PrinterJob printerJob = PrinterJob.getPrinterJob();
/*  961 */     PageFormat userFormat = printerJob.pageDialog(printerJob.defaultPage());
/*  962 */     printerJob.setPrintable(this, userFormat);
/*  963 */     if (printerJob.printDialog()) {
/*  964 */       this.m_maxNumPage = 1;
/*  965 */       setCursor(Cursor.getPredefinedCursor(3));
/*      */       try
/*      */       {
/*  968 */         printerJob.print();
/*      */       } catch (PrinterException e) {
/*  970 */         System.err.println(Mensajes.getString("errPrint") + ": " + e);
/*      */       }
/*  972 */       setCursor(Cursor.getPredefinedCursor(0));
/*      */     }
/*      */   }
/*      */ 
/*      */   public int print(Graphics pg, PageFormat pageFormat, int pageIndex)
/*      */     throws PrinterException
/*      */   {
/*  979 */     if (pageIndex >= this.m_maxNumPage) {
/*  980 */       return 1;
/*      */     }
/*      */ 
/*  983 */     FechaHoy hoy = new FechaHoy();
/*  984 */     int anchoImporte = 0;
/*      */ 
/*  986 */     pg.translate((int)pageFormat.getImageableX(), (int)pageFormat.getImageableY());
/*      */ 
/*  988 */     int wPage = 0;
/*  989 */     int hPage = 0;
/*  990 */     if (pageFormat.getOrientation() == 1) {
/*  991 */       wPage = (int)pageFormat.getImageableWidth();
/*  992 */       hPage = (int)pageFormat.getImageableHeight();
/*      */     } else {
/*  994 */       wPage = (int)pageFormat.getImageableWidth();
/*  995 */       wPage += wPage / 2;
/*  996 */       hPage = (int)pageFormat.getImageableHeight();
/*  997 */       pg.setClip(0, 0, wPage, hPage);
/*      */     }
/*  999 */     int y = 0;
/* 1000 */     pg.setFont(this.tabla.getFont().deriveFont(1, this.tabla.getFont().getSize2D() + 2.0F));
/* 1001 */     pg.setColor(Color.black);
/*      */ 
/* 1003 */     FontMetrics fm = pg.getFontMetrics();
/* 1004 */     y += fm.getAscent();
/* 1005 */     pg.drawString(Mensajes.getString("diario") + "      " + hoy.getFecha(), 0, y);
/* 1006 */     y += 20;
/* 1007 */     Font headerFont = this.tabla.getFont().deriveFont(1);
/* 1008 */     pg.setFont(headerFont);
/* 1009 */     fm = pg.getFontMetrics();
/*      */ 
/* 1011 */     TableColumnModel colModel = this.tabla.getColumnModel();
/* 1012 */     int nColumns = colModel.getColumnCount();
/* 1013 */     int[] x = new int[nColumns];
/* 1014 */     x[0] = 0;
/*      */ 
/* 1016 */     int h = fm.getAscent();
/* 1017 */     y += h;
/*      */ 
/* 1021 */     for (int nCol = 0; nCol < nColumns; nCol++) {
/* 1022 */       TableColumn tk = colModel.getColumn(nCol);
/* 1023 */       int width = tk.getWidth();
/* 1024 */       if (x[nCol] + width > wPage) {
/* 1025 */         nColumns = nCol;
/* 1026 */         break;
/*      */       }
/* 1028 */       if (nCol + 1 < nColumns) {
/* 1029 */         x[(nCol + 1)] = (x[nCol] + width);
/*      */       }
/* 1031 */       String titleP = (String)tk.getIdentifier();
/* 1032 */       if (titleP.equals(Mensajes.getString("importe"))) {
/* 1033 */         anchoImporte = tk.getWidth() * 80 / 100;
/*      */       }
/* 1035 */       pg.drawString(titleP, x[nCol], y);
/*      */     }
/*      */ 
/* 1038 */     pg.setFont(this.tabla.getFont());
/* 1039 */     fm = pg.getFontMetrics();
/*      */ 
/* 1041 */     int header = y;
/* 1042 */     h = fm.getHeight();
/* 1043 */     int rowH = Math.max((int)(h * 1.5D), 10);
/* 1044 */     int rowPerPage = (hPage - header) / rowH;
/* 1045 */     this.m_maxNumPage = Math.max((int)Math.ceil(this.tabla.getRowCount() / rowPerPage), 1);
/*      */ 
/* 1049 */     int iniRow = pageIndex * rowPerPage;
/* 1050 */     int endRow = Math.min(this.tabla.getRowCount(), iniRow + rowPerPage);
/*      */ 
/* 1052 */     for (int nRow = iniRow; nRow < endRow; nRow++) {
/* 1053 */       y += h;
/* 1054 */       for (int nCol = 0; nCol < nColumns; nCol++) {
/* 1055 */         pg.setColor(Color.black);
/* 1056 */         int col = this.tabla.getColumnModel().getColumn(nCol).getModelIndex();
/* 1057 */         Object obj = this.tabla_ord.getValueAt(nRow, col);
/* 1058 */         String str = "";
/* 1059 */         if (obj != null) {
/* 1060 */           str = obj.toString();
/*      */         }
/* 1062 */         if ((obj instanceof Double)) {
/* 1063 */           str = this.fn.format(obj);
/*      */ 
/* 1065 */           FontRenderContext frc = ((Graphics2D)pg).getFontRenderContext();
/* 1066 */           TextLayout layout = new TextLayout(str, this.tabla.getFont(), frc);
/* 1067 */           float w = layout.getAdvance();
/* 1068 */           pg.drawString(str, x[nCol] + (anchoImporte - (int)w), y);
/* 1069 */         } else if ((obj instanceof Date)) {
/* 1070 */           String cad = obj.toString();
/* 1071 */           int year = Integer.parseInt(cad.substring(0, 4));
/* 1072 */           int month = Integer.parseInt(cad.substring(5, 7)) - 1;
/* 1073 */           int date = Integer.parseInt(cad.substring(8));
/* 1074 */           GregorianCalendar calendario = new GregorianCalendar();
/* 1075 */           calendario.set(year, month, date);
/* 1076 */           SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
/* 1077 */           str = sdf.format(calendario.getTime());
/* 1078 */           pg.drawString(str, x[nCol], y);
/*      */         } else {
/* 1080 */           pg.drawString(str, x[nCol], y);
/*      */         }
/*      */       }
/*      */     }
/* 1084 */     if (pageIndex == this.m_maxNumPage - 1) {
/* 1085 */       pg.setFont(this.tabla.getFont().deriveFont(0, 9.0F));
/* 1086 */       y += 20;
/* 1087 */       pg.drawString(Mensajes.getString("totListDia") + ":", 70, y);
/* 1088 */       pg.drawString(this.cDebe.getText(), 220, y);
/* 1089 */       pg.drawString(this.cHaber.getText(), 290, y);
/* 1090 */       pg.drawString(this.cSaldo.getText(), 370, y);
/*      */     }
/* 1092 */     System.gc();
/* 1093 */     return 0;
/*      */   }
/*      */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.TablaDiario
 * JD-Core Version:    0.6.2
 */