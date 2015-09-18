package servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileTools extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	        FileTools fileTools = new FileTools();
	        
	        String filePath = "E:/截图";
		    String file=getFile(filePath)==null?"":getFile(filePath);
		    if(file==null||file==""||file.isEmpty()){
		    	response.sendRedirect("home.jsp");
		    	return;
		    }
		    file=file.replaceAll("\\\\","/");
		    file=new Compress().control(file);
	        
	        fileTools.do_all("E:/compress");
	        //File file=new File(".");
	        //System.out.println(file.getAbsolutePath());
	        response.sendRedirect("home.jsp");
	}
    
    public void make_directory(String name){
        
        File file = new File(name);
        file.mkdir();
    }
    
    public void make_text_file(String name, String content){
        
        try{
            
            PrintWriter writer = new PrintWriter(name + ".txt", "UTF-8");
            writer.println(content);
            writer.close();
        }catch(Exception ex){
            
            ex.printStackTrace();
        }
    }
    
    public void move_file(File file, File directory){
        
        file.renameTo(new File(directory.getAbsolutePath() + "/" + file.getName()));
    }
    
    public File[] getDirectoryContents(File folder){
        
        return folder.listFiles();
    }
    
    public File getDirectoryImage(File folder){
        
        for(File file : folder.listFiles()){
        	if(!file.getName().equals("src")){
            System.out.println(file.getName().substring(file.getName().length() - 4));
            if(file.getName().substring(file.getName().length() - 4).equals(".png")){
                return file;
            }}
        }
        return null;
    }
    
    public String getFile(String filePath){
		 File root = new File(filePath);
		 File[] files = root.listFiles();
		 if (files==null||files.length==0) {
			return null;
		}
		 String file=files[0].getAbsolutePath();
		 System.out.println(file);
		 return file;
	 }
    
    public  void  copyFile(String  oldPath,  String  newPath)  {  
        try  {  
//            int  bytesum  =  0;  
            int  byteread  =  0;  
            File  oldfile  =  new  File(oldPath);  
            if  (oldfile.exists())  {  //文件存在时  
                InputStream  inStream  =  new  FileInputStream(oldPath);  //读入原文件 
                FileOutputStream  fs  =  new  FileOutputStream(newPath);
                byte[]  buffer  =  new  byte[1444];  
//                int  length;  
                while  (  (byteread  =  inStream.read(buffer))  !=  -1)  {  
//                    bytesum  +=  byteread;  //字节数  文件大小  
//                    System.out.println(bytesum);  
                    fs.write(buffer,  0,  byteread);  
                }  
                inStream.close();  
            }  
            else {
            	System.out.println("null");  
			}
        }  
        catch  (Exception  e)  {  
            System.out.println("复制单个文件操作出错");  
            e.printStackTrace();  
  
        }  
  
    } 
    
    public void do_all(String update){
        
        File data = new File("D:\\MyEclipse workspace\\faceDiary\\WebRoot\\img\\data");
        File current = new File(".");
        File image = getDirectoryImage(new File(update));//Contents(new File(update))[0];
        
        String old=image.getAbsolutePath();
        String newFile="D:\\game\\xampp\\tomcat\\webapps\\faceDiary\\img\\"+image.getName();
        String newFile2="D:\\MyEclipse workspace\\faceDiary\\WebRoot\\img\\"+image.getName();
        
        System.out.println("old:"+old+" new:"+newFile);
        copyFile(old,newFile);
        copyFile(old,newFile2);
        
        File latest = null;
        
        if(data.exists() && data.isDirectory()){
            
            File[] dataContent = getDirectoryContents(data);
            
            make_directory("D:\\MyEclipse workspace\\faceDiary\\WebRoot\\img\\user" + dataContent.length);
            File dir = new File("D:\\MyEclipse workspace\\faceDiary\\WebRoot\\img\\user" + dataContent.length);
            move_file(dir, data);
            latest = getDirectoryContents(data)[getDirectoryContents(data).length - 1];
        }else{
            System.out.println("Here");
            make_directory("D:\\MyEclipse workspace\\faceDiary\\WebRoot\\img\\data");
            data = new File("D:\\MyEclipse workspace\\faceDiary\\WebRoot\\img\\data");
            
            make_directory("D:\\MyEclipse workspace\\faceDiary\\WebRoot\\img\\user" + 0);
            File dir = new File("D:\\MyEclipse workspace\\faceDiary\\WebRoot\\img\\user0");
            move_file(dir, data);
            latest = getDirectoryContents(data)[0];
        }
        
        move_file(image, current);
        
        
        FaceInfo faceInfo = new FaceInfo(image.getName(), 0);
        
        for(int i = 0; i < faceInfo.faces; i++){

            int cur = i + 1;

            FaceInfo faceInfoIter = new FaceInfo(image.getName(), i);
            make_text_file(image.getName().substring(0, image.getName().length() - 4) + cur, faceInfoIter.toString());
            File text = new File(image.getName().substring(0, image.getName().length() - 4) + cur + ".txt");
            move_file(text, latest);
        }
        move_file(getDirectoryImage(new File(".")), latest);    
    }

}
