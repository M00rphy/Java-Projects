/*     */ package almacen2.gui.listados;
/*     */ 
/*     */ import almacen2.data.listados.AgrupadoTableModel;
/*     */ import almacen2.data.listados.CVTableModel;
/*     */ import almacen2.data.listados.ExistenciasTableModel;
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
/*     */ import contaes.manejoDatos.ManejoEmpresas;
/*     */ import contaes.manejoDatos.auxiliar.AddExtension;
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
/*  47 */   private float ANCHOTABLAREGULAR = 100.0F;
/*     */   private PdfTemplate total;
/*     */   private BaseFont helv;
/*  51 */   private Font helvetica9 = FontFactory.getFont("Helvetica", 9.0F);
/*  52 */   private Font helvetica8 = FontFactory.getFont("Helvetica", 8.0F);
/*     */   private String datos;
/*     */   private String titulo1;
/*     */   private String titulo2;
/*     */   private DecimalFormat fn;
/*     */   JFrame marcoPadre;
/*     */ 
/*     */   public GenerarPdf(JFrame marcoPadre)
/*     */   {
/*  63 */     this.marcoPadre = marcoPadre;
/*  64 */     inicializar();
/*     */   }
/*     */ 
/*     */   public void setTitulos(String titulo1, String titulo2) {
/*  68 */     this.titulo1 = titulo1;
/*  69 */     this.titulo2 = titulo2;
/*     */   }
/*     */ 
/*     */   private void inicializar() {
/*  73 */     DecimalFormatSymbols formato = new DecimalFormatSymbols();
/*  74 */     formato.setDecimalSeparator(',');
/*  75 */     formato.setPerMill('.');
/*  76 */     this.fn = new DecimalFormat("#,###,##0.00", formato);
/*     */ 
/*  78 */     ManejoEmpresas mE = new ManejoEmpresas(Inicio.getCGeneral(), Inicio.p.getEmpresa());
/*  79 */     String nuestroNombre = mE.getNombre(Inicio.p.getEmpresa());
/*  80 */     this.datos = nuestroNombre;
/*     */ 
/*  82 */     this.titulo1 = "";
/*  83 */     this.titulo2 = "";
/*     */   }
/*     */ 
/*     */   public void generarDocumento(float[] tamColums, AgrupadoTableModel modeloTabla) {
/*  87 */     File archivo = obtenerArchivo();
/*  88 */     if (archivo != null)
/*     */       try {
/*  90 */         Document document = new Document();
/*  91 */         PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(archivo));
/*  92 */         writer.setPageEvent(this);
/*  93 */         document.open();
/*  94 */         document.setMargins(72.0F, 72.0F, 72.0F, 72.0F);
/*  95 */         generarTablaPdf(document, tamColums, modeloTabla);
/*     */ 
/*  97 */         document.close();
/*  98 */         JOptionPane.showMessageDialog(this.marcoPadre, "Informe generado en " + archivo.getAbsolutePath(), "", 1);
/*     */       } catch (DocumentException ex) {
/* 100 */         Logger.getLogger(GenerarPdf.class.getName()).log(Level.SEVERE, null, ex);
/*     */       } catch (IOException ex) {
/* 102 */         Logger.getLogger(GenerarPdf.class.getName()).log(Level.SEVERE, null, ex);
/*     */       } catch (Exception ex) {
/* 104 */         Logger.getLogger(GenerarPdf.class.getName()).log(Level.SEVERE, null, ex);
/*     */       }
/*     */   }
/*     */ 
/*     */   public void generarDocumento(float[] tamColums, CVTableModel modeloTabla)
/*     */   {
/* 110 */     File archivo = obtenerArchivo();
/* 111 */     if (archivo != null)
/*     */       try {
/* 113 */         Document document = new Document();
/* 114 */         PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(archivo));
/* 115 */         writer.setPageEvent(this);
/* 116 */         document.open();
/* 117 */         document.setMargins(72.0F, 72.0F, 72.0F, 72.0F);
/* 118 */         generarTablaPdf(document, tamColums, modeloTabla);
/*     */ 
/* 120 */         document.close();
/* 121 */         JOptionPane.showMessageDialog(this.marcoPadre, "Informe generado en " + archivo.getAbsolutePath(), "", 1);
/*     */       } catch (DocumentException ex) {
/* 123 */         Logger.getLogger(GenerarPdf.class.getName()).log(Level.SEVERE, null, ex);
/*     */       } catch (IOException ex) {
/* 125 */         Logger.getLogger(GenerarPdf.class.getName()).log(Level.SEVERE, null, ex);
/*     */       } catch (Exception ex) {
/* 127 */         Logger.getLogger(GenerarPdf.class.getName()).log(Level.SEVERE, null, ex);
/*     */       }
/*     */   }
/*     */ 
/*     */   public void generarDocumento(float[] tamColums, ExistenciasTableModel modeloTabla)
/*     */   {
/* 133 */     File archivo = obtenerArchivo();
/* 134 */     if (archivo != null)
/*     */       try {
/* 136 */         Document document = new Document();
/* 137 */         PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(archivo));
/* 138 */         writer.setPageEvent(this);
/* 139 */         document.open();
/* 140 */         document.setMargins(72.0F, 72.0F, 72.0F, 72.0F);
/* 141 */         generarTablaPdf(document, tamColums, modeloTabla);
/*     */ 
/* 143 */         document.close();
/* 144 */         JOptionPane.showMessageDialog(this.marcoPadre, "Informe generado en " + archivo.getAbsolutePath(), "", 1);
/*     */       } catch (DocumentException ex) {
/* 146 */         Logger.getLogger(GenerarPdf.class.getName()).log(Level.SEVERE, null, ex);
/*     */       } catch (IOException ex) {
/* 148 */         Logger.getLogger(GenerarPdf.class.getName()).log(Level.SEVERE, null, ex);
/*     */       } catch (Exception ex) {
/* 150 */         Logger.getLogger(GenerarPdf.class.getName()).log(Level.SEVERE, null, ex);
/*     */       }
/*     */   }
/*     */ 
/*     */   private File obtenerArchivo()
/*     */   {
/* 156 */     File archivo = null;
/* 157 */     JFileChooser fc = new JFileChooser();
/* 158 */     if (!this.titulo1.equals("")) {
/* 159 */       fc.setSelectedFile(new File(this.titulo1 + ".pdf"));
/*     */     }
/* 161 */     int retorno = fc.showSaveDialog(this.marcoPadre);
/* 162 */     if (retorno == 0) {
/* 163 */       archivo = fc.getSelectedFile();
/* 164 */       archivo = AddExtension.pdf(archivo);
/*     */     }
/* 166 */     return archivo;
/*     */   }
/*     */ 
/*     */   private void generarTablaPdf(Document document, float[] tamColums, AgrupadoTableModel modeloTabla) {
/*     */     try {
/* 171 */       int numColumns = modeloTabla.getColumnCount();
/* 172 */       PdfPTable header = new PdfPTable(tamColums);
/* 173 */       for (int i = 0; i < numColumns; i++) {
/* 174 */         header.addCell(formarCell(new Paragraph(modeloTabla.getColumnName(i), this.helvetica9), 1, 0, true));
/*     */       }
/* 176 */       header.setWidthPercentage(this.ANCHOTABLAREGULAR);
/* 177 */       document.add(header);
/*     */ 
/* 179 */       PdfPTable body = new PdfPTable(tamColums);
/*     */ 
/* 185 */       for (int i = 0; i < modeloTabla.getRowCount(); i++) {
/* 186 */         String nombre = (String)modeloTabla.getValueAt(i, 0);
/* 187 */         Double unidadesCompra = (Double)modeloTabla.getValueAt(i, 1);
/* 188 */         Double totalCompra = (Double)modeloTabla.getValueAt(i, 2);
/* 189 */         Double unidadesVenta = (Double)modeloTabla.getValueAt(i, 3);
/* 190 */         Double totalVenta = (Double)modeloTabla.getValueAt(i, 4);
/*     */ 
/* 192 */         body.addCell(formarCell(new Paragraph(nombre, this.helvetica9), 1, 0, true));
/* 193 */         body.addCell(formarCell(new Paragraph(this.fn.format(unidadesCompra), this.helvetica9), 1, 2, true));
/* 194 */         body.addCell(formarCell(new Paragraph(this.fn.format(totalCompra), this.helvetica9), 1, 2, true));
/* 195 */         body.addCell(formarCell(new Paragraph(this.fn.format(unidadesVenta), this.helvetica9), 1, 2, true));
/* 196 */         body.addCell(formarCell(new Paragraph(this.fn.format(totalVenta), this.helvetica9), 1, 2, true));
/*     */       }
/* 198 */       body.setWidthPercentage(this.ANCHOTABLAREGULAR);
/* 199 */       document.add(body);
/*     */     }
/*     */     catch (DocumentException ex) {
/* 202 */       Logger.getLogger(GenerarPdf.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void generarTablaPdf(Document document, float[] tamColums, CVTableModel modeloTabla) {
/*     */     try {
/* 208 */       int numColumns = modeloTabla.getColumnCount();
/* 209 */       PdfPTable header = new PdfPTable(tamColums);
/* 210 */       for (int i = 0; i < numColumns; i++) {
/* 211 */         header.addCell(formarCell(new Paragraph(modeloTabla.getColumnName(i), this.helvetica9), 1, 0, true));
/*     */       }
/* 213 */       header.setWidthPercentage(this.ANCHOTABLAREGULAR);
/* 214 */       document.add(header);
/*     */ 
/* 216 */       PdfPTable body = new PdfPTable(tamColums);
/*     */ 
/* 223 */       for (int i = 0; i < modeloTabla.getRowCount(); i++) {
/* 224 */         String fecha = (String)modeloTabla.getValueAt(i, 0);
/* 225 */         String referencia = (String)modeloTabla.getValueAt(i, 1);
/* 226 */         String descripcion = (String)modeloTabla.getValueAt(i, 2);
/* 227 */         Integer es = (Integer)modeloTabla.getValueAt(i, 3);
/* 228 */         double importe = ((Double)modeloTabla.getValueAt(i, 4)).doubleValue();
/* 229 */         double pLista = ((Double)modeloTabla.getValueAt(i, 5)).doubleValue();
/* 230 */         body.addCell(formarCell(new Paragraph(fecha, this.helvetica9), 1, 0, true));
/* 231 */         body.addCell(formarCell(new Paragraph(referencia, this.helvetica9), 1, 0, true));
/* 232 */         body.addCell(formarCell(new Paragraph(descripcion, this.helvetica9), 1, 0, true));
/* 233 */         body.addCell(formarCell(new Paragraph(es.toString(), this.helvetica9), 1, 2, true));
/* 234 */         body.addCell(formarCell(new Paragraph(this.fn.format(importe), this.helvetica9), 1, 2, true));
/* 235 */         body.addCell(formarCell(new Paragraph(this.fn.format(pLista), this.helvetica9), 1, 2, true));
/*     */       }
/* 237 */       body.setWidthPercentage(this.ANCHOTABLAREGULAR);
/* 238 */       document.add(body);
/*     */     }
/*     */     catch (DocumentException ex) {
/* 241 */       Logger.getLogger(GenerarPdf.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void generarTablaPdf(Document document, float[] tamColums, ExistenciasTableModel modeloTabla) {
/*     */     try {
/* 247 */       int numColumns = modeloTabla.getColumnCount();
/* 248 */       PdfPTable header = new PdfPTable(tamColums);
/* 249 */       for (int i = 0; i < numColumns; i++) {
/* 250 */         header.addCell(formarCell(new Paragraph(modeloTabla.getColumnName(i), this.helvetica9), 1, 0, true));
/*     */       }
/* 252 */       header.setWidthPercentage(this.ANCHOTABLAREGULAR);
/* 253 */       document.add(header);
/*     */ 
/* 255 */       PdfPTable body = new PdfPTable(tamColums);
/*     */ 
/* 260 */       for (int i = 0; i < modeloTabla.getRowCount(); i++) {
/* 261 */         String referencia = (String)modeloTabla.getValueAt(i, 0);
/* 262 */         String descripcion = (String)modeloTabla.getValueAt(i, 1);
/* 263 */         Integer unidades = (Integer)modeloTabla.getValueAt(i, 2);
/* 264 */         double coste = ((Double)modeloTabla.getValueAt(i, 3)).doubleValue();
/* 265 */         body.addCell(formarCell(new Paragraph(referencia, this.helvetica9), 1, 0, true));
/* 266 */         body.addCell(formarCell(new Paragraph(descripcion, this.helvetica9), 1, 0, true));
/* 267 */         body.addCell(formarCell(new Paragraph(unidades.toString(), this.helvetica9), 1, 2, true));
/* 268 */         body.addCell(formarCell(new Paragraph(this.fn.format(coste), this.helvetica9), 1, 2, true));
/*     */       }
/* 270 */       body.setWidthPercentage(this.ANCHOTABLAREGULAR);
/* 271 */       document.add(body);
/*     */     }
/*     */     catch (DocumentException ex) {
/* 274 */       Logger.getLogger(GenerarPdf.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void cabecera(Document document)
/*     */     throws DocumentException
/*     */   {
/* 282 */     PdfPTable tabla = new PdfPTable(1);
/* 283 */     Paragraph par = null;
/* 284 */     PdfPCell cell = null;
/*     */ 
/* 286 */     par = formarParagraph(this.datos, this.helvetica9, 0);
/* 287 */     cell = formarCell(par, 1, 0, false);
/* 288 */     tabla.addCell(cell);
/*     */ 
/* 290 */     par = formarParagraph(this.titulo1, this.helvetica9, 0);
/* 291 */     cell = formarCell(par, 1, 0, false);
/* 292 */     tabla.addCell(cell);
/*     */ 
/* 294 */     par = formarParagraph(this.titulo2, this.helvetica9, 0);
/* 295 */     cell = formarCell(par, 1, 0, false);
/* 296 */     tabla.addCell(cell);
/*     */ 
/* 298 */     document.add(tabla);
/*     */ 
/* 300 */     document.add(new Paragraph("\n"));
/*     */   }
/*     */ 
/*     */   private Paragraph formarParagraph(String string, Font fuente, int align) {
/* 304 */     Paragraph par = new Paragraph(string, fuente);
/*     */ 
/* 306 */     par.setAlignment(align);
/* 307 */     return par;
/*     */   }
/*     */ 
/*     */   private PdfPCell formarCell(Paragraph par, int alignVertical, int alignHorizontal, boolean border)
/*     */   {
/* 319 */     PdfPCell cell = new PdfPCell();
/*     */ 
/* 321 */     if (!border) {
/* 322 */       cell.setBorder(0);
/*     */     }
/* 324 */     par.setAlignment(alignHorizontal);
/* 325 */     cell.addElement(par);
/* 326 */     cell.setBackgroundColor(Color.WHITE);
/* 327 */     cell.setHorizontalAlignment(alignHorizontal);
/* 328 */     cell.setVerticalAlignment(alignVertical);
/* 329 */     return cell;
/*     */   }
/*     */ 
/*     */   public void onOpenDocument(PdfWriter writer, Document document)
/*     */   {
/* 334 */     this.total = writer.getDirectContent().createTemplate(100.0F, 100.0F);
/* 335 */     this.total.setBoundingBox(new Rectangle(-20.0F, -20.0F, 100.0F, 100.0F));
/*     */     try {
/* 337 */       this.helv = BaseFont.createFont("Helvetica", "Cp1252", false);
/*     */     }
/*     */     catch (Exception e) {
/* 340 */       throw new ExceptionConverter(e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void onStartPage(PdfWriter writer, Document document)
/*     */   {
/*     */     try {
/* 347 */       cabecera(document);
/*     */     } catch (Exception ex) {
/* 349 */       Logger.getLogger(GenerarPdf.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void onEndPage(PdfWriter writer, Document document)
/*     */   {
/* 355 */     PdfContentByte cb = writer.getDirectContent();
/* 356 */     cb.saveState();
/* 357 */     String text = "Página " + writer.getPageNumber() + " de ";
/* 358 */     float textBase = document.bottom();
/* 359 */     float textSize = this.helv.getWidthPoint(text, 8.0F);
/* 360 */     cb.beginText();
/* 361 */     cb.setFontAndSize(this.helv, 8.0F);
/*     */ 
/* 363 */     cb.setTextMatrix(document.left(), textBase);
/* 364 */     cb.showText(text);
/* 365 */     cb.endText();
/* 366 */     cb.addTemplate(this.total, document.left() + textSize, textBase);
/*     */ 
/* 368 */     cb.restoreState();
/*     */   }
/*     */ 
/*     */   public void onCloseDocument(PdfWriter writer, Document document)
/*     */   {
/* 373 */     this.total.beginText();
/* 374 */     this.total.setFontAndSize(this.helv, 8.0F);
/* 375 */     this.total.setTextMatrix(0.0F, 0.0F);
/* 376 */     this.total.showText(String.valueOf(writer.getPageNumber() - 1));
/* 377 */     this.total.endText();
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.gui.listados.GenerarPdf
 * JD-Core Version:    0.6.2
 */