/*     */ package contaes.dialogosFunciones;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.auxiliar.DocNumPositivos;
/*     */ import contaes.manejoDatos.ManejoVencimientos;
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Frame;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.GregorianCalendar;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ 
/*     */ public class BorrarVencimientos extends JDialog
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  28 */   private JPanel jContentPane = null;
/*  29 */   private JLabel jLabel = null;
/*  30 */   private JTextField jTextField = null;
/*  31 */   private JComboBox jComboBox = null;
/*  32 */   private JButton ok = null;
/*  33 */   private JButton cancel = null;
/*     */   private boolean aPagar;
/*     */ 
/*     */   public BorrarVencimientos(Frame owner)
/*     */   {
/*  41 */     this(owner, Mensajes.getString("menu35"), true, true);
/*     */   }
/*     */ 
/*     */   public BorrarVencimientos(Frame owner, boolean aPagar) {
/*  45 */     this(owner, aPagar ? Mensajes.getString("menu35") : Mensajes.getString("menu35a"), true, aPagar);
/*     */   }
/*     */ 
/*     */   public BorrarVencimientos(Frame owner, String title, boolean modal, boolean aPagar) {
/*  49 */     super(owner, title, modal);
/*  50 */     setDefaultCloseOperation(2);
/*  51 */     this.aPagar = aPagar;
/*  52 */     initialize();
/*     */   }
/*     */ 
/*     */   private void initialize()
/*     */   {
/*  61 */     setPreferredSize(new Dimension(300, 155));
/*  62 */     setBounds(new Rectangle(0, 22, 300, 155));
/*  63 */     setContentPane(getJContentPane());
/*     */   }
/*     */ 
/*     */   private JPanel getJContentPane()
/*     */   {
/*  72 */     if (this.jContentPane == null) {
/*  73 */       this.jLabel = new JLabel();
/*  74 */       this.jLabel.setText(Mensajes.getString("elimVtoAnterior"));
/*  75 */       this.jLabel.setLocation(new Point(10, 15));
/*  76 */       this.jLabel.setSize(new Dimension(280, 16));
/*  77 */       this.jContentPane = new JPanel();
/*  78 */       this.jContentPane.setLayout(null);
/*  79 */       this.jContentPane.add(this.jLabel, null);
/*  80 */       this.jContentPane.add(getJTextField(), null);
/*  81 */       this.jContentPane.add(getJComboBox(), null);
/*  82 */       this.jContentPane.add(getOk(), null);
/*  83 */       this.jContentPane.add(getCancel(), null);
/*     */     }
/*  85 */     return this.jContentPane;
/*     */   }
/*     */ 
/*     */   private JTextField getJTextField()
/*     */   {
/*  94 */     if (this.jTextField == null) {
/*  95 */       this.jTextField = new JTextField();
/*  96 */       this.jTextField.setDocument(new DocNumPositivos());
/*  97 */       this.jTextField.setLocation(new Point(35, 50));
/*  98 */       this.jTextField.setSize(new Dimension(60, 20));
/*     */     }
/* 100 */     return this.jTextField;
/*     */   }
/*     */ 
/*     */   private JComboBox getJComboBox()
/*     */   {
/* 109 */     if (this.jComboBox == null) {
/* 110 */       String[] opciones = { Mensajes.getString("years"), Mensajes.getString("months"), Mensajes.getString("days") };
/* 111 */       this.jComboBox = new JComboBox(opciones);
/* 112 */       this.jComboBox.setSize(new Dimension(120, 27));
/* 113 */       this.jComboBox.setPreferredSize(new Dimension(120, 24));
/* 114 */       this.jComboBox.setLocation(new Point(120, 47));
/* 115 */       this.jComboBox.setSelectedIndex(0);
/*     */     }
/* 117 */     return this.jComboBox;
/*     */   }
/*     */ 
/*     */   private JButton getOk()
/*     */   {
/* 126 */     if (this.ok == null) {
/* 127 */       this.ok = new JButton();
/* 128 */       this.ok.setText(Mensajes.getString("aceptar"));
/* 129 */       this.ok.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/ok.png")));
/* 130 */       this.ok.setHorizontalTextPosition(2);
/* 131 */       this.ok.setLocation(new Point(25, 85));
/* 132 */       this.ok.setSize(new Dimension(104, 27));
/* 133 */       this.ok.addActionListener(new ActionListener() {
/*     */         public void actionPerformed(ActionEvent e) {
/* 135 */           String text = BorrarVencimientos.this.jTextField.getText();
/* 136 */           if (!text.equals(""))
/* 137 */             BorrarVencimientos.this.borrar(Integer.parseInt(text), BorrarVencimientos.this.jComboBox.getSelectedItem().toString());
/* 138 */           BorrarVencimientos.this.dispose();
/*     */         }
/*     */       });
/*     */     }
/* 142 */     return this.ok;
/*     */   }
/*     */ 
/*     */   private JButton getCancel()
/*     */   {
/* 151 */     if (this.cancel == null) {
/* 152 */       this.cancel = new JButton();
/* 153 */       this.cancel.setText(Mensajes.getString("cancelar"));
/* 154 */       this.cancel.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/cancel.png")));
/* 155 */       this.cancel.setHorizontalTextPosition(2);
/* 156 */       this.cancel.setLocation(new Point(140, 85));
/* 157 */       this.cancel.setSize(new Dimension(100, 27));
/* 158 */       this.cancel.addActionListener(new ActionListener() {
/*     */         public void actionPerformed(ActionEvent e) {
/* 160 */           BorrarVencimientos.this.dispose();
/*     */         }
/*     */       });
/*     */     }
/* 164 */     return this.cancel;
/*     */   }
/*     */ 
/*     */   private void borrar(int num, String tipo) {
/* 168 */     ManejoVencimientos vencimientoM = new ManejoVencimientos(Inicio.getCEmpresa());
/* 169 */     GregorianCalendar fI = new GregorianCalendar();
/* 170 */     if (tipo.equals(Mensajes.getString("years")))
/* 171 */       fI.add(1, -num);
/* 172 */     else if (tipo.equals(Mensajes.getString("months")))
/* 173 */       fI.add(2, -num);
/* 174 */     else if (tipo.equals(Mensajes.getString("days")))
/* 175 */       fI.add(6, -num);
/* 176 */     String fecha = Integer.toString(fI.get(1)) + "-" + Integer.toString(fI.get(2) + 1) + "-" + Integer.toString(fI.get(5));
/*     */ 
/* 179 */     vencimientoM.borrarAnterioresA(!this.aPagar, fecha);
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.dialogosFunciones.BorrarVencimientos
 * JD-Core Version:    0.6.2
 */