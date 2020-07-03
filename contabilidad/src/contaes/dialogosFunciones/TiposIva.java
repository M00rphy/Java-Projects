/*     */ package contaes.dialogosFunciones;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.MarcoInicio;
/*     */ import contaes.Puente;
/*     */ import contaes.manejoDatos.ManejoSubcuentas;
/*     */ import contaes.manejoDatos.ManejoTiposIVA;
/*     */ import contaes.manejoDatos.TipoIVA;
/*     */ import contaes.manejoDatos.TipoSubcuenta;
/*     */ import java.awt.Container;
/*     */ import java.awt.Frame;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.WindowAdapter;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.util.ArrayList;
import java.util.List;
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
/*     */ public class TiposIva extends JDialog
/*     */ {
/*  30 */   private boolean cambios = false;
/*     */   private JButton botonAdd;
/*     */   private JButton botonEliminar;
/*     */   private JButton botonModificar;
/*     */   private JTextField campoIVA;
/*     */   private JTextField campoNombre;
/*     */   private JTextField campoRE;
/*     */   private JComboBox comboRepercutido;
/*     */   private JComboBox comboRepercutidoRE;
/*     */   private JComboBox comboSoportado;
/*     */   private JLabel jLabel1;
/*     */   private JLabel jLabel2;
/*     */   private JLabel jLabel3;
/*     */   private JLabel jLabel4;
/*     */   private JLabel jLabel5;
/*     */   private JLabel jLabel6;
/*     */   private JLabel jLabel8;
/*     */   private JPanel jPanel1;
/*     */   private JScrollPane jScrollPane1;
/*     */   private JList listaTipos;
/*     */ 
/*     */   public TiposIva(Frame parent, boolean modal)
/*     */   {
/*  34 */     super(parent, modal);
/*  35 */     initComponents();
/*  36 */     crearListaIVA();
/*  37 */    llenarCombos();
/*     */   }
/*     */ 
/*     */   private void crearListaIVA() {
/*  41 */     this.listaTipos.setModel(getModeloLista());
/*     */   }
/*     */ 
/*     */   private DefaultListModel getModeloLista() {
/*  45 */     DefaultListModel modeloLista = new DefaultListModel();
/*  46 */     ArrayList tiposIVA = new ArrayList();
/*  47 */       ManejoTiposIVA mT = new ManejoTiposIVA(Inicio.getCGeneral());
/*  48 */     tiposIVA = mT.getTiposIVA();
/*  49 */     for (TipoIVA tipo :(List<TipoIVA>) tiposIVA)
/*  50 */       modeloLista.addElement(tipo);
/*  51 */     return modeloLista;
/*     */   }
/*     */ 
/*     */   private void llenarCombos()
/*     */   {
/*  57 */     DefaultComboBoxModel modeloCombo1 = new DefaultComboBoxModel();
/*  58 */     DefaultComboBoxModel modeloCombo2 = new DefaultComboBoxModel();
/*  59 */     DefaultComboBoxModel modeloCombo3 = new DefaultComboBoxModel();
/*  60 */     ArrayList tiposSubcuenta = new ArrayList();
/*  61 */     ManejoSubcuentas mS = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
               tiposSubcuenta = mS.listaSubcuentas(47200000, 47299999);
               tiposSubcuenta.addAll(mS.listaSubcuentas(47700000, 47799999));
int x=0;
/*  64 */     for (TipoSubcuenta subcuenta :(List<TipoSubcuenta>) tiposSubcuenta) {
//    System.out.println(tiposSubcuenta.get(x++));
/*  65 */       modeloCombo1.addElement(subcuenta);
/*  66 */       modeloCombo2.addElement(subcuenta);
/*  67 */       modeloCombo3.addElement(subcuenta);
/*     */     }
/*  69 */     this.comboRepercutido.setModel(modeloCombo1);
/*  70 */     this.comboRepercutidoRE.setModel(modeloCombo2);
/*  71 */     this.comboSoportado.setModel(modeloCombo3);
/*     */   }
/*     */ 
/*     */   private int indiceCombo(int subcuenta) {
/*  75 */     int indice = 0;
/*  76 */     for (int x = 0; x < this.comboRepercutido.getItemCount(); x++) {
/*  77 */       TipoSubcuenta sub = new TipoSubcuenta();
/*  78 */       sub = (TipoSubcuenta)this.comboRepercutido.getItemAt(x);
/*  79 */       if (sub.getCodigo() == subcuenta) {
/*  80 */         indice = x;
/*  81 */         break;
/*     */       }
/*     */     }
/*  84 */     return indice;
/*     */   }
/*     */ 
/*     */   private void guardarTipoIVA()
/*     */   {
/*  95 */     TipoIVA nuevoIva = new TipoIVA();
/*  96 */     String nombre = this.campoNombre.getText();
/*  97 */     if ((nombre != null) && (!nombre.equals(""))) {
/*  98 */       double iva = 0.0D; double re = 0.0D;
/*     */       try {
/* 100 */         iva = Double.parseDouble(this.campoIVA.getText());
/* 101 */         re = Double.parseDouble(this.campoRE.getText());
/*     */       } catch (NumberFormatException numberFormatException) {
/* 103 */         numberFormatException.printStackTrace();
/*     */       }
/* 105 */       if (iva == 0.0D) {
/* 106 */         re = 0.0D;
/*     */       }
/* 108 */       TipoSubcuenta subReper = (TipoSubcuenta)this.comboRepercutido.getSelectedItem();
/* 109 */       TipoSubcuenta subSopor = (TipoSubcuenta)this.comboSoportado.getSelectedItem();
/* 110 */       TipoSubcuenta subRE = (TipoSubcuenta)this.comboRepercutidoRE.getSelectedItem();
/* 111 */       
            if (!subReper.equals(subRE)) {
/* 112 */         ManejoTiposIVA mT = new ManejoTiposIVA(Inicio.getCGeneral());
/* 113 */         if (mT.cuentaUtilizada(subSopor.getCodigo())) {
/* 114 */           showError("La cuenta " + subSopor + " ya está\n asignada a otro tipo de IVA");
/* 115 */           return;
/*     */         }
/* 117 */         if (mT.cuentaUtilizada(subReper.getCodigo())) {
/* 118 */           showError("La cuenta " + subReper + " ya está\n asignada a otro tipo de IVA");
/* 119 */           return;
/*     */         }
/* 121 */         if (mT.cuentaUtilizada(subRE.getCodigo())) {
/* 122 */           showError("La cuenta " + subRE + " ya está\n asignada a otro tipo de IVA");
/* 123 */           return;
/*     */         }
/* 125 */         nuevoIva.setNombre(nombre);
/* 126 */         nuevoIva.setIva(iva);
/* 127 */         nuevoIva.setRe(re);
/* 128 */         if (iva != 0.0D) {
/* 129 */           nuevoIva.setCuentaRep(subReper.getCodigo());
/* 130 */           nuevoIva.setCuentaSop(subSopor.getCodigo());
/* 131 */           nuevoIva.setCuentaRE(subRE.getCodigo());
/*     */         }
/* 133 */         mT.crear(nuevoIva);
/* 134 */         crearListaIVA();
/*     */       }
/*     */       else {
/* 137 */         showError("La cuenta de IVA repercutido no puede ser\nigual que la de recargo de equivalencia");
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void modificarTipoIVA() {
/* 143 */     TipoIVA nuevoIva = new TipoIVA();
/* 144 */     String nombre = this.campoNombre.getText();
/* 145 */     double iva = 0.0D; double re = 0.0D;
/*     */     try {
/* 147 */       iva = Double.parseDouble(this.campoIVA.getText());
/* 148 */       re = Double.parseDouble(this.campoRE.getText());
/*     */     } catch (NumberFormatException numberFormatException) {
/* 150 */       numberFormatException.printStackTrace();
/*     */     }
/* 152 */     TipoSubcuenta subReper = (TipoSubcuenta)this.comboRepercutido.getSelectedItem();
/* 153 */     TipoSubcuenta subSopor = (TipoSubcuenta)this.comboSoportado.getSelectedItem();
/* 154 */     TipoSubcuenta subRE = (TipoSubcuenta)this.comboRepercutidoRE.getSelectedItem();
System.out.println(nombre);
/* 155 */     if (!subReper.equals(subRE)) {
/* 156 */       TipoIVA viejoIva = new TipoIVA();
/* 157 */       viejoIva = (TipoIVA)this.listaTipos.getSelectedValue();
/* 158 */       ManejoTiposIVA mT = new ManejoTiposIVA(Inicio.getCGeneral());
/* 159 */       if ((mT.cuentaUtilizada(subSopor.getCodigo())) && 
/* 160 */         (subSopor.getCodigo() != viejoIva.getCuentaSop())) {
/* 161 */         showError("La cuenta " + subSopor + " ya está\n asignada a otro tipo de IVA");
/* 162 */         return;
/*     */       }
/*     */ 
/* 165 */       if ((mT.cuentaUtilizada(subReper.getCodigo())) && 
/* 166 */         (subReper.getCodigo() != viejoIva.getCuentaRep())) {
/* 167 */         showError("La cuenta " + subReper + " ya está\n asignada a otro tipo de IVA");
/* 168 */         return;
/*     */       }
/*     */ 
/* 171 */       if ((mT.cuentaUtilizada(subRE.getCodigo())) && 
/* 172 */         (subRE.getCodigo() != viejoIva.getCuentaRE())) {
/* 173 */         showError("La cuenta " + subRE + " ya está\n asignada a otro tipo de IVA");
/* 174 */         return;
/*     */       }
/*     */ 
/* 177 */       nuevoIva.setId(viejoIva.getId());
/* 178 */       nuevoIva.setNombre(nombre);
/* 179 */       nuevoIva.setIva(iva);
/* 180 */       nuevoIva.setRe(re);
/* 181 */       nuevoIva.setCuentaRep(subReper.getCodigo());
/* 182 */       nuevoIva.setCuentaSop(subSopor.getCodigo());
/* 183 */       nuevoIva.setCuentaRE(subRE.getCodigo());
/* 184 */       mT.modificar(nuevoIva);
/* 185 */       crearListaIVA();
/*     */     } else {
/* 187 */       showError("La cuenta de IVA repercutido no puede ser\nigual que la de recargo de equivalencia");
/*     */     }
/*     */   }
/*     */ 
/*     */   private void eliminarTipoIVA() {
/* 192 */     TipoIVA iva = new TipoIVA();
/* 193 */     iva = (TipoIVA)this.listaTipos.getSelectedValue();
/* 194 */     if (iva.getId() != -1) {
/* 195 */       ManejoTiposIVA mT = new ManejoTiposIVA(Inicio.getCGeneral());
/* 196 */       mT.borrar(iva);
/* 197 */       crearListaIVA();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void colocarDatos(TipoIVA iva)
/*     */   {
/* 203 */     this.campoNombre.setText(iva.getNombre());
/* 204 */     this.campoIVA.setText(Double.toString(iva.getIva()));
/* 205 */     this.campoRE.setText(Double.toString(iva.getRe()));
/*     */ 
/* 209 */     this.comboRepercutido.setSelectedIndex(indiceCombo(iva.getCuentaRep()));
/* 210 */     this.comboRepercutidoRE.setSelectedIndex(indiceCombo(iva.getCuentaRE()));
/* 211 */     this.comboSoportado.setSelectedIndex(indiceCombo(iva.getCuentaSop()));
/*     */   }
/*     */ 
/*     */   private void showError(String error) {
/* 215 */     JOptionPane.showMessageDialog(Inicio.frame, error, "Error", 0);
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/* 228 */     this.jPanel1 = new JPanel();
/* 229 */     this.botonAdd = new JButton();
/* 230 */     this.botonEliminar = new JButton();
/* 231 */     this.botonModificar = new JButton();
/* 232 */     this.jScrollPane1 = new JScrollPane();
/* 233 */     this.listaTipos = new JList();
/* 234 */     this.jLabel1 = new JLabel();
/* 235 */     this.jLabel2 = new JLabel();
/* 236 */     this.jLabel3 = new JLabel();
/* 237 */     this.jLabel4 = new JLabel();
/* 238 */     this.jLabel5 = new JLabel();
/* 239 */     this.jLabel8 = new JLabel();
/* 240 */     this.jLabel6 = new JLabel();
/* 241 */     this.comboSoportado = new JComboBox();
/* 242 */     this.comboRepercutido = new JComboBox();
/* 243 */     this.comboRepercutidoRE = new JComboBox();
/* 244 */     this.campoNombre = new JTextField();
/* 245 */     this.campoRE = new JTextField();
/* 246 */     this.campoIVA = new JTextField();
/*     */ 
/* 248 */     setDefaultCloseOperation(2);
/* 249 */     setTitle("Gestión de Tipos de IVA");
/* 250 */     addWindowListener(new WindowAdapter() {
/*     */       public void windowClosed(WindowEvent evt) {
/* 252 */         TiposIva.this.formWindowClosed(evt);
/*     */       }
/*     */     });
/* 256 */     this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/*     */ 
/* 258 */     this.botonAdd.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/edit_add.png")));
/* 259 */     this.botonAdd.setToolTipText("Añadir");
/* 260 */     this.botonAdd.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 262 */         TiposIva.this.botonAddActionPerformed(evt);
/*     */       }
/*     */     });
/* 266 */     this.botonEliminar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/edit_remove.png")));
/* 267 */     this.botonEliminar.setToolTipText("Eliminar");
/* 268 */     this.botonEliminar.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 270 */         TiposIva.this.botonEliminarActionPerformed(evt);
/*     */       }
/*     */     });
/* 274 */     this.botonModificar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/apply.png")));
/* 275 */     this.botonModificar.setToolTipText("Modificar");
/* 276 */     this.botonModificar.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 278 */         TiposIva.this.botonModificarActionPerformed(evt);
/*     */       }
/*     */     });
/* 282 */     this.listaTipos.setSelectionMode(0);
/* 283 */     this.listaTipos.addListSelectionListener(new ListSelectionListener() {
/*     */       public void valueChanged(ListSelectionEvent evt) {
/* 285 */         TiposIva.this.listaTiposValueChanged(evt);
/*     */       }
/*     */     });
/* 288 */     this.jScrollPane1.setViewportView(this.listaTipos);
/*     */ 
/* 290 */     this.jLabel1.setText("Nombre");
/*     */ 
/* 292 */     this.jLabel2.setText("Tipo I.V.A.");
/*     */ 
/* 294 */     this.jLabel3.setText("Tipo R.E.");
/*     */ 
/* 296 */     this.jLabel4.setText("Subcuentas");
/*     */ 
/* 298 */     this.jLabel5.setText("Repercutido");
/*     */ 
/* 300 */     this.jLabel8.setText("Soportado");
/*     */ 
/* 302 */     this.jLabel6.setText("Rep.Recargo Equiv.");
/*     */ 
/* 304 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 305 */     this.jPanel1.setLayout(jPanel1Layout);
/* 306 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().add(this.botonAdd).add(18, 18, 18).add(this.botonEliminar).add(18, 18, 18).add(this.botonModificar).addPreferredGap(0, 439, -2)).add(jPanel1Layout.createSequentialGroup().add(this.jScrollPane1, -2, 192, -2).add(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().add(86, 86, 86).add(jPanel1Layout.createParallelGroup(1).add(this.jLabel1).add(this.jLabel2).add(this.jLabel3))).add(jPanel1Layout.createSequentialGroup().add(54, 54, 54).add(jPanel1Layout.createParallelGroup(2).add(this.jLabel4).add(this.jLabel5))).add(jPanel1Layout.createSequentialGroup().addPreferredGap(0).add(jPanel1Layout.createParallelGroup(2).add(this.jLabel8, -2, 74, -2).add(this.jLabel6)))).addPreferredGap(0).add(jPanel1Layout.createParallelGroup(2).add(1, this.comboSoportado, 0, 222, 32767).add(1, this.comboRepercutido, 0, 222, 32767).add(this.comboRepercutidoRE, 0, 222, 32767).add(jPanel1Layout.createParallelGroup(1, false).add(this.campoNombre, -1, 204, 32767).add(this.campoRE).add(this.campoIVA, -2, 93, -2))))).add(15, 15, 15)));
/*     */ 
/* 348 */     jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().add(jPanel1Layout.createParallelGroup(3).add(this.jLabel1).add(this.campoNombre, -2, -1, -2)).addPreferredGap(0).add(jPanel1Layout.createParallelGroup(3).add(this.jLabel2).add(this.campoIVA, -2, -1, -2)).addPreferredGap(0).add(jPanel1Layout.createParallelGroup(3).add(this.jLabel3).add(this.campoRE, -2, -1, -2)).addPreferredGap(0, 49, 32767).add(this.jLabel4).addPreferredGap(1).add(jPanel1Layout.createParallelGroup(3).add(this.comboRepercutido, -2, -1, -2).add(this.jLabel5)).add(18, 18, 18).add(jPanel1Layout.createParallelGroup(3).add(this.comboRepercutidoRE, -2, -1, -2).add(this.jLabel6)).add(18, 18, 18).add(jPanel1Layout.createParallelGroup(3).add(this.comboSoportado, -2, -1, -2).add(this.jLabel8))).add(this.jScrollPane1, -2, 290, -2)).add(18, 18, 18).add(jPanel1Layout.createParallelGroup(3).add(this.botonAdd).add(this.botonEliminar).add(this.botonModificar)).add(11, 11, 11)));
/*     */ 
/* 388 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 389 */     getContentPane().setLayout(layout);
/* 390 */     layout.setHorizontalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(this.jPanel1, -1, -1, 32767).addContainerGap()));
/*     */ 
/* 397 */     layout.setVerticalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(this.jPanel1, -2, -1, -2).addContainerGap(-1, 32767)));
/*     */ 
/* 405 */     pack();
/*     */   }
/*     */ 
/*     */   private void botonAddActionPerformed(ActionEvent evt) {
/* 409 */     guardarTipoIVA();
/* 410 */     this.cambios = true;
/*     */   }
/*     */ 
/*     */   private void botonEliminarActionPerformed(ActionEvent evt) {
/* 414 */     eliminarTipoIVA();
/* 415 */     this.cambios = true;
/*     */   }
/*     */ 
/*     */   private void botonModificarActionPerformed(ActionEvent evt) {
/* 419 */     modificarTipoIVA();
/* 420 */     this.cambios = true;
/*     */   }
/*     */ 
/*     */   private void listaTiposValueChanged(ListSelectionEvent evt)
/*     */   {
/* 425 */     if ((!evt.getValueIsAdjusting()) && (!this.listaTipos.isSelectionEmpty())) {
/* 426 */       TipoIVA iva = (TipoIVA)this.listaTipos.getSelectedValue();
/* 427 */       colocarDatos(iva);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void formWindowClosed(WindowEvent evt) {
/* 432 */     if (this.cambios)
/* 433 */       Inicio.frame.renovarModeloIVA();
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.dialogosFunciones.TiposIva
 * JD-Core Version:    0.6.2
 */