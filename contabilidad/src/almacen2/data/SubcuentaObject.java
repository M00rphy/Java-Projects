/*    */ package almacen2.data;
/*    */ 
/*    */ public class SubcuentaObject
/*    */ {
/*    */   private Integer codigo;
/*    */   private String nombre;
/*    */ 
/*    */   public SubcuentaObject(Integer codigo, String nombre)
/*    */   {
/* 18 */     this.codigo = codigo;
/* 19 */     this.nombre = nombre;
/*    */   }
/*    */ 
/*    */   public Integer getCodigo() {
/* 23 */     return this.codigo;
/*    */   }
/*    */ 
/*    */   public void setCodigo(Integer codigo) {
/* 27 */     this.codigo = codigo;
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
/* 40 */     return this.codigo + " - " + this.nombre;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.data.SubcuentaObject
 * JD-Core Version:    0.6.2
 */