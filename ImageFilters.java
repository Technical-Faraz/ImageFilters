import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

public class ImageFilters {
	
	// Negative 
	public static BufferedImage negative(BufferedImage img1) {
		BufferedImage imgResult = new BufferedImage(img1.getWidth(),img1.getWidth(),BufferedImage.TYPE_INT_ARGB);
	
		for(int y = 0; y < img1.getHeight(); y++) {
			for(int x = 0; x < img1.getWidth(); x++) {
				//Getting pixels of first image 
				int p1 = img1.getRGB(x,y);
				int r1 = (p1>>16) & 0xff;	//get red
				int g1 = (p1>>8) & 0xff; //get green
				int b1 = p1 & 0xff; //get blue
				//Result image
				int a = 255;
				int r = 255 - r1;
				int g = 255 - g1;
				int b = 255 - b1;
				
				int p = (a<<24) | (r<<16) | (g<<8) | b;
				imgResult.setRGB(x, y, p);
				}
		}
		return imgResult;
	}
	//Grayscale
	public static BufferedImage grayscale(BufferedImage img1) {
		BufferedImage imgResult = new BufferedImage(img1.getWidth(),img1.getWidth(),BufferedImage.TYPE_INT_ARGB);
	
		for(int y = 0; y < img1.getHeight(); y++) {
			for(int x = 0; x < img1.getWidth(); x++) {
				//Getting pixels of first image 
				int p1 = img1.getRGB(x,y);
				int r1 = (p1>>16) & 0xff;	//get red
				int g1 = (p1>>8) & 0xff; //get green
				int b1 = p1 & 0xff; //get blue
				//Result image
				int a = 255;
				int r = (r1+g1+b1)/3;
				int g = (r1+g1+b1)/3;
				int b = (r1+g1+b1)/3;
				
				int p = (a<<24) | (r<<16) | (g<<8) | b;
				imgResult.setRGB(x, y, p);
				}
		}
		return imgResult;
	}
	public static BufferedImage Hue(BufferedImage img1, int color) { // color = 1 for red, 2 for green, 3 for blue
		BufferedImage imgResult = new BufferedImage(img1.getWidth(),img1.getWidth(),BufferedImage.TYPE_INT_ARGB);
	
		for(int y = 0; y < img1.getHeight(); y++) {
			for(int x = 0; x < img1.getWidth(); x++) {
				//Getting pixels of first image 
				int p1 = img1.getRGB(x,y);
				int r1 = (p1>>16) & 0xff;	//get red
				int g1 = (p1>>8) & 0xff; //get green
				int b1 = p1 & 0xff; //get blue
				
				int avg = r1+g1+b1;
				int a,r,g,b;
				//Result image according to selected hue
				if(color == 1) { //red hue
					if(avg < 128) {
						a =  255;
						r = 2*avg;
						g = 0;
						b = 0;
					}else {
						a =  255;
						r = 255;
						g = 2*avg-255;
						b = 2*avg-255;
					}
				}else if(color == 2) { //green hue
					if(avg < 128) {
						a =  255;
						g = 2*avg;
						r = 0;
						b = 0;
					}else {
						a =  255;
						g = 255;
						r = 2*avg-255;
						b = 2*avg-255;
					}
				}else{ //blue hue
					if(avg < 128) {
						a =  255;
						b = 2*avg;
						r = 0;
						g = 0;
					}else {
						a =  255;
						b = 255;
						r = 2*avg-255;
						g = 2*avg-255;
					}
				}
	
				int p = (a<<24) | (r<<16) | (g<<8) | b;
				imgResult.setRGB(x, y, p);
				}
		}
		return imgResult;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Selection option.
/**************************************************************************************************************************************************************************************/
		
			try {
				// Taking Foreground pic 
				JOptionPane.showMessageDialog(null , "Select an Image.","Image Filters by faraz ahmed 19sw05", JOptionPane.DEFAULT_OPTION);
				JFileChooser choose = new JFileChooser();
				int check = choose.showOpenDialog(null);  
				File file1 = null; 
				if(check == JFileChooser.APPROVE_OPTION) { 
					file1 = choose.getSelectedFile();  
				}
	
				//Get size from main pic
				BufferedImage sizePic = ImageIO.read(file1);
				int width = sizePic.getWidth();		int height = sizePic.getHeight();
				
				BufferedImage img1 = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
				img1 = ImageIO.read(file1);
				
				//Create a duplicated pic to store the result	
				File file = new File("(Output)-"+file1.getName());
				file.createNewFile();
				ImageIO.write(img1, "png", file);
				
				
				BufferedImage imgResult = new BufferedImage(img1.getWidth(),img1.getWidth(),BufferedImage.TYPE_INT_ARGB);
				//Choose filters
				String[] options = {"Apply Negative","Apply Grayscale","Apply Hue"};
				int select = JOptionPane.showOptionDialog(null, "Click to perform the task", "Image Filters by faraz ahmed 19sw05", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
				if(select == 0)
					imgResult = negative(img1);
				else if(select == 1)
					imgResult = grayscale(img1);
				else {
					String[] optionsHue = {"Red Hue","Green Hue","Blue Hue"};
					int selectHue = JOptionPane.showOptionDialog(null, "Choose the Hue color", "Image Filters by faraz ahmed 19sw05", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, optionsHue, optionsHue[0]);
					imgResult = Hue(img1, selectHue + 1);
				}
				File resultImg = new File(file.getName());
				ImageIO.write(imgResult, "png", resultImg);
				JOptionPane.showMessageDialog(null,"Filter applied on an Image successfully...\nFile name: "+file.getName(), "Made By faraz ahmed (19sw05)", JOptionPane.INFORMATION_MESSAGE);
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null,"Error found!","Made By faraz ahmed (19sw05)", JOptionPane.ERROR_MESSAGE);
			}
		
		/**************************************************************************************************************************************************************************************/	
	}

}
