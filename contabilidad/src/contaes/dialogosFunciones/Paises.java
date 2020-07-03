/*     */ package contaes.dialogosFunciones;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.MarcoInicio;
/*     */ import contaes.manejoDatos.ManejoPaises;
/*     */ import contaes.manejoDatos.TipoPais;
/*     */ import java.awt.Container;
/*     */ import java.awt.Frame;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.WindowAdapter;
/*     */ import java.awt.event.WindowEvent;
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
/*     */ 
/*     */ public class Paises extends JDialog
/*     */ {
/*     */   private ManejoPaises mP;
/*  28 */   private boolean cambios = false;
/*     */   private JButton botonAdd;
/*     */   private JButton botonEliminar;
/*     */   private JButton botonModificar;
/*     */   private JTextField campoNombre;
/*     */   private JComboBox comboSituacion;
/*     */   private JLabel jLabel1;
/*     */   private JLabel jLabel2;
/*     */   private JPanel jPanel1;
/*     */   private JScrollPane jScrollPane1;
/*     */   private JList listaMedios;
/*     */ 
/*     */   public Paises(Frame parent, boolean modal)
/*     */   {
/*  32 */     super(parent, modal);
/*  33 */     initComponents();
/*  34 */     this.mP = new ManejoPaises(Inicio.getCGeneral());
/*  35 */     crearListaMedios();
/*     */   }
/*     */ 
/*     */   private void crearListaMedios() {
/*  39 */     this.listaMedios.setModel(getModeloLista());
/*  40 */     limpiarForm();
/*     */   }
/*     */ 
/*     */   private DefaultListModel getModeloLista() {
/*  44 */     DefaultListModel modeloLista = new DefaultListModel();
/*  45 */     ArrayList paises = new ArrayList();
/*  46 */     paises = this.mP.getTodosPaises();
/*  47 */     this.mP.cerrarRs();
/*  48 */     for (TipoPais tipoPais :(List<TipoPais>)  paises) {
/*  49 */       modeloLista.addElement(tipoPais);
/*     */     }
/*  51 */     return modeloLista;
/*     */   }
/*     */ 
/*     */   private void limpiarForm()
/*     */   {
/*  57 */     this.campoNombre.setText("");
/*  58 */     this.comboSituacion.setSelectedIndex(0);
/*     */   }
/*     */ 
/*     */   private void guardar() {
/*  62 */     String nombre = this.campoNombre.getText();
/*  63 */     if ((nombre != null) && (!nombre.equals(""))) {
/*  64 */       int situacion = this.comboSituacion.getSelectedIndex();
/*  65 */       TipoPais pais = new TipoPais(-1, nombre, situacion);
/*  66 */       this.mP.crear(pais);
/*  67 */       crearListaMedios();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void modificar() {
/*  72 */     TipoPais oldPais = (TipoPais)this.listaMedios.getSelectedValue();
/*  73 */     String nombre = this.campoNombre.getText();
/*  74 */     if ((oldPais != null) && (!nombre.equals(""))) {
/*  75 */       int situacion = this.comboSituacion.getSelectedIndex();
/*  76 */       TipoPais pais = new TipoPais(oldPais.getId(), nombre, situacion);
/*  77 */       boolean modificado = this.mP.modificar(pais);
/*  78 */       if (modificado)
/*  79 */         crearListaMedios();
/*     */       else
/*  81 */         showError("Error al actualizar los datos");
/*     */     }
/*     */   }
/*     */ 
/*     */   private void eliminar()
/*     */   {
/*  87 */     TipoPais pais = (TipoPais)this.listaMedios.getSelectedValue();
/*  88 */     if (pais != null) {
/*  89 */       boolean borrado = this.mP.borrar(pais);
/*  90 */       if (borrado) {
/*  91 */         crearListaMedios();
/*     */       }
/*     */       else
/*  94 */         showError("Error al eliminar elemento");
/*     */     }
/*     */   }
/*     */ 
/*     */   private void colocarDatos(TipoPais pais)
/*     */   {
/* 100 */     this.campoNombre.setText(pais.getNombre());
/* 101 */     this.comboSituacion.setSelectedIndex(pais.getSituacion());
/*     */   }
/*     */ 
/*     */   private void showError(String error) {
/* 105 */     JOptionPane.showMessageDialog(Inicio.frame, error, "Error", 0);
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/* 118 */     this.jPanel1 = new JPanel();
/* 119 */     this.botonAdd = new JButton();
/* 120 */     this.botonEliminar = new JButton();
/* 121 */     this.botonModificar = new JButton();
/* 122 */     this.jScrollPane1 = new JScrollPane();
/* 123 */     this.listaMedios = new JList();
/* 124 */     this.jLabel1 = new JLabel();
/* 125 */     this.campoNombre = new JTextField();
/* 126 */     this.jLabel2 = new JLabel();
/* 127 */     this.comboSituacion = new JComboBox();
/*     */ 
/* 129 */     setDefaultCloseOperation(2);
/* 130 */     ResourceBundle bundle = ResourceBundle.getBundle("internationalization/Mensajes");
/* 131 */     setTitle(bundle.getString("paises"));
/* 132 */     addWindowListener(new WindowAdapter() {
/*     */       public void windowClosing(WindowEvent evt) {
/* 134 */         Paises.this.formWindowClosing(evt);
/*     */       }
/*     */     });
/* 138 */     this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/*     */ 
/* 140 */     this.botonAdd.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/edit_add.png")));
/* 141 */     this.botonAdd.setToolTipText("Añadir");
/* 142 */     this.botonAdd.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 144 */         Paises.this.botonAddActionPerformed(evt);
/*     */       }
/*     */     });
/* 148 */     this.botonEliminar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/edit_remove.png")));
/* 149 */     this.botonEliminar.setToolTipText("Eliminar");
/* 150 */     this.botonEliminar.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 152 */         Paises.this.botonEliminarActionPerformed(evt);
/*     */       }
/*     */     });
/* 156 */     this.botonModificar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/apply.png")));
/* 157 */     this.botonModificar.setToolTipText("Modificar");
/* 158 */     this.botonModificar.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 160 */         Paises.this.botonModificarActionPerformed(evt);
/*     */       }
/*     */     });
/* 164 */     this.listaMedios.setSelectionMode(0);
/* 165 */     this.listaMedios.addListSelectionListener(new ListSelectionListener() {
/*     */       public void valueChanged(ListSelectionEvent evt) {
/* 167 */         Paises.this.listaMediosValueChanged(evt);
/*     */       }
/*     */     });
/* 170 */     this.jScrollPane1.setViewportView(this.listaMedios);
/*     */ 
/* 172 */     this.jLabel1.setText(bundle.getString("nombre"));
/*     */ 
/* 174 */     this.jLabel2.setText(bundle.getString("situacion"));
/*     */ 
/* 176 */     this.comboSituacion.setModel(new DefaultComboBoxModel(new String[] { "España", "Comunitario", "No comunitario" }));
/*     */ 
/* 178 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 179 */     this.jPanel1.setLayout(jPanel1Layout);
/* 180 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().add(this.botonAdd).add(18, 18, 18).add(this.botonEliminar).add(18, 18, 18).add(this.botonModificar)).add(jPanel1Layout.createSequentialGroup().add(this.jScrollPane1, -2, 192, -2).add(18, 18, 18).add(jPanel1Layout.createParallelGroup(1, false).add(this.jLabel2).add(this.campoNombre, -1, 221, 32767).add(this.jLabel1).add(this.comboSituacion, 0, -1, 32767)))).add(138, 138, 138)));
/*     */ 
/* 201 */     jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().add(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().add(36, 36, 36).add(this.jLabel1).addPreferredGap(1).add(this.campoNombre, -2, -1, -2).add(16, 16, 16).add(this.jLabel2).addPreferredGap(0).add(this.comboSituacion, -2, -1, -2)).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(this.jScrollPane1, -1, 302, 32767))).addPreferredGap(0).add(jPanel1Layout.createParallelGroup(3).add(this.botonAdd).add(this.botonEliminar).add(this.botonModificar)).add(11, 11, 11)));
/*     */ 
/* 225 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 226 */     getContentPane().setLayout(layout);
/* 227 */     layout.setHorizontalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(this.jPanel1, -2, 454, -2).addContainerGap(-1, 32767)));
/*     */ 
/* 234 */     layout.setVerticalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(this.jPanel1, -2, -1, -2).addContainerGap(-1, 32767)));
/*     */ 
/* 242 */     pack();
/*     */   }
/*     */ 
/*     */   private void botonAddActionPerformed(ActionEvent evt) {
/* 246 */     guardar();
/* 247 */     this.cambios = true;
/*     */   }
/*     */ 
/*     */   private void botonEliminarActionPerformed(ActionEvent evt) {
/* 251 */     eliminar();
/* 252 */     this.cambios = true;
/*     */   }
/*     */ 
/*     */   private void botonModificarActionPerformed(ActionEvent evt) {
/* 256 */     modificar();
/* 257 */     this.cambios = true;
/*     */   }
/*     */ 
/*     */   private void listaMediosValueChanged(ListSelectionEvent evt)
/*     */   {
/* 262 */     if ((!evt.getValueIsAdjusting()) && (!this.listaMedios.isSelectionEmpty())) {
/* 263 */       TipoPais pais = (TipoPais)this.listaMedios.getSelectedValue();
/* 264 */       colocarDatos(pais);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void formWindowClosing(WindowEvent evt) {
/* 269 */     if (this.cambios) {
/* 270 */       Inicio.frame.renovarModeloPaises();
/*     */     }
/* 272 */     this.mP.cerrarRs();
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.dialogosFunciones.Paises
 * JD-Core Version:    0.6.2
 */