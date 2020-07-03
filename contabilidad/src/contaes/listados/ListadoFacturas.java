/*     */ package contaes.listados;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.Puente;
/*     */ import contaes.calendario.ICalendarTextField;
/*     */ import contaes.manejoDatos.ManejoAsientos;
/*     */ import contaes.manejoDatos.ManejoTiposIVA;
/*     */ import contaes.manejoDatos.TipoApunte;
/*     */ import contaes.manejoDatos.TipoFactura;
/*     */ import contaes.manejoDatos.auxiliar.FinLinea;
/*     */ import contaes.manejoDatos.auxiliar.MySQLConection;
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Frame;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.PrintStream;
/*     */ import java.net.URL;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.Formatter;
/*     */ import java.util.List;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ public class ListadoFacturas extends JDialog
/*     */   implements ActionListener
/*     */ {
/*     */   private static final String ACEPTAR = "aceptar";
/*     */   private static final String CANCELAR = "cancelar";
/*  64 */   String fecha = "";
/*  65 */   String fechaF = "";
/*     */   boolean emitidas;
/*  66 */   boolean listar = false;
/*  67 */   List<String> listado = new ArrayList();
/*     */ 
/*  69 */   JPanel panel1 = new JPanel();
/*  70 */   GridBagLayout gbLayout = new GridBagLayout();
/*  71 */   JLabel etiq1 = new JLabel();
/*  72 */   JLabel etiq2 = new JLabel();
/*  73 */   ICalendarTextField campoFecha = new ICalendarTextField();
/*  74 */   ICalendarTextField campoFechaF = new ICalendarTextField();
/*  75 */   JCheckBox numerar = new JCheckBox();
/*  76 */   JButton aceptar = new JButton();
/*  77 */   JButton cancelar = new JButton();
/*  78 */   ImageIcon iconoAceptar = createImageIcon("/contaes/iconos/ok.png");
/*  79 */   ImageIcon iconoCancelar = createImageIcon("/contaes/iconos/cancel.png");
/*     */ 
/*     */   public ListadoFacturas(Frame owner, String title, boolean modal, boolean emitidas) {
/*  82 */     super(owner, title, modal);
/*  83 */     this.emitidas = emitidas;
/*     */     try {
/*  85 */       setDefaultCloseOperation(2);
/*  86 */       initialize();
/*  87 */       pack();
/*     */     }
/*     */     catch (Exception exception) {
/*  90 */       exception.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public ListadoFacturas(Frame owner, boolean modal) {
/*  95 */     this(owner, Mensajes.getString("lisFacturas"), modal, true);
/*     */   }
/*     */ 
/*     */   public ListadoFacturas(Frame owner, boolean modal, boolean emitidas) {
/*  99 */     this(owner, Mensajes.getString("lisFacturas"), modal, emitidas);
/*     */   }
/*     */ 
/*     */   private void initialize() throws Exception
/*     */   {
/* 104 */     this.etiq1.setText(Mensajes.getString("fechaIni"));
/* 105 */     this.etiq2.setText(Mensajes.getString("fechaFin"));
/* 106 */     this.aceptar.setText(Mensajes.getString("aceptar"));
/* 107 */     this.aceptar.setIcon(this.iconoAceptar);
/* 108 */     this.aceptar.setVerticalTextPosition(0);
/* 109 */     this.aceptar.setHorizontalTextPosition(2);
/* 110 */     this.aceptar.setActionCommand("aceptar");
/* 111 */     this.aceptar.addActionListener(this);
/* 112 */     this.cancelar.setText(Mensajes.getString("cancelar"));
/* 113 */     this.cancelar.setIcon(this.iconoCancelar);
/* 114 */     this.cancelar.setVerticalTextPosition(0);
/* 115 */     this.cancelar.setHorizontalTextPosition(2);
/* 116 */     this.cancelar.setActionCommand("cancelar");
/* 117 */     this.cancelar.addActionListener(this);
/* 118 */     if (!this.emitidas) {
/* 119 */       this.numerar.setText(Mensajes.getString("numPropia"));
/* 120 */       this.numerar.setSelected(true);
/*     */     } else {
/* 122 */       this.numerar.setVisible(false);
/* 123 */     }this.campoFecha.setMinimumSize(new Dimension(100, 26));
/* 124 */     this.campoFecha.setToolTipText(Mensajes.getString("formatoFecha"));
/* 125 */     this.campoFecha.setComponente(this.campoFechaF);
/* 126 */     this.campoFechaF.setMinimumSize(new Dimension(100, 26));
/* 127 */     this.campoFechaF.setToolTipText(Mensajes.getString("formatoFecha"));
/* 128 */     this.campoFechaF.setComponente(this.aceptar);
/*     */ 
/* 130 */     this.panel1.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(1), BorderFactory.createEmptyBorder(5, 5, 5, 5))));
/*     */ 
/* 133 */     this.panel1.setLayout(this.gbLayout);
/*     */ 
/* 135 */     GridBagConstraints cons = new GridBagConstraints();
/* 136 */     cons.insets.bottom = 7;
/* 137 */     cons.insets.top = 7;
/* 138 */     cons.insets.left = 7;
/* 139 */     cons.insets.right = 7;
/*     */ 
/* 141 */     cons.gridy = 0;
/* 142 */     cons.gridx = 0;
/* 143 */     this.gbLayout.setConstraints(this.etiq1, cons);
/* 144 */     this.panel1.add(this.etiq1);
/*     */ 
/* 146 */     cons.gridx = 1;
/* 147 */     this.gbLayout.setConstraints(this.etiq2, cons);
/* 148 */     this.panel1.add(this.etiq2);
/*     */ 
/* 151 */     cons.fill = 2;
/* 152 */     cons.gridy = 1;
/* 153 */     cons.gridx = 0;
/* 154 */     this.gbLayout.setConstraints(this.campoFecha, cons);
/* 155 */     this.panel1.add(this.campoFecha);
/*     */ 
/* 157 */     cons.gridx = 1;
/* 158 */     this.gbLayout.setConstraints(this.campoFechaF, cons);
/* 159 */     this.panel1.add(this.campoFechaF);
/*     */ 
/* 162 */     cons.fill = 0;
/* 163 */     cons.anchor = 10;
/* 164 */     cons.gridy = 3;
/* 165 */     cons.gridx = 0;
/* 166 */     this.gbLayout.setConstraints(this.aceptar, cons);
/* 167 */     this.panel1.add(this.aceptar);
/*     */ 
/* 169 */     cons.gridx = 1;
/* 170 */     this.gbLayout.setConstraints(this.cancelar, cons);
/* 171 */     this.panel1.add(this.cancelar);
/*     */ 
/* 173 */     cons.gridy = 2;
/* 174 */     cons.gridx = 0;
/* 175 */     cons.gridwidth = 2;
/* 176 */     this.gbLayout.setConstraints(this.numerar, cons);
/* 177 */     this.panel1.add(this.numerar);
/*     */ 
/* 179 */     getContentPane().add(this.panel1);
/*     */   }
/*     */ 
/*     */   public void actionPerformed(ActionEvent e) {
/* 183 */     String cmd = e.getActionCommand();
/*     */ 
/* 185 */     if ("aceptar".equals(cmd)) {
/* 186 */       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/* 187 */       Date fecha_d = this.campoFecha.getDate();
/* 188 */       this.fecha = sdf.format(fecha_d);
/* 189 */       fecha_d = this.campoFechaF.getDate();
/* 190 */       this.fechaF = sdf.format(fecha_d);
/*     */ 
/* 192 */       if (hacerListado(this.fecha, this.fechaF, this.emitidas, this.numerar.isSelected()))
/* 193 */         this.listar = true;
/* 194 */       dispose();
/*     */     }
/* 196 */     else if ("cancelar".equals(cmd)) {
/* 197 */       this.listar = false;
/* 198 */       dispose();
/*     */     }
/*     */   }
/*     */ 
/*     */   public List<String> textoListado() {
/* 203 */     return this.listado;
/*     */   }
/*     */ 
/*     */   public boolean Listar() {
/* 207 */     return this.listar;
/*     */   }
/*     */ 
/*     */   private boolean hacerListadorecibidas(String fechaI, String fechaF, boolean numerar) {
/* 211 */     String EOL = FinLinea.get();
/*     */ 
/* 213 */     StringBuilder sb = new StringBuilder();
/* 214 */     Formatter formater = new Formatter(sb);
/* 215 */     ArrayList facturas = new ArrayList();
/*     */     try {
/* 217 */       if (numerar)
/* 218 */         formater.format("    ", new Object[0]);
/* 219 */       formater.format(new StringBuilder().append("%13s %-12s%-11s%-34s %-8s %-10s %-12s %s").append(EOL).append(EOL).toString(), new Object[] { Mensajes.getString("numero"), Mensajes.getString("fecha"), Mensajes.getString("NIF"), Mensajes.getString("nombre"), Mensajes.getString("base"), "Tipo", Mensajes.getString("IVAs"), Mensajes.getString("total") });
/*     */ 
/* 229 */       ResultSet res = Inicio.getCEmpresa().getRes(new StringBuilder().append("SELECT f.numero,f.fecha,s.nombre,t.NIF,f.base,f.iva,f.total,f.id_asiento FROM reci").append(Inicio.p.getEjercicio()).append(" f ").append("JOIN scta").append(Inicio.p.getEjercicio()).append(" s ON f.cuenta=s.codigo ").append("JOIN Terceros t ON f.cuenta=t.codigo ").append(" WHERE fecha BETWEEN '").append(fechaI).append("' AND '").append(fechaF).append("'").append(" ORDER BY fecha,numero").toString());
/*     */ 
/* 235 */       int miNumeracion = 1; int mesAct = 24;
/* 236 */       double base = 0.0D; double iva = 0.0D; double total = 0.0D; double acMesAntT = 0.0D; double acMesAntI = 0.0D; double acMesAntB = 0.0D;
/* 237 */       while (res.next()) {
/* 238 */         int idAsiento = res.getInt(8);
/*     */ 
/* 240 */         double base1 = res.getDouble(5);
/* 241 */         double iva1 = res.getDouble(6);
/* 242 */         double total1 = res.getDouble(7);
/* 243 */         TipoFactura facturaTemp = new TipoFactura();
/* 244 */         facturaTemp.setNumero(res.getString(1));
/* 245 */         facturaTemp.setFecha(res.getString(2));
/* 246 */         facturaTemp.setConcepto(new StringBuilder().append(res.getString(4)).append(" ").append(res.getString(3)).toString());
/* 247 */         facturaTemp.setBase(base1);
/* 248 */         facturaTemp.setIva(iva1);
/* 249 */         facturaTemp.setTotal(total1);
/* 250 */         facturaTemp.setId_asiento(idAsiento);
/* 251 */         facturas.add(facturaTemp);
/*     */       }
/* 253 */       for (TipoFactura factura :(List<TipoFactura>)   facturas)
/*     */       {
/* 255 */         String cadFecha = factura.getFecha();
/* 256 */         int mesAnt = mesAct;
/* 257 */         mesAct = Integer.parseInt(cadFecha.substring(5, 7));
/* 258 */         cadFecha = new StringBuilder().append(cadFecha.substring(8)).append("-").append(cadFecha.substring(5, 7)).append("-").append(cadFecha.substring(0, 4)).toString();
/*     */ 
/* 260 */         if (mesAnt < mesAct)
/*     */         {
/* 262 */           formater.format(EOL, new Object[0]);
/* 263 */           if (numerar)
/* 264 */             formater.format("    ", new Object[0]);
/* 265 */           formater.format(new StringBuilder().append("%63s: %,13.2f%,20.2f%,13.2f").append(EOL).toString(), new Object[] { Mensajes.getString("totalMes"), Double.valueOf(base - acMesAntB), Double.valueOf(iva - acMesAntI), Double.valueOf(total - acMesAntT) });
/*     */ 
/* 270 */           if (numerar)
/* 271 */             formater.format("    ", new Object[0]);
/* 272 */           formater.format(new StringBuilder().append("%63s: %,13.2f%,20.2f%,13.2f").append(EOL).append(EOL).toString(), new Object[] { Mensajes.getString("totalAcum"), Double.valueOf(base), Double.valueOf(iva), Double.valueOf(total) });
/*     */ 
/* 274 */           acMesAntT = total;
/* 275 */           acMesAntI = iva;
/* 276 */           acMesAntB = base;
/*     */         }
/* 278 */         if (numerar) {
/* 279 */           String pre = "";
/* 280 */           if (miNumeracion < 10) pre = new StringBuilder().append("  ").append(miNumeracion).toString();
/* 281 */           else if (miNumeracion < 100) pre = new StringBuilder().append(" ").append(miNumeracion).toString();
/*     */           else
/* 283 */             pre = new StringBuilder().append("").append(miNumeracion).toString();
/* 284 */           formater.format("%s ", new Object[] { pre });
/*     */         }
/* 286 */         formater.format(new StringBuilder().append("%13s %s %-40.40s %,13.2f%,20.2f%,13.2f").append(EOL).toString(), new Object[] { factura.getNumero(), cadFecha, factura.getConcepto(), Double.valueOf(factura.getBase()), Double.valueOf(factura.getIva()), Double.valueOf(factura.getTotal()) });
/*     */ 
/* 289 */         ArrayList apuntes = new ArrayList();
/* 290 */         ManejoAsientos mA = new ManejoAsientos(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 291 */         ManejoTiposIVA mT = new ManejoTiposIVA(Inicio.getCGeneral());
/* 292 */         apuntes = mA.getApuntes(factura.getId_asiento());
/* 293 */         for (TipoApunte apunte :(List<TipoApunte>)  apuntes) {
/* 294 */           String tipoIva = mT.getTipoForInvoices(apunte.getCuenta());
/* 295 */           tipoIva = tipoIva.substring(1);
/* 296 */           if (!tipoIva.equals("X")) {
/* 297 */             double tipo = Double.valueOf(tipoIva).doubleValue();
/* 298 */             if (tipo != 0.0D) {
/* 299 */               double importeIva = apunte.getImporte();
/* 300 */               double importeBase = importeIva / (tipo / 100.0D);
/* 301 */               formater.format(new StringBuilder().append("%68s  %,13.2f%,7.2f%,13.2f").append(EOL).toString(), new Object[] { "Desglose por tipos de I.V.A.", Double.valueOf(importeBase), Double.valueOf(tipo), Double.valueOf(importeIva) });
/*     */             }
/*     */           }
/*     */         }
/*     */ 
/* 306 */         formater.format(EOL, new Object[0]);
/*     */ 
/* 308 */         base += factura.getBase();
/* 309 */         iva += factura.getIva();
/* 310 */         total += factura.getTotal();
/*     */ 
/* 312 */         miNumeracion++;
/*     */       }
/*     */ 
/* 315 */       formater.format(EOL, new Object[0]);
/* 316 */       if (numerar)
/* 317 */         formater.format("    ", new Object[0]);
/* 318 */       formater.format(new StringBuilder().append("%63s: %,13.2f%,20.2f%,13.2f").append(EOL).toString(), new Object[] { Mensajes.getString("totalMes"), Double.valueOf(base - acMesAntB), Double.valueOf(iva - acMesAntI), Double.valueOf(total - acMesAntT) });
/*     */ 
/* 323 */       if (numerar)
/* 324 */         formater.format("    ", new Object[0]);
/* 325 */       formater.format(new StringBuilder().append("%63s: %,13.2f%,20.2f%,13.2f").append(EOL).append(EOL).toString(), new Object[] { Mensajes.getString("totalAcum"), Double.valueOf(base), Double.valueOf(iva), Double.valueOf(total) });
/*     */ 
/* 327 */       this.listado.add(sb.toString());
/*     */     }
/*     */     catch (SQLException exc) {
/* 330 */       exc.printStackTrace();
/* 331 */       return false;
/*     */     }
/* 333 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean hacerListadoEmitidas(String fechaI, String fechaF) {
/* 337 */     String EOL = FinLinea.get();
/*     */ 
/* 339 */     StringBuilder sb = new StringBuilder();
/* 340 */     Formatter formater = new Formatter(sb);
/* 341 */     ArrayList facturas = new ArrayList();
/*     */     try {
/* 343 */       formater.format(new StringBuilder().append("%13s %-12s%-11s%-34s %-8s %-10s %-10s %-10s %-12s %s").append(EOL).append(EOL).toString(), new Object[] { Mensajes.getString("numero"), Mensajes.getString("fecha"), Mensajes.getString("NIF"), Mensajes.getString("nombre"), Mensajes.getString("base"), "Tipo", Mensajes.getString("IVAs"), "T.RE", "R.E.", Mensajes.getString("total") });
/*     */ 
/* 355 */       ResultSet res = Inicio.getCEmpresa().getRes(new StringBuilder().append("SELECT f.numero,f.fecha,s.nombre,t.NIF,f.base,f.iva,f.total,f.id_asiento,f.re FROM emit").append(Inicio.p.getEjercicio()).append(" f ").append("JOIN scta").append(Inicio.p.getEjercicio()).append(" s ON f.cuenta=s.codigo ").append("JOIN Terceros t ON f.cuenta=t.codigo ").append(" WHERE fecha BETWEEN '").append(fechaI).append("' AND '").append(fechaF).append("'").append(" ORDER BY fecha,numero").toString());
/*     */ 
/* 361 */       int miNumeracion = 1; int mesAct = 24;
/* 362 */       double base = 0.0D; double iva = 0.0D; double total = 0.0D; double re = 0.0D; double acMesAntT = 0.0D; double acMesAntI = 0.0D; double acMesAntR = 0.0D; double acMesAntB = 0.0D;
/* 363 */       while (res.next()) {
/* 364 */         int idAsiento = res.getInt(8);
/*     */ 
/* 366 */         double base1 = res.getDouble(5);
/* 367 */         double iva1 = res.getDouble(6);
/* 368 */         double re1 = res.getDouble(9);
/* 369 */         double total1 = res.getDouble(7);
/* 370 */         TipoFactura facturaTemp = new TipoFactura();
/* 371 */         facturaTemp.setNumero(res.getString(1));
/* 372 */         facturaTemp.setFecha(res.getString(2));
/* 373 */         facturaTemp.setConcepto(new StringBuilder().append(res.getString(4)).append(" ").append(res.getString(3)).toString());
/* 374 */         facturaTemp.setBase(base1);
/* 375 */         facturaTemp.setIva(iva1);
/* 376 */         facturaTemp.setRe(re1);
/* 377 */         facturaTemp.setTotal(total1);
/* 378 */         facturaTemp.setId_asiento(idAsiento);
/* 379 */         facturas.add(facturaTemp);
/*     */       }
/* 381 */       for (TipoFactura factura :(List<TipoFactura>)  facturas)
/*     */       {
/* 383 */         String cadFecha = factura.getFecha();
/* 384 */         int mesAnt = mesAct;
/* 385 */         mesAct = Integer.parseInt(cadFecha.substring(5, 7));
/* 386 */         cadFecha = new StringBuilder().append(cadFecha.substring(8)).append("-").append(cadFecha.substring(5, 7)).append("-").append(cadFecha.substring(0, 4)).toString();
/*     */ 
/* 388 */         if (mesAnt < mesAct)
/*     */         {
/* 390 */           formater.format(EOL, new Object[0]);
/* 391 */           formater.format(new StringBuilder().append("%63s: %,13.2f%,20.2f%,20.2f%,13.2f").append(EOL).toString(), new Object[] { Mensajes.getString("totalMes"), Double.valueOf(base - acMesAntB), Double.valueOf(iva - acMesAntI), Double.valueOf(re - acMesAntR), Double.valueOf(total - acMesAntT) });
/*     */ 
/* 397 */           formater.format(new StringBuilder().append("%63s: %,13.2f%,20.2f%,20.2f%,13.2f").append(EOL).append(EOL).toString(), new Object[] { Mensajes.getString("totalAcum"), Double.valueOf(base), Double.valueOf(iva), Double.valueOf(re), Double.valueOf(total) });
/*     */ 
/* 399 */           acMesAntT = total;
/* 400 */           acMesAntI = iva;
/* 401 */           acMesAntR = re;
/* 402 */           acMesAntB = base;
/*     */         }
/* 404 */         formater.format(new StringBuilder().append("%13s %s %-40.40s %,13.2f%,20.2f%,20.2f%,13.2f").append(EOL).toString(), new Object[] { factura.getNumero(), cadFecha, factura.getConcepto(), Double.valueOf(factura.getBase()), Double.valueOf(factura.getIva()), Double.valueOf(factura.getRe()), Double.valueOf(factura.getTotal()) });
/*     */ 
/* 407 */         ArrayList apuntes = new ArrayList();
/* 408 */         ManejoAsientos mA = new ManejoAsientos(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 409 */         ManejoTiposIVA mT = new ManejoTiposIVA(Inicio.getCGeneral());
/* 410 */         apuntes = mA.getApuntes(factura.getId_asiento());
/* 411 */         ArrayList recargos = new ArrayList();
/* 412 */         if (factura.getRe() != 0.0D)
/*     */         {
/* 414 */           for (TipoApunte apunte : (List<TipoApunte>)apuntes) {
/* 415 */             String tipo = mT.getTipoForInvoices(apunte.getCuenta());
/* 416 */             if (tipo.substring(0, 1).equals("R")) {
/* 417 */               objetoRE recargo = new objetoRE();
/* 418 */               recargo.setCuenta(apunte.getCuenta());
/* 419 */               recargo.setImporte(apunte.getImporte());
/* 420 */               recargo.setTipo(tipo.substring(1));
/* 421 */               double t = Double.parseDouble(tipo.substring(1));
/* 422 */               double b = apunte.getImporte() / (t / 100.0D);
/* 423 */               int bi = (int)b;
/* 424 */               recargo.setBase(bi);
/* 425 */               recargos.add(recargo);
/*     */             }
/*     */           }
/*     */         }
/* 429 */         for (TipoApunte apunte : (List<TipoApunte>)apuntes) {
/* 430 */           String tipoIva = mT.getTipoForInvoices(apunte.getCuenta());
/* 431 */           String regimen = tipoIva.substring(0, 1);
/* 432 */           tipoIva = tipoIva.substring(1);
/* 433 */           double tipo = Double.valueOf(tipoIva).doubleValue();
/* 434 */           if ((tipo != 0.0D) && (regimen.equals("G"))) {
/* 435 */             double importeIva = apunte.getImporte();
/* 436 */             double importeBase = importeIva / (tipo / 100.0D);
/* 437 */             double tipoRE = 0.0D;
/* 438 */             double importeRE = 0.0D;
/* 439 */             int baseComp = (int)importeBase;
/* 440 */             for (objetoRE unRE : (List<objetoRE>)recargos) {
/* 441 */               if ((unRE.getBase() >= baseComp - 1) && (unRE.getBase() <= baseComp + 1) && (mT.SonDelMismoTipo(apunte.getCuenta(), unRE.getCuenta())))
/*     */               {
/* 443 */                 tipoRE = Double.parseDouble(unRE.getTipo());
/* 444 */                 importeRE = unRE.getImporte();
/* 445 */                 break;
/*     */               }
/*     */             }
/* 448 */             if (tipoRE == 0.0D) {
/* 449 */               formater.format(new StringBuilder().append("%64s  %,13.2f%,7.2f%,13.2f").append(EOL).toString(), new Object[] { "Desglose por tipos de I.V.A.", Double.valueOf(importeBase), Double.valueOf(tipo), Double.valueOf(importeIva) });
/*     */             }
/*     */             else {
/* 452 */               formater.format(new StringBuilder().append("%64s  %,13.2f%,7.2f%,13.2f%,7.2f%,13.2f").append(EOL).toString(), new Object[] { "Desglose por tipos de I.V.A.", Double.valueOf(importeBase), Double.valueOf(tipo), Double.valueOf(importeIva), Double.valueOf(tipoRE), Double.valueOf(importeRE) });
/*     */             }
/*     */           }
/*     */         }
/*     */ 
/* 457 */         formater.format(EOL, new Object[0]);
/*     */ 
/* 459 */         base += factura.getBase();
/* 460 */         iva += factura.getIva();
/* 461 */         re += factura.getRe();
/* 462 */         total += factura.getTotal();
/*     */ 
/* 464 */         miNumeracion++;
/*     */       }
/*     */ 
/* 467 */       formater.format(EOL, new Object[0]);
/* 468 */       formater.format(new StringBuilder().append("%63s: %,13.2f%,20.2f%,20.2f%,13.2f").append(EOL).toString(), new Object[] { Mensajes.getString("totalMes"), Double.valueOf(base - acMesAntB), Double.valueOf(iva - acMesAntI), Double.valueOf(re - acMesAntR), Double.valueOf(total - acMesAntT) });
/*     */ 
/* 474 */       formater.format(new StringBuilder().append("%63s: %,13.2f%,20.2f%,20.2f%,13.2f").append(EOL).append(EOL).toString(), new Object[] { Mensajes.getString("totalAcum"), Double.valueOf(base), Double.valueOf(iva), Double.valueOf(re), Double.valueOf(total) });
/*     */ 
/* 476 */       this.listado.add(sb.toString());
/*     */     } catch (SQLException exc) {
/* 478 */       exc.printStackTrace();
/* 479 */       return false;
/*     */     }
/* 481 */     return true;
/*     */   }
/*     */ 
/*     */   protected boolean hacerListado(String fechaI, String fechaF, boolean emitidas, boolean numerar) {
/* 485 */     if (!emitidas) return hacerListadorecibidas(fechaI, fechaF, numerar);
/* 486 */     return hacerListadoEmitidas(fechaI, fechaF);
/*     */   }
/*     */ 
/*     */   protected static ImageIcon createImageIcon(String path)
/*     */   {
/* 582 */     URL imgURL = ListadoFacturas.class.getResource(path);
/* 583 */     if (imgURL != null)
/* 584 */       return new ImageIcon(imgURL);
/* 585 */     System.err.println(new StringBuilder().append("Couldn't find file: ").append(path).toString());
/* 586 */     return null;
/*     */   }
/*     */   private class objetoRE { private int cuenta;
/*     */     private int base;
/*     */     private String tipo;
/*     */     private double importe;
/*     */ 
/* 596 */     public objetoRE(int cuenta, int base, String tipo, double importe) { this.cuenta = cuenta;
/* 597 */       this.base = base;
/* 598 */       this.tipo = tipo;
/* 599 */       this.importe = importe; }
/*     */ 
/*     */     public objetoRE()
/*     */     {
/*     */     }
/*     */ 
/*     */     public int getCuenta() {
/* 606 */       return this.cuenta;
/*     */     }
/*     */ 
/*     */     public void setCuenta(int cuenta) {
/* 610 */       this.cuenta = cuenta;
/*     */     }
/*     */ 
/*     */     public int getBase() {
/* 614 */       return this.base;
/*     */     }
/*     */ 
/*     */     public void setBase(int base) {
/* 618 */       this.base = base;
/*     */     }
/*     */ 
/*     */     public double getImporte() {
/* 622 */       return this.importe;
/*     */     }
/*     */ 
/*     */     public void setImporte(double importe) {
/* 626 */       this.importe = importe;
/*     */     }
/*     */ 
/*     */     public String getTipo() {
/* 630 */       return this.tipo;
/*     */     }
/*     */ 
/*     */     public void setTipo(String tipo) {
/* 634 */       this.tipo = tipo;
/*     */     }
/*     */ 
/*     */     public String toString()
/*     */     {
/* 639 */       return this.base + " : " + this.tipo + " : " + this.importe;
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.listados.ListadoFacturas
 * JD-Core Version:    0.6.2
 */