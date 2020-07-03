/*     */ package contaes;
/*     */ 
/*     */ import contaes.auxiliarTablas.DerechaColorRenderer;
/*     */ import contaes.auxiliarTablas.GeneralColorRenderer;
/*     */ import contaes.auxiliarTablas.ImporteColorRenderer;
/*     */ import contaes.dialogosAuxiliares.DonacionJDialog;
/*     */ import contaes.manejoDatos.ControlCentre;
/*     */ import contaes.manejoDatos.InformeProductosTableModel;
/*     */ import contaes.manejoDatos.auxiliar.ConfiguracionBean;
/*     */ import java.awt.Color;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JInternalFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.event.InternalFrameEvent;
/*     */ import javax.swing.event.InternalFrameListener;
/*     */ import javax.swing.table.DefaultTableModel;
/*     */ import javax.swing.table.TableColumn;
/*     */ import javax.swing.table.TableColumnModel;
/*     */ import org.jdesktop.layout.GroupLayout;
/*     */ import org.jdesktop.layout.GroupLayout.ParallelGroup;
/*     */ import org.jdesktop.layout.GroupLayout.SequentialGroup;
/*     */ 
/*     */ public class ControlCentreJInternalFrame extends JInternalFrame
/*     */ {
/*     */   ControlCentre datos;
/*     */   InformeProductosTableModel modeloMas;
/*     */   InformeProductosTableModel modeloMenos;
/*     */   private JLabel acreAdmPub;
/*     */   private JLabel acreComerciales;
/*     */   private JLabel acreEntFinCP;
/*     */   private JLabel acreEntFinLP;
/*     */   private JLabel acrePersonal;
/*     */   private JLabel acreProvCP;
/*     */   private JLabel acreProvLP;
/*     */   private JLabel bancos;
/*     */   private JLabel beneficio;
/*     */   private JLabel caja;
/*     */   private JLabel cobrosHoy;
/*     */   private JLabel cobrosSiete;
/*     */   private JLabel cobrosTreinta;
/*     */   private JLabel deudAdmPub;
/*     */   private JLabel deudClientes;
/*     */   private JLabel deudInvFinCP;
/*     */   private JLabel deudInvFinLP;
/*     */   private JLabel deudPersonal;
/*     */   private JLabel disponibilidad;
/*     */   private JLabel fondosPropios;
/*     */   private JLabel gastos;
/*     */   private JLabel importe;
/*     */   private JLabel ingresos;
/*     */   private JLabel inmovilizado;
/*     */   private JButton jButton1;
/*     */   private JLabel jLabel1;
/*     */   private JLabel jLabel10;
/*     */   private JLabel jLabel11;
/*     */   private JLabel jLabel12;
/*     */   private JLabel jLabel13;
/*     */   private JLabel jLabel14;
/*     */   private JLabel jLabel15;
/*     */   private JLabel jLabel16;
/*     */   private JLabel jLabel17;
/*     */   private JLabel jLabel18;
/*     */   private JLabel jLabel19;
/*     */   private JLabel jLabel2;
/*     */   private JLabel jLabel20;
/*     */   private JLabel jLabel21;
/*     */   private JLabel jLabel22;
/*     */   private JLabel jLabel23;
/*     */   private JLabel jLabel24;
/*     */   private JLabel jLabel25;
/*     */   private JLabel jLabel26;
/*     */   private JLabel jLabel27;
/*     */   private JLabel jLabel28;
/*     */   private JLabel jLabel29;
/*     */   private JLabel jLabel3;
/*     */   private JLabel jLabel30;
/*     */   private JLabel jLabel31;
/*     */   private JLabel jLabel4;
/*     */   private JLabel jLabel5;
/*     */   private JLabel jLabel6;
/*     */   private JLabel jLabel7;
/*     */   private JLabel jLabel8;
/*     */   private JLabel jLabel9;
/*     */   private JPanel jPanel1;
/*     */   private JPanel jPanel2;
/*     */   private JPanel jPanel3;
/*     */   private JPanel jPanel4;
/*     */   private JPanel jPanel5;
/*     */   private JPanel jPanel6;
/*     */   private JPanel jPanel7;
/*     */   private JScrollPane jScrollPane1;
/*     */   private JScrollPane jScrollPane2;
/*     */   private JTable jTable1;
/*     */   private JTable jTable2;
/*     */   private JLabel pagosHoy;
/*     */   private JLabel pagosSiete;
/*     */   private JLabel pagosTreinta;
/*     */   private JLabel unidades;
/*     */ 
/*     */   public ControlCentreJInternalFrame()
/*     */   {
/*  38 */     initComponents();
/*  39 */     Rectangle bounds = readFrameBounds();
/*  40 */     if (bounds != null) {
/*  41 */       setBounds(bounds);
/*     */     }
/*  43 */     this.datos = new ControlCentre();
/*  44 */     this.modeloMas = new InformeProductosTableModel(this.datos.getMasVendidos());
/*  45 */     this.modeloMenos = new InformeProductosTableModel(this.datos.getMenosVendidos());
/*  46 */     this.jTable1.setModel(this.modeloMas);
/*  47 */     this.jTable2.setModel(this.modeloMenos);
/*  48 */     formatearTabla(this.jTable1);
/*  49 */     formatearTabla(this.jTable2);
/*  50 */     if (Inicio.p.isRegistro())
/*  51 */       colocarDatos();
/*     */   }
/*     */ 
/*     */   private void formatearTabla(JTable tabla)
/*     */   {
/*  56 */     TableColumn columna = null;
/*  57 */     int anchoTabla = 440;
/*  58 */     columna = tabla.getColumnModel().getColumn(0);
/*  59 */     columna.setPreferredWidth((int)(anchoTabla * 0.18D));
/*  60 */     columna.setCellRenderer(new GeneralColorRenderer());
/*  61 */     columna = tabla.getColumnModel().getColumn(1);
/*  62 */     columna.setPreferredWidth((int)(anchoTabla * 0.4D));
/*  63 */     columna.setCellRenderer(new GeneralColorRenderer());
/*  64 */     columna = tabla.getColumnModel().getColumn(2);
/*  65 */     columna.setPreferredWidth((int)(anchoTabla * 0.1D));
/*  66 */     columna.setCellRenderer(new DerechaColorRenderer());
/*  67 */     columna = tabla.getColumnModel().getColumn(3);
/*  68 */     columna.setPreferredWidth((int)(anchoTabla * 0.16D));
/*  69 */     columna.setCellRenderer(new ImporteColorRenderer());
/*  70 */     columna = tabla.getColumnModel().getColumn(4);
/*  71 */     columna.setPreferredWidth((int)(anchoTabla * 0.16D));
/*  72 */     columna.setCellRenderer(new ImporteColorRenderer());
/*     */   }
/*     */ 
/*     */   private void colocarDatos()
/*     */   {
/*  78 */     this.acreAdmPub.setText(String.format("%,10.2f €", new Object[] { Double.valueOf(this.datos.getAcreAdminPub()) }));
/*  79 */     this.acreComerciales.setText(String.format("%,10.2f €", new Object[] { Double.valueOf(this.datos.getAcreComerciales()) }));
/*  80 */     this.acreEntFinCP.setText(String.format("%,10.2f €", new Object[] { Double.valueOf(this.datos.getAcreEntFinCP()) }));
/*  81 */     this.acreEntFinLP.setText(String.format("%,10.2f €", new Object[] { Double.valueOf(this.datos.getAcreEntFinLP()) }));
/*  82 */     this.acrePersonal.setText(String.format("%,10.2f €", new Object[] { Double.valueOf(this.datos.getAcrePersonal()) }));
/*  83 */     this.acreProvCP.setText(String.format("%,10.2f €", new Object[] { Double.valueOf(this.datos.getAcreProvCP()) }));
/*  84 */     this.acreProvLP.setText(String.format("%,10.2f €", new Object[] { Double.valueOf(this.datos.getAcreProvLP()) }));
/*  85 */     this.bancos.setText(String.format("%,10.2f €", new Object[] { Double.valueOf(this.datos.getBancos()) }));
/*  86 */     this.beneficio.setText(String.format("%,10.2f €", new Object[] { Double.valueOf(this.datos.getIngresos() - this.datos.getGastos()) }));
/*  87 */     this.caja.setText(String.format("%,10.2f €", new Object[] { Double.valueOf(this.datos.getCaja()) }));
/*  88 */     this.cobrosHoy.setText(String.format("%,10.2f €", new Object[] { Double.valueOf(this.datos.getCobrosHoy()) }));
/*  89 */     this.cobrosSiete.setText(String.format("%,10.2f €", new Object[] { Double.valueOf(this.datos.getCobrosSiete()) }));
/*  90 */     this.cobrosTreinta.setText(String.format("%,10.2f €", new Object[] { Double.valueOf(this.datos.getCobrosTreinta()) }));
/*  91 */     this.deudAdmPub.setText(String.format("%,10.2f €", new Object[] { Double.valueOf(this.datos.getDeudAdminPub()) }));
/*  92 */     this.deudClientes.setText(String.format("%,10.2f €", new Object[] { Double.valueOf(this.datos.getDeudComerciales()) }));
/*  93 */     this.deudInvFinCP.setText(String.format("%,10.2f €", new Object[] { Double.valueOf(this.datos.getDeudInvFinCP()) }));
/*  94 */     this.deudInvFinLP.setText(String.format("%,10.2f €", new Object[] { Double.valueOf(this.datos.getDeudInvFinLP()) }));
/*  95 */     this.deudPersonal.setText(String.format("%,10.2f €", new Object[] { Double.valueOf(this.datos.getDeudPersonal()) }));
/*  96 */     this.disponibilidad.setText(String.format("%,10.2f €", new Object[] { Double.valueOf(this.datos.getCaja() + this.datos.getBancos()) }));
/*  97 */     this.gastos.setText(String.format("%,10.2f €", new Object[] { Double.valueOf(this.datos.getGastos()) }));
/*  98 */     this.importe.setText(String.format("%,10.2f €", new Object[] { Double.valueOf(this.datos.getExisImporte()) }));
/*  99 */     this.ingresos.setText(String.format("%,10.2f €", new Object[] { Double.valueOf(this.datos.getIngresos()) }));
/* 100 */     this.pagosHoy.setText(String.format("%,10.2f €", new Object[] { Double.valueOf(this.datos.getPagosHoy()) }));
/* 101 */     this.pagosSiete.setText(String.format("%,10.2f €", new Object[] { Double.valueOf(this.datos.getPagosSiete()) }));
/* 102 */     this.pagosTreinta.setText(String.format("%,10.2f €", new Object[] { Double.valueOf(this.datos.getPagosTreinta()) }));
/* 103 */     this.unidades.setText(String.format("%d", new Object[] { Integer.valueOf(this.datos.getExisNumero()) }));
/* 104 */     this.inmovilizado.setText(String.format("%,10.2f €", new Object[] { Double.valueOf(this.datos.getInmovilizado()) }));
/* 105 */     this.fondosPropios.setText(String.format("%,10.2f €", new Object[] { Double.valueOf(this.datos.getFondosPropios()) }));
/*     */   }
/*     */ 
/*     */   public void renovar() {
/* 109 */     if (Inicio.p.isRegistro()) {
/* 110 */       this.datos.calcular();
/* 111 */       colocarDatos();
/*     */     }
/*     */     else {
/* 114 */       aviso();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void aviso() {
/* 119 */     DonacionJDialog dlg = new DonacionJDialog(Inicio.frame, true, "Cuadro de mando");
/* 120 */     Inicio.frame.mostrarDialogo(dlg);
/*     */   }
/*     */ 
/*     */   private Rectangle readFrameBounds() {
/* 124 */     Rectangle bounds = null;
/* 125 */     ArrayList array = new ArrayList();
/* 126 */     ConfiguracionBean config = new ConfiguracionBean();
/* 127 */     array = config.getConfig("<cclastposition>");
/* 128 */     if (!array.isEmpty()) {
/*     */       try {
/* 130 */         bounds = new Rectangle(Integer.parseInt((String)array.get(0)), Integer.parseInt((String)array.get(1)), Integer.parseInt((String)array.get(2)), Integer.parseInt((String)array.get(3)));
/*     */       }
/*     */       catch (NumberFormatException e)
/*     */       {
/* 135 */         e.printStackTrace();
/*     */       } catch (ArrayIndexOutOfBoundsException ex) {
/* 137 */         ex.printStackTrace();
/*     */       }
/*     */     }
/* 140 */     return bounds;
/*     */   }
/*     */ 
/*     */   public void saveFrameBounds() {
/* 144 */     Rectangle dim = getBounds();
/* 145 */     ConfiguracionBean config = new ConfiguracionBean();
/* 146 */     ArrayList array = new ArrayList();
/* 147 */     array.add(Integer.toString(dim.x));
/* 148 */     array.add(Integer.toString(dim.y));
/* 149 */     array.add(Integer.toString(dim.width));
/* 150 */     array.add(Integer.toString(dim.height));
/* 151 */     config.saveConfig("<cclastposition>", array);
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/* 163 */     this.jPanel1 = new JPanel();
/* 164 */     this.jLabel1 = new JLabel();
/* 165 */     this.jLabel2 = new JLabel();
/* 166 */     this.caja = new JLabel();
/* 167 */     this.jLabel3 = new JLabel();
/* 168 */     this.bancos = new JLabel();
/* 169 */     this.disponibilidad = new JLabel();
/* 170 */     this.jPanel2 = new JPanel();
/* 171 */     this.jLabel4 = new JLabel();
/* 172 */     this.jLabel5 = new JLabel();
/* 173 */     this.jLabel6 = new JLabel();
/* 174 */     this.cobrosTreinta = new JLabel();
/* 175 */     this.cobrosSiete = new JLabel();
/* 176 */     this.cobrosHoy = new JLabel();
/* 177 */     this.pagosHoy = new JLabel();
/* 178 */     this.pagosSiete = new JLabel();
/* 179 */     this.pagosTreinta = new JLabel();
/* 180 */     this.jPanel3 = new JPanel();
/* 181 */     this.jLabel7 = new JLabel();
/* 182 */     this.jLabel8 = new JLabel();
/* 183 */     this.acreEntFinCP = new JLabel();
/* 184 */     this.jLabel9 = new JLabel();
/* 185 */     this.acreEntFinLP = new JLabel();
/* 186 */     this.jLabel10 = new JLabel();
/* 187 */     this.acreComerciales = new JLabel();
/* 188 */     this.jLabel11 = new JLabel();
/* 189 */     this.jLabel12 = new JLabel();
/* 190 */     this.acreProvCP = new JLabel();
/* 191 */     this.jLabel13 = new JLabel();
/* 192 */     this.acreProvLP = new JLabel();
/* 193 */     this.jLabel14 = new JLabel();
/* 194 */     this.acrePersonal = new JLabel();
/* 195 */     this.jLabel15 = new JLabel();
/* 196 */     this.acreAdmPub = new JLabel();
/* 197 */     this.jPanel4 = new JPanel();
/* 198 */     this.jLabel16 = new JLabel();
/* 199 */     this.jLabel17 = new JLabel();
/* 200 */     this.deudInvFinCP = new JLabel();
/* 201 */     this.jLabel18 = new JLabel();
/* 202 */     this.deudInvFinLP = new JLabel();
/* 203 */     this.jLabel19 = new JLabel();
/* 204 */     this.deudClientes = new JLabel();
/* 205 */     this.jLabel23 = new JLabel();
/* 206 */     this.deudPersonal = new JLabel();
/* 207 */     this.jLabel24 = new JLabel();
/* 208 */     this.deudAdmPub = new JLabel();
/* 209 */     this.jPanel5 = new JPanel();
/* 210 */     this.jLabel20 = new JLabel();
/* 211 */     this.beneficio = new JLabel();
/* 212 */     this.jLabel21 = new JLabel();
/* 213 */     this.ingresos = new JLabel();
/* 214 */     this.jLabel22 = new JLabel();
/* 215 */     this.gastos = new JLabel();
/* 216 */     this.jPanel6 = new JPanel();
/* 217 */     this.jLabel25 = new JLabel();
/* 218 */     this.jLabel26 = new JLabel();
/* 219 */     this.unidades = new JLabel();
/* 220 */     this.jLabel27 = new JLabel();
/* 221 */     this.importe = new JLabel();
/* 222 */     this.jLabel28 = new JLabel();
/* 223 */     this.jScrollPane1 = new JScrollPane();
/* 224 */     this.jTable1 = new JTable();
/* 225 */     this.jLabel29 = new JLabel();
/* 226 */     this.jScrollPane2 = new JScrollPane();
/* 227 */     this.jTable2 = new JTable();
/* 228 */     this.jButton1 = new JButton();
/* 229 */     this.jPanel7 = new JPanel();
/* 230 */     this.jLabel30 = new JLabel();
/* 231 */     this.jLabel31 = new JLabel();
/* 232 */     this.fondosPropios = new JLabel();
/* 233 */     this.inmovilizado = new JLabel();
/*     */ 
/* 235 */     setClosable(true);
/* 236 */     setDefaultCloseOperation(1);
/* 237 */     setIconifiable(true);
/* 238 */     setMaximizable(true);
/* 239 */     setResizable(true);
/* 240 */     setTitle("Cuadro de mando");
/* 241 */     setNormalBounds(new Rectangle(0, 0, 810, 560));
/* 242 */     setPreferredSize(new Dimension(810, 560));
/* 243 */     setSize(new Dimension(810, 560));
/* 244 */     addInternalFrameListener(new InternalFrameListener() {
/*     */       public void internalFrameOpened(InternalFrameEvent evt) {
/*     */       }
/*     */       public void internalFrameClosing(InternalFrameEvent evt) {
/* 248 */         ControlCentreJInternalFrame.this.formInternalFrameClosing(evt);
/*     */       }
/*     */ 
/*     */       public void internalFrameClosed(InternalFrameEvent evt)
/*     */       {
/*     */       }
/*     */ 
/*     */       public void internalFrameIconified(InternalFrameEvent evt)
/*     */       {
/*     */       }
/*     */ 
/*     */       public void internalFrameDeiconified(InternalFrameEvent evt)
/*     */       {
/*     */       }
/*     */ 
/*     */       public void internalFrameActivated(InternalFrameEvent evt)
/*     */       {
/*     */       }
/*     */ 
/*     */       public void internalFrameDeactivated(InternalFrameEvent evt)
/*     */       {
/*     */       }
/*     */     });
/* 262 */     this.jPanel1.setBorder(BorderFactory.createTitledBorder(null, "Disponibilidad monetaria", 0, 0, new Font("Lucida Grande", 0, 13), new Color(0, 153, 102)));
/*     */ 
/* 264 */     this.jLabel1.setText("Total");
/*     */ 
/* 266 */     this.jLabel2.setText("Caja");
/*     */ 
/* 268 */     this.caja.setText("9.999.999 €");
/*     */ 
/* 270 */     this.jLabel3.setText("Bancos");
/*     */ 
/* 272 */     this.bancos.setText("9.999.999 €");
/*     */ 
/* 274 */     this.disponibilidad.setText("9.999.999 €");
/*     */ 
/* 276 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 277 */     this.jPanel1.setLayout(jPanel1Layout);
/* 278 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().add(jPanel1Layout.createParallelGroup(2).add(jPanel1Layout.createSequentialGroup().add(8, 8, 8).add(this.jLabel2).addPreferredGap(0).add(this.caja, -1, -1, 32767)).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(this.jLabel1))).add(18, 18, 18).add(jPanel1Layout.createParallelGroup(1).add(this.disponibilidad).add(jPanel1Layout.createSequentialGroup().add(this.jLabel3).addPreferredGap(0).add(this.bancos))).addContainerGap(61, 32767)));
/*     */ 
/* 299 */     jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().add(jPanel1Layout.createParallelGroup(3).add(this.disponibilidad).add(this.jLabel1)).addPreferredGap(0).add(jPanel1Layout.createParallelGroup(3).add(this.jLabel2).add(this.caja).add(this.jLabel3).add(this.bancos))));
/*     */ 
/* 313 */     this.jPanel2.setBorder(BorderFactory.createTitledBorder(null, "Cobros y Pagos", 0, 0, new Font("Lucida Grande", 0, 13), new Color(153, 51, 255)));
/*     */ 
/* 315 */     this.jLabel4.setText("Hoy");
/*     */ 
/* 317 */     this.jLabel5.setText("Próximos 7 días");
/*     */ 
/* 319 */     this.jLabel6.setText("Próximos 30 días");
/*     */ 
/* 321 */     this.cobrosTreinta.setText("9.999.999 €");
/*     */ 
/* 323 */     this.cobrosSiete.setText("9.999.999 €");
/*     */ 
/* 325 */     this.cobrosHoy.setText("9.999.999 €");
/*     */ 
/* 327 */     this.pagosHoy.setText("9.999.999 €");
/*     */ 
/* 329 */     this.pagosSiete.setText("9.999.999 €");
/*     */ 
/* 331 */     this.pagosTreinta.setText("9.999.999 €");
/*     */ 
/* 333 */     GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
/* 334 */     this.jPanel2.setLayout(jPanel2Layout);
/* 335 */     jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(1).add(jPanel2Layout.createSequentialGroup().add(9, 9, 9).add(jPanel2Layout.createParallelGroup(1).add(this.jLabel4).add(this.jLabel5).add(this.jLabel6)).add(18, 18, 18).add(jPanel2Layout.createParallelGroup(1).add(2, this.cobrosHoy).add(2, this.cobrosSiete).add(2, this.cobrosTreinta)).add(18, 18, 18).add(jPanel2Layout.createParallelGroup(1).add(2, this.pagosHoy).add(2, this.pagosSiete).add(2, this.pagosTreinta)).addContainerGap(-1, 32767)));
/*     */ 
/* 355 */     jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(1).add(jPanel2Layout.createSequentialGroup().add(jPanel2Layout.createParallelGroup(3).add(this.jLabel4).add(this.cobrosHoy).add(this.pagosHoy)).addPreferredGap(0).add(jPanel2Layout.createParallelGroup(3).add(this.jLabel5).add(this.cobrosSiete).add(this.pagosSiete)).addPreferredGap(0).add(jPanel2Layout.createParallelGroup(3).add(this.jLabel6).add(this.cobrosTreinta).add(this.pagosTreinta))));
/*     */ 
/* 374 */     this.jPanel3.setBorder(BorderFactory.createTitledBorder(null, "Acreedores", 0, 0, new Font("Lucida Grande", 0, 13), new Color(204, 0, 0)));
/*     */ 
/* 376 */     this.jLabel7.setText("Entidades financieras");
/*     */ 
/* 378 */     this.jLabel8.setText("Corto Pl.");
/*     */ 
/* 380 */     this.acreEntFinCP.setText("9.999.999 €");
/*     */ 
/* 382 */     this.jLabel9.setText("Largo Pl.");
/*     */ 
/* 384 */     this.acreEntFinLP.setText("9.999.999 €");
/*     */ 
/* 386 */     this.jLabel10.setText("Proveedores comerciales");
/*     */ 
/* 388 */     this.acreComerciales.setText("9.999.999 €");
/*     */ 
/* 390 */     this.jLabel11.setText("Proveedores no comerciales");
/*     */ 
/* 392 */     this.jLabel12.setText("Corto Pl.");
/*     */ 
/* 394 */     this.acreProvCP.setText("9.999.999 €");
/*     */ 
/* 396 */     this.jLabel13.setText("Largo Pl.");
/*     */ 
/* 398 */     this.acreProvLP.setText("9.999.999 €");
/*     */ 
/* 400 */     this.jLabel14.setText("Personal");
/*     */ 
/* 402 */     this.acrePersonal.setText("9.999.999 €");
/*     */ 
/* 404 */     this.jLabel15.setText("Administraciones Públicas");
/*     */ 
/* 406 */     this.acreAdmPub.setText("9.999.999 €");
/*     */ 
/* 408 */     GroupLayout jPanel3Layout = new GroupLayout(this.jPanel3);
/* 409 */     this.jPanel3.setLayout(jPanel3Layout);
/* 410 */     jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(1).add(jPanel3Layout.createSequentialGroup().add(jPanel3Layout.createParallelGroup(1).add(jPanel3Layout.createSequentialGroup().add(jPanel3Layout.createParallelGroup(1).add(jPanel3Layout.createSequentialGroup().add(this.jLabel8).addPreferredGap(0).add(this.acreEntFinCP)).add(jPanel3Layout.createSequentialGroup().add(this.jLabel12).addPreferredGap(0).add(this.acreProvCP))).addPreferredGap(1).add(jPanel3Layout.createParallelGroup(1).add(this.jLabel13).add(this.jLabel9)).add(18, 18, 18).add(jPanel3Layout.createParallelGroup(1).add(2, this.acreEntFinLP).add(2, this.acreComerciales).add(2, this.acreProvLP).add(2, this.acrePersonal).add(2, this.acreAdmPub))).add(jPanel3Layout.createSequentialGroup().addContainerGap().add(jPanel3Layout.createParallelGroup(1).add(this.jLabel7).add(this.jLabel10).add(this.jLabel11).add(this.jLabel14).add(this.jLabel15)))).addContainerGap(26, 32767)));
/*     */ 
/* 445 */     jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(1).add(jPanel3Layout.createSequentialGroup().add(this.jLabel7).addPreferredGap(0).add(jPanel3Layout.createParallelGroup(3).add(this.jLabel8).add(this.acreEntFinCP).add(this.jLabel9).add(this.acreEntFinLP)).addPreferredGap(0).add(jPanel3Layout.createParallelGroup(3).add(this.jLabel10).add(this.acreComerciales)).add(6, 6, 6).add(this.jLabel11).addPreferredGap(0).add(jPanel3Layout.createParallelGroup(3).add(this.jLabel12).add(this.acreProvCP).add(this.jLabel13).add(this.acreProvLP)).addPreferredGap(0).add(jPanel3Layout.createParallelGroup(3).add(this.jLabel14).add(this.acrePersonal)).addPreferredGap(0).add(jPanel3Layout.createParallelGroup(3).add(this.jLabel15).add(this.acreAdmPub))));
/*     */ 
/* 477 */     this.jPanel4.setBorder(BorderFactory.createTitledBorder(null, "Deudores e Inversiones financieras", 0, 0, new Font("Lucida Grande", 0, 13), new Color(0, 102, 255)));
/*     */ 
/* 479 */     this.jLabel16.setText("Inversiones financieras");
/*     */ 
/* 481 */     this.jLabel17.setText("Corto Pl.");
/*     */ 
/* 483 */     this.deudInvFinCP.setText("9.999.999 €");
/*     */ 
/* 485 */     this.jLabel18.setText("Largo Pl.");
/*     */ 
/* 487 */     this.deudInvFinLP.setText("9.999.999 €");
/*     */ 
/* 489 */     this.jLabel19.setText("Clientes");
/*     */ 
/* 491 */     this.deudClientes.setText("9.999.999 €");
/*     */ 
/* 493 */     this.jLabel23.setText("Personal");
/*     */ 
/* 495 */     this.deudPersonal.setText("9.999.999 €");
/*     */ 
/* 497 */     this.jLabel24.setText("Administraciones Públicas");
/*     */ 
/* 499 */     this.deudAdmPub.setText("9.999.999 €");
/*     */ 
/* 501 */     GroupLayout jPanel4Layout = new GroupLayout(this.jPanel4);
/* 502 */     this.jPanel4.setLayout(jPanel4Layout);
/* 503 */     jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(1).add(jPanel4Layout.createSequentialGroup().add(jPanel4Layout.createParallelGroup(1).add(jPanel4Layout.createSequentialGroup().addContainerGap().add(jPanel4Layout.createParallelGroup(1).add(this.jLabel16).add(this.jLabel19).add(this.jLabel23).add(this.jLabel24))).add(jPanel4Layout.createSequentialGroup().add(this.jLabel17).addPreferredGap(0).add(this.deudInvFinCP).addPreferredGap(1).add(this.jLabel18))).addPreferredGap(1).add(jPanel4Layout.createParallelGroup(1).add(2, this.deudInvFinLP).add(2, this.deudClientes).add(2, this.deudPersonal).add(2, this.deudAdmPub)).addContainerGap(30, 32767)));
/*     */ 
/* 528 */     jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(1).add(jPanel4Layout.createSequentialGroup().add(this.jLabel16).addPreferredGap(0).add(jPanel4Layout.createParallelGroup(3).add(this.jLabel17).add(this.deudInvFinCP).add(this.jLabel18).add(this.deudInvFinLP)).addPreferredGap(0).add(jPanel4Layout.createParallelGroup(3).add(this.jLabel19).add(this.deudClientes)).addPreferredGap(0).add(jPanel4Layout.createParallelGroup(3).add(this.jLabel23).add(this.deudPersonal)).addPreferredGap(0).add(jPanel4Layout.createParallelGroup(3).add(this.jLabel24).add(this.deudAdmPub))));
/*     */ 
/* 552 */     this.jPanel5.setBorder(BorderFactory.createTitledBorder(null, "Beneficios", 0, 0, new Font("Lucida Grande", 0, 13), new Color(0, 153, 102)));
/*     */ 
/* 554 */     this.jLabel20.setText("Beneficio");
/*     */ 
/* 556 */     this.beneficio.setText("9.999.999 €");
/*     */ 
/* 558 */     this.jLabel21.setText("Ingresos");
/*     */ 
/* 560 */     this.ingresos.setText("9.999.999 €");
/*     */ 
/* 562 */     this.jLabel22.setText("Gastos");
/*     */ 
/* 564 */     this.gastos.setText("9.999.999 €");
/*     */ 
/* 566 */     GroupLayout jPanel5Layout = new GroupLayout(this.jPanel5);
/* 567 */     this.jPanel5.setLayout(jPanel5Layout);
/* 568 */     jPanel5Layout.setHorizontalGroup(jPanel5Layout.createParallelGroup(1).add(jPanel5Layout.createSequentialGroup().add(jPanel5Layout.createParallelGroup(1).add(jPanel5Layout.createSequentialGroup().add(32, 32, 32).add(this.jLabel20)).add(jPanel5Layout.createSequentialGroup().add(12, 12, 12).add(jPanel5Layout.createParallelGroup(1).add(this.jLabel21).add(this.jLabel22)))).addPreferredGap(1, -1, 32767).add(jPanel5Layout.createParallelGroup(1).add(2, this.beneficio).add(2, this.ingresos).add(2, this.gastos)).add(23, 23, 23)));
/*     */ 
/* 587 */     jPanel5Layout.setVerticalGroup(jPanel5Layout.createParallelGroup(1).add(jPanel5Layout.createSequentialGroup().add(jPanel5Layout.createParallelGroup(3).add(this.jLabel20).add(this.beneficio)).addPreferredGap(0).add(jPanel5Layout.createParallelGroup(3).add(this.jLabel21).add(this.ingresos)).addPreferredGap(0).add(jPanel5Layout.createParallelGroup(3).add(this.jLabel22).add(this.gastos))));
/*     */ 
/* 603 */     this.jPanel6.setBorder(BorderFactory.createTitledBorder(null, "Almacén", 0, 0, new Font("Lucida Grande", 0, 13), new Color(51, 0, 153)));
/*     */ 
/* 605 */     this.jLabel25.setText("Existencias");
/*     */ 
/* 607 */     this.jLabel26.setText("Unidades");
/*     */ 
/* 609 */     this.unidades.setText("9.999.999");
/*     */ 
/* 611 */     this.jLabel27.setText("Importe");
/*     */ 
/* 613 */     this.importe.setText("9.999.999 €");
/*     */ 
/* 615 */     this.jLabel28.setForeground(new Color(0, 102, 255));
/* 616 */     this.jLabel28.setText("Lo más vendido");
/*     */ 
/* 618 */     this.jTable1.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null }, { null, null, null, null }, { null, null, null, null }, { null, null, null, null } }, new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }));
/*     */ 
/* 629 */     this.jScrollPane1.setViewportView(this.jTable1);
/*     */ 
/* 631 */     this.jLabel29.setForeground(new Color(204, 0, 0));
/* 632 */     this.jLabel29.setText("Lo menos vendido");
/*     */ 
/* 634 */     this.jTable2.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null }, { null, null, null, null }, { null, null, null, null }, { null, null, null, null } }, new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }));
/*     */ 
/* 645 */     this.jScrollPane2.setViewportView(this.jTable2);
/*     */ 
/* 647 */     GroupLayout jPanel6Layout = new GroupLayout(this.jPanel6);
/* 648 */     this.jPanel6.setLayout(jPanel6Layout);
/* 649 */     jPanel6Layout.setHorizontalGroup(jPanel6Layout.createParallelGroup(1).add(jPanel6Layout.createSequentialGroup().addContainerGap().add(jPanel6Layout.createParallelGroup(1).add(this.jLabel25).add(this.jLabel28).add(this.jLabel29)).addPreferredGap(0).add(this.unidades).addPreferredGap(1).add(this.jLabel27).addPreferredGap(0).add(this.importe)).add(2, jPanel6Layout.createSequentialGroup().addContainerGap(36, 32767).add(this.jLabel26).add(322, 322, 322)).add(this.jScrollPane1, -1, 416, 32767).add(this.jScrollPane2, -1, 416, 32767));
/*     */ 
/* 670 */     jPanel6Layout.setVerticalGroup(jPanel6Layout.createParallelGroup(1).add(jPanel6Layout.createSequentialGroup().add(this.jLabel25).addPreferredGap(0).add(jPanel6Layout.createParallelGroup(3).add(this.jLabel26).add(this.unidades).add(this.jLabel27).add(this.importe)).addPreferredGap(0).add(this.jLabel28).addPreferredGap(0).add(this.jScrollPane1, -2, 152, -2).addPreferredGap(0).add(this.jLabel29).addPreferredGap(0).add(this.jScrollPane2, -1, 148, 32767)));
/*     */ 
/* 690 */     this.jButton1.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/reload.png")));
/* 691 */     this.jButton1.setText("Actualizar");
/* 692 */     this.jButton1.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 694 */         ControlCentreJInternalFrame.this.jButton1ActionPerformed(evt);
/*     */       }
/*     */     });
/* 698 */     this.jPanel7.setBorder(BorderFactory.createTitledBorder(" "));
/*     */ 
/* 700 */     this.jLabel30.setText("Fondos Propios");
/*     */ 
/* 702 */     this.jLabel31.setText("Inmovilizado");
/*     */ 
/* 704 */     this.fondosPropios.setText("9.999.999 €");
/*     */ 
/* 706 */     this.inmovilizado.setText("9.999.999 €");
/*     */ 
/* 708 */     GroupLayout jPanel7Layout = new GroupLayout(this.jPanel7);
/* 709 */     this.jPanel7.setLayout(jPanel7Layout);
/* 710 */     jPanel7Layout.setHorizontalGroup(jPanel7Layout.createParallelGroup(1).add(jPanel7Layout.createSequentialGroup().add(9, 9, 9).add(jPanel7Layout.createParallelGroup(1).add(this.jLabel30).add(this.jLabel31)).addPreferredGap(0).add(jPanel7Layout.createParallelGroup(1).add(2, this.fondosPropios).add(2, this.inmovilizado)).addContainerGap(12, 32767)));
/*     */ 
/* 723 */     jPanel7Layout.setVerticalGroup(jPanel7Layout.createParallelGroup(1).add(jPanel7Layout.createSequentialGroup().add(jPanel7Layout.createParallelGroup(3).add(this.jLabel30).add(this.fondosPropios)).addPreferredGap(0).add(jPanel7Layout.createParallelGroup(3).add(this.inmovilizado).add(this.jLabel31)).addContainerGap(13, 32767)));
/*     */ 
/* 736 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 737 */     getContentPane().setLayout(layout);
/* 738 */     layout.setHorizontalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(layout.createParallelGroup(1).add(layout.createSequentialGroup().add(layout.createParallelGroup(2).add(1, this.jPanel3, -1, -1, 32767).add(1, this.jPanel2, -1, -1, 32767).add(this.jPanel1, -1, -1, 32767).add(this.jPanel4, -1, -1, 32767)).addPreferredGap(1)).add(2, layout.createSequentialGroup().add(this.jButton1).add(103, 103, 103))).add(layout.createParallelGroup(1, false).add(layout.createSequentialGroup().add(this.jPanel5, -2, -1, -2).addPreferredGap(0).add(this.jPanel7, -2, 214, -2)).add(this.jPanel6, -1, -1, 32767)).add(45, 45, 45)));
/*     */ 
/* 761 */     layout.setVerticalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().add(layout.createParallelGroup(1).add(layout.createSequentialGroup().add(this.jPanel1, -2, -1, -2).addPreferredGap(0).add(this.jPanel2, -2, 102, -2).addPreferredGap(0).add(this.jPanel3, -2, -1, -2).addPreferredGap(0).add(this.jPanel4, -2, -1, -2).addPreferredGap(1).add(this.jButton1)).add(layout.createSequentialGroup().add(layout.createParallelGroup(1, false).add(layout.createSequentialGroup().add(this.jPanel5, -2, -1, -2).add(7, 7, 7)).add(layout.createSequentialGroup().add(this.jPanel7, 0, 81, 32767).add(18, 18, 18))).add(this.jPanel6, -2, -1, -2))).add(29, 29, 29)));
/*     */ 
/* 787 */     pack();
/*     */   }
/*     */ 
/*     */   private void jButton1ActionPerformed(ActionEvent evt) {
/* 791 */     renovar();
/*     */   }
/*     */ 
/*     */   private void formInternalFrameClosing(InternalFrameEvent evt) {
/* 795 */     saveFrameBounds();
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.ControlCentreJInternalFrame
 * JD-Core Version:    0.6.2
 */