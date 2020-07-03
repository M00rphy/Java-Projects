/*    */ package pos.model;
/*    */ 
/*    */ public class MedioPago
/*    */ {
/*    */   private Integer id;
/*    */   private String nombre;
/*    */   private int cuentaCobro;
/*    */   private double comision;
/*    */   private int cuentaComision;
/*    */ 
/*    */   public MedioPago(Integer id, String nombre, int cuentaCobro, double comision, int cuentaComision)
/*    */   {
/* 34 */     this.id = id;
/* 35 */     this.nombre = nombre;
/* 36 */     this.cuentaCobro = cuentaCobro;
/* 37 */     this.comision = comision;
/* 38 */     this.cuentaComision = cuentaComision;
/*    */   }
/*    */ 
/*    */   public double getComision() {
/* 42 */     return this.comision;
/*    */   }
/*    */ 
/*    */   public void setComision(double comision) {
/* 46 */     this.comision = comision;
/*    */   }
/*    */ 
/*    */   public int getCuentaCobro() {
/* 50 */     return this.cuentaCobro;
/*    */   }
/*    */ 
/*    */   public void setCuentaCobro(int cuentaCobro) {
/* 54 */     this.cuentaCobro = cuentaCobro;
/*    */   }
/*    */ 
/*    */   public int getCuentaComision() {
/* 58 */     return this.cuentaComision;
/*    */   }
/*    */ 
/*    */   public void setCuentaComision(int cuentaComision) {
/* 62 */     this.cuentaComision = cuentaComision;
/*    */   }
/*    */ 
/*    */   public Integer getId() {
/* 66 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Integer id) {
/* 70 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public String getNombre() {
/* 74 */     return this.nombre;
/*    */   }
/*    */ 
/*    */   public void setNombre(String nombre) {
/* 78 */     this.nombre = nombre;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 83 */     return this.nombre + " (" + this.cuentaCobro + ")";
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.model.MedioPago
 * JD-Core Version:    0.6.2
 */