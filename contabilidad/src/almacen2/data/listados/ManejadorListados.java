/*     */ package almacen2.data.listados;
/*     */ 
/*     */ import almacen2.data.FPObject;
/*     */ import almacen2.data.MySQLConection;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ 
/*     */ public class ManejadorListados
/*     */ {
/*  14 */   private MySQLConection con = null;
/*     */   private ResultSet res;
/*     */ 
/*     */   public ManejadorListados(MySQLConection con)
/*     */   {
/*  19 */     this.con = con;
/*     */   }
/*     */ 
/*     */   public ArrayList<existenciaObject> listadoExistencias(int origen, int fp, int almacen)
/*     */   {
/*  29 */     String almacenSelect = "";
/*  30 */     if (almacen != -1) {
/*  31 */       almacenSelect = " WHERE b.almacen = " + almacen + " ";
/*     */     }
/*  33 */     ArrayList array = new ArrayList();
/*  34 */     String busqueda = "SELECT a.referencia,CONCAT(c.nombre,' ',a.descripcion),SUM(b.io),SUM(b.io)*a.coste FROM Producto a JOIN PIO b ON a.referencia = b.referencia JOIN proveedores c ON a.proveedor = c.id" + almacenSelect + " GROUP BY referencia";
/*     */ 
/*  40 */     if (almacen != -1) {
/*  41 */       almacenSelect = " AND b.almacen = " + almacen + " ";
/*     */     }
/*  43 */     if (origen == 0) {
/*  44 */       busqueda = "SELECT a.referencia,CONCAT(c.nombre,' ',a.descripcion),SUM(b.io),SUM(b.io)*a.coste FROM Producto a JOIN PIO b ON a.referencia = b.referencia JOIN proveedores c ON a.proveedor = c.id WHERE a.familia = " + fp + almacenSelect + " GROUP BY referencia";
/*     */     }
/*  51 */     else if (origen == 1) {
/*  52 */       busqueda = "SELECT a.referencia,CONCAT(c.nombre,' ',a.descripcion),SUM(b.io),SUM(b.io)*a.coste FROM Producto a JOIN PIO b ON a.referencia = b.referencia JOIN proveedores c ON a.proveedor = c.id WHERE a.proveedor = " + fp + almacenSelect + " GROUP BY referencia";
/*     */     }
/*     */ 
/*     */     try
/*     */     {
/*  61 */       int sumaUnidades = 0;
/*  62 */       double sumaCostes = 0.0D;
/*  63 */       this.res = this.con.getRes(busqueda);
/*  64 */       while (this.res.next()) {
/*  65 */         array.add(new existenciaObject(this.res.getString(1), this.res.getString(2), this.res.getInt(3), this.res.getDouble(4)));
/*  66 */         sumaUnidades += this.res.getInt(3);
/*  67 */         sumaCostes += this.res.getDouble(4);
/*     */       }
/*  69 */       array.add(new existenciaObject("", "Total", sumaUnidades, sumaCostes));
/*     */     } catch (SQLException e) {
/*  71 */       e.printStackTrace();
/*     */     }
/*  73 */     return array;
/*     */   }
/*     */ 
/*     */   public ArrayList<existenciaObject> listadoExistencias(int origen, int fp, String fecha, int almacen) {
/*  77 */     String almacenSelect = "";
/*  78 */     if (almacen != -1) {
/*  79 */       almacenSelect = " AND b.almacen = " + almacen + " ";
/*     */     }
/*  81 */     ArrayList array = new ArrayList();
/*  82 */     String busqueda = "SELECT a.referencia,CONCAT(c.nombre,' ',a.descripcion),SUM(b.io),SUM(b.io)*a.coste FROM Producto a JOIN PIO b ON a.referencia = b.referencia JOIN proveedores c ON a.proveedor = c.id WHERE b.fecha <= '" + fecha + "'" + almacenSelect + " GROUP BY a.referencia";
/*     */ 
/*  89 */     if (origen == 0) {
/*  90 */       busqueda = "SELECT a.referencia,CONCAT(c.nombre,' ',a.descripcion),SUM(b.io),SUM(b.io)*a.coste FROM Producto a JOIN PIO b ON a.referencia = b.referencia JOIN proveedores c ON a.proveedor = c.id WHERE a.familia = " + fp + " AND b.fecha <= '" + fecha + "'" + almacenSelect + " GROUP BY a.referencia";
/*     */     }
/*  97 */     else if (origen == 1) {
/*  98 */       busqueda = "SELECT a.referencia,CONCAT(c.nombre,' ',a.descripcion),SUM(b.io),SUM(b.io)*a.coste FROM Producto a JOIN PIO b ON a.referencia = b.referencia JOIN proveedores c ON a.proveedor = c.id WHERE a.proveedor = " + fp + " AND b.fecha <= '" + fecha + "'" + almacenSelect + " GROUP BY a.referencia";
/*     */     }
/*     */ 
/*     */     try
/*     */     {
/* 107 */       int sumaUnidades = 0;
/* 108 */       double sumaCostes = 0.0D;
/* 109 */       this.res = this.con.getRes(busqueda);
/* 110 */       while (this.res.next()) {
/* 111 */         array.add(new existenciaObject(this.res.getString(1), this.res.getString(2), this.res.getInt(3), this.res.getDouble(4)));
/* 112 */         sumaUnidades += this.res.getInt(3);
/* 113 */         sumaCostes += this.res.getDouble(4);
/*     */       }
/* 115 */       array.add(new existenciaObject("", "Total", sumaUnidades, sumaCostes));
/*     */     } catch (SQLException e) {
/* 117 */       e.printStackTrace();
/*     */     }
/* 119 */     return array;
/*     */   }
/*     */ 
/*     */   public ArrayList<compraVentaObject> listadoCVDiario(int origen, int tipo, int fp, String fInicial, String fFinal, int almacen)
/*     */   {
/* 129 */     String almacenSelect = "";
/* 130 */     if (almacen != -1) {
/* 131 */       almacenSelect = " AND b.almacen = " + almacen + " ";
/*     */     }
/* 133 */     ArrayList array = new ArrayList();
/*     */ 
/* 135 */     String busqueda = "SELECT DATE_FORMAT(b.fecha,'%d-%m-%Y'),a.referencia,CONCAT(c.nombre,' ',a.descripcion),b.io,-(b.io)*b.importe,a.pvp FROM Producto a JOIN PIO b ON a.referencia = b.referencia JOIN proveedores c ON a.proveedor = c.id WHERE b.fecha BETWEEN '" + fInicial + "' AND '" + fFinal + "'" + almacenSelect + " ORDER BY b.fecha";
/*     */ 
/* 142 */     if (origen == 2) {
/* 143 */       if (tipo == 0) {
/* 144 */         busqueda = "SELECT DATE_FORMAT(b.fecha,'%d-%m-%Y'),a.referencia,CONCAT(c.nombre,' ',a.descripcion),b.io,b.importe,a.pvp FROM Producto a JOIN PIO b ON a.referencia = b.referencia JOIN proveedores c ON a.proveedor = c.id WHERE b.io = 1 AND b.fecha BETWEEN '" + fInicial + "' AND '" + fFinal + "'" + almacenSelect + " ORDER BY b.fecha";
/*     */       }
/* 152 */       else if (tipo == 1) {
/* 153 */         busqueda = "SELECT DATE_FORMAT(b.fecha,'%d-%m-%Y'),a.referencia,CONCAT(c.nombre,' ',a.descripcion),-b.io,b.importe,a.pvp FROM Producto a JOIN PIO b ON a.referencia = b.referencia JOIN proveedores c ON a.proveedor = c.id WHERE b.io = -1 AND b.fecha BETWEEN '" + fInicial + "' AND '" + fFinal + "'" + almacenSelect + " ORDER BY b.fecha";
/*     */       }
/*     */ 
/*     */     }
/* 162 */     else if (origen == 0)
/*     */     {
/* 164 */       busqueda = "SELECT DATE_FORMAT(b.fecha,'%d-%m-%Y'),a.referencia,CONCAT(c.nombre,' ',a.descripcion),b.io,-(b.io)*b.importe,a.pvp FROM Producto a JOIN PIO b ON a.referencia = b.referencia JOIN proveedores c ON a.proveedor = c.id WHERE a.familia = " + fp + " AND" + " b.fecha BETWEEN '" + fInicial + "' AND '" + fFinal + "'" + almacenSelect + " ORDER BY b.fecha";
/*     */ 
/* 172 */       if (tipo == 0) {
/* 173 */         busqueda = "SELECT DATE_FORMAT(b.fecha,'%d-%m-%Y'),a.referencia,CONCAT(c.nombre,' ',a.descripcion),b.io,b.importe,a.pvp FROM Producto a JOIN PIO b ON a.referencia = b.referencia JOIN proveedores c ON a.proveedor = c.id WHERE a.familia = " + fp + " AND" + " b.io = 1 AND" + " b.fecha BETWEEN '" + fInicial + "' AND '" + fFinal + "'" + almacenSelect + " ORDER BY b.fecha";
/*     */       }
/* 182 */       else if (tipo == 1) {
/* 183 */         busqueda = "SELECT DATE_FORMAT(b.fecha,'%d-%m-%Y'),a.referencia,CONCAT(c.nombre,' ',a.descripcion),-b.io,b.importe,a.pvp FROM Producto a JOIN PIO b ON a.referencia = b.referencia JOIN proveedores c ON a.proveedor = c.id WHERE a.familia = " + fp + " AND" + " b.io = -1 AND" + " b.fecha BETWEEN '" + fInicial + "' AND '" + fFinal + "'" + almacenSelect + " ORDER BY b.fecha";
/*     */       }
/*     */ 
/*     */     }
/* 193 */     else if (origen == 1)
/*     */     {
/* 195 */       busqueda = "SELECT DATE_FORMAT(b.fecha,'%d-%m-%Y'),a.referencia,a.descripcion,b.io,-(b.io)*b.importe,a.pvp FROM Producto a JOIN PIO b ON a.referencia = b.referencia WHERE a.proveedor = " + fp + " AND" + " b.fecha BETWEEN '" + fInicial + "' AND '" + fFinal + "'" + almacenSelect + " ORDER BY b.fecha";
/*     */ 
/* 202 */       if (tipo == 0) {
/* 203 */         busqueda = "SELECT DATE_FORMAT(b.fecha,'%d-%m-%Y'),a.referencia,a.descripcion,b.io,b.importe,a.pvp FROM Producto a JOIN PIO b ON a.referencia = b.referencia WHERE a.proveedor = " + fp + " AND" + " b.io = 1 AND" + " b.fecha BETWEEN '" + fInicial + "' AND '" + fFinal + "'" + almacenSelect + " ORDER BY b.fecha";
/*     */       }
/* 211 */       else if (tipo == 1) {
/* 212 */         busqueda = "SELECT DATE_FORMAT(b.fecha,'%d-%m-%Y'),a.referencia,a.descripcion,-b.io,b.importe,a.pvp FROM Producto a JOIN PIO b ON a.referencia = b.referencia WHERE a.proveedor = " + fp + " AND" + " b.io = -1 AND" + " b.fecha BETWEEN '" + fInicial + "' AND '" + fFinal + "'" + almacenSelect + " ORDER BY b.fecha";
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 222 */     int mes = Integer.parseInt(fInicial.substring(4, 6));
/* 223 */     int sumaUnidades = 0; int totalUnidades = 0;
/* 224 */     double sumaImportes = 0.0D; double totalImporte = 0.0D;
/* 225 */     double sumaPvp = 0.0D; double totalPvp = 0.0D;
/*     */     try {
/* 227 */       this.res = this.con.getRes(busqueda);
/* 228 */       String fecha = null;
/* 229 */       while (this.res.next()) {
/* 230 */         fecha = this.res.getString(1);
/* 231 */         int mesEnCurso = Integer.parseInt(fecha.substring(3, 5));
/* 232 */         if (mes != mesEnCurso) {
/* 233 */           mes = mesEnCurso;
/* 234 */           if ((sumaUnidades != 0) || (sumaImportes != 0.0D)) {
/* 235 */             array.add(new compraVentaObject("", "", "Total del mes", sumaUnidades, sumaImportes, sumaPvp));
/*     */           }
/* 237 */           sumaUnidades = 0;
/* 238 */           sumaImportes = 0.0D;
/* 239 */           sumaPvp = 0.0D;
/*     */         }
/*     */ 
/* 242 */         int unidades = this.res.getInt(4);
/* 243 */         double importe = this.res.getDouble(5);
/* 244 */         double pvp = this.res.getDouble(6);
/* 245 */         array.add(new compraVentaObject(fecha, this.res.getString(2), this.res.getString(3), unidades, importe, pvp));
/* 246 */         sumaUnidades += unidades;
/* 247 */         totalUnidades += unidades;
/* 248 */         sumaImportes += importe;
/* 249 */         totalImporte += importe;
/* 250 */         sumaPvp += pvp;
/* 251 */         totalPvp += pvp;
/*     */       }
/* 253 */       array.add(new compraVentaObject("", "", "Total del mes", sumaUnidades, sumaImportes, sumaPvp));
/* 254 */       array.add(new compraVentaObject("", "", "Total", totalUnidades, totalImporte, totalPvp));
/*     */     } catch (SQLException e) {
/* 256 */       e.printStackTrace();
/*     */     }
/*     */ 
/* 259 */     return array;
/*     */   }
/*     */ 
/*     */   public ArrayList<compraVentaObject> listadoCVMensual(int origen, int tipo, int fp, String fInicial, String fFinal, int almacen)
/*     */   {
/* 269 */     String almacenSelect = "";
/* 270 */     if (almacen != -1) {
/* 271 */       almacenSelect = " AND b.almacen = " + almacen + " ";
/*     */     }
/* 273 */     ArrayList array = new ArrayList();
/*     */ 
/* 275 */     String busqueda = "SELECT DATE_FORMAT(b.fecha,'%m-%Y') as mes, a.referencia,CONCAT(c.nombre,' ',a.descripcion),SUM(io),SUM(importe),a.pvp FROM Producto a JOIN PIO b ON a.referencia = b.referencia JOIN proveedores c ON a.proveedor = c.id WHERE b.fecha BETWEEN '" + fInicial + "' AND '" + fFinal + "'" + almacenSelect + " GROUP BY referencia,mes,io ORDER BY fecha,referencia";
/*     */ 
/* 283 */     if (origen == 2) {
/* 284 */       if (tipo == 0) {
/* 285 */         busqueda = "SELECT DATE_FORMAT(b.fecha,'%m-%Y') as mes, a.referencia,CONCAT(c.nombre,' ',a.descripcion),SUM(io),SUM(importe),a.pvp FROM Producto a JOIN PIO b ON a.referencia = b.referencia JOIN proveedores c ON a.proveedor = c.id WHERE io = 1 AND b.fecha BETWEEN '" + fInicial + "' AND '" + fFinal + "'" + almacenSelect + " GROUP BY referencia,mes ORDER BY fecha,referencia";
/*     */       }
/* 294 */       else if (tipo == 1) {
/* 295 */         busqueda = "SELECT DATE_FORMAT(b.fecha,'%m-%Y') as mes, a.referencia,CONCAT(c.nombre,' ',a.descripcion),-SUM(io),-SUM(importe),a.pvp FROM Producto a JOIN PIO b ON a.referencia = b.referencia JOIN proveedores c ON a.proveedor = c.id WHERE io = -1 AND b.fecha BETWEEN '" + fInicial + "' AND '" + fFinal + "'" + almacenSelect + " GROUP BY referencia,mes ORDER BY fecha,referencia";
/*     */       }
/*     */ 
/*     */     }
/* 305 */     else if (origen == 0) {
/* 306 */       busqueda = "SELECT DATE_FORMAT(b.fecha,'%m-%Y') as mes, a.referencia,CONCAT(c.nombre,' ',a.descripcion),SUM(io),SUM(importe),a.pvp FROM Producto a JOIN PIO b ON a.referencia = b.referencia JOIN proveedores c ON a.proveedor = c.id WHERE familia = " + fp + " AND" + " b.fecha BETWEEN '" + fInicial + "' AND '" + fFinal + "'" + almacenSelect + " GROUP BY referencia,mes,io ORDER BY fecha,referencia";
/*     */ 
/* 315 */       if (tipo == 0) {
/* 316 */         busqueda = "SELECT DATE_FORMAT(b.fecha,'%m-%Y') as mes, a.referencia,CONCAT(c.nombre,' ',a.descripcion),SUM(io),SUM(importe),a.pvp FROM Producto a JOIN PIO b ON a.referencia = b.referencia JOIN proveedores c ON a.proveedor = c.id WHERE familia = " + fp + " AND io = 1 AND" + " b.fecha BETWEEN '" + fInicial + "' AND '" + fFinal + "'" + almacenSelect + " GROUP BY referencia,mes ORDER BY fecha,referencia";
/*     */       }
/* 325 */       else if (tipo == 1) {
/* 326 */         busqueda = "SELECT DATE_FORMAT(b.fecha,'%m-%Y') as mes, a.referencia,CONCAT(c.nombre,' ',a.descripcion),-SUM(io),-SUM(importe),a.pvp FROM Producto a JOIN PIO b ON a.referencia = b.referencia JOIN proveedores c ON a.proveedor = c.id WHERE familia = " + fp + " AND io = -1 AND" + " b.fecha BETWEEN '" + fInicial + "' AND '" + fFinal + "'" + almacenSelect + " GROUP BY referencia,mes ORDER BY fecha,referencia";
/*     */       }
/*     */ 
/*     */     }
/* 336 */     else if (origen == 1) {
/* 337 */       busqueda = "SELECT DATE_FORMAT(b.fecha,'%m-%Y') as mes, a.referencia,CONCAT(c.nombre,' ',a.descripcion),SUM(io),SUM(importe),a.pvp FROM Producto a JOIN PIO b ON a.referencia = b.referencia JOIN proveedores c ON a.proveedor = c.id WHERE proveedor = " + fp + " AND" + " b.fecha BETWEEN '" + fInicial + "' AND '" + fFinal + "'" + almacenSelect + " GROUP BY referencia,mes,io ORDER BY fecha,referencia";
/*     */ 
/* 346 */       if (tipo == 0) {
/* 347 */         busqueda = "SELECT DATE_FORMAT(b.fecha,'%m-%Y') as mes, a.referencia,CONCAT(c.nombre,' ',a.descripcion),SUM(io),SUM(importe),a.pvp FROM Producto a JOIN PIO b ON a.referencia = b.referencia JOIN proveedores c ON a.proveedor = c.id WHERE proveedor = " + fp + " AND io = 1 AND" + " b.fecha BETWEEN '" + fInicial + "' AND '" + fFinal + "'" + almacenSelect + " GROUP BY referencia,mes ORDER BY fecha,referencia";
/*     */       }
/* 356 */       else if (tipo == 1) {
/* 357 */         busqueda = "SELECT DATE_FORMAT(b.fecha,'%m-%Y') as mes, a.referencia,CONCAT(c.nombre,' ',a.descripcion),-SUM(io),-SUM(importe),a.pvp FROM Producto a JOIN PIO b ON a.referencia = b.referencia JOIN proveedores c ON a.proveedor = c.id WHERE proveedor = " + fp + " AND io = -1 AND" + " b.fecha BETWEEN '" + fInicial + "' AND '" + fFinal + "'" + almacenSelect + " GROUP BY referencia,mes ORDER BY fecha,referencia";
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 368 */     int mes = Integer.parseInt(fInicial.substring(4, 6));
/* 369 */     int sumaUnidades = 0; int totalUnidades = 0;
/* 370 */     double sumaImportes = 0.0D; double totalImporte = 0.0D;
/*     */     try {
/* 372 */       this.res = this.con.getRes(busqueda);
/* 373 */       String fecha = null;
/* 374 */       while (this.res.next()) {
/* 375 */         fecha = this.res.getString(1);
/* 376 */         int mesEnCurso = Integer.parseInt(fecha.substring(0, 2));
/* 377 */         if (mes != mesEnCurso) {
/* 378 */           mes = mesEnCurso;
/* 379 */           if ((sumaUnidades != 0) || (sumaImportes != 0.0D)) {
/* 380 */             array.add(new compraVentaObject("", "", "Total del mes", sumaUnidades, sumaImportes, 0.0D));
/*     */           }
/* 382 */           sumaUnidades = 0;
/* 383 */           sumaImportes = 0.0D;
/*     */         }
/* 385 */         int unidades = this.res.getInt(4);
/* 386 */         double importe = this.res.getDouble(5);
/* 387 */         array.add(new compraVentaObject(fecha, this.res.getString(2), this.res.getString(3), this.res.getInt(4), this.res.getDouble(5), this.res.getDouble(6)));
/* 388 */         sumaUnidades += unidades;
/* 389 */         totalUnidades += unidades;
/* 390 */         sumaImportes += importe;
/* 391 */         totalImporte += importe;
/*     */       }
/* 393 */       array.add(new compraVentaObject("", "", "Total del mes", sumaUnidades, sumaImportes, 0.0D));
/* 394 */       array.add(new compraVentaObject("", "", "Total", totalUnidades, totalImporte, 0.0D));
/*     */     } catch (SQLException e) {
/* 396 */       e.printStackTrace();
/*     */     }
/* 398 */     return array;
/*     */   }
/*     */ 
/*     */   public ArrayList<AgrupadoObject> listadoAgrupado(int origen, String fInicial, String fFinal, int almacen) {
/* 402 */     String almacenSelect = "";
/* 403 */     if (almacen != -1) {
/* 404 */       almacenSelect = " AND p.almacen = " + almacen + " ";
/*     */     }
/* 406 */     ArrayList array = new ArrayList();
/* 407 */     ArrayList array2 = new ArrayList();
/* 408 */     String tabla = origen == 0 ? "familias" : "proveedores";
/*     */     try {
/* 410 */       this.res = this.con.getRes("SELECT id,nombre FROM " + tabla + " ORDER BY nombre");
/* 411 */       FPObject fp = null;
/* 412 */       while (this.res.next()) {
/* 413 */         fp = new FPObject();
/* 414 */         fp.setId(this.res.getInt(1));
/* 415 */         fp.setNombre(this.res.getString(2));
/* 416 */         array2.add(fp);
/*     */       }
/*     */ 
/* 419 */       String campo = origen == 0 ? "familia" : "proveedor";
/* 420 */       Double uniCT = Double.valueOf(0.0D);
/* 421 */       Double totCT = Double.valueOf(0.0D);
/* 422 */       Double uniVT = Double.valueOf(0.0D);
/* 423 */       Double totVT = Double.valueOf(0.0D);
/* 424 */       for (FPObject fpO :(List<FPObject>) array2) {
/* 425 */         Double uniC = Double.valueOf(0.0D);
/* 426 */         Double totC = Double.valueOf(0.0D);
/* 427 */         Double uniV = Double.valueOf(0.0D);
/* 428 */         Double totV = Double.valueOf(0.0D);
/* 429 */         String sql = "SELECT SUM(p.io),SUM(p.importe) FROM pio p LEFT JOIN Producto pp ON p.referencia=pp.referencia WHERE p.io > 0 AND pp." + campo + " = " + fpO.getId() + " AND p.fecha BETWEEN '" + fInicial + "' AND '" + fFinal + "'" + almacenSelect;
/*     */ 
/* 432 */         this.res = this.con.getRes(sql);
/* 433 */         if (this.res.next()) {
/* 434 */           uniC = Double.valueOf(this.res.getDouble(1));
/* 435 */           uniCT = Double.valueOf(uniCT.doubleValue() + uniC.doubleValue());
/* 436 */           totC = Double.valueOf(this.res.getDouble(2));
/* 437 */           totCT = Double.valueOf(totCT.doubleValue() + totC.doubleValue());
/*     */         }
/* 439 */         sql = "SELECT SUM(p.io),SUM(p.importe) FROM pio p LEFT JOIN Producto pp ON p.referencia=pp.referencia WHERE p.io < 0 AND pp." + campo + " = " + fpO.getId() + " AND p.fecha BETWEEN '" + fInicial + "' AND '" + fFinal + "'" + almacenSelect;
/*     */ 
/* 442 */         this.res = this.con.getRes(sql);
/* 443 */         if (this.res.next()) {
/* 444 */           uniV = Double.valueOf(-1.0D * this.res.getDouble(1));
/* 445 */           uniVT = Double.valueOf(uniVT.doubleValue() + uniV.doubleValue());
/* 446 */           totV = Double.valueOf(this.res.getDouble(2));
/* 447 */           totVT = Double.valueOf(totVT.doubleValue() + totV.doubleValue());
/*     */         }
/* 449 */         AgrupadoObject objeto = new AgrupadoObject(fpO.getNombre(), uniC, totC, uniV, totV);
/* 450 */         array.add(objeto);
/*     */       }
/* 452 */       array.add(new AgrupadoObject("Total", uniCT, totCT, uniVT, totVT));
/*     */     } catch (SQLException ex) {
/* 454 */       Logger.getLogger(ManejadorListados.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/* 456 */     return array;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.data.listados.ManejadorListados
 * JD-Core Version:    0.6.2
 */