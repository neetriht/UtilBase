package com.mybatis;

import java.util.List;

/**
 * Created by neetriht on 2017-11-07.
 */

public interface IRASTFILE {
    // public List<RASTFILE> getUserList();

    public void addFile(RASTFILE file);

//        public void updateFile(RASTFILE file);
//
//        public void deleteFile(String fileId);

    public RASTFILE GetonefilesByID(String id);

    public List<RASTFILE> GetAllfiles();
}
