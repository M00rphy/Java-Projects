/*     */ package pos.view;
/*     */ 
/*     */ import contaes.calendario.ICalendarTextField;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Frame;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.Date;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.LayoutStyle;
/*     */ 
/*     */ public class AskForFechasJDialog extends JDialog
/*     */ {
/*     */   Date[] fechas;
/*     */   private ICalendarTextField iCalendarTextField1;
/*     */   private ICalendarTextField iCalendarTextField2;
/*     */   private JButton jButton1;
/*     */   private JButton jButton2;
/*     */   private JLabel jLabel1;
/*     */   private JLabel jLabel2;
/*     */   private JPanel jPanel1;
/*     */ 
/*     */   public AskForFechasJDialog(Frame parent, boolean modal)
/*     */   {
/*  28 */     super(parent, modal);
/*  29 */     initComponents();
/*  30 */     this.iCalendarTextField1.setComponente(this.iCalendarTextField2);
/*  31 */     this.iCalendarTextField2.setComponente(this.jButton1);
/*  32 */     this.fechas = new Date[2];
/*     */   }
/*     */ 
/*     */   public static Date[] obtenerObjeto(Frame parent, boolean modal) {
/*  36 */     AskForFechasJDialog dlg = new AskForFechasJDialog(parent, modal);
/*  37 */     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/*  38 */     Dimension frameSize = dlg.getSize();
/*  39 */     if (frameSize.height > screenSize.height) {
/*  40 */       frameSize.height = screenSize.height;
/*     */     }
/*  42 */     if (frameSize.width > screenSize.width) {
/*  43 */       frameSize.width = screenSize.width;
/*     */     }
/*  45 */     dlg.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
/*     */ 
/*  48 */     dlg.setVisible(true);
/*  49 */     return dlg.getFechas();
/*     */   }
/*     */ 
/*     */   public Date[] getFechas() {
/*  53 */     return this.fechas;
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/*  65 */     this.jPanel1 = new JPanel();
/*  66 */     this.jLabel1 = new JLabel();
/*  67 */     this.iCalendarTextField1 = new ICalendarTextField();
/*  68 */     this.jLabel2 = new JLabel();
/*  69 */     this.iCalendarTextField2 = new ICalendarTextField();
/*  70 */     this.jButton1 = new JButton();
/*  71 */     this.jButton2 = new JButton();
/*     */ 
/*  73 */     setDefaultCloseOperation(2);
/*  74 */     ResourceBundle bundle = ResourceBundle.getBundle("internationalization/Mensajes");
/*  75 */     setTitle(bundle.getString("fecha"));
/*     */ 
/*  77 */     this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/*     */ 
/*  79 */     this.jLabel1.setText(bundle.getString("fechaIni"));
/*     */ 
/*  81 */     this.jLabel2.setText(bundle.getString("fechaFin"));
/*     */ 
/*  83 */     this.jButton1.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/ok.png")));
/*  84 */     this.jButton1.setText(bundle.getString("bien"));
/*  85 */     this.jButton1.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/*  87 */         AskForFechasJDialog.this.jButton1ActionPerformed(evt);
/*     */       }
/*     */     });
/*  91 */     this.jButton2.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/cancel.png")));
/*  92 */     this.jButton2.setText(bundle.getString("cancelar"));
/*  93 */     this.jButton2.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/*  95 */         AskForFechasJDialog.this.jButton2ActionPerformed(evt);
/*     */       }
/*     */     });
/*  99 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 100 */     this.jPanel1.setLayout(jPanel1Layout);
/* 101 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel1).addComponent(this.jLabel2).addComponent(this.jButton1)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jButton2).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.iCalendarTextField2, -1, -1, 32767).addComponent(this.iCalendarTextField1, -1, 138, 32767))).addContainerGap(-1, 32767)));
/*     */ 
/* 117 */     jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.iCalendarTextField1, -2, -1, -2).addComponent(this.jLabel1)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.jLabel2).addComponent(this.iCalendarTextField2, -2, -1, -2)).addGap(18, 18, 18).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jButton1).addComponent(this.jButton2)).addContainerGap(-1, 32767)));
/*     */ 
/* 135 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 136 */     getContentPane().setLayout(layout);
/* 137 */     layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -2, -1, -2).addContainerGap(-1, 32767)));
/*     */ 
/* 144 */     layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -2, -1, -2).addContainerGap(-1, 32767)));
/*     */ 
/* 152 */     pack();
/*     */   }
/*     */ 
/*     */   private void jButton2ActionPerformed(ActionEvent evt) {
/* 156 */     dispose();
/*     */   }
/*     */ 
/*     */   private void jButton1ActionPerformed(ActionEvent evt) {
/* 160 */     Date fecha1 = this.iCalendarTextField1.getDate();
/* 161 */     Date fecha2 = this.iCalendarTextField2.getDate();
/* 162 */     if ((fecha1 != null) && (fecha2 != null)) {
/* 163 */       this.fechas[0] = new Date();
/* 164 */       this.fechas[1] = new Date();
/* 165 */       this.fechas[0].setTime(fecha1.getTime());
/* 166 */       this.fechas[1].setTime(fecha2.getTime());
/* 167 */       dispose();
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.view.AskForFechasJDialog
 * JD-Core Version:    0.6.2
 */