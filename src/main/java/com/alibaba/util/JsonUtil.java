package com.alibaba.util;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonUtil {

	private static final Logger log = Logger.getLogger(JsonUtil.class);
	private static ObjectMapper mapper = new ObjectMapper();

	/**
	 * Object to JSON
	 * 
	 * @param obj
	 * @return String
	 */
	public static <T> String toJson(T obj) {
		try {
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			log.error("write to json string error:" + obj, e);
			return null;
		}
	}

	/**
	 * JSON to Object
	 * 
	 * @param jsonString
	 * @param clazz
	 * @return <T> T
	 */
	public static <T> T fromJson(String jsonString, Class<T> clazz) {
		try {
			return mapper.readValue(jsonString, clazz);
		} catch (Exception e) {
			log.error("parse json string error:" + jsonString, e);
			return null;
		}
	}
	

	public static class TestBean {
		String nm;
		Date dt;
		int age;
		BigDecimal bd;
		
		public String getNm() {
			return nm;
		}
		public void setNm(String nm) {
			this.nm = nm;
		}
		public Date getDt() {
			return dt;
		}
		public void setDt(Date dt) {
			this.dt = dt;
		}
		public BigDecimal getBd() {
			return bd;
		}
		public void setBd(BigDecimal bd) {
			this.bd = bd;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
	}
}
