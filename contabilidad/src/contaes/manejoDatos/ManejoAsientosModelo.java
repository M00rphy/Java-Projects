/*     */ package contaes.manejoDatos;
/*     */ 
/*     */ import contaes.manejoDatos.auxiliar.MySQLConection;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ 
/*     */ public class ManejoAsientosModelo
/*     */ {
/*     */   private MySQLConection con;
/*     */   private ResultSet res;
/*     */ 
/*     */   public ManejoAsientosModelo(MySQLConection con)
/*     */   {
/*  15 */     this.con = con;
/*     */   }
/*     */ 
/*     */   public int crearAsiento(TipoAsientoModelo asiento)
/*     */   {
/*  20 */     return crearAsiento(asiento.getDescripcion(), asiento.getConcepto(), asiento.getMarca());
/*     */   }
/*     */ 
/*     */   public int crearAsiento(String descripcion, String concepto, String marca)
/*     */   {
/*  25 */     int id = -1;
/*     */     try {
/*  27 */       this.res = this.con.getRes("SELECT MAX(id) FROM aPatronP");
/*  28 */       if (this.res.next()) {
/*  29 */         id = this.res.getInt(1) + 1;
/*  30 */         this.con.getSentencia().executeUpdate("INSERT INTO aPatronP VALUES (" + id + "," + "'" + descripcion + "'," + "'" + concepto + "'," + "'" + marca + "')");
/*     */       }
/*     */ 
/*  36 */       this.res.close();
/*     */     } catch (SQLException exc) {
/*  38 */       exc.printStackTrace();
/*     */     }
/*  40 */     return id;
/*     */   }
/*     */ 
/*     */   public void modificarAsiento(int id, String descripcion, String concepto, String marca)
/*     */   {
/*     */     try {
/*  46 */       this.con.getSentencia().executeUpdate("UPDATE aPatronP SET descripcion='" + descripcion + "'," + "concepto='" + concepto + "'," + "marca='" + marca + "'" + " WHERE id=" + id);
/*     */ 
/*  51 */       borrarApuntes(id);
/*     */     } catch (SQLException e) {
/*  53 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void borrarAsiento(int id) {
/*     */     try {
/*  59 */       this.con.getSentencia().executeUpdate("DELETE FROM aPatronP WHERE id = " + id);
/*     */     } catch (SQLException e) {
/*  61 */       e.printStackTrace();
/*     */     }
/*  63 */     LinkedList listadoAp = new LinkedList();
/*  64 */     listadoAp.addAll(listadoApuntes(id));
/*     */     Iterator i$;
/*  65 */     if (listadoAp.size() > 0)
/*  66 */       for (i$ = listadoAp.iterator(); i$.hasNext(); ) { int apunte = ((Integer)i$.next()).intValue();
/*  67 */         borrarApunte(apunte);
/*     */       }
/*     */   }
/*     */ 
/*     */   public String[][] listaAsientos()
/*     */   {
/*  73 */     String[][] lista = (String[][])null;
/*  74 */     int i = -1;
/*     */     try {
/*  76 */       this.res = this.con.getRes("SELECT COUNT(id) FROM aPatronP");
/*  77 */       if (this.res.next()) {
/*  78 */         i = this.res.getInt(1);
/*     */       }
/*  80 */       if (i != -1) {
/*  81 */         lista = new String[2][i];
/*  82 */         i = 0;
/*  83 */         this.res = this.con.getRes("SELECT id,descripcion FROM aPatronP ORDER BY descripcion");
/*  84 */         while (this.res.next()) {
/*  85 */           lista[0][i] = this.res.getString(1);
/*  86 */           lista[1][i] = this.res.getString(2);
/*  87 */           i++;
/*     */         }
/*     */       }
/*  90 */       this.res.close();
/*     */     } catch (SQLException exc) {
/*  92 */       exc.printStackTrace();
/*     */     }
/*     */ 
/*  95 */     return lista;
/*     */   }
/*     */ 
/*     */   public TipoAsientoModelo datosAsiento(int id) {
/*  99 */     TipoAsientoModelo datos = null;
/*     */     try {
/* 101 */       this.res = this.con.getRes("SELECT * FROM aPatronP WHERE id = " + id);
/* 102 */       if (this.res.next()) {
/* 103 */         datos = new TipoAsientoModelo();
/* 104 */         datos.setId(id);
/* 105 */         datos.setDescripcion(this.res.getString(2));
/* 106 */         datos.setConcepto(this.res.getString(3));
/* 107 */         datos.setMarca(this.res.getString(4));
/*     */       }
/* 109 */       this.res.close();
/*     */     } catch (SQLException e) {
/* 111 */       e.printStackTrace();
/*     */     }
/* 113 */     return datos;
/*     */   }
/*     */ 
/*     */   public void crearApunte(int id, int cuenta, double importe, String CA)
/*     */   {
/*     */     try {
/* 119 */       this.con.getSentencia().executeUpdate("INSERT INTO aPatronS (aPatronP_id,cuenta,importe,CA) VALUES (" + id + "," + cuenta + "," + importe + ",'" + CA + "')");
/*     */     }
/*     */     catch (SQLException e)
/*     */     {
/* 125 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void modificarApunte() {
/*     */   }
/*     */ 
/*     */   private void borrarApunte(int id) {
/*     */     try {
/* 134 */       this.con.getSentencia().executeUpdate("DELETE FROM aPatronS WHERE id = " + id);
/*     */     } catch (SQLException e) {
/* 136 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void borrarApuntes(int idAP) {
/*     */     try {
/* 142 */       this.con.getSentencia().executeUpdate("DELETE FROM aPatronS WHERE aPatronP_id=" + idAP);
/*     */     } catch (SQLException e) {
/* 144 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public LinkedList<Integer> listadoApuntes(int aPatronP_id) {
/* 149 */     LinkedList lista = new LinkedList();
/*     */     try {
/* 151 */       this.res = this.con.getRes("SELECT id FROM aPatronS WHERE aPatronP_id = " + aPatronP_id);
/* 152 */       while (this.res.next()) {
/* 153 */         lista.add(Integer.valueOf(this.res.getInt(1)));
/*     */       }
/* 155 */       this.res.close();
/*     */     } catch (SQLException e) {
/* 157 */       e.printStackTrace();
/*     */     }
/* 159 */     return lista;
/*     */   }
/*     */ 
/*     */   public TipoApunteModelo datosApunte(int id)
/*     */   {
/* 182 */     TipoApunteModelo datos = null;
/*     */     try {
/* 184 */       this.res = this.con.getRes("SELECT * FROM aPatronS WHERE id = " + id);
/* 185 */       if (this.res.next()) {
/* 186 */         datos = new TipoApunteModelo();
/* 187 */         datos.setId(id);
/* 188 */         datos.setAPatronP_id(this.res.getInt(2));
/* 189 */         datos.setCuenta(this.res.getInt(3));
/* 190 */         datos.setImporte(this.res.getDouble(4));
/* 191 */         datos.setCA(this.res.getString(5));
/*     */       }
/* 193 */       this.res.close();
/*     */     } catch (SQLException e) {
/* 195 */       e.printStackTrace();
/*     */     }
/* 197 */     return datos;
/*     */   }
/*     */ 
/*     */   public void cerrarRs() {
/* 201 */     if (this.res != null) {
/*     */       try {
/* 203 */         this.res.close();
/*     */       } catch (SQLException sqlEx) {
/*     */       }
/* 206 */       this.res = null;
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.ManejoAsientosModelo
 * JD-Core Version:    0.6.2
 */