package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub("224e0495cfc644e209857d9f34e87a72cfc3cca4");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("nlkazakova", "java_pft")).commits();
        for (RepoCommit commit: commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
            System.out.println(new RepoCommit.Smart(commit).message());
        }
    }
}
