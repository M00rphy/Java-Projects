/*     */ package contaes.informes.view;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.MarcoInicio;
/*     */ import contaes.calendario.ICalendarTextField;
/*     */ import contaes.informes.control.RatiosControl;
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.Container;
/*     */ import java.awt.Frame;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import org.jdesktop.layout.GroupLayout;
/*     */ import org.jdesktop.layout.GroupLayout.ParallelGroup;
/*     */ import org.jdesktop.layout.GroupLayout.SequentialGroup;
/*     */ 
/*     */ public class RatiosJDialog extends JDialog
/*     */ {
/*     */   private ICalendarTextField campoFechaFin;
/*     */   private ICalendarTextField campoFechaIni;
/*     */   private JButton jButton1;
/*     */   private JButton jButton2;
/*     */   private JLabel jLabel1;
/*     */   private JLabel jLabel2;
/*     */   private JPanel jPanel1;
/*     */ 
/*     */   public RatiosJDialog(Frame parent, boolean modal)
/*     */   {
/*  28 */     super(parent, modal);
/*  29 */     initComponents();
/*  30 */     this.campoFechaIni.setComponente(this.campoFechaFin);
/*  31 */     this.campoFechaFin.setComponente(this.jButton1);
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/*  43 */     this.jPanel1 = new JPanel();
/*  44 */     this.jLabel1 = new JLabel();
/*  45 */     this.campoFechaIni = new ICalendarTextField();
/*  46 */     this.jButton1 = new JButton();
/*  47 */     this.jButton2 = new JButton();
/*  48 */     this.jLabel2 = new JLabel();
/*  49 */     this.campoFechaFin = new ICalendarTextField();
/*     */ 
/*  51 */     setDefaultCloseOperation(2);
/*  52 */     ResourceBundle bundle = ResourceBundle.getBundle("internationalization/Mensajes");
/*  53 */     setTitle(bundle.getString("ratio00"));
/*     */ 
/*  55 */     this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/*     */ 
/*  57 */     this.jLabel1.setText(bundle.getString("fechaIni"));
/*     */ 
/*  59 */     this.jButton1.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/Mlistado.png")));
/*  60 */     this.jButton1.setText(bundle.getString("crear"));
/*  61 */     this.jButton1.setHorizontalTextPosition(2);
/*  62 */     this.jButton1.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/*  64 */         RatiosJDialog.this.jButton1ActionPerformed(evt);
/*     */       }
/*     */     });
/*  68 */     this.jButton2.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/cancel.png")));
/*  69 */     this.jButton2.setText(bundle.getString("cancelar"));
/*  70 */     this.jButton2.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/*  72 */         RatiosJDialog.this.jButton2ActionPerformed(evt);
/*     */       }
/*     */     });
/*  76 */     this.jLabel2.setText(bundle.getString("fechaFin"));
/*     */ 
/*  78 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/*  79 */     this.jPanel1.setLayout(jPanel1Layout);
/*  80 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().add(this.jButton1).add(49, 49, 49).add(this.jButton2).add(199, 199, 199)).add(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().add(this.campoFechaIni, -2, 130, -2).addPreferredGap(1).add(this.campoFechaFin, -2, 130, -2).addContainerGap(-1, 32767)).add(jPanel1Layout.createSequentialGroup().add(this.jLabel1).addPreferredGap(0, 68, 32767).add(this.jLabel2).add(197, 197, 197))))));
/*     */ 
/* 102 */     jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(jPanel1Layout.createParallelGroup(3).add(this.jLabel1).add(this.jLabel2)).addPreferredGap(0).add(jPanel1Layout.createParallelGroup(2).add(this.campoFechaIni, -2, -1, -2).add(this.campoFechaFin, -2, -1, -2)).add(18, 18, 18).add(jPanel1Layout.createParallelGroup(3).add(this.jButton1).add(this.jButton2)).addContainerGap(-1, 32767)));
/*     */ 
/* 120 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 121 */     getContentPane().setLayout(layout);
/* 122 */     layout.setHorizontalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(this.jPanel1, -2, 315, -2).addContainerGap(-1, 32767)));
/*     */ 
/* 129 */     layout.setVerticalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(this.jPanel1, -1, -1, 32767).addContainerGap()));
/*     */ 
/* 137 */     pack();
/*     */   }
/*     */ 
/*     */   private void jButton1ActionPerformed(ActionEvent evt) {
/* 141 */     Date fechaIni = this.campoFechaIni.getDate();
/* 142 */     Date fechaFin = this.campoFechaFin.getDate();
/* 143 */     if ((fechaIni != null) && (fechaFin != null)) {
/* 144 */       SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
/* 145 */       String titulo2 = sdf.format(fechaIni) + " " + Mensajes.getString("a") + " " + sdf.format(fechaFin);
/* 146 */       String titulo1 = Mensajes.getString("ratio00");
/* 147 */       titulo1 = titulo1 + " " + Mensajes.getString("para") + " : " + Inicio.frame.getTitle().substring(10);
/* 148 */       RatiosControl rC = new RatiosControl();
/* 149 */       RatiosJFrame marco = new RatiosJFrame(titulo1, titulo2, rC.listado(fechaIni, fechaFin));
/* 150 */       Inicio.frame.mostrarMarcoExterno(marco);
/* 151 */       rC.cerrarRs();
/* 152 */       dispose();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void jButton2ActionPerformed(ActionEvent evt)
/*     */   {
/* 158 */     dispose();
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.informes.view.RatiosJDialog
 * JD-Core Version:    0.6.2
 */