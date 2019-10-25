package com.alibaba.wechat.menu;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.util.HttpUtils;

import net.sf.json.JSONObject;

public class MenuMain {
	private static final Logger logger = LoggerFactory.getLogger(MenuMain.class);
	private final String APPID = "wx0d05fe0f02bb588e";
	private final String SECRET = "e906e0e20127ee206fb9baae50b8bf38";
	private String url = "https://api.weixin.qq.com/cgi-bin/token";
	private String tokenUrl="https://api.weixin.qq.com/cgi-bin/token";
    public static void main(String[] args) {
     
        ClickButton cbt=new ClickButton();
        cbt.setKey("image");
        cbt.setName("回复图片");
        cbt.setType("click");
         
         
        ViewButton vbt=new ViewButton();
        vbt.setUrl("http://localhost/login.html");
        vbt.setName("博客");
        vbt.setType("view");
         
        JSONArray sub_button=new JSONArray();
        sub_button.add(cbt);
        sub_button.add(vbt);
         
         
        JSONObject buttonOne=new JSONObject();
        buttonOne.put("name", "菜单");
        buttonOne.put("sub_button", sub_button);
         
        JSONArray button=new JSONArray();
        button.add(vbt);
        button.add(buttonOne);
        button.add(cbt);
         
        JSONObject menujson=new JSONObject();
        menujson.put("button", button);
//        System.out.println(menujson);
        //这里为请求接口的url   +号后面的是token，这里就不做过多对token获取的方法解释
        MenuMain mm = new MenuMain();
        String tokenStr = "";
		try {
			tokenStr = mm.getToken_getTicket();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        String url2="https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+tokenStr;
         
        try{
            String rs=HttpUtils.sendPostBuffer(url2, menujson.toString());
            System.out.println(rs);
        }catch(Exception e){
            System.out.println("请求错误！");
        }
    }

    public String getToken_getTicket() throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        params.put("grant_type", "client_credential");
        params.put("appid", APPID);
        params.put("secret", SECRET);
        String jstoken = HttpUtils.sendGet(
        		tokenUrl, params);
        String access_token = JSONObject.fromObject(jstoken).getString(
                "access_token"); // 获取到token并赋值保存
                System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"token为=============================="+access_token);
                
                return access_token;
    }
 
}
