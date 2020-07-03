/*     */ package contaes.cuentas;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.Puente;
/*     */ import contaes.auxiliar.AlinearCadena;
/*     */ import contaes.auxiliarTablas.GeneralColorRenderer;
/*     */ import contaes.auxiliarTablas.ImporteColorRenderer;
/*     */ import contaes.auxiliarTablas.ImporteNegritaRenderer;
/*     */ import contaes.auxiliarTablas.NegritaRenderer;
/*     */ import contaes.manejoDatos.auxiliar.AcumuladosMensuales;
/*     */ import contaes.manejoDatos.auxiliar.FinLinea;
/*     */ import contaes.manejoDatos.auxiliar.GrabarFichero;
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Cursor;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Insets;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.font.TextLayout;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.print.PageFormat;
/*     */ import java.awt.print.Printable;
/*     */ import java.awt.print.PrinterException;
/*     */ import java.awt.print.PrinterJob;
/*     */ import java.io.File;
/*     */ import java.io.PrintStream;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.DecimalFormatSymbols;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JFileChooser;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.border.CompoundBorder;
/*     */ import javax.swing.table.JTableHeader;
/*     */ import javax.swing.table.TableCellRenderer;
/*     */ import javax.swing.table.TableColumn;
/*     */ import javax.swing.table.TableColumnModel;
/*     */ 
/*     */ public class TablaDatosMensuales extends JPanel
/*     */   implements Printable
/*     */ {
/*  70 */   private static double TITLE_SCALE = 1.1D;
/*  71 */   private JPanel jPanel = null;
/*  72 */   private JLabel numCuenta = null;
/*  73 */   private JLabel nomCuenta = null;
/*  74 */   private JPanel jPanel1 = null;
/*  75 */   private JScrollPane jScrollPane = null;
/*  76 */   private JTable tabla = null;
/*  77 */   private DecimalFormatSymbols formato = new DecimalFormatSymbols();
/*     */   private DecimalFormat fn;
/*  79 */   private JButton bImprimir = null;
/*  80 */   private JButton bGuardar = null;
/*  81 */   private String cuenta = "";
/*  82 */   private String nombreCuenta = "";
/*     */ 
/*     */   public TablaDatosMensuales()
/*     */   {
/*  89 */     initialize();
/*     */   }
/*     */ 
/*     */   private void initialize()
/*     */   {
/*  98 */     this.formato.setDecimalSeparator(',');
/*  99 */     this.formato.setPerMill('.');
/* 100 */     this.fn = new DecimalFormat("#,###,##0.00", this.formato);
/*     */ 
/* 102 */     setLayout(new BorderLayout());
/* 103 */     setSize(450, 420);
/* 104 */     add(getJPanel(), "North");
/* 105 */     add(getJPanel1(), "South");
/* 106 */     add(getJScrollPane(), "Center");
/*     */   }
/*     */ 
/*     */   private JPanel getJPanel()
/*     */   {
/* 115 */     if (this.jPanel == null) {
/* 116 */       this.nomCuenta = new JLabel();
/* 117 */       this.nomCuenta.setText("");
/* 118 */       this.numCuenta = new JLabel();
/* 119 */       this.numCuenta.setText("");
/* 120 */       this.numCuenta.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
/* 121 */       this.jPanel = new JPanel();
/* 122 */       this.jPanel.setLayout(new BorderLayout());
/* 123 */       this.jPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 10, 50));
/* 124 */       this.jPanel.add(this.numCuenta, "West");
/* 125 */       this.jPanel.add(this.nomCuenta, "Center");
/*     */     }
/* 127 */     return this.jPanel;
/*     */   }
/*     */ 
/*     */   private JPanel getJPanel1()
/*     */   {
/* 136 */     if (this.jPanel1 == null) {
/* 137 */       GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
/* 138 */       gridBagConstraints1.gridx = 1;
/* 139 */       gridBagConstraints1.gridwidth = 1;
/* 140 */       gridBagConstraints1.insets = new Insets(10, 30, 10, 0);
/* 141 */       gridBagConstraints1.gridy = 0;
/* 142 */       GridBagConstraints gridBagConstraints = new GridBagConstraints();
/* 143 */       gridBagConstraints.gridx = 0;
/* 144 */       gridBagConstraints.gridwidth = 1;
/* 145 */       gridBagConstraints.insets = new Insets(10, 0, 10, 30);
/* 146 */       gridBagConstraints.gridy = 0;
/* 147 */       this.jPanel1 = new JPanel();
/* 148 */       this.jPanel1.setLayout(new GridBagLayout());
/*     */ 
/* 150 */       this.jPanel1.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(15, 70, 20, 70), BorderFactory.createEtchedBorder(1)));
/*     */ 
/* 153 */       this.jPanel1.add(getBImprimir(), gridBagConstraints);
/* 154 */       this.jPanel1.add(getBGuardar(), gridBagConstraints1);
/*     */     }
/* 156 */     return this.jPanel1;
/*     */   }
/*     */ 
/*     */   private JScrollPane getJScrollPane()
/*     */   {
/* 165 */     if (this.jScrollPane == null) {
/* 166 */       this.jScrollPane = new JScrollPane();
/*     */ 
/* 168 */       this.jScrollPane.setBorder(new CompoundBorder(BorderFactory.createMatteBorder(5, 70, 5, 90, Color.white), new CompoundBorder(BorderFactory.createLoweredBevelBorder(), BorderFactory.createMatteBorder(5, 10, 5, 10, Color.white))));
/*     */ 
/* 172 */       this.jScrollPane.setViewportView(getTabla());
/*     */     }
/* 174 */     return this.jScrollPane;
/*     */   }
/*     */ 
/*     */   private JTable getTabla()
/*     */   {
/* 183 */     if (this.tabla == null) {
/* 184 */       String[] columnas = { Mensajes.getString("mes"), Mensajes.getString("debe"), Mensajes.getString("haber"), Mensajes.getString("saldo") };
/*     */ 
/* 186 */       Object[][] datos = { { Mensajes.getString("apertura"), new Integer(0), new Integer(0), new Integer(0) }, { Mensajes.getString("enero"), new Integer(0), new Integer(0), new Integer(0) }, { Mensajes.getString("febrero"), new Integer(0), new Integer(0), new Integer(0) }, { Mensajes.getString("marzo"), new Integer(0), new Integer(0), new Integer(0) }, { Mensajes.getString("abril"), new Integer(0), new Integer(0), new Integer(0) }, { Mensajes.getString("mayo"), new Integer(0), new Integer(0), new Integer(0) }, { Mensajes.getString("junio"), new Integer(0), new Integer(0), new Integer(0) }, { Mensajes.getString("julio"), new Integer(0), new Integer(0), new Integer(0) }, { Mensajes.getString("agosto"), new Integer(0), new Integer(0), new Integer(0) }, { Mensajes.getString("septiembre"), new Integer(0), new Integer(0), new Integer(0) }, { Mensajes.getString("octubre"), new Integer(0), new Integer(0), new Integer(0) }, { Mensajes.getString("noviembre"), new Integer(0), new Integer(0), new Integer(0) }, { Mensajes.getString("diciembre"), new Integer(0), new Integer(0), new Integer(0) }, { Mensajes.getString("cierre"), new Integer(0), new Integer(0), new Integer(0) }, { Mensajes.getString("total"), new Integer(0), new Integer(0), new Integer(0) } };
/*     */ 
/* 203 */       final TableCellRenderer importeNegritaRenderer = new ImporteNegritaRenderer();
/* 204 */       final TableCellRenderer negritaRenderer = new NegritaRenderer();
/* 205 */       this.tabla = new JTable(datos, columnas)
/*     */       {
/*     */         public TableCellRenderer getCellRenderer(int row, int column)
/*     */         {
/* 209 */           if ((row == 14) && ((column == 1) || (column == 2) || (column == 3)))
/*     */           {
/* 211 */             return importeNegritaRenderer;
/* 212 */           }if ((row == 14) && (column == 0)) {
/* 213 */             return negritaRenderer;
/*     */           }
/*     */ 
/* 216 */           return super.getCellRenderer(row, column);
/*     */         }
/*     */       };
/* 219 */       TableColumn columna = null;
/* 220 */       columna = this.tabla.getColumnModel().getColumn(0);
/* 221 */       columna = this.tabla.getColumnModel().getColumn(1);
/* 222 */       columna.setCellRenderer(new ImporteColorRenderer());
/* 223 */       columna = this.tabla.getColumnModel().getColumn(2);
/* 224 */       columna.setCellRenderer(new ImporteColorRenderer());
/* 225 */       columna = this.tabla.getColumnModel().getColumn(3);
/* 226 */       columna.setCellRenderer(new ImporteColorRenderer());
/* 227 */       this.tabla.setDefaultRenderer(Object.class, new GeneralColorRenderer());
/*     */     }
/* 229 */     return this.tabla;
/*     */   }
/*     */ 
/*     */   public void colocarDatos(String cta, String name)
/*     */   {
/* 241 */     if (this.cuenta.equals(cta)) {
/* 242 */       return;
/*     */     }
/* 244 */     this.cuenta = cta;
/* 245 */     this.nombreCuenta = name;
/* 246 */     setCursor(Cursor.getPredefinedCursor(3));
/* 247 */     this.numCuenta.setText(this.cuenta);
/* 248 */     this.nomCuenta.setText(this.nombreCuenta);
/* 249 */     AcumuladosMensuales datos = new AcumuladosMensuales(Inicio.getCEmpresa(), this.cuenta, Inicio.p.getEjercicio());
/*     */ 
/* 251 */     for (int x = 0; x < 14; x++) {
/* 252 */       this.tabla.setValueAt(new Double(datos.getD()[x]), x, 1);
/* 253 */       this.tabla.setValueAt(new Double(datos.getH()[x]), x, 2);
/* 254 */       this.tabla.setValueAt(new Double(datos.getS()[x]), x, 3);
/*     */     }
/* 256 */     this.tabla.setValueAt(new Double(datos.getD()[14]), 14, 1);
/* 257 */     this.tabla.setValueAt(new Double(datos.getH()[14]), 14, 2);
/* 258 */     this.tabla.setValueAt(new Double(datos.getS()[14]), 14, 3);
/* 259 */     setCursor(Cursor.getPredefinedCursor(0));
/*     */   }
/*     */ 
/*     */   private JButton getBImprimir()
/*     */   {
/* 268 */     if (this.bImprimir == null) {
/* 269 */       this.bImprimir = new JButton();
/* 270 */       this.bImprimir.setText(Mensajes.getString("print"));
/* 271 */       this.bImprimir.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/print.png")));
/* 272 */       this.bImprimir.setHorizontalTextPosition(2);
/* 273 */       this.bImprimir.addActionListener(new ActionListener()
/*     */       {
/*     */         public void actionPerformed(ActionEvent e) {
/* 276 */           TablaDatosMensuales.this.imprimir();
/*     */         }
/*     */       });
/*     */     }
/* 280 */     return this.bImprimir;
/*     */   }
/*     */ 
/*     */   private JButton getBGuardar()
/*     */   {
/* 289 */     if (this.bGuardar == null) {
/* 290 */       this.bGuardar = new JButton();
/* 291 */       this.bGuardar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/save16.png")));
/* 292 */       this.bGuardar.setText(Mensajes.getString("guardar"));
/* 293 */       this.bGuardar.setHorizontalTextPosition(2);
/* 294 */       this.bGuardar.addActionListener(new ActionListener()
/*     */       {
/*     */         public void actionPerformed(ActionEvent e) {
/* 297 */           TablaDatosMensuales.this.guardarEnArchivo();
/*     */         }
/*     */       });
/*     */     }
/* 301 */     return this.bGuardar;
/*     */   }
/*     */ 
/*     */   private void guardarEnArchivo()
/*     */   {
/* 309 */     String EOL = FinLinea.get();
/* 310 */     JFileChooser fc = new JFileChooser();
/* 311 */     int retorno = fc.showSaveDialog(this);
/* 312 */     if (retorno == 0) {
/* 313 */       File archivo = fc.getSelectedFile();
/* 314 */       GrabarFichero salida = new GrabarFichero(archivo);
/* 315 */       AlinearCadena alinea = new AlinearCadena();
/* 316 */       salida.insertar(this.cuenta + " : " + this.nombreCuenta + EOL + EOL);
/* 317 */       for (int i = 0; i < this.tabla.getRowCount(); i++) {
/* 318 */         String cadena = alinea.Izquierda(this.tabla.getValueAt(i, 0).toString(), 15);
/* 319 */         for (int column = 1; column < this.tabla.getColumnCount(); column++) {
/* 320 */           cadena = cadena + alinea.Derecha(this.fn.format(this.tabla.getValueAt(i, column)), 12);
/*     */         }
/*     */ 
/* 323 */         salida.insertar(cadena + EOL);
/*     */       }
/* 325 */       salida.cerrar();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void imprimir()
/*     */   {
/* 334 */     PrinterJob printerJob = PrinterJob.getPrinterJob();
/* 335 */     printerJob.setPrintable(this);
/* 336 */     boolean doPrint = printerJob.printDialog();
/* 337 */     if (doPrint)
/*     */       try {
/* 339 */         printerJob.print();
/*     */       } catch (PrinterException exception) {
/* 341 */         System.err.println(Mensajes.getString("errPrint") + ": " + exception);
/*     */       }
/*     */   }
/*     */ 
/*     */   public int print(Graphics pGraphics, PageFormat pPageFormat, int pPageIndex)
/*     */     throws PrinterException
/*     */   {
/* 363 */     Graphics2D lGraphics2D = (Graphics2D)pGraphics;
/*     */ 
/* 366 */     lGraphics2D.setColor(Color.black);
/*     */ 
/* 369 */     int lFontHeight = lGraphics2D.getFontMetrics().getHeight();
/*     */ 
/* 372 */     int lPageHeight = (int)pPageFormat.getImageableHeight();
/* 373 */     int lPageWidth = (int)pPageFormat.getImageableWidth();
/*     */ 
/* 377 */     int lTableWidth = this.tabla.getColumnModel().getTotalColumnWidth();
/* 378 */     double lScale = 1.0D;
/* 379 */     if (lTableWidth >= lPageWidth) {
/* 380 */       lScale = lPageWidth / lTableWidth;
/*     */     }
/*     */ 
/* 384 */     int lOneRowHeight = this.tabla.getRowHeight();
/* 385 */     int lHeaderHeight = this.tabla.getTableHeader().getHeight();
/*     */ 
/* 387 */     int lNumRowsOnAPage = (int)((lPageHeight - 2.0D * TITLE_SCALE * lFontHeight - lHeaderHeight * lScale) / Math.ceil(lOneRowHeight * lScale) - 1.0D);
/*     */ 
/* 391 */     int lTotalNumPages = (this.tabla.getRowCount() - 1) / lNumRowsOnAPage + 1;
/*     */ 
/* 394 */     if (pPageIndex >= lTotalNumPages) {
/* 395 */       return 1;
/*     */     }
/*     */ 
/* 399 */     lGraphics2D.translate(pPageFormat.getImageableX(), pPageFormat.getImageableY());
/*     */ 
/* 403 */     printTitle(lGraphics2D, pPageFormat, pPageIndex == 0);
/*     */ 
/* 407 */     if (lScale >= 1.0D) {
/* 408 */       lGraphics2D.translate((lPageWidth - lTableWidth) / 2.0F, 0.0D);
/*     */     }
/* 410 */     lGraphics2D.translate(0.0D, 2 * lFontHeight);
/*     */ 
/* 415 */     lGraphics2D.scale(lScale, lScale);
/* 416 */     lGraphics2D.setClip(0, 0, lTableWidth, lHeaderHeight);
/*     */ 
/* 421 */     this.tabla.getTableHeader().paint(lGraphics2D);
/* 422 */     lGraphics2D.drawLine(0, 0, 0, lHeaderHeight);
/*     */ 
/* 430 */     int lPageHeightForTable = lOneRowHeight * lNumRowsOnAPage;
/* 431 */     lGraphics2D.translate(0.0D, lHeaderHeight);
/* 432 */     lGraphics2D.translate(0.0D, -pPageIndex * lPageHeightForTable);
/* 433 */     lGraphics2D.setClip(0, pPageIndex * lPageHeightForTable, lTableWidth, lPageHeightForTable);
/*     */ 
/* 439 */     int lThisPageRows = Math.min(this.tabla.getRowCount() - pPageIndex * lNumRowsOnAPage, lNumRowsOnAPage);
/*     */ 
/* 442 */     int lThisPageHeight = lThisPageRows * lOneRowHeight;
/* 443 */     this.tabla.paint(lGraphics2D);
/* 444 */     lGraphics2D.drawLine(0, pPageIndex * lPageHeightForTable, 0, pPageIndex * lPageHeightForTable + lThisPageHeight - 1);
/*     */ 
/* 450 */     return 0;
/*     */   }
/*     */ 
/*     */   private void printTitle(Graphics2D pGraphics, PageFormat pPageFormat, boolean pIsFirstPage)
/*     */   {
/* 464 */     String lTitle = this.cuenta + " : " + this.nombreCuenta;
/* 465 */     if (!pIsFirstPage)
/*     */     {
/* 467 */       lTitle = lTitle + " (Continued)";
/*     */     }
/*     */ 
/* 472 */     pGraphics.scale(TITLE_SCALE, TITLE_SCALE);
/* 473 */     TextLayout lLayout = new TextLayout(lTitle, pGraphics.getFont(), pGraphics.getFontRenderContext());
/*     */ 
/* 477 */     Rectangle2D lTextSize = lLayout.getBounds();
/* 478 */     int lTextWidth = (int)lTextSize.getWidth();
/* 479 */     int lTextBase = (int)lTextSize.getHeight();
/* 480 */     pGraphics.drawString(lTitle, (int)(pPageFormat.getImageableWidth() / 2.0D / TITLE_SCALE) - lTextWidth / 2, lTextBase);
/*     */ 
/* 484 */     pGraphics.scale(1.0D / TITLE_SCALE, 1.0D / TITLE_SCALE);
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.cuentas.TablaDatosMensuales
 * JD-Core Version:    0.6.2
 */