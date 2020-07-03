/*      */ package contaes.listados;
/*      */ 
/*      */ import contaes.Inicio;
/*      */ import contaes.Puente;
/*      */ import contaes.calendario.ICalendarTextField;
/*      */ import contaes.manejoDatos.ManejoEmpresas;
/*      */ import contaes.manejoDatos.auxiliar.AddExtension;
/*      */ import contaes.manejoDatos.auxiliar.FinLinea;
/*      */ import contaes.manejoDatos.auxiliar.GrabarFichero;
/*      */ import contaes.manejoDatos.auxiliar.MySQLConection;
/*      */ import internationalization.Mensajes;
/*      */ import java.awt.Container;
/*      */ import java.awt.Cursor;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.Frame;
/*      */ import java.awt.GridBagConstraints;
/*      */ import java.awt.GridBagLayout;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.ActionListener;
/*      */ import java.io.File;
/*      */ import java.io.PrintStream;
/*      */ import java.net.URL;
/*      */ import java.sql.ResultSet;
/*      */ import java.sql.SQLException;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Date;
/*      */ import java.util.Formatter;
/*      */ import java.util.GregorianCalendar;
/*      */ import java.util.List;
/*      */ import javax.swing.BorderFactory;
/*      */ import javax.swing.ImageIcon;
/*      */ import javax.swing.JButton;
/*      */ import javax.swing.JCheckBox;
/*      */ import javax.swing.JDialog;
/*      */ import javax.swing.JFileChooser;
/*      */ import javax.swing.JLabel;
/*      */ import javax.swing.JPanel;
/*      */ 
/*      */ public class Balance08 extends JDialog
/*      */   implements ActionListener
/*      */ {
/*      */   private static final String ACEPTAR = "aceptar";
/*      */   private static final String CANCELAR = "cancelar";
/*      */   private static final String CSV = "csv";
/*   67 */   String fecha = "";
/*   68 */   String fechaF = "";
/*   69 */   boolean listar = false;
/*   70 */   private List<String> listado = new ArrayList();
/*      */   private ResultSet res;
/*   72 */   JPanel panel1 = new JPanel();
/*   73 */   GridBagLayout gbLayout = new GridBagLayout();
/*   74 */   JLabel etiq1 = new JLabel();
/*   75 */   JLabel etiq2 = new JLabel();
/*   76 */   ICalendarTextField campoFecha = new ICalendarTextField();
/*   77 */   ICalendarTextField campoFechaF = new ICalendarTextField();
/*   78 */   JButton aceptar = new JButton();
/*   79 */   JButton cancelar = new JButton();
/*   80 */   JButton csv = new JButton();
/*   81 */   JCheckBox orden = new JCheckBox();
/*   82 */   ImageIcon iconoAceptar = createImageIcon("/contaes/iconos/ok.png");
/*   83 */   ImageIcon iconoCancelar = createImageIcon("/contaes/iconos/cancel.png");
/*   84 */   ImageIcon iconoCsv = createImageIcon("/almacen2/iconos/asCSV.png");
/*      */ 
/*      */   public Balance08(Frame owner, String title, boolean modal)
/*      */   {
/*   94 */     super(owner, title, modal);
/*      */     try {
/*   96 */       setDefaultCloseOperation(2);
/*   97 */       initialize();
/*   98 */       pack();
/*      */     } catch (Exception exception) {
/*  100 */       exception.printStackTrace();
/*      */     }
/*      */   }
/*      */ 
/*      */   public Balance08(Frame owner, boolean modal) {
/*  105 */     this(owner, Mensajes.getString("balance07"), modal);
/*      */   }
/*      */ 
/*      */   private void initialize()
/*      */     throws Exception
/*      */   {
/*  114 */     this.etiq1.setText(Mensajes.getString("fechaIni"));
/*  115 */     this.etiq2.setText(Mensajes.getString("fechaFin"));
/*  116 */     this.aceptar.setText(Mensajes.getString("aceptar"));
/*  117 */     this.aceptar.setIcon(this.iconoAceptar);
/*  118 */     this.aceptar.setVerticalTextPosition(0);
/*  119 */     this.aceptar.setHorizontalTextPosition(2);
/*  120 */     this.aceptar.setActionCommand("aceptar");
/*  121 */     this.aceptar.addActionListener(this);
/*  122 */     this.cancelar.setText(Mensajes.getString("cancelar"));
/*  123 */     this.cancelar.setIcon(this.iconoCancelar);
/*  124 */     this.cancelar.setVerticalTextPosition(0);
/*  125 */     this.cancelar.setHorizontalTextPosition(2);
/*  126 */     this.cancelar.setActionCommand("cancelar");
/*  127 */     this.cancelar.addActionListener(this);
/*  128 */     this.csv.setText(Mensajes.getString("saveAsCsv"));
/*  129 */     this.csv.setIcon(this.iconoCsv);
/*  130 */     this.csv.setVerticalTextPosition(0);
/*  131 */     this.csv.setHorizontalTextPosition(2);
/*  132 */     this.csv.setActionCommand("csv");
/*  133 */     this.csv.addActionListener(this);
/*  134 */     this.orden.setText(Mensajes.getString("nosaldoscero"));
/*  135 */     this.orden.setSelected(false);
/*      */ 
/*  138 */     this.campoFecha.setMinimumSize(new Dimension(100, 26));
/*  139 */     this.campoFecha.setToolTipText(Mensajes.getString("formatoFecha"));
/*  140 */     this.campoFecha.setComponente(this.campoFechaF);
/*  141 */     this.campoFechaF.setMinimumSize(new Dimension(100, 26));
/*  142 */     this.campoFechaF.setToolTipText(Mensajes.getString("formatoFecha"));
/*  143 */     this.campoFechaF.setComponente(this.orden);
/*      */ 
/*  145 */     this.panel1.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(1), BorderFactory.createEmptyBorder(5, 5, 5, 5))));
/*      */ 
/*  148 */     this.panel1.setLayout(this.gbLayout);
/*      */ 
/*  150 */     GridBagConstraints cons = new GridBagConstraints();
/*  151 */     cons.insets.bottom = 7;
/*  152 */     cons.insets.top = 7;
/*  153 */     cons.insets.left = 7;
/*  154 */     cons.insets.right = 7;
/*      */ 
/*  156 */     cons.gridy = 0;
/*  157 */     cons.gridx = 0;
/*  158 */     this.gbLayout.setConstraints(this.etiq1, cons);
/*  159 */     this.panel1.add(this.etiq1);
/*      */ 
/*  161 */     cons.gridx = 1;
/*  162 */     this.gbLayout.setConstraints(this.etiq2, cons);
/*  163 */     this.panel1.add(this.etiq2);
/*      */ 
/*  166 */     cons.fill = 2;
/*  167 */     cons.gridy = 1;
/*  168 */     cons.gridx = 0;
/*  169 */     this.gbLayout.setConstraints(this.campoFecha, cons);
/*  170 */     this.panel1.add(this.campoFecha);
/*      */ 
/*  172 */     cons.gridx = 1;
/*  173 */     this.gbLayout.setConstraints(this.campoFechaF, cons);
/*  174 */     this.panel1.add(this.campoFechaF);
/*      */ 
/*  177 */     cons.fill = 0;
/*  178 */     cons.anchor = 10;
/*  179 */     cons.gridy = 3;
/*  180 */     cons.gridx = 0;
/*  181 */     this.gbLayout.setConstraints(this.aceptar, cons);
/*  182 */     this.panel1.add(this.aceptar);
/*      */ 
/*  184 */     cons.gridx = 1;
/*  185 */     this.gbLayout.setConstraints(this.cancelar, cons);
/*  186 */     this.panel1.add(this.cancelar);
/*      */ 
/*  188 */     cons.gridy = 2;
/*  189 */     cons.gridx = 0;
/*  190 */     cons.gridwidth = 2;
/*  191 */     this.gbLayout.setConstraints(this.orden, cons);
/*  192 */     this.panel1.add(this.orden);
/*      */ 
/*  194 */     cons.fill = 0;
/*  195 */     cons.anchor = 10;
/*  196 */     cons.gridy = 4;
/*  197 */     cons.gridx = 0;
/*  198 */     this.gbLayout.setConstraints(this.csv, cons);
/*  199 */     this.panel1.add(this.csv);
/*      */ 
/*  201 */     getContentPane().add(this.panel1);
/*      */   }
/*      */ 
/*      */   public void actionPerformed(ActionEvent e)
/*      */   {
/*  210 */     String cmd = e.getActionCommand();
/*      */ 
/*  212 */     if ("aceptar".equals(cmd)) {
/*  213 */       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/*  214 */       Date fecha_d = this.campoFecha.getDate();
/*  215 */       this.fecha = sdf.format(fecha_d);
/*  216 */       fecha_d = this.campoFechaF.getDate();
/*  217 */       this.fechaF = sdf.format(fecha_d);
/*      */ 
/*  219 */       if ((!this.fecha.equals("")) && (!this.fechaF.equals(""))) {
/*  220 */         hacerListado(this.fecha, this.fechaF);
/*  221 */         dispose();
/*      */       }
/*  223 */     } else if ("csv".equals(cmd)) {
/*  224 */       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/*  225 */       Date fecha_d = this.campoFecha.getDate();
/*  226 */       this.fecha = sdf.format(fecha_d);
/*  227 */       fecha_d = this.campoFechaF.getDate();
/*  228 */       this.fechaF = sdf.format(fecha_d);
/*      */ 
/*  230 */       if ((!this.fecha.equals("")) && (!this.fechaF.equals(""))) {
/*  231 */         hacerListadoCsv(this.fecha, this.fechaF);
/*  232 */         this.listar = false;
/*  233 */         dispose();
/*      */       }
/*  235 */     } else if ("cancelar".equals(cmd)) {
/*  236 */       this.listar = false;
/*  237 */       dispose();
/*      */     }
/*      */   }
/*      */ 
/*      */   public List<String> textoListado()
/*      */   {
/*  247 */     return this.listado;
/*      */   }
/*      */ 
/*      */   public boolean Listar()
/*      */   {
/*  256 */     return this.listar;
/*      */   }
/*      */ 
/*      */   protected void hacerListado(String fechaI, String fechaF)
/*      */   {
/*  266 */     boolean listarCeros = this.orden.isSelected();
/*  267 */     String EOL = FinLinea.get();
/*  268 */     setCursor(Cursor.getPredefinedCursor(3));
/*  269 */     String nombreEmpresa = new ManejoEmpresas(Inicio.getCGeneral(), Inicio.p.getEmpresa()).getNombre(Inicio.p.getEmpresa());
/*      */ 
/*  272 */     double[] ba = new double[14];
/*  273 */     double[] bp = new double[25];
/*  274 */     for (int i = 0; i < 14; i++) {
/*  275 */       ba[i] = 0.0D;
/*      */     }
/*  277 */     for (int i = 0; i < 25; i++) {
/*  278 */       bp[i] = 0.0D;
/*      */     }
/*  280 */     ba[0] = calculaGrupo("AAI", fechaI, fechaF);
/*  281 */     ba[1] = calculaGrupo("AAII", fechaI, fechaF);
/*  282 */     ba[2] = calculaGrupo("AAIII", fechaI, fechaF);
/*  283 */     ba[3] = calculaGrupo("AAIV", fechaI, fechaF);
/*  284 */     ba[4] = calculaGrupo("AAV", fechaI, fechaF);
/*  285 */     ba[5] = calculaGrupo("AAVI", fechaI, fechaF);
/*  286 */     ba[6] = calculaGrupo("ABI", fechaI, fechaF);
/*  287 */     ba[7] = calculaGrupo("ABII1", fechaI, fechaF);
/*  288 */     ba[8] = calculaGrupo("ABII2", fechaI, fechaF);
/*  289 */     ba[9] = calculaGrupo("ABII3", fechaI, fechaF);
/*  290 */     ba[10] = calculaGrupo("ABIII", fechaI, fechaF);
/*  291 */     ba[11] = calculaGrupo("ABIV", fechaI, fechaF);
/*  292 */     ba[12] = calculaGrupo("ABV", fechaI, fechaF);
/*  293 */     ba[13] = calculaGrupo("ABVI", fechaI, fechaF);
/*      */ 
/*  295 */     bp[0] = calculaGrupo("PA1I1", fechaI, fechaF);
/*  296 */     bp[1] = calculaGrupo("PA1I2", fechaI, fechaF);
/*  297 */     bp[2] = calculaGrupo("PA1II", fechaI, fechaF);
/*  298 */     bp[3] = calculaGrupo("PA1III", fechaI, fechaF);
/*  299 */     bp[4] = calculaGrupo("PA1IV", fechaI, fechaF);
/*  300 */     bp[5] = calculaGrupo("PA1V", fechaI, fechaF);
/*  301 */     bp[6] = calculaGrupo("PA1VI", fechaI, fechaF);
/*  302 */     bp[7] = calculaGrupo("PA1VII", fechaI, fechaF);
/*  303 */     bp[8] = calculaGrupo("PA1VIII", fechaI, fechaF);
/*  304 */     bp[9] = calculaGrupo("PA2", fechaI, fechaF);
/*  305 */     bp[10] = calculaGrupo("PBI", fechaI, fechaF);
/*  306 */     bp[11] = calculaGrupo("PBII1", fechaI, fechaF);
/*  307 */     bp[12] = calculaGrupo("PBII2", fechaI, fechaF);
/*  308 */     bp[13] = calculaGrupo("PBIII", fechaI, fechaF);
/*  309 */     bp[14] = calculaGrupo("PBIV", fechaI, fechaF);
/*  310 */     bp[15] = calculaGrupo("PCI", fechaI, fechaF);
/*  311 */     bp[16] = calculaGrupo("PCII1", fechaI, fechaF);
/*  312 */     bp[17] = calculaGrupo("PCII2", fechaI, fechaF);
/*  313 */     bp[18] = calculaGrupo("PCIII", fechaI, fechaF);
/*  314 */     bp[19] = calculaGrupo("PCIV1", fechaI, fechaF);
/*  315 */     bp[20] = calculaGrupo("PCIV2", fechaI, fechaF);
/*  316 */     bp[21] = calculaGrupo("PCV", fechaI, fechaF);
/*  317 */     bp[22] = calculaGrupo("PBII3", fechaI, fechaF);
/*  318 */     bp[23] = calculaGrupo("PBV", fechaI, fechaF);
/*  319 */     bp[24] = calculaGrupo("PCII3", fechaI, fechaF);
/*      */ 
/*  321 */     double grupoEsp1 = calculaGrupo("ESP1", fechaI, fechaF);
/*  322 */     double grupoEsp2 = calculaGrupo("ESP2", fechaI, fechaF);
/*  323 */     if (grupoEsp1 >= 0.0D)
/*  324 */       ba[10] += grupoEsp1;
/*      */     else {
/*  326 */       bp[24] -= grupoEsp1;
/*      */     }
/*  328 */     if (grupoEsp2 >= 0.0D)
/*  329 */       ba[11] += grupoEsp2;
/*      */     else {
/*  331 */       bp[18] -= grupoEsp2;
/*      */     }
/*      */ 
/*  334 */     double[] t = new double[13];
/*      */ 
/*  336 */     for (int i = 0; i < 13; i++) {
/*  337 */       t[i] = 0.0D;
/*      */     }
/*  339 */     for (int i = 0; i < 6; i++) {
/*  340 */       t[0] += ba[i];
/*      */     }
/*  342 */     for (int i = 7; i < 10; i++) {
/*  343 */       t[2] += ba[i];
/*      */     }
/*  345 */     for (int i = 10; i < 14; i++) {
/*  346 */       t[1] += ba[i];
/*      */     }
/*  348 */     t[1] = (t[1] + t[2] + ba[6]);
/*  349 */     t[3] = (t[0] + t[1]);
/*      */ 
/*  351 */     t[6] = (bp[0] + bp[1]);
/*  352 */     for (int i = 2; i < 9; i++) {
/*  353 */       t[5] += bp[i];
/*      */     }
/*  355 */     t[5] += t[6];
/*  356 */     t[4] = (t[5] + bp[9]);
/*  357 */     t[8] = (bp[11] + bp[12] + bp[22]);
/*  358 */     t[7] = (bp[10] + t[8] + bp[13] + bp[14] + bp[23]);
/*  359 */     t[10] = (bp[16] + bp[17] + bp[24]);
/*  360 */     t[11] = (bp[19] + bp[20]);
/*  361 */     t[9] = (bp[15] + t[10] + bp[18] + t[11] + bp[21]);
/*  362 */     t[12] = (t[4] + t[7] + t[9]);
/*      */ 
/*  365 */     String fechaIW = pastYear(fechaI);
/*  366 */     String fechaFW = pastYear(fechaF);
/*  367 */     double[] baW = new double[14];
/*  368 */     double[] bpW = new double[25];
/*  369 */     for (int i = 0; i < 14; i++) {
/*  370 */       baW[i] = 0.0D;
/*      */     }
/*  372 */     for (int i = 0; i < 25; i++) {
/*  373 */       bpW[i] = 0.0D;
/*      */     }
/*  375 */     baW[0] = calculaGrupo("AAI", fechaIW, fechaFW);
/*  376 */     baW[1] = calculaGrupo("AAII", fechaIW, fechaFW);
/*  377 */     baW[2] = calculaGrupo("AAIII", fechaIW, fechaFW);
/*  378 */     baW[3] = calculaGrupo("AAIV", fechaIW, fechaFW);
/*  379 */     baW[4] = calculaGrupo("AAV", fechaIW, fechaFW);
/*  380 */     baW[5] = calculaGrupo("AAVI", fechaIW, fechaFW);
/*  381 */     baW[6] = calculaGrupo("ABI", fechaIW, fechaFW);
/*  382 */     baW[7] = calculaGrupo("ABII1", fechaIW, fechaFW);
/*  383 */     baW[8] = calculaGrupo("ABII2", fechaIW, fechaFW);
/*  384 */     baW[9] = calculaGrupo("ABII3", fechaIW, fechaFW);
/*  385 */     baW[10] = calculaGrupo("ABIII", fechaIW, fechaFW);
/*  386 */     baW[11] = calculaGrupo("ABIV", fechaIW, fechaFW);
/*  387 */     baW[12] = calculaGrupo("ABV", fechaIW, fechaFW);
/*  388 */     baW[13] = calculaGrupo("ABVI", fechaIW, fechaFW);
/*      */ 
/*  390 */     bpW[0] = calculaGrupo("PA1I1", fechaIW, fechaFW);
/*  391 */     bpW[1] = calculaGrupo("PA1I2", fechaIW, fechaFW);
/*  392 */     bpW[2] = calculaGrupo("PA1II", fechaIW, fechaFW);
/*  393 */     bpW[3] = calculaGrupo("PA1III", fechaIW, fechaFW);
/*  394 */     bpW[4] = calculaGrupo("PA1IV", fechaIW, fechaFW);
/*  395 */     bpW[5] = calculaGrupo("PA1V", fechaIW, fechaFW);
/*  396 */     bpW[6] = calculaGrupo("PA1VI", fechaIW, fechaFW);
/*  397 */     bpW[7] = calculaGrupo("PA1VII", fechaIW, fechaFW);
/*  398 */     bpW[8] = calculaGrupo("PA1VIII", fechaIW, fechaFW);
/*  399 */     bpW[9] = calculaGrupo("PA2", fechaIW, fechaFW);
/*  400 */     bpW[10] = calculaGrupo("PBI", fechaIW, fechaFW);
/*  401 */     bpW[11] = calculaGrupo("PBII1", fechaIW, fechaFW);
/*  402 */     bpW[12] = calculaGrupo("PBII2", fechaIW, fechaFW);
/*  403 */     bpW[13] = calculaGrupo("PBIII", fechaIW, fechaFW);
/*  404 */     bpW[14] = calculaGrupo("PBIV", fechaIW, fechaFW);
/*  405 */     bpW[15] = calculaGrupo("PCI", fechaIW, fechaFW);
/*  406 */     bpW[16] = calculaGrupo("PCII1", fechaIW, fechaFW);
/*  407 */     bpW[17] = calculaGrupo("PCII2", fechaIW, fechaFW);
/*  408 */     bpW[18] = calculaGrupo("PCIII", fechaIW, fechaFW);
/*  409 */     bpW[19] = calculaGrupo("PCIV1", fechaIW, fechaFW);
/*  410 */     bpW[20] = calculaGrupo("PCIV2", fechaIW, fechaFW);
/*  411 */     bpW[21] = calculaGrupo("PCV", fechaIW, fechaFW);
/*  412 */     bpW[22] = calculaGrupo("PBII3", fechaIW, fechaFW);
/*  413 */     bpW[23] = calculaGrupo("PBV", fechaIW, fechaFW);
/*  414 */     bpW[24] = calculaGrupo("PCII3", fechaIW, fechaFW);
/*      */ 
/*  416 */     double grupoEsp1W = calculaGrupo("ESP1", fechaIW, fechaFW);
/*  417 */     double grupoEsp2W = calculaGrupo("ESP2", fechaIW, fechaFW);
/*  418 */     if (grupoEsp1W >= 0.0D)
/*  419 */       baW[10] += grupoEsp1W;
/*      */     else {
/*  421 */       bpW[24] -= grupoEsp1W;
/*      */     }
/*  423 */     if (grupoEsp2W >= 0.0D)
/*  424 */       baW[11] += grupoEsp2W;
/*      */     else {
/*  426 */       bpW[18] -= grupoEsp2W;
/*      */     }
/*      */ 
/*  429 */     double[] tW = new double[13];
/*      */ 
/*  431 */     for (int i = 0; i < 13; i++) {
/*  432 */       tW[i] = 0.0D;
/*      */     }
/*  434 */     for (int i = 0; i < 6; i++) {
/*  435 */       tW[0] += baW[i];
/*      */     }
/*  437 */     for (int i = 7; i < 10; i++) {
/*  438 */       tW[2] += baW[i];
/*      */     }
/*  440 */     for (int i = 10; i < 14; i++) {
/*  441 */       tW[1] += baW[i];
/*      */     }
/*  443 */     tW[1] = (tW[1] + tW[2] + baW[6]);
/*  444 */     tW[3] = (tW[0] + tW[1]);
/*      */ 
/*  446 */     tW[6] = (bpW[0] + bpW[1]);
/*  447 */     for (int i = 2; i < 9; i++) {
/*  448 */       tW[5] += bpW[i];
/*      */     }
/*  450 */     tW[5] += tW[6];
/*  451 */     tW[4] = (tW[5] + bpW[9]);
/*  452 */     tW[8] = (bpW[11] + bpW[12] + bpW[22]);
/*  453 */     tW[7] = (bpW[10] + tW[8] + bpW[13] + bpW[14] + bpW[23]);
/*  454 */     tW[10] = (bpW[16] + bpW[17] + bpW[24]);
/*  455 */     tW[11] = (bpW[19] + bpW[20]);
/*  456 */     tW[9] = (bpW[15] + tW[10] + bpW[18] + tW[11] + bpW[21]);
/*  457 */     tW[12] = (tW[4] + tW[7] + tW[9]);
/*      */ 
/*  460 */     StringBuilder sb = new StringBuilder();
/*  461 */     Formatter formater = new Formatter(sb);
/*  462 */     formater.format(new StringBuilder().append("%-10s %s %s").append(EOL).toString(), new Object[] { Mensajes.getString("balance07"), Mensajes.getString("para"), nombreEmpresa });
/*      */ 
/*  464 */     formater.format(new StringBuilder().append("%s %s-%s y %s-%-53s %s").append(EOL).append(EOL).toString(), new Object[] { Mensajes.getString("entre"), fechaI.substring(6), fechaI.substring(4, 6), fechaF.substring(6), fechaF.substring(4, 6), Mensajes.getString("ejercicio") });
/*      */ 
/*  468 */     formater.format(new StringBuilder().append("%73s %14s").append(EOL).toString(), new Object[] { Inicio.p.getEjercicio(), Integer.toString(Integer.parseInt(Inicio.p.getEjercicio()) - 1) });
/*  469 */     formater.format(new StringBuilder().append("ACTIVO").append(EOL).append(EOL).append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "A) ACTIVO NO CORRIENTE", Double.valueOf(t[0]), Double.valueOf(tW[0]) });
/*  470 */     if ((!listarCeros) || ((listarCeros) && ((ba[0] != 0.0D) || (baW[0] != 0.0D)))) {
/*  471 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "    I. Inmovilizado intangible", Double.valueOf(ba[0]), Double.valueOf(baW[0]) });
/*      */     }
/*  473 */     if ((!listarCeros) || ((listarCeros) && ((ba[1] != 0.0D) || (baW[1] != 0.0D)))) {
/*  474 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "    II. Inmovilizado material", Double.valueOf(ba[1]), Double.valueOf(baW[1]) });
/*      */     }
/*  476 */     if ((!listarCeros) || ((listarCeros) && ((ba[2] != 0.0D) || (baW[2] != 0.0D)))) {
/*  477 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "    III. Inversiones inmobiliarias", Double.valueOf(ba[2]), Double.valueOf(baW[2]) });
/*      */     }
/*  479 */     if ((!listarCeros) || ((listarCeros) && ((ba[3] != 0.0D) || (baW[3] != 0.0D)))) {
/*  480 */       formater.format(new StringBuilder().append("%s").append(EOL).toString(), new Object[] { "    IV. Inversiones en empresas del grupo y asociadas" });
/*  481 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "        a largo plazo", Double.valueOf(ba[3]), Double.valueOf(baW[3]) });
/*      */     }
/*  483 */     if ((!listarCeros) || ((listarCeros) && ((ba[4] != 0.0D) || (baW[4] != 0.0D)))) {
/*  484 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "    V. Inversiones financieras a largo plazo", Double.valueOf(ba[4]), Double.valueOf(baW[4]) });
/*      */     }
/*  486 */     if ((!listarCeros) || ((listarCeros) && ((ba[5] != 0.0D) || (baW[5] != 0.0D)))) {
/*  487 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "    VI. Activos por Impuesto diferido", Double.valueOf(ba[5]), Double.valueOf(baW[5]) });
/*      */     }
/*  489 */     formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "B) ACTIVO CORRIENTE", Double.valueOf(t[1]), Double.valueOf(tW[1]) });
/*  490 */     if ((!listarCeros) || ((listarCeros) && ((ba[6] != 0.0D) || (baW[6] != 0.0D)))) {
/*  491 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "    I. Existencias", Double.valueOf(ba[6]), Double.valueOf(baW[6]) });
/*      */     }
/*  493 */     if ((!listarCeros) || ((listarCeros) && ((t[2] != 0.0D) || (tW[2] != 0.0D)))) {
/*  494 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "    II. Deudores comerciales y otras cuentas a cobrar", Double.valueOf(t[2]), Double.valueOf(tW[2]) });
/*      */     }
/*  496 */     if ((!listarCeros) || ((listarCeros) && ((ba[7] != 0.0D) || (baW[7] != 0.0D)))) {
/*  497 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "       1. Clientes por ventas y Prestación de servicios", Double.valueOf(ba[7]), Double.valueOf(baW[7]) });
/*      */     }
/*  499 */     if ((!listarCeros) || ((listarCeros) && (ba[8] != 0.0D)) || (baW[8] != 0.0D)) {
/*  500 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "       2. Accionistas (socios) por desembolsos exigidos", Double.valueOf(ba[8]), Double.valueOf(baW[8]) });
/*      */     }
/*  502 */     if ((!listarCeros) || ((listarCeros) && ((ba[9] != 0.0D) || (baW[9] != 0.0D)))) {
/*  503 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "       3. Otros deudores", Double.valueOf(ba[9]), Double.valueOf(baW[9]) });
/*      */     }
/*  505 */     if ((!listarCeros) || ((listarCeros) && ((ba[10] != 0.0D) || (baW[10] != 0.0D)))) {
/*  506 */       formater.format(new StringBuilder().append("%s").append(EOL).toString(), new Object[] { "    III. Inversiones en empresas del grupo y asociadas" });
/*  507 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "         a corto plazo", Double.valueOf(ba[10]), Double.valueOf(baW[10]) });
/*      */     }
/*  509 */     if ((!listarCeros) || ((listarCeros) && ((ba[11] != 0.0D) || (baW[11] != 0.0D)))) {
/*  510 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "    IV. Inversiones financieras a corto plazo", Double.valueOf(ba[11]), Double.valueOf(baW[11]) });
/*      */     }
/*  512 */     if ((!listarCeros) || ((listarCeros) && ((ba[12] != 0.0D) || (baW[12] != 0.0D)))) {
/*  513 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "    V. Periodificaciones a corto plazo", Double.valueOf(ba[12]), Double.valueOf(baW[12]) });
/*      */     }
/*  515 */     if ((!listarCeros) || ((listarCeros) && ((ba[13] != 0.0D) || (baW[12] != 0.0D)))) {
/*  516 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "    VI. Efectivo y otros activos líquidos equivalentes", Double.valueOf(ba[13]), Double.valueOf(baW[13]) });
/*      */     }
/*  518 */     formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "TOTAL ACTIVO (A+B)", Double.valueOf(t[3]), Double.valueOf(tW[3]) });
/*  519 */     formater.format(new StringBuilder().append(EOL).append("PATRIMONIO NETO Y PASIVO").append(EOL).append(EOL).append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "A) PATRIMONIO NETO", Double.valueOf(t[4]), Double.valueOf(tW[4]) });
/*  520 */     if ((!listarCeros) || ((listarCeros) && (t[5] != 0.0D)) || (tW[5] != 0.0D)) {
/*  521 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "  A-1) Fondos propios", Double.valueOf(t[5]), Double.valueOf(tW[5]) });
/*      */     }
/*  523 */     if ((!listarCeros) || ((listarCeros) && (t[6] != 0.0D)) || (tW[6] != 0.0D)) {
/*  524 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "    I. Capital", Double.valueOf(t[6]), Double.valueOf(tW[6]) });
/*      */     }
/*  526 */     if ((!listarCeros) || ((listarCeros) && ((bp[0] != 0.0D) || (bpW[0] != 0.0D)))) {
/*  527 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "       1. Capital escriturado", Double.valueOf(bp[0]), Double.valueOf(bpW[0]) });
/*      */     }
/*  529 */     if ((!listarCeros) || ((listarCeros) && ((bp[1] != 0.0D) || (bpW[1] != 0.0D)))) {
/*  530 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "       2. (Capital no exigido)", Double.valueOf(bp[1]), Double.valueOf(bpW[1]) });
/*      */     }
/*  532 */     if ((!listarCeros) || ((listarCeros) && ((bp[2] != 0.0D) || (bpW[2] != 0.0D)))) {
/*  533 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "    II. Primas de emisión", Double.valueOf(bp[2]), Double.valueOf(bpW[2]) });
/*      */     }
/*  535 */     if ((!listarCeros) || ((listarCeros) && ((bp[3] != 0.0D) || (bpW[3] != 0.0D)))) {
/*  536 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "    III. Reservas", Double.valueOf(bp[3]), Double.valueOf(bpW[3]) });
/*      */     }
/*  538 */     if ((!listarCeros) || ((listarCeros) && ((bp[4] != 0.0D) || (bpW[4] != 0.0D)))) {
/*  539 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "    IV. (Acciones y participaciones en patrimonio propio)", Double.valueOf(bp[4]), Double.valueOf(bpW[4]) });
/*      */     }
/*  541 */     if ((!listarCeros) || ((listarCeros) && ((bp[5] != 0.0D) || (bpW[5] != 0.0D)))) {
/*  542 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "    V. Resultados de ejercicios anteriores", Double.valueOf(bp[5]), Double.valueOf(bpW[5]) });
/*      */     }
/*  544 */     if ((!listarCeros) || ((listarCeros) && ((bp[6] != 0.0D) || (bpW[6] != 0.0D)))) {
/*  545 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "    VI. Otras aportaciones de socios", Double.valueOf(bp[6]), Double.valueOf(bpW[6]) });
/*      */     }
/*  547 */     if ((!listarCeros) || ((listarCeros) && ((bp[7] != 0.0D) || (bpW[7] != 0.0D)))) {
/*  548 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "    VII. Resultado del ejercicio", Double.valueOf(bp[7]), Double.valueOf(bpW[7]) });
/*      */     }
/*  550 */     if ((!listarCeros) || ((listarCeros) && ((bp[8] != 0.0D) || (bpW[8] != 0.0D)))) {
/*  551 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "    VIII. (Dividendo a cuenta)", Double.valueOf(bp[8]), Double.valueOf(bpW[8]) });
/*      */     }
/*  553 */     if ((!listarCeros) || ((listarCeros) && ((bp[9] != 0.0D) || (bpW[9] != 0.0D)))) {
/*  554 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "  A-2) Subvenciones, donaciones y legados recibidos", Double.valueOf(bp[9]), Double.valueOf(bpW[9]) });
/*      */     }
/*  556 */     formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "B) PASIVO NO CORRIENTE", Double.valueOf(t[7]), Double.valueOf(tW[7]) });
/*  557 */     if ((!listarCeros) || ((listarCeros) && ((bp[10] != 0.0D) || (bpW[10] != 0.0D)))) {
/*  558 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "    I. Provisiones a largo plazo", Double.valueOf(bp[10]), Double.valueOf(bpW[10]) });
/*      */     }
/*  560 */     if ((!listarCeros) || ((listarCeros) && (t[8] != 0.0D))) {
/*  561 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "    II. Deudas a largo plazo", Double.valueOf(t[8]), Double.valueOf(tW[8]) });
/*      */     }
/*  563 */     if ((!listarCeros) || ((listarCeros) && ((bp[11] != 0.0D) || (bpW[11] != 0.0D)))) {
/*  564 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "       1. Deudas con entidades de crédito", Double.valueOf(bp[11]), Double.valueOf(bpW[11]) });
/*      */     }
/*  566 */     if ((!listarCeros) || ((listarCeros) && ((bp[12] != 0.0D) || (bpW[12] != 0.0D)))) {
/*  567 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "       2. Acreedores por arrendamiento financiero", Double.valueOf(bp[12]), Double.valueOf(bpW[12]) });
/*      */     }
/*  569 */     if ((!listarCeros) || ((listarCeros) && ((bp[22] != 0.0D) || (bpW[22] != 0.0D)))) {
/*  570 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "       3. Otras deudas a largo plazo", Double.valueOf(bp[22]), Double.valueOf(bpW[22]) });
/*      */     }
/*  572 */     if ((!listarCeros) || ((listarCeros) && ((bp[13] != 0.0D) || (bpW[13] != 0.0D)))) {
/*  573 */       formater.format(new StringBuilder().append("%s").append(EOL).toString(), new Object[] { "    III. Deudas con empresas del grupo y asociadas" });
/*  574 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "         a largo plazo", Double.valueOf(bp[13]), Double.valueOf(bpW[13]) });
/*      */     }
/*  576 */     if ((!listarCeros) || ((listarCeros) && ((bp[14] != 0.0D) || (bpW[14] != 0.0D)))) {
/*  577 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "    IV. Pasivos por impuesto diferido", Double.valueOf(bp[14]), Double.valueOf(bpW[14]) });
/*      */     }
/*  579 */     if ((!listarCeros) || ((listarCeros) && ((bp[23] != 0.0D) || (bpW[23] != 0.0D)))) {
/*  580 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "    V. Periodificaciones a largo plazo", Double.valueOf(bp[23]), Double.valueOf(bpW[23]) });
/*      */     }
/*  582 */     formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "C) PASIVO CORRIENTE", Double.valueOf(t[9]), Double.valueOf(tW[9]) });
/*  583 */     if ((!listarCeros) || ((listarCeros) && ((bp[15] != 0.0D) || (bpW[15] != 0.0D)))) {
/*  584 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "    I. Provisiones a corto plazo", Double.valueOf(bp[15]), Double.valueOf(bpW[15]) });
/*      */     }
/*  586 */     if ((!listarCeros) || ((listarCeros) && ((t[10] != 0.0D) || (tW[10] != 0.0D)))) {
/*  587 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "    II. Deudas a corto plazo", Double.valueOf(t[10]), Double.valueOf(tW[10]) });
/*      */     }
/*  589 */     if ((!listarCeros) || ((listarCeros) && ((bp[16] != 0.0D) || (bpW[16] != 0.0D)))) {
/*  590 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "       1. Deudas con entidades de crédito", Double.valueOf(bp[16]), Double.valueOf(bpW[16]) });
/*      */     }
/*  592 */     if ((!listarCeros) || ((listarCeros) && ((bp[17] != 0.0D) || (bpW[17] != 0.0D)))) {
/*  593 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "       2. Acreedores por arrendamiento financiero", Double.valueOf(bp[17]), Double.valueOf(bpW[17]) });
/*      */     }
/*  595 */     if ((!listarCeros) || ((listarCeros) && ((bp[24] != 0.0D) || (bpW[24] != 0.0D)))) {
/*  596 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "       3. Otras deudas a corto plazo", Double.valueOf(bp[24]), Double.valueOf(bpW[24]) });
/*      */     }
/*  598 */     if ((!listarCeros) || ((listarCeros) && ((bp[18] != 0.0D) || (bpW[18] != 0.0D)))) {
/*  599 */       formater.format(new StringBuilder().append("%s").append(EOL).toString(), new Object[] { "    III. Deudas con empresas del grupo y asociadas" });
/*  600 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "         a corto plazo", Double.valueOf(bp[18]), Double.valueOf(bpW[18]) });
/*      */     }
/*  602 */     if ((!listarCeros) || ((listarCeros) && ((t[11] != 0.0D) || (tW[11] != 0.0D)))) {
/*  603 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "    IV. Acreedores comerciales y otras cuentas a pagar", Double.valueOf(t[11]), Double.valueOf(tW[11]) });
/*      */     }
/*  605 */     if ((!listarCeros) || ((listarCeros) && ((bp[19] != 0.0D) || (bpW[19] != 0.0D)))) {
/*  606 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "       1. Proveedores", Double.valueOf(bp[19]), Double.valueOf(bpW[19]) });
/*      */     }
/*  608 */     if ((!listarCeros) || ((listarCeros) && ((bp[20] != 0.0D) || (bpW[20] != 0.0D)))) {
/*  609 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "       2. Otros acreedores", Double.valueOf(bp[20]), Double.valueOf(bpW[20]) });
/*      */     }
/*  611 */     if ((!listarCeros) || ((listarCeros) && ((bp[21] != 0.0D) || (bpW[21] != 0.0D)))) {
/*  612 */       formater.format(new StringBuilder().append("%-60s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "    V. Periodificaciones", Double.valueOf(bp[21]), Double.valueOf(bpW[21]) });
/*      */     }
/*  614 */     formater.format("%-60s %,13.2f %,13.2f", new Object[] { "TOTAL PATRIMONIO NETO Y PASIVO (A+B+C)", Double.valueOf(t[12]), Double.valueOf(tW[12]) });
/*  615 */     this.listado.add(sb.toString());
/*  616 */     this.listar = true;
/*      */ 
/*  618 */     setCursor(Cursor.getPredefinedCursor(0));
/*      */   }
/*      */ 
/*      */   protected void hacerListadoCsv(String fechaI, String fechaF) {
/*  622 */     boolean listarCeros = this.orden.isSelected();
/*  623 */     String EOL = FinLinea.get();
/*  624 */     setCursor(Cursor.getPredefinedCursor(3));
/*  625 */     String nombreEmpresa = new ManejoEmpresas(Inicio.getCGeneral(), Inicio.p.getEmpresa()).getNombre(Inicio.p.getEmpresa());
/*      */ 
/*  628 */     double[] ba = new double[14];
/*  629 */     double[] bp = new double[25];
/*  630 */     for (int i = 0; i < 14; i++) {
/*  631 */       ba[i] = 0.0D;
/*      */     }
/*  633 */     for (int i = 0; i < 25; i++) {
/*  634 */       bp[i] = 0.0D;
/*      */     }
/*  636 */     ba[0] = calculaGrupo("AAI", fechaI, fechaF);
/*  637 */     ba[1] = calculaGrupo("AAII", fechaI, fechaF);
/*  638 */     ba[2] = calculaGrupo("AAIII", fechaI, fechaF);
/*  639 */     ba[3] = calculaGrupo("AAIV", fechaI, fechaF);
/*  640 */     ba[4] = calculaGrupo("AAV", fechaI, fechaF);
/*  641 */     ba[5] = calculaGrupo("AAVI", fechaI, fechaF);
/*  642 */     ba[6] = calculaGrupo("ABI", fechaI, fechaF);
/*  643 */     ba[7] = calculaGrupo("ABII1", fechaI, fechaF);
/*  644 */     ba[8] = calculaGrupo("ABII2", fechaI, fechaF);
/*  645 */     ba[9] = calculaGrupo("ABII3", fechaI, fechaF);
/*  646 */     ba[10] = calculaGrupo("ABIII", fechaI, fechaF);
/*  647 */     ba[11] = calculaGrupo("ABIV", fechaI, fechaF);
/*  648 */     ba[12] = calculaGrupo("ABV", fechaI, fechaF);
/*  649 */     ba[13] = calculaGrupo("ABVI", fechaI, fechaF);
/*      */ 
/*  651 */     bp[0] = calculaGrupo("PA1I1", fechaI, fechaF);
/*  652 */     bp[1] = calculaGrupo("PA1I2", fechaI, fechaF);
/*  653 */     bp[2] = calculaGrupo("PA1II", fechaI, fechaF);
/*  654 */     bp[3] = calculaGrupo("PA1III", fechaI, fechaF);
/*  655 */     bp[4] = calculaGrupo("PA1IV", fechaI, fechaF);
/*  656 */     bp[5] = calculaGrupo("PA1V", fechaI, fechaF);
/*  657 */     bp[6] = calculaGrupo("PA1VI", fechaI, fechaF);
/*  658 */     bp[7] = calculaGrupo("PA1VII", fechaI, fechaF);
/*  659 */     bp[8] = calculaGrupo("PA1VIII", fechaI, fechaF);
/*  660 */     bp[9] = calculaGrupo("PA2", fechaI, fechaF);
/*  661 */     bp[10] = calculaGrupo("PBI", fechaI, fechaF);
/*  662 */     bp[11] = calculaGrupo("PBII1", fechaI, fechaF);
/*  663 */     bp[12] = calculaGrupo("PBII2", fechaI, fechaF);
/*  664 */     bp[13] = calculaGrupo("PBIII", fechaI, fechaF);
/*  665 */     bp[14] = calculaGrupo("PBIV", fechaI, fechaF);
/*  666 */     bp[15] = calculaGrupo("PCI", fechaI, fechaF);
/*  667 */     bp[16] = calculaGrupo("PCII1", fechaI, fechaF);
/*  668 */     bp[17] = calculaGrupo("PCII2", fechaI, fechaF);
/*  669 */     bp[18] = calculaGrupo("PCIII", fechaI, fechaF);
/*  670 */     bp[19] = calculaGrupo("PCIV1", fechaI, fechaF);
/*  671 */     bp[20] = calculaGrupo("PCIV2", fechaI, fechaF);
/*  672 */     bp[21] = calculaGrupo("PCV", fechaI, fechaF);
/*  673 */     bp[22] = calculaGrupo("PBII3", fechaI, fechaF);
/*  674 */     bp[23] = calculaGrupo("PBV", fechaI, fechaF);
/*  675 */     bp[24] = calculaGrupo("PCII3", fechaI, fechaF);
/*      */ 
/*  677 */     double grupoEsp1 = calculaGrupo("ESP1", fechaI, fechaF);
/*  678 */     double grupoEsp2 = calculaGrupo("ESP2", fechaI, fechaF);
/*  679 */     if (grupoEsp1 >= 0.0D)
/*  680 */       ba[10] += grupoEsp1;
/*      */     else {
/*  682 */       bp[24] -= grupoEsp1;
/*      */     }
/*  684 */     if (grupoEsp2 >= 0.0D)
/*  685 */       ba[11] += grupoEsp2;
/*      */     else {
/*  687 */       bp[18] -= grupoEsp2;
/*      */     }
/*      */ 
/*  690 */     double[] t = new double[13];
/*      */ 
/*  692 */     for (int i = 0; i < 13; i++) {
/*  693 */       t[i] = 0.0D;
/*      */     }
/*  695 */     for (int i = 0; i < 6; i++) {
/*  696 */       t[0] += ba[i];
/*      */     }
/*  698 */     for (int i = 7; i < 10; i++) {
/*  699 */       t[2] += ba[i];
/*      */     }
/*  701 */     for (int i = 10; i < 14; i++) {
/*  702 */       t[1] += ba[i];
/*      */     }
/*  704 */     t[1] = (t[1] + t[2] + ba[6]);
/*  705 */     t[3] = (t[0] + t[1]);
/*      */ 
/*  707 */     t[6] = (bp[0] + bp[1]);
/*  708 */     for (int i = 2; i < 9; i++) {
/*  709 */       t[5] += bp[i];
/*      */     }
/*  711 */     t[5] += t[6];
/*  712 */     t[4] = (t[5] + bp[9]);
/*  713 */     t[8] = (bp[11] + bp[12] + bp[22]);
/*  714 */     t[7] = (bp[10] + t[8] + bp[13] + bp[14] + bp[23]);
/*  715 */     t[10] = (bp[16] + bp[17] + bp[24]);
/*  716 */     t[11] = (bp[19] + bp[20]);
/*  717 */     t[9] = (bp[15] + t[10] + bp[18] + t[11] + bp[21]);
/*  718 */     t[12] = (t[4] + t[7] + t[9]);
/*      */ 
/*  721 */     String fechaIW = pastYear(fechaI);
/*  722 */     String fechaFW = pastYear(fechaF);
/*  723 */     double[] baW = new double[14];
/*  724 */     double[] bpW = new double[25];
/*  725 */     for (int i = 0; i < 14; i++) {
/*  726 */       baW[i] = 0.0D;
/*      */     }
/*  728 */     for (int i = 0; i < 25; i++) {
/*  729 */       bpW[i] = 0.0D;
/*      */     }
/*  731 */     baW[0] = calculaGrupo("AAI", fechaIW, fechaFW);
/*  732 */     baW[1] = calculaGrupo("AAII", fechaIW, fechaFW);
/*  733 */     baW[2] = calculaGrupo("AAIII", fechaIW, fechaFW);
/*  734 */     baW[3] = calculaGrupo("AAIV", fechaIW, fechaFW);
/*  735 */     baW[4] = calculaGrupo("AAV", fechaIW, fechaFW);
/*  736 */     baW[5] = calculaGrupo("AAVI", fechaIW, fechaFW);
/*  737 */     baW[6] = calculaGrupo("ABI", fechaIW, fechaFW);
/*  738 */     baW[7] = calculaGrupo("ABII1", fechaIW, fechaFW);
/*  739 */     baW[8] = calculaGrupo("ABII2", fechaIW, fechaFW);
/*  740 */     baW[9] = calculaGrupo("ABII3", fechaIW, fechaFW);
/*  741 */     baW[10] = calculaGrupo("ABIII", fechaIW, fechaFW);
/*  742 */     baW[11] = calculaGrupo("ABIV", fechaIW, fechaFW);
/*  743 */     baW[12] = calculaGrupo("ABV", fechaIW, fechaFW);
/*  744 */     baW[13] = calculaGrupo("ABVI", fechaIW, fechaFW);
/*      */ 
/*  746 */     bpW[0] = calculaGrupo("PA1I1", fechaIW, fechaFW);
/*  747 */     bpW[1] = calculaGrupo("PA1I2", fechaIW, fechaFW);
/*  748 */     bpW[2] = calculaGrupo("PA1II", fechaIW, fechaFW);
/*  749 */     bpW[3] = calculaGrupo("PA1III", fechaIW, fechaFW);
/*  750 */     bpW[4] = calculaGrupo("PA1IV", fechaIW, fechaFW);
/*  751 */     bpW[5] = calculaGrupo("PA1V", fechaIW, fechaFW);
/*  752 */     bpW[6] = calculaGrupo("PA1VI", fechaIW, fechaFW);
/*  753 */     bpW[7] = calculaGrupo("PA1VII", fechaIW, fechaFW);
/*  754 */     bpW[8] = calculaGrupo("PA1VIII", fechaIW, fechaFW);
/*  755 */     bpW[9] = calculaGrupo("PA2", fechaIW, fechaFW);
/*  756 */     bpW[10] = calculaGrupo("PBI", fechaIW, fechaFW);
/*  757 */     bpW[11] = calculaGrupo("PBII1", fechaIW, fechaFW);
/*  758 */     bpW[12] = calculaGrupo("PBII2", fechaIW, fechaFW);
/*  759 */     bpW[13] = calculaGrupo("PBIII", fechaIW, fechaFW);
/*  760 */     bpW[14] = calculaGrupo("PBIV", fechaIW, fechaFW);
/*  761 */     bpW[15] = calculaGrupo("PCI", fechaIW, fechaFW);
/*  762 */     bpW[16] = calculaGrupo("PCII1", fechaIW, fechaFW);
/*  763 */     bpW[17] = calculaGrupo("PCII2", fechaIW, fechaFW);
/*  764 */     bpW[18] = calculaGrupo("PCIII", fechaIW, fechaFW);
/*  765 */     bpW[19] = calculaGrupo("PCIV1", fechaIW, fechaFW);
/*  766 */     bpW[20] = calculaGrupo("PCIV2", fechaIW, fechaFW);
/*  767 */     bpW[21] = calculaGrupo("PCV", fechaIW, fechaFW);
/*  768 */     bpW[22] = calculaGrupo("PBII3", fechaIW, fechaFW);
/*  769 */     bpW[23] = calculaGrupo("PBV", fechaIW, fechaFW);
/*  770 */     bpW[24] = calculaGrupo("PCII3", fechaIW, fechaFW);
/*      */ 
/*  772 */     double grupoEsp1W = calculaGrupo("ESP1", fechaIW, fechaFW);
/*  773 */     double grupoEsp2W = calculaGrupo("ESP2", fechaIW, fechaFW);
/*  774 */     if (grupoEsp1W >= 0.0D)
/*  775 */       baW[10] += grupoEsp1W;
/*      */     else {
/*  777 */       bpW[24] -= grupoEsp1W;
/*      */     }
/*  779 */     if (grupoEsp2W >= 0.0D)
/*  780 */       baW[11] += grupoEsp2W;
/*      */     else {
/*  782 */       bpW[18] -= grupoEsp2W;
/*      */     }
/*      */ 
/*  785 */     double[] tW = new double[13];
/*      */ 
/*  787 */     for (int i = 0; i < 13; i++) {
/*  788 */       tW[i] = 0.0D;
/*      */     }
/*  790 */     for (int i = 0; i < 6; i++) {
/*  791 */       tW[0] += baW[i];
/*      */     }
/*  793 */     for (int i = 7; i < 10; i++) {
/*  794 */       tW[2] += baW[i];
/*      */     }
/*  796 */     for (int i = 10; i < 14; i++) {
/*  797 */       tW[1] += baW[i];
/*      */     }
/*  799 */     tW[1] = (tW[1] + tW[2] + baW[6]);
/*  800 */     tW[3] = (tW[0] + tW[1]);
/*      */ 
/*  802 */     tW[6] = (bpW[0] + bpW[1]);
/*  803 */     for (int i = 2; i < 9; i++) {
/*  804 */       tW[5] += bpW[i];
/*      */     }
/*  806 */     tW[5] += tW[6];
/*  807 */     tW[4] = (tW[5] + bpW[9]);
/*  808 */     tW[8] = (bpW[11] + bpW[12] + bpW[22]);
/*  809 */     tW[7] = (bpW[10] + tW[8] + bpW[13] + bpW[14] + bpW[23]);
/*  810 */     tW[10] = (bpW[16] + bpW[17] + bpW[24]);
/*  811 */     tW[11] = (bpW[19] + bpW[20]);
/*  812 */     tW[9] = (bpW[15] + tW[10] + bpW[18] + tW[11] + bpW[21]);
/*  813 */     tW[12] = (tW[4] + tW[7] + tW[9]);
/*      */ 
/*  816 */     StringBuilder sb = new StringBuilder();
/*  817 */     Formatter formater = new Formatter(sb);
/*  818 */     formater.format(new StringBuilder().append("%s %s %s").append(EOL).toString(), new Object[] { Mensajes.getString("balance07"), Mensajes.getString("para"), nombreEmpresa });
/*      */ 
/*  820 */     formater.format(new StringBuilder().append("%s %s-%s y %s-%s").append(EOL).append(EOL).toString(), new Object[] { Mensajes.getString("entre"), fechaI.substring(6), fechaI.substring(4, 6), fechaF.substring(6), fechaF.substring(4, 6) });
/*      */ 
/*  823 */     formater.format(new StringBuilder().append("%s;%s;%s").append(EOL).toString(), new Object[] { Mensajes.getString("ejercicio"), Inicio.p.getEjercicio(), Integer.toString(Integer.parseInt(Inicio.p.getEjercicio()) - 1) });
/*  824 */     formater.format(new StringBuilder().append("ACTIVO").append(EOL).append(EOL).append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "A) ACTIVO NO CORRIENTE", Double.valueOf(t[0]), Double.valueOf(tW[0]) });
/*  825 */     if ((!listarCeros) || ((listarCeros) && ((ba[0] != 0.0D) || (baW[0] != 0.0D)))) {
/*  826 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "    I. Inmovilizado intangible", Double.valueOf(ba[0]), Double.valueOf(baW[0]) });
/*      */     }
/*  828 */     if ((!listarCeros) || ((listarCeros) && ((ba[1] != 0.0D) || (baW[1] != 0.0D)))) {
/*  829 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "    II. Inmovilizado material", Double.valueOf(ba[1]), Double.valueOf(baW[1]) });
/*      */     }
/*  831 */     if ((!listarCeros) || ((listarCeros) && ((ba[2] != 0.0D) || (baW[2] != 0.0D)))) {
/*  832 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "    III. Inversiones inmobiliarias", Double.valueOf(ba[2]), Double.valueOf(baW[2]) });
/*      */     }
/*  834 */     if ((!listarCeros) || ((listarCeros) && ((ba[3] != 0.0D) || (baW[3] != 0.0D)))) {
/*  835 */       formater.format(new StringBuilder().append("%s").append(EOL).toString(), new Object[] { "    IV. Inversiones en empresas del grupo y asociadas" });
/*  836 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "        a largo plazo", Double.valueOf(ba[3]), Double.valueOf(baW[3]) });
/*      */     }
/*  838 */     if ((!listarCeros) || ((listarCeros) && ((ba[4] != 0.0D) || (baW[4] != 0.0D)))) {
/*  839 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "    V. Inversiones financieras a largo plazo", Double.valueOf(ba[4]), Double.valueOf(baW[4]) });
/*      */     }
/*  841 */     if ((!listarCeros) || ((listarCeros) && ((ba[5] != 0.0D) || (baW[5] != 0.0D)))) {
/*  842 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "    VI. Activos por Impuesto diferido", Double.valueOf(ba[5]), Double.valueOf(baW[5]) });
/*      */     }
/*  844 */     formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "B) ACTIVO CORRIENTE", Double.valueOf(t[1]), Double.valueOf(tW[1]) });
/*  845 */     if ((!listarCeros) || ((listarCeros) && ((ba[6] != 0.0D) || (baW[6] != 0.0D)))) {
/*  846 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "    I. Existencias", Double.valueOf(ba[6]), Double.valueOf(baW[6]) });
/*      */     }
/*  848 */     if ((!listarCeros) || ((listarCeros) && ((t[2] != 0.0D) || (tW[2] != 0.0D)))) {
/*  849 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "    II. Deudores comerciales y otras cuentas a cobrar", Double.valueOf(t[2]), Double.valueOf(tW[2]) });
/*      */     }
/*  851 */     if ((!listarCeros) || ((listarCeros) && ((ba[7] != 0.0D) || (baW[7] != 0.0D)))) {
/*  852 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "       1. Clientes por ventas y Prestación de servicios", Double.valueOf(ba[7]), Double.valueOf(baW[7]) });
/*      */     }
/*  854 */     if ((!listarCeros) || ((listarCeros) && (ba[8] != 0.0D)) || (baW[8] != 0.0D)) {
/*  855 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "       2. Accionistas (socios) por desembolsos exigidos", Double.valueOf(ba[8]), Double.valueOf(baW[8]) });
/*      */     }
/*  857 */     if ((!listarCeros) || ((listarCeros) && ((ba[9] != 0.0D) || (baW[9] != 0.0D)))) {
/*  858 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "       3. Otros deudores", Double.valueOf(ba[9]), Double.valueOf(baW[9]) });
/*      */     }
/*  860 */     if ((!listarCeros) || ((listarCeros) && ((ba[10] != 0.0D) || (baW[10] != 0.0D)))) {
/*  861 */       formater.format(new StringBuilder().append("%s").append(EOL).toString(), new Object[] { "    III. Inversiones en empresas del grupo y asociadas" });
/*  862 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "         a corto plazo", Double.valueOf(ba[10]), Double.valueOf(baW[10]) });
/*      */     }
/*  864 */     if ((!listarCeros) || ((listarCeros) && ((ba[11] != 0.0D) || (baW[11] != 0.0D)))) {
/*  865 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "    IV. Inversiones financieras a corto plazo", Double.valueOf(ba[11]), Double.valueOf(baW[11]) });
/*      */     }
/*  867 */     if ((!listarCeros) || ((listarCeros) && ((ba[12] != 0.0D) || (baW[12] != 0.0D)))) {
/*  868 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "    V. Periodificaciones a corto plazo", Double.valueOf(ba[12]), Double.valueOf(baW[12]) });
/*      */     }
/*  870 */     if ((!listarCeros) || ((listarCeros) && ((ba[13] != 0.0D) || (baW[12] != 0.0D)))) {
/*  871 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "    VI. Efectivo y otros activos líquidos equivalentes", Double.valueOf(ba[13]), Double.valueOf(baW[13]) });
/*      */     }
/*  873 */     formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "TOTAL ACTIVO (A+B)", Double.valueOf(t[3]), Double.valueOf(tW[3]) });
/*  874 */     formater.format(new StringBuilder().append(EOL).append("PATRIMONIO NETO Y PASIVO").append(EOL).append(EOL).append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "A) PATRIMONIO NETO", Double.valueOf(t[4]), Double.valueOf(tW[4]) });
/*  875 */     if ((!listarCeros) || ((listarCeros) && (t[5] != 0.0D)) || (tW[5] != 0.0D)) {
/*  876 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "  A-1) Fondos propios", Double.valueOf(t[5]), Double.valueOf(tW[5]) });
/*      */     }
/*  878 */     if ((!listarCeros) || ((listarCeros) && (t[6] != 0.0D)) || (tW[6] != 0.0D)) {
/*  879 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "    I. Capital", Double.valueOf(t[6]), Double.valueOf(tW[6]) });
/*      */     }
/*  881 */     if ((!listarCeros) || ((listarCeros) && ((bp[0] != 0.0D) || (bpW[0] != 0.0D)))) {
/*  882 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "       1. Capital escriturado", Double.valueOf(bp[0]), Double.valueOf(bpW[0]) });
/*      */     }
/*  884 */     if ((!listarCeros) || ((listarCeros) && ((bp[1] != 0.0D) || (bpW[1] != 0.0D)))) {
/*  885 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "       2. (Capital no exigido)", Double.valueOf(bp[1]), Double.valueOf(bpW[1]) });
/*      */     }
/*  887 */     if ((!listarCeros) || ((listarCeros) && ((bp[2] != 0.0D) || (bpW[2] != 0.0D)))) {
/*  888 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "    II. Primas de emisión", Double.valueOf(bp[2]), Double.valueOf(bpW[2]) });
/*      */     }
/*  890 */     if ((!listarCeros) || ((listarCeros) && ((bp[3] != 0.0D) || (bpW[3] != 0.0D)))) {
/*  891 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "    III. Reservas", Double.valueOf(bp[3]), Double.valueOf(bpW[3]) });
/*      */     }
/*  893 */     if ((!listarCeros) || ((listarCeros) && ((bp[4] != 0.0D) || (bpW[4] != 0.0D)))) {
/*  894 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "    IV. (Acciones y participaciones en patrimonio propio)", Double.valueOf(bp[4]), Double.valueOf(bpW[4]) });
/*      */     }
/*  896 */     if ((!listarCeros) || ((listarCeros) && ((bp[5] != 0.0D) || (bpW[5] != 0.0D)))) {
/*  897 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "    V. Resultados de ejercicios anteriores", Double.valueOf(bp[5]), Double.valueOf(bpW[5]) });
/*      */     }
/*  899 */     if ((!listarCeros) || ((listarCeros) && ((bp[6] != 0.0D) || (bpW[6] != 0.0D)))) {
/*  900 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "    VI. Otras aportaciones de socios", Double.valueOf(bp[6]), Double.valueOf(bpW[6]) });
/*      */     }
/*  902 */     if ((!listarCeros) || ((listarCeros) && ((bp[7] != 0.0D) || (bpW[7] != 0.0D)))) {
/*  903 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "    VII. Resultado del ejercicio", Double.valueOf(bp[7]), Double.valueOf(bpW[7]) });
/*      */     }
/*  905 */     if ((!listarCeros) || ((listarCeros) && ((bp[8] != 0.0D) || (bpW[8] != 0.0D)))) {
/*  906 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "    VIII. (Dividendo a cuenta)", Double.valueOf(bp[8]), Double.valueOf(bpW[8]) });
/*      */     }
/*  908 */     if ((!listarCeros) || ((listarCeros) && ((bp[9] != 0.0D) || (bpW[9] != 0.0D)))) {
/*  909 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "  A-2) Subvenciones, donaciones y legados recibidos", Double.valueOf(bp[9]), Double.valueOf(bpW[9]) });
/*      */     }
/*  911 */     formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "B) PASIVO NO CORRIENTE", Double.valueOf(t[7]), Double.valueOf(tW[7]) });
/*  912 */     if ((!listarCeros) || ((listarCeros) && ((bp[10] != 0.0D) || (bpW[10] != 0.0D)))) {
/*  913 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "    I. Provisiones a largo plazo", Double.valueOf(bp[10]), Double.valueOf(bpW[10]) });
/*      */     }
/*  915 */     if ((!listarCeros) || ((listarCeros) && (t[8] != 0.0D))) {
/*  916 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "    II. Deudas a largo plazo", Double.valueOf(t[8]), Double.valueOf(tW[8]) });
/*      */     }
/*  918 */     if ((!listarCeros) || ((listarCeros) && ((bp[11] != 0.0D) || (bpW[11] != 0.0D)))) {
/*  919 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "       1. Deudas con entidades de crédito", Double.valueOf(bp[11]), Double.valueOf(bpW[11]) });
/*      */     }
/*  921 */     if ((!listarCeros) || ((listarCeros) && ((bp[12] != 0.0D) || (bpW[12] != 0.0D)))) {
/*  922 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "       2. Acreedores por arrendamiento financiero", Double.valueOf(bp[12]), Double.valueOf(bpW[12]) });
/*      */     }
/*  924 */     if ((!listarCeros) || ((listarCeros) && ((bp[22] != 0.0D) || (bpW[22] != 0.0D)))) {
/*  925 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "       3. Otras deudas a largo plazo", Double.valueOf(bp[22]), Double.valueOf(bpW[22]) });
/*      */     }
/*  927 */     if ((!listarCeros) || ((listarCeros) && ((bp[13] != 0.0D) || (bpW[13] != 0.0D)))) {
/*  928 */       formater.format(new StringBuilder().append("%s").append(EOL).toString(), new Object[] { "    III. Deudas con empresas del grupo y asociadas" });
/*  929 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "         a largo plazo", Double.valueOf(bp[13]), Double.valueOf(bpW[13]) });
/*      */     }
/*  931 */     if ((!listarCeros) || ((listarCeros) && ((bp[14] != 0.0D) || (bpW[14] != 0.0D)))) {
/*  932 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "    IV. Pasivos por impuesto diferido", Double.valueOf(bp[14]), Double.valueOf(bpW[14]) });
/*      */     }
/*  934 */     if ((!listarCeros) || ((listarCeros) && ((bp[23] != 0.0D) || (bpW[23] != 0.0D)))) {
/*  935 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "    V. Periodificaciones a largo plazo", Double.valueOf(bp[23]), Double.valueOf(bpW[23]) });
/*      */     }
/*  937 */     formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "C) PASIVO CORRIENTE", Double.valueOf(t[9]), Double.valueOf(tW[9]) });
/*  938 */     if ((!listarCeros) || ((listarCeros) && ((bp[15] != 0.0D) || (bpW[15] != 0.0D)))) {
/*  939 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "    I. Provisiones a corto plazo", Double.valueOf(bp[15]), Double.valueOf(bpW[15]) });
/*      */     }
/*  941 */     if ((!listarCeros) || ((listarCeros) && ((t[10] != 0.0D) || (tW[10] != 0.0D)))) {
/*  942 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "    II. Deudas a corto plazo", Double.valueOf(t[10]), Double.valueOf(tW[10]) });
/*      */     }
/*  944 */     if ((!listarCeros) || ((listarCeros) && ((bp[16] != 0.0D) || (bpW[16] != 0.0D)))) {
/*  945 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "       1. Deudas con entidades de crédito", Double.valueOf(bp[16]), Double.valueOf(bpW[16]) });
/*      */     }
/*  947 */     if ((!listarCeros) || ((listarCeros) && ((bp[17] != 0.0D) || (bpW[17] != 0.0D)))) {
/*  948 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "       2. Acreedores por arrendamiento financiero", Double.valueOf(bp[17]), Double.valueOf(bpW[17]) });
/*      */     }
/*  950 */     if ((!listarCeros) || ((listarCeros) && ((bp[24] != 0.0D) || (bpW[24] != 0.0D)))) {
/*  951 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "       3. Otras deudas a corto plazo", Double.valueOf(bp[24]), Double.valueOf(bpW[24]) });
/*      */     }
/*  953 */     if ((!listarCeros) || ((listarCeros) && ((bp[18] != 0.0D) || (bpW[18] != 0.0D)))) {
/*  954 */       formater.format(new StringBuilder().append("%s").append(EOL).toString(), new Object[] { "    III. Deudas con empresas del grupo y asociadas" });
/*  955 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "         a corto plazo", Double.valueOf(bp[18]), Double.valueOf(bpW[18]) });
/*      */     }
/*  957 */     if ((!listarCeros) || ((listarCeros) && ((t[11] != 0.0D) || (tW[11] != 0.0D)))) {
/*  958 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "    IV. Acreedores comerciales y otras cuentas a pagar", Double.valueOf(t[11]), Double.valueOf(tW[11]) });
/*      */     }
/*  960 */     if ((!listarCeros) || ((listarCeros) && ((bp[19] != 0.0D) || (bpW[19] != 0.0D)))) {
/*  961 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "       1. Proveedores", Double.valueOf(bp[19]), Double.valueOf(bpW[19]) });
/*      */     }
/*  963 */     if ((!listarCeros) || ((listarCeros) && ((bp[20] != 0.0D) || (bpW[20] != 0.0D)))) {
/*  964 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "       2. Otros acreedores", Double.valueOf(bp[20]), Double.valueOf(bpW[20]) });
/*      */     }
/*  966 */     if ((!listarCeros) || ((listarCeros) && ((bp[21] != 0.0D) || (bpW[21] != 0.0D)))) {
/*  967 */       formater.format(new StringBuilder().append("%s;%.2f;%.2f").append(EOL).toString(), new Object[] { "    V. Periodificaciones", Double.valueOf(bp[21]), Double.valueOf(bpW[21]) });
/*      */     }
/*  969 */     formater.format("%s;%.2f;%.2f", new Object[] { "TOTAL PATRIMONIO NETO Y PASIVO (A+B+C)", Double.valueOf(t[12]), Double.valueOf(tW[12]) });
/*  970 */     this.listado.add(sb.toString());
/*  971 */     setCursor(Cursor.getPredefinedCursor(0));
/*  972 */     JFileChooser fc = new JFileChooser();
/*  973 */     fc.setSelectedFile(new File("Balance.csv"));
/*  974 */     int retorno = fc.showSaveDialog(this);
/*  975 */     if (retorno == 0) {
/*  976 */       File archivo = fc.getSelectedFile();
/*  977 */       archivo = AddExtension.csv(archivo);
/*  978 */       GrabarFichero salida = new GrabarFichero(archivo);
/*  979 */       for (String linea : this.listado) {
/*  980 */         salida.insertar(linea);
/*      */       }
/*  982 */       salida.cerrar();
/*      */     }
/*      */   }
/*      */ 
/*      */   private double calculaGrupo(String grupo, String fechaI, String fechaF)
/*      */   {
/*  995 */     if ((grupo.equals("")) || (grupo.length() < 1)) {
/*  996 */       return 0.0D;
/*      */     }
/*  998 */     double importe = 0.0D; double importeDebe = 0.0D; double importeHaber = 0.0D;
/*      */     try {
/* 1000 */       String ejercicio = fechaI.substring(0, 4);
/* 1001 */       String cadenaSeleccion = new StringBuilder().append("SELECT SUM(apt.importe) FROM apte").append(ejercicio).append(" AS apt ").append("JOIN scta").append(ejercicio).append(" as scta ON apt.cuenta=scta.codigo ").append("JOIN asto").append(ejercicio).append(" as ast ON apt.id_asiento=ast.id_asiento ").append("WHERE scta.gbalance = '").append(grupo).append("' AND ").append("ast.fecha BETWEEN '").append(fechaI).append("' AND '").append(fechaF).append("' ").append("AND ast.marca NOT LIKE 'C'").toString();
/*      */ 
/* 1008 */       this.res = Inicio.getCEmpresa().getRes(new StringBuilder().append(cadenaSeleccion).append(" AND apt.DH = 'D'").toString());
/*      */ 
/* 1010 */       if (this.res.next()) {
/* 1011 */         importeDebe = this.res.getDouble(1);
/*      */       }
/* 1013 */       this.res = Inicio.getCEmpresa().getRes(new StringBuilder().append(cadenaSeleccion).append(" AND apt.DH = 'H'").toString());
/*      */ 
/* 1015 */       if (this.res.next())
/* 1016 */         importeHaber = this.res.getDouble(1);
/*      */     }
/*      */     catch (SQLException exc) {
/* 1019 */       System.out.println(exc.getMessage());
/*      */     }
/* 1021 */     if (grupo.substring(0, 1).equals("E"))
/* 1022 */       importe = importeDebe - importeHaber;
/*      */     else {
/* 1024 */       importe = grupo.substring(0, 1).equals("A") ? importeDebe - importeHaber : importeHaber - importeDebe;
/*      */     }
/* 1026 */     if (!grupo.substring(grupo.length() - 1).equals("-")) {
/* 1027 */       importe += calculaGrupo(new StringBuilder().append(grupo).append("-").toString(), fechaI, fechaF);
/*      */     }
/* 1029 */     return importe;
/*      */   }
/*      */ 
/*      */   private String pastYear(String fecha) {
/* 1033 */     int year = Integer.parseInt(fecha.substring(0, 4));
/* 1034 */     int mes = Integer.parseInt(fecha.substring(5, 7));
/* 1035 */     int dia = Integer.parseInt(fecha.substring(8));
/* 1036 */     GregorianCalendar cal = new GregorianCalendar(year, mes - 1, dia);
/* 1037 */     cal.add(1, -1);
/* 1038 */     Date datTemp = new Date(cal.getTimeInMillis());
/* 1039 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/* 1040 */     return sdf.format(datTemp);
/*      */   }
/*      */ 
/*      */   protected static ImageIcon createImageIcon(String path)
/*      */   {
/* 1050 */     URL imgURL = Balance08.class.getResource(path);
/* 1051 */     if (imgURL != null) {
/* 1052 */       return new ImageIcon(imgURL);
/*      */     }
/* 1054 */     System.err.println(new StringBuilder().append("Couldn't find file: ").append(path).toString());
/* 1055 */     return null;
/*      */   }
/*      */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.listados.Balance08
 * JD-Core Version:    0.6.2
 */