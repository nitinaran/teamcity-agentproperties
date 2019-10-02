package com.teamcity.agentproperties;

import java.io.File;
import org.jetbrains.annotations.NotNull;
import jetbrains.buildServer.serverSide.agent.SAgentFileManager;

public class SAgentFileManagerDefaultCallback implements SAgentFileManager.Callback {
    private String myError = null;
    private File myFile = null;

    public void error(@NotNull String reason) {
        myError = reason;
    }

    public void success(@NotNull File file) {
        myFile = file;
    }

    public boolean isComplete() {
        return myError != null || myFile != null;
    }

    public String getError() {
        return myError;
    }

    public File getFile() {
        return myFile;
    }
}
