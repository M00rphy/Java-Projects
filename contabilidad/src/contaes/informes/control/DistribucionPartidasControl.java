/*     */ package contaes.informes.control;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.informes.model.DistribucionPartidasObject;
/*     */ import contaes.manejoDatos.ManejoSubcuentas;
/*     */ import contaes.manejoDatos.TipoSubcuenta;
/*     */ import contaes.manejoDatos.auxiliar.MySQLConection;
/*     */ import internationalization.Mensajes;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ 
/*     */ public class DistribucionPartidasControl
/*     */ {
/*     */   private MySQLConection con;
/*     */   private String ejercicio;
/*     */   private ResultSet res;
/*     */ 
/*     */   public DistribucionPartidasControl(MySQLConection con, String ejercicio)
/*     */   {
/*  32 */     this.con = con;
/*  33 */     this.ejercicio = ejercicio;
/*     */   }
/*     */ 
/*     */   public ArrayList<DistribucionPartidasObject> listado(Integer grupo, boolean positivo, java.util.Date fechaIni, java.util.Date fechaFin, boolean noCeros) {
/*  37 */     ArrayList array = new ArrayList();
/*  38 */     Integer signo = Integer.valueOf(positivo ? 1 : -1);
/*  39 */     Double totalGrupo = Double.valueOf(0.0D);
/*  40 */     Integer grupoInicial2 = Integer.valueOf(grupo.intValue() * 10);
/*  41 */     String nombreGrupoGlobal = nombreGrupo(grupo.intValue());
/*  42 */     if (!nombreGrupoGlobal.equals("")) {
/*  43 */       for (int grupo2 = grupoInicial2.intValue(); grupo2 <= grupoInicial2.intValue() + 9; grupo2++) {
/*  44 */         String nombreGrupo2 = nombreGrupo(grupo2);
/*  45 */         if (!nombreGrupo2.equals("")) {
/*  46 */           Double totalGrupo2 = Double.valueOf(0.0D);
/*  47 */           Integer grupoInicial3 = Integer.valueOf(grupo2 * 10);
/*  48 */           for (int grupo3 = grupoInicial3.intValue(); grupo3 <= grupoInicial3.intValue() + 9; grupo3++) {
/*  49 */             String nombreGrupo3 = nombreGrupo(grupo3);
/*  50 */             if (!nombreGrupo3.equals("")) {
/*  51 */               Double importe = Double.valueOf(0.0D);
/*  52 */               importe = importeSaldo(Integer.valueOf(grupo3), fechaIni, fechaFin);
/*  53 */               if ((!noCeros) || ((noCeros) && (importe.doubleValue() != 0.0D))) {
/*  54 */                 DistribucionPartidasObject objeto = new DistribucionPartidasObject(nombreGrupo3, Double.valueOf(signo.intValue() * importe.doubleValue()));
/*  55 */                 array.add(objeto);
/*  56 */                 totalGrupo2 = Double.valueOf(totalGrupo2.doubleValue() + importe.doubleValue());
/*     */               }
/*     */             }
/*     */           }
/*  60 */           DistribucionPartidasObject objeto = new DistribucionPartidasObject(nombreGrupo2, Double.valueOf(signo.intValue() * totalGrupo2.doubleValue()));
/*  61 */           array.add(objeto);
/*  62 */           totalGrupo = Double.valueOf(totalGrupo.doubleValue() + totalGrupo2.doubleValue());
/*     */         }
/*     */       }
/*  65 */       DistribucionPartidasObject objeto = new DistribucionPartidasObject(nombreGrupoGlobal, Double.valueOf(signo.intValue() * totalGrupo.doubleValue()));
/*  66 */       array.add(objeto);
/*     */     }
/*     */ 
/*  69 */     for (int i = 0; i < array.size(); i++) {
/*  70 */       Double porcentaje = Double.valueOf(((DistribucionPartidasObject)array.get(i)).getImporte().doubleValue() / (signo.intValue() * totalGrupo.doubleValue()) * 100.0D);
/*  71 */       ((DistribucionPartidasObject)array.get(i)).setPorcentaje(porcentaje);
/*     */     }
/*  73 */     return array;
/*     */   }
/*     */ 
/*     */   public ArrayList<DistribucionPartidasObject> listadoSubcuentas(Integer subcuentaIni, Integer subcuentaFin, java.util.Date fechaIni, java.util.Date fechaFin, boolean noCeros, int opcion) {
/*  77 */     ArrayList array = new ArrayList();
/*  78 */     Double importeTotal = Double.valueOf(0.0D);
/*  79 */     ManejoSubcuentas mS = new ManejoSubcuentas(this.con, this.ejercicio);
/*  80 */     ArrayList arraySubcuentas = mS.listaSubcuentas(subcuentaIni.intValue(), subcuentaFin.intValue());
/*     */ 
/*  82 */     for (TipoSubcuenta tipoSubcuenta : (List<TipoSubcuenta>) arraySubcuentas) {
/*  83 */       Double importe = importeSaldoSubcuenta(Integer.valueOf(tipoSubcuenta.getCodigo()), fechaIni, fechaFin, opcion);
/*  84 */       if ((!noCeros) || ((noCeros) && (importe.doubleValue() != 0.0D))) {
/*  85 */         importeTotal = Double.valueOf(importeTotal.doubleValue() + importe.doubleValue());
/*  86 */         DistribucionPartidasObject objeto = new DistribucionPartidasObject(tipoSubcuenta.getNombre(), importe);
/*  87 */         array.add(objeto);
/*     */       }
/*     */     }
/*  90 */     DistribucionPartidasObject objeto = new DistribucionPartidasObject(Mensajes.getString("total"), importeTotal);
/*  91 */     array.add(objeto);
/*     */ 
/*  93 */     for (int i = 0; i < array.size(); i++) {
/*  94 */       Double porcentaje = Double.valueOf(((DistribucionPartidasObject)array.get(i)).getImporte().doubleValue() / importeTotal.doubleValue() * 100.0D);
/*  95 */       ((DistribucionPartidasObject)array.get(i)).setPorcentaje(porcentaje);
/*     */     }
/*  97 */     return array;
/*     */   }
/*     */ 
/*     */   private String nombreGrupo(int codigoGrupo) {
/* 101 */     String nombre = "";
/*     */     try {
/* 103 */       this.res = Inicio.getCGeneral().getRes("SELECT nombre FROM Plan_contable WHERE codigo = " + codigoGrupo);
/* 104 */       if (this.res.next())
/* 105 */         nombre = this.res.getString(1);
/*     */     }
/*     */     catch (SQLException ex) {
/* 108 */       Logger.getLogger(DistribucionPartidasControl.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/*     */ 
/* 111 */     return nombre;
/*     */   }
/*     */ 
/*     */   private Double importeSaldo(Integer grupo3, java.util.Date fechaIni, java.util.Date fechaFin) {
/* 115 */     Double importe = Double.valueOf(0.0D);
/* 116 */     Integer subcuentaIni = Integer.valueOf(grupo3.intValue() * 100000);
/* 117 */     Integer subcuentaFin = Integer.valueOf(subcuentaIni.intValue() + 99999);
/* 118 */     String sqlQuery = "SELECT SUM(importe) FROM apte" + this.ejercicio + " ap JOIN asto" + this.ejercicio + " ast ON ap.id_asiento=ast.id_asiento WHERE ap.DH = ? " + " AND (ap.cuenta BETWEEN ? AND ?) " + " AND (ast.fecha BETWEEN ? AND ?)";
/*     */     try
/*     */     {
/* 123 */       PreparedStatement ps = this.con.getCon().prepareStatement(sqlQuery);
/* 124 */       ps.setString(1, "D");
/* 125 */       ps.setInt(2, subcuentaIni.intValue());
/* 126 */       ps.setInt(3, subcuentaFin.intValue());
/* 127 */       ps.setDate(4, new java.sql.Date(fechaIni.getTime()));
/* 128 */       ps.setDate(5, new java.sql.Date(fechaFin.getTime()));
/* 129 */       this.res = ps.executeQuery();
/* 130 */       if (this.res.next()) {
/* 131 */         importe = Double.valueOf(importe.doubleValue() + this.res.getDouble(1));
/*     */       }
/* 133 */       ps = this.con.getCon().prepareStatement(sqlQuery);
/* 134 */       ps.setString(1, "H");
/* 135 */       ps.setInt(2, subcuentaIni.intValue());
/* 136 */       ps.setInt(3, subcuentaFin.intValue());
/* 137 */       ps.setDate(4, new java.sql.Date(fechaIni.getTime()));
/* 138 */       ps.setDate(5, new java.sql.Date(fechaFin.getTime()));
/* 139 */       this.res = ps.executeQuery();
/* 140 */       if (this.res.next())
/* 141 */         importe = Double.valueOf(importe.doubleValue() - this.res.getDouble(1));
/*     */     }
/*     */     catch (SQLException ex)
/*     */     {
/* 145 */       Logger.getLogger(DistribucionPartidasControl.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/*     */ 
/* 148 */     return importe;
/*     */   }
/*     */ 
/*     */   private Double importeSaldoSubcuenta(Integer subcuenta, java.util.Date fechaIni, java.util.Date fechaFin, int opcion)
/*     */   {
/* 155 */     Double saldo = Double.valueOf(0.0D);
/* 156 */     Double debe = Double.valueOf(0.0D);
/* 157 */     Double haber = Double.valueOf(0.0D);
/* 158 */     String sqlQuery = "SELECT SUM(importe) FROM apte" + this.ejercicio + " ap JOIN asto" + this.ejercicio + " ast ON ap.id_asiento=ast.id_asiento WHERE ap.DH = ? " + " AND (ap.cuenta = ?) " + " AND (ast.fecha BETWEEN ? AND ?)";
/*     */     try
/*     */     {
/* 163 */       PreparedStatement ps = this.con.getCon().prepareStatement(sqlQuery);
/* 164 */       ps.setString(1, "D");
/* 165 */       ps.setInt(2, subcuenta.intValue());
/* 166 */       ps.setDate(3, new java.sql.Date(fechaIni.getTime()));
/* 167 */       ps.setDate(4, new java.sql.Date(fechaFin.getTime()));
/* 168 */       this.res = ps.executeQuery();
/* 169 */       if (this.res.next()) {
/* 170 */         debe = Double.valueOf(this.res.getDouble(1));
/*     */       }
/* 172 */       ps = this.con.getCon().prepareStatement(sqlQuery);
/* 173 */       ps.setString(1, "H");
/* 174 */       ps.setInt(2, subcuenta.intValue());
/* 175 */       ps.setDate(3, new java.sql.Date(fechaIni.getTime()));
/* 176 */       ps.setDate(4, new java.sql.Date(fechaFin.getTime()));
/* 177 */       this.res = ps.executeQuery();
/* 178 */       if (this.res.next())
/* 179 */         haber = Double.valueOf(this.res.getDouble(1));
/*     */     }
/*     */     catch (SQLException ex)
/*     */     {
/* 183 */       Logger.getLogger(DistribucionPartidasControl.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/* 185 */     saldo = Double.valueOf(debe.doubleValue() - haber.doubleValue());
/* 186 */     if (opcion == 1) {
/* 187 */       return debe;
/*     */     }
/* 189 */     if (opcion == 2) {
/* 190 */       return haber;
/*     */     }
/*     */ 
/* 193 */     return saldo;
/*     */   }
/*     */ 
/*     */   public void cerrarRs()
/*     */   {
/* 198 */     if (this.res != null) {
/*     */       try {
/* 200 */         this.res.close();
/*     */       } catch (SQLException sqlEx) {
/*     */       }
/* 203 */       this.res = null;
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.informes.control.DistribucionPartidasControl
 * JD-Core Version:    0.6.2
 */