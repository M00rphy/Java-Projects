/*     */ package contaes.dialogosFunciones;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.MarcoInicio;
/*     */ import contaes.auxiliar.DocDigitos;
/*     */ import contaes.manejoDatos.ManejoFormasPago;
/*     */ import contaes.manejoDatos.TipoFormaPago;
/*     */ import java.awt.Container;
/*     */ import java.awt.Frame;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.WindowAdapter;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.util.ArrayList;
import java.util.List;
/*     */ import javax.swing.DefaultListModel;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JList;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.event.ListSelectionEvent;
/*     */ import javax.swing.event.ListSelectionListener;
/*     */ import org.jdesktop.layout.GroupLayout;
/*     */ import org.jdesktop.layout.GroupLayout.ParallelGroup;
/*     */ import org.jdesktop.layout.GroupLayout.SequentialGroup;
/*     */ 
/*     */ public class FormasPago extends JDialog
/*     */ {
/*  28 */   boolean cambios = false;
/*     */   private JButton botonBorrar;
/*     */   private JButton botonGuardar;
/*     */   private JButton botonModificar;
/*     */   private JTextField campoDiaFijo;
/*     */   private JTextField campoEntrePagos;
/*     */   private JTextField campoNombre;
/*     */   private JTextField campoNumero;
/*     */   private JTextField campoPrimerPago;
/*     */   private JButton jButton1;
/*     */   private JLabel jLabel1;
/*     */   private JLabel jLabel2;
/*     */   private JLabel jLabel3;
/*     */   private JLabel jLabel4;
/*     */   private JLabel jLabel5;
/*     */   private JList jList1;
/*     */   private JScrollPane jScrollPane1;
/*     */ 
/*     */   public FormasPago(Frame parent, boolean modal)
/*     */   {
/*  32 */     super(parent, modal);
/*  33 */     initComponents();
/*     */ 
/*  35 */     crearLista();
/*     */   }
/*     */ 
/*     */   private void crearLista() {
/*  39 */     this.jList1.setModel(getModeloLista());
/*     */   }
/*     */ 
/*     */   private DefaultListModel getModeloLista() {
/*  43 */     DefaultListModel modelo = new DefaultListModel();
/*  44 */     ArrayList listaTipos = new ArrayList();
/*  45 */     ManejoFormasPago mF = new ManejoFormasPago(Inicio.getCGeneral());
/*  46 */     listaTipos = mF.getFormasPago();
/*  47 */     for (TipoFormaPago tipoFormaPago :(List<TipoFormaPago>) listaTipos) {
/*  48 */       modelo.addElement(tipoFormaPago);
/*     */     }
/*  50 */     return modelo;
/*     */   }
/*     */ 
/*     */   private void guardarFormaPago() {
/*  54 */     TipoFormaPago formaPago = getFormaFromForm();
/*  55 */     if (formaPago != null) {
/*  56 */       ManejoFormasPago mF = new ManejoFormasPago(Inicio.getCGeneral());
/*  57 */       int id = mF.crear(formaPago);
/*  58 */       if (id != -1) {
/*  59 */         crearLista();
/*     */       }
/*     */       else
/*  62 */         showError("Error al introducir los datos en la base de datos.");
/*     */     }
/*     */   }
/*     */ 
/*     */   private void modificarFormaPago()
/*     */   {
/*  68 */     TipoFormaPago formaPago = getFormaFromForm();
/*  69 */     if ((formaPago != null) && 
/*  70 */       (!this.jList1.isSelectionEmpty())) {
/*  71 */       TipoFormaPago oldForma = (TipoFormaPago)this.jList1.getSelectedValue();
/*  72 */       if (oldForma != null) {
/*  73 */         formaPago.setIdFormaPago(oldForma.getIdFormaPago());
/*  74 */         ManejoFormasPago mF = new ManejoFormasPago(Inicio.getCGeneral());
/*  75 */         if (mF.modificar(formaPago)) {
/*  76 */           crearLista();
/*     */         }
/*     */         else
/*  79 */           showError("Error al actualizar los datos.");
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private TipoFormaPago getFormaFromForm()
/*     */   {
/*  87 */     TipoFormaPago fP = null;
/*  88 */     String nombre = this.campoNombre.getText();
/*  89 */     String PP = this.campoPrimerPago.getText();
/*  90 */     String EP = this.campoEntrePagos.getText();
/*  91 */     String N = this.campoNumero.getText();
/*  92 */     String DF = this.campoDiaFijo.getText();
/*  93 */     if ((!nombre.equals("")) && (!PP.equals("")) && (!EP.equals("")) && (!N.equals(""))) {
/*  94 */       fP = new TipoFormaPago();
/*  95 */       fP.setPago(nombre);
/*  96 */       fP.setDiasPrimerPago(Integer.valueOf(Integer.parseInt(PP)));
/*  97 */       fP.setDiasEntrePagos(Integer.valueOf(Integer.parseInt(EP)));
/*  98 */       fP.setNumeroPagos(Integer.valueOf(Integer.parseInt(N)));
/*  99 */       if ((DF != null) && (!DF.equals("")))
/* 100 */         fP.setDiaFijoPago(Integer.valueOf(Integer.parseInt(DF)));
/*     */     }
/*     */     else
/*     */     {
/* 104 */       showError("Falta algún dato");
/*     */     }
/* 106 */     return fP;
/*     */   }
/*     */ 
/*     */   private void cargarForma(TipoFormaPago formaPago) {
/* 110 */     this.campoNombre.setText(formaPago.getPago());
/* 111 */     this.campoPrimerPago.setText(Integer.toString(formaPago.getDiasPrimerPago().intValue()));
/* 112 */     this.campoEntrePagos.setText(Integer.toString(formaPago.getDiasEntrePagos().intValue()));
/* 113 */     this.campoNumero.setText(Integer.toString(formaPago.getNumeroPagos().intValue()));
/* 114 */     if (formaPago.getDiaFijoPago() != null)
/* 115 */       this.campoDiaFijo.setText(Integer.toString(formaPago.getDiaFijoPago().intValue()));
/*     */   }
/*     */ 
/*     */   private void limpiarForm()
/*     */   {
/* 120 */     this.campoNombre.setText("");
/* 121 */     this.campoPrimerPago.setText("");
/* 122 */     this.campoEntrePagos.setText("");
/* 123 */     this.campoNumero.setText("");
/* 124 */     this.campoDiaFijo.setText("");
/*     */   }
/*     */ 
/*     */   private void eliminarFormaPago(TipoFormaPago formaPago) {
/* 128 */     if ((formaPago.getIdFormaPago() != null) && (formaPago.getIdFormaPago().intValue() != -1)) {
/* 129 */       ManejoFormasPago mF = new ManejoFormasPago(Inicio.getCGeneral());
/* 130 */       if (mF.borrar(formaPago.getIdFormaPago().intValue())) {
/* 131 */         crearLista();
/*     */       }
/*     */       else
/* 134 */         showError("Error al eliminar la forma de pago seleccionada");
/*     */     }
/*     */   }
/*     */ 
/*     */   private void showError(String error)
/*     */   {
/* 140 */     JOptionPane.showMessageDialog(Inicio.frame, error, "Error", 0);
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/* 153 */     this.jScrollPane1 = new JScrollPane();
/* 154 */     this.jList1 = new JList();
/* 155 */     this.jLabel1 = new JLabel();
/* 156 */     this.jLabel2 = new JLabel();
/* 157 */     this.jLabel3 = new JLabel();
/* 158 */     this.jLabel4 = new JLabel();
/* 159 */     this.jLabel5 = new JLabel();
/* 160 */     this.campoNombre = new JTextField();
/* 161 */     this.campoPrimerPago = new JTextField();
/* 162 */     this.campoEntrePagos = new JTextField();
/* 163 */     this.campoNumero = new JTextField();
/* 164 */     this.campoDiaFijo = new JTextField();
/* 165 */     this.botonGuardar = new JButton();
/* 166 */     this.botonBorrar = new JButton();
/* 167 */     this.botonModificar = new JButton();
/* 168 */     this.jButton1 = new JButton();
/*     */ 
/* 170 */     setDefaultCloseOperation(2);
/* 171 */     setTitle("Gestión de formas de pago");
/* 172 */     addWindowListener(new WindowAdapter() {
/*     */       public void windowClosed(WindowEvent evt) {
/* 174 */         FormasPago.this.formWindowClosed(evt);
/*     */       }
/*     */     });
/* 178 */     this.jList1.setSelectionMode(0);
/* 179 */     this.jList1.addListSelectionListener(new ListSelectionListener() {
/*     */       public void valueChanged(ListSelectionEvent evt) {
/* 181 */         FormasPago.this.jList1ValueChanged(evt);
/*     */       }
/*     */     });
/* 184 */     this.jScrollPane1.setViewportView(this.jList1);
/*     */ 
/* 186 */     this.jLabel1.setText("Nombre");
/*     */ 
/* 188 */     this.jLabel2.setText("Días hasta 1er pago");
/*     */ 
/* 190 */     this.jLabel3.setText("Días entre pagos");
/*     */ 
/* 192 */     this.jLabel4.setText("Número de pagos");
/*     */ 
/* 194 */     this.jLabel5.setText("Día fijo de pago");
/*     */ 
/* 196 */     this.campoPrimerPago.setDocument(new DocDigitos());
/*     */ 
/* 198 */     this.campoEntrePagos.setDocument(new DocDigitos());
/*     */ 
/* 200 */     this.campoNumero.setDocument(new DocDigitos());
/*     */ 
/* 202 */     this.campoDiaFijo.setDocument(new DocDigitos());
/*     */ 
/* 204 */     this.botonGuardar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/edit_add.png")));
/* 205 */     this.botonGuardar.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 207 */         FormasPago.this.botonGuardarActionPerformed(evt);
/*     */       }
/*     */     });
/* 211 */     this.botonBorrar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/edit_remove.png")));
/* 212 */     this.botonBorrar.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 214 */         FormasPago.this.botonBorrarActionPerformed(evt);
/*     */       }
/*     */     });
/* 218 */     this.botonModificar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/apply.png")));
/* 219 */     this.botonModificar.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 221 */         FormasPago.this.botonModificarActionPerformed(evt);
/*     */       }
/*     */     });
/* 225 */     this.jButton1.setText("Limpiar");
/* 226 */     this.jButton1.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 228 */         FormasPago.this.jButton1ActionPerformed(evt);
/*     */       }
/*     */     });
/* 232 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 233 */     getContentPane().setLayout(layout);
/* 234 */     layout.setHorizontalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(this.jScrollPane1, -2, 132, -2).add(18, 18, 18).add(layout.createParallelGroup(1).add(layout.createSequentialGroup().add(this.jLabel1).addPreferredGap(0).add(this.campoNombre, -1, 212, 32767)).add(layout.createSequentialGroup().add(layout.createParallelGroup(1).add(this.jLabel2).add(this.jLabel3).add(this.jLabel4).add(this.jLabel5)).addPreferredGap(0).add(layout.createParallelGroup(1, false).add(this.campoDiaFijo).add(this.campoNumero).add(this.campoEntrePagos).add(this.campoPrimerPago, -1, 68, 32767))).add(layout.createSequentialGroup().add(this.botonGuardar).add(18, 18, 18).add(this.botonBorrar).add(18, 18, 18).add(this.botonModificar).add(18, 18, 18).add(this.jButton1))).addContainerGap()));
/*     */ 
/* 267 */     layout.setVerticalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(layout.createParallelGroup(1).add(layout.createSequentialGroup().add(layout.createParallelGroup(3).add(this.jLabel1).add(this.campoNombre, -2, -1, -2)).addPreferredGap(1).add(layout.createParallelGroup(3).add(this.jLabel2).add(this.campoPrimerPago, -2, -1, -2)).addPreferredGap(1).add(layout.createParallelGroup(3).add(this.jLabel3).add(this.campoEntrePagos, -2, -1, -2)).addPreferredGap(1).add(layout.createParallelGroup(3).add(this.jLabel4).add(this.campoNumero, -2, -1, -2)).addPreferredGap(1).add(layout.createParallelGroup(3).add(this.jLabel5).add(this.campoDiaFijo, -2, -1, -2)).add(18, 18, 18).add(layout.createParallelGroup(3).add(this.botonGuardar).add(this.botonBorrar).add(this.botonModificar).add(this.jButton1))).add(this.jScrollPane1, -2, 232, -2)).addContainerGap(20, 32767)));
/*     */ 
/* 302 */     pack();
/*     */   }
/*     */ 
/*     */   private void botonGuardarActionPerformed(ActionEvent evt) {
/* 306 */     this.cambios = true;
/* 307 */     guardarFormaPago();
/*     */   }
/*     */ 
/*     */   private void botonBorrarActionPerformed(ActionEvent evt) {
/* 311 */     this.cambios = true;
/* 312 */     if (!this.jList1.isSelectionEmpty()) {
/* 313 */       TipoFormaPago fp = (TipoFormaPago)this.jList1.getSelectedValue();
/* 314 */       eliminarFormaPago(fp);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void botonModificarActionPerformed(ActionEvent evt) {
/* 319 */     this.cambios = true;
/* 320 */     modificarFormaPago();
/*     */   }
/*     */ 
/*     */   private void jList1ValueChanged(ListSelectionEvent evt) {
/* 324 */     if ((!evt.getValueIsAdjusting()) && (!this.jList1.isSelectionEmpty())) {
/* 325 */       TipoFormaPago formaPago = (TipoFormaPago)this.jList1.getSelectedValue();
/* 326 */       cargarForma(formaPago);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void jButton1ActionPerformed(ActionEvent evt) {
/* 331 */     limpiarForm();
/*     */   }
/*     */ 
/*     */   private void formWindowClosed(WindowEvent evt) {
/* 335 */     if (this.cambios)
/* 336 */       Inicio.frame.renovarModeloFormasPago();
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.dialogosFunciones.FormasPago
 * JD-Core Version:    0.6.2
 */