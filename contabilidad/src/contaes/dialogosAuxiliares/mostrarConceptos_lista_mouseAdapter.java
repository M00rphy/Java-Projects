/*     */ package contaes.dialogosAuxiliares;
/*     */ 
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ 
/*     */ class mostrarConceptos_lista_mouseAdapter extends MouseAdapter
/*     */ {
/*     */   private MostrarConceptos adaptee;
/*     */ 
/*     */   mostrarConceptos_lista_mouseAdapter(MostrarConceptos adaptee)
/*     */   {
/* 134 */     this.adaptee = adaptee;
/*     */   }
/*     */ 
/*     */   public void mouseClicked(MouseEvent e) {
/* 138 */     this.adaptee.lista_mouseClicked(e);
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.dialogosAuxiliares.mostrarConceptos_lista_mouseAdapter
 * JD-Core Version:    0.6.2
 */