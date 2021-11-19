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
    printTmp("PM2M was created");
    repositoryStateEvents.addListener(this);
  }

  public String branchRevisionsToString(Map<String, String> revs) {
    StringBuilder revisions = new StringBuilder();
    revs.forEach((key, value) -> revisions.append("\n{\n\t").append(key).append(" : ").append(value).append("\n}"));
    return revisions.append("\n").toString();
  }

  @Override
  public void beforeRepositoryStateUpdate(@NotNull VcsRoot root, @NotNull RepositoryState oldState, @NotNull RepositoryState newState) {

  }

  @Override
  public void repositoryStateChanged(@NotNull VcsRoot root, @NotNull RepositoryState oldState, @NotNull RepositoryState newState) {
    printTmp("PM2: Catched commit (after): " +
             branchRevisionsToString(oldState.getBranchRevisions()) +
             " > " +
             branchRevisionsToString(newState.getBranchRevisions()));


  }
}
