/*     */ package contaes.manejoDatos.funciones;
/*     */ 
/*     */ import contaes.auxiliar.AlinearCadena;
/*     */ import contaes.manejoDatos.auxiliar.MySQLConection;
/*     */ import internationalization.Mensajes;
/*     */ import java.io.PrintStream;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.DecimalFormatSymbols;
/*     */ 
/*     */ public class BuscarVencimientosSinFactura
/*     */ {
/*     */   private ResultSet res;
/*     */   private MySQLConection con;
/*     */   private int[] listaId;
/*     */   private String tabla;
/*     */   private String tablaFacturas;
/*     */   private String lista;
/*     */   private String ejercicio;
/*     */ 
/*     */   public BuscarVencimientosSinFactura(MySQLConection con, boolean esRecibida, String ejercicio)
/*     */   {
/*  25 */     this.con = con;
/*  26 */     this.ejercicio = ejercicio;
/*  27 */     this.tabla = (esRecibida ? "vencimientos" : "vencimientosc");
/*  28 */     this.tablaFacturas = (esRecibida ? "reci" : "emit");
/*  29 */     setLista(crearLista());
/*     */   }
/*     */ 
/*     */   private String crearLista() {
/*  33 */     int i = 0;
/*  34 */     DecimalFormatSymbols formato = new DecimalFormatSymbols();
/*  35 */     formato.setDecimalSeparator(',');
/*  36 */     formato.setPerMill('.');
/*  37 */     DecimalFormat fn = new DecimalFormat("#,###,##0.00", formato);
/*  38 */     StringBuilder listadoV = new StringBuilder();
/*  39 */     listadoV.append("  ");
/*  40 */     listadoV.append(Mensajes.getString("fecha"));
/*  41 */     listadoV.append("        ");
/*  42 */     listadoV.append(Mensajes.getString("factura"));
/*  43 */     listadoV.append("     ");
/*  44 */     listadoV.append(Mensajes.getString("importe"));
/*  45 */     listadoV.append("    ");
/*  46 */     listadoV.append(Mensajes.getString("num"));
/*  47 */     listadoV.append("  ");
/*  48 */     listadoV.append(Mensajes.getString("iniPagado"));
/*  49 */     listadoV.append("   \n\n");
/*  50 */     AlinearCadena alinea = new AlinearCadena();
/*     */     try {
/*  52 */       this.res = this.con.getRes(new StringBuilder().append("SELECT v.fecha, v.factura, v.importe, v.num, v.pagado,COUNT(f.numero) AS facturas,v.ejercicio FROM ").append(this.tabla).append(" v ").append("LEFT JOIN ").append(this.tablaFacturas).append(this.ejercicio).append(" f ").append("ON v.factura=f.numero ").append("GROUP BY v.id ").append("HAVING v.ejercicio = ").append(this.ejercicio).append(" AND facturas = 0").toString());
/*     */ 
/*  59 */       while (this.res.next()) {
/*  60 */         String cadFecha = this.res.getString(1);
/*  61 */         listadoV.append(cadFecha.substring(8));
/*  62 */         listadoV.append("-");
/*  63 */         listadoV.append(cadFecha.substring(5, 7));
/*  64 */         listadoV.append("-");
/*  65 */         listadoV.append(cadFecha.substring(0, 4));
/*  66 */         listadoV.append(" ");
/*  67 */         listadoV.append(alinea.Derecha(this.res.getString(2), 12));
/*  68 */         listadoV.append(alinea.Derecha(fn.format(this.res.getFloat(3)), 10));
/*  69 */         listadoV.append(alinea.Derecha(this.res.getString(4), 6));
/*  70 */         if (this.res.getBoolean(5)) {
/*  71 */           listadoV.append(" ");
/*  72 */           listadoV.append(Mensajes.getString("iniPagado"));
/*     */         }
/*  74 */         listadoV.append("\n");
/*  75 */         i++;
/*     */       }
/*  77 */       if (i > 0) {
/*  78 */         this.listaId = new int[i];
/*  79 */         this.res = this.con.getRes(new StringBuilder().append("SELECT v.id,COUNT(f.numero) AS facturas,v.ejercicio FROM ").append(this.tabla).append(" v ").append("LEFT JOIN ").append(this.tablaFacturas).append(this.ejercicio).append(" f ").append("ON v.factura=f.numero ").append("GROUP BY v.id ").append("HAVING v.ejercicio = ").append(this.ejercicio).append(" AND facturas = 0").toString());
/*     */ 
/*  86 */         int x = 0;
/*  87 */         while (this.res.next()) {
/*  88 */           this.listaId[x] = this.res.getInt(1);
/*  89 */           x++;
/*     */         }
/*  91 */         setListaId(this.listaId);
/*     */       }
/*  93 */       this.res.close();
/*     */     }
/*     */     catch (SQLException exc) {
/*  96 */       System.out.println(exc.getMessage());
/*     */     }
/*  98 */     return listadoV.toString();
/*     */   }
/*     */ 
/*     */   public int[] getListaId()
/*     */   {
/* 105 */     return this.listaId;
/*     */   }
/*     */ 
/*     */   public void setListaId(int[] id)
/*     */   {
/* 112 */     this.listaId = id;
/*     */   }
/*     */ 
/*     */   public String getLista()
/*     */   {
/* 119 */     return this.lista;
/*     */   }
/*     */ 
/*     */   public void setLista(String lista)
/*     */   {
/* 126 */     this.lista = lista;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.funciones.BuscarVencimientosSinFactura
 * JD-Core Version:    0.6.2
 */