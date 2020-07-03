/*      */ package org.jdesktop.swingx;
/*      */ 
/*      */ import java.awt.Component;
/*      */ import java.awt.Container;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.Insets;
/*      */ import java.awt.LayoutManager;
/*      */ import java.awt.Rectangle;
/*      */ import java.beans.PropertyChangeListener;
/*      */ import java.beans.PropertyChangeSupport;
/*      */ import java.io.IOException;
/*      */ import java.io.Reader;
/*      */ import java.io.StreamTokenizer;
/*      */ import java.io.StringReader;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.ListIterator;
/*      */ import java.util.Map;
/*      */ import javax.swing.UIManager;
/*      */ 
/*      */ public class MultiSplitLayout
/*      */   implements LayoutManager
/*      */ {
/*   74 */   private final Map<String, Component> childMap = new HashMap();
/*   75 */   private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
/*      */   private Node model;
/*      */   private int dividerSize;
/*   78 */   private boolean floatingDividers = true;
/*      */ 
/*      */   public MultiSplitLayout()
/*      */   {
/*   87 */     this(new Leaf("default"));
/*      */   }
/*      */ 
/*      */   public MultiSplitLayout(Node model)
/*      */   {
/*   96 */     this.model = model;
/*   97 */     this.dividerSize = UIManager.getInt("SplitPane.dividerSize");
/*   98 */     if (this.dividerSize == 0)
/*   99 */       this.dividerSize = 7;
/*      */   }
/*      */ 
/*      */   public void addPropertyChangeListener(PropertyChangeListener listener)
/*      */   {
/*  104 */     if (listener != null)
/*  105 */       this.pcs.addPropertyChangeListener(listener);
/*      */   }
/*      */ 
/*      */   public void removePropertyChangeListener(PropertyChangeListener listener) {
/*  109 */     if (listener != null)
/*  110 */       this.pcs.removePropertyChangeListener(listener);
/*      */   }
/*      */ 
/*      */   public PropertyChangeListener[] getPropertyChangeListeners() {
/*  114 */     return this.pcs.getPropertyChangeListeners();
/*      */   }
/*      */ 
/*      */   private void firePCS(String propertyName, Object oldValue, Object newValue) {
/*  118 */     if ((oldValue == null) || (newValue == null) || (!oldValue.equals(newValue)))
/*  119 */       this.pcs.firePropertyChange(propertyName, oldValue, newValue);
/*      */   }
/*      */ 
/*      */   public Node getModel()
/*      */   {
/*  130 */     return this.model;
/*      */   }
/*      */ 
/*      */   public void setModel(Node model)
/*      */   {
/*  143 */     if ((model == null) || ((model instanceof Divider))) {
/*  144 */       throw new IllegalArgumentException("invalid model");
/*      */     }
/*  146 */     Node oldModel = model;
/*  147 */     this.model = model;
/*  148 */     firePCS("model", oldModel, model);
/*      */   }
/*      */ 
/*      */   public int getDividerSize()
/*      */   {
/*  158 */     return this.dividerSize;
/*      */   }
/*      */ 
/*      */   public void setDividerSize(int dividerSize)
/*      */   {
/*  170 */     if (dividerSize < 0) {
/*  171 */       throw new IllegalArgumentException("invalid dividerSize");
/*      */     }
/*  173 */     int oldDividerSize = this.dividerSize;
/*  174 */     this.dividerSize = dividerSize;
/*  175 */     firePCS("dividerSize", Integer.valueOf(oldDividerSize), Integer.valueOf(dividerSize));
/*      */   }
/*      */ 
/*      */   public boolean getFloatingDividers()
/*      */   {
/*  182 */     return this.floatingDividers;
/*      */   }
/*      */ 
/*      */   public void setFloatingDividers(boolean floatingDividers)
/*      */   {
/*  195 */     boolean oldFloatingDividers = this.floatingDividers;
/*  196 */     this.floatingDividers = floatingDividers;
/*  197 */     firePCS("floatingDividers", Boolean.valueOf(oldFloatingDividers), Boolean.valueOf(floatingDividers));
/*      */   }
/*      */ 
/*      */   public void addLayoutComponent(String name, Component child)
/*      */   {
/*  216 */     if (name == null) {
/*  217 */       throw new IllegalArgumentException("name not specified");
/*      */     }
/*  219 */     this.childMap.put(name, child);
/*      */   }
/*      */ 
/*      */   public void removeLayoutComponent(Component child)
/*      */   {
/*  229 */     String name = child.getName();
/*  230 */     if (name != null)
/*  231 */       this.childMap.remove(name);
/*      */   }
/*      */ 
/*      */   private Component childForNode(Node node)
/*      */   {
/*  236 */     if ((node instanceof Leaf)) {
/*  237 */       Leaf leaf = (Leaf)node;
/*  238 */       String name = leaf.getName();
/*  239 */       return name != null ? (Component)this.childMap.get(name) : null;
/*      */     }
/*  241 */     return null;
/*      */   }
/*      */ 
/*      */   private Dimension preferredComponentSize(Node node)
/*      */   {
/*  246 */     Component child = childForNode(node);
/*  247 */     return child != null ? child.getPreferredSize() : new Dimension(0, 0);
/*      */   }
/*      */ 
/*      */   private Dimension minimumComponentSize(Node node)
/*      */   {
/*  253 */     Component child = childForNode(node);
/*  254 */     return child != null ? child.getMinimumSize() : new Dimension(0, 0);
/*      */   }
/*      */ 
/*      */   private Dimension preferredNodeSize(Node root)
/*      */   {
/*  259 */     if ((root instanceof Leaf)) {
/*  260 */       return preferredComponentSize(root);
/*      */     }
/*  262 */     if ((root instanceof Divider)) {
/*  263 */       int dividerSize = getDividerSize();
/*  264 */       return new Dimension(dividerSize, dividerSize);
/*      */     }
/*      */ 
/*  267 */     Split split = (Split)root;
/*  268 */     List splitChildren = split.getChildren();
/*  269 */     int width = 0;
/*  270 */     int height = 0;
/*  271 */     if (split.isRowLayout()) {
/*  272 */       for (Node splitChild : (List<Node>)splitChildren) {
/*  273 */         Dimension size = preferredNodeSize(splitChild);
/*  274 */         width += size.width;
/*  275 */         height = Math.max(height, size.height);
/*      */       }
/*      */     }
/*      */     else {
/*  279 */       for (Node splitChild :( List<Node>) splitChildren) {
/*  280 */         Dimension size = preferredNodeSize(splitChild);
/*  281 */         width = Math.max(width, size.width);
/*  282 */         height += size.height;
/*      */       }
/*      */     }
/*  285 */     return new Dimension(width, height);
/*      */   }
/*      */ 
/*      */   private Dimension minimumNodeSize(Node root)
/*      */   {
/*  290 */     if ((root instanceof Leaf)) {
/*  291 */       Component child = childForNode(root);
/*  292 */       return child != null ? child.getMinimumSize() : new Dimension(0, 0);
/*      */     }
/*  294 */     if ((root instanceof Divider)) {
/*  295 */       int dividerSize = getDividerSize();
/*  296 */       return new Dimension(dividerSize, dividerSize);
/*      */     }
/*      */ 
/*  299 */     Split split = (Split)root;
/*  300 */     List splitChildren = split.getChildren();
/*  301 */     int width = 0;
/*  302 */     int height = 0;
/*  303 */     if (split.isRowLayout()) {
/*  304 */       for (Node splitChild :(List<Node>) splitChildren) {
/*  305 */         Dimension size = minimumNodeSize(splitChild);
/*  306 */         width += size.width;
/*  307 */         height = Math.max(height, size.height);
/*      */       }
/*      */     }
/*      */     else {
/*  311 */       for (Node splitChild :(List<Node>) splitChildren) {
/*  312 */         Dimension size = minimumNodeSize(splitChild);
/*  313 */         width = Math.max(width, size.width);
/*  314 */         height += size.height;
/*      */       }
/*      */     }
/*  317 */     return new Dimension(width, height);
/*      */   }
/*      */ 
/*      */   private Dimension sizeWithInsets(Container parent, Dimension size)
/*      */   {
/*  322 */     Insets insets = parent.getInsets();
/*  323 */     int width = size.width + insets.left + insets.right;
/*  324 */     int height = size.height + insets.top + insets.bottom;
/*  325 */     return new Dimension(width, height);
/*      */   }
/*      */ 
/*      */   public Dimension preferredLayoutSize(Container parent) {
/*  329 */     Dimension size = preferredNodeSize(getModel());
/*  330 */     return sizeWithInsets(parent, size);
/*      */   }
/*      */ 
/*      */   public Dimension minimumLayoutSize(Container parent) {
/*  334 */     Dimension size = minimumNodeSize(getModel());
/*  335 */     return sizeWithInsets(parent, size);
/*      */   }
/*      */ 
/*      */   private Rectangle boundsWithYandHeight(Rectangle bounds, double y, double height)
/*      */   {
/*  340 */     Rectangle r = new Rectangle();
/*  341 */     r.setBounds((int)bounds.getX(), (int)y, (int)bounds.getWidth(), (int)height);
/*  342 */     return r;
/*      */   }
/*      */ 
/*      */   private Rectangle boundsWithXandWidth(Rectangle bounds, double x, double width) {
/*  346 */     Rectangle r = new Rectangle();
/*  347 */     r.setBounds((int)x, (int)bounds.getY(), (int)width, (int)bounds.getHeight());
/*  348 */     return r;
/*      */   }
/*      */ 
/*      */   private void minimizeSplitBounds(Split split, Rectangle bounds)
/*      */   {
/*  353 */     Rectangle splitBounds = new Rectangle(bounds.x, bounds.y, 0, 0);
/*  354 */     List splitChildren = split.getChildren();
/*  355 */     Node lastChild = (Node)splitChildren.get(splitChildren.size() - 1);
/*  356 */     Rectangle lastChildBounds = lastChild.getBounds();
/*  357 */     if (split.isRowLayout()) {
/*  358 */       int lastChildMaxX = lastChildBounds.x + lastChildBounds.width;
/*  359 */       splitBounds.add(lastChildMaxX, bounds.y + bounds.height);
/*      */     }
/*      */     else {
/*  362 */       int lastChildMaxY = lastChildBounds.y + lastChildBounds.height;
/*  363 */       splitBounds.add(bounds.x + bounds.width, lastChildMaxY);
/*      */     }
/*  365 */     split.setBounds(splitBounds);
/*      */   }
/*      */ 
/*      */   private void layoutShrink(Split split, Rectangle bounds)
/*      */   {
/*  370 */     Rectangle splitBounds = split.getBounds();
/*  371 */     ListIterator splitChildren = split.getChildren().listIterator();
/*      */ 
/*  373 */     Node lastWeightedChild = split.lastWeightedChild();
/*      */ 
/*  375 */     if (split.isRowLayout()) {
/*  376 */       int totalWidth = 0;
/*  377 */       int minWeightedWidth = 0;
/*  378 */       int totalWeightedWidth = 0;
/*  379 */       for (Node splitChild : split.getChildren()) {
/*  380 */         int nodeWidth = splitChild.getBounds().width;
/*  381 */         int nodeMinWidth = Math.min(nodeWidth, minimumNodeSize(splitChild).width);
/*  382 */         totalWidth += nodeWidth;
/*  383 */         if (splitChild.getWeight() > 0.0D) {
/*  384 */           minWeightedWidth += nodeMinWidth;
/*  385 */           totalWeightedWidth += nodeWidth;
/*      */         }
/*      */       }
/*      */ 
/*  389 */       double x = bounds.getX();
/*  390 */       double extraWidth = splitBounds.getWidth() - bounds.getWidth();
/*  391 */       double availableWidth = extraWidth;
/*  392 */       boolean onlyShrinkWeightedComponents = totalWeightedWidth - minWeightedWidth > extraWidth;
/*      */ 
/*  395 */       while (splitChildren.hasNext()) {
/*  396 */         Node splitChild = (Node)splitChildren.next();
/*  397 */         Rectangle splitChildBounds = splitChild.getBounds();
/*  398 */         double minSplitChildWidth = minimumNodeSize(splitChild).getWidth();
/*  399 */         double splitChildWeight = onlyShrinkWeightedComponents ? splitChild.getWeight() : splitChildBounds.getWidth() / totalWidth;
/*      */ 
/*  403 */         if (!splitChildren.hasNext()) {
/*  404 */           double newWidth = Math.max(minSplitChildWidth, bounds.getMaxX() - x);
/*  405 */           Rectangle newSplitChildBounds = boundsWithXandWidth(bounds, x, newWidth);
/*  406 */           layout2(splitChild, newSplitChildBounds);
/*      */         }
/*  408 */         else if ((availableWidth > 0.0D) && (splitChildWeight > 0.0D)) {
/*  409 */           double allocatedWidth = Math.rint(splitChildWeight * extraWidth);
/*  410 */           double oldWidth = splitChildBounds.getWidth();
/*  411 */           double newWidth = Math.max(minSplitChildWidth, oldWidth - allocatedWidth);
/*  412 */           Rectangle newSplitChildBounds = boundsWithXandWidth(bounds, x, newWidth);
/*  413 */           layout2(splitChild, newSplitChildBounds);
/*  414 */           availableWidth -= oldWidth - splitChild.getBounds().getWidth();
/*      */         }
/*      */         else {
/*  417 */           double existingWidth = splitChildBounds.getWidth();
/*  418 */           Rectangle newSplitChildBounds = boundsWithXandWidth(bounds, x, existingWidth);
/*  419 */           layout2(splitChild, newSplitChildBounds);
/*      */         }
/*  421 */         x = splitChild.getBounds().getMaxX();
/*      */       }
/*      */     }
/*      */     else
/*      */     {
/*  426 */       int totalHeight = 0;
/*  427 */       int minWeightedHeight = 0;
/*  428 */       int totalWeightedHeight = 0;
/*  429 */       for (Node splitChild : split.getChildren()) {
/*  430 */         int nodeHeight = splitChild.getBounds().height;
/*  431 */         int nodeMinHeight = Math.min(nodeHeight, minimumNodeSize(splitChild).height);
/*  432 */         totalHeight += nodeHeight;
/*  433 */         if (splitChild.getWeight() > 0.0D) {
/*  434 */           minWeightedHeight += nodeMinHeight;
/*  435 */           totalWeightedHeight += nodeHeight;
/*      */         }
/*      */       }
/*      */ 
/*  439 */       double y = bounds.getY();
/*  440 */       double extraHeight = splitBounds.getHeight() - bounds.getHeight();
/*  441 */       double availableHeight = extraHeight;
/*  442 */       boolean onlyShrinkWeightedComponents = totalWeightedHeight - minWeightedHeight > extraHeight;
/*      */ 
/*  445 */       while (splitChildren.hasNext()) {
/*  446 */         Node splitChild = (Node)splitChildren.next();
/*  447 */         Rectangle splitChildBounds = splitChild.getBounds();
/*  448 */         double minSplitChildHeight = minimumNodeSize(splitChild).getHeight();
/*  449 */         double splitChildWeight = onlyShrinkWeightedComponents ? splitChild.getWeight() : splitChildBounds.getHeight() / totalHeight;
/*      */ 
/*  453 */         if (!splitChildren.hasNext()) {
/*  454 */           double oldHeight = splitChildBounds.getHeight();
/*  455 */           double newHeight = Math.max(minSplitChildHeight, bounds.getMaxY() - y);
/*  456 */           Rectangle newSplitChildBounds = boundsWithYandHeight(bounds, y, newHeight);
/*  457 */           layout2(splitChild, newSplitChildBounds);
/*  458 */           availableHeight -= oldHeight - splitChild.getBounds().getHeight();
/*      */         }
/*  460 */         else if ((availableHeight > 0.0D) && (splitChildWeight > 0.0D)) {
/*  461 */           double allocatedHeight = Math.rint(splitChildWeight * extraHeight);
/*  462 */           double oldHeight = splitChildBounds.getHeight();
/*  463 */           double newHeight = Math.max(minSplitChildHeight, oldHeight - allocatedHeight);
/*  464 */           Rectangle newSplitChildBounds = boundsWithYandHeight(bounds, y, newHeight);
/*  465 */           layout2(splitChild, newSplitChildBounds);
/*  466 */           availableHeight -= oldHeight - splitChild.getBounds().getHeight();
/*      */         }
/*      */         else {
/*  469 */           double existingHeight = splitChildBounds.getHeight();
/*  470 */           Rectangle newSplitChildBounds = boundsWithYandHeight(bounds, y, existingHeight);
/*  471 */           layout2(splitChild, newSplitChildBounds);
/*      */         }
/*  473 */         y = splitChild.getBounds().getMaxY();
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  484 */     minimizeSplitBounds(split, bounds);
/*      */   }
/*      */ 
/*      */   private void layoutGrow(Split split, Rectangle bounds)
/*      */   {
/*  489 */     Rectangle splitBounds = split.getBounds();
/*  490 */     ListIterator splitChildren = split.getChildren().listIterator();
/*  491 */     Node lastWeightedChild = split.lastWeightedChild();
/*      */ 
/*  502 */     if (split.isRowLayout()) {
/*  503 */       double x = bounds.getX();
/*  504 */       double extraWidth = bounds.getWidth() - splitBounds.getWidth();
/*  505 */       double availableWidth = extraWidth;
/*      */ 
/*  507 */       while (splitChildren.hasNext()) {
/*  508 */         Node splitChild = (Node)splitChildren.next();
/*  509 */         Rectangle splitChildBounds = splitChild.getBounds();
/*  510 */         double splitChildWeight = splitChild.getWeight();
/*      */ 
/*  512 */         if (!splitChildren.hasNext()) {
/*  513 */           double newWidth = bounds.getMaxX() - x;
/*  514 */           Rectangle newSplitChildBounds = boundsWithXandWidth(bounds, x, newWidth);
/*  515 */           layout2(splitChild, newSplitChildBounds);
/*      */         }
/*  517 */         else if ((availableWidth > 0.0D) && (splitChildWeight > 0.0D)) {
/*  518 */           double allocatedWidth = splitChild.equals(lastWeightedChild) ? availableWidth : Math.rint(splitChildWeight * extraWidth);
/*      */ 
/*  521 */           double newWidth = splitChildBounds.getWidth() + allocatedWidth;
/*  522 */           Rectangle newSplitChildBounds = boundsWithXandWidth(bounds, x, newWidth);
/*  523 */           layout2(splitChild, newSplitChildBounds);
/*  524 */           availableWidth -= allocatedWidth;
/*      */         }
/*      */         else {
/*  527 */           double existingWidth = splitChildBounds.getWidth();
/*  528 */           Rectangle newSplitChildBounds = boundsWithXandWidth(bounds, x, existingWidth);
/*  529 */           layout2(splitChild, newSplitChildBounds);
/*      */         }
/*  531 */         x = splitChild.getBounds().getMaxX();
/*      */       }
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*  545 */       double y = bounds.getY();
/*  546 */       double extraHeight = bounds.getMaxY() - splitBounds.getHeight();
/*  547 */       double availableHeight = extraHeight;
/*      */ 
/*  549 */       while (splitChildren.hasNext()) {
/*  550 */         Node splitChild = (Node)splitChildren.next();
/*  551 */         Rectangle splitChildBounds = splitChild.getBounds();
/*  552 */         double splitChildWeight = splitChild.getWeight();
/*      */ 
/*  554 */         if (!splitChildren.hasNext()) {
/*  555 */           double newHeight = bounds.getMaxY() - y;
/*  556 */           Rectangle newSplitChildBounds = boundsWithYandHeight(bounds, y, newHeight);
/*  557 */           layout2(splitChild, newSplitChildBounds);
/*      */         }
/*  559 */         else if ((availableHeight > 0.0D) && (splitChildWeight > 0.0D)) {
/*  560 */           double allocatedHeight = splitChild.equals(lastWeightedChild) ? availableHeight : Math.rint(splitChildWeight * extraHeight);
/*      */ 
/*  563 */           double newHeight = splitChildBounds.getHeight() + allocatedHeight;
/*  564 */           Rectangle newSplitChildBounds = boundsWithYandHeight(bounds, y, newHeight);
/*  565 */           layout2(splitChild, newSplitChildBounds);
/*  566 */           availableHeight -= allocatedHeight;
/*      */         }
/*      */         else {
/*  569 */           double existingHeight = splitChildBounds.getHeight();
/*  570 */           Rectangle newSplitChildBounds = boundsWithYandHeight(bounds, y, existingHeight);
/*  571 */           layout2(splitChild, newSplitChildBounds);
/*      */         }
/*  573 */         y = splitChild.getBounds().getMaxY();
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private void layout2(Node root, Rectangle bounds)
/*      */   {
/*  583 */     if ((root instanceof Leaf)) {
/*  584 */       Component child = childForNode(root);
/*  585 */       if (child != null) {
/*  586 */         child.setBounds(bounds);
/*      */       }
/*  588 */       root.setBounds(bounds);
/*      */     }
/*  590 */     else if ((root instanceof Divider)) {
/*  591 */       root.setBounds(bounds);
/*      */     }
/*  593 */     else if ((root instanceof Split)) {
/*  594 */       Split split = (Split)root;
/*  595 */       boolean grow = split.getBounds().width <= bounds.width;
/*      */ 
/*  598 */       if (grow) {
/*  599 */         layoutGrow(split, bounds);
/*  600 */         root.setBounds(bounds);
/*      */       }
/*      */       else {
/*  603 */         layoutShrink(split, bounds);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private void layout1(Node root, Rectangle bounds)
/*      */   {
/*  625 */     if ((root instanceof Leaf)) {
/*  626 */       root.setBounds(bounds);
/*      */     }
/*  628 */     else if ((root instanceof Split)) {
/*  629 */       Split split = (Split)root;
/*  630 */       Iterator splitChildren = split.getChildren().iterator();
/*  631 */       Rectangle childBounds = null;
/*  632 */       int dividerSize = getDividerSize();
/*      */ 
/*  641 */       if (split.isRowLayout()) {
/*  642 */         double x = bounds.getX();
/*  643 */         while (splitChildren.hasNext()) {
/*  644 */           Node splitChild = (Node)splitChildren.next();
/*  645 */           Divider dividerChild = splitChildren.hasNext() ? (Divider)splitChildren.next() : null;
/*      */ 
/*  648 */           double childWidth = 0.0D;
/*  649 */           if (getFloatingDividers()) {
/*  650 */             childWidth = preferredNodeSize(splitChild).getWidth();
/*      */           }
/*  653 */           else if (dividerChild != null) {
/*  654 */             childWidth = dividerChild.getBounds().getX() - x;
/*      */           }
/*      */           else {
/*  657 */             childWidth = split.getBounds().getMaxX() - x;
/*      */           }
/*      */ 
/*  660 */           childBounds = boundsWithXandWidth(bounds, x, childWidth);
/*  661 */           layout1(splitChild, childBounds);
/*      */ 
/*  663 */           if ((getFloatingDividers()) && (dividerChild != null)) {
/*  664 */             double dividerX = childBounds.getMaxX();
/*  665 */             Rectangle dividerBounds = boundsWithXandWidth(bounds, dividerX, dividerSize);
/*  666 */             dividerChild.setBounds(dividerBounds);
/*      */           }
/*  668 */           if (dividerChild != null) {
/*  669 */             x = dividerChild.getBounds().getMaxX();
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*  680 */         double y = bounds.getY();
/*  681 */         while (splitChildren.hasNext()) {
/*  682 */           Node splitChild = (Node)splitChildren.next();
/*  683 */           Divider dividerChild = splitChildren.hasNext() ? (Divider)splitChildren.next() : null;
/*      */ 
/*  686 */           double childHeight = 0.0D;
/*  687 */           if (getFloatingDividers()) {
/*  688 */             childHeight = preferredNodeSize(splitChild).getHeight();
/*      */           }
/*  691 */           else if (dividerChild != null) {
/*  692 */             childHeight = dividerChild.getBounds().getY() - y;
/*      */           }
/*      */           else {
/*  695 */             childHeight = split.getBounds().getMaxY() - y;
/*      */           }
/*      */ 
/*  698 */           childBounds = boundsWithYandHeight(bounds, y, childHeight);
/*  699 */           layout1(splitChild, childBounds);
/*      */ 
/*  701 */           if ((getFloatingDividers()) && (dividerChild != null)) {
/*  702 */             double dividerY = childBounds.getMaxY();
/*  703 */             Rectangle dividerBounds = boundsWithYandHeight(bounds, dividerY, dividerSize);
/*  704 */             dividerChild.setBounds(dividerBounds);
/*      */           }
/*  706 */           if (dividerChild != null) {
/*  707 */             y = dividerChild.getBounds().getMaxY();
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*  718 */       minimizeSplitBounds(split, bounds);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void throwInvalidLayout(String msg, Node node)
/*      */   {
/*  739 */     throw new InvalidLayoutException(msg, node);
/*      */   }
/*      */ 
/*      */   private void checkLayout(Node root) {
/*  743 */     if ((root instanceof Split)) {
/*  744 */       Split split = (Split)root;
/*  745 */       if (split.getChildren().size() <= 2) {
/*  746 */         throwInvalidLayout("Split must have > 2 children", root);
/*      */       }
/*  748 */       Iterator splitChildren = split.getChildren().iterator();
/*  749 */       double weight = 0.0D;
/*  750 */       while (splitChildren.hasNext()) {
/*  751 */         Node splitChild = (Node)splitChildren.next();
/*  752 */         if ((splitChild instanceof Divider)) {
/*  753 */           throwInvalidLayout("expected a Split or Leaf Node", splitChild);
/*      */         }
/*  755 */         if (splitChildren.hasNext()) {
/*  756 */           Node dividerChild = (Node)splitChildren.next();
/*  757 */           if (!(dividerChild instanceof Divider)) {
/*  758 */             throwInvalidLayout("expected a Divider Node", dividerChild);
/*      */           }
/*      */         }
/*  761 */         weight += splitChild.getWeight();
/*  762 */         checkLayout(splitChild);
/*      */       }
/*  764 */       if (weight > 1.0D)
/*  765 */         throwInvalidLayout("Split children's total weight > 1.0", root);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void layoutContainer(Container parent)
/*      */   {
/*  776 */     checkLayout(getModel());
/*  777 */     Insets insets = parent.getInsets();
/*  778 */     Dimension size = parent.getSize();
/*  779 */     int width = size.width - (insets.left + insets.right);
/*  780 */     int height = size.height - (insets.top + insets.bottom);
/*  781 */     Rectangle bounds = new Rectangle(insets.left, insets.top, width, height);
/*  782 */     layout1(getModel(), bounds);
/*  783 */     layout2(getModel(), bounds);
/*      */   }
/*      */ 
/*      */   private Divider dividerAt(Node root, int x, int y)
/*      */   {
/*  788 */     if ((root instanceof Divider)) {
/*  789 */       Divider divider = (Divider)root;
/*  790 */       return divider.getBounds().contains(x, y) ? divider : null;
/*      */     }
/*  792 */     if ((root instanceof Split)) {
/*  793 */       Split split = (Split)root;
/*  794 */       for (Node child : split.getChildren()) {
/*  795 */         if (child.getBounds().contains(x, y)) {
/*  796 */           return dividerAt(child, x, y);
/*      */         }
/*      */       }
/*      */     }
/*  800 */     return null;
/*      */   }
/*      */ 
/*      */   public Divider dividerAt(int x, int y)
/*      */   {
/*  812 */     return dividerAt(getModel(), x, y);
/*      */   }
/*      */ 
/*      */   private boolean nodeOverlapsRectangle(Node node, Rectangle r2) {
/*  816 */     Rectangle r1 = node.getBounds();
/*  817 */     return (r1.x <= r2.x + r2.width) && (r1.x + r1.width >= r2.x) && (r1.y <= r2.y + r2.height) && (r1.y + r1.height >= r2.y);
/*      */   }
/*      */ 
/*      */   private List<Divider> dividersThatOverlap(Node root, Rectangle r)
/*      */   {
/*  823 */     if ((nodeOverlapsRectangle(root, r)) && ((root instanceof Split))) {
/*  824 */       List dividers = new ArrayList();
/*  825 */       for (Node child : ((Split)root).getChildren()) {
/*  826 */         if ((child instanceof Divider)) {
/*  827 */           if (nodeOverlapsRectangle(child, r)) {
/*  828 */             dividers.add((Divider)child);
/*      */           }
/*      */         }
/*  831 */         else if ((child instanceof Split)) {
/*  832 */           dividers.addAll(dividersThatOverlap(child, r));
/*      */         }
/*      */       }
/*  835 */       return dividers;
/*      */     }
/*      */ 
/*  838 */     return Collections.emptyList();
/*      */   }
/*      */ 
/*      */   public List<Divider> dividersThatOverlap(Rectangle r)
/*      */   {
/*  851 */     if (r == null) {
/*  852 */       throw new IllegalArgumentException("null Rectangle");
/*      */     }
/*  854 */     return dividersThatOverlap(getModel(), r);
/*      */   }
/*      */ 
/*      */   private static void throwParseException(StreamTokenizer st, String msg)
/*      */     throws Exception
/*      */   {
/* 1170 */     throw new Exception("MultiSplitLayout.parseModel Error: " + msg);
/*      */   }
/*      */ 
/*      */   private static void parseAttribute(String name, StreamTokenizer st, Node node) throws Exception {
/* 1174 */     if (st.nextToken() != 61) {
/* 1175 */       throwParseException(st, "expected '=' after " + name);
/*      */     }
/* 1177 */     if (name.equalsIgnoreCase("WEIGHT")) {
/* 1178 */       if (st.nextToken() == -2) {
/* 1179 */         node.setWeight(st.nval);
/*      */       }
/*      */       else {
/* 1182 */         throwParseException(st, "invalid weight");
/*      */       }
/*      */     }
/* 1185 */     else if (name.equalsIgnoreCase("NAME")) {
/* 1186 */       if (st.nextToken() == -3) {
/* 1187 */         if ((node instanceof Leaf)) {
/* 1188 */           ((Leaf)node).setName(st.sval);
/*      */         }
/*      */         else {
/* 1191 */           throwParseException(st, "can't specify name for " + node);
/*      */         }
/*      */       }
/*      */       else {
/* 1195 */         throwParseException(st, "invalid name");
/*      */       }
/*      */     }
/*      */     else
/* 1199 */       throwParseException(st, "unrecognized attribute \"" + name + "\"");
/*      */   }
/*      */ 
/*      */   private static void addSplitChild(Split parent, Node child)
/*      */   {
/* 1204 */     List children = new ArrayList(parent.getChildren());
/* 1205 */     if (children.size() == 0) {
/* 1206 */       children.add(child);
/*      */     }
/*      */     else {
/* 1209 */       children.add(new Divider());
/* 1210 */       children.add(child);
/*      */     }
/* 1212 */     parent.setChildren(children);
/*      */   }
/*      */ 
/*      */   private static void parseLeaf(StreamTokenizer st, Split parent) throws Exception {
/* 1216 */     Leaf leaf = new Leaf();
/*      */     int token;
/* 1218 */     while (((token = st.nextToken()) != -1) && 
/* 1219 */       (token != 41))
/*      */     {
/* 1222 */       if (token == -3) {
/* 1223 */         parseAttribute(st.sval, st, leaf);
/*      */       }
/*      */       else {
/* 1226 */         throwParseException(st, "Bad Leaf: " + leaf);
/*      */       }
/*      */     }
/* 1229 */     addSplitChild(parent, leaf);
/*      */   }
/*      */ 
/*      */   private static void parseSplit(StreamTokenizer st, Split parent)
/*      */     throws Exception
/*      */   {
/*      */     int token;
/* 1234 */     while (((token = st.nextToken()) != -1) && 
/* 1235 */       (token != 41))
/*      */     {
/* 1238 */       if (token == -3) {
/* 1239 */         if (st.sval.equalsIgnoreCase("WEIGHT")) {
/* 1240 */           parseAttribute(st.sval, st, parent);
/*      */         }
/*      */         else {
/* 1243 */           addSplitChild(parent, new Leaf(st.sval));
/*      */         }
/*      */       }
/* 1246 */       else if (token == 40) {
/* 1247 */         if ((token = st.nextToken()) != -3) {
/* 1248 */           throwParseException(st, "invalid node type");
/*      */         }
/* 1250 */         String nodeType = st.sval.toUpperCase();
/* 1251 */         if (nodeType.equals("LEAF")) {
/* 1252 */           parseLeaf(st, parent);
/*      */         }
/* 1254 */         else if ((nodeType.equals("ROW")) || (nodeType.equals("COLUMN"))) {
/* 1255 */           Split split = new Split();
/* 1256 */           split.setRowLayout(nodeType.equals("ROW"));
/* 1257 */           addSplitChild(parent, split);
/* 1258 */           parseSplit(st, split);
/*      */         }
/*      */         else {
/* 1261 */           throwParseException(st, "unrecognized node type '" + nodeType + "'");
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private static Node parseModel(Reader r) {
/* 1268 */     StreamTokenizer st = new StreamTokenizer(r);
/*      */     try {
/* 1270 */       Split root = new Split();
/* 1271 */       parseSplit(st, root);
/* 1272 */       return (Node)root.getChildren().get(0);
/*      */     }
/*      */     catch (Exception e) {
/* 1275 */       System.err.println(e);
/*      */     } finally {
/*      */       try {
/* 1278 */         r.close(); } catch (IOException ignore) {  }
/*      */     }
/* 1280 */     return null;
/*      */   }
/*      */ 
/*      */   public static Node parseModel(String s)
/*      */   {
/* 1330 */     return parseModel(new StringReader(s));
/*      */   }
/*      */ 
/*      */   private static void printModel(String indent, Node root)
/*      */   {
/* 1335 */     if ((root instanceof Split)) {
/* 1336 */       Split split = (Split)root;
/* 1337 */       System.out.println(indent + split);
/* 1338 */       for (Node child : split.getChildren())
/* 1339 */         printModel(indent + "  ", child);
/*      */     }
/*      */     else
/*      */     {
/* 1343 */       System.out.println(indent + root);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void printModel(Node root)
/*      */   {
/* 1351 */     printModel("", root);
/*      */   }
/*      */ 
/*      */   public static class Divider extends MultiSplitLayout.Node
/*      */   {
/*      */     public final boolean isVertical()
/*      */     {
/* 1151 */       MultiSplitLayout.Split parent = getParent();
/* 1152 */       return parent != null ? parent.isRowLayout() : false;
/*      */     }
/*      */ 
/*      */     public void setWeight(double weight)
/*      */     {
/* 1160 */       throw new UnsupportedOperationException();
/*      */     }
/*      */ 
/*      */     public String toString() {
/* 1164 */       return "MultiSplitLayout.Divider " + getBounds().toString();
/*      */     }
/*      */   }
/*      */ 
/*      */   public static class Leaf extends MultiSplitLayout.Node
/*      */   {
/* 1082 */     private String name = "";
/*      */ 
/*      */     public Leaf()
/*      */     {
/*      */     }
/*      */ 
/*      */     public Leaf(String name)
/*      */     {
/* 1097 */       if (name == null) {
/* 1098 */         throw new IllegalArgumentException("name is null");
/*      */       }
/* 1100 */       this.name = name;
/*      */     }
/*      */ 
/*      */     public String getName()
/*      */     {
/* 1109 */       return this.name;
/*      */     }
/*      */ 
/*      */     public void setName(String name)
/*      */     {
/* 1118 */       if (name == null) {
/* 1119 */         throw new IllegalArgumentException("name is null");
/*      */       }
/* 1121 */       this.name = name;
/*      */     }
/*      */ 
/*      */     public String toString() {
/* 1125 */       StringBuffer sb = new StringBuffer("MultiSplitLayout.Leaf");
/* 1126 */       sb.append(" \"");
/* 1127 */       sb.append(getName());
/* 1128 */       sb.append("\"");
/* 1129 */       sb.append(" weight=");
/* 1130 */       sb.append(getWeight());
/* 1131 */       sb.append(" ");
/* 1132 */       sb.append(getBounds());
/* 1133 */       return sb.toString();
/*      */     }
/*      */   }
/*      */ 
/*      */   public static class Split extends MultiSplitLayout.Node
/*      */   {
/*  984 */     private List<MultiSplitLayout.Node> children = Collections.emptyList();
/*  985 */     private boolean rowLayout = true;
/*      */ 
/*      */     public boolean isRowLayout()
/*      */     {
/*  996 */       return this.rowLayout;
/*      */     }
/*      */ 
/*      */     public void setRowLayout(boolean rowLayout)
/*      */     {
/* 1009 */       this.rowLayout = rowLayout;
/*      */     }
/*      */ 
/*      */     public List<MultiSplitLayout.Node> getChildren()
/*      */     {
/* 1020 */       return new ArrayList(this.children);
/*      */     }
/*      */ 
/*      */     public void setChildren(List<MultiSplitLayout.Node> children)
/*      */     {
/* 1035 */       if (children == null) {
/* 1036 */         throw new IllegalArgumentException("children must be a non-null List");
/*      */       }
/* 1038 */       for (MultiSplitLayout.Node child : this.children) {
/* 1039 */         child.setParent(null);
/*      */       }
/* 1041 */       this.children = new ArrayList(children);
/* 1042 */       for (MultiSplitLayout.Node child : this.children)
/* 1043 */         child.setParent(this);
/*      */     }
/*      */ 
/*      */     public final MultiSplitLayout.Node lastWeightedChild()
/*      */     {
/* 1056 */       List children = getChildren();
/* 1057 */       MultiSplitLayout.Node weightedChild = null;
/* 1058 */       for (MultiSplitLayout.Node child :( List<MultiSplitLayout.Node>) children) {
/* 1059 */         if (child.getWeight() > 0.0D) {
/* 1060 */           weightedChild = child;
/*      */         }
/*      */       }
/* 1063 */       return weightedChild;
/*      */     }
/*      */ 
/*      */     public String toString() {
/* 1067 */       int nChildren = getChildren().size();
/* 1068 */       StringBuffer sb = new StringBuffer("MultiSplitLayout.Split");
/* 1069 */       sb.append(isRowLayout() ? " ROW [" : " COLUMN [");
/* 1070 */       sb.append(new StringBuilder().append(nChildren).append(nChildren == 1 ? " child" : " children").toString());
/* 1071 */       sb.append("] ");
/* 1072 */       sb.append(getBounds());
/* 1073 */       return sb.toString();
/*      */     }
/*      */   }
/*      */ 
/*      */   public static abstract class Node
/*      */   {
/*  862 */     private MultiSplitLayout.Split parent = null;
/*  863 */     private Rectangle bounds = new Rectangle();
/*  864 */     private double weight = 0.0D;
/*      */ 
/*      */     public MultiSplitLayout.Split getParent()
/*      */     {
/*  872 */       return this.parent;
/*      */     }
/*      */ 
/*      */     public void setParent(MultiSplitLayout.Split parent)
/*      */     {
/*  882 */       this.parent = parent;
/*      */     }
/*      */ 
/*      */     public Rectangle getBounds()
/*      */     {
/*  892 */       return new Rectangle(this.bounds);
/*      */     }
/*      */ 
/*      */     public void setBounds(Rectangle bounds)
/*      */     {
/*  905 */       if (bounds == null) {
/*  906 */         throw new IllegalArgumentException("null bounds");
/*      */       }
/*  908 */       this.bounds = new Rectangle(bounds);
/*      */     }
/*      */ 
/*      */     public double getWeight()
/*      */     {
/*  919 */       return this.weight;
/*      */     }
/*      */ 
/*      */     public void setWeight(double weight)
/*      */     {
/*  936 */       if ((weight < 0.0D) || (weight > 1.0D)) {
/*  937 */         throw new IllegalArgumentException("invalid weight");
/*      */       }
/*  939 */       this.weight = weight;
/*      */     }
/*      */ 
/*      */     private Node siblingAtOffset(int offset) {
/*  943 */       MultiSplitLayout.Split parent = getParent();
/*  944 */       if (parent == null) return null;
/*  945 */       List siblings = parent.getChildren();
/*  946 */       int index = siblings.indexOf(this);
/*  947 */       if (index == -1) return null;
/*  948 */       index += offset;
/*  949 */       return (index > -1) && (index < siblings.size()) ? (Node)siblings.get(index) : null;
/*      */     }
/*      */ 
/*      */     public Node nextSibling()
/*      */     {
/*  962 */       return siblingAtOffset(1);
/*      */     }
/*      */ 
/*      */     public Node previousSibling()
/*      */     {
/*  975 */       return siblingAtOffset(-1);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static class InvalidLayoutException extends RuntimeException
/*      */   {
/*      */     private final MultiSplitLayout.Node node;
/*      */ 
/*      */     public InvalidLayoutException(String msg, MultiSplitLayout.Node node)
/*      */     {
/*  729 */       super();
/*  730 */       this.node = node;
/*      */     }
/*      */ 
/*      */     public MultiSplitLayout.Node getNode()
/*      */     {
/*  735 */       return this.node;
/*      */     }
/*      */   }
/*      */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     org.jdesktop.swingx.MultiSplitLayout
 * JD-Core Version:    0.6.2
 */