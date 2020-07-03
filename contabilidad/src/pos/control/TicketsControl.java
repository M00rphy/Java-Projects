/*     */ package pos.control;
/*     */ 
/*     */ import almacen2.data.MySQLConection;
/*     */ import contaes.Inicio;
/*     */ import contaes.Puente;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import pos.model.Ticket;
/*     */ 
/*     */ public class TicketsControl
/*     */ {
/*     */   private MySQLConection con;
/*     */   private ResultSet res;
/*     */ 
/*     */   public TicketsControl(MySQLConection con)
/*     */   {
/*  28 */     this.con = con;
/*     */   }
/*     */ 
/*     */   public int crear(Ticket ticket) {
/*  32 */     int id = -1;
/*     */     try {
/*  34 */       String cadenaSQL = "INSERT INTO tickets (numero,fecha,almacen,mediopago,plazos,vendedor,cliente,cerrado) VALUES(?,?,?,?,?,?,?,?)";
/*  35 */       if (ticket.getIdplazoanterior() != null) {
/*  36 */         cadenaSQL = "INSERT INTO tickets (numero,fecha,almacen,mediopago,plazos,vendedor,cliente,cerrado,idplazoanterior) VALUES(?,?,?,?,?,?,?,?,?)";
/*     */       }
/*  38 */       PreparedStatement ps = this.con.getCon().prepareStatement(cadenaSQL);
/*  39 */       ps.setInt(1, ticket.getNumero());
/*  40 */       ps.setDate(2, new java.sql.Date(ticket.getFecha().getTime()));
/*     */ 
/*  42 */       ps.setInt(3, ticket.getAlmacen());
/*  43 */       ps.setInt(4, ticket.getMedioPago());
/*  44 */       ps.setInt(5, ticket.getPlazos());
/*  45 */       ps.setInt(6, ticket.getVendedor());
/*  46 */       ps.setInt(7, ticket.getCliente());
/*  47 */       ps.setInt(8, ticket.isCerrado());
/*  48 */       if (ticket.getIdplazoanterior() != null) {
/*  49 */         ps.setInt(9, ticket.getIdplazoanterior().intValue());
/*     */       }
/*     */ 
/*  52 */       ps.execute();
/*  53 */       cadenaSQL = "SELECT LAST_INSERT_ID() FROM tickets";
/*  54 */       ps = this.con.getCon().prepareStatement(cadenaSQL);
/*  55 */       this.res = ps.executeQuery();
/*  56 */       if (this.res.next())
/*  57 */         id = this.res.getInt(1);
/*     */     }
/*     */     catch (SQLException exc) {
/*  60 */       exc.printStackTrace();
/*     */     }
/*  62 */     return id;
/*     */   }
/*     */ 
/*     */   public boolean modificar(Ticket ticket) {
/*  66 */     boolean modificado = false;
/*     */     try {
/*  68 */       String cadenaSQL = "UPDATE tickets SET numero = ?, fecha = ?, almacen = ?, mediopago = ?,plazos = ?, vendedor = ?, cliente = ?, cerrado = ? WHERE id = ?";
/*     */ 
/*  70 */       PreparedStatement ps = this.con.getCon().prepareStatement(cadenaSQL);
/*  71 */       ps.setInt(1, ticket.getNumero());
/*  72 */       ps.setDate(2, new java.sql.Date(ticket.getFecha().getTime()));
/*     */ 
/*  74 */       ps.setInt(3, ticket.getAlmacen());
/*  75 */       ps.setInt(4, ticket.getMedioPago());
/*  76 */       ps.setInt(5, ticket.getPlazos());
/*  77 */       ps.setInt(6, ticket.getVendedor());
/*  78 */       ps.setInt(7, ticket.getCliente());
/*  79 */       ps.setInt(8, ticket.isCerrado());
/*  80 */       ps.setInt(9, ticket.getId().intValue());
/*     */ 
/*  82 */       int result = ps.executeUpdate();
/*  83 */       if (result > 0)
/*  84 */         modificado = true;
/*     */     }
/*     */     catch (SQLException exc) {
/*  87 */       exc.printStackTrace();
/*     */     }
/*  89 */     return modificado;
/*     */   }
/*     */ 
/*     */   public boolean borrar(Ticket ticket) {
/*  93 */     boolean borrado = false;
/*     */     try {
/*  95 */       String cadenaSQL = "DELETE FROM tickets WHERE id = ?";
/*  96 */       PreparedStatement ps = this.con.getCon().prepareStatement(cadenaSQL);
/*  97 */       ps.setInt(1, ticket.getId().intValue());
/*  98 */       int result = ps.executeUpdate();
/*  99 */       if (result > 0) {
/* 100 */         VentaPosControl vPC = new VentaPosControl(this.con);
/* 101 */         if (vPC.borrar(ticket.getId().intValue()))
/* 102 */           borrado = true;
/*     */       }
/*     */     }
/*     */     catch (SQLException exc) {
/* 106 */       exc.printStackTrace();
/*     */     }
/* 108 */     return borrado;
/*     */   }
/*     */ 
/*     */   public int getIdTicket(int nTicket) {
/*     */     try {
/* 113 */       String cadenaSQL = "SELECT id FROM tickets WHERE numero = " + nTicket;
/* 114 */       this.res = this.con.getRes(cadenaSQL);
/* 115 */       if (this.res.next())
/* 116 */         return this.res.getInt(1);
/*     */     }
/*     */     catch (SQLException exc)
/*     */     {
/* 120 */       exc.printStackTrace();
/*     */     }
/* 122 */     return -1;
/*     */   }
/*     */ 
/*     */   public Ticket getTicket(int id) {
/* 126 */     Ticket ticket = null;
/*     */     try {
/* 128 */       String cadenaSQL = "SELECT * FROM tickets WHERE id = " + id;
/* 129 */       this.res = this.con.getRes(cadenaSQL);
/* 130 */       if (this.res.next()) {
/* 131 */         int numero = this.res.getInt(2);
/* 132 */         java.util.Date fecha = this.res.getDate(3);
/* 133 */         int almacen = this.res.getInt(4);
/* 134 */         int medioPago = this.res.getInt(5);
/* 135 */         int plazos = this.res.getInt(6);
/* 136 */         int vendedor = this.res.getInt(7);
/* 137 */         int cliente = this.res.getInt(8);
/* 138 */         int cerrado = this.res.getInt(9);
/* 139 */         Integer idplazoanterior = Integer.valueOf(this.res.getInt(10));
/* 140 */         ticket = new Ticket(Integer.valueOf(id), numero, fecha, almacen, medioPago, plazos, vendedor, cliente, cerrado, idplazoanterior);
/*     */       }
/*     */     }
/*     */     catch (SQLException exc) {
/* 144 */       exc.printStackTrace();
/*     */     }
/* 146 */     return ticket;
/*     */   }
/*     */ 
/*     */   public ArrayList<Ticket> getTodosTickets() {
/* 150 */     ArrayList tickets = new ArrayList();
/*     */     try {
/* 152 */       String cadenaSQL = "SELECT * FROM tickets ORDER BY numero";
/* 153 */       Ticket ticket = null;
/* 154 */       this.res = this.con.getRes(cadenaSQL);
/* 155 */       while (this.res.next()) {
/* 156 */         Integer id = Integer.valueOf(this.res.getInt(1));
/* 157 */         int numero = this.res.getInt(2);
/* 158 */         java.util.Date fecha = this.res.getDate(3);
/* 159 */         int almacen = this.res.getInt(4);
/* 160 */         int medioPago = this.res.getInt(5);
/* 161 */         int plazos = this.res.getInt(6);
/* 162 */         int vendedor = this.res.getInt(7);
/* 163 */         int cliente = this.res.getInt(8);
/* 164 */         int cerrado = this.res.getInt(9);
/* 165 */         Integer idplazoanterior = Integer.valueOf(this.res.getInt(10));
/* 166 */         ticket = new Ticket(id, numero, fecha, almacen, medioPago, plazos, vendedor, cliente, cerrado, idplazoanterior);
/*     */ 
/* 168 */         tickets.add(ticket);
/*     */       }
/*     */     }
/*     */     catch (SQLException exc) {
/* 172 */       exc.printStackTrace();
/*     */     }
/* 174 */     return tickets;
/*     */   }
/*     */ 
/*     */   public int getNuevoNumero() {
/* 178 */     int numero = 0;
/* 179 */     String fechaInicio = Inicio.p.getEjercicio() + "01" + "01";
/* 180 */     String fechaFin = Inicio.p.getEjercicio() + "12" + "31";
/*     */     try {
/* 182 */       String cadenaSQL = "SELECT MAX(numero) FROM tickets WHERE fecha BETWEEN '" + fechaInicio + "' AND '" + fechaFin + "'";
/* 183 */       this.res = this.con.getRes(cadenaSQL);
/* 184 */       if (this.res.next())
/* 185 */         numero = this.res.getInt(1);
/*     */     }
/*     */     catch (SQLException ex) {
/* 188 */       Logger.getLogger(TicketsControl.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/* 190 */     numero++; return numero;
/*     */   }
/*     */ 
/*     */   public int getBest(String campo) {
/* 194 */     int numero = -1;
/*     */     try {
/* 196 */       String cadenaSQL = "SELECT " + campo + ",count(*) as max FROM tickets group by " + campo + " order by max desc limit 1";
/* 197 */       this.res = this.con.getRes(cadenaSQL);
/* 198 */       if (this.res.next())
/* 199 */         numero = this.res.getInt(1);
/*     */     }
/*     */     catch (SQLException ex) {
/* 202 */       Logger.getLogger(TicketsControl.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/* 204 */     return numero;
/*     */   }
/*     */ 
/*     */   public void cerrarRs() {
/* 208 */     if (this.res != null) {
/*     */       try {
/* 210 */         this.res.close(); } catch (SQLException sqlEx) {
/*     */       }
/* 212 */       this.res = null;
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.control.TicketsControl
 * JD-Core Version:    0.6.2
 */