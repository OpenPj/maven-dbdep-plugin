package com.sourcesense.maven.dbdep.plugin.bl;

import java.util.List;

import org.apache.maven.project.MavenProject;

public interface IDbDependenciesMojoBl {

	public static final String BEAN_NAME = "dbDepMojoBl";
	public void write(MavenProject project, String projectName, String environment);
	public String getEnvironment(List activeProfiles);
	
}
