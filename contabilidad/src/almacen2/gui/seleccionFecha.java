/*     */ package almacen2.gui;
/*     */ 
/*     */ import contaes.calendario.ICalendarTextField;
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Frame;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ public class seleccionFecha extends JDialog
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  24 */   private JPanel jContentPane = null;
/*  25 */   private JLabel jLabel = null;
/*  26 */   private ICalendarTextField fInicial = null;
/*  27 */   private JButton jButton = null;
/*  28 */   private JButton jButton1 = null;
/*     */ 
/*  30 */   private String[] fechas = null;
/*     */ 
/*     */   public seleccionFecha(Frame owner)
/*     */   {
/*  36 */     super(owner);
/*  37 */     initialize();
/*     */   }
/*     */ 
/*     */   public static String[] obtenerObjeto(Frame owner) {
/*  41 */     seleccionFecha dlg = new seleccionFecha(owner);
/*  42 */     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/*  43 */     Dimension frameSize = dlg.getSize();
/*  44 */     if (frameSize.height > screenSize.height) {
/*  45 */       frameSize.height = screenSize.height;
/*     */     }
/*  47 */     if (frameSize.width > screenSize.width) {
/*  48 */       frameSize.width = screenSize.width;
/*     */     }
/*  50 */     dlg.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
/*     */ 
/*  52 */     dlg.pack();
/*  53 */     dlg.setVisible(true);
/*  54 */     return dlg.obtenerValorRetorno();
/*     */   }
/*     */ 
/*     */   private void initialize()
/*     */   {
/*  63 */     setTitle(Mensajes.getString("seleccion") + " " + Mensajes.getString("fecha"));
/*  64 */     setModal(true);
/*  65 */     setBounds(new Rectangle(0, 22, 280, 175));
/*  66 */     setContentPane(getJContentPane());
/*     */   }
/*     */ 
/*     */   private JPanel getJContentPane()
/*     */   {
/*  75 */     if (this.jContentPane == null) {
/*  76 */       GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
/*  77 */       gridBagConstraints5.gridx = 1;
/*  78 */       gridBagConstraints5.anchor = 17;
/*  79 */       gridBagConstraints5.insets = new Insets(0, 5, 5, 5);
/*  80 */       gridBagConstraints5.gridy = 2;
/*  81 */       GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
/*  82 */       gridBagConstraints4.gridx = 0;
/*  83 */       gridBagConstraints4.insets = new Insets(0, 5, 5, 5);
/*  84 */       gridBagConstraints4.gridy = 2;
/*  85 */       GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
/*  86 */       gridBagConstraints3.fill = 2;
/*  87 */       gridBagConstraints3.gridy = 1;
/*  88 */       gridBagConstraints3.weightx = 1.0D;
/*  89 */       gridBagConstraints3.insets = new Insets(5, 5, 10, 5);
/*  90 */       gridBagConstraints3.gridx = 1;
/*  91 */       GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
/*  92 */       gridBagConstraints2.fill = 2;
/*  93 */       gridBagConstraints2.gridy = 0;
/*  94 */       gridBagConstraints2.weightx = 1.0D;
/*  95 */       gridBagConstraints2.insets = new Insets(5, 5, 5, 5);
/*  96 */       gridBagConstraints2.gridx = 1;
/*  97 */       GridBagConstraints gridBagConstraints = new GridBagConstraints();
/*  98 */       gridBagConstraints.gridx = 0;
/*  99 */       gridBagConstraints.insets = new Insets(5, 5, 5, 5);
/* 100 */       gridBagConstraints.anchor = 17;
/* 101 */       gridBagConstraints.gridy = 0;
/* 102 */       this.jLabel = new JLabel();
/* 103 */       this.jLabel.setText(Mensajes.getString("fecha"));
/* 104 */       this.jContentPane = new JPanel();
/* 105 */       this.jContentPane.setLayout(new GridBagLayout());
/* 106 */       this.jContentPane.add(this.jLabel, gridBagConstraints);
/* 107 */       this.jContentPane.add(getFInicial(), gridBagConstraints2);
/* 108 */       this.jContentPane.add(getJButton(), gridBagConstraints4);
/* 109 */       this.jContentPane.add(getJButton1(), gridBagConstraints5);
/*     */     }
/* 111 */     return this.jContentPane;
/*     */   }
/*     */ 
/*     */   private ICalendarTextField getFInicial()
/*     */   {
/* 120 */     if (this.fInicial == null) {
/* 121 */       this.fInicial = new ICalendarTextField();
/*     */     }
/* 123 */     return this.fInicial;
/*     */   }
/*     */ 
/*     */   private JButton getJButton()
/*     */   {
/* 132 */     if (this.jButton == null) {
/* 133 */       this.jButton = new JButton();
/* 134 */       this.jButton.setText(Mensajes.getString("bien"));
/* 135 */       this.jButton.addActionListener(new ActionListener() {
/*     */         public void actionPerformed(ActionEvent e) {
/* 137 */           seleccionFecha.this.fechas = new String[2];
/* 138 */           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/* 139 */           Date fecha = seleccionFecha.this.fInicial.getDate();
/* 140 */           if (fecha != null)
/* 141 */             seleccionFecha.this.fechas[0] = sdf.format(fecha);
/*     */           else
/* 143 */             seleccionFecha.this.fechas = null;
/* 144 */           seleccionFecha.this.dispose();
/*     */         }
/*     */       });
/*     */     }
/* 148 */     return this.jButton;
/*     */   }
/*     */ 
/*     */   private JButton getJButton1()
/*     */   {
/* 157 */     if (this.jButton1 == null) {
/* 158 */       this.jButton1 = new JButton();
/* 159 */       this.jButton1.setText(Mensajes.getString("cancelar"));
/* 160 */       this.jButton1.addActionListener(new ActionListener() {
/*     */         public void actionPerformed(ActionEvent e) {
/* 162 */           seleccionFecha.this.dispose();
/*     */         }
/*     */       });
/*     */     }
/* 166 */     return this.jButton1;
/*     */   }
/*     */ 
/*     */   private String[] obtenerValorRetorno() {
/* 170 */     return this.fechas;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.gui.seleccionFecha
 * JD-Core Version:    0.6.2
 */