/*     */ package contaes.manejoDatos;
/*     */ 
/*     */ public class TipoAsiento
/*     */ {
/*     */   protected int id_asiento;
/*     */   protected int numero;
/*     */   protected String fecha;
/*     */   private String documento;
/*     */   private String marca;
/*     */ 
/*     */   public TipoAsiento()
/*     */   {
/*     */   }
/*     */ 
/*     */   public TipoAsiento(int id_asiento, int numero, String fecha, String documento, String marca)
/*     */   {
/*  27 */     this.id_asiento = id_asiento;
/*  28 */     this.numero = numero;
/*  29 */     this.fecha = fecha;
/*  30 */     this.documento = documento;
/*  31 */     this.marca = marca;
/*     */   }
/*     */ 
/*     */   public String getDocumento()
/*     */   {
/*  38 */     return this.documento;
/*     */   }
/*     */ 
/*     */   public void setDocumento(String documento)
/*     */   {
/*  45 */     this.documento = documento;
/*     */   }
/*     */ 
/*     */   public String getFecha()
/*     */   {
/*  52 */     return this.fecha;
/*     */   }
/*     */ 
/*     */   public void setFecha(String fecha)
/*     */   {
/*  59 */     this.fecha = fecha;
/*     */   }
/*     */ 
/*     */   public int getId_asiento()
/*     */   {
/*  66 */     return this.id_asiento;
/*     */   }
/*     */ 
/*     */   public void setId_asiento(int id_asiento)
/*     */   {
/*  73 */     this.id_asiento = id_asiento;
/*     */   }
/*     */ 
/*     */   public String getMarca()
/*     */   {
/*  80 */     return this.marca;
/*     */   }
/*     */ 
/*     */   public void setMarca(String marca)
/*     */   {
/*  87 */     this.marca = marca;
/*     */   }
/*     */ 
/*     */   public int getNumero()
/*     */   {
/*  94 */     return this.numero;
/*     */   }
/*     */ 
/*     */   public void setNumero(int numero)
/*     */   {
/* 101 */     this.numero = numero;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.TipoAsiento
 * JD-Core Version:    0.6.2
 */