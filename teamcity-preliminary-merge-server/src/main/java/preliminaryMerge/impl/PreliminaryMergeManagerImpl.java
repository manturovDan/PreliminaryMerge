package preliminaryMerge.impl;

import jetbrains.buildServer.log.Loggers;
import jetbrains.buildServer.util.EventDispatcher;
import jetbrains.buildServer.vcs.RepositoryState;
import jetbrains.buildServer.vcs.RepositoryStateListener;
import jetbrains.buildServer.vcs.VcsRoot;
import main.java.preliminaryMerge.PreliminaryMergeManager;
import org.jetbrains.annotations.NotNull;

public class PreliminaryMergeManagerImpl implements PreliminaryMergeManager, RepositoryStateListener {
  public PreliminaryMergeManagerImpl(@NotNull final EventDispatcher<RepositoryStateListener> repositoryStateEvents) {
    Loggers.VCS.debug("PMM was created");
    System.out.println("PMM was created");

    repositoryStateEvents.addListener(this);
  }

  @Override
  public void beforeRepositoryStateUpdate(@NotNull VcsRoot root, @NotNull RepositoryState oldState, @NotNull RepositoryState newState) {
    Loggers.VCS.debug("PM: Catched commit (before): " + oldState.getBranchRevisions() + " > " + newState.getBranchRevisions());
    System.out.println("PM: Catched commit (before): " + oldState.getBranchRevisions() + " > " + newState.getBranchRevisions());
  }

  @Override
  public void repositoryStateChanged(@NotNull VcsRoot root, @NotNull RepositoryState oldState, @NotNull RepositoryState newState) {
    Loggers.VCS.debug("PM: Catched commit (after): " + oldState.getBranchRevisions() + " > " + newState.getBranchRevisions());
    System.out.println("PM: Catched commit (after): " + oldState.getBranchRevisions() + " > " + newState.getBranchRevisions());
  }
}
