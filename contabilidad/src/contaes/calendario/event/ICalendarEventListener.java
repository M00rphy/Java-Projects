package contaes.calendario.event;

import java.util.EventListener;

public abstract interface ICalendarEventListener extends EventListener
{
  public abstract void diaSelecionado(ICalendarEvent paramICalendarEvent);

  public abstract void mesSelecionado(ICalendarEvent paramICalendarEvent);

  public abstract void anoSelecionado(ICalendarEvent paramICalendarEvent);

  public abstract void fechandoCalendario(ICalendarEvent paramICalendarEvent);

  public abstract void abrindoCalendario(ICalendarEvent paramICalendarEvent);

  public abstract boolean isAbilitado();
}

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.calendario.event.ICalendarEventListener
 * JD-Core Version:    0.6.2
 */