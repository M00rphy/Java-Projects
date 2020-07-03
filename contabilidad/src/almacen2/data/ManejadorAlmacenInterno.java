/*     */ package almacen2.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class ManejadorAlmacenInterno
/*     */ {
/*     */   private MySQLConection conexion;
/*     */   private ResultSet res;
/*     */ 
/*     */   public ManejadorAlmacenInterno(MySQLConection conexion)
/*     */   {
/*  22 */     this.conexion = conexion;
/*     */   }
/*     */ 
/*     */   public boolean existeId(int id) {
/*     */     try {
/*  27 */       this.res = this.conexion.getRes("SELECT * FROM almacenes WHERE id = " + id);
/*  28 */       if (this.res.next())
/*  29 */         return true;
/*     */     } catch (SQLException e) {
/*  31 */       e.printStackTrace();
/*  32 */       return false;
/*     */     }
/*  34 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean crear(AlmacenInterno almacen) {
/*  38 */     String sql = "INSERT INTO almacenes (id,nombre) VALUES (" + almacen.getId() + ",'" + almacen.getNombre() + "')";
/*     */     try
/*     */     {
/*  42 */       this.conexion.getSentencia().executeUpdate(sql);
/*     */     } catch (SQLException e) {
/*  44 */       e.printStackTrace();
/*  45 */       return false;
/*     */     }
/*  47 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean modificar(AlmacenInterno almacen) {
/*  51 */     String sql = "UPDATE almacenes SET nombre ='" + almacen.getNombre() + "'" + " WHERE id = " + almacen.getId();
/*     */     try
/*     */     {
/*  55 */       this.conexion.getSentencia().executeUpdate(sql);
/*     */     } catch (SQLException e) {
/*  57 */       e.printStackTrace();
/*  58 */       return false;
/*     */     }
/*  60 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean eliminar(AlmacenInterno almacen) {
/*  64 */     boolean exito = false;
/*     */     try {
/*  66 */       this.res = this.conexion.getRes("SELECT * FROM pio WHERE almacen = " + almacen.getId());
/*  67 */       if (!this.res.next()) {
/*  68 */         this.conexion.getSentencia().executeUpdate("DELETE FROM almacenes WHERE id = " + almacen.getId());
/*     */ 
/*  70 */         exito = true;
/*     */       }
/*     */     } catch (SQLException e) {
/*  73 */       e.printStackTrace();
/*     */     }
/*  75 */     return exito;
/*     */   }
/*     */ 
/*     */   public int nuevoRegistro() {
/*  79 */     int nuevoRegistro = 0;
/*     */     try {
/*  81 */       this.res = this.conexion.getRes("SELECT MAX(id) FROM almacenes");
/*  82 */       if (this.res.next())
/*  83 */         nuevoRegistro = this.res.getInt(1) + 1;
/*     */     } catch (SQLException e) {
/*  85 */       e.printStackTrace();
/*     */     }
/*  87 */     return nuevoRegistro;
/*     */   }
/*     */ 
/*     */   public AlmacenInterno getObjeto(int id) {
/*  91 */     AlmacenInterno objeto = null;
/*     */     try {
/*  93 */       this.res = this.conexion.getRes("SELECT * FROM almacenes WHERE id = " + id);
/*     */ 
/*  95 */       if (this.res.next()) {
/*  96 */         String nombre = this.res.getString(2);
/*     */ 
/*  98 */         objeto = new AlmacenInterno(Integer.valueOf(id), nombre);
/*     */       }
/*     */     } catch (SQLException ex) {
/* 101 */       ex.printStackTrace();
/*     */     }
/* 103 */     return objeto;
/*     */   }
/*     */ 
/*     */   public ArrayList<AlmacenInterno> getAlmacenes() {
/* 107 */     ArrayList almacenes = new ArrayList();
/* 108 */     AlmacenInterno objeto = null;
/*     */     try {
/* 110 */       this.res = this.conexion.getRes("SELECT * FROM almacenes ORDER BY id");
/* 111 */       while (this.res.next()) {
/* 112 */         objeto = null;
/* 113 */         int id = this.res.getInt(1);
/* 114 */         String nombre = this.res.getString(2);
/* 115 */         objeto = new AlmacenInterno(Integer.valueOf(id), nombre);
/* 116 */         if (objeto != null)
/* 117 */           almacenes.add(objeto);
/*     */       }
/*     */     }
/*     */     catch (SQLException ex) {
/* 121 */       ex.printStackTrace();
/*     */     }
/* 123 */     return almacenes;
/*     */   }
/*     */ 
/*     */   public void cerrarRs() {
/* 127 */     if (this.res != null)
/*     */       try {
/* 129 */         this.res.close();
/*     */       } catch (SQLException ex) {
/* 131 */         ex.printStackTrace();
/*     */       }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.data.ManejadorAlmacenInterno
 * JD-Core Version:    0.6.2
 */