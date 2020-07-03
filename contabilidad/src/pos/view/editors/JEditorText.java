/*     */ package pos.view.editors;
/*     */ 
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.Timer;
/*     */ import pos.model.BasicException;
/*     */ 
/*     */ public abstract class JEditorText extends JEditorAbstract
/*     */ {
/*     */   protected String m_svalue;
/*     */   public static final int MODE_Abc1 = 0;
/*     */   public static final int MODE_abc1 = 1;
/*     */   public static final int MODE_ABC1 = 2;
/*     */   public static final int MODE_123 = 3;
/*     */   public int m_iMode;
/*     */   protected int m_iTicks;
/*     */   protected char m_cLastChar;
/*     */   protected long m_lcount;
/*     */   private Timer m_jtimer;
/*  43 */   private static final char[] CHAR_abc1_1 = { '.', '?', '!', ',', '1', ';', ':' };
/*  44 */   private static final char[] CHAR_abc1_2 = { 'a', 'b', 'c', '2', ' ' };
/*  45 */   private static final char[] CHAR_abc1_3 = { 'd', 'e', 'f', '3', '‚' };
/*  46 */   private static final char[] CHAR_abc1_4 = { 'g', 'h', 'i', '4', '¡' };
/*  47 */   private static final char[] CHAR_abc1_5 = { 'j', 'k', 'l', '5' };
/*  48 */   private static final char[] CHAR_abc1_6 = { 'm', 'n', 'o', '6', '¤', '¢' };
/*  49 */   private static final char[] CHAR_abc1_7 = { 'p', 'q', 'r', 's', '7' };
/*  50 */   private static final char[] CHAR_abc1_8 = { 't', 'u', 'v', '8', '£', 65533 };
/*  51 */   private static final char[] CHAR_abc1_9 = { 'w', 'x', 'y', 'z', '9' };
/*  52 */   private static final char[] CHAR_abc1_0 = { ' ', '0' };
/*     */ 
/*  54 */   private static final char[] CHAR_ABC1_1 = { '.', '?', '!', ',', '1', ';', ':' };
/*  55 */   private static final char[] CHAR_ABC1_2 = { 'A', 'B', 'C', '2', 'µ' };
/*  56 */   private static final char[] CHAR_ABC1_3 = { 'D', 'E', 'F', '3', 'É' };
/*  57 */   private static final char[] CHAR_ABC1_4 = { 'G', 'H', 'I', '4', 'Í' };
/*  58 */   private static final char[] CHAR_ABC1_5 = { 'J', 'K', 'L', '5' };
/*  59 */   private static final char[] CHAR_ABC1_6 = { 'M', 'N', 'O', '6', 'Ñ', 'Ó' };
/*  60 */   private static final char[] CHAR_ABC1_7 = { 'P', 'Q', 'R', 'S', '7' };
/*  61 */   private static final char[] CHAR_ABC1_8 = { 'T', 'U', 'V', '8', 'Ú', 'Ü' };
/*  62 */   private static final char[] CHAR_ABC1_9 = { 'W', 'X', 'Y', 'Z', '9' };
/*  63 */   private static final char[] CHAR_ABC1_0 = { ' ', '0' };
/*     */ 
/*     */   public JEditorText()
/*     */   {
/*  67 */     this.m_svalue = null;
/*     */ 
/*  69 */     this.m_iTicks = 0;
/*  70 */     this.m_cLastChar = '\000';
/*  71 */     this.m_jtimer = new Timer(1000, new TimerAction());
/*  72 */     this.m_lcount = 0L;
/*  73 */     this.m_iMode = getStartMode();
/*  74 */     this.m_jtimer.start();
/*     */   }
/*     */ 
/*     */   protected abstract int getStartMode();
/*     */ 
/*     */   public final void reset()
/*     */   {
/*  82 */     String sOldText = getText();
/*     */ 
/*  85 */     this.m_iMode = getStartMode();
/*  86 */     this.m_svalue = null;
/*  87 */     this.m_iTicks = 0;
/*  88 */     this.m_cLastChar = '\000';
/*     */ 
/*  90 */     reprintText();
/*     */ 
/*  92 */     firePropertyChange("Text", sOldText, getText());
/*     */   }
/*     */ 
/*     */   public final void setText(String sText)
/*     */   {
/*  97 */     String sOldText = getText();
/*     */ 
/*  99 */     this.m_svalue = sText;
/* 100 */     this.m_iTicks = 0;
/* 101 */     this.m_cLastChar = '\000';
/*     */ 
/* 103 */     reprintText();
/*     */ 
/* 105 */     firePropertyChange("Text", sOldText, getText());
/*     */   }
/*     */ 
/*     */   public final void setEditModeEnum(int iMode)
/*     */   {
/* 110 */     this.m_iMode = iMode;
/* 111 */     this.m_iTicks = 0;
/* 112 */     this.m_cLastChar = '\000';
/*     */ 
/* 114 */     reprintText();
/*     */   }
/*     */ 
/*     */   public final String getText() {
/* 118 */     if (this.m_cLastChar == 0) {
/* 119 */       return this.m_svalue;
/*     */     }
/* 121 */     return appendChar2Value(getKeyChar());
/*     */   }
/*     */ 
/*     */   protected final int getAlignment()
/*     */   {
/* 126 */     return 2;
/*     */   }
/*     */ 
/*     */   protected final String getEditMode() {
/* 130 */     switch (this.m_iMode) { case 0:
/* 131 */       return "Abc1";
/*     */     case 1:
/* 132 */       return "abc1";
/*     */     case 2:
/* 133 */       return "ABC1";
/*     */     case 3:
/* 134 */       return "123"; }
/* 135 */     return null;
/*     */   }
/*     */ 
/*     */   protected String getTextEdit()
/*     */   {
/* 141 */     StringBuffer s = new StringBuffer();
/* 142 */     s.append("<html>");
/* 143 */     if (this.m_svalue != null) {
/* 144 */       s.append(this.m_svalue);
/*     */     }
/* 146 */     if (this.m_cLastChar != 0) {
/* 147 */       s.append("<font color=\"#a0a0a0\">");
/* 148 */       s.append(getKeyChar());
/* 149 */       s.append("</font>");
/*     */     }
/* 151 */     s.append("<font color=\"#a0a0a0\">_</font>");
/*     */ 
/* 153 */     return s.toString();
/*     */   }
/*     */ 
/*     */   protected String getTextFormat() throws BasicException {
/* 157 */     return "<html>" + this.m_svalue;
/*     */   }
/*     */ 
/*     */   protected void typeCharInternal(char c)
/*     */   {
/* 164 */     String sOldText = getText();
/*     */ 
/* 166 */     if (c == '\b') {
/* 167 */       if (this.m_cLastChar == 0)
/*     */       {
/* 169 */         if ((this.m_svalue != null) && (this.m_svalue.length() > 0))
/* 170 */           this.m_svalue = this.m_svalue.substring(0, this.m_svalue.length() - 1);
/*     */       }
/*     */       else
/*     */       {
/* 174 */         this.m_iTicks = 0;
/* 175 */         this.m_cLastChar = '\000';
/*     */       }
/* 177 */     } else if (c == '')
/*     */     {
/* 179 */       this.m_iMode = getStartMode();
/* 180 */       this.m_svalue = null;
/* 181 */       this.m_iTicks = 0;
/* 182 */       this.m_cLastChar = '\000';
/* 183 */     } else if (c >= ' ') {
/* 184 */       if (this.m_cLastChar != 0) {
/* 185 */         char ckey = getKeyChar();
/* 186 */         this.m_svalue = appendChar2Value(ckey);
/* 187 */         acceptKeyChar(ckey);
/*     */       }
/* 189 */       this.m_iTicks = 0;
/* 190 */       this.m_cLastChar = '\000';
/* 191 */       this.m_svalue = appendChar2Value(c);
/*     */     }
/*     */ 
/* 194 */     this.m_jtimer.restart();
/*     */ 
/* 196 */     firePropertyChange("Text", sOldText, getText());
/*     */   }
/*     */ 
/*     */   protected void transCharInternal(char c)
/*     */   {
/* 201 */     String sOldText = getText();
/*     */ 
/* 203 */     if (c == '-') {
/* 204 */       if (this.m_cLastChar == 0)
/*     */       {
/* 206 */         if ((this.m_svalue != null) && (this.m_svalue.length() > 0))
/* 207 */           this.m_svalue = this.m_svalue.substring(0, this.m_svalue.length() - 1);
/*     */       }
/*     */       else
/*     */       {
/* 211 */         this.m_iTicks = 0;
/* 212 */         this.m_cLastChar = '\000';
/*     */       }
/* 214 */     } else if (c == '')
/*     */     {
/* 216 */       this.m_iMode = getStartMode();
/* 217 */       this.m_svalue = null;
/* 218 */       this.m_iTicks = 0;
/* 219 */       this.m_cLastChar = '\000';
/* 220 */     } else if (c == '.') {
/* 221 */       if (this.m_cLastChar != 0) {
/* 222 */         this.m_svalue = appendChar2Value(getKeyChar());
/*     */       }
/* 224 */       this.m_iTicks = 0;
/* 225 */       this.m_cLastChar = '\000';
/* 226 */       this.m_iMode = ((this.m_iMode + 1) % 4);
/* 227 */     } else if ((c == '1') || (c == '2') || (c == '3') || (c == '4') || (c == '5') || (c == '6') || (c == '7') || (c == '8') || (c == '9') || (c == '0')) {
/* 228 */       if (this.m_iMode == 3) {
/* 229 */         this.m_svalue = appendChar2Value(c);
/* 230 */       } else if (c == this.m_cLastChar) {
/* 231 */         this.m_iTicks += 1;
/*     */       } else {
/* 233 */         if (this.m_cLastChar != 0) {
/* 234 */           char ckey = getKeyChar();
/* 235 */           this.m_svalue = appendChar2Value(ckey);
/* 236 */           acceptKeyChar(ckey);
/*     */         }
/* 238 */         this.m_iTicks = 0;
/* 239 */         this.m_cLastChar = c;
/*     */       }
/*     */     }
/*     */ 
/* 243 */     this.m_jtimer.restart();
/*     */ 
/* 245 */     firePropertyChange("Text", sOldText, getText());
/*     */   }
/*     */ 
/*     */   private void acceptKeyChar(char c) {
/* 249 */     if ((this.m_iMode == 0) && (c != ' '))
/* 250 */       this.m_iMode = 1;
/* 251 */     else if ((this.m_iMode == 1) && (c == '.'))
/* 252 */       this.m_iMode = 0;
/*     */   }
/*     */ 
/*     */   protected char getKeyChar()
/*     */   {
/* 258 */     char[] clist = null;
/* 259 */     switch (this.m_iMode) {
/*     */     case 1:
/* 261 */       switch (this.m_cLastChar) { case '1':
/* 262 */         clist = CHAR_abc1_1; break;
/*     */       case '2':
/* 263 */         clist = CHAR_abc1_2; break;
/*     */       case '3':
/* 264 */         clist = CHAR_abc1_3; break;
/*     */       case '4':
/* 265 */         clist = CHAR_abc1_4; break;
/*     */       case '5':
/* 266 */         clist = CHAR_abc1_5; break;
/*     */       case '6':
/* 267 */         clist = CHAR_abc1_6; break;
/*     */       case '7':
/* 268 */         clist = CHAR_abc1_7; break;
/*     */       case '8':
/* 269 */         clist = CHAR_abc1_8; break;
/*     */       case '9':
/* 270 */         clist = CHAR_abc1_9; break;
/*     */       case '0':
/* 271 */         clist = CHAR_abc1_0;
/*     */       }
/* 273 */       break;
/*     */     case 0:
/*     */     case 2:
/* 276 */       switch (this.m_cLastChar) { case '1':
/* 277 */         clist = CHAR_ABC1_1; break;
/*     */       case '2':
/* 278 */         clist = CHAR_ABC1_2; break;
/*     */       case '3':
/* 279 */         clist = CHAR_ABC1_3; break;
/*     */       case '4':
/* 280 */         clist = CHAR_ABC1_4; break;
/*     */       case '5':
/* 281 */         clist = CHAR_ABC1_5; break;
/*     */       case '6':
/* 282 */         clist = CHAR_ABC1_6; break;
/*     */       case '7':
/* 283 */         clist = CHAR_ABC1_7; break;
/*     */       case '8':
/* 284 */         clist = CHAR_ABC1_8; break;
/*     */       case '9':
/* 285 */         clist = CHAR_ABC1_9; break;
/*     */       case '0':
/* 286 */         clist = CHAR_ABC1_0;
/*     */       }
/*     */       break;
/*     */     }
/*     */ 
/* 291 */     if (clist == null) {
/* 292 */       return this.m_cLastChar;
/*     */     }
/* 294 */     return clist[(this.m_iTicks % clist.length)];
/*     */   }
/*     */ 
/*     */   private String appendChar2Value(char c)
/*     */   {
/* 314 */     StringBuffer s = new StringBuffer();
/* 315 */     if (this.m_svalue != null) {
/* 316 */       s.append(this.m_svalue);
/*     */     }
/* 318 */     s.append(c);
/* 319 */     return s.toString();
/*     */   }
/*     */ 
/*     */   private class TimerAction
/*     */     implements ActionListener
/*     */   {
/*     */     private TimerAction()
/*     */     {
/*     */     }
/*     */ 
/*     */     public void actionPerformed(ActionEvent evt)
/*     */     {
/* 300 */       if (JEditorText.this.m_cLastChar != 0)
/*     */       {
/* 302 */         char ckey = JEditorText.this.getKeyChar();
/* 303 */         JEditorText.this.m_svalue = JEditorText.this.appendChar2Value(ckey);
/* 304 */         JEditorText.this.acceptKeyChar(ckey);
/* 305 */         JEditorText.this.m_iTicks = 0;
/* 306 */         JEditorText.this.m_cLastChar = '\000';
/* 307 */         JEditorText.this.m_jtimer.restart();
/* 308 */         JEditorText.this.reprintText();
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.view.editors.JEditorText
 * JD-Core Version:    0.6.2
 */