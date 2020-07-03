/*    */ package almacen2.data;
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
/*    */ public class MySQLConectionOldAlmacen
/*    */ {
/*    */   private ResultSet res;
/*    */   private Connection con;
/*    */   private Statement sentencia;
/*    */ 
/*    */   public MySQLConectionOldAlmacen()
/*    */   {
/*    */     try
/*    */     {
/* 22 */       this.con = DriverManager.getConnection("jdbc:mysql://" + Inicio.p.getDireccionIP() + "/almacen", Inicio.p.getUsuario(), Inicio.p.getPassword());
/*    */ 
/* 26 */       this.sentencia = this.con.createStatement();
/*    */     }
/*    */     catch (SQLException exc) {
/* 29 */       System.out.println(exc.getMessage());
/*    */     }
/*    */   }
/*    */ 
/*    */   public void cierraConexion()
/*    */   {
/*    */     try
/*    */     {
/* 39 */       this.sentencia.close();
/* 40 */       this.con.close();
/*    */     }
/*    */     catch (SQLException exc) {
/* 43 */       System.out.println(exc.getMessage());
/*    */     }
/*    */   }
/*    */ 
/*    */   public Statement getSentencia()
/*    */   {
/* 51 */     return this.sentencia;
/*    */   }
/*    */ 
/*    */   public ResultSet getRes(String consulta)
/*    */     throws SQLException
/*    */   {
/* 59 */     this.res = this.sentencia.executeQuery(consulta);
/* 60 */     return this.res;
/*    */   }
/*    */ 
/*    */   public Connection getCon()
/*    */   {
/* 67 */     return this.con;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.data.MySQLConectionOldAlmacen
 * JD-Core Version:    0.6.2
 */