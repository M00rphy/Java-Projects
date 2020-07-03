/*     */ package contaes.manejoDatos.funciones;
/*     */ 
/*     */ import contaes.manejoDatos.ManejoApuntes;
/*     */ import contaes.manejoDatos.ManejoAsientos;
/*     */ import contaes.manejoDatos.ManejoSubcuentas;
/*     */ import contaes.manejoDatos.auxiliar.ImportesCuentaEntreFechas;
/*     */ import contaes.manejoDatos.auxiliar.MySQLConection;
/*     */ import internationalization.Mensajes;
/*     */ import java.io.PrintStream;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ 
/*     */ public class LiquidacionTrimIVA
/*     */ {
/*     */   private double sReper;
/*     */   private double sSopor;
/*     */   private double saldo;
/*  18 */   private LinkedList<Integer> listaSoportado = new LinkedList();
/*  19 */   private LinkedList<Integer> listaRepercutido = new LinkedList();
/*     */   private String ejercicio;
/*     */   private MySQLConection con;
/*     */ 
/*     */   public LiquidacionTrimIVA(MySQLConection con, String ejercicio)
/*     */   {
/*  24 */     this.con = con;
/*  25 */     this.ejercicio = ejercicio;
/*  26 */     recopilarCuentas();
/*     */   }
/*     */ 
/*     */   private void recopilarCuentas() {
/*  30 */     this.listaSoportado.clear();
/*  31 */     this.listaRepercutido.clear();
/*  32 */     ManejoSubcuentas cuentaM = new ManejoSubcuentas(this.con, this.ejercicio);
/*  33 */     this.listaSoportado = cuentaM.listadoSubcuentas(47200000, 47299999);
/*  34 */     this.listaRepercutido = cuentaM.listadoSubcuentas(47700000, 47799999);
/*     */   }
/*     */ 
/*     */   public void calculaImportes(int trim) {
/*  38 */     String fechaI = ""; String fechaF = "";
/*  39 */     switch (trim) {
/*     */     case 2:
/*  41 */       fechaI = this.ejercicio + "-04-01";
/*  42 */       fechaF = this.ejercicio + "-06-30";
/*  43 */       break;
/*     */     case 3:
/*  45 */       fechaI = this.ejercicio + "-07-01";
/*  46 */       fechaF = this.ejercicio + "-09-30";
/*  47 */       break;
/*     */     case 4:
/*  49 */       fechaI = this.ejercicio + "-10-01";
/*  50 */       fechaF = this.ejercicio + "-12-31";
/*  51 */       break;
/*     */     default:
/*  53 */       fechaI = this.ejercicio + "-01-01";
/*  54 */       fechaF = this.ejercicio + "-03-31";
/*     */     }
/*  56 */     this.sReper = 0.0D; this.sSopor = 0.0D;
/*     */ 
/*  58 */     for (Iterator i$ = this.listaRepercutido.iterator(); i$.hasNext(); ) { int lista = ((Integer)i$.next()).intValue();
/*  59 */       ImportesCuentaEntreFechas importe = new ImportesCuentaEntreFechas(String.valueOf(lista), fechaI, fechaF, "L", this.ejercicio);
/*     */ 
/*  61 */       this.sReper -= importe.saldo();
/*     */     }
/*  63 */     for (Iterator i$ = this.listaSoportado.iterator(); i$.hasNext(); ) { int lista = ((Integer)i$.next()).intValue();
/*  64 */       ImportesCuentaEntreFechas importe = new ImportesCuentaEntreFechas(String.valueOf(lista), fechaI, fechaF, "L", this.ejercicio);
/*     */ 
/*  66 */       this.sSopor += importe.saldo();
/*     */     }
/*  68 */     this.saldo = (this.sReper - this.sSopor);
/*     */   }
/*     */ 
/*     */   private double saldoCuenta(int cuenta, int trim) {
/*  72 */     String fechaI = ""; String fechaF = "";
/*  73 */     switch (trim) {
/*     */     case 2:
/*  75 */       fechaI = this.ejercicio + "-04-01";
/*  76 */       fechaF = this.ejercicio + "-06-30";
/*  77 */       break;
/*     */     case 3:
/*  79 */       fechaI = this.ejercicio + "-07-01";
/*  80 */       fechaF = this.ejercicio + "-09-30";
/*  81 */       break;
/*     */     case 4:
/*  83 */       fechaI = this.ejercicio + "-10-01";
/*  84 */       fechaF = this.ejercicio + "-12-31";
/*  85 */       break;
/*     */     default:
/*  87 */       fechaI = this.ejercicio + "-01-01";
/*  88 */       fechaF = this.ejercicio + "-03-31";
/*     */     }
/*  90 */     ImportesCuentaEntreFechas importe = new ImportesCuentaEntreFechas(String.valueOf(cuenta), fechaI, fechaF, "L", this.ejercicio);
/*     */ 
/*  92 */     return importe.saldo();
/*     */   }
/*     */ 
/*     */   public boolean crearAsiento(int trim) {
/*  96 */     if (existeAsiento(trim)) return false;
/*  97 */     String fecha = this.ejercicio;
/*  98 */     switch (trim) {
/*     */     case 1:
/* 100 */       fecha = fecha + "-03-31";
/* 101 */       break;
/*     */     case 2:
/* 103 */       fecha = fecha + "-06-30";
/* 104 */       break;
/*     */     case 3:
/* 106 */       fecha = fecha + "-09-30";
/* 107 */       break;
/*     */     case 4:
/* 109 */       fecha = fecha + "-12-31";
/*     */     }
/* 111 */     ManejoAsientos asientoM = new ManejoAsientos(this.con, this.ejercicio);
/* 112 */     int idAsiento = asientoM.crear(asientoM.nuevoNumero(), fecha, "", "L");
/* 113 */     if (idAsiento == -1) return false;
/* 114 */     ManejoApuntes apunteM = new ManejoApuntes(this.con, this.ejercicio);
/* 115 */     if (this.saldo >= 0.0D)
/* 116 */       apunteM.crear(idAsiento, 47500000, Mensajes.getString("liquidIVAs"), Mensajes.getString("haberA"), this.saldo);
/* 117 */     else apunteM.crear(idAsiento, 47000000, Mensajes.getString("liquidIVAs"), Mensajes.getString("debeA"), -this.saldo);
/* 118 */     for (Iterator i$ = this.listaRepercutido.iterator(); i$.hasNext(); ) { int lista = ((Integer)i$.next()).intValue();
/* 119 */       double saldo = saldoCuenta(lista, trim);
/* 120 */       if (saldo > 0.0D) {
/* 121 */         apunteM.crear(idAsiento, lista, Mensajes.getString("liquidIVAs"), Mensajes.getString("haberA"), saldo);
/*     */       }
/* 123 */       else if (saldo < 0.0D) {
/* 124 */         apunteM.crear(idAsiento, lista, Mensajes.getString("liquidIVAs"), Mensajes.getString("debeA"), -saldo);
/*     */       }
/*     */     }
/* 127 */     for (Iterator i$ = this.listaSoportado.iterator(); i$.hasNext(); ) { int lista = ((Integer)i$.next()).intValue();
/* 128 */       double saldo = saldoCuenta(lista, trim);
/* 129 */       if (saldo > 0.0D) {
/* 130 */         apunteM.crear(idAsiento, lista, Mensajes.getString("liquidIVAs"), Mensajes.getString("haberA"), saldo);
/*     */       }
/* 132 */       else if (saldo < 0.0D) {
/* 133 */         apunteM.crear(idAsiento, lista, Mensajes.getString("liquidIVAs"), Mensajes.getString("debeA"), -saldo);
/*     */       }
/*     */     }
/* 136 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean existeAsiento(int trim)
/*     */   {
/* 147 */     boolean existe = false;
/*     */ 
/* 149 */     String condicion = " WHERE marca='L' AND fecha BETWEEN '" + this.ejercicio;
/* 150 */     switch (trim) {
/*     */     case 1:
/* 152 */       condicion = condicion + "-01-01' AND '" + this.ejercicio + "-03-31'";
/* 153 */       break;
/*     */     case 2:
/* 155 */       condicion = condicion + "-04-01' AND '" + this.ejercicio + "-06-30'";
/* 156 */       break;
/*     */     case 3:
/* 158 */       condicion = condicion + "-07-01' AND '" + this.ejercicio + "-09-30'";
/* 159 */       break;
/*     */     default:
/* 161 */       condicion = condicion + "-10-01' AND '" + this.ejercicio + "-12-31'";
/*     */     }
/*     */     try {
/* 164 */       ResultSet res = this.con.getRes("SELECT * FROM asto" + this.ejercicio + condicion);
/* 165 */       if (res.next()) existe = true;
/* 166 */       res.close();
/*     */     }
/*     */     catch (SQLException exc) {
/* 169 */       System.out.println(exc.getMessage());
/* 170 */       return existe;
/*     */     }
/* 172 */     return existe;
/*     */   }
/*     */ 
/*     */   public double getSaldo()
/*     */   {
/* 179 */     return this.saldo;
/*     */   }
/*     */ 
/*     */   public double getSReper()
/*     */   {
/* 186 */     return this.sReper;
/*     */   }
/*     */ 
/*     */   public double getSSopor()
/*     */   {
/* 193 */     return this.sSopor;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.funciones.LiquidacionTrimIVA
 * JD-Core Version:    0.6.2
 */