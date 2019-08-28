package com.cp.interview.thread.disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * Description: 事件消费者，也就是一个事件处理器。这个事件处理器简单地把事件中存储的数据打印到终端
 *
 * @author chenpeng
 * @date 2019/8/28 14:28
 */
public class LongEventHandler implements EventHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent longEvent, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("消费者" + longEvent.getValue());

    }
}