package com.example.demo.test;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigDecimal;
import java.security.GeneralSecurityException;

public class stringTest {
    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal("20.001");
        System.out.println(bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString());
        int i = 0;
        while(i++<5){

        }
        System.out.println(i);


    }


}
