/*     */ package contaes.manejoDatos;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.Puente;
/*     */ import contaes.manejoDatos.auxiliar.MySQLConection;
/*     */ import java.io.PrintStream;
/*     */ import java.sql.Connection;
/*     */ import java.sql.DriverManager;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import java.util.LinkedList;
import java.util.List;
/*     */ 
/*     */ public class ManejoEmpresas
/*     */ {
/*     */   private MySQLConection conEmpresas;
/*     */   private MySQLConection conEjercicio;
/*     */   private ResultSet res;
/*     */   private int empresa;
/*     */ 
/*     */   public ManejoEmpresas(MySQLConection conEmpresas, int empresa)
/*     */   {
/*  25 */     this.conEmpresas = conEmpresas;
/*  26 */     this.empresa = empresa;
/*     */   }
/*     */ 
/*     */   public void crearEmpresa(String[] datos) {
/*  30 */     int idEmpresa = -1;
/*  31 */     String nombreBD = null;
/*     */     try
/*     */     {
/*  35 */       this.conEmpresas.getSentencia().executeUpdate("INSERT INTO Empresas(nombre,NIF,direccion,localidad,provincia,codpostal,telefono,fax,prefijo) VALUES('" + datos[0] + "','" + datos[1] + "','" + datos[2] + "','" + datos[3] + "','" + datos[4] + "','" + datos[5] + "','" + datos[6] + "','" + datos[7] + "','" + datos[8] + "')");
/*     */ 
/*  47 */       this.res = this.conEmpresas.getRes("SELECT LAST_INSERT_ID() FROM Empresas");
/*  48 */       if (this.res.next()) {
/*  49 */         idEmpresa = this.res.getInt(1);
/*  50 */         nombreBD = "contaes" + idEmpresa;
/*     */       }
/*  52 */       this.res.close();
/*     */     } catch (SQLException exc) {
/*  54 */       exc.printStackTrace();
/*     */     }
/*     */ 
/*  57 */     if (idEmpresa != -1)
/*     */       try {
/*  59 */         Connection con = DriverManager.getConnection("jdbc:mysql://" + Inicio.p.getDireccionIP() + "/", Inicio.p.getUsuario(), Inicio.p.getPassword());
/*     */ 
/*  62 */         Statement sentencia = con.createStatement();
/*  63 */         sentencia.executeUpdate("CREATE DATABASE " + nombreBD);
/*  64 */         con = DriverManager.getConnection("jdbc:mysql://" + Inicio.p.getDireccionIP() + "/" + nombreBD, Inicio.p.getUsuario(), Inicio.p.getPassword());
/*     */ 
/*  67 */         sentencia = con.createStatement();
/*  68 */         sentencia.executeUpdate("CREATE TABLE Terceros (codigo INTEGER UNSIGNED NOT NULL,NIF VARCHAR(9) NOT NULL DEFAULT '',direccion VARCHAR(60),CP VARCHAR(8),localidad VARCHAR(20),provincia VARCHAR(20),telefono VARCHAR(14),fax VARCHAR(14),email VARCHAR(30),letras VARCHAR(20) NOT NULL DEFAULT 'NO',idFormaPago INTEGER UNSIGNED,cuentaPago INTEGER UNSIGNED,pais INTEGER NOT NULL DEFAULT 1,PRIMARY KEY(codigo)) ENGINE=MyISAM");
/*     */ 
/*  83 */         sentencia.executeUpdate("CREATE TABLE vencimientos (id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,fecha DATE NOT NULL DEFAULT '2000-01-01',ejercicio INTEGER NOT NULL DEFAULT '2000',factura VARCHAR(20) NOT NULL DEFAULT '',fechaf DATE NOT NULL DEFAULT '2000-01-01',cuenta INTEGER NOT NULL DEFAULT '40000000',importe DOUBLE NOT NULL DEFAULT '0',num VARCHAR(5) NOT NULL DEFAULT '',pagado BOOLEAN NOT NULL DEFAULT '0',cuentap INTEGER NOT NULL DEFAULT '57000000',PRIMARY KEY(id),INDEX vtos_FK1(factura),INDEX vtos_fecha(fecha),INDEX vtos_importe(importe)) ENGINE=MyISAM");
/*     */ 
/*  98 */         sentencia.executeUpdate("CREATE TABLE vencimientosc (id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,fecha DATE NOT NULL DEFAULT '2000-01-01',ejercicio INTEGER NOT NULL DEFAULT '2000',factura VARCHAR(20) NOT NULL DEFAULT '',fechaf DATE NOT NULL DEFAULT '2000-01-01',cuenta INTEGER NOT NULL DEFAULT '43000000',importe DOUBLE NOT NULL DEFAULT '0',num VARCHAR(5) NOT NULL DEFAULT '',pagado BOOLEAN NOT NULL DEFAULT '0',cuentap INTEGER NOT NULL DEFAULT '57000000',PRIMARY KEY(id),INDEX vtos_FK1(factura),INDEX vtos_fecha(fecha),INDEX vtos_importe(importe)) ENGINE=MyISAM");
/*     */ 
/* 113 */         sentencia.executeUpdate("CREATE TABLE productos (  id INT UNSIGNED NOT NULL AUTO_INCREMENT,  descripcion VARCHAR(255) NOT NULL,  subcuenta INT UNSIGNED NOT NULL,  precio DOUBLE NOT NULL,  PRIMARY KEY (id))ENGINE = MyISAM");
/*     */ 
/* 120 */         sentencia.executeUpdate("CREATE TABLE facturas (  id INT UNSIGNED NOT NULL AUTO_INCREMENT,  numero VARCHAR(255) NOT NULL,  cliente INT UNSIGNED NOT NULL,  fecha DATE NOT NULL,  retencion DOUBLE,  recargo BOOLEAN NOT NULL,  formapago INT,  base DOUBLE NOT NULL,  iva DOUBLE NOT NULL,  contabilizada BOOLEAN NOT NULL DEFAULT '0',  isalmacenada BOOLEAN NOT NULL DEFAULT '0',  PRIMARY KEY (id),  INDEX cliente(cliente),  INDEX formapago(formapago))ENGINE = MyISAM");
/*     */ 
/* 136 */         sentencia.executeUpdate("CREATE TABLE lineafactura (  id INT UNSIGNED NOT NULL AUTO_INCREMENT,  idFactura INT UNSIGNED NOT NULL,  idProducto INT UNSIGNED NOT NULL,  concepto VARCHAR(255),  base DOUBLE NOT NULL,  tipoiva INT UNSIGNED NOT NULL,  total DOUBLE NOT NULL,  unidades DOUBLE NOT NULL,  PRIMARY KEY (id),  INDEX factura(idFactura),  INDEX producto(idProducto))ENGINE = MyISAM");
/*     */ 
/* 149 */         sentencia.executeUpdate("CREATE TABLE facturascompras (  id INT UNSIGNED NOT NULL AUTO_INCREMENT,  numero VARCHAR(255) NOT NULL,  cliente INT UNSIGNED NOT NULL,  fecha DATE NOT NULL,  retencion DOUBLE,  recargo BOOLEAN NOT NULL,  formapago INT,  base DOUBLE NOT NULL,  iva DOUBLE NOT NULL,  contabilizada BOOLEAN NOT NULL DEFAULT '0',  isalmacenada BOOLEAN NOT NULL DEFAULT '0',  PRIMARY KEY (id),  INDEX cliente(cliente),  INDEX formapago(formapago))ENGINE = MyISAM");
/*     */ 
/* 165 */         sentencia.executeUpdate("CREATE TABLE lineafacturacompras (  id INT UNSIGNED NOT NULL AUTO_INCREMENT,  idFactura INT UNSIGNED NOT NULL,  idProducto INT UNSIGNED NOT NULL,  concepto VARCHAR(255),  base DOUBLE NOT NULL,  tipoiva INT UNSIGNED NOT NULL,  total DOUBLE NOT NULL,  unidades DOUBLE NOT NULL,  PRIMARY KEY (id),  INDEX factura(idFactura),  INDEX producto(idProducto))ENGINE = MyISAM");
/*     */ 
/* 178 */         sentencia.close();
/* 179 */         con.close();
/*     */       } catch (SQLException exc) {
/* 181 */         exc.printStackTrace();
/*     */       }
/*     */   }
/*     */ 
/*     */   public void modificarEmpresa(String[] datos)
/*     */   {
/*     */     try {
/* 188 */       this.conEmpresas.getSentencia().executeUpdate("UPDATE Empresas SET NIF ='" + datos[1] + "'," + "direccion ='" + datos[2] + "'," + "localidad ='" + datos[3] + "'," + "provincia ='" + datos[4] + "'," + "codpostal ='" + datos[5] + "'," + "telefono ='" + datos[6] + "'," + "fax ='" + datos[7] + "'," + "prefijo = '" + datos[8] + "' WHERE nombre='" + datos[0] + "'");
/*     */     }
/*     */     catch (SQLException e)
/*     */     {
/* 199 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean borrarEmpresa(String nombre) {
/* 204 */     int idEmpresa = -1;
/* 205 */     String nombreBD = null;
/*     */     try {
/* 207 */       this.res = this.conEmpresas.getRes("SELECT id FROM Empresas WHERE nombre='" + nombre + "'");
/* 208 */       if (this.res.next()) {
/* 209 */         idEmpresa = this.res.getInt(1);
/*     */       }
/* 211 */       this.res.close();
/* 212 */       if (idEmpresa != this.empresa) {
/* 213 */         nombreBD = "contaes" + idEmpresa;
/* 214 */         if (idEmpresa != -1) {
/* 215 */           this.conEmpresas.getSentencia().executeUpdate("DELETE FROM Empresas WHERE id = " + idEmpresa);
/* 216 */           Connection con = DriverManager.getConnection("jdbc:mysql://" + Inicio.p.getDireccionIP() + "/", Inicio.p.getUsuario(), Inicio.p.getPassword());
/*     */ 
/* 219 */           Statement sentencia = con.createStatement();
/* 220 */           sentencia.executeUpdate("DROP DATABASE " + nombreBD);
/*     */         }
/*     */       } else {
/* 223 */         return false;
/*     */       }
/*     */     } catch (SQLException exc) {
/* 226 */       exc.printStackTrace();
/* 227 */       return false;
/*     */     }
/* 229 */     return true;
/*     */   }
/*     */ 
/*     */   public String[] datosEmpresa(String nombre) {
/* 233 */     String[] datos = null;
/*     */     try {
/* 235 */       this.res = this.conEmpresas.getRes("SELECT NIF,direccion,localidad,provincia,codpostal,telefono,fax,prefijo FROM Empresas WHERE nombre='" + nombre + "'");
/*     */ 
/* 237 */       if (this.res.next()) {
/* 238 */         datos = new String[8];
/* 239 */         datos[0] = this.res.getString(1);
/* 240 */         datos[1] = this.res.getString(2);
/* 241 */         datos[2] = this.res.getString(3);
/* 242 */         datos[3] = this.res.getString(4);
/* 243 */         datos[4] = this.res.getString(5);
/* 244 */         datos[5] = this.res.getString(6);
/* 245 */         datos[6] = this.res.getString(7);
/* 246 */         datos[7] = this.res.getString(8);
/*     */       }
/* 248 */       this.res.close();
/*     */     } catch (SQLException e) {
/* 250 */       System.out.println(e.getMessage());
/*     */     }
/* 252 */     return datos;
/*     */   }
/*     */ 
/*     */   public LinkedList<String> listaEmpresas() {
/* 256 */     LinkedList lista = new LinkedList();
/*     */     try {
/* 258 */       this.res = this.conEmpresas.getRes("SELECT nombre FROM Empresas");
/* 259 */       while (this.res.next()) {
/* 260 */         lista.add(this.res.getString(1));
/*     */       }
/* 262 */       this.res.close();
/*     */     } catch (SQLException exc) {
/* 264 */       exc.printStackTrace();
/*     */     }
/* 266 */     return lista;
/*     */   }
/*     */ 
/*     */   public LinkedList<Integer> listaIdEmpresas() {
/* 270 */     LinkedList lista = new LinkedList();
/*     */     try {
/* 272 */       this.res = this.conEmpresas.getRes("SELECT id FROM Empresas");
/* 273 */       while (this.res.next()) {
/* 274 */         lista.add(Integer.valueOf(this.res.getInt(1)));
/*     */       }
/* 276 */       this.res.close();
/*     */     } catch (SQLException exc) {
/* 278 */       exc.printStackTrace();
/*     */     }
/* 280 */     return lista;
/*     */   }
/*     */ 
/*     */   public int getIdEmpresa(String nombre)
/*     */   {
/* 305 */     int id = -1;
/*     */     try {
/* 307 */       this.res = this.conEmpresas.getRes("SELECT id FROM Empresas WHERE nombre='" + nombre + "'");
/*     */ 
/* 309 */       if (this.res.next()) {
/* 310 */         id = this.res.getInt(1);
/*     */       }
/* 312 */       this.res.close();
/*     */     } catch (SQLException exc) {
/* 314 */       exc.printStackTrace();
/*     */     }
/* 316 */     return id;
/*     */   }
/*     */ 
/*     */   public String getNombre(int idEmpresa) {
/*     */     try {
/* 321 */       this.res = this.conEmpresas.getRes("SELECT nombre FROM Empresas WHERE id = " + idEmpresa);
/*     */ 
/* 323 */       if (this.res.next()) {
/* 324 */         return this.res.getString(1);
/*     */       }
/* 326 */       this.res.close();
/*     */     } catch (SQLException exc) {
/* 328 */       exc.printStackTrace();
/*     */     }
/* 330 */     return "";
/*     */   }
/*     */ 
/*     */   public String getNif(int idEmpresa) {
/*     */     try {
/* 335 */       this.res = this.conEmpresas.getRes("SELECT NIF FROM Empresas WHERE id = " + idEmpresa);
/*     */ 
/* 337 */       if (this.res.next()) {
/* 338 */         return this.res.getString(1);
/*     */       }
/* 340 */       this.res.close();
/*     */     } catch (SQLException exc) {
/* 342 */       exc.printStackTrace();
/*     */     }
/* 344 */     return "";
/*     */   }
/*     */ 
/*     */   public String getPrejifo(int id) {
/* 348 */     String prefijo = "";
/*     */     try {
/* 350 */       this.res = this.conEmpresas.getRes("SELECT prefijo FROM Empresas WHERE id=" + id);
/*     */ 
/* 352 */       if (this.res.next()) {
/* 353 */         prefijo = this.res.getString(1);
/*     */       }
/* 355 */       this.res.close();
/*     */     } catch (SQLException exc) {
/* 357 */       exc.printStackTrace();
/*     */     }
/* 359 */     return prefijo;
/*     */   }
/*     */ 
/*     */   public LinkedList<String> listaEjercicios(int empresa) {
/* 363 */     LinkedList lista = new LinkedList();
/* 364 */     if (this.conEjercicio != null) {
/* 365 */       this.conEjercicio.cierraConexion();
/*     */     }
/* 367 */     this.conEjercicio = new MySQLConection(empresa);
/*     */     try {
/* 369 */       this.res = this.conEjercicio.getRes("SHOW TABLES LIKE 'asto%'");
/* 370 */       while (this.res.next()) {
/* 371 */         lista.add(this.res.getString(1).substring(4));
/*     */       }
/* 373 */       this.res.close();
/*     */     } catch (SQLException e) {
/* 375 */       System.out.println(e.getMessage());
/*     */     } finally {
/* 377 */       this.conEjercicio.cierraConexion();
/*     */     }
/* 379 */     return lista;
/*     */   }
/*     */ 
/*     */   public String crearEjercicio1(int empresa, int ejercicio)
/*     */   {
/* 411 */     if (((ejercicio >= 1990 ? 1 : 0) | (ejercicio <= 2100 ? 1 : 0)) != 0) {
/* 412 */       boolean existe = false;
/* 413 */       boolean primero = true;
/* 414 */       LinkedList listaEjercicios = new LinkedList();
/* 415 */       listaEjercicios.addAll(listaEjercicios(empresa));
/* 416 */       if (listaEjercicios.size() > 0) {
/* 417 */         for (String x :(List<String>) listaEjercicios) {
/* 418 */           if (Integer.parseInt(x) == ejercicio - 1) {
/* 419 */             primero = false;
/*     */           }
/* 421 */           if (Integer.parseInt(x) == ejercicio) {
/* 422 */             existe = true;
/*     */           }
/*     */         }
/*     */       }
/* 426 */       if (existe) {
/* 427 */         return "existe";
/*     */       }
/* 429 */       if (primero) {
/* 430 */         return "primero";
/*     */       }
/* 432 */       return "NoPrimero";
/*     */     }
/* 434 */     return "RangoNoValido";
/*     */   }
/*     */ 
/*     */   public void crearEjercicio2(int empresa, String ejercicio, boolean copiarCuentas)
/*     */   {
/* 439 */     if (this.conEjercicio != null) {
/* 440 */       this.conEjercicio.cierraConexion();
/*     */     }
/* 442 */     this.conEjercicio = new MySQLConection(empresa);
/*     */     try {
/* 444 */       this.conEjercicio.getSentencia().executeUpdate("CREATE TABLE apte" + ejercicio + " (" + "id_apunte INTEGER UNSIGNED NOT NULL AUTO_INCREMENT," + "id_asiento INTEGER UNSIGNED NOT NULL," + "cuenta INTEGER UNSIGNED NOT NULL DEFAULT '0'," + "concepto VARCHAR(30) NOT NULL DEFAULT ''," + "DH CHAR(1) NOT NULL DEFAULT 'D'," + "importe DOUBLE NOT NULL DEFAULT '0'," + "PRIMARY KEY(id_apunte)," + "INDEX apte_FK1(id_asiento)," + "INDEX apte_importe_FK3(Importe)," + "INDEX apte_FK2(cuenta)) ENGINE=MyISAM");
/*     */ 
/* 455 */       this.conEjercicio.getSentencia().executeUpdate("CREATE TABLE asto" + ejercicio + " (" + "id_asiento INTEGER UNSIGNED NOT NULL AUTO_INCREMENT," + "numero INTEGER UNSIGNED NOT NULL," + "fecha DATE NOT NULL," + "documento VARCHAR(20) NOT NULL DEFAULT ''," + "marca CHAR(1) NOT NULL DEFAULT ''," + "PRIMARY KEY(id_asiento)," + "INDEX Por_numero(numero)," + "INDEX Por_fecha(fecha)) ENGINE=MyISAM");
/*     */ 
/* 464 */       this.conEjercicio.getSentencia().executeUpdate("CREATE TABLE emit" + ejercicio + " (" + "numero VARCHAR(20) NOT NULL," + "fecha DATE NOT NULL," + "cuenta INTEGER UNSIGNED NOT NULL," + "id_asiento INTEGER UNSIGNED NOT NULL," + "concepto VARCHAR(30) NOT NULL DEFAULT ''," + "base DOUBLE NOT NULL DEFAULT '0'," + "iva DOUBLE NOT NULL DEFAULT '0'," + "re DOUBLE NOT NULL DEFAULT '0'," + "total DOUBLE NOT NULL DEFAULT '0'," + "PRIMARY KEY(numero)," + "INDEX emit_fecha(fecha)," + "INDEX emit_FK1(cuenta)," + "INDEX emit_FK2(id_asiento)) ENGINE=MyISAM");
/*     */ 
/* 478 */       this.conEjercicio.getSentencia().executeUpdate("CREATE TABLE reci" + ejercicio + " (" + "numero VARCHAR(20) NOT NULL," + "fecha DATE NOT NULL," + "cuenta INTEGER UNSIGNED NOT NULL," + "id_asiento INTEGER UNSIGNED NOT NULL," + "concepto VARCHAR(30) NOT NULL DEFAULT ''," + "base DOUBLE NOT NULL DEFAULT '0'," + "iva DOUBLE NOT NULL DEFAULT '0'," + "total DOUBLE NOT NULL DEFAULT '0'," + "importacion BOOL NOT NULL DEFAULT '0'," + "PRIMARY KEY(numero)," + "INDEX reci_fecha(fecha)," + "INDEX reci_FK1(cuenta)," + "INDEX reci_FK2(id_asiento)) ENGINE=MyISAM");
/*     */ 
/* 492 */       this.conEjercicio.getSentencia().executeUpdate("CREATE TABLE scta" + ejercicio + " (" + "codigo INTEGER UNSIGNED NOT NULL," + "nombre VARCHAR(255) NOT NULL DEFAULT ''," + "gbalance VARCHAR(9) NOT NULL," + "debe DOUBLE NOT NULL DEFAULT '0'," + "haber DOUBLE NOT NULL DEFAULT '0'," + "saldo DOUBLE NOT NULL DEFAULT '0'," + "debe1 DOUBLE UNSIGNED NOT NULL DEFAULT '0'," + "haber1 DOUBLE UNSIGNED NOT NULL DEFAULT '0'," + "debe2 DOUBLE UNSIGNED NOT NULL DEFAULT '0'," + "haber2 DOUBLE UNSIGNED NOT NULL DEFAULT '0'," + "debe3 DOUBLE UNSIGNED NOT NULL DEFAULT '0'," + "haber3 DOUBLE UNSIGNED NOT NULL DEFAULT '0'," + "debe4 DOUBLE UNSIGNED NOT NULL DEFAULT '0'," + "haber4 DOUBLE UNSIGNED NOT NULL DEFAULT '0'," + "debe5 DOUBLE UNSIGNED NOT NULL DEFAULT '0'," + "haber5 DOUBLE UNSIGNED NOT NULL DEFAULT '0'," + "debe6 DOUBLE UNSIGNED NOT NULL DEFAULT '0'," + "haber6 DOUBLE UNSIGNED NOT NULL DEFAULT '0'," + "debe7 DOUBLE UNSIGNED NOT NULL DEFAULT '0'," + "haber7 DOUBLE UNSIGNED NOT NULL DEFAULT '0'," + "debe8 DOUBLE UNSIGNED NOT NULL DEFAULT '0'," + "haber8 DOUBLE UNSIGNED NOT NULL DEFAULT '0'," + "debe9 DOUBLE UNSIGNED NOT NULL DEFAULT '0'," + "haber9 DOUBLE UNSIGNED NOT NULL DEFAULT '0'," + "debe10 DOUBLE UNSIGNED NOT NULL DEFAULT '0'," + "haber10 DOUBLE UNSIGNED NOT NULL DEFAULT '0'," + "debe11 DOUBLE UNSIGNED NOT NULL DEFAULT '0'," + "haber11 DOUBLE UNSIGNED NOT NULL DEFAULT '0'," + "debe12 DOUBLE UNSIGNED NOT NULL DEFAULT '0'," + "haber12 DOUBLE UNSIGNED NOT NULL DEFAULT '0'," + "PRIMARY KEY(codigo)," + "INDEX scta_saldo(saldo)) ENGINE=MyISAM");
/*     */ 
/* 525 */       if (copiarCuentas) {
/* 526 */         int ejAnt = Integer.parseInt(ejercicio) - 1;
/* 527 */         String ejercicioAnterior = String.valueOf(ejAnt);
/* 528 */         this.conEjercicio.getSentencia().executeUpdate("INSERT INTO scta" + ejercicio + "(codigo,nombre,gbalance) " + "SELECT codigo,nombre,gbalance FROM scta" + ejercicioAnterior);
/*     */       }
/*     */ 
/*     */     }
/*     */     catch (SQLException e)
/*     */     {
/* 534 */       e.printStackTrace();
/*     */     } finally {
/* 536 */       this.conEjercicio.cierraConexion();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void borrarEjercicio(int empresa, String ejercicio, boolean vencimientos) {
/* 541 */     this.conEjercicio.cierraConexion();
/* 542 */     this.conEjercicio = new MySQLConection(empresa);
/*     */     try {
/* 544 */       this.conEjercicio.getSentencia().executeUpdate("DROP TABLE apte" + ejercicio);
/* 545 */       this.conEjercicio.getSentencia().executeUpdate("DROP TABLE asto" + ejercicio);
/* 546 */       this.conEjercicio.getSentencia().executeUpdate("DROP TABLE scta" + ejercicio);
/* 547 */       this.conEjercicio.getSentencia().executeUpdate("DROP TABLE emit" + ejercicio);
/* 548 */       this.conEjercicio.getSentencia().executeUpdate("DROP TABLE reci" + ejercicio);
/* 549 */       if (vencimientos)
/* 550 */         this.conEjercicio.getSentencia().executeUpdate("DELETE FROM vencimientos WHERE ejercicio=" + ejercicio);
/*     */     }
/*     */     catch (SQLException e) {
/* 553 */       e.printStackTrace();
/*     */     } finally {
/* 555 */       this.conEjercicio.cierraConexion();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void cerrarRs() {
/* 560 */     if (this.res != null) {
/*     */       try {
/* 562 */         this.res.close();
/*     */       } catch (SQLException sqlEx) {
/*     */       }
/* 565 */       this.res = null;
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.ManejoEmpresas
 * JD-Core Version:    0.6.2
 */