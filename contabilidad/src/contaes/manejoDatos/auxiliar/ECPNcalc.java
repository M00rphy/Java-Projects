/*    */ package contaes.manejoDatos.auxiliar;
/*    */ 
/*    */ import contaes.Inicio;
/*    */ import internationalization.Mensajes;
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
/*    */ import javax.swing.JOptionPane;
/*    */ 
/*    */ public class ECPNcalc
/*    */ {
/*    */   private static ResultSet res;
/*    */ 
/*    */   public static double sumaDebe(String cuenta, String ejercicio)
/*    */   {
/* 18 */     String cuentaSelected = " cuenta BETWEEN " + cuenta + "0000000".substring(cuenta.length() - 1) + " AND " + cuenta + "9999999".substring(cuenta.length() - 1);
/*    */ 
/* 20 */     String fechaI = ejercicio + "-01-01";
/* 21 */     String fechaF = ejercicio + "-12-31";
/* 22 */     double debe = 0.0D;
/*    */     try
/*    */     {
/* 25 */       String cadenaSeleccion = "SELECT SUM(apte.importe) FROM apte" + ejercicio + " AS apte JOIN " + "asto" + ejercicio + " AS asto " + "ON apte.id_asiento = asto.id_asiento " + "WHERE " + cuentaSelected + " AND asto.fecha BETWEEN '" + fechaI + "' AND '" + fechaF + "' ";
/*    */ 
/* 31 */       cadenaSeleccion = cadenaSeleccion + " AND asto.marca NOT LIKE 'A' AND asto.marca NOT LIKE 'C'";
/* 32 */       cadenaSeleccion = cadenaSeleccion + " AND apte.DH = ";
/* 33 */       res = Inicio.getCEmpresa().getRes(cadenaSeleccion + "'" + Mensajes.getString("debeA") + "'");
/* 34 */       if (res.next()) debe = res.getDouble(1);
/* 35 */       res.close();
/* 36 */       res = null;
/*    */     }
/*    */     catch (SQLException exc) {
/* 39 */       JOptionPane.showMessageDialog(null, Mensajes.getString("errObtDatos") + ":\n" + exc.getMessage());
/*    */     }
/* 41 */     return debe;
/*    */   }
/*    */ 
/*    */   public static double sumaHaber(String cuenta, String ejercicio) {
/* 45 */     String cuentaSelected = " cuenta BETWEEN " + cuenta + "0000000".substring(cuenta.length() - 1) + " AND " + cuenta + "9999999".substring(cuenta.length() - 1);
/*    */ 
/* 47 */     String fechaI = ejercicio + "-01-01";
/* 48 */     String fechaF = ejercicio + "-12-31";
/* 49 */     double haber = 0.0D;
/*    */     try {
/* 51 */       String cadenaSeleccion = "SELECT SUM(apte.importe) FROM apte" + ejercicio + " AS apte JOIN " + "asto" + ejercicio + " AS asto " + "ON apte.id_asiento = asto.id_asiento " + "WHERE " + cuentaSelected + " AND asto.fecha BETWEEN '" + fechaI + "' AND '" + fechaF + "' ";
/*    */ 
/* 57 */       cadenaSeleccion = cadenaSeleccion + " AND asto.marca NOT LIKE 'A' AND asto.marca NOT LIKE 'C'";
/* 58 */       cadenaSeleccion = cadenaSeleccion + " AND apte.DH = ";
/* 59 */       res = Inicio.getCEmpresa().getRes(cadenaSeleccion + "'" + Mensajes.getString("haberA") + "'");
/* 60 */       if (res.next()) haber = res.getDouble(1);
/* 61 */       res.close();
/* 62 */       res = null;
/*    */     }
/*    */     catch (SQLException exc) {
/* 65 */       JOptionPane.showMessageDialog(null, Mensajes.getString("errObtDatos") + ":\n" + exc.getMessage());
/*    */     }
/* 67 */     return haber;
/*    */   }
/*    */ 
/*    */   public static double saldoApertura(String cuenta, String ejercicio)
/*    */   {
/* 72 */     String cuentaSelected = " cuenta BETWEEN " + cuenta + "0000000".substring(cuenta.length() - 1) + " AND " + cuenta + "9999999".substring(cuenta.length() - 1);
/*    */ 
/* 74 */     double saldo = 0.0D;
/*    */     try {
/* 76 */       String cadenaSeleccion = "SELECT SUM(apte.importe) FROM apte" + ejercicio + " AS apte JOIN " + "asto" + ejercicio + " AS asto " + "ON apte.id_asiento = asto.id_asiento " + "WHERE " + cuentaSelected;
/*    */ 
/* 81 */       cadenaSeleccion = cadenaSeleccion + " AND asto.marca = 'A'";
/* 82 */       cadenaSeleccion = cadenaSeleccion + " AND apte.DH = ";
/* 83 */       res = Inicio.getCEmpresa().getRes(cadenaSeleccion + "'" + Mensajes.getString("haberA") + "'");
/* 84 */       if (res.next()) {
/* 85 */         saldo = res.getDouble(1);
/*    */       }
/* 87 */       res = Inicio.getCEmpresa().getRes(cadenaSeleccion + "'" + Mensajes.getString("debeA") + "'");
/* 88 */       if (res.next()) {
/* 89 */         saldo -= res.getDouble(1);
/*    */       }
/* 91 */       res.close();
/* 92 */       res = null;
/*    */     }
/*    */     catch (SQLException exc) {
/* 95 */       JOptionPane.showMessageDialog(null, Mensajes.getString("errObtDatos") + ":\n" + exc.getMessage());
/*    */     }
/* 97 */     return saldo;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.auxiliar.ECPNcalc
 * JD-Core Version:    0.6.2
 */