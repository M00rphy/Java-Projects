package contaes.manejoDatos;

public abstract interface ManejoAgendaInterface
{
  public abstract boolean actualizar(String[] paramArrayOfString);

  public abstract boolean crear(String[] paramArrayOfString);

  public abstract int cuentaPago(String paramString);

  public abstract String[] datos(String paramString);

  public abstract String getCuentaBanco(String paramString);

  public abstract Integer formaPago(String paramString);

  public abstract boolean isExistenDatos();
}

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.ManejoAgendaInterface
 * JD-Core Version:    0.6.2
 */