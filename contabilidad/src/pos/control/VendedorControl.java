/*     */ package pos.control;
/*     */ 
/*     */ import almacen2.data.MySQLConection;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import pos.model.Vendedor;
/*     */ 
/*     */ public class VendedorControl
/*     */ {
/*     */   private MySQLConection con;
/*     */   private ResultSet res;
/*     */ 
/*     */   public VendedorControl(MySQLConection con)
/*     */   {
/*  25 */     this.con = con;
/*     */   }
/*     */ 
/*     */   public int crear(Vendedor vendedor) {
/*  29 */     int id = -1;
/*     */     try {
/*  31 */       String cadenaSQL = "INSERT INTO vendedores (nombre) VALUES(?)";
/*  32 */       PreparedStatement ps = this.con.getCon().prepareStatement(cadenaSQL);
/*  33 */       ps.setString(1, vendedor.getNombre());
/*     */ 
/*  35 */       ps.execute();
/*  36 */       cadenaSQL = "SELECT LAST_INSERT_ID() FROM vendedores";
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
/*     */   public boolean modificar(Vendedor vendedor) {
/*  49 */     boolean modificado = false;
/*     */     try {
/*  51 */       String cadenaSQL = "UPDATE vendedores SET nombre = ? WHERE id = ?";
/*  52 */       PreparedStatement ps = this.con.getCon().prepareStatement(cadenaSQL);
/*  53 */       ps.setString(1, vendedor.getNombre());
/*  54 */       ps.setInt(2, vendedor.getId().intValue());
/*  55 */       int result = ps.executeUpdate();
/*  56 */       if (result > 0)
/*  57 */         modificado = true;
/*     */     }
/*     */     catch (SQLException exc) {
/*  60 */       exc.printStackTrace();
/*     */     }
/*  62 */     return modificado;
/*     */   }
/*     */ 
/*     */   public boolean borrar(Vendedor vendedor) {
/*  66 */     boolean borrado = false;
/*     */     try {
/*  68 */       String cadenaSQL = "DELETE FROM vendedores WHERE id = ?";
/*  69 */       PreparedStatement ps = this.con.getCon().prepareStatement(cadenaSQL);
/*  70 */       ps.setInt(1, vendedor.getId().intValue());
/*  71 */       int result = ps.executeUpdate();
/*  72 */       if (result > 0)
/*  73 */         borrado = true;
/*     */     }
/*     */     catch (SQLException exc) {
/*  76 */       exc.printStackTrace();
/*     */     }
/*  78 */     return borrado;
/*     */   }
/*     */ 
/*     */   public Vendedor getVendedor(int id) {
/*  82 */     Vendedor vendedor = null;
/*     */     try {
/*  84 */       String cadenaSQL = "SELECT * FROM vendedores WHERE id = " + id;
/*  85 */       this.res = this.con.getRes(cadenaSQL);
/*  86 */       if (this.res.next()) {
/*  87 */         String nombre = this.res.getString(2);
/*  88 */         vendedor = new Vendedor(Integer.valueOf(id), nombre);
/*     */       }
/*     */     }
/*     */     catch (SQLException exc) {
/*  92 */       exc.printStackTrace();
/*     */     }
/*  94 */     return vendedor;
/*     */   }
/*     */ 
/*     */   public ArrayList<Vendedor> getTodosVendedores() {
/*  98 */     ArrayList vendedores = new ArrayList();
/*     */     try {
/* 100 */       String cadenaSQL = "SELECT * FROM vendedores ORDER BY nombre";
/* 101 */       Vendedor vendedor = null;
/* 102 */       this.res = this.con.getRes(cadenaSQL);
/* 103 */       while (this.res.next()) {
/* 104 */         Integer id = Integer.valueOf(this.res.getInt(1));
/* 105 */         String nombre = this.res.getString(2);
/* 106 */         vendedor = new Vendedor(id, nombre);
/*     */ 
/* 108 */         vendedores.add(vendedor);
/*     */       }
/*     */     }
/*     */     catch (SQLException exc) {
/* 112 */       exc.printStackTrace();
/*     */     }
/* 114 */     return vendedores;
/*     */   }
/*     */ 
/*     */   public void cerrarRs() {
/* 118 */     if (this.res != null) {
/*     */       try {
/* 120 */         this.res.close(); } catch (SQLException sqlEx) {
/*     */       }
/* 122 */       this.res = null;
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.control.VendedorControl
 * JD-Core Version:    0.6.2
 */