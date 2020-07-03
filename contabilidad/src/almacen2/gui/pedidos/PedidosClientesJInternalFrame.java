/*      */ package almacen2.gui.pedidos;
/*      */ 
/*      */ import almacen2.data.FPObject;
/*      */ import almacen2.data.ManejadorAlmacenInterno;
/*      */ import almacen2.data.ManejadorListasInicio;
/*      */ import almacen2.data.ManejadorPIO;
/*      */ import almacen2.data.MySQLConection;
/*      */ import almacen2.data.PIOObject;
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
/*      */ import contaes.dialogosAuxiliares.MostrarCuentas;
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
/*      */ import javax.swing.DefaultComboBoxModel;
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
/*      */ public class PedidosClientesJInternalFrame extends JInternalFrame
/*      */ {
/*   90 */   private MultiSplitPane panelMultiple = null;
/*   91 */   private PanelDerecha panelDerecha = null;
/*   92 */   private PanelIzquierda panelIzquierda = null;
/*   93 */   BorderLayout bLayout = new BorderLayout();
/*      */   private MySQLConection con;
/*      */   private ManejadorListasInicio listas;
/*   96 */   private int id_pedido = -1;
/*   97 */   private boolean compras = false;
/*      */ 
/*      */   public PedidosClientesJInternalFrame(String titulo)
/*      */   {
/*  103 */     this(titulo, -1);
/*      */   }
/*      */ 
/*      */   public PedidosClientesJInternalFrame(String titulo, int idPedido) {
/*  107 */     super(titulo, true, true, true, true);
/*  108 */     setDefaultCloseOperation(2);
/*  109 */     initialize();
/*  110 */     this.panelDerecha.colocarPedido(idPedido);
/*      */   }
/*      */ 
/*      */   private void initialize()
/*      */   {
/*  119 */     this.con = new MySQLConection();
/*  120 */     this.listas = new ManejadorListasInicio(this.con);
/*  121 */     addInternalFrameListener(new InternalFrameAdapter()
/*      */     {
/*      */       public void internalFrameClosed(InternalFrameEvent e)
/*      */       {
/*  125 */         Inicio.frame.eliminarMarcoMenu(Mensajes.getString("pedidosClientes"));
/*  126 */         super.internalFrameClosed(e);
/*      */       }
/*      */ 
/*      */       public void internalFrameClosing(InternalFrameEvent e)
/*      */       {
/*      */         try
/*      */         {
/*  133 */           XMLEncoder encod = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("configdir/colocaPanel3.ini")));
/*      */ 
/*  135 */           MultiSplitLayout.Node model = PedidosClientesJInternalFrame.this.panelMultiple.getMultiSplitLayout().getModel();
/*  136 */           encod.writeObject(model);
/*  137 */           encod.close();
/*      */         } catch (FileNotFoundException e1) {
/*  139 */           e1.printStackTrace();
/*      */         }
/*  141 */         if (PedidosClientesJInternalFrame.this.con != null) {
/*  142 */           PedidosClientesJInternalFrame.this.con.cierraConexion();
/*      */         }
/*  144 */         super.internalFrameClosing(e);
/*      */       }
/*      */     });
/*  147 */     setBounds(20, 20, 750, 440);
/*  148 */     setFrameIcon(new ImageIcon(getClass().getResource("/contaes/iconos/facturacion18.png")));
/*  149 */     getContentPane().setLayout(this.bLayout);
/*  150 */     getContentPane().add(crearPanelMultiple(), "Center");
/*      */   }
/*      */ 
/*      */   private void mostrarDialogo(JDialog dlg) {
/*  154 */     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/*  155 */     Dimension frameSize = dlg.getSize();
/*  156 */     if (frameSize.height > screenSize.height) {
/*  157 */       frameSize.height = screenSize.height;
/*      */     }
/*  159 */     if (frameSize.width > screenSize.width) {
/*  160 */       frameSize.width = screenSize.width;
/*      */     }
/*  162 */     dlg.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
/*      */ 
/*  165 */     dlg.setVisible(true);
/*      */   }
/*      */ 
/*      */   private MultiSplitPane crearPanelMultiple() {
/*  169 */     if (this.panelMultiple == null) {
/*  170 */       String layoutDef = "(ROW (LEAF name=izquierda weight=0.25) (LEAF name=derecha weight=0.75))";
/*      */ 
/*  172 */       this.panelMultiple = new MultiSplitPane();
/*      */       try
/*      */       {
/*  175 */         XMLDecoder d = new XMLDecoder(new BufferedInputStream(new FileInputStream("configdir/colocaPanel3.ini")));
/*      */ 
/*  178 */         MultiSplitLayout.Node model = (MultiSplitLayout.Node)d.readObject();
/*  179 */         this.panelMultiple.getMultiSplitLayout().setModel(model);
/*  180 */         this.panelMultiple.getMultiSplitLayout().setFloatingDividers(false);
/*  181 */         d.close();
/*      */       } catch (Exception exc) {
/*  183 */         MultiSplitLayout.Node model = MultiSplitLayout.parseModel(layoutDef);
/*  184 */         this.panelMultiple.getMultiSplitLayout().setModel(model);
/*      */       }
/*  186 */       this.panelMultiple.setDividerSize(1);
/*  187 */       this.panelMultiple.add(crearPanelIzquierda(), "izquierda");
/*  188 */       this.panelMultiple.add(crearPanelDerecha(), "derecha");
/*      */     }
/*      */ 
/*  191 */     return this.panelMultiple;
/*      */   }
/*      */ 
/*      */   private PanelIzquierda crearPanelIzquierda() {
/*  195 */     if (this.panelIzquierda == null) {
/*  196 */       this.panelIzquierda = new PanelIzquierda();
/*      */     }
/*      */ 
/*  199 */     return this.panelIzquierda;
/*      */   }
/*      */ 
/*      */   private PanelDerecha crearPanelDerecha() {
/*  203 */     if (this.panelDerecha == null) {
/*  204 */       this.panelDerecha = new PanelDerecha();
/*      */     }
/*      */ 
/*  207 */     return this.panelDerecha;
/*      */   }
/*      */ 
/*      */   public void renovarModeloProveedor() {
/*  211 */     this.panelIzquierda.renovarModeloProveedor();
/*      */   }
/*      */ 
/*      */   public void renovarModeloCliente() {
/*  215 */     this.panelDerecha.renovarModeloCliente();
/*      */   }
/*      */ 
/*      */   class PanelIzquierda extends JPanel
/*      */   {
/* 1151 */     private JComboBox comboProveedores = null;
/* 1152 */     private JScrollPane jScrollPane1 = null;
/* 1153 */     private JList jList = null;
/* 1154 */     private DefaultListModel modeloLista = null;
/* 1155 */     private JButton botonBuscar = null;
/*      */ 
/*      */     public PanelIzquierda()
/*      */     {
/* 1162 */       initialize();
/*      */     }
/*      */ 
/*      */     private void initialize()
/*      */     {
/* 1171 */       setLayout(new BorderLayout());
/* 1172 */       add(getComboProveedores(), "North");
/* 1173 */       add(getJScrollPane1(), "Center");
/* 1174 */       add(getBotonBuscar(), "South");
/*      */     }
/*      */ 
/*      */     private JButton getBotonBuscar() {
/* 1178 */       if (this.botonBuscar == null) {
/* 1179 */         this.botonBuscar = new JButton();
/* 1180 */         this.botonBuscar.setPreferredSize(new Dimension(75, 25));
/* 1181 */         this.botonBuscar.setText(Mensajes.getString("buscar") + " " + Mensajes.getString("producto"));
/* 1182 */         this.botonBuscar.setHorizontalTextPosition(2);
/* 1183 */         this.botonBuscar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/find.png")));
/* 1184 */         this.botonBuscar.addActionListener(new ActionListener()
/*      */         {
/*      */           public void actionPerformed(ActionEvent e) {
/* 1187 */             String referencia = almacen2.gui.Busqueda.obtenerObjeto(Inicio.frame, PedidosClientesJInternalFrame.this.con);
/* 1188 */             if (referencia != null) {
/* 1189 */            //   PedidosClientesJInternalFrame.PanelDerecha.access$1500(PedidosClientesJInternalFrame.this.panelDerecha, referencia);
 /*      */           new   PedidosClientesJInternalFrame(panelDerecha.getSelectedReferencia()); 
                          }
/*      */           }
/*      */         });
/*      */       }
/* 1194 */       return this.botonBuscar;
/*      */     }
/*      */ 
/*      */     private JComboBox getComboProveedores()
/*      */     {
/* 1203 */       if (this.comboProveedores == null) {
/* 1204 */         this.comboProveedores = new JComboBox();
/* 1205 */         this.comboProveedores.addActionListener(new ActionListener()
/*      */         {
/*      */           public void actionPerformed(ActionEvent e) {
/* 1208 */             int indice = PedidosClientesJInternalFrame.PanelIzquierda.this.comboProveedores.getSelectedIndex();
/* 1209 */             if (indice > 0)
/* 1210 */               PedidosClientesJInternalFrame.PanelIzquierda.this.llenarLista(PedidosClientesJInternalFrame.this.listas.getProveedores()[(indice - 1)].getId());
/*      */           }
/*      */         });
/* 1214 */         llenarComboProveedores();
/*      */       }
/* 1216 */       return this.comboProveedores;
/*      */     }
/*      */ 
/*      */     private JScrollPane getJScrollPane1()
/*      */     {
/* 1225 */       if (this.jScrollPane1 == null) {
/* 1226 */         this.jScrollPane1 = new JScrollPane();
/* 1227 */         this.jScrollPane1.setViewportView(getJList());
/*      */       }
/* 1229 */       return this.jScrollPane1;
/*      */     }
/*      */ 
/*      */     private JList getJList()
/*      */     {
/* 1238 */       if (this.jList == null) {
/* 1239 */         this.jList = new JList(getModeloLista());
/* 1240 */         this.jList.setSelectionMode(1);
/* 1241 */         this.jList.setLayoutOrientation(0);
/* 1242 */         this.jList.setBackground(Color.white);
/* 1243 */         this.jList.setDragEnabled(true);
/*      */       }
/* 1245 */       return this.jList;
/*      */     }
/*      */ 
/*      */     private DefaultListModel getModeloLista() {
/* 1249 */       if (this.modeloLista == null) {
/* 1250 */         this.modeloLista = new DefaultListModel();
/*      */       }
/* 1252 */       return this.modeloLista;
/*      */     }
/*      */ 
/*      */     private void llenarLista(int idProveedor) {
/* 1256 */       PedidosClientesJInternalFrame.this.listas.actualizarReferencias(idProveedor, false);
/* 1257 */       if (PedidosClientesJInternalFrame.this.listas.getReferencias() != null) {
/* 1258 */         this.modeloLista.clear();
/* 1259 */         for (String refer : PedidosClientesJInternalFrame.this.listas.getReferencias())
/* 1260 */           this.modeloLista.addElement(refer);
/*      */       }
/*      */     }
/*      */ 
/*      */     private void llenarComboProveedores()
/*      */     {
/* 1266 */       if (this.comboProveedores != null) {
/* 1267 */         this.comboProveedores.removeAllItems();
/* 1268 */         this.comboProveedores.addItem(Mensajes.getString("proveedores"));
/* 1269 */         for (int x = 0; x < PedidosClientesJInternalFrame.this.listas.getProveedores().length; x++)
/* 1270 */           this.comboProveedores.addItem(PedidosClientesJInternalFrame.this.listas.getProveedores()[x].getNombre());
/*      */       }
/*      */     }
/*      */ 
/*      */     public void renovarModeloProveedor()
/*      */     {
/* 1276 */       PedidosClientesJInternalFrame.this.listas.actualizarFamiliasyProveedores(false);
/* 1277 */       if (this.comboProveedores != null) {
/* 1278 */         this.comboProveedores.removeAllItems();
/* 1279 */         this.comboProveedores.addItem(Mensajes.getString("proveedores"));
/* 1280 */         for (int x = 0; x < PedidosClientesJInternalFrame.this.listas.getProveedores().length; x++)
/* 1281 */           this.comboProveedores.addItem(PedidosClientesJInternalFrame.this.listas.getProveedores()[x].getNombre());
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
/*  241 */     private JButton bFactura = null;
/*  242 */     private JLabel jLabel3 = null;
/*  243 */     private JTextField cPiezas = null;
/*  244 */     private JLabel jLabel4 = null;
/*  245 */     private JFormattedTextField cCoste = null;
/*  246 */     private PedidoTableModel modelo = null;
/*  247 */     private JButton botonPdf = null;
/*  248 */     private JButton bBuscarC = null;
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
/*  292 */         this.jLabel2.setText(Mensajes.getString("cliente"));
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
/*  328 */         this.jLabel4.setText(Mensajes.getString("importe"));
/*  329 */         this.jLabel3 = new JLabel();
/*  330 */         this.jLabel3.setBounds(new Rectangle(230, 10, 45, 16));
/*  331 */         this.jLabel3.setText(Mensajes.getString("piezas"));
/*  332 */         this.jPanel1.add(getBAnadir(), null);
/*  333 */         this.jPanel1.add(getBEliminar(), null);
/*  334 */         this.jPanel1.add(getBRecibido(), null);
/*  335 */         this.jPanel1.add(getBGuardar(), null);
/*  336 */         this.jPanel1.add(getBotonPdf(), null);
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
/*  391 */         this.modelo = new PedidoTableModel(this.listaPedido, PedidosClientesJInternalFrame.this.compras);
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
/*  408 */             int id = new Pedidos(PedidosClientesJInternalFrame.this.con).obtenerID(PedidosClientesJInternalFrame.PanelDerecha.this.cCodigo.getText(), PedidosClientesJInternalFrame.this.compras);
/*  409 */             if (id != -1) {
/*  410 */               int mostrar = JOptionPane.showConfirmDialog(Inicio.frame, Mensajes.getString("mostrarPedido") + "\n" + Mensajes.getString("mostrarPedido2"), Mensajes.getString("confirma"), 0);
/*      */ 
/*  414 */               if (mostrar == 0) {
/*  415 */                 PedidosClientesJInternalFrame.PanelDerecha.this.colocarPedido(id);
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
/*  447 */         llenarCProveedor();
/*      */       }
/*  449 */       return this.cProveedor;
/*      */     }
/*      */ 
/*      */     private JButton getBBuscarC() {
/*  453 */       if (this.bBuscarC == null) {
/*  454 */         this.bBuscarC = new JButton();
/*  455 */         this.bBuscarC.setBounds(new Rectangle(235, 58, 25, 25));
/*  456 */         this.bBuscarC.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/find.png")));
/*  457 */         this.bBuscarC.addActionListener(new ActionListener()
/*      */         {
/*      */           public void actionPerformed(ActionEvent e) {
/*  460 */             PedidosClientesJInternalFrame.PanelDerecha.this.buscarCliente();
/*      */           }
/*      */         });
/*      */       }
/*  464 */       return this.bBuscarC;
/*      */     }
/*      */ 
/*      */     private void llenarCProveedor() {
/*  468 */       if (this.cProveedor != null) {
/*  469 */         ArrayList array = new ArrayList();
/*  470 */         ManejoSubcuentas mS = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  471 */         array = mS.listaSubcuentas(43000000, 43599999);
/*  472 */         mS.cerrarRs();
/*  473 */         DefaultComboBoxModel modeloCP = new DefaultComboBoxModel();
/*  474 */         for (TipoSubcuenta tipoSubcuenta :(List<TipoSubcuenta>) array) {
/*  475 */           modeloCP.addElement(tipoSubcuenta);
/*      */         }
/*  477 */         this.cProveedor.setModel(modeloCP);
/*      */       }
/*      */     }
/*      */ 
/*      */     public void renovarModeloCliente() {
/*  482 */       if (this.cProveedor != null) {
/*  483 */         ArrayList array = new ArrayList();
/*  484 */         ManejoSubcuentas mS = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  485 */         array = mS.listaSubcuentas(43000000, 43599999);
/*  486 */         mS.cerrarRs();
/*  487 */         DefaultComboBoxModel modeloCP = new DefaultComboBoxModel();
/*  488 */         for (TipoSubcuenta tipoSubcuenta :(List<TipoSubcuenta>) array) {
/*  489 */           modeloCP.addElement(tipoSubcuenta);
/*      */         }
/*  491 */         this.cProveedor.setModel(modeloCP);
/*      */       }
/*      */     }
/*      */ 
/*      */     private int cProveedorGetSubcuentaSelected() {
/*  496 */       int s = -1;
/*  497 */       TipoSubcuenta subcuenta = (TipoSubcuenta)this.cProveedor.getSelectedItem();
/*  498 */       if (subcuenta != null) {
/*  499 */         s = subcuenta.getCodigo();
/*      */       }
/*  501 */       return s;
/*      */     }
/*      */ 
/*      */     private void cProveedorSetSelectedSubcuenta(int codigo) {
/*  505 */       for (int i = 0; i < this.cProveedor.getItemCount(); i++) {
/*  506 */         TipoSubcuenta subcuenta = (TipoSubcuenta)this.cProveedor.getItemAt(i);
/*  507 */         if (subcuenta.getCodigo() == codigo) {
/*  508 */           this.cProveedor.setSelectedIndex(i);
/*  509 */           break;
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*      */     private void buscarCliente() {
/*  515 */       MostrarCuentas dlg2 = new MostrarCuentas(Inicio.frame, Mensajes.getString("cuentas"), true, 43000000, 43599999);
/*  516 */       Dimension dlgSize = dlg2.getPreferredSize();
/*  517 */       Dimension frmSize = Inicio.frame.getSize();
/*  518 */       Point loc = getLocation();
/*  519 */       dlg2.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
/*      */ 
/*  521 */       dlg2.setVisible(true);
/*  522 */       if (!dlg2.Seleccion().equals("")) {
/*  523 */         Integer codigo = Integer.valueOf(Integer.parseInt(dlg2.Seleccion()));
/*  524 */         cProveedorSetSelectedSubcuenta(codigo.intValue());
/*      */       }
/*      */     }
/*      */ 
/*      */     private JButton getBLimpiar()
/*      */     {
/*  534 */       if (this.bLimpiar == null) {
/*  535 */         this.bLimpiar = new JButton();
/*  536 */         this.bLimpiar.setBounds(new Rectangle(300, 5, 105, 25));
/*  537 */         this.bLimpiar.setHorizontalTextPosition(2);
/*  538 */         this.bLimpiar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/cancel.png")));
/*  539 */         this.bLimpiar.setText(Mensajes.getString("limpiar"));
/*  540 */         this.bLimpiar.addActionListener(new ActionListener()
/*      */         {
/*      */           public void actionPerformed(ActionEvent e) {
/*  543 */             PedidosClientesJInternalFrame.PanelDerecha.this.limpiarPanel();
/*      */           }
/*      */         });
/*      */       }
/*  547 */       return this.bLimpiar;
/*      */     }
/*      */ 
/*      */     private void limpiarPanel() {
/*  551 */       this.cCodigo.setText("");
/*  552 */       this.cFecha.setDate(new Date());
/*  553 */       this.cProveedor.setSelectedIndex(0);
/*  554 */       this.cCoste.setText("0");
/*  555 */       this.cPiezas.setText("0");
/*  556 */       this.listaPedido.clear();
/*  557 */       this.modelo.fireTableDataChanged();
/*  558 */       this.tabla.repaint();
/*  559 */       PedidosClientesJInternalFrame.this.id_pedido = -1;
/*  560 */       this.sumaPiezas = 0;
/*  561 */       this.sumaCostes = 0.0D;
/*  562 */       this.bNuevo.setText(Mensajes.getString("nuevo"));
/*      */     }
/*      */ 
/*      */     private JButton getBBuscar()
/*      */     {
/*  571 */       if (this.bBuscar == null) {
/*  572 */         this.bBuscar = new JButton();
/*  573 */         this.bBuscar.setBounds(new Rectangle(415, 60, 90, 25));
/*  574 */         this.bBuscar.setHorizontalTextPosition(2);
/*  575 */         this.bBuscar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/find.png")));
/*  576 */         this.bBuscar.setText(Mensajes.getString("buscar"));
/*  577 */         this.bBuscar.addActionListener(new ActionListener()
/*      */         {
/*      */           public void actionPerformed(ActionEvent e) {
/*  580 */             PedidosClientesJInternalFrame.PanelDerecha.this.buscarPedido();
/*      */           }
/*      */         });
/*      */       }
/*  584 */       return this.bBuscar;
/*      */     }
/*      */ 
/*      */     private JButton getBFactura() {
/*  588 */       if (this.bFactura == null) {
/*  589 */         this.bFactura = new JButton();
/*  590 */         this.bFactura.setBounds(new Rectangle(415, 20, 120, 25));
/*  591 */         this.bFactura.setHorizontalTextPosition(2);
/*  592 */         this.bFactura.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/facturacion18.png")));
/*  593 */         this.bFactura.setText(Mensajes.getString("crearFactura"));
/*  594 */         this.bFactura.addActionListener(new ActionListener()
/*      */         {
/*      */           public void actionPerformed(ActionEvent e) {
/*  597 */             PedidosClientesJInternalFrame.PanelDerecha.this.pasarAFactura();
/*      */           }
/*      */         });
/*      */       }
/*  601 */       return this.bFactura;
/*      */     }
/*      */ 
/*      */     private void buscarPedido() {
/*  605 */       PedidoObject pedido = Busqueda.obtenerObjeto(Inicio.frame, PedidosClientesJInternalFrame.this.con, PedidosClientesJInternalFrame.this.compras);
/*  606 */       if (pedido != null)
/*  607 */         colocarPedido(pedido);
/*      */     }
/*      */ 
/*      */     private void colocarPedido(PedidoObject pedido)
/*      */     {
/*  612 */       if (pedido != null) {
/*  613 */         limpiarPanel();
/*  614 */         PedidosClientesJInternalFrame.this.id_pedido = new Pedidos(PedidosClientesJInternalFrame.this.con).obtenerID(pedido.getCodigo(), pedido.getProveedor(), pedido.getFecha(), PedidosClientesJInternalFrame.this.compras);
/*  615 */         this.cCodigo.setText(pedido.getCodigo());
/*  616 */         int year = Integer.parseInt(pedido.getFecha().substring(0, 4));
/*  617 */         int month = Integer.parseInt(pedido.getFecha().substring(5, 7)) - 1;
/*  618 */         int date = Integer.parseInt(pedido.getFecha().substring(8));
/*  619 */         GregorianCalendar calendario = new GregorianCalendar();
/*  620 */         calendario.set(year, month, date);
/*  621 */         this.cFecha.setCalendar(calendario);
/*  622 */         cProveedorSetSelectedSubcuenta(pedido.getProveedor());
/*  623 */         Productos mProducto = new Productos(PedidosClientesJInternalFrame.this.con);
/*  624 */         ArrayList productos = mProducto.listaPedido(PedidosClientesJInternalFrame.this.id_pedido);
/*  625 */         for (productoPedido producto :(List<productoPedido>) productos) {
/*  626 */           this.listaPedido.add(new LineaPedidoObject(producto.getId(), producto.getReferencia(), mProducto.datosReferencia(producto.getReferencia(), PedidosClientesJInternalFrame.this.compras).getDescripcion(), producto.getUnidades(), producto.getImporte().doubleValue(), producto.isRecibido()));
/*      */ 
/*  629 */           this.sumaPiezas += producto.getUnidades();
/*  630 */           this.sumaCostes += producto.getImporte().doubleValue();
/*      */         }
/*  632 */         this.cPiezas.setText(Integer.toString(this.sumaPiezas));
/*  633 */         this.cCoste.setValue(Double.valueOf(this.sumaCostes));
/*  634 */         this.modelo.fireTableDataChanged();
/*  635 */         this.tabla.repaint();
/*  636 */         this.bNuevo.setText(Mensajes.getString("modificar"));
/*      */       }
/*      */     }
/*      */ 
/*      */     private void colocarPedido(int id) {
/*  641 */       if (id != -1)
/*  642 */         colocarPedido(new Pedidos(PedidosClientesJInternalFrame.this.con).obtenerPedido(id));
/*      */     }
/*      */ 
/*      */     private JButton getBNuevo()
/*      */     {
/*  652 */       if (this.bNuevo == null) {
/*  653 */         this.bNuevo = new JButton();
/*  654 */         this.bNuevo.setBounds(new Rectangle(300, 32, 105, 25));
/*  655 */         this.bNuevo.setText(Mensajes.getString("nuevo"));
/*  656 */         this.bNuevo.setHorizontalTextPosition(2);
/*  657 */         this.bNuevo.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/new.png")));
/*  658 */         this.bNuevo.addActionListener(new ActionListener()
/*      */         {
/*      */           public void actionPerformed(ActionEvent e) {
/*  661 */             String titulo = e.getActionCommand();
/*  662 */             if (titulo.equals(Mensajes.getString("nuevo")))
/*  663 */               PedidosClientesJInternalFrame.PanelDerecha.this.nuevoPedido();
/*  664 */             else if (titulo.equals(Mensajes.getString("modificar"))) {
/*  665 */               PedidosClientesJInternalFrame.PanelDerecha.this.modificarPedido();
/*      */             }
/*      */           }
/*      */         });
/*      */       }
/*  670 */       return this.bNuevo;
/*      */     }
/*      */ 
/*      */     private void nuevoPedido() {
/*  674 */       SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
/*  675 */       Date fecha = this.cFecha.getDate();
/*  676 */       if ((fecha == null) || (this.cCodigo.getText().equals(""))) {
/*  677 */         JOptionPane.showMessageDialog(Inicio.frame, Mensajes.getString("pedidoSinDatos"));
/*  678 */         return;
/*      */       }
/*  680 */       Pedidos pedidos = new Pedidos(PedidosClientesJInternalFrame.this.con);
/*  681 */       PedidosClientesJInternalFrame.this.id_pedido = pedidos.nuevoPedido(this.cCodigo.getText(), cProveedorGetSubcuentaSelected(), sdf.format(fecha), PedidosClientesJInternalFrame.this.compras);
/*      */ 
/*  684 */       this.bNuevo.setText(Mensajes.getString("modificar"));
/*      */     }
/*      */ 
/*      */     private void modificarPedido() {
/*  688 */       SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
/*  689 */       Date fecha = this.cFecha.getDate();
/*  690 */       if ((fecha == null) || (this.cCodigo.getText().equals(""))) {
/*  691 */         return;
/*      */       }
/*  693 */       Pedidos pedidos = new Pedidos(PedidosClientesJInternalFrame.this.con);
/*  694 */       pedidos.modificarPedido(PedidosClientesJInternalFrame.this.id_pedido, this.cCodigo.getText(), cProveedorGetSubcuentaSelected(), sdf.format(fecha));
/*      */     }
/*      */ 
/*      */     private JButton getBBorrar()
/*      */     {
/*  705 */       if (this.bBorrar == null) {
/*  706 */         this.bBorrar = new JButton();
/*  707 */         this.bBorrar.setBounds(new Rectangle(300, 60, 105, 25));
/*  708 */         this.bBorrar.setHorizontalTextPosition(2);
/*  709 */         this.bBorrar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/delete.png")));
/*  710 */         this.bBorrar.setText(Mensajes.getString("borrar"));
/*  711 */         this.bBorrar.addActionListener(new ActionListener()
/*      */         {
/*      */           public void actionPerformed(ActionEvent e) {
/*  714 */             PedidosClientesJInternalFrame.PanelDerecha.this.borrarPedido();
/*      */           }
/*      */         });
/*      */       }
/*  718 */       return this.bBorrar;
/*      */     }
/*      */ 
/*      */     private void borrarPedido() {
/*  722 */       Pedidos pedido = new Pedidos(PedidosClientesJInternalFrame.this.con);
/*  723 */       pedido.eliminarPedido(PedidosClientesJInternalFrame.this.id_pedido);
/*  724 */       limpiarPanel();
/*      */     }
/*      */ 
/*      */     private JButton getBotonPdf() {
/*  728 */       if (this.botonPdf == null) {
/*  729 */         this.botonPdf = new JButton();
/*  730 */         this.botonPdf.setBounds(new Rectangle(10, 45, 120, 29));
/*  731 */         this.botonPdf.setText(Mensajes.getString("albaran"));
/*  732 */         this.botonPdf.setHorizontalTextPosition(2);
/*  733 */         this.botonPdf.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/pdf.png")));
/*  734 */         this.botonPdf.addActionListener(new ActionListener()
/*      */         {
/*      */           public void actionPerformed(ActionEvent e) {
/*  737 */             PedidosClientesJInternalFrame.PanelDerecha.this.generarPdf();
/*      */           }
/*      */         });
/*      */       }
/*  741 */       return this.botonPdf;
/*      */     }
/*      */ 
/*      */     private void generarPdf() {
/*  745 */       String codigo = this.cCodigo.getText();
/*  746 */       Date f = this.cFecha.getDate();
/*  747 */       int subcuenta = cProveedorGetSubcuentaSelected();
/*  748 */       if ((!codigo.equals("")) && (f != null)) {
/*  749 */         SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
/*  750 */         String fecha = sdf.format(f);
/*  751 */         PedidoObject pedido = new PedidoObject(Integer.valueOf(-1), codigo, fecha, subcuenta);
/*  752 */         ArrayList lineasPedido = new ArrayList();
/*      */ 
/*  754 */         for (int x = 0; x < this.tabla.getRowCount(); x++) {
/*  755 */           String referencia = this.tabla.getValueAt(x, 1).toString();
/*  756 */           String descripcion = this.tabla.getValueAt(x, 2).toString();
/*  757 */           Integer unidades = (Integer)this.tabla.getValueAt(x, 3);
/*  758 */           Double precio = (Double)this.tabla.getValueAt(x, 4);
/*  759 */           LineaPedidoObject linea = new LineaPedidoObject(-1, referencia, descripcion, unidades.intValue(), precio.doubleValue(), false);
/*  760 */           lineasPedido.add(linea);
/*      */         }
/*  762 */         if (lineasPedido.size() > 0) {
/*  763 */           GenerarPdf crearPdf = new GenerarPdf(pedido, lineasPedido);
/*  764 */           crearPdf.hacerTrabajo();
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*      */     private JButton getBAnadir()
/*      */     {
/*  775 */       if (this.bAnadir == null) {
/*  776 */         this.bAnadir = new JButton();
/*  777 */         this.bAnadir.setBounds(new Rectangle(10, 10, 35, 29));
/*  778 */         this.bAnadir.setIcon(new ImageIcon(getClass().getResource("/almacen2/iconos/mas.png")));
/*  779 */         this.bAnadir.addActionListener(new ActionListener()
/*      */         {
/*      */           public void actionPerformed(ActionEvent e) {
/*  782 */             String referencia = PedidosClientesJInternalFrame.PanelDerecha.this.getSelectedReferencia();
/*  783 */             if (referencia != null) {
/*  784 */               PedidosClientesJInternalFrame.PanelDerecha.this.anadirProducto(referencia);
/*      */             }
/*      */           }
/*      */         });
/*      */       }
/*  789 */       return this.bAnadir;
/*      */     }
/*      */ 
/*      */     private String getSelectedReferencia() {
/*  793 */       String referencia = null;
/*  794 */  //     if (!PedidosClientesJInternalFrame.access$1600(PedidosClientesJInternalFrame.this).jList.isSelectionEmpty()) {
     if (!panelIzquierda.jList.isSelectionEmpty()) {
/*  795 */     //    referencia = PedidosClientesJInternalFrame.access$1600(PedidosClientesJInternalFrame.this).jList.getSelectedValue().toString();
/*      */        referencia = panelIzquierda.jList.getSelectedValue().toString();
     }
/*  797 */       return referencia;
/*      */     }
/*      */ 
/*      */     private void anadirProducto(String referencia) {
/*  801 */       if (PedidosClientesJInternalFrame.this.id_pedido != -1) {
/*  802 */         Productos producto = new Productos(PedidosClientesJInternalFrame.this.con);
/*  803 */         String datos = GetImporteJDialog.obtenerDatos(Inicio.frame, true, Double.valueOf(producto.datosReferencia(referencia, PedidosClientesJInternalFrame.this.compras).getImporte()));
/*  804 */         String tUni = datos.substring(0, datos.indexOf("-"));
/*  805 */         String tImp = datos.substring(datos.indexOf("-") + 1);
/*  806 */         int unidades = Integer.parseInt(tUni);
/*  807 */         double importe = Double.parseDouble(tImp) * unidades;
/*  808 */         int id = producto.nuevoProducto(PedidosClientesJInternalFrame.this.id_pedido, referencia, Double.valueOf(importe), unidades);
/*  809 */         if (id != -1) {
/*  810 */           this.listaPedido.add(new LineaPedidoObject(id, referencia, producto.datosReferencia(referencia, PedidosClientesJInternalFrame.this.compras).getDescripcion(), unidades, importe, false));
/*      */ 
/*  813 */           this.sumaPiezas += unidades;
/*  814 */           this.sumaCostes += importe;
/*  815 */           this.cPiezas.setText(Integer.toString(this.sumaPiezas));
/*  816 */           this.cCoste.setValue(Double.valueOf(this.sumaCostes));
/*  817 */           this.modelo.fireTableDataChanged();
/*  818 */           this.tabla.repaint();
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*      */     private JButton getBEliminar()
/*      */     {
/*  829 */       if (this.bEliminar == null) {
/*  830 */         this.bEliminar = new JButton();
/*  831 */         this.bEliminar.setBounds(new Rectangle(50, 10, 35, 29));
/*  832 */         this.bEliminar.setIcon(new ImageIcon(getClass().getResource("/almacen2/iconos/menos.png")));
/*  833 */         this.bEliminar.addActionListener(new ActionListener()
/*      */         {
/*      */           public void actionPerformed(ActionEvent e) {
/*  836 */             PedidosClientesJInternalFrame.PanelDerecha.this.eliminarProducto();
/*      */           }
/*      */         });
/*      */       }
/*  840 */       return this.bEliminar;
/*      */     }
/*      */ 
/*      */     private void eliminarProducto()
/*      */     {
/*  845 */       ListSelectionModel lsm = this.tabla.getSelectionModel();
/*  846 */       if (lsm.isSelectionEmpty()) {
/*  847 */         return;
/*      */       }
/*  849 */       int inicio = lsm.getMinSelectionIndex();
/*  850 */       int fin = lsm.getMaxSelectionIndex();
/*      */ 
/*  852 */       for (int fila = inicio; fila <= fin; fila++) {
/*  853 */         if (this.tabla.isRowSelected(fila)) {
/*  854 */           int id = ((LineaPedidoObject)this.listaPedido.get(fila)).getId();
/*  855 */           double coste = ((LineaPedidoObject)this.listaPedido.get(fila)).getCoste();
/*  856 */           int unidades = ((LineaPedidoObject)this.listaPedido.get(fila)).getUnidades();
/*  857 */           new Productos(PedidosClientesJInternalFrame.this.con).eliminarProducto(id);
/*  858 */           this.listaPedido.remove(fila);
/*  859 */           this.sumaPiezas -= unidades;
/*  860 */           this.sumaCostes -= coste;
/*  861 */           this.cPiezas.setText(Integer.toString(this.sumaPiezas));
/*  862 */           this.cCoste.setValue(Double.valueOf(this.sumaCostes));
/*      */         }
/*      */       }
/*  865 */       this.modelo.fireTableDataChanged();
/*  866 */       this.tabla.repaint();
/*      */     }
/*      */ 
/*      */     private JButton getBRecibido()
/*      */     {
/*  875 */       if (this.bRecibido == null) {
/*  876 */         this.bRecibido = new JButton();
/*  877 */         this.bRecibido.setBounds(new Rectangle(90, 10, 35, 29));
/*  878 */         this.bRecibido.setIcon(new ImageIcon(getClass().getResource("/almacen2/iconos/ok.png")));
/*  879 */         this.bRecibido.setToolTipText(Mensajes.getString("marcarEnviado"));
/*  880 */         this.bRecibido.addActionListener(new ActionListener()
/*      */         {
/*      */           public void actionPerformed(ActionEvent e) {
/*  883 */             PedidosClientesJInternalFrame.PanelDerecha.this.marcarRecibido();
/*      */           }
/*      */         });
/*      */       }
/*  887 */       return this.bRecibido;
/*      */     }
/*      */ 
/*      */     private void marcarRecibido() {
/*  891 */       ArrayList almacenes = new ManejadorAlmacenInterno(PedidosClientesJInternalFrame.this.con).getAlmacenes();
/*  892 */       AuxiliarJDialog dlg = new AuxiliarJDialog(Inicio.frame, true, PedidosClientesJInternalFrame.this.compras, almacenes);
/*  893 */       PedidosClientesJInternalFrame.this.mostrarDialogo(dlg);
/*  894 */       if (dlg.isAceptado()) {
/*  895 */         String fecha = dlg.getFecha();
/*  896 */         int almacen = dlg.getAlmacen();
/*  897 */         if (fecha != null)
/*      */         {
/*  899 */           ListSelectionModel lsm = this.tabla.getSelectionModel();
/*  900 */           if (lsm.isSelectionEmpty()) {
/*  901 */             return;
/*      */           }
/*  903 */           int inicio = lsm.getMinSelectionIndex();
/*  904 */           int fin = lsm.getMaxSelectionIndex();
/*      */ 
/*  906 */           for (int fila = inicio; fila <= fin; fila++) {
/*  907 */             if (this.tabla.isRowSelected(fila)) {
/*  908 */               int id = ((LineaPedidoObject)this.listaPedido.get(fila)).getId();
/*  909 */               boolean valorActual = ((LineaPedidoObject)this.listaPedido.get(fila)).isRecibido();
/*  910 */               new Productos(PedidosClientesJInternalFrame.this.con).marcarRecibido(id, !valorActual);
/*  911 */               LineaPedidoObject objeto = new LineaPedidoObject(((LineaPedidoObject)this.listaPedido.get(fila)).getId(), ((LineaPedidoObject)this.listaPedido.get(fila)).getReferencia(), ((LineaPedidoObject)this.listaPedido.get(fila)).getDescripcion(), ((LineaPedidoObject)this.listaPedido.get(fila)).getUnidades(), ((LineaPedidoObject)this.listaPedido.get(fila)).getCoste(), !valorActual);
/*      */ 
/*  913 */               this.listaPedido.set(fila, objeto);
/*  914 */               if (!valorActual) {
/*  915 */                 int unidades = ((LineaPedidoObject)this.listaPedido.get(fila)).getUnidades();
/*  916 */                 PIOObject producto = new PIOObject();
/*  917 */                 producto.setFecha(fecha);
/*  918 */                 producto.setImporte(((LineaPedidoObject)this.listaPedido.get(fila)).getCoste() / unidades);
/*  919 */                 int IO = PedidosClientesJInternalFrame.this.compras ? 1 : -1;
/*  920 */                 producto.setIO(IO);
/*  921 */                 producto.setAlmacen(almacen);
/*  922 */                 ManejadorPIO mPIO = new ManejadorPIO(PedidosClientesJInternalFrame.this.con, ((LineaPedidoObject)this.listaPedido.get(fila)).getReferencia());
/*  923 */                 for (int i = 0; i < unidades; i++) {
/*  924 */                   mPIO.nuevaIO(producto);
/*      */                 }
/*      */               }
/*      */             }
/*      */           }
/*  929 */           this.modelo.fireTableDataChanged();
/*  930 */           this.tabla.repaint();
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*      */     private JButton getBGuardar()
/*      */     {
/*  941 */       if (this.bGuardar == null) {
/*  942 */         this.bGuardar = new JButton();
/*  943 */         this.bGuardar.setBounds(new Rectangle(140, 10, 35, 29));
/*  944 */         this.bGuardar.setIcon(new ImageIcon(getClass().getResource("/almacen2/iconos/asCSV.png")));
/*  945 */         this.bGuardar.setToolTipText(Mensajes.getString("guardarCSV"));
/*  946 */         this.bGuardar.addActionListener(new ActionListener()
/*      */         {
/*      */           public void actionPerformed(ActionEvent e) {
/*  949 */             PedidosClientesJInternalFrame.PanelDerecha.this.guardarCSV();
/*      */           }
/*      */         });
/*      */       }
/*  953 */       return this.bGuardar;
/*      */     }
/*      */ 
/*      */     private JTextField getCPiezas()
/*      */     {
/*  962 */       if (this.cPiezas == null) {
/*  963 */         this.cPiezas = new JTextField();
/*  964 */         this.cPiezas.setBounds(new Rectangle(280, 10, 75, 19));
/*  965 */         this.cPiezas.setEditable(false);
/*      */       }
/*  967 */       return this.cPiezas;
/*      */     }
/*      */ 
/*      */     private JFormattedTextField getCCoste()
/*      */     {
/*  976 */       if (this.cCoste == null) {
/*  977 */         this.cCoste = new JFormattedTextField(this.fn);
/*  978 */         this.cCoste.setBounds(new Rectangle(280, 35, 100, 19));
/*  979 */         this.cCoste.setEditable(false);
/*      */       }
/*  981 */       return this.cCoste;
/*      */     }
/*      */ 
/*      */     private void guardarCSV() {
/*  985 */       SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
/*  986 */       JFileChooser fc = new JFileChooser();
/*  987 */       fc.setSelectedFile(new File(new StringBuilder().append("Pedido").append(this.cCodigo.getText()).append(".csv").toString()));
/*  988 */       int retorno = fc.showSaveDialog(this);
/*  989 */       if (retorno == 0) {
/*  990 */         File archivo = fc.getSelectedFile();
/*  991 */         archivo = AddExtension.csv(archivo);
/*  992 */         GrabarFichero salida = new GrabarFichero(archivo);
/*  993 */         TipoSubcuenta cliente = (TipoSubcuenta)this.cProveedor.getSelectedItem();
/*  994 */         String nombreCliente = cliente.getNombre();
/*  995 */         salida.insertar(new StringBuilder().append(this.cCodigo.getText()).append(": ").append(nombreCliente).append("\n").append("Fecha: ").append(sdf.format(this.cFecha.getDate())).append("\n\n").toString());
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
/* 1014 */         formater.format("%s;%s;%s;%s\n", new Object[] { Mensajes.getString("piezas"), this.cPiezas.getText(), Mensajes.getString("importe"), this.cCoste.getText() });
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
/* 1026 */         TipoSubcuenta subcuenta = (TipoSubcuenta)this.cProveedor.getSelectedItem();
/* 1027 */         int origen = 0;
/* 1028 */         if (subcuenta != null) {
/* 1029 */           ManejoSubcuentas mS = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 1030 */           origen = mS.getOrigen(subcuenta.getCodigo()).intValue();
/* 1031 */           mS.cerrarRs();
/*      */         }
/*      */ 
/* 1034 */         double totalBase = 0.0D; double totalIva = 0.0D; double totalTotal = 0.0D;
/* 1035 */         int filaInicial = lsm.getMinSelectionIndex();
/* 1036 */         int filaFinal = lsm.getMaxSelectionIndex();
/* 1037 */         LineaFactura linea = null;
/*      */ 
/* 1039 */         for (int x = filaInicial; x <= filaFinal; x++) {
/* 1040 */           if (this.tabla.isRowSelected(x)) {
/* 1041 */             boolean incluir = true;
/* 1042 */             String referencia = this.tabla.getValueAt(x, 1).toString();
/* 1043 */             Double total = (Double)this.tabla.getValueAt(x, 4);
/* 1044 */             total = doubleTwoDecimals(total);
/* 1045 */             Integer unidades = Integer.valueOf(Integer.parseInt(this.tabla.getValueAt(x, 3).toString()));
/* 1046 */             boolean isRecibido = ((LineaPedidoObject)this.listaPedido.get(x)).isRecibido();
/* 1047 */             if (isRecibido)
/*      */             {
/* 1049 */               int pregunta = JOptionPane.showConfirmDialog(Inicio.frame, new StringBuilder().append(this.tabla.getValueAt(x, 2).toString()).append("\n").append(Mensajes.getString("incluirenviado")).toString(), Mensajes.getString("confirma"), 0);
/*      */ 
/* 1053 */               if (pregunta == 0)
/* 1054 */                 incluir = true;
/*      */               else {
/* 1056 */                 incluir = false;
/*      */               }
/*      */             }
/* 1059 */             if (incluir) {
/* 1060 */               Producto producto = null;
/* 1061 */               AlmacenControl aC = new AlmacenControl();
/* 1062 */               producto = aC.getProducto(referencia, !PedidosClientesJInternalFrame.this.compras);
/* 1063 */               if (producto != null) {
/* 1064 */                 int idTipoIva = aC.getIvaProducto(producto.getId().intValue());
/* 1065 */                 ManejoTiposIVA mT = new ManejoTiposIVA(Inicio.getCGeneral());
/* 1066 */                 TipoIVA tipoIva = mT.getTipoIVA(idTipoIva);
/* 1067 */                 if ((origen != 0) && (mT.getFullTipoCero() != null)) {
/* 1068 */                   tipoIva = mT.getFullTipoCero();
/*      */                 }
/* 1070 */                 mT.cerrarRs();
/* 1071 */                 String concepto = this.tabla.getValueAt(x, 2).toString();
/* 1072 */                 double iva = 0.0D;
/* 1073 */                 double importe = total.doubleValue();
/* 1074 */                 if (tipoIva != null) {
/* 1075 */                   importe = total.doubleValue() / (1.0D + tipoIva.getIva() / 100.0D);
/* 1076 */                   importe = doubleTwoDecimals(Double.valueOf(importe)).doubleValue();
/* 1077 */                   iva = total.doubleValue() - importe;
/*      */                 }
/*      */ 
/* 1080 */                 linea = new LineaFactura(Integer.valueOf(-1), Integer.valueOf(-1), producto, concepto, Double.valueOf(importe), tipoIva, total, Double.valueOf(unidades.doubleValue()));
/* 1081 */                 lineasFactura.add(linea);
/* 1082 */                 totalBase += importe;
/* 1083 */                 totalIva += iva;
/* 1084 */                 totalTotal += total.doubleValue();
/*      */               }
/* 1086 */               aC.cerrarConexion();
/*      */             }
/*      */           }
/*      */         }
/* 1090 */         if ((!lineasFactura.isEmpty()) && 
/* 1091 */           (subcuenta != null)) {
/* 1092 */           ManejoAgenda mA = new ManejoAgenda(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 1093 */           Integer idFormaPago = mA.formaPago(Integer.toString(subcuenta.getCodigo()));
/* 1094 */           mA.cerrarRs();
/* 1095 */           ManejoFormasPago mFP = new ManejoFormasPago(Inicio.getCGeneral());
/* 1096 */           TipoFormaPago formaPago = new TipoFormaPago();
/* 1097 */           if (idFormaPago != null)
/* 1098 */             formaPago = mFP.getFormaPago(idFormaPago.intValue());
/*      */           else {
/* 1100 */             formaPago = mFP.getFirstFormaPago();
/*      */           }
/* 1102 */           mFP.cerrarRs();
/* 1103 */           Factura factura = new Factura(Integer.valueOf(-1), "0", subcuenta, new Date(), Double.valueOf(0.0D), false, formaPago, Double.valueOf(totalBase), Double.valueOf(totalIva), false, false);
/* 1104 */           ArrayList almacenes = new ManejadorAlmacenInterno(PedidosClientesJInternalFrame.this.con).getAlmacenes();
/* 1105 */           PasoAFactura dlg = new PasoAFactura(Inicio.frame, true, factura, lineasFactura, !PedidosClientesJInternalFrame.this.compras, almacenes);
/* 1106 */           Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/* 1107 */           Dimension frameSize = dlg.getSize();
/* 1108 */           if (frameSize.height > screenSize.height) {
/* 1109 */             frameSize.height = screenSize.height;
/*      */           }
/* 1111 */           if (frameSize.width > screenSize.width) {
/* 1112 */             frameSize.width = screenSize.width;
/*      */           }
/* 1114 */           dlg.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
/*      */ 
/* 1116 */           dlg.pack();
/* 1117 */           dlg.setVisible(true);
/* 1118 */           if (dlg.isIsOk()) {
/* 1119 */             for (int x = filaInicial; x <= filaFinal; x++) {
/* 1120 */               if (this.tabla.isRowSelected(x)) {
/* 1121 */                 boolean isRecibido = ((LineaPedidoObject)this.listaPedido.get(x)).isRecibido();
/* 1122 */                 int id = Integer.parseInt(this.tabla.getValueAt(x, 0).toString());
/* 1123 */                 new Productos(PedidosClientesJInternalFrame.this.con).marcarRecibido(id, true);
/* 1124 */                 if (!isRecibido)
/* 1125 */                   this.modelo.changeState(x);
/*      */               }
/*      */             }
/* 1128 */             this.modelo.fireTableDataChanged();
/* 1129 */             this.tabla.repaint();
/*      */           }
/*      */         }
/*      */ 
/* 1133 */         System.gc();
/*      */       } else {
/* 1135 */         JOptionPane.showMessageDialog(Inicio.frame, Mensajes.getString("selectproductos"), Mensajes.getString("informacion"), 1);
/*      */       }
/*      */     }
/*      */ 
/*      */     private Double doubleTwoDecimals(Double number) {
/* 1140 */       if (number != null) {
/* 1141 */         BigDecimal dec = new BigDecimal(number.doubleValue());
/*      */ 
/* 1143 */         return Double.valueOf(dec.setScale(2, 4).doubleValue());
/*      */       }
/* 1145 */       return Double.valueOf(-1.0D);
/*      */     }
/*      */   }
/*      */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.gui.pedidos.PedidosClientesJInternalFrame
 * JD-Core Version:    0.6.2
 */