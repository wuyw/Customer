package com.wxj.log;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target({METHOD}) // 声明在方法上
@Retention(RUNTIME)// 运行时期, 也就是一直保留, 通常也都用这个
public @interface Log {
	/**
	 * 
	 * 日志信息
	 * @return
	 */
	String message();
	
	/**
	 * 
	 * 日志记录等级
	 * @return
	 */
	LogLevel level() default LogLevel.TRACE;
	
	/**
	 * 
	 * 是否覆盖包日志等级
	 * 1.为false不会参考level属性。
	 * 2.为true会参考level属性。
	 * @return
	 */
	boolean override() default false;
}
