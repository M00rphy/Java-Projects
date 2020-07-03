/*     */ package pos.control;
/*     */ 
/*     */ import com.sun.image.codec.jpeg.ImageFormatException;
/*     */ import contaes.Inicio;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.print.PageFormat;
/*     */ import java.awt.print.PrinterException;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import javax.imageio.ImageIO;
/*     */ import pos.model.Ticket;
/*     */ import pos.model.VentaPOS;
/*     */ 
/*     */ public class TicketImprimiblePlazosFin extends TicketImprimible
/*     */ {
/*  27 */   private ArrayList<Double> pagosAnteriores = new ArrayList();
/*  28 */   private ArrayList<Double> totalPagado = new ArrayList();
/*     */ 
/*     */   public TicketImprimiblePlazosFin(Ticket ticket, ArrayList<VentaPOS> lineas, String vendedor, String formaPago, Double entregado) {
/*  31 */     super(ticket, lineas, vendedor, formaPago, entregado);
/*  32 */     setPlazos();
/*     */   }
/*     */ 
/*     */   private void setPlazos() {
/*  36 */     ResumenControl rC = new ResumenControl(Inicio.getcAlmacen());
/*  37 */     for (VentaPOS venta : this.lineas) {
/*  38 */       double pA = rC.plazosAnteriores(venta.getIdProducto(), this.ticket.getIdplazoanterior());
/*  39 */       double tP = venta.getTotal() + pA;
/*  40 */       this.pagosAnteriores.add(Double.valueOf(pA));
/*  41 */       this.totalPagado.add(Double.valueOf(tP));
/*     */     }
/*  43 */     rC.cerrarRs();
/*     */   }
/*     */ 
/*     */   public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException
/*     */   {
/*  48 */     if (pageIndex > 0) {
/*  49 */       return 1;
/*     */     }
/*     */ 
/*  55 */     Graphics2D g2d = (Graphics2D)graphics;
/*  56 */     g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
/*  57 */     g2d.setFont(new Font("Monospaced", 1, 8));
/*     */ 
/*  61 */     int y = 10;
/*  62 */     double total = 0.0D;
/*  63 */     boolean logo = false;
/*     */     try {
/*  65 */       File archivo = new File("configdir/logo.jpg");
/*  66 */       Integer anchoEstablecido = Integer.valueOf(165);
/*  67 */       if ((archivo != null) && (archivo.exists())) {
/*  68 */         BufferedImage imagen = ImageIO.read(archivo);
/*  69 */         Integer ancho = Integer.valueOf(imagen.getWidth());
/*  70 */         Double scaleFactor = Double.valueOf(anchoEstablecido.doubleValue() / ancho.doubleValue());
/*  71 */         Double yTrans = Double.valueOf(imagen.getHeight() * scaleFactor.doubleValue());
/*  72 */         g2d.drawImage(imagen, 10, y, anchoEstablecido.intValue(), yTrans.intValue(), null);
/*  73 */         y += yTrans.intValue() + 10;
/*  74 */         logo = true;
/*     */       }
/*     */     } catch (IOException e) {
/*  77 */       e.printStackTrace(); } catch (ImageFormatException e) {
/*  78 */       e.printStackTrace();
/*  79 */     }if (!logo) {
/*  80 */       graphics.drawString(this.cabecera, 10, y);
/*  81 */       y += 10;
/*     */     }
/*  83 */     g2d.setFont(new Font("Monospaced", 1, 6));
/*  84 */     graphics.drawString(this.nif, 10, y);
/*  85 */     y += 10;
/*  86 */     graphics.drawString(this.direccion, 10, y);
/*  87 */     y += 10;
/*  88 */     graphics.drawString(this.provincia, 10, y);
/*  89 */     y += 10;
/*  90 */     graphics.drawString(this.telefono, 10, y);
/*     */ 
/*  92 */     g2d.setFont(new Font("Monospaced", 1, 7));
/*  93 */     y += 30;
/*  94 */     graphics.drawString("Número: " + this.ticket.getNumero(), 10, y);
/*  95 */     y += 15;
/*  96 */     graphics.drawString("Fecha: " + fechaConFormato(this.ticket.getFecha()), 10, y);
/*  97 */     y += 20;
/*  98 */     graphics.drawString("--------------------------------------", 10, y);
/*  99 */     y += 10;
/* 100 */     int temp = 0;
/* 101 */     for (VentaPOS ventaPOS : this.lineas) {
/* 102 */       String descripcion = ventaPOS.getDescripcion();
/*     */ 
/* 106 */       String linea = String.format("%16.16s %,8.2f %3s %,8.2f", new Object[] { descripcion.toUpperCase(), Double.valueOf(ventaPOS.getImporte()), "x" + ventaPOS.getUnidades(), Double.valueOf(ventaPOS.getTotal()) });
/*     */ 
/* 109 */       graphics.drawString(linea, 10, y);
/* 110 */       y += 10;
/* 111 */       linea = String.format("%20.20s %,8.2f", new Object[] { "Pagos anteriores", this.pagosAnteriores.get(temp) });
/* 112 */       graphics.drawString(linea, 10, y);
/* 113 */       y += 10;
/* 114 */       linea = String.format("%20.20s %,8.2f", new Object[] { "Total pagado", this.totalPagado.get(temp) });
/* 115 */       graphics.drawString(linea, 10, y);
/* 116 */       y += 15;
/* 117 */       total += ventaPOS.getTotal();
/* 118 */       temp++;
/*     */     }
/* 120 */     y -= 5;
/* 121 */     graphics.drawString("--------------------------------------", 10, y);
/* 122 */     y += 20;
/* 123 */     g2d.setFont(new Font("Monospaced", 1, 8));
/* 124 */     graphics.drawString(String.format("%20s %,10.2f", new Object[] { "TOTAL (IVA incluido)", Double.valueOf(total) }), 10, y);
/* 125 */     g2d.setFont(new Font("Monospaced", 1, 7));
/* 126 */     if (this.entregado != null) {
/* 127 */       y += 15;
/* 128 */       graphics.drawString(String.format("%20s %,10.2f €", new Object[] { "Entrega ", this.entregado }), 10, y);
/* 129 */       y += 10;
/* 130 */       graphics.drawString(String.format("%20s %,10.2f €", new Object[] { "Devolución ", Double.valueOf(this.entregado.doubleValue() - total) }), 10, y);
/*     */     }
/* 132 */     y += 20;
/* 133 */     graphics.drawString("Forma de pago: " + this.formaPago, 10, y);
/* 134 */     y += 15;
/* 135 */     graphics.drawString("Le atendió " + this.vendedor, 10, y);
/* 136 */     y += 20;
/* 137 */     g2d.setFont(new Font("Monospaced", 0, 8));
/* 138 */     graphics.drawString("GRACIAS POR SU VISITA", 20, y);
/*     */ 
/* 141 */     return 0;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.control.TicketImprimiblePlazosFin
 * JD-Core Version:    0.6.2
 */