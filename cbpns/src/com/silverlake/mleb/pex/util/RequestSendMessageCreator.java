package com.silverlake.mleb.pex.util;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.core.MessageCreator;

public class RequestSendMessageCreator implements MessageCreator{

		private String message;
		private TextMessage texMessage;
		private String destQueueName;
		
		@Override
		public Message createMessage(Session arg0) throws JMSException {
			setTexMessage(arg0.createTextMessage(message));
			Destination replyQueue = arg0.createQueue(destQueueName);
			texMessage.setJMSReplyTo(replyQueue);
			
			return getTexMessage();
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public TextMessage getTexMessage() {
			return texMessage;
		}

		public void setTexMessage(TextMessage texMessage) {
			this.texMessage = texMessage;
		}
		
		public String getMsgID() throws JMSException
		{
			return texMessage.getJMSMessageID();
		}
		
		public String getDestQueueName() {
			return destQueueName;
		}

		public void setDestQueueName(String destQueueName) {
			this.destQueueName = destQueueName;
		}

		
	}