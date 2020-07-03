/*     */ package contaes.manejoDatos;
/*     */ 
/*     */ import contaes.manejoDatos.auxiliar.MySQLConection;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class ManejoTiposIVA
/*     */ {
/*     */   private MySQLConection con;
/*     */   private ResultSet res;
/*     */ 
/*     */   public ManejoTiposIVA(MySQLConection con)
/*     */   {
/*  19 */     this.con = con;
/*     */   }
/*     */ 
/*     */   public boolean crear(TipoIVA tipoiva) {
/*     */     try {
/*  24 */       this.con.getSentencia().executeUpdate("INSERT INTO tiposIVA (nombre,iva,re,cuentaRep,cuentaSop,cuentaRE,cuentaRepIntra,cuentaSopIntra,cuentaImpor) VALUES('" + tipoiva.getNombre() + "'," + tipoiva.getIva() + "," + tipoiva.getRe() + "," + tipoiva.getCuentaRep() + "," + tipoiva.getCuentaSop() + "," + tipoiva.getCuentaRE() + "," + tipoiva.getCuentaRepIntra() + "," + tipoiva.getCuentaSopIntra() + "," + tipoiva.getCuentaImport() + ")");
/*     */     }
/*     */     catch (SQLException e)
/*     */     {
/*  37 */       e.printStackTrace();
/*  38 */       return false;
/*     */     }
/*  40 */     return true;
/*     */   }
/*     */ 
/*     */   public int borrar(TipoIVA tipoiva) {
/*  44 */     int id = -1;
/*     */     try {
/*  46 */       this.con.getSentencia().executeUpdate("DELETE FROM tiposIVA WHERE id=" + tipoiva.getId());
/*     */ 
/*  48 */       this.res = this.con.getRes("SELECT MAX(id) FROM tiposIVA");
/*  49 */       if (this.res.next())
/*  50 */         id = this.res.getInt(1);
/*     */     } catch (SQLException e) {
/*  52 */       e.printStackTrace();
/*  53 */       return id;
/*     */     }
/*  55 */     return id;
/*     */   }
/*     */ 
/*     */   public void modificar(TipoIVA tipoiva) {
/*     */     try {
/*  60 */       this.con.getSentencia().executeUpdate("UPDATE tiposIVA SET nombre = '" + tipoiva.getNombre() + "'," + " iva = " + tipoiva.getIva() + "," + " re = " + tipoiva.getRe() + "," + " cuentaRep = " + tipoiva.getCuentaRep() + "," + " cuentaSop = " + tipoiva.getCuentaSop() + "," + " cuentaRE = " + tipoiva.getCuentaRE() + "," + " cuentaRepIntra = " + tipoiva.getCuentaRepIntra() + "," + " cuentaSopIntra = " + tipoiva.getCuentaSopIntra() + "," + " cuentaImpor = " + tipoiva.getCuentaImport() + " WHERE id = " + tipoiva.getId());
/*     */     }
/*     */     catch (SQLException e)
/*     */     {
/*  72 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public TipoIVA getTipoIVA(int id) {
/*  77 */     TipoIVA tipoiva = null;
/*     */     try {
/*  79 */       this.res = this.con.getRes("SELECT * FROM tiposIVA WHERE id=" + id);
/*     */ 
/*  81 */       if (this.res.next()) {
/*  82 */         tipoiva = new TipoIVA(id, this.res.getString(2), this.res.getDouble(3), this.res.getDouble(4), this.res.getInt(5), this.res.getInt(6), this.res.getInt(7), this.res.getInt(8), this.res.getInt(9), this.res.getInt(10));
/*     */       }
/*     */ 
/*  85 */       this.res.close();
/*     */     } catch (SQLException e) {
/*  87 */       e.printStackTrace();
/*     */     }
/*  89 */     return tipoiva;
/*     */   }
/*     */ 
/*     */   public ArrayList<TipoIVA> getTiposIVA() {
/*  93 */     ArrayList tiposiva = new ArrayList();
/*     */     try
/*     */     {
/*  96 */       this.res = this.con.getRes("SELECT * FROM tiposIVA");
/*  97 */       while (this.res.next()) {
/*  98 */         TipoIVA tipoiva = new TipoIVA(this.res.getInt(1), this.res.getString(2), this.res.getDouble(3), this.res.getDouble(4), this.res.getInt(5), this.res.getInt(6), this.res.getInt(7), this.res.getInt(8), this.res.getInt(9), this.res.getInt(10));
/*     */ 
/* 101 */         tiposiva.add(tipoiva);
/*     */       }
/* 103 */       this.res.close();
/*     */     } catch (SQLException e) {
/* 105 */       e.printStackTrace();
/*     */     }
/*     */ 
/* 108 */     return tiposiva;
/*     */   }
/*     */ 
/*     */   public boolean cuentaLibre(int cuenta) {
/* 112 */     boolean estaLibre = true;
/* 113 */     for (TipoIVA tipo : getTiposIVA()) {
/* 114 */       if ((tipo.getCuentaImport() == cuenta) || (tipo.getCuentaRE() == cuenta) || (tipo.getCuentaRep() == cuenta) || (tipo.getCuentaRepIntra() == cuenta) || (tipo.getCuentaSop() == cuenta) || (tipo.getCuentaSopIntra() == cuenta))
/*     */       {
/* 120 */         estaLibre = false;
/*     */       }
/*     */     }
/*     */ 
/* 124 */     return estaLibre;
/*     */   }
/*     */ 
/*     */   public boolean cuentaUtilizada(int cuenta) {
/* 128 */     boolean estaUtilizada = true;
/*     */     try {
/* 130 */       this.res = this.con.getRes("SELECT * FROM tiposIVA WHERE cuentaRep = " + cuenta + " OR" + " cuentaSop = " + cuenta + " OR" + " cuentaRE = " + cuenta);
/*     */ 
/* 134 */       if (!this.res.next())
/* 135 */         estaUtilizada = false;
/* 136 */       this.res.close();
/*     */     } catch (SQLException e) {
/* 138 */       e.printStackTrace();
/*     */     }
/* 140 */     return estaUtilizada;
/*     */   }
/*     */ 
/*     */   public String getTipoForInvoices(int cuenta) {
/* 144 */     String tipo = "X00";
/* 145 */     for (TipoIVA iva : getTiposIVA()) {
/* 146 */       if ((iva.getCuentaRep() == cuenta) || (iva.getCuentaSop() == cuenta)) {
/* 147 */         tipo = "G" + iva.getIva();
/*     */       }
/* 149 */       else if (iva.getCuentaRE() == cuenta) {
/* 150 */         tipo = "R" + iva.getRe();
/*     */       }
/* 152 */       else if ((iva.getCuentaRepIntra() == cuenta) || (iva.getCuentaSopIntra() == cuenta)) {
/* 153 */         tipo = "C" + iva.getIva();
/*     */       }
/* 155 */       else if (iva.getCuentaImport() == cuenta) {
/* 156 */         tipo = "I" + iva.getIva();
/*     */       }
/*     */     }
/* 159 */     return tipo;
/*     */   }
/*     */ 
/*     */   public TipoIVA getFullTipoForInvoices(int cuenta) {
/* 163 */     for (TipoIVA iva : getTiposIVA()) {
/* 164 */       if ((iva.getCuentaRep() == cuenta) || (iva.getCuentaSop() == cuenta) || (iva.getCuentaRE() == cuenta) || (iva.getCuentaRepIntra() == cuenta) || (iva.getCuentaSopIntra() == cuenta) || (iva.getCuentaImport() == cuenta))
/*     */       {
/* 168 */         return iva;
/*     */       }
/*     */     }
/* 171 */     return null;
/*     */   }
/*     */ 
/*     */   public TipoIVA getFullTipoCero() {
/* 175 */     for (TipoIVA iva : getTiposIVA()) {
/* 176 */       if (iva.getIva() == 0.0D) {
/* 177 */         return iva;
/*     */       }
/*     */     }
/* 180 */     return null;
/*     */   }
/*     */ 
/*     */   public String getTipoWithCuenta(int cuenta) {
/* 184 */     String tipo = "error";
/* 185 */     for (TipoIVA iva : getTiposIVA()) {
/* 186 */       if ((iva.getCuentaRep() == cuenta) || (iva.getCuentaSop() == cuenta) || (iva.getCuentaRE() == cuenta) || (iva.getCuentaRepIntra() == cuenta) || (iva.getCuentaSopIntra() == cuenta) || (iva.getCuentaImport() == cuenta))
/*     */       {
/* 189 */         tipo = iva.getNombre();
/*     */       }
/*     */     }
/* 192 */     return tipo;
/*     */   }
/*     */ 
/*     */   public boolean SonDelMismoTipo(int cuenta1, int cuenta2) {
/* 196 */     if (getTipoWithCuenta(cuenta1).equals(getTipoWithCuenta(cuenta2)))
/* 197 */       return true;
/* 198 */     return false;
/*     */   }
/*     */ 
/*     */   public void cerrarRs() {
/* 202 */     if (this.res != null) {
/*     */       try {
/* 204 */         this.res.close(); } catch (SQLException sqlEx) {
/*     */       }
/* 206 */       this.res = null;
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.ManejoTiposIVA
 * JD-Core Version:    0.6.2
 */