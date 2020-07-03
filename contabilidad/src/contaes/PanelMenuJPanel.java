/*     */ package contaes;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Container;
/*     */ import java.awt.Font;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JPanel;
/*     */ import org.jdesktop.swingx.JXHyperlink;
/*     */ import org.jdesktop.swingx.JXTaskPane;
/*     */ import org.jdesktop.swingx.JXTaskPaneContainer;
/*     */ 
/*     */ public class PanelMenuJPanel extends JPanel
/*     */   implements ActionListener
/*     */ {
/*     */   private static final String SELECCIONAREMPRESA = "selempresa";
/*     */   private static final String GESTIONEMPRESAS = "gesempresas";
/*     */   private static final String ABRIREJERCICIO = "abrirejercicio";
/*     */   private static final String ASIENTOS = "asientos";
/*     */   private static final String ASIENTOSAUT = "asientosaut";
/*     */   private static final String CONSULTASDIARIO = "consultasdiario";
/*     */   private static final String CONCEPTOS = "conceptos";
/*     */   private static final String INTROFACTURAS = "facturas";
/*     */   private static final String GESTIONEMITIDAS = "gestionEmitidas";
/*     */   private static final String GESTIONRECIBIDAS = "gestionRecibidas";
/*     */   private static final String LISTAEMITIDAS = "emitidas";
/*     */   private static final String LISTARECIBIDAS = "recibidas";
/*     */   private static final String VENCIMIENTOS = "vencimientos";
/*     */   private static final String VENCIMIENTOSCOBRAR = "vencimientoscobrar";
/*     */   private static final String GESTIONCUENTAS = "cuentas";
/*     */   private static final String BALNIVELES = "niveles";
/*     */   private static final String PYG07 = "pyg07";
/*     */   private static final String BALANCE07 = "balance07";
/*     */   private static final String LIQUIDAFISCALIVA = "liquidacionfiscaliva";
/*     */   private static final String OPTERCEROS = "operacionesTerceros";
/*     */   private static final String TIPOSIVA = "tiposIVA";
/*     */   private static final String FORMASPAGO = "formasPago";
/*     */   private static final String PRODUCTOS = "productos";
/*     */   private static final String PEDIDOSPROVEEDORES = "pedidosproveedores";
/*     */   private static final String PEDIDOSCLIENTES = "pedidosclientes";
/*     */   private static final String GESTIONFACTURACION = "gestionfacturas";
/*     */   private static final String INTROFACTURACION = "introduccionfacturas";
/*     */   private static final String GESTIONFACTURACIONPROV = "gestionfacturasrecibidas";
/*     */   private static final String INTROFACTURACIONPROV = "introduccionfacturasrecibidas";
/*     */   private static final String LISTADOSALMACEN = "listadosalmacen";
/*     */   private static final String RESUMENMENSUALCLIENTES = "resumenclientes";
/*     */   private static final String RESUMENMENSUALPROVEEDORES = "resumenproveedores";
/*     */   private static final String DISTRIBUCIONPARTIDAS = "distribucionpartidas";
/*     */   private static final String ORIGENESDESTINOS = "origenesDestinos";
/*     */   private static final String RATIOS = "ratios";
/*     */   private static final String ALMACENES = "almacenes";
/*     */   private static final String MEDIOSPAGO = "mediospago";
/*     */   private static final String VENDEDORES = "vendedores";
/*     */   private static final String TERMINALVENTAS = "terminalventas";
/*     */   private static final String RESUMENDIARIO = "resumendiario";
/*     */   private static final String PAISES = "paises";
/*     */   private static final String CSB19 = "CSB19";
/*     */   private static final String CSB58 = "CSB58";
/*     */   private static final String CONTROLCENTRE = "controlcentre";
/*     */   private JButton jButton1;
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
/*     */   private JXHyperlink jXHyperlink24;
/*     */   private JXHyperlink jXHyperlink25;
/*     */   private JXHyperlink jXHyperlink26;
/*     */   private JXHyperlink jXHyperlink27;
/*     */   private JXHyperlink jXHyperlink28;
/*     */   private JXHyperlink jXHyperlink29;
/*     */   private JXHyperlink jXHyperlink3;
/*     */   private JXHyperlink jXHyperlink30;
/*     */   private JXHyperlink jXHyperlink31;
/*     */   private JXHyperlink jXHyperlink32;
/*     */   private JXHyperlink jXHyperlink33;
/*     */   private JXHyperlink jXHyperlink34;
/*     */   private JXHyperlink jXHyperlink35;
/*     */   private JXHyperlink jXHyperlink36;
/*     */   private JXHyperlink jXHyperlink37;
/*     */   private JXHyperlink jXHyperlink38;
/*     */   private JXHyperlink jXHyperlink39;
/*     */   private JXHyperlink jXHyperlink4;
/*     */   private JXHyperlink jXHyperlink40;
/*     */   private JXHyperlink jXHyperlink41;
/*     */   private JXHyperlink jXHyperlink42;
/*     */   private JXHyperlink jXHyperlink43;
/*     */   private JXHyperlink jXHyperlink5;
/*     */   private JXHyperlink jXHyperlink6;
/*     */   private JXHyperlink jXHyperlink7;
/*     */   private JXHyperlink jXHyperlink8;
/*     */   private JXHyperlink jXHyperlink9;
/*     */   private JXTaskPane jXTaskPane1;
/*     */   private JXTaskPane jXTaskPane2;
/*     */   private JXTaskPane jXTaskPane3;
/*     */   private JXTaskPane jXTaskPane4;
/*     */   private JXTaskPane jXTaskPane5;
/*     */   private JXTaskPane jXTaskPane6;
/*     */   private JXTaskPane jXTaskPane7;
/*     */   private JXTaskPane jXTaskPane8;
/*     */   private JXTaskPaneContainer jXTaskPaneContainer1;
/*     */ 
/*     */   public PanelMenuJPanel()
/*     */   {
/*  69 */     initComponents();
/*     */   }
/*     */ 
/*     */   public int[] saveState() {
/*  73 */     int[] statePanel = new int[8];
/*  74 */     statePanel[0] = (this.jXTaskPane1.isCollapsed() ? 0 : 1);
/*  75 */     statePanel[1] = (this.jXTaskPane2.isCollapsed() ? 0 : 1);
/*  76 */     statePanel[2] = (this.jXTaskPane3.isCollapsed() ? 0 : 1);
/*  77 */     statePanel[3] = (this.jXTaskPane4.isCollapsed() ? 0 : 1);
/*  78 */     statePanel[4] = (this.jXTaskPane5.isCollapsed() ? 0 : 1);
/*  79 */     statePanel[5] = (this.jXTaskPane6.isCollapsed() ? 0 : 1);
/*  80 */     statePanel[6] = (this.jXTaskPane7.isCollapsed() ? 0 : 1);
/*  81 */     statePanel[7] = (this.jXTaskPane8.isCollapsed() ? 0 : 1);
/*  82 */     return statePanel;
/*     */   }
/*     */ 
/*     */   public void readState(int[] statePanel)
/*     */   {
/*  93 */     this.jXTaskPane1.setCollapsed(statePanel[0] == 0);
/*  94 */     this.jXTaskPane2.setCollapsed(statePanel[1] == 0);
/*  95 */     this.jXTaskPane3.setCollapsed(statePanel[2] == 0);
/*  96 */     this.jXTaskPane4.setCollapsed(statePanel[3] == 0);
/*  97 */     this.jXTaskPane5.setCollapsed(statePanel[4] == 0);
/*  98 */     this.jXTaskPane6.setCollapsed(statePanel[5] == 0);
/*  99 */     this.jXTaskPane7.setCollapsed(statePanel[6] == 0);
/* 100 */     this.jXTaskPane8.setCollapsed(statePanel[7] == 0);
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/* 112 */     this.jXTaskPaneContainer1 = new JXTaskPaneContainer();
/* 113 */     this.jButton1 = new JButton();
/* 114 */     this.jXTaskPane1 = new JXTaskPane();
/* 115 */     this.jXHyperlink1 = new JXHyperlink();
/* 116 */     this.jXHyperlink2 = new JXHyperlink();
/* 117 */     this.jXHyperlink3 = new JXHyperlink();
/* 118 */     this.jXHyperlink4 = new JXHyperlink();
/* 119 */     this.jXHyperlink5 = new JXHyperlink();
/* 120 */     this.jXHyperlink41 = new JXHyperlink();
/* 121 */     this.jXTaskPane2 = new JXTaskPane();
/* 122 */     this.jXHyperlink27 = new JXHyperlink();
/* 123 */     this.jXHyperlink6 = new JXHyperlink();
/* 124 */     this.jXHyperlink7 = new JXHyperlink();
/* 125 */     this.jXHyperlink8 = new JXHyperlink();
/* 126 */     this.jXHyperlink9 = new JXHyperlink();
/* 127 */     this.jXTaskPane3 = new JXTaskPane();
/* 128 */     this.jXHyperlink10 = new JXHyperlink();
/* 129 */     this.jXHyperlink11 = new JXHyperlink();
/* 130 */     this.jXHyperlink12 = new JXHyperlink();
/* 131 */     this.jXHyperlink13 = new JXHyperlink();
/* 132 */     this.jXHyperlink14 = new JXHyperlink();
/* 133 */     this.jXHyperlink42 = new JXHyperlink();
/* 134 */     this.jXHyperlink43 = new JXHyperlink();
/* 135 */     this.jXTaskPane7 = new JXTaskPane();
/* 136 */     this.jXHyperlink20 = new JXHyperlink();
/* 137 */     this.jXHyperlink21 = new JXHyperlink();
/* 138 */     this.jXHyperlink22 = new JXHyperlink();
/* 139 */     this.jXHyperlink23 = new JXHyperlink();
/* 140 */     this.jXHyperlink24 = new JXHyperlink();
/* 141 */     this.jXHyperlink25 = new JXHyperlink();
/* 142 */     this.jXHyperlink26 = new JXHyperlink();
/* 143 */     this.jXTaskPane6 = new JXTaskPane();
/* 144 */     this.jXHyperlink30 = new JXHyperlink();
/* 145 */     this.jXHyperlink31 = new JXHyperlink();
/* 146 */     this.jXHyperlink32 = new JXHyperlink();
/* 147 */     this.jXHyperlink33 = new JXHyperlink();
/* 148 */     this.jXHyperlink34 = new JXHyperlink();
/* 149 */     this.jXTaskPane4 = new JXTaskPane();
/* 150 */     this.jXHyperlink15 = new JXHyperlink();
/* 151 */     this.jXHyperlink16 = new JXHyperlink();
/* 152 */     this.jXHyperlink17 = new JXHyperlink();
/* 153 */     this.jXHyperlink18 = new JXHyperlink();
/* 154 */     this.jXTaskPane5 = new JXTaskPane();
/* 155 */     this.jXHyperlink19 = new JXHyperlink();
/* 156 */     this.jXHyperlink28 = new JXHyperlink();
/* 157 */     this.jXHyperlink35 = new JXHyperlink();
/* 158 */     this.jXHyperlink29 = new JXHyperlink();
/* 159 */     this.jXHyperlink36 = new JXHyperlink();
/* 160 */     this.jXTaskPane8 = new JXTaskPane();
/* 161 */     this.jXHyperlink39 = new JXHyperlink();
/* 162 */     this.jXHyperlink40 = new JXHyperlink();
/* 163 */     this.jXHyperlink37 = new JXHyperlink();
/* 164 */     this.jXHyperlink38 = new JXHyperlink();
/*     */ 
/* 166 */     setBackground(new Color(69, 105, 170));
/*     */ 
/* 168 */     this.jXTaskPaneContainer1.setBackground(new Color(69, 105, 170));
/*     */ 
/* 170 */     this.jButton1.setFont(new Font("Lucida Grande", 1, 13));
/* 171 */     this.jButton1.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/cache.png")));
/* 172 */     this.jButton1.setText("Cuadro de mando");
/* 173 */     this.jButton1.setActionCommand("controlcentre");
/* 174 */     this.jButton1.setHorizontalAlignment(2);
/* 175 */     this.jButton1.addActionListener(this);
/* 176 */     this.jXTaskPaneContainer1.add(this.jButton1);
/*     */ 
/* 178 */     this.jXTaskPane1.setCollapsed(true);
/* 179 */     this.jXTaskPane1.setIcon(new ImageIcon(getClass().getResource("/almacen2/iconos/misc.png")));
/* 180 */     this.jXTaskPane1.setTitle("General");
/* 181 */     this.jXTaskPane1.setName("General");
/*     */ 
/* 183 */     this.jXHyperlink1.setClickedColor(new Color(0, 51, 255));
/* 184 */     this.jXHyperlink1.setText("Empresas");
/* 185 */     this.jXHyperlink1.setActionCommand("gesempresas");
/* 186 */     this.jXHyperlink1.addActionListener(this);
/* 187 */     this.jXTaskPane1.getContentPane().add(this.jXHyperlink1);
/*     */ 
/* 189 */     this.jXHyperlink2.setClickedColor(new Color(0, 51, 255));
/* 190 */     this.jXHyperlink2.setText("Ejercicios");
/* 191 */     this.jXHyperlink2.setActionCommand("abrirejercicio");
/* 192 */     this.jXHyperlink2.addActionListener(this);
/* 193 */     this.jXTaskPane1.getContentPane().add(this.jXHyperlink2);
/*     */ 
/* 195 */     this.jXHyperlink3.setClickedColor(new Color(0, 51, 255));
/* 196 */     this.jXHyperlink3.setText("Conceptos");
/* 197 */     this.jXHyperlink3.setActionCommand("conceptos");
/* 198 */     this.jXHyperlink3.addActionListener(this);
/* 199 */     this.jXTaskPane1.getContentPane().add(this.jXHyperlink3);
/*     */ 
/* 201 */     this.jXHyperlink4.setClickedColor(new Color(0, 51, 255));
/* 202 */     this.jXHyperlink4.setText("Tipos de IVA");
/* 203 */     this.jXHyperlink4.setActionCommand("tiposIVA");
/* 204 */     this.jXHyperlink4.addActionListener(this);
/* 205 */     this.jXTaskPane1.getContentPane().add(this.jXHyperlink4);
/*     */ 
/* 207 */     this.jXHyperlink5.setClickedColor(new Color(0, 51, 255));
/* 208 */     this.jXHyperlink5.setText("Formas de pago");
/* 209 */     this.jXHyperlink5.setActionCommand("formasPago");
/* 210 */     this.jXHyperlink5.addActionListener(this);
/* 211 */     this.jXTaskPane1.getContentPane().add(this.jXHyperlink5);
/*     */ 
/* 213 */     this.jXHyperlink41.setClickedColor(new Color(0, 51, 255));
/* 214 */     ResourceBundle bundle = ResourceBundle.getBundle("internationalization/Mensajes");
/* 215 */     this.jXHyperlink41.setText(bundle.getString("paises"));
/* 216 */     this.jXHyperlink41.setActionCommand("paises");
/* 217 */     this.jXHyperlink41.addActionListener(this);
/* 218 */     this.jXTaskPane1.getContentPane().add(this.jXHyperlink41);
/*     */ 
/* 220 */     this.jXTaskPaneContainer1.add(this.jXTaskPane1);
/*     */ 
/* 222 */     this.jXTaskPane2.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/D16.png")));
/* 223 */     this.jXTaskPane2.setTitle("Contabilidad");
/*     */ 
/* 225 */     this.jXHyperlink27.setClickedColor(new Color(0, 51, 255));
/* 226 */     this.jXHyperlink27.setText("Seleccionar empresa");
/* 227 */     this.jXHyperlink27.setActionCommand("selempresa");
/* 228 */     this.jXHyperlink27.addActionListener(this);
/* 229 */     this.jXTaskPane2.getContentPane().add(this.jXHyperlink27);
/*     */ 
/* 231 */     this.jXHyperlink6.setClickedColor(new Color(0, 51, 255));
/* 232 */     this.jXHyperlink6.setText("Asientos");
/* 233 */     this.jXHyperlink6.setActionCommand("asientos");
/* 234 */     this.jXHyperlink6.addActionListener(this);
/* 235 */     this.jXTaskPane2.getContentPane().add(this.jXHyperlink6);
/*     */ 
/* 237 */     this.jXHyperlink7.setClickedColor(new Color(0, 51, 255));
/* 238 */     this.jXHyperlink7.setText("Asientos modelo");
/* 239 */     this.jXHyperlink7.setActionCommand("asientosaut");
/* 240 */     this.jXHyperlink7.addActionListener(this);
/* 241 */     this.jXTaskPane2.getContentPane().add(this.jXHyperlink7);
/*     */ 
/* 243 */     this.jXHyperlink8.setClickedColor(new Color(0, 51, 255));
/* 244 */     this.jXHyperlink8.setText("Diario");
/* 245 */     this.jXHyperlink8.setActionCommand("consultasdiario");
/* 246 */     this.jXHyperlink8.addActionListener(this);
/* 247 */     this.jXTaskPane2.getContentPane().add(this.jXHyperlink8);
/*     */ 
/* 249 */     this.jXHyperlink9.setClickedColor(new Color(0, 51, 255));
/* 250 */     this.jXHyperlink9.setText("PGC y cuentas");
/* 251 */     this.jXHyperlink9.setActionCommand("cuentas");
/* 252 */     this.jXHyperlink9.addActionListener(this);
/* 253 */     this.jXTaskPane2.getContentPane().add(this.jXHyperlink9);
/*     */ 
/* 255 */     this.jXTaskPaneContainer1.add(this.jXTaskPane2);
/*     */ 
/* 257 */     this.jXTaskPane3.setCollapsed(true);
/* 258 */     this.jXTaskPane3.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/MlistFacturas.png")));
/* 259 */     this.jXTaskPane3.setTitle("Facturas contables");
/*     */ 
/* 261 */     this.jXHyperlink10.setClickedColor(new Color(0, 51, 255));
/* 262 */     this.jXHyperlink10.setText("Crear/Editar");
/* 263 */     this.jXHyperlink10.setActionCommand("facturas");
/* 264 */     this.jXHyperlink10.addActionListener(this);
/* 265 */     this.jXTaskPane3.getContentPane().add(this.jXHyperlink10);
/*     */ 
/* 267 */     this.jXHyperlink11.setClickedColor(new Color(0, 51, 255));
/* 268 */     this.jXHyperlink11.setText("Gestión Emitidas");
/* 269 */     this.jXHyperlink11.setActionCommand("gestionEmitidas");
/* 270 */     this.jXHyperlink11.addActionListener(this);
/* 271 */     this.jXTaskPane3.getContentPane().add(this.jXHyperlink11);
/*     */ 
/* 273 */     this.jXHyperlink12.setClickedColor(new Color(0, 51, 255));
/* 274 */     this.jXHyperlink12.setText("Gestión Recibidas");
/* 275 */     this.jXHyperlink12.setActionCommand("gestionRecibidas");
/* 276 */     this.jXHyperlink12.addActionListener(this);
/* 277 */     this.jXTaskPane3.getContentPane().add(this.jXHyperlink12);
/*     */ 
/* 279 */     this.jXHyperlink13.setClickedColor(new Color(0, 51, 255));
/* 280 */     this.jXHyperlink13.setText("Vencimientos a cobrar");
/* 281 */     this.jXHyperlink13.setActionCommand("vencimientoscobrar");
/* 282 */     this.jXHyperlink13.addActionListener(this);
/* 283 */     this.jXTaskPane3.getContentPane().add(this.jXHyperlink13);
/*     */ 
/* 285 */     this.jXHyperlink14.setClickedColor(new Color(0, 51, 255));
/* 286 */     this.jXHyperlink14.setText("Vencimientos a pagar");
/* 287 */     this.jXHyperlink14.setActionCommand("vencimientos");
/* 288 */     this.jXHyperlink14.addActionListener(this);
/* 289 */     this.jXTaskPane3.getContentPane().add(this.jXHyperlink14);
/*     */ 
/* 291 */     this.jXHyperlink42.setClickedColor(new Color(0, 51, 255));
/* 292 */     this.jXHyperlink42.setText("CSB19");
/* 293 */     this.jXHyperlink42.setToolTipText("Adeudos por domiciliaciones");
/* 294 */     this.jXHyperlink42.setActionCommand("CSB19");
/* 295 */     this.jXHyperlink42.addActionListener(this);
/* 296 */     this.jXTaskPane3.getContentPane().add(this.jXHyperlink42);
/*     */ 
/* 298 */     this.jXHyperlink43.setClickedColor(new Color(0, 51, 255));
/* 299 */     this.jXHyperlink43.setText("CSB58");
/* 300 */     this.jXHyperlink43.setToolTipText("Anticipo y gestión de cobro de créditos");
/* 301 */     this.jXHyperlink43.setActionCommand("CSB58");
/* 302 */     this.jXHyperlink43.addActionListener(this);
/* 303 */     this.jXTaskPane3.getContentPane().add(this.jXHyperlink43);
/*     */ 
/* 305 */     this.jXTaskPaneContainer1.add(this.jXTaskPane3);
/*     */ 
/* 307 */     this.jXTaskPane7.setCollapsed(true);
/* 308 */     this.jXTaskPane7.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/Masientos.png")));
/* 309 */     this.jXTaskPane7.setTitle("Informes contables");
/*     */ 
/* 311 */     this.jXHyperlink20.setClickedColor(new Color(0, 51, 255));
/* 312 */     this.jXHyperlink20.setText("Facturas emitidas");
/* 313 */     this.jXHyperlink20.setActionCommand("emitidas");
/* 314 */     this.jXHyperlink20.addActionListener(this);
/* 315 */     this.jXHyperlink20.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 317 */         PanelMenuJPanel.this.jXHyperlink20ActionPerformed(evt);
/*     */       }
/*     */     });
/* 320 */     this.jXTaskPane7.getContentPane().add(this.jXHyperlink20);
/*     */ 
/* 322 */     this.jXHyperlink21.setClickedColor(new Color(0, 51, 255));
/* 323 */     this.jXHyperlink21.setText("Facturas recibidas");
/* 324 */     this.jXHyperlink21.setActionCommand("recibidas");
/* 325 */     this.jXHyperlink21.addActionListener(this);
/* 326 */     this.jXTaskPane7.getContentPane().add(this.jXHyperlink21);
/*     */ 
/* 328 */     this.jXHyperlink22.setClickedColor(new Color(0, 51, 255));
/* 329 */     this.jXHyperlink22.setText("Operaciones con terceros");
/* 330 */     this.jXHyperlink22.setActionCommand("operacionesTerceros");
/* 331 */     this.jXHyperlink22.addActionListener(this);
/* 332 */     this.jXTaskPane7.getContentPane().add(this.jXHyperlink22);
/*     */ 
/* 334 */     this.jXHyperlink23.setClickedColor(new Color(0, 51, 255));
/* 335 */     this.jXHyperlink23.setText("Resumen de IVA");
/* 336 */     this.jXHyperlink23.setActionCommand("liquidacionfiscaliva");
/* 337 */     this.jXHyperlink23.addActionListener(this);
/* 338 */     this.jXTaskPane7.getContentPane().add(this.jXHyperlink23);
/*     */ 
/* 340 */     this.jXHyperlink24.setClickedColor(new Color(0, 51, 255));
/* 341 */     this.jXHyperlink24.setText("Sumas y saldos por niveles");
/* 342 */     this.jXHyperlink24.setActionCommand("niveles");
/* 343 */     this.jXHyperlink24.addActionListener(this);
/* 344 */     this.jXTaskPane7.getContentPane().add(this.jXHyperlink24);
/*     */ 
/* 346 */     this.jXHyperlink25.setClickedColor(new Color(0, 51, 255));
/* 347 */     this.jXHyperlink25.setText("Pérdidas y ganancias");
/* 348 */     this.jXHyperlink25.setActionCommand("pyg07");
/* 349 */     this.jXHyperlink25.addActionListener(this);
/* 350 */     this.jXTaskPane7.getContentPane().add(this.jXHyperlink25);
/*     */ 
/* 352 */     this.jXHyperlink26.setClickedColor(new Color(0, 51, 255));
/* 353 */     this.jXHyperlink26.setText("Balance de situación");
/* 354 */     this.jXHyperlink26.setActionCommand("balance07");
/* 355 */     this.jXHyperlink26.addActionListener(this);
/* 356 */     this.jXTaskPane7.getContentPane().add(this.jXHyperlink26);
/*     */ 
/* 358 */     this.jXTaskPaneContainer1.add(this.jXTaskPane7);
/*     */ 
/* 360 */     this.jXTaskPane6.setCollapsed(true);
/* 361 */     this.jXTaskPane6.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/Mlistado.png")));
/* 362 */     this.jXTaskPane6.setTitle("Informes");
/*     */ 
/* 364 */     this.jXHyperlink30.setClickedColor(new Color(0, 51, 255));
/* 365 */     this.jXHyperlink30.setText("Resumen mensual clientes");
/* 366 */     this.jXHyperlink30.setActionCommand("resumenclientes");
/* 367 */     this.jXHyperlink30.addActionListener(this);
/* 368 */     this.jXTaskPane6.getContentPane().add(this.jXHyperlink30);
/*     */ 
/* 370 */     this.jXHyperlink31.setClickedColor(new Color(0, 51, 255));
/* 371 */     this.jXHyperlink31.setText("Resumen mensual proveedores");
/* 372 */     this.jXHyperlink31.setActionCommand("resumenproveedores");
/* 373 */     this.jXHyperlink31.addActionListener(this);
/* 374 */     this.jXTaskPane6.getContentPane().add(this.jXHyperlink31);
/*     */ 
/* 376 */     this.jXHyperlink32.setClickedColor(new Color(0, 51, 255));
/* 377 */     this.jXHyperlink32.setText(bundle.getString("distribucionPartidas"));
/* 378 */     this.jXHyperlink32.setActionCommand("distribucionpartidas");
/* 379 */     this.jXHyperlink32.addActionListener(this);
/* 380 */     this.jXTaskPane6.getContentPane().add(this.jXHyperlink32);
/*     */ 
/* 382 */     this.jXHyperlink33.setClickedColor(new Color(0, 51, 255));
/* 383 */     this.jXHyperlink33.setText(bundle.getString("origenDestinoMenu"));
/* 384 */     this.jXHyperlink33.setActionCommand("origenesDestinos");
/* 385 */     this.jXHyperlink33.addActionListener(this);
/* 386 */     this.jXTaskPane6.getContentPane().add(this.jXHyperlink33);
/*     */ 
/* 388 */     this.jXHyperlink34.setClickedColor(new Color(0, 51, 255));
/* 389 */     this.jXHyperlink34.setText(bundle.getString("ratios"));
/* 390 */     this.jXHyperlink34.setActionCommand("ratios");
/* 391 */     this.jXHyperlink34.addActionListener(this);
/* 392 */     this.jXTaskPane6.getContentPane().add(this.jXHyperlink34);
/*     */ 
/* 394 */     this.jXTaskPaneContainer1.add(this.jXTaskPane6);
/*     */ 
/* 396 */     this.jXTaskPane4.setCollapsed(true);
/* 397 */     this.jXTaskPane4.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/facturacion18.png")));
/* 398 */     this.jXTaskPane4.setTitle("Facturación");
/*     */ 
/* 400 */     this.jXHyperlink15.setClickedColor(new Color(0, 51, 255));
/* 401 */     this.jXHyperlink15.setText("Crear/Editar a clientes");
/* 402 */     this.jXHyperlink15.setActionCommand("introduccionfacturas");
/* 403 */     this.jXHyperlink15.addActionListener(this);
/* 404 */     this.jXTaskPane4.getContentPane().add(this.jXHyperlink15);
/*     */ 
/* 406 */     this.jXHyperlink16.setClickedColor(new Color(0, 51, 255));
/* 407 */     this.jXHyperlink16.setText("Crear/Editar a proveedores");
/* 408 */     this.jXHyperlink16.setActionCommand("introduccionfacturasrecibidas");
/* 409 */     this.jXHyperlink16.addActionListener(this);
/* 410 */     this.jXTaskPane4.getContentPane().add(this.jXHyperlink16);
/*     */ 
/* 412 */     this.jXHyperlink17.setClickedColor(new Color(0, 51, 255));
/* 413 */     this.jXHyperlink17.setText("Gestión facturas clientes");
/* 414 */     this.jXHyperlink17.setActionCommand("gestionfacturas");
/* 415 */     this.jXHyperlink17.addActionListener(this);
/* 416 */     this.jXTaskPane4.getContentPane().add(this.jXHyperlink17);
/*     */ 
/* 418 */     this.jXHyperlink18.setClickedColor(new Color(0, 51, 255));
/* 419 */     this.jXHyperlink18.setText("Gestión facturas proveedores");
/* 420 */     this.jXHyperlink18.setActionCommand("gestionfacturasrecibidas");
/* 421 */     this.jXHyperlink18.addActionListener(this);
/* 422 */     this.jXTaskPane4.getContentPane().add(this.jXHyperlink18);
/*     */ 
/* 424 */     this.jXTaskPaneContainer1.add(this.jXTaskPane4);
/*     */ 
/* 426 */     this.jXTaskPane5.setCollapsed(true);
/* 427 */     this.jXTaskPane5.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/producto18.png")));
/* 428 */     this.jXTaskPane5.setTitle("Almacén");
/*     */ 
/* 430 */     this.jXHyperlink19.setClickedColor(new Color(0, 51, 255));
/* 431 */     this.jXHyperlink19.setText("Gestión de almacén");
/* 432 */     this.jXHyperlink19.setActionCommand("productos");
/* 433 */     this.jXHyperlink19.addActionListener(this);
/* 434 */     this.jXTaskPane5.getContentPane().add(this.jXHyperlink19);
/*     */ 
/* 436 */     this.jXHyperlink28.setClickedColor(new Color(0, 51, 255));
/* 437 */     this.jXHyperlink28.setText(bundle.getString("pedidosProveedores"));
/* 438 */     this.jXHyperlink28.setActionCommand("pedidosproveedores");
/* 439 */     this.jXHyperlink28.addActionListener(this);
/* 440 */     this.jXTaskPane5.getContentPane().add(this.jXHyperlink28);
/*     */ 
/* 442 */     this.jXHyperlink35.setClickedColor(new Color(0, 51, 255));
/* 443 */     this.jXHyperlink35.setText(bundle.getString("pedidosClientes"));
/* 444 */     this.jXHyperlink35.setActionCommand("pedidosclientes");
/* 445 */     this.jXHyperlink35.addActionListener(this);
/* 446 */     this.jXTaskPane5.getContentPane().add(this.jXHyperlink35);
/*     */ 
/* 448 */     this.jXHyperlink29.setClickedColor(new Color(0, 51, 255));
/* 449 */     this.jXHyperlink29.setText("Listados");
/* 450 */     this.jXHyperlink29.setActionCommand("listadosalmacen");
/* 451 */     this.jXHyperlink29.addActionListener(this);
/* 452 */     this.jXTaskPane5.getContentPane().add(this.jXHyperlink29);
/*     */ 
/* 454 */     this.jXHyperlink36.setClickedColor(new Color(0, 51, 255));
/* 455 */     this.jXHyperlink36.setText(bundle.getString("almacenes"));
/* 456 */     this.jXHyperlink36.setActionCommand("almacenes");
/* 457 */     this.jXHyperlink36.addActionListener(this);
/* 458 */     this.jXTaskPane5.getContentPane().add(this.jXHyperlink36);
/*     */ 
/* 460 */     this.jXTaskPaneContainer1.add(this.jXTaskPane5);
/*     */ 
/* 462 */     this.jXTaskPane8.setTitle(bundle.getString("postitle"));
/*     */ 
/* 464 */     this.jXHyperlink39.setClickedColor(new Color(0, 51, 255));
/* 465 */     this.jXHyperlink39.setText(bundle.getString("terminalventas"));
/* 466 */     this.jXHyperlink39.setActionCommand("terminalventas");
/* 467 */     this.jXHyperlink39.addActionListener(this);
/* 468 */     this.jXTaskPane8.getContentPane().add(this.jXHyperlink39);
/*     */ 
/* 470 */     this.jXHyperlink40.setClickedColor(new Color(0, 51, 255));
/* 471 */     this.jXHyperlink40.setText(bundle.getString("resumendia"));
/* 472 */     this.jXHyperlink40.setActionCommand("resumendiario");
/* 473 */     this.jXHyperlink40.addActionListener(this);
/* 474 */     this.jXTaskPane8.getContentPane().add(this.jXHyperlink40);
/*     */ 
/* 476 */     this.jXHyperlink37.setClickedColor(new Color(0, 51, 255));
/* 477 */     this.jXHyperlink37.setText(bundle.getString("formaspago"));
/* 478 */     this.jXHyperlink37.setActionCommand("mediospago");
/* 479 */     this.jXHyperlink37.addActionListener(this);
/* 480 */     this.jXTaskPane8.getContentPane().add(this.jXHyperlink37);
/*     */ 
/* 482 */     this.jXHyperlink38.setClickedColor(new Color(0, 51, 255));
/* 483 */     this.jXHyperlink38.setText(bundle.getString("vendedores"));
/* 484 */     this.jXHyperlink38.setActionCommand("vendedores");
/* 485 */     this.jXHyperlink38.addActionListener(this);
/* 486 */     this.jXTaskPane8.getContentPane().add(this.jXHyperlink38);
/*     */ 
/* 488 */     this.jXTaskPaneContainer1.add(this.jXTaskPane8);
/*     */ 
/* 490 */     add(this.jXTaskPaneContainer1);
/*     */   }
/*     */ 
/*     */   private void jXHyperlink20ActionPerformed(ActionEvent evt)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void actionPerformed(ActionEvent e)
/*     */   {
/* 554 */     String cmd = e.getActionCommand();
/* 555 */     Inicio.frame.realizarAccion(cmd);
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.PanelMenuJPanel
 * JD-Core Version:    0.6.2
 */