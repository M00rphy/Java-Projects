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
/*     */ public class ManejoTablaDiario
/*     */ {
/*     */   private String base;
/*     */   private String orden;
/*     */   private String seleccion;
/*  14 */   private boolean primero = true;
/*  15 */   private Connection con = null;
/*     */   private Statement sentencia;
/*     */   private int empresa;
/*     */ 
/*     */   public ManejoTablaDiario(int empresa, String ejercicio)
/*     */   {
/*  20 */     this.empresa = empresa;
/*  21 */     abrirConexion();
/*  22 */     this.base = ("SELECT numero,fecha,documento,cuenta,concepto,DH,importe,marca FROM apte" + ejercicio + " apt JOIN asto" + ejercicio + " ast ON apt.id_asiento=ast.id_asiento");
/*     */ 
/*  25 */     this.orden = " ORDER BY ast.numero,apt.id_apunte";
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
/*     */   private String seleccionaVarios(String cadena, int criterio) {
/*  94 */     ComprobarDato comp = new ComprobarDato();
/*  95 */     if (criterio == 3) return "";
/*  96 */     //if (((criterio < 1 ? 1 : 0) | (criterio > 5 ? 1 : 0) | cadena.equals("")) != 0) return "";
/*  97 */     if (criterio < 1) criterio=1; else criterio= 0;
if (criterio > 5) criterio=1; else criterio= 0;

if (cadena.equals("")&& criterio!= 0) return "";
                String sel = primeroSiguiente();
/*  98 */     while (!cadena.equals("")) {
/*  99 */       int indice = cadena.indexOf(';');
/* 100 */       if (indice >= 0) {
/* 101 */         comp.setDato(cadena.substring(0, indice));
/* 102 */         if ((criterio == 1) && (comp.esEntero()))
/* 103 */           sel = sel + " ast.numero=" + cadena.substring(0, indice) + " OR ";
/* 104 */         else if ((criterio == 2) && (comp.esEntero())) {
/* 105 */           sel = sel + " apt.cuenta=" + cadena.substring(0, indice) + " OR ";
/*     */         }
/* 108 */         else if ((criterio == 4) && (comp.esDoble()))
/* 109 */           sel = sel + " apt.importe=" + cadena.substring(0, indice) + " OR ";
/* 110 */         else if (criterio == 5)
/* 111 */           sel = sel + " ast.marca='" + cadena.substring(0, indice) + "' OR ";
/* 112 */         cadena = cadena.substring(indice + 1);
/*     */       }
/*     */       else {
/* 115 */         comp.setDato(cadena.substring(0));
/* 116 */         if ((criterio == 1) && (comp.esEntero()))
/* 117 */           sel = sel + " ast.numero=" + cadena.substring(0);
/* 118 */         else if ((criterio == 2) && (comp.esEntero())) {
/* 119 */           sel = sel + " apt.cuenta=" + cadena.substring(0);
/*     */         }
/* 122 */         else if ((criterio == 4) && (comp.esDoble()))
/* 123 */           sel = sel + " apt.importe=" + cadena.substring(0);
/* 124 */         else if (criterio == 5)
/* 125 */           sel = sel + " ast.marca='" + cadena.substring(0) + "'";
/* 126 */         sel = sel + ")";
/* 127 */         cadena = "";
/*     */       }
/*     */     }
/* 130 */     return sel;
/*     */   }
/*     */ 
/*     */   private String seleccionaEntreDos(String inicio, String fin, int criterio)
/*     */   {
/* 135 */     String sel = primeroSiguiente();
/* 136 */     if (((criterio < 1 ? 1 : 0) | (criterio > 4 ? 1 : 0)) != 0) return "";
/* 137 */     ComprobarDato uno = new ComprobarDato(inicio);
/* 138 */     ComprobarDato dos = new ComprobarDato(fin);
/* 139 */     if ((criterio == 1) && (uno.esEntero()) && (dos.esEntero())) {
/* 140 */       sel = sel + " ast.numero BETWEEN " + inicio + " AND " + fin;
/*     */     }
/* 142 */     else if ((criterio == 2) && (uno.esEntero()) && (dos.esEntero())) {
/* 143 */       sel = sel + " apt.cuenta BETWEEN " + inicio + " AND " + fin;
/*     */     }
/* 145 */     else if ((criterio == 3) && (uno.esFecha().equals(inicio)) && (dos.esFecha().equals(fin))) {
/* 146 */       sel = sel + " ast.fecha BETWEEN '" + inicio + "' AND '" + fin + "'";
/*     */     }
/* 148 */     else if ((criterio == 4) && (uno.esDoble()) && (dos.esDoble())) {
/* 149 */       sel = sel + " apt.importe BETWEEN " + inicio + " AND " + fin;
/*     */     }
/* 151 */     sel = sel + ")";
/* 152 */     return sel;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.ManejoTablaDiario
 * JD-Core Version:    0.6.2
 */