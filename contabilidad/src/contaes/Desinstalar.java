/*     */ package contaes;
/*     */ 
/*     */ import java.awt.Container;
/*     */ import java.awt.Frame;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.sql.Connection;
/*     */ import java.sql.DriverManager;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import java.util.LinkedList;
import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.GroupLayout.Alignment;
/*     */ import javax.swing.GroupLayout.ParallelGroup;
/*     */ import javax.swing.GroupLayout.SequentialGroup;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
import javax.swing.LayoutStyle;
/*     */ import javax.swing.LayoutStyle.ComponentPlacement;
/*     */ 
/*     */ public class Desinstalar extends JDialog
/*     */ {
/*     */   private JButton jButton1;
/*     */   private JButton jButton2;
/*     */   private JLabel jLabel1;
/*     */   private JLabel jLabel2;
/*     */   private JLabel jLabel3;
/*     */   private JLabel jLabel4;
/*     */   private JPanel jPanel1;
/*     */ 
/*     */   public Desinstalar(Frame parent, boolean modal)
/*     */   {
/*  31 */     super(parent, modal);
/*  32 */     initComponents();
/*  33 */     this.jButton2.requestFocusInWindow();
/*     */   }
/*     */ 
/*     */   private void desinstalar() {
/*     */     try {
/*  38 */       String g = "contaes";
/*  39 */       Connection con = DriverManager.getConnection("jdbc:mysql://" + Inicio.p.getDireccionIP() + "/", Inicio.p.getUsuario(), Inicio.p.getPassword());
/*  40 */       Statement sentencia = con.createStatement();
/*  41 */       LinkedList listaEmpresas = new LinkedList();
/*  42 */       ResultSet res = sentencia.executeQuery("SHOW DATABASES");
/*  43 */       while (res.next()) {
/*  44 */         String db = res.getString(1);
/*  45 */         if ((db.indexOf(g) != -1) && (db.length() > g.length())) {
/*  46 */           listaEmpresas.add(db.substring(g.length()));
/*     */         }
/*     */       }
/*  49 */       res.close();
/*  50 */       for (String id : (List<String>)listaEmpresas) {
/*  51 */         sentencia.executeUpdate("DROP DATABASE IF EXISTS contaes" + id);
/*     */       }
/*     */ 
/*  56 */       sentencia.executeUpdate("DROP DATABASE IF EXISTS contaes");
/*  57 */       sentencia.executeUpdate("DROP DATABASE IF EXISTS almacen2");
/*     */     }
/*     */     catch (SQLException ex)
/*     */     {
/*  61 */       Logger.getLogger(Desinstalar.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/*  74 */     this.jPanel1 = new JPanel();
/*  75 */     this.jLabel1 = new JLabel();
/*  76 */     this.jLabel2 = new JLabel();
/*  77 */     this.jLabel3 = new JLabel();
/*  78 */     this.jLabel4 = new JLabel();
/*  79 */     this.jButton1 = new JButton();
/*  80 */     this.jButton2 = new JButton();
/*     */ 
/*  82 */     setDefaultCloseOperation(2);
/*  83 */     setTitle("Desinstalar Contaes");
/*     */ 
/*  85 */     this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/*     */ 
/*  87 */     this.jLabel1.setText("ATENCIÓN: Este proceso borrará");
/*     */ 
/*  89 */     this.jLabel2.setText("todas sus bases de datos.");
/*     */ 
/*  91 */     this.jLabel3.setText("¿Está seguro de que desea");
/*     */ 
/*  93 */     this.jLabel4.setText("continuar?");
/*     */ 
/*  95 */     this.jButton1.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/apply.png")));
/*  96 */     this.jButton1.setText("Continuar");
/*  97 */     this.jButton1.setHorizontalTextPosition(2);
/*  98 */     this.jButton1.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 100 */         Desinstalar.this.jButton1ActionPerformed(evt);
/*     */       }
/*     */     });
/* 104 */     this.jButton2.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/cancel22.png")));
/* 105 */     this.jButton2.setText("Cancelar");
/* 106 */     this.jButton2.setHorizontalTextPosition(2);
/* 107 */     this.jButton2.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 109 */         Desinstalar.this.jButton2ActionPerformed(evt);
/*     */       }
/*     */     });
/* 113 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 114 */     this.jPanel1.setLayout(jPanel1Layout);
/* 115 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false).addComponent(this.jLabel1, GroupLayout.Alignment.LEADING, -1, -1, 32767).addComponent(this.jLabel2, GroupLayout.Alignment.LEADING, -1, 217, 32767)).addComponent(this.jLabel3, -1, 217, 32767).addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup().addComponent(this.jButton1).addGap(18, 18, 18).addComponent(this.jButton2)).addComponent(this.jLabel4)).addContainerGap()));
/*     */ 
/* 131 */     jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel2).addGap(18, 18, 18).addComponent(this.jLabel3).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel4, -2, 16, -2).addGap(18, 18, 18).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jButton2, -2, 47, -2).addComponent(this.jButton1)).addContainerGap(-1, 32767)));
/*     */ 
/* 149 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 150 */     getContentPane().setLayout(layout);
/* 151 */     layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -1, -1, 32767).addContainerGap()));
/*     */ 
/* 158 */     layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -1, -1, 32767).addContainerGap()));
/*     */ 
/* 166 */     pack();
/*     */   }
/*     */ 
/*     */   private void jButton1ActionPerformed(ActionEvent evt) {
/* 170 */     desinstalar();
/* 171 */     dispose();
/* 172 */     System.exit(0);
/*     */   }
/*     */ 
/*     */   private void jButton2ActionPerformed(ActionEvent evt) {
/* 176 */     dispose();
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.Desinstalar
 * JD-Core Version:    0.6.2
 */