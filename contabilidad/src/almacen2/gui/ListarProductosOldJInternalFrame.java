/*      */ package almacen2.gui;
/*      */ 
/*      */ import almacen2.data.MySQLConectionOldAlmacen;
/*      */ import contaes.listados.Listado;
/*      */ import java.awt.BorderLayout;
/*      */ import java.awt.Color;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.GridBagConstraints;
/*      */ import java.awt.GridBagLayout;
/*      */ import java.awt.Insets;
/*      */ import java.awt.Toolkit;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.ActionListener;
/*      */ import java.sql.ResultSet;
/*      */ import java.sql.SQLException;
/*      */ import java.text.DecimalFormat;
/*      */ import java.text.DecimalFormatSymbols;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.Locale;
/*      */ import java.util.Vector;
/*      */ import javax.swing.BorderFactory;
/*      */ import javax.swing.ButtonGroup;
/*      */ import javax.swing.JButton;
/*      */ import javax.swing.JCheckBox;
/*      */ import javax.swing.JComboBox;
/*      */ import javax.swing.JFrame;
/*      */ import javax.swing.JInternalFrame;
/*      */ import javax.swing.JLabel;
/*      */ import javax.swing.JOptionPane;
/*      */ import javax.swing.JPanel;
/*      */ import javax.swing.JRadioButton;
/*      */ import javax.swing.JTextField;
/*      */ 
/*      */ public class ListarProductosOldJInternalFrame extends JInternalFrame
/*      */ {
/*      */   private static final long serialVersionUID = 1L;
/*      */   MySQLConectionOldAlmacen con;
/*      */   private ResultSet res;
/*   39 */   private JPanel jContentPane = null;
/*   40 */   private JPanel PanelCampos = null;
/*   41 */   private JPanel PanelTipos = null;
/*   42 */   private JPanel PanelInferior = null;
/*   43 */   private JLabel jLabel = null;
/*   44 */   private JTextField jTextField4 = null;
/*   45 */   private JComboBox jComboBox1 = null;
/*   46 */   private JLabel jLabel5 = null;
/*   47 */   private JTextField jTextField1 = null;
/*   48 */   private JLabel FechaEt = null;
/*   49 */   private JLabel jLabel6 = null;
/*   50 */   private JLabel jLabel7 = null;
/*   51 */   private JTextField jTextField2 = null;
/*   52 */   private JTextField jTextField3 = null;
/*   53 */   private JButton Listar = null;
/*   54 */   private JLabel jLabel3 = null;
/*   55 */   private JLabel jLabel4 = null;
/*   56 */   private JCheckBox jCheckBox1 = null;
/*   57 */   private JCheckBox jCheckBox2 = null;
/*   58 */   private JCheckBox jCheckBox3 = null;
/*   59 */   private JCheckBox jCheckBox4 = null;
/*   60 */   private JCheckBox jCheckBox5 = null;
/*   61 */   private JCheckBox jCheckBox6 = null;
/*   62 */   private JCheckBox jCheckBox7 = null;
/*   63 */   private JCheckBox jCheckBox8 = null;
/*   64 */   private ButtonGroup GrupoBotones = null;
/*   65 */   private JRadioButton ord_referencia = null;
/*   66 */   private JRadioButton ord_f_compra = null;
/*   67 */   private JRadioButton ord_f_venta = null;
/*   68 */   private JButton AyudaListadoLibre = null;
/*      */ 
/*      */   public ListarProductosOldJInternalFrame()
/*      */   {
/*   74 */     super("Listados de almacén", true, true, true, true);
/*   75 */     this.con = new MySQLConectionOldAlmacen();
/*   76 */     initialize();
/*      */   }
/*      */ 
/*      */   private void initialize()
/*      */   {
/*   85 */     setSize(470, 410);
/*   86 */     setContentPane(getJContentPane());
/*      */   }
/*      */ 
/*      */   private JPanel getJContentPane()
/*      */   {
/*   95 */     if (this.jContentPane == null) {
/*   96 */       this.jContentPane = new JPanel();
/*   97 */       this.jContentPane.setLayout(new BorderLayout());
/*   98 */       this.jContentPane.add(getPanelCampos(), "Center");
/*   99 */       this.jContentPane.add(getPanelTipos(), "East");
/*  100 */       this.jContentPane.add(getPanelInferior(), "South");
/*  101 */       iniGrupoBotones();
/*      */     }
/*  103 */     return this.jContentPane;
/*      */   }
/*      */ 
/*      */   private JPanel getPanelCampos()
/*      */   {
/*  112 */     if (this.PanelCampos == null) {
/*  113 */       GridBagConstraints gridBagConstraints22 = new GridBagConstraints();
/*  114 */       gridBagConstraints22.gridx = 1;
/*  115 */       gridBagConstraints22.anchor = 17;
/*  116 */       gridBagConstraints22.gridy = 6;
/*  117 */       GridBagConstraints gridBagConstraints110 = new GridBagConstraints();
/*  118 */       gridBagConstraints110.gridx = 1;
/*  119 */       gridBagConstraints110.anchor = 17;
/*  120 */       gridBagConstraints110.gridy = 5;
/*  121 */       GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
/*  122 */       gridBagConstraints21.gridx = 1;
/*  123 */       gridBagConstraints21.anchor = 17;
/*  124 */       gridBagConstraints21.gridy = 1;
/*  125 */       GridBagConstraints gridBagConstraints20 = new GridBagConstraints();
/*  126 */       gridBagConstraints20.gridx = 0;
/*  127 */       gridBagConstraints20.anchor = 17;
/*  128 */       gridBagConstraints20.gridy = 9;
/*  129 */       GridBagConstraints gridBagConstraints19 = new GridBagConstraints();
/*  130 */       gridBagConstraints19.gridx = 0;
/*  131 */       gridBagConstraints19.anchor = 17;
/*  132 */       gridBagConstraints19.gridy = 8;
/*  133 */       GridBagConstraints gridBagConstraints18 = new GridBagConstraints();
/*  134 */       gridBagConstraints18.gridx = 0;
/*  135 */       gridBagConstraints18.anchor = 17;
/*  136 */       gridBagConstraints18.gridy = 7;
/*  137 */       GridBagConstraints gridBagConstraints17 = new GridBagConstraints();
/*  138 */       gridBagConstraints17.gridx = 0;
/*  139 */       gridBagConstraints17.anchor = 17;
/*  140 */       gridBagConstraints17.gridy = 6;
/*  141 */       GridBagConstraints gridBagConstraints16 = new GridBagConstraints();
/*  142 */       gridBagConstraints16.gridx = 0;
/*  143 */       gridBagConstraints16.anchor = 17;
/*  144 */       gridBagConstraints16.gridy = 5;
/*  145 */       GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
/*  146 */       gridBagConstraints15.gridx = 0;
/*  147 */       gridBagConstraints15.anchor = 17;
/*  148 */       gridBagConstraints15.gridy = 4;
/*  149 */       GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
/*  150 */       gridBagConstraints14.gridx = 0;
/*  151 */       gridBagConstraints14.anchor = 17;
/*  152 */       gridBagConstraints14.gridy = 3;
/*  153 */       GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
/*  154 */       gridBagConstraints13.gridx = 0;
/*  155 */       gridBagConstraints13.anchor = 17;
/*  156 */       gridBagConstraints13.gridy = 2;
/*  157 */       GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
/*  158 */       gridBagConstraints12.gridx = 0;
/*  159 */       gridBagConstraints12.anchor = 17;
/*  160 */       gridBagConstraints12.gridy = 1;
/*  161 */       this.jLabel4 = new JLabel();
/*  162 */       this.jLabel4.setText("Referencia");
/*  163 */       GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
/*  164 */       gridBagConstraints11.gridx = 1;
/*  165 */       gridBagConstraints11.insets = new Insets(0, 5, 0, 0);
/*  166 */       gridBagConstraints11.gridy = 0;
/*  167 */       this.jLabel3 = new JLabel();
/*  168 */       this.jLabel3.setText("Ordenar por:");
/*  169 */       GridBagConstraints gridBagConstraints = new GridBagConstraints();
/*  170 */       gridBagConstraints.gridx = 0;
/*  171 */       gridBagConstraints.insets = new Insets(0, 0, 0, 5);
/*  172 */       gridBagConstraints.gridy = 0;
/*  173 */       this.jLabel = new JLabel();
/*  174 */       this.jLabel.setText("Incluir:");
/*  175 */       this.PanelCampos = new JPanel();
/*  176 */       this.PanelCampos.setLayout(new GridBagLayout());
/*  177 */       this.PanelCampos.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createLineBorder(Color.gray, 1)));
/*  178 */       this.PanelCampos.add(this.jLabel, gridBagConstraints);
/*  179 */       this.PanelCampos.add(this.jLabel3, gridBagConstraints11);
/*  180 */       this.PanelCampos.add(this.jLabel4, gridBagConstraints12);
/*  181 */       this.PanelCampos.add(getJCheckBox1(), gridBagConstraints13);
/*  182 */       this.PanelCampos.add(getJCheckBox2(), gridBagConstraints14);
/*  183 */       this.PanelCampos.add(getJCheckBox3(), gridBagConstraints15);
/*  184 */       this.PanelCampos.add(getJCheckBox4(), gridBagConstraints16);
/*  185 */       this.PanelCampos.add(getJCheckBox5(), gridBagConstraints17);
/*  186 */       this.PanelCampos.add(getJCheckBox6(), gridBagConstraints18);
/*  187 */       this.PanelCampos.add(getJCheckBox7(), gridBagConstraints19);
/*  188 */       this.PanelCampos.add(getJCheckBox8(), gridBagConstraints20);
/*  189 */       this.PanelCampos.add(getOrd_referencia(), gridBagConstraints21);
/*  190 */       this.PanelCampos.add(getOrd_f_compra(), gridBagConstraints110);
/*  191 */       this.PanelCampos.add(getOrd_f_venta(), gridBagConstraints22);
/*      */     }
/*  193 */     return this.PanelCampos;
/*      */   }
/*      */ 
/*      */   private JPanel getPanelTipos()
/*      */   {
/*  202 */     if (this.PanelTipos == null) {
/*  203 */       GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
/*  204 */       gridBagConstraints9.fill = 1;
/*  205 */       gridBagConstraints9.gridy = 5;
/*  206 */       gridBagConstraints9.weightx = 1.0D;
/*  207 */       gridBagConstraints9.insets = new Insets(0, 5, 0, 5);
/*  208 */       gridBagConstraints9.ipadx = 10;
/*  209 */       gridBagConstraints9.gridx = 1;
/*  210 */       GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
/*  211 */       gridBagConstraints8.fill = 1;
/*  212 */       gridBagConstraints8.gridy = 5;
/*  213 */       gridBagConstraints8.weightx = 1.0D;
/*  214 */       gridBagConstraints8.insets = new Insets(0, 5, 0, 5);
/*      */ 
/*  216 */       gridBagConstraints8.gridx = 0;
/*  217 */       GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
/*  218 */       gridBagConstraints7.gridx = 1;
/*  219 */       gridBagConstraints7.gridy = 4;
/*  220 */       this.jLabel7 = new JLabel();
/*  221 */       this.jLabel7.setText("Fin");
/*  222 */       GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
/*  223 */       gridBagConstraints6.gridx = 0;
/*  224 */       gridBagConstraints6.gridy = 4;
/*  225 */       this.jLabel6 = new JLabel();
/*  226 */       this.jLabel6.setText("Inicio");
/*  227 */       GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
/*  228 */       gridBagConstraints5.gridx = 0;
/*  229 */       gridBagConstraints5.gridwidth = 2;
/*  230 */       gridBagConstraints5.gridy = 3;
/*  231 */       this.FechaEt = new JLabel();
/*  232 */       this.FechaEt.setText("Fecha");
/*  233 */       GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
/*  234 */       gridBagConstraints4.fill = 1;
/*  235 */       gridBagConstraints4.insets = new Insets(0, 0, 0, 5);
/*  236 */       gridBagConstraints4.gridy = 2;
/*  237 */       gridBagConstraints4.weightx = 1.0D;
/*  238 */       gridBagConstraints4.gridx = 1;
/*  239 */       GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
/*  240 */       gridBagConstraints3.gridx = 0;
/*  241 */       gridBagConstraints3.gridy = 2;
/*  242 */       this.jLabel5 = new JLabel();
/*  243 */       this.jLabel5.setText("Familia");
/*  244 */       GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
/*  245 */       gridBagConstraints2.fill = 1;
/*  246 */       gridBagConstraints2.gridy = 1;
/*  247 */       gridBagConstraints2.weightx = 1.0D;
/*  248 */       gridBagConstraints2.gridwidth = 2;
/*  249 */       gridBagConstraints2.insets = new Insets(0, 5, 10, 5);
/*  250 */       gridBagConstraints2.gridx = 0;
/*  251 */       GridBagConstraints gridBagConstraints = new GridBagConstraints();
/*  252 */       gridBagConstraints.gridx = 0;
/*  253 */       gridBagConstraints.anchor = 17;
/*  254 */       gridBagConstraints.insets = new Insets(0, 5, 0, 0);
/*  255 */       gridBagConstraints.gridy = 0;
/*  256 */       this.jLabel = new JLabel();
/*  257 */       this.jLabel.setText("Tipo de listado");
/*  258 */       this.PanelTipos = new JPanel();
/*  259 */       this.PanelTipos.setLayout(new GridBagLayout());
/*  260 */       this.PanelTipos.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createLineBorder(Color.gray, 1)));
/*  261 */       this.PanelTipos.add(this.jLabel, gridBagConstraints);
/*  262 */       this.PanelTipos.add(getJComboBox1(), gridBagConstraints2);
/*  263 */       this.PanelTipos.add(this.jLabel5, gridBagConstraints3);
/*  264 */       this.PanelTipos.add(getJTextField1(), gridBagConstraints4);
/*  265 */       this.PanelTipos.add(this.FechaEt, gridBagConstraints5);
/*  266 */       this.PanelTipos.add(this.jLabel6, gridBagConstraints6);
/*  267 */       this.PanelTipos.add(this.jLabel7, gridBagConstraints7);
/*  268 */       this.PanelTipos.add(getJTextField2(), gridBagConstraints8);
/*  269 */       this.PanelTipos.add(getJTextField3(), gridBagConstraints9);
/*      */     }
/*  271 */     return this.PanelTipos;
/*      */   }
/*      */ 
/*      */   private JPanel getPanelInferior()
/*      */   {
/*  280 */     if (this.PanelInferior == null) {
/*  281 */       GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
/*  282 */       gridBagConstraints31.gridx = 1;
/*  283 */       gridBagConstraints31.insets = new Insets(0, 5, 0, 0);
/*  284 */       gridBagConstraints31.gridy = 1;
/*  285 */       GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
/*  286 */       gridBagConstraints10.gridx = 0;
/*  287 */       gridBagConstraints10.insets = new Insets(6, 0, 0, 0);
/*  288 */       gridBagConstraints10.gridy = 2;
/*  289 */       GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
/*  290 */       gridBagConstraints1.fill = 2;
/*  291 */       gridBagConstraints1.gridy = 1;
/*  292 */       gridBagConstraints1.weightx = 1.0D;
/*  293 */       gridBagConstraints1.gridx = 0;
/*  294 */       GridBagConstraints gridBagConstraints = new GridBagConstraints();
/*  295 */       gridBagConstraints.gridx = 0;
/*  296 */       gridBagConstraints.anchor = 17;
/*  297 */       gridBagConstraints.ipadx = 0;
/*  298 */       gridBagConstraints.insets = new Insets(0, 15, 0, 0);
/*  299 */       gridBagConstraints.gridy = 0;
/*  300 */       this.jLabel = new JLabel();
/*  301 */       this.jLabel.setText("Listado libre");
/*  302 */       this.PanelInferior = new JPanel();
/*  303 */       this.PanelInferior.setLayout(new GridBagLayout());
/*  304 */       this.PanelInferior.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
/*  305 */       this.PanelInferior.add(this.jLabel, gridBagConstraints);
/*  306 */       this.PanelInferior.add(getJTextField4(), gridBagConstraints1);
/*  307 */       this.PanelInferior.add(getListar(), gridBagConstraints10);
/*  308 */       this.PanelInferior.add(getAyudaListadoLibre(), gridBagConstraints31);
/*      */     }
/*  310 */     return this.PanelInferior;
/*      */   }
/*      */ 
/*      */   private JTextField getJTextField4()
/*      */   {
/*  319 */     if (this.jTextField4 == null) {
/*  320 */       this.jTextField4 = new JTextField();
/*  321 */       this.jTextField4.setToolTipText("Referencia, RefProv, Descripcion, Proveedor, f_compra, f_venta, coste,pvp, pvr");
/*      */     }
/*  323 */     return this.jTextField4;
/*      */   }
/*      */ 
/*      */   private JComboBox getJComboBox1()
/*      */   {
/*  332 */     if (this.jComboBox1 == null) {
/*  333 */       this.jComboBox1 = new JComboBox(new String[] { "Stock por familia", "Stock por proveedor", "Stock total", "Comprado por familia", "Comprado por proveedor", "Comprado total", "Vendido por familia", "Vendido por proveedor", "Vendido total", "Total por familia", "Total por proveedor" });
/*      */ 
/*  337 */       this.jComboBox1.setSelectedIndex(0);
/*  338 */       this.jComboBox1.addActionListener(new ActionListener()
/*      */       {
/*      */         public void actionPerformed(ActionEvent e) {
/*  341 */           ListarProductosOldJInternalFrame.this.tipoCambiado(ListarProductosOldJInternalFrame.this.jComboBox1.getSelectedIndex());
/*      */         }
/*      */       });
/*      */     }
/*  345 */     return this.jComboBox1;
/*      */   }
/*      */ 
/*      */   private JTextField getJTextField1()
/*      */   {
/*  354 */     if (this.jTextField1 == null) {
/*  355 */       this.jTextField1 = new JTextField();
/*      */     }
/*  357 */     return this.jTextField1;
/*      */   }
/*      */ 
/*      */   private JTextField getJTextField2()
/*      */   {
/*  366 */     if (this.jTextField2 == null) {
/*  367 */       this.jTextField2 = new JTextField();
/*      */     }
/*  369 */     return this.jTextField2;
/*      */   }
/*      */ 
/*      */   private JTextField getJTextField3()
/*      */   {
/*  378 */     if (this.jTextField3 == null) {
/*  379 */       this.jTextField3 = new JTextField();
/*      */     }
/*  381 */     this.jTextField3.setText(fechaHoy());
/*  382 */     return this.jTextField3;
/*      */   }
/*      */ 
/*      */   private JButton getListar()
/*      */   {
/*  391 */     if (this.Listar == null) {
/*  392 */       this.Listar = new JButton();
/*  393 */       this.Listar.setText("Listar");
/*  394 */       this.Listar.addActionListener(new ActionListener()
/*      */       {
/*      */         public void actionPerformed(ActionEvent e) {
/*  397 */           String[] cadena = ListarProductosOldJInternalFrame.this.crearCondicion();
/*  398 */           if (!cadena[0].equals("ERROR")) {
/*  399 */             ListarProductosOldJInternalFrame.this.realizarListado(cadena[0], cadena[1]);
/*      */           }
/*      */         }
/*      */       });
/*      */     }
/*  404 */     return this.Listar;
/*      */   }
/*      */ 
/*      */   private JCheckBox getJCheckBox1()
/*      */   {
/*  413 */     if (this.jCheckBox1 == null) {
/*  414 */       this.jCheckBox1 = new JCheckBox();
/*  415 */       this.jCheckBox1.setText("Ref.Proveedor");
/*  416 */       this.jCheckBox1.setHorizontalAlignment(2);
/*  417 */       this.jCheckBox1.setSelected(true);
/*      */     }
/*  419 */     return this.jCheckBox1;
/*      */   }
/*      */ 
/*      */   private JCheckBox getJCheckBox2()
/*      */   {
/*  428 */     if (this.jCheckBox2 == null) {
/*  429 */       this.jCheckBox2 = new JCheckBox();
/*  430 */       this.jCheckBox2.setText("Descripción");
/*  431 */       this.jCheckBox2.setHorizontalAlignment(2);
/*  432 */       this.jCheckBox2.setHorizontalTextPosition(11);
/*  433 */       this.jCheckBox2.setSelected(true);
/*      */     }
/*  435 */     return this.jCheckBox2;
/*      */   }
/*      */ 
/*      */   private JCheckBox getJCheckBox3()
/*      */   {
/*  444 */     if (this.jCheckBox3 == null) {
/*  445 */       this.jCheckBox3 = new JCheckBox();
/*  446 */       this.jCheckBox3.setHorizontalAlignment(2);
/*  447 */       this.jCheckBox3.setSelected(true);
/*  448 */       this.jCheckBox3.setText("Proveedor");
/*      */     }
/*  450 */     return this.jCheckBox3;
/*      */   }
/*      */ 
/*      */   private JCheckBox getJCheckBox4()
/*      */   {
/*  459 */     if (this.jCheckBox4 == null) {
/*  460 */       this.jCheckBox4 = new JCheckBox();
/*  461 */       this.jCheckBox4.setText("Fecha compra");
/*  462 */       this.jCheckBox4.setHorizontalAlignment(2);
/*  463 */       this.jCheckBox4.setSelected(true);
/*      */     }
/*  465 */     return this.jCheckBox4;
/*      */   }
/*      */ 
/*      */   private JCheckBox getJCheckBox5()
/*      */   {
/*  474 */     if (this.jCheckBox5 == null) {
/*  475 */       this.jCheckBox5 = new JCheckBox();
/*  476 */       this.jCheckBox5.setHorizontalAlignment(2);
/*  477 */       this.jCheckBox5.setSelected(true);
/*  478 */       this.jCheckBox5.setText("Fecha venta");
/*      */     }
/*  480 */     return this.jCheckBox5;
/*      */   }
/*      */ 
/*      */   private JCheckBox getJCheckBox6()
/*      */   {
/*  489 */     if (this.jCheckBox6 == null) {
/*  490 */       this.jCheckBox6 = new JCheckBox();
/*  491 */       this.jCheckBox6.setHorizontalAlignment(2);
/*  492 */       this.jCheckBox6.setSelected(true);
/*  493 */       this.jCheckBox6.setText("Coste");
/*      */     }
/*  495 */     return this.jCheckBox6;
/*      */   }
/*      */ 
/*      */   private JCheckBox getJCheckBox7()
/*      */   {
/*  504 */     if (this.jCheckBox7 == null) {
/*  505 */       this.jCheckBox7 = new JCheckBox();
/*  506 */       this.jCheckBox7.setText("P.V.P.");
/*  507 */       this.jCheckBox7.setHorizontalAlignment(2);
/*  508 */       this.jCheckBox7.setSelected(true);
/*      */     }
/*  510 */     return this.jCheckBox7;
/*      */   }
/*      */ 
/*      */   private JCheckBox getJCheckBox8()
/*      */   {
/*  519 */     if (this.jCheckBox8 == null) {
/*  520 */       this.jCheckBox8 = new JCheckBox();
/*  521 */       this.jCheckBox8.setHorizontalAlignment(2);
/*  522 */       this.jCheckBox8.setSelected(true);
/*  523 */       this.jCheckBox8.setText("Precio final");
/*      */     }
/*  525 */     return this.jCheckBox8;
/*      */   }
/*      */ 
/*      */   private void iniGrupoBotones() {
/*  529 */     if (this.GrupoBotones == null) {
/*  530 */       this.GrupoBotones = new ButtonGroup();
/*  531 */       this.GrupoBotones.add(this.ord_referencia);
/*  532 */       this.GrupoBotones.add(this.ord_f_compra);
/*  533 */       this.GrupoBotones.add(this.ord_f_venta);
/*      */     }
/*      */   }
/*      */ 
/*      */   private JRadioButton getOrd_referencia()
/*      */   {
/*  543 */     if (this.ord_referencia == null) {
/*  544 */       this.ord_referencia = new JRadioButton();
/*  545 */       this.ord_referencia.setSelected(true);
/*      */     }
/*  547 */     return this.ord_referencia;
/*      */   }
/*      */ 
/*      */   private JRadioButton getOrd_f_compra()
/*      */   {
/*  556 */     if (this.ord_f_compra == null) {
/*  557 */       this.ord_f_compra = new JRadioButton();
/*      */     }
/*  559 */     return this.ord_f_compra;
/*      */   }
/*      */ 
/*      */   private JRadioButton getOrd_f_venta()
/*      */   {
/*  568 */     if (this.ord_f_venta == null) {
/*  569 */       this.ord_f_venta = new JRadioButton();
/*      */     }
/*  571 */     return this.ord_f_venta;
/*      */   }
/*      */ 
/*      */   private JButton getAyudaListadoLibre()
/*      */   {
/*  580 */     if (this.AyudaListadoLibre == null) {
/*  581 */       this.AyudaListadoLibre = new JButton();
/*  582 */       this.AyudaListadoLibre.setText("?");
/*  583 */       this.AyudaListadoLibre.addActionListener(new ActionListener()
/*      */       {
/*      */         public void actionPerformed(ActionEvent e) {
/*  586 */           ListarProductosOldJInternalFrame.this.muestraAyudaLibre();
/*      */         }
/*      */       });
/*      */     }
/*  590 */     return this.AyudaListadoLibre;
/*      */   }
/*      */ 
/*      */   private void tipoCambiado(int opcion)
/*      */   {
/*  604 */     this.jTextField1.setText("");
/*  605 */     this.jTextField2.setText("");
/*      */ 
/*  607 */     this.jTextField4.setText("");
/*  608 */     switch (opcion) {
/*      */     case 0:
/*  610 */       this.jLabel5.setText("Familia");
/*  611 */       this.jTextField1.setEnabled(true);
/*  612 */       this.jLabel6.setText("");
/*  613 */       this.jTextField2.setEnabled(false);
/*  614 */       this.jLabel7.setText("Fin");
/*  615 */       this.jTextField3.setEnabled(true);
/*  616 */       break;
/*      */     case 1:
/*  618 */       this.jLabel5.setText("Proveedor");
/*  619 */       this.jTextField1.setEnabled(true);
/*  620 */       this.jLabel6.setText("");
/*  621 */       this.jTextField2.setEnabled(false);
/*  622 */       this.jLabel7.setText("Fin");
/*  623 */       this.jTextField3.setEnabled(true);
/*  624 */       break;
/*      */     case 2:
/*  626 */       this.jLabel5.setText("");
/*  627 */       this.jTextField1.setEnabled(false);
/*  628 */       this.jLabel6.setText("");
/*  629 */       this.jTextField2.setEnabled(false);
/*  630 */       this.jLabel7.setText("Fin");
/*  631 */       this.jTextField3.setEnabled(true);
/*  632 */       break;
/*      */     case 3:
/*  634 */       this.jLabel5.setText("Familia");
/*  635 */       this.jTextField1.setEnabled(true);
/*  636 */       this.jLabel6.setText("Inicio");
/*  637 */       this.jTextField2.setEnabled(true);
/*  638 */       this.jLabel7.setText("Fin");
/*  639 */       this.jTextField3.setEnabled(true);
/*  640 */       break;
/*      */     case 4:
/*  642 */       this.jLabel5.setText("Proveedor");
/*  643 */       this.jTextField1.setEnabled(true);
/*  644 */       this.jLabel6.setText("Inicio");
/*  645 */       this.jTextField2.setEnabled(true);
/*  646 */       this.jLabel7.setText("Fin");
/*  647 */       this.jTextField3.setEnabled(true);
/*  648 */       break;
/*      */     case 5:
/*  650 */       this.jLabel5.setText("");
/*  651 */       this.jTextField1.setEnabled(false);
/*  652 */       this.jLabel6.setText("Inicio");
/*  653 */       this.jTextField2.setEnabled(true);
/*  654 */       this.jLabel7.setText("Fin");
/*  655 */       this.jTextField3.setEnabled(true);
/*  656 */       break;
/*      */     case 6:
/*  658 */       this.jLabel5.setText("Familia");
/*  659 */       this.jTextField1.setEnabled(true);
/*  660 */       this.jLabel6.setText("Inicio");
/*  661 */       this.jTextField2.setEnabled(true);
/*  662 */       this.jLabel7.setText("Fin");
/*  663 */       this.jTextField3.setEnabled(true);
/*  664 */       break;
/*      */     case 7:
/*  666 */       this.jLabel5.setText("Proveedor");
/*  667 */       this.jTextField1.setEnabled(true);
/*  668 */       this.jLabel6.setText("Inicio");
/*  669 */       this.jTextField2.setEnabled(true);
/*  670 */       this.jLabel7.setText("Fin");
/*  671 */       this.jTextField3.setEnabled(true);
/*  672 */       break;
/*      */     case 8:
/*  674 */       this.jLabel5.setText("");
/*  675 */       this.jTextField1.setEnabled(false);
/*  676 */       this.jLabel6.setText("Inicio");
/*  677 */       this.jTextField2.setEnabled(true);
/*  678 */       this.jLabel7.setText("Fin");
/*  679 */       this.jTextField3.setEnabled(true);
/*  680 */       break;
/*      */     case 9:
/*  682 */       this.jLabel5.setText("Familia");
/*  683 */       this.jTextField1.setEnabled(true);
/*  684 */       this.jLabel6.setText("");
/*  685 */       this.jTextField2.setEnabled(false);
/*  686 */       this.jLabel7.setText("");
/*  687 */       this.jTextField3.setEnabled(false);
/*  688 */       break;
/*      */     case 10:
/*  690 */       this.jLabel5.setText("Proveedor");
/*  691 */       this.jTextField1.setEnabled(true);
/*  692 */       this.jLabel6.setText("");
/*  693 */       this.jTextField2.setEnabled(false);
/*  694 */       this.jLabel7.setText("");
/*  695 */       this.jTextField3.setEnabled(false);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void muestraAyudaLibre()
/*      */   {
/*  701 */     dialogo_aviso("Nombre de los campos en la base de datos:\nReferencia(*)  -> Referencia\nRef.Proveedor  -> RefProv\nDescripción    -> Descripcion\nProveedor      -> Proveedor\nFecha compra(*)-> f_compra\nFecha venta(*) -> f_venta\nCoste          -> coste\nP.V.P.         -> pvp\nPrecio venta   -> pvr\n* Campos por los que se puede ordenar");
/*      */   }
/*      */ 
/*      */   private String[] crearCondicion()
/*      */   {
/*  720 */     String condicion = "";
/*      */     String titulo;
/*  722 */     if (!this.jTextField4.getText().equals(""))
/*      */     {
/*  724 */       titulo = "Listado: condición de libre configuración";
/*  725 */       condicion = this.jTextField4.getText();
/*      */     }
/*      */     else
/*      */     {
/*  734 */       String orden = "ORDER BY Referencia";
/*  735 */       if (this.ord_f_compra.isSelected()) {
/*  736 */         orden = "ORDER BY f_compra";
/*      */       }
/*  738 */       if (this.ord_f_venta.isSelected()) {
/*  739 */         orden = "ORDER BY f_venta";
/*      */       }
/*      */ 
/*  748 */       titulo = (String)this.jComboBox1.getSelectedItem();
/*      */       Comprobar_dato dato;
/*      */       Comprobar_dato dato1;
/*      */       Comprobar_dato dato2;
/*  749 */       switch (this.jComboBox1.getSelectedIndex()) {
/*      */       case 0:
/*  751 */         dato = new Comprobar_dato(this.jTextField1.getText());
/*  752 */         dato1 = new Comprobar_dato(this.jTextField3.getText());
/*  753 */         if ((dato.es_entero().equals("SI")) && (dato1.es_fecha().equals(this.jTextField3.getText()))) {
/*  754 */           titulo = titulo + ": " + cualNombre(this.jTextField1.getText(), 0) + " a fecha " + this.jTextField3.getText();
/*  755 */           long fam_l = Long.parseLong(this.jTextField1.getText()) * 1000000L;
/*  756 */           condicion = "WHERE (Referencia BETWEEN " + fam_l + " AND " + (fam_l + 999999L) + ") AND f_compra<'" + this.jTextField3.getText() + "' AND (f_venta ='1960-01-01' OR f_venta>'" + this.jTextField3.getText() + "')" + orden;
/*      */         }
/*      */         else
/*      */         {
/*  760 */           dialogo_aviso("Error en familia o fecha");
/*      */         }
/*  762 */         break;
/*      */       case 1:
/*  765 */         dato = new Comprobar_dato(this.jTextField1.getText());
/*  766 */         dato1 = new Comprobar_dato(this.jTextField3.getText());
/*  767 */         if ((dato.es_entero().equals("SI")) && (dato1.es_fecha().equals(this.jTextField3.getText()))) {
/*  768 */           titulo = titulo + ": " + cualNombre(this.jTextField1.getText(), 1) + " a fecha " + this.jTextField3.getText();
/*  769 */           int prov_l = Integer.parseInt(this.jTextField1.getText());
/*  770 */           condicion = "WHERE (Proveedor=" + prov_l + ") AND f_compra<'" + this.jTextField3.getText() + "' AND (f_venta ='1960-01-01' OR f_venta>'" + this.jTextField3.getText() + "')" + orden;
/*      */         }
/*      */         else {
/*  773 */           dialogo_aviso("Error en proveedor o fecha");
/*      */         }
/*  775 */         break;
/*      */       case 2:
/*  778 */         dato1 = new Comprobar_dato(this.jTextField3.getText());
/*  779 */         if (dato1.es_fecha().equals(this.jTextField3.getText())) {
/*  780 */           titulo = titulo + " a fecha " + this.jTextField3.getText();
/*  781 */           condicion = "WHERE f_compra<'" + this.jTextField3.getText() + "' AND (f_venta ='1960-01-01' OR f_venta>'" + this.jTextField3.getText() + "')" + orden;
/*      */         } else {
/*  783 */           dialogo_aviso("Error en fecha");
/*      */         }
/*  785 */         break;
/*      */       case 3:
/*  788 */         dato = new Comprobar_dato(this.jTextField1.getText());
/*  789 */         dato1 = new Comprobar_dato(this.jTextField2.getText());
/*  790 */         dato2 = new Comprobar_dato(this.jTextField3.getText());
/*  791 */         if ((dato.es_entero().equals("SI")) && (dato1.es_fecha().equals(this.jTextField2.getText())) && (dato2.es_fecha().equals(this.jTextField3.getText()))) {
/*  792 */           titulo = titulo + ": " + cualNombre(this.jTextField1.getText(), 0) + " entre " + this.jTextField2.getText() + " y " + this.jTextField3.getText();
/*  793 */           long fam_l = Long.parseLong(this.jTextField1.getText()) * 1000000L;
/*  794 */           condicion = "WHERE (Referencia BETWEEN " + fam_l + " AND " + (fam_l + 999999L) + ") AND (f_compra >='" + this.jTextField2.getText() + "' AND f_compra<='" + this.jTextField3.getText() + "')" + orden;
/*      */         }
/*      */         else
/*      */         {
/*  798 */           dialogo_aviso("Error en familia o fecha");
/*      */         }
/*  800 */         break;
/*      */       case 4:
/*  803 */         dato = new Comprobar_dato(this.jTextField1.getText());
/*  804 */         dato1 = new Comprobar_dato(this.jTextField2.getText());
/*  805 */         dato2 = new Comprobar_dato(this.jTextField3.getText());
/*  806 */         if ((dato.es_entero().equals("SI")) && (dato1.es_fecha().equals(this.jTextField2.getText())) && (dato2.es_fecha().equals(this.jTextField3.getText()))) {
/*  807 */           titulo = titulo + ": " + cualNombre(this.jTextField1.getText(), 1) + " entre " + this.jTextField2.getText() + " y " + this.jTextField3.getText();
/*  808 */           int prov_l = Integer.parseInt(this.jTextField1.getText());
/*  809 */           condicion = "WHERE (Proveedor=" + prov_l + ") AND (f_compra >='" + this.jTextField2.getText() + "' AND f_compra<='" + this.jTextField3.getText() + "')" + orden;
/*      */         }
/*      */         else
/*      */         {
/*  813 */           dialogo_aviso("Error en proveedor o fecha");
/*      */         }
/*  815 */         break;
/*      */       case 5:
/*  818 */         dato1 = new Comprobar_dato(this.jTextField2.getText());
/*  819 */         dato2 = new Comprobar_dato(this.jTextField3.getText());
/*  820 */         if ((dato1.es_fecha().equals(this.jTextField2.getText())) && (dato2.es_fecha().equals(this.jTextField3.getText()))) {
/*  821 */           titulo = titulo + " entre " + this.jTextField2.getText() + " y " + this.jTextField3.getText();
/*  822 */           condicion = "WHERE f_compra >='" + this.jTextField2.getText() + "' AND f_compra<='" + this.jTextField3.getText() + "'" + orden;
/*      */         }
/*      */         else {
/*  825 */           dialogo_aviso("Error en fecha");
/*      */         }
/*  827 */         break;
/*      */       case 6:
/*  830 */         dato = new Comprobar_dato(this.jTextField1.getText());
/*  831 */         dato1 = new Comprobar_dato(this.jTextField2.getText());
/*  832 */         dato2 = new Comprobar_dato(this.jTextField3.getText());
/*  833 */         if ((dato.es_entero().equals("SI")) && (dato1.es_fecha().equals(this.jTextField2.getText())) && (dato2.es_fecha().equals(this.jTextField3.getText()))) {
/*  834 */           titulo = titulo + ": " + cualNombre(this.jTextField1.getText(), 0) + " entre " + this.jTextField2.getText() + " y " + this.jTextField3.getText();
/*  835 */           long fam_l = Long.parseLong(this.jTextField1.getText()) * 1000000L;
/*  836 */           condicion = "WHERE (Referencia BETWEEN " + fam_l + " AND " + (fam_l + 999999L) + ") AND (f_venta >='" + this.jTextField2.getText() + "' AND f_venta<='" + this.jTextField3.getText() + "')" + orden;
/*      */         }
/*      */         else
/*      */         {
/*  840 */           dialogo_aviso("Error en familia o fecha");
/*      */         }
/*  842 */         break;
/*      */       case 7:
/*  845 */         dato = new Comprobar_dato(this.jTextField1.getText());
/*  846 */         dato1 = new Comprobar_dato(this.jTextField2.getText());
/*  847 */         dato2 = new Comprobar_dato(this.jTextField3.getText());
/*  848 */         if ((dato.es_entero().equals("SI")) && (dato1.es_fecha().equals(this.jTextField2.getText())) && (dato2.es_fecha().equals(this.jTextField3.getText()))) {
/*  849 */           titulo = titulo + ": " + cualNombre(this.jTextField1.getText(), 1) + " entre " + this.jTextField2.getText() + " y " + this.jTextField3.getText();
/*  850 */           int prov_l = Integer.parseInt(this.jTextField1.getText());
/*  851 */           condicion = "WHERE (Proveedor=" + prov_l + ") AND (f_venta >='" + this.jTextField2.getText() + "' AND f_venta <='" + this.jTextField3.getText() + "')" + orden;
/*      */         }
/*      */         else
/*      */         {
/*  855 */           dialogo_aviso("Error en proveedor o fecha");
/*      */         }
/*  857 */         break;
/*      */       case 8:
/*  860 */         dato1 = new Comprobar_dato(this.jTextField2.getText());
/*  861 */         dato2 = new Comprobar_dato(this.jTextField3.getText());
/*  862 */         if ((dato1.es_fecha().equals(this.jTextField2.getText())) && (dato2.es_fecha().equals(this.jTextField3.getText()))) {
/*  863 */           titulo = titulo + " entre " + this.jTextField2.getText() + " y " + this.jTextField3.getText();
/*  864 */           condicion = "WHERE f_venta >='" + this.jTextField2.getText() + "' AND f_venta<='" + this.jTextField3.getText() + "'" + orden;
/*      */         }
/*      */         else {
/*  867 */           dialogo_aviso("Error en fecha");
/*      */         }
/*  869 */         break;
/*      */       case 9:
/*  872 */         dato = new Comprobar_dato(this.jTextField1.getText());
/*  873 */         if (dato.es_entero().equals("SI")) {
/*  874 */           long fam_l = Long.parseLong(this.jTextField1.getText()) * 1000000L;
/*  875 */           titulo = titulo + ": " + cualNombre(this.jTextField1.getText(), 0);
/*  876 */           condicion = "WHERE (Referencia BETWEEN " + fam_l + " AND " + (fam_l + 999999L) + ")" + orden;
/*      */         } else {
/*  878 */           dialogo_aviso("Error en familia");
/*      */         }
/*  880 */         break;
/*      */       case 10:
/*  883 */         dato = new Comprobar_dato(this.jTextField1.getText());
/*  884 */         if (dato.es_entero().equals("SI")) {
/*  885 */           titulo = titulo + ": " + cualNombre(this.jTextField1.getText(), 1);
/*  886 */           int prov_l = Integer.parseInt(this.jTextField1.getText());
/*  887 */           condicion = "WHERE (Proveedor=" + prov_l + ")" + orden;
/*      */         } else {
/*  889 */           dialogo_aviso("Error en proveedor");
/*      */         }
/*      */         break;
/*      */       }
/*      */     }
/*  894 */     if (!condicion.equals("")) {
/*  895 */       return new String[] { condicion, titulo };
/*      */     }
/*  897 */     return new String[] { "ERROR", "" };
/*      */   }
/*      */ 
/*      */   private void realizarListado(String condicion, String titulo)
/*      */   {
/*  908 */     if (condicion.equals("ERROR")) {
/*  909 */       return;
/*      */     }
/*  911 */     Vector listado = null;
/*  912 */     DecimalFormatSymbols formato = new DecimalFormatSymbols();
/*  913 */     formato.setDecimalSeparator(',');
/*  914 */     formato.setPerMill('.');
/*  915 */     DecimalFormat fn = new DecimalFormat("#,###,##0.00", formato);
/*  916 */     long s_piezas = 0L;
/*  917 */     double s_coste = 0.0D; double s_pvp = 0.0D; double s_pvr = 0.0D;
/*      */ 
/*  920 */     String linea = "Referencia  ";
/*  921 */     if (this.jCheckBox1.getSelectedObjects() != null) {
/*  922 */       linea = linea + "Ref.Provee.       ";
/*      */     }
/*  924 */     if (this.jCheckBox2.getSelectedObjects() != null) {
/*  925 */       linea = linea + "               Descripción              ";
/*      */     }
/*  927 */     if (this.jCheckBox3.getSelectedObjects() != null) {
/*  928 */       linea = linea + "Prov. ";
/*      */     }
/*  930 */     if (this.jCheckBox4.getSelectedObjects() != null) {
/*  931 */       linea = linea + "f.compra   ";
/*      */     }
/*  933 */     if (this.jCheckBox5.getSelectedObjects() != null) {
/*  934 */       linea = linea + " f.venta    ";
/*      */     }
/*  936 */     if (this.jCheckBox6.getSelectedObjects() != null) {
/*  937 */       linea = linea + "     coste     ";
/*      */     }
/*  939 */     if (this.jCheckBox7.getSelectedObjects() != null) {
/*  940 */       linea = linea + "     p.v.p.    ";
/*      */     }
/*  942 */     if (this.jCheckBox8.getSelectedObjects() != null)
/*  943 */       linea = linea + "  p.v.final";
/*      */     try
/*      */     {
/*  946 */       this.res = this.con.getRes("SELECT COUNT(*) FROM productos " + condicion);
/*  947 */       int lineas = this.res.next() ? this.res.getInt(1) : 0;
/*  948 */       listado = new Vector(lineas + 3, 5);
/*  949 */       listado.addElement(new String(linea + "\n"));
/*  950 */       linea = "";
/*  951 */       this.res = this.con.getRes("SELECT * FROM productos " + condicion);
/*  952 */       String r = "                                         ";
/*  953 */       while (this.res.next())
/*      */       {
/*      */         String f_v;
/*      */        
/*  954 */         if (this.res.getString(6).equals("1960-01-01")) {
/*  955 */           f_v = "          ";
/*      */         } else {
/*  957 */           f_v = this.res.getString(6);
/*  958 */           f_v = f_v.substring(8) + "-" + f_v.substring(5, 7) + "-" + f_v.substring(0, 4);
/*      */         }
/*  960 */         String f_c = this.res.getString(5);
/*  961 */         f_c = f_c.substring(8) + "-" + f_c.substring(5, 7) + "-" + f_c.substring(0, 4);
/*  962 */         String re = this.res.getString(1);
/*  963 */         if (re.length() == 7) {
/*  964 */           re = "0" + re;
/*      */         }
/*  966 */         linea = re + " ";
/*  967 */         if (this.jCheckBox1.getSelectedObjects() != null) {
/*  968 */           linea = linea + r.substring(0, 19 - this.res.getString(2).length()) + this.res.getString(2) + " ";
/*      */         }
/*  970 */         if (this.jCheckBox2.getSelectedObjects() != null) {
/*  971 */           linea = linea + this.res.getString(3) + r.substring(0, 41 - this.res.getString(3).length());
/*      */         }
/*  973 */         if (this.jCheckBox3.getSelectedObjects() != null) {
/*  974 */           linea = linea + this.res.getString(4) + " ";
/*      */         }
/*  976 */         if (this.jCheckBox4.getSelectedObjects() != null) {
/*  977 */           linea = linea + f_c + " ";
/*      */         }
/*  979 */         if (this.jCheckBox5.getSelectedObjects() != null) {
/*  980 */           linea = linea + f_v;
/*      */         }
/*  982 */         if (this.jCheckBox6.getSelectedObjects() != null) {
/*  983 */           linea = linea + r.substring(0, 14 - fn.format(this.res.getDouble(7)).length()) + fn.format(this.res.getDouble(7));
/*      */         }
/*  985 */         if (this.jCheckBox7.getSelectedObjects() != null) {
/*  986 */           linea = linea + r.substring(0, 14 - fn.format(this.res.getDouble(8)).length()) + fn.format(this.res.getDouble(8));
/*      */         }
/*  988 */         if (this.jCheckBox8.getSelectedObjects() != null) {
/*  989 */           linea = linea + r.substring(0, 14 - fn.format(this.res.getDouble(9)).length()) + fn.format(this.res.getDouble(9));
/*      */         }
/*  991 */         linea = linea + "\n";
/*  992 */         listado.addElement(new String(linea));
/*  993 */         s_piezas += 1L;
/*  994 */         s_coste += this.res.getDouble(7);
/*  995 */         s_pvp += this.res.getDouble(8);
/*  996 */         s_pvr += this.res.getDouble(9);
/*      */       }
/*  998 */       linea = "\n   Totales:      piezas: " + String.valueOf(s_piezas);
/*  999 */       if (this.jCheckBox6.getSelectedObjects() != null) {
/* 1000 */         linea = linea + "          Costes: " + fn.format(s_coste);
/*      */       }
/* 1002 */       if (this.jCheckBox7.getSelectedObjects() != null) {
/* 1003 */         linea = linea + "          p.v.p.: " + fn.format(s_pvp);
/*      */       }
/* 1005 */       if (this.jCheckBox8.getSelectedObjects() != null) {
/* 1006 */         linea = linea + "          Ventas: " + fn.format(s_pvr);
/*      */       }
/* 1008 */       linea = linea + "\n";
/* 1009 */       listado.addElement(new String(linea));
/*      */     } catch (SQLException exc) {
/* 1011 */       dialogo_aviso("Error SQL al crear el listado:\n" + exc.getMessage());
/*      */     }
/* 1013 */     if (listado != null) {
/* 1014 */       Listado ListaEnMarco = new Listado(titulo, listado);
/* 1015 */       mostrarMarcoExterno(ListaEnMarco);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void dialogo_aviso(String texto) {
/* 1020 */     JOptionPane.showMessageDialog(getContentPane(), texto, "Error", 1);
/*      */   }
/*      */ 
/*      */   private void mostrarMarcoExterno(JFrame frame) {
/* 1024 */     frame.validate();
/*      */ 
/* 1026 */     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/* 1027 */     Dimension frameSize = frame.getSize();
/* 1028 */     if (frameSize.height > screenSize.height) {
/* 1029 */       frameSize.height = screenSize.height;
/*      */     }
/* 1031 */     if (frameSize.width > screenSize.width) {
/* 1032 */       frameSize.width = screenSize.width;
/*      */     }
/* 1034 */     frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
/*      */ 
/* 1036 */     frame.setVisible(true);
/*      */   }
/*      */ 
/*      */   private String cualNombre(String num, int x)
/*      */   {
/* 1048 */     String seleccion = "";
/*      */     try {
/* 1050 */       if (x == 0) {
/* 1051 */         seleccion = "familias";
/*      */       }
/* 1053 */       if (x == 1) {
/* 1054 */         seleccion = "proveedores";
/*      */       }
/* 1056 */       if ((x == 0) || (x == 1)) {
/* 1057 */         this.res = this.con.getRes("SELECT nombre FROM " + seleccion + " WHERE numero=" + Integer.parseInt(num));
/*      */ 
/* 1060 */         if (this.res.next())
/* 1061 */           seleccion = this.res.getString(1);
/*      */         else
/* 1063 */           seleccion = "No existe";
/*      */       }
/*      */     }
/*      */     catch (SQLException exc) {
/* 1067 */       return seleccion;
/*      */     }
/* 1069 */     return seleccion;
/*      */   }
/*      */ 
/*      */   private String fechaHoy()
/*      */   {
/* 1078 */     fecha_hoy fecha_a = new fecha_hoy();
/* 1079 */     String fecha = new String(fecha_a.dar_fecha_hoy());
/* 1080 */     return fecha;
/*      */   }
/*      */ 
/*      */   class Comprobar_dato
/*      */   {
/*      */     String dato;
/*      */ 
/*      */     public Comprobar_dato()
/*      */     {
/*      */     }
/*      */ 
/*      */     public Comprobar_dato(String a)
/*      */     {
/* 1108 */       this.dato = a;
/*      */     }
/*      */ 
/*      */     public String es_fecha() {
/*      */       try {
/* 1113 */         java.sql.Date b = java.sql.Date.valueOf(this.dato);
/* 1114 */         this.dato = b.toString();
/*      */       } catch (NumberFormatException exc) {
/* 1116 */         return exc.toString();
/*      */       } catch (StringIndexOutOfBoundsException exc) {
/* 1118 */         return exc.toString();
/*      */       } catch (IllegalArgumentException exc) {
/* 1120 */         return exc.toString();
/*      */       }
/* 1122 */       return this.dato;
/*      */     }
/*      */ 
/*      */     public String es_doble() {
/*      */       try {
/* 1127 */         new Double(this.dato).doubleValue();
/*      */       } catch (NumberFormatException exc) {
/* 1129 */         return exc.toString();
/*      */       }
/* 1131 */       return "SI";
/*      */     }
/*      */ 
/*      */     public String es_entero() {
/*      */       try {
/* 1136 */         Integer.parseInt(this.dato);
/*      */       } catch (NumberFormatException exc) {
/* 1138 */         return exc.toString();
/*      */       }
/* 1140 */       return "SI";
/*      */     }
/*      */ 
/*      */     public String es_long() {
/*      */       try {
/* 1145 */         Long.parseLong(this.dato);
/*      */       } catch (NumberFormatException exc) {
/* 1147 */         return exc.toString();
/*      */       }
/* 1149 */       return "SI";
/*      */     }
/*      */   }
/*      */ 
/*      */   class fecha_hoy
/*      */   {
/*      */     SimpleDateFormat formato;
/*      */     String Diafinal;
/*      */     java.util.Date DiaHoy;
/*      */ 
/*      */     public fecha_hoy()
/*      */     {
/* 1090 */       this.formato = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
/* 1091 */       this.DiaHoy = new java.util.Date();
/* 1092 */       this.Diafinal = this.formato.format(this.DiaHoy);
/*      */     }
/*      */ 
/*      */     public String dar_fecha_hoy() {
/* 1096 */       return this.Diafinal;
/*      */     }
/*      */   }
/*      */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.gui.ListarProductosOldJInternalFrame
 * JD-Core Version:    0.6.2
 */