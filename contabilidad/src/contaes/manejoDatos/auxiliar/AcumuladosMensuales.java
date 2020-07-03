/*     */ package contaes.manejoDatos.auxiliar;
/*     */ 
/*     */ import internationalization.Mensajes;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import javax.swing.JOptionPane;
/*     */ 
/*     */ public class AcumuladosMensuales
/*     */ {
/*     */   private ResultSet res;
/*     */   private MySQLConection con;
/*  37 */   double[] d = new double[15];
/*  38 */   double[] h = new double[15];
/*  39 */   double[] s = new double[15];
/*     */   private boolean sinPYG;
/*     */ 
/*     */   public AcumuladosMensuales(MySQLConection con, String cuenta, String ejercicio)
/*     */   {
/*  47 */     this(con, cuenta, ejercicio, false);
/*     */   }
/*     */ 
/*     */   public AcumuladosMensuales(MySQLConection con, String cuenta, String ejercicio, boolean sinPYG) {
/*  51 */     this.sinPYG = sinPYG;
/*  52 */     this.con = con;
/*  53 */     ejecutar(cuenta, ejercicio);
/*     */   }
/*     */ 
/*     */   private void ejecutar(String cuenta, String ejercicio)
/*     */   {
/*  62 */     int mes = 1; int dias = 30; int year = Integer.parseInt(ejercicio);
/*  63 */     String fechaF = year + "-01-01";
/*  64 */     for (int x = 0; x < 15; x++) {
/*  65 */       this.d[x] = 0.0D;
/*  66 */       this.h[x] = 0.0D;
/*  67 */       this.s[x] = 0.0D;
/*     */     }
/*  69 */     String cuentaSelected = " apte.cuenta";
/*  70 */     if (cuenta.length() >= 8)
/*  71 */       cuentaSelected = cuentaSelected + " = " + cuenta;
/*     */     else {
/*  73 */       cuentaSelected = cuentaSelected + " BETWEEN " + cuenta + "0000000".substring(cuenta.length() - 1) + " AND " + cuenta + "9999999".substring(cuenta.length() - 1);
/*     */     }
/*     */     try
/*     */     {
/*  77 */       while (mes < 13) {
/*  78 */         switch (mes) {
/*     */         case 1:
/*     */         case 3:
/*     */         case 5:
/*     */         case 7:
/*     */         case 8:
/*     */         case 10:
/*     */         case 12:
/*  86 */           dias = 31;
/*  87 */           break;
/*     */         case 4:
/*     */         case 6:
/*     */         case 9:
/*     */         case 11:
/*  92 */           dias = 30;
/*  93 */           break;
/*     */         case 2:
/*  95 */           if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0))
/*  96 */             dias = 29;
/*     */           else {
/*  98 */             dias = 28;
/*     */           }
/*     */           break;
/*     */         }
/* 102 */         String mesSt = Integer.toString(mes);
/* 103 */         if (mesSt.length() == 1) {
/* 104 */           mesSt = "0" + mesSt;
/*     */         }
/* 106 */         fechaF = year + "-" + mesSt + "-" + dias;
/* 107 */         String fechaI = year + "-" + mesSt + "-01";
/* 108 */         String cadenaSeleccion = "SELECT SUM(apte.importe) FROM apte" + ejercicio + " AS apte JOIN " + "asto" + ejercicio + " AS asto " + "ON apte.id_asiento = asto.id_asiento " + "WHERE " + cuentaSelected + " AND asto.fecha BETWEEN '" + fechaI + "' AND '" + fechaF + "'" + " AND asto.marca NOT LIKE 'A'" + " AND asto.marca NOT LIKE 'C'";
/*     */ 
/* 116 */         if (this.sinPYG) {
/* 117 */           cadenaSeleccion = cadenaSeleccion + " AND asto.marca NOT LIKE 'G'";
/*     */         }
/* 119 */         cadenaSeleccion = cadenaSeleccion + " AND apte.DH = ";
/* 120 */         this.res = this.con.getRes(cadenaSeleccion + "'" + Mensajes.getString("debeA") + "'");
/* 121 */         if (this.res.next()) {
/* 122 */           this.d[mes] = this.res.getDouble(1);
/*     */         }
/* 124 */         this.res = this.con.getRes(cadenaSeleccion + "'" + Mensajes.getString("haberA") + "'");
/* 125 */         if (this.res.next()) {
/* 126 */           this.h[mes] = this.res.getDouble(1);
/*     */         }
/* 128 */         mes++;
/*     */       }
/* 130 */       String cadenaSeleccion = "SELECT SUM(apte.importe) FROM apte" + ejercicio + " AS apte JOIN " + "asto" + ejercicio + " AS asto " + "ON apte.id_asiento = asto.id_asiento " + "WHERE " + cuentaSelected + " AND asto.marca LIKE 'A'" + " AND apte.DH = ";
/*     */ 
/* 137 */       this.res = this.con.getRes(cadenaSeleccion + "'" + Mensajes.getString("debeA") + "'");
/* 138 */       if (this.res.next()) {
/* 139 */         this.d[0] = this.res.getDouble(1);
/*     */       }
/* 141 */       this.res = this.con.getRes(cadenaSeleccion + "'" + Mensajes.getString("haberA") + "'");
/* 142 */       if (this.res.next()) {
/* 143 */         this.h[0] = this.res.getDouble(1);
/*     */       }
/* 145 */       cadenaSeleccion = "SELECT SUM(apte.importe) FROM apte" + ejercicio + " AS apte JOIN " + "asto" + ejercicio + " AS asto " + "ON apte.id_asiento = asto.id_asiento " + "WHERE " + cuentaSelected + " AND asto.marca LIKE 'C'" + " AND apte.DH = ";
/*     */ 
/* 152 */       this.res = this.con.getRes(cadenaSeleccion + "'" + Mensajes.getString("debeA") + "'");
/* 153 */       if (this.res.next()) {
/* 154 */         this.d[13] = this.res.getDouble(1);
/*     */       }
/* 156 */       this.res = this.con.getRes(cadenaSeleccion + "'" + Mensajes.getString("haberA") + "'");
/* 157 */       if (this.res.next()) {
/* 158 */         this.h[13] = this.res.getDouble(1);
/*     */       }
/* 160 */       this.res.close();
/*     */     } catch (SQLException exc) {
/* 162 */       JOptionPane.showMessageDialog(null, Mensajes.getString("errObtDatos") + ":\n" + exc.getMessage());
/*     */     }
/* 164 */     for (int x = 0; x < 14; x++) {
/* 165 */       this.s[x] = (this.d[x] - this.h[x]);
/* 166 */       this.d[14] += this.d[x];
/* 167 */       this.h[14] += this.h[x];
/* 168 */       this.s[14] += this.s[x];
/*     */     }
/*     */   }
/*     */ 
/*     */   public double[] getD()
/*     */   {
/* 176 */     return this.d;
/*     */   }
/*     */ 
/*     */   public double[] getH()
/*     */   {
/* 183 */     return this.h;
/*     */   }
/*     */ 
/*     */   public double[] getS()
/*     */   {
/* 190 */     return this.s;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.auxiliar.AcumuladosMensuales
 * JD-Core Version:    0.6.2
 */