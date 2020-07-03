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
/*     */ import java.awt.event.ItemEvent;
/*     */ import java.awt.event.ItemListener;
/*     */ import java.util.ArrayList;
import java.util.List;
/*     */ import javax.swing.DefaultComboBoxModel;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JTextField;
/*     */ import org.jdesktop.layout.GroupLayout;
/*     */ import org.jdesktop.layout.GroupLayout.ParallelGroup;
/*     */ import org.jdesktop.layout.GroupLayout.SequentialGroup;
/*     */ 
/*     */ public class ProveedoresJDialog extends JDialog
/*     */ {
/*  31 */   private DefaultComboBoxModel modeloComboCuentas = new DefaultComboBoxModel();
/*     */   private FPObject objeto;
/*     */   private JTextField campoNombre;
/*     */   private JTextField campoNumero;
/*     */   private JComboBox comboSubcuentas;
/*     */   private JButton jButton1;
/*     */   private JButton jButton2;
/*     */   private JLabel jLabel1;
/*     */   private JLabel jLabel2;
/*     */   private JLabel jLabel3;
/*     */ 
/*     */   public ProveedoresJDialog(Frame parent, boolean modal, UserObject usuario, FPObject objeto)
/*     */   {
/*  36 */     super(parent, modal);
/*     */ 
/*  38 */     initComponents();
/*  39 */     setModeloCombos(usuario);
/*  40 */     cargarObjeto(usuario, objeto);
/*     */   }
/*     */ 
/*     */   public static FPObject obtenerElemento(Frame parent, boolean modal, UserObject usuario, FPObject objeto)
/*     */   {
/*  45 */     ProveedoresJDialog dlg = new ProveedoresJDialog(parent, modal, usuario, objeto);
/*  46 */     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/*  47 */     Dimension frameSize = dlg.getSize();
/*  48 */     if (frameSize.height > screenSize.height) {
/*  49 */       frameSize.height = screenSize.height;
/*     */     }
/*  51 */     if (frameSize.width > screenSize.width) {
/*  52 */       frameSize.width = screenSize.width;
/*     */     }
/*  54 */     dlg.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
/*     */ 
/*  56 */     dlg.pack();
/*  57 */     dlg.setVisible(true);
/*  58 */     return dlg.obtenerObjetoFinal();
/*     */   }
/*     */ 
/*     */   private void setModeloCombos(UserObject usuario) {
/*  62 */     ManejadorSubcuentas mS = new ManejadorSubcuentas(usuario);
/*     */ 
/*  64 */     ArrayList listaSubcVentas = new ArrayList();
/*  65 */     listaSubcVentas = mS.listaSubcuentas(40000000, 40999999);
/*  66 */     for (SubcuentaObject subcuentaObject :(List<SubcuentaObject>) listaSubcVentas) {
/*  67 */       this.modeloComboCuentas.addElement(subcuentaObject);
/*     */     }
/*  69 */     this.comboSubcuentas.setModel(this.modeloComboCuentas);
/*     */   }
/*     */ 
/*     */   private void cargarObjeto(UserObject usuario, FPObject objetoOld) {
/*  73 */     if (objetoOld != null) {
/*  74 */       this.campoNumero.setText(Integer.toString(objetoOld.getId()));
/*  75 */       this.campoNombre.setText(objetoOld.getNombre());
/*  76 */       ManejadorSubcuentas mS = new ManejadorSubcuentas(usuario);
/*  77 */       SubcuentaObject subcuenta = mS.getSubcuenta(objetoOld.getSubcuenta1());
/*  78 */       if (subcuenta != null)
/*  79 */         this.comboSubcuentas.setSelectedItem(subcuenta);
/*     */     }
/*     */   }
/*     */ 
/*     */   private FPObject obtenerObjetoFinal()
/*     */   {
/*  85 */     return this.objeto;
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/*  97 */     this.jLabel1 = new JLabel();
/*  98 */     this.campoNumero = new JTextField();
/*  99 */     this.jLabel2 = new JLabel();
/* 100 */     this.campoNombre = new JTextField();
/* 101 */     this.jLabel3 = new JLabel();
/* 102 */     this.comboSubcuentas = new JComboBox();
/* 103 */     this.jButton1 = new JButton();
/* 104 */     this.jButton2 = new JButton();
/*     */ 
/* 106 */     setDefaultCloseOperation(2);
/* 107 */     setTitle("Proveedores");
/*     */ 
/* 109 */     this.jLabel1.setText("Número");
/*     */ 
/* 111 */     this.campoNumero.setDocument(new DocNumPositivos());
/*     */ 
/* 113 */     this.jLabel2.setText("Nombre");
/*     */ 
/* 115 */     this.jLabel3.setText("Subcuenta");
/*     */ 
/* 117 */     this.comboSubcuentas.addItemListener(new ItemListener() {
/*     */       public void itemStateChanged(ItemEvent evt) {
/* 119 */         ProveedoresJDialog.this.comboSubcuentasItemStateChanged(evt);
/*     */       }
/*     */     });
/* 123 */     this.jButton1.setIcon(new ImageIcon(getClass().getResource("/almacen2/iconos/apply.png")));
/* 124 */     this.jButton1.setText("Guardar");
/* 125 */     this.jButton1.setHorizontalTextPosition(2);
/* 126 */     this.jButton1.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 128 */         ProveedoresJDialog.this.jButton1ActionPerformed(evt);
/*     */       }
/*     */     });
/* 132 */     this.jButton2.setIcon(new ImageIcon(getClass().getResource("/almacen2/iconos/cancel.png")));
/* 133 */     this.jButton2.setText("Cancelar");
/* 134 */     this.jButton2.setHorizontalTextPosition(2);
/* 135 */     this.jButton2.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 137 */         ProveedoresJDialog.this.jButton2ActionPerformed(evt);
/*     */       }
/*     */     });
/* 141 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 142 */     getContentPane().setLayout(layout);
/* 143 */     layout.setHorizontalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(layout.createParallelGroup(1).add(layout.createSequentialGroup().add(this.jButton1).add(18, 18, 18).add(this.jButton2)).add(layout.createSequentialGroup().add(this.jLabel1).add(18, 18, 18).add(this.campoNumero, -2, 109, -2)).add(layout.createSequentialGroup().add(this.jLabel3).add(18, 18, 18).add(this.comboSubcuentas, 0, 277, 32767)).add(layout.createSequentialGroup().add(this.jLabel2).add(31, 31, 31).add(this.campoNombre, -1, 279, 32767))).addContainerGap()));
/*     */ 
/* 166 */     layout.setVerticalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(layout.createParallelGroup(3).add(this.jLabel1).add(this.campoNumero, -2, -1, -2)).addPreferredGap(1).add(layout.createParallelGroup(3).add(this.jLabel3).add(this.comboSubcuentas, -2, -1, -2)).addPreferredGap(1).add(layout.createParallelGroup(3).add(this.jLabel2).add(this.campoNombre, -2, -1, -2)).add(18, 18, 18).add(layout.createParallelGroup(3).add(this.jButton1).add(this.jButton2)).addContainerGap(16, 32767)));
/*     */ 
/* 188 */     pack();
/*     */   }
/*     */ 
/*     */   private void jButton2ActionPerformed(ActionEvent evt)
/*     */   {
/* 193 */     dispose();
/*     */   }
/*     */ 
/*     */   private void jButton1ActionPerformed(ActionEvent evt) {
/* 197 */     this.objeto = new FPObject();
/* 198 */     this.objeto.setId(Integer.parseInt(this.campoNumero.getText()));
/* 199 */     this.objeto.setNombre(this.campoNombre.getText());
/* 200 */     SubcuentaObject subcuenta = (SubcuentaObject)this.comboSubcuentas.getSelectedItem();
/* 201 */     this.objeto.setSubcuenta1(subcuenta.getCodigo().intValue());
/* 202 */     this.objeto.setSubcuenta2(-1);
/* 203 */     dispose();
/*     */   }
/*     */ 
/*     */   private void comboSubcuentasItemStateChanged(ItemEvent evt)
/*     */   {
/* 208 */     SubcuentaObject subc = (SubcuentaObject)this.comboSubcuentas.getSelectedItem();
/* 209 */     if (subc != null)
/* 210 */       this.campoNombre.setText(subc.getNombre());
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.gui.ProveedoresJDialog
 * JD-Core Version:    0.6.2
 */