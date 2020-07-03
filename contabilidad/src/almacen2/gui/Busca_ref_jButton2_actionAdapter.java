/*     */ package almacen2.gui;
/*     */ 
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ 
/*     */ class Busca_ref_jButton2_actionAdapter
/*     */   implements ActionListener
/*     */ {
/*     */   private BuscarRefOldJDialog adaptee;
/*     */ 
/*     */   Busca_ref_jButton2_actionAdapter(BuscarRefOldJDialog adaptee)
/*     */   {
/* 145 */     this.adaptee = adaptee;
/*     */   }
/*     */ 
/*     */   public void actionPerformed(ActionEvent e) {
/* 149 */     this.adaptee.jButton2_actionPerformed(e);
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.gui.Busca_ref_jButton2_actionAdapter
 * JD-Core Version:    0.6.2
 */