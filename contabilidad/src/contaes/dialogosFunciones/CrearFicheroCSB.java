/*     */ package contaes.dialogosFunciones;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.auxiliar.DocTresDigitos;
/*     */ import contaes.calendario.ICalendarTextField;
/*     */ import contaes.listados.Remesas19;
/*     */ import contaes.listados.Remesas58;
/*     */ import contaes.manejoDatos.ManejoSubcuentas;
/*     */ import contaes.manejoDatos.TipoBanco;
/*     */ import contaes.manejoDatos.TipoVencimiento;
/*     */ import contaes.manejoDatos.auxiliar.ConfiguracionBean;
/*     */ import java.awt.Frame;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
import java.util.List;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.DefaultComboBoxModel;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.LayoutStyle;
/*     */ 
/*     */ public class CrearFicheroCSB extends JDialog
/*     */ {
/*     */   ArrayList<TipoVencimiento> lista;
/*     */   int modelo;
/*  33 */   DefaultComboBoxModel modelComboBanco = new DefaultComboBoxModel();
/*     */   private ICalendarTextField campoFecha;
/*     */   private JTextField campoNif;
/*     */   private JTextField campoNombre;
/*     */   private JTextField campoSufijo;
/*     */   private JComboBox comboBancos;
/*     */   private JButton jButton1;
/*     */   private JButton jButton2;
/*     */   private JLabel jLabel1;
/*     */   private JLabel jLabel2;
/*     */   private JLabel jLabel3;
/*     */   private JLabel jLabel4;
/*     */   private JPanel jPanel1;
/*     */   private JLabel labelFecha;
/*     */ 
/*     */   public CrearFicheroCSB(Frame parent, boolean modal, ArrayList<TipoVencimiento> lista, int modelo)
/*     */   {
/*  37 */     super(parent, modal);
/*  38 */     this.lista = lista;
/*  39 */     this.modelo = modelo;
/*  40 */     initComponents();
/*  41 */     this.campoSufijo.setDocument(new DocTresDigitos());
/*  42 */     this.campoSufijo.setText(getSufijo());
/*  43 */     cargarComboBancos();
/*  44 */     if (modelo == 58) {
/*  45 */       this.campoFecha.setEnabled(false);
/*  46 */       this.campoFecha.setVisible(false);
/*  47 */       this.labelFecha.setText("");
/*     */     }
/*     */   }
/*     */ 
/*     */   private void cargarComboBancos() {
/*  52 */     this.modelComboBanco.removeAllElements();
/*  53 */     ManejoSubcuentas mS = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  54 */     ArrayList listaBancos = mS.getBancosConCcc();
/*  55 */     for (TipoBanco tipoBanco : (List<TipoBanco>)listaBancos) {
/*  56 */       this.modelComboBanco.addElement(tipoBanco);
/*     */     }
/*  58 */     this.comboBancos.setModel(this.modelComboBanco);
/*     */   }
/*     */ 
/*     */   private String getSufijo() {
/*  62 */     String sufijo = "";
/*  63 */     ArrayList array = new ArrayList();
/*  64 */     ConfiguracionBean config = new ConfiguracionBean();
/*  65 */     array = config.getConfig("<sufijo>");
/*     */     try {
/*  67 */       if (!array.isEmpty())
/*  68 */         sufijo = (String)array.get(0);
/*     */     }
/*     */     catch (ArrayIndexOutOfBoundsException e) {
/*  71 */       e.printStackTrace();
/*     */     }
/*  73 */     return sufijo;
/*     */   }
/*     */ 
/*     */   private void setSufijo(String sufijo) {
/*  77 */     ArrayList array = new ArrayList();
/*  78 */     array.add(sufijo);
/*  79 */     ConfiguracionBean config = new ConfiguracionBean();
/*  80 */     config.saveConfig("<sufijo>", array);
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/*  91 */     this.jPanel1 = new JPanel();
/*  92 */     this.jLabel1 = new JLabel();
/*  93 */     this.campoNombre = new JTextField();
/*  94 */     this.jLabel2 = new JLabel();
/*  95 */     this.campoNif = new JTextField();
/*  96 */     this.jLabel3 = new JLabel();
/*  97 */     this.campoSufijo = new JTextField();
/*  98 */     this.jLabel4 = new JLabel();
/*  99 */     this.comboBancos = new JComboBox();
/* 100 */     this.labelFecha = new JLabel();
/* 101 */     this.campoFecha = new ICalendarTextField();
/* 102 */     this.jButton1 = new JButton();
/* 103 */     this.jButton2 = new JButton();
/*     */ 
/* 105 */     setDefaultCloseOperation(2);
/*     */ 
/* 107 */     this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/*     */ 
/* 109 */     this.jLabel1.setText("Nombre presentador:");
/*     */ 
/* 111 */     this.jLabel2.setText("NIF presentador:");
/*     */ 
/* 113 */     this.jLabel3.setText("Sufijo:");
/*     */ 
/* 115 */     this.jLabel4.setText("Cuenta:");
/*     */ 
/* 117 */     this.labelFecha.setText("Fecha cargo:");
/*     */ 
/* 119 */     this.jButton1.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/Mlistado.png")));
/* 120 */     ResourceBundle bundle = ResourceBundle.getBundle("internationalization/Mensajes");
/* 121 */     this.jButton1.setText(bundle.getString("crear"));
/* 122 */     this.jButton1.setHorizontalTextPosition(2);
/* 123 */     this.jButton1.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 125 */         CrearFicheroCSB.this.jButton1ActionPerformed(evt);
/*     */       }
/*     */     });
/* 129 */     this.jButton2.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/cancel.png")));
/* 130 */     this.jButton2.setText(bundle.getString("cancelar"));
/* 131 */     this.jButton2.setHorizontalTextPosition(2);
/* 132 */     this.jButton2.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 134 */         CrearFicheroCSB.this.jButton2ActionPerformed(evt);
/*     */       }
/*     */     });
/* 138 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 139 */     this.jPanel1.setLayout(jPanel1Layout);
/* 140 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel1).addComponent(this.jLabel2).addComponent(this.jLabel3)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.campoNombre, -1, 233, 32767).addComponent(this.campoNif, -2, 117, -2).addComponent(this.campoSufijo, -2, 79, -2))).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.jLabel4).addGap(18, 18, 18).addComponent(this.comboBancos, 0, 306, 32767)).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.labelFecha).addGap(18, 18, 18).addComponent(this.campoFecha, -2, 151, -2))).addContainerGap()).addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup().addComponent(this.jButton1).addGap(36, 36, 36).addComponent(this.jButton2).addGap(53, 53, 53)))));
/*     */ 
/* 172 */     jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel1).addComponent(this.campoNombre, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel2).addComponent(this.campoNif, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel3).addComponent(this.campoSufijo, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel4).addComponent(this.comboBancos, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.labelFecha).addComponent(this.campoFecha, -2, -1, -2)).addGap(18, 18, 18).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jButton2).addComponent(this.jButton1)).addContainerGap(-1, 32767)));
/*     */ 
/* 202 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 203 */     getContentPane().setLayout(layout);
/* 204 */     layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -1, -1, 32767).addContainerGap()));
/*     */ 
/* 211 */     layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -1, -1, 32767).addContainerGap()));
/*     */ 
/* 219 */     pack();
/*     */   }
/*     */ 
/*     */   private void jButton1ActionPerformed(ActionEvent evt) {
/* 223 */     String nombrePresentador = this.campoNombre.getText();
/* 224 */     String nifPresentador = this.campoNif.getText();
/* 225 */     if (nifPresentador.length() > 9) {
/* 226 */       nifPresentador = nifPresentador.substring(0, 9);
/*     */     }
/* 228 */     String sufijo = this.campoSufijo.getText();
/* 229 */     if (sufijo.equals("")) {
/* 230 */       sufijo = "000";
/*     */     }
/* 232 */     Date fechaCargo = this.campoFecha.getDate();
/* 233 */     if (fechaCargo == null) {
/* 234 */       fechaCargo = new Date();
/*     */     }
/* 236 */     TipoBanco banco = (TipoBanco)this.comboBancos.getSelectedItem();
/* 237 */     String cccOrdenante = "";
/* 238 */     if (banco != null)
/* 239 */       cccOrdenante = banco.getCcc();
/*     */     Remesas19 remesa19;
/*     */     Remesas58 remesa58;
/* 241 */     if (this.modelo == 19) {
/* 242 */       remesa19 = new Remesas19(fechaCargo, nombrePresentador, nifPresentador, sufijo, cccOrdenante, this.lista);
/*     */     }
/* 244 */     else if (this.modelo == 58) {
/* 245 */       remesa58 = new Remesas58(fechaCargo, nombrePresentador, nifPresentador, sufijo, cccOrdenante, this.lista);
/*     */     }
/* 247 */     setSufijo(sufijo);
/* 248 */     dispose();
/*     */   }
/*     */ 
/*     */   private void jButton2ActionPerformed(ActionEvent evt) {
/* 252 */     dispose();
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.dialogosFunciones.CrearFicheroCSB
 * JD-Core Version:    0.6.2
 */