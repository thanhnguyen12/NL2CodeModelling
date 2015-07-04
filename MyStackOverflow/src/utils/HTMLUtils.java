package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HTMLUtils {

	public static String getTextFromHTML(String html) {
		if(html == null)
			return null;
		StringBuilder strBldr = new StringBuilder();
		Document doc = Jsoup.parse(html);
		Elements links = doc.getElementsByTag("p");
		for (Element link : links) {
			String result = link.text();
			strBldr.append(result + "\n");
		}
		return strBldr.length() > 0 ? strBldr.toString() : null;
	}
	
	public static List<String> getCodeFromHTML(String html) {
		if(html == null)
			return null;
		List<String> codeFrags = new ArrayList<String>();
		Document doc = Jsoup.parse(html);
		Elements links = doc.getElementsByTag("code");
		for (Element link : links) {
			String code = link.text();
			codeFrags.add(code);
		}
		return codeFrags;
	}
	
	@SuppressWarnings("unused")
	/** Need to be reconsidered*/
	private static String parseHTMLString(String html, String tag) {
		if(html == null)
			return null;
		StringBuilder strBldr = new StringBuilder();
		/** Hard-code */
		int always = 2;
		
		switch(always) {
		case 1: /** Using Regex for computational issue */
			/** Treat \n, \r\n,... as a character using Pattern.DOALL */
			html = "<pre><code>Body1;</code></pre>";
			Pattern patt = Pattern.compile("<tag>(.*)</tag>", Pattern.DOTALL);
			Matcher match = patt.matcher(html);
			while(match.find()) {
				String result = match.group(1);
				strBldr.append(result);
			}
			break;
			
		case 2: /** Using Jsoup for testing */
			Document doc = Jsoup.parse(html);
			Elements links = doc.getElementsByTag(tag);
			for (Element link : links) {
				String result = link.text();
				strBldr.append(result);
			}
			break;
		}
		return strBldr.length() > 0 ? strBldr.toString() : null ;
	}
}
