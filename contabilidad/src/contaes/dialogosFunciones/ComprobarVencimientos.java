/*     */ package contaes.dialogosFunciones;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.Puente;
/*     */ import contaes.manejoDatos.ManejoVencimientos;
/*     */ import contaes.manejoDatos.funciones.BuscarVencimientosSinFactura;
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Font;
/*     */ import java.awt.Frame;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTextArea;
/*     */ 
/*     */ public class ComprobarVencimientos extends JDialog
/*     */ {
/*  47 */   private JPanel jContentPane = null;
/*  48 */   private JPanel panelAbajo = null;
/*  49 */   private JButton aceptar = null;
/*  50 */   private JButton cancelar = null;
/*  51 */   private JLabel jLabel = null;
/*  52 */   private JScrollPane scrollPane = null;
/*  53 */   private JTextArea textArea = null;
/*     */   private String lista;
/*     */   private int[] listaId;
/*     */ 
/*     */   public ComprobarVencimientos()
/*     */   {
/*  63 */     initialize();
/*     */   }
/*     */ 
/*     */   public ComprobarVencimientos(Frame owner, boolean modal) {
/*  67 */     super(owner, modal);
/*  68 */     initialize();
/*     */   }
/*     */ 
/*     */   private void initialize()
/*     */   {
/*  77 */     BuscarVencimientosSinFactura clase = new BuscarVencimientosSinFactura(Inicio.getCEmpresa(), esRecibida(), Inicio.p.getEjercicio());
/*     */ 
/*  81 */     this.lista = clase.getLista();
/*  82 */     this.listaId = clase.getListaId();
/*  83 */     setSize(415, 350);
/*  84 */     setTitle(Mensajes.getString("comprobar") + " " + Mensajes.getString("vencimientos"));
/*  85 */     setContentPane(getJContentPane());
/*     */   }
/*     */ 
/*     */   private JPanel getJContentPane()
/*     */   {
/*  94 */     if (this.jContentPane == null) {
/*  95 */       this.jLabel = new JLabel();
/*  96 */       this.jLabel.setText(Mensajes.getString("vtoSinFra"));
/*  97 */       this.jLabel.setHorizontalAlignment(0);
/*  98 */       this.jContentPane = new JPanel();
/*  99 */       this.jContentPane.setLayout(new BorderLayout());
/*     */ 
/* 101 */       this.jContentPane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(1), BorderFactory.createEmptyBorder(5, 5, 5, 5))));
/*     */ 
/* 104 */       this.jContentPane.add(getPanelAbajo(), "South");
/* 105 */       this.jContentPane.add(this.jLabel, "North");
/* 106 */       this.jContentPane.add(getScrollPane(), "Center");
/*     */     }
/* 108 */     return this.jContentPane;
/*     */   }
/*     */ 
/*     */   private JPanel getPanelAbajo()
/*     */   {
/* 117 */     if (this.panelAbajo == null) {
/* 118 */       this.panelAbajo = new JPanel();
/* 119 */       this.panelAbajo.setLayout(new BorderLayout());
/* 120 */       this.panelAbajo.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
/* 121 */       if (this.listaId != null)
/* 122 */         this.panelAbajo.add(getAceptar(), "West");
/* 123 */       this.panelAbajo.add(getCancelar(), "East");
/*     */     }
/* 125 */     return this.panelAbajo;
/*     */   }
/*     */ 
/*     */   private JButton getAceptar()
/*     */   {
/* 134 */     if (this.aceptar == null) {
/* 135 */       this.aceptar = new JButton();
/* 136 */       this.aceptar.setHorizontalAlignment(2);
/* 137 */       this.aceptar.setText(Mensajes.getString("borrar"));
/* 138 */       this.aceptar.setHorizontalTextPosition(2);
/* 139 */       this.aceptar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/ok.png")));
/* 140 */       this.aceptar.addActionListener(new ActionListener() {
/*     */         public void actionPerformed(ActionEvent e) {
/* 142 */           ComprobarVencimientos.this.borrarVencimientos();
/* 143 */           ComprobarVencimientos.this.dispose();
/*     */         }
/*     */       });
/*     */     }
/* 147 */     return this.aceptar;
/*     */   }
/*     */ 
/*     */   private JButton getCancelar()
/*     */   {
/* 156 */     if (this.cancelar == null) {
/* 157 */       this.cancelar = new JButton();
/* 158 */       this.cancelar.setHorizontalTextPosition(2);
/* 159 */       this.cancelar.setText(Mensajes.getString("cancelar"));
/* 160 */       this.cancelar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/cancel.png")));
/* 161 */       this.cancelar.addActionListener(new ActionListener() {
/*     */         public void actionPerformed(ActionEvent e) {
/* 163 */           ComprobarVencimientos.this.dispose();
/*     */         }
/*     */       });
/*     */     }
/* 167 */     return this.cancelar;
/*     */   }
/*     */ 
/*     */   private JScrollPane getScrollPane()
/*     */   {
/* 176 */     if (this.scrollPane == null) {
/* 177 */       this.scrollPane = new JScrollPane();
/* 178 */       this.scrollPane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10), BorderFactory.createBevelBorder(1)));
/* 179 */       this.scrollPane.setViewportView(getTextArea());
/*     */     }
/* 181 */     return this.scrollPane;
/*     */   }
/*     */ 
/*     */   private JTextArea getTextArea()
/*     */   {
/* 190 */     if (this.textArea == null) {
/* 191 */       this.textArea = new JTextArea(this.lista);
/* 192 */       this.textArea.setEditable(false);
/* 193 */       this.textArea.setFont(new Font("Monospaced", 0, 12));
/*     */     }
/* 195 */     return this.textArea;
/*     */   }
/*     */ 
/*     */   private void borrarVencimientos() {
/* 199 */     ManejoVencimientos vencimientoM = new ManejoVencimientos(Inicio.getCEmpresa());
/* 200 */     for (int x = 0; x < this.listaId.length; x++)
/* 201 */       vencimientoM.borrar(!esRecibida(), this.listaId[x]);
/*     */   }
/*     */ 
/*     */   protected boolean esRecibida()
/*     */   {
/* 206 */     return true;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.dialogosFunciones.ComprobarVencimientos
 * JD-Core Version:    0.6.2
 */