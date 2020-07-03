/*     */ package contaes.empresas;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.Puente;
/*     */ import contaes.auxiliar.DocNumPositivos;
/*     */ import contaes.manejoDatos.ManejoEmpresas;
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.Frame;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Insets;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.LinkedList;
import java.util.List;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ 
/*     */ public class CreaEjercicio extends JDialog
/*     */ {
/*  49 */   private JPanel jContentPane = null;
/*  50 */   private JLabel jLabel = null;
/*  51 */   private JComboBox listaEmpresas = null;
/*  52 */   private JLabel jLabel1 = null;
/*  53 */   private JTextField cTexto = null;
/*  54 */   private JButton bCrear = null;
/*  55 */   private JButton bCancelar = null;
/*     */   private ManejoEmpresas empresaM;
/*     */ 
/*     */   public CreaEjercicio()
/*     */   {
/*  63 */     this(new Frame(), Mensajes.getString("creaEj"), true);
/*     */   }
/*     */ 
/*     */   public CreaEjercicio(Frame owner, String title, boolean modal) {
/*  67 */     super(owner, title, modal);
/*  68 */     this.empresaM = new ManejoEmpresas(Inicio.getCGeneral(), Inicio.p.getEmpresa());
/*  69 */     initialize();
/*     */   }
/*     */ 
/*     */   private void initialize()
/*     */   {
/*  78 */     setSize(300, 210);
/*  79 */     setContentPane(getJContentPane());
/*     */   }
/*     */ 
/*     */   private JPanel getJContentPane()
/*     */   {
/*  88 */     if (this.jContentPane == null) {
/*  89 */       GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
/*  90 */       gridBagConstraints5.gridx = 1;
/*  91 */       gridBagConstraints5.insets = new Insets(20, 7, 10, 10);
/*  92 */       gridBagConstraints5.gridy = 4;
/*  93 */       GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
/*  94 */       gridBagConstraints4.gridx = 0;
/*  95 */       gridBagConstraints4.insets = new Insets(20, 10, 10, 7);
/*  96 */       gridBagConstraints4.gridy = 4;
/*  97 */       GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
/*  98 */       gridBagConstraints3.fill = 2;
/*  99 */       gridBagConstraints3.gridy = 3;
/* 100 */       gridBagConstraints3.weightx = 1.0D;
/* 101 */       gridBagConstraints3.ipadx = 0;
/* 102 */       gridBagConstraints3.ipady = 0;
/* 103 */       gridBagConstraints3.insets = new Insets(0, 10, 0, 0);
/* 104 */       gridBagConstraints3.gridx = 0;
/* 105 */       GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
/* 106 */       gridBagConstraints2.gridx = 0;
/* 107 */       gridBagConstraints2.anchor = 17;
/* 108 */       gridBagConstraints2.insets = new Insets(10, 10, 5, 0);
/* 109 */       gridBagConstraints2.gridy = 2;
/* 110 */       this.jLabel1 = new JLabel();
/* 111 */       this.jLabel1.setText(Mensajes.getString("ejercicio") + ":");
/* 112 */       GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
/* 113 */       gridBagConstraints1.fill = 2;
/* 114 */       gridBagConstraints1.gridy = 1;
/* 115 */       gridBagConstraints1.weightx = 1.0D;
/* 116 */       gridBagConstraints1.gridwidth = 2;
/* 117 */       gridBagConstraints1.insets = new Insets(0, 10, 0, 10);
/* 118 */       gridBagConstraints1.gridx = 0;
/* 119 */       GridBagConstraints gridBagConstraints = new GridBagConstraints();
/* 120 */       gridBagConstraints.gridx = 0;
/* 121 */       gridBagConstraints.anchor = 17;
/* 122 */       gridBagConstraints.insets = new Insets(10, 10, 5, 0);
/* 123 */       gridBagConstraints.gridy = 0;
/* 124 */       this.jLabel = new JLabel();
/* 125 */       this.jLabel.setText(Mensajes.getString("empresa") + ":");
/* 126 */       this.jContentPane = new JPanel();
/* 127 */       this.jContentPane.setLayout(new GridBagLayout());
/* 128 */       this.jContentPane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createEtchedBorder(1)));
/*     */ 
/* 130 */       this.jContentPane.add(this.jLabel, gridBagConstraints);
/* 131 */       this.jContentPane.add(getListaEmpresas(), gridBagConstraints1);
/* 132 */       this.jContentPane.add(this.jLabel1, gridBagConstraints2);
/* 133 */       this.jContentPane.add(getCTexto(), gridBagConstraints3);
/* 134 */       this.jContentPane.add(getBCrear(), gridBagConstraints4);
/* 135 */       this.jContentPane.add(getBCancelar(), gridBagConstraints5);
/*     */     }
/* 137 */     return this.jContentPane;
/*     */   }
/*     */ 
/*     */   private JComboBox getListaEmpresas()
/*     */   {
/* 146 */     if (this.listaEmpresas == null) {
/* 147 */       this.listaEmpresas = new JComboBox();
/* 148 */       LinkedList lista = new LinkedList();
/* 149 */       lista.addAll(this.empresaM.listaEmpresas());
/* 150 */       if (lista.size() > 0) {
/* 151 */         for (String x :(List<String>) lista)
/* 152 */           this.listaEmpresas.addItem(x);
/*     */       }
/*     */     }
/* 155 */     return this.listaEmpresas;
/*     */   }
/*     */ 
/*     */   private JTextField getCTexto()
/*     */   {
/* 164 */     if (this.cTexto == null) {
/* 165 */       this.cTexto = new JTextField();
/* 166 */       this.cTexto.setDocument(new DocNumPositivos());
/*     */     }
/* 168 */     return this.cTexto;
/*     */   }
/*     */ 
/*     */   private JButton getBCrear()
/*     */   {
/* 177 */     if (this.bCrear == null) {
/* 178 */       this.bCrear = new JButton();
/* 179 */       this.bCrear.setText(Mensajes.getString("crear"));
/* 180 */       this.bCrear.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/new.png")));
/* 181 */       this.bCrear.setHorizontalTextPosition(2);
/* 182 */       this.bCrear.addActionListener(new ActionListener() {
/*     */         public void actionPerformed(ActionEvent e) {
/* 184 */           CreaEjercicio.this.crearPulsado();
/*     */         }
/*     */       });
/*     */     }
/* 188 */     return this.bCrear;
/*     */   }
/*     */ 
/*     */   private JButton getBCancelar()
/*     */   {
/* 197 */     if (this.bCancelar == null) {
/* 198 */       this.bCancelar = new JButton();
/* 199 */       this.bCancelar.setText(Mensajes.getString("cancelar"));
/* 200 */       this.bCancelar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/cancel.png")));
/* 201 */       this.bCancelar.setHorizontalTextPosition(2);
/* 202 */       this.bCancelar.addActionListener(new ActionListener() {
/*     */         public void actionPerformed(ActionEvent e) {
/* 204 */           CreaEjercicio.this.dispose();
/*     */         }
/*     */       });
/*     */     }
/* 208 */     return this.bCancelar;
/*     */   }
/*     */ 
/*     */   private void crearPulsado()
/*     */   {
/* 217 */     String empresa = this.listaEmpresas.getSelectedItem().toString();
/* 218 */     int idEmpresa = this.empresaM.getIdEmpresa(empresa);
/* 219 */     if (idEmpresa != -1) {
/* 220 */       String newEjercicio = this.cTexto.getText();
/* 221 */       boolean copiarCuentas = false;
/*     */       try
/*     */       {
/* 224 */         int newEj_int = Integer.parseInt(newEjercicio);
/* 225 */         String retorno = this.empresaM.crearEjercicio1(idEmpresa, newEj_int);
/* 226 */         if (retorno.equals("existe")) {
/* 227 */           JOptionPane.showMessageDialog(getContentPane(), Mensajes.getString("ejercicioYaExiste"));
/* 228 */           return;
/*     */         }
/* 230 */         if (retorno.equals("RangoNoValido")) {
/* 231 */           JOptionPane.showMessageDialog(getContentPane(), Mensajes.getString("rangoAnnos"));
/* 232 */           return;
/*     */         }
/* 234 */         if (retorno.equals("primero")) {
/* 235 */           copiarCuentas = false;
/*     */         }
/* 237 */         else if (retorno.equals("NoPrimero")) {
/* 238 */           int confirma = JOptionPane.showConfirmDialog(getContentPane(), Mensajes.getString("copiarCtas"), Mensajes.getString("confirma"), 0);
/*     */ 
/* 241 */           if (confirma == 0)
/* 242 */             copiarCuentas = true;
/*     */         }
/* 244 */         this.empresaM.crearEjercicio2(idEmpresa, newEjercicio, copiarCuentas);
/*     */       } catch (NumberFormatException e) {
/* 246 */         e.printStackTrace();
/*     */       }
/* 248 */       JOptionPane.showMessageDialog(getContentPane(), Mensajes.getString("ejercicioCreado"));
/*     */ 
/* 250 */       dispose();
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.empresas.CreaEjercicio
 * JD-Core Version:    0.6.2
 */