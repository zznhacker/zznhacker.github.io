/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

/**
 *
 * @author Shen Yang
 */

import com.facepp.*;
import com.facepp.error.FaceppParseException;
import com.facepp.http.HttpRequests;
import com.facepp.http.PostParameters;
import com.facepp.result.FaceppResult;

import java.io.File;

public class FaceInfo{
    
    private String json;
    private String[] info;
    public int faces;
    
    public FaceInfo(String image, int face){
        
        String json = null;
        HttpRequests httpRequests = new HttpRequests("0ef14fa726ce34d820c5a44e57fef470", "4Y9YXOMSDvqu1Ompn9NSpNwWQFHs1hYD");
        PostParameters postParameters;
        
        if(image.substring(0, 4).equals("http")){
            
            postParameters = new PostParameters().setUrl(image).setAttribute("all");
        }else{
        
            File file = new File("D:\\game\\xampp\\tomcat\\webapps\\faceDiary\\img\\"+image);
            postParameters = new PostParameters().setImg(file);
        }
	
        try{
            
            httpRequests.request("detection", "detect", postParameters);
	}catch(FaceppParseException e){
            
            e.printStackTrace();
	}
	
        try{
            
            FaceppResult result = httpRequests.detectionDetect(postParameters);
            faces = result.get("face").getCount();
            json = result.get("face").get(face).toString();
	}catch(FaceppParseException e){
		
            e.printStackTrace();
	}
        
        this.json = json.substring(1, json.length() - 1);
        info = this.json.split("}");
    }
    
    public float get_mouthRight_y(){
        
        String[] split = info[0].split(":");
        
        return Float.parseFloat(split[2].substring(0, split[2].length() - 4));
    }
    
    public float get_mouthRight_x(){
        
        String[] split = info[0].split(":");
        
        return Float.parseFloat(split[3]);
    }
    
    public float get_mouthLeft_x(){
        
        String[] split = info[1].split(":");
        
        return Float.parseFloat(split[3]);
    }
    
    public float get_mouthLeft_y(){
        
        String[] split = info[1].split(":");
        
        return Float.parseFloat(split[3]);
    }
    
    public float get_center_y(){
        
        String[] split = info[2].split(":");
        
        return Float.parseFloat(split[2].substring(0, split[2].length() - 4));
    }
    
    public float get_center_x(){
        
        String[] split = info[2].split(":");
        
        return Float.parseFloat(split[3]);
    }
    
    public float get_height(){
        
        String[] split = info[3].split(":");
        
        return Float.parseFloat(split[1].substring(0, split[1].length() - 6));
    }
    
    public float get_width(){
        
        String[] split = info[3].split(":");
        
        return Float.parseFloat(split[3].substring(0, split[3].length() - 10));
    }
    
    public String get_face_id(){
        
        String[] split = info[3].split(":");
        
        return split[4].substring(1, split[4].length() - 13);
    }
    
    public int get_age_range(){
        
        String[] split = info[3].split(":");
        
        return Integer.parseInt(split[7].substring(0, split[7].length() - 8));
    }
    
    public int get_age_value(){
        
        String[] split = info[3].split(":");
        
        return Integer.parseInt(split[8]);
    }
    
    public String get_gender_value(){
        
        String[] split = info[4].split(":");
        
        return split[2].substring(1, split[2].length() - 14);
    }
    
    public float get_gender_confidence(){
        
        String[] split = info[4].split(":");
        
        return Float.parseFloat(split[3]);
    }
    
    public String get_race_value(){
        
        String[] split = info[5].split(":");
        
        return split[2].substring(1, split[2].length() - 14);
    }
    
    public float get_race_confidence(){
        
        String[] split = info[5].split(":");
        
        return Float.parseFloat(split[3]);
    }
    
    public float get_smiling_value(){
        
        String[] split = info[6].split(":");

        return Float.parseFloat(split[2]);
    }
    
    public float get_nose_y(){
        
        String[] split = info[8].split(":");
        
        return Float.parseFloat(split[2].substring(0, split[2].length() - 4));
    }
    
    public float get_nose_x(){
        
        String[] split = info[8].split(":");
        
        return Float.parseFloat(split[3]);
    }
    
    public float get_eyeLeft_y(){
        
        String[] split = info[9].split(":");
        
        return Float.parseFloat(split[2].substring(0, split[2].length() - 4));
    }
    
    public float get_eyeLeft_x(){
        
        String[] split = info[9].split(":");
        
        return Float.parseFloat(split[3]);
    }
    
    public float get_eyeRight_y(){
        
        String[] split = info[10].split(":");
        
        return Float.parseFloat(split[2].substring(0, split[2].length() - 4));
    }
    
    public float get_eyeRight_x(){
        
        String[] split = info[10].split(":");
        
        return Float.parseFloat(split[3]);
    }
    
    public String to_politically_correct(String race){
        
        if(race.equals("White")){
            
            return " Caucasian";
        }else if(race.equals("Black")){
        
            return "n African American"; 
        }else{
            
            return "n Asian";
        }
    }
    
    public String toString(){
        
        StringBuilder sb = new StringBuilder();
        String age;
        
        sb.append("You're a" + to_politically_correct(get_race_value()));
        
        if(get_race_confidence() <= 60){

            sb.append("?");
        }
        
        if(get_gender_confidence() >= 60){
            
            if(get_gender_value().equals("Male")){
            
                sb.append(" gentleman");
            }else{
            
                sb.append(" lady");
            }
        
        }else{
            
            if(get_gender_value().equals("Male")){
            
                sb.append(" gentleman?");
            }else{
            
                sb.append(" lady?");
            }
        }
        
        sb.append(" in your ");
        
        if(get_age_value() / 10 == 1){
            
            age = "teens";
        }else{
            
            age = (get_age_value() / 10) * 10 + "s";
        }
        
        if(get_age_value() % 10 <= 3){
            
            sb.append("early ").append(age);
        }else if(get_age_value() % 10 <= 6){
            
            sb.append("mid ").append(age);
        }else{
            
            sb.append("late ").append(age);
        }
        
        sb.append(" and ");
        
        if(get_smiling_value() > 50){
            
            sb.append("you look like you're having the time of you life.");
        }else{
            
            sb.append("it wouldn't hurt to smile a bit more.");
        }
        
        return sb.toString();
    }
}