/*     */ package contaes.informes.view;
/*     */ 
/*     */ import contaes.auxiliarTablas.GeneralColorRenderer;
/*     */ import contaes.auxiliarTablas.ImporteColorRenderer;
/*     */ import contaes.auxiliarTablas.TableSorter;
/*     */ import contaes.informes.model.RatiosObject;
/*     */ import contaes.informes.model.RatiosTableModel;
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
/*     */ import java.util.ArrayList;
/*     */ import java.util.Formatter;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JFileChooser;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.table.TableColumn;
/*     */ import javax.swing.table.TableColumnModel;
/*     */ 
/*     */ public class RatiosJFrame extends JFrame
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  37 */   private JPanel jContentPane = null;
/*  38 */   private JPanel jPanel = null;
/*  39 */   private JLabel jLabel = null;
/*  40 */   private JLabel jLabel1 = null;
/*  41 */   private JScrollPane jScrollPane = null;
/*  42 */   private JTable tabla = null;
/*  43 */   private RatiosTableModel modelo = null;
/*  44 */   private TableSorter tablaOrdenada = null;
/*  45 */   private JPanel jPanel1 = null;
/*  46 */   private JButton jButton1 = null;
/*  47 */   private JButton jButton2 = null;
/*  48 */   private JButton jButton3 = null;
/*     */   private String titulo1;
/*     */   private String titulo2;
/*     */   private ArrayList<RatiosObject> lista;
/*     */ 
/*     */   public RatiosJFrame(String titulo1, String titulo2, ArrayList<RatiosObject> lista)
/*     */     throws HeadlessException
/*     */   {
/*  57 */     this.titulo1 = titulo1;
/*  58 */     this.titulo2 = titulo2;
/*  59 */     this.lista = lista;
/*  60 */     initialize();
/*     */   }
/*     */ 
/*     */   private void initialize()
/*     */   {
/*  69 */     setSize(720, 425);
/*  70 */     setContentPane(getJContentPane());
/*  71 */     setTitle(Mensajes.getString("listados"));
/*     */   }
/*     */ 
/*     */   private JPanel getJContentPane()
/*     */   {
/*  80 */     if (this.jContentPane == null) {
/*  81 */       this.jContentPane = new JPanel();
/*  82 */       this.jContentPane.setLayout(new BorderLayout());
/*  83 */       this.jContentPane.setBackground(Color.white);
/*  84 */       this.jContentPane.add(getJPanel(), "North");
/*  85 */       this.jContentPane.add(getJScrollPane(), "Center");
/*  86 */       this.jContentPane.add(getJPanel1(), "South");
/*     */     }
/*  88 */     return this.jContentPane;
/*     */   }
/*     */ 
/*     */   private JPanel getJPanel()
/*     */   {
/*  97 */     if (this.jPanel == null) {
/*  98 */       BorderLayout borderLayout = new BorderLayout();
/*  99 */       borderLayout.setVgap(7);
/* 100 */       this.jLabel1 = new JLabel();
/* 101 */       this.jLabel1.setText(this.titulo2);
/* 102 */       this.jLabel = new JLabel();
/* 103 */       this.jLabel.setText(this.titulo1);
/* 104 */       this.jPanel = new JPanel();
/* 105 */       this.jPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 5));
/* 106 */       this.jPanel.setBackground(Color.white);
/* 107 */       this.jPanel.setLayout(borderLayout);
/* 108 */       this.jPanel.add(this.jLabel, "North");
/* 109 */       this.jPanel.add(this.jLabel1, "Center");
/*     */     }
/* 111 */     return this.jPanel;
/*     */   }
/*     */ 
/*     */   private JScrollPane getJScrollPane()
/*     */   {
/* 120 */     if (this.jScrollPane == null) {
/* 121 */       this.jScrollPane = new JScrollPane();
/* 122 */       this.jScrollPane.setBackground(Color.white);
/* 123 */       this.jScrollPane.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
/* 124 */       this.jScrollPane.setViewportView(getTabla());
/*     */     }
/* 126 */     return this.jScrollPane;
/*     */   }
/*     */ 
/*     */   private JTable getTabla()
/*     */   {
/* 135 */     if (this.tabla == null) {
/* 136 */       this.tabla = new JTable(getTablaOrdenada());
/* 137 */       this.tablaOrdenada.setTableHeader(this.tabla.getTableHeader());
/* 138 */       int anchoTabla = 710;
/* 139 */       TableColumn columna = this.tabla.getColumnModel().getColumn(0);
/* 140 */       columna.setPreferredWidth((int)(anchoTabla * 0.6D));
/* 141 */       columna = this.tabla.getColumnModel().getColumn(1);
/* 142 */       columna.setPreferredWidth((int)(anchoTabla * 0.2D));
/* 143 */       columna.setCellRenderer(new ImporteColorRenderer());
/* 144 */       columna = this.tabla.getColumnModel().getColumn(2);
/* 145 */       columna.setPreferredWidth((int)(anchoTabla * 0.2D));
/* 146 */       columna.setCellRenderer(new ImporteColorRenderer());
/* 147 */       this.tabla.setDefaultRenderer(Object.class, new GeneralColorRenderer());
/*     */     }
/* 149 */     return this.tabla;
/*     */   }
/*     */ 
/*     */   private RatiosTableModel getModelo() {
/* 153 */     if (this.modelo == null)
/* 154 */       this.modelo = new RatiosTableModel(this.lista);
/* 155 */     return this.modelo;
/*     */   }
/*     */ 
/*     */   private TableSorter getTablaOrdenada() {
/* 159 */     if (this.tablaOrdenada == null) {
/* 160 */       this.tablaOrdenada = new TableSorter();
/* 161 */       this.tablaOrdenada.setTableModel(getModelo());
/*     */     }
/* 163 */     return this.tablaOrdenada;
/*     */   }
/*     */ 
/*     */   private JPanel getJPanel1()
/*     */   {
/* 172 */     if (this.jPanel1 == null) {
/* 173 */       this.jPanel1 = new JPanel();
/* 174 */       this.jPanel1.setLayout(null);
/* 175 */       this.jPanel1.setBackground(Color.white);
/* 176 */       this.jPanel1.setPreferredSize(new Dimension(150, 40));
/* 177 */       this.jPanel1.add(getJButton1(), null);
/* 178 */       this.jPanel1.add(getJButton2(), null);
/* 179 */       this.jPanel1.add(getJButton3(), null);
/*     */     }
/* 181 */     return this.jPanel1;
/*     */   }
/*     */ 
/*     */   private JButton getJButton1()
/*     */   {
/* 190 */     if (this.jButton1 == null) {
/* 191 */       this.jButton1 = new JButton();
/* 192 */       this.jButton1.setBounds(new Rectangle(50, 5, 35, 29));
/* 193 */       this.jButton1.setIcon(new ImageIcon(getClass().getResource("/almacen2/iconos/asTXT.png")));
/* 194 */       this.jButton1.setToolTipText(Mensajes.getString("guardarTexto"));
/* 195 */       this.jButton1.addActionListener(new ActionListener() {
/*     */         public void actionPerformed(ActionEvent e) {
/* 197 */           RatiosJFrame.this.guardarLista();
/*     */         }
/*     */       });
/*     */     }
/* 201 */     return this.jButton1;
/*     */   }
/*     */ 
/*     */   private JButton getJButton2()
/*     */   {
/* 210 */     if (this.jButton2 == null) {
/* 211 */       this.jButton2 = new JButton();
/* 212 */       this.jButton2.setBounds(new Rectangle(95, 5, 35, 29));
/* 213 */       this.jButton2.setIcon(new ImageIcon(getClass().getResource("/almacen2/iconos/asCSV.png")));
/* 214 */       this.jButton2.setToolTipText(Mensajes.getString("guardarCSV"));
/* 215 */       this.jButton2.addActionListener(new ActionListener() {
/*     */         public void actionPerformed(ActionEvent e) {
/* 217 */           RatiosJFrame.this.guardarCSV();
/*     */         }
/*     */       });
/*     */     }
/* 221 */     return this.jButton2;
/*     */   }
/*     */ 
/*     */   private JButton getJButton3()
/*     */   {
/* 230 */     if (this.jButton3 == null) {
/* 231 */       this.jButton3 = new JButton();
/* 232 */       this.jButton3.setBounds(new Rectangle(140, 5, 35, 29));
/* 233 */       this.jButton3.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/pdf.png")));
/* 234 */       this.jButton3.setToolTipText(Mensajes.getString("guardarPDF"));
/* 235 */       this.jButton3.addActionListener(new ActionListener() {
/*     */         public void actionPerformed(ActionEvent e) {
/* 237 */           RatiosJFrame.this.guardarPDF();
/*     */         }
/*     */       });
/*     */     }
/* 241 */     return this.jButton3;
/*     */   }
/*     */ 
/*     */   private void guardarLista() {
/* 245 */     JFileChooser fc = new JFileChooser();
/* 246 */     fc.setSelectedFile(new File(new StringBuilder().append(this.titulo1).append(".txt").toString()));
/* 247 */     int retorno = fc.showSaveDialog(this);
/* 248 */     if (retorno == 0) {
/* 249 */       File archivo = fc.getSelectedFile();
/* 250 */       archivo = AddExtension.txt(archivo);
/* 251 */       GrabarFichero salida = new GrabarFichero(archivo);
/* 252 */       salida.insertar(new StringBuilder().append(this.titulo1).append("\n").append(this.titulo2).append("\n\n").toString());
/* 253 */       StringBuilder sb = new StringBuilder();
/* 254 */       Formatter formater = new Formatter(sb);
/* 255 */       formater.format("%80s %8s %8s\n", new Object[] { Mensajes.getString("nombre"), Mensajes.getString("actual"), Mensajes.getString("anterior") });
/*     */ 
/* 257 */       for (int fila = 0; fila < this.tabla.getRowCount(); fila++) {
/* 258 */         formater.format("%80s    %,5.2f    %,5.2f\n", new Object[] { this.tabla.getValueAt(fila, 0), this.tabla.getValueAt(fila, 1), this.tabla.getValueAt(fila, 2) });
/*     */       }
/*     */ 
/* 263 */       salida.insertar(sb.toString());
/* 264 */       salida.cerrar();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void guardarCSV() {
/* 269 */     JFileChooser fc = new JFileChooser();
/* 270 */     fc.setSelectedFile(new File(new StringBuilder().append(this.titulo1).append(".csv").toString()));
/* 271 */     int retorno = fc.showSaveDialog(this);
/* 272 */     if (retorno == 0) {
/* 273 */       File archivo = fc.getSelectedFile();
/* 274 */       archivo = AddExtension.csv(archivo);
/* 275 */       GrabarFichero salida = new GrabarFichero(archivo);
/* 276 */       salida.insertar(new StringBuilder().append(this.titulo1).append("\n").append(this.titulo2).append("\n\n").toString());
/* 277 */       StringBuilder sb = new StringBuilder();
/* 278 */       Formatter formater = new Formatter(sb);
/* 279 */       formater.format("%s;%s;%s\n", new Object[] { Mensajes.getString("nombre"), Mensajes.getString("actual"), Mensajes.getString("anterior") });
/*     */ 
/* 282 */       for (int fila = 0; fila < this.tabla.getRowCount(); fila++) {
/* 283 */         formater.format("%s;%,5.2f;%,5.2f\n", new Object[] { this.tabla.getValueAt(fila, 0), this.tabla.getValueAt(fila, 1), this.tabla.getValueAt(fila, 2) });
/*     */       }
/*     */ 
/* 288 */       salida.insertar(sb.toString());
/* 289 */       salida.cerrar();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void guardarPDF() {
/* 294 */     GenerarPdf gPdf = new GenerarPdf(this);
/* 295 */     gPdf.setTitulos(this.titulo1, this.titulo2);
/* 296 */     gPdf.generarDocumento(new float[] { 2.0F, 0.5F, 0.5F }, this.modelo);
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.informes.view.RatiosJFrame
 * JD-Core Version:    0.6.2
 */