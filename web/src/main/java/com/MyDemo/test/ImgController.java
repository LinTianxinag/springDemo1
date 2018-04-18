package com.MyDemo.test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
/**
 * desciper::介绍方法名
 * Created by LinTianxiang.
 * Date: 2018/2/27.
 * time:${time}
 *
 * 这里实现Java调用摄像头进行图片捕捉的功能
 *
 */
public class ImgController {


/**
 *  ━━━━━━神兽出没━━━━━━
 *　　　┏┓　　　┏┓
 *　　┏┛┻━━━┛┻┓
 *　    ┃　　　　　　　┃
 *　	  ┃　　　━　　　┃
 *　    ┃　┳┛　┗┳    ┃
 *　　┃　　　　　　　┃
 *　　┃　　　┻　　　┃
 *　　┃　　　　　　　┃
 *　　┗━┓　　　┏━┛Code is far away from bug with the animal protecting
 *　　　　┃　　　┃    神兽保佑,代码无bug
 *　　　　┃　　　┃
 *　　　　┃　　　┗━━━┓
 *　　　　┃　　　　　　　┣┓
 *　　　　┃　　　　　　　┏┛
 *　　　　┗┓┓┏━┳┓┏┛
 *　　　　　┃┫┫　┃┫┫
 *　　　　　┗┻┛　┗┻┛
 *
 * ━━━━━━感觉萌萌哒━━━━━━
 */




    /**
     * 二值化（好用）
     * @param src
     * @param dest
     * @return
     */
    public BufferedImage filter(BufferedImage src, BufferedImage dest) {
        int width = src.getWidth();
        int height = src.getHeight();

        // if ( dest == null )
        // dest = createCompatibleDestImage( src, null );

        int[] inPixels = new int[width * height];
        int[] outPixels = new int[width * height];
        // src = super.filter(src, null); // we need to create new one
        getRGB(src, 0, 0, width, height, inPixels);
        int index = 0;
        int means = getThreshold(inPixels, height, width);
        means=100;
        for (int row = 0; row < height; row++) {
            int ta = 0, tr = 0, tg = 0, tb = 0;
            for (int col = 0; col < width; col++) {
                index = row * width + col;
                ta = (inPixels[index] >> 24) & 0xff;
                tr = (inPixels[index] >> 16) & 0xff;
                tg = (inPixels[index] >> 8) & 0xff;
                tb = inPixels[index] & 0xff;
                if (tr > means) {
                    tr = tg = tb = 255; // white
                } else {
                    tr = tg = tb = 0; // black
                }
                outPixels[index] = (ta << 24) | (tr << 16) | (tg << 8) | tb;
            }
        }
        setRGB(dest, 0, 0, width, height, outPixels);
        return dest;
    }

    private int getThreshold(int[] inPixels, int height, int width) {
        // maybe this value can reduce the calculation consume;
        int inithreshold = 127;
        int finalthreshold = 0;
        int temp[] = new int[inPixels.length];
        for (int index = 0; index < inPixels.length; index++) {
            temp[index] = (inPixels[index] >> 16) & 0xff;
        }
        List<Integer> sub1 = new ArrayList<Integer>();
        List<Integer> sub2 = new ArrayList<Integer>();
        int means1 = 0, means2 = 0;
        while (finalthreshold != inithreshold) {
            finalthreshold = inithreshold;
            for (int i = 0; i < temp.length; i++) {
                if (temp[i] <= inithreshold) {
                    sub1.add(temp[i]);
                } else {
                    sub2.add(temp[i]);
                }
            }
            means1 = getMeans(sub1);
            means2 = getMeans(sub2);
            sub1.clear();
            sub2.clear();
            inithreshold = (means1 + means2) / 2;
        }
        long start = System.currentTimeMillis();
        System.out.println("Final threshold  = " + finalthreshold);
        long endTime = System.currentTimeMillis() - start;
        System.out.println("Time consumes : " + endTime);
        return finalthreshold;
    }

    private static int getMeans(List<Integer> data) {
        int result = 0;
        int size = data.size();
        for (Integer i : data) {
            result += i;
        }
        return (result / size);
    }

    public void setRGB(BufferedImage image, int x, int y, int width,
                       int height, int[] pixels) {
        int type = image.getType();
        if (type == BufferedImage.TYPE_INT_ARGB
                || type == BufferedImage.TYPE_INT_RGB)
            image.getRaster().setDataElements(x, y, width, height, pixels);
        else
            image.setRGB(x, y, width, height, pixels, 0, width);
    }

    public void getRGB(BufferedImage image, int x, int y, int width,
                       int height, int[] pixels) {
        int type = image.getType();
        if (type == BufferedImage.TYPE_INT_ARGB
                || type == BufferedImage.TYPE_INT_RGB)
            image.getRaster().getDataElements(x, y, width, height, pixels);
        else
            image.getRGB(x, y, width, height, pixels, 0, width);
    }
/**
 * 这里根据处理后的图片，生成可以识别的线型对象，然后有不同的对象进行处理。
 *
 * */
    public static void main(String[] args) throws IOException {
        ImgController i = new ImgController();
        BufferedImage bi=ImageIO.read(new File("E:/photo/word5.jpg"));
        ImageIO.write(i.filter(bi, bi), "png", new File("E:/photo/word2Test.png"));
    }

}
