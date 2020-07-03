/*     */ package contaes.auxiliarTablas;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.event.TableModelEvent;
/*     */ import javax.swing.event.TableModelListener;
/*     */ import javax.swing.table.AbstractTableModel;
/*     */ import javax.swing.table.JTableHeader;
/*     */ import javax.swing.table.TableCellRenderer;
/*     */ import javax.swing.table.TableColumn;
/*     */ import javax.swing.table.TableColumnModel;
/*     */ import javax.swing.table.TableModel;
/*     */ 
/*     */ public class TableSorter extends AbstractTableModel
/*     */ {
/*     */   protected TableModel tableModel;
/*     */   public static final int DESCENDING = -1;
/*     */   public static final int NOT_SORTED = 0;
/*     */   public static final int ASCENDING = 1;
/*  87 */   private static Directive EMPTY_DIRECTIVE = new Directive(-1, 0);
/*     */ 
/*  90 */   public static final Comparator COMPARABLE_COMAPRATOR = new Comparator() {
/*     */     public int compare(Object o1, Object o2) {
/*  92 */       return ((Comparable)o1).compareTo(o2);
/*     */     }
/*  90 */   };
/*     */ 
/*  96 */   public static final Comparator LEXICAL_COMPARATOR = new Comparator() {
/*     */     public int compare(Object o1, Object o2) {
/*  98 */       return o1.toString().compareTo(o2.toString());
/*     */     }
/*  96 */   };
/*     */   private Row[] viewToModel;
/*     */   private int[] modelToView;
/*     */   private JTableHeader tableHeader;
/*     */   private MouseListener mouseListener;
/*     */   private TableModelListener tableModelListener;
/* 108 */   private Map<Class<?>, Comparator<?>> columnComparators = new HashMap();
/* 109 */   private List<Directive> sortingColumns = new ArrayList();
/*     */ 
/*     */   public TableSorter() {
/* 112 */     this.mouseListener = new MouseHandler();
/* 113 */     this.tableModelListener = new TableModelHandler();
/*     */   }
/*     */ 
/*     */   public TableSorter(TableModel tableModel) {
/* 117 */     this();
/* 118 */     setTableModel(tableModel);
/*     */   }
/*     */ 
/*     */   public TableSorter(TableModel tableModel, JTableHeader tableHeader) {
/* 122 */     this();
/* 123 */     setTableHeader(tableHeader);
/* 124 */     setTableModel(tableModel);
/*     */   }
/*     */ 
/*     */   private void clearSortingState() {
/* 128 */     this.viewToModel = null;
/* 129 */     this.modelToView = null;
/*     */   }
/*     */ 
/*     */   public TableModel getTableModel() {
/* 133 */     return this.tableModel;
/*     */   }
/*     */ 
/*     */   public void setTableModel(TableModel tableModel) {
/* 137 */     if (this.tableModel != null) {
/* 138 */       this.tableModel.removeTableModelListener(this.tableModelListener);
/*     */     }
/*     */ 
/* 141 */     this.tableModel = tableModel;
/* 142 */     if (this.tableModel != null) {
/* 143 */       this.tableModel.addTableModelListener(this.tableModelListener);
/*     */     }
/*     */ 
/* 146 */     clearSortingState();
/* 147 */     fireTableStructureChanged();
/*     */   }
/*     */ 
/*     */   public JTableHeader getTableHeader() {
/* 151 */     return this.tableHeader;
/*     */   }
/*     */ 
/*     */   public void setTableHeader(JTableHeader tableHeader) {
/* 155 */     if (this.tableHeader != null) {
/* 156 */       this.tableHeader.removeMouseListener(this.mouseListener);
/* 157 */       TableCellRenderer defaultRenderer = this.tableHeader.getDefaultRenderer();
/* 158 */       if ((defaultRenderer instanceof SortableHeaderRenderer)) {
/* 159 */         this.tableHeader.setDefaultRenderer(((SortableHeaderRenderer)defaultRenderer).tableCellRenderer);
/*     */       }
/*     */     }
/* 162 */     this.tableHeader = tableHeader;
/* 163 */     if (this.tableHeader != null) {
/* 164 */       this.tableHeader.addMouseListener(this.mouseListener);
/* 165 */       this.tableHeader.setDefaultRenderer(new SortableHeaderRenderer(this.tableHeader.getDefaultRenderer()));
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean isSorting()
/*     */   {
/* 171 */     return this.sortingColumns.size() != 0;
/*     */   }
/*     */ 
/*     */   private Directive getDirective(int column) {
/* 175 */     for (int i = 0; i < this.sortingColumns.size(); i++) {
/* 176 */       Directive directive = (Directive)this.sortingColumns.get(i);
/* 177 */       if (directive.column == column) {
/* 178 */         return directive;
/*     */       }
/*     */     }
/* 181 */     return EMPTY_DIRECTIVE;
/*     */   }
/*     */ 
/*     */   public int getSortingStatus(int column) {
/* 185 */     return getDirective(column).direction;
/*     */   }
/*     */ 
/*     */   private void sortingStatusChanged() {
/* 189 */     clearSortingState();
/* 190 */     fireTableDataChanged();
/* 191 */     if (this.tableHeader != null)
/* 192 */       this.tableHeader.repaint();
/*     */   }
/*     */ 
/*     */   public void setSortingStatus(int column, int status)
/*     */   {
/* 197 */     Directive directive = getDirective(column);
/* 198 */     if (directive != EMPTY_DIRECTIVE) {
/* 199 */       this.sortingColumns.remove(directive);
/*     */     }
/* 201 */     if (status != 0) {
/* 202 */       this.sortingColumns.add(new Directive(column, status));
/*     */     }
/* 204 */     sortingStatusChanged();
/*     */   }
/*     */ 
/*     */   protected Icon getHeaderRendererIcon(int column, int size) {
/* 208 */     Directive directive = getDirective(column);
/* 209 */     if (directive == EMPTY_DIRECTIVE) {
/* 210 */       return null;
/*     */     }
/* 212 */     return new Arrow(directive.direction == -1, size, this.sortingColumns.indexOf(directive));
/*     */   }
/*     */ 
/*     */   private void cancelSorting() {
/* 216 */     this.sortingColumns.clear();
/* 217 */     sortingStatusChanged();
/*     */   }
/*     */ 
/*     */   public void setColumnComparator(Class type, Comparator comparator)
/*     */   {
/* 222 */     if (comparator == null)
/* 223 */       this.columnComparators.remove(type);
/*     */     else
/* 225 */       this.columnComparators.put(type, comparator);
/*     */   }
/*     */ 
/*     */   protected Comparator getComparator(int column)
/*     */   {
/* 231 */     Class columnType = this.tableModel.getColumnClass(column);
/* 232 */     Comparator comparator = (Comparator)this.columnComparators.get(columnType);
/* 233 */     if (comparator != null) {
/* 234 */       return comparator;
/*     */     }
/* 236 */     if (Comparable.class.isAssignableFrom(columnType)) {
/* 237 */       return COMPARABLE_COMAPRATOR;
/*     */     }
/* 239 */     return LEXICAL_COMPARATOR;
/*     */   }
/*     */ 
/*     */   private Row[] getViewToModel() {
/* 243 */     if (this.viewToModel == null) {
/* 244 */       int tableModelRowCount = this.tableModel.getRowCount();
/* 245 */       this.viewToModel = new Row[tableModelRowCount];
/* 246 */       for (int row = 0; row < tableModelRowCount; row++) {
/* 247 */         this.viewToModel[row] = new Row(row);
/*     */       }
/*     */ 
/* 250 */       if (isSorting()) {
/* 251 */         Arrays.sort(this.viewToModel);
/*     */       }
/*     */     }
/* 254 */     return this.viewToModel;
/*     */   }
/*     */ 
/*     */   public int modelIndex(int viewIndex) {
/* 258 */     return getViewToModel()[viewIndex].modelIndex;
/*     */   }
/*     */ 
/*     */   private int[] getModelToView() {
/* 262 */     if (this.modelToView == null) {
/* 263 */       int n = getViewToModel().length;
/* 264 */       this.modelToView = new int[n];
/* 265 */       for (int i = 0; i < n; i++) {
/* 266 */         this.modelToView[modelIndex(i)] = i;
/*     */       }
/*     */     }
/* 269 */     return this.modelToView;
/*     */   }
/*     */ 
/*     */   public int getRowCount()
/*     */   {
/* 275 */     return this.tableModel == null ? 0 : this.tableModel.getRowCount();
/*     */   }
/*     */ 
/*     */   public int getColumnCount() {
/* 279 */     return this.tableModel == null ? 0 : this.tableModel.getColumnCount();
/*     */   }
/*     */ 
/*     */   public String getColumnName(int column) {
/* 283 */     return this.tableModel.getColumnName(column);
/*     */   }
/*     */ 
/*     */   public Class getColumnClass(int column)
/*     */   {
/* 288 */     return this.tableModel.getColumnClass(column);
/*     */   }
/*     */ 
/*     */   public boolean isCellEditable(int row, int column) {
/* 292 */     return this.tableModel.isCellEditable(modelIndex(row), column);
/*     */   }
/*     */ 
/*     */   public Object getValueAt(int row, int column) {
/* 296 */     return this.tableModel.getValueAt(modelIndex(row), column);
/*     */   }
/*     */ 
/*     */   public void setValueAt(Object aValue, int row, int column) {
/* 300 */     this.tableModel.setValueAt(aValue, modelIndex(row), column);
/*     */   }
/*     */ 
/*     */   private static class Directive
/*     */   {
/*     */     private int column;
/*     */     private int direction;
/*     */ 
/*     */     public Directive(int column, int direction)
/*     */     {
/* 500 */       this.column = column;
/* 501 */       this.direction = direction;
/*     */     }
/*     */   }
/*     */ 
/*     */   private class SortableHeaderRenderer
/*     */     implements TableCellRenderer
/*     */   {
/*     */     private TableCellRenderer tableCellRenderer;
/*     */ 
/*     */     public SortableHeaderRenderer(TableCellRenderer tableCellRenderer)
/*     */     {
/* 474 */       this.tableCellRenderer = tableCellRenderer;
/*     */     }
/*     */ 
/*     */     public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
/*     */     {
/* 483 */       Component c = this.tableCellRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
/*     */ 
/* 485 */       if ((c instanceof JLabel)) {
/* 486 */         JLabel l = (JLabel)c;
/* 487 */         l.setHorizontalTextPosition(2);
/* 488 */         int modelColumn = table.convertColumnIndexToModel(column);
/* 489 */         l.setIcon(TableSorter.this.getHeaderRendererIcon(modelColumn, l.getFont().getSize()));
/*     */       }
/* 491 */       return c;
/*     */     }
/*     */   }
/*     */ 
/*     */   private static class Arrow
/*     */     implements Icon
/*     */   {
/*     */     private boolean descending;
/*     */     private int size;
/*     */     private int priority;
/*     */ 
/*     */     public Arrow(boolean descending, int size, int priority)
/*     */     {
/* 423 */       this.descending = descending;
/* 424 */       this.size = size;
/* 425 */       this.priority = priority;
/*     */     }
/*     */ 
/*     */     public void paintIcon(Component c, Graphics g, int x, int y) {
/* 429 */       Color color = c == null ? Color.GRAY : c.getBackground();
/*     */ 
/* 432 */       int dx = (int)(this.size / 2 * Math.pow(0.8D, this.priority));
/* 433 */       int dy = this.descending ? dx : -dx;
/*     */ 
/* 435 */       y = y + 5 * this.size / 6 + (this.descending ? -dy : 0);
/* 436 */       int shift = this.descending ? 1 : -1;
/* 437 */       g.translate(x, y);
/*     */ 
/* 440 */       g.setColor(color.darker());
/* 441 */       g.drawLine(dx / 2, dy, 0, 0);
/* 442 */       g.drawLine(dx / 2, dy + shift, 0, shift);
/*     */ 
/* 445 */       g.setColor(color.brighter());
/* 446 */       g.drawLine(dx / 2, dy, dx, 0);
/* 447 */       g.drawLine(dx / 2, dy + shift, dx, shift);
/*     */ 
/* 450 */       if (this.descending)
/* 451 */         g.setColor(color.darker().darker());
/*     */       else {
/* 453 */         g.setColor(color.brighter().brighter());
/*     */       }
/* 455 */       g.drawLine(dx, 0, 0, 0);
/*     */ 
/* 457 */       g.setColor(color);
/* 458 */       g.translate(-x, -y);
/*     */     }
/*     */ 
/*     */     public int getIconWidth() {
/* 462 */       return this.size;
/*     */     }
/*     */ 
/*     */     public int getIconHeight() {
/* 466 */       return this.size;
/*     */     }
/*     */   }
/*     */ 
/*     */   private class MouseHandler extends MouseAdapter
/*     */   {
/*     */     private MouseHandler()
/*     */     {
/*     */     }
/*     */ 
/*     */     public void mouseClicked(MouseEvent e)
/*     */     {
/* 399 */       JTableHeader h = (JTableHeader)e.getSource();
/* 400 */       TableColumnModel columnModel = h.getColumnModel();
/* 401 */       int viewColumn = columnModel.getColumnIndexAtX(e.getX());
/* 402 */       int column = columnModel.getColumn(viewColumn).getModelIndex();
/* 403 */       if (column != -1) {
/* 404 */         int status = TableSorter.this.getSortingStatus(column);
/* 405 */         if (!e.isControlDown()) {
/* 406 */           TableSorter.this.cancelSorting();
/*     */         }
/*     */ 
/* 410 */         status += (e.isShiftDown() ? -1 : 1);
/* 411 */         status = (status + 4) % 3 - 1;
/* 412 */         TableSorter.this.setSortingStatus(column, status);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private class TableModelHandler
/*     */     implements TableModelListener
/*     */   {
/*     */     private TableModelHandler()
/*     */     {
/*     */     }
/*     */ 
/*     */     public void tableChanged(TableModelEvent e)
/*     */     {
/* 345 */       if (!TableSorter.this.isSorting()) {
/* 346 */         TableSorter.this.clearSortingState();
/* 347 */         TableSorter.this.fireTableChanged(e);
/* 348 */         return;
/*     */       }
/*     */ 
/* 354 */       if (e.getFirstRow() == -1) {
/* 355 */         TableSorter.this.cancelSorting();
/* 356 */         TableSorter.this.fireTableChanged(e);
/* 357 */         return;
/*     */       }
/*     */ 
/* 378 */       int column = e.getColumn();
/* 379 */       if ((e.getFirstRow() == e.getLastRow()) && (column != -1) && (TableSorter.this.getSortingStatus(column) == 0) && (TableSorter.this.modelToView != null))
/*     */       {
/* 383 */         int viewIndex = TableSorter.this.getModelToView()[e.getFirstRow()];
/* 384 */         TableSorter.this.fireTableChanged(new TableModelEvent(TableSorter.this, viewIndex, viewIndex, column, e.getType()));
/*     */ 
/* 387 */         return;
/*     */       }
/*     */ 
/* 391 */       TableSorter.this.clearSortingState();
/* 392 */       TableSorter.this.fireTableDataChanged();
/*     */     }
/*     */   }
/*     */ 
/*     */   private class Row
/*     */     implements Comparable
/*     */   {
/*     */     private int modelIndex;
/*     */ 
/*     */     public Row(int index)
/*     */     {
/* 310 */       this.modelIndex = index;
/*     */     }
/*     */ 
/*     */     public int compareTo(Object o) {
/* 314 */       int row1 = this.modelIndex;
/* 315 */       int row2 = ((Row)o).modelIndex;
/*     */ 
/* 317 */       for (Iterator it = TableSorter.this.sortingColumns.iterator(); it.hasNext(); ) {
/* 318 */         TableSorter.Directive directive = (TableSorter.Directive)it.next();
/* 319 */         int column = directive.column;
/* 320 */         Object o1 = TableSorter.this.tableModel.getValueAt(row1, column);
/* 321 */         Object o2 = TableSorter.this.tableModel.getValueAt(row2, column);
/*     */ 
/* 323 */         int comparison = 0;
/*     */ 
/* 325 */         if ((o1 == null) && (o2 == null))
/* 326 */           comparison = 0;
/* 327 */         else if (o1 == null)
/* 328 */           comparison = -1;
/* 329 */         else if (o2 == null)
/* 330 */           comparison = 1;
/*     */         else {
/* 332 */           comparison = TableSorter.this.getComparator(column).compare(o1, o2);
/*     */         }
/* 334 */         if (comparison != 0) {
/* 335 */           return directive.direction == -1 ? -comparison : comparison;
/*     */         }
/*     */       }
/* 338 */       return 0;
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.auxiliarTablas.TableSorter
 * JD-Core Version:    0.6.2
 */