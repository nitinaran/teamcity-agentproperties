package com.teamcity.agentproperties;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.intellij.openapi.diagnostic.Logger;

class SAgentPropertiesPool {

    private static final Logger Log = Logger.getInstance(SAgentPropertiesPool.class.getName());

    private ExecutorService myExecutorService;
    private final int NUM_THREADS = 5;

    SAgentPropertiesPool() {
        myExecutorService = Executors.newFixedThreadPool(NUM_THREADS);
        Log.debug(myExecutorService.toString());
        Log.info(SAgentPropertiesPool.class.getName() + " initialized");
    }

    ExecutorService getMyExecutorService() {
        return myExecutorService;
    }

}
