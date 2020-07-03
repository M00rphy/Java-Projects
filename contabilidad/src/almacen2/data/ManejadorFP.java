/*     */ package almacen2.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ 
/*     */ public class ManejadorFP
/*     */ {
/*  10 */   private MySQLConection conexion = null;
/*     */   private ResultSet res;
/*     */ 
/*     */   public ManejadorFP(MySQLConection conexion)
/*     */   {
/*  15 */     this.conexion = conexion;
/*     */   }
/*     */ 
/*     */   public boolean existeId(int id, boolean conFamilias) {
/*     */     try {
/*  20 */       this.res = this.conexion.getRes("SELECT * FROM " + tabla(conFamilias) + " WHERE id = " + id);
/*  21 */       if (this.res.next())
/*  22 */         return true;
/*     */     }
/*     */     catch (SQLException e) {
/*  25 */       e.printStackTrace();
/*  26 */       return false;
/*     */     }
/*  28 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean crear(FPObject fp, boolean conFamilias) {
/*  32 */     String sql = "INSERT INTO " + tabla(conFamilias) + " (id,nombre,subventas,subcompras) VALUES (" + fp.getId() + ",'" + fp.getNombre() + "'," + fp.getSubcuenta1() + "," + fp.getSubcuenta2() + ")";
/*     */ 
/*  36 */     if (!conFamilias) {
/*  37 */       sql = "INSERT INTO " + tabla(conFamilias) + " (id,nombre,subcuenta) VALUES (" + fp.getId() + ",'" + fp.getNombre() + "'," + fp.getSubcuenta1() + ")";
/*     */     }
/*     */ 
/*     */     try
/*     */     {
/*  43 */       this.conexion.getSentencia().executeUpdate(sql);
/*     */     } catch (SQLException e) {
/*  45 */       e.printStackTrace();
/*  46 */       return false;
/*     */     }
/*  48 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean modificar(FPObject fp, boolean conFamilias) {
/*  52 */     String sql = "UPDATE " + tabla(conFamilias) + " SET nombre ='" + fp.getNombre() + "'," + " subventas = " + fp.getSubcuenta1() + "," + " subcompras = " + fp.getSubcuenta2() + " WHERE id = " + fp.getId();
/*     */ 
/*  57 */     if (!conFamilias) {
/*  58 */       sql = "UPDATE " + tabla(conFamilias) + " SET nombre ='" + fp.getNombre() + "'," + " subcuenta = " + fp.getSubcuenta1() + " WHERE id = " + fp.getId();
/*     */     }
/*     */ 
/*     */     try
/*     */     {
/*  64 */       this.conexion.getSentencia().executeUpdate(sql);
/*     */     } catch (SQLException e) {
/*  66 */       e.printStackTrace();
/*  67 */       return false;
/*     */     }
/*  69 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean eliminar(FPObject fp, boolean conFamilias) {
/*     */     try {
/*  74 */       this.conexion.getSentencia().executeUpdate("DELETE FROM " + tabla(conFamilias) + " WHERE id = " + fp.getId());
/*     */     }
/*     */     catch (SQLException e) {
/*  77 */       e.printStackTrace();
/*  78 */       return false;
/*     */     }
/*  80 */     return true;
/*     */   }
/*     */ 
/*     */   public int nuevoRegistro(boolean conFamilias) {
/*  84 */     int nuevoRegistro = 0;
/*     */     try {
/*  86 */       this.res = this.conexion.getRes("SELECT MAX(id) FROM " + tabla(conFamilias));
/*  87 */       if (this.res.next())
/*  88 */         nuevoRegistro = this.res.getInt(1) + 1;
/*     */     }
/*     */     catch (SQLException e) {
/*  91 */       e.printStackTrace();
/*     */     }
/*  93 */     return nuevoRegistro;
/*     */   }
/*     */ 
/*     */   public FPObject getObjeto(boolean conFamilias, int id) {
/*  97 */     FPObject objeto = null;
/*     */     try {
/*  99 */       this.res = this.conexion.getRes("SELECT * FROM " + tabla(conFamilias) + " WHERE id = " + id);
/*     */ 
/* 101 */       if (this.res.next()) {
/* 102 */         String nombre = this.res.getString(2);
/* 103 */         int subcuenta1 = this.res.getInt(3);
/* 104 */         int subcuenta2 = -1;
/* 105 */         if (conFamilias) {
/* 106 */           subcuenta2 = this.res.getInt(4);
/*     */         }
/* 108 */         objeto = new FPObject(id, nombre, subcuenta1, subcuenta2);
/*     */       }
/*     */     } catch (SQLException ex) {
/* 111 */       Logger.getLogger(ManejadorFP.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/* 113 */     return objeto;
/*     */   }
/*     */ 
/*     */   private String tabla(boolean conFamilias) {
/* 117 */     return conFamilias ? "familias" : "proveedores";
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.data.ManejadorFP
 * JD-Core Version:    0.6.2
 */