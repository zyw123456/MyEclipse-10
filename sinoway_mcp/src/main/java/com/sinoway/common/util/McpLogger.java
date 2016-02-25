package com.sinoway.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 公共日志类,参考WFLogger
 * @author Liuzhen
 * @version 1.0
 * 2015-11-3
 */
public class McpLogger {
	private final static String LOGTYPE_NORMAL = "NORMAL";
	private Logger logger;
	private String type;

	/**
	 * 获取日志类型
	 * 
	 * @return 日志类型
	 */
	public String getType() {
		return type.replace("[", "").replace("]", "");
	}

	/**
	 * 使用该构造方法将使日志类型默认为NORMAL
	 */
	private McpLogger() {
		this.type = "[" + LOGTYPE_NORMAL + "]";
	}

	/**
	 * 使用指定的日志类别来构造日志器<br>
	 * 使用日志类别主要用于使用者便于进行自定义的日志归类, 减少在解析时的工作量
	 * 
	 * @param type
	 *            日志类别
	 */
	private McpLogger(String loggerType) {
		this.type = "[" + loggerType + "]";
	}

	/**
	 * 通过指定日志器名称来获取日志器实例
	 * 
	 * @param name
	 *            日志器名称
	 * @return 日志器实例
	 */
	public static McpLogger getLogger(String name) {
		McpLogger logger = new McpLogger();
		logger.logger = LoggerFactory.getLogger(name);
		return logger;
	}

	/**
	 * 通过指定日志类别和日志器名称来获取日志器实例
	 * 
	 * @param loggerType
	 *            日志类别
	 * @param name
	 *            日志器名称
	 * @return 日志器实例
	 */
	public static McpLogger getLogger(String loggerType, String name) {
		McpLogger logger = new McpLogger(loggerType);
		logger.logger = LoggerFactory.getLogger(name);
		return logger;
	}

	/**
	 * 通过类名获取日志器实例
	 * 
	 * @param clazz
	 *            类名
	 * @return 日志器实例
	 */
	public static McpLogger getLogger(Class<?> clazz) {
		McpLogger logger = new McpLogger();
		logger.logger = LoggerFactory.getLogger(clazz);
		return logger;
	}

	/**
	 * 通过日志类别和类获取日志器实例
	 * 
	 * @param loggerType
	 *            日志类别
	 * @param clazz
	 *            类
	 * @return 日志器实例
	 */
	public static McpLogger getLogger(String loggerType, Class<?> clazz) {
		McpLogger logger = new McpLogger(loggerType);
		logger.logger = LoggerFactory.getLogger(clazz);
		return logger;
	}

	public void trace(Object message) {
		logger.trace(type + message);
	}

	public void trace(Object message, Throwable t) {

		logger.trace(type + message, t);
	}

	public boolean isTraceEnabled() {

		return logger.isTraceEnabled();
	}

	public void debug(Object message) {

		logger.debug(type + message);
	}

	public void debug(Object message, Throwable t) {

		logger.debug(type + message, t);
	}

	/**
	 * 使用带有格式化占位符的格式串来输出调试信息，可以避免额外的参数转换
	 * 
	 * @param format
	 *            格式串，使用"{}"来占位
	 * @param arg
	 *            用于替换{}的参数
	 */
	public void debug(String format, Object arg) {
		logger.debug(type + format, arg);
	}

	/**
	 * 使用带有格式化占位符的格式串来输出调试信息，可以避免额外的参数转换
	 * 
	 * @param format
	 *            格式串，使用"{}"来占位
	 * @param arg1
	 *            用于替换{}的参数
	 * @param arg2
	 *            用于替换{}的参数
	 */
	public void debug(String format, Object arg1, Object arg2) {
		logger.debug(type + format, arg1, arg2);
	}

	/**
	 * 使用带有格式化占位符的格式串来输出调试信息，可以避免额外的参数转换
	 * 
	 * @param format
	 *            格式串，使用"{}"来占位
	 * @param argArray
	 *            用于替换{}的参数
	 */
	public void debug(String format, Object... argArray) {
		logger.debug(type + format, argArray);
	}

	public void error(Object message) {

		logger.error(type + message);
	}

	public void error(Object message, Throwable t) {

		logger.error(type + message, t);
	}

	/**
	 * 使用带有格式化占位符的格式串来输出调试信息，可以避免额外的参数转换
	 * 
	 * @param format
	 *            格式串，使用"{}"来占位
	 * @param argArray
	 *            用于替换{}的参数
	 */
	public void error(String format, Object... argArray) {
		logger.error(type + format, argArray);
	}

	public void info(Object message) {

		logger.info(type + message);
	}

	public void info(Object message, Throwable t) {

		logger.info(type + message, t);
	}

	public boolean isDebugEnabled() {

		return logger.isDebugEnabled();
	}

	public boolean isInfoEnabled() {

		return logger.isInfoEnabled();
	}

	public void warn(Object message) {

		logger.warn(type + message);
	}

	public void warn(Object message, Throwable t) {

		logger.warn(type + message, t);
	}

	public static void main(String[] args) {
		// DEBUG < INFO < WARN < ERROR
		McpLogger logger = McpLogger.getLogger(McpLogger.class);
		logger.debug("你好！debug日志");
		logger.info("你好！info日志");
		logger.warn("你好！warn日志");
		logger.error("你好！error日志");
		
	}
}
