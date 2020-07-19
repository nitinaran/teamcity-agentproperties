package com.teamcity.agentproperties;

import java.util.concurrent.ExecutorService;

import com.intellij.openapi.diagnostic.Logger;

import jetbrains.buildServer.serverSide.BuildServerAdapter;

public class BuildEventListener extends BuildServerAdapter {

    public static final Logger Log = Logger.getInstance(BuildEventListener.class.getName());

    private ExecutorService myExecutorService;

    BuildEventListener(ExecutorService myExecutorService) {
        this.myExecutorService = myExecutorService;
        Log.info(BuildEventListener.class.getName() + " initialized");
    }

    @Override
    public void serverShutdown() {
        myExecutorService.shutdown();
        while (!myExecutorService.isTerminated()) {
        }
        Log.info("Server shutdown requested, so stopped all the threads");
    }
}
