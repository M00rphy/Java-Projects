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
/* 22 */       this.con = DriverManager.getConnection("jdbc:mysql://" + Inicio.p.getDireccionIP() + "/almacen2", Inicio.p.getUsuario(), Inicio.p.getPassword());
/*    */ 
/* 25 */       this.sentencia = this.con.createStatement();
/*    */     } catch (SQLException exc) {
/* 27 */       System.out.println(exc.getMessage());
/*    */     }
/*    */   }
/*    */ 
/*    */   public void cierraConexion()
/*    */   {
/*    */     try
/*    */     {
/* 37 */       this.sentencia.close();
/* 38 */       this.con.close();
/*    */     } catch (SQLException exc) {
/* 40 */       System.out.println(exc.getMessage());
/*    */     }
/*    */   }
/*    */ 
/*    */   public Statement getSentencia()
/*    */   {
/* 48 */     return this.sentencia;
/*    */   }
/*    */ 
/*    */   public ResultSet getRes(String consulta)
/*    */     throws SQLException
/*    */   {
/* 56 */     this.res = this.sentencia.executeQuery(consulta);
/* 57 */     return this.res;
/*    */   }
/*    */ 
/*    */   public Connection getCon()
/*    */   {
/* 64 */     return this.con;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.data.MySQLConection
 * JD-Core Version:    0.6.2
 */