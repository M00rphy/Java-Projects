/*     */ package contaes.manejoDatos;
/*     */ 
/*     */ import contaes.manejoDatos.auxiliar.MySQLConection;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class ManejoApuntes
/*     */ {
/*     */   private MySQLConection con;
/*     */   private String ejercicio;
/*     */   private ResultSet res;
/*     */ 
/*     */   public ManejoApuntes(MySQLConection con, String ejercicio)
/*     */   {
/*  20 */     this.con = con;
/*  21 */     this.ejercicio = ejercicio;
/*     */   }
/*     */ 
/*     */   public boolean crear(TipoApunte apunte) {
/*  25 */     if (crear(apunte.getId_asiento(), apunte.getCuenta(), apunte.getConcepto(), apunte.getDH(), apunte.getImporte()))
/*     */     {
/*  27 */       return true;
/*     */     }
/*  29 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean crear(int id_asiento, int cuenta, String concepto, String DH, double importe) {
/*     */     try {
/*  34 */       this.con.getSentencia().executeUpdate("INSERT INTO apte" + this.ejercicio + " (id_asiento,cuenta,concepto,DH,importe) VALUES(" + id_asiento + "," + cuenta + ",'" + concepto + "','" + DH + "'," + importe + ")");
/*     */ 
/*  37 */       ManejoSubcuentas cuentas = new ManejoSubcuentas(this.con, this.ejercicio);
/*  38 */       cuentas.actualizarImportes(true, cuenta, DH, importe);
/*     */     } catch (SQLException e) {
/*  40 */       e.printStackTrace();
/*  41 */       return false;
/*     */     }
/*  43 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean borrar(int id_apunte) {
/*  47 */     TipoApunte apunte = datos(id_apunte);
/*  48 */     if (apunte != null) {
/*     */       try {
/*  50 */         this.con.getSentencia().executeUpdate("DELETE FROM apte" + this.ejercicio + " WHERE id_apunte=" + id_apunte);
/*     */ 
/*  52 */         ManejoSubcuentas cuentas = new ManejoSubcuentas(this.con, this.ejercicio);
/*  53 */         cuentas.actualizarImportes(false, apunte.getCuenta(), apunte.getDH(), apunte.getImporte());
/*  54 */         return true;
/*     */       }
/*     */       catch (SQLException e) {
/*  57 */         e.printStackTrace();
/*     */       }
/*     */     }
/*  60 */     return false;
/*     */   }
/*     */ 
/*     */   public void modificar(TipoApunte apunte)
/*     */   {
/*  85 */     modificar(apunte.getId_apunte(), apunte.getCuenta(), apunte.getConcepto(), apunte.getDH(), apunte.getImporte());
/*     */   }
/*     */ 
/*     */   public void modificar(int id_apunte, int cuenta, String concepto, String DH, double importe)
/*     */   {
/*  90 */     TipoApunte apunte = datos(id_apunte);
/*  91 */     if (apunte != null)
/*     */       try {
/*  93 */         ManejoSubcuentas cuentaModif = new ManejoSubcuentas(this.con, this.ejercicio);
/*  94 */         cuentaModif.actualizarImportes(false, apunte.getCuenta(), apunte.getDH(), apunte.getImporte());
/*  95 */         this.con.getSentencia().executeUpdate("UPDATE apte" + this.ejercicio + " SET cuenta = " + cuenta + ", concepto = '" + concepto + "'" + ", DH = '" + DH + "'" + ", importe = " + importe + " WHERE id_apunte = " + id_apunte);
/*     */ 
/* 101 */         cuentaModif.actualizarImportes(true, cuenta, DH, importe);
/*     */       }
/*     */       catch (SQLException e) {
/* 104 */         e.printStackTrace();
/*     */       }
/*     */   }
/*     */ 
/*     */   public TipoApunte datos(int id_apunte)
/*     */   {
/* 132 */     TipoApunte apunte = null;
/*     */     try {
/* 134 */       this.res = this.con.getRes("SELECT * FROM apte" + this.ejercicio + " WHERE id_apunte=" + id_apunte);
/*     */ 
/* 136 */       if (this.res.next()) {
/* 137 */         apunte = new TipoApunte(id_apunte, this.res.getInt(2), this.res.getInt(3), this.res.getString(4), this.res.getString(5), this.res.getDouble(6));
/*     */       }
/*     */ 
/* 140 */       this.res.close();
/*     */     } catch (SQLException e) {
/* 142 */       e.printStackTrace();
/*     */     }
/* 144 */     return apunte;
/*     */   }
/*     */ 
/*     */   public ArrayList<TipoApunte> apuntesAsiento(int idAsiento) {
/* 148 */     ArrayList apuntes = new ArrayList();
/*     */     try
/*     */     {
/* 151 */       this.res = this.con.getRes("SELECT * FROM apte" + this.ejercicio + " WHERE id_asiento=" + idAsiento + " ORDER BY id_apunte");
/*     */ 
/* 153 */       while (this.res.next()) {
/* 154 */         TipoApunte apunte = new TipoApunte(this.res.getInt(1), this.res.getInt(2), this.res.getInt(3), this.res.getString(4), this.res.getString(5), this.res.getDouble(6));
/*     */ 
/* 156 */         apuntes.add(apunte);
/*     */       }
/* 158 */       this.res.close();
/*     */     } catch (SQLException e) {
/* 160 */       e.printStackTrace();
/*     */     }
/* 162 */     return apuntes;
/*     */   }
/*     */ 
/*     */   public void cerrarRs() {
/* 166 */     if (this.res != null) {
/*     */       try {
/* 168 */         this.res.close();
/*     */       } catch (SQLException sqlEx) {
/*     */       }
/* 171 */       this.res = null;
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.ManejoApuntes
 * JD-Core Version:    0.6.2
 */