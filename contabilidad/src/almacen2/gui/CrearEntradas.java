/*     */ package almacen2.gui;
/*     */ 
/*     */ import almacen2.data.AlmacenInterno;
/*     */ import almacen2.data.PIOObject;
/*     */ import contaes.auxiliar.DocNumPositivos;
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
/*     */ public class CrearEntradas extends JDialog
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  32 */   private JPanel jContentPane = null;
/*  33 */   private JLabel jLabel = null;
/*  34 */   private JLabel jLabel1 = null;
/*  35 */   private JLabel jLabel2 = null;
/*  36 */   private JLabel jLabel4 = null;
/*  37 */   private JButton jButton = null;
/*  38 */   private JTextField campoReferencia = null;
/*  39 */   private ICalendarTextField campoFecha = null;
/*  40 */   private JTextField campoUnidades = null;
/*  41 */   private JButton jButton1 = null;
/*  42 */   private JComboBox comboBox = null;
/*  43 */   private String referencia = null;
/*  44 */   private int unidades = 1;
/*  45 */   private PIOObject elemento = null;
/*     */   private ArrayList<AlmacenInterno> almacenes;
/*     */ 
/*     */   public CrearEntradas(Frame owner, String referencia, int unidades, ArrayList<AlmacenInterno> almacenes)
/*     */   {
/*  54 */     super(owner);
/*  55 */     this.referencia = referencia;
/*  56 */     this.unidades = unidades;
/*  57 */     this.almacenes = almacenes;
/*  58 */     initialize();
/*     */   }
/*     */ 
/*     */   public static PIOObject obtenerObjeto(Frame owner, String referencia, int unidades, ArrayList<AlmacenInterno> almacenes) {
/*  62 */     CrearEntradas dlg = new CrearEntradas(owner, referencia, unidades, almacenes);
/*  63 */     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/*  64 */     Dimension frameSize = dlg.getSize();
/*  65 */     if (frameSize.height > screenSize.height) {
/*  66 */       frameSize.height = screenSize.height;
/*     */     }
/*  68 */     if (frameSize.width > screenSize.width) {
/*  69 */       frameSize.width = screenSize.width;
/*     */     }
/*  71 */     dlg.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
/*     */ 
/*  73 */     dlg.pack();
/*  74 */     dlg.setVisible(true);
/*  75 */     return dlg.obtenerValorRetorno();
/*     */   }
/*     */ 
/*     */   private void initialize()
/*     */   {
/*  84 */     setSize(300, 200);
/*  85 */     setModal(true);
/*  86 */     setTitle(Mensajes.getString("menu21alma"));
/*  87 */     setContentPane(getJContentPane());
/*  88 */     this.campoUnidades.requestFocus();
/*     */   }
/*     */ 
/*     */   private JPanel getJContentPane()
/*     */   {
/*  97 */     if (this.jContentPane == null) {
/*  98 */       GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
/*  99 */       gridBagConstraints11.fill = 2;
/* 100 */       gridBagConstraints11.gridy = 4;
/* 101 */       gridBagConstraints11.weightx = 1.0D;
/* 102 */       gridBagConstraints11.insets = new Insets(3, 5, 3, 10);
/* 103 */       gridBagConstraints11.gridx = 1;
/* 104 */       GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
/* 105 */       gridBagConstraints10.gridx = 0;
/* 106 */       gridBagConstraints10.anchor = 17;
/* 107 */       gridBagConstraints10.insets = new Insets(3, 10, 3, 0);
/* 108 */       gridBagConstraints10.gridy = 4;
/* 109 */       this.jLabel4 = new JLabel();
/* 110 */       this.jLabel4.setText(Mensajes.getString("almacen"));
/* 111 */       GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
/* 112 */       gridBagConstraints9.gridx = 1;
/* 113 */       gridBagConstraints9.insets = new Insets(5, 10, 10, 0);
/* 114 */       gridBagConstraints9.anchor = 17;
/* 115 */       gridBagConstraints9.gridy = 5;
/* 116 */       GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
/* 117 */       gridBagConstraints7.fill = 2;
/* 118 */       gridBagConstraints7.gridy = 2;
/* 119 */       gridBagConstraints7.weightx = 1.0D;
/* 120 */       gridBagConstraints7.insets = new Insets(3, 5, 3, 10);
/* 121 */       gridBagConstraints7.gridx = 1;
/* 122 */       GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
/* 123 */       gridBagConstraints6.fill = 2;
/* 124 */       gridBagConstraints6.gridy = 1;
/* 125 */       gridBagConstraints6.weightx = 1.0D;
/* 126 */       gridBagConstraints6.insets = new Insets(3, 5, 3, 10);
/* 127 */       gridBagConstraints6.gridx = 1;
/* 128 */       GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
/* 129 */       gridBagConstraints5.fill = 2;
/* 130 */       gridBagConstraints5.gridy = 0;
/* 131 */       gridBagConstraints5.weightx = 1.0D;
/* 132 */       gridBagConstraints5.insets = new Insets(10, 5, 3, 10);
/* 133 */       gridBagConstraints5.gridx = 1;
/* 134 */       GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
/* 135 */       gridBagConstraints4.gridx = 0;
/* 136 */       gridBagConstraints4.insets = new Insets(5, 10, 10, 0);
/* 137 */       gridBagConstraints4.gridy = 5;
/* 138 */       GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
/* 139 */       gridBagConstraints2.gridx = 0;
/* 140 */       gridBagConstraints2.anchor = 17;
/* 141 */       gridBagConstraints2.insets = new Insets(3, 10, 3, 0);
/* 142 */       gridBagConstraints2.gridy = 2;
/* 143 */       this.jLabel2 = new JLabel();
/* 144 */       this.jLabel2.setText(Mensajes.getString("unidades"));
/* 145 */       GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
/* 146 */       gridBagConstraints1.gridx = 0;
/* 147 */       gridBagConstraints1.anchor = 17;
/* 148 */       gridBagConstraints1.insets = new Insets(3, 10, 3, 0);
/* 149 */       gridBagConstraints1.gridy = 1;
/* 150 */       this.jLabel1 = new JLabel();
/* 151 */       this.jLabel1.setText(Mensajes.getString("fecha"));
/* 152 */       GridBagConstraints gridBagConstraints = new GridBagConstraints();
/* 153 */       gridBagConstraints.gridx = 0;
/* 154 */       gridBagConstraints.anchor = 17;
/* 155 */       gridBagConstraints.insets = new Insets(10, 10, 3, 0);
/* 156 */       gridBagConstraints.gridy = 0;
/* 157 */       this.jLabel = new JLabel();
/* 158 */       this.jLabel.setText(Mensajes.getString("referencia"));
/* 159 */       this.jContentPane = new JPanel();
/* 160 */       this.jContentPane.setLayout(new GridBagLayout());
/* 161 */       this.jContentPane.add(this.jLabel, gridBagConstraints);
/* 162 */       this.jContentPane.add(this.jLabel1, gridBagConstraints1);
/* 163 */       this.jContentPane.add(this.jLabel2, gridBagConstraints2);
/* 164 */       this.jContentPane.add(this.jLabel4, gridBagConstraints10);
/* 165 */       this.jContentPane.add(getJButton(), gridBagConstraints4);
/* 166 */       this.jContentPane.add(getCampoReferencia(), gridBagConstraints5);
/* 167 */       this.jContentPane.add(getCampoFecha(), gridBagConstraints6);
/* 168 */       this.jContentPane.add(getCampoUnidades(), gridBagConstraints7);
/* 169 */       this.jContentPane.add(getJButton1(), gridBagConstraints9);
/* 170 */       this.jContentPane.add(getComboBox(), gridBagConstraints11);
/*     */     }
/* 172 */     return this.jContentPane;
/*     */   }
/*     */ 
/*     */   private JButton getJButton()
/*     */   {
/* 181 */     if (this.jButton == null) {
/* 182 */       this.jButton = new JButton();
/* 183 */       this.jButton.setText(Mensajes.getString("bien"));
/* 184 */       this.jButton.addActionListener(new ActionListener()
/*     */       {
/*     */         public void actionPerformed(ActionEvent e) {
/* 187 */           CrearEntradas.this.botonBien();
/*     */         }
/*     */       });
/*     */     }
/* 191 */     return this.jButton;
/*     */   }
/*     */ 
/*     */   private JTextField getCampoReferencia()
/*     */   {
/* 200 */     if (this.campoReferencia == null) {
/* 201 */       this.campoReferencia = new JTextField();
/* 202 */       this.campoReferencia.setEditable(false);
/* 203 */       this.campoReferencia.setText(this.referencia);
/*     */     }
/* 205 */     return this.campoReferencia;
/*     */   }
/*     */ 
/*     */   private ICalendarTextField getCampoFecha()
/*     */   {
/* 214 */     if (this.campoFecha == null) {
/* 215 */       this.campoFecha = new ICalendarTextField();
/* 216 */       this.campoFecha.setPreferredSize(new Dimension(125, 26));
/* 217 */       this.campoFecha.setDate(new Date());
/*     */     }
/* 219 */     return this.campoFecha;
/*     */   }
/*     */ 
/*     */   private JTextField getCampoUnidades()
/*     */   {
/* 228 */     if (this.campoUnidades == null) {
/* 229 */       this.campoUnidades = new JTextField();
/* 230 */       this.campoUnidades.setDocument(new DocNumPositivos());
/* 231 */       this.campoUnidades.setText(Integer.toString(this.unidades));
/*     */     }
/* 233 */     return this.campoUnidades;
/*     */   }
/*     */ 
/*     */   private JButton getJButton1()
/*     */   {
/* 242 */     if (this.jButton1 == null) {
/* 243 */       this.jButton1 = new JButton();
/* 244 */       this.jButton1.setText(Mensajes.getString("cancelar"));
/* 245 */       this.jButton1.addActionListener(new ActionListener()
/*     */       {
/*     */         public void actionPerformed(ActionEvent e) {
/* 248 */           CrearEntradas.this.dispose();
/*     */         }
/*     */       });
/*     */     }
/* 252 */     return this.jButton1;
/*     */   }
/*     */ 
/*     */   private JComboBox getComboBox() {
/* 256 */     if (this.comboBox == null) {
/* 257 */       this.comboBox = new JComboBox();
/* 258 */       DefaultComboBoxModel modelo = new DefaultComboBoxModel();
/* 259 */       for (AlmacenInterno almacenInterno : this.almacenes) {
/* 260 */         modelo.addElement(almacenInterno);
/*     */       }
/* 262 */       this.comboBox.setModel(modelo);
/*     */     }
/* 264 */     return this.comboBox;
/*     */   }
/*     */ 
/*     */   private void botonBien() {
/* 268 */     this.unidades = Integer.parseInt(this.campoUnidades.getText());
/* 269 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/* 270 */     Date fecha_d = this.campoFecha.getDate();
/* 271 */     String fecha = fecha_d != null ? sdf.format(fecha_d) : "";
/* 272 */     AlmacenInterno almacen = (AlmacenInterno)this.comboBox.getSelectedItem();
/* 273 */     this.elemento = new PIOObject();
/*     */ 
/* 275 */     this.elemento.setFecha(fecha);
/* 276 */     this.elemento.setIO(this.unidades);
/* 277 */     if (almacen != null)
/* 278 */       this.elemento.setAlmacen(almacen.getId().intValue());
/*     */     else {
/* 280 */       this.elemento.setAlmacen(1);
/*     */     }
/* 282 */     dispose();
/*     */   }
/*     */ 
/*     */   private PIOObject obtenerValorRetorno() {
/* 286 */     return this.elemento;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.gui.CrearEntradas
 * JD-Core Version:    0.6.2
 */