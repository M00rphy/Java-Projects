/*     */ package contaes.dialogosFunciones;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.MarcoInicio;
/*     */ import contaes.Puente;
/*     */ import contaes.manejoDatos.funciones.ReordenaDiario;
/*     */ import java.awt.Container;
/*     */ import java.awt.Frame;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.GroupLayout.Alignment;
/*     */ import javax.swing.GroupLayout.ParallelGroup;
/*     */ import javax.swing.GroupLayout.SequentialGroup;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JProgressBar;
import javax.swing.LayoutStyle;
/*     */ import javax.swing.LayoutStyle.ComponentPlacement;
/*     */ import javax.swing.SwingWorker;
/*     */ 
/*     */ public class ReordenarDiario extends JDialog
/*     */ {
/*     */   Task tarea;
/*     */   private JButton jButton1;
/*     */   private JPanel jPanel1;
/*     */   private JProgressBar jProgressBar1;
/*     */ 
/*     */   public ReordenarDiario(Frame parent, boolean modal)
/*     */   {
/*  28 */     super(parent, modal);
/*  29 */     initComponents();
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/*  41 */     this.jPanel1 = new JPanel();
/*  42 */     this.jProgressBar1 = new JProgressBar();
/*  43 */     this.jButton1 = new JButton();
/*     */ 
/*  45 */     setDefaultCloseOperation(2);
/*  46 */     ResourceBundle bundle = ResourceBundle.getBundle("internationalization/Mensajes");
/*  47 */     setTitle(bundle.getString("renumerarD"));
/*     */ 
/*  49 */     this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/*     */ 
/*  51 */     this.jButton1.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/MrenumD.png")));
/*  52 */     this.jButton1.setText(bundle.getString("renumerar"));
/*  53 */     this.jButton1.setHorizontalTextPosition(2);
/*  54 */     this.jButton1.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/*  56 */         ReordenarDiario.this.jButton1ActionPerformed(evt);
/*     */       }
/*     */     });
/*  60 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/*  61 */     this.jPanel1.setLayout(jPanel1Layout);
/*  62 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(this.jProgressBar1, -2, 185, -2).addContainerGap(-1, 32767)).addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup().addContainerGap(53, 32767).addComponent(this.jButton1).addGap(49, 49, 49)));
/*     */ 
/*  73 */     jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(this.jProgressBar1, -2, 42, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton1).addContainerGap(18, 32767)));
/*     */ 
/*  83 */     GroupLayout layout = new GroupLayout(getContentPane());
/*  84 */     getContentPane().setLayout(layout);
/*  85 */     layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -1, -1, 32767).addContainerGap()));
/*     */ 
/*  92 */     layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -1, -1, 32767).addContainerGap()));
/*     */ 
/* 100 */     pack();
/*     */   }
/*     */ 
/*     */   private void jButton1ActionPerformed(ActionEvent evt) {
/* 104 */     this.jProgressBar1.setEnabled(true);
/* 105 */     this.jProgressBar1.setIndeterminate(true);
/* 106 */     this.jButton1.setEnabled(false);
/* 107 */     this.tarea = new Task();
/* 108 */     this.tarea.execute();
/* 109 */     dispose();
/*     */   }
/*     */ 
/*     */   class Task extends SwingWorker<Void, Void>
/*     */   {
/*     */     Task()
/*     */     {
/*     */     }
/*     */ 
/*     */     public Void doInBackground()
/*     */     {
/* 126 */       setProgress(0);
/* 127 */       ReordenaDiario.reordenar(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 128 */       System.gc();
/* 129 */       Inicio.frame.renovarTabla(0);
/* 130 */       return null;
/*     */     }
/*     */ 
/*     */     public void done()
/*     */     {
/* 138 */       Toolkit.getDefaultToolkit().beep();
/* 139 */       ReordenarDiario.this.jButton1.setEnabled(true);
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.dialogosFunciones.ReordenarDiario
 * JD-Core Version:    0.6.2
 */