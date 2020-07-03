/*     */ package contaes.empresas;
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
/*  45 */   JPanel panel1 = new JPanel();
/*  46 */   JPanel panel2 = new JPanel();
/*  47 */   JPanel insetsPanel1 = new JPanel();
/*  48 */   JPanel insetsPanel2 = new JPanel();
/*  49 */   JPanel insetsPanel3 = new JPanel();
/*  50 */   JButton button1 = new JButton();
/*  51 */   JLabel imageLabel = new JLabel();
/*  52 */   JLabel label1 = new JLabel();
/*  53 */   JLabel label2 = new JLabel();
/*  54 */   JLabel label3 = new JLabel();
/*  55 */   JLabel label4 = new JLabel();
/*  56 */   ImageIcon image1 = new ImageIcon();
/*  57 */   BorderLayout borderLayout1 = new BorderLayout();
/*  58 */   BorderLayout borderLayout2 = new BorderLayout();
/*  59 */   FlowLayout flowLayout1 = new FlowLayout();
/*  60 */   GridLayout gridLayout1 = new GridLayout();
/*  61 */   String product = "ContaEs 4";
/*  62 */   String version = "2.5";
/*  63 */   String comments = Mensajes.getString("info2");
/*  64 */   String copyright = "Copyright (c) 2009";
/*  65 */   String author = Mensajes.getString("autor");
/*     */ 
/*     */   public AboutBox(Frame owner, boolean modal) {
/*  68 */     super(owner, modal);
/*     */     try {
/*  70 */       setDefaultCloseOperation(2);
/*  71 */       jInit();
/*     */     }
/*     */     catch (Exception exception) {
/*  74 */       exception.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public AboutBox() {
/*  79 */     this(new Frame(), true);
/*     */   }
/*     */ 
/*     */   private void jInit()
/*     */     throws Exception
/*     */   {
/*  88 */     this.product = (this.product + " - " + this.version);
/*  89 */     this.image1 = new ImageIcon(AboutBox.class.getResource("/contaes/iconos/about.png"));
/*  90 */     this.imageLabel.setIcon(this.image1);
/*  91 */     setTitle(Mensajes.getString("info"));
/*  92 */     this.panel1.setLayout(this.borderLayout1);
/*  93 */     this.panel2.setLayout(this.borderLayout2);
/*  94 */     this.insetsPanel1.setLayout(this.flowLayout1);
/*  95 */     this.insetsPanel2.setLayout(this.flowLayout1);
/*  96 */     this.insetsPanel2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
/*  97 */     this.gridLayout1.setRows(4);
/*  98 */     this.gridLayout1.setColumns(1);
/*  99 */     this.label1.setText(this.product);
/* 100 */     this.label2.setText(this.comments);
/* 101 */     this.label3.setText(this.copyright);
/* 102 */     this.label4.setText(this.author);
/* 103 */     this.insetsPanel3.setLayout(this.gridLayout1);
/* 104 */     this.insetsPanel3.setBorder(BorderFactory.createEmptyBorder(10, 60, 10, 10));
/* 105 */     this.button1.setText(Mensajes.getString("aceptar"));
/* 106 */     this.button1.setHorizontalTextPosition(2);
/* 107 */     this.button1.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/ok.png")));
/* 108 */     this.button1.addActionListener(this);
/* 109 */     this.insetsPanel2.add(this.imageLabel, null);
/* 110 */     this.panel2.add(this.insetsPanel2, "West");
/* 111 */     getContentPane().add(this.panel1, null);
/* 112 */     this.insetsPanel3.add(this.label1, null);
/* 113 */     this.insetsPanel3.add(this.label2, null);
/* 114 */     this.insetsPanel3.add(this.label3, null);
/* 115 */     this.insetsPanel3.add(this.label4, null);
/* 116 */     this.panel2.add(this.insetsPanel3, "Center");
/* 117 */     this.insetsPanel1.add(this.button1, null);
/* 118 */     this.panel1.add(this.insetsPanel1, "South");
/* 119 */     this.panel1.add(this.panel2, "North");
/* 120 */     setResizable(true);
/*     */   }
/*     */ 
/*     */   public void actionPerformed(ActionEvent actionEvent)
/*     */   {
/* 129 */     if (actionEvent.getSource() == this.button1)
/* 130 */       dispose();
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.empresas.AboutBox
 * JD-Core Version:    0.6.2
 */