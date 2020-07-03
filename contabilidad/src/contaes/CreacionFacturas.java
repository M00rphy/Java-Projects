/*      */ package contaes;
/*      */ 
/*      */ import contaes.auxiliar.CampoCuenta;
/*      */ import contaes.auxiliar.DocTreintaCarac;
/*      */ import contaes.auxiliarTablas.GeneralColorRenderer;
/*      */ import contaes.auxiliarTablas.ImporteColorRenderer;
/*      */ import contaes.calendario.ICalendarTextField;
/*      */ import contaes.dialogosAuxiliares.GenerarVencimientos;
/*      */ import contaes.dialogosAuxiliares.MostrarConceptos;
/*      */ import contaes.dialogosAuxiliares.PagaVencimientos;
/*      */ import contaes.dialogosAuxiliares.PagoTarjeta;
/*      */ import contaes.dialogosFunciones.Calculadora;
/*      */ import contaes.dialogosFunciones.TiposIva;
/*      */ import contaes.manejoDatos.ManejoApuntes;
/*      */ import contaes.manejoDatos.ManejoAsientos;
/*      */ import contaes.manejoDatos.ManejoFacturas;
/*      */ import contaes.manejoDatos.ManejoSubcuentas;
/*      */ import contaes.manejoDatos.ManejoTiposIVA;
/*      */ import contaes.manejoDatos.TipoApunte;
/*      */ import contaes.manejoDatos.TipoFactura;
/*      */ import contaes.manejoDatos.TipoFacturaExtended;
/*      */ import contaes.manejoDatos.TipoIVA;
/*      */ import contaes.manejoDatos.auxiliar.LeerFichero;
/*      */ import contaes.tratamientoDatos.Operaciones;
/*      */ import internationalization.Mensajes;
/*      */ import java.awt.Container;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.Point;
/*      */ import java.awt.Toolkit;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.ActionListener;
/*      */ import java.awt.event.FocusAdapter;
/*      */ import java.awt.event.FocusEvent;
/*      */ import java.awt.event.FocusListener;
/*      */ import java.awt.event.KeyAdapter;
/*      */ import java.awt.event.KeyEvent;
/*      */ import java.awt.event.MouseAdapter;
/*      */ import java.awt.event.MouseEvent;
/*      */ import java.beans.PropertyVetoException;
/*      */ import java.io.IOException;
/*      */ import java.io.PrintStream;
/*      */ import java.math.BigDecimal;
/*      */ import java.text.DecimalFormat;
/*      */ import java.text.DecimalFormatSymbols;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Date;
/*      */ import java.util.GregorianCalendar;
import java.util.List;
/*      */ import java.util.ResourceBundle;
/*      */ import javax.swing.BorderFactory;
/*      */ import javax.swing.ButtonGroup;
/*      */ import javax.swing.DefaultComboBoxModel;
/*      */ import javax.swing.ImageIcon;
/*      */ import javax.swing.JButton;
/*      */ import javax.swing.JCheckBox;
/*      */ import javax.swing.JComboBox;
/*      */ import javax.swing.JDesktopPane;
/*      */ import javax.swing.JInternalFrame;
/*      */ import javax.swing.JLabel;
/*      */ import javax.swing.JOptionPane;
/*      */ import javax.swing.JPanel;
/*      */ import javax.swing.JRadioButton;
/*      */ import javax.swing.JScrollPane;
/*      */ import javax.swing.JTable;
/*      */ import javax.swing.JTextField;
/*      */ import javax.swing.event.InternalFrameAdapter;
/*      */ import javax.swing.event.InternalFrameEvent;
/*      */ import javax.swing.table.DefaultTableModel;
/*      */ import javax.swing.table.TableColumn;
/*      */ import javax.swing.table.TableColumnModel;
/*      */ import javax.swing.table.TableModel;
/*      */ import org.jdesktop.layout.GroupLayout;
/*      */ import org.jdesktop.layout.GroupLayout.ParallelGroup;
/*      */ import org.jdesktop.layout.GroupLayout.SequentialGroup;
/*      */ 
/*      */ public class CreacionFacturas extends JInternalFrame
/*      */ {
/*   62 */   private ManejoSubcuentas subcuentaM = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*   63 */   private ManejoFacturas facturaM = new ManejoFacturas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*   64 */   private ManejoAsientos asientoM = new ManejoAsientos(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*   65 */   private ManejoApuntes apunteM = new ManejoApuntes(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*   66 */   private Operaciones operar = new Operaciones();
/*      */   private double sumaBases;
/*      */   private double sumaIvas;
/*      */   private double sumaREs;
/*      */   private double sumaTotales;
/*      */   private int quien_llamo;
/*      */   private JButton botonAddTable;
/*      */   private JButton botonGenerar;
/*      */   private JButton botonLimpiar;
/*      */   private JButton botonPagar;
/*      */   private JButton botonVencimientos;
/*      */   private ButtonGroup buttonGroup1;
/*      */   private JTextField campoBase;
/*      */   private JTextField campoConcepto;
/*      */   private CampoCuenta campoCuentaRetencion;
/*      */   private JTextField campoNombreSubcuenta;
/*      */   private JTextField campoNumero;
/*      */   private JTextField campoRetencion;
/*      */   private CampoCuenta campoSubcuenta;
/*      */   private JTextField campoSumaBases;
/*      */   private JTextField campoSumaIvas;
/*      */   private JTextField campoSumaREs;
/*      */   private JTextField campoSumaTotales;
/*      */   private CampoCuenta campoTerceros;
/*      */   private JComboBox comboTiposIva;
/*      */   private JRadioButton esAdqIntrac;
/*      */   private JRadioButton esEmitida;
/*      */   private JRadioButton esImportacion;
/*      */   private JCheckBox esRecargo;
/*      */   private JRadioButton esRecibida;
/*      */   private ICalendarTextField iCalendarTextField1;
/*      */   private JLabel jLabel1;
/*      */   private JLabel jLabel10;
/*      */   private JLabel jLabel11;
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
/*      */   private JTable jTable1;
/*      */ 
/*      */   public CreacionFacturas()
/*      */   {
/*   75 */     super(Mensajes.getString("introFacturas"), true, true, true, true);
/*   76 */     setDefaultCloseOperation(1);
/*   77 */     initComponents();
/*   78 */     crearListaIVA();
/*   79 */     iniciarSumas();
/*   80 */     this.esRecibida.setSelected(true);
/*   81 */     configurarTabla();
/*      */ 
/*   83 */     addInternalFrameListener(new InternalFrameAdapter()
/*      */     {
/*      */       public void internalFrameDeactivated(InternalFrameEvent e)
/*      */       {
/*   87 */         marcoDesactivado();
/*      */       }
/*      */ 
/*      */       public void internalFrameActivated(InternalFrameEvent e)
/*      */       {
/*   92 */         marcoActivado();
/*      */       }
/*      */ 
/*      */       public void internalFrameClosed(InternalFrameEvent e)
/*      */       {
/*   97 */         Inicio.frame.eliminarMarcoMenu(Mensajes.getString("introFacturas"));
/*      */       }
/*      */ 
/*      */       private void marcoActivado() {
/*  101 */         if (!Inicio.p.getFactura().equals("NEW")) {
/*  102 */           CreacionFacturas.this.cargarFactura(Inicio.p.getFactura());
/*      */         }
/*  104 */         else if (Inicio.p.getControl() == 2) {
/*  105 */           Inicio.p.setControl(0);
/*  106 */           String cCuenta = Inicio.p.getCuentaStr();
/*  107 */           if (!cCuenta.equals("Error"))
/*  108 */             switch (CreacionFacturas.this.quien_llamo) {
/*      */             case 0:
/*  110 */               CreacionFacturas.this.campoSubcuenta.setText(cCuenta);
/*  111 */               CreacionFacturas.this.campoSubcuenta.requestFocus();
/*  112 */               break;
/*      */             case 1:
/*  114 */               CreacionFacturas.this.campoTerceros.setText(cCuenta);
/*  115 */               break;
/*      */             case 2:
/*  117 */               CreacionFacturas.this.campoCuentaRetencion.setText(cCuenta);
/*      */             }
/*      */         }
/*      */       }
/*      */ 
/*      */       private void marcoDesactivado()
/*      */       {
/*      */       }
/*      */     });
/*  128 */     TeclaPulsada escuchaTecla = new TeclaPulsada();
/*  129 */     this.campoNumero.addKeyListener(escuchaTecla);
/*  130 */     this.campoSubcuenta.addKeyListener(escuchaTecla);
/*  131 */     this.campoConcepto.addKeyListener(escuchaTecla);
/*  132 */     this.campoTerceros.addKeyListener(escuchaTecla);
/*  133 */     this.comboTiposIva.addKeyListener(escuchaTecla);
/*  134 */     this.campoBase.addKeyListener(escuchaTecla);
/*  135 */     this.botonAddTable.addKeyListener(escuchaTecla);
/*  136 */     this.campoCuentaRetencion.addKeyListener(escuchaTecla);
/*  137 */     this.campoRetencion.addKeyListener(escuchaTecla);
/*  138 */     this.iCalendarTextField1.setComponente(this.campoSubcuenta);
/*      */ 
/*  140 */     ControlFoco escuchaFoco = new ControlFoco();
/*  141 */     this.campoSubcuenta.addFocusListener(escuchaFoco);
/*  142 */     this.campoTerceros.addFocusListener(escuchaFoco);
/*  143 */     this.campoCuentaRetencion.addFocusListener(escuchaFoco);
/*      */ 
/*  145 */     cambiarEtiquetas(false);
/*      */   }
/*      */ 
/*      */   private void limpiarForm() {
/*  149 */     this.campoNumero.setText("");
/*  150 */     if (this.esEmitida.isSelected()) {
/*  151 */       this.campoNumero.setText(this.facturaM.nuevoNumeroFactEmitida(Inicio.p.getPrefijo()));
/*      */     }
/*  153 */     this.campoSubcuenta.setText("");
/*  154 */     this.campoNombreSubcuenta.setText("");
/*  155 */     this.campoConcepto.setText("");
/*  156 */     this.campoTerceros.setText("");
/*  157 */     this.campoBase.setText("");
/*  158 */     this.campoCuentaRetencion.setText("");
/*  159 */     this.campoRetencion.setText("");
/*  160 */     iniciarSumas();
/*  161 */     this.campoSumaBases.setText("");
/*  162 */     this.campoSumaIvas.setText("");
/*  163 */     this.campoSumaREs.setText("");
/*  164 */     this.campoSumaTotales.setText("");
/*  165 */     this.esImportacion.setSelected(false);
/*  166 */     this.esAdqIntrac.setSelected(false);
/*  167 */     this.esRecargo.setSelected(false);
/*  168 */     DefaultTableModel modelo = (DefaultTableModel)this.jTable1.getModel();
/*  169 */     int filas = modelo.getRowCount();
/*  170 */     for (int x = 0; x < filas; x++) {
/*  171 */       modelo.removeRow(0);
/*      */     }
/*  173 */     this.campoNumero.requestFocus();
/*      */   }
/*      */ 
/*      */   private void iniciarSumas() {
/*  177 */     this.sumaBases = 0.0D;
/*  178 */     this.sumaIvas = 0.0D;
/*  179 */     this.sumaREs = 0.0D;
/*  180 */     this.sumaTotales = 0.0D;
/*      */   }
/*      */ 
/*      */   private void colocarSumas() {
/*  184 */     DecimalFormatSymbols formato = new DecimalFormatSymbols();
/*  185 */     formato.setDecimalSeparator(',');
/*  186 */     formato.setPerMill('.');
/*  187 */     DecimalFormat fn = new DecimalFormat("#,###,##0.00", formato);
/*  188 */     this.campoSumaBases.setText(fn.format(this.sumaBases));
/*  189 */     this.campoSumaIvas.setText(fn.format(this.sumaIvas));
/*  190 */     this.campoSumaREs.setText(fn.format(this.sumaREs));
/*  191 */     this.campoSumaTotales.setText(fn.format(this.sumaTotales));
/*      */   }
/*      */ 
/*      */   public void renovarModeloIva() {
/*  195 */     this.comboTiposIva.setModel(getModeloLista());
/*      */   }
/*      */ 
/*      */   private void crearListaIVA() {
/*  199 */     this.comboTiposIva.setModel(getModeloLista());
/*      */   }
/*      */ 
/*      */   private void configurarTabla()
/*      */   {
/*  204 */     TableColumn columna = null;
/*  205 */     this.jTable1.setSelectionMode(0);
/*      */ 
/*  207 */     columna = this.jTable1.getColumnModel().getColumn(0);
/*  208 */     columna.setCellRenderer(new GeneralColorRenderer());
/*  209 */     columna = this.jTable1.getColumnModel().getColumn(1);
/*  210 */     columna.setCellRenderer(new GeneralColorRenderer());
/*  211 */     columna = this.jTable1.getColumnModel().getColumn(2);
/*  212 */     columna.setCellRenderer(new ImporteColorRenderer());
/*  213 */     columna = this.jTable1.getColumnModel().getColumn(3);
/*  214 */     columna.setCellRenderer(new ImporteColorRenderer());
/*  215 */     columna = this.jTable1.getColumnModel().getColumn(4);
/*  216 */     columna.setCellRenderer(new ImporteColorRenderer());
/*  217 */     columna = this.jTable1.getColumnModel().getColumn(5);
/*  218 */     columna.setCellRenderer(new ImporteColorRenderer());
/*      */   }
/*      */ 
/*      */   private DefaultComboBoxModel getModeloLista()
/*      */   {
/*  223 */     DefaultComboBoxModel modeloLista = new DefaultComboBoxModel();
/*  224 */     ArrayList tiposIVA = new ArrayList();
/*  225 */     ManejoTiposIVA mT = new ManejoTiposIVA(Inicio.getCGeneral());
/*  226 */     tiposIVA = mT.getTiposIVA();
/*  227 */     for (TipoIVA tipo :(List<TipoIVA>) tiposIVA) {
/*  228 */       modeloLista.addElement(tipo);
/*      */     }
/*  230 */     return modeloLista;
/*      */   }
/*      */ 
/*      */   private boolean comprobarFecha(String fecha) {
/*  234 */     if (fecha.equals("")) {
/*  235 */       JOptionPane.showMessageDialog(getContentPane(), Mensajes.getString("sinFecha"));
/*  236 */       return false;
/*      */     }
/*  238 */     if (!fecha.substring(0, 4).equals(Inicio.p.getEjercicio())) {
/*  239 */       JOptionPane.showMessageDialog(getContentPane(), Mensajes.getString("ejercicioNoCorrecto"));
/*  240 */       return false;
/*      */     }
/*  242 */     return true;
/*      */   }
/*      */ 
/*      */   private void cambiarEtiquetas(boolean emitida) {
/*  246 */     if (emitida) {
/*  247 */       this.jLabel3.setText(Mensajes.getString("cliente"));
/*  248 */       this.jLabel5.setText(Mensajes.getString("ventasIng"));
/*  249 */       this.botonPagar.setText(Mensajes.getString("cobrar"));
/*      */ 
/*  254 */       this.esImportacion.setText("Exportación");
/*  255 */       this.esAdqIntrac.setText("Entr.Intrac.");
/*      */     } else {
/*  257 */       this.jLabel3.setText(Mensajes.getString("proveedor"));
/*  258 */       this.jLabel5.setText(Mensajes.getString("comprasGas"));
/*  259 */       this.botonPagar.setText(Mensajes.getString("pagar"));
/*      */ 
/*  262 */       this.esImportacion.setText("Importación");
/*  263 */       this.esAdqIntrac.setText("Adq.Intracom.");
/*      */     }
/*      */   }
/*      */ 
/*      */   protected void activar_seleccion_cuenta(int ctrl)
/*      */   {
/*  276 */     Inicio.p.setControl(ctrl);
/*  277 */     JInternalFrame[] todos = getDesktopPane().getAllFrames();
/*  278 */     int numero = todos.length;
/*  279 */     for (int x = 0; x <= numero; x++) {
/*  280 */       JInternalFrame f = todos[x];
/*  281 */       if (f.getTitle().equals(Mensajes.getString("gestCtas"))) {
/*  282 */         f.setVisible(true);
/*      */         try {
/*  284 */           if (f.isIcon()) {
/*  285 */             f.setIcon(false);
/*      */           }
/*  287 */           if (!f.isSelected())
/*  288 */             f.setSelected(true);
/*      */         }
/*      */         catch (PropertyVetoException exc)
/*      */         {
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private double doubleTwoDecimals(Double number) {
/*  298 */     if (number != null) {
/*  299 */       BigDecimal dec = new BigDecimal(number.doubleValue());
/*  300 */       return dec.setScale(2, 4).doubleValue();
/*      */     }
/*  302 */     return -1.0D;
/*      */   }
/*      */ 
/*      */   private double StringToDouble(String valor) {
/*  306 */     double numero = -1.0D;
/*      */     try {
/*  308 */       numero = Double.parseDouble(valor);
/*  309 */       numero = doubleTwoDecimals(Double.valueOf(numero));
/*      */     } catch (NumberFormatException exc) {
/*      */     }
/*  312 */     return numero;
/*      */   }
/*      */ 
/*      */   public void cerrarConexion()
/*      */   {
/*      */   }
/*      */ 
/*      */   private void pagarCobrar() {
/*  320 */     if ((!this.campoNumero.getText().equals("")) && (!this.campoSubcuenta.getText().equals("")) && (this.sumaTotales != 0.0D)) {
/*  321 */       PagaVencimientos dlg = new PagaVencimientos(Inicio.frame, Mensajes.getString("fecha") + " y " + Mensajes.getString("ctaPago"), this.iCalendarTextField1.getDate(), true);
/*  322 */       Dimension dlgSize = dlg.getPreferredSize();
/*  323 */       Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/*  324 */       dlg.setLocation((screenSize.width - dlgSize.width) / 2, (screenSize.height - dlgSize.height) / 2);
/*      */ 
/*  326 */       dlg.setModal(true);
/*  327 */       dlg.setVisible(true);
/*  328 */       int cuentaPago = dlg.enCuenta();
/*  329 */       if (cuentaPago != -1) {
/*  330 */         String fechaPago = dlg.enfecha();
/*  331 */         realizarPagoCobro(cuentaPago, fechaPago);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private void realizarPagoCobro(int cuentaPago, String fechaPago)
/*      */   {
/*  344 */     if (!fechaPago.substring(0, 4).equals(Inicio.p.getEjercicio())) {
/*  345 */       JOptionPane.showMessageDialog(getContentPane(), Mensajes.getString("ejercicioNoCorrecto"));
/*  346 */       return;
/*      */     }
/*  348 */     String cual = Mensajes.getString("pago");
/*      */ 
/*  350 */     int num_asto = this.asientoM.nuevoNumero();
/*  351 */     String marca = this.esRecibida.isSelected() ? "P" : "Q";
/*  352 */     if (num_asto != -1) {
/*  353 */       int id_asto = this.asientoM.crear(num_asto, fechaPago, this.campoNumero.getText(), marca);
/*  354 */       if (id_asto != -1) {
/*  355 */         if (((this.esEmitida.isSelected()) && (this.sumaTotales >= 0.0D)) || ((this.esRecibida.isSelected()) && (this.sumaTotales < 0.0D))) {
/*  356 */           this.apunteM.crear(id_asto, Integer.parseInt(this.campoSubcuenta.getText()), this.campoConcepto.getText(), Mensajes.getString("haberA"), this.sumaTotales >= 0.0D ? this.sumaTotales : -1.0D * this.sumaTotales);
/*  357 */           this.apunteM.crear(id_asto, cuentaPago, this.campoConcepto.getText(), Mensajes.getString("debeA"), this.sumaTotales >= 0.0D ? this.sumaTotales : -1.0D * this.sumaTotales);
/*  358 */           cual = Mensajes.getString("cobro");
/*      */         } else {
/*  360 */           double importeTotal = this.sumaTotales;
/*  361 */           if ((this.esRecibida.isSelected()) && (!this.campoRetencion.getText().equals(""))) {
/*  362 */             double retencion = StringToDouble(this.campoRetencion.getText());
/*  363 */             if (retencion != -1.0D) {
/*  364 */               importeTotal -= retencion;
/*      */             }
/*      */           }
/*  367 */           this.apunteM.crear(id_asto, Integer.parseInt(this.campoSubcuenta.getText()), this.campoConcepto.getText(), Mensajes.getString("debeA"), importeTotal >= 0.0D ? importeTotal : -1.0D * importeTotal);
/*  368 */           this.apunteM.crear(id_asto, cuentaPago, this.campoConcepto.getText(), Mensajes.getString("haberA"), importeTotal >= 0.0D ? importeTotal : -1.0D * importeTotal);
/*      */         }
/*  370 */         if (this.esEmitida.isSelected()) {
/*  371 */           String cliente = "";
/*  372 */           boolean enArchivo = false;
/*  373 */           int ctaGasto = -1;
/*  374 */           double porcent = 0.0D;
/*      */           try
/*      */           {
/*  377 */             LeerFichero ventasTPV = new LeerFichero("configdir/ventasTPV.ini");
/*  378 */             while (!ventasTPV.eof()) {
/*  379 */               String linea = ventasTPV.leer();
/*  380 */               int uno = linea.indexOf(";");
/*  381 */               int dos = linea.indexOf(";", uno + 1);
/*  382 */               String nombre = linea.substring(0, uno);
/*  383 */               if (cuentaPago == Integer.parseInt(nombre)) {
/*  384 */                 enArchivo = true;
/*  385 */                 ctaGasto = Integer.parseInt(linea.substring(uno + 1, dos));
/*  386 */                 porcent = Double.parseDouble(linea.substring(dos + 1, linea.lastIndexOf(";"))) / 100.0D;
/*  387 */                 cliente = linea.substring(linea.lastIndexOf(";") + 1);
/*  388 */                 break;
/*      */               }
/*      */             }
/*      */           } catch (IOException e) {
/*  392 */             e.printStackTrace();
/*      */           }
/*  394 */           if (!enArchivo) {
/*  395 */             cliente = "*";
/*  396 */             PagoTarjeta dlg = new PagoTarjeta(Inicio.frame, Mensajes.getString("comPagoTjta"), true);
/*  397 */             Dimension dlgSize = dlg.getPreferredSize();
/*  398 */             Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/*  399 */             dlg.setLocation((screenSize.width - dlgSize.width) / 2, (screenSize.height - dlgSize.height) / 2);
/*      */ 
/*  401 */             dlg.setVisible(true);
/*  402 */             porcent = dlg.conComision() / 100.0D;
/*  403 */             ctaGasto = dlg.enCuenta();
/*      */           }
/*  405 */           if (((cliente.equals("*")) || (cliente.equals(this.campoSubcuenta.getText()))) && 
/*  406 */             (ctaGasto != -1) && (porcent != 0.0D)) {
/*  407 */             double comTarjeta = (this.sumaTotales >= 0.0D ? this.sumaTotales : -1.0D * this.sumaTotales) * porcent;
/*  408 */             this.apunteM.crear(id_asto, cuentaPago, this.campoConcepto.getText(), Mensajes.getString("haberA"), comTarjeta);
/*  409 */             this.apunteM.crear(id_asto, ctaGasto, this.campoConcepto.getText(), Mensajes.getString("debeA"), comTarjeta);
/*      */           }
/*      */         }
/*      */ 
/*  413 */         JOptionPane.showMessageDialog(getContentPane(), cual + " " + Mensajes.getString("doWsucces"));
/*      */       }
/*      */     }
/*  416 */     Inicio.frame.renovarTabla(0);
/*      */   }
/*      */ 
/*      */   private void crearVencimientos() {
/*  420 */     boolean aPagar = !this.esEmitida.isSelected();
/*  421 */     if (this.iCalendarTextField1.getDate() != null) {
/*  422 */       SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
/*  423 */       Date fecha_n = this.iCalendarTextField1.getDate();
/*  424 */       String fecha_bd = sdf.format(fecha_n);
/*  425 */       if ((comprobarFecha(fecha_bd)) && (!this.campoNumero.getText().equals("")) && (this.sumaTotales != 0.0D)) {
/*  426 */         double retencion = 0.0D;
/*  427 */         String sRetencion = this.campoRetencion.getText();
/*  428 */         if ((sRetencion != null) && (!sRetencion.equals(""))) {
/*  429 */           retencion = StringToDouble(sRetencion);
/*  430 */           if (retencion == -1.0D) {
/*  431 */             retencion = 0.0D;
/*      */           }
/*      */         }
/*      */ 
/*  435 */         TipoFactura factura = new TipoFactura();
/*  436 */         factura.setNumero(this.campoNumero.getText());
/*  437 */         factura.setFecha(fecha_bd);
/*  438 */         factura.setTotal(this.sumaTotales - retencion);
/*  439 */         factura.setCuenta(Integer.parseInt(this.campoSubcuenta.getText()));
/*  440 */         GenerarVencimientos crear = new GenerarVencimientos(Inicio.frame, Mensajes.getString("menu35"), true, factura, aPagar);
/*      */ 
/*  443 */         Dimension dlgSize = crear.getSize();
/*  444 */         Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/*  445 */         crear.setLocation((screenSize.width - dlgSize.width) / 2, (screenSize.height - dlgSize.height) / 2);
/*      */ 
/*  447 */         crear.setVisible(true);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private void generarFactura() {
/*  453 */     int id_asto = -1;
/*  454 */     int num_asto = this.asientoM.nuevoNumero();
/*  455 */     Date fecha_n = this.iCalendarTextField1.getDate();
/*  456 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
/*  457 */     String fecha_bd = fecha_n != null ? sdf.format(fecha_n) : "";
/*  458 */     int importacion = 0;
/*  459 */     if (this.esRecibida.isSelected()) {
/*  460 */       if (this.esImportacion.isSelected()) {
/*  461 */         importacion = 1;
/*      */       }
/*  463 */       else if (this.esAdqIntrac.isSelected()) {
/*  464 */         importacion = 2;
/*      */       }
/*      */     }
/*  467 */     if (!comprobarFecha(fecha_bd)) {
/*  468 */       return;
/*      */     }
/*  470 */     boolean emitida = this.esEmitida.isSelected();
/*  471 */     String marca = this.esEmitida.isSelected() ? "E" : "R";
/*  472 */     int existe = this.facturaM.existeFactura(emitida, this.campoNumero.getText());
/*  473 */     if (existe != -1) {
/*  474 */       int borro = JOptionPane.showConfirmDialog(getContentPane(), "Se va a modificar una factura ya creada.", Mensajes.getString("confirma"), 0);
/*      */ 
/*  477 */       if (borro == 0)
/*  478 */         num_asto = this.asientoM.numeroAsiento(existe);
/*      */       else {
/*  480 */         return;
/*      */       }
/*      */     }
/*  483 */     if (num_asto != -1) {
/*  484 */       if (existe != -1)
/*      */       {
/*  486 */         this.facturaM.borrar(emitida, this.campoNumero.getText());
/*      */       }
/*  488 */       id_asto = this.asientoM.crear(num_asto, fecha_bd, this.campoNumero.getText(), marca);
/*  489 */       if (id_asto != -1) {
/*  490 */         if (!this.facturaM.crear(emitida, this.campoNumero.getText(), fecha_bd, Integer.parseInt(this.campoSubcuenta.getText()), id_asto, this.campoConcepto.getText(), this.sumaBases, this.sumaIvas, this.sumaREs, importacion))
/*      */         {
/*  493 */           this.asientoM.borrar(id_asto);
/*      */         }
/*  495 */         String concepto = this.campoConcepto.getText();
/*  496 */         String debe = Mensajes.getString("debeA");
/*  497 */         String haber = Mensajes.getString("haberA");
/*  498 */         if (emitida) {
/*  499 */           if (this.sumaTotales > 0.0D) {
/*  500 */             double importeCliente = this.sumaTotales;
/*  501 */             if ((!this.campoCuentaRetencion.getText().equals("")) && (!this.campoRetencion.getText().equals(""))) {
/*  502 */               double retencion = StringToDouble(this.campoRetencion.getText());
/*  503 */               if (retencion != -1.0D) {
/*  504 */                 importeCliente -= retencion;
/*  505 */                 this.apunteM.crear(id_asto, Integer.parseInt(this.campoCuentaRetencion.getText()), concepto, debe, retencion);
/*      */               }
/*      */             }
/*  508 */             this.apunteM.crear(id_asto, Integer.parseInt(this.campoSubcuenta.getText()), concepto, debe, importeCliente);
/*  509 */           } else if (this.sumaTotales < 0.0D) {
/*  510 */             double importeCliente = -1.0D * this.sumaTotales;
/*  511 */             if ((!this.campoCuentaRetencion.getText().equals("")) && (!this.campoRetencion.getText().equals(""))) {
/*  512 */               double retencion = StringToDouble(this.campoRetencion.getText());
/*  513 */               if (retencion != -1.0D) {
/*  514 */                 if (retencion < 0.0D) {
/*  515 */                   retencion = -1.0D * retencion;
/*      */                 }
/*  517 */                 importeCliente -= retencion;
/*  518 */                 this.apunteM.crear(id_asto, Integer.parseInt(this.campoCuentaRetencion.getText()), concepto, haber, retencion);
/*      */               }
/*      */             }
/*  521 */             this.apunteM.crear(id_asto, Integer.parseInt(this.campoSubcuenta.getText()), concepto, haber, importeCliente);
/*      */           }
/*      */ 
/*      */         }
/*  525 */         else if (this.sumaTotales > 0.0D) {
/*  526 */           double importeProveedor = this.sumaTotales;
/*  527 */           if ((!this.campoCuentaRetencion.getText().equals("")) && (!this.campoRetencion.getText().equals(""))) {
/*  528 */             double retencion = StringToDouble(this.campoRetencion.getText());
/*  529 */             if (retencion != -1.0D) {
/*  530 */               importeProveedor -= retencion;
/*  531 */               this.apunteM.crear(id_asto, Integer.parseInt(this.campoCuentaRetencion.getText()), concepto, haber, retencion);
/*      */             }
/*      */           }
/*  534 */           this.apunteM.crear(id_asto, Integer.parseInt(this.campoSubcuenta.getText()), concepto, haber, importeProveedor);
/*  535 */         } else if (this.sumaTotales < 0.0D) {
/*  536 */           double importeProveedor = -1.0D * this.sumaTotales;
/*  537 */           if ((!this.campoCuentaRetencion.getText().equals("")) && (!this.campoRetencion.getText().equals(""))) {
/*  538 */             double retencion = StringToDouble(this.campoRetencion.getText());
/*  539 */             if (retencion != -1.0D) {
/*  540 */               if (retencion < 0.0D) {
/*  541 */                 retencion = -1.0D * retencion;
/*      */               }
/*  543 */               importeProveedor -= retencion;
/*  544 */               this.apunteM.crear(id_asto, Integer.parseInt(this.campoCuentaRetencion.getText()), concepto, debe, retencion);
/*      */             }
/*      */           }
/*  547 */           this.apunteM.crear(id_asto, Integer.parseInt(this.campoSubcuenta.getText()), concepto, debe, importeProveedor);
/*      */         }
/*      */ 
/*  550 */         int filas = this.jTable1.getModel().getRowCount();
/*  551 */         DefaultTableModel modelo = (DefaultTableModel)this.jTable1.getModel();
/*  552 */         for (int fila = 0; fila < filas; fila++) {
/*  553 */           int cuentaGI = ((Integer)modelo.getValueAt(fila, 0)).intValue();
/*  554 */           TipoIVA tipoIva = (TipoIVA)modelo.getValueAt(fila, 1);
/*  555 */           double baseImp = ((Double)modelo.getValueAt(fila, 2)).doubleValue();
/*  556 */           double iva = ((Double)modelo.getValueAt(fila, 3)).doubleValue();
/*  557 */           int cuentaIva = tipoIva.getCuentaSop();
/*      */ 
/*  559 */           if (emitida) {
/*  560 */             cuentaIva = tipoIva.getCuentaRep();
/*  561 */             int cuentaRe = tipoIva.getCuentaRE();
/*  562 */             double re = ((Double)modelo.getValueAt(fila, 4)).doubleValue();
/*  563 */             if (baseImp > 0.0D) {
/*  564 */               this.apunteM.crear(id_asto, cuentaGI, concepto, haber, baseImp);
/*  565 */               if (iva != 0.0D) {
/*  566 */                 this.apunteM.crear(id_asto, cuentaIva, concepto, haber, iva);
/*      */               }
/*  568 */               if (re != 0.0D)
/*  569 */                 this.apunteM.crear(id_asto, cuentaRe, concepto, haber, re);
/*      */             }
/*  571 */             else if (baseImp < 0.0D) {
/*  572 */               this.apunteM.crear(id_asto, cuentaGI, concepto, debe, -1.0D * baseImp);
/*  573 */               if (iva != 0.0D) {
/*  574 */                 this.apunteM.crear(id_asto, cuentaIva, concepto, debe, -1.0D * iva);
/*      */               }
/*  576 */               if (re != 0.0D) {
/*  577 */                 this.apunteM.crear(id_asto, cuentaRe, concepto, debe, -1.0D * re);
/*      */               }
/*      */             }
/*      */           }
/*  581 */           else if (baseImp > 0.0D) {
/*  582 */             this.apunteM.crear(id_asto, cuentaGI, concepto, debe, baseImp);
/*  583 */             if (iva != 0.0D) {
/*  584 */               this.apunteM.crear(id_asto, cuentaIva, concepto, debe, iva);
/*  585 */               if (importacion == 2) {
/*  586 */                 this.apunteM.crear(id_asto, tipoIva.getCuentaRep(), concepto, haber, iva);
/*      */               }
/*      */             }
/*      */           }
/*  590 */           else if (baseImp < 0.0D) {
/*  591 */             this.apunteM.crear(id_asto, cuentaGI, concepto, haber, -1.0D * baseImp);
/*  592 */             if (iva != 0.0D) {
/*  593 */               this.apunteM.crear(id_asto, cuentaIva, concepto, haber, -1.0D * iva);
/*  594 */               if (importacion == 2) {
/*  595 */                 this.apunteM.crear(id_asto, tipoIva.getCuentaRep(), concepto, debe, -1.0D * iva);
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */ 
/*  602 */       Inicio.frame.renovarTabla(0);
/*  603 */       if (emitida)
/*  604 */         Inicio.frame.renovarTabla(5);
/*      */       else {
/*  606 */         Inicio.frame.renovarTabla(6);
/*      */       }
/*  608 */       int pregunta = JOptionPane.showConfirmDialog(getContentPane(), Mensajes.getString("factCreada") + "\n" + Mensajes.getString("limpiarForm"), Mensajes.getString("confirma"), 0);
/*      */ 
/*  611 */       if (pregunta == 0) {
/*  612 */         limpiarForm();
/*  613 */         this.campoNumero.requestFocus();
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private void cargarFactura(String numero) {
/*  619 */     if (numero.substring(0, 1).equals("E")) {
/*  620 */       this.esEmitida.setSelected(true);
/*  621 */       this.esRecibida.setSelected(false);
/*      */     }
/*      */     else {
/*  624 */       this.esEmitida.setSelected(false);
/*  625 */       this.esRecibida.setSelected(true);
/*      */     }
/*  627 */     cambiarEtiquetas(this.esEmitida.isSelected());
/*  628 */     numero = numero.substring(1);
/*      */     TipoIVA ivaCero;
/*      */    
/*  629 */     if (this.esEmitida.isSelected())
/*      */     {
/*  631 */       TipoFactura factura = this.facturaM.datos(true, numero);
/*  632 */       if (factura != null) {
/*  633 */         limpiarForm();
/*  634 */         this.campoNumero.setText(factura.getNumero());
/*  635 */         if (factura.getRe() != 0.0D) {
/*  636 */           this.esRecargo.setSelected(true);
/*      */         }
/*      */         else {
/*  639 */           this.esRecargo.setSelected(false);
/*      */         }
/*  641 */         this.campoConcepto.setText(factura.getConcepto());
/*  642 */         String cad = factura.getFecha();
/*  643 */         int year = Integer.parseInt(cad.substring(0, 4));
/*  644 */         int month = Integer.parseInt(cad.substring(5, 7)) - 1;
/*  645 */         int date = Integer.parseInt(cad.substring(8));
/*  646 */         GregorianCalendar calendario = new GregorianCalendar();
/*  647 */         calendario.set(year, month, date);
/*  648 */         this.iCalendarTextField1.setCalendar(calendario);
/*  649 */         this.campoSubcuenta.requestFocus();
/*  650 */         this.campoSubcuenta.setText(Integer.toString(factura.getCuenta()));
/*  651 */         this.campoConcepto.requestFocus();
/*  652 */         ManejoTiposIVA mT = new ManejoTiposIVA(Inicio.getCGeneral());
/*  653 */         ivaCero = mT.getFullTipoCero();
/*  654 */         ArrayList apuntes = new ArrayList();
/*  655 */         ArrayList apuntesIngresos = new ArrayList();
/*  656 */         apuntes = this.asientoM.getApuntes(factura.getId_asiento());
/*  657 */         apuntesIngresos = this.asientoM.getApuntes(factura.getId_asiento());
/*      */ 
/*  659 */         for (int z = 0; z < apuntesIngresos.size(); z++) {
/*  660 */           String subcuenta = Integer.toString(((TipoApunte)apuntesIngresos.get(z)).getCuenta());
/*  661 */           if ((subcuenta.substring(0, 3).equals("473")) || (subcuenta.substring(0, 4).equals("4751"))) {
/*  662 */             this.campoCuentaRetencion.setText(subcuenta);
/*  663 */             this.campoRetencion.setText(Double.toString(((TipoApunte)apuntesIngresos.get(z)).getImporte()));
/*  664 */             apuntesIngresos.remove(z);
/*  665 */             break;
/*      */           }
/*      */         }
/*      */ 
/*  669 */         for (TipoApunte apunte : (List<TipoApunte>)apuntes) {
/*  670 */           boolean abono = false;
/*  671 */           int subcuentaIngresos = 0;
/*  672 */           String tipoIva = mT.getTipoForInvoices(apunte.getCuenta());
/*  673 */           String regimen = tipoIva.substring(0, 1);
/*  674 */           tipoIva = tipoIva.substring(1);
/*  675 */           double tipo = Double.valueOf(tipoIva).doubleValue();
/*  676 */           if ((tipo != 0.0D) && (regimen.equals("G"))) {
/*  677 */             double importeIva = apunte.getImporte();
/*  678 */             double importeBase = importeIva / (tipo / 100.0D);
/*      */ 
/*  680 */             for (int z = 0; z < apuntesIngresos.size(); z++) {
/*  681 */               double importe = ((TipoApunte)apuntesIngresos.get(z)).getImporte();
/*  682 */               int subcuenta = ((TipoApunte)apuntesIngresos.get(z)).getCuenta();
/*  683 */               if ((Integer.toString(subcuenta).substring(0, 1).equals("7")) && (importe >= importeBase - 0.4D) && (importe <= importeBase + 0.4D))
/*      */               {
/*  685 */                 if (((TipoApunte)apuntesIngresos.get(z)).getDH().equals("D"))
/*  686 */                   abono = true;
/*  687 */                 subcuentaIngresos = subcuenta;
/*  688 */                 apuntesIngresos.remove(z);
/*  689 */                 break;
/*      */               }
/*      */             }
/*      */ 
/*  693 */             TipoIVA fullTipoIva = mT.getFullTipoForInvoices(apunte.getCuenta());
/*      */ 
/*  695 */             double importeRe = 0.0D;
/*  696 */             if (this.esRecargo.isSelected())
/*  697 */               importeRe = importeBase * (fullTipoIva.getRe() / 100.0D);
/*  698 */             double importeTotal = importeBase + importeIva + importeRe;
/*  699 */             if (abono) {
/*  700 */               importeBase *= -1.0D;
/*  701 */               importeIva *= -1.0D;
/*  702 */               importeRe *= -1.0D;
/*  703 */               importeTotal *= -1.0D;
/*      */             }
/*  705 */             this.sumaBases += importeBase;
/*  706 */             this.sumaIvas += importeIva;
/*  707 */             this.sumaREs += importeRe;
/*  708 */             this.sumaTotales += importeTotal;
/*  709 */             Object[] newLine = new Object[6];
/*  710 */             newLine[0] = Integer.valueOf(subcuentaIngresos);
/*  711 */             newLine[1] = fullTipoIva;
/*  712 */             newLine[2] = Double.valueOf(importeBase);
/*  713 */             newLine[3] = Double.valueOf(importeIva);
/*  714 */             newLine[4] = Double.valueOf(importeRe);
/*  715 */             newLine[5] = Double.valueOf(importeTotal);
/*  716 */             DefaultTableModel modelo = (DefaultTableModel)this.jTable1.getModel();
/*  717 */             modelo.addRow(newLine);
/*  718 */             colocarSumas();
/*      */           }
/*      */         }
/*      */ 
/*  722 */         for (TipoApunte apunte : (List<TipoApunte>)apuntesIngresos) {
/*  723 */           double importe = apunte.getImporte();
/*  724 */           int subcuenta = apunte.getCuenta();
/*  725 */           if (Integer.toString(subcuenta).substring(0, 1).equals("7")) {
/*  726 */             if (apunte.getDH().equals("D"))
/*  727 */               importe *= -1.0D;
/*  728 */             this.sumaBases += importe;
/*  729 */             this.sumaTotales += importe;
/*  730 */             Object[] newLine = new Object[6];
/*  731 */             newLine[0] = Integer.valueOf(subcuenta);
/*  732 */             newLine[1] = ivaCero;
/*  733 */             newLine[2] = Double.valueOf(importe);
/*  734 */             newLine[3] = Float.valueOf(0.0F);
/*  735 */             newLine[4] = Float.valueOf(0.0F);
/*  736 */             newLine[5] = Double.valueOf(importe);
/*  737 */             DefaultTableModel modelo = (DefaultTableModel)this.jTable1.getModel();
/*  738 */             modelo.addRow(newLine);
/*  739 */             colocarSumas();
/*      */           }
/*      */         }
/*      */       }
/*  743 */     } else if (this.esRecibida.isSelected())
/*      */     {
/*  745 */       TipoFacturaExtended factura = this.facturaM.datos(numero);
/*  746 */       if (factura != null) {
/*  747 */         limpiarForm();
/*  748 */         this.campoNumero.setText(factura.getNumero());
/*      */ 
/*  761 */         this.campoConcepto.setText(factura.getConcepto());
/*  762 */         String cad = factura.getFecha();
/*  763 */         int year = Integer.parseInt(cad.substring(0, 4));
/*  764 */         int month = Integer.parseInt(cad.substring(5, 7)) - 1;
/*  765 */         int date = Integer.parseInt(cad.substring(8));
/*  766 */         GregorianCalendar calendario = new GregorianCalendar();
/*  767 */         calendario.set(year, month, date);
/*  768 */         this.iCalendarTextField1.setCalendar(calendario);
/*  769 */         this.campoSubcuenta.requestFocus();
/*  770 */         this.campoSubcuenta.setText(Integer.toString(factura.getCuenta()));
/*  771 */         this.campoConcepto.requestFocus();
/*  772 */         ManejoTiposIVA mT = new ManejoTiposIVA(Inicio.getCGeneral());
/*  773 */         ivaCero = mT.getFullTipoCero();
/*  774 */         ArrayList apuntes = new ArrayList();
/*  775 */         ArrayList apuntesGastos = new ArrayList();
/*  776 */         apuntes = this.asientoM.getApuntes(factura.getId_asiento());
/*  777 */         apuntesGastos = this.asientoM.getApuntes(factura.getId_asiento());
/*      */ 
/*  779 */         for (int z = 0; z < apuntesGastos.size(); z++) {
/*  780 */           String subcuenta = Integer.toString(((TipoApunte)apuntesGastos.get(z)).getCuenta());
/*  781 */           if (subcuenta.substring(0, 4).equals("4751")) {
/*  782 */             this.campoCuentaRetencion.setText(subcuenta);
/*  783 */             this.campoRetencion.setText(Double.toString(((TipoApunte)apuntesGastos.get(z)).getImporte()));
/*  784 */             apuntesGastos.remove(z);
/*  785 */             break;
/*      */           }
/*      */         }
/*      */ 
/*  789 */         for (TipoApunte apunte :(List<TipoApunte>) apuntes) {
/*  790 */           boolean abono = false;
/*  791 */           int subcuentaGastos = 0;
/*  792 */           String tipoIva = mT.getTipoForInvoices(apunte.getCuenta());
/*  793 */           String regimen = tipoIva.substring(0, 1);
/*  794 */           tipoIva = tipoIva.substring(1);
/*  795 */           double tipo = Double.valueOf(tipoIva).doubleValue();
/*  796 */           if ((tipo != 0.0D) && (regimen.equals("G"))) {
/*  797 */             double importeIva = apunte.getImporte();
/*  798 */             double importeBase = importeIva / (tipo / 100.0D);
/*      */ 
/*  800 */             for (int z = 0; z < apuntesGastos.size(); z++) {
/*  801 */               double importe = ((TipoApunte)apuntesGastos.get(z)).getImporte();
/*  802 */               int subcuenta = ((TipoApunte)apuntesGastos.get(z)).getCuenta();
/*  803 */               if ((Integer.toString(subcuenta).substring(0, 1).equals("6")) || (Integer.toString(subcuenta).substring(0, 1).equals("2")) || (Integer.toString(subcuenta).substring(0, 3).equals("174")) || ((Integer.toString(subcuenta).substring(0, 3).equals("524")) && (importe >= importeBase - 0.4D) && (importe <= importeBase + 0.4D)))
/*      */               {
/*  808 */                 if (((TipoApunte)apuntesGastos.get(z)).getDH().equals("H"))
/*  809 */                   abono = true;
/*  810 */                 subcuentaGastos = subcuenta;
/*  811 */                 apuntesGastos.remove(z);
/*  812 */                 break;
/*      */               }
/*      */             }
/*      */ 
/*  816 */             TipoIVA fullTipoIva = mT.getFullTipoForInvoices(apunte.getCuenta());
/*  817 */             double importeRe = 0.0D;
/*  818 */             double importeTotal = importeBase + importeIva + importeRe;
/*  819 */             if (this.esAdqIntrac.isSelected()) {
/*  820 */               importeTotal = importeBase;
/*      */             }
/*  822 */             if (abono) {
/*  823 */               importeBase *= -1.0D;
/*  824 */               importeIva *= -1.0D;
/*  825 */               importeRe *= -1.0D;
/*  826 */               importeTotal *= -1.0D;
/*      */             }
/*  828 */             this.sumaBases += importeBase;
/*  829 */             this.sumaIvas += importeIva;
/*  830 */             this.sumaREs += importeRe;
/*  831 */             this.sumaTotales += importeTotal;
/*  832 */             Object[] newLine = new Object[6];
/*  833 */             newLine[0] = Integer.valueOf(subcuentaGastos);
/*  834 */             newLine[1] = fullTipoIva;
/*  835 */             newLine[2] = Double.valueOf(importeBase);
/*  836 */             newLine[3] = Double.valueOf(importeIva);
/*  837 */             newLine[4] = Double.valueOf(importeRe);
/*  838 */             newLine[5] = Double.valueOf(importeTotal);
/*  839 */             DefaultTableModel modelo = (DefaultTableModel)this.jTable1.getModel();
/*  840 */             modelo.addRow(newLine);
/*  841 */             colocarSumas();
/*      */           }
/*      */         }
/*      */ 
/*  845 */         for (TipoApunte apunte :(List<TipoApunte>) apuntesGastos) {
/*  846 */           double importe = apunte.getImporte();
/*  847 */           int subcuenta = apunte.getCuenta();
/*  848 */           if ((Integer.toString(subcuenta).substring(0, 1).equals("6")) || (Integer.toString(subcuenta).substring(0, 1).equals("2")) || (Integer.toString(subcuenta).substring(0, 3).equals("174")) || (Integer.toString(subcuenta).substring(0, 3).equals("524")))
/*      */           {
/*  852 */             if (apunte.getDH().equals("H"))
/*  853 */               importe *= -1.0D;
/*  854 */             this.sumaBases += importe;
/*  855 */             this.sumaTotales += importe;
/*  856 */             Object[] newLine = new Object[6];
/*  857 */             newLine[0] = Integer.valueOf(subcuenta);
/*  858 */             newLine[1] = ivaCero;
/*  859 */             newLine[2] = Double.valueOf(importe);
/*  860 */             newLine[3] = Float.valueOf(0.0F);
/*  861 */             newLine[4] = Float.valueOf(0.0F);
/*  862 */             newLine[5] = Double.valueOf(importe);
/*  863 */             DefaultTableModel modelo = (DefaultTableModel)this.jTable1.getModel();
/*  864 */             modelo.addRow(newLine);
/*  865 */             colocarSumas();
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*  870 */     Inicio.p.setFactura("NEW");
/*      */   }
/*      */ 
/*      */   private void initComponents()
/*      */   {
/*  882 */     this.buttonGroup1 = new ButtonGroup();
/*  883 */     this.jPanel1 = new JPanel();
/*  884 */     this.esEmitida = new JRadioButton();
/*  885 */     this.esRecibida = new JRadioButton();
/*  886 */     this.jLabel1 = new JLabel();
/*  887 */     this.campoNumero = new JTextField();
/*  888 */     this.jLabel2 = new JLabel();
/*  889 */     this.jLabel3 = new JLabel();
/*  890 */     this.campoNombreSubcuenta = new JTextField();
/*  891 */     this.jLabel4 = new JLabel();
/*  892 */     this.campoConcepto = new JTextField();
/*  893 */     this.jLabel5 = new JLabel();
/*  894 */     this.iCalendarTextField1 = new ICalendarTextField();
/*  895 */     this.campoTerceros = new CampoCuenta(this.subcuentaM);
/*  896 */     this.campoSubcuenta = new CampoCuenta(this.subcuentaM);
/*  897 */     this.jLabel6 = new JLabel();
/*  898 */     this.comboTiposIva = new JComboBox();
/*  899 */     this.jLabel7 = new JLabel();
/*  900 */     this.campoBase = new JTextField();
/*  901 */     this.jScrollPane1 = new JScrollPane();
/*  902 */     this.jTable1 = new JTable();
/*  903 */     this.botonAddTable = new JButton();
/*  904 */     this.jLabel8 = new JLabel();
/*  905 */     this.campoSumaBases = new JTextField();
/*  906 */     this.campoSumaIvas = new JTextField();
/*  907 */     this.campoSumaREs = new JTextField();
/*  908 */     this.campoSumaTotales = new JTextField();
/*  909 */     this.botonGenerar = new JButton();
/*  910 */     this.botonLimpiar = new JButton();
/*  911 */     this.botonPagar = new JButton();
/*  912 */     this.botonVencimientos = new JButton();
/*  913 */     this.jLabel9 = new JLabel();
/*  914 */     this.campoRetencion = new JTextField();
/*  915 */     this.jLabel10 = new JLabel();
/*  916 */     this.campoCuentaRetencion = new CampoCuenta(this.subcuentaM);
/*  917 */     this.jLabel11 = new JLabel();
/*  918 */     this.jPanel2 = new JPanel();
/*  919 */     this.esImportacion = new JRadioButton();
/*  920 */     this.esAdqIntrac = new JRadioButton();
/*  921 */     this.esRecargo = new JCheckBox();
/*      */ 
/*  923 */     addFocusListener(new FocusAdapter() {
/*      */       public void focusGained(FocusEvent evt) {
/*  925 */         CreacionFacturas.this.formFocusGained(evt);
/*      */       }
/*      */     });
/*  929 */     this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/*      */ 
/*  931 */     this.buttonGroup1.add(this.esEmitida);
/*  932 */     this.esEmitida.setText("Factura emitida");
/*  933 */     this.esEmitida.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  935 */         CreacionFacturas.this.esEmitidaActionPerformed(evt);
/*      */       }
/*      */     });
/*  939 */     this.buttonGroup1.add(this.esRecibida);
/*  940 */     this.esRecibida.setText("Factura recibida");
/*  941 */     this.esRecibida.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  943 */         CreacionFacturas.this.esRecibidaActionPerformed(evt);
/*      */       }
/*      */     });
/*  947 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/*  948 */     this.jPanel1.setLayout(jPanel1Layout);
/*  949 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(jPanel1Layout.createParallelGroup(1).add(this.esEmitida).add(this.esRecibida)).addContainerGap(23, 32767)));
/*      */ 
/*  958 */     jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(this.esEmitida).addPreferredGap(1).add(this.esRecibida).addContainerGap(-1, 32767)));
/*      */ 
/*  968 */     this.jLabel1.setText("Número de factura");
/*      */ 
/*  970 */     this.jLabel2.setText("Fecha de factura");
/*      */ 
/*  972 */     this.jLabel3.setText("Proveedor");
/*      */ 
/*  974 */     this.campoNombreSubcuenta.setEditable(false);
/*      */ 
/*  976 */     this.jLabel4.setText("Concepto");
/*      */ 
/*  978 */     this.campoConcepto.setDocument(new DocTreintaCarac());
/*      */ 
/*  980 */     this.jLabel5.setText("Compras/Gastos");
/*      */ 
/*  982 */     this.campoSubcuenta.addFocusListener(new FocusAdapter() {
/*      */       public void focusLost(FocusEvent evt) {
/*  984 */         CreacionFacturas.this.campoSubcuentaFocusLost(evt);
/*      */       }
/*      */     });
/*  988 */     this.jLabel6.setText("Tipo IVA");
/*      */ 
/*  990 */     this.jLabel7.setText("Base imponible");
/*      */ 
/*  992 */     this.jTable1.setModel(new DefaultTableModel(new String[] { "Subcuenta", "Tipo IVA", "Base", "IVA", "RE", "Total" }, 0)
/*      */     {
/*  997 */       Class[] types = { Integer.class, TiposIva.class, Double.class, Double.class, Double.class, Double.class };
/*      */ 
/* 1000 */       boolean[] canEdit = { false, false, false, false, false, false };
/*      */ 
/*      */       public Class getColumnClass(int columnIndex)
/*      */       {
/* 1005 */         return this.types[columnIndex];
/*      */       }
/*      */ 
/*      */       public boolean isCellEditable(int rowIndex, int columnIndex) {
/* 1009 */         return this.canEdit[columnIndex];
/*      */       }
/*      */     });
/* 1012 */     this.jTable1.addMouseListener(new MouseAdapter() {
/*      */       public void mouseClicked(MouseEvent evt) {
/* 1014 */         CreacionFacturas.this.jTableMouseClicked(evt);
/*      */       }
/*      */     });
/* 1017 */     this.jScrollPane1.setViewportView(this.jTable1);
/*      */ 
/* 1019 */     this.botonAddTable.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/redo16.png")));
/* 1020 */     this.botonAddTable.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/* 1022 */         CreacionFacturas.this.botonAddTableActionPerformed(evt);
/*      */       }
/*      */     });
/* 1026 */     this.jLabel8.setText("Sumas");
/*      */ 
/* 1028 */     this.botonGenerar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/new.png")));
/* 1029 */     this.botonGenerar.setText("Crear factura");
/* 1030 */     this.botonGenerar.setHorizontalTextPosition(2);
/* 1031 */     this.botonGenerar.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/* 1033 */         CreacionFacturas.this.botonGenerarActionPerformed(evt);
/*      */       }
/*      */     });
/* 1037 */     this.botonLimpiar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/cancel.png")));
/* 1038 */     this.botonLimpiar.setText("Limpiar");
/* 1039 */     this.botonLimpiar.setHorizontalTextPosition(2);
/* 1040 */     this.botonLimpiar.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/* 1042 */         CreacionFacturas.this.botonLimpiarActionPerformed(evt);
/*      */       }
/*      */     });
/* 1046 */     this.botonPagar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/importe16.png")));
/* 1047 */     this.botonPagar.setText("Pagar");
/* 1048 */     this.botonPagar.setHorizontalTextPosition(2);
/* 1049 */     this.botonPagar.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/* 1051 */         CreacionFacturas.this.botonPagarActionPerformed(evt);
/*      */       }
/*      */     });
/* 1055 */     this.botonVencimientos.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/date.png")));
/* 1056 */     this.botonVencimientos.setText("Vencimientos");
/* 1057 */     this.botonVencimientos.setHorizontalTextPosition(2);
/* 1058 */     this.botonVencimientos.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/* 1060 */         CreacionFacturas.this.botonVencimientosActionPerformed(evt);
/*      */       }
/*      */     });
/* 1064 */     this.jLabel9.setText("Retención :");
/*      */ 
/* 1066 */     this.jLabel10.setText("Subcuenta");
/*      */ 
/* 1068 */     this.jLabel11.setText("Importe");
/*      */ 
/* 1070 */     this.jPanel2.setBorder(BorderFactory.createEtchedBorder());
/*      */ 
/* 1072 */     ResourceBundle bundle = ResourceBundle.getBundle("internationalization/Mensajes");
/* 1073 */     this.esImportacion.setText(bundle.getString("importacion"));
/* 1074 */     this.esImportacion.setEnabled(false);
/* 1075 */     this.esImportacion.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/* 1077 */         CreacionFacturas.this.esImportacionActionPerformed(evt);
/*      */       }
/*      */     });
/* 1081 */     this.esAdqIntrac.setText("Adq.Intracom.");
/* 1082 */     this.esAdqIntrac.setEnabled(false);
/* 1083 */     this.esAdqIntrac.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/* 1085 */         CreacionFacturas.this.esAdqIntracActionPerformed(evt);
/*      */       }
/*      */     });
/* 1089 */     GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
/* 1090 */     this.jPanel2.setLayout(jPanel2Layout);
/* 1091 */     jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(1).add(jPanel2Layout.createSequentialGroup().addContainerGap().add(jPanel2Layout.createParallelGroup(1).add(this.esAdqIntrac, -2, 132, -2).add(this.esImportacion)).addContainerGap(23, 32767)));
/*      */ 
/* 1100 */     jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(1).add(jPanel2Layout.createSequentialGroup().addContainerGap().add(this.esImportacion).addPreferredGap(0).add(this.esAdqIntrac).addContainerGap(13, 32767)));
/*      */ 
/* 1110 */     this.esRecargo.setText("Recargo Equiv.");
/*      */ 
/* 1112 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 1113 */     getContentPane().setLayout(layout);
/* 1114 */     layout.setHorizontalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(layout.createParallelGroup(1).add(layout.createSequentialGroup().add(275, 275, 275).add(this.campoBase, -2, 127, -2).add(18, 18, 18).add(this.botonAddTable)).add(layout.createSequentialGroup().add(layout.createParallelGroup(1).add(this.campoTerceros, -2, 122, -2).add(this.jLabel5)).add(layout.createParallelGroup(1).add(layout.createSequentialGroup().addPreferredGap(0).add(this.comboTiposIva, -2, 109, -2)).add(layout.createSequentialGroup().add(153, 153, 153).add(this.jLabel7)).add(layout.createSequentialGroup().add(32, 32, 32).add(this.jLabel6, -2, 67, -2)))).add(2, layout.createSequentialGroup().add(layout.createParallelGroup(1, false).add(this.jPanel2, -1, -1, 32767).add(this.jPanel1, -1, -1, 32767)).add(36, 36, 36).add(layout.createParallelGroup(1).add(layout.createSequentialGroup().add(layout.createParallelGroup(1).add(layout.createSequentialGroup().add(this.jLabel1).add(42, 42, 42)).add(layout.createSequentialGroup().add(this.campoNumero, -1, 216, 32767).addPreferredGap(0))).add(layout.createParallelGroup(1).add(this.iCalendarTextField1, -2, 128, -2).add(this.jLabel2)).add(18, 18, 18).add(this.esRecargo).addPreferredGap(0)).add(layout.createSequentialGroup().addPreferredGap(0).add(this.jLabel3).addPreferredGap(0).add(this.campoSubcuenta, -2, 95, -2).addPreferredGap(0).add(this.campoNombreSubcuenta, -1, 323, 32767)).add(layout.createSequentialGroup().addPreferredGap(0).add(layout.createParallelGroup(1).add(layout.createSequentialGroup().add(this.jLabel4).addPreferredGap(0).add(this.campoConcepto, -1, 426, 32767)).add(2, layout.createSequentialGroup().add(this.jLabel9).addPreferredGap(0, 14, 32767).add(this.jLabel10).addPreferredGap(0).add(this.campoCuentaRetencion, -2, 136, -2).addPreferredGap(0).add(this.jLabel11).addPreferredGap(0).add(this.campoRetencion, -2, 140, -2))))).add(47, 47, 47)).add(2, layout.createSequentialGroup().add(layout.createParallelGroup(2).add(1, this.jScrollPane1, -1, 682, 32767).add(layout.createSequentialGroup().add(layout.createParallelGroup(2).add(layout.createSequentialGroup().add(this.jLabel8).add(35, 35, 35)).add(layout.createSequentialGroup().add(this.botonLimpiar, -2, 92, -2).add(19, 19, 19))).add(layout.createParallelGroup(1).add(2, this.campoSumaBases, -2, 98, -2).add(2, this.botonGenerar)).add(18, 18, 18).add(layout.createParallelGroup(1).add(layout.createSequentialGroup().add(this.campoSumaIvas, -2, 87, -2).addPreferredGap(0).add(this.campoSumaREs, -2, 80, -2).addPreferredGap(0).add(this.campoSumaTotales, -2, 104, -2)).add(layout.createSequentialGroup().add(this.botonPagar).add(18, 18, 18).add(this.botonVencimientos))))).add(58, 58, 58)))));
/*      */ 
/* 1211 */     layout.setVerticalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().add(layout.createParallelGroup(1).add(layout.createSequentialGroup().add(layout.createParallelGroup(1, false).add(layout.createSequentialGroup().add(20, 20, 20).add(layout.createParallelGroup(3).add(this.jLabel1).add(this.jLabel2)).addPreferredGap(0).add(layout.createParallelGroup(1).add(this.iCalendarTextField1, -2, -1, -2).add(this.campoNumero, -2, -1, -2)).addPreferredGap(0)).add(2, layout.createSequentialGroup().addContainerGap(-1, 32767).add(this.esRecargo).add(24, 24, 24))).add(layout.createParallelGroup(3).add(this.campoSubcuenta, -2, -1, -2).add(this.jLabel3).add(this.campoNombreSubcuenta, -2, -1, -2)).addPreferredGap(0).add(layout.createParallelGroup(3).add(this.campoConcepto, -2, -1, -2).add(this.jLabel4)).addPreferredGap(0).add(layout.createParallelGroup(3).add(this.campoRetencion, -2, -1, -2).add(this.jLabel11).add(this.campoCuentaRetencion, -2, -1, -2).add(this.jLabel10).add(this.jLabel9))).add(layout.createSequentialGroup().addContainerGap().add(this.jPanel1, -2, -1, -2).addPreferredGap(0).add(this.jPanel2, -2, -1, -2))).add(20, 20, 20).add(layout.createParallelGroup(2).add(layout.createParallelGroup(3).add(this.jLabel7).add(this.jLabel6)).add(this.jLabel5)).addPreferredGap(0).add(layout.createParallelGroup(2).add(layout.createParallelGroup(1).add(this.campoTerceros, -2, -1, -2).add(layout.createParallelGroup(3).add(this.comboTiposIva, -2, -1, -2).add(this.campoBase, -2, -1, -2))).add(this.botonAddTable)).addPreferredGap(0).add(this.jScrollPane1, -2, 139, -2).addPreferredGap(0).add(layout.createParallelGroup(3).add(this.jLabel8).add(this.campoSumaBases, -2, -1, -2).add(this.campoSumaIvas, -2, -1, -2).add(this.campoSumaREs, -2, -1, -2).add(this.campoSumaTotales, -2, -1, -2)).addPreferredGap(1).add(layout.createParallelGroup(3).add(this.botonGenerar).add(this.botonLimpiar).add(this.botonPagar).add(this.botonVencimientos)).addContainerGap(-1, 32767)));
/*      */ 
/* 1283 */     pack();
/*      */   }
/*      */ 
/*      */   private void botonAddTableActionPerformed(ActionEvent evt)
/*      */   {
/* 1288 */     double base = StringToDouble(this.campoBase.getText());
/* 1289 */     TipoIVA iva = (TipoIVA)this.comboTiposIva.getSelectedItem();
/* 1290 */     if ((iva.getIva() != 0.0D) && (this.esEmitida.isSelected()) && ((this.esAdqIntrac.isSelected()) || (this.esImportacion.isSelected()))) {
/* 1291 */       int pregunta = JOptionPane.showConfirmDialog(getContentPane(), "Para exportaciones y entregas intracomunitarias debería\nhaber seleccionado un tipo de IVA cero.\n¿Está seguro de que desea continuar?.", Mensajes.getString("confirma"), 0);
/*      */ 
/* 1297 */       if (pregunta == 1) {
/* 1298 */         return;
/*      */       }
/*      */     }
/* 1301 */     if (base != -1.0D) {
/* 1302 */       String cadenaCuenta = this.campoTerceros.getText();
/* 1303 */       if (!cadenaCuenta.equals("")) {
/* 1304 */         int subcuenta = Integer.parseInt(cadenaCuenta);
/* 1305 */         double importeIva = doubleTwoDecimals(Double.valueOf(base * iva.getIva() / 100.0D));
/* 1306 */         double importeRe = 0.0D;
/* 1307 */         if ((this.esEmitida.isSelected()) && (this.esRecargo.isSelected())) {
/* 1308 */           importeRe = doubleTwoDecimals(Double.valueOf(base * iva.getRe() / 100.0D));
/*      */         }
/* 1310 */         double importeTotal = base + importeIva + importeRe;
/* 1311 */         if (this.esAdqIntrac.isSelected()) {
/* 1312 */           importeTotal = base;
/*      */         }
/* 1314 */         this.sumaBases += base;
/* 1315 */         this.sumaIvas += importeIva;
/* 1316 */         this.sumaREs += importeRe;
/* 1317 */         this.sumaTotales += importeTotal;
/* 1318 */         Object[] newLine = new Object[6];
/* 1319 */         newLine[0] = Integer.valueOf(subcuenta);
/* 1320 */         newLine[1] = iva;
/* 1321 */         newLine[2] = Double.valueOf(base);
/* 1322 */         newLine[3] = Double.valueOf(importeIva);
/* 1323 */         newLine[4] = Double.valueOf(importeRe);
/* 1324 */         newLine[5] = Double.valueOf(importeTotal);
/* 1325 */         DefaultTableModel modelo = (DefaultTableModel)this.jTable1.getModel();
/* 1326 */         modelo.addRow(newLine);
/* 1327 */         colocarSumas();
/* 1328 */         this.campoTerceros.setText("");
/* 1329 */         this.campoBase.setText("");
/* 1330 */         this.campoTerceros.requestFocus();
/*      */       } else {
/* 1332 */         System.out.println("No se ha introducido subcuenta");
/*      */       }
/*      */     }
/*      */     else {
/* 1336 */       System.out.println("Número no válido");
/*      */     }
/*      */   }
/*      */ 
/*      */   private void esEmitidaActionPerformed(ActionEvent evt) {
/* 1341 */     limpiarForm();
/* 1342 */     cambiarEtiquetas(true);
/*      */   }
/*      */ 
/*      */   private void esRecibidaActionPerformed(ActionEvent evt) {
/* 1346 */     limpiarForm();
/* 1347 */     cambiarEtiquetas(false);
/*      */   }
/*      */ 
/*      */   private void botonLimpiarActionPerformed(ActionEvent evt) {
/* 1351 */     limpiarForm();
/*      */   }
/*      */ 
/*      */   private void botonGenerarActionPerformed(ActionEvent evt) {
/* 1355 */     generarFactura();
/*      */   }
/*      */ 
/*      */   private void botonPagarActionPerformed(ActionEvent evt) {
/* 1359 */     pagarCobrar();
/*      */   }
/*      */ 
/*      */   private void botonVencimientosActionPerformed(ActionEvent evt) {
/* 1363 */     crearVencimientos();
/*      */   }
/*      */ 
/*      */   private void jTableMouseClicked(MouseEvent evt) {
/* 1367 */     if (evt.getClickCount() == 2) {
/* 1368 */       int fila = this.jTable1.rowAtPoint(evt.getPoint());
/* 1369 */       if ((fila >= 0) && (fila < this.jTable1.getModel().getRowCount())) {
/* 1370 */         DefaultTableModel modelo = (DefaultTableModel)this.jTable1.getModel();
/* 1371 */         double base = ((Double)modelo.getValueAt(fila, 2)).doubleValue();
/* 1372 */         double iva = ((Double)modelo.getValueAt(fila, 3)).doubleValue();
/* 1373 */         double re = ((Double)modelo.getValueAt(fila, 4)).doubleValue();
/* 1374 */         double total = ((Double)modelo.getValueAt(fila, 5)).doubleValue();
/* 1375 */         this.sumaBases -= base;
/* 1376 */         this.sumaIvas -= iva;
/* 1377 */         this.sumaREs -= re;
/* 1378 */         this.sumaTotales -= total;
/* 1379 */         colocarSumas();
/* 1380 */         modelo.removeRow(fila);
/*      */       } else {
/* 1382 */         System.out.println("Se ha producido un error. No se puede realizar la operacion.");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private void formFocusGained(FocusEvent evt)
/*      */   {
/*      */   }
/*      */ 
/*      */   private void esImportacionActionPerformed(ActionEvent evt) {
/* 1392 */     if (this.esImportacion.isSelected())
/* 1393 */       this.esAdqIntrac.setSelected(false);
/*      */   }
/*      */ 
/*      */   private void esAdqIntracActionPerformed(ActionEvent evt)
/*      */   {
/* 1398 */     if (this.esAdqIntrac.isSelected())
/* 1399 */       this.esImportacion.setSelected(false);
/*      */   }
/*      */ 
/*      */   private void campoSubcuentaFocusLost(FocusEvent evt)
/*      */   {
/* 1404 */     Integer origen = this.campoSubcuenta.getOrigen();
/* 1405 */     if (origen != null)
/* 1406 */       if (origen.intValue() == 1) {
/* 1407 */         this.esImportacion.setSelected(false);
/* 1408 */         this.esAdqIntrac.setSelected(true);
/*      */       }
/* 1410 */       else if (origen.intValue() == 2) {
/* 1411 */         this.esImportacion.setSelected(true);
/* 1412 */         this.esAdqIntrac.setSelected(false);
/*      */       }
/*      */       else {
/* 1415 */         this.esImportacion.setSelected(false);
/* 1416 */         this.esAdqIntrac.setSelected(false);
/*      */       }
/*      */   }
/*      */ 
/*      */   private class ControlFoco
/*      */     implements FocusListener
/*      */   {
/*      */     private ControlFoco()
/*      */     {
/*      */     }
/*      */ 
/*      */     private void comprobarCuentaRetencion(CampoCuenta campo)
/*      */     {
/* 1575 */       int largo = 4;
/* 1576 */       String comprobacion = "4751";
/* 1577 */       if (CreacionFacturas.this.esEmitida.isSelected()) {
/* 1578 */         largo = 3;
/* 1579 */         comprobacion = "473";
/*      */       }
/* 1581 */       String cuenta = campo.getText();
/* 1582 */       if ((cuenta.length() > 4) && (!cuenta.substring(0, largo).equals(comprobacion))) {
/* 1583 */         JOptionPane.showMessageDialog(CreacionFacturas.this.getContentPane(), Mensajes.getString("laCta") + " " + cuenta + "\n" + Mensajes.getString("noEsDe") + " retenciones");
/*      */ 
/* 1585 */         campo.setText("");
/* 1586 */         campo.requestFocus();
/*      */       }
/*      */     }
/*      */ 
/*      */     private void comprobarCuenta(CampoCuenta campo)
/*      */     {
/* 1599 */       if (!esDeIngGasto(campo.getText())) {
/* 1600 */         String cad = CreacionFacturas.this.esEmitida.isSelected() ? Mensajes.getString("ingresos") : Mensajes.getString("gastos");
/* 1601 */         JOptionPane.showMessageDialog(CreacionFacturas.this.getContentPane(), Mensajes.getString("laCta") + " " + campo.getText() + "\n" + Mensajes.getString("noEsDe") + " " + cad);
/*      */ 
/* 1603 */         campo.setText("");
/* 1604 */         campo.requestFocus();
/*      */       }
/*      */     }
/*      */ 
/*      */     private boolean esDeIngGasto(String cuenta)
/*      */     {
/* 1615 */       if (cuenta.length() > 0) {
/* 1616 */         if ((CreacionFacturas.this.esEmitida.isSelected()) && (cuenta.substring(0, 1).equals("7")))
/* 1617 */           return true;
/* 1618 */         if ((CreacionFacturas.this.esRecibida.isSelected()) && ((cuenta.substring(0, 1).equals("6")) || (cuenta.substring(0, 1).equals("2")) || (cuenta.substring(0, 3).equals("174")) || (cuenta.substring(0, 3).equals("524"))))
/*      */         {
/* 1621 */           return true;
/*      */         }
/* 1623 */         return false;
/*      */       }
/*      */ 
/* 1626 */       return true;
/*      */     }
/*      */ 
/*      */     public void focusGained(FocusEvent arg0)
/*      */     {
/*      */     }
/*      */ 
/*      */     public void focusLost(FocusEvent e)
/*      */     {
/* 1635 */       Object fuente = e.getSource();
/* 1636 */       if (fuente == CreacionFacturas.this.campoSubcuenta) {
/* 1637 */         CreacionFacturas.this.campoNombreSubcuenta.setText(CreacionFacturas.this.campoSubcuenta.getNombre());
/*      */       }
/* 1639 */       else if (fuente == CreacionFacturas.this.campoTerceros) {
/* 1640 */         comprobarCuenta(CreacionFacturas.this.campoTerceros);
/*      */       }
/* 1642 */       else if (fuente == CreacionFacturas.this.campoCuentaRetencion)
/* 1643 */         comprobarCuentaRetencion(CreacionFacturas.this.campoCuentaRetencion);
/*      */     }
/*      */   }
/*      */ 
/*      */   private class TeclaPulsada extends KeyAdapter
/*      */   {
/*      */     private TeclaPulsada()
/*      */     {
/*      */     }
/*      */ 
/*      */     public void keyPressed(KeyEvent e)
/*      */     {
/* 1468 */       int tecla = e.getKeyCode();
/* 1469 */       Object campo = e.getSource();
/* 1470 */       if (e.isAltDown()) {
/* 1471 */         if (tecla == 67) {
/* 1472 */           if (campo == CreacionFacturas.this.campoSubcuenta) {
/* 1473 */             if (CreacionFacturas.this.campoSubcuenta.getText().equals("")) {
/* 1474 */               CreacionFacturas.this.quien_llamo = 0;
/* 1475 */               CreacionFacturas.this.activar_seleccion_cuenta(2);
/*      */             }
/* 1477 */           } else if (campo == CreacionFacturas.this.campoConcepto) {
/* 1478 */             MostrarConceptos dlg2 = new MostrarConceptos(Inicio.frame, Mensajes.getString("conceptos"), true);
/* 1479 */             Dimension dlgSize = dlg2.getPreferredSize();
/* 1480 */             Dimension frmSize = CreacionFacturas.this.getContentPane().getSize();
/* 1481 */             Point loc = CreacionFacturas.this.getLocation();
/* 1482 */             dlg2.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
/*      */ 
/* 1484 */             dlg2.setModal(true);
/* 1485 */             dlg2.setVisible(true);
/* 1486 */             if (!dlg2.Seleccion().equals(""))
/* 1487 */               CreacionFacturas.this.campoConcepto.setText(dlg2.Seleccion());
/*      */           }
/* 1489 */           else if (campo == CreacionFacturas.this.campoTerceros) {
/* 1490 */             if (CreacionFacturas.this.campoTerceros.getText().equals("")) {
/* 1491 */               CreacionFacturas.this.quien_llamo = 1;
/* 1492 */               CreacionFacturas.this.activar_seleccion_cuenta(2);
/*      */             }
/* 1494 */           } else if (campo == CreacionFacturas.this.campoBase) {
/* 1495 */             Inicio.calculadora.colocaOrigen((JTextField)campo);
/* 1496 */             Inicio.calculadora.setVisible(true);
/* 1497 */           } else if (campo == CreacionFacturas.this.campoCuentaRetencion) {
/* 1498 */             if (CreacionFacturas.this.campoCuentaRetencion.getText().equals("")) {
/* 1499 */               CreacionFacturas.this.quien_llamo = 2;
/* 1500 */               CreacionFacturas.this.activar_seleccion_cuenta(2);
/*      */             }
/* 1502 */           } else if (campo == CreacionFacturas.this.campoRetencion) {
/* 1503 */             Inicio.calculadora.colocaOrigen((JTextField)campo);
/* 1504 */             Inicio.calculadora.setVisible(true);
/*      */           }
/*      */         }
/* 1507 */         if (tecla == 80) {
/* 1508 */           if (campo == CreacionFacturas.this.campoBase)
/* 1509 */             CreacionFacturas.this.campoBase.setText(Inicio.calculadora.getResultado());
/* 1510 */           else if (campo == CreacionFacturas.this.campoRetencion) {
/* 1511 */             CreacionFacturas.this.campoRetencion.setText(Inicio.calculadora.getResultado());
/*      */           }
/*      */         }
/* 1514 */         if ((tecla == 78) && 
/* 1515 */           (campo == CreacionFacturas.this.campoConcepto)) {
/* 1516 */           String temp = CreacionFacturas.this.campoConcepto.getText();
/* 1517 */           temp = temp + CreacionFacturas.this.campoNumero.getText();
/* 1518 */           CreacionFacturas.this.campoConcepto.setText(temp);
/*      */         }
/*      */       }
/*      */ 
/* 1522 */       if (tecla == 10) {
/* 1523 */         if (campo == CreacionFacturas.this.campoNumero) {
/* 1524 */           String numero = CreacionFacturas.this.campoNumero.getText();
/* 1525 */           if ((numero != null) && (!numero.equals(""))) {
/* 1526 */             int existe = CreacionFacturas.this.facturaM.existeFactura(CreacionFacturas.this.esEmitida.isSelected(), numero);
/* 1527 */             if (existe != -1) {
/* 1528 */               numero = "R" + numero;
/* 1529 */               CreacionFacturas.this.cargarFactura(numero);
/*      */             }
/*      */           }
/* 1532 */           CreacionFacturas.this.iCalendarTextField1.requestFocus();
/* 1533 */         } else if (campo == CreacionFacturas.this.campoSubcuenta) {
/* 1534 */           CreacionFacturas.this.campoConcepto.requestFocus();
/* 1535 */         } else if (campo == CreacionFacturas.this.campoConcepto) {
/* 1536 */           if (CreacionFacturas.this.campoCuentaRetencion.isEnabled())
/* 1537 */             CreacionFacturas.this.campoCuentaRetencion.requestFocus();
/*      */           else
/* 1539 */             CreacionFacturas.this.campoTerceros.requestFocus();
/*      */         }
/* 1541 */         else if (campo == CreacionFacturas.this.campoTerceros) {
/* 1542 */           CreacionFacturas.this.comboTiposIva.requestFocus();
/* 1543 */         } else if (campo == CreacionFacturas.this.comboTiposIva) {
/* 1544 */           CreacionFacturas.this.campoBase.requestFocus();
/* 1545 */         } else if (campo == CreacionFacturas.this.campoBase) {
/* 1546 */           CreacionFacturas.this.botonAddTable.requestFocus();
/* 1547 */         } else if (campo == CreacionFacturas.this.campoCuentaRetencion) {
/* 1548 */           CreacionFacturas.this.campoRetencion.requestFocus();
/* 1549 */         } else if (campo == CreacionFacturas.this.campoRetencion) {
/* 1550 */           CreacionFacturas.this.campoTerceros.requestFocus();
/*      */         }
/*      */       }
/* 1553 */       else if ((tecla == 9) && (campo == CreacionFacturas.this.campoNumero)) {
/* 1554 */         String numero = CreacionFacturas.this.campoNumero.getText();
/* 1555 */         if ((numero != null) && (!numero.equals(""))) {
/* 1556 */           int existe = CreacionFacturas.this.facturaM.existeFactura(CreacionFacturas.this.esEmitida.isSelected(), numero);
/* 1557 */           if (existe != -1) {
/* 1558 */             numero = "R" + numero;
/* 1559 */             CreacionFacturas.this.cargarFactura(numero);
/*      */           }
/*      */         }
/* 1562 */         CreacionFacturas.this.iCalendarTextField1.requestFocus();
/*      */       }
/*      */     }
/*      */   }
/*      */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.CreacionFacturas
 * JD-Core Version:    0.6.2
 */