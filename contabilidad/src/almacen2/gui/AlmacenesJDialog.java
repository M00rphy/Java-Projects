/*     */ package almacen2.gui;
/*     */ 
/*     */ import almacen2.data.AlmacenInterno;
/*     */ import almacen2.data.ManejadorAlmacenInterno;
/*     */ import almacen2.data.MySQLConection;
/*     */ import contaes.Inicio;
/*     */ import contaes.auxiliar.DocDigitos;
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
/*     */ 
/*     */ public class AlmacenesJDialog extends JDialog
/*     */ {
/*     */   private MySQLConection con;
/*     */   private ManejadorAlmacenInterno mAI;
/*     */   private JButton botonAdd;
/*     */   private JButton botonEliminar;
/*     */   private JButton botonModificar;
/*     */   private JTextField campoId;
/*     */   private JTextField campoNombre;
/*     */   private JLabel jLabel1;
/*     */   private JLabel jLabel2;
/*     */   private JPanel jPanel1;
/*     */   private JScrollPane jScrollPane1;
/*     */   private JList listaAlmacenes;
/*     */ 
/*     */   public AlmacenesJDialog(Frame parent, boolean modal)
/*     */   {
/*  34 */     super(parent, modal);
/*  35 */     this.con = new MySQLConection();
/*  36 */     this.mAI = new ManejadorAlmacenInterno(this.con);
/*  37 */     initComponents();
/*  38 */     crearListaAlmacenes();
/*     */   }
/*     */ 
/*     */   private void crearListaAlmacenes() {
/*  42 */     this.listaAlmacenes.setModel(getModeloLista());
/*     */   }
/*     */ 
/*     */   private DefaultListModel getModeloLista() {
/*  46 */     DefaultListModel modeloLista = new DefaultListModel();
/*  47 */     ArrayList almacenes = new ArrayList();
/*  48 */     almacenes = this.mAI.getAlmacenes();
/*  49 */     for (AlmacenInterno almacenInterno :(List<AlmacenInterno>) almacenes) {
/*  50 */       modeloLista.addElement(almacenInterno);
/*     */     }
/*  52 */     return modeloLista;
/*     */   }
/*     */ 
/*     */   private void setNewId()
/*     */   {
/*  57 */     this.campoId.setText(Integer.toString(this.mAI.nuevoRegistro()));
/*     */   }
/*     */ 
/*     */   private void guardarAlmacen() {
/*  61 */     setNewId();
/*  62 */     AlmacenInterno almacen = null;
/*  63 */     int id = -1;
/*  64 */     if (!this.campoId.getText().equals("")) {
/*  65 */       id = Integer.parseInt(this.campoId.getText());
/*     */     }
/*  67 */     String nombre = this.campoNombre.getText();
/*  68 */     if (id != -1) {
/*  69 */       almacen = new AlmacenInterno(Integer.valueOf(id), nombre);
/*  70 */       if (almacen != null) {
/*  71 */         this.mAI.crear(almacen);
/*  72 */         crearListaAlmacenes();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void modificarAlmacen() {
/*  78 */     AlmacenInterno almacen = null;
/*  79 */     AlmacenInterno almacenOld = (AlmacenInterno)this.listaAlmacenes.getSelectedValue();
/*  80 */     String nombre = this.campoNombre.getText();
/*  81 */     almacen = new AlmacenInterno(almacenOld.getId(), nombre);
/*  82 */     this.mAI.modificar(almacen);
/*  83 */     crearListaAlmacenes();
/*     */   }
/*     */ 
/*     */   private void eliminarAlmacen() {
/*  87 */     AlmacenInterno almacen = null;
/*  88 */     almacen = (AlmacenInterno)this.listaAlmacenes.getSelectedValue();
/*  89 */     if ((almacen != null) && (almacen.getId().intValue() != -1)) {
/*  90 */       boolean eliminado = this.mAI.eliminar(almacen);
/*  91 */       if (eliminado) {
/*  92 */         crearListaAlmacenes();
/*     */       }
/*     */       else
/*  95 */         showError("No se ha podido eliminar el almacén.\n Compruebe que no existen entradas\no salidas de productos asociadas a él.");
/*     */     }
/*     */   }
/*     */ 
/*     */   private void colocarDatos(AlmacenInterno almacen)
/*     */   {
/* 102 */     this.campoId.setText(Integer.toString(almacen.getId().intValue()));
/* 103 */     this.campoNombre.setText(almacen.getNombre());
/*     */   }
/*     */ 
/*     */   private void showError(String error) {
/* 107 */     JOptionPane.showMessageDialog(Inicio.frame, error, "Error", 0);
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/* 120 */     this.jPanel1 = new JPanel();
/* 121 */     this.botonAdd = new JButton();
/* 122 */     this.botonEliminar = new JButton();
/* 123 */     this.botonModificar = new JButton();
/* 124 */     this.jScrollPane1 = new JScrollPane();
/* 125 */     this.listaAlmacenes = new JList();
/* 126 */     this.jLabel1 = new JLabel();
/* 127 */     this.jLabel2 = new JLabel();
/* 128 */     this.campoId = new JTextField();
/* 129 */     this.campoNombre = new JTextField();
/*     */ 
/* 131 */     setDefaultCloseOperation(2);
/* 132 */     ResourceBundle bundle = ResourceBundle.getBundle("internationalization/Mensajes");
/* 133 */     setTitle(bundle.getString("almacenes"));
/* 134 */     addWindowListener(new WindowAdapter() {
/*     */       public void windowClosed(WindowEvent evt) {
/* 136 */         AlmacenesJDialog.this.formWindowClosed(evt);
/*     */       }
/*     */     });
/* 140 */     this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/*     */ 
/* 142 */     this.botonAdd.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/edit_add.png")));
/* 143 */     this.botonAdd.setToolTipText("Añadir");
/* 144 */     this.botonAdd.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 146 */         AlmacenesJDialog.this.botonAddActionPerformed(evt);
/*     */       }
/*     */     });
/* 150 */     this.botonEliminar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/edit_remove.png")));
/* 151 */     this.botonEliminar.setToolTipText("Eliminar");
/* 152 */     this.botonEliminar.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 154 */         AlmacenesJDialog.this.botonEliminarActionPerformed(evt);
/*     */       }
/*     */     });
/* 158 */     this.botonModificar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/apply.png")));
/* 159 */     this.botonModificar.setToolTipText("Modificar");
/* 160 */     this.botonModificar.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 162 */         AlmacenesJDialog.this.botonModificarActionPerformed(evt);
/*     */       }
/*     */     });
/* 166 */     this.listaAlmacenes.setSelectionMode(0);
/* 167 */     this.listaAlmacenes.addListSelectionListener(new ListSelectionListener() {
/*     */       public void valueChanged(ListSelectionEvent evt) {
/* 169 */         AlmacenesJDialog.this.listaAlmacenesValueChanged(evt);
/*     */       }
/*     */     });
/* 172 */     this.jScrollPane1.setViewportView(this.listaAlmacenes);
/*     */ 
/* 174 */     this.jLabel1.setText(bundle.getString("id"));
/*     */ 
/* 176 */     this.jLabel2.setText(bundle.getString("nombre"));
/*     */ 
/* 178 */     this.campoId.setDocument(new DocDigitos());
/* 179 */     this.campoId.setEditable(false);
/*     */ 
/* 181 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 182 */     this.jPanel1.setLayout(jPanel1Layout);
/* 183 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(jPanel1Layout.createParallelGroup(1, false).add(jPanel1Layout.createSequentialGroup().add(this.botonAdd).add(18, 18, 18).add(this.botonEliminar).add(18, 18, 18).add(this.botonModificar).addPreferredGap(0, 171, -2)).add(jPanel1Layout.createSequentialGroup().add(jPanel1Layout.createParallelGroup(1).add(this.jLabel1).add(this.jLabel2)).add(18, 18, 18).add(jPanel1Layout.createParallelGroup(1).add(this.campoId, -2, 85, -2).add(this.campoNombre, -2, 205, -2))).add(this.jScrollPane1)).addContainerGap(15, 32767)));
/*     */ 
/* 206 */     jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(jPanel1Layout.createParallelGroup(3).add(this.jLabel1).add(this.campoId, -2, -1, -2)).addPreferredGap(0).add(jPanel1Layout.createParallelGroup(3).add(this.jLabel2).add(this.campoNombre, -2, -1, -2)).add(26, 26, 26).add(this.jScrollPane1, -2, 290, -2).addPreferredGap(0).add(jPanel1Layout.createParallelGroup(3).add(this.botonAdd).add(this.botonEliminar).add(this.botonModificar)).addContainerGap()));
/*     */ 
/* 227 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 228 */     getContentPane().setLayout(layout);
/* 229 */     layout.setHorizontalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(this.jPanel1, -2, -1, -2).addContainerGap(-1, 32767)));
/*     */ 
/* 236 */     layout.setVerticalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(this.jPanel1, -1, -1, 32767).addContainerGap()));
/*     */ 
/* 244 */     pack();
/*     */   }
/*     */ 
/*     */   private void botonAddActionPerformed(ActionEvent evt) {
/* 248 */     guardarAlmacen();
/*     */   }
/*     */ 
/*     */   private void botonEliminarActionPerformed(ActionEvent evt) {
/* 252 */     eliminarAlmacen();
/*     */   }
/*     */ 
/*     */   private void botonModificarActionPerformed(ActionEvent evt) {
/* 256 */     modificarAlmacen();
/*     */   }
/*     */ 
/*     */   private void listaAlmacenesValueChanged(ListSelectionEvent evt)
/*     */   {
/* 261 */     if ((!evt.getValueIsAdjusting()) && (!this.listaAlmacenes.isSelectionEmpty())) {
/* 262 */       AlmacenInterno almacen = (AlmacenInterno)this.listaAlmacenes.getSelectedValue();
/* 263 */       colocarDatos(almacen);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void formWindowClosed(WindowEvent evt)
/*     */   {
/* 269 */     if (this.con != null)
/* 270 */       this.con.cierraConexion();
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.gui.AlmacenesJDialog
 * JD-Core Version:    0.6.2
 */