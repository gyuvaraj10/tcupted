package com.tcup.ted.utilities;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Yuvaraj on 22/02/2017.
 */
@Repository
public class TemplateUtil {

    private VelocityEngine engine = new VelocityEngine();
    private Template template = null;
    private VelocityContext context = new VelocityContext();
    private StringWriter writer = new StringWriter();

    public void load(String filePath) throws Exception {
        engine.init(getProperties());
        template = engine.getTemplate(filePath);
    }
    public void put(String templateKey, Object templateValue){
        context.put(templateKey, templateValue);
    }

    public void fillTemplate() throws IOException {
        template.merge(context, writer);
    }

    public String getFilledTemplate(){
        return writer.toString();
    }

    private Properties getProperties(){
        Properties p = new Properties();
        p.setProperty("resource.loader","file");
        p.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.FileResourceLoader");
        p.setProperty("file.resource.loader.path", this.getClass().getProtectionDomain().getCodeSource().getLocation().getFile());
        p.setProperty("file.resource.loader.cache", "false");
        p.setProperty("file.resource.loader.modificationCheckInterval", "0");
        return p;
    }

    public String getStringFromVmFile(Map<String,Object> templateProp, String vmFilePath) throws Exception{
        context = new VelocityContext(templateProp);
        load(vmFilePath);
        fillTemplate();
        return getFilledTemplate();
    }
}
