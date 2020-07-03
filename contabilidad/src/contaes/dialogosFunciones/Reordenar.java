/*     */ package contaes.dialogosFunciones;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.MarcoInicio;
/*     */ import contaes.Puente;
/*     */ import contaes.calendario.ICalendarTextField;
/*     */ import contaes.manejoDatos.funciones.ReordenaEmitidasFacturacion;
/*     */ import contaes.manejoDatos.funciones.ReordenaTickets;
/*     */ import java.awt.Container;
/*     */ import java.awt.Frame;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import org.jdesktop.layout.GroupLayout;
/*     */ import org.jdesktop.layout.GroupLayout.ParallelGroup;
/*     */ import org.jdesktop.layout.GroupLayout.SequentialGroup;
/*     */ 
/*     */ public class Reordenar extends JDialog
/*     */ {
/*     */   int opcion;
/*     */   private JButton botonCancelar;
/*     */   private ICalendarTextField campoFechaFin;
/*     */   private ICalendarTextField campoFechaIni;
/*     */   private JButton campoRenumerar;
/*     */   private JLabel jLabel1;
/*     */   private JLabel jLabel2;
/*     */   private JPanel jPanel1;
/*     */ 
/*     */   public Reordenar(Frame parent, boolean modal, int opcion)
/*     */   {
/*  29 */     super(parent, modal);
/*  30 */     this.opcion = opcion;
/*  31 */     initComponents();
/*  32 */     if (opcion == 0) {
/*  33 */       setTitle("Renumerar facturas de ventas");
/*     */     }
/*  35 */     else if (opcion == 1)
/*  36 */       setTitle("Renumerar tickets");
/*     */   }
/*     */ 
/*     */   private boolean reordena()
/*     */   {
/*  41 */     Date fechaInicial = this.campoFechaIni.getDate();
/*  42 */     Date fechaFinal = this.campoFechaFin.getDate();
/*  43 */     GregorianCalendar gc = new GregorianCalendar();
/*  44 */     gc.setTime(fechaInicial);
/*  45 */     String ejercicio = Integer.toString(gc.get(1));
/*  46 */     if (!ejercicio.equals(Inicio.p.getEjercicio())) {
/*  47 */       JOptionPane.showMessageDialog(Inicio.frame, "El ejercicio de la fecha inicial\nno coincide con el ejercicio actual", "Error", 0);
/*     */ 
/*  49 */       return false;
/*     */     }
/*  51 */     gc.setTime(fechaFinal);
/*  52 */     ejercicio = Integer.toString(gc.get(1));
/*  53 */     if (!ejercicio.equals(Inicio.p.getEjercicio())) {
/*  54 */       JOptionPane.showMessageDialog(Inicio.frame, "El ejercicio de la fecha final\nno coincide con el ejercicio actual", "Error", 0);
/*     */ 
/*  56 */       return false;
/*     */     }
/*  58 */     switch (this.opcion) {
/*     */     case 0:
/*  60 */       ReordenaEmitidasFacturacion.reordenar(Inicio.getCFacturacion(), fechaInicial, fechaFinal);
/*  61 */       Inicio.frame.renovarTabla(7);
/*  62 */       break;
/*     */     case 1:
/*  64 */       ReordenaTickets.reordenar(Inicio.getcAlmacen(), fechaInicial, fechaFinal);
/*     */     }
/*     */ 
/*  67 */     System.gc();
/*  68 */     return true;
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/*  80 */     this.jPanel1 = new JPanel();
/*  81 */     this.jLabel1 = new JLabel();
/*  82 */     this.campoFechaIni = new ICalendarTextField();
/*  83 */     this.jLabel2 = new JLabel();
/*  84 */     this.campoFechaFin = new ICalendarTextField();
/*  85 */     this.botonCancelar = new JButton();
/*  86 */     this.campoRenumerar = new JButton();
/*     */ 
/*  88 */     setDefaultCloseOperation(2);
/*     */ 
/*  90 */     this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/*     */ 
/*  92 */     ResourceBundle bundle = ResourceBundle.getBundle("internationalization/Mensajes");
/*  93 */     this.jLabel1.setText(bundle.getString("fechaIni"));
/*     */ 
/*  95 */     this.jLabel2.setText(bundle.getString("fechaFin"));
/*     */ 
/*  97 */     this.botonCancelar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/cancel.png")));
/*  98 */     this.botonCancelar.setText(bundle.getString("cancelar"));
/*  99 */     this.botonCancelar.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 101 */         Reordenar.this.botonCancelarActionPerformed(evt);
/*     */       }
/*     */     });
/* 105 */     this.campoRenumerar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/MrenumD.png")));
/* 106 */     this.campoRenumerar.setText(bundle.getString("renumerar"));
/* 107 */     this.campoRenumerar.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 109 */         Reordenar.this.campoRenumerarActionPerformed(evt);
/*     */       }
/*     */     });
/* 113 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 114 */     this.jPanel1.setLayout(jPanel1Layout);
/* 115 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(jPanel1Layout.createParallelGroup(1).add(this.jLabel1).add(this.jLabel2)).add(18, 18, 18).add(jPanel1Layout.createParallelGroup(1, false).add(this.campoFechaFin, -1, -1, 32767).add(this.campoFechaIni, -1, 169, 32767)).addContainerGap(-1, 32767)).add(2, jPanel1Layout.createSequentialGroup().addContainerGap(88, 32767).add(this.campoRenumerar).add(27, 27, 27).add(this.botonCancelar).addContainerGap()));
/*     */ 
/* 134 */     jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().add(20, 20, 20).add(jPanel1Layout.createParallelGroup(2).add(this.campoFechaIni, -2, -1, -2).add(this.jLabel1)).addPreferredGap(0).add(jPanel1Layout.createParallelGroup(2).add(this.jLabel2).add(this.campoFechaFin, -2, -1, -2)).add(18, 18, 18).add(jPanel1Layout.createParallelGroup(3).add(this.botonCancelar).add(this.campoRenumerar)).addContainerGap(-1, 32767)));
/*     */ 
/* 152 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 153 */     getContentPane().setLayout(layout);
/* 154 */     layout.setHorizontalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(this.jPanel1, -1, -1, 32767).addContainerGap()));
/*     */ 
/* 161 */     layout.setVerticalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(this.jPanel1, -1, -1, 32767).addContainerGap()));
/*     */ 
/* 169 */     pack();
/*     */   }
/*     */ 
/*     */   private void botonCancelarActionPerformed(ActionEvent evt) {
/* 173 */     dispose();
/*     */   }
/*     */ 
/*     */   private void campoRenumerarActionPerformed(ActionEvent evt) {
/* 177 */     if (reordena()) {
/* 178 */       JOptionPane.showMessageDialog(Inicio.frame, "Proceso terminado", "Información", 1);
/*     */ 
/* 180 */       dispose();
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.dialogosFunciones.Reordenar
 * JD-Core Version:    0.6.2
 */