/*    */ package almacen2.data;
/*    */ 
/*    */ public class AlmacenInterno
/*    */ {
/*    */   private Integer id;
/*    */   private String nombre;
/* 17 */   private int stock = 0;
/*    */ 
/*    */   public AlmacenInterno(Integer id, String nombre) {
/* 20 */     this.id = id;
/* 21 */     this.nombre = nombre;
/*    */   }
/*    */ 
/*    */   public Integer getId() {
/* 25 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Integer id) {
/* 29 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public String getNombre() {
/* 33 */     return this.nombre;
/*    */   }
/*    */ 
/*    */   public void setNombre(String nombre) {
/* 37 */     this.nombre = nombre;
/*    */   }
/*    */ 
/*    */   public int getStock() {
/* 41 */     return this.stock;
/*    */   }
/*    */ 
/*    */   public void setStock(int stock) {
/* 45 */     this.stock = stock;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 50 */     return this.id + " - " + this.nombre;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.data.AlmacenInterno
 * JD-Core Version:    0.6.2
 */