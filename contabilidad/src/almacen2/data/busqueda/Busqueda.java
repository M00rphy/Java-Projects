/*     */ package almacen2.data.busqueda;
/*     */ 
/*     */ import almacen2.data.FPObject;
/*     */ import almacen2.data.ManejadorListasInicio;
/*     */ import almacen2.data.MySQLConection;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class Busqueda
/*     */ {
/*     */   private MySQLConection con;
/*     */   private ResultSet res;
/*     */   private String cadena;
/*  17 */   private ArrayList<Objeto> listado = new ArrayList();
/*  18 */   private HashSet<String> listaReferencias = new HashSet();
/*     */   FPObject[] proveedores;
/*     */   FPObject[] familias;
/*     */ 
/*     */   public Busqueda(MySQLConection con, String cadena)
/*     */   {
/*  24 */     this.con = con;
/*  25 */     this.cadena = cadena;
/*  26 */     this.proveedores = new ManejadorListasInicio(con).getProveedores();
/*  27 */     this.familias = new ManejadorListasInicio(con).getFamilias();
/*  28 */     iniciarBusqueda();
/*     */   }
/*     */ 
/*     */   private void iniciarBusqueda() {
/*  32 */     if ((this.cadena.length() >= 3) && (this.cadena.substring(1, 2).equals(":"))) {
/*  33 */       String condicion = this.cadena.substring(0, 2);
/*  34 */       if (condicion.equals("R:")) {
/*  35 */         this.cadena = this.cadena.substring(2);
/*  36 */         busquedaReferencia();
/*     */       }
/*  38 */       else if (condicion.equals("F:")) {
/*  39 */         this.cadena = this.cadena.substring(2);
/*  40 */         busquedaFamilia();
/*     */       }
/*  42 */       else if (condicion.equals("P:")) {
/*  43 */         this.cadena = this.cadena.substring(2);
/*  44 */         busquedaProveedor();
/*     */       }
/*  46 */       else if (condicion.equals("D:")) {
/*  47 */         this.cadena = this.cadena.substring(2);
/*  48 */         busquedaDescripcion();
/*     */       }
/*  50 */       else if (condicion.equals("S:")) {
/*  51 */         this.cadena = this.cadena.substring(2);
/*  52 */         busquedaStock();
/*     */       }
/*  54 */       else if (condicion.equals("C:")) {
/*  55 */         this.cadena = this.cadena.substring(2);
/*  56 */         int puntoComa = this.cadena.indexOf(";");
/*  57 */         int dif = 10;
/*  58 */         if (puntoComa != -1) {
/*  59 */           String dife = this.cadena.substring(puntoComa + 1);
/*  60 */           this.cadena = this.cadena.substring(0, puntoComa);
/*  61 */           if (esEntero(dife))
/*  62 */             dif = Integer.parseInt(dife);
/*     */         }
/*  64 */         busquedaCoste(dif);
/*     */       }
/*  66 */       else if (condicion.equals("I:")) {
/*  67 */         this.cadena = this.cadena.substring(2);
/*  68 */         int puntoComa = this.cadena.indexOf(";");
/*  69 */         int dif = 10;
/*  70 */         if (puntoComa != -1) {
/*  71 */           String dife = this.cadena.substring(puntoComa + 1);
/*  72 */           this.cadena = this.cadena.substring(0, puntoComa);
/*  73 */           if (esEntero(dife))
/*  74 */             dif = Integer.parseInt(dife);
/*     */         }
/*  76 */         busquedaPVP(dif);
/*     */       }
/*  78 */       else if (condicion.equals("A:")) {
/*  79 */         this.cadena = this.cadena.substring(2);
/*  80 */         busquedaAlmacen();
/*     */       }
/*     */ 
/*     */     }
/*  84 */     else if (this.cadena.equals("")) {
/*  85 */       String cadenaDeBusqueda = "SELECT referencia FROM Producto";
/*  86 */       buscar(cadenaDeBusqueda);
/*     */     }
/*     */     else {
/*  89 */       busquedaGLobal();
/*     */     }
/*  91 */     ConstruirLista();
/*     */   }
/*     */ 
/*     */   private void buscar(String cadenaDeBusqueda) {
/*     */     try {
/*  96 */       this.res = this.con.getRes(cadenaDeBusqueda);
/*  97 */       while (this.res.next())
/*  98 */         this.listaReferencias.add(this.res.getString(1));
/*     */     } catch (SQLException e) {
/* 100 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void busquedaReferencia() {
/* 105 */     busquedaReferencia(this.cadena);
/*     */   }
/*     */ 
/*     */   private void busquedaReferencia(String referencia) {
/* 109 */     String cadenaDeBusqueda = "SELECT referencia FROM Producto WHERE referencia LIKE '%" + referencia + "%'";
/* 110 */     buscar(cadenaDeBusqueda);
/*     */   }
/*     */ 
/*     */   private void busquedaFamilia() {
/*     */     try {
/* 115 */       ArrayList listaFam = new ArrayList();
/* 116 */       this.res = this.con.getRes("SELECT id FROM familias WHERE nombre LIKE '%" + this.cadena + "%'");
/* 117 */       while (this.res.next())
/* 118 */         listaFam.add(new Integer(this.res.getInt(1)));Iterator i$;
/* 119 */       for (i$ = listaFam.iterator(); i$.hasNext(); ) { int idFam = ((Integer)i$.next()).intValue();
/* 120 */         String cadenaDeBusqueda = "SELECT referencia FROM Producto WHERE familia = " + idFam;
/* 121 */         buscar(cadenaDeBusqueda);
/*     */       }
/*     */     }
/*     */     catch (SQLException e)
/*     */     {
/*     */       Iterator i$;
/* 124 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void busquedaProveedor() {
/*     */     try {
/* 130 */       ArrayList listaProv = new ArrayList();
/* 131 */       this.res = this.con.getRes("SELECT id FROM proveedores WHERE nombre LIKE '%" + this.cadena + "%'");
/* 132 */       while (this.res.next())
/* 133 */         listaProv.add(new Integer(this.res.getInt(1)));Iterator i$;
/* 134 */       for (i$ = listaProv.iterator(); i$.hasNext(); ) { int idProv = ((Integer)i$.next()).intValue();
/* 135 */         String cadenaDeBusqueda = "SELECT referencia FROM Producto WHERE proveedor = " + idProv;
/* 136 */         buscar(cadenaDeBusqueda);
/*     */       }
/*     */     }
/*     */     catch (SQLException e)
/*     */     {
/*     */       Iterator i$;
/* 139 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void busquedaAlmacen() {
/*     */     try {
/* 145 */       ArrayList listaAlmacenes = new ArrayList();
/* 146 */       this.res = this.con.getRes("SELECT id FROM almacenes WHERE nombre LIKE '%" + this.cadena + "%'");
/* 147 */       while (this.res.next())
/* 148 */         listaAlmacenes.add(new Integer(this.res.getInt(1)));Iterator i$;
/* 149 */       for (i$ = listaAlmacenes.iterator(); i$.hasNext(); ) { int idAlmacen = ((Integer)i$.next()).intValue();
/* 150 */         String cadenaDeBusqueda = "SELECT referencia FROM pio WHERE almacen = " + idAlmacen;
/* 151 */         buscar(cadenaDeBusqueda);
/*     */       }
/*     */     }
/*     */     catch (SQLException e)
/*     */     {
/*     */       Iterator i$;
/* 154 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void busquedaDescripcion() {
/* 159 */     String cadenaDeBusqueda = "SELECT referencia FROM Producto WHERE descripcion LIKE '%" + this.cadena + "%'";
/* 160 */     buscar(cadenaDeBusqueda);
/*     */   }
/*     */ 
/*     */   private void busquedaStock() {
/* 164 */     if (esEntero(this.cadena)) {
/* 165 */       String cadenaDeBusqueda = "SELECT referencia, SUM(io) AS suma FROM PIO GROUP BY referencia HAVING suma = " + this.cadena;
/*     */ 
/* 167 */       buscar(cadenaDeBusqueda);
/* 168 */       if (Integer.parseInt(this.cadena) == 0) {
/* 169 */         cadenaDeBusqueda = "SELECT a.referencia FROM Producto a LEFT JOIN PIO b ON a.referencia=b.referencia WHERE b.referencia IS NULL";
/*     */ 
/* 172 */         buscar(cadenaDeBusqueda);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void busquedaCoste(int dif)
/*     */   {
/* 187 */     if (esDouble(this.cadena)) {
/* 188 */       double valor1 = new Double(this.cadena).doubleValue() - dif;
/* 189 */       double valor2 = new Double(this.cadena).doubleValue() + dif;
/* 190 */       String cadenaDeBusqueda = "SELECT referencia FROM Producto WHERE coste BETWEEN " + valor1 + " AND " + valor2;
/* 191 */       buscar(cadenaDeBusqueda);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void busquedaPVP(int dif) {
/* 196 */     if (esDouble(this.cadena)) {
/* 197 */       double valor1 = new Double(this.cadena).doubleValue() - dif;
/* 198 */       double valor2 = new Double(this.cadena).doubleValue() + dif;
/* 199 */       String cadenaDeBusqueda = "SELECT referencia FROM Producto WHERE pvp BETWEEN " + valor1 + " AND " + valor2;
/* 200 */       buscar(cadenaDeBusqueda);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void busquedaGLobal() {
/* 205 */     busquedaReferencia();
/* 206 */     busquedaFamilia();
/* 207 */     busquedaProveedor();
/* 208 */     busquedaDescripcion();
/* 209 */     busquedaStock();
/* 210 */     busquedaCoste(10);
/* 211 */     busquedaPVP(10);
/*     */   }
/*     */ 
/*     */   private void ConstruirLista() {
/* 215 */     for (String ref : this.listaReferencias) {
/* 216 */       String descripcion = ""; String familia = ""; String proveedor = "";
/* 217 */       int stock = 0;
/* 218 */       double coste = 0.0D; double pvp = 0.0D;
/*     */       try {
/* 220 */         this.res = this.con.getRes("SELECT * FROM Producto WHERE referencia = '" + ref + "'");
/* 221 */         if (this.res.next()) {
/* 222 */           descripcion = this.res.getString(3);
/* 223 */           int pr = this.res.getInt(4);
/* 224 */           for (int ind = 0; ind < this.proveedores.length; ind++) {
/* 225 */             if (pr == this.proveedores[ind].getId()) {
/* 226 */               proveedor = this.proveedores[ind].getNombre();
/* 227 */               break;
/*     */             }
/*     */           }
/* 230 */           pr = this.res.getInt(5);
/* 231 */           for (int ind = 0; ind < this.familias.length; ind++) {
/* 232 */             if (pr == this.familias[ind].getId()) {
/* 233 */               familia = this.familias[ind].getNombre();
/* 234 */               break;
/*     */             }
/*     */           }
/* 237 */           coste = this.res.getDouble(6);
/* 238 */           pvp = this.res.getDouble(7);
/*     */         }
/* 240 */         this.res = this.con.getRes("SELECT SUM(io) FROM PIO WHERE referencia = '" + ref + "'");
/* 241 */         if (this.res.next())
/* 242 */           stock = this.res.getInt(1);
/* 243 */         Objeto objeto = new Objeto(ref, familia, proveedor, descripcion, coste, pvp, stock);
/* 244 */         this.listado.add(objeto);
/*     */       } catch (SQLException e) {
/* 246 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public ArrayList<Objeto> getListado() {
/* 252 */     return this.listado;
/*     */   }
/*     */ 
/*     */   private boolean esEntero(String valor) {
/*     */     try {
/* 257 */       Integer.parseInt(valor);
/*     */     }
/*     */     catch (NumberFormatException exc) {
/* 260 */       return false;
/*     */     }
/* 262 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean esDouble(String valor) {
/*     */     try {
/* 267 */       new Double(valor).doubleValue();
/*     */     }
/*     */     catch (NumberFormatException exc) {
/* 270 */       return false;
/*     */     }
/* 272 */     return true;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.data.busqueda.Busqueda
 * JD-Core Version:    0.6.2
 */