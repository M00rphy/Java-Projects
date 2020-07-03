/*    */ package almacen2.data.pedidos;
/*    */ 
/*    */ public class BusquedaObject
/*    */ {
/*    */   private Integer id;
/*    */   private String codigo;
/*    */   private String fecha;
/*    */   private String proveedor;
/*    */   private int sumaPiezas;
/*    */   private double sumaCoste;
/*    */ 
/*    */   public BusquedaObject(Integer id, String codigo, String fecha, String proveedor, int sumaPiezas, double sumaCoste)
/*    */   {
/* 15 */     this.id = id;
/* 16 */     this.codigo = codigo;
/* 17 */     this.fecha = fecha;
/* 18 */     this.proveedor = proveedor;
/* 19 */     this.sumaPiezas = sumaPiezas;
/* 20 */     this.sumaCoste = sumaCoste;
/*    */   }
/*    */ 
/*    */   public Integer getId() {
/* 24 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Integer id) {
/* 28 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public String getCodigo() {
/* 32 */     return this.codigo;
/*    */   }
/*    */   public String getFecha() {
/* 35 */     return this.fecha;
/*    */   }
/*    */   public String getProveedor() {
/* 38 */     return this.proveedor;
/*    */   }
/*    */   public int getSumaPiezas() {
/* 41 */     return this.sumaPiezas;
/*    */   }
/*    */   public double getSumaCoste() {
/* 44 */     return this.sumaCoste;
/*    */   }
/*    */ 
/*    */   public String toString() {
/* 48 */     return getCodigo() + ": " + getFecha() + ", " + getProveedor() + ", " + getSumaPiezas() + " - " + getSumaCoste();
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.data.pedidos.BusquedaObject
 * JD-Core Version:    0.6.2
 */