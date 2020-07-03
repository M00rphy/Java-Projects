/*     */ package pos.view;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.Puente;
/*     */ import contaes.manejoDatos.ManejoSubcuentas;
/*     */ import contaes.manejoDatos.TipoSubcuenta;
/*     */ import java.awt.Container;
/*     */ import java.awt.Frame;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.ArrayList;
import java.util.List;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.DefaultComboBoxModel;
/*     */ import javax.swing.DefaultListModel;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JList;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.event.ListSelectionEvent;
/*     */ import javax.swing.event.ListSelectionListener;
/*     */ import org.jdesktop.layout.GroupLayout;
/*     */ import org.jdesktop.layout.GroupLayout.ParallelGroup;
/*     */ import org.jdesktop.layout.GroupLayout.SequentialGroup;
/*     */ import pos.control.MedioPagoControl;
/*     */ import pos.model.MedioPago;
/*     */ 
/*     */ public class MediosPagoJDialog extends JDialog
/*     */ {
/*     */   private JButton botonAdd;
/*     */   private JButton botonEliminar;
/*     */   private JButton botonModificar;
/*     */   private JTextField campoComision;
/*     */   private JTextField campoNombre;
/*     */   private JComboBox comboCobro;
/*     */   private JComboBox comboComision;
/*     */   private JLabel jLabel1;
/*     */   private JLabel jLabel2;
/*     */   private JLabel jLabel3;
/*     */   private JLabel jLabel4;
/*     */   private JLabel jLabel5;
/*     */   private JLabel jLabel6;
/*     */   private JPanel jPanel1;
/*     */   private JScrollPane jScrollPane1;
/*     */   private JList listaMedios;
/*     */ 
/*     */   public MediosPagoJDialog(Frame parent, boolean modal)
/*     */   {
/*  33 */     super(parent, modal);
/*  34 */     initComponents();
/*  35 */     llenarCombos();
/*  36 */     crearListaMedios();
/*     */   }
/*     */ 
/*     */   private void crearListaMedios() {
/*  40 */     this.listaMedios.setModel(getModeloLista());
/*  41 */     limpiarForm();
/*     */   }
/*     */ 
/*     */   private DefaultListModel getModeloLista() {
/*  45 */     DefaultListModel modeloLista = new DefaultListModel();
/*  46 */     ArrayList medios = new ArrayList();
/*  47 */     MedioPagoControl mPC = new MedioPagoControl(Inicio.getcAlmacen());
/*  48 */     medios = mPC.getTodosMediosPago();
/*  49 */     mPC.cerrarRs();
/*  50 */     for (MedioPago medioPago :(List<MedioPago>)  medios) {
/*  51 */       modeloLista.addElement(medioPago);
/*     */     }
/*  53 */     return modeloLista;
/*     */   }
/*     */ 
/*     */   private void llenarCombos()
/*     */   {
/*  59 */     DefaultComboBoxModel modeloCombo1 = new DefaultComboBoxModel();
/*  60 */     DefaultComboBoxModel modeloCombo2 = new DefaultComboBoxModel();
/*     */ 
/*  62 */     ArrayList tiposSubcuenta = new ArrayList();
/*  63 */     ManejoSubcuentas mS = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  64 */     tiposSubcuenta = mS.listaSubcuentas(57000000, 57999999);
/*  65 */     for (TipoSubcuenta subcuenta :(List<TipoSubcuenta>)  tiposSubcuenta) {
/*  66 */       modeloCombo1.addElement(subcuenta);
/*     */     }
/*  68 */     this.comboCobro.setModel(modeloCombo1);
/*     */ 
/*  70 */     tiposSubcuenta = mS.listaSubcuentas(66000000, 66999999);
/*  71 */     tiposSubcuenta.addAll(mS.listaSubcuentas(70600000, 70699999));
/*  72 */     for (TipoSubcuenta subcuenta :(List<TipoSubcuenta>)  tiposSubcuenta) {
/*  73 */       modeloCombo2.addElement(subcuenta);
/*     */     }
/*  75 */     this.comboComision.setModel(modeloCombo2);
/*     */   }
/*     */ 
/*     */   private int indiceCombo(JComboBox comboBox, int subcuenta) {
/*  79 */     int indice = 0;
/*  80 */     TipoSubcuenta sub = new TipoSubcuenta();
/*  81 */     for (int x = 0; x < comboBox.getItemCount(); x++) {
/*  82 */       sub = (TipoSubcuenta)comboBox.getItemAt(x);
/*  83 */       if (sub.getCodigo() == subcuenta) {
/*  84 */         indice = x;
/*  85 */         break;
/*     */       }
/*     */     }
/*  88 */     return indice;
/*     */   }
/*     */ 
/*     */   private void limpiarForm() {
/*  92 */     this.campoNombre.setText("");
/*  93 */     this.campoComision.setText("");
/*  94 */     if (this.comboCobro.getItemCount() > 0) {
/*  95 */       this.comboCobro.setSelectedIndex(0);
/*     */     }
/*  97 */     if (this.comboComision.getItemCount() > 0)
/*  98 */       this.comboComision.setSelectedIndex(0);
/*     */   }
/*     */ 
/*     */   private void guardar()
/*     */   {
/* 103 */     MedioPago medioPago = null;
/* 104 */     String nombre = this.campoNombre.getText();
/* 105 */     if ((nombre != null) && (!nombre.equals(""))) {
/* 106 */       double comsion = 0.0D;
/*     */       try {
/* 108 */         comsion = Double.parseDouble(this.campoComision.getText());
/*     */       } catch (NumberFormatException numberFormatException) {
/* 110 */         numberFormatException.printStackTrace();
/*     */       }
/* 112 */       TipoSubcuenta subCobro = (TipoSubcuenta)this.comboCobro.getSelectedItem();
/* 113 */       TipoSubcuenta subComision = (TipoSubcuenta)this.comboComision.getSelectedItem();
/* 114 */       medioPago = new MedioPago(Integer.valueOf(-1), nombre, subCobro.getCodigo(), comsion, subComision.getCodigo());
/* 115 */       MedioPagoControl mPC = new MedioPagoControl(Inicio.getcAlmacen());
/* 116 */       int id = mPC.crear(medioPago);
/* 117 */       mPC.cerrarRs();
/* 118 */       crearListaMedios();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void modificar() {
/* 123 */     MedioPago nuevoMedioPago = null;
/* 124 */     String nombre = this.campoNombre.getText();
/* 125 */     double comision = 0.0D;
/*     */     try {
/* 127 */       comision = Double.parseDouble(this.campoComision.getText());
/*     */     } catch (NumberFormatException numberFormatException) {
/* 129 */       numberFormatException.printStackTrace();
/*     */     }
/* 131 */     TipoSubcuenta subCobro = (TipoSubcuenta)this.comboCobro.getSelectedItem();
/* 132 */     TipoSubcuenta subComision = (TipoSubcuenta)this.comboComision.getSelectedItem();
/* 133 */     MedioPago viejoMedioPago = (MedioPago)this.listaMedios.getSelectedValue();
/* 134 */     nuevoMedioPago = new MedioPago(viejoMedioPago.getId(), nombre, subCobro.getCodigo(), comision, subComision.getCodigo());
/* 135 */     MedioPagoControl mPC = new MedioPagoControl(Inicio.getcAlmacen());
/* 136 */     boolean modificado = mPC.modificar(nuevoMedioPago);
/* 137 */     mPC.cerrarRs();
/* 138 */     if (modificado) {
/* 139 */       crearListaMedios();
/*     */     }
/*     */     else
/* 142 */       showError("Error al actualizar los datos");
/*     */   }
/*     */ 
/*     */   private void eliminar()
/*     */   {
/* 147 */     MedioPago medioPago = (MedioPago)this.listaMedios.getSelectedValue();
/* 148 */     if ((medioPago != null) && (medioPago.getId().intValue() != -1)) {
/* 149 */       MedioPagoControl mPC = new MedioPagoControl(Inicio.getcAlmacen());
/* 150 */       boolean borrado = mPC.borrar(medioPago);
/* 151 */       mPC.cerrarRs();
/* 152 */       if (borrado) {
/* 153 */         crearListaMedios();
/*     */       }
/*     */       else
/* 156 */         showError("Error al eliminar elemento");
/*     */     }
/*     */   }
/*     */ 
/*     */   private void colocarDatos(MedioPago medio)
/*     */   {
/* 163 */     this.campoNombre.setText(medio.getNombre());
/* 164 */     this.campoComision.setText(Double.toString(medio.getComision()));
/* 165 */     this.comboCobro.setSelectedIndex(indiceCombo(this.comboCobro, medio.getCuentaCobro()));
/* 166 */     this.comboComision.setSelectedIndex(indiceCombo(this.comboComision, medio.getCuentaComision()));
/*     */   }
/*     */ 
/*     */   private void showError(String error) {
/* 170 */     JOptionPane.showMessageDialog(Inicio.frame, error, "Error", 0);
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/* 183 */     this.jPanel1 = new JPanel();
/* 184 */     this.botonAdd = new JButton();
/* 185 */     this.botonEliminar = new JButton();
/* 186 */     this.botonModificar = new JButton();
/* 187 */     this.jScrollPane1 = new JScrollPane();
/* 188 */     this.listaMedios = new JList();
/* 189 */     this.jLabel1 = new JLabel();
/* 190 */     this.jLabel2 = new JLabel();
/* 191 */     this.jLabel4 = new JLabel();
/* 192 */     this.jLabel5 = new JLabel();
/* 193 */     this.jLabel6 = new JLabel();
/* 194 */     this.comboCobro = new JComboBox();
/* 195 */     this.comboComision = new JComboBox();
/* 196 */     this.campoNombre = new JTextField();
/* 197 */     this.campoComision = new JTextField();
/* 198 */     this.jLabel3 = new JLabel();
/*     */ 
/* 200 */     setDefaultCloseOperation(2);
/* 201 */     ResourceBundle bundle = ResourceBundle.getBundle("internationalization/Mensajes");
/* 202 */     setTitle(bundle.getString("gesmediospago"));
/*     */ 
/* 204 */     this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/*     */ 
/* 206 */     this.botonAdd.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/edit_add.png")));
/* 207 */     this.botonAdd.setToolTipText("Añadir");
/* 208 */     this.botonAdd.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 210 */         MediosPagoJDialog.this.botonAddActionPerformed(evt);
/*     */       }
/*     */     });
/* 214 */     this.botonEliminar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/edit_remove.png")));
/* 215 */     this.botonEliminar.setToolTipText("Eliminar");
/* 216 */     this.botonEliminar.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 218 */         MediosPagoJDialog.this.botonEliminarActionPerformed(evt);
/*     */       }
/*     */     });
/* 222 */     this.botonModificar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/apply.png")));
/* 223 */     this.botonModificar.setToolTipText("Modificar");
/* 224 */     this.botonModificar.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 226 */         MediosPagoJDialog.this.botonModificarActionPerformed(evt);
/*     */       }
/*     */     });
/* 230 */     this.listaMedios.setSelectionMode(0);
/* 231 */     this.listaMedios.addListSelectionListener(new ListSelectionListener() {
/*     */       public void valueChanged(ListSelectionEvent evt) {
/* 233 */         MediosPagoJDialog.this.listaMediosValueChanged(evt);
/*     */       }
/*     */     });
/* 236 */     this.jScrollPane1.setViewportView(this.listaMedios);
/*     */ 
/* 238 */     this.jLabel1.setText(bundle.getString("nombre"));
/*     */ 
/* 240 */     this.jLabel2.setText(bundle.getString("comision"));
/*     */ 
/* 242 */     this.jLabel4.setText(bundle.getString("subcuentas"));
/*     */ 
/* 244 */     this.jLabel5.setText(bundle.getString("cobro"));
/*     */ 
/* 246 */     this.jLabel6.setText(bundle.getString("comision"));
/*     */ 
/* 248 */     this.jLabel3.setText(bundle.getString("bancaria"));
/*     */ 
/* 250 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 251 */     this.jPanel1.setLayout(jPanel1Layout);
/* 252 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().add(this.botonAdd).add(18, 18, 18).add(this.botonEliminar).add(18, 18, 18).add(this.botonModificar).addPreferredGap(0, 376, -2)).add(jPanel1Layout.createSequentialGroup().add(this.jScrollPane1, -2, 192, -2).addPreferredGap(0).add(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().add(20, 20, 20).add(jPanel1Layout.createParallelGroup(2).add(this.jLabel5).add(this.jLabel4).add(this.jLabel6).add(this.jLabel2))).add(jPanel1Layout.createSequentialGroup().add(18, 18, 18).add(this.jLabel1))).add(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().addPreferredGap(0).add(this.jLabel3).add(18, 18, 18).add(this.campoComision, -2, 93, -2)).add(jPanel1Layout.createSequentialGroup().add(jPanel1Layout.createParallelGroup(2, false).add(1, jPanel1Layout.createSequentialGroup().add(18, 18, 18).add(this.comboComision, 0, -1, 32767)).add(1, jPanel1Layout.createSequentialGroup().add(20, 20, 20).add(jPanel1Layout.createParallelGroup(2, false).add(1, this.comboCobro, 0, -1, 32767).add(1, this.campoNombre, -1, 204, 32767)))).addPreferredGap(0, -1, 32767))))).add(63, 63, 63)));
/*     */ 
/* 297 */     jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().add(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().add(52, 52, 52).add(jPanel1Layout.createParallelGroup(3).add(this.campoNombre, -2, -1, -2).add(this.jLabel1)).add(22, 22, 22).add(this.jLabel4).addPreferredGap(0).add(jPanel1Layout.createParallelGroup(3).add(this.jLabel5).add(this.comboCobro, -2, -1, -2)).addPreferredGap(1).add(jPanel1Layout.createParallelGroup(3).add(this.jLabel6).add(this.comboComision, -2, -1, -2)).add(18, 18, 18).add(jPanel1Layout.createParallelGroup(3).add(this.jLabel2).add(this.jLabel3).add(this.campoComision, -2, -1, -2))).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(this.jScrollPane1, -1, 302, 32767))).addPreferredGap(0).add(jPanel1Layout.createParallelGroup(3).add(this.botonAdd).add(this.botonEliminar).add(this.botonModificar)).add(11, 11, 11)));
/*     */ 
/* 332 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 333 */     getContentPane().setLayout(layout);
/* 334 */     layout.setHorizontalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(this.jPanel1, -1, -1, 32767).addContainerGap()));
/*     */ 
/* 341 */     layout.setVerticalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(this.jPanel1, -2, -1, -2).addContainerGap(-1, 32767)));
/*     */ 
/* 349 */     pack();
/*     */   }
/*     */ 
/*     */   private void botonAddActionPerformed(ActionEvent evt) {
/* 353 */     guardar();
/*     */   }
/*     */ 
/*     */   private void botonEliminarActionPerformed(ActionEvent evt) {
/* 357 */     eliminar();
/*     */   }
/*     */ 
/*     */   private void botonModificarActionPerformed(ActionEvent evt) {
/* 361 */     modificar();
/*     */   }
/*     */ 
/*     */   private void listaMediosValueChanged(ListSelectionEvent evt)
/*     */   {
/* 366 */     if ((!evt.getValueIsAdjusting()) && (!this.listaMedios.isSelectionEmpty())) {
/* 367 */       MedioPago medio = (MedioPago)this.listaMedios.getSelectedValue();
/* 368 */       colocarDatos(medio);
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.view.MediosPagoJDialog
 * JD-Core Version:    0.6.2
 */