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
/*     */ public class Balance extends JDialog
/*     */   implements ActionListener
/*     */ {
/*     */   private static final String ACEPTAR = "aceptar";
/*     */   private static final String CANCELAR = "cancelar";
/*  61 */   private AlinearCadena alinea = new AlinearCadena();
/*  62 */   String fecha = "";
/*  63 */   String fechaF = "";
/*  64 */   boolean listar = false;
/*  65 */   private List<String> listado = new ArrayList();
/*     */   private ResultSet res;
/*  67 */   JPanel panel1 = new JPanel();
/*  68 */   GridBagLayout gbLayout = new GridBagLayout();
/*  69 */   JLabel etiq1 = new JLabel();
/*  70 */   JLabel etiq2 = new JLabel();
/*  71 */   ICalendarTextField campoFecha = new ICalendarTextField();
/*  72 */   ICalendarTextField campoFechaF = new ICalendarTextField();
/*  73 */   JButton aceptar = new JButton();
/*  74 */   JButton cancelar = new JButton();
/*  75 */   JCheckBox orden = new JCheckBox();
/*  76 */   ImageIcon iconoAceptar = createImageIcon("/contaes/iconos/ok.png");
/*  77 */   ImageIcon iconoCancelar = createImageIcon("/contaes/iconos/cancel.png");
/*     */ 
/*     */   public Balance(Frame owner, String title, boolean modal)
/*     */   {
/*  87 */     super(owner, title, modal);
/*     */     try {
/*  89 */       setDefaultCloseOperation(2);
/*  90 */       initialize();
/*  91 */       pack();
/*     */     } catch (Exception exception) {
/*  93 */       exception.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public Balance(Frame owner, boolean modal) {
/*  98 */     this(owner, Mensajes.getString("balance"), modal);
/*     */   }
/*     */ 
/*     */   private void initialize()
/*     */     throws Exception
/*     */   {
/* 107 */     this.etiq1.setText(Mensajes.getString("fechaIni"));
/* 108 */     this.etiq2.setText(Mensajes.getString("fechaFin"));
/* 109 */     this.aceptar.setText(Mensajes.getString("aceptar"));
/* 110 */     this.aceptar.setIcon(this.iconoAceptar);
/* 111 */     this.aceptar.setVerticalTextPosition(0);
/* 112 */     this.aceptar.setHorizontalTextPosition(2);
/* 113 */     this.aceptar.setActionCommand("aceptar");
/* 114 */     this.aceptar.addActionListener(this);
/* 115 */     this.cancelar.setText(Mensajes.getString("cancelar"));
/* 116 */     this.cancelar.setIcon(this.iconoCancelar);
/* 117 */     this.cancelar.setVerticalTextPosition(0);
/* 118 */     this.cancelar.setHorizontalTextPosition(2);
/* 119 */     this.cancelar.setActionCommand("cancelar");
/* 120 */     this.cancelar.addActionListener(this);
/* 121 */     this.orden.setText("Abreviado");
/* 122 */     this.orden.setSelected(false);
/* 123 */     this.orden.setToolTipText(Mensajes.getString("balAbre"));
/* 124 */     this.campoFecha.setMinimumSize(new Dimension(100, 26));
/* 125 */     this.campoFecha.setToolTipText(Mensajes.getString("formatoFecha"));
/* 126 */     this.campoFecha.setComponente(this.campoFechaF);
/* 127 */     this.campoFechaF.setMinimumSize(new Dimension(100, 26));
/* 128 */     this.campoFechaF.setToolTipText(Mensajes.getString("formatoFecha"));
/* 129 */     this.campoFechaF.setComponente(this.orden);
/*     */ 
/* 131 */     this.panel1.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(1), BorderFactory.createEmptyBorder(5, 5, 5, 5))));
/*     */ 
/* 134 */     this.panel1.setLayout(this.gbLayout);
/*     */ 
/* 136 */     GridBagConstraints cons = new GridBagConstraints();
/* 137 */     cons.insets.bottom = 7;
/* 138 */     cons.insets.top = 7;
/* 139 */     cons.insets.left = 7;
/* 140 */     cons.insets.right = 7;
/*     */ 
/* 142 */     cons.gridy = 0;
/* 143 */     cons.gridx = 0;
/* 144 */     this.gbLayout.setConstraints(this.etiq1, cons);
/* 145 */     this.panel1.add(this.etiq1);
/*     */ 
/* 147 */     cons.gridx = 1;
/* 148 */     this.gbLayout.setConstraints(this.etiq2, cons);
/* 149 */     this.panel1.add(this.etiq2);
/*     */ 
/* 152 */     cons.fill = 2;
/* 153 */     cons.gridy = 1;
/* 154 */     cons.gridx = 0;
/* 155 */     this.gbLayout.setConstraints(this.campoFecha, cons);
/* 156 */     this.panel1.add(this.campoFecha);
/*     */ 
/* 158 */     cons.gridx = 1;
/* 159 */     this.gbLayout.setConstraints(this.campoFechaF, cons);
/* 160 */     this.panel1.add(this.campoFechaF);
/*     */ 
/* 163 */     cons.fill = 0;
/* 164 */     cons.anchor = 10;
/* 165 */     cons.gridy = 3;
/* 166 */     cons.gridx = 0;
/* 167 */     this.gbLayout.setConstraints(this.aceptar, cons);
/* 168 */     this.panel1.add(this.aceptar);
/*     */ 
/* 170 */     cons.gridx = 1;
/* 171 */     this.gbLayout.setConstraints(this.cancelar, cons);
/* 172 */     this.panel1.add(this.cancelar);
/*     */ 
/* 174 */     cons.gridy = 2;
/* 175 */     cons.gridx = 0;
/* 176 */     cons.gridwidth = 2;
/* 177 */     this.gbLayout.setConstraints(this.orden, cons);
/* 178 */     this.panel1.add(this.orden);
/*     */ 
/* 180 */     getContentPane().add(this.panel1);
/*     */   }
/*     */ 
/*     */   public void actionPerformed(ActionEvent e)
/*     */   {
/* 188 */     String cmd = e.getActionCommand();
/*     */ 
/* 190 */     if ("aceptar".equals(cmd)) {
/* 191 */       SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
/* 192 */       Date fecha_d = this.campoFecha.getDate();
/* 193 */       this.fecha = sdf.format(fecha_d);
/* 194 */       fecha_d = this.campoFechaF.getDate();
/* 195 */       this.fechaF = sdf.format(fecha_d);
/*     */ 
/* 197 */       if ((!this.fecha.equals("")) && (!this.fechaF.equals(""))) {
/* 198 */         hacerListado(this.fecha, this.fechaF);
/* 199 */         dispose();
/*     */       }
/* 201 */     } else if ("cancelar".equals(cmd)) {
/* 202 */       this.listar = false;
/* 203 */       dispose();
/*     */     }
/*     */   }
/*     */ 
/*     */   public List<String> textoListado()
/*     */   {
/* 213 */     return this.listado;
/*     */   }
/*     */ 
/*     */   public boolean Listar()
/*     */   {
/* 222 */     return this.listar;
/*     */   }
/*     */ 
/*     */   protected void hacerListado(String fechaI, String fechaF)
/*     */   {
/* 232 */     setCursor(Cursor.getPredefinedCursor(3));
/* 233 */     String nombreEmpresa = new ManejoEmpresas(Inicio.getCGeneral(), Inicio.p.getEmpresa()).getNombre(Inicio.p.getEmpresa());
/* 234 */     double[] ba = new double[53];
/* 235 */     double[] bp = new double[50];
/* 236 */     for (int i = 0; i < 53; i++) {
/* 237 */       ba[i] = 0.0D;
/*     */     }
/* 239 */     for (int i = 0; i < 50; i++) {
/* 240 */       bp[i] = 0.0D;
/*     */     }
/* 242 */     DecimalFormatSymbols formato = new DecimalFormatSymbols();
/* 243 */     formato.setDecimalSeparator(',');
/* 244 */     formato.setPerMill('.');
/* 245 */     DecimalFormat fn = new DecimalFormat("#,###,##0.00", formato);
/* 246 */     ba[0] = calculaGrupo("BAA", fechaI, fechaF);
/* 247 */     ba[1] = calculaGrupo("BABI", fechaI, fechaF);
/* 248 */     ba[2] = calculaGrupo("BABII1", fechaI, fechaF);
/* 249 */     ba[3] = calculaGrupo("BABII2", fechaI, fechaF);
/* 250 */     ba[4] = calculaGrupo("BABII3", fechaI, fechaF);
/* 251 */     ba[5] = calculaGrupo("BABII4", fechaI, fechaF);
/* 252 */     ba[6] = calculaGrupo("BABII5", fechaI, fechaF);
/* 253 */     ba[7] = calculaGrupo("BABII6", fechaI, fechaF);
/* 254 */     ba[8] = calculaGrupo("BABII7", fechaI, fechaF);
/* 255 */     ba[9] = calculaGrupo("BABII8", fechaI, fechaF);
/* 256 */     ba[10] = calculaGrupo("BABIII1", fechaI, fechaF);
/* 257 */     ba[11] = calculaGrupo("BABIII2", fechaI, fechaF);
/* 258 */     ba[12] = calculaGrupo("BABIII3", fechaI, fechaF);
/* 259 */     ba[13] = calculaGrupo("BABIII4", fechaI, fechaF);
/* 260 */     ba[14] = calculaGrupo("BABIII5", fechaI, fechaF);
/* 261 */     ba[15] = calculaGrupo("BABIII6", fechaI, fechaF);
/* 262 */     ba[16] = calculaGrupo("BABIII7", fechaI, fechaF);
/* 263 */     ba[17] = calculaGrupo("BABIV1", fechaI, fechaF);
/* 264 */     ba[18] = calculaGrupo("BABIV2", fechaI, fechaF);
/* 265 */     ba[19] = calculaGrupo("BABIV3", fechaI, fechaF);
/* 266 */     ba[20] = calculaGrupo("BABIV4", fechaI, fechaF);
/* 267 */     ba[21] = calculaGrupo("BABIV5", fechaI, fechaF);
/* 268 */     ba[22] = calculaGrupo("BABIV6", fechaI, fechaF);
/* 269 */     ba[23] = calculaGrupo("BABIV7", fechaI, fechaF);
/* 270 */     ba[24] = calculaGrupo("BABIV8", fechaI, fechaF);
/* 271 */     ba[25] = calculaGrupo("BABV", fechaI, fechaF);
/* 272 */     ba[26] = calculaGrupo("BAC", fechaI, fechaF);
/* 273 */     ba[27] = calculaGrupo("BADI", fechaI, fechaF);
/* 274 */     ba[28] = calculaGrupo("BADII1", fechaI, fechaF);
/* 275 */     ba[29] = calculaGrupo("BADII2", fechaI, fechaF);
/* 276 */     ba[30] = calculaGrupo("BADII3", fechaI, fechaF);
/* 277 */     ba[31] = calculaGrupo("BADII4", fechaI, fechaF);
/* 278 */     ba[32] = calculaGrupo("BADII5", fechaI, fechaF);
/* 279 */     ba[33] = calculaGrupo("BADII6", fechaI, fechaF);
/* 280 */     ba[34] = calculaGrupo("BADII7", fechaI, fechaF);
/* 281 */     ba[35] = calculaGrupo("BADIII1", fechaI, fechaF);
/* 282 */     ba[36] = calculaGrupo("BADIII2", fechaI, fechaF);
/* 283 */     ba[37] = calculaGrupo("BADIII3", fechaI, fechaF);
/* 284 */     ba[38] = calculaGrupo("BADIII4", fechaI, fechaF);
/* 285 */     ba[39] = calculaGrupo("BADIII5", fechaI, fechaF);
/* 286 */     ba[40] = calculaGrupo("BADIII6", fechaI, fechaF);
/* 287 */     ba[41] = calculaGrupo("BADIII7", fechaI, fechaF);
/* 288 */     ba[42] = calculaGrupo("BADIV1", fechaI, fechaF);
/* 289 */     ba[43] = calculaGrupo("BADIV2", fechaI, fechaF);
/* 290 */     ba[44] = calculaGrupo("BADIV3", fechaI, fechaF);
/* 291 */     ba[45] = calculaGrupo("BADIV4", fechaI, fechaF);
/* 292 */     ba[46] = calculaGrupo("BADIV5", fechaI, fechaF);
/* 293 */     ba[47] = calculaGrupo("BADIV6", fechaI, fechaF);
/* 294 */     ba[48] = calculaGrupo("BADIV7", fechaI, fechaF);
/* 295 */     ba[49] = calculaGrupo("BADIV8", fechaI, fechaF);
/* 296 */     ba[50] = calculaGrupo("BADV", fechaI, fechaF);
/* 297 */     ba[51] = calculaGrupo("BADVI", fechaI, fechaF);
/* 298 */     ba[52] = calculaGrupo("BADVII", fechaI, fechaF);
/*     */ 
/* 300 */     bp[0] = calculaGrupo("BPAI", fechaI, fechaF);
/* 301 */     bp[1] = calculaGrupo("BPAII", fechaI, fechaF);
/* 302 */     bp[2] = calculaGrupo("BPAIII", fechaI, fechaF);
/* 303 */     bp[3] = calculaGrupo("BPAIV1", fechaI, fechaF);
/* 304 */     bp[4] = calculaGrupo("BPAIV2", fechaI, fechaF);
/* 305 */     bp[5] = calculaGrupo("BPAIV3", fechaI, fechaF);
/* 306 */     bp[6] = calculaGrupo("BPAIV4", fechaI, fechaF);
/* 307 */     bp[7] = calculaGrupo("BPAIV5", fechaI, fechaF);
/* 308 */     bp[8] = calculaGrupo("BPAV1", fechaI, fechaF);
/* 309 */     bp[9] = calculaGrupo("BPAV2", fechaI, fechaF);
/* 310 */     bp[10] = calculaGrupo("BPAV3", fechaI, fechaF);
/* 311 */     bp[11] = calculaGrupo("BPAVI", fechaI, fechaF);
/* 312 */     bp[12] = calculaGrupo("BPAVII", fechaI, fechaF);
/* 313 */     bp[13] = calculaGrupo("BPB1", fechaI, fechaF);
/* 314 */     bp[14] = calculaGrupo("BPB2", fechaI, fechaF);
/* 315 */     bp[15] = calculaGrupo("BPB3", fechaI, fechaF);
/* 316 */     bp[16] = calculaGrupo("BPC1", fechaI, fechaF);
/* 317 */     bp[17] = calculaGrupo("BPC2", fechaI, fechaF);
/* 318 */     bp[18] = calculaGrupo("BPC3", fechaI, fechaF);
/* 319 */     bp[19] = calculaGrupo("BPC4", fechaI, fechaF);
/* 320 */     bp[20] = calculaGrupo("BPDI1", fechaI, fechaF);
/* 321 */     bp[21] = calculaGrupo("BPDI2", fechaI, fechaF);
/* 322 */     bp[22] = calculaGrupo("BPDI3", fechaI, fechaF);
/* 323 */     bp[23] = calculaGrupo("BPDII", fechaI, fechaF);
/* 324 */     bp[24] = calculaGrupo("BPDIII1", fechaI, fechaF);
/* 325 */     bp[25] = calculaGrupo("BPDIII2", fechaI, fechaF);
/* 326 */     bp[26] = calculaGrupo("BPDIV1", fechaI, fechaF);
/* 327 */     bp[27] = calculaGrupo("BPDIV2", fechaI, fechaF);
/* 328 */     bp[28] = calculaGrupo("BPDIV3", fechaI, fechaF);
/* 329 */     bp[29] = calculaGrupo("BPDV1", fechaI, fechaF);
/* 330 */     bp[30] = calculaGrupo("BPDV2", fechaI, fechaF);
/* 331 */     bp[31] = calculaGrupo("BPDV3", fechaI, fechaF);
/* 332 */     bp[32] = calculaGrupo("BPEI1", fechaI, fechaF);
/* 333 */     bp[33] = calculaGrupo("BPEI2", fechaI, fechaF);
/* 334 */     bp[34] = calculaGrupo("BPEI3", fechaI, fechaF);
/* 335 */     bp[35] = calculaGrupo("BPEI4", fechaI, fechaF);
/* 336 */     bp[36] = calculaGrupo("BPEII1", fechaI, fechaF);
/* 337 */     bp[37] = calculaGrupo("BPEII2", fechaI, fechaF);
/* 338 */     bp[38] = calculaGrupo("BPEIII1", fechaI, fechaF);
/* 339 */     bp[39] = calculaGrupo("BPEIII2", fechaI, fechaF);
/* 340 */     bp[40] = calculaGrupo("BPEIV1", fechaI, fechaF);
/* 341 */     bp[41] = calculaGrupo("BPEIV2", fechaI, fechaF);
/* 342 */     bp[42] = calculaGrupo("BPEIV3", fechaI, fechaF);
/* 343 */     bp[43] = calculaGrupo("BPEV1", fechaI, fechaF);
/* 344 */     bp[44] = calculaGrupo("BPEV2", fechaI, fechaF);
/* 345 */     bp[45] = calculaGrupo("BPEV3", fechaI, fechaF);
/* 346 */     bp[46] = calculaGrupo("BPEV4", fechaI, fechaF);
/* 347 */     bp[47] = calculaGrupo("BPEV5", fechaI, fechaF);
/* 348 */     bp[48] = calculaGrupo("BPEVI", fechaI, fechaF);
/* 349 */     bp[49] = calculaGrupo("BPEVII", fechaI, fechaF);
/*     */ 
/* 353 */     if (!this.orden.isSelected())
/*     */     {
/* 355 */       String linea = "";
/* 356 */       linea = Mensajes.getString("balance") + " " + Mensajes.getString("para") + " " + nombreEmpresa + ".   " + Mensajes.getString("ejercicio") + ": " + Inicio.p.getEjercicio() + "\n";
/* 357 */       this.listado.add(linea);
/* 358 */       linea = Mensajes.getString("entre") + " " + fechaI.substring(6) + "-" + fechaI.substring(4, 6) + " y " + fechaF.substring(6) + "-" + fechaF.substring(4, 6) + "\n\n";
/*     */ 
/* 360 */       this.listado.add(linea);
/* 361 */       double[] t = new double[26];
/* 362 */       for (int i = 0; i < 26; i++) {
/* 363 */         t[i] = 0.0D;
/*     */       }
/* 365 */       for (int i = 2; i < 10; i++) {
/* 366 */         t[1] += ba[i];
/*     */       }
/* 368 */       for (int i = 10; i < 17; i++) {
/* 369 */         t[2] += ba[i];
/*     */       }
/* 371 */       for (int i = 17; i < 25; i++) {
/* 372 */         t[3] += ba[i];
/*     */       }
/* 374 */       t[0] = (ba[0] + t[1] + t[2] + t[3] + ba[25]);
/* 375 */       for (int i = 28; i < 35; i++) {
/* 376 */         t[5] += ba[i];
/*     */       }
/* 378 */       for (int i = 35; i < 42; i++) {
/* 379 */         t[6] += ba[i];
/*     */       }
/* 381 */       for (int i = 42; i < 50; i++) {
/* 382 */         t[7] += ba[i];
/*     */       }
/* 384 */       t[4] = (ba[27] + t[5] + t[6] + t[7] + ba[50] + ba[51] + ba[52]);
/* 385 */       t[8] = (ba[0] + t[0] + ba[26] + t[4]);
/* 386 */       for (int i = 3; i < 8; i++) {
/* 387 */         t[10] += bp[i];
/*     */       }
/* 389 */       for (int i = 8; i < 11; i++) {
/* 390 */         t[11] += bp[i];
/*     */       }
/* 392 */       t[9] = (bp[0] + bp[1] + bp[2] + t[10] + t[11] + bp[11] + bp[12]);
/* 393 */       for (int i = 13; i < 16; i++) {
/* 394 */         t[12] += bp[i];
/*     */       }
/* 396 */       for (int i = 16; i < 20; i++) {
/* 397 */         t[13] += bp[i];
/*     */       }
/* 399 */       for (int i = 20; i < 23; i++) {
/* 400 */         t[15] += bp[i];
/*     */       }
/* 402 */       t[16] = (bp[24] + bp[25]);
/* 403 */       for (int i = 26; i < 29; i++) {
/* 404 */         t[17] += bp[i];
/*     */       }
/* 406 */       for (int i = 29; i < 32; i++) {
/* 407 */         t[18] += bp[i];
/*     */       }
/* 409 */       t[14] = (t[15] + bp[23] + t[16] + t[17] + t[18]);
/* 410 */       for (int i = 32; i < 36; i++) {
/* 411 */         t[20] += bp[i];
/*     */       }
/* 413 */       t[21] = (bp[36] + bp[37]);
/* 414 */       t[22] = (bp[38] + bp[39]);
/* 415 */       for (int i = 40; i < 43; i++) {
/* 416 */         t[23] += bp[i];
/*     */       }
/* 418 */       for (int i = 43; i < 48; i++) {
/* 419 */         t[24] += bp[i];
/*     */       }
/* 421 */       t[19] = (bp[48] + bp[49]);
/* 422 */       for (int i = 20; i < 25; i++) {
/* 423 */         t[19] += t[i];
/*     */       }
/* 425 */       t[25] = (t[9] + t[12] + t[13] + t[14] + t[19]);
/*     */ 
/* 427 */       linea = "ACTIVO\n";
/* 428 */       this.listado.add(linea);
/* 429 */       linea = this.alinea.Izquierda("A) Accionistas(socios) por desembolsos no exigidos", 60) + this.alinea.Derecha(fn.format(ba[0]), 12) + "\n";
/* 430 */       this.listado.add(linea);
/* 431 */       linea = this.alinea.Izquierda("B) Inmovilizado", 60) + this.alinea.Derecha(fn.format(t[0]), 12) + "\n";
/* 432 */       this.listado.add(linea);
/* 433 */       linea = this.alinea.Izquierda("    I. Gastos de establecimiento", 60) + this.alinea.Derecha(fn.format(ba[1]), 12) + "\n";
/* 434 */       this.listado.add(linea);
/* 435 */       linea = this.alinea.Izquierda("   II. Inmovilizaciones inmateriales", 60) + this.alinea.Derecha(fn.format(t[1]), 12) + "\n";
/* 436 */       this.listado.add(linea);
/* 437 */       linea = this.alinea.Izquierda("       1. Gastos de investigacion y desarrollo", 60) + this.alinea.Derecha(fn.format(ba[2]), 12) + "\n";
/* 438 */       this.listado.add(linea);
/* 439 */       linea = this.alinea.Izquierda("       2. Concesiones,patentes,licencias,marcas y similares", 60) + this.alinea.Derecha(fn.format(ba[3]), 12) + "\n";
/* 440 */       this.listado.add(linea);
/* 441 */       linea = this.alinea.Izquierda("       3. Fondo de comercio", 60) + this.alinea.Derecha(fn.format(ba[4]), 12) + "\n";
/* 442 */       this.listado.add(linea);
/* 443 */       linea = this.alinea.Izquierda("       4. Derechos de traspaso", 60) + this.alinea.Derecha(fn.format(ba[5]), 12) + "\n";
/* 444 */       this.listado.add(linea);
/* 445 */       linea = this.alinea.Izquierda("       5. Aplicaciones informáticas", 60) + this.alinea.Derecha(fn.format(ba[6]), 12) + "\n";
/* 446 */       this.listado.add(linea);
/* 447 */       linea = this.alinea.Izquierda("       6. Anticipos", 60) + this.alinea.Derecha(fn.format(ba[7]), 12) + "\n";
/* 448 */       this.listado.add(linea);
/* 449 */       linea = this.alinea.Izquierda("       7. Provisiones", 60) + this.alinea.Derecha(fn.format(ba[8]), 12) + "\n";
/* 450 */       this.listado.add(linea);
/* 451 */       linea = this.alinea.Izquierda("       8. Amortizaciones", 60) + this.alinea.Derecha(fn.format(ba[9]), 12) + "\n";
/* 452 */       this.listado.add(linea);
/* 453 */       linea = this.alinea.Izquierda("  III. Inmovilizaciones materiales", 60) + this.alinea.Derecha(fn.format(t[2]), 12) + "\n";
/* 454 */       this.listado.add(linea);
/* 455 */       linea = this.alinea.Izquierda("       1. Terrenos y construcciones", 60) + this.alinea.Derecha(fn.format(ba[10]), 12) + "\n";
/* 456 */       this.listado.add(linea);
/* 457 */       linea = this.alinea.Izquierda("       2. Instalaciones técnicas y maquinaria", 60) + this.alinea.Derecha(fn.format(ba[11]), 12) + "\n";
/* 458 */       this.listado.add(linea);
/* 459 */       linea = this.alinea.Izquierda("       3. Otras instalaciones,utillaje y mobiliario", 60) + this.alinea.Derecha(fn.format(ba[12]), 12) + "\n";
/* 460 */       this.listado.add(linea);
/* 461 */       linea = this.alinea.Izquierda("       4. Anticipos e inmovilizaciones materiales en curso", 60) + this.alinea.Derecha(fn.format(ba[13]), 12) + "\n";
/* 462 */       this.listado.add(linea);
/* 463 */       linea = this.alinea.Izquierda("       5. Otro inmovilizado", 60) + this.alinea.Derecha(fn.format(ba[14]), 12) + "\n";
/* 464 */       this.listado.add(linea);
/* 465 */       linea = this.alinea.Izquierda("       6. Provisiones", 60) + this.alinea.Derecha(fn.format(ba[15]), 12) + "\n";
/* 466 */       this.listado.add(linea);
/* 467 */       linea = this.alinea.Izquierda("       7. Amortizaciones", 60) + this.alinea.Derecha(fn.format(ba[16]), 12) + "\n";
/* 468 */       this.listado.add(linea);
/* 469 */       linea = this.alinea.Izquierda("   IV. Inmovilizaciones financieras", 60) + this.alinea.Derecha(fn.format(t[3]), 12) + "\n";
/* 470 */       this.listado.add(linea);
/* 471 */       linea = this.alinea.Izquierda("       1. Participaciones en empresas del grupo", 60) + this.alinea.Derecha(fn.format(ba[17]), 12) + "\n";
/* 472 */       this.listado.add(linea);
/* 473 */       linea = this.alinea.Izquierda("       2. Créditos a empresas del grupo", 60) + this.alinea.Derecha(fn.format(ba[18]), 12) + "\n";
/* 474 */       this.listado.add(linea);
/* 475 */       linea = this.alinea.Izquierda("       3. Participaciones en empresas asociadas", 60) + this.alinea.Derecha(fn.format(ba[19]), 12) + "\n";
/* 476 */       this.listado.add(linea);
/* 477 */       linea = this.alinea.Izquierda("       4. Créditos a empresas asociadas", 60) + this.alinea.Derecha(fn.format(ba[20]), 12) + "\n";
/* 478 */       this.listado.add(linea);
/* 479 */       linea = this.alinea.Izquierda("       5. Cartera de valores a largo plazo", 60) + this.alinea.Derecha(fn.format(ba[21]), 12) + "\n";
/* 480 */       this.listado.add(linea);
/* 481 */       linea = this.alinea.Izquierda("       6. Otros créditos", 60) + this.alinea.Derecha(fn.format(ba[22]), 12) + "\n";
/* 482 */       this.listado.add(linea);
/* 483 */       linea = this.alinea.Izquierda("       7. Depósitos y fianzas constituidos a largo plazo", 60) + this.alinea.Derecha(fn.format(ba[23]), 12) + "\n";
/* 484 */       this.listado.add(linea);
/* 485 */       linea = this.alinea.Izquierda("       8. Provisiones", 60) + this.alinea.Derecha(fn.format(ba[24]), 12) + "\n";
/* 486 */       this.listado.add(linea);
/* 487 */       linea = this.alinea.Izquierda("    V. Acciones propias", 60) + this.alinea.Derecha(fn.format(ba[25]), 12) + "\n";
/* 488 */       this.listado.add(linea);
/* 489 */       linea = this.alinea.Izquierda("C) Gastos a distribuir en varios ejercicios", 60) + this.alinea.Derecha(fn.format(ba[26]), 12) + "\n";
/* 490 */       this.listado.add(linea);
/* 491 */       linea = this.alinea.Izquierda("D) Activo circulante", 60) + this.alinea.Derecha(fn.format(t[4]), 12) + "\n";
/* 492 */       this.listado.add(linea);
/* 493 */       linea = this.alinea.Izquierda("    I. Accionistas por desembolsos exigidos", 60) + this.alinea.Derecha(fn.format(ba[27]), 12) + "\n";
/* 494 */       this.listado.add(linea);
/* 495 */       linea = this.alinea.Izquierda("   II. Existencias", 60) + this.alinea.Derecha(fn.format(t[5]), 12) + "\n";
/* 496 */       this.listado.add(linea);
/* 497 */       linea = this.alinea.Izquierda("       1. Comerciales", 60) + this.alinea.Derecha(fn.format(ba[28]), 12) + "\n";
/* 498 */       this.listado.add(linea);
/* 499 */       linea = this.alinea.Izquierda("       2. Materias Primas y otros aprovisionamientos", 60) + this.alinea.Derecha(fn.format(ba[29]), 12) + "\n";
/* 500 */       this.listado.add(linea);
/* 501 */       linea = this.alinea.Izquierda("       3. Productos en curso y semiterminados", 60) + this.alinea.Derecha(fn.format(ba[30]), 12) + "\n";
/* 502 */       this.listado.add(linea);
/* 503 */       linea = this.alinea.Izquierda("       4. Productos terminados", 60) + this.alinea.Derecha(fn.format(ba[31]), 12) + "\n";
/* 504 */       this.listado.add(linea);
/* 505 */       linea = this.alinea.Izquierda("       5. Subproductos, residuos y materiales recuperados", 60) + this.alinea.Derecha(fn.format(ba[32]), 12) + "\n";
/* 506 */       this.listado.add(linea);
/* 507 */       linea = this.alinea.Izquierda("       6. Anticipos", 60) + this.alinea.Derecha(fn.format(ba[33]), 12) + "\n";
/* 508 */       this.listado.add(linea);
/* 509 */       linea = this.alinea.Izquierda("       7. Provisiones", 60) + this.alinea.Derecha(fn.format(ba[34]), 12) + "\n";
/* 510 */       this.listado.add(linea);
/* 511 */       linea = this.alinea.Izquierda("  III. Deudores", 60) + this.alinea.Derecha(fn.format(t[6]), 12) + "\n";
/* 512 */       this.listado.add(linea);
/* 513 */       linea = this.alinea.Izquierda("       1. Clientes por ventas y prestaciones de servicios", 60) + this.alinea.Derecha(fn.format(ba[35]), 12) + "\n";
/* 514 */       this.listado.add(linea);
/* 515 */       linea = this.alinea.Izquierda("       2. Empresas del grupo, deudores", 60) + this.alinea.Derecha(fn.format(ba[36]), 12) + "\n";
/* 516 */       this.listado.add(linea);
/* 517 */       linea = this.alinea.Izquierda("       3. Empresas asociadas, deudores", 60) + this.alinea.Derecha(fn.format(ba[37]), 12) + "\n";
/* 518 */       this.listado.add(linea);
/* 519 */       linea = this.alinea.Izquierda("       4. Deudores varios", 60) + this.alinea.Derecha(fn.format(ba[38]), 12) + "\n";
/* 520 */       this.listado.add(linea);
/* 521 */       linea = this.alinea.Izquierda("       5. Personal", 60) + this.alinea.Derecha(fn.format(ba[39]), 12) + "\n";
/* 522 */       this.listado.add(linea);
/* 523 */       linea = this.alinea.Izquierda("       6. Administraciones Publicas", 60) + this.alinea.Derecha(fn.format(ba[40]), 12) + "\n";
/* 524 */       this.listado.add(linea);
/* 525 */       linea = this.alinea.Izquierda("       7. Provisiones", 60) + this.alinea.Derecha(fn.format(ba[41]), 12) + "\n";
/* 526 */       this.listado.add(linea);
/* 527 */       linea = this.alinea.Izquierda("   IV. Inversiones Financieras temporales", 60) + this.alinea.Derecha(fn.format(t[7]), 12) + "\n";
/* 528 */       this.listado.add(linea);
/* 529 */       linea = this.alinea.Izquierda("       1. Participaciones en empresas del grupo", 60) + this.alinea.Derecha(fn.format(ba[42]), 12) + "\n";
/* 530 */       this.listado.add(linea);
/* 531 */       linea = this.alinea.Izquierda("       2. Créditos a empresas del grupo", 60) + this.alinea.Derecha(fn.format(ba[43]), 12) + "\n";
/* 532 */       this.listado.add(linea);
/* 533 */       linea = this.alinea.Izquierda("       3. Participaciones en empresas asociadas", 60) + this.alinea.Derecha(fn.format(ba[44]), 12) + "\n";
/* 534 */       this.listado.add(linea);
/* 535 */       linea = this.alinea.Izquierda("       4. Créditos a empresas asociadas", 60) + this.alinea.Derecha(fn.format(ba[45]), 12) + "\n";
/* 536 */       this.listado.add(linea);
/* 537 */       linea = this.alinea.Izquierda("       5. Cartera de valores a corto plazo", 60) + this.alinea.Derecha(fn.format(ba[46]), 12) + "\n";
/* 538 */       this.listado.add(linea);
/* 539 */       linea = this.alinea.Izquierda("       6. Otros créditos", 60) + this.alinea.Derecha(fn.format(ba[47]), 12) + "\n";
/* 540 */       this.listado.add(linea);
/* 541 */       linea = this.alinea.Izquierda("       7. Depósitos y fianzas constituidos a corto plazo", 60) + this.alinea.Derecha(fn.format(ba[48]), 12) + "\n";
/* 542 */       this.listado.add(linea);
/* 543 */       linea = this.alinea.Izquierda("       8. Provisiones", 60) + this.alinea.Derecha(fn.format(ba[49]), 12) + "\n";
/* 544 */       this.listado.add(linea);
/* 545 */       linea = this.alinea.Izquierda("    V. Acciones propias a corto plazo", 60) + this.alinea.Derecha(fn.format(ba[50]), 12) + "\n";
/* 546 */       this.listado.add(linea);
/* 547 */       linea = this.alinea.Izquierda("   VI. Tesorería", 60) + this.alinea.Derecha(fn.format(ba[51]), 12) + "\n";
/* 548 */       this.listado.add(linea);
/* 549 */       linea = this.alinea.Izquierda("  VII. Ajustes por periodificación", 60) + this.alinea.Derecha(fn.format(ba[52]), 12) + "\n";
/* 550 */       this.listado.add(linea);
/* 551 */       linea = this.alinea.Izquierda("TOTAL GENERAL (A+B+C+D)", 60) + this.alinea.Derecha(fn.format(t[8]), 12) + "\n";
/* 552 */       this.listado.add(linea);
/* 553 */       linea = "\nPASIVO\n";
/* 554 */       this.listado.add(linea);
/* 555 */       linea = this.alinea.Izquierda("A) Fondos propios", 60) + this.alinea.Derecha(fn.format(t[9]), 12) + "\n";
/* 556 */       this.listado.add(linea);
/* 557 */       linea = this.alinea.Izquierda("    I. Capital suscrito", 60) + this.alinea.Derecha(fn.format(bp[0]), 12) + "\n";
/* 558 */       this.listado.add(linea);
/* 559 */       linea = this.alinea.Izquierda("   II. Primas de emisión", 60) + this.alinea.Derecha(fn.format(bp[1]), 12) + "\n";
/* 560 */       this.listado.add(linea);
/* 561 */       linea = this.alinea.Izquierda("  III. Reservas de revalorización", 60) + this.alinea.Derecha(fn.format(bp[2]), 12) + "\n";
/* 562 */       this.listado.add(linea);
/* 563 */       linea = this.alinea.Izquierda("   IV. Reservas", 60) + this.alinea.Derecha(fn.format(t[10]), 12) + "\n";
/* 564 */       this.listado.add(linea);
/* 565 */       linea = this.alinea.Izquierda("       1. Reserva legal", 60) + this.alinea.Derecha(fn.format(bp[3]), 12) + "\n";
/* 566 */       this.listado.add(linea);
/* 567 */       linea = this.alinea.Izquierda("       2. Reservas para acciones propias", 60) + this.alinea.Derecha(fn.format(bp[4]), 12) + "\n";
/* 568 */       this.listado.add(linea);
/* 569 */       linea = this.alinea.Izquierda("       3. Reservas para acciones de la sociedad dominante", 60) + this.alinea.Derecha(fn.format(bp[5]), 12) + "\n";
/* 570 */       this.listado.add(linea);
/* 571 */       linea = this.alinea.Izquierda("       4. Reservas estatuarias", 60) + this.alinea.Derecha(fn.format(bp[6]), 12) + "\n";
/* 572 */       this.listado.add(linea);
/* 573 */       linea = this.alinea.Izquierda("       5. Otras reservas", 60) + this.alinea.Derecha(fn.format(bp[7]), 12) + "\n";
/* 574 */       this.listado.add(linea);
/* 575 */       linea = this.alinea.Izquierda("    V. Resultados de ejercicios anteriores", 60) + this.alinea.Derecha(fn.format(t[11]), 12) + "\n";
/* 576 */       this.listado.add(linea);
/* 577 */       linea = this.alinea.Izquierda("       1. Remanente", 60) + this.alinea.Derecha(fn.format(bp[8]), 12) + "\n";
/* 578 */       this.listado.add(linea);
/* 579 */       linea = this.alinea.Izquierda("       2. Resultados negativos de ejercicios anteriores", 60) + this.alinea.Derecha(fn.format(bp[9]), 12) + "\n";
/* 580 */       this.listado.add(linea);
/* 581 */       linea = this.alinea.Izquierda("       3. Aportaciones de socios para compensación perdidas", 60) + this.alinea.Derecha(fn.format(bp[10]), 12) + "\n";
/* 582 */       this.listado.add(linea);
/* 583 */       linea = this.alinea.Izquierda("   VI. Pérdidas y Ganancias ( Beneficio o pérdida )", 60) + this.alinea.Derecha(fn.format(bp[11]), 12) + "\n";
/* 584 */       this.listado.add(linea);
/* 585 */       linea = this.alinea.Izquierda("  VII. Dividendos a cuenta entregado en el ejercicio", 60) + this.alinea.Derecha(fn.format(bp[12]), 12) + "\n";
/* 586 */       this.listado.add(linea);
/* 587 */       linea = this.alinea.Izquierda("B) Ingresos a distribuir entre varios ejercicios", 60) + this.alinea.Derecha(fn.format(t[12]), 12) + "\n";
/* 588 */       this.listado.add(linea);
/* 589 */       linea = this.alinea.Izquierda("       1. Subvenciones de capital", 60) + this.alinea.Derecha(fn.format(bp[13]), 12) + "\n";
/* 590 */       this.listado.add(linea);
/* 591 */       linea = this.alinea.Izquierda("       2. Diferencias positivas de cambio", 60) + this.alinea.Derecha(fn.format(bp[14]), 12) + "\n";
/* 592 */       this.listado.add(linea);
/* 593 */       linea = this.alinea.Izquierda("       3. Otros ingresos a distribuir en varios ejercicios", 60) + this.alinea.Derecha(fn.format(bp[15]), 12) + "\n";
/* 594 */       this.listado.add(linea);
/* 595 */       linea = this.alinea.Izquierda("C) Provisiones para riesgos y gastos", 60) + this.alinea.Derecha(fn.format(t[13]), 12) + "\n";
/* 596 */       this.listado.add(linea);
/* 597 */       linea = this.alinea.Izquierda("       1. Provisiones para pensiones y obligaciones simil.", 60) + this.alinea.Derecha(fn.format(bp[16]), 12) + "\n";
/* 598 */       this.listado.add(linea);
/* 599 */       linea = this.alinea.Izquierda("       2. Provisiones para impuestos", 60) + this.alinea.Derecha(fn.format(bp[17]), 12) + "\n";
/* 600 */       this.listado.add(linea);
/* 601 */       linea = this.alinea.Izquierda("       3. Otras provisiones", 60) + this.alinea.Derecha(fn.format(bp[18]), 12) + "\n";
/* 602 */       this.listado.add(linea);
/* 603 */       linea = this.alinea.Izquierda("       4. Fondo de reversión", 60) + this.alinea.Derecha(fn.format(bp[19]), 12) + "\n";
/* 604 */       this.listado.add(linea);
/* 605 */       linea = this.alinea.Izquierda("D) Acreedores a largo plazo", 60) + this.alinea.Derecha(fn.format(t[14]), 12) + "\n";
/* 606 */       this.listado.add(linea);
/* 607 */       linea = this.alinea.Izquierda("    I. Emisiones de obligaciones y otros valores negociabl.", 60) + this.alinea.Derecha(fn.format(t[15]), 12) + "\n";
/* 608 */       this.listado.add(linea);
/* 609 */       linea = this.alinea.Izquierda("       1. Obligaciones no convertibles", 60) + this.alinea.Derecha(fn.format(bp[20]), 12) + "\n";
/* 610 */       this.listado.add(linea);
/* 611 */       linea = this.alinea.Izquierda("       2. Obligaciones convertibles", 60) + this.alinea.Derecha(fn.format(bp[21]), 12) + "\n";
/* 612 */       this.listado.add(linea);
/* 613 */       linea = this.alinea.Izquierda("       3. Otras deudas representadas en valores negociables", 60) + this.alinea.Derecha(fn.format(bp[22]), 12) + "\n";
/* 614 */       this.listado.add(linea);
/* 615 */       linea = this.alinea.Izquierda("   II. Deudas con entidades de crédito", 60) + this.alinea.Derecha(fn.format(bp[23]), 12) + "\n";
/* 616 */       this.listado.add(linea);
/* 617 */       linea = this.alinea.Izquierda("  III. Deudas con empresas del grupo y asociadas", 60) + this.alinea.Derecha(fn.format(t[16]), 12) + "\n";
/* 618 */       this.listado.add(linea);
/* 619 */       linea = this.alinea.Izquierda("       1. Deudas con empresas del grupo", 60) + this.alinea.Derecha(fn.format(bp[24]), 12) + "\n";
/* 620 */       this.listado.add(linea);
/* 621 */       linea = this.alinea.Izquierda("       2. Deudas con empresas asociadas", 60) + this.alinea.Derecha(fn.format(bp[25]), 12) + "\n";
/* 622 */       this.listado.add(linea);
/* 623 */       linea = this.alinea.Izquierda("   IV. Otros acreedores", 60) + this.alinea.Derecha(fn.format(t[17]), 12) + "\n";
/* 624 */       this.listado.add(linea);
/* 625 */       linea = this.alinea.Izquierda("       1. Deudas representadas por efectos a pagar", 60) + this.alinea.Derecha(fn.format(bp[26]), 12) + "\n";
/* 626 */       this.listado.add(linea);
/* 627 */       linea = this.alinea.Izquierda("       2. Otras deudas", 60) + this.alinea.Derecha(fn.format(bp[27]), 12) + "\n";
/* 628 */       this.listado.add(linea);
/* 629 */       linea = this.alinea.Izquierda("       3. Fianzas y depósitos recibidos a largo plazo", 60) + this.alinea.Derecha(fn.format(bp[28]), 12) + "\n";
/* 630 */       this.listado.add(linea);
/* 631 */       linea = this.alinea.Izquierda("    V. Desembolsos pendientes sobre acciones no exigidos", 60) + this.alinea.Derecha(fn.format(t[18]), 12) + "\n";
/* 632 */       this.listado.add(linea);
/* 633 */       linea = this.alinea.Izquierda("       1. De empresas del grupo", 60) + this.alinea.Derecha(fn.format(bp[29]), 12) + "\n";
/* 634 */       this.listado.add(linea);
/* 635 */       linea = this.alinea.Izquierda("       2. De empresas asociadas", 60) + this.alinea.Derecha(fn.format(bp[30]), 12) + "\n";
/* 636 */       this.listado.add(linea);
/* 637 */       linea = this.alinea.Izquierda("       3. De otras empresas", 60) + this.alinea.Derecha(fn.format(bp[31]), 12) + "\n";
/* 638 */       this.listado.add(linea);
/* 639 */       linea = this.alinea.Izquierda("E) Acreedores a corto plazo", 60) + this.alinea.Derecha(fn.format(t[19]), 12) + "\n";
/* 640 */       this.listado.add(linea);
/* 641 */       linea = this.alinea.Izquierda("    I. Emisiones de obligaciones y otros valores negocia.", 60) + this.alinea.Derecha(fn.format(t[20]), 12) + "\n";
/* 642 */       this.listado.add(linea);
/* 643 */       linea = this.alinea.Izquierda("       1. Obligaciones no convertibles", 60) + this.alinea.Derecha(fn.format(bp[32]), 12) + "\n";
/* 644 */       this.listado.add(linea);
/* 645 */       linea = this.alinea.Izquierda("       2. Obligaciones convertibles", 60) + this.alinea.Derecha(fn.format(bp[33]), 12) + "\n";
/* 646 */       this.listado.add(linea);
/* 647 */       linea = this.alinea.Izquierda("       3. Otras deudas representadas en valores negociables", 60) + this.alinea.Derecha(fn.format(bp[34]), 12) + "\n";
/* 648 */       this.listado.add(linea);
/* 649 */       linea = this.alinea.Izquierda("       4. Intereses de obligaciones y otros valores", 60) + this.alinea.Derecha(fn.format(bp[35]), 12) + "\n";
/* 650 */       this.listado.add(linea);
/* 651 */       linea = this.alinea.Izquierda("   II. Deudas con entidades de crédito", 60) + this.alinea.Derecha(fn.format(t[21]), 12) + "\n";
/* 652 */       this.listado.add(linea);
/* 653 */       linea = this.alinea.Izquierda("       1. Prestamos y otras deudas", 60) + this.alinea.Derecha(fn.format(bp[36]), 12) + "\n";
/* 654 */       this.listado.add(linea);
/* 655 */       linea = this.alinea.Izquierda("       2. Deuda por intereses", 60) + this.alinea.Derecha(fn.format(bp[37]), 12) + "\n";
/* 656 */       this.listado.add(linea);
/* 657 */       linea = this.alinea.Izquierda("  III. Deudas con empresas del grupo y asociadas a corto", 60) + this.alinea.Derecha(fn.format(t[22]), 12) + "\n";
/* 658 */       this.listado.add(linea);
/* 659 */       linea = this.alinea.Izquierda("       1. Deudas con empresas del grupo", 60) + this.alinea.Derecha(fn.format(bp[38]), 12) + "\n";
/* 660 */       this.listado.add(linea);
/* 661 */       linea = this.alinea.Izquierda("       2. Deudas con empresas asociadas", 60) + this.alinea.Derecha(fn.format(bp[39]), 12) + "\n";
/* 662 */       this.listado.add(linea);
/* 663 */       linea = this.alinea.Izquierda("   IV. Acreedores comerciales", 60) + this.alinea.Derecha(fn.format(t[23]), 12) + "\n";
/* 664 */       this.listado.add(linea);
/* 665 */       linea = this.alinea.Izquierda("       1. Anticipos recibidos por pedidos", 60) + this.alinea.Derecha(fn.format(bp[40]), 12) + "\n";
/* 666 */       this.listado.add(linea);
/* 667 */       linea = this.alinea.Izquierda("       2. Deudas por compras o prestaciones de servicios", 60) + this.alinea.Derecha(fn.format(bp[41]), 12) + "\n";
/* 668 */       this.listado.add(linea);
/* 669 */       linea = this.alinea.Izquierda("       3. Deudas representadas por efectos a pagar", 60) + this.alinea.Derecha(fn.format(bp[42]), 12) + "\n";
/* 670 */       this.listado.add(linea);
/* 671 */       linea = this.alinea.Izquierda("    V. Otras deudas no comerciales", 60) + this.alinea.Derecha(fn.format(t[24]), 12) + "\n";
/* 672 */       this.listado.add(linea);
/* 673 */       linea = this.alinea.Izquierda("       1. Administraciones públicas", 60) + this.alinea.Derecha(fn.format(bp[43]), 12) + "\n";
/* 674 */       this.listado.add(linea);
/* 675 */       linea = this.alinea.Izquierda("       2. Deudas representadas por efectos a pagar", 60) + this.alinea.Derecha(fn.format(bp[44]), 12) + "\n";
/* 676 */       this.listado.add(linea);
/* 677 */       linea = this.alinea.Izquierda("       3. Otras deudas", 60) + this.alinea.Derecha(fn.format(bp[45]), 12) + "\n";
/* 678 */       this.listado.add(linea);
/* 679 */       linea = this.alinea.Izquierda("       4. Remuneraciones pendientes de pago", 60) + this.alinea.Derecha(fn.format(bp[46]), 12) + "\n";
/* 680 */       this.listado.add(linea);
/* 681 */       linea = this.alinea.Izquierda("       5. Fianzas y depositos recibidos a corto plazo", 60) + this.alinea.Derecha(fn.format(bp[47]), 12) + "\n";
/* 682 */       this.listado.add(linea);
/* 683 */       linea = this.alinea.Izquierda("   VI. Provisiones para operaciones de tráfico", 60) + this.alinea.Derecha(fn.format(bp[48]), 12) + "\n";
/* 684 */       this.listado.add(linea);
/* 685 */       linea = this.alinea.Izquierda("  VII. Ajustes por peridificación", 60) + this.alinea.Derecha(fn.format(bp[49]), 12) + "\n";
/* 686 */       this.listado.add(linea);
/* 687 */       linea = this.alinea.Izquierda("TOTAL GENERAL (A+B+C+D+E)", 60) + this.alinea.Derecha(fn.format(t[25]), 12) + "\n";
/* 688 */       this.listado.add(linea);
/* 689 */       this.listar = true;
/*     */     }
/*     */     else {
/* 692 */       String linea = "";
/* 693 */       linea = Mensajes.getString("balanceAb") + " " + Mensajes.getString("para") + " " + nombreEmpresa + ".   " + Mensajes.getString("ejercicio") + ": " + Inicio.p.getEjercicio() + "\n";
/* 694 */       this.listado.add(linea);
/* 695 */       linea = Mensajes.getString("entre") + " " + fechaI.substring(6) + "-" + fechaI.substring(4, 6) + " y " + fechaF.substring(6) + "-" + fechaF.substring(4, 6) + "\n\n";
/*     */ 
/* 697 */       this.listado.add(linea);
/*     */ 
/* 699 */       double[] t = new double[17];
/* 700 */       for (int i = 0; i < 17; i++) {
/* 701 */         t[i] = 0.0D;
/*     */       }
/* 703 */       for (int i = 2; i < 10; i++) {
/* 704 */         t[1] += ba[i];
/*     */       }
/* 706 */       for (int i = 10; i < 17; i++) {
/* 707 */         t[2] += ba[i];
/*     */       }
/* 709 */       for (int i = 17; i < 25; i++) {
/* 710 */         t[3] += ba[i];
/*     */       }
/* 712 */       t[0] = (ba[1] + ba[25]);
/* 713 */       for (int i = 1; i < 4; i++) {
/* 714 */         t[0] += t[i];
/*     */       }
/* 716 */       for (int i = 28; i < 35; i++) {
/* 717 */         t[5] += ba[i];
/*     */       }
/* 719 */       for (int i = 35; i < 42; i++) {
/* 720 */         t[6] += ba[i];
/*     */       }
/* 722 */       for (int i = 42; i < 50; i++) {
/* 723 */         t[7] += ba[i];
/*     */       }
/* 725 */       t[4] = ba[27];
/* 726 */       for (int i = 5; i < 8; i++) {
/* 727 */         t[4] += t[i];
/*     */       }
/* 729 */       for (int i = 50; i < 53; i++) {
/* 730 */         t[4] += ba[i];
/*     */       }
/* 732 */       t[8] = (ba[0] + t[0] + ba[26] + t[4]);
/*     */ 
/* 734 */       for (int i = 0; i < 13; i++) {
/* 735 */         t[9] += bp[i];
/*     */       }
/* 737 */       for (int i = 3; i < 8; i++) {
/* 738 */         t[10] += bp[i];
/*     */       }
/* 740 */       for (int i = 8; i < 11; i++) {
/* 741 */         t[11] += bp[i];
/*     */       }
/* 743 */       for (int i = 13; i < 16; i++) {
/* 744 */         t[12] += bp[i];
/*     */       }
/* 746 */       for (int i = 16; i < 20; i++) {
/* 747 */         t[13] += bp[i];
/*     */       }
/* 749 */       for (int i = 20; i < 32; i++) {
/* 750 */         t[14] += bp[i];
/*     */       }
/* 752 */       for (int i = 32; i < 50; i++) {
/* 753 */         t[15] += bp[i];
/*     */       }
/* 755 */       t[16] = (t[9] + t[12] + t[13] + t[14] + t[15]);
/*     */ 
/* 757 */       linea = "ACTIVO\n";
/* 758 */       this.listado.add(linea);
/* 759 */       linea = this.alinea.Izquierda("A) Accionistas (socios) por desembolsos no exigidos", 60) + this.alinea.Derecha(fn.format(ba[0]), 12) + "\n";
/* 760 */       this.listado.add(linea);
/* 761 */       linea = this.alinea.Izquierda("B) Inmovilizado", 60) + this.alinea.Derecha(fn.format(t[0]), 12) + "\n";
/* 762 */       this.listado.add(linea);
/* 763 */       linea = this.alinea.Izquierda("     I. Gastos de establecimiento", 60) + this.alinea.Derecha(fn.format(ba[1]), 12) + "\n";
/* 764 */       this.listado.add(linea);
/* 765 */       linea = this.alinea.Izquierda("    II. Inmovilizaciones inmateriales", 60) + this.alinea.Derecha(fn.format(t[1]), 12) + "\n";
/* 766 */       this.listado.add(linea);
/* 767 */       linea = this.alinea.Izquierda("   III. Inmovilizaciones materiales", 60) + this.alinea.Derecha(fn.format(t[2]), 12) + "\n";
/* 768 */       this.listado.add(linea);
/* 769 */       linea = this.alinea.Izquierda("    IV. Inmovilizaciones financieras", 60) + this.alinea.Derecha(fn.format(t[3]), 12) + "\n";
/* 770 */       this.listado.add(linea);
/* 771 */       linea = this.alinea.Izquierda("     V. Acciones propias", 60) + this.alinea.Derecha(fn.format(ba[25]), 12) + "\n";
/* 772 */       this.listado.add(linea);
/* 773 */       linea = this.alinea.Izquierda("C) Gastos a distribuir en varios ejercicios", 60) + this.alinea.Derecha(fn.format(ba[26]), 12) + "\n";
/* 774 */       this.listado.add(linea);
/* 775 */       linea = this.alinea.Izquierda("D) Activo circulante", 60) + this.alinea.Derecha(fn.format(t[4]), 12) + "\n";
/* 776 */       this.listado.add(linea);
/* 777 */       linea = this.alinea.Izquierda("     I. Accionistas por desembolsos exigidos", 60) + this.alinea.Derecha(fn.format(ba[27]), 12) + "\n";
/* 778 */       this.listado.add(linea);
/* 779 */       linea = this.alinea.Izquierda("    II. Existencias", 60) + this.alinea.Derecha(fn.format(t[5]), 12) + "\n";
/* 780 */       this.listado.add(linea);
/* 781 */       linea = this.alinea.Izquierda("   III. Deudores", 60) + this.alinea.Derecha(fn.format(t[6]), 12) + "\n";
/* 782 */       this.listado.add(linea);
/* 783 */       linea = this.alinea.Izquierda("    IV. Inversiones financieras temporales", 60) + this.alinea.Derecha(fn.format(t[7]), 12) + "\n";
/* 784 */       this.listado.add(linea);
/* 785 */       linea = this.alinea.Izquierda("     V. Acciones propias a corto plazo", 60) + this.alinea.Derecha(fn.format(ba[50]), 12) + "\n";
/* 786 */       this.listado.add(linea);
/* 787 */       linea = this.alinea.Izquierda("    VI. Tesorería", 60) + this.alinea.Derecha(fn.format(ba[51]), 12) + "\n";
/* 788 */       this.listado.add(linea);
/* 789 */       linea = this.alinea.Izquierda("   VII. Ajustes por periodificación", 60) + this.alinea.Derecha(fn.format(ba[52]), 12) + "\n";
/* 790 */       this.listado.add(linea);
/* 791 */       linea = this.alinea.Izquierda("TOTAL GENERAL (A+B+C+D)", 60) + this.alinea.Derecha(fn.format(t[8]), 12) + "\n";
/* 792 */       this.listado.add(linea);
/* 793 */       linea = "\nPASIVO\n";
/* 794 */       this.listado.add(linea);
/* 795 */       linea = this.alinea.Izquierda("A) Fondos propios", 60) + this.alinea.Derecha(fn.format(t[9]), 12) + "\n";
/* 796 */       this.listado.add(linea);
/* 797 */       linea = this.alinea.Izquierda("     I. Capital suscrito", 60) + this.alinea.Derecha(fn.format(bp[0]), 12) + "\n";
/* 798 */       this.listado.add(linea);
/* 799 */       linea = this.alinea.Izquierda("    II. Prima de emisión", 60) + this.alinea.Derecha(fn.format(bp[1]), 12) + "\n";
/* 800 */       this.listado.add(linea);
/* 801 */       linea = this.alinea.Izquierda("   III. Reserva de revalorización", 60) + this.alinea.Derecha(fn.format(bp[2]), 12) + "\n";
/* 802 */       this.listado.add(linea);
/* 803 */       linea = this.alinea.Izquierda("    IV. Reservas", 60) + this.alinea.Derecha(fn.format(t[10]), 12) + "\n";
/* 804 */       this.listado.add(linea);
/* 805 */       linea = this.alinea.Izquierda("     V. Resultados de ejercicios anteriores", 60) + this.alinea.Derecha(fn.format(t[11]), 12) + "\n";
/* 806 */       this.listado.add(linea);
/* 807 */       linea = this.alinea.Izquierda("    VI. Pérdidas y ganancias", 60) + this.alinea.Derecha(fn.format(bp[11]), 12) + "\n";
/* 808 */       this.listado.add(linea);
/* 809 */       linea = this.alinea.Izquierda("   VII. Dividendo a cuenta entregado en el ejercicio", 60) + this.alinea.Derecha(fn.format(bp[12]), 12) + "\n";
/* 810 */       this.listado.add(linea);
/* 811 */       linea = this.alinea.Izquierda("B) Ingresos a distribuir en varios ejercicios", 60) + this.alinea.Derecha(fn.format(t[12]), 12) + "\n";
/* 812 */       this.listado.add(linea);
/* 813 */       linea = this.alinea.Izquierda("C) Provisiones para riesgos y gastos", 60) + this.alinea.Derecha(fn.format(t[13]), 12) + "\n";
/* 814 */       this.listado.add(linea);
/* 815 */       linea = this.alinea.Izquierda("D) Acreedores a largo plazo", 60) + this.alinea.Derecha(fn.format(t[14]), 12) + "\n";
/* 816 */       this.listado.add(linea);
/* 817 */       linea = this.alinea.Izquierda("E) Acreedores a corto plazo", 60) + this.alinea.Derecha(fn.format(t[15]), 12) + "\n";
/* 818 */       this.listado.add(linea);
/* 819 */       linea = this.alinea.Izquierda("TOTAL GENERAL (A+B+C+D+E)", 60) + this.alinea.Derecha(fn.format(t[16]), 12) + "\n";
/* 820 */       this.listado.add(linea);
/* 821 */       this.listar = true;
/*     */     }
/* 823 */     setCursor(Cursor.getPredefinedCursor(0));
/*     */   }
/*     */ 
/*     */   private double calculaGrupo(String grupo, String fechaI, String fechaF)
/*     */   {
/* 835 */     if ((grupo.equals("")) || (grupo.length() < 2)) {
/* 836 */       return 0.0D;
/*     */     }
/* 838 */     double importe = 0.0D; double importeDebe = 0.0D; double importeHaber = 0.0D;
/*     */     try {
/* 840 */       String ejercicio = fechaI.substring(0, 4);
/* 841 */       String cadenaSeleccion = "SELECT SUM(apt.importe) FROM apte" + ejercicio + " AS apt " + "JOIN scta" + ejercicio + " as scta ON apt.cuenta=scta.codigo " + "JOIN asto" + ejercicio + " as ast ON apt.id_asiento=ast.id_asiento " + "WHERE scta.gbalance = '" + grupo + "' AND " + "ast.fecha BETWEEN '" + fechaI + "' AND '" + fechaF + "' " + "AND ast.marca NOT LIKE 'C'";
/*     */ 
/* 848 */       this.res = Inicio.getCEmpresa().getRes(cadenaSeleccion + " AND apt.DH = 'D'");
/*     */ 
/* 850 */       if (this.res.next()) {
/* 851 */         importeDebe = this.res.getDouble(1);
/*     */       }
/* 853 */       this.res = Inicio.getCEmpresa().getRes(cadenaSeleccion + " AND apt.DH = 'H'");
/*     */ 
/* 855 */       if (this.res.next())
/* 856 */         importeHaber = this.res.getDouble(1);
/*     */     }
/*     */     catch (SQLException exc) {
/* 859 */       System.out.println(exc.getMessage());
/*     */     }
/* 861 */     importe = grupo.substring(0, 2).equals("BA") ? importeDebe - importeHaber : importeHaber - importeDebe;
/* 862 */     if (!grupo.substring(grupo.length() - 1).equals("-")) {
/* 863 */       importe += calculaGrupo(grupo + "-", fechaI, fechaF);
/*     */     }
/* 865 */     return importe;
/*     */   }
/*     */ 
/*     */   protected static ImageIcon createImageIcon(String path)
/*     */   {
/* 875 */     URL imgURL = Balance.class.getResource(path);
/* 876 */     if (imgURL != null) {
/* 877 */       return new ImageIcon(imgURL);
/*     */     }
/* 879 */     System.err.println("Couldn't find file: " + path);
/* 880 */     return null;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.listados.Balance
 * JD-Core Version:    0.6.2
 */