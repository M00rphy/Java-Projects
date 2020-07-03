/*     */ package contaes.cuentas;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.Puente;
/*     */ import contaes.auxiliar.CampoCuenta;
/*     */ import contaes.manejoDatos.ManejoSubcuentas;
/*     */ import contaes.manejoDatos.auxiliar.AcumuladosMensuales;
/*     */ import contaes.manejoDatos.funciones.SaldosCuatroEjercicios;
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.Frame;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.HeadlessException;
/*     */ import java.awt.Insets;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.KeyAdapter;
/*     */ import java.awt.event.KeyEvent;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ public class ComparativaSaldosDlg extends JDialog
/*     */ {
/*  51 */   private JPanel jContentPane = null;
/*  52 */   private JLabel jLabel = null;
/*  53 */   private CampoCuenta campoCuenta = null;
/*  54 */   private JButton bAceptar = null;
/*  55 */   private JButton bCancelar = null;
/*  56 */   private String cuenta = null;
/*  57 */   private String nombreCuenta = null;
/*  58 */   private AcumuladosMensuales[] acYear = null;
/*  59 */   private String[] year = null;
/*     */   private int nYear;
/*  61 */   private boolean hayDatos = false;
/*  62 */   private Acciones escuchaAccion = null;
/*     */ 
/*     */   public ComparativaSaldosDlg() throws HeadlessException
/*     */   {
/*  66 */     initialize();
/*     */   }
/*     */ 
/*     */   public ComparativaSaldosDlg(Frame arg0, boolean arg1) throws HeadlessException
/*     */   {
/*  71 */     super(arg0, arg1);
/*  72 */     initialize();
/*     */   }
/*     */ 
/*     */   private void initialize()
/*     */   {
/*  81 */     this.escuchaAccion = new Acciones();
/*  82 */     setSize(300, 200);
/*  83 */     setTitle(Mensajes.getString("comparativaGrafica"));
/*  84 */     setContentPane(getJContentPane());
/*     */   }
/*     */ 
/*     */   private JPanel getJContentPane()
/*     */   {
/*  93 */     if (this.jContentPane == null) {
/*  94 */       GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
/*  95 */       gridBagConstraints3.gridx = 1;
/*  96 */       gridBagConstraints3.insets = new Insets(10, 10, 10, 20);
/*  97 */       gridBagConstraints3.gridy = 1;
/*  98 */       GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
/*  99 */       gridBagConstraints2.gridx = 0;
/* 100 */       gridBagConstraints2.insets = new Insets(10, 20, 10, 10);
/* 101 */       gridBagConstraints2.gridy = 1;
/* 102 */       GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
/* 103 */       gridBagConstraints1.fill = 2;
/* 104 */       gridBagConstraints1.gridy = 0;
/* 105 */       gridBagConstraints1.weightx = 1.0D;
/* 106 */       gridBagConstraints1.insets = new Insets(10, 5, 5, 10);
/* 107 */       gridBagConstraints1.gridx = 1;
/* 108 */       GridBagConstraints gridBagConstraints = new GridBagConstraints();
/* 109 */       gridBagConstraints.gridx = 0;
/* 110 */       gridBagConstraints.insets = new Insets(10, 10, 5, 10);
/* 111 */       gridBagConstraints.anchor = 10;
/* 112 */       gridBagConstraints.gridy = 0;
/* 113 */       this.jLabel = new JLabel();
/* 114 */       this.jLabel.setText(Mensajes.getString("cuenta"));
/* 115 */       this.jContentPane = new JPanel();
/* 116 */       this.jContentPane.setLayout(new GridBagLayout());
/*     */ 
/* 119 */       this.jContentPane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createEtchedBorder(1)));
/* 120 */       this.jContentPane.add(this.jLabel, gridBagConstraints);
/* 121 */       this.jContentPane.add(getCampoCuenta(), gridBagConstraints1);
/* 122 */       this.jContentPane.add(getBAceptar(), gridBagConstraints2);
/* 123 */       this.jContentPane.add(getBCancelar(), gridBagConstraints3);
/*     */     }
/* 125 */     return this.jContentPane;
/*     */   }
/*     */ 
/*     */   private CampoCuenta getCampoCuenta() {
/* 129 */     if (this.campoCuenta == null) {
/* 130 */       ManejoSubcuentas subcuentaM = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 131 */       this.campoCuenta = new CampoCuenta(subcuentaM);
/* 132 */       this.campoCuenta.addKeyListener(new KeyAdapter()
/*     */       {
/*     */         public void keyPressed(KeyEvent arg0) {
/* 135 */           int tecla = arg0.getKeyCode();
/* 136 */           if (tecla == 10) {
/* 137 */             ComparativaSaldosDlg.this.bAceptar.requestFocus();
/*     */           }
/*     */         }
/*     */       });
/*     */     }
/* 142 */     return this.campoCuenta;
/*     */   }
/*     */ 
/*     */   private JButton getBAceptar()
/*     */   {
/* 151 */     if (this.bAceptar == null) {
/* 152 */       this.bAceptar = new JButton();
/* 153 */       this.bAceptar.setHorizontalTextPosition(2);
/* 154 */       this.bAceptar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/ok.png")));
/* 155 */       this.bAceptar.setText(Mensajes.getString("aceptar"));
/* 156 */       this.bAceptar.addActionListener(this.escuchaAccion);
/*     */     }
/* 158 */     return this.bAceptar;
/*     */   }
/*     */ 
/*     */   private JButton getBCancelar()
/*     */   {
/* 167 */     if (this.bCancelar == null) {
/* 168 */       this.bCancelar = new JButton();
/* 169 */       this.bCancelar.setHorizontalTextPosition(2);
/* 170 */       this.bCancelar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/cancel.png")));
/* 171 */       this.bCancelar.setText(Mensajes.getString("cancelar"));
/* 172 */       this.bCancelar.addActionListener(this.escuchaAccion);
/*     */     }
/* 174 */     return this.bCancelar;
/*     */   }
/*     */ 
/*     */   private void CrearDatos(String cuenta)
/*     */   {
/* 184 */     this.nombreCuenta = this.campoCuenta.getNombre();
/* 185 */     boolean sinPYG = Integer.parseInt(cuenta) >= 60000000;
/* 186 */     SaldosCuatroEjercicios clase = new SaldosCuatroEjercicios(Inicio.getCEmpresa(), cuenta, sinPYG);
/* 187 */     clase.generarDatos();
/* 188 */     this.year = clase.getYear();
/* 189 */     this.acYear = clase.getSaldos();
/* 190 */     this.hayDatos = clase.isHayDatos();
/* 191 */     this.nYear = this.acYear.length;
/*     */   }
/*     */ 
/*     */   public boolean isHayDatos()
/*     */   {
/* 214 */     return this.hayDatos;
/*     */   }
/*     */ 
/*     */   public String getCuenta()
/*     */   {
/* 221 */     return this.cuenta;
/*     */   }
/*     */ 
/*     */   private void setCuenta(String cuenta)
/*     */   {
/* 228 */     this.cuenta = cuenta;
/*     */   }
/*     */ 
/*     */   public String getNombreCuenta()
/*     */   {
/* 235 */     return this.nombreCuenta;
/*     */   }
/*     */ 
/*     */   public int getNYear()
/*     */   {
/* 242 */     return this.nYear;
/*     */   }
/*     */ 
/*     */   public String[] getYear()
/*     */   {
/* 249 */     return this.year;
/*     */   }
/*     */ 
/*     */   public double[][] getDatosSaldos()
/*     */   {
/* 258 */     double[][] datos = new double[this.nYear][14];
/* 259 */     for (int x = 0; x < this.nYear; x++) {
/* 260 */       for (int y = 0; y < 14; y++)
/*     */       {
/* 262 */         datos[x][y] = this.acYear[x].getS()[y];
/*     */       }
/*     */     }
/* 265 */     return datos;
/*     */   }
/*     */ 
/*     */   private class Acciones
/*     */     implements ActionListener
/*     */   {
/*     */     private Acciones()
/*     */     {
/*     */     }
/*     */ 
/*     */     public void actionPerformed(ActionEvent e)
/*     */     {
/* 197 */       Object fuente = e.getSource();
/* 198 */       if (fuente == ComparativaSaldosDlg.this.bAceptar) {
/* 199 */         if ((!ComparativaSaldosDlg.this.campoCuenta.getText().equals("")) && (ComparativaSaldosDlg.this.campoCuenta.getNombre() != null)) {
/* 200 */           ComparativaSaldosDlg.this.setCuenta(ComparativaSaldosDlg.this.campoCuenta.getText());
/* 201 */           ComparativaSaldosDlg.this.CrearDatos(ComparativaSaldosDlg.this.campoCuenta.getText());
/*     */         }
/* 203 */         ComparativaSaldosDlg.this.dispose();
/* 204 */       } else if (fuente == ComparativaSaldosDlg.this.bCancelar) {
/* 205 */         ComparativaSaldosDlg.this.dispose();
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.cuentas.ComparativaSaldosDlg
 * JD-Core Version:    0.6.2
 */