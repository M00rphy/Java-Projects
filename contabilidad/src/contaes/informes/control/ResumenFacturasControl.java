/*     */ package contaes.informes.control;
/*     */ 
/*     */ import contaes.informes.model.ResumenFacturasObject;
/*     */ import contaes.manejoDatos.ManejoSubcuentas;
/*     */ import contaes.manejoDatos.TipoSubcuenta;
/*     */ import contaes.manejoDatos.auxiliar.MySQLConection;
/*     */ import internationalization.Mensajes;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.GregorianCalendar;
import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ 
/*     */ public class ResumenFacturasControl
/*     */ {
/*     */   private MySQLConection con;
/*     */   private String ejercicio;
/*     */   private ResultSet res;
/*     */ 
/*     */   public ResumenFacturasControl(MySQLConection con, String ejercicio)
/*     */   {
/*  33 */     this.con = con;
/*  34 */     this.ejercicio = ejercicio;
/*     */   }
/*     */ 
/*     */   public ArrayList<ResumenFacturasObject> listado(int cuentaIni, int cuentaFin, java.util.Date fechaIni, java.util.Date fechaFin, boolean ventas)
/*     */   {
/*  41 */     ArrayList array = new ArrayList();
/*  42 */     String tabla = "reci" + this.ejercicio;
/*  43 */     String sqlQueryImporte = "SELECT SUM(total) FROM " + tabla + " WHERE cuenta = ?" + " AND fecha BETWEEN ? AND ?";
/*     */ 
/*  45 */     ArrayList arrayCuentas = new ArrayList();
/*  46 */     ManejoSubcuentas mS = new ManejoSubcuentas(this.con, this.ejercicio);
/*  47 */     arrayCuentas = mS.listaSubcuentas(cuentaIni, cuentaFin);
/*  48 */     mS.cerrarRs();
/*  49 */     double sumaTotal = 0.0D;
/*  50 */     GregorianCalendar fechaIniGC = new GregorianCalendar();
/*  51 */     GregorianCalendar fechaFinGC = new GregorianCalendar();
/*     */ 
/*  53 */     fechaIniGC.setTime(fechaIni);
/*  54 */     fechaFinGC.setTime(fechaFin);
/*     */ 
/*  56 */     int anioInicial = fechaIniGC.get(1);
/*  57 */     int anioFinal = fechaFinGC.get(1);
/*  58 */     int mesInicial = fechaIniGC.get(2) + 1;
/*  59 */     int mesFinal = fechaFinGC.get(2) + 1;
/*  60 */     int anioEnCurso = anioInicial;
/*  61 */     int mesEnCurso = mesInicial;
/*     */ 
/*  63 */     while (anioEnCurso <= anioFinal) {
/*  64 */       while (mesEnCurso <= 12) {
/*  65 */         Double sumaMes = Double.valueOf(0.0D);
/*  66 */         fechaIniGC = new GregorianCalendar(anioEnCurso, mesEnCurso - 1, 1);
/*  67 */         fechaFinGC = new GregorianCalendar(anioEnCurso, mesEnCurso - 1, diaFinalMes(mesEnCurso, anioEnCurso));
/*  68 */         String mes = Integer.toString(fechaIniGC.get(2) + 1) + "/" + Integer.toString(fechaIniGC.get(1));
/*     */ 
/*  70 */         for (TipoSubcuenta cuenta :(List<TipoSubcuenta>) arrayCuentas) {
/*     */           try {
/*  72 */             PreparedStatement ps = this.con.getCon().prepareStatement(sqlQueryImporte);
/*  73 */             ps.setInt(1, cuenta.getCodigo());
/*  74 */             ps.setDate(2, new java.sql.Date(fechaIniGC.getTimeInMillis()));
/*  75 */             ps.setDate(3, new java.sql.Date(fechaFinGC.getTimeInMillis()));
/*  76 */             this.res = ps.executeQuery();
/*  77 */             if (this.res.next()) {
/*  78 */               Double importe = Double.valueOf(this.res.getDouble(1));
/*  79 */               ResumenFacturasObject objeto = new ResumenFacturasObject(mes, cuenta.getNombre(), importe);
/*  80 */               array.add(objeto);
/*  81 */               sumaMes = Double.valueOf(sumaMes.doubleValue() + importe.doubleValue());
/*     */             }
/*     */           } catch (SQLException ex) {
/*  84 */             Logger.getLogger(ResumenFacturasControl.class.getName()).log(Level.SEVERE, null, ex);
/*     */           }
/*     */         }
/*  87 */         array.add(new ResumenFacturasObject(mes, Mensajes.getString("totalMes"), sumaMes));
/*  88 */         sumaTotal += sumaMes.doubleValue();
/*  89 */         if ((anioEnCurso == anioFinal) && (mesEnCurso == mesFinal)) {
/*     */           break;
/*     */         }
/*  92 */         mesEnCurso++;
/*     */       }
/*  94 */       mesEnCurso = 1;
/*  95 */       anioEnCurso++;
/*     */     }
/*  97 */     array.add(new ResumenFacturasObject("", Mensajes.getString("total"), Double.valueOf(sumaTotal)));
/*  98 */     return array;
/*     */   }
/*     */ 
/*     */   private int diaFinalMes(int mes, int year) {
/* 102 */     int dias = 31;
/*     */ 
/* 104 */     switch (mes) {
/*     */     case 4:
/*     */     case 6:
/*     */     case 9:
/*     */     case 11:
/* 109 */       dias = 30;
/* 110 */       break;
/*     */     case 2:
/* 112 */       if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0))
/* 113 */         dias = 29;
/*     */       else
/* 115 */         dias = 28; break;
/*     */     case 3:
/*     */     case 5:
/*     */     case 7:
/*     */     case 8:
/* 119 */     case 10: } return dias;
/*     */   }
/*     */ 
/*     */   public void cerrarRs() {
/* 123 */     if (this.res != null) {
/*     */       try {
/* 125 */         this.res.close();
/*     */       } catch (SQLException sqlEx) {
/*     */       }
/* 128 */       this.res = null;
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.informes.control.ResumenFacturasControl
 * JD-Core Version:    0.6.2
 */