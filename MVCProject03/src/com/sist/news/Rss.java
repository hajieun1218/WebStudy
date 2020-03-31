package com.sist.news;
// XML 파싱

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/*
 * 		JAXP => Java Api for XML Parse 
 * 				설정 파일 읽기 (Mybatis,Spring,...)
 * 				= DOM (Document Object Model) => 메모리에 저장 (수정,삭제,추가)
 * 				= SAX (Simple Api for XML)    => 읽기전용
 * 
 * 		JAXB => Java Api for XML Bind  => Annotation 이용 (빅데이터용)
 * 				= 마셸     ===> Java class에 있는 데이터 => XML변환
 * 				= 언마셸  ===> XML => Java Object 변환
 * 
 * 		<rss>
 * 			<channel>
 * 				<item>
 * 					<title></title>
 * 					<author></author>
 * 					<description></description>
 * 					<link></link>
 * 				</item>
 * 				<item>
 * 					<title></title>
 * 					<author></author>
 * 					<description></description>
 * 					<link></link>
 * 				</item>
 * 				<item>
 * 					<title></title>
 * 					<author></author>
 * 					<description></description>
 * 					<link></link>
 * 				</item>
 * 			</channel>
 * 		</rss>
 * 
 * 		태그와 태그 사이에 태그가 있으면 class : rss, channel, item
 * 		태그와 태그 사이에 값이 있으면 변수 : title, author, description, link
 * 		
 * 		newssearch.naver.com/search.naver?where=rss&query=코로나
 */
@XmlRootElement
public class Rss {
	private Channel channel=new Channel();

	public Channel getChannel() {
		return channel;
	}
	
	@XmlElement
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	
}
