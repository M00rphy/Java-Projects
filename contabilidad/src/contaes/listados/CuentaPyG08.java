/*     */ package contaes.listados;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.Puente;
/*     */ import contaes.calendario.ICalendarTextField;
/*     */ import contaes.manejoDatos.ManejoEmpresas;
/*     */ import contaes.manejoDatos.auxiliar.AddExtension;
/*     */ import contaes.manejoDatos.auxiliar.FinLinea;
/*     */ import contaes.manejoDatos.auxiliar.GrabarFichero;
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
/*     */ import java.io.File;
/*     */ import java.io.PrintStream;
/*     */ import java.net.URL;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.Formatter;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.List;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JFileChooser;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ public class CuentaPyG08 extends JDialog
/*     */   implements ActionListener
/*     */ {
/*     */   private static final String ACEPTAR = "aceptar";
/*     */   private static final String CANCELAR = "cancelar";
/*     */   private static final String CSV = "csv";
/*  68 */   String fecha = "";
/*  69 */   String fechaF = "";
/*  70 */   boolean listar = false;
/*  71 */   private List<String> listado = new ArrayList();
/*     */   private ResultSet res;
/*  75 */   JPanel panel1 = new JPanel();
/*  76 */   GridBagLayout gbLayout = new GridBagLayout();
/*  77 */   JLabel etiq1 = new JLabel();
/*  78 */   JLabel etiq2 = new JLabel();
/*  79 */   ICalendarTextField campoFecha = new ICalendarTextField();
/*  80 */   ICalendarTextField campoFechaF = new ICalendarTextField();
/*  81 */   JButton aceptar = new JButton();
/*  82 */   JButton cancelar = new JButton();
/*  83 */   JButton csv = new JButton();
/*  84 */   JCheckBox orden = new JCheckBox();
/*  85 */   ImageIcon iconoAceptar = createImageIcon("/contaes/iconos/ok.png");
/*  86 */   ImageIcon iconoCancelar = createImageIcon("/contaes/iconos/cancel.png");
/*  87 */   ImageIcon iconoCsv = createImageIcon("/almacen2/iconos/asCSV.png");
/*     */ 
/*     */   public CuentaPyG08(Frame owner, String title, boolean modal)
/*     */   {
/*  97 */     super(owner, title, modal);
/*     */     try {
/*  99 */       setDefaultCloseOperation(2);
/* 100 */       initialize();
/* 101 */       pack();
/*     */     }
/*     */     catch (Exception exception) {
/* 104 */       exception.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public CuentaPyG08(Frame owner, boolean modal) {
/* 109 */     this(owner, Mensajes.getString("ctaPG2"), modal);
/*     */   }
/*     */ 
/*     */   private void initialize()
/*     */     throws Exception
/*     */   {
/* 118 */     this.etiq1.setText(Mensajes.getString("fechaIni"));
/* 119 */     this.etiq2.setText(Mensajes.getString("fechaFin"));
/* 120 */     this.aceptar.setText(Mensajes.getString("aceptar"));
/* 121 */     this.aceptar.setIcon(this.iconoAceptar);
/* 122 */     this.aceptar.setVerticalTextPosition(0);
/* 123 */     this.aceptar.setHorizontalTextPosition(2);
/* 124 */     this.aceptar.setActionCommand("aceptar");
/* 125 */     this.aceptar.addActionListener(this);
/* 126 */     this.cancelar.setText(Mensajes.getString("cancelar"));
/* 127 */     this.cancelar.setIcon(this.iconoCancelar);
/* 128 */     this.cancelar.setVerticalTextPosition(0);
/* 129 */     this.cancelar.setHorizontalTextPosition(2);
/* 130 */     this.cancelar.setActionCommand("cancelar");
/* 131 */     this.csv.setText(Mensajes.getString("saveAsCsv"));
/* 132 */     this.csv.setIcon(this.iconoCsv);
/* 133 */     this.csv.setVerticalTextPosition(0);
/* 134 */     this.csv.setHorizontalTextPosition(2);
/* 135 */     this.csv.setActionCommand("csv");
/* 136 */     this.csv.addActionListener(this);
/* 137 */     this.cancelar.addActionListener(this);
/* 138 */     this.orden.setText(Mensajes.getString("nosaldoscero"));
/* 139 */     this.orden.setSelected(false);
/*     */ 
/* 142 */     this.campoFecha.setMinimumSize(new Dimension(100, 26));
/* 143 */     this.campoFecha.setToolTipText(Mensajes.getString("formatoFecha"));
/* 144 */     this.campoFecha.setComponente(this.campoFechaF);
/* 145 */     this.campoFechaF.setMinimumSize(new Dimension(100, 26));
/* 146 */     this.campoFechaF.setToolTipText(Mensajes.getString("formatoFecha"));
/* 147 */     this.campoFechaF.setComponente(this.orden);
/*     */ 
/* 149 */     this.panel1.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(1), BorderFactory.createEmptyBorder(5, 5, 5, 5))));
/*     */ 
/* 152 */     this.panel1.setLayout(this.gbLayout);
/*     */ 
/* 154 */     GridBagConstraints cons = new GridBagConstraints();
/* 155 */     cons.insets.bottom = 7;
/* 156 */     cons.insets.top = 7;
/* 157 */     cons.insets.left = 7;
/* 158 */     cons.insets.right = 7;
/*     */ 
/* 160 */     cons.gridy = 0;
/* 161 */     cons.gridx = 0;
/* 162 */     this.gbLayout.setConstraints(this.etiq1, cons);
/* 163 */     this.panel1.add(this.etiq1);
/*     */ 
/* 165 */     cons.gridx = 1;
/* 166 */     this.gbLayout.setConstraints(this.etiq2, cons);
/* 167 */     this.panel1.add(this.etiq2);
/*     */ 
/* 170 */     cons.fill = 2;
/* 171 */     cons.gridy = 1;
/* 172 */     cons.gridx = 0;
/* 173 */     this.gbLayout.setConstraints(this.campoFecha, cons);
/* 174 */     this.panel1.add(this.campoFecha);
/*     */ 
/* 176 */     cons.gridx = 1;
/* 177 */     this.gbLayout.setConstraints(this.campoFechaF, cons);
/* 178 */     this.panel1.add(this.campoFechaF);
/*     */ 
/* 181 */     cons.fill = 0;
/* 182 */     cons.anchor = 10;
/* 183 */     cons.gridy = 3;
/* 184 */     cons.gridx = 0;
/* 185 */     this.gbLayout.setConstraints(this.aceptar, cons);
/* 186 */     this.panel1.add(this.aceptar);
/*     */ 
/* 188 */     cons.gridx = 1;
/* 189 */     this.gbLayout.setConstraints(this.cancelar, cons);
/* 190 */     this.panel1.add(this.cancelar);
/*     */ 
/* 192 */     cons.gridy = 2;
/* 193 */     cons.gridx = 0;
/* 194 */     cons.gridwidth = 2;
/* 195 */     this.gbLayout.setConstraints(this.orden, cons);
/* 196 */     this.panel1.add(this.orden);
/*     */ 
/* 198 */     cons.fill = 0;
/* 199 */     cons.anchor = 10;
/* 200 */     cons.gridy = 4;
/* 201 */     cons.gridx = 0;
/* 202 */     this.gbLayout.setConstraints(this.csv, cons);
/* 203 */     this.panel1.add(this.csv);
/*     */ 
/* 205 */     getContentPane().add(this.panel1);
/*     */   }
/*     */ 
/*     */   public void actionPerformed(ActionEvent e)
/*     */   {
/* 213 */     String cmd = e.getActionCommand();
/*     */ 
/* 215 */     if ("aceptar".equals(cmd)) {
/* 216 */       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/* 217 */       Date fecha_d = this.campoFecha.getDate();
/* 218 */       this.fecha = sdf.format(fecha_d);
/* 219 */       fecha_d = this.campoFechaF.getDate();
/* 220 */       this.fechaF = sdf.format(fecha_d);
/*     */ 
/* 222 */       if ((!this.fecha.equals("")) && (!this.fechaF.equals(""))) {
/* 223 */         hacerListado(this.fecha, this.fechaF);
/* 224 */         dispose();
/*     */       }
/*     */     }
/* 227 */     else if ("csv".equals(cmd)) {
/* 228 */       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/* 229 */       Date fecha_d = this.campoFecha.getDate();
/* 230 */       this.fecha = sdf.format(fecha_d);
/* 231 */       fecha_d = this.campoFechaF.getDate();
/* 232 */       this.fechaF = sdf.format(fecha_d);
/*     */ 
/* 234 */       if ((!this.fecha.equals("")) && (!this.fechaF.equals(""))) {
/* 235 */         hacerListadoCsv(this.fecha, this.fechaF);
/* 236 */         this.listar = false;
/* 237 */         dispose();
/*     */       }
/*     */     }
/* 240 */     else if ("cancelar".equals(cmd)) {
/* 241 */       this.listar = false;
/* 242 */       dispose();
/*     */     }
/*     */   }
/*     */ 
/*     */   public List<String> textoListado()
/*     */   {
/* 252 */     return this.listado;
/*     */   }
/*     */ 
/*     */   public boolean Listar()
/*     */   {
/* 261 */     return this.listar;
/*     */   }
/*     */ 
/*     */   private void hacerListado(String fechaI, String fechaF)
/*     */   {
/* 271 */     boolean listarCeros = this.orden.isSelected();
/* 272 */     String EOL = FinLinea.get();
/* 273 */     setCursor(Cursor.getPredefinedCursor(3));
/* 274 */     String nombreEmpresa = new ManejoEmpresas(Inicio.getCGeneral(), Inicio.p.getEmpresa()).getNombre(Inicio.p.getEmpresa());
/*     */ 
/* 277 */     double[] pg = new double[17];
/* 278 */     for (int i = 0; i < 17; i++) {
/* 279 */       pg[i] = 0.0D;
/*     */     }
/*     */ 
/* 282 */     pg[0] = calculaGrupo("G1", fechaI, fechaF);
/* 283 */     pg[1] = calculaGrupo("G2", fechaI, fechaF);
/* 284 */     pg[2] = calculaGrupo("G3", fechaI, fechaF);
/* 285 */     pg[3] = calculaGrupo("G4", fechaI, fechaF);
/* 286 */     pg[4] = calculaGrupo("G5", fechaI, fechaF);
/* 287 */     pg[5] = calculaGrupo("G6", fechaI, fechaF);
/* 288 */     pg[6] = calculaGrupo("G7", fechaI, fechaF);
/* 289 */     pg[7] = calculaGrupo("G8", fechaI, fechaF);
/* 290 */     pg[8] = calculaGrupo("G9", fechaI, fechaF);
/* 291 */     pg[9] = calculaGrupo("G10", fechaI, fechaF);
/* 292 */     pg[10] = calculaGrupo("G11", fechaI, fechaF);
/* 293 */     pg[11] = calculaGrupo("G12", fechaI, fechaF);
/* 294 */     pg[12] = calculaGrupo("G13", fechaI, fechaF);
/* 295 */     pg[13] = calculaGrupo("G14", fechaI, fechaF);
/* 296 */     pg[14] = calculaGrupo("G15", fechaI, fechaF);
/* 297 */     pg[15] = calculaGrupo("G16", fechaI, fechaF);
/* 298 */     pg[16] = calculaGrupo("G17", fechaI, fechaF);
/* 299 */     double A = 0.0D; double B = 0.0D; double C = 0.0D; double D = 0.0D;
/* 300 */     for (int i = 0; i < 11; i++) A += pg[i];
/* 301 */     for (int i = 11; i < 16; i++) B += pg[i];
/* 302 */     C = A + B;
/* 303 */     D = C + pg[16];
/*     */ 
/* 306 */     String fechaIW = pastYear(fechaI);
/* 307 */     String fechaFW = pastYear(fechaF);
/* 308 */     double[] pgW = new double[17];
/* 309 */     for (int i = 0; i < 17; i++) {
/* 310 */       pgW[i] = 0.0D;
/*     */     }
/*     */ 
/* 313 */     pgW[0] = calculaGrupo("G1", fechaIW, fechaFW);
/* 314 */     pgW[1] = calculaGrupo("G2", fechaIW, fechaFW);
/* 315 */     pgW[2] = calculaGrupo("G3", fechaIW, fechaFW);
/* 316 */     pgW[3] = calculaGrupo("G4", fechaIW, fechaFW);
/* 317 */     pgW[4] = calculaGrupo("G5", fechaIW, fechaFW);
/* 318 */     pgW[5] = calculaGrupo("G6", fechaIW, fechaFW);
/* 319 */     pgW[6] = calculaGrupo("G7", fechaIW, fechaFW);
/* 320 */     pgW[7] = calculaGrupo("G8", fechaIW, fechaFW);
/* 321 */     pgW[8] = calculaGrupo("G9", fechaIW, fechaFW);
/* 322 */     pgW[9] = calculaGrupo("G10", fechaIW, fechaFW);
/* 323 */     pgW[10] = calculaGrupo("G11", fechaIW, fechaFW);
/* 324 */     pgW[11] = calculaGrupo("G12", fechaIW, fechaFW);
/* 325 */     pgW[12] = calculaGrupo("G13", fechaIW, fechaFW);
/* 326 */     pgW[13] = calculaGrupo("G14", fechaIW, fechaFW);
/* 327 */     pgW[14] = calculaGrupo("G15", fechaIW, fechaFW);
/* 328 */     pgW[15] = calculaGrupo("G16", fechaIW, fechaFW);
/* 329 */     pgW[16] = calculaGrupo("G17", fechaIW, fechaFW);
/* 330 */     double AW = 0.0D; double BW = 0.0D; double CW = 0.0D; double DW = 0.0D;
/* 331 */     for (int i = 0; i < 11; i++) AW += pgW[i];
/* 332 */     for (int i = 11; i < 16; i++) BW += pgW[i];
/* 333 */     CW = AW + BW;
/* 334 */     DW = CW + pgW[16];
/*     */ 
/* 337 */     StringBuilder sb = new StringBuilder();
/* 338 */     Formatter formater = new Formatter(sb);
/* 339 */     formater.format(new StringBuilder().append("%s %s").append(EOL).append(EOL).append("Entre %s-%s y %s-%-53s %s").append(EOL).append(EOL).toString(), new Object[] { "CUENTAS DE PÉRDIDAS Y GANANCIAS para ", nombreEmpresa, fechaI.substring(6), fechaI.substring(4, 6), fechaF.substring(6), fechaF.substring(4, 6), Mensajes.getString("ejercicio") });
/*     */ 
/* 343 */     formater.format(new StringBuilder().append("%73s %14s").append(EOL).toString(), new Object[] { Inicio.p.getEjercicio(), Integer.toString(Integer.parseInt(Inicio.p.getEjercicio()) - 1) });
/* 344 */     formater.format(new StringBuilder().append("%s").append(EOL).toString(), new Object[] { new StringBuilder().append("(Debe)Haber").append(EOL).append(EOL).toString() });
/* 345 */     if ((!listarCeros) || ((listarCeros) && ((pg[0] != 0.0D) || (pgW[0] != 0.0D))))
/* 346 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "1. Importe neto de la cifra de negocios", Double.valueOf(pg[0]), Double.valueOf(pgW[0]) });
/* 347 */     if ((!listarCeros) || ((listarCeros) && ((pg[1] != 0.0D) || (pgW[1] != 0.0D)))) {
/* 348 */       formater.format(new StringBuilder().append("%s").append(EOL).toString(), new Object[] { "2. Variación de existencia de productos terminados" });
/* 349 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "   y en curso de fabricación", Double.valueOf(pg[1]), Double.valueOf(pgW[0]) });
/*     */     }
/* 351 */     if ((!listarCeros) || ((listarCeros) && ((pg[2] != 0.0D) || (pgW[2] != 0.0D))))
/* 352 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "3. Trabajos realizados por la empresa para su activo", Double.valueOf(pg[2]), Double.valueOf(pgW[2]) });
/* 353 */     if ((!listarCeros) || ((listarCeros) && ((pg[3] != 0.0D) || (pgW[3] != 0.0D))))
/* 354 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "4. Aprovisionamientos", Double.valueOf(pg[3]), Double.valueOf(pgW[3]) });
/* 355 */     if ((!listarCeros) || ((listarCeros) && ((pg[4] != 0.0D) || (pgW[4] != 0.0D))))
/* 356 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "5. Otros ingresos de explotación", Double.valueOf(pg[4]), Double.valueOf(pgW[4]) });
/* 357 */     if ((!listarCeros) || ((listarCeros) && ((pg[5] != 0.0D) || (pgW[5] != 0.0D))))
/* 358 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "6. Gastos de personal", Double.valueOf(pg[5]), Double.valueOf(pgW[5]) });
/* 359 */     if ((!listarCeros) || ((listarCeros) && ((pg[6] != 0.0D) || (pgW[6] != 0.0D))))
/* 360 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "7. Otros gastos de explotación", Double.valueOf(pg[6]), Double.valueOf(pgW[6]) });
/* 361 */     if ((!listarCeros) || ((listarCeros) && ((pg[7] != 0.0D) || (pgW[7] != 0.0D))))
/* 362 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "8. Amortización del inmovilizado", Double.valueOf(pg[7]), Double.valueOf(pgW[7]) });
/* 363 */     if ((!listarCeros) || ((listarCeros) && ((pg[8] != 0.0D) || (pgW[8] != 0.0D)))) {
/* 364 */       formater.format(new StringBuilder().append("%s").append(EOL).toString(), new Object[] { "9. Imputación de subvenciones de inmovilizado" });
/* 365 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "   no financiero y otras", Double.valueOf(pg[8]), Double.valueOf(pgW[8]) });
/*     */     }
/* 367 */     if ((!listarCeros) || ((listarCeros) && ((pg[9] != 0.0D) || (pgW[9] != 0.0D))))
/* 368 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "10. Excesos de provisiones", Double.valueOf(pg[9]), Double.valueOf(pgW[9]) });
/* 369 */     if ((!listarCeros) || ((listarCeros) && ((pg[10] != 0.0D) || (pgW[10] != 0.0D)))) {
/* 370 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "11. Deterioro y resultado por enajenaciones del inmovilizado", Double.valueOf(pg[10]), Double.valueOf(pgW[10]) });
/*     */     }
/* 372 */     formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).append(EOL).toString(), new Object[] { "A) RESULTADO DE EXPLOTACIÓN", Double.valueOf(A), Double.valueOf(AW) });
/* 373 */     if ((!listarCeros) || ((listarCeros) && ((pg[11] != 0.0D) || (pgW[11] != 0.0D))))
/* 374 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "12. Ingresos financieros", Double.valueOf(pg[11]), Double.valueOf(pgW[11]) });
/* 375 */     if ((!listarCeros) || ((listarCeros) && ((pg[12] != 0.0D) || (pgW[12] != 0.0D))))
/* 376 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "13. Gastos financieros", Double.valueOf(pg[12]), Double.valueOf(pgW[12]) });
/* 377 */     if ((!listarCeros) || ((listarCeros) && ((pg[13] != 0.0D) || (pgW[13] != 0.0D))))
/* 378 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "14. Variación de valor razonable en instrumentos financieros", Double.valueOf(pg[13]), Double.valueOf(pgW[13]) });
/* 379 */     if ((!listarCeros) || ((listarCeros) && ((pg[14] != 0.0D) || (pgW[14] != 0.0D))))
/* 380 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "15. Diferencias de cambio", Double.valueOf(pg[14]), Double.valueOf(pgW[14]) });
/* 381 */     if ((!listarCeros) || ((listarCeros) && ((pg[15] != 0.0D) || (pgW[15] != 0.0D)))) {
/* 382 */       formater.format(new StringBuilder().append("%s").append(EOL).toString(), new Object[] { "16. Deterioro y resultado por enajenaciones de instrumentos" });
/* 383 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).append(EOL).toString(), new Object[] { "    finacieros", Double.valueOf(pg[15]), Double.valueOf(pgW[15]) });
/*     */     }
/* 385 */     formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).append(EOL).toString(), new Object[] { "B) RESULTADO FINANCIERO", Double.valueOf(B), Double.valueOf(BW) });
/* 386 */     formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).append(EOL).toString(), new Object[] { "C) RESULTADO ANTES DE IMPUESTOS", Double.valueOf(C), Double.valueOf(CW) });
/* 387 */     if ((!listarCeros) || ((listarCeros) && ((pg[16] != 0.0D) || (pgW[16] != 0.0D))))
/* 388 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).append(EOL).toString(), new Object[] { "17. Impuesto sobre beneficios", Double.valueOf(pg[16]), Double.valueOf(pgW[16]) });
/* 389 */     formater.format("%-60s %,13.2f %,13.2f", new Object[] { "D) RESULTADO DEL EJERCICIO", Double.valueOf(D), Double.valueOf(DW) });
/* 390 */     this.listado.add(sb.toString());
/* 391 */     this.listar = true;
/* 392 */     setCursor(Cursor.getPredefinedCursor(0));
/*     */   }
/*     */ 
/*     */   private void hacerListadoCsv(String fechaI, String fechaF) {
/* 396 */     boolean listarCeros = this.orden.isSelected();
/* 397 */     String EOL = FinLinea.get();
/* 398 */     setCursor(Cursor.getPredefinedCursor(3));
/* 399 */     String nombreEmpresa = new ManejoEmpresas(Inicio.getCGeneral(), Inicio.p.getEmpresa()).getNombre(Inicio.p.getEmpresa());
/*     */ 
/* 402 */     double[] pg = new double[17];
/* 403 */     for (int i = 0; i < 17; i++) {
/* 404 */       pg[i] = 0.0D;
/*     */     }
/*     */ 
/* 407 */     pg[0] = calculaGrupo("G1", fechaI, fechaF);
/* 408 */     pg[1] = calculaGrupo("G2", fechaI, fechaF);
/* 409 */     pg[2] = calculaGrupo("G3", fechaI, fechaF);
/* 410 */     pg[3] = calculaGrupo("G4", fechaI, fechaF);
/* 411 */     pg[4] = calculaGrupo("G5", fechaI, fechaF);
/* 412 */     pg[5] = calculaGrupo("G6", fechaI, fechaF);
/* 413 */     pg[6] = calculaGrupo("G7", fechaI, fechaF);
/* 414 */     pg[7] = calculaGrupo("G8", fechaI, fechaF);
/* 415 */     pg[8] = calculaGrupo("G9", fechaI, fechaF);
/* 416 */     pg[9] = calculaGrupo("G10", fechaI, fechaF);
/* 417 */     pg[10] = calculaGrupo("G11", fechaI, fechaF);
/* 418 */     pg[11] = calculaGrupo("G12", fechaI, fechaF);
/* 419 */     pg[12] = calculaGrupo("G13", fechaI, fechaF);
/* 420 */     pg[13] = calculaGrupo("G14", fechaI, fechaF);
/* 421 */     pg[14] = calculaGrupo("G15", fechaI, fechaF);
/* 422 */     pg[15] = calculaGrupo("G16", fechaI, fechaF);
/* 423 */     pg[16] = calculaGrupo("G17", fechaI, fechaF);
/* 424 */     double A = 0.0D; double B = 0.0D; double C = 0.0D; double D = 0.0D;
/* 425 */     for (int i = 0; i < 11; i++) A += pg[i];
/* 426 */     for (int i = 11; i < 16; i++) B += pg[i];
/* 427 */     C = A + B;
/* 428 */     D = C + pg[16];
/*     */ 
/* 431 */     String fechaIW = pastYear(fechaI);
/* 432 */     String fechaFW = pastYear(fechaF);
/* 433 */     double[] pgW = new double[17];
/* 434 */     for (int i = 0; i < 17; i++) {
/* 435 */       pgW[i] = 0.0D;
/*     */     }
/*     */ 
/* 438 */     pgW[0] = calculaGrupo("G1", fechaIW, fechaFW);
/* 439 */     pgW[1] = calculaGrupo("G2", fechaIW, fechaFW);
/* 440 */     pgW[2] = calculaGrupo("G3", fechaIW, fechaFW);
/* 441 */     pgW[3] = calculaGrupo("G4", fechaIW, fechaFW);
/* 442 */     pgW[4] = calculaGrupo("G5", fechaIW, fechaFW);
/* 443 */     pgW[5] = calculaGrupo("G6", fechaIW, fechaFW);
/* 444 */     pgW[6] = calculaGrupo("G7", fechaIW, fechaFW);
/* 445 */     pgW[7] = calculaGrupo("G8", fechaIW, fechaFW);
/* 446 */     pgW[8] = calculaGrupo("G9", fechaIW, fechaFW);
/* 447 */     pgW[9] = calculaGrupo("G10", fechaIW, fechaFW);
/* 448 */     pgW[10] = calculaGrupo("G11", fechaIW, fechaFW);
/* 449 */     pgW[11] = calculaGrupo("G12", fechaIW, fechaFW);
/* 450 */     pgW[12] = calculaGrupo("G13", fechaIW, fechaFW);
/* 451 */     pgW[13] = calculaGrupo("G14", fechaIW, fechaFW);
/* 452 */     pgW[14] = calculaGrupo("G15", fechaIW, fechaFW);
/* 453 */     pgW[15] = calculaGrupo("G16", fechaIW, fechaFW);
/* 454 */     pgW[16] = calculaGrupo("G17", fechaIW, fechaFW);
/* 455 */     double AW = 0.0D; double BW = 0.0D; double CW = 0.0D; double DW = 0.0D;
/* 456 */     for (int i = 0; i < 11; i++) AW += pgW[i];
/* 457 */     for (int i = 11; i < 16; i++) BW += pgW[i];
/* 458 */     CW = AW + BW;
/* 459 */     DW = CW + pgW[16];
/*     */ 
/* 462 */     StringBuilder sb = new StringBuilder();
/* 463 */     Formatter formater = new Formatter(sb);
/* 464 */     formater.format(new StringBuilder().append("%s %s").append(EOL).append(EOL).append("Entre %s-%s y %s-%s").append(EOL).append(EOL).toString(), new Object[] { "CUENTAS DE PÉRDIDAS Y GANANCIAS para ", nombreEmpresa, fechaI.substring(6), fechaI.substring(4, 6), fechaF.substring(6), fechaF.substring(4, 6) });
/*     */ 
/* 468 */     formater.format(new StringBuilder().append("%s;%s;%s").append(EOL).toString(), new Object[] { Mensajes.getString("ejercicio"), Inicio.p.getEjercicio(), Integer.toString(Integer.parseInt(Inicio.p.getEjercicio()) - 1) });
/* 469 */     formater.format(new StringBuilder().append("%s").append(EOL).toString(), new Object[] { new StringBuilder().append("(Debe)Haber").append(EOL).append(EOL).toString() });
/* 470 */     if ((!listarCeros) || ((listarCeros) && ((pg[0] != 0.0D) || (pgW[0] != 0.0D))))
/* 471 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "1. Importe neto de la cifra de negocios", Double.valueOf(pg[0]), Double.valueOf(pgW[0]) });
/* 472 */     if ((!listarCeros) || ((listarCeros) && ((pg[1] != 0.0D) || (pgW[1] != 0.0D)))) {
/* 473 */       formater.format(new StringBuilder().append("%s").append(EOL).toString(), new Object[] { "2. Variación de existencia de productos terminados" });
/* 474 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "   y en curso de fabricación", Double.valueOf(pg[1]), Double.valueOf(pgW[0]) });
/*     */     }
/* 476 */     if ((!listarCeros) || ((listarCeros) && ((pg[2] != 0.0D) || (pgW[2] != 0.0D))))
/* 477 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "3. Trabajos realizados por la empresa para su activo", Double.valueOf(pg[2]), Double.valueOf(pgW[2]) });
/* 478 */     if ((!listarCeros) || ((listarCeros) && ((pg[3] != 0.0D) || (pgW[3] != 0.0D))))
/* 479 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "4. Aprovisionamientos", Double.valueOf(pg[3]), Double.valueOf(pgW[3]) });
/* 480 */     if ((!listarCeros) || ((listarCeros) && ((pg[4] != 0.0D) || (pgW[4] != 0.0D))))
/* 481 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "5. Otros ingresos de explotación", Double.valueOf(pg[4]), Double.valueOf(pgW[4]) });
/* 482 */     if ((!listarCeros) || ((listarCeros) && ((pg[5] != 0.0D) || (pgW[5] != 0.0D))))
/* 483 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "6. Gastos de personal", Double.valueOf(pg[5]), Double.valueOf(pgW[5]) });
/* 484 */     if ((!listarCeros) || ((listarCeros) && ((pg[6] != 0.0D) || (pgW[6] != 0.0D))))
/* 485 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "7. Otros gastos de explotación", Double.valueOf(pg[6]), Double.valueOf(pgW[6]) });
/* 486 */     if ((!listarCeros) || ((listarCeros) && ((pg[7] != 0.0D) || (pgW[7] != 0.0D))))
/* 487 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "8. Amortización del inmovilizado", Double.valueOf(pg[7]), Double.valueOf(pgW[7]) });
/* 488 */     if ((!listarCeros) || ((listarCeros) && ((pg[8] != 0.0D) || (pgW[8] != 0.0D)))) {
/* 489 */       formater.format(new StringBuilder().append("%s").append(EOL).toString(), new Object[] { "9. Imputación de subvenciones de inmovilizado" });
/* 490 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "   no financiero y otras", Double.valueOf(pg[8]), Double.valueOf(pgW[8]) });
/*     */     }
/* 492 */     if ((!listarCeros) || ((listarCeros) && ((pg[9] != 0.0D) || (pgW[9] != 0.0D))))
/* 493 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "10. Excesos de provisiones", Double.valueOf(pg[9]), Double.valueOf(pgW[9]) });
/* 494 */     if ((!listarCeros) || ((listarCeros) && ((pg[10] != 0.0D) || (pgW[10] != 0.0D)))) {
/* 495 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "11. Deterioro y resultado por enajenaciones del inmovilizado", Double.valueOf(pg[10]), Double.valueOf(pgW[10]) });
/*     */     }
/* 497 */     formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).append(EOL).toString(), new Object[] { "A) RESULTADO DE EXPLOTACIÓN", Double.valueOf(A), Double.valueOf(AW) });
/* 498 */     if ((!listarCeros) || ((listarCeros) && ((pg[11] != 0.0D) || (pgW[11] != 0.0D))))
/* 499 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "12. Ingresos financieros", Double.valueOf(pg[11]), Double.valueOf(pgW[11]) });
/* 500 */     if ((!listarCeros) || ((listarCeros) && ((pg[12] != 0.0D) || (pgW[12] != 0.0D))))
/* 501 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "13. Gastos financieros", Double.valueOf(pg[12]), Double.valueOf(pgW[12]) });
/* 502 */     if ((!listarCeros) || ((listarCeros) && ((pg[13] != 0.0D) || (pgW[13] != 0.0D))))
/* 503 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "14. Variación de valor razonable en instrumentos financieros", Double.valueOf(pg[13]), Double.valueOf(pgW[13]) });
/* 504 */     if ((!listarCeros) || ((listarCeros) && ((pg[14] != 0.0D) || (pgW[14] != 0.0D))))
/* 505 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "15. Diferencias de cambio", Double.valueOf(pg[14]), Double.valueOf(pgW[14]) });
/* 506 */     if ((!listarCeros) || ((listarCeros) && ((pg[15] != 0.0D) || (pgW[15] != 0.0D)))) {
/* 507 */       formater.format(new StringBuilder().append("%s").append(EOL).toString(), new Object[] { "16. Deterioro y resultado por enajenaciones de instrumentos" });
/* 508 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).append(EOL).toString(), new Object[] { "    finacieros", Double.valueOf(pg[15]), Double.valueOf(pgW[15]) });
/*     */     }
/* 510 */     formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).append(EOL).toString(), new Object[] { "B) RESULTADO FINANCIERO", Double.valueOf(B), Double.valueOf(BW) });
/* 511 */     formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).append(EOL).toString(), new Object[] { "C) RESULTADO ANTES DE IMPUESTOS", Double.valueOf(C), Double.valueOf(CW) });
/* 512 */     if ((!listarCeros) || ((listarCeros) && ((pg[16] != 0.0D) || (pgW[16] != 0.0D))))
/* 513 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).append(EOL).toString(), new Object[] { "17. Impuesto sobre beneficios", Double.valueOf(pg[16]), Double.valueOf(pgW[16]) });
/* 514 */     formater.format("%s;%.2f;%.2f", new Object[] { "D) RESULTADO DEL EJERCICIO", Double.valueOf(D), Double.valueOf(DW) });
/* 515 */     this.listado.add(sb.toString());
/* 516 */     this.listar = false;
/* 517 */     setCursor(Cursor.getPredefinedCursor(0));
/* 518 */     JFileChooser fc = new JFileChooser();
/* 519 */     fc.setSelectedFile(new File("Resultado.csv"));
/* 520 */     int retorno = fc.showSaveDialog(this);
/* 521 */     if (retorno == 0) {
/* 522 */       File archivo = fc.getSelectedFile();
/* 523 */       archivo = AddExtension.csv(archivo);
/* 524 */       GrabarFichero salida = new GrabarFichero(archivo);
/* 525 */       for (String linea : this.listado) {
/* 526 */         salida.insertar(linea);
/*     */       }
/* 528 */       salida.cerrar();
/*     */     }
/*     */   }
/*     */ 
/*     */   private double calculaGrupo(String grupo, String fechaI, String fechaF)
/*     */   {
/* 541 */     if ((grupo.equals("")) || (grupo.length() < 1)) return 0.0D;
/* 542 */     double importe = 0.0D; double importeDebe = 0.0D; double importeHaber = 0.0D;
/*     */     try {
/* 544 */       String ejercicio = fechaI.substring(0, 4);
/* 545 */       String cadenaSeleccion = new StringBuilder().append("SELECT SUM(apt.importe) FROM apte").append(ejercicio).append(" AS apt ").append("JOIN scta").append(ejercicio).append(" as scta ON apt.cuenta=scta.codigo ").append("JOIN asto").append(ejercicio).append(" as ast ON apt.id_asiento=ast.id_asiento ").append("WHERE scta.gbalance = '").append(grupo).append("' AND ").append("ast.fecha BETWEEN '").append(fechaI).append("' AND '").append(fechaF).append("' ").append("AND ast.marca NOT LIKE 'G'").toString();
/*     */ 
/* 552 */       this.res = Inicio.getCEmpresa().getRes(new StringBuilder().append(cadenaSeleccion).append(" AND apt.DH = 'D'").toString());
/*     */ 
/* 554 */       if (this.res.next()) importeDebe = this.res.getDouble(1);
/* 555 */       this.res = Inicio.getCEmpresa().getRes(new StringBuilder().append(cadenaSeleccion).append(" AND apt.DH = 'H'").toString());
/*     */ 
/* 557 */       if (this.res.next()) importeHaber = this.res.getDouble(1); 
/*     */     }
/* 559 */     catch (SQLException exc) { System.out.println(exc.getMessage()); }
/* 560 */     importe = importeHaber - importeDebe;
/* 561 */     if (!grupo.substring(grupo.length() - 1).equals("-"))
/* 562 */       importe += calculaGrupo(new StringBuilder().append(grupo).append("-").toString(), fechaI, fechaF);
/* 563 */     return importe;
/*     */   }
/*     */ 
/*     */   private String pastYear(String fecha) {
/* 567 */     int year = Integer.parseInt(fecha.substring(0, 4));
/* 568 */     int mes = Integer.parseInt(fecha.substring(5, 7));
/* 569 */     int dia = Integer.parseInt(fecha.substring(8));
/* 570 */     GregorianCalendar cal = new GregorianCalendar(year, mes - 1, dia);
/* 571 */     cal.add(1, -1);
/* 572 */     Date datTemp = new Date(cal.getTimeInMillis());
/* 573 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/* 574 */     return sdf.format(datTemp);
/*     */   }
/*     */ 
/*     */   protected static ImageIcon createImageIcon(String path)
/*     */   {
/* 584 */     URL imgURL = Balance.class.getResource(path);
/* 585 */     if (imgURL != null)
/* 586 */       return new ImageIcon(imgURL);
/* 587 */     System.err.println(new StringBuilder().append("Couldn't find file: ").append(path).toString());
/* 588 */     return null;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.listados.CuentaPyG08
 * JD-Core Version:    0.6.2
 */