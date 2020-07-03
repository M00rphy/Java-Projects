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
/*     */ public class seleccionFechas extends JDialog
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  24 */   private JPanel jContentPane = null;
/*  25 */   private JLabel jLabel = null;
/*  26 */   private JLabel jLabel1 = null;
/*  27 */   private ICalendarTextField fInicial = null;
/*  28 */   private ICalendarTextField fFinal = null;
/*  29 */   private JButton jButton = null;
/*  30 */   private JButton jButton1 = null;
/*     */ 
/*  32 */   private String[] fechas = null;
/*     */ 
/*     */   public seleccionFechas(Frame owner)
/*     */   {
/*  38 */     super(owner);
/*  39 */     initialize();
/*     */   }
/*     */ 
/*     */   public static String[] obtenerObjeto(Frame owner) {
/*  43 */     seleccionFechas dlg = new seleccionFechas(owner);
/*  44 */     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/*  45 */     Dimension frameSize = dlg.getSize();
/*  46 */     if (frameSize.height > screenSize.height) {
/*  47 */       frameSize.height = screenSize.height;
/*     */     }
/*  49 */     if (frameSize.width > screenSize.width) {
/*  50 */       frameSize.width = screenSize.width;
/*     */     }
/*  52 */     dlg.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
/*     */ 
/*  54 */     dlg.pack();
/*  55 */     dlg.setVisible(true);
/*  56 */     return dlg.obtenerValorRetorno();
/*     */   }
/*     */ 
/*     */   private void initialize()
/*     */   {
/*  65 */     setTitle(Mensajes.getString("seleccion") + " " + Mensajes.getString("fecha"));
/*  66 */     setModal(true);
/*  67 */     setBounds(new Rectangle(0, 22, 280, 175));
/*  68 */     setContentPane(getJContentPane());
/*     */   }
/*     */ 
/*     */   private JPanel getJContentPane()
/*     */   {
/*  77 */     if (this.jContentPane == null) {
/*  78 */       GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
/*  79 */       gridBagConstraints5.gridx = 1;
/*  80 */       gridBagConstraints5.anchor = 17;
/*  81 */       gridBagConstraints5.insets = new Insets(0, 5, 5, 5);
/*  82 */       gridBagConstraints5.gridy = 2;
/*  83 */       GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
/*  84 */       gridBagConstraints4.gridx = 0;
/*  85 */       gridBagConstraints4.insets = new Insets(0, 5, 5, 5);
/*  86 */       gridBagConstraints4.gridy = 2;
/*  87 */       GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
/*  88 */       gridBagConstraints3.fill = 2;
/*  89 */       gridBagConstraints3.gridy = 1;
/*  90 */       gridBagConstraints3.weightx = 1.0D;
/*  91 */       gridBagConstraints3.insets = new Insets(5, 5, 10, 5);
/*  92 */       gridBagConstraints3.gridx = 1;
/*  93 */       GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
/*  94 */       gridBagConstraints2.fill = 2;
/*  95 */       gridBagConstraints2.gridy = 0;
/*  96 */       gridBagConstraints2.weightx = 1.0D;
/*  97 */       gridBagConstraints2.insets = new Insets(5, 5, 5, 5);
/*  98 */       gridBagConstraints2.gridx = 1;
/*  99 */       GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
/* 100 */       gridBagConstraints1.gridx = 0;
/* 101 */       gridBagConstraints1.insets = new Insets(5, 5, 10, 5);
/* 102 */       gridBagConstraints1.anchor = 17;
/* 103 */       gridBagConstraints1.gridy = 1;
/* 104 */       this.jLabel1 = new JLabel();
/* 105 */       this.jLabel1.setText(Mensajes.getString("fecha") + " " + Mensajes.getString("final"));
/* 106 */       GridBagConstraints gridBagConstraints = new GridBagConstraints();
/* 107 */       gridBagConstraints.gridx = 0;
/* 108 */       gridBagConstraints.insets = new Insets(5, 5, 5, 5);
/* 109 */       gridBagConstraints.anchor = 17;
/* 110 */       gridBagConstraints.gridy = 0;
/* 111 */       this.jLabel = new JLabel();
/* 112 */       this.jLabel.setText(Mensajes.getString("fecha") + " " + Mensajes.getString("inicial"));
/* 113 */       this.jContentPane = new JPanel();
/* 114 */       this.jContentPane.setLayout(new GridBagLayout());
/* 115 */       this.jContentPane.add(this.jLabel, gridBagConstraints);
/* 116 */       this.jContentPane.add(this.jLabel1, gridBagConstraints1);
/* 117 */       this.jContentPane.add(getFInicial(), gridBagConstraints2);
/* 118 */       this.jContentPane.add(getFFinal(), gridBagConstraints3);
/* 119 */       this.jContentPane.add(getJButton(), gridBagConstraints4);
/* 120 */       this.jContentPane.add(getJButton1(), gridBagConstraints5);
/*     */     }
/* 122 */     return this.jContentPane;
/*     */   }
/*     */ 
/*     */   private ICalendarTextField getFInicial()
/*     */   {
/* 131 */     if (this.fInicial == null) {
/* 132 */       this.fInicial = new ICalendarTextField();
/*     */     }
/* 134 */     return this.fInicial;
/*     */   }
/*     */ 
/*     */   private ICalendarTextField getFFinal()
/*     */   {
/* 143 */     if (this.fFinal == null) {
/* 144 */       this.fFinal = new ICalendarTextField();
/*     */     }
/* 146 */     return this.fFinal;
/*     */   }
/*     */ 
/*     */   private JButton getJButton()
/*     */   {
/* 155 */     if (this.jButton == null) {
/* 156 */       this.jButton = new JButton();
/* 157 */       this.jButton.setText(Mensajes.getString("bien"));
/* 158 */       this.jButton.addActionListener(new ActionListener() {
/*     */         public void actionPerformed(ActionEvent e) {
/* 160 */           seleccionFechas.this.fechas = new String[2];
/* 161 */           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/* 162 */           Date fecha = seleccionFechas.this.fInicial.getDate();
/* 163 */           if (fecha != null) {
/* 164 */             seleccionFechas.this.fechas[0] = sdf.format(fecha);
/* 165 */             fecha = seleccionFechas.this.fFinal.getDate();
/* 166 */             if (fecha != null)
/* 167 */               seleccionFechas.this.fechas[1] = sdf.format(fecha);
/*     */             else
/* 169 */               seleccionFechas.this.fechas = null;
/*     */           }
/* 171 */           seleccionFechas.this.dispose();
/*     */         }
/*     */       });
/*     */     }
/* 175 */     return this.jButton;
/*     */   }
/*     */ 
/*     */   private JButton getJButton1()
/*     */   {
/* 184 */     if (this.jButton1 == null) {
/* 185 */       this.jButton1 = new JButton();
/* 186 */       this.jButton1.setText(Mensajes.getString("cancelar"));
/* 187 */       this.jButton1.addActionListener(new ActionListener() {
/*     */         public void actionPerformed(ActionEvent e) {
/* 189 */           seleccionFechas.this.dispose();
/*     */         }
/*     */       });
/*     */     }
/* 193 */     return this.jButton1;
/*     */   }
/*     */ 
/*     */   private String[] obtenerValorRetorno() {
/* 197 */     return this.fechas;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.gui.seleccionFechas
 * JD-Core Version:    0.6.2
 */