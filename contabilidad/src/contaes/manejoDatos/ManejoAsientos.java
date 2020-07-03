/*     */ package contaes.manejoDatos;
/*     */ 
/*     */ import contaes.manejoDatos.auxiliar.MySQLConection;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ 
/*     */ public class ManejoAsientos
/*     */ {
/*     */   private MySQLConection con;
/*     */   private String ejercicio;
/*     */   private ResultSet res;
/*     */ 
/*     */   public ManejoAsientos(MySQLConection con, String ejercicio)
/*     */   {
/*  21 */     this.con = con;
/*  22 */     this.ejercicio = ejercicio;
/*     */   }
/*     */ 
/*     */   public int nuevoNumero() {
/*  26 */     int n = -1;
/*     */     try {
/*  28 */       this.res = this.con.getRes("SELECT MAX(numero) from asto" + this.ejercicio);
/*  29 */       if (this.res.next()) {
/*  30 */         n = this.res.getInt(1) + 1;
/*     */       }
/*  32 */       this.res.close();
/*     */     } catch (SQLException e) {
/*  34 */       e.printStackTrace();
/*     */     }
/*  36 */     return n;
/*     */   }
/*     */ 
/*     */   public int crear(TipoAsiento asiento) {
/*  40 */     return crear(asiento.getNumero(), asiento.getFecha(), asiento.getDocumento(), asiento.getMarca());
/*     */   }
/*     */ 
/*     */   public int crear(int numero, String fecha, String documento, String marca)
/*     */   {
/*  53 */     int id_asiento = -1;
/*     */     try {
/*  55 */       this.con.getSentencia().executeUpdate("INSERT INTO asto" + this.ejercicio + " (numero,fecha,documento,marca) VALUES(" + numero + ",'" + fecha + "','" + documento + "','" + marca + "')");
/*     */ 
/*  60 */       this.res = this.con.getRes("SELECT LAST_INSERT_ID() FROM asto" + this.ejercicio);
/*  61 */       if (this.res.next()) {
/*  62 */         id_asiento = this.res.getInt(1);
/*     */       }
/*  64 */       this.res.close();
/*     */     }
/*     */     catch (SQLException e) {
/*  67 */       e.printStackTrace();
/*  68 */       return -1;
/*     */     }
/*  70 */     return id_asiento;
/*     */   }
/*     */ 
/*     */   public void modificar(TipoAsiento asiento) {
/*  74 */     modificar(asiento.getId_asiento(), asiento.getFecha(), asiento.getDocumento(), asiento.getMarca());
/*     */   }
/*     */ 
/*     */   public void modificar(int id_asiento, String fecha, String documento, String marca) {
/*     */     try {
/*  79 */       this.con.getSentencia().executeUpdate("UPDATE asto" + this.ejercicio + " SET " + "  fecha='" + fecha + "'" + ", documento='" + documento + "'" + ", marca='" + marca + "'" + "  WHERE id_asiento=" + id_asiento);
/*     */     }
/*     */     catch (SQLException e)
/*     */     {
/*  85 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void borrar(int id_asiento) {
/*  90 */     if (borradoApuntes(id_asiento))
/*     */       try {
/*  92 */         this.con.getSentencia().executeUpdate("DELETE FROM asto" + this.ejercicio + " WHERE id_asiento=" + id_asiento);
/*     */       }
/*     */       catch (SQLException e) {
/*  95 */         e.printStackTrace();
/*     */       }
/*     */   }
/*     */ 
/*     */   private boolean borradoApuntes(int id_asiento)
/*     */   {
/* 102 */     LinkedList id = new LinkedList();
/* 103 */     id.addAll(apuntesEnAsiento(id_asiento));
/*     */ 
/* 105 */     if (id.size() == 0) {
/* 106 */       return false;
/*     */     }
/* 108 */     ManejoApuntes apuntes = new ManejoApuntes(this.con, this.ejercicio);
/* 109 */     for (Iterator i$ = id.iterator(); i$.hasNext(); ) { int i = ((Integer)i$.next()).intValue();
/* 110 */       apuntes.borrar(i);
/*     */     }
/* 112 */     return true;
/*     */   }
/*     */ 
/*     */   public TipoAsiento datos(boolean porNumero, int numero)
/*     */   {
/* 124 */     String campoBusqueda = "numero";
/* 125 */     if (!porNumero) {
/* 126 */       campoBusqueda = "id_asiento";
/*     */     }
/* 128 */     TipoAsiento asiento = null;
/*     */     try {
/* 130 */       this.res = this.con.getRes("SELECT * FROM asto" + this.ejercicio + " WHERE " + campoBusqueda + "=" + numero);
/* 131 */       if (this.res.next()) {
/* 132 */         asiento = new TipoAsiento(this.res.getInt(1), numero, this.res.getString(3), this.res.getString(4), this.res.getString(5));
/*     */       }
/*     */ 
/* 135 */       this.res.close();
/*     */     } catch (SQLException e) {
/* 137 */       e.printStackTrace();
/*     */     }
/* 139 */     return asiento;
/*     */   }
/*     */ 
/*     */   public int existeAsiento(Integer numero, String fecha, String doc, String marca) {
/* 143 */     int idAsiento = -1;
/* 144 */     String query = "SELECT id_asiento FROM asto" + this.ejercicio;
/* 145 */     boolean where = false;
/* 146 */     if ((numero != null) && (numero.intValue() > 0)) {
/* 147 */       if (where) {
/* 148 */         query = query + " AND numero = " + numero;
/*     */       } else {
/* 150 */         query = query + " WHERE numero = " + numero;
/* 151 */         where = true;
/*     */       }
/*     */     }
/* 154 */     if (fecha != null) {
/* 155 */       if (where) {
/* 156 */         query = query + " AND fecha = '" + fecha + "'";
/*     */       } else {
/* 158 */         query = query + " WHERE fecha = '" + fecha + "'";
/* 159 */         where = true;
/*     */       }
/*     */     }
/* 162 */     if ((doc != null) && (doc.length() > 0)) {
/* 163 */       if (where) {
/* 164 */         query = query + " AND documento LIKE '" + doc + "'";
/*     */       } else {
/* 166 */         query = query + " WHERE documento LIKE '" + doc + "'";
/* 167 */         where = true;
/*     */       }
/*     */     }
/* 170 */     if ((marca != null) && (marca.length() > 0))
/* 171 */       if (where) {
/* 172 */         query = query + " AND marca LIKE '" + marca + "'";
/*     */       } else {
/* 174 */         query = query + " WHERE marca LIKE '" + marca + "'";
/* 175 */         where = true;
/*     */       }
/*     */     try
/*     */     {
/* 179 */       this.res = this.con.getRes(query);
/* 180 */       if (this.res.next()) {
/* 181 */         idAsiento = this.res.getInt(1);
/*     */       }
/* 183 */       this.res.close();
/*     */     } catch (SQLException e) {
/* 185 */       e.printStackTrace();
/*     */     }
/* 187 */     return idAsiento;
/*     */   }
/*     */ 
/*     */   public LinkedList<Integer> apuntesEnAsiento(int id_asiento) {
/* 191 */     LinkedList apuntes = new LinkedList();
/*     */     try
/*     */     {
/* 194 */       this.res = this.con.getRes("SELECT id_apunte FROM apte" + this.ejercicio + " WHERE id_asiento=" + id_asiento + " ORDER BY id_apunte");
/*     */ 
/* 196 */       while (this.res.next()) {
/* 197 */         apuntes.add(Integer.valueOf(this.res.getInt(1)));
/*     */       }
/* 199 */       this.res.close();
/*     */     } catch (SQLException e) {
/* 201 */       e.printStackTrace();
/*     */     }
/* 203 */     return apuntes;
/*     */   }
/*     */ 
/*     */   public ArrayList<TipoApunte> getApuntes(int id_asiento) {
/* 207 */     ArrayList apuntes = new ArrayList();
/*     */     try {
/* 209 */       this.res = this.con.getRes("SELECT * FROM apte" + this.ejercicio + " WHERE id_asiento=" + id_asiento + " ORDER BY id_apunte");
/*     */ 
/* 211 */       while (this.res.next()) {
/* 212 */         TipoApunte apunte = new TipoApunte();
/* 213 */         apunte = new TipoApunte(this.res.getInt(1), this.res.getInt(2), this.res.getInt(3), this.res.getString(4), this.res.getString(5), this.res.getDouble(6));
/*     */ 
/* 215 */         apuntes.add(apunte);
/*     */       }
/* 217 */       this.res.close();
/*     */     } catch (SQLException e) {
/* 219 */       e.printStackTrace();
/*     */     }
/* 221 */     return apuntes;
/*     */   }
/*     */ 
/*     */   public int numeroAsiento(int id_Asiento) {
/*     */     try {
/* 226 */       this.res = this.con.getRes("SELECT numero FROM asto" + this.ejercicio + "" + " WHERE id_asiento = " + id_Asiento);
/*     */ 
/* 228 */       if (this.res.next()) {
/* 229 */         return this.res.getInt(1);
/*     */       }
/* 231 */       this.res.close();
/*     */     } catch (SQLException e) {
/* 233 */       e.printStackTrace();
/*     */     }
/* 235 */     return -1;
/*     */   }
/*     */ 
/*     */   public int primero() {
/*     */     try {
/* 240 */       this.res = this.con.getRes("SELECT MIN(numero) FROM asto" + this.ejercicio);
/* 241 */       if (this.res.next()) {
/* 242 */         return this.res.getInt(1);
/*     */       }
/* 244 */       this.res.close();
/*     */     } catch (SQLException e) {
/* 246 */       e.printStackTrace();
/*     */     }
/* 248 */     return -1;
/*     */   }
/*     */ 
/*     */   public int ultimo() {
/*     */     try {
/* 253 */       this.res = this.con.getRes("SELECT MAX(numero) FROM asto" + this.ejercicio);
/* 254 */       if (this.res.next()) {
/* 255 */         return this.res.getInt(1);
/*     */       }
/* 257 */       this.res.close();
/*     */     } catch (SQLException e) {
/* 259 */       e.printStackTrace();
/*     */     }
/* 261 */     return -1;
/*     */   }
/*     */ 
/*     */   public void cerrarRs() {
/* 265 */     if (this.res != null) {
/*     */       try {
/* 267 */         this.res.close();
/*     */       } catch (SQLException sqlEx) {
/*     */       }
/* 270 */       this.res = null;
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.ManejoAsientos
 * JD-Core Version:    0.6.2
 */