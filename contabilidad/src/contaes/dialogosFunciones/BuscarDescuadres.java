/*     */ package contaes.dialogosFunciones;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.Puente;
/*     */ import contaes.manejoDatos.funciones.BuscarDescuabres;
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.Container;
/*     */ import java.awt.Cursor;
/*     */ import java.awt.Frame;
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
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTextArea;
import javax.swing.LayoutStyle;
/*     */ import javax.swing.LayoutStyle.ComponentPlacement;
/*     */ 
/*     */ public class BuscarDescuadres extends JDialog
/*     */ {
/*     */   private JButton jButton1;
/*     */   private JButton jButton2;
/*     */   private JPanel jPanel1;
/*     */   private JScrollPane jScrollPane1;
/*     */   private JTextArea jTextArea1;
/*     */ 
/*     */   public BuscarDescuadres(Frame parent, boolean modal)
/*     */   {
/*  26 */     super(parent, modal);
/*  27 */     initComponents();
/*     */   }
/*     */ 
/*     */   private void ejecutar() {
/*  31 */     this.jTextArea1.setText("");
/*  32 */     setCursor(Cursor.getPredefinedCursor(3));
/*  33 */     this.jTextArea1.setText(Mensajes.getString("buscando") + " ...");
/*  34 */     String listaDescuadres = BuscarDescuabres.Comprobar(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*     */ 
/*  36 */     setCursor(Cursor.getPredefinedCursor(0));
/*  37 */     if (!listaDescuadres.equals(""))
/*  38 */       this.jTextArea1.setText(listaDescuadres);
/*     */     else
/*  40 */       this.jTextArea1.setText(Mensajes.getString("noDescuadres"));
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/*  53 */     this.jPanel1 = new JPanel();
/*  54 */     this.jScrollPane1 = new JScrollPane();
/*  55 */     this.jTextArea1 = new JTextArea();
/*  56 */     this.jButton1 = new JButton();
/*  57 */     this.jButton2 = new JButton();
/*     */ 
/*  59 */     setDefaultCloseOperation(2);
/*  60 */     ResourceBundle bundle = ResourceBundle.getBundle("internationalization/Mensajes");
/*  61 */     setTitle(bundle.getString("descuadres"));
/*     */ 
/*  63 */     this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/*     */ 
/*  65 */     this.jTextArea1.setColumns(20);
/*  66 */     this.jTextArea1.setEditable(false);
/*  67 */     this.jTextArea1.setRows(5);
/*  68 */     this.jScrollPane1.setViewportView(this.jTextArea1);
/*     */ 
/*  70 */     this.jButton1.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/find.png")));
/*  71 */     this.jButton1.setText(bundle.getString("buscar"));
/*  72 */     this.jButton1.setHorizontalTextPosition(2);
/*  73 */     this.jButton1.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/*  75 */         BuscarDescuadres.this.jButton1ActionPerformed(evt);
/*     */       }
/*     */     });
/*  79 */     this.jButton2.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/cancel.png")));
/*  80 */     this.jButton2.setText(bundle.getString("cancelar"));
/*  81 */     this.jButton2.setHorizontalTextPosition(2);
/*  82 */     this.jButton2.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/*  84 */         BuscarDescuadres.this.jButton2ActionPerformed(evt);
/*     */       }
/*     */     });
/*  88 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/*  89 */     this.jPanel1.setLayout(jPanel1Layout);
/*  90 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jScrollPane1, -1, 454, 32767).addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup().addComponent(this.jButton2).addGap(18, 18, 18).addComponent(this.jButton1))).addContainerGap()));
/*     */ 
/* 102 */     jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(this.jScrollPane1, -2, 131, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jButton1).addComponent(this.jButton2)).addContainerGap(-1, 32767)));
/*     */ 
/* 114 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 115 */     getContentPane().setLayout(layout);
/* 116 */     layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -1, -1, 32767).addContainerGap()));
/*     */ 
/* 123 */     layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -1, -1, 32767).addContainerGap()));
/*     */ 
/* 131 */     pack();
/*     */   }
/*     */ 
/*     */   private void jButton2ActionPerformed(ActionEvent evt) {
/* 135 */     dispose();
/*     */   }
/*     */ 
/*     */   private void jButton1ActionPerformed(ActionEvent evt) {
/* 139 */     ejecutar();
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.dialogosFunciones.BuscarDescuadres
 * JD-Core Version:    0.6.2
 */