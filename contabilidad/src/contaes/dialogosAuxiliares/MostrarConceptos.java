/*     */ package contaes.dialogosAuxiliares;
/*     */ 
/*     */ import contaes.manejoDatos.ManejoConceptos;
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dialog;
/*     */ import java.awt.Frame;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.util.LinkedList;
import java.util.List;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.DefaultListModel;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JList;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ 
/*     */ public class MostrarConceptos extends JDialog
/*     */ {
/*  51 */   JPanel panel1 = new JPanel();
/*     */   JScrollPane panel2;
/*  53 */   JLabel etiqueta = new JLabel();
/*  54 */   BorderLayout borderLayout1 = new BorderLayout(5, 5);
/*     */   JList lista;
/*  56 */   DefaultListModel modeloLista = new DefaultListModel();
/*  57 */   String seleccion = "";
/*     */ 
/*     */   public MostrarConceptos(Frame owner, String title, boolean modal) {
/*  60 */     super(owner, title, modal);
/*     */     try {
/*  62 */       setDefaultCloseOperation(2);
/*  63 */       initialize();
/*  64 */       pack();
/*     */     }
/*     */     catch (Exception exception) {
/*  67 */       exception.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public MostrarConceptos(Dialog owner, String title, boolean modal) {
/*  72 */     super(owner, title, modal);
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
/*     */   public MostrarConceptos() {
/*  84 */     this(new Frame(), Mensajes.getString("conceptos"), true);
/*     */   }
/*     */ 
/*     */   private void initialize() throws Exception {
/*  88 */     LinkedList listaConceptos = ManejoConceptos.listaDescripcion();
/*  89 */     for (String x :(List<String>) listaConceptos)
/*  90 */       this.modeloLista.addElement(x);
/*  91 */     this.panel1.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(1), BorderFactory.createEmptyBorder(3, 3, 3, 3))));
/*     */ 
/*  94 */     this.lista = new JList(this.modeloLista);
/*  95 */     this.lista.setSelectionMode(0);
/*  96 */     this.lista.addKeyListener(new mostrarConceptos_lista_keyAdapter(this));
/*  97 */     this.lista.addMouseListener(new mostrarConceptos_lista_mouseAdapter(this));
/*     */ 
/*  99 */     this.panel2 = new JScrollPane(this.lista);
/* 100 */     this.etiqueta.setText(Mensajes.getString("selecUnConcepto"));
/* 101 */     this.panel1.setLayout(this.borderLayout1);
/* 102 */     this.panel1.add(this.etiqueta, "North");
/* 103 */     this.panel1.add(this.panel2, "Center");
/* 104 */     getContentPane().add(this.panel1);
/*     */   }
/*     */ 
/*     */   public String Seleccion() {
/* 108 */     return this.seleccion;
/*     */   }
/*     */ 
/*     */   public void lista_mouseClicked(MouseEvent e) {
/* 112 */     this.seleccion = this.lista.getSelectedValue().toString();
/* 113 */     dispose();
/*     */   }
/*     */ 
/*     */   public void lista_keyPressed(KeyEvent e) {
/* 117 */     int tecla = e.getKeyCode();
/* 118 */     if ((((tecla == 10 ? 1 : 0) | (tecla == 32 ? 1 : 0)) != 0) && 
/* 119 */       (!this.lista.isSelectionEmpty())) {
/* 120 */       this.seleccion = this.lista.getSelectedValue().toString();
/* 121 */       dispose();
/*     */     }
/*     */ 
/* 124 */     if (tecla == 27)
/* 125 */       dispose();
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.dialogosAuxiliares.MostrarConceptos
 * JD-Core Version:    0.6.2
 */