package security;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataFilter {

	public static String removeHTMLTags(String htmlString) {
        String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; 
        String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; 
        String regEx_html="<[^>]+>";
        
        Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE); 
        Matcher m_script=p_script.matcher(htmlString); 
        htmlString=m_script.replaceAll(""); 
        
        Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE); 
        Matcher m_style=p_style.matcher(htmlString); 
        htmlString=m_style.replaceAll("");  
        
        Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
        Matcher m_html=p_html.matcher(htmlString); 
        htmlString=m_html.replaceAll("");  

       return htmlString.trim(); 
	}
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
