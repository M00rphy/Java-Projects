/*    */ package contaes.manejoDatos.funciones;
/*    */ 
/*    */ import contaes.manejoDatos.auxiliar.MySQLConection;
/*    */ import java.sql.SQLException;
/*    */ import java.sql.Statement;
/*    */ 
/*    */ public class SustitucionDeCuentas
/*    */ {
/*    */   private String ejercicio;
/*    */   private MySQLConection con;
/*    */ 
/*    */   public SustitucionDeCuentas(MySQLConection con, String ejercicio)
/*    */   {
/* 18 */     this.con = con;
/* 19 */     this.ejercicio = ejercicio;
/*    */   }
/*    */ 
/*    */   public void ejecutar(int cuentaOld, int cuentaNew, boolean diario, boolean recibidas, boolean emitidas)
/*    */   {
/*    */     try {
/* 25 */       if (diario) {
/* 26 */         this.con.getSentencia().executeUpdate("UPDATE apte" + this.ejercicio + " SET cuenta=" + cuentaNew + " WHERE cuenta=" + cuentaOld);
/*    */       }
/*    */ 
/* 29 */       if (recibidas) {
/* 30 */         this.con.getSentencia().executeUpdate("UPDATE reci" + this.ejercicio + " SET cuenta=" + cuentaNew + " WHERE cuenta=" + cuentaOld);
/*    */ 
/* 33 */         this.con.getSentencia().executeUpdate("UPDATE vencimientos SET cuenta=" + cuentaNew + " WHERE cuenta=" + cuentaOld);
/*    */       }
/*    */ 
/* 37 */       if (emitidas) {
/* 38 */         this.con.getSentencia().executeUpdate("UPDATE emit" + this.ejercicio + " SET cuenta=" + cuentaNew + " WHERE cuenta=" + cuentaOld);
/*    */ 
/* 41 */         this.con.getSentencia().executeUpdate("UPDATE vencimientosc SET cuenta=" + cuentaNew + " WHERE cuenta=" + cuentaOld);
/*    */       }
/*    */     }
/*    */     catch (SQLException e)
/*    */     {
/* 46 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.funciones.SustitucionDeCuentas
 * JD-Core Version:    0.6.2
 */