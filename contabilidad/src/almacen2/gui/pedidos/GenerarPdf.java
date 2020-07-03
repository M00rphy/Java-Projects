/*     */ package almacen2.gui.pedidos;
/*     */ 
/*     */ import almacen2.data.pedidos.LineaPedidoObject;
/*     */ import almacen2.data.pedidos.PedidoObject;
/*     */ import com.lowagie.text.BadElementException;
/*     */ import com.lowagie.text.Document;
/*     */ import com.lowagie.text.DocumentException;
/*     */ import com.lowagie.text.ExceptionConverter;
/*     */ import com.lowagie.text.Font;
/*     */ import com.lowagie.text.FontFactory;
/*     */ import com.lowagie.text.Image;
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
/*     */ import contaes.manejoDatos.ManejoAgenda;
/*     */ import contaes.manejoDatos.ManejoEmpresas;
/*     */ import contaes.manejoDatos.auxiliar.AddExtension;
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.Color;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.net.MalformedURLException;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.DecimalFormatSymbols;
/*     */ import java.util.ArrayList;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.swing.JFileChooser;
/*     */ import javax.swing.JOptionPane;
/*     */ 
/*     */ public class GenerarPdf extends PdfPageEventHelper
/*     */ {
/*     */   PedidoObject pedido;
/*     */   ArrayList<LineaPedidoObject> lineasPedido;
/*  54 */   float ANCHOTABLAREGULAR = 100.0F;
/*  55 */   float ANCHOTABLAMEDIA = 50.0F;
/*     */   protected PdfTemplate total;
/*     */   protected BaseFont helv;
/*  59 */   Font helvetica9 = FontFactory.getFont("Helvetica", 9.0F);
/*  60 */   Font helvetica8 = FontFactory.getFont("Helvetica", 8.0F);
/*     */   private String datos;
/*     */   private String datosCliente;
/*     */ 
/*     */   public GenerarPdf(PedidoObject pedido, ArrayList<LineaPedidoObject> lineasPedido)
/*     */   {
/*  66 */     this.pedido = pedido;
/*  67 */     this.lineasPedido = lineasPedido;
/*  68 */     obtenerDatos();
/*     */   }
/*     */ 
/*     */   public void hacerTrabajo() {
/*  72 */     File archivo = obtenerArchivo();
/*  73 */     if ((archivo != null) && 
/*  74 */       (generarDocumento(archivo)))
/*  75 */       JOptionPane.showMessageDialog(Inicio.frame, "Albarán guardado en " + archivo.getAbsolutePath(), "Info", 1);
/*     */   }
/*     */ 
/*     */   private void obtenerDatos()
/*     */   {
/*  86 */     ManejoEmpresas mE = new ManejoEmpresas(Inicio.getCGeneral(), Inicio.p.getEmpresa());
/*  87 */     String nuestroNombre = mE.getNombre(Inicio.p.getEmpresa());
/*  88 */     String[] empresa = mE.datosEmpresa(nuestroNombre);
/*  89 */     this.datos = (nuestroNombre + "\n");
/*  90 */     if (empresa[1] != null) {
/*  91 */       this.datos = (this.datos + empresa[1] + "\n");
/*     */     }
/*  93 */     if (empresa[4] != null) {
/*  94 */       this.datos = (this.datos + empresa[4] + " - ");
/*     */     }
/*  96 */     if (empresa[2] != null) {
/*  97 */       this.datos = (this.datos + empresa[2] + " - ");
/*     */     }
/*  99 */     if (empresa[3] != null) {
/* 100 */       this.datos = (this.datos + empresa[3] + "\n");
/*     */     }
/* 102 */     if (empresa[0] != null) {
/* 103 */       this.datos = (this.datos + empresa[0] + "\n");
/*     */     }
/* 105 */     if (empresa[5] != null) {
/* 106 */       this.datos = (this.datos + empresa[5] + " - ");
/*     */     }
/* 108 */     if (empresa[6] != null) {
/* 109 */       this.datos = (this.datos + empresa[6] + "\n");
/*     */     }
/*     */ 
/* 112 */     ManejoAgenda mA = new ManejoAgenda(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 113 */     String codCuenta = Integer.toString(this.pedido.getProveedor());
/* 114 */     String[] cliente = mA.datos(codCuenta);
/* 115 */     this.datosCliente = (cliente[1] + "\n");
/* 116 */     if (cliente[3] != null) {
/* 117 */       this.datosCliente = (this.datosCliente + cliente[3] + "\n");
/*     */     }
/* 119 */     if (cliente[4] != null) {
/* 120 */       this.datosCliente = (this.datosCliente + cliente[4] + " - ");
/*     */     }
/* 122 */     if (cliente[5] != null) {
/* 123 */       this.datosCliente = (this.datosCliente + cliente[5] + " - ");
/*     */     }
/* 125 */     if (cliente[6] != null) {
/* 126 */       this.datosCliente = (this.datosCliente + cliente[6] + "\n");
/*     */     }
/* 128 */     if (cliente[2] != null) {
/* 129 */       this.datosCliente = (this.datosCliente + cliente[2] + "\n");
/*     */     }
/* 131 */     mA.cerrarRs();
/*     */   }
/*     */ 
/*     */   private File obtenerArchivo()
/*     */   {
/* 137 */     File archivo = null;
/* 138 */     JFileChooser fc = new JFileChooser();
/* 139 */     fc.setSelectedFile(new File("Pedido" + this.pedido.getCodigo() + ".pdf"));
/* 140 */     int retorno = fc.showSaveDialog(Inicio.frame);
/* 141 */     if (retorno == 0) {
/* 142 */       archivo = fc.getSelectedFile();
/* 143 */       archivo = AddExtension.pdf(archivo);
/*     */     }
/* 145 */     return archivo;
/*     */   }
/*     */ 
/*     */   private boolean generarDocumento(File f) {
/* 149 */     if (f != null) {
/*     */       try {
/* 151 */         Document document = new Document();
/* 152 */         PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(f));
/*     */ 
/* 154 */         writer.setPageEvent(this);
/* 155 */         document.open();
/* 156 */         document.setMargins(72.0F, 72.0F, 72.0F, 72.0F);
/* 157 */         tablaLineasFactura(document);
/* 158 */         document.close();
/* 159 */         return true;
/*     */       } catch (DocumentException ex) {
/* 161 */         Logger.getLogger(GenerarPdf.class.getName()).log(Level.SEVERE, null, ex);
/* 162 */         return false;
/*     */       } catch (IOException ex) {
/* 164 */         Logger.getLogger(GenerarPdf.class.getName()).log(Level.SEVERE, null, ex);
/* 165 */         return false;
/*     */       } catch (Exception ex) {
/* 167 */         Logger.getLogger(GenerarPdf.class.getName()).log(Level.SEVERE, null, ex);
/* 168 */         return false;
/*     */       }
/*     */     }
/* 171 */     return false;
/*     */   }
/*     */ 
/*     */   private void tablaLineasFactura(Document document) {
/* 175 */     DecimalFormatSymbols formato = new DecimalFormatSymbols();
/* 176 */     formato.setDecimalSeparator(',');
/* 177 */     formato.setPerMill('.');
/* 178 */     DecimalFormat fn = new DecimalFormat("#,###,##0.00", formato);
/*     */     try
/*     */     {
/* 182 */       PdfPTable header = new PdfPTable(new float[] { 1.0F, 2.0F, 0.75F, 1.0F, 1.0F });
/* 183 */       header.addCell(formarCell(new Paragraph("Referencia", this.helvetica9), 1, 0, false));
/* 184 */       header.addCell(formarCell(new Paragraph("Descripción", this.helvetica9), 1, 0, false));
/* 185 */       header.addCell(formarCell(new Paragraph("Unidades", this.helvetica9), 1, 2, false));
/* 186 */       header.addCell(formarCell(new Paragraph("Importe/unidad", this.helvetica9), 1, 0, false));
/* 187 */       header.addCell(formarCell(new Paragraph("Importe", this.helvetica9), 1, 2, false));
/* 188 */       header.setWidthPercentage(this.ANCHOTABLAREGULAR);
/* 189 */       document.add(header);
/*     */ 
/* 191 */       PdfPTable body = new PdfPTable(new float[] { 1.0F, 2.0F, 0.75F, 1.0F, 1.0F });
/*     */ 
/* 193 */       for (LineaPedidoObject linea : this.lineasPedido) {
/* 194 */         int uni = linea.getUnidades();
/* 195 */         double importeUni = linea.getCoste() / uni;
/* 196 */         body.addCell(formarCell(new Paragraph(linea.getReferencia(), this.helvetica9), 1, 0, true));
/* 197 */         body.addCell(formarCell(new Paragraph(linea.getDescripcion(), this.helvetica9), 1, 0, true));
/* 198 */         body.addCell(formarCell(new Paragraph(Integer.toString(uni), this.helvetica9), 1, 2, true));
/* 199 */         body.addCell(formarCell(new Paragraph(fn.format(importeUni), this.helvetica9), 1, 2, true));
/* 200 */         body.addCell(formarCell(new Paragraph(fn.format(linea.getCoste()), this.helvetica9), 1, 2, true));
/*     */       }
/* 202 */       body.setWidthPercentage(this.ANCHOTABLAREGULAR);
/* 203 */       document.add(body);
/*     */     }
/*     */     catch (DocumentException ex) {
/* 206 */       Logger.getLogger(GenerarPdf.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void cabecera(Document document) throws DocumentException
/*     */   {
/* 212 */     boolean conLogo = getLogo() != null;
/*     */ 
/* 214 */     PdfPTable tabla = new PdfPTable(2);
/* 215 */     Paragraph par = null;
/* 216 */     PdfPCell cell = null;
/*     */ 
/* 218 */     if (conLogo)
/*     */     {
/* 220 */       cell = new PdfPCell(getLogo());
/* 221 */       cell.setBackgroundColor(Color.WHITE);
/* 222 */       cell.setHorizontalAlignment(0);
/* 223 */       cell.setVerticalAlignment(1);
/* 224 */       cell.setBorder(0);
/*     */     }
/*     */     else
/*     */     {
/* 228 */       par = formarParagraph(this.datos, this.helvetica9, 0);
/* 229 */       cell = formarCell(par, 1, 0, false);
/*     */     }
/* 231 */     tabla.addCell(cell);
/*     */ 
/* 234 */     cell = new PdfPCell();
/* 235 */     cell.setBorder(0);
/* 236 */     tabla.addCell(cell);
/*     */ 
/* 238 */     if (!conLogo)
/*     */     {
/* 240 */       cell = new PdfPCell();
/* 241 */       cell.setBorder(0);
/*     */     }
/*     */     else
/*     */     {
/* 245 */       par = formarParagraph(this.datos, this.helvetica9, 0);
/* 246 */       cell = formarCell(par, 1, 0, false);
/*     */     }
/* 248 */     tabla.addCell(cell);
/*     */ 
/* 252 */     par = formarParagraph(this.datosCliente, this.helvetica9, 0);
/* 253 */     cell = formarCell(par, 1, 0, true);
/* 254 */     tabla.addCell(cell);
/*     */ 
/* 256 */     document.add(tabla);
/*     */ 
/* 258 */     tabla = new PdfPTable(2);
/* 259 */     tabla.setWidthPercentage(this.ANCHOTABLAMEDIA);
/* 260 */     par = formarParagraph(Mensajes.getString("numero"), this.helvetica9, 0);
/* 261 */     cell = formarCell(par, 1, 0, false);
/* 262 */     tabla.addCell(cell);
/* 263 */     par = formarParagraph(Mensajes.getString("fecha"), this.helvetica9, 0);
/* 264 */     cell = formarCell(par, 1, 0, false);
/* 265 */     tabla.addCell(cell);
/* 266 */     par = formarParagraph(this.pedido.getCodigo(), this.helvetica9, 0);
/* 267 */     cell = formarCell(par, 1, 0, true);
/* 268 */     tabla.addCell(cell);
/* 269 */     par = formarParagraph(this.pedido.getFecha(), this.helvetica9, 0);
/* 270 */     cell = formarCell(par, 1, 0, true);
/* 271 */     tabla.addCell(cell);
/* 272 */     tabla.setHorizontalAlignment(0);
/* 273 */     document.add(tabla);
/*     */ 
/* 275 */     document.add(new Paragraph("\n"));
/*     */   }
/*     */ 
/*     */   private Paragraph formarParagraph(String string, Font fuente, int align) {
/* 279 */     Paragraph par = new Paragraph(string, fuente);
/*     */ 
/* 281 */     par.setAlignment(align);
/* 282 */     return par;
/*     */   }
/*     */ 
/*     */   private PdfPCell formarCell(Paragraph par, int alignVertical, int alignHorizontal, boolean border)
/*     */   {
/* 294 */     PdfPCell cell = new PdfPCell();
/*     */ 
/* 296 */     if (!border) {
/* 297 */       cell.setBorder(0);
/*     */     }
/* 299 */     par.setAlignment(alignHorizontal);
/* 300 */     cell.addElement(par);
/* 301 */     cell.setBackgroundColor(Color.WHITE);
/* 302 */     cell.setHorizontalAlignment(alignHorizontal);
/* 303 */     cell.setVerticalAlignment(alignVertical);
/* 304 */     return cell;
/*     */   }
/*     */ 
/*     */   private Image getLogo()
/*     */   {
/*     */     try
/*     */     {
/* 311 */       File f = new File("images/logo.png");
/*     */ 
/* 313 */       if (f.exists()) {
/* 314 */         return Image.getInstance("images/logo.png");
/*     */       }
/* 316 */       return null;
/*     */     }
/*     */     catch (BadElementException ex) {
/* 319 */       Logger.getLogger(GenerarPdf.class.getName()).log(Level.SEVERE, null, ex);
/* 320 */       return null;
/*     */     } catch (MalformedURLException ex) {
/* 322 */       Logger.getLogger(GenerarPdf.class.getName()).log(Level.SEVERE, null, ex);
/* 323 */       return null;
/*     */     } catch (IOException ex) {
/* 325 */       Logger.getLogger(GenerarPdf.class.getName()).log(Level.SEVERE, null, ex);
/* 326 */     }return null;
/*     */   }
/*     */ 
/*     */   public void onOpenDocument(PdfWriter writer, Document document)
/*     */   {
/* 332 */     this.total = writer.getDirectContent().createTemplate(100.0F, 100.0F);
/* 333 */     this.total.setBoundingBox(new Rectangle(-20.0F, -20.0F, 100.0F, 100.0F));
/*     */     try {
/* 335 */       this.helv = BaseFont.createFont("Helvetica", "Cp1252", false);
/*     */     }
/*     */     catch (Exception e) {
/* 338 */       throw new ExceptionConverter(e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void onStartPage(PdfWriter writer, Document document)
/*     */   {
/*     */     try {
/* 345 */       cabecera(document);
/*     */     } catch (Exception ex) {
/* 347 */       Logger.getLogger(GenerarPdf.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void onEndPage(PdfWriter writer, Document document)
/*     */   {
/* 353 */     PdfContentByte cb = writer.getDirectContent();
/* 354 */     cb.saveState();
/* 355 */     String text = "Página " + writer.getPageNumber() + " de ";
/* 356 */     float textBase = document.bottom();
/* 357 */     float textSize = this.helv.getWidthPoint(text, 8.0F);
/* 358 */     cb.beginText();
/* 359 */     cb.setFontAndSize(this.helv, 8.0F);
/*     */ 
/* 361 */     cb.setTextMatrix(document.left(), textBase);
/* 362 */     cb.showText(text);
/* 363 */     cb.endText();
/* 364 */     cb.addTemplate(this.total, document.left() + textSize, textBase);
/*     */ 
/* 366 */     cb.restoreState();
/*     */   }
/*     */ 
/*     */   public void onCloseDocument(PdfWriter writer, Document document)
/*     */   {
/* 371 */     this.total.beginText();
/* 372 */     this.total.setFontAndSize(this.helv, 8.0F);
/* 373 */     this.total.setTextMatrix(0.0F, 0.0F);
/* 374 */     this.total.showText(String.valueOf(writer.getPageNumber() - 1));
/* 375 */     this.total.endText();
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.gui.pedidos.GenerarPdf
 * JD-Core Version:    0.6.2
 */