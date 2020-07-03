/*      */ package contaes.dialogosFunciones;
/*      */ 
/*      */ import contaes.auxiliar.DocNumeros;
/*      */ import internationalization.Mensajes;
/*      */ import java.awt.BorderLayout;
/*      */ import java.awt.Color;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.Font;
/*      */ import java.awt.GridBagConstraints;
/*      */ import java.awt.GridBagLayout;
/*      */ import java.awt.HeadlessException;
/*      */ import java.awt.Insets;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.ActionListener;
/*      */ import java.awt.event.KeyEvent;
/*      */ import java.awt.event.KeyListener;
/*      */ import java.awt.event.WindowAdapter;
/*      */ import java.awt.event.WindowEvent;
/*      */ import java.text.DecimalFormat;
/*      */ import java.text.DecimalFormatSymbols;
/*      */ import javax.swing.BorderFactory;
/*      */ import javax.swing.JButton;
/*      */ import javax.swing.JFrame;
/*      */ import javax.swing.JLabel;
/*      */ import javax.swing.JPanel;
/*      */ import javax.swing.JTextField;
/*      */ 
/*      */ public class Calculadora extends JFrame
/*      */   implements ActionListener, KeyListener
/*      */ {
/*   35 */   private JPanel jContentPane = null;
/*   36 */   private JPanel controles = null;
/*   37 */   private JButton MC = null;
/*   38 */   private JButton MR = null;
/*   39 */   private JButton Mmas = null;
/*   40 */   private JButton C = null;
/*   41 */   private JButton masMenos = null;
/*   42 */   private JButton sqrt = null;
/*   43 */   private JButton cuadrado = null;
/*   44 */   private JButton unoEntreX = null;
/*   45 */   private JButton porcentaje = null;
/*   46 */   private JButton siete = null;
/*   47 */   private JButton cuatro = null;
/*   48 */   private JButton uno = null;
/*   49 */   private JButton cero = null;
/*   50 */   private JButton ocho = null;
/*   51 */   private JButton cinco = null;
/*   52 */   private JButton dos = null;
/*   53 */   private JButton punto = null;
/*   54 */   private JButton nueve = null;
/*   55 */   private JButton seis = null;
/*   56 */   private JButton tres = null;
/*   57 */   private JButton igual = null;
/*   58 */   private JButton dividir = null;
/*   59 */   private JButton multiplicar = null;
/*   60 */   private JButton resta = null;
/*   61 */   private JButton suma = null;
/*   62 */   private JButton localC = null;
/*   63 */   private JButton euro = null;
/*   64 */   private JButton devolver = null;
/*   65 */   private JTextField DisplError = null;
/*   66 */   private JTextField lcdDisplay = null;
/*   67 */   private JPanel panelMem = null;
/*   68 */   private JLabel LabelMem = null;
/*   69 */   private JTextField campoOrigen = null;
/*      */   int Counter;
/*      */   double Result;
/*      */   double Operand;
/*      */   double Mem;
/*      */   boolean DecimalFlag;
/*      */   boolean SignFlag;
/*      */   boolean OperatorKey;
/*      */   boolean FunctionKey;
/*      */   int Operator;
/*      */   char currchar;
/*      */   String Status;
/*      */   public static final int OpMinus = 11;
/*      */   public static final int OpMultiply = 12;
/*      */   public static final int OpPlus = 13;
/*      */   public static final int OpDivide = 15;
/*      */   public static final int OpPercent = 16;
/*      */   public static final int OpMPlus = 19;
/*      */   public static final int OpMClear = 20;
/*      */   public static final int OpMR = 21;
/*      */   public static final double cambio = 166.386D;
/*      */ 
/*      */   public Calculadora()
/*      */     throws HeadlessException
/*      */   {
/*  119 */     initialize();
/*      */   }
/*      */ 
/*      */   public Calculadora(String arg0) throws HeadlessException {
/*  123 */     super(arg0);
/*  124 */     setDefaultCloseOperation(1);
/*  125 */     initialize();
/*      */   }
/*      */ 
/*      */   private void initialize()
/*      */   {
/*  134 */     setContentPane(getJContentPane());
/*  135 */     setTitle("Calculadora");
/*  136 */     setBounds(new Rectangle(0, 22, 389, 240));
/*  137 */     Clicked_Clear();
/*  138 */     addWindowListener(new WindowAdapter()
/*      */     {
/*      */       public void windowActivated(WindowEvent e) {
/*  141 */         Calculadora.this.lcdDisplay.requestFocusInWindow();
/*      */       }
/*      */     });
/*      */   }
/*      */ 
/*      */   private void foco() {
/*  147 */     this.lcdDisplay.requestFocus();
/*      */   }
/*      */ 
/*      */   private JPanel getJContentPane()
/*      */   {
/*  156 */     if (this.jContentPane == null) {
/*  157 */       this.jContentPane = new JPanel();
/*  158 */       this.jContentPane.setLayout(new BorderLayout());
/*  159 */       this.jContentPane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3), BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(1), BorderFactory.createEmptyBorder(5, 5, 5, 5))));
/*  160 */       this.jContentPane.add(getControles(), "Center");
/*      */     }
/*  162 */     return this.jContentPane;
/*      */   }
/*      */ 
/*      */   private JPanel getControles()
/*      */   {
/*  171 */     if (this.controles == null) {
/*  172 */       GridBagConstraints gridBagConstraints111 = new GridBagConstraints();
/*  173 */       gridBagConstraints111.gridx = 0;
/*  174 */       gridBagConstraints111.gridy = 1;
/*  175 */       GridBagConstraints gridBagConstraints41 = new GridBagConstraints();
/*  176 */       gridBagConstraints41.gridx = 0;
/*  177 */       gridBagConstraints41.gridy = 0;
/*  178 */       GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
/*  179 */       gridBagConstraints31.fill = 2;
/*  180 */       gridBagConstraints31.gridy = 0;
/*  181 */       gridBagConstraints31.weightx = 1.0D;
/*  182 */       gridBagConstraints31.gridwidth = 3;
/*  183 */       gridBagConstraints31.insets = new Insets(2, 5, 2, 5);
/*  184 */       gridBagConstraints31.gridx = 3;
/*  185 */       GridBagConstraints gridBagConstraints26 = new GridBagConstraints();
/*  186 */       gridBagConstraints26.fill = 2;
/*  187 */       gridBagConstraints26.gridy = 0;
/*  188 */       gridBagConstraints26.weightx = 1.0D;
/*  189 */       gridBagConstraints26.gridwidth = 2;
/*  190 */       gridBagConstraints26.insets = new Insets(2, 5, 2, 5);
/*  191 */       gridBagConstraints26.gridx = 1;
/*  192 */       GridBagConstraints gridBagConstraints27 = new GridBagConstraints();
/*  193 */       gridBagConstraints27.gridx = 4;
/*  194 */       gridBagConstraints27.insets = new Insets(0, 2, 0, 0);
/*  195 */       gridBagConstraints27.gridy = 1;
/*  196 */       GridBagConstraints gridBagConstraints110 = new GridBagConstraints();
/*  197 */       gridBagConstraints110.gridx = 2;
/*  198 */       gridBagConstraints110.gridwidth = 2;
/*  199 */       gridBagConstraints110.gridy = 1;
/*  200 */       GridBagConstraints gridBagConstraints25 = new GridBagConstraints();
/*  201 */       gridBagConstraints25.gridx = 5;
/*  202 */       gridBagConstraints25.insets = new Insets(2, 2, 0, 0);
/*  203 */       gridBagConstraints25.gridy = 5;
/*  204 */       GridBagConstraints gridBagConstraints24 = new GridBagConstraints();
/*  205 */       gridBagConstraints24.gridx = 5;
/*  206 */       gridBagConstraints24.insets = new Insets(2, 2, 0, 0);
/*  207 */       gridBagConstraints24.gridy = 4;
/*  208 */       GridBagConstraints gridBagConstraints23 = new GridBagConstraints();
/*  209 */       gridBagConstraints23.gridx = 5;
/*  210 */       gridBagConstraints23.insets = new Insets(2, 2, 0, 0);
/*  211 */       gridBagConstraints23.gridy = 3;
/*  212 */       GridBagConstraints gridBagConstraints22 = new GridBagConstraints();
/*  213 */       gridBagConstraints22.gridx = 5;
/*  214 */       gridBagConstraints22.insets = new Insets(2, 2, 0, 0);
/*  215 */       gridBagConstraints22.gridy = 2;
/*  216 */       GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
/*  217 */       gridBagConstraints21.gridx = 4;
/*  218 */       gridBagConstraints21.insets = new Insets(2, 2, 0, 0);
/*  219 */       gridBagConstraints21.gridy = 5;
/*  220 */       GridBagConstraints gridBagConstraints20 = new GridBagConstraints();
/*  221 */       gridBagConstraints20.gridx = 4;
/*  222 */       gridBagConstraints20.insets = new Insets(2, 2, 0, 0);
/*  223 */       gridBagConstraints20.gridy = 4;
/*  224 */       GridBagConstraints gridBagConstraints19 = new GridBagConstraints();
/*  225 */       gridBagConstraints19.gridx = 4;
/*  226 */       gridBagConstraints19.insets = new Insets(2, 2, 0, 0);
/*  227 */       gridBagConstraints19.gridy = 3;
/*  228 */       GridBagConstraints gridBagConstraints18 = new GridBagConstraints();
/*  229 */       gridBagConstraints18.gridx = 4;
/*  230 */       gridBagConstraints18.insets = new Insets(2, 2, 0, 0);
/*  231 */       gridBagConstraints18.gridy = 2;
/*  232 */       GridBagConstraints gridBagConstraints17 = new GridBagConstraints();
/*  233 */       gridBagConstraints17.gridx = 3;
/*  234 */       gridBagConstraints17.insets = new Insets(2, 2, 0, 0);
/*  235 */       gridBagConstraints17.gridy = 5;
/*  236 */       GridBagConstraints gridBagConstraints16 = new GridBagConstraints();
/*  237 */       gridBagConstraints16.gridx = 3;
/*  238 */       gridBagConstraints16.insets = new Insets(2, 2, 0, 0);
/*  239 */       gridBagConstraints16.gridy = 4;
/*  240 */       GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
/*  241 */       gridBagConstraints15.gridx = 3;
/*  242 */       gridBagConstraints15.insets = new Insets(2, 2, 0, 0);
/*  243 */       gridBagConstraints15.gridy = 3;
/*  244 */       GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
/*  245 */       gridBagConstraints14.gridx = 3;
/*  246 */       gridBagConstraints14.insets = new Insets(2, 2, 0, 0);
/*  247 */       gridBagConstraints14.gridy = 2;
/*  248 */       GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
/*  249 */       gridBagConstraints13.gridx = 2;
/*  250 */       gridBagConstraints13.insets = new Insets(2, 2, 0, 0);
/*  251 */       gridBagConstraints13.gridy = 5;
/*  252 */       GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
/*  253 */       gridBagConstraints12.gridx = 2;
/*  254 */       gridBagConstraints12.insets = new Insets(2, 2, 0, 0);
/*  255 */       gridBagConstraints12.gridy = 4;
/*  256 */       GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
/*  257 */       gridBagConstraints11.gridx = 2;
/*  258 */       gridBagConstraints11.insets = new Insets(2, 2, 0, 0);
/*  259 */       gridBagConstraints11.gridy = 3;
/*  260 */       GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
/*  261 */       gridBagConstraints10.gridx = 2;
/*  262 */       gridBagConstraints10.insets = new Insets(2, 2, 0, 0);
/*  263 */       gridBagConstraints10.gridy = 2;
/*  264 */       GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
/*  265 */       gridBagConstraints9.gridx = 5;
/*  266 */       gridBagConstraints9.insets = new Insets(2, 2, 0, 0);
/*  267 */       gridBagConstraints9.gridy = 1;
/*  268 */       GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
/*  269 */       gridBagConstraints8.gridx = 1;
/*  270 */       gridBagConstraints8.insets = new Insets(2, 2, 0, 0);
/*  271 */       gridBagConstraints8.gridy = 4;
/*  272 */       GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
/*  273 */       gridBagConstraints7.gridx = 1;
/*  274 */       gridBagConstraints7.insets = new Insets(2, 2, 0, 0);
/*  275 */       gridBagConstraints7.gridy = 3;
/*  276 */       GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
/*  277 */       gridBagConstraints6.gridx = 1;
/*  278 */       gridBagConstraints6.insets = new Insets(2, 2, 0, 0);
/*  279 */       gridBagConstraints6.gridy = 2;
/*  280 */       GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
/*  281 */       gridBagConstraints4.gridx = 1;
/*  282 */       gridBagConstraints4.insets = new Insets(2, 0, 0, 0);
/*  283 */       gridBagConstraints4.gridy = 5;
/*  284 */       GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
/*  285 */       gridBagConstraints3.gridx = 0;
/*  286 */       gridBagConstraints3.insets = new Insets(2, 0, 0, 0);
/*  287 */       gridBagConstraints3.gridy = 5;
/*  288 */       GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
/*  289 */       gridBagConstraints2.gridx = 0;
/*  290 */       gridBagConstraints2.insets = new Insets(2, 0, 0, 0);
/*  291 */       gridBagConstraints2.gridy = 4;
/*  292 */       GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
/*  293 */       gridBagConstraints1.gridx = 0;
/*  294 */       gridBagConstraints1.insets = new Insets(2, 0, 0, 0);
/*  295 */       gridBagConstraints1.gridy = 3;
/*  296 */       GridBagConstraints gridBagConstraints = new GridBagConstraints();
/*  297 */       gridBagConstraints.gridx = 0;
/*  298 */       gridBagConstraints.gridy = 2;
/*  299 */       this.controles = new JPanel();
/*  300 */       this.controles.setLayout(new GridBagLayout());
/*  301 */       this.controles.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
/*  302 */       this.controles.add(getMC(), gridBagConstraints);
/*  303 */       this.controles.add(getMR(), gridBagConstraints1);
/*  304 */       this.controles.add(getMmas(), gridBagConstraints2);
/*  305 */       this.controles.add(getC(), gridBagConstraints3);
/*  306 */       this.controles.add(getMasMenos(), gridBagConstraints4);
/*  307 */       this.controles.add(getSqrt(), gridBagConstraints6);
/*  308 */       this.controles.add(getCuadrado(), gridBagConstraints7);
/*  309 */       this.controles.add(getUnoEntreX(), gridBagConstraints8);
/*  310 */       this.controles.add(getPorcentaje(), gridBagConstraints9);
/*  311 */       this.controles.add(getSiete(), gridBagConstraints10);
/*  312 */       this.controles.add(getCuatro(), gridBagConstraints11);
/*  313 */       this.controles.add(getUno(), gridBagConstraints12);
/*  314 */       this.controles.add(getCero(), gridBagConstraints13);
/*  315 */       this.controles.add(getOcho(), gridBagConstraints14);
/*  316 */       this.controles.add(getCinco(), gridBagConstraints15);
/*  317 */       this.controles.add(getDos(), gridBagConstraints16);
/*  318 */       this.controles.add(getPunto(), gridBagConstraints17);
/*  319 */       this.controles.add(getNueve(), gridBagConstraints18);
/*  320 */       this.controles.add(getSeis(), gridBagConstraints19);
/*  321 */       this.controles.add(getTres(), gridBagConstraints20);
/*  322 */       this.controles.add(getIgual(), gridBagConstraints21);
/*  323 */       this.controles.add(getDividir(), gridBagConstraints22);
/*  324 */       this.controles.add(getMultiplicar(), gridBagConstraints23);
/*  325 */       this.controles.add(getResta(), gridBagConstraints24);
/*  326 */       this.controles.add(getSuma(), gridBagConstraints25);
/*  327 */       this.controles.add(getLocalC(), gridBagConstraints110);
/*  328 */       this.controles.add(getJButton(), gridBagConstraints27);
/*  329 */       this.controles.add(getDisplError(), gridBagConstraints26);
/*  330 */       this.controles.add(getLcdDisplay(), gridBagConstraints31);
/*  331 */       this.controles.add(getPanelMem(), gridBagConstraints41);
/*  332 */       this.controles.add(getDevolver(), gridBagConstraints111);
/*      */     }
/*  334 */     return this.controles;
/*      */   }
/*      */ 
/*      */   private JButton getMC()
/*      */   {
/*  343 */     if (this.MC == null) {
/*  344 */       this.MC = new JButton();
/*  345 */       this.MC.setText("MC");
/*  346 */       this.MC.addActionListener(this);
/*  347 */       this.MC.setPreferredSize(new Dimension(62, 26));
/*  348 */       this.MC.setFont(new Font("Dialog", 0, 12));
/*      */     }
/*  350 */     return this.MC;
/*      */   }
/*      */ 
/*      */   private JButton getMR()
/*      */   {
/*  359 */     if (this.MR == null) {
/*  360 */       this.MR = new JButton();
/*  361 */       this.MR.setText("MR");
/*  362 */       this.MR.addActionListener(this);
/*  363 */       this.MR.setPreferredSize(new Dimension(62, 26));
/*  364 */       this.MR.setFont(new Font("Dialog", 0, 12));
/*      */     }
/*  366 */     return this.MR;
/*      */   }
/*      */ 
/*      */   private JButton getMmas()
/*      */   {
/*  375 */     if (this.Mmas == null) {
/*  376 */       this.Mmas = new JButton();
/*  377 */       this.Mmas.setText("M+");
/*  378 */       this.Mmas.addActionListener(this);
/*  379 */       this.Mmas.setPreferredSize(new Dimension(64, 26));
/*  380 */       this.Mmas.setFont(new Font("Dialog", 0, 12));
/*      */     }
/*  382 */     return this.Mmas;
/*      */   }
/*      */ 
/*      */   private JButton getC()
/*      */   {
/*  391 */     if (this.C == null) {
/*  392 */       this.C = new JButton();
/*  393 */       this.C.setText("C");
/*  394 */       this.C.addActionListener(this);
/*  395 */       this.C.setFont(new Font("Dialog", 1, 12));
/*  396 */       this.C.setForeground(Color.red);
/*  397 */       this.C.setPreferredSize(new Dimension(54, 26));
/*      */     }
/*  399 */     return this.C;
/*      */   }
/*      */ 
/*      */   private JButton getMasMenos()
/*      */   {
/*  408 */     if (this.masMenos == null) {
/*  409 */       this.masMenos = new JButton();
/*  410 */       this.masMenos.setText("+/-");
/*  411 */       this.masMenos.setFont(new Font("Dialog", 0, 12));
/*  412 */       this.masMenos.addActionListener(this);
/*  413 */       this.masMenos.setPreferredSize(new Dimension(67, 26));
/*      */     }
/*  415 */     return this.masMenos;
/*      */   }
/*      */ 
/*      */   private JButton getSqrt()
/*      */   {
/*  424 */     if (this.sqrt == null) {
/*  425 */       this.sqrt = new JButton();
/*  426 */       this.sqrt.setFont(new Font("Dialog", 0, 12));
/*  427 */       this.sqrt.setPreferredSize(new Dimension(67, 26));
/*  428 */       this.sqrt.setText("sqrt");
/*  429 */       this.sqrt.addActionListener(this);
/*      */     }
/*  431 */     return this.sqrt;
/*      */   }
/*      */ 
/*      */   private JButton getCuadrado()
/*      */   {
/*  440 */     if (this.cuadrado == null) {
/*  441 */       this.cuadrado = new JButton();
/*  442 */       this.cuadrado.setFont(new Font("Dialog", 0, 12));
/*  443 */       this.cuadrado.setPreferredSize(new Dimension(62, 26));
/*  444 */       this.cuadrado.setText("x²");
/*  445 */       this.cuadrado.addActionListener(this);
/*      */     }
/*  447 */     return this.cuadrado;
/*      */   }
/*      */ 
/*      */   private JButton getUnoEntreX()
/*      */   {
/*  456 */     if (this.unoEntreX == null) {
/*  457 */       this.unoEntreX = new JButton();
/*  458 */       this.unoEntreX.setFont(new Font("Dialog", 0, 12));
/*  459 */       this.unoEntreX.setPreferredSize(new Dimension(65, 26));
/*  460 */       this.unoEntreX.setText("1/x");
/*  461 */       this.unoEntreX.addActionListener(this);
/*      */     }
/*  463 */     return this.unoEntreX;
/*      */   }
/*      */ 
/*      */   private JButton getPorcentaje()
/*      */   {
/*  472 */     if (this.porcentaje == null) {
/*  473 */       this.porcentaje = new JButton();
/*  474 */       this.porcentaje.setPreferredSize(new Dimension(52, 26));
/*  475 */       this.porcentaje.setText("%");
/*  476 */       this.porcentaje.addActionListener(this);
/*  477 */       this.porcentaje.setFont(new Font("Dialog", 0, 12));
/*      */     }
/*  479 */     return this.porcentaje;
/*      */   }
/*      */ 
/*      */   private JButton getSiete()
/*      */   {
/*  488 */     if (this.siete == null) {
/*  489 */       this.siete = new JButton();
/*  490 */       this.siete.setText("7");
/*  491 */       this.siete.addActionListener(this);
/*  492 */       this.siete.setPreferredSize(new Dimension(52, 26));
/*  493 */       this.siete.setFont(new Font("Dialog", 0, 12));
/*      */     }
/*  495 */     return this.siete;
/*      */   }
/*      */ 
/*      */   private JButton getCuatro()
/*      */   {
/*  504 */     if (this.cuatro == null) {
/*  505 */       this.cuatro = new JButton();
/*  506 */       this.cuatro.setText("4");
/*  507 */       this.cuatro.addActionListener(this);
/*  508 */       this.cuatro.setPreferredSize(new Dimension(52, 26));
/*  509 */       this.cuatro.setFont(new Font("Dialog", 0, 12));
/*      */     }
/*  511 */     return this.cuatro;
/*      */   }
/*      */ 
/*      */   private JButton getUno()
/*      */   {
/*  520 */     if (this.uno == null) {
/*  521 */       this.uno = new JButton();
/*  522 */       this.uno.setText("1");
/*  523 */       this.uno.addActionListener(this);
/*  524 */       this.uno.setPreferredSize(new Dimension(52, 26));
/*  525 */       this.uno.setFont(new Font("Dialog", 0, 12));
/*      */     }
/*  527 */     return this.uno;
/*      */   }
/*      */ 
/*      */   private JButton getCero()
/*      */   {
/*  536 */     if (this.cero == null) {
/*  537 */       this.cero = new JButton();
/*  538 */       this.cero.setText("0");
/*  539 */       this.cero.addActionListener(this);
/*  540 */       this.cero.setPreferredSize(new Dimension(52, 26));
/*  541 */       this.cero.setFont(new Font("Dialog", 0, 12));
/*      */     }
/*  543 */     return this.cero;
/*      */   }
/*      */ 
/*      */   private JButton getOcho()
/*      */   {
/*  552 */     if (this.ocho == null) {
/*  553 */       this.ocho = new JButton();
/*  554 */       this.ocho.setText("8");
/*  555 */       this.ocho.addActionListener(this);
/*  556 */       this.ocho.setPreferredSize(new Dimension(52, 26));
/*  557 */       this.ocho.setFont(new Font("Dialog", 0, 12));
/*      */     }
/*  559 */     return this.ocho;
/*      */   }
/*      */ 
/*      */   private JButton getCinco()
/*      */   {
/*  568 */     if (this.cinco == null) {
/*  569 */       this.cinco = new JButton();
/*  570 */       this.cinco.setText("5");
/*  571 */       this.cinco.addActionListener(this);
/*  572 */       this.cinco.setPreferredSize(new Dimension(52, 26));
/*  573 */       this.cinco.setFont(new Font("Dialog", 0, 12));
/*      */     }
/*  575 */     return this.cinco;
/*      */   }
/*      */ 
/*      */   private JButton getDos()
/*      */   {
/*  584 */     if (this.dos == null) {
/*  585 */       this.dos = new JButton();
/*  586 */       this.dos.setText("2");
/*  587 */       this.dos.addActionListener(this);
/*  588 */       this.dos.setPreferredSize(new Dimension(52, 26));
/*  589 */       this.dos.setFont(new Font("Dialog", 0, 12));
/*      */     }
/*  591 */     return this.dos;
/*      */   }
/*      */ 
/*      */   private JButton getPunto()
/*      */   {
/*  600 */     if (this.punto == null) {
/*  601 */       this.punto = new JButton();
/*  602 */       this.punto.setText(".");
/*  603 */       this.punto.addActionListener(this);
/*  604 */       this.punto.setFont(new Font("Dialog", 0, 12));
/*  605 */       this.punto.setPreferredSize(new Dimension(52, 26));
/*      */     }
/*  607 */     return this.punto;
/*      */   }
/*      */ 
/*      */   private JButton getNueve()
/*      */   {
/*  616 */     if (this.nueve == null) {
/*  617 */       this.nueve = new JButton();
/*  618 */       this.nueve.setText("9");
/*  619 */       this.nueve.addActionListener(this);
/*  620 */       this.nueve.setPreferredSize(new Dimension(52, 26));
/*  621 */       this.nueve.setFont(new Font("Dialog", 0, 12));
/*      */     }
/*  623 */     return this.nueve;
/*      */   }
/*      */ 
/*      */   private JButton getSeis()
/*      */   {
/*  632 */     if (this.seis == null) {
/*  633 */       this.seis = new JButton();
/*  634 */       this.seis.setText("6");
/*  635 */       this.seis.addActionListener(this);
/*  636 */       this.seis.setPreferredSize(new Dimension(52, 26));
/*  637 */       this.seis.setFont(new Font("Dialog", 0, 12));
/*      */     }
/*  639 */     return this.seis;
/*      */   }
/*      */ 
/*      */   private JButton getTres()
/*      */   {
/*  648 */     if (this.tres == null) {
/*  649 */       this.tres = new JButton();
/*  650 */       this.tres.setText("3");
/*  651 */       this.tres.addActionListener(this);
/*  652 */       this.tres.setPreferredSize(new Dimension(52, 26));
/*  653 */       this.tres.setFont(new Font("Dialog", 0, 12));
/*      */     }
/*  655 */     return this.tres;
/*      */   }
/*      */ 
/*      */   private JButton getIgual()
/*      */   {
/*  664 */     if (this.igual == null) {
/*  665 */       this.igual = new JButton();
/*  666 */       this.igual.setText("=");
/*  667 */       this.igual.addActionListener(this);
/*  668 */       this.igual.setPreferredSize(new Dimension(54, 26));
/*  669 */       this.igual.setFont(new Font("Dialog", 0, 12));
/*      */     }
/*  671 */     return this.igual;
/*      */   }
/*      */ 
/*      */   private JButton getDividir()
/*      */   {
/*  680 */     if (this.dividir == null) {
/*  681 */       this.dividir = new JButton();
/*  682 */       this.dividir.setText("/");
/*  683 */       this.dividir.addActionListener(this);
/*  684 */       this.dividir.setFont(new Font("Dialog", 0, 12));
/*  685 */       this.dividir.setPreferredSize(new Dimension(50, 26));
/*      */     }
/*  687 */     return this.dividir;
/*      */   }
/*      */ 
/*      */   private JButton getMultiplicar()
/*      */   {
/*  696 */     if (this.multiplicar == null) {
/*  697 */       this.multiplicar = new JButton();
/*  698 */       this.multiplicar.setText("x");
/*  699 */       this.multiplicar.addActionListener(this);
/*  700 */       this.multiplicar.setFont(new Font("Dialog", 0, 12));
/*  701 */       this.multiplicar.setPreferredSize(new Dimension(52, 26));
/*      */     }
/*  703 */     return this.multiplicar;
/*      */   }
/*      */ 
/*      */   private JButton getResta()
/*      */   {
/*  712 */     if (this.resta == null) {
/*  713 */       this.resta = new JButton();
/*  714 */       this.resta.setText("-");
/*  715 */       this.resta.addActionListener(this);
/*  716 */       this.resta.setFont(new Font("Dialog", 0, 12));
/*  717 */       this.resta.setPreferredSize(new Dimension(52, 26));
/*      */     }
/*  719 */     return this.resta;
/*      */   }
/*      */ 
/*      */   private JButton getSuma()
/*      */   {
/*  728 */     if (this.suma == null) {
/*  729 */       this.suma = new JButton();
/*  730 */       this.suma.setText("+");
/*  731 */       this.suma.addActionListener(this);
/*  732 */       this.suma.setFont(new Font("Dialog", 0, 12));
/*  733 */       this.suma.setPreferredSize(new Dimension(54, 26));
/*      */     }
/*  735 */     return this.suma;
/*      */   }
/*      */ 
/*      */   private JButton getLocalC()
/*      */   {
/*  744 */     if (this.localC == null) {
/*  745 */       this.localC = new JButton();
/*  746 */       this.localC.setText("Pts");
/*  747 */       this.localC.addActionListener(this);
/*  748 */       this.localC.setFont(new Font("Dialog", 0, 12));
/*      */     }
/*  750 */     return this.localC;
/*      */   }
/*      */ 
/*      */   private JButton getJButton()
/*      */   {
/*  759 */     if (this.euro == null) {
/*  760 */       this.euro = new JButton();
/*  761 */       this.euro.setText("€");
/*  762 */       this.euro.addActionListener(this);
/*  763 */       this.euro.setPreferredSize(new Dimension(52, 26));
/*  764 */       this.euro.setFont(new Font("Dialog", 0, 12));
/*      */     }
/*  766 */     return this.euro;
/*      */   }
/*      */ 
/*      */   private JButton getDevolver()
/*      */   {
/*  775 */     if (this.devolver == null) {
/*  776 */       this.devolver = new JButton();
/*  777 */       this.devolver.setForeground(Color.red);
/*  778 */       this.devolver.setText("◄");
/*  779 */       this.devolver.setToolTipText(Mensajes.getString("pegarResultado"));
/*  780 */       this.devolver.addActionListener(this);
/*      */     }
/*  782 */     return this.devolver;
/*      */   }
/*      */ 
/*      */   private JTextField getDisplError()
/*      */   {
/*  791 */     if (this.DisplError == null) {
/*  792 */       this.DisplError = new JTextField();
/*  793 */       this.DisplError.setEditable(false);
/*      */     }
/*  795 */     return this.DisplError;
/*      */   }
/*      */ 
/*      */   private JTextField getLcdDisplay()
/*      */   {
/*  804 */     if (this.lcdDisplay == null) {
/*  805 */       this.lcdDisplay = new JTextField();
/*  806 */       this.lcdDisplay.setHorizontalAlignment(4);
/*  807 */       this.lcdDisplay.setDocument(new DocNumeros());
/*  808 */       this.lcdDisplay.addKeyListener(this);
/*      */     }
/*  810 */     return this.lcdDisplay;
/*      */   }
/*      */ 
/*      */   private JPanel getPanelMem()
/*      */   {
/*  819 */     if (this.panelMem == null) {
/*  820 */       this.LabelMem = new JLabel();
/*  821 */       this.LabelMem.setText(" ");
/*  822 */       this.LabelMem.setPreferredSize(new Dimension(21, 16));
/*  823 */       this.panelMem = new JPanel();
/*  824 */       this.panelMem.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
/*  825 */       this.panelMem.add(this.LabelMem, null);
/*      */     }
/*  827 */     return this.panelMem;
/*      */   }
/*      */ 
/*      */   public void actionPerformed(ActionEvent event)
/*      */   {
/*  834 */     Object src = event.getSource();
/*      */ 
/*  838 */     if ((src instanceof JButton))
/*      */     {
/*  842 */       if ((!this.Status.equals("ERROR")) || (src == this.C))
/*      */       {
/*  848 */         if (src == this.uno) {
/*  849 */           NumericButton(1, true);
/*      */         }
/*  851 */         if (src == this.dos) {
/*  852 */           NumericButton(2, true);
/*      */         }
/*  854 */         if (src == this.tres) {
/*  855 */           NumericButton(3, true);
/*      */         }
/*  857 */         if (src == this.cuatro) {
/*  858 */           NumericButton(4, true);
/*      */         }
/*  860 */         if (src == this.cinco) {
/*  861 */           NumericButton(5, true);
/*      */         }
/*  863 */         if (src == this.seis) {
/*  864 */           NumericButton(6, true);
/*      */         }
/*  866 */         if (src == this.siete) {
/*  867 */           NumericButton(7, true);
/*      */         }
/*  869 */         if (src == this.ocho) {
/*  870 */           NumericButton(8, true);
/*      */         }
/*  872 */         if (src == this.nueve) {
/*  873 */           NumericButton(9, true);
/*      */         }
/*  875 */         if (src == this.cero) {
/*  876 */           NumericButton(0, true);
/*      */         }
/*      */ 
/*  883 */         if (src == this.resta) {
/*  884 */           OperatorButton(11);
/*      */         }
/*  886 */         if (src == this.multiplicar) {
/*  887 */           OperatorButton(12);
/*      */         }
/*  889 */         if (src == this.suma) {
/*  890 */           OperatorButton(13);
/*      */         }
/*  892 */         if (src == this.igual) {
/*  893 */           OperatorButton(14);
/*      */         }
/*  895 */         if (src == this.dividir) {
/*  896 */           OperatorButton(15);
/*      */         }
/*      */ 
/*  902 */         if (src == this.punto) {
/*  903 */           DecimalButton(true);
/*      */         }
/*      */ 
/*  909 */         if (src == this.porcentaje) {
/*  910 */           OperatorButton(16);
/*      */         }
/*      */ 
/*  917 */         if (src == this.C) {
/*  918 */           Clicked_Clear();
/*      */         }
/*      */ 
/*  924 */         if (src == this.masMenos) {
/*  925 */           PlusMinusButton();
/*      */         }
/*      */ 
/*  931 */         if (src == this.cuadrado) {
/*  932 */           SqrButton();
/*      */         }
/*      */ 
/*  938 */         if (src == this.sqrt) {
/*  939 */           SqrRootButton();
/*      */         }
/*      */ 
/*  945 */         if (src == this.unoEntreX) {
/*  946 */           OneOverXButton();
/*      */         }
/*      */ 
/*  949 */         if (src == this.localC) {
/*  950 */           cambioMonedaLocal();
/*      */         }
/*  952 */         if (src == this.euro) {
/*  953 */           cambioEuro();
/*      */         }
/*      */ 
/*  960 */         if (src == this.Mmas) {
/*  961 */           MemoryButton(19);
/*      */         }
/*      */ 
/*  964 */         if (src == this.MC) {
/*  965 */           MemoryButton(20);
/*      */         }
/*      */ 
/*  968 */         if (src == this.MR) {
/*  969 */           MemoryButton(21);
/*      */         }
/*      */ 
/*  972 */         if (src == this.devolver) {
/*  973 */           colocarResultado();
/*      */         }
/*      */       }
/*  976 */       foco();
/*      */     }
/*      */   }
/*      */ 
/*      */   public void DisplayError(String err_msg)
/*      */   {
/*  988 */     this.DisplError.setText(err_msg);
/*      */   }
/*      */ 
/*      */   public void NumericButton(int i, boolean boton)
/*      */   {
/*  993 */     DisplayError(" ");
/*      */ 
/*  998 */     String Display = this.lcdDisplay.getText();
/*      */ 
/* 1004 */     if (this.OperatorKey == true) {
/* 1005 */       this.Counter = 0;
/*      */     }
/*      */ 
/* 1008 */     this.Counter += 1;
/*      */ 
/* 1013 */     if ((Display.equals("0")) || (this.Status.equals("FIRST"))) {
/* 1014 */       Display = "";
/*      */     }
/*      */ 
/* 1017 */     if (this.Counter < 21)
/*      */     {
/* 1020 */       if (boton) {
/* 1021 */         Display = Display + String.valueOf(i);
/*      */       }
/*      */     }
/*      */     else {
/* 1025 */       DisplayError("Limit 20 Dgts");
/*      */     }
/*      */ 
/* 1028 */     this.lcdDisplay.setText(Display);
/*      */ 
/* 1031 */     this.Status = "VALID";
/* 1032 */     this.OperatorKey = false;
/* 1033 */     this.FunctionKey = false;
/*      */   }
/*      */ 
/*      */   public void OperatorButton(int i)
/*      */   {
/* 1040 */     DisplayError(" ");
/*      */ 
/* 1047 */     this.Result = new Double(this.lcdDisplay.getText()).doubleValue();
/*      */ 
/* 1050 */     if ((!this.OperatorKey) || ((this.FunctionKey =true) != false)) {
/* 1051 */       switch (this.Operator)
/*      */       {
/*      */       case 13:
/* 1055 */         this.Result = (this.Operand + this.Result);
/* 1056 */         break;
/*      */       case 11:
/* 1061 */         this.Result = (this.Operand - this.Result);
/* 1062 */         break;
/*      */       case 12:
/* 1067 */         this.Result *= this.Operand;
/* 1068 */         break;
/*      */       case 16:
/* 1070 */         this.Result = (this.Operand * (this.Result / 100.0D));
/* 1071 */         break;
/*      */       case 15:
/* 1077 */         if (this.Result == 0.0D)
/*      */         {
/* 1080 */           this.Status = "ERROR";
/*      */ 
/* 1084 */           this.lcdDisplay.setText("ERROR");
/*      */ 
/* 1090 */           DisplayError("ERROR: Division by Zero");
/*      */         }
/*      */         else
/*      */         {
/* 1095 */           this.Result = (this.Operand / this.Result);
/*      */         }
/*      */         break;
/*      */       case 14:
/*      */       }
/*      */ 
/* 1101 */       if (!this.Status.equals("ERROR")) {
/* 1102 */         this.Status = "FIRST";
/*      */ 
/* 1107 */         this.Operand = this.Result;
/*      */ 
/* 1109 */         this.Operator = i;
/*      */ 
/* 1116 */         this.lcdDisplay.setText(String.valueOf(this.Result));
/*      */ 
/* 1120 */         this.DecimalFlag = false;
/*      */ 
/* 1124 */         this.SignFlag = false;
/*      */ 
/* 1128 */         this.OperatorKey = true;
/*      */ 
/* 1132 */         this.FunctionKey = false;
/*      */ 
/* 1134 */         DisplayError(" ");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public void DecimalButton(boolean boton)
/*      */   {
/* 1143 */     DisplayError(" ");
/*      */ 
/* 1148 */     String Display = this.lcdDisplay.getText();
/*      */ 
/* 1151 */     if (this.Status.equals("FIRST")) {
/* 1152 */       Display = "0";
/*      */     }
/*      */ 
/* 1156 */     if (!this.DecimalFlag)
/*      */     {
/* 1158 */       if (boton) {
/* 1159 */         Display = Display + ".";
/*      */       }
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/* 1165 */       DisplayError("Number already has a Decimal Point");
/*      */     }
/*      */ 
/* 1171 */     this.lcdDisplay.setText(Display);
/* 1172 */     this.DecimalFlag = true;
/* 1173 */     this.Status = "VALID";
/*      */ 
/* 1176 */     this.OperatorKey = false;
/*      */   }
/*      */ 
/*      */   public void Clicked_Clear()
/*      */   {
/* 1185 */     this.Counter = 0;
/* 1186 */     this.Status = "FIRST";
/* 1187 */     this.Operand = 0.0D;
/* 1188 */     this.Result = 0.0D;
/* 1189 */     this.Operator = 0;
/*      */ 
/* 1191 */     this.DecimalFlag = false;
/*      */ 
/* 1194 */     this.SignFlag = false;
/*      */ 
/* 1196 */     this.OperatorKey = false;
/*      */ 
/* 1199 */     this.FunctionKey = false;
/*      */ 
/* 1205 */     this.lcdDisplay.setText("0");
/* 1206 */     DisplayError(" ");
/*      */   }
/*      */ 
/*      */   public void PlusMinusButton()
/*      */   {
/* 1211 */     DisplayError(" ");
/*      */ 
/* 1216 */     String Display = this.lcdDisplay.getText();
/*      */ 
/* 1221 */     if ((!this.Status.equals("FIRST")) || (!Display.equals("0")))
/*      */     {
/* 1227 */       this.Result = new Double(this.lcdDisplay.getText()).doubleValue();
/*      */ 
/* 1230 */       this.Result = (-this.Result);
/*      */ 
/* 1235 */       this.lcdDisplay.setText(String.valueOf(this.Result));
/* 1236 */       this.Status = "VALID";
/* 1237 */       this.SignFlag = true;
/* 1238 */       this.DecimalFlag = true;
/*      */     }
/*      */   }
/*      */ 
/*      */   public void SqrButton()
/*      */   {
/* 1244 */     DisplayError(" ");
/*      */ 
/* 1249 */     String Display = this.lcdDisplay.getText();
/*      */ 
/* 1254 */     if ((!this.Status.equals("FIRST")) || (!Display.equals("0")))
/*      */     {
/* 1260 */       this.Result = new Double(this.lcdDisplay.getText()).doubleValue();
/*      */ 
/* 1264 */       this.Result *= this.Result;
/*      */ 
/* 1269 */       this.lcdDisplay.setText(String.valueOf(this.Result));
/*      */ 
/* 1271 */       this.Status = "FIRST";
/*      */ 
/* 1274 */       this.OperatorKey = true;
/* 1275 */       this.FunctionKey = true;
/*      */     }
/*      */   }
/*      */ 
/*      */   public void SqrRootButton()
/*      */   {
/* 1281 */     DisplayError(" ");
/*      */ 
/* 1286 */     String Display = this.lcdDisplay.getText();
/*      */ 
/* 1291 */     if ((!this.Status.equals("FIRST")) || (!Display.equals("0")))
/*      */     {
/* 1297 */       this.Result = new Double(this.lcdDisplay.getText()).doubleValue();
/*      */ 
/* 1302 */       this.Result = Math.sqrt(this.Result);
/*      */ 
/* 1307 */       this.lcdDisplay.setText(String.valueOf(this.Result));
/*      */ 
/* 1309 */       this.Status = "FIRST";
/*      */ 
/* 1312 */       this.OperatorKey = true;
/* 1313 */       this.FunctionKey = true;
/*      */     }
/*      */   }
/*      */ 
/*      */   public void OneOverXButton()
/*      */   {
/* 1319 */     DisplayError(" ");
/*      */ 
/* 1324 */     String Display = this.lcdDisplay.getText();
/*      */ 
/* 1329 */     if ((!this.Status.equals("FIRST")) || (!Display.equals("0")))
/*      */     {
/* 1335 */       this.Result = new Double(this.lcdDisplay.getText()).doubleValue();
/*      */ 
/* 1338 */       this.Result = (1.0D / this.Result);
/*      */ 
/* 1343 */       this.lcdDisplay.setText(String.valueOf(this.Result));
/*      */ 
/* 1345 */       this.Status = "FIRST";
/*      */ 
/* 1348 */       this.OperatorKey = true;
/* 1349 */       this.FunctionKey = true;
/*      */     }
/*      */   }
/*      */ 
/*      */   public void cambioEuro() {
/* 1354 */     DisplayError(" ");
/*      */ 
/* 1359 */     String Display = this.lcdDisplay.getText();
/*      */ 
/* 1364 */     if ((!this.Status.equals("FIRST")) || (!Display.equals("0")))
/*      */     {
/* 1371 */       this.Result = new Double(this.lcdDisplay.getText()).doubleValue();
/*      */ 
/* 1374 */       this.Result /= 166.386D;
/*      */ 
/* 1379 */       this.lcdDisplay.setText(String.valueOf(this.Result));
/*      */ 
/* 1381 */       this.Status = "FIRST";
/*      */ 
/* 1384 */       this.OperatorKey = true;
/* 1385 */       this.FunctionKey = true;
/*      */     }
/*      */   }
/*      */ 
/*      */   public void cambioMonedaLocal() {
/* 1390 */     DisplayError(" ");
/*      */ 
/* 1395 */     String Display = this.lcdDisplay.getText();
/*      */ 
/* 1400 */     if ((!this.Status.equals("FIRST")) || (!Display.equals("0")))
/*      */     {
/* 1407 */       this.Result = new Double(this.lcdDisplay.getText()).doubleValue();
/*      */ 
/* 1410 */       this.Result *= 166.386D;
/*      */ 
/* 1415 */       this.lcdDisplay.setText(String.valueOf(this.Result));
/*      */ 
/* 1417 */       this.Status = "FIRST";
/*      */ 
/* 1420 */       this.OperatorKey = true;
/* 1421 */       this.FunctionKey = true;
/*      */     }
/*      */   }
/*      */ 
/*      */   public void MemoryButton(int i)
/*      */   {
/* 1429 */     DisplayError(" ");
/*      */ 
/* 1434 */     String Display = this.lcdDisplay.getText();
/*      */ 
/* 1437 */     switch (i)
/*      */     {
/*      */     case 19:
/* 1442 */       if (((Display.equals("0.")) || (Display.equals("0"))) && (this.Mem == 0.0D))
/*      */       {
/* 1445 */         this.LabelMem.setText(" ");
/*      */       }
/*      */       else
/*      */       {
/* 1454 */         double temp = new Double(this.lcdDisplay.getText()).doubleValue();
/*      */ 
/* 1458 */         this.Mem += temp;
/*      */ 
/* 1463 */         this.LabelMem.setText("M");
/*      */       }
/* 1465 */       break;
/*      */     case 21:
/* 1470 */       this.lcdDisplay.setText(String.valueOf(this.Mem));
/* 1471 */       break;
/*      */     case 20:
/* 1474 */       this.Mem = 0.0D;
/*      */ 
/* 1477 */       this.LabelMem.setText(" ");
/*      */     }
/*      */ 
/* 1480 */     this.Status = "FIRST";
/*      */ 
/* 1483 */     this.OperatorKey = true;
/*      */ 
/* 1486 */     if (this.Mem == 0.0D)
/* 1487 */       this.LabelMem.setText(" ");
/*      */   }
/*      */ 
/*      */   public void keyPressed(KeyEvent e)
/*      */   {
/* 1492 */     if ((e.isAltDown()) && (e.getKeyCode() == 80)) {
/* 1493 */       colocarResultado();
/*      */     }
/*      */     else
/*      */     {
/* 1497 */       this.currchar = e.getKeyChar();
/*      */ 
/* 1502 */       if (this.currchar == '0') NumericButton(0, false);
/* 1503 */       if (this.currchar == '1') NumericButton(1, false);
/* 1504 */       if (this.currchar == '2') NumericButton(2, false);
/* 1505 */       if (this.currchar == '3') NumericButton(3, false);
/* 1506 */       if (this.currchar == '4') NumericButton(4, false);
/* 1507 */       if (this.currchar == '5') NumericButton(5, false);
/* 1508 */       if (this.currchar == '6') NumericButton(6, false);
/* 1509 */       if (this.currchar == '7') NumericButton(7, false);
/* 1510 */       if (this.currchar == '8') NumericButton(8, false);
/* 1511 */       if (this.currchar == '9') NumericButton(9, false);
/*      */ 
/* 1522 */       if (this.currchar == '/') OperatorButton(15);
/* 1523 */       if (this.currchar == '*') OperatorButton(12);
/* 1524 */       if (this.currchar == '-') OperatorButton(11);
/* 1525 */       if (this.currchar == '+') OperatorButton(13);
/*      */ 
/* 1527 */       if (this.currchar == '=') OperatorButton(14);
/*      */ 
/* 1532 */       if (this.currchar == '\n') OperatorButton(14);
/*      */ 
/* 1537 */       if (this.currchar == '.') DecimalButton(false); 
/*      */     }
/*      */   }
/*      */ 
/*      */   public void keyReleased(KeyEvent arg0)
/*      */   {
/*      */   }
/*      */ 
/*      */   public void keyTyped(KeyEvent arg0) {
/*      */   }
/*      */ 
/* 1548 */   public void colocaOrigen(JTextField campo) { this.campoOrigen = campo; }
/*      */ 
/*      */   private void colocarResultado()
/*      */   {
/* 1552 */     if (this.campoOrigen != null) {
/* 1553 */       this.campoOrigen.setText(getResultado());
/* 1554 */       this.campoOrigen = null;
/* 1555 */       setVisible(false);
/*      */     }
/*      */   }
/*      */ 
/*      */   public String getResultado()
/*      */   {
/* 1561 */     DecimalFormatSymbols formato = new DecimalFormatSymbols();
/* 1562 */     formato.setDecimalSeparator('.');
/* 1563 */     DecimalFormat fn = new DecimalFormat("##0.00", formato);
/* 1564 */     String resultado = fn.format(this.Result);
/* 1565 */     return resultado;
/*      */   }
/*      */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.dialogosFunciones.Calculadora
 * JD-Core Version:    0.6.2
 */