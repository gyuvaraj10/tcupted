package com.tests.bootstrap;

import com.google.gson.Gson;
import com.tcup.ted.services.companies.model.TcupCompany;
import com.tcup.ted.services.companies.model.TcupProject;
import org.apache.http.client.utils.DateUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * Created by Yuvaraj on 20/01/2018.
 */
public class SampleClass {

    @Test
    public void test() {

        Date today = new Date();
        Calendar c = Calendar.getInstance();
        c.set(2017,0,1);
        System.out.println(c.getTime().toString());
//        System.out.println(today.toString());
        //script to delete the jobs until n-4
//        def j = Jenkins.instance
//        def ivtSanityView = j.getView('Project IVT and Sanity')
//        def currentRelease= 308
//        def expectedReleaseToKeepUntil = currentRelease-3;
//        ivtSanityView.views.each {   view ->
//                def name = view.name
//                def releaseNumber = name.split("R")[1].split(" ")[0].split("a")[0]
//                if(Integer.valueOf(releaseNumber) < expectedReleaseToKeepUntil) {
//                    view.items.each { v ->
//                        def jobs = v.items
//                            jobs.each { job->
//                                job.delete()
//                        }
//                    }
//                }
//        }

    //script to delete the jobs from Sanity Suite until  n-4
//        def j = Jenkins.instance
//        def ivtSanityView = j.getView('Sanity Suite')
//        def currentRelease= 308
//        def expectedReleaseToKeepUntil = currentRelease-3;
//        ivtSanityView.views.each {   view ->
//                def name = view.name
//            def releaseNumber = name.split("R")[1].split(" ")[0].split("a")[0]
//            if(Integer.valueOf(releaseNumber) < expectedReleaseToKeepUntil) {
//                view.items.each { v ->
//                    if(v instanceof hudson.model.FreeStyleProject) {
//                        v.delete()
//                    }
//                }
//            }
//        }


//        def j = Jenkins.instance
//        def jobs = j.items
//        jobs.each { job->
//            if(!(job instanceof com.cloudbees.hudson.plugins.folder.Folder)){
//                def displayName = job.name;
//                def lastBuild = job.lastBuild;
//                if(lastBuild !=null) {
//                    println(job.name+"," +job.lastBuild.getTime())
//                } else {
//                    println(job.name+"," +job.lastBuild)
//                }
//            }
//        }

    }
}
