/*     */ package contaes.manejoDatos.auxiliar;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.Puente;
/*     */ import internationalization.Mensajes;
/*     */ import java.io.IOException;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.Properties;
/*     */ import javax.swing.JOptionPane;
/*     */ 
/*     */ public class ImportesCuentaEntreFechas
/*     */ {
/*     */   private ResultSet res;
/*     */   private double d;
/*     */   private double h;
/*     */   private double s;
/*  43 */   private String nombre = "";
/*     */   private String cuenta;
/*     */   private String ejercicio;
/*     */   private String fechaI;
/*     */   private String fechaF;
/*     */   private String M;
/*  49 */   private String M2 = "";
/*     */ 
/*     */   public ImportesCuentaEntreFechas(String cuenta, String FI, String FF) {
/*  52 */     this(cuenta, FI, FF, "");
/*     */   }
/*     */ 
/*     */   public ImportesCuentaEntreFechas(String cuenta, String FI, String FF, String M) {
/*  56 */     this(cuenta, FI, FF, M, Inicio.p.getEjercicio());
/*     */   }
/*     */ 
/*     */   public ImportesCuentaEntreFechas(String cuenta, String FI, String FF, String M, String ejercicio) {
/*  60 */     this.cuenta = cuenta;
/*  61 */     this.ejercicio = ejercicio;
/*  62 */     this.fechaI = FI;
/*  63 */     this.fechaF = FF;
/*  64 */     this.M = M;
/*  65 */     this.d = 0.0D;
/*  66 */     this.h = 0.0D;
/*  67 */     this.s = 0.0D;
/*  68 */     ejecutar();
/*     */   }
/*     */ 
/*     */   public ImportesCuentaEntreFechas(String cuenta, String FI, String FF, String M, String M2, String ejercicio) {
/*  72 */     this.cuenta = cuenta;
/*  73 */     this.ejercicio = ejercicio;
/*  74 */     this.fechaI = FI;
/*  75 */     this.fechaF = FF;
/*  76 */     this.M = M;
/*  77 */     this.M2 = M2;
/*  78 */     this.d = 0.0D;
/*  79 */     this.h = 0.0D;
/*  80 */     this.s = 0.0D;
/*  81 */     ejecutar();
/*     */   }
/*     */ 
/*     */   private void ejecutar() {
/*  85 */     String cuentaSelected = " cuenta";
/*  86 */     if (this.cuenta.length() >= 8)
/*  87 */       cuentaSelected = cuentaSelected + " = " + this.cuenta;
/*     */     else {
/*  89 */       cuentaSelected = cuentaSelected + " BETWEEN " + this.cuenta + "0000000".substring(this.cuenta.length() - 1) + " AND " + this.cuenta + "9999999".substring(this.cuenta.length() - 1);
/*     */     }
/*     */     try
/*     */     {
/*  93 */       if (this.cuenta.length() >= 8) {
/*  94 */         this.res = Inicio.getCEmpresa().getRes("SELECT nombre FROM scta" + this.ejercicio + " WHERE codigo = " + Integer.parseInt(this.cuenta));
/*     */ 
/*  96 */         if (this.res.next())
/*  97 */           this.nombre = this.res.getString(1);
/*     */       }
/*     */       else {
/* 100 */         Properties sistema = System.getProperties();
/* 101 */         String sisOp = sistema.getProperty("os.name");
/* 102 */         String archivoPGC = sisOp.substring(0, 3).equals("Mac") ? "pgc08MAC.txt" : "pgc08.txt";
/*     */         try
/*     */         {
/* 105 */           LeerFichero pgc = new LeerFichero("configdir/" + archivoPGC);
/* 106 */           int numeroCuentas = Integer.parseInt(pgc.leer());
/* 107 */           for (int x = 0; x < numeroCuentas; x++) {
/* 108 */             String linea = pgc.leer();
/* 109 */             String codigoCuenta = linea.substring(0, linea.indexOf(";"));
/* 110 */             if (this.cuenta.equals(codigoCuenta)) {
/* 111 */               this.nombre = linea.substring(linea.indexOf(";") + 1, linea.lastIndexOf(";"));
/*     */             }
/*     */             else
/* 114 */               if (pgc.eof())
/*     */                 break;
/*     */           }
/*     */         }
/*     */         catch (IOException e) {
/* 119 */           e.printStackTrace();
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 125 */       String cadenaSeleccion = "SELECT SUM(apte.importe) FROM apte" + this.ejercicio + " AS apte JOIN " + "asto" + this.ejercicio + " AS asto " + "ON apte.id_asiento = asto.id_asiento " + "WHERE " + cuentaSelected + " AND asto.fecha BETWEEN '" + this.fechaI + "' AND '" + this.fechaF + "' ";
/*     */ 
/* 131 */       if (!this.M.equals("")) {
/* 132 */         cadenaSeleccion = cadenaSeleccion + " AND asto.marca NOT LIKE '" + this.M + "'";
/*     */       }
/* 134 */       if (!this.M2.equals("")) {
/* 135 */         cadenaSeleccion = cadenaSeleccion + " AND asto.marca NOT LIKE '" + this.M2 + "'";
/*     */       }
/* 137 */       cadenaSeleccion = cadenaSeleccion + " AND apte.DH = ";
/* 138 */       this.res = Inicio.getCEmpresa().getRes(cadenaSeleccion + "'" + Mensajes.getString("debeA") + "'");
/* 139 */       if (this.res.next()) {
/* 140 */         this.d = this.res.getDouble(1);
/*     */       }
/* 142 */       this.res = Inicio.getCEmpresa().getRes(cadenaSeleccion + "'" + Mensajes.getString("haberA") + "'");
/* 143 */       if (this.res.next()) {
/* 144 */         this.h = this.res.getDouble(1);
/*     */       }
/* 146 */       this.res.close();
/* 147 */       this.res = null;
/*     */     } catch (SQLException exc) {
/* 149 */       JOptionPane.showMessageDialog(null, Mensajes.getString("errObtDatos") + ":\n" + exc.getMessage());
/*     */     }
/* 151 */     this.s = (this.d - this.h);
/*     */   }
/*     */ 
/*     */   public double debe() {
/* 155 */     return this.d;
/*     */   }
/*     */ 
/*     */   public double haber() {
/* 159 */     return this.h;
/*     */   }
/*     */ 
/*     */   public double saldo() {
/* 163 */     return this.s;
/*     */   }
/*     */ 
/*     */   public String titulo() {
/* 167 */     return this.nombre;
/*     */   }
/*     */ 
/*     */   public String codigo() {
/* 171 */     return this.cuenta;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.auxiliar.ImportesCuentaEntreFechas
 * JD-Core Version:    0.6.2
 */