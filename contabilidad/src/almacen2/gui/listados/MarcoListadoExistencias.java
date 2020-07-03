/*     */ package almacen2.gui.listados;
/*     */ 
/*     */ import almacen2.data.listados.ExistenciasTableModel;
/*     */ import almacen2.data.listados.existenciaObject;
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
/*     */ import java.io.PrintStream;
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
/*     */ public class MarcoListadoExistencias extends JFrame
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  43 */   private JPanel jContentPane = null;
/*  44 */   private JPanel jPanel = null;
/*  45 */   private JLabel jLabel = null;
/*  46 */   private JLabel jLabel1 = null;
/*  47 */   private JScrollPane jScrollPane = null;
/*  48 */   private JTable tabla = null;
/*  49 */   private ExistenciasTableModel modelo = null;
/*  50 */   private TableSorter tablaOrdenada = null;
/*  51 */   private JPanel jPanel1 = null;
/*  52 */   private JButton jButton = null;
/*  53 */   private JButton jButton1 = null;
/*  54 */   private JButton jButton2 = null;
/*  55 */   private JButton jButton3 = null;
/*     */   private String titulo1;
/*     */   private String titulo2;
/*     */   private ArrayList<existenciaObject> lista;
/*     */ 
/*     */   public MarcoListadoExistencias(String titulo1, String titulo2, ArrayList<existenciaObject> lista)
/*     */     throws HeadlessException
/*     */   {
/*  63 */     this.titulo1 = titulo1;
/*  64 */     this.titulo2 = titulo2;
/*  65 */     this.lista = lista;
/*  66 */     initialize();
/*     */   }
/*     */ 
/*     */   private void initialize()
/*     */   {
/*  75 */     setSize(600, 425);
/*  76 */     setContentPane(getJContentPane());
/*  77 */     setTitle(Mensajes.getString("listados"));
/*     */   }
/*     */ 
/*     */   private JPanel getJContentPane()
/*     */   {
/*  86 */     if (this.jContentPane == null) {
/*  87 */       this.jContentPane = new JPanel();
/*  88 */       this.jContentPane.setLayout(new BorderLayout());
/*  89 */       this.jContentPane.setBackground(Color.white);
/*  90 */       this.jContentPane.add(getJPanel(), "North");
/*  91 */       this.jContentPane.add(getJScrollPane(), "Center");
/*  92 */       this.jContentPane.add(getJPanel1(), "South");
/*     */     }
/*  94 */     return this.jContentPane;
/*     */   }
/*     */ 
/*     */   private JPanel getJPanel()
/*     */   {
/* 103 */     if (this.jPanel == null) {
/* 104 */       BorderLayout borderLayout = new BorderLayout();
/* 105 */       borderLayout.setVgap(7);
/* 106 */       this.jLabel1 = new JLabel();
/* 107 */       this.jLabel1.setText(this.titulo2);
/* 108 */       this.jLabel = new JLabel();
/* 109 */       this.jLabel.setText(this.titulo1);
/* 110 */       this.jPanel = new JPanel();
/* 111 */       this.jPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 5));
/* 112 */       this.jPanel.setBackground(Color.white);
/* 113 */       this.jPanel.setLayout(borderLayout);
/* 114 */       this.jPanel.add(this.jLabel, "North");
/* 115 */       this.jPanel.add(this.jLabel1, "Center");
/*     */     }
/* 117 */     return this.jPanel;
/*     */   }
/*     */ 
/*     */   private JScrollPane getJScrollPane()
/*     */   {
/* 126 */     if (this.jScrollPane == null) {
/* 127 */       this.jScrollPane = new JScrollPane();
/* 128 */       this.jScrollPane.setBackground(Color.white);
/* 129 */       this.jScrollPane.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
/* 130 */       this.jScrollPane.setViewportView(getTabla());
/*     */     }
/* 132 */     return this.jScrollPane;
/*     */   }
/*     */ 
/*     */   private JTable getTabla()
/*     */   {
/* 141 */     if (this.tabla == null) {
/* 142 */       this.tabla = new JTable(getTablaOrdenada());
/* 143 */       this.tablaOrdenada.setTableHeader(this.tabla.getTableHeader());
/* 144 */       int anchoTabla = 590;
/* 145 */       TableColumn columna = this.tabla.getColumnModel().getColumn(0);
/* 146 */       columna.setPreferredWidth((int)(anchoTabla * 0.15D));
/* 147 */       columna = this.tabla.getColumnModel().getColumn(1);
/* 148 */       columna.setPreferredWidth((int)(anchoTabla * 0.57D));
/* 149 */       columna = this.tabla.getColumnModel().getColumn(2);
/* 150 */       columna.setPreferredWidth((int)(anchoTabla * 0.13D));
/* 151 */       columna.setCellRenderer(new DerechaColorRenderer());
/* 152 */       columna = this.tabla.getColumnModel().getColumn(3);
/* 153 */       columna.setPreferredWidth((int)(anchoTabla * 0.15D));
/* 154 */       columna.setCellRenderer(new ImporteColorRenderer());
/* 155 */       this.tabla.setDefaultRenderer(Object.class, new GeneralColorRenderer());
/*     */     }
/* 157 */     return this.tabla;
/*     */   }
/*     */ 
/*     */   private ExistenciasTableModel getModelo() {
/* 161 */     if (this.modelo == null) {
/* 162 */       this.modelo = new ExistenciasTableModel(this.lista);
/*     */     }
/* 164 */     return this.modelo;
/*     */   }
/*     */ 
/*     */   private TableSorter getTablaOrdenada() {
/* 168 */     if (this.tablaOrdenada == null) {
/* 169 */       this.tablaOrdenada = new TableSorter();
/* 170 */       this.tablaOrdenada.setTableModel(getModelo());
/*     */     }
/* 172 */     return this.tablaOrdenada;
/*     */   }
/*     */ 
/*     */   private JPanel getJPanel1()
/*     */   {
/* 181 */     if (this.jPanel1 == null) {
/* 182 */       this.jPanel1 = new JPanel();
/* 183 */       this.jPanel1.setLayout(null);
/* 184 */       this.jPanel1.setBackground(Color.white);
/* 185 */       this.jPanel1.setPreferredSize(new Dimension(150, 40));
/* 186 */       this.jPanel1.add(getJButton(), null);
/* 187 */       this.jPanel1.add(getJButton1(), null);
/* 188 */       this.jPanel1.add(getJButton2(), null);
/* 189 */       this.jPanel1.add(getJButton3(), null);
/*     */     }
/* 191 */     return this.jPanel1;
/*     */   }
/*     */ 
/*     */   private JButton getJButton()
/*     */   {
/* 200 */     if (this.jButton == null) {
/* 201 */       this.jButton = new JButton();
/* 202 */       this.jButton.setBounds(new Rectangle(5, 5, 35, 29));
/* 203 */       this.jButton.setIcon(new ImageIcon(getClass().getResource("/almacen2/iconos/sumatorio.png")));
/* 204 */       this.jButton.setToolTipText(Mensajes.getString("tipSumatorio"));
/* 205 */       this.jButton.addActionListener(new ActionListener()
/*     */       {
/*     */         public void actionPerformed(ActionEvent e) {
/* 208 */           MarcoListadoExistencias.this.calcularSumas();
/*     */         }
/*     */       });
/*     */     }
/* 212 */     return this.jButton;
/*     */   }
/*     */ 
/*     */   private JButton getJButton1()
/*     */   {
/* 221 */     if (this.jButton1 == null) {
/* 222 */       this.jButton1 = new JButton();
/* 223 */       this.jButton1.setBounds(new Rectangle(50, 5, 35, 29));
/* 224 */       this.jButton1.setIcon(new ImageIcon(getClass().getResource("/almacen2/iconos/asTXT.png")));
/* 225 */       this.jButton1.setToolTipText(Mensajes.getString("guardarTexto"));
/* 226 */       this.jButton1.addActionListener(new ActionListener()
/*     */       {
/*     */         public void actionPerformed(ActionEvent e) {
/* 229 */           MarcoListadoExistencias.this.guardarLista();
/*     */         }
/*     */       });
/*     */     }
/* 233 */     return this.jButton1;
/*     */   }
/*     */ 
/*     */   private JButton getJButton2()
/*     */   {
/* 242 */     if (this.jButton2 == null) {
/* 243 */       this.jButton2 = new JButton();
/* 244 */       this.jButton2.setBounds(new Rectangle(95, 5, 35, 29));
/* 245 */       this.jButton2.setIcon(new ImageIcon(getClass().getResource("/almacen2/iconos/asCSV.png")));
/* 246 */       this.jButton2.setToolTipText(Mensajes.getString("guardarCSV"));
/* 247 */       this.jButton2.addActionListener(new ActionListener()
/*     */       {
/*     */         public void actionPerformed(ActionEvent e) {
/* 250 */           MarcoListadoExistencias.this.guardarCSV();
/*     */         }
/*     */       });
/*     */     }
/* 254 */     return this.jButton2;
/*     */   }
/*     */ 
/*     */   private JButton getJButton3() {
/* 258 */     if (this.jButton3 == null) {
/* 259 */       this.jButton3 = new JButton();
/* 260 */       this.jButton3.setBounds(new Rectangle(140, 5, 35, 29));
/* 261 */       this.jButton3.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/pdf.png")));
/* 262 */       this.jButton3.setToolTipText(Mensajes.getString("guardarPDF"));
/* 263 */       this.jButton3.addActionListener(new ActionListener()
/*     */       {
/*     */         public void actionPerformed(ActionEvent e) {
/* 266 */           MarcoListadoExistencias.this.guardarPDF();
/*     */         }
/*     */       });
/*     */     }
/* 270 */     return this.jButton3;
/*     */   }
/*     */ 
/*     */   private void calcularSumas() {
/* 274 */     int unidades = 0;
/* 275 */     double coste = 0.0D;
/* 276 */     ListSelectionModel lsm = this.tabla.getSelectionModel();
/* 277 */     if (lsm.isSelectionEmpty()) {
/* 278 */       return;
/*     */     }
/* 280 */     int inicio = lsm.getMinSelectionIndex();
/* 281 */     int fin = lsm.getMaxSelectionIndex();
/*     */ 
/* 283 */     for (int index = inicio; index <= fin; index++) {
/* 284 */       if (this.tabla.isRowSelected(index)) {
/* 285 */         unidades += ((existenciaObject)this.lista.get(index)).getUnidades();
/* 286 */         coste += ((existenciaObject)this.lista.get(index)).getCoste();
/*     */       }
/*     */     }
/*     */ 
/* 290 */     DecimalFormatSymbols formato = new DecimalFormatSymbols();
/* 291 */     formato.setDecimalSeparator(',');
/* 292 */     formato.setPerMill('.');
/* 293 */     DecimalFormat fn = new DecimalFormat("#,###,##0.00", formato);
/* 294 */     String mensaje = new StringBuilder().append(Mensajes.getString("unidades")).append(" = ").append(Integer.toString(unidades)).append("\n").append(Mensajes.getString("coste")).append(" = ").append(fn.format(coste)).toString();
/*     */ 
/* 296 */     JOptionPane.showMessageDialog(this, mensaje, Mensajes.getString("sumas"), 1);
/*     */   }
/*     */ 
/*     */   private void guardarLista()
/*     */   {
/* 302 */     JFileChooser fc = new JFileChooser();
/* 303 */     fc.setSelectedFile(new File(new StringBuilder().append(this.titulo1).append(".csv").toString()));
/* 304 */     int retorno = fc.showSaveDialog(this);
/* 305 */     if (retorno == 0) {
/* 306 */       File archivo = fc.getSelectedFile();
/* 307 */       archivo = AddExtension.txt(archivo);
/* 308 */       GrabarFichero salida = new GrabarFichero(archivo);
/* 309 */       salida.insertar(new StringBuilder().append(this.titulo1).append("\n").append(this.titulo2).append("\n\n").toString());
/* 310 */       StringBuilder sb = new StringBuilder();
/* 311 */       Formatter formater = new Formatter(sb);
/* 312 */       formater.format("%-10s %80s %8s %10s\n", new Object[] { Mensajes.getString("referencia"), Mensajes.getString("descripcion"), Mensajes.getString("unidades"), Mensajes.getString("coste") });
/* 313 */       for (int fila = 0; fila < this.tabla.getRowCount(); fila++) {
/* 314 */         formater.format("%10s %80s %8s %,10.2f\n", new Object[] { this.tabla.getValueAt(fila, 0), this.tabla.getValueAt(fila, 1), this.tabla.getValueAt(fila, 2), this.tabla.getValueAt(fila, 3) });
/*     */       }
/*     */ 
/* 320 */       salida.insertar(sb.toString());
/* 321 */       salida.cerrar();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void guardarCSV() {
/* 326 */     JFileChooser fc = new JFileChooser();
/* 327 */     fc.setSelectedFile(new File(new StringBuilder().append(this.titulo1).append(".csv").toString()));
/* 328 */     int retorno = fc.showSaveDialog(this);
/* 329 */     if (retorno == 0) {
/* 330 */       File archivo = fc.getSelectedFile();
/* 331 */       archivo = AddExtension.csv(archivo);
/* 332 */       GrabarFichero salida = new GrabarFichero(archivo);
/* 333 */       salida.insertar(new StringBuilder().append(this.titulo1).append("\n").append(this.titulo2).append("\n\n").toString());
/* 334 */       StringBuilder sb = new StringBuilder();
/* 335 */       Formatter formater = new Formatter(sb);
/* 336 */       formater.format("%s;%s;%s;%s\n", new Object[] { Mensajes.getString("referencia"), Mensajes.getString("descripcion"), Mensajes.getString("unidades"), Mensajes.getString("coste") });
/*     */ 
/* 339 */       for (int fila = 0; fila < this.tabla.getRowCount(); fila++) {
/* 340 */         formater.format("%s;%s;%s;%,10.2f\n", new Object[] { this.tabla.getValueAt(fila, 0), this.tabla.getValueAt(fila, 1), this.tabla.getValueAt(fila, 2), this.tabla.getValueAt(fila, 3) });
/*     */       }
/*     */ 
/* 346 */       System.out.println(sb.toString());
/* 347 */       salida.insertar(sb.toString());
/* 348 */       salida.cerrar();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void guardarPDF() {
/* 353 */     GenerarPdf gPdf = new GenerarPdf(this);
/* 354 */     gPdf.setTitulos(this.titulo1, this.titulo2);
/* 355 */     gPdf.generarDocumento(new float[] { 0.5F, 2.0F, 0.5F, 0.5F }, this.modelo);
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.gui.listados.MarcoListadoExistencias
 * JD-Core Version:    0.6.2
 */