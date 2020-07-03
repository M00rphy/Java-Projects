/*    */ package contaes.manejoDatos.funciones;
/*    */ 
/*    */ import contaes.manejoDatos.auxiliar.MySQLConection;
/*    */ import contaes.manejoDatos.auxiliar.TipoOrdenar;
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
/*    */ import java.sql.Statement;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ 
/*    */ public class ReordenaDiario
/*    */ {
/* 14 */   private static List<TipoOrdenar> lista = new ArrayList();
/*    */ 
/*    */   public static void reordenar(MySQLConection con, String ejercicio)
/*    */   {
/*    */     try {
/* 19 */       lista.clear();
/* 20 */       int nAsientos = 0;
/* 21 */       ResultSet res = con.getRes("SELECT COUNT(numero) FROM asto" + ejercicio);
/* 22 */       if (res.next()) {
/* 23 */         nAsientos = res.getInt(1);
/*    */       }
/* 25 */       res = con.getRes("SELECT id_asiento,fecha FROM asto" + ejercicio + " ORDER BY numero");
/*    */ 
/* 27 */       while (res.next()) {
/* 28 */         lista.add(new TipoOrdenar(res.getInt(1), res.getString(2)));
/*    */       }
/*    */ 
/* 31 */       Collections.sort(lista);
/*    */ 
/* 33 */       TipoOrdenar aux = null;
/* 34 */       res = con.getRes("SELECT id_asiento,fecha FROM asto" + ejercicio + " WHERE marca='A'");
/*    */ 
/* 36 */       if (res.next()) {
/* 37 */         aux = new TipoOrdenar(res.getInt(1), res.getString(2));
/* 38 */         int indice = encontrar(aux);
/* 39 */         if (indice != -1) {
/* 40 */           lista.remove(indice);
/* 41 */           lista.add(0, aux);
/*    */         }
/*    */       }
/* 44 */       res = con.getRes("SELECT id_asiento,fecha FROM asto" + ejercicio + " WHERE marca='C'");
/*    */ 
/* 46 */       if (res.next()) {
/* 47 */         aux = new TipoOrdenar(res.getInt(1), res.getString(2));
/* 48 */         int indice = encontrar(aux);
/* 49 */         if (indice != -1) {
/* 50 */           lista.remove(indice);
/* 51 */           lista.add(aux);
/*    */         }
/*    */       }
/*    */ 
/* 55 */       for (int i = 0; i < nAsientos; i++) {
/* 56 */         con.getSentencia().executeUpdate("UPDATE asto" + ejercicio + " SET numero=" + (i + 1) + " WHERE id_asiento=" + ((TipoOrdenar)lista.get(i)).ID());
/*    */       }
/*    */ 
/* 59 */       res.close();
/*    */     } catch (SQLException e) {
/* 61 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/*    */   private static int encontrar(TipoOrdenar dato) {
/* 66 */     for (int indice = 0; indice < lista.size(); indice++) {
/* 67 */       if (((TipoOrdenar)lista.get(indice)).ID() == dato.ID()) {
/* 68 */         return indice;
/*    */       }
/*    */     }
/* 71 */     return -1;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.funciones.ReordenaDiario
 * JD-Core Version:    0.6.2
 */