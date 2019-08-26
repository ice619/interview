package com.cp.interview.thread.base;

import java.util.concurrent.*;

/**
 * Description:
 *
 * @author chenpeng
 * @date 2019/8/26 17:55
 */
public class TestCallable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Integer> future = executorService.submit(new AddNumberTask());
        System.out.println(future.get());

        if(executorService != null){
            executorService.shutdown();
        }

    }
}

class AddNumberTask implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        System.out.println("AddNumberTask call..");
        Thread.sleep(5000);
        return 5000;
    }
}