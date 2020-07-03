/*    */ package facturacion.model;
/*    */ 
/*    */ public class Proveedor
/*    */ {
/*    */   int id;
/*    */   String nombre;
/*    */ 
/*    */   public Proveedor(int id, String nombre)
/*    */   {
/* 18 */     this.id = id;
/* 19 */     this.nombre = nombre;
/*    */   }
/*    */ 
/*    */   public int getId() {
/* 23 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(int id) {
/* 27 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public String getNombre() {
/* 31 */     return this.nombre;
/*    */   }
/*    */ 
/*    */   public void setNombre(String nombre) {
/* 35 */     this.nombre = nombre;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 40 */     return this.nombre;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     facturacion.model.Proveedor
 * JD-Core Version:    0.6.2
 */