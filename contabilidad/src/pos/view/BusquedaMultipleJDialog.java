/*     */ package pos.view;
/*     */ 
/*     */ import almacen2.data.AlmacenInterno;
/*     */ import almacen2.data.ManejadorAlmacenInterno;
/*     */ import contaes.Inicio;
/*     */ import contaes.Puente;
/*     */ import contaes.manejoDatos.ManejoSubcuentas;
/*     */ import contaes.manejoDatos.TipoSubcuenta;
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Frame;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.KeyAdapter;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.util.ArrayList;
import java.util.List;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.DefaultListModel;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.GroupLayout.Alignment;
/*     */ import javax.swing.GroupLayout.ParallelGroup;
/*     */ import javax.swing.GroupLayout.SequentialGroup;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JList;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;
/*     */ import javax.swing.LayoutStyle.ComponentPlacement;
/*     */ import javax.swing.ListModel;
/*     */ import pos.control.MedioPagoControl;
/*     */ import pos.control.VendedorControl;
/*     */ import pos.model.MedioPago;
/*     */ import pos.model.ObjetoMultifuncional;
/*     */ import pos.model.Vendedor;
/*     */ import pos.view.editors.JEditorKeys;
/*     */ import pos.view.editors.JEditorString;
/*     */ 
/*     */ public class BusquedaMultipleJDialog extends JDialog
/*     */ {
/*     */   ArrayList<ObjetoMultifuncional> listaObjetos;
/*     */   ObjetoMultifuncional objetoSeleccionado;
/*     */   DefaultListModel modelo;
/*     */   private JButton Inicioo;
/*     */   private JButton abajo;
/*     */   private JButton arriba;
/*     */   private JButton botonBuscarDeNuevo;
/*     */   private JButton botonSeleccionar;
/*     */   private JEditorString campoBusqueda;
/*     */   private JButton fin;
/*     */   private JEditorKeys jEditorKeys1;
/*     */   private JList jList;
/*     */   private JPanel jPanel1;
/*     */   private JPanel jPanel2;
/*     */   private JScrollPane jScrollPane1;
/*     */ 
/*     */   public BusquedaMultipleJDialog(Frame parent, boolean modal, int tipoObjeto)
/*     */   {
/*  49 */     super(parent, modal);
/*  50 */     initComponents();
/*     */ 
/*  52 */     this.campoBusqueda.addPropertyChangeListener("Edition", new RecalculateName());
/*  53 */     this.campoBusqueda.addEditorKeys(this.jEditorKeys1);
/*  54 */     this.campoBusqueda.activate();
/*     */ 
/*  56 */     setModeloLista(tipoObjeto);
/*  57 */     crearLista();
/*  58 */     this.jList.setSelectedIndex(0);
/*  59 */     this.campoBusqueda.requestFocus();
/*     */   }
/*     */ 
/*     */   public static ObjetoMultifuncional obtenerObjeto(Frame parent, boolean modal, int tipoObjeto) {
/*  63 */     BusquedaMultipleJDialog dlg = new BusquedaMultipleJDialog(parent, modal, tipoObjeto);
/*  64 */     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/*  65 */     Dimension frameSize = dlg.getSize();
/*  66 */     if (frameSize.height > screenSize.height) {
/*  67 */       frameSize.height = screenSize.height;
/*     */     }
/*  69 */     if (frameSize.width > screenSize.width) {
/*  70 */       frameSize.width = screenSize.width;
/*     */     }
/*  72 */     dlg.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
/*     */ 
/*  75 */     dlg.setVisible(true);
/*  76 */     return dlg.getObjetoSeleccionado();
/*     */   }
/*     */ 
/*     */   private void crearLista() {
/*  80 */     this.jList.setModel(this.modelo);
/*     */   }
/*     */ 
/*     */   private DefaultListModel setModeloLista(int tipoObjeto) {
/*  84 */     this.modelo = new DefaultListModel();
/*  85 */     if (tipoObjeto == 0) {
/*  86 */       setTitle(Mensajes.getString("seleccione") + ": " + Mensajes.getString("cliente"));
/*  87 */       ArrayList lista = new ArrayList();
/*  88 */       ManejoSubcuentas mS = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  89 */       lista = mS.listaSubcuentas(43000000, 43099999);
/*  90 */       mS.cerrarRs();
/*  91 */       for (TipoSubcuenta tipoSubcuenta : (List<TipoSubcuenta>)lista) {
/*  92 */         this.modelo.addElement(new ObjetoMultifuncional(tipoSubcuenta.getCodigo(), tipoSubcuenta.getNombre()));
/*     */       }
/*     */ 
/*     */     }
/* 100 */     else if (tipoObjeto == 1) {
/* 101 */       setTitle(Mensajes.getString("seleccione") + ": " + Mensajes.getString("vendedor"));
/* 102 */       ArrayList lista = new ArrayList();
/* 103 */       VendedorControl vC = new VendedorControl(Inicio.getcAlmacen());
/* 104 */       lista = vC.getTodosVendedores();
/* 105 */       vC.cerrarRs();
/* 106 */       for (Vendedor vendedor :  (List<Vendedor>)lista) {
/* 107 */         this.modelo.addElement(new ObjetoMultifuncional(vendedor.getId().intValue(), vendedor.getNombre()));
/*     */       }
/*     */     }
/* 110 */     else if (tipoObjeto == 2) {
/* 111 */       setTitle(Mensajes.getString("seleccione") + ": " + Mensajes.getString("almacen"));
/* 112 */       ArrayList lista = new ArrayList();
/* 113 */       ManejadorAlmacenInterno mAI = new ManejadorAlmacenInterno(Inicio.getcAlmacen());
/* 114 */       lista = mAI.getAlmacenes();
/* 115 */       mAI.cerrarRs();
/* 116 */       for (AlmacenInterno almacenInterno : (List<AlmacenInterno>) lista)
/* 117 */         this.modelo.addElement(new ObjetoMultifuncional(almacenInterno.getId().intValue(), almacenInterno.getNombre()));
/*     */     }
/*     */     else
/*     */     {
/* 121 */       setTitle(Mensajes.getString("seleccione") + ": " + Mensajes.getString("formapago"));
/* 122 */       ArrayList lista = new ArrayList();
/* 123 */       MedioPagoControl mPC = new MedioPagoControl(Inicio.getcAlmacen());
/* 124 */       lista = mPC.getTodosMediosPago();
/* 125 */       mPC.cerrarRs();
/* 126 */       for (MedioPago medioPago :(List<MedioPago>) lista) {
/* 127 */         this.modelo.addElement(new ObjetoMultifuncional(medioPago.getId().intValue(), medioPago.getNombre()));
/*     */       }
/*     */     }
/* 130 */     return this.modelo;
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/* 143 */     this.jPanel1 = new JPanel();
/* 144 */     this.campoBusqueda = new JEditorString();
/* 145 */     this.jEditorKeys1 = new JEditorKeys();
/* 146 */     this.jScrollPane1 = new JScrollPane();
/* 147 */     this.jList = new JList();
/* 148 */     this.botonSeleccionar = new JButton();
/* 149 */     this.jPanel2 = new JPanel();
/* 150 */     this.Inicioo = new JButton();
/* 151 */     this.arriba = new JButton();
/* 152 */     this.abajo = new JButton();
/* 153 */     this.fin = new JButton();
/* 154 */     this.botonBuscarDeNuevo = new JButton();
/*     */ 
/* 156 */     setDefaultCloseOperation(2);
/* 157 */     ResourceBundle bundle = ResourceBundle.getBundle("internationalization/Mensajes");
/* 158 */     setTitle(bundle.getString("seleccionar"));
/*     */ 
/* 160 */     this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/*     */ 
/* 162 */     this.campoBusqueda.addKeyListener(new KeyAdapter() {
/*     */       public void keyPressed(KeyEvent evt) {
/* 164 */         BusquedaMultipleJDialog.this.campoBusquedaKeyPressed(evt);
/*     */       }
/*     */     });
/* 168 */     this.jScrollPane1.setViewportView(this.jList);
/*     */ 
/* 170 */     this.botonSeleccionar.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/button_ok.png")));
/* 171 */     this.botonSeleccionar.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 173 */         BusquedaMultipleJDialog.this.botonSeleccionarActionPerformed(evt);
/*     */       }
/*     */     });
/* 177 */     this.Inicioo.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/3uparrow.png")));
/* 178 */     this.Inicioo.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 180 */         BusquedaMultipleJDialog.this.InicioActionPerformed(evt);
/*     */       }
/*     */     });
/* 184 */     this.arriba.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/1uparrow22.png")));
/* 185 */     this.arriba.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 187 */         BusquedaMultipleJDialog.this.arribaActionPerformed(evt);
/*     */       }
/*     */     });
/* 191 */     this.abajo.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/1downarrow22.png")));
/* 192 */     this.abajo.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 194 */         BusquedaMultipleJDialog.this.abajoActionPerformed(evt);
/*     */       }
/*     */     });
/* 198 */     this.fin.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/3downarrow.png")));
/* 199 */     this.fin.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 201 */         BusquedaMultipleJDialog.this.finActionPerformed(evt);
/*     */       }
/*     */     });
/* 205 */     GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
/* 206 */     this.jPanel2.setLayout(jPanel2Layout);
/* 207 */     jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.Inicioo).addComponent(this.arriba).addComponent(this.abajo).addComponent(this.fin)).addContainerGap(-1, 32767)));
/*     */ 
/* 218 */     jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addComponent(this.Inicioo).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.arriba).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.abajo).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.fin).addContainerGap(-1, 32767)));
/*     */ 
/* 232 */     this.botonBuscarDeNuevo.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/search22.png")));
/* 233 */     this.botonBuscarDeNuevo.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 235 */         BusquedaMultipleJDialog.this.botonBuscarDeNuevoActionPerformed(evt);
/*     */       }
/*     */     });
/* 239 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 240 */     this.jPanel1.setLayout(jPanel1Layout);
/* 241 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.campoBusqueda, -2, 183, -2).addComponent(this.jScrollPane1, 0, 0, 32767)).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanel2, -2, -1, -2)).addGroup(jPanel1Layout.createSequentialGroup().addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.botonBuscarDeNuevo).addComponent(this.botonSeleccionar)))).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jEditorKeys1, -2, -1, -2).addContainerGap(-1, 32767)));
/*     */ 
/* 261 */     jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.jEditorKeys1, -2, -1, -2).addContainerGap()).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.campoBusqueda, -2, -1, -2).addComponent(this.botonBuscarDeNuevo)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.jScrollPane1, -1, 241, 32767).addContainerGap(17, 32767)).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.botonSeleccionar).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanel2, -2, -1, -2).addContainerGap()))))));
/*     */ 
/* 285 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 286 */     getContentPane().setLayout(layout);
/* 287 */     layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -2, -1, -2).addContainerGap(-1, 32767)));
/*     */ 
/* 294 */     layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -2, -1, -2).addContainerGap(-1, 32767)));
/*     */ 
/* 302 */     pack();
/*     */   }
/*     */ 
/*     */   private void campoBusquedaKeyPressed(KeyEvent evt)
/*     */   {
/*     */   }
/*     */ 
/*     */   private void botonSeleccionarActionPerformed(ActionEvent evt) {
/* 310 */     this.objetoSeleccionado = ((ObjetoMultifuncional)this.jList.getSelectedValue());
/* 311 */     dispose();
/*     */   }
/*     */ 
/*     */   private void InicioActionPerformed(ActionEvent evt) {
/* 315 */     this.jList.setSelectedIndex(0);
/*     */   }
/*     */ 
/*     */   private void arribaActionPerformed(ActionEvent evt) {
/* 319 */     if (!this.jList.isSelectionEmpty()) {
/* 320 */       int rowSelected = this.jList.getSelectedIndex();
/* 321 */       if (rowSelected > 0) {
/* 322 */         rowSelected--;
/* 323 */         this.jList.setSelectedIndex(rowSelected);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void abajoActionPerformed(ActionEvent evt) {
/* 329 */     if (!this.jList.isSelectionEmpty()) {
/* 330 */       int rowSelected = this.jList.getSelectedIndex();
/* 331 */       if (rowSelected < this.modelo.getSize() - 1) {
/* 332 */         rowSelected++;
/* 333 */         this.jList.setSelectedIndex(rowSelected);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void finActionPerformed(ActionEvent evt) {
/* 339 */     this.jList.setSelectedIndex(this.modelo.getSize() - 1);
/*     */   }
/*     */ 
/*     */   private void botonBuscarDeNuevoActionPerformed(ActionEvent evt) {
/* 343 */     String cadena = this.campoBusqueda.getText();
/* 344 */     busquedaEnLista(cadena);
/*     */   }
/*     */ 
/*     */   private void busquedaEnLista(String cadena) {
/* 348 */     if (cadena != null) {
/* 349 */       boolean finTabla = true;
/* 350 */       cadena = cadena.toUpperCase();
/* 351 */       int firstFile = this.jList.getSelectedIndex() + 1;
/* 352 */       int lastFile = this.jList.getModel().getSize() - 1;
/* 353 */       if (firstFile > lastFile) {
/* 354 */         firstFile = 0;
/*     */       }
/* 356 */       for (int x = firstFile; x < lastFile; x++) {
/* 357 */         String objetoLista = this.modelo.elementAt(x).toString();
/* 358 */         objetoLista = objetoLista.toUpperCase();
/* 359 */         if (objetoLista.indexOf(cadena) != -1) {
/* 360 */           this.jList.setSelectedIndex(x);
/* 361 */           finTabla = false;
/* 362 */           break;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public ObjetoMultifuncional getObjetoSeleccionado()
/*     */   {
/* 386 */     return this.objetoSeleccionado;
/*     */   }
/*     */ 
/*     */   private class RecalculateName
/*     */     implements PropertyChangeListener
/*     */   {
/*     */     private RecalculateName()
/*     */     {
/*     */     }
/*     */ 
/*     */     public void propertyChange(PropertyChangeEvent evt)
/*     */     {
/* 380 */       String cadena = BusquedaMultipleJDialog.this.campoBusqueda.getText();
/* 381 */       BusquedaMultipleJDialog.this.busquedaEnLista(cadena);
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.view.BusquedaMultipleJDialog
 * JD-Core Version:    0.6.2
 */