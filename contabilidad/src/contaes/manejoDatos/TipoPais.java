/*    */ package contaes.manejoDatos;
/*    */ 
/*    */ public class TipoPais
/*    */ {
/*    */   private int id;
/*    */   private String nombre;
/*    */   private int situacion;
/*    */ 
/*    */   public TipoPais(int id, String nombre, int situacion)
/*    */   {
/* 19 */     this.id = id;
/* 20 */     this.nombre = nombre;
/* 21 */     this.situacion = situacion;
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
/*    */   public String getNombre() {
/* 33 */     return this.nombre;
/*    */   }
/*    */ 
/*    */   public void setNombre(String nombre) {
/* 37 */     this.nombre = nombre;
/*    */   }
/*    */ 
/*    */   public int getSituacion() {
/* 41 */     return this.situacion;
/*    */   }
/*    */ 
/*    */   public void setSituacion(int situacion) {
/* 45 */     this.situacion = situacion;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 50 */     return this.nombre;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.TipoPais
 * JD-Core Version:    0.6.2
 */