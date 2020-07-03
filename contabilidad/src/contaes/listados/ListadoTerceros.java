/*    */ package contaes.listados;
/*    */ 
/*    */ import contaes.Inicio;
/*    */ import contaes.Puente;
/*    */ import contaes.manejoDatos.auxiliar.FinLinea;
/*    */ import contaes.manejoDatos.auxiliar.MySQLConection;
/*    */ import internationalization.Mensajes;
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Formatter;
/*    */ import java.util.List;
/*    */ 
/*    */ public class ListadoTerceros
/*    */ {
/*    */   private ResultSet res;
/* 38 */   private String titulo = null;
/* 39 */   private List<String> listado = new ArrayList();
/* 40 */   private boolean listar = false;
/*    */ 
/*    */   public ListadoTerceros()
/*    */   {
/* 46 */     this.titulo = Mensajes.getString("listTerceros");
/* 47 */     if (crearListado())
/* 48 */       this.listar = true;
/*    */   }
/*    */ 
/*    */   private boolean crearListado()
/*    */   {
/* 58 */     String EOL = FinLinea.get();
/*    */     try {
/* 60 */       this.res = Inicio.getCEmpresa().getRes("SELECT s.nombre,t.NIF,t.direccion,t.CP,t.localidad,t.provincia,t.telefono,t.fax,t.email,p.nombre FROM scta" + Inicio.p.getEjercicio() + " AS s JOIN Terceros AS t ON s.codigo=t.codigo" + " LEFT JOIN contaes.paises p ON t.pais = p.id" + " ORDER BY s.nombre");
/*    */ 
/* 66 */       while (this.res.next()) {
/* 67 */         StringBuilder sb = new StringBuilder();
/* 68 */         Formatter formater = new Formatter(sb);
/* 69 */         formater.format("%-50.50s - %s" + EOL + "%s" + EOL + "%-8s - %s" + EOL + "%-20s (%s)" + EOL + "%-14s - %s" + EOL + "%s" + EOL + "" + EOL + "", new Object[] { this.res.getString(1), this.res.getString(2), this.res.getString(3), this.res.getString(4), this.res.getString(5), this.res.getString(6), this.res.getString(10), this.res.getString(7), this.res.getString(8), this.res.getString(9) });
/*    */ 
/* 72 */         this.listado.add(sb.toString());
/*    */       }
/*    */     } catch (SQLException e) {
/* 75 */       e.printStackTrace();
/*    */     }
/*    */ 
/* 78 */     return true;
/*    */   }
/*    */ 
/*    */   public String getTitulo()
/*    */   {
/* 85 */     return this.titulo;
/*    */   }
/*    */ 
/*    */   public List<String> getListado()
/*    */   {
/* 92 */     return this.listado;
/*    */   }
/*    */ 
/*    */   public boolean isListar()
/*    */   {
/* 99 */     return this.listar;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.listados.ListadoTerceros
 * JD-Core Version:    0.6.2
 */