/*      */ package pos.view;
/*      */ 
/*      */ import almacen2.data.AlmacenInterno;
/*      */ import almacen2.data.ManejadorAlmacenInterno;
/*      */ import almacen2.data.ManejadorProducto;
/*      */ import almacen2.data.ProductObject;
/*      */ import contaes.Inicio;
/*      */ import contaes.MarcoInicio;
/*      */ import contaes.Puente;
/*      */ import contaes.auxiliarTablas.BooleanColorRenderer;
/*      */ import contaes.auxiliarTablas.DerechaColorRenderer;
/*      */ import contaes.auxiliarTablas.GeneralColorRenderer;
/*      */ import contaes.auxiliarTablas.ImporteColorRenderer;
/*      */ import contaes.manejoDatos.ManejoSubcuentas;
/*      */ import contaes.manejoDatos.TipoSubcuenta;
/*      */ import internationalization.Mensajes;
/*      */ import java.awt.Container;
/*      */ import java.awt.Font;
/*      */ import java.awt.Toolkit;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.ActionListener;
/*      */ import java.awt.event.KeyAdapter;
/*      */ import java.awt.event.KeyEvent;
/*      */ import java.awt.event.MouseAdapter;
/*      */ import java.awt.event.MouseEvent;
/*      */ import java.awt.print.PrinterException;
/*      */ import java.awt.print.PrinterJob;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Date;
import java.util.List;
/*      */ import java.util.ResourceBundle;
/*      */ import java.util.logging.Level;
/*      */ import java.util.logging.Logger;
/*      */ import javax.swing.BorderFactory;
/*      */ import javax.swing.DefaultComboBoxModel;
/*      */ import javax.swing.GroupLayout;
/*      */ import javax.swing.GroupLayout.Alignment;
/*      */ import javax.swing.GroupLayout.ParallelGroup;
/*      */ import javax.swing.GroupLayout.SequentialGroup;
/*      */ import javax.swing.ImageIcon;
/*      */ import javax.swing.JButton;
/*      */ import javax.swing.JComboBox;
/*      */ import javax.swing.JInternalFrame;
/*      */ import javax.swing.JLabel;
/*      */ import javax.swing.JOptionPane;
/*      */ import javax.swing.JPanel;
/*      */ import javax.swing.JScrollPane;
/*      */ import javax.swing.JTable;
/*      */ import javax.swing.JTextField;
import javax.swing.LayoutStyle;
/*      */ import javax.swing.LayoutStyle.ComponentPlacement;
/*      */ import javax.swing.ListSelectionModel;
/*      */ import javax.swing.border.SoftBevelBorder;
/*      */ import javax.swing.event.InternalFrameEvent;
/*      */ import javax.swing.event.InternalFrameListener;
/*      */ import javax.swing.table.TableColumn;
/*      */ import javax.swing.table.TableColumnModel;
/*      */ import pos.control.MedioPagoControl;
/*      */ import pos.control.TerminalVentasDefaults;
/*      */ import pos.control.TicketImprimible;
/*      */ import pos.control.TicketImprimiblePlazos;
/*      */ import pos.control.TicketImprimiblePlazosFin;
/*      */ import pos.control.TicketsControl;
/*      */ import pos.control.VendedorControl;
/*      */ import pos.control.VentaPosControl;
/*      */ import pos.model.BasicException;
/*      */ import pos.model.LineaVentaPOSTableModel;
/*      */ import pos.model.MedioPago;
/*      */ import pos.model.ObjetoMultifuncional;
/*      */ import pos.model.Ticket;
/*      */ import pos.model.Vendedor;
/*      */ import pos.model.VentaPOS;
/*      */ import pos.view.calendar.JCalendarDialog;
/*      */ 
/*      */ public class TerminalVentas extends JInternalFrame
/*      */ {
/*      */   private static final int NUMBERZERO = 0;
/*      */   private static final int NUMBERVALID = 1;
/*      */   private static final int NUMBER_INPUTZERO = 0;
/*      */   private static final int NUMBER_INPUTZERODEC = 1;
/*      */   private static final int NUMBER_INPUTINT = 2;
/*      */   private static final int NUMBER_INPUTDEC = 3;
/*      */   private static final int NUMBER_PORZERO = 4;
/*      */   private static final int NUMBER_PORZERODEC = 5;
/*      */   private static final int NUMBER_PORINT = 6;
/*      */   private static final int NUMBER_PORDEC = 7;
/*      */   private int m_iNumberStatus;
/*      */   private int m_iNumberStatusInput;
/*      */   private int m_iNumberStatusPor;
/*   79 */   private int idTicket = -1;
/*   80 */   private int numeroTicket = 0;
/*      */   private Date fecha;
/*   82 */   private int totalUnidades = 0;
/*   83 */   private double totalImporte = 0.0D;
/*   84 */   private int cerrado = 0;
/*      */   private LineaVentaPOSTableModel modelo;
/*      */   private ObjetoMultifuncional cliente;
/*      */   private ObjetoMultifuncional vendedor;
/*      */   private ObjetoMultifuncional medioPago;
/*      */   private ObjetoMultifuncional almacen;
/*      */   private JButton botonAddProduct;
/*      */   private JButton botonBorrar;
/*      */   private JButton botonBuscar;
/*      */   private JButton botonEditar;
/*      */   private JButton botonImprimirTicket;
/*      */   private JButton botonLimpiarTicket;
/*      */   private JButton botonNotas;
/*      */   private JButton botonNuevoTicket;
/*      */   private JButton botonSeleccionarAlmacen;
/*      */   private JButton botonSeleccionarCliente;
/*      */   private JButton botonSeleccionarMedioPago;
/*      */   private JButton botonSeleccionarVendedor;
/*      */   private JButton botonTablaAbajo;
/*      */   private JButton botonTablaArriba;
/*      */   private JTextField campoNumero;
/*      */   private JTextField campoReferer;
/*      */   private JLabel campoTotalImporte;
/*      */   private JLabel campoTotalUnidades;
/*      */   private JTextField campoUnidades;
/*      */   private JLabel fechaJLabel;
/*      */   private JComboBox formasJComboBox;
/*      */   private JButton jButton1;
/*      */   private JLabel jLabel1;
/*      */   private JLabel jLabel3;
/*      */   private JLabel jLabel4;
/*      */   private JLabel jLabel8;
/*      */   private JNumberKeys jNumberKeys1;
/*      */   private JPanel jPanel1;
/*      */   private JPanel jPanel2;
/*      */   private JPanel jPanel3;
/*      */   private JPanel jPanel4;
/*      */   private JScrollPane jScrollPane1;
/*      */   private JLabel nombreAlmacen;
/*      */   private JLabel nombreCliente;
/*      */   private JLabel nombreMedioPago;
/*      */   private JLabel nombreVendedor;
/*      */   private JTable tablaVentas;
/*      */ 
/*      */   public TerminalVentas()
/*      */   {
/*   94 */     initComponents();
/*      */ 
/*   96 */     this.fecha = new Date();
/*   97 */     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
/*   98 */     this.fechaJLabel.setText(sdf.format(this.fecha));
/*      */ 
/*  100 */     setTableProperties();
/*  101 */     limpiarReferer();
/*      */ 
/*  103 */     TerminalVentasDefaults tvd = new TerminalVentasDefaults();
/*  104 */     if (tvd.getCliente() != null) {
/*  105 */       this.cliente = tvd.getCliente();
/*  106 */       this.nombreCliente.setText(this.cliente.getNombre());
/*      */     }
/*  108 */     if (tvd.getVendedor() != null) {
/*  109 */       this.vendedor = tvd.getVendedor();
/*  110 */       this.nombreVendedor.setText(this.vendedor.getNombre());
/*      */     }
/*  112 */     if (tvd.getAlmacen() != null) {
/*  113 */       this.almacen = tvd.getAlmacen();
/*  114 */       this.nombreAlmacen.setText(this.almacen.getNombre());
/*      */     }
/*  116 */     if (tvd.getMedioPago() != null) {
/*  117 */       this.medioPago = tvd.getMedioPago();
/*  118 */       this.nombreMedioPago.setText(this.medioPago.getNombre());
/*      */     }
/*      */   }
/*      */ 
/*      */   private void setTableProperties() {
/*  123 */     if (this.modelo == null) {
/*  124 */       this.modelo = new LineaVentaPOSTableModel();
/*      */     }
/*  126 */     this.tablaVentas.setModel(this.modelo);
/*  127 */     int anchoTabla = 395;
/*  128 */     TableColumn columna = this.tablaVentas.getColumnModel().getColumn(0);
/*  129 */     columna.setPreferredWidth((int)(anchoTabla * 0.05D));
/*  130 */     columna = this.tablaVentas.getColumnModel().getColumn(1);
/*  131 */     columna.setPreferredWidth((int)(anchoTabla * 0.35D));
/*  132 */     columna.setCellRenderer(new GeneralColorRenderer());
/*  133 */     columna = this.tablaVentas.getColumnModel().getColumn(2);
/*  134 */     columna.setPreferredWidth((int)(anchoTabla * 0.15D));
/*  135 */     columna.setCellRenderer(new DerechaColorRenderer());
/*  136 */     columna = this.tablaVentas.getColumnModel().getColumn(3);
/*  137 */     columna.setPreferredWidth((int)(anchoTabla * 0.2D));
/*  138 */     columna.setCellRenderer(new ImporteColorRenderer());
/*  139 */     columna = this.tablaVentas.getColumnModel().getColumn(4);
/*  140 */     columna.setPreferredWidth((int)(anchoTabla * 0.2D));
/*  141 */     columna.setCellRenderer(new ImporteColorRenderer());
/*  142 */     columna = this.tablaVentas.getColumnModel().getColumn(5);
/*  143 */     columna.setPreferredWidth((int)(anchoTabla * 0.05D));
/*  144 */     columna.setCellRenderer(new BooleanColorRenderer());
/*  145 */     this.tablaVentas.setDefaultRenderer(Object.class, new GeneralColorRenderer());
/*      */ 
/*  147 */     this.jScrollPane1.setViewportView(this.tablaVentas);
/*      */   }
/*      */ 
/*      */   private void addLineToTable(VentaPOS linea, int pos) {
/*  151 */     if (pos >= 0) {
/*  152 */       this.modelo.addRow(linea, pos);
/*      */     }
/*      */     else {
/*  155 */       this.modelo.addRow(linea);
/*      */     }
/*  157 */     this.modelo.fireTableDataChanged();
/*  158 */     this.tablaVentas.repaint();
/*  159 */     if (pos >= 0) {
/*  160 */       this.tablaVentas.changeSelection(pos, 0, false, false);
/*      */     }
/*      */     else {
/*  163 */       this.tablaVentas.changeSelection(this.modelo.getRowCount() - 1, 0, false, false);
/*      */     }
/*  165 */     this.totalUnidades += linea.getUnidades();
/*  166 */     this.totalImporte += linea.getTotal();
/*  167 */     this.campoTotalUnidades.setText(Integer.toString(this.totalUnidades));
/*  168 */     this.campoTotalImporte.setText(String.format("%,15.2f %s", new Object[] { Double.valueOf(this.totalImporte), Mensajes.getString("moneda") }));
/*      */   }
/*      */ 
/*      */   private void removeLineFromTable(int rowIndex)
/*      */   {
/*  173 */     VentaPOS linea = this.modelo.getObjectAt(rowIndex);
/*  174 */     this.modelo.removeRow(rowIndex);
/*  175 */     this.modelo.fireTableDataChanged();
/*  176 */     this.tablaVentas.repaint();
/*  177 */     if (rowIndex - 1 >= 0) {
/*  178 */       this.tablaVentas.changeSelection(rowIndex - 1, 0, false, false);
/*      */     }
/*  180 */     this.totalUnidades -= linea.getUnidades();
/*  181 */     this.totalImporte -= linea.getTotal();
/*  182 */     this.campoTotalUnidades.setText(Integer.toString(this.totalUnidades));
/*  183 */     this.campoTotalImporte.setText(String.format("%,15.2f %s", new Object[] { Double.valueOf(this.totalImporte), Mensajes.getString("moneda") }));
/*      */   }
/*      */ 
/*      */   private void generarTicket(boolean print) {
/*  187 */     boolean continuar = true;
/*  188 */     if (this.cerrado != 0) {
/*  189 */       int confirma = JOptionPane.showConfirmDialog(Inicio.frame, "Este ticket está ya cerrado.\n¿Seguro que desea modificarlo?", "AVISO", 0);
/*      */ 
/*  192 */       if (confirma == 1) {
/*  193 */         continuar = false;
/*      */       }
/*      */     }
/*  196 */     if ((continuar) && 
/*  197 */       (this.numeroTicket >= 0))
/*  198 */       if (this.cliente != null) {
/*  199 */         if (this.vendedor != null) {
/*  200 */           if (this.medioPago != null) {
/*  201 */             if (this.almacen != null) {
/*  202 */               int plazos = this.formasJComboBox.getSelectedIndex();
/*  203 */               Integer idTicketAnterior = null;
/*  204 */               if (plazos > 0) {
/*  205 */                 JOptionPane.showMessageDialog(getContentPane(), Mensajes.getString("seleccionplazo"), Mensajes.getString("informacion"), 1);
/*  206 */                 idTicketAnterior = BusquedaTicketJDialog.obtenerObjeto(Inicio.frame, true);
/*      */               }
/*  208 */               Ticket ticket = new Ticket(Integer.valueOf(this.idTicket), this.numeroTicket, this.fecha, this.almacen.getId(), this.medioPago.getId(), plazos, this.vendedor.getId(), this.cliente.getId(), 0, idTicketAnterior);
/*      */ 
/*  210 */               if (ticket != null) {
/*  211 */                 TicketsControl tC = new TicketsControl(Inicio.getcAlmacen());
/*  212 */                 if (this.idTicket != -1)
/*  213 */                   tC.modificar(ticket);
/*      */                 else {
/*  215 */                   this.idTicket = tC.crear(ticket);
/*      */                 }
/*  217 */                 tC.cerrarRs();
/*  218 */                 if (this.idTicket != -1) {
/*  219 */                   VentaPosControl vPC = new VentaPosControl(Inicio.getcAlmacen());
/*  220 */                   ArrayList ventas = this.modelo.getObjetos();
/*  221 */                   for (VentaPOS ventaPOS :(List<VentaPOS>) ventas) {
/*  222 */                     ventaPOS.setIdTicket(this.idTicket);
/*  223 */                     if (ventaPOS.getId().intValue() != -1) {
/*  224 */                       vPC.modificar(ventaPOS);
/*      */                     } else {
/*  226 */                       int id = vPC.crear(ventaPOS);
/*  227 */                       ventaPOS.setId(Integer.valueOf(id));
/*      */                     }
/*      */                   }
/*  230 */                   vPC.cerrarRs();
/*  231 */                   Double entregado = PagoCajaJDialog.obtenerObjeto(Inicio.frame, true, Double.valueOf(this.totalImporte));
/*  232 */                   if (print) {
/*  233 */                     TicketImprimible imprimir = null;
/*  234 */                     switch (plazos) {
/*      */                     case 1:
/*  236 */                       imprimir = new TicketImprimiblePlazos(ticket, ventas, this.vendedor.getNombre(), this.medioPago.getNombre(), entregado);
/*  237 */                       break;
/*      */                     case 2:
/*  239 */                       imprimir = new TicketImprimiblePlazosFin(ticket, ventas, this.vendedor.getNombre(), this.medioPago.getNombre(), entregado);
/*  240 */                       break;
/*      */                     default:
/*  242 */                       imprimir = new TicketImprimible(ticket, ventas, this.vendedor.getNombre(), this.medioPago.getNombre(), entregado);
/*      */                     }
/*      */ 
/*  245 */                     PrinterJob printJob = PrinterJob.getPrinterJob();
/*  246 */                     printJob.setPrintable(imprimir);
/*      */                     try {
/*  248 */                       printJob.print();
/*      */                     } catch (PrinterException ex) {
/*  250 */                       ex.printStackTrace();
/*      */                     }
/*      */                   }
/*      */                 }
/*  254 */                 limpiarForm();
/*  255 */                 colocarNuevoNumero();
/*      */               }
/*      */             }
/*      */             else {
/*  259 */               mostrarError(Mensajes.getString("noselecciono") + " " + Mensajes.getString("almacen"));
/*      */             }
/*      */           }
/*  262 */           else mostrarError(Mensajes.getString("noselecciono") + " " + Mensajes.getString("formapago"));
/*      */         }
/*      */         else
/*  265 */           mostrarError(Mensajes.getString("noselecciono") + " " + Mensajes.getString("vendedor"));
/*      */       }
/*      */       else
/*  268 */         mostrarError(Mensajes.getString("noselecciono") + " " + Mensajes.getString("cliente"));
/*      */   }
/*      */ 
/*      */   public void cargarTicket(int id)
/*      */   {
/*  277 */     limpiarForm();
/*  278 */     TicketsControl tC = new TicketsControl(Inicio.getcAlmacen());
/*  279 */     Ticket ticket = tC.getTicket(id);
/*  280 */     tC.cerrarRs();
/*  281 */     VentaPosControl vPS = new VentaPosControl(Inicio.getcAlmacen());
/*  282 */     ArrayList lineas = vPS.getTodasVentasPosPorTicket(id);
/*  283 */     vPS.cerrarRs();
/*  284 */     this.idTicket = ticket.getId().intValue();
/*  285 */     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
/*  286 */     this.numeroTicket = ticket.getNumero();
/*  287 */     this.campoNumero.setText(Integer.toString(this.numeroTicket));
/*  288 */     this.fecha = ticket.getFecha();
/*  289 */     this.fechaJLabel.setText(sdf.format(this.fecha));
/*  290 */     this.cerrado = ticket.isCerrado();
/*      */ 
/*  292 */     ManejoSubcuentas mS = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  293 */     TipoSubcuenta subcuenta = mS.datos(ticket.getCliente());
/*  294 */     mS.cerrarRs();
/*  295 */     if (subcuenta != null) {
/*  296 */       this.cliente = new ObjetoMultifuncional(ticket.getCliente(), subcuenta.getNombre());
/*  297 */       this.nombreCliente.setText(this.cliente.getNombre());
/*      */     }
/*      */ 
/*  300 */     VendedorControl vC = new VendedorControl(Inicio.getcAlmacen());
/*  301 */     Vendedor v = vC.getVendedor(ticket.getVendedor());
/*  302 */     vC.cerrarRs();
/*  303 */     if (v != null) {
/*  304 */       this.vendedor = new ObjetoMultifuncional(ticket.getVendedor(), v.getNombre());
/*  305 */       this.nombreVendedor.setText(this.vendedor.getNombre());
/*      */     }
/*      */ 
/*  308 */     ManejadorAlmacenInterno mAI = new ManejadorAlmacenInterno(Inicio.getcAlmacen());
/*  309 */     AlmacenInterno al = mAI.getObjeto(ticket.getAlmacen());
/*  310 */     mAI.cerrarRs();
/*  311 */     if (al != null) {
/*  312 */       this.almacen = new ObjetoMultifuncional(ticket.getAlmacen(), al.getNombre());
/*  313 */       this.nombreAlmacen.setText(this.almacen.getNombre());
/*      */     }
/*      */ 
/*  316 */     MedioPagoControl mPC = new MedioPagoControl(Inicio.getcAlmacen());
/*  317 */     MedioPago medio = mPC.getMedioPago(ticket.getMedioPago());
/*  318 */     mPC.cerrarRs();
/*  319 */     if (medio != null) {
/*  320 */       this.medioPago = new ObjetoMultifuncional(ticket.getMedioPago(), medio.getNombre());
/*  321 */       this.nombreMedioPago.setText(this.medioPago.getNombre());
/*      */     }
/*      */ 
/*  324 */     this.formasJComboBox.setSelectedIndex(ticket.getPlazos());
/*      */ 
/*  326 */     for (VentaPOS ventaPOS :(List<VentaPOS>) lineas)
/*  327 */       addLineToTable(ventaPOS, -1);
/*      */   }
/*      */ 
/*      */   private void colocarNuevoNumero()
/*      */   {
/*  333 */     TicketsControl tC = new TicketsControl(Inicio.getcAlmacen());
/*  334 */     this.numeroTicket = tC.getNuevoNumero();
/*  335 */     tC.cerrarRs();
/*  336 */     this.campoNumero.setText(Integer.toString(this.numeroTicket));
/*      */   }
/*      */ 
/*      */   private void limpiarReferer() {
/*  340 */     this.campoReferer.setText("");
/*  341 */     this.campoUnidades.setText("");
/*      */ 
/*  343 */     this.m_iNumberStatus = 0;
/*  344 */     this.m_iNumberStatusInput = 0;
/*  345 */     this.m_iNumberStatusPor = 0;
/*      */   }
/*      */ 
/*      */   private void limpiarForm() {
/*  349 */     limpiarReferer();
/*  350 */     for (int i = this.modelo.getRowCount() - 1; i >= 0; i--) {
/*  351 */       this.modelo.removeRow(i);
/*      */     }
/*  353 */     this.modelo.fireTableDataChanged();
/*  354 */     this.tablaVentas.repaint();
/*  355 */     this.idTicket = -1;
/*  356 */     this.totalUnidades = 0;
/*  357 */     this.totalImporte = 0.0D;
/*  358 */     this.cerrado = 0;
/*  359 */     this.campoTotalUnidades.setText("");
/*  360 */     this.campoTotalImporte.setText("");
/*  361 */     this.fecha = new Date();
/*  362 */     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
/*  363 */     this.fechaJLabel.setText(sdf.format(this.fecha));
/*      */   }
/*      */ 
/*      */   private double getPorValue() {
/*      */     try {
/*  368 */       return Double.parseDouble(this.campoUnidades.getText().substring(1));
/*      */     } catch (NumberFormatException e) {
/*  370 */       return 1.0D; } catch (StringIndexOutOfBoundsException e) {
/*      */     }
/*  372 */     return 1.0D;
/*      */   }
/*      */ 
/*      */   private boolean isDouble(String str)
/*      */   {
/*      */     try {
/*  378 */       Double.parseDouble(str);
/*  379 */       return true;
/*      */     }
/*      */     catch (NumberFormatException e) {
/*      */     }
/*  383 */     return false;
/*      */   }
/*      */ 
/*      */   private void calcOperation(char cTrans) {
/*  387 */     if (cTrans == '\n')
/*      */     {
/*  389 */       String refer = this.campoReferer.getText();
/*  390 */       if (refer.length() > 0) {
/*  391 */         String referencia = this.campoReferer.getText();
/*  392 */         if (!referencia.equals("")) {
/*  393 */           ProductObject producto = selecccionarProducto(referencia);
/*  394 */           introducirProducto(producto);
/*      */         }
/*      */       } else {
/*  397 */         Toolkit.getDefaultToolkit().beep();
/*      */       }
/*      */ 
/*      */     }
/*  403 */     else if (cTrans == '') {
/*  404 */       limpiarReferer();
/*      */     }
/*  406 */     else if ((cTrans == '0') && (this.m_iNumberStatus == 0))
/*      */     {
/*  408 */       this.campoReferer.setText("0");
/*  409 */     } else if (((cTrans == '1') || (cTrans == '2') || (cTrans == '3') || (cTrans == '4') || (cTrans == '5') || (cTrans == '6') || (cTrans == '7') || (cTrans == '8') || (cTrans == '9')) && (this.m_iNumberStatus == 0))
/*      */     {
/*  412 */       this.campoReferer.setText(this.campoReferer.getText() + cTrans);
/*      */ 
/*  414 */       this.m_iNumberStatus = 2;
/*  415 */       this.m_iNumberStatusInput = 1;
/*  416 */     } else if (((cTrans == '0') || (cTrans == '1') || (cTrans == '2') || (cTrans == '3') || (cTrans == '4') || (cTrans == '5') || (cTrans == '6') || (cTrans == '7') || (cTrans == '8') || (cTrans == '9')) && (this.m_iNumberStatus == 2))
/*      */     {
/*  419 */       this.campoReferer.setText(this.campoReferer.getText() + cTrans);
/*      */     }
/*  421 */     else if ((cTrans == '.') && (this.m_iNumberStatus == 0)) {
/*  422 */       this.campoReferer.setText("0.");
/*  423 */       this.m_iNumberStatus = 1;
/*  424 */     } else if ((cTrans == '.') && (this.m_iNumberStatus == 2)) {
/*  425 */       this.campoReferer.setText(this.campoReferer.getText() + ".");
/*  426 */       this.m_iNumberStatus = 3;
/*      */     }
/*  428 */     else if ((cTrans == '0') && ((this.m_iNumberStatus == 1) || (this.m_iNumberStatus == 3)))
/*      */     {
/*  431 */       this.campoReferer.setText(this.campoReferer.getText() + cTrans);
/*  432 */     } else if (((cTrans == '1') || (cTrans == '2') || (cTrans == '3') || (cTrans == '4') || (cTrans == '5') || (cTrans == '6') || (cTrans == '7') || (cTrans == '8') || (cTrans == '9')) && ((this.m_iNumberStatus == 1) || (this.m_iNumberStatus == 3)))
/*      */     {
/*  435 */       this.campoReferer.setText(this.campoReferer.getText() + cTrans);
/*  436 */       this.m_iNumberStatus = 3;
/*  437 */       this.m_iNumberStatusInput = 1;
/*      */     }
/*  439 */     else if ((cTrans == '*') && ((this.m_iNumberStatus == 2) || (this.m_iNumberStatus == 3)))
/*      */     {
/*  441 */       this.campoUnidades.setText("x");
/*  442 */       this.m_iNumberStatus = 4;
/*  443 */     } else if ((cTrans == '*') && ((this.m_iNumberStatus == 0) || (this.m_iNumberStatus == 1)))
/*      */     {
/*  445 */       this.campoReferer.setText("0");
/*  446 */       this.campoUnidades.setText("x");
/*  447 */       this.m_iNumberStatus = 4;
/*      */     }
/*  449 */     else if ((cTrans == '0') && (this.m_iNumberStatus == 4))
/*      */     {
/*  451 */       this.campoUnidades.setText("x0");
/*  452 */     } else if (((cTrans == '1') || (cTrans == '2') || (cTrans == '3') || (cTrans == '4') || (cTrans == '5') || (cTrans == '6') || (cTrans == '7') || (cTrans == '8') || (cTrans == '9')) && (this.m_iNumberStatus == 4))
/*      */     {
/*  455 */       this.campoUnidades.setText("x" + Character.toString(cTrans));
/*  456 */       this.m_iNumberStatus = 6;
/*  457 */       this.m_iNumberStatusPor = 1;
/*  458 */     } else if (((cTrans == '0') || (cTrans == '1') || (cTrans == '2') || (cTrans == '3') || (cTrans == '4') || (cTrans == '5') || (cTrans == '6') || (cTrans == '7') || (cTrans == '8') || (cTrans == '9')) && (this.m_iNumberStatus == 6))
/*      */     {
/*  461 */       this.campoUnidades.setText(this.campoUnidades.getText() + cTrans);
/*      */     }
/*  463 */     else if ((cTrans == '.') && (this.m_iNumberStatus == 4)) {
/*  464 */       this.campoUnidades.setText("x0.");
/*  465 */       this.m_iNumberStatus = 5;
/*  466 */     } else if ((cTrans == '.') && (this.m_iNumberStatus == 6)) {
/*  467 */       this.campoUnidades.setText(this.campoUnidades.getText() + ".");
/*  468 */       this.m_iNumberStatus = 7;
/*      */     }
/*  470 */     else if ((cTrans == '0') && ((this.m_iNumberStatus == 5) || (this.m_iNumberStatus == 7)))
/*      */     {
/*  473 */       this.campoUnidades.setText(this.campoUnidades.getText() + cTrans);
/*  474 */     } else if (((cTrans == '1') || (cTrans == '2') || (cTrans == '3') || (cTrans == '4') || (cTrans == '5') || (cTrans == '6') || (cTrans == '7') || (cTrans == '8') || (cTrans == '9')) && ((this.m_iNumberStatus == 5) || (this.m_iNumberStatus == 7)))
/*      */     {
/*  477 */       this.campoUnidades.setText(this.campoUnidades.getText() + cTrans);
/*  478 */       this.m_iNumberStatus = 7;
/*  479 */       this.m_iNumberStatusPor = 1;
/*      */     }
/*  481 */     else if ((cTrans == '+') && (this.m_iNumberStatusInput == 0) && (this.m_iNumberStatusPor == 0))
/*      */     {
/*  483 */       int i = this.tablaVentas.getSelectionModel().getMinSelectionIndex();
/*  484 */       if (i < 0) {
/*  485 */         Toolkit.getDefaultToolkit().beep();
/*      */       } else {
/*  487 */         int rowIndex = this.tablaVentas.getSelectedRow();
/*  488 */         VentaPOS linea = this.modelo.getObjectAt(rowIndex);
/*  489 */         removeLineFromTable(rowIndex);
/*  490 */         linea.setUnidades(linea.getUnidades() + 1);
/*  491 */         linea.recalcularTotal();
/*  492 */         addLineToTable(linea, rowIndex);
/*  493 */         limpiarReferer();
/*      */       }
/*      */ 
/*      */     }
/*  497 */     else if ((cTrans == '-') && (this.m_iNumberStatusInput == 0) && (this.m_iNumberStatusPor == 0))
/*      */     {
/*  500 */       int i = this.tablaVentas.getSelectionModel().getMinSelectionIndex();
/*  501 */       if (i < 0) {
/*  502 */         Toolkit.getDefaultToolkit().beep();
/*      */       } else {
/*  504 */         int rowIndex = this.tablaVentas.getSelectedRow();
/*  505 */         VentaPOS linea = this.modelo.getObjectAt(rowIndex);
/*  506 */         removeLineFromTable(rowIndex);
/*  507 */         linea.setUnidades(linea.getUnidades() - 1);
/*  508 */         linea.recalcularTotal();
/*  509 */         addLineToTable(linea, rowIndex);
/*      */       }
/*      */ 
/*      */     }
/*  513 */     else if ((cTrans == '+') && (this.m_iNumberStatusInput == 0) && (this.m_iNumberStatusPor == 1))
/*      */     {
/*  515 */       int i = this.tablaVentas.getSelectionModel().getMinSelectionIndex();
/*  516 */       if (i < 0) {
/*  517 */         Toolkit.getDefaultToolkit().beep();
/*      */       } else {
/*  519 */         Double dPor = Double.valueOf(getPorValue());
/*  520 */         int rowIndex = this.tablaVentas.getSelectedRow();
/*  521 */         VentaPOS linea = this.modelo.getObjectAt(rowIndex);
/*  522 */         removeLineFromTable(rowIndex);
/*  523 */         linea.setUnidades(dPor.intValue());
/*  524 */         linea.recalcularTotal();
/*  525 */         addLineToTable(linea, rowIndex);
/*  526 */         limpiarReferer();
/*      */       }
/*      */ 
/*      */     }
/*  530 */     else if ((cTrans == '-') && (this.m_iNumberStatusInput == 0) && (this.m_iNumberStatusPor == 1))
/*      */     {
/*  533 */       int i = this.tablaVentas.getSelectionModel().getMinSelectionIndex();
/*  534 */       if (i < 0) {
/*  535 */         Toolkit.getDefaultToolkit().beep();
/*      */       } else {
/*  537 */         Double dPor = Double.valueOf(getPorValue());
/*  538 */         int rowIndex = this.tablaVentas.getSelectedRow();
/*  539 */         VentaPOS linea = this.modelo.getObjectAt(rowIndex);
/*  540 */         removeLineFromTable(rowIndex);
/*  541 */         linea.setUnidades(-1 * dPor.intValue());
/*  542 */         linea.recalcularTotal();
/*  543 */         addLineToTable(linea, rowIndex);
/*  544 */         limpiarReferer();
/*      */       }
/*      */ 
/*      */     }
/*  548 */     else if ((cTrans == '+') && (this.m_iNumberStatusInput == 1) && (this.m_iNumberStatusPor == 0))
/*      */     {
/*  550 */       int i = this.tablaVentas.getSelectionModel().getMinSelectionIndex();
/*  551 */       if (i < 0) {
/*  552 */         Toolkit.getDefaultToolkit().beep();
/*      */       } else {
/*  554 */         String nuevoImporte = this.campoReferer.getText();
/*  555 */         if (isDouble(nuevoImporte)) {
/*  556 */           int rowIndex = this.tablaVentas.getSelectedRow();
/*  557 */           VentaPOS linea = this.modelo.getObjectAt(rowIndex);
/*  558 */           removeLineFromTable(rowIndex);
/*  559 */           linea.setImporte(Double.valueOf(nuevoImporte).doubleValue());
/*  560 */           linea.recalcularTotal();
/*  561 */           addLineToTable(linea, rowIndex);
/*  562 */           limpiarReferer();
/*      */         }
/*      */       }
/*      */     }
/*  566 */     else if ((cTrans == '-') && (this.m_iNumberStatusInput == 1) && (this.m_iNumberStatusPor == 0))
/*      */     {
/*  568 */       int i = this.tablaVentas.getSelectionModel().getMinSelectionIndex();
/*  569 */       if (i < 0) {
/*  570 */         Toolkit.getDefaultToolkit().beep();
/*      */       } else {
/*  572 */         String nuevoImporte = this.campoReferer.getText();
/*  573 */         if (isDouble(nuevoImporte)) {
/*  574 */           int rowIndex = this.tablaVentas.getSelectedRow();
/*  575 */           VentaPOS linea = this.modelo.getObjectAt(rowIndex);
/*  576 */           removeLineFromTable(rowIndex);
/*  577 */           linea.setImporte(-1.0D * Double.valueOf(nuevoImporte).doubleValue());
/*  578 */           linea.recalcularTotal();
/*  579 */           addLineToTable(linea, rowIndex);
/*  580 */           limpiarReferer();
/*      */         }
/*      */       }
/*      */     }
/*  584 */     else if ((cTrans != '+') || (this.m_iNumberStatusInput != 1) || (this.m_iNumberStatusPor != 1))
/*      */     {
/*  588 */       if ((cTrans != '-') || (this.m_iNumberStatusInput != 1) || (this.m_iNumberStatusPor != 1))
/*      */       {
/*  593 */         if (((cTrans == ' ') || (cTrans == '=')) && 
/*  594 */           (this.tablaVentas.getRowCount() <= 0))
/*      */         {
/*  597 */           Toolkit.getDefaultToolkit().beep();
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private ProductObject selecccionarProducto(String referencia) {
/*  604 */     ProductObject producto = null;
/*  605 */     ManejadorProducto mP = new ManejadorProducto(Inicio.getcAlmacen());
/*  606 */     producto = mP.obtenerProducto(referencia);
/*      */ 
/*  612 */     return producto;
/*      */   }
/*      */ 
/*      */   private void introducirProducto(ProductObject producto) {
/*  616 */     if (producto != null) {
/*  617 */       String descripcion = producto.getDescripcion();
/*      */ 
/*  626 */       VentaPOS venta = new VentaPOS(Integer.valueOf(-1), -1, producto.getId(), descripcion, 1, producto.getPvp(), "");
/*  627 */       addLineToTable(venta, -1);
/*  628 */       limpiarReferer();
/*      */     }
/*      */   }
/*      */ 
/*      */   private void mostrarError(String error) {
/*  633 */     JOptionPane.showMessageDialog(getContentPane(), error, "Error", 0);
/*      */   }
/*      */ 
/*      */   private String getNotaFromSelectedRow() {
/*  637 */     String nota = "";
/*  638 */     int fila = this.tablaVentas.getSelectedRow();
/*  639 */     if (fila != -1) {
/*  640 */       VentaPOS venta = this.modelo.getObjectAt(fila);
/*  641 */       nota = venta.getNota();
/*      */     }
/*  643 */     return nota;
/*      */   }
/*      */ 
/*      */   private void setNotaToSelectedRow(String nota) {
/*  647 */     int fila = this.tablaVentas.getSelectedRow();
/*  648 */     if (fila != -1) {
/*  649 */       VentaPOS venta = this.modelo.getObjectAt(fila);
/*  650 */       venta.setNota(nota);
/*  651 */       removeLineFromTable(fila);
/*  652 */       addLineToTable(venta, fila);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void initComponents()
/*      */   {
/*  665 */     this.jLabel1 = new JLabel();
/*  666 */     this.campoNumero = new JTextField();
/*  667 */     this.jScrollPane1 = new JScrollPane();
/*  668 */     this.tablaVentas = new JTable();
/*  669 */     this.jLabel3 = new JLabel();
/*  670 */     this.jLabel4 = new JLabel();
/*  671 */     this.botonBorrar = new JButton();
/*  672 */     this.botonEditar = new JButton();
/*  673 */     this.botonAddProduct = new JButton();
/*  674 */     this.jNumberKeys1 = new JNumberKeys();
/*  675 */     this.campoReferer = new JTextField();
/*  676 */     this.campoUnidades = new JTextField();
/*  677 */     this.botonBuscar = new JButton();
/*  678 */     this.fechaJLabel = new JLabel();
/*  679 */     this.jPanel1 = new JPanel();
/*  680 */     this.nombreCliente = new JLabel();
/*  681 */     this.nombreVendedor = new JLabel();
/*  682 */     this.nombreMedioPago = new JLabel();
/*  683 */     this.jLabel8 = new JLabel();
/*  684 */     this.nombreAlmacen = new JLabel();
/*  685 */     this.formasJComboBox = new JComboBox();
/*  686 */     this.botonSeleccionarCliente = new JButton();
/*  687 */     this.botonSeleccionarVendedor = new JButton();
/*  688 */     this.botonSeleccionarMedioPago = new JButton();
/*  689 */     this.botonSeleccionarAlmacen = new JButton();
/*  690 */     this.jPanel4 = new JPanel();
/*  691 */     this.jButton1 = new JButton();
/*  692 */     this.jPanel2 = new JPanel();
/*  693 */     this.botonTablaArriba = new JButton();
/*  694 */     this.botonTablaAbajo = new JButton();
/*  695 */     this.jPanel3 = new JPanel();
/*  696 */     this.botonNuevoTicket = new JButton();
/*  697 */     this.botonImprimirTicket = new JButton();
/*  698 */     this.botonLimpiarTicket = new JButton();
/*  699 */     this.campoTotalUnidades = new JLabel();
/*  700 */     this.campoTotalImporte = new JLabel();
/*  701 */     this.botonNotas = new JButton();
/*      */ 
/*  703 */     setClosable(true);
/*  704 */     setDefaultCloseOperation(2);
/*  705 */     setIconifiable(true);
/*  706 */     setMaximizable(true);
/*  707 */     setResizable(true);
/*  708 */     ResourceBundle bundle = ResourceBundle.getBundle("internationalization/Mensajes");
/*  709 */     setTitle(bundle.getString("terminalventas"));
/*  710 */     setFrameIcon(new ImageIcon(getClass().getResource("/contaes/iconos/tpv.png")));
/*  711 */     addInternalFrameListener(new InternalFrameListener() {
/*      */       public void internalFrameOpened(InternalFrameEvent evt) {
/*      */       }
/*      */       public void internalFrameClosing(InternalFrameEvent evt) {
/*      */       }
/*      */       public void internalFrameClosed(InternalFrameEvent evt) {
/*  717 */         TerminalVentas.this.formInternalFrameClosed(evt);
/*      */       }
/*      */       public void internalFrameIconified(InternalFrameEvent evt) {
/*      */       }
/*      */       public void internalFrameDeiconified(InternalFrameEvent evt) {
/*      */       }
/*      */       public void internalFrameActivated(InternalFrameEvent evt) {
/*  724 */         TerminalVentas.this.formInternalFrameActivated(evt);
/*      */       }
/*      */ 
/*      */       public void internalFrameDeactivated(InternalFrameEvent evt)
/*      */       {
/*      */       }
/*      */     });
/*  730 */     this.jLabel1.setText(bundle.getString("numero"));
/*      */ 
/*  732 */     this.campoNumero.setEditable(false);
/*      */ 
/*  734 */     this.jScrollPane1.setViewportView(this.tablaVentas);
/*      */ 
/*  736 */     this.jLabel3.setText(bundle.getString("unidades"));
/*      */ 
/*  738 */     this.jLabel4.setText(bundle.getString("importe"));
/*      */ 
/*  740 */     this.botonBorrar.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/locationbar_erase.png")));
/*  741 */     this.botonBorrar.setText(bundle.getString("borrar"));
/*  742 */     this.botonBorrar.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  744 */         TerminalVentas.this.botonBorrarActionPerformed(evt);
/*      */       }
/*      */     });
/*  748 */     this.botonEditar.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/color_line.png")));
/*  749 */     this.botonEditar.setText(bundle.getString("editar"));
/*  750 */     this.botonEditar.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  752 */         TerminalVentas.this.botonEditarActionPerformed(evt);
/*      */       }
/*      */     });
/*  756 */     this.botonAddProduct.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/addProduct.png")));
/*  757 */     this.botonAddProduct.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  759 */         TerminalVentas.this.botonAddProductActionPerformed(evt);
/*      */       }
/*      */     });
/*  763 */     this.jNumberKeys1.addJNumberEventListener(new JNumberEventListener() {
/*      */       public void keyPerformed(JNumberEvent evt) {
/*  765 */         TerminalVentas.this.jNumberKeys1KeyPerformed(evt);
/*      */       }
/*      */     });
/*  769 */     this.campoReferer.addKeyListener(new KeyAdapter() {
/*      */       public void keyPressed(KeyEvent evt) {
/*  771 */         TerminalVentas.this.campoRefererKeyPressed(evt);
/*      */       }
/*      */     });
/*  775 */     this.botonBuscar.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/search22.png")));
/*  776 */     this.botonBuscar.setText(bundle.getString("buscar"));
/*  777 */     this.botonBuscar.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  779 */         TerminalVentas.this.botonBuscarActionPerformed(evt);
/*      */       }
/*      */     });
/*  783 */     this.fechaJLabel.setFont(new Font("Lucida Grande", 2, 16));
/*  784 */     this.fechaJLabel.setText("30/09/2009");
/*  785 */     this.fechaJLabel.addMouseListener(new MouseAdapter() {
/*      */       public void mouseClicked(MouseEvent evt) {
/*  787 */         TerminalVentas.this.fechaJLabelMouseClicked(evt);
/*      */       }
/*      */     });
/*  791 */     this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/*      */ 
/*  793 */     this.nombreCliente.setText(bundle.getString("cliente"));
/*      */ 
/*  795 */     this.nombreVendedor.setText(bundle.getString("vendedor"));
/*      */ 
/*  797 */     this.nombreMedioPago.setText(bundle.getString("mediospago"));
/*      */ 
/*  799 */     this.jLabel8.setText(bundle.getString("tipopago"));
/*      */ 
/*  801 */     this.nombreAlmacen.setText(bundle.getString("almacen"));
/*      */ 
/*  803 */     this.formasJComboBox.setFont(new Font("Lucida Grande", 0, 16));
/*  804 */     this.formasJComboBox.setModel(new DefaultComboBoxModel(new String[] { "Pago único", "Entrega a cuenta", "Entrega final" }));
/*      */ 
/*  806 */     this.botonSeleccionarCliente.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/edit_group.png")));
/*  807 */     this.botonSeleccionarCliente.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  809 */         TerminalVentas.this.botonSeleccionarClienteActionPerformed(evt);
/*      */       }
/*      */     });
/*  813 */     this.botonSeleccionarVendedor.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/yast_sysadmin22.png")));
/*  814 */     this.botonSeleccionarVendedor.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  816 */         TerminalVentas.this.botonSeleccionarVendedorActionPerformed(evt);
/*      */       }
/*      */     });
/*  820 */     this.botonSeleccionarMedioPago.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/cash22.png")));
/*  821 */     this.botonSeleccionarMedioPago.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  823 */         TerminalVentas.this.botonSeleccionarMedioPagoActionPerformed(evt);
/*      */       }
/*      */     });
/*  827 */     this.botonSeleccionarAlmacen.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/package22.png")));
/*  828 */     this.botonSeleccionarAlmacen.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  830 */         TerminalVentas.this.botonSeleccionarAlmacenActionPerformed(evt);
/*      */       }
/*      */     });
/*  834 */     this.jPanel4.setBorder(new SoftBevelBorder(1));
/*      */ 
/*  836 */     this.jButton1.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/search22.png")));
/*  837 */     this.jButton1.setText(bundle.getString("buscart"));
/*  838 */     this.jButton1.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  840 */         TerminalVentas.this.jButton1ActionPerformed(evt);
/*      */       }
/*      */     });
/*  844 */     GroupLayout jPanel4Layout = new GroupLayout(this.jPanel4);
/*  845 */     this.jPanel4.setLayout(jPanel4Layout);
/*  846 */     jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel4Layout.createSequentialGroup().addGap(49, 49, 49).addComponent(this.jButton1).addContainerGap(54, 32767)));
/*      */ 
/*  853 */     jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel4Layout.createSequentialGroup().addGap(14, 14, 14).addComponent(this.jButton1).addContainerGap(17, 32767)));
/*      */ 
/*  861 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/*  862 */     this.jPanel1.setLayout(jPanel1Layout);
/*  863 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.botonSeleccionarCliente).addGap(18, 18, 18).addComponent(this.nombreCliente, -2, 254, -2).addGap(63, 63, 63)).addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.botonSeleccionarVendedor).addGap(18, 18, 18).addComponent(this.nombreVendedor, -1, 311, 32767)).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.botonSeleccionarAlmacen).addGap(18, 18, 18).addComponent(this.nombreAlmacen, -1, 311, 32767))).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED))).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.botonSeleccionarMedioPago).addGap(18, 18, 18).addComponent(this.nombreMedioPago, -1, 270, 32767).addGap(35, 35, 35)).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.jLabel8).addGap(28, 28, 28).addComponent(this.formasJComboBox, -2, 216, -2).addContainerGap()))).addGroup(jPanel1Layout.createSequentialGroup().addGap(53, 53, 53).addComponent(this.jPanel4, -2, -1, -2).addContainerGap()))));
/*      */ 
/*  903 */     jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGap(18, 18, 18).addComponent(this.nombreCliente)).addGroup(jPanel1Layout.createSequentialGroup().addGap(8, 8, 8).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.botonSeleccionarMedioPago).addGroup(jPanel1Layout.createSequentialGroup().addGap(8, 8, 8).addComponent(this.nombreMedioPago)).addComponent(this.botonSeleccionarCliente)))).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGap(8, 8, 8).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.botonSeleccionarVendedor).addGroup(jPanel1Layout.createSequentialGroup().addGap(10, 10, 10).addComponent(this.nombreVendedor))).addGap(18, 18, 18).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.botonSeleccionarAlmacen).addGroup(jPanel1Layout.createSequentialGroup().addGap(8, 8, 8).addComponent(this.nombreAlmacen)))).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel8).addComponent(this.formasJComboBox, -2, 40, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanel4, -2, -1, -2))).addContainerGap(-1, 32767)));
/*      */ 
/*  942 */     this.botonTablaArriba.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/1uparrow22.png")));
/*  943 */     this.botonTablaArriba.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  945 */         TerminalVentas.this.botonTablaArribaActionPerformed(evt);
/*      */       }
/*      */     });
/*  949 */     this.botonTablaAbajo.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/1downarrow22.png")));
/*  950 */     this.botonTablaAbajo.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  952 */         TerminalVentas.this.botonTablaAbajoActionPerformed(evt);
/*      */       }
/*      */     });
/*  956 */     GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
/*  957 */     this.jPanel2.setLayout(jPanel2Layout);
/*  958 */     jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.botonTablaArriba).addComponent(this.botonTablaAbajo)).addContainerGap(-1, 32767)));
/*      */ 
/*  967 */     jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addComponent(this.botonTablaArriba).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.botonTablaAbajo).addContainerGap(-1, 32767)));
/*      */ 
/*  977 */     this.botonNuevoTicket.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/editnew.png")));
/*  978 */     this.botonNuevoTicket.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  980 */         TerminalVentas.this.botonNuevoTicketActionPerformed(evt);
/*      */       }
/*      */     });
/*  984 */     this.botonImprimirTicket.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/fileprint.png")));
/*  985 */     this.botonImprimirTicket.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  987 */         TerminalVentas.this.botonImprimirTicketActionPerformed(evt);
/*      */       }
/*      */     });
/*  991 */     this.botonLimpiarTicket.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/editdelete.png")));
/*  992 */     this.botonLimpiarTicket.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  994 */         TerminalVentas.this.botonLimpiarTicketActionPerformed(evt);
/*      */       }
/*      */     });
/*  998 */     GroupLayout jPanel3Layout = new GroupLayout(this.jPanel3);
/*  999 */     this.jPanel3.setLayout(jPanel3Layout);
/* 1000 */     jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addComponent(this.botonNuevoTicket, -2, 45, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.botonImprimirTicket, -2, 45, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.botonLimpiarTicket, -2, 45, -2).addContainerGap()));
/*      */ 
/* 1010 */     jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.botonNuevoTicket, -2, 45, -2).addComponent(this.botonImprimirTicket, -2, 45, -2).addComponent(this.botonLimpiarTicket, -2, 45, -2)).addContainerGap()));
/*      */ 
/* 1020 */     this.campoTotalUnidades.setHorizontalAlignment(4);
/* 1021 */     this.campoTotalUnidades.setText("0");
/*      */ 
/* 1023 */     this.campoTotalImporte.setHorizontalAlignment(4);
/* 1024 */     this.campoTotalImporte.setText("0");
/*      */ 
/* 1026 */     this.botonNotas.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/notes.png")));
/* 1027 */     this.botonNotas.setText(bundle.getString("nota"));
/* 1028 */     this.botonNotas.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/* 1030 */         TerminalVentas.this.botonNotasActionPerformed(evt);
/*      */       }
/*      */     });
/* 1034 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 1035 */     getContentPane().setLayout(layout);
/* 1036 */     layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false).addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup().addGap(18, 18, 18).addComponent(this.jPanel1, -1, -1, 32767)).addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.jLabel3).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.campoTotalUnidades, -2, 72, -2).addGap(18, 18, 18).addComponent(this.jLabel4).addGap(18, 18, 18).addComponent(this.campoTotalImporte, -2, 113, -2)).addComponent(this.jScrollPane1, -2, 395, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.botonBuscar).addComponent(this.botonEditar).addComponent(this.botonBorrar).addComponent(this.botonNotas).addComponent(this.jPanel2, -2, -1, -2))).addGroup(layout.createSequentialGroup().addComponent(this.jLabel1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.campoNumero, -2, 128, -2).addGap(18, 18, 18).addComponent(this.jPanel3, -2, -1, -2).addGap(18, 18, 18).addComponent(this.fechaJLabel, -2, 110, -2))).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(10, 10, 10).addComponent(this.campoReferer, -2, 116, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.campoUnidades, -2, 59, -2).addGap(18, 18, 18).addComponent(this.botonAddProduct)).addComponent(this.jNumberKeys1, -2, -1, -2)))).addContainerGap()));
/*      */ 
/* 1084 */     layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.jNumberKeys1, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.campoUnidades, -2, -1, -2).addComponent(this.campoReferer, -2, -1, -2)).addComponent(this.botonAddProduct))).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel1).addComponent(this.campoNumero, -2, -1, -2)).addComponent(this.jPanel3, -2, -1, -2).addComponent(this.fechaJLabel)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false).addComponent(this.jScrollPane1, -2, 231, -2).addGroup(layout.createSequentialGroup().addComponent(this.botonNotas, -2, 35, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.botonBuscar).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.botonEditar).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.botonBorrar).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED))).addGap(12, 12, 12).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel3).addComponent(this.jLabel4).addComponent(this.campoTotalUnidades).addComponent(this.campoTotalImporte)))).addGap(10, 10, 10)).addGroup(layout.createSequentialGroup().addContainerGap(47, 32767).addComponent(this.jPanel2, -2, -1, -2).addGap(203, 203, 203))).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanel1, -2, -1, -2).addContainerGap()));
/*      */ 
/* 1134 */     pack();
/*      */   }
/*      */ 
/*      */   private void botonBorrarActionPerformed(ActionEvent evt) {
/* 1138 */     int rowIndex = this.tablaVentas.getSelectedRow();
/* 1139 */     if (rowIndex != -1)
/* 1140 */       removeLineFromTable(rowIndex);
/*      */   }
/*      */ 
/*      */   private void botonEditarActionPerformed(ActionEvent evt)
/*      */   {
/* 1145 */     int rowIndex = this.tablaVentas.getSelectedRow();
/* 1146 */     if (rowIndex != -1)
/*      */       try {
/* 1148 */         VentaPOS lineaVenta = this.modelo.getObjectAt(rowIndex);
/* 1149 */         VentaPOS lineaModificada = JProductLineEdit.showMessage(this, lineaVenta);
/* 1150 */         if ((lineaModificada != null) && (!lineaVenta.equals(lineaModificada))) {
/* 1151 */           removeLineFromTable(rowIndex);
/* 1152 */           addLineToTable(lineaModificada, rowIndex);
/*      */         }
/*      */       } catch (BasicException ex) {
/* 1155 */         Logger.getLogger(TerminalVentas.class.getName()).log(Level.SEVERE, null, ex);
/*      */       }
/*      */   }
/*      */ 
/*      */   private void jNumberKeys1KeyPerformed(JNumberEvent evt)
/*      */   {
/* 1161 */     calcOperation(evt.getKey());
/*      */   }
/*      */ 
/*      */   private void botonAddProductActionPerformed(ActionEvent evt) {
/* 1165 */     String referencia = this.campoReferer.getText();
/* 1166 */     if (!referencia.equals("")) {
/* 1167 */       ProductObject producto = selecccionarProducto(referencia);
/* 1168 */       introducirProducto(producto);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void botonBuscarActionPerformed(ActionEvent evt) {
/* 1173 */     String referencia = BusquedaProductosJDialog.obtenerObjeto(Inicio.frame, true);
/* 1174 */     if ((referencia != null) && (!referencia.equals(""))) {
/* 1175 */       ProductObject producto = selecccionarProducto(referencia);
/* 1176 */       introducirProducto(producto);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void campoRefererKeyPressed(KeyEvent evt) {
/* 1181 */     if (evt.getKeyCode() == 10)
/* 1182 */       calcOperation('\n');
/*      */   }
/*      */ 
/*      */   private void botonNuevoTicketActionPerformed(ActionEvent evt)
/*      */   {
/* 1187 */     if (this.tablaVentas.getRowCount() > 0)
/* 1188 */       generarTicket(false);
/*      */   }
/*      */ 
/*      */   private void botonLimpiarTicketActionPerformed(ActionEvent evt)
/*      */   {
/* 1193 */     limpiarForm();
/* 1194 */     colocarNuevoNumero();
/*      */   }
/*      */ 
/*      */   private void botonSeleccionarClienteActionPerformed(ActionEvent evt) {
/* 1198 */     this.cliente = BusquedaMultipleJDialog.obtenerObjeto(Inicio.frame, true, 0);
/* 1199 */     if (this.cliente != null)
/* 1200 */       this.nombreCliente.setText(this.cliente.getNombre());
/*      */   }
/*      */ 
/*      */   private void botonSeleccionarVendedorActionPerformed(ActionEvent evt)
/*      */   {
/* 1205 */     this.vendedor = BusquedaMultipleJDialog.obtenerObjeto(Inicio.frame, true, 1);
/* 1206 */     if (this.vendedor != null)
/* 1207 */       this.nombreVendedor.setText(this.vendedor.getNombre());
/*      */   }
/*      */ 
/*      */   private void botonSeleccionarAlmacenActionPerformed(ActionEvent evt)
/*      */   {
/* 1212 */     this.almacen = BusquedaMultipleJDialog.obtenerObjeto(Inicio.frame, true, 2);
/* 1213 */     if (this.almacen != null)
/* 1214 */       this.nombreAlmacen.setText(this.almacen.getNombre());
/*      */   }
/*      */ 
/*      */   private void botonSeleccionarMedioPagoActionPerformed(ActionEvent evt)
/*      */   {
/* 1219 */     this.medioPago = BusquedaMultipleJDialog.obtenerObjeto(Inicio.frame, true, 3);
/* 1220 */     if (this.medioPago != null)
/* 1221 */       this.nombreMedioPago.setText(this.medioPago.getNombre());
/*      */   }
/*      */ 
/*      */   private void botonTablaAbajoActionPerformed(ActionEvent evt)
/*      */   {
/* 1226 */     int rowSelected = this.tablaVentas.getSelectedRow();
/* 1227 */     if (rowSelected < this.tablaVentas.getRowCount() - 1) {
/* 1228 */       rowSelected++;
/*      */     }
/* 1230 */     this.tablaVentas.changeSelection(rowSelected, 0, false, false);
/*      */   }
/*      */ 
/*      */   private void botonTablaArribaActionPerformed(ActionEvent evt) {
/* 1234 */     int rowSelected = this.tablaVentas.getSelectedRow();
/* 1235 */     if (rowSelected > 0) {
/* 1236 */       rowSelected--;
/*      */     }
/* 1238 */     this.tablaVentas.changeSelection(rowSelected, 0, false, false);
/*      */   }
/*      */ 
/*      */   private void formInternalFrameActivated(InternalFrameEvent evt) {
/* 1242 */     if (this.campoNumero.getText().equals(""))
/* 1243 */       colocarNuevoNumero();
/*      */   }
/*      */ 
/*      */   private void botonImprimirTicketActionPerformed(ActionEvent evt)
/*      */   {
/* 1248 */     if (this.tablaVentas.getRowCount() > 0)
/* 1249 */       generarTicket(true);
/*      */   }
/*      */ 
/*      */   private void fechaJLabelMouseClicked(MouseEvent evt)
/*      */   {
/* 1254 */     if (evt.getClickCount() == 2) {
/* 1255 */       Date seleccionfecha = JCalendarDialog.showCalendarTimeHours(getParent(), this.fecha);
/* 1256 */       if (seleccionfecha != null) {
/* 1257 */         this.fecha.setTime(seleccionfecha.getTime());
/* 1258 */         SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
/* 1259 */         this.fechaJLabel.setText(sdf.format(this.fecha));
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private void botonNotasActionPerformed(ActionEvent evt) {
/* 1265 */     String oldNota = getNotaFromSelectedRow();
/* 1266 */     String newNota = TerminalNotasJDialog.obtenerObjeto(Inicio.frame, true, oldNota);
/* 1267 */     if ((newNota != null) && (!newNota.equals(oldNota)))
/* 1268 */       setNotaToSelectedRow(newNota);
/*      */   }
/*      */ 
/*      */   private void formInternalFrameClosed(InternalFrameEvent evt)
/*      */   {
/* 1273 */     if (!Inicio.p.isModoPOS())
/* 1274 */       Inicio.frame.eliminarMarcoMenu(Mensajes.getString("terminalventas"));
/*      */   }
/*      */ 
/*      */   private void jButton1ActionPerformed(ActionEvent evt) {
/* 1278 */     Integer idTick = BusquedaTicketJDialog.obtenerObjeto(Inicio.frame, true);
/* 1279 */     if (idTick != null)
/* 1280 */       cargarTicket(idTick.intValue());
/*      */   }
/*      */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.view.TerminalVentas
 * JD-Core Version:    0.6.2
 */