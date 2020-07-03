/*    */ package contaes.manejoDatos;
/*    */ 
/*    */ public class TipoBanco
/*    */ {
/*    */   private int codigo;
/*    */   private String nombre;
/* 16 */   private String ccc = "";
/*    */ 
/*    */   public TipoBanco(int codigo, String nombre) {
/* 19 */     this.codigo = codigo;
/* 20 */     this.nombre = nombre;
/*    */   }
/*    */ 
/*    */   public String getCcc() {
/* 24 */     return this.ccc;
/*    */   }
/*    */ 
/*    */   public void setCcc(String ccc) {
/* 28 */     this.ccc = ccc;
/*    */   }
/*    */ 
/*    */   public int getCodigo() {
/* 32 */     return this.codigo;
/*    */   }
/*    */ 
/*    */   public void setCodigo(int codigo) {
/* 36 */     this.codigo = codigo;
/*    */   }
/*    */ 
/*    */   public String getNombre() {
/* 40 */     return this.nombre;
/*    */   }
/*    */ 
/*    */   public void setNombre(String nombre) {
/* 44 */     this.nombre = nombre;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 49 */     return this.nombre;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.TipoBanco
 * JD-Core Version:    0.6.2
 */