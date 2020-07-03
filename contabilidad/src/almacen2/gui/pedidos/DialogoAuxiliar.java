/*     */ package almacen2.gui.pedidos;
/*     */ 
/*     */ import contaes.calendario.ICalendarTextField;
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Frame;
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
/*     */ public class DialogoAuxiliar extends JDialog
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  23 */   private JPanel jContentPane = null;
/*  24 */   private JLabel jLabel = null;
/*  25 */   private ICalendarTextField campoFecha = null;
/*  26 */   private JButton jButton = null;
/*  27 */   private JButton jButton1 = null;
/*     */ 
/*  29 */   private String fecha = null;
/*     */   private boolean compras;
/*     */ 
/*     */   public DialogoAuxiliar(Frame owner, boolean compras)
/*     */   {
/*  36 */     super(owner);
/*  37 */     this.compras = compras;
/*  38 */     initialize();
/*     */   }
/*     */ 
/*     */   public static String obtenerFecha(Frame owner, boolean compras) {
/*  42 */     DialogoAuxiliar dlg = new DialogoAuxiliar(owner, compras);
/*  43 */     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/*  44 */     Dimension frameSize = dlg.getSize();
/*  45 */     if (frameSize.height > screenSize.height) {
/*  46 */       frameSize.height = screenSize.height;
/*     */     }
/*  48 */     if (frameSize.width > screenSize.width) {
/*  49 */       frameSize.width = screenSize.width;
/*     */     }
/*  51 */     dlg.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
/*     */ 
/*  54 */     dlg.setVisible(true);
/*  55 */     return dlg.obtenerValorRetorno();
/*     */   }
/*     */ 
/*     */   private void initialize()
/*     */   {
/*  64 */     setModal(true);
/*  65 */     setSize(415, 290);
/*  66 */     setContentPane(getJContentPane());
/*     */   }
/*     */ 
/*     */   private JPanel getJContentPane()
/*     */   {
/*  75 */     if (this.jContentPane == null) {
/*  76 */       this.jLabel = new JLabel();
/*  77 */       this.jLabel.setBounds(new Rectangle(15, 5, 405, 150));
/*  78 */       if (this.compras) {
/*  79 */         this.jLabel.setText(Mensajes.getString("recibidosDLG"));
/*     */       }
/*     */       else {
/*  82 */         this.jLabel.setText(Mensajes.getString("enviadosDLG"));
/*     */       }
/*  84 */       this.jContentPane = new JPanel();
/*  85 */       this.jContentPane.setLayout(null);
/*  86 */       this.jContentPane.add(this.jLabel, null);
/*  87 */       this.jContentPane.add(getCampoFecha(), null);
/*  88 */       this.jContentPane.add(getJButton(), null);
/*  89 */       this.jContentPane.add(getJButton1(), null);
/*     */     }
/*  91 */     return this.jContentPane;
/*     */   }
/*     */ 
/*     */   private ICalendarTextField getCampoFecha()
/*     */   {
/* 100 */     if (this.campoFecha == null) {
/* 101 */       this.campoFecha = new ICalendarTextField();
/* 102 */       this.campoFecha.setBounds(new Rectangle(25, 165, 172, 28));
/*     */     }
/* 104 */     return this.campoFecha;
/*     */   }
/*     */ 
/*     */   private JButton getJButton()
/*     */   {
/* 113 */     if (this.jButton == null) {
/* 114 */       this.jButton = new JButton();
/* 115 */       this.jButton.setBounds(new Rectangle(15, 215, 95, 29));
/* 116 */       this.jButton.setText(Mensajes.getString("bien"));
/* 117 */       this.jButton.addActionListener(new ActionListener() {
/*     */         public void actionPerformed(ActionEvent e) {
/* 119 */           DialogoAuxiliar.this.asignarFecha();
/*     */         }
/*     */       });
/*     */     }
/* 123 */     return this.jButton;
/*     */   }
/*     */ 
/*     */   private void asignarFecha() {
/* 127 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/* 128 */     Date fecha_d = this.campoFecha.getDate();
/* 129 */     if (fecha_d == null) fecha_d = new Date();
/* 130 */     this.fecha = sdf.format(fecha_d);
/* 131 */     dispose();
/*     */   }
/*     */ 
/*     */   private JButton getJButton1()
/*     */   {
/* 141 */     if (this.jButton1 == null) {
/* 142 */       this.jButton1 = new JButton();
/* 143 */       this.jButton1.setBounds(new Rectangle(120, 215, 110, 29));
/* 144 */       this.jButton1.setText(Mensajes.getString("cancelar"));
/* 145 */       this.jButton1.addActionListener(new ActionListener() {
/*     */         public void actionPerformed(ActionEvent e) {
/* 147 */           DialogoAuxiliar.this.dispose();
/*     */         }
/*     */       });
/*     */     }
/* 151 */     return this.jButton1;
/*     */   }
/*     */ 
/*     */   private String obtenerValorRetorno() {
/* 155 */     return this.fecha;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.gui.pedidos.DialogoAuxiliar
 * JD-Core Version:    0.6.2
 */