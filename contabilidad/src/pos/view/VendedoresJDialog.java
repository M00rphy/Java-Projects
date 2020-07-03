/*     */ package pos.view;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import java.awt.Container;
/*     */ import java.awt.Frame;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.ArrayList;
import java.util.List;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.DefaultListModel;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
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
/*     */ import pos.control.VendedorControl;
/*     */ import pos.model.Vendedor;
/*     */ 
/*     */ public class VendedoresJDialog extends JDialog
/*     */ {
/*     */   private JButton botonAdd;
/*     */   private JButton botonEliminar;
/*     */   private JButton botonModificar;
/*     */   private JTextField campoNombre;
/*     */   private JLabel jLabel1;
/*     */   private JPanel jPanel1;
/*     */   private JScrollPane jScrollPane1;
/*     */   private JList listaMedios;
/*     */ 
/*     */   public VendedoresJDialog(Frame parent, boolean modal)
/*     */   {
/*  35 */     super(parent, modal);
/*  36 */     initComponents();
/*  37 */     crearListaMedios();
/*     */   }
/*     */ 
/*     */   private void crearListaMedios() {
/*  41 */     this.listaMedios.setModel(getModeloLista());
/*  42 */     limpiarForm();
/*     */   }
/*     */ 
/*     */   private DefaultListModel getModeloLista() {
/*  46 */     DefaultListModel modeloLista = new DefaultListModel();
/*  47 */     ArrayList vendedores = new ArrayList();
/*  48 */     VendedorControl vC = new VendedorControl(Inicio.getcAlmacen());
/*  49 */     vendedores = vC.getTodosVendedores();
/*  50 */     for (Vendedor vendedor :(List<Vendedor>) vendedores) {
/*  51 */       modeloLista.addElement(vendedor);
/*     */     }
/*  53 */     return modeloLista;
/*     */   }
/*     */ 
/*     */   private void limpiarForm()
/*     */   {
/*  59 */     this.campoNombre.setText("");
/*     */   }
/*     */ 
/*     */   private void guardar() {
/*  63 */     Vendedor vendedor = null;
/*  64 */     String nombre = this.campoNombre.getText();
/*  65 */     if ((nombre != null) && (!nombre.equals(""))) {
/*  66 */       vendedor = new Vendedor(Integer.valueOf(-1), nombre);
/*  67 */       VendedorControl vC = new VendedorControl(Inicio.getcAlmacen());
/*  68 */       vC.crear(vendedor);
/*  69 */       vC.cerrarRs();
/*  70 */       crearListaMedios();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void modificar() {
/*  75 */     Vendedor vendedor = null;
/*  76 */     String nombre = this.campoNombre.getText();
/*  77 */     Vendedor viejoVendedor = (Vendedor)this.listaMedios.getSelectedValue();
/*  78 */     VendedorControl vC = new VendedorControl(Inicio.getcAlmacen());
/*  79 */     vendedor = new Vendedor(viejoVendedor.getId(), nombre);
/*  80 */     boolean modificado = vC.modificar(vendedor);
/*  81 */     vC.cerrarRs();
/*  82 */     if (modificado) {
/*  83 */       crearListaMedios();
/*     */     }
/*     */     else
/*  86 */       showError("Error al actualizar los datos");
/*     */   }
/*     */ 
/*     */   private void eliminar()
/*     */   {
/*  91 */     Vendedor vendedor = (Vendedor)this.listaMedios.getSelectedValue();
/*  92 */     if (vendedor != null) {
/*  93 */       VendedorControl vC = new VendedorControl(Inicio.getcAlmacen());
/*  94 */       boolean borrado = vC.borrar(vendedor);
/*  95 */       if (borrado) {
/*  96 */         crearListaMedios();
/*     */       }
/*     */       else
/*  99 */         showError("Error al eliminar elemento");
/*     */     }
/*     */   }
/*     */ 
/*     */   private void colocarDatos(Vendedor vendedor)
/*     */   {
/* 105 */     this.campoNombre.setText(vendedor.getNombre());
/*     */   }
/*     */ 
/*     */   private void showError(String error) {
/* 109 */     JOptionPane.showMessageDialog(Inicio.frame, error, "Error", 0);
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/* 122 */     this.jPanel1 = new JPanel();
/* 123 */     this.botonAdd = new JButton();
/* 124 */     this.botonEliminar = new JButton();
/* 125 */     this.botonModificar = new JButton();
/* 126 */     this.jScrollPane1 = new JScrollPane();
/* 127 */     this.listaMedios = new JList();
/* 128 */     this.jLabel1 = new JLabel();
/* 129 */     this.campoNombre = new JTextField();
/*     */ 
/* 131 */     setDefaultCloseOperation(2);
/* 132 */     ResourceBundle bundle = ResourceBundle.getBundle("internationalization/Mensajes");
/* 133 */     setTitle(bundle.getString("vendedores"));
/*     */ 
/* 135 */     this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/*     */ 
/* 137 */     this.botonAdd.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/edit_add.png")));
/* 138 */     this.botonAdd.setToolTipText("Añadir");
/* 139 */     this.botonAdd.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 141 */         VendedoresJDialog.this.botonAddActionPerformed(evt);
/*     */       }
/*     */     });
/* 145 */     this.botonEliminar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/edit_remove.png")));
/* 146 */     this.botonEliminar.setToolTipText("Eliminar");
/* 147 */     this.botonEliminar.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 149 */         VendedoresJDialog.this.botonEliminarActionPerformed(evt);
/*     */       }
/*     */     });
/* 153 */     this.botonModificar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/apply.png")));
/* 154 */     this.botonModificar.setToolTipText("Modificar");
/* 155 */     this.botonModificar.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 157 */         VendedoresJDialog.this.botonModificarActionPerformed(evt);
/*     */       }
/*     */     });
/* 161 */     this.listaMedios.setSelectionMode(0);
/* 162 */     this.listaMedios.addListSelectionListener(new ListSelectionListener() {
/*     */       public void valueChanged(ListSelectionEvent evt) {
/* 164 */         VendedoresJDialog.this.listaMediosValueChanged(evt);
/*     */       }
/*     */     });
/* 167 */     this.jScrollPane1.setViewportView(this.listaMedios);
/*     */ 
/* 169 */     this.jLabel1.setText(bundle.getString("nombre"));
/*     */ 
/* 171 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 172 */     this.jPanel1.setLayout(jPanel1Layout);
/* 173 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().add(this.botonAdd).add(18, 18, 18).add(this.botonEliminar).add(18, 18, 18).add(this.botonModificar)).add(jPanel1Layout.createSequentialGroup().add(this.jScrollPane1, -2, 192, -2).add(18, 18, 18).add(jPanel1Layout.createParallelGroup(1).add(this.campoNombre, -2, 221, -2).add(this.jLabel1)))).add(138, 138, 138)));
/*     */ 
/* 192 */     jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().add(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().add(36, 36, 36).add(this.jLabel1).addPreferredGap(1).add(this.campoNombre, -2, -1, -2)).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(this.jScrollPane1, -1, 302, 32767))).addPreferredGap(0).add(jPanel1Layout.createParallelGroup(3).add(this.botonAdd).add(this.botonEliminar).add(this.botonModificar)).add(11, 11, 11)));
/*     */ 
/* 212 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 213 */     getContentPane().setLayout(layout);
/* 214 */     layout.setHorizontalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(this.jPanel1, -2, 454, -2).addContainerGap(-1, 32767)));
/*     */ 
/* 221 */     layout.setVerticalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(this.jPanel1, -2, -1, -2).addContainerGap(-1, 32767)));
/*     */ 
/* 229 */     pack();
/*     */   }
/*     */ 
/*     */   private void botonAddActionPerformed(ActionEvent evt) {
/* 233 */     guardar();
/*     */   }
/*     */ 
/*     */   private void botonEliminarActionPerformed(ActionEvent evt) {
/* 237 */     eliminar();
/*     */   }
/*     */ 
/*     */   private void botonModificarActionPerformed(ActionEvent evt) {
/* 241 */     modificar();
/*     */   }
/*     */ 
/*     */   private void listaMediosValueChanged(ListSelectionEvent evt)
/*     */   {
/* 246 */     if ((!evt.getValueIsAdjusting()) && (!this.listaMedios.isSelectionEmpty())) {
/* 247 */       Vendedor vendedor = (Vendedor)this.listaMedios.getSelectedValue();
/* 248 */       colocarDatos(vendedor);
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.view.VendedoresJDialog
 * JD-Core Version:    0.6.2
 */