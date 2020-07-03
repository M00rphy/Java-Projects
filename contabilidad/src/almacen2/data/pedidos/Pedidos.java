/*    */ package almacen2.data.pedidos;
/*    */ 
/*    */ import almacen2.data.MySQLConection;
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
/*    */ import java.sql.Statement;
/*    */ 
/*    */ public class Pedidos
/*    */ {
/*    */   MySQLConection con;
/*    */   ResultSet res;
/*    */ 
/*    */   public Pedidos(MySQLConection con)
/*    */   {
/* 15 */     this.con = con;
/*    */   }
/*    */ 
/*    */   public int nuevoPedido(String numero, int proveedor, String fecha, boolean compras) {
/* 19 */     int id = -1;
/*    */     try {
/* 21 */       this.con.getSentencia().executeUpdate("INSERT INTO pedidos (numero,proveedor,fecha,compras) VALUES ('" + numero + "'," + proveedor + ",'" + fecha + "'," + compras + ")");
/*    */ 
/* 23 */       this.res = this.con.getRes("SELECT last_insert_id() FROM pedidos");
/* 24 */       if (this.res.next())
/* 25 */         id = this.res.getInt(1);
/*    */     } catch (SQLException e) {
/* 27 */       e.printStackTrace();
/*    */     }
/*    */ 
/* 30 */     return id;
/*    */   }
/*    */ 
/*    */   public void eliminarPedido(int id) {
/*    */     try {
/* 35 */       this.con.getSentencia().executeUpdate("DELETE FROM pedidos WHERE id = " + id);
/* 36 */       this.con.getSentencia().executeUpdate("DELETE FROM productos_pedidos WHERE id_pedido = " + id);
/*    */     } catch (SQLException e) {
/* 38 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/*    */   public void modificarPedido(int id, String numero, int proveedor, String fecha) {
/*    */     try {
/* 44 */       this.con.getSentencia().executeUpdate("UPDATE pedidos SET numero = '" + numero + "'," + "proveedor = " + proveedor + "," + "fecha = '" + fecha + "' WHERE id = " + id);
/*    */     }
/*    */     catch (SQLException e)
/*    */     {
/* 49 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/*    */   public int obtenerID(String numero, int proveedor, String fecha, boolean compras) {
/* 54 */     int id = -1;
/*    */     try {
/* 56 */       String esCompra = compras ? " AND compras" : " AND NOT compras";
/* 57 */       this.res = this.con.getRes("SELECT id FROM pedidos WHERE numero ='" + numero + "' AND " + "proveedor = " + proveedor + " AND fecha = '" + fecha + "'" + esCompra);
/*    */ 
/* 59 */       if (this.res.next())
/* 60 */         id = this.res.getInt(1);
/*    */     } catch (SQLException e) {
/* 62 */       e.printStackTrace();
/*    */     }
/*    */ 
/* 65 */     return id;
/*    */   }
/*    */ 
/*    */   public int obtenerID(String numero, boolean compras) {
/* 69 */     int id = -1;
/*    */     try {
/* 71 */       String esCompra = compras ? " AND compras" : " AND NOT compras";
/* 72 */       this.res = this.con.getRes("SELECT id FROM pedidos WHERE numero ='" + numero + "'" + esCompra);
/* 73 */       if (this.res.next())
/* 74 */         id = this.res.getInt(1);
/*    */     } catch (SQLException e) {
/* 76 */       e.printStackTrace();
/*    */     }
/*    */ 
/* 79 */     return id;
/*    */   }
/*    */ 
/*    */   public PedidoObject obtenerPedido(int id) {
/* 83 */     PedidoObject pedido = null;
/*    */     try {
/* 85 */       this.res = this.con.getRes("SELECT numero,proveedor,fecha FROM pedidos WHERE id = " + id);
/* 86 */       if (this.res.next())
/* 87 */         pedido = new PedidoObject(Integer.valueOf(id), this.res.getString(1), this.res.getString(3), this.res.getInt(2));
/*    */     }
/*    */     catch (SQLException e) {
/* 90 */       e.printStackTrace();
/*    */     }
/* 92 */     return pedido;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.data.pedidos.Pedidos
 * JD-Core Version:    0.6.2
 */