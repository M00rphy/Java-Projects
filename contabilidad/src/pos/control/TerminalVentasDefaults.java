/*    */ package pos.control;
/*    */ 
/*    */ import contaes.Inicio;
/*    */ import contaes.Puente;
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
/*    */ import pos.model.ObjetoMultifuncional;
/*    */ 
/*    */ public class TerminalVentasDefaults
/*    */ {
/*    */   ObjetoMultifuncional cliente;
/*    */   ObjetoMultifuncional vendedor;
/*    */   ObjetoMultifuncional almacen;
/*    */   ObjetoMultifuncional medioPago;
/*    */   ResultSet rs;
/*    */ 
/*    */   public TerminalVentasDefaults()
/*    */   {
/* 26 */     this.cliente = new ObjetoMultifuncional();
/* 27 */     this.vendedor = new ObjetoMultifuncional();
/* 28 */     this.almacen = new ObjetoMultifuncional();
/* 29 */     this.medioPago = new ObjetoMultifuncional();
/* 30 */     TicketsControl tC = new TicketsControl(Inicio.getcAlmacen());
/* 31 */     this.cliente.setId(tC.getBest("cliente"));
/* 32 */     this.vendedor.setId(tC.getBest("vendedor"));
/* 33 */     this.almacen.setId(tC.getBest("almacen"));
/* 34 */     this.medioPago.setId(tC.getBest("mediopago"));
/* 35 */     tC.cerrarRs();
/*    */     try {
/* 37 */       if (this.cliente.getId() != -1) {
/* 38 */         this.rs = Inicio.getCEmpresa().getRes("SELECT nombre FROM scta" + Inicio.p.getEjercicio() + " WHERE codigo = " + this.cliente.getId());
/*    */ 
/* 40 */         if (this.rs.next())
/* 41 */           this.cliente.setNombre(this.rs.getString(1));
/*    */       }
/*    */       else
/*    */       {
/* 45 */         this.cliente = null;
/*    */       }
/* 47 */       if (this.vendedor.getId() != -1) {
/* 48 */         this.rs = Inicio.getcAlmacen().getRes("SELECT nombre FROM vendedores WHERE id = " + this.vendedor.getId());
/* 49 */         if (this.rs.next())
/* 50 */           this.vendedor.setNombre(this.rs.getString(1));
/*    */       }
/*    */       else
/*    */       {
/* 54 */         this.vendedor = null;
/*    */       }
/* 56 */       if (this.almacen.getId() != -1) {
/* 57 */         this.rs = Inicio.getcAlmacen().getRes("SELECT nombre FROM almacenes WHERE id = " + this.almacen.getId());
/* 58 */         if (this.rs.next())
/* 59 */           this.almacen.setNombre(this.rs.getString(1));
/*    */       }
/*    */       else
/*    */       {
/* 63 */         this.almacen = null;
/*    */       }
/* 65 */       if (this.medioPago.getId() != -1) {
/* 66 */         this.rs = Inicio.getcAlmacen().getRes("SELECT nombre FROM mediospago WHERE id = " + this.medioPago.getId());
/* 67 */         if (this.rs.next())
/* 68 */           this.medioPago.setNombre(this.rs.getString(1));
/*    */       }
/*    */       else
/*    */       {
/* 72 */         this.medioPago = null;
/*    */       }
/*    */     }
/*    */     catch (SQLException exc) {
/* 76 */       exc.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/*    */   public ObjetoMultifuncional getCliente() {
/* 81 */     return this.cliente;
/*    */   }
/*    */ 
/*    */   public ObjetoMultifuncional getAlmacen() {
/* 85 */     return this.almacen;
/*    */   }
/*    */ 
/*    */   public ObjetoMultifuncional getMedioPago() {
/* 89 */     return this.medioPago;
/*    */   }
/*    */ 
/*    */   public ObjetoMultifuncional getVendedor() {
/* 93 */     return this.vendedor;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.control.TerminalVentasDefaults
 * JD-Core Version:    0.6.2
 */