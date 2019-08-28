package com.cp.interview.thread.disruptor;

/**
 * Description: 定义事件event  通过Disruptor 进行交换的数据类型。
 * 首先声明一个Event来包含需要传递的数据
 * @author chenpeng
 * @date 2019/8/28 14:24
 */
public class LongEvent {
    private Long value;

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

}