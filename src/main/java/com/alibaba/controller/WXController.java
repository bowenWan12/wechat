package com.alibaba.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.controller.dispatcher.EventDispatcher;
import com.alibaba.controller.dispatcher.MsgDispatcher;
import com.alibaba.util.MessageUtil;

@RestController
@RequestMapping("/wx")
public class WXController {
	private static final Logger logger = LoggerFactory.getLogger(WXController.class);
	
    
    /**
     * get消息验证，直接返回官网要求的字符串即可。
     * @param req
     * @param resp
     * @return echostr
     */
    @GetMapping("/verify")
    public String wxVeriy(HttpServletRequest req, HttpServletResponse resp) {
    	String signature = req.getParameter("signature");
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		String echostr = req.getParameter("echostr");
		logger.info(signature+"|"+timestamp+"|"+nonce+"|"+echostr);
		return echostr;
    }
    
    @PostMapping("/verify")
	public void postReq(HttpServletRequest req, HttpServletResponse resp) {
		try {
			Map<String, String> map=MessageUtil.parseXml(req);
            String msgtype=map.get("MsgType");
            if(MessageUtil.REQ_MESSAGE_TYPE_EVENT.equals(msgtype)){
                EventDispatcher.processEvent(map); //进入事件处理
            }else{
//            	MsgDispatcher.processMessage(map); //进入消息处理
            	resp.setCharacterEncoding("UTF-8");
            	resp.setContentType("text/html;charset=utf-8");
            	resp.getWriter().print(MsgDispatcher.processMessage(map));
                
            }
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
