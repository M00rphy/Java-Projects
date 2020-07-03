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
/*     */ public class ManejoTablaFacturasEmitidas
/*     */ {
/*     */   private String base;
/*     */   private String orden;
/*     */   private String seleccion;
/*  14 */   private boolean primero = true;
/*  15 */   private Connection con = null;
/*     */   private Statement sentencia;
/*     */   private int empresa;
/*     */ 
/*     */   public ManejoTablaFacturasEmitidas(int empresa, String ejercicio)
/*     */   {
/*  20 */     this.empresa = empresa;
/*  21 */     abrirConexion();
/*  22 */     this.base = ("SELECT f.numero,f.fecha,f.cuenta,a.numero,f.concepto,f.base,f.iva,f.total FROM emit" + ejercicio + " f JOIN asto" + ejercicio + " a ON f.id_asiento=a.id_asiento");
/*     */ 
/*  25 */     this.orden = " ORDER BY f.fecha,f.numero";
/*  26 */     this.seleccion = "";
/*     */   }
/*     */ 
/*     */   private String mandatoSQL()
/*     */   {
/*  34 */     return this.base + this.seleccion + this.orden;
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
/*     */   public void seleccionEstandar(String inicio, String fin, int criterio)
/*     */   {
/*  49 */     if (fin.equals("")) this.seleccion += seleccionaVarios(inicio, criterio); else
/*  50 */       this.seleccion += seleccionaEntreDos(inicio, fin, criterio);
/*     */   }
/*     */ 
/*     */   public void seleccionTexto(String campo, String texto) {
/*  54 */     this.seleccion = (this.seleccion + primeroSiguiente() + campo + " LIKE '" + texto + "')");
/*     */   }
/*     */ 
/*     */   public void abrirConexion() {
/*     */     try {
/*  59 */       this.con = DriverManager.getConnection("jdbc:mysql://" + Inicio.p.getDireccionIP() + "/contaes" + this.empresa, Inicio.p.getUsuario(), Inicio.p.getPassword());
/*     */ 
/*  62 */       this.sentencia = this.con.createStatement();
/*     */     }
/*     */     catch (SQLException exc) {
/*  65 */       System.out.println(exc.getMessage());
/*     */     }
/*     */   }
/*     */ 
/*     */   public void cerrarConexion() {
/*     */     try {
/*  71 */       this.sentencia.close();
/*  72 */       this.con.close();
/*     */     } catch (SQLException e) {
/*  74 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean conexionCerrada() {
/*     */     try {
/*  80 */       return this.con.isClosed();
/*     */     } catch (SQLException e) {
/*  82 */       e.printStackTrace();
/*     */     }
/*  84 */     return true;
/*     */   }
/*     */ 
/*     */   private String primeroSiguiente() {
/*  88 */     if (!this.primero) return " AND (";
/*  89 */     this.primero = false;
/*  90 */     return " WHERE (";
/*     */   }
/*     */ 
/*     */   private String seleccionaVarios(String cadena, int criterio)
/*     */   {
/*  95 */     ComprobarDato comp = new ComprobarDato();
/*  96 */     if (criterio == 3) return "";
/*  97 */   //  if (((criterio < 1 ? 1 : 0) | (criterio > 7 ? 1 : 0) | cadena.equals("")) != 0) return "";
/*  98 */    if (criterio < 1) criterio=1; else criterio= 0;
if (criterio > 7) criterio=1; else criterio= 0;
if (cadena.equals("")&& criterio!= 0) return "";
String sel = primeroSiguiente();
/*  99 */     while (!cadena.equals("")) {
/* 100 */       int indice = cadena.indexOf(';');
/* 101 */       if (indice >= 0) {
/* 102 */         comp.setDato(cadena.substring(0, indice));
/* 103 */         if ((criterio == 1) && (comp.esEntero()))
/* 104 */           sel = sel + " a.numero=" + cadena.substring(0, indice) + " OR ";
/* 105 */         else if ((criterio == 2) && (comp.esEntero())) {
/* 106 */           sel = sel + " f.cuenta=" + cadena.substring(0, indice) + " OR ";
/*     */         }
/* 109 */         else if ((criterio == 4) && (comp.esDoble()))
/* 110 */           sel = sel + " f.base=" + cadena.substring(0, indice) + " OR ";
/* 111 */         else if ((criterio == 5) && (comp.esDoble()))
/* 112 */           sel = sel + " f.iva=" + cadena.substring(0, indice) + " OR ";
/* 113 */         else if ((criterio == 6) && (comp.esDoble()))
/* 114 */           sel = sel + " f.total=" + cadena.substring(0, indice) + " OR ";
/* 115 */         else if (criterio == 7)
/* 116 */           sel = sel + " f.numero='" + cadena.substring(0, indice) + "' OR ";
/* 117 */         cadena = cadena.substring(indice + 1);
/*     */       }
/*     */       else {
/* 120 */         comp.setDato(cadena.substring(0));
/* 121 */         if ((criterio == 1) && (comp.esEntero()))
/* 122 */           sel = sel + " a.numero=" + cadena.substring(0);
/* 123 */         else if ((criterio == 2) && (comp.esEntero())) {
/* 124 */           sel = sel + " f.cuenta=" + cadena.substring(0);
/*     */         }
/* 127 */         else if ((criterio == 4) && (comp.esDoble()))
/* 128 */           sel = sel + " f.base=" + cadena.substring(0);
/* 129 */         else if ((criterio == 5) && (comp.esDoble()))
/* 130 */           sel = sel + " f.iva=" + cadena.substring(0);
/* 131 */         else if ((criterio == 6) && (comp.esDoble()))
/* 132 */           sel = sel + " f.total=" + cadena.substring(0);
/* 133 */         else if (criterio == 7)
/* 134 */           sel = sel + " f.numero='" + cadena.substring(0) + "'";
/* 135 */         sel = sel + ")";
/* 136 */         cadena = "";
/*     */       }
/*     */     }
/* 139 */     return sel;
/*     */   }
/*     */ 
/*     */   private String seleccionaEntreDos(String inicio, String fin, int criterio)
/*     */   {
/* 145 */     String sel = primeroSiguiente();
/* 146 */     if (((criterio < 1 ? 1 : 0) | (criterio > 6 ? 1 : 0)) != 0) return "";
/* 147 */     ComprobarDato uno = new ComprobarDato(inicio);
/* 148 */     ComprobarDato dos = new ComprobarDato(fin);
/* 149 */     if ((criterio == 1) && (uno.esEntero()) && (dos.esEntero())) {
/* 150 */       sel = sel + " a.numero BETWEEN " + inicio + " AND " + fin;
/*     */     }
/* 152 */     else if ((criterio == 2) && (uno.esEntero()) && (dos.esEntero())) {
/* 153 */       sel = sel + " f.cuenta BETWEEN " + inicio + " AND " + fin;
/*     */     }
/* 155 */     else if ((criterio == 3) && (uno.esFecha().equals(inicio)) && (dos.esFecha().equals(fin))) {
/* 156 */       sel = sel + " f.fecha BETWEEN '" + inicio + "' AND '" + fin + "'";
/*     */     }
/* 158 */     else if ((criterio == 4) && (uno.esDoble()) && (dos.esDoble())) {
/* 159 */       sel = sel + " f.base BETWEEN " + inicio + " AND " + fin;
/*     */     }
/* 161 */     else if ((criterio == 5) && (uno.esDoble()) && (dos.esDoble())) {
/* 162 */       sel = sel + " f.iva BETWEEN " + inicio + " AND " + fin;
/*     */     }
/* 164 */     else if ((criterio == 6) && (uno.esDoble()) && (dos.esDoble())) {
/* 165 */       sel = sel + " f.total BETWEEN " + inicio + " AND " + fin;
/*     */     }
/* 167 */     sel = sel + ")";
/* 168 */     return sel;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.ManejoTablaFacturasEmitidas
 * JD-Core Version:    0.6.2
 */