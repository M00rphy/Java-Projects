/*      */ package facturacion.view;
/*      */ 
/*      */ import contaes.Inicio;
/*      */ import contaes.MarcoInicio;
/*      */ import contaes.Puente;
/*      */ import contaes.auxiliar.DocNumeros;
/*      */ import contaes.auxiliarTablas.GeneralColorRenderer;
/*      */ import contaes.auxiliarTablas.ImporteColorRenderer;
/*      */ import contaes.calendario.ICalendarTextField;
/*      */ import contaes.dialogosAuxiliares.MostrarCuentas;
/*      */ import contaes.dialogosFunciones.Calculadora;
/*      */ import contaes.manejoDatos.ManejoAgenda;
/*      */ import contaes.manejoDatos.ManejoFormasPago;
/*      */ import contaes.manejoDatos.ManejoSubcuentas;
/*      */ import contaes.manejoDatos.ManejoTiposIVA;
/*      */ import contaes.manejoDatos.TipoFormaPago;
/*      */ import contaes.manejoDatos.TipoIVA;
/*      */ import contaes.manejoDatos.TipoSubcuenta;
/*      */ import facturacion.control.AlmacenControl;
/*      */ import facturacion.control.FacturaControl;
/*      */ import facturacion.control.LineaFacturaControl;
/*      */ import facturacion.model.Factura;
/*      */ import facturacion.model.LineaFactura;
/*      */ import facturacion.model.Producto;
/*      */ import internationalization.Mensajes;
/*      */ import java.awt.Color;
/*      */ import java.awt.Container;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.Point;
/*      */ import java.awt.Toolkit;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.ActionListener;
/*      */ import java.awt.event.FocusAdapter;
/*      */ import java.awt.event.FocusEvent;
/*      */ import java.awt.event.ItemEvent;
/*      */ import java.awt.event.ItemListener;
/*      */ import java.awt.event.KeyAdapter;
/*      */ import java.awt.event.KeyEvent;
/*      */ import java.awt.event.MouseAdapter;
/*      */ import java.awt.event.MouseEvent;
/*      */ import java.math.BigDecimal;
/*      */ import java.text.DecimalFormat;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Date;
import java.util.List;
/*      */ import java.util.ResourceBundle;
/*      */ import java.util.logging.Level;
/*      */ import java.util.logging.Logger;
/*      */ import javax.swing.BorderFactory;
/*      */ import javax.swing.DefaultComboBoxModel;
/*      */ import javax.swing.ImageIcon;
/*      */ import javax.swing.JButton;
/*      */ import javax.swing.JCheckBox;
/*      */ import javax.swing.JComboBox;
/*      */ import javax.swing.JFormattedTextField;
/*      */ import javax.swing.JInternalFrame;
/*      */ import javax.swing.JLabel;
/*      */ import javax.swing.JOptionPane;
/*      */ import javax.swing.JPanel;
/*      */ import javax.swing.JScrollPane;
/*      */ import javax.swing.JTable;
/*      */ import javax.swing.JTextField;
/*      */ import javax.swing.event.InternalFrameEvent;
/*      */ import javax.swing.event.InternalFrameListener;
/*      */ import javax.swing.table.DefaultTableModel;
/*      */ import javax.swing.table.TableColumn;
/*      */ import javax.swing.table.TableColumnModel;
/*      */ import javax.swing.text.DefaultFormatterFactory;
/*      */ import javax.swing.text.NumberFormatter;
/*      */ import org.jdesktop.layout.GroupLayout;
/*      */ import org.jdesktop.layout.GroupLayout.ParallelGroup;
/*      */ import org.jdesktop.layout.GroupLayout.SequentialGroup;
/*      */ 
/*      */ public class FacturasJInternalFrame extends JInternalFrame
/*      */ {
/*   54 */   DefaultTableModel modeloTabla = getTableModelLineas(null);
/*   55 */   DefaultComboBoxModel modeloComboClientes = new DefaultComboBoxModel();
/*   56 */   DefaultComboBoxModel modeloComboFormaPago = new DefaultComboBoxModel();
/*   57 */   DefaultComboBoxModel modeloComboTiposIva = new DefaultComboBoxModel();
/*      */ 
/*   60 */   Double baseTotal = Double.valueOf(0.0D);
/*   61 */   Double ivaTotal = Double.valueOf(0.0D);
/*   62 */   Double totalTotal = Double.valueOf(0.0D);
/*      */ 
/*   64 */   boolean ventas = true;
/*   65 */   Integer idFactura = Integer.valueOf(-1);
/*      */   Producto productoEnUso;
/*      */   private JButton botonAddToTable;
/*      */   private JButton botonContabilizar;
/*      */   private JButton botonCrear;
/*      */   private JButton botonLimpiar;
/*      */   private JButton botonPdf;
/*      */   private JTextField campoBase;
/*      */   private JTextField campoConcepto;
/*      */   private ICalendarTextField campoFecha;
/*      */   private JFormattedTextField campoInfoBase;
/*      */   private JFormattedTextField campoInfoIva;
/*      */   private JFormattedTextField campoInfoTotal;
/*      */   private JTextField campoNombreProducto;
/*      */   private JTextField campoNumero;
/*      */   private JTextField campoReferencia;
/*      */   private JTextField campoRetencion;
/*      */   private JTextField campoUnidades;
/*      */   private JComboBox comboClientes;
/*      */   private JComboBox comboFormaPago;
/*      */   private JComboBox comboTiposIva;
/*      */   private JCheckBox esRecargo;
/*      */   private JButton jButton1;
/*      */   private JButton jButton2;
/*      */   private JLabel jLabel1;
/*      */   private JLabel jLabel10;
/*      */   private JLabel jLabel11;
/*      */   private JLabel jLabel12;
/*      */   private JLabel jLabel13;
/*      */   private JLabel jLabel14;
/*      */   private JLabel jLabel2;
/*      */   private JLabel jLabel3;
/*      */   private JLabel jLabel4;
/*      */   private JLabel jLabel5;
/*      */   private JLabel jLabel6;
/*      */   private JLabel jLabel7;
/*      */   private JLabel jLabel8;
/*      */   private JLabel jLabel9;
/*      */   private JPanel jPanel1;
/*      */   private JPanel jPanel2;
/*      */   private JScrollPane jScrollPane1;
/*      */   private JTable tablaLineas;
/*      */ 
/*      */   public FacturasJInternalFrame()
/*      */   {
/*   71 */     initComponents();
/*   72 */     this.campoFecha.setComponente(this.comboClientes);
/*   73 */     llenarCombos();
/*   74 */     fijarAnchoTabla();
/*   75 */     limpiarForm();
/*      */   }
/*      */ 
/*      */   public void renovarModeloSubcuentas() {
/*      */     try {
/*   80 */       this.modeloComboClientes.removeAllElements();
/*   81 */       ManejoSubcuentas mS = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*   82 */       ArrayList subcuentas = mS.listaSubcuentas(43000000, 44999999);
/*   83 */       for (TipoSubcuenta subc :(List<TipoSubcuenta>) subcuentas) {
/*   84 */         this.modeloComboClientes.addElement(subc);
/*      */       }
/*   86 */       this.comboClientes.setModel(this.modeloComboClientes);
/*      */     } catch (Exception ex) {
/*   88 */       Logger.getLogger(FacturasProveedoresJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void renovarModeloFormasPago() {
/*      */     try {
/*   94 */       this.modeloComboFormaPago.removeAllElements();
/*   95 */       ManejoFormasPago mFP = new ManejoFormasPago(Inicio.getCGeneral());
/*   96 */       ArrayList formasPago = mFP.getFormasPago();
/*   97 */       for (TipoFormaPago formaP :(List<TipoFormaPago>)  formasPago) {
/*   98 */         this.modeloComboFormaPago.addElement(formaP);
/*      */       }
/*  100 */       this.comboFormaPago.setModel(this.modeloComboFormaPago);
/*      */     } catch (Exception ex) {
/*  102 */       Logger.getLogger(FacturasProveedoresJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void renovarModeloIva() {
/*      */     try {
/*  108 */       this.modeloComboTiposIva.removeAllElements();
/*  109 */       ManejoTiposIVA mTI = new ManejoTiposIVA(Inicio.getCGeneral());
/*  110 */       ArrayList tiposIva = mTI.getTiposIVA();
/*  111 */       for (TipoIVA tipoIVA : (List<TipoIVA>) tiposIva) {
/*  112 */         this.modeloComboTiposIva.addElement(tipoIVA);
/*      */       }
/*  114 */       this.comboTiposIva.setModel(this.modeloComboTiposIva);
/*      */     } catch (Exception ex) {
/*  116 */       Logger.getLogger(FacturasProveedoresJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void llenarCombos() {
/*      */     try {
/*  122 */       ManejoSubcuentas mS = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  123 */       ArrayList subcuentas = mS.listaSubcuentas(43000000, 44999999);
/*  124 */       for (TipoSubcuenta subc :(List<TipoSubcuenta>)  subcuentas) {
/*  125 */         this.modeloComboClientes.addElement(subc);
/*      */       }
/*  127 */       this.comboClientes.setModel(this.modeloComboClientes);
/*      */ 
/*  129 */       ManejoFormasPago mFP = new ManejoFormasPago(Inicio.getCGeneral());
/*  130 */       ArrayList formasPago = mFP.getFormasPago();
/*  131 */       for (TipoFormaPago formaP :(List<TipoFormaPago>)  formasPago) {
/*  132 */         this.modeloComboFormaPago.addElement(formaP);
/*      */       }
/*  134 */       this.comboFormaPago.setModel(this.modeloComboFormaPago);
/*      */ 
/*  136 */       ManejoTiposIVA mTI = new ManejoTiposIVA(Inicio.getCGeneral());
/*  137 */       ArrayList tiposIva = mTI.getTiposIVA();
/*  138 */       for (TipoIVA tipoIVA :(List<TipoIVA>)  tiposIva) {
/*  139 */         this.modeloComboTiposIva.addElement(tipoIVA);
/*      */       }
/*  141 */       this.comboTiposIva.setModel(this.modeloComboTiposIva);
/*      */     }
/*      */     catch (Exception ex)
/*      */     {
/*  153 */       Logger.getLogger(FacturasJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
/*      */     }
/*      */   }
/*      */ 
/*      */   private DefaultTableModel getTableModelLineas(ArrayList<LineaFactura> lineas)
/*      */   {
/*  159 */     DefaultTableModel tableModel = new DefaultTableModel(new Object[0][], new String[0])
/*      */     {
/*  166 */       Class[] types = { Double.class, Producto.class, String.class, Double.class, TipoIVA.class, Double.class, Double.class, Double.class };
/*      */ 
/*  178 */       boolean[] canEdit = { false, false, false, false, false, false, false, false };
/*      */ 
/*      */       public Class getColumnClass(int columnIndex)
/*      */       {
/*  186 */         return this.types[columnIndex];
/*      */       }
/*      */ 
/*      */       public boolean isCellEditable(int rowIndex, int columnIndex)
/*      */       {
/*  192 */         return this.canEdit[columnIndex];
/*      */       }
/*      */     };
/*  195 */     int headerSize = 8;
/*  196 */     String[] header = new String[headerSize];
/*  197 */     header[0] = "Uni.";
/*  198 */     header[1] = "Producto";
/*  199 */     header[2] = "Concepto";
/*  200 */     header[3] = "Base";
/*  201 */     header[4] = "Tipo IVA";
/*  202 */     header[5] = "IVA";
/*  203 */     header[6] = "RE";
/*  204 */     header[7] = "Total";
/*      */ 
/*  206 */     Object[][] objects = (Object[][])null;
/*      */     int i;
/*  208 */     if ((lineas != null) && (lineas.size() > 0))
/*      */     {
/*  210 */       objects = new Object[lineas.size()][headerSize];
/*  211 */       i = 0;
/*  212 */       for (LineaFactura a : lineas)
/*      */       {
/*  214 */         if (a.getId() != null)
/*      */         {
/*  216 */           objects[i][0] = a.getUnidades();
/*      */         }
/*  218 */         Double iva = Double.valueOf(a.getBase().doubleValue() * a.getTipoIva().getIva() / 100.0D);
/*  219 */         Double re = Double.valueOf(a.getBase().doubleValue() * a.getTipoIva().getRe() / 100.0D);
/*  220 */         objects[i][1] = a.getIdProducto();
/*  221 */         objects[i][2] = a.getConcepto();
/*  222 */         objects[i][3] = a.getBase();
/*  223 */         objects[i][4] = a.getTipoIva();
/*  224 */         objects[i][5] = iva;
/*  225 */         objects[i][6] = re;
/*  226 */         objects[i][7] = Double.valueOf(a.getBase().doubleValue() + iva.doubleValue() + re.doubleValue());
/*  227 */         i++;
/*      */       }
/*      */     }
/*  230 */     tableModel.setDataVector(objects, header);
/*  231 */     return tableModel;
/*      */   }
/*      */ 
/*      */   private void fijarAnchoTabla() {
/*  235 */     int anchoTabla = 650;
/*      */ 
/*  237 */     TableColumn columna = this.tablaLineas.getColumnModel().getColumn(0);
/*  238 */     columna.setPreferredWidth((int)(anchoTabla * 0.05D));
/*  239 */     columna.setCellRenderer(new GeneralColorRenderer());
/*  240 */     columna = this.tablaLineas.getColumnModel().getColumn(1);
/*  241 */     columna.setPreferredWidth((int)(anchoTabla * 0.2D));
/*  242 */     columna.setCellRenderer(new GeneralColorRenderer());
/*  243 */     columna = this.tablaLineas.getColumnModel().getColumn(2);
/*  244 */     columna.setPreferredWidth((int)(anchoTabla * 0.2D));
/*  245 */     columna.setCellRenderer(new GeneralColorRenderer());
/*  246 */     columna = this.tablaLineas.getColumnModel().getColumn(3);
/*  247 */     columna.setPreferredWidth((int)(anchoTabla * 0.1D));
/*  248 */     columna.setCellRenderer(new ImporteColorRenderer());
/*  249 */     columna = this.tablaLineas.getColumnModel().getColumn(4);
/*  250 */     columna.setPreferredWidth((int)(anchoTabla * 0.15D));
/*  251 */     columna.setCellRenderer(new GeneralColorRenderer());
/*  252 */     columna = this.tablaLineas.getColumnModel().getColumn(5);
/*  253 */     columna.setPreferredWidth((int)(anchoTabla * 0.1D));
/*  254 */     columna.setCellRenderer(new ImporteColorRenderer());
/*  255 */     columna = this.tablaLineas.getColumnModel().getColumn(6);
/*  256 */     columna.setPreferredWidth((int)(anchoTabla * 0.1D));
/*  257 */     columna.setCellRenderer(new ImporteColorRenderer());
/*  258 */     columna = this.tablaLineas.getColumnModel().getColumn(7);
/*  259 */     columna.setPreferredWidth((int)(anchoTabla * 0.1D));
/*  260 */     columna.setCellRenderer(new ImporteColorRenderer());
/*      */   }
/*      */ 
/*      */   private void limpiarForm() {
/*  264 */     this.baseTotal = Double.valueOf(0.0D);
/*  265 */     this.ivaTotal = Double.valueOf(0.0D);
/*  266 */     this.totalTotal = Double.valueOf(0.0D);
/*  267 */     this.modeloTabla = getTableModelLineas(null);
/*  268 */     this.tablaLineas.setModel(this.modeloTabla);
/*  269 */     this.campoFecha.setDate(new Date());
/*  270 */     this.campoRetencion.setText("");
/*  271 */     this.esRecargo.setSelected(false);
/*      */ 
/*  273 */     this.campoUnidades.setText("");
/*  274 */     this.campoConcepto.setText("");
/*  275 */     this.campoBase.setText("");
/*  276 */     this.campoInfoBase.setText("");
/*  277 */     this.campoInfoIva.setText("");
/*  278 */     this.campoInfoTotal.setText("");
/*  279 */     FacturaControl fC = new FacturaControl(Inicio.getCFacturacion(), this.ventas);
/*  280 */     this.campoNumero.setText(fC.nuevoNumeroFactEmitida(Inicio.p.getPrefijo()));
/*  281 */     fC.cerrarRs();
/*  282 */     this.idFactura = Integer.valueOf(-1);
/*  283 */     this.botonCrear.setEnabled(true);
/*  284 */     this.botonContabilizar.setText("Pasar a contabilidad");
/*      */     try {
/*  286 */       this.comboClientes.setSelectedIndex(0);
/*  287 */       this.comboFormaPago.setSelectedIndex(0);
/*  288 */       this.comboTiposIva.setSelectedIndex(0);
/*      */     }
/*      */     catch (IllegalArgumentException exc) {
/*  291 */       exc.printStackTrace();
/*      */     }
/*      */   }
/*      */ 
/*      */   private void limpiarFormProducto() {
/*  296 */     this.campoReferencia.setText("");
/*  297 */     this.campoNombreProducto.setText("");
/*  298 */     this.campoUnidades.setText("");
/*  299 */     this.campoConcepto.setText("");
/*  300 */     this.campoBase.setText("");
/*      */   }
/*      */ 
/*      */   private void addLineToTable() {
/*  304 */     TipoIVA tipoIva = (TipoIVA)this.comboTiposIva.getSelectedItem();
/*  305 */     TipoSubcuenta cliente = (TipoSubcuenta)this.comboClientes.getSelectedItem();
/*  306 */     if ((cliente != null) && (tipoIva != null)) {
/*  307 */       ManejoSubcuentas mS = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  308 */       int origen = mS.getOrigen(cliente.getCodigo()).intValue();
/*  309 */       mS.cerrarRs();
/*  310 */       if ((tipoIva.getIva() != 0.0D) && (origen != 0)) {
/*  311 */         int pregunta = JOptionPane.showConfirmDialog(getContentPane(), "Para exportaciones y entregas intracomunitarias debería\nhaber seleccionado un tipo de IVA cero.\n¿Está seguro de que desea continuar?.", Mensajes.getString("confirma"), 0);
/*      */ 
/*  316 */         if (pregunta == 1) {
/*  317 */           return;
/*      */         }
/*      */       }
/*      */     }
/*  321 */     Object[] linea = new Object[8];
/*  322 */     String stUni = this.campoUnidades.getText();
/*  323 */     if ((!stUni.equals("")) && (esDoble(stUni))) {
/*  324 */       Double unidades = Double.valueOf(Double.parseDouble(stUni));
/*  325 */       linea[0] = unidades;
/*      */ 
/*  327 */       if (this.productoEnUso != null) {
/*  328 */         linea[1] = this.productoEnUso;
/*  329 */         linea[2] = this.campoConcepto.getText();
/*  330 */         String stBase = this.campoBase.getText();
/*  331 */         if ((!stBase.equals("")) && (esDoble(stBase))) {
/*  332 */           Double base = Double.valueOf(Double.parseDouble(stBase));
/*  333 */           base = Double.valueOf(base.doubleValue() * unidades.doubleValue());
/*  334 */           linea[3] = base;
/*      */ 
/*  336 */           if (tipoIva != null) {
/*  337 */             linea[4] = tipoIva;
/*  338 */             Double iva = Double.valueOf(base.doubleValue() * tipoIva.getIva() / 100.0D);
/*  339 */             linea[5] = iva;
/*  340 */             Double re = Double.valueOf(0.0D);
/*  341 */             if (this.esRecargo.isSelected()) {
/*  342 */               re = Double.valueOf(base.doubleValue() * tipoIva.getRe() / 100.0D);
/*      */             }
/*  344 */             linea[6] = re;
/*  345 */             Double total = Double.valueOf(base.doubleValue() + iva.doubleValue() + re.doubleValue());
/*  346 */             linea[7] = total;
/*  347 */             this.baseTotal = Double.valueOf(this.baseTotal.doubleValue() + base.doubleValue());
/*  348 */             this.ivaTotal = Double.valueOf(this.ivaTotal.doubleValue() + (iva.doubleValue() + re.doubleValue()));
/*  349 */             this.totalTotal = Double.valueOf(this.totalTotal.doubleValue() + total.doubleValue());
/*  350 */             this.campoInfoBase.setValue(this.baseTotal);
/*  351 */             this.campoInfoIva.setValue(this.ivaTotal);
/*  352 */             this.campoInfoTotal.setValue(this.totalTotal);
/*  353 */             this.modeloTabla.addRow(linea);
/*  354 */             limpiarFormProducto();
/*      */           }
/*      */           else {
/*  357 */             mostrarError("Debe seleccionar un tipo de IVA");
/*      */           }
/*      */         }
/*      */         else {
/*  361 */           mostrarError("No ha introducido base imponible o el número introducido no es válido");
/*      */         }
/*      */       }
/*      */       else {
/*  365 */         mostrarError("Debe seleccionar un producto");
/*      */       }
/*      */     }
/*      */     else {
/*  369 */       mostrarError("No ha introducido unidades o el número introducido no es válido");
/*      */     }
/*      */   }
/*      */ 
/*      */   private void removeLineFromTable(int fila) {
/*  374 */     if ((fila >= 0) && (fila < this.modeloTabla.getRowCount())) {
/*  375 */       Double unidades = (Double)this.modeloTabla.getValueAt(fila, 0);
/*  376 */       Producto producto = (Producto)this.modeloTabla.getValueAt(fila, 1);
/*  377 */       String concepto = (String)this.modeloTabla.getValueAt(fila, 2);
/*  378 */       Double base = (Double)this.modeloTabla.getValueAt(fila, 3);
/*  379 */       TipoIVA tipoIva = (TipoIVA)this.modeloTabla.getValueAt(fila, 4);
/*  380 */       Double iva = (Double)this.modeloTabla.getValueAt(fila, 5);
/*  381 */       Double re = (Double)this.modeloTabla.getValueAt(fila, 6);
/*  382 */       Double total = (Double)this.modeloTabla.getValueAt(fila, 7);
/*  383 */       this.baseTotal = Double.valueOf(this.baseTotal.doubleValue() - base.doubleValue());
/*  384 */       this.ivaTotal = Double.valueOf(this.ivaTotal.doubleValue() - (iva.doubleValue() + re.doubleValue()));
/*  385 */       this.totalTotal = Double.valueOf(this.totalTotal.doubleValue() - total.doubleValue());
/*  386 */       this.campoInfoBase.setValue(this.baseTotal);
/*  387 */       this.campoInfoIva.setValue(this.ivaTotal);
/*  388 */       this.campoInfoTotal.setValue(this.totalTotal);
/*  389 */       this.modeloTabla.removeRow(fila);
/*      */ 
/*  391 */       updateProductoEnUso(producto);
/*  392 */       this.campoUnidades.setText(unidades.toString());
/*      */ 
/*  394 */       this.campoConcepto.setText(concepto);
/*      */ 
/*  396 */       this.comboTiposIva.setSelectedItem(tipoIva);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void cargarFactura(Factura factura) {
/*  401 */     limpiarForm();
/*  402 */     this.idFactura = factura.getId();
/*  403 */     this.campoNumero.setText(factura.getNumero());
/*  404 */     this.campoFecha.setDate(factura.getFecha());
/*  405 */     this.comboClientes.setSelectedItem(factura.getCliente());
/*  406 */     this.esRecargo.setSelected(factura.isRecargo());
/*  407 */     this.campoRetencion.setText(factura.getRetencion().toString());
/*  408 */     if (factura.getFormaPago() != null)
/*  409 */       this.comboFormaPago.setSelectedItem(factura.getFormaPago());
/*  410 */     this.campoInfoBase.setValue(factura.getBase());
/*  411 */     this.baseTotal = factura.getBase();
/*  412 */     this.campoInfoIva.setValue(factura.getIva());
/*  413 */     this.ivaTotal = factura.getIva();
/*  414 */     this.campoInfoTotal.setValue(factura.getTotal());
/*  415 */     this.totalTotal = factura.getTotal();
/*  416 */     if (factura.isContabilizada()) {
/*  417 */       this.botonCrear.setEnabled(false);
/*  418 */       this.botonContabilizar.setText("No contabilizada");
/*      */     }
/*  420 */     LineaFacturaControl lFC = new LineaFacturaControl(Inicio.getCFacturacion(), this.ventas);
/*      */     try {
/*  422 */       ArrayList lineas = lFC.lineas(this.idFactura);
/*  423 */       for (LineaFactura linea : (List<LineaFactura>) lineas) {
/*  424 */         Double iva = Double.valueOf(linea.getBase().doubleValue() * linea.getTipoIva().getIva() / 100.0D);
/*  425 */         Double re = Double.valueOf(0.0D);
/*  426 */         if (this.esRecargo.isSelected()) {
/*  427 */           re = Double.valueOf(linea.getBase().doubleValue() * linea.getTipoIva().getRe() / 100.0D);
/*      */         }
/*  429 */         Object[] fila = new Object[8];
/*  430 */         fila[0] = linea.getUnidades();
/*  431 */         fila[1] = linea.getIdProducto();
/*  432 */         fila[2] = linea.getConcepto();
/*  433 */         fila[3] = linea.getBase();
/*  434 */         fila[4] = linea.getTipoIva();
/*  435 */         fila[5] = iva;
/*  436 */         fila[6] = re;
/*  437 */         fila[7] = Double.valueOf(linea.getBase().doubleValue() + iva.doubleValue() + re.doubleValue());
/*  438 */         this.modeloTabla.addRow(fila);
/*      */       }
/*      */     } catch (Exception ex) {
/*  441 */       Logger.getLogger(FacturasJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
/*      */     }
/*  443 */     lFC.cerrarRs();
/*      */   }
/*      */ 
/*      */   private Factura leerFacturaFromForm() {
/*  447 */     Factura factura = null;
/*  448 */     String numero = this.campoNumero.getText();
/*  449 */     if ((numero != null) && (!numero.equals(""))) {
/*  450 */       Date fecha = this.campoFecha.getDate();
/*  451 */       if (fecha != null) {
/*  452 */         TipoSubcuenta cliente = (TipoSubcuenta)this.comboClientes.getSelectedItem();
/*  453 */         if (cliente != null) {
/*  454 */           String stRetenc = this.campoRetencion.getText();
/*  455 */           Double retencion = Double.valueOf(0.0D);
/*  456 */           if ((!stRetenc.equals("")) && (esDoble(stRetenc))) {
/*  457 */             retencion = Double.valueOf(Double.parseDouble(stRetenc));
/*      */           }
/*  459 */           TipoFormaPago formaPago = (TipoFormaPago)this.comboFormaPago.getSelectedItem();
/*  460 */           if (this.totalTotal.doubleValue() != 0.0D) {
/*  461 */             factura = new Factura(this.idFactura, numero, cliente, fecha, retencion, this.esRecargo.isSelected(), formaPago, this.baseTotal, this.ivaTotal, false, false);
/*      */           }
/*      */           else
/*  464 */             mostrarError("Parece no haber datos en la factura");
/*      */         }
/*      */         else
/*      */         {
/*  468 */           mostrarError("Debe especificar un cliente");
/*      */         }
/*      */       }
/*      */       else {
/*  472 */         mostrarError("Debe especificar una fecha");
/*      */       }
/*      */     }
/*      */     else {
/*  476 */       mostrarError("Debe especificar un número de factura");
/*      */     }
/*  478 */     return factura;
/*      */   }
/*      */ 
/*      */   private ArrayList<LineaFactura> leerLineasFromForm(Integer idFactura) {
/*  482 */     ArrayList lineas = new ArrayList();
/*      */ 
/*  484 */     for (int x = 0; x < this.modeloTabla.getRowCount(); x++) {
/*  485 */       LineaFactura linea = null;
/*  486 */       Double unidades = (Double)this.modeloTabla.getValueAt(x, 0);
/*  487 */       Producto producto = (Producto)this.modeloTabla.getValueAt(x, 1);
/*  488 */       String concepto = (String)this.modeloTabla.getValueAt(x, 2);
/*  489 */       Double base = (Double)this.modeloTabla.getValueAt(x, 3);
/*  490 */       TipoIVA tipoIva = (TipoIVA)this.modeloTabla.getValueAt(x, 4);
/*  491 */       Double total = (Double)this.modeloTabla.getValueAt(x, 7);
/*  492 */       linea = new LineaFactura(Integer.valueOf(-1), idFactura, producto, concepto, base, tipoIva, total, unidades);
/*  493 */       lineas.add(linea);
/*      */     }
/*  495 */     return lineas;
/*      */   }
/*      */ 
/*      */   private int getIdAlmacen() {
/*  499 */     ActualizarAlmacenJDialog dlg = new ActualizarAlmacenJDialog(Inicio.frame, true);
/*  500 */     Dimension dlgSize = dlg.getSize();
/*  501 */     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/*  502 */     dlg.setLocation((screenSize.width - dlgSize.width) / 2, (screenSize.height - dlgSize.height) / 2);
/*      */ 
/*  504 */     dlg.setVisible(true);
/*  505 */     int idAlmacen = dlg.getIdAlmacen();
/*  506 */     return idAlmacen;
/*      */   }
/*      */ 
/*      */   private void crearFactura() {
/*  510 */     FacturaControl fC = new FacturaControl(Inicio.getCFacturacion(), this.ventas);
/*  511 */     int idAlmacen = getIdAlmacen();
/*  512 */     boolean isAlmacenada = idAlmacen != -1;
/*      */     try {
/*  514 */       AlmacenControl aC = new AlmacenControl();
/*  515 */       Factura factura = leerFacturaFromForm();
/*  516 */       if (factura != null) {
/*  517 */         ArrayList lineas = leerLineasFromForm(factura.getId());
/*  518 */         if (lineas.size() > 0) {
/*  519 */           if (factura.getId().intValue() != -1) {
/*  520 */             if ((!isAlmacenada) && (fC.isAlmacenada(factura.getId()))) {
/*  521 */               isAlmacenada = true;
/*      */             }
/*  523 */             factura.setIsAlmacenada(isAlmacenada);
/*      */ 
/*  525 */             fC.modificar(factura);
/*  526 */             fC.cerrarRs();
/*  527 */             LineaFacturaControl lFC = new LineaFacturaControl(Inicio.getCFacturacion(), this.ventas);
/*  528 */             ArrayList lineasOld = lFC.lineas(factura.getId());
/*  529 */             for (LineaFactura lineaFactura :(List<LineaFactura>) lineasOld) {
/*  530 */               lFC.borrar(lineaFactura);
/*  531 */               if (idAlmacen != -1) {
/*  532 */                 suprimirSalidaAlmacen(aC, lineaFactura, factura.getFecha());
/*      */               }
/*      */             }
/*  535 */             for (LineaFactura lineaFactura :(List<LineaFactura>) lineas) {
/*  536 */               if (lineaFactura != null) {
/*  537 */                 lFC.crear(lineaFactura);
/*  538 */                 if (idAlmacen != -1) {
/*  539 */                   crearSalidaAlmacen(aC, lineaFactura, factura.getFecha(), idAlmacen);
/*      */                 }
/*      */               }
/*      */             }
/*  543 */             lFC.cerrarRs();
/*      */           }
/*      */           else
/*      */           {
/*  547 */             factura.setIsAlmacenada(isAlmacenada);
/*  548 */             Integer idF = fC.crear(factura);
/*  549 */             fC.cerrarRs();
/*  550 */             factura.setId(idF);
/*  551 */             LineaFacturaControl lFC = new LineaFacturaControl(Inicio.getCFacturacion(), this.ventas);
/*  552 */             for (LineaFactura lineaFactura : (List<LineaFactura>)lineas) {
/*  553 */               if (lineaFactura != null) {
/*  554 */                 lineaFactura.setIdFactura(factura.getId());
/*  555 */                 lFC.crear(lineaFactura);
/*  556 */                 if (idAlmacen != -1) {
/*  557 */                   crearSalidaAlmacen(aC, lineaFactura, factura.getFecha(), idAlmacen);
/*      */                 }
/*      */               }
/*      */             }
/*  561 */             lFC.cerrarRs();
/*      */           }
/*  563 */           limpiarForm();
/*  564 */           Inicio.frame.renovarTabla(7);
/*      */         }
/*      */       }
/*  567 */       aC.cerrarConexion();
/*      */     } catch (Exception ex) {
/*  569 */       Logger.getLogger(FacturasJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void crearSalidaAlmacen(AlmacenControl aC, LineaFactura linea, Date fechaFactura, int idAlmacen) {
/*  574 */     double base = linea.getTotal().doubleValue();
/*  575 */     double importeUnidad = base / linea.getUnidades().doubleValue();
/*  576 */     if (base >= 0.0D) {
/*  577 */       aC.insertVenta(linea.getIdProducto().getReferencia(), fechaFactura, importeUnidad, linea.getUnidades().intValue(), idAlmacen);
/*      */     } else {
/*  579 */       Double coste = aC.getCosteProducto(linea.getIdProducto().getReferencia());
/*  580 */       if (coste == null) {
/*  581 */         coste = Double.valueOf(0.0D);
/*      */       }
/*  583 */       aC.insertCompra(linea.getIdProducto().getReferencia(), fechaFactura, coste.doubleValue(), linea.getUnidades().intValue(), idAlmacen);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void suprimirSalidaAlmacen(AlmacenControl aC, LineaFactura linea, Date fechaFactura) {
/*  588 */     int pio = -1;
/*  589 */     String referencia = linea.getIdProducto().getReferencia();
/*  590 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/*  591 */     String fecha = sdf.format(fechaFactura);
/*  592 */     int unidades = linea.getUnidades().intValue();
/*  593 */     double importe = linea.getTotal().doubleValue() / unidades;
/*  594 */     for (int i = 0; i < unidades; i++)
/*  595 */       aC.suprimirPIO(referencia, fecha, importe, pio);
/*      */   }
/*      */ 
/*      */   private void contabilizarFactura()
/*      */   {
/*  600 */     mostrarError("Recuerde: la factura se contabilizará con los datos\nalmacenados en la base de datos.\nSi ha realizado cambios en el formulario, guardelos\npulsando el botón Crear y vuelva, después, para contabilizar la factura.");
/*      */ 
/*  603 */     if (this.idFactura.intValue() != -1) {
/*      */       try {
/*  605 */         FacturaControl fC = new FacturaControl(Inicio.getCFacturacion(), this.ventas);
/*  606 */         Factura factura = fC.factura(this.idFactura);
/*  607 */         LineaFacturaControl lFC = new LineaFacturaControl(Inicio.getCFacturacion(), this.ventas);
/*  608 */         ArrayList lineas = lFC.lineas(factura.getId());
/*  609 */         String oldNumero = factura.getNumero();
/*  610 */         ContabilizarFacturaJDialog dlg = new ContabilizarFacturaJDialog(Inicio.frame, true, factura, lineas, this.ventas);
/*  611 */         Dimension dlgSize = dlg.getSize();
/*  612 */         Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/*  613 */         dlg.setLocation((screenSize.width - dlgSize.width) / 2, (screenSize.height - dlgSize.height) / 2);
/*      */ 
/*  615 */         dlg.setVisible(true);
/*  616 */         if (dlg.isContabilizada()) {
/*  617 */           fC = new FacturaControl(Inicio.getCFacturacion(), this.ventas);
/*  618 */           factura.setNumero(oldNumero);
/*  619 */           factura.setContabilizada(true);
/*  620 */           factura.setIsAlmacenada(dlg.isAlmacenada());
/*  621 */           fC.modificar(factura);
/*  622 */           this.botonCrear.setEnabled(false);
/*  623 */           this.botonContabilizar.setText("No contabilizada");
/*  624 */           Inicio.frame.renovarTabla(7);
/*      */         }
/*  626 */         fC.cerrarRs();
/*  627 */         lFC.cerrarRs();
/*      */       } catch (Exception ex) {
/*  629 */         Logger.getLogger(FacturasJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
/*      */       }
/*      */     }
/*      */     else
/*  633 */       mostrarError("Para contabilizar una factura ha de crearla antes");
/*      */   }
/*      */ 
/*      */   private void descontabilizarFactura()
/*      */   {
/*  638 */     mostrarError("Recuerde: sólo se marcará la factura como no contabilizada.\nTendrá que borrar manualmente la factura contable que se creó\nal contabilizarla anteriormente.");
/*      */ 
/*  641 */     if (this.idFactura.intValue() != -1)
/*      */       try {
/*  643 */         FacturaControl fC = new FacturaControl(Inicio.getCFacturacion(), this.ventas);
/*  644 */         Factura factura = fC.factura(this.idFactura);
/*  645 */         factura.setContabilizada(false);
/*  646 */         fC.modificar(factura);
/*  647 */         this.botonCrear.setEnabled(true);
/*  648 */         this.botonContabilizar.setText("Pasar a contabilidad");
/*  649 */         Inicio.frame.renovarTabla(7);
/*  650 */         fC.cerrarRs();
/*      */       } catch (Exception ex) {
/*  652 */         Logger.getLogger(FacturasProveedoresJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
/*      */       }
/*      */   }
/*      */ 
/*      */   private void saveToPDF()
/*      */   {
/*  658 */     Factura factura = leerFacturaFromForm();
/*  659 */     ArrayList lineas = leerLineasFromForm(this.idFactura);
/*  660 */     GenerarPdf pdf = new GenerarPdf(factura, lineas);
/*  661 */     pdf.hacerTrabajo();
/*      */   }
/*      */ 
/*      */   private void mostrarError(String error) {
/*  665 */     JOptionPane.showMessageDialog(getContentPane(), error, "Error", 0);
/*      */   }
/*      */ 
/*      */   private boolean esDoble(String dato) {
/*      */     try {
/*  670 */       new Double(dato).doubleValue();
/*      */     }
/*      */     catch (NumberFormatException exc) {
/*  673 */       return false;
/*      */     }
/*  675 */     return true;
/*      */   }
/*      */ 
/*      */   private void updateProductoEnUso(Producto producto) {
/*  679 */     if (producto != null) {
/*  680 */       this.productoEnUso = new Producto(producto.getId(), producto.getReferencia(), producto.getDescripcion(), producto.getSubcuenta(), producto.getPrecio());
/*  681 */       this.campoReferencia.setText(this.productoEnUso.getReferencia());
/*  682 */       this.campoNombreProducto.setText(this.productoEnUso.getDescripcion());
/*  683 */       this.campoConcepto.setText(this.productoEnUso.getDescripcion());
/*      */ 
/*  685 */       colocarIvaProducto(producto.getId());
/*      */     }
/*      */   }
/*      */ 
/*      */   private void colocarIvaProducto(Integer idProducto) {
/*  690 */     if ((idProducto != null) && (idProducto.intValue() != -1)) {
/*  691 */       AlmacenControl aC = new AlmacenControl();
/*  692 */       int idIva = aC.getIvaProducto(idProducto.intValue());
/*  693 */       aC.cerrarConexion();
/*  694 */       for (int i = 0; i < this.comboTiposIva.getItemCount(); i++) {
/*  695 */         TipoIVA object = (TipoIVA)this.comboTiposIva.getItemAt(i);
/*  696 */         if (object.getId() == idIva) {
/*  697 */           this.comboTiposIva.setSelectedIndex(i);
/*  698 */           Double precio = Double.valueOf(this.productoEnUso.getPrecio().doubleValue() / (1.0D + object.getIva() / 100.0D));
/*  699 */           this.campoBase.setText(doubleTwoDecimals(precio).toString());
/*  700 */           break;
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private Double doubleTwoDecimals(Double number) {
/*  707 */     if (number != null) {
/*  708 */       BigDecimal dec = new BigDecimal(number.doubleValue());
/*  709 */       return Double.valueOf(dec.setScale(2, 4).doubleValue());
/*      */     }
/*  711 */     return Double.valueOf(-1.0D);
/*      */   }
/*      */ 
/*      */   private void buscarCliente() {
/*  715 */     MostrarCuentas dlg2 = new MostrarCuentas(Inicio.frame, Mensajes.getString("cuentas"), true, 43000000, 44999999);
/*  716 */     Dimension dlgSize = dlg2.getPreferredSize();
/*  717 */     Dimension frmSize = Inicio.frame.getSize();
/*  718 */     Point loc = getLocation();
/*  719 */     dlg2.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
/*      */ 
/*  721 */     dlg2.setVisible(true);
/*  722 */     if (!dlg2.Seleccion().equals("")) {
/*  723 */       Integer codigo = Integer.valueOf(Integer.parseInt(dlg2.Seleccion()));
/*  724 */       ManejoSubcuentas mS = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  725 */       TipoSubcuenta subcuenta = mS.datos(codigo.intValue());
/*  726 */       this.comboClientes.setSelectedItem(subcuenta);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void initComponents()
/*      */   {
/*  739 */     this.jPanel1 = new JPanel();
/*  740 */     this.jLabel1 = new JLabel();
/*  741 */     this.campoNumero = new JTextField();
/*  742 */     this.jLabel2 = new JLabel();
/*  743 */     this.campoFecha = new ICalendarTextField();
/*  744 */     this.esRecargo = new JCheckBox();
/*  745 */     this.jLabel3 = new JLabel();
/*  746 */     this.comboClientes = new JComboBox();
/*  747 */     this.jLabel4 = new JLabel();
/*  748 */     this.campoRetencion = new JTextField();
/*  749 */     this.jLabel5 = new JLabel();
/*  750 */     this.comboFormaPago = new JComboBox();
/*  751 */     this.jButton2 = new JButton();
/*  752 */     this.jPanel2 = new JPanel();
/*  753 */     this.jLabel6 = new JLabel();
/*  754 */     this.jLabel7 = new JLabel();
/*  755 */     this.campoUnidades = new JTextField();
/*  756 */     this.jLabel8 = new JLabel();
/*  757 */     this.campoConcepto = new JTextField();
/*  758 */     this.jLabel9 = new JLabel();
/*  759 */     this.campoBase = new JTextField();
/*  760 */     this.jLabel10 = new JLabel();
/*  761 */     this.comboTiposIva = new JComboBox();
/*  762 */     this.jScrollPane1 = new JScrollPane();
/*  763 */     this.tablaLineas = new JTable();
/*  764 */     this.botonAddToTable = new JButton();
/*  765 */     this.jLabel11 = new JLabel();
/*  766 */     this.jLabel12 = new JLabel();
/*  767 */     this.jLabel13 = new JLabel();
/*  768 */     this.jLabel14 = new JLabel();
/*  769 */     this.campoInfoBase = new JFormattedTextField();
/*  770 */     this.campoInfoIva = new JFormattedTextField();
/*  771 */     this.campoInfoTotal = new JFormattedTextField();
/*  772 */     this.jButton1 = new JButton();
/*  773 */     this.campoReferencia = new JTextField();
/*  774 */     this.campoNombreProducto = new JTextField();
/*  775 */     this.botonLimpiar = new JButton();
/*  776 */     this.botonCrear = new JButton();
/*  777 */     this.botonContabilizar = new JButton();
/*  778 */     this.botonPdf = new JButton();
/*      */ 
/*  780 */     setClosable(true);
/*  781 */     setIconifiable(true);
/*  782 */     setMaximizable(true);
/*  783 */     setResizable(true);
/*  784 */     setTitle("Facturación (Ventas)");
/*  785 */     setFrameIcon(new ImageIcon(getClass().getResource("/contaes/iconos/facturacion18.png")));
/*  786 */     setPreferredSize(new Dimension(725, 590));
/*  787 */     addInternalFrameListener(new InternalFrameListener() {
/*      */       public void internalFrameOpened(InternalFrameEvent evt) {
/*      */       }
/*      */       public void internalFrameClosing(InternalFrameEvent evt) {
/*      */       }
/*      */       public void internalFrameClosed(InternalFrameEvent evt) {
/*      */       }
/*      */       public void internalFrameIconified(InternalFrameEvent evt) {
/*      */       }
/*      */       public void internalFrameDeiconified(InternalFrameEvent evt) {
/*      */       }
/*      */       public void internalFrameActivated(InternalFrameEvent evt) {
/*  799 */         FacturasJInternalFrame.this.formInternalFrameActivated(evt);
/*      */       }
/*      */ 
/*      */       public void internalFrameDeactivated(InternalFrameEvent evt)
/*      */       {
/*      */       }
/*      */     });
/*  805 */     this.jPanel1.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
/*      */ 
/*  807 */     this.jLabel1.setText("Número");
/*      */ 
/*  809 */     this.campoNumero.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  811 */         FacturasJInternalFrame.this.campoNumeroActionPerformed(evt);
/*      */       }
/*      */     });
/*  814 */     this.campoNumero.addFocusListener(new FocusAdapter() {
/*      */       public void focusLost(FocusEvent evt) {
/*  816 */         FacturasJInternalFrame.this.campoNumeroFocusLost(evt);
/*      */       }
/*      */     });
/*  820 */     this.jLabel2.setText("Fecha");
/*      */ 
/*  822 */     this.esRecargo.setText("Recargo de equivalencia");
/*      */ 
/*  824 */     this.jLabel3.setText("Cliente");
/*      */ 
/*  826 */     this.comboClientes.addItemListener(new ItemListener() {
/*      */       public void itemStateChanged(ItemEvent evt) {
/*  828 */         FacturasJInternalFrame.this.comboClientesItemStateChanged(evt);
/*      */       }
/*      */     });
/*  832 */     this.jLabel4.setText("Retención");
/*      */ 
/*  834 */     this.campoRetencion.setDocument(new DocNumeros());
/*  835 */     this.campoRetencion.addKeyListener(new KeyAdapter() {
/*      */       public void keyPressed(KeyEvent evt) {
/*  837 */         FacturasJInternalFrame.this.campoRetencionKeyPressed(evt);
/*      */       }
/*      */     });
/*  841 */     this.jLabel5.setText("Forma de pago");
/*      */ 
/*  843 */     this.jButton2.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/find.png")));
/*  844 */     ResourceBundle bundle = ResourceBundle.getBundle("internationalization/Mensajes");
/*  845 */     this.jButton2.setText(bundle.getString("buscar"));
/*  846 */     this.jButton2.setHorizontalTextPosition(2);
/*  847 */     this.jButton2.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  849 */         FacturasJInternalFrame.this.jButton2ActionPerformed(evt);
/*      */       }
/*      */     });
/*  853 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/*  854 */     this.jPanel1.setLayout(jPanel1Layout);
/*  855 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().add(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().add(this.jLabel1).addPreferredGap(0).add(this.campoNumero, -2, 116, -2).add(18, 18, 18).add(this.jLabel2).addPreferredGap(0).add(this.campoFecha, -2, 135, -2)).add(jPanel1Layout.createSequentialGroup().add(this.jLabel3).add(18, 18, 18).add(this.comboClientes, 0, 324, 32767))).add(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().add(53, 53, 53).add(this.esRecargo)).add(jPanel1Layout.createSequentialGroup().add(18, 18, 18).add(this.jButton2)))).add(jPanel1Layout.createSequentialGroup().add(this.jLabel4).addPreferredGap(0).add(this.campoRetencion, -2, 116, -2).add(52, 52, 52).add(this.jLabel5).add(18, 18, 18).add(this.comboFormaPago, 0, 271, 32767))).addContainerGap(22, 32767)));
/*      */ 
/*  891 */     jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().add(18, 18, 18).add(jPanel1Layout.createParallelGroup(2).add(jPanel1Layout.createParallelGroup(3).add(this.jLabel1).add(this.campoNumero, -2, -1, -2).add(this.jLabel2)).add(jPanel1Layout.createParallelGroup(1).add(this.esRecargo).add(this.campoFecha, -2, -1, -2))).addPreferredGap(0).add(jPanel1Layout.createParallelGroup(3).add(this.comboClientes, -2, -1, -2).add(this.jLabel3).add(this.jButton2)).add(9, 9, 9).add(jPanel1Layout.createParallelGroup(3).add(this.campoRetencion, -2, -1, -2).add(this.jLabel4).add(this.jLabel5).add(this.comboFormaPago, -2, -1, -2)).addContainerGap(-1, 32767)));
/*      */ 
/*  917 */     this.jPanel2.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
/*      */ 
/*  919 */     this.jLabel6.setText("Producto");
/*      */ 
/*  921 */     this.jLabel7.setText("Unidades");
/*      */ 
/*  923 */     this.campoUnidades.setDocument(new DocNumeros());
/*  924 */     this.campoUnidades.addFocusListener(new FocusAdapter() {
/*      */       public void focusLost(FocusEvent evt) {
/*  926 */         FacturasJInternalFrame.this.campoUnidadesFocusLost(evt);
/*      */       }
/*      */     });
/*  930 */     this.jLabel8.setText("Concepto");
/*      */ 
/*  932 */     this.jLabel9.setText("Precio unidad");
/*      */ 
/*  934 */     this.campoBase.setDocument(new DocNumeros());
/*  935 */     this.campoBase.addKeyListener(new KeyAdapter() {
/*      */       public void keyPressed(KeyEvent evt) {
/*  937 */         FacturasJInternalFrame.this.campoBaseKeyPressed(evt);
/*      */       }
/*      */     });
/*  941 */     this.jLabel10.setText("Tipo de IVA");
/*      */ 
/*  943 */     this.tablaLineas.setModel(this.modeloTabla);
/*  944 */     this.tablaLineas.setSelectionMode(0);
/*  945 */     this.tablaLineas.addMouseListener(new MouseAdapter() {
/*      */       public void mouseClicked(MouseEvent evt) {
/*  947 */         FacturasJInternalFrame.this.tablaLineasMouseClicked(evt);
/*      */       }
/*      */     });
/*  950 */     this.jScrollPane1.setViewportView(this.tablaLineas);
/*      */ 
/*  952 */     this.botonAddToTable.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/redo.png")));
/*  953 */     this.botonAddToTable.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  955 */         FacturasJInternalFrame.this.botonAddToTableActionPerformed(evt);
/*      */       }
/*      */     });
/*  959 */     this.jLabel11.setText("Totales:");
/*      */ 
/*  961 */     this.jLabel12.setText("Base");
/*      */ 
/*  963 */     this.jLabel13.setText("Impuestos");
/*      */ 
/*  965 */     this.jLabel14.setText("Total");
/*      */ 
/*  967 */     this.campoInfoBase.setEditable(false);
/*  968 */     this.campoInfoBase.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("#,###,##0.00"))));
/*      */ 
/*  970 */     this.campoInfoIva.setEditable(false);
/*  971 */     this.campoInfoIva.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("#,###,##0.00"))));
/*      */ 
/*  973 */     this.campoInfoTotal.setEditable(false);
/*  974 */     this.campoInfoTotal.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("#,###,##0.00"))));
/*      */ 
/*  976 */     this.jButton1.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/producto18.png")));
/*  977 */     this.jButton1.setText("Buscar producto");
/*  978 */     this.jButton1.setHorizontalTextPosition(2);
/*  979 */     this.jButton1.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  981 */         FacturasJInternalFrame.this.jButton1ActionPerformed(evt);
/*      */       }
/*      */     });
/*  985 */     this.campoReferencia.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  987 */         FacturasJInternalFrame.this.campoReferenciaActionPerformed(evt);
/*      */       }
/*      */     });
/*  991 */     this.campoNombreProducto.setEditable(false);
/*      */ 
/*  993 */     GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
/*  994 */     this.jPanel2.setLayout(jPanel2Layout);
/*  995 */     jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(1).add(jPanel2Layout.createSequentialGroup().addContainerGap().add(jPanel2Layout.createParallelGroup(1, false).add(jPanel2Layout.createSequentialGroup().add(jPanel2Layout.createParallelGroup(1).add(2, jPanel2Layout.createSequentialGroup().add(this.jLabel9).addPreferredGap(0)).add(jPanel2Layout.createSequentialGroup().add(this.jLabel7).add(37, 37, 37))).add(jPanel2Layout.createParallelGroup(1).add(jPanel2Layout.createSequentialGroup().add(this.campoBase, -2, 117, -2).add(18, 18, 18).add(jPanel2Layout.createParallelGroup(1).add(jPanel2Layout.createSequentialGroup().add(this.jLabel10).addPreferredGap(1).add(this.comboTiposIva, -2, 156, -2).addPreferredGap(0, 89, 32767).add(this.botonAddToTable, -2, 35, -2)).add(jPanel2Layout.createSequentialGroup().add(this.jLabel8).addPreferredGap(0).add(this.campoConcepto, -2, 234, -2).addPreferredGap(0, 59, 32767)))).add(jPanel2Layout.createSequentialGroup().add(this.campoUnidades, -2, 75, -2).addPreferredGap(0, 423, 32767)))).add(jPanel2Layout.createSequentialGroup().add(this.jLabel6).addPreferredGap(0).add(this.campoReferencia, -2, 114, -2).addPreferredGap(0).add(this.campoNombreProducto, -2, 264, -2).addPreferredGap(0).add(this.jButton1))).add(51, 51, 51)).add(2, this.jScrollPane1, -1, 664, 32767).add(jPanel2Layout.createSequentialGroup().addContainerGap().add(this.jLabel11).add(18, 18, 18).add(this.jLabel12).addPreferredGap(0).add(this.campoInfoBase, -2, 116, -2).addPreferredGap(1).add(this.jLabel13).addPreferredGap(0).add(this.campoInfoIva, -2, 102, -2).addPreferredGap(0).add(this.jLabel14).addPreferredGap(0).add(this.campoInfoTotal, -2, 117, -2).addContainerGap(61, 32767)));
/*      */ 
/* 1054 */     jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(1).add(jPanel2Layout.createSequentialGroup().addContainerGap().add(jPanel2Layout.createParallelGroup(3).add(this.jLabel6).add(this.campoReferencia, -2, -1, -2).add(this.campoNombreProducto, -2, -1, -2).add(this.jButton1)).addPreferredGap(0).add(jPanel2Layout.createParallelGroup(2).add(jPanel2Layout.createSequentialGroup().add(jPanel2Layout.createParallelGroup(3).add(this.jLabel7).add(this.campoUnidades, -2, -1, -2).add(this.jLabel8).add(this.campoConcepto, -2, -1, -2)).addPreferredGap(0).add(jPanel2Layout.createParallelGroup(3).add(this.jLabel9).add(this.campoBase, -2, -1, -2).add(this.jLabel10).add(this.comboTiposIva, -2, -1, -2))).add(this.botonAddToTable, -2, 35, -2)).addPreferredGap(0).add(this.jScrollPane1, -2, 137, -2).addPreferredGap(0).add(jPanel2Layout.createParallelGroup(3).add(this.jLabel11).add(this.jLabel12).add(this.jLabel14).add(this.campoInfoBase, -2, -1, -2).add(this.jLabel13).add(this.campoInfoIva, -2, -1, -2).add(this.campoInfoTotal, -2, -1, -2)).addContainerGap(-1, 32767)));
/*      */ 
/* 1092 */     this.botonLimpiar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/cancel.png")));
/* 1093 */     this.botonLimpiar.setText("Limpiar");
/* 1094 */     this.botonLimpiar.setHorizontalTextPosition(2);
/* 1095 */     this.botonLimpiar.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/* 1097 */         FacturasJInternalFrame.this.botonLimpiarActionPerformed(evt);
/*      */       }
/*      */     });
/* 1101 */     this.botonCrear.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/new.png")));
/* 1102 */     this.botonCrear.setText("Crear");
/* 1103 */     this.botonCrear.setHorizontalTextPosition(2);
/* 1104 */     this.botonCrear.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/* 1106 */         FacturasJInternalFrame.this.botonCrearActionPerformed(evt);
/*      */       }
/*      */     });
/* 1110 */     this.botonContabilizar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/D16.png")));
/* 1111 */     this.botonContabilizar.setText("Pasar a contabilidad");
/* 1112 */     this.botonContabilizar.setHorizontalTextPosition(2);
/* 1113 */     this.botonContabilizar.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/* 1115 */         FacturasJInternalFrame.this.botonContabilizarActionPerformed(evt);
/*      */       }
/*      */     });
/* 1119 */     this.botonPdf.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/pdf.png")));
/* 1120 */     this.botonPdf.setText("Crear PDF");
/* 1121 */     this.botonPdf.setHorizontalTextPosition(2);
/* 1122 */     this.botonPdf.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/* 1124 */         FacturasJInternalFrame.this.botonPdfActionPerformed(evt);
/*      */       }
/*      */     });
/* 1128 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 1129 */     getContentPane().setLayout(layout);
/* 1130 */     layout.setHorizontalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(layout.createParallelGroup(1).add(2, layout.createSequentialGroup().add(21, 21, 21).add(this.botonLimpiar).addPreferredGap(0, 25, 32767).add(this.botonCrear).add(41, 41, 41).add(this.botonPdf).add(46, 46, 46).add(this.botonContabilizar).add(179, 179, 179)).add(2, layout.createSequentialGroup().add(layout.createParallelGroup(2).add(1, this.jPanel2, -1, -1, 32767).add(this.jPanel1, -1, -1, 32767)).addContainerGap()))));
/*      */ 
/* 1151 */     layout.setVerticalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(this.jPanel1, -2, -1, -2).add(18, 18, 18).add(this.jPanel2, -2, -1, -2).addPreferredGap(1).add(layout.createParallelGroup(3).add(this.botonPdf).add(this.botonContabilizar).add(this.botonCrear).add(this.botonLimpiar)).addContainerGap(-1, 32767)));
/*      */ 
/* 1167 */     pack();
/*      */   }
/*      */ 
/*      */   private void botonLimpiarActionPerformed(ActionEvent evt) {
/* 1171 */     limpiarForm();
/*      */   }
/*      */ 
/*      */   private void botonCrearActionPerformed(ActionEvent evt) {
/* 1175 */     crearFactura();
/*      */   }
/*      */ 
/*      */   private void botonPdfActionPerformed(ActionEvent evt) {
/* 1179 */     saveToPDF();
/*      */   }
/*      */ 
/*      */   private void botonContabilizarActionPerformed(ActionEvent evt) {
/* 1183 */     String text = this.botonContabilizar.getText();
/* 1184 */     if (text.equals("Pasar a contabilidad")) {
/* 1185 */       contabilizarFactura();
/*      */     }
/* 1187 */     else if (text.equals("No contabilizada"))
/* 1188 */       descontabilizarFactura();
/*      */   }
/*      */ 
/*      */   private void botonAddToTableActionPerformed(ActionEvent evt)
/*      */   {
/* 1193 */     addLineToTable();
/*      */   }
/*      */ 
/*      */   private void tablaLineasMouseClicked(MouseEvent evt) {
/* 1197 */     if (evt.getClickCount() == 2) {
/* 1198 */       int fila = this.tablaLineas.rowAtPoint(evt.getPoint());
/* 1199 */       removeLineFromTable(fila);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void campoUnidadesFocusLost(FocusEvent evt)
/*      */   {
/*      */   }
/*      */ 
/*      */   private void campoNumeroFocusLost(FocusEvent evt)
/*      */   {
/* 1217 */     String numero = this.campoNumero.getText();
/* 1218 */     if ((numero != null) && (!numero.equals(""))) {
/* 1219 */       FacturaControl fC = new FacturaControl(Inicio.getCFacturacion(), this.ventas);
/*      */       try {
/* 1221 */         Factura factura = fC.factura(numero);
/* 1222 */         if (factura != null) {
/* 1223 */           cargarFactura(factura);
/*      */         }
/* 1225 */         fC.cerrarRs();
/*      */       } catch (Exception ex) {
/* 1227 */         Logger.getLogger(FacturasJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private void formInternalFrameActivated(InternalFrameEvent evt) {
/* 1233 */     String numero = Inicio.p.getFacturacion();
/* 1234 */     if ((numero != null) && (!numero.equals(""))) {
/* 1235 */       FacturaControl fC = new FacturaControl(Inicio.getCFacturacion(), this.ventas);
/*      */       try {
/* 1237 */         Factura factura = fC.factura(numero);
/* 1238 */         if (factura != null) {
/* 1239 */           cargarFactura(factura);
/* 1240 */           Inicio.p.setFacturacion("");
/*      */         }
/* 1242 */         fC.cerrarRs();
/*      */       } catch (Exception ex) {
/* 1244 */         Logger.getLogger(FacturasJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private void comboClientesItemStateChanged(ItemEvent evt) {
/* 1250 */     TipoSubcuenta cliente = (TipoSubcuenta)this.comboClientes.getSelectedItem();
/* 1251 */     if (cliente != null) {
/* 1252 */       ManejoAgenda mA = new ManejoAgenda(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 1253 */       Integer idFormaPago = mA.formaPago(Integer.toString(cliente.getCodigo()));
/* 1254 */       mA.cerrarRs();
/* 1255 */       if (idFormaPago != null) {
/* 1256 */         ManejoFormasPago mFP = new ManejoFormasPago(Inicio.getCGeneral());
/* 1257 */         TipoFormaPago formaPago = mFP.getFormaPago(idFormaPago.intValue());
/* 1258 */         if (formaPago != null)
/* 1259 */           this.comboFormaPago.setSelectedItem(formaPago);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private void jButton1ActionPerformed(ActionEvent evt)
/*      */   {
/* 1267 */     Producto producto = null;
/*      */     try {
/* 1269 */       producto = BusquedaAlmacenJDialog.obtenerObjeto(Inicio.frame, this.ventas);
/*      */     }
/*      */     catch (Exception ex) {
/* 1272 */       Logger.getLogger(FacturasJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
/*      */     }
/* 1274 */     updateProductoEnUso(producto);
/*      */   }
/*      */ 
/*      */   private void campoReferenciaActionPerformed(ActionEvent evt)
/*      */   {
/* 1279 */     String referencia = this.campoReferencia.getText();
/* 1280 */     if ((referencia != null) && (!referencia.equals(""))) {
/* 1281 */       Producto producto = null;
/* 1282 */       AlmacenControl aC = new AlmacenControl();
/* 1283 */       producto = aC.getProducto(referencia, this.ventas);
/* 1284 */       updateProductoEnUso(producto);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void campoRetencionKeyPressed(KeyEvent evt) {
/* 1289 */     int tecla = evt.getKeyCode();
/* 1290 */     if ((evt.isAltDown()) && (tecla == 67)) {
/* 1291 */       Inicio.calculadora.colocaOrigen(this.campoRetencion);
/* 1292 */       Inicio.calculadora.setVisible(true);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void campoBaseKeyPressed(KeyEvent evt) {
/* 1297 */     int tecla = evt.getKeyCode();
/* 1298 */     if ((evt.isAltDown()) && (tecla == 67)) {
/* 1299 */       Inicio.calculadora.colocaOrigen(this.campoBase);
/* 1300 */       Inicio.calculadora.setVisible(true);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void jButton2ActionPerformed(ActionEvent evt) {
/* 1305 */     buscarCliente();
/*      */   }
/*      */ 
/*      */   private void campoNumeroActionPerformed(ActionEvent evt) {
/* 1309 */     String numero = this.campoNumero.getText();
/* 1310 */     if ((numero != null) && (!numero.equals("")))
/* 1311 */       this.campoFecha.requestFocusInWindow();
/*      */   }
/*      */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     facturacion.view.FacturasJInternalFrame
 * JD-Core Version:    0.6.2
 */