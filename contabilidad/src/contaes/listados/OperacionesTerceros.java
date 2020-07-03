/*     */ package contaes.listados;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.Puente;
/*     */ import contaes.auxiliar.DocNumeros;
/*     */ import contaes.manejoDatos.auxiliar.FinLinea;
/*     */ import contaes.manejoDatos.auxiliar.MySQLConection;
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.Frame;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.HeadlessException;
/*     */ import java.awt.Insets;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.PrintStream;
/*     */ import java.net.URL;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.DecimalFormatSymbols;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Formatter;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.ButtonGroup;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JRadioButton;
/*     */ import javax.swing.JTextField;
/*     */ 
/*     */ public class OperacionesTerceros extends JDialog
/*     */ {
/*  56 */   private JPanel jContentPane = null;
/*  57 */   private ButtonGroup grupo = new ButtonGroup();
/*  58 */   private JRadioButton bCompras = null;
/*  59 */   private JRadioButton bVentas = null;
/*  60 */   private JRadioButton bEfectivo = null;
/*  61 */   private JLabel jLabel = null;
/*  62 */   private JTextField cImporte = null;
/*  63 */   private JButton bAceptar = null;
/*  64 */   private JButton bCancelar = null;
/*  65 */   private JPanel jPanel = null;
/*     */ 
/*  67 */   private String titulo = new StringBuilder().append(Mensajes.getString("OperaTerceros")).append(" - ").toString();
/*  68 */   private List<String> listado = new ArrayList();
/*  69 */   private boolean listar = false;
/*     */   private ResultSet res;
/*     */ 
/*     */   public OperacionesTerceros()
/*     */     throws HeadlessException
/*     */   {
/*  75 */     initialize();
/*     */   }
/*     */ 
/*     */   public OperacionesTerceros(Frame arg0, boolean arg1) throws HeadlessException
/*     */   {
/*  80 */     super(arg0, arg1);
/*  81 */     initialize();
/*     */   }
/*     */ 
/*     */   public OperacionesTerceros(Frame arg0, String arg1, boolean arg2) throws HeadlessException
/*     */   {
/*  86 */     super(arg0, arg1, arg2);
/*  87 */     initialize();
/*     */   }
/*     */ 
/*     */   private void initialize()
/*     */   {
/*  96 */     setSize(300, 200);
/*  97 */     setTitle(Mensajes.getString("OperaTerceros"));
/*  98 */     setContentPane(getJContentPane());
/*  99 */     this.grupo.add(this.bCompras);
/* 100 */     this.grupo.add(this.bVentas);
/* 101 */     this.grupo.add(this.bEfectivo);
/*     */   }
/*     */ 
/*     */   private JPanel getJContentPane()
/*     */   {
/* 110 */     if (this.jContentPane == null) {
/* 111 */       GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
/* 112 */       gridBagConstraints5.gridx = 0;
/* 113 */       gridBagConstraints5.gridwidth = 2;
/* 114 */       gridBagConstraints5.insets = new Insets(0, 0, 5, 0);
/* 115 */       gridBagConstraints5.gridy = 0;
/* 116 */       GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
/* 117 */       gridBagConstraints4.gridx = 1;
/* 118 */       gridBagConstraints4.insets = new Insets(5, 5, 0, 0);
/* 119 */       gridBagConstraints4.gridy = 3;
/* 120 */       GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
/* 121 */       gridBagConstraints3.gridx = 0;
/* 122 */       gridBagConstraints3.insets = new Insets(5, 0, 0, 5);
/* 123 */       gridBagConstraints3.gridy = 3;
/* 124 */       GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
/* 125 */       gridBagConstraints2.fill = 2;
/* 126 */       gridBagConstraints2.gridy = 2;
/* 127 */       gridBagConstraints2.weightx = 1.0D;
/* 128 */       gridBagConstraints2.insets = new Insets(0, 7, 5, 10);
/* 129 */       gridBagConstraints2.gridx = 1;
/* 130 */       GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
/* 131 */       gridBagConstraints11.gridx = 0;
/* 132 */       gridBagConstraints11.insets = new Insets(0, 5, 10, 5);
/* 133 */       gridBagConstraints11.gridy = 2;
/* 134 */       this.jLabel = new JLabel();
/* 135 */       this.jLabel.setText(Mensajes.getString("ImporteMinimo"));
/* 136 */       this.jContentPane = new JPanel();
/* 137 */       this.jContentPane.setLayout(new GridBagLayout());
/* 138 */       this.jContentPane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(1), BorderFactory.createEmptyBorder(5, 5, 5, 5))));
/*     */ 
/* 141 */       this.jContentPane.add(this.jLabel, gridBagConstraints11);
/* 142 */       this.jContentPane.add(getCImporte(), gridBagConstraints2);
/* 143 */       this.jContentPane.add(getBAceptar(), gridBagConstraints3);
/* 144 */       this.jContentPane.add(getBCancelar(), gridBagConstraints4);
/* 145 */       this.jContentPane.add(getJPanel(), gridBagConstraints5);
/*     */     }
/* 147 */     return this.jContentPane;
/*     */   }
/*     */ 
/*     */   private JRadioButton getBCompras()
/*     */   {
/* 156 */     if (this.bCompras == null) {
/* 157 */       this.bCompras = new JRadioButton();
/* 158 */       this.bCompras.setText(Mensajes.getString("compras"));
/* 159 */       this.bCompras.setSelected(true);
/*     */     }
/* 161 */     return this.bCompras;
/*     */   }
/*     */ 
/*     */   private JRadioButton getBVentas()
/*     */   {
/* 170 */     if (this.bVentas == null) {
/* 171 */       this.bVentas = new JRadioButton();
/* 172 */       this.bVentas.setText(Mensajes.getString("ventas"));
/*     */     }
/* 174 */     return this.bVentas;
/*     */   }
/*     */ 
/*     */   private JRadioButton getBEfectivo() {
/* 178 */     if (this.bEfectivo == null) {
/* 179 */       this.bEfectivo = new JRadioButton();
/* 180 */       this.bEfectivo.setText(Mensajes.getString("efectivo"));
/*     */     }
/* 182 */     return this.bEfectivo;
/*     */   }
/*     */ 
/*     */   private JTextField getCImporte()
/*     */   {
/* 191 */     if (this.cImporte == null) {
/* 192 */       this.cImporte = new JTextField();
/* 193 */       this.cImporte.setDocument(new DocNumeros());
/*     */     }
/* 195 */     return this.cImporte;
/*     */   }
/*     */ 
/*     */   private JButton getBAceptar()
/*     */   {
/* 204 */     if (this.bAceptar == null) {
/* 205 */       this.bAceptar = new JButton();
/* 206 */       this.bAceptar.setHorizontalTextPosition(2);
/* 207 */       this.bAceptar.setText(Mensajes.getString("aceptar"));
/* 208 */       this.bAceptar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/ok.png")));
/* 209 */       this.bAceptar.addActionListener(new ActionListener() {
/*     */         public void actionPerformed(ActionEvent e) {
/* 211 */           double importeMin = 0.0D;
/* 212 */           if (!OperacionesTerceros.this.cImporte.getText().equals(""))
/*     */             try {
/* 214 */               importeMin = Double.parseDouble(OperacionesTerceros.this.cImporte.getText());
/*     */             }
/*     */             catch (NumberFormatException eN) {
/* 217 */               eN.printStackTrace();
/*     */             }
/* 219 */           if (OperacionesTerceros.this.crearListado(importeMin)) OperacionesTerceros.this.listar = true;
/* 220 */           OperacionesTerceros.this.dispose();
/*     */         }
/*     */       });
/*     */     }
/* 224 */     return this.bAceptar;
/*     */   }
/*     */ 
/*     */   private JButton getBCancelar()
/*     */   {
/* 233 */     if (this.bCancelar == null) {
/* 234 */       this.bCancelar = new JButton();
/* 235 */       this.bCancelar.setHorizontalTextPosition(2);
/* 236 */       this.bCancelar.setText(Mensajes.getString("cancelar"));
/* 237 */       this.bCancelar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/cancel.png")));
/* 238 */       this.bCancelar.addActionListener(new ActionListener() {
/*     */         public void actionPerformed(ActionEvent e) {
/* 240 */           OperacionesTerceros.this.dispose();
/*     */         }
/*     */       });
/*     */     }
/* 244 */     return this.bCancelar;
/*     */   }
/*     */ 
/*     */   private boolean crearListado(double importeMin)
/*     */   {
/* 253 */     if (this.bEfectivo.isSelected())
/* 254 */       return listadoEfectivo(importeMin);
/* 255 */     String EOL = FinLinea.get();
/* 256 */     String tabla = this.bVentas.isSelected() ? "emit" : "reci";
/* 257 */     tabla = new StringBuilder().append(tabla).append(Inicio.p.getEjercicio()).toString();
/*     */ 
/* 259 */     StringBuilder sb = new StringBuilder();
/* 260 */     Formatter formater = new Formatter(sb);
/*     */     try
/*     */     {
/* 263 */       this.res = Inicio.getCEmpresa().getRes(new StringBuilder().append("SELECT COUNT(cuenta) FROM (SELECT cuenta,sum(total) AS suma FROM ").append(tabla).append(" GROUP BY cuenta HAVING suma >=").append(importeMin).append(") AS importes").toString());
/*     */ 
/* 266 */       if (!this.res.next()) return false;
/* 267 */       this.res = Inicio.getCEmpresa().getRes(new StringBuilder().append("SELECT s.nombre,t.NIF,t.direccion,t.CP,t.localidad,t.provincia,SUM(f.total) AS suma FROM ").append(tabla).append(" AS f JOIN scta").append(Inicio.p.getEjercicio()).append(" AS s ON f.cuenta=s.codigo").append(" JOIN Terceros AS t ON s.codigo=t.codigo").append(" GROUP BY f.cuenta HAVING suma >=").append(importeMin).append(" ORDER BY s.nombre").toString());
/*     */ 
/* 272 */       while (this.res.next()) {
/* 273 */         String nombre = this.res.getString(1);
/* 274 */         String localidad = this.res.getString(5);
/* 275 */         String provincia = this.res.getString(6);
/* 276 */         if (localidad == null) localidad = "";
/* 277 */         if (provincia == null) provincia = "";
/* 278 */         if (nombre.length() > 50) nombre = nombre.substring(0, 50);
/* 279 */         formater.format(new StringBuilder().append("%-50s %s: %s").append(EOL).append("%s").append(EOL).append("%-8s - %s").toString(), new Object[] { nombre, Mensajes.getString("NIF"), this.res.getString(2), this.res.getString(3), this.res.getString(4), localidad });
/*     */ 
/* 282 */         if (!localidad.equals(provincia))
/* 283 */           formater.format(" (%s)", new Object[] { provincia });
/* 284 */         formater.format(new StringBuilder().append(EOL).append("%s: %,15.2f %s").append(EOL).append(EOL).toString(), new Object[] { Mensajes.getString("importe"), Double.valueOf(this.res.getDouble(7)), Mensajes.getString("moneda") });
/*     */       }
/*     */ 
/* 287 */       formater.format(new StringBuilder().append(EOL).append(EOL).append("Operaciones individuales").append(EOL).append(EOL).toString(), new Object[0]);
/*     */ 
/* 291 */       this.res = Inicio.getCEmpresa().getRes(new StringBuilder().append("SELECT DATE_FORMAT(f.fecha,'%d-%m-%Y'),f.numero,s.nombre,t.NIF,f.total FROM ").append(tabla).append(" AS f JOIN scta").append(Inicio.p.getEjercicio()).append(" AS s ON f.cuenta=s.codigo").append(" JOIN Terceros AS t ON s.codigo=t.codigo").append(" WHERE total >=").append(importeMin).append(" ORDER BY s.nombre").toString());
/*     */ 
/* 296 */       while (this.res.next()) {
/* 297 */         String nombre = this.res.getString(3);
/* 298 */         if (nombre.length() > 50) nombre = nombre.substring(0, 50);
/* 299 */         formater.format(new StringBuilder().append("%s N.factura: %s").append(EOL).toString(), new Object[] { this.res.getString(1), this.res.getString(2) });
/* 300 */         formater.format(new StringBuilder().append("%-50s %s").append(EOL).toString(), new Object[] { nombre, this.res.getString(4) });
/* 301 */         formater.format(new StringBuilder().append("%s: %,15.2f %s").append(EOL).append(EOL).toString(), new Object[] { Mensajes.getString("importe"), Double.valueOf(this.res.getDouble(5)), Mensajes.getString("moneda") });
/*     */       }
/*     */ 
/* 304 */       this.listado.add(sb.toString());
/* 305 */       if (this.bVentas.isSelected()) this.titulo = new StringBuilder().append(this.titulo).append(Mensajes.getString("ventas")).toString(); else
/* 306 */         this.titulo = new StringBuilder().append(this.titulo).append(Mensajes.getString("compras")).toString();
/*     */     }
/*     */     catch (SQLException e) {
/* 309 */       e.printStackTrace();
/* 310 */       return false;
/*     */     }
/* 312 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean listadoEfectivo(double importeMin) {
/*     */     try {
/* 317 */       ArrayList listaAstos = new ArrayList();
/* 318 */       this.res = Inicio.getCEmpresa().getRes(new StringBuilder().append("SELECT id_asiento FROM apte").append(Inicio.p.getEjercicio()).append(" WHERE cuenta BETWEEN 57000000 AND 57199999 AND importe >= ").append(importeMin).append(" AND DH = 'D'").toString());
/*     */ 
/* 321 */       while (this.res.next())
/* 322 */         listaAstos.add(Integer.valueOf(this.res.getInt(1)));
/* 323 */       int numeroCargos = listaAstos.size();
/* 324 */       this.res = Inicio.getCEmpresa().getRes(new StringBuilder().append("SELECT id_asiento FROM apte").append(Inicio.p.getEjercicio()).append(" WHERE cuenta BETWEEN 57000000 AND 57199999 AND importe >= ").append(importeMin).append(" AND DH = 'H'").toString());
/*     */ 
/* 327 */       while (this.res.next())
/* 328 */         listaAstos.add(Integer.valueOf(this.res.getInt(1)));
/* 329 */       if (listaAstos.size() > 0) {
/* 330 */         ArrayList elemento = new ArrayList();
/* 331 */         int x = 0; int y = 0;
/* 332 */         for (Iterator i$ = listaAstos.iterator(); i$.hasNext(); ) { int asto = ((Integer)i$.next()).intValue();
/* 333 */           this.res = Inicio.getCEmpresa().getRes(new StringBuilder().append("SELECT a.fecha,b.cuenta,b.concepto,b.importe FROM asto").append(Inicio.p.getEjercicio()).append(" a JOIN apte").append(Inicio.p.getEjercicio()).append(" b ON a.id_asiento = b.id_asiento").append(" WHERE a.id_asiento = ").append(asto).append(" AND a.marca NOT LIKE 'A'").append(" AND a.marca NOT LIKE 'C'").toString());
/*     */ 
/* 339 */           while (this.res.next()) {
/* 340 */             int cuenta = this.res.getInt(2);
/* 341 */             if ((cuenta >= 40000000) && (cuenta < 45000000)) {
/* 342 */               elemento.add(new objeto(this.res.getString(1), this.res.getString(3), cuenta, this.res.getDouble(4)));
/* 343 */               y++;
/*     */             }
/*     */           }
/* 346 */           if (x++ == numeroCargos - 1) numeroCargos = y;
/*     */         }
/* 348 */         String EOL = FinLinea.get();
/* 349 */         StringBuilder sb = new StringBuilder();
/* 350 */         Formatter formater = new Formatter(sb);
/* 351 */         x = 0;
/* 352 */         if (numeroCargos > 0) formater.format(new StringBuilder().append("   %s").append(EOL).append(EOL).toString(), new Object[] { Mensajes.getString("cobrado") });
/* 353 */         for (objeto cuenta :(List<objeto>)  elemento)
/*     */         {
/* 355 */           if (x++ == numeroCargos) formater.format(new StringBuilder().append("   %s").append(EOL).append(EOL).toString(), new Object[] { Mensajes.getString("pagado") });
/* 356 */           this.res = Inicio.getCEmpresa().getRes(new StringBuilder().append("SELECT s.nombre,t.NIF,t.direccion,t.CP,t.localidad,t.provincia FROM scta").append(Inicio.p.getEjercicio()).append(" AS s").append(" JOIN Terceros AS t ON s.codigo=t.codigo").append(" WHERE s.codigo = ").append(cuenta.getCuenta()).toString());
/*     */ 
/* 360 */           while (this.res.next()) {
/* 361 */             String nombre = this.res.getString(1);
/* 362 */             String localidad = this.res.getString(5);
/* 363 */             String provincia = this.res.getString(6);
/* 364 */             if (localidad == null) localidad = "";
/* 365 */             if (provincia == null) provincia = "";
/* 366 */             if (nombre.length() > 50) nombre = nombre.substring(0, 50);
/* 367 */             formater.format(new StringBuilder().append("%-50s %s: %s").append(EOL).append("%s").append(EOL).append("%-8s - %s").toString(), new Object[] { nombre, Mensajes.getString("NIF"), this.res.getString(2), this.res.getString(3), this.res.getString(4), localidad });
/*     */ 
/* 370 */             if (!localidad.equals(provincia))
/* 371 */               formater.format(" (%s)", new Object[] { provincia });
/* 372 */             formater.format(new StringBuilder().append(EOL).append("%s:").toString(), new Object[] { Mensajes.getString("operacion") });
/* 373 */             formater.format(new StringBuilder().append(EOL).append("%s: %-30s %,15.2f %s").append(EOL).append(EOL).toString(), new Object[] { cuenta.getFecha(), cuenta.getConcepto(), Double.valueOf(cuenta.getImporte()), Mensajes.getString("moneda") });
/*     */           }
/*     */         }
/*     */ 
/* 377 */         this.listado.add(sb.toString());
/*     */       }
/*     */     }
/*     */     catch (SQLException e) {
/* 381 */       e.printStackTrace();
/* 382 */       return false;
/*     */     }
/* 384 */     DecimalFormat formato = new DecimalFormat("#,###,##0.00", new DecimalFormatSymbols());
/* 385 */     this.titulo = new StringBuilder().append(this.titulo).append(Mensajes.getString("operaEfectivo")).append(" ").append(formato.format(importeMin)).append(" ").append(Mensajes.getString("moneda")).toString();
/* 386 */     return true;
/*     */   }
/*     */ 
/*     */   public String getTitulo()
/*     */   {
/* 421 */     return this.titulo;
/*     */   }
/*     */ 
/*     */   public boolean isListar()
/*     */   {
/* 428 */     return this.listar;
/*     */   }
/*     */ 
/*     */   public List<String> getListado()
/*     */   {
/* 435 */     return this.listado;
/*     */   }
/*     */ 
/*     */   private JPanel getJPanel()
/*     */   {
/* 444 */     if (this.jPanel == null) {
/* 445 */       GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
/* 446 */       gridBagConstraints2.insets = new Insets(5, 5, 5, 5);
/* 447 */       gridBagConstraints2.gridx = 0;
/* 448 */       gridBagConstraints2.gridy = 2;
/* 449 */       gridBagConstraints2.anchor = 17;
/* 450 */       gridBagConstraints2.gridwidth = 1;
/* 451 */       GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
/* 452 */       gridBagConstraints1.insets = new Insets(5, 5, 0, 5);
/* 453 */       gridBagConstraints1.gridx = 0;
/* 454 */       gridBagConstraints1.gridy = 1;
/* 455 */       gridBagConstraints1.anchor = 17;
/* 456 */       gridBagConstraints1.gridwidth = 1;
/* 457 */       GridBagConstraints gridBagConstraints = new GridBagConstraints();
/* 458 */       gridBagConstraints.insets = new Insets(5, 5, 0, 5);
/* 459 */       gridBagConstraints.gridx = 0;
/* 460 */       gridBagConstraints.gridy = 0;
/* 461 */       gridBagConstraints.anchor = 17;
/* 462 */       gridBagConstraints.gridwidth = 1;
/* 463 */       this.jPanel = new JPanel();
/* 464 */       this.jPanel.setLayout(new GridBagLayout());
/* 465 */       this.jPanel.setBorder(BorderFactory.createEtchedBorder(1));
/* 466 */       this.jPanel.add(getBCompras(), gridBagConstraints);
/* 467 */       this.jPanel.add(getBVentas(), gridBagConstraints1);
/* 468 */       this.jPanel.add(getBEfectivo(), gridBagConstraints2);
/*     */     }
/* 470 */     return this.jPanel;
/*     */   }
/*     */ 
/*     */   protected static ImageIcon createImageIcon(String path) {
/* 474 */     URL imgURL = OperacionesTerceros.class.getResource(path);
/* 475 */     if (imgURL != null) {
/* 476 */       return new ImageIcon(imgURL);
/*     */     }
/* 478 */     System.err.println(new StringBuilder().append("Couldn't find file: ").append(path).toString());
/* 479 */     return null;
/*     */   }
/*     */ 
/*     */   private class objeto
/*     */   {
/*     */     private String fecha;
/*     */     private String concepto;
/*     */     private int cuenta;
/*     */     private double importe;
/*     */ 
/*     */     public objeto(String fecha, String concepto, int cuenta, double importe)
/*     */     {
/* 395 */       this.fecha = fecha;
/* 396 */       this.concepto = concepto;
/* 397 */       this.cuenta = cuenta;
/* 398 */       this.importe = importe;
/*     */     }
/*     */     public String getFecha() {
/* 401 */       return this.fecha;
/*     */     }
/*     */     public String getConcepto() {
/* 404 */       return this.concepto;
/*     */     }
/*     */     public int getCuenta() {
/* 407 */       return this.cuenta;
/*     */     }
/*     */     public double getImporte() {
/* 410 */       return this.importe;
/*     */     }
/*     */     public String toString() {
/* 413 */       return this.fecha + " - " + this.concepto + " - " + Integer.toString(this.cuenta) + " - " + Double.toString(this.importe);
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.listados.OperacionesTerceros
 * JD-Core Version:    0.6.2
 */