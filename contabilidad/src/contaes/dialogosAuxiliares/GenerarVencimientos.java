/*     */ package contaes.dialogosAuxiliares;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.MarcoInicio;
/*     */ import contaes.Puente;
/*     */ import contaes.manejoDatos.ManejoAgenda;
/*     */ import contaes.manejoDatos.ManejoApuntes;
/*     */ import contaes.manejoDatos.ManejoAsientos;
/*     */ import contaes.manejoDatos.ManejoFormasPago;
/*     */ import contaes.manejoDatos.ManejoSubcuentas;
/*     */ import contaes.manejoDatos.ManejoVencimientos;
/*     */ import contaes.manejoDatos.TipoFactura;
/*     */ import contaes.manejoDatos.TipoFormaPago;
/*     */ import contaes.manejoDatos.TipoSubcuenta;
/*     */ import contaes.manejoDatos.TipoVencimiento;
/*     */ import java.awt.Container;
/*     */ import java.awt.Frame;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
import java.util.List;
/*     */ import javax.swing.DefaultComboBoxModel;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import org.jdesktop.layout.GroupLayout;
/*     */ import org.jdesktop.layout.GroupLayout.ParallelGroup;
/*     */ import org.jdesktop.layout.GroupLayout.SequentialGroup;
/*     */ 
/*     */ public class GenerarVencimientos extends JDialog
/*     */ {
/*     */   TipoFactura factura;
/*     */   boolean aPagar;
/*     */   private JComboBox comboCuentasTesoreria;
/*     */   private JComboBox comboFormasPago;
/*     */   private JCheckBox isEfectos;
/*     */   private JButton jButton1;
/*     */   private JButton jButton2;
/*     */   private JLabel jLabel1;
/*     */   private JLabel jLabel2;
/*     */ 
/*     */   public GenerarVencimientos(Frame parent, String title, boolean modal, TipoFactura factura, boolean aPagar)
/*     */   {
/*  43 */     super(parent, title, modal);
/*  44 */     this.factura = factura;
/*  45 */     this.aPagar = aPagar;
/*  46 */     initComponents();
/*     */ 
/*  48 */     this.comboFormasPago.setModel(modeloFormasPago());
/*  49 */     this.comboCuentasTesoreria.setModel(modeloCuentas());
/*     */ 
/*  51 */     colocarDatos();
/*     */   }
/*     */ 
/*     */   private DefaultComboBoxModel modeloFormasPago() {
/*  55 */     DefaultComboBoxModel modelo = new DefaultComboBoxModel();
/*  56 */     ManejoFormasPago mFP = new ManejoFormasPago(Inicio.getCGeneral());
/*  57 */     ArrayList listaFormas = mFP.getFormasPago();
/*  58 */     for (TipoFormaPago fPago :(List<TipoFormaPago>) listaFormas) {
/*  59 */       modelo.addElement(fPago);
/*     */     }
/*  61 */     return modelo;
/*     */   }
/*     */ 
/*     */   private DefaultComboBoxModel modeloCuentas() {
/*  65 */     DefaultComboBoxModel modelo = new DefaultComboBoxModel();
/*  66 */     ManejoSubcuentas mS = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  67 */     ArrayList tiposSubcuenta = new ArrayList();
/*  68 */     tiposSubcuenta = mS.listaSubcuentas(57000000, 57999999);
/*  69 */     for (TipoSubcuenta tipoSubcuenta :(List<TipoSubcuenta>) tiposSubcuenta) {
/*  70 */       modelo.addElement(tipoSubcuenta);
/*     */     }
/*  72 */     return modelo;
/*     */   }
/*     */ 
/*     */   private void colocarDatos() {
/*  76 */     if (this.factura != null) {
/*  77 */       ManejoAgenda mA = new ManejoAgenda(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  78 */       int idFP = mA.formaPago(Integer.toString(this.factura.getCuenta())).intValue();
/*  79 */       int cuentaP = mA.cuentaPago(Integer.toString(this.factura.getCuenta()));
/*  80 */       ManejoSubcuentas mS = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  81 */       TipoSubcuenta subcuentaPago = mS.datos(cuentaP);
/*  82 */       ManejoFormasPago mFP = new ManejoFormasPago(Inicio.getCGeneral());
/*  83 */       TipoFormaPago formaPago = mFP.getFormaPago(idFP);
/*  84 */       if (subcuentaPago != null) {
/*  85 */         this.comboCuentasTesoreria.setSelectedItem(subcuentaPago);
/*     */       }
/*  87 */       if (formaPago != null) {
/*  88 */         this.comboFormasPago.setSelectedItem(formaPago);
/*     */       }
/*  90 */       mA.cerrarRs();
/*  91 */       mS.cerrarRs();
/*  92 */       mFP.cerrarRs();
/*     */     }
/*     */   }
/*     */ 
/*     */   private boolean ejecutarAccionPrincipal() {
/*  97 */     TipoSubcuenta subcuentaPago = (TipoSubcuenta)this.comboCuentasTesoreria.getSelectedItem();
/*  98 */     TipoFormaPago formaPago = (TipoFormaPago)this.comboFormasPago.getSelectedItem();
/*  99 */     if ((this.factura != null) && (subcuentaPago != null) && (formaPago != null)) {
/* 100 */       boolean pasarEfectos = this.isEfectos.isSelected();
/* 101 */       int subcuenta = this.factura.getCuenta();
/* 102 */       if (pasarEfectos) {
/* 103 */         subcuenta = cuentaEfectos(subcuenta);
/* 104 */         if (subcuenta == -1) {
/* 105 */           String cadena = this.aPagar ? "Proveedor/Acreedor" : "Cliente/Deudor";
/* 106 */           JOptionPane.showMessageDialog(getContentPane(), "NO existe cuenta de Efectos comerciales\npara este " + cadena);
/*     */ 
/* 108 */           return false;
/*     */         }
/*     */ 
/* 111 */         ManejoAsientos asientoM = new ManejoAsientos(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 112 */         ManejoApuntes apunteM = new ManejoApuntes(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 113 */         int id_asto = -1;
/* 114 */         int num_asto = asientoM.nuevoNumero();
/* 115 */         id_asto = asientoM.crear(num_asto, this.factura.getFecha(), "", "");
/* 116 */         if (this.aPagar)
/*     */         {
/* 118 */           apunteM.crear(id_asto, this.factura.getCuenta(), "Traspaso a efectos comerciales", "D", this.factura.getTotal());
/* 119 */           apunteM.crear(id_asto, subcuenta, "Traspaso a efectos comerciales", "H", this.factura.getTotal());
/*     */         }
/*     */         else {
/* 122 */           apunteM.crear(id_asto, subcuenta, "Traspaso a efectos comerciales", "D", this.factura.getTotal());
/* 123 */           apunteM.crear(id_asto, this.factura.getCuenta(), "Traspaso a efectos comerciales", "H", this.factura.getTotal());
/*     */         }
/*     */       }
/* 126 */       int year = Integer.parseInt(this.factura.getFecha().substring(0, 4));
/* 127 */       int mes = Integer.parseInt(this.factura.getFecha().substring(4, 6));
/* 128 */       mes--;
/* 129 */       int dia = Integer.parseInt(this.factura.getFecha().substring(6));
/* 130 */       Calendar calendar = Calendar.getInstance();
/* 131 */       Calendar calendarFechaMes = Calendar.getInstance();
/* 132 */       calendar.set(year, mes, dia);
/* 133 */       ArrayList vencimientos = new ArrayList();
/* 134 */       double importeVencimiento = this.factura.getTotal() / formaPago.getNumeroPagos().intValue();
/*     */ 
/* 136 */       for (int i = 0; i < formaPago.getNumeroPagos().intValue(); i++)
/* 137 */         if ((formaPago.getDiaFijoPago() == null) || (formaPago.getDiaFijoPago().intValue() == 0)) {
/* 138 */           if (i == 0) {
/* 139 */             calendar.add(5, formaPago.getDiasPrimerPago().intValue());
/*     */           }
/* 142 */           else if (formaPago.getDiasEntrePagos().intValue() == 30) {
/* 143 */             calendar.add(2, 1);
/*     */           }
/*     */           else {
/* 146 */             calendar.add(5, formaPago.getDiasEntrePagos().intValue());
/*     */           }
/*     */ 
/* 149 */           String num = Integer.toString(i + 1) + "/" + Integer.toString(formaPago.getNumeroPagos().intValue());
/* 150 */           TipoVencimiento vencimiento = new TipoVencimiento();
/* 151 */           vencimiento.setFecha(dateToString(calendar.getTime()));
/* 152 */           vencimiento.setEjercicio(year);
/* 153 */           vencimiento.setFactura(this.factura.getNumero());
/* 154 */           vencimiento.setFechaf(this.factura.getFecha());
/* 155 */           vencimiento.setCuenta(this.factura.getCuenta());
/* 156 */           vencimiento.setImporte(importeVencimiento);
/* 157 */           vencimiento.setNum(num);
/* 158 */           vencimiento.setPagado(false);
/* 159 */           vencimiento.setCuentap(subcuentaPago.getCodigo());
/* 160 */           vencimientos.add(vencimiento);
/*     */         }
/*     */         else {
/* 163 */           if (i == 0) {
/* 164 */             calendar.add(5, formaPago.getDiasPrimerPago().intValue());
/* 165 */             calendarFechaMes.setTime(calendar.getTime());
/* 166 */             calendarFechaMes.set(5, formaPago.getDiaFijoPago().intValue());
/* 167 */             if (calendar.getTimeInMillis() > calendarFechaMes.getTimeInMillis()) {
/* 168 */               calendarFechaMes.add(2, 1);
/*     */             }
/* 170 */             calendar.setTime(calendarFechaMes.getTime());
/*     */           }
/*     */           else {
/* 173 */             calendar.add(5, formaPago.getDiasEntrePagos().intValue());
/* 174 */             calendarFechaMes.setTime(calendar.getTime());
/* 175 */             calendarFechaMes.set(5, formaPago.getDiaFijoPago().intValue());
/* 176 */             if (calendar.getTimeInMillis() > calendarFechaMes.getTimeInMillis()) {
/* 177 */               calendarFechaMes.add(2, 1);
/*     */             }
/*     */           }
/*     */ 
/* 181 */           if ((calendarFechaMes.get(5) < formaPago.getDiaFijoPago().intValue()) && (calendarFechaMes.get(2) == 2))
/*     */           {
/* 183 */             calendarFechaMes.set(2, 1);
/* 184 */             calendarFechaMes.set(5, 28);
/* 185 */             if (i == 0) {
/* 186 */               calendar.setTime(calendarFechaMes.getTime());
/*     */             }
/*     */           }
/* 189 */           String num = Integer.toString(i + 1) + "/" + Integer.toString(formaPago.getNumeroPagos().intValue());
/* 190 */           TipoVencimiento vencimiento = new TipoVencimiento();
/* 191 */           vencimiento.setFecha(dateToString(calendarFechaMes.getTime()));
/* 192 */           vencimiento.setEjercicio(year);
/* 193 */           vencimiento.setFactura(this.factura.getNumero());
/* 194 */           vencimiento.setFechaf(this.factura.getFecha());
/* 195 */           vencimiento.setCuenta(this.factura.getCuenta());
/* 196 */           vencimiento.setImporte(importeVencimiento);
/* 197 */           vencimiento.setNum(num);
/* 198 */           vencimiento.setPagado(false);
/* 199 */           vencimiento.setCuentap(subcuentaPago.getCodigo());
/* 200 */           vencimientos.add(vencimiento);
/*     */         }
/*     */       ManejoVencimientos mV;
/* 204 */       if (vencimientos.size() > 0) {
/* 205 */         mV = new ManejoVencimientos(Inicio.getCEmpresa());
/* 206 */         for (TipoVencimiento tipoVencimiento :(List<TipoVencimiento>)  vencimientos) {
/* 207 */           mV.crear(!this.aPagar, tipoVencimiento);
/*     */         }
/*     */       }
/* 210 */       if (this.aPagar)
/* 211 */         Inicio.frame.renovarTabla(1);
/*     */       else {
/* 213 */         Inicio.frame.renovarTabla(2);
/*     */       }
/* 215 */       return true;
/*     */     }
/* 217 */     return false;
/*     */   }
/*     */ 
/*     */   private int cuentaEfectos(int cuenta)
/*     */   {
/* 222 */     String cadenaSubcuenta = Integer.toString(cuenta);
/* 223 */     String cadenaGrupo = cadenaSubcuenta.substring(0, 2);
/* 224 */     String cadenaFin = cadenaSubcuenta.substring(3);
/* 225 */     if (cadenaGrupo.equals("40"))
/* 226 */       cadenaSubcuenta = "401" + cadenaFin;
/* 227 */     else if (cadenaGrupo.equals("41"))
/* 228 */       cadenaSubcuenta = "411" + cadenaFin;
/* 229 */     else if (cadenaGrupo.equals("43"))
/* 230 */       cadenaSubcuenta = "431" + cadenaFin;
/* 231 */     else if (cadenaGrupo.equals("44")) {
/* 232 */       cadenaSubcuenta = "441" + cadenaFin;
/*     */     }
/* 234 */     int cuentaEfectos = Integer.parseInt(cadenaSubcuenta);
/* 235 */     ManejoSubcuentas subcuentaM = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 236 */     if (subcuentaM.existeSubcuenta(cuentaEfectos)) {
/* 237 */       return cuentaEfectos;
/*     */     }
/* 239 */     return -1;
/*     */   }
/*     */ 
/*     */   private String dateToString(Date date) {
/* 243 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
/* 244 */     return sdf.format(date);
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/* 256 */     this.jLabel1 = new JLabel();
/* 257 */     this.comboFormasPago = new JComboBox();
/* 258 */     this.isEfectos = new JCheckBox();
/* 259 */     this.jLabel2 = new JLabel();
/* 260 */     this.comboCuentasTesoreria = new JComboBox();
/* 261 */     this.jButton1 = new JButton();
/* 262 */     this.jButton2 = new JButton();
/*     */ 
/* 264 */     setDefaultCloseOperation(2);
/*     */ 
/* 266 */     this.jLabel1.setText("Seleccione la forma de pago:");
/*     */ 
/* 268 */     this.isEfectos.setText("Pasar a efectos comerciales");
/*     */ 
/* 270 */     this.jLabel2.setText("Seleccione una cuenta de tesorería:");
/*     */ 
/* 272 */     this.jButton1.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/apply.png")));
/* 273 */     this.jButton1.setText("Generar");
/* 274 */     this.jButton1.setHorizontalTextPosition(2);
/* 275 */     this.jButton1.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 277 */         GenerarVencimientos.this.jButton1ActionPerformed(evt);
/*     */       }
/*     */     });
/* 281 */     this.jButton2.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/cancel.png")));
/* 282 */     this.jButton2.setText("Cancelar");
/* 283 */     this.jButton2.setHorizontalTextPosition(2);
/* 284 */     this.jButton2.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 286 */         GenerarVencimientos.this.jButton2ActionPerformed(evt);
/*     */       }
/*     */     });
/* 290 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 291 */     getContentPane().setLayout(layout);
/* 292 */     layout.setHorizontalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().add(layout.createParallelGroup(1).add(2, layout.createSequentialGroup().add(20, 20, 20).add(this.comboFormasPago, 0, 239, 32767)).add(layout.createSequentialGroup().addContainerGap().add(this.comboCuentasTesoreria, 0, 242, 32767)).add(layout.createSequentialGroup().addContainerGap().add(layout.createParallelGroup(1).add(2, layout.createSequentialGroup().add(this.jButton1).addPreferredGap(0, 77, 32767).add(this.jButton2)).add(this.jLabel1).add(this.jLabel2).add(this.isEfectos)))).addContainerGap()));
/*     */ 
/* 314 */     layout.setVerticalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(this.jLabel1).addPreferredGap(0).add(this.comboFormasPago, -2, -1, -2).add(14, 14, 14).add(this.jLabel2).addPreferredGap(0).add(this.comboCuentasTesoreria, -2, -1, -2).add(6, 6, 6).add(this.isEfectos).add(18, 18, 18).add(layout.createParallelGroup(3).add(this.jButton2).add(this.jButton1)).addContainerGap(18, 32767)));
/*     */ 
/* 334 */     pack();
/*     */   }
/*     */ 
/*     */   private void jButton1ActionPerformed(ActionEvent evt)
/*     */   {
/* 339 */     if (ejecutarAccionPrincipal())
/* 340 */       dispose();
/*     */   }
/*     */ 
/*     */   private void jButton2ActionPerformed(ActionEvent evt)
/*     */   {
/* 346 */     dispose();
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.dialogosAuxiliares.GenerarVencimientos
 * JD-Core Version:    0.6.2
 */