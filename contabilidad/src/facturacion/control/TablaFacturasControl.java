/*     */ package facturacion.control;
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
/*     */ 
/*     */ public class TablaFacturasControl
/*     */ {
/*     */   private String base;
/*     */   private String orden;
/*     */   private String seleccion;
/*  14 */   private boolean primero = false;
/*  15 */   private Connection con = null;
/*     */   private Statement sentencia;
/*     */   private int empresa;
/*     */ 
/*     */   public TablaFacturasControl(int empresa, String ejercicio, boolean ventas)
/*     */   {
/*  20 */     this.empresa = empresa;
/*  21 */     String tabla = ventas ? "facturas" : "facturascompras";
/*  22 */     abrirConexion();
/*  23 */     String fechaInicio = new StringBuilder().append(ejercicio).append("01").append("01").toString();
/*  24 */     String fechaFin = new StringBuilder().append(ejercicio).append("12").append("31").toString();
/*  25 */     this.base = new StringBuilder().append("SELECT f.id,f.numero,f.fecha,s.nombre,f.base,f.iva,(f.base+f.iva),f.recargo,f.contabilizada FROM ").append(tabla).append(" f JOIN scta").append(ejercicio).append(" s ON f.cliente=s.codigo WHERE (fecha BETWEEN '").append(fechaInicio).append("' AND '").append(fechaFin).append("') ").toString();
/*     */ 
/*  28 */     this.orden = " ORDER BY f.fecha,f.numero";
/*  29 */     this.seleccion = "";
/*     */   }
/*     */ 
/*     */   private String mandatoSQL()
/*     */   {
/*  37 */     return new StringBuilder().append(this.base).append(this.seleccion).append(this.orden).toString();
/*     */   }
/*     */ 
/*     */   public ResultSet resultado() throws SQLException {
/*  41 */     return this.sentencia.executeQuery(mandatoSQL());
/*     */   }
/*     */ 
/*     */   public void seleccionTotal()
/*     */   {
/*  46 */     this.primero = false;
/*  47 */     this.seleccion = "";
/*     */   }
/*     */ 
/*     */   public void seleccionEstandar(String inicio, String fin, int criterio)
/*     */   {
/*  52 */     if (fin.equals("")) this.seleccion = new StringBuilder().append(this.seleccion).append(seleccionaVarios(inicio, criterio)).toString(); else
/*  53 */       this.seleccion = new StringBuilder().append(this.seleccion).append(seleccionaEntreDos(inicio, fin, criterio)).toString();
/*     */   }
/*     */ 
/*     */   public void seleccionTexto(String campo, String texto) {
/*  57 */     this.seleccion = new StringBuilder().append(this.seleccion).append(primeroSiguiente()).append(campo).append(" LIKE '").append(texto).append("')").toString();
/*     */   }
/*     */ 
/*     */   public void abrirConexion() {
/*     */     try {
/*  62 */       this.con = DriverManager.getConnection(new StringBuilder().append("jdbc:mysql://").append(Inicio.p.getDireccionIP()).append("/contaes").append(this.empresa).toString(), Inicio.p.getUsuario(), Inicio.p.getPassword());
/*     */ 
/*  65 */       this.sentencia = this.con.createStatement();
/*     */     }
/*     */     catch (SQLException exc) {
/*  68 */       System.out.println(exc.getMessage());
/*     */     }
/*     */   }
/*     */ 
/*     */   public void cerrarConexion() {
/*     */     try {
/*  74 */       this.sentencia.close();
/*  75 */       this.con.close();
/*     */     } catch (SQLException e) {
/*  77 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean conexionCerrada() {
/*     */     try {
/*  83 */       return this.con.isClosed();
/*     */     } catch (SQLException e) {
/*  85 */       e.printStackTrace();
/*     */     }
/*  87 */     return true;
/*     */   }
/*     */ 
/*     */   private String primeroSiguiente() {
/*  91 */     if (!this.primero) {
/*  92 */       return " AND (";
/*     */     }
/*     */ 
/*  95 */     this.primero = false;
/*  96 */     return " WHERE (";
/*     */   }
/*     */ 
/*     */   public void seleccionaRecargo(boolean recargo)
/*     */   {
/* 101 */     this.seleccion = new StringBuilder().append(this.seleccion).append(primeroSiguiente()).append("f.recargo = ").append(recargo ? "1)" : "0)").toString();
/*     */   }
/*     */ 
/*     */   public void seleccionaContabilizada(boolean contabilizada)
/*     */   {
/* 106 */     this.seleccion = new StringBuilder().append(this.seleccion).append(primeroSiguiente()).append("f.contabilizada = ").append(contabilizada ? "1)" : "0)").toString();
/*     */   }
/*     */ 
/*     */   private String seleccionaVarios(String cadena, int criterio)
/*     */   {
/* 112 */     ComprobarDato comp = new ComprobarDato();
/* 113 */     if (criterio == 3) return "";
/* 114 */     //if (((criterio < 1 ? 1 : 0) | (criterio > 5 ? 1 : 0) | cadena.equals("")) != 0) return "";
/* 115 */     if (((criterio < 1) || (criterio > 5)|| (cadena.equals("") && criterio!=0)))return "";
String sel = primeroSiguiente();
/* 116 */     while (!cadena.equals("")) {
/* 117 */       int indice = cadena.indexOf(';');
/* 118 */       if (indice >= 0) {
/* 119 */         comp.setDato(cadena.substring(0, indice));
/* 120 */         if (criterio == 1)
/* 121 */           sel = new StringBuilder().append(sel).append(" f.numero='").append(cadena.substring(0, indice)).append("' OR ").toString();
/* 122 */         else if ((criterio == 2) && (comp.esEntero())) {
/* 123 */           sel = new StringBuilder().append(sel).append(" f.cliente=").append(cadena.substring(0, indice)).append(" OR ").toString();
/*     */         }
/* 126 */         else if ((criterio == 4) && (comp.esDoble()))
/* 127 */           sel = new StringBuilder().append(sel).append(" f.base=").append(cadena.substring(0, indice)).append(" OR ").toString();
/* 128 */         else if ((criterio == 5) && (comp.esDoble()))
/* 129 */           sel = new StringBuilder().append(sel).append(" f.iva=").append(cadena.substring(0, indice)).append(" OR ").toString();
/* 130 */         cadena = cadena.substring(indice + 1);
/*     */       }
/*     */       else {
/* 133 */         comp.setDato(cadena.substring(0));
/* 134 */         if (criterio == 1)
/* 135 */           sel = new StringBuilder().append(sel).append(" f.numero=").append(cadena.substring(0)).toString();
/* 136 */         else if ((criterio == 2) && (comp.esEntero())) {
/* 137 */           sel = new StringBuilder().append(sel).append(" f.cliente=").append(cadena.substring(0)).toString();
/*     */         }
/* 140 */         else if ((criterio == 4) && (comp.esDoble()))
/* 141 */           sel = new StringBuilder().append(sel).append(" f.base=").append(cadena.substring(0)).toString();
/* 142 */         else if ((criterio == 5) && (comp.esDoble()))
/* 143 */           sel = new StringBuilder().append(sel).append(" f.iva=").append(cadena.substring(0)).toString();
/* 144 */         sel = new StringBuilder().append(sel).append(")").toString();
/* 145 */         cadena = "";
/*     */       }
/*     */     }
/* 148 */     return sel;
/*     */   }
/*     */ 
/*     */   private String seleccionaEntreDos(String inicio, String fin, int criterio)
/*     */   {
/* 154 */     String sel = primeroSiguiente();
/* 155 */     if (((criterio < 1 ? 1 : 0) | (criterio > 5 ? 1 : 0)) != 0) return "";
/* 156 */     ComprobarDato uno = new ComprobarDato(inicio);
/* 157 */     ComprobarDato dos = new ComprobarDato(fin);
/* 158 */     if (criterio == 1) {
/* 159 */       sel = new StringBuilder().append(sel).append(" f.numero BETWEEN '").append(inicio).append("' AND '").append(fin).append("'").toString();
/*     */     }
/* 161 */     else if ((criterio == 2) && (uno.esEntero()) && (dos.esEntero())) {
/* 162 */       sel = new StringBuilder().append(sel).append(" f.cliente BETWEEN ").append(inicio).append(" AND ").append(fin).toString();
/*     */     }
/* 164 */     else if ((criterio == 3) && (uno.esFecha().equals(inicio)) && (dos.esFecha().equals(fin))) {
/* 165 */       sel = new StringBuilder().append(sel).append(" f.fecha BETWEEN '").append(inicio).append("' AND '").append(fin).append("'").toString();
/*     */     }
/* 167 */     else if ((criterio == 4) && (uno.esDoble()) && (dos.esDoble())) {
/* 168 */       sel = new StringBuilder().append(sel).append(" f.base BETWEEN ").append(inicio).append(" AND ").append(fin).toString();
/*     */     }
/* 170 */     else if ((criterio == 5) && (uno.esDoble()) && (dos.esDoble())) {
/* 171 */       sel = new StringBuilder().append(sel).append(" f.iva BETWEEN ").append(inicio).append(" AND ").append(fin).toString();
/*     */     }
/* 173 */     sel = new StringBuilder().append(sel).append(")").toString();
/* 174 */     return sel;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     facturacion.control.TablaFacturasControl
 * JD-Core Version:    0.6.2
 */