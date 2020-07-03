/*    */ package contaes.empresas;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.UnsupportedEncodingException;
/*    */ import java.security.InvalidAlgorithmParameterException;
/*    */ import java.security.InvalidKeyException;
/*    */ import java.security.NoSuchAlgorithmException;
/*    */ import java.security.spec.AlgorithmParameterSpec;
/*    */ import java.security.spec.InvalidKeySpecException;
/*    */ import java.security.spec.KeySpec;
/*    */ import javax.crypto.BadPaddingException;
/*    */ import javax.crypto.Cipher;
/*    */ import javax.crypto.IllegalBlockSizeException;
/*    */ import javax.crypto.NoSuchPaddingException;
/*    */ import javax.crypto.SecretKey;
/*    */ import javax.crypto.SecretKeyFactory;
/*    */ import javax.crypto.spec.PBEKeySpec;
/*    */ import javax.crypto.spec.PBEParameterSpec;
/*    */ import sun.misc.BASE64Decoder;
/*    */ import sun.misc.BASE64Encoder;
/*    */ 
/*    */ public class DesEncrypter
/*    */ {
/*    */   Cipher ecipher;
/*    */   Cipher dcipher;
/* 19 */   byte[] salt = { -87, -101, -56, 50, 86, 53, -29, 3 };
/*    */ 
/* 25 */   int iterationCount = 19;
/*    */ 
/*    */   DesEncrypter(String passPhrase)
/*    */   {
/*    */     try {
/* 30 */       KeySpec keySpec = new PBEKeySpec(passPhrase.toCharArray(), this.salt, this.iterationCount);
/* 31 */       SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
/*    */ 
/* 33 */       this.ecipher = Cipher.getInstance(key.getAlgorithm());
/* 34 */       this.dcipher = Cipher.getInstance(key.getAlgorithm());
/*    */ 
/* 37 */       AlgorithmParameterSpec paramSpec = new PBEParameterSpec(this.salt, this.iterationCount);
/*    */ 
/* 40 */       this.ecipher.init(1, key, paramSpec);
/* 41 */       this.dcipher.init(2, key, paramSpec);
/*    */     } catch (InvalidAlgorithmParameterException e) {
/*    */     } catch (InvalidKeySpecException e) {
/*    */     } catch (NoSuchPaddingException e) {
/*    */     } catch (NoSuchAlgorithmException e) {
/*    */     }
/*    */     catch (InvalidKeyException e) {
/*    */     }
/*    */   }
/*    */ 
/*    */   public String encrypt(String str) {
/*    */     try {
/* 53 */       byte[] utf8 = str.getBytes("UTF8");
/*    */ 
/* 56 */       byte[] enc = this.ecipher.doFinal(utf8);
/*    */ 
/* 59 */       return new BASE64Encoder().encode(enc);
/*    */     } catch (BadPaddingException e) {
/*    */     } catch (IllegalBlockSizeException e) {
/*    */     } catch (UnsupportedEncodingException e) {
/*    */     }
/* 64 */     return null;
/*    */   }
/*    */ 
/*    */   public String decrypt(String str)
/*    */   {
/*    */     try {
/* 70 */       byte[] dec = new BASE64Decoder().decodeBuffer(str);
/*    */ 
/* 73 */       byte[] utf8 = this.dcipher.doFinal(dec);
/*    */ 
/* 76 */       return new String(utf8, "UTF8");
/*    */     } catch (BadPaddingException e) {
/*    */     } catch (IllegalBlockSizeException e) {
/*    */     } catch (UnsupportedEncodingException e) {
/*    */     } catch (IOException e) {
/*    */     }
/* 82 */     return null;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.empresas.DesEncrypter
 * JD-Core Version:    0.6.2
 */