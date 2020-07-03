/*     */ package facturacion.view;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.MarcoInicio;
/*     */ import contaes.Puente;
/*     */ import contaes.manejoDatos.ManejoApuntes;
/*     */ import contaes.manejoDatos.ManejoAsientos;
/*     */ import contaes.manejoDatos.ManejoFacturas;
/*     */ import contaes.manejoDatos.ManejoSubcuentas;
/*     */ import contaes.manejoDatos.ManejoVencimientos;
/*     */ import contaes.manejoDatos.TipoFactura;
/*     */ import contaes.manejoDatos.TipoFormaPago;
/*     */ import contaes.manejoDatos.TipoIVA;
/*     */ import contaes.manejoDatos.TipoSubcuenta;
/*     */ import contaes.manejoDatos.TipoVencimiento;
/*     */ import facturacion.control.AlmacenControl;
/*     */ import facturacion.model.Factura;
/*     */ import facturacion.model.LineaFactura;
/*     */ import facturacion.model.Producto;
/*     */ import java.awt.Container;
/*     */ import java.awt.Frame;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
import java.util.List;
/*     */ import java.util.ResourceBundle;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.DefaultComboBoxModel;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.event.ChangeEvent;
/*     */ import javax.swing.event.ChangeListener;
/*     */ import org.jdesktop.layout.GroupLayout;
/*     */ import org.jdesktop.layout.GroupLayout.ParallelGroup;
/*     */ import org.jdesktop.layout.GroupLayout.SequentialGroup;
/*     */ 
/*     */ public class ContabilizarFacturaPorProductosJDialog extends JDialog
/*     */ {
/*     */   private Factura factura;
/*     */   private ArrayList<LineaFactura> lineas;
/*  44 */   private DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
/*  45 */   private DefaultComboBoxModel modeloComboCobro = new DefaultComboBoxModel();
/*     */ 
/*  47 */   boolean contabilizada = false;
/*     */   private boolean emitida;
/*     */   private JButton botonContabilizar;
/*     */   private JTextField campoNumero;
/*     */   private JComboBox comboSubcuentaCobro;
/*     */   private JComboBox comboSubcuentas;
/*     */   private JCheckBox crearVencimientos;
/*     */   private JButton jButton1;
/*     */   private JLabel jLabel1;
/*     */   private JLabel jLabel2;
/*     */   private JLabel jLabel3;
/*     */   private JPanel jPanel1;
/*     */   private JCheckBox pasarAAlmacen;
/*     */   private JCheckBox pasarAEfectos;
/*     */   private JCheckBox soloMarcarContabilizada;
/*     */ 
/*     */   public ContabilizarFacturaPorProductosJDialog(Frame parent, boolean modal, Factura factura, ArrayList<LineaFactura> lineas, boolean emitida)
/*     */   {
/*  52 */     super(parent, modal);
/*  53 */     this.factura = factura;
/*  54 */     this.lineas = lineas;
/*  55 */     this.emitida = emitida;
/*  56 */     initComponents();
/*     */ 
/*  58 */     llenarCombo();
/*  59 */     this.campoNumero.setText(factura.getNumero());
/*     */   }
/*     */ 
/*     */   private void llenarCombo() {
/*     */     try {
/*  64 */       ManejoSubcuentas mS = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  65 */       ArrayList subcuentas = mS.listaSubcuentas(47300000, 47399999);
/*  66 */       subcuentas.addAll(mS.listaSubcuentas(47510000, 47519999));
/*  67 */       for (TipoSubcuenta subc :(List<TipoSubcuenta>)  subcuentas) {
/*  68 */         this.modeloCombo.addElement(subc);
/*     */       }
/*  70 */       this.comboSubcuentas.setModel(this.modeloCombo);
/*     */ 
/*  72 */       ArrayList subcuentasCobro = mS.listaSubcuentas(57000000, 57999999);
/*  73 */       for (TipoSubcuenta subcc :(List<TipoSubcuenta>)  subcuentasCobro) {
/*  74 */         this.modeloComboCobro.addElement(subcc);
/*     */       }
/*  76 */       this.comboSubcuentaCobro.setModel(this.modeloComboCobro);
/*     */     } catch (Exception ex) {
/*  78 */       Logger.getLogger(FacturasJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void contabilizar() {
/*  83 */     String nuevoNumero = this.campoNumero.getText();
/*  84 */     if (!nuevoNumero.equals("")) {
/*  85 */       this.factura.setNumero(nuevoNumero);
/*     */     }
/*  87 */     if (this.soloMarcarContabilizada.isSelected()) {
/*  88 */       setContabilizada(true);
/*     */     }
/*     */     else {
/*  91 */       ManejoAsientos mA = new ManejoAsientos(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  92 */       ManejoApuntes mAp = new ManejoApuntes(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  93 */       ManejoFacturas mF = new ManejoFacturas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  94 */       AlmacenControl aC = new AlmacenControl();
/*     */ 
/*  96 */       int existe = mF.existeFactura(this.emitida, this.factura.getNumero());
/*  97 */       if (existe == -1) {
/*  98 */         String marca = this.emitida ? "E" : "R";
/*  99 */         double total = 0.0D;
/* 100 */         int numAsto = mA.nuevoNumero();
/* 101 */         SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
/* 102 */         String fechaBD = sdf.format(this.factura.getFecha());
/* 103 */         if (numAsto != -1) {
/* 104 */           double ivaTotal = 0.0D;
/* 105 */           double reTotal = 0.0D;
/* 106 */           int idAsto = mA.crear(numAsto, fechaBD, this.factura.getNumero(), marca);
/* 107 */           for (LineaFactura linea : this.lineas) {
/* 108 */             String DH = this.factura.getBase().doubleValue() >= 0.0D ? "H" : "D";
/* 109 */             if (!this.emitida) {
/* 110 */               DH = this.factura.getBase().doubleValue() >= 0.0D ? "D" : "H";
/*     */             }
/* 112 */             double base = linea.getBase().doubleValue();
/* 113 */             double iva = base * linea.getTipoIva().getIva() / 100.0D;
/* 114 */             double re = base * linea.getTipoIva().getRe() / 100.0D;
/* 115 */             mAp.crear(idAsto, linea.getIdProducto().getSubcuenta().getCodigo(), linea.getConcepto(), DH, base >= 0.0D ? base : -1.0D * base);
/* 116 */             total += base;
/* 117 */             if (iva != 0.0D) {
/* 118 */               int cuenta = this.emitida ? linea.getTipoIva().getCuentaRep() : linea.getTipoIva().getCuentaSop();
/* 119 */               mAp.crear(idAsto, cuenta, linea.getConcepto(), DH, iva >= 0.0D ? iva : -1.0D * iva);
/* 120 */               total += iva;
/* 121 */               ivaTotal += iva;
/*     */             }
/* 123 */             if ((this.emitida) && (re != 0.0D)) {
/* 124 */               mAp.crear(idAsto, linea.getTipoIva().getCuentaRE(), linea.getConcepto(), DH, re >= 0.0D ? re : -1.0D * re);
/* 125 */               total += re;
/* 126 */               reTotal += re;
/*     */             }
/* 128 */             if (this.pasarAAlmacen.isSelected()) {
/* 129 */               double importeUnidad = base / linea.getUnidades().doubleValue();
/* 130 */               if (this.emitida) {
/* 131 */                 if (base >= 0.0D) {
/* 132 */                   aC.insertVenta(linea.getIdProducto().getReferencia(), this.factura.getFecha(), importeUnidad, linea.getUnidades().intValue(), 1);
/*     */                 }
/*     */                 else {
/* 135 */                   Double coste = aC.getCosteProducto(linea.getIdProducto().getReferencia());
/* 136 */                   if (coste == null) {
/* 137 */                     coste = Double.valueOf(0.0D);
/*     */                   }
/* 139 */                   aC.insertCompra(linea.getIdProducto().getReferencia(), this.factura.getFecha(), coste.doubleValue(), linea.getUnidades().intValue(), 1);
/*     */                 }
/*     */ 
/*     */               }
/* 143 */               else if (base >= 0.0D) {
/* 144 */                 aC.insertCompra(linea.getIdProducto().getReferencia(), this.factura.getFecha(), importeUnidad, linea.getUnidades().intValue(), 1);
/*     */               }
/*     */               else {
/* 147 */                 aC.insertVenta(linea.getIdProducto().getReferencia(), this.factura.getFecha(), importeUnidad, linea.getUnidades().intValue(), 1);
/*     */               }
/*     */             }
/*     */           }
/*     */ 
/* 152 */           if (this.factura.getRetencion().doubleValue() != 0.0D) {
/* 153 */             TipoSubcuenta subRetencion = (TipoSubcuenta)this.comboSubcuentas.getSelectedItem();
/* 154 */             if (subRetencion != null) {
/* 155 */               mAp.crear(idAsto, subRetencion.getCodigo(), "Retención. Factura " + this.factura.getNumero(), this.emitida ? "D" : "H", this.factura.getRetencion().doubleValue());
/* 156 */               if (total >= 0.0D) {
/* 157 */                 total -= this.factura.getRetencion().doubleValue();
/*     */               }
/*     */               else {
/* 160 */                 total += this.factura.getRetencion().doubleValue();
/*     */               }
/*     */             }
/*     */           }
/* 164 */           String DH = this.factura.getBase().doubleValue() >= 0.0D ? "D" : "H";
/* 165 */           if (!this.emitida) {
/* 166 */             DH = this.factura.getBase().doubleValue() >= 0.0D ? "H" : "D";
/*     */           }
/* 168 */           mAp.crear(idAsto, this.factura.getCliente().getCodigo(), "Factura nº " + this.factura.getNumero(), DH, total >= 0.0D ? total : -1.0D * total);
/*     */ 
/* 170 */           TipoFactura facturaContable = new TipoFactura(this.factura.getNumero(), fechaBD, this.factura.getCliente().getCodigo(), idAsto, "Factura " + this.factura.getNumero(), this.factura.getBase().doubleValue(), ivaTotal, reTotal, this.factura.getBase().doubleValue() + ivaTotal + reTotal);
/* 171 */           mF.crear(this.emitida, facturaContable);
/* 172 */           Inicio.frame.renovarTabla(0);
/* 173 */           if (this.emitida)
/* 174 */             Inicio.frame.renovarTabla(5);
/*     */           else
/* 176 */             Inicio.frame.renovarTabla(6);
/* 177 */           setContabilizada(true);
/* 178 */           if (this.crearVencimientos.isSelected())
/* 179 */             generarVencimientos(facturaContable);
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/* 184 */         JOptionPane.showMessageDialog(getContentPane(), "Ya existe una factura con este número", "Error", 0);
/*     */       }
/* 186 */       aC.cerrarConexion();
/* 187 */       mF.cerrarRs();
/* 188 */       mA.cerrarRs();
/* 189 */       mAp.cerrarRs();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void generarVencimientos(TipoFactura facturaContable) {
/* 194 */     Double totalACobrar = Double.valueOf(this.factura.getTotal().doubleValue() - this.factura.getRetencion().doubleValue());
/* 195 */     if (this.pasarAEfectos.isSelected()) {
/* 196 */       int subcuentaEfectos = cuentaEfectos(facturaContable.getCuenta());
/* 197 */       if (subcuentaEfectos != -1)
/*     */       {
/* 199 */         ManejoAsientos mA = new ManejoAsientos(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 200 */         ManejoApuntes mAp = new ManejoApuntes(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 201 */         int idAsto = -1;
/* 202 */         int num_asto = mA.nuevoNumero();
/* 203 */         idAsto = mA.crear(num_asto, facturaContable.getFecha(), "", "");
/*     */ 
/* 205 */         if (this.emitida) {
/* 206 */           mAp.crear(idAsto, subcuentaEfectos, "Traspaso a efectos comerciales", "D", totalACobrar.doubleValue());
/* 207 */           mAp.crear(idAsto, facturaContable.getCuenta(), "Traspaso a efectos comerciales", "H", totalACobrar.doubleValue());
/*     */         }
/*     */         else {
/* 210 */           mAp.crear(idAsto, subcuentaEfectos, "Traspaso a efectos comerciales", "H", totalACobrar.doubleValue());
/* 211 */           mAp.crear(idAsto, facturaContable.getCuenta(), "Traspaso a efectos comerciales", "D", totalACobrar.doubleValue());
/*     */         }
/*     */       }
/*     */       else {
/* 215 */         JOptionPane.showMessageDialog(getContentPane(), "NO existe cuenta de Efectos comerciales\npara este Cliente/Deudor\nNo se hace traspaso a efectos comerciales");
/*     */       }
/*     */     }
/*     */ 
/* 219 */     TipoFormaPago formaPago = this.factura.getFormaPago();
/* 220 */     TipoSubcuenta subcuentaPago = (TipoSubcuenta)this.comboSubcuentaCobro.getSelectedItem();
/* 221 */     int year = Integer.parseInt(facturaContable.getFecha().substring(0, 4));
/* 222 */     int mes = Integer.parseInt(facturaContable.getFecha().substring(4, 6));
/* 223 */     mes--;
/* 224 */     int dia = Integer.parseInt(facturaContable.getFecha().substring(6));
/* 225 */     Calendar calendar = Calendar.getInstance();
/* 226 */     Calendar calendarFechaMes = Calendar.getInstance();
/* 227 */     calendar.set(year, mes, dia);
/* 228 */     ArrayList vencimientos = new ArrayList();
/* 229 */     double importeVencimiento = totalACobrar.doubleValue() / formaPago.getNumeroPagos().intValue();
/*     */ 
/* 231 */     for (int i = 0; i < formaPago.getNumeroPagos().intValue(); i++)
/* 232 */       if ((formaPago.getDiaFijoPago() == null) || (formaPago.getDiaFijoPago().intValue() == 0)) {
/* 233 */         if (i == 0) {
/* 234 */           calendar.add(5, formaPago.getDiasPrimerPago().intValue());
/*     */         }
/* 237 */         else if (formaPago.getDiasEntrePagos().intValue() == 30) {
/* 238 */           calendar.add(2, 1);
/*     */         }
/*     */         else {
/* 241 */           calendar.add(5, formaPago.getDiasEntrePagos().intValue());
/*     */         }
/*     */ 
/* 244 */         String num = Integer.toString(i + 1) + "/" + Integer.toString(formaPago.getNumeroPagos().intValue());
/* 245 */         TipoVencimiento vencimiento = new TipoVencimiento();
/* 246 */         vencimiento.setFecha(dateToString(calendar.getTime()));
/* 247 */         vencimiento.setEjercicio(year);
/* 248 */         vencimiento.setFactura(facturaContable.getNumero());
/* 249 */         vencimiento.setFechaf(facturaContable.getFecha());
/* 250 */         vencimiento.setCuenta(facturaContable.getCuenta());
/* 251 */         vencimiento.setImporte(importeVencimiento);
/* 252 */         vencimiento.setNum(num);
/* 253 */         vencimiento.setPagado(false);
/* 254 */         vencimiento.setCuentap(subcuentaPago.getCodigo());
/* 255 */         vencimientos.add(vencimiento);
/*     */       }
/*     */       else {
/* 258 */         if (i == 0) {
/* 259 */           calendar.add(5, formaPago.getDiasPrimerPago().intValue());
/* 260 */           calendarFechaMes.setTime(calendar.getTime());
/* 261 */           calendarFechaMes.set(5, formaPago.getDiaFijoPago().intValue());
/* 262 */           if (calendar.getTimeInMillis() > calendarFechaMes.getTimeInMillis()) {
/* 263 */             calendarFechaMes.add(2, 1);
/*     */           }
/* 265 */           calendar.setTime(calendarFechaMes.getTime());
/*     */         }
/*     */         else {
/* 268 */           calendar.add(5, formaPago.getDiasEntrePagos().intValue());
/* 269 */           calendarFechaMes.setTime(calendar.getTime());
/* 270 */           calendarFechaMes.set(5, formaPago.getDiaFijoPago().intValue());
/* 271 */           if (calendar.getTimeInMillis() > calendarFechaMes.getTimeInMillis()) {
/* 272 */             calendarFechaMes.add(2, 1);
/*     */           }
/*     */         }
/*     */ 
/* 276 */         if ((calendarFechaMes.get(5) < formaPago.getDiaFijoPago().intValue()) && (calendarFechaMes.get(2) == 2))
/*     */         {
/* 278 */           calendarFechaMes.set(2, 1);
/* 279 */           calendarFechaMes.set(5, 28);
/* 280 */           if (i == 0) {
/* 281 */             calendar.setTime(calendarFechaMes.getTime());
/*     */           }
/*     */         }
/* 284 */         String num = Integer.toString(i + 1) + "/" + Integer.toString(formaPago.getNumeroPagos().intValue());
/* 285 */         TipoVencimiento vencimiento = new TipoVencimiento();
/* 286 */         vencimiento.setFecha(dateToString(calendarFechaMes.getTime()));
/* 287 */         vencimiento.setEjercicio(year);
/* 288 */         vencimiento.setFactura(facturaContable.getNumero());
/* 289 */         vencimiento.setFechaf(facturaContable.getFecha());
/* 290 */         vencimiento.setCuenta(facturaContable.getCuenta());
/* 291 */         vencimiento.setImporte(importeVencimiento);
/* 292 */         vencimiento.setNum(num);
/* 293 */         vencimiento.setPagado(false);
/* 294 */         vencimiento.setCuentap(subcuentaPago.getCodigo());
/* 295 */         vencimientos.add(vencimiento);
/*     */       }
/*     */     ManejoVencimientos mV;
/* 299 */     if (vencimientos.size() > 0) {
/* 300 */       mV = new ManejoVencimientos(Inicio.getCEmpresa());
/* 301 */       for (TipoVencimiento tipoVencimiento :(List<TipoVencimiento>)  vencimientos) {
/* 302 */         mV.crear(this.emitida, tipoVencimiento);
/*     */       }
/*     */     }
/* 305 */     if (this.emitida) {
/* 306 */       Inicio.frame.renovarTabla(2);
/*     */     }
/*     */     else
/* 309 */       Inicio.frame.renovarTabla(1);
/*     */   }
/*     */ 
/*     */   private int cuentaEfectos(int cuenta)
/*     */   {
/* 314 */     String cadenaSubcuenta = Integer.toString(cuenta);
/* 315 */     String cadenaGrupo = cadenaSubcuenta.substring(0, 2);
/* 316 */     String cadenaFin = cadenaSubcuenta.substring(3);
/* 317 */     if (cadenaGrupo.equals("40"))
/* 318 */       cadenaSubcuenta = "401" + cadenaFin;
/* 319 */     else if (cadenaGrupo.equals("41"))
/* 320 */       cadenaSubcuenta = "411" + cadenaFin;
/* 321 */     else if (cadenaGrupo.equals("43"))
/* 322 */       cadenaSubcuenta = "431" + cadenaFin;
/* 323 */     else if (cadenaGrupo.equals("44")) {
/* 324 */       cadenaSubcuenta = "441" + cadenaFin;
/*     */     }
/* 326 */     int cuentaEfectos = Integer.parseInt(cadenaSubcuenta);
/* 327 */     ManejoSubcuentas mS = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 328 */     if (mS.existeSubcuenta(cuentaEfectos)) {
/* 329 */       return cuentaEfectos;
/*     */     }
/* 331 */     return -1;
/*     */   }
/*     */ 
/*     */   private String dateToString(Date date) {
/* 335 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
/* 336 */     return sdf.format(date);
/*     */   }
/*     */ 
/*     */   public boolean isContabilizada() {
/* 340 */     return this.contabilizada;
/*     */   }
/*     */ 
/*     */   private void setContabilizada(boolean contabilizada) {
/* 344 */     this.contabilizada = contabilizada;
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/* 356 */     this.jPanel1 = new JPanel();
/* 357 */     this.jLabel3 = new JLabel();
/* 358 */     this.pasarAEfectos = new JCheckBox();
/* 359 */     this.jLabel2 = new JLabel();
/* 360 */     this.comboSubcuentaCobro = new JComboBox();
/* 361 */     this.jLabel1 = new JLabel();
/* 362 */     this.campoNumero = new JTextField();
/* 363 */     this.comboSubcuentas = new JComboBox();
/* 364 */     this.crearVencimientos = new JCheckBox();
/* 365 */     this.botonContabilizar = new JButton();
/* 366 */     this.jButton1 = new JButton();
/* 367 */     this.soloMarcarContabilizada = new JCheckBox();
/* 368 */     this.pasarAAlmacen = new JCheckBox();
/*     */ 
/* 370 */     setDefaultCloseOperation(2);
/* 371 */     setTitle("Contabilizar factura");
/*     */ 
/* 373 */     this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/*     */ 
/* 375 */     ResourceBundle bundle = ResourceBundle.getBundle("internationalization/Mensajes");
/* 376 */     this.jLabel3.setText(bundle.getString("numerofactura"));
/*     */ 
/* 378 */     this.pasarAEfectos.setText("Pasar a efectos comerciales");
/* 379 */     this.pasarAEfectos.setEnabled(false);
/*     */ 
/* 381 */     this.jLabel2.setText("Cuenta de cobro");
/*     */ 
/* 383 */     this.jLabel1.setText("Cuenta de retenciones");
/*     */ 
/* 385 */     this.crearVencimientos.setText("Crear vencimientos");
/* 386 */     this.crearVencimientos.addChangeListener(new ChangeListener() {
/*     */       public void stateChanged(ChangeEvent evt) {
/* 388 */         ContabilizarFacturaPorProductosJDialog.this.crearVencimientosStateChanged(evt);
/*     */       }
/*     */     });
/* 392 */     this.botonContabilizar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/D16.png")));
/* 393 */     this.botonContabilizar.setText("Contabilizar");
/* 394 */     this.botonContabilizar.setHorizontalTextPosition(2);
/* 395 */     this.botonContabilizar.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 397 */         ContabilizarFacturaPorProductosJDialog.this.botonContabilizarActionPerformed(evt);
/*     */       }
/*     */     });
/* 401 */     this.jButton1.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/cancel.png")));
/* 402 */     this.jButton1.setText("Cancelar");
/* 403 */     this.jButton1.setHorizontalTextPosition(2);
/* 404 */     this.jButton1.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 406 */         ContabilizarFacturaPorProductosJDialog.this.jButton1ActionPerformed(evt);
/*     */       }
/*     */     });
/* 410 */     this.soloMarcarContabilizada.setText("Sólo marcar como Contabilizada");
/* 411 */     this.soloMarcarContabilizada.setToolTipText("Sólo marca la factura como contabilizada. No introduce nada en la contabilidad");
/*     */ 
/* 413 */     this.pasarAAlmacen.setText(bundle.getString("updatealmacen"));
/*     */ 
/* 415 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 416 */     this.jPanel1.setLayout(jPanel1Layout);
/* 417 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().add(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(jPanel1Layout.createParallelGroup(1).add(this.jLabel3).add(jPanel1Layout.createSequentialGroup().add(27, 27, 27).add(jPanel1Layout.createParallelGroup(1).add(this.pasarAEfectos).add(jPanel1Layout.createSequentialGroup().addPreferredGap(0).add(this.jLabel2).addPreferredGap(1).add(this.comboSubcuentaCobro, 0, 222, 32767)))).add(this.crearVencimientos).add(jPanel1Layout.createSequentialGroup().add(this.botonContabilizar).add(49, 49, 49).add(this.jButton1)).add(jPanel1Layout.createSequentialGroup().add(this.jLabel1).addPreferredGap(0).add(jPanel1Layout.createParallelGroup(1).add(this.campoNumero, -2, 182, -2).add(this.comboSubcuentas, -2, 213, -2)))).add(34, 34, 34)).add(jPanel1Layout.createSequentialGroup().add(29, 29, 29).add(this.soloMarcarContabilizada)).add(this.pasarAAlmacen)).addContainerGap()));
/*     */ 
/* 452 */     jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(this.soloMarcarContabilizada, -2, 33, -2).addPreferredGap(0).add(jPanel1Layout.createParallelGroup(3).add(this.jLabel3).add(this.campoNumero, -2, -1, -2)).add(19, 19, 19).add(jPanel1Layout.createParallelGroup(3).add(this.jLabel1).add(this.comboSubcuentas, -2, -1, -2)).addPreferredGap(1).add(this.crearVencimientos).addPreferredGap(0).add(jPanel1Layout.createParallelGroup(3).add(this.jLabel2).add(this.comboSubcuentaCobro, -2, -1, -2)).addPreferredGap(0).add(this.pasarAEfectos).add(18, 18, 18).add(this.pasarAAlmacen).add(7, 7, 7).add(jPanel1Layout.createParallelGroup(3).add(this.botonContabilizar).add(this.jButton1)).addContainerGap(-1, 32767)));
/*     */ 
/* 482 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 483 */     getContentPane().setLayout(layout);
/* 484 */     layout.setHorizontalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(this.jPanel1, -1, -1, 32767).addContainerGap()));
/*     */ 
/* 491 */     layout.setVerticalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(this.jPanel1, -1, -1, 32767).addContainerGap()));
/*     */ 
/* 499 */     pack();
/*     */   }
/*     */ 
/*     */   private void crearVencimientosStateChanged(ChangeEvent evt) {
/* 503 */     if (this.crearVencimientos.isSelected()) {
/* 504 */       this.pasarAEfectos.setEnabled(true);
/*     */     }
/*     */     else
/* 507 */       this.pasarAEfectos.setEnabled(false);
/*     */   }
/*     */ 
/*     */   private void jButton1ActionPerformed(ActionEvent evt)
/*     */   {
/* 512 */     dispose();
/*     */   }
/*     */ 
/*     */   private void botonContabilizarActionPerformed(ActionEvent evt) {
/* 516 */     contabilizar();
/* 517 */     dispose();
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     facturacion.view.ContabilizarFacturaPorProductosJDialog
 * JD-Core Version:    0.6.2
 */