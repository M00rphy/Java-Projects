/*     */ package contaes.dialogosAuxiliares;
/*     */ 
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Cursor;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Image;
/*     */ import java.awt.Insets;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.print.PageFormat;
/*     */ import java.awt.print.Printable;
/*     */ import java.awt.print.PrinterException;
/*     */ import java.awt.print.PrinterJob;
/*     */ import java.io.PrintStream;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JToolBar;
/*     */ import javax.swing.border.MatteBorder;
/*     */ 
/*     */ public class PrintPreview extends JFrame
/*     */ {
/*     */   protected int m_wPage;
/*     */   protected int m_hPage;
/*     */   protected Printable m_target;
/*     */   protected JComboBox m_cbScale;
/*     */   protected PreviewContainer m_preview;
/*     */   protected PageFormat pageFormat;
/*     */ 
/*     */   public PrintPreview(Printable target)
/*     */   {
/*  40 */     this(target, Mensajes.getString("preview"));
/*     */   }
/*     */ 
/*     */   public PrintPreview(Printable target, String title) {
/*  44 */     super(title);
/*  45 */     setSize(600, 400);
/*  46 */     this.m_target = target;
/*     */ 
/*  48 */     JToolBar tb = new JToolBar();
/*  49 */     JButton bt = new JButton(Mensajes.getString("print"), new ImageIcon("/contaes/iconos/print.png"));
/*  50 */     ActionListener lst = new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/*  52 */         PrinterJob prnJob = PrinterJob.getPrinterJob();
/*  53 */         prnJob.setPrintable(PrintPreview.this.m_target, PrintPreview.this.pageFormat);
/*  54 */         boolean doPrint = prnJob.printDialog();
/*  55 */         if (doPrint) {
/*     */           try {
/*  57 */             PrintPreview.this.setCursor(Cursor.getPredefinedCursor(3));
/*     */ 
/*  59 */             prnJob.print();
/*     */           } catch (PrinterException exception) {
/*  61 */             System.err.println(Mensajes.getString("errPrint") + ": " + exception);
/*     */           }
/*  63 */           PrintPreview.this.setCursor(Cursor.getPredefinedCursor(0));
/*     */         }
/*     */ 
/*  66 */         PrintPreview.this.dispose();
/*     */       }
/*     */     };
/*  69 */     bt.addActionListener(lst);
/*  70 */     bt.setAlignmentY(0.5F);
/*  71 */     bt.setMargin(new Insets(4, 6, 4, 6));
/*  72 */     tb.add(bt);
/*     */ 
/*  74 */     bt = new JButton(Mensajes.getString("cerrar"));
/*  75 */     lst = new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/*  77 */         PrintPreview.this.dispose();
/*     */       }
/*     */     };
/*  80 */     bt.addActionListener(lst);
/*  81 */     bt.setAlignmentY(0.5F);
/*  82 */     bt.setMargin(new Insets(2, 6, 2, 6));
/*  83 */     tb.add(bt);
/*     */ 
/*  85 */     String[] scales = { "10 %", "25 %", "50 %", "100 %" };
/*  86 */     this.m_cbScale = new JComboBox(scales);
/*  87 */     lst = new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/*  89 */         Thread runner = new Thread() {
/*     */           public void run() {
/*  91 */             String str = PrintPreview.this.m_cbScale.getSelectedItem().toString();
/*     */ 
/*  93 */             if (str.endsWith("%"))
/*  94 */               str = str.substring(0, str.length() - 1);
/*  95 */             str = str.trim();
/*  96 */             int scale = 0;
/*     */             try { scale = Integer.parseInt(str); } catch (NumberFormatException ex) {
/*  98 */               return;
/*  99 */             }int w = PrintPreview.this.m_wPage * scale / 100;
/* 100 */             int h = PrintPreview.this.m_hPage * scale / 100;
/*     */ 
/* 102 */             Component[] comps = PrintPreview.this.m_preview.getComponents();
/* 103 */             for (int k = 0; k < comps.length; k++)
/* 104 */               if ((comps[k] instanceof PrintPreview.PagePreview))
/*     */               {
/* 106 */                 PrintPreview.PagePreview pp = (PrintPreview.PagePreview)comps[k];
/* 107 */                 pp.setScaledSize(w, h);
/*     */               }
/* 109 */             PrintPreview.this.m_preview.doLayout();
/* 110 */             PrintPreview.this.m_preview.getParent().getParent().validate();
/*     */           }
/*     */         };
/* 113 */         runner.start();
/*     */       }
/*     */     };
/* 116 */     this.m_cbScale.addActionListener(lst);
/* 117 */     this.m_cbScale.setMaximumSize(this.m_cbScale.getPreferredSize());
/* 118 */     this.m_cbScale.setEditable(true);
/* 119 */     tb.addSeparator();
/* 120 */     tb.add(this.m_cbScale);
/* 121 */     getContentPane().add(tb, "North");
/*     */ 
/* 123 */     this.m_preview = new PreviewContainer();
/*     */ 
/* 125 */     PrinterJob prnJob = PrinterJob.getPrinterJob();
/*     */ 
/* 127 */     this.pageFormat = prnJob.pageDialog(prnJob.defaultPage());
/* 128 */     if ((this.pageFormat.getHeight() == 0.0D) || (this.pageFormat.getWidth() == 0.0D)) {
/* 129 */       System.err.println(Mensajes.getString("errSizePag"));
/* 130 */       return;
/*     */     }
/* 132 */     this.m_wPage = ((int)this.pageFormat.getWidth());
/* 133 */     this.m_hPage = ((int)this.pageFormat.getHeight());
/* 134 */     int scale = 10;
/* 135 */     int w = this.m_wPage * scale / 100;
/* 136 */     int h = this.m_hPage * scale / 100;
/*     */ 
/* 138 */     int pageIndex = 0;
/*     */     try {
/*     */       while (true) {
/* 141 */         BufferedImage img = new BufferedImage(this.m_wPage, this.m_hPage, 1);
/*     */ 
/* 143 */         Graphics g = img.getGraphics();
/* 144 */         g.setColor(Color.white);
/* 145 */         g.fillRect(0, 0, this.m_wPage, this.m_hPage);
/* 146 */         if (target.print(g, this.pageFormat, pageIndex) != 0) {
/*     */           break;
/*     */         }
/* 149 */         PagePreview pp = new PagePreview(w, h, img);
/* 150 */         this.m_preview.add(pp);
/* 151 */         pageIndex++;
/*     */       }
/*     */     }
/*     */     catch (PrinterException e) {
/* 155 */       e.printStackTrace();
/* 156 */       System.err.println(Mensajes.getString("errPrint") + ": " + e.toString());
/*     */     }
/*     */ 
/* 159 */     JScrollPane ps = new JScrollPane(this.m_preview);
/* 160 */     getContentPane().add(ps, "Center");
/*     */ 
/* 162 */     setDefaultCloseOperation(2);
/* 163 */     setVisible(true);
/*     */   }
/*     */ 
/*     */   class PagePreview extends JPanel
/*     */   {
/*     */     protected int m_w;
/*     */     protected int m_h;
/*     */     protected Image m_source;
/*     */     protected Image m_img;
/*     */ 
/*     */     public PagePreview(int w, int h, Image source)
/*     */     {
/* 243 */       this.m_w = w;
/* 244 */       this.m_h = h;
/* 245 */       this.m_source = source;
/* 246 */       this.m_img = this.m_source.getScaledInstance(this.m_w, this.m_h, 4);
/*     */ 
/* 248 */       this.m_img.flush();
/* 249 */       setBackground(Color.white);
/* 250 */       setBorder(new MatteBorder(1, 1, 2, 2, Color.black));
/*     */     }
/*     */ 
/*     */     public void setScaledSize(int w, int h) {
/* 254 */       this.m_w = w;
/* 255 */       this.m_h = h;
/* 256 */       this.m_img = this.m_source.getScaledInstance(this.m_w, this.m_h, 4);
/*     */ 
/* 258 */       repaint();
/*     */     }
/*     */ 
/*     */     public Dimension getPreferredSize() {
/* 262 */       Insets ins = getInsets();
/* 263 */       return new Dimension(this.m_w + ins.left + ins.right, this.m_h + ins.top + ins.bottom);
/*     */     }
/*     */ 
/*     */     public Dimension getMaximumSize()
/*     */     {
/* 268 */       return getPreferredSize();
/*     */     }
/*     */ 
/*     */     public Dimension getMinimumSize() {
/* 272 */       return getPreferredSize();
/*     */     }
/*     */ 
/*     */     public void paint(Graphics g) {
/* 276 */       g.setColor(getBackground());
/* 277 */       g.fillRect(0, 0, getWidth(), getHeight());
/* 278 */       g.drawImage(this.m_img, 0, 0, this);
/* 279 */       paintBorder(g);
/*     */     }
/*     */   }
/*     */ 
/*     */   class PreviewContainer extends JPanel
/*     */   {
/* 168 */     protected int H_GAP = 16;
/* 169 */     protected int V_GAP = 10;
/*     */ 
/*     */     PreviewContainer() {  } 
/* 172 */     public Dimension getPreferredSize() { int n = getComponentCount();
/* 173 */       if (n == 0)
/* 174 */         return new Dimension(this.H_GAP, this.V_GAP);
/* 175 */       Component comp = getComponent(0);
/* 176 */       Dimension dc = comp.getPreferredSize();
/* 177 */       int w = dc.width;
/* 178 */       int h = dc.height;
/*     */ 
/* 180 */       Dimension dp = getParent().getSize();
/* 181 */       int nCol = Math.max((dp.width - this.H_GAP) / (w + this.H_GAP), 1);
/* 182 */       int nRow = n / nCol;
/* 183 */       if (nRow * nCol < n) {
/* 184 */         nRow++;
/*     */       }
/* 186 */       int ww = nCol * (w + this.H_GAP) + this.H_GAP;
/* 187 */       int hh = nRow * (h + this.V_GAP) + this.V_GAP;
/* 188 */       Insets ins = getInsets();
/* 189 */       return new Dimension(ww + ins.left + ins.right, hh + ins.top + ins.bottom);
/*     */     }
/*     */ 
/*     */     public Dimension getMaximumSize()
/*     */     {
/* 194 */       return getPreferredSize();
/*     */     }
/*     */ 
/*     */     public Dimension getMinimumSize() {
/* 198 */       return getPreferredSize();
/*     */     }
/*     */ 
/*     */     public void doLayout() {
/* 202 */       Insets ins = getInsets();
/* 203 */       int x = ins.left + this.H_GAP;
/* 204 */       int y = ins.top + this.V_GAP;
/*     */ 
/* 206 */       int n = getComponentCount();
/* 207 */       if (n == 0)
/* 208 */         return;
/* 209 */       Component comp = getComponent(0);
/* 210 */       Dimension dc = comp.getPreferredSize();
/* 211 */       int w = dc.width;
/* 212 */       int h = dc.height;
/*     */ 
/* 214 */       Dimension dp = getParent().getSize();
/* 215 */       int nCol = Math.max((dp.width - this.H_GAP) / (w + this.H_GAP), 1);
/* 216 */       int nRow = n / nCol;
/* 217 */       if (nRow * nCol < n) {
/* 218 */         nRow++;
/*     */       }
/* 220 */       int index = 0;
/* 221 */       for (int k = 0; k < nRow; k++) {
/* 222 */         for (int m = 0; m < nCol; m++) {
/* 223 */           if (index >= n)
/* 224 */             return;
/* 225 */           comp = getComponent(index++);
/* 226 */           comp.setBounds(x, y, w, h);
/* 227 */           x += w + this.H_GAP;
/*     */         }
/* 229 */         y += h + this.V_GAP;
/* 230 */         x = ins.left + this.H_GAP;
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.dialogosAuxiliares.PrintPreview
 * JD-Core Version:    0.6.2
 */