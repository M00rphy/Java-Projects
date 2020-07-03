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
/*     */ 
/*     */ public class ManejoTablaFacturasRecibidas
/*     */ {
/*     */   private String base;
/*     */   private String orden;
/*     */   private String seleccion;
/*  14 */   private boolean primero = true;
/*  15 */   private Connection con = null;
/*     */   private Statement sentencia;
/*     */   private int empresa;
/*     */ 
/*     */   public ManejoTablaFacturasRecibidas(int empresa, String ejercicio)
/*     */   {
/*  20 */     this.empresa = empresa;
/*  21 */     abrirConexion();
/*  22 */     this.base = new StringBuilder().append("SELECT f.numero,f.fecha,f.cuenta,a.numero,f.concepto,f.base,f.iva,f.total,f.importacion FROM reci").append(ejercicio).append(" f JOIN asto").append(ejercicio).append(" a ON f.id_asiento=a.id_asiento").toString();
/*     */ 
/*  25 */     this.orden = " ORDER BY f.fecha,f.numero";
/*  26 */     this.seleccion = "";
/*     */   }
/*     */ 
/*     */   private String mandatoSQL()
/*     */   {
/*  34 */     return new StringBuilder().append(this.base).append(this.seleccion).append(this.orden).toString();
/*     */   }
/*     */ 
/*     */   public ResultSet resultado() throws SQLException {
/*  38 */     return this.sentencia.executeQuery(mandatoSQL());
/*     */   }
/*     */ 
/*     */   public void seleccionTotal()
/*     */   {
/*  43 */     this.primero = true;
/*  44 */     this.seleccion = "";
/*     */   }
/*     */ 
/*     */   public void seleccionarImportaciones(boolean importacion) {
/*  48 */     this.seleccion = new StringBuilder().append(this.seleccion).append(primeroSiguiente()).append(importacion ? " " : "NOT ").append("f.importacion)").toString();
/*     */   }
/*     */ 
/*     */   public void seleccionEstandar(String inicio, String fin, int criterio)
/*     */   {
/*  56 */     if (fin.equals("")) this.seleccion = new StringBuilder().append(this.seleccion).append(seleccionaVarios(inicio, criterio)).toString(); else
/*  57 */       this.seleccion = new StringBuilder().append(this.seleccion).append(seleccionaEntreDos(inicio, fin, criterio)).toString();
/*     */   }
/*     */ 
/*     */   public void seleccionTexto(String campo, String texto) {
/*  61 */     this.seleccion = new StringBuilder().append(this.seleccion).append(primeroSiguiente()).append(campo).append(" LIKE '").append(texto).append("')").toString();
/*     */   }
/*     */ 
/*     */   public void abrirConexion() {
/*     */     try {
/*  66 */       this.con = DriverManager.getConnection(new StringBuilder().append("jdbc:mysql://").append(Inicio.p.getDireccionIP()).append("/contaes").append(this.empresa).toString(), Inicio.p.getUsuario(), Inicio.p.getPassword());
/*     */ 
/*  69 */       this.sentencia = this.con.createStatement();
/*     */     }
/*     */     catch (SQLException exc) {
/*  72 */       System.out.println(exc.getMessage());
/*     */     }
/*     */   }
/*     */ 
/*     */   public void cerrarConexion() {
/*     */     try {
/*  78 */       this.sentencia.close();
/*  79 */       this.con.close();
/*     */     } catch (SQLException e) {
/*  81 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean conexionCerrada() {
/*     */     try {
/*  87 */       return this.con.isClosed();
/*     */     } catch (SQLException e) {
/*  89 */       e.printStackTrace();
/*     */     }
/*  91 */     return true;
/*     */   }
/*     */ 
/*     */   private String primeroSiguiente() {
/*  95 */     if (!this.primero) return " AND (";
/*  96 */     this.primero = false;
/*  97 */     return " WHERE (";
/*     */   }
/*     */ 
/*     */   private String seleccionaVarios(String cadena, int criterio)
/*     */   {
/* 102 */     ComprobarDato comp = new ComprobarDato();
/* 103 */     if (criterio == 3) return "";
/* 104 */    // if (((criterio < 1 ? 1 : 0) | (criterio > 7 ? 1 : 0) | cadena.equals("")) != 0) return "";
/* 105 */     if (criterio < 1) criterio=1; else criterio= 0;
if (criterio > 7) criterio=1; else criterio= 0;
if (cadena.equals("")&& criterio!= 0) return "";
String sel = primeroSiguiente();
/* 106 */     while (!cadena.equals("")) {
/* 107 */       int indice = cadena.indexOf(';');
/* 108 */       if (indice >= 0) {
/* 109 */         comp.setDato(cadena.substring(0, indice));
/* 110 */         if ((criterio == 1) && (comp.esEntero()))
/* 111 */           sel = new StringBuilder().append(sel).append(" a.numero=").append(cadena.substring(0, indice)).append(" OR ").toString();
/* 112 */         else if ((criterio == 2) && (comp.esEntero())) {
/* 113 */           sel = new StringBuilder().append(sel).append(" f.cuenta=").append(cadena.substring(0, indice)).append(" OR ").toString();
/*     */         }
/* 116 */         else if ((criterio == 4) && (comp.esDoble()))
/* 117 */           sel = new StringBuilder().append(sel).append(" f.base=").append(cadena.substring(0, indice)).append(" OR ").toString();
/* 118 */         else if ((criterio == 5) && (comp.esDoble()))
/* 119 */           sel = new StringBuilder().append(sel).append(" f.iva=").append(cadena.substring(0, indice)).append(" OR ").toString();
/* 120 */         else if ((criterio == 6) && (comp.esDoble()))
/* 121 */           sel = new StringBuilder().append(sel).append(" f.total=").append(cadena.substring(0, indice)).append(" OR ").toString();
/* 122 */         else if (criterio == 7)
/* 123 */           sel = new StringBuilder().append(sel).append(" f.numero='").append(cadena.substring(0, indice)).append("' OR ").toString();
/* 124 */         cadena = cadena.substring(indice + 1);
/*     */       }
/*     */       else {
/* 127 */         comp.setDato(cadena.substring(0));
/* 128 */         if ((criterio == 1) && (comp.esEntero()))
/* 129 */           sel = new StringBuilder().append(sel).append(" a.numero=").append(cadena.substring(0)).toString();
/* 130 */         else if ((criterio == 2) && (comp.esEntero())) {
/* 131 */           sel = new StringBuilder().append(sel).append(" f.cuenta=").append(cadena.substring(0)).toString();
/*     */         }
/* 134 */         else if ((criterio == 4) && (comp.esDoble()))
/* 135 */           sel = new StringBuilder().append(sel).append(" f.base=").append(cadena.substring(0)).toString();
/* 136 */         else if ((criterio == 5) && (comp.esDoble()))
/* 137 */           sel = new StringBuilder().append(sel).append(" f.iva=").append(cadena.substring(0)).toString();
/* 138 */         else if ((criterio == 6) && (comp.esDoble()))
/* 139 */           sel = new StringBuilder().append(sel).append(" f.total=").append(cadena.substring(0)).toString();
/* 140 */         else if (criterio == 7)
/* 141 */           sel = new StringBuilder().append(sel).append(" f.numero='").append(cadena.substring(0)).append("'").toString();
/* 142 */         sel = new StringBuilder().append(sel).append(")").toString();
/* 143 */         cadena = "";
/*     */       }
/*     */     }
/* 146 */     return sel;
/*     */   }
/*     */ 
/*     */   private String seleccionaEntreDos(String inicio, String fin, int criterio)
/*     */   {
/* 152 */     String sel = primeroSiguiente();
/* 153 */     if (((criterio < 1 ? 1 : 0) | (criterio > 6 ? 1 : 0)) != 0) return "";
/* 154 */     ComprobarDato uno = new ComprobarDato(inicio);
/* 155 */     ComprobarDato dos = new ComprobarDato(fin);
/* 156 */     if ((criterio == 1) && (uno.esEntero()) && (dos.esEntero())) {
/* 157 */       sel = new StringBuilder().append(sel).append(" a.numero BETWEEN ").append(inicio).append(" AND ").append(fin).toString();
/*     */     }
/* 159 */     else if ((criterio == 2) && (uno.esEntero()) && (dos.esEntero())) {
/* 160 */       sel = new StringBuilder().append(sel).append(" f.cuenta BETWEEN ").append(inicio).append(" AND ").append(fin).toString();
/*     */     }
/* 162 */     else if ((criterio == 3) && (uno.esFecha().equals(inicio)) && (dos.esFecha().equals(fin))) {
/* 163 */       sel = new StringBuilder().append(sel).append(" f.fecha BETWEEN '").append(inicio).append("' AND '").append(fin).append("'").toString();
/*     */     }
/* 165 */     else if ((criterio == 4) && (uno.esDoble()) && (dos.esDoble())) {
/* 166 */       sel = new StringBuilder().append(sel).append(" f.base BETWEEN ").append(inicio).append(" AND ").append(fin).toString();
/*     */     }
/* 168 */     else if ((criterio == 5) && (uno.esDoble()) && (dos.esDoble())) {
/* 169 */       sel = new StringBuilder().append(sel).append(" f.iva BETWEEN ").append(inicio).append(" AND ").append(fin).toString();
/*     */     }
/* 171 */     else if ((criterio == 6) && (uno.esDoble()) && (dos.esDoble())) {
/* 172 */       sel = new StringBuilder().append(sel).append(" f.total BETWEEN ").append(inicio).append(" AND ").append(fin).toString();
/*     */     }
/* 174 */     sel = new StringBuilder().append(sel).append(")").toString();
/* 175 */     return sel;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.ManejoTablaFacturasRecibidas
 * JD-Core Version:    0.6.2
 */