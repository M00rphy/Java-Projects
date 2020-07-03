/*     */ package contaes.cuentas;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.MarcoInicio;
/*     */ import contaes.Puente;
/*     */ import contaes.manejoDatos.ManejoSubcuentas;
/*     */ import contaes.manejoDatos.TipoSubcuenta;
/*     */ import contaes.manejoDatos.funciones.RegeneraSaldos;
/*     */ import contaes.manejoDatos.funciones.SustitucionDeCuentas;
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.Color;
/*     */ import java.awt.Frame;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.HeadlessException;
/*     */ import java.awt.Insets;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.ItemEvent;
/*     */ import java.awt.event.ItemListener;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.DefaultComboBoxModel;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ 
/*     */ public class Sustitucion extends JDialog
/*     */ {
/*  29 */   private JPanel jContentPane = null;
/*  30 */   private JLabel jLabel = null;
/*  31 */   private JComboBox listaCuentas1 = null;
/*  32 */   private JTextField nombre1 = null;
/*  33 */   private JLabel jLabel1 = null;
/*  34 */   private JComboBox listaCuentas2 = null;
/*  35 */   private JTextField nombre2 = null;
/*  36 */   private JPanel jPanel = null;
/*  37 */   private JLabel jLabel2 = null;
/*  38 */   private JCheckBox enDiario = null;
/*  39 */   private JCheckBox enFactEmitidas = null;
/*  40 */   private JCheckBox enFactRecibidas = null;
/*  41 */   private JButton bSustituir = null;
/*  42 */   private JButton bCancelar = null;
/*     */ 
/*     */   public Sustitucion() throws HeadlessException
/*     */   {
/*  46 */     initialize();
/*     */   }
/*     */ 
/*     */   public Sustitucion(Frame arg0, boolean arg1) throws HeadlessException {
/*  50 */     super(arg0, arg1);
/*  51 */     initialize();
/*     */   }
/*     */ 
/*     */   private void initialize()
/*     */   {
/*  61 */     setTitle(Mensajes.getString("sustituCuentas"));
/*  62 */     setContentPane(getJContentPane());
/*     */   }
/*     */ 
/*     */   private JPanel getJContentPane()
/*     */   {
/*  71 */     if (this.jContentPane == null) {
/*  72 */       GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
/*  73 */       gridBagConstraints13.gridx = 1;
/*  74 */       gridBagConstraints13.insets = new Insets(5, 0, 5, 0);
/*  75 */       gridBagConstraints13.gridy = 5;
/*  76 */       GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
/*  77 */       gridBagConstraints12.gridx = 0;
/*  78 */       gridBagConstraints12.insets = new Insets(5, 10, 5, 0);
/*  79 */       gridBagConstraints12.gridy = 5;
/*  80 */       GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
/*  81 */       gridBagConstraints6.gridx = 0;
/*  82 */       gridBagConstraints6.fill = 2;
/*  83 */       gridBagConstraints6.gridwidth = 2;
/*  84 */       gridBagConstraints6.insets = new Insets(3, 3, 3, 3);
/*  85 */       gridBagConstraints6.gridy = 4;
/*  86 */       GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
/*  87 */       gridBagConstraints5.fill = 2;
/*  88 */       gridBagConstraints5.gridy = 3;
/*  89 */       gridBagConstraints5.weightx = 1.0D;
/*  90 */       gridBagConstraints5.insets = new Insets(5, 0, 5, 5);
/*  91 */       gridBagConstraints5.ipadx = 200;
/*  92 */       gridBagConstraints5.gridx = 1;
/*  93 */       GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
/*  94 */       gridBagConstraints4.fill = 2;
/*  95 */       gridBagConstraints4.gridy = 3;
/*  96 */       gridBagConstraints4.weightx = 1.0D;
/*  97 */       gridBagConstraints4.insets = new Insets(5, 5, 5, 10);
/*  98 */       gridBagConstraints4.gridx = 0;
/*  99 */       GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
/* 100 */       gridBagConstraints3.gridx = 0;
/* 101 */       gridBagConstraints3.anchor = 17;
/* 102 */       gridBagConstraints3.insets = new Insets(5, 5, 0, 0);
/* 103 */       gridBagConstraints3.gridy = 2;
/* 104 */       this.jLabel1 = new JLabel();
/* 105 */       this.jLabel1.setText(Mensajes.getString("por"));
/* 106 */       GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
/* 107 */       gridBagConstraints2.fill = 2;
/* 108 */       gridBagConstraints2.gridy = 1;
/* 109 */       gridBagConstraints2.weightx = 1.0D;
/* 110 */       gridBagConstraints2.insets = new Insets(5, 0, 0, 5);
/* 111 */       gridBagConstraints2.ipadx = 200;
/* 112 */       gridBagConstraints2.gridx = 1;
/* 113 */       GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
/* 114 */       gridBagConstraints1.fill = 2;
/* 115 */       gridBagConstraints1.gridy = 1;
/* 116 */       gridBagConstraints1.weightx = 1.0D;
/* 117 */       gridBagConstraints1.insets = new Insets(5, 5, 0, 10);
/* 118 */       gridBagConstraints1.gridx = 0;
/* 119 */       GridBagConstraints gridBagConstraints = new GridBagConstraints();
/* 120 */       gridBagConstraints.gridx = 0;
/* 121 */       gridBagConstraints.anchor = 17;
/* 122 */       gridBagConstraints.insets = new Insets(10, 5, 0, 0);
/* 123 */       gridBagConstraints.gridy = 0;
/* 124 */       this.jLabel = new JLabel();
/* 125 */       this.jLabel.setText(Mensajes.getString("sustituir"));
/* 126 */       this.jContentPane = new JPanel();
/* 127 */       this.jContentPane.setLayout(new GridBagLayout());
/* 128 */       this.jContentPane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createEtchedBorder(1)));
/* 129 */       this.jContentPane.add(this.jLabel, gridBagConstraints);
/* 130 */       this.jContentPane.add(getListaCuentas1(), gridBagConstraints1);
/* 131 */       this.jContentPane.add(getNombre1(), gridBagConstraints2);
/* 132 */       this.jContentPane.add(this.jLabel1, gridBagConstraints3);
/* 133 */       this.jContentPane.add(getListaCuentas2(), gridBagConstraints4);
/* 134 */       this.jContentPane.add(getNombre2(), gridBagConstraints5);
/* 135 */       this.jContentPane.add(getJPanel(), gridBagConstraints6);
/* 136 */       this.jContentPane.add(getBSustituir(), gridBagConstraints12);
/* 137 */       this.jContentPane.add(getBCancelar(), gridBagConstraints13);
/*     */     }
/* 139 */     return this.jContentPane;
/*     */   }
/*     */ 
/*     */   private JComboBox getListaCuentas1()
/*     */   {
/* 148 */     if (this.listaCuentas1 == null) {
/* 149 */       this.listaCuentas1 = new JComboBox();
/* 150 */       rellenarCombo(this.listaCuentas1);
/* 151 */       this.listaCuentas1.addItemListener(new ItemListener()
/*     */       {
/*     */         public void itemStateChanged(ItemEvent e) {
/* 154 */           Sustitucion.this.nombre1.setText(Sustitucion.this.nombreCuenta(Sustitucion.this.listaCuentas1.getSelectedItem().toString()));
/*     */         }
/*     */       });
/*     */     }
/* 158 */     return this.listaCuentas1;
/*     */   }
/*     */ 
/*     */   private JTextField getNombre1()
/*     */   {
/* 167 */     if (this.nombre1 == null) {
/* 168 */       this.nombre1 = new JTextField();
/* 169 */       this.nombre1.setEditable(false);
/* 170 */       this.nombre1.setBackground(new Color(225, 225, 225));
/*     */     }
/* 172 */     return this.nombre1;
/*     */   }
/*     */ 
/*     */   private JComboBox getListaCuentas2()
/*     */   {
/* 181 */     if (this.listaCuentas2 == null) {
/* 182 */       this.listaCuentas2 = new JComboBox();
/* 183 */       rellenarCombo(this.listaCuentas2);
/* 184 */       this.listaCuentas2.addItemListener(new ItemListener()
/*     */       {
/*     */         public void itemStateChanged(ItemEvent e) {
/* 187 */           Sustitucion.this.nombre2.setText(Sustitucion.this.nombreCuenta(Sustitucion.this.listaCuentas2.getSelectedItem().toString()));
/*     */         }
/*     */       });
/*     */     }
/* 191 */     return this.listaCuentas2;
/*     */   }
/*     */ 
/*     */   private JTextField getNombre2()
/*     */   {
/* 200 */     if (this.nombre2 == null) {
/* 201 */       this.nombre2 = new JTextField();
/* 202 */       this.nombre2.setEditable(false);
/* 203 */       this.nombre2.setBackground(new Color(225, 225, 225));
/*     */     }
/* 205 */     return this.nombre2;
/*     */   }
/*     */ 
/*     */   private JPanel getJPanel()
/*     */   {
/* 214 */     if (this.jPanel == null) {
/* 215 */       GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
/* 216 */       gridBagConstraints11.anchor = 17;
/* 217 */       gridBagConstraints11.gridheight = 2;
/* 218 */       gridBagConstraints11.gridy = 0;
/* 219 */       gridBagConstraints11.gridx = 1;
/* 220 */       gridBagConstraints11.insets = new Insets(0, 10, 0, 10);
/* 221 */       GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
/* 222 */       gridBagConstraints10.gridx = 2;
/* 223 */       gridBagConstraints10.anchor = 17;
/* 224 */       gridBagConstraints10.gridy = 1;
/* 225 */       GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
/* 226 */       gridBagConstraints9.gridx = 2;
/* 227 */       gridBagConstraints9.anchor = 17;
/* 228 */       gridBagConstraints9.gridy = 0;
/* 229 */       GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
/* 230 */       gridBagConstraints7.insets = new Insets(5, 5, 5, 10);
/* 231 */       gridBagConstraints7.gridy = 0;
/* 232 */       gridBagConstraints7.gridheight = 2;
/* 233 */       gridBagConstraints7.anchor = 10;
/* 234 */       gridBagConstraints7.gridx = 0;
/* 235 */       this.jLabel2 = new JLabel();
/* 236 */       this.jLabel2.setText(Mensajes.getString("en"));
/* 237 */       this.jPanel = new JPanel();
/* 238 */       this.jPanel.setLayout(new GridBagLayout());
/* 239 */       this.jPanel.setBorder(BorderFactory.createEtchedBorder(1));
/* 240 */       this.jPanel.add(this.jLabel2, gridBagConstraints7);
/* 241 */       this.jPanel.add(getEnDiario(), gridBagConstraints11);
/* 242 */       this.jPanel.add(getEnFactEmitidas(), gridBagConstraints9);
/* 243 */       this.jPanel.add(getEnFactRecibidas(), gridBagConstraints10);
/*     */     }
/* 245 */     return this.jPanel;
/*     */   }
/*     */ 
/*     */   private JCheckBox getEnDiario()
/*     */   {
/* 254 */     if (this.enDiario == null) {
/* 255 */       this.enDiario = new JCheckBox();
/* 256 */       this.enDiario.setText(Mensajes.getString("diario"));
/* 257 */       this.enDiario.setSelected(true);
/*     */     }
/* 259 */     return this.enDiario;
/*     */   }
/*     */ 
/*     */   private JCheckBox getEnFactEmitidas()
/*     */   {
/* 268 */     if (this.enFactEmitidas == null) {
/* 269 */       this.enFactEmitidas = new JCheckBox();
/* 270 */       this.enFactEmitidas.setSelected(true);
/* 271 */       this.enFactEmitidas.setText(Mensajes.getString("factEmitidas"));
/* 272 */       this.enFactEmitidas.setToolTipText(Mensajes.getString("yenvencimientosC"));
/*     */     }
/* 274 */     return this.enFactEmitidas;
/*     */   }
/*     */ 
/*     */   private JCheckBox getEnFactRecibidas()
/*     */   {
/* 283 */     if (this.enFactRecibidas == null) {
/* 284 */       this.enFactRecibidas = new JCheckBox();
/* 285 */       this.enFactRecibidas.setSelected(true);
/* 286 */       this.enFactRecibidas.setText(Mensajes.getString("factRecibidas"));
/* 287 */       this.enFactRecibidas.setToolTipText(Mensajes.getString("yenvencimientos"));
/*     */     }
/* 289 */     return this.enFactRecibidas;
/*     */   }
/*     */ 
/*     */   private JButton getBSustituir()
/*     */   {
/* 298 */     if (this.bSustituir == null) {
/* 299 */       this.bSustituir = new JButton();
/* 300 */       this.bSustituir.setHorizontalTextPosition(2);
/* 301 */       this.bSustituir.setText(Mensajes.getString("sustituir"));
/* 302 */       this.bSustituir.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/ok.png")));
/* 303 */       this.bSustituir.addActionListener(new ActionListener()
/*     */       {
/*     */         public void actionPerformed(ActionEvent e) {
/* 306 */           Sustitucion.this.sustitucionMasiva(Sustitucion.this.listaCuentas1.getSelectedItem().toString(), Sustitucion.this.listaCuentas2.getSelectedItem().toString());
/*     */ 
/* 308 */           Sustitucion.this.dispose();
/*     */         }
/*     */       });
/*     */     }
/* 312 */     return this.bSustituir;
/*     */   }
/*     */ 
/*     */   private JButton getBCancelar()
/*     */   {
/* 321 */     if (this.bCancelar == null) {
/* 322 */       this.bCancelar = new JButton();
/* 323 */       this.bCancelar.setText(Mensajes.getString("cancelar"));
/* 324 */       this.bCancelar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/cancel.png")));
/* 325 */       this.bCancelar.setHorizontalTextPosition(2);
/* 326 */       this.bCancelar.addActionListener(new ActionListener()
/*     */       {
/*     */         public void actionPerformed(ActionEvent e) {
/* 329 */           Sustitucion.this.dispose();
/*     */         }
/*     */       });
/*     */     }
/* 333 */     return this.bCancelar;
/*     */   }
/*     */ 
/*     */   private void rellenarCombo(JComboBox jCombo) {
/* 337 */     ManejoSubcuentas cuentaM = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 338 */     LinkedList lista = cuentaM.listadoSubcuentas(10000000, 80000000);
/* 339 */     if (lista.size() > 0) {
/* 340 */       DefaultComboBoxModel modelo = new DefaultComboBoxModel();
/* 341 */       for (Iterator i$ = lista.iterator(); i$.hasNext(); ) { int x = ((Integer)i$.next()).intValue();
/* 342 */         modelo.addElement(String.valueOf(x));
/*     */       }
/* 344 */       jCombo.setModel(modelo);
/*     */     }
/*     */   }
/*     */ 
/*     */   private String nombreCuenta(String codigo) {
/* 349 */     ManejoSubcuentas cuentaM = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 350 */     String nombre = null;
/*     */     try {
/* 352 */       nombre = cuentaM.datos(Integer.parseInt(codigo)).getNombre();
/*     */     } catch (NumberFormatException e1) {
/* 354 */       e1.printStackTrace();
/*     */     }
/* 356 */     if (nombre == null) {
/* 357 */       nombre = "";
/*     */     }
/* 359 */     return nombre;
/*     */   }
/*     */ 
/*     */   private void sustitucionMasiva(String cuenta1, String cuenta2) {
/* 363 */     int volver = JOptionPane.showConfirmDialog(getParent(), Mensajes.getString("dlgConfSustitCta") + " " + cuenta1 + "  " + Mensajes.getString("por") + "  " + cuenta2 + "?", Mensajes.getString("confirma"), 0);
/*     */ 
/* 367 */     if (volver == 0) {
/* 368 */       SustitucionDeCuentas clase = new SustitucionDeCuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*     */       try {
/* 370 */         clase.ejecutar(Integer.parseInt(cuenta1), Integer.parseInt(cuenta2), this.enDiario.isSelected(), this.enFactRecibidas.isSelected(), this.enFactEmitidas.isSelected());
/*     */ 
/* 373 */         RegeneraSaldos.regenera(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 374 */         Inicio.frame.renovarTabla(0);
/* 375 */         System.gc();
/*     */       } catch (NumberFormatException e1) {
/* 377 */         e1.printStackTrace();
/*     */       }
/*     */     }
/* 380 */     JOptionPane.showMessageDialog(getContentPane(), Mensajes.getString("dlgAvisoSustitCta"));
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.cuentas.Sustitucion
 * JD-Core Version:    0.6.2
 */