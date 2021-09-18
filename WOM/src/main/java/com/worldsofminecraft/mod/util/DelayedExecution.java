package com.worldsofminecraft.mod.util;

import com.worldsofminecraft.util.functional.Executor;

public class DelayedExecution {

    private Executor executor;

    public DelayedExecution(Executor executor) {
        this.executor = executor;
    }

    public void executeAfter(double seconds) {
        final long delay = (long) (seconds * 1000);
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(delay);
                    executor.execute();
                } catch (InterruptedException e) {
                    Utils.getInstance()
                         .getLogger()
                         .error("Could not execute DelayedExecution.");
                    e.printStackTrace();
                }
            }

        });
        t.start();
    }

}
