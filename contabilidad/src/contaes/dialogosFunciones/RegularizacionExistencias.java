/*     */ package contaes.dialogosFunciones;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.Puente;
/*     */ import contaes.auxiliar.DocNumeros;
/*     */ import contaes.auxiliarTablas.DefaultTableModelRegularizacion;
/*     */ import contaes.auxiliarTablas.GeneralColorRenderer;
/*     */ import contaes.auxiliarTablas.ImporteColorRenderer;
/*     */ import contaes.manejoDatos.ManejoApuntes;
/*     */ import contaes.manejoDatos.ManejoAsientos;
/*     */ import contaes.manejoDatos.ManejoSubcuentas;
/*     */ import contaes.manejoDatos.TipoSubcuenta;
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.Container;
/*     */ import java.awt.Frame;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.ArrayList;
import java.util.List;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.DefaultCellEditor;
/*     */ import javax.swing.DefaultComboBoxModel;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.GroupLayout.Alignment;
/*     */ import javax.swing.GroupLayout.ParallelGroup;
/*     */ import javax.swing.GroupLayout.SequentialGroup;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.JTextField;
import javax.swing.LayoutStyle;
/*     */ import javax.swing.LayoutStyle.ComponentPlacement;
/*     */ import javax.swing.table.TableColumn;
/*     */ import javax.swing.table.TableColumnModel;
/*     */ 
/*     */ public class RegularizacionExistencias extends JDialog
/*     */ {
/*     */   DefaultTableModelRegularizacion modeloTabla;
/*     */   DefaultComboBoxModel modeloCombo;
/*     */   private JButton jButton1;
/*     */   private JButton jButton2;
/*     */   private JPanel jPanel1;
/*     */   private JScrollPane jScrollPane1;
/*     */   private JTable tabla;
/*     */ 
/*     */   public RegularizacionExistencias(Frame parent, boolean modal)
/*     */   {
/*  42 */     super(parent, modal);
/*  43 */     initComponents();
/*  44 */     initTabla();
/*  45 */     cargarDatos();
/*     */   }
/*     */ 
/*     */   private void cargarDatos() {
/*  49 */     ArrayList listaSubcuentas = new ArrayList();
/*  50 */     ManejoSubcuentas mS = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  51 */     listaSubcuentas = mS.listaSubcuentas(30000000, 38999999);
/*  52 */     int row = 0;
/*  53 */     Object[] objeto = null;
/*  54 */     for (TipoSubcuenta subcuenta :(List<TipoSubcuenta>)  listaSubcuentas) {
/*  55 */       objeto = new Object[] { subcuenta, new Double(subcuenta.getSaldo()) };
/*     */ 
/*  58 */       this.modeloTabla.addRow(objeto);
/*  59 */       TipoSubcuenta contrapartida = getContrapartida(subcuenta);
/*  60 */       if (contrapartida != null) {
/*  61 */         this.modeloTabla.setValueAt(contrapartida, row, 2);
/*     */       }
/*  63 */       row++;
/*     */     }
/*  65 */     this.modeloTabla.fireTableDataChanged();
/*  66 */     this.tabla.repaint();
/*     */   }
/*     */ 
/*     */   private TipoSubcuenta getContrapartida(TipoSubcuenta subcuenta) {
/*  70 */     Integer t = Integer.valueOf(subcuenta.getCodigo());
/*  71 */     String clave1 = t.toString().substring(0, 2);
/*  72 */     String clave2 = "610";
/*  73 */     if (clave1.equals("31")) {
/*  74 */       clave2 = "611";
/*     */     }
/*  76 */     else if (clave1.equals("32")) {
/*  77 */       clave2 = "612";
/*     */     }
/*  79 */     else if (clave1.equals("33")) {
/*  80 */       clave2 = "710";
/*     */     }
/*  82 */     else if (clave1.equals("34")) {
/*  83 */       clave2 = "711";
/*     */     }
/*  85 */     else if (clave1.equals("35")) {
/*  86 */       clave2 = "712";
/*     */     }
/*  88 */     else if (clave1.equals("36")) {
/*  89 */       clave2 = "713";
/*     */     }
/*  91 */     TipoSubcuenta retorno = null;
/*  92 */     for (int i = 0; i < this.modeloCombo.getSize(); i++) {
/*  93 */       retorno = (TipoSubcuenta)this.modeloCombo.getElementAt(i);
/*  94 */       Integer t2 = Integer.valueOf(retorno.getCodigo());
/*  95 */       String comprob = t2.toString().substring(0, 3);
/*  96 */       if (comprob.equals(clave2)) {
/*     */         break;
/*     */       }
/*     */     }
/* 100 */     return retorno;
/*     */   }
/*     */ 
/*     */   private void initTabla() {
/* 104 */     ArrayList listaSubcuentas = new ArrayList();
/* 105 */     ManejoSubcuentas mS = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 106 */     listaSubcuentas = mS.listaSubcuentas(61000000, 61299999);
/* 107 */     listaSubcuentas.addAll(mS.listaSubcuentas(71000000, 71399999));
/* 108 */     mS.cerrarRs();
/* 109 */     this.modeloCombo = new DefaultComboBoxModel();
/* 110 */     for (TipoSubcuenta tipoSubcuenta : (List<TipoSubcuenta>)listaSubcuentas) {
/* 111 */       this.modeloCombo.addElement(tipoSubcuenta);
/*     */     }
/* 113 */     JComboBox combo = new JComboBox(this.modeloCombo);
/* 114 */     JTextField textField = new JTextField();
/* 115 */     textField.setDocument(new DocNumeros());
/*     */ 
/* 117 */     this.modeloTabla = new DefaultTableModelRegularizacion();
/* 118 */     this.tabla.setModel(this.modeloTabla);
/*     */ 
/* 120 */     TableColumn columna = null;
/* 121 */     int anchoTabla = getWidth() - 40;
/* 122 */     columna = this.tabla.getColumnModel().getColumn(0);
/* 123 */     columna.setPreferredWidth((int)(anchoTabla * 0.35D));
/* 124 */     columna.setCellRenderer(new GeneralColorRenderer());
/* 125 */     columna = this.tabla.getColumnModel().getColumn(1);
/* 126 */     columna.setCellRenderer(new ImporteColorRenderer());
/* 127 */     columna.setPreferredWidth((int)(anchoTabla * 0.15D));
/* 128 */     columna = this.tabla.getColumnModel().getColumn(2);
/* 129 */     columna.setPreferredWidth((int)(anchoTabla * 0.35D));
/* 130 */     columna.setCellRenderer(new GeneralColorRenderer());
/* 131 */     columna.setCellEditor(new DefaultCellEditor(combo));
/* 132 */     columna = this.tabla.getColumnModel().getColumn(3);
/* 133 */     columna.setPreferredWidth((int)(anchoTabla * 0.15D));
/* 134 */     columna.setCellRenderer(new GeneralColorRenderer());
/* 135 */     columna.setCellEditor(new DefaultCellEditor(textField));
/*     */ 
/* 137 */     this.jScrollPane1.setViewportView(this.tabla);
/*     */   }
/*     */ 
/*     */   private void crearAsiento() {
/* 141 */     int rowCount = this.tabla.getRowCount();
/* 142 */     if (rowCount > 0) {
/* 143 */       ManejoAsientos mAs = new ManejoAsientos(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 144 */       ManejoApuntes mAp = new ManejoApuntes(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 145 */       int idAsiento = mAs.crear(mAs.nuevoNumero(), Inicio.p.getEjercicio() + "-12-31", "", "");
/* 146 */       if (idAsiento != -1)
/* 147 */         for (int i = 0; i < rowCount; i++)
/*     */           try {
/* 149 */             double exFinal = Double.parseDouble(this.modeloTabla.getValueAt(i, 3).toString());
/* 150 */             if (exFinal < 0.0D) {
/* 151 */               exFinal = 0.0D;
/*     */             }
/* 153 */             TipoSubcuenta cuentaEx = (TipoSubcuenta)this.modeloTabla.getValueAt(i, 0);
/* 154 */             TipoSubcuenta cuentaGa = (TipoSubcuenta)this.modeloTabla.getValueAt(i, 2);
/*     */ 
/* 156 */             mAp.crear(idAsiento, cuentaEx.getCodigo(), Mensajes.getString("regulEt6"), Mensajes.getString("haberA"), cuentaEx.getSaldo());
/*     */ 
/* 158 */             mAp.crear(idAsiento, cuentaGa.getCodigo(), Mensajes.getString("regulEt6"), Mensajes.getString("debeA"), cuentaEx.getSaldo());
/*     */ 
/* 161 */             mAp.crear(idAsiento, cuentaEx.getCodigo(), Mensajes.getString("regulEt6"), Mensajes.getString("debeA"), exFinal);
/*     */ 
/* 163 */             mAp.crear(idAsiento, cuentaGa.getCodigo(), Mensajes.getString("regulEt6"), Mensajes.getString("haberA"), exFinal);
/*     */           }
/*     */           catch (NumberFormatException e)
/*     */           {
/* 167 */             e.printStackTrace();
/*     */           }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/* 183 */     this.jPanel1 = new JPanel();
/* 184 */     this.jScrollPane1 = new JScrollPane();
/* 185 */     this.tabla = new JTable();
/* 186 */     this.jButton1 = new JButton();
/* 187 */     this.jButton2 = new JButton();
/*     */ 
/* 189 */     setDefaultCloseOperation(2);
/* 190 */     ResourceBundle bundle = ResourceBundle.getBundle("internationalization/Mensajes");
/* 191 */     setTitle(bundle.getString("regulaEx"));
/*     */ 
/* 193 */     this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/*     */ 
/* 195 */     this.jScrollPane1.setViewportView(this.tabla);
/*     */ 
/* 197 */     this.jButton1.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/MasientoRE.png")));
/* 198 */     this.jButton1.setText(bundle.getString("ejecutar"));
/* 199 */     this.jButton1.setHorizontalTextPosition(2);
/* 200 */     this.jButton1.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 202 */         RegularizacionExistencias.this.jButton1ActionPerformed(evt);
/*     */       }
/*     */     });
/* 206 */     this.jButton2.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/cancel.png")));
/* 207 */     this.jButton2.setText(bundle.getString("cancelar"));
/* 208 */     this.jButton2.setHorizontalTextPosition(2);
/* 209 */     this.jButton2.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 211 */         RegularizacionExistencias.this.jButton2ActionPerformed(evt);
/*     */       }
/*     */     });
/* 215 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 216 */     this.jPanel1.setLayout(jPanel1Layout);
/* 217 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jScrollPane1, -1, 598, 32767).addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup().addComponent(this.jButton2).addGap(18, 18, 18).addComponent(this.jButton1))).addContainerGap()));
/*     */ 
/* 229 */     jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(this.jScrollPane1, -2, 245, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jButton1).addComponent(this.jButton2)).addContainerGap(-1, 32767)));
/*     */ 
/* 241 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 242 */     getContentPane().setLayout(layout);
/* 243 */     layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -1, -1, 32767).addContainerGap()));
/*     */ 
/* 250 */     layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -1, -1, 32767).addContainerGap()));
/*     */ 
/* 258 */     pack();
/*     */   }
/*     */ 
/*     */   private void jButton2ActionPerformed(ActionEvent evt) {
/* 262 */     dispose();
/*     */   }
/*     */ 
/*     */   private void jButton1ActionPerformed(ActionEvent evt) {
/* 266 */     crearAsiento();
/* 267 */     dispose();
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.dialogosFunciones.RegularizacionExistencias
 * JD-Core Version:    0.6.2
 */