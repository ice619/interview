package com.cp.interview.thread.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * Description: 需要让Disruptor为我们创建事件，我们同时还声明了一个EventFactory来实例化Event对象。
 *
 * @author chenpeng
 * @date 2019/8/28 14:27
 */
public class LongEventFactory implements EventFactory<LongEvent> {
    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}