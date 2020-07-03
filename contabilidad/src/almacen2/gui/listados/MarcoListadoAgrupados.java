/*     */ package almacen2.gui.listados;
/*     */ 
/*     */ import almacen2.data.listados.AgrupadoObject;
/*     */ import almacen2.data.listados.AgrupadoTableModel;
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
/*     */ public class MarcoListadoAgrupados extends JFrame
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  40 */   private JPanel jContentPane = null;
/*  41 */   private JPanel jPanel = null;
/*  42 */   private JLabel jLabel = null;
/*  43 */   private JLabel jLabel1 = null;
/*  44 */   private JScrollPane jScrollPane = null;
/*  45 */   private JTable tabla = null;
/*  46 */   private AgrupadoTableModel modelo = null;
/*  47 */   private TableSorter tablaOrdenada = null;
/*  48 */   private JPanel jPanel1 = null;
/*  49 */   private JButton jButton = null;
/*  50 */   private JButton jButton1 = null;
/*  51 */   private JButton jButton2 = null;
/*  52 */   private JButton jButton3 = null;
/*     */   private String titulo1;
/*     */   private String titulo2;
/*     */   private ArrayList<AgrupadoObject> lista;
/*     */ 
/*     */   public MarcoListadoAgrupados(String titulo1, String titulo2, ArrayList<AgrupadoObject> lista)
/*     */     throws HeadlessException
/*     */   {
/*  60 */     this.titulo1 = titulo1;
/*  61 */     this.titulo2 = titulo2;
/*  62 */     this.lista = lista;
/*  63 */     initialize();
/*     */   }
/*     */ 
/*     */   private void initialize()
/*     */   {
/*  72 */     setSize(720, 425);
/*  73 */     setContentPane(getJContentPane());
    setVisible(true);
/*  74 */     setTitle(Mensajes.getString("listados"));
/*     */   }
/*     */ 
/*     */   private JPanel getJContentPane()
/*     */   {
/*  83 */     if (this.jContentPane == null) {
/*  84 */       this.jContentPane = new JPanel();
/*  85 */       this.jContentPane.setLayout(new BorderLayout());
/*  86 */       this.jContentPane.setBackground(Color.white);
/*  87 */       this.jContentPane.add(getJPanel(), "North");
/*  88 */       this.jContentPane.add(getJScrollPane(), "Center");
/*  89 */       this.jContentPane.add(getJPanel1(), "South");
/*     */     }
/*  91 */     return this.jContentPane;
/*     */   }
/*     */ 
/*     */   private JPanel getJPanel()
/*     */   {
/* 100 */     if (this.jPanel == null) {
/* 101 */       BorderLayout borderLayout = new BorderLayout();
/* 102 */       borderLayout.setVgap(7);
/* 103 */       this.jLabel1 = new JLabel();
/* 104 */       this.jLabel1.setText(this.titulo2);
/* 105 */       this.jLabel = new JLabel();
/* 106 */       this.jLabel.setText(this.titulo1);
/* 107 */       this.jPanel = new JPanel();
/* 108 */       this.jPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 5));
/* 109 */       this.jPanel.setBackground(Color.white);
/* 110 */       this.jPanel.setLayout(borderLayout);
/* 111 */       this.jPanel.add(this.jLabel, "North");
/* 112 */       this.jPanel.add(this.jLabel1, "Center");
/*     */     }
/* 114 */     return this.jPanel;
/*     */   }
/*     */ 
/*     */   private JScrollPane getJScrollPane()
/*     */   {
/* 123 */     if (this.jScrollPane == null) {
/* 124 */       this.jScrollPane = new JScrollPane();
/* 125 */       this.jScrollPane.setBackground(Color.white);
/* 126 */       this.jScrollPane.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
/* 127 */       this.jScrollPane.setViewportView(getTabla());
/*     */     }
/* 129 */     return this.jScrollPane;
/*     */   }
/*     */ 
/*     */   private JTable getTabla()
/*     */   {
/* 138 */     if (this.tabla == null) {
/* 139 */       this.tabla = new JTable(getTablaOrdenada());
/* 140 */       this.tablaOrdenada.setTableHeader(this.tabla.getTableHeader());
/* 141 */       int anchoTabla = 710;
/* 142 */       TableColumn columna = this.tabla.getColumnModel().getColumn(0);
/* 143 */       columna.setPreferredWidth((int)(anchoTabla * 0.4D));
/* 144 */       columna = this.tabla.getColumnModel().getColumn(1);
/* 145 */       columna.setPreferredWidth((int)(anchoTabla * 0.12D));
/* 146 */       columna.setCellRenderer(new ImporteColorRenderer());
/* 147 */       columna = this.tabla.getColumnModel().getColumn(2);
/* 148 */       columna.setPreferredWidth((int)(anchoTabla * 0.18D));
/* 149 */       columna.setCellRenderer(new ImporteColorRenderer());
/* 150 */       columna = this.tabla.getColumnModel().getColumn(3);
/* 151 */       columna.setPreferredWidth((int)(anchoTabla * 0.12D));
/* 152 */       columna.setCellRenderer(new ImporteColorRenderer());
/* 153 */       columna = this.tabla.getColumnModel().getColumn(4);
/* 154 */       columna.setPreferredWidth((int)(anchoTabla * 0.18D));
/* 155 */       columna.setCellRenderer(new ImporteColorRenderer());
/* 156 */       this.tabla.setDefaultRenderer(Object.class, new GeneralColorRenderer());
/*     */     }
/* 158 */     return this.tabla;
/*     */   }
/*     */ 
/*     */   private AgrupadoTableModel getModelo() {
/* 162 */     if (this.modelo == null) {
/* 163 */       this.modelo = new AgrupadoTableModel(this.lista);
/*     */     }
/* 165 */     return this.modelo;
/*     */   }
/*     */ 
/*     */   private TableSorter getTablaOrdenada() {
/* 169 */     if (this.tablaOrdenada == null) {
/* 170 */       this.tablaOrdenada = new TableSorter();
/* 171 */       this.tablaOrdenada.setTableModel(getModelo());
/*     */     }
/* 173 */     return this.tablaOrdenada;
/*     */   }
/*     */ 
/*     */   private JPanel getJPanel1()
/*     */   {
/* 182 */     if (this.jPanel1 == null) {
/* 183 */       this.jPanel1 = new JPanel();
/* 184 */       this.jPanel1.setLayout(null);
/* 185 */       this.jPanel1.setBackground(Color.white);
/* 186 */       this.jPanel1.setPreferredSize(new Dimension(150, 40));
/* 187 */       this.jPanel1.add(getJButton(), null);
/* 188 */       this.jPanel1.add(getJButton1(), null);
/* 189 */       this.jPanel1.add(getJButton2(), null);
/* 190 */       this.jPanel1.add(getJButton3(), null);
/*     */     }
/* 192 */     return this.jPanel1;
/*     */   }
/*     */ 
/*     */   private JButton getJButton()
/*     */   {
/* 201 */     if (this.jButton == null) {
/* 202 */       this.jButton = new JButton();
/* 203 */       this.jButton.setBounds(new Rectangle(5, 5, 35, 29));
/* 204 */       this.jButton.setIcon(new ImageIcon(getClass().getResource("/almacen2/iconos/sumatorio.png")));
/* 205 */       this.jButton.setToolTipText(Mensajes.getString("tipSumatorio"));
/* 206 */       this.jButton.addActionListener(new ActionListener()
/*     */       {
/*     */         public void actionPerformed(ActionEvent e) {
/* 209 */           MarcoListadoAgrupados.this.calcularSumas();
/*     */         }
/*     */       });
/*     */     }
/* 213 */     return this.jButton;
/*     */   }
/*     */ 
/*     */   private JButton getJButton1()
/*     */   {
/* 222 */     if (this.jButton1 == null) {
/* 223 */       this.jButton1 = new JButton();
/* 224 */       this.jButton1.setBounds(new Rectangle(50, 5, 35, 29));
/* 225 */       this.jButton1.setIcon(new ImageIcon(getClass().getResource("/almacen2/iconos/asTXT.png")));
/* 226 */       this.jButton1.setToolTipText(Mensajes.getString("guardarTexto"));
/* 227 */       this.jButton1.addActionListener(new ActionListener()
/*     */       {
/*     */         public void actionPerformed(ActionEvent e) {
/* 230 */           MarcoListadoAgrupados.this.guardarLista();
/*     */         }
/*     */       });
/*     */     }
/* 234 */     return this.jButton1;
/*     */   }
/*     */ 
/*     */   private JButton getJButton2()
/*     */   {
/* 243 */     if (this.jButton2 == null) {
/* 244 */       this.jButton2 = new JButton();
/* 245 */       this.jButton2.setBounds(new Rectangle(95, 5, 35, 29));
/* 246 */       this.jButton2.setIcon(new ImageIcon(getClass().getResource("/almacen2/iconos/asCSV.png")));
/* 247 */       this.jButton2.setToolTipText(Mensajes.getString("guardarCSV"));
/* 248 */       this.jButton2.addActionListener(new ActionListener()
/*     */       {
/*     */         public void actionPerformed(ActionEvent e) {
/* 251 */           MarcoListadoAgrupados.this.guardarCSV();
/*     */         }
/*     */       });
/*     */     }
/* 255 */     return this.jButton2;
/*     */   }
/*     */ 
/*     */   private JButton getJButton3() {
/* 259 */     if (this.jButton3 == null) {
/* 260 */       this.jButton3 = new JButton();
/* 261 */       this.jButton3.setBounds(new Rectangle(140, 5, 35, 29));
/* 262 */       this.jButton3.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/pdf.png")));
/* 263 */       this.jButton3.setToolTipText(Mensajes.getString("guardarPDF"));
/* 264 */       this.jButton3.addActionListener(new ActionListener()
/*     */       {
/*     */         public void actionPerformed(ActionEvent e) {
/* 267 */           MarcoListadoAgrupados.this.guardarPDF();
/*     */         }
/*     */       });
/*     */     }
/* 271 */     return this.jButton3;
/*     */   }
/*     */ 
/*     */   private void calcularSumas()
/*     */   {
/* 276 */     Double uniC = Double.valueOf(0.0D);
/* 277 */     Double totC = Double.valueOf(0.0D);
/* 278 */     Double uniV = Double.valueOf(0.0D);
/* 279 */     Double totV = Double.valueOf(0.0D);
/* 280 */     ListSelectionModel lsm = this.tabla.getSelectionModel();
/* 281 */     if (lsm.isSelectionEmpty()) {
/* 282 */       return;
/*     */     }
/* 284 */     int inicio = lsm.getMinSelectionIndex();
/* 285 */     int fin = lsm.getMaxSelectionIndex();
/*     */ 
/* 287 */     for (int index = inicio; index <= fin; index++) {
/* 288 */       if (this.tabla.isRowSelected(index)) {
/* 289 */         uniC = Double.valueOf(uniC.doubleValue() + ((AgrupadoObject)this.lista.get(index)).getUnidadesCompra().doubleValue());
/* 290 */         totC = Double.valueOf(totC.doubleValue() + ((AgrupadoObject)this.lista.get(index)).getTotalCompra().doubleValue());
/* 291 */         uniV = Double.valueOf(uniV.doubleValue() + ((AgrupadoObject)this.lista.get(index)).getUnidadesVenta().doubleValue());
/* 292 */         totV = Double.valueOf(totV.doubleValue() + ((AgrupadoObject)this.lista.get(index)).getTotalVenta().doubleValue());
/*     */       }
/*     */     }
/*     */ 
/* 296 */     DecimalFormatSymbols formato = new DecimalFormatSymbols();
/* 297 */     formato.setDecimalSeparator(',');
/* 298 */     formato.setPerMill('.');
/* 299 */     DecimalFormat fn = new DecimalFormat("#,###,##0.00", formato);
/* 300 */     String mensaje = new StringBuilder().append(Mensajes.getString("compras")).append(":\n").append(Mensajes.getString("unidades")).append(" = ").append(fn.format(uniC)).append("\n").append(Mensajes.getString("importe")).append(" = ").append(fn.format(totC)).append("\n\n").append(Mensajes.getString("ventas")).append(":\n").append(Mensajes.getString("unidades")).append(" = ").append(fn.format(uniV)).append("\n").append(Mensajes.getString("importe")).append(" = ").append(fn.format(totV)).append("\n\n").toString();
/*     */ 
/* 306 */     JOptionPane.showMessageDialog(this, mensaje, Mensajes.getString("sumas"), 1);
/*     */   }
/*     */ 
/*     */   private void guardarLista()
/*     */   {
/* 312 */     JFileChooser fc = new JFileChooser();
/* 313 */     fc.setSelectedFile(new File(new StringBuilder().append(this.titulo1).append(".txt").toString()));
/* 314 */     int retorno = fc.showSaveDialog(this);
/* 315 */     if (retorno == 0) {
/* 316 */       File archivo = fc.getSelectedFile();
/* 317 */       archivo = AddExtension.txt(archivo);
/* 318 */       archivo = AddExtension.txt(archivo);
/* 319 */       GrabarFichero salida = new GrabarFichero(archivo);
/* 320 */       salida.insertar(new StringBuilder().append(this.titulo1).append("\n").append(this.titulo2).append("\n\n").toString());
/* 321 */       StringBuilder sb = new StringBuilder();
/* 322 */       Formatter formater = new Formatter(sb);
/* 323 */       formater.format("%40s %12s %12s %12s %12s\n", new Object[] { Mensajes.getString("nombre"), Mensajes.getString("compras"), Mensajes.getString("importe"), Mensajes.getString("ventas"), Mensajes.getString("importe") });
/*     */ 
/* 327 */       for (int fila = 0; fila < this.tabla.getRowCount(); fila++) {
/* 328 */         formater.format("%40s %,12.2f %,12.2f %,12.2f %,12.2f\n", new Object[] { this.tabla.getValueAt(fila, 0), this.tabla.getValueAt(fila, 1), this.tabla.getValueAt(fila, 2), this.tabla.getValueAt(fila, 3), this.tabla.getValueAt(fila, 4) });
/*     */       }
/*     */ 
/* 335 */       salida.insertar(sb.toString());
/* 336 */       salida.cerrar();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void guardarCSV() {
/* 341 */     JFileChooser fc = new JFileChooser();
/* 342 */     fc.setSelectedFile(new File(new StringBuilder().append(this.titulo1).append(".csv").toString()));
/* 343 */     int retorno = fc.showSaveDialog(this);
/* 344 */     if (retorno == 0) {
/* 345 */       File archivo = fc.getSelectedFile();
/* 346 */       archivo = AddExtension.csv(archivo);
/* 347 */       GrabarFichero salida = new GrabarFichero(archivo);
/* 348 */       salida.insertar(new StringBuilder().append(this.titulo1).append("\n").append(this.titulo2).append("\n\n").toString());
/* 349 */       StringBuilder sb = new StringBuilder();
/* 350 */       Formatter formater = new Formatter(sb);
/* 351 */       formater.format("%s;%s;%s;%s;%s;%s\n", new Object[] { Mensajes.getString("nombre"), Mensajes.getString("compras"), Mensajes.getString("importe"), Mensajes.getString("ventas"), Mensajes.getString("importe") });
/*     */ 
/* 355 */       for (int fila = 0; fila < this.tabla.getRowCount(); fila++) {
/* 356 */         formater.format("%s;%,10.2f;%,10.2f;%,10.2f;%,10.2f\n", new Object[] { this.tabla.getValueAt(fila, 0), this.tabla.getValueAt(fila, 1), this.tabla.getValueAt(fila, 2), this.tabla.getValueAt(fila, 3), this.tabla.getValueAt(fila, 4) });
/*     */       }
/*     */ 
/* 363 */       salida.insertar(sb.toString());
/* 364 */       salida.cerrar();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void guardarPDF()
/*     */   {
/* 378 */     GenerarPdf gPdf = new GenerarPdf(this);
/* 379 */     gPdf.setTitulos(this.titulo1, this.titulo2);
/* 380 */     gPdf.generarDocumento(new float[] { 2.0F, 0.5F, 0.5F, 0.5F, 0.5F }, this.modelo);
/*     */   }
public static void main(String arg[]){
    new MarcoListadoAgrupados("prueba","", null);
}
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.gui.listados.MarcoListadoAgrupados
 * JD-Core Version:    0.6.2
 */