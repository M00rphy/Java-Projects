/*     */ package contaes.dialogosAuxiliares;
/*     */ 
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.Frame;
/*     */ import java.awt.HeadlessException;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.KeyAdapter;
/*     */ import java.awt.event.KeyEvent;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ 
/*     */ public abstract class BusquedaBase extends JDialog
/*     */ {
/*  19 */   protected boolean Busqueda = true;
/*  20 */   protected boolean Accion = false;
/*  21 */   protected String inicio = "";
/*  22 */   protected String fin = "";
/*     */ 
/*  24 */   protected JButton buscar = null;
/*  25 */   protected JButton selecc = null;
/*  26 */   protected JButton cancel = null;
/*  27 */   protected JLabel jLabel1 = null;
/*  28 */   protected JLabel jLabel2 = null;
/*  29 */   protected JLabel jLabel3 = null;
/*  30 */   protected JLabel jLabel4 = null;
/*     */   protected Acciones escuchaAccion;
/*     */   protected TeclaPulsada escuchaTecla;
/*     */ 
/*     */   public BusquedaBase(Frame arg0, String arg1, boolean arg2)
/*     */     throws HeadlessException
/*     */   {
/*  42 */     super(arg0, arg1, arg2);
/*  43 */     this.escuchaAccion = new Acciones();
/*  44 */     this.escuchaTecla = new TeclaPulsada();
/*  45 */     this.jLabel1 = new JLabel();
/*  46 */     this.jLabel1.setText(Mensajes.getString("iniSelec"));
/*  47 */     this.jLabel2 = new JLabel();
/*  48 */     this.jLabel2.setText("");
/*  49 */     this.jLabel2.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/downArrow.png")));
/*  50 */     this.jLabel3 = new JLabel();
/*  51 */     this.jLabel3.setText("");
/*  52 */     this.jLabel3.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/downArrow.png")));
/*  53 */     this.jLabel4 = new JLabel();
/*  54 */     this.jLabel4.setText(Mensajes.getString("finSelec"));
/*     */   }
/*     */ 
/*     */   public boolean isAccion()
/*     */   {
/*  61 */     return this.Accion;
/*     */   }
/*     */ 
/*     */   protected void setAccion(boolean accion)
/*     */   {
/*  68 */     this.Accion = accion;
/*     */   }
/*     */ 
/*     */   public boolean isBusqueda()
/*     */   {
/*  75 */     return this.Busqueda;
/*     */   }
/*     */ 
/*     */   protected void setBusqueda(boolean busqueda)
/*     */   {
/*  82 */     this.Busqueda = busqueda;
/*     */   }
/*     */ 
/*     */   public String getFin()
/*     */   {
/*  89 */     return this.fin;
/*     */   }
/*     */ 
/*     */   protected void setFin(String fin)
/*     */   {
/*  96 */     this.fin = fin;
/*     */   }
/*     */ 
/*     */   public String getInicio()
/*     */   {
/* 103 */     return this.inicio;
/*     */   }
/*     */ 
/*     */   protected void setInicio(String inicio)
/*     */   {
/* 110 */     this.inicio = inicio;
/*     */   }
/*     */ 
/*     */   protected JButton getBuscar()
/*     */   {
/* 119 */     if (this.buscar == null) {
/* 120 */       this.buscar = new JButton();
/* 121 */       this.buscar.setText(Mensajes.getString("buscar"));
/* 122 */       this.buscar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/find.png")));
/* 123 */       this.buscar.setHorizontalTextPosition(2);
/* 124 */       this.buscar.addActionListener(this.escuchaAccion);
/*     */     }
/* 126 */     return this.buscar;
/*     */   }
/*     */ 
/*     */   protected JButton getSelecc()
/*     */   {
/* 135 */     if (this.selecc == null) {
/* 136 */       this.selecc = new JButton();
/* 137 */       this.selecc.setText(Mensajes.getString("seleccionar"));
/* 138 */       this.selecc.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/filter.png")));
/* 139 */       this.selecc.setHorizontalTextPosition(2);
/* 140 */       this.selecc.addActionListener(this.escuchaAccion);
/*     */     }
/* 142 */     return this.selecc;
/*     */   }
/*     */ 
/*     */   protected JButton getCancel()
/*     */   {
/* 151 */     if (this.cancel == null) {
/* 152 */       this.cancel = new JButton();
/* 153 */       this.cancel.setText(Mensajes.getString("cancelar"));
/* 154 */       this.cancel.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/cancel.png")));
/* 155 */       this.cancel.setHorizontalTextPosition(2);
/* 156 */       this.cancel.addActionListener(this.escuchaAccion);
/*     */     }
/* 158 */     return this.cancel;
/*     */   }
/*     */ 
/*     */   protected abstract void buscar();
/*     */ 
/*     */   protected abstract void seleccionar();
/*     */ 
/*     */   protected abstract void cambiaFoco(KeyEvent paramKeyEvent);
/*     */ 
/*     */   protected class TeclaPulsada extends KeyAdapter
/*     */   {
/*     */     protected TeclaPulsada()
/*     */     {
/*     */     }
/*     */ 
/*     */     public void keyPressed(KeyEvent e)
/*     */     {
/* 180 */       BusquedaBase.this.cambiaFoco(e);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected class Acciones
/*     */     implements ActionListener
/*     */   {
/*     */     protected Acciones()
/*     */     {
/*     */     }
/*     */ 
/*     */     public void actionPerformed(ActionEvent e)
/*     */     {
/* 168 */       Object fuente = e.getSource();
/* 169 */       if (fuente == BusquedaBase.this.cancel) BusquedaBase.this.dispose();
/* 170 */       else if (fuente == BusquedaBase.this.selecc) BusquedaBase.this.seleccionar();
/* 171 */       else if (fuente == BusquedaBase.this.buscar) BusquedaBase.this.buscar();
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.dialogosAuxiliares.BusquedaBase
 * JD-Core Version:    0.6.2
 */