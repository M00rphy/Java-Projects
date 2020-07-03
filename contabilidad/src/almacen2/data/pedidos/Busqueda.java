/*     */ package almacen2.data.pedidos;
/*     */ 
/*     */ import almacen2.data.FPObject;
/*     */ import almacen2.data.ManejadorListasInicio;
/*     */ import contaes.Inicio;
/*     */ import contaes.Puente;
/*     */ import java.sql.Date;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
import java.util.List;
/*     */ 
/*     */ public class Busqueda
/*     */ {
/*     */   private almacen2.data.MySQLConection con;
/*     */   private ResultSet res;
/*     */   private String cadena;
/*  19 */   private ArrayList<BusquedaObject> listado = new ArrayList();
/*  20 */   private HashSet<Integer> listaIdPedidos = new HashSet();
/*     */   FPObject[] proveedores;
/*     */   private String isCompras;
/*     */ 
/*     */   public Busqueda(almacen2.data.MySQLConection con, String cadena, boolean compras)
/*     */   {
/*  26 */     this.con = con;
/*  27 */     this.cadena = cadena;
/*  28 */     this.isCompras = (compras ? "compras" : "NOT compras");
/*  29 */     this.proveedores = new ManejadorListasInicio(con).getProveedores();
/*  30 */     iniciarBusqueda();
/*     */   }
/*     */ 
/*     */   private void iniciarBusqueda() {
/*  34 */     if ((this.cadena.length() >= 3) && (this.cadena.substring(1, 2).equals(":"))) {
/*  35 */       String condicion = this.cadena.substring(0, 2);
/*  36 */       if (condicion.equals("C:")) {
/*  37 */         this.cadena = this.cadena.substring(2);
/*  38 */         busquedaCodigo();
/*     */       }
/*  40 */       else if (condicion.equals("F:")) {
/*  41 */         this.cadena = this.cadena.substring(2);
/*  42 */         busquedaFecha();
/*     */       }
/*  44 */       else if (condicion.equals("P:")) {
/*  45 */         this.cadena = this.cadena.substring(2);
/*  46 */         busquedaProveedor();
/*     */       }
/*  48 */       else if (condicion.equals("R:")) {
/*  49 */         this.cadena = this.cadena.substring(2);
/*  50 */         busquedaReferencia();
/*     */       }
/*  52 */       else if (condicion.equals("D:")) {
/*  53 */         this.cadena = this.cadena.substring(2);
/*  54 */         busquedaDescripcion();
/*     */       }
/*  56 */       else if (condicion.equals("U:")) {
/*  57 */         this.cadena = this.cadena.substring(2);
/*  58 */         busquedaUnidades();
/*     */       }
/*  60 */       else if (condicion.equals("I:")) {
/*  61 */         this.cadena = this.cadena.substring(2);
/*  62 */         int puntoComa = this.cadena.indexOf(";");
/*  63 */         int dif = 10;
/*  64 */         if (puntoComa != -1) {
/*  65 */           String dife = this.cadena.substring(puntoComa + 1);
/*  66 */           this.cadena = this.cadena.substring(0, puntoComa);
/*  67 */           if (esEntero(dife))
/*  68 */             dif = Integer.parseInt(dife);
/*     */         }
/*  70 */         busquedaImporte(dif);
/*     */       }
/*  72 */       else if (condicion.equals("A:")) {
/*  73 */         this.cadena = this.cadena.substring(2);
/*  74 */         busquedaAlmacen();
/*     */       }
/*     */ 
/*     */     }
/*  78 */     else if (this.cadena.equals("")) {
/*  79 */       String cadenaDeBusqueda = "SELECT id FROM pedidos WHERE " + this.isCompras;
/*  80 */       buscar(cadenaDeBusqueda);
/*     */     }
/*     */     else {
/*  83 */       busquedaGLobal();
/*     */     }
/*  85 */     ConstruirLista();
/*     */   }
/*     */ 
/*     */   private void buscar(String cadenaDeBusqueda) {
/*     */     try {
/*  90 */       this.res = this.con.getRes(cadenaDeBusqueda);
/*  91 */       while (this.res.next())
/*  92 */         this.listaIdPedidos.add(new Integer(this.res.getInt(1)));
/*     */     } catch (SQLException e) {
/*  94 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void busquedaCodigo() {
/*  99 */     String cadenaDeBusqueda = "SELECT id FROM pedidos WHERE " + this.isCompras + " AND numero LIKE '%" + this.cadena + "%'";
/* 100 */     buscar(cadenaDeBusqueda);
/*     */   }
/*     */ 
/*     */   private void busquedaFecha() {
/* 104 */     if (esFecha(this.cadena)) {
/* 105 */       String cadenaDeBusqueda = "SELECT id FROM pedidos WHERE " + this.isCompras + " AND fecha ='" + this.cadena + "'";
/* 106 */       buscar(cadenaDeBusqueda);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void busquedaProveedor() {
/*     */     try {
/* 112 */       ArrayList listaProv = new ArrayList();
/* 113 */       if (this.isCompras.equals("compras")) {
/* 114 */         this.res = this.con.getRes("SELECT id FROM proveedores WHERE nombre LIKE '%" + this.cadena + "%'");
/* 115 */         while (this.res.next()) {
/* 116 */           listaProv.add(new Integer(this.res.getInt(1)));
/*     */         }
/*     */       }
/*     */ 
/* 120 */       this.res = Inicio.getCEmpresa().getRes("SELECT codigo FROM scta" + Inicio.p.getEjercicio() + " WHERE nombre LIKE '%" + this.cadena + "%'");
/*     */ 
/* 122 */       while (this.res.next()) {
/* 123 */         listaProv.add(new Integer(this.res.getInt(1)));
/*     */       }
/*     */    Iterator i$;
/* 126 */       for (i$ = listaProv.iterator(); i$.hasNext(); ) { int idProv = ((Integer)i$.next()).intValue();
/* 127 */         String cadenaDeBusqueda = "SELECT id FROM pedidos WHERE " + this.isCompras + " AND proveedor = " + idProv;
/* 128 */         buscar(cadenaDeBusqueda);
/*     */       }
/*     */     }
/*     */     catch (SQLException e)
/*     */     {
/*     */       Iterator i$;
/* 131 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void busquedaAlmacen() {
/*     */     try {
/* 137 */       ArrayList listaAlmacenes = new ArrayList();
/* 138 */       this.res = this.con.getRes("SELECT id FROM almacenes WHERE nombre LIKE '%" + this.cadena + "%'");
/* 139 */       while (this.res.next())
/* 140 */         listaAlmacenes.add(new Integer(this.res.getInt(1)));   Iterator i$;
/* 141 */       for (i$ = listaAlmacenes.iterator(); i$.hasNext(); ) { int idAlmacen = ((Integer)i$.next()).intValue();
/* 142 */         String cadenaDeBusqueda = "SELECT referencia FROM pio WHERE almacen = " + idAlmacen;
/* 143 */         buscar(cadenaDeBusqueda);
/*     */       }
/*     */     }
/*     */     catch (SQLException e)
/*     */     {
/*     */       Iterator i$;
/* 146 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void busquedaReferencia() {
/* 151 */     busquedaReferencia(this.cadena);
/*     */   }
/*     */ 
/*     */   private void busquedaReferencia(String referencia)
/*     */   {
/* 156 */     String cadenaDeBusqueda = "SELECT a.id_pedido FROM productos_pedidos a JOIN pedidos b ON a.id_pedido = b.id WHERE b." + this.isCompras + " AND a.referencia LIKE '%" + referencia + "%'";
/*     */ 
/* 158 */     buscar(cadenaDeBusqueda);
/*     */   }
/*     */ 
/*     */   private void busquedaDescripcion() {
/*     */     try {
/* 163 */       ArrayList listaRef = new ArrayList();
/* 164 */       this.res = this.con.getRes("SELECT referencia FROM Producto WHERE descripcion LIKE '%" + this.cadena + "%'");
/* 165 */       while (this.res.next()) {
/* 166 */         listaRef.add(this.res.getString(1));
/*     */       }
/* 168 */       for (String referencia :(List<String>) listaRef)
/* 169 */         busquedaReferencia(referencia);
/*     */     }
/*     */     catch (SQLException e) {
/* 172 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void busquedaUnidades() {
/* 177 */     if (esEntero(this.cadena))
/*     */     {
/* 180 */       String cadenaDeBusqueda = "SELECT a.id_pedido, COUNT(a.referencia) as suma, b.compras FROM productos_pedidos a JOIN pedidos b ON a.id_pedido = b.id GROUP BY a.id_pedido HAVING suma = " + this.cadena;
/*     */ 
/* 182 */       if (this.isCompras.equals("compras")) {
/* 183 */         cadenaDeBusqueda = cadenaDeBusqueda + " AND b.compras";
/*     */       }
/*     */       else {
/* 186 */         cadenaDeBusqueda = cadenaDeBusqueda + " AND NOT b.compras";
/*     */       }
/* 188 */       buscar(cadenaDeBusqueda);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void busquedaImporte(int dif)
/*     */   {
/* 202 */     if (esDouble(this.cadena)) {
/* 203 */       double valor1 = new Double(this.cadena).doubleValue() - dif;
/* 204 */       double valor2 = new Double(this.cadena).doubleValue() + dif;
/*     */ 
/* 208 */       String cadenaDeBusqueda = "SELECT a.id_pedido, SUM(b.coste) AS suma, c.compras FROM productos_pedidos a JOIN Producto b ON a.referencia = b.referencia JOIN pedidos c ON a.id_pedido=c.id GROUP BY id_pedido HAVING suma BETWEEN " + valor1 + " AND " + valor2;
/*     */ 
/* 211 */       if (this.isCompras.equals("compras")) {
/* 212 */         cadenaDeBusqueda = cadenaDeBusqueda + " AND c.compras";
/*     */       }
/*     */       else {
/* 215 */         cadenaDeBusqueda = cadenaDeBusqueda + " AND NOT c.compras";
/*     */       }
/* 217 */       buscar(cadenaDeBusqueda);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void busquedaImporte()
/*     */   {
/* 233 */     busquedaImporte(10);
/*     */   }
/*     */ 
/*     */   private void busquedaGLobal() {
/* 237 */     busquedaCodigo();
/* 238 */     busquedaFecha();
/* 239 */     busquedaProveedor();
/* 240 */     busquedaReferencia();
/* 241 */     busquedaDescripcion();
/* 242 */     busquedaUnidades();
/* 243 */     busquedaImporte();
/*     */   }
/*     */ 
/*     */   private void ConstruirLista() {
/* 247 */     for (Iterator i$ = this.listaIdPedidos.iterator(); i$.hasNext(); ) { int id = ((Integer)i$.next()).intValue();
/* 248 */       String codigo = ""; String fecha = ""; String proveedor = "";
/* 249 */       int unidades = 0;
/* 250 */       double coste = 0.0D;
/*     */       try {
/* 252 */         this.res = this.con.getRes("SELECT * FROM pedidos WHERE id = " + id);
/* 253 */         if (this.res.next()) {
/* 254 */           codigo = this.res.getString(2);
/* 255 */           fecha = this.res.getString(4);
/* 256 */           int pr = this.res.getInt(3);
/* 257 */           if (this.isCompras.equals("compras")) {
/* 258 */             for (int ind = 0; ind < this.proveedores.length; ind++) {
/* 259 */               if (pr == this.proveedores[ind].getId()) {
/* 260 */                 proveedor = this.proveedores[ind].getNombre();
/* 261 */                 break;
/*     */               }
/*     */             }
/*     */           }
/*     */           else {
/* 266 */             proveedor = Integer.toString(pr);
/*     */           }
/*     */         }
/* 269 */         this.res = this.con.getRes("SELECT COUNT(referencia) FROM productos_pedidos WHERE id_pedido = " + id);
/* 270 */         if (this.res.next())
/* 271 */           unidades = this.res.getInt(1);
/* 272 */         this.res = this.con.getRes("SELECT SUM(p.coste) FROM Producto p JOIN productos_pedidos a ON p.referencia = a.referencia WHERE a.id_pedido = " + id);
/*     */ 
/* 275 */         if (this.res.next())
/* 276 */           coste = this.res.getDouble(1);
/* 277 */         BusquedaObject objeto = new BusquedaObject(Integer.valueOf(id), codigo, fecha, proveedor, unidades, coste);
/* 278 */         this.listado.add(objeto);
/*     */       } catch (SQLException e) {
/* 280 */         e.printStackTrace();
/*     */       } }
/*     */   }
/*     */ 
/*     */   public ArrayList<BusquedaObject> getListado()
/*     */   {
/* 286 */     return this.listado;
/*     */   }
/*     */ 
/*     */   private boolean esFecha(String valor) {
/*     */     try {
/* 291 */       Date.valueOf(valor);
/*     */     }
/*     */     catch (NumberFormatException exc) {
/* 294 */       return false;
/*     */     }
/*     */     catch (StringIndexOutOfBoundsException exc) {
/* 297 */       return false;
/*     */     }
/*     */     catch (IllegalArgumentException exc) {
/* 300 */       return false;
/*     */     }
/* 302 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean esEntero(String valor) {
/*     */     try {
/* 307 */       Integer.parseInt(valor);
/*     */     }
/*     */     catch (NumberFormatException exc) {
/* 310 */       return false;
/*     */     }
/* 312 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean esDouble(String valor) {
/*     */     try {
/* 317 */       new Double(valor).doubleValue();
/*     */     }
/*     */     catch (NumberFormatException exc) {
/* 320 */       return false;
/*     */     }
/* 322 */     return true;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.data.pedidos.Busqueda
 * JD-Core Version:    0.6.2
 */