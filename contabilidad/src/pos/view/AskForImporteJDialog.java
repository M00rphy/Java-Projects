/*     */ package pos.view;
/*     */ 
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Frame;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.KeyAdapter;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.LayoutStyle;
/*     */ 
/*     */ public class AskForImporteJDialog extends JDialog
/*     */ {
/*  23 */   private double importe = 0.0D;
/*     */   private JTextField campoImporte;
/*     */   private JButton jButton1;
/*     */   private JLabel jLabel1;
/*     */   private JNumberKeys jNumberKeys1;
/*     */   private JPanel jPanel1;
/*     */   private JLabel productoJLabel;
/*     */ 
/*     */   public AskForImporteJDialog(Frame parent, boolean modal, String producto)
/*     */   {
/*  27 */     super(parent, modal);
/*  28 */     initComponents();
/*  29 */     this.productoJLabel.setText(producto);
/*     */   }
/*     */ 
/*     */   public static double obtenerObjeto(Frame parent, boolean modal, String producto) {
/*  33 */     AskForImporteJDialog dlg = new AskForImporteJDialog(parent, modal, producto);
/*  34 */     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/*  35 */     Dimension frameSize = dlg.getSize();
/*  36 */     if (frameSize.height > screenSize.height) {
/*  37 */       frameSize.height = screenSize.height;
/*     */     }
/*  39 */     if (frameSize.width > screenSize.width) {
/*  40 */       frameSize.width = screenSize.width;
/*     */     }
/*  42 */     dlg.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
/*     */ 
/*  45 */     dlg.setVisible(true);
/*  46 */     return dlg.getImporte();
/*     */   }
/*     */ 
/*     */   private void asignarImporte() {
/*  50 */     String impSt = this.campoImporte.getText();
/*  51 */     if ((impSt != null) && (!impSt.equals("")))
/*     */       try {
/*  53 */         this.importe = Double.valueOf(impSt).doubleValue();
/*  54 */         dispose();
/*     */       }
/*     */       catch (NumberFormatException e) {
/*  57 */         System.out.println("Importe no válido");
/*     */       }
/*     */   }
/*     */ 
/*     */   private void botonPanelPulsado(char c)
/*     */   {
/*  63 */     if (c == '\n') {
/*  64 */       asignarImporte();
/*     */     }
/*  66 */     else if (c == '0') {
/*  67 */       this.campoImporte.setText(this.campoImporte.getText() + "0");
/*     */     }
/*  69 */     else if (c == '1') {
/*  70 */       this.campoImporte.setText(this.campoImporte.getText() + "1");
/*     */     }
/*  72 */     else if (c == '2') {
/*  73 */       this.campoImporte.setText(this.campoImporte.getText() + "2");
/*     */     }
/*  75 */     else if (c == '3') {
/*  76 */       this.campoImporte.setText(this.campoImporte.getText() + "3");
/*     */     }
/*  78 */     else if (c == '4') {
/*  79 */       this.campoImporte.setText(this.campoImporte.getText() + "4");
/*     */     }
/*  81 */     else if (c == '5') {
/*  82 */       this.campoImporte.setText(this.campoImporte.getText() + "5");
/*     */     }
/*  84 */     else if (c == '6') {
/*  85 */       this.campoImporte.setText(this.campoImporte.getText() + "6");
/*     */     }
/*  87 */     else if (c == '7') {
/*  88 */       this.campoImporte.setText(this.campoImporte.getText() + "7");
/*     */     }
/*  90 */     else if (c == '8') {
/*  91 */       this.campoImporte.setText(this.campoImporte.getText() + "8");
/*     */     }
/*  93 */     else if (c == '9') {
/*  94 */       this.campoImporte.setText(this.campoImporte.getText() + "9");
/*     */     }
/*  96 */     else if (c == '.') {
/*  97 */       this.campoImporte.setText(this.campoImporte.getText() + ".");
/*     */     }
/*  99 */     else if (c == '') {
/* 100 */       this.campoImporte.setText("");
/*     */     }
/* 102 */     else if (c != '*')
/*     */     {
/* 105 */       if (c != '+')
/*     */       {
/* 108 */         if (c != '-')
/*     */         {
/* 111 */           if (c == '=')
/* 112 */             asignarImporte();  } 
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/* 117 */   public double getImporte() { return this.importe; }
/*     */ 
/*     */ 
/*     */   private void initComponents()
/*     */   {
/* 129 */     this.jPanel1 = new JPanel();
/* 130 */     this.jNumberKeys1 = new JNumberKeys();
/* 131 */     this.campoImporte = new JTextField();
/* 132 */     this.jButton1 = new JButton();
/* 133 */     this.jLabel1 = new JLabel();
/* 134 */     this.productoJLabel = new JLabel();
/*     */ 
/* 136 */     setDefaultCloseOperation(2);
/* 137 */     ResourceBundle bundle = ResourceBundle.getBundle("internationalization/Mensajes");
/* 138 */     setTitle(bundle.getString("importe"));
/*     */ 
/* 140 */     this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/*     */ 
/* 142 */     this.jNumberKeys1.addJNumberEventListener(new JNumberEventListener() {
/*     */       public void keyPerformed(JNumberEvent evt) {
/* 144 */         AskForImporteJDialog.this.jNumberKeys1KeyPerformed(evt);
/*     */       }
/*     */     });
/* 148 */     this.campoImporte.addKeyListener(new KeyAdapter() {
/*     */       public void keyPressed(KeyEvent evt) {
/* 150 */         AskForImporteJDialog.this.campoImporteKeyPressed(evt);
/*     */       }
/*     */     });
/* 154 */     this.jButton1.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/cash22.png")));
/* 155 */     this.jButton1.setText(bundle.getString("bien"));
/* 156 */     this.jButton1.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 158 */         AskForImporteJDialog.this.jButton1ActionPerformed(evt);
/*     */       }
/*     */     });
/* 162 */     this.jLabel1.setText("Importe final para:");
/* 163 */     this.jLabel1.setVerticalAlignment(1);
/*     */ 
/* 165 */     this.productoJLabel.setVerticalAlignment(1);
/*     */ 
/* 167 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 168 */     this.jPanel1.setLayout(jPanel1Layout);
/* 169 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel1, -1, 261, 32767).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false).addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup().addGap(6, 6, 6).addComponent(this.campoImporte, -2, 145, -2).addGap(30, 30, 30).addComponent(this.jButton1)).addComponent(this.jNumberKeys1, GroupLayout.Alignment.LEADING, -1, -1, 32767)))).addGroup(jPanel1Layout.createSequentialGroup().addGap(12, 12, 12).addComponent(this.productoJLabel, -1, 255, 32767))).addContainerGap()));
/*     */ 
/* 189 */     jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.productoJLabel, -1, 33, 32767).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jNumberKeys1, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.campoImporte, -2, 38, -2).addComponent(this.jButton1)).addContainerGap()));
/*     */ 
/* 205 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 206 */     getContentPane().setLayout(layout);
/* 207 */     layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -2, -1, -2).addContainerGap(-1, 32767)));
/*     */ 
/* 214 */     layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -1, -1, 32767).addContainerGap()));
/*     */ 
/* 222 */     pack();
/*     */   }
/*     */ 
/*     */   private void campoImporteKeyPressed(KeyEvent evt) {
/* 226 */     if (evt.getKeyCode() == 10)
/* 227 */       asignarImporte();
/*     */   }
/*     */ 
/*     */   private void jButton1ActionPerformed(ActionEvent evt)
/*     */   {
/* 232 */     asignarImporte();
/*     */   }
/*     */ 
/*     */   private void jNumberKeys1KeyPerformed(JNumberEvent evt) {
/* 236 */     botonPanelPulsado(evt.getKey());
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.view.AskForImporteJDialog
 * JD-Core Version:    0.6.2
 */