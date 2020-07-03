/*     */ package pos.view;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.auxiliarTablas.BooleanColorRenderer;
/*     */ import contaes.auxiliarTablas.DerechaColorRenderer;
/*     */ import contaes.auxiliarTablas.GeneralColorRenderer;
/*     */ import contaes.auxiliarTablas.ImporteColorRenderer;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Frame;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.DefaultComboBoxModel;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.GroupLayout.Alignment;
/*     */ import javax.swing.GroupLayout.ParallelGroup;
/*     */ import javax.swing.GroupLayout.SequentialGroup;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.JTextField;
import javax.swing.LayoutStyle;
/*     */ import javax.swing.LayoutStyle.ComponentPlacement;
/*     */ import javax.swing.table.TableColumn;
/*     */ import javax.swing.table.TableColumnModel;
/*     */ import pos.control.ResumenControl;
/*     */ import pos.model.BasicException;
/*     */ import pos.model.ObjetoMultifuncional;
/*     */ import pos.model.ResumenVentaTicket;
/*     */ import pos.model.ResumenVentaTicketTableModel;
/*     */ import pos.view.calendar.JCalendarDialog;
/*     */ import pos.view.editors.JEditorCurrency;
/*     */ import pos.view.editors.JEditorIntegerPositive;
/*     */ import pos.view.editors.JEditorKeys;
/*     */ 
/*     */ public class BusquedaTicketJDialog extends JDialog
/*     */ {
/*     */   private Integer idTicket;
/*     */   private Date fechaInicial;
/*     */   private Date fechaFinal;
/*     */   private Integer ticket;
/*     */   private String referencia;
/*     */   private ObjetoMultifuncional cliente;
/*     */   private Double importe;
/*     */   private ResumenVentaTicketTableModel modeloTabla;
/*     */   private JButton botonAceptar;
/*     */   private JButton botonBuscar;
/*     */   private JButton botonCancelar;
/*     */   private JButton botonCliente;
/*     */   private JButton botonFechaFin;
/*     */   private JButton botonFechaIni;
/*     */   private JButton botonReferencia;
/*     */   private JButton botonTablaAbajo;
/*     */   private JButton botonTablaArriba;
/*     */   private JButton botonTablaFin;
/*     */   private JButton botonTablaInicio;
/*     */   private JTextField campoCliente;
/*     */   private JTextField campoFechaFin;
/*     */   private JTextField campoFechaIni;
/*     */   private JEditorCurrency campoImporte;
/*     */   private JTextField campoReferencia;
/*     */   private JEditorIntegerPositive campoTicket;
/*     */   private JComboBox comboImporte;
/*     */   private JEditorKeys editorKeys;
/*     */   private JButton jButton1;
/*     */   private JLabel jLabel1;
/*     */   private JLabel jLabel2;
/*     */   private JLabel jLabel3;
/*     */   private JLabel jLabel4;
/*     */   private JLabel jLabel5;
/*     */   private JLabel jLabel6;
/*     */   private JPanel jPanel1;
/*     */   private JPanel jPanel2;
/*     */   private JScrollPane jScrollPane1;
/*     */   private JTable tabla;
/*     */ 
/*     */   public BusquedaTicketJDialog(Frame parent, boolean modal)
/*     */   {
/*  51 */     super(parent, modal);
/*  52 */     initComponents();
/*     */ 
/*  54 */     this.campoTicket.addEditorKeys(this.editorKeys);
/*  55 */     this.campoImporte.addEditorKeys(this.editorKeys);
/*  56 */     this.campoTicket.reset();
/*  57 */     this.campoTicket.activate();
/*  58 */     this.campoImporte.reset();
/*     */ 
/*  60 */     ArrayList lineas = new ArrayList();
/*  61 */     crearTabla(lineas);
/*     */   }
/*     */ 
/*     */   public static Integer obtenerObjeto(Frame parent, boolean modal)
/*     */   {
/*  66 */     BusquedaTicketJDialog dlg = new BusquedaTicketJDialog(parent, modal);
/*  67 */     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/*  68 */     Dimension frameSize = dlg.getSize();
/*  69 */     if (frameSize.height > screenSize.height) {
/*  70 */       frameSize.height = screenSize.height;
/*     */     }
/*  72 */     if (frameSize.width > screenSize.width) {
/*  73 */       frameSize.width = screenSize.width;
/*     */     }
/*  75 */     dlg.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
/*     */ 
/*  78 */     dlg.setVisible(true);
/*  79 */     return dlg.getIdTicket();
/*     */   }
/*     */ 
/*     */   private void buscar() {
/*  83 */     if (!this.campoTicket.getText().equals("")) {
/*     */       try {
/*  85 */         this.ticket = new Integer(this.campoTicket.getValueInteger());
/*     */       }
/*     */       catch (BasicException exc) {
/*  88 */         exc.printStackTrace();
/*     */       }
/*     */     }
/*  91 */     if (!this.campoImporte.getText().equals("")) {
/*  92 */       this.importe = new Double(this.campoImporte.getDoubleValue().doubleValue());
/*     */     }
/*  94 */     ResumenControl rC = new ResumenControl(Inicio.getcAlmacen());
/*  95 */     ArrayList lineas = rC.busquedaVentas(this.fechaInicial, this.fechaFinal, this.ticket, this.referencia, this.cliente, this.importe, this.comboImporte.getSelectedIndex());
/*  96 */     rC.cerrarRs();
/*  97 */     crearTabla(lineas);
/*     */   }
/*     */ 
/*     */   private void crearTabla(ArrayList<ResumenVentaTicket> lineas) {
/* 101 */     this.modeloTabla = new ResumenVentaTicketTableModel(lineas);
/*     */ 
/* 103 */     this.tabla.setModel(this.modeloTabla);
/*     */ 
/* 105 */     int anchoTabla = 520;
/* 106 */     TableColumn columna = this.tabla.getColumnModel().getColumn(0);
/* 107 */     columna.setPreferredWidth((int)(anchoTabla * 0.08D));
/* 108 */     columna = this.tabla.getColumnModel().getColumn(1);
/* 109 */     columna.setPreferredWidth((int)(anchoTabla * 0.32D));
/* 110 */     columna.setCellRenderer(new GeneralColorRenderer());
/* 111 */     columna = this.tabla.getColumnModel().getColumn(2);
/* 112 */     columna.setPreferredWidth((int)(anchoTabla * 0.1D));
/* 113 */     columna.setCellRenderer(new DerechaColorRenderer());
/* 114 */     columna = this.tabla.getColumnModel().getColumn(3);
/* 115 */     columna.setPreferredWidth((int)(anchoTabla * 0.15D));
/* 116 */     columna.setCellRenderer(new ImporteColorRenderer());
/* 117 */     columna = this.tabla.getColumnModel().getColumn(4);
/* 118 */     columna.setPreferredWidth((int)(anchoTabla * 0.15D));
/* 119 */     columna.setCellRenderer(new ImporteColorRenderer());
/* 120 */     columna = this.tabla.getColumnModel().getColumn(5);
/* 121 */     columna.setPreferredWidth((int)(anchoTabla * 0.15D));
/* 122 */     columna.setCellRenderer(new GeneralColorRenderer());
/* 123 */     columna = this.tabla.getColumnModel().getColumn(6);
/* 124 */     columna.setPreferredWidth((int)(anchoTabla * 0.05D));
/* 125 */     columna.setCellRenderer(new DerechaColorRenderer());
/* 126 */     columna = this.tabla.getColumnModel().getColumn(7);
/* 127 */     columna.setPreferredWidth((int)(anchoTabla * 0.05D));
/* 128 */     columna.setCellRenderer(new BooleanColorRenderer());
/* 129 */     this.tabla.setDefaultRenderer(Object.class, new GeneralColorRenderer());
/*     */ 
/* 131 */     this.jScrollPane1.setViewportView(this.tabla);
/*     */   }
/*     */ 
/*     */   public Integer getIdTicket() {
/* 135 */     return this.idTicket;
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/* 147 */     this.jPanel1 = new JPanel();
/* 148 */     this.jPanel2 = new JPanel();
/* 149 */     this.jLabel1 = new JLabel();
/* 150 */     this.campoTicket = new JEditorIntegerPositive();
/* 151 */     this.jLabel2 = new JLabel();
/* 152 */     this.campoFechaIni = new JTextField();
/* 153 */     this.campoFechaFin = new JTextField();
/* 154 */     this.jLabel3 = new JLabel();
/* 155 */     this.campoReferencia = new JTextField();
/* 156 */     this.jLabel4 = new JLabel();
/* 157 */     this.campoCliente = new JTextField();
/* 158 */     this.jLabel5 = new JLabel();
/* 159 */     this.jLabel6 = new JLabel();
/* 160 */     this.comboImporte = new JComboBox();
/* 161 */     this.campoImporte = new JEditorCurrency();
/* 162 */     this.botonFechaIni = new JButton();
/* 163 */     this.botonFechaFin = new JButton();
/* 164 */     this.botonCliente = new JButton();
/* 165 */     this.botonReferencia = new JButton();
/* 166 */     this.editorKeys = new JEditorKeys();
/* 167 */     this.jScrollPane1 = new JScrollPane();
/* 168 */     this.tabla = new JTable();
/* 169 */     this.botonBuscar = new JButton();
/* 170 */     this.botonAceptar = new JButton();
/* 171 */     this.botonCancelar = new JButton();
/* 172 */     this.botonTablaInicio = new JButton();
/* 173 */     this.botonTablaArriba = new JButton();
/* 174 */     this.botonTablaAbajo = new JButton();
/* 175 */     this.botonTablaFin = new JButton();
/* 176 */     this.jButton1 = new JButton();
/*     */ 
/* 178 */     setDefaultCloseOperation(2);
/*     */ 
/* 180 */     this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/*     */ 
/* 182 */     ResourceBundle bundle = ResourceBundle.getBundle("internationalization/Mensajes");
/* 183 */     this.jLabel1.setText(bundle.getString("ticket"));
/*     */ 
/* 185 */     this.jLabel2.setText(bundle.getString("fechaIni"));
/*     */ 
/* 187 */     this.campoFechaIni.setEditable(false);
/*     */ 
/* 189 */     this.campoFechaFin.setEditable(false);
/*     */ 
/* 191 */     this.jLabel3.setText(bundle.getString("fechaFin"));
/*     */ 
/* 193 */     this.jLabel4.setText(bundle.getString("referencia"));
/*     */ 
/* 195 */     this.jLabel5.setText(bundle.getString("cliente"));
/*     */ 
/* 197 */     this.jLabel6.setText(bundle.getString("total"));
/*     */ 
/* 199 */     this.comboImporte.setModel(new DefaultComboBoxModel(new String[] { "Menor que", "Igual", "Mayor que" }));
/*     */ 
/* 201 */     this.botonFechaIni.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/date.png")));
/* 202 */     this.botonFechaIni.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 204 */         BusquedaTicketJDialog.this.botonFechaIniActionPerformed(evt);
/*     */       }
/*     */     });
/* 208 */     this.botonFechaFin.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/date.png")));
/* 209 */     this.botonFechaFin.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 211 */         BusquedaTicketJDialog.this.botonFechaFinActionPerformed(evt);
/*     */       }
/*     */     });
/* 215 */     this.botonCliente.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/edit_group.png")));
/* 216 */     this.botonCliente.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 218 */         BusquedaTicketJDialog.this.botonClienteActionPerformed(evt);
/*     */       }
/*     */     });
/* 222 */     this.botonReferencia.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/producto18.png")));
/* 223 */     this.botonReferencia.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 225 */         BusquedaTicketJDialog.this.botonReferenciaActionPerformed(evt);
/*     */       }
/*     */     });
/* 229 */     GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
/* 230 */     this.jPanel2.setLayout(jPanel2Layout);
/* 231 */     jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel1).addComponent(this.jLabel2).addComponent(this.jLabel3).addComponent(this.jLabel4).addComponent(this.jLabel5)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 34, 32767).addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false).addComponent(this.campoCliente, GroupLayout.Alignment.LEADING).addComponent(this.campoReferencia, GroupLayout.Alignment.LEADING).addComponent(this.campoFechaFin, GroupLayout.Alignment.LEADING).addComponent(this.campoFechaIni, GroupLayout.Alignment.LEADING, -2, 197, -2).addComponent(this.campoTicket, GroupLayout.Alignment.LEADING, -2, 148, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.botonFechaIni, -2, 40, -2).addComponent(this.botonFechaFin, -2, 40, -2).addComponent(this.botonCliente, -2, 40, -2))).addGroup(jPanel2Layout.createSequentialGroup().addComponent(this.jLabel6).addGap(18, 18, 18).addComponent(this.comboImporte, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.campoImporte, -1, 179, 32767)).addComponent(this.botonReferencia, GroupLayout.Alignment.TRAILING, -2, 40, -2)).addContainerGap()));
/*     */ 
/* 264 */     jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel1)).addComponent(this.campoTicket, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.campoFechaIni, -2, -1, -2).addComponent(this.jLabel2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.campoFechaFin, -2, -1, -2).addComponent(this.jLabel3)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.campoReferencia, -2, -1, -2).addComponent(this.jLabel4)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.campoCliente, -2, -1, -2).addComponent(this.jLabel5))).addGroup(jPanel2Layout.createSequentialGroup().addGap(36, 36, 36).addComponent(this.botonFechaIni).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.botonFechaFin).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.botonReferencia).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.botonCliente, -2, 30, -2))).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel6).addComponent(this.comboImporte, -2, -1, -2)).addComponent(this.campoImporte, -2, -1, -2)).addContainerGap(-1, 32767)));
/*     */ 
/* 308 */     this.jScrollPane1.setViewportView(this.tabla);
/*     */ 
/* 310 */     this.botonBuscar.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/search22.png")));
/* 311 */     this.botonBuscar.setText(bundle.getString("buscar"));
/* 312 */     this.botonBuscar.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 314 */         BusquedaTicketJDialog.this.botonBuscarActionPerformed(evt);
/*     */       }
/*     */     });
/* 318 */     this.botonAceptar.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/button_ok.png")));
/* 319 */     this.botonAceptar.setText(bundle.getString("aceptar"));
/* 320 */     this.botonAceptar.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 322 */         BusquedaTicketJDialog.this.botonAceptarActionPerformed(evt);
/*     */       }
/*     */     });
/* 326 */     this.botonCancelar.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/button_cancel.png")));
/* 327 */     this.botonCancelar.setText(bundle.getString("cancelar"));
/* 328 */     this.botonCancelar.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 330 */         BusquedaTicketJDialog.this.botonCancelarActionPerformed(evt);
/*     */       }
/*     */     });
/* 334 */     this.botonTablaInicio.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/3uparrow.png")));
/* 335 */     this.botonTablaInicio.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 337 */         BusquedaTicketJDialog.this.botonTablaInicioActionPerformed(evt);
/*     */       }
/*     */     });
/* 341 */     this.botonTablaArriba.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/1uparrow22.png")));
/* 342 */     this.botonTablaArriba.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 344 */         BusquedaTicketJDialog.this.botonTablaArribaActionPerformed(evt);
/*     */       }
/*     */     });
/* 348 */     this.botonTablaAbajo.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/1downarrow22.png")));
/* 349 */     this.botonTablaAbajo.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 351 */         BusquedaTicketJDialog.this.botonTablaAbajoActionPerformed(evt);
/*     */       }
/*     */     });
/* 355 */     this.botonTablaFin.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/3downarrow.png")));
/* 356 */     this.botonTablaFin.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 358 */         BusquedaTicketJDialog.this.botonTablaFinActionPerformed(evt);
/*     */       }
/*     */     });
/* 362 */     this.jButton1.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/cash22.png")));
/* 363 */     this.jButton1.setText(bundle.getString("plazos"));
/* 364 */     this.jButton1.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 366 */         BusquedaTicketJDialog.this.jButton1ActionPerformed(evt);
/*     */       }
/*     */     });
/* 370 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 371 */     this.jPanel1.setLayout(jPanel1Layout);
/* 372 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.jScrollPane1, -2, 522, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.botonTablaInicio).addComponent(this.botonTablaArriba).addComponent(this.botonTablaAbajo).addComponent(this.botonTablaFin))).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.jPanel2, -2, -1, -2).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.botonBuscar).addGap(18, 18, 18).addComponent(this.botonAceptar).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.botonCancelar).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.jButton1).addGap(1, 1, 1))).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.editorKeys, -2, 185, -2))).addContainerGap(9, 32767)));
/*     */ 
/* 401 */     jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.jPanel2, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.botonBuscar).addComponent(this.botonAceptar).addComponent(this.botonCancelar).addComponent(this.jButton1))).addComponent(this.editorKeys, -2, 245, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.botonTablaInicio).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.botonTablaArriba).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.botonTablaAbajo).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.botonTablaFin)).addComponent(this.jScrollPane1, 0, 0, 32767)).addContainerGap(-1, 32767)));
/*     */ 
/* 429 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 430 */     getContentPane().setLayout(layout);
/* 431 */     layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -1, -1, 32767).addContainerGap()));
/*     */ 
/* 438 */     layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -1, -1, 32767).addContainerGap()));
/*     */ 
/* 446 */     pack();
/*     */   }
/*     */ 
/*     */   private void botonFechaIniActionPerformed(ActionEvent evt) {
/* 450 */     Date seleccionfecha = JCalendarDialog.showCalendarTimeHours(getParent(), new Date());
/* 451 */     if (seleccionfecha != null) {
/* 452 */       this.fechaInicial = new Date();
/* 453 */       this.fechaInicial.setTime(seleccionfecha.getTime());
/* 454 */       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
/* 455 */       this.campoFechaIni.setText(sdf.format(this.fechaInicial));
/*     */     }
/*     */   }
/*     */ 
/*     */   private void botonFechaFinActionPerformed(ActionEvent evt) {
/* 460 */     Date seleccionfecha = JCalendarDialog.showCalendarTimeHours(getParent(), new Date());
/* 461 */     if (seleccionfecha != null) {
/* 462 */       this.fechaFinal = new Date();
/* 463 */       this.fechaFinal.setTime(seleccionfecha.getTime());
/* 464 */       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
/* 465 */       this.campoFechaFin.setText(sdf.format(this.fechaFinal));
/*     */     }
/*     */   }
/*     */ 
/*     */   private void botonReferenciaActionPerformed(ActionEvent evt) {
/* 470 */     this.referencia = BusquedaProductosJDialog.obtenerObjeto(Inicio.frame, true);
/* 471 */     if ((this.referencia != null) && (!this.referencia.equals("")))
/* 472 */       this.campoReferencia.setText(this.referencia);
/*     */   }
/*     */ 
/*     */   private void botonClienteActionPerformed(ActionEvent evt)
/*     */   {
/* 477 */     this.cliente = BusquedaMultipleJDialog.obtenerObjeto(Inicio.frame, true, 0);
/* 478 */     if (this.cliente != null)
/* 479 */       this.campoCliente.setText(this.cliente.getNombre());
/*     */   }
/*     */ 
/*     */   private void botonBuscarActionPerformed(ActionEvent evt)
/*     */   {
/* 484 */     buscar();
/*     */   }
/*     */ 
/*     */   private void botonAceptarActionPerformed(ActionEvent evt) {
/* 488 */     if (this.tabla.getSelectedRow() != -1) {
/* 489 */       ResumenVentaTicket objeto = this.modeloTabla.getObjectAt(this.tabla.getSelectedRow());
/* 490 */       if (objeto != null) {
/* 491 */         this.idTicket = Integer.valueOf(objeto.getIdTicket());
/*     */       }
/*     */     }
/* 494 */     dispose();
/*     */   }
/*     */ 
/*     */   private void botonCancelarActionPerformed(ActionEvent evt) {
/* 498 */     dispose();
/*     */   }
/*     */ 
/*     */   private void botonTablaInicioActionPerformed(ActionEvent evt) {
/* 502 */     this.tabla.changeSelection(0, 0, false, false);
/*     */   }
/*     */ 
/*     */   private void botonTablaArribaActionPerformed(ActionEvent evt) {
/* 506 */     if (this.tabla.getSelectedRow() != -1) {
/* 507 */       int rowSelected = this.tabla.getSelectedRow();
/* 508 */       if (rowSelected > 0) {
/* 509 */         rowSelected--;
/* 510 */         this.tabla.changeSelection(rowSelected, 0, false, false);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void botonTablaAbajoActionPerformed(ActionEvent evt) {
/* 516 */     if (this.tabla.getSelectedRow() != -1) {
/* 517 */       int rowSelected = this.tabla.getSelectedRow();
/* 518 */       if (rowSelected < this.tabla.getRowCount() - 1) {
/* 519 */         rowSelected++;
/* 520 */         this.tabla.changeSelection(rowSelected, 0, false, false);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void botonTablaFinActionPerformed(ActionEvent evt) {
/* 526 */     this.tabla.changeSelection(this.tabla.getRowCount() - 1, 0, false, false);
/*     */   }
/*     */ 
/*     */   private void jButton1ActionPerformed(ActionEvent evt) {
/* 530 */     if ((this.modeloTabla.getRowCount() > 0) && (this.tabla.getSelectedRow() != -1)) {
/* 531 */       ResumenVentaTicket objeto = this.modeloTabla.getObjectAt(this.tabla.getSelectedRow());
/* 532 */       if (objeto != null) {
/* 533 */         ResumenControl rC = new ResumenControl(Inicio.getcAlmacen());
/* 534 */         ArrayList lineas = rC.getSeriePlazos(Integer.valueOf(objeto.getIdTicket()));
/* 535 */         rC.cerrarRs();
/* 536 */         crearTabla(lineas);
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.view.BusquedaTicketJDialog
 * JD-Core Version:    0.6.2
 */