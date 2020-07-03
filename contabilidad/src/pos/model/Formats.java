/*     */ package pos.model;
/*     */ 
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.text.DateFormat;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.NumberFormat;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ 
/*     */ public abstract class Formats
/*     */ {
/*  27 */   public static final Formats NULL = new FormatsNULL();
/*  28 */   public static final Formats INT = new FormatsINT();
/*  29 */   public static final Formats STRING = new FormatsSTRING();
/*  30 */   public static final Formats DOUBLE = new FormatsDOUBLE();
/*  31 */   public static final Formats CURRENCY = new FormatsCURRENCY();
/*  32 */   public static final Formats PERCENT = new FormatsPERCENT();
/*  33 */   public static final Formats BOOLEAN = new FormatsBOOLEAN();
/*  34 */   public static final Formats TIMESTAMP = new FormatsTIMESTAMP();
/*  35 */   public static final Formats DATE = new FormatsDATE();
/*  36 */   public static final Formats TIME = new FormatsTIME();
/*  37 */   public static final Formats BYTEA = new FormatsBYTEA();
/*     */ 
/*  39 */   private static NumberFormat m_integerformat = NumberFormat.getIntegerInstance();
/*  40 */   private static NumberFormat m_doubleformat = NumberFormat.getNumberInstance();
/*  41 */   private static NumberFormat m_currencyformat = NumberFormat.getCurrencyInstance();
/*  42 */   private static NumberFormat m_percentformat = new DecimalFormat("#,##0.##%");
/*     */ 
/*  44 */   private static DateFormat m_dateformat = DateFormat.getDateInstance();
/*  45 */   private static DateFormat m_timeformat = DateFormat.getTimeInstance();
/*  46 */   private static DateFormat m_datetimeformat = DateFormat.getDateTimeInstance();
/*     */ 
/*     */   public static int getCurrencyDecimals()
/*     */   {
/*  55 */     return m_currencyformat.getMaximumFractionDigits();
/*     */   }
/*     */ 
/*     */   public String formatValue(Object value) {
/*  59 */     if (value == null) {
/*  60 */       return "";
/*     */     }
/*  62 */     return formatValueInt(value);
/*     */   }
/*     */ 
/*     */   public Object parseValue(String value, Object defvalue) throws BasicException
/*     */   {
/*  67 */     if ((value == null) || ("".equals(value)))
/*  68 */       return defvalue;
/*     */     try
/*     */     {
/*  71 */       return parseValueInt(value);
/*     */     } catch (ParseException e) {
/*  73 */       throw new BasicException(e.getMessage(), e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public Object parseValue(String value) throws BasicException
/*     */   {
/*  79 */     return parseValue(value, null);
/*     */   }
/*     */ 
/*     */   public static void setIntegerPattern(String pattern) {
/*  83 */     if ((pattern == null) || (pattern.equals("")))
/*  84 */       m_integerformat = NumberFormat.getIntegerInstance();
/*     */     else
/*  86 */       m_integerformat = new DecimalFormat(pattern);
/*     */   }
/*     */ 
/*     */   public static void setDoublePattern(String pattern)
/*     */   {
/*  91 */     if ((pattern == null) || (pattern.equals("")))
/*  92 */       m_doubleformat = NumberFormat.getNumberInstance();
/*     */     else
/*  94 */       m_doubleformat = new DecimalFormat(pattern);
/*     */   }
/*     */ 
/*     */   public static void setCurrencyPattern(String pattern)
/*     */   {
/*  99 */     if ((pattern == null) || (pattern.equals("")))
/* 100 */       m_currencyformat = NumberFormat.getCurrencyInstance();
/*     */     else
/* 102 */       m_currencyformat = new DecimalFormat(pattern);
/*     */   }
/*     */ 
/*     */   public static void setPercentPattern(String pattern)
/*     */   {
/* 107 */     if ((pattern == null) || (pattern.equals("")))
/* 108 */       m_percentformat = new DecimalFormat("#,##0.##%");
/*     */     else
/* 110 */       m_percentformat = new DecimalFormat(pattern);
/*     */   }
/*     */ 
/*     */   public static void setDatePattern(String pattern)
/*     */   {
/* 115 */     if ((pattern == null) || (pattern.equals("")))
/* 116 */       m_dateformat = DateFormat.getDateInstance();
/*     */     else
/* 118 */       m_dateformat = new SimpleDateFormat(pattern);
/*     */   }
/*     */ 
/*     */   public static void setTimePattern(String pattern)
/*     */   {
/* 123 */     if ((pattern == null) || (pattern.equals("")))
/* 124 */       m_timeformat = DateFormat.getTimeInstance();
/*     */     else
/* 126 */       m_timeformat = new SimpleDateFormat(pattern);
/*     */   }
/*     */ 
/*     */   public static void setDateTimePattern(String pattern)
/*     */   {
/* 131 */     if ((pattern == null) || (pattern.equals("")))
/* 132 */       m_datetimeformat = DateFormat.getDateTimeInstance();
/*     */     else
/* 134 */       m_datetimeformat = new SimpleDateFormat(pattern);
/*     */   }
/*     */ 
/*     */   protected abstract String formatValueInt(Object paramObject);
/*     */ 
/*     */   protected abstract Object parseValueInt(String paramString)
/*     */     throws ParseException;
/*     */ 
/*     */   public abstract int getAlignment();
/*     */ 
/*     */   private static final class FormatsBYTEA extends Formats
/*     */   {
/*     */     protected String formatValueInt(Object value)
/*     */     {
/*     */       try
/*     */       {
/* 270 */         return new String((byte[])value, "UTF-8"); } catch (UnsupportedEncodingException eu) {
/*     */       }
/* 272 */       return "";
/*     */     }
/*     */ 
/*     */     protected Object parseValueInt(String value) throws ParseException {
/*     */       try {
/* 277 */         return value.getBytes("UTF-8"); } catch (UnsupportedEncodingException eu) {
/*     */       }
/* 279 */       return new byte[0];
/*     */     }
/*     */ 
/*     */     public int getAlignment() {
/* 283 */       return 10;
/*     */     }
/*     */   }
/*     */ 
/*     */   private static final class FormatsTIME extends Formats
/*     */   {
/*     */     protected String formatValueInt(Object value)
/*     */     {
/* 258 */       return Formats.m_timeformat.format((Date)value);
/*     */     }
/*     */     protected Object parseValueInt(String value) throws ParseException {
/* 261 */       return Formats.m_timeformat.parse(value);
/*     */     }
/*     */     public int getAlignment() {
/* 264 */       return 0;
/*     */     }
/*     */   }
/*     */ 
/*     */   private static final class FormatsDATE extends Formats
/*     */   {
/*     */     protected String formatValueInt(Object value)
/*     */     {
/* 247 */       return Formats.m_dateformat.format((Date)value);
/*     */     }
/*     */     protected Object parseValueInt(String value) throws ParseException {
/* 250 */       return Formats.m_dateformat.parse(value);
/*     */     }
/*     */     public int getAlignment() {
/* 253 */       return 0;
/*     */     }
/*     */   }
/*     */ 
/*     */   private static final class FormatsTIMESTAMP extends Formats
/*     */   {
/*     */     protected String formatValueInt(Object value)
/*     */     {
/* 231 */       return Formats.m_datetimeformat.format((Date)value);
/*     */     }
/*     */     protected Object parseValueInt(String value) throws ParseException {
/*     */       try {
/* 235 */         return Formats.m_datetimeformat.parse(value);
/*     */       } catch (ParseException e) {
/*     */       }
/* 238 */       return Formats.m_dateformat.parse(value);
/*     */     }
/*     */ 
/*     */     public int getAlignment() {
/* 242 */       return 0;
/*     */     }
/*     */   }
/*     */ 
/*     */   private static final class FormatsBOOLEAN extends Formats
/*     */   {
/*     */     protected String formatValueInt(Object value)
/*     */     {
/* 220 */       return ((Boolean)value).toString();
/*     */     }
/*     */     protected Object parseValueInt(String value) throws ParseException {
/* 223 */       return Boolean.valueOf(value);
/*     */     }
/*     */     public int getAlignment() {
/* 226 */       return 0;
/*     */     }
/*     */   }
/*     */ 
/*     */   private static final class FormatsCURRENCY extends Formats
/*     */   {
/*     */     protected String formatValueInt(Object value)
/*     */     {
/* 204 */       return Formats.m_currencyformat.format(DoubleUtils.fixDecimals((Number)value));
/*     */     }
/*     */     protected Object parseValueInt(String value) throws ParseException {
/*     */       try {
/* 208 */         return new Double(Formats.m_currencyformat.parse(value).doubleValue());
/*     */       } catch (ParseException e) {
/*     */       }
/* 211 */       return new Double(Formats.m_doubleformat.parse(value).doubleValue());
/*     */     }
/*     */ 
/*     */     public int getAlignment() {
/* 215 */       return 4;
/*     */     }
/*     */   }
/*     */ 
/*     */   private static final class FormatsPERCENT extends Formats
/*     */   {
/*     */     protected String formatValueInt(Object value)
/*     */     {
/* 188 */       return Formats.m_percentformat.format(DoubleUtils.fixDecimals((Number)value));
/*     */     }
/*     */     protected Object parseValueInt(String value) throws ParseException {
/*     */       try {
/* 192 */         return new Double(Formats.m_percentformat.parse(value).doubleValue());
/*     */       } catch (ParseException e) {
/*     */       }
/* 195 */       return new Double(Formats.m_doubleformat.parse(value).doubleValue() / 100.0D);
/*     */     }
/*     */ 
/*     */     public int getAlignment() {
/* 199 */       return 4;
/*     */     }
/*     */   }
/*     */ 
/*     */   private static final class FormatsDOUBLE extends Formats
/*     */   {
/*     */     protected String formatValueInt(Object value)
/*     */     {
/* 177 */       return Formats.m_doubleformat.format(DoubleUtils.fixDecimals((Number)value));
/*     */     }
/*     */     protected Object parseValueInt(String value) throws ParseException {
/* 180 */       return new Double(Formats.m_doubleformat.parse(value).doubleValue());
/*     */     }
/*     */     public int getAlignment() {
/* 183 */       return 4;
/*     */     }
/*     */   }
/*     */ 
/*     */   private static final class FormatsSTRING extends Formats
/*     */   {
/*     */     protected String formatValueInt(Object value)
/*     */     {
/* 166 */       return (String)value;
/*     */     }
/*     */     protected Object parseValueInt(String value) throws ParseException {
/* 169 */       return value;
/*     */     }
/*     */     public int getAlignment() {
/* 172 */       return 2;
/*     */     }
/*     */   }
/*     */ 
/*     */   private static final class FormatsINT extends Formats
/*     */   {
/*     */     protected String formatValueInt(Object value)
/*     */     {
/* 155 */       return Formats.m_integerformat.format(((Number)value).longValue());
/*     */     }
/*     */     protected Object parseValueInt(String value) throws ParseException {
/* 158 */       return new Integer(Formats.m_integerformat.parse(value).intValue());
/*     */     }
/*     */     public int getAlignment() {
/* 161 */       return 4;
/*     */     }
/*     */   }
/*     */ 
/*     */   private static final class FormatsNULL extends Formats
/*     */   {
/*     */     protected String formatValueInt(Object value)
/*     */     {
/* 144 */       return null;
/*     */     }
/*     */     protected Object parseValueInt(String value) throws ParseException {
/* 147 */       return null;
/*     */     }
/*     */     public int getAlignment() {
/* 150 */       return 2;
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.model.Formats
 * JD-Core Version:    0.6.2
 */