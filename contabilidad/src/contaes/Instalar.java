/*     */ package contaes;
/*     */ 
/*     */ import contaes.manejoDatos.auxiliar.LeerFichero;
/*     */ import java.io.IOException;
/*     */ import java.sql.Connection;
/*     */ import java.sql.DriverManager;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import java.util.LinkedList;
import java.util.List;
/*     */ import java.util.Properties;
/*     */ 
/*     */ public class Instalar
/*     */ {
/*     */   private Connection con;
/*     */   private Statement sentencia;
/*     */   private ResultSet res;
/*     */   private String error;
/*     */ 
/*     */   public String getError()
/*     */   {
/*  47 */     return this.error;
/*     */   }
/*     */ 
/*     */   public int yaInstalado()
/*     */   {
/*  56 */     int retorno = -1;
/*     */     try {
/*  58 */       this.con = DriverManager.getConnection("jdbc:mysql://" + Inicio.p.getDireccionIP() + "/", Inicio.p.getUsuario(), Inicio.p.getPassword());
/*     */ 
/*  61 */       this.sentencia = this.con.createStatement();
/*  62 */       this.res = this.sentencia.executeQuery("SHOW DATABASES");
/*  63 */       while (this.res.next()) {
/*  64 */         String nombre = this.res.getString(1);
/*  65 */         if ((nombre.equals("contaes")) || (nombre.equals("almacen2"))) {
/*  66 */           retorno++;
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*  71 */       this.sentencia.close();
/*  72 */       this.con.close();
/*     */     }
/*     */     catch (SQLException e) {
/*  75 */       e.printStackTrace();
/*  76 */       this.error = e.getMessage();
/*  77 */       return -2;
/*     */     }
/*  79 */     return retorno;
/*     */   }
/*     */ 
/*     */   public boolean instalacion()
/*     */   {
/*     */     try
/*     */     {
/*  89 */       this.con = DriverManager.getConnection("jdbc:mysql://" + Inicio.p.getDireccionIP() + "/", Inicio.p.getUsuario(), Inicio.p.getPassword());
/*     */ 
/*  92 */       this.sentencia = this.con.createStatement();
/*  93 */       this.sentencia.executeUpdate("DROP DATABASE IF EXISTS contaes");
/*  94 */       this.sentencia.executeUpdate("CREATE DATABASE contaes");
/*  95 */       this.sentencia.executeUpdate("DROP DATABASE IF EXISTS almacen2");
/*  96 */       this.sentencia.executeUpdate("CREATE DATABASE almacen2");
/*     */ 
/*  98 */       this.con = DriverManager.getConnection("jdbc:mysql://" + Inicio.p.getDireccionIP() + "/contaes", Inicio.p.getUsuario(), Inicio.p.getPassword());
/*     */ 
/* 101 */       this.sentencia = this.con.createStatement();
/* 102 */       this.sentencia.executeUpdate("CREATE TABLE Conceptos (id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,descripcion VARCHAR(30) NOT NULL DEFAULT '',PRIMARY KEY(id)) ENGINE=MyISAM;");
/*     */ 
/* 107 */       this.sentencia.executeUpdate("INSERT INTO Conceptos(descripcion) VALUES('Ingreso en Banco'),('Intereses préstamo'),('Ingreso en caja'),('Pago por caja');");
/*     */ 
/* 112 */       this.sentencia.executeUpdate("CREATE TABLE Empresas (id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,nombre VARCHAR(25) NOT NULL DEFAULT '',NIF VARCHAR(11),direccion VARCHAR(90),localidad VARCHAR(40),provincia VARCHAR(40),codpostal VARCHAR(7),telefono VARCHAR(14),fax VARCHAR(14),prefijo VARCHAR(10) NOT NULL DEFAULT '',PRIMARY KEY(id)) ENGINE=MyISAM;");
/*     */ 
/* 125 */       this.sentencia.executeUpdate("CREATE TABLE aPatronP (id INTEGER UNSIGNED NOT NULL,descripcion VARCHAR(50) NOT NULL DEFAULT '',concepto VARCHAR(30) NOT NULL DEFAULT '',marca CHAR(1) NOT NULL DEFAULT '',PRIMARY KEY(id)) ENGINE=MyISAM;");
/*     */ 
/* 132 */       this.sentencia.executeUpdate("CREATE TABLE aPatronS (id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,aPatronP_id INTEGER UNSIGNED NOT NULL DEFAULT '0',cuenta INTEGER UNSIGNED NOT NULL DEFAULT '0',importe DOUBLE UNSIGNED NOT NULL DEFAULT '0',CA CHAR(1) NOT NULL DEFAULT '',PRIMARY KEY(id),INDEX Index1(aPatronP_id)) ENGINE=MyISAM;");
/*     */ 
/* 150 */       this.sentencia.executeUpdate("CREATE TABLE Plan_contable (codigo int(10) unsigned NOT NULL default '0',nombre varchar(255) NOT NULL DEFAULT '',grupo_bal varchar(9) NOT NULL DEFAULT '',PRIMARY KEY  (codigo),FULLTEXT INDEX plan_contable_nombre(nombre)) ENGINE=MyISAM");
/*     */ 
/* 157 */       this.sentencia.executeUpdate("CREATE TABLE tiposIVA (id INT NOT NULL AUTO_INCREMENT,nombre VARCHAR(255) NOT NULL,iva DOUBLE UNSIGNED NOT NULL DEFAULT 16,re DOUBLE UNSIGNED NOT NULL DEFAULT 0,cuentaRep INTEGER UNSIGNED,cuentaSop INTEGER UNSIGNED,cuentaRE INTEGER UNSIGNED,cuentaRepIntra INTEGER UNSIGNED,cuentaSopIntra INTEGER UNSIGNED,cuentaImpor INTEGER UNSIGNED,PRIMARY KEY (id),INDEX nombre(nombre)) ENGINE=MyISAM;");
/*     */ 
/* 171 */       this.sentencia.executeUpdate("CREATE TABLE formasPago (  idFormaPago INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,  pago VARCHAR(255) NOT NULL,  diaFijoPago INTEGER UNSIGNED,  diasEntrePagos INTEGER UNSIGNED NOT NULL,  diasPrimerPago INTEGER UNSIGNED NOT NULL,  numeroPagos INTEGER UNSIGNED NOT NULL,  PRIMARY KEY (idFormaPago)) ENGINE=MyISAM;");
/*     */ 
/* 180 */       this.sentencia.executeUpdate("CREATE TABLE paises (  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,  nombre VARCHAR(255) NOT NULL,  situacion INTEGER UNSIGNED NOT NULL DEFAULT 0,  PRIMARY KEY (id)) ENGINE=MyISAM;");
/*     */ 
/* 186 */       this.sentencia.executeUpdate("INSERT INTO paises VALUES (1,'España',0);");
/*     */ 
/* 198 */       Properties sistema = System.getProperties();
/* 199 */       String sisOp = sistema.getProperty("os.name");
/* 200 */       String archivoPGC = sisOp.substring(0, 3).equals("Mac") ? "pgc08MAC.txt" : "pgc08.txt";
/* 201 */       String sql = "INSERT INTO Plan_contable (codigo,nombre,grupo_bal) VALUES(?,?,?)";
/*     */ 
/* 203 */       LeerFichero pgc = new LeerFichero("configdir/" + archivoPGC);
/* 204 */       int numeroCuentas = Integer.parseInt(pgc.leer());
/* 205 */       PreparedStatement ps = this.con.prepareCall(sql);
/*     */ 
/* 207 */       for (int x = 0; x < numeroCuentas; x++) {
/* 208 */         String linea = pgc.leer();
/* 209 */         int codigo = Integer.parseInt(linea.substring(0, linea.indexOf(";")));
/* 210 */         String nombre = linea.substring(linea.indexOf(";") + 1, linea.lastIndexOf(";"));
/* 211 */         String codBal = linea.substring(linea.lastIndexOf(";") + 1);
/* 212 */         ps.setInt(1, codigo);
/* 213 */         ps.setString(2, nombre);
/* 214 */         ps.setString(3, codBal);
/* 215 */         ps.execute();
/* 216 */         if (pgc.eof())
/*     */         {
/*     */           break;
/*     */         }
/*     */       }
/*     */ 
/* 222 */       this.con = DriverManager.getConnection("jdbc:mysql://" + Inicio.p.getDireccionIP() + "/almacen2", Inicio.p.getUsuario(), Inicio.p.getPassword());
/*     */ 
/* 225 */       this.sentencia = this.con.createStatement();
/* 226 */       this.sentencia.executeUpdate("CREATE TABLE familias (  id INTEGER UNSIGNED NOT NULL,  nombre VARCHAR(45) NOT NULL,  subventas INTEGER UNSIGNED,  subcompras INTEGER UNSIGNED,  PRIMARY KEY(id)) ENGINE=MyISAM;");
/*     */ 
/* 233 */       this.sentencia.executeUpdate("CREATE TABLE pedidos (  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,  numero VARCHAR(25) NOT NULL,  proveedor INTEGER UNSIGNED NOT NULL,  fecha DATE NOT NULL,  compras BOOLEAN NOT NULL DEFAULT TRUE,  PRIMARY KEY(id)) ENGINE=MyISAM;");
/*     */ 
/* 241 */       this.sentencia.executeUpdate("CREATE TABLE PIO (  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,  referencia VARCHAR(20) NOT NULL,  fecha DATE NOT NULL,  importe DOUBLE(10,2) ZEROFILL,  io TINYINT NOT NULL DEFAULT 1,  almacen INTEGER NOT NULL DEFAULT 1,  PRIMARY KEY(id, referencia),  INDEX PIO_fecha(fecha),  INDEX PIO_ref(referencia),  INDEX PIO_alma(almacen)) ENGINE=MyISAM;");
/*     */ 
/* 253 */       this.sentencia.executeUpdate("CREATE TABLE Producto (  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,  referencia VARCHAR(20) NOT NULL,  descripcion VARCHAR(80),  proveedor INTEGER UNSIGNED NOT NULL,  familia INTEGER UNSIGNED NOT NULL,  coste DOUBLE(10,2) ZEROFILL,  pvp DOUBLE(10,2) ZEROFILL,  imagen BLOB,  stockminimo INTEGER UNSIGNED NOT NULL DEFAULT 0,  pedidominimo INTEGER UNSIGNED NOT NULL DEFAULT 1,  idtipoiva INTEGER NOT NULL DEFAULT -1,  PRIMARY KEY(id, referencia),  INDEX Producto_proveedor(proveedor),  INDEX Producto_familia(familia),  INDEX Producto_ref(referencia)) ENGINE=MyISAM;");
/*     */ 
/* 270 */       this.sentencia.executeUpdate("CREATE TABLE productos_pedidos (  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,  id_pedido INTEGER UNSIGNED NOT NULL,  referencia VARCHAR(20) NOT NULL,  importe DOUBLE NOT NULL DEFAULT 0,  recibido BOOLEAN NOT NULL DEFAULT FALSE,  unidades INTEGER NOT NULL,  PRIMARY KEY(id, id_pedido, referencia)) ENGINE=MyISAM;");
/*     */ 
/* 279 */       this.sentencia.executeUpdate("CREATE TABLE proveedores (  id INTEGER UNSIGNED NOT NULL,  nombre VARCHAR(45) NOT NULL,  subcuenta INTEGER UNSIGNED,  PRIMARY KEY(id)) ENGINE=MyISAM;");
/*     */ 
/* 285 */       this.sentencia.executeUpdate("CREATE TABLE almacenes (  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,  nombre VARCHAR(255) NOT NULL,  PRIMARY KEY(id)) ENGINE=MyISAM;");
/*     */ 
/* 290 */       this.sentencia.executeUpdate("INSERT INTO almacenes VALUES(1,'Principal');");
/* 291 */       this.sentencia.executeUpdate("CREATE TABLE mediospago ( id INTEGER NOT NULL AUTO_INCREMENT, nombre VARCHAR(255) NOT NULL, cuentacobro INTEGER NOT NULL, comision DOUBLE NOT NULL DEFAULT 0.0, cuentacomision INTEGER NOT NULL DEFAULT -1, PRIMARY KEY (id)) ENGINE = MyISAM;");
/*     */ 
/* 299 */       this.sentencia.executeUpdate("CREATE TABLE tickets ( id INTEGER NOT NULL AUTO_INCREMENT, numero INTEGER NOT NULL, fecha DATE NOT NULL, almacen INTEGER NOT NULL DEFAULT 1, mediopago INTEGER NOT NULL, plazos INTEGER NOT NULL, vendedor INTEGER NOT NULL, cliente INTEGER NOT NULL, cerrado BOOLEAN NOT NULL DEFAULT false, idplazoanterior INTEGER UNSIGNED DEFAULT NULL, PRIMARY KEY (id), INDEX numeros(numero)) ENGINE = MyISAM;");
/*     */ 
/* 313 */       this.sentencia.executeUpdate("CREATE TABLE vendedores ( id INTEGER NOT NULL AUTO_INCREMENT, nombre VARCHAR(255) NOT NULL, PRIMARY KEY (id)) ENGINE = MyISAM;");
/*     */ 
/* 318 */       this.sentencia.executeUpdate("CREATE TABLE ventaspos ( id INTEGER NOT NULL AUTO_INCREMENT, idticket INTEGER NOT NULL, idproducto INTEGER NOT NULL, descripcion VARCHAR(255) NOT NULL, unidades INTEGER NOT NULL DEFAULT 1, importe DOUBLE NOT NULL DEFAULT 0, PRIMARY KEY (id), INDEX tickets(idticket), INDEX productos(idproducto)) ENGINE = MyISAM;");
/*     */ 
/* 329 */       this.sentencia.executeUpdate("CREATE TABLE notaspos (id INT NOT NULL AUTO_INCREMENT,idventa INT NOT NULL,nota VARCHAR(255),PRIMARY KEY (id, idventa)) ENGINE = MyISAM;");
/*     */ 
/* 336 */       this.sentencia.close();
/* 337 */       this.con.close();
/* 338 */       return true;
/*     */     } catch (SQLException e) {
/* 340 */       e.printStackTrace();
/* 341 */       this.error = e.getMessage();
/*     */     } catch (IOException e) {
/* 343 */       e.printStackTrace();
/*     */     }
/* 345 */     return false;
/*     */   }
/*     */ 
/*     */   public void de21a22()
/*     */   {
/*     */     try
/*     */     {
/* 354 */       this.con = DriverManager.getConnection("jdbc:mysql://" + Inicio.p.getDireccionIP() + "/almacen2", Inicio.p.getUsuario(), Inicio.p.getPassword());
/*     */ 
/* 357 */       this.sentencia = this.con.createStatement();
/* 358 */       this.sentencia.executeUpdate("ALTER TABLE tickets ADD COLUMN idplazoanterior INTEGER UNSIGNED DEFAULT NULL AFTER `cerrado`;");
/*     */ 
/* 360 */       this.sentencia.close();
/* 361 */       this.con.close();
/*     */     }
/*     */     catch (SQLException e) {
/* 364 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void de24a241()
/*     */   {
/*     */     try
/*     */     {
/* 373 */       this.con = DriverManager.getConnection("jdbc:mysql://" + Inicio.p.getDireccionIP() + "/almacen2", Inicio.p.getUsuario(), Inicio.p.getPassword());
/*     */ 
/* 376 */       this.sentencia = this.con.createStatement();
/* 377 */       this.sentencia.executeUpdate("UPDATE tickets SET cerrado = 3 WHERE cerrado = 1;");
/* 378 */       this.sentencia.close();
/* 379 */       this.con.close();
/*     */     }
/*     */     catch (SQLException e) {
/* 382 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void de241a242()
/*     */   {
/*     */     try
/*     */     {
/* 391 */       this.con = DriverManager.getConnection("jdbc:mysql://" + Inicio.p.getDireccionIP() + "/contaes", Inicio.p.getUsuario(), Inicio.p.getPassword());
/*     */ 
/* 394 */       this.sentencia = this.con.createStatement();
/*     */ 
/* 396 */       LinkedList listaEmpresas = new LinkedList();
/* 397 */       this.res = this.sentencia.executeQuery("SELECT id FROM Empresas");
/* 398 */       while (this.res.next()) {
/* 399 */         Integer id = Integer.valueOf(this.res.getInt(1));
/* 400 */         listaEmpresas.add(id.toString());
/*     */       }
/* 402 */       this.res.close();
/* 403 */       for (String id :(List<String>) listaEmpresas) {
/* 404 */         this.con = null;
/* 405 */         this.sentencia = null;
/* 406 */         this.con = DriverManager.getConnection("jdbc:mysql://" + Inicio.p.getDireccionIP() + "/contaes" + id, Inicio.p.getUsuario(), Inicio.p.getPassword());
/*     */ 
/* 409 */         this.sentencia = this.con.createStatement();
/* 410 */         this.sentencia.executeUpdate("ALTER TABLE facturascompras ADD COLUMN isalmacenada BOOLEAN NOT NULL DEFAULT 0 AFTER contabilizada;");
/* 411 */         this.sentencia.executeUpdate("ALTER TABLE facturas ADD COLUMN isalmacenada BOOLEAN NOT NULL DEFAULT 0 AFTER contabilizada;");
/*     */       }
/* 413 */       this.sentencia.close();
/* 414 */       this.con.close();
/*     */     } catch (SQLException e) {
/* 416 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void de244a245()
/*     */   {
/*     */     try
/*     */     {
/* 425 */       this.con = DriverManager.getConnection("jdbc:mysql://" + Inicio.p.getDireccionIP() + "/almacen2", Inicio.p.getUsuario(), Inicio.p.getPassword());
/*     */ 
/* 428 */       this.sentencia = this.con.createStatement();
/* 429 */       this.sentencia.executeUpdate("ALTER TABLE Producto MODIFY COLUMN imagen BLOB;");
/* 430 */       this.sentencia.close();
/* 431 */       this.con.close();
/*     */     }
/*     */     catch (SQLException e) {
/* 434 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.Instalar
 * JD-Core Version:    0.6.2
 */