package com.example.demo.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author wk
 * @date 2022/4/14
 * @apiNote
 */
public class stream {
    public static void main(String[] args) {
        String ss = "[{\"floorPrice\":\"108元\",\"marketPrice\":\"500M\",\"productId\":\"982202142089\",\"provinceCode\":\"09\",\"cornerMark\":\"全国\",\"advertType\":\"3\",\"advertId\":\"CLH535660926\",\"productName\":\"跨域收单500M\",\"commIntroduce\":\"10010。\",\"thirdAppUrl\":\"0\",\"subTitle\":\"测试222\",\"speechCraf\":\"10\",\"mainTitle\":\"测试500M\",\"touchType\":\"KY03\",\"discountFlag\":\"500\",\"commType\":\"5个月\",\"channelName\":\"跨域融合收单广告位三\",\"strategyId\":\"11,17,13,10\",\"twoiAdvertId\":\"CLH535660926\",\"channelCode\":\"KY03\",\"profitRatio\":\"10\"},{\"floorPrice\":\"98元\",\"marketPrice\":\"300M\",\"productId\":\"982202142088\",\"provinceCode\":\"09\",\"cornerMark\":\"全国\",\"advertType\":\"3\",\"advertId\":\"CLH540571544\",\"productName\":\"跨域收单300M\",\"commIntroduce\":\"10010。\",\"thirdAppUrl\":\"0\",\"subTitle\":\"测试222\",\"speechCraf\":\"10\",\"mainTitle\":\"测试300M\",\"touchType\":\"KY02\",\"discountFlag\":\"300\",\"commType\":\"3个月\",\"channelName\":\"跨域融合收单广告位二\",\"strategyId\":\"10,11,13,17,18,19,30,31,34,36,38,50,51,59,70,71,74,75,76,79,83,84,85,86,87,88\",\"twoiAdvertId\":\"CLH540571544\",\"channelCode\":\"KY02\",\"profitRatio\":\"10\"},{\"marketPrice\":\"200M\",\"cornerMark\":\"全国\",\"productName\":\"跨域收单200M\",\"orderUrl\":\"尊敬的客户。\",\"thirdAppUrl\":\"0\",\"subTitle\":\"测试222\",\"busiType\":\"快与您取得联系哦。\",\"strategyId\":\"10,11,13,17,18,19,30,31,34,36,38,50,51,59,70,71,74,75,76,79,81,83,84,85,86,87,88,89,90,91,97\",\"bankingProductId\":\"业务说明\\n\",\"channelCode\":\"KY01\",\"profitRatio\":\"10\",\"floorPrice\":\"78元\",\"moduleType\":\"办理流程\\n\",\"productId\":\"982202142084\",\"provinceCode\":\"09\",\"advertType\":\"3\",\"advertId\":\"CLH557169725\",\"commIntroduce\":\":为准,或详询10010。\",\"speechCraf\":\"10\",\"mainTitle\":\"测试200M\",\"touchType\":\"KY01\",\"discountFlag\":\"200\",\"commType\":\"1个月\",\"recommendContent\":\"活动规则\",\"channelName\":\"跨域融合收单广告位一\",\"priceRange\":\"感抱歉。\",\"twoiAdvertId\":\"CLH557169725\"},{\"floorPrice\":\"168元\",\"marketPrice\":\"1000M\",\"productId\":\"982202142090\",\"provinceCode\":\"09\",\"cornerMark\":\"全国\",\"advertType\":\"3\",\"advertId\":\"CLH560984488\",\"productName\":\"跨域收单1000M\",\"commIntroduce\":\"作人员告知为准,或详询10010。\",\"thirdAppUrl\":\"0\",\"subTitle\":\"测试222\",\"speechCraf\":\"10\",\"mainTitle\":\"测试1000M\",\"touchType\":\"KY04\",\"discountFlag\":\"1000\",\"commType\":\"10个月\",\"channelName\":\"跨域融合收单广告位四\",\"strategyId\":\"11\",\"twoiAdvertId\":\"CLH560984488\",\"channelCode\":\"KY04\",\"profitRatio\":\"10\"}] \n";
        JSONArray jsonArray = JSONArray.parseArray(ss);
        List<Map> resulList = jsonArray.toJavaList(Map.class);
        System.out.println(JSONObject.toJSONString(resulList));
        List<Map> areas = new ArrayList<>();
        areas.add(new HashMap<String, String>() {{
            put("provinceCode", "11");
            put("provinceName", "11");
        }});
        areas.add(new HashMap<String, String>() {{
            put("provinceCode", "10");
            put("provinceName", "10");
        }});
        areas.add(new HashMap<String, String>() {{
            put("provinceCode", "13");
            put("provinceName", "13");
        }});
        areas.add(new HashMap<String, String>() {{
            put("provinceCode", "17");
            put("provinceName", "17");
        }});
        areas.add(new HashMap<String, String>() {{
            put("provinceCode", "18");
            put("provinceName", "18");
        }});
        areas.add(new HashMap<String, String>() {{
            put("provinceCode", "30");
            put("provinceName", "30");
        }});
        areas.add(new HashMap<String, String>() {{
            put("provinceCode", "31");
            put("provinceName", "31");
        }});

        Map<String, Object> resultMap = new HashMap<>(2);

        for (Map<String, Object> s : resulList) {
            if (!StringUtils.isBlank(String.valueOf(s.get("strategyId")))) {
                List<Map> areaList = new ArrayList<>();
                areaList.addAll(areas);
                List<String> area = Arrays.asList(String.valueOf(s.get("strategyId")).split(","));
                areaList = areaList.stream().filter(p -> area.contains(p.get("provinceCode"))).collect(Collectors.toList());
                s.put("areas", areaList);
                System.out.println(areaList.hashCode());
            } else {
                s.put("areas", areas);

            }
        }
        resultMap.put("resulList", resulList);
        System.out.println(resultMap);
        System.out.println(JSONObject.toJSONString(resultMap));
        System.out.println(JSONObject.toJSONString(resultMap, SerializerFeature.DisableCircularReferenceDetect));

    }
}
