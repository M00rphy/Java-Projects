package contaes.calendario;

import java.util.Calendar;
import java.util.Date;

public abstract interface ICalendarField
{
  public abstract void setCalendar(Calendar paramCalendar);

  public abstract Calendar getCalendar();

  public abstract void setDate(Date paramDate);

  public abstract Date getDate();

  public abstract void requestFocus();
}

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.calendario.ICalendarField
 * JD-Core Version:    0.6.2
 */