/*     */ package facturacion.control;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.Puente;
/*     */ import facturacion.model.Familia;
/*     */ import facturacion.model.ObjetoBusquedaAlmacen;
/*     */ import facturacion.model.Proveedor;
/*     */ import java.io.PrintStream;
/*     */ import java.sql.Connection;
/*     */ import java.sql.DriverManager;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ 
/*     */ public class BusquedaProductosAlmacen
/*     */ {
/*     */   private Connection con;
/*     */   private Statement sentencia;
/*     */   private ResultSet res;
/*     */   private String cadena;
/*  23 */   private ArrayList<ObjetoBusquedaAlmacen> listado = new ArrayList();
/*  24 */   private HashSet<String> listaReferencias = new HashSet();
/*     */   ArrayList<Proveedor> proveedores;
/*     */   ArrayList<Familia> familias;
/*     */ 
/*     */   public BusquedaProductosAlmacen(String cadena)
/*     */   {
/*     */     try
/*     */     {
/*  31 */       this.con = DriverManager.getConnection("jdbc:mysql://" + Inicio.p.getDireccionIP() + "/almacen2", Inicio.p.getUsuario(), Inicio.p.getPassword());
/*     */ 
/*  35 */       this.sentencia = this.con.createStatement();
/*     */     }
/*     */     catch (SQLException exc) {
/*  38 */       System.out.println(exc.getMessage());
/*     */     }
/*  40 */     this.cadena = cadena;
/*  41 */     getFamilias();
/*  42 */     getProveedores();
/*  43 */     iniciarBusqueda();
/*     */   }
/*     */ 
/*     */   private void getFamilias() {
/*  47 */     this.familias = new ArrayList();
/*     */     try
/*     */     {
/*  52 */       this.res = this.sentencia.executeQuery("SELECT * FROM familias");
/*  53 */       while (this.res.next()) {
/*  54 */         int id = this.res.getInt(1);
/*  55 */         String nombre = this.res.getString(2);
/*  56 */         Familia familia = new Familia(id, nombre);
/*  57 */         this.familias.add(familia);
/*     */       }
/*     */     } catch (SQLException ex) {
/*  60 */       Logger.getLogger(BusquedaProductosAlmacen.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void getProveedores() {
/*  65 */     this.proveedores = new ArrayList();
/*     */     try
/*     */     {
/*  70 */       this.res = this.sentencia.executeQuery("SELECT * FROM proveedores");
/*  71 */       while (this.res.next()) {
/*  72 */         int id = this.res.getInt(1);
/*  73 */         String nombre = this.res.getString(2);
/*  74 */         Proveedor proveedor = new Proveedor(id, nombre);
/*  75 */         this.proveedores.add(proveedor);
/*     */       }
/*     */     } catch (SQLException ex) {
/*  78 */       Logger.getLogger(BusquedaProductosAlmacen.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void iniciarBusqueda() {
/*  83 */     if ((this.cadena.length() >= 3) && (this.cadena.substring(1, 2).equals(":"))) {
/*  84 */       String condicion = this.cadena.substring(0, 2);
/*  85 */       if (condicion.equals("R:")) {
/*  86 */         this.cadena = this.cadena.substring(2);
/*  87 */         busquedaReferencia();
/*     */       }
/*  89 */       else if (condicion.equals("F:")) {
/*  90 */         this.cadena = this.cadena.substring(2);
/*  91 */         busquedaFamilia();
/*     */       }
/*  93 */       else if (condicion.equals("P:")) {
/*  94 */         this.cadena = this.cadena.substring(2);
/*  95 */         busquedaProveedor();
/*     */       }
/*  97 */       else if (condicion.equals("D:")) {
/*  98 */         this.cadena = this.cadena.substring(2);
/*  99 */         busquedaDescripcion();
/*     */       }
/* 101 */       else if (condicion.equals("S:")) {
/* 102 */         this.cadena = this.cadena.substring(2);
/* 103 */         busquedaStock();
/*     */       }
/* 105 */       else if (condicion.equals("C:")) {
/* 106 */         this.cadena = this.cadena.substring(2);
/* 107 */         int puntoComa = this.cadena.indexOf(";");
/* 108 */         int dif = 10;
/* 109 */         if (puntoComa != -1) {
/* 110 */           String dife = this.cadena.substring(puntoComa + 1);
/* 111 */           this.cadena = this.cadena.substring(0, puntoComa);
/* 112 */           if (esEntero(dife))
/* 113 */             dif = Integer.parseInt(dife);
/*     */         }
/* 115 */         busquedaCoste(dif);
/*     */       }
/* 117 */       else if (condicion.equals("I:")) {
/* 118 */         this.cadena = this.cadena.substring(2);
/* 119 */         int puntoComa = this.cadena.indexOf(";");
/* 120 */         int dif = 10;
/* 121 */         if (puntoComa != -1) {
/* 122 */           String dife = this.cadena.substring(puntoComa + 1);
/* 123 */           this.cadena = this.cadena.substring(0, puntoComa);
/* 124 */           if (esEntero(dife))
/* 125 */             dif = Integer.parseInt(dife);
/*     */         }
/* 127 */         busquedaPVP(dif);
/*     */       }
/*     */ 
/*     */     }
/* 131 */     else if (this.cadena.equals("")) {
/* 132 */       String cadenaDeBusqueda = "SELECT referencia FROM Producto";
/* 133 */       buscar(cadenaDeBusqueda);
/*     */     }
/*     */     else {
/* 136 */       busquedaGLobal();
/*     */     }
/* 138 */     ConstruirLista();
/*     */   }
/*     */ 
/*     */   private void buscar(String cadenaDeBusqueda) {
/*     */     try {
/* 143 */       this.res = this.sentencia.executeQuery(cadenaDeBusqueda);
/* 144 */       while (this.res.next())
/* 145 */         this.listaReferencias.add(this.res.getString(1));
/*     */     } catch (SQLException e) {
/* 147 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void busquedaReferencia() {
/* 152 */     busquedaReferencia(this.cadena);
/*     */   }
/*     */ 
/*     */   private void busquedaReferencia(String referencia) {
/* 156 */     String cadenaDeBusqueda = "SELECT referencia FROM Producto WHERE referencia LIKE '%" + referencia + "%'";
/* 157 */     buscar(cadenaDeBusqueda);
/*     */   }
/*     */ 
/*     */   private void busquedaFamilia() {
/*     */     try {
/* 162 */       ArrayList listaFam = new ArrayList();
/* 163 */       this.res = this.sentencia.executeQuery("SELECT id FROM familias WHERE nombre LIKE '%" + this.cadena + "%'");
/* 164 */       while (this.res.next())
/* 165 */         listaFam.add(new Integer(this.res.getInt(1))); Iterator i$;
/* 166 */       for (i$ = listaFam.iterator(); i$.hasNext(); ) { int idFam = ((Integer)i$.next()).intValue();
/* 167 */         String cadenaDeBusqueda = "SELECT referencia FROM Producto WHERE familia = " + idFam;
/* 168 */         buscar(cadenaDeBusqueda);
/*     */       }
/*     */     }
/*     */     catch (SQLException e)
/*     */     {
/*     */       Iterator i$;
/* 171 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void busquedaProveedor() {
/*     */     try {
/* 177 */       ArrayList listaProv = new ArrayList();
/* 178 */       this.res = this.sentencia.executeQuery("SELECT id FROM proveedores WHERE nombre LIKE '%" + this.cadena + "%'");
/* 179 */       while (this.res.next())
/* 180 */         listaProv.add(new Integer(this.res.getInt(1))); Iterator i$;
/* 181 */       for (i$ = listaProv.iterator(); i$.hasNext(); ) { int idProv = ((Integer)i$.next()).intValue();
/* 182 */         String cadenaDeBusqueda = "SELECT referencia FROM Producto WHERE proveedor = " + idProv;
/* 183 */         buscar(cadenaDeBusqueda);
/*     */       }
/*     */     }
/*     */     catch (SQLException e)
/*     */     {
/*     */       Iterator i$;
/* 186 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void busquedaDescripcion() {
/* 191 */     String cadenaDeBusqueda = "SELECT referencia FROM Producto WHERE descripcion LIKE '%" + this.cadena + "%'";
/* 192 */     buscar(cadenaDeBusqueda);
/*     */   }
/*     */ 
/*     */   private void busquedaStock() {
/* 196 */     if (esEntero(this.cadena)) {
/* 197 */       String cadenaDeBusqueda = "SELECT referencia, SUM(io) AS suma FROM PIO GROUP BY referencia HAVING suma = " + this.cadena;
/*     */ 
/* 199 */       buscar(cadenaDeBusqueda);
/* 200 */       if (Integer.parseInt(this.cadena) == 0) {
/* 201 */         cadenaDeBusqueda = "SELECT a.referencia FROM Producto a LEFT JOIN PIO b ON a.referencia=b.referencia WHERE b.referencia IS NULL";
/*     */ 
/* 204 */         buscar(cadenaDeBusqueda);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void busquedaCoste(int dif)
/*     */   {
/* 219 */     if (esDouble(this.cadena)) {
/* 220 */       double valor1 = new Double(this.cadena).doubleValue() - dif;
/* 221 */       double valor2 = new Double(this.cadena).doubleValue() + dif;
/* 222 */       String cadenaDeBusqueda = "SELECT referencia FROM Producto WHERE coste BETWEEN " + valor1 + " AND " + valor2;
/* 223 */       buscar(cadenaDeBusqueda);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void busquedaPVP(int dif) {
/* 228 */     if (esDouble(this.cadena)) {
/* 229 */       double valor1 = new Double(this.cadena).doubleValue() - dif;
/* 230 */       double valor2 = new Double(this.cadena).doubleValue() + dif;
/* 231 */       String cadenaDeBusqueda = "SELECT referencia FROM Producto WHERE pvp BETWEEN " + valor1 + " AND " + valor2;
/* 232 */       buscar(cadenaDeBusqueda);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void busquedaGLobal() {
/* 237 */     busquedaReferencia();
/* 238 */     busquedaFamilia();
/* 239 */     busquedaProveedor();
/* 240 */     busquedaDescripcion();
/* 241 */     busquedaStock();
/* 242 */     busquedaCoste(10);
/* 243 */     busquedaPVP(10);
/*     */   }
/*     */ 
/*     */   private void ConstruirLista() {
/* 247 */     for (String ref : this.listaReferencias) {
/* 248 */       String descripcion = ""; String familia = ""; String proveedor = "";
/* 249 */       int stock = 0;
/* 250 */       double coste = 0.0D; double pvp = 0.0D;
/*     */       try {
/* 252 */         this.res = this.sentencia.executeQuery("SELECT * FROM Producto WHERE referencia = '" + ref + "'");
/* 253 */         if (this.res.next()) {
/* 254 */           descripcion = this.res.getString(3);
/* 255 */           int pr = this.res.getInt(4);
/* 256 */           for (Proveedor provee : this.proveedores) {
/* 257 */             if (pr == provee.getId()) {
/* 258 */               proveedor = provee.getNombre();
/* 259 */               break;
/*     */             }
/*     */           }
/* 262 */           pr = this.res.getInt(5);
/* 263 */           for (Familia fam : this.familias) {
/* 264 */             if (pr == fam.getId()) {
/* 265 */               familia = fam.getNombre();
/* 266 */               break;
/*     */             }
/*     */           }
/* 269 */           coste = this.res.getDouble(6);
/* 270 */           pvp = this.res.getDouble(7);
/*     */         }
/* 272 */         this.res = this.sentencia.executeQuery("SELECT SUM(io) FROM PIO WHERE referencia = '" + ref + "'");
/* 273 */         if (this.res.next())
/* 274 */           stock = this.res.getInt(1);
/* 275 */         ObjetoBusquedaAlmacen objeto = new ObjetoBusquedaAlmacen(ref, familia, proveedor, descripcion, coste, pvp, stock);
/* 276 */         this.listado.add(objeto);
/*     */       } catch (SQLException e) {
/* 278 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public ArrayList<ObjetoBusquedaAlmacen> getListado() {
/* 284 */     return this.listado;
/*     */   }
/*     */ 
/*     */   private boolean esEntero(String valor) {
/*     */     try {
/* 289 */       Integer.parseInt(valor);
/*     */     }
/*     */     catch (NumberFormatException exc) {
/* 292 */       return false;
/*     */     }
/* 294 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean esDouble(String valor) {
/*     */     try {
/* 299 */       new Double(valor).doubleValue();
/*     */     }
/*     */     catch (NumberFormatException exc) {
/* 302 */       return false;
/*     */     }
/* 304 */     return true;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     facturacion.control.BusquedaProductosAlmacen
 * JD-Core Version:    0.6.2
 */