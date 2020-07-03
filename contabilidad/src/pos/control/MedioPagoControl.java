/*     */ package pos.control;
/*     */ 
/*     */ import almacen2.data.MySQLConection;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import pos.model.MedioPago;
/*     */ 
/*     */ public class MedioPagoControl
/*     */ {
/*     */   private MySQLConection con;
/*     */   private ResultSet res;
/*     */ 
/*     */   public MedioPagoControl(MySQLConection con)
/*     */   {
/*  25 */     this.con = con;
/*     */   }
/*     */ 
/*     */   public int crear(MedioPago medioPago) {
/*  29 */     int id = -1;
/*     */     try {
/*  31 */       String cadenaSQL = "INSERT INTO mediospago (nombre,cuentacobro,comision,cuentacomision) VALUES(?,?,?,?)";
/*  32 */       PreparedStatement ps = this.con.getCon().prepareStatement(cadenaSQL);
/*  33 */       ps.setString(1, medioPago.getNombre());
/*  34 */       ps.setInt(2, medioPago.getCuentaCobro());
/*  35 */       ps.setDouble(3, medioPago.getComision());
/*  36 */       ps.setInt(4, medioPago.getCuentaComision());
/*     */ 
/*  38 */       ps.execute();
/*  39 */       cadenaSQL = "SELECT LAST_INSERT_ID() FROM mediospago";
/*  40 */       ps = this.con.getCon().prepareStatement(cadenaSQL);
/*  41 */       this.res = ps.executeQuery();
/*  42 */       if (this.res.next())
/*  43 */         id = this.res.getInt(1);
/*     */     }
/*     */     catch (SQLException exc) {
/*  46 */       exc.printStackTrace();
/*     */     }
/*  48 */     return id;
/*     */   }
/*     */ 
/*     */   public boolean modificar(MedioPago medioPago) {
/*  52 */     boolean modificado = false;
/*     */     try {
/*  54 */       String cadenaSQL = "UPDATE mediospago SET nombre = ?, cuentacobro = ?, comision = ?,cuentacomision = ? WHERE id = ?";
/*     */ 
/*  56 */       PreparedStatement ps = this.con.getCon().prepareStatement(cadenaSQL);
/*  57 */       ps.setString(1, medioPago.getNombre());
/*  58 */       ps.setInt(2, medioPago.getCuentaCobro());
/*  59 */       ps.setDouble(3, medioPago.getComision());
/*  60 */       ps.setInt(4, medioPago.getCuentaComision());
/*  61 */       ps.setInt(5, medioPago.getId().intValue());
/*     */ 
/*  63 */       int result = ps.executeUpdate();
/*  64 */       if (result > 0)
/*  65 */         modificado = true;
/*     */     }
/*     */     catch (SQLException exc) {
/*  68 */       exc.printStackTrace();
/*     */     }
/*  70 */     return modificado;
/*     */   }
/*     */ 
/*     */   public boolean borrar(MedioPago medioPago) {
/*  74 */     boolean borrado = false;
/*     */     try {
/*  76 */       String cadenaSQL = "DELETE FROM mediospago WHERE id = ?";
/*  77 */       PreparedStatement ps = this.con.getCon().prepareStatement(cadenaSQL);
/*  78 */       ps.setInt(1, medioPago.getId().intValue());
/*  79 */       int result = ps.executeUpdate();
/*  80 */       if (result > 0)
/*  81 */         borrado = true;
/*     */     }
/*     */     catch (SQLException exc) {
/*  84 */       exc.printStackTrace();
/*     */     }
/*  86 */     return borrado;
/*     */   }
/*     */ 
/*     */   public MedioPago getMedioPago(int id) {
/*  90 */     MedioPago medioPago = null;
/*     */     try {
/*  92 */       String cadenaSQL = "SELECT * FROM mediospago WHERE id = " + id;
/*  93 */       this.res = this.con.getRes(cadenaSQL);
/*  94 */       if (this.res.next()) {
/*  95 */         String nombre = this.res.getString(2);
/*  96 */         int cuentaCobro = this.res.getInt(3);
/*  97 */         double comision = this.res.getDouble(4);
/*  98 */         int cuentaComision = this.res.getInt(5);
/*  99 */         medioPago = new MedioPago(Integer.valueOf(id), nombre, cuentaCobro, comision, cuentaComision);
/*     */       }
/*     */     }
/*     */     catch (SQLException exc) {
/* 103 */       exc.printStackTrace();
/*     */     }
/* 105 */     return medioPago;
/*     */   }
/*     */ 
/*     */   public ArrayList<MedioPago> getTodosMediosPago() {
/* 109 */     ArrayList mediosPago = new ArrayList();
/*     */     try {
/* 111 */       String cadenaSQL = "SELECT * FROM mediospago ORDER BY nombre";
/* 112 */       MedioPago medioPago = null;
/* 113 */       this.res = this.con.getRes(cadenaSQL);
/* 114 */       while (this.res.next()) {
/* 115 */         Integer id = Integer.valueOf(this.res.getInt(1));
/* 116 */         String nombre = this.res.getString(2);
/* 117 */         int cuentaCobro = this.res.getInt(3);
/* 118 */         double comision = this.res.getDouble(4);
/* 119 */         int cuentaComision = this.res.getInt(5);
/* 120 */         medioPago = new MedioPago(id, nombre, cuentaCobro, comision, cuentaComision);
/*     */ 
/* 122 */         mediosPago.add(medioPago);
/*     */       }
/*     */     }
/*     */     catch (SQLException exc) {
/* 126 */       exc.printStackTrace();
/*     */     }
/* 128 */     return mediosPago;
/*     */   }
/*     */ 
/*     */   public void cerrarRs() {
/* 132 */     if (this.res != null) {
/*     */       try {
/* 134 */         this.res.close(); } catch (SQLException sqlEx) {
/*     */       }
/* 136 */       this.res = null;
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.control.MedioPagoControl
 * JD-Core Version:    0.6.2
 */