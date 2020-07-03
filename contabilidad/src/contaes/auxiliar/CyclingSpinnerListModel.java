/*    */ package contaes.auxiliar;
/*    */ 
/*    */ import javax.swing.SpinnerListModel;
/*    */ 
/*    */ public class CyclingSpinnerListModel extends SpinnerListModel
/*    */ {
/*    */   Object firstValue;
/*    */   Object lastValue;
/*    */ 
/*    */   public CyclingSpinnerListModel(Object[] values)
/*    */   {
/*  9 */     super(values);
/* 10 */     this.firstValue = values[0];
/* 11 */     this.lastValue = values[(values.length - 1)];
/*    */   }
/*    */ 
/*    */   public Object getNextValue() {
/* 15 */     Object value = super.getNextValue();
/* 16 */     if (value == null) {
/* 17 */       value = this.firstValue;
/*    */     }
/* 19 */     return value;
/*    */   }
/*    */ 
/*    */   public Object getPreviousValue() {
/* 23 */     Object value = super.getPreviousValue();
/* 24 */     if (value == null) {
/* 25 */       value = this.lastValue;
/*    */     }
/* 27 */     return value;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.auxiliar.CyclingSpinnerListModel
 * JD-Core Version:    0.6.2
 */