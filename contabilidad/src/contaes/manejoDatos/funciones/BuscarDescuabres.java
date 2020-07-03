/*    */ package contaes.manejoDatos.funciones;
/*    */ 
/*    */ import contaes.manejoDatos.auxiliar.MySQLConection;
/*    */ import internationalization.Mensajes;
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
/*    */ import java.util.LinkedList;
/*    */ 
/*    */ public class BuscarDescuabres
/*    */ {
/*    */   public static String Comprobar(MySQLConection con, String ejercicio)
/*    */   {
/* 15 */     String listaDescuabres = "";
/* 16 */     LinkedList numeroAs = new LinkedList();
/* 17 */     LinkedList debeAs = new LinkedList();
/* 18 */     LinkedList haberAs = new LinkedList();
/*    */     try {
/* 20 */       ResultSet res = con.getRes("SELECT ast.numero,SUM(apt.importe) FROM apte" + ejercicio + " AS apt" + " JOIN asto" + ejercicio + " AS ast" + " ON apt.id_asiento = ast.id_asiento" + " WHERE apt.DH = '" + Mensajes.getString("debeA") + "'" + " GROUP BY ast.id_asiento");
/*    */ 
/* 27 */       while (res.next()) {
/* 28 */         numeroAs.add(Integer.valueOf(res.getInt(1)));
/* 29 */         debeAs.add(Double.valueOf(res.getDouble(2)));
/*    */       }
/* 31 */       res = con.getRes("SELECT SUM(apt.importe) FROM apte" + ejercicio + " AS apt" + " JOIN asto" + ejercicio + " AS ast" + " ON apt.id_asiento = ast.id_asiento" + " WHERE apt.DH = '" + Mensajes.getString("haberA") + "'" + " GROUP BY ast.id_asiento");
/*    */ 
/* 38 */       while (res.next()) {
/* 39 */         haberAs.add(Double.valueOf(res.getDouble(1)));
/*    */       }
/*    */ 
/* 42 */       for (int i = 0; i < numeroAs.size(); i++) {
/* 43 */         double saldo = ((Double)debeAs.get(i)).doubleValue() - ((Double)haberAs.get(i)).doubleValue();
/* 44 */         if (((saldo > 0.005D ? 1 : 0) | (saldo < -0.005D ? 1 : 0)) != 0) {
/* 45 */           listaDescuabres = listaDescuabres + Mensajes.getString("asiento") + ": " + numeroAs.get(i) + " " + Mensajes.getString("descuadrado") + ".\n";
/*    */         }
/*    */       }
/* 48 */       res.close();
/*    */     } catch (SQLException exc) {
/* 50 */       exc.printStackTrace();
/*    */     }
/* 52 */     return listaDescuabres;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.funciones.BuscarDescuabres
 * JD-Core Version:    0.6.2
 */