package com.example.demo.test;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
@Slf4j
public class ListTest {
    public static void main(String[] args) {

        List<Map<String, String>> areas = new ArrayList<>();
        areas.add(new HashMap<String, String>() {{
            put("provinceCode", "1");
            put("touchType", "RH02");
            put("ord", "1");
        }});
        areas.add(new HashMap<String, String>() {{
            put("provinceCode", "3");
            put("touchType", "RH02");
            put("ord", "1");
        }});
        List<String> area = Arrays.asList(String.valueOf("1,2,3,4,").split(","));
        List<Map<String, String>> areaList = areas.stream().filter(p -> area.contains(p.get("provinceCode"))).collect(Collectors.toList());
        System.out.println(areaList);
        List<Map<String,String>> resultList = new ArrayList<>();
        resultList.add(new HashMap<String, String>() {{
                put("provinceCode", "09");
                put("touchType", "RH02");
                put("ord", "1");
        }});
        resultList.add(new HashMap<String, String>() {{
            put("provinceCode", "09");
            put("touchType", "RH02");
            put("ord", "2");
        }});
        resultList.add(new HashMap<String, String>() {{
            put("provinceCode", "09");
            put("touchType", "RH03");
            put("ord", "3");
        }});
        resultList.add(new HashMap<String, String>() {{
            put("provinceCode", "09");
            put("touchType", "RH03");
            put("ord", "8");
        }});
        resultList.add(new HashMap<String, String>() {{
            put("provinceCode", "09");
            put("touchType", "RH03");
            put("ord", "4");
        }});
        resultList.add(new HashMap<String, String>() {{
            put("provinceCode", "09");
            put("touchType", "RH01");
            put("ord", "5");
        }});
        resultList.add(new HashMap<String, String>() {{
            put("provinceCode", "09");
            put("touchType", "RH01");
        }});
        resultList.add(new HashMap<String, String>() {{
            put("provinceCode", "09");
            put("touchType", "RH04");
            put("ord", "6");
        }});
        resultList.add(new HashMap<String, String>() {{
            put("provinceCode", "09");
            put("touchType", "RH04");
            put("ord", "0");
        }});
        resultList.add(new HashMap<String, String>() {{
            put("provinceCode", "09");
            put("touchType", "RH04");
        }});
        log.info("广告位推荐信息==>{}", resultList.size());
        log.info("广告位推荐信息==>{}", JSONObject.toJSONString(resultList));
        //先省份后总部
        List<Map<String, String>> filterResultList = resultList.stream().filter(x -> (!StringUtils.isEmpty(x.get("provinceCode")) && !"09".equals(x.get("provinceCode")))).collect(Collectors.toList());

        //多个省取第一个
        if (!CollectionUtils.isEmpty(filterResultList)) {
            Map<String, List<Map<String, String>>> filterResultListMap = filterResultList.stream().collect(Collectors.groupingBy(x -> x.get("provinceCode"),LinkedHashMap::new,Collectors.toList()));
            log.info("广告位推荐信息==>{}", JSONObject.toJSONString(filterResultListMap));
            resultList = filterResultListMap.values().stream().findFirst().get();
        }
        log.info("广告位推荐信息==>{}", JSONObject.toJSONString(resultList));

        Map<String, List<Map<String, String>>> touchType = resultList.stream().collect(Collectors.groupingBy(x -> x.get("touchType"),LinkedHashMap::new,Collectors.toList()));
        List<Map<String, String>> returnList = new ArrayList<>();
        touchType.values().forEach(x-> returnList.add(x.get(0)));
        log.info("广告位推荐信息==>{}", JSONObject.toJSONString(returnList));
        resultList = returnList.stream().sorted(Comparator.comparing(e -> e.get("touchType"), Comparator.reverseOrder())).collect(Collectors.toList());
        log.info("广告位推荐信息==>{}", JSONObject.toJSONString(resultList));

        System.out.println(String.format("%.2f", Double.parseDouble("12.23432")));
        System.out.println(String.format("%.2f", Double.parseDouble("12.2")));
        System.out.println(Double.parseDouble("12.2"));
        System.out.println(Double.parseDouble("12"));

        int[] strings = {1,21,234,34,3,34,5,5,56,6,64,3,543,534,534,5643,5,436,34,6,34654,6,654,654,67};
        int[] ints = Arrays.copyOfRange(strings, 0, 15);
        System.out.println(ints[14]);


        List<String> list1 = new ArrayList<>();
        list1.add("111");
        list1.add("121");
        list1.add("131");
        String x = "111,222,333,";
        String[] split = x.split(",");

        System.out.println(Arrays.asList(split));
        System.out.println(String.join("|", Arrays.asList(split)));

        List<Map<String,String>> resulList = new ArrayList<>();
        HashMap<String, String> map = new HashMap<String, String>(){
            {
                put("commProfitContent", "111");
            }
        };
        resulList.add(map);
//        for (Map<String, String> s : resulList) {
//            if (!StringUtils.isEmpty(s.get("commProfitContent"))) {
//                s.put("cornerMark", s.get("commProfitContent"));
//            }
//            //默认文案
//            if (!StringUtils.isEmpty(s.get("commProfitContent"))) {
//                s.put("busiType", "尊敬的客户，您参与的0元领宽带活动已成功办理，订单号||，联通客服人员将尽快与您取得联系，请耐心等待哦。");
//            }
//            if (!StringUtils.isEmpty(s.get("commProfitContent"))) {
//                s.put("priceRange", "主人，系统繁忙，请您稍后重试哈。给您带来的不便，我们深感抱歉。");
//            }
//            if (!StringUtils.isEmpty(s.get("commProfitContent"))) {
//                s.put("orderUrl", "尊敬的客户，您已成功办理0元领宽带活动，订单号为||，联通客服人员将尽快与您取得联系，请耐心等待哦。");
//            }
//        }

        resulList.forEach(s->{
            if (!StringUtils.isEmpty(s.get("commProfitContent"))) {
                s.put("cornerMark", s.get("commProfitContent"));
            }
            //默认文案
            if (!StringUtils.isEmpty(s.get("commProfitContent"))) {
                s.put("busiType", "尊敬的客户，您参与的0元领宽带活动已成功办理，订单号||，联通客服人员将尽快与您取得联系，请耐心等待哦。");
            }
            if (!StringUtils.isEmpty(s.get("commProfitContent"))) {
                s.put("priceRange", "主人，系统繁忙，请您稍后重试哈。给您带来的不便，我们深感抱歉。");
            }
            if (!StringUtils.isEmpty(s.get("commProfitContent"))) {
                s.put("orderUrl", "尊敬的客户，您已成功办理0元领宽带活动，订单号为||，联通客服人员将尽快与您取得联系，请耐心等待哦。");
            }
        });
        System.out.println(resulList);





    }
}
