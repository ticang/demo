package com.example.demo.test;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;

/**
 * @author 阿导
 * @CopyRight 万物皆导
 * @Created 2018年09月14日 16:13:00
 */
public class yasuo {
    /**
     * 指定图片宽度和高度和压缩比例对图片进行压缩
     *
     * @param imgsrc 源图片地址
     * @param imgdist 目标图片地址
     */
    public static void reduceImg(String imgsrc, String imgdist) {
        try {
            File srcfile = new File(imgsrc);
            // 检查图片文件是否存在
            if (!srcfile.exists()) {
                System.out.println("文件不存在");
            }
            int[] results = getImgWidthHeight(srcfile);

            int widthdist = results[0];
            int heightdist = results[1];
            // 开始读取文件并进行压缩
            Image src = ImageIO.read(srcfile);

            // 构造一个类型为预定义图像类型之一的 BufferedImage
            BufferedImage tag = new BufferedImage( widthdist/3,  heightdist/3, BufferedImage.TYPE_INT_RGB);

            // 这边是压缩的模式设置
            tag.getGraphics().drawImage(src.getScaledInstance(widthdist/3, heightdist/3, Image.SCALE_SMOOTH), 0, 0, null);

            //创建文件输出流
            FileOutputStream out = new FileOutputStream(imgdist);
            //将图片按JPEG压缩，保存到out中
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(tag);
            //关闭文件输出流
            out.close();
        } catch (Exception ef) {
            ef.printStackTrace();
        }
    }

    /**
     * 获取图片宽度和高度
     *
     * @param file 图片路径
     * @return 返回图片的宽度
     */
    public static int[] getImgWidthHeight(File file) {
        InputStream is = null;
        BufferedImage src = null;
        int result[] = { 0, 0 };
        try {
            // 获得文件输入流
            is = new FileInputStream(file);
            // 从流里将图片写入缓冲图片区
            src = ImageIO.read(is);
            // 得到源图片宽
            result[0] =src.getWidth(null);
            // 得到源图片高
            result[1] =src.getHeight(null);
            is.close();  //关闭输入流
        } catch (Exception ef) {
            ef.printStackTrace();
        }

        return result;
    }

    public static void main(String[] args) {

        String img1 = "C://Users//Administrator//Documents//WeChat Files//wxid_1r6qrhyuwgzd22//FileStorage//File//2021-08//2021年8月沃视频免费大片资源清单+竖版海报//2021年8月沃视频免费大片资源清单+竖版海报//竖版海报//小偷家族.jpg";
        String img2 = "C://Users//Administrator//Documents//WeChat Files//wxid_1r6qrhyuwgzd22//FileStorage//File//2021-08//2021年8月沃视频免费大片资源清单+竖版海报//2021年8月沃视频免费大片资源清单+竖版海报//竖版海报//desc.png";

        File srcfile = new File(img1);


        File distfile = new File(img2);

        System.out.println("压缩前图片大小：" + srcfile.length());
        reduceImg(img1, img2);
        System.out.println("压缩后图片大小：" + distfile.length());

    }

}

