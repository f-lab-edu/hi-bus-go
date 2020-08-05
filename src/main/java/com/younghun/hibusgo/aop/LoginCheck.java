package com.younghun.hibusgo.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 로그인 체크 어노테이션
 * @author 조영훈
 *
 * 어노테이션으로 메소드에 런타임시 로그인 체크 aop 적용
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LoginCheck {

}
