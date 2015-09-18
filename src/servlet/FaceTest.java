package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.facepp.error.FaceppParseException;
import com.facepp.http.HttpRequests;
import com.facepp.http.PostParameters;
import com.facepp.result.FaceppResult;

public class FaceTest extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		out.println("11111");
		HttpRequests httpRequests = new HttpRequests("0ef14fa726ce34d820c5a44e57fef470",
	            "4Y9YXOMSDvqu1Ompn9NSpNwWQFHs1hYD");
		
		String filePath = "E:/НиЭМ";
	    String file=getFile(filePath);
	    file=file.replaceAll("\\\\","/");
	    file=new Compress().control(file);
		File photo=new File(file);
		
		System.out.println(file);
		//File testFile=new File();
		System.out.println("length:"+photo.length());
		PostParameters postParameters = new PostParameters().setImg(photo);  //.setUrl("http://faceplusplus.com/static/img/demo/8.jpg").setAttribute("all");
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
			out.println(genderName);
		} catch (FaceppParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	 public String getFile(String filePath){
		 File root = new File(filePath);
		 File[] files = root.listFiles();
		 String file=files[0].getAbsolutePath();
		 System.out.println(file);
		 return file;
	 }
	
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
	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
