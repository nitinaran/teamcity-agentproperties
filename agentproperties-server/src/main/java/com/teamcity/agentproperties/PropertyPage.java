package com.teamcity.agentproperties;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import javax.servlet.http.HttpServletRequest;
import com.intellij.openapi.diagnostic.Logger;
import jetbrains.buildServer.serverSide.BuildAgentManager;
import jetbrains.buildServer.serverSide.SBuildAgent;
import jetbrains.buildServer.serverSide.agent.SAgentFileManager;
import jetbrains.buildServer.serverSide.auth.Permission;
import jetbrains.buildServer.users.SUser;
import jetbrains.buildServer.util.WaitFor;
import jetbrains.buildServer.web.openapi.PagePlaces;
import jetbrains.buildServer.web.openapi.PluginDescriptor;
import jetbrains.buildServer.web.openapi.agent.AgentDetailsTab;
import jetbrains.buildServer.web.util.SessionUser;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PropertyPage extends AgentDetailsTab {

    private static final Logger Log = Logger.getInstance(PropertyPage.class.getName());

    private static final String TAB_TITLE = "Agent Properties";
    private static final String PLUGIN_NAME = "agentproperties";
    private static final String PROPERTIES_PAGE = "properties.jsp";

    private SAgentFileManager mySAgentFileManager;

    public PropertyPage(@NotNull PagePlaces pagePlaces, @NotNull PluginDescriptor pluginDescriptor,
            @NotNull BuildAgentManager agentManager, @NotNull SAgentFileManager sAgentFileManager) {
        super(TAB_TITLE, PLUGIN_NAME, pluginDescriptor.getPluginResourcesPath(PROPERTIES_PAGE), pagePlaces,
                agentManager);
        mySAgentFileManager = sAgentFileManager;
        register();
        Log.info(PropertyPage.class.getName() + " initialized");
    }

    public void fillModel(@NotNull Map<String, Object> model, @NotNull HttpServletRequest request) {
        SBuildAgent myAgent = getAgent(request);

        if (!myAgent.isRegistered()) {
            Log.debug("Agent is not registered with Teamcity server");
            return;
        }

        final SAgentFileManagerDefaultCallback callback = new SAgentFileManagerDefaultCallback();
        mySAgentFileManager.requestForFile(myAgent.getName(), "#conf/buildAgent.properties", false, callback);
        new WaitFor(){
            @Override
            protected boolean condition() {
                return callback.isComplete();
            }
        };
        StringBuilder properties = new StringBuilder();
        try {
            Scanner sc = new Scanner(callback.getFile());
            while(sc.hasNextLine()) {
                properties.append(sc.nextLine());
                properties.append("\r\n");
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        model.put("agentProperties", properties.toString());
        model.put("agentId", myAgent.getId());
    }

    @Override
    public boolean isAvailable(@NotNull HttpServletRequest request) {
        return super.isAvailable(request) && checkPermissions(request, Permission.MANAGE_SERVER_INSTALLATION);
    }

    protected boolean checkPermissions(@NotNull HttpServletRequest request, @NotNull Permission permission) {
        SUser user = getUserOrNull(request);
        return user != null && user.isPermissionGrantedGlobally(permission);
    }

    @Nullable
    protected SUser getUserOrNull(@NotNull HttpServletRequest request) {
        return SessionUser.getUser(request);
    }
}
