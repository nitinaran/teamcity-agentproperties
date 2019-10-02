package com.teamcity.agentproperties;

import jetbrains.buildServer.controllers.BaseFormXmlController;
import jetbrains.buildServer.serverSide.BuildAgentManager;
import jetbrains.buildServer.serverSide.BuildServerListener;
import jetbrains.buildServer.serverSide.SBuildAgent;
import jetbrains.buildServer.serverSide.SBuildServer;
import jetbrains.buildServer.util.EventDispatcher;
import jetbrains.buildServer.web.openapi.PluginDescriptor;
import jetbrains.buildServer.web.openapi.WebControllerManager;

import jetbrains.buildServer.web.openapi.agent.AgentFinderUtil;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.servlet.ModelAndView;

import java.util.concurrent.ExecutorService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.intellij.openapi.diagnostic.Logger;


public class Controller extends BaseFormXmlController {

    private static final Logger Log = Logger.getInstance(Controller.class.getName());

    private static final String CONTROLLER_PATH = "properties.html";
    private PluginDescriptor myPluginDescriptor;
    private SAgentProperties mySAgentProperties;
    private BuildAgentManager myBuildAgentManager;
    private ExecutorService myExecutorService;

    public Controller(@NotNull SBuildServer server, @NotNull WebControllerManager webControllerManager,
            @NotNull PluginDescriptor myPluginDescriptor, @NotNull SAgentProperties mySAgentProperties,
            @NotNull BuildAgentManager myBuildAgentManager, @NotNull SAgentPropertiesPool mySAgentPropertiesPool,
            @NotNull EventDispatcher<BuildServerListener> myEventDispatcher) {
        super(server);
        this.myPluginDescriptor = myPluginDescriptor;
        this.mySAgentProperties = mySAgentProperties;
        this.myBuildAgentManager = myBuildAgentManager;
        this.myExecutorService = mySAgentPropertiesPool.getMyExecutorService();
        BuildEventListener buildEventListener = new BuildEventListener(myExecutorService);
        myEventDispatcher.addListener(buildEventListener);
        webControllerManager.registerController(this.myPluginDescriptor.getPluginResourcesPath(CONTROLLER_PATH), this);
        Log.info(Controller.class.getName() + " initialized at path: " + CONTROLLER_PATH);
    }

    protected void doPost(@NotNull HttpServletRequest httpServletRequest, @NotNull HttpServletResponse httpServletResponse, @NotNull Element element) {
        Log.debug("Posting the new configuration to the agent to update");

        SBuildAgent agent = AgentFinderUtil.findAgent(httpServletRequest, myBuildAgentManager);

        final String newConfiguration = httpServletRequest.getParameter("agentPropertiesFile");
        try {
            SAgentProperties worker = new SAgentProperties(myBuildAgentManager);
            worker.setMyAgentId(agent.getId());
            worker.setMyNewConfiguration(newConfiguration);
            myExecutorService.execute(worker);
            mySAgentProperties.updateAgentProperties(agent.getId(), newConfiguration);
        } catch (NumberFormatException e) {
            Log.error("Invalid agent id: " + agent.getId());
            return;
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/agentDetails.html?id=" + agent.getId() + "&tab=agentSummary");
        } catch (java.io.IOException ioException) {
            Log.error(ioException);
        }
    }

    protected ModelAndView doGet(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
        return null;
    }
}
