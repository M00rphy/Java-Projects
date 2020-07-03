/*     */ package contaes;
/*     */ 
/*     */ import contaes.manejoDatos.ManejoApuntes;
/*     */ import contaes.manejoDatos.ManejoAsientos;
/*     */ import contaes.manejoDatos.ManejoFacturas;
/*     */ import contaes.manejoDatos.ManejoSubcuentas;
/*     */ import contaes.tratamientoDatos.Operaciones;
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.beans.PropertyVetoException;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDesktopPane;
/*     */ import javax.swing.JInternalFrame;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JToolBar;
/*     */ 
/*     */ public abstract class MarcoInternoAsientosFacturas extends MarcoInterno
/*     */ {
/*     */   protected static final int PRIMERO = 0;
/*     */   protected static final int ULTIMO = 1;
/*     */   protected static final int ANTERIOR = 2;
/*     */   protected static final int SIGUIENTE = 3;
/*  25 */   protected ManejoSubcuentas subcuentaM = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  26 */   protected ManejoFacturas facturaM = new ManejoFacturas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  27 */   protected ManejoAsientos asientoM = new ManejoAsientos(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  28 */   protected ManejoApuntes apunteM = new ManejoApuntes(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  29 */   protected Operaciones operar = new Operaciones();
/*     */ 
/*     */   public MarcoInternoAsientosFacturas(String titulo) {
/*  32 */     super(titulo);
/*     */   }
/*     */ 
/*     */   protected boolean comprobarFecha(String fecha)
/*     */   {
/*  42 */     if (fecha.equals("")) {
/*  43 */       JOptionPane.showMessageDialog(getContentPane(), Mensajes.getString("sinFecha"));
/*  44 */       return false;
/*     */     }
/*  46 */     if (!fecha.substring(0, 4).equals(Inicio.p.getEjercicio())) {
/*  47 */       JOptionPane.showMessageDialog(getContentPane(), Mensajes.getString("ejercicioNoCorrecto"));
/*  48 */       return false;
/*     */     }
/*  50 */     return true;
/*     */   }
/*     */ 
/*     */   protected void addBotones(JToolBar toolBar) {
/*  54 */     JButton button = null;
/*     */ 
/*  57 */     button = makeNavigationButton("2leftarrow", "", Mensajes.getString("Primero"));
/*     */ 
/*  59 */     button.addActionListener(new accionRealizada(0));
/*  60 */     toolBar.add(button);
/*     */ 
/*  62 */     button = makeNavigationButton("1leftarrow", "", Mensajes.getString("anterior"));
/*     */ 
/*  64 */     button.addActionListener(new accionRealizada(2));
/*  65 */     toolBar.add(button);
/*     */ 
/*  67 */     toolBar.addSeparator();
/*     */ 
/*  69 */     button = makeNavigationButton("1rightarrow", "", Mensajes.getString("siguiente"));
/*     */ 
/*  71 */     button.addActionListener(new accionRealizada(3));
/*  72 */     toolBar.add(button);
/*     */ 
/*  74 */     button = makeNavigationButton("2rightarrow", "", Mensajes.getString("ultimo"));
/*     */ 
/*  76 */     button.addActionListener(new accionRealizada(1));
/*  77 */     toolBar.add(button);
/*     */   }
/*     */ 
/*     */   protected abstract void colocarDatos(int paramInt);
/*     */ 
/*     */   protected void activar_seleccion_cuenta(int ctrl)
/*     */   {
/* 102 */     Inicio.p.setControl(ctrl);
/* 103 */     JInternalFrame[] todos = getDesktopPane().getAllFrames();
/* 104 */     int numero = todos.length;
/* 105 */     for (int x = 0; x <= numero; x++) {
/* 106 */       JInternalFrame f = todos[x];
/* 107 */       if (f.getTitle().equals(Mensajes.getString("gestCtas"))) {
/* 108 */         f.setVisible(true);
/*     */         try {
/* 110 */           if (f.isIcon()) {
/* 111 */             f.setIcon(false);
/*     */           }
/* 113 */           if (!f.isSelected())
/* 114 */             f.setSelected(true);
/*     */         }
/*     */         catch (PropertyVetoException exc)
/*     */         {
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   protected class accionRealizada
/*     */     implements ActionListener
/*     */   {
/*     */     private int opcion;
/*     */ 
/*     */     public accionRealizada(int opcion)
/*     */     {
/*  85 */       this.opcion = opcion;
/*     */     }
/*     */ 
/*     */     public void actionPerformed(ActionEvent arg0) {
/*  89 */       MarcoInternoAsientosFacturas.this.colocarDatos(this.opcion);
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.MarcoInternoAsientosFacturas
 * JD-Core Version:    0.6.2
 */