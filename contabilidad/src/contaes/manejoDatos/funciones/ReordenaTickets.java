/*    */ package contaes.manejoDatos.funciones;
/*    */ 
/*    */ import almacen2.data.MySQLConection;
/*    */ import contaes.manejoDatos.auxiliar.TipoOrdenar;
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
/*    */ import java.sql.Statement;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.Date;
/*    */ import java.util.List;
/*    */ 
/*    */ public class ReordenaTickets
/*    */ {
/* 17 */   private static List<TipoOrdenar> lista = new ArrayList();
/*    */ 
/*    */   public static void reordenar(MySQLConection con, Date fechaInicio, Date fechaFin)
/*    */   {
/* 21 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/*    */     try {
/* 23 */       lista.clear();
/* 24 */       int nTickets = 0;
/* 25 */       ResultSet res = con.getRes("SELECT COUNT(numero) FROM tickets WHERE fecha BETWEEN '" + sdf.format(fechaInicio) + "' AND '" + sdf.format(fechaFin) + "'");
/*    */ 
/* 27 */       if (res.next()) {
/* 28 */         nTickets = res.getInt(1);
/*    */       }
/* 30 */       res = con.getRes("SELECT id,fecha FROM tickets WHERE fecha BETWEEN '" + sdf.format(fechaInicio) + "' AND '" + sdf.format(fechaFin) + "'" + " ORDER BY numero");
/*    */ 
/* 33 */       while (res.next()) {
/* 34 */         lista.add(new TipoOrdenar(res.getInt(1), res.getString(2)));
/*    */       }
/*    */ 
/* 37 */       Collections.sort(lista);
/*    */ 
/* 40 */       int min = -1;
/* 41 */       res = con.getRes("SELECT MIN(numero) FROM tickets WHERE fecha BETWEEN '" + sdf.format(fechaInicio) + "' AND '" + sdf.format(fechaFin) + "'");
/*    */ 
/* 43 */       if (res.next()) {
/* 44 */         min = res.getInt(1);
/*    */       }
/*    */ 
/* 47 */       if (min != -1)
/*    */       {
/* 49 */         int index = 0;
/* 50 */         for (int i = min; i < min + nTickets; i++) {
/* 51 */           con.getSentencia().executeUpdate("UPDATE tickets SET numero=" + i + " WHERE id = " + ((TipoOrdenar)lista.get(index)).ID());
/*    */ 
/* 53 */           index++;
/*    */         }
/*    */       }
/* 56 */       res.close();
/*    */     } catch (SQLException e) {
/* 58 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.funciones.ReordenaTickets
 * JD-Core Version:    0.6.2
 */