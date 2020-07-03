/*     */ package almacen2.data;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.Puente;
/*     */ import facturacion.model.Producto;
/*     */ import java.sql.Connection;
/*     */ import java.sql.DriverManager;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ 
/*     */ public class ManejadorOldAlmacen
/*     */ {
/*     */   private ResultSet res;
/*     */   private Connection con;
/*     */   private Statement sentencia;
/*     */ 
/*     */   public ManejadorOldAlmacen()
/*     */   {
/*     */     try
/*     */     {
/*  28 */       this.con = DriverManager.getConnection("jdbc:mysql://" + Inicio.p.getDireccionIP() + "/almacen", Inicio.p.getUsuario(), Inicio.p.getPassword());
/*     */ 
/*  31 */       this.sentencia = this.con.createStatement();
/*     */     }
/*     */     catch (SQLException exc) {
/*     */     }
/*     */   }
/*     */ 
/*     */   public ProductObject getProducto(String referencia) {
/*  38 */     ProductObject producto = null;
/*     */     try {
/*  40 */       this.res = this.sentencia.executeQuery("SELECT Descripcion,Proveedor,coste,pvp from productos WHERE referencia = '" + referencia + "'");
/*     */ 
/*  42 */       if (this.res.next()) {
/*  43 */         int id = Integer.parseInt(referencia);
/*  44 */         String descripcion = this.res.getString(1);
/*  45 */         int proveedor = this.res.getInt(2);
/*  46 */         double coste = this.res.getDouble(3);
/*  47 */         double importe = this.res.getDouble(4);
/*  48 */         int familia = getFamilia(id);
/*  49 */         int idIva = 1;
/*  50 */         producto = new ProductObject(id, referencia, descripcion, proveedor, familia, coste, importe, "", 0, 0, idIva);
/*     */       }
/*     */       else {
/*  53 */         this.res = this.sentencia.executeQuery("SELECT Descripcion,Proveedor,coste,pvp,Referencia from productos WHERE RefProv = '" + referencia + "' AND f_venta = '1960-01-01'");
/*     */ 
/*  55 */         if (this.res.next()) {
/*  56 */           int id = this.res.getInt(5);
/*  57 */           String descripcion = this.res.getString(1);
/*  58 */           int proveedor = this.res.getInt(2);
/*  59 */           double coste = this.res.getDouble(3);
/*  60 */           double importe = this.res.getDouble(4);
/*  61 */           int familia = getFamilia(id);
/*  62 */           int idIva = 1;
/*  63 */           producto = new ProductObject(id, Integer.toString(id), descripcion, proveedor, familia, coste, importe, "", 0, 0, idIva);
/*     */         }
/*     */       }
/*     */     } catch (SQLException ex) {
/*  67 */       Logger.getLogger(ManejadorOldAlmacen.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/*  69 */     return producto;
/*     */   }
/*     */ 
/*     */   public Producto getProducto2(String referencia) {
/*  73 */     Producto producto = null;
/*  74 */     ProductObject pr = getProducto(referencia);
/*  75 */     if (pr != null) {
/*  76 */       producto = new Producto(Integer.valueOf(pr.getId()), pr.getReferencia(), pr.getDescripcion(), null, Double.valueOf(pr.getPvp()));
/*     */     }
/*  78 */     return producto;
/*     */   }
/*     */ 
/*     */   private int getFamilia(int referencia) {
/*  82 */     int familia = 1;
/*  83 */     if (referencia < 20000000)
/*  84 */       familia = 1;
/*  85 */     else if (((referencia >= 22000000) && (referencia < 23000000)) || ((referencia >= 79000000) && (referencia < 94000000)))
/*     */     {
/*  87 */       familia = 22;
/*     */     }
/*  89 */     else familia = 3;
/*     */ 
/*  91 */     return familia;
/*     */   }
/*     */ 
/*     */   public void bajaAlmacen(String referencia, String fecha, double importe) {
/*     */     try {
/*  96 */       this.sentencia.executeUpdate("UPDATE productos SET f_venta='" + fecha + "'," + "pvr=" + importe + " WHERE referencia =" + referencia);
/*     */     }
/*     */     catch (SQLException ex) {
/*  99 */       Logger.getLogger(ManejadorOldAlmacen.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void deshacerBajaAlmacen(String referencia) {
/*     */     try {
/* 105 */       this.sentencia.executeUpdate("UPDATE productos SET f_venta='1960-01-01',pvr = 0 WHERE referencia =" + referencia);
/*     */     } catch (SQLException ex) {
/* 107 */       Logger.getLogger(ManejadorOldAlmacen.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void cerrarConexion() {
/*     */     try {
/* 113 */       if (this.res != null) {
/* 114 */         this.res.close();
/* 115 */         this.res = null;
/*     */       }
/* 117 */       if (this.sentencia != null) {
/* 118 */         this.sentencia.close();
/* 119 */         this.sentencia = null;
/*     */       }
/* 121 */       if (this.con != null) {
/* 122 */         this.con.close();
/* 123 */         this.con = null;
/*     */       }
/*     */     } catch (SQLException ex) {
/* 126 */       Logger.getLogger(ManejadorOldAlmacen.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.data.ManejadorOldAlmacen
 * JD-Core Version:    0.6.2
 */