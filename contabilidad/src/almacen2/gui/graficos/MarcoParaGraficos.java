/*     */ package almacen2.gui.graficos;
/*     */ 
/*     */ import contaes.manejoDatos.auxiliar.AddExtension;
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.HeadlessException;
/*     */ import java.awt.Image;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.ItemEvent;
/*     */ import java.awt.event.ItemListener;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import javax.imageio.ImageIO;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JFileChooser;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ public class MarcoParaGraficos extends JFrame
/*     */ {
/*  56 */   private JPanel jContentPane = null;
/*  57 */   private JPanel panelGrafico = null;
/*  58 */   private JPanel panelFunciones = null;
/*  59 */   private JButton bGuardar = null;
/*  60 */   private JCheckBox jCheckBox = null;
/*  61 */   private Graficos grafico = null;
/*     */ 
/*  63 */   private String titulo = null;
/*  64 */   private int numLeyendas = -1;
/*  65 */   private String[] leyendas = null;
/*  66 */   private ArrayList<String> xTitle = null;
/*  67 */   private ArrayList<ArrayList<Integer>> datos = null;
/*     */ 
/*     */   public MarcoParaGraficos(String titulo, int numLeyendas, String[] leyendas, ArrayList<String> xTitle, ArrayList<ArrayList<Integer>> datos)
/*     */     throws HeadlessException
/*     */   {
/*  73 */     this.titulo = titulo;
/*  74 */     this.numLeyendas = numLeyendas;
/*  75 */     this.leyendas = leyendas;
/*  76 */     this.xTitle = xTitle;
/*  77 */     this.datos = datos;
/*  78 */     initialize();
/*     */   }
/*     */ 
/*     */   private void initialize()
/*     */   {
/*  87 */     setSize(540, 360);
/*  88 */     setContentPane(getJContentPane());
/*  89 */     setTitle(Mensajes.getString("grafico"));
/*     */   }
/*     */ 
/*     */   private JPanel getJContentPane()
/*     */   {
/*  98 */     if (this.jContentPane == null) {
/*  99 */       this.jContentPane = new JPanel();
/* 100 */       this.jContentPane.setLayout(new BorderLayout());
/* 101 */       this.jContentPane.add(getPanelGrafico(), "Center");
/* 102 */       this.jContentPane.add(getPanelFunciones(), "South");
/*     */     }
/* 104 */     return this.jContentPane;
/*     */   }
/*     */ 
/*     */   private JPanel getPanelGrafico()
/*     */   {
/* 113 */     if (this.panelGrafico == null) {
/* 114 */       this.panelGrafico = new JPanel();
/* 115 */       this.panelGrafico.setLayout(new BorderLayout());
/* 116 */       this.panelGrafico.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createBevelBorder(1)));
/* 117 */       this.panelGrafico.add(getGrafico(), "Center");
/*     */     }
/* 119 */     return this.panelGrafico;
/*     */   }
/*     */ 
/*     */   private JPanel getPanelFunciones()
/*     */   {
/* 128 */     if (this.panelFunciones == null) {
/* 129 */       GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
/* 130 */       gridBagConstraints1.insets = new Insets(5, 10, 5, 10);
/* 131 */       gridBagConstraints1.gridy = 0;
/* 132 */       gridBagConstraints1.ipadx = 0;
/* 133 */       gridBagConstraints1.gridx = 1;
/* 134 */       GridBagConstraints gridBagConstraints = new GridBagConstraints();
/* 135 */       gridBagConstraints.insets = new Insets(5, 10, 5, 10);
/* 136 */       gridBagConstraints.gridy = 0;
/* 137 */       gridBagConstraints.ipadx = 0;
/* 138 */       gridBagConstraints.gridx = 0;
/* 139 */       this.panelFunciones = new JPanel();
/* 140 */       this.panelFunciones.setLayout(new GridBagLayout());
/* 141 */       this.panelFunciones.add(getBGuardar(), gridBagConstraints);
/* 142 */       this.panelFunciones.add(getJCheckBox(), gridBagConstraints1);
/*     */     }
/* 144 */     return this.panelFunciones;
/*     */   }
/*     */ 
/*     */   private JButton getBGuardar()
/*     */   {
/* 153 */     if (this.bGuardar == null) {
/* 154 */       this.bGuardar = new JButton();
/* 155 */       this.bGuardar.setHorizontalTextPosition(2);
/* 156 */       this.bGuardar.setToolTipText(Mensajes.getString("guardarGraficott"));
/* 157 */       this.bGuardar.setIcon(new ImageIcon(getClass().getResource("/almacen2/iconos/save.png")));
/* 158 */       this.bGuardar.addActionListener(new ActionListener() {
/*     */         public void actionPerformed(ActionEvent e) {
/* 160 */           MarcoParaGraficos.this.guardarGrafico();
/*     */         }
/*     */       });
/*     */     }
/* 164 */     return this.bGuardar;
/*     */   }
/*     */ 
/*     */   private JCheckBox getJCheckBox()
/*     */   {
/* 173 */     if (this.jCheckBox == null) {
/* 174 */       this.jCheckBox = new JCheckBox();
/* 175 */       this.jCheckBox.setSelected(true);
/* 176 */       this.jCheckBox.setText(Mensajes.getString("barraSolida"));
/* 177 */       this.jCheckBox.addItemListener(new ItemListener() {
/*     */         public void itemStateChanged(ItemEvent e) {
/* 179 */           if (e.getStateChange() == 2) {
/* 180 */             MarcoParaGraficos.this.grafico.setSolido(false);
/* 181 */             MarcoParaGraficos.this.grafico.repaint();
/*     */           }
/*     */           else {
/* 184 */             MarcoParaGraficos.this.grafico.setSolido(true);
/* 185 */             MarcoParaGraficos.this.grafico.repaint();
/*     */           }
/*     */         }
/*     */       });
/*     */     }
/* 190 */     return this.jCheckBox;
/*     */   }
/*     */ 
/*     */   private JPanel getGrafico()
/*     */   {
/* 199 */     if (this.grafico == null) {
/* 200 */       this.grafico = new Graficos();
/* 201 */       this.grafico.setTitulo(this.titulo);
/* 202 */       this.grafico.setLeyendas(this.numLeyendas, this.leyendas);
/* 203 */       this.grafico.setXTitles(this.xTitle);
/* 204 */       this.grafico.setDatos(this.datos);
/*     */     }
/* 206 */     return this.grafico;
/*     */   }
/*     */ 
/*     */   private void guardarGrafico()
/*     */   {
/*     */     try
/*     */     {
/* 215 */       JFileChooser fc = new JFileChooser();
/* 216 */       fc.setSelectedFile(new File("grafico.png"));
/* 217 */       int retorno = fc.showSaveDialog(this);
/* 218 */       if (retorno == 0) {
/* 219 */         File archivo = fc.getSelectedFile();
/* 220 */         archivo = AddExtension.png(archivo);
/* 221 */         Rectangle r = this.grafico.getBounds();
/* 222 */         Image image = this.grafico.createImage(r.width, r.height);
/* 223 */         Graphics g = image.getGraphics();
/* 224 */         this.grafico.paint(g);
/* 225 */         ImageIO.write((RenderedImage)image, "png", archivo);
/*     */       }
/*     */     }
/*     */     catch (IOException ioe) {
/* 229 */       ioe.printStackTrace();
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.gui.graficos.MarcoParaGraficos
 * JD-Core Version:    0.6.2
 */