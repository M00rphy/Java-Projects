/*     */ package facturacion.view;
/*     */ 
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
/*     */ import contaes.manejoDatos.ManejoTiposIVA;
/*     */ import contaes.manejoDatos.TipoFormaPago;
/*     */ import contaes.manejoDatos.TipoIVA;
/*     */ import contaes.manejoDatos.TipoSubcuenta;
/*     */ import contaes.manejoDatos.TipoVencimiento;
/*     */ import contaes.manejoDatos.auxiliar.AddExtension;
/*     */ import facturacion.model.Factura;
/*     */ import facturacion.model.LineaFactura;
/*     */ import facturacion.model.Producto;
/*     */ import java.awt.Color;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.net.MalformedURLException;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.DecimalFormatSymbols;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.swing.JFileChooser;
/*     */ import javax.swing.JOptionPane;
/*     */ 
/*     */ public class GenerarPdf extends PdfPageEventHelper
/*     */ {
/*     */   Factura factura;
/*     */   ArrayList<LineaFactura> lineas;
/*     */   List<IVA> listaIva;
/*     */   ArrayList<TipoVencimiento> vencimientos;
/*  65 */   float ANCHOTABLAREGULAR = 100.0F;
/*  66 */   float ANCHOTABLAMEDIA = 50.0F;
/*     */   protected PdfTemplate total;
/*     */   protected BaseFont helv;
/*  70 */   Font helvetica9 = FontFactory.getFont("Helvetica", 9.0F);
/*  71 */   Font helvetica8 = FontFactory.getFont("Helvetica", 8.0F);
/*     */   String datos;
/*     */   String datosCliente;
/*     */   String cuentaBancaria;
/*     */ 
/*     */   public GenerarPdf(Factura factura, ArrayList<LineaFactura> lineas)
/*     */   {
/*  78 */     this.factura = factura;
/*  79 */     this.lineas = lineas;
/*  80 */     obtenerDatos();
/*     */   }
/*     */ 
/*     */   public void hacerTrabajo() {
/*  84 */     File archivo = obtenerArchivo();
/*  85 */     if ((archivo != null) && 
/*  86 */       (generarDocumento(archivo)))
/*  87 */       JOptionPane.showMessageDialog(Inicio.frame, "factura generada en " + archivo.getAbsolutePath(), "Error", 1);
/*     */   }
/*     */ 
/*     */   private void obtenerDatos()
/*     */   {
/*  98 */     ManejoEmpresas mE = new ManejoEmpresas(Inicio.getCGeneral(), Inicio.p.getEmpresa());
/*  99 */     String nuestroNombre = mE.getNombre(Inicio.p.getEmpresa());
/* 100 */     String[] empresa = mE.datosEmpresa(nuestroNombre);
/* 101 */     mE.cerrarRs();
/* 102 */     this.datos = (nuestroNombre + "\n");
/* 103 */     if (empresa[1] != null) {
/* 104 */       this.datos = (this.datos + empresa[1] + "\n");
/*     */     }
/* 106 */     if (empresa[4] != null) {
/* 107 */       this.datos = (this.datos + empresa[4] + " - ");
/*     */     }
/* 109 */     if (empresa[2] != null) {
/* 110 */       this.datos = (this.datos + empresa[2] + " - ");
/*     */     }
/* 112 */     if (empresa[3] != null) {
/* 113 */       this.datos = (this.datos + empresa[3] + "\n");
/*     */     }
/* 115 */     if (empresa[0] != null) {
/* 116 */       this.datos = (this.datos + empresa[0] + "\n");
/*     */     }
/* 118 */     if (empresa[5] != null) {
/* 119 */       this.datos = (this.datos + empresa[5] + " - ");
/*     */     }
/* 121 */     if (empresa[6] != null) {
/* 122 */       this.datos = (this.datos + empresa[6] + "\n");
/*     */     }
/*     */ 
/* 125 */     ManejoAgenda mA = new ManejoAgenda(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 126 */     String codCuenta = Integer.toString(this.factura.getCliente().getCodigo());
/* 127 */     String[] cliente = mA.datos(codCuenta);
/* 128 */     this.datosCliente = (cliente[1] + "\n");
/* 129 */     if (cliente[3] != null) {
/* 130 */       this.datosCliente = (this.datosCliente + cliente[3] + "\n");
/*     */     }
/* 132 */     if (cliente[4] != null) {
/* 133 */       this.datosCliente = (this.datosCliente + cliente[4] + " - ");
/*     */     }
/* 135 */     if (cliente[5] != null) {
/* 136 */       this.datosCliente = (this.datosCliente + cliente[5] + " - ");
/*     */     }
/* 138 */     if (cliente[6] != null) {
/* 139 */       this.datosCliente = (this.datosCliente + cliente[6] + "\n");
/*     */     }
/* 141 */     if (cliente[2] != null) {
/* 142 */       this.datosCliente = (this.datosCliente + cliente[2] + "\n");
/*     */     }
/* 144 */     if (cliente[12] != null) {
/* 145 */       this.cuentaBancaria = cliente[12];
/*     */     }
/* 147 */     mA.cerrarRs();
/*     */   }
/*     */ 
/*     */   private File obtenerArchivo()
/*     */   {
/* 153 */     File archivo = null;
/* 154 */     JFileChooser fc = new JFileChooser();
/* 155 */     fc.setSelectedFile(new File("Factura" + this.factura.getNumero() + ".pdf"));
/* 156 */     int retorno = fc.showSaveDialog(Inicio.frame);
/* 157 */     if (retorno == 0) {
/* 158 */       archivo = fc.getSelectedFile();
/* 159 */       archivo = AddExtension.pdf(archivo);
/*     */     }
/* 161 */     return archivo;
/*     */   }
/*     */ 
/*     */   private boolean generarDocumento(File f) {
/* 165 */     if (f != null) {
/*     */       try {
/* 167 */         Document document = new Document();
/* 168 */         PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(f));
/*     */ 
/* 170 */         writer.setPageEvent(this);
/* 171 */         document.open();
/* 172 */         document.setMargins(72.0F, 72.0F, 72.0F, 72.0F);
/* 173 */         tablaLineasFactura(document);
/* 174 */         document.close();
/* 175 */         return true;
/*     */       } catch (DocumentException ex) {
/* 177 */         Logger.getLogger(GenerarPdf.class.getName()).log(Level.SEVERE, null, ex);
/* 178 */         return false;
/*     */       } catch (IOException ex) {
/* 180 */         Logger.getLogger(GenerarPdf.class.getName()).log(Level.SEVERE, null, ex);
/* 181 */         return false;
/*     */       } catch (Exception ex) {
/* 183 */         Logger.getLogger(GenerarPdf.class.getName()).log(Level.SEVERE, null, ex);
/* 184 */         return false;
/*     */       }
/*     */     }
/* 187 */     return false;
/*     */   }
/*     */ 
/*     */   private void tablaLineasFactura(Document document) {
/* 191 */     DecimalFormatSymbols formato = new DecimalFormatSymbols();
/* 192 */     formato.setDecimalSeparator(',');
/* 193 */     formato.setPerMill('.');
/* 194 */     DecimalFormat fn = new DecimalFormat("#,###,##0.00", formato);
/*     */     try
/*     */     {
/* 197 */       ManejoTiposIVA mTi = new ManejoTiposIVA(Inicio.getCGeneral());
/* 198 */       ArrayList tipos = mTi.getTiposIVA();
/* 199 */       this.listaIva = new ArrayList();
/* 200 */       for (TipoIVA tipoIVA : (List<TipoIVA>) tipos) {
/* 201 */         IVA iva = new IVA(Integer.valueOf(tipoIVA.getId()), Double.valueOf(tipoIVA.getIva()), Double.valueOf(tipoIVA.getRe()));
/* 202 */         this.listaIva.add(iva);
/*     */       }
/* 204 */       Collections.sort(this.listaIva);
/*     */ 
/* 206 */       PdfPTable header = new PdfPTable(new float[] { 1.0F, 2.0F, 1.0F, 1.0F, 1.0F });
/* 207 */       header.addCell(formarCell(new Paragraph("Unidades", this.helvetica9), 1, 0, false));
/* 208 */       header.addCell(formarCell(new Paragraph("Concepto", this.helvetica9), 1, 0, false));
/* 209 */       header.addCell(formarCell(new Paragraph("Tipo IVA", this.helvetica9), 1, 2, false));
/* 210 */       header.addCell(formarCell(new Paragraph("Precio unidad", this.helvetica9), 1, 2, false));
/* 211 */       header.addCell(formarCell(new Paragraph("Precio total", this.helvetica9), 1, 2, false));
/* 212 */       header.setWidthPercentage(this.ANCHOTABLAREGULAR);
/* 213 */       document.add(header);
/*     */ 
/* 215 */       PdfPTable body = new PdfPTable(new float[] { 1.0F, 2.0F, 1.0F, 1.0F, 1.0F });
/*     */ 
/* 217 */       Double totalBases = Double.valueOf(0.0D);
/*     */ 
/* 219 */       for (LineaFactura linea : this.lineas) {
/* 220 */         String ti = Double.toString(linea.getTipoIva().getIva()) + "%";
/* 221 */         body.addCell(new PdfPCell(new Paragraph(linea.getUnidades().toString(), this.helvetica9)));
/* 222 */         body.addCell(new PdfPCell(new Paragraph(linea.getConcepto(), this.helvetica9)));
/* 223 */         body.addCell(formarCell(new Paragraph(ti, this.helvetica9), 1, 2, true));
/* 224 */         body.addCell(formarCell(new Paragraph(fn.format(linea.getIdProducto().getPrecio()), this.helvetica9), 1, 2, true));
/* 225 */         body.addCell(formarCell(new Paragraph(fn.format(linea.getBase()), this.helvetica9), 1, 2, true));
/* 226 */         sumarAlTipoAdecuado(Integer.valueOf(linea.getTipoIva().getId()), linea.getBase().doubleValue());
/* 227 */         totalBases = Double.valueOf(totalBases.doubleValue() + linea.getBase().doubleValue());
/*     */       }
/*     */ 
/* 230 */       body.addCell(new PdfPCell(new Paragraph("", this.helvetica9)));
/* 231 */       body.addCell(new PdfPCell(new Paragraph("Total", this.helvetica9)));
/* 232 */       body.addCell(new PdfPCell(new Paragraph("", this.helvetica9)));
/* 233 */       body.addCell(new PdfPCell(new Paragraph("", this.helvetica9)));
/* 234 */       body.addCell(formarCell(new Paragraph(fn.format(totalBases), this.helvetica9), 1, 2, true));
/* 235 */       body.setWidthPercentage(this.ANCHOTABLAREGULAR);
/* 236 */       document.add(body);
/*     */ 
/* 238 */       document.add(new Paragraph("\n"));
/* 239 */       PdfPTable summary = new PdfPTable(5);
/* 240 */       summary.addCell(formarCell(new Paragraph("Tipo IVA", this.helvetica9), 1, 0, false));
/* 241 */       summary.addCell(formarCell(new Paragraph("base", this.helvetica9), 1, 2, false));
/* 242 */       summary.addCell(formarCell(new Paragraph("IVA", this.helvetica9), 1, 2, false));
/* 243 */       summary.addCell(formarCell(new Paragraph("Rec.Equiv.", this.helvetica9), 1, 2, false));
/* 244 */       summary.addCell(formarCell(new Paragraph("Total", this.helvetica9), 1, 2, false));
/*     */ 
/* 246 */       Double totalIVA = Double.valueOf(0.0D);
/* 247 */       Double totalRe = Double.valueOf(0.0D);
/* 248 */       totalBases = Double.valueOf(0.0D);
/* 249 */       for (IVA iva : this.listaIva) {
/* 250 */         if (iva.getSumaBases().doubleValue() != 0.0D) {
/* 251 */           Double re = Double.valueOf(0.0D);
/* 252 */           if (this.factura.isRecargo()) {
/* 253 */             re = Double.valueOf(iva.getSumaBases().doubleValue() * iva.getTipoRE().doubleValue() / 100.0D);
/*     */           }
/* 255 */           Double importeIva = Double.valueOf(iva.getSumaBases().doubleValue() * iva.getTipoIVA().doubleValue() / 100.0D);
/* 256 */           Double totalLinea = Double.valueOf(iva.getSumaBases().doubleValue() + importeIva.doubleValue() + re.doubleValue());
/* 257 */           summary.addCell(new PdfPCell(new Paragraph(iva.getTipoIVA().toString() + "%", this.helvetica9)));
/* 258 */           summary.addCell(formarCell(new Paragraph(fn.format(iva.getSumaBases()), this.helvetica9), 1, 2, true));
/* 259 */           summary.addCell(formarCell(new Paragraph(fn.format(importeIva), this.helvetica9), 1, 2, true));
/* 260 */           summary.addCell(formarCell(new Paragraph(fn.format(re), this.helvetica9), 1, 2, true));
/* 261 */           summary.addCell(formarCell(new Paragraph(fn.format(totalLinea), this.helvetica9), 1, 2, true));
/* 262 */           totalBases = Double.valueOf(totalBases.doubleValue() + iva.getSumaBases().doubleValue());
/* 263 */           totalIVA = Double.valueOf(totalIVA.doubleValue() + importeIva.doubleValue());
/* 264 */           totalRe = Double.valueOf(totalRe.doubleValue() + re.doubleValue());
/*     */         }
/*     */       }
/* 267 */       summary.addCell(new PdfPCell(new Paragraph("Total", this.helvetica9)));
/* 268 */       summary.addCell(formarCell(new Paragraph(fn.format(totalBases), this.helvetica9), 1, 2, true));
/* 269 */       summary.addCell(formarCell(new Paragraph(fn.format(totalIVA), this.helvetica9), 1, 2, true));
/* 270 */       summary.addCell(formarCell(new Paragraph(fn.format(totalRe), this.helvetica9), 1, 2, true));
/* 271 */       summary.addCell(formarCell(new Paragraph(fn.format(totalBases.doubleValue() + totalIVA.doubleValue() + totalRe.doubleValue()), this.helvetica9), 1, 2, true));
/* 272 */       summary.setWidthPercentage(this.ANCHOTABLAREGULAR);
/* 273 */       document.add(summary);
/*     */ 
/* 275 */       if (this.factura.getRetencion().doubleValue() != 0.0D) {
/* 276 */         document.add(new Paragraph("\n"));
/* 277 */         PdfPTable rete = new PdfPTable(2);
/* 278 */         rete.addCell(new PdfPCell(new Paragraph("Retenciones", this.helvetica9)));
/* 279 */         rete.addCell(formarCell(new Paragraph(fn.format(this.factura.getRetencion()), this.helvetica9), 1, 2, true));
/* 280 */         rete.addCell(new PdfPCell(new Paragraph("Total a pagar", this.helvetica9)));
/* 281 */         rete.addCell(formarCell(new Paragraph(fn.format(totalBases.doubleValue() + totalIVA.doubleValue() + totalRe.doubleValue() - this.factura.getRetencion().doubleValue()), this.helvetica9), 1, 2, true));
/* 282 */         rete.setWidthPercentage(this.ANCHOTABLAMEDIA);
/* 283 */         rete.setHorizontalAlignment(2);
/* 284 */         document.add(rete);
/*     */       }
/*     */ 
/* 287 */       obtenerVencimientos();
/* 288 */       document.add(new Paragraph("\n\n"));
/* 289 */       document.add(new Paragraph("Forma de pago: " + this.factura.getFormaPago() + "\n", this.helvetica9));
/* 290 */       document.add(new Paragraph("Vencimientos\n", this.helvetica9));
/* 291 */       PdfPTable payments = new PdfPTable(3);
/* 292 */       payments.addCell(formarCell(new Paragraph("Número", this.helvetica8), 1, 0, false));
/* 293 */       payments.addCell(formarCell(new Paragraph("Fecha", this.helvetica8), 1, 0, false));
/* 294 */       payments.addCell(formarCell(new Paragraph("Importe", this.helvetica8), 1, 2, false));
/*     */ 
/* 296 */       int num = 1;
/* 297 */       int numTotal = this.vencimientos.size();
/* 298 */       for (TipoVencimiento vencimiento : this.vencimientos) {
/* 299 */         payments.addCell(new PdfPCell(new Paragraph(num + " de " + numTotal, this.helvetica8)));
/* 300 */         payments.addCell(new PdfPCell(new Paragraph(vencimiento.getFecha(), this.helvetica8)));
/* 301 */         payments.addCell(formarCell(new Paragraph(fn.format(vencimiento.getImporte()), this.helvetica8), 1, 2, true));
/* 302 */         num++;
/*     */       }
/* 304 */       payments.setWidthPercentage(this.ANCHOTABLAMEDIA);
/* 305 */       payments.setHorizontalAlignment(0);
/* 306 */       document.add(payments);
/* 307 */       if ((this.cuentaBancaria != null) && (!this.cuentaBancaria.equals("")) && (!this.cuentaBancaria.equals("NO")))
/* 308 */         document.add(new Paragraph("\nCuenta bancaria: " + this.cuentaBancaria + "\n", this.helvetica9));
/*     */     }
/*     */     catch (DocumentException ex) {
/* 311 */       Logger.getLogger(GenerarPdf.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void sumarAlTipoAdecuado(Integer id, double importe) {
/* 316 */     for (int x = 0; x < this.listaIva.size(); x++)
/* 317 */       if (((IVA)this.listaIva.get(x)).getId() == id) {
/* 318 */         ((IVA)this.listaIva.get(x)).sumar(Double.valueOf(importe));
/* 319 */         return;
/*     */       }
/*     */   }
/*     */ 
/*     */   private void obtenerVencimientos()
/*     */   {
/* 325 */     this.vencimientos = new ArrayList();
/* 326 */     TipoFormaPago formaPago = this.factura.getFormaPago();
/* 327 */     double totalACobrar = this.factura.getTotal().doubleValue() - this.factura.getRetencion().doubleValue();
/* 328 */     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
/*     */ 
/* 330 */     Calendar calendar = Calendar.getInstance();
/* 331 */     Calendar calendarFechaMes = Calendar.getInstance();
/* 332 */     calendar.setTime(this.factura.getFecha());
/* 333 */     double importeVencimiento = totalACobrar / formaPago.getNumeroPagos().intValue();
/*     */ 
/* 335 */     for (int i = 0; i < formaPago.getNumeroPagos().intValue(); i++)
/* 336 */       if ((formaPago.getDiaFijoPago() == null) || (formaPago.getDiaFijoPago().intValue() == 0)) {
/* 337 */         if (i == 0) {
/* 338 */           calendar.add(5, formaPago.getDiasPrimerPago().intValue());
/*     */         }
/* 341 */         else if (formaPago.getDiasEntrePagos().intValue() == 30) {
/* 342 */           calendar.add(2, 1);
/*     */         }
/*     */         else {
/* 345 */           calendar.add(5, formaPago.getDiasEntrePagos().intValue());
/*     */         }
/*     */ 
/* 348 */         String num = Integer.toString(i + 1) + "/" + Integer.toString(formaPago.getNumeroPagos().intValue());
/* 349 */         TipoVencimiento vencimiento = new TipoVencimiento();
/* 350 */         vencimiento.setFecha(sdf.format(calendar.getTime()));
/* 351 */         vencimiento.setEjercicio(calendar.get(1));
/* 352 */         vencimiento.setFactura(this.factura.getNumero());
/* 353 */         vencimiento.setFechaf(sdf.format(this.factura.getFecha()));
/* 354 */         vencimiento.setCuenta(this.factura.getCliente().getCodigo());
/* 355 */         vencimiento.setImporte(importeVencimiento);
/* 356 */         vencimiento.setNum(num);
/* 357 */         vencimiento.setPagado(false);
/* 358 */         vencimiento.setCuentap(this.factura.getCliente().getCodigo());
/* 359 */         this.vencimientos.add(vencimiento);
/*     */       }
/*     */       else {
/* 362 */         if (i == 0) {
/* 363 */           calendar.add(5, formaPago.getDiasPrimerPago().intValue());
/* 364 */           calendarFechaMes.setTime(calendar.getTime());
/* 365 */           calendarFechaMes.set(5, formaPago.getDiaFijoPago().intValue());
/* 366 */           if (calendar.getTimeInMillis() > calendarFechaMes.getTimeInMillis()) {
/* 367 */             calendarFechaMes.add(2, 1);
/*     */           }
/* 369 */           calendar.setTime(calendarFechaMes.getTime());
/*     */         }
/*     */         else {
/* 372 */           calendar.add(5, formaPago.getDiasEntrePagos().intValue());
/* 373 */           calendarFechaMes.setTime(calendar.getTime());
/* 374 */           calendarFechaMes.set(5, formaPago.getDiaFijoPago().intValue());
/* 375 */           if (calendar.getTimeInMillis() > calendarFechaMes.getTimeInMillis()) {
/* 376 */             calendarFechaMes.add(2, 1);
/*     */           }
/*     */         }
/*     */ 
/* 380 */         if ((calendarFechaMes.get(5) < formaPago.getDiaFijoPago().intValue()) && (calendarFechaMes.get(2) == 2))
/*     */         {
/* 382 */           calendarFechaMes.set(2, 1);
/* 383 */           calendarFechaMes.set(5, 28);
/* 384 */           if (i == 0) {
/* 385 */             calendar.setTime(calendarFechaMes.getTime());
/*     */           }
/*     */         }
/* 388 */         String num = Integer.toString(i + 1) + "/" + Integer.toString(formaPago.getNumeroPagos().intValue());
/* 389 */         TipoVencimiento vencimiento = new TipoVencimiento();
/* 390 */         vencimiento.setFecha(sdf.format(calendar.getTime()));
/* 391 */         vencimiento.setEjercicio(calendar.get(1));
/* 392 */         vencimiento.setFactura(this.factura.getNumero());
/* 393 */         vencimiento.setFechaf(sdf.format(this.factura.getFecha()));
/* 394 */         vencimiento.setCuenta(this.factura.getCliente().getCodigo());
/* 395 */         vencimiento.setImporte(importeVencimiento);
/* 396 */         vencimiento.setNum(num);
/* 397 */         vencimiento.setPagado(false);
/* 398 */         vencimiento.setCuentap(this.factura.getCliente().getCodigo());
/* 399 */         this.vencimientos.add(vencimiento);
/*     */       }
/*     */   }
/*     */ 
/*     */   private void cabecera(Document document)
/*     */     throws DocumentException
/*     */   {
/* 406 */     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
/* 407 */     String fecha = sdf.format(this.factura.getFecha());
/* 408 */     boolean conLogo = getLogo() != null;
/*     */ 
/* 410 */     PdfPTable tabla = new PdfPTable(2);
/* 411 */     Paragraph par = null;
/* 412 */     PdfPCell cell = null;
/*     */ 
/* 414 */     if (conLogo)
/*     */     {
/* 416 */       cell = new PdfPCell(getLogo());
/* 417 */       cell.setBackgroundColor(Color.WHITE);
/* 418 */       cell.setHorizontalAlignment(0);
/* 419 */       cell.setVerticalAlignment(1);
/* 420 */       cell.setBorder(0);
/*     */     }
/*     */     else
/*     */     {
/* 424 */       par = formarParagraph(this.datos, this.helvetica9, 0);
/* 425 */       cell = formarCell(par, 1, 0, false);
/*     */     }
/* 427 */     tabla.addCell(cell);
/*     */ 
/* 430 */     cell = new PdfPCell();
/* 431 */     cell.setBorder(0);
/* 432 */     tabla.addCell(cell);
/*     */ 
/* 434 */     if (!conLogo)
/*     */     {
/* 436 */       cell = new PdfPCell();
/* 437 */       cell.setBorder(0);
/*     */     }
/*     */     else
/*     */     {
/* 441 */       par = formarParagraph(this.datos, this.helvetica9, 0);
/* 442 */       cell = formarCell(par, 1, 0, false);
/*     */     }
/* 444 */     tabla.addCell(cell);
/*     */ 
/* 448 */     par = formarParagraph(this.datosCliente, this.helvetica9, 0);
/* 449 */     cell = formarCell(par, 1, 0, true);
/* 450 */     tabla.addCell(cell);
/*     */ 
/* 452 */     document.add(tabla);
/*     */ 
/* 454 */     tabla = new PdfPTable(2);
/* 455 */     tabla.setWidthPercentage(this.ANCHOTABLAMEDIA);
/* 456 */     par = formarParagraph("Número", this.helvetica9, 0);
/* 457 */     cell = formarCell(par, 1, 0, false);
/* 458 */     tabla.addCell(cell);
/* 459 */     par = formarParagraph("Fecha", this.helvetica9, 0);
/* 460 */     cell = formarCell(par, 1, 0, false);
/* 461 */     tabla.addCell(cell);
/* 462 */     par = formarParagraph(this.factura.getNumero(), this.helvetica9, 0);
/* 463 */     cell = formarCell(par, 1, 0, true);
/* 464 */     tabla.addCell(cell);
/* 465 */     par = formarParagraph(fecha, this.helvetica9, 0);
/* 466 */     cell = formarCell(par, 1, 0, true);
/* 467 */     tabla.addCell(cell);
/* 468 */     tabla.setHorizontalAlignment(0);
/* 469 */     document.add(tabla);
/*     */ 
/* 471 */     document.add(new Paragraph("\n"));
/*     */   }
/*     */ 
/*     */   private Paragraph formarParagraph(String string, Font fuente, int align) {
/* 475 */     Paragraph par = new Paragraph(string, fuente);
/*     */ 
/* 477 */     par.setAlignment(align);
/* 478 */     return par;
/*     */   }
/*     */ 
/*     */   private PdfPCell formarCell(Paragraph par, int alignVertical, int alignHorizontal, boolean border)
/*     */   {
/* 490 */     PdfPCell cell = new PdfPCell();
/*     */ 
/* 492 */     if (!border) {
/* 493 */       cell.setBorder(0);
/*     */     }
/* 495 */     par.setAlignment(alignHorizontal);
/* 496 */     cell.addElement(par);
/* 497 */     cell.setBackgroundColor(Color.WHITE);
/* 498 */     cell.setHorizontalAlignment(alignHorizontal);
/* 499 */     cell.setVerticalAlignment(alignVertical);
/* 500 */     return cell;
/*     */   }
/*     */ 
/*     */   private Image getLogo()
/*     */   {
/*     */     try
/*     */     {
/* 507 */       File f = new File("images/logo.png");
/*     */ 
/* 509 */       if (f.exists()) {
/* 510 */         return Image.getInstance("images/logo.png");
/*     */       }
/* 512 */       return null;
/*     */     }
/*     */     catch (BadElementException ex) {
/* 515 */       Logger.getLogger(GenerarPdf.class.getName()).log(Level.SEVERE, null, ex);
/* 516 */       return null;
/*     */     } catch (MalformedURLException ex) {
/* 518 */       Logger.getLogger(GenerarPdf.class.getName()).log(Level.SEVERE, null, ex);
/* 519 */       return null;
/*     */     } catch (IOException ex) {
/* 521 */       Logger.getLogger(GenerarPdf.class.getName()).log(Level.SEVERE, null, ex);
/* 522 */     }return null;
/*     */   }
/*     */ 
/*     */   public void onOpenDocument(PdfWriter writer, Document document)
/*     */   {
/* 528 */     this.total = writer.getDirectContent().createTemplate(100.0F, 100.0F);
/* 529 */     this.total.setBoundingBox(new Rectangle(-20.0F, -20.0F, 100.0F, 100.0F));
/*     */     try {
/* 531 */       this.helv = BaseFont.createFont("Helvetica", "Cp1252", false);
/*     */     }
/*     */     catch (Exception e) {
/* 534 */       throw new ExceptionConverter(e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void onStartPage(PdfWriter writer, Document document)
/*     */   {
/*     */     try {
/* 541 */       cabecera(document);
/*     */     } catch (Exception ex) {
/* 543 */       Logger.getLogger(GenerarPdf.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void onEndPage(PdfWriter writer, Document document)
/*     */   {
/* 549 */     PdfContentByte cb = writer.getDirectContent();
/* 550 */     cb.saveState();
/* 551 */     String text = "Página " + writer.getPageNumber() + " de ";
/* 552 */     float textBase = document.bottom();
/* 553 */     float textSize = this.helv.getWidthPoint(text, 8.0F);
/* 554 */     cb.beginText();
/* 555 */     cb.setFontAndSize(this.helv, 8.0F);
/*     */ 
/* 557 */     cb.setTextMatrix(document.left(), textBase);
/* 558 */     cb.showText(text);
/* 559 */     cb.endText();
/* 560 */     cb.addTemplate(this.total, document.left() + textSize, textBase);
/*     */ 
/* 562 */     cb.restoreState();
/*     */   }
/*     */ 
/*     */   public void onCloseDocument(PdfWriter writer, Document document)
/*     */   {
/* 567 */     this.total.beginText();
/* 568 */     this.total.setFontAndSize(this.helv, 8.0F);
/* 569 */     this.total.setTextMatrix(0.0F, 0.0F);
/* 570 */     this.total.showText(String.valueOf(writer.getPageNumber() - 1));
/* 571 */     this.total.endText();
/*     */   }
/*     */   class IVA implements Comparable<IVA> { Integer id;
/*     */     Double tipoIVA;
/*     */     Double tipoRE;
/*     */     Double sumaBases;
/*     */ 
/* 581 */     public IVA(Integer id, Double tipoIVA, Double tipoRE) { this.id = id;
/* 582 */       this.tipoIVA = tipoIVA;
/* 583 */       this.tipoRE = tipoRE;
/* 584 */       this.sumaBases = Double.valueOf(0.0D); }
/*     */ 
/*     */     public Integer getId()
/*     */     {
/* 588 */       return this.id;
/*     */     }
/*     */ 
/*     */     public Double getTipoIVA() {
/* 592 */       return this.tipoIVA;
/*     */     }
/*     */ 
/*     */     public Double getTipoRE() {
/* 596 */       return this.tipoRE;
/*     */     }
/*     */ 
/*     */     public Double getSumaBases() {
/* 600 */       return this.sumaBases;
/*     */     }
/*     */ 
/*     */     public void setSumaBases(Double sumaBases) {
/* 604 */       this.sumaBases = sumaBases;
/*     */     }
/*     */ 
/*     */     public void sumar(Double base) {
/* 608 */       this.sumaBases = Double.valueOf(this.sumaBases.doubleValue() + base.doubleValue());
/*     */     }
/*     */ 
/*     */     public int compareTo(IVA i) {
/* 612 */       return this.tipoIVA.doubleValue() > i.tipoIVA.doubleValue() ? 1 : this.tipoIVA.doubleValue() < i.tipoIVA.doubleValue() ? -1 : 0;
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     facturacion.view.GenerarPdf
 * JD-Core Version:    0.6.2
 */