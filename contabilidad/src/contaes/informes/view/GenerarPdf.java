/*     */ package contaes.informes.view;
/*     */ 
/*     */ import com.lowagie.text.Document;
/*     */ import com.lowagie.text.DocumentException;
/*     */ import com.lowagie.text.ExceptionConverter;
/*     */ import com.lowagie.text.Font;
/*     */ import com.lowagie.text.FontFactory;
/*     */ import com.lowagie.text.Paragraph;
/*     */ import com.lowagie.text.Rectangle;
/*     */ import com.lowagie.text.pdf.BaseFont;
/*     */ import com.lowagie.text.pdf.PdfContentByte;
/*     */ import com.lowagie.text.pdf.PdfPCell;
/*     */ import com.lowagie.text.pdf.PdfPTable;
/*     */ import com.lowagie.text.pdf.PdfPageEventHelper;
/*     */ import com.lowagie.text.pdf.PdfTemplate;
/*     */ import com.lowagie.text.pdf.PdfWriter;
/*     */ import contaes.Inicio;
/*     */ import contaes.Puente;
/*     */ import contaes.auxiliarTablas.TableSorter;
/*     */ import contaes.informes.model.ContrapartidasTableModel;
/*     */ import contaes.informes.model.DistribucionPartidasTableModel;
/*     */ import contaes.informes.model.RatiosTableModel;
/*     */ import contaes.informes.model.ResumenFacturasTableModel;
/*     */ import contaes.manejoDatos.ManejoEmpresas;
/*     */ import contaes.manejoDatos.TipoSubcuenta;
/*     */ import java.awt.Color;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.DecimalFormatSymbols;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.swing.JFileChooser;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JOptionPane;
/*     */ 
/*     */ public class GenerarPdf extends PdfPageEventHelper
/*     */ {
/*  49 */   private float ANCHOTABLAREGULAR = 100.0F;
/*     */   private PdfTemplate total;
/*     */   private BaseFont helv;
/*  53 */   private Font helvetica9 = FontFactory.getFont("Helvetica", 9.0F);
/*  54 */   private Font helvetica8 = FontFactory.getFont("Helvetica", 8.0F);
/*     */   private String datos;
/*     */   private String titulo1;
/*     */   private String titulo2;
/*     */   private DecimalFormat fn;
/*     */   JFrame marcoPadre;
/*     */ 
/*     */   public GenerarPdf(JFrame marcoPadre)
/*     */   {
/*  65 */     this.marcoPadre = marcoPadre;
/*  66 */     inicializar();
/*     */   }
/*     */ 
/*     */   public void setTitulos(String titulo1, String titulo2) {
/*  70 */     this.titulo1 = titulo1;
/*  71 */     this.titulo2 = titulo2;
/*     */   }
/*     */ 
/*     */   private void inicializar() {
/*  75 */     DecimalFormatSymbols formato = new DecimalFormatSymbols();
/*  76 */     formato.setDecimalSeparator(',');
/*  77 */     formato.setPerMill('.');
/*  78 */     this.fn = new DecimalFormat("#,###,##0.00", formato);
/*     */ 
/*  80 */     ManejoEmpresas mE = new ManejoEmpresas(Inicio.getCGeneral(), Inicio.p.getEmpresa());
/*  81 */     String nuestroNombre = mE.getNombre(Inicio.p.getEmpresa());
/*  82 */     this.datos = nuestroNombre;
/*     */ 
/*  84 */     this.titulo1 = "";
/*  85 */     this.titulo2 = "";
/*     */   }
/*     */ 
/*     */   public void generarDocumento(float[] tamColums, TableSorter tabla, int modelo)
/*     */   {
/*  92 */     File archivo = obtenerArchivo();
/*  93 */     if (archivo != null)
/*     */       try {
/*  95 */         Document document = new Document();
/*  96 */         PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(archivo));
/*  97 */         writer.setPageEvent(this);
/*  98 */         document.open();
/*  99 */         document.setMargins(72.0F, 72.0F, 72.0F, 72.0F);
/* 100 */         switch (modelo) {
/*     */         case 0:
/* 102 */           ContrapartidasTableModel modeloContrapartidas = (ContrapartidasTableModel)tabla.getTableModel();
/* 103 */           generarTablaPdf(document, tamColums, modeloContrapartidas);
/* 104 */           break;
/*     */         case 1:
/* 106 */           DistribucionPartidasTableModel modeloDistribucion = (DistribucionPartidasTableModel)tabla.getTableModel();
/* 107 */           generarTablaPdf(document, tamColums, modeloDistribucion);
/* 108 */           break;
/*     */         case 2:
/* 110 */           RatiosTableModel modeloRatios = (RatiosTableModel)tabla.getTableModel();
/* 111 */           generarTablaPdf(document, tamColums, modeloRatios);
/* 112 */           break;
/*     */         case 3:
/* 114 */           ResumenFacturasTableModel modeloFacturas = (ResumenFacturasTableModel)tabla.getTableModel();
/* 115 */           generarTablaPdf(document, tamColums, modeloFacturas);
/*     */         }
/*     */ 
/* 119 */         document.close();
/* 120 */         JOptionPane.showMessageDialog(this.marcoPadre, "Informe generado en " + archivo.getAbsolutePath(), "", 1);
/*     */       } catch (DocumentException ex) {
/* 122 */         Logger.getLogger(GenerarPdf.class.getName()).log(Level.SEVERE, null, ex);
/*     */       } catch (IOException ex) {
/* 124 */         Logger.getLogger(GenerarPdf.class.getName()).log(Level.SEVERE, null, ex);
/*     */       } catch (Exception ex) {
/* 126 */         Logger.getLogger(GenerarPdf.class.getName()).log(Level.SEVERE, null, ex);
/*     */       }
/*     */   }
/*     */ 
/*     */   public void generarDocumento(float[] tamColums, ContrapartidasTableModel modeloTabla)
/*     */   {
/* 132 */     File archivo = obtenerArchivo();
/* 133 */     if (archivo != null)
/*     */       try {
/* 135 */         Document document = new Document();
/* 136 */         PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(archivo));
/* 137 */         writer.setPageEvent(this);
/* 138 */         document.open();
/* 139 */         document.setMargins(72.0F, 72.0F, 72.0F, 72.0F);
/* 140 */         generarTablaPdf(document, tamColums, modeloTabla);
/*     */ 
/* 142 */         document.close();
/* 143 */         JOptionPane.showMessageDialog(this.marcoPadre, "Informe generado en " + archivo.getAbsolutePath(), "", 1);
/*     */       } catch (DocumentException ex) {
/* 145 */         Logger.getLogger(GenerarPdf.class.getName()).log(Level.SEVERE, null, ex);
/*     */       } catch (IOException ex) {
/* 147 */         Logger.getLogger(GenerarPdf.class.getName()).log(Level.SEVERE, null, ex);
/*     */       } catch (Exception ex) {
/* 149 */         Logger.getLogger(GenerarPdf.class.getName()).log(Level.SEVERE, null, ex);
/*     */       }
/*     */   }
/*     */ 
/*     */   public void generarDocumento(float[] tamColums, DistribucionPartidasTableModel modeloTabla)
/*     */   {
/* 155 */     File archivo = obtenerArchivo();
/* 156 */     if (archivo != null)
/*     */       try {
/* 158 */         Document document = new Document();
/* 159 */         PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(archivo));
/* 160 */         writer.setPageEvent(this);
/* 161 */         document.open();
/* 162 */         document.setMargins(72.0F, 72.0F, 72.0F, 72.0F);
/* 163 */         generarTablaPdf(document, tamColums, modeloTabla);
/*     */ 
/* 165 */         document.close();
/* 166 */         JOptionPane.showMessageDialog(this.marcoPadre, "Informe generado en " + archivo.getAbsolutePath(), "", 1);
/*     */       } catch (DocumentException ex) {
/* 168 */         Logger.getLogger(GenerarPdf.class.getName()).log(Level.SEVERE, null, ex);
/*     */       } catch (IOException ex) {
/* 170 */         Logger.getLogger(GenerarPdf.class.getName()).log(Level.SEVERE, null, ex);
/*     */       } catch (Exception ex) {
/* 172 */         Logger.getLogger(GenerarPdf.class.getName()).log(Level.SEVERE, null, ex);
/*     */       }
/*     */   }
/*     */ 
/*     */   public void generarDocumento(float[] tamColums, RatiosTableModel modeloTabla)
/*     */   {
/* 178 */     File archivo = obtenerArchivo();
/* 179 */     if (archivo != null)
/*     */       try {
/* 181 */         Document document = new Document();
/* 182 */         PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(archivo));
/* 183 */         writer.setPageEvent(this);
/* 184 */         document.open();
/* 185 */         document.setMargins(72.0F, 72.0F, 72.0F, 72.0F);
/* 186 */         generarTablaPdf(document, tamColums, modeloTabla);
/*     */ 
/* 188 */         document.close();
/* 189 */         JOptionPane.showMessageDialog(this.marcoPadre, "Informe generado en " + archivo.getAbsolutePath(), "", 1);
/*     */       } catch (DocumentException ex) {
/* 191 */         Logger.getLogger(GenerarPdf.class.getName()).log(Level.SEVERE, null, ex);
/*     */       } catch (IOException ex) {
/* 193 */         Logger.getLogger(GenerarPdf.class.getName()).log(Level.SEVERE, null, ex);
/*     */       } catch (Exception ex) {
/* 195 */         Logger.getLogger(GenerarPdf.class.getName()).log(Level.SEVERE, null, ex);
/*     */       }
/*     */   }
/*     */ 
/*     */   public void generarDocumento(float[] tamColums, ResumenFacturasTableModel modeloTabla)
/*     */   {
/* 201 */     File archivo = obtenerArchivo();
/* 202 */     if (archivo != null)
/*     */       try {
/* 204 */         Document document = new Document();
/* 205 */         PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(archivo));
/* 206 */         writer.setPageEvent(this);
/* 207 */         document.open();
/* 208 */         document.setMargins(72.0F, 72.0F, 72.0F, 72.0F);
/* 209 */         generarTablaPdf(document, tamColums, modeloTabla);
/*     */ 
/* 211 */         document.close();
/* 212 */         JOptionPane.showMessageDialog(this.marcoPadre, "Informe generado en " + archivo.getAbsolutePath(), "", 1);
/*     */       } catch (DocumentException ex) {
/* 214 */         Logger.getLogger(GenerarPdf.class.getName()).log(Level.SEVERE, null, ex);
/*     */       } catch (IOException ex) {
/* 216 */         Logger.getLogger(GenerarPdf.class.getName()).log(Level.SEVERE, null, ex);
/*     */       } catch (Exception ex) {
/* 218 */         Logger.getLogger(GenerarPdf.class.getName()).log(Level.SEVERE, null, ex);
/*     */       }
/*     */   }
/*     */ 
/*     */   private File obtenerArchivo()
/*     */   {
/* 224 */     File archivo = null;
/* 225 */     JFileChooser fc = new JFileChooser();
/* 226 */     int retorno = fc.showSaveDialog(this.marcoPadre);
/* 227 */     if (retorno == 0) {
/* 228 */       archivo = fc.getSelectedFile();
/*     */     }
/* 230 */     return archivo;
/*     */   }
/*     */ 
/*     */   private void generarTablaPdf(Document document, float[] tamColums, ContrapartidasTableModel modeloTabla) {
/*     */     try {
/* 235 */       int numColumns = modeloTabla.getColumnCount();
/* 236 */       PdfPTable header = new PdfPTable(tamColums);
/* 237 */       for (int i = 0; i < numColumns; i++) {
/* 238 */         header.addCell(formarCell(new Paragraph(modeloTabla.getColumnName(i), this.helvetica9), 1, 0, true));
/*     */       }
/* 240 */       header.setWidthPercentage(this.ANCHOTABLAREGULAR);
/* 241 */       document.add(header);
/*     */ 
/* 243 */       PdfPTable body = new PdfPTable(tamColums);
/*     */ 
/* 246 */       for (int i = 0; i < modeloTabla.getRowCount(); i++) {
/* 247 */         TipoSubcuenta subcuenta = (TipoSubcuenta)modeloTabla.getValueAt(i, 0);
/* 248 */         Double importe = (Double)modeloTabla.getValueAt(i, 1);
/* 249 */         body.addCell(formarCell(new Paragraph(subcuenta.toString(), this.helvetica9), 1, 0, true));
/* 250 */         body.addCell(formarCell(new Paragraph(this.fn.format(importe), this.helvetica9), 1, 2, true));
/*     */       }
/* 252 */       body.setWidthPercentage(this.ANCHOTABLAREGULAR);
/* 253 */       document.add(body);
/*     */     }
/*     */     catch (DocumentException ex) {
/* 256 */       Logger.getLogger(GenerarPdf.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void generarTablaPdf(Document document, float[] tamColums, DistribucionPartidasTableModel modeloTabla) {
/*     */     try {
/* 262 */       int numColumns = modeloTabla.getColumnCount();
/* 263 */       PdfPTable header = new PdfPTable(tamColums);
/* 264 */       for (int i = 0; i < numColumns; i++) {
/* 265 */         header.addCell(formarCell(new Paragraph(modeloTabla.getColumnName(i), this.helvetica9), 1, 0, true));
/*     */       }
/* 267 */       header.setWidthPercentage(this.ANCHOTABLAREGULAR);
/* 268 */       document.add(header);
/*     */ 
/* 270 */       PdfPTable body = new PdfPTable(tamColums);
/*     */ 
/* 274 */       for (int i = 0; i < modeloTabla.getRowCount(); i++) {
/* 275 */         String nombre = (String)modeloTabla.getValueAt(i, 0);
/* 276 */         Double importe = (Double)modeloTabla.getValueAt(i, 1);
/* 277 */         Double porcentaje = (Double)modeloTabla.getValueAt(i, 2);
/* 278 */         body.addCell(formarCell(new Paragraph(nombre, this.helvetica9), 1, 0, true));
/* 279 */         body.addCell(formarCell(new Paragraph(this.fn.format(importe), this.helvetica9), 1, 2, true));
/* 280 */         body.addCell(formarCell(new Paragraph(this.fn.format(porcentaje), this.helvetica9), 1, 2, true));
/*     */       }
/* 282 */       body.setWidthPercentage(this.ANCHOTABLAREGULAR);
/* 283 */       document.add(body);
/*     */     }
/*     */     catch (DocumentException ex) {
/* 286 */       Logger.getLogger(GenerarPdf.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void generarTablaPdf(Document document, float[] tamColums, RatiosTableModel modeloTabla) {
/*     */     try {
/* 292 */       int numColumns = modeloTabla.getColumnCount();
/* 293 */       PdfPTable header = new PdfPTable(tamColums);
/* 294 */       for (int i = 0; i < numColumns; i++) {
/* 295 */         header.addCell(formarCell(new Paragraph(modeloTabla.getColumnName(i), this.helvetica9), 1, 0, true));
/*     */       }
/* 297 */       header.setWidthPercentage(this.ANCHOTABLAREGULAR);
/* 298 */       document.add(header);
/*     */ 
/* 300 */       PdfPTable body = new PdfPTable(tamColums);
/*     */ 
/* 304 */       for (int i = 0; i < modeloTabla.getRowCount(); i++) {
/* 305 */         String nombre = (String)modeloTabla.getValueAt(i, 0);
/* 306 */         Double actual = (Double)modeloTabla.getValueAt(i, 1);
/* 307 */         Double anterior = (Double)modeloTabla.getValueAt(i, 2);
/* 308 */         body.addCell(formarCell(new Paragraph(nombre, this.helvetica9), 1, 0, true));
/* 309 */         body.addCell(formarCell(new Paragraph(this.fn.format(actual), this.helvetica9), 1, 2, true));
/* 310 */         body.addCell(formarCell(new Paragraph(this.fn.format(anterior), this.helvetica9), 1, 2, true));
/*     */       }
/* 312 */       body.setWidthPercentage(this.ANCHOTABLAREGULAR);
/* 313 */       document.add(body);
/*     */     }
/*     */     catch (DocumentException ex) {
/* 316 */       Logger.getLogger(GenerarPdf.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void generarTablaPdf(Document document, float[] tamColums, ResumenFacturasTableModel modeloTabla) {
/*     */     try {
/* 322 */       int numColumns = modeloTabla.getColumnCount();
/* 323 */       PdfPTable header = new PdfPTable(tamColums);
/* 324 */       for (int i = 0; i < numColumns; i++) {
/* 325 */         header.addCell(formarCell(new Paragraph(modeloTabla.getColumnName(i), this.helvetica9), 1, 0, true));
/*     */       }
/* 327 */       header.setWidthPercentage(this.ANCHOTABLAREGULAR);
/* 328 */       document.add(header);
/*     */ 
/* 330 */       PdfPTable body = new PdfPTable(tamColums);
/*     */ 
/* 334 */       for (int i = 0; i < modeloTabla.getRowCount(); i++) {
/* 335 */         String mes = (String)modeloTabla.getValueAt(i, 0);
/* 336 */         String nombre = (String)modeloTabla.getValueAt(i, 1);
/* 337 */         Double importe = (Double)modeloTabla.getValueAt(i, 2);
/* 338 */         body.addCell(formarCell(new Paragraph(mes, this.helvetica9), 1, 0, true));
/* 339 */         body.addCell(formarCell(new Paragraph(nombre, this.helvetica9), 1, 0, true));
/* 340 */         body.addCell(formarCell(new Paragraph(this.fn.format(importe), this.helvetica9), 1, 2, true));
/*     */       }
/* 342 */       body.setWidthPercentage(this.ANCHOTABLAREGULAR);
/* 343 */       document.add(body);
/*     */     }
/*     */     catch (DocumentException ex) {
/* 346 */       Logger.getLogger(GenerarPdf.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void cabecera(Document document)
/*     */     throws DocumentException
/*     */   {
/* 450 */     PdfPTable tabla = new PdfPTable(1);
/* 451 */     Paragraph par = null;
/* 452 */     PdfPCell cell = null;
/*     */ 
/* 454 */     par = formarParagraph(this.datos, this.helvetica9, 0);
/* 455 */     cell = formarCell(par, 1, 0, false);
/* 456 */     tabla.addCell(cell);
/*     */ 
/* 458 */     par = formarParagraph(this.titulo1, this.helvetica9, 0);
/* 459 */     cell = formarCell(par, 1, 0, false);
/* 460 */     tabla.addCell(cell);
/*     */ 
/* 462 */     par = formarParagraph(this.titulo2, this.helvetica9, 0);
/* 463 */     cell = formarCell(par, 1, 0, false);
/* 464 */     tabla.addCell(cell);
/*     */ 
/* 466 */     document.add(tabla);
/*     */ 
/* 468 */     document.add(new Paragraph("\n"));
/*     */   }
/*     */ 
/*     */   private Paragraph formarParagraph(String string, Font fuente, int align) {
/* 472 */     Paragraph par = new Paragraph(string, fuente);
/*     */ 
/* 474 */     par.setAlignment(align);
/* 475 */     return par;
/*     */   }
/*     */ 
/*     */   private PdfPCell formarCell(Paragraph par, int alignVertical, int alignHorizontal, boolean border)
/*     */   {
/* 487 */     PdfPCell cell = new PdfPCell();
/*     */ 
/* 489 */     if (!border) {
/* 490 */       cell.setBorder(0);
/*     */     }
/* 492 */     par.setAlignment(alignHorizontal);
/* 493 */     cell.addElement(par);
/* 494 */     cell.setBackgroundColor(Color.WHITE);
/* 495 */     cell.setHorizontalAlignment(alignHorizontal);
/* 496 */     cell.setVerticalAlignment(alignVertical);
/* 497 */     return cell;
/*     */   }
/*     */ 
/*     */   public void onOpenDocument(PdfWriter writer, Document document)
/*     */   {
/* 502 */     this.total = writer.getDirectContent().createTemplate(100.0F, 100.0F);
/* 503 */     this.total.setBoundingBox(new Rectangle(-20.0F, -20.0F, 100.0F, 100.0F));
/*     */     try {
/* 505 */       this.helv = BaseFont.createFont("Helvetica", "Cp1252", false);
/*     */     }
/*     */     catch (Exception e) {
/* 508 */       throw new ExceptionConverter(e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void onStartPage(PdfWriter writer, Document document)
/*     */   {
/*     */     try {
/* 515 */       cabecera(document);
/*     */     } catch (Exception ex) {
/* 517 */       Logger.getLogger(GenerarPdf.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void onEndPage(PdfWriter writer, Document document)
/*     */   {
/* 523 */     PdfContentByte cb = writer.getDirectContent();
/* 524 */     cb.saveState();
/* 525 */     String text = "Página " + writer.getPageNumber() + " de ";
/* 526 */     float textBase = document.bottom();
/* 527 */     float textSize = this.helv.getWidthPoint(text, 8.0F);
/* 528 */     cb.beginText();
/* 529 */     cb.setFontAndSize(this.helv, 8.0F);
/*     */ 
/* 531 */     cb.setTextMatrix(document.left(), textBase);
/* 532 */     cb.showText(text);
/* 533 */     cb.endText();
/* 534 */     cb.addTemplate(this.total, document.left() + textSize, textBase);
/*     */ 
/* 536 */     cb.restoreState();
/*     */   }
/*     */ 
/*     */   public void onCloseDocument(PdfWriter writer, Document document)
/*     */   {
/* 541 */     this.total.beginText();
/* 542 */     this.total.setFontAndSize(this.helv, 8.0F);
/* 543 */     this.total.setTextMatrix(0.0F, 0.0F);
/* 544 */     this.total.showText(String.valueOf(writer.getPageNumber() - 1));
/* 545 */     this.total.endText();
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.informes.view.GenerarPdf
 * JD-Core Version:    0.6.2
 */