package facturacion.control.interfaces;

import facturacion.model.Factura;
import java.util.ArrayList;

public abstract interface FacturaControlInterface
{
  public abstract Integer crear(Factura paramFactura)
    throws Exception;

  public abstract boolean modificar(Factura paramFactura)
    throws Exception;

  public abstract boolean borrar(Factura paramFactura)
    throws Exception;

  public abstract Factura factura(Integer paramInteger)
    throws Exception;

  public abstract Factura factura(String paramString)
    throws Exception;

  public abstract Factura facturaEjercicio(Integer paramInteger)
    throws Exception;

  public abstract Factura facturaEjercicio(String paramString)
    throws Exception;

  public abstract ArrayList<Factura> facturas()
    throws Exception;

  public abstract ArrayList<Factura> facturasEjercicio()
    throws Exception;

  public abstract String nuevoNumeroFactEmitida(String paramString);

  public abstract void cerrarRs();
}

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     facturacion.control.interfaces.FacturaControlInterface
 * JD-Core Version:    0.6.2
 */