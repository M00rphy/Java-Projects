/*     */ package contaes.manejoDatos;
/*     */ 
/*     */ import contaes.manejoDatos.auxiliar.MySQLConection;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class ManejoPaises
/*     */ {
/*     */   private MySQLConection con;
/*     */   private ResultSet res;
/*     */ 
/*     */   public ManejoPaises(MySQLConection con)
/*     */   {
/*  24 */     this.con = con;
/*     */   }
/*     */ 
/*     */   public int crear(TipoPais pais) {
/*  28 */     int id = -1;
/*     */     try {
/*  30 */       String cadenaSQL = "INSERT INTO paises (nombre,situacion) VALUES(?,?)";
/*  31 */       PreparedStatement ps = this.con.getCon().prepareStatement(cadenaSQL);
/*  32 */       ps.setString(1, pais.getNombre());
/*  33 */       ps.setInt(2, pais.getSituacion());
/*     */ 
/*  35 */       ps.execute();
/*  36 */       cadenaSQL = "SELECT LAST_INSERT_ID() FROM paises";
/*  37 */       ps = this.con.getCon().prepareStatement(cadenaSQL);
/*  38 */       this.res = ps.executeQuery();
/*  39 */       if (this.res.next())
/*  40 */         id = this.res.getInt(1);
/*     */     }
/*     */     catch (SQLException exc) {
/*  43 */       exc.printStackTrace();
/*     */     }
/*  45 */     return id;
/*     */   }
/*     */ 
/*     */   public boolean modificar(TipoPais pais) {
/*  49 */     boolean modificado = false;
/*     */     try {
/*  51 */       String cadenaSQL = "UPDATE paises SET nombre = ?, situacion = ? WHERE id = ?";
/*  52 */       PreparedStatement ps = this.con.getCon().prepareStatement(cadenaSQL);
/*  53 */       ps.setString(1, pais.getNombre());
/*  54 */       ps.setInt(2, pais.getSituacion());
/*  55 */       ps.setInt(3, pais.getId());
/*  56 */       int result = ps.executeUpdate();
/*  57 */       if (result > 0)
/*  58 */         modificado = true;
/*     */     }
/*     */     catch (SQLException exc) {
/*  61 */       exc.printStackTrace();
/*     */     }
/*  63 */     return modificado;
/*     */   }
/*     */ 
/*     */   public boolean borrar(TipoPais pais) {
/*  67 */     boolean borrado = false;
/*     */     try {
/*  69 */       String cadenaSQL = "DELETE FROM paises WHERE id = ?";
/*  70 */       PreparedStatement ps = this.con.getCon().prepareStatement(cadenaSQL);
/*  71 */       ps.setInt(1, pais.getId());
/*  72 */       int result = ps.executeUpdate();
/*  73 */       if (result > 0)
/*  74 */         borrado = true;
/*     */     }
/*     */     catch (SQLException exc) {
/*  77 */       exc.printStackTrace();
/*     */     }
/*  79 */     return borrado;
/*     */   }
/*     */ 
/*     */   public TipoPais getPais(int id) {
/*  83 */     TipoPais pais = null;
/*     */     try {
/*  85 */       String cadenaSQL = "SELECT * FROM paises WHERE id = " + id;
/*  86 */       this.res = this.con.getRes(cadenaSQL);
/*  87 */       if (this.res.next()) {
/*  88 */         String nombre = this.res.getString(2);
/*  89 */         int situacion = this.res.getInt(3);
/*  90 */         pais = new TipoPais(id, nombre, situacion);
/*     */       }
/*     */     }
/*     */     catch (SQLException exc) {
/*  94 */       exc.printStackTrace();
/*     */     }
/*  96 */     return pais;
/*     */   }
/*     */ 
/*     */   public ArrayList<TipoPais> getTodosPaises() {
/* 100 */     ArrayList paises = new ArrayList();
/*     */     try {
/* 102 */       String cadenaSQL = "SELECT * FROM paises ORDER BY nombre";
/* 103 */       TipoPais pais = null;
/* 104 */       this.res = this.con.getRes(cadenaSQL);
/* 105 */       while (this.res.next()) {
/* 106 */         Integer id = Integer.valueOf(this.res.getInt(1));
/* 107 */         String nombre = this.res.getString(2);
/* 108 */         int situacion = this.res.getInt(3);
/* 109 */         pais = new TipoPais(id.intValue(), nombre, situacion);
/* 110 */         paises.add(pais);
/*     */       }
/*     */     }
/*     */     catch (SQLException exc) {
/* 114 */       exc.printStackTrace();
/*     */     }
/* 116 */     return paises;
/*     */   }
/*     */ 
/*     */   public void cerrarRs() {
/* 120 */     if (this.res != null) {
/*     */       try {
/* 122 */         this.res.close(); } catch (SQLException sqlEx) {
/*     */       }
/* 124 */       this.res = null;
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.ManejoPaises
 * JD-Core Version:    0.6.2
 */