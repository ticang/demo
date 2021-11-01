package com.example.demo.test;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class ListTest {
    public static void main(String[] args) {
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
