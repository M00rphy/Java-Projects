/*     */ package contaes.auxiliarTablas;
/*     */ 
/*     */ import java.sql.Connection;
/*     */ import java.sql.DatabaseMetaData;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.ResultSetMetaData;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.swing.table.AbstractTableModel;
/*     */ 
/*     */ public class ScrollableTableModel extends AbstractTableModel
/*     */ {
/*  24 */   ResultSet resultSet = null;
/*  25 */   Connection connection = null;
/*  26 */   List<String> colNames = new ArrayList();
/*  27 */   int rowCount = -1;
/*  28 */   List<Class<?>> colClasses = null;
/*  29 */   Statement stmt = null;
/*     */ 
/*     */   public ScrollableTableModel()
/*     */   {
/*     */   }
/*     */ 
/*     */   public ScrollableTableModel(Connection con, String select)
/*     */   {
/*  45 */     this(con, select, null);
/*     */   }
/*     */ 
/*     */   public ScrollableTableModel(Connection con, String select, List<String> colNames)
/*     */   {
/*  56 */     if (con == null) {
/*  57 */       throw new IllegalArgumentException("The connection passed as parameter is null.");
/*     */     }
/*     */     try
/*     */     {
/*  61 */       if (con.isClosed())
/*  62 */         throw new IllegalArgumentException("The connection passed as parameter is closed.");
/*     */     }
/*     */     catch (SQLException e)
/*     */     {
/*  66 */       throw new ScrollableTableModelException("Error getting connection status.", e);
/*     */     }
/*  68 */     if (!supportsScrollInsensitive(con)) {
/*  69 */       throw new IllegalArgumentException("The connection passed as parameter don't supports insensitive scroll.");
/*     */     }
/*     */ 
/*  72 */     this.connection = con;
/*  73 */     if (select == null) {
/*  74 */       throw new IllegalArgumentException("The query passed as parameter is null.");
/*     */     }
/*     */ 
/*  77 */     if (select.trim().length() == 0) {
/*  78 */       throw new IllegalArgumentException("The query passed as parameter is empty.");
/*     */     }
/*     */ 
/*  81 */     this.stmt = null;
/*  82 */     ResultSet rs = null;
/*     */     try {
/*  84 */       this.stmt = this.connection.createStatement(1005, 1007);
/*     */     }
/*     */     catch (SQLException e) {
/*  87 */       throw new ScrollableTableModelException("Error creating statement", e);
/*     */     }
/*     */     try
/*     */     {
/*  91 */       rs = this.stmt.executeQuery(select);
/*  92 */       this.resultSet = rs;
/*     */     } catch (SQLException e) {
/*  94 */       throw new ScrollableTableModelException("Error executing query", e);
/*     */     }
/*     */ 
/*  97 */     if ((colNames == null) || (colNames.isEmpty())) {
/*  98 */       fillColNames(rs);
/*     */     }
/*     */     else
/*     */     {
/* 102 */       this.colNames = new ArrayList(colNames);
/* 103 */       ResultSetMetaData rsmd = null;
/*     */       try {
/* 105 */         rsmd = this.resultSet.getMetaData();
/*     */       } catch (SQLException e) {
/* 107 */         throw new ScrollableTableModelException("Error getting ResultSetMetadata", e);
/*     */       }
/*     */ 
/* 110 */       int colCount = -1;
/*     */       try {
/* 112 */         colCount = rsmd.getColumnCount();
/*     */       } catch (SQLException e) {
/* 114 */         throw new ScrollableTableModelException("Error getting the column count", e);
/*     */       }
/* 116 */       if (colCount != colNames.size())
/* 117 */         throw new IllegalArgumentException("The colNames parameter contains an invalid number of columns.");
/*     */     }
/*     */   }
/*     */ 
/*     */   public ScrollableTableModel(ResultSet rs)
/*     */   {
/* 128 */     this(rs, null);
/*     */   }
/*     */ 
/*     */   public ScrollableTableModel(ResultSet rs, List<String> colNames)
/*     */   {
/* 139 */     if (rs == null) {
/* 140 */       new IllegalArgumentException("The resultset passed as parameter is null.");
/*     */     }
/* 142 */     Statement s = null;
/*     */     try {
/* 144 */       s = rs.getStatement();
/*     */     } catch (SQLException e) {
/* 146 */       throw new ScrollableTableModelException("Error getting statement from resultset.", e);
/*     */     }
/* 148 */     Connection c = null;
/*     */     try {
/* 150 */       c = s.getConnection();
/*     */     } catch (SQLException e) {
/* 152 */       throw new ScrollableTableModelException("Error getting connection from resultset.", e);
/*     */     }
/* 154 */     if (!supportsScrollInsensitive(c)) {
/* 155 */       throw new IllegalArgumentException("Your connection don't supports insensitive scroll.");
/*     */     }
/*     */ 
/* 159 */     if (colNames != null) {
/* 160 */       ResultSetMetaData rmd = null;
/*     */       try {
/* 162 */         rmd = rs.getMetaData();
/*     */       } catch (SQLException e) {
/* 164 */         throw new ScrollableTableModelException("Error getting ResultSetMetaData.", e);
/*     */       }
/*     */ 
/* 168 */       int colCount = -1;
/*     */       try {
/* 170 */         colCount = rmd.getColumnCount();
/*     */       } catch (SQLException e) {
/* 172 */         throw new ScrollableTableModelException("Error getting the column count.", e);
/*     */       }
/*     */ 
/* 175 */       if (colNames.size() != colCount) {
/* 176 */         throw new IllegalArgumentException("The colNames parameter contains an invalid number of columns.");
/*     */       }
/*     */ 
/* 179 */       this.colNames = new ArrayList(colNames);
/*     */     } else {
/* 181 */       fillColNames(rs);
/*     */     }
/*     */ 
/* 184 */     this.resultSet = rs;
/* 185 */     this.stmt = s;
/*     */   }
/*     */ 
/*     */   public ScrollableTableModel(Statement stmt)
/*     */   {
/* 195 */     this(stmt, null);
/*     */   }
/*     */ 
/*     */   public ScrollableTableModel(Statement stmt, List<String> colNames)
/*     */   {
/* 207 */     if (stmt == null) {
/* 208 */       new IllegalArgumentException("The statement passed as parameter is null.");
/*     */     }
/* 210 */     ResultSet rs = null;
/*     */     try {
/* 212 */       rs = stmt.getResultSet();
/*     */     } catch (SQLException e) {
/* 214 */       throw new ScrollableTableModelException("Error getting resultset from statement.", e);
/*     */     }
/* 216 */     Connection c = null;
/*     */     try {
/* 218 */       c = stmt.getConnection();
/*     */     } catch (SQLException e) {
/* 220 */       throw new ScrollableTableModelException("Error getting connection from resultset.", e);
/*     */     }
/* 222 */     if (!supportsScrollInsensitive(c)) {
/* 223 */       throw new IllegalArgumentException("Your connection don't supports insensitive scroll.");
/*     */     }
/*     */ 
/* 227 */     if (colNames != null) {
/* 228 */       ResultSetMetaData rmd = null;
/*     */       try {
/* 230 */         rmd = rs.getMetaData();
/*     */       } catch (SQLException e) {
/* 232 */         throw new ScrollableTableModelException("Error getting ResultSetMetaData.", e);
/*     */       }
/*     */ 
/* 236 */       int colCount = -1;
/*     */       try {
/* 238 */         colCount = rmd.getColumnCount();
/*     */       } catch (SQLException e) {
/* 240 */         throw new ScrollableTableModelException("Error getting the column count.", e);
/*     */       }
/*     */ 
/* 243 */       if (colNames.size() != colCount) {
/* 244 */         throw new IllegalArgumentException("The colCount parameter contains an invalid number of columns.");
/*     */       }
/*     */ 
/* 247 */       this.colNames = new ArrayList(colNames);
/*     */     } else {
/* 249 */       fillColNames(rs);
/*     */     }
/*     */ 
/* 252 */     this.resultSet = rs;
/* 253 */     this.stmt = stmt;
/*     */   }
/*     */ 
/*     */   private void fillColNames(ResultSet resultSet)
/*     */   {
/* 271 */     if (resultSet == null) {
/* 272 */       throw new IllegalArgumentException("Error filling column names: The ResultSet parameter is null");
/*     */     }
/* 274 */     if (this.colNames == null) {
/* 275 */       ResultSetMetaData rsmd = null;
/*     */       try {
/* 277 */         rsmd = resultSet.getMetaData();
/* 278 */         int colCount = rsmd.getColumnCount();
/* 279 */         if (colCount == 0);
/* 282 */         this.colNames = new ArrayList();
/* 283 */         for (int i = 0; i < colCount; i++) {
/* 284 */           String colLabel = rsmd.getColumnLabel(i + 1);
/* 285 */           this.colNames.add(colLabel);
/*     */         }
/*     */       } catch (SQLException e) {
/* 288 */         e.printStackTrace();
/* 289 */         throw new ScrollableTableModelException("Error getting ResultSetMetadata", e);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public int getColumnCount()
/*     */   {
/* 298 */     return this.colNames.size();
/*     */   }
/*     */ 
/*     */   public int getRowCount()
/*     */   {
/* 305 */     if (this.rowCount == -1) {
/*     */       try {
/* 307 */         this.resultSet.last();
/* 308 */         this.rowCount = this.resultSet.getRow();
/*     */       } catch (SQLException e) {
/* 310 */         throw new ScrollableTableModelException("Error scrolling to latest row", e);
/*     */       }
/*     */     }
/*     */ 
/* 314 */     return this.rowCount;
/*     */   }
/*     */ 
/*     */   public Object getValueAt(int rowIndex, int columnIndex)
/*     */   {
/* 325 */     int rowNdx = rowIndex + 1;
/* 326 */     int colNdx = columnIndex + 1;
/*     */     try {
/* 328 */       this.resultSet.absolute(rowNdx);
/* 329 */       return this.resultSet.getObject(colNdx);
/*     */     } catch (SQLException e) {
/* 331 */       throw new ScrollableTableModelException("Error getting value at " + rowIndex + ", " + columnIndex, e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getColumnName(int column)
/*     */   {
/* 340 */     return (String)this.colNames.get(column);
/*     */   }
/*     */ 
/*     */   public Class<?> getColumnClass(int columnIndex)
/*     */   {
/* 350 */     if (this.colClasses == null) {
/* 351 */       this.colClasses = new ArrayList();
/* 352 */       ResultSetMetaData md = null;
/*     */       try {
/* 354 */         md = this.resultSet.getMetaData();
/* 355 */         int colCount = md.getColumnCount();
/* 356 */         for (int i = 0; i < colCount; i++)
/*     */           try {
/* 358 */             String className = md.getColumnClassName(i + 1);
/* 359 */             Class c = Class.forName(className);
/* 360 */             this.colClasses.add(c);
/*     */           } catch (ClassNotFoundException e) {
/* 362 */             throw new ScrollableTableModelException("Error getting column classes.", e);
/*     */           }
/*     */       }
/*     */       catch (SQLException e) {
/* 366 */         throw new ScrollableTableModelException("Error getting column classes.", e);
/*     */       }
/*     */     }
/* 369 */     Class c = (Class)this.colClasses.get(columnIndex);
/*     */ 
/* 371 */     return c;
/*     */   }
/*     */ 
/*     */   private boolean supportsScrollInsensitive(Connection con)
/*     */   {
/* 381 */     DatabaseMetaData md = null;
/*     */     try {
/* 383 */       md = con.getMetaData();
/*     */     } catch (SQLException e) {
/* 385 */       throw new ScrollableTableModelException("Error getting database metadata.", e);
/*     */     }
/*     */     try {
/* 388 */       return md.supportsResultSetType(1004);
/*     */     } catch (SQLException e) {
/* 390 */       throw new ScrollableTableModelException("Error getting database metadata info.", e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void destroy()
/*     */   {
/* 399 */     if (this.stmt != null)
/*     */       try {
/* 401 */         this.stmt.close();
/*     */       }
/*     */       catch (SQLException e) {
/*     */       }
/* 405 */     this.stmt = null;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.auxiliarTablas.ScrollableTableModel
 * JD-Core Version:    0.6.2
 */