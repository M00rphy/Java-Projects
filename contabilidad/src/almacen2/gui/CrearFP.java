/*     */ package almacen2.gui;
/*     */ 
/*     */ import almacen2.data.FPObject;
/*     */ import contaes.auxiliar.DocNumPositivos;
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Frame;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ 
/*     */ public class CrearFP extends JDialog
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  26 */   private JPanel jContentPane = null;
/*  27 */   private JLabel jLabel = null;
/*  28 */   private JLabel jLabel1 = null;
/*  29 */   private JButton jButton1 = null;
/*  30 */   private JButton jButton2 = null;
/*  31 */   private JTextField id = null;
/*  32 */   private JTextField nombre = null;
/*     */ 
/*  34 */   private FPObject elemento = null;
/*     */   private int nuevoRegistro;
/*     */ 
/*     */   public CrearFP(Frame owner, String titulo, int nuevoRegistro)
/*     */   {
/*  41 */     super(owner);
/*  42 */     this.nuevoRegistro = nuevoRegistro;
/*  43 */     initialize(titulo);
/*     */   }
/*     */ 
/*     */   public static FPObject obtenerElemento(Frame owner, String titulo, int nuevoRegistro) {
/*  47 */     CrearFP dlg = new CrearFP(owner, titulo, nuevoRegistro);
/*  48 */     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/*  49 */     Dimension frameSize = dlg.getSize();
/*  50 */     if (frameSize.height > screenSize.height) {
/*  51 */       frameSize.height = screenSize.height;
/*     */     }
/*  53 */     if (frameSize.width > screenSize.width) {
/*  54 */       frameSize.width = screenSize.width;
/*     */     }
/*  56 */     dlg.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
/*     */ 
/*  58 */     dlg.pack();
/*  59 */     dlg.setVisible(true);
/*  60 */     return dlg.obtenerValorRetorno();
/*     */   }
/*     */ 
/*     */   private void initialize(String titulo)
/*     */   {
/*  69 */     setSize(300, 200);
/*  70 */     setTitle(Mensajes.getString("nuevo") + ": " + titulo);
/*  71 */     setModal(true);
/*  72 */     setContentPane(getJContentPane());
/*     */   }
/*     */ 
/*     */   private JPanel getJContentPane()
/*     */   {
/*  81 */     if (this.jContentPane == null) {
/*  82 */       GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
/*  83 */       gridBagConstraints6.fill = 2;
/*  84 */       gridBagConstraints6.gridy = 1;
/*  85 */       gridBagConstraints6.weightx = 1.0D;
/*  86 */       gridBagConstraints6.insets = new Insets(5, 6, 5, 5);
/*  87 */       gridBagConstraints6.gridx = 1;
/*  88 */       GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
/*  89 */       gridBagConstraints5.fill = 2;
/*  90 */       gridBagConstraints5.gridy = 0;
/*  91 */       gridBagConstraints5.weightx = 1.0D;
/*  92 */       gridBagConstraints5.insets = new Insets(5, 6, 5, 5);
/*  93 */       gridBagConstraints5.gridx = 1;
/*  94 */       GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
/*  95 */       gridBagConstraints4.gridx = 1;
/*  96 */       gridBagConstraints4.anchor = 17;
/*  97 */       gridBagConstraints4.insets = new Insets(0, 10, 0, 0);
/*  98 */       gridBagConstraints4.gridy = 2;
/*  99 */       GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
/* 100 */       gridBagConstraints3.gridx = 0;
/* 101 */       gridBagConstraints3.insets = new Insets(0, 0, 0, 5);
/* 102 */       gridBagConstraints3.gridy = 2;
/* 103 */       GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
/* 104 */       gridBagConstraints1.gridx = 0;
/* 105 */       gridBagConstraints1.insets = new Insets(5, 5, 5, 0);
/* 106 */       gridBagConstraints1.gridy = 1;
/* 107 */       this.jLabel1 = new JLabel();
/* 108 */       this.jLabel1.setText(Mensajes.getString("nombre"));
/* 109 */       GridBagConstraints gridBagConstraints = new GridBagConstraints();
/* 110 */       gridBagConstraints.gridx = 0;
/* 111 */       gridBagConstraints.insets = new Insets(5, 5, 5, 0);
/* 112 */       gridBagConstraints.gridy = 0;
/* 113 */       this.jLabel = new JLabel();
/* 114 */       this.jLabel.setText(Mensajes.getString("numero"));
/* 115 */       this.jContentPane = new JPanel();
/* 116 */       this.jContentPane.setLayout(new GridBagLayout());
/* 117 */       this.jContentPane.add(this.jLabel, gridBagConstraints);
/* 118 */       this.jContentPane.add(this.jLabel1, gridBagConstraints1);
/* 119 */       this.jContentPane.add(getJButton1(), gridBagConstraints3);
/* 120 */       this.jContentPane.add(getJButton2(), gridBagConstraints4);
/* 121 */       this.jContentPane.add(getId(), gridBagConstraints5);
/* 122 */       this.jContentPane.add(getNombre(), gridBagConstraints6);
/*     */     }
/* 124 */     return this.jContentPane;
/*     */   }
/*     */ 
/*     */   private JButton getJButton1()
/*     */   {
/* 133 */     if (this.jButton1 == null) {
/* 134 */       this.jButton1 = new JButton();
/* 135 */       this.jButton1.setText(Mensajes.getString("bien"));
/* 136 */       this.jButton1.addActionListener(new ActionListener() {
/*     */         public void actionPerformed(ActionEvent e) {
/* 138 */           CrearFP.this.botonBien();
/*     */         }
/*     */       });
/*     */     }
/* 142 */     return this.jButton1;
/*     */   }
/*     */ 
/*     */   private JButton getJButton2()
/*     */   {
/* 151 */     if (this.jButton2 == null) {
/* 152 */       this.jButton2 = new JButton();
/* 153 */       this.jButton2.setText(Mensajes.getString("cancelar"));
/* 154 */       this.jButton2.addActionListener(new ActionListener() {
/*     */         public void actionPerformed(ActionEvent e) {
/* 156 */           CrearFP.this.dispose();
/*     */         }
/*     */       });
/*     */     }
/* 160 */     return this.jButton2;
/*     */   }
/*     */ 
/*     */   private JTextField getId()
/*     */   {
/* 169 */     if (this.id == null) {
/* 170 */       this.id = new JTextField();
/* 171 */       this.id.setDocument(new DocNumPositivos());
/* 172 */       this.id.setText(Integer.toString(this.nuevoRegistro));
/*     */     }
/* 174 */     return this.id;
/*     */   }
/*     */ 
/*     */   private JTextField getNombre()
/*     */   {
/* 183 */     if (this.nombre == null) {
/* 184 */       this.nombre = new JTextField();
/*     */     }
/* 186 */     return this.nombre;
/*     */   }
/*     */ 
/*     */   private void botonBien() {
/* 190 */     String i = this.id.getText();
/* 191 */     String n = this.nombre.getText();
/* 192 */     if ((i != null) && (n != null))
/* 193 */       this.elemento = new FPObject(Integer.parseInt(i), n, -1, -1);
/* 194 */     dispose();
/*     */   }
/*     */ 
/*     */   private FPObject obtenerValorRetorno() {
/* 198 */     return this.elemento;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.gui.CrearFP
 * JD-Core Version:    0.6.2
 */