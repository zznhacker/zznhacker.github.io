package servlet;



import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;

import org.apache.commons.lang.StringUtils;


public class Compress {
	public String control(String path)  
    {  
        if(compressPic(path, "E:/ssss.jpg"))  
        {
        	
            System.out.println("ѹ���ɹ���");
            
            File file = new File(path);
            file.delete();
            JpgToPng service = new JpgToPng();
            boolean flag = service.narrowAndFormateTransfer("E:/ssss.jpg", "E:/compress/ssss.png",491,900, "png");
            System.out.println(flag);
            return "E:/compress/ssss.png";//.replaceFirst("jpg", "png");
        }  
        else  
        {  
            System.out.println("ѹ��ʧ�ܣ�");
            return null;
        }  
    }  
      
  
    public static boolean compressPic(String srcFilePath, String descFilePath)  
    {  
        File file = null;  
        BufferedImage src = null;  
        FileOutputStream out = null;  
        ImageWriter imgWrier;  
        ImageWriteParam imgWriteParams;  
  
        // ָ��дͼƬ�ķ�ʽΪ jpg  
        imgWrier = ImageIO.getImageWritersByFormatName("jpg").next();  
        imgWriteParams = new javax.imageio.plugins.jpeg.JPEGImageWriteParam(null);  
        // Ҫʹ��ѹ��������ָ��ѹ����ʽΪMODE_EXPLICIT  
        imgWriteParams.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);  
        // ����ָ��ѹ���ĳ̶ȣ�����qality��ȡֵ0~1��Χ�ڣ�  
        imgWriteParams.setCompressionQuality((float)1);  
        imgWriteParams.setProgressiveMode(ImageWriteParam.MODE_DISABLED);  
        ColorModel colorModel = ColorModel.getRGBdefault();
        // ָ��ѹ��ʱʹ�õ�ɫ��ģʽ  
        imgWriteParams.setDestinationType(new javax.imageio.ImageTypeSpecifier(colorModel, colorModel  
                .createCompatibleSampleModel(16, 16)));  
  
        try  
        {  
            if(StringUtils.isBlank(srcFilePath))  
            {  
                return false;  
            }  
            else  
            {  
                file = new File(srcFilePath);  
                src = ImageIO.read(file);  
                out = new FileOutputStream(descFilePath);  
  
                imgWrier.reset();  
                // ������ָ�� outֵ�����ܵ���write����, ImageOutputStream����ͨ���κ� OutputStream����  
                imgWrier.setOutput(ImageIO.createImageOutputStream(out));  
                // ����write�������Ϳ�����������дͼƬ  
                imgWrier.write(null, new IIOImage(src, null, null), imgWriteParams);  
                out.flush();  
                out.close();  
            }  
        }  
        catch(Exception e)  
        {  
            e.printStackTrace();  
            return false;  
        }  
        return true;  
    }  
}
