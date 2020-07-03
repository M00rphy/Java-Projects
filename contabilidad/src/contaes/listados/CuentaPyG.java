/*     */ package contaes.listados;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.Puente;
/*     */ import contaes.auxiliar.AlinearCadena;
/*     */ import contaes.calendario.ICalendarTextField;
/*     */ import contaes.manejoDatos.ManejoEmpresas;
/*     */ import contaes.manejoDatos.auxiliar.MySQLConection;
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.Container;
/*     */ import java.awt.Cursor;
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
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.DecimalFormatSymbols;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ public class CuentaPyG extends JDialog
/*     */   implements ActionListener
/*     */ {
/*     */   private static final String ACEPTAR = "aceptar";
/*     */   private static final String CANCELAR = "cancelar";
/*  62 */   private AlinearCadena alinea = new AlinearCadena();
/*  63 */   String fecha = "";
/*  64 */   String fechaF = "";
/*  65 */   boolean listar = false;
/*  66 */   private List<String> listado = new ArrayList();
/*     */   private ResultSet res;
/*  70 */   JPanel panel1 = new JPanel();
/*  71 */   GridBagLayout gbLayout = new GridBagLayout();
/*  72 */   JLabel etiq1 = new JLabel();
/*  73 */   JLabel etiq2 = new JLabel();
/*  74 */   ICalendarTextField campoFecha = new ICalendarTextField();
/*  75 */   ICalendarTextField campoFechaF = new ICalendarTextField();
/*  76 */   JButton aceptar = new JButton();
/*  77 */   JButton cancelar = new JButton();
/*  78 */   JCheckBox orden = new JCheckBox();
/*  79 */   ImageIcon iconoAceptar = createImageIcon("/contaes/iconos/ok.png");
/*  80 */   ImageIcon iconoCancelar = createImageIcon("/contaes/iconos/cancel.png");
/*     */ 
/*     */   public CuentaPyG(Frame owner, String title, boolean modal)
/*     */   {
/*  90 */     super(owner, title, modal);
/*     */     try {
/*  92 */       setDefaultCloseOperation(2);
/*  93 */       initialize();
/*  94 */       pack();
/*     */     }
/*     */     catch (Exception exception) {
/*  97 */       exception.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public CuentaPyG(Frame owner, boolean modal) {
/* 102 */     this(owner, Mensajes.getString("ctaPG2"), modal);
/*     */   }
/*     */ 
/*     */   private void initialize()
/*     */     throws Exception
/*     */   {
/* 111 */     this.etiq1.setText(Mensajes.getString("fechaIni"));
/* 112 */     this.etiq2.setText(Mensajes.getString("fechaFin"));
/* 113 */     this.aceptar.setText(Mensajes.getString("aceptar"));
/* 114 */     this.aceptar.setIcon(this.iconoAceptar);
/* 115 */     this.aceptar.setVerticalTextPosition(0);
/* 116 */     this.aceptar.setHorizontalTextPosition(2);
/* 117 */     this.aceptar.setActionCommand("aceptar");
/* 118 */     this.aceptar.addActionListener(this);
/* 119 */     this.cancelar.setText(Mensajes.getString("cancelar"));
/* 120 */     this.cancelar.setIcon(this.iconoCancelar);
/* 121 */     this.cancelar.setVerticalTextPosition(0);
/* 122 */     this.cancelar.setHorizontalTextPosition(2);
/* 123 */     this.cancelar.setActionCommand("cancelar");
/* 124 */     this.cancelar.addActionListener(this);
/* 125 */     this.orden.setText(Mensajes.getString("abreviada"));
/* 126 */     this.orden.setSelected(false);
/* 127 */     this.orden.setToolTipText(Mensajes.getString("pygAbre"));
/* 128 */     this.campoFecha.setMinimumSize(new Dimension(100, 26));
/* 129 */     this.campoFecha.setToolTipText(Mensajes.getString("formatoFecha"));
/* 130 */     this.campoFecha.setComponente(this.campoFechaF);
/* 131 */     this.campoFechaF.setMinimumSize(new Dimension(100, 26));
/* 132 */     this.campoFechaF.setToolTipText(Mensajes.getString("formatoFecha"));
/* 133 */     this.campoFechaF.setComponente(this.orden);
/*     */ 
/* 135 */     this.panel1.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(1), BorderFactory.createEmptyBorder(5, 5, 5, 5))));
/*     */ 
/* 138 */     this.panel1.setLayout(this.gbLayout);
/*     */ 
/* 140 */     GridBagConstraints cons = new GridBagConstraints();
/* 141 */     cons.insets.bottom = 7;
/* 142 */     cons.insets.top = 7;
/* 143 */     cons.insets.left = 7;
/* 144 */     cons.insets.right = 7;
/*     */ 
/* 146 */     cons.gridy = 0;
/* 147 */     cons.gridx = 0;
/* 148 */     this.gbLayout.setConstraints(this.etiq1, cons);
/* 149 */     this.panel1.add(this.etiq1);
/*     */ 
/* 151 */     cons.gridx = 1;
/* 152 */     this.gbLayout.setConstraints(this.etiq2, cons);
/* 153 */     this.panel1.add(this.etiq2);
/*     */ 
/* 156 */     cons.fill = 2;
/* 157 */     cons.gridy = 1;
/* 158 */     cons.gridx = 0;
/* 159 */     this.gbLayout.setConstraints(this.campoFecha, cons);
/* 160 */     this.panel1.add(this.campoFecha);
/*     */ 
/* 162 */     cons.gridx = 1;
/* 163 */     this.gbLayout.setConstraints(this.campoFechaF, cons);
/* 164 */     this.panel1.add(this.campoFechaF);
/*     */ 
/* 167 */     cons.fill = 0;
/* 168 */     cons.anchor = 10;
/* 169 */     cons.gridy = 3;
/* 170 */     cons.gridx = 0;
/* 171 */     this.gbLayout.setConstraints(this.aceptar, cons);
/* 172 */     this.panel1.add(this.aceptar);
/*     */ 
/* 174 */     cons.gridx = 1;
/* 175 */     this.gbLayout.setConstraints(this.cancelar, cons);
/* 176 */     this.panel1.add(this.cancelar);
/*     */ 
/* 178 */     cons.gridy = 2;
/* 179 */     cons.gridx = 0;
/* 180 */     cons.gridwidth = 2;
/* 181 */     this.gbLayout.setConstraints(this.orden, cons);
/* 182 */     this.panel1.add(this.orden);
/*     */ 
/* 184 */     getContentPane().add(this.panel1);
/*     */   }
/*     */ 
/*     */   public void actionPerformed(ActionEvent e)
/*     */   {
/* 192 */     String cmd = e.getActionCommand();
/*     */ 
/* 194 */     if ("aceptar".equals(cmd)) {
/* 195 */       SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
/* 196 */       Date fecha_d = this.campoFecha.getDate();
/* 197 */       this.fecha = sdf.format(fecha_d);
/* 198 */       fecha_d = this.campoFechaF.getDate();
/* 199 */       this.fechaF = sdf.format(fecha_d);
/*     */ 
/* 201 */       if ((!this.fecha.equals("")) && (!this.fechaF.equals(""))) {
/* 202 */         hacerListado(this.fecha, this.fechaF);
/* 203 */         dispose();
/*     */       }
/*     */     }
/* 206 */     else if ("cancelar".equals(cmd)) {
/* 207 */       this.listar = false;
/* 208 */       dispose();
/*     */     }
/*     */   }
/*     */ 
/*     */   public List<String> textoListado()
/*     */   {
/* 218 */     return this.listado;
/*     */   }
/*     */ 
/*     */   public boolean Listar()
/*     */   {
/* 227 */     return this.listar;
/*     */   }
/*     */ 
/*     */   private void hacerListado(String fechaI, String fechaF)
/*     */   {
/* 237 */     setCursor(Cursor.getPredefinedCursor(3));
/* 238 */     String nombreEmpresa = new ManejoEmpresas(Inicio.getCGeneral(), Inicio.p.getEmpresa()).getNombre(Inicio.p.getEmpresa());
/* 239 */     double[] pa = new double[27];
/* 240 */     double[] pb = new double[24];
/* 241 */     for (int i = 0; i < 27; i++) {
/* 242 */       pa[i] = 0.0D;
/*     */     }
/* 244 */     for (int i = 0; i < 24; i++) {
/* 245 */       pb[i] = 0.0D;
/*     */     }
/* 247 */     DecimalFormatSymbols formato = new DecimalFormatSymbols();
/* 248 */     formato.setDecimalSeparator(',');
/* 249 */     formato.setPerMill('.');
/* 250 */     DecimalFormat fn = new DecimalFormat("#,###,##0.00", formato);
/*     */ 
/* 252 */     pa[1] = calculaGrupo("PGA2a", fechaI, fechaF);
/* 253 */     pa[2] = calculaGrupo("PGA2b", fechaI, fechaF);
/* 254 */     pa[3] = calculaGrupo("PGA2c", fechaI, fechaF);
/* 255 */     pa[4] = calculaGrupo("PGA3a", fechaI, fechaF);
/* 256 */     pa[5] = calculaGrupo("PGA3b", fechaI, fechaF);
/* 257 */     pa[6] = calculaGrupo("PGA4", fechaI, fechaF);
/* 258 */     pa[7] = calculaGrupo("PGA5a", fechaI, fechaF);
/* 259 */     pa[8] = calculaGrupo("PGA5b", fechaI, fechaF);
/* 260 */     pa[9] = calculaGrupo("PGA5c", fechaI, fechaF);
/* 261 */     pa[10] = calculaGrupo("PGA6a", fechaI, fechaF);
/* 262 */     pa[11] = calculaGrupo("PGA6b", fechaI, fechaF);
/* 263 */     pa[12] = calculaGrupo("PGA6c", fechaI, fechaF);
/* 264 */     pa[13] = calculaGrupo("PGA6d", fechaI, fechaF);
/* 265 */     pa[14] = calculaGrupo("PGA7a", fechaI, fechaF);
/* 266 */     pa[15] = calculaGrupo("PGA7b", fechaI, fechaF);
/* 267 */     pa[16] = calculaGrupo("PGA7c", fechaI, fechaF);
/* 268 */     pa[17] = calculaGrupo("PGA7d", fechaI, fechaF);
/* 269 */     pa[18] = calculaGrupo("PGA8", fechaI, fechaF);
/* 270 */     pa[19] = calculaGrupo("PGA9", fechaI, fechaF);
/* 271 */     pa[20] = calculaGrupo("PGA10", fechaI, fechaF);
/* 272 */     pa[21] = calculaGrupo("PGA11", fechaI, fechaF);
/* 273 */     pa[22] = calculaGrupo("PGA12", fechaI, fechaF);
/* 274 */     pa[23] = calculaGrupo("PGA13", fechaI, fechaF);
/* 275 */     pa[24] = calculaGrupo("PGA14", fechaI, fechaF);
/* 276 */     pa[25] = calculaGrupo("PGA15", fechaI, fechaF);
/* 277 */     pa[26] = calculaGrupo("PGA16", fechaI, fechaF);
/*     */ 
/* 279 */     pb[0] = calculaGrupo("PGB1a", fechaI, fechaF);
/* 280 */     pb[1] = calculaGrupo("PGB1b", fechaI, fechaF);
/* 281 */     pb[2] = calculaGrupo("PGB1c", fechaI, fechaF);
/* 282 */     pb[3] = calculaGrupo("PGB2", fechaI, fechaF);
/* 283 */     pb[4] = calculaGrupo("PGB3", fechaI, fechaF);
/* 284 */     pb[5] = calculaGrupo("PGB4a", fechaI, fechaF);
/* 285 */     pb[6] = calculaGrupo("PGB4b", fechaI, fechaF);
/* 286 */     pb[7] = calculaGrupo("PGB4c", fechaI, fechaF);
/* 287 */     pb[8] = calculaGrupo("PGB5a", fechaI, fechaF);
/* 288 */     pb[9] = calculaGrupo("PGB5b", fechaI, fechaF);
/* 289 */     pb[10] = calculaGrupo("PGB5c", fechaI, fechaF);
/* 290 */     pb[11] = calculaGrupo("PGB6a", fechaI, fechaF);
/* 291 */     pb[12] = calculaGrupo("PGB6b", fechaI, fechaF);
/* 292 */     pb[13] = calculaGrupo("PGB6c", fechaI, fechaF);
/* 293 */     pb[14] = calculaGrupo("PGB7a", fechaI, fechaF);
/* 294 */     pb[15] = calculaGrupo("PGB7b", fechaI, fechaF);
/* 295 */     pb[16] = calculaGrupo("PGB7c", fechaI, fechaF);
/* 296 */     pb[17] = calculaGrupo("PGB7d", fechaI, fechaF);
/* 297 */     pb[18] = calculaGrupo("PGB8", fechaI, fechaF);
/* 298 */     pb[19] = calculaGrupo("PGB9", fechaI, fechaF);
/* 299 */     pb[20] = calculaGrupo("PGB10", fechaI, fechaF);
/* 300 */     pb[21] = calculaGrupo("PGB11", fechaI, fechaF);
/* 301 */     pb[22] = calculaGrupo("PGB12", fechaI, fechaF);
/* 302 */     pb[23] = calculaGrupo("PGB13", fechaI, fechaF);
/*     */ 
/* 306 */     if (!this.orden.isSelected())
/*     */     {
/* 308 */       String linea = "";
/* 309 */       linea = "CUENTAS DE PÉRDIDAS Y GANANCIAS para " + nombreEmpresa + "\n";
/* 310 */       this.listado.add(linea);
/* 311 */       linea = "Ejercicio: " + Inicio.p.getEjercicio() + ".  Entre " + fechaI.substring(6) + "-" + fechaI.substring(4, 6) + " y " + fechaF.substring(6) + "-" + fechaF.substring(4, 6) + "\n\n";
/*     */ 
/* 313 */       this.listado.add(linea);
/* 314 */       double[] t = new double[18];
/* 315 */       for (int i = 0; i < 18; i++) t[i] = 0.0D;
/* 316 */       for (int i = 1; i < 4; i++) t[1] += pa[i];
/* 317 */       for (int i = 4; i < 7; i++) t[2] += pa[i];
/* 318 */       for (int i = 7; i < 10; i++) t[3] += pa[i];
/* 319 */       for (int i = 10; i < 14; i++) t[4] += pa[i];
/* 320 */       for (int i = 14; i < 18; i++) t[6] += pa[i];
/* 321 */       t[0] = (pa[6] + t[6]);
/* 322 */       if (pb[3] < 0.0D) t[0] -= pb[3];
/* 323 */       for (int i = 1; i < 5; i++) t[0] += t[i];
/* 324 */       for (int i = 18; i < 27; i++) t[0] += pa[i];
/* 325 */       for (int i = 0; i < 3; i++) t[13] += pb[i];
/* 326 */       for (int i = 5; i < 8; i++) t[14] += pb[i];
/* 327 */       t[5] = (t[13] + pb[3] + pb[4] + t[14] - t[1] - t[2] - t[3] - pa[6] - t[4]);
/* 328 */       for (int i = 8; i < 11; i++) t[15] += pb[i];
/* 329 */       for (int i = 11; i < 14; i++) t[16] += pb[i];
/* 330 */       for (int i = 14; i < 18; i++) t[17] += pb[i];
/* 331 */       t[7] = (pb[18] - t[6] - pa[18] - pa[19]);
/* 332 */       for (int i = 15; i < 18; i++) t[7] += t[i];
/* 333 */       t[8] = (t[5] + t[7]);
/* 334 */       for (int i = 19; i < 24; i++) t[9] += pb[i];
/* 335 */       for (int i = 20; i < 25; i++) t[9] -= pa[i];
/* 336 */       t[10] = (t[8] + t[9]);
/* 337 */       t[11] = (t[10] - pa[25] - pa[26]);
/* 338 */       t[12] = (t[13] + pb[4] + t[14] + t[15] + t[16] + t[17] + pb[18]);
/* 339 */       if (pb[3] >= 0.0D) t[12] += pb[3];
/* 340 */       for (int i = 19; i < 24; i++) t[12] += pb[i];
/*     */ 
/* 342 */       linea = "DEBE\n";
/* 343 */       this.listado.add(linea);
/* 344 */       linea = this.alinea.Izquierda("A) GASTOS", 60) + this.alinea.Derecha(fn.format(t[0]), 12) + "\n";
/* 345 */       this.listado.add(linea);
/* 346 */       if (pb[3] < 0.0D) {
/* 347 */         linea = "   1. Reducción de existencias de productos terminados\n";
/* 348 */         this.listado.add(linea);
/* 349 */         linea = this.alinea.Izquierda("      y en curso de fabricación", 60) + this.alinea.Derecha(fn.format(-pb[3]), 12) + "\n";
/*     */ 
/* 351 */         this.listado.add(linea);
/*     */       }
/* 353 */       linea = this.alinea.Izquierda("   2. Aprovisionamientos", 60) + this.alinea.Derecha(fn.format(t[1]), 12) + "\n";
/* 354 */       this.listado.add(linea);
/* 355 */       linea = this.alinea.Izquierda("      a) Consumo de mercaderías", 60) + this.alinea.Derecha(fn.format(pa[1]), 12) + "\n";
/* 356 */       this.listado.add(linea);
/* 357 */       linea = "      b) Consumo de materias primas y otras materias\n";
/* 358 */       this.listado.add(linea);
/* 359 */       linea = this.alinea.Izquierda("         consumibles", 60) + this.alinea.Derecha(fn.format(pa[2]), 12) + "\n";
/* 360 */       this.listado.add(linea);
/* 361 */       linea = this.alinea.Izquierda("      c) Otros gastos externos", 60) + this.alinea.Derecha(fn.format(pa[3]), 12) + "\n";
/* 362 */       this.listado.add(linea);
/* 363 */       linea = this.alinea.Izquierda("   3. Gastos de personal", 60) + this.alinea.Derecha(fn.format(t[2]), 12) + "\n";
/* 364 */       this.listado.add(linea);
/* 365 */       linea = this.alinea.Izquierda("      a) Sueldos, salarios y asimilados", 60) + this.alinea.Derecha(fn.format(pa[4]), 12) + "\n";
/* 366 */       this.listado.add(linea);
/* 367 */       linea = this.alinea.Izquierda("      b) Cargas sociales", 60) + this.alinea.Derecha(fn.format(pa[5]), 12) + "\n";
/* 368 */       this.listado.add(linea);
/* 369 */       linea = this.alinea.Izquierda("   4. Dotaciones para amortizaciones de inmovilizado", 60) + this.alinea.Derecha(fn.format(pa[6]), 12) + "\n";
/* 370 */       this.listado.add(linea);
/* 371 */       linea = this.alinea.Izquierda("   5. Variación de las provisiones de tráfico", 60) + this.alinea.Derecha(fn.format(t[3]), 12) + "\n";
/* 372 */       this.listado.add(linea);
/* 373 */       linea = this.alinea.Izquierda("      a) Variación de las provisiones de existencias", 60) + this.alinea.Derecha(fn.format(pa[7]), 12) + "\n";
/* 374 */       this.listado.add(linea);
/* 375 */       linea = "      b) Variación de provisiones y pérdidas de crédito\n";
/* 376 */       this.listado.add(linea);
/* 377 */       linea = this.alinea.Izquierda("         incobrables", 60) + this.alinea.Derecha(fn.format(pa[8]), 12) + "\n";
/* 378 */       this.listado.add(linea);
/* 379 */       linea = this.alinea.Izquierda("      c) Variacion de otras provisiones de tráfico", 60) + this.alinea.Derecha(fn.format(pa[9]), 12) + "\n";
/* 380 */       this.listado.add(linea);
/* 381 */       linea = this.alinea.Izquierda("   6. Otros gastos de explotación", 60) + this.alinea.Derecha(fn.format(t[4]), 12) + "\n";
/* 382 */       this.listado.add(linea);
/* 383 */       linea = this.alinea.Izquierda("      a) Servicios exteriores", 60) + this.alinea.Derecha(fn.format(pa[10]), 12) + "\n";
/* 384 */       this.listado.add(linea);
/* 385 */       linea = this.alinea.Izquierda("      b) Tributos", 60) + this.alinea.Derecha(fn.format(pa[11]), 12) + "\n";
/* 386 */       this.listado.add(linea);
/* 387 */       linea = this.alinea.Izquierda("      c) Otros gastos de gestión corriente", 60) + this.alinea.Derecha(fn.format(pa[12]), 12) + "\n";
/* 388 */       this.listado.add(linea);
/* 389 */       linea = this.alinea.Izquierda("      d) Dotación al fondo de reversión", 60) + this.alinea.Derecha(fn.format(pa[13]), 12) + "\n";
/* 390 */       this.listado.add(linea);
/* 391 */       if (t[5] >= 0.0D) {
/* 392 */         linea = "   I. BENEFICIOS DE EXPLOTACIÓN\n";
/* 393 */         this.listado.add(linea);
/* 394 */         linea = this.alinea.Izquierda("       (B1+B2+B3+B4-A1-A2-A3-A4-A5-A6)", 60) + this.alinea.Derecha(fn.format(t[5]), 12) + "\n";
/*     */ 
/* 396 */         this.listado.add(linea);
/*     */       }
/* 398 */       linea = this.alinea.Izquierda("   7. Gastos financieros y gastos asimilados", 60) + this.alinea.Derecha(fn.format(t[6]), 12) + "\n";
/* 399 */       this.listado.add(linea);
/* 400 */       linea = this.alinea.Izquierda("      a) Por deudas con empresas del grupo", 60) + this.alinea.Derecha(fn.format(pa[14]), 12) + "\n";
/* 401 */       this.listado.add(linea);
/* 402 */       linea = this.alinea.Izquierda("      b) Por deudas con empresas asociadas", 60) + this.alinea.Derecha(fn.format(pa[15]), 12) + "\n";
/* 403 */       this.listado.add(linea);
/* 404 */       linea = this.alinea.Izquierda("      c) Por deudas con terceros y gastos asimilados", 60) + this.alinea.Derecha(fn.format(pa[16]), 12) + "\n";
/* 405 */       this.listado.add(linea);
/* 406 */       linea = this.alinea.Izquierda("      d) Perdidas de inversiones financieras", 60) + this.alinea.Derecha(fn.format(pa[17]), 12) + "\n";
/* 407 */       this.listado.add(linea);
/* 408 */       linea = this.alinea.Izquierda("   8. Variación de las provisiones de inversiones financ.", 60) + this.alinea.Derecha(fn.format(pa[18]), 12) + "\n";
/* 409 */       this.listado.add(linea);
/* 410 */       linea = this.alinea.Izquierda("   9. Diferencias negativas de cambio", 60) + this.alinea.Derecha(fn.format(pa[19]), 12) + "\n";
/* 411 */       this.listado.add(linea);
/* 412 */       if (t[7] >= 0.0D) {
/* 413 */         linea = "  II. RESULTADOS FINANCIEROS POSITIVOS\n";
/* 414 */         this.listado.add(linea);
/* 415 */         linea = this.alinea.Izquierda("      (B5+B6+B7+B8-A7-A8-A9)", 60) + this.alinea.Derecha(fn.format(t[7]), 12) + "\n";
/*     */ 
/* 417 */         this.listado.add(linea);
/*     */       }
/* 419 */       if (t[8] >= 0.0D) {
/* 420 */         linea = " III. BENEFICIOS DE LAS ACTIVIDADES ORDINARIAS\n";
/* 421 */         this.listado.add(linea);
/* 422 */         linea = this.alinea.Izquierda("      (AI+AII-BI-BII)", 60) + this.alinea.Derecha(fn.format(t[8]), 12) + "\n";
/*     */ 
/* 424 */         this.listado.add(linea);
/*     */       }
/* 426 */       linea = "  10. Variación provisiones de inmovilizado inmaterial,\n";
/* 427 */       this.listado.add(linea);
/* 428 */       linea = this.alinea.Izquierda("      material y cartera control", 60) + this.alinea.Derecha(fn.format(pa[20]), 12) + "\n";
/* 429 */       this.listado.add(linea);
/* 430 */       linea = "  11. Pérdidas procedentes inmovilizado inmaterial,\n";
/* 431 */       this.listado.add(linea);
/* 432 */       linea = this.alinea.Izquierda("      material y cartera control", 60) + this.alinea.Derecha(fn.format(pa[21]), 12) + "\n";
/* 433 */       this.listado.add(linea);
/* 434 */       linea = "  12. Pérdidas por operaciones con acciones y\n";
/* 435 */       this.listado.add(linea);
/* 436 */       linea = this.alinea.Izquierda("      obligaciones propias", 60) + this.alinea.Derecha(fn.format(pa[22]), 12) + "\n";
/* 437 */       this.listado.add(linea);
/* 438 */       linea = this.alinea.Izquierda("  13. Gastos extraordinarios", 60) + this.alinea.Derecha(fn.format(pa[23]), 12) + "\n";
/* 439 */       this.listado.add(linea);
/* 440 */       linea = this.alinea.Izquierda("  14. Gastos y pérdidas de otros ejercicios", 60) + this.alinea.Derecha(fn.format(pa[24]), 12) + "\n";
/* 441 */       this.listado.add(linea);
/* 442 */       if (t[9] >= 0.0D) {
/* 443 */         linea = "  IV. RESULTADOS EXTRAORDINARIOS POSITIVOS\n";
/* 444 */         this.listado.add(linea);
/* 445 */         linea = this.alinea.Izquierda("      (B9+B10+B11+B12+B13-A10-A11-A12-A13-A14)", 60) + this.alinea.Derecha(fn.format(t[9]), 12) + "\n";
/*     */ 
/* 447 */         this.listado.add(linea);
/*     */       }
/* 449 */       if (t[10] >= 0.0D) {
/* 450 */         linea = this.alinea.Izquierda("   V. BENEFICIOS ANTES DE IMPUESTOS (AIII+AIV-BIII-BIV)", 60) + this.alinea.Derecha(fn.format(t[10]), 12) + "\n";
/*     */ 
/* 453 */         this.listado.add(linea);
/*     */       }
/* 455 */       linea = this.alinea.Izquierda("  15. Impuestos sobre Sociedades", 60) + this.alinea.Derecha(fn.format(pa[25]), 12) + "\n";
/* 456 */       this.listado.add(linea);
/* 457 */       linea = this.alinea.Izquierda("  16. Otros impuestos", 60) + this.alinea.Derecha(fn.format(pa[26]), 12) + "\n";
/* 458 */       this.listado.add(linea);
/* 459 */       if (t[11] >= 0.0D) {
/* 460 */         linea = this.alinea.Izquierda("  VI. RESULTADOS DEL EJERCICIO (BENEFICIOS) (AV-A15-A16)", 60) + this.alinea.Derecha(fn.format(t[11]), 12) + "\n";
/*     */ 
/* 463 */         this.listado.add(linea);
/*     */       }
/* 465 */       linea = "HABER\n";
/* 466 */       this.listado.add(linea);
/* 467 */       linea = this.alinea.Izquierda("B) INGRESOS", 60) + this.alinea.Derecha(fn.format(t[12]), 12) + "\n";
/* 468 */       this.listado.add(linea);
/* 469 */       linea = this.alinea.Izquierda("   1. Importe neto de la cifra de negocios", 60) + this.alinea.Derecha(fn.format(t[13]), 12) + "\n";
/* 470 */       this.listado.add(linea);
/* 471 */       linea = this.alinea.Izquierda("      a) Ventas", 60) + this.alinea.Derecha(fn.format(pb[0]), 12) + "\n";
/* 472 */       this.listado.add(linea);
/* 473 */       linea = this.alinea.Izquierda("      b) Prestaciones de servicios", 60) + this.alinea.Derecha(fn.format(pb[1]), 12) + "\n";
/* 474 */       this.listado.add(linea);
/* 475 */       linea = this.alinea.Izquierda("      c) Devoluciones y rappels sobre ventas", 60) + this.alinea.Derecha(fn.format(pb[2]), 12) + "\n";
/* 476 */       this.listado.add(linea);
/* 477 */       if (pb[3] >= 0.0D) {
/* 478 */         linea = "   2. Aumento de existencias de productos terminados y\n";
/* 479 */         this.listado.add(linea);
/* 480 */         linea = this.alinea.Izquierda("      en curso de fabricación", 60) + this.alinea.Derecha(fn.format(pb[3]), 12) + "\n";
/*     */ 
/* 482 */         this.listado.add(linea);
/*     */       }
/* 484 */       linea = "   3. Trabajos efectuados por la empresa para el\n";
/* 485 */       this.listado.add(linea);
/* 486 */       linea = this.alinea.Izquierda("      inmovilizado", 60) + this.alinea.Derecha(fn.format(pb[4]), 12) + "\n";
/* 487 */       this.listado.add(linea);
/* 488 */       linea = this.alinea.Izquierda("   4. Otros ingresos de explotación", 60) + this.alinea.Derecha(fn.format(t[14]), 12) + "\n";
/* 489 */       this.listado.add(linea);
/* 490 */       linea = this.alinea.Izquierda("      a) Ingresos accesorios y otros de gestión corriente", 60) + this.alinea.Derecha(fn.format(pb[5]), 12) + "\n";
/* 491 */       this.listado.add(linea);
/* 492 */       linea = this.alinea.Izquierda("      b) Subvenciones", 60) + this.alinea.Derecha(fn.format(pb[6]), 12) + "\n";
/* 493 */       this.listado.add(linea);
/* 494 */       linea = this.alinea.Izquierda("      c) Exceso de provisiones de riesgos y gastos", 60) + this.alinea.Derecha(fn.format(pb[7]), 12) + "\n";
/* 495 */       this.listado.add(linea);
/* 496 */       if (t[5] < 0.0D) {
/* 497 */         linea = "   I. PÉRDIDAS DE EXPLOTACIÓN\n";
/* 498 */         this.listado.add(linea);
/* 499 */         linea = this.alinea.Izquierda("      (A1+A2+A3+A4+A5+A6-B1-B2-B3-B4)", 60) + this.alinea.Derecha(fn.format(-t[5]), 12) + "\n";
/*     */ 
/* 501 */         this.listado.add(linea);
/*     */       }
/* 503 */       linea = this.alinea.Izquierda("   5. Ingresos de participaciones en capital", 60) + this.alinea.Derecha(fn.format(t[15]), 12) + "\n";
/* 504 */       this.listado.add(linea);
/* 505 */       linea = this.alinea.Izquierda("      a) En empresas del grupo", 60) + this.alinea.Derecha(fn.format(pb[8]), 12) + "\n";
/* 506 */       this.listado.add(linea);
/* 507 */       linea = this.alinea.Izquierda("      b) En empresas asociadas", 60) + this.alinea.Derecha(fn.format(pb[9]), 12) + "\n";
/* 508 */       this.listado.add(linea);
/* 509 */       linea = this.alinea.Izquierda("      c) En empresas fuera del grupo", 60) + this.alinea.Derecha(fn.format(pb[10]), 12) + "\n";
/* 510 */       this.listado.add(linea);
/* 511 */       linea = "   6. Ingresos de otros valores negociables y de créditos\n";
/* 512 */       this.listado.add(linea);
/* 513 */       linea = this.alinea.Izquierda("      del activo inmovilizado", 60) + this.alinea.Derecha(fn.format(t[16]), 12) + "\n";
/* 514 */       this.listado.add(linea);
/* 515 */       linea = this.alinea.Izquierda("      a) De empresas del grupo", 60) + this.alinea.Derecha(fn.format(pb[11]), 12) + "\n";
/* 516 */       this.listado.add(linea);
/* 517 */       linea = this.alinea.Izquierda("      b) De empresas asociadas", 60) + this.alinea.Derecha(fn.format(pb[12]), 12) + "\n";
/* 518 */       this.listado.add(linea);
/* 519 */       linea = this.alinea.Izquierda("      c) De empresas fuera del grupo", 60) + this.alinea.Derecha(fn.format(pb[13]), 12) + "\n";
/* 520 */       this.listado.add(linea);
/* 521 */       linea = this.alinea.Izquierda("   7. Otros intereses e ingresos asimilados", 60) + this.alinea.Derecha(fn.format(t[17]), 12) + "\n";
/* 522 */       this.listado.add(linea);
/* 523 */       linea = this.alinea.Izquierda("      a) De empresas del grupo", 60) + this.alinea.Derecha(fn.format(pb[14]), 12) + "\n";
/* 524 */       this.listado.add(linea);
/* 525 */       linea = this.alinea.Izquierda("      b) De empresas asociadas", 60) + this.alinea.Derecha(fn.format(pb[15]), 12) + "\n";
/* 526 */       this.listado.add(linea);
/* 527 */       linea = this.alinea.Izquierda("      c) Otros intereses", 60) + this.alinea.Derecha(fn.format(pb[16]), 12) + "\n";
/* 528 */       this.listado.add(linea);
/* 529 */       linea = this.alinea.Izquierda("      d) Beneficios en inversiones financieras", 60) + this.alinea.Derecha(fn.format(pb[17]), 12) + "\n";
/* 530 */       this.listado.add(linea);
/* 531 */       linea = this.alinea.Izquierda("   8. Diferencias positivas de cambio", 60) + this.alinea.Derecha(fn.format(pb[18]), 12) + "\n";
/* 532 */       this.listado.add(linea);
/* 533 */       if (t[7] < 0.0D) {
/* 534 */         linea = "  II. RESULTADOS FINANCIEROS NEGATIVOS\n";
/* 535 */         this.listado.add(linea);
/* 536 */         linea = this.alinea.Izquierda("      (A7+A8+A9-B5-B6-B7-B8)", 60) + this.alinea.Derecha(fn.format(-t[7]), 12) + "\n";
/*     */ 
/* 538 */         this.listado.add(linea);
/*     */       }
/* 540 */       if (t[8] < 0.0D) {
/* 541 */         linea = " III. PÉRDIDAS DE LAS ACTIVIDADES ORDINARIAS\n";
/* 542 */         this.listado.add(linea);
/* 543 */         linea = this.alinea.Izquierda("      (BI+BII-AI-AII)", 60) + this.alinea.Derecha(fn.format(-t[8]), 12) + "\n";
/*     */ 
/* 545 */         this.listado.add(linea);
/*     */       }
/* 547 */       linea = "   9. Beneficios en enajenación inmovilizado\n";
/* 548 */       this.listado.add(linea);
/* 549 */       linea = this.alinea.Izquierda("      inmaterial,material y cartera control", 60) + this.alinea.Derecha(fn.format(pb[19]), 12) + "\n";
/* 550 */       this.listado.add(linea);
/* 551 */       linea = "  10. Beneficios por operaciones con acciones y\n";
/* 552 */       this.listado.add(linea);
/* 553 */       linea = this.alinea.Izquierda("      obligaciones propias", 60) + this.alinea.Derecha(fn.format(pb[20]), 12) + "\n";
/* 554 */       this.listado.add(linea);
/* 555 */       linea = "  11. Subvenciones de capital transferidas al resultado\n";
/* 556 */       this.listado.add(linea);
/* 557 */       linea = this.alinea.Izquierda("      del ejercicio", 60) + this.alinea.Derecha(fn.format(pb[21]), 12) + "\n";
/* 558 */       this.listado.add(linea);
/* 559 */       linea = this.alinea.Izquierda("  12. Ingresos extraordinarios", 60) + this.alinea.Derecha(fn.format(pb[22]), 12) + "\n";
/* 560 */       this.listado.add(linea);
/* 561 */       linea = this.alinea.Izquierda("  13. Ingresos y beneficios de otros ejercicios", 60) + this.alinea.Derecha(fn.format(pb[23]), 12) + "\n";
/* 562 */       this.listado.add(linea);
/* 563 */       if (t[9] < 0.0D) {
/* 564 */         linea = "  IV. RESULTADOS EXTRAORDINARIOS NEGATIVOS\n";
/* 565 */         this.listado.add(linea);
/* 566 */         linea = this.alinea.Izquierda("      (A10+A11+A12+A13+A14-B9-B10-B11-B12-B13)", 60) + this.alinea.Derecha(fn.format(-t[9]), 12) + "\n";
/*     */ 
/* 568 */         this.listado.add(linea);
/*     */       }
/* 570 */       if (t[10] < 0.0D) {
/* 571 */         linea = this.alinea.Izquierda("   V. PÉRDIDAS ANTES DE IMPUESTOS (BIII+BIV-AIII-AIV)", 60) + this.alinea.Derecha(fn.format(-t[10]), 12) + "\n";
/*     */ 
/* 574 */         this.listado.add(linea);
/*     */       }
/* 576 */       if (t[11] < 0.0D) {
/* 577 */         linea = this.alinea.Izquierda("  VI. RESULTADO DEL EJERCICIO (PÉRDIDAS) (BV+A15+A16)", 60) + this.alinea.Derecha(fn.format(-t[11]), 12) + "\n";
/*     */ 
/* 580 */         this.listado.add(linea);
/*     */       }
/* 582 */       this.listar = true;
/*     */     }
/*     */     else
/*     */     {
/* 586 */       String linea = "";
/* 587 */       linea = "CUENTAS DE PÉRDIDAS Y GANANCIAS Abreviada para " + nombreEmpresa + "\n";
/* 588 */       this.listado.add(linea);
/* 589 */       linea = "Ejercicio: " + Inicio.p.getEjercicio() + ".  Entre " + fechaI.substring(6) + "-" + fechaI.substring(4, 6) + " y " + fechaF.substring(6) + "-" + fechaF.substring(4, 6) + "\n\n";
/*     */ 
/* 591 */       this.listado.add(linea);
/*     */ 
/* 593 */       double[] t = new double[20];
/* 594 */       for (int i = 0; i < 20; i++) t[i] = 0.0D;
/* 595 */       t[1] = pb[3];
/* 596 */       for (int i = 1; i < 4; i++) t[1] += pa[i];
/* 597 */       t[2] = (pa[4] + pa[5]);
/* 598 */       for (int i = 7; i < 10; i++) t[3] += pa[i];
/* 599 */       for (int i = 10; i < 14; i++) t[4] += pa[i];
/* 600 */       for (int i = 14; i < 18; i++) t[6] += pa[i];
/* 601 */       t[0] = (pa[6] + t[6]);
/* 602 */       for (int i = 1; i < 5; i++) t[0] += t[i];
/* 603 */       for (int i = 20; i < 27; i++) t[0] += pa[i];
/*     */ 
/* 605 */       for (int i = 0; i < 3; i++) t[14] += pb[i];
/* 606 */       for (int i = 4; i < 8; i++) t[15] += pb[i];
/* 607 */       t[13] = (t[14] + t[15]);
/* 608 */       t[17] = (pb[8] + pb[11] + pb[14]);
/* 609 */       t[18] = (pb[9] + pb[12] + pb[15]);
/* 610 */       t[19] = (pb[10] + pb[13] + pb[16]);
/* 611 */       t[16] = pb[17];
/* 612 */       for (int i = 17; i < 20; i++) t[16] += t[i];
/* 613 */       t[12] = (t[13] + t[16]);
/* 614 */       for (int i = 18; i < 24; i++) t[12] += pb[i];
/*     */ 
/* 616 */       t[5] = (t[13] - pa[6]);
/* 617 */       for (int i = 1; i < 5; i++) t[5] -= t[i];
/* 618 */       t[7] = (t[16] + pb[18] - t[6] - pa[18] - pa[19]);
/* 619 */       t[8] = (t[5] + t[7]);
/* 620 */       for (int i = 19; i < 24; i++) t[9] += pb[i];
/* 621 */       for (int i = 20; i < 25; i++) t[9] -= pa[i];
/* 622 */       t[10] = (t[8] + t[9]);
/* 623 */       t[11] = (t[10] - pa[25] - pa[26]);
/*     */ 
/* 625 */       linea = "DEBE\n";
/* 626 */       this.listado.add(linea);
/* 627 */       linea = this.alinea.Izquierda("A) GASTOS", 60) + this.alinea.Derecha(fn.format(t[0]), 12) + "\n";
/* 628 */       this.listado.add(linea);
/* 629 */       linea = this.alinea.Izquierda("   1. Consumos de explotación", 60) + this.alinea.Derecha(fn.format(t[1]), 12) + "\n";
/* 630 */       this.listado.add(linea);
/* 631 */       linea = this.alinea.Izquierda("   2. Gastos de personal", 60) + this.alinea.Derecha(fn.format(t[2]), 12) + "\n";
/* 632 */       this.listado.add(linea);
/* 633 */       linea = this.alinea.Izquierda("      a) Sueldos, salarios y asimilados", 60) + this.alinea.Derecha(fn.format(pa[4]), 12) + "\n";
/* 634 */       this.listado.add(linea);
/* 635 */       linea = this.alinea.Izquierda("      b) Cargas sociales", 60) + this.alinea.Derecha(fn.format(pa[5]), 12) + "\n";
/* 636 */       this.listado.add(linea);
/* 637 */       linea = this.alinea.Izquierda("   3. Dotaciones para amortizaciones de inmovilizado", 60) + this.alinea.Derecha(fn.format(pa[6]), 12) + "\n";
/* 638 */       this.listado.add(linea);
/* 639 */       linea = "   4. Variación de las provisiones de tráfico\n";
/* 640 */       this.listado.add(linea);
/* 641 */       linea = this.alinea.Izquierda("      y pérdidas de créditos incobrables", 60) + this.alinea.Derecha(fn.format(t[3]), 12) + "\n";
/* 642 */       this.listado.add(linea);
/* 643 */       linea = this.alinea.Izquierda("   5. Otros gastos de explotación", 60) + this.alinea.Derecha(fn.format(t[4]), 12) + "\n";
/* 644 */       this.listado.add(linea);
/* 645 */       if (t[5] >= 0.0D) {
/* 646 */         linea = this.alinea.Izquierda("   I. BENEFICIOS DE EXPLOTACIÓN (B1-A1-A2-A3-A4-A5)", 60) + this.alinea.Derecha(fn.format(t[5]), 12) + "\n";
/*     */ 
/* 648 */         this.listado.add(linea);
/*     */       }
/* 650 */       linea = this.alinea.Izquierda("   6. Gastos financieros y gastos asimilados", 60) + this.alinea.Derecha(fn.format(t[6]), 12) + "\n";
/* 651 */       this.listado.add(linea);
/* 652 */       linea = this.alinea.Izquierda("      a) Por deudas con empresas del grupo", 60) + this.alinea.Derecha(fn.format(pa[14]), 12) + "\n";
/* 653 */       this.listado.add(linea);
/* 654 */       linea = this.alinea.Izquierda("      b) Por deudas con empresas asociadas", 60) + this.alinea.Derecha(fn.format(pa[15]), 12) + "\n";
/* 655 */       this.listado.add(linea);
/* 656 */       linea = this.alinea.Izquierda("      c) Por otras deudas", 60) + this.alinea.Derecha(fn.format(pa[16]), 12) + "\n";
/* 657 */       this.listado.add(linea);
/* 658 */       linea = this.alinea.Izquierda("      d) Pérdidas de inversiones financieras", 60) + this.alinea.Derecha(fn.format(pa[17]), 12) + "\n";
/* 659 */       this.listado.add(linea);
/* 660 */       linea = "   7. Variación de las provisiones de inversiones\n";
/* 661 */       this.listado.add(linea);
/* 662 */       linea = this.alinea.Izquierda("      financieras", 60) + this.alinea.Derecha(fn.format(pa[18]), 12) + "\n";
/* 663 */       this.listado.add(linea);
/* 664 */       linea = this.alinea.Izquierda("   8. Diferencias negativas de cambio", 60) + this.alinea.Derecha(fn.format(pa[19]), 12) + "\n";
/* 665 */       this.listado.add(linea);
/* 666 */       if (t[7] >= 0.0D) {
/* 667 */         linea = this.alinea.Izquierda("  II. RESULTADOS FINANCIEROS POSITIVOS (B2+B3-A6-A7-A8)", 60) + this.alinea.Derecha(fn.format(t[7]), 12) + "\n";
/*     */ 
/* 670 */         this.listado.add(linea);
/*     */       }
/* 672 */       if (t[8] >= 0.0D) {
/* 673 */         linea = " III. BENEFICIOS DE LAS ACTIVIDADES ORDINARIAS\n";
/* 674 */         this.listado.add(linea);
/* 675 */         linea = this.alinea.Izquierda("      (AI+AII-BI-BII)", 60) + this.alinea.Derecha(fn.format(t[8]), 12) + "\n";
/*     */ 
/* 677 */         this.listado.add(linea);
/*     */       }
/* 679 */       linea = "   9. Variación de las provisiones de inmovilizado\n";
/* 680 */       this.listado.add(linea);
/* 681 */       linea = this.alinea.Izquierda("      inmaterial, material y cartera de control", 60) + this.alinea.Derecha(fn.format(pa[20]), 12) + "\n";
/* 682 */       this.listado.add(linea);
/* 683 */       linea = "  10. Pérdidas procedentes del inmovilizado\n";
/* 684 */       this.listado.add(linea);
/* 685 */       linea = this.alinea.Izquierda("      inmaterial, material y cartera de control", 60) + this.alinea.Derecha(fn.format(pa[21]), 12) + "\n";
/* 686 */       this.listado.add(linea);
/* 687 */       linea = "  11. Pérdidas por operaciones con acciones y\n";
/* 688 */       this.listado.add(linea);
/* 689 */       linea = this.alinea.Izquierda("      obligaciones propias", 60) + this.alinea.Derecha(fn.format(pa[22]), 12) + "\n";
/* 690 */       this.listado.add(linea);
/* 691 */       linea = this.alinea.Izquierda("  12. Gastos extraordinarios", 60) + this.alinea.Derecha(fn.format(pa[23]), 12) + "\n";
/* 692 */       this.listado.add(linea);
/* 693 */       linea = this.alinea.Izquierda("  13. Gastos y pérdidas de otros ejercicios", 60) + this.alinea.Derecha(fn.format(pa[24]), 12) + "\n";
/* 694 */       this.listado.add(linea);
/* 695 */       if (t[9] >= 0.0D) {
/* 696 */         linea = "  IV. RESULTADOS EXTRAORDINARIOS POSITIVOS\n";
/* 697 */         this.listado.add(linea);
/* 698 */         linea = this.alinea.Izquierda("      (B4+B5+B6+B7+B8-A9-A10-A11-A12-A13)", 60) + this.alinea.Derecha(fn.format(t[9]), 12) + "\n";
/*     */ 
/* 700 */         this.listado.add(linea);
/*     */       }
/* 702 */       if (t[10] >= 0.0D) {
/* 703 */         linea = this.alinea.Izquierda("   V. BENEFICIOS ANTES DE IMPUESTOS (AIII+AIV-BIII-BIV)", 60) + this.alinea.Derecha(fn.format(t[10]), 12) + "\n";
/*     */ 
/* 706 */         this.listado.add(linea);
/*     */       }
/* 708 */       linea = this.alinea.Izquierda("  14. Impuesto sobre sociedades", 60) + this.alinea.Derecha(fn.format(pa[25]), 12) + "\n";
/* 709 */       this.listado.add(linea);
/* 710 */       linea = this.alinea.Izquierda("  15. Otros impuestos", 60) + this.alinea.Derecha(fn.format(pa[26]), 12) + "\n";
/* 711 */       this.listado.add(linea);
/* 712 */       if (t[11] >= 0.0D) {
/* 713 */         linea = this.alinea.Izquierda("  VI. RESULTADO DEL EJERCICIO (BENEFICIOS) (AV-A14-A15)", 60) + this.alinea.Derecha(fn.format(t[11]), 12) + "\n";
/*     */ 
/* 716 */         this.listado.add(linea);
/*     */       }
/* 718 */       linea = "HABER\n";
/* 719 */       this.listado.add(linea);
/* 720 */       linea = this.alinea.Izquierda("B) INGRESOS", 60) + this.alinea.Derecha(fn.format(t[12]), 12) + "\n";
/* 721 */       this.listado.add(linea);
/* 722 */       linea = this.alinea.Izquierda("   1. Ingresos de explotación", 60) + this.alinea.Derecha(fn.format(t[13]), 12) + "\n";
/* 723 */       this.listado.add(linea);
/* 724 */       linea = this.alinea.Izquierda("      a) Importe neto de la cifra de negocios", 60) + this.alinea.Derecha(fn.format(t[14]), 12) + "\n";
/* 725 */       this.listado.add(linea);
/* 726 */       linea = this.alinea.Izquierda("      b) Otros ingresos de explotación", 60) + this.alinea.Derecha(fn.format(t[15]), 12) + "\n";
/* 727 */       this.listado.add(linea);
/* 728 */       if (t[5] < 0.0D) {
/* 729 */         linea = this.alinea.Izquierda("   I. PÉRDIDAS DE EXPLOTACIÓN (A1+A2+A3+A4+A5-B1)", 60) + this.alinea.Derecha(fn.format(-t[5]), 12) + "\n";
/*     */ 
/* 731 */         this.listado.add(linea);
/*     */       }
/* 733 */       linea = this.alinea.Izquierda("   2. Ingresos financieros", 60) + this.alinea.Derecha(fn.format(t[16]), 12) + "\n";
/* 734 */       this.listado.add(linea);
/* 735 */       linea = this.alinea.Izquierda("      a) En empresas del grupo", 60) + this.alinea.Derecha(fn.format(t[17]), 12) + "\n";
/* 736 */       this.listado.add(linea);
/* 737 */       linea = this.alinea.Izquierda("      b) En empresas asociadas", 60) + this.alinea.Derecha(fn.format(t[18]), 12) + "\n";
/* 738 */       this.listado.add(linea);
/* 739 */       linea = this.alinea.Izquierda("      c) Otros", 60) + this.alinea.Derecha(fn.format(t[19]), 12) + "\n";
/* 740 */       this.listado.add(linea);
/* 741 */       linea = this.alinea.Izquierda("      d) Beneficios en inversiones financieras", 60) + this.alinea.Derecha(fn.format(pb[17]), 12) + "\n";
/* 742 */       this.listado.add(linea);
/* 743 */       linea = this.alinea.Izquierda("   3. Diferencias positivas de cambio", 60) + this.alinea.Derecha(fn.format(pb[18]), 12) + "\n";
/* 744 */       this.listado.add(linea);
/* 745 */       if (t[7] < 0.0D) {
/* 746 */         linea = this.alinea.Izquierda("  II. RESULTADOS FINANCIEROS NEGATIVOS (A6+A7+A8-B2-B3)", 60) + this.alinea.Derecha(fn.format(-t[7]), 12) + "\n";
/*     */ 
/* 749 */         this.listado.add(linea);
/*     */       }
/* 751 */       if (t[8] < 0.0D) {
/* 752 */         linea = " III. PÉRDIDAS DE LAS ACTIVIDADES ORDINARIAS\n";
/* 753 */         this.listado.add(linea);
/* 754 */         linea = this.alinea.Izquierda("      (BI+BII-AI-AII)", 60) + this.alinea.Derecha(fn.format(-t[8]), 12) + "\n";
/*     */ 
/* 756 */         this.listado.add(linea);
/*     */       }
/* 758 */       linea = "   4. Beneficios en enajenación del inmovilizado\n";
/* 759 */       this.listado.add(linea);
/* 760 */       linea = this.alinea.Izquierda("      inmaterial, material y cartera de control", 60) + this.alinea.Derecha(fn.format(pb[19]), 12) + "\n";
/* 761 */       this.listado.add(linea);
/* 762 */       linea = "   5. Beneficios por operaciones con acciones\n";
/* 763 */       this.listado.add(linea);
/* 764 */       linea = this.alinea.Izquierda("      y obligaciones propias", 60) + this.alinea.Derecha(fn.format(pb[20]), 12) + "\n";
/* 765 */       this.listado.add(linea);
/* 766 */       linea = "   6. Subvenciones de capital transferidas al\n";
/* 767 */       this.listado.add(linea);
/* 768 */       linea = this.alinea.Izquierda("      resultado del ejercicio", 60) + this.alinea.Derecha(fn.format(pb[21]), 12) + "\n";
/* 769 */       this.listado.add(linea);
/* 770 */       linea = this.alinea.Izquierda("   7. Ingresos extraordinarios", 60) + this.alinea.Derecha(fn.format(pb[22]), 12) + "\n";
/* 771 */       this.listado.add(linea);
/* 772 */       linea = this.alinea.Izquierda("   8. Ingresos y beneficios de otros ejercicios", 60) + this.alinea.Derecha(fn.format(pb[23]), 12) + "\n";
/* 773 */       this.listado.add(linea);
/* 774 */       if (t[9] < 0.0D) {
/* 775 */         linea = "  IV. RESULTADOS EXTRAORDINARIOS NEGATIVOS\n";
/* 776 */         this.listado.add(linea);
/* 777 */         linea = this.alinea.Izquierda("      (A9+A10+A11+A12+A13-B4-B5-B6-B7-B8)", 60) + this.alinea.Derecha(fn.format(-t[9]), 12) + "\n";
/*     */ 
/* 779 */         this.listado.add(linea);
/*     */       }
/* 781 */       if (t[10] < 0.0D) {
/* 782 */         linea = this.alinea.Izquierda("   V. PÉRDIDAS ANTES DE IMPUESTOS (BIII+BIV-AIII-AIV)", 60) + this.alinea.Derecha(fn.format(-t[10]), 12) + "\n";
/*     */ 
/* 785 */         this.listado.add(linea);
/*     */       }
/* 787 */       if (t[11] < 0.0D) {
/* 788 */         linea = this.alinea.Izquierda("  VI. RESULTADO DEL EJERCICIO (PÉRDIDAS) (BV+A14+A15)", 60) + this.alinea.Derecha(fn.format(-t[11]), 12) + "\n";
/*     */ 
/* 791 */         this.listado.add(linea);
/*     */       }
/* 793 */       this.listar = true;
/*     */     }
/* 795 */     setCursor(Cursor.getPredefinedCursor(0));
/*     */   }
/*     */ 
/*     */   private double calculaGrupo(String grupo, String fechaI, String fechaF)
/*     */   {
/* 807 */     if ((grupo.equals("")) || (grupo.length() < 3)) return 0.0D;
/* 808 */     double importe = 0.0D; double importeDebe = 0.0D; double importeHaber = 0.0D;
/*     */     try {
/* 810 */       String ejercicio = fechaI.substring(0, 4);
/* 811 */       String cadenaSeleccion = "SELECT SUM(apt.importe) FROM apte" + ejercicio + " AS apt " + "JOIN scta" + ejercicio + " as scta ON apt.cuenta=scta.codigo " + "JOIN asto" + ejercicio + " as ast ON apt.id_asiento=ast.id_asiento " + "WHERE scta.gbalance = '" + grupo + "' AND " + "ast.fecha BETWEEN '" + fechaI + "' AND '" + fechaF + "' " + "AND ast.marca NOT LIKE 'G'";
/*     */ 
/* 818 */       this.res = Inicio.getCEmpresa().getRes(cadenaSeleccion + " AND apt.DH = 'D'");
/*     */ 
/* 820 */       if (this.res.next()) importeDebe = this.res.getDouble(1);
/* 821 */       this.res = Inicio.getCEmpresa().getRes(cadenaSeleccion + " AND apt.DH = 'H'");
/*     */ 
/* 823 */       if (this.res.next()) importeHaber = this.res.getDouble(1); 
/*     */     }
/* 825 */     catch (SQLException exc) { System.out.println(exc.getMessage()); }
/* 826 */     importe = grupo.substring(0, 3).equals("PGA") ? importeDebe - importeHaber : importeHaber - importeDebe;
/*     */ 
/* 829 */     if (!grupo.substring(grupo.length() - 1).equals("-"))
/* 830 */       importe += calculaGrupo(grupo + "-", fechaI, fechaF);
/* 831 */     return importe;
/*     */   }
/*     */ 
/*     */   protected static ImageIcon createImageIcon(String path)
/*     */   {
/* 841 */     URL imgURL = Balance.class.getResource(path);
/* 842 */     if (imgURL != null)
/* 843 */       return new ImageIcon(imgURL);
/* 844 */     System.err.println("Couldn't find file: " + path);
/* 845 */     return null;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.listados.CuentaPyG
 * JD-Core Version:    0.6.2
 */