/*    */ package almacen2.data;
/*    */ 
/*    */ public class RDObject
/*    */ {
/*    */   private String ref;
/*    */   private String descrip;
/*    */ 
/*    */   public RDObject(String ref, String descrip)
/*    */   {
/* 10 */     this.ref = ref;
/* 11 */     this.descrip = descrip;
/*    */   }
/*    */ 
/*    */   public RDObject()
/*    */   {
/*    */   }
/*    */ 
/*    */   public String getRef() {
/* 19 */     return this.ref;
/*    */   }
/*    */ 
/*    */   public void setRef(String ref) {
/* 23 */     this.ref = ref;
/*    */   }
/*    */ 
/*    */   public String getDescrip() {
/* 27 */     return this.descrip;
/*    */   }
/*    */ 
/*    */   public void setDescrip(String descrip) {
/* 31 */     this.descrip = descrip;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.data.RDObject
 * JD-Core Version:    0.6.2
 */