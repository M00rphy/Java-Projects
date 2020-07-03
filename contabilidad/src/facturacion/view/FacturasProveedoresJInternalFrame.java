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
/*      */ public class FacturasProveedoresJInternalFrame extends JInternalFrame
/*      */ {
/*   53 */   DefaultTableModel modeloTabla = getTableModelLineas(null);
/*   54 */   DefaultComboBoxModel modeloComboProveedores = new DefaultComboBoxModel();
/*   55 */   DefaultComboBoxModel modeloComboFormaPago = new DefaultComboBoxModel();
/*   56 */   DefaultComboBoxModel modeloComboTiposIva = new DefaultComboBoxModel();
/*      */ 
/*   59 */   Double baseTotal = Double.valueOf(0.0D);
/*   60 */   Double ivaTotal = Double.valueOf(0.0D);
/*   61 */   Double totalTotal = Double.valueOf(0.0D);
/*      */ 
/*   63 */   boolean ventas = false;
/*   64 */   Integer idFactura = Integer.valueOf(-1);
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
/*      */   private JComboBox comboFormaPago;
/*      */   private JComboBox comboProveedores;
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
/*      */   public FacturasProveedoresJInternalFrame()
/*      */   {
/*   70 */     initComponents();
/*   71 */     this.campoFecha.setComponente(this.comboProveedores);
/*   72 */     llenarCombos();
/*   73 */     fijarAnchoTabla();
/*      */   }
/*      */ 
/*      */   public void renovarModeloSubcuentas() {
/*      */     try {
/*   78 */       this.modeloComboProveedores.removeAllElements();
/*   79 */       ManejoSubcuentas mS = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*   80 */       ArrayList subcuentas = mS.listaSubcuentas(40000000, 41999999);
/*   81 */       for (TipoSubcuenta subc :(List<TipoSubcuenta>) subcuentas) {
/*   82 */         this.modeloComboProveedores.addElement(subc);
/*      */       }
/*   84 */       this.comboProveedores.setModel(this.modeloComboProveedores);
/*      */     } catch (Exception ex) {
/*   86 */       Logger.getLogger(FacturasProveedoresJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void renovarModeloFormasPago() {
/*      */     try {
/*   92 */       this.modeloComboFormaPago.removeAllElements();
/*   93 */       ManejoFormasPago mFP = new ManejoFormasPago(Inicio.getCGeneral());
/*   94 */       ArrayList formasPago = mFP.getFormasPago();
/*   95 */       for (TipoFormaPago formaP : (List<TipoFormaPago>) formasPago) {
/*   96 */         this.modeloComboFormaPago.addElement(formaP);
/*      */       }
/*   98 */       this.comboFormaPago.setModel(this.modeloComboFormaPago);
/*      */     } catch (Exception ex) {
/*  100 */       Logger.getLogger(FacturasProveedoresJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void renovarModeloIva() {
/*      */     try {
/*  106 */       this.modeloComboTiposIva.removeAllElements();
/*  107 */       ManejoTiposIVA mTI = new ManejoTiposIVA(Inicio.getCGeneral());
/*  108 */       ArrayList tiposIva = mTI.getTiposIVA();
/*  109 */       for (TipoIVA tipoIVA :(List<TipoIVA>) tiposIva) {
/*  110 */         this.modeloComboTiposIva.addElement(tipoIVA);
/*      */       }
/*  112 */       this.comboTiposIva.setModel(this.modeloComboTiposIva);
/*      */     } catch (Exception ex) {
/*  114 */       Logger.getLogger(FacturasProveedoresJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void llenarCombos() {
/*      */     try {
/*  120 */       ManejoSubcuentas mS = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  121 */       ArrayList subcuentas = mS.listaSubcuentas(40000000, 41999999);
/*  122 */       for (TipoSubcuenta subc :(List<TipoSubcuenta>)  subcuentas) {
/*  123 */         this.modeloComboProveedores.addElement(subc);
/*      */       }
/*  125 */       this.comboProveedores.setModel(this.modeloComboProveedores);
/*      */ 
/*  127 */       ManejoFormasPago mFP = new ManejoFormasPago(Inicio.getCGeneral());
/*  128 */       ArrayList formasPago = mFP.getFormasPago();
/*  129 */       for (TipoFormaPago formaP :(List<TipoFormaPago>)  formasPago) {
/*  130 */         this.modeloComboFormaPago.addElement(formaP);
/*      */       }
/*  132 */       this.comboFormaPago.setModel(this.modeloComboFormaPago);
/*      */ 
/*  134 */       ManejoTiposIVA mTI = new ManejoTiposIVA(Inicio.getCGeneral());
/*  135 */       ArrayList tiposIva = mTI.getTiposIVA();
/*  136 */       for (TipoIVA tipoIVA : (List<TipoIVA>) tiposIva) {
/*  137 */         this.modeloComboTiposIva.addElement(tipoIVA);
/*      */       }
/*  139 */       this.comboTiposIva.setModel(this.modeloComboTiposIva);
/*      */     }
/*      */     catch (Exception ex)
/*      */     {
/*  151 */       Logger.getLogger(FacturasProveedoresJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
/*      */     }
/*      */   }
/*      */ 
/*      */   private DefaultTableModel getTableModelLineas(ArrayList<LineaFactura> lineas)
/*      */   {
/*  157 */     DefaultTableModel tableModel = new DefaultTableModel(new Object[0][], new String[0])
/*      */     {
/*  164 */       Class[] types = { Double.class, Producto.class, String.class, Double.class, TipoIVA.class, Double.class, Double.class, Double.class };
/*      */ 
/*  176 */       boolean[] canEdit = { false, false, false, false, false, false, false, false };
/*      */ 
/*      */       public Class getColumnClass(int columnIndex)
/*      */       {
/*  184 */         return this.types[columnIndex];
/*      */       }
/*      */ 
/*      */       public boolean isCellEditable(int rowIndex, int columnIndex)
/*      */       {
/*  190 */         return this.canEdit[columnIndex];
/*      */       }
/*      */     };
/*  193 */     int headerSize = 8;
/*  194 */     String[] header = new String[headerSize];
/*  195 */     header[0] = "Uni.";
/*  196 */     header[1] = "Producto";
/*  197 */     header[2] = "Concepto";
/*  198 */     header[3] = "Base";
/*  199 */     header[4] = "Tipo IVA";
/*  200 */     header[5] = "IVA";
/*  201 */     header[6] = "RE";
/*  202 */     header[7] = "Total";
/*      */ 
/*  204 */     Object[][] objects = (Object[][])null;
/*      */     int i;
/*  206 */     if ((lineas != null) && (lineas.size() > 0))
/*      */     {
/*  208 */       objects = new Object[lineas.size()][headerSize];
/*  209 */       i = 0;
/*  210 */       for (LineaFactura a : lineas)
/*      */       {
/*  212 */         if (a.getId() != null)
/*      */         {
/*  214 */           objects[i][0] = a.getUnidades();
/*      */         }
/*  216 */         Double iva = Double.valueOf(a.getBase().doubleValue() * a.getTipoIva().getIva() / 100.0D);
/*  217 */         Double re = Double.valueOf(a.getBase().doubleValue() * a.getTipoIva().getRe() / 100.0D);
/*  218 */         objects[i][1] = a.getIdProducto();
/*  219 */         objects[i][2] = a.getConcepto();
/*  220 */         objects[i][3] = a.getBase();
/*  221 */         objects[i][4] = a.getTipoIva();
/*  222 */         objects[i][5] = iva;
/*  223 */         objects[i][6] = re;
/*  224 */         objects[i][7] = Double.valueOf(a.getBase().doubleValue() + iva.doubleValue() + re.doubleValue());
/*  225 */         i++;
/*      */       }
/*      */     }
/*  228 */     tableModel.setDataVector(objects, header);
/*  229 */     return tableModel;
/*      */   }
/*      */ 
/*      */   private void fijarAnchoTabla() {
/*  233 */     int anchoTabla = 650;
/*      */ 
/*  235 */     TableColumn columna = this.tablaLineas.getColumnModel().getColumn(0);
/*  236 */     columna.setPreferredWidth((int)(anchoTabla * 0.05D));
/*  237 */     columna.setCellRenderer(new GeneralColorRenderer());
/*  238 */     columna = this.tablaLineas.getColumnModel().getColumn(1);
/*  239 */     columna.setPreferredWidth((int)(anchoTabla * 0.2D));
/*  240 */     columna.setCellRenderer(new GeneralColorRenderer());
/*  241 */     columna = this.tablaLineas.getColumnModel().getColumn(2);
/*  242 */     columna.setPreferredWidth((int)(anchoTabla * 0.2D));
/*  243 */     columna.setCellRenderer(new GeneralColorRenderer());
/*  244 */     columna = this.tablaLineas.getColumnModel().getColumn(3);
/*  245 */     columna.setPreferredWidth((int)(anchoTabla * 0.1D));
/*  246 */     columna.setCellRenderer(new ImporteColorRenderer());
/*  247 */     columna = this.tablaLineas.getColumnModel().getColumn(4);
/*  248 */     columna.setPreferredWidth((int)(anchoTabla * 0.15D));
/*  249 */     columna.setCellRenderer(new GeneralColorRenderer());
/*  250 */     columna = this.tablaLineas.getColumnModel().getColumn(5);
/*  251 */     columna.setPreferredWidth((int)(anchoTabla * 0.1D));
/*  252 */     columna.setCellRenderer(new ImporteColorRenderer());
/*  253 */     columna = this.tablaLineas.getColumnModel().getColumn(6);
/*  254 */     columna.setPreferredWidth((int)(anchoTabla * 0.1D));
/*  255 */     columna.setCellRenderer(new ImporteColorRenderer());
/*  256 */     columna = this.tablaLineas.getColumnModel().getColumn(7);
/*  257 */     columna.setPreferredWidth((int)(anchoTabla * 0.1D));
/*  258 */     columna.setCellRenderer(new ImporteColorRenderer());
/*      */   }
/*      */ 
/*      */   private void limpiarForm() {
/*  262 */     this.baseTotal = Double.valueOf(0.0D);
/*  263 */     this.ivaTotal = Double.valueOf(0.0D);
/*  264 */     this.totalTotal = Double.valueOf(0.0D);
/*  265 */     this.modeloTabla = getTableModelLineas(null);
/*  266 */     this.tablaLineas.setModel(this.modeloTabla);
/*  267 */     this.campoFecha.setDate(new Date());
/*  268 */     this.campoRetencion.setText("");
/*  269 */     this.esRecargo.setSelected(false);
/*      */ 
/*  271 */     this.campoReferencia.setText("");
/*  272 */     this.campoNombreProducto.setText("");
/*  273 */     this.campoUnidades.setText("");
/*  274 */     this.campoConcepto.setText("");
/*  275 */     this.campoBase.setText("");
/*  276 */     this.campoInfoBase.setText("");
/*  277 */     this.campoInfoIva.setText("");
/*  278 */     this.campoInfoTotal.setText("");
/*      */ 
/*  282 */     this.idFactura = Integer.valueOf(-1);
/*  283 */     this.botonCrear.setEnabled(true);
/*  284 */     this.botonContabilizar.setText("Pasar a contabilidad");
/*      */     try {
/*  286 */       this.comboProveedores.setSelectedIndex(0);
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
/*  304 */     Object[] linea = new Object[8];
/*  305 */     String stUni = this.campoUnidades.getText();
/*  306 */     if ((!stUni.equals("")) && (esDoble(stUni))) {
/*  307 */       Double unidades = Double.valueOf(Double.parseDouble(stUni));
/*  308 */       linea[0] = unidades;
/*      */ 
/*  310 */       if (this.productoEnUso != null) {
/*  311 */         linea[1] = this.productoEnUso;
/*  312 */         linea[2] = this.campoConcepto.getText();
/*  313 */         String stBase = this.campoBase.getText();
/*  314 */         if ((!stBase.equals("")) && (esDoble(stBase))) {
/*  315 */           Double base = Double.valueOf(Double.parseDouble(stBase));
/*  316 */           base = Double.valueOf(base.doubleValue() * unidades.doubleValue());
/*  317 */           linea[3] = base;
/*  318 */           TipoIVA tipoIva = (TipoIVA)this.comboTiposIva.getSelectedItem();
/*  319 */           if (tipoIva != null) {
/*  320 */             linea[4] = tipoIva;
/*  321 */             Double iva = Double.valueOf(base.doubleValue() * tipoIva.getIva() / 100.0D);
/*  322 */             linea[5] = iva;
/*  323 */             Double re = Double.valueOf(0.0D);
/*  324 */             if (this.esRecargo.isSelected()) {
/*  325 */               re = Double.valueOf(base.doubleValue() * tipoIva.getRe() / 100.0D);
/*      */             }
/*  327 */             linea[6] = re;
/*  328 */             Double total = Double.valueOf(base.doubleValue() + iva.doubleValue() + re.doubleValue());
/*  329 */             linea[7] = total;
/*  330 */             this.baseTotal = Double.valueOf(this.baseTotal.doubleValue() + base.doubleValue());
/*  331 */             this.ivaTotal = Double.valueOf(this.ivaTotal.doubleValue() + (iva.doubleValue() + re.doubleValue()));
/*  332 */             this.totalTotal = Double.valueOf(this.totalTotal.doubleValue() + total.doubleValue());
/*  333 */             this.campoInfoBase.setValue(this.baseTotal);
/*  334 */             this.campoInfoIva.setValue(this.ivaTotal);
/*  335 */             this.campoInfoTotal.setValue(this.totalTotal);
/*  336 */             this.modeloTabla.addRow(linea);
/*  337 */             limpiarFormProducto();
/*      */           }
/*      */           else {
/*  340 */             mostrarError("Debe seleccionar un tipo de IVA");
/*      */           }
/*      */         }
/*      */         else {
/*  344 */           mostrarError("No ha introducido base imponible o el número introducido no es válido");
/*      */         }
/*      */       }
/*      */       else {
/*  348 */         mostrarError("Debe seleccionar un producto");
/*      */       }
/*      */     }
/*      */     else {
/*  352 */       mostrarError("No ha introducido unidades o el número introducido no es válido");
/*      */     }
/*      */   }
/*      */ 
/*      */   private void removeLineFromTable(int fila) {
/*  357 */     if ((fila >= 0) && (fila < this.modeloTabla.getRowCount())) {
/*  358 */       Double unidades = (Double)this.modeloTabla.getValueAt(fila, 0);
/*  359 */       Producto producto = (Producto)this.modeloTabla.getValueAt(fila, 1);
/*  360 */       String concepto = (String)this.modeloTabla.getValueAt(fila, 2);
/*  361 */       Double base = (Double)this.modeloTabla.getValueAt(fila, 3);
/*  362 */       TipoIVA tipoIva = (TipoIVA)this.modeloTabla.getValueAt(fila, 4);
/*  363 */       Double iva = (Double)this.modeloTabla.getValueAt(fila, 5);
/*  364 */       Double re = (Double)this.modeloTabla.getValueAt(fila, 6);
/*  365 */       Double total = (Double)this.modeloTabla.getValueAt(fila, 7);
/*  366 */       this.baseTotal = Double.valueOf(this.baseTotal.doubleValue() - base.doubleValue());
/*  367 */       this.ivaTotal = Double.valueOf(this.ivaTotal.doubleValue() - (iva.doubleValue() + re.doubleValue()));
/*  368 */       this.totalTotal = Double.valueOf(this.totalTotal.doubleValue() - total.doubleValue());
/*  369 */       this.campoInfoBase.setValue(this.baseTotal);
/*  370 */       this.campoInfoIva.setValue(this.ivaTotal);
/*  371 */       this.campoInfoTotal.setValue(this.totalTotal);
/*  372 */       this.modeloTabla.removeRow(fila);
/*      */ 
/*  374 */       updateProductoEnUso(producto);
/*  375 */       this.campoUnidades.setText(unidades.toString());
/*      */ 
/*  377 */       this.campoConcepto.setText(concepto);
/*      */ 
/*  379 */       this.comboTiposIva.setSelectedItem(tipoIva);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void cargarFactura(Factura factura) {
/*  384 */     limpiarForm();
/*  385 */     this.idFactura = factura.getId();
/*  386 */     this.campoNumero.setText(factura.getNumero());
/*  387 */     this.campoFecha.setDate(factura.getFecha());
/*  388 */     this.comboProveedores.setSelectedItem(factura.getCliente());
/*  389 */     this.esRecargo.setSelected(factura.isRecargo());
/*  390 */     this.campoRetencion.setText(factura.getRetencion().toString());
/*  391 */     if (factura.getFormaPago() != null)
/*  392 */       this.comboFormaPago.setSelectedItem(factura.getFormaPago());
/*  393 */     this.campoInfoBase.setValue(factura.getBase());
/*  394 */     this.baseTotal = factura.getBase();
/*  395 */     this.campoInfoIva.setValue(factura.getIva());
/*  396 */     this.ivaTotal = factura.getIva();
/*  397 */     this.campoInfoTotal.setValue(factura.getTotal());
/*  398 */     this.totalTotal = factura.getTotal();
/*  399 */     if (factura.isContabilizada()) {
/*  400 */       this.botonCrear.setEnabled(false);
/*  401 */       this.botonContabilizar.setText("No contabilizada");
/*      */     }
/*  403 */     LineaFacturaControl lFC = new LineaFacturaControl(Inicio.getCFacturacion(), this.ventas);
/*      */     try {
/*  405 */       ArrayList lineas = lFC.lineas(this.idFactura);
/*  406 */       for (LineaFactura linea :(List<LineaFactura>) lineas) {
/*  407 */         Double iva = Double.valueOf(linea.getBase().doubleValue() * linea.getTipoIva().getIva() / 100.0D);
/*  408 */         Double re = Double.valueOf(0.0D);
/*  409 */         if (this.esRecargo.isSelected()) {
/*  410 */           re = Double.valueOf(linea.getBase().doubleValue() * linea.getTipoIva().getRe() / 100.0D);
/*      */         }
/*  412 */         Object[] fila = new Object[8];
/*  413 */         fila[0] = linea.getUnidades();
/*  414 */         fila[1] = linea.getIdProducto();
/*  415 */         fila[2] = linea.getConcepto();
/*  416 */         fila[3] = linea.getBase();
/*  417 */         fila[4] = linea.getTipoIva();
/*  418 */         fila[5] = iva;
/*  419 */         fila[6] = re;
/*  420 */         fila[7] = Double.valueOf(linea.getBase().doubleValue() + iva.doubleValue() + re.doubleValue());
/*  421 */         this.modeloTabla.addRow(fila);
/*      */       }
/*      */     } catch (Exception ex) {
/*  424 */       Logger.getLogger(FacturasProveedoresJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
/*      */     }
/*  426 */     lFC.cerrarRs();
/*      */   }
/*      */ 
/*      */   private Factura leerFacturaFromForm() {
/*  430 */     Factura factura = null;
/*  431 */     String numero = this.campoNumero.getText();
/*  432 */     if ((numero != null) && (!numero.equals(""))) {
/*  433 */       Date fecha = this.campoFecha.getDate();
/*  434 */       if (fecha != null) {
/*  435 */         TipoSubcuenta cliente = (TipoSubcuenta)this.comboProveedores.getSelectedItem();
/*  436 */         if (cliente != null) {
/*  437 */           String stRetenc = this.campoRetencion.getText();
/*  438 */           Double retencion = Double.valueOf(0.0D);
/*  439 */           if ((!stRetenc.equals("")) && (esDoble(stRetenc))) {
/*  440 */             retencion = Double.valueOf(Double.parseDouble(stRetenc));
/*      */           }
/*  442 */           TipoFormaPago formaPago = (TipoFormaPago)this.comboFormaPago.getSelectedItem();
/*  443 */           if (this.totalTotal.doubleValue() != 0.0D) {
/*  444 */             factura = new Factura(this.idFactura, numero, cliente, fecha, retencion, this.esRecargo.isSelected(), formaPago, this.baseTotal, this.ivaTotal, false, false);
/*      */           }
/*      */           else
/*  447 */             mostrarError("Parece no haber datos en la factura");
/*      */         }
/*      */         else
/*      */         {
/*  451 */           mostrarError("Debe especificar un proveedor");
/*      */         }
/*      */       }
/*      */       else {
/*  455 */         mostrarError("Debe especificar una fecha");
/*      */       }
/*      */     }
/*      */     else {
/*  459 */       mostrarError("Debe especificar un número de factura");
/*      */     }
/*  461 */     return factura;
/*      */   }
/*      */ 
/*      */   private ArrayList<LineaFactura> leerLineasFromForm(Integer idFactura) {
/*  465 */     ArrayList lineas = new ArrayList();
/*      */ 
/*  467 */     for (int x = 0; x < this.modeloTabla.getRowCount(); x++) {
/*  468 */       LineaFactura linea = null;
/*  469 */       Double unidades = (Double)this.modeloTabla.getValueAt(x, 0);
/*  470 */       Producto producto = (Producto)this.modeloTabla.getValueAt(x, 1);
/*  471 */       String concepto = (String)this.modeloTabla.getValueAt(x, 2);
/*  472 */       Double base = (Double)this.modeloTabla.getValueAt(x, 3);
/*  473 */       TipoIVA tipoIva = (TipoIVA)this.modeloTabla.getValueAt(x, 4);
/*  474 */       Double total = (Double)this.modeloTabla.getValueAt(x, 7);
/*  475 */       linea = new LineaFactura(Integer.valueOf(-1), idFactura, producto, concepto, base, tipoIva, total, unidades);
/*  476 */       lineas.add(linea);
/*      */     }
/*  478 */     return lineas;
/*      */   }
/*      */ 
/*      */   private int getIdAlmacen() {
/*  482 */     ActualizarAlmacenJDialog dlg = new ActualizarAlmacenJDialog(Inicio.frame, true);
/*  483 */     Dimension dlgSize = dlg.getSize();
/*  484 */     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/*  485 */     dlg.setLocation((screenSize.width - dlgSize.width) / 2, (screenSize.height - dlgSize.height) / 2);
/*      */ 
/*  487 */     dlg.setVisible(true);
/*  488 */     int idAlmacen = dlg.getIdAlmacen();
/*  489 */     return idAlmacen;
/*      */   }
/*      */ 
/*      */   private void crearFactura() {
/*  493 */     FacturaControl fC = new FacturaControl(Inicio.getCFacturacion(), this.ventas);
/*  494 */     int idAlmacen = getIdAlmacen();
/*  495 */     boolean isAlmacenda = idAlmacen != -1;
/*      */     try {
/*  497 */       AlmacenControl aC = new AlmacenControl();
/*  498 */       Factura factura = leerFacturaFromForm();
/*  499 */       if (factura != null) {
/*  500 */         ArrayList lineas = leerLineasFromForm(factura.getId());
/*  501 */         if (lineas.size() > 0) {
/*  502 */           if (factura.getId().intValue() != -1)
/*      */           {
/*  504 */             if ((!isAlmacenda) && (fC.isAlmacenada(factura.getId()))) {
/*  505 */               isAlmacenda = true;
/*      */             }
/*  507 */             factura.setIsAlmacenada(isAlmacenda);
/*  508 */             fC.modificar(factura);
/*  509 */             fC.cerrarRs();
/*  510 */             LineaFacturaControl lFC = new LineaFacturaControl(Inicio.getCFacturacion(), this.ventas);
/*  511 */             ArrayList lineasOld = lFC.lineas(factura.getId());
/*  512 */             for (LineaFactura lineaFactura :(List<LineaFactura>)  lineasOld) {
/*  513 */               lFC.borrar(lineaFactura);
/*  514 */               if (idAlmacen != -1) {
/*  515 */                 suprimirEntradaAlmacen(aC, lineaFactura, factura.getFecha());
/*      */               }
/*      */             }
/*  518 */             for (LineaFactura lineaFactura :(List<LineaFactura>)  lineas) {
/*  519 */               if (lineaFactura != null) {
/*  520 */                 lFC.crear(lineaFactura);
/*  521 */                 if (idAlmacen != -1) {
/*  522 */                   crearEntradaAlmacen(aC, lineaFactura, factura.getFecha(), idAlmacen);
/*      */                 }
/*      */               }
/*      */             }
/*  526 */             lFC.cerrarRs();
/*      */           }
/*      */           else
/*      */           {
/*  530 */             factura.setIsAlmacenada(isAlmacenda);
/*  531 */             Integer idF = fC.crear(factura);
/*  532 */             fC.cerrarRs();
/*  533 */             factura.setId(idF);
/*  534 */             LineaFacturaControl lFC = new LineaFacturaControl(Inicio.getCFacturacion(), this.ventas);
/*  535 */             for (LineaFactura lineaFactura :(List<LineaFactura>)  lineas) {
/*  536 */               if (lineaFactura != null) {
/*  537 */                 lineaFactura.setIdFactura(factura.getId());
/*  538 */                 lFC.crear(lineaFactura);
/*  539 */                 if (idAlmacen != -1) {
/*  540 */                   crearEntradaAlmacen(aC, lineaFactura, factura.getFecha(), idAlmacen);
/*      */                 }
/*      */               }
/*      */             }
/*  544 */             lFC.cerrarRs();
/*      */           }
/*  546 */           limpiarForm();
/*  547 */           Inicio.frame.renovarTabla(8);
/*      */         }
/*      */       }
/*  550 */       aC.cerrarConexion();
/*      */     } catch (Exception ex) {
/*  552 */       Logger.getLogger(FacturasProveedoresJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void crearEntradaAlmacen(AlmacenControl aC, LineaFactura linea, Date fechaFactura, int idAlmacen) {
/*  557 */     double base = linea.getBase().doubleValue();
/*  558 */     double importeUnidad = base / linea.getUnidades().doubleValue();
/*  559 */     if (base >= 0.0D)
/*  560 */       aC.insertCompra(linea.getIdProducto().getReferencia(), fechaFactura, importeUnidad, linea.getUnidades().intValue(), idAlmacen);
/*      */     else
/*  562 */       aC.insertVenta(linea.getIdProducto().getReferencia(), fechaFactura, importeUnidad, linea.getUnidades().intValue(), idAlmacen);
/*      */   }
/*      */ 
/*      */   private void suprimirEntradaAlmacen(AlmacenControl aC, LineaFactura linea, Date fechaFactura)
/*      */   {
/*  567 */     int pio = 1;
/*  568 */     String referencia = linea.getIdProducto().getReferencia();
/*  569 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/*  570 */     String fecha = sdf.format(fechaFactura);
/*  571 */     int unidades = linea.getUnidades().intValue();
/*  572 */     double importe = linea.getTotal().doubleValue() / unidades;
/*  573 */     for (int i = 0; i < unidades; i++)
/*  574 */       aC.suprimirPIO(referencia, fecha, importe, pio);
/*      */   }
/*      */ 
/*      */   private void contabilizarFactura()
/*      */   {
/*  579 */     mostrarError("Recuerde: la factura se contabilizará con los datos\nalmacenados en la base de datos.\nSi ha realizado cambios en el formulario, guardelos\npulsando el botón Crear y vuelva, después, para contabilizar la factura.");
/*      */ 
/*  582 */     if (this.idFactura.intValue() != -1) {
/*      */       try {
/*  584 */         FacturaControl fC = new FacturaControl(Inicio.getCFacturacion(), this.ventas);
/*  585 */         Factura factura = fC.factura(this.idFactura);
/*  586 */         LineaFacturaControl lFC = new LineaFacturaControl(Inicio.getCFacturacion(), this.ventas);
/*  587 */         ArrayList lineas = lFC.lineas(factura.getId());
/*  588 */         String oldNumero = factura.getNumero();
/*  589 */         ContabilizarFacturaJDialog dlg = new ContabilizarFacturaJDialog(Inicio.frame, true, factura, lineas, this.ventas);
/*  590 */         Dimension dlgSize = dlg.getSize();
/*  591 */         Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/*  592 */         dlg.setLocation((screenSize.width - dlgSize.width) / 2, (screenSize.height - dlgSize.height) / 2);
/*      */ 
/*  594 */         dlg.setVisible(true);
/*  595 */         if (dlg.isContabilizada()) {
/*  596 */           fC = new FacturaControl(Inicio.getCFacturacion(), this.ventas);
/*  597 */           factura.setNumero(oldNumero);
/*  598 */           factura.setContabilizada(true);
/*  599 */           fC.modificar(factura);
/*  600 */           this.botonCrear.setEnabled(false);
/*  601 */           this.botonContabilizar.setText("No contabilizada");
/*  602 */           Inicio.frame.renovarTabla(8);
/*      */         }
/*  604 */         fC.cerrarRs();
/*  605 */         lFC.cerrarRs();
/*      */       } catch (Exception ex) {
/*  607 */         Logger.getLogger(FacturasProveedoresJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
/*      */       }
/*      */     }
/*      */     else
/*  611 */       mostrarError("Para contabilizar una factura ha de crearla antes");
/*      */   }
/*      */ 
/*      */   private void descontabilizarFactura()
/*      */   {
/*  616 */     mostrarError("Recuerde: sólo se marcará la factura como no contabilizada.\nTendrá que borrar manualmente la factura contable que se creó\nal contabilizarla anteriormente.");
/*      */ 
/*  619 */     if (this.idFactura.intValue() != -1)
/*      */       try {
/*  621 */         FacturaControl fC = new FacturaControl(Inicio.getCFacturacion(), this.ventas);
/*  622 */         Factura factura = fC.factura(this.idFactura);
/*  623 */         factura.setContabilizada(false);
/*  624 */         fC.modificar(factura);
/*  625 */         this.botonCrear.setEnabled(true);
/*  626 */         this.botonContabilizar.setText("Pasar a contabilidad");
/*  627 */         Inicio.frame.renovarTabla(8);
/*  628 */         fC.cerrarRs();
/*      */       } catch (Exception ex) {
/*  630 */         Logger.getLogger(FacturasProveedoresJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
/*      */       }
/*      */   }
/*      */ 
/*      */   private void saveToPDF()
/*      */   {
/*  636 */     Factura factura = leerFacturaFromForm();
/*  637 */     ArrayList lineas = leerLineasFromForm(this.idFactura);
/*  638 */     GenerarPdf pdf = new GenerarPdf(factura, lineas);
/*  639 */     pdf.hacerTrabajo();
/*      */   }
/*      */ 
/*      */   private void mostrarError(String error) {
/*  643 */     JOptionPane.showMessageDialog(getContentPane(), error, "Error", 0);
/*      */   }
/*      */ 
/*      */   private boolean esDoble(String dato) {
/*      */     try {
/*  648 */       new Double(dato).doubleValue();
/*      */     }
/*      */     catch (NumberFormatException exc) {
/*  651 */       return false;
/*      */     }
/*  653 */     return true;
/*      */   }
/*      */ 
/*      */   private void updateProductoEnUso(Producto producto) {
/*  657 */     if (producto != null) {
/*  658 */       this.productoEnUso = new Producto(producto.getId(), producto.getReferencia(), producto.getDescripcion(), producto.getSubcuenta(), producto.getPrecio());
/*  659 */       this.campoReferencia.setText(this.productoEnUso.getReferencia());
/*  660 */       this.campoNombreProducto.setText(this.productoEnUso.getDescripcion());
/*  661 */       this.campoConcepto.setText(this.productoEnUso.getDescripcion());
/*  662 */       this.campoBase.setText(this.productoEnUso.getPrecio().toString());
/*  663 */       colocarIvaProducto(producto.getId());
/*      */     }
/*      */   }
/*      */ 
/*      */   private void colocarIvaProducto(Integer idProducto) {
/*  668 */     if ((idProducto != null) && (idProducto.intValue() != -1)) {
/*  669 */       AlmacenControl aC = new AlmacenControl();
/*  670 */       int idIva = aC.getIvaProducto(idProducto.intValue());
/*  671 */       aC.cerrarConexion();
/*  672 */       for (int i = 0; i < this.comboTiposIva.getItemCount(); i++) {
/*  673 */         TipoIVA object = (TipoIVA)this.comboTiposIva.getItemAt(i);
/*  674 */         if (object.getId() == idIva) {
/*  675 */           this.comboTiposIva.setSelectedIndex(i);
/*  676 */           break;
/*      */         }
/*      */       }
/*  679 */       aC.cerrarConexion();
/*      */     }
/*      */   }
/*      */ 
/*      */   private void buscarProveedor() {
/*  684 */     MostrarCuentas dlg2 = new MostrarCuentas(Inicio.frame, Mensajes.getString("cuentas"), true, 40000000, 41999999);
/*  685 */     Dimension dlgSize = dlg2.getPreferredSize();
/*  686 */     Dimension frmSize = Inicio.frame.getSize();
/*  687 */     Point loc = getLocation();
/*  688 */     dlg2.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
/*      */ 
/*  690 */     dlg2.setVisible(true);
/*  691 */     if (!dlg2.Seleccion().equals("")) {
/*  692 */       Integer codigo = Integer.valueOf(Integer.parseInt(dlg2.Seleccion()));
/*  693 */       ManejoSubcuentas mS = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  694 */       TipoSubcuenta subcuenta = mS.datos(codigo.intValue());
/*  695 */       this.comboProveedores.setSelectedItem(subcuenta);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void initComponents()
/*      */   {
/*  708 */     this.jPanel1 = new JPanel();
/*  709 */     this.jLabel1 = new JLabel();
/*  710 */     this.campoNumero = new JTextField();
/*  711 */     this.jLabel2 = new JLabel();
/*  712 */     this.campoFecha = new ICalendarTextField();
/*  713 */     this.esRecargo = new JCheckBox();
/*  714 */     this.jLabel3 = new JLabel();
/*  715 */     this.comboProveedores = new JComboBox();
/*  716 */     this.jLabel4 = new JLabel();
/*  717 */     this.campoRetencion = new JTextField();
/*  718 */     this.jLabel5 = new JLabel();
/*  719 */     this.comboFormaPago = new JComboBox();
/*  720 */     this.jButton2 = new JButton();
/*  721 */     this.jPanel2 = new JPanel();
/*  722 */     this.jLabel6 = new JLabel();
/*  723 */     this.jLabel7 = new JLabel();
/*  724 */     this.campoUnidades = new JTextField();
/*  725 */     this.jLabel8 = new JLabel();
/*  726 */     this.campoConcepto = new JTextField();
/*  727 */     this.jLabel9 = new JLabel();
/*  728 */     this.campoBase = new JTextField();
/*  729 */     this.jLabel10 = new JLabel();
/*  730 */     this.comboTiposIva = new JComboBox();
/*  731 */     this.jScrollPane1 = new JScrollPane();
/*  732 */     this.tablaLineas = new JTable();
/*  733 */     this.botonAddToTable = new JButton();
/*  734 */     this.jLabel11 = new JLabel();
/*  735 */     this.jLabel12 = new JLabel();
/*  736 */     this.jLabel13 = new JLabel();
/*  737 */     this.jLabel14 = new JLabel();
/*  738 */     this.campoInfoBase = new JFormattedTextField();
/*  739 */     this.campoInfoIva = new JFormattedTextField();
/*  740 */     this.campoInfoTotal = new JFormattedTextField();
/*  741 */     this.jButton1 = new JButton();
/*  742 */     this.campoReferencia = new JTextField();
/*  743 */     this.campoNombreProducto = new JTextField();
/*  744 */     this.botonLimpiar = new JButton();
/*  745 */     this.botonCrear = new JButton();
/*  746 */     this.botonContabilizar = new JButton();
/*  747 */     this.botonPdf = new JButton();
/*      */ 
/*  749 */     setClosable(true);
/*  750 */     setIconifiable(true);
/*  751 */     setMaximizable(true);
/*  752 */     setResizable(true);
/*  753 */     setTitle("Facturación (Compras)");
/*  754 */     setFrameIcon(new ImageIcon(getClass().getResource("/contaes/iconos/facturacion18.png")));
/*  755 */     setPreferredSize(new Dimension(725, 590));
/*  756 */     addInternalFrameListener(new InternalFrameListener() {
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
/*  768 */         FacturasProveedoresJInternalFrame.this.formInternalFrameActivated(evt);
/*      */       }
/*      */ 
/*      */       public void internalFrameDeactivated(InternalFrameEvent evt)
/*      */       {
/*      */       }
/*      */     });
/*  774 */     this.jPanel1.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
/*      */ 
/*  776 */     this.jLabel1.setText("Número");
/*      */ 
/*  778 */     this.campoNumero.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  780 */         FacturasProveedoresJInternalFrame.this.campoNumeroActionPerformed(evt);
/*      */       }
/*      */     });
/*  783 */     this.campoNumero.addFocusListener(new FocusAdapter() {
/*      */       public void focusLost(FocusEvent evt) {
/*  785 */         FacturasProveedoresJInternalFrame.this.campoNumeroFocusLost(evt);
/*      */       }
/*      */     });
/*  789 */     this.jLabel2.setText("Fecha");
/*      */ 
/*  791 */     this.esRecargo.setText("Recargo de equivalencia");
/*  792 */     this.esRecargo.setEnabled(false);
/*      */ 
/*  794 */     this.jLabel3.setText("Proveedor");
/*      */ 
/*  796 */     this.comboProveedores.addItemListener(new ItemListener() {
/*      */       public void itemStateChanged(ItemEvent evt) {
/*  798 */         FacturasProveedoresJInternalFrame.this.comboProveedoresItemStateChanged(evt);
/*      */       }
/*      */     });
/*  802 */     this.jLabel4.setText("Retención");
/*      */ 
/*  804 */     this.campoRetencion.setDocument(new DocNumeros());
/*  805 */     this.campoRetencion.addKeyListener(new KeyAdapter() {
/*      */       public void keyPressed(KeyEvent evt) {
/*  807 */         FacturasProveedoresJInternalFrame.this.campoRetencionKeyPressed(evt);
/*      */       }
/*      */     });
/*  811 */     this.jLabel5.setText("Forma de pago");
/*      */ 
/*  813 */     this.jButton2.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/find.png")));
/*  814 */     ResourceBundle bundle = ResourceBundle.getBundle("internationalization/Mensajes");
/*  815 */     this.jButton2.setText(bundle.getString("buscar"));
/*  816 */     this.jButton2.setHorizontalTextPosition(2);
/*  817 */     this.jButton2.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  819 */         FacturasProveedoresJInternalFrame.this.jButton2ActionPerformed(evt);
/*      */       }
/*      */     });
/*  823 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/*  824 */     this.jPanel1.setLayout(jPanel1Layout);
/*  825 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().add(this.jLabel1).addPreferredGap(0).add(this.campoNumero, -2, 116, -2).add(18, 18, 18).add(this.jLabel2).addPreferredGap(0).add(this.campoFecha, -2, 135, -2).addPreferredGap(0, 71, 32767).add(this.esRecargo)).add(jPanel1Layout.createSequentialGroup().add(this.jLabel4).addPreferredGap(0).add(this.campoRetencion, -2, 116, -2).add(52, 52, 52).add(this.jLabel5).add(18, 18, 18).add(this.comboFormaPago, 0, 276, 32767)).add(jPanel1Layout.createSequentialGroup().add(this.jLabel3).addPreferredGap(1).add(this.comboProveedores, -2, 344, -2).add(18, 18, 18).add(this.jButton2))).addContainerGap(-1, 32767)));
/*      */ 
/*  856 */     jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().add(18, 18, 18).add(jPanel1Layout.createParallelGroup(2).add(jPanel1Layout.createParallelGroup(3).add(this.jLabel1).add(this.campoNumero, -2, -1, -2).add(this.jLabel2)).add(jPanel1Layout.createParallelGroup(1).add(this.esRecargo).add(this.campoFecha, -2, -1, -2))).addPreferredGap(0).add(jPanel1Layout.createParallelGroup(3).add(this.jLabel3).add(this.comboProveedores, -2, -1, -2).add(this.jButton2)).add(9, 9, 9).add(jPanel1Layout.createParallelGroup(3).add(this.campoRetencion, -2, -1, -2).add(this.jLabel4).add(this.jLabel5).add(this.comboFormaPago, -2, -1, -2)).addContainerGap(-1, 32767)));
/*      */ 
/*  882 */     this.jPanel2.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
/*      */ 
/*  884 */     this.jLabel6.setText("Producto");
/*      */ 
/*  886 */     this.jLabel7.setText("Unidades");
/*      */ 
/*  888 */     this.campoUnidades.setDocument(new DocNumeros());
/*  889 */     this.campoUnidades.addFocusListener(new FocusAdapter() {
/*      */       public void focusLost(FocusEvent evt) {
/*  891 */         FacturasProveedoresJInternalFrame.this.campoUnidadesFocusLost(evt);
/*      */       }
/*      */     });
/*  895 */     this.jLabel8.setText("Concepto");
/*      */ 
/*  897 */     this.jLabel9.setText("Precio unidad");
/*      */ 
/*  899 */     this.campoBase.setDocument(new DocNumeros());
/*  900 */     this.campoBase.addKeyListener(new KeyAdapter() {
/*      */       public void keyPressed(KeyEvent evt) {
/*  902 */         FacturasProveedoresJInternalFrame.this.campoBaseKeyPressed(evt);
/*      */       }
/*      */     });
/*  906 */     this.jLabel10.setText("Tipo de IVA");
/*      */ 
/*  908 */     this.tablaLineas.setModel(this.modeloTabla);
/*  909 */     this.tablaLineas.setSelectionMode(0);
/*  910 */     this.tablaLineas.addMouseListener(new MouseAdapter() {
/*      */       public void mouseClicked(MouseEvent evt) {
/*  912 */         FacturasProveedoresJInternalFrame.this.tablaLineasMouseClicked(evt);
/*      */       }
/*      */     });
/*  915 */     this.jScrollPane1.setViewportView(this.tablaLineas);
/*      */ 
/*  917 */     this.botonAddToTable.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/redo.png")));
/*  918 */     this.botonAddToTable.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  920 */         FacturasProveedoresJInternalFrame.this.botonAddToTableActionPerformed(evt);
/*      */       }
/*      */     });
/*  924 */     this.jLabel11.setText("Totales:");
/*      */ 
/*  926 */     this.jLabel12.setText("Base");
/*      */ 
/*  928 */     this.jLabel13.setText("Impuestos");
/*      */ 
/*  930 */     this.jLabel14.setText("Total");
/*      */ 
/*  932 */     this.campoInfoBase.setEditable(false);
/*  933 */     this.campoInfoBase.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("#,###,##0.00"))));
/*      */ 
/*  935 */     this.campoInfoIva.setEditable(false);
/*  936 */     this.campoInfoIva.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("#,###,##0.00"))));
/*      */ 
/*  938 */     this.campoInfoTotal.setEditable(false);
/*  939 */     this.campoInfoTotal.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("#,###,##0.00"))));
/*      */ 
/*  941 */     this.jButton1.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/producto18.png")));
/*  942 */     this.jButton1.setText("Buscar producto");
/*  943 */     this.jButton1.setHorizontalTextPosition(2);
/*  944 */     this.jButton1.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  946 */         FacturasProveedoresJInternalFrame.this.jButton1ActionPerformed(evt);
/*      */       }
/*      */     });
/*  950 */     this.campoReferencia.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  952 */         FacturasProveedoresJInternalFrame.this.campoReferenciaActionPerformed(evt);
/*      */       }
/*      */     });
/*  956 */     this.campoNombreProducto.setEditable(false);
/*      */ 
/*  958 */     GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
/*  959 */     this.jPanel2.setLayout(jPanel2Layout);
/*  960 */     jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(1).add(jPanel2Layout.createSequentialGroup().addContainerGap().add(jPanel2Layout.createParallelGroup(1, false).add(jPanel2Layout.createSequentialGroup().add(jPanel2Layout.createParallelGroup(1).add(2, jPanel2Layout.createSequentialGroup().add(this.jLabel9).addPreferredGap(0)).add(jPanel2Layout.createSequentialGroup().add(this.jLabel7).add(37, 37, 37))).add(jPanel2Layout.createParallelGroup(1).add(jPanel2Layout.createSequentialGroup().add(this.campoBase, -2, 117, -2).add(18, 18, 18).add(jPanel2Layout.createParallelGroup(1).add(jPanel2Layout.createSequentialGroup().add(this.jLabel10).addPreferredGap(1).add(this.comboTiposIva, -2, 156, -2).addPreferredGap(0, 89, 32767).add(this.botonAddToTable, -2, 35, -2)).add(jPanel2Layout.createSequentialGroup().add(this.jLabel8).addPreferredGap(0).add(this.campoConcepto, -2, 236, -2).addPreferredGap(0, 57, 32767)))).add(jPanel2Layout.createSequentialGroup().add(this.campoUnidades, -2, 75, -2).addPreferredGap(0, 423, 32767)))).add(jPanel2Layout.createSequentialGroup().add(this.jLabel6).addPreferredGap(0).add(this.campoReferencia, -2, 114, -2).addPreferredGap(0).add(this.campoNombreProducto, -2, 264, -2).addPreferredGap(0).add(this.jButton1))).add(51, 51, 51)).add(2, this.jScrollPane1, -1, 664, 32767).add(jPanel2Layout.createSequentialGroup().addContainerGap().add(this.jLabel11).add(18, 18, 18).add(this.jLabel12).addPreferredGap(0).add(this.campoInfoBase, -2, 116, -2).addPreferredGap(1).add(this.jLabel13).addPreferredGap(0).add(this.campoInfoIva, -2, 102, -2).addPreferredGap(0).add(this.jLabel14).addPreferredGap(0).add(this.campoInfoTotal, -2, 117, -2).addContainerGap(61, 32767)));
/*      */ 
/* 1019 */     jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(1).add(jPanel2Layout.createSequentialGroup().addContainerGap().add(jPanel2Layout.createParallelGroup(3).add(this.jLabel6).add(this.campoReferencia, -2, -1, -2).add(this.campoNombreProducto, -2, -1, -2).add(this.jButton1)).addPreferredGap(0).add(jPanel2Layout.createParallelGroup(2).add(jPanel2Layout.createSequentialGroup().add(jPanel2Layout.createParallelGroup(3).add(this.jLabel7).add(this.campoUnidades, -2, -1, -2).add(this.jLabel8).add(this.campoConcepto, -2, -1, -2)).addPreferredGap(0).add(jPanel2Layout.createParallelGroup(3).add(this.jLabel9).add(this.campoBase, -2, -1, -2).add(this.jLabel10).add(this.comboTiposIva, -2, -1, -2))).add(this.botonAddToTable, -2, 35, -2)).addPreferredGap(0).add(this.jScrollPane1, -2, 137, -2).addPreferredGap(0).add(jPanel2Layout.createParallelGroup(3).add(this.jLabel11).add(this.jLabel12).add(this.jLabel14).add(this.campoInfoBase, -2, -1, -2).add(this.jLabel13).add(this.campoInfoIva, -2, -1, -2).add(this.campoInfoTotal, -2, -1, -2)).addContainerGap(-1, 32767)));
/*      */ 
/* 1057 */     this.botonLimpiar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/cancel.png")));
/* 1058 */     this.botonLimpiar.setText("Limpiar");
/* 1059 */     this.botonLimpiar.setHorizontalTextPosition(2);
/* 1060 */     this.botonLimpiar.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/* 1062 */         FacturasProveedoresJInternalFrame.this.botonLimpiarActionPerformed(evt);
/*      */       }
/*      */     });
/* 1066 */     this.botonCrear.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/new.png")));
/* 1067 */     this.botonCrear.setText("Crear");
/* 1068 */     this.botonCrear.setHorizontalTextPosition(2);
/* 1069 */     this.botonCrear.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/* 1071 */         FacturasProveedoresJInternalFrame.this.botonCrearActionPerformed(evt);
/*      */       }
/*      */     });
/* 1075 */     this.botonContabilizar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/D16.png")));
/* 1076 */     this.botonContabilizar.setText("Pasar a contabilidad");
/* 1077 */     this.botonContabilizar.setHorizontalTextPosition(2);
/* 1078 */     this.botonContabilizar.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/* 1080 */         FacturasProveedoresJInternalFrame.this.botonContabilizarActionPerformed(evt);
/*      */       }
/*      */     });
/* 1084 */     this.botonPdf.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/pdf.png")));
/* 1085 */     this.botonPdf.setText("Crear PDF");
/* 1086 */     this.botonPdf.setEnabled(false);
/* 1087 */     this.botonPdf.setHorizontalTextPosition(2);
/* 1088 */     this.botonPdf.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/* 1090 */         FacturasProveedoresJInternalFrame.this.botonPdfActionPerformed(evt);
/*      */       }
/*      */     });
/* 1094 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 1095 */     getContentPane().setLayout(layout);
/* 1096 */     layout.setHorizontalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(layout.createParallelGroup(1).add(2, layout.createSequentialGroup().add(21, 21, 21).add(this.botonLimpiar).addPreferredGap(0, 25, 32767).add(this.botonCrear).add(41, 41, 41).add(this.botonPdf).add(46, 46, 46).add(this.botonContabilizar).add(179, 179, 179)).add(2, layout.createSequentialGroup().add(layout.createParallelGroup(2).add(1, this.jPanel2, -1, -1, 32767).add(this.jPanel1, -1, -1, 32767)).addContainerGap()))));
/*      */ 
/* 1117 */     layout.setVerticalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(this.jPanel1, -2, -1, -2).add(18, 18, 18).add(this.jPanel2, -2, -1, -2).addPreferredGap(1).add(layout.createParallelGroup(3).add(this.botonPdf).add(this.botonContabilizar).add(this.botonCrear).add(this.botonLimpiar)).addContainerGap(-1, 32767)));
/*      */ 
/* 1133 */     pack();
/*      */   }
/*      */ 
/*      */   private void botonLimpiarActionPerformed(ActionEvent evt) {
/* 1137 */     limpiarForm();
/*      */   }
/*      */ 
/*      */   private void botonCrearActionPerformed(ActionEvent evt) {
/* 1141 */     crearFactura();
/*      */   }
/*      */ 
/*      */   private void botonPdfActionPerformed(ActionEvent evt) {
/* 1145 */     saveToPDF();
/*      */   }
/*      */ 
/*      */   private void botonContabilizarActionPerformed(ActionEvent evt) {
/* 1149 */     String text = this.botonContabilizar.getText();
/* 1150 */     if (text.equals("Pasar a contabilidad")) {
/* 1151 */       contabilizarFactura();
/*      */     }
/* 1153 */     else if (text.equals("No contabilizada"))
/* 1154 */       descontabilizarFactura();
/*      */   }
/*      */ 
/*      */   private void botonAddToTableActionPerformed(ActionEvent evt)
/*      */   {
/* 1159 */     addLineToTable();
/*      */   }
/*      */ 
/*      */   private void tablaLineasMouseClicked(MouseEvent evt) {
/* 1163 */     if (evt.getClickCount() == 2) {
/* 1164 */       int fila = this.tablaLineas.rowAtPoint(evt.getPoint());
/* 1165 */       removeLineFromTable(fila);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void campoUnidadesFocusLost(FocusEvent evt)
/*      */   {
/*      */   }
/*      */ 
/*      */   private void campoNumeroFocusLost(FocusEvent evt)
/*      */   {
/* 1183 */     String numero = this.campoNumero.getText();
/* 1184 */     if ((numero != null) && (!numero.equals(""))) {
/* 1185 */       FacturaControl fC = new FacturaControl(Inicio.getCFacturacion(), this.ventas);
/*      */       try {
/* 1187 */         Factura factura = fC.factura(numero);
/* 1188 */         if (factura != null) {
/* 1189 */           cargarFactura(factura);
/*      */         }
/* 1191 */         fC.cerrarRs();
/*      */       } catch (Exception ex) {
/* 1193 */         Logger.getLogger(FacturasProveedoresJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private void formInternalFrameActivated(InternalFrameEvent evt) {
/* 1199 */     String numero = Inicio.p.getFacturacion();
/* 1200 */     if ((numero != null) && (!numero.equals(""))) {
/* 1201 */       FacturaControl fC = new FacturaControl(Inicio.getCFacturacion(), this.ventas);
/*      */       try {
/* 1203 */         Factura factura = fC.factura(numero);
/* 1204 */         if (factura != null) {
/* 1205 */           cargarFactura(factura);
/* 1206 */           Inicio.p.setFacturacion("");
/*      */         }
/* 1208 */         fC.cerrarRs();
/*      */       } catch (Exception ex) {
/* 1210 */         Logger.getLogger(FacturasProveedoresJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private void comboProveedoresItemStateChanged(ItemEvent evt) {
/* 1216 */     TipoSubcuenta cliente = (TipoSubcuenta)this.comboProveedores.getSelectedItem();
/* 1217 */     if (cliente != null) {
/* 1218 */       ManejoAgenda mA = new ManejoAgenda(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 1219 */       Integer idFormaPago = mA.formaPago(Integer.toString(cliente.getCodigo()));
/* 1220 */       mA.cerrarRs();
/* 1221 */       if (idFormaPago != null) {
/* 1222 */         ManejoFormasPago mFP = new ManejoFormasPago(Inicio.getCGeneral());
/* 1223 */         TipoFormaPago formaPago = mFP.getFormaPago(idFormaPago.intValue());
/* 1224 */         if (formaPago != null)
/* 1225 */           this.comboFormaPago.setSelectedItem(formaPago);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private void jButton1ActionPerformed(ActionEvent evt)
/*      */   {
/* 1233 */     Producto producto = null;
/*      */     try {
/* 1235 */       producto = BusquedaAlmacenJDialog.obtenerObjeto(Inicio.frame, this.ventas);
/*      */     }
/*      */     catch (Exception ex) {
/* 1238 */       Logger.getLogger(FacturasProveedoresJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
/*      */     }
/* 1240 */     updateProductoEnUso(producto);
/*      */   }
/*      */ 
/*      */   private void campoReferenciaActionPerformed(ActionEvent evt)
/*      */   {
/* 1245 */     String referencia = this.campoReferencia.getText();
/* 1246 */     if ((referencia != null) && (!referencia.equals(""))) {
/* 1247 */       Producto producto = null;
/* 1248 */       AlmacenControl aC = new AlmacenControl();
/* 1249 */       producto = aC.getProducto(referencia, this.ventas);
/* 1250 */       updateProductoEnUso(producto);
/* 1251 */       aC.cerrarConexion();
/*      */     }
/*      */   }
/*      */ 
/*      */   private void campoRetencionKeyPressed(KeyEvent evt) {
/* 1256 */     int tecla = evt.getKeyCode();
/* 1257 */     if ((evt.isAltDown()) && (tecla == 67)) {
/* 1258 */       Inicio.calculadora.colocaOrigen(this.campoRetencion);
/* 1259 */       Inicio.calculadora.setVisible(true);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void campoBaseKeyPressed(KeyEvent evt) {
/* 1264 */     int tecla = evt.getKeyCode();
/* 1265 */     if ((evt.isAltDown()) && (tecla == 67)) {
/* 1266 */       Inicio.calculadora.colocaOrigen(this.campoBase);
/* 1267 */       Inicio.calculadora.setVisible(true);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void jButton2ActionPerformed(ActionEvent evt) {
/* 1272 */     buscarProveedor();
/*      */   }
/*      */ 
/*      */   private void campoNumeroActionPerformed(ActionEvent evt) {
/* 1276 */     String numero = this.campoNumero.getText();
/* 1277 */     if ((numero != null) && (!numero.equals("")))
/* 1278 */       this.campoFecha.requestFocusInWindow();
/*      */   }
/*      */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     facturacion.view.FacturasProveedoresJInternalFrame
 * JD-Core Version:    0.6.2
 */