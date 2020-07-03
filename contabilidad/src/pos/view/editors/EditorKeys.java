package pos.view.editors;

public abstract interface EditorKeys
{
  public static final int MODE_STRING = 0;
  public static final int MODE_DOUBLE = 1;
  public static final int MODE_DOUBLE_POSITIVE = 2;
  public static final int MODE_INTEGER = 3;
  public static final int MODE_INTEGER_POSITIVE = 4;

  public abstract void setActive(EditorComponent paramEditorComponent, int paramInt);

  public abstract void setInactive(EditorComponent paramEditorComponent);
}

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.view.editors.EditorKeys
 * JD-Core Version:    0.6.2
 */