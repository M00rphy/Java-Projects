/*     */ package contaes.manejoDatos;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.manejoDatos.auxiliar.MySQLConection;
/*     */ import facturacion.control.AlmacenControl;
/*     */ import facturacion.control.FacturaControl;
/*     */ import internationalization.Mensajes;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import java.util.ArrayList;
/*     */ import java.util.LinkedList;
import java.util.List;
/*     */ 
/*     */ public class ManejoSubcuentas
/*     */ {
/*     */   private MySQLConection con;
/*     */   private String ejercicio;
/*     */   private ResultSet res;
/*     */ 
/*     */   public ManejoSubcuentas(MySQLConection con, String ejercicio)
/*     */   {
/*  25 */     this.con = con;
/*  26 */     this.ejercicio = ejercicio;
/*     */   }
/*     */ 
/*     */   public boolean crear(int codigo, String nombre, String gbalance) {
/*     */     try {
/*  31 */       this.con.getSentencia().executeUpdate("INSERT INTO scta" + this.ejercicio + " (codigo,nombre,gbalance) VALUES" + " (" + codigo + ",'" + nombre + "','" + gbalance + "')");
/*     */     }
/*     */     catch (SQLException e)
/*     */     {
/*  35 */       e.printStackTrace();
/*  36 */       return false;
/*     */     }
/*  38 */     return true;
/*     */   }
/*     */ 
/*     */   public void modificar(int codigo, String nombre, String gbalance) {
/*     */     try {
/*  43 */       this.con.getSentencia().executeUpdate("UPDATE scta" + this.ejercicio + " SET nombre='" + nombre + "'," + " gbalance ='" + gbalance + "'" + " WHERE codigo =" + codigo);
/*     */     }
/*     */     catch (SQLException e)
/*     */     {
/*  48 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public int borrar(int codigo)
/*     */   {
/*  64 */     int isSubcuentaEnUso = 0;
/*     */     try {
/*  66 */       this.res = this.con.getRes("SELECT * FROM apte" + this.ejercicio + " WHERE cuenta = " + codigo);
/*     */ 
/*  68 */       if (this.res.next()) {
/*  69 */         isSubcuentaEnUso = 1;
/*     */       }
/*  71 */       if (isSubcuentaEnUso == 0) {
/*  72 */         ManejoTiposIVA mTI = new ManejoTiposIVA(Inicio.getCGeneral());
/*  73 */         if (mTI.cuentaUtilizada(codigo)) {
/*  74 */           isSubcuentaEnUso = 2;
/*     */         }
/*  76 */         mTI.cerrarRs();
/*     */       }
/*  78 */       if (isSubcuentaEnUso == 0) {
/*  79 */         FacturaControl fC = new FacturaControl(Inicio.getCFacturacion(), true);
/*  80 */         isSubcuentaEnUso = fC.isSubcuentaEnUso(codigo);
/*  81 */         if (isSubcuentaEnUso == 0) {
/*  82 */           fC = new FacturaControl(Inicio.getCFacturacion(), false);
/*  83 */           isSubcuentaEnUso = fC.isSubcuentaEnUso(codigo);
/*     */         }
/*  85 */         fC.cerrarRs();
/*     */       }
/*  87 */       if (isSubcuentaEnUso == 0) {
/*  88 */         AlmacenControl aC = new AlmacenControl();
/*  89 */         isSubcuentaEnUso = aC.isSubcuentaEnUso(codigo);
/*  90 */         aC.cerrarConexion();
/*     */       }
/*  92 */       if (isSubcuentaEnUso == 0)
/*  93 */         this.con.getSentencia().executeUpdate("DELETE FROM scta" + this.ejercicio + " WHERE codigo=" + codigo);
/*     */     }
/*     */     catch (SQLException e)
/*     */     {
/*  97 */       e.printStackTrace();
/*     */     }
/*  99 */     return isSubcuentaEnUso;
/*     */   }
/*     */ 
/*     */   public void actualizarImportes(boolean esSuma, int cuenta, String DH, double importe) {
/* 103 */     if (!esSuma)
/* 104 */       importe = -1.0D * importe;
/*     */     try
/*     */     {
/* 107 */       if (DH.equals(Mensajes.getString("debeA"))) {
/* 108 */         this.con.getSentencia().executeUpdate("UPDATE scta" + this.ejercicio + " SET debe = debe+" + importe + " WHERE codigo=" + cuenta);
/*     */       }
/* 111 */       else if (DH.equals(Mensajes.getString("haberA"))) {
/* 112 */         this.con.getSentencia().executeUpdate("UPDATE scta" + this.ejercicio + " SET haber = haber+" + importe + " WHERE codigo=" + cuenta);
/*     */       }
/*     */ 
/* 116 */       this.con.getSentencia().executeUpdate("UPDATE scta" + this.ejercicio + " SET saldo = debe - haber" + " WHERE codigo=" + cuenta);
/*     */     }
/*     */     catch (SQLException e)
/*     */     {
/* 120 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public TipoSubcuenta datos(int codigoCuenta) {
/* 125 */     TipoSubcuenta subcuenta = null;
/*     */     try {
/* 127 */       this.res = this.con.getRes("SELECT nombre,gbalance,debe,haber,saldo FROM scta" + this.ejercicio + " WHERE codigo = " + codigoCuenta);
/*     */ 
/* 129 */       if (this.res.next()) {
/* 130 */         subcuenta = new TipoSubcuenta(codigoCuenta, this.res.getString(1), this.res.getString(2), this.res.getDouble(3), this.res.getDouble(4), this.res.getDouble(5));
/*     */       }
/*     */ 
/* 133 */       this.res.close();
/*     */     } catch (SQLException e) {
/* 135 */       e.printStackTrace();
/*     */     }
/* 137 */     return subcuenta;
/*     */   }
/*     */ 
/*     */   public LinkedList<Integer> listadoSubcuentas(int inicio, int fin) {
/* 141 */     LinkedList lista = new LinkedList();
/*     */     try {
/* 143 */       this.res = this.con.getRes("SELECT codigo FROM scta" + this.ejercicio + " WHERE codigo BETWEEN " + inicio + " AND " + fin + " ORDER BY codigo");
/*     */ 
/* 146 */       while (this.res.next()) {
/* 147 */         lista.add(Integer.valueOf(this.res.getInt(1)));
/*     */       }
/* 149 */       this.res.close();
/*     */     } catch (SQLException e) {
/* 151 */       e.printStackTrace();
/*     */     }
/* 153 */     return lista;
/*     */   }
/*     */ 
/*     */   public LinkedList<String> listaSubcuentasTitle(int inicio, int fin)
/*     */   {
/* 178 */     LinkedList lista = new LinkedList();
/*     */     try {
/* 180 */       this.res = this.con.getRes("SELECT nombre FROM scta" + this.ejercicio + " WHERE codigo BETWEEN " + inicio + " AND " + fin + " ORDER BY codigo");
/*     */ 
/* 183 */       while (this.res.next()) {
/* 184 */         lista.add(this.res.getString(1));
/*     */       }
/* 186 */       this.res.close();
/*     */     } catch (SQLException e) {
/* 188 */       e.printStackTrace();
/*     */     }
/* 190 */     return lista;
/*     */   }
/*     */ 
/*     */   public ArrayList<TipoSubcuenta> listaSubcuentas(int inicio, int fin) {
/* 194 */     ArrayList lista = new ArrayList();
/*     */     try
/*     */     {
/* 197 */       this.res = this.con.getRes("SELECT codigo,nombre,gbalance,debe,haber,saldo FROM scta" + this.ejercicio + " WHERE codigo BETWEEN " + inicio + " AND " + fin + " ORDER BY codigo");
/*     */ 
/* 200 */       while (this.res.next()) {
/* 201 */         TipoSubcuenta subcuenta = new TipoSubcuenta(this.res.getInt(1), this.res.getString(2), this.res.getString(3), this.res.getDouble(4), this.res.getDouble(5), this.res.getDouble(6));
/*     */ 
/* 203 */         lista.add(subcuenta);
/*     */       }
/* 205 */       this.res.close();
/*     */     } catch (SQLException e) {
/* 207 */       e.printStackTrace();
/*     */     }
/* 209 */     return lista;
/*     */   }
/*     */ 
/*     */   public String buscarNumero(String cadena) {
/* 213 */     String numero = "0";
/*     */     try {
/* 215 */       this.res = this.con.getRes("Select codigo FROM scta" + this.ejercicio + " WHERE nombre LIKE '%" + cadena + "%'");
/*     */ 
/* 217 */       if (this.res.next())
/* 218 */         numero = this.res.getString(1);
/*     */     }
/*     */     catch (SQLException e) {
/* 221 */       e.printStackTrace();
/*     */     }
/* 223 */     return numero;
/*     */   }
/*     */ 
/*     */   public boolean existeSubcuenta(int cuenta) {
/*     */     try {
/* 228 */       this.res = this.con.getRes("Select codigo FROM scta" + this.ejercicio + " WHERE codigo = " + cuenta);
/*     */ 
/* 230 */       if (this.res.next())
/* 231 */         return true;
/*     */     }
/*     */     catch (SQLException e) {
/* 234 */       e.printStackTrace();
/*     */     }
/* 236 */     return false;
/*     */   }
/*     */ 
/*     */   public Integer getOrigen(int subcuenta)
/*     */   {
/* 246 */     Integer origen = null;
/*     */     try {
/* 248 */       this.res = this.con.getRes("SELECT p.situacion FROM contaes.paises p LEFT JOIN Terceros t ON p.id = t.pais WHERE t.codigo =" + subcuenta);
/*     */ 
/* 250 */       if (this.res.next()) {
/* 251 */         origen = Integer.valueOf(this.res.getInt(1));
/*     */       }
/* 253 */       this.res.close();
/*     */     } catch (SQLException exc) {
/* 255 */       exc.printStackTrace();
/*     */     }
/* 257 */     return origen;
/*     */   }
/*     */ 
/*     */   public ArrayList<TipoBanco> getBancosConCcc() {
/* 261 */     ArrayList temp = new ArrayList();
/* 262 */     ArrayList lista = new ArrayList();
/*     */     try
/*     */     {
/* 265 */       this.res = this.con.getRes("SELECT codigo,nombre FROM scta" + this.ejercicio + " WHERE codigo BETWEEN 57200000 AND 57599999" + " ORDER BY codigo");
/*     */ 
/* 268 */       while (this.res.next()) {
/* 269 */         int codigo = this.res.getInt(1);
/* 270 */         String nombre = this.res.getString(2);
/* 271 */         TipoBanco banco = new TipoBanco(codigo, nombre);
/* 272 */         temp.add(banco);
/*     */       }
/* 274 */       this.res.close();
/* 275 */       for (TipoBanco tipoBanco :(List<TipoBanco>) temp) {
/* 276 */         this.res = this.con.getRes("SELECT letras FROM Terceros WHERE codigo=" + tipoBanco.getCodigo());
/* 277 */         if (this.res.next()) {
/* 278 */           String ccc = this.res.getString(1);
/* 279 */           if ((!ccc.equals("NO")) && (!ccc.equals(""))) {
/* 280 */             TipoBanco banco = new TipoBanco(tipoBanco.getCodigo(), tipoBanco.getNombre());
/* 281 */             banco.setCcc(ccc);
/* 282 */             lista.add(banco);
/*     */           }
/*     */         }
/* 285 */         this.res.close();
/*     */       }
/*     */     } catch (SQLException exc) {
/* 288 */       exc.printStackTrace();
/*     */     }
/*     */ 
/* 291 */     return lista;
/*     */   }
/*     */ 
/*     */   public void cerrarRs() {
/* 295 */     if (this.res != null) {
/*     */       try {
/* 297 */         this.res.close();
/*     */       } catch (SQLException sqlEx) {
/*     */       }
/* 300 */       this.res = null;
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.ManejoSubcuentas
 * JD-Core Version:    0.6.2
 */