/*     */ package almacen2.gui.pedidos;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.auxiliar.DocDigitos;
/*     */ import contaes.auxiliar.DocNumeros;
/*     */ import contaes.dialogosFunciones.Calculadora;
/*     */ import contaes.manejoDatos.funciones.ComprobarDato;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Frame;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.KeyAdapter;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ import org.jdesktop.layout.GroupLayout;
/*     */ import org.jdesktop.layout.GroupLayout.ParallelGroup;
/*     */ import org.jdesktop.layout.GroupLayout.SequentialGroup;
/*     */ 
/*     */ public class GetImporteJDialog extends JDialog
/*     */ {
/*     */   private Double importe;
/*     */   private String datos;
/*     */   private JTextField campoImporte;
/*     */   private JTextField campoUnidades;
/*     */   private JButton jButton1;
/*     */   private JLabel jLabel1;
/*     */   private JLabel jLabel2;
/*     */   private JPanel jPanel1;
/*     */ 
/*     */   public GetImporteJDialog(Frame parent, boolean modal, Double importe)
/*     */   {
/*  34 */     super(parent, modal);
/*  35 */     initComponents();
/*  36 */     this.importe = importe;
/*  37 */     this.campoImporte.setText(importe.toString());
/*     */   }
/*     */ 
/*     */   public static String obtenerDatos(Frame parent, boolean modal, Double importe) {
/*  41 */     GetImporteJDialog dlg = new GetImporteJDialog(parent, modal, importe);
/*  42 */     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/*  43 */     Dimension frameSize = dlg.getSize();
/*  44 */     if (frameSize.height > screenSize.height) {
/*  45 */       frameSize.height = screenSize.height;
/*     */     }
/*  47 */     if (frameSize.width > screenSize.width) {
/*  48 */       frameSize.width = screenSize.width;
/*     */     }
/*  50 */     dlg.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
/*     */ 
/*  53 */     dlg.setVisible(true);
/*  54 */     return dlg.getDatos();
/*     */   }
/*     */ 
/*     */   public Double getImporte() {
/*  58 */     return this.importe;
/*     */   }
/*     */ 
/*     */   public String getDatos() {
/*  62 */     return this.datos;
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/*  74 */     this.jPanel1 = new JPanel();
/*  75 */     this.jLabel1 = new JLabel();
/*  76 */     this.campoImporte = new JTextField();
/*  77 */     this.jButton1 = new JButton();
/*  78 */     this.jLabel2 = new JLabel();
/*  79 */     this.campoUnidades = new JTextField();
/*     */ 
/*  81 */     setDefaultCloseOperation(2);
/*  82 */     ResourceBundle bundle = ResourceBundle.getBundle("internationalization/Mensajes");
/*  83 */     setTitle(bundle.getString("importe"));
/*     */ 
/*  85 */     this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/*     */ 
/*  87 */     this.jLabel1.setText(bundle.getString("introImporte"));
/*     */ 
/*  89 */     this.campoImporte.setDocument(new DocNumeros());
/*  90 */     this.campoImporte.addKeyListener(new KeyAdapter() {
/*     */       public void keyPressed(KeyEvent evt) {
/*  92 */         GetImporteJDialog.this.campoImporteKeyPressed(evt);
/*     */       }
/*     */     });
/*  96 */     this.jButton1.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/apply.png")));
/*  97 */     this.jButton1.setText(bundle.getString("aceptar"));
/*  98 */     this.jButton1.setHorizontalTextPosition(2);
/*  99 */     this.jButton1.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 101 */         GetImporteJDialog.this.jButton1ActionPerformed(evt);
/*     */       }
/*     */     });
/* 105 */     this.jLabel2.setText(bundle.getString("unidades"));
/*     */ 
/* 107 */     this.campoUnidades.setDocument(new DocDigitos());
/* 108 */     this.campoUnidades.setText("1");
/*     */ 
/* 110 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 111 */     this.jPanel1.setLayout(jPanel1Layout);
/* 112 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().add(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(this.jLabel2)).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(this.campoUnidades, -2, 82, -2)).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(this.jLabel1)).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().add(21, 21, 21).add(this.jButton1)).add(this.campoImporte, -1, 135, 32767)))).addContainerGap()));
/*     */ 
/* 134 */     jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().add(10, 10, 10).add(this.jLabel2).addPreferredGap(0).add(this.campoUnidades, -2, -1, -2).addPreferredGap(0).add(this.jLabel1).addPreferredGap(0).add(this.campoImporte, -2, -1, -2).addPreferredGap(1).add(this.jButton1).addContainerGap(15, 32767)));
/*     */ 
/* 150 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 151 */     getContentPane().setLayout(layout);
/* 152 */     layout.setHorizontalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(this.jPanel1, -1, -1, 32767).addContainerGap()));
/*     */ 
/* 159 */     layout.setVerticalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(this.jPanel1, -1, -1, 32767).addContainerGap()));
/*     */ 
/* 167 */     pack();
/*     */   }
/*     */ 
/*     */   private void jButton1ActionPerformed(ActionEvent evt) {
/* 171 */     String textoImporte = this.campoImporte.getText();
/* 172 */     String textoUnidades = this.campoUnidades.getText();
/* 173 */     ComprobarDato cD = new ComprobarDato(textoImporte);
/* 174 */     if (cD.esDoble()) {
/* 175 */       this.datos = (textoUnidades + "-" + textoImporte);
/* 176 */       this.importe = Double.valueOf(Double.parseDouble(textoImporte));
/* 177 */       dispose();
/*     */     }
/*     */     else {
/* 180 */       JOptionPane.showMessageDialog(getParent(), "El importe introducido no parece ser un número", "Error", 0);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void campoImporteKeyPressed(KeyEvent evt)
/*     */   {
/* 186 */     int tecla = evt.getKeyCode();
/* 187 */     Object campo = evt.getSource();
/* 188 */     if ((evt.isAltDown()) && 
/* 189 */       (campo == this.campoImporte))
/* 190 */       if (tecla == 67) {
/* 191 */         Inicio.calculadora.colocaOrigen(this.campoImporte);
/* 192 */         Inicio.calculadora.setVisible(true);
/* 193 */       } else if (tecla == 80) {
/* 194 */         this.campoImporte.setText(Inicio.calculadora.getResultado());
/*     */       }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.gui.pedidos.GetImporteJDialog
 * JD-Core Version:    0.6.2
 */