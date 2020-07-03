/*     */ package contaes.calendario;
/*     */ 
/*     */ import contaes.calendario.event.ISpinnerModelEvent;
/*     */ import contaes.calendario.event.ISpinnerModelListener;
/*     */ import java.util.Date;
/*     */ import javax.swing.SpinnerDateModel;
/*     */ import javax.swing.event.EventListenerList;
/*     */ 
/*     */ public class ISpinnerDateModel extends SpinnerDateModel
/*     */ {
/*  47 */   private EventListenerList listeners = new EventListenerList();
/*     */ 
/*     */   public ISpinnerDateModel()
/*     */   {
/*     */   }
/*     */ 
/*     */   public ISpinnerDateModel(Date value, Comparable start, Comparable end, int calendarField)
/*     */   {
/*  65 */     super(value, start, end, calendarField);
/*     */   }
/*     */ 
/*     */   public Object getNextValue()
/*     */   {
/*  75 */     new Thread()
/*     */     {
/*     */       public void run() {
/*  78 */         ISpinnerDateModel.this.fireProximoValorSelecionado();
/*     */       }
/*     */     }
/*  75 */     .start();
/*     */ 
/*  82 */     Object retValue = super.getNextValue();
/*  83 */     return retValue;
/*     */   }
/*     */ 
/*     */   public Object getPreviousValue()
/*     */   {
/*  94 */     new Thread()
/*     */     {
/*     */       public void run() {
/*  97 */         ISpinnerDateModel.this.firePrevioValorSelecionado();
/*     */       }
/*     */     }
/*  94 */     .start();
/*     */ 
/* 101 */     Object retValue = super.getPreviousValue();
/* 102 */     return retValue;
/*     */   }
/*     */ 
/*     */   public void addModelListener(ISpinnerModelListener listener)
/*     */   {
/* 111 */     this.listeners.add(ISpinnerModelListener.class, listener);
/*     */   }
/*     */ 
/*     */   public void removeModelListener(ISpinnerModelListener listener)
/*     */   {
/* 120 */     this.listeners.remove(ISpinnerModelListener.class, listener);
/*     */   }
/*     */ 
/*     */   private void fireProximoValorSelecionado()
/*     */   {
/* 128 */     Object[] listeners = this.listeners.getListeners(ISpinnerModelListener.class);
/*     */ 
/* 130 */     for (int i = 0; i < listeners.length; i++)
/*     */     {
/* 132 */       ((ISpinnerModelListener)listeners[i]).proximoValorSelecionado(new ISpinnerModelEvent(this));
/*     */     }
/*     */   }
/*     */ 
/*     */   private void firePrevioValorSelecionado()
/*     */   {
/* 141 */     Object[] listeners = this.listeners.getListeners(ISpinnerModelListener.class);
/*     */ 
/* 143 */     for (int i = 0; i < listeners.length; i++)
/* 144 */       ((ISpinnerModelListener)listeners[i]).previoValorSelecionado(new ISpinnerModelEvent(this));
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.calendario.ISpinnerDateModel
 * JD-Core Version:    0.6.2
 */