/*     */ package contaes.dialogosAuxiliares;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.Puente;
/*     */ import contaes.manejoDatos.ManejoSubcuentas;
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dialog;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Frame;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.util.LinkedList;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.DefaultListModel;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JList;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTextField;
/*     */ 
/*     */ public class MostrarCuentas extends JDialog
/*     */ {
/*  57 */   JPanel panel1 = new JPanel();
/*     */   JScrollPane panel2;
/*  59 */   JLabel etiqueta = new JLabel();
/*  60 */   BorderLayout borderLayout1 = new BorderLayout(5, 5);
/*     */   JList lista;
/*  62 */   DefaultListModel modeloLista = new DefaultListModel();
/*  63 */   String seleccion = "";
/*  64 */   JPanel panel3 = null;
/*  65 */   JButton botonBuscar = new JButton(Mensajes.getString("buscar"));
/*  66 */   JTextField campoBuscar = new JTextField();
/*     */   private int cuentaInicial;
/*     */   private int cuentaFinal;
/*     */ 
/*     */   public MostrarCuentas(Frame owner, String title, boolean modal)
/*     */   {
/*  72 */     super(owner, title, modal);
/*  73 */     this.cuentaInicial = 10000000;
/*  74 */     this.cuentaFinal = 80000000;
/*     */     try {
/*  76 */       setDefaultCloseOperation(2);
/*  77 */       initialize();
/*  78 */       pack();
/*     */     }
/*     */     catch (Exception exception) {
/*  81 */       exception.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public MostrarCuentas(Dialog owner, String title, boolean modal) {
/*  86 */     super(owner, title, modal);
/*  87 */     this.cuentaInicial = 10000000;
/*  88 */     this.cuentaFinal = 80000000;
/*     */     try {
/*  90 */       setDefaultCloseOperation(2);
/*  91 */       initialize();
/*  92 */       pack();
/*     */     }
/*     */     catch (Exception exception) {
/*  95 */       exception.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public MostrarCuentas(Frame owner, String title, boolean modal, int cuentaInicial, int cuentaFinal) {
/* 100 */     super(owner, title, modal);
/* 101 */     this.cuentaInicial = cuentaInicial;
/* 102 */     this.cuentaFinal = cuentaFinal;
/*     */     try {
/* 104 */       setDefaultCloseOperation(2);
/* 105 */       initialize();
/* 106 */       pack();
/*     */     }
/*     */     catch (Exception exception) {
/* 109 */       exception.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public MostrarCuentas(Dialog owner, String title, boolean modal, int cuentaInicial, int cuentaFinal) {
/* 114 */     super(owner, title, modal);
/* 115 */     this.cuentaInicial = cuentaInicial;
/* 116 */     this.cuentaFinal = cuentaFinal;
/*     */     try {
/* 118 */       setDefaultCloseOperation(2);
/* 119 */       initialize();
/* 120 */       pack();
/*     */     }
/*     */     catch (Exception exception) {
/* 123 */       exception.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public MostrarCuentas() {
/* 128 */     this(new Frame(), Mensajes.getString("conceptos"), true);
/*     */   }
/*     */ 
/*     */   private void initialize() throws Exception {
/* 132 */     this.panel1.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(1), BorderFactory.createEmptyBorder(5, 5, 5, 5))));
/*     */ 
/* 135 */     ManejoSubcuentas cuentaM = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 136 */     LinkedList listaCod = cuentaM.listadoSubcuentas(this.cuentaInicial, this.cuentaFinal);
/* 137 */     LinkedList listaTitle = cuentaM.listaSubcuentasTitle(this.cuentaInicial, this.cuentaFinal);
/* 138 */     for (int x = 0; x < listaCod.size(); x++) {
/* 139 */       this.modeloLista.addElement(listaCod.get(x) + " - " + (String)listaTitle.get(x));
/*     */     }
/* 141 */     this.lista = new JList(this.modeloLista);
/* 142 */     this.lista.setSelectionMode(0);
/* 143 */     this.lista.addKeyListener(new mostrarCuentas_lista_keyAdapter(this));
/* 144 */     this.lista.addMouseListener(new mostrarCuentas_lista_mouseAdapter(this));
/*     */ 
/* 146 */     this.panel2 = new JScrollPane(this.lista);
/* 147 */     this.etiqueta.setText(Mensajes.getString("selecUnaCuenta"));
/* 148 */     this.panel1.setLayout(this.borderLayout1);
/* 149 */     this.panel1.add(this.etiqueta, "North");
/* 150 */     this.panel1.add(this.panel2, "Center");
/* 151 */     this.panel1.add(getPanelBuscar(), "South");
/* 152 */     getContentPane().add(this.panel1);
/* 153 */     this.lista.setSelectedIndex(0);
/*     */   }
/*     */ 
/*     */   private JPanel getPanelBuscar() {
/* 157 */     if (this.panel3 == null) {
/* 158 */       this.panel3 = new JPanel();
/*     */ 
/* 160 */       this.campoBuscar.setBounds(5, 5, 120, 19);
/* 161 */       this.campoBuscar.setPreferredSize(new Dimension(120, 19));
/* 162 */       this.campoBuscar.addActionListener(new ActionListener()
/*     */       {
/*     */         public void actionPerformed(ActionEvent e) {
/* 165 */           String texto = MostrarCuentas.this.campoBuscar.getText();
/* 166 */           if (!texto.equals(""))
/* 167 */             MostrarCuentas.this.buscar(texto);
/*     */         }
/*     */       });
/* 171 */       this.botonBuscar.setBounds(135, 5, 70, 23);
/* 172 */       this.botonBuscar.setHorizontalTextPosition(2);
/* 173 */       this.botonBuscar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/find.png")));
/* 174 */       this.botonBuscar.addActionListener(new ActionListener()
/*     */       {
/*     */         public void actionPerformed(ActionEvent e) {
/* 177 */           String texto = MostrarCuentas.this.campoBuscar.getText();
/* 178 */           if (!texto.equals(""))
/* 179 */             MostrarCuentas.this.buscar(texto);
/*     */         }
/*     */       });
/* 183 */       this.panel3.add(this.campoBuscar, null);
/* 184 */       this.panel3.add(this.botonBuscar, null);
/*     */     }
/* 186 */     return this.panel3;
/*     */   }
/*     */ 
/*     */   private void buscar(String texto) {
/* 190 */     String patron = texto.toUpperCase();
/* 191 */     int numFilas = this.modeloLista.getSize(); int x = 0; int y = 0;
/*     */ 
/* 193 */     for (x = this.lista.getSelectedIndex() + 1; x < numFilas; x++) {
/* 194 */       Object objeto = this.modeloLista.get(x);
/* 195 */       if (objeto.toString().toUpperCase().indexOf(patron) != -1) {
/* 196 */         this.lista.setSelectedValue(objeto, true);
/*     */ 
/* 198 */         break;
/*     */       }
/*     */     }
/*     */ 
/* 202 */     if (x == numFilas) {
/* 203 */       int volver = JOptionPane.showConfirmDialog(getParent(), Mensajes.getString("finTabla"), Mensajes.getString("confirma"), 0);
/*     */ 
/* 206 */       if (volver == 0) {
/* 207 */         this.lista.setSelectedIndex(0);
/* 208 */         if (this.modeloLista.get(0).toString().toUpperCase().indexOf(patron) != -1) {
/* 209 */           this.lista.setSelectedValue(this.modeloLista.get(0), true);
/* 210 */           return;
/*     */         }
/*     */ 
/* 213 */         buscar(texto);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public String Seleccion() {
/* 219 */     return this.seleccion;
/*     */   }
/*     */ 
/*     */   public void lista_mouseClicked(MouseEvent e) {
/* 223 */     if (e.getClickCount() == 2) {
/* 224 */       this.seleccion = this.lista.getSelectedValue().toString().substring(0, 8);
/* 225 */       dispose();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void lista_keyPressed(KeyEvent e) {
/* 230 */     int tecla = e.getKeyCode();
/* 231 */     if ((((tecla == 10 ? 1 : 0) | (tecla == 32 ? 1 : 0)) != 0) && 
/* 232 */       (!this.lista.isSelectionEmpty())) {
/* 233 */       this.seleccion = this.lista.getSelectedValue().toString().substring(0, 8);
/* 234 */       dispose();
/*     */     }
/*     */ 
/* 237 */     if (tecla == 27)
/* 238 */       dispose();
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.dialogosAuxiliares.MostrarCuentas
 * JD-Core Version:    0.6.2
 */