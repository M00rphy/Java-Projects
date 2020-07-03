/*    */ package almacen2.data;
/*    */ 
/*    */ public class PIOObject
/*    */ {
/*    */   private int id;
/*    */   private String fecha;
/*    */   private double importe;
/*    */   private int IO;
/*    */   private int almacen;
/*    */ 
/*    */   public PIOObject(int id, String fecha, double importe, int io, int almacen)
/*    */   {
/* 13 */     this.id = id;
/* 14 */     this.fecha = fecha;
/* 15 */     this.importe = importe;
/* 16 */     this.IO = io;
/* 17 */     this.almacen = almacen;
/*    */   }
/*    */ 
/*    */   public PIOObject()
/*    */   {
/*    */   }
/*    */ 
/*    */   public int getId() {
/* 25 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(int id) {
/* 29 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public String getFecha() {
/* 33 */     return this.fecha;
/*    */   }
/*    */ 
/*    */   public void setFecha(String fecha) {
/* 37 */     this.fecha = fecha;
/*    */   }
/*    */ 
/*    */   public double getImporte() {
/* 41 */     return this.importe;
/*    */   }
/*    */ 
/*    */   public void setImporte(double importe) {
/* 45 */     this.importe = importe;
/*    */   }
/*    */ 
/*    */   public int getIO() {
/* 49 */     return this.IO;
/*    */   }
/*    */ 
/*    */   public void setIO(int io) {
/* 53 */     this.IO = io;
/*    */   }
/*    */ 
/*    */   public int getAlmacen() {
/* 57 */     return this.almacen;
/*    */   }
/*    */ 
/*    */   public void setAlmacen(int almacen) {
/* 61 */     this.almacen = almacen;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.data.PIOObject
 * JD-Core Version:    0.6.2
 */