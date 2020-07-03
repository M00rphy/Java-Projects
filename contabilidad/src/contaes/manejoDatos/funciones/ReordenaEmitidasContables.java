/*    */ package contaes.manejoDatos.funciones;
/*    */ 
/*    */ import contaes.Inicio;
/*    */ import contaes.Puente;
/*    */ import contaes.manejoDatos.auxiliar.MySQLConection;
/*    */ import contaes.manejoDatos.auxiliar.TipoOrdenar;
/*    */ import java.sql.Connection;
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
/*    */ import java.sql.Statement;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ 
/*    */ public class ReordenaEmitidasContables
/*    */ {
/* 14 */   private static List<TipoOrdenar> lista = new ArrayList();
/*    */ 
/*    */   public static void reordenar(MySQLConection con, String ejercicio)
/*    */   {
/*    */     try {
/* 19 */       lista.clear();
/* 20 */       int nFacturas = 0;
/* 21 */       ResultSet res = con.getRes("SELECT COUNT(numero) FROM emit" + ejercicio);
/* 22 */       if (res.next()) {
/* 23 */         nFacturas = res.getInt(1);
/*    */       }
/* 25 */       res = con.getRes("SELECT numero,fecha FROM emit" + ejercicio + " ORDER BY numero");
/*    */ 
/* 27 */       while (res.next()) {
/* 28 */         lista.add(new TipoOrdenar(res.getInt(1), res.getString(2)));
/*    */       }
/*    */ 
/* 32 */       Collections.sort(lista);
/*    */ 
/* 35 */       con.getCon().setAutoCommit(false);
/* 36 */       for (int i = 0; i < nFacturas; i++) {
/* 37 */         String nuevoNumero = Inicio.p.getPrefijo() + "0000".substring(String.valueOf(i + 1).length()) + String.valueOf(i + 1);
/*    */ 
/* 40 */         con.getSentencia().executeUpdate("UPDATE emit" + ejercicio + " SET numero='" + nuevoNumero + "' WHERE numero=" + ((TipoOrdenar)lista.get(i)).ID());
/*    */       }
/*    */ 
/* 43 */       con.getCon().commit();
/* 44 */       con.getCon().setAutoCommit(true);
/* 45 */       res.close();
/*    */     } catch (SQLException e) {
/* 47 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.funciones.ReordenaEmitidasContables
 * JD-Core Version:    0.6.2
 */