package com.sist.temp;

import java.lang.reflect.Method;
import java.util.*;

public class MainClass {
	public static void main(String[] args) {
		try {
			Class acls=Class.forName("com.sist.temp.A"); // 리플렉션
//			A a=(A)acls.newInstance();
//			a.display();
			Object obj=acls.newInstance();
			Method[] m=acls.getDeclaredMethods(); // 모든 메소드를 가져온다
			m[0].invoke(obj, null);
		} catch(Exception ex) {}
	}
}
