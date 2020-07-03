/*     */ package almacen2.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import java.util.ArrayList;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ 
/*     */ public class ManejadorProducto
/*     */ {
/*  11 */   private MySQLConection conexion = null;
/*  12 */   private ProductObject producto = null;
/*     */   private ResultSet res;
/*     */ 
/*     */   public ManejadorProducto(MySQLConection conexion)
/*     */   {
/*  17 */     this.conexion = conexion;
/*     */   }
/*     */ 
/*     */   public ProductObject obtenerProducto(int id) {
/*     */     try {
/*  22 */       this.res = this.conexion.getRes("SELECT * FROM Producto WHERE id = " + id);
/*  23 */       if (this.res.next()) {
/*  24 */         this.producto = new ProductObject(this.res.getInt(1), this.res.getString(2), this.res.getString(3), this.res.getInt(4), this.res.getInt(5), this.res.getDouble(6), this.res.getDouble(7), this.res.getString(8), this.res.getInt(9), this.res.getInt(10), this.res.getInt(11));
/*     */       }
/*     */     }
/*     */     catch (SQLException e)
/*     */     {
/*  29 */       e.printStackTrace();
/*     */     }
/*  31 */     return this.producto;
/*     */   }
/*     */ 
/*     */   public ProductObject obtenerProducto(String referencia) {
/*  35 */     return obtenerProducto(obtenerID(referencia));
/*     */   }
/*     */ 
/*     */   public int obtenerID(String referencia) {
/*  39 */     int id = -1;
/*     */     try {
/*  41 */       this.res = this.conexion.getRes("SELECT id FROM Producto WHERE referencia = '" + referencia + "'");
/*  42 */       if (this.res.next())
/*  43 */         id = this.res.getInt(1);
/*     */     }
/*     */     catch (SQLException e) {
/*  46 */       e.printStackTrace();
/*     */     }
/*  48 */     return id;
/*     */   }
/*     */ 
/*     */   public ArrayList<ProductObject> productosProveedor(int proveedor) {
/*  52 */     ArrayList listaProductos = new ArrayList();
/*  53 */     ProductObject product = null;
/*     */     try {
/*  55 */       this.res = this.conexion.getRes("SELECT * FROM Producto WHERE proveedor = " + proveedor);
/*  56 */       while (this.res.next()) {
/*  57 */         product = new ProductObject(this.res.getInt(1), this.res.getString(2), this.res.getString(3), this.res.getInt(4), this.res.getInt(5), this.res.getDouble(6), this.res.getDouble(7), this.res.getString(8), this.res.getInt(9), this.res.getInt(10), this.res.getInt(11));
/*     */ 
/*  60 */         listaProductos.add(product);
/*     */       }
/*     */     }
/*     */     catch (SQLException ex) {
/*  64 */       Logger.getLogger(ManejadorProducto.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/*  66 */     return listaProductos;
/*     */   }
/*     */ 
/*     */   public boolean introducirProducto(ProductObject producto)
/*     */   {
/*     */     try {
/*  72 */       this.res = this.conexion.getRes("SELECT * FROM Producto WHERE referencia = '" + producto.getReferencia() + "'");
/*  73 */       if (this.res.next()) {
/*  74 */         return false;
/*     */       }
/*  76 */       this.conexion.getSentencia().executeUpdate("INSERT INTO Producto (referencia, descripcion, proveedor, familia, coste, pvp, imagen, stockminimo, pedidominimo,idtipoiva) VALUES ('" + producto.getReferencia() + "','" + producto.getDescripcion() + "'," + producto.getProveedor() + "," + producto.getFamilia() + "," + producto.getCoste() + "," + producto.getPvp() + ",'" + producto.getImagen() + "'," + producto.getStockMinimo() + "," + producto.getPedidoMinimo() + "," + producto.getIdtipoiva() + ")");
/*     */     }
/*     */     catch (SQLException e)
/*     */     {
/*  83 */       e.printStackTrace();
/*  84 */       return false;
/*     */     }
/*  86 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean actualizarProducto(ProductObject producto)
/*     */   {
/*  91 */     String clausulaWHERE = " WHERE id = " + producto.getId();
/*     */     try {
/*  93 */       this.conexion.getSentencia().executeUpdate("UPDATE Producto SET referencia = '" + producto.getReferencia() + "'," + "descripcion = '" + producto.getDescripcion() + "'," + "proveedor = " + producto.getProveedor() + "," + "familia = " + producto.getFamilia() + "," + "coste = " + producto.getCoste() + ",pvp = " + producto.getPvp() + "," + "imagen = '" + producto.getImagen() + "'," + "stockminimo = " + producto.getStockMinimo() + "," + "pedidominimo = " + producto.getPedidoMinimo() + "," + "idtipoiva = " + producto.getIdtipoiva() + " " + clausulaWHERE);
/*     */     }
/*     */     catch (SQLException e)
/*     */     {
/* 105 */       e.printStackTrace();
/* 106 */       return false;
/*     */     }
/*     */ 
/* 109 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean eliminarProducto(int id) {
/*     */     try {
/* 114 */       this.conexion.getSentencia().executeUpdate("DELETE FROM Producto WHERE id = " + id);
/*     */     } catch (SQLException e) {
/* 116 */       e.printStackTrace();
/* 117 */       return false;
/*     */     }
/* 119 */     return true;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.data.ManejadorProducto
 * JD-Core Version:    0.6.2
 */