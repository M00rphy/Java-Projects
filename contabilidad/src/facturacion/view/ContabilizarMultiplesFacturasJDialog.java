/*     */ package facturacion.view;
/*     */ 
/*     */ import almacen2.data.AlmacenInterno;
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
/*     */ import facturacion.control.FacturaControl;
/*     */ import facturacion.control.LineaFacturaControl;
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
/*     */ import javax.swing.event.ChangeEvent;
/*     */ import javax.swing.event.ChangeListener;
/*     */ import org.jdesktop.layout.GroupLayout;
/*     */ import org.jdesktop.layout.GroupLayout.ParallelGroup;
/*     */ import org.jdesktop.layout.GroupLayout.SequentialGroup;
/*     */ 
/*     */ public class ContabilizarMultiplesFacturasJDialog extends JDialog
/*     */ {
/*     */   private ArrayList<String> numeros;
/*     */   private Factura factura;
/*  48 */   private ArrayList<LineaFactura> lineas = new ArrayList();
/*  49 */   private ArrayList<LineaFacturaContable> lineasContables = new ArrayList();
/*  50 */   private DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
/*  51 */   private DefaultComboBoxModel modeloComboCobro = new DefaultComboBoxModel();
/*  52 */   private DefaultComboBoxModel modeloComboAlmacenes = new DefaultComboBoxModel();
/*  53 */   private boolean contabilizada = false;
/*  54 */   private boolean almacenada = false;
/*     */   private boolean emitida;
/*     */   private ManejoAsientos mA;
/*     */   private ManejoApuntes mAp;
/*     */   private ManejoFacturas mF;
/*     */   private AlmacenControl aC;
/*     */   private JButton botonContabilizar;
/*     */   private JComboBox comboAlmacenes;
/*     */   private JComboBox comboSubcuentaCobro;
/*     */   private JComboBox comboSubcuentas;
/*     */   private JCheckBox crearVencimientos;
/*     */   private JButton jButton1;
/*     */   private JLabel jLabel1;
/*     */   private JLabel jLabel2;
/*     */   private JLabel jLabel3;
/*     */   private JLabel jLabel4;
/*     */   private JPanel jPanel1;
/*     */   private JCheckBox pasarAAlmacen;
/*     */   private JCheckBox pasarAEfectos;
/*     */   private JCheckBox soloMarcarContabilizada;
/*     */ 
/*     */   public ContabilizarMultiplesFacturasJDialog(Frame parent, boolean modal, ArrayList<String> numeros, boolean emitida)
/*     */   {
/*  64 */     super(parent, modal);
/*  65 */     this.numeros = numeros;
/*  66 */     this.emitida = emitida;
/*  67 */     initComponents();
/*  68 */     this.mA = new ManejoAsientos(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  69 */     this.mAp = new ManejoApuntes(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  70 */     this.mF = new ManejoFacturas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  71 */     this.aC = new AlmacenControl();
/*  72 */     llenarCombo();
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
/*  86 */       for (TipoSubcuenta subcc :(List<TipoSubcuenta>)  subcuentasCobro) {
/*  87 */         this.modeloComboCobro.addElement(subcc);
/*     */       }
/*  89 */       this.comboSubcuentaCobro.setModel(this.modeloComboCobro);
/*     */ 
/*  91 */       ArrayList almacenes = this.aC.getAlmacenes();
/*  92 */       for (AlmacenInterno almacenInterno :(List<AlmacenInterno>)  almacenes) {
/*  93 */         this.modeloComboAlmacenes.addElement(almacenInterno);
/*     */       }
/*  95 */       this.comboAlmacenes.setModel(this.modeloComboAlmacenes);
/*     */     }
/*     */     catch (Exception ex) {
/*  98 */       Logger.getLogger(FacturasJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void ejecutarProceso() {
/* 103 */     FacturaControl fC = new FacturaControl(Inicio.getCFacturacion(), this.emitida);
/* 104 */     ArrayList listaFacturas = new ArrayList();
/* 105 */     for (String numero : this.numeros) {
/*     */       try {
/* 107 */         listaFacturas.add(fC.factura(numero));
/*     */       } catch (Exception ex) {
/* 109 */         Logger.getLogger(ContabilizarMultiplesFacturasJDialog.class.getName()).log(Level.SEVERE, null, ex);
/*     */       }
/*     */     }
/*     */ 
/* 113 */     LineaFacturaControl lFC = new LineaFacturaControl(Inicio.getCFacturacion(), this.emitida);
/* 114 */     for (Factura f : (List<Factura>)listaFacturas) {
/* 115 */       this.factura = f;
/* 116 */       this.lineas.clear();
/* 117 */       if (!this.factura.isContabilizada()) {
/*     */         try {
/* 119 */           this.lineas = lFC.lineas(this.factura.getId());
/*     */         } catch (Exception ex) {
/* 121 */           Logger.getLogger(ContabilizarMultiplesFacturasJDialog.class.getName()).log(Level.SEVERE, null, ex);
/*     */         }
/* 123 */         if (!this.lineas.isEmpty()) {
/* 124 */           crearLineasContables();
/* 125 */           contabilizar();
/* 126 */           if (isContabilizada()) {
/* 127 */             this.factura.setContabilizada(true);
/* 128 */             this.factura.setIsAlmacenada(isAlmacenada());
/*     */             try {
/* 130 */               fC.modificar(this.factura);
/*     */             } catch (Exception ex) {
/* 132 */               Logger.getLogger(ContabilizarMultiplesFacturasJDialog.class.getName()).log(Level.SEVERE, null, ex);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 138 */     if (this.emitida)
/* 139 */       Inicio.frame.renovarTabla(7);
/*     */     else {
/* 141 */       Inicio.frame.renovarTabla(8);
/*     */     }
/* 143 */     fC.cerrarRs();
/* 144 */     lFC.cerrarRs();
/*     */   }
/*     */ 
/*     */   private void crearLineasContables() {
/* 148 */     this.lineasContables.clear();
/* 149 */     for (LineaFactura lineaProducto : this.lineas) {
/* 150 */       int subcuenta = lineaProducto.getIdProducto().getSubcuenta().getCodigo();
/* 151 */       TipoIVA tipoIva = lineaProducto.getTipoIva();
/* 152 */       double base = lineaProducto.getBase().doubleValue();
/* 153 */       addLineaContable(subcuenta, tipoIva, base);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void addLineaContable(int subcuenta, TipoIVA tipoIva, double base) {
/* 158 */     boolean existe = false;
/* 159 */     for (int i = 0; i < this.lineasContables.size(); i++) {
/* 160 */       if ((((LineaFacturaContable)this.lineasContables.get(i)).getSubcuenta() == subcuenta) && (((LineaFacturaContable)this.lineasContables.get(i)).getTipoIVA().equals(tipoIva)))
/*     */       {
/* 162 */         ((LineaFacturaContable)this.lineasContables.get(i)).setBase(((LineaFacturaContable)this.lineasContables.get(i)).getBase() + base);
/* 163 */         existe = true;
/* 164 */         break;
/*     */       }
/*     */     }
/* 167 */     if (!existe) {
/* 168 */       LineaFacturaContable linea = new LineaFacturaContable(subcuenta, tipoIva, base);
/* 169 */       this.lineasContables.add(linea);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void contabilizar() {
/* 174 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
/* 175 */     Date nuevaFecha = this.factura.getFecha();
/* 176 */     String fechaBD = sdf.format(nuevaFecha != null ? nuevaFecha : this.factura.getFecha());
/* 177 */     if (!fechaBD.substring(0, 4).equals(Inicio.p.getEjercicio())) {
/* 178 */       JOptionPane.showMessageDialog(getContentPane(), Mensajes.getString("ejercicioNoCorrecto"));
/* 179 */       return;
/*     */     }
/*     */ 
/* 182 */     if (this.soloMarcarContabilizada.isSelected()) {
/* 183 */       setContabilizada(true);
/*     */     } else {
/* 185 */       if ((this.crearVencimientos.isSelected()) && (this.factura.getFormaPago() == null)) {
/* 186 */         JOptionPane.showMessageDialog(getContentPane(), "La factura: " + this.factura.getNumero() + "\n" + "no tiene asociada una forma de pago.\n" + "Seleccione una y guarde la factura antes de volver.", "Error", 0);
/*     */ 
/* 190 */         return;
/*     */       }
/* 192 */       int existe = this.mF.existeFactura(this.emitida, this.factura.getNumero());
/*     */       int idAlmacen;
/* 193 */       if (existe == -1)
/*     */       {
/* 195 */         String marca = this.emitida ? "E" : "R";
/* 196 */         double total = 0.0D;
/* 197 */         int numAsto = this.mA.nuevoNumero();
/*     */ 
/* 200 */         if (numAsto != -1) {
/* 201 */           double ivaTotal = 0.0D;
/* 202 */           double reTotal = 0.0D;
/* 203 */           int idAsto = this.mA.crear(numAsto, fechaBD, this.factura.getNumero(), marca);
/* 204 */           for (LineaFacturaContable linea : this.lineasContables) {
/* 205 */             String DH = this.factura.getBase().doubleValue() >= 0.0D ? "H" : "D";
/* 206 */             if (!this.emitida) {
/* 207 */               DH = this.factura.getBase().doubleValue() >= 0.0D ? "D" : "H";
/*     */             }
/* 209 */             double base = linea.getBase();
/* 210 */             double iva = base * linea.getTipoIVA().getIva() / 100.0D;
/* 211 */             double re = base * linea.getTipoIVA().getRe() / 100.0D;
/* 212 */             this.mAp.crear(idAsto, linea.getSubcuenta(), "Factura nº " + this.factura.getNumero(), DH, base >= 0.0D ? base : -1.0D * base);
/* 213 */             total += base;
/* 214 */             if (iva != 0.0D) {
/* 215 */               int cuenta = this.emitida ? linea.getTipoIVA().getCuentaRep() : linea.getTipoIVA().getCuentaSop();
/* 216 */               this.mAp.crear(idAsto, cuenta, "Factura nº " + this.factura.getNumero(), DH, iva >= 0.0D ? iva : -1.0D * iva);
/* 217 */               total += iva;
/* 218 */               ivaTotal += iva;
/*     */             }
/* 220 */             if ((this.emitida) && (this.factura.isRecargo())) {
/* 221 */               this.mAp.crear(idAsto, linea.getTipoIVA().getCuentaRE(), "Factura nº " + this.factura.getNumero(), DH, re >= 0.0D ? re : -1.0D * re);
/* 222 */               total += re;
/* 223 */               reTotal += re;
/*     */             }
/*     */           }
/* 226 */           if (this.factura.getRetencion().doubleValue() != 0.0D) {
/* 227 */             TipoSubcuenta subRetencion = (TipoSubcuenta)this.comboSubcuentas.getSelectedItem();
/* 228 */             if (subRetencion != null) {
/* 229 */               this.mAp.crear(idAsto, subRetencion.getCodigo(), "Retención. Factura " + this.factura.getNumero(), this.emitida ? "D" : "H", this.factura.getRetencion().doubleValue());
/* 230 */               if (total >= 0.0D)
/* 231 */                 total -= this.factura.getRetencion().doubleValue();
/*     */               else {
/* 233 */                 total += this.factura.getRetencion().doubleValue();
/*     */               }
/*     */             }
/*     */           }
/* 237 */           String DH = this.factura.getBase().doubleValue() >= 0.0D ? "D" : "H";
/* 238 */           if (!this.emitida) {
/* 239 */             DH = this.factura.getBase().doubleValue() >= 0.0D ? "H" : "D";
/*     */           }
/* 241 */           this.mAp.crear(idAsto, this.factura.getCliente().getCodigo(), "Factura nº " + this.factura.getNumero(), DH, total >= 0.0D ? total : -1.0D * total);
/*     */ 
/* 243 */           TipoFactura facturaContable = new TipoFactura(this.factura.getNumero(), fechaBD, this.factura.getCliente().getCodigo(), idAsto, "Factura " + this.factura.getNumero(), this.factura.getBase().doubleValue(), ivaTotal, reTotal, this.factura.getBase().doubleValue() + ivaTotal + reTotal);
/* 244 */           this.mF.crear(this.emitida, facturaContable);
/* 245 */           Inicio.frame.renovarTabla(0);
/* 246 */           if (this.emitida)
/* 247 */             Inicio.frame.renovarTabla(5);
/*     */           else {
/* 249 */             Inicio.frame.renovarTabla(6);
/*     */           }
/* 251 */           setContabilizada(true);
/* 252 */           if (this.crearVencimientos.isSelected()) {
/* 253 */             generarVencimientos(facturaContable);
/*     */           }
/*     */ 
/* 257 */           if ((this.pasarAAlmacen.isSelected()) && (!this.factura.isIsAlmacenada())) {
/* 258 */             setAlmacenada(true);
/* 259 */             idAlmacen = 1;
/* 260 */             AlmacenInterno almacen = (AlmacenInterno)this.comboAlmacenes.getSelectedItem();
/* 261 */             if (almacen != null) {
/* 262 */               idAlmacen = almacen.getId().intValue();
/*     */             }
/* 264 */             for (LineaFactura linea : this.lineas) {
/* 265 */               double base = linea.getBase().doubleValue();
/* 266 */               if (this.emitida) {
/* 267 */                 base = linea.getTotal().doubleValue();
/*     */               }
/* 269 */               double importeUnidad = base / linea.getUnidades().doubleValue();
/* 270 */               if (this.emitida) {
/* 271 */                 if (base >= 0.0D) {
/* 272 */                   this.aC.insertVenta(linea.getIdProducto().getReferencia(), nuevaFecha != null ? nuevaFecha : this.factura.getFecha(), importeUnidad, linea.getUnidades().intValue(), idAlmacen);
/*     */                 } else {
/* 274 */                   Double coste = this.aC.getCosteProducto(linea.getIdProducto().getReferencia());
/* 275 */                   if (coste == null) {
/* 276 */                     coste = Double.valueOf(0.0D);
/*     */                   }
/* 278 */                   this.aC.insertCompra(linea.getIdProducto().getReferencia(), nuevaFecha != null ? nuevaFecha : this.factura.getFecha(), coste.doubleValue(), linea.getUnidades().intValue(), idAlmacen);
/*     */                 }
/*     */               }
/* 281 */               else if (base >= 0.0D)
/* 282 */                 this.aC.insertCompra(linea.getIdProducto().getReferencia(), nuevaFecha != null ? nuevaFecha : this.factura.getFecha(), importeUnidad, linea.getUnidades().intValue(), idAlmacen);
/*     */               else {
/* 284 */                 this.aC.insertVenta(linea.getIdProducto().getReferencia(), nuevaFecha != null ? nuevaFecha : this.factura.getFecha(), importeUnidad, linea.getUnidades().intValue(), idAlmacen);
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/* 292 */         JOptionPane.showMessageDialog(getContentPane(), "Ya existe una factura de número: " + this.factura.getNumero(), "Error", 0);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void generarVencimientos(TipoFactura facturaContable)
/*     */   {
/* 299 */     Double totalACobrar = Double.valueOf(this.factura.getTotal().doubleValue() - this.factura.getRetencion().doubleValue());
/* 300 */     int cuentaTerceros = facturaContable.getCuenta();
/* 301 */     if (this.pasarAEfectos.isSelected()) {
/* 302 */       int subcuentaEfectos = cuentaEfectos(facturaContable.getCuenta());
/* 303 */       cuentaTerceros = subcuentaEfectos;
/* 304 */       if (subcuentaEfectos != -1)
/*     */       {
/* 306 */         int idAsto = -1;
/* 307 */         int num_asto = this.mA.nuevoNumero();
/* 308 */         idAsto = this.mA.crear(num_asto, facturaContable.getFecha(), "", "");
/*     */ 
/* 310 */         if (this.emitida) {
/* 311 */           this.mAp.crear(idAsto, subcuentaEfectos, "Traspaso a efectos comerciales", "D", totalACobrar.doubleValue());
/* 312 */           this.mAp.crear(idAsto, facturaContable.getCuenta(), "Traspaso a efectos comerciales", "H", totalACobrar.doubleValue());
/*     */         } else {
/* 314 */           this.mAp.crear(idAsto, subcuentaEfectos, "Traspaso a efectos comerciales", "H", totalACobrar.doubleValue());
/* 315 */           this.mAp.crear(idAsto, facturaContable.getCuenta(), "Traspaso a efectos comerciales", "D", totalACobrar.doubleValue());
/*     */         }
/*     */       } else {
/* 318 */         JOptionPane.showMessageDialog(getContentPane(), "NO existe cuenta de Efectos comerciales\npara este Cliente/Deudor\nNo se hace traspaso a efectos comerciales");
/*     */       }
/*     */     }
/*     */ 
/* 322 */     TipoFormaPago formaPago = this.factura.getFormaPago();
/* 323 */     TipoSubcuenta subcuentaPago = (TipoSubcuenta)this.comboSubcuentaCobro.getSelectedItem();
/* 324 */     int year = Integer.parseInt(facturaContable.getFecha().substring(0, 4));
/* 325 */     int mes = Integer.parseInt(facturaContable.getFecha().substring(4, 6));
/* 326 */     mes--;
/* 327 */     int dia = Integer.parseInt(facturaContable.getFecha().substring(6));
/* 328 */     Calendar calendar = Calendar.getInstance();
/* 329 */     Calendar calendarFechaMes = Calendar.getInstance();
/* 330 */     calendar.set(year, mes, dia);
/* 331 */     ArrayList vencimientos = new ArrayList();
/* 332 */     double importeVencimiento = totalACobrar.doubleValue() / formaPago.getNumeroPagos().intValue();
/*     */ 
/* 334 */     for (int i = 0; i < formaPago.getNumeroPagos().intValue(); i++)
/* 335 */       if ((formaPago.getDiaFijoPago() == null) || (formaPago.getDiaFijoPago().intValue() == 0)) {
/* 336 */         if (i == 0) {
/* 337 */           calendar.add(5, formaPago.getDiasPrimerPago().intValue());
/*     */         }
/* 339 */         else if (formaPago.getDiasEntrePagos().intValue() == 30)
/* 340 */           calendar.add(2, 1);
/*     */         else {
/* 342 */           calendar.add(5, formaPago.getDiasEntrePagos().intValue());
/*     */         }
/*     */ 
/* 345 */         String num = Integer.toString(i + 1) + "/" + Integer.toString(formaPago.getNumeroPagos().intValue());
/* 346 */         TipoVencimiento vencimiento = new TipoVencimiento();
/* 347 */         vencimiento.setFecha(dateToString(calendar.getTime()));
/* 348 */         vencimiento.setEjercicio(year);
/* 349 */         vencimiento.setFactura(facturaContable.getNumero());
/* 350 */         vencimiento.setFechaf(facturaContable.getFecha());
/* 351 */         vencimiento.setCuenta(cuentaTerceros);
/* 352 */         vencimiento.setImporte(importeVencimiento);
/* 353 */         vencimiento.setNum(num);
/* 354 */         vencimiento.setPagado(false);
/* 355 */         vencimiento.setCuentap(subcuentaPago.getCodigo());
/* 356 */         vencimientos.add(vencimiento);
/*     */       } else {
/* 358 */         if (i == 0) {
/* 359 */           calendar.add(5, formaPago.getDiasPrimerPago().intValue());
/* 360 */           calendarFechaMes.setTime(calendar.getTime());
/* 361 */           calendarFechaMes.set(5, formaPago.getDiaFijoPago().intValue());
/* 362 */           if (calendar.getTimeInMillis() > calendarFechaMes.getTimeInMillis()) {
/* 363 */             calendarFechaMes.add(2, 1);
/*     */           }
/* 365 */           calendar.setTime(calendarFechaMes.getTime());
/*     */         } else {
/* 367 */           calendar.add(5, formaPago.getDiasEntrePagos().intValue());
/* 368 */           calendarFechaMes.setTime(calendar.getTime());
/* 369 */           calendarFechaMes.set(5, formaPago.getDiaFijoPago().intValue());
/* 370 */           if (calendar.getTimeInMillis() > calendarFechaMes.getTimeInMillis()) {
/* 371 */             calendarFechaMes.add(2, 1);
/*     */           }
/*     */         }
/*     */ 
/* 375 */         if ((calendarFechaMes.get(5) < formaPago.getDiaFijoPago().intValue()) && (calendarFechaMes.get(2) == 2))
/*     */         {
/* 377 */           calendarFechaMes.set(2, 1);
/* 378 */           calendarFechaMes.set(5, 28);
/* 379 */           if (i == 0) {
/* 380 */             calendar.setTime(calendarFechaMes.getTime());
/*     */           }
/*     */         }
/* 383 */         String num = Integer.toString(i + 1) + "/" + Integer.toString(formaPago.getNumeroPagos().intValue());
/* 384 */         TipoVencimiento vencimiento = new TipoVencimiento();
/* 385 */         vencimiento.setFecha(dateToString(calendarFechaMes.getTime()));
/* 386 */         vencimiento.setEjercicio(year);
/* 387 */         vencimiento.setFactura(facturaContable.getNumero());
/* 388 */         vencimiento.setFechaf(facturaContable.getFecha());
/* 389 */         vencimiento.setCuenta(cuentaTerceros);
/* 390 */         vencimiento.setImporte(importeVencimiento);
/* 391 */         vencimiento.setNum(num);
/* 392 */         vencimiento.setPagado(false);
/* 393 */         vencimiento.setCuentap(subcuentaPago.getCodigo());
/* 394 */         vencimientos.add(vencimiento);
/*     */       }
/*     */     ManejoVencimientos mV;
/* 398 */     if (vencimientos.size() > 0) {
/* 399 */       mV = new ManejoVencimientos(Inicio.getCEmpresa());
/* 400 */       for (TipoVencimiento tipoVencimiento :(List<TipoVencimiento>)  vencimientos) {
/* 401 */         mV.crear(this.emitida, tipoVencimiento);
/*     */       }
/*     */     }
/* 404 */     if (this.emitida)
/* 405 */       Inicio.frame.renovarTabla(2);
/*     */     else
/* 407 */       Inicio.frame.renovarTabla(1);
/*     */   }
/*     */ 
/*     */   private int cuentaEfectos(int cuenta)
/*     */   {
/* 412 */     String cadenaSubcuenta = Integer.toString(cuenta);
/* 413 */     String cadenaGrupo = cadenaSubcuenta.substring(0, 2);
/* 414 */     String cadenaFin = cadenaSubcuenta.substring(3);
/* 415 */     if (cadenaGrupo.equals("40"))
/* 416 */       cadenaSubcuenta = "401" + cadenaFin;
/* 417 */     else if (cadenaGrupo.equals("41"))
/* 418 */       cadenaSubcuenta = "411" + cadenaFin;
/* 419 */     else if (cadenaGrupo.equals("43"))
/* 420 */       cadenaSubcuenta = "431" + cadenaFin;
/* 421 */     else if (cadenaGrupo.equals("44")) {
/* 422 */       cadenaSubcuenta = "441" + cadenaFin;
/*     */     }
/* 424 */     int cuentaEfectos = Integer.parseInt(cadenaSubcuenta);
/* 425 */     ManejoSubcuentas mS = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 426 */     if (mS.existeSubcuenta(cuentaEfectos)) {
/* 427 */       return cuentaEfectos;
/*     */     }
/* 429 */     return -1;
/*     */   }
/*     */ 
/*     */   private String dateToString(Date date) {
/* 433 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
/* 434 */     return sdf.format(date);
/*     */   }
/*     */ 
/*     */   public boolean isContabilizada() {
/* 438 */     return this.contabilizada;
/*     */   }
/*     */ 
/*     */   private void setContabilizada(boolean contabilizada) {
/* 442 */     this.contabilizada = contabilizada;
/*     */   }
/*     */ 
/*     */   public boolean isAlmacenada() {
/* 446 */     return this.almacenada;
/*     */   }
/*     */ 
/*     */   public void setAlmacenada(boolean almacenada) {
/* 450 */     this.almacenada = almacenada;
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/* 462 */     this.jPanel1 = new JPanel();
/* 463 */     this.pasarAEfectos = new JCheckBox();
/* 464 */     this.jLabel2 = new JLabel();
/* 465 */     this.comboSubcuentaCobro = new JComboBox();
/* 466 */     this.jLabel1 = new JLabel();
/* 467 */     this.comboSubcuentas = new JComboBox();
/* 468 */     this.crearVencimientos = new JCheckBox();
/* 469 */     this.botonContabilizar = new JButton();
/* 470 */     this.jButton1 = new JButton();
/* 471 */     this.soloMarcarContabilizada = new JCheckBox();
/* 472 */     this.pasarAAlmacen = new JCheckBox();
/* 473 */     this.jLabel4 = new JLabel();
/* 474 */     this.comboAlmacenes = new JComboBox();
/* 475 */     this.jLabel3 = new JLabel();
/*     */ 
/* 477 */     setDefaultCloseOperation(2);
/* 478 */     setTitle("Contabilizar factura");
/*     */ 
/* 480 */     this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/*     */ 
/* 482 */     this.pasarAEfectos.setText("Pasar a efectos comerciales");
/* 483 */     this.pasarAEfectos.setEnabled(false);
/*     */ 
/* 485 */     this.jLabel2.setText("Cuenta de cobro");
/*     */ 
/* 487 */     this.jLabel1.setText("Cuenta de retenciones");
/*     */ 
/* 489 */     this.crearVencimientos.setText("Crear vencimientos");
/* 490 */     this.crearVencimientos.addChangeListener(new ChangeListener() {
/*     */       public void stateChanged(ChangeEvent evt) {
/* 492 */         ContabilizarMultiplesFacturasJDialog.this.crearVencimientosStateChanged(evt);
/*     */       }
/*     */     });
/* 496 */     this.botonContabilizar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/D16.png")));
/* 497 */     this.botonContabilizar.setText("Contabilizar");
/* 498 */     this.botonContabilizar.setHorizontalTextPosition(2);
/* 499 */     this.botonContabilizar.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 501 */         ContabilizarMultiplesFacturasJDialog.this.botonContabilizarActionPerformed(evt);
/*     */       }
/*     */     });
/* 505 */     this.jButton1.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/cancel.png")));
/* 506 */     this.jButton1.setText("Cancelar");
/* 507 */     this.jButton1.setHorizontalTextPosition(2);
/* 508 */     this.jButton1.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 510 */         ContabilizarMultiplesFacturasJDialog.this.jButton1ActionPerformed(evt);
/*     */       }
/*     */     });
/* 514 */     this.soloMarcarContabilizada.setText("Sólo marcar como Contabilizada");
/* 515 */     this.soloMarcarContabilizada.setToolTipText("Sólo marca la factura como contabilizada. No introduce nada en la contabilidad");
/*     */ 
/* 517 */     ResourceBundle bundle = ResourceBundle.getBundle("internationalization/Mensajes");
/* 518 */     this.pasarAAlmacen.setText(bundle.getString("updatealmacen"));
/*     */ 
/* 520 */     this.jLabel4.setText(bundle.getString("almacen"));
/*     */ 
/* 522 */     this.jLabel3.setText("(Sólo para facturas que no hayan sido almacenadas)");
/*     */ 
/* 524 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 525 */     this.jPanel1.setLayout(jPanel1Layout);
/* 526 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().add(29, 29, 29).add(this.soloMarcarContabilizada).add(154, 154, 154)).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().add(27, 27, 27).add(jPanel1Layout.createParallelGroup(1).add(this.pasarAEfectos).add(jPanel1Layout.createSequentialGroup().add(this.jLabel2).addPreferredGap(1).add(this.comboSubcuentaCobro, 0, 223, 32767)))).add(this.crearVencimientos).add(jPanel1Layout.createSequentialGroup().add(this.jLabel1).addPreferredGap(0).add(this.comboSubcuentas, -2, 213, -2))).add(34, 34, 34)).add(jPanel1Layout.createSequentialGroup().add(this.pasarAAlmacen).addContainerGap(172, 32767)).add(jPanel1Layout.createSequentialGroup().add(29, 29, 29).add(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().add(this.jLabel4).add(18, 18, 18).add(this.comboAlmacenes, -2, 201, -2)).add(this.jLabel3)).add(64, 64, 64)).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(this.botonContabilizar).addPreferredGap(0, 79, 32767).add(this.jButton1).add(148, 148, 148)));
/*     */ 
/* 568 */     jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(this.soloMarcarContabilizada, -2, 33, -2).addPreferredGap(0).add(jPanel1Layout.createParallelGroup(3).add(this.jLabel1).add(this.comboSubcuentas, -2, -1, -2)).addPreferredGap(1).add(this.crearVencimientos).addPreferredGap(0).add(jPanel1Layout.createParallelGroup(3).add(this.jLabel2).add(this.comboSubcuentaCobro, -2, -1, -2)).addPreferredGap(0).add(this.pasarAEfectos).add(18, 18, 18).add(this.pasarAAlmacen).addPreferredGap(0).add(this.jLabel3).addPreferredGap(1).add(jPanel1Layout.createParallelGroup(3).add(this.jLabel4).add(this.comboAlmacenes, -2, -1, -2)).add(18, 18, 18).add(jPanel1Layout.createParallelGroup(3).add(this.jButton1).add(this.botonContabilizar)).addContainerGap(-1, 32767)));
/*     */ 
/* 600 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 601 */     getContentPane().setLayout(layout);
/* 602 */     layout.setHorizontalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(this.jPanel1, -1, -1, 32767).addContainerGap()));
/*     */ 
/* 609 */     layout.setVerticalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(this.jPanel1, -1, -1, 32767).addContainerGap()));
/*     */ 
/* 617 */     pack();
/*     */   }
/*     */ 
/*     */   private void crearVencimientosStateChanged(ChangeEvent evt) {
/* 621 */     if (this.crearVencimientos.isSelected())
/* 622 */       this.pasarAEfectos.setEnabled(true);
/*     */     else
/* 624 */       this.pasarAEfectos.setEnabled(false);
/*     */   }
/*     */ 
/*     */   private void jButton1ActionPerformed(ActionEvent evt)
/*     */   {
/* 629 */     dispose();
/*     */   }
/*     */ 
/*     */   private void botonContabilizarActionPerformed(ActionEvent evt) {
/* 633 */     ejecutarProceso();
/* 634 */     JOptionPane.showMessageDialog(Inicio.frame, "Proceso terminado", "Información", 1);
/* 635 */     this.aC.cerrarConexion();
/* 636 */     this.mF.cerrarRs();
/* 637 */     this.mA.cerrarRs();
/* 638 */     this.mAp.cerrarRs();
/* 639 */     dispose();
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
/* 665 */       this.subcuenta = subcuenta;
/* 666 */       this.tipoIVA = tipoIVA;
/* 667 */       this.base = base;
/*     */     }
/*     */ 
/*     */     public double getBase() {
/* 671 */       return this.base;
/*     */     }
/*     */ 
/*     */     public int getSubcuenta() {
/* 675 */       return this.subcuenta;
/*     */     }
/*     */ 
/*     */     public TipoIVA getTipoIVA() {
/* 679 */       return this.tipoIVA;
/*     */     }
/*     */ 
/*     */     public void setBase(double base) {
/* 683 */       this.base = base;
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     facturacion.view.ContabilizarMultiplesFacturasJDialog
 * JD-Core Version:    0.6.2
 */