/*     */ package contaes.listados;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.Puente;
/*     */ import contaes.manejoDatos.ManejoTiposIVA;
/*     */ import contaes.manejoDatos.TipoIVA;
/*     */ import contaes.manejoDatos.auxiliar.FinLinea;
/*     */ import contaes.manejoDatos.auxiliar.ImportesCuentaEntreFechas;
/*     */ import contaes.manejoDatos.auxiliar.MySQLConection;
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.Frame;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.HeadlessException;
/*     */ import java.awt.Insets;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Formatter;
/*     */ import java.util.List;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ public class LiquidacionFiscalIVA extends JDialog
/*     */ {
/*  53 */   private JPanel jContentPane = null;
/*  54 */   private JLabel jLabel = null;
/*  55 */   private JComboBox trimestre = null;
/*  56 */   private JButton bAceptar = null;
/*  57 */   private JButton bCancelar = null;
/*     */   private int seleccion;
/*  59 */   private String titulo = "";
/*  60 */   private List<String> listado = new ArrayList();
/*  61 */   private boolean listar = false;
/*     */ 
/*     */   public LiquidacionFiscalIVA() throws HeadlessException
/*     */   {
/*  65 */     initialize();
/*     */   }
/*     */ 
/*     */   public LiquidacionFiscalIVA(Frame arg0) throws HeadlessException {
/*  69 */     super(arg0);
/*  70 */     initialize();
/*     */   }
/*     */ 
/*     */   public LiquidacionFiscalIVA(Frame arg0, boolean arg1) throws HeadlessException
/*     */   {
/*  75 */     super(arg0, arg1);
/*  76 */     initialize();
/*     */   }
/*     */ 
/*     */   public LiquidacionFiscalIVA(Frame arg0, String arg1, boolean arg2) throws HeadlessException
/*     */   {
/*  81 */     super(arg0, arg1, arg2);
/*  82 */     this.titulo = arg1;
/*  83 */     initialize();
/*     */   }
/*     */ 
/*     */   private void initialize()
/*     */   {
/*  92 */     setSize(300, 200);
/*  93 */     setContentPane(getJContentPane());
/*     */   }
/*     */ 
/*     */   private JPanel getJContentPane()
/*     */   {
/* 102 */     if (this.jContentPane == null) {
/* 103 */       GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
/* 104 */       gridBagConstraints2.gridx = 1;
/* 105 */       gridBagConstraints2.insets = new Insets(10, 10, 10, 10);
/* 106 */       gridBagConstraints2.gridy = 1;
/* 107 */       GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
/* 108 */       gridBagConstraints11.gridx = 0;
/* 109 */       gridBagConstraints11.insets = new Insets(10, 10, 10, 10);
/* 110 */       gridBagConstraints11.gridy = 1;
/* 111 */       GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
/* 112 */       gridBagConstraints1.fill = 2;
/* 113 */       gridBagConstraints1.gridy = 0;
/* 114 */       gridBagConstraints1.weightx = 1.0D;
/* 115 */       gridBagConstraints1.insets = new Insets(10, 5, 5, 10);
/* 116 */       gridBagConstraints1.gridx = 1;
/* 117 */       GridBagConstraints gridBagConstraints = new GridBagConstraints();
/* 118 */       gridBagConstraints.gridx = 0;
/* 119 */       gridBagConstraints.insets = new Insets(10, 10, 5, 5);
/* 120 */       gridBagConstraints.gridy = 0;
/* 121 */       this.jLabel = new JLabel();
/* 122 */       this.jLabel.setText(Mensajes.getString("trimestre"));
/* 123 */       this.jContentPane = new JPanel();
/* 124 */       this.jContentPane.setLayout(new GridBagLayout());
/* 125 */       this.jContentPane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3), BorderFactory.createEtchedBorder(1)));
/* 126 */       this.jContentPane.add(this.jLabel, gridBagConstraints);
/* 127 */       this.jContentPane.add(getListaTrimestre(), gridBagConstraints1);
/* 128 */       this.jContentPane.add(getBAceptar(), gridBagConstraints11);
/* 129 */       this.jContentPane.add(getBCancelar(), gridBagConstraints2);
/*     */     }
/* 131 */     return this.jContentPane;
/*     */   }
/*     */ 
/*     */   private JComboBox getListaTrimestre()
/*     */   {
/* 140 */     if (this.trimestre == null) {
/* 141 */       String[] trimestres = { Mensajes.getString("primero"), Mensajes.getString("segundo"), Mensajes.getString("tercero"), Mensajes.getString("cuarto") };
/* 142 */       this.trimestre = new JComboBox(trimestres);
/*     */     }
/* 144 */     return this.trimestre;
/*     */   }
/*     */ 
/*     */   private JButton getBAceptar()
/*     */   {
/* 153 */     if (this.bAceptar == null) {
/* 154 */       this.bAceptar = new JButton();
/* 155 */       this.bAceptar.setHorizontalTextPosition(2);
/* 156 */       this.bAceptar.setText(Mensajes.getString("aceptar"));
/* 157 */       this.bAceptar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/ok.png")));
/* 158 */       this.bAceptar.addActionListener(new ActionListener()
/*     */       {
/*     */         public void actionPerformed(ActionEvent e) {
/* 161 */           LiquidacionFiscalIVA.this.seleccion = LiquidacionFiscalIVA.this.trimestre.getSelectedIndex();
/* 162 */           if (LiquidacionFiscalIVA.this.crearListado(LiquidacionFiscalIVA.this.seleccion)) {
/* 163 */             LiquidacionFiscalIVA.this.listar = true;
/*     */           }
/* 165 */           LiquidacionFiscalIVA.this.dispose();
/*     */         }
/*     */       });
/*     */     }
/* 169 */     return this.bAceptar;
/*     */   }
/*     */ 
/*     */   private JButton getBCancelar()
/*     */   {
/* 178 */     if (this.bCancelar == null) {
/* 179 */       this.bCancelar = new JButton();
/* 180 */       this.bCancelar.setText(Mensajes.getString("cancelar"));
/* 181 */       this.bCancelar.setHorizontalTextPosition(2);
/* 182 */       this.bCancelar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/cancel.png")));
/* 183 */       this.bCancelar.addActionListener(new ActionListener()
/*     */       {
/*     */         public void actionPerformed(ActionEvent e) {
/* 186 */           LiquidacionFiscalIVA.this.dispose();
/*     */         }
/*     */       });
/*     */     }
/* 190 */     return this.bCancelar;
/*     */   }
/*     */ 
/*     */   private boolean crearListado(int trim)
/*     */   {
/* 204 */     if ((trim < 0) || (trim > 3)) {
/* 205 */       return false;
/*     */     }
/*     */ 
/* 208 */     double devengadoTotal = 0.0D;
/* 209 */     double deducible = 0.0D;
/* 210 */     double baseDeducible = 0.0D;
/* 211 */     double importaciones = 0.0D;
/* 212 */     double baseImportaciones = 0.0D;
/* 213 */     double baseIntracomunitarias = 0.0D;
/* 214 */     double intracomunitarias = 0.0D;
/* 215 */     double deducibleInv = 0.0D;
/* 216 */     double baseDeducibleInv = 0.0D;
/* 217 */     double importacionesInv = 0.0D;
/* 218 */     double baseImportacionesInv = 0.0D;
/* 219 */     double baseIntracomunitariasInv = 0.0D;
/* 220 */     double intracomunitariasInv = 0.0D;
/* 221 */     String ejercicio = Inicio.p.getEjercicio();
/* 222 */     String fechaIni = "";
/* 223 */     String fechaFin = "";
/* 224 */     switch (trim) {
/*     */     case 0:
/* 226 */       fechaIni = new StringBuilder().append(ejercicio).append("0101").toString();
/* 227 */       fechaFin = new StringBuilder().append(ejercicio).append("0331").toString();
/* 228 */       break;
/*     */     case 1:
/* 230 */       fechaIni = new StringBuilder().append(ejercicio).append("0401").toString();
/* 231 */       fechaFin = new StringBuilder().append(ejercicio).append("0630").toString();
/* 232 */       break;
/*     */     case 2:
/* 234 */       fechaIni = new StringBuilder().append(ejercicio).append("0701").toString();
/* 235 */       fechaFin = new StringBuilder().append(ejercicio).append("0930").toString();
/* 236 */       break;
/*     */     case 3:
/* 238 */       fechaIni = new StringBuilder().append(ejercicio).append("1001").toString();
/* 239 */       fechaFin = new StringBuilder().append(ejercicio).append("1231").toString();
/*     */     }
/*     */     try
/*     */     {
/* 243 */       ResultSet res = Inicio.getCEmpresa().getRes(new StringBuilder().append("SELECT SUM(base),SUM(iva) FROM reci").append(ejercicio).append(" WHERE fecha BETWEEN '").append(fechaIni).append("' AND '").append(fechaFin).append("' AND importacion = 2").toString());
/* 244 */       if (res.next()) {
/* 245 */         baseIntracomunitarias += res.getDouble(1);
/* 246 */         intracomunitarias += res.getDouble(2);
/*     */       }
/* 248 */       String EOL = FinLinea.get();
/* 249 */       StringBuilder sb = new StringBuilder();
/* 250 */       Formatter formater = new Formatter(sb);
/* 251 */       formater.format(new StringBuilder().append("%s %s").append(EOL).append(EOL).toString(), new Object[] { Mensajes.getString("IVA"), Mensajes.getString("devengado") });
/* 252 */       formater.format(new StringBuilder().append("%17s %15s %5s %13s").append(EOL).append(EOL).toString(), new Object[] { "Régimen", Mensajes.getString("BI"), Mensajes.getString("tipo"), Mensajes.getString("cuota") });
/*     */ 
/* 254 */       ArrayList listaTiposIva = new ArrayList();
/* 255 */       ManejoTiposIVA mT = new ManejoTiposIVA(Inicio.getCGeneral());
/* 256 */       listaTiposIva = mT.getTiposIVA();
/* 257 */       for (TipoIVA tipoIva :(List<TipoIVA>)  listaTiposIva) {
/* 258 */         if ((tipoIva.getIva() != 0.0D) || (tipoIva.getRe() != 0.0D)) {
/* 259 */           String tIva = new StringBuilder().append(Double.toString(tipoIva.getIva())).append("%").toString();
/* 260 */           String tRe = new StringBuilder().append(Double.toString(tipoIva.getRe())).append("%").toString();
/* 261 */           ImportesCuentaEntreFechas saldoIVA = new ImportesCuentaEntreFechas(Integer.toString(tipoIva.getCuentaRep()), fechaIni, fechaFin, "L", "R", Inicio.p.getEjercicio());
/* 262 */           ImportesCuentaEntreFechas saldoRE = new ImportesCuentaEntreFechas(Integer.toString(tipoIva.getCuentaRE()), fechaIni, fechaFin, "L", "R", Inicio.p.getEjercicio());
/* 263 */           double cuotaRG = -1.0D * saldoIVA.saldo();
/* 264 */           double cuotaRE = -1.0D * saldoRE.saldo();
/* 265 */           double baseRG = cuotaRG / (tipoIva.getIva() / 100.0D);
/* 266 */           double baseRE = cuotaRE / (tipoIva.getRe() / 100.0D);
/* 267 */           devengadoTotal += cuotaRG + cuotaRE;
/* 268 */           formater.format(new StringBuilder().append("%17s %,15.2f %5s %,13.2f").append(EOL).toString(), new Object[] { "General", Double.valueOf(baseRG), tIva, Double.valueOf(cuotaRG) });
/* 269 */           formater.format(new StringBuilder().append("%17s %,15.2f %5s %,13.2f").append(EOL).append(EOL).toString(), new Object[] { "Rec.Equiv.", Double.valueOf(baseRE), tRe, Double.valueOf(cuotaRE) });
/*     */         }
/*     */       }
/* 272 */       formater.format(new StringBuilder().append("%17s %,15.2f %5s %,13.2f").append(EOL).toString(), new Object[] { "Adqu.intracomuni.", Double.valueOf(baseIntracomunitarias), "", Double.valueOf(intracomunitarias) });
/* 273 */       formater.format(new StringBuilder().append("%37s : %,13.2f").append(EOL).append(EOL).append(EOL).toString(), new Object[] { "Total cuota devengada", Double.valueOf(devengadoTotal + intracomunitarias) });
/*     */ 
/* 280 */       res = Inicio.getCEmpresa().getRes(new StringBuilder().append("SELECT SUM(base), SUM(iva) FROM reci").append(ejercicio).append(" WHERE importacion = 2").append(" AND id_asiento IN").append(" (SELECT a.id_asiento FROM asto").append(ejercicio).append(" a LEFT JOIN apte").append(ejercicio).append(" b").append(" ON a.id_asiento=b.id_asiento WHERE a.marca = 'R' AND").append(" a.fecha BETWEEN '").append(fechaIni).append("' AND '").append(fechaFin).append("'").append(" AND b.cuenta BETWEEN 20000000 AND 29999999)").toString());
/*     */ 
/* 286 */       if (res.next()) {
/* 287 */         baseIntracomunitariasInv = res.getDouble(1);
/* 288 */         intracomunitariasInv = res.getDouble(2);
/*     */       }
/*     */ 
/* 292 */       res = Inicio.getCEmpresa().getRes(new StringBuilder().append("SELECT SUM(base),SUM(iva) FROM reci").append(ejercicio).append(" WHERE fecha BETWEEN '").append(fechaIni).append("' AND '").append(fechaFin).append("'").toString());
/* 293 */       if (res.next()) {
/* 294 */         baseDeducible += res.getDouble(1);
/* 295 */         deducible += res.getDouble(2);
/*     */       }
/*     */ 
/* 298 */       res = Inicio.getCEmpresa().getRes(new StringBuilder().append("SELECT SUM(base), SUM(iva) FROM reci").append(ejercicio).append(" WHERE importacion = 0").append(" AND id_asiento IN").append(" (SELECT a.id_asiento FROM asto").append(ejercicio).append(" a LEFT JOIN apte").append(ejercicio).append(" b").append(" ON a.id_asiento=b.id_asiento WHERE a.marca = 'R' AND").append(" a.fecha BETWEEN '").append(fechaIni).append("' AND '").append(fechaFin).append("'").append(" AND b.cuenta BETWEEN 20000000 AND 29999999)").toString());
/*     */ 
/* 304 */       if (res.next()) {
/* 305 */         baseDeducibleInv = res.getDouble(1);
/* 306 */         deducibleInv = res.getDouble(2);
/*     */       }
/*     */ 
/* 309 */       res = Inicio.getCEmpresa().getRes(new StringBuilder().append("SELECT SUM(base),SUM(iva) FROM reci").append(ejercicio).append(" WHERE fecha BETWEEN '").append(fechaIni).append("' AND '").append(fechaFin).append("' AND importacion = 1").toString());
/* 310 */       if (res.next()) {
/* 311 */         baseImportaciones += res.getDouble(1);
/* 312 */         importaciones += res.getDouble(2);
/*     */       }
/*     */ 
/* 315 */       res = Inicio.getCEmpresa().getRes(new StringBuilder().append("SELECT SUM(base), SUM(iva) FROM reci").append(ejercicio).append(" WHERE importacion = 1").append(" AND id_asiento IN").append(" (SELECT a.id_asiento FROM asto").append(ejercicio).append(" a LEFT JOIN apte").append(ejercicio).append(" b").append(" ON a.id_asiento=b.id_asiento WHERE a.marca = 'R' AND").append(" a.fecha BETWEEN '").append(fechaIni).append("' AND '").append(fechaFin).append("'").append(" AND b.cuenta BETWEEN 20000000 AND 29999999)").toString());
/*     */ 
/* 321 */       if (res.next()) {
/* 322 */         baseImportacionesInv = res.getDouble(1);
/* 323 */         importacionesInv = res.getDouble(2);
/*     */       }
/*     */ 
/* 326 */       baseDeducible -= baseImportaciones + baseIntracomunitarias;
/* 327 */       deducible -= importaciones + intracomunitarias;
/* 328 */       formater.format(new StringBuilder().append("%s %s").append(EOL).append(EOL).toString(), new Object[] { Mensajes.getString("IVA"), Mensajes.getString("deducible") });
/* 329 */       formater.format(new StringBuilder().append("%39s %13s").append(EOL).append(EOL).toString(), new Object[] { Mensajes.getString("BI"), Mensajes.getString("cuota") });
/* 330 */       formater.format(new StringBuilder().append("%25s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "Operaciones interiores", Double.valueOf(baseDeducible - baseDeducibleInv), Double.valueOf(deducible - deducibleInv) });
/* 331 */       formater.format(new StringBuilder().append("%25s %,13.2f %,13.2f").append(EOL).append(EOL).toString(), new Object[] { "Op. int. (Inversión)", Double.valueOf(baseDeducibleInv), Double.valueOf(deducibleInv) });
/* 332 */       formater.format(new StringBuilder().append("%25s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "Adqui.intracomunitarias", Double.valueOf(baseIntracomunitarias - baseIntracomunitariasInv), Double.valueOf(intracomunitarias - intracomunitariasInv) });
/* 333 */       formater.format(new StringBuilder().append("%25s %,13.2f %,13.2f").append(EOL).append(EOL).toString(), new Object[] { "Adqui.intrac. (Inversión)", Double.valueOf(baseIntracomunitariasInv), Double.valueOf(intracomunitariasInv) });
/* 334 */       formater.format(new StringBuilder().append("%25s %,13.2f %,13.2f").append(EOL).toString(), new Object[] { "Importaciones", Double.valueOf(baseImportaciones - baseImportacionesInv), Double.valueOf(importaciones - importacionesInv) });
/* 335 */       formater.format(new StringBuilder().append("%25s %,13.2f %,13.2f").append(EOL).append(EOL).toString(), new Object[] { "Importaciones (Inversión)", Double.valueOf(baseImportacionesInv), Double.valueOf(importacionesInv) });
/* 336 */       formater.format(new StringBuilder().append("%25s %,13.2f %,13.2f").append(EOL).append(EOL).append(EOL).toString(), new Object[] { "Total a deducir :", Double.valueOf(baseDeducible + baseImportaciones + baseIntracomunitarias), Double.valueOf(deducible + importaciones + intracomunitarias) });
/*     */ 
/* 343 */       double entregaIntra = 0.0D;
/* 344 */       double exportaciones = 0.0D;
/* 345 */       res = Inicio.getCEmpresa().getRes(new StringBuilder().append("SELECT SUM(a.base) FROM emit").append(ejercicio).append(" a").append(" LEFT JOIN Terceros b ON a.cuenta = b.codigo").append(" WHERE a.fecha BETWEEN '").append(fechaIni).append("' AND '").append(fechaFin).append("'").append(" AND b.pais IN (SELECT id FROM contaes.paises WHERE situacion = 1)").toString());
/*     */ 
/* 349 */       if (res.next()) {
/* 350 */         entregaIntra = res.getDouble(1);
/*     */       }
/* 352 */       res = Inicio.getCEmpresa().getRes(new StringBuilder().append("SELECT SUM(a.base) FROM emit").append(ejercicio).append(" a").append(" LEFT JOIN Terceros b ON a.cuenta = b.codigo").append(" WHERE a.fecha BETWEEN '").append(fechaIni).append("' AND '").append(fechaFin).append("'").append(" AND b.pais IN (SELECT id FROM contaes.paises WHERE situacion = 2)").toString());
/*     */ 
/* 356 */       if (res.next()) {
/* 357 */         exportaciones = res.getDouble(1);
/*     */       }
/* 359 */       formater.format(new StringBuilder().append("%25s %,13.2f").append(EOL).toString(), new Object[] { "Entr.intracomunitarias", Double.valueOf(entregaIntra) });
/* 360 */       formater.format(new StringBuilder().append("%25s %,13.2f").append(EOL).append(EOL).append(EOL).toString(), new Object[] { "Exportaciones ...", Double.valueOf(exportaciones) });
/* 361 */       formater.format("%37s  %,14.2f", new Object[] { "Resultado de la liquidación :", Double.valueOf(devengadoTotal - (deducible + importaciones)) });
/*     */ 
/* 363 */       this.listado.add(sb.toString());
/*     */     }
/*     */     catch (SQLException e) {
/* 366 */       e.printStackTrace();
/* 367 */       return false;
/*     */     }
/* 369 */     return true;
/*     */   }
/*     */ 
/*     */   public List<String> getListado()
/*     */   {
/* 376 */     return this.listado;
/*     */   }
/*     */ 
/*     */   public boolean isListar()
/*     */   {
/* 383 */     return this.listar;
/*     */   }
/*     */ 
/*     */   public String getTitulo()
/*     */   {
/* 390 */     return this.titulo;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.listados.LiquidacionFiscalIVA
 * JD-Core Version:    0.6.2
 */