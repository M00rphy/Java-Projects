/*    */ package almacen2.data;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.sql.Connection;
/*    */ import java.sql.DriverManager;
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
/*    */ import java.sql.Statement;
/*    */ import java.util.ArrayList;
/*    */ import java.util.GregorianCalendar;
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.Logger;
/*    */ 
/*    */ public class ManejadorSubcuentas
/*    */ {
/*    */   private ResultSet res;
/*    */   private Connection con;
/*    */   private Statement sentencia;
/*    */ 
/*    */   public ManejadorSubcuentas(UserObject usuario)
/*    */   {
/*    */     try
/*    */     {
/* 31 */       this.con = DriverManager.getConnection("jdbc:mysql://" + usuario.getDir() + "/contaes" + usuario.getEmpresa(), usuario.getNombre(), usuario.getContra());
/*    */ 
/* 35 */       this.sentencia = this.con.createStatement();
/*    */     }
/*    */     catch (SQLException exc) {
/* 38 */       System.out.println(exc.getMessage());
/*    */     }
/*    */   }
/*    */ 
/*    */   public ArrayList<SubcuentaObject> listaSubcuentas(int subcIni, int subCfin) {
/* 43 */     ArrayList lista = new ArrayList();
/* 44 */     int ejercicio = new GregorianCalendar().get(1);
/*    */     try
/*    */     {
/* 47 */       this.res = this.sentencia.executeQuery("SELECT codigo,nombre FROM scta" + ejercicio + " WHERE codigo BETWEEN " + subcIni + " AND " + subCfin);
/*    */ 
/* 49 */       while (this.res.next()) {
/* 50 */         Integer codigo = Integer.valueOf(this.res.getInt(1));
/* 51 */         String nombre = this.res.getString(2);
/* 52 */         SubcuentaObject subcuenta = new SubcuentaObject(codigo, nombre);
/* 53 */         lista.add(subcuenta);
/*    */       }
/*    */     } catch (SQLException ex) {
/* 56 */       Logger.getLogger(ManejadorSubcuentas.class.getName()).log(Level.SEVERE, null, ex);
/*    */     }
/* 58 */     return lista;
/*    */   }
/*    */ 
/*    */   public SubcuentaObject getSubcuenta(int codigo) {
/* 62 */     int ejercicio = new GregorianCalendar().get(1);
/* 63 */     SubcuentaObject subcuenta = null;
/*    */     try {
/* 65 */       this.res = this.sentencia.executeQuery("SELECT nombre FROM scta" + ejercicio + " WHERE codigo = " + codigo);
/*    */ 
/* 67 */       if (this.res.next()) {
/* 68 */         String nombre = this.res.getString(1);
/* 69 */         subcuenta = new SubcuentaObject(Integer.valueOf(codigo), nombre);
/*    */       }
/*    */     } catch (SQLException ex) {
/* 72 */       Logger.getLogger(ManejadorSubcuentas.class.getName()).log(Level.SEVERE, null, ex);
/*    */     }
/* 74 */     return subcuenta;
/*    */   }
/*    */ 
/*    */   public void cerrarConexion() {
/*    */     try {
/* 79 */       if (this.res != null) {
/* 80 */         this.res.close();
/* 81 */         this.res = null;
/*    */       }
/* 83 */       if (this.sentencia != null) {
/* 84 */         this.sentencia.close();
/* 85 */         this.sentencia = null;
/*    */       }
/* 87 */       if (this.con != null) {
/* 88 */         this.con.close();
/* 89 */         this.con = null;
/*    */       }
/*    */     } catch (SQLException ex) {
/* 92 */       ex.printStackTrace();
/*    */     }
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.data.ManejadorSubcuentas
 * JD-Core Version:    0.6.2
 */