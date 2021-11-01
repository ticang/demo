package com.example.demo.test;

import com.ailk.ecs.open.esbclient.OpenEsbClient;
import com.ailk.ecs.open.esbclient.bean.EcAopResult;
import com.ailk.ecs.open.esbclient.bean.SysParamBean;
import com.ailk.ecs.open.esbclient.sign.SignAlgorithmType;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.omg.PortableServer.REQUEST_PROCESSING_POLICY_ID;
import sun.util.resources.cldr.zh.CalendarData_zh_Hans_HK;

import java.text.SimpleDateFormat;
import java.util.*;


public class Maintest {
    public static void main(String[] args) {
        String url = "";//测试环境
//创建应用时选择的签名加密方式
        SignAlgorithmType type = SignAlgorithmType.HmacSHA256;
//创建应用后应用基本信息中的密钥
        //若签名加密方式为SHA，请使用"应用密钥"
        //若使用加密方式为RSA，请使用"私钥"
        String signSecurty = "fbc856ead0faef115382c4a6b0ce646e"; //测试环境
        OpenEsbClient client = new OpenEsbClient(url, type, signSecurty);
        SysParamBean sysParamBean = new SysParamBean();
//应用ID，创建应用后可在开发者视图查阅
        sysParamBean.setAppId("505818"); //测试环境

        sysParamBean.setBusiSerial(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
//默认选择格式为json
        sysParamBean.setFormat("json");
//选择接入的能力编码，可位于开发者视图能力查询处查阅

//接入版本，如未作额外通知默认填写1
        sysParamBean.setVersion("1");

        JSONObject busiParam = new JSONObject();
//content业务参数，不同能力业务参数不同

        sysParamBean.setMethod("RISKCONTROL.RISKCONTROL_DECISION");
        Map reqMap = new HashMap();
        reqMap.put("USER_ID", "17611170118");
        reqMap.put("RISK_CONTROL_CODE", "PloyEventIdHMD001");
        reqMap.put("SCENE_CODE", "00");
        reqMap.put("AGENCY_ID", "superfriday");
        busiParam.put("RISK_CONTROL_DECISION_REQ", reqMap);
        EcAopResult result = client.call(sysParamBean, busiParam);
        String string = result.getResponse();
        System.out.println(result);
        JSONObject jsonObject = JSONObject.parseObject(string);
        System.out.println("格式化:" + jsonObject);
        JSONObject resultjson = (JSONObject) jsonObject.get("result");
        String rspmsgstr = (String) resultjson.get("rspmsg");
        System.out.println("rspmsg值:" + rspmsgstr);
        JSONObject rspmsgjson = JSONObject.parseObject(rspmsgstr);
        System.out.println("busiorder值:" + rspmsgjson.get("busiorder"));
    }
}