/*     */ package contaes.dialogosAuxiliares;
/*     */ 
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.Frame;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Insets;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.text.Document;
/*     */ import javax.swing.text.PlainDocument;
/*     */ 
/*     */ public class BusquedaStd extends BusquedaBase
/*     */ {
/*  44 */   private JPanel jContentPane = null;
/*  45 */   private JLabel jLabel = null;
/*  46 */   private JTextField comienzo = null;
/*  47 */   private JTextField finaliza = null;
/*     */   private boolean puedeSeleccionar;
/*     */ 
/*     */   public BusquedaStd()
/*     */   {
/*  55 */     this(new Frame(), Mensajes.getString("selBu"), true, true);
/*     */   }
/*     */ 
/*     */   public BusquedaStd(Frame owner, String title, boolean modal, boolean puedeSeleccionar) {
/*  59 */     super(owner, title, modal);
/*  60 */     this.puedeSeleccionar = puedeSeleccionar;
/*  61 */     initialize();
/*     */   }
/*     */ 
/*     */   private void initialize()
/*     */   {
/*  70 */     setSize(300, 200);
/*  71 */     setContentPane(getJContentPane());
/*     */   }
/*     */ 
/*     */   private JPanel getJContentPane()
/*     */   {
/*  80 */     if (this.jContentPane == null) {
/*  81 */       GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
/*  82 */       gridBagConstraints6.fill = 2;
/*  83 */       gridBagConstraints6.gridy = 3;
/*  84 */       gridBagConstraints6.weightx = 1.0D;
/*  85 */       gridBagConstraints6.insets = new Insets(10, 5, 0, 5);
/*  86 */       gridBagConstraints6.gridx = 0;
/*  87 */       GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
/*  88 */       gridBagConstraints5.gridx = 1;
/*  89 */       gridBagConstraints5.insets = new Insets(0, 0, 0, 0);
/*  90 */       gridBagConstraints5.anchor = 10;
/*  91 */       gridBagConstraints5.gridy = 1;
/*  92 */       if (!this.puedeSeleccionar) {
/*  93 */         this.jLabel4.setText("");
/*  94 */         this.jLabel4.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/help.png")));
/*  95 */         this.jLabel4.setToolTipText(Mensajes.getString("masInfo"));
/*  96 */         this.jLabel4.addMouseListener(new MouseAdapter() {
/*     */           public void mouseClicked(MouseEvent e) {
/*  98 */             BusquedaStd.this.mostrarSugerencia();
/*     */           }
/*     */         });
/*     */       }
/* 102 */       GridBagConstraints gridBagConstraints41 = new GridBagConstraints();
/* 103 */       gridBagConstraints41.gridx = 0;
/* 104 */       gridBagConstraints41.insets = new Insets(5, 0, 5, 0);
/* 105 */       gridBagConstraints41.gridwidth = 2;
/* 106 */       gridBagConstraints41.gridy = 5;
/* 107 */       GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
/* 108 */       gridBagConstraints31.gridx = 1;
/* 109 */       gridBagConstraints31.insets = new Insets(7, 0, 0, 0);
/* 110 */       gridBagConstraints31.gridy = 4;
/* 111 */       GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
/* 112 */       gridBagConstraints21.gridx = 0;
/* 113 */       gridBagConstraints21.insets = new Insets(7, 0, 0, 0);
/* 114 */       gridBagConstraints21.gridy = 4;
/* 115 */       GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
/* 116 */       gridBagConstraints11.fill = 2;
/* 117 */       gridBagConstraints11.gridy = 3;
/* 118 */       gridBagConstraints11.weightx = 1.0D;
/* 119 */       gridBagConstraints11.insets = new Insets(10, 5, 0, 5);
/* 120 */       gridBagConstraints11.gridx = 1;
/* 121 */       GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
/* 122 */       gridBagConstraints4.fill = 2;
/* 123 */       gridBagConstraints4.gridy = 3;
/* 124 */       gridBagConstraints4.weightx = 1.0D;
/* 125 */       gridBagConstraints4.insets = new Insets(10, 5, 0, 5);
/* 126 */       gridBagConstraints4.gridx = 0;
/* 127 */       GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
/* 128 */       gridBagConstraints3.gridx = 1;
/* 129 */       gridBagConstraints3.insets = new Insets(3, 0, 0, 0);
/* 130 */       gridBagConstraints3.anchor = 10;
/* 131 */       gridBagConstraints3.gridy = 2;
/* 132 */       if (!this.puedeSeleccionar)
/* 133 */         this.jLabel3.setIcon(null);
/* 134 */       GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
/* 135 */       gridBagConstraints2.gridx = 0;
/* 136 */       gridBagConstraints2.insets = new Insets(3, 0, 0, 0);
/* 137 */       gridBagConstraints2.anchor = 10;
/* 138 */       gridBagConstraints2.gridy = 2;
/* 139 */       GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
/* 140 */       gridBagConstraints1.gridx = 0;
/* 141 */       gridBagConstraints1.insets = new Insets(3, 0, 0, 0);
/* 142 */       gridBagConstraints1.anchor = 10;
/* 143 */       gridBagConstraints1.gridy = 1;
/* 144 */       GridBagConstraints gridBagConstraints = new GridBagConstraints();
/* 145 */       gridBagConstraints.gridx = 0;
/* 146 */       gridBagConstraints.insets = new Insets(3, 0, 0, 0);
/* 147 */       gridBagConstraints.anchor = 10;
/* 148 */       gridBagConstraints.gridy = 0;
/* 149 */       this.jLabel = new JLabel();
/* 150 */       this.jLabel.setText(Mensajes.getString("patronBusq"));
/* 151 */       this.jContentPane = new JPanel();
/* 152 */       this.jContentPane.setLayout(new GridBagLayout());
/* 153 */       this.jContentPane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createEtchedBorder(1)));
/* 154 */       this.jContentPane.add(this.jLabel, gridBagConstraints);
/* 155 */       this.jContentPane.add(this.jLabel1, gridBagConstraints1);
/* 156 */       this.jContentPane.add(this.jLabel2, gridBagConstraints2);
/* 157 */       this.jContentPane.add(this.jLabel3, gridBagConstraints3);
/* 158 */       this.jContentPane.add(getComienzo(), gridBagConstraints4);
/* 159 */       this.jContentPane.add(getFinaliza(), gridBagConstraints11);
/* 160 */       this.jContentPane.add(getBuscar(), gridBagConstraints21);
/* 161 */       this.jContentPane.add(getSelecc(), gridBagConstraints31);
/* 162 */       this.jContentPane.add(getCancel(), gridBagConstraints41);
/* 163 */       this.jContentPane.add(this.jLabel4, gridBagConstraints5);
/* 164 */       this.jContentPane.add(getComienzo(), gridBagConstraints6);
/*     */     }
/* 166 */     return this.jContentPane;
/*     */   }
/*     */ 
/*     */   private JTextField getComienzo()
/*     */   {
/* 175 */     if (this.comienzo == null) {
/* 176 */       this.comienzo = new JTextField();
/*     */     }
/* 178 */     this.comienzo.setDocument(documento());
/* 179 */     this.comienzo.addKeyListener(this.escuchaTecla);
/* 180 */     return this.comienzo;
/*     */   }
/*     */ 
/*     */   private JTextField getFinaliza()
/*     */   {
/* 189 */     if (this.finaliza == null) {
/* 190 */       this.finaliza = new JTextField();
/*     */     }
/* 192 */     this.finaliza.setDocument(documento());
/* 193 */     this.finaliza.addKeyListener(this.escuchaTecla);
/* 194 */     if (!this.puedeSeleccionar) this.finaliza.setEnabled(false);
/* 195 */     return this.finaliza;
/*     */   }
/*     */ 
/*     */   private void mostrarSugerencia() {
/* 199 */     String sugerencia = Mensajes.getString("infoSelec");
/* 200 */     JOptionPane.showMessageDialog(getContentPane(), sugerencia);
/*     */   }
/*     */ 
/*     */   protected Document documento()
/*     */   {
/* 209 */     return new PlainDocument();
/*     */   }
/*     */ 
/*     */   protected void cambiaFoco(KeyEvent arg0) {
/* 213 */     int tecla = arg0.getKeyCode();
/* 214 */     Object campo = arg0.getSource();
/* 215 */     if (tecla == 10)
/* 216 */       if (campo == this.comienzo) {
/* 217 */         if (this.puedeSeleccionar)
/* 218 */           this.finaliza.requestFocus();
/* 219 */         else this.buscar.requestFocus();
/*     */       }
/* 221 */       else if (campo == this.finaliza)
/* 222 */         this.buscar.requestFocus();
/*     */   }
/*     */ 
/*     */   protected void buscar()
/*     */   {
/* 228 */     setInicio(this.comienzo.getText());
/* 229 */     if (!getInicio().equals("")) {
/* 230 */       setAccion(true);
/* 231 */       setBusqueda(true);
/*     */     }
/* 233 */     dispose();
/*     */   }
/*     */ 
/*     */   protected void seleccionar() {
/* 237 */     setInicio(this.comienzo.getText());
/* 238 */     setFin(this.finaliza.getText());
/* 239 */     if (!getInicio().equals("")) {
/* 240 */       setAccion(true);
/* 241 */       setBusqueda(false);
/*     */     }
/* 243 */     dispose();
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.dialogosAuxiliares.BusquedaStd
 * JD-Core Version:    0.6.2
 */