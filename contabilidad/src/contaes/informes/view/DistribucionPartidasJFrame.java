/*     */ package contaes.informes.view;
/*     */ 
/*     */ import contaes.auxiliarTablas.GeneralColorRenderer;
/*     */ import contaes.auxiliarTablas.ImporteColorRenderer;
/*     */ import contaes.auxiliarTablas.TableSorter;
/*     */ import contaes.informes.model.DistribucionPartidasObject;
/*     */ import contaes.informes.model.DistribucionPartidasTableModel;
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
/*     */ public class DistribucionPartidasJFrame extends JFrame
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  41 */   private JPanel jContentPane = null;
/*  42 */   private JPanel jPanel = null;
/*  43 */   private JLabel jLabel = null;
/*  44 */   private JLabel jLabel1 = null;
/*  45 */   private JScrollPane jScrollPane = null;
/*  46 */   private JTable tabla = null;
/*  47 */   private DistribucionPartidasTableModel modelo = null;
/*  48 */   private TableSorter tablaOrdenada = null;
/*  49 */   private JPanel jPanel1 = null;
/*  50 */   private JButton jButton = null;
/*  51 */   private JButton jButton1 = null;
/*  52 */   private JButton jButton2 = null;
/*  53 */   private JButton jButton3 = null;
/*     */   private String titulo1;
/*     */   private String titulo2;
/*     */   private ArrayList<DistribucionPartidasObject> lista;
/*     */ 
/*     */   public DistribucionPartidasJFrame(String titulo1, String titulo2, ArrayList<DistribucionPartidasObject> lista)
/*     */     throws HeadlessException
/*     */   {
/*  62 */     this.titulo1 = titulo1;
/*  63 */     this.titulo2 = titulo2;
/*  64 */     this.lista = lista;
/*  65 */     initialize();
/*     */   }
/*     */ 
/*     */   private void initialize()
/*     */   {
/*  74 */     setSize(720, 425);
/*  75 */     setContentPane(getJContentPane());
/*  76 */     setTitle(Mensajes.getString("listados"));
/*     */   }
/*     */ 
/*     */   private JPanel getJContentPane()
/*     */   {
/*  85 */     if (this.jContentPane == null) {
/*  86 */       this.jContentPane = new JPanel();
/*  87 */       this.jContentPane.setLayout(new BorderLayout());
/*  88 */       this.jContentPane.setBackground(Color.white);
/*  89 */       this.jContentPane.add(getJPanel(), "North");
/*  90 */       this.jContentPane.add(getJScrollPane(), "Center");
/*  91 */       this.jContentPane.add(getJPanel1(), "South");
/*     */     }
/*  93 */     return this.jContentPane;
/*     */   }
/*     */ 
/*     */   private JPanel getJPanel()
/*     */   {
/* 102 */     if (this.jPanel == null) {
/* 103 */       BorderLayout borderLayout = new BorderLayout();
/* 104 */       borderLayout.setVgap(7);
/* 105 */       this.jLabel1 = new JLabel();
/* 106 */       this.jLabel1.setText(this.titulo2);
/* 107 */       this.jLabel = new JLabel();
/* 108 */       this.jLabel.setText(this.titulo1);
/* 109 */       this.jPanel = new JPanel();
/* 110 */       this.jPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 5));
/* 111 */       this.jPanel.setBackground(Color.white);
/* 112 */       this.jPanel.setLayout(borderLayout);
/* 113 */       this.jPanel.add(this.jLabel, "North");
/* 114 */       this.jPanel.add(this.jLabel1, "Center");
/*     */     }
/* 116 */     return this.jPanel;
/*     */   }
/*     */ 
/*     */   private JScrollPane getJScrollPane()
/*     */   {
/* 125 */     if (this.jScrollPane == null) {
/* 126 */       this.jScrollPane = new JScrollPane();
/* 127 */       this.jScrollPane.setBackground(Color.white);
/* 128 */       this.jScrollPane.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
/* 129 */       this.jScrollPane.setViewportView(getTabla());
/*     */     }
/* 131 */     return this.jScrollPane;
/*     */   }
/*     */ 
/*     */   private JTable getTabla()
/*     */   {
/* 140 */     if (this.tabla == null) {
/* 141 */       this.tabla = new JTable(getTablaOrdenada());
/* 142 */       this.tablaOrdenada.setTableHeader(this.tabla.getTableHeader());
/* 143 */       int anchoTabla = 710;
/* 144 */       TableColumn columna = this.tabla.getColumnModel().getColumn(0);
/* 145 */       columna.setPreferredWidth((int)(anchoTabla * 0.65D));
/* 146 */       columna = this.tabla.getColumnModel().getColumn(1);
/* 147 */       columna.setPreferredWidth((int)(anchoTabla * 0.2D));
/* 148 */       columna.setCellRenderer(new ImporteColorRenderer());
/* 149 */       columna = this.tabla.getColumnModel().getColumn(2);
/* 150 */       columna.setPreferredWidth((int)(anchoTabla * 0.15D));
/* 151 */       columna.setCellRenderer(new ImporteColorRenderer());
/* 152 */       this.tabla.setDefaultRenderer(Object.class, new GeneralColorRenderer());
/*     */     }
/* 154 */     return this.tabla;
/*     */   }
/*     */ 
/*     */   private DistribucionPartidasTableModel getModelo() {
/* 158 */     if (this.modelo == null)
/* 159 */       this.modelo = new DistribucionPartidasTableModel(this.lista);
/* 160 */     return this.modelo;
/*     */   }
/*     */ 
/*     */   private TableSorter getTablaOrdenada() {
/* 164 */     if (this.tablaOrdenada == null) {
/* 165 */       this.tablaOrdenada = new TableSorter();
/* 166 */       this.tablaOrdenada.setTableModel(getModelo());
/*     */     }
/* 168 */     return this.tablaOrdenada;
/*     */   }
/*     */ 
/*     */   private JPanel getJPanel1()
/*     */   {
/* 177 */     if (this.jPanel1 == null) {
/* 178 */       this.jPanel1 = new JPanel();
/* 179 */       this.jPanel1.setLayout(null);
/* 180 */       this.jPanel1.setBackground(Color.white);
/* 181 */       this.jPanel1.setPreferredSize(new Dimension(150, 40));
/* 182 */       this.jPanel1.add(getJButton(), null);
/* 183 */       this.jPanel1.add(getJButton1(), null);
/* 184 */       this.jPanel1.add(getJButton2(), null);
/* 185 */       this.jPanel1.add(getJButton3(), null);
/*     */     }
/* 187 */     return this.jPanel1;
/*     */   }
/*     */ 
/*     */   private JButton getJButton()
/*     */   {
/* 196 */     if (this.jButton == null) {
/* 197 */       this.jButton = new JButton();
/* 198 */       this.jButton.setBounds(new Rectangle(5, 5, 35, 29));
/* 199 */       this.jButton.setIcon(new ImageIcon(getClass().getResource("/almacen2/iconos/sumatorio.png")));
/* 200 */       this.jButton.setToolTipText(Mensajes.getString("tipSumatorio"));
/* 201 */       this.jButton.addActionListener(new ActionListener() {
/*     */         public void actionPerformed(ActionEvent e) {
/* 203 */           DistribucionPartidasJFrame.this.calcularSumas();
/*     */         }
/*     */       });
/*     */     }
/* 207 */     return this.jButton;
/*     */   }
/*     */ 
/*     */   private JButton getJButton1()
/*     */   {
/* 216 */     if (this.jButton1 == null) {
/* 217 */       this.jButton1 = new JButton();
/* 218 */       this.jButton1.setBounds(new Rectangle(50, 5, 35, 29));
/* 219 */       this.jButton1.setIcon(new ImageIcon(getClass().getResource("/almacen2/iconos/asTXT.png")));
/* 220 */       this.jButton1.setToolTipText(Mensajes.getString("guardarTexto"));
/* 221 */       this.jButton1.addActionListener(new ActionListener() {
/*     */         public void actionPerformed(ActionEvent e) {
/* 223 */           DistribucionPartidasJFrame.this.guardarLista();
/*     */         }
/*     */       });
/*     */     }
/* 227 */     return this.jButton1;
/*     */   }
/*     */ 
/*     */   private JButton getJButton2()
/*     */   {
/* 236 */     if (this.jButton2 == null) {
/* 237 */       this.jButton2 = new JButton();
/* 238 */       this.jButton2.setBounds(new Rectangle(95, 5, 35, 29));
/* 239 */       this.jButton2.setIcon(new ImageIcon(getClass().getResource("/almacen2/iconos/asCSV.png")));
/* 240 */       this.jButton2.setToolTipText(Mensajes.getString("guardarCSV"));
/* 241 */       this.jButton2.addActionListener(new ActionListener() {
/*     */         public void actionPerformed(ActionEvent e) {
/* 243 */           DistribucionPartidasJFrame.this.guardarCSV();
/*     */         }
/*     */       });
/*     */     }
/* 247 */     return this.jButton2;
/*     */   }
/*     */ 
/*     */   private JButton getJButton3()
/*     */   {
/* 256 */     if (this.jButton3 == null) {
/* 257 */       this.jButton3 = new JButton();
/* 258 */       this.jButton3.setBounds(new Rectangle(140, 5, 35, 29));
/* 259 */       this.jButton3.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/pdf.png")));
/* 260 */       this.jButton3.setToolTipText(Mensajes.getString("guardarPDF"));
/* 261 */       this.jButton3.addActionListener(new ActionListener() {
/*     */         public void actionPerformed(ActionEvent e) {
/* 263 */           DistribucionPartidasJFrame.this.guardarPDF();
/*     */         }
/*     */       });
/*     */     }
/* 267 */     return this.jButton3;
/*     */   }
/*     */ 
/*     */   private void calcularSumas()
/*     */   {
/* 272 */     Double total = Double.valueOf(0.0D);
/* 273 */     ListSelectionModel lsm = this.tabla.getSelectionModel();
/* 274 */     if (lsm.isSelectionEmpty()) {
/* 275 */       return;
/*     */     }
/* 277 */     int inicio = lsm.getMinSelectionIndex();
/* 278 */     int fin = lsm.getMaxSelectionIndex();
/*     */ 
/* 280 */     for (int index = inicio; index <= fin; index++) {
/* 281 */       if (this.tabla.isRowSelected(index)) {
/* 282 */         total = Double.valueOf(total.doubleValue() + ((DistribucionPartidasObject)this.lista.get(index)).getImporte().doubleValue());
/*     */       }
/*     */     }
/*     */ 
/* 286 */     DecimalFormatSymbols formato = new DecimalFormatSymbols();
/* 287 */     formato.setDecimalSeparator(',');
/* 288 */     formato.setPerMill('.');
/* 289 */     DecimalFormat fn = new DecimalFormat("#,###,##0.00", formato);
/* 290 */     String mensaje = new StringBuilder().append(Mensajes.getString("total")).append(" : ").append(fn.format(total)).toString();
/* 291 */     JOptionPane.showMessageDialog(this, mensaje, Mensajes.getString("sumas"), 1);
/*     */   }
/*     */ 
/*     */   private void guardarLista()
/*     */   {
/* 297 */     JFileChooser fc = new JFileChooser();
/* 298 */     fc.setSelectedFile(new File(new StringBuilder().append(this.titulo1).append(".txt").toString()));
/* 299 */     int retorno = fc.showSaveDialog(this);
/* 300 */     if (retorno == 0) {
/* 301 */       File archivo = fc.getSelectedFile();
/* 302 */       archivo = AddExtension.txt(archivo);
/* 303 */       GrabarFichero salida = new GrabarFichero(archivo);
/* 304 */       salida.insertar(new StringBuilder().append(this.titulo1).append("\n").append(this.titulo2).append("\n\n").toString());
/* 305 */       StringBuilder sb = new StringBuilder();
/* 306 */       Formatter formater = new Formatter(sb);
/* 307 */       formater.format("%80s %12s %5s\n", new Object[] { Mensajes.getString("nombre"), Mensajes.getString("importe"), "%" });
/*     */ 
/* 309 */       for (int fila = 0; fila < this.tabla.getRowCount(); fila++) {
/* 310 */         formater.format("%80s %,12.2f %,5.2f\n", new Object[] { this.tabla.getValueAt(fila, 0), this.tabla.getValueAt(fila, 1), this.tabla.getValueAt(fila, 2) });
/*     */       }
/*     */ 
/* 315 */       salida.insertar(sb.toString());
/* 316 */       salida.cerrar();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void guardarCSV() {
/* 321 */     JFileChooser fc = new JFileChooser();
/* 322 */     fc.setSelectedFile(new File(new StringBuilder().append(this.titulo1).append(".csv").toString()));
/* 323 */     int retorno = fc.showSaveDialog(this);
/* 324 */     if (retorno == 0) {
/* 325 */       File archivo = fc.getSelectedFile();
/* 326 */       archivo = AddExtension.csv(archivo);
/* 327 */       GrabarFichero salida = new GrabarFichero(archivo);
/* 328 */       salida.insertar(new StringBuilder().append(this.titulo1).append("\n").append(this.titulo2).append("\n\n").toString());
/* 329 */       StringBuilder sb = new StringBuilder();
/* 330 */       Formatter formater = new Formatter(sb);
/* 331 */       formater.format("%s;%s;%s\n", new Object[] { Mensajes.getString("nombre"), Mensajes.getString("importe"), "%" });
/*     */ 
/* 334 */       for (int fila = 0; fila < this.tabla.getRowCount(); fila++) {
/* 335 */         formater.format("%s;%,12.2f;%,5.2f\n", new Object[] { this.tabla.getValueAt(fila, 0), this.tabla.getValueAt(fila, 1), this.tabla.getValueAt(fila, 2) });
/*     */       }
/*     */ 
/* 340 */       salida.insertar(sb.toString());
/* 341 */       salida.cerrar();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void guardarPDF() {
/* 346 */     GenerarPdf gPdf = new GenerarPdf(this);
/* 347 */     gPdf.setTitulos(this.titulo1, this.titulo2);
/* 348 */     gPdf.generarDocumento(new float[] { 2.0F, 0.5F, 0.5F }, this.modelo);
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.informes.view.DistribucionPartidasJFrame
 * JD-Core Version:    0.6.2
 */