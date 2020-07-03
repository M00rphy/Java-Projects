/*    */ package pos.model;
/*    */ 
/*    */ public class ResumenAgrupado
/*    */ {
/*    */   private int id;
/*    */   private String nombre;
/*    */   private double importe;
/*    */ 
/*    */   public ResumenAgrupado(int id, String nombre, double importe)
/*    */   {
/* 19 */     this.id = id;
/* 20 */     this.nombre = nombre;
/* 21 */     this.importe = importe;
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
/*    */   public double getImporte() {
/* 33 */     return this.importe;
/*    */   }
/*    */ 
/*    */   public void setImporte(double importe) {
/* 37 */     this.importe = importe;
/*    */   }
/*    */ 
/*    */   public String getNombre() {
/* 41 */     return this.nombre;
/*    */   }
/*    */ 
/*    */   public void setNombre(String nombre) {
/* 45 */     this.nombre = nombre;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.model.ResumenAgrupado
 * JD-Core Version:    0.6.2
 */