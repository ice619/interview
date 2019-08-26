package com.cp.interview.thread.base.future;

/**
 * Description:
 * Future模式的核心在于：去除了主函数的等待时间，并使得原本需要等待的时间段可以用于处理其他业务逻辑
 * Futrure模式:对于多线程，如果线程A要等待线程B的结果，那么线程A没必要等待B，直到B有结果，可以先拿到一个未来的Future，等B有结果是再取真实的结果
 *
 * @author chenpeng
 * @date 2019/8/26 20:27
 */
public class FutureClient {

    public Data request(String queryStr){
        FutureData futureData = new FutureData();
        new Thread(new Runnable() {
            @Override
            public void run() {
                RealData realData = new RealData(queryStr);
                futureData.setRealData(realData);
            }
        }).start();
        return futureData;
    }

    public static void main(String[] args) {
        FutureClient futureClient = new FutureClient();
        Data request = futureClient.request("请求参数。。。");
        System.out.println("请求发送成功!");
        System.out.println("执行其他任务...");

        String result = request.getRequest();
        System.out.println("获取到结果..." + result);

    }
}