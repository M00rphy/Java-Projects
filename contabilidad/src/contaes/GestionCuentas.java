/*     */ package contaes;
/*     */ 
/*     */ import contaes.cuentas.Agenda;
/*     */ import contaes.cuentas.GraficoDatosMensuales;
/*     */ import contaes.cuentas.PlanEmpresa;
/*     */ import contaes.cuentas.PlanGeneral;
/*     */ import contaes.cuentas.TablaDatosMensuales;
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.beans.PropertyVetoException;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDesktopPane;
/*     */ import javax.swing.JInternalFrame;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTabbedPane;
/*     */ import javax.swing.event.ChangeEvent;
/*     */ import javax.swing.event.ChangeListener;
/*     */ import javax.swing.event.InternalFrameAdapter;
/*     */ import javax.swing.event.InternalFrameEvent;
/*     */ 
/*     */ public class GestionCuentas extends MarcoInterno
/*     */ {
/*  45 */   private JPanel jContentPane = null;
/*  46 */   private JButton jButton = null;
/*  47 */   private JTabbedPane jTabbedPane = null;
/*  48 */   private PlanGeneral panel0 = new PlanGeneral();
/*  49 */   private PlanEmpresa panel1 = new PlanEmpresa();
/*  50 */   private Agenda panel2 = new Agenda();
/*  51 */   private TablaDatosMensuales panel3 = new TablaDatosMensuales();
/*  52 */   private GraficoDatosMensuales panel4 = new GraficoDatosMensuales();
/*  53 */   private int tabActual = 0; private int tabAnterior = 0;
/*  54 */   private String cuentaSelected = "Error";
/*  55 */   private String nombreCuentaSelected = "";
/*     */ 
/*     */   public GestionCuentas()
/*     */   {
/*  61 */     super(Mensajes.getString("gestCtas"));
/*  62 */     setDefaultCloseOperation(1);
/*  63 */     initialize();
/*  64 */     desactivarBotonSeleccionar();
/*     */   }
/*     */ 
/*     */   private void initialize()
/*     */   {
/*  73 */     setFrameIcon(new ImageIcon(getClass().getResource("/contaes/iconos/C18.png")));
/*  74 */     Rectangle parentBounds = Inicio.frame.localizacion();
/*  75 */     int ancho = parentBounds.width < 800 ? parentBounds.width : 730;
/*  76 */     int alto = parentBounds.height < 600 ? parentBounds.height : 550;
/*  77 */     setBounds(10, 50, ancho, alto);
/*  78 */     setContentPane(getJContentPane());
/*  79 */     addInternalFrameListener(new InternalFrameAdapter()
/*     */     {
/*     */       public void internalFrameDeactivated(InternalFrameEvent e)
/*     */       {
/*  83 */         if (Inicio.p.getControl() != 0)
/*  84 */           GestionCuentas.this.colocarCuenta();
/*     */       }
/*     */ 
/*     */       public void internalFrameActivated(InternalFrameEvent e)
/*     */       {
/*  90 */         if (Inicio.p.getControl() != 0) {
/*  91 */           GestionCuentas.this.activarBotonSeleccionar();
/*  92 */           GestionCuentas.this.jTabbedPane.setSelectedIndex(1);
/*     */         }
/*  94 */         if ((GestionCuentas.this.tabActual != 0) && (GestionCuentas.this.tabActual == 1))
/*  95 */           GestionCuentas.this.panel1.focoABuscar();
/*     */       }
/*     */ 
/*     */       public void internalFrameClosed(InternalFrameEvent e)
/*     */       {
/* 101 */         Inicio.frame.eliminarMarcoMenu(GestionCuentas.this.getTitle());
/*     */       }
/*     */     });
/*     */   }
/*     */ 
/*     */   private JPanel getJContentPane()
/*     */   {
/* 112 */     if (this.jContentPane == null) {
/* 113 */       this.jContentPane = new JPanel();
/* 114 */       this.jContentPane.setLayout(new BorderLayout());
/* 115 */       this.jContentPane.add(getJButton(), "South");
/* 116 */       this.jContentPane.add(getJTabbedPane(), "Center");
/*     */     }
/* 118 */     return this.jContentPane;
/*     */   }
/*     */ 
/*     */   private JButton getJButton()
/*     */   {
/* 127 */     if (this.jButton == null) {
/* 128 */       this.jButton = new JButton();
/* 129 */       this.jButton.setText(Mensajes.getString("selVolver"));
/* 130 */       this.jButton.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/ok.png")));
/* 131 */       this.jButton.addActionListener(new ActionListener()
/*     */       {
/*     */         public void actionPerformed(ActionEvent e) {
/* 134 */           GestionCuentas.this.botonPulsado();
/*     */         }
/*     */       });
/*     */     }
/* 138 */     return this.jButton;
/*     */   }
/*     */ 
/*     */   private JTabbedPane getJTabbedPane()
/*     */   {
/* 147 */     if (this.jTabbedPane == null) {
/* 148 */       this.jTabbedPane = new JTabbedPane();
/* 149 */       this.jTabbedPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
/* 150 */       this.jTabbedPane.addTab(Mensajes.getString("cuentas"), this.panel0);
/* 151 */       this.jTabbedPane.addTab(Mensajes.getString("subcuentas"), this.panel1);
/* 152 */       this.jTabbedPane.addTab(Mensajes.getString("agenda"), this.panel2);
/* 153 */       this.jTabbedPane.addTab(Mensajes.getString("tablaDatos"), this.panel3);
/* 154 */       this.jTabbedPane.addTab(Mensajes.getString("graficoDatos"), this.panel4);
/* 155 */       this.jTabbedPane.addChangeListener(new ChangeListener()
/*     */       {
/*     */         public void stateChanged(ChangeEvent e) {
/* 158 */           GestionCuentas.this.tabChanged();
/*     */         }
/*     */       });
/*     */     }
/* 162 */     return this.jTabbedPane;
/*     */   }
/*     */ 
/*     */   public void activarAgenda(String texto, boolean agenda) {
/* 166 */     this.panel1.buscar(texto);
/* 167 */     if (agenda) {
/* 168 */       String cuenta_a_mostrar = this.panel1.cuentaSeleccionada();
/* 169 */       if (!cuenta_a_mostrar.equals("Error")) {
/* 170 */         this.panel2.colocarDatos(cuenta_a_mostrar);
/* 171 */         this.jTabbedPane.setSelectedIndex(2);
/*     */       }
/*     */     }
/*     */     else {
/* 175 */       this.jTabbedPane.setSelectedIndex(1);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void tabChanged()
/*     */   {
/* 185 */     this.tabAnterior = this.tabActual;
/* 186 */     this.tabActual = this.jTabbedPane.getSelectedIndex();
/* 187 */     if (this.tabAnterior == 0) {
/* 188 */       this.cuentaSelected = this.panel0.cuentaSeleccionada();
/* 189 */       this.nombreCuentaSelected = this.panel0.nombreSeleccionado();
/* 190 */     } else if (this.tabAnterior == 1) {
/* 191 */       this.cuentaSelected = this.panel1.cuentaSeleccionada();
/* 192 */       this.nombreCuentaSelected = this.panel1.nombreSeleccionado();
/*     */     }
/* 194 */     if (this.tabActual != 0)
/* 195 */       if (this.tabActual == 1) {
/* 196 */         this.panel1.focoABuscar();
/* 197 */       } else if (this.tabActual == 2)
/*     */       {
/* 200 */         String cuenta_a_mostrar = this.panel1.cuentaSeleccionada();
/* 201 */         if (!cuenta_a_mostrar.equals("Error"))
/* 202 */           this.panel2.colocarDatos(cuenta_a_mostrar);
/*     */       }
/* 204 */       else if (this.tabActual == 3)
/*     */       {
/* 206 */         if (!this.cuentaSelected.equals("Error"))
/* 207 */           this.panel3.colocarDatos(this.cuentaSelected, this.nombreCuentaSelected);
/*     */       }
/* 209 */       else if (this.tabActual == 4)
/*     */       {
/* 211 */         if (!this.cuentaSelected.equals("Error"))
/* 212 */           this.panel4.colocarDatos(this.cuentaSelected, this.nombreCuentaSelected);
/*     */       }
/*     */   }
/*     */ 
/*     */   public void renovarTabla()
/*     */   {
/* 218 */     this.panel1.renovarTablaEmpresa();
/*     */   }
/*     */ 
/*     */   private void botonPulsado()
/*     */   {
/* 226 */     int control = Inicio.p.getControl();
/* 227 */     if (control != 0) {
/* 228 */       colocarCuenta();
/* 229 */       String titulo = Mensajes.getString("introAsto");
/* 230 */       if (((control == 2 ? 1 : 0) | (control == 3 ? 1 : 0)) != 0) {
/* 231 */         titulo = Mensajes.getString("introFacturas");
/*     */       }
/* 233 */       JInternalFrame[] todos = getDesktopPane().getAllFrames();
/* 234 */       int numero = todos.length;
/* 235 */       for (int x = 0; x <= numero; x++) {
/* 236 */         JInternalFrame f = todos[x];
/* 237 */         if (f.getTitle().equals(titulo)) {
/* 238 */           f.setVisible(true);
/*     */           try {
/* 240 */             if (f.isIcon()) {
/* 241 */               f.setIcon(false);
/*     */             }
/* 243 */             if (!f.isSelected())
/* 244 */               f.setSelected(true);
/*     */           }
/*     */           catch (PropertyVetoException exc)
/*     */           {
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void colocarCuenta()
/*     */   {
/* 261 */     Inicio.p.setCuentaStr(this.panel1.cuentaSeleccionada());
/* 262 */     desactivarBotonSeleccionar();
/*     */   }
/*     */ 
/*     */   private void activarBotonSeleccionar()
/*     */   {
/* 270 */     this.jButton.setEnabled(true);
/* 271 */     this.jButton.setVisible(true);
/*     */   }
/*     */ 
/*     */   private void desactivarBotonSeleccionar()
/*     */   {
/* 279 */     this.jButton.setEnabled(false);
/* 280 */     this.jButton.setVisible(false);
/*     */   }
/*     */ 
/*     */   public void cerrarConexion()
/*     */   {
/*     */   }
/*     */ 
/*     */   public void renovarModeloCuentas() {
/* 288 */     this.panel2.renovarModeloCuentas();
/*     */   }
/*     */ 
/*     */   public void renovarModeloFormasPago() {
/* 292 */     this.panel2.renovarModeloFormasPago();
/*     */   }
/*     */ 
/*     */   public void renovarModeloPaises()
/*     */   {
/* 297 */     this.panel2.renovarModeloPaises();
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.GestionCuentas
 * JD-Core Version:    0.6.2
 */