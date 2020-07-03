package pos.view.editors;

import java.awt.Component;

public abstract interface EditorComponent
{
  public abstract void addEditorKeys(EditorKeys paramEditorKeys);

  public abstract Component getComponent();

  public abstract void deactivate();

  public abstract void typeChar(char paramChar);

  public abstract void transChar(char paramChar);
}

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.view.editors.EditorComponent
 * JD-Core Version:    0.6.2
 */