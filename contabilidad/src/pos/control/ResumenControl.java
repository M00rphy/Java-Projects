/*     */ package pos.control;
/*     */ 
/*     */ import almacen2.data.MySQLConection;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import pos.model.ObjetoMultifuncional;
/*     */ import pos.model.ResumenAgrupado;
/*     */ import pos.model.ResumenVentaTicket;
/*     */ import pos.model.ResumenVentaTicketMio;
/*     */ 
/*     */ public class ResumenControl
/*     */ {
/*     */   private MySQLConection con;
/*     */   private ResultSet res;
/*     */ 
/*     */   public ResumenControl(MySQLConection con)
/*     */   {
/*  32 */     this.con = con;
/*     */   }
/*     */ 
/*     */   public ArrayList<ResumenVentaTicket> listaVentas(java.util.Date fecha) {
/*  36 */     ArrayList lista = new ArrayList();
/*  37 */     String cadenaSQL = "SELECT a.id, a.numero, b.descripcion, b.unidades, b.importe, (b.unidades*b.importe), a.mediopago, a.plazos, b.idproducto, a.almacen, a.cliente, a.cerrado, c.nombre, d.nota FROM tickets a JOIN ventaspos b ON a.id = b.idticket JOIN mediospago c ON a.mediopago = c.id LEFT JOIN notaspos d ON b.id = d.idventa WHERE a.fecha = ? ORDER BY a.numero";
/*     */     try
/*     */     {
/*  46 */       PreparedStatement ps = this.con.getCon().prepareStatement(cadenaSQL);
/*  47 */       ps.setDate(1, new java.sql.Date(fecha.getTime()));
/*  48 */       this.res = ps.executeQuery();
/*  49 */       while (this.res.next()) {
/*  50 */         int idTicket = this.res.getInt(1);
/*  51 */         int numero = this.res.getInt(2);
/*  52 */         String descripcion = this.res.getString(3);
/*  53 */         int unidades = this.res.getInt(4);
/*  54 */         double importe = this.res.getDouble(5);
/*  55 */         double total = this.res.getDouble(6);
/*  56 */         int idMedioPago = this.res.getInt(7);
/*  57 */         int plazos = this.res.getInt(8);
/*  58 */         int idProducto = this.res.getInt(9);
/*  59 */         int almacen = this.res.getInt(10);
/*  60 */         int cliente = this.res.getInt(11);
/*  61 */         int cerrado = this.res.getInt(12);
/*  62 */         String medioPago = this.res.getString(13);
/*  63 */         String nota = this.res.getString(14);
/*  64 */         if (nota == null) {
/*  65 */           nota = "";
/*     */         }
/*  67 */         ResumenVentaTicket objeto = new ResumenVentaTicket(idTicket, numero, descripcion, unidades, importe, total, medioPago, plazos, idProducto, almacen, cliente, idMedioPago, cerrado, nota);
/*  68 */         lista.add(objeto);
/*     */       }
/*     */     } catch (SQLException ex) {
/*  71 */       Logger.getLogger(ResumenControl.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/*  73 */     return lista;
/*     */   }
/*     */ 
/*     */   public ArrayList<ResumenVentaTicket> busquedaVentas(java.util.Date fecha, java.util.Date fechaFin, Integer ticket, String referencia, ObjetoMultifuncional cliente, Double importe, int comp) {
/*  77 */     ArrayList lista = new ArrayList();
/*  78 */     boolean isWhere = true;
/*  79 */     String cadenaSQL = "SELECT a.id, a.numero, b.descripcion, b.unidades, b.importe, (b.unidades*b.importe) AS t, a.mediopago, a.plazos, b.idproducto, a.almacen, a.cliente, a.cerrado, c.nombre, d.nota FROM tickets a JOIN ventaspos b ON a.id = b.idticket JOIN mediospago c ON a.mediopago = c.id LEFT JOIN notaspos d ON b.id = d.idventa LEFT JOIN Producto e ON b.idproducto = e.id";
/*     */ 
/*  87 */     if (fecha != null) {
/*  88 */       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/*  89 */       if (fechaFin != null) {
/*  90 */         cadenaSQL = cadenaSQL + " WHERE a.fecha BETWEEN '" + sdf.format(fecha) + "' AND '" + sdf.format(fechaFin) + "'";
/*     */       }
/*     */       else {
/*  93 */         cadenaSQL = cadenaSQL + " WHERE a.fecha = '" + sdf.format(fecha) + "'";
/*     */       }
/*  95 */       isWhere = false;
/*     */     }
/*  97 */     if (ticket != null) {
/*  98 */       if (isWhere) {
/*  99 */         cadenaSQL = cadenaSQL + " WHERE a.numero = " + ticket;
/* 100 */         isWhere = false;
/*     */       }
/*     */       else {
/* 103 */         cadenaSQL = cadenaSQL + " AND a.numero = " + ticket;
/*     */       }
/*     */     }
/*     */ 
/* 107 */     if (referencia != null) {
/* 108 */       if (isWhere) {
/* 109 */         cadenaSQL = cadenaSQL + " WHERE e.referencia = '" + referencia + "'";
/* 110 */         isWhere = false;
/*     */       }
/*     */       else {
/* 113 */         cadenaSQL = cadenaSQL + " AND e.referencia = '" + referencia + "'";
/*     */       }
/*     */     }
/*     */ 
/* 117 */     if (cliente != null) {
/* 118 */       if (isWhere) {
/* 119 */         cadenaSQL = cadenaSQL + " WHERE cliente = " + cliente.getId();
/* 120 */         isWhere = false;
/*     */       }
/*     */       else {
/* 123 */         cadenaSQL = cadenaSQL + " AND cliente = " + cliente.getId();
/*     */       }
/*     */     }
/*     */ 
/* 127 */     if (importe != null) {
/* 128 */       String compara = " = ";
/* 129 */       if (comp == 0) {
/* 130 */         compara = " < ";
/*     */       }
/* 132 */       else if (comp == 2) {
/* 133 */         compara = " > ";
/*     */       }
/* 135 */       cadenaSQL = cadenaSQL + " HAVING t " + compara + importe;
/*     */     }
/*     */ 
/* 138 */     cadenaSQL = cadenaSQL + " ORDER BY a.numero";
/*     */     try
/*     */     {
/* 141 */       PreparedStatement ps = this.con.getCon().prepareStatement(cadenaSQL);
/* 142 */       this.res = ps.executeQuery();
/* 143 */       while (this.res.next()) {
/* 144 */         int idTicket = this.res.getInt(1);
/* 145 */         int numero = this.res.getInt(2);
/* 146 */         String descripcion = this.res.getString(3);
/* 147 */         int unidades = this.res.getInt(4);
/* 148 */         double importeBD = this.res.getDouble(5);
/* 149 */         double total = this.res.getDouble(6);
/* 150 */         int idMedioPago = this.res.getInt(7);
/* 151 */         int plazos = this.res.getInt(8);
/* 152 */         int idProducto = this.res.getInt(9);
/* 153 */         int almacen = this.res.getInt(10);
/* 154 */         int clienteBD = this.res.getInt(11);
/* 155 */         int cerrado = this.res.getInt(12);
/* 156 */         String medioPago = this.res.getString(13);
/* 157 */         String nota = this.res.getString(14);
/* 158 */         if (nota == null) {
/* 159 */           nota = "";
/*     */         }
/* 161 */         ResumenVentaTicket objeto = new ResumenVentaTicket(idTicket, numero, descripcion, unidades, importeBD, total, medioPago, plazos, idProducto, almacen, clienteBD, idMedioPago, cerrado, nota);
/* 162 */         lista.add(objeto);
/*     */       }
/*     */     } catch (SQLException ex) {
/* 165 */       Logger.getLogger(ResumenControl.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/* 167 */     return lista;
/*     */   }
/*     */ 
/*     */   public ArrayList<ResumenVentaTicket> getSeriePlazos(Integer idTicket) {
/* 171 */     ArrayList lista = new ArrayList();
/* 172 */     getSeriePlazos(lista, idTicket);
/* 173 */     return lista;
/*     */   }
/*     */ 
/*     */   private void getSeriePlazos(ArrayList<ResumenVentaTicket> lista, Integer idTicket) {
/* 177 */     String cadenaSQL = "SELECT a.id, a.numero, b.descripcion, b.unidades, b.importe, (b.unidades*b.importe), a.mediopago, a.plazos, b.idproducto, a.almacen, a.cliente, a.cerrado, c.nombre, d.nota, a.idplazoanterior FROM tickets a JOIN ventaspos b ON a.id = b.idticket JOIN mediospago c ON a.mediopago = c.id LEFT JOIN notaspos d ON b.id = d.idventa WHERE a.id = ? ORDER BY a.numero";
/*     */ 
/* 184 */     Integer idTicketAnterior = null;
/*     */     try
/*     */     {
/* 187 */       PreparedStatement ps = this.con.getCon().prepareStatement(cadenaSQL);
/* 188 */       ps.setInt(1, idTicket.intValue());
/* 189 */       this.res = ps.executeQuery();
/* 190 */       while (this.res.next())
/*     */       {
/* 192 */         int numero = this.res.getInt(2);
/* 193 */         String descripcion = this.res.getString(3);
/* 194 */         int unidades = this.res.getInt(4);
/* 195 */         double importe = this.res.getDouble(5);
/* 196 */         double total = this.res.getDouble(6);
/* 197 */         int idMedioPago = this.res.getInt(7);
/* 198 */         int plazos = this.res.getInt(8);
/* 199 */         int idProducto = this.res.getInt(9);
/* 200 */         int almacen = this.res.getInt(10);
/* 201 */         int cliente = this.res.getInt(11);
/* 202 */         int cerrado = this.res.getInt(12);
/* 203 */         String medioPago = this.res.getString(13);
/* 204 */         String nota = this.res.getString(14);
/* 205 */         if (nota == null) {
/* 206 */           nota = "";
/*     */         }
/* 208 */         ResumenVentaTicket objeto = new ResumenVentaTicket(idTicket.intValue(), numero, descripcion, unidades, importe, total, medioPago, plazos, idProducto, almacen, cliente, idMedioPago, cerrado, nota);
/* 209 */         lista.add(objeto);
/* 210 */         idTicketAnterior = Integer.valueOf(this.res.getInt(15));
/*     */       }
/*     */     } catch (SQLException ex) {
/* 213 */       Logger.getLogger(ResumenControl.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/* 215 */     if (idTicketAnterior != null)
/* 216 */       getSeriePlazos(lista, idTicketAnterior);
/*     */   }
/*     */ 
/*     */   public double plazosAnteriores(int idProducto, Integer idTicketA)
/*     */   {
/* 221 */     double importe = 0.0D;
/* 222 */     if (idTicketA != null) {
/* 223 */       Integer idTicketAA = null;
/* 224 */       String cadenaSQL = "SELECT (b.unidades*b.importe), a.idplazoanterior FROM tickets a JOIN ventaspos b ON a.id = b.idticket WHERE a.id = ? AND b.idproducto =  ?";
/*     */       try
/*     */       {
/* 228 */         PreparedStatement ps = this.con.getCon().prepareStatement(cadenaSQL);
/* 229 */         ps.setInt(1, idTicketA.intValue());
/* 230 */         ps.setInt(2, idProducto);
/* 231 */         this.res = ps.executeQuery();
/* 232 */         if (this.res.next()) {
/* 233 */           importe += this.res.getDouble(1);
/* 234 */           idTicketAA = Integer.valueOf(this.res.getInt(2));
/*     */         }
/*     */       } catch (SQLException ex) {
/* 237 */         Logger.getLogger(ResumenControl.class.getName()).log(Level.SEVERE, null, ex);
/*     */       }
/* 239 */       importe += plazosAnteriores(idProducto, idTicketAA);
/*     */     }
/* 241 */     return importe;
/*     */   }
/*     */ 
/*     */   public ArrayList<ResumenVentaTicketMio> listaVentasBetweenDates(java.util.Date[] fecha) {
/* 245 */     ArrayList lista = new ArrayList();
/* 246 */     String cadenaSQL = "SELECT a.id, a.numero, b.descripcion, b.unidades, b.importe, (b.unidades*b.importe), a.mediopago, a.plazos, b.idproducto, a.almacen, a.cliente, a.cerrado, c.nombre, a.fecha, d.nota FROM tickets a JOIN ventaspos b ON a.id = b.idticket JOIN mediospago c ON a.mediopago = c.id LEFT JOIN notaspos d ON b.id = d.idventa WHERE a.fecha BETWEEN ? AND ? ORDER BY a.fecha,a.numero";
/*     */     try
/*     */     {
/* 255 */       PreparedStatement ps = this.con.getCon().prepareStatement(cadenaSQL);
/* 256 */       ps.setDate(1, new java.sql.Date(fecha[0].getTime()));
/* 257 */       ps.setDate(2, new java.sql.Date(fecha[1].getTime()));
/* 258 */       this.res = ps.executeQuery();
/* 259 */       while (this.res.next()) {
/* 260 */         int idTicket = this.res.getInt(1);
/* 261 */         int numero = this.res.getInt(2);
/* 262 */         String descripcion = this.res.getString(3);
/* 263 */         int unidades = this.res.getInt(4);
/* 264 */         double importe = this.res.getDouble(5);
/* 265 */         double total = this.res.getDouble(6);
/* 266 */         int idMedioPago = this.res.getInt(7);
/* 267 */         int plazos = this.res.getInt(8);
/* 268 */         int idProducto = this.res.getInt(9);
/* 269 */         int almacen = getFamiliaForMe(idProducto);
/* 270 */         int cliente = this.res.getInt(11);
/* 271 */         int cerrado = this.res.getInt(12);
/* 272 */         String medioPago = this.res.getString(13);
/* 273 */         java.util.Date fechaTicket = this.res.getDate(14);
/* 274 */         String nota = this.res.getString(15);
/* 275 */         if (nota == null) {
/* 276 */           nota = "";
/*     */         }
/* 278 */         ResumenVentaTicketMio objeto = new ResumenVentaTicketMio(fechaTicket, idTicket, numero, descripcion, unidades, importe, total, medioPago, plazos, idProducto, almacen, cliente, idMedioPago, cerrado, nota);
/* 279 */         lista.add(objeto);
/*     */       }
/*     */     } catch (SQLException ex) {
/* 282 */       Logger.getLogger(ResumenControl.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/* 284 */     return lista;
/*     */   }
/*     */ 
/*     */   private int getFamiliaForMe(int referencia) {
/* 288 */     int familia = 1;
/* 289 */     if (referencia < 1000000) {
/* 290 */       familia = 2;
/*     */     }
/* 292 */     else if ((referencia >= 1000000) && (referencia < 20000000))
/* 293 */       familia = 1;
/* 294 */     else if (((referencia >= 22000000) && (referencia < 23000000)) || ((referencia >= 79000000) && (referencia < 94000000)))
/*     */     {
/* 296 */       familia = 2;
/*     */     }
/* 298 */     else familia = 3;
/*     */ 
/* 300 */     if ((referencia == 587) || (referencia == 593)) {
/* 301 */       familia = 1;
/*     */     }
/* 303 */     else if (referencia == 589) {
/* 304 */       familia = 3;
/*     */     }
/* 306 */     else if ((referencia == 590) || (referencia == 592)) {
/* 307 */       familia = 4;
/*     */     }
/* 309 */     return familia;
/*     */   }
/*     */ 
/*     */   public ArrayList<ResumenAgrupado> resumenMediosPago(java.util.Date fecha) {
/* 313 */     ArrayList lista = new ArrayList();
/* 314 */     String cadenaSQL = "SELECT c.id,c.nombre, SUM(b.unidades*b.importe) FROM ventaspos b JOIN tickets a ON b.idticket = a.id JOIN mediospago c ON a.mediopago = c.id WHERE a.fecha = ? GROUP BY c.id";
/*     */     try
/*     */     {
/* 320 */       PreparedStatement ps = this.con.getCon().prepareStatement(cadenaSQL);
/* 321 */       ps.setDate(1, new java.sql.Date(fecha.getTime()));
/* 322 */       this.res = ps.executeQuery();
/* 323 */       while (this.res.next()) {
/* 324 */         int id = this.res.getInt(1);
/* 325 */         String nombre = this.res.getString(2);
/* 326 */         double importe = this.res.getDouble(3);
/* 327 */         ResumenAgrupado objeto = new ResumenAgrupado(id, nombre, importe);
/* 328 */         lista.add(objeto);
/*     */       }
/*     */     } catch (SQLException ex) {
/* 331 */       Logger.getLogger(ResumenControl.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/* 333 */     return lista;
/*     */   }
/*     */ 
/*     */   public ArrayList<ResumenAgrupado> resumenFamilias(java.util.Date fecha) {
/* 337 */     ArrayList lista = new ArrayList();
/* 338 */     String cadenaSQL = "SELECT c.familia, d.nombre, SUM(b.unidades*b.importe) FROM ventaspos b JOIN tickets a ON b.idticket = a.id JOIN Producto c ON b.idproducto = c.id JOIN familias d ON c.familia = d.id WHERE a.fecha = ? GROUP BY c.familia";
/*     */     try
/*     */     {
/* 345 */       PreparedStatement ps = this.con.getCon().prepareStatement(cadenaSQL);
/* 346 */       ps.setDate(1, new java.sql.Date(fecha.getTime()));
/* 347 */       this.res = ps.executeQuery();
/* 348 */       while (this.res.next()) {
/* 349 */         int id = this.res.getInt(1);
/* 350 */         String nombre = this.res.getString(2);
/* 351 */         double importe = this.res.getDouble(3);
/* 352 */         ResumenAgrupado objeto = new ResumenAgrupado(id, nombre, importe);
/* 353 */         lista.add(objeto);
/*     */       }
/*     */     } catch (SQLException ex) {
/* 356 */       Logger.getLogger(ResumenControl.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/* 358 */     return lista;
/*     */   }
/*     */ 
/*     */   public ArrayList<ResumenAgrupado> resumenFamiliasMio(java.util.Date fecha) {
/* 362 */     ArrayList listaFamilias = new ArrayList();
/* 363 */     ArrayList listaVentas = listaVentas(fecha);
/* 364 */     ResumenAgrupado resumen = null;
/* 365 */     for (ResumenVentaTicket venta :(List<ResumenVentaTicket>) listaVentas) {
/*     */       try {
/* 367 */         int f = getFamilia(venta.getIdProducto());
/* 368 */         this.res = this.con.getRes("SELECT familia FROM Producto WHERE id = " + venta.getIdProducto());
/* 369 */         if (this.res.next()) {
/* 370 */           f = this.res.getInt(1);
/*     */         }
/* 372 */         resumen = new ResumenAgrupado(f, "", venta.getTotal());
/* 373 */         if (resumen != null)
/* 374 */           addObject(listaFamilias, resumen);
/*     */       }
/*     */       catch (SQLException ex) {
/* 377 */         Logger.getLogger(ResumenControl.class.getName()).log(Level.SEVERE, null, ex);
/*     */       }
/*     */     }
/* 380 */     for (ResumenAgrupado resumenA :(List<ResumenAgrupado>) listaFamilias) {
/*     */       try {
/* 382 */         this.res = this.con.getRes("SELECT nombre FROM familias WHERE id = " + resumenA.getId());
/* 383 */         if (this.res.next())
/* 384 */           resumenA.setNombre(this.res.getString(1));
/*     */       }
/*     */       catch (SQLException ex) {
/* 387 */         Logger.getLogger(ResumenControl.class.getName()).log(Level.SEVERE, null, ex);
/*     */       }
/*     */     }
/*     */ 
/* 391 */     return listaFamilias;
/*     */   }
/*     */ 
/*     */   private void addObject(ArrayList<ResumenAgrupado> lista, ResumenAgrupado object) {
/* 395 */     boolean existe = false;
/* 396 */     for (int i = 0; i < lista.size(); i++) {
/* 397 */       if (((ResumenAgrupado)lista.get(i)).getId() == object.getId()) {
/* 398 */         ((ResumenAgrupado)lista.get(i)).setImporte(((ResumenAgrupado)lista.get(i)).getImporte() + object.getImporte());
/* 399 */         existe = true;
/* 400 */         break;
/*     */       }
/*     */     }
/* 403 */     if (!existe)
/* 404 */       lista.add(object);
/*     */   }
/*     */ 
/*     */   private int getFamilia(int referencia)
/*     */   {
/* 409 */     int familia = 1;
/* 410 */     if (referencia < 20000000)
/* 411 */       familia = 1;
/* 412 */     else if (((referencia >= 22000000) && (referencia < 23000000)) || ((referencia >= 79000000) && (referencia < 94000000)))
/*     */     {
/* 414 */       familia = 22;
/*     */     }
/* 416 */     else familia = 3;
/*     */ 
/* 418 */     return familia;
/*     */   }
/*     */ 
/*     */   public ArrayList<ResumenAgrupado> resumenClientes(java.util.Date fecha) {
/* 422 */     ArrayList lista = new ArrayList();
/* 423 */     String cadenaSQL = "SELECT a.cliente, SUM(b.unidades*b.importe) FROM ventaspos b JOIN tickets a ON b.idticket = a.id WHERE a.fecha = ? GROUP BY a.cliente";
/*     */     try
/*     */     {
/* 428 */       PreparedStatement ps = this.con.getCon().prepareStatement(cadenaSQL);
/* 429 */       ps.setDate(1, new java.sql.Date(fecha.getTime()));
/* 430 */       this.res = ps.executeQuery();
/* 431 */       while (this.res.next()) {
/* 432 */         int id = this.res.getInt(1);
/* 433 */         double importe = this.res.getDouble(2);
/* 434 */         ResumenAgrupado objeto = new ResumenAgrupado(id, "", importe);
/* 435 */         lista.add(objeto);
/*     */       }
/*     */     } catch (SQLException ex) {
/* 438 */       Logger.getLogger(ResumenControl.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/* 440 */     return lista;
/*     */   }
/*     */ 
/*     */   public int getCuentaCobroMedio(int id) {
/* 444 */     int cuentaCobro = 57000000;
/*     */     try {
/* 446 */       String cadenaSQL = "SELECT cuentacobro FROM mediospago WHERE id = " + id;
/* 447 */       this.res = this.con.getRes(cadenaSQL);
/* 448 */       if (this.res.next())
/* 449 */         cuentaCobro = this.res.getInt(1);
/*     */     }
/*     */     catch (SQLException exc)
/*     */     {
/* 453 */       exc.printStackTrace();
/*     */     }
/* 455 */     return cuentaCobro;
/*     */   }
/*     */ 
/*     */   public ResumenAgrupado getComisionMedio(int id, double importe) {
/* 459 */     ResumenAgrupado objeto = null;
/*     */     try
/*     */     {
/* 463 */       String cadenaSQL = "SELECT comision, cuentacomision FROM mediospago WHERE id = " + id;
/* 464 */       this.res = this.con.getRes(cadenaSQL);
/* 465 */       if (this.res.next()) {
/* 466 */         double c = this.res.getDouble(1);
/* 467 */         double comision = importe * (c / 100.0D);
/* 468 */         int cuenta = this.res.getInt(2);
/* 469 */         objeto = new ResumenAgrupado(cuenta, "", comision);
/*     */       }
/*     */     }
/*     */     catch (SQLException exc) {
/* 473 */       exc.printStackTrace();
/*     */     }
/* 475 */     return objeto;
/*     */   }
/*     */ 
/*     */   public void cerrarRs() {
/* 479 */     if (this.res != null) {
/*     */       try {
/* 481 */         this.res.close(); } catch (SQLException sqlEx) {
/*     */       }
/* 483 */       this.res = null;
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.control.ResumenControl
 * JD-Core Version:    0.6.2
 */