/*     */ package contaes.dialogosAuxiliares;
/*     */ 
/*     */ import java.awt.event.KeyAdapter;
/*     */ import java.awt.event.KeyEvent;
/*     */ 
/*     */ class mostrarConceptos_lista_keyAdapter extends KeyAdapter
/*     */ {
/*     */   private MostrarConceptos adaptee;
/*     */ 
/*     */   mostrarConceptos_lista_keyAdapter(MostrarConceptos adaptee)
/*     */   {
/* 146 */     this.adaptee = adaptee;
/*     */   }
/*     */ 
/*     */   public void keyPressed(KeyEvent e) {
/* 150 */     this.adaptee.lista_keyPressed(e);
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.dialogosAuxiliares.mostrarConceptos_lista_keyAdapter
 * JD-Core Version:    0.6.2
 */