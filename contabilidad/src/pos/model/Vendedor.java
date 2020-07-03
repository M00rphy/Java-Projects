/*    */ package pos.model;
/*    */ 
/*    */ public class Vendedor
/*    */ {
/*    */   private Integer id;
/*    */   private String nombre;
/*    */ 
/*    */   public Vendedor(Integer id, String nombre)
/*    */   {
/* 28 */     this.id = id;
/* 29 */     this.nombre = nombre;
/*    */   }
/*    */ 
/*    */   public Integer getId() {
/* 33 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Integer id) {
/* 37 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public String getNombre() {
/* 41 */     return this.nombre;
/*    */   }
/*    */ 
/*    */   public void setNombre(String nombre) {
/* 45 */     this.nombre = nombre;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 50 */     return this.nombre;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.model.Vendedor
 * JD-Core Version:    0.6.2
 */