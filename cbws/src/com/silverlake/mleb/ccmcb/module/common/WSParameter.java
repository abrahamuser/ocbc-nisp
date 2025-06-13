package com.silverlake.mleb.ccmcb.module.common;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface WSParameter {
	/**
	 * Flag of parameter is mandatory or not 
	 * @return true if mandatory
	 */
	boolean isMandatory() default false;
	/**
	 * Flag of parameter is empty allowed or not when field is mandatory
	 * @return true if allowed empty
	 */
	boolean isEmptyAllowed() default false;
}
