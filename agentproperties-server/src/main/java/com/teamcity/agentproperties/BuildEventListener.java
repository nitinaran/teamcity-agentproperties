package com.teamcity.agentproperties;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import com.intellij.openapi.diagnostic.Logger;

import jetbrains.buildServer.BuildAgent;
import jetbrains.buildServer.BuildProblemData;
import jetbrains.buildServer.BuildType;
import jetbrains.buildServer.messages.BuildMessage1;
import jetbrains.buildServer.messages.Status;
import jetbrains.buildServer.responsibility.ResponsibilityEntry;
import jetbrains.buildServer.responsibility.TestNameResponsibilityEntry;
import jetbrains.buildServer.serverSide.BuildPromotion;
import jetbrains.buildServer.serverSide.BuildRevision;
import jetbrains.buildServer.serverSide.BuildServerListener;
import jetbrains.buildServer.serverSide.BuildTypeTemplate;
import jetbrains.buildServer.serverSide.SBuild;
import jetbrains.buildServer.serverSide.SBuildAgent;
import jetbrains.buildServer.serverSide.SBuildType;
import jetbrains.buildServer.serverSide.SFinishedBuild;
import jetbrains.buildServer.serverSide.SProject;
import jetbrains.buildServer.serverSide.SProjectFeatureDescriptor;
import jetbrains.buildServer.serverSide.SQueuedBuild;
import jetbrains.buildServer.serverSide.SRunningBuild;
import jetbrains.buildServer.serverSide.STest;
import jetbrains.buildServer.serverSide.TagData;
import jetbrains.buildServer.serverSide.mute.MuteInfo;
import jetbrains.buildServer.serverSide.problems.BuildProblemInfo;
import jetbrains.buildServer.tests.TestName;
import jetbrains.buildServer.users.SUser;
import jetbrains.buildServer.users.User;
import jetbrains.buildServer.vcs.SVcsRoot;
import jetbrains.buildServer.vcs.VcsModification;
import jetbrains.buildServer.vcs.VcsRoot;

public class BuildEventListener implements BuildServerListener {

    public static final Logger Log = Logger.getInstance(BuildEventListener.class.getName());

    private ExecutorService myExecutorService;

    BuildEventListener(ExecutorService myExecutorService) {
        this.myExecutorService = myExecutorService;
        Log.info(BuildEventListener.class.getName() + " initialized");
    }

    @Override
    public void buildTypeAddedToQueue(SQueuedBuild queuedBuild) {

    }

    @Override
    public void buildFinished(SRunningBuild build) {

    }

    @Override
    public void buildInterrupted(SRunningBuild build) {

    }

    @Override
    public void buildRemovedFromQueue(SQueuedBuild queuedBuild, User user, String comment) {

    }

    @Override
    public void serverShutdown() {
        myExecutorService.shutdown();
        while (!myExecutorService.isTerminated()) {
        }
        Log.info("Server shutdown requested, so stopped all the threads");
    }

    @Override
    public void buildStarted(SRunningBuild build) {

    }

    @Override
    public void buildTypeAddedToQueue(SBuildType buildType) {

    }

    @Override
    public void beforeBuildFinish(SRunningBuild runningBuild) {

    }

    @Override
    public void serverStartup() {

    }

    @Override
    public void buildTypeRegistered(SBuildType buildType) {

    }

    @Override
    public void buildTypeUnregistered(SBuildType buildType) {

    }

    @Override
    public void buildTypeMoved(SBuildType buildType, SProject original) {

    }

    @Override
    public void buildTypeTemplateExternalIdChanged(BuildTypeTemplate buildTypeTemplate, String oldExternalId,
            String newExternalId) {

    }

    @Override
    public void buildTypeExternalIdChanged(SBuildType buildType, String oldExternalId, String newExternalId) {

    }

    @Override
    public void projectMoved(SProject project, SProject originalParentProject) {

    }

    @Override
    public void beforeBuildTypeDeleted(String buildTypeId) {

    }

    @Override
    public void buildTypeDeleted(String buildTypeId) {

    }

    @Override
    public void buildTypeTemplateDeleted(String buildTypeTemplateId) {

    }

    @Override
    public void buildTypeTemplateDeleted(BuildTypeTemplate template) {

    }

    @Override
    public void buildTypeActiveStatusChanged(SBuildType buildType) {

    }

    @Override
    public void projectCreated(String projectId, SUser user) {

    }

    @Override
    public void projectExternalIdChanged(SProject project, String oldExternalId, String newExternalId) {

    }

    @Override
    public void projectRemoved(String projectId) {

    }

    @Override
    public void projectRemoved(SProject project) {

    }

    @Override
    public void projectPersisted(String projectId) {

    }

    @Override
    public void projectRestored(String projectId) {

    }

    @Override
    public void projectArchived(String projectId) {

    }

    @Override
    public void projectDearchived(String projectId) {

    }

    @Override
    public void buildTypeTemplatePersisted(BuildTypeTemplate buildTemplate) {

    }

    @Override
    public void buildTypePersisted(SBuildType buildType) {

    }

    @Override
    public void vcsRootRemoved(SVcsRoot root) {

    }

    @Override
    public void vcsRootUpdated(SVcsRoot oldVcsRoot, SVcsRoot newVcsRoot) {

    }

    @Override
    public void vcsRootExternalIdChanged(SVcsRoot vcsRoot, String oldExternalId, String newExternalId) {

    }

    @Override
    public void vcsRootPersisted(SVcsRoot vcsRoot) {

    }

    @Override
    public void vcsRootsPersisted() {

    }

    @Override
    public void projectFeatureAdded(SProject project, SProjectFeatureDescriptor projectFeature) {

    }

    @Override
    public void projectFeatureRemoved(SProject project, SProjectFeatureDescriptor projectFeature) {

    }

    @Override
    public void projectFeatureChanged(SProject project, SProjectFeatureDescriptor before,
            SProjectFeatureDescriptor after) {

    }

    @Override
    public void agentRegistered(SBuildAgent agent, long currentlyRunningBuildId) {

    }

    @Override
    public void agentDescriptionUpdated(SBuildAgent agent) {

    }

    @Override
    public void beforeAgentUnregistered(SBuildAgent agent) {

    }

    @Override
    public void agentUnregistered(SBuildAgent agent) {

    }

    @Override
    public void agentStatusChanged(SBuildAgent agent, boolean wasEnabled, boolean wasAuthorized) {

    }

    @Override
    public void agentRemoved(SBuildAgent agent) {

    }

    @Override
    public void buildPinned(SBuild build, User user, String comment) {

    }

    @Override
    public void buildUnpinned(SBuild build, User user, String comment) {

    }

    @Override
    public void buildQueueOrderChanged() {

    }

    @Override
    public void changesLoaded(SRunningBuild build) {

    }

    @Override
    public void buildChangedStatus(SRunningBuild build, Status oldStatus, Status newStatus) {

    }

    @Override
    public void messageReceived(SRunningBuild build, BuildMessage1 message) {

    }

    @Override
    public void responsibleChanged(SBuildType bt, ResponsibilityEntry oldValue, ResponsibilityEntry newValue) {

    }

    @Override
    public void responsibleChanged(SProject project, TestNameResponsibilityEntry oldValue,
            TestNameResponsibilityEntry newValue, boolean isUserAction) {

    }

    @Override
    public void responsibleChanged(SProject project, Collection<TestName> testNames, ResponsibilityEntry entry,
            boolean isUserAction) {

    }

    @Override
    public void responsibleChanged(SProject project, Collection<BuildProblemInfo> buildProblems,
            ResponsibilityEntry entry) {

    }

    @Override
    public void responsibleRemoved(SProject project, TestNameResponsibilityEntry entry) {

    }

    @Override
    public void beforeEntryDelete(SFinishedBuild entry) {

    }

    @Override
    public void entryDeleted(SFinishedBuild entry) {

    }

    @Override
    public void entriesDeleted(Collection<SFinishedBuild> removedEntries) {

    }

    @Override
    public void entryCreated(SFinishedBuild build) {

    }

    @Override
    public void pluginsLoaded() {

    }

    @Override
    public void changeAdded(VcsModification modification, VcsRoot root, Collection<SBuildType> buildTypes) {

    }

    @Override
    public void cleanupStarted() {

    }

    @Override
    public void cleanupFinished() {

    }

    @Override
    public void serverShutdownComplete() {

    }

    @Override
    public void sourcesVersionReleased(BuildAgent agent) {

    }

    @Override
    public void sourcesVersionReleased(BuildType configuration) {

    }

    @Override
    public void sourcesVersionReleased(BuildType configuration, BuildAgent agent) {

    }

    @Override
    public void labelingFailed(SBuild build, VcsRoot root, Throwable exception) {

    }

    @Override
    public void labelingSucceed(SBuild build, BuildRevision revision) {

    }

    @Override
    public void buildTagsChanged(SBuild build, List<String> oldTags, List<String> newTags) {

    }

    @Override
    public void buildTagsChanged(SBuild build, User user, List<String> oldTags, List<String> newTags) {

    }

    @Override
    public void buildPromotionTagsChanged(BuildPromotion buildPromotion, User user, Collection<TagData> oldTags,
            Collection<TagData> newTags) {

    }

    @Override
    public void buildCommented(SBuild build, User user, String comment) {

    }

    @Override
    public void serverConfigurationReloaded() {

    }

    @Override
	public void testsMuted(MuteInfo muteInfo) {

	}

	@Override
	public void testsUnmuted(SUser user, Map<MuteInfo, Collection<STest>> unmutedGroups) {

	}

	@Override
	public void buildProblemsMuted(MuteInfo muteInfo) {

	}

	@Override
	public void buildProblemsUnmuted(SUser user, Map<MuteInfo, Collection<BuildProblemInfo>> unmutedGroups) {

	}

	@Override
	public void buildProblemsChanged(SBuild build, List<BuildProblemData> before, List<BuildProblemData> after) {

	}

	@Override
	public void statisticValuePublished(SBuild build, String valueTypeKey, BigDecimal value) {

	}

	@Override
	public void buildArtifactsChanged(SBuild build) {

	}
}
