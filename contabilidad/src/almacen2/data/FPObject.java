/*    */ package almacen2.data;
/*    */ 
/*    */ public class FPObject
/*    */ {
/*    */   private int id;
/*    */   private String nombre;
/*    */   private int subcuenta1;
/*    */   private int subcuenta2;
/*    */ 
/*    */   public FPObject(int id, String nombre, int subcuenta1, int subcuenta2)
/*    */   {
/* 12 */     this.id = id;
/* 13 */     this.nombre = nombre;
/* 14 */     this.subcuenta1 = subcuenta1;
/* 15 */     this.subcuenta2 = subcuenta2;
/*    */   }
/*    */ 
/*    */   public FPObject()
/*    */   {
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
/*    */   public int getSubcuenta1() {
/* 39 */     return this.subcuenta1;
/*    */   }
/*    */ 
/*    */   public void setSubcuenta1(int subcuenta1) {
/* 43 */     this.subcuenta1 = subcuenta1;
/*    */   }
/*    */ 
/*    */   public int getSubcuenta2() {
/* 47 */     return this.subcuenta2;
/*    */   }
/*    */ 
/*    */   public void setSubcuenta2(int subcuenta2) {
/* 51 */     this.subcuenta2 = subcuenta2;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 56 */     return getId() + " : " + getNombre();
/*    */   }
/*    */ 
/*    */   public boolean equals(Object obj)
/*    */   {
/* 61 */     if (obj == null) {
/* 62 */       return false;
/*    */     }
/* 64 */     if (getClass() != obj.getClass()) {
/* 65 */       return false;
/*    */     }
/* 67 */     FPObject other = (FPObject)obj;
/* 68 */     if (this.id != other.id) {
/* 69 */       return false;
/*    */     }
/* 71 */     if (this.nombre == null ? other.nombre != null : !this.nombre.equals(other.nombre)) {
/* 72 */       return false;
/*    */     }
/* 74 */     if (this.subcuenta1 != other.subcuenta1) {
/* 75 */       return false;
/*    */     }
/* 77 */     if (this.subcuenta2 != other.subcuenta2) {
/* 78 */       return false;
/*    */     }
/* 80 */     return true;
/*    */   }
/*    */ 
/*    */   public int hashCode()
/*    */   {
/* 85 */     int hash = 7;
/* 86 */     hash = 37 * hash + this.id;
/* 87 */     return hash;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.data.FPObject
 * JD-Core Version:    0.6.2
 */