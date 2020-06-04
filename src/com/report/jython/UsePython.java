package com.report.jython;

import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Properties;

public class UsePython {


    public void run(String pythonwithpath) {
        try {
            String s;
            System.out.println("Start....");
            // --caption DDD --proc_name CCC
            System.out.println(pythonwithpath);
            //Process rt = Runtime.getRuntime().exec("python " + pythonwithpath);
            Process rt = Runtime.getRuntime().exec("python " + pythonwithpath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(rt.getInputStream()));
            while ((s = bufferedReader.readLine()) != null) {
                System.out.println(s);
            }
            rt.waitFor();
            bufferedReader.close();
            System.out.println("Done....");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void runJython() {
        String[] arg = {"DDD", "CCC"};
        // PythonInterpreter.initialize(System.getProperties(),
        // System.getProperties(), arg);
        Properties props = new Properties();
        //props.setProperty("python.path", "D:\\ProgramServer\\Python27\\Lib\\site-packages\\;D:\\ProgramServer\\Python27\\Lib\\;D:\\ProgramServer\\Python27\\Lib\\site-packages\\numpy\\core\\;D:\\ProgramServer\\Python27\\Lib\\site-packages\\numpy\\lib\\");
        PythonInterpreter interpreter = new PythonInterpreter();
        System.out.println("Start....");
        interpreter.exec("import sys");
        interpreter.exec("print sys");
        interpreter.set("a", new PyInteger(42));
        interpreter.exec("print a");
        interpreter.exec("x = 2+2");
        PyObject x = interpreter.get("x");
        System.out.println("x: " + x);
        System.out.println("Goodbye, cruel world");


        //interpreter.execfile("D:\\Program Files\\Python_Root\\PyCharm_Root\\LearnSpark\\com\\learn\\science\\TestJ.py");
        PyFunction func = (PyFunction) interpreter.get("abc", PyFunction.class);
//		if (func != null) {
//			PyObject pyobj = func.__call__(new PyString("DDD"), new PyString("CCC"));
//			System.out.println("Get return...." + pyobj.toString());
//		} else {
//			System.out.println("func....");
//		}
        System.out.println("Done....");
    }
}
