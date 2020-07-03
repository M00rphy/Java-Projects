/*     */ package contaes.manejoDatos.funciones;
/*     */ 
/*     */ import contaes.manejoDatos.TipoCuenta;
/*     */ import contaes.manejoDatos.auxiliar.MySQLConection;
/*     */ import java.io.PrintStream;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.Enumeration;
/*     */ import javax.swing.JTree;
/*     */ import javax.swing.tree.DefaultMutableTreeNode;
/*     */ 
/*     */ public class BuildPlanTree
/*     */ {
/*  16 */   private JTree arbol = null;
/*  17 */   private TipoCuenta[] nodo = null;
/*     */   private DefaultMutableTreeNode root;
/*     */ 
/*     */   public BuildPlanTree()
/*     */   {
/*  21 */     iniciar();
/*     */   }
/*     */ 
/*     */   private void iniciar()
/*     */   {
/*  28 */     MySQLConection con = new MySQLConection();
/*     */ 
/*  30 */     this.root = new DefaultMutableTreeNode("Plan Contable");
/*     */     try {
/*  32 */       ResultSet res = con.getRes("SELECT COUNT(*) FROM Plan_contable");
/*  33 */       res.next();
/*  34 */       this.nodo = new TipoCuenta[res.getInt(1)];
/*  35 */       int i = 0;
/*  36 */       res = con.getRes("SELECT * FROM Plan_contable ORDER BY codigo");
/*  37 */       while (res.next()) {
/*  38 */         this.nodo[i] = new TipoCuenta(res.getInt(1), res.getString(2), res.getString(3));
/*  39 */         i++;
/*     */       }
/*  41 */       res.close();
/*     */     } catch (SQLException e) {
/*  43 */       e.printStackTrace();
/*     */     }
/*  45 */     con.cierraConexion();
/*  46 */     for (int i = 0; i < this.nodo.length; i++) {
/*  47 */       addNodo(this.nodo[i]);
/*     */     }
/*  49 */     this.arbol = new JTree(this.root);
/*     */   }
/*     */ 
/*     */   private void addNodo(TipoCuenta nodo) {
/*  53 */     int idPadre = nodo.getParent();
/*  54 */     if (idPadre == 0) { this.root.add(new DefaultMutableTreeNode(nodo));
/*     */     } else {
/*  56 */       TipoCuenta padre = buscarPadre(idPadre);
/*  57 */       if (padre != null) {
/*  58 */         DefaultMutableTreeNode nodoPadre = buscarNodo(padre);
/*  59 */         if (nodoPadre != null)
/*  60 */           nodoPadre.add(new DefaultMutableTreeNode(nodo));
/*     */       }
/*     */       else {
/*  63 */         System.out.println("Nodo padre de: " + nodo + " no encontrado");
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private DefaultMutableTreeNode buscarNodo(TipoCuenta nodo)
/*     */   {
/*  70 */     DefaultMutableTreeNode node = null;
/*     */     Enumeration lista;
/*     */    
/*  75 */     if (nodo.getParent() == 0)
/*  76 */       lista = this.root.breadthFirstEnumeration();
/*  77 */     else lista = buscarNodo(buscarPadre(nodo.getParent())).breadthFirstEnumeration();
/*     */ 
/*  80 */     while (lista.hasMoreElements())
/*     */     {
/*  83 */       node = (DefaultMutableTreeNode)lista.nextElement();
/*     */ 
/*  86 */       if (nodo.toString().equals(node.getUserObject().toString()))
/*     */       {
/*  89 */         return node;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  94 */     return null;
/*     */   }
/*     */ 
/*     */   private TipoCuenta buscarPadre(int idPadre) {
/*  98 */     int y = 0;
/*     */ 
/* 100 */     for (int x = 0; x < this.nodo.length; x++) {
/* 101 */       if (this.nodo[x].getCodigo() == idPadre) {
/* 102 */         y = x;
/* 103 */         break;
/*     */       }
/*     */     }
/* 106 */     return this.nodo[y];
/*     */   }
/*     */ 
/*     */   public JTree getArbol() {
/* 110 */     return this.arbol;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.funciones.BuildPlanTree
 * JD-Core Version:    0.6.2
 */