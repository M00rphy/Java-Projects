/*     */ package pos.control;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.Puente;
/*     */ import contaes.manejoDatos.ManejoAgenda;
/*     */ import contaes.manejoDatos.ManejoFormasPago;
/*     */ import contaes.manejoDatos.ManejoSubcuentas;
/*     */ import contaes.manejoDatos.ManejoTiposIVA;
/*     */ import contaes.manejoDatos.TipoFormaPago;
/*     */ import contaes.manejoDatos.TipoIVA;
/*     */ import contaes.manejoDatos.TipoSubcuenta;
/*     */ import facturacion.control.AlmacenControl;
/*     */ import facturacion.control.FacturaControl;
/*     */ import facturacion.control.LineaFacturaControl;
/*     */ import facturacion.model.Factura;
/*     */ import facturacion.model.LineaFactura;
/*     */ import facturacion.model.Producto;
/*     */ import java.util.ArrayList;
import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.swing.JOptionPane;
/*     */ import pos.model.Ticket;
/*     */ import pos.model.VentaPOS;
/*     */ 
/*     */ public class TicketsToFacturacion
/*     */ {
/*     */   private int[] rangoTickets;
/*     */   private boolean conRecargo;
/*     */   private boolean borrar;
/*     */ 
/*     */   public TicketsToFacturacion(int[] rangoTickets, boolean conRecargo, boolean borrar)
/*     */   {
/*  38 */     this.rangoTickets = rangoTickets;
/*  39 */     this.conRecargo = conRecargo;
/*  40 */     this.borrar = borrar;
/*     */   }
/*     */ 
/*     */   public void realizarTraspaso() {
/*  44 */     pasarAFacturacion();
/*     */   }
/*     */ 
/*     */   private void pasarAFacturacion() {
/*  48 */     if (this.rangoTickets.length != 2) {
/*  49 */       return;
/*     */     }
/*     */ 
/*  52 */     TicketsControl tC = new TicketsControl(Inicio.getcAlmacen());
/*  53 */     VentaPosControl vPC = new VentaPosControl(Inicio.getcAlmacen());
/*  54 */     FacturaControl fC = new FacturaControl(Inicio.getCFacturacion(), true);
/*  55 */     ManejoSubcuentas mS = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  56 */     ManejoAgenda mA = new ManejoAgenda(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  57 */     ManejoFormasPago mFP = new ManejoFormasPago(Inicio.getCGeneral());
/*  58 */     LineaFacturaControl lFC = new LineaFacturaControl(Inicio.getCFacturacion(), true);
/*  59 */     AlmacenControl aC = new AlmacenControl();
/*  60 */     ManejoTiposIVA mTI = new ManejoTiposIVA(Inicio.getCGeneral());
/*     */ 
/*  62 */     for (int nTicket = this.rangoTickets[0]; nTicket <= this.rangoTickets[1]; nTicket++)
/*     */     {
/*  64 */       int idTicket = tC.getIdTicket(nTicket);
/*  65 */       Ticket ticket = tC.getTicket(idTicket);
/*  66 */       ArrayList lineasTicket = vPC.getTodasVentasPosPorTicket(idTicket);
/*  67 */       if (ticket == null) {
/*  68 */         JOptionPane.showMessageDialog(Inicio.frame, "El número de ticket: " + nTicket + " no es válido.\n" + "No se creará factura para él.", "Error", 0);
/*     */ 
/*  72 */         break;
/*     */       }
/*     */ 
/*  75 */       String numero = fC.nuevoNumeroFactEmitida(Inicio.p.getPrefijo());
/*  76 */       TipoSubcuenta cliente = mS.datos(ticket.getCliente());
/*  77 */       if (cliente == null) {
/*  78 */         JOptionPane.showMessageDialog(Inicio.frame, "El ticket: " + nTicket + " tiene asignado un cliente no válido.\n" + "No se creará factura para él.", "Error", 0);
/*     */ 
/*  82 */         break;
/*     */       }
/*     */ 
/*  85 */       TipoFormaPago formaPago = null;
/*  86 */       Integer idFormaPago = mA.formaPago(Integer.toString(cliente.getCodigo()));
/*  87 */       if (idFormaPago != null) {
/*  88 */         formaPago = mFP.getFormaPago(idFormaPago.intValue());
/*     */       }
/*  90 */       Factura factura = new Factura(Integer.valueOf(-1), numero, cliente, ticket.getFecha(), Double.valueOf(0.0D), this.conRecargo, formaPago, Double.valueOf(0.0D), Double.valueOf(0.0D), false, (ticket.isCerrado() == 1) || (ticket.isCerrado() == 3));
/*     */       try {
/*  92 */         factura.setId(fC.crear(factura));
/*     */       } catch (Exception ex) {
/*  94 */         Logger.getLogger(TicketsToFacturacion.class.getName()).log(Level.SEVERE, null, ex);
/*     */       }
/*  96 */       if (factura.getId().intValue() == -1) {
/*  97 */         JOptionPane.showMessageDialog(Inicio.frame, "Imposible crear factura para el ticket: " + nTicket, "Error", 0);
/*     */ 
/* 100 */         break;
/*     */       }
/*     */ 
/* 103 */       double baseTotal = 0.0D; double ivaTotal = 0.0D; double totalTotal = 0.0D;
/* 104 */       int totalLineas = 0;
/*     */ 
/* 106 */       for (VentaPOS ventaPOS : (List<VentaPOS>)lineasTicket) {
/* 107 */         Producto producto = aC.getProducto(Integer.valueOf(ventaPOS.getIdProducto()), true);
/* 108 */         if (producto == null) {
/* 109 */           JOptionPane.showMessageDialog(Inicio.frame, "El producto: " + ventaPOS.getDescripcion() + "\n" + "del ticket: " + nTicket + " no es válido.\n" + "Esta línea no se incluirá en la factura.", "Error", 0);
/*     */ 
/* 114 */           break;
/*     */         }
/* 116 */         TipoIVA tipoIva = mTI.getTipoIVA(aC.getIvaProducto(ventaPOS.getIdProducto()));
/* 117 */         if (tipoIva == null) {
/* 118 */           JOptionPane.showMessageDialog(Inicio.frame, "El producto: " + producto.getDescripcion() + "\n" + "del ticket: " + nTicket + " tiene IVA nulo o no válido.\n" + "Esta línea no se incluirá en la factura.", "Error", 0);
/*     */ 
/* 123 */           break;
/*     */         }
/* 125 */         double total = ventaPOS.getTotal();
/* 126 */         double porIva = tipoIva.getIva() / 100.0D;
/* 127 */         if (this.conRecargo) {
/* 128 */           porIva += tipoIva.getRe() / 100.0D;
/*     */         }
/* 130 */         double base = total / (1.0D + porIva);
/* 131 */         double iva = total - base;
/*     */ 
/* 133 */         baseTotal += base;
/* 134 */         ivaTotal += iva;
/* 135 */         totalTotal += total;
/* 136 */         double unidades = ventaPOS.getUnidades();
/*     */ 
/* 138 */         LineaFactura lineaFactura = new LineaFactura(Integer.valueOf(-1), factura.getId(), producto, ventaPOS.getDescripcion(), Double.valueOf(base), tipoIva, Double.valueOf(total), Double.valueOf(unidades));
/*     */         try {
/* 140 */           lFC.crear(lineaFactura);
/* 141 */           totalLineas++;
/*     */         } catch (Exception ex) {
/* 143 */           Logger.getLogger(TicketsToFacturacion.class.getName()).log(Level.SEVERE, null, ex);
/*     */         }
/*     */       }
/*     */ 
/* 147 */       factura.setBase(Double.valueOf(baseTotal));
/* 148 */       factura.setIva(Double.valueOf(ivaTotal));
/* 149 */       factura.setTotal(Double.valueOf(totalTotal));
/*     */       try {
/* 151 */         if (totalLineas > 0) {
/* 152 */           fC.modificar(factura);
/*     */         } else {
/* 154 */           JOptionPane.showMessageDialog(Inicio.frame, "La factura para el ticket: " + nTicket + "\n" + "no contiene líneas y no se creará.", "Error", 0);
/*     */ 
/* 158 */           fC.borrar(factura);
/* 159 */           break;
/*     */         }
/*     */       } catch (Exception ex) {
/* 162 */         Logger.getLogger(TicketsToFacturacion.class.getName()).log(Level.SEVERE, null, ex);
/*     */       }
/* 164 */       if (this.borrar) {
/* 165 */         tC.borrar(ticket);
/*     */       }
/*     */     }
/*     */ 
/* 169 */     lFC.cerrarRs();
/* 170 */     aC.cerrarConexion();
/* 171 */     mTI.cerrarRs();
/* 172 */     tC.cerrarRs();
/* 173 */     vPC.cerrarRs();
/* 174 */     mS.cerrarRs();
/* 175 */     fC.cerrarRs();
/* 176 */     mA.cerrarRs();
/* 177 */     mFP.cerrarRs();
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.control.TicketsToFacturacion
 * JD-Core Version:    0.6.2
 */