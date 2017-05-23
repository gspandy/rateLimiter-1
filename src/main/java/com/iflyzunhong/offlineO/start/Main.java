package com.iflyzunhong.offlineO.start;

import com.iflyzunhong.slcommon.utils.AppSysTool;
import com.iflyzunhong.slcommon.utils.ConfigTool;
import com.iflyzunhong.slcommon.utils.rmq.OfflineRmqUtil;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 彭家玮
 * @version V1.0
 * @className Tasks
 * @description   启动类,start.sh找这个类进行启动
 * @date 2017/5/16
 */
public class Main {

    private final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) throws UnsupportedEncodingException {
        initModuleName("test");
        String directoryPath = System.getProperty("user.dir")+ ConfigTool.getConfigValue("targetPath");
        File directory = new File(directoryPath);
        String[] child = new String[0];
        File file = null;
        if (directory.isDirectory()) {
            child = directory.list();
        }
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < child.length; i++) {
            if (child != null) {
                String filePath = directoryPath+"/"+child[i];
                list.add(filePath);
            }
        }
        //拼凑好的filePath传入，根据list大小来生成线程
        new TPS().start(list);
    }


    private static void initModuleName(String moduleName) {
        AppSysTool.setModuleName(moduleName);
        //初始化MDC参数
        MDC.put("module", AppSysTool.getModuleName());
        MDC.put("ip", AppSysTool.getIPAddress());
    }
}
