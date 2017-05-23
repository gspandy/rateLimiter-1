package com.iflyzunhong.offlineO.start;

import com.google.common.util.concurrent.RateLimiter;
import com.iflyzunhong.slcommon.utils.ConfigTool;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 彭家玮
 * @version V1.0
 * @className TPS
 * @description //TODO 描述这个类的作用
 * @date 2017/5/16
 */
public class TPS {
    private static final Logger logger = Logger.getLogger(TPS.class);
    //每秒十个
    private double TPS = Double.parseDouble(ConfigTool.getConfigValue("TPS"));
    private int threadNum = 10;
    public void start(List<String> list) {
        logger.info("filePath sequence= "+list+"    TPS = "+TPS);
        ExecutorService executor = Executors.newFixedThreadPool(list.size());
        //创建给定TPS的rateLimiter
        final RateLimiter rateLimiter = RateLimiter.create(TPS);
        for (int i = 0; i < list.size();i++){
            executor.execute(new Tasks(rateLimiter,list.get(i)));
        }
        executor.shutdown();
    }

//    public static void main(String[] args) {
//        String directoryPath = System.getProperty("user.dir")+ ConfigTool.getConfigValue("targetPath");
//        File directory = new File(directoryPath);
//        String[] child = new String[0];
//        File file = null;
//        if (directory.isDirectory()) {
//            child = directory.list();
//        }
//        List<String> list = new ArrayList<String>();
//        for (int i = 0; i < child.length; i++) {
//            if (child != null) {
//                String filePath = directoryPath+"/"+child[i];
//                list.add(filePath);
//            }
//        }
//
//        //拼凑好的filePath传入，根据list大小来生成线程
//        new TPS().start(list);
//    }
}
