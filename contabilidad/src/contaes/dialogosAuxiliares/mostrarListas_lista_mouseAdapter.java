/*     */ package contaes.dialogosAuxiliares;
/*     */ 
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ 
/*     */ class mostrarListas_lista_mouseAdapter extends MouseAdapter
/*     */ {
/*     */   private MostrarListas adaptee;
/*     */ 
/*     */   mostrarListas_lista_mouseAdapter(MostrarListas adaptee)
/*     */   {
/* 209 */     this.adaptee = adaptee;
/*     */   }
/*     */ 
/*     */   public void mouseClicked(MouseEvent e)
/*     */   {
/* 214 */     this.adaptee.lista_mouseClicked(e);
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.dialogosAuxiliares.mostrarListas_lista_mouseAdapter
 * JD-Core Version:    0.6.2
 */