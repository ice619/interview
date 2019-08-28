package com.cp.interview.thread.disruptor;
import	java.nio.ByteBuffer;

import com.lmax.disruptor.RingBuffer;

/**
 * Description: 定义生产者发送事件
 *
 * @author chenpeng
 * @date 2019/8/28 14:31
 */
public class LongEventProducer {
    public final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer byteBuffer){
        long sequence = ringBuffer.next();
        Long data = null;
        try {
            LongEvent longEvent = ringBuffer.get(sequence);
            data = byteBuffer.getLong(0);
            longEvent.setValue(data);

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("生产者准备发送数据:" + sequence);
            ringBuffer.publish(sequence);
        }

    }
}