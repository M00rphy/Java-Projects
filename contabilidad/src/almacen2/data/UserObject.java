/*    */ package almacen2.data;
/*    */ 
/*    */ public class UserObject
/*    */ {
/*    */   private String nombre;
/*    */   private String contra;
/*    */   private String dir;
/*    */   private int empresa;
/*    */ 
/*    */   public UserObject(String nombre, String contra, String dir, int empresa)
/*    */   {
/* 12 */     this.nombre = nombre;
/* 13 */     this.contra = contra;
/* 14 */     this.dir = dir;
/* 15 */     this.empresa = empresa;
/*    */   }
/*    */ 
/*    */   public String getNombre() {
/* 19 */     return this.nombre;
/*    */   }
/*    */ 
/*    */   public String getContra() {
/* 23 */     return this.contra;
/*    */   }
/*    */ 
/*    */   public String getDir() {
/* 27 */     return this.dir;
/*    */   }
/*    */ 
/*    */   public int getEmpresa() {
/* 31 */     return this.empresa;
/*    */   }
/*    */ 
/*    */   public void setEmpresa(int empresa) {
/* 35 */     this.empresa = empresa;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.data.UserObject
 * JD-Core Version:    0.6.2
 */