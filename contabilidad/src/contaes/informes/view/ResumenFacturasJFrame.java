/*     */ package contaes.informes.view;
/*     */ 
/*     */ import contaes.auxiliarTablas.GeneralColorRenderer;
/*     */ import contaes.auxiliarTablas.ImporteColorRenderer;
/*     */ import contaes.auxiliarTablas.TableSorter;
/*     */ import contaes.informes.model.ResumenFacturasObject;
/*     */ import contaes.informes.model.ResumenFacturasTableModel;
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
/*     */ public class ResumenFacturasJFrame extends JFrame
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  41 */   private JPanel jContentPane = null;
/*  42 */   private JPanel jPanel = null;
/*  43 */   private JLabel jLabel = null;
/*  44 */   private JLabel jLabel1 = null;
/*  45 */   private JScrollPane jScrollPane = null;
/*  46 */   private JTable tabla = null;
/*  47 */   private ResumenFacturasTableModel modelo = null;
/*  48 */   private TableSorter tablaOrdenada = null;
/*  49 */   private JPanel jPanel1 = null;
/*  50 */   private JButton jButton = null;
/*  51 */   private JButton jButton1 = null;
/*  52 */   private JButton jButton2 = null;
/*  53 */   private JButton jButton3 = null;
/*     */   private String titulo1;
/*     */   private String titulo2;
/*     */   private ArrayList<ResumenFacturasObject> lista;
/*     */ 
/*     */   public ResumenFacturasJFrame(String titulo1, String titulo2, ArrayList<ResumenFacturasObject> lista)
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
/* 144 */       columna.setPreferredWidth((int)(anchoTabla * 0.2D));
/* 145 */       columna = this.tabla.getColumnModel().getColumn(1);
/* 146 */       columna.setPreferredWidth((int)(anchoTabla * 0.6D));
/* 147 */       columna = this.tabla.getColumnModel().getColumn(2);
/* 148 */       columna.setPreferredWidth((int)(anchoTabla * 0.2D));
/* 149 */       columna.setCellRenderer(new ImporteColorRenderer());
/* 150 */       this.tabla.setDefaultRenderer(Object.class, new GeneralColorRenderer());
/*     */     }
/* 152 */     return this.tabla;
/*     */   }
/*     */ 
/*     */   private ResumenFacturasTableModel getModelo() {
/* 156 */     if (this.modelo == null) {
/* 157 */       this.modelo = new ResumenFacturasTableModel(this.lista);
/*     */     }
/* 159 */     return this.modelo;
/*     */   }
/*     */ 
/*     */   private TableSorter getTablaOrdenada() {
/* 163 */     if (this.tablaOrdenada == null) {
/* 164 */       this.tablaOrdenada = new TableSorter();
/* 165 */       this.tablaOrdenada.setTableModel(getModelo());
/*     */     }
/* 167 */     return this.tablaOrdenada;
/*     */   }
/*     */ 
/*     */   private JPanel getJPanel1()
/*     */   {
/* 176 */     if (this.jPanel1 == null) {
/* 177 */       this.jPanel1 = new JPanel();
/* 178 */       this.jPanel1.setLayout(null);
/* 179 */       this.jPanel1.setBackground(Color.white);
/* 180 */       this.jPanel1.setPreferredSize(new Dimension(150, 40));
/* 181 */       this.jPanel1.add(getJButton(), null);
/* 182 */       this.jPanel1.add(getJButton1(), null);
/* 183 */       this.jPanel1.add(getJButton2(), null);
/* 184 */       this.jPanel1.add(getJButton3(), null);
/*     */     }
/* 186 */     return this.jPanel1;
/*     */   }
/*     */ 
/*     */   private JButton getJButton()
/*     */   {
/* 195 */     if (this.jButton == null) {
/* 196 */       this.jButton = new JButton();
/* 197 */       this.jButton.setBounds(new Rectangle(5, 5, 35, 29));
/* 198 */       this.jButton.setIcon(new ImageIcon(getClass().getResource("/almacen2/iconos/sumatorio.png")));
/* 199 */       this.jButton.setToolTipText(Mensajes.getString("tipSumatorio"));
/* 200 */       this.jButton.addActionListener(new ActionListener()
/*     */       {
/*     */         public void actionPerformed(ActionEvent e) {
/* 203 */           ResumenFacturasJFrame.this.calcularSumas();
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
/* 221 */       this.jButton1.addActionListener(new ActionListener()
/*     */       {
/*     */         public void actionPerformed(ActionEvent e) {
/* 224 */           ResumenFacturasJFrame.this.guardarLista();
/*     */         }
/*     */       });
/*     */     }
/* 228 */     return this.jButton1;
/*     */   }
/*     */ 
/*     */   private JButton getJButton2()
/*     */   {
/* 237 */     if (this.jButton2 == null) {
/* 238 */       this.jButton2 = new JButton();
/* 239 */       this.jButton2.setBounds(new Rectangle(95, 5, 35, 29));
/* 240 */       this.jButton2.setIcon(new ImageIcon(getClass().getResource("/almacen2/iconos/asCSV.png")));
/* 241 */       this.jButton2.setToolTipText(Mensajes.getString("guardarCSV"));
/* 242 */       this.jButton2.addActionListener(new ActionListener()
/*     */       {
/*     */         public void actionPerformed(ActionEvent e) {
/* 245 */           ResumenFacturasJFrame.this.guardarCSV();
/*     */         }
/*     */       });
/*     */     }
/* 249 */     return this.jButton2;
/*     */   }
/*     */ 
/*     */   private JButton getJButton3()
/*     */   {
/* 258 */     if (this.jButton3 == null) {
/* 259 */       this.jButton3 = new JButton();
/* 260 */       this.jButton3.setBounds(new Rectangle(140, 5, 35, 29));
/* 261 */       this.jButton3.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/pdf.png")));
/* 262 */       this.jButton3.setToolTipText(Mensajes.getString("guardarPDF"));
/* 263 */       this.jButton3.addActionListener(new ActionListener()
/*     */       {
/*     */         public void actionPerformed(ActionEvent e) {
/* 266 */           ResumenFacturasJFrame.this.guardarPDF();
/*     */         }
/*     */       });
/*     */     }
/* 270 */     return this.jButton3;
/*     */   }
/*     */ 
/*     */   private void calcularSumas()
/*     */   {
/* 275 */     Double total = Double.valueOf(0.0D);
/* 276 */     ListSelectionModel lsm = this.tabla.getSelectionModel();
/* 277 */     if (lsm.isSelectionEmpty()) {
/* 278 */       return;
/*     */     }
/* 280 */     int inicio = lsm.getMinSelectionIndex();
/* 281 */     int fin = lsm.getMaxSelectionIndex();
/*     */ 
/* 283 */     for (int index = inicio; index <= fin; index++) {
/* 284 */       if (this.tabla.isRowSelected(index)) {
/* 285 */         total = Double.valueOf(total.doubleValue() + ((ResumenFacturasObject)this.lista.get(index)).getImporte().doubleValue());
/*     */       }
/*     */     }
/*     */ 
/* 289 */     DecimalFormatSymbols formato = new DecimalFormatSymbols();
/* 290 */     formato.setDecimalSeparator(',');
/* 291 */     formato.setPerMill('.');
/* 292 */     DecimalFormat fn = new DecimalFormat("#,###,##0.00", formato);
/* 293 */     String mensaje = new StringBuilder().append(Mensajes.getString("total")).append(" : ").append(fn.format(total)).toString();
/* 294 */     JOptionPane.showMessageDialog(this, mensaje, Mensajes.getString("sumas"), 1);
/*     */   }
/*     */ 
/*     */   private void guardarLista()
/*     */   {
/* 300 */     JFileChooser fc = new JFileChooser();
/* 301 */     fc.setSelectedFile(new File(new StringBuilder().append(this.titulo1).append(".txt").toString()));
/* 302 */     int retorno = fc.showSaveDialog(this);
/* 303 */     if (retorno == 0) {
/* 304 */       File archivo = fc.getSelectedFile();
/* 305 */       archivo = AddExtension.txt(archivo);
/* 306 */       GrabarFichero salida = new GrabarFichero(archivo);
/* 307 */       salida.insertar(new StringBuilder().append(this.titulo1).append("\n").append(this.titulo2).append("\n\n").toString());
/* 308 */       StringBuilder sb = new StringBuilder();
/* 309 */       Formatter formater = new Formatter(sb);
/* 310 */       formater.format("%10s %60s %12s\n", new Object[] { Mensajes.getString("mes"), Mensajes.getString("nombre"), Mensajes.getString("importe") });
/*     */ 
/* 313 */       for (int fila = 0; fila < this.tabla.getRowCount(); fila++) {
/* 314 */         formater.format("%10s %60s %,12.2f\n", new Object[] { this.tabla.getValueAt(fila, 0), this.tabla.getValueAt(fila, 1), this.tabla.getValueAt(fila, 2) });
/*     */       }
/*     */ 
/* 319 */       salida.insertar(sb.toString());
/* 320 */       salida.cerrar();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void guardarCSV() {
/* 325 */     JFileChooser fc = new JFileChooser();
/* 326 */     fc.setSelectedFile(new File(new StringBuilder().append(this.titulo1).append(".csv").toString()));
/* 327 */     int retorno = fc.showSaveDialog(this);
/* 328 */     if (retorno == 0) {
/* 329 */       File archivo = fc.getSelectedFile();
/* 330 */       archivo = AddExtension.csv(archivo);
/* 331 */       GrabarFichero salida = new GrabarFichero(archivo);
/* 332 */       salida.insertar(new StringBuilder().append(this.titulo1).append("\n").append(this.titulo2).append("\n\n").toString());
/* 333 */       StringBuilder sb = new StringBuilder();
/* 334 */       Formatter formater = new Formatter(sb);
/* 335 */       formater.format("%s;%s;%s\n", new Object[] { Mensajes.getString("mes"), Mensajes.getString("nombre"), Mensajes.getString("importe") });
/*     */ 
/* 338 */       for (int fila = 0; fila < this.tabla.getRowCount(); fila++) {
/* 339 */         formater.format("%s;%s;%,10.2f\n", new Object[] { this.tabla.getValueAt(fila, 0), this.tabla.getValueAt(fila, 1), this.tabla.getValueAt(fila, 2) });
/*     */       }
/*     */ 
/* 344 */       salida.insertar(sb.toString());
/* 345 */       salida.cerrar();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void guardarPDF() {
/* 350 */     GenerarPdf gPdf = new GenerarPdf(this);
/* 351 */     gPdf.setTitulos(this.titulo1, this.titulo2);
/* 352 */     gPdf.generarDocumento(new float[] { 0.5F, 2.0F, 0.5F }, this.modelo);
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.informes.view.ResumenFacturasJFrame
 * JD-Core Version:    0.6.2
 */