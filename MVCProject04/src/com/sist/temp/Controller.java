package com.sist.temp;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
// Runtime : 프로그램 종료할때까지 메모리 저장
// type : 클래스

@Retention(RUNTIME)
@Target(TYPE)
public @interface Controller {

}
