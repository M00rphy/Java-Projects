/*     */ package contaes.dialogosAuxiliares;
/*     */ 
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.Font;
/*     */ import java.awt.Frame;
/*     */ import java.awt.GraphicsEnvironment;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.HeadlessException;
/*     */ import java.awt.Insets;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextArea;
/*     */ 
/*     */ public class MostrarFuentes extends JDialog
/*     */   implements ActionListener
/*     */ {
/*  27 */   private JPanel jContentPane = null;
/*  28 */   private JTextArea AreaDePrueba = null;
/*  29 */   private JLabel jLabel = null;
/*  30 */   private JLabel jLabel1 = null;
/*  31 */   private JComboBox fuentes = null;
/*  32 */   private JComboBox tamanos = null;
/*  33 */   private JButton jButton = null;
/*  34 */   private JButton jButton1 = null;
/*     */   private String fuente;
/*     */   private int tamano;
/*     */   private boolean cambiar;
/*     */ 
/*     */   public MostrarFuentes(Frame Owner, String title, boolean modal, String fuente, int tamano)
/*     */     throws HeadlessException
/*     */   {
/*  48 */     super(Owner, title, modal);
/*  49 */     this.fuente = fuente;
/*  50 */     this.tamano = tamano;
/*  51 */     initialize();
/*     */   }
/*     */ 
/*     */   public MostrarFuentes(String fuente, int tamano)
/*     */   {
/*  61 */     this(new Frame(), Mensajes.getString("fuentes"), true, fuente, tamano);
/*     */   }
/*     */ 
/*     */   public MostrarFuentes()
/*     */   {
/*  68 */     this(new Frame(), Mensajes.getString("fuentes"), true, "Dialog", 12);
/*     */   }
/*     */ 
/*     */   private void initialize()
/*     */   {
/*  77 */     setSize(330, 210);
/*  78 */     setContentPane(getJContentPane());
/*  79 */     setCambiar(false);
/*  80 */     this.fuentes.setSelectedItem(this.fuente);
/*  81 */     this.tamanos.setSelectedItem(Integer.toString(this.tamano));
/*  82 */     this.AreaDePrueba.setFont(new Font(this.fuente, 0, this.tamano));
/*     */   }
/*     */ 
/*     */   private JPanel getJContentPane()
/*     */   {
/*  91 */     if (this.jContentPane == null) {
/*  92 */       GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
/*  93 */       gridBagConstraints6.gridx = 1;
/*  94 */       gridBagConstraints6.anchor = 17;
/*  95 */       gridBagConstraints6.insets = new Insets(0, -50, 10, 10);
/*  96 */       gridBagConstraints6.gridy = 3;
/*  97 */       GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
/*  98 */       gridBagConstraints5.gridx = 0;
/*  99 */       gridBagConstraints5.anchor = 17;
/* 100 */       gridBagConstraints5.insets = new Insets(0, 20, 10, 0);
/* 101 */       gridBagConstraints5.gridy = 3;
/* 102 */       GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
/* 103 */       gridBagConstraints4.fill = 2;
/* 104 */       gridBagConstraints4.gridy = 1;
/* 105 */       gridBagConstraints4.weightx = 1.0D;
/* 106 */       gridBagConstraints4.insets = new Insets(0, 10, 10, 10);
/* 107 */       gridBagConstraints4.gridx = 1;
/* 108 */       GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
/* 109 */       gridBagConstraints3.fill = 2;
/* 110 */       gridBagConstraints3.gridy = 1;
/* 111 */       gridBagConstraints3.weightx = 1.0D;
/* 112 */       gridBagConstraints3.insets = new Insets(0, 10, 10, 10);
/* 113 */       gridBagConstraints3.gridx = 0;
/* 114 */       GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
/* 115 */       gridBagConstraints2.gridx = 1;
/* 116 */       gridBagConstraints2.anchor = 17;
/* 117 */       gridBagConstraints2.insets = new Insets(0, 10, 5, 0);
/* 118 */       gridBagConstraints2.gridy = 0;
/* 119 */       this.jLabel1 = new JLabel();
/* 120 */       this.jLabel1.setText(Mensajes.getString("tamano"));
/* 121 */       GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
/* 122 */       gridBagConstraints1.gridx = 0;
/* 123 */       gridBagConstraints1.anchor = 17;
/* 124 */       gridBagConstraints1.insets = new Insets(0, 10, 5, 0);
/* 125 */       gridBagConstraints1.gridy = 0;
/* 126 */       this.jLabel = new JLabel();
/* 127 */       this.jLabel.setText(Mensajes.getString("fuente"));
/* 128 */       GridBagConstraints gridBagConstraints = new GridBagConstraints();
/* 129 */       gridBagConstraints.fill = 1;
/* 130 */       gridBagConstraints.gridy = 2;
/* 131 */       gridBagConstraints.weightx = 1.0D;
/* 132 */       gridBagConstraints.weighty = 1.0D;
/* 133 */       gridBagConstraints.gridwidth = 2;
/* 134 */       gridBagConstraints.insets = new Insets(10, 10, 15, 10);
/* 135 */       gridBagConstraints.gridx = 0;
/* 136 */       this.jContentPane = new JPanel();
/* 137 */       this.jContentPane.setLayout(new GridBagLayout());
/* 138 */       this.jContentPane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(1), BorderFactory.createEmptyBorder(5, 5, 5, 5))));
/*     */ 
/* 141 */       this.jContentPane.add(getAreaDePrueba(), gridBagConstraints);
/* 142 */       this.jContentPane.add(this.jLabel, gridBagConstraints1);
/* 143 */       this.jContentPane.add(this.jLabel1, gridBagConstraints2);
/* 144 */       this.jContentPane.add(getFuentes(), gridBagConstraints3);
/* 145 */       this.jContentPane.add(getTamanos(), gridBagConstraints4);
/* 146 */       this.jContentPane.add(getJButton(), gridBagConstraints5);
/* 147 */       this.jContentPane.add(getJButton1(), gridBagConstraints6);
/*     */     }
/* 149 */     return this.jContentPane;
/*     */   }
/*     */ 
/*     */   private JTextArea getAreaDePrueba()
/*     */   {
/* 158 */     if (this.AreaDePrueba == null) {
/* 159 */       this.AreaDePrueba = new JTextArea();
/* 160 */       this.AreaDePrueba.setBorder(BorderFactory.createEtchedBorder(1));
/*     */     }
/* 162 */     this.AreaDePrueba.setText(Mensajes.getString("textPrueba"));
/* 163 */     return this.AreaDePrueba;
/*     */   }
/*     */ 
/*     */   private JComboBox getFuentes()
/*     */   {
/* 172 */     if (this.fuentes == null) {
/* 173 */       GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
/*     */ 
/* 175 */       String[] fontNames = ge.getAvailableFontFamilyNames();
/* 176 */       this.fuentes = new JComboBox(fontNames);
/* 177 */       this.fuentes.setMaximumSize(this.fuentes.getPreferredSize());
/* 178 */       this.fuentes.setEditable(false);
/* 179 */       this.fuentes.setActionCommand("FUENTES");
/* 180 */       this.fuentes.addActionListener(this);
/*     */     }
/* 182 */     return this.fuentes;
/*     */   }
/*     */ 
/*     */   private JComboBox getTamanos()
/*     */   {
/* 191 */     if (this.tamanos == null) {
/* 192 */       this.tamanos = new JComboBox(new String[] { "8", "9", "10", "11", "12", "14", "16", "18", "20", "22", "24", "26", "28", "36", "48", "72" });
/*     */ 
/* 195 */       this.tamanos.setMaximumSize(this.tamanos.getPreferredSize());
/* 196 */       this.tamanos.setEditable(false);
/* 197 */       this.tamanos.setActionCommand("TAMANOS");
/* 198 */       this.tamanos.addActionListener(this);
/*     */     }
/* 200 */     return this.tamanos;
/*     */   }
/*     */ 
/*     */   private JButton getJButton()
/*     */   {
/* 209 */     if (this.jButton == null) {
/* 210 */       this.jButton = new JButton();
/* 211 */       this.jButton.setText(Mensajes.getString("seleccionar"));
/* 212 */       this.jButton.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/ok.png")));
/* 213 */       this.jButton.setHorizontalTextPosition(2);
/* 214 */       this.jButton.setActionCommand("SELECCIONAR");
/* 215 */       this.jButton.addActionListener(this);
/*     */     }
/* 217 */     return this.jButton;
/*     */   }
/*     */ 
/*     */   private JButton getJButton1()
/*     */   {
/* 226 */     if (this.jButton1 == null) {
/* 227 */       this.jButton1 = new JButton();
/* 228 */       this.jButton1.setText(Mensajes.getString("cancelar"));
/* 229 */       this.jButton1.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/cancel.png")));
/* 230 */       this.jButton1.setHorizontalTextPosition(2);
/* 231 */       this.jButton1.setActionCommand("CANCELAR");
/* 232 */       this.jButton1.addActionListener(this);
/*     */     }
/* 234 */     return this.jButton1;
/*     */   }
/*     */ 
/*     */   public void actionPerformed(ActionEvent arg0) {
/* 238 */     String cmd = arg0.getActionCommand();
/* 239 */     if ("CANCELAR".equals(cmd)) {
/* 240 */       dispose();
/*     */     }
/* 242 */     else if ("SELECCIONAR".equals(cmd)) {
/* 243 */       setCambiar(true);
/* 244 */       dispose();
/*     */     }
/* 246 */     else if ("FUENTES".equals(cmd)) {
/* 247 */       setFuente(this.fuentes.getSelectedItem().toString());
/*     */     }
/* 249 */     else if ("TAMANOS".equals(cmd)) {
/* 250 */       setTamano(Integer.parseInt(this.tamanos.getSelectedItem().toString()));
/*     */     }
/*     */ 
/* 253 */     this.AreaDePrueba.setFont(new Font(this.fuente, 0, this.tamano));
/*     */   }
/*     */ 
/*     */   public String getFuente()
/*     */   {
/* 260 */     return this.fuente;
/*     */   }
/*     */ 
/*     */   public void setFuente(String fuente)
/*     */   {
/* 267 */     this.fuente = fuente;
/*     */   }
/*     */ 
/*     */   public int getTamano()
/*     */   {
/* 274 */     return this.tamano;
/*     */   }
/*     */ 
/*     */   public void setTamano(int tamano)
/*     */   {
/* 281 */     this.tamano = tamano;
/*     */   }
/*     */ 
/*     */   public boolean isCambiar()
/*     */   {
/* 288 */     return this.cambiar;
/*     */   }
/*     */ 
/*     */   public void setCambiar(boolean cambiar)
/*     */   {
/* 295 */     this.cambiar = cambiar;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.dialogosAuxiliares.MostrarFuentes
 * JD-Core Version:    0.6.2
 */