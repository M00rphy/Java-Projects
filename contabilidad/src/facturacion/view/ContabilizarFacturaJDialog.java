/*     */ package facturacion.view;
/*     */ 
/*     */ import almacen2.data.AlmacenInterno;
/*     */ import contaes.Inicio;
/*     */ import contaes.MarcoInicio;
/*     */ import contaes.Puente;
/*     */ import contaes.calendario.ICalendarTextField;
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
/*     */ import internationalization.Mensajes;
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
/*     */ public class ContabilizarFacturaJDialog extends JDialog
/*     */ {
/*     */   private Factura factura;
/*     */   private ArrayList<LineaFactura> lineas;
/*     */   private ArrayList<LineaFacturaContable> lineasContables;
/*  49 */   private DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
/*  50 */   private DefaultComboBoxModel modeloComboCobro = new DefaultComboBoxModel();
/*  51 */   private DefaultComboBoxModel modeloComboAlmacenes = new DefaultComboBoxModel();
/*     */ 
/*  53 */   boolean contabilizada = false;
/*  54 */   boolean almacenada = false;
/*     */   private boolean emitida;
/*     */   private JButton botonContabilizar;
/*     */   private ICalendarTextField campoFecha;
/*     */   private JTextField campoNumero;
/*     */   private JComboBox comboAlmacenes;
/*     */   private JComboBox comboSubcuentaCobro;
/*     */   private JComboBox comboSubcuentas;
/*     */   private JCheckBox crearVencimientos;
/*     */   private JButton jButton1;
/*     */   private JLabel jLabel1;
/*     */   private JLabel jLabel2;
/*     */   private JLabel jLabel3;
/*     */   private JLabel jLabel4;
/*     */   private JLabel jLabel5;
/*     */   private JPanel jPanel1;
/*     */   private JCheckBox pasarAAlmacen;
/*     */   private JCheckBox pasarAEfectos;
/*     */   private JCheckBox soloMarcarContabilizada;
/*     */ 
/*     */   public ContabilizarFacturaJDialog(Frame parent, boolean modal, Factura factura, ArrayList<LineaFactura> lineas, boolean emitida)
/*     */   {
/*  59 */     super(parent, modal);
/*  60 */     this.factura = factura;
/*  61 */     this.lineas = lineas;
/*  62 */     this.emitida = emitida;
/*  63 */     initComponents();
/*     */ 
/*  65 */     llenarCombo();
/*  66 */     crearLineasContables();
/*  67 */     this.campoNumero.setText(factura.getNumero());
/*  68 */     this.campoFecha.setDate(factura.getFecha());
/*  69 */     if (factura.isIsAlmacenada()) {
/*  70 */       this.pasarAAlmacen.setEnabled(false);
/*  71 */       this.comboAlmacenes.setEnabled(false);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void llenarCombo() {
/*     */     try {
/*  77 */       ManejoSubcuentas mS = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  78 */       ArrayList subcuentas = mS.listaSubcuentas(47300000, 47399999);
/*  79 */       subcuentas.addAll(mS.listaSubcuentas(47510000, 47519999));
/*  80 */       for (TipoSubcuenta subc :(List<TipoSubcuenta>) subcuentas) {
/*  81 */         this.modeloCombo.addElement(subc);
/*     */       }
/*  83 */       this.comboSubcuentas.setModel(this.modeloCombo);
/*     */ 
/*  85 */       ArrayList subcuentasCobro = mS.listaSubcuentas(57000000, 57999999);
/*  86 */       for (TipoSubcuenta subcc : (List<TipoSubcuenta>)subcuentasCobro) {
/*  87 */         this.modeloComboCobro.addElement(subcc);
/*     */       }
/*  89 */       this.comboSubcuentaCobro.setModel(this.modeloComboCobro);
/*     */ 
/*  91 */       AlmacenControl aC = new AlmacenControl();
/*  92 */       ArrayList almacenes = aC.getAlmacenes();
/*  93 */       aC.cerrarConexion();
/*  94 */       for (AlmacenInterno almacenInterno : (List<AlmacenInterno>)almacenes) {
/*  95 */         this.modeloComboAlmacenes.addElement(almacenInterno);
/*     */       }
/*  97 */       this.comboAlmacenes.setModel(this.modeloComboAlmacenes);
/*     */     }
/*     */     catch (Exception ex) {
/* 100 */       Logger.getLogger(FacturasJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void crearLineasContables() {
/* 105 */     this.lineasContables = new ArrayList();
/* 106 */     for (LineaFactura lineaProducto : this.lineas) {
/* 107 */       int subcuenta = lineaProducto.getIdProducto().getSubcuenta().getCodigo();
/* 108 */       TipoIVA tipoIva = lineaProducto.getTipoIva();
/*     */ 
/* 120 */       double base = lineaProducto.getBase().doubleValue();
/* 121 */       addLineaContable(subcuenta, tipoIva, base);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void addLineaContable(int subcuenta, TipoIVA tipoIva, double base) {
/* 126 */     boolean existe = false;
/* 127 */     for (int i = 0; i < this.lineasContables.size(); i++) {
/* 128 */       if ((((LineaFacturaContable)this.lineasContables.get(i)).getSubcuenta() == subcuenta) && (((LineaFacturaContable)this.lineasContables.get(i)).getTipoIVA().equals(tipoIva)))
/*     */       {
/* 130 */         ((LineaFacturaContable)this.lineasContables.get(i)).setBase(((LineaFacturaContable)this.lineasContables.get(i)).getBase() + base);
/* 131 */         existe = true;
/* 132 */         break;
/*     */       }
/*     */     }
/* 135 */     if (!existe) {
/* 136 */       LineaFacturaContable linea = new LineaFacturaContable(subcuenta, tipoIva, base);
/* 137 */       this.lineasContables.add(linea);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void contabilizar() {
/* 142 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
/* 143 */     Date nuevaFecha = this.campoFecha.getDate();
/* 144 */     String fechaBD = sdf.format(nuevaFecha != null ? nuevaFecha : this.factura.getFecha());
/* 145 */     if (!fechaBD.substring(0, 4).equals(Inicio.p.getEjercicio())) {
/* 146 */       JOptionPane.showMessageDialog(getContentPane(), Mensajes.getString("ejercicioNoCorrecto"));
/* 147 */       return;
/*     */     }
/*     */ 
/* 150 */     String nuevoNumero = this.campoNumero.getText();
/* 151 */     if (!nuevoNumero.equals("")) {
/* 152 */       this.factura.setNumero(nuevoNumero);
/*     */     }
/* 154 */     if (this.soloMarcarContabilizada.isSelected()) {
/* 155 */       setContabilizada(true);
/*     */     }
/*     */     else {
/* 158 */       if ((this.crearVencimientos.isSelected()) && (this.factura.getFormaPago() == null)) {
/* 159 */         JOptionPane.showMessageDialog(getContentPane(), "Marco Generar vencimientos y la factura\nno tiene asociada una forma de pago.\nSeleccione una y guarde la factura antes de volver.", "Error", 0);
/*     */ 
/* 163 */         return;
/*     */       }
/* 165 */       ManejoAsientos mA = new ManejoAsientos(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 166 */       ManejoApuntes mAp = new ManejoApuntes(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 167 */       ManejoFacturas mF = new ManejoFacturas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 168 */       AlmacenControl aC = new AlmacenControl();
/*     */ 
/* 170 */       int existe = mF.existeFactura(this.emitida, this.factura.getNumero());
/*     */       int idAlmacen;
/* 171 */       if (existe == -1)
/*     */       {
/* 173 */         String marca = this.emitida ? "E" : "R";
/* 174 */         double total = 0.0D;
/* 175 */         int numAsto = mA.nuevoNumero();
/*     */ 
/* 178 */         if (numAsto != -1) {
/* 179 */           double ivaTotal = 0.0D;
/* 180 */           double reTotal = 0.0D;
/* 181 */           int idAsto = mA.crear(numAsto, fechaBD, this.factura.getNumero(), marca);
/* 182 */           for (LineaFacturaContable linea : this.lineasContables) {
/* 183 */             String DH = this.factura.getBase().doubleValue() >= 0.0D ? "H" : "D";
/* 184 */             if (!this.emitida) {
/* 185 */               DH = this.factura.getBase().doubleValue() >= 0.0D ? "D" : "H";
/*     */             }
/* 187 */             double base = linea.getBase();
/* 188 */             double iva = base * linea.getTipoIVA().getIva() / 100.0D;
/* 189 */             double re = base * linea.getTipoIVA().getRe() / 100.0D;
/* 190 */             mAp.crear(idAsto, linea.getSubcuenta(), "Factura nº " + this.factura.getNumero(), DH, base >= 0.0D ? base : -1.0D * base);
/* 191 */             total += base;
/* 192 */             if (iva != 0.0D) {
/* 193 */               int cuenta = this.emitida ? linea.getTipoIVA().getCuentaRep() : linea.getTipoIVA().getCuentaSop();
/* 194 */               mAp.crear(idAsto, cuenta, "Factura nº " + this.factura.getNumero(), DH, iva >= 0.0D ? iva : -1.0D * iva);
/* 195 */               total += iva;
/* 196 */               ivaTotal += iva;
/*     */             }
/* 198 */             if ((this.emitida) && (this.factura.isRecargo())) {
/* 199 */               mAp.crear(idAsto, linea.getTipoIVA().getCuentaRE(), "Factura nº " + this.factura.getNumero(), DH, re >= 0.0D ? re : -1.0D * re);
/* 200 */               total += re;
/* 201 */               reTotal += re;
/*     */             }
/*     */           }
/* 204 */           if (this.factura.getRetencion().doubleValue() != 0.0D) {
/* 205 */             TipoSubcuenta subRetencion = (TipoSubcuenta)this.comboSubcuentas.getSelectedItem();
/* 206 */             if (subRetencion != null) {
/* 207 */               mAp.crear(idAsto, subRetencion.getCodigo(), "Retención. Factura " + this.factura.getNumero(), this.emitida ? "D" : "H", this.factura.getRetencion().doubleValue());
/* 208 */               if (total >= 0.0D) {
/* 209 */                 total -= this.factura.getRetencion().doubleValue();
/*     */               }
/*     */               else {
/* 212 */                 total += this.factura.getRetencion().doubleValue();
/*     */               }
/*     */             }
/*     */           }
/* 216 */           String DH = this.factura.getBase().doubleValue() >= 0.0D ? "D" : "H";
/* 217 */           if (!this.emitida) {
/* 218 */             DH = this.factura.getBase().doubleValue() >= 0.0D ? "H" : "D";
/*     */           }
/* 220 */           mAp.crear(idAsto, this.factura.getCliente().getCodigo(), "Factura nº " + this.factura.getNumero(), DH, total >= 0.0D ? total : -1.0D * total);
/*     */ 
/* 222 */           TipoFactura facturaContable = new TipoFactura(this.factura.getNumero(), fechaBD, this.factura.getCliente().getCodigo(), idAsto, "Factura " + this.factura.getNumero(), this.factura.getBase().doubleValue(), ivaTotal, reTotal, this.factura.getBase().doubleValue() + ivaTotal + reTotal);
/* 223 */           mF.crear(this.emitida, facturaContable);
/* 224 */           Inicio.frame.renovarTabla(0);
/* 225 */           if (this.emitida)
/* 226 */             Inicio.frame.renovarTabla(5);
/*     */           else
/* 228 */             Inicio.frame.renovarTabla(6);
/* 229 */           setContabilizada(true);
/* 230 */           if (this.crearVencimientos.isSelected()) {
/* 231 */             generarVencimientos(facturaContable);
/*     */           }
/*     */ 
/* 235 */           if (this.pasarAAlmacen.isSelected()) {
/* 236 */             setAlmacenada(true);
/* 237 */             idAlmacen = 1;
/* 238 */             AlmacenInterno almacen = (AlmacenInterno)this.comboAlmacenes.getSelectedItem();
/* 239 */             if (almacen != null) {
/* 240 */               idAlmacen = almacen.getId().intValue();
/*     */             }
/* 242 */             for (LineaFactura linea : this.lineas) {
/* 243 */               double base = linea.getBase().doubleValue();
/* 244 */               if (this.emitida) {
/* 245 */                 base = linea.getTotal().doubleValue();
/*     */               }
/* 247 */               double importeUnidad = base / linea.getUnidades().doubleValue();
/* 248 */               if (this.emitida) {
/* 249 */                 if (base >= 0.0D) {
/* 250 */                   aC.insertVenta(linea.getIdProducto().getReferencia(), nuevaFecha != null ? nuevaFecha : this.factura.getFecha(), importeUnidad, linea.getUnidades().intValue(), idAlmacen);
/*     */                 } else {
/* 252 */                   Double coste = aC.getCosteProducto(linea.getIdProducto().getReferencia());
/* 253 */                   if (coste == null) {
/* 254 */                     coste = Double.valueOf(0.0D);
/*     */                   }
/* 256 */                   aC.insertCompra(linea.getIdProducto().getReferencia(), nuevaFecha != null ? nuevaFecha : this.factura.getFecha(), coste.doubleValue(), linea.getUnidades().intValue(), idAlmacen);
/*     */                 }
/*     */               }
/* 259 */               else if (base >= 0.0D)
/* 260 */                 aC.insertCompra(linea.getIdProducto().getReferencia(), nuevaFecha != null ? nuevaFecha : this.factura.getFecha(), importeUnidad, linea.getUnidades().intValue(), idAlmacen);
/*     */               else {
/* 262 */                 aC.insertVenta(linea.getIdProducto().getReferencia(), nuevaFecha != null ? nuevaFecha : this.factura.getFecha(), importeUnidad, linea.getUnidades().intValue(), idAlmacen);
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/* 271 */         JOptionPane.showMessageDialog(getContentPane(), "Ya existe una factura con este número", "Error", 0);
/*     */       }
/* 273 */       aC.cerrarConexion();
/* 274 */       mF.cerrarRs();
/* 275 */       mA.cerrarRs();
/* 276 */       mAp.cerrarRs();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void generarVencimientos(TipoFactura facturaContable) {
/* 281 */     Double totalACobrar = Double.valueOf(this.factura.getTotal().doubleValue() - this.factura.getRetencion().doubleValue());
/* 282 */     int cuentaTerceros = facturaContable.getCuenta();
/* 283 */     if (this.pasarAEfectos.isSelected()) {
/* 284 */       int subcuentaEfectos = cuentaEfectos(facturaContable.getCuenta());
/* 285 */       cuentaTerceros = subcuentaEfectos;
/* 286 */       if (subcuentaEfectos != -1)
/*     */       {
/* 288 */         ManejoAsientos mA = new ManejoAsientos(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 289 */         ManejoApuntes mAp = new ManejoApuntes(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 290 */         int idAsto = -1;
/* 291 */         int num_asto = mA.nuevoNumero();
/* 292 */         idAsto = mA.crear(num_asto, facturaContable.getFecha(), "", "");
/*     */ 
/* 294 */         if (this.emitida) {
/* 295 */           mAp.crear(idAsto, subcuentaEfectos, "Traspaso a efectos comerciales", "D", totalACobrar.doubleValue());
/* 296 */           mAp.crear(idAsto, facturaContable.getCuenta(), "Traspaso a efectos comerciales", "H", totalACobrar.doubleValue());
/*     */         }
/*     */         else {
/* 299 */           mAp.crear(idAsto, subcuentaEfectos, "Traspaso a efectos comerciales", "H", totalACobrar.doubleValue());
/* 300 */           mAp.crear(idAsto, facturaContable.getCuenta(), "Traspaso a efectos comerciales", "D", totalACobrar.doubleValue());
/*     */         }
/*     */       }
/*     */       else {
/* 304 */         JOptionPane.showMessageDialog(getContentPane(), "NO existe cuenta de Efectos comerciales\npara este Cliente/Deudor\nNo se hace traspaso a efectos comerciales");
/*     */       }
/*     */     }
/*     */ 
/* 308 */     TipoFormaPago formaPago = this.factura.getFormaPago();
/* 309 */     TipoSubcuenta subcuentaPago = (TipoSubcuenta)this.comboSubcuentaCobro.getSelectedItem();
/* 310 */     int year = Integer.parseInt(facturaContable.getFecha().substring(0, 4));
/* 311 */     int mes = Integer.parseInt(facturaContable.getFecha().substring(4, 6));
/* 312 */     mes--;
/* 313 */     int dia = Integer.parseInt(facturaContable.getFecha().substring(6));
/* 314 */     Calendar calendar = Calendar.getInstance();
/* 315 */     Calendar calendarFechaMes = Calendar.getInstance();
/* 316 */     calendar.set(year, mes, dia);
/* 317 */     ArrayList vencimientos = new ArrayList();
/* 318 */     double importeVencimiento = totalACobrar.doubleValue() / formaPago.getNumeroPagos().intValue();
/*     */ 
/* 320 */     for (int i = 0; i < formaPago.getNumeroPagos().intValue(); i++)
/* 321 */       if ((formaPago.getDiaFijoPago() == null) || (formaPago.getDiaFijoPago().intValue() == 0)) {
/* 322 */         if (i == 0) {
/* 323 */           calendar.add(5, formaPago.getDiasPrimerPago().intValue());
/*     */         }
/* 326 */         else if (formaPago.getDiasEntrePagos().intValue() == 30) {
/* 327 */           calendar.add(2, 1);
/*     */         }
/*     */         else {
/* 330 */           calendar.add(5, formaPago.getDiasEntrePagos().intValue());
/*     */         }
/*     */ 
/* 333 */         String num = Integer.toString(i + 1) + "/" + Integer.toString(formaPago.getNumeroPagos().intValue());
/* 334 */         TipoVencimiento vencimiento = new TipoVencimiento();
/* 335 */         vencimiento.setFecha(dateToString(calendar.getTime()));
/* 336 */         vencimiento.setEjercicio(year);
/* 337 */         vencimiento.setFactura(facturaContable.getNumero());
/* 338 */         vencimiento.setFechaf(facturaContable.getFecha());
/* 339 */         vencimiento.setCuenta(cuentaTerceros);
/* 340 */         vencimiento.setImporte(importeVencimiento);
/* 341 */         vencimiento.setNum(num);
/* 342 */         vencimiento.setPagado(false);
/* 343 */         vencimiento.setCuentap(subcuentaPago.getCodigo());
/* 344 */         vencimientos.add(vencimiento);
/*     */       }
/*     */       else {
/* 347 */         if (i == 0) {
/* 348 */           calendar.add(5, formaPago.getDiasPrimerPago().intValue());
/* 349 */           calendarFechaMes.setTime(calendar.getTime());
/* 350 */           calendarFechaMes.set(5, formaPago.getDiaFijoPago().intValue());
/* 351 */           if (calendar.getTimeInMillis() > calendarFechaMes.getTimeInMillis()) {
/* 352 */             calendarFechaMes.add(2, 1);
/*     */           }
/* 354 */           calendar.setTime(calendarFechaMes.getTime());
/*     */         }
/*     */         else {
/* 357 */           calendar.add(5, formaPago.getDiasEntrePagos().intValue());
/* 358 */           calendarFechaMes.setTime(calendar.getTime());
/* 359 */           calendarFechaMes.set(5, formaPago.getDiaFijoPago().intValue());
/* 360 */           if (calendar.getTimeInMillis() > calendarFechaMes.getTimeInMillis()) {
/* 361 */             calendarFechaMes.add(2, 1);
/*     */           }
/*     */         }
/*     */ 
/* 365 */         if ((calendarFechaMes.get(5) < formaPago.getDiaFijoPago().intValue()) && (calendarFechaMes.get(2) == 2))
/*     */         {
/* 367 */           calendarFechaMes.set(2, 1);
/* 368 */           calendarFechaMes.set(5, 28);
/* 369 */           if (i == 0) {
/* 370 */             calendar.setTime(calendarFechaMes.getTime());
/*     */           }
/*     */         }
/* 373 */         String num = Integer.toString(i + 1) + "/" + Integer.toString(formaPago.getNumeroPagos().intValue());
/* 374 */         TipoVencimiento vencimiento = new TipoVencimiento();
/* 375 */         vencimiento.setFecha(dateToString(calendarFechaMes.getTime()));
/* 376 */         vencimiento.setEjercicio(year);
/* 377 */         vencimiento.setFactura(facturaContable.getNumero());
/* 378 */         vencimiento.setFechaf(facturaContable.getFecha());
/* 379 */         vencimiento.setCuenta(cuentaTerceros);
/* 380 */         vencimiento.setImporte(importeVencimiento);
/* 381 */         vencimiento.setNum(num);
/* 382 */         vencimiento.setPagado(false);
/* 383 */         vencimiento.setCuentap(subcuentaPago.getCodigo());
/* 384 */         vencimientos.add(vencimiento);
/*     */       }
/*     */     ManejoVencimientos mV;
/* 388 */     if (vencimientos.size() > 0) {
/* 389 */       mV = new ManejoVencimientos(Inicio.getCEmpresa());
/* 390 */       for (TipoVencimiento tipoVencimiento : (List<TipoVencimiento>) vencimientos) {
/* 391 */         mV.crear(this.emitida, tipoVencimiento);
/*     */       }
/*     */     }
/* 394 */     if (this.emitida) {
/* 395 */       Inicio.frame.renovarTabla(2);
/*     */     }
/*     */     else
/* 398 */       Inicio.frame.renovarTabla(1);
/*     */   }
/*     */ 
/*     */   private int cuentaEfectos(int cuenta)
/*     */   {
/* 403 */     String cadenaSubcuenta = Integer.toString(cuenta);
/* 404 */     String cadenaGrupo = cadenaSubcuenta.substring(0, 2);
/* 405 */     String cadenaFin = cadenaSubcuenta.substring(3);
/* 406 */     if (cadenaGrupo.equals("40"))
/* 407 */       cadenaSubcuenta = "401" + cadenaFin;
/* 408 */     else if (cadenaGrupo.equals("41"))
/* 409 */       cadenaSubcuenta = "411" + cadenaFin;
/* 410 */     else if (cadenaGrupo.equals("43"))
/* 411 */       cadenaSubcuenta = "431" + cadenaFin;
/* 412 */     else if (cadenaGrupo.equals("44")) {
/* 413 */       cadenaSubcuenta = "441" + cadenaFin;
/*     */     }
/* 415 */     int cuentaEfectos = Integer.parseInt(cadenaSubcuenta);
/* 416 */     ManejoSubcuentas mS = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 417 */     if (mS.existeSubcuenta(cuentaEfectos)) {
/* 418 */       return cuentaEfectos;
/*     */     }
/* 420 */     return -1;
/*     */   }
/*     */ 
/*     */   private String dateToString(Date date) {
/* 424 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
/* 425 */     return sdf.format(date);
/*     */   }
/*     */ 
/*     */   public boolean isContabilizada() {
/* 429 */     return this.contabilizada;
/*     */   }
/*     */ 
/*     */   private void setContabilizada(boolean contabilizada) {
/* 433 */     this.contabilizada = contabilizada;
/*     */   }
/*     */ 
/*     */   public boolean isAlmacenada() {
/* 437 */     return this.almacenada;
/*     */   }
/*     */ 
/*     */   public void setAlmacenada(boolean almacenada) {
/* 441 */     this.almacenada = almacenada;
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/* 453 */     this.jPanel1 = new JPanel();
/* 454 */     this.jLabel3 = new JLabel();
/* 455 */     this.pasarAEfectos = new JCheckBox();
/* 456 */     this.jLabel2 = new JLabel();
/* 457 */     this.comboSubcuentaCobro = new JComboBox();
/* 458 */     this.jLabel1 = new JLabel();
/* 459 */     this.campoNumero = new JTextField();
/* 460 */     this.comboSubcuentas = new JComboBox();
/* 461 */     this.crearVencimientos = new JCheckBox();
/* 462 */     this.botonContabilizar = new JButton();
/* 463 */     this.jButton1 = new JButton();
/* 464 */     this.soloMarcarContabilizada = new JCheckBox();
/* 465 */     this.pasarAAlmacen = new JCheckBox();
/* 466 */     this.jLabel4 = new JLabel();
/* 467 */     this.comboAlmacenes = new JComboBox();
/* 468 */     this.jLabel5 = new JLabel();
/* 469 */     this.campoFecha = new ICalendarTextField();
/*     */ 
/* 471 */     setDefaultCloseOperation(2);
/* 472 */     setTitle("Contabilizar factura");
/*     */ 
/* 474 */     this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/*     */ 
/* 476 */     ResourceBundle bundle = ResourceBundle.getBundle("internationalization/Mensajes");
/* 477 */     this.jLabel3.setText(bundle.getString("numerofactura"));
/*     */ 
/* 479 */     this.pasarAEfectos.setText("Pasar a efectos comerciales");
/* 480 */     this.pasarAEfectos.setEnabled(false);
/*     */ 
/* 482 */     this.jLabel2.setText("Cuenta de cobro");
/*     */ 
/* 484 */     this.jLabel1.setText("Cuenta de retenciones");
/*     */ 
/* 486 */     this.crearVencimientos.setText("Crear vencimientos");
/* 487 */     this.crearVencimientos.addChangeListener(new ChangeListener() {
/*     */       public void stateChanged(ChangeEvent evt) {
/* 489 */         ContabilizarFacturaJDialog.this.crearVencimientosStateChanged(evt);
/*     */       }
/*     */     });
/* 493 */     this.botonContabilizar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/D16.png")));
/* 494 */     this.botonContabilizar.setText("Contabilizar");
/* 495 */     this.botonContabilizar.setHorizontalTextPosition(2);
/* 496 */     this.botonContabilizar.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 498 */         ContabilizarFacturaJDialog.this.botonContabilizarActionPerformed(evt);
/*     */       }
/*     */     });
/* 502 */     this.jButton1.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/cancel.png")));
/* 503 */     this.jButton1.setText("Cancelar");
/* 504 */     this.jButton1.setHorizontalTextPosition(2);
/* 505 */     this.jButton1.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 507 */         ContabilizarFacturaJDialog.this.jButton1ActionPerformed(evt);
/*     */       }
/*     */     });
/* 511 */     this.soloMarcarContabilizada.setText("Sólo marcar como Contabilizada");
/* 512 */     this.soloMarcarContabilizada.setToolTipText("Sólo marca la factura como contabilizada. No introduce nada en la contabilidad");
/*     */ 
/* 514 */     this.pasarAAlmacen.setText(bundle.getString("updatealmacen"));
/*     */ 
/* 516 */     this.jLabel4.setText(bundle.getString("almacen"));
/*     */ 
/* 518 */     this.jLabel5.setText(bundle.getString("fecha"));
/*     */ 
/* 520 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 521 */     this.jPanel1.setLayout(jPanel1Layout);
/* 522 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().add(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(jPanel1Layout.createParallelGroup(2).add(this.jLabel3).add(this.jLabel5)).add(29, 29, 29).add(jPanel1Layout.createParallelGroup(1, false).add(this.campoFecha, -1, -1, 32767).add(this.campoNumero, -1, 182, 32767)).add(31, 31, 31)).add(jPanel1Layout.createSequentialGroup().add(29, 29, 29).add(this.soloMarcarContabilizada))).add(39, 39, 39)).add(2, jPanel1Layout.createSequentialGroup().addContainerGap().add(this.botonContabilizar).addPreferredGap(0, 66, 32767).add(this.jButton1).add(148, 148, 148)).add(jPanel1Layout.createSequentialGroup().add(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().add(27, 27, 27).add(jPanel1Layout.createParallelGroup(1).add(this.pasarAEfectos).add(jPanel1Layout.createSequentialGroup().addPreferredGap(0).add(this.jLabel2).addPreferredGap(1).add(this.comboSubcuentaCobro, 0, 222, 32767)))).add(this.crearVencimientos).add(jPanel1Layout.createSequentialGroup().add(this.jLabel1).addPreferredGap(0).add(this.comboSubcuentas, -2, 213, -2)))).add(this.pasarAAlmacen)).add(jPanel1Layout.createSequentialGroup().add(29, 29, 29).add(this.jLabel4).add(18, 18, 18).add(this.comboAlmacenes, -2, 201, -2).add(68, 68, 68))).add(34, 34, 34)));
/*     */ 
/* 575 */     jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(this.soloMarcarContabilizada, -2, 33, -2).addPreferredGap(0).add(jPanel1Layout.createParallelGroup(3).add(this.jLabel3).add(this.campoNumero, -2, -1, -2)).addPreferredGap(0).add(jPanel1Layout.createParallelGroup(2).add(this.campoFecha, -2, -1, -2).add(this.jLabel5)).addPreferredGap(0, 19, 32767).add(jPanel1Layout.createParallelGroup(3).add(this.jLabel1).add(this.comboSubcuentas, -2, -1, -2)).addPreferredGap(1).add(this.crearVencimientos).addPreferredGap(0).add(jPanel1Layout.createParallelGroup(3).add(this.jLabel2).add(this.comboSubcuentaCobro, -2, -1, -2)).addPreferredGap(0).add(this.pasarAEfectos).add(18, 18, 18).add(this.pasarAAlmacen).addPreferredGap(1).add(jPanel1Layout.createParallelGroup(3).add(this.jLabel4).add(this.comboAlmacenes, -2, -1, -2)).add(18, 18, 18).add(jPanel1Layout.createParallelGroup(3).add(this.jButton1).add(this.botonContabilizar)).addContainerGap()));
/*     */ 
/* 613 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 614 */     getContentPane().setLayout(layout);
/* 615 */     layout.setHorizontalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(this.jPanel1, -1, -1, 32767).addContainerGap()));
/*     */ 
/* 622 */     layout.setVerticalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(this.jPanel1, -1, -1, 32767).addContainerGap()));
/*     */ 
/* 630 */     pack();
/*     */   }
/*     */ 
/*     */   private void crearVencimientosStateChanged(ChangeEvent evt) {
/* 634 */     if (this.crearVencimientos.isSelected()) {
/* 635 */       this.pasarAEfectos.setEnabled(true);
/*     */     }
/*     */     else
/* 638 */       this.pasarAEfectos.setEnabled(false);
/*     */   }
/*     */ 
/*     */   private void jButton1ActionPerformed(ActionEvent evt)
/*     */   {
/* 643 */     dispose();
/*     */   }
/*     */ 
/*     */   private void botonContabilizarActionPerformed(ActionEvent evt) {
/* 647 */     contabilizar();
/* 648 */     dispose();
/*     */   }
/*     */ 
/*     */   class LineaFacturaContable
/*     */   {
/*     */     private int subcuenta;
/*     */     private TipoIVA tipoIVA;
/*     */     private double base;
/*     */ 
/*     */     public LineaFacturaContable(int subcuenta, TipoIVA tipoIVA, double base)
/*     */     {
/* 679 */       this.subcuenta = subcuenta;
/* 680 */       this.tipoIVA = tipoIVA;
/* 681 */       this.base = base;
/*     */     }
/*     */ 
/*     */     public double getBase() {
/* 685 */       return this.base;
/*     */     }
/*     */ 
/*     */     public int getSubcuenta() {
/* 689 */       return this.subcuenta;
/*     */     }
/*     */ 
/*     */     public TipoIVA getTipoIVA() {
/* 693 */       return this.tipoIVA;
/*     */     }
/*     */ 
/*     */     public void setBase(double base) {
/* 697 */       this.base = base;
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     facturacion.view.ContabilizarFacturaJDialog
 * JD-Core Version:    0.6.2
 */