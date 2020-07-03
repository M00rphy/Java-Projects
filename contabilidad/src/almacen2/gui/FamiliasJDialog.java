/*     */ package almacen2.gui;
/*     */ 
/*     */ import almacen2.data.FPObject;
/*     */ import almacen2.data.ManejadorSubcuentas;
/*     */ import almacen2.data.SubcuentaObject;
/*     */ import almacen2.data.UserObject;
/*     */ import contaes.auxiliar.DocNumPositivos;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Frame;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.ArrayList;
import java.util.List;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.DefaultComboBoxModel;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ import org.jdesktop.layout.GroupLayout;
/*     */ import org.jdesktop.layout.GroupLayout.ParallelGroup;
/*     */ import org.jdesktop.layout.GroupLayout.SequentialGroup;
/*     */ 
/*     */ public class FamiliasJDialog extends JDialog
/*     */ {
/*  31 */   private DefaultComboBoxModel modeloVentas = new DefaultComboBoxModel();
/*  32 */   private DefaultComboBoxModel modeloCompras = new DefaultComboBoxModel();
/*     */   private FPObject objeto;
/*     */   private JTextField campoNombre;
/*     */   private JTextField campoNumero;
/*     */   private JComboBox comboCompras;
/*     */   private JComboBox comboVentas;
/*     */   private JButton jButton1;
/*     */   private JButton jButton2;
/*     */   private JLabel jLabel1;
/*     */   private JLabel jLabel2;
/*     */   private JLabel jLabel3;
/*     */   private JLabel jLabel4;
/*     */   private JPanel jPanel1;
/*     */ 
/*     */   public FamiliasJDialog(Frame parent, boolean modal, UserObject usuario, FPObject objeto)
/*     */   {
/*  37 */     super(parent, modal);
/*     */ 
/*  39 */     initComponents();
/*  40 */     setModeloCombos(usuario);
/*  41 */     cargarObjeto(usuario, objeto);
/*     */   }
/*     */ 
/*     */   public static FPObject obtenerElemento(Frame parent, boolean modal, UserObject usuario, FPObject objeto)
/*     */   {
/*  46 */     FamiliasJDialog dlg = new FamiliasJDialog(parent, modal, usuario, objeto);
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
/*  57 */     dlg.pack();
/*  58 */     dlg.setVisible(true);
/*  59 */     return dlg.obtenerObjetoFinal();
/*     */   }
/*     */ 
/*     */   private void setModeloCombos(UserObject usuario) {
/*  63 */     ManejadorSubcuentas mS = new ManejadorSubcuentas(usuario);
/*     */ 
/*  65 */     ArrayList listaSubcVentas = new ArrayList();
/*  66 */     listaSubcVentas = mS.listaSubcuentas(70000000, 70999999);
/*  67 */     for (SubcuentaObject subcuentaObject : (List<SubcuentaObject>)listaSubcVentas) {
/*  68 */       this.modeloVentas.addElement(subcuentaObject);
/*     */     }
/*  70 */     this.comboVentas.setModel(this.modeloVentas);
/*     */ 
/*  72 */     ArrayList listaSubcCompras = new ArrayList();
/*  73 */     listaSubcCompras = mS.listaSubcuentas(60000000, 60999999);
/*  74 */     for (SubcuentaObject subcuentaObject :(List<SubcuentaObject>) listaSubcCompras) {
/*  75 */       this.modeloCompras.addElement(subcuentaObject);
/*     */     }
/*  77 */     this.comboCompras.setModel(this.modeloCompras);
/*     */   }
/*     */ 
/*     */   private void cargarObjeto(UserObject usuario, FPObject objetoOld) {
/*  81 */     if (objetoOld != null) {
/*  82 */       this.campoNumero.setText(Integer.toString(objetoOld.getId()));
/*  83 */       this.campoNombre.setText(objetoOld.getNombre());
/*  84 */       ManejadorSubcuentas mS = new ManejadorSubcuentas(usuario);
/*  85 */       SubcuentaObject subcuenta = mS.getSubcuenta(objetoOld.getSubcuenta1());
/*  86 */       if (subcuenta != null) {
/*  87 */         this.modeloVentas.setSelectedItem(subcuenta);
/*     */       }
/*  89 */       subcuenta = mS.getSubcuenta(objetoOld.getSubcuenta2());
/*  90 */       if (subcuenta != null)
/*  91 */         this.modeloCompras.setSelectedItem(subcuenta);
/*     */     }
/*     */   }
/*     */ 
/*     */   private FPObject obtenerObjetoFinal()
/*     */   {
/*  97 */     return this.objeto;
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/* 109 */     this.jPanel1 = new JPanel();
/* 110 */     this.jLabel1 = new JLabel();
/* 111 */     this.campoNumero = new JTextField();
/* 112 */     this.jLabel2 = new JLabel();
/* 113 */     this.campoNombre = new JTextField();
/* 114 */     this.jLabel3 = new JLabel();
/* 115 */     this.comboVentas = new JComboBox();
/* 116 */     this.jLabel4 = new JLabel();
/* 117 */     this.comboCompras = new JComboBox();
/* 118 */     this.jButton1 = new JButton();
/* 119 */     this.jButton2 = new JButton();
/*     */ 
/* 121 */     setDefaultCloseOperation(2);
/* 122 */     setTitle("Familias");
/*     */ 
/* 124 */     this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/*     */ 
/* 126 */     this.jLabel1.setText("Número");
/*     */ 
/* 128 */     this.campoNumero.setDocument(new DocNumPositivos());
/*     */ 
/* 130 */     this.jLabel2.setText("Nombre");
/*     */ 
/* 132 */     this.jLabel3.setText("Cuenta Ventas");
/*     */ 
/* 134 */     this.jLabel4.setText("Cuenta Compras");
/*     */ 
/* 136 */     this.jButton1.setIcon(new ImageIcon(getClass().getResource("/almacen2/iconos/apply.png")));
/* 137 */     this.jButton1.setText("Guardar");
/* 138 */     this.jButton1.setHorizontalTextPosition(2);
/* 139 */     this.jButton1.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 141 */         FamiliasJDialog.this.jButton1ActionPerformed(evt);
/*     */       }
/*     */     });
/* 145 */     this.jButton2.setIcon(new ImageIcon(getClass().getResource("/almacen2/iconos/cancel.png")));
/* 146 */     this.jButton2.setText("Cancelar");
/* 147 */     this.jButton2.setHorizontalTextPosition(2);
/* 148 */     this.jButton2.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 150 */         FamiliasJDialog.this.jButton2ActionPerformed(evt);
/*     */       }
/*     */     });
/* 154 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 155 */     this.jPanel1.setLayout(jPanel1Layout);
/* 156 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().add(this.jLabel1).add(18, 18, 18).add(this.campoNumero, -2, 109, -2)).add(jPanel1Layout.createSequentialGroup().add(this.jLabel2).add(18, 18, 18).add(this.campoNombre, -1, 324, 32767)).add(jPanel1Layout.createSequentialGroup().add(this.jLabel3).add(18, 18, 18).add(this.comboVentas, 0, 284, 32767)).add(jPanel1Layout.createSequentialGroup().add(this.jLabel4).addPreferredGap(0).add(this.comboCompras, 0, 282, 32767)).add(jPanel1Layout.createSequentialGroup().add(this.jButton1).add(18, 18, 18).add(this.jButton2))).addContainerGap()));
/*     */ 
/* 183 */     jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(jPanel1Layout.createParallelGroup(3).add(this.jLabel1).add(this.campoNumero, -2, -1, -2)).addPreferredGap(1).add(jPanel1Layout.createParallelGroup(3).add(this.jLabel2).add(this.campoNombre, -2, -1, -2)).add(18, 18, 18).add(jPanel1Layout.createParallelGroup(3).add(this.jLabel3).add(this.comboVentas, -2, -1, -2)).add(18, 18, 18).add(jPanel1Layout.createParallelGroup(3).add(this.jLabel4).add(this.comboCompras, -2, -1, -2)).add(18, 18, 18).add(jPanel1Layout.createParallelGroup(3).add(this.jButton1).add(this.jButton2)).addContainerGap(-1, 32767)));
/*     */ 
/* 209 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 210 */     getContentPane().setLayout(layout);
/* 211 */     layout.setHorizontalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(this.jPanel1, -1, -1, 32767).addContainerGap()));
/*     */ 
/* 218 */     layout.setVerticalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(this.jPanel1, -2, -1, -2).addContainerGap(-1, 32767)));
/*     */ 
/* 226 */     pack();
/*     */   }
/*     */ 
/*     */   private void jButton2ActionPerformed(ActionEvent evt) {
/* 230 */     dispose();
/*     */   }
/*     */ 
/*     */   private void jButton1ActionPerformed(ActionEvent evt) {
/* 234 */     this.objeto = new FPObject();
/* 235 */     this.objeto.setId(Integer.parseInt(this.campoNumero.getText()));
/* 236 */     this.objeto.setNombre(this.campoNombre.getText());
/* 237 */     SubcuentaObject subcuenta = (SubcuentaObject)this.comboVentas.getSelectedItem();
/* 238 */     this.objeto.setSubcuenta1(subcuenta.getCodigo().intValue());
/* 239 */     subcuenta = (SubcuentaObject)this.comboCompras.getSelectedItem();
/* 240 */     this.objeto.setSubcuenta2(subcuenta.getCodigo().intValue());
/* 241 */     dispose();
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.gui.FamiliasJDialog
 * JD-Core Version:    0.6.2
 */