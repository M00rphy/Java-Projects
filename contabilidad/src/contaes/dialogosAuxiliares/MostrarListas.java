/*     */ package contaes.dialogosAuxiliares;
/*     */ 
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
/*     */ import java.util.ArrayList;
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
/*     */ public class MostrarListas extends JDialog
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
/*     */   ArrayList<String> listaAMostrar;
/*     */ 
/*     */   public MostrarListas(Frame owner, String title, boolean modal, ArrayList<String> listaAMostrar)
/*     */   {
/*  71 */     super(owner, title, modal);
/*  72 */     this.listaAMostrar = listaAMostrar;
/*     */     try {
/*  74 */       setDefaultCloseOperation(2);
/*  75 */       initialize();
/*  76 */       pack();
/*     */     }
/*     */     catch (Exception exception) {
/*  79 */       exception.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public MostrarListas(Dialog owner, String title, boolean modal, ArrayList<String> listaAMostrar) {
/*  84 */     super(owner, title, modal);
/*  85 */     this.listaAMostrar = listaAMostrar;
/*     */     try {
/*  87 */       setDefaultCloseOperation(2);
/*  88 */       initialize();
/*  89 */       pack();
/*     */     }
/*     */     catch (Exception exception) {
/*  92 */       exception.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void initialize() throws Exception {
/*  97 */     this.panel1.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(1), BorderFactory.createEmptyBorder(5, 5, 5, 5))));
/*     */ 
/* 100 */     for (String string : this.listaAMostrar) {
/* 101 */       this.modeloLista.addElement(string);
/*     */     }
/* 103 */     this.lista = new JList(this.modeloLista);
/* 104 */     this.lista.setSelectionMode(0);
/* 105 */     this.lista.addKeyListener(new mostrarListas_lista_keyAdapter(this));
/* 106 */     this.lista.addMouseListener(new mostrarListas_lista_mouseAdapter(this));
/*     */ 
/* 108 */     this.panel2 = new JScrollPane(this.lista);
/* 109 */     this.etiqueta.setText(Mensajes.getString("selecUnaCuenta"));
/* 110 */     this.panel1.setLayout(this.borderLayout1);
/* 111 */     this.panel1.add(this.etiqueta, "North");
/* 112 */     this.panel1.add(this.panel2, "Center");
/* 113 */     this.panel1.add(getPanelBuscar(), "South");
/* 114 */     getContentPane().add(this.panel1);
/* 115 */     this.lista.setSelectedIndex(0);
/*     */   }
/*     */ 
/*     */   private JPanel getPanelBuscar() {
/* 119 */     if (this.panel3 == null) {
/* 120 */       this.panel3 = new JPanel();
/*     */ 
/* 122 */       this.campoBuscar.setBounds(5, 5, 120, 19);
/* 123 */       this.campoBuscar.setPreferredSize(new Dimension(120, 19));
/* 124 */       this.campoBuscar.addActionListener(new ActionListener()
/*     */       {
/*     */         public void actionPerformed(ActionEvent e) {
/* 127 */           String texto = MostrarListas.this.campoBuscar.getText();
/* 128 */           if (!texto.equals(""))
/* 129 */             MostrarListas.this.buscar(texto);
/*     */         }
/*     */       });
/* 133 */       this.botonBuscar.setBounds(135, 5, 70, 23);
/* 134 */       this.botonBuscar.setHorizontalTextPosition(2);
/* 135 */       this.botonBuscar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/find.png")));
/* 136 */       this.botonBuscar.addActionListener(new ActionListener()
/*     */       {
/*     */         public void actionPerformed(ActionEvent e) {
/* 139 */           String texto = MostrarListas.this.campoBuscar.getText();
/* 140 */           if (!texto.equals(""))
/* 141 */             MostrarListas.this.buscar(texto);
/*     */         }
/*     */       });
/* 145 */       this.panel3.add(this.campoBuscar, null);
/* 146 */       this.panel3.add(this.botonBuscar, null);
/*     */     }
/* 148 */     return this.panel3;
/*     */   }
/*     */ 
/*     */   private void buscar(String texto) {
/* 152 */     String patron = texto.toUpperCase();
/* 153 */     int numFilas = this.modeloLista.getSize(); int x = 0; int y = 0;
/*     */ 
/* 155 */     for (x = this.lista.getSelectedIndex() + 1; x < numFilas; x++) {
/* 156 */       Object objeto = this.modeloLista.get(x);
/* 157 */       if (objeto.toString().toUpperCase().indexOf(patron) != -1) {
/* 158 */         this.lista.setSelectedValue(objeto, true);
/*     */ 
/* 160 */         break;
/*     */       }
/*     */     }
/*     */ 
/* 164 */     if (x == numFilas) {
/* 165 */       int volver = JOptionPane.showConfirmDialog(getParent(), Mensajes.getString("finTabla"), Mensajes.getString("confirma"), 0);
/*     */ 
/* 168 */       if (volver == 0) {
/* 169 */         this.lista.setSelectedIndex(0);
/* 170 */         if (this.modeloLista.get(0).toString().toUpperCase().indexOf(patron) != -1) {
/* 171 */           this.lista.setSelectedValue(this.modeloLista.get(0), true);
/* 172 */           return;
/*     */         }
/*     */ 
/* 175 */         buscar(texto);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public String Seleccion() {
/* 181 */     return this.seleccion;
/*     */   }
/*     */ 
/*     */   public void lista_mouseClicked(MouseEvent e) {
/* 185 */     if (e.getClickCount() == 2) {
/* 186 */       this.seleccion = this.lista.getSelectedValue().toString();
/* 187 */       dispose();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void lista_keyPressed(KeyEvent e) {
/* 192 */     int tecla = e.getKeyCode();
/* 193 */     if ((((tecla == 10 ? 1 : 0) | (tecla == 32 ? 1 : 0)) != 0) && 
/* 194 */       (!this.lista.isSelectionEmpty())) {
/* 195 */       this.seleccion = this.lista.getSelectedValue().toString();
/* 196 */       dispose();
/*     */     }
/*     */ 
/* 199 */     if (tecla == 27)
/* 200 */       dispose();
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.dialogosAuxiliares.MostrarListas
 * JD-Core Version:    0.6.2
 */