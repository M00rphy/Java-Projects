/*     */ package contaes.dialogosAuxiliares;
/*     */ 
/*     */ import contaes.calendario.ICalendarTextField;
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.Frame;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Insets;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.text.SimpleDateFormat;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ public class BusquedaFecha extends BusquedaBase
/*     */ {
/*  42 */   private JPanel jContentPane = null;
/*  43 */   private JLabel jLabel = null;
/*  44 */   private ICalendarTextField comienzo = null;
/*  45 */   private ICalendarTextField finaliza = null;
/*     */ 
/*     */   public BusquedaFecha()
/*     */   {
/*  51 */     this(new Frame(), Mensajes.getString("selBu"), true);
/*     */   }
/*     */ 
/*     */   public BusquedaFecha(Frame owner, String title, boolean modal) {
/*  55 */     super(owner, title, modal);
/*  56 */     initialize();
/*     */   }
/*     */ 
/*     */   private void initialize()
/*     */   {
/*  65 */     setSize(300, 230);
/*  66 */     setContentPane(getJContentPane());
/*     */   }
/*     */ 
/*     */   private JPanel getJContentPane()
/*     */   {
/*  75 */     if (this.jContentPane == null) {
/*  76 */       GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
/*  77 */       gridBagConstraints6.fill = 2;
/*  78 */       gridBagConstraints6.gridy = 3;
/*  79 */       gridBagConstraints6.weightx = 1.0D;
/*  80 */       gridBagConstraints6.insets = new Insets(10, 5, 0, 5);
/*  81 */       gridBagConstraints6.gridx = 0;
/*  82 */       GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
/*  83 */       gridBagConstraints5.gridx = 1;
/*  84 */       gridBagConstraints5.insets = new Insets(0, 0, 0, 0);
/*  85 */       gridBagConstraints5.anchor = 10;
/*  86 */       gridBagConstraints5.gridy = 1;
/*  87 */       GridBagConstraints gridBagConstraints41 = new GridBagConstraints();
/*  88 */       gridBagConstraints41.gridx = 0;
/*  89 */       gridBagConstraints41.insets = new Insets(5, 0, 5, 0);
/*  90 */       gridBagConstraints41.gridwidth = 2;
/*  91 */       gridBagConstraints41.gridy = 5;
/*  92 */       GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
/*  93 */       gridBagConstraints31.gridx = 1;
/*  94 */       gridBagConstraints31.insets = new Insets(7, 0, 0, 0);
/*  95 */       gridBagConstraints31.gridy = 4;
/*  96 */       GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
/*  97 */       gridBagConstraints21.gridx = 0;
/*  98 */       gridBagConstraints21.insets = new Insets(7, 0, 0, 0);
/*  99 */       gridBagConstraints21.gridy = 4;
/* 100 */       GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
/* 101 */       gridBagConstraints11.fill = 2;
/* 102 */       gridBagConstraints11.gridy = 3;
/* 103 */       gridBagConstraints11.weightx = 1.0D;
/* 104 */       gridBagConstraints11.insets = new Insets(10, 8, 0, 8);
/* 105 */       gridBagConstraints11.gridx = 1;
/* 106 */       GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
/* 107 */       gridBagConstraints4.fill = 2;
/* 108 */       gridBagConstraints4.gridy = 3;
/* 109 */       gridBagConstraints4.weightx = 1.0D;
/* 110 */       gridBagConstraints4.insets = new Insets(10, 5, 0, 5);
/* 111 */       gridBagConstraints4.gridx = 0;
/* 112 */       GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
/* 113 */       gridBagConstraints3.gridx = 1;
/* 114 */       gridBagConstraints3.insets = new Insets(3, 0, 0, 0);
/* 115 */       gridBagConstraints3.anchor = 10;
/* 116 */       gridBagConstraints3.gridy = 2;
/* 117 */       GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
/* 118 */       gridBagConstraints2.gridx = 0;
/* 119 */       gridBagConstraints2.insets = new Insets(3, 0, 0, 0);
/* 120 */       gridBagConstraints2.anchor = 10;
/* 121 */       gridBagConstraints2.gridy = 2;
/* 122 */       GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
/* 123 */       gridBagConstraints1.gridx = 0;
/* 124 */       gridBagConstraints1.insets = new Insets(3, 0, 0, 0);
/* 125 */       gridBagConstraints1.anchor = 10;
/* 126 */       gridBagConstraints1.gridy = 1;
/* 127 */       GridBagConstraints gridBagConstraints = new GridBagConstraints();
/* 128 */       gridBagConstraints.gridx = 0;
/* 129 */       gridBagConstraints.insets = new Insets(3, 0, 0, 0);
/* 130 */       gridBagConstraints.anchor = 10;
/* 131 */       gridBagConstraints.gridy = 0;
/* 132 */       this.jLabel = new JLabel();
/* 133 */       this.jLabel.setText(Mensajes.getString("fechaBusq"));
/* 134 */       this.jContentPane = new JPanel();
/* 135 */       this.jContentPane.setLayout(new GridBagLayout());
/* 136 */       this.jContentPane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createEtchedBorder(1)));
/* 137 */       this.jContentPane.add(this.jLabel, gridBagConstraints);
/* 138 */       this.jContentPane.add(this.jLabel1, gridBagConstraints1);
/* 139 */       this.jContentPane.add(this.jLabel2, gridBagConstraints2);
/* 140 */       this.jContentPane.add(this.jLabel3, gridBagConstraints3);
/* 141 */       this.jContentPane.add(getBuscar(), gridBagConstraints21);
/* 142 */       this.jContentPane.add(getSelecc(), gridBagConstraints31);
/* 143 */       this.jContentPane.add(getCancel(), gridBagConstraints41);
/* 144 */       this.jContentPane.add(this.jLabel4, gridBagConstraints5);
/* 145 */       this.jContentPane.add(getFinaliza(), gridBagConstraints11);
/* 146 */       this.jContentPane.add(getComienzo(), gridBagConstraints6);
/*     */     }
/* 148 */     return this.jContentPane;
/*     */   }
/*     */ 
/*     */   private ICalendarTextField getComienzo()
/*     */   {
/* 157 */     if (this.comienzo == null) {
/* 158 */       this.comienzo = new ICalendarTextField();
/* 159 */       this.comienzo.setComponente(this.finaliza);
/*     */     }
/* 161 */     return this.comienzo;
/*     */   }
/*     */ 
/*     */   private ICalendarTextField getFinaliza()
/*     */   {
/* 170 */     if (this.finaliza == null) {
/* 171 */       this.finaliza = new ICalendarTextField();
/* 172 */       this.finaliza.setComponente(this.buscar);
/*     */     }
/* 174 */     return this.finaliza;
/*     */   }
/*     */ 
/*     */   protected void buscar() {
/* 178 */     if (this.comienzo.getDate() != null) {
/* 179 */       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/* 180 */       setInicio(sdf.format(this.comienzo.getDate()));
/* 181 */       setAccion(true);
/* 182 */       setBusqueda(true);
/*     */     }
/* 184 */     dispose();
/*     */   }
/*     */ 
/*     */   protected void seleccionar() {
/* 188 */     if ((this.comienzo.getDate() != null) && (this.finaliza.getDate() != null)) {
/* 189 */       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/* 190 */       setInicio(sdf.format(this.comienzo.getDate()));
/* 191 */       setFin(sdf.format(this.finaliza.getDate()));
/* 192 */       setAccion(true);
/* 193 */       setBusqueda(false);
/*     */     }
/* 195 */     dispose();
/*     */   }
/*     */ 
/*     */   protected void cambiaFoco(KeyEvent e)
/*     */   {
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.dialogosAuxiliares.BusquedaFecha
 * JD-Core Version:    0.6.2
 */