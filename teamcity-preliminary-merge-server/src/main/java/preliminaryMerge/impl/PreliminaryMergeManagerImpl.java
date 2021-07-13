package preliminaryMerge.impl;

import java.util.Map;
import jetbrains.buildServer.log.Loggers;
import jetbrains.buildServer.util.EventDispatcher;
import jetbrains.buildServer.vcs.RepositoryState;
import jetbrains.buildServer.vcs.RepositoryStateListener;
import jetbrains.buildServer.vcs.VcsRoot;
import main.java.preliminaryMerge.PreliminaryMergeManager;
import org.jetbrains.annotations.NotNull;

public class PreliminaryMergeManagerImpl implements PreliminaryMergeManager, RepositoryStateListener {
  public void printTmp(String toPrint) {
    Loggers.VCS.debug(toPrint);
    System.out.println(toPrint);
  }

  public PreliminaryMergeManagerImpl(@NotNull final EventDispatcher<RepositoryStateListener> repositoryStateEvents) {
    printTmp("PMM was created");

    repositoryStateEvents.addListener(this);
  }

  public String branchRevisionsToString(Map<String, String> revs) {
    StringBuilder revisions = new StringBuilder();
    revs.forEach((key, value) -> revisions.append("\n{").append(key).append(":").append(value).append("\n}\n"));
    return revs.toString();
  }

  @Override
  public void beforeRepositoryStateUpdate(@NotNull VcsRoot root, @NotNull RepositoryState oldState, @NotNull RepositoryState newState) {
    printTmp("PM: Catched commit (before): " +
             branchRevisionsToString(oldState.getBranchRevisions()) +
             " > " +
             branchRevisionsToString(newState.getBranchRevisions()));
  }

  @Override
  public void repositoryStateChanged(@NotNull VcsRoot root, @NotNull RepositoryState oldState, @NotNull RepositoryState newState) {
    printTmp("PM: Catched commit (after): " +
             branchRevisionsToString(oldState.getBranchRevisions()) +
             " > " +
             branchRevisionsToString(newState.getBranchRevisions()));
  }
}
