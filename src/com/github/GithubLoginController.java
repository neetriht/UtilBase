package com.github;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.File;
import java.io.IOException;

public class GithubLoginController {


    public void cloneRepository() {
        try {
            Git.cloneRepository().setURI("https://admin@localhost:8443/r/game-of-life.git").setDirectory(new File("D:\\source-code\\temp-1")).call();
        } catch (GitAPIException e) {
            e.printStackTrace();
        }
    }

    public void fetchRepository() {
        Repository rep = null;
        try {
            rep = new FileRepository("D:\\source-code\\temp-1\\.git");
            Git git = new Git(rep);
            git.pull().setRemote("origin").call();
//fetch命令提供了setRefSpecs方法，而pull命令并没有提供，所有pull命令只能fetch所有的分支
            git.fetch().setCredentialsProvider(new UsernamePasswordCredentialsProvider("myname", "password")).call();
            //setRefSpecs("refs/heads/*:refs/heads/*").call();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
