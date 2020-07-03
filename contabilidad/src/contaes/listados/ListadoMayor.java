/*     */ package contaes.listados;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.Puente;
/*     */ import contaes.calendario.ICalendarTextField;
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
/*     */ public class ListadoMayor extends JDialog
/*     */   implements ActionListener
/*     */ {
/*     */   private static final String ACEPTAR = "aceptar";
/*     */   private static final String CANCELAR = "cancelar";
/*  56 */   String fecha = "";
/*  57 */   String fechaF = "";
/*  58 */   boolean listar = false;
/*     */   int cuenta;
/*  60 */   List<String> listado = new ArrayList();
/*  61 */   JPanel panel1 = new JPanel();
/*  62 */   GridBagLayout gbLayout = new GridBagLayout();
/*  63 */   JLabel etiq1 = new JLabel();
/*  64 */   JLabel etiq2 = new JLabel();
/*  65 */   ICalendarTextField campoFecha = new ICalendarTextField();
/*  66 */   ICalendarTextField campoFechaF = new ICalendarTextField();
/*  67 */   JButton aceptar = new JButton();
/*  68 */   JButton cancelar = new JButton();
/*  69 */   JCheckBox orden = new JCheckBox();
/*  70 */   ImageIcon iconoAceptar = createImageIcon("/contaes/iconos/ok.png");
/*  71 */   ImageIcon iconoCancelar = createImageIcon("/contaes/iconos/cancel.png");
/*     */ 
/*     */   public ListadoMayor(Frame owner, String title, boolean modal, int cta) {
/*  74 */     super(owner, title, modal);
/*  75 */     this.cuenta = cta;
/*     */     try {
/*  77 */       setDefaultCloseOperation(2);
/*  78 */       initialize();
/*  79 */       pack();
/*     */     } catch (Exception exception) {
/*  81 */       exception.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public ListadoMayor(int cta) {
/*  86 */     this(new Frame(), Mensajes.getString("listMayor"), true, cta);
/*     */   }
/*     */ 
/*     */   private void initialize() throws Exception {
/*  90 */     this.etiq1.setText(Mensajes.getString("fechaIni"));
/*  91 */     this.etiq2.setText(Mensajes.getString("fechaFin"));
/*  92 */     this.aceptar.setText(Mensajes.getString("aceptar"));
/*  93 */     this.aceptar.setIcon(this.iconoAceptar);
/*  94 */     this.aceptar.setVerticalTextPosition(0);
/*  95 */     this.aceptar.setHorizontalTextPosition(2);
/*  96 */     this.aceptar.setActionCommand("aceptar");
/*  97 */     this.aceptar.addActionListener(this);
/*  98 */     this.cancelar.setText(Mensajes.getString("cancelar"));
/*  99 */     this.cancelar.setIcon(this.iconoCancelar);
/* 100 */     this.cancelar.setVerticalTextPosition(0);
/* 101 */     this.cancelar.setHorizontalTextPosition(2);
/* 102 */     this.cancelar.setActionCommand("cancelar");
/* 103 */     this.cancelar.addActionListener(this);
/* 104 */     this.orden.setText(Mensajes.getString("ordenFecha"));
/* 105 */     this.orden.setSelected(false);
/* 106 */     this.orden.setToolTipText(Mensajes.getString("ordenfechatt"));
/* 107 */     this.campoFecha.setMinimumSize(new Dimension(100, 26));
/* 108 */     this.campoFecha.setToolTipText(Mensajes.getString("formatoFecha"));
/* 109 */     this.campoFecha.setComponente(this.campoFechaF);
/* 110 */     this.campoFechaF.setMinimumSize(new Dimension(100, 26));
/* 111 */     this.campoFechaF.setToolTipText(Mensajes.getString("formatoFecha"));
/* 112 */     this.campoFechaF.setComponente(this.orden);
/*     */ 
/* 114 */     this.panel1.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(1), BorderFactory.createEmptyBorder(5, 5, 5, 5))));
/*     */ 
/* 117 */     this.panel1.setLayout(this.gbLayout);
/*     */ 
/* 119 */     GridBagConstraints cons = new GridBagConstraints();
/* 120 */     cons.insets.bottom = 7;
/* 121 */     cons.insets.top = 7;
/* 122 */     cons.insets.left = 7;
/* 123 */     cons.insets.right = 7;
/*     */ 
/* 125 */     cons.gridy = 0;
/* 126 */     cons.gridx = 0;
/* 127 */     this.gbLayout.setConstraints(this.etiq1, cons);
/* 128 */     this.panel1.add(this.etiq1);
/*     */ 
/* 130 */     cons.gridx = 1;
/* 131 */     this.gbLayout.setConstraints(this.etiq2, cons);
/* 132 */     this.panel1.add(this.etiq2);
/*     */ 
/* 135 */     cons.fill = 2;
/* 136 */     cons.gridy = 1;
/* 137 */     cons.gridx = 0;
/* 138 */     this.gbLayout.setConstraints(this.campoFecha, cons);
/* 139 */     this.panel1.add(this.campoFecha);
/*     */ 
/* 141 */     cons.gridx = 1;
/* 142 */     this.gbLayout.setConstraints(this.campoFechaF, cons);
/* 143 */     this.panel1.add(this.campoFechaF);
/*     */ 
/* 146 */     cons.fill = 0;
/* 147 */     cons.anchor = 10;
/* 148 */     cons.gridy = 3;
/* 149 */     cons.gridx = 0;
/* 150 */     this.gbLayout.setConstraints(this.aceptar, cons);
/* 151 */     this.panel1.add(this.aceptar);
/*     */ 
/* 153 */     cons.gridx = 1;
/* 154 */     this.gbLayout.setConstraints(this.cancelar, cons);
/* 155 */     this.panel1.add(this.cancelar);
/*     */ 
/* 157 */     cons.gridy = 2;
/* 158 */     cons.gridx = 0;
/* 159 */     cons.gridwidth = 2;
/* 160 */     this.gbLayout.setConstraints(this.orden, cons);
/* 161 */     this.panel1.add(this.orden);
/*     */ 
/* 163 */     getContentPane().add(this.panel1);
/*     */   }
/*     */ 
/*     */   public void actionPerformed(ActionEvent e) {
/* 167 */     String cmd = e.getActionCommand();
/*     */ 
/* 169 */     if ("aceptar".equals(cmd)) {
/* 170 */       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/* 171 */       Date fecha_d = this.campoFecha.getDate();
/* 172 */       this.fecha = sdf.format(fecha_d);
/* 173 */       fecha_d = this.campoFechaF.getDate();
/* 174 */       this.fechaF = sdf.format(fecha_d);
/*     */ 
/* 176 */       hacerListado(this.fecha, this.fechaF, this.cuenta);
/* 177 */       dispose();
/* 178 */     } else if ("cancelar".equals(cmd)) {
/* 179 */       this.listar = false;
/* 180 */       dispose();
/*     */     }
/*     */   }
/*     */ 
/*     */   public List<String> textoListado() {
/* 185 */     return this.listado;
/*     */   }
/*     */ 
/*     */   public boolean Listar() {
/* 189 */     return this.listar;
/*     */   }
/*     */ 
/*     */   protected void hacerListado(String fechaI, String fechaF, int cuenta) {
/* 193 */     String EOL = FinLinea.get();
/* 194 */     String tablaApte = new StringBuilder().append("apte").append(Inicio.p.getEjercicio()).toString();
/* 195 */     String tablaAsto = new StringBuilder().append("asto").append(Inicio.p.getEjercicio()).toString();
/* 196 */     String Orden = " ORDER BY ast.numero";
/* 197 */     if (this.orden.isSelected()) {
/* 198 */       Orden = " ORDER BY ast.fecha,ast.numero";
/*     */     }
/*     */ 
/* 201 */     StringBuilder sb = new StringBuilder();
/* 202 */     Formatter formater = new Formatter(sb);
/*     */     try {
/* 204 */       formater.format(new StringBuilder().append("%4s %-11s%-12s%-20s %-2s%s%s   %-13s %s").append(EOL).toString(), new Object[] { Mensajes.getString("asto"), Mensajes.getString("fecha"), Mensajes.getString("documento"), Mensajes.getString("concepto"), Mensajes.getString("marcaA"), Mensajes.getString("debeA"), Mensajes.getString("haberA"), Mensajes.getString("importe"), Mensajes.getString("acumulado") });
/*     */ 
/* 210 */       ResultSet res = Inicio.getCEmpresa().getRes(new StringBuilder().append("SELECT ast.numero,ast.fecha,ast.documento,ap.concepto,ast.marca,ap.DH,ap.importe FROM ").append(tablaApte).append(" ap JOIN ").append(tablaAsto).append(" ast ON ap.id_asiento=ast.id_asiento").append(" WHERE ap.cuenta=").append(cuenta).append(" AND ast.fecha BETWEEN '").append(fechaI).append("' AND '").append(fechaF).append("'").append(Orden).toString());
/*     */ 
/* 214 */       int mesAct = 24;
/* 215 */       double acumulado = 0.0D; double acMesAntT = 0.0D;
/* 216 */       while (res.next()) {
/* 217 */         String numero = res.getString(1);
/* 218 */         double importeApunte = res.getDouble(7);
/* 219 */         String DoH = res.getString(6);
/*     */ 
/* 221 */         String cadFecha = res.getString(2);
/* 222 */         int mesAnt = mesAct;
/* 223 */         mesAct = Integer.parseInt(cadFecha.substring(5, 7));
/* 224 */         if (mesAnt < mesAct)
/*     */         {
/* 226 */           formater.format(new StringBuilder().append("%51s: %,13.2f").append(EOL).append(EOL).toString(), new Object[] { Mensajes.getString("totalMes"), Double.valueOf(acumulado - acMesAntT) });
/* 227 */           acMesAntT = acumulado;
/*     */         }
/* 229 */         if (DoH.equals("H"))
/* 230 */           acumulado -= importeApunte;
/*     */         else {
/* 232 */           acumulado += importeApunte;
/*     */         }
/* 234 */         cadFecha = new StringBuilder().append(cadFecha.substring(8)).append("-").append(cadFecha.substring(5, 7)).append("-").append(cadFecha.substring(0, 4)).toString();
/* 235 */         formater.format(new StringBuilder().append("%4s %s %-12.12s%-20.20s %-2s%-2s%,13.2f%,13.2f").append(EOL).toString(), new Object[] { numero, cadFecha, res.getString(3), res.getString(4), res.getString(5), DoH, Double.valueOf(importeApunte), Double.valueOf(acumulado) });
/*     */       }
/*     */ 
/* 239 */       formater.format(new StringBuilder().append(EOL).append("%51s: %,13.2f").append(EOL).toString(), new Object[] { Mensajes.getString("totalMes"), Double.valueOf(acumulado - acMesAntT) });
/* 240 */       formater.format(new StringBuilder().append("%51s: %,13.2f").append(EOL).toString(), new Object[] { Mensajes.getString("totalAcum"), Double.valueOf(acumulado) });
/* 241 */       this.listado.add(sb.toString());
/* 242 */       this.listar = true;
/*     */     } catch (SQLException exc) {
/* 244 */       exc.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   protected static ImageIcon createImageIcon(String path) {
/* 249 */     URL imgURL = ListadoMayor.class.getResource(path);
/* 250 */     if (imgURL != null) {
/* 251 */       return new ImageIcon(imgURL);
/*     */     }
/* 253 */     System.err.println(new StringBuilder().append("Couldn't find file: ").append(path).toString());
/* 254 */     return null;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.listados.ListadoMayor
 * JD-Core Version:    0.6.2
 */