import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class MyQRTest {

	public static void main(String[] args) {


		String myCodeText = "http://www.blah.com/";

		String filePath = "myqrcode.png";
		int size = 27;
		String fileType = "png";
		File myFile = new File(filePath);
		try {

			Map<EncodeHintType, Object> hintMap = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
			hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");

			// Now with zxing version 3.2.1 you could change border size (white border size to just 1)
			hintMap.put(EncodeHintType.MARGIN, 1); /* default = 4 */
			hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			BitMatrix byteMatrix = qrCodeWriter.encode(myCodeText, BarcodeFormat.QR_CODE, size,
					size, hintMap);
			int CrunchifyWidth = (byteMatrix.getWidth())*10;
			BufferedImage image = new BufferedImage(CrunchifyWidth, CrunchifyWidth,
					BufferedImage.TYPE_INT_RGB);
			image.createGraphics();

			Graphics2D graphics = (Graphics2D) image.getGraphics();
			graphics.setColor(Color.WHITE);
			graphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth);
			graphics.setColor(Color.BLACK);

			for (int i = 0; i < size; i++) {

				for (int j = 0; j < size; j++) {

				   // Color color = new Color(image.getRGB(j, i));
				    if(j>8) {
			            graphics.setColor(Color.RED);
				    }
				    if (j<=8) {
                        graphics.setColor(Color.BLACK);
				    }
				    if(j>16) {
                        graphics.setColor(Color.BLUE);

				    }
					if (byteMatrix.get(i, j)) {
						graphics.fillRect(i*10, j*10, 10, 10);
					}
				}
			}





			String imagePath= "Animal-Face-300px.png";
			Image animal = ImageIO.read(new File(imagePath));

            graphics.setColor(Color.WHITE);
			graphics.fill(new Rectangle2D.Double((CrunchifyWidth/2-25), (CrunchifyWidth/2-25), 50,50));
			graphics.drawImage(animal, (CrunchifyWidth/2-25), (CrunchifyWidth/2-25), 50, 50, null);


//
//            graphics.setColor(Color.RED);
//			  graphics.fill(new Ellipse2D.Double(30,30,40,40));
//            graphics.fill(new Rectangle2D.Double(100,100,50,50));
//            graphics.fillRect(160, 160, 30, 30);



			ImageIO.write(image, fileType, myFile);
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("\n\nYou have successfully created QR Code.");
	}
	public void colorRecursive(int a, int b, BitMatrix m ) {

	    if (!m.get(a+1, b)) {
	        colorRecursive(a+1,b,m);
	    }
	    if(!m.get(a, b+1)) {
	           colorRecursive(a,b+1,m);
	    }
	    if (!m.get(a-1, b)) {
            colorRecursive(a+1,b,m);
        }
        if(!m.get(a, b-1)) {
               colorRecursive(a,b+1,m);
        }

	}

}

