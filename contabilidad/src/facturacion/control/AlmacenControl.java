/*     */ package facturacion.control;
/*     */ 
/*     */ import almacen2.data.AlmacenInterno;
/*     */ import contaes.Inicio;
/*     */ import contaes.Puente;
/*     */ import contaes.manejoDatos.ManejoSubcuentas;
/*     */ import contaes.manejoDatos.TipoSubcuenta;
/*     */ import facturacion.model.Familia;
/*     */ import facturacion.model.Producto;
/*     */ import java.io.PrintStream;
/*     */ import java.sql.Connection;
/*     */ import java.sql.DriverManager;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ 
/*     */ public class AlmacenControl
/*     */ {
/*     */   private ResultSet resAc;
/*     */   private Connection con;
/*     */   private Statement sentencia;
/*     */ 
/*     */   public AlmacenControl()
/*     */   {
/*     */     try
/*     */     {
/*  38 */       this.con = DriverManager.getConnection("jdbc:mysql://" + Inicio.p.getDireccionIP() + "/almacen2", Inicio.p.getUsuario(), Inicio.p.getPassword());
/*     */ 
/*  41 */       this.sentencia = this.con.createStatement();
/*     */     } catch (SQLException exc) {
/*  43 */       System.out.println(exc.getMessage());
/*     */     }
/*     */   }
/*     */ 
/*     */   public int isSubcuentaEnUso(int subcuenta)
/*     */   {
/*  55 */     int rstado = 0;
/*     */     try {
/*  57 */       this.resAc = this.sentencia.executeQuery("SELECT * FROM familias WHERE subventas = " + subcuenta + " OR subcompras = " + subcuenta);
/*  58 */       if (this.resAc.next()) {
/*  59 */         rstado = 5;
/*     */       }
/*  61 */       if (rstado == 0) {
/*  62 */         this.resAc = this.sentencia.executeQuery("SELECT * FROM proveedores WHERE subcuenta = " + subcuenta);
/*  63 */         if (this.resAc.next()) {
/*  64 */           rstado = 6;
/*     */         }
/*     */       }
/*  67 */       if (rstado == 0) {
/*  68 */         this.resAc = this.sentencia.executeQuery("SELECT * FROM pedidos WHERE proveedor = " + subcuenta + " AND compras = 0");
/*  69 */         if (this.resAc.next())
/*  70 */           rstado = 7;
/*     */       }
/*     */     }
/*     */     catch (SQLException ex) {
/*  74 */       ex.printStackTrace();
/*     */     }
/*  76 */     return rstado;
/*     */   }
/*     */ 
/*     */   public ArrayList<AlmacenInterno> getAlmacenes() {
/*  80 */     ArrayList almacenes = new ArrayList();
/*  81 */     AlmacenInterno objeto = null;
/*     */     try {
/*  83 */       this.resAc = this.sentencia.executeQuery("SELECT * FROM almacenes ORDER BY id");
/*  84 */       while (this.resAc.next()) {
/*  85 */         objeto = null;
/*  86 */         int id = this.resAc.getInt(1);
/*  87 */         String nombre = this.resAc.getString(2);
/*  88 */         objeto = new AlmacenInterno(Integer.valueOf(id), nombre);
/*  89 */         if (objeto != null)
/*  90 */           almacenes.add(objeto);
/*     */       }
/*     */     }
/*     */     catch (SQLException ex) {
/*  94 */       ex.printStackTrace();
/*     */     }
/*  96 */     return almacenes;
/*     */   }
/*     */ 
/*     */   public ArrayList<Familia> getFamilias() {
/* 100 */     ArrayList familias = new ArrayList();
/*     */     try
/*     */     {
/* 105 */       this.resAc = this.sentencia.executeQuery("SELECT * FROM familias");
/* 106 */       while (this.resAc.next()) {
/* 107 */         int id = this.resAc.getInt(1);
/* 108 */         String nombre = this.resAc.getString(2);
/* 109 */         Familia familia = new Familia(id, nombre);
/* 110 */         familias.add(familia);
/*     */       }
/*     */     } catch (SQLException ex) {
/* 113 */       Logger.getLogger(AlmacenControl.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/* 115 */     return familias;
/*     */   }
/*     */ 
/*     */   public Producto getProducto(String referencia, boolean venta)
/*     */   {
/* 132 */     Producto producto = null;
/*     */     try {
/* 134 */       Integer id = Integer.valueOf(-1);
/* 135 */       String campoPrecio = venta ? "pvp" : "coste";
/* 136 */       String descripcion = "";
/* 137 */       Double precio = Double.valueOf(0.0D);
/* 138 */       Integer familia = null;
/* 139 */       TipoSubcuenta subcuenta = null;
/* 140 */       this.resAc = this.sentencia.executeQuery("SELECT id,descripcion,familia," + campoPrecio + " FROM Producto WHERE referencia = '" + referencia + "'");
/* 141 */       if (this.resAc.next()) {
/* 142 */         id = Integer.valueOf(this.resAc.getInt(1));
/* 143 */         descripcion = this.resAc.getString(2);
/* 144 */         familia = Integer.valueOf(this.resAc.getInt(3));
/* 145 */         precio = Double.valueOf(this.resAc.getDouble(4));
/*     */       }
/* 147 */       if (familia != null) {
/* 148 */         subcuenta = getSubcuentaFamilia(familia.intValue(), venta);
/* 149 */         if (subcuenta != null)
/* 150 */           producto = new Producto(id, referencia, descripcion, subcuenta, precio);
/*     */       }
/*     */     }
/*     */     catch (SQLException ex) {
/* 154 */       Logger.getLogger(AlmacenControl.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/* 156 */     return producto;
/*     */   }
/*     */ 
/*     */   public Producto getProducto(Integer id, boolean venta) {
/* 160 */     Producto producto = null;
/*     */     try {
/* 162 */       String campoPrecio = venta ? "pvp" : "coste";
/* 163 */       String referencia = "";
/* 164 */       String descripcion = "";
/* 165 */       Double precio = Double.valueOf(0.0D);
/* 166 */       Integer familia = null;
/* 167 */       TipoSubcuenta subcuenta = null;
/* 168 */       this.resAc = this.sentencia.executeQuery("SELECT descripcion,familia," + campoPrecio + ",referencia FROM Producto WHERE id = " + id);
/* 169 */       if (this.resAc.next()) {
/* 170 */         descripcion = this.resAc.getString(1);
/* 171 */         familia = Integer.valueOf(this.resAc.getInt(2));
/* 172 */         precio = Double.valueOf(this.resAc.getDouble(3));
/* 173 */         referencia = this.resAc.getString(4);
/*     */       }
/* 175 */       if (familia != null) {
/* 176 */         subcuenta = getSubcuentaFamilia(familia.intValue(), venta);
/* 177 */         if (subcuenta != null)
/* 178 */           producto = new Producto(id, referencia, descripcion, subcuenta, precio);
/*     */       }
/*     */     }
/*     */     catch (SQLException ex) {
/* 182 */       Logger.getLogger(AlmacenControl.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/* 184 */     return producto;
/*     */   }
/*     */ 
/*     */   public ArrayList<Producto> getProductos(boolean venta) {
/* 188 */     ArrayList productos = new ArrayList();
/* 189 */     ArrayList listaId = new ArrayList();
/*     */     try {
/* 191 */       this.resAc = this.sentencia.executeQuery("SELECT id FROM Producto ORDER BY descripcion");
/* 192 */       while (this.resAc.next())
/* 193 */         listaId.add(Integer.valueOf(this.resAc.getInt(1)));
/*     */     }
/*     */     catch (SQLException ex) {
/* 196 */       Logger.getLogger(AlmacenControl.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/*     */ 
/* 199 */     for (Integer referencia :(List<Integer>) listaId) {
/* 200 */       Producto producto = getProducto(referencia, venta);
/* 201 */       if (producto != null) {
/* 202 */         productos.add(producto);
/*     */       }
/*     */     }
/* 205 */     return productos;
/*     */   }
/*     */ 
/*     */   public void insertVenta(String referencia, Date fecha, double importe, int unidades, int almacen) {
/* 209 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
/* 210 */     boolean newAlmacen = false;
/*     */     try {
/* 212 */       this.resAc = this.sentencia.executeQuery("SELECT * FROM Producto WHERE referencia = '" + referencia + "'");
/* 213 */       if (this.resAc.next()) {
/* 214 */         newAlmacen = true;
/*     */       }
/* 216 */       if (newAlmacen) {
/* 217 */         if (unidades >= 0) {
/* 218 */           for (int i = 0; i < unidades; i++) {
/* 219 */             this.sentencia.executeUpdate("INSERT INTO PIO (referencia,fecha,importe,io,almacen) VALUES ('" + referencia + "','" + sdf.format(fecha) + "'," + importe + ",-1," + almacen + ")");
/*     */           }
/*     */         }
/* 222 */         else if (unidades < 0) {
/* 223 */           unidades = -1 * unidades;
/* 224 */           double coste = getCosteProducto(referencia).doubleValue();
/* 225 */           insertCompra(referencia, fecha, coste, unidades, almacen);
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/*     */     catch (SQLException ex)
/*     */     {
/* 239 */       Logger.getLogger(AlmacenControl.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void insertCompra(String referencia, Date fecha, double importe, int unidades, int almacen) {
/* 244 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
/*     */     try {
/* 246 */       for (int i = 0; i < unidades; i++)
/* 247 */         this.sentencia.executeUpdate("INSERT INTO PIO (referencia,fecha,importe,io,almacen) VALUES ('" + referencia + "','" + sdf.format(fecha) + "'," + importe + ",1," + almacen + ")");
/*     */     }
/*     */     catch (SQLException ex) {
/* 250 */       Logger.getLogger(AlmacenControl.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/*     */   }
/*     */ 
/*     */   public Double getCosteProducto(String referencia) {
/* 255 */     Double coste = null;
/*     */     try {
/* 257 */       this.resAc = this.sentencia.executeQuery("SELECT coste FROM Producto WHERE referencia = '" + referencia + "'");
/* 258 */       if (this.resAc.next())
/* 259 */         coste = Double.valueOf(this.resAc.getDouble(1));
/*     */     }
/*     */     catch (SQLException ex) {
/* 262 */       Logger.getLogger(AlmacenControl.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/* 264 */     return coste;
/*     */   }
/*     */ 
/*     */   public void suprimirPIO(String referencia, String date, double importe, int io) {
/*     */     try {
/* 269 */       int id = -1;
/* 270 */       this.resAc = this.sentencia.executeQuery("SELECT id FROM PIO WHERE referencia = '" + referencia + "'" + " AND fecha = '" + date + "'" + " AND importe = " + importe + " AND io = " + io);
/*     */ 
/* 275 */       if (this.resAc.next()) {
/* 276 */         id = this.resAc.getInt(1);
/*     */       }
/* 278 */       if (id != -1)
/* 279 */         this.sentencia.executeUpdate("DELETE FROM PIO WHERE id = " + id);
/*     */     }
/*     */     catch (SQLException ex) {
/* 282 */       Logger.getLogger(AlmacenControl.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/*     */   }
/*     */ 
/*     */   private TipoSubcuenta getSubcuenta(int codigo) {
/* 287 */     ManejoSubcuentas mS = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 288 */     return mS.datos(codigo);
/*     */   }
/*     */ 
/*     */   public TipoSubcuenta getSubcuentaFamilia(int familia, boolean venta) {
/* 292 */     String campo = venta ? "subventas" : "subcompras";
/* 293 */     int codigoSubcuenta = 0;
/*     */     try {
/* 295 */       this.resAc = this.sentencia.executeQuery("SELECT " + campo + " FROM familias WHERE id = " + familia);
/* 296 */       if (this.resAc.next())
/* 297 */         codigoSubcuenta = this.resAc.getInt(1);
/*     */     }
/*     */     catch (SQLException ex) {
/* 300 */       Logger.getLogger(AlmacenControl.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/* 302 */     return getSubcuenta(codigoSubcuenta);
/*     */   }
/*     */ 
/*     */   public int getIvaProducto(int idProducto) {
/* 306 */     int id = -1;
/*     */     try {
/* 308 */       this.resAc = this.sentencia.executeQuery("SELECT idtipoiva FROM Producto WHERE id = " + idProducto);
/* 309 */       if (this.resAc.next())
/* 310 */         id = this.resAc.getInt(1);
/*     */     }
/*     */     catch (SQLException ex) {
/* 313 */       Logger.getLogger(AlmacenControl.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/* 315 */     return id;
/*     */   }
/*     */ 
/*     */   public Integer getIdTicketAnterior(int idTicket) {
/* 319 */     Integer id = null;
/* 320 */     String cadenaSQL = "SELECT a.idplazoanterior FROM tickets a WHERE a.id = ?";
/*     */     try {
/* 322 */       PreparedStatement ps = this.con.prepareStatement(cadenaSQL);
/* 323 */       ps.setInt(1, idTicket);
/* 324 */       this.resAc = ps.executeQuery();
/* 325 */       if (this.resAc.next())
/* 326 */         id = Integer.valueOf(this.resAc.getInt(1));
/*     */     }
/*     */     catch (SQLException ex) {
/* 329 */       Logger.getLogger(AlmacenControl.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/* 331 */     return id;
/*     */   }
/*     */ 
/*     */   public double plazosAnteriores(int idProducto, Integer idTicketA) {
/* 335 */     double importe = 0.0D;
/* 336 */     if (idTicketA != null) {
/* 337 */       Integer idTicketAA = null;
/* 338 */       String cadenaSQL = "SELECT (b.unidades*b.importe), a.idplazoanterior FROM tickets a JOIN ventaspos b ON a.id = b.idticket WHERE a.id = ? AND b.idproducto =  ?";
/*     */       try
/*     */       {
/* 342 */         PreparedStatement ps = this.con.prepareStatement(cadenaSQL);
/* 343 */         ps.setInt(1, idTicketA.intValue());
/* 344 */         ps.setInt(2, idProducto);
/* 345 */         this.resAc = ps.executeQuery();
/* 346 */         if (this.resAc.next()) {
/* 347 */           importe += this.resAc.getDouble(1);
/* 348 */           idTicketAA = Integer.valueOf(this.resAc.getInt(2));
/*     */         }
/*     */       } catch (SQLException ex) {
/* 351 */         Logger.getLogger(AlmacenControl.class.getName()).log(Level.SEVERE, null, ex);
/*     */       }
/* 353 */       importe += plazosAnteriores(idProducto, idTicketAA);
/*     */     }
/* 355 */     return importe;
/*     */   }
/*     */ 
/*     */   public ResultSet getRes() {
/* 359 */     return this.resAc;
/*     */   }
/*     */ 
/*     */   public Statement getSentencia() {
/* 363 */     return this.sentencia;
/*     */   }
/*     */ 
/*     */   public void cerrarConexion() {
/*     */     try {
/* 368 */       if (this.resAc != null) {
/* 369 */         this.resAc.close();
/* 370 */         this.resAc = null;
/*     */       }
/* 372 */       if (this.sentencia != null) {
/* 373 */         this.sentencia.close();
/* 374 */         this.sentencia = null;
/*     */       }
/* 376 */       if (this.con != null) {
/* 377 */         this.con.close();
/* 378 */         this.con = null;
/*     */       }
/*     */     } catch (SQLException ex) {
/* 381 */       Logger.getLogger(AlmacenControl.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     facturacion.control.AlmacenControl
 * JD-Core Version:    0.6.2
 */