/*      */ package pos.view;
/*      */ 
/*      */ import contaes.Inicio;
/*      */ import contaes.MarcoInicio;
/*      */ import contaes.Puente;
/*      */ import contaes.auxiliarTablas.BooleanColorRenderer;
/*      */ import contaes.auxiliarTablas.DerechaColorRenderer;
/*      */ import contaes.auxiliarTablas.GeneralColorRenderer;
/*      */ import contaes.auxiliarTablas.ImporteColorRenderer;
/*      */ import contaes.manejoDatos.ManejoApuntes;
/*      */ import contaes.manejoDatos.ManejoAsientos;
/*      */ import contaes.manejoDatos.ManejoTiposIVA;
/*      */ import contaes.manejoDatos.TipoIVA;
/*      */ import contaes.manejoDatos.TipoSubcuenta;
/*      */ import contaes.manejoDatos.auxiliar.ConfiguracionBean;
/*      */ import facturacion.control.AlmacenControl;
/*      */ import facturacion.model.Producto;
/*      */ import internationalization.Mensajes;
/*      */ import java.awt.Color;
/*      */ import java.awt.Container;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.Font;
/*      */ import java.awt.Point;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.ActionListener;
/*      */ import java.awt.event.MouseAdapter;
/*      */ import java.awt.event.MouseEvent;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Date;
/*      */ import java.util.HashSet;
import java.util.List;
/*      */ import java.util.ResourceBundle;
/*      */ import javax.swing.BorderFactory;
/*      */ import javax.swing.GroupLayout;
/*      */ import javax.swing.GroupLayout.Alignment;
/*      */ import javax.swing.GroupLayout.ParallelGroup;
/*      */ import javax.swing.GroupLayout.SequentialGroup;
/*      */ import javax.swing.ImageIcon;
/*      */ import javax.swing.JButton;
/*      */ import javax.swing.JCheckBox;
/*      */ import javax.swing.JInternalFrame;
/*      */ import javax.swing.JLabel;
/*      */ import javax.swing.JOptionPane;
/*      */ import javax.swing.JPanel;
/*      */ import javax.swing.JScrollPane;
/*      */ import javax.swing.JTable;
import javax.swing.LayoutStyle;
/*      */ import javax.swing.LayoutStyle.ComponentPlacement;
/*      */ import javax.swing.event.InternalFrameEvent;
/*      */ import javax.swing.event.InternalFrameListener;
/*      */ import javax.swing.table.TableColumn;
/*      */ import javax.swing.table.TableColumnModel;
/*      */ import pos.control.ResumenControl;
/*      */ import pos.control.TicketsControl;
/*      */ import pos.model.ResumenAgrupado;
/*      */ import pos.model.ResumenAgrupadoTableModel;
/*      */ import pos.model.ResumenVentaTicket;
/*      */ import pos.model.ResumenVentaTicketMio;
/*      */ import pos.model.ResumenVentaTicketTableModel;
/*      */ import pos.model.Ticket;
/*      */ import pos.view.calendar.JCalendarDialog;
/*      */ 
/*      */ public class ResumenDiario extends JInternalFrame
/*      */ {
/*      */   ResumenVentaTicketTableModel modeloVentas;
/*      */   ResumenAgrupadoTableModel modeloMediosPago;
/*      */   ResumenAgrupadoTableModel modeloFamilias;
/*      */   Date fechaEnUso;
/*      */   SimpleDateFormat sdf;
/*      */   private JButton abajo;
/*      */   private JButton abajo1;
/*      */   private JButton abajo2;
/*      */   private JButton arriba;
/*      */   private JButton arriba1;
/*      */   private JButton arriba2;
/*      */   private JButton botonBorrarTicket;
/*      */   private JButton botonCerrarAlmacen;
/*      */   private JButton botonCerrarCaja;
/*      */   private JButton botonCierreTotal;
/*      */   private JButton botonConsultas;
/*      */   private JButton botonNota;
/*      */   private JButton botonRecargar;
/*      */   private JLabel campoImporte;
/*      */   private JLabel campoUnidades;
/*      */   private JLabel fechaJLabel;
/*      */   private JButton fin;
/*      */   private JButton fin1;
/*      */   private JButton fin2;
/*      */   private JButton inicio;
/*      */   private JButton inicio1;
/*      */   private JButton inicio2;
/*      */   private JButton jButton1;
/*      */   private JCheckBox jCheckBox1;
/*      */   private JLabel jLabel1;
/*      */   private JLabel jLabel2;
/*      */   private JPanel jPanel1;
/*      */   private JPanel jPanel2;
/*      */   private JPanel jPanel3;
/*      */   private JPanel jPanel4;
/*      */   private JScrollPane jScrollPane1;
/*      */   private JScrollPane jScrollPane2;
/*      */   private JScrollPane jScrollPane3;
/*      */   private JLabel labelCajaCerrada;
/*      */   private JTable tablaFamilias;
/*      */   private JTable tablaMedios;
/*      */   private JTable tablaVentas;
/*      */ 
/*      */   public ResumenDiario()
/*      */   {
/*   62 */     initComponents();
/*   63 */     setSavedCBState();
/*   64 */     this.sdf = new SimpleDateFormat("dd/MM/yyyy");
/*   65 */     this.fechaEnUso = new Date();
/*   66 */     generarTablas(this.fechaEnUso);
/*      */   }
/*      */ 
/*      */   private void generarTablas(Date fecha) {
/*   70 */     this.fechaJLabel.setText(this.sdf.format(fecha));
/*   71 */     ResumenControl rC = new ResumenControl(Inicio.getcAlmacen());
/*   72 */     ArrayList lineasVentas = rC.listaVentas(fecha);
/*   73 */     ArrayList lineasMediosPago = rC.resumenMediosPago(fecha);
/*   74 */     ArrayList lineasFamilias = rC.resumenFamilias(fecha);
/*      */ 
/*   77 */     rC.cerrarRs();
/*      */ 
/*   79 */     this.modeloVentas = new ResumenVentaTicketTableModel(lineasVentas);
/*   80 */     this.modeloMediosPago = new ResumenAgrupadoTableModel(lineasMediosPago);
/*   81 */     this.modeloFamilias = new ResumenAgrupadoTableModel(lineasFamilias);
/*      */ 
/*   83 */     this.tablaVentas.setModel(this.modeloVentas);
/*   84 */     this.tablaMedios.setModel(this.modeloMediosPago);
/*   85 */     this.tablaFamilias.setModel(this.modeloFamilias);
/*      */ 
/*   87 */     int anchoTabla = 628;
/*   88 */     TableColumn columna = this.tablaVentas.getColumnModel().getColumn(0);
/*   89 */     columna.setPreferredWidth((int)(anchoTabla * 0.08D));
/*   90 */     columna = this.tablaVentas.getColumnModel().getColumn(1);
/*   91 */     columna.setPreferredWidth((int)(anchoTabla * 0.32D));
/*   92 */     columna.setCellRenderer(new GeneralColorRenderer());
/*   93 */     columna = this.tablaVentas.getColumnModel().getColumn(2);
/*   94 */     columna.setPreferredWidth((int)(anchoTabla * 0.1D));
/*   95 */     columna.setCellRenderer(new DerechaColorRenderer());
/*   96 */     columna = this.tablaVentas.getColumnModel().getColumn(3);
/*   97 */     columna.setPreferredWidth((int)(anchoTabla * 0.15D));
/*   98 */     columna.setCellRenderer(new ImporteColorRenderer());
/*   99 */     columna = this.tablaVentas.getColumnModel().getColumn(4);
/*  100 */     columna.setPreferredWidth((int)(anchoTabla * 0.15D));
/*  101 */     columna.setCellRenderer(new ImporteColorRenderer());
/*  102 */     columna = this.tablaVentas.getColumnModel().getColumn(5);
/*  103 */     columna.setPreferredWidth((int)(anchoTabla * 0.15D));
/*  104 */     columna.setCellRenderer(new GeneralColorRenderer());
/*  105 */     columna = this.tablaVentas.getColumnModel().getColumn(6);
/*  106 */     columna.setPreferredWidth((int)(anchoTabla * 0.05D));
/*  107 */     columna.setCellRenderer(new DerechaColorRenderer());
/*  108 */     columna = this.tablaVentas.getColumnModel().getColumn(7);
/*  109 */     columna.setPreferredWidth((int)(anchoTabla * 0.05D));
/*  110 */     columna.setCellRenderer(new BooleanColorRenderer());
/*  111 */     this.tablaVentas.setDefaultRenderer(Object.class, new GeneralColorRenderer());
/*      */ 
/*  113 */     this.jScrollPane1.setViewportView(this.tablaVentas);
/*      */ 
/*  115 */     anchoTabla = 277;
/*  116 */     columna = this.tablaMedios.getColumnModel().getColumn(0);
/*  117 */     columna.setPreferredWidth((int)(anchoTabla * 0.7D));
/*  118 */     columna = this.tablaMedios.getColumnModel().getColumn(1);
/*  119 */     columna.setPreferredWidth((int)(anchoTabla * 0.3D));
/*  120 */     columna.setCellRenderer(new ImporteColorRenderer());
/*  121 */     this.tablaMedios.setDefaultRenderer(Object.class, new GeneralColorRenderer());
/*      */ 
/*  123 */     this.jScrollPane3.setViewportView(this.tablaMedios);
/*      */ 
/*  125 */     columna = this.tablaFamilias.getColumnModel().getColumn(0);
/*  126 */     columna.setPreferredWidth((int)(anchoTabla * 0.7D));
/*  127 */     columna = this.tablaFamilias.getColumnModel().getColumn(1);
/*  128 */     columna.setPreferredWidth((int)(anchoTabla * 0.3D));
/*  129 */     columna.setCellRenderer(new ImporteColorRenderer());
/*  130 */     this.tablaFamilias.setDefaultRenderer(Object.class, new GeneralColorRenderer());
/*      */ 
/*  132 */     this.jScrollPane2.setViewportView(this.tablaFamilias);
/*      */ 
/*  134 */     int sumaUnidades = 0;
/*  135 */     double sumaTotales = 0.0D;
/*  136 */     for (ResumenVentaTicket resumenVentaTicket :(List<ResumenVentaTicket>) lineasVentas) {
/*  137 */       sumaUnidades += resumenVentaTicket.getUnidades();
/*  138 */       sumaTotales += resumenVentaTicket.getTotal();
/*      */     }
/*  140 */     if (lineasVentas.isEmpty()) {
/*  141 */       this.labelCajaCerrada.setText("");
/*      */     }
/*  144 */     else if (lineasVentas.get(0) != null) {
/*  145 */       if (((ResumenVentaTicket)lineasVentas.get(0)).isCerrado() == 3) {
/*  146 */         this.labelCajaCerrada.setText(Mensajes.getString("cajacerrada"));
/*      */       }
/*  148 */       else if (((ResumenVentaTicket)lineasVentas.get(0)).isCerrado() == 2) {
/*  149 */         this.labelCajaCerrada.setText(Mensajes.getString("contacerrada"));
/*      */       }
/*  151 */       else if (((ResumenVentaTicket)lineasVentas.get(0)).isCerrado() == 1) {
/*  152 */         this.labelCajaCerrada.setText(Mensajes.getString("almacerrada"));
/*      */       }
/*      */       else {
/*  155 */         this.labelCajaCerrada.setText("");
/*      */       }
/*      */     }
/*      */ 
/*  159 */     this.campoUnidades.setText(Integer.toString(sumaUnidades));
/*  160 */     this.campoImporte.setText(String.format("%,15.2f %s", new Object[] { Double.valueOf(sumaTotales), Mensajes.getString("moneda") }));
/*      */   }
/*      */ 
/*      */   private void inicio(JTable tabla) {
/*  164 */     tabla.changeSelection(0, 0, false, false);
/*      */   }
/*      */ 
/*      */   private void arriba(JTable tabla) {
/*  168 */     if (tabla.getSelectedRow() != -1) {
/*  169 */       int rowSelected = tabla.getSelectedRow();
/*  170 */       if (rowSelected > 0) {
/*  171 */         rowSelected--;
/*  172 */         tabla.changeSelection(rowSelected, 0, false, false);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private void abajo(JTable tabla) {
/*  178 */     if (tabla.getSelectedRow() != -1) {
/*  179 */       int rowSelected = tabla.getSelectedRow();
/*  180 */       if (rowSelected < tabla.getRowCount() - 1) {
/*  181 */         rowSelected++;
/*  182 */         tabla.changeSelection(rowSelected, 0, false, false);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private void fin(JTable tabla) {
/*  188 */     tabla.changeSelection(tabla.getRowCount() - 1, 0, false, false);
/*      */   }
/*      */ 
/*      */   private void mostrarError(String error) {
/*  192 */     JOptionPane.showMessageDialog(getContentPane(), error, "Error", 0);
/*      */   }
/*      */ 
/*      */   private void cerrarCajaDia(boolean almacen) {
/*  196 */     AlmacenControl aC = new AlmacenControl();
/*      */ 
/*  198 */     ArrayList listaVentas = this.modeloVentas.getObjetos();
/*  199 */     if (almacen) {
/*  200 */       for (ResumenVentaTicket linea : (List<ResumenVentaTicket>) listaVentas) {
/*  201 */         Producto producto = aC.getProducto(Integer.valueOf(linea.getIdProducto()), true);
/*      */ 
/*  212 */         if (linea.getPlazos() != 1) {
/*  213 */           boolean insertar = true;
/*      */ 
/*  225 */           if (insertar) {
/*  226 */             double importeVenta = linea.getImporte();
/*  227 */             if (linea.getPlazos() == 2) {
/*  228 */               int idTicketAnterior = aC.getIdTicketAnterior(linea.getIdTicket()).intValue();
/*  229 */               double importeAnterior = aC.plazosAnteriores(linea.getIdProducto(), Integer.valueOf(idTicketAnterior));
/*      */ 
/*  231 */               importeVenta += importeAnterior;
/*      */             }
/*  233 */             aC.insertVenta(producto.getReferencia(), this.fechaEnUso, importeVenta, linea.getUnidades(), linea.getAlmacen());
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*  238 */     if (!almacen)
/*      */     {
/*  241 */       boolean conIVA = this.jCheckBox1.isSelected();
/*  242 */       ResumenControl rC = new ResumenControl(Inicio.getcAlmacen());
/*  243 */       ArrayList listaMedios = this.modeloMediosPago.getObjetos();
/*  244 */       ArrayList listaMediosSubc = new ArrayList();
/*  245 */       ArrayList listaMediosComisionSubc = new ArrayList();
/*  246 */       for (ResumenAgrupado resumenAgrupado :(List<ResumenAgrupado>) listaMedios) {
/*  247 */         int cuenta = rC.getCuentaCobroMedio(resumenAgrupado.getId());
/*  248 */         double importe = resumenAgrupado.getImporte();
/*  249 */         ResumenAgrupado objeto = new ResumenAgrupado(cuenta, "", importe);
/*  250 */         ResumenAgrupado objetoComision = rC.getComisionMedio(resumenAgrupado.getId(), importe);
/*  251 */         objetoComision.setNombre(resumenAgrupado.getNombre());
/*  252 */         listaMediosSubc.add(objeto);
/*  253 */         listaMediosComisionSubc.add(objetoComision);
/*      */       }
/*      */ 
/*  263 */       ArrayList listaFamiliasSubc = new ArrayList();
/*  264 */       for (ResumenAgrupado resumenAgrupado : this.modeloFamilias.getObjetos()) {
/*  265 */         TipoSubcuenta subc = aC.getSubcuentaFamilia(resumenAgrupado.getId(), true);
/*  266 */         double importe = resumenAgrupado.getImporte();
/*  267 */         ResumenAgrupado objeto = new ResumenAgrupado(subc.getCodigo(), "", importe);
/*  268 */         listaFamiliasSubc.add(objeto);
/*      */       }
/*      */ 
/*  271 */       ArrayList listaClientesSubc = rC.resumenClientes(this.fechaEnUso);
/*  272 */       rC.cerrarRs();
/*      */ 
/*  274 */       ArrayList listaIVASubc = new ArrayList();
/*  275 */       ArrayList listaFamiliasIVASubc = new ArrayList();
/*  276 */       if (conIVA) {
/*  277 */         ManejoTiposIVA mTI = new ManejoTiposIVA(Inicio.getCGeneral());
/*  278 */         for (ResumenVentaTicket linea : (List<ResumenVentaTicket>) listaVentas) {
/*  279 */           int idIVA = aC.getIvaProducto(linea.getIdProducto());
/*  280 */           TipoIVA iva = mTI.getTipoIVA(idIVA);
/*  281 */           int subcuentaIVA = iva.getCuentaRep();
/*  282 */           Producto producto = aC.getProducto(Integer.valueOf(linea.getIdProducto()), true);
/*  283 */           int subcuentaFamilia = producto.getSubcuenta().getCodigo();
/*  284 */           double importeIVA = linea.getTotal() - linea.getTotal() / (1.0D + iva.getIva() / 100.0D);
/*  285 */           ResumenAgrupado ivaObject = new ResumenAgrupado(subcuentaIVA, "", importeIVA);
/*  286 */           ResumenAgrupado familiaObject = new ResumenAgrupado(subcuentaFamilia, "", -1.0D * importeIVA);
/*  287 */           addObject(listaIVASubc, ivaObject);
/*  288 */           addObject(listaFamiliasIVASubc, familiaObject);
/*      */         }
/*  290 */         for (ResumenAgrupado resumenAgrupado :(List<ResumenAgrupado>) listaFamiliasIVASubc) {
/*  291 */           addObject(listaFamiliasSubc, resumenAgrupado);
/*      */         }
/*      */       }
/*  294 */       aC.cerrarConexion();
/*  295 */       ManejoAsientos mAs = new ManejoAsientos(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  296 */       int numeroAsiento = mAs.nuevoNumero();
/*  297 */       if (numeroAsiento != -1) {
/*  298 */         SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
/*  299 */         int idAstoVentas = mAs.crear(numeroAsiento, sdf2.format(this.fechaEnUso), "", "T");
/*  300 */         int idAstoCobro = mAs.crear(numeroAsiento + 1, sdf2.format(this.fechaEnUso), "", "T");
/*  301 */         mAs.cerrarRs();
/*  302 */         if ((idAstoVentas != -1) && (idAstoCobro != -1)) {
/*  303 */           ManejoApuntes mAp = new ManejoApuntes(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  304 */           String concepto = "Ventas día " + this.sdf.format(this.fechaEnUso);
/*      */ 
/*  306 */           for (ResumenAgrupado resumenAgrupado :(List<ResumenAgrupado>) listaFamiliasSubc) {
/*  307 */             String dh = resumenAgrupado.getImporte() >= 0.0D ? "H" : "D";
/*  308 */             mAp.crear(idAstoVentas, resumenAgrupado.getId(), concepto, dh, resumenAgrupado.getImporte());
/*      */           }
/*  310 */           for (ResumenAgrupado resumenAgrupado :(List<ResumenAgrupado>) listaIVASubc) {
/*  311 */             String dh = resumenAgrupado.getImporte() >= 0.0D ? "H" : "D";
/*  312 */             mAp.crear(idAstoVentas, resumenAgrupado.getId(), concepto, dh, resumenAgrupado.getImporte());
/*      */           }
/*  314 */           for (ResumenAgrupado resumenAgrupado :(List<ResumenAgrupado>) listaClientesSubc) {
/*  315 */             String dh = resumenAgrupado.getImporte() >= 0.0D ? "D" : "H";
/*  316 */             mAp.crear(idAstoVentas, resumenAgrupado.getId(), concepto, dh, resumenAgrupado.getImporte());
/*      */           }
/*      */ 
/*  319 */           for (ResumenAgrupado resumenAgrupado :(List<ResumenAgrupado>) listaClientesSubc) {
/*  320 */             String dh = resumenAgrupado.getImporte() >= 0.0D ? "H" : "D";
/*  321 */             mAp.crear(idAstoCobro, resumenAgrupado.getId(), concepto, dh, resumenAgrupado.getImporte());
/*      */           }
/*  323 */           for (ResumenAgrupado resumenAgrupado :(List<ResumenAgrupado>) listaMediosSubc) {
/*  324 */             String dh = resumenAgrupado.getImporte() >= 0.0D ? "D" : "H";
/*  325 */             mAp.crear(idAstoCobro, resumenAgrupado.getId(), concepto, dh, resumenAgrupado.getImporte());
/*      */           }
/*      */ 
/*  328 */           int temp = 0;
/*  329 */           for (ResumenAgrupado resumenAgrupado : (List<ResumenAgrupado>)listaMediosComisionSubc) {
/*  330 */             if (resumenAgrupado.getImporte() != 0.0D) {
/*  331 */               String dh1 = resumenAgrupado.getImporte() >= 0.0D ? "D" : "H";
/*  332 */               String dh2 = resumenAgrupado.getImporte() >= 0.0D ? "H" : "D";
/*  333 */               mAp.crear(idAstoCobro, resumenAgrupado.getId(), "Comisión " + resumenAgrupado.getNombre(), dh1, resumenAgrupado.getImporte());
/*      */ 
/*  335 */               mAp.crear(idAstoCobro, ((ResumenAgrupado)listaMediosSubc.get(temp)).getId(), "Comisión " + resumenAgrupado.getNombre(), dh2, resumenAgrupado.getImporte());
/*      */             }
/*      */ 
/*  338 */             temp++;
/*      */           }
/*  340 */           mAp.cerrarRs();
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*  345 */     TicketsControl tC = new TicketsControl(Inicio.getcAlmacen());
/*  346 */     HashSet listaControl = new HashSet();
/*      */ 
/*  348 */     for (ResumenVentaTicket venta : (List<ResumenVentaTicket>)listaVentas) {
/*  349 */       if (!listaControl.contains(new Integer(venta.getIdTicket()))) {
/*  350 */         listaControl.add(new Integer(venta.getIdTicket()));
/*  351 */         Ticket ticket = tC.getTicket(venta.getIdTicket());
/*  352 */         int estado = 3;
/*  353 */         if (almacen) {
/*  354 */           if (ticket.isCerrado() == 2) {
/*  355 */             estado = 3;
/*      */           }
/*      */           else
/*      */           {
/*  359 */             estado = 1;
/*      */           }
/*      */ 
/*      */         }
/*  363 */         else if (!almacen) {
/*  364 */           if (ticket.isCerrado() == 1) {
/*  365 */             estado = 3;
/*      */           }
/*      */           else
/*      */           {
/*  369 */             estado = 2;
/*      */           }
/*      */         }
/*      */ 
/*  373 */         ticket.setCerrado(estado);
/*  374 */         tC.modificar(ticket);
/*      */       }
/*      */     }
/*  377 */     tC.cerrarRs();
/*  378 */     generarTablas(this.fechaEnUso);
/*  379 */     String opt = almacen ? " de almacén " : " contable ";
/*  380 */     JOptionPane.showMessageDialog(getContentPane(), "Proceso de actualización" + opt + "terminado", "Aviso", 1);
/*      */   }
/*      */ 
/*      */   private void cambiarCajaANoCerrada()
/*      */   {
/*  385 */     ArrayList listaVentas = this.modeloVentas.getObjetos();
/*  386 */     TicketsControl tC = new TicketsControl(Inicio.getcAlmacen());
/*  387 */     HashSet listaControl = new HashSet();
/*      */ 
/*  389 */     for (ResumenVentaTicket venta :(List<ResumenVentaTicket>) listaVentas) {
/*  390 */       if (!listaControl.contains(new Integer(venta.getIdTicket()))) {
/*  391 */         listaControl.add(new Integer(venta.getIdTicket()));
/*  392 */         Ticket ticket = tC.getTicket(venta.getIdTicket());
/*  393 */         ticket.setCerrado(0);
/*  394 */         tC.modificar(ticket);
/*      */       }
/*      */     }
/*  397 */     tC.cerrarRs();
/*  398 */     generarTablas(this.fechaEnUso);
/*  399 */     this.labelCajaCerrada.setText("");
/*      */   }
/*      */ 
/*      */   private int isCajaCerrada() {
/*  403 */     int cerrada = 0;
/*  404 */     if (this.modeloVentas.getRowCount() > 0) {
/*  405 */       ResumenVentaTicket ticketComprobacion = this.modeloVentas.getObjectAt(0);
/*  406 */       cerrada = ticketComprobacion.isCerrado();
/*      */     }
/*  408 */     return cerrada;
/*      */   }
/*      */ 
/*      */   private void addObject(ArrayList<ResumenAgrupado> lista, ResumenAgrupado object) {
/*  412 */     boolean existe = false;
/*  413 */     for (int i = 0; i < lista.size(); i++) {
/*  414 */       if (((ResumenAgrupado)lista.get(i)).getId() == object.getId()) {
/*  415 */         ((ResumenAgrupado)lista.get(i)).setImporte(((ResumenAgrupado)lista.get(i)).getImporte() + object.getImporte());
/*  416 */         existe = true;
/*  417 */         break;
/*      */       }
/*      */     }
/*  420 */     if (!existe)
/*  421 */       lista.add(object);
/*      */   }
/*      */ 
/*      */   private void mostrarNota()
/*      */   {
/*  426 */     int row = this.tablaVentas.getSelectedRow();
/*  427 */     if (row != -1) {
/*  428 */       String nota = this.modeloVentas.getObjectAt(row).getNota();
/*  429 */       if (!nota.equals("")) {
/*  430 */         ResumenNotasJDialog dlg = new ResumenNotasJDialog(Inicio.frame, true, nota);
/*  431 */         Dimension dlgSize = dlg.getPreferredSize();
/*  432 */         Dimension frmSize = Inicio.frame.getSize();
/*  433 */         Point loc = Inicio.frame.getLocation();
/*  434 */         dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
/*      */ 
/*  436 */         dlg.pack();
/*  437 */         dlg.setVisible(true);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private void makeListado() {
/*  443 */     Date[] fechas = AskForFechasJDialog.obtenerObjeto(Inicio.frame, true);
/*  444 */     if ((fechas[0] != null) && (fechas[1] != null)) {
/*  445 */       ArrayList lineas = new ArrayList();
/*  446 */       ResumenControl rC = new ResumenControl(Inicio.getcAlmacen());
/*  447 */       lineas = rC.listaVentasBetweenDates(fechas);
/*  448 */       rC.cerrarRs();
/*  449 */       ListadoVentasJFrame frame = new ListadoVentasJFrame("Ventas TPV", "Entre " + this.sdf.format(fechas[0]) + " y " + this.sdf.format(fechas[1]), componerArray(lineas));
/*      */ 
/*  452 */       Inicio.frame.mostrarMarcoExterno(frame);
/*      */     }
/*      */   }
/*      */ 
/*      */   private ArrayList<ResumenVentaTicketMio> componerArray(ArrayList<ResumenVentaTicketMio> lineas)
/*      */   {
/*  458 */     ArrayList newLines = new ArrayList();
/*  459 */     ResumenVentaTicketMio line = null;
/*      */ 
/*  461 */     Date fechaTemp = ((ResumenVentaTicketMio)lineas.get(0)).getFecha();
/*  462 */     double sumaDia = 0.0D;
/*  463 */     int uniDia = 0; int uniTotal = 0;
/*  464 */     double sumaTotal = 0.0D;
/*  465 */     for (ResumenVentaTicketMio venta : lineas) {
/*  466 */       if (!fechaTemp.equals(venta.getFecha())) {
/*  467 */         line = new ResumenVentaTicketMio(fechaTemp, -1, -1, "Total", uniDia, 0.0D, sumaDia, null, -1, -1, -1, -1, -1, -1, null);
/*  468 */         newLines.add(line);
/*  469 */         line = null;
/*  470 */         sumaDia = 0.0D;
/*  471 */         uniDia = 0;
/*      */       }
/*  473 */       fechaTemp = venta.getFecha();
/*  474 */       sumaDia += venta.getTotal();
/*  475 */       sumaTotal += venta.getTotal();
/*  476 */       uniDia += venta.getUnidades();
/*  477 */       uniTotal += venta.getUnidades();
/*  478 */       newLines.add(venta);
/*      */     }
/*  480 */     line = new ResumenVentaTicketMio(fechaTemp, -1, -1, "Total", uniDia, 0.0D, sumaDia, null, -1, -1, -1, -1, -1, -1, null);
/*  481 */     newLines.add(line);
/*  482 */     line = new ResumenVentaTicketMio(fechaTemp, -1, -1, "Total", uniTotal, 0.0D, sumaTotal, null, -1, -1, -1, -1, -1, -1, null);
/*  483 */     newLines.add(line);
/*  484 */     return newLines;
/*      */   }
/*      */ 
/*      */   private void initComponents()
/*      */   {
/*  541 */     this.fechaJLabel = new JLabel();
/*  542 */     this.jScrollPane1 = new JScrollPane();
/*  543 */     this.tablaVentas = new JTable();
/*  544 */     this.jPanel1 = new JPanel();
/*  545 */     this.inicio = new JButton();
/*  546 */     this.arriba = new JButton();
/*  547 */     this.abajo = new JButton();
/*  548 */     this.fin = new JButton();
/*  549 */     this.jLabel1 = new JLabel();
/*  550 */     this.jLabel2 = new JLabel();
/*  551 */     this.jScrollPane2 = new JScrollPane();
/*  552 */     this.tablaFamilias = new JTable();
/*  553 */     this.jPanel2 = new JPanel();
/*  554 */     this.inicio1 = new JButton();
/*  555 */     this.arriba1 = new JButton();
/*  556 */     this.abajo1 = new JButton();
/*  557 */     this.fin1 = new JButton();
/*  558 */     this.jScrollPane3 = new JScrollPane();
/*  559 */     this.tablaMedios = new JTable();
/*  560 */     this.jPanel3 = new JPanel();
/*  561 */     this.inicio2 = new JButton();
/*  562 */     this.arriba2 = new JButton();
/*  563 */     this.abajo2 = new JButton();
/*  564 */     this.fin2 = new JButton();
/*  565 */     this.jButton1 = new JButton();
/*  566 */     this.botonRecargar = new JButton();
/*  567 */     this.botonConsultas = new JButton();
/*  568 */     this.botonBorrarTicket = new JButton();
/*  569 */     this.campoUnidades = new JLabel();
/*  570 */     this.campoImporte = new JLabel();
/*  571 */     this.botonNota = new JButton();
/*  572 */     this.labelCajaCerrada = new JLabel();
/*  573 */     this.jPanel4 = new JPanel();
/*  574 */     this.botonCerrarCaja = new JButton();
/*  575 */     this.jCheckBox1 = new JCheckBox();
/*  576 */     this.botonCerrarAlmacen = new JButton();
/*  577 */     this.botonCierreTotal = new JButton();
/*      */ 
/*  579 */     setClosable(true);
/*  580 */     setIconifiable(true);
/*  581 */     setMaximizable(true);
/*  582 */     setResizable(true);
/*  583 */     ResourceBundle bundle = ResourceBundle.getBundle("internationalization/Mensajes");
/*  584 */     setTitle(bundle.getString("resumendia"));
/*  585 */     setFrameIcon(new ImageIcon(getClass().getResource("/contaes/iconos/tpvres.png")));
/*  586 */     addInternalFrameListener(new InternalFrameListener() {
/*      */       public void internalFrameOpened(InternalFrameEvent evt) {
/*      */       }
/*      */       public void internalFrameClosing(InternalFrameEvent evt) {
/*  590 */         ResumenDiario.this.formInternalFrameClosing(evt);
/*      */       }
/*      */       public void internalFrameClosed(InternalFrameEvent evt) {
/*  593 */         ResumenDiario.this.formInternalFrameClosed(evt);
/*      */       }
/*      */ 
/*      */       public void internalFrameIconified(InternalFrameEvent evt)
/*      */       {
/*      */       }
/*      */ 
/*      */       public void internalFrameDeiconified(InternalFrameEvent evt)
/*      */       {
/*      */       }
/*      */ 
/*      */       public void internalFrameActivated(InternalFrameEvent evt)
/*      */       {
/*      */       }
/*      */ 
/*      */       public void internalFrameDeactivated(InternalFrameEvent evt)
/*      */       {
/*      */       }
/*      */     });
/*  605 */     this.fechaJLabel.setFont(new Font("Lucida Grande", 2, 16));
/*  606 */     this.fechaJLabel.setText("30/09/2009");
/*  607 */     this.fechaJLabel.addMouseListener(new MouseAdapter() {
/*      */       public void mouseClicked(MouseEvent evt) {
/*  609 */         ResumenDiario.this.fechaJLabelMouseClicked(evt);
/*      */       }
/*      */     });
/*  613 */     this.jScrollPane1.setViewportView(this.tablaVentas);
/*      */ 
/*  615 */     this.inicio.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/3uparrow.png")));
/*  616 */     this.inicio.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  618 */         ResumenDiario.this.inicioActionPerformed(evt);
/*      */       }
/*      */     });
/*  622 */     this.arriba.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/1uparrow22.png")));
/*  623 */     this.arriba.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  625 */         ResumenDiario.this.arribaActionPerformed(evt);
/*      */       }
/*      */     });
/*  629 */     this.abajo.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/1downarrow22.png")));
/*  630 */     this.abajo.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  632 */         ResumenDiario.this.abajoActionPerformed(evt);
/*      */       }
/*      */     });
/*  636 */     this.fin.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/3downarrow.png")));
/*  637 */     this.fin.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  639 */         ResumenDiario.this.finActionPerformed(evt);
/*      */       }
/*      */     });
/*  643 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/*  644 */     this.jPanel1.setLayout(jPanel1Layout);
/*  645 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.inicio).addComponent(this.arriba).addComponent(this.abajo).addComponent(this.fin)).addContainerGap(-1, 32767)));
/*      */ 
/*  656 */     jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(this.inicio).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.arriba).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.abajo).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.fin).addContainerGap()));
/*      */ 
/*  670 */     this.jLabel1.setText(bundle.getString("unidades"));
/*      */ 
/*  672 */     this.jLabel2.setText(bundle.getString("importe"));
/*      */ 
/*  674 */     this.jScrollPane2.setViewportView(this.tablaFamilias);
/*      */ 
/*  676 */     this.inicio1.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/3uparrow.png")));
/*  677 */     this.inicio1.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  679 */         ResumenDiario.this.inicio1ActionPerformed(evt);
/*      */       }
/*      */     });
/*  683 */     this.arriba1.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/1uparrow22.png")));
/*  684 */     this.arriba1.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  686 */         ResumenDiario.this.arriba1ActionPerformed(evt);
/*      */       }
/*      */     });
/*  690 */     this.abajo1.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/1downarrow22.png")));
/*  691 */     this.abajo1.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  693 */         ResumenDiario.this.abajo1ActionPerformed(evt);
/*      */       }
/*      */     });
/*  697 */     this.fin1.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/3downarrow.png")));
/*  698 */     this.fin1.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  700 */         ResumenDiario.this.fin1ActionPerformed(evt);
/*      */       }
/*      */     });
/*  704 */     GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
/*  705 */     this.jPanel2.setLayout(jPanel2Layout);
/*  706 */     jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.inicio1).addComponent(this.arriba1).addComponent(this.abajo1).addComponent(this.fin1)).addContainerGap(-1, 32767)));
/*      */ 
/*  717 */     jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addComponent(this.inicio1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.arriba1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.abajo1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.fin1).addContainerGap()));
/*      */ 
/*  731 */     this.jScrollPane3.setViewportView(this.tablaMedios);
/*      */ 
/*  733 */     this.inicio2.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/3uparrow.png")));
/*  734 */     this.inicio2.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  736 */         ResumenDiario.this.inicio2ActionPerformed(evt);
/*      */       }
/*      */     });
/*  740 */     this.arriba2.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/1uparrow22.png")));
/*  741 */     this.arriba2.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  743 */         ResumenDiario.this.arriba2ActionPerformed(evt);
/*      */       }
/*      */     });
/*  747 */     this.abajo2.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/1downarrow22.png")));
/*  748 */     this.abajo2.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  750 */         ResumenDiario.this.abajo2ActionPerformed(evt);
/*      */       }
/*      */     });
/*  754 */     this.fin2.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/3downarrow.png")));
/*  755 */     this.fin2.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  757 */         ResumenDiario.this.fin2ActionPerformed(evt);
/*      */       }
/*      */     });
/*  761 */     GroupLayout jPanel3Layout = new GroupLayout(this.jPanel3);
/*  762 */     this.jPanel3.setLayout(jPanel3Layout);
/*  763 */     jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addContainerGap().addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.inicio2).addComponent(this.arriba2).addComponent(this.abajo2).addComponent(this.fin2)).addContainerGap(-1, 32767)));
/*      */ 
/*  774 */     jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addContainerGap().addComponent(this.inicio2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.arriba2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.abajo2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.fin2).addContainerGap()));
/*      */ 
/*  788 */     this.jButton1.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/edit.png")));
/*  789 */     this.jButton1.setText(bundle.getString("modificar"));
/*  790 */     this.jButton1.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  792 */         ResumenDiario.this.jButton1ActionPerformed(evt);
/*      */       }
/*      */     });
/*  796 */     this.botonRecargar.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/reload.png")));
/*  797 */     this.botonRecargar.setText(bundle.getString("recargarTablas"));
/*  798 */     this.botonRecargar.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  800 */         ResumenDiario.this.botonRecargarActionPerformed(evt);
/*      */       }
/*      */     });
/*  804 */     this.botonConsultas.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/1day.png")));
/*  805 */     this.botonConsultas.setText(bundle.getString("consultasanteriores"));
/*  806 */     this.botonConsultas.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  808 */         ResumenDiario.this.botonConsultasActionPerformed(evt);
/*      */       }
/*      */     });
/*  812 */     this.botonBorrarTicket.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/editdelete.png")));
/*  813 */     this.botonBorrarTicket.setText(bundle.getString("borrar"));
/*  814 */     this.botonBorrarTicket.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  816 */         ResumenDiario.this.botonBorrarTicketActionPerformed(evt);
/*      */       }
/*      */     });
/*  820 */     this.campoUnidades.setHorizontalAlignment(4);
/*  821 */     this.campoUnidades.setText("0");
/*      */ 
/*  823 */     this.campoImporte.setHorizontalAlignment(4);
/*  824 */     this.campoImporte.setText("0");
/*      */ 
/*  826 */     this.botonNota.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/notes.png")));
/*  827 */     this.botonNota.setText(bundle.getString("vernota"));
/*  828 */     this.botonNota.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  830 */         ResumenDiario.this.botonNotaActionPerformed(evt);
/*      */       }
/*      */     });
/*  834 */     this.labelCajaCerrada.setForeground(new Color(255, 0, 0));
/*  835 */     this.labelCajaCerrada.setText("(Caja cerrada)");
/*  836 */     this.labelCajaCerrada.addMouseListener(new MouseAdapter() {
/*      */       public void mouseClicked(MouseEvent evt) {
/*  838 */         ResumenDiario.this.labelCajaCerradaMouseClicked(evt);
/*      */       }
/*      */     });
/*  842 */     this.jPanel4.setBorder(BorderFactory.createEtchedBorder());
/*      */ 
/*  844 */     this.botonCerrarCaja.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/A18.png")));
/*  845 */     this.botonCerrarCaja.setText(bundle.getString("actConta"));
/*  846 */     this.botonCerrarCaja.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  848 */         ResumenDiario.this.botonCerrarCajaActionPerformed(evt);
/*      */       }
/*      */     });
/*  852 */     this.jCheckBox1.setText(bundle.getString("desglosaiva"));
/*  853 */     this.jCheckBox1.setToolTipText(bundle.getString("incluirIva"));
/*      */ 
/*  855 */     this.botonCerrarAlmacen.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/facturacion18.png")));
/*  856 */     this.botonCerrarAlmacen.setText(bundle.getString("actAlma"));
/*  857 */     this.botonCerrarAlmacen.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  859 */         ResumenDiario.this.botonCerrarAlmacenActionPerformed(evt);
/*      */       }
/*      */     });
/*  863 */     this.botonCierreTotal.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/close.png")));
/*  864 */     this.botonCierreTotal.setText(bundle.getString("cerrarCaja"));
/*  865 */     this.botonCierreTotal.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  867 */         ResumenDiario.this.botonCierreTotalActionPerformed(evt);
/*      */       }
/*      */     });
/*  871 */     GroupLayout jPanel4Layout = new GroupLayout(this.jPanel4);
/*  872 */     this.jPanel4.setLayout(jPanel4Layout);
/*  873 */     jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel4Layout.createSequentialGroup().addContainerGap().addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel4Layout.createSequentialGroup().addComponent(this.botonCerrarCaja).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jCheckBox1, -1, 179, 32767)).addGroup(jPanel4Layout.createSequentialGroup().addComponent(this.botonCerrarAlmacen).addGap(46, 46, 46).addComponent(this.botonCierreTotal))).addContainerGap()));
/*      */ 
/*  888 */     jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup().addContainerGap(-1, 32767).addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.botonCerrarAlmacen, -2, 35, -2).addComponent(this.botonCierreTotal)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.botonCerrarCaja, -2, 35, -2).addComponent(this.jCheckBox1)).addContainerGap()));
/*      */ 
/*  902 */     GroupLayout layout = new GroupLayout(getContentPane());
/*  903 */     getContentPane().setLayout(layout);
/*  904 */     layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addComponent(this.fechaJLabel, -2, 115, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.labelCajaCerrada, -2, 94, -2).addGap(188, 188, 188)).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addGroup(layout.createSequentialGroup().addGap(6, 6, 6).addComponent(this.jLabel1).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.campoUnidades, -2, 80, -2).addGap(48, 48, 48).addComponent(this.jLabel2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.campoImporte, -2, 120, -2).addGap(30, 30, 30).addComponent(this.botonNota).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.jButton1).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.botonBorrarTicket)).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.jScrollPane1, GroupLayout.Alignment.LEADING, -2, 629, -2).addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addGroup(layout.createSequentialGroup().addComponent(this.botonRecargar).addGap(18, 18, 18).addComponent(this.botonConsultas)).addComponent(this.jScrollPane2, -2, 277, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanel2, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jScrollPane3, 0, 277, 32767))).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jPanel1, -2, -1, -2).addComponent(this.jPanel3, -2, -1, -2)))).addContainerGap(11, 32767)))).addGroup(layout.createSequentialGroup().addGap(348, 348, 348).addComponent(this.jPanel4, -1, -1, 32767).addContainerGap()));
/*      */ 
/*  955 */     layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.fechaJLabel).addComponent(this.labelCajaCerrada)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jScrollPane1, -2, 190, -2).addComponent(this.jPanel1, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.botonBorrarTicket, -2, 35, -2).addComponent(this.jButton1, -2, 35, -2).addComponent(this.botonNota).addComponent(this.jLabel1).addComponent(this.jLabel2).addComponent(this.campoUnidades).addComponent(this.campoImporte)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jPanel2, -2, -1, -2).addComponent(this.jScrollPane2, -2, 158, -2).addComponent(this.jScrollPane3, -2, 157, -2).addComponent(this.jPanel3, -2, -1, -2)).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanel4, -2, -1, -2)).addGroup(layout.createSequentialGroup().addGap(31, 31, 31).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.botonConsultas, -2, 35, -2).addComponent(this.botonRecargar, -2, 35, -2)))).addContainerGap(-1, 32767)));
/*      */ 
/*  993 */     pack();
/*      */   }
/*      */ 
/*      */   private void inicioActionPerformed(ActionEvent evt) {
/*  997 */     inicio(this.tablaVentas);
/*      */   }
/*      */ 
/*      */   private void arribaActionPerformed(ActionEvent evt) {
/* 1001 */     arriba(this.tablaVentas);
/*      */   }
/*      */ 
/*      */   private void abajoActionPerformed(ActionEvent evt) {
/* 1005 */     abajo(this.tablaVentas);
/*      */   }
/*      */ 
/*      */   private void finActionPerformed(ActionEvent evt) {
/* 1009 */     fin(this.tablaVentas);
/*      */   }
/*      */ 
/*      */   private void jButton1ActionPerformed(ActionEvent evt) {
/* 1013 */     int fila = this.tablaVentas.getSelectedRow();
/* 1014 */     if (fila != -1) {
/* 1015 */       ResumenVentaTicket objeto = this.modeloVentas.getObjectAt(fila);
/* 1016 */       if (objeto != null)
/* 1017 */         Inicio.frame.editarTicketVenta(objeto.getIdTicket());
/*      */     }
/*      */   }
/*      */ 
/*      */   private void inicio1ActionPerformed(ActionEvent evt)
/*      */   {
/* 1023 */     inicio(this.tablaFamilias);
/*      */   }
/*      */ 
/*      */   private void arriba1ActionPerformed(ActionEvent evt) {
/* 1027 */     arriba(this.tablaFamilias);
/*      */   }
/*      */ 
/*      */   private void abajo1ActionPerformed(ActionEvent evt) {
/* 1031 */     abajo(this.tablaFamilias);
/*      */   }
/*      */ 
/*      */   private void fin1ActionPerformed(ActionEvent evt) {
/* 1035 */     fin(this.tablaFamilias);
/*      */   }
/*      */ 
/*      */   private void inicio2ActionPerformed(ActionEvent evt) {
/* 1039 */     inicio(this.tablaMedios);
/*      */   }
/*      */ 
/*      */   private void arriba2ActionPerformed(ActionEvent evt) {
/* 1043 */     arriba(this.tablaMedios);
/*      */   }
/*      */ 
/*      */   private void abajo2ActionPerformed(ActionEvent evt) {
/* 1047 */     abajo(this.tablaMedios);
/*      */   }
/*      */ 
/*      */   private void fin2ActionPerformed(ActionEvent evt) {
/* 1051 */     fin(this.tablaMedios);
/*      */   }
/*      */ 
/*      */   private void botonRecargarActionPerformed(ActionEvent evt) {
/* 1055 */     generarTablas(this.fechaEnUso);
/*      */   }
/*      */ 
/*      */   private void botonCerrarCajaActionPerformed(ActionEvent evt) {
/* 1059 */     if ((isCajaCerrada() != 2) && (isCajaCerrada() != 3)) {
/* 1060 */       cerrarCajaDia(false);
/*      */     }
/*      */     else
/* 1063 */       mostrarError("¡Esta caja parece estar ya cerrada en contabilidad!");
/*      */   }
/*      */ 
/*      */   private void botonConsultasActionPerformed(ActionEvent evt)
/*      */   {
/* 1068 */     Date seleccionfecha = JCalendarDialog.showCalendar(getParent(), this.fechaEnUso);
/* 1069 */     if (seleccionfecha != null) {
/* 1070 */       this.fechaEnUso.setTime(seleccionfecha.getTime());
/* 1071 */       generarTablas(this.fechaEnUso);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void botonBorrarTicketActionPerformed(ActionEvent evt) {
/* 1076 */     int fila = this.tablaVentas.getSelectedRow();
/* 1077 */     if (fila != -1) {
/* 1078 */       ResumenVentaTicket objeto = this.modeloVentas.getObjectAt(fila);
/* 1079 */       if (objeto != null)
/* 1080 */         if (objeto.isCerrado() == 0) {
/* 1081 */           TicketsControl tC = new TicketsControl(Inicio.getcAlmacen());
/* 1082 */           Ticket ticket = tC.getTicket(objeto.getIdTicket());
/* 1083 */           if (tC.borrar(ticket))
/* 1084 */             generarTablas(this.fechaEnUso);
/*      */         }
/*      */         else
/*      */         {
/* 1088 */           mostrarError("No se puede eliminar un ticket ya cerrado");
/*      */         }
/*      */     }
/*      */   }
/*      */ 
/*      */   private void fechaJLabelMouseClicked(MouseEvent evt)
/*      */   {
/* 1095 */     if (evt.getClickCount() == 2)
/* 1096 */       makeListado();
/*      */   }
/*      */ 
/*      */   private void botonNotaActionPerformed(ActionEvent evt)
/*      */   {
/* 1101 */     mostrarNota();
/*      */   }
/*      */ 
/*      */   private void labelCajaCerradaMouseClicked(MouseEvent evt) {
/* 1105 */     if (evt.getClickCount() == 2) {
/* 1106 */       int qst = JOptionPane.showConfirmDialog(getContentPane(), "Este proceso sólo marca la caja como no cerrada.\nNO deshace los apuntes contables, ni las altas\ny bajas en almacén. ¿Desea continuar?", Mensajes.getString("confirma"), 0);
/*      */ 
/* 1111 */       if ((qst == 0) && 
/* 1112 */         (isCajaCerrada() != 0))
/* 1113 */         cambiarCajaANoCerrada();
/*      */     }
/*      */   }
/*      */ 
/*      */   private void formInternalFrameClosed(InternalFrameEvent evt)
/*      */   {
/* 1120 */     if (!Inicio.p.isModoPOS())
/* 1121 */       Inicio.frame.eliminarMarcoMenu(Mensajes.getString("resumendia"));
/*      */   }
/*      */ 
/*      */   private void formInternalFrameClosing(InternalFrameEvent evt)
/*      */   {
/* 1126 */     ArrayList estado = new ArrayList();
/* 1127 */     if (this.jCheckBox1.isSelected()) {
/* 1128 */       estado.add("1");
/*      */     }
/*      */     else {
/* 1131 */       estado.add("0");
/*      */     }
/* 1133 */     ConfiguracionBean config = new ConfiguracionBean();
/* 1134 */     config.saveConfig("<desgIvaTerminal>", estado);
/*      */   }
/*      */ 
/*      */   private void botonCerrarAlmacenActionPerformed(ActionEvent evt) {
/* 1138 */     if ((isCajaCerrada() != 1) && (isCajaCerrada() != 3)) {
/* 1139 */       cerrarCajaDia(true);
/*      */     }
/*      */     else
/* 1142 */       mostrarError("¡Esta caja parece estar ya cerrada en almacén!");
/*      */   }
/*      */ 
/*      */   private void botonCierreTotalActionPerformed(ActionEvent evt)
/*      */   {
/* 1147 */     if ((isCajaCerrada() != 1) && (isCajaCerrada() != 3)) {
/* 1148 */       cerrarCajaDia(true);
/*      */     }
/*      */     else {
/* 1151 */       mostrarError("¡Esta caja parece estar ya cerrada en almacén!");
/*      */     }
/* 1153 */     if ((isCajaCerrada() != 2) && (isCajaCerrada() != 3)) {
/* 1154 */       cerrarCajaDia(false);
/*      */     }
/*      */     else
/* 1157 */       mostrarError("¡Esta caja parece estar ya cerrada en contabilidad!");
/*      */   }
/*      */ 
/*      */   private void setSavedCBState()
/*      */   {
/* 1162 */     ArrayList array = new ArrayList();
/* 1163 */     ConfiguracionBean config = new ConfiguracionBean();
/* 1164 */     array = config.getConfig("<desgIvaTerminal>");
/* 1165 */     if (!array.isEmpty()) {
/* 1166 */       String estado = (String)array.get(0);
/* 1167 */       if (estado.equals("1")) {
/* 1168 */         this.jCheckBox1.setSelected(true);
/*      */       }
/*      */       else
/* 1171 */         this.jCheckBox1.setSelected(false);
/*      */     }
/*      */   }
/*      */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.view.ResumenDiario
 * JD-Core Version:    0.6.2
 */