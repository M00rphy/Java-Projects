/*     */ package contaes.dialogosAuxiliares;
/*     */ 
/*     */ import java.awt.event.KeyAdapter;
/*     */ import java.awt.event.KeyEvent;
/*     */ 
/*     */ class mostrarListas_lista_keyAdapter extends KeyAdapter
/*     */ {
/*     */   private MostrarListas adaptee;
/*     */ 
/*     */   mostrarListas_lista_keyAdapter(MostrarListas adaptee)
/*     */   {
/* 221 */     this.adaptee = adaptee;
/*     */   }
/*     */ 
/*     */   public void keyPressed(KeyEvent e)
/*     */   {
/* 226 */     this.adaptee.lista_keyPressed(e);
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.dialogosAuxiliares.mostrarListas_lista_keyAdapter
 * JD-Core Version:    0.6.2
 */