/*     */ package almacen2.data;
/*     */ 
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ManejadorPIO
/*     */ {
/*  11 */   private MySQLConection conexion = null;
/*     */   private ResultSet res;
/*  13 */   private List<PIOObject> pio = new ArrayList();
/*     */   private String referencia;
/*     */ 
/*     */   public ManejadorPIO(MySQLConection conexion, String referencia)
/*     */   {
/*  18 */     this.conexion = conexion;
/*  19 */     this.referencia = referencia;
/*     */   }
/*     */ 
/*     */   public void crearLista() {
/*  23 */     this.pio.clear();
/*     */     try {
/*  25 */       this.res = this.conexion.getRes("SELECT id,fecha,importe,io,almacen FROM PIO WHERE referencia ='" + this.referencia + "' ORDER BY fecha");
/*  26 */       while (this.res.next())
/*  27 */         this.pio.add(new PIOObject(this.res.getInt(1), this.res.getString(2), this.res.getDouble(3), this.res.getInt(4), this.res.getInt(5)));
/*     */     }
/*     */     catch (SQLException e) {
/*  30 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean nuevaIO(PIOObject nuevo) {
/*     */     try {
/*  36 */       this.conexion.getSentencia().executeUpdate("INSERT INTO PIO (referencia,fecha,importe,io,almacen) VALUES ('" + this.referencia + "','" + nuevo.getFecha() + "'," + nuevo.getImporte() + "," + nuevo.getIO() + "," + nuevo.getAlmacen() + ")");
/*     */     }
/*     */     catch (SQLException e)
/*     */     {
/*  40 */       e.printStackTrace();
/*  41 */       return false;
/*     */     }
/*  43 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean modificarIO(PIOObject nuevo) {
/*  47 */     boolean retorno = false;
/*     */     try {
/*  49 */       String cadenaSQL = "UPDATE PIO SET  referencia = ?, fecha = ?, importe = ?, io = ?, almacen = ?  WHERE id = ?";
/*     */ 
/*  52 */       PreparedStatement ps = this.conexion.getCon().prepareStatement(cadenaSQL);
/*  53 */       ps.setString(1, this.referencia);
/*  54 */       ps.setString(2, nuevo.getFecha());
/*  55 */       ps.setDouble(3, nuevo.getImporte());
/*  56 */       ps.setInt(4, nuevo.getIO());
/*  57 */       ps.setInt(5, nuevo.getAlmacen());
/*  58 */       ps.setInt(6, nuevo.getId());
/*  59 */       int result = ps.executeUpdate();
/*  60 */       if (result > 0)
/*  61 */         retorno = true;
/*     */     }
/*     */     catch (SQLException e) {
/*  64 */       e.printStackTrace();
/*  65 */       return retorno;
/*     */     }
/*  67 */     return retorno;
/*     */   }
/*     */ 
/*     */   public boolean eliminarId(int id) {
/*     */     try {
/*  72 */       this.conexion.getSentencia().executeUpdate("DELETE FROM PIO WHERE id = " + id);
/*     */     } catch (SQLException e) {
/*  74 */       e.printStackTrace();
/*  75 */       return false;
/*     */     }
/*  77 */     return true;
/*     */   }
/*     */ 
/*     */   public int calcularStock() {
/*  81 */     int stock = 0;
/*     */     try {
/*  83 */       this.res = this.conexion.getRes("SELECT SUM(io) FROM PIO WHERE referencia ='" + this.referencia + "'");
/*  84 */       if (this.res.next())
/*  85 */         stock = this.res.getInt(1);
/*     */     }
/*     */     catch (SQLException e) {
/*  88 */       e.printStackTrace();
/*     */     }
/*  90 */     return stock;
/*     */   }
/*     */ 
/*     */   public int calcularStockAlmacen(int almacen) {
/*  94 */     int stock = 0;
/*     */     try {
/*  96 */       this.res = this.conexion.getRes("SELECT SUM(io) FROM PIO WHERE referencia ='" + this.referencia + "'" + " AND almacen = " + almacen);
/*     */ 
/*  98 */       if (this.res.next())
/*  99 */         stock = this.res.getInt(1);
/*     */     }
/*     */     catch (SQLException e) {
/* 102 */       e.printStackTrace();
/*     */     }
/* 104 */     return stock;
/*     */   }
/*     */ 
/*     */   public ArrayList<AlmacenInterno> getAlmacenesConStock() {
/* 108 */     ArrayList almacenes = new ArrayList();
/* 109 */     ManejadorAlmacenInterno mAI = new ManejadorAlmacenInterno(this.conexion);
/* 110 */     almacenes = mAI.getAlmacenes();
/* 111 */     mAI.cerrarRs();
/* 112 */     for (AlmacenInterno almacenInterno :(List<AlmacenInterno>) almacenes) {
/* 113 */       almacenInterno.setStock(calcularStockAlmacen(almacenInterno.getId().intValue()));
/*     */     }
/* 115 */     return almacenes;
/*     */   }
/*     */ 
/*     */   public double calcularBeneficio() {
/* 119 */     double beneficio = 0.0D;
/*     */     try {
/* 121 */       this.res = this.conexion.getRes("SELECT SUM(importe*io) FROM PIO WHERE referencia = '" + this.referencia + "'");
/* 122 */       if (this.res.next())
/* 123 */         beneficio = -this.res.getDouble(1);
/*     */     }
/*     */     catch (SQLException e) {
/* 126 */       e.printStackTrace();
/*     */     }
/* 128 */     return beneficio;
/*     */   }
/*     */ 
/*     */   public List<PIOObject> getPio() {
/* 132 */     return this.pio;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.data.ManejadorPIO
 * JD-Core Version:    0.6.2
 */