package com.dashuai.meterial2.samples10.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UrlUtil {

	private static final String baseUrl = "http://image.baidu.com/channel/listjson?";
	private static final String searchUrl = "http://image.baidu.com/i?tn=resultjson&ie=utf-8&word=";

	private static String urlEncode(String tag2) {

		if (null == tag2 || tag2.equals("")) {
			return tag2;
		}
		String str = "";

		try {
			str = URLEncoder.encode(tag2, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;

	}

	public static String appendUrl(String tag1, String tag2, int pn, int rn) {

		StringBuffer buffer = new StringBuffer();
		buffer.append(baseUrl)
				.append("pn=")
				.append(pn)
				.append("&rn=")
				.append(rn)
				.append("&tag1=")
				.append(urlEncode(tag1))
				.append("&tag2=")
				.append(urlEncode(tag2))
				.append("&tag3=&width=&height=&ic=0&ie=utf8&oe=utf-8&image_id=&fr=channel&app=img.browse.channel.wallpaper&t=")
				.append(Math.random());

		return buffer.toString();
	}

	public static String appendUrl2(String word, int pn, int rn) {

		StringBuffer buffer = new StringBuffer();
		buffer.append(searchUrl).append(urlEncode(word)).append("&pn=")
				.append(pn).append("&rn=").append(rn);

		return buffer.toString();
	}
}
