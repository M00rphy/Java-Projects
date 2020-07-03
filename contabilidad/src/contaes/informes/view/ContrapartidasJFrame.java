/*     */ package contaes.informes.view;
/*     */ 
/*     */ import contaes.auxiliarTablas.GeneralColorRenderer;
/*     */ import contaes.auxiliarTablas.ImporteColorRenderer;
/*     */ import contaes.auxiliarTablas.TableSorter;
/*     */ import contaes.informes.model.ContrapartidaObject;
/*     */ import contaes.informes.model.ContrapartidasTableModel;
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
/*     */ public class ContrapartidasJFrame extends JFrame
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  41 */   private JPanel jContentPane = null;
/*  42 */   private JPanel jPanel = null;
/*  43 */   private JLabel jLabel = null;
/*  44 */   private JLabel jLabel1 = null;
/*  45 */   private JScrollPane jScrollPane = null;
/*  46 */   private JTable tabla = null;
/*  47 */   private ContrapartidasTableModel modelo = null;
/*  48 */   private TableSorter tablaOrdenada = null;
/*  49 */   private JPanel jPanel1 = null;
/*  50 */   private JButton jButton = null;
/*  51 */   private JButton jButton1 = null;
/*  52 */   private JButton jButton2 = null;
/*  53 */   private JButton jButton3 = null;
/*     */   private String titulo1;
/*     */   private String titulo2;
/*     */   private ArrayList<ContrapartidaObject> lista;
/*     */ 
/*     */   public ContrapartidasJFrame(String titulo1, String titulo2, ArrayList<ContrapartidaObject> lista)
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
/* 144 */       columna.setPreferredWidth((int)(anchoTabla * 0.8D));
/* 145 */       columna = this.tabla.getColumnModel().getColumn(1);
/* 146 */       columna.setPreferredWidth((int)(anchoTabla * 0.2D));
/* 147 */       columna.setCellRenderer(new ImporteColorRenderer());
/* 148 */       this.tabla.setDefaultRenderer(Object.class, new GeneralColorRenderer());
/*     */     }
/* 150 */     return this.tabla;
/*     */   }
/*     */ 
/*     */   private ContrapartidasTableModel getModelo() {
/* 154 */     if (this.modelo == null) {
/* 155 */       this.modelo = new ContrapartidasTableModel(this.lista);
/*     */     }
/* 157 */     return this.modelo;
/*     */   }
/*     */ 
/*     */   private TableSorter getTablaOrdenada() {
/* 161 */     if (this.tablaOrdenada == null) {
/* 162 */       this.tablaOrdenada = new TableSorter();
/* 163 */       this.tablaOrdenada.setTableModel(getModelo());
/*     */     }
/* 165 */     return this.tablaOrdenada;
/*     */   }
/*     */ 
/*     */   private JPanel getJPanel1()
/*     */   {
/* 174 */     if (this.jPanel1 == null) {
/* 175 */       this.jPanel1 = new JPanel();
/* 176 */       this.jPanel1.setLayout(null);
/* 177 */       this.jPanel1.setBackground(Color.white);
/* 178 */       this.jPanel1.setPreferredSize(new Dimension(150, 40));
/* 179 */       this.jPanel1.add(getJButton(), null);
/* 180 */       this.jPanel1.add(getJButton1(), null);
/* 181 */       this.jPanel1.add(getJButton2(), null);
/* 182 */       this.jPanel1.add(getJButton3(), null);
/*     */     }
/* 184 */     return this.jPanel1;
/*     */   }
/*     */ 
/*     */   private JButton getJButton()
/*     */   {
/* 193 */     if (this.jButton == null) {
/* 194 */       this.jButton = new JButton();
/* 195 */       this.jButton.setBounds(new Rectangle(5, 5, 35, 29));
/* 196 */       this.jButton.setIcon(new ImageIcon(getClass().getResource("/almacen2/iconos/sumatorio.png")));
/* 197 */       this.jButton.setToolTipText(Mensajes.getString("tipSumatorio"));
/* 198 */       this.jButton.addActionListener(new ActionListener()
/*     */       {
/*     */         public void actionPerformed(ActionEvent e) {
/* 201 */           ContrapartidasJFrame.this.calcularSumas();
/*     */         }
/*     */       });
/*     */     }
/* 205 */     return this.jButton;
/*     */   }
/*     */ 
/*     */   private JButton getJButton1()
/*     */   {
/* 214 */     if (this.jButton1 == null) {
/* 215 */       this.jButton1 = new JButton();
/* 216 */       this.jButton1.setBounds(new Rectangle(50, 5, 35, 29));
/* 217 */       this.jButton1.setIcon(new ImageIcon(getClass().getResource("/almacen2/iconos/asTXT.png")));
/* 218 */       this.jButton1.setToolTipText(Mensajes.getString("guardarTexto"));
/* 219 */       this.jButton1.addActionListener(new ActionListener()
/*     */       {
/*     */         public void actionPerformed(ActionEvent e) {
/* 222 */           ContrapartidasJFrame.this.guardarLista();
/*     */         }
/*     */       });
/*     */     }
/* 226 */     return this.jButton1;
/*     */   }
/*     */ 
/*     */   private JButton getJButton2()
/*     */   {
/* 235 */     if (this.jButton2 == null) {
/* 236 */       this.jButton2 = new JButton();
/* 237 */       this.jButton2.setBounds(new Rectangle(95, 5, 35, 29));
/* 238 */       this.jButton2.setIcon(new ImageIcon(getClass().getResource("/almacen2/iconos/asCSV.png")));
/* 239 */       this.jButton2.setToolTipText(Mensajes.getString("guardarCSV"));
/* 240 */       this.jButton2.addActionListener(new ActionListener()
/*     */       {
/*     */         public void actionPerformed(ActionEvent e) {
/* 243 */           ContrapartidasJFrame.this.guardarCSV();
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
/* 261 */       this.jButton3.addActionListener(new ActionListener()
/*     */       {
/*     */         public void actionPerformed(ActionEvent e) {
/* 264 */           ContrapartidasJFrame.this.guardarPDF();
/*     */         }
/*     */       });
/*     */     }
/* 268 */     return this.jButton3;
/*     */   }
/*     */ 
/*     */   private void calcularSumas()
/*     */   {
/* 273 */     Double total = Double.valueOf(0.0D);
/* 274 */     ListSelectionModel lsm = this.tabla.getSelectionModel();
/* 275 */     if (lsm.isSelectionEmpty()) {
/* 276 */       return;
/*     */     }
/* 278 */     int inicio = lsm.getMinSelectionIndex();
/* 279 */     int fin = lsm.getMaxSelectionIndex();
/*     */ 
/* 281 */     for (int index = inicio; index <= fin; index++) {
/* 282 */       if (this.tabla.isRowSelected(index)) {
/* 283 */         total = Double.valueOf(total.doubleValue() + ((ContrapartidaObject)this.lista.get(index)).getImporte().doubleValue());
/*     */       }
/*     */     }
/*     */ 
/* 287 */     DecimalFormatSymbols formato = new DecimalFormatSymbols();
/* 288 */     formato.setDecimalSeparator(',');
/* 289 */     formato.setPerMill('.');
/* 290 */     DecimalFormat fn = new DecimalFormat("#,###,##0.00", formato);
/* 291 */     String mensaje = new StringBuilder().append(Mensajes.getString("total")).append(" : ").append(fn.format(total)).toString();
/* 292 */     JOptionPane.showMessageDialog(this, mensaje, Mensajes.getString("sumas"), 1);
/*     */   }
/*     */ 
/*     */   private void guardarLista()
/*     */   {
/* 298 */     JFileChooser fc = new JFileChooser();
/* 299 */     fc.setSelectedFile(new File(new StringBuilder().append(this.titulo1).append(".txt").toString()));
/* 300 */     int retorno = fc.showSaveDialog(this);
/* 301 */     if (retorno == 0) {
/* 302 */       File archivo = fc.getSelectedFile();
/* 303 */       archivo = AddExtension.txt(archivo);
/* 304 */       GrabarFichero salida = new GrabarFichero(archivo);
/* 305 */       salida.insertar(new StringBuilder().append(this.titulo1).append("\n").append(this.titulo2).append("\n\n").toString());
/* 306 */       StringBuilder sb = new StringBuilder();
/* 307 */       Formatter formater = new Formatter(sb);
/* 308 */       formater.format("%80s %12s\n", new Object[] { Mensajes.getString("subcuenta"), Mensajes.getString("importe") });
/*     */ 
/* 311 */       for (int fila = 0; fila < this.tabla.getRowCount(); fila++) {
/* 312 */         formater.format("%80s %,12.2f\n", new Object[] { this.tabla.getValueAt(fila, 0), this.tabla.getValueAt(fila, 1) });
/*     */       }
/*     */ 
/* 316 */       salida.insertar(sb.toString());
/* 317 */       salida.cerrar();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void guardarCSV() {
/* 322 */     JFileChooser fc = new JFileChooser();
/* 323 */     fc.setSelectedFile(new File(new StringBuilder().append(this.titulo1).append(".csv").toString()));
/* 324 */     int retorno = fc.showSaveDialog(this);
/* 325 */     if (retorno == 0) {
/* 326 */       File archivo = fc.getSelectedFile();
/* 327 */       archivo = AddExtension.csv(archivo);
/* 328 */       GrabarFichero salida = new GrabarFichero(archivo);
/* 329 */       salida.insertar(new StringBuilder().append(this.titulo1).append("\n").append(this.titulo2).append("\n\n").toString());
/* 330 */       StringBuilder sb = new StringBuilder();
/* 331 */       Formatter formater = new Formatter(sb);
/* 332 */       formater.format("%s;%s\n", new Object[] { Mensajes.getString("subcuenta"), Mensajes.getString("importe") });
/*     */ 
/* 335 */       for (int fila = 0; fila < this.tabla.getRowCount(); fila++) {
/* 336 */         formater.format("%s;%,10.2f\n", new Object[] { this.tabla.getValueAt(fila, 0), this.tabla.getValueAt(fila, 1) });
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
/* 348 */     gPdf.generarDocumento(new float[] { 2.0F, 0.5F }, this.modelo);
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.informes.view.ContrapartidasJFrame
 * JD-Core Version:    0.6.2
 */