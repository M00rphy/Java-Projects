/*     */ package contaes.listados;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.Puente;
/*     */ import contaes.auxiliar.CampoCuenta;
/*     */ import contaes.calendario.ICalendarTextField;
/*     */ import contaes.dialogosAuxiliares.MostrarCuentas;
/*     */ import contaes.manejoDatos.ManejoSubcuentas;
/*     */ import contaes.manejoDatos.auxiliar.FinLinea;
/*     */ import contaes.manejoDatos.auxiliar.ImportesCuentaEntreFechas;
/*     */ import contaes.manejoDatos.auxiliar.LeerFichero;
/*     */ import contaes.manejoDatos.auxiliar.MySQLConection;
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.Container;
/*     */ import java.awt.Cursor;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Frame;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Point;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.ItemEvent;
/*     */ import java.awt.event.ItemListener;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.KeyListener;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.net.URL;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.Formatter;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Properties;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.text.JTextComponent;
/*     */ 
/*     */ public class ListadoNiveles extends JDialog
/*     */   implements ActionListener, KeyListener
/*     */ {
/*     */   private static final String ACEPTAR = "aceptar";
/*     */   private static final String CANCELAR = "cancelar";
/*  75 */   private ManejoSubcuentas subcuentaM = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*     */ 
/*  77 */   boolean listar = false;
/*  78 */   private List<String> listado = new ArrayList();
/*     */ 
/*  80 */   JPanel panel1 = new JPanel();
/*  81 */   GridBagLayout gbLayout = new GridBagLayout();
/*  82 */   JLabel etiq1 = new JLabel();
/*  83 */   JLabel etiq2 = new JLabel();
/*  84 */   JLabel etiq3 = new JLabel();
/*  85 */   JLabel etiq4 = new JLabel();
/*  86 */   JLabel etiq5 = new JLabel();
/*  87 */   ICalendarTextField campoFecha = new ICalendarTextField();
/*  88 */   ICalendarTextField campoFechaF = new ICalendarTextField();
/*  89 */   CampoCuenta campoCuentaI = new CampoCuenta(this.subcuentaM);
/*  90 */   CampoCuenta campoCuentaF = new CampoCuenta(this.subcuentaM);
/*  91 */   JButton aceptar = new JButton();
/*  92 */   JButton cancelar = new JButton();
/*  93 */   String[] opciones = { Mensajes.getString("uno"), Mensajes.getString("dos"), Mensajes.getString("tres"), Mensajes.getString("ocho") };
/*  94 */   JComboBox niveles = new JComboBox(this.opciones);
/*  95 */   JCheckBox ceros = new JCheckBox();
/*  96 */   ImageIcon iconoAceptar = createImageIcon("/contaes/iconos/ok.png");
/*  97 */   ImageIcon iconoCancelar = createImageIcon("/contaes/iconos/cancel.png");
/*     */ 
/*     */   public ListadoNiveles(Frame owner, String title, boolean modal) {
/* 100 */     super(owner, title, modal);
/*     */     try {
/* 102 */       setDefaultCloseOperation(2);
/* 103 */       initialize();
/* 104 */       pack();
/*     */     }
/*     */     catch (Exception exception) {
/* 107 */       exception.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public ListadoNiveles() {
/* 112 */     this(new Frame(), Mensajes.getString("niveles"), true);
/*     */   }
/*     */ 
/*     */   private void initialize() throws Exception {
/* 116 */     this.etiq1.setText(Mensajes.getString("fechaIni"));
/* 117 */     this.etiq2.setText(Mensajes.getString("fechaFin"));
/* 118 */     this.etiq3.setText(Mensajes.getString("ctaIni"));
/* 119 */     this.etiq4.setText(Mensajes.getString("ctaFin"));
/* 120 */     this.etiq5.setText(Mensajes.getString("queNivel"));
/* 121 */     this.aceptar.setText(Mensajes.getString("aceptar"));
/* 122 */     this.aceptar.setIcon(this.iconoAceptar);
/* 123 */     this.aceptar.setVerticalTextPosition(0);
/* 124 */     this.aceptar.setHorizontalTextPosition(2);
/* 125 */     this.aceptar.setActionCommand("aceptar");
/* 126 */     this.aceptar.addActionListener(this);
/* 127 */     this.cancelar.setText(Mensajes.getString("cancelar"));
/* 128 */     this.cancelar.setIcon(this.iconoCancelar);
/* 129 */     this.cancelar.setVerticalTextPosition(0);
/* 130 */     this.cancelar.setHorizontalTextPosition(2);
/* 131 */     this.cancelar.setActionCommand("cancelar");
/* 132 */     this.cancelar.addActionListener(this);
/* 133 */     this.ceros.setText(Mensajes.getString("nosaldoscero"));
/* 134 */     this.ceros.setSelected(false);
/* 135 */     this.campoFecha.setMinimumSize(new Dimension(100, 26));
/* 136 */     this.campoFecha.setToolTipText(Mensajes.getString("formatoFecha"));
/* 137 */     this.campoFecha.setComponente(this.campoFechaF);
/* 138 */     this.campoFechaF.setMinimumSize(new Dimension(100, 26));
/* 139 */     this.campoFechaF.setToolTipText(Mensajes.getString("formatoFecha"));
/* 140 */     this.campoFechaF.setComponente(this.campoCuentaI);
/* 141 */     this.campoCuentaI.addKeyListener(this);
/* 142 */     this.campoCuentaF.addKeyListener(this);
/* 143 */     this.niveles.addItemListener(new ItemListener() {
/*     */       public void itemStateChanged(ItemEvent e) {
/* 145 */         ListadoNiveles.this.cuentasOnOff();
/*     */       }
/*     */     });
/* 148 */     cuentasOnOff();
/*     */ 
/* 150 */     this.panel1.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(1), BorderFactory.createEmptyBorder(5, 5, 5, 5))));
/*     */ 
/* 153 */     this.panel1.setLayout(this.gbLayout);
/*     */ 
/* 155 */     GridBagConstraints cons = new GridBagConstraints();
/* 156 */     cons.insets.bottom = 4;
/* 157 */     cons.insets.top = 4;
/* 158 */     cons.insets.left = 4;
/* 159 */     cons.insets.right = 4;
/*     */ 
/* 161 */     cons.gridy = 0;
/* 162 */     cons.gridx = 0;
/* 163 */     this.gbLayout.setConstraints(this.etiq1, cons);
/* 164 */     this.panel1.add(this.etiq1);
/*     */ 
/* 166 */     cons.gridx = 1;
/* 167 */     this.gbLayout.setConstraints(this.etiq2, cons);
/* 168 */     this.panel1.add(this.etiq2);
/*     */ 
/* 171 */     cons.fill = 2;
/* 172 */     cons.gridy = 1;
/* 173 */     cons.gridx = 0;
/* 174 */     this.gbLayout.setConstraints(this.campoFecha, cons);
/* 175 */     this.panel1.add(this.campoFecha);
/*     */ 
/* 177 */     cons.gridx = 1;
/* 178 */     this.gbLayout.setConstraints(this.campoFechaF, cons);
/* 179 */     this.panel1.add(this.campoFechaF);
/*     */ 
/* 182 */     cons.gridy = 2;
/* 183 */     cons.gridx = 0;
/* 184 */     this.gbLayout.setConstraints(this.etiq3, cons);
/* 185 */     this.panel1.add(this.etiq3);
/*     */ 
/* 187 */     cons.gridx = 1;
/* 188 */     this.gbLayout.setConstraints(this.etiq4, cons);
/* 189 */     this.panel1.add(this.etiq4);
/*     */ 
/* 192 */     cons.fill = 2;
/* 193 */     cons.gridy = 4;
/* 194 */     cons.gridx = 0;
/* 195 */     this.gbLayout.setConstraints(this.campoCuentaI, cons);
/* 196 */     this.panel1.add(this.campoCuentaI);
/*     */ 
/* 198 */     cons.gridx = 1;
/* 199 */     this.gbLayout.setConstraints(this.campoCuentaF, cons);
/* 200 */     this.panel1.add(this.campoCuentaF);
/*     */ 
/* 203 */     cons.fill = 0;
/* 204 */     cons.anchor = 10;
/* 205 */     cons.gridy = 6;
/* 206 */     cons.gridx = 0;
/* 207 */     this.gbLayout.setConstraints(this.aceptar, cons);
/* 208 */     this.panel1.add(this.aceptar);
/*     */ 
/* 210 */     cons.gridx = 1;
/* 211 */     this.gbLayout.setConstraints(this.cancelar, cons);
/* 212 */     this.panel1.add(this.cancelar);
/*     */ 
/* 214 */     cons.gridy = 3;
/* 215 */     cons.gridx = 0;
/* 216 */     cons.anchor = 13;
/* 217 */     this.gbLayout.setConstraints(this.etiq5, cons);
/* 218 */     this.panel1.add(this.etiq5);
/*     */ 
/* 220 */     cons.anchor = 10;
/* 221 */     cons.gridx = 1;
/* 222 */     this.gbLayout.setConstraints(this.niveles, cons);
/* 223 */     this.panel1.add(this.niveles);
/*     */ 
/* 225 */     cons.gridy = 5;
/* 226 */     cons.gridx = 0;
/* 227 */     cons.gridwidth = 2;
/* 228 */     this.gbLayout.setConstraints(this.ceros, cons);
/* 229 */     this.panel1.add(this.ceros);
/*     */ 
/* 231 */     getContentPane().add(this.panel1);
/*     */   }
/*     */ 
/*     */   public void actionPerformed(ActionEvent e) {
/* 235 */     String cmd = e.getActionCommand();
/*     */ 
/* 237 */     if ("aceptar".equals(cmd)) {
/* 238 */       int cuentaI = 0; int cuentaF = 0;
/* 239 */       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/* 240 */       Date fecha_d = this.campoFecha.getDate();
/* 241 */       String fecha = sdf.format(fecha_d);
/* 242 */       fecha_d = this.campoFechaF.getDate();
/* 243 */       String fechaF = sdf.format(fecha_d);
/* 244 */       if ((!fecha.equals("")) && (!fechaF.equals(""))) {
/* 245 */         if (this.niveles.getSelectedItem().toString().equals(Mensajes.getString("ocho"))) {
/*     */           try {
/* 247 */             cuentaI = Integer.parseInt(this.campoCuentaI.getText());
/* 248 */             cuentaF = Integer.parseInt(this.campoCuentaF.getText());
/*     */           }
/*     */           catch (NumberFormatException exc) {
/* 251 */             System.out.println("Excepci�n en formato de n�mero");
/*     */ 
/* 253 */             return;
/*     */           }
/*     */         }
/* 256 */         setCursor(Cursor.getPredefinedCursor(3));
/*     */ 
/* 258 */         if (hacerListado(fecha, fechaF, cuentaI, cuentaF)) this.listar = true;
/* 259 */         setCursor(Cursor.getPredefinedCursor(0));
/* 260 */         dispose();
/*     */       }
/*     */     }
/* 263 */     else if ("cancelar".equals(cmd)) {
/* 264 */       this.listar = false;
/* 265 */       dispose();
/*     */     }
/*     */   }
/*     */ 
/*     */   public List<String> textoListado() {
/* 270 */     return this.listado;
/*     */   }
/*     */ 
/*     */   public boolean Listar() {
/* 274 */     return this.listar;
/*     */   }
/*     */ 
/*     */   private void cuentasOnOff() {
/* 278 */     if (this.niveles.getSelectedItem().toString().equals(Mensajes.getString("ocho"))) {
/* 279 */       this.campoCuentaI.setEnabled(true);
/* 280 */       this.campoCuentaF.setEnabled(true);
/*     */     }
/*     */     else {
/* 283 */       this.campoCuentaI.setEnabled(false);
/* 284 */       this.campoCuentaF.setEnabled(false);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected boolean hacerListado(String fechaI, String fechaF, int cuentaI, int cuentaF) {
/* 289 */     setCursor(Cursor.getPredefinedCursor(3));
/* 290 */     String EOL = FinLinea.get();
/* 291 */     boolean listarCeros = this.ceros.isSelected();
/* 292 */     int nivel = this.niveles.getSelectedIndex() + 1;
/* 293 */     double debeTotal = 0.0D;
/* 294 */     double haberTotal = 0.0D;
/* 295 */     double saldoTotal = 0.0D;
/* 296 */     StringBuilder sb = new StringBuilder();
/* 297 */     Formatter formater = new Formatter(sb);
/* 298 */     List codigoCuentas = new LinkedList();
/* 299 */     if (nivel <= 3)
/*     */     {
/* 301 */       Properties sistema = System.getProperties();
/* 302 */       String sisOp = sistema.getProperty("os.name");
/* 303 */       String archivoPGC = sisOp.substring(0, 3).equals("Mac") ? "pgc08MAC.txt" : "pgc08.txt";
/*     */       try
/*     */       {
/* 306 */         LeerFichero pgc = new LeerFichero(new StringBuilder().append("configdir/").append(archivoPGC).toString());
/* 307 */         int numeroCuentas = Integer.parseInt(pgc.leer());
/* 308 */         for (int x = 0; x < numeroCuentas; x++) {
/* 309 */           String linea = pgc.leer();
/* 310 */           String cuenta = linea.substring(0, linea.indexOf(";"));
/* 311 */           if (cuenta.length() == nivel)
/* 312 */             codigoCuentas.add(cuenta);
/* 313 */           if (pgc.eof()) break;
/*     */         }
/*     */       } catch (IOException e) {
/* 316 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/*     */       try {
/* 322 */         ResultSet res = Inicio.getCEmpresa().getRes(new StringBuilder().append("SELECT codigo FROM scta").append(Inicio.p.getEjercicio()).append(" WHERE codigo BETWEEN ").append(cuentaI).append(" AND ").append(cuentaF).toString());
/*     */ 
/* 324 */         while (res.next())
/* 325 */           codigoCuentas.add(res.getString(1));
/*     */       }
/*     */       catch (SQLException exc)
/*     */       {
/* 329 */         exc.printStackTrace();
/* 330 */         return false;
/*     */       }
/*     */     }
/* 333 */     ImportesCuentaEntreFechas[] cuenta = new ImportesCuentaEntreFechas[codigoCuentas.size()];
/* 334 */     int i = 0;
/* 335 */     for (String codigo :(List<String>) codigoCuentas) {
/* 336 */       cuenta[(i++)] = new ImportesCuentaEntreFechas(codigo, fechaI, fechaF);
/*     */     }
/* 338 */     formater.format(new StringBuilder().append("%10s %-40s %13s %13s %13s").append(EOL).toString(), new Object[] { Mensajes.getString("cuenta"), Mensajes.getString("titulo"), Mensajes.getString("debe"), Mensajes.getString("haber"), Mensajes.getString("saldo") });
/*     */ 
/* 341 */     for (i = 0; i < cuenta.length; i++) {
/* 342 */       if ((!listarCeros) || ((listarCeros) && ((cuenta[i].saldo() != 0.0D) || (cuenta[i].haber() != 0.0D) || (cuenta[i].debe() != 0.0D)))) {
/* 343 */         debeTotal += cuenta[i].debe();
/* 344 */         haberTotal += cuenta[i].haber();
/* 345 */         formater.format(new StringBuilder().append("%10s %-40.40s %,13.2f %,13.2f %,13.2f").append(EOL).toString(), new Object[] { cuenta[i].codigo(), cuenta[i].titulo(), Double.valueOf(cuenta[i].debe()), Double.valueOf(cuenta[i].haber()), Double.valueOf(cuenta[i].saldo()) });
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 350 */     saldoTotal = debeTotal - haberTotal;
/* 351 */     formater.format(new StringBuilder().append(EOL).append("%50s: %,13.2f %,13.2f %,13.2f").toString(), new Object[] { Mensajes.getString("totales"), Double.valueOf(debeTotal), Double.valueOf(haberTotal), Double.valueOf(saldoTotal) });
/*     */ 
/* 353 */     this.listado.add(sb.toString());
/*     */ 
/* 355 */     setCursor(Cursor.getPredefinedCursor(0));
/* 356 */     return true;
/*     */   }
/*     */ 
/*     */   protected static ImageIcon createImageIcon(String path) {
/* 360 */     URL imgURL = ListadoNiveles.class.getResource(path);
/* 361 */     if (imgURL != null)
/* 362 */       return new ImageIcon(imgURL);
/* 363 */     System.err.println(new StringBuilder().append("Couldn't find file: ").append(path).toString());
/* 364 */     return null;
/*     */   }
/*     */ 
/*     */   public void keyPressed(KeyEvent arg0) {
/* 368 */     int tecla = arg0.getKeyCode();
/* 369 */     Object campo = arg0.getSource();
/* 370 */     if ((arg0.isAltDown()) && (tecla == 67) && (
/* 371 */       (campo == this.campoCuentaI) || (campo == this.campoCuentaF))) {
/* 372 */       MostrarCuentas dlg2 = new MostrarCuentas(this, Mensajes.getString("cuentas"), true);
/* 373 */       Dimension dlgSize = dlg2.getPreferredSize();
/* 374 */       Dimension frmSize = getSize();
/* 375 */       Point loc = getLocation();
/* 376 */       dlg2.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
/*     */ 
/* 378 */       dlg2.setVisible(true);
/* 379 */       if (!dlg2.Seleccion().equals("")) {
/* 380 */         ((JTextComponent)campo).setText(dlg2.Seleccion());
/*     */       }
/*     */     }
/* 383 */     if (tecla == 10)
/* 384 */       if (campo == this.campoCuentaI)
/* 385 */         this.campoCuentaF.requestFocus();
/* 386 */       else if (campo == this.campoCuentaF)
/* 387 */         this.niveles.requestFocus();
/* 388 */       else if (campo == this.niveles)
/* 389 */         this.aceptar.requestFocus();
/*     */   }
/*     */ 
/*     */   public void keyReleased(KeyEvent arg0)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void keyTyped(KeyEvent arg0)
/*     */   {
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.listados.ListadoNiveles
 * JD-Core Version:    0.6.2
 */