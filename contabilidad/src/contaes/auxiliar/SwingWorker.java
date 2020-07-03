/*     */ package contaes.auxiliar;
/*     */ 
/*     */ import javax.swing.SwingUtilities;
/*     */ 
/*     */ public abstract class SwingWorker
/*     */ {
/*     */   private Object value;
/*  19 */   private boolean isFinished = false;
/*     */   private ThreadVar threadVar;
/*     */ 
/*     */   protected synchronized Object getValue()
/*     */   {
/*  39 */     return this.value;
/*     */   }
/*     */ 
/*     */   private synchronized void setValue(Object x)
/*     */   {
/*  46 */     this.value = x;
/*     */   }
/*     */ 
/*     */   public boolean isFinished() {
/*  50 */     return this.isFinished;
/*     */   }
/*     */ 
/*     */   private void setFinished(boolean isFinished) {
/*  54 */     this.isFinished = isFinished;
/*     */   }
/*     */ 
/*     */   public abstract Object construct();
/*     */ 
/*     */   public void finished()
/*     */   {
/*  67 */     setFinished(true);
/*     */   }
/*     */ 
/*     */   public void interrupt()
/*     */   {
/*  75 */     Thread t = this.threadVar.get();
/*  76 */     if (t != null) {
/*  77 */       t.interrupt();
/*     */     }
/*  79 */     this.threadVar.clear();
/*     */   }
/*     */ 
/*     */   public Object get()
/*     */   {
/*     */     while (true)
/*     */     {
/*  91 */       Thread t = this.threadVar.get();
/*  92 */       if (t == null)
/*  93 */         return getValue();
/*     */       try
/*     */       {
/*  96 */         t.join();
/*     */       }
/*     */       catch (InterruptedException e) {
/*  99 */         Thread.currentThread().interrupt();
/* 100 */         return null;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public SwingWorker()
/*     */   {
/* 111 */     final Runnable doFinished = new Runnable() {
/* 112 */       public void run() { SwingWorker.this.finished(); }
/*     */ 
/*     */     };
/* 115 */     Runnable doConstruct = new Runnable() {
/*     */       public void run() {
/*     */         try {
/* 118 */           SwingWorker.this.setValue(SwingWorker.this.construct());
/*     */         }
/*     */         finally {
/* 121 */           SwingWorker.this.threadVar.clear();
/*     */         }
/*     */ 
/* 124 */         SwingUtilities.invokeLater(doFinished);
/*     */       }
/*     */     };
/* 128 */     Thread t = new Thread(doConstruct);
/* 129 */     this.threadVar = new ThreadVar(t);
/*     */   }
/*     */ 
/*     */   public void start()
/*     */   {
/* 136 */     Thread t = this.threadVar.get();
/* 137 */     if (t != null)
/* 138 */       t.start();
/*     */   }
/*     */ 
/*     */   private static class ThreadVar
/*     */   {
/*     */     private Thread thread;
/*     */ 
/*     */     ThreadVar(Thread t)
/*     */     {
/*  27 */       this.thread = t; } 
/*  28 */     synchronized Thread get() { return this.thread; } 
/*  29 */     synchronized void clear() { this.thread = null; }
/*     */ 
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.auxiliar.SwingWorker
 * JD-Core Version:    0.6.2
 */