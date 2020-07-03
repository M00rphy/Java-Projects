/*    */ package contaes.manejoDatos.funciones;
/*    */ 
/*    */ import contaes.Inicio;
/*    */ import contaes.manejoDatos.auxiliar.LeerFichero;
/*    */ import contaes.manejoDatos.auxiliar.MySQLConection;
/*    */ import java.io.IOException;
/*    */ import java.sql.Connection;
/*    */ import java.sql.PreparedStatement;
/*    */ import java.sql.SQLException;
/*    */ import java.util.Properties;
/*    */ 
/*    */ public class IntroducirPGCenBD
/*    */ {
/*    */   public void ejecutar()
/*    */   {
/* 26 */     Properties sistema = System.getProperties();
/* 27 */     String sisOp = sistema.getProperty("os.name");
/* 28 */     String archivoPGC = sisOp.substring(0, 3).equals("Mac") ? "pgc08MAC.txt" : "pgc08.txt";
/* 29 */     String sql = "INSERT INTO Plan_contable (codigo,nombre,grupo_bal) VALUES(?,?,?)";
/*    */     try
/*    */     {
/* 32 */       LeerFichero pgc = new LeerFichero("configdir/" + archivoPGC);
/* 33 */       int numeroCuentas = Integer.parseInt(pgc.leer());
/* 34 */       PreparedStatement ps = Inicio.getCGeneral().getCon().prepareCall(sql);
/*    */ 
/* 36 */       for (int x = 0; x < numeroCuentas; x++) {
/* 37 */         String linea = pgc.leer();
/* 38 */         int codigo = Integer.parseInt(linea.substring(0, linea.indexOf(";")));
/* 39 */         String nombre = linea.substring(linea.indexOf(";") + 1, linea.lastIndexOf(";"));
/* 40 */         String codBal = linea.substring(linea.lastIndexOf(";") + 1);
/* 41 */         ps.setInt(1, codigo);
/* 42 */         ps.setString(2, nombre);
/* 43 */         ps.setString(3, codBal);
/* 44 */         ps.execute();
/* 45 */         if (pgc.eof())
/*    */           break;
/*    */       }
/*    */     }
/*    */     catch (SQLException ex) {
/* 50 */       ex.printStackTrace();
/*    */     }
/*    */     catch (IOException e) {
/* 53 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.funciones.IntroducirPGCenBD
 * JD-Core Version:    0.6.2
 */