package contaes.calendario.event;

import java.util.EventListener;

public abstract interface ISpinnerModelListener extends EventListener
{
  public abstract void proximoValorSelecionado(ISpinnerModelEvent paramISpinnerModelEvent);

  public abstract void previoValorSelecionado(ISpinnerModelEvent paramISpinnerModelEvent);
}

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.calendario.event.ISpinnerModelListener
 * JD-Core Version:    0.6.2
 */