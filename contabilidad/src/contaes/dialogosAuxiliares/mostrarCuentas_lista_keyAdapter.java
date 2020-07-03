/*     */ package contaes.dialogosAuxiliares;
/*     */ 
/*     */ import java.awt.event.KeyAdapter;
/*     */ import java.awt.event.KeyEvent;
/*     */ 
/*     */ class mostrarCuentas_lista_keyAdapter extends KeyAdapter
/*     */ {
/*     */   private MostrarCuentas adaptee;
/*     */ 
/*     */   mostrarCuentas_lista_keyAdapter(MostrarCuentas adaptee)
/*     */   {
/* 260 */     this.adaptee = adaptee;
/*     */   }
/*     */ 
/*     */   public void keyPressed(KeyEvent e)
/*     */   {
/* 265 */     this.adaptee.lista_keyPressed(e);
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.dialogosAuxiliares.mostrarCuentas_lista_keyAdapter
 * JD-Core Version:    0.6.2
 */