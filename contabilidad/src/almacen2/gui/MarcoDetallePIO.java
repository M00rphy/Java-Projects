/*     */ package almacen2.gui;
/*     */ 
/*     */ import almacen2.data.PIOListObject;
/*     */ import almacen2.data.PIOListTableModel;
/*     */ import almacen2.data.PIOObject;
/*     */ import contaes.Inicio;
/*     */ import contaes.MarcoInicio;
/*     */ import contaes.auxiliarTablas.DerechaColorRenderer;
/*     */ import contaes.auxiliarTablas.GeneralColorRenderer;
/*     */ import contaes.auxiliarTablas.ImporteColorRenderer;
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
/*     */ import java.util.Collections;
/*     */ import java.util.Formatter;
import java.util.List;
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
/*     */ public class MarcoDetallePIO extends JFrame
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  43 */   private JPanel jContentPane = null;
/*  44 */   private JPanel jPanel = null;
/*  45 */   private JLabel jLabel = null;
/*  46 */   private JLabel jLabel1 = null;
/*  47 */   private JScrollPane jScrollPane = null;
/*  48 */   private JTable tabla = null;
/*  49 */   private PIOListTableModel modelo = null;
/*  50 */   private JPanel jPanel1 = null;
/*  51 */   private JButton jButton = null;
/*  52 */   private JButton jButton1 = null;
/*  53 */   private JButton jButton2 = null;
/*     */   private String referencia;
/*     */   private String descripcion;
/*     */   private ArrayList<Integer> id;
/*     */   private ArrayList<PIOListObject> lista;
/*     */   private int stock;
/*     */   private double beneficio;
/*     */ 
/*     */   public MarcoDetallePIO(String referencia, String descripcion, ArrayList<Integer> id, ArrayList<PIOListObject> lista)
/*     */     throws HeadlessException
/*     */   {
/*  64 */     this.referencia = referencia;
/*  65 */     this.descripcion = descripcion;
/*  66 */     this.id = id;
/*  67 */     this.lista = lista;
/*  68 */     this.beneficio = ((PIOListObject)lista.get(lista.size() - 1)).getImporte().doubleValue();
/*  69 */     this.stock = Integer.parseInt(((PIOListObject)lista.get(lista.size() - 1)).getUnidades());
/*  70 */     initialize();
/*     */   }
/*     */ 
/*     */   private void initialize()
/*     */   {
/*  79 */     setSize(300, 425);
/*  80 */     setContentPane(getJContentPane());
/*  81 */     setTitle(Mensajes.getString("detalle"));
/*     */   }
/*     */ 
/*     */   private JPanel getJContentPane()
/*     */   {
/*  90 */     if (this.jContentPane == null) {
/*  91 */       this.jContentPane = new JPanel();
/*  92 */       this.jContentPane.setLayout(new BorderLayout());
/*  93 */       this.jContentPane.setBackground(Color.white);
/*  94 */       this.jContentPane.add(getJPanel(), "North");
/*  95 */       this.jContentPane.add(getJScrollPane(), "Center");
/*  96 */       this.jContentPane.add(getJPanel1(), "South");
/*     */     }
/*  98 */     return this.jContentPane;
/*     */   }
/*     */ 
/*     */   private JPanel getJPanel()
/*     */   {
/* 107 */     if (this.jPanel == null) {
/* 108 */       BorderLayout borderLayout = new BorderLayout();
/* 109 */       borderLayout.setVgap(7);
/* 110 */       this.jLabel1 = new JLabel();
/* 111 */       this.jLabel1.setText(this.descripcion);
/* 112 */       this.jLabel = new JLabel();
/* 113 */       this.jLabel.setText(this.referencia);
/* 114 */       this.jPanel = new JPanel();
/* 115 */       this.jPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 5));
/* 116 */       this.jPanel.setBackground(Color.white);
/* 117 */       this.jPanel.setLayout(borderLayout);
/* 118 */       this.jPanel.add(this.jLabel, "North");
/* 119 */       this.jPanel.add(this.jLabel1, "Center");
/*     */     }
/* 121 */     return this.jPanel;
/*     */   }
/*     */ 
/*     */   private JScrollPane getJScrollPane()
/*     */   {
/* 130 */     if (this.jScrollPane == null) {
/* 131 */       this.jScrollPane = new JScrollPane();
/* 132 */       this.jScrollPane.setBackground(Color.white);
/* 133 */       this.jScrollPane.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
/* 134 */       this.jScrollPane.setViewportView(getTabla());
/*     */     }
/* 136 */     return this.jScrollPane;
/*     */   }
/*     */ 
/*     */   private JTable getTabla()
/*     */   {
/* 145 */     if (this.tabla == null) {
/* 146 */       this.tabla = new JTable(getModelo());
/* 147 */       this.tabla.setBackground(Color.white);
/* 148 */       TableColumn columna = this.tabla.getColumnModel().getColumn(1);
/* 149 */       columna.setCellRenderer(new DerechaColorRenderer());
/* 150 */       columna = this.tabla.getColumnModel().getColumn(2);
/* 151 */       columna.setCellRenderer(new ImporteColorRenderer());
/* 152 */       this.tabla.setDefaultRenderer(Object.class, new GeneralColorRenderer());
/*     */     }
/* 154 */     return this.tabla;
/*     */   }
/*     */ 
/*     */   private PIOListTableModel getModelo() {
/* 158 */     if (this.modelo == null) {
/* 159 */       this.modelo = new PIOListTableModel(this.lista);
/*     */     }
/* 161 */     return this.modelo;
/*     */   }
/*     */ 
/*     */   private JPanel getJPanel1()
/*     */   {
/* 170 */     if (this.jPanel1 == null) {
/* 171 */       this.jPanel1 = new JPanel();
/* 172 */       this.jPanel1.setLayout(null);
/* 173 */       this.jPanel1.setBackground(Color.white);
/* 174 */       this.jPanel1.setPreferredSize(new Dimension(100, 40));
/* 175 */       this.jPanel1.add(getJButton(), null);
/* 176 */       this.jPanel1.add(getJButton1(), null);
/* 177 */       this.jPanel1.add(getJButton2(), null);
/*     */     }
/* 179 */     return this.jPanel1;
/*     */   }
/*     */ 
/*     */   private JButton getJButton()
/*     */   {
/* 188 */     if (this.jButton == null) {
/* 189 */       this.jButton = new JButton();
/* 190 */       this.jButton.setBounds(new Rectangle(5, 5, 35, 29));
/* 191 */       this.jButton.setIcon(new ImageIcon(getClass().getResource("/almacen2/iconos/menos.png")));
/* 192 */       this.jButton.setToolTipText(Mensajes.getString("dialogoEliminar"));
/* 193 */       this.jButton.addActionListener(new ActionListener()
/*     */       {
/*     */         public void actionPerformed(ActionEvent e) {
/* 196 */           MarcoDetallePIO.this.eliminarElemento();
/*     */         }
/*     */       });
/*     */     }
/* 200 */     return this.jButton;
/*     */   }
/*     */ 
/*     */   private JButton getJButton1()
/*     */   {
/* 209 */     if (this.jButton1 == null) {
/* 210 */       this.jButton1 = new JButton();
/* 211 */       this.jButton1.setBounds(new Rectangle(100, 5, 35, 29));
/* 212 */       this.jButton1.setIcon(new ImageIcon(getClass().getResource("/almacen2/iconos/save.png")));
/* 213 */       this.jButton1.setToolTipText(Mensajes.getString("guardarTexto"));
/* 214 */       this.jButton1.addActionListener(new ActionListener()
/*     */       {
/*     */         public void actionPerformed(ActionEvent e) {
/* 217 */           MarcoDetallePIO.this.guardarLista();
/*     */         }
/*     */       });
/*     */     }
/* 221 */     return this.jButton1;
/*     */   }
/*     */ 
/*     */   private JButton getJButton2()
/*     */   {
/* 230 */     if (this.jButton2 == null) {
/* 231 */       this.jButton2 = new JButton();
/* 232 */       this.jButton2.setBounds(new Rectangle(50, 5, 35, 29));
/* 233 */       this.jButton2.setIcon(new ImageIcon(getClass().getResource("/almacen2/iconos/misc.png")));
/* 234 */       this.jButton2.setToolTipText(Mensajes.getString("modificar"));
/* 235 */       this.jButton2.addActionListener(new ActionListener()
/*     */       {
/*     */         public void actionPerformed(ActionEvent e) {
/* 238 */           MarcoDetallePIO.this.modificarElemento();
/*     */         }
/*     */       });
/*     */     }
/* 242 */     return this.jButton2;
/*     */   }
/*     */ 
/*     */   private void modificarElemento() {
/* 246 */     int index = this.tabla.getSelectedRow();
/* 247 */     int idProducto = ((Integer)this.id.get(index)).intValue();
/* 248 */     PIOListObject productoLista = (PIOListObject)this.lista.get(index);
/* 249 */     int io = Integer.parseInt(productoLista.getUnidades());
/* 250 */     PIOObject producto = new PIOObject(idProducto, productoLista.getFecha(), productoLista.getImporte().doubleValue(), io, productoLista.getAlmacen());
/* 251 */     dispose();
/* 252 */     ModificarPIO dlg = new ModificarPIO(Inicio.frame, true, producto, this.referencia);
/* 253 */     Inicio.frame.mostrarDialogo(dlg);
/*     */   }
/*     */ 
/*     */   private void eliminarElemento() {
/* 257 */     int borro = JOptionPane.showConfirmDialog(getContentPane(), Mensajes.getString("confBorrarMtos"), Mensajes.getString("confirma"), 0);
/*     */ 
/* 260 */     if (borro == 1) {
/* 261 */       return;
/*     */     }
/*     */ 
/* 264 */     ListSelectionModel lsm = this.tabla.getSelectionModel();
/* 265 */     if (lsm.isSelectionEmpty()) {
/* 266 */       return;
/*     */     }
/* 268 */     int inicio = lsm.getMinSelectionIndex();
/* 269 */     int fin = lsm.getMaxSelectionIndex();
/*     */ 
/* 271 */     ArrayList listaAEliminar = new ArrayList();
/* 272 */     for (int index = inicio; index <= fin; index++) {
/* 273 */       if (this.tabla.isRowSelected(index)) {
/* 274 */         this.stock -= Integer.parseInt(((PIOListObject)this.lista.get(index)).getUnidades());
/* 275 */         this.beneficio += Integer.parseInt(((PIOListObject)this.lista.get(index)).getUnidades()) * ((PIOListObject)this.lista.get(index)).getImporte().doubleValue();
/* 276 */         this.lista.remove(this.lista.size() - 1);
/* 277 */         this.lista.add(new PIOListObject(Mensajes.getString("total"), Integer.toString(this.stock), this.beneficio, -1));
/* 278 */         listaAEliminar.add(Integer.valueOf(index));
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 284 */     Collections.reverse(listaAEliminar);
/* 285 */     for (Integer index :(List<Integer>) listaAEliminar) {
/* 286 */       Inicio.frame.eliminarPIO(this.referencia, ((Integer)this.id.get(index.intValue())).intValue());
/*     */ 
/* 290 */       this.modelo.removeRow(index.intValue());
/*     */ 
/* 292 */       this.id.remove(index);
/*     */     }
/* 294 */     this.modelo.fireTableDataChanged();
/* 295 */     this.tabla.repaint();
/*     */   }
/*     */ 
/*     */   private void guardarLista() {
/* 299 */     JFileChooser fc = new JFileChooser();
/* 300 */     fc.setSelectedFile(new File(new StringBuilder().append("Detalle_").append(this.referencia).append(".txt").toString()));
/* 301 */     int retorno = fc.showSaveDialog(this);
/* 302 */     if (retorno == 0) {
/* 303 */       File archivo = fc.getSelectedFile();
/* 304 */       archivo = AddExtension.txt(archivo);
/* 305 */       GrabarFichero salida = new GrabarFichero(archivo);
/* 306 */       salida.insertar(new StringBuilder().append(this.referencia).append("\n").append(this.descripcion).append("\n\n").toString());
/* 307 */       StringBuilder sb = new StringBuilder();
/* 308 */       Formatter formater = new Formatter(sb);
/* 309 */       formater.format("%-10s %8s %10s %10s\n", new Object[] { Mensajes.getString("fecha"), Mensajes.getString("unidades"), Mensajes.getString("importe"), Mensajes.getString("almacen") });
/* 310 */       for (int fila = 0; fila < this.tabla.getRowCount(); fila++) {
/* 311 */         formater.format("%10s %8s %,10.2f %8s\n", new Object[] { this.tabla.getValueAt(fila, 0), this.tabla.getValueAt(fila, 1), this.tabla.getValueAt(fila, 2), this.tabla.getValueAt(fila, 3) });
/*     */       }
/*     */ 
/* 317 */       salida.insertar(sb.toString());
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.gui.MarcoDetallePIO
 * JD-Core Version:    0.6.2
 */