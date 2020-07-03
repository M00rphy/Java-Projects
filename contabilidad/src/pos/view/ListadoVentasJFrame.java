/*     */ package pos.view;
/*     */ 
/*     */ import contaes.auxiliarTablas.BooleanColorRenderer;
/*     */ import contaes.auxiliarTablas.DerechaColorRenderer;
/*     */ import contaes.auxiliarTablas.FechaColorRenderer;
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
/*     */ import java.text.SimpleDateFormat;
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
/*     */ import pos.model.ResumenVentaTicketMio;
/*     */ import pos.model.ResumenVentaTicketMioTableModel;
/*     */ 
/*     */ public class ListadoVentasJFrame extends JFrame
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  42 */   private JPanel jContentPane = null;
/*  43 */   private JPanel jPanel = null;
/*  44 */   private JLabel jLabel = null;
/*  45 */   private JLabel jLabel1 = null;
/*  46 */   private JScrollPane jScrollPane = null;
/*  47 */   private JTable tabla = null;
/*  48 */   private ResumenVentaTicketMioTableModel modelo = null;
/*  49 */   private TableSorter tablaOrdenada = null;
/*  50 */   private JPanel jPanel1 = null;
/*  51 */   private JButton jButton2 = null;
/*     */   private String titulo1;
/*     */   private String titulo2;
/*     */   private ArrayList<ResumenVentaTicketMio> lista;
/*     */ 
/*     */   public ListadoVentasJFrame(String titulo1, String titulo2, ArrayList<ResumenVentaTicketMio> lista)
/*     */     throws HeadlessException
/*     */   {
/*  59 */     this.titulo1 = titulo1;
/*  60 */     this.titulo2 = titulo2;
/*  61 */     this.lista = lista;
/*  62 */     initialize();
/*     */   }
/*     */ 
/*     */   private void initialize()
/*     */   {
/*  71 */     setSize(720, 425);
/*  72 */     setContentPane(getJContentPane());
/*  73 */     setTitle(Mensajes.getString("listados"));
/*     */   }
/*     */ 
/*     */   private JPanel getJContentPane()
/*     */   {
/*  82 */     if (this.jContentPane == null) {
/*  83 */       this.jContentPane = new JPanel();
/*  84 */       this.jContentPane.setLayout(new BorderLayout());
/*  85 */       this.jContentPane.setBackground(Color.white);
/*  86 */       this.jContentPane.add(getJPanel(), "North");
/*  87 */       this.jContentPane.add(getJScrollPane(), "Center");
/*  88 */       this.jContentPane.add(getJPanel1(), "South");
/*     */     }
/*  90 */     return this.jContentPane;
/*     */   }
/*     */ 
/*     */   private JPanel getJPanel()
/*     */   {
/*  99 */     if (this.jPanel == null) {
/* 100 */       BorderLayout borderLayout = new BorderLayout();
/* 101 */       borderLayout.setVgap(7);
/* 102 */       this.jLabel1 = new JLabel();
/* 103 */       this.jLabel1.setText(this.titulo2);
/* 104 */       this.jLabel = new JLabel();
/* 105 */       this.jLabel.setText(this.titulo1);
/* 106 */       this.jPanel = new JPanel();
/* 107 */       this.jPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 5));
/* 108 */       this.jPanel.setBackground(Color.white);
/* 109 */       this.jPanel.setLayout(borderLayout);
/* 110 */       this.jPanel.add(this.jLabel, "North");
/* 111 */       this.jPanel.add(this.jLabel1, "Center");
/*     */     }
/* 113 */     return this.jPanel;
/*     */   }
/*     */ 
/*     */   private JScrollPane getJScrollPane()
/*     */   {
/* 122 */     if (this.jScrollPane == null) {
/* 123 */       this.jScrollPane = new JScrollPane();
/* 124 */       this.jScrollPane.setBackground(Color.white);
/* 125 */       this.jScrollPane.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
/* 126 */       this.jScrollPane.setViewportView(getTabla());
/*     */     }
/* 128 */     return this.jScrollPane;
/*     */   }
/*     */ 
/*     */   private JTable getTabla()
/*     */   {
/* 137 */     if (this.tabla == null) {
/* 138 */       this.tabla = new JTable(getTablaOrdenada());
/* 139 */       this.tablaOrdenada.setTableHeader(this.tabla.getTableHeader());
/* 140 */       int anchoTabla = 710;
/* 141 */       TableColumn columna = this.tabla.getColumnModel().getColumn(0);
/* 142 */       columna.setPreferredWidth((int)(anchoTabla * 0.06D));
/*     */ 
/* 144 */       columna = this.tabla.getColumnModel().getColumn(1);
/* 145 */       columna.setPreferredWidth((int)(anchoTabla * 0.11D));
/* 146 */       columna.setCellRenderer(new FechaColorRenderer());
/*     */ 
/* 148 */       columna = this.tabla.getColumnModel().getColumn(2);
/* 149 */       columna.setPreferredWidth((int)(anchoTabla * 0.3D));
/* 150 */       columna.setCellRenderer(new GeneralColorRenderer());
/*     */ 
/* 152 */       columna = this.tabla.getColumnModel().getColumn(3);
/* 153 */       columna.setPreferredWidth((int)(anchoTabla * 0.05D));
/* 154 */       columna.setCellRenderer(new DerechaColorRenderer());
/*     */ 
/* 156 */       columna = this.tabla.getColumnModel().getColumn(4);
/* 157 */       columna.setPreferredWidth((int)(anchoTabla * 0.1D));
/* 158 */       columna.setCellRenderer(new ImporteColorRenderer());
/*     */ 
/* 160 */       columna = this.tabla.getColumnModel().getColumn(5);
/* 161 */       columna.setPreferredWidth((int)(anchoTabla * 0.1D));
/* 162 */       columna.setCellRenderer(new ImporteColorRenderer());
/*     */ 
/* 164 */       columna = this.tabla.getColumnModel().getColumn(6);
/* 165 */       columna.setPreferredWidth((int)(anchoTabla * 0.03D));
/* 166 */       columna.setCellRenderer(new DerechaColorRenderer());
/*     */ 
/* 168 */       columna = this.tabla.getColumnModel().getColumn(7);
/* 169 */       columna.setPreferredWidth((int)(anchoTabla * 0.1D));
/* 170 */       columna.setCellRenderer(new GeneralColorRenderer());
/*     */ 
/* 172 */       columna = this.tabla.getColumnModel().getColumn(8);
/* 173 */       columna.setPreferredWidth((int)(anchoTabla * 0.05D));
/* 174 */       columna.setCellRenderer(new BooleanColorRenderer());
/*     */ 
/* 176 */       columna = this.tabla.getColumnModel().getColumn(9);
/* 177 */       columna.setPreferredWidth((int)(anchoTabla * 0.1D));
/* 178 */       columna.setCellRenderer(new GeneralColorRenderer());
/*     */ 
/* 180 */       this.tabla.setDefaultRenderer(Object.class, new GeneralColorRenderer());
/*     */     }
/* 182 */     return this.tabla;
/*     */   }
/*     */ 
/*     */   private ResumenVentaTicketMioTableModel getModelo() {
/* 186 */     if (this.modelo == null) {
/* 187 */       this.modelo = new ResumenVentaTicketMioTableModel(this.lista);
/*     */     }
/* 189 */     return this.modelo;
/*     */   }
/*     */ 
/*     */   private TableSorter getTablaOrdenada() {
/* 193 */     if (this.tablaOrdenada == null) {
/* 194 */       this.tablaOrdenada = new TableSorter();
/* 195 */       this.tablaOrdenada.setTableModel(getModelo());
/*     */     }
/* 197 */     return this.tablaOrdenada;
/*     */   }
/*     */ 
/*     */   private JPanel getJPanel1()
/*     */   {
/* 206 */     if (this.jPanel1 == null) {
/* 207 */       this.jPanel1 = new JPanel();
/* 208 */       this.jPanel1.setLayout(null);
/* 209 */       this.jPanel1.setBackground(Color.white);
/* 210 */       this.jPanel1.setPreferredSize(new Dimension(150, 40));
/* 211 */       this.jPanel1.add(getJButton2(), null);
/*     */     }
/* 213 */     return this.jPanel1;
/*     */   }
/*     */ 
/*     */   private JButton getJButton2()
/*     */   {
/* 222 */     if (this.jButton2 == null) {
/* 223 */       this.jButton2 = new JButton();
/* 224 */       this.jButton2.setBounds(new Rectangle(95, 5, 35, 29));
/* 225 */       this.jButton2.setIcon(new ImageIcon(getClass().getResource("/almacen2/iconos/asCSV.png")));
/* 226 */       this.jButton2.setToolTipText(Mensajes.getString("guardarCSV"));
/* 227 */       this.jButton2.addActionListener(new ActionListener()
/*     */       {
/*     */         public void actionPerformed(ActionEvent e) {
/* 230 */           ListadoVentasJFrame.this.guardarCSV();
/*     */         }
/*     */       });
/*     */     }
/* 234 */     return this.jButton2;
/*     */   }
/*     */ 
/*     */   private void guardarCSV() {
/* 238 */     JFileChooser fc = new JFileChooser();
/* 239 */     fc.setSelectedFile(new File("ventas.csv"));
/* 240 */     int retorno = fc.showSaveDialog(this);
/* 241 */     if (retorno == 0) {
/* 242 */       SimpleDateFormat sdff = new SimpleDateFormat("dd/MM/yyyy");
/* 243 */       File archivo = fc.getSelectedFile();
/* 244 */       archivo = AddExtension.csv(archivo);
/* 245 */       GrabarFichero salida = new GrabarFichero(archivo);
/*     */ 
/* 249 */       StringBuilder sb = new StringBuilder();
/* 250 */       Formatter formater = new Formatter(sb);
/* 251 */       formater.format("%s;%s;%s;%s;%s;%s;%s;%s;%s\n", new Object[] { "Ticket", "Fecha", "Descripción", "Importe", "pl", "Medio de Pago", "IdMediopago", "Familia", "Notas" });
/*     */ 
/* 253 */       for (ResumenVentaTicketMio venta : this.lista) {
/* 254 */         String plazos = "";
/* 255 */         if (venta.getPlazos() == 2) {
/* 256 */           plazos = "Resto";
/*     */         }
/* 258 */         else if (venta.getPlazos() == 1) {
/* 259 */           plazos = "A cta.";
/*     */         }
/* 261 */         String fecha = "";
/* 262 */         if (venta.getFecha() != null)
/* 263 */           fecha = sdff.format(venta.getFecha());
/* 264 */         String ticket = "";
/* 265 */         if (venta.getTicket() != -1)
/* 266 */           ticket = Integer.toString(venta.getTicket());
/* 267 */         String medioPago = "";
/* 268 */         if (venta.getMedioPago() != null)
/* 269 */           medioPago = venta.getMedioPago();
/* 270 */         String idMedioP = "";
/* 271 */         if (venta.getIdMedioPago() != -1)
/* 272 */           idMedioP = Integer.toString(venta.getIdMedioPago());
/* 273 */         String almacen = "";
/* 274 */         if (venta.getAlmacen() != -1)
/* 275 */           almacen = Integer.toString(venta.getAlmacen());
/* 276 */         String nota = "";
/* 277 */         if (venta.getNota() != null) {
/* 278 */           nota = venta.getNota();
/*     */         }
/*     */ 
/* 293 */         formater.format("%s;%s;%s;%.2f;%s;%s;%s;%s;%s\n", new Object[] { ticket, fecha, venta.getDescripcion(), Double.valueOf(venta.getTotal()), plazos, medioPago, idMedioP, almacen, nota });
/*     */       }
/*     */ 
/* 300 */       salida.insertar(sb.toString());
/* 301 */       salida.cerrar();
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.view.ListadoVentasJFrame
 * JD-Core Version:    0.6.2
 */