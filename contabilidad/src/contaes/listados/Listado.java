/*     */ package contaes.listados;
/*     */ 
/*     */ import contaes.dialogosAuxiliares.MostrarFuentes;
/*     */ import contaes.dialogosAuxiliares.PrintPreview;
/*     */ import contaes.manejoDatos.auxiliar.AddExtension;
/*     */ import contaes.manejoDatos.auxiliar.FechaHoy;
/*     */ import contaes.manejoDatos.auxiliar.FinLinea;
/*     */ import contaes.manejoDatos.auxiliar.GrabarFichero;
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.font.FontRenderContext;
/*     */ import java.awt.font.LineBreakMeasurer;
/*     */ import java.awt.font.TextAttribute;
/*     */ import java.awt.font.TextLayout;
/*     */ import java.awt.print.PageFormat;
/*     */ import java.awt.print.Printable;
/*     */ import java.awt.print.PrinterException;
/*     */ import java.awt.print.PrinterJob;
/*     */ import java.io.File;
/*     */ import java.io.PrintStream;
/*     */ import java.net.URL;
/*     */ import java.text.AttributedCharacterIterator;
/*     */ import java.text.AttributedString;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JFileChooser;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTextArea;
/*     */ import javax.swing.JToolBar;
/*     */ import javax.swing.border.CompoundBorder;
/*     */ 
/*     */ public class Listado extends JFrame
/*     */   implements ActionListener, Printable
/*     */ {
/*     */   private static final String IMPRIMIR = "imprimir";
/*     */   private static final String VISTAPREVIA = "vistaprevia";
/*     */   private static final String FUENTES = "fuentes";
/*     */   private static final String GUARDAR = "guardar";
/*  77 */   protected int m_maxNumPage = 1;
/*  78 */   BorderLayout borderLayout1 = new BorderLayout();
/*  79 */   JToolBar barra = new JToolBar();
/*     */   JScrollPane scrollPane;
/*  81 */   JTextArea areaListado = new JTextArea();
/*  82 */   Font fuenteTabla = new Font("Monospaced", 0, 12);
/*     */   FechaHoy hoy;
/*     */   String textoListado;
/*     */   String titulo;
/*  86 */   List<String> matrizListado = new ArrayList();
/*     */ 
/*     */   public Listado(String title, List<String> texto)
/*     */   {
/*  95 */     this.matrizListado = texto;
/*  96 */     this.titulo = title;
/*     */     try {
/*  98 */       initialize();
/*     */     } catch (Exception exception) {
/* 100 */       exception.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void initialize()
/*     */     throws Exception
/*     */   {
/* 110 */     this.hoy = new FechaHoy();
/* 111 */     int inset = 80;
/* 112 */     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/* 113 */     int ancho = screenSize.width < 910 ? screenSize.width : 910;
/* 114 */     int alto = screenSize.height < 620 ? screenSize.height : 620;
/* 115 */     setBounds(inset, inset, ancho, alto);
/* 116 */     this.titulo = (this.titulo + "      " + this.hoy.getFecha());
/* 117 */     setTitle(this.titulo);
/* 118 */     this.areaListado.setFont(this.fuenteTabla);
/* 119 */     this.areaListado.append(this.titulo + "\n\n");
/* 120 */     for (String linea : this.matrizListado) {
/* 121 */       this.areaListado.append(linea);
/*     */     }
/*     */ 
/* 128 */     this.scrollPane = new JScrollPane(this.areaListado);
/* 129 */     this.scrollPane.setBorder(new CompoundBorder(BorderFactory.createLoweredBevelBorder(), BorderFactory.createMatteBorder(20, 20, 20, 20, Color.white)));
/*     */ 
/* 131 */     addBotones(this.barra);
/* 132 */     this.barra.setRollover(true);
/*     */ 
/* 134 */     getContentPane().setLayout(this.borderLayout1);
/* 135 */     getContentPane().add(this.scrollPane, "Center");
/* 136 */     getContentPane().add(this.barra, "First");
/*     */   }
/*     */ 
/*     */   public void actionPerformed(ActionEvent e)
/*     */   {
/* 143 */     String cmd = e.getActionCommand();
/* 144 */     if ("imprimir".equals(cmd)) {
/* 145 */       imprimirTexto();
/* 146 */     } else if ("vistaprevia".equals(cmd)) {
/* 147 */       new PrintPreview(this);
/* 148 */     } else if ("fuentes".equals(cmd)) {
/* 149 */       String fuentePrevia = this.areaListado.getFont().getFamily();
/* 150 */       int tamanoPrevio = this.areaListado.getFont().getSize();
/* 151 */       MostrarFuentes dlg = new MostrarFuentes(this, Mensajes.getString("fuentes"), true, fuentePrevia, tamanoPrevio);
/*     */ 
/* 153 */       Dimension dlgSize = dlg.getPreferredSize();
/* 154 */       Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/* 155 */       dlg.setLocation((screenSize.width - dlgSize.width) / 2, (screenSize.height - dlgSize.height) / 2);
/*     */ 
/* 157 */       dlg.setVisible(true);
/* 158 */       if (dlg.isCambiar()) {
/* 159 */         this.fuenteTabla = new Font(dlg.getFuente(), 0, dlg.getTamano());
/*     */       }
/* 161 */       this.areaListado.setFont(this.fuenteTabla);
/* 162 */     } else if ("guardar".equals(cmd)) {
/* 163 */       guardarEnArchivo();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void guardarEnArchivo()
/*     */   {
/* 172 */     String EOL = FinLinea.get();
/* 173 */     JFileChooser fc = new JFileChooser();
/* 174 */     fc.setSelectedFile(new File(this.titulo + ".txt"));
/* 175 */     int retorno = fc.showSaveDialog(this);
/* 176 */     if (retorno == 0) {
/* 177 */       File archivo = fc.getSelectedFile();
/* 178 */       archivo = AddExtension.txt(archivo);
/* 179 */       GrabarFichero salida = new GrabarFichero(archivo);
/* 180 */       salida.insertar(this.titulo + EOL + EOL);
/* 181 */       for (String linea : this.matrizListado) {
/* 182 */         salida.insertar(linea);
/*     */       }
/*     */ 
/* 188 */       salida.cerrar();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void imprimirTexto()
/*     */   {
/* 197 */     PrinterJob printerJob = PrinterJob.getPrinterJob();
/* 198 */     PageFormat userFormat = printerJob.pageDialog(printerJob.defaultPage());
/* 199 */     printerJob.setPrintable(this, userFormat);
/* 200 */     boolean doPrint = printerJob.printDialog();
/* 201 */     if (doPrint)
/*     */       try {
/* 203 */         printerJob.print();
/*     */       } catch (PrinterException exception) {
/* 205 */         System.err.println(Mensajes.getString("errPrint") + ": " + exception);
/*     */       }
/*     */   }
/*     */ 
/*     */   public int print(Graphics g, PageFormat pageFormat, int pageIndex)
/*     */     throws PrinterException
/*     */   {
/* 212 */     if (pageIndex >= this.m_maxNumPage) {
/* 213 */       return 1;
/*     */     }
/*     */ 
/* 216 */     ArrayList matrizImpresion = new ArrayList();
/* 217 */     String EOL = FinLinea.get();
/* 218 */     for (String cadena : this.matrizListado) {
/* 219 */       int ind = cadena.indexOf(EOL);
/* 220 */       while (ind != -1) {
/* 221 */         String temp = cadena.substring(0, ind);
/* 222 */         matrizImpresion.add(temp);
/* 223 */         cadena = cadena.substring(ind + 1);
/* 224 */         ind = cadena.indexOf(EOL);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 229 */     Graphics2D g2d = (Graphics2D)g;
/*     */ 
/* 231 */     g2d.translate((int)pageFormat.getImageableX(), (int)pageFormat.getImageableY());
/*     */ 
/* 233 */     int wPage = 0;
/* 234 */     int hPage = 0;
/* 235 */     if (pageFormat.getOrientation() == 1) {
/* 236 */       wPage = (int)pageFormat.getImageableWidth();
/* 237 */       hPage = (int)pageFormat.getImageableHeight();
/*     */     } else {
/* 239 */       wPage = (int)pageFormat.getImageableWidth();
/* 240 */       wPage += wPage / 2;
/* 241 */       hPage = (int)pageFormat.getImageableHeight();
/* 242 */       g2d.setClip(0, 0, wPage, hPage);
/*     */     }
/* 244 */     int y = 0;
/* 245 */     g2d.setFont(this.areaListado.getFont().deriveFont(1, this.areaListado.getFont().getSize2D() + 2.0F));
/* 246 */     g2d.setColor(Color.black);
/* 247 */     FontMetrics fm = g2d.getFontMetrics();
/* 248 */     y += fm.getAscent();
/* 249 */     g2d.drawString(this.titulo, 0, y);
/* 250 */     y += 20;
/*     */ 
/* 252 */     g2d.setFont(this.areaListado.getFont());
/* 253 */     fm = g2d.getFontMetrics();
/*     */ 
/* 256 */     int header = y;
/* 257 */     int h = fm.getHeight();
/* 258 */     FontRenderContext frc = g2d.getFontRenderContext();
/* 259 */     TextLayout layTemp = new TextLayout(" ", this.areaListado.getFont(), frc);
/*     */ 
/* 262 */     int rowH = (int)(h + layTemp.getDescent() + layTemp.getLeading());
/* 263 */     int rowPerPage = (hPage - header) / rowH;
/* 264 */     this.m_maxNumPage = Math.max((int)Math.ceil(matrizImpresion.size() / rowPerPage), 1);
/*     */ 
/* 267 */     int iniRow = pageIndex * rowPerPage;
/* 268 */     int endRow = Math.min(matrizImpresion.size(), iniRow + rowPerPage);
/*     */ 
/* 271 */     g2d.setColor(Color.black);
/*     */ 
/* 273 */     for (int nRow = iniRow; nRow < endRow; nRow++) {
/* 274 */       y += h;
/* 275 */       String str = (String)matrizImpresion.get(nRow);
/* 276 */       if (str.equals("")) {
/* 277 */         str = " ";
/*     */       }
/* 279 */       TextLayout layout = new TextLayout(str, this.areaListado.getFont(), frc);
/* 280 */       float w = layout.getAdvance();
/* 281 */       if (w > wPage) {
/* 282 */         AttributedString mStyledText = new AttributedString(str);
/* 283 */         mStyledText.addAttribute(TextAttribute.FONT, this.areaListado.getFont());
/* 284 */         AttributedCharacterIterator charIterator = mStyledText.getIterator();
/* 285 */         LineBreakMeasurer measurer = new LineBreakMeasurer(charIterator, frc);
/* 286 */         float wrappingWidth = (float)pageFormat.getImageableWidth();
/* 287 */         float x = 0.0F;
/* 288 */         while (measurer.getPosition() < charIterator.getEndIndex()) {
/* 289 */           layout = measurer.nextLayout(wrappingWidth);
/* 290 */           y = (int)(y + layout.getAscent());
/* 291 */           float dx = layout.isLeftToRight() ? 0.0F : wrappingWidth - layout.getAdvance();
/* 292 */           layout.draw(g2d, x + dx, y);
/* 293 */           y = (int)(y + (layout.getDescent() + layout.getLeading()));
/*     */         }
/*     */       } else {
/* 296 */         layout.draw(g2d, 0.0F, y);
/*     */       }
/*     */     }
/*     */ 
/* 300 */     System.gc();
/* 301 */     return 0;
/*     */   }
/*     */ 
/*     */   protected void addBotones(JToolBar toolBar)
/*     */   {
/* 310 */     JButton button = null;
/*     */ 
/* 312 */     button = makeNavigationButton("save", "guardar", Mensajes.getString("saveAstt"), Mensajes.getString("saveAs"));
/*     */ 
/* 315 */     toolBar.add(button);
/*     */ 
/* 317 */     button = makeNavigationButton("fonts", "fuentes", Mensajes.getString("selFonttt"), Mensajes.getString("fuentes"));
/*     */ 
/* 320 */     toolBar.add(button);
/*     */ 
/* 323 */     button = makeNavigationButton("print24", "imprimir", Mensajes.getString("printtt"), Mensajes.getString("print"));
/*     */ 
/* 326 */     toolBar.add(button);
/*     */ 
/* 328 */     button = makeNavigationButton("preview", "vistaprevia", Mensajes.getString("previewtt"), Mensajes.getString("preview"));
/*     */ 
/* 331 */     toolBar.add(button);
/*     */   }
/*     */ 
/*     */   protected JButton makeNavigationButton(String imageName, String actionCommand, String toolTipText, String altText)
/*     */   {
/* 348 */     String imgLocation = "/contaes/iconos/" + imageName + ".png";
/* 349 */     URL imageURL = Listado.class.getResource(imgLocation);
/*     */ 
/* 352 */     JButton button = new JButton();
/* 353 */     button.setActionCommand(actionCommand);
/* 354 */     button.setToolTipText(toolTipText);
/* 355 */     button.addActionListener(this);
/*     */ 
/* 357 */     if (imageURL != null) {
/* 358 */       button.setIcon(new ImageIcon(imageURL, altText));
/*     */     } else {
/* 360 */       button.setText(altText);
/* 361 */       System.err.println("Resource not found: " + imgLocation);
/*     */     }
/*     */ 
/* 364 */     return button;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.listados.Listado
 * JD-Core Version:    0.6.2
 */