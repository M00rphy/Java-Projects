/*     */ package almacen2.gui.listados;
/*     */ 
/*     */ import almacen2.data.listados.CVTableModel;
/*     */ import almacen2.data.listados.compraVentaObject;
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
/*     */ import java.awt.HeadlessException;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.File;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.DecimalFormatSymbols;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Formatter;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JFileChooser;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.ListSelectionModel;
/*     */ import javax.swing.table.TableColumn;
/*     */ import javax.swing.table.TableColumnModel;
/*     */ 
/*     */ public class MarcoListadoCV extends JFrame
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  41 */   private JPanel jContentPane = null;
/*  42 */   private JPanel jPanel = null;
/*  43 */   private JLabel jLabel = null;
/*  44 */   private JLabel jLabel1 = null;
/*  45 */   private JScrollPane jScrollPane = null;
/*  46 */   private JTable tabla = null;
/*  47 */   private CVTableModel modelo = null;
/*  48 */   private TableSorter tablaOrdenada = null;
/*  49 */   private JPanel jPanel1 = null;
/*  50 */   private JButton jButton = null;
/*  51 */   private JButton jButton1 = null;
/*  52 */   private JButton jButton2 = null;
/*  53 */   private JButton jButton3 = null;
/*     */   private String titulo1;
/*     */   private String titulo2;
/*     */   private ArrayList<compraVentaObject> lista;
/*     */ 
/*     */   public MarcoListadoCV(String titulo1, String titulo2, ArrayList<compraVentaObject> lista)
/*     */     throws HeadlessException
/*     */   {
/*  61 */     this.titulo1 = titulo1;
/*  62 */     this.titulo2 = titulo2;
/*  63 */     this.lista = lista;
/*  64 */     initialize();
/*     */   }
/*     */ 
/*     */   private void initialize()
/*     */   {
/*  73 */     setSize(720, 425);
/*  74 */     setContentPane(getJContentPane());
/*  75 */     setTitle(Mensajes.getString("listados"));
/*     */   }
/*     */ 
/*     */   private JPanel getJContentPane()
/*     */   {
/*  84 */     if (this.jContentPane == null) {
/*  85 */       this.jContentPane = new JPanel();
/*  86 */       this.jContentPane.setLayout(new BorderLayout());
/*  87 */       this.jContentPane.setBackground(Color.white);
/*  88 */       this.jContentPane.add(getJPanel(), "North");
/*  89 */       this.jContentPane.add(getJScrollPane(), "Center");
/*  90 */       this.jContentPane.add(getJPanel1(), "South");
/*     */     }
/*  92 */     return this.jContentPane;
/*     */   }
/*     */ 
/*     */   private JPanel getJPanel()
/*     */   {
/* 101 */     if (this.jPanel == null) {
/* 102 */       BorderLayout borderLayout = new BorderLayout();
/* 103 */       borderLayout.setVgap(7);
/* 104 */       this.jLabel1 = new JLabel();
/* 105 */       this.jLabel1.setText(this.titulo2);
/* 106 */       this.jLabel = new JLabel();
/* 107 */       this.jLabel.setText(this.titulo1);
/* 108 */       this.jPanel = new JPanel();
/* 109 */       this.jPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 5));
/* 110 */       this.jPanel.setBackground(Color.white);
/* 111 */       this.jPanel.setLayout(borderLayout);
/* 112 */       this.jPanel.add(this.jLabel, "North");
/* 113 */       this.jPanel.add(this.jLabel1, "Center");
/*     */     }
/* 115 */     return this.jPanel;
/*     */   }
/*     */ 
/*     */   private JScrollPane getJScrollPane()
/*     */   {
/* 124 */     if (this.jScrollPane == null) {
/* 125 */       this.jScrollPane = new JScrollPane();
/* 126 */       this.jScrollPane.setBackground(Color.white);
/* 127 */       this.jScrollPane.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
/* 128 */       this.jScrollPane.setViewportView(getTabla());
/*     */     }
/* 130 */     return this.jScrollPane;
/*     */   }
/*     */ 
/*     */   private JTable getTabla()
/*     */   {
/* 139 */     if (this.tabla == null) {
/* 140 */       this.tabla = new JTable(getTablaOrdenada());
/* 141 */       this.tablaOrdenada.setTableHeader(this.tabla.getTableHeader());
/* 142 */       int anchoTabla = 710;
/* 143 */       TableColumn columna = this.tabla.getColumnModel().getColumn(0);
/* 144 */       columna.setPreferredWidth((int)(anchoTabla * 0.13D));
/* 145 */       columna = this.tabla.getColumnModel().getColumn(1);
/* 146 */       columna.setPreferredWidth((int)(anchoTabla * 0.11D));
/* 147 */       columna = this.tabla.getColumnModel().getColumn(2);
/* 148 */       columna.setPreferredWidth((int)(anchoTabla * 0.47D));
/* 149 */       columna = this.tabla.getColumnModel().getColumn(3);
/* 150 */       columna.setPreferredWidth((int)(anchoTabla * 0.09D));
/* 151 */       columna.setCellRenderer(new DerechaColorRenderer());
/* 152 */       columna = this.tabla.getColumnModel().getColumn(4);
/* 153 */       columna.setPreferredWidth((int)(anchoTabla * 0.11D));
/* 154 */       columna.setCellRenderer(new ImporteColorRenderer());
/* 155 */       columna = this.tabla.getColumnModel().getColumn(5);
/* 156 */       columna.setPreferredWidth((int)(anchoTabla * 0.11D));
/* 157 */       columna.setCellRenderer(new ImporteColorRenderer());
/* 158 */       this.tabla.setDefaultRenderer(Object.class, new GeneralColorRenderer());
/*     */     }
/* 160 */     return this.tabla;
/*     */   }
/*     */ 
/*     */   private CVTableModel getModelo() {
/* 164 */     if (this.modelo == null) {
/* 165 */       this.modelo = new CVTableModel(this.lista);
/*     */     }
/* 167 */     return this.modelo;
/*     */   }
/*     */ 
/*     */   private TableSorter getTablaOrdenada() {
/* 171 */     if (this.tablaOrdenada == null) {
/* 172 */       this.tablaOrdenada = new TableSorter();
/* 173 */       this.tablaOrdenada.setTableModel(getModelo());
/*     */     }
/* 175 */     return this.tablaOrdenada;
/*     */   }
/*     */ 
/*     */   private JPanel getJPanel1()
/*     */   {
/* 184 */     if (this.jPanel1 == null) {
/* 185 */       this.jPanel1 = new JPanel();
/* 186 */       this.jPanel1.setLayout(null);
/* 187 */       this.jPanel1.setBackground(Color.white);
/* 188 */       this.jPanel1.setPreferredSize(new Dimension(150, 40));
/* 189 */       this.jPanel1.add(getJButton(), null);
/* 190 */       this.jPanel1.add(getJButton1(), null);
/* 191 */       this.jPanel1.add(getJButton2(), null);
/* 192 */       this.jPanel1.add(getJButton3(), null);
/*     */     }
/* 194 */     return this.jPanel1;
/*     */   }
/*     */ 
/*     */   private JButton getJButton()
/*     */   {
/* 203 */     if (this.jButton == null) {
/* 204 */       this.jButton = new JButton();
/* 205 */       this.jButton.setBounds(new Rectangle(5, 5, 35, 29));
/* 206 */       this.jButton.setIcon(new ImageIcon(getClass().getResource("/almacen2/iconos/sumatorio.png")));
/* 207 */       this.jButton.setToolTipText(Mensajes.getString("tipSumatorio"));
/* 208 */       this.jButton.addActionListener(new ActionListener()
/*     */       {
/*     */         public void actionPerformed(ActionEvent e) {
/* 211 */           MarcoListadoCV.this.calcularSumas();
/*     */         }
/*     */       });
/*     */     }
/* 215 */     return this.jButton;
/*     */   }
/*     */ 
/*     */   private JButton getJButton1()
/*     */   {
/* 224 */     if (this.jButton1 == null) {
/* 225 */       this.jButton1 = new JButton();
/* 226 */       this.jButton1.setBounds(new Rectangle(50, 5, 35, 29));
/* 227 */       this.jButton1.setIcon(new ImageIcon(getClass().getResource("/almacen2/iconos/asTXT.png")));
/* 228 */       this.jButton1.setToolTipText(Mensajes.getString("guardarTexto"));
/* 229 */       this.jButton1.addActionListener(new ActionListener()
/*     */       {
/*     */         public void actionPerformed(ActionEvent e) {
/* 232 */           MarcoListadoCV.this.guardarLista();
/*     */         }
/*     */       });
/*     */     }
/* 236 */     return this.jButton1;
/*     */   }
/*     */ 
/*     */   private JButton getJButton2()
/*     */   {
/* 245 */     if (this.jButton2 == null) {
/* 246 */       this.jButton2 = new JButton();
/* 247 */       this.jButton2.setBounds(new Rectangle(95, 5, 35, 29));
/* 248 */       this.jButton2.setIcon(new ImageIcon(getClass().getResource("/almacen2/iconos/asCSV.png")));
/* 249 */       this.jButton2.setToolTipText(Mensajes.getString("guardarCSV"));
/* 250 */       this.jButton2.addActionListener(new ActionListener()
/*     */       {
/*     */         public void actionPerformed(ActionEvent e) {
/* 253 */           MarcoListadoCV.this.guardarCSV();
/*     */         }
/*     */       });
/*     */     }
/* 257 */     return this.jButton2;
/*     */   }
/*     */ 
/*     */   private JButton getJButton3() {
/* 261 */     if (this.jButton3 == null) {
/* 262 */       this.jButton3 = new JButton();
/* 263 */       this.jButton3.setBounds(new Rectangle(140, 5, 35, 29));
/* 264 */       this.jButton3.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/pdf.png")));
/* 265 */       this.jButton3.setToolTipText(Mensajes.getString("guardarPDF"));
/* 266 */       this.jButton3.addActionListener(new ActionListener()
/*     */       {
/*     */         public void actionPerformed(ActionEvent e) {
/* 269 */           MarcoListadoCV.this.guardarPDF();
/*     */         }
/*     */       });
/*     */     }
/* 273 */     return this.jButton3;
/*     */   }
/*     */ 
/*     */   private void calcularSumas() {
/* 277 */     int unidades = 0;
/* 278 */     double coste = 0.0D;
/* 279 */     ListSelectionModel lsm = this.tabla.getSelectionModel();
/* 280 */     if (lsm.isSelectionEmpty()) {
/* 281 */       return;
/*     */     }
/* 283 */     int inicio = lsm.getMinSelectionIndex();
/* 284 */     int fin = lsm.getMaxSelectionIndex();
/*     */ 
/* 286 */     for (int index = inicio; index <= fin; index++) {
/* 287 */       if (this.tabla.isRowSelected(index)) {
/* 288 */         unidades += ((compraVentaObject)this.lista.get(index)).getEs();
/* 289 */         coste += ((compraVentaObject)this.lista.get(index)).getImporte();
/*     */       }
/*     */     }
/*     */ 
/* 293 */     DecimalFormatSymbols formato = new DecimalFormatSymbols();
/* 294 */     formato.setDecimalSeparator(',');
/* 295 */     formato.setPerMill('.');
/* 296 */     DecimalFormat fn = new DecimalFormat("#,###,##0.00", formato);
/* 297 */     String mensaje = new StringBuilder().append(Mensajes.getString("unidades")).append(" = ").append(Integer.toString(unidades)).append("\n").append(Mensajes.getString("importe")).append(" = ").append(fn.format(coste)).toString();
/*     */ 
/* 299 */     JOptionPane.showMessageDialog(this, mensaje, Mensajes.getString("sumas"), 1);
/*     */   }
/*     */ 
/*     */   private void guardarLista()
/*     */   {
/* 305 */     JFileChooser fc = new JFileChooser();
/* 306 */     fc.setSelectedFile(new File(new StringBuilder().append(this.titulo1).append(".txt").toString()));
/* 307 */     int retorno = fc.showSaveDialog(this);
/* 308 */     if (retorno == 0) {
/* 309 */       File archivo = fc.getSelectedFile();
/* 310 */       archivo = AddExtension.txt(archivo);
/* 311 */       GrabarFichero salida = new GrabarFichero(archivo);
/* 312 */       salida.insertar(new StringBuilder().append(this.titulo1).append("\n").append(this.titulo2).append("\n\n").toString());
/* 313 */       StringBuilder sb = new StringBuilder();
/* 314 */       Formatter formater = new Formatter(sb);
/* 315 */       formater.format("%-10s %20s %80s %8s %10s %10s\n", new Object[] { Mensajes.getString("fecha"), Mensajes.getString("referencia"), Mensajes.getString("descripcion"), Mensajes.getString("unidades"), Mensajes.getString("importe"), Mensajes.getString("pLista") });
/*     */ 
/* 319 */       for (int fila = 0; fila < this.tabla.getRowCount(); fila++) {
/* 320 */         formater.format("%10s %20s %80s %8s %,10.2f %,10.2f\n", new Object[] { this.tabla.getValueAt(fila, 0), this.tabla.getValueAt(fila, 1), this.tabla.getValueAt(fila, 2), this.tabla.getValueAt(fila, 3), this.tabla.getValueAt(fila, 4), this.tabla.getValueAt(fila, 5) });
/*     */       }
/*     */ 
/* 328 */       salida.insertar(sb.toString());
/* 329 */       salida.cerrar();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void guardarCSV() {
/* 334 */     JFileChooser fc = new JFileChooser();
/* 335 */     fc.setSelectedFile(new File(new StringBuilder().append(this.titulo1).append(".csv").toString()));
/* 336 */     int retorno = fc.showSaveDialog(this);
/* 337 */     if (retorno == 0) {
/* 338 */       File archivo = fc.getSelectedFile();
/* 339 */       archivo = AddExtension.csv(archivo);
/* 340 */       GrabarFichero salida = new GrabarFichero(archivo);
/* 341 */       salida.insertar(new StringBuilder().append(this.titulo1).append("\n").append(this.titulo2).append("\n\n").toString());
/* 342 */       StringBuilder sb = new StringBuilder();
/* 343 */       Formatter formater = new Formatter(sb);
/* 344 */       formater.format("%s;%s;%s;%s;%s;%s\n", new Object[] { Mensajes.getString("fecha"), Mensajes.getString("referencia"), Mensajes.getString("descripcion"), Mensajes.getString("unidades"), Mensajes.getString("importe"), Mensajes.getString("pLista") });
/*     */ 
/* 348 */       for (int fila = 0; fila < this.tabla.getRowCount(); fila++) {
/* 349 */         formater.format("%s;%s;%s;%s;%,10.2f;%,10.2f\n", new Object[] { this.tabla.getValueAt(fila, 0), this.tabla.getValueAt(fila, 1), this.tabla.getValueAt(fila, 2), this.tabla.getValueAt(fila, 3), this.tabla.getValueAt(fila, 4), this.tabla.getValueAt(fila, 5) });
/*     */       }
/*     */ 
/* 357 */       salida.insertar(sb.toString());
/* 358 */       salida.cerrar();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void guardarPDF() {
/* 363 */     GenerarPdf gPdf = new GenerarPdf(this);
/* 364 */     gPdf.setTitulos(this.titulo1, this.titulo2);
/* 365 */     gPdf.generarDocumento(new float[] { 0.5F, 0.5F, 2.0F, 0.5F, 0.5F, 0.5F }, this.modelo);
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.gui.listados.MarcoListadoCV
 * JD-Core Version:    0.6.2
 */