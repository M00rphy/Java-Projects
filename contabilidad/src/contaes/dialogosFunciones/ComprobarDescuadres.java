/*     */ package contaes.dialogosFunciones;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.Puente;
/*     */ import contaes.manejoDatos.funciones.BuscarDescuabres;
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Container;
/*     */ import java.awt.Cursor;
/*     */ import java.awt.Frame;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.PrintStream;
/*     */ import java.net.URL;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTextArea;
/*     */ 
/*     */ public class ComprobarDescuadres extends JDialog
/*     */   implements ActionListener
/*     */ {
/*     */   private static final String ACEPTAR = "aceptar";
/*     */   private static final String CANCELAR = "cancelar";
/*     */   private JButton aceptar;
/*  50 */   private JTextArea texto = new JTextArea(4, 20);
/*  51 */   private JScrollPane panel2 = new JScrollPane(this.texto);
/*  52 */   ImageIcon iconoSelec = createImageIcon("/contaes/iconos/ok.png");
/*  53 */   JPanel panel1 = new JPanel();
/*  54 */   BorderLayout borderLayout1 = new BorderLayout(5, 15);
/*     */ 
/*     */   public ComprobarDescuadres(Frame owner, String title, boolean modal) {
/*  57 */     super(owner, title, modal);
/*     */     try {
/*  59 */       setDefaultCloseOperation(2);
/*  60 */       initialize();
/*  61 */       pack();
/*     */     } catch (Exception exception) {
/*  63 */       exception.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public ComprobarDescuadres() {
/*  68 */     this(new Frame(), Mensajes.getString("descuadres"), true);
/*     */   }
/*     */ 
/*     */   private void initialize() throws Exception {
/*  72 */     this.texto.setEditable(false);
/*  73 */     this.aceptar = new JButton();
/*  74 */     this.aceptar.setText(Mensajes.getString("buscar") + " " + Mensajes.getString("descuadres"));
/*  75 */     this.aceptar.setIcon(this.iconoSelec);
/*  76 */     this.aceptar.setVerticalTextPosition(0);
/*  77 */     this.aceptar.setHorizontalTextPosition(2);
/*  78 */     this.aceptar.setActionCommand("aceptar");
/*  79 */     this.aceptar.addActionListener(this);
/*  80 */     this.panel1.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(1), BorderFactory.createEmptyBorder(5, 5, 5, 5))));
/*     */ 
/*  83 */     this.panel1.setLayout(this.borderLayout1);
/*  84 */     this.panel1.add(this.panel2, "Center");
/*  85 */     this.panel1.add(this.aceptar, "Last");
/*  86 */     getContentPane().add(this.panel1);
/*     */   }
/*     */ 
/*     */   public void actionPerformed(ActionEvent e) {
/*  90 */     String cmd = e.getActionCommand();
/*     */ 
/*  92 */     if ("aceptar".equals(cmd)) {
/*  93 */       setCursor(Cursor.getPredefinedCursor(3));
/*  94 */       String listaDescuadres = BuscarDescuabres.Comprobar(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*     */ 
/*  96 */       this.texto.setText(Mensajes.getString("buscando") + " ...");
/*  97 */       setCursor(Cursor.getPredefinedCursor(0));
/*  98 */       if (!listaDescuadres.equals(""))
/*  99 */         this.texto.setText(listaDescuadres);
/*     */       else {
/* 101 */         this.texto.setText(Mensajes.getString("noDescuadres"));
/*     */       }
/* 103 */       this.aceptar.setText(Mensajes.getString("aceptar"));
/* 104 */       this.aceptar.setActionCommand("cancelar");
/* 105 */       this.panel2.revalidate();
/* 106 */     } else if ("cancelar".equals(cmd)) {
/* 107 */       dispose();
/*     */     }
/*     */   }
/*     */ 
/*     */   protected static ImageIcon createImageIcon(String path) {
/* 112 */     URL imgURL = ComprobarDescuadres.class.getResource(path);
/* 113 */     if (imgURL != null) {
/* 114 */       return new ImageIcon(imgURL);
/*     */     }
/* 116 */     System.err.println("Couldn't find file: " + path);
/* 117 */     return null;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.dialogosFunciones.ComprobarDescuadres
 * JD-Core Version:    0.6.2
 */