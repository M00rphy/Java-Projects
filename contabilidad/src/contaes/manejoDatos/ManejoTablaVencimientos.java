/*     */ package contaes.manejoDatos;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.Puente;
/*     */ import contaes.manejoDatos.funciones.ComprobarDato;
/*     */ import java.io.PrintStream;
/*     */ import java.sql.Connection;
/*     */ import java.sql.DriverManager;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import java.util.GregorianCalendar;
/*     */ 
/*     */ public class ManejoTablaVencimientos
/*     */ {
/*     */   private String tabla;
/*     */   private String base;
/*     */   private String orden;
/*     */   private String seleccion;
/*     */   private int empresa;
/*  18 */   private boolean primero = true;
/*     */   private Connection con;
/*     */   private Statement sentencia;
/*     */ 
/*     */   public ManejoTablaVencimientos(int empresa, String ejercicio, boolean aPagar)
/*     */   {
/*  23 */     this.empresa = empresa;
/*  24 */     abrirConexion();
/*  25 */     this.tabla = (aPagar ? "vencimientos" : "vencimientosc");
/*  26 */     this.base = new StringBuilder().append("SELECT v.id,v.fecha,v.factura,v.fechaf,s.nombre,v.importe,v.cuentap,v.num,v.pagado FROM ").append(this.tabla).append(" v JOIN scta").append(ejercicio).append(" s ON v.cuenta=s.codigo").toString();
/*     */ 
/*  28 */     this.orden = " ORDER BY v.fecha,v.cuenta,v.num";
/*  29 */     this.seleccion = "";
/*     */   }
/*     */ 
/*     */   private String mandatoSQL() {
/*  33 */     return new StringBuilder().append(this.base).append(this.seleccion).append(this.orden).toString();
/*     */   }
/*     */ 
/*     */   public ResultSet resultado() throws SQLException {
/*  37 */     return this.sentencia.executeQuery(mandatoSQL());
/*     */   }
/*     */ 
/*     */   public void seleccionTotal() {
/*  41 */     this.primero = true;
/*  42 */     this.seleccion = "";
/*     */   }
/*     */ 
/*     */   public void seleccionFija(int tipo) {
/*  46 */     GregorianCalendar fI = new GregorianCalendar();
/*  47 */     String fechaI = new StringBuilder().append(Integer.toString(fI.get(1))).append("-").append(Integer.toString(fI.get(2) + 1)).append("-").append(Integer.toString(fI.get(5))).toString();
/*     */ 
/*  50 */     String fechaF = new StringBuilder().append(Integer.toString(fI.get(1))).append("-").append(Integer.toString(fI.get(2) + 1)).append("-").append(Integer.toString(fI.get(5))).toString();
/*     */ 
/*  53 */     if (tipo == 1) {
/*  54 */       fI.add(6, 7);
/*  55 */       fechaF = new StringBuilder().append(Integer.toString(fI.get(1))).append("-").append(Integer.toString(fI.get(2) + 1)).append("-").append(Integer.toString(fI.get(5))).toString();
/*     */     }
/*  59 */     else if (tipo == 2) {
/*  60 */       fI.add(2, 1);
/*  61 */       fechaF = new StringBuilder().append(Integer.toString(fI.get(1))).append("-").append(Integer.toString(fI.get(2) + 1)).append("-").append(Integer.toString(fI.get(5))).toString();
/*     */     }
/*     */ 
/*  65 */     this.primero = true;
/*  66 */     this.seleccion = new StringBuilder().append(primeroSiguiente()).append(" v.pagado = 0 AND v.fecha BETWEEN '").append(fechaI).append("' AND '").append(fechaF).append("')").toString();
/*     */   }
/*     */ 
/*     */   public void seleccionEstandar(String inicio, String fin, int criterio)
/*     */   {
/*  71 */     if (fin.equals("")) this.seleccion = new StringBuilder().append(this.seleccion).append(seleccionaVarios(inicio, criterio)).toString(); else
/*  72 */       this.seleccion = new StringBuilder().append(this.seleccion).append(seleccionaEntreDos(inicio, fin, criterio)).toString();
/*     */   }
/*     */ 
/*     */   public void seleccionTexto(String campo, String texto) {
/*  76 */     this.seleccion = new StringBuilder().append(this.seleccion).append(primeroSiguiente()).append(campo).append(" LIKE '").append(texto).append("')").toString();
/*     */   }
/*     */ 
/*     */   public void seleccionaPagado(boolean pagado) {
/*  80 */     this.seleccion = new StringBuilder().append(this.seleccion).append(primeroSiguiente()).append("v.pagado = ").append(pagado ? "1)" : "0)").toString();
/*     */   }
/*     */ 
/*     */   public void abrirConexion()
/*     */   {
/*     */     try {
/*  86 */       this.con = DriverManager.getConnection(new StringBuilder().append("jdbc:mysql://").append(Inicio.p.getDireccionIP()).append("/contaes").append(this.empresa).toString(), Inicio.p.getUsuario(), Inicio.p.getPassword());
/*     */ 
/*  89 */       this.sentencia = this.con.createStatement();
/*     */     }
/*     */     catch (SQLException exc) {
/*  92 */       System.out.println(exc.getMessage());
/*     */     }
/*     */   }
/*     */ 
/*     */   public void cerrarConexion() {
/*     */     try {
/*  98 */       this.sentencia.close();
/*  99 */       this.con.close();
/*     */     } catch (SQLException e) {
/* 101 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean conexionCerrada() {
/*     */     try {
/* 107 */       return this.con.isClosed();
/*     */     } catch (SQLException e) {
/* 109 */       e.printStackTrace();
/*     */     }
/* 111 */     return true;
/*     */   }
/*     */ 
/*     */   private String primeroSiguiente() {
/* 115 */     if (!this.primero) return " AND (";
/* 116 */     this.primero = false;
/* 117 */     return " WHERE (";
/*     */   }
/*     */ 
/*     */   private String seleccionaEntreDos(String inicio, String fin, int criterio)
/*     */   {
/* 122 */     String sel = primeroSiguiente();
/* 123 */     if (((criterio < 1 ? 1 : 0) | (criterio > 5 ? 1 : 0)) != 0) return "";
/* 124 */     ComprobarDato uno = new ComprobarDato(inicio);
/* 125 */     ComprobarDato dos = new ComprobarDato(fin);
/* 126 */     if ((criterio == 1) && (uno.esFecha().equals(inicio)) && (dos.esFecha().equals(fin))) {
/* 127 */       sel = new StringBuilder().append(sel).append(" v.fechaf BETWEEN '").append(inicio).append("' AND '").append(fin).append("'").toString();
/*     */     }
/* 129 */     else if ((criterio == 2) && (uno.esEntero()) && (dos.esEntero())) {
/* 130 */       sel = new StringBuilder().append(sel).append(" v.cuenta BETWEEN ").append(inicio).append(" AND ").append(fin).toString();
/*     */     }
/* 132 */     else if ((criterio == 3) && (uno.esFecha().equals(inicio)) && (dos.esFecha().equals(fin))) {
/* 133 */       sel = new StringBuilder().append(sel).append(" v.fecha BETWEEN '").append(inicio).append("' AND '").append(fin).append("'").toString();
/*     */     }
/* 135 */     else if ((criterio == 4) && (uno.esDoble()) && (dos.esDoble())) {
/* 136 */       sel = new StringBuilder().append(sel).append(" v.importe BETWEEN ").append(inicio).append(" AND ").append(fin).toString();
/*     */     }
/* 138 */     else if ((criterio == 5) && (uno.esEntero()) && (dos.esEntero())) {
/* 139 */       sel = new StringBuilder().append(sel).append(" v.cuentap BETWEEN ").append(inicio).append(" AND ").append(fin).toString();
/*     */     }
/* 141 */     sel = new StringBuilder().append(sel).append(")").toString();
/* 142 */     return sel;
/*     */   }
/*     */ 
/*     */   private String seleccionaVarios(String cadena, int criterio) {
/* 146 */     ComprobarDato comp = new ComprobarDato();
/* 147 */     if (criterio == 3) return "";
/* 148 */    // if (((criterio < 1 ? 1 : 0) | (criterio > 5 ? 1 : 0) | cadena.equals("")) != 0) return "";
/* 149 */    if (criterio < 1) criterio=1; else criterio= 0;
if (criterio > 5) criterio=1; else criterio= 0;
if (cadena.equals("")&& criterio!= 0) return "";
String sel = primeroSiguiente();
/* 150 */     while (!cadena.equals("")) {
/* 151 */       int indice = cadena.indexOf(';');
/* 152 */        if (indice >= 0) {
/* 153 */         comp.setDato(cadena.substring(0, indice));
/* 154 */         if ((criterio == 1) && (comp.esFecha().equals(cadena.substring(0, indice))))
/* 155 */           sel = new StringBuilder().append(sel).append(" v.fechaf='").append(cadena.substring(0, indice)).append("' OR ").toString();
/* 156 */         else if ((criterio == 2) && (comp.esEntero())) {
/* 157 */           sel = new StringBuilder().append(sel).append(" v.cuenta=").append(cadena.substring(0, indice)).append(" OR ").toString();
/*     */         }
/* 160 */         else if ((criterio == 4) && (comp.esDoble()))
/* 161 */           sel = new StringBuilder().append(sel).append(" v.importe=").append(cadena.substring(0, indice)).append(" OR ").toString();
/* 162 */         else if ((criterio == 5) && (comp.esEntero()))
/* 163 */           sel = new StringBuilder().append(sel).append(" v.cuentap=").append(cadena.substring(0, indice)).append(" OR ").toString();
/* 164 */         cadena = cadena.substring(indice + 1);
/*     */       }
/*     */       else {
/* 167 */         comp.setDato(cadena.substring(0));
/* 168 */         if ((criterio == 1) && (comp.esFecha().equals(cadena.substring(0))))
/* 169 */           sel = new StringBuilder().append(sel).append(" v.fechaf='").append(cadena.substring(0)).append("'").toString();
/* 170 */         else if ((criterio == 2) && (comp.esEntero())) {
/* 171 */           sel = new StringBuilder().append(sel).append(" v.cuenta=").append(cadena.substring(0)).toString();
/*     */         }
/* 174 */         else if ((criterio == 4) && (comp.esDoble()))
/* 175 */           sel = new StringBuilder().append(sel).append(" v.importe=").append(cadena.substring(0)).toString();
/* 176 */         else if ((criterio == 5) && (comp.esEntero()))
/* 177 */           sel = new StringBuilder().append(sel).append(" v.cuentap=").append(cadena.substring(0)).toString();
/* 178 */         sel = new StringBuilder().append(sel).append(")").toString();
/* 179 */         cadena = "";
/*     */       }
/*     */     }
/* 182 */     return sel;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.ManejoTablaVencimientos
 * JD-Core Version:    0.6.2
 */