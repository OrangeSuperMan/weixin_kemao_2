package edu.gdkm.weixin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.gdkm.weixin.domain.InMessage;
import edu.gdkm.weixin.service.MessageConvertHelper;


@RestController
@RequestMapping("/kemao_2/message/receiver")
public class MessageReceiverController {
	//日志记录器
	private static final Logger LOG=LoggerFactory.getLogger(MessageReceiverController.class);
	@GetMapping
       public String echo(
    		   @RequestParam("signature") String signature,
        	   @RequestParam("timestamp") String timestamp,
        	   @RequestParam("nonce") String nonce,
        	   @RequestParam("echostr") String echostr
    		   ) {
		
		return echostr;
   
       }
	public String onMessage(@RequestBody String xml) {
		LOG.trace("收到的消息原文：\n{}\n--------------",xml);
		//转换消息
		//转换消息1.获取消息的类型
//		String type=xml.substring(xml.indexOf("<MsgType><![CDATA[")+18);
//		type=type.substring(0,type.indexOf("]"));
		
		InMessage inMessage=MessageConvertHelper.convert(xml);
		
		LOG.debug("转换后的消息对象\n{}\n",inMessage);
		//把消息丢入队列
		//消息队列中的消息
		//产生客服消息
		return "success";
	}
}
