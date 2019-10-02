package com.teamcity.agentproperties;

import com.intellij.openapi.diagnostic.Logger;
import jetbrains.buildServer.agent.BuildAgent;
import jetbrains.buildServer.agent.ServerCommandsHandlersRegistry;

public class AgentPropertiesExtension {

    private BuildAgent myBuildAgent;
    private ServerCommandsHandlersRegistry myServerCommandsHandlersRegistry;

    private static final Logger Log = Logger.getInstance("jetbrains.buildServer." + AgentPropertiesExtension.class.getName());

    public AgentPropertiesExtension(final BuildAgent myBuildAgent, ServerCommandsHandlersRegistry myServerCommandsHandlersRegistry) {
        this.myBuildAgent = myBuildAgent;
        this.myServerCommandsHandlersRegistry = myServerCommandsHandlersRegistry;
        this.myServerCommandsHandlersRegistry.registerCommandsHandler("update-agent-properties", (Object) new UpdateAgentPropertiesImpl(this.myBuildAgent));
        Log.debug(AgentPropertiesExtension.class.getName() + " initialized");
    }
}
