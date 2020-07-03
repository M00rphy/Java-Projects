/*    */ package contaes.manejoDatos.auxiliar;
/*    */ 
/*    */ import contaes.Inicio;
/*    */ import contaes.Puente;
/*    */ import java.io.PrintStream;
/*    */ import java.sql.Connection;
/*    */ import java.sql.DriverManager;
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
/*    */ import java.sql.Statement;
/*    */ 
/*    */ public class MySQLConection
/*    */ {
/*    */   private ResultSet res;
/*    */   private Connection con;
/*    */   private Statement sentencia;
/*    */ 
/*    */   public MySQLConection()
/*    */   {
/*    */     try
/*    */     {
/* 22 */       this.con = DriverManager.getConnection("jdbc:mysql://" + Inicio.p.getDireccionIP() + "/contaes", Inicio.p.getUsuario(), Inicio.p.getPassword());
/*    */ 
/* 25 */       this.sentencia = this.con.createStatement();
/*    */     } catch (SQLException exc) {
/* 27 */       System.out.println(exc.getMessage());
/*    */     }
/*    */   }
/*    */ 
/*    */   public MySQLConection(int empresa)
/*    */   {
/*    */     try
/*    */     {
/* 39 */       this.con = DriverManager.getConnection("jdbc:mysql://" + Inicio.p.getDireccionIP() + "/contaes" + empresa, Inicio.p.getUsuario(), Inicio.p.getPassword());
/*    */ 
/* 42 */       this.sentencia = this.con.createStatement();
/*    */     } catch (SQLException exc) {
/* 44 */       System.out.println(exc.getMessage());
/*    */     }
/*    */   }
/*    */ 
/*    */   public void cierraConexion()
/*    */   {
/*    */     try
/*    */     {
/* 54 */       if (this.res != null) {
/* 55 */         this.res.close();
/*    */       }
/* 57 */       this.sentencia.close();
/* 58 */       this.con.close();
/*    */     } catch (SQLException exc) {
/* 60 */       System.out.println(exc.getMessage());
/*    */     }
/*    */   }
/*    */ 
/*    */   public Statement getSentencia()
/*    */   {
/* 68 */     return this.sentencia;
/*    */   }
/*    */ 
/*    */   public ResultSet getRes(String consulta)
/*    */     throws SQLException
/*    */   {
/* 76 */     this.res = this.sentencia.executeQuery(consulta);
/* 77 */     return this.res;
/*    */   }
/*    */ 
/*    */   public Connection getCon()
/*    */   {
/* 84 */     return this.con;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.auxiliar.MySQLConection
 * JD-Core Version:    0.6.2
 */