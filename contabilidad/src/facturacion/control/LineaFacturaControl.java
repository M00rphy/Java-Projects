/*     */ package facturacion.control;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.manejoDatos.ManejoTiposIVA;
/*     */ import contaes.manejoDatos.TipoIVA;
/*     */ import contaes.manejoDatos.auxiliar.MySQLConection;
/*     */ import facturacion.control.interfaces.LineaFacturaControlInterface;
/*     */ import facturacion.model.LineaFactura;
/*     */ import facturacion.model.Producto;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class LineaFacturaControl
/*     */   implements LineaFacturaControlInterface
/*     */ {
/*     */   private MySQLConection con;
/*     */   private ResultSet res;
/*     */   private String tabla;
/*     */   private boolean ventas;
/*     */ 
/*     */   public LineaFacturaControl(MySQLConection con, boolean ventas)
/*     */   {
/*  32 */     this.con = con;
/*  33 */     this.ventas = ventas;
/*  34 */     if (ventas) {
/*  35 */       this.tabla = "lineafactura";
/*     */     }
/*     */     else
/*  38 */       this.tabla = "lineafacturacompras";
/*     */   }
/*     */ 
/*     */   public Integer crear(LineaFactura linea)
/*     */     throws Exception
/*     */   {
/*  44 */     Integer retorno = Integer.valueOf(-1);
/*     */     try {
/*  46 */       String cadenaSQL = "INSERT INTO " + this.tabla + " (idFactura,idProducto,concepto,base,tipoiva,total,unidades) VALUES(?,?,?,?,?,?,?)";
/*  47 */       PreparedStatement ps = this.con.getCon().prepareStatement(cadenaSQL);
/*  48 */       ps.setInt(1, linea.getIdFactura().intValue());
/*  49 */       ps.setInt(2, linea.getIdProducto().getId().intValue());
/*  50 */       ps.setString(3, linea.getConcepto());
/*  51 */       ps.setDouble(4, linea.getBase().doubleValue());
/*  52 */       ps.setInt(5, linea.getTipoIva().getId());
/*  53 */       ps.setDouble(6, linea.getTotal().doubleValue());
/*  54 */       ps.setDouble(7, linea.getUnidades().doubleValue());
/*  55 */       ps.execute();
/*  56 */       cadenaSQL = "SELECT LAST_INSERT_ID() FROM " + this.tabla;
/*  57 */       ps = this.con.getCon().prepareStatement(cadenaSQL);
/*  58 */       this.res = ps.executeQuery();
/*  59 */       if (this.res.next())
/*  60 */         retorno = Integer.valueOf(this.res.getInt(1));
/*     */     }
/*     */     catch (SQLException exc) {
/*  63 */       exc.printStackTrace();
/*     */     }
/*  65 */     return retorno;
/*     */   }
/*     */ 
/*     */   public boolean modificar(LineaFactura linea) throws Exception
/*     */   {
/*  70 */     boolean retorno = false;
/*     */     try {
/*  72 */       String cadenaSQL = "UPDATE " + this.tabla + " SET idFactura = ?, idProducto = ?, concepto = ?," + "base = ?, tipoiva = ?, total = ?, unidades = ? WHERE id = ?";
/*     */ 
/*  74 */       PreparedStatement ps = this.con.getCon().prepareStatement(cadenaSQL);
/*  75 */       ps.setInt(1, linea.getIdFactura().intValue());
/*  76 */       ps.setInt(2, linea.getIdProducto().getId().intValue());
/*  77 */       ps.setString(3, linea.getConcepto());
/*  78 */       ps.setDouble(4, linea.getBase().doubleValue());
/*  79 */       ps.setInt(5, linea.getTipoIva().getId());
/*  80 */       ps.setDouble(6, linea.getTotal().doubleValue());
/*  81 */       ps.setDouble(7, linea.getUnidades().doubleValue());
/*  82 */       ps.setInt(8, linea.getId().intValue());
/*  83 */       int result = ps.executeUpdate();
/*  84 */       if (result > 0)
/*  85 */         retorno = true;
/*     */     }
/*     */     catch (SQLException exc) {
/*  88 */       exc.printStackTrace();
/*     */     }
/*  90 */     return retorno;
/*     */   }
/*     */ 
/*     */   public boolean borrar(LineaFactura linea) throws Exception
/*     */   {
/*  95 */     boolean retorno = false;
/*     */     try {
/*  97 */       String cadenaSQL = "DELETE FROM " + this.tabla + " WHERE id = ?";
/*  98 */       PreparedStatement ps = this.con.getCon().prepareStatement(cadenaSQL);
/*  99 */       ps.setInt(1, linea.getId().intValue());
/* 100 */       int result = ps.executeUpdate();
/* 101 */       if (result > 0)
/* 102 */         retorno = true;
/*     */     }
/*     */     catch (SQLException exc) {
/* 105 */       exc.printStackTrace();
/*     */     }
/* 107 */     return retorno;
/*     */   }
/*     */ 
/*     */   public boolean borrarPorFactura(int idFactura) throws Exception {
/* 111 */     boolean retorno = false;
/*     */     try {
/* 113 */       String cadenaSQL = "DELETE FROM " + this.tabla + " WHERE idFactura = ?";
/* 114 */       PreparedStatement ps = this.con.getCon().prepareStatement(cadenaSQL);
/* 115 */       ps.setInt(1, idFactura);
/* 116 */       int result = ps.executeUpdate();
/* 117 */       if (result > 0)
/* 118 */         retorno = true;
/*     */     }
/*     */     catch (SQLException exc) {
/* 121 */       exc.printStackTrace();
/*     */     }
/* 123 */     return retorno;
/*     */   }
/*     */ 
/*     */   public LineaFactura linea(Integer id) throws Exception
/*     */   {
/* 128 */     LineaFactura linea = null;
/*     */     try {
/* 130 */       String cadenaSQL = "SELECT * FROM " + this.tabla + " WHERE id = " + id;
/* 131 */       this.res = this.con.getRes(cadenaSQL);
/* 132 */       if (this.res.next()) {
/* 133 */         Integer idFactura = Integer.valueOf(this.res.getInt(2));
/* 134 */         int idP = this.res.getInt(3);
/* 135 */         String concepto = this.res.getString(4);
/* 136 */         Double base = Double.valueOf(this.res.getDouble(5));
/* 137 */         int idTI = this.res.getInt(6);
/* 138 */         Double total = Double.valueOf(this.res.getDouble(7));
/* 139 */         Double unidades = Double.valueOf(this.res.getDouble(8));
/* 140 */         AlmacenControl aC = new AlmacenControl();
/* 141 */         Producto producto = aC.getProducto(Integer.valueOf(idP), this.ventas);
/* 142 */         aC.cerrarConexion();
/* 143 */         ManejoTiposIVA mTI = new ManejoTiposIVA(Inicio.getCGeneral());
/* 144 */         TipoIVA tipoIva = mTI.getTipoIVA(idTI);
/* 145 */         linea = new LineaFactura(id, idFactura, producto, concepto, base, tipoIva, total, unidades);
/*     */       }
/*     */     } catch (SQLException exc) {
/* 148 */       exc.printStackTrace();
/*     */     }
/* 150 */     return linea;
/*     */   }
/*     */ 
/*     */   public ArrayList<LineaFactura> lineas(Integer idFactura) throws Exception
/*     */   {
/* 155 */     ArrayList lineas = new ArrayList();
/*     */     try {
/* 157 */       String cadenaSQL = "SELECT * FROM " + this.tabla + " WHERE idFactura = " + idFactura;
/* 158 */       LineaFactura linea = null;
/* 159 */       AlmacenControl aC = new AlmacenControl();
/* 160 */       ManejoTiposIVA mTI = new ManejoTiposIVA(Inicio.getCGeneral());
/* 161 */       this.res = this.con.getRes(cadenaSQL);
/* 162 */       while (this.res.next()) {
/* 163 */         Integer id = Integer.valueOf(this.res.getInt(1));
/* 164 */         int idP = this.res.getInt(3);
/* 165 */         String concepto = this.res.getString(4);
/* 166 */         Double base = Double.valueOf(this.res.getDouble(5));
/* 167 */         int idTI = this.res.getInt(6);
/* 168 */         Double total = Double.valueOf(this.res.getDouble(7));
/* 169 */         Double unidades = Double.valueOf(this.res.getDouble(8));
/* 170 */         Producto producto = aC.getProducto(Integer.valueOf(idP), this.ventas);
/* 171 */         TipoIVA tipoIva = mTI.getTipoIVA(idTI);
/* 172 */         linea = new LineaFactura(id, idFactura, producto, concepto, base, tipoIva, total, unidades);
/* 173 */         lineas.add(linea);
/*     */       }
/* 175 */       aC.cerrarConexion();
/* 176 */       mTI.cerrarRs();
/*     */     } catch (SQLException exc) {
/* 178 */       exc.printStackTrace();
/*     */     }
/*     */ 
/* 181 */     return lineas;
/*     */   }
/*     */ 
/*     */   public void cerrarRs()
/*     */   {
/* 186 */     if (this.res != null) {
/*     */       try {
/* 188 */         this.res.close(); } catch (SQLException sqlEx) {
/*     */       }
/* 190 */       this.res = null;
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     facturacion.control.LineaFacturaControl
 * JD-Core Version:    0.6.2
 */