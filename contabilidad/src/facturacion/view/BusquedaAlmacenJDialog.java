/*     */ package facturacion.view;
/*     */ 
/*     */ import contaes.auxiliarTablas.DerechaColorRenderer;
/*     */ import contaes.auxiliarTablas.GeneralColorRenderer;
/*     */ import contaes.auxiliarTablas.ImporteColorRenderer;
/*     */ import contaes.auxiliarTablas.TableSorter;
/*     */ import contaes.manejoDatos.auxiliar.GrabarFichero;
/*     */ import facturacion.control.AlmacenControl;
/*     */ import facturacion.control.BusquedaProductosAlmacen;
/*     */ import facturacion.model.ModeloTablaBusquedaAlmacen;
/*     */ import facturacion.model.ObjetoBusquedaAlmacen;
/*     */ import facturacion.model.Producto;
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Frame;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.io.File;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Formatter;
import java.util.List;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JFileChooser;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.table.TableColumn;
/*     */ import javax.swing.table.TableColumnModel;
/*     */ import javax.swing.table.TableModel;
/*     */ 
/*     */ public class BusquedaAlmacenJDialog extends JDialog
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  43 */   private JPanel jContentPane = null;
/*  44 */   private JPanel jPanel = null;
/*  45 */   private JTextField cBusqueda = null;
/*  46 */   private JButton bBuscar = null;
/*  47 */   private JPanel jPanel1 = null;
/*  48 */   private JButton bVolver = null;
/*  49 */   private JButton jButton1 = null;
/*  50 */   private JScrollPane jScrollPane = null;
/*  51 */   private JTable tabla = null;
/*  52 */   private ModeloTablaBusquedaAlmacen modelo = null;
/*  53 */   private TableSorter tablaOrdenada = null;
/*     */   boolean venta;
/*  56 */   String resultado = null;
/*  57 */   Producto producto = null;
/*  58 */   private ArrayList<ObjetoBusquedaAlmacen> lista = new ArrayList();
/*     */ 
/*     */   public BusquedaAlmacenJDialog(Frame owner, boolean venta)
/*     */   {
/*  64 */     super(owner);
/*  65 */     this.venta = venta;
/*  66 */     initialize();
/*     */   }
/*     */ 
/*     */   public static Producto obtenerObjeto(Frame owner, boolean venta) {
/*  70 */     BusquedaAlmacenJDialog dlg = new BusquedaAlmacenJDialog(owner, venta);
/*  71 */     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/*  72 */     Dimension frameSize = dlg.getSize();
/*  73 */     if (frameSize.height > screenSize.height) {
/*  74 */       frameSize.height = screenSize.height;
/*     */     }
/*  76 */     if (frameSize.width > screenSize.width) {
/*  77 */       frameSize.width = screenSize.width;
/*     */     }
/*  79 */     dlg.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
/*     */ 
/*  82 */     dlg.setVisible(true);
/*  83 */     return dlg.producto;
/*     */   }
/*     */ 
/*     */   private void initialize()
/*     */   {
/*  92 */     setSize(600, 360);
/*  93 */     setModal(true);
/*  94 */     setContentPane(getJContentPane());
/*     */   }
/*     */ 
/*     */   private JPanel getJContentPane()
/*     */   {
/* 103 */     if (this.jContentPane == null) {
/* 104 */       this.jContentPane = new JPanel();
/* 105 */       this.jContentPane.setBackground(Color.white);
/* 106 */       this.jContentPane.setLayout(new BorderLayout());
/* 107 */       this.jContentPane.add(getJPanel(), "North");
/* 108 */       this.jContentPane.add(getJPanel1(), "South");
/* 109 */       this.jContentPane.add(getJScrollPane(), "Center");
/*     */     }
/* 111 */     return this.jContentPane;
/*     */   }
/*     */ 
/*     */   private JPanel getJPanel()
/*     */   {
/* 120 */     if (this.jPanel == null) {
/* 121 */       this.jPanel = new JPanel();
/* 122 */       this.jPanel.setLayout(null);
/* 123 */       this.jPanel.setPreferredSize(new Dimension(25, 35));
/* 124 */       this.jPanel.setBackground(Color.white);
/* 125 */       this.jPanel.add(getCBusqueda(), null);
/* 126 */       this.jPanel.add(getBBuscar(), null);
/*     */     }
/* 128 */     return this.jPanel;
/*     */   }
/*     */ 
/*     */   private JTextField getCBusqueda()
/*     */   {
/* 137 */     if (this.cBusqueda == null) {
/* 138 */       this.cBusqueda = new JTextField();
/* 139 */       this.cBusqueda.setBounds(new Rectangle(15, 7, 170, 19));
/* 140 */       this.cBusqueda.setToolTipText(Mensajes.getString("campoBusquedatt2"));
/* 141 */       this.cBusqueda.addActionListener(new ActionListener() {
/*     */         public void actionPerformed(ActionEvent e) {
/* 143 */           BusquedaAlmacenJDialog.this.buscar();
/*     */         }
/*     */       });
/*     */     }
/* 147 */     return this.cBusqueda;
/*     */   }
/*     */ 
/*     */   private JButton getBBuscar()
/*     */   {
/* 156 */     if (this.bBuscar == null) {
/* 157 */       this.bBuscar = new JButton();
/* 158 */       this.bBuscar.setBounds(new Rectangle(195, 5, 90, 23));
/* 159 */       this.bBuscar.setText(Mensajes.getString("buscar"));
/* 160 */       this.bBuscar.addActionListener(new ActionListener() {
/*     */         public void actionPerformed(ActionEvent e) {
/* 162 */           BusquedaAlmacenJDialog.this.buscar();
/*     */         }
/*     */       });
/*     */     }
/* 166 */     return this.bBuscar;
/*     */   }
/*     */ 
/*     */   private void buscar() {
/* 170 */     String texto = this.cBusqueda.getText();
/*     */ 
/* 172 */     if ((texto == null) || (texto.equals("")))
/* 173 */       texto = "";
/* 174 */     ArrayList listado = new BusquedaProductosAlmacen(texto).getListado();
/* 175 */     this.lista.clear();
/* 176 */     for (ObjetoBusquedaAlmacen objeto : (List<ObjetoBusquedaAlmacen>)listado)
/* 177 */       this.lista.add(objeto);
/* 178 */     this.modelo.fireTableDataChanged();
/* 179 */     this.tabla.repaint();
/*     */   }
/*     */ 
/*     */   private JPanel getJPanel1()
/*     */   {
/* 188 */     if (this.jPanel1 == null) {
/* 189 */       this.jPanel1 = new JPanel();
/* 190 */       this.jPanel1.setLayout(null);
/* 191 */       this.jPanel1.setBackground(Color.white);
/* 192 */       this.jPanel1.setPreferredSize(new Dimension(25, 35));
/* 193 */       this.jPanel1.add(getBVolver(), null);
/* 194 */       this.jPanel1.add(getJButton1(), null);
/*     */     }
/* 196 */     return this.jPanel1;
/*     */   }
/*     */ 
/*     */   private JButton getBVolver()
/*     */   {
/* 205 */     if (this.bVolver == null) {
/* 206 */       this.bVolver = new JButton();
/* 207 */       this.bVolver.setBounds(new Rectangle(35, 5, 90, 23));
/* 208 */       this.bVolver.setText(Mensajes.getString("volver"));
/* 209 */       this.bVolver.addActionListener(new ActionListener() {
/*     */         public void actionPerformed(ActionEvent e) {
/* 211 */           BusquedaAlmacenJDialog.this.volver(BusquedaAlmacenJDialog.this.tabla.getSelectedRow());
/*     */         }
/*     */       });
/*     */     }
/* 215 */     return this.bVolver;
/*     */   }
/*     */ 
/*     */   private void volver(int indice)
/*     */   {
/* 220 */     if (indice != -1) {
/* 221 */       int columna = 0;
/* 222 */       while ((columna < this.tabla.getColumnCount()) && 
/* 223 */         (!this.tabla.getColumnName(columna).equals(Mensajes.getString("referencia")))) {
/* 222 */         columna++;
/*     */       }
/*     */ 
/* 226 */       this.resultado = this.tabla.getValueAt(indice, columna).toString();
/* 227 */       AlmacenControl aC = new AlmacenControl();
/* 228 */       this.producto = aC.getProducto(this.resultado, this.venta);
/* 229 */       aC.cerrarConexion();
/*     */     }
/* 231 */     dispose();
/*     */   }
/*     */ 
/*     */   private JButton getJButton1()
/*     */   {
/* 240 */     if (this.jButton1 == null) {
/* 241 */       this.jButton1 = new JButton();
/* 242 */       this.jButton1.setBounds(new Rectangle(140, 2, 35, 29));
/* 243 */       this.jButton1.setIcon(new ImageIcon(getClass().getResource("/almacen2/iconos/asTXT.png")));
/* 244 */       this.jButton1.setToolTipText(Mensajes.getString("guardarTexto"));
/* 245 */       this.jButton1.addActionListener(new ActionListener() {
/*     */         public void actionPerformed(ActionEvent e) {
/* 247 */           BusquedaAlmacenJDialog.this.guardarLista();
/*     */         }
/*     */       });
/*     */     }
/* 251 */     return this.jButton1;
/*     */   }
/*     */ 
/*     */   private JScrollPane getJScrollPane()
/*     */   {
/* 260 */     if (this.jScrollPane == null) {
/* 261 */       this.jScrollPane = new JScrollPane();
/* 262 */       this.jScrollPane.setBackground(Color.white);
/* 263 */       this.jScrollPane.setViewportView(getTabla());
/*     */     }
/* 265 */     return this.jScrollPane;
/*     */   }
/*     */ 
/*     */   private JTable getTabla()
/*     */   {
/* 274 */     if (this.tabla == null) {
/* 275 */       this.tabla = new JTable(getTablaOrdenada());
/* 276 */       this.tabla.addMouseListener(new MouseAdapter()
/*     */       {
/*     */         public void mouseClicked(MouseEvent evt) {
/* 279 */           BusquedaAlmacenJDialog.this.tablaMouseClicked(evt);
/*     */         }
/*     */       });
/* 282 */       this.tabla.setBackground(Color.white);
/* 283 */       this.tablaOrdenada.setTableHeader(this.tabla.getTableHeader());
/* 284 */       int anchoTabla = 430;
/* 285 */       TableColumn columna = this.tabla.getColumnModel().getColumn(0);
/* 286 */       columna.setPreferredWidth((int)(anchoTabla * 0.15D));
/* 287 */       columna = this.tabla.getColumnModel().getColumn(1);
/* 288 */       columna.setPreferredWidth((int)(anchoTabla * 0.15D));
/* 289 */       columna = this.tabla.getColumnModel().getColumn(2);
/* 290 */       columna.setPreferredWidth((int)(anchoTabla * 0.15D));
/* 291 */       columna = this.tabla.getColumnModel().getColumn(3);
/* 292 */       columna.setPreferredWidth((int)(anchoTabla * 0.25D));
/* 293 */       columna = this.tabla.getColumnModel().getColumn(4);
/* 294 */       columna.setPreferredWidth((int)(anchoTabla * 0.1D));
/* 295 */       columna.setCellRenderer(new ImporteColorRenderer());
/* 296 */       columna = this.tabla.getColumnModel().getColumn(5);
/* 297 */       columna.setPreferredWidth((int)(anchoTabla * 0.1D));
/* 298 */       columna.setCellRenderer(new ImporteColorRenderer());
/* 299 */       columna = this.tabla.getColumnModel().getColumn(6);
/* 300 */       columna.setPreferredWidth((int)(anchoTabla * 0.1D));
/* 301 */       columna.setCellRenderer(new DerechaColorRenderer());
/* 302 */       this.tabla.setDefaultRenderer(Object.class, new GeneralColorRenderer());
/*     */     }
/* 304 */     return this.tabla;
/*     */   }
/*     */ 
/*     */   private void tablaMouseClicked(MouseEvent evt) {
/* 308 */     if (evt.getClickCount() == 2) {
/* 309 */       int fila = this.tabla.rowAtPoint(evt.getPoint());
/* 310 */       if ((fila >= 0) && (fila < this.tabla.getModel().getRowCount()))
/* 311 */         volver(fila);
/*     */     }
/*     */   }
/*     */ 
/*     */   private ModeloTablaBusquedaAlmacen getModelo()
/*     */   {
/* 317 */     if (this.modelo == null) {
/* 318 */       this.modelo = new ModeloTablaBusquedaAlmacen(this.lista);
/*     */     }
/* 320 */     return this.modelo;
/*     */   }
/*     */ 
/*     */   private TableSorter getTablaOrdenada() {
/* 324 */     if (this.tablaOrdenada == null) {
/* 325 */       this.tablaOrdenada = new TableSorter();
/* 326 */       this.tablaOrdenada.setTableModel(getModelo());
/*     */     }
/* 328 */     return this.tablaOrdenada;
/*     */   }
/*     */ 
/*     */   private void guardarLista() {
/* 332 */     JFileChooser fc = new JFileChooser();
/* 333 */     int retorno = fc.showSaveDialog(this);
/* 334 */     if (retorno == 0) {
/* 335 */       File archivo = fc.getSelectedFile();
/* 336 */       GrabarFichero salida = new GrabarFichero(archivo);
/* 337 */       StringBuilder sb = new StringBuilder();
/* 338 */       Formatter formater = new Formatter(sb);
/* 339 */       formater.format("%-20s %-20s %-20s %-80s %10s %10s %5s\n", new Object[] { Mensajes.getString("referencia"), Mensajes.getString("familia"), Mensajes.getString("proveedor"), Mensajes.getString("descripcion"), Mensajes.getString("coste"), Mensajes.getString("pvp"), Mensajes.getString("stck") });
/*     */ 
/* 342 */       for (int fila = 0; fila < this.tabla.getRowCount(); fila++) {
/* 343 */         formater.format("%-20s %-20s %-20s %-80s %,10.2f %,10.2f %5s\n", new Object[] { this.tabla.getValueAt(fila, 0), this.tabla.getValueAt(fila, 1), this.tabla.getValueAt(fila, 2), this.tabla.getValueAt(fila, 3), this.tabla.getValueAt(fila, 4), this.tabla.getValueAt(fila, 5), this.tabla.getValueAt(fila, 6) });
/*     */       }
/*     */ 
/* 352 */       salida.insertar(sb.toString());
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     facturacion.view.BusquedaAlmacenJDialog
 * JD-Core Version:    0.6.2
 */