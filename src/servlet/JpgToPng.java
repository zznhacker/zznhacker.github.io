package servlet;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;


public class JpgToPng {


	    private static JpgToPng instance;

	    JpgToPng() {
	        instance = this;
	    }

	    public static JpgToPng getInstance() {
	        if (instance == null) {
	            instance = new JpgToPng();
	        }
	        return instance;
	    }

	   
	    public boolean narrowAndFormateTransfer(String srcPath, String destPath, int height, int width, String formate) {
	        boolean flag = false;
	        try {
	            File file = new File(srcPath);
	            File destFile = new File(destPath);
	            if (!destFile.getParentFile().exists()) {
	                destFile.getParentFile().mkdir();
	            }
	            BufferedImage src = ImageIO.read(file); // �����ļ�
	            Image image = src.getScaledInstance(width, height, Image.SCALE_DEFAULT);
	            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	            Graphics g = tag.getGraphics();
	            g.drawImage(image, 0, 0, null); // ������С���ͼ
	            g.dispose();
	            flag = ImageIO.write(tag, formate, new FileOutputStream(destFile));// ������ļ���
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return flag;
	    }

	    public static void main(String[] args) {
	        try {
	            JpgToPng service = new JpgToPng();
	            boolean flag = service.narrowAndFormateTransfer("E:\\�ҵ�ͼƬ/��ɡ.jpg", "E:\\�ҵ�ͼƬ/��ɡ.png", 400, 400, "png");
	            System.out.println(flag);
	        } catch (Exception e) {
	            System.out.println(e);
	        }
	    }

}
