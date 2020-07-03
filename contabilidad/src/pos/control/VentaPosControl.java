/*     */ package pos.control;
/*     */ 
/*     */ import almacen2.data.MySQLConection;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
import java.util.List;
/*     */ import pos.model.VentaPOS;
/*     */ 
/*     */ public class VentaPosControl
/*     */ {
/*     */   private MySQLConection con;
/*     */   private ResultSet res;
/*     */ 
/*     */   public VentaPosControl(MySQLConection con)
/*     */   {
/*  25 */     this.con = con;
/*     */   }
/*     */ 
/*     */   public int crear(VentaPOS venta) {
/*  29 */     int id = -1;
/*     */     try {
/*  31 */       String cadenaSQL = "INSERT INTO ventaspos (idticket,idproducto,descripcion,unidades,importe) VALUES(?,?,?,?,?)";
/*  32 */       PreparedStatement ps = this.con.getCon().prepareStatement(cadenaSQL);
/*  33 */       ps.setInt(1, venta.getIdTicket());
/*  34 */       ps.setInt(2, venta.getIdProducto());
/*  35 */       ps.setString(3, venta.getDescripcion());
/*  36 */       ps.setInt(4, venta.getUnidades());
/*  37 */       ps.setDouble(5, venta.getImporte());
/*     */ 
/*  39 */       ps.execute();
/*  40 */       cadenaSQL = "SELECT LAST_INSERT_ID() FROM mediospago";
/*  41 */       ps = this.con.getCon().prepareStatement(cadenaSQL);
/*  42 */       this.res = ps.executeQuery();
/*  43 */       if (this.res.next()) {
/*  44 */         id = this.res.getInt(1);
/*     */       }
/*  46 */       if ((id != -1) && (!venta.getNota().equals("")))
/*  47 */         insertNota(id, venta.getNota());
/*     */     }
/*     */     catch (SQLException exc) {
/*  50 */       exc.printStackTrace();
/*     */     }
/*  52 */     return id;
/*     */   }
/*     */ 
/*     */   public boolean modificar(VentaPOS venta) {
/*  56 */     boolean modificado = false;
/*     */     try {
/*  58 */       String cadenaSQL = "UPDATE ventaspos SET idticket = ?, idproducto = ?, descripcion = ?,unidades = ?, importe = ? WHERE id = ?";
/*     */ 
/*  60 */       PreparedStatement ps = this.con.getCon().prepareStatement(cadenaSQL);
/*  61 */       ps.setInt(1, venta.getIdTicket());
/*  62 */       ps.setInt(2, venta.getIdProducto());
/*  63 */       ps.setString(3, venta.getDescripcion());
/*  64 */       ps.setInt(4, venta.getUnidades());
/*  65 */       ps.setDouble(5, venta.getImporte());
/*  66 */       ps.setInt(6, venta.getId().intValue());
/*     */ 
/*  68 */       int result = ps.executeUpdate();
/*  69 */       if (venta.getId().intValue() != -1) {
/*  70 */         insertNota(venta.getId().intValue(), venta.getNota());
/*     */       }
/*  72 */       if (result > 0)
/*  73 */         modificado = true;
/*     */     }
/*     */     catch (SQLException exc) {
/*  76 */       exc.printStackTrace();
/*     */     }
/*  78 */     return modificado;
/*     */   }
/*     */ 
/*     */   public boolean borrar(VentaPOS venta) {
/*  82 */     boolean borrado = false;
/*     */     try {
/*  84 */       String cadenaSQL = "DELETE FROM ventaspos WHERE id = ?";
/*  85 */       PreparedStatement ps = this.con.getCon().prepareStatement(cadenaSQL);
/*  86 */       ps.setInt(1, venta.getId().intValue());
/*  87 */       int result = ps.executeUpdate();
/*  88 */       if (result > 0) {
/*  89 */         borrarNota(venta.getId().intValue());
/*  90 */         borrado = true;
/*     */       }
/*     */     } catch (SQLException exc) {
/*  93 */       exc.printStackTrace();
/*     */     }
/*  95 */     return borrado;
/*     */   }
/*     */ 
/*     */   private void insertNota(int idVenta, String nota) {
/*     */     try {
/* 100 */       String cadenaSQL = "SELECT * FROM notaspos WHERE idventa = ?";
/* 101 */       PreparedStatement ps = this.con.getCon().prepareStatement(cadenaSQL);
/* 102 */       ps.setInt(1, idVenta);
/* 103 */       this.res = ps.executeQuery();
/* 104 */       if (this.res.next()) {
/* 105 */         cadenaSQL = "UPDATE notaspos SET nota = ? WHERE idventa = ?";
/*     */       }
/*     */       else {
/* 108 */         cadenaSQL = "INSERT INTO notaspos (nota, idventa) VALUES (?,?)";
/*     */       }
/* 110 */       ps = this.con.getCon().prepareStatement(cadenaSQL);
/* 111 */       ps.setString(1, nota);
/* 112 */       ps.setInt(2, idVenta);
/* 113 */       ps.executeUpdate();
/*     */     } catch (SQLException ex) {
/* 115 */       ex.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void borrarNota(int idVenta) {
/*     */     try {
/* 121 */       String cadenaSQL = "DELETE FROM notaspos WHERE idventa = ?";
/* 122 */       PreparedStatement ps = this.con.getCon().prepareStatement(cadenaSQL);
/* 123 */       ps.setInt(1, idVenta);
/* 124 */       ps.executeUpdate();
/*     */     } catch (SQLException exc) {
/* 126 */       exc.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   private String getNota(int idVenta) {
/* 131 */     String nota = "";
/*     */     try {
/* 133 */       String cadenaSQL = "SELECT nota FROM notaspos WHERE idventa = ?";
/* 134 */       PreparedStatement ps = this.con.getCon().prepareStatement(cadenaSQL);
/* 135 */       ps.setInt(1, idVenta);
/* 136 */       this.res = ps.executeQuery();
/* 137 */       if (this.res.next())
/* 138 */         nota = this.res.getString(1);
/*     */     }
/*     */     catch (SQLException ex) {
/* 141 */       ex.printStackTrace();
/*     */     }
/* 143 */     return nota;
/*     */   }
/*     */ 
/*     */   public boolean borrar(int idTicket) {
/* 147 */     boolean borrado = false;
/*     */     try {
/* 149 */       String cadenaSQL = "DELETE FROM ventaspos WHERE idticket = ?";
/* 150 */       PreparedStatement ps = this.con.getCon().prepareStatement(cadenaSQL);
/* 151 */       ps.setInt(1, idTicket);
/* 152 */       int result = ps.executeUpdate();
/* 153 */       if (result > 0)
/* 154 */         borrado = true;
/*     */     }
/*     */     catch (SQLException exc) {
/* 157 */       exc.printStackTrace();
/*     */     }
/* 159 */     return borrado;
/*     */   }
/*     */ 
/*     */   public VentaPOS getVentaPOS(int id) {
/* 163 */     VentaPOS venta = null;
/*     */     try {
/* 165 */       String nota = getNota(id);
/* 166 */       String cadenaSQL = "SELECT * FROM ventaspos WHERE id = " + id;
/* 167 */       this.res = this.con.getRes(cadenaSQL);
/* 168 */       if (this.res.next()) {
/* 169 */         int idTicket = this.res.getInt(2);
/* 170 */         int idProducto = this.res.getInt(3);
/* 171 */         String descripcion = this.res.getString(4);
/* 172 */         int unidades = this.res.getInt(5);
/* 173 */         double importe = this.res.getDouble(6);
/* 174 */         venta = new VentaPOS(Integer.valueOf(id), idTicket, idProducto, descripcion, unidades, importe, nota);
/*     */       }
/*     */     }
/*     */     catch (SQLException exc) {
/* 178 */       exc.printStackTrace();
/*     */     }
/* 180 */     return venta;
/*     */   }
/*     */ 
/*     */   public VentaPOS getVentaPOSPorTicket(int idTicket) {
/* 184 */     VentaPOS venta = null;
/*     */     try {
/* 186 */       String cadenaSQL = "SELECT * FROM ventaspos WHERE idticket = " + idTicket;
/* 187 */       this.res = this.con.getRes(cadenaSQL);
/* 188 */       if (this.res.next()) {
/* 189 */         int id = this.res.getInt(1);
/* 190 */         int idProducto = this.res.getInt(3);
/* 191 */         String descripcion = this.res.getString(4);
/* 192 */         int unidades = this.res.getInt(5);
/* 193 */         double importe = this.res.getDouble(6);
/* 194 */         venta = new VentaPOS(Integer.valueOf(id), idTicket, idProducto, descripcion, unidades, importe, "");
/*     */       }
/* 196 */       venta.setNota(getNota(venta.getId().intValue()));
/*     */     }
/*     */     catch (SQLException exc) {
/* 199 */       exc.printStackTrace();
/*     */     }
/* 201 */     return venta;
/*     */   }
/*     */ 
/*     */   public ArrayList<VentaPOS> getTodasVentasPos() {
/* 205 */     ArrayList ventasPos = new ArrayList();
/*     */     try {
/* 207 */       String cadenaSQL = "SELECT * FROM ventaspos";
/* 208 */       VentaPOS venta = null;
/* 209 */       this.res = this.con.getRes(cadenaSQL);
/* 210 */       while (this.res.next()) {
/* 211 */         int id = this.res.getInt(1);
/* 212 */         int idTicket = this.res.getInt(2);
/* 213 */         int idProducto = this.res.getInt(3);
/* 214 */         String descripcion = this.res.getString(4);
/* 215 */         int unidades = this.res.getInt(5);
/* 216 */         double importe = this.res.getDouble(6);
/* 217 */         venta = new VentaPOS(Integer.valueOf(id), idTicket, idProducto, descripcion, unidades, importe, "");
/* 218 */         ventasPos.add(venta);
/*     */       }
/* 220 */       for (VentaPOS ventaPOS : (List<VentaPOS>)ventasPos)
/* 221 */         ventaPOS.setNota(getNota(ventaPOS.getId().intValue()));
/*     */     }
/*     */     catch (SQLException exc)
/*     */     {
/* 225 */       exc.printStackTrace();
/*     */     }
/* 227 */     return ventasPos;
/*     */   }
/*     */ 
/*     */   public ArrayList<VentaPOS> getTodasVentasPosPorTicket(int idTicket) {
/* 231 */     ArrayList ventasPos = new ArrayList();
/*     */     try {
/* 233 */       String cadenaSQL = "SELECT * FROM ventaspos WHERE idticket = " + idTicket;
/* 234 */       VentaPOS venta = null;
/* 235 */       this.res = this.con.getRes(cadenaSQL);
/* 236 */       while (this.res.next()) {
/* 237 */         int id = this.res.getInt(1);
/*     */ 
/* 239 */         int idProducto = this.res.getInt(3);
/* 240 */         String descripcion = this.res.getString(4);
/* 241 */         int unidades = this.res.getInt(5);
/* 242 */         double importe = this.res.getDouble(6);
/* 243 */         venta = new VentaPOS(Integer.valueOf(id), idTicket, idProducto, descripcion, unidades, importe, "");
/* 244 */         ventasPos.add(venta);
/*     */       }
/* 246 */       for (VentaPOS ventaPOS :(List<VentaPOS>) ventasPos)
/* 247 */         ventaPOS.setNota(getNota(ventaPOS.getId().intValue()));
/*     */     }
/*     */     catch (SQLException exc)
/*     */     {
/* 251 */       exc.printStackTrace();
/*     */     }
/* 253 */     return ventasPos;
/*     */   }
/*     */ 
/*     */   public void cerrarRs() {
/* 257 */     if (this.res != null) {
/*     */       try {
/* 259 */         this.res.close(); } catch (SQLException sqlEx) {
/*     */       }
/* 261 */       this.res = null;
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.control.VentaPosControl
 * JD-Core Version:    0.6.2
 */