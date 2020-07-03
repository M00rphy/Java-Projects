/*    */ package pos.model;
/*    */ 
/*    */ public class ObjetoMultifuncional
/*    */ {
/*    */   private int id;
/*    */   private String nombre;
/*    */ 
/*    */   public ObjetoMultifuncional(int id, String nombre)
/*    */   {
/* 18 */     this.id = id;
/* 19 */     this.nombre = nombre;
/*    */   }
/*    */ 
/*    */   public ObjetoMultifuncional() {
/* 23 */     this.id = -1;
/* 24 */     this.nombre = "";
/*    */   }
/*    */ 
/*    */   public int getId() {
/* 28 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(int id) {
/* 32 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public String getNombre() {
/* 36 */     return this.nombre;
/*    */   }
/*    */ 
/*    */   public void setNombre(String nombre) {
/* 40 */     this.nombre = nombre;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 45 */     return this.nombre;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.model.ObjetoMultifuncional
 * JD-Core Version:    0.6.2
 */