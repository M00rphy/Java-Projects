/*     */ package pos.view;
/*     */ 
/*     */ import java.awt.ComponentOrientation;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Insets;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ public class JNumberKeys extends JPanel
/*     */ {
/*  28 */   private Vector m_Listeners = new Vector();
/*     */ 
/*  30 */   private boolean minusenabled = true;
/*  31 */   private boolean equalsenabled = true;
/*     */   private JButton m_jCE;
/*     */   private JButton m_jEquals;
/*     */   private JButton m_jKey0;
/*     */   private JButton m_jKey1;
/*     */   private JButton m_jKey2;
/*     */   private JButton m_jKey3;
/*     */   private JButton m_jKey4;
/*     */   private JButton m_jKey5;
/*     */   private JButton m_jKey6;
/*     */   private JButton m_jKey7;
/*     */   private JButton m_jKey8;
/*     */   private JButton m_jKey9;
/*     */   private JButton m_jKeyDot;
/*     */   private JButton m_jMinus;
/*     */   private JButton m_jMultiply;
/*     */   private JButton m_jPlus;
/*     */ 
/*     */   public JNumberKeys()
/*     */   {
/*  35 */     initComponents();
/*     */ 
/*  37 */     this.m_jKey0.addActionListener(new MyKeyNumberListener('0'));
/*  38 */     this.m_jKey1.addActionListener(new MyKeyNumberListener('1'));
/*  39 */     this.m_jKey2.addActionListener(new MyKeyNumberListener('2'));
/*  40 */     this.m_jKey3.addActionListener(new MyKeyNumberListener('3'));
/*  41 */     this.m_jKey4.addActionListener(new MyKeyNumberListener('4'));
/*  42 */     this.m_jKey5.addActionListener(new MyKeyNumberListener('5'));
/*  43 */     this.m_jKey6.addActionListener(new MyKeyNumberListener('6'));
/*  44 */     this.m_jKey7.addActionListener(new MyKeyNumberListener('7'));
/*  45 */     this.m_jKey8.addActionListener(new MyKeyNumberListener('8'));
/*  46 */     this.m_jKey9.addActionListener(new MyKeyNumberListener('9'));
/*  47 */     this.m_jKeyDot.addActionListener(new MyKeyNumberListener('.'));
/*  48 */     this.m_jMultiply.addActionListener(new MyKeyNumberListener('*'));
/*  49 */     this.m_jCE.addActionListener(new MyKeyNumberListener(''));
/*  50 */     this.m_jPlus.addActionListener(new MyKeyNumberListener('+'));
/*  51 */     this.m_jMinus.addActionListener(new MyKeyNumberListener('-'));
/*  52 */     this.m_jEquals.addActionListener(new MyKeyNumberListener('='));
/*     */   }
/*     */ 
/*     */   public void setNumbersOnly(boolean value) {
/*  56 */     this.m_jEquals.setVisible(value);
/*  57 */     this.m_jMinus.setVisible(value);
/*  58 */     this.m_jPlus.setVisible(value);
/*  59 */     this.m_jMultiply.setVisible(value);
/*     */   }
/*     */ 
/*     */   public void setEnabled(boolean b)
/*     */   {
/*  64 */     super.setEnabled(b);
/*     */ 
/*  66 */     this.m_jKey0.setEnabled(b);
/*  67 */     this.m_jKey1.setEnabled(b);
/*  68 */     this.m_jKey2.setEnabled(b);
/*  69 */     this.m_jKey3.setEnabled(b);
/*  70 */     this.m_jKey4.setEnabled(b);
/*  71 */     this.m_jKey5.setEnabled(b);
/*  72 */     this.m_jKey6.setEnabled(b);
/*  73 */     this.m_jKey7.setEnabled(b);
/*  74 */     this.m_jKey8.setEnabled(b);
/*  75 */     this.m_jKey9.setEnabled(b);
/*  76 */     this.m_jKeyDot.setEnabled(b);
/*  77 */     this.m_jMultiply.setEnabled(b);
/*  78 */     this.m_jCE.setEnabled(b);
/*  79 */     this.m_jPlus.setEnabled(b);
/*  80 */     this.m_jMinus.setEnabled((this.minusenabled) && (b));
/*  81 */     this.m_jEquals.setEnabled((this.equalsenabled) && (b));
/*     */   }
/*     */ 
/*     */   public void setComponentOrientation(ComponentOrientation o)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void setMinusEnabled(boolean b)
/*     */   {
/*  90 */     this.minusenabled = b;
/*  91 */     this.m_jMinus.setEnabled((this.minusenabled) && (isEnabled()));
/*     */   }
/*     */ 
/*     */   public boolean isMinusEnabled() {
/*  95 */     return this.minusenabled;
/*     */   }
/*     */ 
/*     */   public void setEqualsEnabled(boolean b) {
/*  99 */     this.equalsenabled = b;
/* 100 */     this.m_jEquals.setEnabled((this.equalsenabled) && (isEnabled()));
/*     */   }
/*     */ 
/*     */   public boolean isEqualsEnabled() {
/* 104 */     return this.equalsenabled;
/*     */   }
/*     */ 
/*     */   public boolean isNumbersOnly()
/*     */   {
/* 109 */     return this.m_jEquals.isVisible();
/*     */   }
/*     */ 
/*     */   public void addJNumberEventListener(JNumberEventListener listener) {
/* 113 */     this.m_Listeners.add(listener);
/*     */   }
/*     */   public void removeJNumberEventListener(JNumberEventListener listener) {
/* 116 */     this.m_Listeners.remove(listener);
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/* 147 */     this.m_jCE = new JButton();
/* 148 */     this.m_jMultiply = new JButton();
/* 149 */     this.m_jMinus = new JButton();
/* 150 */     this.m_jPlus = new JButton();
/* 151 */     this.m_jKey9 = new JButton();
/* 152 */     this.m_jKey8 = new JButton();
/* 153 */     this.m_jKey7 = new JButton();
/* 154 */     this.m_jKey4 = new JButton();
/* 155 */     this.m_jKey5 = new JButton();
/* 156 */     this.m_jKey6 = new JButton();
/* 157 */     this.m_jKey3 = new JButton();
/* 158 */     this.m_jKey2 = new JButton();
/* 159 */     this.m_jKey1 = new JButton();
/* 160 */     this.m_jKey0 = new JButton();
/* 161 */     this.m_jKeyDot = new JButton();
/* 162 */     this.m_jEquals = new JButton();
/*     */ 
/* 164 */     setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
/* 165 */     setLayout(new GridBagLayout());
/*     */ 
/* 167 */     this.m_jCE.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/btnce.png")));
/* 168 */     this.m_jCE.setFocusPainted(false);
/* 169 */     this.m_jCE.setFocusable(false);
/* 170 */     this.m_jCE.setMargin(new Insets(8, 16, 8, 16));
/* 171 */     this.m_jCE.setRequestFocusEnabled(false);
/* 172 */     GridBagConstraints gridBagConstraints = new GridBagConstraints();
/* 173 */     gridBagConstraints.gridx = 0;
/* 174 */     gridBagConstraints.gridy = 0;
/* 175 */     gridBagConstraints.gridwidth = 2;
/* 176 */     gridBagConstraints.fill = 1;
/* 177 */     gridBagConstraints.weightx = 1.0D;
/* 178 */     gridBagConstraints.weighty = 1.0D;
/* 179 */     add(this.m_jCE, gridBagConstraints);
/*     */ 
/* 181 */     this.m_jMultiply.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/btnmult.png")));
/* 182 */     this.m_jMultiply.setFocusPainted(false);
/* 183 */     this.m_jMultiply.setFocusable(false);
/* 184 */     this.m_jMultiply.setMargin(new Insets(8, 16, 8, 16));
/* 185 */     this.m_jMultiply.setRequestFocusEnabled(false);
/* 186 */     gridBagConstraints = new GridBagConstraints();
/* 187 */     gridBagConstraints.gridx = 2;
/* 188 */     gridBagConstraints.gridy = 0;
/* 189 */     gridBagConstraints.fill = 1;
/* 190 */     gridBagConstraints.weightx = 1.0D;
/* 191 */     gridBagConstraints.weighty = 1.0D;
/* 192 */     gridBagConstraints.insets = new Insets(0, 5, 0, 0);
/* 193 */     add(this.m_jMultiply, gridBagConstraints);
/*     */ 
/* 195 */     this.m_jMinus.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/btnminus.png")));
/* 196 */     this.m_jMinus.setFocusPainted(false);
/* 197 */     this.m_jMinus.setFocusable(false);
/* 198 */     this.m_jMinus.setMargin(new Insets(8, 16, 8, 16));
/* 199 */     this.m_jMinus.setRequestFocusEnabled(false);
/* 200 */     gridBagConstraints = new GridBagConstraints();
/* 201 */     gridBagConstraints.gridx = 3;
/* 202 */     gridBagConstraints.gridy = 0;
/* 203 */     gridBagConstraints.fill = 1;
/* 204 */     gridBagConstraints.weightx = 1.0D;
/* 205 */     gridBagConstraints.weighty = 1.0D;
/* 206 */     gridBagConstraints.insets = new Insets(0, 5, 0, 0);
/* 207 */     add(this.m_jMinus, gridBagConstraints);
/*     */ 
/* 209 */     this.m_jPlus.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/btnplus.png")));
/* 210 */     this.m_jPlus.setFocusPainted(false);
/* 211 */     this.m_jPlus.setFocusable(false);
/* 212 */     this.m_jPlus.setMargin(new Insets(8, 16, 8, 16));
/* 213 */     this.m_jPlus.setRequestFocusEnabled(false);
/* 214 */     gridBagConstraints = new GridBagConstraints();
/* 215 */     gridBagConstraints.gridx = 3;
/* 216 */     gridBagConstraints.gridy = 1;
/* 217 */     gridBagConstraints.gridheight = 2;
/* 218 */     gridBagConstraints.fill = 1;
/* 219 */     gridBagConstraints.weightx = 1.0D;
/* 220 */     gridBagConstraints.weighty = 1.0D;
/* 221 */     gridBagConstraints.insets = new Insets(5, 5, 0, 0);
/* 222 */     add(this.m_jPlus, gridBagConstraints);
/*     */ 
/* 224 */     this.m_jKey9.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/btn9.png")));
/* 225 */     this.m_jKey9.setFocusPainted(false);
/* 226 */     this.m_jKey9.setFocusable(false);
/* 227 */     this.m_jKey9.setMargin(new Insets(8, 16, 8, 16));
/* 228 */     this.m_jKey9.setRequestFocusEnabled(false);
/* 229 */     gridBagConstraints = new GridBagConstraints();
/* 230 */     gridBagConstraints.gridx = 2;
/* 231 */     gridBagConstraints.gridy = 1;
/* 232 */     gridBagConstraints.fill = 1;
/* 233 */     gridBagConstraints.weightx = 1.0D;
/* 234 */     gridBagConstraints.weighty = 1.0D;
/* 235 */     gridBagConstraints.insets = new Insets(5, 5, 0, 0);
/* 236 */     add(this.m_jKey9, gridBagConstraints);
/*     */ 
/* 238 */     this.m_jKey8.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/btn8.png")));
/* 239 */     this.m_jKey8.setFocusPainted(false);
/* 240 */     this.m_jKey8.setFocusable(false);
/* 241 */     this.m_jKey8.setMargin(new Insets(8, 16, 8, 16));
/* 242 */     this.m_jKey8.setRequestFocusEnabled(false);
/* 243 */     gridBagConstraints = new GridBagConstraints();
/* 244 */     gridBagConstraints.gridx = 1;
/* 245 */     gridBagConstraints.gridy = 1;
/* 246 */     gridBagConstraints.fill = 1;
/* 247 */     gridBagConstraints.weightx = 1.0D;
/* 248 */     gridBagConstraints.weighty = 1.0D;
/* 249 */     gridBagConstraints.insets = new Insets(5, 5, 0, 0);
/* 250 */     add(this.m_jKey8, gridBagConstraints);
/*     */ 
/* 252 */     this.m_jKey7.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/btn7.png")));
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
/* 266 */     this.m_jKey4.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/btn4.png")));
/* 267 */     this.m_jKey4.setFocusPainted(false);
/* 268 */     this.m_jKey4.setFocusable(false);
/* 269 */     this.m_jKey4.setMargin(new Insets(8, 16, 8, 16));
/* 270 */     this.m_jKey4.setRequestFocusEnabled(false);
/* 271 */     gridBagConstraints = new GridBagConstraints();
/* 272 */     gridBagConstraints.gridx = 0;
/* 273 */     gridBagConstraints.gridy = 2;
/* 274 */     gridBagConstraints.fill = 1;
/* 275 */     gridBagConstraints.weightx = 1.0D;
/* 276 */     gridBagConstraints.weighty = 1.0D;
/* 277 */     gridBagConstraints.insets = new Insets(5, 0, 0, 0);
/* 278 */     add(this.m_jKey4, gridBagConstraints);
/*     */ 
/* 280 */     this.m_jKey5.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/btn5.png")));
/* 281 */     this.m_jKey5.setFocusPainted(false);
/* 282 */     this.m_jKey5.setFocusable(false);
/* 283 */     this.m_jKey5.setMargin(new Insets(8, 16, 8, 16));
/* 284 */     this.m_jKey5.setRequestFocusEnabled(false);
/* 285 */     gridBagConstraints = new GridBagConstraints();
/* 286 */     gridBagConstraints.gridx = 1;
/* 287 */     gridBagConstraints.gridy = 2;
/* 288 */     gridBagConstraints.fill = 1;
/* 289 */     gridBagConstraints.weightx = 1.0D;
/* 290 */     gridBagConstraints.weighty = 1.0D;
/* 291 */     gridBagConstraints.insets = new Insets(5, 5, 0, 0);
/* 292 */     add(this.m_jKey5, gridBagConstraints);
/*     */ 
/* 294 */     this.m_jKey6.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/btn6.png")));
/* 295 */     this.m_jKey6.setFocusPainted(false);
/* 296 */     this.m_jKey6.setFocusable(false);
/* 297 */     this.m_jKey6.setMargin(new Insets(8, 16, 8, 16));
/* 298 */     this.m_jKey6.setRequestFocusEnabled(false);
/* 299 */     gridBagConstraints = new GridBagConstraints();
/* 300 */     gridBagConstraints.gridx = 2;
/* 301 */     gridBagConstraints.gridy = 2;
/* 302 */     gridBagConstraints.fill = 1;
/* 303 */     gridBagConstraints.weightx = 1.0D;
/* 304 */     gridBagConstraints.weighty = 1.0D;
/* 305 */     gridBagConstraints.insets = new Insets(5, 5, 0, 0);
/* 306 */     add(this.m_jKey6, gridBagConstraints);
/*     */ 
/* 308 */     this.m_jKey3.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/btn3.png")));
/* 309 */     this.m_jKey3.setFocusPainted(false);
/* 310 */     this.m_jKey3.setFocusable(false);
/* 311 */     this.m_jKey3.setMargin(new Insets(8, 16, 8, 16));
/* 312 */     this.m_jKey3.setRequestFocusEnabled(false);
/* 313 */     gridBagConstraints = new GridBagConstraints();
/* 314 */     gridBagConstraints.gridx = 2;
/* 315 */     gridBagConstraints.gridy = 3;
/* 316 */     gridBagConstraints.fill = 1;
/* 317 */     gridBagConstraints.weightx = 1.0D;
/* 318 */     gridBagConstraints.weighty = 1.0D;
/* 319 */     gridBagConstraints.insets = new Insets(5, 5, 0, 0);
/* 320 */     add(this.m_jKey3, gridBagConstraints);
/*     */ 
/* 322 */     this.m_jKey2.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/btn2.png")));
/* 323 */     this.m_jKey2.setFocusPainted(false);
/* 324 */     this.m_jKey2.setFocusable(false);
/* 325 */     this.m_jKey2.setMargin(new Insets(8, 16, 8, 16));
/* 326 */     this.m_jKey2.setRequestFocusEnabled(false);
/* 327 */     gridBagConstraints = new GridBagConstraints();
/* 328 */     gridBagConstraints.gridx = 1;
/* 329 */     gridBagConstraints.gridy = 3;
/* 330 */     gridBagConstraints.fill = 1;
/* 331 */     gridBagConstraints.weightx = 1.0D;
/* 332 */     gridBagConstraints.weighty = 1.0D;
/* 333 */     gridBagConstraints.insets = new Insets(5, 5, 0, 0);
/* 334 */     add(this.m_jKey2, gridBagConstraints);
/*     */ 
/* 336 */     this.m_jKey1.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/btn1.png")));
/* 337 */     this.m_jKey1.setFocusPainted(false);
/* 338 */     this.m_jKey1.setFocusable(false);
/* 339 */     this.m_jKey1.setMargin(new Insets(8, 16, 8, 16));
/* 340 */     this.m_jKey1.setRequestFocusEnabled(false);
/* 341 */     gridBagConstraints = new GridBagConstraints();
/* 342 */     gridBagConstraints.gridx = 0;
/* 343 */     gridBagConstraints.gridy = 3;
/* 344 */     gridBagConstraints.fill = 1;
/* 345 */     gridBagConstraints.weightx = 1.0D;
/* 346 */     gridBagConstraints.weighty = 1.0D;
/* 347 */     gridBagConstraints.insets = new Insets(5, 0, 0, 0);
/* 348 */     add(this.m_jKey1, gridBagConstraints);
/*     */ 
/* 350 */     this.m_jKey0.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/btn0.png")));
/* 351 */     this.m_jKey0.setFocusPainted(false);
/* 352 */     this.m_jKey0.setFocusable(false);
/* 353 */     this.m_jKey0.setMargin(new Insets(8, 16, 8, 16));
/* 354 */     this.m_jKey0.setRequestFocusEnabled(false);
/* 355 */     gridBagConstraints = new GridBagConstraints();
/* 356 */     gridBagConstraints.gridx = 0;
/* 357 */     gridBagConstraints.gridy = 4;
/* 358 */     gridBagConstraints.gridwidth = 2;
/* 359 */     gridBagConstraints.fill = 1;
/* 360 */     gridBagConstraints.weightx = 1.0D;
/* 361 */     gridBagConstraints.weighty = 1.0D;
/* 362 */     gridBagConstraints.insets = new Insets(5, 0, 0, 0);
/* 363 */     add(this.m_jKey0, gridBagConstraints);
/*     */ 
/* 365 */     this.m_jKeyDot.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/btndot.png")));
/* 366 */     this.m_jKeyDot.setFocusPainted(false);
/* 367 */     this.m_jKeyDot.setFocusable(false);
/* 368 */     this.m_jKeyDot.setMargin(new Insets(8, 16, 8, 16));
/* 369 */     this.m_jKeyDot.setRequestFocusEnabled(false);
/* 370 */     gridBagConstraints = new GridBagConstraints();
/* 371 */     gridBagConstraints.gridx = 2;
/* 372 */     gridBagConstraints.gridy = 4;
/* 373 */     gridBagConstraints.fill = 1;
/* 374 */     gridBagConstraints.weightx = 1.0D;
/* 375 */     gridBagConstraints.weighty = 1.0D;
/* 376 */     gridBagConstraints.insets = new Insets(5, 5, 0, 0);
/* 377 */     add(this.m_jKeyDot, gridBagConstraints);
/*     */ 
/* 379 */     this.m_jEquals.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/btnequals.png")));
/* 380 */     this.m_jEquals.setFocusPainted(false);
/* 381 */     this.m_jEquals.setFocusable(false);
/* 382 */     this.m_jEquals.setMargin(new Insets(8, 16, 8, 16));
/* 383 */     this.m_jEquals.setRequestFocusEnabled(false);
/* 384 */     gridBagConstraints = new GridBagConstraints();
/* 385 */     gridBagConstraints.gridx = 3;
/* 386 */     gridBagConstraints.gridy = 3;
/* 387 */     gridBagConstraints.gridheight = 2;
/* 388 */     gridBagConstraints.fill = 1;
/* 389 */     gridBagConstraints.weightx = 1.0D;
/* 390 */     gridBagConstraints.weighty = 1.0D;
/* 391 */     gridBagConstraints.insets = new Insets(5, 5, 0, 0);
/* 392 */     add(this.m_jEquals, gridBagConstraints);
/*     */   }
/*     */ 
/*     */   private class MyKeyNumberListener
/*     */     implements ActionListener
/*     */   {
/*     */     private char m_cCad;
/*     */ 
/*     */     public MyKeyNumberListener(char cCad)
/*     */     {
/* 124 */       this.m_cCad = cCad;
/*     */     }
/*     */ 
/*     */     public void actionPerformed(ActionEvent evt) {
/* 128 */       JNumberEvent oEv = new JNumberEvent(JNumberKeys.this, this.m_cCad);
/*     */ 
/* 131 */       for (Enumeration e = JNumberKeys.this.m_Listeners.elements(); e.hasMoreElements(); ) {
/* 132 */         JNumberEventListener oListener = (JNumberEventListener)e.nextElement();
/* 133 */         oListener.keyPerformed(oEv);
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.view.JNumberKeys
 * JD-Core Version:    0.6.2
 */