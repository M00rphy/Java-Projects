/*     */ package almacen2.gui.listados;
/*     */ 
/*     */ import almacen2.data.AlmacenInterno;
/*     */ import almacen2.data.FPObject;
/*     */ import almacen2.data.ManejadorAlmacenInterno;
/*     */ import almacen2.data.ManejadorListasInicio;
/*     */ import almacen2.data.MySQLConection;
/*     */ import almacen2.data.listados.ManejadorListados;
/*     */ import contaes.Inicio;
/*     */ import contaes.MarcoInicio;
/*     */ import contaes.calendario.ICalendarTextField;
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.Color;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.PrintStream;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
import java.util.List;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.DefaultComboBoxModel;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JInternalFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.event.InternalFrameAdapter;
/*     */ import javax.swing.event.InternalFrameEvent;
/*     */ import org.jdesktop.layout.GroupLayout;
/*     */ import org.jdesktop.layout.GroupLayout.ParallelGroup;
/*     */ import org.jdesktop.layout.GroupLayout.SequentialGroup;
/*     */ import org.jdesktop.swingx.JXHyperlink;
/*     */ import org.jdesktop.swingx.JXTaskPane;
/*     */ 
/*     */ public class ListadosJInternalFrame extends JInternalFrame
/*     */   implements ActionListener
/*     */ {
/*     */   private static final String L10 = "l109";
/*     */   private static final String L110 = "l110";
/*     */   private static final String L111 = "l111";
/*     */   private static final String L120 = "l120";
/*     */   private static final String L121 = "l121";
/*     */   private static final String L130 = "l130";
/*     */   private static final String L131 = "l131";
/*     */   private static final String L20 = "l209";
/*     */   private static final String L210 = "l210";
/*     */   private static final String L211 = "l211";
/*     */   private static final String L220 = "l220";
/*     */   private static final String L221 = "l221";
/*     */   private static final String L230 = "l230";
/*     */   private static final String L231 = "l231";
/*     */   private static final String L30 = "l309";
/*     */   private static final String L310 = "l310";
/*     */   private static final String L311 = "l311";
/*     */   private static final String L320 = "l320";
/*     */   private static final String L321 = "l321";
/*     */   private static final String L330 = "l330";
/*     */   private static final String L331 = "l331";
/*     */   private static final String L140 = "l140";
/*     */   private static final String L240 = "l240";
/*     */   MySQLConection con;
/*  64 */   DefaultComboBoxModel modeloFamilas = new DefaultComboBoxModel();
/*  65 */   DefaultComboBoxModel modeloProveedores = new DefaultComboBoxModel();
/*  66 */   DefaultComboBoxModel modeloAlmacenes = new DefaultComboBoxModel();
/*     */   private ICalendarTextField campoFechaFin;
/*     */   private ICalendarTextField campoFechaInicio;
/*     */   private JComboBox comboAlmacenes;
/*     */   private JComboBox comboFamilias;
/*     */   private JComboBox comboProveedores;
/*     */   private JLabel jLabel1;
/*     */   private JLabel jLabel2;
/*     */   private JLabel jLabel3;
/*     */   private JLabel jLabel4;
/*     */   private JLabel jLabel5;
/*     */   private JPanel jPanel1;
/*     */   private JXHyperlink jXHyperlink1;
/*     */   private JXHyperlink jXHyperlink10;
/*     */   private JXHyperlink jXHyperlink11;
/*     */   private JXHyperlink jXHyperlink12;
/*     */   private JXHyperlink jXHyperlink13;
/*     */   private JXHyperlink jXHyperlink14;
/*     */   private JXHyperlink jXHyperlink15;
/*     */   private JXHyperlink jXHyperlink16;
/*     */   private JXHyperlink jXHyperlink17;
/*     */   private JXHyperlink jXHyperlink18;
/*     */   private JXHyperlink jXHyperlink19;
/*     */   private JXHyperlink jXHyperlink2;
/*     */   private JXHyperlink jXHyperlink20;
/*     */   private JXHyperlink jXHyperlink21;
/*     */   private JXHyperlink jXHyperlink22;
/*     */   private JXHyperlink jXHyperlink23;
/*     */   private JXHyperlink jXHyperlink3;
/*     */   private JXHyperlink jXHyperlink4;
/*     */   private JXHyperlink jXHyperlink5;
/*     */   private JXHyperlink jXHyperlink6;
/*     */   private JXHyperlink jXHyperlink7;
/*     */   private JXHyperlink jXHyperlink8;
/*     */   private JXHyperlink jXHyperlink9;
/*     */   private JXTaskPane jXTaskPane1;
/*     */   private JXTaskPane jXTaskPane10;
/*     */   private JXTaskPane jXTaskPane11;
/*     */   private JXTaskPane jXTaskPane12;
/*     */   private JXTaskPane jXTaskPane2;
/*     */   private JXTaskPane jXTaskPane3;
/*     */   private JXTaskPane jXTaskPane4;
/*     */   private JXTaskPane jXTaskPane5;
/*     */   private JXTaskPane jXTaskPane6;
/*     */   private JXTaskPane jXTaskPane7;
/*     */   private JXTaskPane jXTaskPane8;
/*     */   private JXTaskPane jXTaskPane9;
/*     */ 
/*     */   public ListadosJInternalFrame(String titulo)
/*     */   {
/*  70 */     super(titulo, true, true, true, true);
/*  71 */     initComponents();
/*  72 */     InitFrame();
/*  73 */     this.campoFechaInicio.setComponente(this.campoFechaFin);
/*  74 */     this.campoFechaFin.setComponente(this.comboFamilias);
/*     */   }
/*     */ 
/*     */   private void InitFrame() {
/*  78 */     addInternalFrameListener(new InternalFrameAdapter()
/*     */     {
/*     */       public void internalFrameClosed(InternalFrameEvent e)
/*     */       {
/*  82 */         Inicio.frame.eliminarMarcoMenu(Mensajes.getString("listados"));
/*  83 */         super.internalFrameClosed(e);
/*     */       }
/*     */ 
/*     */       public void internalFrameClosing(InternalFrameEvent e)
/*     */       {
/*  88 */         if (ListadosJInternalFrame.this.con != null) {
/*  89 */           ListadosJInternalFrame.this.con.cierraConexion();
/*     */         }
/*  91 */         super.internalFrameClosing(e);
/*     */       }
/*     */     });
/*  94 */     setBounds(40, 40, 540, 535);
/*  95 */     this.con = new MySQLConection();
/*     */ 
/*  98 */     ManejadorListasInicio mLI = new ManejadorListasInicio(this.con);
/*  99 */     FPObject[] familias = mLI.getFamilias();
/* 100 */     for (FPObject familia : familias) {
/* 101 */       this.modeloFamilas.addElement(familia);
/*     */     }
/* 103 */     this.comboFamilias.setModel(this.modeloFamilas);
/* 104 */     FPObject[] proveedores = mLI.getProveedores();
/* 105 */     for (FPObject proveedor : proveedores) {
/* 106 */       this.modeloProveedores.addElement(proveedor);
/*     */     }
/* 108 */     this.comboProveedores.setModel(this.modeloProveedores);
/*     */ 
/* 110 */     ArrayList almacenes = new ManejadorAlmacenInterno(this.con).getAlmacenes();
/* 111 */     almacenes.add(0, new AlmacenInterno(Integer.valueOf(-1), "Todos"));
/* 112 */     for (AlmacenInterno almacenInterno : (List<AlmacenInterno>)almacenes) {
/* 113 */       this.modeloAlmacenes.addElement(almacenInterno);
/*     */     }
/* 115 */     this.comboAlmacenes.setModel(this.modeloAlmacenes);
/*     */   }
/*     */ 
/*     */   private int getId(int origen)
/*     */   {
/* 120 */     int id = -1;
/* 121 */     FPObject objeto = null;
/* 122 */     if (origen == 1)
/* 123 */       objeto = (FPObject)this.comboFamilias.getSelectedItem();
/* 124 */     else if (origen == 2) {
/* 125 */       objeto = (FPObject)this.comboProveedores.getSelectedItem();
/*     */     }
/* 127 */     if (objeto != null) {
/* 128 */       id = objeto.getId();
/*     */     }
/* 130 */     return id;
/*     */   }
/*     */ 
/*     */   private String crearTituloListado(int tipo, int origen, int agrupa) {
/* 134 */     String ListadoTitle = "";
/* 135 */     switch (tipo) {
/*     */     case 0:
/* 137 */       ListadoTitle = Mensajes.getString("existencias");
/* 138 */       break;
/*     */     case 1:
/* 140 */       ListadoTitle = Mensajes.getString("compras");
/* 141 */       break;
/*     */     case 2:
/* 143 */       ListadoTitle = Mensajes.getString("ventas");
/* 144 */       break;
/*     */     case 3:
/*     */     case 4:
/* 147 */       ListadoTitle = Mensajes.getString("compraVenta2");
/*     */     }
/*     */ 
/* 151 */     ListadoTitle = ListadoTitle + " " + Mensajes.getString("de") + " ";
/* 152 */     FPObject objeto = null;
/* 153 */     switch (origen) {
/*     */     case 0:
/* 155 */       if (tipo == 4) {
/* 156 */         ListadoTitle = ListadoTitle + Mensajes.getString("familias");
/*     */       } else {
/* 158 */         objeto = (FPObject)this.comboFamilias.getSelectedItem();
/* 159 */         if (objeto != null)
/* 160 */           ListadoTitle = ListadoTitle + objeto.getNombre();  } break;
/*     */     case 1:
/* 165 */       if (tipo == 4) {
/* 166 */         ListadoTitle = ListadoTitle + Mensajes.getString("proveedores");
/*     */       } else {
/* 168 */         objeto = (FPObject)this.comboProveedores.getSelectedItem();
/* 169 */         if (objeto != null)
/* 170 */           ListadoTitle = ListadoTitle + objeto.getNombre();  } break;
/*     */     case 2:
/* 175 */       ListadoTitle = ListadoTitle + Mensajes.getString("todo");
/* 176 */       break;
/*     */     }
/*     */ 
/* 180 */     if (agrupa == 0)
/* 181 */       ListadoTitle = ListadoTitle + " , " + Mensajes.getString("diario");
/* 182 */     else if (agrupa == 1) {
/* 183 */       ListadoTitle = ListadoTitle + " , " + Mensajes.getString("mensual");
/*     */     }
/* 185 */     return ListadoTitle;
/*     */   }
/*     */ 
/*     */   private void mostrarMarco(JFrame frame) {
/* 189 */     frame.validate();
/*     */ 
/* 191 */     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/* 192 */     Dimension frameSize = frame.getSize();
/* 193 */     if (frameSize.height > screenSize.height) {
/* 194 */       frameSize.height = screenSize.height;
/*     */     }
/* 196 */     if (frameSize.width > screenSize.width) {
/* 197 */       frameSize.width = screenSize.width;
/*     */     }
/* 199 */     frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
/*     */ 
/* 201 */     frame.setVisible(true);
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/* 213 */     this.jLabel1 = new JLabel();
/* 214 */     this.campoFechaInicio = new ICalendarTextField();
/* 215 */     this.jLabel2 = new JLabel();
/* 216 */     this.campoFechaFin = new ICalendarTextField();
/* 217 */     this.jLabel3 = new JLabel();
/* 218 */     this.comboFamilias = new JComboBox();
/* 219 */     this.jLabel4 = new JLabel();
/* 220 */     this.comboProveedores = new JComboBox();
/* 221 */     this.jPanel1 = new JPanel();
/* 222 */     this.jXTaskPane1 = new JXTaskPane();
/* 223 */     this.jXHyperlink19 = new JXHyperlink();
/* 224 */     this.jXTaskPane4 = new JXTaskPane();
/* 225 */     this.jXHyperlink1 = new JXHyperlink();
/* 226 */     this.jXHyperlink2 = new JXHyperlink();
/* 227 */     this.jXTaskPane7 = new JXTaskPane();
/* 228 */     this.jXHyperlink7 = new JXHyperlink();
/* 229 */     this.jXHyperlink8 = new JXHyperlink();
/* 230 */     this.jXTaskPane10 = new JXTaskPane();
/* 231 */     this.jXHyperlink13 = new JXHyperlink();
/* 232 */     this.jXHyperlink14 = new JXHyperlink();
/* 233 */     this.jXHyperlink22 = new JXHyperlink();
/* 234 */     this.jXTaskPane2 = new JXTaskPane();
/* 235 */     this.jXHyperlink20 = new JXHyperlink();
/* 236 */     this.jXTaskPane5 = new JXTaskPane();
/* 237 */     this.jXHyperlink3 = new JXHyperlink();
/* 238 */     this.jXHyperlink4 = new JXHyperlink();
/* 239 */     this.jXTaskPane8 = new JXTaskPane();
/* 240 */     this.jXHyperlink9 = new JXHyperlink();
/* 241 */     this.jXHyperlink10 = new JXHyperlink();
/* 242 */     this.jXTaskPane11 = new JXTaskPane();
/* 243 */     this.jXHyperlink15 = new JXHyperlink();
/* 244 */     this.jXHyperlink16 = new JXHyperlink();
/* 245 */     this.jXHyperlink23 = new JXHyperlink();
/* 246 */     this.jXTaskPane3 = new JXTaskPane();
/* 247 */     this.jXHyperlink21 = new JXHyperlink();
/* 248 */     this.jXTaskPane6 = new JXTaskPane();
/* 249 */     this.jXHyperlink5 = new JXHyperlink();
/* 250 */     this.jXHyperlink6 = new JXHyperlink();
/* 251 */     this.jXTaskPane9 = new JXTaskPane();
/* 252 */     this.jXHyperlink11 = new JXHyperlink();
/* 253 */     this.jXHyperlink12 = new JXHyperlink();
/* 254 */     this.jXTaskPane12 = new JXTaskPane();
/* 255 */     this.jXHyperlink17 = new JXHyperlink();
/* 256 */     this.jXHyperlink18 = new JXHyperlink();
/* 257 */     this.jLabel5 = new JLabel();
/* 258 */     this.comboAlmacenes = new JComboBox();
/*     */ 
/* 260 */     this.jLabel1.setText("Fecha Inicial");
/*     */ 
/* 262 */     this.jLabel2.setText("Fecha final");
/*     */ 
/* 264 */     this.jLabel3.setText("Familia");
/*     */ 
/* 266 */     this.jLabel4.setText("Proveedor");
/*     */ 
/* 268 */     this.jPanel1.setBorder(BorderFactory.createTitledBorder("Tipo de listado"));
/*     */ 
/* 270 */     this.jXTaskPane1.setTitle("Familias");
/*     */ 
/* 272 */     this.jXHyperlink19.setClickedColor(new Color(0, 51, 255));
/* 273 */     ResourceBundle bundle = ResourceBundle.getBundle("internationalization/Mensajes");
/* 274 */     this.jXHyperlink19.setText(bundle.getString("existencias"));
/* 275 */     this.jXHyperlink19.setActionCommand("l109");
/* 276 */     this.jXHyperlink19.addActionListener(this);
/* 277 */     this.jXTaskPane1.getContentPane().add(this.jXHyperlink19);
/*     */ 
/* 279 */     this.jXTaskPane4.setCollapsed(true);
/* 280 */     this.jXTaskPane4.setTitle("Compras");
/*     */ 
/* 282 */     this.jXHyperlink1.setClickedColor(new Color(0, 51, 255));
/* 283 */     this.jXHyperlink1.setText("Diario");
/* 284 */     this.jXHyperlink1.setActionCommand("l110");
/* 285 */     this.jXHyperlink1.addActionListener(this);
/* 286 */     this.jXTaskPane4.getContentPane().add(this.jXHyperlink1);
/*     */ 
/* 288 */     this.jXHyperlink2.setClickedColor(new Color(0, 51, 255));
/* 289 */     this.jXHyperlink2.setText("Mensual");
/* 290 */     this.jXHyperlink2.setActionCommand("l111");
/* 291 */     this.jXHyperlink2.addActionListener(this);
/* 292 */     this.jXTaskPane4.getContentPane().add(this.jXHyperlink2);
/*     */ 
/* 294 */     this.jXTaskPane1.getContentPane().add(this.jXTaskPane4);
/*     */ 
/* 296 */     this.jXTaskPane7.setCollapsed(true);
/* 297 */     this.jXTaskPane7.setTitle("Ventas");
/*     */ 
/* 299 */     this.jXHyperlink7.setClickedColor(new Color(0, 51, 255));
/* 300 */     this.jXHyperlink7.setText("Diario");
/* 301 */     this.jXHyperlink7.setActionCommand("l120");
/* 302 */     this.jXHyperlink7.addActionListener(this);
/* 303 */     this.jXTaskPane7.getContentPane().add(this.jXHyperlink7);
/*     */ 
/* 305 */     this.jXHyperlink8.setClickedColor(new Color(0, 51, 255));
/* 306 */     this.jXHyperlink8.setText("Mensual");
/* 307 */     this.jXHyperlink8.setActionCommand("l121");
/* 308 */     this.jXHyperlink8.addActionListener(this);
/* 309 */     this.jXTaskPane7.getContentPane().add(this.jXHyperlink8);
/*     */ 
/* 311 */     this.jXTaskPane1.getContentPane().add(this.jXTaskPane7);
/*     */ 
/* 313 */     this.jXTaskPane10.setTitle("Todo");
/*     */ 
/* 315 */     this.jXHyperlink13.setClickedColor(new Color(0, 51, 255));
/* 316 */     this.jXHyperlink13.setText("Diario");
/* 317 */     this.jXHyperlink13.setActionCommand("l130");
/* 318 */     this.jXHyperlink13.addActionListener(this);
/* 319 */     this.jXTaskPane10.getContentPane().add(this.jXHyperlink13);
/*     */ 
/* 321 */     this.jXHyperlink14.setClickedColor(new Color(0, 51, 255));
/* 322 */     this.jXHyperlink14.setText("Mensual");
/* 323 */     this.jXHyperlink14.setActionCommand("l131");
/* 324 */     this.jXHyperlink14.addActionListener(this);
/* 325 */     this.jXTaskPane10.getContentPane().add(this.jXHyperlink14);
/*     */ 
/* 327 */     this.jXTaskPane1.getContentPane().add(this.jXTaskPane10);
/*     */ 
/* 329 */     this.jXHyperlink22.setClickedColor(new Color(0, 51, 255));
/* 330 */     this.jXHyperlink22.setText("Agrupado");
/* 331 */     this.jXHyperlink22.setActionCommand("l140");
/* 332 */     this.jXHyperlink22.addActionListener(this);
/* 333 */     this.jXTaskPane1.getContentPane().add(this.jXHyperlink22);
/*     */ 
/* 335 */     this.jXTaskPane2.setTitle("Proveedores");
/*     */ 
/* 337 */     this.jXHyperlink20.setClickedColor(new Color(0, 51, 255));
/* 338 */     this.jXHyperlink20.setText("Existencias");
/* 339 */     this.jXHyperlink20.setActionCommand("l209");
/* 340 */     this.jXHyperlink20.addActionListener(this);
/* 341 */     this.jXTaskPane2.getContentPane().add(this.jXHyperlink20);
/*     */ 
/* 343 */     this.jXTaskPane5.setCollapsed(true);
/* 344 */     this.jXTaskPane5.setTitle("Compras");
/*     */ 
/* 346 */     this.jXHyperlink3.setClickedColor(new Color(0, 51, 255));
/* 347 */     this.jXHyperlink3.setText("Diario");
/* 348 */     this.jXHyperlink3.setActionCommand("l210");
/* 349 */     this.jXHyperlink3.addActionListener(this);
/* 350 */     this.jXTaskPane5.getContentPane().add(this.jXHyperlink3);
/*     */ 
/* 352 */     this.jXHyperlink4.setClickedColor(new Color(0, 51, 255));
/* 353 */     this.jXHyperlink4.setText("Mensual");
/* 354 */     this.jXHyperlink4.setActionCommand("l211");
/* 355 */     this.jXHyperlink4.addActionListener(this);
/* 356 */     this.jXTaskPane5.getContentPane().add(this.jXHyperlink4);
/*     */ 
/* 358 */     this.jXTaskPane2.getContentPane().add(this.jXTaskPane5);
/*     */ 
/* 360 */     this.jXTaskPane8.setCollapsed(true);
/* 361 */     this.jXTaskPane8.setTitle("Ventas");
/*     */ 
/* 363 */     this.jXHyperlink9.setClickedColor(new Color(0, 51, 255));
/* 364 */     this.jXHyperlink9.setText("Diario");
/* 365 */     this.jXHyperlink9.setActionCommand("l220");
/* 366 */     this.jXHyperlink9.addActionListener(this);
/* 367 */     this.jXTaskPane8.getContentPane().add(this.jXHyperlink9);
/*     */ 
/* 369 */     this.jXHyperlink10.setClickedColor(new Color(0, 51, 255));
/* 370 */     this.jXHyperlink10.setText("Mensual");
/* 371 */     this.jXHyperlink10.setActionCommand("l221");
/* 372 */     this.jXHyperlink10.addActionListener(this);
/* 373 */     this.jXTaskPane8.getContentPane().add(this.jXHyperlink10);
/*     */ 
/* 375 */     this.jXTaskPane2.getContentPane().add(this.jXTaskPane8);
/*     */ 
/* 377 */     this.jXTaskPane11.setTitle("Todo");
/*     */ 
/* 379 */     this.jXHyperlink15.setClickedColor(new Color(0, 51, 255));
/* 380 */     this.jXHyperlink15.setText("Diario");
/* 381 */     this.jXHyperlink15.setActionCommand("l230");
/* 382 */     this.jXHyperlink15.addActionListener(this);
/* 383 */     this.jXTaskPane11.getContentPane().add(this.jXHyperlink15);
/*     */ 
/* 385 */     this.jXHyperlink16.setClickedColor(new Color(0, 51, 255));
/* 386 */     this.jXHyperlink16.setText("Mensual");
/* 387 */     this.jXHyperlink16.setActionCommand("l231");
/* 388 */     this.jXHyperlink16.addActionListener(this);
/* 389 */     this.jXTaskPane11.getContentPane().add(this.jXHyperlink16);
/*     */ 
/* 391 */     this.jXTaskPane2.getContentPane().add(this.jXTaskPane11);
/*     */ 
/* 393 */     this.jXHyperlink23.setClickedColor(new Color(0, 51, 255));
/* 394 */     this.jXHyperlink23.setText("Agrupado");
/* 395 */     this.jXHyperlink23.setActionCommand("l240");
/* 396 */     this.jXHyperlink23.addActionListener(this);
/* 397 */     this.jXTaskPane2.getContentPane().add(this.jXHyperlink23);
/*     */ 
/* 399 */     this.jXTaskPane3.setTitle("Global");
/*     */ 
/* 401 */     this.jXHyperlink21.setClickedColor(new Color(0, 51, 255));
/* 402 */     this.jXHyperlink21.setText("Existencias");
/* 403 */     this.jXHyperlink21.setActionCommand("l309");
/* 404 */     this.jXHyperlink21.addActionListener(this);
/* 405 */     this.jXTaskPane3.getContentPane().add(this.jXHyperlink21);
/*     */ 
/* 407 */     this.jXTaskPane6.setCollapsed(true);
/* 408 */     this.jXTaskPane6.setTitle("Compras");
/*     */ 
/* 410 */     this.jXHyperlink5.setClickedColor(new Color(0, 51, 255));
/* 411 */     this.jXHyperlink5.setText("Diario");
/* 412 */     this.jXHyperlink5.setActionCommand("l310");
/* 413 */     this.jXHyperlink5.addActionListener(this);
/* 414 */     this.jXTaskPane6.getContentPane().add(this.jXHyperlink5);
/*     */ 
/* 416 */     this.jXHyperlink6.setClickedColor(new Color(0, 51, 255));
/* 417 */     this.jXHyperlink6.setText("Mensual");
/* 418 */     this.jXHyperlink6.setActionCommand("l311");
/* 419 */     this.jXHyperlink6.addActionListener(this);
/* 420 */     this.jXTaskPane6.getContentPane().add(this.jXHyperlink6);
/*     */ 
/* 422 */     this.jXTaskPane3.getContentPane().add(this.jXTaskPane6);
/*     */ 
/* 424 */     this.jXTaskPane9.setCollapsed(true);
/* 425 */     this.jXTaskPane9.setTitle("Ventas");
/*     */ 
/* 427 */     this.jXHyperlink11.setClickedColor(new Color(0, 51, 255));
/* 428 */     this.jXHyperlink11.setText("Diario");
/* 429 */     this.jXHyperlink11.setActionCommand("l320");
/* 430 */     this.jXHyperlink11.addActionListener(this);
/* 431 */     this.jXTaskPane9.getContentPane().add(this.jXHyperlink11);
/*     */ 
/* 433 */     this.jXHyperlink12.setClickedColor(new Color(0, 51, 255));
/* 434 */     this.jXHyperlink12.setText("Mensual");
/* 435 */     this.jXHyperlink12.setActionCommand("l321");
/* 436 */     this.jXHyperlink12.addActionListener(this);
/* 437 */     this.jXTaskPane9.getContentPane().add(this.jXHyperlink12);
/*     */ 
/* 439 */     this.jXTaskPane3.getContentPane().add(this.jXTaskPane9);
/*     */ 
/* 441 */     this.jXTaskPane12.setTitle("Todo");
/*     */ 
/* 443 */     this.jXHyperlink17.setClickedColor(new Color(0, 51, 255));
/* 444 */     this.jXHyperlink17.setText("Diario");
/* 445 */     this.jXHyperlink17.setActionCommand("l330");
/* 446 */     this.jXHyperlink17.addActionListener(this);
/* 447 */     this.jXTaskPane12.getContentPane().add(this.jXHyperlink17);
/*     */ 
/* 449 */     this.jXHyperlink18.setClickedColor(new Color(0, 51, 255));
/* 450 */     this.jXHyperlink18.setText("Mensual");
/* 451 */     this.jXHyperlink18.setActionCommand("l331");
/* 452 */     this.jXHyperlink18.addActionListener(this);
/* 453 */     this.jXTaskPane12.getContentPane().add(this.jXHyperlink18);
/*     */ 
/* 455 */     this.jXTaskPane3.getContentPane().add(this.jXTaskPane12);
/*     */ 
/* 457 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 458 */     this.jPanel1.setLayout(jPanel1Layout);
/* 459 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().add(this.jXTaskPane1, -2, 144, -2).addPreferredGap(1).add(this.jXTaskPane2, -2, 140, -2).add(18, 18, 18).add(this.jXTaskPane3, -2, 133, -2).addContainerGap(40, 32767)));
/*     */ 
/* 469 */     jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().add(jPanel1Layout.createParallelGroup(1).add(this.jXTaskPane3, -2, -1, -2).add(this.jXTaskPane2, -2, -1, -2).add(this.jXTaskPane1, -2, -1, -2)).addContainerGap(-1, 32767)));
/*     */ 
/* 479 */     this.jLabel5.setText(bundle.getString("almacen"));
/*     */ 
/* 481 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 482 */     getContentPane().setLayout(layout);
/* 483 */     layout.setHorizontalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(layout.createParallelGroup(1).add(layout.createSequentialGroup().add(this.jLabel1).addPreferredGap(1).add(this.campoFechaInicio, -2, 138, -2).add(18, 18, 18).add(this.jLabel2).addPreferredGap(1).add(this.campoFechaFin, -2, 133, -2)).add(layout.createParallelGroup(2, false).add(1, layout.createSequentialGroup().add(this.jLabel5).add(18, 18, 18).add(this.comboAlmacenes, 0, -1, 32767)).add(1, layout.createSequentialGroup().add(layout.createParallelGroup(1).add(this.jLabel4).add(this.jLabel3)).addPreferredGap(0).add(layout.createParallelGroup(1, false).add(this.comboProveedores, 0, -1, 32767).add(this.comboFamilias, 0, 240, 32767)))).add(this.jPanel1, -1, -1, 32767)).addContainerGap()));
/*     */ 
/* 512 */     layout.setVerticalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(layout.createParallelGroup(2).add(this.campoFechaFin, -2, -1, -2).add(this.jLabel2).add(this.campoFechaInicio, -2, 26, -2).add(this.jLabel1)).addPreferredGap(1).add(layout.createParallelGroup(3).add(this.jLabel3).add(this.comboFamilias, -2, -1, -2)).addPreferredGap(1).add(layout.createParallelGroup(3).add(this.jLabel4).add(this.comboProveedores, -2, -1, -2)).addPreferredGap(1).add(layout.createParallelGroup(3).add(this.jLabel5).add(this.comboAlmacenes, -2, -1, -2)).addPreferredGap(1).add(this.jPanel1, -2, -1, -2).addContainerGap(641, 32767)));
/*     */ 
/* 538 */     pack();
/*     */   }
/*     */ 
/*     */   public void actionPerformed(ActionEvent e)
/*     */   {
/* 590 */     Date fechaI = this.campoFechaInicio.getDate();
/* 591 */     Date fechaF = this.campoFechaFin.getDate();
/* 592 */     if ((fechaI != null) && (fechaF != null)) {
/* 593 */       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/* 594 */       String[] fecha = { sdf.format(fechaI), sdf.format(fechaF) };
/* 595 */       String cmd = e.getActionCommand();
/* 596 */       System.out.println(cmd);
/* 597 */       int origen = Integer.parseInt(cmd.substring(1, 2));
/* 598 */       int tipo = Integer.parseInt(cmd.substring(2, 3));
/* 599 */       int fp = origen == 3 ? 9 : getId(origen);
/* 600 */       if ((fp != -1) || (tipo == 4)) {
/* 601 */         JFrame marco = null;
/* 602 */         origen--;
/* 603 */         int agrupa = Integer.parseInt(cmd.substring(3));
/* 604 */         AlmacenInterno almacen = (AlmacenInterno)this.comboAlmacenes.getSelectedItem();
/* 605 */         String titulo1 = crearTituloListado(tipo, origen, agrupa);
/* 606 */         String titulo2 = Mensajes.getString("almacen") + ": " + almacen.toString() + " ";
/* 607 */         ManejadorListados crear = new ManejadorListados(this.con);
/* 608 */         if (tipo == 0) {
/* 609 */           if (fecha == null) {
/* 610 */             marco = new MarcoListadoExistencias(titulo1, titulo2, crear.listadoExistencias(origen, fp, almacen.getId().intValue()));
/*     */           }
/*     */           else {
/* 613 */             titulo2 = fecha[0].substring(8) + "-" + fecha[0].substring(5, 7) + "-" + fecha[0].substring(0, 4);
/* 614 */             marco = new MarcoListadoExistencias(titulo1, titulo2, crear.listadoExistencias(origen, fp, fecha[0], almacen.getId().intValue()));
/*     */           }
/*     */         }
/* 617 */         else if (tipo == 4) {
/* 618 */           titulo2 = fecha[0].substring(8) + "-" + fecha[0].substring(5, 7) + "-" + fecha[0].substring(0, 4) + " " + Mensajes.getString("a") + " " + fecha[1].substring(8) + "-" + fecha[1].substring(5, 7) + "-" + fecha[1].substring(0, 4);
/*     */ 
/* 621 */           marco = new MarcoListadoAgrupados(titulo1, titulo2, crear.listadoAgrupado(origen, fecha[0], fecha[1], almacen.getId().intValue()));
/*     */         } else {
/* 623 */           titulo2 = fecha[0].substring(8) + "-" + fecha[0].substring(5, 7) + "-" + fecha[0].substring(0, 4) + " " + Mensajes.getString("a") + " " + fecha[1].substring(8) + "-" + fecha[1].substring(5, 7) + "-" + fecha[1].substring(0, 4);
/*     */ 
/* 626 */           tipo--;
/* 627 */           if (agrupa == 0) {
/* 628 */             marco = new MarcoListadoCV(titulo1, titulo2, crear.listadoCVDiario(origen, tipo, fp, fecha[0], fecha[1], almacen.getId().intValue()));
/*     */           }
/* 630 */           else if (agrupa == 1) {
/* 631 */             marco = new MarcoListadoCV(titulo1, titulo2, crear.listadoCVMensual(origen, tipo, fp, fecha[0], fecha[1], almacen.getId().intValue()));
/*     */           }
/*     */         }
/*     */ 
/* 635 */         mostrarMarco(marco);
/*     */       } else {
/* 637 */         mostrarError("No ha seleccionado una familia o proveedor");
/*     */       }
/*     */     }
/*     */     else {
/* 641 */       mostrarError("No ha seleccionado fechas");
/*     */     }
/*     */   }
/*     */ 
/*     */   private void mostrarError(String error) {
/* 646 */     JOptionPane.showMessageDialog(getContentPane(), error, "Error", 0);
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.gui.listados.ListadosJInternalFrame
 * JD-Core Version:    0.6.2
 */