package com.demo.hessian;

import com.caucho.hessian.client.HessianProxyFactory;
import com.whaty.products.learning.webservice.LearningSpaceWebService;

import java.util.List;
import java.util.Map;

/**
 * Hessian 连接实例
 *
 * @Author: YinXu
 * @Date: 18-11-2 下午4:56
 * @Version 1.0
 */
public class HessainUtilDemo {

    private final static String URL = "http://bnu.kfkc.webtrn.cn/learnspace/webService/learningSpaceWebService";

    public static void main(String[] args) {
        try {
            int i = 0;
            while (true) {
                i++;
                if(i >= 1000){
                    break;
                }
                LearningSpaceWebService lsw = HessainUtilDemo.getlearningSpace(10);
                System.out.println("lsw = " + lsw.hashCode());
                List<Map<String, Object>> list = lsw.getCourseInfoList("ff8080815553a112015571b298a605b4", "bnu");
                System.out.println("list = " + list);
            }




        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static HessianProxyFactory proxyFactory = null;

    public static LearningSpaceWebService getlearningSpace(int timeout) throws Exception{
        //从资源文件中查询链接地址



        if(!"".equals(URL) && URL != null){

            if(proxyFactory == null){
                proxyFactory = new HessianProxyFactory();
                //Nginx使用反向代理时 Hessian 的 411 错误解决方案。
                proxyFactory.setChunkedPost(false);
                proxyFactory.setReadTimeout(timeout*1000);
                proxyFactory.setOverloadEnabled(true);
            }

            LearningSpaceWebService learningSpaceWebService = (LearningSpaceWebService) proxyFactory.create(LearningSpaceWebService.class, URL);
            return learningSpaceWebService;
        }
        return null;


    }
}
