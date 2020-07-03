/*      */ package almacen2.gui.pedidos;
/*      */ 
/*      */ import almacen2.data.FPObject;
/*      */ import almacen2.data.ManejadorAlmacenInterno;
/*      */ import almacen2.data.ManejadorFP;
/*      */ import almacen2.data.ManejadorListasInicio;
/*      */ import almacen2.data.ManejadorPIO;
/*      */ import almacen2.data.ManejadorProducto;
/*      */ import almacen2.data.MySQLConection;
/*      */ import almacen2.data.PIOObject;
/*      */ import almacen2.data.ProductObject;
/*      */ import almacen2.data.pedidos.LineaPedidoObject;
/*      */ import almacen2.data.pedidos.PedidoObject;
/*      */ import almacen2.data.pedidos.PedidoTableModel;
/*      */ import almacen2.data.pedidos.Pedidos;
/*      */ import almacen2.data.pedidos.Productos;
/*      */ import almacen2.data.pedidos.datosProducto;
/*      */ import almacen2.data.pedidos.productoPedido;
/*      */ import contaes.Inicio;
/*      */ import contaes.MarcoInicio;
/*      */ import contaes.Puente;
/*      */ import contaes.auxiliarTablas.BooleanColorRenderer;
/*      */ import contaes.auxiliarTablas.DerechaColorRenderer;
/*      */ import contaes.auxiliarTablas.GeneralColorRenderer;
/*      */ import contaes.auxiliarTablas.ImporteColorRenderer;
/*      */ import contaes.calendario.ICalendarTextField;
/*      */ import contaes.dialogosAuxiliares.MostrarListas;
/*      */ import contaes.manejoDatos.ManejoAgenda;
/*      */ import contaes.manejoDatos.ManejoFormasPago;
/*      */ import contaes.manejoDatos.ManejoSubcuentas;
/*      */ import contaes.manejoDatos.ManejoTiposIVA;
/*      */ import contaes.manejoDatos.TipoFormaPago;
/*      */ import contaes.manejoDatos.TipoIVA;
/*      */ import contaes.manejoDatos.TipoSubcuenta;
/*      */ import contaes.manejoDatos.auxiliar.AddExtension;
/*      */ import contaes.manejoDatos.auxiliar.GrabarFichero;
/*      */ import facturacion.control.AlmacenControl;
/*      */ import facturacion.model.Factura;
/*      */ import facturacion.model.LineaFactura;
/*      */ import facturacion.model.Producto;
/*      */ import internationalization.Mensajes;
/*      */ import java.awt.BorderLayout;
/*      */ import java.awt.Color;
/*      */ import java.awt.Container;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.Point;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.Toolkit;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.ActionListener;
/*      */ import java.beans.XMLDecoder;
/*      */ import java.beans.XMLEncoder;
/*      */ import java.io.BufferedInputStream;
/*      */ import java.io.BufferedOutputStream;
/*      */ import java.io.File;
/*      */ import java.io.FileInputStream;
/*      */ import java.io.FileNotFoundException;
/*      */ import java.io.FileOutputStream;
/*      */ import java.math.BigDecimal;
/*      */ import java.text.DecimalFormat;
/*      */ import java.text.DecimalFormatSymbols;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Date;
/*      */ import java.util.Formatter;
/*      */ import java.util.GregorianCalendar;
import java.util.List;
/*      */ import javax.swing.DefaultListModel;
/*      */ import javax.swing.ImageIcon;
/*      */ import javax.swing.JButton;
/*      */ import javax.swing.JComboBox;
/*      */ import javax.swing.JDialog;
/*      */ import javax.swing.JFileChooser;
/*      */ import javax.swing.JFormattedTextField;
/*      */ import javax.swing.JInternalFrame;
/*      */ import javax.swing.JLabel;
/*      */ import javax.swing.JList;
/*      */ import javax.swing.JOptionPane;
/*      */ import javax.swing.JPanel;
/*      */ import javax.swing.JScrollPane;
/*      */ import javax.swing.JTable;
/*      */ import javax.swing.JTextField;
/*      */ import javax.swing.ListSelectionModel;
/*      */ import javax.swing.event.InternalFrameAdapter;
/*      */ import javax.swing.event.InternalFrameEvent;
/*      */ import javax.swing.table.TableColumn;
/*      */ import javax.swing.table.TableColumnModel;
/*      */ import org.jdesktop.swingx.MultiSplitLayout;
/*      */ import org.jdesktop.swingx.MultiSplitLayout.Node;
/*      */ import org.jdesktop.swingx.MultiSplitPane;
/*      */ 
/*      */ public class PedidosJInternalFrame extends JInternalFrame
/*      */ {
/*   93 */   private MultiSplitPane panelMultiple = null;
/*   94 */   private PanelDerecha panelDerecha = null;
/*   95 */   private PanelIzquierda panelIzquierda = null;
/*   96 */   BorderLayout bLayout = new BorderLayout();
/*      */   private MySQLConection con;
/*      */   private ManejadorListasInicio listas;
/*   99 */   private int id_pedido = -1;
/*  100 */   private boolean compras = true;
/*      */ 
/*      */   public PedidosJInternalFrame(String titulo)
/*      */   {
/*  106 */     this(titulo, -1);
/*      */   }
/*      */ 
/*      */   public PedidosJInternalFrame(String titulo, int idPedido) {
/*  110 */     super(titulo, true, true, true, true);
/*  111 */     setDefaultCloseOperation(2);
/*  112 */     initialize();
/*  113 */     this.panelDerecha.colocarPedido(idPedido);
/*      */   }
/*      */ 
/*      */   private void initialize()
/*      */   {
/*  122 */     this.con = new MySQLConection();
/*  123 */     this.listas = new ManejadorListasInicio(this.con);
/*  124 */     addInternalFrameListener(new InternalFrameAdapter()
/*      */     {
/*      */       public void internalFrameClosed(InternalFrameEvent e)
/*      */       {
/*  128 */         Inicio.frame.eliminarMarcoMenu(Mensajes.getString("pedidosProveedores"));
/*  129 */         super.internalFrameClosed(e);
/*      */       }
/*      */ 
/*      */       public void internalFrameClosing(InternalFrameEvent e)
/*      */       {
/*      */         try
/*      */         {
/*  136 */           XMLEncoder encod = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("configdir/colocaPanel2.ini")));
/*      */ 
/*  138 */           MultiSplitLayout.Node model = PedidosJInternalFrame.this.panelMultiple.getMultiSplitLayout().getModel();
/*  139 */           encod.writeObject(model);
/*  140 */           encod.close();
/*      */         } catch (FileNotFoundException e1) {
/*  142 */           e1.printStackTrace();
/*      */         }
/*  144 */         if (PedidosJInternalFrame.this.con != null) {
/*  145 */           PedidosJInternalFrame.this.con.cierraConexion();
/*      */         }
/*  147 */         super.internalFrameClosing(e);
/*      */       }
/*      */     });
/*  150 */     setBounds(20, 20, 750, 440);
/*  151 */     setFrameIcon(new ImageIcon(getClass().getResource("/contaes/iconos/facturacion18.png")));
/*  152 */     getContentPane().setLayout(this.bLayout);
/*  153 */     getContentPane().add(crearPanelMultiple(), "Center");
/*      */   }
/*      */ 
/*      */   private void mostrarDialogo(JDialog dlg) {
/*  157 */     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/*  158 */     Dimension frameSize = dlg.getSize();
/*  159 */     if (frameSize.height > screenSize.height) {
/*  160 */       frameSize.height = screenSize.height;
/*      */     }
/*  162 */     if (frameSize.width > screenSize.width) {
/*  163 */       frameSize.width = screenSize.width;
/*      */     }
/*  165 */     dlg.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
/*      */ 
/*  168 */     dlg.setVisible(true);
/*      */   }
/*      */ 
/*      */   private MultiSplitPane crearPanelMultiple() {
/*  172 */     if (this.panelMultiple == null) {
/*  173 */       String layoutDef = "(ROW (LEAF name=izquierda weight=0.25) (LEAF name=derecha weight=0.75))";
/*      */ 
/*  175 */       this.panelMultiple = new MultiSplitPane();
/*      */       try
/*      */       {
/*  178 */         XMLDecoder d = new XMLDecoder(new BufferedInputStream(new FileInputStream("configdir/colocaPanel2.ini")));
/*      */ 
/*  181 */         MultiSplitLayout.Node model = (MultiSplitLayout.Node)d.readObject();
/*  182 */         this.panelMultiple.getMultiSplitLayout().setModel(model);
/*  183 */         this.panelMultiple.getMultiSplitLayout().setFloatingDividers(false);
/*  184 */         d.close();
/*      */       } catch (Exception exc) {
/*  186 */         MultiSplitLayout.Node model = MultiSplitLayout.parseModel(layoutDef);
/*  187 */         this.panelMultiple.getMultiSplitLayout().setModel(model);
/*      */       }
/*  189 */       this.panelMultiple.setDividerSize(1);
/*  190 */       this.panelMultiple.add(crearPanelIzquierda(), "izquierda");
/*  191 */       this.panelMultiple.add(crearPanelDerecha(), "derecha");
/*      */     }
/*      */ 
/*  194 */     return this.panelMultiple;
/*      */   }
/*      */ 
/*      */   private PanelIzquierda crearPanelIzquierda() {
/*  198 */     if (this.panelIzquierda == null) {
/*  199 */       this.panelIzquierda = new PanelIzquierda();
/*      */     }
/*      */ 
/*  202 */     return this.panelIzquierda;
/*      */   }
/*      */ 
/*      */   private PanelDerecha crearPanelDerecha() {
/*  206 */     if (this.panelDerecha == null) {
/*  207 */       this.panelDerecha = new PanelDerecha();
/*      */     }
/*      */ 
/*  210 */     return this.panelDerecha;
/*      */   }
/*      */ 
/*      */   public void renovarModeloProveedor() {
/*  214 */     this.panelDerecha.renovarModeloProveedor();
/*  215 */     this.panelIzquierda.renovarModeloProveedor();
/*      */   }
/*      */ 
/*      */   class PanelIzquierda extends JPanel
/*      */   {
/* 1147 */     private JComboBox comboProveedores = null;
/* 1148 */     private JScrollPane jScrollPane1 = null;
/* 1149 */     private JList jList = null;
/* 1150 */     private DefaultListModel modeloLista = null;
/* 1151 */     private JButton botonBuscar = null;
/*      */ 
/*      */     public PanelIzquierda()
/*      */     {
/* 1158 */       initialize();
/*      */     }
/*      */ 
/*      */     private void initialize()
/*      */     {
/* 1167 */       setLayout(new BorderLayout());
/* 1168 */       add(getComboProveedores(), "North");
/* 1169 */       add(getJScrollPane1(), "Center");
/* 1170 */       add(getBotonBuscar(), "South");
/*      */     }
/*      */ 
/*      */     private JButton getBotonBuscar() {
/* 1174 */       if (this.botonBuscar == null) {
/* 1175 */         this.botonBuscar = new JButton();
/* 1176 */         this.botonBuscar.setPreferredSize(new Dimension(75, 25));
/* 1177 */         this.botonBuscar.setText(Mensajes.getString("buscar") + " " + Mensajes.getString("producto"));
/* 1178 */         this.botonBuscar.setHorizontalTextPosition(2);
/* 1179 */         this.botonBuscar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/find.png")));
/* 1180 */         this.botonBuscar.addActionListener(new ActionListener()
/*      */         {
/*      */           public void actionPerformed(ActionEvent e) {
/* 1183 */             String referencia = almacen2.gui.Busqueda.obtenerObjeto(Inicio.frame, PedidosJInternalFrame.this.con);
/* 1184 */             if (referencia != null) {
/* 1185 */              // PedidosJInternalFrame.PanelDerecha.access$1800(PedidosJInternalFrame.this.panelDerecha, referencia);
/*      */             new PedidosJInternalFrame(panelDerecha.getSelectedReferencia());
 }
/*      */           }
/*      */         });
/*      */       }
/* 1190 */       return this.botonBuscar;
/*      */     }
/*      */ 
/*      */     private JComboBox getComboProveedores()
/*      */     {
/* 1199 */       if (this.comboProveedores == null) {
/* 1200 */         this.comboProveedores = new JComboBox();
/* 1201 */         this.comboProveedores.addActionListener(new ActionListener()
/*      */         {
/*      */           public void actionPerformed(ActionEvent e) {
/* 1204 */             int indice = PedidosJInternalFrame.PanelIzquierda.this.comboProveedores.getSelectedIndex();
/* 1205 */             if (indice > 0)
/* 1206 */               PedidosJInternalFrame.PanelIzquierda.this.llenarLista(PedidosJInternalFrame.this.listas.getProveedores()[(indice - 1)].getId());
/*      */           }
/*      */         });
/* 1210 */         llenarComboProveedores();
/*      */       }
/* 1212 */       return this.comboProveedores;
/*      */     }
/*      */ 
/*      */     private JScrollPane getJScrollPane1()
/*      */     {
/* 1221 */       if (this.jScrollPane1 == null) {
/* 1222 */         this.jScrollPane1 = new JScrollPane();
/* 1223 */         this.jScrollPane1.setViewportView(getJList());
/*      */       }
/* 1225 */       return this.jScrollPane1;
/*      */     }
/*      */ 
/*      */     private JList getJList()
/*      */     {
/* 1234 */       if (this.jList == null) {
/* 1235 */         this.jList = new JList(getModeloLista());
/* 1236 */         this.jList.setSelectionMode(1);
/* 1237 */         this.jList.setLayoutOrientation(0);
/* 1238 */         this.jList.setBackground(Color.white);
/* 1239 */         this.jList.setDragEnabled(true);
/*      */       }
/* 1241 */       return this.jList;
/*      */     }
/*      */ 
/*      */     private DefaultListModel getModeloLista() {
/* 1245 */       if (this.modeloLista == null) {
/* 1246 */         this.modeloLista = new DefaultListModel();
/*      */       }
/* 1248 */       return this.modeloLista;
/*      */     }
/*      */ 
/*      */     private void llenarLista(int idProveedor) {
/* 1252 */       PedidosJInternalFrame.this.listas.actualizarReferencias(idProveedor, false);
/* 1253 */       if (PedidosJInternalFrame.this.listas.getReferencias() != null) {
/* 1254 */         this.modeloLista.clear();
/* 1255 */         for (String refer : PedidosJInternalFrame.this.listas.getReferencias())
/* 1256 */           this.modeloLista.addElement(refer);
/*      */       }
/*      */     }
/*      */ 
/*      */     private void llenarComboProveedores()
/*      */     {
/* 1262 */       if (this.comboProveedores != null) {
/* 1263 */         this.comboProveedores.removeAllItems();
/* 1264 */         this.comboProveedores.addItem(Mensajes.getString("proveedores"));
/* 1265 */         for (int x = 0; x < PedidosJInternalFrame.this.listas.getProveedores().length; x++)
/* 1266 */           this.comboProveedores.addItem(PedidosJInternalFrame.this.listas.getProveedores()[x].getNombre());
/*      */       }
/*      */     }
/*      */ 
/*      */     public void renovarModeloProveedor()
/*      */     {
/* 1272 */       PedidosJInternalFrame.this.listas.actualizarFamiliasyProveedores(false);
/* 1273 */       if (this.comboProveedores != null) {
/* 1274 */         this.comboProveedores.removeAllItems();
/* 1275 */         this.comboProveedores.addItem(Mensajes.getString("proveedores"));
/* 1276 */         for (int x = 0; x < PedidosJInternalFrame.this.listas.getProveedores().length; x++)
/* 1277 */           this.comboProveedores.addItem(PedidosJInternalFrame.this.listas.getProveedores()[x].getNombre());
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   class PanelDerecha extends JPanel
/*      */   {
/*  223 */     private JPanel jPanel = null;
/*  224 */     private JPanel jPanel1 = null;
/*  225 */     private JScrollPane jScrollPane = null;
/*  226 */     private JTable tabla = null;
/*  227 */     private JLabel jLabel = null;
/*  228 */     private JLabel jLabel1 = null;
/*  229 */     private JLabel jLabel2 = null;
/*  230 */     private JTextField cCodigo = null;
/*  231 */     private ICalendarTextField cFecha = null;
/*  232 */     private JComboBox cProveedor = null;
/*  233 */     private JButton bLimpiar = null;
/*  234 */     private JButton bNuevo = null;
/*  235 */     private JButton bBorrar = null;
/*  236 */     private JButton bAnadir = null;
/*  237 */     private JButton bBuscar = null;
/*  238 */     private JButton bEliminar = null;
/*  239 */     private JButton bRecibido = null;
/*  240 */     private JButton bGuardar = null;
/*  241 */     private JButton bMinimos = null;
/*  242 */     private JButton bFactura = null;
/*  243 */     private JLabel jLabel3 = null;
/*  244 */     private JTextField cPiezas = null;
/*  245 */     private JLabel jLabel4 = null;
/*  246 */     private JFormattedTextField cCoste = null;
/*  247 */     private PedidoTableModel modelo = null;
/*      */     private JButton bBuscarC;
/*  249 */     private ArrayList<LineaPedidoObject> listaPedido = new ArrayList();
/*      */     private DecimalFormat fn;
/*  251 */     private int sumaPiezas = 0;
/*  252 */     private double sumaCostes = 0.0D;
/*      */ 
/*      */     public PanelDerecha()
/*      */     {
/*  259 */       initialize();
/*      */     }
/*      */ 
/*      */     private void initialize()
/*      */     {
/*  268 */       setLayout(new BorderLayout());
/*  269 */       add(getJPanel(), "North");
/*  270 */       add(getJPanel1(), "South");
/*  271 */       add(getJScrollPane(), "Center");
/*      */ 
/*  273 */       DecimalFormatSymbols formato = new DecimalFormatSymbols();
/*  274 */       formato.setDecimalSeparator(',');
/*  275 */       formato.setPerMill('.');
/*  276 */       this.fn = new DecimalFormat("#,###,##0.00", formato);
/*      */     }
/*      */ 
/*      */     private JPanel getJPanel()
/*      */     {
/*  285 */       if (this.jPanel == null) {
/*  286 */         this.jPanel = new JPanel();
/*  287 */         this.jPanel.setLayout(null);
/*  288 */         this.jPanel.setBackground(Color.white);
/*  289 */         this.jPanel.setPreferredSize(new Dimension(500, 100));
/*  290 */         this.jLabel2 = new JLabel();
/*  291 */         this.jLabel2.setBounds(new Rectangle(10, 62, 65, 16));
/*  292 */         this.jLabel2.setText(Mensajes.getString("proveedor"));
/*  293 */         this.jLabel1 = new JLabel();
/*  294 */         this.jLabel1.setBounds(new Rectangle(10, 35, 37, 16));
/*  295 */         this.jLabel1.setText(Mensajes.getString("fecha"));
/*  296 */         this.jLabel = new JLabel();
/*  297 */         this.jLabel.setBounds(new Rectangle(10, 10, 55, 16));
/*  298 */         this.jLabel.setText(Mensajes.getString("codigo"));
/*  299 */         this.jPanel.add(this.jLabel, null);
/*  300 */         this.jPanel.add(this.jLabel1, null);
/*  301 */         this.jPanel.add(this.jLabel2, null);
/*  302 */         this.jPanel.add(getCCodigo(), null);
/*  303 */         this.jPanel.add(getCFecha(), null);
/*  304 */         this.jPanel.add(getCProveedor(), null);
/*  305 */         this.jPanel.add(getBBuscarC(), null);
/*  306 */         this.jPanel.add(getBLimpiar(), null);
/*  307 */         this.jPanel.add(getBNuevo(), null);
/*  308 */         this.jPanel.add(getBBorrar(), null);
/*  309 */         this.jPanel.add(getBBuscar(), null);
/*  310 */         this.jPanel.add(getBFactura(), null);
/*      */       }
/*  312 */       return this.jPanel;
/*      */     }
/*      */ 
/*      */     private JPanel getJPanel1()
/*      */     {
/*  321 */       if (this.jPanel1 == null) {
/*  322 */         this.jPanel1 = new JPanel();
/*  323 */         this.jPanel1.setBackground(Color.white);
/*  324 */         this.jPanel1.setLayout(null);
/*  325 */         this.jPanel1.setPreferredSize(new Dimension(400, 80));
/*  326 */         this.jLabel4 = new JLabel();
/*  327 */         this.jLabel4.setBounds(new Rectangle(230, 35, 45, 16));
/*  328 */         this.jLabel4.setText(Mensajes.getString("coste"));
/*  329 */         this.jLabel3 = new JLabel();
/*  330 */         this.jLabel3.setBounds(new Rectangle(230, 10, 45, 16));
/*  331 */         this.jLabel3.setText(Mensajes.getString("piezas"));
/*  332 */         this.jPanel1.add(getBAnadir(), null);
/*  333 */         this.jPanel1.add(getBEliminar(), null);
/*  334 */         this.jPanel1.add(getBRecibido(), null);
/*  335 */         this.jPanel1.add(getBGuardar(), null);
/*  336 */         this.jPanel1.add(getBMinimos(), null);
/*  337 */         this.jPanel1.add(this.jLabel3, null);
/*  338 */         this.jPanel1.add(getCPiezas(), null);
/*  339 */         this.jPanel1.add(this.jLabel4, null);
/*  340 */         this.jPanel1.add(getCCoste(), null);
/*      */       }
/*  342 */       return this.jPanel1;
/*      */     }
/*      */ 
/*      */     private JScrollPane getJScrollPane()
/*      */     {
/*  351 */       if (this.jScrollPane == null) {
/*  352 */         this.jScrollPane = new JScrollPane();
/*  353 */         this.jScrollPane.setBackground(Color.white);
/*  354 */         this.jScrollPane.setViewportView(getTabla());
/*      */       }
/*  356 */       return this.jScrollPane;
/*      */     }
/*      */ 
/*      */     private JTable getTabla()
/*      */     {
/*  365 */       if (this.tabla == null) {
/*  366 */         this.tabla = new JTable(getModelo());
/*  367 */         this.tabla.setBackground(Color.white);
/*  368 */         int anchoTabla = 560;
/*  369 */         TableColumn columna = this.tabla.getColumnModel().getColumn(0);
/*  370 */         columna.setPreferredWidth((int)(anchoTabla * 0.05D));
/*  371 */         columna = this.tabla.getColumnModel().getColumn(1);
/*  372 */         columna.setPreferredWidth((int)(anchoTabla * 0.17D));
/*  373 */         columna = this.tabla.getColumnModel().getColumn(2);
/*  374 */         columna.setPreferredWidth((int)(anchoTabla * 0.47D));
/*  375 */         columna = this.tabla.getColumnModel().getColumn(3);
/*  376 */         columna.setPreferredWidth((int)(anchoTabla * 0.05D));
/*  377 */         columna.setCellRenderer(new DerechaColorRenderer());
/*  378 */         columna = this.tabla.getColumnModel().getColumn(4);
/*  379 */         columna.setPreferredWidth((int)(anchoTabla * 0.14D));
/*  380 */         columna.setCellRenderer(new ImporteColorRenderer());
/*  381 */         columna = this.tabla.getColumnModel().getColumn(5);
/*  382 */         columna.setPreferredWidth((int)(anchoTabla * 0.12D));
/*  383 */         columna.setCellRenderer(new BooleanColorRenderer());
/*  384 */         this.tabla.setDefaultRenderer(Object.class, new GeneralColorRenderer());
/*      */       }
/*  386 */       return this.tabla;
/*      */     }
/*      */ 
/*      */     private PedidoTableModel getModelo() {
/*  390 */       if (this.modelo == null) {
/*  391 */         this.modelo = new PedidoTableModel(this.listaPedido, PedidosJInternalFrame.this.compras);
/*      */       }
/*  393 */       return this.modelo;
/*      */     }
/*      */ 
/*      */     private JTextField getCCodigo()
/*      */     {
/*  402 */       if (this.cCodigo == null) {
/*  403 */         this.cCodigo = new JTextField();
/*  404 */         this.cCodigo.setBounds(new Rectangle(90, 5, 140, 19));
/*  405 */         this.cCodigo.addActionListener(new ActionListener()
/*      */         {
/*      */           public void actionPerformed(ActionEvent e) {
/*  408 */             int id = new Pedidos(PedidosJInternalFrame.this.con).obtenerID(PedidosJInternalFrame.PanelDerecha.this.cCodigo.getText(), PedidosJInternalFrame.this.compras);
/*  409 */             if (id != -1) {
/*  410 */               int mostrar = JOptionPane.showConfirmDialog(Inicio.frame, Mensajes.getString("mostrarPedido") + "\n" + Mensajes.getString("mostrarPedido2"), Mensajes.getString("confirma"), 0);
/*      */ 
/*  414 */               if (mostrar == 0) {
/*  415 */                 PedidosJInternalFrame.PanelDerecha.this.colocarPedido(id);
/*      */               }
/*      */             }
/*      */           }
/*      */         });
/*      */       }
/*  421 */       return this.cCodigo;
/*      */     }
/*      */ 
/*      */     private ICalendarTextField getCFecha()
/*      */     {
/*  430 */       if (this.cFecha == null) {
/*  431 */         this.cFecha = new ICalendarTextField();
/*  432 */         this.cFecha.setBounds(new Rectangle(90, 30, 140, 23));
/*  433 */         this.cFecha.setComponente(this.cProveedor);
/*      */       }
/*  435 */       return this.cFecha;
/*      */     }
/*      */ 
/*      */     private JComboBox getCProveedor()
/*      */     {
/*  444 */       if (this.cProveedor == null) {
/*  445 */         this.cProveedor = new JComboBox();
/*  446 */         this.cProveedor.setBounds(new Rectangle(90, 60, 140, 23));
/*  447 */         for (int x = 0; x < PedidosJInternalFrame.this.listas.getProveedores().length; x++) {
/*  448 */           this.cProveedor.addItem(PedidosJInternalFrame.this.listas.getProveedores()[x].getNombre());
/*      */         }
/*  450 */         this.cProveedor.addActionListener(new ActionListener()
/*      */         {
/*      */           public void actionPerformed(ActionEvent e) {
/*  453 */             int indice = PedidosJInternalFrame.PanelDerecha.this.cProveedor.getSelectedIndex();
/*  454 */           //  PedidosJInternalFrame.access$700(PedidosJInternalFrame.this).comboProveedores.setSelectedIndex(indice + 1);
/*      */            panelIzquierda.comboProveedores.setSelectedIndex(indice + 1);
                            }
/*      */         });
/*      */       }
/*  458 */       return this.cProveedor;
/*      */     }
/*      */ 
/*      */     public void renovarModeloProveedor() {
/*  462 */       PedidosJInternalFrame.this.listas.actualizarFamiliasyProveedores(false);
/*  463 */       this.cProveedor.removeAllItems();
/*  464 */       for (int x = 0; x < PedidosJInternalFrame.this.listas.getProveedores().length; x++)
/*  465 */         this.cProveedor.addItem(PedidosJInternalFrame.this.listas.getProveedores()[x].getNombre());
/*      */     }
/*      */ 
/*      */     private JButton getBBuscarC()
/*      */     {
/*  470 */       if (this.bBuscarC == null) {
/*  471 */         this.bBuscarC = new JButton();
/*  472 */         this.bBuscarC.setBounds(new Rectangle(235, 58, 25, 25));
/*  473 */         this.bBuscarC.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/find.png")));
/*  474 */         this.bBuscarC.addActionListener(new ActionListener()
/*      */         {
/*      */           public void actionPerformed(ActionEvent e) {
/*  477 */             PedidosJInternalFrame.PanelDerecha.this.buscarProveedor();
/*      */           }
/*      */         });
/*      */       }
/*  481 */       return this.bBuscarC;
/*      */     }
/*      */ 
/*      */     private void buscarProveedor() {
/*  485 */       ArrayList listaP = new ArrayList();
/*  486 */       for (int x = 0; x < PedidosJInternalFrame.this.listas.getProveedores().length; x++) {
/*  487 */         listaP.add(PedidosJInternalFrame.this.listas.getProveedores()[x].getNombre());
/*      */       }
/*  489 */       MostrarListas dlg2 = new MostrarListas(Inicio.frame, Mensajes.getString("cuentas"), true, listaP);
/*  490 */       Dimension dlgSize = dlg2.getPreferredSize();
/*  491 */       Dimension frmSize = Inicio.frame.getSize();
/*  492 */       Point loc = getLocation();
/*  493 */       dlg2.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
/*      */ 
/*  495 */       dlg2.setVisible(true);
/*  496 */       if (!dlg2.Seleccion().equals("")) {
/*  497 */         String proveedor = dlg2.Seleccion();
/*  498 */         this.cProveedor.setSelectedItem(proveedor);
/*      */       }
/*      */     }
/*      */ 
/*      */     private JButton getBLimpiar()
/*      */     {
/*  508 */       if (this.bLimpiar == null) {
/*  509 */         this.bLimpiar = new JButton();
/*  510 */         this.bLimpiar.setBounds(new Rectangle(300, 5, 105, 25));
/*  511 */         this.bLimpiar.setHorizontalTextPosition(2);
/*  512 */         this.bLimpiar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/cancel.png")));
/*  513 */         this.bLimpiar.setText(Mensajes.getString("limpiar"));
/*  514 */         this.bLimpiar.addActionListener(new ActionListener()
/*      */         {
/*      */           public void actionPerformed(ActionEvent e) {
/*  517 */             PedidosJInternalFrame.PanelDerecha.this.limpiarPanel();
/*      */           }
/*      */         });
/*      */       }
/*  521 */       return this.bLimpiar;
/*      */     }
/*      */ 
/*      */     private void limpiarPanel() {
/*  525 */       this.cCodigo.setText("");
/*  526 */       this.cFecha.setDate(new Date());
/*  527 */       this.cProveedor.setSelectedIndex(0);
/*  528 */       this.cCoste.setText("0");
/*  529 */       this.cPiezas.setText("0");
/*  530 */       this.listaPedido.clear();
/*  531 */       this.modelo.fireTableDataChanged();
/*  532 */       this.tabla.repaint();
/*  533 */       PedidosJInternalFrame.this.id_pedido = -1;
/*  534 */       this.sumaPiezas = 0;
/*  535 */       this.sumaCostes = 0.0D;
/*  536 */       this.bNuevo.setText(Mensajes.getString("nuevo"));
/*      */     }
/*      */ 
/*      */     private JButton getBBuscar()
/*      */     {
/*  545 */       if (this.bBuscar == null) {
/*  546 */         this.bBuscar = new JButton();
/*  547 */         this.bBuscar.setBounds(new Rectangle(415, 60, 90, 25));
/*  548 */         this.bBuscar.setHorizontalTextPosition(2);
/*  549 */         this.bBuscar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/find.png")));
/*  550 */         this.bBuscar.setText(Mensajes.getString("buscar"));
/*  551 */         this.bBuscar.addActionListener(new ActionListener()
/*      */         {
/*      */           public void actionPerformed(ActionEvent e) {
/*  554 */             PedidosJInternalFrame.PanelDerecha.this.buscarPedido();
/*      */           }
/*      */         });
/*      */       }
/*  558 */       return this.bBuscar;
/*      */     }
/*      */ 
/*      */     private JButton getBFactura() {
/*  562 */       if (this.bFactura == null) {
/*  563 */         this.bFactura = new JButton();
/*  564 */         this.bFactura.setBounds(new Rectangle(415, 20, 120, 25));
/*  565 */         this.bFactura.setHorizontalTextPosition(2);
/*  566 */         this.bFactura.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/facturacion18.png")));
/*  567 */         this.bFactura.setText(Mensajes.getString("crearFactura"));
/*  568 */         this.bFactura.addActionListener(new ActionListener()
/*      */         {
/*      */           public void actionPerformed(ActionEvent e) {
/*  571 */             PedidosJInternalFrame.PanelDerecha.this.pasarAFactura();
/*      */           }
/*      */         });
/*      */       }
/*  575 */       return this.bFactura;
/*      */     }
/*      */ 
/*      */     private void buscarPedido() {
/*  579 */       PedidoObject pedido = Busqueda.obtenerObjeto(Inicio.frame, PedidosJInternalFrame.this.con, PedidosJInternalFrame.this.compras);
/*  580 */       if (pedido != null)
/*  581 */         colocarPedido(pedido);
/*      */     }
/*      */ 
/*      */     private void colocarPedido(PedidoObject pedido)
/*      */     {
/*  586 */       if (pedido != null) {
/*  587 */         limpiarPanel();
/*  588 */         PedidosJInternalFrame.this.id_pedido = new Pedidos(PedidosJInternalFrame.this.con).obtenerID(pedido.getCodigo(), pedido.getProveedor(), pedido.getFecha(), PedidosJInternalFrame.this.compras);
/*  589 */         this.cCodigo.setText(pedido.getCodigo());
/*  590 */         int year = Integer.parseInt(pedido.getFecha().substring(0, 4));
/*  591 */         int month = Integer.parseInt(pedido.getFecha().substring(5, 7)) - 1;
/*  592 */         int date = Integer.parseInt(pedido.getFecha().substring(8));
/*  593 */         GregorianCalendar calendario = new GregorianCalendar();
/*  594 */         calendario.set(year, month, date);
/*  595 */         this.cFecha.setCalendar(calendario);
/*  596 */         int indice = 0;
/*  597 */         while ((indice < PedidosJInternalFrame.this.listas.getProveedores().length) && (pedido.getProveedor() != PedidosJInternalFrame.this.listas.getProveedores()[indice].getId())) {
/*  598 */           indice++;
/*      */         }
/*  600 */         this.cProveedor.setSelectedIndex(indice);
/*  601 */         Productos mProducto = new Productos(PedidosJInternalFrame.this.con);
/*  602 */         ArrayList productos = mProducto.listaPedido(PedidosJInternalFrame.this.id_pedido);
/*  603 */         for (productoPedido producto :(List<productoPedido>) productos) {
/*  604 */           this.listaPedido.add(new LineaPedidoObject(producto.getId(), producto.getReferencia(), mProducto.datosReferencia(producto.getReferencia(), PedidosJInternalFrame.this.compras).getDescripcion(), producto.getUnidades(), producto.getImporte().doubleValue(), producto.isRecibido()));
/*      */ 
/*  607 */           this.sumaPiezas += producto.getUnidades();
/*  608 */           this.sumaCostes += producto.getImporte().doubleValue();
/*      */         }
/*  610 */         this.cPiezas.setText(Integer.toString(this.sumaPiezas));
/*  611 */         this.cCoste.setValue(Double.valueOf(this.sumaCostes));
/*  612 */         this.modelo.fireTableDataChanged();
/*  613 */         this.tabla.repaint();
/*  614 */         this.bNuevo.setText(Mensajes.getString("modificar"));
/*      */       }
/*      */     }
/*      */ 
/*      */     private void colocarPedido(int id) {
/*  619 */       if (id != -1)
/*  620 */         colocarPedido(new Pedidos(PedidosJInternalFrame.this.con).obtenerPedido(id));
/*      */     }
/*      */ 
/*      */     private JButton getBNuevo()
/*      */     {
/*  630 */       if (this.bNuevo == null) {
/*  631 */         this.bNuevo = new JButton();
/*  632 */         this.bNuevo.setBounds(new Rectangle(300, 32, 105, 25));
/*  633 */         this.bNuevo.setHorizontalTextPosition(2);
/*  634 */         this.bNuevo.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/new.png")));
/*  635 */         this.bNuevo.setText(Mensajes.getString("nuevo"));
/*  636 */         this.bNuevo.addActionListener(new ActionListener()
/*      */         {
/*      */           public void actionPerformed(ActionEvent e) {
/*  639 */             String titulo = e.getActionCommand();
/*  640 */             if (titulo.equals(Mensajes.getString("nuevo")))
/*  641 */               PedidosJInternalFrame.PanelDerecha.this.nuevoPedido();
/*  642 */             else if (titulo.equals(Mensajes.getString("modificar"))) {
/*  643 */               PedidosJInternalFrame.PanelDerecha.this.modificarPedido();
/*      */             }
/*      */           }
/*      */         });
/*      */       }
/*  648 */       return this.bNuevo;
/*      */     }
/*      */ 
/*      */     private void nuevoPedido() {
/*  652 */       SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
/*  653 */       Date fecha = this.cFecha.getDate();
/*  654 */       if ((fecha == null) || (this.cCodigo.getText().equals(""))) {
/*  655 */         JOptionPane.showMessageDialog(Inicio.frame, Mensajes.getString("pedidoSinDatos"));
/*  656 */         return;
/*      */       }
/*  658 */       Pedidos pedidos = new Pedidos(PedidosJInternalFrame.this.con);
/*  659 */       PedidosJInternalFrame.this.id_pedido = pedidos.nuevoPedido(this.cCodigo.getText(), PedidosJInternalFrame.this.listas.getProveedores()[this.cProveedor.getSelectedIndex()].getId(), sdf.format(fecha), PedidosJInternalFrame.this.compras);
/*      */ 
/*  662 */       this.bNuevo.setText(Mensajes.getString("modificar"));
/*      */     }
/*      */ 
/*      */     private void modificarPedido() {
/*  666 */       SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
/*  667 */       Date fecha = this.cFecha.getDate();
/*  668 */       if ((fecha == null) || (this.cCodigo.getText().equals(""))) {
/*  669 */         return;
/*      */       }
/*  671 */       Pedidos pedidos = new Pedidos(PedidosJInternalFrame.this.con);
/*  672 */       pedidos.modificarPedido(PedidosJInternalFrame.this.id_pedido, this.cCodigo.getText(), PedidosJInternalFrame.this.listas.getProveedores()[this.cProveedor.getSelectedIndex()].getId(), sdf.format(fecha));
/*      */     }
/*      */ 
/*      */     private JButton getBBorrar()
/*      */     {
/*  683 */       if (this.bBorrar == null) {
/*  684 */         this.bBorrar = new JButton();
/*  685 */         this.bBorrar.setBounds(new Rectangle(300, 60, 105, 25));
/*  686 */         this.bBorrar.setHorizontalTextPosition(2);
/*  687 */         this.bBorrar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/delete.png")));
/*  688 */         this.bBorrar.setText(Mensajes.getString("borrar"));
/*  689 */         this.bBorrar.addActionListener(new ActionListener()
/*      */         {
/*      */           public void actionPerformed(ActionEvent e) {
/*  692 */             PedidosJInternalFrame.PanelDerecha.this.borrarPedido();
/*      */           }
/*      */         });
/*      */       }
/*  696 */       return this.bBorrar;
/*      */     }
/*      */ 
/*      */     private void borrarPedido() {
/*  700 */       Pedidos pedido = new Pedidos(PedidosJInternalFrame.this.con);
/*  701 */       pedido.eliminarPedido(PedidosJInternalFrame.this.id_pedido);
/*  702 */       limpiarPanel();
/*      */     }
/*      */ 
/*      */     private JButton getBAnadir()
/*      */     {
/*  711 */       if (this.bAnadir == null) {
/*  712 */         this.bAnadir = new JButton();
/*  713 */         this.bAnadir.setBounds(new Rectangle(10, 10, 35, 29));
/*  714 */         this.bAnadir.setIcon(new ImageIcon(getClass().getResource("/almacen2/iconos/mas.png")));
/*  715 */         this.bAnadir.addActionListener(new ActionListener()
/*      */         {
/*      */           public void actionPerformed(ActionEvent e) {
/*  718 */             String referencia = PedidosJInternalFrame.PanelDerecha.this.getSelectedReferencia();
/*  719 */             if (referencia != null) {
/*  720 */               PedidosJInternalFrame.PanelDerecha.this.anadirProducto(referencia);
/*      */             }
/*      */           }
/*      */         });
/*      */       }
/*  725 */       return this.bAnadir;
/*      */     }
/*      */ 
/*      */     private String getSelectedReferencia() {
/*  729 */       String referencia = null;
/*  730 */       if (!panelIzquierda.jList.isSelectionEmpty()) {
/*  731 */         referencia = panelIzquierda.jList.getSelectedValue().toString();
/*      */       }
/*  733 */       return referencia;
/*      */     }
/*      */ 
/*      */     private void anadirProducto(String referencia) {
/*  737 */       if (PedidosJInternalFrame.this.id_pedido != -1) {
/*  738 */         Productos producto = new Productos(PedidosJInternalFrame.this.con);
/*  739 */         String datos = GetImporteJDialog.obtenerDatos(Inicio.frame, true, Double.valueOf(producto.datosReferencia(referencia, PedidosJInternalFrame.this.compras).getImporte()));
/*  740 */         String tUni = datos.substring(0, datos.indexOf("-"));
/*  741 */         String tImp = datos.substring(datos.indexOf("-") + 1);
/*  742 */         int unidades = Integer.parseInt(tUni);
/*  743 */         double importe = Double.parseDouble(tImp) * unidades;
/*      */ 
/*  745 */         int id = producto.nuevoProducto(PedidosJInternalFrame.this.id_pedido, referencia, Double.valueOf(importe), unidades);
/*  746 */         if (id != -1) {
/*  747 */           this.listaPedido.add(new LineaPedidoObject(id, referencia, producto.datosReferencia(referencia, PedidosJInternalFrame.this.compras).getDescripcion(), unidades, importe, false));
/*      */ 
/*  750 */           this.sumaPiezas += unidades;
/*  751 */           this.sumaCostes += importe;
/*  752 */           this.cPiezas.setText(Integer.toString(this.sumaPiezas));
/*  753 */           this.cCoste.setValue(Double.valueOf(this.sumaCostes));
/*  754 */           this.modelo.fireTableDataChanged();
/*  755 */           this.tabla.repaint();
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*      */     private JButton getBEliminar()
/*      */     {
/*  766 */       if (this.bEliminar == null) {
/*  767 */         this.bEliminar = new JButton();
/*  768 */         this.bEliminar.setBounds(new Rectangle(50, 10, 35, 29));
/*  769 */         this.bEliminar.setIcon(new ImageIcon(getClass().getResource("/almacen2/iconos/menos.png")));
/*  770 */         this.bEliminar.addActionListener(new ActionListener()
/*      */         {
/*      */           public void actionPerformed(ActionEvent e) {
/*  773 */             PedidosJInternalFrame.PanelDerecha.this.eliminarProducto();
/*      */           }
/*      */         });
/*      */       }
/*  777 */       return this.bEliminar;
/*      */     }
/*      */ 
/*      */     private void eliminarProducto()
/*      */     {
/*  782 */       ListSelectionModel lsm = this.tabla.getSelectionModel();
/*  783 */       if (lsm.isSelectionEmpty()) {
/*  784 */         return;
/*      */       }
/*  786 */       int inicio = lsm.getMinSelectionIndex();
/*  787 */       int fin = lsm.getMaxSelectionIndex();
/*      */ 
/*  789 */       for (int fila = inicio; fila <= fin; fila++) {
/*  790 */         if (this.tabla.isRowSelected(fila)) {
/*  791 */           int id = ((LineaPedidoObject)this.listaPedido.get(fila)).getId();
/*  792 */           double coste = ((LineaPedidoObject)this.listaPedido.get(fila)).getCoste();
/*  793 */           int unidades = ((LineaPedidoObject)this.listaPedido.get(fila)).getUnidades();
/*  794 */           new Productos(PedidosJInternalFrame.this.con).eliminarProducto(id);
/*  795 */           this.listaPedido.remove(fila);
/*  796 */           this.sumaPiezas -= unidades;
/*  797 */           this.sumaCostes -= coste;
/*  798 */           this.cPiezas.setText(Integer.toString(this.sumaPiezas));
/*  799 */           this.cCoste.setValue(Double.valueOf(this.sumaCostes));
/*      */         }
/*      */       }
/*  802 */       this.modelo.fireTableDataChanged();
/*  803 */       this.tabla.repaint();
/*      */     }
/*      */ 
/*      */     private JButton getBRecibido()
/*      */     {
/*  812 */       if (this.bRecibido == null) {
/*  813 */         this.bRecibido = new JButton();
/*  814 */         this.bRecibido.setBounds(new Rectangle(90, 10, 35, 29));
/*  815 */         this.bRecibido.setIcon(new ImageIcon(getClass().getResource("/almacen2/iconos/ok.png")));
/*  816 */         this.bRecibido.setToolTipText(Mensajes.getString("marcarRecibido"));
/*  817 */         this.bRecibido.addActionListener(new ActionListener()
/*      */         {
/*      */           public void actionPerformed(ActionEvent e) {
/*  820 */             PedidosJInternalFrame.PanelDerecha.this.marcarRecibido();
/*      */           }
/*      */         });
/*      */       }
/*  824 */       return this.bRecibido;
/*      */     }
/*      */ 
/*      */     private void marcarRecibido() {
/*  828 */       ArrayList almacenes = new ManejadorAlmacenInterno(PedidosJInternalFrame.this.con).getAlmacenes();
/*  829 */       AuxiliarJDialog dlg = new AuxiliarJDialog(Inicio.frame, true, PedidosJInternalFrame.this.compras, almacenes);
/*  830 */       PedidosJInternalFrame.this.mostrarDialogo(dlg);
/*  831 */       if (dlg.isAceptado()) {
/*  832 */         String fecha = dlg.getFecha();
/*  833 */         int almacen = dlg.getAlmacen();
/*  834 */         if (fecha != null)
/*      */         {
/*  836 */           ListSelectionModel lsm = this.tabla.getSelectionModel();
/*  837 */           if (lsm.isSelectionEmpty()) {
/*  838 */             return;
/*      */           }
/*  840 */           int inicio = lsm.getMinSelectionIndex();
/*  841 */           int fin = lsm.getMaxSelectionIndex();
/*      */ 
/*  843 */           for (int fila = inicio; fila <= fin; fila++) {
/*  844 */             if (this.tabla.isRowSelected(fila)) {
/*  845 */               int id = ((LineaPedidoObject)this.listaPedido.get(fila)).getId();
/*  846 */               boolean valorActual = ((LineaPedidoObject)this.listaPedido.get(fila)).isRecibido();
/*  847 */               new Productos(PedidosJInternalFrame.this.con).marcarRecibido(id, !valorActual);
/*  848 */               LineaPedidoObject objeto = new LineaPedidoObject(((LineaPedidoObject)this.listaPedido.get(fila)).getId(), ((LineaPedidoObject)this.listaPedido.get(fila)).getReferencia(), ((LineaPedidoObject)this.listaPedido.get(fila)).getDescripcion(), ((LineaPedidoObject)this.listaPedido.get(fila)).getUnidades(), ((LineaPedidoObject)this.listaPedido.get(fila)).getCoste(), !valorActual);
/*      */ 
/*  850 */               this.listaPedido.set(fila, objeto);
/*  851 */               if (!valorActual) {
/*  852 */                 int unidades = ((LineaPedidoObject)this.listaPedido.get(fila)).getUnidades();
/*  853 */                 PIOObject producto = new PIOObject();
/*  854 */                 producto.setFecha(fecha);
/*  855 */                 producto.setImporte(((LineaPedidoObject)this.listaPedido.get(fila)).getCoste() / unidades);
/*  856 */                 int IO = PedidosJInternalFrame.this.compras ? 1 : -1;
/*  857 */                 producto.setIO(IO);
/*  858 */                 producto.setAlmacen(almacen);
/*  859 */                 ManejadorPIO mPIO = new ManejadorPIO(PedidosJInternalFrame.this.con, ((LineaPedidoObject)this.listaPedido.get(fila)).getReferencia());
/*  860 */                 for (int i = 0; i < unidades; i++) {
/*  861 */                   mPIO.nuevaIO(producto);
/*      */                 }
/*      */               }
/*      */             }
/*      */           }
/*      */ 
/*  867 */           this.modelo.fireTableDataChanged();
/*  868 */           this.tabla.repaint();
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*      */     private JButton getBGuardar()
/*      */     {
/*  879 */       if (this.bGuardar == null) {
/*  880 */         this.bGuardar = new JButton();
/*  881 */         this.bGuardar.setBounds(new Rectangle(140, 10, 35, 29));
/*  882 */         this.bGuardar.setIcon(new ImageIcon(getClass().getResource("/almacen2/iconos/asCSV.png")));
/*  883 */         this.bGuardar.setToolTipText(Mensajes.getString("guardarCSV"));
/*  884 */         this.bGuardar.addActionListener(new ActionListener()
/*      */         {
/*      */           public void actionPerformed(ActionEvent e) {
/*  887 */             PedidosJInternalFrame.PanelDerecha.this.guardarCSV();
/*      */           }
/*      */         });
/*      */       }
/*  891 */       return this.bGuardar;
/*      */     }
/*      */ 
/*      */     private JButton getBMinimos()
/*      */     {
/*  900 */       if (this.bMinimos == null) {
/*  901 */         this.bMinimos = new JButton();
/*  902 */         this.bMinimos.setBounds(new Rectangle(10, 45, 180, 29));
/*  903 */         this.bMinimos.setHorizontalTextPosition(2);
/*  904 */         this.bMinimos.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/producto18.png")));
/*  905 */         this.bMinimos.setText(Mensajes.getString("menu24alma"));
/*      */ 
/*  908 */         this.bMinimos.addActionListener(new ActionListener()
/*      */         {
/*      */           public void actionPerformed(ActionEvent e) {
/*  911 */             int idPedido = PedidosJInternalFrame.PanelDerecha.this.crearPedidoMinimos();
/*  912 */             if (idPedido != -1) {
/*  913 */               PedidosJInternalFrame.PanelDerecha.this.colocarPedido(idPedido);
/*      */             }
/*      */           }
/*      */         });
/*      */       }
/*  918 */       return this.bMinimos;
/*      */     }
/*      */ 
/*      */     private int crearPedidoMinimos() {
/*  922 */       int id_pedido = -1;
/*  923 */       int confirma = JOptionPane.showConfirmDialog(this, new StringBuilder().append(Mensajes.getString("pedmindlg1")).append(": ").append(panelIzquierda.comboProveedores.getSelectedItem().toString()).append("\n").append(Mensajes.getString("pedmindlg2")).toString(), Mensajes.getString("pedidominimo"), 0);
/*      */ 
/*  929 */       if (confirma == 0) {
/*  930 */         SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
/*  931 */         SimpleDateFormat sdf1 = new SimpleDateFormat("ddMMyyyy");
/*  932 */         Date fecha = new Date();
/*  933 */         int indice =panelIzquierda.comboProveedores.getSelectedIndex();
/*  934 */         if (indice > 0) {
/*  935 */           int proveedor = PedidosJInternalFrame.this.listas.getProveedores()[(indice - 1)].getId();
/*  936 */           String codigoPedido = new StringBuilder().append(sdf1.format(fecha)).append(panelIzquierda.comboProveedores.getSelectedItem().toString().substring(0, 3)).toString();
/*  937 */           id_pedido = new Pedidos(PedidosJInternalFrame.this.con).nuevoPedido(codigoPedido, proveedor, sdf.format(fecha), PedidosJInternalFrame.this.compras);
/*  938 */           if (id_pedido != -1) {
/*  939 */             ArrayList listaProductos = new ArrayList();
/*  940 */             listaProductos = new ManejadorProducto(PedidosJInternalFrame.this.con).productosProveedor(proveedor);
/*  941 */             for (ProductObject producto :(List<ProductObject>) listaProductos) {
/*  942 */               int stock = new ManejadorPIO(PedidosJInternalFrame.this.con, producto.getReferencia()).calcularStock();
/*  943 */               int numPiezas = producto.getStockMinimo() - stock;
/*  944 */               if (numPiezas > 0) {
/*  945 */                 if (numPiezas < producto.getPedidoMinimo()) {
/*  946 */                   numPiezas = producto.getPedidoMinimo();
/*      */                 }
/*  948 */                 Productos productoPedido = new Productos(PedidosJInternalFrame.this.con);
/*  949 */                 productoPedido.nuevoProducto(id_pedido, producto.getReferencia(), Double.valueOf(producto.getCoste() * numPiezas), numPiezas);
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*  955 */       return id_pedido;
/*      */     }
/*      */ 
/*      */     private JTextField getCPiezas()
/*      */     {
/*  964 */       if (this.cPiezas == null) {
/*  965 */         this.cPiezas = new JTextField();
/*  966 */         this.cPiezas.setBounds(new Rectangle(280, 10, 75, 19));
/*  967 */         this.cPiezas.setEditable(false);
/*      */       }
/*  969 */       return this.cPiezas;
/*      */     }
/*      */ 
/*      */     private JFormattedTextField getCCoste()
/*      */     {
/*  978 */       if (this.cCoste == null) {
/*  979 */         this.cCoste = new JFormattedTextField(this.fn);
/*  980 */         this.cCoste.setBounds(new Rectangle(280, 35, 100, 19));
/*  981 */         this.cCoste.setEditable(false);
/*      */       }
/*  983 */       return this.cCoste;
/*      */     }
/*      */ 
/*      */     private void guardarCSV() {
/*  987 */       SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
/*  988 */       JFileChooser fc = new JFileChooser();
/*  989 */       fc.setSelectedFile(new File(new StringBuilder().append("Pedido").append(this.cCodigo.getText()).append(".csv").toString()));
/*  990 */       int retorno = fc.showSaveDialog(this);
/*  991 */       if (retorno == 0) {
/*  992 */         File archivo = fc.getSelectedFile();
/*  993 */         archivo = AddExtension.csv(archivo);
/*  994 */         GrabarFichero salida = new GrabarFichero(archivo);
/*  995 */         salida.insertar(new StringBuilder().append(this.cCodigo.getText()).append(": ").append(this.cProveedor.getSelectedItem().toString()).append("\n").append("Fecha: ").append(sdf.format(this.cFecha.getDate())).append("\n\n").toString());
/*      */ 
/*  997 */         StringBuilder sb = new StringBuilder();
/*  998 */         Formatter formater = new Formatter(sb);
/*  999 */         formater.format("%s;%s;%s;%s;%s\n", new Object[] { Mensajes.getString("referencia"), Mensajes.getString("descripcion"), Mensajes.getString("unidades"), Mensajes.getString("coste"), Mensajes.getString("recibido") });
/*      */ 
/* 1004 */         for (int fila = 0; fila < this.tabla.getRowCount(); fila++) {
/* 1005 */           Boolean isReceived = (Boolean)this.tabla.getValueAt(fila, 5);
/* 1006 */           String received = isReceived.booleanValue() ? "Sí" : "No";
/* 1007 */           formater.format("%s;%s;%s;%,10.2f;%s\n", new Object[] { this.tabla.getValueAt(fila, 1), this.tabla.getValueAt(fila, 2), this.tabla.getValueAt(fila, 3), this.tabla.getValueAt(fila, 4), received });
/*      */         }
/*      */ 
/* 1014 */         formater.format("%s;%s;%s;%s\n", new Object[] { Mensajes.getString("piezas"), this.cPiezas.getText(), Mensajes.getString("coste"), this.cCoste.getText() });
/*      */ 
/* 1017 */         salida.insertar(sb.toString());
/*      */       }
/*      */     }
/*      */ 
/*      */     private void pasarAFactura()
/*      */     {
/* 1023 */       ArrayList lineasFactura = new ArrayList();
/* 1024 */       ListSelectionModel lsm = this.tabla.getSelectionModel();
/* 1025 */       if (!lsm.isSelectionEmpty()) {
/* 1026 */         double totalBase = 0.0D; double totalIva = 0.0D; double totalTotal = 0.0D;
/* 1027 */         int filaInicial = lsm.getMinSelectionIndex();
/* 1028 */         int filaFinal = lsm.getMaxSelectionIndex();
/* 1029 */         LineaFactura linea = null;
/*      */ 
/* 1031 */         for (int x = filaInicial; x <= filaFinal; x++) {
/* 1032 */           if (this.tabla.isRowSelected(x)) {
/* 1033 */             boolean incluir = true;
/* 1034 */             String referencia = this.tabla.getValueAt(x, 1).toString();
/* 1035 */             Double importe = (Double)this.tabla.getValueAt(x, 4);
/* 1036 */             importe = doubleTwoDecimals(importe);
/* 1037 */             Integer unidades = Integer.valueOf(Integer.parseInt(this.tabla.getValueAt(x, 3).toString()));
/* 1038 */             boolean isRecibido = ((LineaPedidoObject)this.listaPedido.get(x)).isRecibido();
/* 1039 */             if (isRecibido)
/*      */             {
/* 1041 */               int pregunta = JOptionPane.showConfirmDialog(Inicio.frame, new StringBuilder().append(this.tabla.getValueAt(x, 2).toString()).append("\n").append(Mensajes.getString("incluirrecibido")).toString(), Mensajes.getString("confirma"), 0);
/*      */ 
/* 1045 */               if (pregunta == 0)
/* 1046 */                 incluir = true;
/*      */               else {
/* 1048 */                 incluir = false;
/*      */               }
/*      */             }
/* 1051 */             if (incluir) {
/* 1052 */               Producto producto = null;
/* 1053 */               AlmacenControl aC = new AlmacenControl();
/* 1054 */               producto = aC.getProducto(referencia, false);
/* 1055 */               if (producto != null) {
/* 1056 */                 int idTipoIva = aC.getIvaProducto(producto.getId().intValue());
/* 1057 */                 ManejoTiposIVA mT = new ManejoTiposIVA(Inicio.getCGeneral());
/* 1058 */                 TipoIVA tipoIva = mT.getTipoIVA(idTipoIva);
/* 1059 */                 String concepto = this.tabla.getValueAt(x, 2).toString();
/* 1060 */                 double iva = 0.0D;
/* 1061 */                 if (tipoIva != null) {
/* 1062 */                   iva = importe.doubleValue() * tipoIva.getIva() / 100.0D;
/* 1063 */                   iva = doubleTwoDecimals(Double.valueOf(iva)).doubleValue();
/*      */                 }
/* 1065 */                 double total = importe.doubleValue() + iva;
/* 1066 */                 linea = new LineaFactura(Integer.valueOf(-1), Integer.valueOf(-1), producto, concepto, importe, tipoIva, Double.valueOf(total), Double.valueOf(unidades.doubleValue()));
/* 1067 */                 lineasFactura.add(linea);
/* 1068 */                 totalBase += importe.doubleValue();
/* 1069 */                 totalIva += iva;
/* 1070 */                 totalTotal += total;
/*      */               }
/* 1072 */               aC.cerrarConexion();
/*      */             }
/*      */           }
/*      */         }
/* 1076 */         if (!lineasFactura.isEmpty()) {
/* 1077 */           int idProveedor = PedidosJInternalFrame.this.listas.getProveedores()[this.cProveedor.getSelectedIndex()].getId();
/* 1078 */           if (idProveedor != -1) {
/* 1079 */             FPObject objeto = null;
/* 1080 */             ManejadorFP mFP = new ManejadorFP(PedidosJInternalFrame.this.con);
/* 1081 */             objeto = mFP.getObjeto(false, idProveedor);
/* 1082 */             if (objeto != null) {
/* 1083 */               ManejoSubcuentas mS = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 1084 */               TipoSubcuenta subcuenta = mS.datos(objeto.getSubcuenta1());
/* 1085 */               if (subcuenta != null) {
/* 1086 */                 ManejoAgenda mA = new ManejoAgenda(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 1087 */                 Integer idFormaPago = mA.formaPago(Integer.toString(subcuenta.getCodigo()));
/* 1088 */                 mA.cerrarRs();
/* 1089 */                 ManejoFormasPago mFPago = new ManejoFormasPago(Inicio.getCGeneral());
/* 1090 */                 TipoFormaPago formaPago = new TipoFormaPago();
/* 1091 */                 if (idFormaPago != null)
/* 1092 */                   formaPago = mFPago.getFormaPago(idFormaPago.intValue());
/*      */                 else {
/* 1094 */                   formaPago = mFPago.getFirstFormaPago();
/*      */                 }
/* 1096 */                 mFPago.cerrarRs();
/* 1097 */                 Factura factura = new Factura(Integer.valueOf(-1), "0", subcuenta, new Date(), Double.valueOf(0.0D), false, formaPago, Double.valueOf(totalBase), Double.valueOf(totalIva), false, false);
/* 1098 */                 ArrayList almacenes = new ManejadorAlmacenInterno(PedidosJInternalFrame.this.con).getAlmacenes();
/* 1099 */                 PasoAFactura dlg = new PasoAFactura(Inicio.frame, true, factura, lineasFactura, !PedidosJInternalFrame.this.compras, almacenes);
/* 1100 */                 Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/* 1101 */                 Dimension frameSize = dlg.getSize();
/* 1102 */                 if (frameSize.height > screenSize.height) {
/* 1103 */                   frameSize.height = screenSize.height;
/*      */                 }
/* 1105 */                 if (frameSize.width > screenSize.width) {
/* 1106 */                   frameSize.width = screenSize.width;
/*      */                 }
/* 1108 */                 dlg.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
/*      */ 
/* 1110 */                 dlg.pack();
/* 1111 */                 dlg.setVisible(true);
/* 1112 */                 if (dlg.isIsOk()) {
/* 1113 */                   for (int x = filaInicial; x <= filaFinal; x++) {
/* 1114 */                     if (this.tabla.isRowSelected(x)) {
/* 1115 */                       boolean isRecibido = ((LineaPedidoObject)this.listaPedido.get(x)).isRecibido();
/* 1116 */                       int id = Integer.parseInt(this.tabla.getValueAt(x, 0).toString());
/* 1117 */                       new Productos(PedidosJInternalFrame.this.con).marcarRecibido(id, true);
/* 1118 */                       if (!isRecibido)
/* 1119 */                         this.modelo.changeState(x);
/*      */                     }
/*      */                   }
/* 1122 */                   this.modelo.fireTableDataChanged();
/* 1123 */                   this.tabla.repaint();
/*      */                 }
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/* 1129 */         System.gc();
/*      */       } else {
/* 1131 */         JOptionPane.showMessageDialog(Inicio.frame, Mensajes.getString("selectproductos"), Mensajes.getString("informacion"), 1);
/*      */       }
/*      */     }
/*      */ 
/*      */     private Double doubleTwoDecimals(Double number) {
/* 1136 */       if (number != null) {
/* 1137 */         BigDecimal dec = new BigDecimal(number.doubleValue());
/*      */ 
/* 1139 */         return Double.valueOf(dec.setScale(2, 4).doubleValue());
/*      */       }
/* 1141 */       return Double.valueOf(-1.0D);
/*      */     }
/*      */   }
/*      */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.gui.pedidos.PedidosJInternalFrame
 * JD-Core Version:    0.6.2
 */