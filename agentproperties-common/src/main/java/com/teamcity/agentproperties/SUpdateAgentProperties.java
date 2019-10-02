package com.teamcity.agentproperties;

import jetbrains.buildServer.remote.RemoteHandler;

@RemoteHandler(handleName = "update-agent-properties")
public interface SUpdateAgentProperties {
    public static final String HANDLER_NAME = "update-agent-properties";
    Boolean updateAgentProperties(final String agentProperties);
}
