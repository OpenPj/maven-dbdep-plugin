package com.sourcesense.maven.dbdep.plugin.dataobject;

/**
 * Data Object for dependency
 * @author piergiorgiolucidi
 * 
 */
public class DependencyDO {

	private String project = "";
	private String environment = "";
	private String name = "";
	private String version = "";

	public String toString() {
		return "DependencyDO | Project: " + this.project + " | Environment: "
				+ this.environment + " | Name: " + this.name + " | Version: "
				+ this.version;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
