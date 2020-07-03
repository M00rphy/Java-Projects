/*    */ package almacen2.data.pedidos;
/*    */ 
/*    */ import almacen2.data.MySQLConection;
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
/*    */ import java.sql.Statement;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class Productos
/*    */ {
/*    */   MySQLConection con;
/*    */   ResultSet res;
/*    */ 
/*    */   public Productos(MySQLConection con)
/*    */   {
/* 16 */     this.con = con;
/*    */   }
/*    */ 
/*    */   public int nuevoProducto(int idPedido, String referencia, Double importe, int unidades) {
/* 20 */     int id = -1;
/*    */     try {
/* 22 */       this.con.getSentencia().executeUpdate("INSERT INTO productos_pedidos (id_pedido,referencia,importe,recibido,unidades) VALUES (" + idPedido + ",'" + referencia + "'," + importe + ",0," + unidades + ")");
/*    */ 
/* 24 */       this.res = this.con.getRes("SELECT last_insert_id() FROM productos_pedidos");
/* 25 */       if (this.res.next())
/* 26 */         id = this.res.getInt(1);
/*    */     } catch (SQLException e) {
/* 28 */       e.printStackTrace();
/*    */     }
/* 30 */     return id;
/*    */   }
/*    */ 
/*    */   public void eliminarProducto(int id) {
/*    */     try {
/* 35 */       this.con.getSentencia().executeUpdate("DELETE FROM productos_pedidos WHERE id = " + id);
/*    */     } catch (SQLException e) {
/* 37 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/*    */   public void marcarRecibido(int id, boolean valor) {
/*    */     try {
/* 43 */       this.con.getSentencia().executeUpdate("UPDATE productos_pedidos SET recibido = " + valor + " WHERE id = " + id);
/*    */     }
/*    */     catch (SQLException e)
/*    */     {
/* 47 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/*    */   public ArrayList<productoPedido> listaPedido(int idPedido) {
/* 52 */     ArrayList lista = new ArrayList();
/*    */     try {
/* 54 */       this.res = this.con.getRes("SELECT * FROM productos_pedidos WHERE id_pedido = " + idPedido);
/* 55 */       while (this.res.next())
/* 56 */         lista.add(new productoPedido(this.res.getInt(1), this.res.getString(3), Double.valueOf(this.res.getDouble(4)), this.res.getBoolean(5), this.res.getInt(6)));
/*    */     }
/*    */     catch (SQLException e) {
/* 59 */       e.printStackTrace();
/*    */     }
/* 61 */     return lista;
/*    */   }
/*    */ 
/*    */   public datosProducto datosReferencia(String referencia, boolean compras) {
/* 65 */     datosProducto producto = null;
/* 66 */     String campoImporte = compras ? "coste" : "pvp";
/*    */     try {
/* 68 */       this.res = this.con.getRes("SELECT descripcion," + campoImporte + " FROM Producto WHERE referencia = '" + referencia + "'");
/* 69 */       if (this.res.next())
/* 70 */         producto = new datosProducto(this.res.getString(1), this.res.getDouble(2));
/*    */     } catch (SQLException e) {
/* 72 */       e.printStackTrace();
/*    */     }
/* 74 */     return producto;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.data.pedidos.Productos
 * JD-Core Version:    0.6.2
 */