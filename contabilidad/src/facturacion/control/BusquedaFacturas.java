/*     */ package facturacion.control;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.Puente;
/*     */ import contaes.manejoDatos.auxiliar.MySQLConection;
/*     */ import facturacion.model.Factura;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ 
/*     */ public class BusquedaFacturas
/*     */ {
/*     */   private MySQLConection con;
/*     */   private ResultSet res;
/*     */   private ArrayList<Factura> facturas;
/*     */   private HashSet<Integer> listaIds;
/*     */ 
/*     */   public BusquedaFacturas(MySQLConection con, String cadenaBusqueda)
/*     */   {
/*  29 */     this.con = con;
/*  30 */     realizarBusqueda(cadenaBusqueda);
/*     */   }
/*     */ 
/*     */   public static ArrayList<Factura> resultado(MySQLConection con, String cadenaBusqueda) {
/*  34 */     BusquedaFacturas estaClase = new BusquedaFacturas(con, cadenaBusqueda);
/*  35 */     return estaClase.facturas;
/*     */   }
/*     */ 
/*     */   private void realizarBusqueda(String cadenaBusqueda) {
/*  39 */     this.listaIds = new HashSet();
/*     */ 
/*  41 */     if ((cadenaBusqueda.length() == 18) && (cadenaBusqueda.substring(0, 2).equals("F#")))
/*     */     {
/*  43 */       String fInicial = cadenaBusqueda.substring(2, 10);
/*  44 */       String fFinal = cadenaBusqueda.substring(10);
/*  45 */       if ((isDate(fInicial)) && (isDate(fFinal))) {
/*  46 */         busquedaPorfecha(fInicial, fFinal);
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/*  51 */       busquedaPorNumero(cadenaBusqueda);
/*  52 */       busquedaPorCliente(cadenaBusqueda);
/*  53 */       busquedaPorProductos(cadenaBusqueda);
/*  54 */       if (isDouble(cadenaBusqueda)) {
/*  55 */         busquedaPorTotal(cadenaBusqueda, 10);
/*     */       }
/*     */     }
/*  58 */     generarResultado(this.listaIds);
/*     */   }
/*     */ 
/*     */   private void busquedaPorNumero(String cadenaBusqueda) {
/*  62 */     String cadenaSQL = "SELECT id FROM facturas WHERE numero LIKE '%" + cadenaBusqueda + "%'";
/*     */     try {
/*  64 */       this.res = this.con.getRes(cadenaSQL);
/*  65 */       while (this.res.next())
/*  66 */         this.listaIds.add(Integer.valueOf(this.res.getInt(1)));
/*     */     }
/*     */     catch (SQLException ex) {
/*  69 */       Logger.getLogger(BusquedaFacturas.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void busquedaPorCliente(String cadenaBusqueda) {
/*  74 */     String cadenaSQL = "SELECT codigo FROM scta" + Inicio.p.getEjercicio() + " WHERE" + " codigo BETWEEN 43000000 AND 44999999 AND" + " nombre LIKE '%" + cadenaBusqueda + "%'";
/*     */ 
/*  77 */     ArrayList codClientes = new ArrayList();
/*     */     try {
/*  79 */       this.res = this.con.getRes(cadenaSQL);
/*  80 */       while (this.res.next()) {
/*  81 */         codClientes.add(Integer.valueOf(this.res.getInt(1)));
/*     */       }
/*  83 */       if (!codClientes.isEmpty())
/*  84 */         for (Integer codigo : (List<Integer>)codClientes) {
/*  85 */           cadenaSQL = "SELECT id FROM facturas WHERE cliente =" + codigo;
/*  86 */           this.res = this.con.getRes(cadenaSQL);
/*  87 */           while (this.res.next())
/*  88 */             this.listaIds.add(Integer.valueOf(this.res.getInt(1)));
/*     */         }
/*     */     }
/*     */     catch (SQLException ex)
/*     */     {
/*  93 */       Logger.getLogger(BusquedaFacturas.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void busquedaPorfecha(String fIni, String fFin)
/*     */   {
/*  99 */     String fInicial = fIni.substring(4) + fIni.substring(2, 4) + fIni.substring(0, 2);
/* 100 */     String fFinal = fFin.substring(4) + fFin.substring(2, 4) + fFin.substring(0, 2);
/* 101 */     String cadenaSQL = "SELECT id FROM facturas WHERE fecha BETWEEN '" + fInicial + "' AND '" + fFinal + "'";
/*     */     try {
/* 103 */       this.res = this.con.getRes(cadenaSQL);
/* 104 */       while (this.res.next())
/* 105 */         this.listaIds.add(Integer.valueOf(this.res.getInt(1)));
/*     */     }
/*     */     catch (SQLException ex) {
/* 108 */       Logger.getLogger(BusquedaFacturas.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void busquedaPorTotal(String cadenaBusqueda, int dif) {
/* 113 */     double valor1 = new Double(cadenaBusqueda).doubleValue() - dif;
/* 114 */     double valor2 = new Double(cadenaBusqueda).doubleValue() + dif;
/* 115 */     String cadenaSQL = "SELECT id, (base+iva) as total FROM facturas HAVING total BETWEEN " + valor1 + " AND " + valor2;
/*     */     try {
/* 117 */       this.res = this.con.getRes(cadenaSQL);
/* 118 */       while (this.res.next())
/* 119 */         this.listaIds.add(Integer.valueOf(this.res.getInt(1)));
/*     */     }
/*     */     catch (SQLException ex) {
/* 122 */       Logger.getLogger(BusquedaFacturas.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void busquedaPorProductos(String cadenaBusqueda) {
/* 127 */     String cadenaSQL = "SELECT id FROM productos WHERE descripcion LIKE '%" + cadenaBusqueda + "%'";
/* 128 */     ArrayList idProductos = new ArrayList();
/*     */     try {
/* 130 */       this.res = this.con.getRes(cadenaSQL);
/* 131 */       while (this.res.next()) {
/* 132 */         idProductos.add(Integer.valueOf(this.res.getInt(1)));
/*     */       }
/* 134 */       if (!idProductos.isEmpty())
/* 135 */         for (Integer idProducto : (List<Integer>)idProductos) {
/* 136 */           cadenaSQL = "SELECT idFactura FROM lineafactura WHERE idProducto =" + idProducto;
/* 137 */           this.res = this.con.getRes(cadenaSQL);
/* 138 */           while (this.res.next())
/* 139 */             this.listaIds.add(Integer.valueOf(this.res.getInt(1)));
/*     */         }
/*     */     }
/*     */     catch (SQLException ex)
/*     */     {
/* 144 */       Logger.getLogger(BusquedaFacturas.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void generarResultado(HashSet<Integer> listaIds) {
/* 149 */     this.facturas = new ArrayList();
/* 150 */     FacturaControl fC = new FacturaControl(this.con, true);
/* 151 */     for (Integer id : listaIds) {
/*     */       try {
/* 153 */         this.facturas.add(fC.factura(id));
/*     */       } catch (Exception ex) {
/* 155 */         Logger.getLogger(BusquedaFacturas.class.getName()).log(Level.SEVERE, null, ex);
/*     */       }
/*     */     }
/* 158 */     fC.cerrarRs();
/*     */   }
/*     */ 
/*     */   private boolean isDate(String cadena) {
/* 162 */     boolean es = true;
/*     */     try {
/* 164 */       int y = Integer.parseInt(cadena.substring(4));
/* 165 */       int m = Integer.parseInt(cadena.substring(2, 4));
/* 166 */      int d = Integer.parseInt(cadena.substring(0, 2));
/*     */     }
/*     */     catch (NumberFormatException exc)
/*     */     {
/*     */       
/* 169 */       exc.printStackTrace();
/* 170 */       es = false;
/*     */     }
/*     */     catch (ArrayIndexOutOfBoundsException exc2) {
/* 173 */       exc2.printStackTrace();
/* 174 */       es = false;
/*     */     }
/* 176 */     return es;
/*     */   }
/*     */ 
/*     */   private boolean isDouble(String valor) {
/*     */     try {
/* 181 */       new Double(valor).doubleValue();
/*     */     } catch (NumberFormatException exc) {
/* 183 */       return false;
/*     */     }
/* 185 */     return true;
/*     */   }
/*     */ 
/*     */   public void cerrarRs() {
/* 189 */     if (this.res != null) {
/*     */       try {
/* 191 */         this.res.close(); } catch (SQLException sqlEx) {
/*     */       }
/* 193 */       this.res = null;
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     facturacion.control.BusquedaFacturas
 * JD-Core Version:    0.6.2
 */