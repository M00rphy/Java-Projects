/*    */ package contaes.informes.control;
/*    */ 
/*    */ import contaes.Inicio;
/*    */ import contaes.Puente;
/*    */ import contaes.informes.model.ContrapartidaObject;
/*    */ import contaes.manejoDatos.ManejoSubcuentas;
/*    */ import contaes.manejoDatos.TipoSubcuenta;
/*    */ import contaes.manejoDatos.auxiliar.MySQLConection;
/*    */ import java.sql.Connection;
/*    */ import java.sql.PreparedStatement;
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.Logger;
/*    */ 
/*    */ public class ContrapartidasControl
/*    */ {
/*    */   private MySQLConection con;
/*    */   private MySQLConection conAux;
/*    */   private String ejercicio;
/*    */   private ResultSet res;
/*    */ 
/*    */   public ContrapartidasControl()
/*    */   {
/* 33 */     this.con = Inicio.getCEmpresa();
/* 34 */     this.conAux = Inicio.getCFacturacion();
/* 35 */     this.ejercicio = Inicio.p.getEjercicio();
/*    */   }
/*    */ 
/*    */   public ArrayList<ContrapartidaObject> listado(Integer subcuenta, java.util.Date fechaIni, java.util.Date fechaFin, boolean debe) {
/* 39 */     ArrayList array = new ArrayList();
/*    */ 
/* 41 */     String queryTotal = "SELECT cuenta, SUM(importe) FROM apte" + this.ejercicio + " WHERE id_asiento IN " + " (SELECT a.id_asiento FROM asto" + this.ejercicio + " a JOIN apte" + this.ejercicio + " b ON a.id_asiento=b.id_asiento" + " WHERE b.cuenta = ? AND (a.fecha BETWEEN ? AND ?) AND a.marca NOT LIKE 'A' AND a.marca NOT LIKE 'C' AND a.marca NOT LIKE 'P')" + " AND cuenta <> ? AND DH = ? GROUP BY cuenta";
/*    */ 
/* 45 */     String DH = debe ? "H" : "D";
/*    */     try
/*    */     {
/* 53 */       PreparedStatement ps = this.con.getCon().prepareStatement(queryTotal);
/* 54 */       ps.setInt(1, subcuenta.intValue());
/* 55 */       ps.setDate(2, new java.sql.Date(fechaIni.getTime()));
/* 56 */       ps.setDate(3, new java.sql.Date(fechaFin.getTime()));
/* 57 */       ps.setInt(4, subcuenta.intValue());
/* 58 */       ps.setString(5, DH);
/* 59 */       this.res = ps.executeQuery();
/* 60 */       while (this.res.next()) {
/* 61 */         int codigo = this.res.getInt(1);
/* 62 */         Double importe = Double.valueOf(this.res.getDouble(2));
/* 63 */         TipoSubcuenta subc = getSubcuenta(Integer.valueOf(codigo));
/* 64 */         if (subc != null) {
/* 65 */           ContrapartidaObject objeto = new ContrapartidaObject(subc, importe);
/* 66 */           array.add(objeto);
/*    */         }
/*    */       }
/*    */     }
/*    */     catch (SQLException ex) {
/* 71 */       Logger.getLogger(ContrapartidasControl.class.getName()).log(Level.SEVERE, null, ex);
/*    */     }
/*    */ 
/* 74 */     return array;
/*    */   }
/*    */ 
/*    */   private TipoSubcuenta getSubcuenta(Integer codigo) {
/* 78 */     TipoSubcuenta subcuenta = null;
/* 79 */     ManejoSubcuentas mS = new ManejoSubcuentas(this.conAux, this.ejercicio);
/* 80 */     subcuenta = mS.datos(codigo.intValue());
/* 81 */     return subcuenta;
/*    */   }
/*    */ 
/*    */   public void cerrarRs() {
/* 85 */     if (this.res != null) {
/*    */       try {
/* 87 */         this.res.close();
/*    */       } catch (SQLException sqlEx) {
/*    */       }
/* 90 */       this.res = null;
/*    */     }
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.informes.control.ContrapartidasControl
 * JD-Core Version:    0.6.2
 */