/*     */ package contaes.dialogosAuxiliares;
/*     */ 
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ 
/*     */ class mostrarCuentas_lista_mouseAdapter extends MouseAdapter
/*     */ {
/*     */   private MostrarCuentas adaptee;
/*     */ 
/*     */   mostrarCuentas_lista_mouseAdapter(MostrarCuentas adaptee)
/*     */   {
/* 247 */     this.adaptee = adaptee;
/*     */   }
/*     */ 
/*     */   public void mouseClicked(MouseEvent e)
/*     */   {
/* 252 */     this.adaptee.lista_mouseClicked(e);
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.dialogosAuxiliares.mostrarCuentas_lista_mouseAdapter
 * JD-Core Version:    0.6.2
 */