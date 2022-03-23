package com.example.demo.test;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class stringTest {
    private static Boolean checkNotZero(String str) {
        if (str != null && !"".equals(str) && !"NULL".equals(str)) {
            if ("0".equals(str) || "0.0".equals(str)) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        System.out.println(Calendar.getInstance().get(Calendar.MONTH));
        String phoneNumber = "12345678910";
        System.out.println(phoneNumber.substring(phoneNumber.length() - 5));
        DateTimeFormatter PURE_DATETIME_MILLIS_FORMAT = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String id = UUID.randomUUID().toString().replaceAll("-","").substring(0,5);
        String randomString = PURE_DATETIME_MILLIS_FORMAT.format(LocalDateTime.now()) + id;
        String str1 = "{\"serialNumber\":\"15513283764\",\"sign\":\"6DD067523C432FE18C42F52ED1DF970DE0D95194\",\"randomString\":\"" + randomString+ "\"}";
        System.out.println(str1);
        Map<String, Object> paramMap = JSONObject.parseObject(str1, Map.class);
        System.out.println(paramMap);

        String str = "-10.0";
        System.out.println(checkNotZero(str));
        System.out.println(str);
        System.out.println(new BigDecimal("1.232432E9").divide(new BigDecimal("10"), 0, BigDecimal.ROUND_HALF_UP).toString());
        System.out.println(String.format("%.2f", Double.parseDouble("0")));
        String appProportion = new BigDecimal("3").divide(new BigDecimal("8"), 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_HALF_UP).toString();;
//        String appTotalProportion = appProportion.multiply(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_HALF_UP).toString();

        System.out.println(appProportion);
//        String appTotalProportion = Float.parseFloat(appProportion) * 100 + "%";
//        System.out.println(appTotalProportion);

        switch ("222") {
            case "1" :
                break;
            default:
                System.out.println("111");
        }
        long l = Long.parseLong("12323123");
        if (l > 3232) {
            System.out.println(l);
        }
        BigDecimal bigDecimal = new BigDecimal("23.001");
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
            put("touchType", "RH01");}});
        resulList.add(new HashMap<String, String>(){{
            put("provinceCode", "09");
            put("touchType", "RH04");}});
        resulList.add(new HashMap<String, String>(){{
            put("provinceCode", "09");
            put("touchType", "RH02");}});
        resulList.add(new HashMap<String, String>(){{
            put("provinceCode", "09");
            put("touchType", "RH03");}});
        System.out.println(resulList.toString());
        List<Map<String, String>> filterResultList = resulList.stream().filter(x -> (!StringUtils.isEmpty(x.get("provinceCode")) && "09".equals(x.get("provinceCode")))).
                sorted(Comparator.comparing(x->x.get("touchType"))
        ).collect(Collectors.toList());
        System.out.println(filterResultList);
        if (!CollectionUtils.isEmpty(filterResultList)) {
            resulList = filterResultList;
        }
        System.out.println(resulList.toString());



    }


}
