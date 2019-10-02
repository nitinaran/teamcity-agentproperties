package com.teamcity.agentproperties;

import javax.management.RuntimeErrorException;
import com.intellij.openapi.diagnostic.Logger;
import org.jetbrains.annotations.NotNull;
import jetbrains.buildServer.serverSide.BuildAgentEx;
import jetbrains.buildServer.serverSide.BuildAgentManager;


public class SAgentProperties implements Runnable {
    private static final Logger Log = Logger.getInstance(SAgentProperties.class.getName());
    private BuildAgentManager myBuildAgentManagerEx;

    private int myAgentId;
    private String myNewConfiguration;

    public SAgentProperties(@NotNull BuildAgentManager myBuildAgentManagerEx) {
        this.myBuildAgentManagerEx = myBuildAgentManagerEx;
    }

    public void updateAgentProperties(int agentId, String newConfiguration) {
        Log.info("Updating agent properites for agent: " + agentId);
        final BuildAgentEx agent = this.myBuildAgentManagerEx.findAgentById(agentId, true);
        Log.debug("Agent name: " + agent.getName());
        boolean updated = ((SUpdateAgentProperties) agent.getRemoteInterface((Class) SUpdateAgentProperties.class))
                .updateAgentProperties(newConfiguration);
        if (updated) {
            Log.info("Updating configuration successful");
        } else {
            throw new RuntimeErrorException(null, "Unable to update the configuration");
        }
    }

    @Override
    public void run() {
        Log.info("Thread: " + Thread.currentThread().getName() + " started - agent: " + myAgentId + " and configuration:\n" + myNewConfiguration);
        updateAgentProperties(myAgentId, myNewConfiguration);
        Log.info("Thread: " + Thread.currentThread().getName() + " completed");
    }

    public int getMyAgentId() {
        return myAgentId;
    }

    public void setMyAgentId(int myAgentId) {
        this.myAgentId = myAgentId;
    }

    public String getMyNewConfiguration() {
        return myNewConfiguration;
    }

    public void setMyNewConfiguration(String myNewConfiguration) {
        this.myNewConfiguration = myNewConfiguration;
    }
}
