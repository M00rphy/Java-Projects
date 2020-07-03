/*     */ package almacen2.data.graficos;
/*     */ 
/*     */ import almacen2.data.MySQLConection;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class ManejadorGraficos
/*     */ {
/*  11 */   private MySQLConection con = null;
/*     */   private ResultSet res;
/*  13 */   private ArrayList<String> xTitle = new ArrayList();
/*  14 */   private ArrayList<ArrayList<Integer>> datos = new ArrayList();
/*     */ 
/*     */   public ManejadorGraficos(MySQLConection con, String ref1, String ref2, int tipo, String fechaI, String fechaF)
/*     */   {
/*  18 */     this.con = con;
/*  19 */     construirListas(ref1, ref2, tipo, fechaI, fechaF);
/*     */   }
/*     */ 
/*     */   private void construirListas(String ref1, String ref2, int tipo, String fechaI, String fechaF)
/*     */   {
/*  26 */     construirListaMeses(fechaI, fechaF);
/*  27 */     this.datos.add(new ArrayList());
/*  28 */     this.datos.add(new ArrayList());
/*  29 */     String consulta = "SELECT DATE_FORMAT(fecha,'%m-%y') as mes, SUM(io) FROM PIO WHERE referencia='";
/*  30 */     String consulta1 = " AND fecha BETWEEN '" + fechaI + "' AND '" + fechaF + "' GROUP BY mes ORDER BY fecha";
/*     */     try {
/*  32 */       ArrayList mesesTemporal = new ArrayList();
/*  33 */       ArrayList datosTemporal = new ArrayList();
/*  34 */       if (tipo == 0)
/*     */       {
/*  36 */         this.res = this.con.getRes(consulta + ref1 + "' AND io = 1" + consulta1);
/*  37 */         while (this.res.next()) {
/*  38 */           mesesTemporal.add(this.res.getString(1));
/*  39 */           datosTemporal.add(Integer.valueOf(this.res.getInt(2)));
/*     */         }
/*  41 */         insertarDatos(0, mesesTemporal, datosTemporal);
/*  42 */         mesesTemporal.clear();
/*  43 */         datosTemporal.clear();
/*  44 */         this.res = this.con.getRes(consulta + ref1 + "' AND io = -1" + consulta1);
/*  45 */         while (this.res.next()) {
/*  46 */           mesesTemporal.add(this.res.getString(1));
/*  47 */           datosTemporal.add(Integer.valueOf(this.res.getInt(2)));
/*     */         }
/*  49 */         insertarDatos(1, mesesTemporal, datosTemporal);
/*     */       }
/*  51 */       else if (tipo == 1) {
/*  52 */         this.res = this.con.getRes(consulta + ref1 + "' AND io = 1" + consulta1);
/*  53 */         while (this.res.next()) {
/*  54 */           mesesTemporal.add(this.res.getString(1));
/*  55 */           datosTemporal.add(Integer.valueOf(this.res.getInt(2)));
/*     */         }
/*  57 */         insertarDatos(0, mesesTemporal, datosTemporal);
/*  58 */         mesesTemporal.clear();
/*  59 */         datosTemporal.clear();
/*  60 */         this.res = this.con.getRes(consulta + ref2 + "' AND io = 1" + consulta1);
/*  61 */         while (this.res.next()) {
/*  62 */           mesesTemporal.add(this.res.getString(1));
/*  63 */           datosTemporal.add(Integer.valueOf(this.res.getInt(2)));
/*     */         }
/*  65 */         insertarDatos(1, mesesTemporal, datosTemporal);
/*     */       }
/*  67 */       else if (tipo == 2) {
/*  68 */         this.res = this.con.getRes(consulta + ref1 + "' AND io = -1" + consulta1);
/*  69 */         while (this.res.next()) {
/*  70 */           mesesTemporal.add(this.res.getString(1));
/*  71 */           datosTemporal.add(Integer.valueOf(this.res.getInt(2)));
/*     */         }
/*  73 */         insertarDatos(0, mesesTemporal, datosTemporal);
/*  74 */         mesesTemporal.clear();
/*  75 */         datosTemporal.clear();
/*  76 */         this.res = this.con.getRes(consulta + ref2 + "' AND io = -1" + consulta1);
/*  77 */         while (this.res.next()) {
/*  78 */           mesesTemporal.add(this.res.getString(1));
/*  79 */           datosTemporal.add(Integer.valueOf(this.res.getInt(2)));
/*     */         }
/*  81 */         insertarDatos(1, mesesTemporal, datosTemporal);
/*     */       }
/*     */     } catch (SQLException e) {
/*  84 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void insertarDatos(int index, ArrayList<String> meses, ArrayList<Integer> unidades) {
/*  89 */     int pos = 0;
/*  90 */     for (String fecha : this.xTitle)
/*  91 */       if ((pos < meses.size()) && (fecha.equals(meses.get(pos)))) {
/*  92 */         ((ArrayList)this.datos.get(index)).add(unidades.get(pos));
/*  93 */         pos++;
/*     */       }
/*     */       else {
/*  96 */         ((ArrayList)this.datos.get(index)).add(Integer.valueOf(0));
/*     */       }
/*     */   }
/*     */ 
/*     */   private void construirListaMeses(String fI, String fF)
/*     */   {
/* 102 */     String fechaI = fI.substring(5, 7) + "-" + fI.substring(2, 4);
/* 103 */     String fechaF = fF.substring(5, 7) + "-" + fF.substring(2, 4);
/* 104 */     int mesI = Integer.parseInt(fechaI.substring(0, 2));
/* 105 */     int mesF = Integer.parseInt(fechaF.substring(0, 2));
/* 106 */     int yearI = Integer.parseInt(fechaI.substring(3));
/* 107 */     int yearF = Integer.parseInt(fechaF.substring(3));
/* 108 */     for (int y = yearI; y <= yearF; y++) {
/* 109 */       String year = "-" + Integer.toString(y);
/* 110 */       int mesFinal = y == yearF ? mesF : 12;
/* 111 */       int mesInicial = y == yearI ? mesI : 1;
/* 112 */       for (int x = mesInicial; x <= mesFinal; x++) {
/* 113 */         String mes = x < 10 ? "0" + Integer.toString(x) : Integer.toString(x);
/* 114 */         this.xTitle.add(mes + year);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public ArrayList<String> getXTitle() {
/* 120 */     return this.xTitle;
/*     */   }
/*     */ 
/*     */   public ArrayList<ArrayList<Integer>> getDatos() {
/* 124 */     return this.datos;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.data.graficos.ManejadorGraficos
 * JD-Core Version:    0.6.2
 */