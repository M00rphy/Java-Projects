/*    */ package contaes.manejoDatos.funciones;
/*    */ 
/*    */ import contaes.manejoDatos.ManejoSubcuentas;
/*    */ import contaes.manejoDatos.auxiliar.MySQLConection;
/*    */ import internationalization.Mensajes;
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
/*    */ import java.sql.Statement;
/*    */ import java.util.Iterator;
/*    */ import java.util.LinkedList;
/*    */ 
/*    */ public class RegeneraSaldos
/*    */ {
/*    */   private static ResultSet res;
/*    */ 
/*    */   public static void regenera(MySQLConection con, String ejercicio)
/*    */   {
/* 17 */     ManejoSubcuentas cuentaM = new ManejoSubcuentas(con, ejercicio);
/* 18 */     LinkedList listaCta = cuentaM.listadoSubcuentas(10000000, 80000000);
/* 19 */     double d = 0.0D; double h = 0.0D; double s = 0.0D;
/* 20 */     for (Iterator i$ = listaCta.iterator(); i$.hasNext(); ) { int cuenta = ((Integer)i$.next()).intValue();
/*    */       try {
/* 22 */         res = con.getRes("SELECT SUM(importe) FROM apte" + ejercicio + " WHERE cuenta = " + cuenta + " AND DH = '" + Mensajes.getString("debeA") + "'");
/*    */ 
/* 25 */         if (res.next()) {
/* 26 */           d = res.getDouble(1);
/*    */         }
/* 28 */         res = con.getRes("SELECT SUM(importe) FROM apte" + ejercicio + " WHERE cuenta = " + cuenta + " AND DH = '" + Mensajes.getString("haberA") + "'");
/*    */ 
/* 31 */         if (res.next()) {
/* 32 */           h = res.getDouble(1);
/*    */         }
/* 34 */         s = d - h;
/* 35 */         con.getSentencia().executeUpdate("UPDATE scta" + ejercicio + " SET debe=" + d + ", haber=" + h + ", saldo=" + s + " WHERE codigo=" + cuenta);
/*    */ 
/* 38 */         res.close();
/*    */       } catch (SQLException e) {
/* 40 */         e.printStackTrace();
/*    */       }
/* 42 */       d = 0.0D;
/* 43 */       h = 0.0D;
/* 44 */       s = 0.0D;
/*    */     }
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.funciones.RegeneraSaldos
 * JD-Core Version:    0.6.2
 */