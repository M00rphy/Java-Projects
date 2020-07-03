/*     */ package almacen2.gui;
/*     */ 
/*     */ import almacen2.data.MySQLConection;
/*     */ import almacen2.data.busqueda.ModeloTabla;
/*     */ import almacen2.data.busqueda.Objeto;
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
/*  42 */   private JPanel jContentPane = null;
/*  43 */   private JPanel jPanel = null;
/*  44 */   private JTextField cBusqueda = null;
/*  45 */   private JButton bBuscar = null;
/*  46 */   private JPanel jPanel1 = null;
/*  47 */   private JButton bVolver = null;
/*  48 */   private JButton jButton1 = null;
/*  49 */   private JScrollPane jScrollPane = null;
/*  50 */   private JTable tabla = null;
/*  51 */   private ModeloTabla modelo = null;
/*  52 */   private TableSorter tablaOrdenada = null;
/*     */ 
/*  54 */   MySQLConection con = null;
/*  55 */   String resultado = null;
/*  56 */   private ArrayList<Objeto> lista = new ArrayList();
/*     */ 
/*     */   public Busqueda(Frame owner, MySQLConection con)
/*     */   {
/*  62 */     super(owner);
/*  63 */     this.con = con;
/*  64 */     initialize();
/*     */   }
/*     */ 
/*     */   public static String obtenerObjeto(Frame owner, MySQLConection con) {
/*  68 */     Busqueda dlg = new Busqueda(owner, con);
/*  69 */     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/*  70 */     Dimension frameSize = dlg.getSize();
/*  71 */     if (frameSize.height > screenSize.height) {
/*  72 */       frameSize.height = screenSize.height;
/*     */     }
/*  74 */     if (frameSize.width > screenSize.width) {
/*  75 */       frameSize.width = screenSize.width;
/*     */     }
/*  77 */     dlg.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
/*     */ 
/*  80 */     dlg.setVisible(true);
/*  81 */     return dlg.resultado;
/*     */   }
/*     */ 
/*     */   private void initialize()
/*     */   {
/*  90 */     setSize(600, 360);
/*  91 */     setModal(true);
/*  92 */     setContentPane(getJContentPane());
/*     */   }
/*     */ 
/*     */   private JPanel getJContentPane()
/*     */   {
/* 101 */     if (this.jContentPane == null) {
/* 102 */       this.jContentPane = new JPanel();
/* 103 */       this.jContentPane.setBackground(Color.white);
/* 104 */       this.jContentPane.setLayout(new BorderLayout());
/* 105 */       this.jContentPane.add(getJPanel(), "North");
/* 106 */       this.jContentPane.add(getJPanel1(), "South");
/* 107 */       this.jContentPane.add(getJScrollPane(), "Center");
/*     */     }
/* 109 */     return this.jContentPane;
/*     */   }
/*     */ 
/*     */   private JPanel getJPanel()
/*     */   {
/* 118 */     if (this.jPanel == null) {
/* 119 */       this.jPanel = new JPanel();
/* 120 */       this.jPanel.setLayout(null);
/* 121 */       this.jPanel.setPreferredSize(new Dimension(25, 35));
/* 122 */       this.jPanel.setBackground(Color.white);
/* 123 */       this.jPanel.add(getCBusqueda(), null);
/* 124 */       this.jPanel.add(getBBuscar(), null);
/*     */     }
/* 126 */     return this.jPanel;
/*     */   }
/*     */ 
/*     */   private JTextField getCBusqueda()
/*     */   {
/* 135 */     if (this.cBusqueda == null) {
/* 136 */       this.cBusqueda = new JTextField();
/* 137 */       this.cBusqueda.setBounds(new Rectangle(15, 7, 170, 19));
/* 138 */       this.cBusqueda.setToolTipText(Mensajes.getString("campoBusquedatt2"));
/* 139 */       this.cBusqueda.addActionListener(new ActionListener() {
/*     */         public void actionPerformed(ActionEvent e) {
/* 141 */           Busqueda.this.buscar();
/*     */         }
/*     */       });
/*     */     }
/* 145 */     return this.cBusqueda;
/*     */   }
/*     */ 
/*     */   private JButton getBBuscar()
/*     */   {
/* 154 */     if (this.bBuscar == null) {
/* 155 */       this.bBuscar = new JButton();
/* 156 */       this.bBuscar.setBounds(new Rectangle(195, 5, 90, 23));
/* 157 */       this.bBuscar.setText(Mensajes.getString("buscar"));
/* 158 */       this.bBuscar.addActionListener(new ActionListener() {
/*     */         public void actionPerformed(ActionEvent e) {
/* 160 */           Busqueda.this.buscar();
/*     */         }
/*     */       });
/*     */     }
/* 164 */     return this.bBuscar;
/*     */   }
/*     */ 
/*     */   private void buscar() {
/* 168 */     String texto = this.cBusqueda.getText();
/*     */ 
/* 170 */     if ((texto == null) || (texto.equals("")))
/* 171 */       texto = "";
/* 172 */     ArrayList listado = new almacen2.data.busqueda.Busqueda(this.con, texto).getListado();
/* 173 */     this.lista.clear();
/* 174 */     for (Objeto objeto :(List<Objeto>) listado)
/* 175 */       this.lista.add(objeto);
/* 176 */     this.modelo.fireTableDataChanged();
/* 177 */     this.tabla.repaint();
/*     */   }
/*     */ 
/*     */   private JPanel getJPanel1()
/*     */   {
/* 186 */     if (this.jPanel1 == null) {
/* 187 */       this.jPanel1 = new JPanel();
/* 188 */       this.jPanel1.setLayout(null);
/* 189 */       this.jPanel1.setBackground(Color.white);
/* 190 */       this.jPanel1.setPreferredSize(new Dimension(25, 35));
/* 191 */       this.jPanel1.add(getBVolver(), null);
/* 192 */       this.jPanel1.add(getJButton1(), null);
/*     */     }
/* 194 */     return this.jPanel1;
/*     */   }
/*     */ 
/*     */   private JButton getBVolver()
/*     */   {
/* 203 */     if (this.bVolver == null) {
/* 204 */       this.bVolver = new JButton();
/* 205 */       this.bVolver.setBounds(new Rectangle(35, 5, 90, 23));
/* 206 */       this.bVolver.setText(Mensajes.getString("volver"));
/* 207 */       this.bVolver.addActionListener(new ActionListener() {
/*     */         public void actionPerformed(ActionEvent e) {
/* 209 */           Busqueda.this.volver(Busqueda.this.tabla.getSelectedRow());
/*     */         }
/*     */       });
/*     */     }
/* 213 */     return this.bVolver;
/*     */   }
/*     */ 
/*     */   private void volver(int indice)
/*     */   {
/* 218 */     if (indice != -1) {
/* 219 */       int columna = 0;
/* 220 */       while ((columna < this.tabla.getColumnCount()) && 
/* 221 */         (!this.tabla.getColumnName(columna).equals(Mensajes.getString("referencia")))) {
/* 220 */         columna++;
/*     */       }
/*     */ 
/* 224 */       this.resultado = this.tabla.getValueAt(indice, columna).toString();
/*     */     }
/* 226 */     dispose();
/*     */   }
/*     */ 
/*     */   private JButton getJButton1()
/*     */   {
/* 235 */     if (this.jButton1 == null) {
/* 236 */       this.jButton1 = new JButton();
/* 237 */       this.jButton1.setBounds(new Rectangle(140, 2, 35, 29));
/* 238 */       this.jButton1.setIcon(new ImageIcon(getClass().getResource("/almacen2/iconos/asTXT.png")));
/* 239 */       this.jButton1.setToolTipText(Mensajes.getString("guardarTexto"));
/* 240 */       this.jButton1.addActionListener(new ActionListener() {
/*     */         public void actionPerformed(ActionEvent e) {
/* 242 */           Busqueda.this.guardarLista();
/*     */         }
/*     */       });
/*     */     }
/* 246 */     return this.jButton1;
/*     */   }
/*     */ 
/*     */   private JScrollPane getJScrollPane()
/*     */   {
/* 255 */     if (this.jScrollPane == null) {
/* 256 */       this.jScrollPane = new JScrollPane();
/* 257 */       this.jScrollPane.setBackground(Color.white);
/* 258 */       this.jScrollPane.setViewportView(getTabla());
/*     */     }
/* 260 */     return this.jScrollPane;
/*     */   }
/*     */ 
/*     */   private JTable getTabla()
/*     */   {
/* 269 */     if (this.tabla == null) {
/* 270 */       this.tabla = new JTable(getTablaOrdenada());
/* 271 */       this.tabla.addMouseListener(new MouseAdapter()
/*     */       {
/*     */         public void mouseClicked(MouseEvent evt) {
/* 274 */           if (evt.getClickCount() == 2) {
/* 275 */             int fila = Busqueda.this.tabla.rowAtPoint(evt.getPoint());
/* 276 */             if ((fila >= 0) && (fila < Busqueda.this.tabla.getModel().getRowCount()))
/* 277 */               Busqueda.this.volver(fila);
/*     */           }
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
/*     */   private ModeloTabla getModelo() {
/* 308 */     if (this.modelo == null) {
/* 309 */       this.modelo = new ModeloTabla(this.lista);
/*     */     }
/* 311 */     return this.modelo;
/*     */   }
/*     */ 
/*     */   private TableSorter getTablaOrdenada() {
/* 315 */     if (this.tablaOrdenada == null) {
/* 316 */       this.tablaOrdenada = new TableSorter();
/* 317 */       this.tablaOrdenada.setTableModel(getModelo());
/*     */     }
/* 319 */     return this.tablaOrdenada;
/*     */   }
/*     */ 
/*     */   private void guardarLista() {
/* 323 */     JFileChooser fc = new JFileChooser();
/* 324 */     fc.setSelectedFile(new File("busqueda.txt"));
/* 325 */     int retorno = fc.showSaveDialog(this);
/* 326 */     if (retorno == 0) {
/* 327 */       File archivo = fc.getSelectedFile();
/* 328 */       archivo = AddExtension.txt(archivo);
/* 329 */       GrabarFichero salida = new GrabarFichero(archivo);
/* 330 */       StringBuilder sb = new StringBuilder();
/* 331 */       Formatter formater = new Formatter(sb);
/* 332 */       formater.format("%-20s %-20s %-20s %-80s %10s %10s %5s\n", new Object[] { Mensajes.getString("referencia"), Mensajes.getString("familia"), Mensajes.getString("proveedor"), Mensajes.getString("descripcion"), Mensajes.getString("coste"), Mensajes.getString("pvp"), Mensajes.getString("stck") });
/*     */ 
/* 335 */       for (int fila = 0; fila < this.tabla.getRowCount(); fila++) {
/* 336 */         formater.format("%-20s %-20s %-20s %-80s %,10.2f %,10.2f %5s\n", new Object[] { this.tabla.getValueAt(fila, 0), this.tabla.getValueAt(fila, 1), this.tabla.getValueAt(fila, 2), this.tabla.getValueAt(fila, 3), this.tabla.getValueAt(fila, 4), this.tabla.getValueAt(fila, 5), this.tabla.getValueAt(fila, 6) });
/*     */       }
/*     */ 
/* 345 */       salida.insertar(sb.toString());
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.gui.Busqueda
 * JD-Core Version:    0.6.2
 */