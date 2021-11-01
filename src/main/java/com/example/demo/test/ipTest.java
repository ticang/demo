package com.example.demo.test;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class ipTest {

    public static String getAddresses(String content, String encodingString)
            throws UnsupportedEncodingException {
        // 这里调用淘宝的接口
        String urlStr = "http://ip.taobao.com/service/getIpInfo.php";
        // 从接口取得IP所在的省市区信息
        String returnStr = getResult(urlStr, content, encodingString);
        if (returnStr != null) {
            // 处理返回的省市区信息
            System.out.println(returnStr);
            String[] temp = returnStr.split(",");
            if(temp.length<3){
                return "0";//无效IP，局域网测试
            }
            // String region = (temp[5].split(":"))[1].replaceAll("\"", "");
            // region = decodeUnicode(region);// 省份
            String country = (temp[5].split(":"))[0].replaceAll("\"", "");
            String city = (temp[5].split(":"))[1].replaceAll("\"", "");
            String region = country + "," + city;
            return region;
        }
        return null;
    }

    private static String getResult(String urlStr, String content, String encoding) {
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(urlStr);
            connection = (HttpURLConnection) url.openConnection();// 新建连接实例
            connection.setConnectTimeout(2000);// 设置连接超时时间，单位毫秒
            connection.setReadTimeout(2000);// 设置读取数据超时时间，单位毫秒
            connection.setDoOutput(true);// 是否打开输出流 true|false
            connection.setDoInput(true);// 是否打开输入流true|false
            connection.setRequestMethod("POST");// 提交方法POST|GET
            connection.setUseCaches(false);// 是否缓存true|false
            connection.connect();// 打开连接端口
            DataOutputStream out = new DataOutputStream(connection
                    .getOutputStream());// 打开输出流往对端服务器写数据
            out.writeBytes(content);// 写数据,也就是提交你的表单 name=xxx&pwd=xxx
            out.flush();// 刷新
            out.close();// 关闭输出流
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), encoding));// 往对端写完数据对端服务器返回数据
            // 以BufferedReader流来读取
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            reader.close();
            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();// 关闭连接
            }
        }
        return null;
    }

    public static String decodeUnicode(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len;) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException("Malformed encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't') {
                        aChar = '\t';
                    } else if (aChar == 'r') {
                        aChar = '\r';
                    } else if (aChar == 'n') {
                        aChar = '\n';
                    } else if (aChar == 'f') {
                        aChar = '\f';
                    }
                    outBuffer.append(aChar);
                }
            } else {
                outBuffer.append(aChar);
            }
        }
        return outBuffer.toString();
    }

    public static String getAddressResult(String ip) {
        // 创建默认http连接
        HttpClient client = HttpClients.createDefault();
        // 创建一个post请求
        HttpPost post = new HttpPost("http://api.map.baidu.com/location/ip");
        List<NameValuePair> paramList = new ArrayList<NameValuePair>();
        // 传递的参数
        paramList.add(new BasicNameValuePair("ip", ip));
        paramList.add(new BasicNameValuePair("ak", "npaDNxTGE3NSmcVe2CW825DHCOPSwFFk"));
        paramList.add(new BasicNameValuePair("sn", ""));
        paramList.add(new BasicNameValuePair("coor", ""));

        String address = "";
        try {
            // 把参转码后放入请求实体中
            HttpEntity entitya = new UrlEncodedFormEntity(paramList, "utf-8");
            post.setEntity(entitya);// 把请求实体放post请求中
            // 用http连接去执行get请求并且获得http响应
            HttpResponse response = client.execute(post);
            // 从response中取到响应实体
            HttpEntity entity = response.getEntity();
            // 把响应实体转成文本
            String str = EntityUtils.toString(entity);

            // 分隔解析
            if(str.length() >0) {
                int index = str.indexOf("province");
                int index2 = str.indexOf("city");
                int index3 = str.indexOf("district");
                String province = str.substring(index+11, index2-3);
                String city = str.substring(index2+7, index3-3);
                // System.out.println(province);
                // System.out.println(city);
                address = decodeUnicode(province) + "," + decodeUnicode(city);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return address;
    }

    // 测试
    public static void main(String[] args) throws IOException {
        IpAddressUtil ipAddressUtil = new IpAddressUtil();
        String ip = "59.42.239.26";
        String address = "";

        // 淘宝API
        try {
            address = getAddresses("ip="+ip, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(address);
    }

    public String getIpAddress(HttpServletRequest request) {
        // X-Forwarded-For：Squid 服务代理
        String ip = request.getHeader("x-forwarded-for");
        // 有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来
        // 第一个ip为客户端的真实IP
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if(ip.indexOf(",") != -1) {
                ip = ip.split(",")[0];
            }
        }

        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            // Proxy-Client-IP：apache 服务代理
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            // WL-Proxy-Client-IP：weblogic 服务代理
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            // HTTP_CLIENT_IP：有些代理服务器
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            // X-Real-IP：nginx服务代理
            ip = request.getHeader("X-Real-IP");
        }

        // 还是不能获取到，最后再通过request.getRemoteAddr();获取
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}