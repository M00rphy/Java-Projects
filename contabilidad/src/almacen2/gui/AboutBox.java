/*     */ package almacen2.gui;
/*     */ 
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Container;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.Frame;
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ public class AboutBox extends JDialog
/*     */   implements ActionListener
/*     */ {
/*  21 */   JPanel panel1 = new JPanel();
/*  22 */   JPanel panel2 = new JPanel();
/*  23 */   JPanel insetsPanel1 = new JPanel();
/*  24 */   JPanel insetsPanel2 = new JPanel();
/*  25 */   JPanel insetsPanel3 = new JPanel();
/*  26 */   JButton button1 = new JButton();
/*  27 */   JLabel imageLabel = new JLabel();
/*  28 */   JLabel label1 = new JLabel();
/*  29 */   JLabel label2 = new JLabel();
/*  30 */   JLabel label3 = new JLabel();
/*  31 */   JLabel label4 = new JLabel();
/*  32 */   ImageIcon image1 = new ImageIcon();
/*  33 */   BorderLayout borderLayout1 = new BorderLayout();
/*  34 */   BorderLayout borderLayout2 = new BorderLayout();
/*  35 */   FlowLayout flowLayout1 = new FlowLayout();
/*  36 */   GridLayout gridLayout1 = new GridLayout();
/*  37 */   String product = Mensajes.getString("titulo");
/*  38 */   String version = "1.0";
/*  39 */   String comments = Mensajes.getString("info2");
/*  40 */   String copyright = "Copyright (c) 2008";
/*  41 */   String author = Mensajes.getString("autor");
/*     */ 
/*     */   public AboutBox(Frame owner, boolean modal) {
/*  44 */     super(owner, modal);
/*     */     try {
/*  46 */       setDefaultCloseOperation(2);
/*  47 */       jInit();
/*     */     }
/*     */     catch (Exception exception) {
/*  50 */       exception.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public AboutBox() {
/*  55 */     this(new Frame(), true);
/*     */   }
/*     */ 
/*     */   private void jInit()
/*     */     throws Exception
/*     */   {
/*  64 */     this.product = (this.product + " - " + this.version);
/*  65 */     this.image1 = new ImageIcon(AboutBox.class.getResource("/almacen2/iconos/about.png"));
/*  66 */     this.imageLabel.setIcon(this.image1);
/*  67 */     setTitle(Mensajes.getString("info"));
/*  68 */     this.panel1.setLayout(this.borderLayout1);
/*  69 */     this.panel2.setLayout(this.borderLayout2);
/*  70 */     this.insetsPanel1.setLayout(this.flowLayout1);
/*  71 */     this.insetsPanel2.setLayout(this.flowLayout1);
/*  72 */     this.insetsPanel2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
/*  73 */     this.gridLayout1.setRows(4);
/*  74 */     this.gridLayout1.setColumns(1);
/*  75 */     this.label1.setText(this.product);
/*  76 */     this.label2.setText(this.comments);
/*  77 */     this.label3.setText(this.copyright);
/*  78 */     this.label4.setText(this.author);
/*  79 */     this.insetsPanel3.setLayout(this.gridLayout1);
/*  80 */     this.insetsPanel3.setBorder(BorderFactory.createEmptyBorder(10, 60, 10, 10));
/*  81 */     this.button1.setText(Mensajes.getString("bien"));
/*     */ 
/*  84 */     this.button1.addActionListener(this);
/*  85 */     this.insetsPanel2.add(this.imageLabel, null);
/*  86 */     this.panel2.add(this.insetsPanel2, "West");
/*  87 */     getContentPane().add(this.panel1, null);
/*  88 */     this.insetsPanel3.add(this.label1, null);
/*  89 */     this.insetsPanel3.add(this.label2, null);
/*  90 */     this.insetsPanel3.add(this.label3, null);
/*  91 */     this.insetsPanel3.add(this.label4, null);
/*  92 */     this.panel2.add(this.insetsPanel3, "Center");
/*  93 */     this.insetsPanel1.add(this.button1, null);
/*  94 */     this.panel1.add(this.insetsPanel1, "South");
/*  95 */     this.panel1.add(this.panel2, "North");
/*  96 */     setResizable(true);
/*     */   }
/*     */ 
/*     */   public void actionPerformed(ActionEvent actionEvent)
/*     */   {
/* 105 */     if (actionEvent.getSource() == this.button1)
/* 106 */       dispose();
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.gui.AboutBox
 * JD-Core Version:    0.6.2
 */