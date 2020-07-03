/*     */ package contaes.cuentas;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.Puente;
/*     */ import contaes.manejoDatos.auxiliar.AcumuladosMensuales;
/*     */ import contaes.manejoDatos.auxiliar.AddExtension;
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Cursor;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Image;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
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
/*     */ import javax.swing.JFileChooser;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.border.CompoundBorder;
/*     */ 
/*     */ public class GraficoDatosMensuales extends JPanel
/*     */   implements Printable
/*     */ {
/*  59 */   private JPanel jPanel = null;
/*  60 */   private Graficos grafico = null;
/*     */ 
/*  62 */   private JButton bImprimir = null;
/*  63 */   private JButton bGuardar = null;
/*  64 */   private String cuenta = "";
/*  65 */   private String nombreCuenta = "";
/*  66 */   private AcumuladosMensuales datos = null;
/*     */ 
/*     */   public GraficoDatosMensuales()
/*     */   {
/*  73 */     initialize();
/*     */   }
/*     */ 
/*     */   private void initialize()
/*     */   {
/*  82 */     setLayout(new BorderLayout());
/*  83 */     setSize(470, 360);
/*  84 */     add(getGrafico(), "Center");
/*  85 */     add(getJPanel(), "South");
/*     */   }
/*     */ 
/*     */   private Graficos getGrafico()
/*     */   {
/*  94 */     if (this.grafico == null) {
/*  95 */       this.grafico = new Graficos();
/*     */ 
/*  97 */       this.grafico.setBorder(BorderFactory.createEtchedBorder(1));
/*  98 */       this.grafico.setTitulo("");
/*  99 */       this.grafico.setLeyendas(2, new String[] { Mensajes.getString("debe"), Mensajes.getString("haber") });
/* 100 */       this.grafico.setDatos(new double[][] { { 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D }, { 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D } });
/*     */     }
/*     */ 
/* 105 */     return this.grafico;
/*     */   }
/*     */ 
/*     */   private JPanel getJPanel()
/*     */   {
/* 114 */     if (this.jPanel == null) {
/* 115 */       GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
/* 116 */       gridBagConstraints1.gridx = 1;
/* 117 */       gridBagConstraints1.gridwidth = 1;
/* 118 */       gridBagConstraints1.insets = new Insets(10, 30, 10, 0);
/* 119 */       gridBagConstraints1.gridy = 0;
/* 120 */       GridBagConstraints gridBagConstraints = new GridBagConstraints();
/* 121 */       gridBagConstraints.gridx = 0;
/* 122 */       gridBagConstraints.gridwidth = 1;
/* 123 */       gridBagConstraints.insets = new Insets(10, 0, 10, 30);
/* 124 */       gridBagConstraints.gridy = 0;
/* 125 */       this.jPanel = new JPanel();
/* 126 */       this.jPanel.setLayout(new GridBagLayout());
/* 127 */       this.jPanel.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(15, 70, 20, 70), BorderFactory.createEtchedBorder(1)));
/*     */ 
/* 131 */       this.jPanel.add(getBImprimir(), gridBagConstraints);
/* 132 */       this.jPanel.add(getBGuardar(), gridBagConstraints1);
/*     */     }
/* 134 */     return this.jPanel;
/*     */   }
/*     */ 
/*     */   public void colocarDatos(String cta, String name)
/*     */   {
/* 146 */     if (this.cuenta.equals(cta)) return;
/* 147 */     this.cuenta = cta;
/* 148 */     this.nombreCuenta = name;
/* 149 */     setCursor(Cursor.getPredefinedCursor(3));
/* 150 */     this.datos = new AcumuladosMensuales(Inicio.getCEmpresa(), this.cuenta, Inicio.p.getEjercicio());
/*     */ 
/* 152 */     double[] debe = this.datos.getD();
/* 153 */     double[] haber = this.datos.getH();
/* 154 */     setCursor(Cursor.getPredefinedCursor(0));
/* 155 */     this.grafico.setTitulo(this.cuenta + " : " + this.nombreCuenta);
/* 156 */     this.grafico.setDatos(new double[][] { { debe[0], debe[1], debe[2], debe[3], debe[4], debe[5], debe[6], debe[7], debe[8], debe[9], debe[10], debe[11], debe[12], debe[13] }, { haber[0], haber[1], haber[2], haber[3], haber[4], haber[5], haber[6], haber[7], haber[8], haber[9], haber[10], haber[11], haber[12], haber[13] } });
/*     */ 
/* 162 */     this.grafico.repaint();
/*     */   }
/*     */ 
/*     */   private JButton getBImprimir()
/*     */   {
/* 171 */     if (this.bImprimir == null) {
/* 172 */       this.bImprimir = new JButton();
/* 173 */       this.bImprimir.setText(Mensajes.getString("print"));
/* 174 */       this.bImprimir.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/print.png")));
/* 175 */       this.bImprimir.setHorizontalTextPosition(2);
/* 176 */       this.bImprimir.addActionListener(new ActionListener() {
/*     */         public void actionPerformed(ActionEvent e) {
/* 178 */           GraficoDatosMensuales.this.imprimir();
/*     */         }
/*     */       });
/*     */     }
/* 182 */     return this.bImprimir;
/*     */   }
/*     */ 
/*     */   private JButton getBGuardar()
/*     */   {
/* 191 */     if (this.bGuardar == null) {
/* 192 */       this.bGuardar = new JButton();
/* 193 */       this.bGuardar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/save16.png")));
/* 194 */       this.bGuardar.setText(Mensajes.getString("saveAs"));
/* 195 */       this.bGuardar.setToolTipText(Mensajes.getString("guardarGraficott"));
/* 196 */       this.bGuardar.setHorizontalTextPosition(2);
/* 197 */       this.bGuardar.addActionListener(new ActionListener() {
/*     */         public void actionPerformed(ActionEvent e) {
/* 199 */           GraficoDatosMensuales.this.guardarEnArchivo();
/*     */         }
/*     */       });
/*     */     }
/* 203 */     return this.bGuardar;
/*     */   }
/*     */ 
/*     */   private void guardarEnArchivo()
/*     */   {
/*     */     try
/*     */     {
/* 212 */       JFileChooser fc = new JFileChooser();
/* 213 */       fc.setSelectedFile(new File("grafico.png"));
/* 214 */       int retorno = fc.showSaveDialog(this);
/* 215 */       if (retorno == 0) {
/* 216 */         File archivo = fc.getSelectedFile();
/* 217 */         archivo = AddExtension.png(archivo);
/* 218 */         Rectangle r = this.grafico.getBounds();
/* 219 */         Image image = this.grafico.createImage(r.width, r.height);
/* 220 */         Graphics g = image.getGraphics();
/* 221 */         this.grafico.paint(g);
/* 222 */         ImageIO.write((RenderedImage)image, "png", archivo);
/*     */       }
/*     */     }
/*     */     catch (IOException ioe) {
/* 226 */       ioe.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void imprimir()
/*     */   {
/* 235 */     this.grafico.setFondoGradiente(false);
/* 236 */     PrinterJob printerJob = PrinterJob.getPrinterJob();
/* 237 */     PageFormat userFormat = printerJob.pageDialog(printerJob.defaultPage());
/* 238 */     printerJob.setPrintable(this, userFormat);
/* 239 */     boolean doPrint = printerJob.printDialog();
/* 240 */     if (doPrint) {
/*     */       try {
/* 242 */         printerJob.print();
/*     */       } catch (PrinterException exception) {
/* 244 */         System.err.println(Mensajes.getString("errPrint") + ": " + exception);
/*     */       }
/*     */     }
/* 247 */     this.grafico.setFondoGradiente(true);
/* 248 */     System.gc();
/*     */   }
/*     */ 
/*     */   public int print(Graphics pGraphics, PageFormat pPageFormat, int pPageIndex)
/*     */     throws PrinterException
/*     */   {
/* 268 */     if (pPageIndex >= 1) {
/* 269 */       return 1;
/*     */     }
/* 271 */     pGraphics.translate((int)pPageFormat.getImageableX(), (int)pPageFormat.getImageableY());
/*     */ 
/* 273 */     int wPage = (int)pPageFormat.getImageableWidth();
/* 274 */     int hPage = (int)pPageFormat.getImageableHeight();
/* 275 */     pGraphics.setClip(0, 0, wPage, hPage);
/*     */ 
/* 277 */     this.grafico.paint(pGraphics);
/* 278 */     return 0;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.cuentas.GraficoDatosMensuales
 * JD-Core Version:    0.6.2
 */