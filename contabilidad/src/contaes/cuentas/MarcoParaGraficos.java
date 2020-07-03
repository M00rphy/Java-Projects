/*     */ package contaes.cuentas;
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
/*     */ import java.awt.print.PageFormat;
/*     */ import java.awt.print.Printable;
/*     */ import java.awt.print.PrinterException;
/*     */ import java.awt.print.PrinterJob;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
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
/*     */   implements Printable
/*     */ {
/*  59 */   private JPanel jContentPane = null;
/*  60 */   private JPanel panelGrafico = null;
/*  61 */   private JPanel panelFunciones = null;
/*  62 */   private JButton bGuardar = null;
/*  63 */   private JButton bImprimir = null;
/*  64 */   private JCheckBox jCheckBox = null;
/*  65 */   private Graficos grafico = null;
/*     */ 
/*  67 */   private String titulo = null;
/*  68 */   private int numLeyendas = -1;
/*  69 */   private String[] leyendas = null;
/*  70 */   private double[][] datos = (double[][])null;
/*     */ 
/*     */   public MarcoParaGraficos(String titulo, int numLeyendas, String[] leyendas, double[][] datos)
/*     */     throws HeadlessException
/*     */   {
/*  76 */     this.titulo = titulo;
/*  77 */     this.numLeyendas = numLeyendas;
/*  78 */     this.leyendas = leyendas;
/*  79 */     this.datos = datos;
/*  80 */     initialize();
/*     */   }
/*     */ 
/*     */   private void initialize()
/*     */   {
/*  89 */     setSize(540, 360);
/*  90 */     setContentPane(getJContentPane());
/*  91 */     setTitle(Mensajes.getString("comparativaGrafica"));
/*     */   }
/*     */ 
/*     */   private JPanel getJContentPane()
/*     */   {
/* 100 */     if (this.jContentPane == null) {
/* 101 */       this.jContentPane = new JPanel();
/* 102 */       this.jContentPane.setLayout(new BorderLayout());
/* 103 */       this.jContentPane.add(getPanelGrafico(), "Center");
/* 104 */       this.jContentPane.add(getPanelFunciones(), "South");
/*     */     }
/* 106 */     return this.jContentPane;
/*     */   }
/*     */ 
/*     */   private JPanel getPanelGrafico()
/*     */   {
/* 115 */     if (this.panelGrafico == null) {
/* 116 */       this.panelGrafico = new JPanel();
/* 117 */       this.panelGrafico.setLayout(new BorderLayout());
/* 118 */       this.panelGrafico.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createBevelBorder(1)));
/* 119 */       this.panelGrafico.add(getGrafico(), "Center");
/*     */     }
/* 121 */     return this.panelGrafico;
/*     */   }
/*     */ 
/*     */   private JPanel getPanelFunciones()
/*     */   {
/* 130 */     if (this.panelFunciones == null) {
/* 131 */       GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
/* 132 */       gridBagConstraints2.gridx = 2;
/* 133 */       gridBagConstraints2.gridy = 0;
/* 134 */       GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
/* 135 */       gridBagConstraints1.insets = new Insets(5, 10, 5, 10);
/* 136 */       gridBagConstraints1.gridy = 0;
/* 137 */       gridBagConstraints1.ipadx = 0;
/* 138 */       gridBagConstraints1.gridx = 1;
/* 139 */       GridBagConstraints gridBagConstraints = new GridBagConstraints();
/* 140 */       gridBagConstraints.insets = new Insets(5, 10, 5, 10);
/* 141 */       gridBagConstraints.gridy = 0;
/* 142 */       gridBagConstraints.ipadx = 0;
/* 143 */       gridBagConstraints.gridx = 0;
/* 144 */       this.panelFunciones = new JPanel();
/* 145 */       this.panelFunciones.setLayout(new GridBagLayout());
/* 146 */       this.panelFunciones.add(getBGuardar(), gridBagConstraints);
/* 147 */       this.panelFunciones.add(getBImprimir(), gridBagConstraints1);
/* 148 */       this.panelFunciones.add(getJCheckBox(), gridBagConstraints2);
/*     */     }
/* 150 */     return this.panelFunciones;
/*     */   }
/*     */ 
/*     */   private JButton getBGuardar()
/*     */   {
/* 159 */     if (this.bGuardar == null) {
/* 160 */       this.bGuardar = new JButton();
/* 161 */       this.bGuardar.setHorizontalTextPosition(2);
/* 162 */       this.bGuardar.setText(Mensajes.getString("saveAs"));
/* 163 */       this.bGuardar.setToolTipText(Mensajes.getString("guardarGraficott"));
/* 164 */       this.bGuardar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/save16.png")));
/* 165 */       this.bGuardar.addActionListener(new ActionListener() {
/*     */         public void actionPerformed(ActionEvent e) {
/* 167 */           MarcoParaGraficos.this.guardarGrafico();
/*     */         }
/*     */       });
/*     */     }
/* 171 */     return this.bGuardar;
/*     */   }
/*     */ 
/*     */   private JButton getBImprimir()
/*     */   {
/* 180 */     if (this.bImprimir == null) {
/* 181 */       this.bImprimir = new JButton();
/* 182 */       this.bImprimir.setHorizontalTextPosition(2);
/* 183 */       this.bImprimir.setText(Mensajes.getString("print"));
/* 184 */       this.bImprimir.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/print.png")));
/* 185 */       this.bImprimir.addActionListener(new ActionListener() {
/*     */         public void actionPerformed(ActionEvent e) {
/* 187 */           MarcoParaGraficos.this.imprimir();
/*     */         }
/*     */       });
/*     */     }
/* 191 */     return this.bImprimir;
/*     */   }
/*     */ 
/*     */   private JCheckBox getJCheckBox()
/*     */   {
/* 200 */     if (this.jCheckBox == null) {
/* 201 */       this.jCheckBox = new JCheckBox();
/* 202 */       this.jCheckBox.setSelected(true);
/* 203 */       this.jCheckBox.setText(Mensajes.getString("barraSolida"));
/* 204 */       this.jCheckBox.addItemListener(new ItemListener() {
/*     */         public void itemStateChanged(ItemEvent e) {
/* 206 */           if (e.getStateChange() == 2) {
/* 207 */             MarcoParaGraficos.this.grafico.setSolido(false);
/* 208 */             MarcoParaGraficos.this.grafico.repaint();
/*     */           }
/*     */           else {
/* 211 */             MarcoParaGraficos.this.grafico.setSolido(true);
/* 212 */             MarcoParaGraficos.this.grafico.repaint();
/*     */           }
/*     */         }
/*     */       });
/*     */     }
/* 217 */     return this.jCheckBox;
/*     */   }
/*     */ 
/*     */   private JPanel getGrafico()
/*     */   {
/* 226 */     if (this.grafico == null) {
/* 227 */       this.grafico = new Graficos();
/* 228 */       this.grafico.setTitulo(this.titulo);
/* 229 */       this.grafico.setLeyendas(this.numLeyendas, this.leyendas);
/* 230 */       this.grafico.setDatos(this.datos);
/*     */     }
/* 232 */     return this.grafico;
/*     */   }
/*     */ 
/*     */   private void guardarGrafico()
/*     */   {
/*     */     try
/*     */     {
/* 241 */       JFileChooser fc = new JFileChooser();
/* 242 */       fc.setSelectedFile(new File("grafico.png"));
/* 243 */       int retorno = fc.showSaveDialog(this);
/* 244 */       if (retorno == 0) {
/* 245 */         File archivo = fc.getSelectedFile();
/* 246 */         archivo = AddExtension.png(archivo);
/* 247 */         Rectangle r = this.grafico.getBounds();
/* 248 */         Image image = this.grafico.createImage(r.width, r.height);
/* 249 */         Graphics g = image.getGraphics();
/* 250 */         this.grafico.paint(g);
/* 251 */         ImageIO.write((RenderedImage)image, "png", archivo);
/*     */       }
/*     */     }
/*     */     catch (IOException ioe) {
/* 255 */       ioe.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void imprimir()
/*     */   {
/* 264 */     this.grafico.setFondoGradiente(false);
/* 265 */     PrinterJob printerJob = PrinterJob.getPrinterJob();
/* 266 */     PageFormat userFormat = printerJob.pageDialog(printerJob.defaultPage());
/* 267 */     printerJob.setPrintable(this, userFormat);
/* 268 */     boolean doPrint = printerJob.printDialog();
/* 269 */     if (doPrint) {
/*     */       try {
/* 271 */         printerJob.print();
/*     */       } catch (PrinterException exception) {
/* 273 */         System.err.println(Mensajes.getString("errPrint") + ": " + exception);
/*     */       }
/*     */     }
/* 276 */     this.grafico.setFondoGradiente(true);
/* 277 */     System.gc();
/*     */   }
/*     */ 
/*     */   public int print(Graphics pGraphics, PageFormat pPageFormat, int pPageIndex)
/*     */     throws PrinterException
/*     */   {
/* 297 */     if (pPageIndex >= 1) {
/* 298 */       return 1;
/*     */     }
/* 300 */     pGraphics.translate((int)pPageFormat.getImageableX(), (int)pPageFormat.getImageableY());
/*     */ 
/* 302 */     int wPage = (int)pPageFormat.getImageableWidth();
/* 303 */     int hPage = (int)pPageFormat.getImageableHeight();
/* 304 */     pGraphics.setClip(0, 0, wPage, hPage);
/*     */ 
/* 306 */     this.grafico.paint(pGraphics);
/* 307 */     return 0;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.cuentas.MarcoParaGraficos
 * JD-Core Version:    0.6.2
 */