/*     */ package pos.control;
/*     */ 
/*     */ import com.sun.image.codec.jpeg.ImageFormatException;
/*     */ import contaes.Inicio;
/*     */ import contaes.Puente;
/*     */ import contaes.manejoDatos.ManejoEmpresas;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.print.PageFormat;
/*     */ import java.awt.print.Printable;
/*     */ import java.awt.print.PrinterException;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import javax.imageio.ImageIO;
/*     */ import pos.model.Ticket;
/*     */ import pos.model.VentaPOS;
/*     */ 
/*     */ public class TicketImprimible
/*     */   implements Printable
/*     */ {
/*     */   protected Ticket ticket;
/*     */   protected ArrayList<VentaPOS> lineas;
/*     */   protected String vendedor;
/*     */   protected String formaPago;
/*  34 */   protected String cabecera = "";
/*  35 */   protected String nif = "";
/*  36 */   protected String direccion = "";
/*  37 */   protected String provincia = "";
/*  38 */   protected String telefono = "";
/*  39 */   protected Double entregado = null;
/*     */ 
/*     */   public TicketImprimible(Ticket ticket, ArrayList<VentaPOS> lineas, String vendedor, String formaPago, Double entregado) {
/*  42 */     this.ticket = ticket;
/*  43 */     this.lineas = lineas;
/*  44 */     this.vendedor = vendedor;
/*  45 */     this.formaPago = formaPago;
/*  46 */     this.entregado = entregado;
/*  47 */     getCabecera();
/*     */   }
/*     */ 
/*     */   public void setLineas(ArrayList<VentaPOS> lineas) {
/*  51 */     this.lineas = lineas;
/*     */   }
/*     */ 
/*     */   public void setTicket(Ticket ticket) {
/*  55 */     this.ticket = ticket;
/*     */   }
/*     */ 
/*     */   public void setFormaPago(String formaPago) {
/*  59 */     this.formaPago = formaPago;
/*     */   }
/*     */ 
/*     */   public void setVendedor(String vendedor) {
/*  63 */     this.vendedor = vendedor;
/*     */   }
/*     */ 
/*     */   protected void getCabecera() {
/*  67 */     ManejoEmpresas mE = new ManejoEmpresas(Inicio.getCGeneral(), Inicio.p.getEmpresa());
/*  68 */     String nuestroNombre = mE.getNombre(Inicio.p.getEmpresa());
/*  69 */     String[] empresa = mE.datosEmpresa(nuestroNombre);
/*  70 */     mE.cerrarRs();
/*  71 */     this.cabecera = centrarTexto(nuestroNombre.toUpperCase(), 31);
/*  72 */     if (empresa[0] != null) {
/*  73 */       this.nif = centrarTexto("NIF: " + empresa[0], 40);
/*     */     }
/*  75 */     if (empresa[1] != null) {
/*  76 */       this.direccion = centrarTexto(empresa[1], 40);
/*     */     }
/*  78 */     if (empresa[4] != null) {
/*  79 */       this.provincia = (empresa[4] + " - ");
/*     */     }
/*  81 */     if (empresa[3] != null) {
/*  82 */       this.provincia += empresa[3];
/*     */     }
/*  84 */     this.provincia = centrarTexto(this.provincia, 40);
/*  85 */     if (empresa[5] != null) {
/*  86 */       this.telefono = empresa[5];
/*     */     }
/*  88 */     if (empresa[6] != null) {
/*  89 */       this.telefono = (this.telefono + " - " + empresa[6]);
/*     */     }
/*  91 */     this.telefono = centrarTexto(this.telefono, 40);
/*     */   }
/*     */ 
/*     */   protected String centrarTexto(String texto, int cpl) {
/*  95 */     if (texto.length() > cpl) {
/*  96 */       return texto.substring(0, cpl);
/*     */     }
/*  98 */     if (texto.length() == cpl) {
/*  99 */       return texto;
/*     */     }
/*     */ 
/* 102 */     int x = (cpl - texto.length()) / 2;
/* 103 */     String espacios = "                    ";
/* 104 */     texto = espacios.substring(0, x) + texto;
/* 105 */     return texto;
/*     */   }
/*     */ 
/*     */   protected String fechaConFormato(Date fecha)
/*     */   {
/* 110 */     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
/* 111 */     return sdf.format(fecha);
/*     */   }
/*     */ 
/*     */   public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException
/*     */   {
/* 116 */     if (pageIndex > 0) {
/* 117 */       return 1;
/*     */     }
/*     */ 
/* 123 */     Graphics2D g2d = (Graphics2D)graphics;
/* 124 */     g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
/* 125 */     g2d.setFont(new Font("Monospaced", 1, 8));
/*     */ 
/* 129 */     int y = 10;
/* 130 */     double total = 0.0D;
/* 131 */     boolean logo = false;
/*     */     try {
/* 133 */       File archivo = new File("configdir/logo.jpg");
/* 134 */       Integer anchoEstablecido = Integer.valueOf(165);
/* 135 */       if ((archivo != null) && (archivo.exists())) {
/* 136 */         BufferedImage imagen = ImageIO.read(archivo);
/* 137 */         Integer ancho = Integer.valueOf(imagen.getWidth());
/* 138 */         Double scaleFactor = Double.valueOf(anchoEstablecido.doubleValue() / ancho.doubleValue());
/* 139 */         Double yTrans = Double.valueOf(imagen.getHeight() * scaleFactor.doubleValue());
/* 140 */         g2d.drawImage(imagen, 10, y, anchoEstablecido.intValue(), yTrans.intValue(), null);
/* 141 */         y += yTrans.intValue() + 10;
/* 142 */         logo = true;
/*     */       }
/*     */     } catch (IOException e) {
/* 145 */       e.printStackTrace(); } catch (ImageFormatException e) {
/* 146 */       e.printStackTrace();
/* 147 */     }if (!logo) {
/* 148 */       graphics.drawString(this.cabecera, 10, y);
/* 149 */       y += 10;
/*     */     }
/* 151 */     g2d.setFont(new Font("Monospaced", 1, 6));
/* 152 */     graphics.drawString(this.nif, 10, y);
/* 153 */     y += 10;
/* 154 */     graphics.drawString(this.direccion, 10, y);
/* 155 */     y += 10;
/* 156 */     graphics.drawString(this.provincia, 10, y);
/* 157 */     y += 10;
/* 158 */     graphics.drawString(this.telefono, 10, y);
/*     */ 
/* 160 */     g2d.setFont(new Font("Monospaced", 1, 7));
/* 161 */     y += 30;
/* 162 */     graphics.drawString("Número: " + this.ticket.getNumero(), 10, y);
/* 163 */     y += 15;
/* 164 */     graphics.drawString("Fecha: " + fechaConFormato(this.ticket.getFecha()), 10, y);
/* 165 */     y += 20;
/* 166 */     graphics.drawString("--------------------------------------", 10, y);
/* 167 */     y += 10;
/* 168 */     for (VentaPOS ventaPOS : this.lineas) {
/* 169 */       String descripcion = ventaPOS.getDescripcion();
/*     */ 
/* 173 */       String linea = String.format("%16.16s %,8.2f %3s %,8.2f", new Object[] { descripcion.toUpperCase(), Double.valueOf(ventaPOS.getImporte()), "x" + ventaPOS.getUnidades(), Double.valueOf(ventaPOS.getTotal()) });
/*     */ 
/* 176 */       graphics.drawString(linea, 10, y);
/* 177 */       y += 15;
/* 178 */       total += ventaPOS.getTotal();
/*     */     }
/* 180 */     y -= 5;
/* 181 */     graphics.drawString("--------------------------------------", 10, y);
/* 182 */     y += 20;
/* 183 */     g2d.setFont(new Font("Monospaced", 1, 8));
/* 184 */     graphics.drawString(String.format("%20s %,10.2f", new Object[] { "TOTAL (IVA incluido)", Double.valueOf(total) }), 10, y);
/* 185 */     g2d.setFont(new Font("Monospaced", 1, 7));
/* 186 */     if (this.entregado != null) {
/* 187 */       y += 15;
/* 188 */       graphics.drawString(String.format("%20s %,10.2f €", new Object[] { "Entrega ", this.entregado }), 10, y);
/* 189 */       y += 10;
/* 190 */       graphics.drawString(String.format("%20s %,10.2f €", new Object[] { "Devolución ", Double.valueOf(this.entregado.doubleValue() - total) }), 10, y);
/*     */     }
/* 192 */     y += 20;
/* 193 */     graphics.drawString("Forma de pago: " + this.formaPago, 10, y);
/* 194 */     y += 15;
/* 195 */     graphics.drawString("Le atendió " + this.vendedor, 10, y);
/* 196 */     y += 20;
/* 197 */     g2d.setFont(new Font("Monospaced", 0, 8));
/* 198 */     graphics.drawString("GRACIAS POR SU VISITA", 20, y);
/*     */ 
/* 201 */     return 0;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.control.TicketImprimible
 * JD-Core Version:    0.6.2
 */