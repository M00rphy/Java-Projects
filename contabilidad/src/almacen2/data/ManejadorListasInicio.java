/*     */ package almacen2.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ManejadorListasInicio
/*     */ {
/*  10 */   private MySQLConection conexion = null;
/*     */   private ResultSet res;
/*  12 */   private FPObject[] familias = null;
/*  13 */   private FPObject[] proveedores = null;
/*  14 */   private List<String> referencias = new ArrayList();
/*     */ 
/*     */   public ManejadorListasInicio(MySQLConection conexion)
/*     */   {
/*  19 */     this.conexion = conexion;
/*  20 */     crearFamilias();
/*  21 */     crearProveedores();
/*     */   }
/*     */ 
/*     */   private void crearFamilias()
/*     */   {
/*  27 */     int total = 0; int indice = 0;
/*     */     try {
/*  29 */       this.res = this.conexion.getRes("SELECT COUNT(id) FROM familias");
/*  30 */       if (this.res.next())
/*  31 */         total = this.res.getInt(1);
/*  32 */       this.familias = new FPObject[total];
/*  33 */       this.res = this.conexion.getRes("SELECT id,nombre,subventas,subcompras FROM familias ORDER by nombre");
/*  34 */       while ((this.res.next()) && (indice < total))
/*  35 */         this.familias[(indice++)] = new FPObject(this.res.getInt(1), this.res.getString(2), this.res.getInt(3), this.res.getInt(4));
/*     */     }
/*     */     catch (SQLException e) {
/*  38 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void crearProveedores() {
/*  43 */     int total = 0; int indice = 0;
/*     */     try {
/*  45 */       this.res = this.conexion.getRes("SELECT COUNT(id) FROM proveedores");
/*  46 */       if (this.res.next())
/*  47 */         total = this.res.getInt(1);
/*  48 */       this.proveedores = new FPObject[total];
/*  49 */       this.res = this.conexion.getRes("SELECT id,nombre,subcuenta FROM proveedores ORDER by nombre");
/*  50 */       while ((this.res.next()) && (indice < total))
/*  51 */         this.proveedores[(indice++)] = new FPObject(this.res.getInt(1), this.res.getString(2), this.res.getInt(1), -1);
/*     */     }
/*     */     catch (SQLException e) {
/*  54 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void crearReferencias(int id, boolean familia) {
/*  59 */     String where = familia ? " WHERE familia = " : " WHERE proveedor = ";
/*  60 */     where = where + id + " ";
/*     */     try {
/*  62 */       this.res = this.conexion.getRes("SELECT referencia FROM Producto " + where + " ORDER BY referencia");
/*  63 */       while (this.res.next())
/*  64 */         this.referencias.add(this.res.getString(1));
/*     */     }
/*     */     catch (SQLException e) {
/*  67 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void actualizarFamiliasyProveedores(boolean familias)
/*     */   {
/*  83 */     if (familias)
/*  84 */       crearFamilias();
/*     */     else
/*  86 */       crearProveedores();
/*     */   }
/*     */ 
/*     */   public void actualizarReferencias(int id, boolean familia) {
/*  90 */     this.referencias.clear();
/*     */ 
/*  92 */     crearReferencias(id, familia);
/*     */   }
/*     */ 
/*     */   public FPObject[] getFamilias()
/*     */   {
/*  97 */     return this.familias;
/*     */   }
/*     */ 
/*     */   public FPObject[] getProveedores() {
/* 101 */     return this.proveedores;
/*     */   }
/*     */ 
/*     */   public List<String> getReferencias() {
/* 105 */     return this.referencias;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.data.ManejadorListasInicio
 * JD-Core Version:    0.6.2
 */