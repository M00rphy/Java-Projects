/*     */ package contaes;
/*     */ 
/*     */ import contaes.dialogosAuxiliares.BusquedaBase;
/*     */ import contaes.dialogosAuxiliares.BusquedaFecha;
/*     */ import contaes.dialogosAuxiliares.BusquedaParaCuentas;
/*     */ import contaes.dialogosAuxiliares.BusquedaStd;
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Rectangle;
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.ListSelectionModel;
/*     */ import javax.swing.event.ListSelectionEvent;
/*     */ 
/*     */ public abstract class MarcoInternoTablas extends MarcoInterno
/*     */ {
/*  21 */   protected String comienzo = ""; protected String fin = "";
/*  22 */   protected boolean Accion = false; protected boolean Buscar = true;
/*     */   protected int anchoTabla;
/*  23 */   protected int selectedRow = 0; protected int lastSearch = 0; protected int lastSelectedRow = 0;
/*  24 */   protected int m_maxNumPage = 1;
/*  25 */   protected List<String> nombres_col = new ArrayList();
/*     */   protected DecimalFormat fn;
/*     */ 
/*     */   public MarcoInternoTablas(String titulo)
/*     */   {
/*  29 */     super(titulo);
/*     */   }
/*     */ 
/*     */   protected void dialogoSeleccion(boolean puedeSel, int opcion) {
/*  33 */     BusquedaBase dlg = null;
/*  34 */     switch (opcion) {
/*     */     case 0:
/*  36 */       dlg = new BusquedaParaCuentas(Inicio.frame, Mensajes.getString("selBu"), true);
/*  37 */       break;
/*     */     case 1:
/*  39 */       dlg = new BusquedaFecha(Inicio.frame, Mensajes.getString("selBu"), true);
/*  40 */       break;
/*     */     default:
/*  42 */       dlg = new BusquedaStd(Inicio.frame, Mensajes.getString("selBu"), true, puedeSel);
/*     */     }
/*  44 */     mostrarDialogo(dlg);
/*  45 */     if (dlg.isAccion()) {
/*  46 */       setAccion(dlg.isAccion());
/*  47 */       setBuscar(dlg.isBusqueda());
/*  48 */       setComienzo(dlg.getInicio());
/*  49 */       if (!isBuscar()) setFin(dlg.getFin()); 
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void mostrarDialogo(JDialog dlg)
/*     */   {
/*  54 */     Dimension dlgSize = dlg.getPreferredSize();
/*  55 */     Rectangle parentBounds = Inicio.frame.localizacion();
/*  56 */     dlg.setLocation(parentBounds.x + (parentBounds.width - dlgSize.width) / 2, parentBounds.y + (parentBounds.height - dlgSize.height) / 2);
/*     */ 
/*  58 */     dlg.setVisible(true);
/*     */   }
/*     */ 
/*     */   protected void SeleccionTabla(ListSelectionEvent e)
/*     */   {
/*  63 */     if (e.getValueIsAdjusting()) return;
/*  64 */     ListSelectionModel lsm = (ListSelectionModel)e.getSource();
/*  65 */     if (lsm.isSelectionEmpty())
/*     */     {
/*  67 */       this.selectedRow = -1;
/*     */     }
/*     */     else
/*     */     {
/*  71 */       this.selectedRow = lsm.getMinSelectionIndex();
/*  72 */       this.lastSelectedRow = lsm.getMaxSelectionIndex();
/*     */     }
/*     */   }
/*     */ 
/*     */   protected boolean isAccion()
/*     */   {
/*  80 */     return this.Accion;
/*     */   }
/*     */ 
/*     */   protected void setAccion(boolean accion)
/*     */   {
/*  87 */     this.Accion = accion;
/*     */   }
/*     */ 
/*     */   protected boolean isBuscar()
/*     */   {
/*  94 */     return this.Buscar;
/*     */   }
/*     */ 
/*     */   protected void setBuscar(boolean buscar)
/*     */   {
/* 101 */     this.Buscar = buscar;
/*     */   }
/*     */ 
/*     */   protected String getComienzo()
/*     */   {
/* 108 */     return this.comienzo;
/*     */   }
/*     */ 
/*     */   protected void setComienzo(String comienzo)
/*     */   {
/* 115 */     this.comienzo = comienzo;
/*     */   }
/*     */ 
/*     */   protected String getFin()
/*     */   {
/* 122 */     return this.fin;
/*     */   }
/*     */ 
/*     */   protected void setFin(String fin)
/*     */   {
/* 129 */     this.fin = fin;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.MarcoInternoTablas
 * JD-Core Version:    0.6.2
 */