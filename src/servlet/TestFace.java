package servlet;

import java.io.File;

public class TestFace {
    
    public static void main(String[] args){
        
        FileTools fileTools = new FileTools();
        
        fileTools.do_all("E:/compress");
        //File file=new File(".");
        //System.out.println(file.getAbsolutePath());
    }
}