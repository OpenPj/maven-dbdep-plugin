package com.sourcesense.maven.dbdep.plugin;

/*
 * Copyright 2008 Piergiorgio Lucidi 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 * http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under 
 * the License is distributed on an 
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and limitations under the License. 
 */

import java.util.List;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.sourcesense.maven.dbdep.plugin.bl.IDbDependenciesMojoBl;

/**
 * 
 * @author <a href="mailto:piergiorgiolucidi@gmail.com">Piergiorgio Lucidi </a>
 * 
 * @goal dbdep
 * 
 * @phase package
 */
public class DbDependenciesMojo extends AbstractMojo {
    
    /**
     * POM
     * 
     * @parameter expression="${project}"
     * @readonly
     * @required
     */
    private MavenProject project;
    
    /**
     * @parameter expression="${project.name}"
     * @readonly
     * @required
     */
    private String name;
    
    private static final String SPRING_CONTEXT = "applicationContext.xml";

    public void execute() throws MojoExecutionException {
        List activeProfiles = project.getActiveProfiles();
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {SPRING_CONTEXT});
        IDbDependenciesMojoBl dbDepMojoBl = (IDbDependenciesMojoBl)context.getBean(IDbDependenciesMojoBl.BEAN_NAME);
        String environment = dbDepMojoBl.getEnvironment(activeProfiles);
        dbDepMojoBl.write(project,name,environment);
    }
    
}
