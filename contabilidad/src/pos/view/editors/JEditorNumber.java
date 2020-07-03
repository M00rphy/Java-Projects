/*     */ package pos.view.editors;
/*     */ 
/*     */ import java.awt.Toolkit;
/*     */ import pos.model.BasicException;
/*     */ import pos.model.DoubleUtils;
/*     */ import pos.model.Formats;
/*     */ 
/*     */ public abstract class JEditorNumber extends JEditorAbstract
/*     */ {
/*     */   private static final int NUMBER_ZERONULL = 0;
/*     */   private static final int NUMBER_INT = 1;
/*     */   private static final int NUMBER_DEC = 2;
/*     */   private int m_iNumberStatus;
/*     */   private String m_sNumber;
/*     */   private boolean m_bNegative;
/*     */   private Formats m_fmt;
/*     */ 
/*     */   public JEditorNumber()
/*     */   {
/*  40 */     this.m_fmt = getFormat();
/*  41 */     reset();
/*     */   }
/*     */ 
/*     */   protected abstract Formats getFormat();
/*     */ 
/*     */   public void reset()
/*     */   {
/*  48 */     String sOldText = getText();
/*     */ 
/*  50 */     this.m_sNumber = "";
/*  51 */     this.m_bNegative = false;
/*  52 */     this.m_iNumberStatus = 0;
/*     */ 
/*  54 */     reprintText();
/*     */ 
/*  56 */     firePropertyChange("Text", sOldText, getText());
/*     */   }
/*     */ 
/*     */   public void setDoubleValue(Double dvalue)
/*     */   {
/*  61 */     String sOldText = getText();
/*     */ 
/*  63 */     if (dvalue == null) {
/*  64 */       this.m_sNumber = "";
/*  65 */       this.m_bNegative = false;
/*  66 */       this.m_iNumberStatus = 0;
/*  67 */     } else if (dvalue.doubleValue() >= 0.0D) {
/*  68 */       this.m_sNumber = formatDouble(dvalue);
/*  69 */       this.m_bNegative = false;
/*  70 */       this.m_iNumberStatus = 0;
/*     */     } else {
/*  72 */       this.m_sNumber = formatDouble(Double.valueOf(-dvalue.doubleValue()));
/*  73 */       this.m_bNegative = true;
/*  74 */       this.m_iNumberStatus = 0;
/*     */     }
/*  76 */     reprintText();
/*     */ 
/*  78 */     firePropertyChange("Text", sOldText, getText());
/*     */   }
/*     */ 
/*     */   public Double getDoubleValue()
/*     */   {
/*  83 */     String text = getText();
/*  84 */     if ((text == null) || (text.equals("")))
/*  85 */       return null;
/*     */     try
/*     */     {
/*  88 */       return Double.valueOf(Double.parseDouble(text)); } catch (NumberFormatException e) {
/*     */     }
/*  90 */     return null;
/*     */   }
/*     */ 
/*     */   public void setValueInteger(int ivalue)
/*     */   {
/*  97 */     String sOldText = getText();
/*     */ 
/*  99 */     if (ivalue >= 0) {
/* 100 */       this.m_sNumber = Integer.toString(ivalue);
/* 101 */       this.m_bNegative = false;
/* 102 */       this.m_iNumberStatus = 0;
/*     */     } else {
/* 104 */       this.m_sNumber = Integer.toString(-ivalue);
/* 105 */       this.m_bNegative = true;
/* 106 */       this.m_iNumberStatus = 0;
/*     */     }
/* 108 */     reprintText();
/*     */ 
/* 110 */     firePropertyChange("Text", sOldText, getText());
/*     */   }
/*     */ 
/*     */   public int getValueInteger() throws BasicException {
/*     */     try {
/* 115 */       return Integer.parseInt(getText());
/*     */     } catch (NumberFormatException e) {
/* 117 */       throw new BasicException(e);
/*     */     }
/*     */   }
/*     */ 
/*     */   private String formatDouble(Double value) {
/* 122 */     String sNumber = Double.toString(DoubleUtils.fixDecimals(value));
/* 123 */     if (sNumber.endsWith(".0")) {
/* 124 */       sNumber = sNumber.substring(0, sNumber.length() - 2);
/*     */     }
/* 126 */     return sNumber;
/*     */   }
/*     */ 
/*     */   protected String getEditMode() {
/* 130 */     return "-1.23";
/*     */   }
/*     */ 
/*     */   public String getText() {
/* 134 */     return new StringBuilder().append(this.m_bNegative ? "-" : "").append(this.m_sNumber).toString();
/*     */   }
/*     */ 
/*     */   protected int getAlignment() {
/* 138 */     return 4;
/*     */   }
/*     */ 
/*     */   protected String getTextEdit() {
/* 142 */     return getText();
/*     */   }
/*     */ 
/*     */   protected String getTextFormat() throws BasicException {
/* 146 */     return this.m_fmt.formatValue(getDoubleValue());
/*     */   }
/*     */ 
/*     */   protected void typeCharInternal(char cTrans) {
/* 150 */     transChar(cTrans);
/*     */   }
/*     */ 
/*     */   protected void transCharInternal(char cTrans)
/*     */   {
/* 155 */     String sOldText = getText();
/*     */ 
/* 157 */     if (cTrans == '') {
/* 158 */       reset();
/* 159 */     } else if (cTrans == '-') {
/* 160 */       this.m_bNegative = (!this.m_bNegative);
/* 161 */     } else if ((cTrans == '0') && (this.m_iNumberStatus == 0))
/*     */     {
/* 164 */       this.m_sNumber = "0";
/* 165 */     } else if (((cTrans == '1') || (cTrans == '2') || (cTrans == '3') || (cTrans == '4') || (cTrans == '5') || (cTrans == '6') || (cTrans == '7') || (cTrans == '8') || (cTrans == '9')) && (this.m_iNumberStatus == 0))
/*     */     {
/* 167 */       this.m_iNumberStatus = 1;
/* 168 */       this.m_sNumber = Character.toString(cTrans);
/* 169 */     } else if ((cTrans == '.') && (this.m_iNumberStatus == 0)) {
/* 170 */       this.m_iNumberStatus = 2;
/* 171 */       this.m_sNumber = "0.";
/*     */     }
/* 173 */     else if (((cTrans == '0') || (cTrans == '1') || (cTrans == '2') || (cTrans == '3') || (cTrans == '4') || (cTrans == '5') || (cTrans == '6') || (cTrans == '7') || (cTrans == '8') || (cTrans == '9')) && (this.m_iNumberStatus == 1))
/*     */     {
/* 176 */       this.m_sNumber = new StringBuilder().append(this.m_sNumber).append(cTrans).toString();
/* 177 */     } else if ((cTrans == '.') && (this.m_iNumberStatus == 1)) {
/* 178 */       this.m_iNumberStatus = 2;
/* 179 */       this.m_sNumber = new StringBuilder().append(this.m_sNumber).append('.').toString();
/*     */     }
/* 181 */     else if (((cTrans == '0') || (cTrans == '1') || (cTrans == '2') || (cTrans == '3') || (cTrans == '4') || (cTrans == '5') || (cTrans == '6') || (cTrans == '7') || (cTrans == '8') || (cTrans == '9')) && (this.m_iNumberStatus == 2))
/*     */     {
/* 183 */       this.m_sNumber = new StringBuilder().append(this.m_sNumber).append(cTrans).toString();
/*     */     }
/*     */     else {
/* 186 */       Toolkit.getDefaultToolkit().beep();
/*     */     }
/*     */ 
/* 189 */     firePropertyChange("Text", sOldText, getText());
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.view.editors.JEditorNumber
 * JD-Core Version:    0.6.2
 */