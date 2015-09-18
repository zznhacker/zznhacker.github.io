package servlet;


import java.io.File;

import com.facepp.*;
import com.facepp.error.FaceppParseException;
import com.facepp.http.HttpRequests;
import com.facepp.http.PostParameters;
import com.facepp.result.FaceppResult;
public class TestFace1 {
	
	public String getGenderName(String res)
	{
		String[] res1=res.split(",");
		String[] res12=res1[1].split(":");
		String[] res122=res12[1].split("}");
		String[] res11=res1[0].split(":");
		String[] res112=res11[1].split("\"");
		String genderName=res112[1];
		return genderName;
		
	}
	public float getGenderConfident(String res)
	{
		String[] res1=res.split(",");
		String[] res12=res1[1].split(":");
		String[] res122=res12[1].split("}");
		String[] res11=res1[0].split(":");
		String[] res112=res11[1].split("\"");
		String genderName=res112[1];
		
		float genderConfidence=Float.parseFloat(res122[0]);
		return genderConfidence;
	}
	
public static void main(String[] args) {
	HttpRequests httpRequests = new HttpRequests("0ef14fa726ce34d820c5a44e57fef470",
            "4Y9YXOMSDvqu1Ompn9NSpNwWQFHs1hYD");
	File file=new File("E:/20111016215.jpg");
	PostParameters postParameters = new PostParameters().setImg(file);  //.setUrl("http://faceplusplus.com/static/img/demo/8.jpg").setAttribute("all");
	try {
		httpRequests.request("detection", "detect", postParameters);
	} catch (FaceppParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		FaceppResult result = httpRequests.detectionDetect(postParameters);
		String a=result.get("face").get(0).get("attribute").get("gender").toString();
		String[] res1=a.split(",");
		String[] res12=res1[1].split(":");
		String[] res122=res12[1].split("}");
		String[] res11=res1[0].split(":");
		String[] res112=res11[1].split("\"");
		String genderName=res112[1];
		float genderConfidence=Float.parseFloat(res122[0]);
		
	} catch (FaceppParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}
}
