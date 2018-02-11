import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.Key;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wangmeng247
 * @date 2018-02-05 11:09
 */

public class AllTest {
    public static void main(String[] args){
//        Long timeStamp = 1535731199000L;  //获取当前时间戳
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss dd");
//        String sd = sdf.format(new Date(Long.parseLong(String.valueOf(timeStamp))));   // 时间戳转换成时间
//        System.out.println(sd);
//
//        Long timeStamp2 = 1514768400000L;  //获取当前时间戳
//        SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss dd");
//        String sd2 = sdf2.format(new Date(Long.parseLong(String.valueOf(timeStamp2))));   // 时间戳转换成时间
//        System.out.println(sd2);

        jdkPBE();
    }

    public static final String src = "laoyeye pbe";

    // 用jdk实现:
    public static void jdkPBE()
    {
        try
        {
            // 初始化盐
            SecureRandom random = new SecureRandom();
            byte[] salt = random.generateSeed(8);
            // 口令与密钥
            String password = "laoyeye4";
            PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray());
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBEWITHMD5andDES");
            Key key = factory.generateSecret(pbeKeySpec);
            // 加密
            PBEParameterSpec pbeParameterSpac = new PBEParameterSpec(salt, 100);//参数：盐和迭代次数
            Cipher cipher = Cipher.getInstance("PBEWITHMD5andDES");
            cipher.init(Cipher.ENCRYPT_MODE, key, pbeParameterSpac);
            byte[] result = cipher.doFinal(src.getBytes());
            System.out.println("jdk pbe encrypt:" + Hex.encodeHexString(result));
            // 解密
            cipher.init(Cipher.DECRYPT_MODE, key, pbeParameterSpac);
            result = cipher.doFinal(result);
            System.out.println("jdk pbe decrypt:" + new String(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
