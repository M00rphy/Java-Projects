/*    */ package contaes.manejoDatos;
/*    */ 
/*    */ public class TipoAsientoModelo
/*    */ {
/*    */   private int id;
/*    */   private String descripcion;
/*    */   private String concepto;
/*    */   private String marca;
/*    */ 
/*    */   public TipoAsientoModelo()
/*    */   {
/*    */   }
/*    */ 
/*    */   public TipoAsientoModelo(int id, String descripcion, String concepto, String marca)
/*    */   {
/* 21 */     this.id = id;
/* 22 */     this.descripcion = descripcion;
/* 23 */     this.concepto = concepto;
/* 24 */     this.marca = marca;
/*    */   }
/*    */ 
/*    */   public String getConcepto()
/*    */   {
/* 31 */     return this.concepto;
/*    */   }
/*    */ 
/*    */   public void setConcepto(String concepto)
/*    */   {
/* 38 */     this.concepto = concepto;
/*    */   }
/*    */ 
/*    */   public String getDescripcion()
/*    */   {
/* 45 */     return this.descripcion;
/*    */   }
/*    */ 
/*    */   public void setDescripcion(String descripcion)
/*    */   {
/* 52 */     this.descripcion = descripcion;
/*    */   }
/*    */ 
/*    */   public int getId()
/*    */   {
/* 59 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(int id)
/*    */   {
/* 66 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public String getMarca()
/*    */   {
/* 73 */     return this.marca;
/*    */   }
/*    */ 
/*    */   public void setMarca(String marca)
/*    */   {
/* 80 */     this.marca = marca;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.TipoAsientoModelo
 * JD-Core Version:    0.6.2
 */