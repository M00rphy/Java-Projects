/*     */ package contaes.dialogosAuxiliares;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.Puente;
/*     */ import contaes.auxiliar.DocNumeros;
/*     */ import contaes.manejoDatos.ManejoSubcuentas;
/*     */ import contaes.manejoDatos.funciones.ComprobarDato;
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Frame;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.PrintStream;
/*     */ import java.net.URL;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ 
/*     */ public class PagoTarjeta extends JDialog
/*     */   implements ActionListener
/*     */ {
/*  53 */   int cuenta = -1;
/*  54 */   double comision = 0.0D;
/*     */ 
/*  56 */   JPanel panel1 = new JPanel();
/*  57 */   GridBagLayout gbLayout = new GridBagLayout();
/*  58 */   JLabel etiq1 = new JLabel();
/*  59 */   JLabel etiq2 = new JLabel();
/*  60 */   JTextField campoComision = new JTextField();
/*  61 */   JComboBox cajaCuentas = new JComboBox();
/*  62 */   JButton aceptar = new JButton();
/*  63 */   JButton cancelar = new JButton();
/*  64 */   ImageIcon iconoAceptar = createImageIcon("/contaes/iconos/ok.png");
/*  65 */   ImageIcon iconoCancelar = createImageIcon("/contaes/iconos/cancel.png");
/*     */ 
/*     */   public PagoTarjeta(Frame owner, String title, boolean modal) {
/*  68 */     super(owner, title, modal);
/*     */     try {
/*  70 */       setDefaultCloseOperation(2);
/*  71 */       jInit();
/*  72 */       pack();
/*     */     }
/*     */     catch (Exception exception) {
/*  75 */       exception.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public PagoTarjeta() {
/*  80 */     this(new Frame(), Mensajes.getString("porcentCtaComi"), true);
/*     */   }
/*     */ 
/*     */   private void jInit() throws Exception {
/*  84 */     this.etiq1.setText("% " + Mensajes.getString("comision"));
/*  85 */     this.etiq2.setText(Mensajes.getString("ctaGastos"));
/*  86 */     this.aceptar.setText(Mensajes.getString("aceptar"));
/*  87 */     this.aceptar.setIcon(this.iconoAceptar);
/*  88 */     this.aceptar.setVerticalTextPosition(0);
/*  89 */     this.aceptar.setHorizontalTextPosition(2);
/*  90 */     this.aceptar.addActionListener(this);
/*  91 */     this.cancelar.setText(Mensajes.getString("cancelar"));
/*  92 */     this.cancelar.setIcon(this.iconoCancelar);
/*  93 */     this.cancelar.setVerticalTextPosition(0);
/*  94 */     this.cancelar.setHorizontalTextPosition(2);
/*  95 */     this.cancelar.addActionListener(this);
/*  96 */     this.campoComision.setMinimumSize(new Dimension(100, 19));
/*  97 */     this.campoComision.setDocument(new DocNumeros());
/*  98 */     this.campoComision.addActionListener(this);
/*  99 */     llenarLista();
/*     */ 
/* 101 */     this.panel1.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createEtchedBorder(1)));
/* 102 */     this.panel1.setLayout(this.gbLayout);
/*     */ 
/* 104 */     GridBagConstraints cons = new GridBagConstraints();
/* 105 */     cons.insets.bottom = 7;
/* 106 */     cons.insets.top = 7;
/* 107 */     cons.insets.left = 7;
/* 108 */     cons.insets.right = 7;
/*     */ 
/* 110 */     cons.gridy = 0;
/* 111 */     cons.gridx = 0;
/* 112 */     this.gbLayout.setConstraints(this.etiq1, cons);
/* 113 */     this.panel1.add(this.etiq1);
/*     */ 
/* 115 */     cons.gridx = 1;
/* 116 */     this.gbLayout.setConstraints(this.etiq2, cons);
/* 117 */     this.panel1.add(this.etiq2);
/*     */ 
/* 120 */     cons.fill = 2;
/* 121 */     cons.gridy = 1;
/* 122 */     cons.gridx = 0;
/* 123 */     this.gbLayout.setConstraints(this.campoComision, cons);
/* 124 */     this.panel1.add(this.campoComision);
/*     */ 
/* 126 */     cons.gridx = 1;
/* 127 */     this.gbLayout.setConstraints(this.cajaCuentas, cons);
/* 128 */     this.panel1.add(this.cajaCuentas);
/*     */ 
/* 131 */     cons.fill = 0;
/* 132 */     cons.anchor = 10;
/* 133 */     cons.gridy = 2;
/* 134 */     cons.gridx = 0;
/* 135 */     this.gbLayout.setConstraints(this.aceptar, cons);
/* 136 */     this.panel1.add(this.aceptar);
/*     */ 
/* 138 */     cons.gridx = 1;
/* 139 */     this.gbLayout.setConstraints(this.cancelar, cons);
/* 140 */     this.panel1.add(this.cancelar);
/*     */ 
/* 142 */     getContentPane().add(this.panel1);
/*     */   }
/*     */ 
/*     */   public void actionPerformed(ActionEvent e) {
/* 146 */     Object campo = e.getSource();
/* 147 */     if (campo == this.aceptar) {
/* 148 */       String t = this.cajaCuentas.getSelectedItem().toString();
/* 149 */       String comi = this.campoComision.getText();
/* 150 */       this.cuenta = Integer.parseInt(t);
/* 151 */       ComprobarDato dato = new ComprobarDato(comi);
/* 152 */       if (dato.esDoble())
/* 153 */         this.comision = Double.parseDouble(this.campoComision.getText());
/* 154 */       dispose();
/*     */     }
/* 156 */     else if (campo == this.cancelar) {
/* 157 */       this.cuenta = -1;
/* 158 */       dispose();
/*     */     }
/* 160 */     else if (campo == this.campoComision) {
/* 161 */       this.cajaCuentas.requestFocus();
/*     */     }
/*     */   }
/*     */ 
/*     */   public int enCuenta() {
/* 166 */     return this.cuenta;
/*     */   }
/*     */ 
/*     */   public double conComision() {
/* 170 */     return this.comision;
/*     */   }
/*     */ 
/*     */   protected void llenarLista() {
/* 174 */     ManejoSubcuentas cuentaM = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 175 */     LinkedList lista = cuentaM.listadoSubcuentas(66000000, 66999999);
/* 176 */     for (Iterator i$ = lista.iterator(); i$.hasNext(); ) { int x = ((Integer)i$.next()).intValue();
/* 177 */       this.cajaCuentas.addItem(String.valueOf(x)); }
/* 178 */     if (this.cajaCuentas.getItemCount() == 0) dispose(); 
/*     */   }
/*     */ 
/*     */   protected static ImageIcon createImageIcon(String path)
/*     */   {
/* 182 */     URL imgURL = PagoTarjeta.class.getResource(path);
/* 183 */     if (imgURL != null)
/* 184 */       return new ImageIcon(imgURL);
/* 185 */     System.err.println("Couldn't find file: " + path);
/* 186 */     return null;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.dialogosAuxiliares.PagoTarjeta
 * JD-Core Version:    0.6.2
 */