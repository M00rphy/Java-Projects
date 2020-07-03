/*    */ package contaes.manejoDatos;
/*    */ 
/*    */ import contaes.Inicio;
/*    */ import contaes.manejoDatos.auxiliar.MySQLConection;
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
/*    */ import java.sql.Statement;
/*    */ import java.util.LinkedList;
/*    */ 
/*    */ public class ManejoConceptos
/*    */ {
/* 12 */   private static MySQLConection con = Inicio.getCGeneral();
/*    */ 
/*    */   public static void crear(String concepto) {
/*    */     try {
/* 16 */       con.getSentencia().executeUpdate("INSERT INTO Conceptos(descripcion) VALUES('" + concepto + "')");
/*    */     } catch (SQLException e) {
/* 18 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/*    */   public static void modificar(int id, String concepto) {
/*    */     try {
/* 24 */       con.getSentencia().executeUpdate("UPDATE Conceptos SET descripcion='" + concepto + "' " + "WHERE id=" + id);
/*    */     }
/*    */     catch (SQLException e) {
/* 27 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/*    */   public static void borrar(int id) {
/*    */     try {
/* 33 */       con.getSentencia().executeUpdate("DELETE FROM Conceptos WHERE id=" + id);
/*    */     } catch (SQLException e) {
/* 35 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/*    */   public static LinkedList<Integer> listaID() {
/* 40 */     LinkedList lista = new LinkedList();
/*    */     try
/*    */     {
/* 43 */       ResultSet res = con.getRes("SELECT id FROM Conceptos ORDER BY descripcion");
/* 44 */       while (res.next()) {
/* 45 */         lista.add(Integer.valueOf(res.getInt(1)));
/*    */       }
/* 47 */       res.close();
/*    */     } catch (SQLException e) {
/* 49 */       e.printStackTrace();
/*    */     }
/* 51 */     return lista;
/*    */   }
/*    */ 
/*    */   public static LinkedList<String> listaDescripcion() {
/* 55 */     LinkedList lista = new LinkedList();
/*    */     try
/*    */     {
/* 58 */       ResultSet res = con.getRes("SELECT descripcion FROM Conceptos ORDER BY descripcion");
/* 59 */       while (res.next()) {
/* 60 */         lista.add(res.getString(1));
/*    */       }
/* 62 */       res.close();
/*    */     } catch (SQLException e) {
/* 64 */       e.printStackTrace();
/*    */     }
/* 66 */     return lista;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.ManejoConceptos
 * JD-Core Version:    0.6.2
 */