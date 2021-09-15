package com.example.demo.test;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigDecimal;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class stringTest {
    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal("20.001");
        System.out.println(bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString());
        int i = 0;
        while(i++<5){

        }
        System.out.println(i);

        HashMap<String, String> map1 = new HashMap<String, String>() {{
            put("provinceCode", "09");
            put("id", "1");
        }};
        System.out.println(String.join("|", map1.values()));

        List<Map<String, String>> resulList = new ArrayList<>();
        HashMap<String, String> hashMap = new HashMap<>();
        resulList.add(new HashMap<String, String>(){{
            put("provinceCode", "09");
            put("id", "1");}});
        resulList.add(new HashMap<String, String>(){{
            put("provinceCode", "09");
            put("id", "2");}});
//        resulList.add(new HashMap<String, String>(){{
//            put("provinceCode", "19");
//            put("id", "3");}});
//        resulList.add(new HashMap<String, String>(){{
//            put("provinceCode", "39");
//            put("id", "4");}});
        System.out.println(resulList.toString());
        List<Map<String, String>> filterResultList = resulList.stream().filter(x -> (!StringUtils.isEmpty(x.get("provinceCode")) && !"09".equals(x.get("provinceCode")))).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(filterResultList)) {
            resulList = filterResultList;
        }
        System.out.println(resulList.toString());



    }


}
