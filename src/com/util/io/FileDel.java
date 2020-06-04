package com.util.io;

import java.io.File;

public class FileDel {
	public void checkdeleteFile(String filename) {
		File file = new File(filename);
		if (file.exists())
			file.delete();
	}
}
