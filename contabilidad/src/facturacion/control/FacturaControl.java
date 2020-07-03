/*     */ package facturacion.control;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.Puente;
/*     */ import contaes.manejoDatos.ManejoFormasPago;
/*     */ import contaes.manejoDatos.ManejoSubcuentas;
/*     */ import contaes.manejoDatos.TipoFormaPago;
/*     */ import contaes.manejoDatos.TipoSubcuenta;
/*     */ import contaes.manejoDatos.auxiliar.MySQLConection;
/*     */ import facturacion.control.interfaces.FacturaControlInterface;
/*     */ import facturacion.model.Factura;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class FacturaControl
/*     */   implements FacturaControlInterface
/*     */ {
/*     */   private MySQLConection con;
/*     */   private ResultSet res;
/*     */   private String tabla;
/*     */ 
/*     */   public FacturaControl(MySQLConection con, boolean ventas)
/*     */   {
/*  33 */     this.con = con;
/*  34 */     if (ventas)
/*  35 */       this.tabla = "facturas";
/*     */     else
/*  37 */       this.tabla = "facturascompras";
/*     */   }
/*     */ 
/*     */   public int isSubcuentaEnUso(int subcuenta)
/*     */   {
/*  48 */     int rstado = 0;
/*     */     try {
/*  50 */       this.res = this.con.getRes("SELECT * FROM " + this.tabla + " WHERE cliente = " + subcuenta);
/*  51 */       if (this.res.next())
/*  52 */         if (this.tabla.equals("facturas"))
/*  53 */           rstado = 3;
/*     */         else
/*  55 */           rstado = 4;
/*     */     }
/*     */     catch (SQLException ex)
/*     */     {
/*  59 */       ex.printStackTrace();
/*     */     }
/*  61 */     return rstado;
/*     */   }
/*     */ 
/*     */   public Integer crear(Factura factura) throws Exception
/*     */   {
/*  66 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
/*  67 */     Integer retorno = Integer.valueOf(-1);
/*     */     try {
/*  69 */       String cadenaSQL = "INSERT INTO " + this.tabla + " (numero,cliente,fecha,retencion,recargo,formapago,base,iva,contabilizada,isalmacenada) VALUES(?,?,?,?,?,?,?,?,?,?)";
/*  70 */       PreparedStatement ps = this.con.getCon().prepareStatement(cadenaSQL);
/*  71 */       ps.setString(1, factura.getNumero());
/*  72 */       ps.setInt(2, factura.getCliente().getCodigo());
/*  73 */       ps.setString(3, sdf.format(factura.getFecha()));
/*  74 */       ps.setDouble(4, factura.getRetencion().doubleValue());
/*  75 */       ps.setBoolean(5, factura.isRecargo());
/*  76 */       if (factura.getFormaPago() != null)
/*  77 */         ps.setInt(6, factura.getFormaPago().getIdFormaPago().intValue());
/*     */       else {
/*  79 */         ps.setInt(6, -1);
/*     */       }
/*  81 */       ps.setDouble(7, factura.getBase().doubleValue());
/*  82 */       ps.setDouble(8, factura.getIva().doubleValue());
/*  83 */       ps.setBoolean(9, factura.isContabilizada());
/*  84 */       ps.setBoolean(10, factura.isIsAlmacenada());
/*  85 */       ps.execute();
/*  86 */       cadenaSQL = "SELECT LAST_INSERT_ID() FROM " + this.tabla;
/*  87 */       ps = this.con.getCon().prepareStatement(cadenaSQL);
/*  88 */       this.res = ps.executeQuery();
/*  89 */       if (this.res.next())
/*  90 */         retorno = Integer.valueOf(this.res.getInt(1));
/*     */     }
/*     */     catch (SQLException exc) {
/*  93 */       exc.printStackTrace();
/*     */     }
/*  95 */     return retorno;
/*     */   }
/*     */ 
/*     */   public boolean modificar(Factura factura) throws Exception
/*     */   {
/* 100 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
/* 101 */     boolean retorno = false;
/*     */     try {
/* 103 */       String cadenaSQL = "UPDATE " + this.tabla + " SET numero = ?, cliente = ?, fecha = ?, retencion = ?," + "recargo = ?, formapago = ?, base = ?, iva = ?, contabilizada = ?, isalmacenada = ?" + " WHERE id = ?";
/*     */ 
/* 106 */       PreparedStatement ps = this.con.getCon().prepareStatement(cadenaSQL);
/* 107 */       ps.setString(1, factura.getNumero());
/* 108 */       ps.setInt(2, factura.getCliente().getCodigo());
/* 109 */       ps.setString(3, sdf.format(factura.getFecha()));
/* 110 */       ps.setDouble(4, factura.getRetencion().doubleValue());
/* 111 */       ps.setBoolean(5, factura.isRecargo());
/* 112 */       if (factura.getFormaPago() != null)
/* 113 */         ps.setInt(6, factura.getFormaPago().getIdFormaPago().intValue());
/*     */       else {
/* 115 */         ps.setInt(6, -1);
/*     */       }
/* 117 */       ps.setDouble(7, factura.getBase().doubleValue());
/* 118 */       ps.setDouble(8, factura.getIva().doubleValue());
/* 119 */       ps.setBoolean(9, factura.isContabilizada());
/* 120 */       ps.setBoolean(10, factura.isIsAlmacenada());
/* 121 */       ps.setInt(11, factura.getId().intValue());
/* 122 */       int result = ps.executeUpdate();
/* 123 */       if (result > 0)
/* 124 */         retorno = true;
/*     */     }
/*     */     catch (SQLException exc) {
/* 127 */       exc.printStackTrace();
/*     */     }
/* 129 */     return retorno;
/*     */   }
/*     */ 
/*     */   public boolean borrar(Factura factura) throws Exception
/*     */   {
/* 134 */     boolean retorno = false;
/*     */     try {
/* 136 */       String cadenaSQL = "DELETE FROM " + this.tabla + " WHERE id = ?";
/* 137 */       PreparedStatement ps = this.con.getCon().prepareStatement(cadenaSQL);
/* 138 */       ps.setInt(1, factura.getId().intValue());
/* 139 */       int result = ps.executeUpdate();
/* 140 */       if (result > 0)
/* 141 */         retorno = true;
/*     */     }
/*     */     catch (SQLException exc) {
/* 144 */       exc.printStackTrace();
/*     */     }
/* 146 */     return retorno;
/*     */   }
/*     */ 
/*     */   public Factura factura(Integer idFactura) throws Exception
/*     */   {
/* 151 */     Factura factura = null;
/*     */     try {
/* 153 */       String cadenaSQL = "SELECT * FROM " + this.tabla + " WHERE id = " + idFactura;
/* 154 */       this.res = this.con.getRes(cadenaSQL);
/* 155 */       if (this.res.next()) {
/* 156 */         String numero = this.res.getString(2);
/* 157 */         int cl = this.res.getInt(3);
/* 158 */         Date fecha = this.res.getDate(4);
/* 159 */         Double retencion = Double.valueOf(this.res.getDouble(5));
/* 160 */         boolean recargo = this.res.getBoolean(6);
/* 161 */         int fp = this.res.getInt(7);
/* 162 */         Double base = Double.valueOf(this.res.getDouble(8));
/* 163 */         Double iva = Double.valueOf(this.res.getDouble(9));
/* 164 */         boolean contabilizada = this.res.getBoolean(10);
/* 165 */         boolean isAlmacenada = this.res.getBoolean(11);
/* 166 */         ManejoSubcuentas mS = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 167 */         TipoSubcuenta cliente = mS.datos(cl);
/* 168 */         ManejoFormasPago mFP = new ManejoFormasPago(Inicio.getCGeneral());
/* 169 */         TipoFormaPago formapago = mFP.getFormaPago(fp);
/* 170 */         factura = new Factura(idFactura, numero, cliente, fecha, retencion, recargo, formapago, base, iva, contabilizada, isAlmacenada);
/*     */       }
/*     */     }
/*     */     catch (SQLException exc) {
/* 174 */       exc.printStackTrace();
/*     */     }
/* 176 */     return factura;
/*     */   }
/*     */ 
/*     */   public int existeFactura(Factura factura) throws Exception {
/* 180 */     int id = -1;
/* 181 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/*     */     try {
/* 183 */       String cadenaSQL = "SELECT id FROM " + this.tabla + " WHERE numero = ? AND fecha = ?";
/* 184 */       PreparedStatement ps = this.con.getCon().prepareStatement(cadenaSQL);
/* 185 */       ps.setString(1, factura.getNumero());
/* 186 */       ps.setString(2, sdf.format(factura.getFecha()));
/* 187 */       this.res = ps.executeQuery();
/* 188 */       if (this.res.next())
/* 189 */         id = this.res.getInt(1);
/*     */     }
/*     */     catch (SQLException exc) {
/* 192 */       exc.printStackTrace();
/*     */     }
/* 194 */     return id;
/*     */   }
/*     */ 
/*     */   public boolean isAlmacenada(Integer idFactura) throws Exception {
/* 198 */     boolean isAlmacenada = false;
/* 199 */     if (idFactura != null) {
/*     */       try {
/* 201 */         String cadenaSQL = "SELECT isalmacenada FROM " + this.tabla + " WHERE id = " + idFactura;
/* 202 */         this.res = this.con.getRes(cadenaSQL);
/* 203 */         if (this.res.next())
/* 204 */           isAlmacenada = this.res.getBoolean(1);
/*     */       }
/*     */       catch (SQLException exc)
/*     */       {
/* 208 */         exc.printStackTrace();
/*     */       }
/*     */     }
/* 211 */     return isAlmacenada;
/*     */   }
/*     */ 
/*     */   public Factura factura(String numero) throws Exception
/*     */   {
/* 216 */     Factura factura = null;
/*     */     try {
/* 218 */       String cadenaSQL = "SELECT * FROM " + this.tabla + " WHERE numero = '" + numero + "'";
/* 219 */       this.res = this.con.getRes(cadenaSQL);
/* 220 */       if (this.res.next()) {
/* 221 */         Integer idFactura = Integer.valueOf(this.res.getInt(1));
/* 222 */         int cl = this.res.getInt(3);
/* 223 */         Date fecha = this.res.getDate(4);
/* 224 */         Double retencion = Double.valueOf(this.res.getDouble(5));
/* 225 */         boolean recargo = this.res.getBoolean(6);
/* 226 */         int fp = this.res.getInt(7);
/* 227 */         Double base = Double.valueOf(this.res.getDouble(8));
/* 228 */         Double iva = Double.valueOf(this.res.getDouble(9));
/* 229 */         boolean contabilizada = this.res.getBoolean(10);
/* 230 */         boolean isAlmacenada = this.res.getBoolean(11);
/* 231 */         ManejoSubcuentas mS = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 232 */         TipoSubcuenta cliente = mS.datos(cl);
/* 233 */         ManejoFormasPago mFP = new ManejoFormasPago(Inicio.getCGeneral());
/* 234 */         TipoFormaPago formapago = mFP.getFormaPago(fp);
/* 235 */         factura = new Factura(idFactura, numero, cliente, fecha, retencion, recargo, formapago, base, iva, contabilizada, isAlmacenada);
/*     */       }
/*     */     }
/*     */     catch (SQLException exc) {
/* 239 */       exc.printStackTrace();
/*     */     }
/* 241 */     return factura;
/*     */   }
/*     */ 
/*     */   public Factura facturaEjercicio(Integer idFactura) throws Exception
/*     */   {
/* 246 */     Factura factura = null;
/* 247 */     String fechaInicio = Inicio.p.getEjercicio() + "01" + "01";
/* 248 */     String fechaFin = Inicio.p.getEjercicio() + "12" + "31";
/*     */     try {
/* 250 */       String cadenaSQL = "SELECT * FROM " + this.tabla + " WHERE id = " + idFactura + " AND fecha BETWEEN '" + fechaInicio + "' AND '" + fechaFin + "'";
/* 251 */       this.res = this.con.getRes(cadenaSQL);
/* 252 */       if (this.res.next()) {
/* 253 */         String numero = this.res.getString(2);
/* 254 */         int cl = this.res.getInt(3);
/* 255 */         Date fecha = this.res.getDate(4);
/* 256 */         Double retencion = Double.valueOf(this.res.getDouble(5));
/* 257 */         boolean recargo = this.res.getBoolean(6);
/* 258 */         int fp = this.res.getInt(7);
/* 259 */         Double base = Double.valueOf(this.res.getDouble(8));
/* 260 */         Double iva = Double.valueOf(this.res.getDouble(9));
/* 261 */         boolean contabilizada = this.res.getBoolean(10);
/* 262 */         boolean isAlmacenada = this.res.getBoolean(11);
/* 263 */         ManejoSubcuentas mS = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 264 */         TipoSubcuenta cliente = mS.datos(cl);
/* 265 */         ManejoFormasPago mFP = new ManejoFormasPago(Inicio.getCGeneral());
/* 266 */         TipoFormaPago formapago = mFP.getFormaPago(fp);
/* 267 */         factura = new Factura(idFactura, numero, cliente, fecha, retencion, recargo, formapago, base, iva, contabilizada, isAlmacenada);
/*     */       }
/*     */     }
/*     */     catch (SQLException exc) {
/* 271 */       exc.printStackTrace();
/*     */     }
/* 273 */     return factura;
/*     */   }
/*     */ 
/*     */   public Factura facturaEjercicio(String numero) throws Exception
/*     */   {
/* 278 */     Factura factura = null;
/* 279 */     String fechaInicio = Inicio.p.getEjercicio() + "01" + "01";
/* 280 */     String fechaFin = Inicio.p.getEjercicio() + "12" + "31";
/*     */     try {
/* 282 */       String cadenaSQL = "SELECT * FROM " + this.tabla + " WHERE numero = '" + numero + "' AND fecha BETWEEN '" + fechaInicio + "' AND '" + fechaFin + "'";
/* 283 */       this.res = this.con.getRes(cadenaSQL);
/* 284 */       if (this.res.next()) {
/* 285 */         Integer idFactura = Integer.valueOf(this.res.getInt(1));
/* 286 */         int cl = this.res.getInt(3);
/* 287 */         Date fecha = this.res.getDate(4);
/* 288 */         Double retencion = Double.valueOf(this.res.getDouble(5));
/* 289 */         boolean recargo = this.res.getBoolean(6);
/* 290 */         int fp = this.res.getInt(7);
/* 291 */         Double base = Double.valueOf(this.res.getDouble(8));
/* 292 */         Double iva = Double.valueOf(this.res.getDouble(9));
/* 293 */         boolean contabilizada = this.res.getBoolean(10);
/* 294 */         boolean isAlmacenada = this.res.getBoolean(11);
/* 295 */         ManejoSubcuentas mS = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 296 */         TipoSubcuenta cliente = mS.datos(cl);
/* 297 */         ManejoFormasPago mFP = new ManejoFormasPago(Inicio.getCGeneral());
/* 298 */         TipoFormaPago formapago = mFP.getFormaPago(fp);
/* 299 */         factura = new Factura(idFactura, numero, cliente, fecha, retencion, recargo, formapago, base, iva, contabilizada, isAlmacenada);
/*     */       }
/*     */     }
/*     */     catch (SQLException exc) {
/* 303 */       exc.printStackTrace();
/*     */     }
/* 305 */     return factura;
/*     */   }
/*     */ 
/*     */   public ArrayList<Factura> facturas() throws Exception
/*     */   {
/* 310 */     ArrayList facturas = new ArrayList();
/*     */     try {
/* 312 */       String cadenaSQL = "SELECT * FROM " + this.tabla;
/* 313 */       Factura factura = null;
/* 314 */       this.res = this.con.getRes(cadenaSQL);
/* 315 */       while (this.res.next()) {
/* 316 */         Integer id = Integer.valueOf(this.res.getInt(1));
/* 317 */         String numero = this.res.getString(2);
/* 318 */         int cl = this.res.getInt(3);
/* 319 */         Date fecha = this.res.getDate(4);
/* 320 */         Double retencion = Double.valueOf(this.res.getDouble(5));
/* 321 */         boolean recargo = this.res.getBoolean(6);
/* 322 */         int fp = this.res.getInt(7);
/* 323 */         Double base = Double.valueOf(this.res.getDouble(8));
/* 324 */         Double iva = Double.valueOf(this.res.getDouble(9));
/* 325 */         boolean contabilizada = this.res.getBoolean(10);
/* 326 */         boolean isAlmacenada = this.res.getBoolean(11);
/* 327 */         ManejoSubcuentas mS = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 328 */         TipoSubcuenta cliente = mS.datos(cl);
/* 329 */         ManejoFormasPago mFP = new ManejoFormasPago(Inicio.getCGeneral());
/* 330 */         TipoFormaPago formapago = mFP.getFormaPago(fp);
/* 331 */         factura = new Factura(id, numero, cliente, fecha, retencion, recargo, formapago, base, iva, contabilizada, isAlmacenada);
/* 332 */         facturas.add(factura);
/*     */       }
/*     */     }
/*     */     catch (SQLException exc) {
/* 336 */       exc.printStackTrace();
/*     */     }
/* 338 */     return facturas;
/*     */   }
/*     */ 
/*     */   public ArrayList<Factura> facturasEjercicio() throws Exception
/*     */   {
/* 343 */     ArrayList facturas = new ArrayList();
/* 344 */     String fechaInicio = Inicio.p.getEjercicio() + "01" + "01";
/* 345 */     String fechaFin = Inicio.p.getEjercicio() + "12" + "31";
/*     */     try {
/* 347 */       String cadenaSQL = "SELECT * FROM " + this.tabla + " WHERE fecha BETWEEN '" + fechaInicio + "' AND '" + fechaFin + "'";
/* 348 */       Factura factura = null;
/* 349 */       this.res = this.con.getRes(cadenaSQL);
/* 350 */       while (this.res.next()) {
/* 351 */         Integer id = Integer.valueOf(this.res.getInt(1));
/* 352 */         String numero = this.res.getString(2);
/* 353 */         int cl = this.res.getInt(3);
/* 354 */         Date fecha = this.res.getDate(4);
/* 355 */         Double retencion = Double.valueOf(this.res.getDouble(5));
/* 356 */         boolean recargo = this.res.getBoolean(6);
/* 357 */         int fp = this.res.getInt(7);
/* 358 */         Double base = Double.valueOf(this.res.getDouble(8));
/* 359 */         Double iva = Double.valueOf(this.res.getDouble(9));
/* 360 */         boolean contabilizada = this.res.getBoolean(10);
/* 361 */         boolean isAlmacenada = this.res.getBoolean(11);
/* 362 */         ManejoSubcuentas mS = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 363 */         TipoSubcuenta cliente = mS.datos(cl);
/* 364 */         ManejoFormasPago mFP = new ManejoFormasPago(Inicio.getCGeneral());
/* 365 */         TipoFormaPago formapago = mFP.getFormaPago(fp);
/* 366 */         factura = new Factura(id, numero, cliente, fecha, retencion, recargo, formapago, base, iva, contabilizada, isAlmacenada);
/* 367 */         facturas.add(factura);
/*     */       }
/*     */     }
/*     */     catch (SQLException exc) {
/* 371 */       exc.printStackTrace();
/*     */     }
/* 373 */     return facturas;
/*     */   }
/*     */ 
/*     */   public ArrayList<Factura> facturasEntreFechas(Date fechaIni, Date fechaFin) throws Exception {
/* 377 */     ArrayList facturas = new ArrayList();
/* 378 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/*     */     try {
/* 380 */       String cadenaSQL = "SELECT * FROM " + this.tabla + " WHERE fecha BETWEEN '" + sdf.format(fechaIni) + "' AND '" + sdf.format(fechaFin) + "'";
/*     */ 
/* 382 */       Factura factura = null;
/* 383 */       this.res = this.con.getRes(cadenaSQL);
/* 384 */       while (this.res.next()) {
/* 385 */         Integer id = Integer.valueOf(this.res.getInt(1));
/* 386 */         String numero = this.res.getString(2);
/* 387 */         int cl = this.res.getInt(3);
/* 388 */         Date fecha = this.res.getDate(4);
/* 389 */         Double retencion = Double.valueOf(this.res.getDouble(5));
/* 390 */         boolean recargo = this.res.getBoolean(6);
/* 391 */         int fp = this.res.getInt(7);
/* 392 */         Double base = Double.valueOf(this.res.getDouble(8));
/* 393 */         Double iva = Double.valueOf(this.res.getDouble(9));
/* 394 */         boolean contabilizada = this.res.getBoolean(10);
/* 395 */         boolean isAlmacenada = this.res.getBoolean(11);
/* 396 */         ManejoSubcuentas mS = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 397 */         TipoSubcuenta cliente = mS.datos(cl);
/* 398 */         ManejoFormasPago mFP = new ManejoFormasPago(Inicio.getCGeneral());
/* 399 */         TipoFormaPago formapago = mFP.getFormaPago(fp);
/* 400 */         factura = new Factura(id, numero, cliente, fecha, retencion, recargo, formapago, base, iva, contabilizada, isAlmacenada);
/* 401 */         facturas.add(factura);
/*     */       }
/*     */     }
/*     */     catch (SQLException exc) {
/* 405 */       exc.printStackTrace();
/*     */     }
/* 407 */     return facturas;
/*     */   }
/*     */ 
/*     */   public ArrayList<Factura> facturasEntreNumeros(String inicio, String fin) throws Exception {
/* 411 */     ArrayList facturas = new ArrayList();
/*     */     try {
/* 413 */       String cadenaSQL = "SELECT * FROM " + this.tabla + " WHERE numero BETWEEN '" + inicio + "' AND '" + fin + "'";
/*     */ 
/* 415 */       Factura factura = null;
/* 416 */       this.res = this.con.getRes(cadenaSQL);
/* 417 */       while (this.res.next()) {
/* 418 */         Integer id = Integer.valueOf(this.res.getInt(1));
/* 419 */         String numero = this.res.getString(2);
/* 420 */         int cl = this.res.getInt(3);
/* 421 */         Date fecha = this.res.getDate(4);
/* 422 */         Double retencion = Double.valueOf(this.res.getDouble(5));
/* 423 */         boolean recargo = this.res.getBoolean(6);
/* 424 */         int fp = this.res.getInt(7);
/* 425 */         Double base = Double.valueOf(this.res.getDouble(8));
/* 426 */         Double iva = Double.valueOf(this.res.getDouble(9));
/* 427 */         boolean contabilizada = this.res.getBoolean(10);
/* 428 */         boolean isAlmacenada = this.res.getBoolean(11);
/* 429 */         ManejoSubcuentas mS = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 430 */         TipoSubcuenta cliente = mS.datos(cl);
/* 431 */         ManejoFormasPago mFP = new ManejoFormasPago(Inicio.getCGeneral());
/* 432 */         TipoFormaPago formapago = mFP.getFormaPago(fp);
/* 433 */         factura = new Factura(id, numero, cliente, fecha, retencion, recargo, formapago, base, iva, contabilizada, isAlmacenada);
/* 434 */         facturas.add(factura);
/*     */       }
/*     */     }
/*     */     catch (SQLException exc) {
/* 438 */       exc.printStackTrace();
/*     */     }
/* 440 */     return facturas;
/*     */   }
/*     */ 
/*     */   public boolean isRangoEnEjercicio(String inicio, String fin, String ejercicio) {
/* 444 */     boolean isEnEjercicio = true;
/*     */     try
/*     */     {
/* 447 */       String cadenaSQL = "SELECT MIN(fecha) FROM " + this.tabla + " WHERE numero BETWEEN '" + inicio + "' AND '" + fin + "'";
/*     */ 
/* 449 */       this.res = this.con.getRes(cadenaSQL);
/* 450 */       if (this.res.next()) {
/* 451 */         String fecha = this.res.getString(1);
/* 452 */         if ((fecha != null) && (!ejercicio.equals(fecha.substring(0, 4)))) {
/* 453 */           isEnEjercicio = false;
/*     */         }
/*     */       }
/*     */ 
/* 457 */       cadenaSQL = "SELECT MAX(fecha) FROM " + this.tabla + " WHERE numero BETWEEN '" + inicio + "' AND '" + fin + "'";
/*     */ 
/* 459 */       this.res = this.con.getRes(cadenaSQL);
/* 460 */       if (this.res.next()) {
/* 461 */         String fecha = this.res.getString(1);
/* 462 */         if ((fecha != null) && (!ejercicio.equals(fecha.substring(0, 4))))
/* 463 */           isEnEjercicio = false;
/*     */       }
/*     */     }
/*     */     catch (SQLException exc)
/*     */     {
/* 468 */       exc.printStackTrace();
/*     */     }
/*     */ 
/* 471 */     return isEnEjercicio;
/*     */   }
/*     */ 
/*     */   public String nuevoNumeroFactEmitida(String prefijo)
/*     */   {
/* 476 */     String fechaInicio = Inicio.p.getEjercicio() + "01" + "01";
/* 477 */     String fechaFin = Inicio.p.getEjercicio() + "12" + "31";
/* 478 */     String nuevoNumero = "";
/* 479 */     int largoPrefijo = prefijo.length();
/* 480 */     int numeroDatos = -1;
/*     */     try {
/* 482 */       this.res = this.con.getRes("SELECT COUNT(numero) from " + this.tabla + " WHERE fecha BETWEEN '" + fechaInicio + "' AND '" + fechaFin + "'");
/* 483 */       if (this.res.next()) {
/* 484 */         numeroDatos = this.res.getInt(1);
/*     */       }
/* 486 */       if (numeroDatos == 0) {
/* 487 */         return prefijo + "0001";
/*     */       }
/* 489 */       if (numeroDatos != -1) {
/* 490 */         String[] numeros = new String[numeroDatos];
/* 491 */         int x = 0;
/* 492 */         this.res = this.con.getRes("SELECT numero from " + this.tabla + " WHERE fecha BETWEEN '" + fechaInicio + "' AND '" + fechaFin + "'");
/* 493 */         while (this.res.next()) {
/* 494 */           numeros[x] = this.res.getString(1);
/* 495 */           x++;
/*     */         }
/* 497 */         this.res.close();
/* 498 */         if (largoPrefijo > 0) {
/* 499 */           for (x = 0; x < numeroDatos; x++) {
/* 500 */             numeros[x] = numeros[x].substring(largoPrefijo);
/*     */           }
/*     */         }
/* 503 */         int max = 0;
/* 504 */         if ((!numeros[(numeroDatos - 1)].equals("")) && (esEntero(numeros[(numeroDatos - 1)]))) {
/* 505 */           max = Integer.parseInt(numeros[(numeroDatos - 1)]);
/*     */         }
/* 507 */         for (x = 0; x < numeroDatos - 1; x++) {
/* 508 */           if ((!numeros[x].equals("")) && (esEntero(numeros[x])) && 
/* 509 */             (Integer.parseInt(numeros[x]) > max)) {
/* 510 */             max = Integer.parseInt(numeros[x]);
/*     */           }
/*     */         }
/*     */ 
/* 514 */         nuevoNumero = prefijo + "0000".substring(String.valueOf(max + 1).length()) + String.valueOf(max + 1);
/*     */       }
/*     */ 
/*     */     }
/*     */     catch (SQLException e)
/*     */     {
/* 520 */       e.printStackTrace();
/*     */     } catch (NumberFormatException ex) {
/* 522 */       ex.printStackTrace();
/*     */     }
/* 524 */     return nuevoNumero;
/*     */   }
/*     */ 
/*     */   private boolean esEntero(String dato) {
/*     */     try {
/* 529 */       Integer.parseInt(dato);
/*     */     } catch (NumberFormatException exc) {
/* 531 */       return false;
/*     */     }
/* 533 */     return true;
/*     */   }
/*     */ 
/*     */   public void cerrarRs()
/*     */   {
/* 538 */     if (this.res != null) {
/*     */       try {
/* 540 */         this.res.close();
/*     */       } catch (SQLException sqlEx) {
/*     */       }
/* 543 */       this.res = null;
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     facturacion.control.FacturaControl
 * JD-Core Version:    0.6.2
 */