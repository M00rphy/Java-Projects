/*     */ package pos.view;
/*     */ 
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Frame;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.GroupLayout.Alignment;
/*     */ import javax.swing.GroupLayout.ParallelGroup;
/*     */ import javax.swing.GroupLayout.SequentialGroup;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
import javax.swing.LayoutStyle;
/*     */ import javax.swing.LayoutStyle.ComponentPlacement;
/*     */ import pos.view.editors.JEditorCurrency;
/*     */ import pos.view.editors.JEditorKeys;
/*     */ 
/*     */ public class PagoCajaJDialog extends JDialog
/*     */ {
/*     */   private Double importe;
/*  27 */   private Double pagado = null;
/*     */   private JButton botonAceptar;
/*     */   private JTextField campoDevolucion;
/*     */   private JEditorCurrency campoEntrega;
/*     */   private JTextField campoImporte;
/*     */   private JButton jButton1;
/*     */   private JEditorKeys jEditorKeys1;
/*     */   private JLabel jLabel1;
/*     */   private JLabel jLabel2;
/*     */   private JLabel jLabel3;
/*     */   private JPanel jPanel1;
/*     */ 
/*     */   public PagoCajaJDialog(Frame parent, boolean modal, Double importe)
/*     */   {
/*  31 */     super(parent, modal);
/*  32 */     initComponents();
/*     */ 
/*  34 */     this.campoEntrega.addPropertyChangeListener("Edition", new RecalculateDevol());
/*  35 */     this.campoEntrega.addEditorKeys(this.jEditorKeys1);
/*  36 */     this.campoEntrega.activate();
/*     */ 
/*  38 */     this.importe = importe;
/*  39 */     if (importe != null) {
/*  40 */       String imp = String.format("%,13.2f", new Object[] { this.importe });
/*  41 */       this.campoImporte.setText(imp + " " + Mensajes.getString("moneda"));
/*     */     }
/*     */   }
/*     */ 
/*     */   public static Double obtenerObjeto(Frame parent, boolean modal, Double importe) {
/*  46 */     PagoCajaJDialog dlg = new PagoCajaJDialog(parent, modal, importe);
/*  47 */     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/*  48 */     Dimension frameSize = dlg.getSize();
/*  49 */     if (frameSize.height > screenSize.height) {
/*  50 */       frameSize.height = screenSize.height;
/*     */     }
/*  52 */     if (frameSize.width > screenSize.width) {
/*  53 */       frameSize.width = screenSize.width;
/*     */     }
/*  55 */     dlg.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
/*     */ 
/*  58 */     dlg.setVisible(true);
/*  59 */     return dlg.getPagado();
/*     */   }
/*     */ 
/*     */   public Double getPagado() {
/*  63 */     return this.pagado;
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/*  75 */     this.jPanel1 = new JPanel();
/*  76 */     this.jLabel1 = new JLabel();
/*  77 */     this.campoImporte = new JTextField();
/*  78 */     this.jLabel2 = new JLabel();
/*  79 */     this.campoEntrega = new JEditorCurrency();
/*  80 */     this.jLabel3 = new JLabel();
/*  81 */     this.campoDevolucion = new JTextField();
/*  82 */     this.botonAceptar = new JButton();
/*  83 */     this.jButton1 = new JButton();
/*  84 */     this.jEditorKeys1 = new JEditorKeys();
/*     */ 
/*  86 */     setDefaultCloseOperation(2);
/*  87 */     ResourceBundle bundle = ResourceBundle.getBundle("internationalization/Mensajes");
/*  88 */     setTitle(bundle.getString("cobroticket"));
/*     */ 
/*  90 */     this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/*     */ 
/*  92 */     this.jLabel1.setText(bundle.getString("importecobrar"));
/*     */ 
/*  94 */     this.campoImporte.setEditable(false);
/*     */ 
/*  96 */     this.jLabel2.setText(bundle.getString("entrega"));
/*     */ 
/*  98 */     this.jLabel3.setText(bundle.getString("devolucion"));
/*     */ 
/* 100 */     this.campoDevolucion.setEditable(false);
/*     */ 
/* 102 */     this.botonAceptar.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/button_ok.png")));
/* 103 */     this.botonAceptar.setText(bundle.getString("aceptar"));
/* 104 */     this.botonAceptar.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 106 */         PagoCajaJDialog.this.botonAceptarActionPerformed(evt);
/*     */       }
/*     */     });
/* 110 */     this.jButton1.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/button_cancel.png")));
/* 111 */     this.jButton1.setText(bundle.getString("cancelar"));
/* 112 */     this.jButton1.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 114 */         PagoCajaJDialog.this.jButton1ActionPerformed(evt);
/*     */       }
/*     */     });
/* 118 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 119 */     this.jPanel1.setLayout(jPanel1Layout);
/* 120 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.campoImporte, -1, 156, 32767).addComponent(this.jLabel1).addComponent(this.jLabel2).addComponent(this.jLabel3).addComponent(this.campoDevolucion).addComponent(this.campoEntrega, -1, -1, 32767)).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.botonAceptar).addGap(12, 12, 12).addComponent(this.jButton1))).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jEditorKeys1, -2, -1, -2).addContainerGap(-1, 32767)));
/*     */ 
/* 140 */     jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jEditorKeys1, -2, -1, -2).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.jLabel1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.campoImporte, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jLabel2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.campoEntrega, -2, -1, -2).addGap(18, 18, 18).addComponent(this.jLabel3).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.campoDevolucion, -2, -1, -2).addGap(49, 49, 49).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jButton1).addComponent(this.botonAceptar)))).addContainerGap(-1, 32767)));
/*     */ 
/* 165 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 166 */     getContentPane().setLayout(layout);
/* 167 */     layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -1, -1, 32767).addContainerGap()));
/*     */ 
/* 174 */     layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -1, -1, 32767).addContainerGap()));
/*     */ 
/* 182 */     pack();
/*     */   }
/*     */ 
/*     */   private void botonAceptarActionPerformed(ActionEvent evt) {
/* 186 */     if (!this.campoEntrega.getText().equals("")) {
/* 187 */       this.pagado = this.campoEntrega.getDoubleValue();
/*     */     }
/* 189 */     dispose();
/*     */   }
/*     */ 
/*     */   private void jButton1ActionPerformed(ActionEvent evt) {
/* 193 */     dispose();
/*     */   }
/*     */   private class RecalculateDevol implements PropertyChangeListener {
/*     */     private RecalculateDevol() {
/*     */     }
/*     */     public void propertyChange(PropertyChangeEvent evt) {
/* 199 */       if (!PagoCajaJDialog.this.campoEntrega.getText().equals("")) {
/* 200 */         Double en = PagoCajaJDialog.this.campoEntrega.getDoubleValue();
/* 201 */         if (en != null) {
/* 202 */           double devo = en.doubleValue() - PagoCajaJDialog.this.importe.doubleValue();
/* 203 */           String temp = String.format("%,13.2f", new Object[] { Double.valueOf(devo) });
/* 204 */           PagoCajaJDialog.this.campoDevolucion.setText(temp + " " + Mensajes.getString("moneda"));
/*     */         }
/*     */       }
/*     */       else {
/* 208 */         PagoCajaJDialog.this.campoDevolucion.setText("");
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.view.PagoCajaJDialog
 * JD-Core Version:    0.6.2
 */