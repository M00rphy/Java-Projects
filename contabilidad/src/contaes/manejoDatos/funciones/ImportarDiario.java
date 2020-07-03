/*     */ package contaes.manejoDatos.funciones;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.MarcoInicio;
/*     */ import contaes.Puente;
/*     */ import contaes.manejoDatos.ManejoApuntes;
/*     */ import contaes.manejoDatos.ManejoAsientos;
/*     */ import contaes.manejoDatos.ManejoFacturas;
/*     */ import contaes.manejoDatos.TipoApunte;
/*     */ import contaes.manejoDatos.TipoAsientoEx;
/*     */ import contaes.manejoDatos.TipoFacturaExtended;
/*     */ import contaes.manejoDatos.auxiliar.LeerFichero;
/*     */ import java.awt.Cursor;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.JFileChooser;
/*     */ import javax.swing.JOptionPane;
/*     */ 
/*     */ public class ImportarDiario
/*     */ {
/*     */   private ManejoAsientos mA;
/*     */   private ManejoApuntes mAp;
/*     */   private ManejoFacturas mF;
/*     */ 
/*     */   public void importar()
/*     */   {
/*  32 */     this.mA = new ManejoAsientos(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  33 */     this.mAp = new ManejoApuntes(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  34 */     this.mF = new ManejoFacturas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*     */ 
/*  36 */     ArrayList asto = new ArrayList();
/*     */ 
/*  38 */     TipoAsientoEx asiento = null;
/*  39 */     JFileChooser fc = new JFileChooser();
/*  40 */     int retorno = fc.showOpenDialog(Inicio.frame);
/*  41 */     if (retorno == 0) {
/*  42 */       File archivo = fc.getSelectedFile();
/*     */       try {
/*  44 */         LeerFichero entrada = new LeerFichero(archivo.getCanonicalPath());
/*     */ 
/*  46 */         String numeroControl = "primero";
/*  47 */         Inicio.frame.setCursor(Cursor.getPredefinedCursor(3));
/*     */         String linea;
/*  48 */         while ((linea = entrada.leer()) != null) {
/*  49 */           String numero = linea.substring(0, linea.indexOf(59));
/*  50 */           linea = linea.substring(linea.indexOf(59) + 1);
/*  51 */           String fecha = linea.substring(0, linea.indexOf(59));
/*  52 */           linea = linea.substring(linea.indexOf(59) + 1);
/*  53 */           String documento = linea.substring(0, linea.indexOf(59));
/*  54 */           linea = linea.substring(linea.indexOf(59) + 1);
/*  55 */           String marca = linea.substring(0, linea.indexOf(59));
/*  56 */           linea = linea.substring(linea.indexOf(59) + 1);
/*  57 */           String cuenta = linea.substring(0, linea.indexOf(59));
/*  58 */           linea = linea.substring(linea.indexOf(59) + 1);
/*  59 */           String concepto = linea.substring(0, linea.indexOf(59));
/*  60 */           linea = linea.substring(linea.indexOf(59) + 1);
/*  61 */           String DH = linea.substring(0, linea.indexOf(59));
/*  62 */           String importe = linea.substring(linea.indexOf(59) + 1);
/*  63 */           fecha = cambiarFecha(eliminarCaracteres(fecha));
/*  64 */           importe = corregirImporte(eliminarCaracteres(importe));
/*  65 */           numero = eliminarCaracteres(numero);
/*  66 */           cuenta = eliminarCaracteres(cuenta);
/*  67 */           if (cuenta.length() == 8) {
/*  68 */             ComprobarDato dato2 = new ComprobarDato(numero);
/*  69 */             ComprobarDato dato = new ComprobarDato(cuenta);
/*  70 */             ComprobarDato dato1 = new ComprobarDato(importe);
/*  71 */             if ((dato2.esEntero()) && (dato.esEntero()) && (dato1.esDoble())) {
/*  72 */               String[] apte = new String[8];
/*  73 */               apte[0] = numero;
/*  74 */               apte[1] = fecha;
/*  75 */               apte[2] = eliminarCaracteres(documento);
/*  76 */               apte[3] = eliminarCaracteres(marca);
/*  77 */               apte[4] = cuenta;
/*  78 */               apte[5] = eliminarCaracteres(concepto);
/*  79 */               apte[6] = eliminarCaracteres(DH);
/*  80 */               apte[7] = importe;
/*  81 */               if ((numeroControl.equals(apte[0])) || (numeroControl.equals("primero"))) {
/*  82 */                 asto.add(apte);
/*     */               }
/*     */               else {
/*  85 */                 asiento = componerAsiento(asto);
/*  86 */                 if (asiento != null) {
/*  87 */                   crearAsiento(asiento);
/*  88 */                   asiento = null;
/*     */                 }
/*  90 */                 asto.clear();
/*  91 */                 asto.add(apte);
/*     */               }
/*     */             } else {
/*  94 */               showMensaje("Número, cuenta o importe no válido en asiento: " + numeroControl);
/*     */             }
/*     */           } else {
/*  97 */             showMensaje("Longitud de código de subcuenta no válida para:\n" + eliminarCaracteres(cuenta));
/*     */           }
/*  99 */           numeroControl = numero;
/*     */         }
/* 101 */         if (!asto.isEmpty()) {
/* 102 */           asiento = componerAsiento(asto);
/* 103 */           if (asiento != null)
/* 104 */             crearAsiento(asiento);
/*     */         }
/* 106 */         RegeneraSaldos.regenera(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 107 */         Inicio.frame.renovarTabla(0);
/* 108 */         System.gc();
/* 109 */         JOptionPane.showMessageDialog(Inicio.frame, "Importación finalizada", "Información", 1);
/* 110 */         Inicio.frame.setCursor(Cursor.getPredefinedCursor(0));
/*     */       } catch (IOException ex) {
/* 112 */         showMensaje("Hubo algún error al importar los datos:\n" + ex.getMessage());
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private String eliminarCaracteres(String cadena)
/*     */   {
/* 119 */     String retorno = cadena.replace("\"", "");
/* 120 */     retorno = retorno.replace(";", "");
/* 121 */     return retorno;
/*     */   }
/*     */ 
/*     */   private String cambiarFecha(String fecha) {
/* 125 */     return fecha.substring(6) + "-" + fecha.substring(3, 5) + "-" + fecha.substring(0, 2);
/*     */   }
/*     */ 
/*     */   private String corregirImporte(String cadena) {
/* 129 */     String retorno = cadena.replace(".", "");
/* 130 */     retorno = retorno.replace(",", ".");
/* 131 */     return retorno;
/*     */   }
/*     */ 
/*     */   private TipoAsientoEx componerAsiento(ArrayList<String[]> lista) {
/* 135 */     TipoAsientoEx asiento = null;
/* 136 */     int numero = Integer.parseInt(((String[])lista.get(0))[0]);
/* 137 */     String fecha = ((String[])lista.get(0))[1];
/* 138 */     String documento = ((String[])lista.get(0))[2];
/* 139 */     if (documento.length() > 20) {
/* 140 */       documento = documento.substring(0, 20);
/*     */     }
/* 142 */     String marca = ((String[])lista.get(0))[3];
/* 143 */     if (marca.length() > 1) {
/* 144 */       marca = marca.substring(0, 1);
/*     */     }
/* 146 */     ArrayList apuntes = new ArrayList();
/*     */ 
/* 148 */     for (String[] apte : lista) {
/* 149 */       int cuenta = Integer.parseInt(apte[4]);
/* 150 */       String concepto = apte[5];
/* 151 */       if (concepto.length() > 30) {
/* 152 */         concepto = concepto.substring(0, 30);
/*     */       }
/* 154 */       String DH = apte[6].toUpperCase();
/* 155 */       if (DH.length() > 1) {
/* 156 */         DH = DH.substring(0, 1);
/*     */       }
/* 158 */       double importe = Double.parseDouble(apte[7]);
/* 159 */       TipoApunte apunte = new TipoApunte(-1, -1, cuenta, concepto, DH, importe);
/* 160 */       apuntes.add(apunte);
/*     */     }
/* 162 */     asiento = new TipoAsientoEx(-1, numero, fecha, documento, marca, apuntes, null);
/* 163 */     return asiento;
/*     */   }
/*     */ 
/*     */   private void componerfactura(TipoAsientoEx asiento) {
/* 167 */     String numero = asiento.getDocumento();
/* 168 */     String fecha = asiento.getFecha();
/* 169 */     String concepto = "";
/* 170 */     int cuenta = -1;
/* 171 */     double base = 0.0D; double iva = 0.0D; double total = 0.0D;
/* 172 */     boolean emit = asiento.getMarca().equalsIgnoreCase("E");
/* 173 */     for (TipoApunte apunte : asiento.getApuntes()) {
/* 174 */       String cuentaTemp = Integer.toString(apunte.getCuenta());
/* 175 */       if ((cuentaTemp.substring(0, 1).equals("6")) || (cuentaTemp.substring(0, 1).equals("7"))) {
/* 176 */         if ((emit) && (apunte.getDH().equals("H")))
/* 177 */           base += apunte.getImporte();
/* 178 */         else if ((emit) && (apunte.getDH().equals("D")))
/* 179 */           base -= apunte.getImporte();
/* 180 */         else if ((!emit) && (apunte.getDH().equals("D")))
/* 181 */           base += apunte.getImporte();
/* 182 */         else if ((!emit) && (apunte.getDH().equals("H"))) {
/* 183 */           base -= apunte.getImporte();
/*     */         }
/*     */       }
/* 186 */       else if ((cuentaTemp.substring(0, 3).equals("472")) || (cuentaTemp.substring(0, 3).equals("477"))) {
/* 187 */         if ((emit) && (apunte.getDH().equals("H")))
/* 188 */           iva += apunte.getImporte();
/* 189 */         else if ((emit) && (apunte.getDH().equals("D")))
/* 190 */           iva -= apunte.getImporte();
/* 191 */         else if ((!emit) && (apunte.getDH().equals("D")))
/* 192 */           iva += apunte.getImporte();
/* 193 */         else if ((!emit) && (apunte.getDH().equals("H")))
/* 194 */           iva -= apunte.getImporte();
/*     */       }
/*     */       else {
/* 197 */         if (!cuentaTemp.substring(0, 2).equals("47")) {
/* 198 */           cuenta = apunte.getCuenta();
/*     */         }
/* 200 */         if ((emit) && (apunte.getDH().equals("H")))
/* 201 */           total -= apunte.getImporte();
/* 202 */         else if ((emit) && (apunte.getDH().equals("D")))
/* 203 */           total += apunte.getImporte();
/* 204 */         else if ((!emit) && (apunte.getDH().equals("D")))
/* 205 */           total -= apunte.getImporte();
/* 206 */         else if ((!emit) && (apunte.getDH().equals("H")))
/* 207 */           total += apunte.getImporte(); 
/*     */       }
/*     */     }
/* 209 */     TipoFacturaExtended factura = new TipoFacturaExtended(numero, fecha, cuenta, -1, concepto, base, iva, total, 0);
/* 210 */     asiento.setFactura(factura);
/*     */   }
/*     */ 
/*     */   private void crearAsiento(TipoAsientoEx asiento) {
/* 214 */     if (!asiento.getFecha().substring(0, 4).equals(Inicio.p.getEjercicio())) {
/* 215 */       showMensaje("La fecha del asiento: " + asiento.getNumero() + "\n no es del ejercicio activo.");
/* 216 */       return;
/*     */     }
/* 218 */     if ((asiento.getMarca().equals("E")) || (asiento.getMarca().equals("R")))
/* 219 */       componerfactura(asiento);
/* 220 */     if (!asientoCuadrado(asiento)) {
/* 221 */       showMensaje("El asiento: " + asiento.getNumero() + " no está cuadrado\ny no se creará.");
/* 222 */       return;
/*     */     }
/* 224 */     boolean emitida = asiento.getMarca().equals("E");
/* 225 */     if ((asiento.getFactura() != null) && (this.mF.existeFactura(emitida, asiento.getFactura().getNumero()) != -1))
/*     */     {
/* 227 */       Object[] options = { "Copiar", "Reemplazar", "Cancelar" };
/*     */ 
/* 230 */       int n = JOptionPane.showOptionDialog(Inicio.frame, "Ya existe una factura de número " + asiento.getFactura().getNumero() + "\n¿Qué desea hacer?", "Factura existente", 1, 3, null, options, options[2]);
/*     */ 
/* 240 */       if ((n == -1) || (n == 2))
/* 241 */         return;
/* 242 */       if (n == 1)
/*     */       {
/* 244 */         this.mF.borrar(emitida, asiento.getFactura().getNumero());
/*     */       }
/* 245 */       else if (n != 0);
/*     */     }
/*     */ 
/* 250 */     int temp = this.mA.existeAsiento(Integer.valueOf(asiento.getNumero()), null, null, null);
/* 251 */     if (temp != -1) {
/* 252 */       asiento.setNumero(this.mA.nuevoNumero());
/*     */     }
/* 254 */     int idAsiento = this.mA.crear(asiento.getNumero(), asiento.getFecha(), asiento.getDocumento(), asiento.getMarca());
/* 255 */     if (idAsiento != -1) {
/* 256 */       boolean error = false;
/*     */ 
/* 258 */       for (TipoApunte apunte : asiento.getApuntes()) {
/* 259 */         apunte.setId_asiento(idAsiento);
/* 260 */         if (!this.mAp.crear(apunte)) {
/* 261 */           showMensaje("Error al crear apunte: " + apunte + "\nPara asiento: " + asiento);
/* 262 */           error = true;
/*     */         }
/*     */       }
/* 265 */       if (asiento.getFactura() != null) {
/* 266 */         asiento.getFactura().setId_asiento(idAsiento);
/* 267 */         if (!this.mF.crear(emitida, asiento.getFactura())) {
/* 268 */           showMensaje("Error al crear factura: " + asiento.getDocumento() + "\nPara el asiento: " + asiento);
/* 269 */           error = true;
/*     */         }
/*     */       }
/* 272 */       if (error) {
/* 273 */         this.mA.borrar(idAsiento);
/* 274 */         this.mF.borrar(emitida, asiento.getFactura().getNumero());
/*     */       }
/*     */     } else {
/* 277 */       showMensaje("Error al crear asiento: " + asiento);
/*     */     }
/*     */   }
/*     */ 
/*     */   private boolean asientoCuadrado(TipoAsientoEx asiento) {
/* 282 */     double saldo = 0.0D;
/* 283 */     for (TipoApunte apunte : asiento.getApuntes()) {
/* 284 */       if (apunte.getDH().equalsIgnoreCase("D")) {
/* 285 */         saldo += apunte.getImporte();
/*     */       }
/*     */       else {
/* 288 */         saldo -= apunte.getImporte();
/*     */       }
/*     */     }
/* 291 */     if ((saldo > 0.005D) || (saldo < -0.005D)) {
/* 292 */       return false;
/*     */     }
/* 294 */     return true;
/*     */   }
/*     */ 
/*     */   private void showMensaje(String mensaje) {
/* 298 */     JOptionPane.showMessageDialog(Inicio.frame, mensaje, "Error", 0);
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.funciones.ImportarDiario
 * JD-Core Version:    0.6.2
 */