/*     */ package contaes.manejoDatos;
/*     */ 
/*     */ import contaes.manejoDatos.auxiliar.MySQLConection;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ 
/*     */ public class ManejoFacturas
/*     */ {
/*     */   private MySQLConection con;
/*     */   private String ejercicio;
/*     */   private ResultSet res;
/*     */ 
/*     */   public ManejoFacturas(MySQLConection con, String ejercicio)
/*     */   {
/*  19 */     this.con = con;
/*  20 */     this.ejercicio = ejercicio;
/*     */   }
/*     */ 
/*     */   public boolean crear(boolean emitida, TipoFactura factura) {
/*  24 */     return crear(emitida, factura.getNumero(), factura.getFecha(), factura.getCuenta(), factura.getId_asiento(), factura.getConcepto(), factura.getBase(), factura.getIva(), factura.getRe(), 0);
/*     */   }
/*     */ 
/*     */   public boolean crear(boolean emitida, TipoFacturaExtended factura)
/*     */   {
/*  30 */     return crear(emitida, factura.getNumero(), factura.getFecha(), factura.getCuenta(), factura.getId_asiento(), factura.getConcepto(), factura.getBase(), factura.getIva(), factura.getRe(), factura.getImportacion());
/*     */   }
/*     */ 
/*     */   public boolean crear(boolean emitida, String numero, String fecha, int cuenta, int id_asiento, String concepto, double base, double iva, double re, int importacion)
/*     */   {
/*  37 */     String cad = "INSERT INTO ";
/*     */ 
/*  42 */     if (emitida) {
/*  43 */       cad = new StringBuilder().append(cad).append(" emit").append(this.ejercicio).append(" (numero,fecha,cuenta,id_asiento,concepto,base,iva,re,total)").append(" VALUES('").append(numero).append("','").append(fecha).append("',").append(cuenta).append(",").append(id_asiento).append(",'").append(concepto).append("',").append(base).append(",").append(iva).append(",").append(re).append(",").append(base + iva + re).append(")").toString();
/*     */     }
/*     */     else
/*     */     {
/*  47 */       cad = new StringBuilder().append(cad).append(" reci").append(this.ejercicio).append(" (numero,fecha,cuenta,id_asiento,concepto,base,iva,total,importacion)").append(" VALUES('").append(numero).append("','").append(fecha).append("',").append(cuenta).append(",").append(id_asiento).append(",'").append(concepto).append("',").append(base).append(",").append(iva).append(",").append(base + iva).append(",").append(importacion).append(")").toString();
/*     */     }
/*     */ 
/*     */     try
/*     */     {
/*  52 */       this.con.getSentencia().executeUpdate(cad);
/*     */     } catch (SQLException exc) {
/*  54 */       exc.printStackTrace();
/*  55 */       return false;
/*     */     }
/*  57 */     return true;
/*     */   }
/*     */ 
/*     */   public void modificar(boolean emitida, TipoFactura factura) {
/*  61 */     modificar(emitida, factura.getNumero(), factura.getFecha(), factura.getCuenta(), factura.getConcepto(), factura.getBase(), factura.getIva());
/*     */   }
/*     */ 
/*     */   public void modificar(boolean emitida, String numero, String fecha, int cuenta, String concepto, double base, double iva)
/*     */   {
/*  68 */     String cadena = emitida ? "UPDATE emit" : "UPDATE reci";
/*     */     try {
/*  70 */       this.con.getSentencia().executeUpdate(new StringBuilder().append(cadena).append(this.ejercicio).append(" SET ").append("fecha = '").append(fecha).append("',cuenta = ").append(cuenta).append(",concepto ='").append(concepto).append("',").append("base =").append(base).append(",iva =").append(iva).append(", total = ").append(base + iva).append("WHERE numero='").append(numero).append("'").toString());
/*     */     }
/*     */     catch (SQLException e)
/*     */     {
/*  77 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void borrar(boolean emitida, String numero)
/*     */   {
/*  83 */     TipoFactura factura = new TipoFactura();
/*  84 */     factura = datos(emitida, numero);
/*  85 */     String tabla = emitida ? "emit" : "reci";
/*     */     try {
/*  87 */       this.con.getSentencia().executeUpdate(new StringBuilder().append("DELETE FROM ").append(tabla).append(this.ejercicio).append(" WHERE numero = '").append(numero).append("'").toString());
/*     */ 
/*  89 */       if (factura != null) {
/*  90 */         ManejoAsientos mA = new ManejoAsientos(this.con, this.ejercicio);
/*  91 */         mA.borrar(factura.getId_asiento());
/*     */       }
/*     */     } catch (SQLException e) {
/*  94 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public int existeFactura(boolean emitida, String numero)
/*     */   {
/* 107 */     String cad = "SELECT id_asiento FROM ";
/* 108 */     cad = new StringBuilder().append(cad).append(emitida ? "emit" : "reci").toString();
/* 109 */     cad = new StringBuilder().append(cad).append(this.ejercicio).append(" WHERE numero='").append(numero).append("'").toString();
/*     */     try {
/* 111 */       this.res = this.con.getRes(cad);
/* 112 */       if (this.res.next()) {
/* 113 */         return this.res.getInt(1);
/*     */       }
/* 115 */       this.res.close();
/*     */     } catch (SQLException exc) {
/* 117 */       exc.printStackTrace();
/*     */     }
/* 119 */     return -1;
/*     */   }
/*     */ 
/*     */   public String nuevoNumeroFactEmitida(String prefijo) {
/* 123 */     String nuevoNumero = "";
/* 124 */     int largoPrefijo = prefijo.length();
/* 125 */     int numeroDatos = -1;
/*     */     try {
/* 127 */       this.res = this.con.getRes(new StringBuilder().append("SELECT COUNT(numero) from emit").append(this.ejercicio).toString());
/* 128 */       if (this.res.next()) {
/* 129 */         numeroDatos = this.res.getInt(1);
/*     */       }
/* 131 */       if (numeroDatos == 0) {
/* 132 */         return new StringBuilder().append(prefijo).append("0001").toString();
/*     */       }
/* 134 */       if (numeroDatos != -1) {
/* 135 */         String[] numeros = new String[numeroDatos];
/* 136 */         int x = 0;
/* 137 */         this.res = this.con.getRes(new StringBuilder().append("SELECT numero from emit").append(this.ejercicio).toString());
/* 138 */         while (this.res.next()) {
/* 139 */           numeros[x] = this.res.getString(1);
/* 140 */           x++;
/*     */         }
/* 142 */         this.res.close();
/* 143 */         if (largoPrefijo > 0) {
/* 144 */           for (x = 0; x < numeroDatos; x++) {
/* 145 */             numeros[x] = numeros[x].substring(largoPrefijo);
/*     */           }
/*     */         }
/* 148 */         int max = 0;
/* 149 */         if ((!numeros[(numeroDatos - 1)].equals("")) && (esEntero(numeros[(numeroDatos - 1)]))) {
/* 150 */           max = Integer.parseInt(numeros[(numeroDatos - 1)]);
/*     */         }
/* 152 */         for (x = 0; x < numeroDatos - 1; x++) {
/* 153 */           if ((!numeros[x].equals("")) && (esEntero(numeros[x])) && 
/* 154 */             (Integer.parseInt(numeros[x]) > max)) {
/* 155 */             max = Integer.parseInt(numeros[x]);
/*     */           }
/*     */         }
/*     */ 
/* 159 */         nuevoNumero = new StringBuilder().append(prefijo).append("0000".substring(String.valueOf(max + 1).length())).append(String.valueOf(max + 1)).toString();
/*     */       }
/*     */     }
/*     */     catch (NumberFormatException ex)
/*     */     {
/* 164 */       ex.printStackTrace();
/*     */     } catch (SQLException e) {
/* 166 */       e.printStackTrace();
/*     */     }
/* 168 */     return nuevoNumero;
/*     */   }
/*     */ 
/*     */   private boolean esEntero(String dato) {
/*     */     try {
/* 173 */       Integer.parseInt(dato);
/*     */     } catch (NumberFormatException exc) {
/* 175 */       return false;
/*     */     }
/* 177 */     return true;
/*     */   }
/*     */ 
/*     */   public TipoFactura datos(boolean emitida, String numero) {
/* 181 */     if (emitida) {
/* 182 */       return datosEmitida(numero);
/*     */     }
/*     */ 
/* 185 */     return datosRecibida(numero);
/*     */   }
/*     */ 
/*     */   private TipoFactura datosEmitida(String numero)
/*     */   {
/* 190 */     TipoFactura factura = null;
/*     */     try {
/* 192 */       this.res = this.con.getRes(new StringBuilder().append("SELECT * FROM emit").append(this.ejercicio).append(" WHERE numero = '").append(numero).append("'").toString());
/*     */ 
/* 194 */       if (this.res.next()) {
/* 195 */         double total = 0.0D;
/* 196 */         double re = this.res.getDouble(8);
/* 197 */         total = this.res.getDouble(9);
/* 198 */         factura = new TipoFactura(numero, this.res.getString(2), this.res.getInt(3), this.res.getInt(4), this.res.getString(5), this.res.getDouble(6), this.res.getDouble(7), re, total);
/*     */       }
/*     */ 
/* 202 */       this.res.close();
/*     */     } catch (SQLException e) {
/* 204 */       e.printStackTrace();
/*     */     }
/* 206 */     return factura;
/*     */   }
/*     */ 
/*     */   private TipoFactura datosRecibida(String numero) {
/* 210 */     TipoFactura factura = null;
/*     */     try {
/* 212 */       this.res = this.con.getRes(new StringBuilder().append("SELECT * FROM reci").append(this.ejercicio).append(" WHERE numero = '").append(numero).append("'").toString());
/*     */ 
/* 214 */       if (this.res.next()) {
/* 215 */         double total = 0.0D;
/* 216 */         total = this.res.getDouble(8);
/* 217 */         Integer origen = Integer.valueOf(0);
/* 218 */         origen = Integer.valueOf(this.res.getInt(9));
/* 219 */         factura = new TipoFactura(numero, this.res.getString(2), this.res.getInt(3), this.res.getInt(4), this.res.getString(5), this.res.getDouble(6), this.res.getDouble(7), origen.intValue(), total);
/*     */       }
/*     */ 
/* 223 */       this.res.close();
/*     */     } catch (SQLException e) {
/* 225 */       e.printStackTrace();
/*     */     }
/* 227 */     return factura;
/*     */   }
/*     */ 
/*     */   public TipoFacturaExtended datos(String numero)
/*     */   {
/* 237 */     TipoFacturaExtended factura = null;
/*     */     try {
/* 239 */       this.res = this.con.getRes(new StringBuilder().append("SELECT * FROM reci").append(this.ejercicio).append(" WHERE numero = '").append(numero).append("'").toString());
/*     */ 
/* 241 */       if (this.res.next()) {
/* 242 */         factura = new TipoFacturaExtended(numero, this.res.getString(2), this.res.getInt(3), this.res.getInt(4), this.res.getString(5), this.res.getDouble(6), this.res.getDouble(7), this.res.getDouble(8), this.res.getInt(9));
/*     */       }
/*     */ 
/* 246 */       this.res.close();
/*     */     } catch (SQLException e) {
/* 248 */       e.printStackTrace();
/*     */     }
/* 250 */     return factura;
/*     */   }
/*     */ 
/*     */   public int[] vencimientosDeFactura(boolean emitida, String numero) {
/* 254 */     int[] vencimientos = null;
/* 255 */     String tabla = emitida ? "vencimientosc" : "vencimientos";
/*     */     try {
/* 257 */       this.res = this.con.getRes(new StringBuilder().append("SELECT COUNT(id) FROM ").append(tabla).append(" WHERE factura='").append(numero).append("'").toString());
/*     */ 
/* 259 */       if (this.res.next()) {
/* 260 */         vencimientos = new int[this.res.getInt(1)];
/*     */       }
/* 262 */       if (vencimientos.length > 0) {
/* 263 */         this.res = this.con.getRes(new StringBuilder().append("SELECT id FROM ").append(tabla).append(" WHERE factura='").append(numero).append("'").toString());
/*     */ 
/* 265 */         int i = 0;
/* 266 */         while (this.res.next()) {
/* 267 */           vencimientos[i] = this.res.getInt(1);
/* 268 */           i++;
/*     */         }
/*     */       }
/* 271 */       this.res.close();
/*     */     } catch (SQLException e) {
/* 273 */       e.printStackTrace();
/*     */     }
/* 275 */     return vencimientos;
/*     */   }
/*     */ 
/*     */   public void cerrarRs() {
/* 279 */     if (this.res != null) {
/*     */       try {
/* 281 */         this.res.close();
/*     */       } catch (SQLException sqlEx) {
/*     */       }
/* 284 */       this.res = null;
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.ManejoFacturas
 * JD-Core Version:    0.6.2
 */