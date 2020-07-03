/*    */ package contaes.manejoDatos.funciones;
/*    */ 
/*    */ import contaes.Inicio;
/*    */ import contaes.Puente;
/*    */ import contaes.manejoDatos.auxiliar.MySQLConection;
/*    */ import contaes.manejoDatos.auxiliar.TipoOrdenar;
/*    */ import java.io.PrintStream;
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
/*    */ import java.sql.Statement;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.Date;
/*    */ import java.util.List;
/*    */ 
/*    */ public class ReordenaEmitidasFacturacion
/*    */ {
/* 16 */   private static List<TipoOrdenar> lista = new ArrayList();
/*    */ 
/*    */   public static void reordenar(MySQLConection con, Date fechaInicio, Date fechaFin)
/*    */   {
/* 20 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/*    */     try {
/* 22 */       lista.clear();
/* 23 */       int nFacturas = 0;
/* 24 */       ResultSet res = con.getRes("SELECT COUNT(numero) FROM facturas WHERE fecha BETWEEN '" + sdf.format(fechaInicio) + "' AND '" + sdf.format(fechaFin) + "'");
/*    */ 
/* 26 */       if (res.next()) {
/* 27 */         nFacturas = res.getInt(1);
/*    */       }
/* 29 */       res = con.getRes("SELECT id,fecha FROM facturas WHERE fecha BETWEEN '" + sdf.format(fechaInicio) + "' AND '" + sdf.format(fechaFin) + "'" + " ORDER BY numero");
/*    */ 
/* 32 */       while (res.next()) {
/* 33 */         lista.add(new TipoOrdenar(res.getInt(1), res.getString(2)));
/*    */       }
/*    */ 
/* 36 */       Collections.sort(lista);
/*    */ 
/* 39 */       ArrayList listaTemp = new ArrayList();
/* 40 */       int min = 9999;
/* 41 */       int digitos = 4;
/* 42 */       res = con.getRes("SELECT numero FROM facturas WHERE fecha BETWEEN '" + sdf.format(fechaInicio) + "' AND '" + sdf.format(fechaFin) + "'" + " ORDER BY numero");
/*    */ 
/* 45 */       while (res.next()) {
/* 46 */         listaTemp.add(res.getString(1));
/*    */       }
/* 48 */       for (String numTemp : (List<String>)listaTemp) {
/* 49 */         if (numTemp.length() >= 4) {
/* 50 */           int largo = numTemp.length();
/* 51 */           String s = numTemp.substring(largo - digitos);
/*    */           try {
/* 53 */             int i = Integer.parseInt(s);
/* 54 */             if (i < min)
/* 55 */               min = i;
/*    */           }
/*    */           catch (NumberFormatException exc) {
/* 58 */             System.out.println(s + " no es un número");
/*    */           }
/*    */         }
/*    */ 
/*    */       }
/*    */ 
/* 64 */       int index = 0;
/* 65 */       for (int i = min; i < min + nFacturas; i++) {
/* 66 */         String nuevoNumero = Inicio.p.getPrefijo() + "0000".substring(String.valueOf(i).length()) + String.valueOf(i);
/*    */ 
/* 69 */         con.getSentencia().executeUpdate("UPDATE facturas SET numero='" + nuevoNumero + "' WHERE id = " + ((TipoOrdenar)lista.get(index)).ID());
/*    */ 
/* 71 */         index++;
/*    */       }
/* 73 */       res.close();
/*    */     } catch (SQLException e) {
/* 75 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.funciones.ReordenaEmitidasFacturacion
 * JD-Core Version:    0.6.2
 */