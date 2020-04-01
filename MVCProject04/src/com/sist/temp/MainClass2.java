package com.sist.temp;
import java.lang.reflect.Method;
import java.util.*;

import org.apache.catalina.mbeans.ClassNameMBean;

class A {
	@RequestMapping("a.do")
	public void aaa() {
		System.out.println("A:aaa() Call...");
	}
	@RequestMapping("b.do")
	public void bbb() {
		System.out.println("A:bbb() Call...");
	}
	@RequestMapping("c.do")
	public void ccc() {
		System.out.println("A:ccc() Call...");
	}
	@RequestMapping("d.do")
	public void ddd() {
		System.out.println("A:ddd() Call...");
	}
	@RequestMapping("e.do")
	public void eee() {
		System.out.println("A:eee() Call...");
	}
}
public class MainClass2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan=new Scanner(System.in);
		// a.do => aaa, b.do => bbb, ... (URI주소가 들어왔다고 생각)
		System.out.print("URI주소 입력 : ");
		String uri=scan.next();
		
		// 모델을 하나하나 클래스로 만드는 대신 메소드를 사용하면 if문을 많이 써야함 ==> 어노테이션 사용
		/*A a=new A();
		if(uri.equals("a.do")) {
			a.aaa();
		}
		if(uri.equals("b.do")) {
			a.bbb();
		}
		if(uri.equals("c.do")) {
			a.ccc();
		}
		if(uri.equals("d.do")) {
			a.ddd();
		}
		if(uri.equals("e.do")) {
			a.eee();
		}*/
		
		try {
			// 1. 메모리 할당
			// "com.sist.temp.A"를 읽어와서 메모리 할당 (반드시 패키지명부터)
			Class clsName=Class.forName("com.sist.temp.A");
			Object obj=clsName.newInstance();
			// A a=new A();
			
			// 메소드 가져오기
			Method[] methods=clsName.getDeclaredMethods();
			
			for(Method m:methods) {
				// 메소드 위에 어노테이션 여러개 사용할 수 있다 ==> RequestMapping이라는 어노테이션을 달라
				RequestMapping rm=m.getAnnotation(RequestMapping.class);
				
				if(rm.value().equals(uri)) {
					m.invoke(obj, null); // 메소드 호출
				}
//				System.out.println(m.getName());
			}
		} catch(Exception ex) {}
	}

}
