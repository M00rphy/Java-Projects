/*    */ package contaes.manejoDatos;
/*    */ 
/*    */ public class TipoApunteModelo
/*    */ {
/*    */   private int id;
/*    */   private int aPatronP_id;
/*    */   private int cuenta;
/*    */   private double importe;
/*    */   private String CA;
/*    */ 
/*    */   public TipoApunteModelo()
/*    */   {
/*    */   }
/*    */ 
/*    */   public TipoApunteModelo(int id, int patronP_id, int cuenta, double importe, String ca)
/*    */   {
/* 23 */     this.id = id;
/* 24 */     this.aPatronP_id = patronP_id;
/* 25 */     this.cuenta = cuenta;
/* 26 */     this.importe = importe;
/* 27 */     this.CA = ca;
/*    */   }
/*    */ 
/*    */   public int getAPatronP_id()
/*    */   {
/* 34 */     return this.aPatronP_id;
/*    */   }
/*    */ 
/*    */   public void setAPatronP_id(int patronP_id)
/*    */   {
/* 41 */     this.aPatronP_id = patronP_id;
/*    */   }
/*    */ 
/*    */   public String getCA()
/*    */   {
/* 48 */     return this.CA;
/*    */   }
/*    */ 
/*    */   public void setCA(String ca)
/*    */   {
/* 55 */     this.CA = ca;
/*    */   }
/*    */ 
/*    */   public int getCuenta()
/*    */   {
/* 62 */     return this.cuenta;
/*    */   }
/*    */ 
/*    */   public void setCuenta(int cuenta)
/*    */   {
/* 69 */     this.cuenta = cuenta;
/*    */   }
/*    */ 
/*    */   public int getId()
/*    */   {
/* 76 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(int id)
/*    */   {
/* 83 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public double getImporte()
/*    */   {
/* 90 */     return this.importe;
/*    */   }
/*    */ 
/*    */   public void setImporte(double importe)
/*    */   {
/* 97 */     this.importe = importe;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.TipoApunteModelo
 * JD-Core Version:    0.6.2
 */