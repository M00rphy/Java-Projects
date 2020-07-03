/*     */ package almacen2.gui;
/*     */ 
/*     */ import almacen2.data.AlmacenInterno;
/*     */ import almacen2.data.PIOObject;
/*     */ import contaes.auxiliar.DocNumPositivos;
/*     */ import contaes.auxiliar.DocNumeros;
/*     */ import contaes.calendario.ICalendarTextField;
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Frame;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import javax.swing.DefaultComboBoxModel;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ 
/*     */ public class CrearSalidas extends JDialog
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  34 */   private JPanel jContentPane = null;
/*  35 */   private JLabel jLabel = null;
/*  36 */   private JLabel jLabel1 = null;
/*  37 */   private JLabel jLabel2 = null;
/*  38 */   private JLabel jLabel3 = null;
/*  39 */   private JLabel jLabel4 = null;
/*  40 */   private JButton jButton = null;
/*  41 */   private JTextField campoReferencia = null;
/*  42 */   private ICalendarTextField campoFecha = null;
/*  43 */   private JTextField campoUnidades = null;
/*  44 */   private JTextField campoPrecio = null;
/*  45 */   private JButton jButton1 = null;
/*  46 */   private JComboBox comboBox = null;
/*  47 */   private String referencia = null;
/*  48 */   private int unidades = 1;
/*  49 */   private PIOObject elemento = null;
/*     */   private ArrayList<AlmacenInterno> almacenes;
/*     */ 
/*     */   public CrearSalidas(Frame owner, String referencia, int unidades, ArrayList<AlmacenInterno> almacenes)
/*     */   {
/*  58 */     super(owner);
/*  59 */     this.referencia = referencia;
/*  60 */     this.unidades = unidades;
/*  61 */     this.almacenes = almacenes;
/*  62 */     initialize();
/*     */   }
/*     */ 
/*     */   public static PIOObject obtenerObjeto(Frame owner, String referencia, int unidades, ArrayList<AlmacenInterno> almacenes) {
/*  66 */     CrearSalidas dlg = new CrearSalidas(owner, referencia, unidades, almacenes);
/*  67 */     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/*  68 */     Dimension frameSize = dlg.getSize();
/*  69 */     if (frameSize.height > screenSize.height) {
/*  70 */       frameSize.height = screenSize.height;
/*     */     }
/*  72 */     if (frameSize.width > screenSize.width) {
/*  73 */       frameSize.width = screenSize.width;
/*     */     }
/*  75 */     dlg.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
/*     */ 
/*  77 */     dlg.pack();
/*  78 */     dlg.setVisible(true);
/*  79 */     return dlg.obtenerValorRetorno();
/*     */   }
/*     */ 
/*     */   private void initialize()
/*     */   {
/*  88 */     setSize(300, 200);
/*  89 */     setModal(true);
/*  90 */     setTitle(Mensajes.getString("menu22alma"));
/*  91 */     setContentPane(getJContentPane());
/*  92 */     this.campoUnidades.requestFocus();
/*     */   }
/*     */ 
/*     */   private JPanel getJContentPane()
/*     */   {
/* 101 */     if (this.jContentPane == null) {
/* 102 */       GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
/* 103 */       gridBagConstraints11.fill = 2;
/* 104 */       gridBagConstraints11.gridy = 4;
/* 105 */       gridBagConstraints11.weightx = 1.0D;
/* 106 */       gridBagConstraints11.insets = new Insets(3, 5, 3, 10);
/* 107 */       gridBagConstraints11.gridx = 1;
/* 108 */       GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
/* 109 */       gridBagConstraints10.gridx = 0;
/* 110 */       gridBagConstraints10.anchor = 17;
/* 111 */       gridBagConstraints10.insets = new Insets(3, 10, 3, 0);
/* 112 */       gridBagConstraints10.gridy = 4;
/* 113 */       this.jLabel4 = new JLabel();
/* 114 */       this.jLabel4.setText(Mensajes.getString("almacen"));
/* 115 */       GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
/* 116 */       gridBagConstraints9.gridx = 1;
/* 117 */       gridBagConstraints9.insets = new Insets(5, 10, 10, 0);
/* 118 */       gridBagConstraints9.anchor = 17;
/* 119 */       gridBagConstraints9.gridy = 5;
/* 120 */       GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
/* 121 */       gridBagConstraints8.fill = 2;
/* 122 */       gridBagConstraints8.gridy = 3;
/* 123 */       gridBagConstraints8.weightx = 1.0D;
/* 124 */       gridBagConstraints8.insets = new Insets(3, 5, 3, 10);
/* 125 */       gridBagConstraints8.gridx = 1;
/* 126 */       GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
/* 127 */       gridBagConstraints7.fill = 2;
/* 128 */       gridBagConstraints7.gridy = 2;
/* 129 */       gridBagConstraints7.weightx = 1.0D;
/* 130 */       gridBagConstraints7.insets = new Insets(3, 5, 3, 10);
/* 131 */       gridBagConstraints7.gridx = 1;
/* 132 */       GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
/* 133 */       gridBagConstraints6.fill = 2;
/* 134 */       gridBagConstraints6.gridy = 1;
/* 135 */       gridBagConstraints6.weightx = 1.0D;
/* 136 */       gridBagConstraints6.insets = new Insets(3, 5, 3, 10);
/* 137 */       gridBagConstraints6.gridx = 1;
/* 138 */       GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
/* 139 */       gridBagConstraints5.fill = 2;
/* 140 */       gridBagConstraints5.gridy = 0;
/* 141 */       gridBagConstraints5.weightx = 1.0D;
/* 142 */       gridBagConstraints5.insets = new Insets(10, 5, 3, 10);
/* 143 */       gridBagConstraints5.gridx = 1;
/* 144 */       GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
/* 145 */       gridBagConstraints4.gridx = 0;
/* 146 */       gridBagConstraints4.insets = new Insets(5, 10, 10, 0);
/* 147 */       gridBagConstraints4.gridy = 5;
/* 148 */       GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
/* 149 */       gridBagConstraints3.gridx = 0;
/* 150 */       gridBagConstraints3.anchor = 17;
/* 151 */       gridBagConstraints3.insets = new Insets(3, 10, 3, 0);
/* 152 */       gridBagConstraints3.gridy = 3;
/* 153 */       this.jLabel3 = new JLabel();
/* 154 */       this.jLabel3.setText(Mensajes.getString("precio"));
/* 155 */       GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
/* 156 */       gridBagConstraints2.gridx = 0;
/* 157 */       gridBagConstraints2.anchor = 17;
/* 158 */       gridBagConstraints2.insets = new Insets(3, 10, 3, 0);
/* 159 */       gridBagConstraints2.gridy = 2;
/* 160 */       this.jLabel2 = new JLabel();
/* 161 */       this.jLabel2.setText(Mensajes.getString("unidades"));
/* 162 */       GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
/* 163 */       gridBagConstraints1.gridx = 0;
/* 164 */       gridBagConstraints1.anchor = 17;
/* 165 */       gridBagConstraints1.insets = new Insets(3, 10, 3, 0);
/* 166 */       gridBagConstraints1.gridy = 1;
/* 167 */       this.jLabel1 = new JLabel();
/* 168 */       this.jLabel1.setText(Mensajes.getString("fecha"));
/* 169 */       GridBagConstraints gridBagConstraints = new GridBagConstraints();
/* 170 */       gridBagConstraints.gridx = 0;
/* 171 */       gridBagConstraints.anchor = 17;
/* 172 */       gridBagConstraints.insets = new Insets(10, 10, 3, 0);
/* 173 */       gridBagConstraints.gridy = 0;
/* 174 */       this.jLabel = new JLabel();
/* 175 */       this.jLabel.setText(Mensajes.getString("referencia"));
/* 176 */       this.jContentPane = new JPanel();
/* 177 */       this.jContentPane.setLayout(new GridBagLayout());
/* 178 */       this.jContentPane.add(this.jLabel, gridBagConstraints);
/* 179 */       this.jContentPane.add(this.jLabel1, gridBagConstraints1);
/* 180 */       this.jContentPane.add(this.jLabel2, gridBagConstraints2);
/* 181 */       this.jContentPane.add(this.jLabel3, gridBagConstraints3);
/* 182 */       this.jContentPane.add(this.jLabel4, gridBagConstraints10);
/* 183 */       this.jContentPane.add(getJButton(), gridBagConstraints4);
/* 184 */       this.jContentPane.add(getCampoReferencia(), gridBagConstraints5);
/* 185 */       this.jContentPane.add(getCampoFecha(), gridBagConstraints6);
/* 186 */       this.jContentPane.add(getCampoUnidades(), gridBagConstraints7);
/* 187 */       this.jContentPane.add(getCampoPrecio(), gridBagConstraints8);
/* 188 */       this.jContentPane.add(getJButton1(), gridBagConstraints9);
/* 189 */       this.jContentPane.add(getComboBox(), gridBagConstraints11);
/*     */     }
/* 191 */     return this.jContentPane;
/*     */   }
/*     */ 
/*     */   private JButton getJButton()
/*     */   {
/* 200 */     if (this.jButton == null) {
/* 201 */       this.jButton = new JButton();
/* 202 */       this.jButton.setText(Mensajes.getString("bien"));
/* 203 */       this.jButton.addActionListener(new ActionListener()
/*     */       {
/*     */         public void actionPerformed(ActionEvent e) {
/* 206 */           CrearSalidas.this.botonBien();
/*     */         }
/*     */       });
/*     */     }
/* 210 */     return this.jButton;
/*     */   }
/*     */ 
/*     */   private JTextField getCampoReferencia()
/*     */   {
/* 219 */     if (this.campoReferencia == null) {
/* 220 */       this.campoReferencia = new JTextField();
/* 221 */       this.campoReferencia.setEditable(false);
/* 222 */       this.campoReferencia.setText(this.referencia);
/*     */     }
/* 224 */     return this.campoReferencia;
/*     */   }
/*     */ 
/*     */   private ICalendarTextField getCampoFecha()
/*     */   {
/* 233 */     if (this.campoFecha == null) {
/* 234 */       this.campoFecha = new ICalendarTextField();
/* 235 */       this.campoFecha.setPreferredSize(new Dimension(125, 26));
/* 236 */       this.campoFecha.setDate(new Date());
/*     */     }
/* 238 */     return this.campoFecha;
/*     */   }
/*     */ 
/*     */   private JTextField getCampoUnidades()
/*     */   {
/* 247 */     if (this.campoUnidades == null) {
/* 248 */       this.campoUnidades = new JTextField();
/* 249 */       this.campoUnidades.setDocument(new DocNumPositivos());
/* 250 */       this.campoUnidades.setText(Integer.toString(this.unidades));
/*     */     }
/* 252 */     return this.campoUnidades;
/*     */   }
/*     */ 
/*     */   private JTextField getCampoPrecio()
/*     */   {
/* 261 */     if (this.campoPrecio == null) {
/* 262 */       this.campoPrecio = new JTextField();
/* 263 */       this.campoPrecio.setDocument(new DocNumeros());
/*     */     }
/* 265 */     return this.campoPrecio;
/*     */   }
/*     */ 
/*     */   private JButton getJButton1()
/*     */   {
/* 274 */     if (this.jButton1 == null) {
/* 275 */       this.jButton1 = new JButton();
/* 276 */       this.jButton1.setText(Mensajes.getString("cancelar"));
/* 277 */       this.jButton1.addActionListener(new ActionListener()
/*     */       {
/*     */         public void actionPerformed(ActionEvent e) {
/* 280 */           CrearSalidas.this.dispose();
/*     */         }
/*     */       });
/*     */     }
/* 284 */     return this.jButton1;
/*     */   }
/*     */ 
/*     */   private JComboBox getComboBox() {
/* 288 */     if (this.comboBox == null) {
/* 289 */       this.comboBox = new JComboBox();
/* 290 */       DefaultComboBoxModel modelo = new DefaultComboBoxModel();
/* 291 */       for (AlmacenInterno almacenInterno : this.almacenes) {
/* 292 */         modelo.addElement(almacenInterno);
/*     */       }
/* 294 */       this.comboBox.setModel(modelo);
/*     */     }
/* 296 */     return this.comboBox;
/*     */   }
/*     */ 
/*     */   private void botonBien() {
/* 300 */     if ((this.campoFecha.getText().equals("")) || (this.campoUnidades.getText().equals("")) || (this.campoPrecio.getText().equals(""))) {
/* 301 */       return;
/*     */     }
/* 303 */     this.unidades = Integer.parseInt(this.campoUnidades.getText());
/* 304 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/* 305 */     Date fecha_d = this.campoFecha.getDate();
/* 306 */     String fecha = fecha_d != null ? sdf.format(fecha_d) : "";
/* 307 */     AlmacenInterno almacen = (AlmacenInterno)this.comboBox.getSelectedItem();
/* 308 */     this.elemento = new PIOObject();
/*     */ 
/* 310 */     this.elemento.setFecha(fecha);
/* 311 */     this.elemento.setImporte(Double.parseDouble(this.campoPrecio.getText()));
/* 312 */     this.elemento.setIO(this.unidades);
/* 313 */     if (almacen != null)
/* 314 */       this.elemento.setAlmacen(almacen.getId().intValue());
/*     */     else {
/* 316 */       this.elemento.setAlmacen(1);
/*     */     }
/* 318 */     dispose();
/*     */   }
/*     */ 
/*     */   private PIOObject obtenerValorRetorno() {
/* 322 */     return this.elemento;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.gui.CrearSalidas
 * JD-Core Version:    0.6.2
 */