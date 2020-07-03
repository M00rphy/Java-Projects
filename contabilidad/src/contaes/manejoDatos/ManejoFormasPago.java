/*     */ package contaes.manejoDatos;
/*     */ 
/*     */ import contaes.manejoDatos.auxiliar.MySQLConection;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class ManejoFormasPago
/*     */ {
/*     */   private MySQLConection con;
/*     */   private ResultSet res;
/*     */ 
/*     */   public ManejoFormasPago(MySQLConection con)
/*     */   {
/*  23 */     this.con = con;
/*     */   }
/*     */ 
/*     */   public int crear(TipoFormaPago fPago)
/*     */   {
/*  29 */     int id = -1;
/*     */     try {
/*  31 */       this.con.getSentencia().executeUpdate("INSERT INTO formasPago (pago,diaFijoPago, diasEntrePagos, diasPrimerPago, numeroPagos) VALUES('" + fPago.getPago() + "'," + fPago.getDiaFijoPago() + "," + fPago.getDiasEntrePagos() + "," + fPago.getDiasPrimerPago() + "," + fPago.getNumeroPagos() + ")");
/*     */ 
/*  39 */       this.res = this.con.getRes("SELECT MAX(idFormaPago) FROM formasPago");
/*  40 */       if (this.res.next()) {
/*  41 */         id = this.res.getInt(1);
/*     */       }
/*  43 */       this.res.close();
/*     */     } catch (SQLException e) {
/*  45 */       e.printStackTrace();
/*     */     }
/*  47 */     return id;
/*     */   }
/*     */ 
/*     */   public boolean borrar(int idFormaPago) {
/*     */     try {
/*  52 */       this.con.getSentencia().executeUpdate("DELETE FROM formasPago WHERE idFormaPago=" + idFormaPago);
/*     */     }
/*     */     catch (SQLException e) {
/*  55 */       e.printStackTrace();
/*  56 */       return false;
/*     */     }
/*  58 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean modificar(TipoFormaPago fPago) {
/*     */     try {
/*  63 */       this.con.getSentencia().executeUpdate("UPDATE formasPago SET pago = '" + fPago.getPago() + "'," + " diaFijoPago = " + fPago.getDiaFijoPago() + "," + " diasEntrePagos = " + fPago.getDiasEntrePagos() + "," + " diasPrimerPago = " + fPago.getDiasPrimerPago() + "," + " numeroPagos = " + fPago.getNumeroPagos() + " WHERE idFormaPago = " + fPago.getIdFormaPago());
/*     */     }
/*     */     catch (SQLException e)
/*     */     {
/*  71 */       e.printStackTrace();
/*  72 */       return false;
/*     */     }
/*  74 */     return true;
/*     */   }
/*     */ 
/*     */   public TipoFormaPago getFormaPago(int idFormaPago) {
/*  78 */     TipoFormaPago fPago = null;
/*     */     try {
/*  80 */       this.res = this.con.getRes("SELECT * FROM formasPago WHERE idFormaPago = " + idFormaPago);
/*  81 */       if (this.res.next()) {
/*  82 */         fPago = new TipoFormaPago(Integer.valueOf(idFormaPago), this.res.getString(2), Integer.valueOf(this.res.getInt(3)), Integer.valueOf(this.res.getInt(4)), Integer.valueOf(this.res.getInt(5)), Integer.valueOf(this.res.getInt(6)));
/*     */       }
/*     */ 
/*  85 */       this.res.close();
/*     */     }
/*     */     catch (SQLException e) {
/*  88 */       e.printStackTrace();
/*     */     }
/*  90 */     return fPago;
/*     */   }
/*     */ 
/*     */   public TipoFormaPago getFirstFormaPago() {
/*  94 */     TipoFormaPago fPago = null;
/*     */     try {
/*  96 */       this.res = this.con.getRes("SELECT * FROM formasPago ORDER BY pago");
/*  97 */       if (this.res.next()) {
/*  98 */         fPago = new TipoFormaPago(Integer.valueOf(this.res.getInt(1)), this.res.getString(2), Integer.valueOf(this.res.getInt(3)), Integer.valueOf(this.res.getInt(4)), Integer.valueOf(this.res.getInt(5)), Integer.valueOf(this.res.getInt(6)));
/*     */       }
/*     */ 
/* 101 */       this.res.close();
/*     */     }
/*     */     catch (SQLException e) {
/* 104 */       e.printStackTrace();
/*     */     }
/* 106 */     return fPago;
/*     */   }
/*     */ 
/*     */   public ArrayList<TipoFormaPago> getFormasPago() {
/* 110 */     ArrayList formasPago = new ArrayList();
/*     */     try {
/* 112 */       this.res = this.con.getRes("SELECT * FROM formasPago ORDER BY pago");
/* 113 */       while (this.res.next()) {
/* 114 */         TipoFormaPago fPago = new TipoFormaPago(Integer.valueOf(this.res.getInt(1)), this.res.getString(2), Integer.valueOf(this.res.getInt(3)), Integer.valueOf(this.res.getInt(4)), Integer.valueOf(this.res.getInt(5)), Integer.valueOf(this.res.getInt(6)));
/*     */ 
/* 116 */         formasPago.add(fPago);
/*     */       }
/* 118 */       this.res.close();
/*     */     }
/*     */     catch (SQLException e) {
/* 121 */       e.printStackTrace();
/*     */     }
/* 123 */     return formasPago;
/*     */   }
/*     */ 
/*     */   public void cerrarRs() {
/* 127 */     if (this.res != null) {
/*     */       try {
/* 129 */         this.res.close(); } catch (SQLException sqlEx) {
/*     */       }
/* 131 */       this.res = null;
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.ManejoFormasPago
 * JD-Core Version:    0.6.2
 */