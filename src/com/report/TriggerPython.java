package com.report;

import com.GlobalEnv;
import com.report.jython.UsePython;

/**
 * describe:
 *
 * @author scott dai
 * @date 2019/01/11
 */
public class TriggerPython {
    ReportDaily rd = new ReportDaily();

    public void triggerPythonSendMailOut(String send_mail_path) {
        new UsePython().run(send_mail_path);
//        } else {
//            new UsePython().run(path);
//        }
    }
}
