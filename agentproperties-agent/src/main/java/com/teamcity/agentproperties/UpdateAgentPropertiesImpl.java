package com.teamcity.agentproperties;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.intellij.openapi.diagnostic.Logger;
import org.jetbrains.annotations.NotNull;
import jetbrains.buildServer.agent.BuildAgent;

public class UpdateAgentPropertiesImpl implements SUpdateAgentProperties {
    public static final String HANDLER_NAME = "update-agent-properties";

    private static final Logger Log = Logger.getInstance("jetbrains.buildServer." + UpdateAgentPropertiesImpl.class.getName());

    private BuildAgent myBuildAgent;
    private static final String CONFFILE = "conf\\buildAgent.properties";

    public UpdateAgentPropertiesImpl(@NotNull BuildAgent myBuildAgent) {
        this.myBuildAgent = myBuildAgent;
        Log.debug(UpdateAgentPropertiesImpl.class.getName() + " initialized");
    }

    @Override
    public Boolean updateAgentProperties(final String agentProperties) {
        Log.info("Update agent properties called");
        // Log.info("Updated configuration is: " + agentProperties);
        Path myConfFile = Paths.get(myBuildAgent.getConfiguration().getAgentHomeDirectory().toString(), CONFFILE);
        try {
            BufferedWriter bufwriter = new BufferedWriter(new FileWriter(myConfFile.toString()));
            bufwriter.write(agentProperties);
            bufwriter.close();
        } catch (Exception e) {
            System.out.println("Error occured while attempting to write to file: " + e.getMessage());
            return false;
        }
        Log.info("UpdateAgentPropertiesImpl::Updating configuration successful");
        return true;
    }
}
