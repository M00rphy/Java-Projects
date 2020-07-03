/*      */ package contaes;
/*      */ 
/*      */ import almacen2.data.PIOObject;
/*      */ import almacen2.gui.AlmacenJInternalFrame;
/*      */ import almacen2.gui.AlmacenesJDialog;
/*      */ import almacen2.gui.BuscarRefOldJDialog;
/*      */ import almacen2.gui.ListarProductosOldJInternalFrame;
/*      */ import almacen2.gui.OldAlmacenJInternalFrame;
/*      */ import almacen2.gui.listados.ListadosJInternalFrame;
/*      */ import almacen2.gui.pedidos.PedidosClientesJInternalFrame;
/*      */ import almacen2.gui.pedidos.PedidosJInternalFrame;
/*      */ import contaes.asientosModelo.Lista;
/*      */ import contaes.auxiliar.ImageJDesktopPane;
/*      */ import contaes.auxiliar.MyDefaultDesktopManager;
/*      */ import contaes.cuentas.ComparativaCuentasDlg;
/*      */ import contaes.cuentas.ComparativaSaldosDlg;
/*      */ import contaes.cuentas.MarcoParaGraficos;
/*      */ import contaes.cuentas.Sustitucion;
/*      */ import contaes.dialogosAuxiliares.BackupProgramPathJDialog;
/*      */ import contaes.dialogosFunciones.AperturaEjercicio;
/*      */ import contaes.dialogosFunciones.BorrarVencimientos;
/*      */ import contaes.dialogosFunciones.BuscarDescuadres;
/*      */ import contaes.dialogosFunciones.Calculadora;
/*      */ import contaes.dialogosFunciones.CierreEjercicio;
/*      */ import contaes.dialogosFunciones.ComprobarVencimientos;
/*      */ import contaes.dialogosFunciones.ComprobarVencimientosCobrar;
/*      */ import contaes.dialogosFunciones.CrearFicheroCSB;
/*      */ import contaes.dialogosFunciones.FormasPago;
/*      */ import contaes.dialogosFunciones.LiquidacionIVA;
/*      */ import contaes.dialogosFunciones.Paises;
/*      */ import contaes.dialogosFunciones.PerdidasGanancias;
/*      */ import contaes.dialogosFunciones.RegenerarSaldosSubcuentas;
/*      */ import contaes.dialogosFunciones.RegularizacionExistencias;
/*      */ import contaes.dialogosFunciones.Reordenar;
/*      */ import contaes.dialogosFunciones.ReordenarDiario;
/*      */ import contaes.dialogosFunciones.TiposIva;
/*      */ import contaes.dialogosFunciones.TraspasarAsientos;
/*      */ import contaes.dialogosFunciones.TraspasarFacturas;
/*      */ import contaes.empresas.AboutBox;
/*      */ import contaes.empresas.CreaEjercicio;
/*      */ import contaes.empresas.GestionaEmpresas;
/*      */ import contaes.empresas.LastSelectedEmpresa;
/*      */ import contaes.empresas.SeleccionEmpresa;
/*      */ import contaes.empresas.UsuariosJDialog;
/*      */ import contaes.informes.view.ContrapartidasJDialog;
/*      */ import contaes.informes.view.DistribucionPartidasJDialog;
/*      */ import contaes.informes.view.RatiosJDialog;
/*      */ import contaes.informes.view.ResumenFacturasJDialog;
/*      */ import contaes.listados.Balance;
/*      */ import contaes.listados.Balance08;
/*      */ import contaes.listados.CuentaPyG;
/*      */ import contaes.listados.CuentaPyG08;
/*      */ import contaes.listados.ECPN;
/*      */ import contaes.listados.LiquidacionFiscalIVA;
/*      */ import contaes.listados.Listado;
/*      */ import contaes.listados.ListadoFacturas;
/*      */ import contaes.listados.ListadoNiveles;
/*      */ import contaes.listados.OperacionesTerceros;
/*      */ import contaes.manejoDatos.CheckVersionInWeb;
/*      */ import contaes.manejoDatos.ManejoEmpresas;
/*      */ import contaes.manejoDatos.auxiliar.ConfiguracionBean;
/*      */ import contaes.manejoDatos.auxiliar.DataBasesBackup;
/*      */ import contaes.manejoDatos.funciones.ImportarDiario;
/*      */ import contaes.manejoDatos.funciones.ImportarSubcuentas;
/*      */ import facturacion.view.FacturasJInternalFrame;
/*      */ import facturacion.view.FacturasProveedoresJInternalFrame;
/*      */ import facturacion.view.TablaFacturas;
/*      */ import facturacion.view.TablaFacturasCompras;
/*      */ import internationalization.Mensajes;
/*      */ import java.awt.BorderLayout;
/*      */ import java.awt.Container;
/*      */ import java.awt.Desktop;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.Point;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.Toolkit;
/*      */ import java.awt.Window;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.ActionListener;
/*      */ import java.awt.event.ComponentAdapter;
/*      */ import java.awt.event.ComponentEvent;
/*      */ import java.awt.event.WindowAdapter;
/*      */ import java.awt.event.WindowEvent;
/*      */ import java.beans.PropertyVetoException;
/*      */ import java.io.File;
/*      */ import java.io.IOException;
/*      */ import java.io.PrintStream;
/*      */ import java.net.URI;
/*      */ import java.net.URISyntaxException;
/*      */ import java.net.URL;
/*      */ import java.util.ArrayList;
/*      */ import java.util.GregorianCalendar;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import java.util.logging.Level;
/*      */ import java.util.logging.Logger;
/*      */ import javax.swing.BorderFactory;
/*      */ import javax.swing.ImageIcon;
/*      */ import javax.swing.JButton;
/*      */ import javax.swing.JDialog;
/*      */ import javax.swing.JFrame;
/*      */ import javax.swing.JInternalFrame;
/*      */ import javax.swing.JMenu;
/*      */ import javax.swing.JMenuBar;
/*      */ import javax.swing.JMenuItem;
/*      */ import javax.swing.JOptionPane;
/*      */ import javax.swing.JToolBar;
/*      */ import pos.view.MediosPagoJDialog;
/*      */ import pos.view.ResumenDiario;
/*      */ import pos.view.TerminalVentas;
/*      */ import pos.view.TicketsToFacturacionJDialog;
/*      */ import pos.view.VendedoresJDialog;
/*      */ 
/*      */ public class MarcoInicio extends JFrame
/*      */   implements ActionListener
/*      */ {
/*  136 */   boolean firstStart = true;
/*      */   private static final String SELECCIONAREMPRESA = "selempresa";
/*      */   private static final String GESTIONEMPRESAS = "gesempresas";
/*      */   private static final String ABRIREJERCICIO = "abrirejercicio";
/*      */   private static final String BORRAREJERCICIO = "borrarejercicio";
/*      */   private static final String GESTIONUSUARIOS = "gestionusuarios";
/*      */   private static final String DESINSTALAR = "desinstalar";
/*      */   private static final String SALIR = "salir";
/*      */   private static final String ASIENTOS = "asientos";
/*      */   private static final String ASIENTOSAUT = "asientosaut";
/*      */   private static final String CONSULTASDIARIO = "consultasdiario";
/*      */   private static final String CONCEPTOS = "conceptos";
/*      */   private static final String REGENERARSALDOS = "regenerarsaldos";
/*      */   private static final String DESCUADRES = "descuadres";
/*      */   private static final String RENUMERAR = "renumerar";
/*      */   private static final String INTROFACTURAS = "facturas";
/*      */   private static final String GESTIONEMITIDAS = "gestionEmitidas";
/*      */   private static final String GESTIONRECIBIDAS = "gestionRecibidas";
/*      */   private static final String LISTAEMITIDAS = "emitidas";
/*      */   private static final String LISTARECIBIDAS = "recibidas";
/*      */   private static final String VENCIMIENTOS = "vencimientos";
/*      */   private static final String COMPVENCIMIENTOS = "comprobarVencimientos";
/*      */   private static final String ELIMVENCIMIENTOS = "eliminarVencimientos";
/*      */   private static final String VENCIMIENTOSCOBRAR = "vencimientoscobrar";
/*      */   private static final String COMPVENCIMIENTOSCOBRAR = "comprobarVencimientosCobrar";
/*      */   private static final String ELIMVENCIMIENTOSCOBRAR = "eliminarVencimientosCobrar";
/*      */   private static final String GESTIONCUENTAS = "cuentas";
/*      */   private static final String BALNIVELES = "niveles";
/*      */   private static final String PYG = "pyg";
/*      */   private static final String BALANCE = "balance";
/*      */   private static final String PYG07 = "pyg07";
/*      */   private static final String BALANCE07 = "balance07";
/*      */   private static final String ECPN = "ecpn";
/*      */   private static final String REGULASTOCK = "stocks";
/*      */   private static final String ASIENTOPYG = "asientoPyG";
/*      */   private static final String APERTURA = "apertura";
/*      */   private static final String CIERRE = "cierre";
/*      */   private static final String LIQUIDAIVA = "liquidacioniva";
/*      */   private static final String LIQUIDAFISCALIVA = "liquidacionfiscaliva";
/*      */   private static final String AYUDA = "ayuda";
/*      */   private static final String INFO = "info";
/*      */   private static final String GRAFICOSCUENTAS = "graficoscuentas";
/*      */   private static final String GRAFICOSSALDOS = "graficossaldos";
/*      */   private static final String SUSTITUCION = "sustitucion";
/*      */   private static final String CALCULADORA = "calculadora";
/*      */   private static final String OPTERCEROS = "operacionesTerceros";
/*      */   private static final String CERRARTODO = "cerrartodo";
/*      */   private static final String TIPOSIVA = "tiposIVA";
/*      */   private static final String TRASPASAR = "traspasarAsientos";
/*      */   private static final String FORMASPAGO = "formasPago";
/*      */   private static final String IMPORTAR = "ImportarFacturas";
/*      */   private static final String PRODUCTOS = "productos";
/*      */   private static final String GESTIONFACTURACION = "gestionfacturas";
/*      */   private static final String INTROFACTURACION = "introduccionfacturas";
/*      */   private static final String GESTIONFACTURACIONPROV = "gestionfacturasrecibidas";
/*      */   private static final String INTROFACTURACIONPROV = "introduccionfacturasrecibidas";
/*      */   private static final String PEDIDOSPROVEEDORES = "pedidosproveedores";
/*      */   private static final String PEDIDOSCLIENTES = "pedidosclientes";
/*      */   private static final String LISTADOSALMACEN = "listadosalmacen";
/*      */   private static final String RESUMENMENSUALCLIENTES = "resumenclientes";
/*      */   private static final String RESUMENMENSUALPROVEEDORES = "resumenproveedores";
/*      */   private static final String DISTRIBUCIONPARTIDAS = "distribucionpartidas";
/*      */   private static final String ORIGENESDESTINOS = "origenesDestinos";
/*      */   private static final String RATIOS = "ratios";
/*      */   private static final String ALMACENES = "almacenes";
/*      */   private static final String MEDIOSPAGO = "mediospago";
/*      */   private static final String VENDEDORES = "vendedores";
/*      */   private static final String TERMINALVENTAS = "terminalventas";
/*      */   private static final String RESUMENDIARIO = "resumendiario";
/*      */   private static final String PAISES = "paises";
/*      */   private static final String WEB = "web";
/*      */   private static final String UPDATES = "updates";
/*      */   private static final String ISUPDATE = "isupdate";
/*      */   private static final String CSB19 = "CSB19";
/*      */   private static final String CSB58 = "CSB58";
/*      */   private static final String BACKPROGRAMPATH = "backprogrampath";
/*      */   private static final String MAKEBACKUP = "makebackup";
/*      */   private static final String RESTOREBACKUP = "restorebackup";
/*      */   private static final String TPVTOFACT = "tpvtofact";
/*      */   private static final String RENUMERARFACTURACION = "renumerarfacturacion";
/*      */   private static final String RENUMERARTICKETS = "renumerartickets";
/*      */   private static final String TRASPASARFACTURASVENTAS = "traspasarfacturasventas";
/*      */   private static final String TRASPASARFACTURASCOMPRAS = "traspasarfacturascompras";
/*      */   private static final String IMPORTARSUBCUENTAS = "importarsubcuentas";
/*      */   private static final String IMPORTARDIARIO = "importardiaro";
/*      */   private static final String CONTROLCENTRE = "controlcentre";
/*      */   private static final String REGISTRO = "registro";
/*  224 */   BorderLayout bLayout = new BorderLayout();
/*  225 */   ImageJDesktopPane escritorio = new ImageJDesktopPane();
/*  226 */   JMenuBar barraMenu = new JMenuBar();
/*      */ 
/*  228 */   JToolBar herramientasPOS = new JToolBar();
/*  229 */   PanelMenuJPanel panelMenu = new PanelMenuJPanel();
/*      */ 
/*  245 */   JButton terminalPOS = null;
/*  246 */   JButton resumenPOS = null;
/*  247 */   JButton salirPOS = null;
/*  248 */   Asientos marco_asiento = null;
/*  249 */   GestionCuentas marco_cuentas = null;
/*  250 */   TablaDiario marco_tDiario = null;
/*      */ 
/*  252 */   TablaVencimientos marco_tVencimientos = null;
/*  253 */   TablaVencimientosCobrar marco_tVencimientosC = null;
/*  254 */   Conceptos marco_conceptos = null;
/*  255 */   Lista marco_Asientos_Modelo = null;
/*  256 */   CreacionFacturas marco_CreacionFacturas = null;
/*  257 */   TablaFacturasEmitidas marco_facturasEmitidas = null;
/*  258 */   TablaFacturasRecibidas marco_facturasRecibidas = null;
/*  259 */   FacturasJInternalFrame marco_facturacion = null;
/*  260 */   TablaFacturas marco_listado_Facturacion = null;
/*  261 */   FacturasProveedoresJInternalFrame marco_facturacion_p = null;
/*  262 */   TablaFacturasCompras marco_listado_Facturacion_p = null;
/*  263 */   AlmacenJInternalFrame marco_almacen = null;
/*  264 */   PedidosJInternalFrame marco_pedidos = null;
/*  265 */   PedidosClientesJInternalFrame marco_pedidos_clientes = null;
/*  266 */   ListadosJInternalFrame marco_listados_almacen = null;
/*  267 */   TerminalVentas marco_terminalVentas = null;
/*  268 */   ResumenDiario marco_ResumenVentas = null;
/*  269 */   InfoJInternalFrame marco_info = null;
/*  270 */   ControlCentreJInternalFrame centroControl = null;
/*  271 */   String titulo = "ContaEs - ";
/*      */ 
/*      */   public MarcoInicio() {
/*      */     try {
/*  275 */       setDefaultCloseOperation(3);
/*  276 */       initialize();
/*      */     } catch (Exception exception) {
/*  278 */       exception.printStackTrace();
/*      */     }
/*      */   }
/*      */ 
/*      */   private void initialize() throws Exception {
/*  283 */     addWindowListener(new CierreVentana());
/*  284 */     Rectangle bounds = readFrameBounds();
/*  285 */     setPanelMenuState();
/*  286 */     if (bounds == null) {
/*  287 */       int inset = 20;
/*  288 */       Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/*  289 */       int ancho = screenSize.width < 940 ? screenSize.width : 1004;
/*  290 */       int alto = screenSize.height < 700 ? screenSize.height : 710;
/*  291 */       setBounds(inset, inset, ancho, alto);
/*      */     } else {
/*  293 */       setBounds(bounds);
/*      */     }
/*  295 */     setTitle(this.titulo);
/*  296 */     URL icono = MarcoInicio.class.getResource("/contaes/iconos/about16.png");
/*  297 */     if (icono != null) {
/*  298 */       setIconImage(Toolkit.getDefaultToolkit().getImage(icono));
/*      */     }
/*  300 */     getContentPane().setLayout(this.bLayout);
/*  301 */     this.escritorio.setDesktopManager(new MyDefaultDesktopManager());
/*  302 */     URL fondo = MarcoInicio.class.getResource("/contaes/iconos/fondo.png");
/*  303 */     this.escritorio.setImage(new ImageIcon(fondo));
/*  304 */     this.escritorio.setFillEntireArea(true);
/*  305 */     getContentPane().add(this.escritorio, "Center");
/*      */ 
/*  308 */     addComponentListener(new ComponentAdapter()
/*      */     {
/*      */       public void componentResized(ComponentEvent e)
/*      */       {
/*  312 */         MarcoInicio.this.colocarInfo();
/*      */       }
/*      */     });
/*  316 */     while (this.firstStart) {
/*  317 */       if (hayEmpresas()) {
/*  318 */         LastSelectedEmpresa lastSelected = new LastSelectedEmpresa();
/*  319 */         if (lastSelected.isCreated()) {
/*  320 */           setTitle(this.titulo + lastSelected.getNombreEmpresa() + " - " + Inicio.p.getEjercicio());
/*  321 */           colocarInfo(lastSelected.getNombreEmpresa());
/*  322 */           this.firstStart = false;
/*      */         } else {
/*  324 */           seleccionarEmpresa();
/*      */         }
/*      */       } else {
/*  327 */         GestionaEmpresas dlg = new GestionaEmpresas(this, Mensajes.getString("gesEmp"), true);
/*  328 */         mostrarDialogo(dlg);
/*  329 */         if (hayEmpresas()) {
/*  330 */           CreaEjercicio dlg2 = new CreaEjercicio(this, Mensajes.getString("creaEj"), true);
/*  331 */           mostrarDialogo(dlg2);
/*  332 */           saveFirstStart();
/*  333 */           seleccionarEmpresa();
/*      */         }
/*      */         else {
/*  336 */           System.exit(0);
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*  341 */     if (Inicio.p.isModoPOS()) {
/*  342 */       setExtendedState(6);
/*  343 */       setUndecorated(true);
/*  344 */       this.herramientasPOS.setRollover(true);
/*  345 */       this.herramientasPOS.setBorder(BorderFactory.createEtchedBorder(1));
/*  346 */       addBotonesPOS(this.herramientasPOS);
/*  347 */       getContentPane().add(this.herramientasPOS, "First");
/*  348 */       realizarAccion("terminalventas");
/*      */     }
/*      */     else
/*      */     {
/*  354 */       getContentPane().add(this.panelMenu, "West");
/*      */ 
/*  356 */       crearMenu(this.barraMenu);
/*  357 */       setJMenuBar(this.barraMenu);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void saveFirstStart()
/*      */   {
/*  363 */     ArrayList array = new ArrayList();
/*  364 */     GregorianCalendar fecha = new GregorianCalendar();
/*  365 */     fecha.add(2, 1);
/*  366 */     array.add("" + fecha.get(1));
/*  367 */     array.add("" + fecha.get(2));
/*  368 */     array.add("" + fecha.get(5));
/*  369 */     ConfiguracionBean config = new ConfiguracionBean();
/*  370 */     config.saveConfig("<check>", array);
/*      */   }
/*      */ 
/*      */   private GregorianCalendar readFirtsStart() {
/*  374 */     GregorianCalendar fecha = new GregorianCalendar();
/*  375 */     ArrayList array = new ArrayList();
/*  376 */     ConfiguracionBean config = new ConfiguracionBean();
/*  377 */     array = config.getConfig("<check>");
/*      */     try {
/*  379 */       if (!array.isEmpty()) {
/*  380 */         int year = Integer.parseInt((String)array.get(0));
/*  381 */         int month = Integer.parseInt((String)array.get(1));
/*  382 */         int day = Integer.parseInt((String)array.get(2));
/*  383 */         fecha.set(year, month, day);
/*      */       }
/*      */     } catch (ArrayIndexOutOfBoundsException e) {
/*  386 */       e.printStackTrace();
/*      */     }
/*  388 */     return fecha;
/*      */   }
/*      */ 
/*      */   private void setPanelMenuState() {
/*  392 */     ArrayList array = new ArrayList();
/*  393 */     ConfiguracionBean config = new ConfiguracionBean();
/*  394 */     array = config.getConfig("<menuconfig>");
/*  395 */     if (!array.isEmpty())
/*      */       try {
/*  397 */         int[] panelState = new int[8];
/*  398 */         for (int x = 0; x < 8; x++) {
/*  399 */           panelState[x] = Integer.parseInt((String)array.get(x));
/*      */         }
/*  401 */         this.panelMenu.readState(panelState);
/*      */       } catch (ArrayIndexOutOfBoundsException e) {
/*  403 */         e.printStackTrace();
/*      */       }
/*      */   }
/*      */ 
/*      */   private Rectangle readFrameBounds()
/*      */   {
/*  409 */     Rectangle bounds = null;
/*  410 */     ArrayList array = new ArrayList();
/*  411 */     ConfiguracionBean config = new ConfiguracionBean();
/*  412 */     array = config.getConfig("<lastposition>");
/*  413 */     if (!array.isEmpty()) {
/*      */       try {
/*  415 */         bounds = new Rectangle(Integer.parseInt((String)array.get(0)), Integer.parseInt((String)array.get(1)), Integer.parseInt((String)array.get(2)), Integer.parseInt((String)array.get(3)));
/*      */       }
/*      */       catch (NumberFormatException e)
/*      */       {
/*  420 */         e.printStackTrace();
/*      */       } catch (ArrayIndexOutOfBoundsException ex) {
/*  422 */         ex.printStackTrace();
/*      */       }
/*      */     }
/*  425 */     return bounds;
/*      */   }
/*      */ 
/*      */   private void colocarInfo(String empresa) {
/*  429 */     if ((this.marco_info == null) || (this.marco_info.isClosed())) {
/*  430 */       this.marco_info = new InfoJInternalFrame();
/*  431 */       this.escritorio.add(this.marco_info);
/*      */     }
/*  433 */     this.marco_info.redibujar(empresa, Inicio.p.getEjercicio(), this.escritorio.getBounds());
/*  434 */     mostrarMarco(this.marco_info);
/*      */   }
/*      */ 
/*      */   public void colocarInfo() {
/*  438 */     if ((this.marco_info == null) || (this.marco_info.isClosed())) {
/*  439 */       this.marco_info = new InfoJInternalFrame();
/*  440 */       this.escritorio.add(this.marco_info);
/*      */     }
/*  442 */     this.marco_info.redibujar(this.escritorio.getBounds());
/*  443 */     mostrarMarco(this.marco_info);
/*      */   }
/*      */ 
/*      */   public void actionPerformed(ActionEvent e)
/*      */   {
/*  448 */     String cmd = e.getActionCommand();
/*  449 */     realizarAccion(cmd);
/*      */   }
/*      */ 
/*      */   public void realizarAccion(String cmd) {
/*  453 */     if ("selempresa".equals(cmd)) {
/*  454 */       seleccionarEmpresa();
/*  455 */     } else if ("gesempresas".equals(cmd)) {
/*  456 */       GestionaEmpresas dlg = new GestionaEmpresas(this, Mensajes.getString("gesEmp"), true);
/*  457 */       mostrarDialogo(dlg);
/*  458 */     } else if ("abrirejercicio".equals(cmd)) {
/*  459 */       CreaEjercicio dlg = new CreaEjercicio(this, Mensajes.getString("creaEj"), true);
/*  460 */       mostrarDialogo(dlg);
/*  461 */     } else if ("borrarejercicio".equals(cmd)) {
/*  462 */       SeleccionEmpresa dlg = new SeleccionEmpresa(this, Mensajes.getString("suprEj"), true, true);
/*  463 */       mostrarDialogo(dlg);
/*  464 */     } else if ("gestionusuarios".equals(cmd))
/*      */     {
/*  466 */       UsuariosJDialog dlg = new UsuariosJDialog(false);
/*  467 */       mostrarDialogo(dlg);
/*  468 */     } else if ("salir".equals(cmd)) {
/*  469 */       System.exit(0);
/*  470 */     } else if ("desinstalar".equals(cmd)) {
/*  471 */       Desinstalar dlg = new Desinstalar(this, true);
/*  472 */       mostrarDialogo(dlg);
/*  473 */     } else if ("asientos".equals(cmd)) {
/*  474 */       crearMarcoAsientos();
/*  475 */       crearMarcoCuentas();
/*  476 */       mostrarMarco(this.marco_asiento);
/*  477 */     } else if ("asientosaut".equals(cmd)) {
/*  478 */       if ((this.marco_Asientos_Modelo == null) || (this.marco_Asientos_Modelo.isClosed()))
/*      */       {
/*  480 */         this.marco_Asientos_Modelo = new Lista();
/*  481 */         this.escritorio.add(this.marco_Asientos_Modelo);
/*  482 */         annadirMarcoMenu(this.marco_Asientos_Modelo);
/*      */       }
/*  484 */       mostrarMarco(this.marco_Asientos_Modelo);
/*  485 */     } else if ("consultasdiario".equals(cmd)) {
/*  486 */       if ((this.marco_tDiario == null) || (this.marco_tDiario.isClosed())) {
/*  487 */         this.marco_tDiario = new TablaDiario(getSize());
/*  488 */         this.escritorio.add(this.marco_tDiario);
/*  489 */         annadirMarcoMenu(this.marco_tDiario);
/*      */       }
/*  491 */       crearMarcoAsientos();
/*      */ 
/*  493 */       crearMarcoNuevaFactura();
/*  494 */       crearMarcoCuentas();
/*  495 */       mostrarMarco(this.marco_tDiario);
/*  496 */     } else if ("conceptos".equals(cmd)) {
/*  497 */       if ((this.marco_conceptos == null) || (this.marco_conceptos.isClosed())) {
/*  498 */         this.marco_conceptos = new Conceptos();
/*  499 */         this.escritorio.add(this.marco_conceptos);
/*  500 */         annadirMarcoMenu(this.marco_conceptos);
/*      */       }
/*  502 */       mostrarMarco(this.marco_conceptos);
/*      */     }
/*  504 */     else if ("descuadres".equals(cmd)) {
/*  505 */       BuscarDescuadres dlg = new BuscarDescuadres(this, true);
/*  506 */       mostrarDialogo(dlg);
/*  507 */     } else if ("renumerar".equals(cmd)) {
/*  508 */       ReordenarDiario dlg = new ReordenarDiario(this, true);
/*  509 */       mostrarDialogo(dlg);
/*  510 */     } else if ("facturas".equals(cmd)) {
/*  511 */       crearMarcoNuevaFactura();
/*      */ 
/*  513 */       crearMarcoCuentas();
/*      */ 
/*  515 */       mostrarMarco(this.marco_CreacionFacturas);
/*  516 */     } else if ("gestionEmitidas".equals(cmd)) {
/*  517 */       if ((this.marco_facturasEmitidas == null) || (this.marco_facturasEmitidas.isClosed())) {
/*  518 */         this.marco_facturasEmitidas = new TablaFacturasEmitidas();
/*  519 */         this.escritorio.add(this.marco_facturasEmitidas);
/*  520 */         crearMarcoNuevaFactura();
/*  521 */         crearMarcoCuentas();
/*  522 */         annadirMarcoMenu(this.marco_facturasEmitidas);
/*      */       }
/*  524 */       mostrarMarco(this.marco_facturasEmitidas);
/*  525 */     } else if ("gestionRecibidas".equals(cmd)) {
/*  526 */       if ((this.marco_facturasRecibidas == null) || (this.marco_facturasRecibidas.isClosed())) {
/*  527 */         this.marco_facturasRecibidas = new TablaFacturasRecibidas();
/*  528 */         this.escritorio.add(this.marco_facturasRecibidas);
/*  529 */         crearMarcoNuevaFactura();
/*  530 */         crearMarcoCuentas();
/*  531 */         annadirMarcoMenu(this.marco_facturasRecibidas);
/*      */       }
/*  533 */       mostrarMarco(this.marco_facturasRecibidas);
/*  534 */     } else if ("emitidas".equals(cmd)) {
/*  535 */       ListadoFacturas dlgListado = new ListadoFacturas(this, true);
/*  536 */       mostrarDialogo(dlgListado);
/*  537 */       if (dlgListado.Listar()) {
/*  538 */         List listado = dlgListado.textoListado();
/*  539 */         Listado listaEmitidas = new Listado(Mensajes.getString("lisEmit"), listado);
/*  540 */         mostrarMarcoExterno(listaEmitidas);
/*      */       }
/*  542 */     } else if ("recibidas".equals(cmd)) {
/*  543 */       ListadoFacturas dlgListado = new ListadoFacturas(this, true, false);
/*  544 */       mostrarDialogo(dlgListado);
/*  545 */       if (dlgListado.Listar()) {
/*  546 */         List listado = dlgListado.textoListado();
/*  547 */         Listado listaRecibidas = new Listado(Mensajes.getString("listReci"), listado);
/*  548 */         mostrarMarcoExterno(listaRecibidas);
/*      */       }
/*  550 */     } else if ("vencimientos".equals(cmd)) {
/*  551 */       if ((this.marco_tVencimientos == null) || (this.marco_tVencimientos.isClosed())) {
/*  552 */         this.marco_tVencimientos = new TablaVencimientos();
/*  553 */         this.escritorio.add(this.marco_tVencimientos);
/*  554 */         annadirMarcoMenu(this.marco_tVencimientos);
/*      */       }
/*  556 */       mostrarMarco(this.marco_tVencimientos);
/*  557 */     } else if ("comprobarVencimientos".equals(cmd)) {
/*  558 */       ComprobarVencimientos dlg = new ComprobarVencimientos(this, true);
/*  559 */       mostrarDialogo(dlg);
/*  560 */     } else if ("eliminarVencimientos".equals(cmd)) {
/*  561 */       BorrarVencimientos dlg = new BorrarVencimientos(this);
/*  562 */       mostrarDialogo(dlg);
/*  563 */     } else if ("vencimientoscobrar".equals(cmd)) {
/*  564 */       if ((this.marco_tVencimientosC == null) || (this.marco_tVencimientosC.isClosed())) {
/*  565 */         this.marco_tVencimientosC = new TablaVencimientosCobrar();
/*  566 */         this.escritorio.add(this.marco_tVencimientosC);
/*  567 */         annadirMarcoMenu(this.marco_tVencimientosC);
/*      */       }
/*  569 */       mostrarMarco(this.marco_tVencimientosC);
/*  570 */     } else if ("comprobarVencimientosCobrar".equals(cmd)) {
/*  571 */       ComprobarVencimientosCobrar dlg = new ComprobarVencimientosCobrar(this, true);
/*  572 */       mostrarDialogo(dlg);
/*  573 */     } else if ("eliminarVencimientosCobrar".equals(cmd)) {
/*  574 */       BorrarVencimientos dlg = new BorrarVencimientos(this, false);
/*  575 */       mostrarDialogo(dlg);
/*  576 */     } else if ("liquidacioniva".equals(cmd)) {
/*  577 */       LiquidacionIVA dlg = new LiquidacionIVA(this, Mensajes.getString("liquidIVA"), true);
/*  578 */       mostrarDialogo(dlg);
/*  579 */     } else if ("liquidacionfiscaliva".equals(cmd)) {
/*  580 */       LiquidacionFiscalIVA dlg = new LiquidacionFiscalIVA(this, Mensajes.getString("liquidFiscalIVA"), true);
/*  581 */       mostrarDialogo(dlg);
/*  582 */       if (dlg.isListar()) {
/*  583 */         Listado listado = new Listado(dlg.getTitulo(), dlg.getListado());
/*  584 */         mostrarMarcoExterno(listado);
/*      */       }
/*      */     }
/*  587 */     else if ("operacionesTerceros".equals(cmd)) {
/*  588 */       OperacionesTerceros dlg = new OperacionesTerceros(this, true);
/*  589 */       mostrarDialogo(dlg);
/*  590 */       if (dlg.isListar()) {
/*  591 */         Listado listado = new Listado(dlg.getTitulo(), dlg.getListado());
/*  592 */         mostrarMarcoExterno(listado);
/*      */       }
/*  594 */     } else if ("cuentas".equals(cmd)) {
/*  595 */       crearMarcoCuentas();
/*  596 */       mostrarMarco(this.marco_cuentas);
/*  597 */     } else if ("sustitucion".equals(cmd)) {
/*  598 */       Sustitucion dlg = new Sustitucion(this, true);
/*  599 */       mostrarDialogo(dlg);
/*  600 */     } else if ("regenerarsaldos".equals(cmd)) {
/*  601 */       RegenerarSaldosSubcuentas dlg = new RegenerarSaldosSubcuentas(this, true);
/*  602 */       mostrarDialogo(dlg);
/*  603 */     } else if ("graficoscuentas".equals(cmd)) {
/*  604 */       ComparativaCuentasDlg dlg = new ComparativaCuentasDlg(this, true);
/*  605 */       mostrarDialogo(dlg);
/*  606 */       if (dlg.isHayDatos()) {
/*  607 */         MarcoParaGraficos marco = new MarcoParaGraficos(Mensajes.getString("comparativaGrafica") + " " + Mensajes.getString("de") + " " + Mensajes.getString("cuentas"), dlg.getNCta(), dlg.getNombreCuenta(), dlg.getDatosSaldos());
/*      */ 
/*  611 */         mostrarMarcoExterno(marco);
/*      */       }
/*  613 */     } else if ("graficossaldos".equals(cmd)) {
/*  614 */       ComparativaSaldosDlg dlg = new ComparativaSaldosDlg(this, true);
/*  615 */       mostrarDialogo(dlg);
/*  616 */       if (dlg.isHayDatos()) {
/*  617 */         MarcoParaGraficos marco = new MarcoParaGraficos(dlg.getCuenta() + " : " + dlg.getNombreCuenta(), dlg.getNYear(), dlg.getYear(), dlg.getDatosSaldos());
/*      */ 
/*  621 */         mostrarMarcoExterno(marco);
/*      */       }
/*  623 */     } else if ("niveles".equals(cmd)) {
/*  624 */       ListadoNiveles dlgListado = new ListadoNiveles(this, Mensajes.getString("niveles"), true);
/*  625 */       mostrarDialogo(dlgListado);
/*  626 */       if (dlgListado.Listar()) {
/*  627 */         List listado = dlgListado.textoListado();
/*  628 */         Listado listaRecibidas = new Listado(Mensajes.getString("niveles"), listado);
/*  629 */         mostrarMarcoExterno(listaRecibidas);
/*      */       }
/*  631 */     } else if ("pyg".equals(cmd))
/*      */     {
/*  633 */       CuentaPyG dlgListado = new CuentaPyG(this, true);
/*  634 */       mostrarDialogo(dlgListado);
/*  635 */       if (dlgListado.Listar()) {
/*  636 */         List listado = dlgListado.textoListado();
/*  637 */         Listado listaEmitidas = new Listado(Mensajes.getString("ctaPG"), listado);
/*  638 */         mostrarMarcoExterno(listaEmitidas);
/*      */       }
/*  640 */     } else if ("balance".equals(cmd))
/*      */     {
/*  642 */       Balance dlgListado = new Balance(this, true);
/*  643 */       mostrarDialogo(dlgListado);
/*  644 */       if (dlgListado.Listar()) {
/*  645 */         List listado = dlgListado.textoListado();
/*  646 */         Listado listaEmitidas = new Listado(Mensajes.getString("balance"), listado);
/*  647 */         mostrarMarcoExterno(listaEmitidas);
/*      */       }
/*  649 */     } else if ("pyg07".equals(cmd)) {
/*  650 */       CuentaPyG08 dlgListado = new CuentaPyG08(this, true);
/*  651 */       mostrarDialogo(dlgListado);
/*  652 */       if (dlgListado.Listar()) {
/*  653 */         List listado = dlgListado.textoListado();
/*  654 */         Listado listaEmitidas = new Listado(Mensajes.getString("ctaPG07"), listado);
/*  655 */         mostrarMarcoExterno(listaEmitidas);
/*      */       }
/*  657 */     } else if ("balance07".equals(cmd)) {
/*  658 */       Balance08 dlgListado = new Balance08(this, true);
/*  659 */       mostrarDialogo(dlgListado);
/*  660 */       if (dlgListado.Listar()) {
/*  661 */         List listado = dlgListado.textoListado();
/*  662 */         Listado listaEmitidas = new Listado(Mensajes.getString("balance07"), listado);
/*  663 */         mostrarMarcoExterno(listaEmitidas);
/*      */       }
/*  665 */     } else if ("ecpn".equals(cmd)) {
/*  666 */       ECPN clase = new ECPN();
/*  667 */       clase.ejecutar();
/*  668 */     } else if ("stocks".equals(cmd)) {
/*  669 */       RegularizacionExistencias dlg = new RegularizacionExistencias(this, true);
/*  670 */       mostrarDialogo(dlg);
/*  671 */     } else if ("asientoPyG".equals(cmd)) {
/*  672 */       PerdidasGanancias dlg = new PerdidasGanancias(this, true);
/*  673 */       mostrarDialogo(dlg);
/*  674 */     } else if ("apertura".equals(cmd)) {
/*  675 */       AperturaEjercicio dlg = new AperturaEjercicio(this, true);
/*  676 */       mostrarDialogo(dlg);
/*  677 */     } else if ("cierre".equals(cmd)) {
/*  678 */       CierreEjercicio dlg = new CierreEjercicio(this, true);
/*  679 */       mostrarDialogo(dlg);
/*  680 */     } else if ("cerrartodo".equals(cmd)) {
/*  681 */       cerrarMarcos();
/*  682 */     } else if ("ayuda".equals(cmd)) {
/*  683 */       abrirManual();
/*      */     }
/*  686 */     else if ("calculadora".equals(cmd)) {
/*  687 */       Inicio.calculadora.setVisible(true);
/*  688 */     } else if ("info".equals(cmd)) {
/*  689 */       AboutBox dlg = new AboutBox(this, true);
/*  690 */       mostrarDialogo(dlg);
/*  691 */     } else if ("web".equals(cmd)) {
/*      */       try {
/*  693 */         URI url = new URI("http://www.contaes.es");
/*  694 */         Desktop.getDesktop().browse(url);
/*      */       } catch (URISyntaxException ex) {
/*  696 */         Logger.getLogger(MarcoInicio.class.getName()).log(Level.SEVERE, null, ex);
/*      */       }
/*      */       catch (IOException ex) {
/*  699 */         ex.printStackTrace();
/*      */       }
/*  701 */     } else if ("updates".equals(cmd)) {
/*      */       try {
/*  703 */         URI url = new URI("http://www.contaes.es/instalar.html");
/*  704 */         Desktop.getDesktop().browse(url);
/*      */       } catch (URISyntaxException ex) {
/*  706 */         Logger.getLogger(MarcoInicio.class.getName()).log(Level.SEVERE, null, ex);
/*      */       }
/*      */       catch (IOException ex) {
/*  709 */         ex.printStackTrace();
/*      */       }
/*  711 */     } else if ("isupdate".equals(cmd)) {
/*  712 */       boolean isUpdate = new CheckVersionInWeb().isIsUpdate();
/*  713 */       if (isUpdate) {
/*  714 */         JOptionPane.showMessageDialog(this, "Su versión de Contaes4 está actualizada.", "Actualizaciones", 1);
/*      */       }
/*      */       else
/*      */       {
/*  720 */         int confirma = JOptionPane.showConfirmDialog(this, "Hay una versión más reciente de Contaes4.\n¿Desea abrir la página Web para descargarla?", "Actualizaciones", 0);
/*      */ 
/*  724 */         if (confirma == 0) {
/*      */           try {
/*  726 */             URI url = new URI("http://www.contaes.es");
/*  727 */             Desktop.getDesktop().browse(url);
/*      */           } catch (URISyntaxException ex) {
/*  729 */             Logger.getLogger(MarcoInicio.class.getName()).log(Level.SEVERE, null, ex);
/*      */           }
/*      */           catch (IOException ex) {
/*  732 */             ex.printStackTrace();
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*  737 */     else if ("tiposIVA".equals(cmd)) {
/*  738 */       TiposIva dlg = new TiposIva(this, true);
/*  739 */       mostrarDialogo(dlg);
/*  740 */     } else if ("traspasarAsientos".equals(cmd)) {
/*  741 */       TraspasarAsientos dlg = new TraspasarAsientos(this, true);
/*  742 */       mostrarDialogo(dlg);
/*  743 */     } else if ("formasPago".equals(cmd)) {
/*  744 */       FormasPago dlg = new FormasPago(this, true);
/*  745 */       mostrarDialogo(dlg);
/*  746 */     } else if ("ImportarFacturas".equals(cmd)) {
/*  747 */       new ImportarFacturas();
/*  748 */     } else if ("productos".equals(cmd)) {
/*  749 */       if ((this.marco_almacen == null) || (this.marco_almacen.isClosed())) {
/*  750 */         this.marco_almacen = new AlmacenJInternalFrame(Mensajes.getString("almacen"));
/*  751 */         this.escritorio.add(this.marco_almacen);
/*  752 */         annadirMarcoMenu(this.marco_almacen);
/*      */       }
/*  754 */       mostrarMarco(this.marco_almacen);
/*  755 */     } else if ("gestionfacturas".equals(cmd)) {
/*  756 */       if ((this.marco_listado_Facturacion == null) || (this.marco_listado_Facturacion.isClosed())) {
/*  757 */         this.marco_listado_Facturacion = new TablaFacturas();
/*  758 */         this.escritorio.add(this.marco_listado_Facturacion);
/*  759 */         annadirMarcoMenu(this.marco_listado_Facturacion);
/*  760 */         if ((this.marco_facturacion == null) || (this.marco_facturacion.isClosed())) {
/*  761 */           this.marco_facturacion = new FacturasJInternalFrame();
/*  762 */           this.escritorio.add(this.marco_facturacion);
/*  763 */           annadirMarcoMenu(this.marco_facturacion);
/*      */         }
/*      */       }
/*  766 */       mostrarMarco(this.marco_listado_Facturacion);
/*      */     }
/*  768 */     else if ("introduccionfacturas".equals(cmd)) {
/*  769 */       if ((this.marco_facturacion == null) || (this.marco_facturacion.isClosed())) {
/*  770 */         this.marco_facturacion = new FacturasJInternalFrame();
/*  771 */         this.escritorio.add(this.marco_facturacion);
/*  772 */         annadirMarcoMenu(this.marco_facturacion);
/*      */       }
/*  774 */       mostrarMarco(this.marco_facturacion);
/*  775 */     } else if ("gestionfacturasrecibidas".equals(cmd)) {
/*  776 */       if ((this.marco_listado_Facturacion_p == null) || (this.marco_listado_Facturacion_p.isClosed())) {
/*  777 */         this.marco_listado_Facturacion_p = new TablaFacturasCompras();
/*  778 */         this.escritorio.add(this.marco_listado_Facturacion_p);
/*  779 */         annadirMarcoMenu(this.marco_listado_Facturacion_p);
/*  780 */         if ((this.marco_facturacion_p == null) || (this.marco_facturacion_p.isClosed())) {
/*  781 */           this.marco_facturacion_p = new FacturasProveedoresJInternalFrame();
/*  782 */           this.escritorio.add(this.marco_facturacion_p);
/*  783 */           annadirMarcoMenu(this.marco_facturacion_p);
/*      */         }
/*      */       }
/*  786 */       mostrarMarco(this.marco_listado_Facturacion_p);
/*      */     }
/*  788 */     else if ("introduccionfacturasrecibidas".equals(cmd)) {
/*  789 */       if ((this.marco_facturacion_p == null) || (this.marco_facturacion_p.isClosed())) {
/*  790 */         this.marco_facturacion_p = new FacturasProveedoresJInternalFrame();
/*  791 */         this.escritorio.add(this.marco_facturacion_p);
/*  792 */         annadirMarcoMenu(this.marco_facturacion_p);
/*      */       }
/*  794 */       mostrarMarco(this.marco_facturacion_p);
/*  795 */     } else if ("pedidosproveedores".equals(cmd)) {
/*  796 */       if ((this.marco_pedidos == null) || (this.marco_pedidos.isClosed())) {
/*  797 */         this.marco_pedidos = new PedidosJInternalFrame(Mensajes.getString("pedidosProveedores"));
/*  798 */         this.escritorio.add(this.marco_pedidos);
/*  799 */         annadirMarcoMenu(this.marco_pedidos);
/*      */       }
/*  801 */       mostrarMarco(this.marco_pedidos);
/*  802 */     } else if ("pedidosclientes".equals(cmd)) {
/*  803 */       if ((this.marco_pedidos_clientes == null) || (this.marco_pedidos_clientes.isClosed())) {
/*  804 */         this.marco_pedidos_clientes = new PedidosClientesJInternalFrame(Mensajes.getString("pedidosClientes"));
/*  805 */         this.escritorio.add(this.marco_pedidos_clientes);
/*  806 */         annadirMarcoMenu(this.marco_pedidos_clientes);
/*      */       }
/*  808 */       mostrarMarco(this.marco_pedidos_clientes);
/*  809 */     } else if ("listadosalmacen".equals(cmd)) {
/*  810 */       if ((this.marco_listados_almacen == null) || (this.marco_listados_almacen.isClosed())) {
/*  811 */         this.marco_listados_almacen = new ListadosJInternalFrame(Mensajes.getString("listados"));
/*  812 */         this.escritorio.add(this.marco_listados_almacen);
/*  813 */         annadirMarcoMenu(this.marco_listados_almacen);
/*      */       }
/*  815 */       mostrarMarco(this.marco_listados_almacen);
/*  816 */     } else if ("resumenclientes".equals(cmd)) {
/*  817 */       ResumenFacturasJDialog dlg = new ResumenFacturasJDialog(this, true, true);
/*  818 */       mostrarDialogo(dlg);
/*  819 */     } else if ("resumenproveedores".equals(cmd)) {
/*  820 */       ResumenFacturasJDialog dlg = new ResumenFacturasJDialog(this, true, false);
/*  821 */       mostrarDialogo(dlg);
/*  822 */     } else if ("distribucionpartidas".equals(cmd)) {
/*  823 */       DistribucionPartidasJDialog dlg = new DistribucionPartidasJDialog(this, true);
/*  824 */       mostrarDialogo(dlg);
/*  825 */     } else if ("origenesDestinos".equals(cmd)) {
/*  826 */       ContrapartidasJDialog dlg = new ContrapartidasJDialog(this, true);
/*  827 */       mostrarDialogo(dlg);
/*  828 */     } else if ("ratios".equals(cmd)) {
/*  829 */       RatiosJDialog dlg = new RatiosJDialog(this, true);
/*  830 */       mostrarDialogo(dlg);
/*  831 */     } else if ("almacenes".equals(cmd)) {
/*  832 */       AlmacenesJDialog dlg = new AlmacenesJDialog(this, true);
/*  833 */       mostrarDialogo(dlg);
/*  834 */     } else if ("mediospago".equals(cmd)) {
/*  835 */       MediosPagoJDialog dlg = new MediosPagoJDialog(this, true);
/*  836 */       mostrarDialogo(dlg);
/*  837 */     } else if ("vendedores".equals(cmd)) {
/*  838 */       VendedoresJDialog dlg = new VendedoresJDialog(this, true);
/*  839 */       mostrarDialogo(dlg);
/*  840 */     } else if ("terminalventas".equals(cmd)) {
/*  841 */       if ((this.marco_terminalVentas == null) || (this.marco_terminalVentas.isClosed())) {
/*  842 */         this.marco_terminalVentas = new TerminalVentas();
/*  843 */         this.escritorio.add(this.marco_terminalVentas);
/*  844 */         if (!Inicio.p.isModoPOS()) {
/*  845 */           annadirMarcoMenu(this.marco_terminalVentas);
/*      */         }
/*      */       }
/*  848 */       mostrarMarco(this.marco_terminalVentas);
/*  849 */     } else if ("resumendiario".equals(cmd)) {
/*  850 */       if ((this.marco_ResumenVentas == null) || (this.marco_ResumenVentas.isClosed())) {
/*  851 */         this.marco_ResumenVentas = new ResumenDiario();
/*  852 */         this.escritorio.add(this.marco_ResumenVentas);
/*  853 */         if (!Inicio.p.isModoPOS()) {
/*  854 */           annadirMarcoMenu(this.marco_ResumenVentas);
/*      */         }
/*      */       }
/*  857 */       mostrarMarco(this.marco_ResumenVentas);
/*  858 */     } else if ("ALMACENOLD1".equals(cmd)) {
/*  859 */       OldAlmacenJInternalFrame marcoAlmacenOld = new OldAlmacenJInternalFrame();
/*  860 */       this.escritorio.add(marcoAlmacenOld);
/*  861 */       marcoAlmacenOld.show();
/*  862 */       annadirMarcoMenu(marcoAlmacenOld);
/*  863 */     } else if ("ALMACENOLD2".equals(cmd)) {
/*  864 */       ListarProductosOldJInternalFrame marcoAlmacenOld2 = new ListarProductosOldJInternalFrame();
/*  865 */       this.escritorio.add(marcoAlmacenOld2);
/*  866 */       marcoAlmacenOld2.show();
/*  867 */       annadirMarcoMenu(marcoAlmacenOld2);
/*  868 */     } else if ("ALMACENOLD3".equals(cmd)) {
/*  869 */       BuscarRefOldJDialog dlg = new BuscarRefOldJDialog();
/*  870 */       mostrarDialogo(dlg);
/*  871 */     } else if ("paises".equals(cmd)) {
/*  872 */       Paises dlg = new Paises(this, true);
/*  873 */       mostrarDialogo(dlg);
/*  874 */     } else if ("CSB19".equals(cmd)) {
/*  875 */       generarCSB(19);
/*  876 */     } else if ("CSB58".equals(cmd)) {
/*  877 */       generarCSB(58);
/*      */     }
/*  879 */     else if ("makebackup".equals(cmd)) {
/*  880 */       DataBasesBackup backup = new DataBasesBackup();
/*  881 */       backup.make();
/*      */     }
/*  883 */     else if ("restorebackup".equals(cmd)) {
/*  884 */       DataBasesBackup backup = new DataBasesBackup();
/*  885 */       backup.restore();
/*      */     }
/*  887 */     else if ("backprogrampath".equals(cmd)) {
/*  888 */       BackupProgramPathJDialog dlg = new BackupProgramPathJDialog(this, true);
/*  889 */       mostrarDialogo(dlg);
/*      */     }
/*  891 */     else if ("tpvtofact".equals(cmd)) {
/*  892 */       TicketsToFacturacionJDialog dlg = new TicketsToFacturacionJDialog(this, true);
/*  893 */       mostrarDialogo(dlg);
/*  894 */     } else if ("renumerarfacturacion".equals(cmd)) {
/*  895 */       Reordenar dlg = new Reordenar(this, true, 0);
/*  896 */       mostrarDialogo(dlg);
/*  897 */     } else if ("renumerartickets".equals(cmd)) {
/*  898 */       Reordenar dlg = new Reordenar(this, true, 1);
/*  899 */       mostrarDialogo(dlg);
/*  900 */     } else if ("traspasarfacturasventas".equals(cmd)) {
/*  901 */       TraspasarFacturas dlg = new TraspasarFacturas(this, true, true);
/*  902 */       mostrarDialogo(dlg);
/*  903 */     } else if ("traspasarfacturascompras".equals(cmd)) {
/*  904 */       TraspasarFacturas dlg = new TraspasarFacturas(this, true, false);
/*  905 */       mostrarDialogo(dlg);
/*  906 */     } else if ("importarsubcuentas".equals(cmd)) {
/*  907 */       ImportarSubcuentas clase = new ImportarSubcuentas();
/*  908 */       clase.importar();
/*  909 */     } else if ("importardiaro".equals(cmd)) {
/*  910 */       ImportarDiario clase = new ImportarDiario();
/*  911 */       clase.importar();
/*  912 */     } else if ("controlcentre".equals(cmd)) {
/*  913 */       crearCentroControl();
/*  914 */       mostrarMarco(this.centroControl);
/*  915 */     } else if ("registro".equals(cmd)) {
/*  916 */       getSerialNumber();
/*      */     }
/*      */   }
/*      */ 
/*      */   public Rectangle localizacion()
/*      */   {
/*  922 */     return getBounds();
/*      */   }
/*      */ 
/*      */   private boolean hayEmpresas()
/*      */   {
/*  929 */     int numEmpresas = new ManejoEmpresas(Inicio.getCGeneral(), Inicio.p.getEmpresa()).listaEmpresas().size();
/*  930 */     if (numEmpresas > 0) {
/*  931 */       return true;
/*      */     }
/*  933 */     return false;
/*      */   }
/*      */ 
/*      */   private void seleccionarEmpresa()
/*      */   {
/*  940 */     SeleccionEmpresa dlg = new SeleccionEmpresa(this, Mensajes.getString("selEmp"), true, false);
/*  941 */     mostrarDialogo(dlg);
/*  942 */     if (dlg.haySeleccion()) {
/*  943 */       setTitle(this.titulo + dlg.getNombre() + " - " + Inicio.p.getEjercicio());
/*  944 */       if (this.firstStart)
/*      */       {
/*  946 */         this.firstStart = false;
/*      */       }
/*      */       else
/*      */       {
/*  950 */         anularMarco(this.marco_asiento);
/*      */ 
/*  952 */         anularMarco(this.marco_CreacionFacturas);
/*  953 */         anularMarco(this.marco_facturasEmitidas);
/*  954 */         anularMarco(this.marco_facturasRecibidas);
/*  955 */         anularMarco(this.marco_cuentas);
/*  956 */         anularMarco(this.marco_tDiario);
/*  957 */         anularMarco(this.marco_tVencimientos);
/*  958 */         anularMarco(this.marco_tVencimientosC);
/*  959 */         anularMarco(this.marco_facturacion);
/*  960 */         anularMarco(this.marco_listado_Facturacion);
/*  961 */         anularMarco(this.marco_facturacion_p);
/*  962 */         anularMarco(this.marco_listado_Facturacion_p);
/*  963 */         anularMarco(this.marco_almacen);
/*  964 */         anularMarco(this.marco_pedidos);
/*  965 */         anularMarco(this.marco_pedidos_clientes);
/*  966 */         anularMarco(this.marco_listados_almacen);
/*  967 */         anularMarco(this.marco_terminalVentas);
/*  968 */         anularMarco(this.marco_ResumenVentas);
/*  969 */         anularMarco(this.centroControl);
/*      */       }
/*  971 */       colocarInfo(dlg.getNombre());
/*      */     }
/*  973 */     getContentPane().repaint();
/*  974 */     System.gc();
/*      */   }
/*      */ 
/*      */   private void generarCSB(int modelo) {
/*  978 */     if (this.marco_tVencimientosC != null) {
/*  979 */       CrearFicheroCSB dlg = new CrearFicheroCSB(this, true, this.marco_tVencimientosC.getSeleccionados(), modelo);
/*  980 */       mostrarDialogo(dlg);
/*      */     }
/*      */     else {
/*  983 */       JOptionPane.showMessageDialog(this, "La ventana de vencimientos a cobrar\nno está abierta", "Atención", 0);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void getSerialNumber() {
/*  988 */     String s = JOptionPane.showInputDialog(this, "Introduzca su número de serie", "Registro", 3);
/*  989 */     if (s.equals("VBJ05E151R3010M276")) {
/*  990 */       ConfiguracionBean c = new ConfiguracionBean();
/*  991 */       c.saveConfigStr("<mouse>", "1");
/*  992 */       Inicio.p.setRegistro(true);
/*  993 */       JOptionPane.showMessageDialog(this, "¡Gracias por registrar Contaes4!", "Registro", 1);
/*      */     }
/*      */     else {
/*  996 */       JOptionPane.showMessageDialog(this, "¡El número introducido no es correcto!", "Registro", 0);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void editarTicketVenta(int idTicket) {
/* 1001 */     if ((this.marco_terminalVentas == null) || (this.marco_terminalVentas.isClosed())) {
/* 1002 */       this.marco_terminalVentas = new TerminalVentas();
/* 1003 */       this.escritorio.add(this.marco_terminalVentas);
/* 1004 */       annadirMarcoMenu(this.marco_terminalVentas);
/*      */     }
/* 1006 */     mostrarMarco(this.marco_terminalVentas);
/* 1007 */     this.marco_terminalVentas.cargarTicket(idTicket);
/*      */   }
/*      */ 
/*      */   public void activarAgendaPanelCuentas(String texto, boolean agenda) {
/* 1011 */     if (this.marco_cuentas != null)
/* 1012 */       this.marco_cuentas.activarAgenda(texto, agenda);
/*      */   }
/*      */ 
/*      */   public void renovarTabla(int tabla)
/*      */   {
/* 1026 */     switch (tabla) {
/*      */     case 0:
/* 1028 */       if (this.marco_tDiario != null)
/* 1029 */         this.marco_tDiario.renovarTabla(); break;
/*      */     case 1:
/* 1033 */       if (this.marco_tVencimientos != null)
/* 1034 */         this.marco_tVencimientos.renovarTabla(); break;
/*      */     case 2:
/* 1038 */       if (this.marco_tVencimientosC != null)
/* 1039 */         this.marco_tVencimientosC.renovarTabla(); break;
/*      */     case 3:
/* 1043 */       if (this.marco_cuentas != null)
/* 1044 */         this.marco_cuentas.renovarTabla(); break;
/*      */     case 4:
/* 1048 */       if (this.marco_Asientos_Modelo != null)
/* 1049 */         this.marco_Asientos_Modelo.renovarTabla(); break;
/*      */     case 5:
/* 1053 */       if (this.marco_facturasEmitidas != null)
/* 1054 */         this.marco_facturasEmitidas.renovarTabla(); break;
/*      */     case 6:
/* 1058 */       if (this.marco_facturasRecibidas != null)
/* 1059 */         this.marco_facturasRecibidas.renovarTabla(); break;
/*      */     case 7:
/* 1063 */       if (this.marco_listado_Facturacion != null)
/* 1064 */         this.marco_listado_Facturacion.renovarTabla(); break;
/*      */     case 8:
/* 1068 */       if (this.marco_listado_Facturacion_p != null)
/* 1069 */         this.marco_listado_Facturacion_p.renovarTabla();
/*      */       break;
/*      */     }
/*      */   }
/*      */ 
/*      */   public void modificarElemento(int marco)
/*      */   {
/* 1080 */     JInternalFrame f = null;
/* 1081 */     switch (marco) {
/*      */     case 0:
/* 1083 */       crearMarcoAsientos();
/* 1084 */       f = this.marco_asiento;
/* 1085 */       break;
/*      */     case 1:
/* 1087 */       crearMarcoNuevaFactura();
/* 1088 */       f = this.marco_CreacionFacturas;
/* 1089 */       break;
/*      */     case 2:
/* 1091 */       crearMarcoFacturacionClientes();
/* 1092 */       f = this.marco_facturacion;
/* 1093 */       break;
/*      */     case 3:
/* 1095 */       crearMarcoFacturacionProveedores();
/* 1096 */       f = this.marco_facturacion_p;
/*      */     }
/*      */ 
/* 1099 */     if (f != null) {
/* 1100 */       f.setVisible(true);
/*      */       try {
/* 1102 */         if (f.isIcon()) {
/* 1103 */           f.setIcon(false);
/*      */         }
/* 1105 */         if (!f.isSelected())
/* 1106 */           f.setSelected(true);
/*      */       }
/*      */       catch (PropertyVetoException exc) {
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public void eliminarPIO(String referencia, int id) {
/* 1114 */     if (this.marco_almacen != null)
/* 1115 */       this.marco_almacen.borrarPIO(referencia, id);
/*      */   }
/*      */ 
/*      */   public void modificarPIO(String referencia, PIOObject objeto)
/*      */   {
/* 1120 */     if (this.marco_almacen != null)
/* 1121 */       this.marco_almacen.modificarPIO(referencia, objeto);
/*      */   }
/*      */ 
/*      */   public void updateStockAlmacen()
/*      */   {
/* 1126 */     if (this.marco_almacen != null)
/* 1127 */       this.marco_almacen.updateStock();
/*      */   }
/*      */ 
/*      */   public void renovarModeloIVA()
/*      */   {
/* 1134 */     if (this.marco_CreacionFacturas != null) {
/* 1135 */       this.marco_CreacionFacturas.renovarModeloIva();
/*      */     }
/* 1137 */     if (this.marco_facturacion != null) {
/* 1138 */       this.marco_facturacion.renovarModeloIva();
/*      */     }
/* 1140 */     if (this.marco_facturacion_p != null) {
/* 1141 */       this.marco_facturacion_p.renovarModeloIva();
/*      */     }
/* 1143 */     if (this.marco_almacen != null)
/* 1144 */       this.marco_almacen.renovarModeloIva();
/*      */   }
/*      */ 
/*      */   public void renovarModeloFormasPago()
/*      */   {
/* 1149 */     if (this.marco_cuentas != null) {
/* 1150 */       this.marco_cuentas.renovarModeloFormasPago();
/*      */     }
/* 1152 */     if (this.marco_facturacion != null) {
/* 1153 */       this.marco_facturacion.renovarModeloFormasPago();
/*      */     }
/* 1155 */     if (this.marco_facturacion_p != null)
/* 1156 */       this.marco_facturacion_p.renovarModeloFormasPago();
/*      */   }
/*      */ 
/*      */   public void renovarModeloPaises()
/*      */   {
/* 1161 */     if (this.marco_cuentas != null)
/* 1162 */       this.marco_cuentas.renovarModeloPaises();
/*      */   }
/*      */ 
/*      */   public void renovarModeloTesoreria()
/*      */   {
/* 1167 */     if (this.marco_cuentas != null)
/* 1168 */       this.marco_cuentas.renovarModeloCuentas();
/*      */   }
/*      */ 
/*      */   public void renovarModeloProveedoresFacturacion()
/*      */   {
/* 1173 */     if (this.marco_facturacion_p != null)
/* 1174 */       this.marco_facturacion_p.renovarModeloSubcuentas();
/*      */   }
/*      */ 
/*      */   public void renovarModeloClientesFacturacion()
/*      */   {
/* 1179 */     if (this.marco_facturacion != null)
/* 1180 */       this.marco_facturacion.renovarModeloSubcuentas();
/*      */   }
/*      */ 
/*      */   public void renovarModeloProveedoresPedidos()
/*      */   {
/* 1185 */     if (this.marco_pedidos != null) {
/* 1186 */       this.marco_pedidos.renovarModeloProveedor();
/*      */     }
/* 1188 */     if (this.marco_pedidos_clientes != null)
/* 1189 */       this.marco_pedidos_clientes.renovarModeloProveedor();
/*      */   }
/*      */ 
/*      */   public void renovarModeloClientesPedidos()
/*      */   {
/* 1194 */     if (this.marco_pedidos_clientes != null)
/* 1195 */       this.marco_pedidos_clientes.renovarModeloCliente();
/*      */   }
/*      */ 
/*      */   private void crearMenu(JMenuBar menu)
/*      */   {
/* 1200 */     JMenu empresas = new JMenu(Mensajes.getString("menu1"));
/* 1201 */     menu.add(empresas);
/* 1202 */     JMenu diario = new JMenu(Mensajes.getString("diario"));
/* 1203 */     menu.add(diario);
/* 1204 */     JMenu cuentas = new JMenu(Mensajes.getString("menu4"));
/* 1205 */     menu.add(cuentas);
/* 1206 */     JMenu facturas = new JMenu(Mensajes.getString("menu3"));
/* 1207 */     menu.add(facturas);
/* 1208 */     JMenu balances = new JMenu(Mensajes.getString("menu5"));
/* 1209 */     menu.add(balances);
/* 1210 */     JMenu facturacion = new JMenu("Facturación");
/* 1211 */     menu.add(facturacion);
/* 1212 */     JMenu almacen = new JMenu(Mensajes.getString("almacen"));
/* 1213 */     menu.add(almacen);
/* 1214 */     JMenu ventanas = new JMenu(Mensajes.getString("ventana"));
/* 1215 */     menu.add(ventanas);
/* 1216 */     JMenu ayuda = new JMenu(Mensajes.getString("ayuda"));
/* 1217 */     menu.add(ayuda);
/*      */ 
/* 1219 */     JMenuItem item = null;
/* 1220 */     item = makeMenuItem("MseleccEj", "selempresa", Mensajes.getString("menu11tt"), Mensajes.getString("selEmp"));
/* 1221 */     empresas.add(item);
/* 1222 */     item = makeMenuItem("MgestionEmp", "gesempresas", Mensajes.getString("menu12tt"), Mensajes.getString("gesEmp"));
/* 1223 */     empresas.add(item);
/* 1224 */     empresas.addSeparator();
/* 1225 */     item = makeMenuItem("McrearEj", "abrirejercicio", Mensajes.getString("menu13tt"), Mensajes.getString("creaEj"));
/* 1226 */     empresas.add(item);
/* 1227 */     item = makeMenuItem("MsuprimirEj", "borrarejercicio", Mensajes.getString("menu14tt"), Mensajes.getString("suprEj"));
/* 1228 */     empresas.add(item);
/* 1229 */     empresas.addSeparator();
/* 1230 */     item = makeMenuItem("MgestionUsu", "gestionusuarios", Mensajes.getString("menu15tt"), Mensajes.getString("usuarios"));
/* 1231 */     empresas.add(item);
/* 1232 */     empresas.addSeparator();
/* 1233 */     item = makeMenuItem("makebackup", "makebackup", "Realiza una copia de seguridad de las bases de datos", "Respaldar bases de datos");
/* 1234 */     empresas.add(item);
/* 1235 */     item = makeMenuItem("restorebackup", "restorebackup", "Restaura una copia de seguridad de las bases de datos", "Restaurar bases de datos");
/* 1236 */     empresas.add(item);
/* 1237 */     item = makeMenuItem("backuppath", "backprogrampath", "Guarda la ruta de acceso a la instalación de MySQL", "Ruta de MySQL");
/* 1238 */     empresas.add(item);
/* 1239 */     empresas.addSeparator();
/* 1240 */     item = makeMenuItem("danger", "desinstalar", "Borra todas las bases de datos del programa", "Desinstalar");
/* 1241 */     empresas.add(item);
/* 1242 */     empresas.addSeparator();
/* 1243 */     item = makeMenuItem("exit", "salir", Mensajes.getString("menu16tt"), Mensajes.getString("salir"));
/* 1244 */     empresas.add(item);
/*      */ 
/* 1246 */     item = makeMenuItem("Masientos", "asientos", Mensajes.getString("menu21tt"), Mensajes.getString("menu21"));
/* 1247 */     diario.add(item);
/* 1248 */     item = makeMenuItem("MasientosModelo", "asientosaut", Mensajes.getString("menu22tt"), Mensajes.getString("astModel"));
/* 1249 */     diario.add(item);
/* 1250 */     diario.addSeparator();
/* 1251 */     item = makeMenuItem("Mdiario", "consultasdiario", Mensajes.getString("listaDiario"), Mensajes.getString("menu23"));
/* 1252 */     diario.add(item);
/* 1253 */     item = makeMenuItem("Traspaso", "traspasarAsientos", "Copia asientos entre empresas", "Copiar asientos");
/* 1254 */     diario.add(item);
/* 1255 */     diario.addSeparator();
/* 1256 */     item = makeMenuItem("Mconceptos", "conceptos", Mensajes.getString("menu24tt"), Mensajes.getString("menu24"));
/* 1257 */     diario.add(item);
/* 1258 */     diario.addSeparator();
/* 1259 */     item = makeMenuItem("Mdescuadres", "descuadres", Mensajes.getString("menu25tt"), Mensajes.getString("descuadres"));
/* 1260 */     diario.add(item);
/* 1261 */     item = makeMenuItem("MrenumD", "renumerar", Mensajes.getString("menu26tt"), Mensajes.getString("renumerarD"));
/* 1262 */     diario.add(item);
/* 1263 */     diario.addSeparator();
/* 1264 */     item = makeMenuItem("diarioImportar", "importardiaro", "Importar diario desde archivo csv", "Importar diario");
/* 1265 */     diario.add(item);
/*      */ 
/* 1267 */     item = makeMenuItem("Mfacturas", "facturas", Mensajes.getString("menu31tt"), Mensajes.getString("menu31"));
/* 1268 */     facturas.add(item);
/* 1269 */     JMenu Gestion = new JMenu(Mensajes.getString("menu31b"));
/* 1270 */     Gestion.setIcon(new ImageIcon(MarcoInicio.class.getResource("/contaes/iconos/gestionFacturas.png")));
/* 1271 */     facturas.add(Gestion);
/* 1272 */     item = makeMenuItem("gestionEmitidas", "gestionEmitidas", Mensajes.getString("menu31b1tt"), Mensajes.getString("menu31b1"));
/* 1273 */     Gestion.add(item);
/* 1274 */     item = makeMenuItem("gestionRecibidas", "gestionRecibidas", Mensajes.getString("menu31b2tt"), Mensajes.getString("menu31b2"));
/* 1275 */     Gestion.add(item);
/* 1276 */     JMenu listados = new JMenu(Mensajes.getString("menu32"));
/* 1277 */     listados.setIcon(new ImageIcon(MarcoInicio.class.getResource("/contaes/iconos/MlistFacturas.png")));
/* 1278 */     facturas.add(listados);
/* 1279 */     item = makeMenuItem("MlFactEmit", "emitidas", Mensajes.getString("lisEmit"), Mensajes.getString("menu33"));
/* 1280 */     listados.add(item);
/* 1281 */     item = makeMenuItem("MlFactReci", "recibidas", Mensajes.getString("listReci"), Mensajes.getString("menu34"));
/* 1282 */     listados.add(item);
/* 1283 */     facturas.addSeparator();
/* 1284 */     JMenu vencimientos = new JMenu(Mensajes.getString("vencimientos"));
/* 1285 */     vencimientos.setIcon(new ImageIcon(MarcoInicio.class.getResource("/contaes/iconos/MV.png")));
/* 1286 */     item = makeMenuItem("MV", "formasPago", "Gestión de formas de pago", "Formas de pago");
/* 1287 */     vencimientos.add(item);
/* 1288 */     vencimientos.addSeparator();
/* 1289 */     item = makeMenuItem("MVc", "vencimientoscobrar", Mensajes.getString("menu35att"), Mensajes.getString("menu35a"));
/* 1290 */     vencimientos.add(item);
/* 1291 */     item = makeMenuItem("McomprobarVc", "comprobarVencimientosCobrar", Mensajes.getString("menu36tt"), Mensajes.getString("menu36a"));
/* 1292 */     vencimientos.add(item);
/* 1293 */     item = makeMenuItem("MEliminarVc", "eliminarVencimientosCobrar", "", Mensajes.getString("elimVto"));
/* 1294 */     vencimientos.add(item);
/* 1295 */     vencimientos.addSeparator();
/* 1296 */     item = makeMenuItem("MVp", "vencimientos", Mensajes.getString("menu35tt"), Mensajes.getString("menu35"));
/* 1297 */     vencimientos.add(item);
/* 1298 */     item = makeMenuItem("McomprobarVp", "comprobarVencimientos", Mensajes.getString("menu36tt"), Mensajes.getString("menu36"));
/* 1299 */     vencimientos.add(item);
/* 1300 */     item = makeMenuItem("MEliminarVp", "eliminarVencimientos", "", Mensajes.getString("elimVto"));
/* 1301 */     vencimientos.add(item);
/* 1302 */     facturas.add(vencimientos);
/* 1303 */     facturas.addSeparator();
/* 1304 */     item = makeMenuItem("MIVAfiscal", "tiposIVA", "Gestión de tipos de IVA", "Tipos de IVA");
/* 1305 */     facturas.add(item);
/* 1306 */     item = makeMenuItem("MIVAcontable", "liquidacioniva", Mensajes.getString("menu37tt"), Mensajes.getString("liquidIVA"));
/* 1307 */     facturas.add(item);
/* 1308 */     item = makeMenuItem("MIVAfiscal", "liquidacionfiscaliva", Mensajes.getString("menuIVAtt"), Mensajes.getString("liquidFiscalIVA"));
/* 1309 */     facturas.add(item);
/* 1310 */     item = makeMenuItem("MOpTerceros", "operacionesTerceros", "", Mensajes.getString("OperaTerceros"));
/* 1311 */     facturas.add(item);
/*      */ 
/* 1313 */     item = makeMenuItem("facturacion18", "introduccionfacturas", "Creación de facturas (ventas)", "Crear facturas (ventas)");
/* 1314 */     facturacion.add(item);
/* 1315 */     item = makeMenuItem("facturaGes18", "gestionfacturas", "Gestión de facturas (ventas)", "Gestión (ventas)");
/* 1316 */     facturacion.add(item);
/* 1317 */     item = makeMenuItem("facturacion18", "introduccionfacturasrecibidas", "Creación de facturas (compras)", "Crear facturas (compras)");
/* 1318 */     facturacion.add(item);
/* 1319 */     item = makeMenuItem("facturaGes18", "gestionfacturasrecibidas", "Gestión de facturas (compras)", "Gestión (compras)");
/* 1320 */     facturacion.add(item);
/* 1321 */     facturacion.addSeparator();
/* 1322 */     item = makeMenuItem("tpvtofact", "tpvtofact", "Paso de tickets a facturación", "Tickets a facturación");
/* 1323 */     facturacion.add(item);
/* 1324 */     item = makeMenuItem("MrenumD", "renumerarfacturacion", "Renumera facturas de ventas entre dos fechas", "Renumerar facturas de ventas");
/* 1325 */     facturacion.add(item);
/* 1326 */     item = makeMenuItem("MrenumD", "renumerartickets", "Renumera tickets de ventas entre dos fechas", "Renumerar tickets de ventas");
/* 1327 */     facturacion.add(item);
/* 1328 */     facturacion.addSeparator();
/* 1329 */     item = makeMenuItem("Traspaso", "traspasarfacturasventas", "Copia facturas de ventas entre empresas", "Copiar facturas de ventas");
/* 1330 */     facturacion.add(item);
/* 1331 */     item = makeMenuItem("Traspaso", "traspasarfacturascompras", "Copia facturas de compras entre empresas", "Copiar facturas de compras");
/* 1332 */     facturacion.add(item);
/*      */ 
/* 1334 */     item = makeMenuItem("producto18", "productos", "Creación y gestión de productos", "Gestión");
/* 1335 */     almacen.add(item);
/* 1336 */     item = makeMenuItem("facturacion18", "pedidosproveedores", "Pedidos a proveedores", "Pedidos");
/* 1337 */     almacen.add(item);
/* 1338 */     item = makeMenuItem("facturaGes18", "listadosalmacen", "Informes de almacén", "Listados");
/* 1339 */     almacen.add(item);
/*      */ 
/* 1350 */     item = makeMenuItem("Mcuentas", "cuentas", Mensajes.getString("menu41tt"), Mensajes.getString("menu41"));
/* 1351 */     cuentas.add(item);
/* 1352 */     cuentas.addSeparator();
/* 1353 */     item = makeMenuItem("Mgraficos", "graficoscuentas", Mensajes.getString("comparativaGraficatt1"), Mensajes.getString("comparativaGrafica") + " " + Mensajes.getString("de") + " " + Mensajes.getString("cuentas"));
/*      */ 
/* 1355 */     cuentas.add(item);
/* 1356 */     item = makeMenuItem("Mgraficos", "graficossaldos", Mensajes.getString("comparativaGraficatt2"), Mensajes.getString("comparativaGrafica") + " " + Mensajes.getString("de") + " " + Mensajes.getString("saldos"));
/*      */ 
/* 1358 */     cuentas.add(item);
/* 1359 */     cuentas.addSeparator();
/* 1360 */     item = makeMenuItem("MsustituCtas", "sustitucion", Mensajes.getString("sustituCuentastt"), Mensajes.getString("sustituCuentas"));
/* 1361 */     cuentas.add(item);
/* 1362 */     item = makeMenuItem("MrecalcSaldos", "regenerarsaldos", Mensajes.getString("menu42tt"), Mensajes.getString("regen"));
/* 1363 */     cuentas.add(item);
/* 1364 */     cuentas.addSeparator();
/* 1365 */     item = makeMenuItem("cuentasImportar", "importarsubcuentas", "Importar subcuentas desde archivo csv", "Importar subcuentas");
/* 1366 */     cuentas.add(item);
/*      */ 
/* 1368 */     item = makeMenuItem("MlistSN", "niveles", Mensajes.getString("menu51tt"), Mensajes.getString("niveles"));
/* 1369 */     balances.add(item);
/* 1370 */     balances.addSeparator();
/*      */ 
/* 1374 */     item = makeMenuItem("MlistPG", "pyg07", Mensajes.getString("menu52tt07"), Mensajes.getString("ctaPG07"));
/* 1375 */     balances.add(item);
/*      */ 
/* 1378 */     item = makeMenuItem("MlistB", "balance07", Mensajes.getString("menu53tt07"), Mensajes.getString("balance07"));
/* 1379 */     balances.add(item);
/* 1380 */     item = makeMenuItem("MlistB", "ecpn", Mensajes.getString("ecpntt"), Mensajes.getString("ecpn"));
/* 1381 */     balances.add(item);
/* 1382 */     balances.addSeparator();
/* 1383 */     JMenu cierre = new JMenu(Mensajes.getString("menu54"));
/* 1384 */     cierre.setIcon(new ImageIcon(MarcoInicio.class.getResource("/contaes/iconos/Mcierre.png")));
/* 1385 */     balances.add(cierre);
/* 1386 */     item = makeMenuItem("MasientoRE", "stocks", Mensajes.getString("menu55tt"), Mensajes.getString("regulaEx"));
/* 1387 */     cierre.add(item);
/* 1388 */     item = makeMenuItem("MasientoPG", "asientoPyG", Mensajes.getString("menu56tt"), Mensajes.getString("menu56"));
/* 1389 */     cierre.add(item);
/* 1390 */     item = makeMenuItem("MasientoCierre", "cierre", Mensajes.getString("menu57tt"), Mensajes.getString("astoCierre"));
/* 1391 */     cierre.add(item);
/* 1392 */     item = makeMenuItem("MasientoApert", "apertura", Mensajes.getString("menu58tt"), Mensajes.getString("astoApertura"));
/* 1393 */     cierre.add(item);
/*      */ 
/* 1395 */     item = makeMenuItem("hide", "cerrartodo", Mensajes.getString("cerrarTodastt"), Mensajes.getString("cerrarTodas"));
/* 1396 */     ventanas.add(item);
/*      */ 
/* 1398 */     item = makeMenuItem("ok", "registro", "", "Registrar producto");
/* 1399 */     ayuda.add(item);
/* 1400 */     ayuda.addSeparator();
/* 1401 */     item = makeMenuItem("help", "ayuda", "", Mensajes.getString("ayuda"));
/* 1402 */     ayuda.add(item);
/* 1403 */     item = makeMenuItem("Mcalc", "calculadora", "", Mensajes.getString("calculadora"));
/* 1404 */     ayuda.add(item);
/*      */ 
/* 1408 */     ayuda.addSeparator();
/* 1409 */     item = makeMenuItem("web", "web", "", "Web de Contaes");
/* 1410 */     ayuda.add(item);
/* 1411 */     item = makeMenuItem("web", "updates", "", "Novedades");
/* 1412 */     ayuda.add(item);
/* 1413 */     item = makeMenuItem("checkupd", "isupdate", "", "Comprobar actualizaciones");
/* 1414 */     ayuda.add(item);
/* 1415 */     ayuda.addSeparator();
/* 1416 */     item = makeMenuItem("info", "info", "", Mensajes.getString("info"));
/* 1417 */     ayuda.add(item);
/*      */   }
/*      */ 
/*      */   private JMenuItem makeMenuItem(String imageName, String actionCommand, String toolTipText, String Text)
/*      */   {
/* 1425 */     String imgLocation = "/contaes/iconos/" + imageName + ".png";
/* 1426 */     URL imageURL = MarcoInicio.class.getResource(imgLocation);
/*      */ 
/* 1429 */     JMenuItem elementoMenu = new JMenuItem();
/* 1430 */     elementoMenu.setActionCommand(actionCommand);
/* 1431 */     elementoMenu.setToolTipText(toolTipText);
/* 1432 */     elementoMenu.addActionListener(this);
/* 1433 */     elementoMenu.setText(Text);
/*      */ 
/* 1435 */     if (imageURL != null) {
/* 1436 */       elementoMenu.setIcon(new ImageIcon(imageURL));
/*      */     }
/*      */ 
/* 1439 */     return elementoMenu;
/*      */   }
/*      */ 
/*      */   private void addBotonesPOS(JToolBar toolBar)
/*      */   {
/* 1537 */     toolBar.addSeparator();
/*      */ 
/* 1539 */     this.terminalPOS = makeNavigationButton("tpv32", "terminalventas", Mensajes.getString("terminalventas"), "");
/*      */ 
/* 1541 */     toolBar.add(this.terminalPOS);
/* 1542 */     toolBar.addSeparator(new Dimension(20, 0));
/*      */ 
/* 1547 */     this.resumenPOS = makeNavigationButton("tpvres32", "resumendiario", Mensajes.getString("resumendia"), "");
/*      */ 
/* 1549 */     toolBar.add(this.resumenPOS);
/* 1550 */     toolBar.addSeparator(new Dimension(30, 0));
/*      */ 
/* 1553 */     this.salirPOS = makeNavigationButton("exit32", "salir", Mensajes.getString("salir"), "");
/*      */ 
/* 1555 */     toolBar.add(this.salirPOS);
/*      */   }
/*      */ 
/*      */   private JButton makeNavigationButton(String imageName, String actionCommand, String toolTipText, String altText)
/*      */   {
/* 1563 */     String imgLocation = "/contaes/iconos/" + imageName + ".png";
/* 1564 */     URL imageURL = MarcoInicio.class.getResource(imgLocation);
/*      */ 
/* 1567 */     JButton button = new JButton();
/* 1568 */     button.setActionCommand(actionCommand);
/* 1569 */     button.setToolTipText(toolTipText);
/* 1570 */     button.addActionListener(this);
/*      */ 
/* 1572 */     if (imageURL != null) {
/* 1573 */       button.setIcon(new ImageIcon(imageURL, altText));
/*      */     } else {
/* 1575 */       button.setText(altText);
/* 1576 */       System.err.println("Resource not found: " + imgLocation);
/*      */     }
/*      */ 
/* 1579 */     return button;
/*      */   }
/*      */ 
/*      */   public void mostrarDialogo(JDialog dlg) {
/* 1583 */     Dimension dlgSize = dlg.getPreferredSize();
/* 1584 */     Dimension frmSize = getSize();
/* 1585 */     Point loc = getLocation();
/* 1586 */     dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
/*      */ 
/* 1589 */     dlg.pack();
/* 1590 */     dlg.setVisible(true);
/*      */   }
/*      */ 
/*      */   private void mostrarMarco(JInternalFrame marco) {
/* 1594 */     marco.setVisible(true);
/*      */ 
/* 1596 */     marco.moveToFront();
/*      */     try {
/* 1598 */       if (marco.isIcon()) {
/* 1599 */         marco.setIcon(false);
/*      */       }
/* 1601 */       marco.setSelected(true);
/*      */     } catch (PropertyVetoException exc) {
/*      */     }
/*      */   }
/*      */ 
/*      */   public void mostrarMarcoExterno(JFrame frame) {
/* 1607 */     frame.validate();
/*      */ 
/* 1609 */     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/* 1610 */     Dimension frameSize = frame.getSize();
/* 1611 */     if (frameSize.height > screenSize.height) {
/* 1612 */       frameSize.height = screenSize.height;
/*      */     }
/* 1614 */     if (frameSize.width > screenSize.width) {
/* 1615 */       frameSize.width = screenSize.width;
/*      */     }
/* 1617 */     frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
/*      */ 
/* 1619 */     frame.setVisible(true);
/*      */   }
/*      */ 
/*      */   private void abrirManual()
/*      */   {
/*      */     try {
/* 1625 */       File path = new File("ManualContaes.pdf");
/* 1626 */       Desktop.getDesktop().open(path);
/*      */     } catch (IOException ex) {
/* 1628 */       ex.printStackTrace();
/*      */     }
/*      */   }
/*      */ 
/*      */   private void cerrarMarcos()
/*      */   {
/* 1646 */     JInternalFrame[] marco = this.escritorio.getAllFrames();
/* 1647 */     for (int x = 0; x < marco.length; x++) {
/* 1648 */       if (marco[x] != null) {
/* 1649 */         marco[x].hide();
/*      */       }
/*      */     }
/* 1652 */     this.escritorio.repaint();
/*      */   }
/*      */ 
/*      */   private void anularMarco(MarcoInterno marco) {
/* 1656 */     if (marco != null) {
/* 1657 */       eliminarMarcoMenu(marco.getTitle());
/* 1658 */       marco.cerrarConexion();
/* 1659 */       marco.dispose();
/*      */     }
/*      */   }
/*      */ 
/*      */   private void anularMarco(JInternalFrame marco) {
/* 1664 */     if (marco != null) {
/* 1665 */       eliminarMarcoMenu(marco.getTitle());
/* 1666 */       marco.dispose();
/*      */     }
/*      */   }
/*      */ 
/*      */   private void anularMarco(CreacionFacturas marco_CreacionFacturas) {
/* 1671 */     if (marco_CreacionFacturas != null) {
/* 1672 */       eliminarMarcoMenu(marco_CreacionFacturas.getTitle());
/* 1673 */       marco_CreacionFacturas.cerrarConexion();
/* 1674 */       marco_CreacionFacturas.dispose();
/*      */     }
/*      */   }
/*      */ 
/*      */   private void crearMarcoAsientos() {
/* 1679 */     if ((this.marco_asiento == null) || (this.marco_asiento.isClosed())) {
/* 1680 */       this.marco_asiento = new Asientos();
/* 1681 */       this.escritorio.add(this.marco_asiento);
/* 1682 */       annadirMarcoMenu(this.marco_asiento);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void crearMarcoCuentas()
/*      */   {
/* 1694 */     if ((this.marco_cuentas == null) || (this.marco_cuentas.isClosed())) {
/* 1695 */       this.marco_cuentas = new GestionCuentas();
/* 1696 */       this.escritorio.add(this.marco_cuentas);
/* 1697 */       annadirMarcoMenu(this.marco_cuentas);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void crearMarcoNuevaFactura() {
/* 1702 */     if ((this.marco_CreacionFacturas == null) || (this.marco_CreacionFacturas.isClosed())) {
/* 1703 */       this.marco_CreacionFacturas = new CreacionFacturas();
/* 1704 */       this.escritorio.add(this.marco_CreacionFacturas);
/* 1705 */       annadirMarcoMenu(this.marco_CreacionFacturas);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void crearMarcoFacturacionClientes() {
/* 1710 */     if ((this.marco_facturacion == null) || (this.marco_facturacion.isClosed())) {
/* 1711 */       this.marco_facturacion = new FacturasJInternalFrame();
/* 1712 */       this.escritorio.add(this.marco_facturacion);
/* 1713 */       annadirMarcoMenu(this.marco_facturacion);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void crearMarcoFacturacionProveedores() {
/* 1718 */     if ((this.marco_facturacion_p == null) || (this.marco_facturacion_p.isClosed())) {
/* 1719 */       this.marco_facturacion_p = new FacturasProveedoresJInternalFrame();
/* 1720 */       this.escritorio.add(this.marco_facturacion_p);
/* 1721 */       annadirMarcoMenu(this.marco_facturacion_p);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void crearCentroControl() {
/* 1726 */     if ((this.centroControl == null) || (this.centroControl.isClosed())) {
/* 1727 */       this.centroControl = new ControlCentreJInternalFrame();
/* 1728 */       this.escritorio.add(this.centroControl);
/* 1729 */       annadirMarcoMenu(this.centroControl);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void annadirMarcoMenu(final JInternalFrame marco) {
/* 1734 */     String imgLocation = "/contaes/iconos/window.png";
/* 1735 */     URL imageURL = MarcoInicio.class.getResource(imgLocation);
/*      */ 
/* 1738 */     JMenuItem elementoMenu = new JMenuItem();
/* 1739 */     elementoMenu.addActionListener(new ActionListener()
/*      */     {
/*      */       public void actionPerformed(ActionEvent e)
/*      */       {
/* 1743 */         MarcoInicio.this.mostrarMarco(marco);
/*      */       }
/*      */     });
/* 1746 */     elementoMenu.setText(marco.getTitle());
/*      */ 
/* 1748 */     if (imageURL != null) {
/* 1749 */       elementoMenu.setIcon(new ImageIcon(imageURL));
/*      */     }
/* 1751 */     this.barraMenu.getMenu(7).add(elementoMenu);
/*      */   }
/*      */ 
/*      */   public void eliminarMarcoMenu(String titulo) {
/* 1755 */     for (int x = 0; x < this.barraMenu.getMenu(7).getItemCount(); x++)
/* 1756 */       if (titulo.equals(this.barraMenu.getMenu(7).getItem(x).getText())) {
/* 1757 */         this.barraMenu.getMenu(7).remove(x);
/* 1758 */         break;
/*      */       }
/*      */   }
/*      */ 
/*      */   private class CierreVentana extends WindowAdapter
/*      */   {
/*      */     private CierreVentana()
/*      */     {
/*      */     }
/*      */ 
/*      */     public void windowClosing(WindowEvent arg0)
/*      */     {
/* 1781 */       Rectangle dimMarco = arg0.getWindow().getBounds();
/* 1782 */       grabarDimesiones(dimMarco);
/* 1783 */       grabarEmpresaSeleccionada();
/* 1784 */       if (Inicio.getCEmpresa() != null) {
/* 1785 */         Inicio.getCEmpresa().cierraConexion();
/*      */       }
/* 1787 */       if (Inicio.getCGeneral() != null) {
/* 1788 */         Inicio.getCGeneral().cierraConexion();
/*      */       }
/* 1790 */       if (Inicio.getCFacturacion() != null) {
/* 1791 */         Inicio.getCFacturacion().cierraConexion();
/*      */       }
/* 1793 */       if (Inicio.getcAlmacen() != null) {
/* 1794 */         Inicio.getcAlmacen().cierraConexion();
/*      */       }
/* 1796 */       if (MarcoInicio.this.centroControl != null) {
/* 1797 */         MarcoInicio.this.centroControl.saveFrameBounds();
/*      */       }
/* 1799 */       super.windowClosing(arg0);
/*      */     }
/*      */ 
/*      */     private void grabarDimesiones(Rectangle dim) {
/* 1803 */       int[] panelState = MarcoInicio.this.panelMenu.saveState();
/*      */ 
/* 1813 */       ConfiguracionBean config = new ConfiguracionBean();
/* 1814 */       ArrayList array = new ArrayList();
/* 1815 */       array.add(Integer.toString(dim.x));
/* 1816 */       array.add(Integer.toString(dim.y));
/* 1817 */       array.add(Integer.toString(dim.width));
/* 1818 */       array.add(Integer.toString(dim.height));
/* 1819 */       config.saveConfig("<lastposition>", array);
/* 1820 */       array.clear();
/* 1821 */       for (int x = 0; x < 8; x++) {
/* 1822 */         array.add(Integer.toString(panelState[x]));
/*      */       }
/* 1824 */       config.saveConfig("<menuconfig>", array);
/*      */     }
/*      */ 
/*      */     private void grabarEmpresaSeleccionada()
/*      */     {
/* 1832 */       ConfiguracionBean config = new ConfiguracionBean();
/* 1833 */       ArrayList array = new ArrayList();
/* 1834 */       array.add(Integer.toString(Inicio.p.getEmpresa()));
/* 1835 */       array.add(Inicio.p.getEjercicio());
/* 1836 */       config.saveConfig("<lastuse>", array);
/*      */     }
/*      */   }
/*      */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.MarcoInicio
 * JD-Core Version:    0.6.2
 */