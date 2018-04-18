package com.MyDemo.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * desciper::介绍方法名
 * Created by LinTianxiang.
 * Date: 2018/3/12.
 * time:${time}
 */
public class JSoupTest {
    private static final String manga="E://manga";
    private static final Integer IMG=1;

    /**
     * 这里利用一些jsoup将网页上的漫画用爬虫下载下来
     * 这里有一些东西
     *这里的网址需要进行解析，但是某些网址进行了一定的防盗链处理，这样就无法直接进行调用了，
     * 尽管这样，还是需要进行一定的尝试，如果可以的话，就进行大量漫画资源的抓取。
     * */
    public static void main(String[] args) {
        String imgName=null,urlTemp="http://www.tuku.cc/comic/157/",url=null;
        int chapterBegin=1,chapterEnd=20,pageBegin=1,pageEnd=60;
        try {
            for(int i=chapterBegin;i<=chapterEnd;i++){
                for(int j=pageBegin;j<=pageEnd;j++){
//                    System.out.println("url: "+urlTemp+i+"/p"+j+"/");
                    Document doc = Jsoup.connect(urlTemp+i+"/p"+j+"/").timeout(30000).get();
                    Element img = doc.getElementById("cp_image");//cp_image
                    if(img.attr("src")==""){
                        System.out.println("本章下载完成: "+i+",共 "+(j-1)+" 页");
                        break;
                    }
                    System.out.println(img);
                    download(img,1);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

//        比较高效的写法，可以批量处理相关的资源和节点
////        //（定位精确，以免在页面中选择到其他table里面的tr标签）
//        Elements elements = document.select("div.article-body");
//        //获取所有的tr元素
//        for (Element tr : elements.select("tr")) {



    }

    public static void download(Element e,Integer type){

        String imgName=null;
        switch (type){
            case 1:
                if(e!=null) {
                    try {
                        imgName = e.attr("src");
                        System.out.println(imgName);
                        if (imgName != null) {
                            //连接url
                            URL url = new URL(imgName);
                            URLConnection uri = url.openConnection();
                            //获取数据流
                            InputStream is = uri.getInputStream();
                            //写入数据流,图片名字更改
                            String regnex="卷(.*?)jpg";
                            Pattern p = Pattern.compile(regnex);
                            Matcher m = p.matcher(imgName);
                            if(m.find())
                                imgName=m.group(0).replace("/","_");
//                            ---图片名字更改完成
                            OutputStream os = new FileOutputStream(new File(manga, imgName));
                            byte[] buf = new byte[1024];
                            int l = 0;
                            while ((l = is.read(buf)) != -1) {
                                os.write(buf, 0, l);
                            }
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                break;
            default:
                break;

        }

    }

}
