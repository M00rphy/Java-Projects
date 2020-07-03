/*     */ package contaes.dialogosAuxiliares;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.MarcoInicio;
/*     */ import contaes.Puente;
/*     */ import contaes.auxiliar.DocNumPositivos;
/*     */ import contaes.auxiliar.DocVeinteCarac;
/*     */ import contaes.calendario.ICalendarTextField;
/*     */ import contaes.dialogosFunciones.Calculadora;
/*     */ import contaes.manejoDatos.ManejoFacturas;
/*     */ import contaes.manejoDatos.ManejoSubcuentas;
/*     */ import contaes.manejoDatos.ManejoVencimientos;
/*     */ import contaes.manejoDatos.TipoFactura;
/*     */ import contaes.manejoDatos.TipoVencimiento;
/*     */ import contaes.manejoDatos.funciones.ComprobarDato;
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.Container;
/*     */ import java.awt.Frame;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.KeyListener;
/*     */ import java.io.PrintStream;
/*     */ import java.net.URL;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.border.CompoundBorder;
/*     */ 
/*     */ public class CrearVencimientosV extends JDialog
/*     */   implements KeyListener, ActionListener
/*     */ {
/*  65 */   int id = -1;
/*  66 */   JPanel panel1 = new JPanel();
/*  67 */   JLabel jLabel1 = new JLabel();
/*  68 */   JLabel jLabel2 = new JLabel();
/*  69 */   JLabel jLabel3 = new JLabel();
/*  70 */   JLabel jLabel4 = new JLabel();
/*  71 */   JLabel jLabel5 = new JLabel();
/*  72 */   JLabel jLabel6 = new JLabel();
/*  73 */   ICalendarTextField cFecha = new ICalendarTextField();
/*  74 */   JTextField cFactura = new JTextField();
/*  75 */   JLabel cEjercicio = new JLabel();
/*  76 */   JTextField cImporte = new JTextField();
/*  77 */   JTextField cNum = new JTextField();
/*  78 */   JCheckBox pagado = new JCheckBox();
/*  79 */   JComboBox cajaCuentas = new JComboBox();
/*  80 */   JButton aceptar = new JButton();
/*  81 */   JButton cancelar = new JButton();
/*  82 */   GridBagLayout gbLayout = new GridBagLayout();
/*     */ 
/*  84 */   ImageIcon iconoAceptar = createImageIcon("/contaes/iconos/ok.png");
/*  85 */   ImageIcon iconoCancelar = createImageIcon("/contaes/iconos/cancel.png");
/*  86 */   private boolean paraCobrar = true;
/*     */ 
/*  88 */   private String fechaf = null;
/*  89 */   private int cuenta = 0;
/*  90 */   private boolean facturaCorrecta = false;
/*     */ 
/*  92 */   private ManejoFacturas facturaM = new ManejoFacturas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  93 */   private ManejoVencimientos vencimientoM = new ManejoVencimientos(Inicio.getCEmpresa());
/*     */ 
/*     */   public CrearVencimientosV(Frame owner, String title, boolean modal, int x, boolean pagar) {
/*  96 */     super(owner, title, modal);
/*  97 */     this.paraCobrar = (!pagar);
/*  98 */     this.id = x;
/*     */     try {
/* 100 */       setDefaultCloseOperation(2);
/* 101 */       initialize();
/* 102 */       pack();
/*     */     }
/*     */     catch (Exception exception) {
/* 105 */       exception.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void initialize()
/*     */     throws Exception
/*     */   {
/* 119 */     this.panel1.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createEtchedBorder(1)));
/*     */ 
/* 121 */     this.panel1.setLayout(this.gbLayout);
/* 122 */     this.jLabel1.setText(Mensajes.getString("fecha"));
/* 123 */     this.jLabel2.setText(Mensajes.getString("factura"));
/* 124 */     this.jLabel3.setText(Mensajes.getString("ejercicio"));
/* 125 */     this.jLabel4.setText(Mensajes.getString("importe"));
/* 126 */     this.jLabel5.setText(Mensajes.getString("numTotal"));
/* 127 */     this.jLabel6.setText(Mensajes.getString("ctaPago"));
/* 128 */     this.cEjercicio.setText(Inicio.p.getEjercicio());
/* 129 */     if (this.paraCobrar)
/* 130 */       this.pagado.setText(Mensajes.getString("cobrado"));
/* 131 */     else this.pagado.setText(Mensajes.getString("pagado"));
/* 132 */     this.cFactura.setDocument(new DocVeinteCarac());
/* 133 */     if (this.id == -1) this.aceptar.setText(Mensajes.getString("crear")); else
/* 134 */       this.aceptar.setText(Mensajes.getString("modificar"));
/* 135 */     this.cFecha.setComponente(this.cFactura);
/* 136 */     this.aceptar.setIcon(this.iconoAceptar);
/* 137 */     this.aceptar.setVerticalTextPosition(0);
/* 138 */     this.aceptar.setHorizontalTextPosition(2);
/* 139 */     this.aceptar.addActionListener(this);
/* 140 */     this.cancelar.setText(Mensajes.getString("cancelar"));
/* 141 */     this.cancelar.setIcon(this.iconoCancelar);
/* 142 */     this.cancelar.setIcon(this.iconoCancelar);
/* 143 */     this.cancelar.setVerticalTextPosition(0);
/* 144 */     this.cancelar.setHorizontalTextPosition(2);
/* 145 */     this.cancelar.addActionListener(this);
/* 146 */     this.cImporte.setDocument(new DocNumPositivos());
/*     */ 
/* 148 */     this.cFactura.addKeyListener(this);
/* 149 */     this.cImporte.addKeyListener(this);
/* 150 */     this.cNum.addKeyListener(this);
/* 151 */     this.pagado.addKeyListener(this);
/* 152 */     this.cajaCuentas.addKeyListener(this);
/* 153 */     llenarLista();
/*     */ 
/* 155 */     GridBagConstraints cons = new GridBagConstraints();
/* 156 */     cons.insets.bottom = 7;
/* 157 */     cons.insets.top = 7;
/* 158 */     cons.insets.left = 7;
/* 159 */     cons.insets.right = 7;
/*     */ 
/* 161 */     cons.gridy = 0;
/* 162 */     cons.gridx = 0;
/* 163 */     this.gbLayout.setConstraints(this.jLabel1, cons);
/* 164 */     this.panel1.add(this.jLabel1);
/* 165 */     cons.gridy = 0;
/* 166 */     cons.gridx = 1;
/* 167 */     this.gbLayout.setConstraints(this.jLabel2, cons);
/* 168 */     this.panel1.add(this.jLabel2);
/* 169 */     cons.gridy = 0;
/* 170 */     cons.gridx = 2;
/* 171 */     this.gbLayout.setConstraints(this.jLabel3, cons);
/* 172 */     this.panel1.add(this.jLabel3);
/* 173 */     cons.gridy = 2;
/* 174 */     cons.gridx = 0;
/* 175 */     this.gbLayout.setConstraints(this.jLabel4, cons);
/* 176 */     this.panel1.add(this.jLabel4);
/* 177 */     cons.gridy = 2;
/* 178 */     cons.gridx = 1;
/* 179 */     this.gbLayout.setConstraints(this.jLabel5, cons);
/* 180 */     this.panel1.add(this.jLabel5);
/* 181 */     cons.gridy = 4;
/* 182 */     cons.gridx = 0;
/* 183 */     this.gbLayout.setConstraints(this.jLabel6, cons);
/* 184 */     this.panel1.add(this.jLabel6);
/*     */ 
/* 186 */     cons.gridy = 5;
/* 187 */     cons.gridx = 0;
/* 188 */     this.gbLayout.setConstraints(this.aceptar, cons);
/* 189 */     this.panel1.add(this.aceptar);
/* 190 */     cons.gridy = 5;
/* 191 */     cons.gridx = 1;
/* 192 */     this.gbLayout.setConstraints(this.cancelar, cons);
/* 193 */     this.panel1.add(this.cancelar);
/*     */ 
/* 195 */     cons.insets.top = 0;
/* 196 */     cons.gridy = 3;
/* 197 */     cons.gridx = 2;
/* 198 */     this.gbLayout.setConstraints(this.pagado, cons);
/* 199 */     this.panel1.add(this.pagado);
/* 200 */     cons.fill = 2;
/* 201 */     cons.weighty = 1.0D;
/* 202 */     cons.gridy = 1;
/* 203 */     cons.gridx = 0;
/* 204 */     this.gbLayout.setConstraints(this.cFecha, cons);
/* 205 */     this.panel1.add(this.cFecha);
/* 206 */     cons.gridy = 1;
/* 207 */     cons.gridx = 1;
/* 208 */     this.gbLayout.setConstraints(this.cFactura, cons);
/* 209 */     this.panel1.add(this.cFactura);
/* 210 */     cons.insets.left = 20;
/* 211 */     cons.gridy = 1;
/* 212 */     cons.gridx = 2;
/* 213 */     this.gbLayout.setConstraints(this.cEjercicio, cons);
/* 214 */     this.panel1.add(this.cEjercicio);
/* 215 */     cons.insets.left = 7;
/* 216 */     cons.gridy = 3;
/* 217 */     cons.gridx = 0;
/* 218 */     this.gbLayout.setConstraints(this.cImporte, cons);
/* 219 */     this.panel1.add(this.cImporte);
/* 220 */     cons.gridy = 4;
/* 221 */     cons.gridx = 1;
/* 222 */     this.gbLayout.setConstraints(this.cajaCuentas, cons);
/* 223 */     this.panel1.add(this.cajaCuentas);
/* 224 */     cons.fill = 0;
/* 225 */     cons.ipadx = 40;
/* 226 */     cons.gridy = 3;
/* 227 */     cons.gridx = 1;
/* 228 */     this.gbLayout.setConstraints(this.cNum, cons);
/* 229 */     this.panel1.add(this.cNum);
/*     */ 
/* 231 */     getContentPane().add(this.panel1);
/*     */ 
/* 233 */     if (this.id != -1) {
/* 234 */       this.facturaCorrecta = true;
/* 235 */       colocarDatos();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void ejecutar() {
/* 240 */     if (!this.facturaCorrecta) return;
/* 241 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
/* 242 */     Date fecha_d = this.cFecha.getDate();
/* 243 */     String fecha = sdf.format(fecha_d);
/*     */ 
/* 245 */     String ejercicio = Inicio.p.getEjercicio();
/* 246 */     double importe = 0.0D;
/* 247 */     ComprobarDato dato = new ComprobarDato(this.cImporte.getText());
/* 248 */     if (dato.esDoble()) importe = Double.parseDouble(this.cImporte.getText());
/* 249 */     String cuentaPP = this.cajaCuentas.getSelectedItem().toString();
/* 250 */     int cuentaP = Integer.parseInt(cuentaPP);
/* 251 */     if (this.id == -1) {
/* 252 */       this.vencimientoM.crear(this.paraCobrar, fecha, Integer.parseInt(ejercicio), this.cFactura.getText(), this.fechaf, this.cuenta, importe, this.cNum.getText(), this.pagado.isSelected(), cuentaP);
/*     */     }
/*     */     else
/*     */     {
/* 257 */       this.vencimientoM.modificar(this.paraCobrar, this.id, fecha, Integer.parseInt(ejercicio), this.cFactura.getText(), this.fechaf, this.cuenta, importe, this.cNum.getText(), this.pagado.isSelected(), cuentaP);
/*     */     }
/*     */ 
/* 261 */     if (!this.paraCobrar) Inicio.frame.renovarTabla(1); else
/* 262 */       Inicio.frame.renovarTabla(2);
/* 263 */     dispose();
/*     */   }
/*     */ 
/*     */   private static ImageIcon createImageIcon(String path) {
/* 267 */     URL imgURL = CrearVencimientosV.class.getResource(path);
/* 268 */     if (imgURL != null)
/* 269 */       return new ImageIcon(imgURL);
/* 270 */     System.err.println("Couldn't find file: " + path);
/* 271 */     return null;
/*     */   }
/*     */ 
/*     */   private void colocarDatos() {
/* 275 */     TipoVencimiento vencimiento = this.vencimientoM.datos(this.paraCobrar, this.id);
/* 276 */     if (vencimiento != null) {
/* 277 */       String cad = vencimiento.getFecha();
/* 278 */       int year = Integer.parseInt(cad.substring(0, 4));
/* 279 */       int month = Integer.parseInt(cad.substring(5, 7)) - 1;
/* 280 */       int date = Integer.parseInt(cad.substring(8));
/* 281 */       GregorianCalendar calendario = new GregorianCalendar();
/* 282 */       calendario.set(year, month, date);
/* 283 */       this.cFecha.setCalendar(calendario);
/* 284 */       this.cEjercicio.setText(String.valueOf(vencimiento.getEjercicio()));
/* 285 */       this.cFactura.setText(vencimiento.getFactura());
/* 286 */       this.fechaf = vencimiento.getFechaf();
/* 287 */       this.cuenta = vencimiento.getCuenta();
/* 288 */       this.cImporte.setText(String.valueOf(vencimiento.getImporte()));
/* 289 */       this.cNum.setText(vencimiento.getNum());
/* 290 */       if (vencimiento.isPagado()) this.pagado.setSelected(true); else
/* 291 */         this.pagado.setSelected(false);
/* 292 */       this.cajaCuentas.setSelectedItem(String.valueOf(vencimiento.getCuentap()));
/*     */     }
/*     */   }
/*     */ 
/*     */   private void llenarLista() {
/* 297 */     ManejoSubcuentas subcuentaM = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 298 */     LinkedList listaCuenta = subcuentaM.listadoSubcuentas(57000000, 57999999);
/* 299 */     for (Iterator i$ = listaCuenta.iterator(); i$.hasNext(); ) { int x = ((Integer)i$.next()).intValue();
/* 300 */       this.cajaCuentas.addItem(String.valueOf(x)); }
/*     */   }
/*     */ 
/*     */   public void keyPressed(KeyEvent arg0) {
/* 304 */     int tecla = arg0.getKeyCode();
/* 305 */     Object campo = arg0.getSource();
/* 306 */     if ((arg0.isAltDown()) && (campo == this.cImporte)) {
/* 307 */       if (tecla == 67) {
/* 308 */         Inicio.calculadora.colocaOrigen(this.cImporte);
/* 309 */         Inicio.calculadora.setVisible(true);
/*     */       }
/* 311 */       else if (tecla == 80) {
/* 312 */         this.cImporte.setText(Inicio.calculadora.getResultado());
/*     */       }
/*     */     }
/* 315 */     if (tecla == 10)
/* 316 */       if (campo == this.cFactura) {
/* 317 */         if (!this.cFactura.getText().equals("")) {
/* 318 */           int ef = this.facturaM.existeFactura(this.paraCobrar, this.cFactura.getText());
/* 319 */           if (ef != -1) {
/* 320 */             this.facturaCorrecta = true;
/* 321 */             this.fechaf = this.facturaM.datos(this.paraCobrar, this.cFactura.getText()).getFecha();
/* 322 */             this.cuenta = this.facturaM.datos(this.paraCobrar, this.cFactura.getText()).getCuenta();
/* 323 */             this.cImporte.requestFocus();
/*     */           }
/*     */           else {
/* 326 */             JOptionPane.showMessageDialog(getContentPane(), Mensajes.getString("facturaNoDisponible"));
/*     */           }
/*     */         }
/*     */ 
/*     */       }
/* 331 */       else if (campo == this.cImporte) {
/* 332 */         this.cNum.requestFocus();
/*     */       }
/* 334 */       else if (campo == this.cNum) {
/* 335 */         this.pagado.requestFocus();
/*     */       }
/* 337 */       else if (campo == this.pagado) {
/* 338 */         this.cajaCuentas.requestFocus();
/*     */       }
/* 340 */       else if (campo == this.cajaCuentas)
/* 341 */         this.aceptar.requestFocus();
/*     */   }
/*     */ 
/*     */   public void keyReleased(KeyEvent arg0)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void keyTyped(KeyEvent arg0)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void actionPerformed(ActionEvent e)
/*     */   {
/* 354 */     Object fuente = e.getSource();
/* 355 */     if (fuente == this.cancelar) {
/* 356 */       dispose();
/*     */     }
/* 358 */     else if (fuente == this.aceptar)
/* 359 */       ejecutar();
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.dialogosAuxiliares.CrearVencimientosV
 * JD-Core Version:    0.6.2
 */