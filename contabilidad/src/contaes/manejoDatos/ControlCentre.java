/*     */ package contaes.manejoDatos;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.Puente;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.GregorianCalendar;
/*     */ 
/*     */ public class ControlCentre
/*     */ {
/*     */   private double caja;
/*     */   private double bancos;
/*     */   private double pagosHoy;
/*     */   private double pagosSiete;
/*     */   private double pagosTreinta;
/*     */   private double cobrosHoy;
/*     */   private double cobrosSiete;
/*     */   private double cobrosTreinta;
/*     */   private double acreEntFinCP;
/*     */   private double acreEntFinLP;
/*     */   private double acreComerciales;
/*     */   private double acreProvCP;
/*     */   private double acreProvLP;
/*     */   private double acreAdminPub;
/*     */   private double acrePersonal;
/*     */   private double deudInvFinCP;
/*     */   private double deudInvFinLP;
/*     */   private double deudComerciales;
/*     */   private double deudAdminPub;
/*     */   private double deudPersonal;
/*     */   private double ingresos;
/*     */   private double gastos;
/*     */   private double inmovilizado;
/*     */   private double fondosPropios;
/*     */   private int exisNumero;
/*     */   private double exisImporte;
/*     */   ArrayList<TipoInformeProductos> masVendidos;
/*     */   ArrayList<TipoInformeProductos> menosVendidos;
/*     */ 
/*     */   public ControlCentre()
/*     */   {
/*  58 */     calcular();
/*     */   }
/*     */ 
/*     */   public void calcular() {
/*  62 */     this.caja = 0.0D;
/*  63 */     this.bancos = 0.0D;
/*  64 */     this.pagosHoy = 0.0D;
/*  65 */     this.pagosSiete = 0.0D;
/*  66 */     this.pagosTreinta = 0.0D;
/*  67 */     this.cobrosHoy = 0.0D;
/*  68 */     this.cobrosSiete = 0.0D;
/*  69 */     this.cobrosTreinta = 0.0D;
/*  70 */     this.acreEntFinCP = 0.0D;
/*  71 */     this.acreEntFinLP = 0.0D;
/*  72 */     this.acreComerciales = 0.0D;
/*  73 */     this.acreProvCP = 0.0D;
/*  74 */     this.acreProvLP = 0.0D;
/*  75 */     this.acreAdminPub = 0.0D;
/*  76 */     this.acrePersonal = 0.0D;
/*  77 */     this.deudInvFinCP = 0.0D;
/*  78 */     this.deudInvFinLP = 0.0D;
/*  79 */     this.deudComerciales = 0.0D;
/*  80 */     this.deudAdminPub = 0.0D;
/*  81 */     this.deudPersonal = 0.0D;
/*  82 */     this.ingresos = 0.0D;
/*  83 */     this.gastos = 0.0D;
/*  84 */     this.inmovilizado = 0.0D;
/*  85 */     this.fondosPropios = 0.0D;
/*  86 */     this.exisNumero = 0;
/*  87 */     this.exisImporte = 0.0D;
/*     */ 
/*  89 */     this.masVendidos = new ArrayList();
/*  90 */     this.menosVendidos = new ArrayList();
/*     */ 
/*  92 */     GregorianCalendar fecha = new GregorianCalendar();
/*  93 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/*     */     try
/*     */     {
/*  98 */       ResultSet res = Inicio.getCEmpresa().getRes("SELECT SUM(saldo) FROM scta" + Inicio.p.getEjercicio() + " WHERE codigo BETWEEN 57000000 AND 57199999");
/*     */ 
/* 100 */       if (res.next()) {
/* 101 */         this.caja = res.getDouble(1);
/*     */       }
/*     */ 
/* 104 */       res = Inicio.getCEmpresa().getRes("SELECT SUM(saldo) FROM scta" + Inicio.p.getEjercicio() + " WHERE codigo BETWEEN 57200000 AND 57699999");
/*     */ 
/* 106 */       if (res.next()) {
/* 107 */         this.bancos = res.getDouble(1);
/*     */       }
/*     */ 
/* 110 */       res = Inicio.getCEmpresa().getRes("SELECT SUM(saldo) FROM scta" + Inicio.p.getEjercicio() + " WHERE codigo BETWEEN 60000000 AND 79999999 AND saldo <= 0");
/*     */ 
/* 112 */       if (res.next()) {
/* 113 */         this.ingresos = (res.getDouble(1) * -1.0D);
/*     */       }
/*     */ 
/* 116 */       res = Inicio.getCEmpresa().getRes("SELECT SUM(saldo) FROM scta" + Inicio.p.getEjercicio() + " WHERE codigo BETWEEN 60000000 AND 79999999 AND saldo > 0");
/*     */ 
/* 118 */       if (res.next()) {
/* 119 */         this.gastos = res.getDouble(1);
/*     */       }
/*     */ 
/* 122 */       res = Inicio.getCEmpresa().getRes("SELECT SUM(importe) FROM vencimientos WHERE fecha = '" + sdf.format(fecha.getTime()) + "' AND NOT pagado");
/*     */ 
/* 124 */       if (res.next()) {
/* 125 */         this.pagosHoy = res.getDouble(1);
/*     */       }
/*     */ 
/* 128 */       res = Inicio.getCEmpresa().getRes("SELECT SUM(importe) FROM vencimientosc WHERE fecha = '" + sdf.format(fecha.getTime()) + "' AND NOT pagado");
/*     */ 
/* 130 */       if (res.next()) {
/* 131 */         this.cobrosHoy = res.getDouble(1);
/*     */       }
/*     */ 
/* 134 */       GregorianCalendar fechass = new GregorianCalendar();
/* 135 */       fechass.add(5, 7);
/* 136 */       res = Inicio.getCEmpresa().getRes("SELECT SUM(importe) FROM vencimientos WHERE fecha BETWEEN '" + sdf.format(fecha.getTime()) + "' AND '" + sdf.format(fechass.getTime()) + "'" + " AND NOT pagado");
/*     */ 
/* 139 */       if (res.next()) {
/* 140 */         this.pagosSiete = res.getDouble(1);
/*     */       }
/*     */ 
/* 143 */       res = Inicio.getCEmpresa().getRes("SELECT SUM(importe) FROM vencimientosc WHERE fecha BETWEEN '" + sdf.format(fecha.getTime()) + "' AND '" + sdf.format(fechass.getTime()) + "'" + " AND NOT pagado");
/*     */ 
/* 146 */       if (res.next()) {
/* 147 */         this.cobrosSiete = res.getDouble(1);
/*     */       }
/*     */ 
/* 150 */       fechass.add(5, 23);
/* 151 */       res = Inicio.getCEmpresa().getRes("SELECT SUM(importe) FROM vencimientos WHERE fecha BETWEEN '" + sdf.format(fecha.getTime()) + "' AND '" + sdf.format(fechass.getTime()) + "'" + " AND NOT pagado");
/*     */ 
/* 154 */       if (res.next()) {
/* 155 */         this.pagosTreinta = res.getDouble(1);
/*     */       }
/*     */ 
/* 158 */       res = Inicio.getCEmpresa().getRes("SELECT SUM(importe) FROM vencimientosc WHERE fecha BETWEEN '" + sdf.format(fecha.getTime()) + "' AND '" + sdf.format(fechass.getTime()) + "'" + " AND NOT pagado");
/*     */ 
/* 161 */       if (res.next()) {
/* 162 */         this.cobrosTreinta = res.getDouble(1);
/*     */       }
/*     */ 
/* 166 */       res = Inicio.getCEmpresa().getRes("SELECT SUM(saldo) FROM scta" + Inicio.p.getEjercicio() + " WHERE codigo BETWEEN 51000000 AND 52099999");
/*     */ 
/* 168 */       if (res.next()) {
/* 169 */         this.acreEntFinCP = (res.getDouble(1) * -1.0D);
/*     */       }
/*     */ 
/* 172 */       res = Inicio.getCEmpresa().getRes("SELECT SUM(saldo) FROM scta" + Inicio.p.getEjercicio() + " WHERE codigo BETWEEN 16000000 AND 17099999");
/*     */ 
/* 174 */       if (res.next()) {
/* 175 */         this.acreEntFinLP = (res.getDouble(1) * -1.0D);
/*     */       }
/*     */ 
/* 178 */       res = Inicio.getCEmpresa().getRes("SELECT SUM(saldo) FROM scta" + Inicio.p.getEjercicio() + " WHERE codigo BETWEEN 40000000 AND 41999999");
/*     */ 
/* 180 */       if (res.next()) {
/* 181 */         this.acreComerciales = (res.getDouble(1) * -1.0D);
/*     */       }
/*     */ 
/* 184 */       res = Inicio.getCEmpresa().getRes("SELECT SUM(saldo) FROM scta" + Inicio.p.getEjercicio() + " WHERE codigo BETWEEN 50000000 AND 52999999");
/*     */ 
/* 186 */       if (res.next()) {
/* 187 */         this.acreProvCP = (res.getDouble(1) * -1.0D);
/*     */       }
/* 189 */       this.acreProvCP -= this.acreEntFinCP;
/*     */ 
/* 191 */       res = Inicio.getCEmpresa().getRes("SELECT SUM(saldo) FROM scta" + Inicio.p.getEjercicio() + " WHERE codigo BETWEEN 16100000 AND 16399999");
/*     */ 
/* 193 */       if (res.next()) {
/* 194 */         this.acreProvLP = (res.getDouble(1) * -1.0D);
/*     */       }
/* 196 */       res = Inicio.getCEmpresa().getRes("SELECT SUM(saldo) FROM scta" + Inicio.p.getEjercicio() + " WHERE codigo BETWEEN 17100000 AND 17999999");
/*     */ 
/* 198 */       if (res.next()) {
/* 199 */         this.acreProvLP += res.getDouble(1) * -1.0D;
/*     */       }
/*     */ 
/* 202 */       res = Inicio.getCEmpresa().getRes("SELECT SUM(saldo) FROM scta" + Inicio.p.getEjercicio() + " WHERE codigo BETWEEN 47500000 AND 47999999");
/*     */ 
/* 204 */       if (res.next()) {
/* 205 */         this.acreAdminPub = (res.getDouble(1) * -1.0D);
/*     */       }
/* 207 */       res = Inicio.getCEmpresa().getRes("SELECT SUM(saldo) FROM scta" + Inicio.p.getEjercicio() + " WHERE codigo BETWEEN 47300000 AND 47399999");
/*     */ 
/* 209 */       if (res.next()) {
/* 210 */         this.acreAdminPub += res.getDouble(1) * -1.0D;
/*     */       }
/*     */ 
/* 213 */       res = Inicio.getCEmpresa().getRes("SELECT SUM(saldo) FROM scta" + Inicio.p.getEjercicio() + " WHERE codigo BETWEEN 46500000 AND 46599999");
/*     */ 
/* 215 */       if (res.next()) {
/* 216 */         this.acrePersonal = (res.getDouble(1) * -1.0D);
/*     */       }
/*     */ 
/* 221 */       res = Inicio.getCEmpresa().getRes("SELECT SUM(saldo) FROM scta" + Inicio.p.getEjercicio() + " WHERE codigo BETWEEN 53000000 AND 54999999");
/*     */ 
/* 223 */       if (res.next()) {
/* 224 */         this.deudInvFinCP = res.getDouble(1);
/*     */       }
/*     */ 
/* 227 */       res = Inicio.getCEmpresa().getRes("SELECT SUM(saldo) FROM scta" + Inicio.p.getEjercicio() + " WHERE codigo BETWEEN 24000000 AND 25999999");
/*     */ 
/* 229 */       if (res.next()) {
/* 230 */         this.deudInvFinLP = res.getDouble(1);
/*     */       }
/*     */ 
/* 233 */       res = Inicio.getCEmpresa().getRes("SELECT SUM(saldo) FROM scta" + Inicio.p.getEjercicio() + " WHERE codigo BETWEEN 43000000 AND 46999999");
/*     */ 
/* 235 */       if (res.next()) {
/* 236 */         this.deudComerciales = res.getDouble(1);
/*     */       }
/*     */ 
/* 239 */       res = Inicio.getCEmpresa().getRes("SELECT SUM(saldo) FROM scta" + Inicio.p.getEjercicio() + " WHERE codigo BETWEEN 47000000 AND 47499999");
/*     */ 
/* 241 */       if (res.next()) {
/* 242 */         this.deudAdminPub = res.getDouble(1);
/*     */       }
/* 244 */       res = Inicio.getCEmpresa().getRes("SELECT SUM(saldo) FROM scta" + Inicio.p.getEjercicio() + " WHERE codigo BETWEEN 47300000 AND 47399999");
/*     */ 
/* 246 */       if (res.next()) {
/* 247 */         this.deudAdminPub -= res.getDouble(1);
/*     */       }
/*     */ 
/* 250 */       res = Inicio.getCEmpresa().getRes("SELECT SUM(saldo) FROM scta" + Inicio.p.getEjercicio() + " WHERE codigo BETWEEN 46000000 AND 46099999");
/*     */ 
/* 252 */       if (res.next()) {
/* 253 */         this.deudPersonal = res.getDouble(1);
/*     */       }
/*     */ 
/* 257 */       res = Inicio.getCEmpresa().getRes("SELECT SUM(saldo) FROM scta" + Inicio.p.getEjercicio() + " WHERE codigo BETWEEN 20000000 AND 23999999");
/*     */ 
/* 259 */       if (res.next()) {
/* 260 */         this.inmovilizado = res.getDouble(1);
/*     */       }
/* 262 */       res = Inicio.getCEmpresa().getRes("SELECT SUM(saldo) FROM scta" + Inicio.p.getEjercicio() + " WHERE codigo BETWEEN 28000000 AND 28299999");
/*     */ 
/* 264 */       if (res.next()) {
/* 265 */         this.inmovilizado -= res.getDouble(1);
/*     */       }
/*     */ 
/* 268 */       res = Inicio.getCEmpresa().getRes("SELECT SUM(saldo) FROM scta" + Inicio.p.getEjercicio() + " WHERE codigo BETWEEN 10000000 AND 12999999");
/*     */ 
/* 270 */       if (res.next()) {
/* 271 */         this.fondosPropios = (res.getDouble(1) * -1.0D);
/*     */       }
/*     */ 
/* 275 */       res = Inicio.getcAlmacen().getRes("SELECT SUM(b.io),SUM(b.io*a.coste) FROM Producto a JOIN PIO b ON a.referencia = b.referencia");
/*     */ 
/* 277 */       if (res.next()) {
/* 278 */         this.exisNumero = res.getInt(1);
/* 279 */         this.exisImporte = res.getDouble(2);
/*     */       }
/*     */ 
/* 282 */       int mostrar = 0;
/* 283 */       int numP = 0;
/* 284 */       res = Inicio.getcAlmacen().getRes("SELECT COUNT(*) FROM Producto");
/* 285 */       if (res.next()) {
/* 286 */         numP = res.getInt(1);
/*     */       }
/* 288 */       if (numP < 21) {
/* 289 */         mostrar = numP / 2;
/*     */       }
/* 291 */       else if (numP < 31) {
/* 292 */         mostrar = numP / 3;
/*     */       }
/* 294 */       else if (numP < 41) {
/* 295 */         mostrar = numP / 4;
/*     */       }
/* 297 */       else if (numP < 51) {
/* 298 */         mostrar = numP / 5;
/*     */       }
/* 300 */       else if (numP < 61) {
/* 301 */         mostrar = numP / 6;
/*     */       }
/* 303 */       else if (numP < 71) {
/* 304 */         mostrar = numP / 7;
/*     */       }
/*     */       else {
/* 307 */         mostrar = (int)(numP * 0.1D);
/*     */       }
/* 309 */       if (mostrar > 20) {
/* 310 */         mostrar = 20;
/*     */       }
/* 312 */       int temp = 0;
/* 313 */       res = Inicio.getcAlmacen().getRes("SELECT a.referencia,CONCAT(c.nombre,' ',a.descripcion),-SUM(b.io) AS ventas,a.coste,a.pvp FROM Producto a JOIN PIO b ON a.referencia = b.referencia JOIN proveedores c ON a.proveedor = c.id WHERE b.io = -1 GROUP BY a.referencia ORDER BY ventas DESC");
/*     */ 
/* 317 */       while (res.next()) {
/* 318 */         String referencia = res.getString(1);
/* 319 */         String descripcion = res.getString(2);
/* 320 */         int unidades = res.getInt(3);
/* 321 */         double coste = res.getDouble(4);
/* 322 */         double pvp = res.getDouble(5);
/* 323 */         TipoInformeProductos producto = new TipoInformeProductos(referencia, descripcion, unidades, coste, pvp);
/* 324 */         this.masVendidos.add(producto);
/* 325 */         if (temp++ >= mostrar) {
/*     */           break;
/*     */         }
/*     */       }
/* 329 */       temp = 0;
/* 330 */       res = Inicio.getcAlmacen().getRes("SELECT a.referencia,CONCAT(c.nombre,' ',a.descripcion),-SUM(b.io) AS ventas,a.coste,a.pvp FROM Producto a JOIN PIO b ON a.referencia = b.referencia JOIN proveedores c ON a.proveedor = c.id WHERE b.io = -1 GROUP BY a.referencia ORDER BY ventas ASC");
/*     */ 
/* 334 */       while (res.next()) {
/* 335 */         String referencia = res.getString(1);
/* 336 */         String descripcion = res.getString(2);
/* 337 */         int unidades = res.getInt(3);
/* 338 */         double coste = res.getDouble(4);
/* 339 */         double pvp = res.getDouble(5);
/* 340 */         TipoInformeProductos producto = new TipoInformeProductos(referencia, descripcion, unidades, coste, pvp);
/* 341 */         this.menosVendidos.add(producto);
/* 342 */         if (temp++ >= mostrar)
/*     */           break;
/*     */       }
/*     */     }
/*     */     catch (SQLException e) {
/* 347 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public double getAcreAdminPub() {
/* 352 */     return this.acreAdminPub;
/*     */   }
/*     */ 
/*     */   public double getAcreComerciales() {
/* 356 */     return this.acreComerciales;
/*     */   }
/*     */ 
/*     */   public double getAcreEntFinCP() {
/* 360 */     return this.acreEntFinCP;
/*     */   }
/*     */ 
/*     */   public double getAcreEntFinLP() {
/* 364 */     return this.acreEntFinLP;
/*     */   }
/*     */ 
/*     */   public double getAcrePersonal() {
/* 368 */     return this.acrePersonal;
/*     */   }
/*     */ 
/*     */   public double getAcreProvCP() {
/* 372 */     return this.acreProvCP;
/*     */   }
/*     */ 
/*     */   public double getAcreProvLP() {
/* 376 */     return this.acreProvLP;
/*     */   }
/*     */ 
/*     */   public double getBancos() {
/* 380 */     return this.bancos;
/*     */   }
/*     */ 
/*     */   public double getCaja() {
/* 384 */     return this.caja;
/*     */   }
/*     */ 
/*     */   public double getCobrosHoy() {
/* 388 */     return this.cobrosHoy;
/*     */   }
/*     */ 
/*     */   public double getCobrosSiete() {
/* 392 */     return this.cobrosSiete;
/*     */   }
/*     */ 
/*     */   public double getCobrosTreinta() {
/* 396 */     return this.cobrosTreinta;
/*     */   }
/*     */ 
/*     */   public double getDeudAdminPub() {
/* 400 */     return this.deudAdminPub;
/*     */   }
/*     */ 
/*     */   public double getDeudComerciales() {
/* 404 */     return this.deudComerciales;
/*     */   }
/*     */ 
/*     */   public double getDeudInvFinCP() {
/* 408 */     return this.deudInvFinCP;
/*     */   }
/*     */ 
/*     */   public double getDeudInvFinLP() {
/* 412 */     return this.deudInvFinLP;
/*     */   }
/*     */ 
/*     */   public double getDeudPersonal() {
/* 416 */     return this.deudPersonal;
/*     */   }
/*     */ 
/*     */   public double getExisImporte() {
/* 420 */     return this.exisImporte;
/*     */   }
/*     */ 
/*     */   public int getExisNumero() {
/* 424 */     return this.exisNumero;
/*     */   }
/*     */ 
/*     */   public ArrayList<TipoInformeProductos> getMasVendidos() {
/* 428 */     return this.masVendidos;
/*     */   }
/*     */ 
/*     */   public ArrayList<TipoInformeProductos> getMenosVendidos() {
/* 432 */     return this.menosVendidos;
/*     */   }
/*     */ 
/*     */   public double getPagosHoy() {
/* 436 */     return this.pagosHoy;
/*     */   }
/*     */ 
/*     */   public double getPagosSiete() {
/* 440 */     return this.pagosSiete;
/*     */   }
/*     */ 
/*     */   public double getPagosTreinta() {
/* 444 */     return this.pagosTreinta;
/*     */   }
/*     */ 
/*     */   public double getGastos() {
/* 448 */     return this.gastos;
/*     */   }
/*     */ 
/*     */   public double getIngresos() {
/* 452 */     return this.ingresos;
/*     */   }
/*     */ 
/*     */   public double getFondosPropios() {
/* 456 */     return this.fondosPropios;
/*     */   }
/*     */ 
/*     */   public double getInmovilizado() {
/* 460 */     return this.inmovilizado;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.ControlCentre
 * JD-Core Version:    0.6.2
 */