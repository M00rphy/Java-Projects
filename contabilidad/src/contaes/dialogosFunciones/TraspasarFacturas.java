/*     */ package contaes.dialogosFunciones;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.MarcoInicio;
/*     */ import contaes.Puente;
/*     */ import contaes.calendario.ICalendarTextField;
/*     */ import contaes.manejoDatos.ManejoEmpresas;
/*     */ import contaes.manejoDatos.auxiliar.MySQLConection;
/*     */ import facturacion.control.FacturaControl;
/*     */ import facturacion.control.LineaFacturaControl;
/*     */ import facturacion.model.Factura;
/*     */ import facturacion.model.FacturaTotal;
/*     */ import facturacion.model.LineaFactura;
/*     */ import java.awt.Container;
/*     */ import java.awt.Frame;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
import java.util.List;
/*     */ import java.util.ResourceBundle;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.DefaultComboBoxModel;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ import org.jdesktop.layout.GroupLayout;
/*     */ import org.jdesktop.layout.GroupLayout.ParallelGroup;
/*     */ import org.jdesktop.layout.GroupLayout.SequentialGroup;
/*     */ 
/*     */ public class TraspasarFacturas extends JDialog
/*     */ {
/*     */   private ManejoEmpresas mE;
/*     */   private String ejercicio;
/*     */   private int empresa;
/*     */   private boolean ventas;
/*     */   private JButton botonCopiarBorrar;
/*     */   private ICalendarTextField campoFechaFin;
/*     */   private ICalendarTextField campoFechaIni;
/*     */   private JTextField campoFin;
/*     */   private JTextField campoInicio;
/*     */   private JComboBox comboEmpresas;
/*     */   private JButton jButton1;
/*     */   private JButton jButton2;
/*     */   private JLabel jLabel1;
/*     */   private JLabel jLabel2;
/*     */   private JLabel jLabel3;
/*     */   private JLabel jLabel4;
/*     */   private JLabel jLabel5;
/*     */   private JLabel jLabel6;
/*     */   private JLabel jLabel7;
/*     */   private JLabel jLabel8;
/*     */   private JPanel jPanel1;
/*     */ 
/*     */   public TraspasarFacturas(Frame parent, boolean modal, boolean ventas)
/*     */   {
/*  43 */     super(parent, modal);
/*  44 */     this.ventas = ventas;
/*  45 */     initComponents();
/*  46 */     this.botonCopiarBorrar.setHorizontalTextPosition(2);
/*     */ 
/*  48 */     this.mE = new ManejoEmpresas(Inicio.getCGeneral(), Inicio.p.getEmpresa());
/*     */ 
/*  50 */     this.comboEmpresas.setModel(modeloListaEmpresas());
/*     */ 
/*  52 */     this.ejercicio = Inicio.p.getEjercicio();
/*  53 */     if (this.comboEmpresas.getItemCount() > 0) {
/*  54 */       String temp = (String)this.comboEmpresas.getItemAt(0);
/*  55 */       this.empresa = this.mE.getIdEmpresa(temp);
/*     */     } else {
/*  57 */       this.empresa = Inicio.p.getEmpresa();
/*     */     }
/*     */   }
/*     */ 
/*     */   private DefaultComboBoxModel modeloListaEmpresas() {
/*  62 */     DefaultComboBoxModel modelo = new DefaultComboBoxModel();
/*  63 */     LinkedList lista = this.mE.listaEmpresas();
/*  64 */     if (lista.size() > 0) {
/*  65 */       for (String emp :(List<String>)  lista) {
/*  66 */         modelo.addElement(emp);
/*     */       }
/*     */     }
/*  69 */     return modelo;
/*     */   }
/*     */ 
/*     */   private boolean copiarFacturas(boolean borrar) {
/*  73 */     if (this.empresa != Inicio.p.getEmpresa()) {
/*  74 */       int ej = Integer.parseInt(this.ejercicio);
/*  75 */       String situacionEj = this.mE.crearEjercicio1(this.empresa, ej);
/*  76 */       if (situacionEj.equals("existe")) {
/*  77 */         ArrayList facturas = null;
/*  78 */         ArrayList listaBorrar = null;
/*  79 */         String numeroIni = this.campoInicio.getText();
/*  80 */         String numeroFin = this.campoFin.getText();
/*  81 */         if ((!numeroIni.equals("")) && (!numeroFin.equals(""))) {
/*  82 */           facturas = recopilarFacturas(numeroIni, numeroFin);
/*  83 */           if (facturas != null) {
/*  84 */             if (borrar) {
/*  85 */               FacturaControl fC = new FacturaControl(Inicio.getCFacturacion(), this.ventas);
/*     */               try {
/*  87 */                 listaBorrar = fC.facturasEntreNumeros(numeroIni, numeroFin);
/*     */               } catch (Exception ex) {
/*  89 */                 Logger.getLogger(TraspasarFacturas.class.getName()).log(Level.SEVERE, null, ex);
/*     */               }
/*  91 */               fC.cerrarRs();
/*     */             }
/*  93 */             crearFacturas(this.empresa, facturas);
/*     */           } else {
/*  95 */             return false;
/*     */           }
/*     */         } else {
/*  98 */           Date fechaIni = this.campoFechaIni.getDate();
/*  99 */           Date fechaFin = this.campoFechaFin.getDate();
/* 100 */           if ((fechaIni != null) && (fechaFin != null)) {
/* 101 */             facturas = recopilarFacturas(fechaIni, fechaFin);
/* 102 */             if (facturas != null) {
/* 103 */               if (borrar) {
/* 104 */                 FacturaControl fC = new FacturaControl(Inicio.getCFacturacion(), this.ventas);
/*     */                 try {
/* 106 */                   listaBorrar = fC.facturasEntreFechas(fechaIni, fechaFin);
/*     */                 } catch (Exception ex) {
/* 108 */                   Logger.getLogger(TraspasarFacturas.class.getName()).log(Level.SEVERE, null, ex);
/*     */                 }
/* 110 */                 fC.cerrarRs();
/*     */               }
/* 112 */               crearFacturas(this.empresa, facturas);
/*     */             } else {
/* 114 */               return false;
/*     */             }
/*     */           } else {
/* 117 */             return false;
/*     */           }
/*     */         }
/* 120 */         if (borrar) {
/* 121 */           borrarFacturas(listaBorrar);
/* 122 */           if (this.ventas) {
/* 123 */             Inicio.frame.renovarTabla(7);
/*     */           }
/*     */           else {
/* 126 */             Inicio.frame.renovarTabla(8);
/*     */           }
/*     */         }
/* 129 */         return true;
/*     */       }
/* 131 */       showMensaje("No existe el ejercicio para la empresa seleccionada");
/* 132 */       return false;
/*     */     }
/*     */ 
/* 135 */     showMensaje("Debe seleccionar una empresa\ndistinta a la que está en uso.");
/* 136 */     return false;
/*     */   }
/*     */ 
/*     */   private ArrayList<FacturaTotal> recopilarFacturas(String inicio, String fin)
/*     */   {
/* 141 */     FacturaControl fC = new FacturaControl(Inicio.getCFacturacion(), this.ventas);
/* 142 */     if (!fC.isRangoEnEjercicio(inicio, fin, this.ejercicio)) {
/* 143 */       JOptionPane.showMessageDialog(Inicio.frame, "El ejercicio de alguna factura del rango\nespecificado no coincide con el ejercicio actual", "Error", 0);
/*     */ 
/* 145 */       return null;
/*     */     }
/* 147 */     ArrayList facturas = new ArrayList();
/*     */ 
/* 149 */     ArrayList listaTemp = null;
/*     */     try {
/* 151 */       listaTemp = fC.facturasEntreNumeros(inicio, fin);
/*     */     } catch (Exception ex) {
/* 153 */       Logger.getLogger(TraspasarFacturas.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/* 155 */     fC.cerrarRs();
/* 156 */     if (listaTemp == null) {
/* 157 */       JOptionPane.showMessageDialog(Inicio.frame, "No hay facturas en\nel rango indicado", "Error", 0);
/*     */ 
/* 159 */       return null;
/*     */     }
/* 161 */     LineaFacturaControl lFC = new LineaFacturaControl(Inicio.getCFacturacion(), this.ventas);
/* 162 */     for (Factura factura :(List<Factura>) listaTemp) {
/* 163 */       FacturaTotal facturaTotal = null;
/*     */       try {
/* 165 */         facturaTotal = new FacturaTotal(factura.getId(), factura.getNumero(), factura.getCliente(), factura.getFecha(), factura.getRetencion(), factura.isRecargo(), factura.getFormaPago(), factura.getBase(), factura.getIva(), false, factura.isIsAlmacenada(), lFC.lineas(factura.getId()));
/*     */       }
/*     */       catch (Exception ex)
/*     */       {
/* 170 */         Logger.getLogger(TraspasarFacturas.class.getName()).log(Level.SEVERE, null, ex);
/*     */       }
/* 172 */       if (facturaTotal != null) {
/* 173 */         facturas.add(facturaTotal);
/*     */       }
/*     */     }
/* 176 */     lFC.cerrarRs();
/* 177 */     return facturas;
/*     */   }
/*     */ 
/*     */   private ArrayList<FacturaTotal> recopilarFacturas(Date fechaIni, Date fechaFin) {
/* 181 */     GregorianCalendar gc = new GregorianCalendar();
/* 182 */     gc.setTime(fechaIni);
/* 183 */     String ej = Integer.toString(gc.get(1));
/* 184 */     if (!this.ejercicio.equals(ej)) {
/* 185 */       JOptionPane.showMessageDialog(Inicio.frame, "El ejercicio de la fecha inicial\nno coincide con el ejercicio actual", "Error", 0);
/*     */ 
/* 187 */       return null;
/*     */     }
/* 189 */     gc.setTime(fechaFin);
/* 190 */     ej = Integer.toString(gc.get(1));
/* 191 */     if (!this.ejercicio.equals(ej)) {
/* 192 */       JOptionPane.showMessageDialog(Inicio.frame, "El ejercicio de la fecha final\nno coincide con el ejercicio actual", "Error", 0);
/*     */ 
/* 194 */       return null;
/*     */     }
/* 196 */     ArrayList facturas = new ArrayList();
/* 197 */     FacturaControl fC = new FacturaControl(Inicio.getCFacturacion(), this.ventas);
/* 198 */     ArrayList listaTemp = null;
/*     */     try {
/* 200 */       listaTemp = fC.facturasEntreFechas(fechaIni, fechaFin);
/*     */     } catch (Exception ex) {
/* 202 */       Logger.getLogger(TraspasarFacturas.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/* 204 */     fC.cerrarRs();
/* 205 */     if (listaTemp == null) {
/* 206 */       JOptionPane.showMessageDialog(Inicio.frame, "No hay facturas para\nel periodo indicado", "Error", 0);
/*     */ 
/* 208 */       return null;
/*     */     }
/* 210 */     LineaFacturaControl lFC = new LineaFacturaControl(Inicio.getCFacturacion(), this.ventas);
/* 211 */     for (Factura factura :(List<Factura>) listaTemp) {
/* 212 */       FacturaTotal facturaTotal = null;
/*     */       try {
/* 214 */         facturaTotal = new FacturaTotal(factura.getId(), factura.getNumero(), factura.getCliente(), factura.getFecha(), factura.getRetencion(), factura.isRecargo(), factura.getFormaPago(), factura.getBase(), factura.getIva(), false, factura.isIsAlmacenada(), lFC.lineas(factura.getId()));
/*     */       }
/*     */       catch (Exception ex)
/*     */       {
/* 219 */         Logger.getLogger(TraspasarFacturas.class.getName()).log(Level.SEVERE, null, ex);
/*     */       }
/* 221 */       if (facturaTotal != null) {
/* 222 */         facturas.add(facturaTotal);
/*     */       }
/*     */     }
/* 225 */     lFC.cerrarRs();
/* 226 */     return facturas;
/*     */   }
/*     */ 
/*     */   private void borrarFacturas(ArrayList<Factura> facturas)
/*     */   {
/* 256 */     FacturaControl fC = new FacturaControl(Inicio.getCFacturacion(), this.ventas);
/* 257 */     for (Factura factura : facturas) {
/*     */       try {
/* 259 */         fC.borrar(factura);
/*     */       } catch (Exception ex) {
/* 261 */         Logger.getLogger(TraspasarFacturas.class.getName()).log(Level.SEVERE, null, ex);
/*     */       }
/*     */     }
/* 264 */     fC.cerrarRs();
/* 265 */     LineaFacturaControl lFC = new LineaFacturaControl(Inicio.getCFacturacion(), this.ventas);
/* 266 */     for (Factura factura : facturas) {
/*     */       try {
/* 268 */         lFC.borrarPorFactura(factura.getId().intValue());
/*     */       } catch (Exception ex) {
/* 270 */         Logger.getLogger(TraspasarFacturas.class.getName()).log(Level.SEVERE, null, ex);
/*     */       }
/*     */     }
/* 273 */     lFC.cerrarRs();
/*     */   }
/*     */ 
/*     */   private boolean crearFacturas(int empresa, ArrayList<FacturaTotal> facturas) {
/* 277 */     boolean isOk = false;
/* 278 */     MySQLConection conex = new MySQLConection(empresa);
/* 279 */     FacturaControl fC = new FacturaControl(conex, this.ventas);
/* 280 */     for (FacturaTotal facturaTotal : facturas) {
/* 281 */       boolean borrarOld = false;
/*     */       try {
/* 283 */         int oldId = fC.existeFactura(facturaTotal.getFacturaSimple());
/* 284 */         if (oldId != -1) {
/* 285 */           Object[] options = { "Copiar", "Reemplazar", "Cancelar" };
/*     */ 
/* 288 */           int n = JOptionPane.showOptionDialog(Inicio.frame, "Ya existe una factura de número " + facturaTotal.getNumero() + "\n¿Qué desea hacer?", "Factura existente", 1, 3, null, options, options[2]);
/*     */ 
/* 298 */           if ((n != -1) && (n == 2)) {
/*     */             break;
/*     */           }
/* 301 */           if (n == 1)
/*     */           {
/* 303 */             borrarOld = true;
/* 304 */             facturaTotal.setId(Integer.valueOf(oldId));
/* 305 */             fC.modificar(facturaTotal.getFacturaSimple());
/*     */           }
/* 307 */           else if (n == 0)
/*     */           {
/* 309 */             facturaTotal.setId(fC.crear(facturaTotal.getFacturaSimple()));
/*     */           }
/*     */         }
/*     */         else {
/* 313 */           facturaTotal.setId(fC.crear(facturaTotal.getFacturaSimple()));
/*     */         }
/*     */       } catch (Exception ex) {
/* 316 */         Logger.getLogger(TraspasarFacturas.class.getName()).log(Level.SEVERE, null, ex);
/*     */       }
/*     */ 
/* 320 */       facturaTotal.setContabilizada(borrarOld);
/*     */     }
/* 322 */     fC.cerrarRs();
/*     */ FacturaTotal facturaTotal;
/* 324 */     LineaFacturaControl lFC = new LineaFacturaControl(conex, this.ventas);
/* 325 */     for (Iterator i$ = facturas.iterator(); i$.hasNext(); ) { facturaTotal = (FacturaTotal)i$.next();
/* 326 */       boolean borrarOld = facturaTotal.isContabilizada();
/* 327 */       facturaTotal.setContabilizada(false);
/* 328 */       if (borrarOld) {
/*     */         try {
/* 330 */           lFC.borrarPorFactura(facturaTotal.getId().intValue());
/*     */         } catch (Exception ex) {
/* 332 */           Logger.getLogger(TraspasarFacturas.class.getName()).log(Level.SEVERE, null, ex);
/*     */         }
/*     */       }
/* 335 */       ArrayList lineas = facturaTotal.getLineas();
/* 336 */       if ((lineas != null) && (lineas.size() > 0))
/* 337 */         for (LineaFactura lineaFactura : (List<LineaFactura>)lineas) {
/* 338 */           lineaFactura.setIdFactura(facturaTotal.getId());
/*     */           try {
/* 340 */             lFC.crear(lineaFactura);
/*     */           } catch (Exception ex) {
/* 342 */             Logger.getLogger(TraspasarFacturas.class.getName()).log(Level.SEVERE, null, ex);
/*     */           }
/*     */         }
/*     */     }
/*     */    // FacturaTotal facturaTotal;
/* 347 */     isOk = true;
/* 348 */     return isOk;
/*     */   }
/*     */ 
/*     */   private void showMensaje(String mensaje) {
/* 352 */     JOptionPane.showMessageDialog(getContentPane(), mensaje, "Error", 0);
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/* 365 */     this.jPanel1 = new JPanel();
/* 366 */     this.jLabel2 = new JLabel();
/* 367 */     this.comboEmpresas = new JComboBox();
/* 368 */     this.jLabel3 = new JLabel();
/* 369 */     this.jLabel4 = new JLabel();
/* 370 */     this.campoInicio = new JTextField();
/* 371 */     this.campoFin = new JTextField();
/* 372 */     this.jLabel5 = new JLabel();
/* 373 */     this.jButton2 = new JButton();
/* 374 */     this.jButton1 = new JButton();
/* 375 */     this.botonCopiarBorrar = new JButton();
/* 376 */     this.jLabel1 = new JLabel();
/* 377 */     this.jLabel6 = new JLabel();
/* 378 */     this.jLabel7 = new JLabel();
/* 379 */     this.campoFechaIni = new ICalendarTextField();
/* 380 */     this.jLabel8 = new JLabel();
/* 381 */     this.campoFechaFin = new ICalendarTextField();
/*     */ 
/* 383 */     setDefaultCloseOperation(2);
/* 384 */     ResourceBundle bundle = ResourceBundle.getBundle("internationalization/Mensajes");
/* 385 */     setTitle(bundle.getString("copiarasientos"));
/*     */ 
/* 387 */     this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/*     */ 
/* 389 */     this.jLabel2.setText("Seleccione empresa de destino:");
/*     */ 
/* 391 */     this.comboEmpresas.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 393 */         TraspasarFacturas.this.comboEmpresasActionPerformed(evt);
/*     */       }
/*     */     });
/* 397 */     this.jLabel3.setText("Facturas a copiar:");
/*     */ 
/* 399 */     this.jLabel4.setText("Inicial");
/*     */ 
/* 401 */     this.campoInicio.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 403 */         TraspasarFacturas.this.campoInicioActionPerformed(evt);
/*     */       }
/*     */     });
/* 407 */     this.campoFin.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 409 */         TraspasarFacturas.this.campoFinActionPerformed(evt);
/*     */       }
/*     */     });
/* 413 */     this.jLabel5.setText("Final");
/*     */ 
/* 415 */     this.jButton2.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/cancel22.png")));
/* 416 */     this.jButton2.setText(bundle.getString("cancelar"));
/* 417 */     this.jButton2.setHorizontalTextPosition(2);
/* 418 */     this.jButton2.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 420 */         TraspasarFacturas.this.jButton2ActionPerformed(evt);
/*     */       }
/*     */     });
/* 424 */     this.jButton1.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/Traspaso.png")));
/* 425 */     this.jButton1.setText(bundle.getString("copiar"));
/* 426 */     this.jButton1.setHorizontalTextPosition(2);
/* 427 */     this.jButton1.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 429 */         TraspasarFacturas.this.jButton1ActionPerformed(evt);
/*     */       }
/*     */     });
/* 433 */     this.botonCopiarBorrar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/TraspasoBorro.png")));
/* 434 */     this.botonCopiarBorrar.setText("Copiar y borrar");
/* 435 */     this.botonCopiarBorrar.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 437 */         TraspasarFacturas.this.botonCopiarBorrarActionPerformed(evt);
/*     */       }
/*     */     });
/* 441 */     this.jLabel1.setText("Por número de factura");
/*     */ 
/* 443 */     this.jLabel6.setText("Por fecha de factura");
/*     */ 
/* 445 */     this.jLabel7.setText("Inicial");
/*     */ 
/* 447 */     this.jLabel8.setText("Final");
/*     */ 
/* 449 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 450 */     this.jPanel1.setLayout(jPanel1Layout);
/* 451 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(jPanel1Layout.createParallelGroup(1).add(this.botonCopiarBorrar).add(this.jButton1).add(jPanel1Layout.createSequentialGroup().add(170, 170, 170).add(this.jButton2)).add(jPanel1Layout.createSequentialGroup().add(24, 24, 24).add(this.jLabel1)).add(jPanel1Layout.createParallelGroup(1, false).add(this.jLabel3).add(this.jLabel2).add(this.comboEmpresas, 0, -1, 32767)).add(jPanel1Layout.createSequentialGroup().add(this.jLabel7).addPreferredGap(0).add(this.campoFechaIni, -2, 110, -2).addPreferredGap(0).add(this.jLabel8).addPreferredGap(0).add(this.campoFechaFin, -2, 110, -2)).add(jPanel1Layout.createSequentialGroup().add(jPanel1Layout.createParallelGroup(2).add(this.jLabel6).add(jPanel1Layout.createSequentialGroup().add(this.jLabel4).add(18, 18, 18).add(this.campoInicio, -2, 92, -2))).add(22, 22, 22).add(this.jLabel5).addPreferredGap(0).add(this.campoFin, -2, 89, -2))).addContainerGap(-1, 32767)));
/*     */ 
/* 489 */     jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(this.jLabel2).addPreferredGap(1).add(this.comboEmpresas, -2, -1, -2).add(18, 18, 18).add(this.jLabel3).addPreferredGap(0).add(this.jLabel1).addPreferredGap(0).add(jPanel1Layout.createParallelGroup(3).add(this.jLabel4).add(this.campoInicio, -2, -1, -2).add(this.campoFin, -2, -1, -2).add(this.jLabel5)).add(13, 13, 13).add(jPanel1Layout.createParallelGroup(2).add(jPanel1Layout.createSequentialGroup().add(this.jLabel6).addPreferredGap(1).add(jPanel1Layout.createParallelGroup(2).add(this.jLabel7).add(this.campoFechaIni, -2, -1, -2))).add(this.jLabel8).add(this.campoFechaFin, -2, -1, -2)).add(18, 18, 18).add(this.jButton1).add(10, 10, 10).add(jPanel1Layout.createParallelGroup(3).add(this.botonCopiarBorrar).add(this.jButton2)).addContainerGap(-1, 32767)));
/*     */ 
/* 525 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 526 */     getContentPane().setLayout(layout);
/* 527 */     layout.setHorizontalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(this.jPanel1, -2, -1, -2).addContainerGap(-1, 32767)));
/*     */ 
/* 534 */     layout.setVerticalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(this.jPanel1, -1, -1, 32767).addContainerGap()));
/*     */ 
/* 542 */     pack();
/*     */   }
/*     */ 
/*     */   private void jButton1ActionPerformed(ActionEvent evt) {
/* 546 */     if (copiarFacturas(false)) {
/* 547 */       JOptionPane.showMessageDialog(getContentPane(), "Copia realizada", "Información", 1);
/* 548 */       dispose();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void jButton2ActionPerformed(ActionEvent evt) {
/* 553 */     dispose();
/*     */   }
/*     */ 
/*     */   private void comboEmpresasActionPerformed(ActionEvent evt) {
/* 557 */     if (this.comboEmpresas.getSelectedIndex() != -1) {
/* 558 */       String temp = (String)this.comboEmpresas.getSelectedItem();
/* 559 */       this.empresa = this.mE.getIdEmpresa(temp);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void campoInicioActionPerformed(ActionEvent evt) {
/* 564 */     this.campoFin.requestFocus();
/*     */   }
/*     */ 
/*     */   private void campoFinActionPerformed(ActionEvent evt) {
/* 568 */     this.jButton1.requestFocus();
/*     */   }
/*     */ 
/*     */   private void botonCopiarBorrarActionPerformed(ActionEvent evt) {
/* 572 */     if (copiarFacturas(true)) {
/* 573 */       JOptionPane.showMessageDialog(getContentPane(), "Copia realizada", "Información", 1);
/* 574 */       dispose();
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.dialogosFunciones.TraspasarFacturas
 * JD-Core Version:    0.6.2
 */