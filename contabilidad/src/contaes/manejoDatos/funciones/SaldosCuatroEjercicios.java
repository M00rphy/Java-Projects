/*    */ package contaes.manejoDatos.funciones;
/*    */ 
/*    */ import contaes.manejoDatos.auxiliar.AcumuladosMensuales;
/*    */ import contaes.manejoDatos.auxiliar.MySQLConection;
/*    */ import java.io.PrintStream;
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
/*    */ 
/*    */ public class SaldosCuatroEjercicios
/*    */ {
/*    */   private ResultSet res;
/*    */   private MySQLConection con;
/*    */   private String cuenta;
/*    */   private String[] year;
/*    */   private AcumuladosMensuales[] saldos;
/* 16 */   private boolean hayDatos = false;
/*    */   private boolean sinPYG;
/*    */ 
/*    */   public SaldosCuatroEjercicios(MySQLConection con, String cuenta)
/*    */   {
/* 23 */     this(con, cuenta, false);
/*    */   }
/*    */ 
/*    */   public SaldosCuatroEjercicios(MySQLConection con, String cuenta, boolean sinPYG) {
/* 27 */     this.con = con;
/* 28 */     this.cuenta = cuenta;
/* 29 */     this.sinPYG = sinPYG;
/*    */   }
/*    */ 
/*    */   public void generarDatos() {
/* 33 */     this.saldos = null;
/* 34 */     this.year = null;
/* 35 */     int nYear = 0;
/*    */     try {
/* 37 */       this.res = this.con.getRes("SHOW TABLES LIKE 'asto%'");
/* 38 */       while (this.res.next()) {
/* 39 */         nYear++;
/*    */       }
/* 41 */       this.year = new String[nYear];
/* 42 */       int i = 0;
/* 43 */       this.res = this.con.getRes("SHOW TABLES LIKE 'asto%'");
/* 44 */       while (this.res.next()) {
/* 45 */         this.year[i] = new String(this.res.getString(1).substring(4));
/* 46 */         i++;
/*    */       }
/* 48 */       this.res.close();
/*    */     } catch (SQLException e) {
/* 50 */       System.out.println(e.getMessage());
/*    */     }
/*    */ 
/* 53 */     for (int i = 0; i < nYear - 1; i++) {
/* 54 */       for (int j = i + 1; j < nYear; j++) {
/* 55 */         if (Integer.parseInt(this.year[j]) > Integer.parseInt(this.year[i])) {
/* 56 */           String temp = this.year[i];
/* 57 */           this.year[i] = this.year[j];
/* 58 */           this.year[j] = temp;
/*    */         }
/*    */       }
/*    */     }
/* 62 */     if (nYear > 4) nYear = 4;
/* 63 */     for (int i = 0; i < nYear - 1; i++) {
/* 64 */       for (int j = i + 1; j < nYear; j++) {
/* 65 */         if (Integer.parseInt(this.year[j]) < Integer.parseInt(this.year[i])) {
/* 66 */           String temp = this.year[i];
/* 67 */           this.year[i] = this.year[j];
/* 68 */           this.year[j] = temp;
/*    */         }
/*    */       }
/*    */     }
/* 72 */     if (nYear > 0) {
/* 73 */       this.saldos = new AcumuladosMensuales[nYear];
/* 74 */       for (int i = 0; i < nYear; i++)
/* 75 */         this.saldos[i] = new AcumuladosMensuales(this.con, this.cuenta, this.year[i], this.sinPYG);
/* 76 */       this.hayDatos = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   public boolean isHayDatos()
/*    */   {
/* 84 */     return this.hayDatos;
/*    */   }
/*    */ 
/*    */   public String[] getYear()
/*    */   {
/* 91 */     return this.year;
/*    */   }
/*    */ 
/*    */   public AcumuladosMensuales[] getSaldos()
/*    */   {
/* 98 */     return this.saldos;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.funciones.SaldosCuatroEjercicios
 * JD-Core Version:    0.6.2
 */