package com.silverlake.mleb.ccmcb.module.common;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.silverlake.mleb.mcb.constant.VCMethodConstant;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface WSClass {
	/**
	 * Function id of the web service, reference of function id for MiB
	 * @return
	 */
	VCMethodConstant.FUNCTION_CODES functionId() default VCMethodConstant.FUNCTION_CODES.EMPTY;
	/**
	 * Set to true if CR verification is required
	 * requestBean field's requestId and responseCode is mandatory if this flag set to true
	 * @return
	 */
	boolean isCRVerify() default false;
}
