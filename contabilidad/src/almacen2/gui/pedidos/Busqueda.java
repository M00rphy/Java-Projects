/*     */ package almacen2.gui.pedidos;
/*     */ 
/*     */ import almacen2.data.FPObject;
/*     */ import almacen2.data.ManejadorListasInicio;
/*     */ import almacen2.data.MySQLConection;
/*     */ import almacen2.data.pedidos.BusquedaObject;
/*     */ import almacen2.data.pedidos.BusquedaTableModel;
/*     */ import almacen2.data.pedidos.PedidoObject;
/*     */ import contaes.auxiliarTablas.DerechaColorRenderer;
/*     */ import contaes.auxiliarTablas.GeneralColorRenderer;
/*     */ import contaes.auxiliarTablas.ImporteColorRenderer;
/*     */ import contaes.auxiliarTablas.TableSorter;
/*     */ import contaes.manejoDatos.auxiliar.AddExtension;
/*     */ import contaes.manejoDatos.auxiliar.GrabarFichero;
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
/*     */ public class Busqueda extends JDialog
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  45 */   private JPanel jContentPane = null;
/*  46 */   private JPanel jPanel = null;
/*  47 */   private JTextField cBusqueda = null;
/*  48 */   private JButton bBuscar = null;
/*  49 */   private JPanel jPanel1 = null;
/*  50 */   private JButton bVolver = null;
/*  51 */   private JButton jButton1 = null;
/*  52 */   private JScrollPane jScrollPane = null;
/*  53 */   private JTable tabla = null;
/*  54 */   private BusquedaTableModel modelo = null;
/*  55 */   private TableSorter tablaOrdenada = null;
/*     */ 
/*  57 */   MySQLConection con = null;
/*     */ 
/*  59 */   PedidoObject resultado = null;
/*     */   FPObject[] proveedores;
/*  63 */   private ArrayList<BusquedaObject> lista = new ArrayList();
/*     */   private boolean compras;
/*     */ 
/*     */   public Busqueda(Frame owner, MySQLConection con, boolean compras)
/*     */   {
/*  71 */     super(owner);
/*  72 */     this.con = con;
/*  73 */     this.compras = compras;
/*  74 */     initialize();
/*     */   }
/*     */ 
/*     */   public static PedidoObject obtenerObjeto(Frame owner, MySQLConection con, boolean compras) {
/*  78 */     Busqueda dlg = new Busqueda(owner, con, compras);
/*  79 */     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/*  80 */     Dimension frameSize = dlg.getSize();
/*  81 */     if (frameSize.height > screenSize.height) {
/*  82 */       frameSize.height = screenSize.height;
/*     */     }
/*  84 */     if (frameSize.width > screenSize.width) {
/*  85 */       frameSize.width = screenSize.width;
/*     */     }
/*  87 */     dlg.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
/*     */ 
/*  89 */     dlg.pack();
/*  90 */     dlg.setVisible(true);
/*  91 */     return dlg.resultado;
/*     */   }
/*     */ 
/*     */   private void initialize()
/*     */   {
/* 100 */     this.proveedores = new ManejadorListasInicio(this.con).getProveedores();
/* 101 */     setSize(450, 360);
/* 102 */     setModal(true);
/* 103 */     setContentPane(getJContentPane());
/*     */   }
/*     */ 
/*     */   private JPanel getJContentPane()
/*     */   {
/* 112 */     if (this.jContentPane == null) {
/* 113 */       this.jContentPane = new JPanel();
/* 114 */       this.jContentPane.setBackground(Color.white);
/* 115 */       this.jContentPane.setLayout(new BorderLayout());
/* 116 */       this.jContentPane.add(getJPanel(), "North");
/* 117 */       this.jContentPane.add(getJPanel1(), "South");
/* 118 */       this.jContentPane.add(getJScrollPane(), "Center");
/*     */     }
/* 120 */     return this.jContentPane;
/*     */   }
/*     */ 
/*     */   private JPanel getJPanel()
/*     */   {
/* 129 */     if (this.jPanel == null) {
/* 130 */       this.jPanel = new JPanel();
/* 131 */       this.jPanel.setLayout(null);
/* 132 */       this.jPanel.setPreferredSize(new Dimension(25, 35));
/* 133 */       this.jPanel.setBackground(Color.white);
/* 134 */       this.jPanel.add(getCBusqueda(), null);
/* 135 */       this.jPanel.add(getBBuscar(), null);
/*     */     }
/* 137 */     return this.jPanel;
/*     */   }
/*     */ 
/*     */   private JTextField getCBusqueda()
/*     */   {
/* 146 */     if (this.cBusqueda == null) {
/* 147 */       this.cBusqueda = new JTextField();
/* 148 */       this.cBusqueda.setBounds(new Rectangle(15, 7, 170, 19));
/* 149 */       this.cBusqueda.setToolTipText(Mensajes.getString("campoBusquedatt"));
/* 150 */       this.cBusqueda.addActionListener(new ActionListener() {
/*     */         public void actionPerformed(ActionEvent e) {
/* 152 */           Busqueda.this.buscar();
/*     */         }
/*     */       });
/*     */     }
/* 156 */     return this.cBusqueda;
/*     */   }
/*     */ 
/*     */   private JButton getBBuscar()
/*     */   {
/* 165 */     if (this.bBuscar == null) {
/* 166 */       this.bBuscar = new JButton();
/* 167 */       this.bBuscar.setBounds(new Rectangle(195, 5, 90, 23));
/* 168 */       this.bBuscar.setText(Mensajes.getString("buscar"));
/* 169 */       this.bBuscar.addActionListener(new ActionListener() {
/*     */         public void actionPerformed(ActionEvent e) {
/* 171 */           Busqueda.this.buscar();
/*     */         }
/*     */       });
/*     */     }
/* 175 */     return this.bBuscar;
/*     */   }
/*     */ 
/*     */   private void buscar() {
/* 179 */     String texto = this.cBusqueda.getText();
/*     */ 
/* 181 */     if ((texto == null) || (texto.equals("")))
/* 182 */       texto = "";
/* 183 */     ArrayList listado = new almacen2.data.pedidos.Busqueda(this.con, texto, this.compras).getListado();
/* 184 */     this.lista.clear();
/* 185 */     for (BusquedaObject objeto : (List<BusquedaObject>)listado)
/* 186 */       this.lista.add(objeto);
/* 187 */     this.modelo.fireTableDataChanged();
/* 188 */     this.tabla.repaint();
/*     */   }
/*     */ 
/*     */   private JPanel getJPanel1()
/*     */   {
/* 197 */     if (this.jPanel1 == null) {
/* 198 */       this.jPanel1 = new JPanel();
/* 199 */       this.jPanel1.setLayout(null);
/* 200 */       this.jPanel1.setBackground(Color.white);
/* 201 */       this.jPanel1.setPreferredSize(new Dimension(25, 35));
/* 202 */       this.jPanel1.add(getBVolver(), null);
/* 203 */       this.jPanel1.add(getJButton1(), null);
/*     */     }
/* 205 */     return this.jPanel1;
/*     */   }
/*     */ 
/*     */   private JButton getBVolver()
/*     */   {
/* 214 */     if (this.bVolver == null) {
/* 215 */       this.bVolver = new JButton();
/* 216 */       this.bVolver.setBounds(new Rectangle(35, 5, 90, 23));
/* 217 */       this.bVolver.setText(Mensajes.getString("volver"));
/* 218 */       this.bVolver.addActionListener(new ActionListener() {
/*     */         public void actionPerformed(ActionEvent e) {
/* 220 */           Busqueda.this.volver();
/*     */         }
/*     */       });
/*     */     }
/* 224 */     return this.bVolver;
/*     */   }
/*     */ 
/*     */   private void volver() {
/* 228 */     int indice = this.tabla.getSelectedRow();
/* 229 */     if (indice != -1) {
/* 230 */       int index = 0;
/* 231 */       int idProveedor = 0;
/* 232 */       if (this.compras) {
/* 233 */         while ((index < this.proveedores.length) && (!((BusquedaObject)this.lista.get(indice)).getProveedor().equals(this.proveedores[index].getNombre())))
/* 234 */           index++;
/* 235 */         idProveedor = this.proveedores[index].getId();
/*     */       }
/*     */       else {
/* 238 */         String idP = (String)this.tabla.getValueAt(indice, 3);
/* 239 */         idProveedor = Integer.parseInt(idP);
/*     */       }
/* 241 */       Integer id = (Integer)this.tabla.getValueAt(indice, 0);
/* 242 */       this.resultado = new PedidoObject(id, ((BusquedaObject)this.lista.get(indice)).getCodigo(), ((BusquedaObject)this.lista.get(indice)).getFecha(), idProveedor);
/*     */     }
/* 244 */     dispose();
/*     */   }
/*     */ 
/*     */   private JButton getJButton1() {
/* 248 */     if (this.jButton1 == null) {
/* 249 */       this.jButton1 = new JButton();
/* 250 */       this.jButton1.setBounds(new Rectangle(140, 2, 35, 29));
/* 251 */       this.jButton1.setIcon(new ImageIcon(getClass().getResource("/almacen2/iconos/asTXT.png")));
/* 252 */       this.jButton1.setToolTipText(Mensajes.getString("guardarTexto"));
/* 253 */       this.jButton1.addActionListener(new ActionListener() {
/*     */         public void actionPerformed(ActionEvent e) {
/* 255 */           Busqueda.this.guardarLista();
/*     */         }
/*     */       });
/*     */     }
/* 259 */     return this.jButton1;
/*     */   }
/*     */ 
/*     */   private JScrollPane getJScrollPane()
/*     */   {
/* 268 */     if (this.jScrollPane == null) {
/* 269 */       this.jScrollPane = new JScrollPane();
/* 270 */       this.jScrollPane.setBackground(Color.white);
/* 271 */       this.jScrollPane.setViewportView(getTabla());
/*     */     }
/* 273 */     return this.jScrollPane;
/*     */   }
/*     */ 
/*     */   private JTable getTabla()
/*     */   {
/* 282 */     if (this.tabla == null) {
/* 283 */       this.tabla = new JTable(getTablaOrdenada());
/* 284 */       this.tabla.addMouseListener(new MouseAdapter()
/*     */       {
/*     */         public void mouseClicked(MouseEvent evt) {
/* 287 */           if (evt.getClickCount() == 2) {
/* 288 */             int fila = Busqueda.this.tabla.rowAtPoint(evt.getPoint());
/* 289 */             if ((fila >= 0) && (fila < Busqueda.this.tabla.getModel().getRowCount()))
/* 290 */               Busqueda.this.volver();
/*     */           }
/*     */         }
/*     */       });
/* 295 */       this.tabla.setBackground(Color.white);
/* 296 */       this.tablaOrdenada.setTableHeader(this.tabla.getTableHeader());
/* 297 */       int anchoTabla = 430;
/* 298 */       TableColumn columna = this.tabla.getColumnModel().getColumn(0);
/* 299 */       columna.setPreferredWidth((int)(anchoTabla * 0.02D));
/* 300 */       columna = this.tabla.getColumnModel().getColumn(1);
/* 301 */       columna.setPreferredWidth((int)(anchoTabla * 0.22D));
/* 302 */       columna = this.tabla.getColumnModel().getColumn(2);
/* 303 */       columna.setPreferredWidth((int)(anchoTabla * 0.18D));
/* 304 */       columna = this.tabla.getColumnModel().getColumn(3);
/* 305 */       columna.setPreferredWidth((int)(anchoTabla * 0.25D));
/* 306 */       columna = this.tabla.getColumnModel().getColumn(4);
/* 307 */       columna.setPreferredWidth((int)(anchoTabla * 0.15D));
/* 308 */       columna.setCellRenderer(new DerechaColorRenderer());
/* 309 */       columna = this.tabla.getColumnModel().getColumn(5);
/* 310 */       columna.setPreferredWidth((int)(anchoTabla * 0.18D));
/* 311 */       columna.setCellRenderer(new ImporteColorRenderer());
/* 312 */       this.tabla.setDefaultRenderer(Object.class, new GeneralColorRenderer());
/*     */     }
/* 314 */     return this.tabla;
/*     */   }
/*     */ 
/*     */   private BusquedaTableModel getModelo() {
/* 318 */     if (this.modelo == null) {
/* 319 */       this.modelo = new BusquedaTableModel(this.lista);
/*     */     }
/* 321 */     return this.modelo;
/*     */   }
/*     */ 
/*     */   private TableSorter getTablaOrdenada() {
/* 325 */     if (this.tablaOrdenada == null) {
/* 326 */       this.tablaOrdenada = new TableSorter();
/* 327 */       this.tablaOrdenada.setTableModel(getModelo());
/*     */     }
/* 329 */     return this.tablaOrdenada;
/*     */   }
/*     */ 
/*     */   private void guardarLista() {
/* 333 */     JFileChooser fc = new JFileChooser();
/* 334 */     fc.setSelectedFile(new File("busqueda.txt"));
/* 335 */     int retorno = fc.showSaveDialog(this);
/* 336 */     if (retorno == 0) {
/* 337 */       File archivo = fc.getSelectedFile();
/* 338 */       archivo = AddExtension.txt(archivo);
/* 339 */       GrabarFichero salida = new GrabarFichero(archivo);
/* 340 */       StringBuilder sb = new StringBuilder();
/* 341 */       Formatter formater = new Formatter(sb);
/* 342 */       formater.format("%-20s %-10s %-20s %8s %10s\n", new Object[] { Mensajes.getString("codigo"), Mensajes.getString("fecha"), Mensajes.getString("proveedor"), Mensajes.getString("unidades"), Mensajes.getString("importe") });
/*     */ 
/* 345 */       for (int fila = 0; fila < this.tabla.getRowCount(); fila++) {
/* 346 */         formater.format("%-20s %-10s %-20s %8s %,10.2f\n", new Object[] { this.tabla.getValueAt(fila, 1), this.tabla.getValueAt(fila, 2), this.tabla.getValueAt(fila, 3), this.tabla.getValueAt(fila, 4), this.tabla.getValueAt(fila, 5) });
/*     */       }
/*     */ 
/* 353 */       salida.insertar(sb.toString());
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.gui.pedidos.Busqueda
 * JD-Core Version:    0.6.2
 */