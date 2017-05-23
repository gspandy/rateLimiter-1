package com.iflyzunhong.offlineO.start;


import com.google.common.util.concurrent.RateLimiter;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.concurrent.TimeUnit;

/**
 * @author 彭家玮
 * @version V1.0
 * @className Tasks
 * @description //TODO 描述这个类的作用
 * @date 2017/5/16
 */
public class Tasks implements Runnable {
    private RateLimiter rateLimiter;
    private String filePath;
    private boolean isRun = true;
    public Tasks(RateLimiter rateLimiter,String path) {
        this.rateLimiter = rateLimiter;
        this.filePath = path;
    }
    @Override
    public void run() {
        while (isRun){
           // getData();
           sendOffline(filePath);
        }
    }
    private void getData(){
        System.out.println(Thread.currentThread().getName()+" runing!");
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void sendOffline(String path){
        File file = new File(path);
        try {
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(file)); // 建立一个输入流对象reader
            BufferedReader br = new BufferedReader(reader); // 建立一个对象，转换文件内容

            String line = null;
            int NO = 1;
            while ((line = br.readLine()) != null) {
                rateLimiter.acquire();
                Logger.getLogger(Tasks.class).info("filename = "+file.getName()+"  number " + NO + "  user = " + line);
                //读取每一行文件，进行操作
                NO++;
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
