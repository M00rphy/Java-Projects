/*     */ package almacen2.gui.graficos;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.GradientPaint;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

/*     */ import java.text.DecimalFormat;
/*     */ import java.text.DecimalFormatSymbols;
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ public class Graficos extends JPanel
/*     */ {
/*     */   static final int maxCharHeight = 20;
/*     */   static final int minFontSize = 6;
/*     */   static final int maxNumGrupos = 4;
/*  48 */   static final Color bg = Color.white;
/*  49 */   static final Color fg = Color.black;
/*  50 */   static final Color blue = Color.blue;
/*  51 */   static final Color green = Color.green;
/*  52 */   static final Color cyan = Color.cyan;
/*  53 */   static final Color darkGreen = new Color(20, 150, 20);
/*  54 */   static final Color red = Color.red;
/*  55 */   static final Color magenta = Color.magenta;
/*  56 */   static final Color orange = Color.orange;
/*  57 */   static final Color yellow = Color.yellow;
/*  58 */   static final Color fondoArriba = new Color(215, 215, 230);
/*  59 */   static final Color fondoAbajo = new Color(120, 120, 175);
/*     */   Dimension totalSize;
/*     */   FontMetrics fontMetrics;
/*     */   private ArrayList<String> xTitle;
/*  63 */   private String titulo = "Title";
/*  64 */   private int nGrupos = 0;
/*     */   private String[] leyenda;
/*     */   private ArrayList<ArrayList<Integer>> datos;
/*  67 */   private double maxDato = 0.0D;
/*  68 */   private boolean solido = true;
/*     */   DecimalFormat fn;
/*  70 */   private boolean fondoGradiente = true;
/*     */ 
/*     */   public Graficos() {
/*  73 */     init();
/*     */   }
/*     */ 
/*     */   public void init()
/*     */   {
/*  78 */     setBackground(bg);
/*  79 */     setForeground(fg);
/*  80 */     DecimalFormatSymbols formato = new DecimalFormatSymbols();
/*  81 */     formato.setDecimalSeparator(',');
/*  82 */     formato.setPerMill('.');
/*  83 */     this.fn = new DecimalFormat("#,###,###", formato);
/*     */   }
/*     */ 
/*     */   public void setXTitles(ArrayList<String> xT) {
/*  87 */     this.xTitle = xT;
/*     */   }
/*     */ 
/*     */   public void setLeyendas(int nGrupos, String[] l)
/*     */   {
/*  97 */     if (nGrupos > 4)
/*  98 */       this.nGrupos = 4;
/*     */     else {
/* 100 */       this.nGrupos = nGrupos;
/*     */     }
/* 102 */     this.leyenda = new String[nGrupos];
/* 103 */     for (int x = 0; x < nGrupos; x++)
/* 104 */       this.leyenda[x] = l[x];
/*     */   }
/*     */ 
/*     */   public void setDatos(ArrayList<ArrayList<Integer>> datos)
/*     */   {
/* 109 */     this.datos = datos;
/* 110 */     double minDato = 0.0D;
/* 111 */     for (int i = 0; i < this.datos.size(); i++) {
/* 112 */       for (int h = 0; h < ((ArrayList)this.datos.get(i)).size(); h++) {
/* 113 */         if (((Integer)((ArrayList)this.datos.get(i)).get(h)).intValue() > this.maxDato)
/* 114 */           this.maxDato = ((Integer)((ArrayList)this.datos.get(i)).get(h)).intValue();
/* 115 */         else if (((Integer)((ArrayList)this.datos.get(i)).get(h)).intValue() < minDato) {
/* 116 */           minDato = ((Integer)((ArrayList)this.datos.get(i)).get(h)).intValue();
/*     */         }
/*     */       }
/*     */     }
/* 120 */     if ((minDato < 0.0D) && (this.maxDato < -minDato))
/* 121 */       this.maxDato = (-minDato);
/*     */   }
/*     */ 
/*     */   public void setTitulo(String titulo)
/*     */   {
/* 131 */     this.titulo = titulo;
/*     */   }
/*     */ 
/*     */   public boolean isFondoGradiente()
/*     */   {
/* 138 */     return this.fondoGradiente;
/*     */   }
/*     */ 
/*     */   public void setFondoGradiente(boolean fondoGradiente)
/*     */   {
/* 145 */     this.fondoGradiente = fondoGradiente;
/*     */   }
/*     */ 
/*     */   public void setSolido(boolean solido)
/*     */   {
/* 152 */     this.solido = solido;
/*     */   }
/*     */ 
/*     */   FontMetrics pickFont(Graphics2D g2, String longString, int xSpace)
/*     */   {
/* 158 */     boolean fontFits = false;
/* 159 */     Font font = g2.getFont();
/* 160 */     FontMetrics fontMetrics = g2.getFontMetrics();
/* 161 */     int size = font.getSize();
/* 162 */     String name = font.getName();
/* 163 */     int style = font.getStyle();
/*     */ 
/* 165 */     while (!fontFits) {
/* 166 */       if ((fontMetrics.getHeight() <= 20) && (fontMetrics.stringWidth(longString) <= xSpace)) {
/* 167 */         fontFits = true;
/*     */       }
/* 169 */       else if (size <= 6) {
/* 170 */         fontFits = true;
/*     */       } else {
/* 172 */         g2.setFont(font = new Font(name, style, --size));
/*     */ 
/* 175 */         fontMetrics = g2.getFontMetrics();
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 180 */     return fontMetrics;
/*     */   }
/*     */ 
/*     */   public void paint(Graphics g)
/*     */   {
/* 188 */     super.paint(g);
/* 189 */     if (this.nGrupos == 0) {
/* 190 */       this.nGrupos = 1;
/* 191 */       this.leyenda = new String[1];
/* 192 */       this.leyenda[0] = "Leyend";
/*     */     }
/* 194 */     Graphics2D g2 = (Graphics2D)g;
/* 195 */     g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 196 */     Dimension d = getSize();
/* 197 */     GradientPaint gradiente = new GradientPaint(0.0F, 0.0F, fondoArriba, 0.0F, d.height, fondoAbajo);
/* 198 */     if (!isFondoGradiente()) {
/* 199 */       gradiente = new GradientPaint(0.0F, 0.0F, bg, 0.0F, d.height, bg);
/*     */     }
/* 201 */     g2.setPaint(gradiente);
/* 202 */     g2.fill(new Rectangle2D.Double(0.0D, 0.0D, d.width, d.height));
/* 203 */     g2.setPaint(fg);
/* 204 */     int xoff = 40;
/* 205 */     int yoff = 40;
/* 206 */     int margenDcha = 7;
/* 207 */     int gridWidth = (d.width - (xoff + margenDcha)) / this.xTitle.size();
/* 208 */     int gridHeight = (int)((d.height - 2 * yoff) / this.maxDato);
/* 209 */     int barWitdh = (gridWidth - 2) / this.nGrupos;
/* 210 */     int leyendaWidth = (d.width - 20) / this.nGrupos;
/*     */ 
/* 213 */     g2.setFont(new Font(g2.getFont().getName(), 1, g2.getFont().getSize() + 5));
/* 214 */     g2.drawString(this.titulo, 10, 20);
/* 215 */     g2.setFont(new Font(g2.getFont().getName(), 0, g2.getFont().getSize() - 5));
/*     */ 
/* 217 */     this.fontMetrics = pickFont(g2, (String)this.xTitle.get(0), gridWidth);
/*     */ 
/* 222 */     g2.draw(new Line2D.Double(xoff, d.height - yoff, xoff, yoff));
/* 223 */     g2.draw(new Line2D.Double(xoff, d.height - yoff, d.width - margenDcha, d.height - yoff));
/* 224 */     for (int x = 0; x < this.xTitle.size(); x++) {
/* 225 */       g2.drawString((String)this.xTitle.get(x), gridWidth * x + xoff + 2, d.height - 28);
/* 226 */       g2.draw(new Line2D.Double(gridWidth * (x + 1) + xoff, d.height - yoff + 10, gridWidth * (x + 1) + xoff, yoff));
/*     */     }
/* 228 */     this.fontMetrics = pickFont(g2, this.fn.format(this.maxDato), xoff - 5);
/* 229 */     for (int x = 0; x < this.maxDato; x++) {
/* 230 */       g2.draw(new Line2D.Double(xoff - 5, yoff + gridHeight * x, d.width - margenDcha, yoff + gridHeight * x));
/* 231 */       g2.drawString(this.fn.format(this.maxDato - x), 5, yoff + gridHeight * x);
/*     */     }
/*     */ 
/* 235 */     String leyendaMayor = this.leyenda[0];
/* 236 */     for (int x = 0; x < this.nGrupos; x++) {
/* 237 */       if (this.leyenda[x].length() > leyendaMayor.length()) {
/* 238 */         leyendaMayor = this.leyenda[x];
/*     */       }
/*     */     }
/* 241 */     this.fontMetrics = pickFont(g2, leyendaMayor, leyendaWidth);
/* 242 */     for (int x = 0; x < this.nGrupos; x++) {
/* 243 */       if (x % 4 == 0)
/* 244 */         g2.setPaint(blue);
/* 245 */       else if (x % 4 == 1)
/* 246 */         g2.setPaint(orange);
/* 247 */       else if (x % 4 == 2)
/* 248 */         g2.setPaint(green);
/* 249 */       else if (x % 4 == 3) {
/* 250 */         g2.setPaint(red);
/*     */       }
/* 252 */       g2.drawString(this.leyenda[x], leyendaWidth * x + 20, d.height - 7);
/*     */     }
/*     */ 
/* 255 */     g2.setPaint(fg);
/*     */ 
/* 259 */     if (this.datos == null) {
/* 260 */       return;
/*     */     }
/* 262 */     boolean positivo = true;
/* 263 */     double dato = 0.0D;
/* 264 */     for (int i = 0; i < this.datos.size(); i++) {
/* 265 */       if (i % 4 == 0)
/* 266 */         g2.setPaint(blue);
/* 267 */       else if (i % 4 == 1)
/* 268 */         g2.setPaint(orange);
/* 269 */       else if (i % 4 == 2)
/* 270 */         g2.setPaint(green);
/* 271 */       else if (i % 4 == 3) {
/* 272 */         g2.setPaint(red);
/*     */       }
/* 274 */       for (int h = 0; h < ((ArrayList)this.datos.get(i)).size(); h++) {
/* 275 */         dato = ((Integer)((ArrayList)this.datos.get(i)).get(h)).intValue();
/* 276 */         if (dato >= 0.0D) {
/* 277 */           positivo = true;
/* 278 */         } else if (dato < 0.0D) {
/* 279 */           dato = -dato;
/* 280 */           positivo = false;
/*     */         }
/* 282 */         double barHeight = dato * (d.height - 2 * yoff) / this.maxDato;
/* 283 */         if (((this.solido) && (positivo)) || ((!this.solido) && (!positivo))) {
/* 284 */           g2.fill(new Rectangle2D.Double(xoff + 1 + gridWidth * h + barWitdh * i, d.height - yoff - barHeight, barWitdh, barHeight));
/* 285 */         } else if (((this.solido) && (!positivo)) || ((!this.solido) && (positivo))) {
/* 286 */           if (barHeight != 0.0D) {
/* 287 */             g2.draw(new Line2D.Double(xoff + 1 + gridWidth * h + barWitdh * i, d.height - yoff - barHeight, xoff + gridWidth * h + barWitdh * i + barWitdh, d.height - yoff - barHeight));
/*     */ 
/* 291 */             g2.fill(new Rectangle2D.Double(xoff + 1 + gridWidth * h + barWitdh * i, d.height - yoff - 2, barWitdh, 2.0D));
/*     */           }
/*     */ 
/* 295 */           int n = (int)(barHeight / 2.0D);
/* 296 */           for (int z = 0; z < n / 2; z++) {
/* 297 */             g2.fill(new Rectangle2D.Double(xoff + 1 + gridWidth * h + barWitdh * i, d.height - yoff - barHeight + 4 * z, barWitdh, 2.0D));
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 303 */     g2.setPaint(fg);
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.gui.graficos.Graficos
 * JD-Core Version:    0.6.2
 */