package contaes.calendario;

import java.awt.Dimension;
import java.util.Date;

public abstract interface ICalendar
{
  public abstract void setTextComponent(ICalendarField paramICalendarField);

  public abstract void setDate(Date paramDate);

  public abstract void setAbilitado(boolean paramBoolean);

  public abstract boolean isAbilitado();

  public abstract void setVisible(boolean paramBoolean);

  public abstract void setLocation(int paramInt1, int paramInt2);

  public abstract Dimension getSize();
}

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.calendario.ICalendar
 * JD-Core Version:    0.6.2
 */