/*     */ package pos.view;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.MarcoInicio;
/*     */ import contaes.auxiliar.DocDigitos;
/*     */ import java.awt.Container;
/*     */ import java.awt.Frame;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ import org.jdesktop.layout.GroupLayout;
/*     */ import org.jdesktop.layout.GroupLayout.ParallelGroup;
/*     */ import org.jdesktop.layout.GroupLayout.SequentialGroup;
/*     */ import pos.control.TicketsToFacturacion;
/*     */ 
/*     */ public class TicketsToFacturacionJDialog extends JDialog
/*     */ {
/*     */   private JButton botonCancelar;
/*     */   private JButton botonTraspasar;
/*     */   private JTextField campoFinal;
/*     */   private JTextField campoInicial;
/*     */   private JCheckBox checkBorrar;
/*     */   private JCheckBox isRecargo;
/*     */   private JLabel jLabel1;
/*     */   private JLabel jLabel2;
/*     */   private JPanel jPanel1;
/*     */ 
/*     */   public TicketsToFacturacionJDialog(Frame parent, boolean modal)
/*     */   {
/*  25 */     super(parent, modal);
/*  26 */     initComponents();
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/*  38 */     this.jPanel1 = new JPanel();
/*  39 */     this.jLabel1 = new JLabel();
/*  40 */     this.campoInicial = new JTextField();
/*  41 */     this.jLabel2 = new JLabel();
/*  42 */     this.campoFinal = new JTextField();
/*  43 */     this.botonCancelar = new JButton();
/*  44 */     this.botonTraspasar = new JButton();
/*  45 */     this.isRecargo = new JCheckBox();
/*  46 */     this.checkBorrar = new JCheckBox();
/*     */ 
/*  48 */     setDefaultCloseOperation(2);
/*  49 */     setTitle("Pasar a facturación");
/*     */ 
/*  51 */     this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/*     */ 
/*  53 */     this.jLabel1.setText("Ticket inicial");
/*     */ 
/*  55 */     this.campoInicial.setDocument(new DocDigitos());
/*     */ 
/*  57 */     this.jLabel2.setText("Ticket final");
/*     */ 
/*  59 */     this.campoFinal.setDocument(new DocDigitos());
/*     */ 
/*  61 */     this.botonCancelar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/cancel.png")));
/*  62 */     ResourceBundle bundle = ResourceBundle.getBundle("internationalization/Mensajes");
/*  63 */     this.botonCancelar.setText(bundle.getString("cancelar"));
/*  64 */     this.botonCancelar.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/*  66 */         TicketsToFacturacionJDialog.this.botonCancelarActionPerformed(evt);
/*     */       }
/*     */     });
/*  70 */     this.botonTraspasar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/facturacion18.png")));
/*  71 */     this.botonTraspasar.setText(bundle.getString("pasarAFacturacion"));
/*  72 */     this.botonTraspasar.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/*  74 */         TicketsToFacturacionJDialog.this.botonTraspasarActionPerformed(evt);
/*     */       }
/*     */     });
/*  78 */     this.isRecargo.setText("Aplicar recargo de Equivalencia");
/*     */ 
/*  80 */     this.checkBorrar.setText("Borrar tickets después de traspasar");
/*     */ 
/*  82 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/*  83 */     this.jPanel1.setLayout(jPanel1Layout);
/*  84 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().add(jPanel1Layout.createParallelGroup(1).add(this.jLabel1).add(this.jLabel2)).add(18, 18, 18).add(jPanel1Layout.createParallelGroup(1, false).add(this.campoFinal).add(this.campoInicial, -1, 126, 32767))).add(this.isRecargo).add(jPanel1Layout.createParallelGroup(2).add(jPanel1Layout.createSequentialGroup().add(this.botonTraspasar).addPreferredGap(0).add(this.botonCancelar)).add(this.checkBorrar))).addContainerGap(-1, 32767)));
/*     */ 
/* 106 */     jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(jPanel1Layout.createParallelGroup(3).add(this.jLabel1).add(this.campoInicial, -2, -1, -2)).add(18, 18, 18).add(jPanel1Layout.createParallelGroup(3).add(this.jLabel2).add(this.campoFinal, -2, -1, -2)).add(18, 18, 18).add(this.isRecargo).addPreferredGap(1).add(this.checkBorrar).addPreferredGap(1).add(jPanel1Layout.createParallelGroup(3).add(this.botonTraspasar).add(this.botonCancelar)).addContainerGap(-1, 32767)));
/*     */ 
/* 128 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 129 */     getContentPane().setLayout(layout);
/* 130 */     layout.setHorizontalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(this.jPanel1, -1, -1, 32767).addContainerGap()));
/*     */ 
/* 137 */     layout.setVerticalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(this.jPanel1, -1, -1, 32767).addContainerGap()));
/*     */ 
/* 145 */     pack();
/*     */   }
/*     */ 
/*     */   private void botonCancelarActionPerformed(ActionEvent evt) {
/* 149 */     dispose();
/*     */   }
/*     */ 
/*     */   private void botonTraspasarActionPerformed(ActionEvent evt) {
/* 153 */     String ini = this.campoInicial.getText();
/* 154 */     String fin = this.campoFinal.getText();
/* 155 */     if ((!ini.equals("")) && (!fin.equals(""))) {
/* 156 */       int[] rangoTickets = { Integer.parseInt(ini), Integer.parseInt(fin) };
/* 157 */       TicketsToFacturacion tTF = new TicketsToFacturacion(rangoTickets, this.isRecargo.isSelected(), this.checkBorrar.isSelected());
/* 158 */       tTF.realizarTraspaso();
/* 159 */       JOptionPane.showMessageDialog(Inicio.frame, "Proceso terminado", "Información", 1);
/*     */ 
/* 162 */       Inicio.frame.renovarTabla(7);
/* 163 */       dispose();
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.view.TicketsToFacturacionJDialog
 * JD-Core Version:    0.6.2
 */