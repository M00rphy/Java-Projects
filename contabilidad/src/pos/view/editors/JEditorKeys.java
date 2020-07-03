/*     */ package pos.view.editors;
/*     */ 
/*     */ import java.awt.ComponentOrientation;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.EventQueue;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Insets;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.FocusAdapter;
/*     */ import java.awt.event.FocusEvent;
/*     */ import java.awt.event.KeyAdapter;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.util.EventListener;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.event.EventListenerList;
/*     */ 
/*     */ public class JEditorKeys extends JPanel
/*     */   implements EditorKeys
/*     */ {
/*  30 */   private static final char[] SET_DOUBLE = { '', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.', '-' };
/*  31 */   private static final char[] SET_DOUBLE_POSITIVE = { '', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.' };
/*  32 */   private static final char[] SET_INTEGER = { '', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-' };
/*  33 */   private static final char[] SET_INTEGER_POSITIVE = { '', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
/*     */ 
/*  35 */   protected EventListenerList listeners = new EventListenerList();
/*     */   private EditorComponent editorcurrent;
/*     */   private char[] keysavailable;
/*     */   private boolean m_bMinus;
/*     */   private boolean m_bKeyDot;
/*     */   JButton m_jCE;
/*     */   JButton m_jKey0;
/*     */   JButton m_jKey1;
/*     */   JButton m_jKey2;
/*     */   JButton m_jKey3;
/*     */   JButton m_jKey4;
/*     */   JButton m_jKey5;
/*     */   JButton m_jKey6;
/*     */   JButton m_jKey7;
/*     */   JButton m_jKey8;
/*     */   JButton m_jKey9;
/*     */   JButton m_jKeyDot;
/*     */   JButton m_jMinus;
/*     */   JTextField m_txtKeys;
/*     */ 
/*     */   public JEditorKeys()
/*     */   {
/*  45 */     initComponents();
/*     */ 
/*  47 */     this.m_jKey0.addActionListener(new MyKeyNumberListener('0'));
/*  48 */     this.m_jKey1.addActionListener(new MyKeyNumberListener('1'));
/*  49 */     this.m_jKey2.addActionListener(new MyKeyNumberListener('2'));
/*  50 */     this.m_jKey3.addActionListener(new MyKeyNumberListener('3'));
/*  51 */     this.m_jKey4.addActionListener(new MyKeyNumberListener('4'));
/*  52 */     this.m_jKey5.addActionListener(new MyKeyNumberListener('5'));
/*  53 */     this.m_jKey6.addActionListener(new MyKeyNumberListener('6'));
/*  54 */     this.m_jKey7.addActionListener(new MyKeyNumberListener('7'));
/*  55 */     this.m_jKey8.addActionListener(new MyKeyNumberListener('8'));
/*  56 */     this.m_jKey9.addActionListener(new MyKeyNumberListener('9'));
/*  57 */     this.m_jKeyDot.addActionListener(new MyKeyNumberListener('.'));
/*  58 */     this.m_jCE.addActionListener(new MyKeyNumberListener(''));
/*  59 */     this.m_jMinus.addActionListener(new MyKeyNumberListener('-'));
/*     */ 
/*  63 */     this.editorcurrent = null;
/*  64 */     setMode(0);
/*  65 */     doEnabled(false);
/*     */   }
/*     */ 
/*     */   public void setComponentOrientation(ComponentOrientation o)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void addActionListener(ActionListener l)
/*     */   {
/*  74 */     this.listeners.add(ActionListener.class, l);
/*     */   }
/*     */   public void removeActionListener(ActionListener l) {
/*  77 */     this.listeners.remove(ActionListener.class, l);
/*     */   }
/*     */ 
/*     */   protected void fireActionPerformed() {
/*  81 */     EventListener[] l = this.listeners.getListeners(ActionListener.class);
/*  82 */     ActionEvent e = null;
/*  83 */     for (int i = 0; i < l.length; i++) {
/*  84 */       if (e == null) {
/*  85 */         e = new ActionEvent(this, 1001, null);
/*     */       }
/*  87 */       ((ActionListener)l[i]).actionPerformed(e);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void doEnabled(boolean b) {
/*  92 */     this.m_jKey0.setEnabled(b);
/*  93 */     this.m_jKey1.setEnabled(b);
/*  94 */     this.m_jKey2.setEnabled(b);
/*  95 */     this.m_jKey3.setEnabled(b);
/*  96 */     this.m_jKey4.setEnabled(b);
/*  97 */     this.m_jKey5.setEnabled(b);
/*  98 */     this.m_jKey6.setEnabled(b);
/*  99 */     this.m_jKey7.setEnabled(b);
/* 100 */     this.m_jKey8.setEnabled(b);
/* 101 */     this.m_jKey9.setEnabled(b);
/* 102 */     this.m_jKeyDot.setEnabled((b) && (this.m_bKeyDot));
/* 103 */     this.m_jCE.setEnabled(b);
/* 104 */     this.m_jMinus.setEnabled((b) && (this.m_bMinus));
/*     */   }
/*     */ 
/*     */   public void setMode(int iMode) {
/* 108 */     switch (iMode) {
/*     */     case 1:
/* 110 */       this.m_bMinus = true;
/* 111 */       this.m_bKeyDot = true;
/* 112 */       this.keysavailable = SET_DOUBLE;
/* 113 */       break;
/*     */     case 2:
/* 115 */       this.m_bMinus = false;
/* 116 */       this.m_bKeyDot = true;
/* 117 */       this.keysavailable = SET_DOUBLE_POSITIVE;
/* 118 */       break;
/*     */     case 3:
/* 120 */       this.m_bMinus = true;
/* 121 */       this.m_bKeyDot = false;
/* 122 */       this.keysavailable = SET_INTEGER;
/* 123 */       break;
/*     */     case 4:
/* 125 */       this.m_bMinus = false;
/* 126 */       this.m_bKeyDot = false;
/* 127 */       this.keysavailable = SET_INTEGER_POSITIVE;
/* 128 */       break;
/*     */     case 0:
/*     */     default:
/* 131 */       this.m_bMinus = true;
/* 132 */       this.m_bKeyDot = true;
/* 133 */       this.keysavailable = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setActive(EditorComponent e, int iMode)
/*     */   {
/* 156 */     if (this.editorcurrent != null) {
/* 157 */       this.editorcurrent.deactivate();
/*     */     }
/* 159 */     this.editorcurrent = e;
/* 160 */     setMode(iMode);
/* 161 */     doEnabled(true);
/*     */ 
/* 164 */     this.m_txtKeys.setText(null);
/* 165 */     EventQueue.invokeLater(new Runnable() {
/*     */       public void run() {
/* 167 */         JEditorKeys.this.m_txtKeys.requestFocus();
/*     */       }
/*     */     });
/*     */   }
/*     */ 
/*     */   public void setInactive(EditorComponent e)
/*     */   {
/* 174 */     if ((e == this.editorcurrent) && (this.editorcurrent != null)) {
/* 175 */       this.editorcurrent.deactivate();
/* 176 */       this.editorcurrent = null;
/* 177 */       setMode(0);
/* 178 */       doEnabled(false);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/* 191 */     this.m_jKey0 = new JButton();
/* 192 */     this.m_jKey1 = new JButton();
/* 193 */     this.m_jKey4 = new JButton();
/* 194 */     this.m_jKey7 = new JButton();
/* 195 */     this.m_jCE = new JButton();
/* 196 */     this.m_jMinus = new JButton();
/* 197 */     this.m_jKey9 = new JButton();
/* 198 */     this.m_jKey8 = new JButton();
/* 199 */     this.m_jKey5 = new JButton();
/* 200 */     this.m_jKey6 = new JButton();
/* 201 */     this.m_jKey3 = new JButton();
/* 202 */     this.m_jKey2 = new JButton();
/* 203 */     this.m_jKeyDot = new JButton();
/* 204 */     this.m_txtKeys = new JTextField();
/*     */ 
/* 206 */     setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
/* 207 */     setLayout(new GridBagLayout());
/*     */ 
/* 209 */     this.m_jKey0.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/btn0.png")));
/* 210 */     this.m_jKey0.setFocusPainted(false);
/* 211 */     this.m_jKey0.setFocusable(false);
/* 212 */     this.m_jKey0.setMargin(new Insets(8, 16, 8, 16));
/* 213 */     this.m_jKey0.setRequestFocusEnabled(false);
/* 214 */     GridBagConstraints gridBagConstraints = new GridBagConstraints();
/* 215 */     gridBagConstraints.gridx = 0;
/* 216 */     gridBagConstraints.gridy = 4;
/* 217 */     gridBagConstraints.gridwidth = 2;
/* 218 */     gridBagConstraints.fill = 1;
/* 219 */     gridBagConstraints.weightx = 1.0D;
/* 220 */     gridBagConstraints.weighty = 1.0D;
/* 221 */     gridBagConstraints.insets = new Insets(5, 0, 0, 0);
/* 222 */     add(this.m_jKey0, gridBagConstraints);
/*     */ 
/* 224 */     this.m_jKey1.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/btn1.png")));
/* 225 */     this.m_jKey1.setFocusPainted(false);
/* 226 */     this.m_jKey1.setFocusable(false);
/* 227 */     this.m_jKey1.setMargin(new Insets(8, 16, 8, 16));
/* 228 */     this.m_jKey1.setRequestFocusEnabled(false);
/* 229 */     gridBagConstraints = new GridBagConstraints();
/* 230 */     gridBagConstraints.gridx = 0;
/* 231 */     gridBagConstraints.gridy = 3;
/* 232 */     gridBagConstraints.fill = 1;
/* 233 */     gridBagConstraints.weightx = 1.0D;
/* 234 */     gridBagConstraints.weighty = 1.0D;
/* 235 */     gridBagConstraints.insets = new Insets(5, 0, 0, 0);
/* 236 */     add(this.m_jKey1, gridBagConstraints);
/*     */ 
/* 238 */     this.m_jKey4.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/btn4a.png")));
/* 239 */     this.m_jKey4.setFocusPainted(false);
/* 240 */     this.m_jKey4.setFocusable(false);
/* 241 */     this.m_jKey4.setMargin(new Insets(8, 16, 8, 16));
/* 242 */     this.m_jKey4.setRequestFocusEnabled(false);
/* 243 */     gridBagConstraints = new GridBagConstraints();
/* 244 */     gridBagConstraints.gridx = 0;
/* 245 */     gridBagConstraints.gridy = 2;
/* 246 */     gridBagConstraints.fill = 1;
/* 247 */     gridBagConstraints.weightx = 1.0D;
/* 248 */     gridBagConstraints.weighty = 1.0D;
/* 249 */     gridBagConstraints.insets = new Insets(5, 0, 0, 0);
/* 250 */     add(this.m_jKey4, gridBagConstraints);
/*     */ 
/* 252 */     this.m_jKey7.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/btn7a.png")));
/* 253 */     this.m_jKey7.setFocusPainted(false);
/* 254 */     this.m_jKey7.setFocusable(false);
/* 255 */     this.m_jKey7.setMargin(new Insets(8, 16, 8, 16));
/* 256 */     this.m_jKey7.setRequestFocusEnabled(false);
/* 257 */     gridBagConstraints = new GridBagConstraints();
/* 258 */     gridBagConstraints.gridx = 0;
/* 259 */     gridBagConstraints.gridy = 1;
/* 260 */     gridBagConstraints.fill = 1;
/* 261 */     gridBagConstraints.weightx = 1.0D;
/* 262 */     gridBagConstraints.weighty = 1.0D;
/* 263 */     gridBagConstraints.insets = new Insets(5, 0, 0, 0);
/* 264 */     add(this.m_jKey7, gridBagConstraints);
/*     */ 
/* 266 */     this.m_jCE.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/btnce.png")));
/* 267 */     this.m_jCE.setFocusPainted(false);
/* 268 */     this.m_jCE.setFocusable(false);
/* 269 */     this.m_jCE.setMargin(new Insets(8, 16, 8, 16));
/* 270 */     this.m_jCE.setRequestFocusEnabled(false);
/* 271 */     gridBagConstraints = new GridBagConstraints();
/* 272 */     gridBagConstraints.gridx = 0;
/* 273 */     gridBagConstraints.gridy = 0;
/* 274 */     gridBagConstraints.gridwidth = 2;
/* 275 */     gridBagConstraints.fill = 1;
/* 276 */     gridBagConstraints.weightx = 1.0D;
/* 277 */     gridBagConstraints.weighty = 1.0D;
/* 278 */     add(this.m_jCE, gridBagConstraints);
/*     */ 
/* 280 */     this.m_jMinus.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/btnminus.png")));
/* 281 */     this.m_jMinus.setFocusPainted(false);
/* 282 */     this.m_jMinus.setFocusable(false);
/* 283 */     this.m_jMinus.setMargin(new Insets(8, 16, 8, 16));
/* 284 */     this.m_jMinus.setRequestFocusEnabled(false);
/* 285 */     gridBagConstraints = new GridBagConstraints();
/* 286 */     gridBagConstraints.gridx = 2;
/* 287 */     gridBagConstraints.gridy = 0;
/* 288 */     gridBagConstraints.fill = 1;
/* 289 */     gridBagConstraints.weightx = 1.0D;
/* 290 */     gridBagConstraints.weighty = 1.0D;
/* 291 */     gridBagConstraints.insets = new Insets(0, 5, 0, 0);
/* 292 */     add(this.m_jMinus, gridBagConstraints);
/*     */ 
/* 294 */     this.m_jKey9.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/btn9a.png")));
/* 295 */     this.m_jKey9.setFocusPainted(false);
/* 296 */     this.m_jKey9.setFocusable(false);
/* 297 */     this.m_jKey9.setMargin(new Insets(8, 16, 8, 16));
/* 298 */     this.m_jKey9.setRequestFocusEnabled(false);
/* 299 */     gridBagConstraints = new GridBagConstraints();
/* 300 */     gridBagConstraints.gridx = 2;
/* 301 */     gridBagConstraints.gridy = 1;
/* 302 */     gridBagConstraints.fill = 1;
/* 303 */     gridBagConstraints.weightx = 1.0D;
/* 304 */     gridBagConstraints.weighty = 1.0D;
/* 305 */     gridBagConstraints.insets = new Insets(5, 5, 0, 0);
/* 306 */     add(this.m_jKey9, gridBagConstraints);
/*     */ 
/* 308 */     this.m_jKey8.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/btn8a.png")));
/* 309 */     this.m_jKey8.setFocusPainted(false);
/* 310 */     this.m_jKey8.setFocusable(false);
/* 311 */     this.m_jKey8.setMargin(new Insets(8, 16, 8, 16));
/* 312 */     this.m_jKey8.setRequestFocusEnabled(false);
/* 313 */     gridBagConstraints = new GridBagConstraints();
/* 314 */     gridBagConstraints.gridx = 1;
/* 315 */     gridBagConstraints.gridy = 1;
/* 316 */     gridBagConstraints.fill = 1;
/* 317 */     gridBagConstraints.weightx = 1.0D;
/* 318 */     gridBagConstraints.weighty = 1.0D;
/* 319 */     gridBagConstraints.insets = new Insets(5, 5, 0, 0);
/* 320 */     add(this.m_jKey8, gridBagConstraints);
/*     */ 
/* 322 */     this.m_jKey5.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/btn5a.png")));
/* 323 */     this.m_jKey5.setFocusPainted(false);
/* 324 */     this.m_jKey5.setFocusable(false);
/* 325 */     this.m_jKey5.setMargin(new Insets(8, 16, 8, 16));
/* 326 */     this.m_jKey5.setRequestFocusEnabled(false);
/* 327 */     gridBagConstraints = new GridBagConstraints();
/* 328 */     gridBagConstraints.gridx = 1;
/* 329 */     gridBagConstraints.gridy = 2;
/* 330 */     gridBagConstraints.fill = 1;
/* 331 */     gridBagConstraints.weightx = 1.0D;
/* 332 */     gridBagConstraints.weighty = 1.0D;
/* 333 */     gridBagConstraints.insets = new Insets(5, 5, 0, 0);
/* 334 */     add(this.m_jKey5, gridBagConstraints);
/*     */ 
/* 336 */     this.m_jKey6.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/btn6a.png")));
/* 337 */     this.m_jKey6.setFocusPainted(false);
/* 338 */     this.m_jKey6.setFocusable(false);
/* 339 */     this.m_jKey6.setMargin(new Insets(8, 16, 8, 16));
/* 340 */     this.m_jKey6.setRequestFocusEnabled(false);
/* 341 */     gridBagConstraints = new GridBagConstraints();
/* 342 */     gridBagConstraints.gridx = 2;
/* 343 */     gridBagConstraints.gridy = 2;
/* 344 */     gridBagConstraints.fill = 1;
/* 345 */     gridBagConstraints.weightx = 1.0D;
/* 346 */     gridBagConstraints.weighty = 1.0D;
/* 347 */     gridBagConstraints.insets = new Insets(5, 5, 0, 0);
/* 348 */     add(this.m_jKey6, gridBagConstraints);
/*     */ 
/* 350 */     this.m_jKey3.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/btn3a.png")));
/* 351 */     this.m_jKey3.setFocusPainted(false);
/* 352 */     this.m_jKey3.setFocusable(false);
/* 353 */     this.m_jKey3.setMargin(new Insets(8, 16, 8, 16));
/* 354 */     this.m_jKey3.setRequestFocusEnabled(false);
/* 355 */     gridBagConstraints = new GridBagConstraints();
/* 356 */     gridBagConstraints.gridx = 2;
/* 357 */     gridBagConstraints.gridy = 3;
/* 358 */     gridBagConstraints.fill = 1;
/* 359 */     gridBagConstraints.weightx = 1.0D;
/* 360 */     gridBagConstraints.weighty = 1.0D;
/* 361 */     gridBagConstraints.insets = new Insets(5, 5, 0, 0);
/* 362 */     add(this.m_jKey3, gridBagConstraints);
/*     */ 
/* 364 */     this.m_jKey2.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/btn2a.png")));
/* 365 */     this.m_jKey2.setFocusPainted(false);
/* 366 */     this.m_jKey2.setFocusable(false);
/* 367 */     this.m_jKey2.setMargin(new Insets(8, 16, 8, 16));
/* 368 */     this.m_jKey2.setRequestFocusEnabled(false);
/* 369 */     gridBagConstraints = new GridBagConstraints();
/* 370 */     gridBagConstraints.gridx = 1;
/* 371 */     gridBagConstraints.gridy = 3;
/* 372 */     gridBagConstraints.fill = 1;
/* 373 */     gridBagConstraints.weightx = 1.0D;
/* 374 */     gridBagConstraints.weighty = 1.0D;
/* 375 */     gridBagConstraints.insets = new Insets(5, 5, 0, 0);
/* 376 */     add(this.m_jKey2, gridBagConstraints);
/*     */ 
/* 378 */     this.m_jKeyDot.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/btndot.png")));
/* 379 */     this.m_jKeyDot.setFocusPainted(false);
/* 380 */     this.m_jKeyDot.setFocusable(false);
/* 381 */     this.m_jKeyDot.setMargin(new Insets(8, 16, 8, 16));
/* 382 */     this.m_jKeyDot.setRequestFocusEnabled(false);
/* 383 */     gridBagConstraints = new GridBagConstraints();
/* 384 */     gridBagConstraints.gridx = 2;
/* 385 */     gridBagConstraints.gridy = 4;
/* 386 */     gridBagConstraints.fill = 1;
/* 387 */     gridBagConstraints.weightx = 1.0D;
/* 388 */     gridBagConstraints.weighty = 1.0D;
/* 389 */     gridBagConstraints.insets = new Insets(5, 5, 0, 0);
/* 390 */     add(this.m_jKeyDot, gridBagConstraints);
/*     */ 
/* 392 */     this.m_txtKeys.setPreferredSize(new Dimension(0, 0));
/* 393 */     this.m_txtKeys.addFocusListener(new FocusAdapter() {
/*     */       public void focusLost(FocusEvent evt) {
/* 395 */         JEditorKeys.this.m_txtKeysFocusLost(evt);
/*     */       }
/*     */     });
/* 398 */     this.m_txtKeys.addKeyListener(new KeyAdapter() {
/*     */       public void keyTyped(KeyEvent evt) {
/* 400 */         JEditorKeys.this.m_txtKeysKeyTyped(evt);
/*     */       }
/*     */     });
/* 403 */     add(this.m_txtKeys, new GridBagConstraints());
/*     */   }
/*     */ 
/*     */   private void m_txtKeysKeyTyped(KeyEvent evt)
/*     */   {
/* 409 */     if (this.editorcurrent != null) {
/* 410 */       this.m_txtKeys.setText("0");
/*     */ 
/* 413 */       char c = evt.getKeyChar();
/* 414 */       if (c == '\n') {
/* 415 */         fireActionPerformed();
/*     */       }
/* 417 */       else if (this.keysavailable == null)
/*     */       {
/* 419 */         this.editorcurrent.typeChar(c);
/*     */       }
/* 421 */       else for (int i = 0; i < this.keysavailable.length; i++)
/* 422 */           if (c == this.keysavailable[i])
/*     */           {
/* 424 */             this.editorcurrent.typeChar(c);
/*     */           }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void m_txtKeysFocusLost(FocusEvent evt)
/*     */   {
/* 435 */     setInactive(this.editorcurrent);
/*     */   }
/*     */ 
/*     */   private class MyKeyNumberListener
/*     */     implements ActionListener
/*     */   {
/*     */     private char m_cCad;
/*     */ 
/*     */     public MyKeyNumberListener(char cCad)
/*     */     {
/* 143 */       this.m_cCad = cCad;
/*     */     }
/*     */ 
/*     */     public void actionPerformed(ActionEvent evt)
/*     */     {
/* 148 */       if (JEditorKeys.this.editorcurrent != null)
/* 149 */         JEditorKeys.this.editorcurrent.transChar(this.m_cCad);
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.view.editors.JEditorKeys
 * JD-Core Version:    0.6.2
 */