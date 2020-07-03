/*    */ package contaes.calendario;
/*    */ 
/*    */ import java.awt.Dimension;
/*    */ import java.util.Calendar;
/*    */ import java.util.Date;
/*    */ import javax.swing.JSpinner;
/*    */ import javax.swing.JSpinner.DateEditor;
/*    */ 
/*    */ public class IYearEditor extends JSpinner
/*    */ {
/*    */   public IYearEditor()
/*    */   {
/* 42 */     Date hoje = new Date();
/* 43 */     Calendar timeKeeper = Calendar.getInstance();
/* 44 */     timeKeeper.add(1, -100);
/* 45 */     Date dataMinima = timeKeeper.getTime();
/* 46 */     timeKeeper.add(1, 1000);
/* 47 */     Date dataMaxima = timeKeeper.getTime();
/* 48 */     ISpinnerDateModel model = new ISpinnerDateModel(hoje, dataMinima, dataMaxima, 1);
/* 49 */     setModel(model);
/* 50 */     setPreferredSize(new Dimension(80, 20));
/* 51 */     setEditor(new JSpinner.DateEditor(this, "yyyy"));
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.calendario.IYearEditor
 * JD-Core Version:    0.6.2
 */