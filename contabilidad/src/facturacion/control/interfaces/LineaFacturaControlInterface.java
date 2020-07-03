package facturacion.control.interfaces;

import facturacion.model.LineaFactura;
import java.util.ArrayList;

public abstract interface LineaFacturaControlInterface
{
  public abstract Integer crear(LineaFactura paramLineaFactura)
    throws Exception;

  public abstract boolean modificar(LineaFactura paramLineaFactura)
    throws Exception;

  public abstract boolean borrar(LineaFactura paramLineaFactura)
    throws Exception;

  public abstract LineaFactura linea(Integer paramInteger)
    throws Exception;

  public abstract ArrayList<LineaFactura> lineas(Integer paramInteger)
    throws Exception;

  public abstract void cerrarRs();
}

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     facturacion.control.interfaces.LineaFacturaControlInterface
 * JD-Core Version:    0.6.2
 */