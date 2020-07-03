/*     */ package almacen2.procesos;
/*     */ 
/*     */ import java.sql.Connection;
/*     */ import java.sql.DriverManager;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ 
/*     */ public class CrearBD
/*     */ {
/*  20 */   private static String usuario = "root";
/*  21 */   private static String contrasena = "123456";
/*     */   private Connection con;
/*     */   private Statement sentencia;
/*     */ 
/*     */   public CrearBD()
/*     */   {
/*     */     try
/*     */     {
/*  28 */       this.con = DriverManager.getConnection("jdbc:mysql://localhost/", usuario, contrasena);
/*  29 */       this.sentencia = this.con.createStatement();
/*  30 */       this.sentencia.executeUpdate("DROP DATABASE IF EXISTS almacen2");
/*  31 */       this.sentencia.executeUpdate("CREATE DATABASE almacen2");
/*  32 */       this.con = DriverManager.getConnection("jdbc:mysql://localhost/almacen2", usuario, contrasena);
/*  33 */       this.sentencia = this.con.createStatement();
/*  34 */       this.sentencia.executeUpdate("CREATE TABLE familias (  id INTEGER UNSIGNED NOT NULL,  nombre VARCHAR(45) NOT NULL,  subventas INTEGER UNSIGNED,  subcomras INTEGER UNSIGNED,  PRIMARY KEY(id)) ENGINE=MyISAM;");
/*     */ 
/*  41 */       this.sentencia.executeUpdate("CREATE TABLE pedidos (  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,  numero VARCHAR(25) NOT NULL,  proveedor INTEGER UNSIGNED NOT NULL,  fecha DATE NOT NULL,  compras BOOLEAN NOT NULL DEFAULT TRUE,  PRIMARY KEY(id)) ENGINE=MyISAM;");
/*     */ 
/*  49 */       this.sentencia.executeUpdate("CREATE TABLE PIO (  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,  referencia VARCHAR(20) NOT NULL,  fecha DATE NOT NULL,  importe DOUBLE(10,2) ZEROFILL,  io TINYINT NOT NULL DEFAULT 1,  PRIMARY KEY(id, referencia),  INDEX PIO_fecha(fecha),  INDEX PIO_ref(referencia)) ENGINE=MyISAM;");
/*     */ 
/*  59 */       this.sentencia.executeUpdate("CREATE TABLE Producto (  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,  referencia VARCHAR(20) NOT NULL,  descripcion VARCHAR(80),  proveedor INTEGER UNSIGNED NOT NULL,  familia INTEGER UNSIGNED NOT NULL,  coste DOUBLE(10,2) ZEROFILL,  pvp DOUBLE(10,2) ZEROFILL,  imagen VARCHAR(255),  stockminimo INTEGER UNSIGNED NOT NULL DEFAULT 0,  pedidominimo INTEGER UNSIGNED NOT NULL DEFAULT 1,  idtipoiva INTEGER NOT NULL DEFAULT -1,  PRIMARY KEY(id, referencia),  INDEX Producto_proveedor(proveedor),  INDEX Producto_familia(familia),  INDEX Producto_ref(referencia)) ENGINE=MyISAM;");
/*     */ 
/*  76 */       this.sentencia.executeUpdate("CREATE TABLE productos_pedidos (  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,  id_pedido INTEGER UNSIGNED NOT NULL,  referencia VARCHAR(20) NOT NULL,  recibido BOOL NOT NULL DEFAULT FALSE,  PRIMARY KEY(id, id_pedido, referencia)) ENGINE=MyISAM;");
/*     */ 
/*  83 */       this.sentencia.executeUpdate("CREATE TABLE proveedores (  id INTEGER UNSIGNED NOT NULL,  nombre VARCHAR(45) NOT NULL,  subcuenta INTEGER UNSIGNED,  PRIMARY KEY(id)) ENGINE=MyISAM;");
/*     */ 
/*  89 */       this.sentencia.close();
/*  90 */       this.con.close();
/*     */     } catch (SQLException e) {
/*  92 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 100 */     if (args.length >= 2) {
/* 101 */       usuario = args[0];
/* 102 */       contrasena = args[1];
/*     */     }
/* 104 */     if (args.length == 1)
/* 105 */       usuario = args[0];
/* 106 */     new CrearBD();
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.procesos.CrearBD
 * JD-Core Version:    0.6.2
 */