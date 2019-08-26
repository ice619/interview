package com.cp.interview.thread.base.future;


/**
 * Description:
 *
 * @author chenpeng
 * @date 2019/8/26 18:26
 */
public class FutureData implements Data{
    public volatile static boolean ISFLAG = false;
    private RealData realData;

    public synchronized void setRealData(RealData realData){
        // 如果已经获取到结果，直接返回
        if (ISFLAG) {
            return;
        }
        // 如果没有获取到数据,传递真是对象
        this.realData = realData;
        ISFLAG = true;
        // 进行通知
        notify();
    }

    @Override
    public String getRequest() {
        while (!ISFLAG) {
            try {
                wait();
            } catch (Exception e) {

            }
        }
        // 获取到数据,直接返回
        return realData.getRequest();

    }
}