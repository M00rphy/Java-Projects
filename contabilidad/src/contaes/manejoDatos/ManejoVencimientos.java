/*     */ package contaes.manejoDatos;
/*     */ 
/*     */ import contaes.manejoDatos.auxiliar.MySQLConection;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
/*     */ 
/*     */ public class ManejoVencimientos
/*     */ {
/*     */   private MySQLConection con;
/*     */   private ResultSet res;
/*     */ 
/*     */   public ManejoVencimientos(MySQLConection con)
/*     */   {
/*  21 */     this.con = con;
/*     */   }
/*     */ 
/*     */   public boolean crear(boolean cobrar, TipoVencimiento vencimiento) {
/*  25 */     return crear(cobrar, vencimiento.getFecha(), vencimiento.getEjercicio(), vencimiento.getFactura(), vencimiento.getFechaf(), vencimiento.getCuenta(), vencimiento.getImporte(), vencimiento.getNum(), vencimiento.isPagado(), vencimiento.getCuentap());
/*     */   }
/*     */ 
/*     */   public boolean crear(boolean cobrar, String fecha, int ejerccio, String factura, String fechaf, int cuenta, double importe, String num, boolean pagado, int cuentap)
/*     */   {
/*  33 */     String tabla = cobrar ? "vencimientosc" : "vencimientos";
/*     */     try {
/*  35 */       this.con.getSentencia().executeUpdate("INSERT INTO " + tabla + " (fecha,ejercicio,factura,fechaf,cuenta,importe,num,pagado,cuentap)" + " VALUES ('" + fecha + "'," + ejerccio + ",'" + factura + "','" + fechaf + "'," + cuenta + "," + importe + ",'" + num + "'," + pagado + "," + cuentap + ")");
/*     */     }
/*     */     catch (SQLException e)
/*     */     {
/*  40 */       e.printStackTrace();
/*  41 */       return false;
/*     */     }
/*  43 */     return true;
/*     */   }
/*     */ 
/*     */   public void modificar(boolean cobrar, int id, String fecha, int ejerccio, String factura, String fechaf, int cuenta, double importe, String num, boolean pagado, int cuentap)
/*     */   {
/*  48 */     String tabla = cobrar ? "vencimientosc" : "vencimientos";
/*     */     try {
/*  50 */       this.con.getSentencia().executeUpdate("UPDATE " + tabla + " SET " + "fecha='" + fecha + "'," + "ejercicio=" + ejerccio + ", factura ='" + factura + "'," + "importe=" + importe + ",num='" + num + "',pagado=" + pagado + ",cuentap = " + cuentap + ",fechaf ='" + fechaf + "',cuenta=" + cuenta + " WHERE id = " + id);
/*     */     }
/*     */     catch (SQLException e)
/*     */     {
/*  57 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void borrar(boolean cobrar, int id)
/*     */   {
/*  63 */     String tabla = cobrar ? "vencimientosc" : "vencimientos";
/*     */     try {
/*  65 */       this.con.getSentencia().executeUpdate("DELETE FROM " + tabla + " WHERE id = " + id);
/*     */     }
/*     */     catch (SQLException e) {
/*  68 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void borrarAnterioresA(boolean cobrar, String fecha) {
/*  73 */     String tabla = cobrar ? "vencimientosc" : "vencimientos";
/*     */     try {
/*  75 */       this.con.getSentencia().executeUpdate("DELETE FROM " + tabla + " WHERE fecha < '" + fecha + "'");
/*     */     }
/*     */     catch (SQLException e) {
/*  78 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void borrarFactura(boolean cobrar, String factura)
/*     */   {
/*  89 */     String tabla = cobrar ? "vencimientosc" : "vencimientos";
/*     */     try {
/*  91 */       this.con.getSentencia().executeUpdate("DELETE FROM " + tabla + " WHERE factura = '" + factura + "'");
/*     */     }
/*     */     catch (SQLException e) {
/*  94 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void realizar(boolean cobrar, int id) {
/*  99 */     String tabla = cobrar ? "vencimientosc" : "vencimientos";
/*     */     try {
/* 101 */       this.con.getSentencia().executeUpdate("UPDATE " + tabla + " SET pagado = TRUE WHERE id=" + id);
/*     */     }
/*     */     catch (SQLException e) {
/* 104 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean isRealizado(boolean cobrar, int id) {
/* 109 */     String tabla = cobrar ? "vencimientosc" : "vencimientos";
/* 110 */     boolean isReali = false;
/*     */     try {
/* 112 */       this.res = this.con.getRes("SELECT pagado FROM " + tabla + " WHERE id = " + id);
/*     */ 
/* 114 */       if (this.res.next())
/* 115 */         isReali = this.res.getBoolean(1);
/*     */     }
/*     */     catch (SQLException e) {
/* 118 */       e.printStackTrace();
/* 119 */       return isReali;
/*     */     }
/* 121 */     return isReali;
/*     */   }
/*     */ 
/*     */   public TipoVencimiento datos(boolean cobrar, int id) {
/* 125 */     TipoVencimiento vencimiento = null;
/* 126 */     String tabla = cobrar ? "vencimientosc" : "vencimientos";
/*     */     try {
/* 128 */       this.res = this.con.getRes("SELECT * FROM " + tabla + " WHERE id = " + id);
/*     */ 
/* 130 */       if (this.res.next()) {
/* 131 */         vencimiento = new TipoVencimiento(id, this.res.getString(2), this.res.getInt(3), this.res.getString(4), this.res.getString(5), this.res.getInt(6), this.res.getDouble(7), this.res.getString(8), this.res.getBoolean(9), this.res.getInt(10));
/*     */       }
/*     */ 
/* 136 */       this.res.close();
/*     */     } catch (SQLException e) {
/* 138 */       e.printStackTrace();
/*     */     }
/* 140 */     return vencimiento;
/*     */   }
/*     */ 
/*     */   public ArrayList<Integer> listaFechaFactura(boolean cobrar, String factura, Date fecha)
/*     */   {
/* 148 */     ArrayList lista = new ArrayList();
/* 149 */     GregorianCalendar fechaIni = new GregorianCalendar();
/* 150 */     fechaIni.setTime(fecha);
/* 151 */     fechaIni.add(5, -5);
/* 152 */     GregorianCalendar fechaFin = new GregorianCalendar();
/* 153 */     fechaFin.setTime(fecha);
/* 154 */     fechaFin.add(5, 5);
/* 155 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/* 156 */     String tabla = cobrar ? "vencimientosc" : "vencimientos";
/*     */     try {
/* 158 */       this.res = this.con.getRes("SELECT id FROM " + tabla + " WHERE factura = '" + factura + "' AND" + " fecha BETWEEN '" + sdf.format(fechaIni.getTime()) + "' AND" + " '" + sdf.format(fechaFin.getTime()) + "'" + " AND pagado = 0");
/*     */ 
/* 163 */       while (this.res.next()) {
/* 164 */         Integer id = Integer.valueOf(this.res.getInt(1));
/* 165 */         lista.add(id);
/*     */       }
/* 167 */       this.res.close();
/*     */     } catch (SQLException e) {
/* 169 */       e.printStackTrace();
/*     */     }
/* 171 */     return lista;
/*     */   }
/*     */ 
/*     */   public void cerrarRs() {
/* 175 */     if (this.res != null) {
/*     */       try {
/* 177 */         this.res.close();
/*     */       } catch (SQLException sqlEx) {
/*     */       }
/* 180 */       this.res = null;
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.ManejoVencimientos
 * JD-Core Version:    0.6.2
 */