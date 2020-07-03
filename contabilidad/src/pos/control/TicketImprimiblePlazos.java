/*     */ package pos.control;
/*     */ 
/*     */ import almacen2.data.ManejadorOldAlmacen;
/*     */ import almacen2.data.ManejadorProducto;
/*     */ import almacen2.data.ProductObject;
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
/*     */ public class TicketImprimiblePlazos extends TicketImprimible
/*     */ {
/*  30 */   private ArrayList<Double> pagosAnteriores = new ArrayList();
/*  31 */   private ArrayList<Double> totalPagado = new ArrayList();
/*  32 */   private ArrayList<Double> pvpProducto = new ArrayList();
/*     */ 
/*     */   public TicketImprimiblePlazos(Ticket ticket, ArrayList<VentaPOS> lineas, String vendedor, String formaPago, Double entregado) {
/*  35 */     super(ticket, lineas, vendedor, formaPago, entregado);
/*  36 */     setPlazos();
/*  37 */     setPvp();
/*     */   }
/*     */ 
/*     */   private void setPlazos() {
/*  41 */     ResumenControl rC = new ResumenControl(Inicio.getcAlmacen());
/*  42 */     for (VentaPOS venta : this.lineas) {
/*  43 */       double pA = rC.plazosAnteriores(venta.getIdProducto(), this.ticket.getIdplazoanterior());
/*  44 */       double tP = venta.getTotal() + pA;
/*  45 */       this.pagosAnteriores.add(Double.valueOf(pA));
/*  46 */       this.totalPagado.add(Double.valueOf(tP));
/*     */     }
/*  48 */     rC.cerrarRs();
/*     */   }
/*     */   private void setPvp() {
/*  51 */     ProductObject producto = null;
/*  52 */     ManejadorProducto mP = new ManejadorProducto(Inicio.getcAlmacen());
/*  53 */     ManejadorOldAlmacen mOA = new ManejadorOldAlmacen();
/*  54 */     for (VentaPOS venta : this.lineas) {
/*  55 */       producto = mP.obtenerProducto(venta.getIdProducto());
/*     */ 
/*  63 */       double pvp = producto.getPvp();
/*  64 */       this.pvpProducto.add(Double.valueOf(pvp));
/*     */     }
/*     */ 
/*  67 */     mOA.cerrarConexion();
/*     */   }
/*     */ 
/*     */   public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException
/*     */   {
/*  72 */     if (pageIndex > 0) {
/*  73 */       return 1;
/*     */     }
/*     */ 
/*  79 */     Graphics2D g2d = (Graphics2D)graphics;
/*  80 */     g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
/*  81 */     g2d.setFont(new Font("Monospaced", 1, 8));
/*     */ 
/*  85 */     int y = 10;
/*  86 */     double total = 0.0D;
/*  87 */     boolean logo = false;
/*     */     try {
/*  89 */       File archivo = new File("configdir/logo.jpg");
/*  90 */       Integer anchoEstablecido = Integer.valueOf(165);
/*  91 */       if ((archivo != null) && (archivo.exists())) {
/*  92 */         BufferedImage imagen = ImageIO.read(archivo);
/*  93 */         Integer ancho = Integer.valueOf(imagen.getWidth());
/*  94 */         Double scaleFactor = Double.valueOf(anchoEstablecido.doubleValue() / ancho.doubleValue());
/*  95 */         Double yTrans = Double.valueOf(imagen.getHeight() * scaleFactor.doubleValue());
/*  96 */         g2d.drawImage(imagen, 10, y, anchoEstablecido.intValue(), yTrans.intValue(), null);
/*  97 */         y += yTrans.intValue() + 10;
/*  98 */         logo = true;
/*     */       }
/*     */     } catch (IOException e) {
/* 101 */       e.printStackTrace(); } catch (ImageFormatException e) {
/* 102 */       e.printStackTrace();
/* 103 */     }if (!logo) {
/* 104 */       graphics.drawString(this.cabecera, 10, y);
/* 105 */       y += 10;
/*     */     }
/* 107 */     g2d.setFont(new Font("Monospaced", 1, 6));
/* 108 */     graphics.drawString(this.nif, 10, y);
/* 109 */     y += 10;
/* 110 */     graphics.drawString(this.direccion, 10, y);
/* 111 */     y += 10;
/* 112 */     graphics.drawString(this.provincia, 10, y);
/* 113 */     y += 10;
/* 114 */     graphics.drawString(this.telefono, 10, y);
/*     */ 
/* 116 */     g2d.setFont(new Font("Monospaced", 1, 7));
/* 117 */     y += 30;
/* 118 */     graphics.drawString("Número: " + this.ticket.getNumero(), 10, y);
/* 119 */     y += 15;
/* 120 */     graphics.drawString("Fecha: " + fechaConFormato(this.ticket.getFecha()), 10, y);
/* 121 */     y += 20;
/* 122 */     graphics.drawString("--------------------------------------", 10, y);
/* 123 */     y += 10;
/* 124 */     int temp = 0;
/* 125 */     for (VentaPOS ventaPOS : this.lineas) {
/* 126 */       String descripcion = ventaPOS.getDescripcion();
/*     */ 
/* 130 */       String linea = String.format("%16.16s %,8.2f %3s %,8.2f", new Object[] { descripcion.toUpperCase(), Double.valueOf(ventaPOS.getImporte()), "x" + ventaPOS.getUnidades(), Double.valueOf(ventaPOS.getTotal()) });
/*     */ 
/* 133 */       graphics.drawString(linea, 10, y);
/* 134 */       y += 10;
/* 135 */       linea = String.format("%20.20s %,8.2f", new Object[] { "Pagos anteriores", this.pagosAnteriores.get(temp) });
/* 136 */       graphics.drawString(linea, 10, y);
/* 137 */       y += 10;
/* 138 */       linea = String.format("%20.20s %,8.2f", new Object[] { "Total pagado", this.totalPagado.get(temp) });
/* 139 */       graphics.drawString(linea, 10, y);
/* 140 */       y += 10;
/* 141 */       linea = String.format("%20.20s %,8.2f", new Object[] { "Pendiente sobre PVP", Double.valueOf(((Double)this.pvpProducto.get(temp)).doubleValue() - ((Double)this.totalPagado.get(temp)).doubleValue()) });
/* 142 */       graphics.drawString(linea, 10, y);
/* 143 */       y += 15;
/* 144 */       total += ventaPOS.getTotal();
/* 145 */       temp++;
/*     */     }
/* 147 */     y -= 5;
/* 148 */     graphics.drawString("--------------------------------------", 10, y);
/* 149 */     y += 20;
/* 150 */     g2d.setFont(new Font("Monospaced", 1, 8));
/* 151 */     graphics.drawString(String.format("%20s %,10.2f", new Object[] { "TOTAL (IVA incluido)", Double.valueOf(total) }), 10, y);
/* 152 */     g2d.setFont(new Font("Monospaced", 1, 7));
/* 153 */     if (this.entregado != null) {
/* 154 */       y += 15;
/* 155 */       graphics.drawString(String.format("%20s %,10.2f €", new Object[] { "Entrega ", this.entregado }), 10, y);
/* 156 */       y += 10;
/* 157 */       graphics.drawString(String.format("%20s %,10.2f €", new Object[] { "Devolución ", Double.valueOf(this.entregado.doubleValue() - total) }), 10, y);
/*     */     }
/* 159 */     y += 20;
/* 160 */     graphics.drawString("Forma de pago: " + this.formaPago, 10, y);
/* 161 */     y += 15;
/* 162 */     graphics.drawString("Le atendió " + this.vendedor, 10, y);
/* 163 */     y += 20;
/* 164 */     g2d.setFont(new Font("Monospaced", 0, 8));
/* 165 */     graphics.drawString("GRACIAS POR SU VISITA", 20, y);
/*     */ 
/* 168 */     return 0;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.control.TicketImprimiblePlazos
 * JD-Core Version:    0.6.2
 */