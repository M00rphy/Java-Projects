/*     */ package contaes;
/*     */ 
/*     */ import contaes.manejoDatos.ManejoApuntes;
/*     */ import contaes.manejoDatos.ManejoAsientos;
/*     */ import contaes.manejoDatos.ManejoFacturas;
/*     */ import contaes.manejoDatos.TipoFactura;
/*     */ import contaes.manejoDatos.auxiliar.LeerFichero;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.swing.JOptionPane;
/*     */ 
/*     */ public class ImportarFacturas
/*     */ {
/*     */   private ManejoFacturas mF;
/*     */   private ManejoAsientos mAs;
/*     */   private ManejoApuntes mAp;
/*     */ 
/*     */   public ImportarFacturas()
/*     */   {
/*  36 */     importar();
/*     */   }
/*     */ 
/*     */   private void importar() {
/*  40 */     this.mF = new ManejoFacturas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  41 */     this.mAs = new ManejoAsientos(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  42 */     this.mAp = new ManejoApuntes(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*     */     try {
/*  44 */       LeerFichero entrada = new LeerFichero("facturas.csv");
/*     */       String linea;
/*  46 */       while ((linea = entrada.leer()) != null)
/*     */       {
/*  48 */         String numero = linea.substring(0, linea.indexOf(';'));
/*  49 */         linea = linea.substring(linea.indexOf(';') + 1);
/*  50 */         String fecha = linea.substring(0, linea.indexOf(';'));
/*  51 */         linea = linea.substring(linea.indexOf(';') + 1);
/*  52 */         String concepto = linea.substring(0, linea.indexOf(';'));
/*  53 */         linea = linea.substring(linea.indexOf(';') + 1);
/*  54 */         String totalS = linea.substring(0, linea.indexOf(';'));
/*  55 */         linea = linea.substring(linea.indexOf(';') + 1);
/*  56 */         String baseS = linea.substring(0, linea.indexOf(';'));
/*  57 */         linea = linea.substring(linea.indexOf(';') + 1);
/*  58 */         String ivaS = linea.substring(0, linea.indexOf(';'));
/*  59 */         linea = linea.substring(linea.indexOf(';') + 1);
/*  60 */         String ctaPagoS = linea.substring(0, linea.indexOf(';'));
/*  61 */         String ctaIngS = linea.substring(linea.indexOf(';') + 1);
/*     */ 
/*  63 */         numero = "0000".substring(numero.length()) + numero;
/*  64 */         fecha = fecha.substring(6) + fecha.substring(3, 5) + fecha.substring(0, 2);
/*  65 */         concepto = concepto.replace("\"", "");
/*  66 */         if (concepto.length() > 30) concepto = concepto.substring(0, 30); baseS = baseS.replace(".", "");
/*  68 */         baseS = baseS.replace(",", ".");
/*  69 */         Double base = Double.valueOf(Double.parseDouble(baseS));
/*  70 */         Double iva = Double.valueOf(base.doubleValue() * 0.16D);
/*  71 */         Double total = Double.valueOf(base.doubleValue() + iva.doubleValue());
/*     */         int cuentaPago;
/*     */         try { cuentaPago = obtenerCuentaPago(ctaPagoS);
/*     */         } catch (NumberFormatException e)
/*     */         {
/*  78 */           System.out.println("Revise la cuenta de pago para la factura " + numero);
/*  79 */           cuentaPago = 57000000;
/*     */         }int cuentaCliente;
/*     */         try {
/*  82 */           cuentaCliente = obtenerCuentaCliente(ctaPagoS);
/*     */         }
/*     */         catch (NumberFormatException e) {
/*  85 */           System.out.println("Revise la cuenta de cliente para la factura " + numero);
/*  86 */           cuentaCliente = 43000001;
/*     */         }
/*  88 */         ctaIngS = "7000000" + ctaIngS;
/*  89 */         int cuentaVentas = Integer.parseInt(ctaIngS);
/*     */ 
/*  91 */         TipoFactura factura = new TipoFactura();
/*  92 */         factura.setNumero(numero);
/*  93 */         factura.setFecha(fecha);
/*  94 */         factura.setConcepto(concepto);
/*  95 */         factura.setBase(base.doubleValue());
/*  96 */         factura.setIva(iva.doubleValue());
/*  97 */         factura.setTotal(total.doubleValue());
/*  98 */         factura.setCuenta(cuentaCliente);
/*     */ 
/* 100 */         int idAsiento = this.mAs.crear(this.mAs.nuevoNumero(), fecha, numero, "E");
/* 101 */         if (idAsiento != -1) {
/* 102 */           factura.setId_asiento(idAsiento);
/* 103 */           if (this.mF.crear(true, factura))
/*     */           {
/* 105 */             this.mAp.crear(idAsiento, cuentaCliente, "Factura nº " + numero, "D", total.doubleValue());
/* 106 */             this.mAp.crear(idAsiento, cuentaVentas, concepto, "H", base.doubleValue());
/* 107 */             this.mAp.crear(idAsiento, 47700000, "Factura nº " + numero, "H", iva.doubleValue());
/*     */ 
/* 109 */             int idAsiento2 = this.mAs.crear(this.mAs.nuevoNumero(), fecha, numero, "Q");
/* 110 */             if (idAsiento2 != -1) {
/* 111 */               this.mAp.crear(idAsiento2, cuentaPago, "Cobro factura " + numero, "D", total.doubleValue());
/* 112 */               this.mAp.crear(idAsiento2, cuentaCliente, "Cobro factura " + numero, "H", total.doubleValue());
/* 113 */               Double comision = Double.valueOf(0.0D);
/* 114 */               if (ctaPagoS.equals("2"))
/*     */               {
/* 116 */                 comision = Double.valueOf(total.doubleValue() * 0.0076D);
/*     */               }
/* 118 */               else if (ctaPagoS.equals("3"))
/*     */               {
/* 120 */                 comision = Double.valueOf(total.doubleValue() * 0.0095D);
/*     */               }
/* 122 */               if (comision.doubleValue() != 0.0D) {
/* 123 */                 this.mAp.crear(idAsiento2, cuentaPago, "Cobro factura " + numero, "H", comision.doubleValue());
/* 124 */                 this.mAp.crear(idAsiento2, 70690000, "Cobro factura " + numero, "D", comision.doubleValue());
/*     */               }
/*     */             }
/*     */           }
/*     */           else
/*     */           {
/* 130 */             this.mAs.borrar(idAsiento);
/*     */           }
/*     */         }
/*     */       }
/* 134 */       showMensaje("Importación terminada");
/*     */     }
/*     */     catch (IOException ex) {
/* 137 */       showMensaje("Hubo algún error al importar los datos");
/* 138 */       Logger.getLogger(ImportarFacturas.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/*     */   }
/*     */ 
/*     */   private int obtenerCuentaPago(String s) throws NumberFormatException
/*     */   {
/* 144 */     int opcion = Integer.parseInt(s);
/*     */     int cuenta;
/* 145 */     switch (opcion) {
/*     */     case 2:
/*     */     case 4:
/* 148 */       cuenta = 57200005;
/* 149 */       break;
/*     */     case 3:
/*     */     case 5:
/* 152 */       cuenta = 57200002;
/* 153 */       break;
/*     */     case 6:
/* 155 */       cuenta = 57200004;
/* 156 */       break;
/*     */     default:
/* 158 */       cuenta = 57000000;
/*     */     }
/* 160 */     return cuenta;
/*     */   }
/*     */ 
/*     */   private int obtenerCuentaCliente(String s) throws NumberFormatException
/*     */   {
/* 165 */     int opcion = Integer.parseInt(s);
/*     */     int cuenta;
/* 166 */     switch (opcion) {
/*     */     case 2:
/* 168 */       cuenta = 43000002;
/* 169 */       break;
/*     */     case 3:
/* 171 */       cuenta = 43000003;
/* 172 */       break;
/*     */     default:
/* 174 */       cuenta = 43000001;
/*     */     }
/* 176 */     return cuenta;
/*     */   }
/*     */ 
/*     */   private void showMensaje(String mensaje) {
/* 180 */     JOptionPane.showMessageDialog(Inicio.frame, mensaje);
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.ImportarFacturas
 * JD-Core Version:    0.6.2
 */