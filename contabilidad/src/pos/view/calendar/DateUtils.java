/*    */ package pos.view.calendar;
/*    */ 
/*    */ import java.util.Calendar;
/*    */ import java.util.Date;
/*    */ import java.util.GregorianCalendar;
/*    */ 
/*    */ public class DateUtils
/*    */ {
/*    */   public static Date getToday()
/*    */   {
/* 34 */     return getToday(new Date());
/*    */   }
/*    */ 
/*    */   public static Date getToday(Date d)
/*    */   {
/* 39 */     GregorianCalendar ddate = new GregorianCalendar();
/* 40 */     ddate.setTime(d);
/* 41 */     GregorianCalendar ddateday = new GregorianCalendar(ddate.get(1), ddate.get(2), ddate.get(5));
/* 42 */     return ddateday.getTime();
/*    */   }
/*    */ 
/*    */   public static Date getTodayMinutes()
/*    */   {
/* 47 */     return getTodayMinutes(new Date());
/*    */   }
/*    */ 
/*    */   public static Date getTodayMinutes(Date d)
/*    */   {
/* 52 */     GregorianCalendar ddate = new GregorianCalendar();
/* 53 */     ddate.setTime(d);
/* 54 */     GregorianCalendar ddateday = new GregorianCalendar(ddate.get(1), ddate.get(2), ddate.get(5), ddate.get(11), ddate.get(12));
/*    */ 
/* 56 */     return ddateday.getTime();
/*    */   }
/*    */ 
/*    */   public static Date getTodayHours(Date d)
/*    */   {
/* 61 */     Calendar ddate = Calendar.getInstance();
/* 62 */     ddate.setTime(d);
/*    */ 
/* 64 */     Calendar dNow = Calendar.getInstance();
/* 65 */     dNow.clear();
/* 66 */     dNow.set(ddate.get(1), ddate.get(2), ddate.get(5), ddate.get(11), 0, 0);
/*    */ 
/* 69 */     return dNow.getTime();
/*    */   }
/*    */ 
/*    */   public static Date getDate(Date day, Date hour)
/*    */   {
/* 75 */     Calendar dDay = Calendar.getInstance();
/* 76 */     dDay.setTime(day);
/* 77 */     Calendar dHour = Calendar.getInstance();
/* 78 */     dHour.setTime(hour);
/*    */ 
/* 80 */     Calendar dNow = Calendar.getInstance();
/* 81 */     dNow.clear();
/* 82 */     dNow.set(dDay.get(1), dDay.get(2), dDay.get(5), dHour.get(11), dHour.get(12), dHour.get(13));
/*    */ 
/* 85 */     return dNow.getTime();
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.view.calendar.DateUtils
 * JD-Core Version:    0.6.2
 */