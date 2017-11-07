package cn.com.tpri.tpcheck.support;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public final class MatrixToImageWriter {

    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    private MatrixToImageWriter() {}


    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }


    public static void writeToFile(BitMatrix matrix, String format, File file)
            throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, file)) {
            throw new IOException("Could not write an image of format " + format + " to " + file);
        }
    }


    public static void writeToStream(BitMatrix matrix, String format, OutputStream stream)
            throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, stream)) {
            throw new IOException("Could not write an image of format " + format);
        }
    }
    
    /**
     * @为图片添加文字
     * @param pressText 文字
     * @param newImg    带文字的图片
     * @param targetImg 需要添加文字的图片
     * @param fontStyle 
     * @param color
     * @param fontSize
     * @param width
     * @param heigh
     */
    public static void pressText(String pressText, String newImg, String targetImg, int fontStyle, Color color, int fontSize, int width, int height) {
        
        //计算文字开始的位置
        //x开始的位置：（图片宽度-字体大小*字的个数）/2
        int startX = (width-(fontSize*pressText.length()))/2;
        //y开始的位置：图片高度-（图片高度-图片宽度）/2
        int startY = height-(height-width)/2;        
        
        try {
            File file = new File(targetImg);
            Image src = ImageIO.read(file);
            int imageW = src.getWidth(null);
            int imageH = src.getHeight(null);
            BufferedImage image = new BufferedImage(imageW, imageH, BufferedImage.TYPE_INT_RGB);
            Graphics g = image.createGraphics();
            g.drawImage(src, 0, 0, imageW, imageH, null);
            g.setColor(color);
            g.setFont(new Font(null, fontStyle, fontSize));
            g.drawString(pressText, startX, startY);
            g.dispose();

            FileOutputStream out = new FileOutputStream(newImg);
            ImageIO.write(image, "JPEG", out);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(image);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 
     * @param content 二维码内容
     * @param path 路径
     * @param picName 文件名
     * @param tmp_path 临时路径
     */
    public static void create(String pressText,String content,String path, String picName, String tmp_path) {
        try {
            //qrcFile用来存放生成的二维码图片（无logo，无文字）
            File qrcFile = new File(tmp_path,picName);
            
            //用来存放带有logo+文字的二维码图片
            String newImageWithText = path+"/"+picName; 
            
            //width:图片完整的宽;height:图片完整的高
            //因为要在二维码下方附上文字，所以把图片设置为长方形（高大于宽）
            int width = 800;
            int height = 900;

            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

            @SuppressWarnings("rawtypes")
            Map hints = new HashMap();
            
            //设置UTF-8， 防止中文乱码
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            //设置二维码四周白色区域的大小
            hints.put(EncodeHintType.MARGIN,1);
            //设置二维码的容错性
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H); 
            
            //画二维码，记得调用multiFormatWriter.encode()时最后要带上hints参数，不然上面设置无效
            BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            
            //开始画二维码
            MatrixToImageWriter.writeToFile(bitMatrix, "jpg", qrcFile);
    
            int font = 18; //字体大小
            int fontStyle = 1; //字体风格

            //在二维码下方添加文字（文字居中）
            pressText(pressText, newImageWithText, qrcFile.getPath(), fontStyle, Color.BLUE, font,  width,  height) ;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

