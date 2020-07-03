/*     */ package contaes.manejoDatos;
/*     */ 
/*     */ import contaes.manejoDatos.auxiliar.MySQLConection;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ 
/*     */ public class ManejoAgenda
/*     */   implements ManejoAgendaInterface
/*     */ {
/*     */   private ResultSet res;
/*     */   private MySQLConection con;
/*     */   private String ejercicio;
/*  13 */   private boolean existenDatos = false;
/*     */ 
/*     */   public ManejoAgenda(MySQLConection con, String ejercicio)
/*     */   {
/*  19 */     this.con = con;
/*  20 */     this.ejercicio = ejercicio;
/*     */   }
/*     */ 
/*     */   public boolean actualizar(String[] datos) {
/*  24 */     String sentencia = "UPDATE Terceros SET NIF ='" + datos[1] + "'," + "direccion ='" + datos[2] + "'," + "CP ='" + datos[3] + "'," + "localidad ='" + datos[4] + "'," + "provincia ='" + datos[5] + "'," + "telefono ='" + datos[6] + "'," + "fax ='" + datos[7] + "'," + "email ='" + datos[8] + "'," + "letras ='" + datos[11] + "'";
/*     */ 
/*  35 */     if (!datos[9].equals(""))
/*  36 */       sentencia = sentencia + ",idFormaPago =" + datos[9] + "";
/*     */     else {
/*  38 */       sentencia = sentencia + ",idFormaPago = NULL ";
/*     */     }
/*  40 */     if (!datos[10].equals(""))
/*  41 */       sentencia = sentencia + ",cuentaPago =" + datos[10] + "";
/*     */     else {
/*  43 */       sentencia = sentencia + ",cuentaPago = NULL ";
/*     */     }
/*  45 */     sentencia = sentencia + ",pais ='" + datos[12] + "'";
/*  46 */     sentencia = sentencia + " WHERE codigo=" + datos[0];
/*  47 */     if (datos.length > 8) {
/*     */       try {
/*  49 */         this.con.getSentencia().executeUpdate(sentencia);
/*     */       } catch (SQLException e) {
/*  51 */         e.printStackTrace();
/*  52 */         return false;
/*     */       }
/*     */     }
/*  55 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean crear(String[] datos) {
/*  59 */     Integer cuenta = Integer.valueOf(datos[0]);
/*  60 */     Integer idFormaPago = !datos[9].equals("") ? Integer.valueOf(Integer.parseInt(datos[9])) : null;
/*  61 */     Integer cuentaPago = !datos[10].equals("") ? Integer.valueOf(Integer.parseInt(datos[10])) : null;
/*  62 */     if (datos.length > 8) {
/*     */       try {
/*  64 */         this.con.getSentencia().executeUpdate("INSERT INTO Terceros VALUES(" + cuenta + ",'" + datos[1] + "'" + ",'" + datos[2] + "'" + ",'" + datos[3] + "'" + ",'" + datos[4] + "'" + ",'" + datos[5] + "'" + ",'" + datos[6] + "'" + ",'" + datos[7] + "'" + ",'" + datos[8] + "'" + ",'" + datos[11] + "'" + "," + idFormaPago + "" + "," + cuentaPago + "" + "," + datos[12] + "" + ")");
/*     */       }
/*     */       catch (SQLException e)
/*     */       {
/*  89 */         e.printStackTrace();
/*  90 */         return false;
/*     */       }
/*     */     }
/*  93 */     return true;
/*     */   }
/*     */ 
/*     */   public String[] datos(String cuenta) {
/*  97 */     String[] datosCuenta = new String[14];
/*  98 */     datosCuenta[0] = cuenta;
/*     */     try {
/* 100 */       this.res = this.con.getRes("SELECT nombre FROM scta" + this.ejercicio + " WHERE codigo=" + cuenta);
/*     */ 
/* 102 */       if (this.res.next()) {
/* 103 */         datosCuenta[1] = this.res.getString(1);
/* 104 */         this.res = this.con.getRes("SELECT * FROM Terceros WHERE codigo=" + cuenta);
/* 105 */         if (this.res.next()) {
/* 106 */           this.existenDatos = true;
/* 107 */           datosCuenta[2] = this.res.getString(2);
/* 108 */           datosCuenta[3] = this.res.getString(3);
/* 109 */           datosCuenta[4] = this.res.getString(4);
/* 110 */           datosCuenta[5] = this.res.getString(5);
/* 111 */           datosCuenta[6] = this.res.getString(6);
/* 112 */           datosCuenta[7] = this.res.getString(7);
/* 113 */           datosCuenta[8] = this.res.getString(8);
/* 114 */           datosCuenta[9] = this.res.getString(9);
/* 115 */           datosCuenta[12] = this.res.getString(10);
/* 116 */           datosCuenta[13] = Integer.toString(this.res.getInt(13));
/* 117 */           Integer fP = Integer.valueOf(this.res.getInt(11));
/* 118 */           if (fP != null) {
/* 119 */             datosCuenta[10] = Integer.toString(fP.intValue());
/*     */           }
/* 121 */           Integer cP = Integer.valueOf(this.res.getInt(12));
/* 122 */           if (cP != null)
/* 123 */             datosCuenta[11] = Integer.toString(cP.intValue());
/*     */         }
/*     */         else {
/* 126 */           this.existenDatos = false;
/*     */         }
/* 128 */         this.res.close();
/*     */       }
/*     */     } catch (SQLException exc) {
/* 131 */       exc.printStackTrace();
/*     */     }
/* 133 */     return datosCuenta;
/*     */   }
/*     */ 
/*     */   public String getCuentaBanco(String cuenta) {
/*     */     try {
/* 138 */       this.res = this.con.getRes("SELECT letras FROM Terceros WHERE codigo=" + cuenta);
/* 139 */       if (this.res.next()) {
/* 140 */         return this.res.getString(1);
/*     */       }
/* 142 */       this.res.close();
/*     */     } catch (SQLException exc) {
/* 144 */       exc.printStackTrace();
/*     */     }
/* 146 */     return "NO";
/*     */   }
/*     */ 
/*     */   public Integer formaPago(String cuenta) {
/* 150 */     Integer id = null;
/*     */     try {
/* 152 */       this.res = this.con.getRes("SELECT idFormaPago FROM Terceros WHERE codigo=" + cuenta);
/* 153 */       if (this.res.next()) {
/* 154 */         id = Integer.valueOf(this.res.getInt(1));
/*     */       }
/* 156 */       this.res.close();
/*     */     } catch (SQLException exc) {
/* 158 */       exc.printStackTrace();
/*     */     }
/* 160 */     return id;
/*     */   }
/*     */ 
/*     */   public int cuentaPago(String cuenta) {
/* 164 */     Integer cta = null;
/*     */     try {
/* 166 */       this.res = this.con.getRes("SELECT cuentaPago FROM Terceros WHERE codigo=" + cuenta);
/* 167 */       if (this.res.next()) {
/* 168 */         cta = Integer.valueOf(this.res.getInt(1));
/*     */       }
/* 170 */       this.res.close();
/*     */     } catch (SQLException exc) {
/* 172 */       exc.printStackTrace();
/*     */     }
/* 174 */     return cta.intValue();
/*     */   }
/*     */ 
/*     */   public boolean isExistenDatos()
/*     */   {
/* 181 */     return this.existenDatos;
/*     */   }
/*     */ 
/*     */   public void cerrarRs() {
/* 185 */     if (this.res != null) {
/*     */       try {
/* 187 */         this.res.close();
/*     */       } catch (SQLException sqlEx) {
/*     */       }
/* 190 */       this.res = null;
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.ManejoAgenda
 * JD-Core Version:    0.6.2
 */