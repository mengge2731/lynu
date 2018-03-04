package com.wanhong.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.wanhong.common.errorcode.BusinessCode;
import com.wanhong.domain.ResultJson;
import com.wanhong.domain.vo.MessageResultVo;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @author wangmeng247
 * @date 2018-03-04 14:31
 */
public class SendMessageUtil {
    private static Logger logger = LoggerFactory.getLogger(SendMessageUtil.class);
    // 查账户信息的http地址
    private static String URI_GET_USER_INFO = "http://yunpian.com/v1/user/get.json";

    //通用发送接口的http地址
    private static String URI_SEND_SMS = "http://yunpian.com/v1/sms/send.json";

    // 模板发送接口的http地址
    private static String URI_TPL_SEND_SMS = "http://yunpian.com/v1/sms/tpl_send.json";

    // 发送语音验证码接口的http地址
    private static String URI_SEND_VOICE = "http://yunpian.com/v1/voice/send.json";

    //编码格式。发送编码格式统一用UTF-8
    private static String ENCODING = "UTF-8";


    public ResultJson sendMessage(String mobile){
        ResultJson resultJson;
        //修改为您的apikey.apikey可在官网（http://www.yuanpian.com)登录后用户中心首页看到
//       String apikey = "3bf6adecf98710a21230d60d2d1e77f1";
        String apikey = "9b5ba79f7c44e0814b85456a7a1dcffb";
        String code="";
        /**************** 使用通用接口发短信(推荐) *****************/
        //设置您要发送的内容(内容必须和某个模板匹配。以下例子匹配的是系统提供的1号模板）

        Random random = new Random();
        for(int i=0;i<6;i++){
            code+=random.nextInt(9)+1;
        }
        String text = "【中标麒麟】验证码："+code+"，您正在使用中标麒麟license管理平台，该验证码20分钟内有效。";

        String result="";
        try {
            result = this.sendSms(apikey, text, mobile);
        }catch (Exception e) {
            logger.error("发送短信出错 出错信息：{}",e);
            return new ResultJson(BusinessCode.SEND_MESSAGE_ERROR);
        }
        logger.info("云片返回结果：{}"+result);
        MessageResultVo resultVo = JSON.parseObject(result,MessageResultVo.class);
        String susscessmsg = resultVo.getMsg();

        if(susscessmsg!=null && !"".equals(susscessmsg.trim()) && susscessmsg.equals("OK")){
            resultJson = new ResultJson(BusinessCode.SUCCESS);
            Map<String,Object> message_result = new HashMap<>();
            message_result.put("messageResultVo",resultVo);
            message_result.put("message",code);
            resultJson.setData(message_result);
        }else{
            resultJson = new ResultJson(BusinessCode.SEND_MESSAGE_ERROR);
            resultJson.setData(resultVo);
        }
        return resultJson;
    }



    /**
     * 取账户信息
     *
     * @return json格式字符串
     * @throws java.io.IOException
     */
    public static String getUserInfo(String apikey) throws IOException, URISyntaxException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);
        return post(URI_GET_USER_INFO, params);
    }

    /**
     * 通用接口发短信
     *
     * @param apikey apikey
     * @param text   　短信内容
     * @param mobile 　接受的手机号
     * @return json格式字符串
     * @throws IOException
     */
    public static String sendSms(String apikey, String text, String mobile) throws IOException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);
        params.put("text", text);
        params.put("mobile", mobile);
        return post(URI_SEND_SMS, params);
    }

    /**
     * 通过模板发送短信(不推荐)
     *
     * @param apikey    apikey
     * @param tpl_id    　模板id
     * @param tpl_value 　模板变量值
     * @param mobile    　接受的手机号
     * @return json格式字符串
     * @throws IOException
     */
    public static String tplSendSms(String apikey, long tpl_id, String tpl_value, String mobile) throws IOException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);
        params.put("tpl_id", String.valueOf(tpl_id));
        params.put("tpl_value", tpl_value);
        params.put("mobile", mobile);
        return post(URI_TPL_SEND_SMS, params);
    }

    /**
     * 通过接口发送语音验证码
     * @param apikey apikey
     * @param mobile 接收的手机号
     * @param code   验证码
     * @return
     */
    public static String sendVoice(String apikey, String mobile, String code) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);
        params.put("mobile", mobile);
        params.put("code", code);
        return post(URI_SEND_VOICE, params);
    }

    /**
     * 基于HttpClient 4.3的通用POST方法
     *
     * @param url       提交的URL
     * @param paramsMap 提交<参数，值>Map
     * @return 提交响应
     */
    public static String post(String url, Map<String, String> paramsMap) {
        CloseableHttpClient client = HttpClients.createDefault();
        String responseText = "";
        CloseableHttpResponse response = null;
        HttpPost method = new HttpPost(url);
        try {
            if (paramsMap != null) {
                List<NameValuePair> paramList = new ArrayList<NameValuePair>();
                for (Map.Entry<String, String> param : paramsMap.entrySet()) {
                    NameValuePair pair = new BasicNameValuePair(param.getKey(), param.getValue());
                    paramList.add(pair);
                }
                method.setEntity(new UrlEncodedFormEntity(paramList, ENCODING));
            }
            response = client.execute(method);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseText = EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                method.abort();
//            	method.releaseConnection();
                response.close();
                client.getConnectionManager().shutdown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return responseText;
    }

}
