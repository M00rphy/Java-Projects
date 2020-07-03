/*      */ package almacen2.gui;
/*      */ 
/*      */ import almacen2.data.AlmacenInterno;
/*      */ import almacen2.data.FPObject;
/*      */ import almacen2.data.ManejadorAlmacenInterno;
/*      */ import almacen2.data.ManejadorFP;
/*      */ import almacen2.data.ManejadorImagenes;
/*      */ import almacen2.data.ManejadorListasInicio;
/*      */ import almacen2.data.ManejadorPIO;
/*      */ import almacen2.data.ManejadorProducto;
/*      */ import almacen2.data.MySQLConection;
/*      */ import almacen2.data.PIOListObject;
/*      */ import almacen2.data.PIOObject;
/*      */ import almacen2.data.ProductObject;
/*      */ import almacen2.data.UserObject;
/*      */ import almacen2.data.graficos.ManejadorGraficos;
/*      */ import almacen2.gui.graficos.MarcoParaGraficos;
/*      */ import contaes.Inicio;
/*      */ import contaes.MarcoInicio;
/*      */ import contaes.Puente;
/*      */ import contaes.auxiliar.DocNumeros;
/*      */ import contaes.manejoDatos.ManejoTiposIVA;
/*      */ import contaes.manejoDatos.TipoIVA;
/*      */ import internationalization.Mensajes;
/*      */ import java.awt.BorderLayout;
/*      */ import java.awt.Color;
/*      */ import java.awt.Container;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.HeadlessException;
/*      */ import java.awt.Point;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.Toolkit;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.ActionListener;
/*      */ import java.awt.event.MouseAdapter;
/*      */ import java.awt.event.MouseEvent;
/*      */ import java.beans.XMLDecoder;
/*      */ import java.beans.XMLEncoder;
/*      */ import java.io.BufferedInputStream;
/*      */ import java.io.BufferedOutputStream;
/*      */ import java.io.FileInputStream;
/*      */ import java.io.FileNotFoundException;
/*      */ import java.io.FileOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.text.DecimalFormat;
/*      */ import java.text.DecimalFormatSymbols;
/*      */ import java.text.ParseException;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Date;
import java.util.List;
/*      */ import java.util.logging.Level;
/*      */ import java.util.logging.Logger;
/*      */ import javax.imageio.ImageIO;
/*      */ import javax.swing.DefaultComboBoxModel;
/*      */ import javax.swing.DefaultListModel;
/*      */ import javax.swing.ImageIcon;
/*      */ import javax.swing.JButton;
/*      */ import javax.swing.JComboBox;
/*      */ import javax.swing.JFileChooser;
/*      */ import javax.swing.JFormattedTextField;
/*      */ import javax.swing.JFrame;
/*      */ import javax.swing.JInternalFrame;
/*      */ import javax.swing.JLabel;
/*      */ import javax.swing.JList;
/*      */ import javax.swing.JMenuItem;
/*      */ import javax.swing.JOptionPane;
/*      */ import javax.swing.JPanel;
/*      */ import javax.swing.JPopupMenu;
/*      */ import javax.swing.JScrollPane;
/*      */ import javax.swing.JTextField;
/*      */ import javax.swing.event.InternalFrameAdapter;
/*      */ import javax.swing.event.InternalFrameEvent;
/*      */ import javax.swing.event.ListSelectionEvent;
/*      */ import javax.swing.event.ListSelectionListener;
/*      */ import javax.swing.filechooser.FileNameExtensionFilter;
/*      */ import org.jdesktop.swingx.MultiSplitLayout;
/*      */ import org.jdesktop.swingx.MultiSplitLayout.Node;
/*      */ import org.jdesktop.swingx.MultiSplitPane;
/*      */ 
/*      */ public class AlmacenJInternalFrame extends JInternalFrame
/*      */ {
/*      */   private static final String L00 = "l01";
/*      */   private static final String L01 = "l02";
/*      */   private static final String L02 = "l03";
/*   72 */   private MultiSplitPane panelMultiple = null;
/*   73 */   private PanelIzquierda panelIzquierda = null;
/*   74 */   private PanelCentro panelCentro = null;
/*   75 */   private PanelDerecha panelDerecha = null;
/*   76 */   BorderLayout bLayout = new BorderLayout();
/*   77 */   private static MySQLConection con = null;
/*   78 */   private ManejadorListasInicio listas = null;
/*   79 */   private ProductObject articuloMostrado = null;
/*   80 */   private String articuloAnterior = null;
/*   81 */   private boolean nuevoProducto = false;
/*      */ 
/*      */   public AlmacenJInternalFrame(String titulo) throws HeadlessException {
/*   84 */     super(titulo, true, true, true, true);
/*   85 */     setDefaultCloseOperation(2);
/*   86 */     inicializar();
/*      */   }
/*      */ 
/*      */   private void inicializar() {
/*   90 */     con = new MySQLConection();
/*   91 */     this.listas = new ManejadorListasInicio(con);
/*   92 */     addInternalFrameListener(new InternalFrameAdapter()
/*      */     {
/*      */       public void internalFrameActivated(InternalFrameEvent e)
/*      */       {
/*   96 */         if (AlmacenJInternalFrame.this.articuloMostrado != null) {
/*   97 */           AlmacenJInternalFrame.this.panelDerecha.colocarArticulo(AlmacenJInternalFrame.this.articuloMostrado);
/*      */         }
/*   99 */         AlmacenJInternalFrame.this.panelDerecha.llenarComboTiposIva();
/*      */       }
/*      */ 
/*      */       public void internalFrameClosed(InternalFrameEvent e)
/*      */       {
/*  104 */         Inicio.frame.eliminarMarcoMenu(Mensajes.getString("almacen"));
/*  105 */         super.internalFrameClosed(e);
/*      */       }
/*      */ 
/*      */       public void internalFrameClosing(InternalFrameEvent e)
/*      */       {
/*      */         try
/*      */         {
/*  112 */           XMLEncoder encod = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("configdir/colocaPanel.ini")));
/*      */ 
/*  114 */           MultiSplitLayout.Node model = AlmacenJInternalFrame.this.panelMultiple.getMultiSplitLayout().getModel();
/*  115 */           encod.writeObject(model);
/*  116 */           encod.close();
/*      */         } catch (FileNotFoundException e1) {
/*  118 */           e1.printStackTrace();
/*      */         }
/*  120 */         if (AlmacenJInternalFrame.con != null) {
/*  121 */           AlmacenJInternalFrame.con.cierraConexion();
/*      */         }
/*  123 */         super.internalFrameClosing(e);
/*      */       }
/*      */ 
/*      */       public void internalFrameDeactivated(InternalFrameEvent e)
/*      */       {
/*  128 */         super.internalFrameDeactivated(e);
/*      */       }
/*      */ 
/*      */       public void internalFrameOpened(InternalFrameEvent e)
/*      */       {
/*  133 */         super.internalFrameOpened(e);
/*      */       }
/*      */     });
/*  136 */     setBounds(20, 20, 725, 485);
/*  137 */     setFrameIcon(new ImageIcon(getClass().getResource("/contaes/iconos/producto18.png")));
/*  138 */     getContentPane().setLayout(this.bLayout);
/*  139 */     getContentPane().add(crearPanelMultiple(), "Center");
/*      */   }
/*      */ 
/*      */   private MultiSplitPane crearPanelMultiple() {
/*  143 */     if (this.panelMultiple == null) {
/*  144 */       String layoutDef = "(ROW (LEAF name=izquierda weight=0.3) (LEAF name=centro weight=0.3) (LEAF name=derecha weight=0.4))";
/*      */ 
/*  146 */       this.panelMultiple = new MultiSplitPane();
/*      */       try
/*      */       {
/*  149 */         XMLDecoder d = new XMLDecoder(new BufferedInputStream(new FileInputStream("configdir/colocaPanel.ini")));
/*      */ 
/*  152 */         MultiSplitLayout.Node model = (MultiSplitLayout.Node)d.readObject();
/*  153 */         this.panelMultiple.getMultiSplitLayout().setModel(model);
/*  154 */         this.panelMultiple.getMultiSplitLayout().setFloatingDividers(false);
/*  155 */         d.close();
/*      */       } catch (Exception exc) {
/*  157 */         MultiSplitLayout.Node model = MultiSplitLayout.parseModel(layoutDef);
/*  158 */         this.panelMultiple.getMultiSplitLayout().setModel(model);
/*      */       }
/*  160 */       this.panelMultiple.setDividerSize(1);
/*  161 */       this.panelMultiple.add(crearPanelIzquierda(), "izquierda");
/*  162 */       this.panelMultiple.add(crearPanelCentro(), "centro");
/*  163 */       this.panelMultiple.add(crearPanelDerecha(), "derecha");
/*      */     }
/*      */ 
/*  166 */     return this.panelMultiple;
/*      */   }
/*      */ 
/*      */   private PanelIzquierda crearPanelIzquierda() {
/*  170 */     if (this.panelIzquierda == null) {
/*  171 */       this.panelIzquierda = new PanelIzquierda();
/*      */     }
/*      */ 
/*  174 */     return this.panelIzquierda;
/*      */   }
/*      */ 
/*      */   private PanelCentro crearPanelCentro() {
/*  178 */     if (this.panelCentro == null) {
/*  179 */       this.panelCentro = new PanelCentro();
/*      */     }
/*      */ 
/*  182 */     return this.panelCentro;
/*      */   }
/*      */ 
/*      */   private JPanel crearPanelDerecha() {
/*  186 */     if (this.panelDerecha == null) {
/*  187 */       this.panelDerecha = new PanelDerecha();
/*      */     }
/*      */ 
/*  190 */     return this.panelDerecha;
/*      */   }
/*      */ 
/*      */   private void realizarGrafica(ActionEvent arg0) {
/*  194 */     String cmd = arg0.getActionCommand();
/*  195 */     if (cmd.substring(0, 1).equals("l")) {
/*  196 */       int origen = Integer.parseInt(cmd.substring(1, 2));
/*  197 */       int tipo = Integer.parseInt(cmd.substring(2, 3));
/*  198 */       int haySeleccion = comprobarSeleccionLista(origen);
/*  199 */       if (haySeleccion == 1) {
/*  200 */         JOptionPane.showMessageDialog(this, Mensajes.getString("errorListadoF") + "\n" + Mensajes.getString("errorListadoFP"), "Aviso", 1);
/*      */       }
/*  204 */       else if (haySeleccion == 2) {
/*  205 */         JOptionPane.showMessageDialog(this, Mensajes.getString("errorListadoP") + "\n" + Mensajes.getString("errorListadoFP"), "Aviso", 1);
/*      */       }
/*      */       else
/*      */       {
/*  210 */         String[] fecha = null;
/*  211 */         if (tipo != 0)
/*  212 */           fecha = seleccionFechas.obtenerObjeto(Inicio.frame);
/*      */         else {
/*  214 */           fecha = seleccionFecha.obtenerObjeto(Inicio.frame);
/*      */         }
/*  216 */         if (((fecha != null) || (tipo == 0)) && 
/*  217 */           (origen == 0) && 
/*  218 */           (this.articuloMostrado != null)) {
/*  219 */           String titulo1 = "";
/*  220 */           String[] leyendas = new String[2];
/*  221 */           ManejadorGraficos clase = null;
/*  222 */           if ("l01".equals(cmd)) {
/*  223 */             clase = new ManejadorGraficos(con, this.articuloMostrado.getReferencia(), "", 0, fecha[0], fecha[1]);
/*  224 */             titulo1 = Mensajes.getString("cyvde") + " : " + this.articuloMostrado.getReferencia();
/*  225 */             leyendas[0] = Mensajes.getString("compras");
/*  226 */             leyendas[1] = Mensajes.getString("ventas");
/*  227 */           } else if ("l02".equals(cmd)) {
/*  228 */             if (this.articuloAnterior != null) {
/*  229 */               clase = new ManejadorGraficos(con, this.articuloMostrado.getReferencia(), this.articuloAnterior, 1, fecha[0], fecha[1]);
/*      */ 
/*  231 */               titulo1 = Mensajes.getString("compras") + " : " + this.articuloMostrado.getReferencia() + " vs " + this.articuloAnterior;
/*      */ 
/*  233 */               leyendas[0] = this.articuloMostrado.getReferencia();
/*  234 */               leyendas[1] = this.articuloAnterior;
/*      */             } else {
/*  236 */               JOptionPane.showMessageDialog(this, Mensajes.getString("errorListado01") + "\n" + Mensajes.getString("errorListado02"), "Error", 0);
/*      */             }
/*      */ 
/*      */           }
/*  241 */           else if ("l03".equals(cmd)) {
/*  242 */             if (this.articuloAnterior != null) {
/*  243 */               clase = new ManejadorGraficos(con, this.articuloMostrado.getReferencia(), this.articuloAnterior, 2, fecha[0], fecha[1]);
/*      */ 
/*  245 */               titulo1 = Mensajes.getString("ventas") + " : " + this.articuloMostrado.getReferencia() + " vs " + this.articuloAnterior;
/*      */ 
/*  247 */               leyendas[0] = this.articuloMostrado.getReferencia();
/*  248 */               leyendas[1] = this.articuloAnterior;
/*      */             } else {
/*  250 */               JOptionPane.showMessageDialog(this, Mensajes.getString("errorListado01") + "\n" + Mensajes.getString("errorListado02"), "Error", 0);
/*      */             }
/*      */ 
/*      */           }
/*      */ 
/*  256 */           if (clase != null) {
/*  257 */             MarcoParaGraficos marco = new MarcoParaGraficos(titulo1, 2, leyendas, clase.getXTitle(), clase.getDatos());
/*      */ 
/*  259 */             mostrarMarco(marco);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private int comprobarSeleccionLista(int origen)
/*      */   {
/*  269 */     if ((origen == 1) && (!this.panelIzquierda.tipoListado)) {
/*  270 */       return 1;
/*      */     }
/*  272 */     if ((origen == 1) && (this.panelIzquierda.jList.isSelectionEmpty())) {
/*  273 */       return 1;
/*      */     }
/*  275 */     if ((origen == 2) && (this.panelIzquierda.tipoListado)) {
/*  276 */       return 2;
/*      */     }
/*  278 */     if ((origen == 2) && (this.panelIzquierda.jList.isSelectionEmpty())) {
/*  279 */       return 2;
/*      */     }
/*  281 */     return 0;
/*      */   }
/*      */ 
/*      */   public void borrarPIO(String referencia, int id) {
/*  285 */     ManejadorPIO pio = new ManejadorPIO(con, referencia);
/*  286 */     pio.eliminarId(id);
/*      */   }
/*      */ 
/*      */   public void modificarPIO(String referencia, PIOObject objeto) {
/*  290 */     ManejadorPIO pio = new ManejadorPIO(con, referencia);
/*  291 */     pio.modificarIO(objeto);
/*      */   }
/*      */ 
/*      */   private void llamarMarcoListaPIO() {
/*  295 */     if (this.articuloMostrado == null) {
/*  296 */       return;
/*      */     }
/*  298 */     ManejadorPIO pio = new ManejadorPIO(con, this.articuloMostrado.getReferencia());
/*  299 */     if (pio == null) {
/*  300 */       return;
/*      */     }
/*  302 */     pio.crearLista();
/*      */ 
/*  304 */     ArrayList lista = new ArrayList();
/*  305 */     ArrayList id = new ArrayList();
/*  306 */     for (PIOObject objeto : pio.getPio()) {
/*  307 */       id.add(Integer.valueOf(objeto.getId()));
/*  308 */       String fecha = objeto.getFecha().substring(8) + "-" + objeto.getFecha().substring(5, 7) + "-" + objeto.getFecha().substring(0, 4);
/*  309 */       lista.add(new PIOListObject(fecha, Integer.toString(objeto.getIO()), objeto.getImporte(), objeto.getAlmacen()));
/*      */     }
/*  311 */     lista.add(new PIOListObject(Mensajes.getString("total"), Integer.toString(pio.calcularStock()), pio.calcularBeneficio(), -1));
/*  312 */     MarcoDetallePIO marco = new MarcoDetallePIO(this.articuloMostrado.getReferencia(), this.articuloMostrado.getDescripcion(), id, lista);
/*      */ 
/*  315 */     mostrarMarcoDetalle(marco);
/*      */   }
/*      */ 
/*      */   private void renovarListaCentro(int id, boolean familias) {
/*  319 */     this.listas.actualizarReferencias(id, familias);
/*  320 */     this.panelCentro.cambiarModelo();
/*      */   }
/*      */ 
/*      */   private void llenarCombo(boolean familias) {
/*  324 */     if (familias)
/*  325 */       this.panelDerecha.llenarComboFamilias();
/*      */     else
/*  327 */       this.panelDerecha.llenarComboProveedores();
/*      */   }
/*      */ 
/*      */   private void colocarArticulo(String referencia)
/*      */   {
/*  332 */     if (this.nuevoProducto) {
/*  333 */       this.nuevoProducto = false;
/*      */     }
/*  335 */     if (this.articuloMostrado != null) {
/*  336 */       this.articuloAnterior = this.articuloMostrado.getReferencia();
/*      */     }
/*  338 */     this.articuloMostrado = new ManejadorProducto(con).obtenerProducto(referencia);
/*  339 */     if (this.articuloMostrado != null)
/*  340 */       this.panelDerecha.colocarArticulo(this.articuloMostrado);
/*      */     else
/*  342 */       this.panelDerecha.limpiarPanel();
/*      */   }
/*      */ 
/*      */   private void insertarEntradas()
/*      */   {
/*  347 */     if (this.articuloMostrado == null) {
/*  348 */       return;
/*      */     }
/*  350 */     ArrayList almacenes = new ManejadorAlmacenInterno(con).getAlmacenes();
/*  351 */     PIOObject objeto = CrearEntradas.obtenerObjeto(Inicio.frame, this.articuloMostrado.getReferencia(), 1, almacenes);
/*  352 */     if (objeto != null) {
/*  353 */       int totalObjetos = objeto.getIO();
/*  354 */       objeto.setIO(1);
/*  355 */       objeto.setImporte(this.articuloMostrado.getCoste());
/*  356 */       ManejadorPIO pio = new ManejadorPIO(con, this.articuloMostrado.getReferencia());
/*  357 */       for (int x = 0; x < totalObjetos; x++) {
/*  358 */         pio.nuevaIO(objeto);
/*      */       }
/*      */ 
/*  361 */       this.panelDerecha.colocarStock(pio.calcularStock(), this.articuloMostrado.getStockMinimo());
/*      */     }
/*      */   }
/*      */ 
/*      */   private void insertarSalidas() {
/*  366 */     if (this.articuloMostrado == null) {
/*  367 */       return;
/*      */     }
/*  369 */     ArrayList almacenes = new ManejadorAlmacenInterno(con).getAlmacenes();
/*  370 */     PIOObject objeto = CrearSalidas.obtenerObjeto(Inicio.frame, this.articuloMostrado.getReferencia(), 1, almacenes);
/*  371 */     if (objeto != null) {
/*  372 */       int totalObjetos = objeto.getIO();
/*  373 */       objeto.setIO(-1);
/*  374 */       ManejadorPIO pio = new ManejadorPIO(con, this.articuloMostrado.getReferencia());
/*  375 */       for (int x = 0; x < totalObjetos; x++) {
/*  376 */         pio.nuevaIO(objeto);
/*      */       }
/*      */ 
/*  379 */       this.panelDerecha.colocarStock(pio.calcularStock(), this.articuloMostrado.getStockMinimo());
/*      */     }
/*      */   }
/*      */ 
/*      */   private void traspasarProductos(int idAlmacenOrigen, int stockALmacenOrigen) {
/*  384 */     if (this.articuloMostrado != null) {
/*  385 */       ManejadorPIO pio = new ManejadorPIO(con, this.articuloMostrado.getReferencia());
/*      */ 
/*  387 */       ArrayList almacenes = pio.getAlmacenesConStock();
/*  388 */       TraspasarProductosJDialog dlg = new TraspasarProductosJDialog(Inicio.frame, true, almacenes, stockALmacenOrigen);
/*  389 */       Inicio.frame.mostrarDialogo(dlg);
/*  390 */       int idAlmacenDestino = dlg.getIdAlmacenDestino();
/*  391 */       int unidades = dlg.getUnidades();
/*      */ 
/*  393 */       if ((idAlmacenDestino != -1) && (unidades != -1)) {
/*  394 */         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/*  395 */         String fecha = sdf.format(new Date());
/*  396 */         double coste = this.articuloMostrado.getCoste();
/*  397 */         PIOObject objSalida = new PIOObject(this.articuloMostrado.getId(), fecha, coste, -1, idAlmacenOrigen);
/*  398 */         PIOObject objEntrada = new PIOObject(this.articuloMostrado.getId(), fecha, coste, 1, idAlmacenDestino);
/*  399 */         for (int i = 0; i < unidades; i++)
/*      */         {
/*  401 */           pio.nuevaIO(objSalida);
/*      */ 
/*  403 */           pio.nuevaIO(objEntrada);
/*      */         }
/*  405 */         this.panelDerecha.colocarStock(pio.calcularStock(), this.articuloMostrado.getStockMinimo());
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public void updateStock() {
/*  411 */     ManejadorPIO pio = new ManejadorPIO(con, this.articuloMostrado.getReferencia());
/*  412 */     this.panelDerecha.colocarStock(pio.calcularStock(), this.articuloMostrado.getStockMinimo());
/*      */   }
/*      */ 
/*      */   public void renovarModeloIva() {
/*  416 */     this.panelDerecha.renovarModeloIva();
/*      */   }
/*      */ 
/*      */   private void mostrarMarcoDetalle(JFrame frame) {
/*  420 */     Dimension tamano = getSize();
/*  421 */     Point posicion = getLocationOnScreen();
/*  422 */     frame.validate();
/*      */ 
/*  424 */     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/*  425 */     Dimension frameSize = frame.getSize();
/*  426 */     if (frameSize.height > screenSize.height) {
/*  427 */       frameSize.height = screenSize.height;
/*      */     }
/*  429 */     if (frameSize.width > screenSize.width) {
/*  430 */       frameSize.width = screenSize.width;
/*      */     }
/*  432 */     int x = posicion.x + tamano.width;
/*  433 */     if (x + frameSize.width > screenSize.width) {
/*  434 */       x = screenSize.width - frameSize.width;
/*      */     }
/*  436 */     frame.setLocation(x, posicion.y);
/*  437 */     frame.setVisible(true);
/*      */   }
/*      */ 
/*      */   private void mostrarMarco(JFrame frame) {
/*  441 */     frame.validate();
/*      */ 
/*  443 */     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/*  444 */     Dimension frameSize = frame.getSize();
/*  445 */     if (frameSize.height > screenSize.height) {
/*  446 */       frameSize.height = screenSize.height;
/*      */     }
/*  448 */     if (frameSize.width > screenSize.width) {
/*  449 */       frameSize.width = screenSize.width;
/*      */     }
/*  451 */     frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
/*      */ 
/*  453 */     frame.setVisible(true);
/*      */   }
/*      */ 
/*      */   class PanelDerecha extends JPanel
/*      */   {
/* 1026 */     private JLabel jLabel = null;
/* 1027 */     private JComboBox comboFamilias = null;
/* 1028 */     private JLabel jLabel1 = null;
/* 1029 */     private JTextField campoReferencia = null;
/* 1030 */     private JLabel jLabel2 = null;
/* 1031 */     private JTextField campoDescripcion = null;
/* 1032 */     private JLabel jLabel3 = null;
/* 1033 */     private JComboBox comboProveedores = null;
/* 1034 */     private JLabel jLabel4 = null;
/* 1035 */     private JFormattedTextField campoCoste = null;
/* 1036 */     private JLabel jLabel5 = null;
/* 1037 */     private JLabel jLabel11 = null;
/* 1038 */     private JFormattedTextField campoPVP = null;
/* 1039 */     private JLabel jLabel6 = null;
/* 1040 */     private JTextField campoMargen = null;
/* 1041 */     private JLabel jLabel7 = null;
/* 1042 */     private JTextField campoStock = null;
/* 1043 */     private JButton jButton = null;
/* 1044 */     private JButton jButton1 = null;
/* 1045 */     private JButton jButton2 = null;
/* 1046 */     private JButton jButton3 = null;
/* 1047 */     private JButton grafica1 = null;
/* 1048 */     private JButton grafica2 = null;
/* 1049 */     private JButton grafica3 = null;
/* 1050 */     private Imagen jPanel = null;
/* 1051 */     private JLabel jLabel8 = null;
/* 1052 */     private JLabel jLabel9 = null;
/* 1053 */     private JTextField campoStockMinimo = null;
/* 1054 */     private JTextField campoPedidoMinimo = null;
/* 1055 */     private JComboBox comboTiposIva = null;
/* 1056 */     private JLabel jLabel10 = null;
/*      */     private DecimalFormat fn;
/* 1058 */     ManejadorPIO pio = null;
/*      */ 
/*      */     public PanelDerecha()
/*      */     {
/* 1065 */       initialize();
/*      */     }
/*      */ 
/*      */     private void initialize()
/*      */     {
/* 1074 */       DecimalFormatSymbols formato = new DecimalFormatSymbols();
/* 1075 */       formato.setDecimalSeparator(',');
/* 1076 */       formato.setPerMill('.');
/* 1077 */       this.fn = new DecimalFormat("#,###,##0.00", formato);
/*      */ 
/* 1079 */       this.jLabel10 = new JLabel();
/* 1080 */       this.jLabel10.setBounds(new Rectangle(15, 345, 110, 16));
/* 1081 */       this.jLabel10.setText(Mensajes.getString("tipoIva"));
/* 1082 */       this.jLabel9 = new JLabel();
/* 1083 */       this.jLabel9.setBounds(new Rectangle(240, 305, 110, 16));
/* 1084 */       this.jLabel9.setText(Mensajes.getString("pedidominimo"));
/* 1085 */       this.jLabel8 = new JLabel();
/* 1086 */       this.jLabel8.setBounds(new Rectangle(15, 305, 130, 16));
/* 1087 */       this.jLabel8.setText(Mensajes.getString("stockminimo"));
/* 1088 */       this.jLabel7 = new JLabel();
/* 1089 */       this.jLabel7.setBounds(new Rectangle(240, 265, 110, 16));
/* 1090 */       this.jLabel7.setText(Mensajes.getString("stock"));
/* 1091 */       this.jLabel6 = new JLabel();
/* 1092 */       this.jLabel6.setBounds(new Rectangle(15, 265, 130, 16));
/* 1093 */       this.jLabel6.setText(Mensajes.getString("margen"));
/* 1094 */       this.jLabel5 = new JLabel();
/* 1095 */       this.jLabel5.setBounds(new Rectangle(240, 218, 50, 16));
/* 1096 */       this.jLabel5.setText(Mensajes.getString("pvp"));
/* 1097 */       this.jLabel11 = new JLabel();
/* 1098 */       this.jLabel11.setBounds(new Rectangle(240, 232, 65, 16));
/* 1099 */       this.jLabel11.setText(Mensajes.getString("ivain"));
/* 1100 */       this.jLabel4 = new JLabel();
/* 1101 */       this.jLabel4.setBounds(new Rectangle(50, 225, 50, 16));
/* 1102 */       this.jLabel4.setText(Mensajes.getString("coste"));
/* 1103 */       this.jLabel3 = new JLabel();
/* 1104 */       this.jLabel3.setBounds(new Rectangle(175, 110, 75, 16));
/* 1105 */       this.jLabel3.setText(Mensajes.getString("proveedor"));
/* 1106 */       this.jLabel2 = new JLabel();
/* 1107 */       this.jLabel2.setBounds(new Rectangle(18, 160, 85, 16));
/* 1108 */       this.jLabel2.setText(Mensajes.getString("descripcion"));
/* 1109 */       this.jLabel1 = new JLabel();
/* 1110 */       this.jLabel1.setBounds(new Rectangle(175, 30, 75, 16));
/* 1111 */       this.jLabel1.setText(Mensajes.getString("referencia"));
/* 1112 */       this.jLabel = new JLabel();
/* 1113 */       this.jLabel.setBounds(new Rectangle(175, 70, 70, 16));
/* 1114 */       this.jLabel.setText(Mensajes.getString("familia"));
/* 1115 */       setSize(466, 417);
/* 1116 */       setLayout(null);
/* 1117 */       setBackground(Color.white);
/* 1118 */       add(this.jLabel, null);
/* 1119 */       add(getComboFamilias(), null);
/* 1120 */       add(this.jLabel1, null);
/* 1121 */       add(getCampoReferencia(), null);
/* 1122 */       add(this.jLabel2, null);
/* 1123 */       add(getCampoDescripcion(), null);
/* 1124 */       add(this.jLabel3, null);
/* 1125 */       add(getComboProveedores(), null);
/* 1126 */       add(this.jLabel4, null);
/* 1127 */       add(getCampoCoste(), null);
/* 1128 */       add(this.jLabel5, null);
/* 1129 */       add(this.jLabel11, null);
/* 1130 */       add(getCampoPVP(), null);
/* 1131 */       add(this.jLabel6, null);
/* 1132 */       add(getCampoMargen(), null);
/* 1133 */       add(this.jLabel7, null);
/* 1134 */       add(getCampoStock(), null);
/* 1135 */       add(this.jLabel8, null);
/* 1136 */       add(getCampoStockMinimo(), null);
/* 1137 */       add(this.jLabel9, null);
/* 1138 */       add(getCampoPedidoMinimo(), null);
/* 1139 */       add(this.jLabel10, null);
/* 1140 */       add(getComboTiposIva(), null);
/* 1141 */       add(getJButton(), null);
/* 1142 */       add(getJButton1(), null);
/* 1143 */       add(getJButton2(), null);
/* 1144 */       add(getJButton3(), null);
/* 1145 */       add(getGrafica1(), null);
/* 1146 */       add(getGrafica2(), null);
/* 1147 */       add(getGrafica3(), null);
/* 1148 */       add(getJPanel(), null);
/*      */     }
/*      */ 
/*      */     private JTextField getCampoStockMinimo()
/*      */     {
/* 1157 */       if (this.campoStockMinimo == null) {
/* 1158 */         this.campoStockMinimo = new JTextField();
/* 1159 */         this.campoStockMinimo.setBounds(new Rectangle(155, 300, 70, 19));
/*      */       }
/* 1161 */       return this.campoStockMinimo;
/*      */     }
/*      */ 
/*      */     private JTextField getCampoPedidoMinimo()
/*      */     {
/* 1170 */       if (this.campoPedidoMinimo == null) {
/* 1171 */         this.campoPedidoMinimo = new JTextField();
/* 1172 */         this.campoPedidoMinimo.setBounds(new Rectangle(360, 300, 70, 19));
/*      */       }
/* 1174 */       return this.campoPedidoMinimo;
/*      */     }
/*      */ 
/*      */     private JComboBox getComboFamilias()
/*      */     {
/* 1183 */       if (this.comboFamilias == null) {
/* 1184 */         this.comboFamilias = new JComboBox();
/* 1185 */         this.comboFamilias.setBounds(new Rectangle(265, 65, 160, 23));
/* 1186 */         this.comboFamilias.setBackground(Color.white);
/* 1187 */         llenarComboFamilias();
/*      */       }
/* 1189 */       return this.comboFamilias;
/*      */     }
/*      */ 
/*      */     private JTextField getCampoReferencia()
/*      */     {
/* 1198 */       if (this.campoReferencia == null) {
/* 1199 */         this.campoReferencia = new JTextField();
/* 1200 */         this.campoReferencia.setEditable(false);
/* 1201 */         this.campoReferencia.setBounds(new Rectangle(265, 25, 150, 19));
/*      */       }
/* 1203 */       return this.campoReferencia;
/*      */     }
/*      */ 
/*      */     private JTextField getCampoDescripcion()
/*      */     {
/* 1212 */       if (this.campoDescripcion == null) {
/* 1213 */         this.campoDescripcion = new JTextField();
/* 1214 */         this.campoDescripcion.setBounds(new Rectangle(15, 180, 430, 19));
/*      */       }
/* 1216 */       return this.campoDescripcion;
/*      */     }
/*      */ 
/*      */     private JComboBox getComboProveedores()
/*      */     {
/* 1225 */       if (this.comboProveedores == null) {
/* 1226 */         this.comboProveedores = new JComboBox();
/* 1227 */         this.comboProveedores.setBounds(new Rectangle(265, 105, 160, 23));
/* 1228 */         this.comboProveedores.setBackground(Color.white);
/* 1229 */         llenarComboProveedores();
/*      */       }
/* 1231 */       return this.comboProveedores;
/*      */     }
/*      */ 
/*      */     private JFormattedTextField getCampoCoste()
/*      */     {
/* 1240 */       if (this.campoCoste == null) {
/* 1241 */         this.campoCoste = new JFormattedTextField(this.fn);
/* 1242 */         this.campoCoste.setDocument(new DocNumeros());
/* 1243 */         this.campoCoste.setBounds(new Rectangle(105, 220, 110, 19));
/*      */       }
/* 1245 */       return this.campoCoste;
/*      */     }
/*      */ 
/*      */     private JFormattedTextField getCampoPVP()
/*      */     {
/* 1254 */       if (this.campoPVP == null) {
/* 1255 */         this.campoPVP = new JFormattedTextField(this.fn);
/* 1256 */         this.campoPVP.setDocument(new DocNumeros());
/* 1257 */         this.campoPVP.setBounds(new Rectangle(295, 220, 110, 19));
/*      */       }
/* 1259 */       return this.campoPVP;
/*      */     }
/*      */ 
/*      */     private JTextField getCampoMargen()
/*      */     {
/* 1268 */       if (this.campoMargen == null) {
/* 1269 */         this.campoMargen = new JTextField();
/* 1270 */         this.campoMargen.setBounds(new Rectangle(155, 260, 70, 19));
/* 1271 */         this.campoMargen.setEditable(false);
/*      */       }
/* 1273 */       return this.campoMargen;
/*      */     }
/*      */ 
/*      */     private JTextField getCampoStock()
/*      */     {
/* 1282 */       if (this.campoStock == null) {
/* 1283 */         this.campoStock = new JTextField();
/* 1284 */         this.campoStock.setBounds(new Rectangle(360, 260, 70, 19));
/* 1285 */         this.campoStock.setEditable(false);
/* 1286 */         this.campoStock.addMouseListener(new MouseAdapter()
/*      */         {
/*      */           public void mouseClicked(MouseEvent e)
/*      */           {
/* 1290 */             if (e.getClickCount() == 1) {
/* 1291 */               AlmacenJInternalFrame.PanelDerecha.this.mostrarStockAlmacenes(e);
/*      */             }
/*      */           }
/*      */         });
/*      */       }
/* 1296 */       return this.campoStock;
/*      */     }
/*      */ 
/*      */     private JComboBox getComboTiposIva() {
/* 1300 */       if (this.comboTiposIva == null) {
/* 1301 */         this.comboTiposIva = new JComboBox();
/* 1302 */         this.comboTiposIva.setBounds(155, 340, 200, 23);
/* 1303 */         this.comboTiposIva.setBackground(Color.white);
/* 1304 */         llenarComboTiposIva();
/*      */       }
/* 1306 */       return this.comboTiposIva;
/*      */     }
/*      */ 
/*      */     private JButton getJButton()
/*      */     {
/* 1315 */       if (this.jButton == null) {
/* 1316 */         this.jButton = new JButton();
/* 1317 */         this.jButton.setBounds(new Rectangle(10, 400, 160, 27));
/* 1318 */         this.jButton.setHorizontalTextPosition(2);
/* 1319 */         this.jButton.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/save16.png")));
/* 1320 */         this.jButton.setText(Mensajes.getString("guardar"));
/* 1321 */         this.jButton.addActionListener(new ActionListener()
/*      */         {
/*      */           public void actionPerformed(ActionEvent e) {
/* 1324 */             AlmacenJInternalFrame.PanelDerecha.this.guardar();
/*      */           }
/*      */         });
/*      */       }
/* 1328 */       return this.jButton;
/*      */     }
/*      */ 
/*      */     private JButton getJButton1()
/*      */     {
/* 1338 */       if (this.jButton1 == null) {
/* 1339 */         this.jButton1 = new JButton();
/* 1340 */         this.jButton1.setBounds(new Rectangle(410, 398, 35, 27));
/*      */ 
/* 1343 */         this.jButton1.setVerticalAlignment(3);
/* 1344 */         this.jButton1.setIcon(new ImageIcon(getClass().getResource("/almacen2/iconos/derecha.png")));
/* 1345 */         this.jButton1.setToolTipText(Mensajes.getString("detalle"));
/* 1346 */         this.jButton1.addActionListener(new ActionListener()
/*      */         {
/*      */           public void actionPerformed(ActionEvent e) {
/* 1349 */             AlmacenJInternalFrame.this.llamarMarcoListaPIO();
/*      */           }
/*      */         });
/*      */       }
/* 1353 */       return this.jButton1;
/*      */     }
/*      */ 
/*      */     private JButton getJButton2()
/*      */     {
/* 1363 */       if (this.jButton2 == null) {
/* 1364 */         this.jButton2 = new JButton();
/* 1365 */         this.jButton2.setBounds(new Rectangle(325, 398, 35, 27));
/* 1366 */         this.jButton1.setVerticalAlignment(3);
/* 1367 */         this.jButton2.setIcon(new ImageIcon(getClass().getResource("/almacen2/iconos/entradas.png")));
/* 1368 */         this.jButton2.setToolTipText(Mensajes.getString("menu21alma"));
/* 1369 */         this.jButton2.addActionListener(new ActionListener()
/*      */         {
/*      */           public void actionPerformed(ActionEvent e) {
/* 1372 */             AlmacenJInternalFrame.this.insertarEntradas();
/*      */           }
/*      */         });
/*      */       }
/* 1376 */       return this.jButton2;
/*      */     }
/*      */ 
/*      */     private JButton getJButton3()
/*      */     {
/* 1386 */       if (this.jButton3 == null) {
/* 1387 */         this.jButton3 = new JButton();
/* 1388 */         this.jButton3.setBounds(new Rectangle(365, 398, 35, 27));
/* 1389 */         this.jButton1.setVerticalAlignment(3);
/* 1390 */         this.jButton3.setIcon(new ImageIcon(getClass().getResource("/almacen2/iconos/salidas.png")));
/* 1391 */         this.jButton3.setToolTipText(Mensajes.getString("menu22alma"));
/* 1392 */         this.jButton3.addActionListener(new ActionListener()
/*      */         {
/*      */           public void actionPerformed(ActionEvent e) {
/* 1395 */             AlmacenJInternalFrame.this.insertarSalidas();
/*      */           }
/*      */         });
/*      */       }
/* 1399 */       return this.jButton3;
/*      */     }
/*      */ 
/*      */     private JButton getGrafica1()
/*      */     {
/* 1409 */       if (this.grafica1 == null) {
/* 1410 */         this.grafica1 = new JButton();
/* 1411 */         this.grafica1.setBounds(new Rectangle(195, 398, 35, 27));
/* 1412 */         this.grafica1.setVerticalAlignment(3);
/* 1413 */         this.grafica1.setIcon(new ImageIcon(getClass().getResource("/almacen2/iconos/UnProducto.png")));
/* 1414 */         this.grafica1.setToolTipText(Mensajes.getString("compraVenta"));
/* 1415 */         this.grafica1.setActionCommand("l01");
/* 1416 */         this.grafica1.addActionListener(new ActionListener()
/*      */         {
/*      */           public void actionPerformed(ActionEvent e) {
/* 1419 */             AlmacenJInternalFrame.this.realizarGrafica(e);
/*      */           }
/*      */         });
/*      */       }
/* 1423 */       return this.grafica1;
/*      */     }
/*      */ 
/*      */     private JButton getGrafica2()
/*      */     {
/* 1432 */       if (this.grafica2 == null) {
/* 1433 */         this.grafica2 = new JButton();
/* 1434 */         this.grafica2.setBounds(new Rectangle(235, 398, 35, 27));
/* 1435 */         this.grafica2.setVerticalAlignment(3);
/* 1436 */         this.grafica2.setIcon(new ImageIcon(getClass().getResource("/almacen2/iconos/DosPrCompras.png")));
/* 1437 */         this.grafica2.setToolTipText(Mensajes.getString("comparativa1"));
/* 1438 */         this.grafica2.setActionCommand("l02");
/* 1439 */         this.grafica2.addActionListener(new ActionListener()
/*      */         {
/*      */           public void actionPerformed(ActionEvent e) {
/* 1442 */             AlmacenJInternalFrame.this.realizarGrafica(e);
/*      */           }
/*      */         });
/*      */       }
/* 1446 */       return this.grafica2;
/*      */     }
/*      */ 
/*      */     private JButton getGrafica3()
/*      */     {
/* 1455 */       if (this.grafica3 == null) {
/* 1456 */         this.grafica3 = new JButton();
/* 1457 */         this.grafica3.setBounds(new Rectangle(275, 398, 35, 27));
/* 1458 */         this.grafica3.setVerticalAlignment(3);
/* 1459 */         this.grafica3.setIcon(new ImageIcon(getClass().getResource("/almacen2/iconos/DosPrVentas.png")));
/* 1460 */         this.grafica3.setToolTipText(Mensajes.getString("comparativa2"));
/* 1461 */         this.grafica3.setActionCommand("l03");
/* 1462 */         this.grafica3.addActionListener(new ActionListener()
/*      */         {
/*      */           public void actionPerformed(ActionEvent e) {
/* 1465 */             AlmacenJInternalFrame.this.realizarGrafica(e);
/*      */           }
/*      */         });
/*      */       }
/* 1469 */       return this.grafica3;
/*      */     }
/*      */ 
/*      */     private Imagen getJPanel()
/*      */     {
/* 1478 */       if (this.jPanel == null) {
/* 1479 */         this.jPanel = new Imagen();
/* 1480 */         this.jPanel.setBounds(new Rectangle(15, 15, 130, 130));
/* 1481 */         this.jPanel.addMouseListener(new MouseAdapter()
/*      */         {
/*      */           public void mouseClicked(MouseEvent e) {
/* 1484 */             super.mouseClicked(e);
/* 1485 */             if ((e.getButton() == 1) && 
/* 1486 */               (AlmacenJInternalFrame.this.articuloMostrado != null)) {
/* 1487 */               JFileChooser chooser = new JFileChooser();
/* 1488 */               FileNameExtensionFilter filter = new FileNameExtensionFilter("Imágenes PNG", new String[] { "png" });
/*      */ 
/* 1490 */               chooser.setFileFilter(filter);
/* 1491 */               int returnVal = chooser.showOpenDialog(Inicio.frame);
/* 1492 */               if (returnVal == 0) {
/* 1493 */                 ManejadorImagenes mI = new ManejadorImagenes(AlmacenJInternalFrame.con);
/*      */                 try
/*      */                 {
/* 1496 */                   mI.setImage(chooser.getSelectedFile(), AlmacenJInternalFrame.this.articuloMostrado.getReferencia());
/*      */ 
/* 1498 */                   AlmacenJInternalFrame.PanelDerecha.this.jPanel.setImage(ImageIO.read(chooser.getSelectedFile()));
/*      */                 } catch (IOException ex) {
/* 1500 */                   Logger.getLogger(AlmacenJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
/*      */                 }
/* 1502 */                 mI.cerrarRs();
/*      */               }
/*      */             }
/*      */           }
/*      */         });
/*      */       }
/*      */ 
/* 1509 */       return this.jPanel;
/*      */     }
/*      */ 
/*      */     private void llenarComboFamilias() {
/* 1513 */       if (this.comboFamilias != null) {
/* 1514 */         this.comboFamilias.removeAllItems();
/* 1515 */         for (int x = 0; x < AlmacenJInternalFrame.this.listas.getFamilias().length; x++)
/* 1516 */           this.comboFamilias.addItem(AlmacenJInternalFrame.this.listas.getFamilias()[x].getNombre());
/*      */       }
/*      */     }
/*      */ 
/*      */     private void llenarComboProveedores()
/*      */     {
/* 1522 */       if (this.comboProveedores != null) {
/* 1523 */         this.comboProveedores.removeAllItems();
/* 1524 */         for (int x = 0; x < AlmacenJInternalFrame.this.listas.getProveedores().length; x++)
/* 1525 */           this.comboProveedores.addItem(AlmacenJInternalFrame.this.listas.getProveedores()[x].getNombre());
/*      */       }
/*      */     }
/*      */ 
/*      */     public void renovarModeloIva()
/*      */     {
/* 1531 */       ArrayList array = new ArrayList();
/* 1532 */       ManejoTiposIVA mT = new ManejoTiposIVA(Inicio.getCGeneral());
/* 1533 */       array = mT.getTiposIVA();
/* 1534 */       DefaultComboBoxModel modelo = new DefaultComboBoxModel();
/* 1535 */       for (TipoIVA tipoIVA : (List<TipoIVA>)array) {
/* 1536 */         modelo.addElement(tipoIVA);
/*      */       }
/* 1538 */       this.comboTiposIva.setModel(modelo);
/*      */     }
/*      */ 
/*      */     private void llenarComboTiposIva() {
/* 1542 */       if (this.comboTiposIva != null) {
/* 1543 */         ArrayList array = new ArrayList();
/* 1544 */         ManejoTiposIVA mT = new ManejoTiposIVA(Inicio.getCGeneral());
/* 1545 */         array = mT.getTiposIVA();
/* 1546 */         DefaultComboBoxModel modelo = new DefaultComboBoxModel();
/* 1547 */         for (TipoIVA tipoIVA : (List<TipoIVA>)array) {
/* 1548 */           modelo.addElement(tipoIVA);
/*      */         }
/* 1550 */         this.comboTiposIva.setModel(modelo);
/*      */       }
/*      */     }
/*      */ 
/*      */     private int comboTipoIvaGetIdTipoIvaSelected() {
/* 1555 */       int i = -1;
/* 1556 */       TipoIVA tipoIva = (TipoIVA)this.comboTiposIva.getSelectedItem();
/* 1557 */       if (tipoIva != null) {
/* 1558 */         i = tipoIva.getId();
/*      */       }
/* 1560 */       return i;
/*      */     }
/*      */ 
/*      */     private void comboTipoIvaSetSelectedId(int id) {
/* 1564 */       for (int i = 0; i < this.comboTiposIva.getItemCount(); i++) {
/* 1565 */         TipoIVA object = (TipoIVA)this.comboTiposIva.getItemAt(i);
/* 1566 */         if (object.getId() == id) {
/* 1567 */           this.comboTiposIva.setSelectedIndex(i);
/* 1568 */           break;
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*      */     private void colocarArticulo(ProductObject articulo) {
/* 1574 */       this.pio = new ManejadorPIO(AlmacenJInternalFrame.con, articulo.getReferencia());
/* 1575 */       if (this.campoReferencia.isEditable()) {
/* 1576 */         this.campoReferencia.setEditable(false);
/*      */       }
/* 1578 */       this.campoReferencia.setText(articulo.getReferencia());
/* 1579 */       int pos = 0;
/* 1580 */       while ((pos < AlmacenJInternalFrame.this.listas.getFamilias().length) && (articulo.getFamilia() != AlmacenJInternalFrame.this.listas.getFamilias()[pos].getId())) {
/* 1581 */         pos++;
/*      */       }
/* 1583 */       this.comboFamilias.setSelectedIndex(pos);
/* 1584 */       pos = 0;
/* 1585 */       while ((pos < AlmacenJInternalFrame.this.listas.getProveedores().length) && (articulo.getProveedor() != AlmacenJInternalFrame.this.listas.getProveedores()[pos].getId())) {
/* 1586 */         pos++;
/*      */       }
/* 1588 */       this.comboProveedores.setSelectedIndex(pos);
/* 1589 */       this.campoDescripcion.setText(articulo.getDescripcion());
/* 1590 */       this.campoCoste.setValue(Double.valueOf(articulo.getCoste()));
/* 1591 */       this.campoPVP.setValue(Double.valueOf(articulo.getPvp()));
/*      */ 
/* 1594 */       this.campoMargen.setText(this.fn.format((articulo.getPvp() / articulo.getCoste() - 1.0D) * 100.0D));
/* 1595 */       colocarStock(this.pio.calcularStock(), articulo.getStockMinimo());
/*      */ 
/* 1597 */       this.campoPedidoMinimo.setText(Integer.toString(articulo.getPedidoMinimo()));
/* 1598 */       this.campoStockMinimo.setText(Integer.toString(articulo.getStockMinimo()));
/* 1599 */       comboTipoIvaSetSelectedId(articulo.getIdtipoiva());
/* 1600 */       ManejadorImagenes mI = new ManejadorImagenes(AlmacenJInternalFrame.con);
/* 1601 */       this.jPanel.setImage(mI.getImage(articulo.getReferencia()));
/*      */ 
/* 1603 */       mI.cerrarRs();
/*      */     }
/*      */ 
/*      */     private void colocarStock(int stock, int stockMinimo) {
/* 1607 */       if (stock <= stockMinimo)
/* 1608 */         this.campoStock.setForeground(Color.RED);
/*      */       else {
/* 1610 */         this.campoStock.setForeground(Color.BLACK);
/*      */       }
/* 1612 */       this.campoStock.setText(Integer.toString(stock));
/*      */     }
/*      */ 
/*      */     private void limpiarPanel() {
/* 1616 */       this.campoReferencia.setText("");
/* 1617 */       if (AlmacenJInternalFrame.this.nuevoProducto) {
/* 1618 */        // int index = AlmacenJInternalFrame.PanelIzquierda.access$700(AlmacenJInternalFrame.this.panelIzquierda).getSelectedIndex();
int index = panelIzquierda.jList.getSelectedIndex(); 
/* 1619 */      //   boolean familias = AlmacenJInternalFrame.PanelIzquierda.access$600(AlmacenJInternalFrame.this.panelIzquierda);
/* 1620 */    boolean familias = panelIzquierda.tipoListado;       
if (familias) {
/* 1621 */           this.comboFamilias.setSelectedIndex(index);
/*      */         }
/*      */         else
/* 1624 */           this.comboProveedores.setSelectedIndex(index);
/*      */       }
/*      */       else
/*      */       {
/* 1628 */         this.comboFamilias.setSelectedIndex(0);
/* 1629 */         this.comboProveedores.setSelectedIndex(0);
/*      */       }
/* 1631 */       this.campoDescripcion.setText("");
/* 1632 */       this.campoCoste.setText("");
/* 1633 */       this.campoPVP.setText("");
/*      */ 
/* 1636 */       this.campoMargen.setText("");
/* 1637 */       this.campoStock.setText("");
/* 1638 */       this.campoStockMinimo.setText("");
/* 1639 */       this.campoPedidoMinimo.setText("");
/* 1640 */       this.campoReferencia.requestFocusInWindow();
/* 1641 */       this.jPanel.setReferencia("imagenNoDisponible");
/*      */     }
/*      */ 
/*      */     private void guardar() {
/* 1645 */       if (comboTipoIvaGetIdTipoIvaSelected() == -1) {
/* 1646 */         JOptionPane.showMessageDialog(AlmacenJInternalFrame.this, "¡Seleccione un tipo de IVA!", "Error", 0);
/*      */ 
/* 1648 */         return;
/*      */       }
/* 1650 */       String sMin = !this.campoStockMinimo.getText().equals("") ? this.campoStockMinimo.getText() : "0";
/* 1651 */       String pMin = !this.campoPedidoMinimo.getText().equals("") ? this.campoPedidoMinimo.getText() : "1";
/* 1652 */       if ((esEntero(sMin)) && (esEntero(pMin))) {
/* 1653 */         int id = AlmacenJInternalFrame.this.articuloMostrado == null ? -1 : AlmacenJInternalFrame.this.articuloMostrado.getId();
/*      */         try {
/* 1655 */           this.campoCoste.commitEdit();
/* 1656 */           this.campoPVP.commitEdit();
/*      */         } catch (ParseException e) {
/* 1658 */           e.printStackTrace();
/*      */         }
/* 1660 */         AlmacenJInternalFrame.this.articuloMostrado = new ProductObject(id, this.campoReferencia.getText(), this.campoDescripcion.getText(), AlmacenJInternalFrame.this.listas.getProveedores()[this.comboProveedores.getSelectedIndex()].getId(), AlmacenJInternalFrame.this.listas.getFamilias()[this.comboFamilias.getSelectedIndex()].getId(), Double.parseDouble(this.campoCoste.getValue().toString()), Double.parseDouble(this.campoPVP.getValue().toString()), "", Integer.parseInt(sMin), Integer.parseInt(pMin), comboTipoIvaGetIdTipoIvaSelected());
/*      */ 
/* 1669 */         ManejadorProducto operar = new ManejadorProducto(AlmacenJInternalFrame.con);
/* 1670 */         if (AlmacenJInternalFrame.this.nuevoProducto) {
/* 1671 */           boolean bien = operar.introducirProducto(AlmacenJInternalFrame.this.articuloMostrado);
/* 1672 */           if (bien) {
/* 1673 */             AlmacenJInternalFrame.this.articuloMostrado.setId(operar.obtenerID(AlmacenJInternalFrame.this.articuloMostrado.getReferencia()));
/* 1674 */             AlmacenJInternalFrame.this.nuevoProducto = false;
/* 1675 */             this.campoReferencia.setEditable(false);
/* 1676 */      //       id = AlmacenJInternalFrame.PanelIzquierda.access$1600(AlmacenJInternalFrame.this.panelIzquierda);
/* 1677 */           id = panelIzquierda.obtenerIdSeleccionado(); 
renovarListaCentro(id,panelIzquierda.tipoListado);
//AlmacenJInternalFrame.this.renovarListaCentro(id, AlmacenJInternalFrame.PanelIzquierda.access$600(AlmacenJInternalFrame.this.panelIzquierda));
/*      */           }
/*      */           else
/*      */           {
/* 1686 */             JOptionPane.showMessageDialog(AlmacenJInternalFrame.this, Mensajes.getString("errorCrearProducto") + "\n" + Mensajes.getString("existeRef"), "Error", 0);
/*      */           }
/*      */         }
/*      */         else
/*      */         {
/* 1691 */           operar.actualizarProducto(AlmacenJInternalFrame.this.articuloMostrado);
/*      */         }
/*      */       } else {
/* 1694 */         JOptionPane.showMessageDialog(AlmacenJInternalFrame.this, Mensajes.getString("errorMinimos"), "Error", 0);
/*      */       }
/*      */     }
/*      */ 
/*      */     private boolean esEntero(String text)
/*      */     {
/*      */       try {
/* 1701 */         Integer.parseInt(text);
/* 1702 */         return true;
/*      */       } catch (NumberFormatException exc) {
/* 1704 */         exc.printStackTrace();
/* 1705 */       }return false;
/*      */     }
/*      */ 
/*      */     private void mostrarStockAlmacenes(MouseEvent e)
/*      */     {
/* 1710 */       if (AlmacenJInternalFrame.this.articuloMostrado != null) {
/* 1711 */         JPopupMenu popup = new JPopupMenu();
/*      */ 
/* 1713 */         ArrayList almacenes = new ManejadorPIO(AlmacenJInternalFrame.con, AlmacenJInternalFrame.this.articuloMostrado.getReferencia()).getAlmacenesConStock();
/* 1714 */         for (AlmacenInterno almacenInterno : (List<AlmacenInterno>)almacenes) {
/* 1715 */           JMenuItem menuItem = new JMenuItem(almacenInterno.toString() + " : " + almacenInterno.getStock());
/* 1716 */           menuItem.addActionListener(new ActionListener()
/*      */           {
/*      */             public void actionPerformed(ActionEvent e) {
/* 1719 */               String str = e.getActionCommand();
/* 1720 */               String stockTemp = str.substring(str.indexOf(": ") + 1);
/* 1721 */               str = str.substring(0, str.indexOf(" -"));
/* 1722 */               int idAlmacenOrigen = Integer.parseInt(str);
/* 1723 */               int stockAlmacenOrigen = Integer.parseInt(stockTemp.trim());
/* 1724 */               AlmacenJInternalFrame.this.traspasarProductos(idAlmacenOrigen, stockAlmacenOrigen);
/*      */             }
/*      */           });
/* 1727 */           popup.add(menuItem);
/*      */         }
/* 1729 */         popup.show(e.getComponent(), e.getX(), e.getY());
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   class PanelCentro extends JPanel
/*      */   {
/*  798 */     private JButton jButton = null;
/*  799 */     private JScrollPane jScrollPane = null;
/*  800 */     private JList jList = null;
/*  801 */     private JPanel jPanel = null;
/*  802 */     private JButton jButton1 = null;
/*  803 */     private JButton jButton2 = null;
/*      */ 
/*  805 */     private DefaultListModel modeloLista = null;
/*      */ 
/*      */     public PanelCentro()
/*      */     {
/*  812 */       initialize();
/*      */     }
/*      */ 
/*      */     private void initialize()
/*      */     {
/*  821 */       setLayout(new BorderLayout());
/*  822 */       setBounds(new Rectangle(0, 0, 180, 400));
/*  823 */       setPreferredSize(new Dimension(180, 400));
/*      */ 
/*  825 */       add(getJButton(), "North");
/*  826 */       add(getJScrollPane(), "Center");
/*  827 */       add(getJPanel(), "South");
/*      */     }
/*      */ 
/*      */     private JButton getJButton()
/*      */     {
/*  836 */       if (this.jButton == null) {
/*  837 */         this.jButton = new JButton();
/*  838 */         this.jButton.setText(Mensajes.getString("referencia") + "  ");
/*  839 */         this.jButton.setHorizontalTextPosition(2);
/*  840 */         this.jButton.setIcon(new ImageIcon(getClass().getResource("/almacen2/iconos/find.png")));
/*  841 */         this.jButton.setToolTipText("Pulse para buscar productos");
/*  842 */         this.jButton.addActionListener(new ActionListener()
/*      */         {
/*      */           public void actionPerformed(ActionEvent e) {
/*  845 */             String referencia = Busqueda.obtenerObjeto(Inicio.frame, AlmacenJInternalFrame.con);
/*  846 */             if (referencia != null) {
/*  847 */               AlmacenJInternalFrame.this.colocarArticulo(referencia);
/*      */             }
/*      */           }
/*      */         });
/*      */       }
/*  852 */       return this.jButton;
/*      */     }
/*      */ 
/*      */     private JScrollPane getJScrollPane()
/*      */     {
/*  861 */       if (this.jScrollPane == null) {
/*  862 */         this.jScrollPane = new JScrollPane();
/*  863 */         this.jScrollPane.setBackground(Color.white);
/*  864 */         this.jScrollPane.setViewportView(getJList());
/*      */       }
/*  866 */       return this.jScrollPane;
/*      */     }
/*      */ 
/*      */     private JList getJList()
/*      */     {
/*  875 */       if (this.jList == null) {
/*  876 */         this.jList = new JList();
/*  877 */         this.jList = new JList(getModeloLista());
/*  878 */         this.jList.setSelectionMode(1);
/*  879 */         this.jList.setLayoutOrientation(0);
/*  880 */         this.jList.setBackground(Color.white);
/*  881 */         this.jList.setDragEnabled(true);
/*  882 */         cambiarModelo();
/*  883 */         this.jList.addListSelectionListener(new ListSelectionListener()
/*      */         {
/*      */           public void valueChanged(ListSelectionEvent e)
/*      */           {
/*  887 */             if ((!e.getValueIsAdjusting()) && (!AlmacenJInternalFrame.PanelCentro.this.jList.isSelectionEmpty()))
/*      */             {
/*  889 */               AlmacenJInternalFrame.this.colocarArticulo(AlmacenJInternalFrame.PanelCentro.this.jList.getSelectedValue().toString());
/*      */             }
/*      */           }
/*      */         });
/*      */       }
/*  894 */       return this.jList;
/*      */     }
/*      */ 
/*      */     private DefaultListModel getModeloLista() {
/*  898 */       if (this.modeloLista == null) {
/*  899 */         this.modeloLista = new DefaultListModel();
/*      */       }
/*  901 */       return this.modeloLista;
/*      */     }
/*      */ 
/*      */     private JPanel getJPanel()
/*      */     {
/*  910 */       if (this.jPanel == null) {
/*  911 */         this.jPanel = new JPanel();
/*  912 */         this.jPanel.setLayout(null);
/*  913 */         this.jPanel.setPreferredSize(new Dimension(260, 34));
/*  914 */         this.jPanel.add(getJButton1(), null);
/*  915 */         this.jPanel.add(getJButton2(), null);
/*      */       }
/*      */ 
/*  918 */       return this.jPanel;
/*      */     }
/*      */ 
/*      */     private JButton getJButton1()
/*      */     {
/*  927 */       if (this.jButton1 == null) {
/*  928 */         this.jButton1 = new JButton();
/*  929 */         this.jButton1.setBounds(new Rectangle(5, 3, 35, 29));
/*      */ 
/*  931 */         this.jButton1.setIcon(new ImageIcon(getClass().getResource("/almacen2/iconos/mas.png")));
/*  932 */         this.jButton1.setToolTipText("Pulse para añadir una nueva referencia");
/*  933 */         this.jButton1.addActionListener(new ActionListener()
/*      */         {
/*      */           public void actionPerformed(ActionEvent e) {
/*  936 */             AlmacenJInternalFrame.PanelCentro.this.anadir();
/*      */           }
/*      */         });
/*      */       }
/*  940 */       return this.jButton1;
/*      */     }
/*      */ 
/*      */     private JButton getJButton2()
/*      */     {
/*  949 */       if (this.jButton2 == null) {
/*  950 */         this.jButton2 = new JButton();
/*  951 */         this.jButton2.setBounds(new Rectangle(45, 3, 35, 29));
/*      */ 
/*  953 */         this.jButton2.setIcon(new ImageIcon(getClass().getResource("/almacen2/iconos/menos.png")));
/*  954 */         this.jButton2.setToolTipText("Pulse para eliminar la referencia seleccionada");
/*  955 */         this.jButton2.addActionListener(new ActionListener()
/*      */         {
/*      */           public void actionPerformed(ActionEvent e) {
/*  958 */             if (!AlmacenJInternalFrame.PanelCentro.this.jList.isSelectionEmpty()) {
/*  959 */               AlmacenJInternalFrame.PanelCentro.this.eliminar();
/*      */             }
/*      */           }
/*      */         });
/*      */       }
/*  964 */       return this.jButton2;
/*      */     }
/*      */ 
/*      */     private void cambiarModelo()
/*      */     {
/*  990 */     //  AlmacenJInternalFrame.this.listas.actualizarReferencias(AlmacenJInternalFrame.PanelIzquierda.access$1600(AlmacenJInternalFrame.this.panelIzquierda), AlmacenJInternalFrame.PanelIzquierda.access$600(AlmacenJInternalFrame.this.panelIzquierda));
/*  991 */     AlmacenJInternalFrame.this.listas.actualizarReferencias(panelIzquierda.obtenerIdSeleccionado(), panelIzquierda.tipoListado);
    if (AlmacenJInternalFrame.this.listas.getReferencias() != null) {
/*  992 */         this.modeloLista.clear();
/*  993 */         for (String refer : AlmacenJInternalFrame.this.listas.getReferencias())
/*  994 */           this.modeloLista.addElement(refer);
/*      */       }
/*      */     }
/*      */ 
/*      */     private void anadir()
/*      */     {
/* 1000 */       AlmacenJInternalFrame.this.nuevoProducto = true;
/* 1001 */       AlmacenJInternalFrame.this.panelDerecha.limpiarPanel();
AlmacenJInternalFrame.this.panelDerecha.campoReferencia.setEditable(true);
/* 1002 */ //      AlmacenJInternalFrame.access$100(AlmacenJInternalFrame.this).campoReferencia.setEditable(true);
/*      */     }
/*      */ 
/*      */     private void eliminar() {
/* 1006 */       int confirma = JOptionPane.showConfirmDialog(this, Mensajes.getString("confirmaEliminar") + "\n" + Mensajes.getString("confirmaEliminar2"), Mensajes.getString("dialogoEliminar"), 0);
/*      */ 
/* 1010 */       if (confirma == 0) {
/* 1011 */         boolean correcto = false;
/* 1012 */         if (AlmacenJInternalFrame.this.articuloMostrado != null) {
/* 1013 */           correcto = new ManejadorProducto(AlmacenJInternalFrame.con).eliminarProducto(AlmacenJInternalFrame.this.articuloMostrado.getId());
/*      */         }
/* 1015 */         if (correcto) {
/* 1016 */           AlmacenJInternalFrame.this.articuloMostrado = null;
/* 1017 */           cambiarModelo();
/* 1018 */           AlmacenJInternalFrame.this.panelDerecha.limpiarPanel();
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   class PanelIzquierda extends JPanel
/*      */   {
/*  461 */     private JButton jButton = null;
/*  462 */     private JScrollPane jScrollPane = null;
/*  463 */     private JList jList = null;
/*  464 */     private JPanel jPanel = null;
/*  465 */     private JButton jButton1 = null;
/*  466 */     private JButton jButton2 = null;
/*  467 */     private JButton jButton3 = null;
/*  468 */     private DefaultListModel modeloLista = null;
/*  469 */     private boolean tipoListado = true;
/*      */ 
/*      */     public PanelIzquierda()
/*      */     {
/*  476 */       initialize();
/*      */     }
/*      */ 
/*      */     private void initialize()
/*      */     {
/*  485 */       setLayout(new BorderLayout());
/*  486 */       setBounds(new Rectangle(0, 0, 180, 400));
/*  487 */       setPreferredSize(new Dimension(180, 400));
/*      */ 
/*  489 */       add(getJButton(), "North");
/*  490 */       add(getJScrollPane(), "Center");
/*  491 */       add(getJPanel(), "South");
/*      */     }
/*      */ 
/*      */     private JButton getJButton()
/*      */     {
/*  500 */       if (this.jButton == null) {
/*  501 */         this.jButton = new JButton();
/*  502 */         this.jButton.setText(Mensajes.getString("familias"));
/*  503 */         this.jButton.setToolTipText("Pulse para alternar entre Proveedores y Familias");
/*  504 */         this.jButton.addActionListener(new ActionListener()
/*      */         {
/*      */           public void actionPerformed(ActionEvent e) {
/*  507 */             if (AlmacenJInternalFrame.PanelIzquierda.this.tipoListado) {
/*  508 */               AlmacenJInternalFrame.PanelIzquierda.this.tipoListado = false;
/*  509 */               AlmacenJInternalFrame.PanelIzquierda.this.jButton.setText(Mensajes.getString("proveedores"));
/*      */             } else {
/*  511 */               AlmacenJInternalFrame.PanelIzquierda.this.tipoListado = true;
/*  512 */               AlmacenJInternalFrame.PanelIzquierda.this.jButton.setText(Mensajes.getString("familias"));
/*      */             }
/*  514 */             AlmacenJInternalFrame.PanelIzquierda.this.jScrollPane.setViewportView(AlmacenJInternalFrame.PanelIzquierda.this.getJList());
/*      */           }
/*      */         });
/*      */       }
/*  518 */       return this.jButton;
/*      */     }
/*      */ 
/*      */     private JScrollPane getJScrollPane()
/*      */     {
/*  527 */       if (this.jScrollPane == null) {
/*  528 */         this.jScrollPane = new JScrollPane();
/*  529 */         this.jScrollPane.setBackground(Color.white);
/*  530 */         this.jScrollPane.setViewportView(getJList());
/*      */       }
/*  532 */       return this.jScrollPane;
/*      */     }
/*      */ 
/*      */     private JList getJList()
/*      */     {
/*  541 */       this.jList = null;
/*  542 */       this.jList = new JList(getModeloLista());
/*  543 */       this.jList.setSelectionMode(1);
/*  544 */       this.jList.setLayoutOrientation(0);
/*  545 */       this.jList.setBackground(Color.white);
/*      */ 
/*  547 */       cambiarModelo();
/*  548 */       this.jList.addListSelectionListener(new ListSelectionListener()
/*      */       {
/*      */         public void valueChanged(ListSelectionEvent e)
/*      */         {
/*  552 */           if ((!e.getValueIsAdjusting()) && (!AlmacenJInternalFrame.PanelIzquierda.this.jList.isSelectionEmpty()))
/*      */           {
/*  554 */             int id = AlmacenJInternalFrame.PanelIzquierda.this.obtenerIdSeleccionado();
/*  555 */             if (id != -1)
/*  556 */               AlmacenJInternalFrame.this.renovarListaCentro(id, AlmacenJInternalFrame.PanelIzquierda.this.tipoListado);
/*      */           }
/*      */         }
/*      */       });
/*  561 */       this.jList.addMouseListener(new MouseAdapter()
/*      */       {
/*      */         public void MouseCliked(MouseEvent evt) {
/*  564 */           if (evt.getClickCount() == 2) {
/*  565 */             int indice = AlmacenJInternalFrame.PanelIzquierda.this.jList.locationToIndex(evt.getPoint());
/*  566 */             AlmacenJInternalFrame.PanelIzquierda.this.jList.setSelectedIndex(indice);
/*  567 */             AlmacenJInternalFrame.PanelIzquierda.this.modificar();
/*      */           }
/*      */         }
/*      */       });
/*  571 */       return this.jList;
/*      */     }
/*      */ 
/*      */     private DefaultListModel getModeloLista() {
/*  575 */       if (this.modeloLista == null) {
/*  576 */         this.modeloLista = new DefaultListModel();
/*      */       }
/*  578 */       return this.modeloLista;
/*      */     }
/*      */ 
/*      */     private JPanel getJPanel()
/*      */     {
/*  587 */       if (this.jPanel == null) {
/*  588 */         this.jPanel = new JPanel();
/*  589 */         this.jPanel.setLayout(null);
/*  590 */         this.jPanel.setPreferredSize(new Dimension(260, 34));
/*  591 */         this.jPanel.add(getJButton1(), null);
/*  592 */         this.jPanel.add(getJButton2(), null);
/*  593 */         this.jPanel.add(getJButton3(), null);
/*      */       }
/*  595 */       return this.jPanel;
/*      */     }
/*      */ 
/*      */     private JButton getJButton1()
/*      */     {
/*  604 */       if (this.jButton1 == null) {
/*  605 */         this.jButton1 = new JButton();
/*  606 */         this.jButton1.setBounds(new Rectangle(5, 3, 35, 29));
/*      */ 
/*  608 */         this.jButton1.setIcon(new ImageIcon(getClass().getResource("/almacen2/iconos/mas.png")));
/*  609 */         this.jButton1.setToolTipText("Pulse para añadir un nuevo proveedor o familia");
/*  610 */         this.jButton1.addActionListener(new ActionListener()
/*      */         {
/*      */           public void actionPerformed(ActionEvent e) {
/*  613 */             AlmacenJInternalFrame.PanelIzquierda.this.anadir();
/*      */           }
/*      */         });
/*      */       }
/*  617 */       return this.jButton1;
/*      */     }
/*      */ 
/*      */     private JButton getJButton2()
/*      */     {
/*  626 */       if (this.jButton2 == null) {
/*  627 */         this.jButton2 = new JButton();
/*  628 */         this.jButton2.setBounds(new Rectangle(45, 3, 35, 29));
/*      */ 
/*  630 */         this.jButton2.setIcon(new ImageIcon(getClass().getResource("/almacen2/iconos/menos.png")));
/*  631 */         this.jButton2.setToolTipText("Pulse para eliminar el proveedor o familia seleccionado");
/*  632 */         this.jButton2.addActionListener(new ActionListener()
/*      */         {
/*      */           public void actionPerformed(ActionEvent e) {
/*  635 */             if (!AlmacenJInternalFrame.PanelIzquierda.this.jList.isSelectionEmpty()) {
/*  636 */               AlmacenJInternalFrame.PanelIzquierda.this.eliminar();
/*      */             }
/*      */           }
/*      */         });
/*      */       }
/*  641 */       return this.jButton2;
/*      */     }
/*      */ 
/*      */     private JButton getJButton3()
/*      */     {
/*  650 */       if (this.jButton3 == null) {
/*  651 */         this.jButton3 = new JButton();
/*  652 */         this.jButton3.setBounds(new Rectangle(85, 3, 35, 29));
/*      */ 
/*  654 */         this.jButton3.setIcon(new ImageIcon(getClass().getResource("/almacen2/iconos/misc.png")));
/*  655 */         this.jButton3.setToolTipText("Pulse para modificar el proveedor o familia seleccionado");
/*  656 */         this.jButton3.addActionListener(new ActionListener()
/*      */         {
/*      */           public void actionPerformed(ActionEvent e) {
/*  659 */             if (!AlmacenJInternalFrame.PanelIzquierda.this.jList.isSelectionEmpty()) {
/*  660 */               AlmacenJInternalFrame.PanelIzquierda.this.modificar();
/*      */             }
/*      */           }
/*      */         });
/*      */       }
/*  665 */       return this.jButton3;
/*      */     }
/*      */ 
/*      */     private void cambiarModelo() {
/*  669 */       this.modeloLista.clear();
/*  670 */       AlmacenJInternalFrame.this.listas.actualizarFamiliasyProveedores(this.tipoListado);
/*  671 */       if (this.tipoListado) {
/*  672 */         for (int x = 0; x < AlmacenJInternalFrame.this.listas.getFamilias().length; x++)
/*  673 */           this.modeloLista.addElement(AlmacenJInternalFrame.this.listas.getFamilias()[x].getNombre());
/*      */       }
/*      */       else
/*  676 */         for (int x = 0; x < AlmacenJInternalFrame.this.listas.getProveedores().length; x++)
/*  677 */           this.modeloLista.addElement(AlmacenJInternalFrame.this.listas.getProveedores()[x].getNombre());
/*      */     }
/*      */ 
/*      */     private void anadir()
/*      */     {
/*  704 */       int nuevoRegistro = new ManejadorFP(AlmacenJInternalFrame.con).nuevoRegistro(this.tipoListado);
/*  705 */       FPObject objeto = new FPObject(nuevoRegistro, "", -1, -1);
/*  706 */       FPObject objetoNuevo = null;
/*  707 */       UserObject usuario = new UserObject(Inicio.p.getUsuario(), Inicio.p.getPassword(), Inicio.p.getDireccionIP(), Inicio.p.getEmpresa());
/*  708 */       if (this.tipoListado)
/*  709 */         objetoNuevo = FamiliasJDialog.obtenerElemento(Inicio.frame, true, usuario, objeto);
/*      */       else {
/*  711 */         objetoNuevo = ProveedoresJDialog.obtenerElemento(Inicio.frame, true, usuario, objeto);
/*      */       }
/*  713 */       if (objetoNuevo != null) {
/*  714 */         if (new ManejadorFP(AlmacenJInternalFrame.con).existeId(objetoNuevo.getId(), this.tipoListado)) {
/*  715 */           String cabecera = this.tipoListado ? Mensajes.getString("existeF") : Mensajes.getString("existeP");
/*  716 */           JOptionPane.showMessageDialog(AlmacenJInternalFrame.this, cabecera + " " + Mensajes.getString("almacen") + " " + Mensajes.getString("existeFP"), "Error", 0);
/*      */ 
/*  720 */           return;
/*      */         }
/*  722 */         if (!objetoNuevo.equals(objeto)) {
/*  723 */           boolean correcto = new ManejadorFP(AlmacenJInternalFrame.con).crear(objetoNuevo, this.tipoListado);
/*  724 */           if (correcto) {
/*  725 */             AlmacenJInternalFrame.this.listas.actualizarFamiliasyProveedores(this.tipoListado);
/*  726 */             cambiarModelo();
/*  727 */             AlmacenJInternalFrame.this.llenarCombo(this.tipoListado);
/*  728 */             Inicio.frame.renovarModeloProveedoresPedidos();
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*      */     private void modificar() {
/*  735 */       FPObject objeto = null;
/*  736 */       UserObject usuario = new UserObject(Inicio.p.getUsuario(), Inicio.p.getPassword(), Inicio.p.getDireccionIP(), Inicio.p.getEmpresa());
/*  737 */       if (this.tipoListado)
/*  738 */         objeto = AlmacenJInternalFrame.this.listas.getFamilias()[this.jList.getSelectedIndex()];
/*      */       else {
/*  740 */         objeto = AlmacenJInternalFrame.this.listas.getProveedores()[this.jList.getSelectedIndex()];
/*      */       }
/*  742 */       if (objeto != null) {
/*  743 */         FPObject objetoModificado = null;
/*  744 */         if (this.tipoListado)
/*  745 */           objetoModificado = FamiliasJDialog.obtenerElemento(Inicio.frame, true, usuario, objeto);
/*      */         else {
/*  747 */           objetoModificado = ProveedoresJDialog.obtenerElemento(Inicio.frame, true, usuario, objeto);
/*      */         }
/*  749 */         if ((objetoModificado != null) && (!objetoModificado.equals(objeto))) {
/*  750 */           boolean correcto = new ManejadorFP(AlmacenJInternalFrame.con).modificar(objetoModificado, this.tipoListado);
/*  751 */           if (correcto) {
/*  752 */             AlmacenJInternalFrame.this.listas.actualizarFamiliasyProveedores(this.tipoListado);
/*  753 */             cambiarModelo();
/*  754 */             AlmacenJInternalFrame.this.llenarCombo(this.tipoListado);
/*  755 */             Inicio.frame.renovarModeloProveedoresPedidos();
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*      */     private void eliminar() {
/*  762 */       int confirma = JOptionPane.showConfirmDialog(this, Mensajes.getString("confirmaEliminar") + "\n" + Mensajes.getString("confirmaEliminar2"), Mensajes.getString("dialogoEliminar"), 0);
/*      */ 
/*  766 */       if (confirma == 0) {
/*  767 */         boolean correcto = false;
/*  768 */         if (this.tipoListado)
/*  769 */           correcto = new ManejadorFP(AlmacenJInternalFrame.con).eliminar(AlmacenJInternalFrame.this.listas.getFamilias()[this.jList.getSelectedIndex()], this.tipoListado);
/*      */         else {
/*  771 */           correcto = new ManejadorFP(AlmacenJInternalFrame.con).eliminar(AlmacenJInternalFrame.this.listas.getProveedores()[this.jList.getSelectedIndex()], this.tipoListado);
/*      */         }
/*  773 */         if (correcto) {
/*  774 */           AlmacenJInternalFrame.this.listas.actualizarFamiliasyProveedores(this.tipoListado);
/*  775 */           cambiarModelo();
/*  776 */           AlmacenJInternalFrame.this.llenarCombo(this.tipoListado);
/*  777 */           Inicio.frame.renovarModeloProveedoresPedidos();
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*      */     private int obtenerIdSeleccionado() {
/*  783 */       int id = -1;
/*  784 */       int index = this.jList.getSelectedIndex();
/*  785 */       if (index != -1) {
/*  786 */         if (this.tipoListado)
/*  787 */           id = AlmacenJInternalFrame.this.listas.getFamilias()[index].getId();
/*      */         else {
/*  789 */           id = AlmacenJInternalFrame.this.listas.getProveedores()[index].getId();
/*      */         }
/*      */       }
/*  792 */       return id;
/*      */     }
/*      */   }
/*      */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.gui.AlmacenJInternalFrame
 * JD-Core Version:    0.6.2
 */