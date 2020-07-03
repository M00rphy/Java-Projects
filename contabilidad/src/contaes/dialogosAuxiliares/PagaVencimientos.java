/*     */ package contaes.dialogosAuxiliares;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.Puente;
/*     */ import contaes.calendario.ICalendarTextField;
/*     */ import contaes.manejoDatos.ManejoSubcuentas;
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
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.border.CompoundBorder;
/*     */ 
/*     */ public class PagaVencimientos extends JDialog
/*     */   implements ActionListener
/*     */ {
/*     */   private static final String ACEPTAR = "aceptar";
/*     */   private static final String CANCELAR = "cancelar";
/*  58 */   String fecha = "";
/*  59 */   Date date = new Date();
/*  60 */   int cuenta = -1;
/*     */   String cuentaS;
/*  62 */   boolean crearAsto = true;
/*     */ 
/*  64 */   JPanel panel1 = new JPanel();
/*  65 */   GridBagLayout gbLayout = new GridBagLayout();
/*  66 */   JLabel etiq1 = new JLabel();
/*  67 */   JLabel etiq2 = new JLabel();
/*  68 */   ICalendarTextField campoFecha = new ICalendarTextField();
/*  69 */   JComboBox cajaCuentas = new JComboBox();
/*  70 */   JCheckBox crearAsiento = new JCheckBox();
/*  71 */   JButton aceptar = new JButton();
/*  72 */   JButton cancelar = new JButton();
/*  73 */   ImageIcon iconoAceptar = createImageIcon("/contaes/iconos/ok.png");
/*  74 */   ImageIcon iconoCancelar = createImageIcon("/contaes/iconos/cancel.png");
/*     */ 
/*     */   public PagaVencimientos(Frame owner, String title, boolean modal, String cuentaPago, Date fecha) {
/*  77 */     super(owner, title, modal);
/*  78 */     this.cuentaS = cuentaPago;
/*  79 */     this.date = fecha;
/*     */     try {
/*  81 */       setDefaultCloseOperation(2);
/*  82 */       initialize();
/*  83 */       pack();
/*     */     }
/*     */     catch (Exception exception) {
/*  86 */       exception.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public PagaVencimientos(Frame owner, String title, boolean modal, String cuentaPago) {
/*  91 */     this(owner, title, modal, cuentaPago, new Date());
/*     */   }
/*     */ 
/*     */   public PagaVencimientos(Frame owner, String title, Date fecha, boolean modal) {
/*  95 */     this(owner, title, modal, "57000000", fecha);
/*     */   }
/*     */ 
/*     */   private void initialize() throws Exception {
/*  99 */     this.etiq1.setText(Mensajes.getString("fecha"));
/* 100 */     this.etiq2.setText(Mensajes.getString("ctaPago"));
/* 101 */     this.aceptar.setText(Mensajes.getString("aceptar"));
/* 102 */     this.aceptar.setIcon(this.iconoAceptar);
/* 103 */     this.aceptar.setVerticalTextPosition(0);
/* 104 */     this.aceptar.setHorizontalTextPosition(2);
/* 105 */     this.aceptar.setActionCommand("aceptar");
/* 106 */     this.aceptar.addActionListener(this);
/* 107 */     this.cancelar.setText(Mensajes.getString("cancelar"));
/* 108 */     this.cancelar.setIcon(this.iconoCancelar);
/* 109 */     this.cancelar.setVerticalTextPosition(0);
/* 110 */     this.cancelar.setHorizontalTextPosition(2);
/* 111 */     this.cancelar.setActionCommand("cancelar");
/* 112 */     this.cancelar.addActionListener(this);
/* 113 */     this.crearAsiento.setText(Mensajes.getString("astoPago"));
/* 114 */     this.crearAsiento.setSelected(true);
/* 115 */     this.campoFecha.setMinimumSize(new Dimension(120, 26));
/* 116 */     this.campoFecha.setToolTipText(Mensajes.getString("formatoFecha"));
/*     */ 
/* 118 */     this.campoFecha.setDate(this.date);
/* 119 */     llenarLista();
/* 120 */     this.cajaCuentas.setSelectedItem(this.cuentaS);
/*     */ 
/* 122 */     this.panel1.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createEtchedBorder(1)));
/*     */ 
/* 124 */     this.panel1.setLayout(this.gbLayout);
/*     */ 
/* 126 */     GridBagConstraints cons = new GridBagConstraints();
/* 127 */     cons.insets.bottom = 7;
/* 128 */     cons.insets.top = 7;
/* 129 */     cons.insets.left = 7;
/* 130 */     cons.insets.right = 7;
/*     */ 
/* 132 */     cons.gridy = 0;
/* 133 */     cons.gridx = 0;
/* 134 */     this.gbLayout.setConstraints(this.etiq1, cons);
/* 135 */     this.panel1.add(this.etiq1);
/*     */ 
/* 137 */     cons.gridx = 1;
/* 138 */     this.gbLayout.setConstraints(this.etiq2, cons);
/* 139 */     this.panel1.add(this.etiq2);
/*     */ 
/* 142 */     cons.fill = 2;
/* 143 */     cons.gridy = 1;
/* 144 */     cons.gridx = 0;
/* 145 */     this.gbLayout.setConstraints(this.campoFecha, cons);
/* 146 */     this.panel1.add(this.campoFecha);
/*     */ 
/* 148 */     cons.fill = 0;
/* 149 */     cons.gridx = 1;
/* 150 */     this.gbLayout.setConstraints(this.cajaCuentas, cons);
/* 151 */     this.panel1.add(this.cajaCuentas);
/*     */ 
/* 154 */     cons.anchor = 10;
/* 155 */     cons.gridy = 3;
/* 156 */     cons.gridx = 0;
/* 157 */     this.gbLayout.setConstraints(this.aceptar, cons);
/* 158 */     this.panel1.add(this.aceptar);
/*     */ 
/* 160 */     cons.gridx = 1;
/* 161 */     this.gbLayout.setConstraints(this.cancelar, cons);
/* 162 */     this.panel1.add(this.cancelar);
/*     */ 
/* 164 */     cons.gridy = 2;
/* 165 */     cons.gridx = 0;
/* 166 */     cons.gridwidth = 2;
/* 167 */     this.gbLayout.setConstraints(this.crearAsiento, cons);
/* 168 */     this.panel1.add(this.crearAsiento);
/*     */ 
/* 170 */     getContentPane().add(this.panel1);
/*     */   }
/*     */ 
/*     */   public void actionPerformed(ActionEvent e) {
/* 174 */     String cmd = e.getActionCommand();
/*     */ 
/* 176 */     if ("aceptar".equals(cmd)) {
/* 177 */       String t = this.cajaCuentas.getSelectedItem().toString();
/* 178 */       this.cuenta = Integer.parseInt(t);
/* 179 */       SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
/* 180 */       Date fecha_d = this.campoFecha.getDate();
/* 181 */       this.fecha = sdf.format(fecha_d);
/* 182 */       if (!this.crearAsiento.isSelected()) this.crearAsto = false;
/* 183 */       dispose();
/*     */     }
/* 185 */     else if ("cancelar".equals(cmd)) {
/* 186 */       this.cuenta = -1;
/* 187 */       dispose();
/*     */     }
/*     */   }
/*     */ 
/*     */   public int enCuenta() {
/* 192 */     return this.cuenta;
/*     */   }
/*     */ 
/*     */   public String enfecha() {
/* 196 */     return this.fecha;
/*     */   }
/*     */ 
/*     */   public boolean crearA() {
/* 200 */     return this.crearAsto;
/*     */   }
/*     */ 
/*     */   protected void llenarLista() {
/* 204 */     ManejoSubcuentas cuentaM = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 205 */     LinkedList listaS = cuentaM.listadoSubcuentas(57000000, 57999999);
/* 206 */     for (Iterator i$ = listaS.iterator(); i$.hasNext(); ) { int x = ((Integer)i$.next()).intValue();
/* 207 */       this.cajaCuentas.addItem(String.valueOf(x)); }
/*     */   }
/*     */ 
/*     */   protected static ImageIcon createImageIcon(String path) {
/* 211 */     URL imgURL = PagaVencimientos.class.getResource(path);
/* 212 */     if (imgURL != null)
/* 213 */       return new ImageIcon(imgURL);
/* 214 */     System.err.println("Couldn't find file: " + path);
/* 215 */     return null;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.dialogosAuxiliares.PagaVencimientos
 * JD-Core Version:    0.6.2
 */