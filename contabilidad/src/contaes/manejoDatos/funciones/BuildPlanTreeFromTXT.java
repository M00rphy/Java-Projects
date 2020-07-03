/*     */ package contaes.manejoDatos.funciones;
/*     */ 
/*     */ import contaes.manejoDatos.TipoCuenta;
/*     */ import contaes.manejoDatos.auxiliar.LeerFichero;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Enumeration;
/*     */ import java.util.LinkedList;
/*     */ import java.util.Properties;
/*     */ import javax.swing.JTree;
/*     */ import javax.swing.tree.DefaultMutableTreeNode;
/*     */ 
/*     */ public class BuildPlanTreeFromTXT
/*     */ {
/*  16 */   private JTree arbol = null;
/*     */ 
/*  18 */   private LinkedList<TipoCuenta> nodoE = new LinkedList();
/*     */   private DefaultMutableTreeNode root;
/*     */ 
/*     */   public BuildPlanTreeFromTXT()
/*     */   {
/*  22 */     iniciar();
/*     */   }
/*     */ 
/*     */   private void iniciar()
/*     */   {
/*  29 */     Properties sistema = System.getProperties();
/*  30 */     String sisOp = sistema.getProperty("os.name");
/*  31 */     String archivoPGC = sisOp.substring(0, 3).equals("Mac") ? "pgc08MAC.txt" : "pgc08.txt";
/*  32 */     this.nodoE.clear();
/*  33 */     this.root = new DefaultMutableTreeNode("Plan Contable");
/*     */     try
/*     */     {
/*  36 */       LeerFichero pgc = new LeerFichero("configdir/" + archivoPGC);
/*  37 */       int numeroCuentas = Integer.parseInt(pgc.leer());
/*     */ 
/*  39 */       for (int x = 0; x < numeroCuentas; x++) {
/*  40 */         String linea = pgc.leer();
/*  41 */         int codigo = Integer.parseInt(linea.substring(0, linea.indexOf(";")));
/*  42 */         String nombre = linea.substring(linea.indexOf(";") + 1, linea.lastIndexOf(";"));
/*  43 */         String codBal = linea.substring(linea.lastIndexOf(";") + 1);
/*  44 */         this.nodoE.add(new TipoCuenta(codigo, nombre, codBal));
/*     */ 
/*  46 */         addNodo((TipoCuenta)this.nodoE.getLast());
/*     */ 
/*  48 */         if (pgc.eof()) break;
/*     */       }
/*     */     } catch (IOException e) {
/*  51 */       e.printStackTrace();
/*     */     }
/*  53 */     this.arbol = new JTree(this.root);
/*     */   }
/*     */ 
/*     */   private void addNodo(TipoCuenta nodo) {
/*  57 */     int idPadre = nodo.getParent();
/*  58 */     if (idPadre == 0) { this.root.add(new DefaultMutableTreeNode(nodo));
/*     */     } else {
/*  60 */       TipoCuenta padre = buscarPadre(idPadre);
/*  61 */       if (padre != null) {
/*  62 */         DefaultMutableTreeNode nodoPadre = buscarNodo(padre);
/*  63 */         if (nodoPadre != null)
/*  64 */           nodoPadre.add(new DefaultMutableTreeNode(nodo));
/*     */       }
/*     */       else {
/*  67 */         System.out.println("Nodo padre de: " + nodo + " no encontrado");
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private DefaultMutableTreeNode buscarNodo(TipoCuenta nodo)
/*     */   {
/*  74 */     DefaultMutableTreeNode node = null;
/*     */     Enumeration lista;
/*     */    
/*  79 */     if (nodo.getParent() == 0)
/*  80 */       lista = this.root.breadthFirstEnumeration();
/*  81 */     else lista = buscarNodo(buscarPadre(nodo.getParent())).breadthFirstEnumeration();
/*     */ 
/*  84 */     while (lista.hasMoreElements())
/*     */     {
/*  87 */       node = (DefaultMutableTreeNode)lista.nextElement();
/*     */ 
/*  90 */       if (nodo.toString().equals(node.getUserObject().toString()))
/*     */       {
/*  93 */         return node;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  98 */     return null;
/*     */   }
/*     */ 
/*     */   private TipoCuenta buscarPadre(int idPadre) {
/* 102 */     int y = 0;
/*     */ 
/* 104 */     for (int x = 0; x < this.nodoE.size(); x++) {
/* 105 */       if (((TipoCuenta)this.nodoE.get(x)).getCodigo() == idPadre) {
/* 106 */         y = x;
/* 107 */         break;
/*     */       }
/*     */     }
/* 110 */     return (TipoCuenta)this.nodoE.get(y);
/*     */   }
/*     */ 
/*     */   public JTree getArbol() {
/* 114 */     return this.arbol;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.funciones.BuildPlanTreeFromTXT
 * JD-Core Version:    0.6.2
 */