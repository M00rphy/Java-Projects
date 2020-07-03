/*     */ package org.jdesktop.swingx;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Cursor;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.KeyListener;
/*     */ import java.awt.event.MouseEvent;
/*     */ import javax.accessibility.AccessibleContext;
/*     */ import javax.accessibility.AccessibleRole;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.event.MouseInputAdapter;
/*     */ 
/*     */ public class MultiSplitPane extends JPanel
/*     */ {
/*  48 */   private AccessibleContext accessibleContext = null;
/*  49 */   private boolean continuousLayout = true;
/*  50 */   private DividerPainter dividerPainter = new DefaultDividerPainter();
/*     */ 
/* 220 */   private boolean dragUnderway = false;
/* 221 */   private MultiSplitLayout.Divider dragDivider = null;
/* 222 */   private Rectangle initialDividerBounds = null;
/* 223 */   private boolean oldFloatingDividers = true;
/* 224 */   private int dragOffsetX = 0;
/* 225 */   private int dragOffsetY = 0;
/* 226 */   private int dragMin = -1;
/* 227 */   private int dragMax = -1;
/*     */ 
/*     */   public MultiSplitPane()
/*     */   {
/*  57 */     super(new MultiSplitLayout());
/*  58 */     InputHandler inputHandler = new InputHandler();
/*  59 */     addMouseListener(inputHandler);
/*  60 */     addMouseMotionListener(inputHandler);
/*  61 */     addKeyListener(inputHandler);
/*  62 */     setFocusable(true);
/*     */   }
/*     */ 
/*     */   public final MultiSplitLayout getMultiSplitLayout()
/*     */   {
/*  74 */     return (MultiSplitLayout)getLayout();
/*     */   }
/*     */ 
/*     */   public final void setModel(MultiSplitLayout.Node model)
/*     */   {
/*  86 */     getMultiSplitLayout().setModel(model);
/*     */   }
/*     */ 
/*     */   public final void setDividerSize(int dividerSize)
/*     */   {
/*  99 */     getMultiSplitLayout().setDividerSize(dividerSize);
/*     */   }
/*     */ 
/*     */   public void setContinuousLayout(boolean continuousLayout)
/*     */   {
/* 112 */     boolean oldContinuousLayout = continuousLayout;
/* 113 */     this.continuousLayout = continuousLayout;
/* 114 */     firePropertyChange("continuousLayout", oldContinuousLayout, continuousLayout);
/*     */   }
/*     */ 
/*     */   public boolean isContinuousLayout()
/*     */   {
/* 126 */     return this.continuousLayout;
/*     */   }
/*     */ 
/*     */   public MultiSplitLayout.Divider activeDivider()
/*     */   {
/* 136 */     return this.dragDivider;
/*     */   }
/*     */ 
/*     */   public DividerPainter getDividerPainter()
/*     */   {
/* 174 */     return this.dividerPainter;
/*     */   }
/*     */ 
/*     */   public void setDividerPainter(DividerPainter dividerPainter)
/*     */   {
/* 191 */     this.dividerPainter = dividerPainter;
/*     */   }
/*     */ 
/*     */   protected void paintChildren(Graphics g)
/*     */   {
/* 203 */     super.paintChildren(g);
/* 204 */     DividerPainter dp = getDividerPainter();
/* 205 */     Rectangle clipR = g.getClipBounds();
/* 206 */     if ((dp != null) && (clipR != null)) {
/* 207 */       Graphics dpg = g.create();
/*     */       try {
/* 209 */         MultiSplitLayout msl = getMultiSplitLayout();
/* 210 */         for (MultiSplitLayout.Divider divider : msl.dividersThatOverlap(clipR))
/* 211 */           dp.paint(dpg, divider);
/*     */       }
/*     */       finally
/*     */       {
/* 215 */         dpg.dispose();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void startDrag(int mx, int my)
/*     */   {
/* 230 */     requestFocusInWindow();
/* 231 */     MultiSplitLayout msl = getMultiSplitLayout();
/* 232 */     MultiSplitLayout.Divider divider = msl.dividerAt(mx, my);
/* 233 */     if (divider != null) {
/* 234 */       MultiSplitLayout.Node prevNode = divider.previousSibling();
/* 235 */       MultiSplitLayout.Node nextNode = divider.nextSibling();
/* 236 */       if ((prevNode == null) || (nextNode == null)) {
/* 237 */         this.dragUnderway = false;
/*     */       }
/*     */       else {
/* 240 */         this.initialDividerBounds = divider.getBounds();
/* 241 */         this.dragOffsetX = (mx - this.initialDividerBounds.x);
/* 242 */         this.dragOffsetY = (my - this.initialDividerBounds.y);
/* 243 */         this.dragDivider = divider;
/* 244 */         Rectangle prevNodeBounds = prevNode.getBounds();
/* 245 */         Rectangle nextNodeBounds = nextNode.getBounds();
/* 246 */         if (this.dragDivider.isVertical()) {
/* 247 */           this.dragMin = prevNodeBounds.x;
/* 248 */           this.dragMax = (nextNodeBounds.x + nextNodeBounds.width);
/* 249 */           this.dragMax -= this.dragDivider.getBounds().width;
/*     */         }
/*     */         else {
/* 252 */           this.dragMin = prevNodeBounds.y;
/* 253 */           this.dragMax = (nextNodeBounds.y + nextNodeBounds.height);
/* 254 */           this.dragMax -= this.dragDivider.getBounds().height;
/*     */         }
/* 256 */         this.oldFloatingDividers = getMultiSplitLayout().getFloatingDividers();
/* 257 */         getMultiSplitLayout().setFloatingDividers(false);
/* 258 */         this.dragUnderway = true;
/*     */       }
/*     */     }
/*     */     else {
/* 262 */       this.dragUnderway = false;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void repaintDragLimits() {
/* 267 */     Rectangle damageR = this.dragDivider.getBounds();
/* 268 */     if (this.dragDivider.isVertical()) {
/* 269 */       damageR.x = this.dragMin;
/* 270 */       damageR.width = (this.dragMax - this.dragMin);
/*     */     }
/*     */     else {
/* 273 */       damageR.y = this.dragMin;
/* 274 */       damageR.height = (this.dragMax - this.dragMin);
/*     */     }
/* 276 */     repaint(damageR);
/*     */   }
/*     */ 
/*     */   private void updateDrag(int mx, int my) {
/* 280 */     if (!this.dragUnderway) {
/* 281 */       return;
/*     */     }
/* 283 */     Rectangle oldBounds = this.dragDivider.getBounds();
/* 284 */     Rectangle bounds = new Rectangle(oldBounds);
/* 285 */     if (this.dragDivider.isVertical()) {
/* 286 */       bounds.x = (mx - this.dragOffsetX);
/* 287 */       bounds.x = Math.max(bounds.x, this.dragMin);
/* 288 */       bounds.x = Math.min(bounds.x, this.dragMax);
/*     */     }
/*     */     else {
/* 291 */       bounds.y = (my - this.dragOffsetY);
/* 292 */       bounds.y = Math.max(bounds.y, this.dragMin);
/* 293 */       bounds.y = Math.min(bounds.y, this.dragMax);
/*     */     }
/* 295 */     this.dragDivider.setBounds(bounds);
/* 296 */     if (isContinuousLayout()) {
/* 297 */       revalidate();
/* 298 */       repaintDragLimits();
/*     */     }
/*     */     else {
/* 301 */       repaint(oldBounds.union(bounds));
/*     */     }
/*     */   }
/*     */ 
/*     */   private void clearDragState() {
/* 306 */     this.dragDivider = null;
/* 307 */     this.initialDividerBounds = null;
/* 308 */     this.oldFloatingDividers = true;
/* 309 */     this.dragOffsetX = (this.dragOffsetY = 0);
/* 310 */     this.dragMin = (this.dragMax = -1);
/* 311 */     this.dragUnderway = false;
/*     */   }
/*     */ 
/*     */   private void finishDrag(int x, int y) {
/* 315 */     if (this.dragUnderway) {
/* 316 */       clearDragState();
/* 317 */       if (!isContinuousLayout()) {
/* 318 */         revalidate();
/* 319 */         repaint();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void cancelDrag() {
/* 325 */     if (this.dragUnderway) {
/* 326 */       this.dragDivider.setBounds(this.initialDividerBounds);
/* 327 */       getMultiSplitLayout().setFloatingDividers(this.oldFloatingDividers);
/* 328 */       setCursor(Cursor.getPredefinedCursor(0));
/* 329 */       repaint();
/* 330 */       revalidate();
/* 331 */       clearDragState();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void updateCursor(int x, int y, boolean show) {
/* 336 */     if (this.dragUnderway) {
/* 337 */       return;
/*     */     }
/* 339 */     int cursorID = 0;
/* 340 */     if (show) {
/* 341 */       MultiSplitLayout.Divider divider = getMultiSplitLayout().dividerAt(x, y);
/* 342 */       if (divider != null) {
/* 343 */         cursorID = divider.isVertical() ? 11 : 8;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 348 */     setCursor(Cursor.getPredefinedCursor(cursorID));
/*     */   }
/*     */ 
/*     */   public AccessibleContext getAccessibleContext()
/*     */   {
/* 385 */     if (this.accessibleContext == null) {
/* 386 */       this.accessibleContext = new AccessibleMultiSplitPane();
/*     */     }
/* 388 */     return this.accessibleContext;
/*     */   }
/*     */   protected class AccessibleMultiSplitPane extends JPanel.AccessibleJPanel {
/* 391 */     protected AccessibleMultiSplitPane() { super(); } 
/*     */     public AccessibleRole getAccessibleRole() {
/* 393 */       return AccessibleRole.SPLIT_PANE;
/*     */     }
/*     */   }
/*     */ 
/*     */   private class InputHandler extends MouseInputAdapter
/*     */     implements KeyListener
/*     */   {
/*     */     private InputHandler()
/*     */     {
/*     */     }
/*     */ 
/*     */     public void mouseEntered(MouseEvent e)
/*     */     {
/* 355 */       MultiSplitPane.this.updateCursor(e.getX(), e.getY(), true);
/*     */     }
/*     */ 
/*     */     public void mouseMoved(MouseEvent e) {
/* 359 */       MultiSplitPane.this.updateCursor(e.getX(), e.getY(), true);
/*     */     }
/*     */ 
/*     */     public void mouseExited(MouseEvent e) {
/* 363 */       MultiSplitPane.this.updateCursor(e.getX(), e.getY(), false);
/*     */     }
/*     */ 
/*     */     public void mousePressed(MouseEvent e) {
/* 367 */       MultiSplitPane.this.startDrag(e.getX(), e.getY());
/*     */     }
/*     */     public void mouseReleased(MouseEvent e) {
/* 370 */       MultiSplitPane.this.finishDrag(e.getX(), e.getY());
/*     */     }
/*     */     public void mouseDragged(MouseEvent e) {
/* 373 */       MultiSplitPane.this.updateDrag(e.getX(), e.getY());
/*     */     }
/*     */     public void keyPressed(KeyEvent e) {
/* 376 */       if (e.getKeyCode() == 27)
/* 377 */         MultiSplitPane.this.cancelDrag();
/*     */     }
/*     */ 
/*     */     public void keyReleased(KeyEvent e)
/*     */     {
/*     */     }
/*     */ 
/*     */     public void keyTyped(KeyEvent e)
/*     */     {
/*     */     }
/*     */   }
/*     */ 
/*     */   private class DefaultDividerPainter extends MultiSplitPane.DividerPainter
/*     */   {
/*     */     private DefaultDividerPainter()
/*     */     {
/*     */     }
/*     */ 
/*     */     public void paint(Graphics g, MultiSplitLayout.Divider divider)
/*     */     {
/* 158 */       if ((divider == MultiSplitPane.this.activeDivider()) && (!MultiSplitPane.this.isContinuousLayout())) {
/* 159 */         Graphics2D g2d = (Graphics2D)g;
/* 160 */         g2d.setColor(Color.black);
/* 161 */         g2d.fill(divider.getBounds());
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static abstract class DividerPainter
/*     */   {
/*     */     public abstract void paint(Graphics paramGraphics, MultiSplitLayout.Divider paramDivider);
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     org.jdesktop.swingx.MultiSplitPane
 * JD-Core Version:    0.6.2
 */