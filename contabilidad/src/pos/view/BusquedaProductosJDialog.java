/*     */ package pos.view;
/*     */ 
/*     */ import almacen2.data.busqueda.Busqueda;
/*     */ import almacen2.data.busqueda.ModeloTabla;
/*     */ import almacen2.data.busqueda.Objeto;
/*     */ import contaes.Inicio;
/*     */ import contaes.auxiliarTablas.DerechaColorRenderer;
/*     */ import contaes.auxiliarTablas.GeneralColorRenderer;
/*     */ import contaes.auxiliarTablas.ImporteColorRenderer;
/*     */ import contaes.auxiliarTablas.TableSorter;
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.Color;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Frame;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.KeyAdapter;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.util.ArrayList;
import java.util.List;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.GroupLayout.Alignment;
/*     */ import javax.swing.GroupLayout.ParallelGroup;
/*     */ import javax.swing.GroupLayout.SequentialGroup;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTable;
import javax.swing.LayoutStyle;
/*     */ import javax.swing.LayoutStyle.ComponentPlacement;
/*     */ import javax.swing.table.TableColumn;
/*     */ import javax.swing.table.TableColumnModel;
/*     */ import pos.view.editors.JEditorKeys;
/*     */ import pos.view.editors.JEditorString;
/*     */ 
/*     */ public class BusquedaProductosJDialog extends JDialog
/*     */ {
/*  32 */   private ModeloTabla modelo = null;
/*  33 */   private TableSorter tablaOrdenada = null;
/*     */   private String referencia;
/*  35 */   private ArrayList<Objeto> lista = new ArrayList();
/*     */   private JButton Inicioo;
/*     */   private JButton abajo;
/*     */   private JButton arriba;
/*     */   private JButton botonBuscarDeNuevo;
/*     */   private JButton botonSeleccionar;
/*     */   private JEditorString campoBusqueda;
/*     */   private JButton fin;
/*     */   private JEditorKeys jEditorKeys1;
/*     */   private JPanel jPanel1;
/*     */   private JPanel jPanel2;
/*     */   private JScrollPane jScrollPane1;
/*     */   private JTable tabla;
/*     */ 
/*     */   public BusquedaProductosJDialog(Frame parent, boolean modal)
/*     */   {
/*  45 */     super(parent, modal);
/*  46 */     initComponents();
/*     */ 
/*  49 */     this.campoBusqueda.addEditorKeys(this.jEditorKeys1);
/*  50 */     this.campoBusqueda.activate();
/*  51 */     getTabla();
/*     */ 
/*  53 */     this.campoBusqueda.requestFocus();
/*     */   }
/*     */ 
/*     */   private void getTabla() {
/*  57 */     this.tabla = new JTable(getTablaOrdenada());
/*     */ 
/*  59 */     this.tabla.setBackground(Color.white);
/*  60 */     this.tablaOrdenada.setTableHeader(this.tabla.getTableHeader());
/*  61 */     int anchoTabla = 430;
/*  62 */     TableColumn columna = this.tabla.getColumnModel().getColumn(0);
/*  63 */     columna.setPreferredWidth((int)(anchoTabla * 0.15D));
/*  64 */     columna = this.tabla.getColumnModel().getColumn(1);
/*  65 */     columna.setPreferredWidth((int)(anchoTabla * 0.15D));
/*  66 */     columna = this.tabla.getColumnModel().getColumn(2);
/*  67 */     columna.setPreferredWidth((int)(anchoTabla * 0.15D));
/*  68 */     columna = this.tabla.getColumnModel().getColumn(3);
/*  69 */     columna.setPreferredWidth((int)(anchoTabla * 0.25D));
/*  70 */     columna = this.tabla.getColumnModel().getColumn(4);
/*  71 */     columna.setPreferredWidth((int)(anchoTabla * 0.1D));
/*  72 */     columna.setCellRenderer(new ImporteColorRenderer());
/*  73 */     columna = this.tabla.getColumnModel().getColumn(5);
/*  74 */     columna.setPreferredWidth((int)(anchoTabla * 0.1D));
/*  75 */     columna.setCellRenderer(new ImporteColorRenderer());
/*  76 */     columna = this.tabla.getColumnModel().getColumn(6);
/*  77 */     columna.setPreferredWidth((int)(anchoTabla * 0.1D));
/*  78 */     columna.setCellRenderer(new DerechaColorRenderer());
/*  79 */     this.tabla.setDefaultRenderer(Object.class, new GeneralColorRenderer());
/*     */ 
/*  81 */     this.jScrollPane1.setViewportView(this.tabla);
/*     */   }
/*     */ 
/*     */   private ModeloTabla getModelo() {
/*  85 */     if (this.modelo == null) {
/*  86 */       this.modelo = new ModeloTabla(this.lista);
/*     */     }
/*  88 */     return this.modelo;
/*     */   }
/*     */ 
/*     */   private TableSorter getTablaOrdenada() {
/*  92 */     if (this.tablaOrdenada == null) {
/*  93 */       this.tablaOrdenada = new TableSorter();
/*  94 */       this.tablaOrdenada.setTableModel(getModelo());
/*     */     }
/*  96 */     return this.tablaOrdenada;
/*     */   }
/*     */ 
/*     */   public static String obtenerObjeto(Frame parent, boolean modal) {
/* 100 */     BusquedaProductosJDialog dlg = new BusquedaProductosJDialog(parent, modal);
/* 101 */     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/* 102 */     Dimension frameSize = dlg.getSize();
/* 103 */     if (frameSize.height > screenSize.height) {
/* 104 */       frameSize.height = screenSize.height;
/*     */     }
/* 106 */     if (frameSize.width > screenSize.width) {
/* 107 */       frameSize.width = screenSize.width;
/*     */     }
/* 109 */     dlg.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
/*     */ 
/* 112 */     dlg.setVisible(true);
/* 113 */     return dlg.getObjetoSeleccionado();
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/* 126 */     this.jPanel1 = new JPanel();
/* 127 */     this.campoBusqueda = new JEditorString();
/* 128 */     this.jEditorKeys1 = new JEditorKeys();
/* 129 */     this.jPanel2 = new JPanel();
/* 130 */     this.Inicioo = new JButton();
/* 131 */     this.arriba = new JButton();
/* 132 */     this.abajo = new JButton();
/* 133 */     this.fin = new JButton();
/* 134 */     this.botonSeleccionar = new JButton();
/* 135 */     this.botonBuscarDeNuevo = new JButton();
/* 136 */     this.jScrollPane1 = new JScrollPane();
/* 137 */     this.tabla = new JTable();
/*     */ 
/* 139 */     setDefaultCloseOperation(2);
/* 140 */     ResourceBundle bundle = ResourceBundle.getBundle("internationalization/Mensajes");
/* 141 */     setTitle(bundle.getString("seleccionar"));
/*     */ 
/* 143 */     this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/*     */ 
/* 145 */     this.campoBusqueda.addKeyListener(new KeyAdapter() {
/*     */       public void keyPressed(KeyEvent evt) {
/* 147 */         BusquedaProductosJDialog.this.campoBusquedaKeyPressed(evt);
/*     */       }
/*     */     });
/* 151 */     this.Inicioo.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/3uparrow.png")));
/* 152 */     this.Inicioo.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 154 */         BusquedaProductosJDialog.this.InicioActionPerformed(evt);
/*     */       }
/*     */     });
/* 158 */     this.arriba.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/1uparrow22.png")));
/* 159 */     this.arriba.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 161 */         BusquedaProductosJDialog.this.arribaActionPerformed(evt);
/*     */       }
/*     */     });
/* 165 */     this.abajo.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/1downarrow22.png")));
/* 166 */     this.abajo.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 168 */         BusquedaProductosJDialog.this.abajoActionPerformed(evt);
/*     */       }
/*     */     });
/* 172 */     this.fin.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/3downarrow.png")));
/* 173 */     this.fin.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 175 */         BusquedaProductosJDialog.this.finActionPerformed(evt);
/*     */       }
/*     */     });
/* 179 */     this.botonSeleccionar.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/button_ok.png")));
/* 180 */     this.botonSeleccionar.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 182 */         BusquedaProductosJDialog.this.botonSeleccionarActionPerformed(evt);
/*     */       }
/*     */     });
/* 186 */     GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
/* 187 */     this.jPanel2.setLayout(jPanel2Layout);
/* 188 */     jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.Inicioo).addComponent(this.arriba).addComponent(this.abajo).addComponent(this.fin).addComponent(this.botonSeleccionar)).addContainerGap(-1, 32767)));
/*     */ 
/* 200 */     jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addComponent(this.botonSeleccionar).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.Inicioo).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.arriba).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.abajo).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.fin).addContainerGap(-1, 32767)));
/*     */ 
/* 216 */     this.botonBuscarDeNuevo.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/search22.png")));
/* 217 */     this.botonBuscarDeNuevo.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 219 */         BusquedaProductosJDialog.this.botonBuscarDeNuevoActionPerformed(evt);
/*     */       }
/*     */     });
/* 223 */     this.tabla.setSelectionMode(0);
/* 224 */     this.jScrollPane1.setViewportView(this.tabla);
/*     */ 
/* 226 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 227 */     this.jPanel1.setLayout(jPanel1Layout);
/* 228 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jScrollPane1, -2, 429, -2).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.campoBusqueda, -2, 255, -2).addGap(18, 18, 18).addComponent(this.botonBuscarDeNuevo))).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanel2, -2, -1, -2).addGap(6, 6, 6).addComponent(this.jEditorKeys1, -2, -1, -2).addContainerGap(-1, 32767)));
/*     */ 
/* 244 */     jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGap(9, 9, 9).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.campoBusqueda, -2, -1, -2).addComponent(this.botonBuscarDeNuevo)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jEditorKeys1, -2, -1, -2).addComponent(this.jPanel2, -2, -1, -2).addComponent(this.jScrollPane1, -2, 293, -2)).addContainerGap()));
/*     */ 
/* 259 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 260 */     getContentPane().setLayout(layout);
/* 261 */     layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -2, -1, -2).addContainerGap(-1, 32767)));
/*     */ 
/* 268 */     layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -2, -1, -2).addContainerGap(-1, 32767)));
/*     */ 
/* 276 */     pack();
/*     */   }
/*     */ 
/*     */   private void campoBusquedaKeyPressed(KeyEvent evt)
/*     */   {
/*     */   }
/*     */ 
/*     */   private void botonSeleccionarActionPerformed(ActionEvent evt) {
/* 284 */     asignarProductoSeleccionado();
/* 285 */     dispose();
/*     */   }
/*     */ 
/*     */   private void InicioActionPerformed(ActionEvent evt) {
/* 289 */     this.tabla.changeSelection(0, 0, false, false);
/*     */   }
/*     */ 
/*     */   private void arribaActionPerformed(ActionEvent evt) {
/* 293 */     if (this.tabla.getSelectedRow() != -1) {
/* 294 */       int rowSelected = this.tabla.getSelectedRow();
/* 295 */       if (rowSelected > 0) {
/* 296 */         rowSelected--;
/* 297 */         this.tabla.changeSelection(rowSelected, 0, false, false);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void abajoActionPerformed(ActionEvent evt) {
/* 303 */     if (this.tabla.getSelectedRow() != -1) {
/* 304 */       int rowSelected = this.tabla.getSelectedRow();
/* 305 */       if (rowSelected < this.tabla.getRowCount() - 1) {
/* 306 */         rowSelected++;
/* 307 */         this.tabla.changeSelection(rowSelected, 0, false, false);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void finActionPerformed(ActionEvent evt) {
/* 313 */     this.tabla.changeSelection(this.tabla.getRowCount() - 1, 0, false, false);
/*     */   }
/*     */ 
/*     */   private void botonBuscarDeNuevoActionPerformed(ActionEvent evt) {
/* 317 */     String cadena = this.campoBusqueda.getText();
/* 318 */     busquedaEnLista(cadena);
/*     */   }
/*     */ 
/*     */   private void busquedaEnLista(String texto) {
/* 322 */     if ((texto == null) || (texto.equals(""))) {
/* 323 */       texto = "";
/*     */     }
/* 325 */     ArrayList listado = new Busqueda(Inicio.getcAlmacen(), texto).getListado();
/* 326 */     this.lista.clear();
/* 327 */     for (Objeto objeto :(List<Objeto>)  listado) {
/* 328 */       this.lista.add(objeto);
/*     */     }
/* 330 */     this.modelo.fireTableDataChanged();
/* 331 */     this.tabla.repaint();
/* 332 */     this.tabla.changeSelection(0, 0, false, false);
/*     */   }
/*     */ 
/*     */   private void asignarProductoSeleccionado() {
/* 336 */     int indice = this.tabla.getSelectedRow();
/* 337 */     if (indice != -1) {
/* 338 */       int columna = 0;
/* 339 */       while ((columna < this.tabla.getColumnCount()) && 
/* 340 */         (!this.tabla.getColumnName(columna).equals(Mensajes.getString("referencia")))) {
/* 339 */         columna++;
/*     */       }
/*     */ 
/* 344 */       this.referencia = this.tabla.getValueAt(indice, columna).toString();
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getObjetoSeleccionado() {
/* 349 */     return this.referencia;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.view.BusquedaProductosJDialog
 * JD-Core Version:    0.6.2
 */