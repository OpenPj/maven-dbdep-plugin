Apache Maven Database Dependencies Plugin (dbdep for Maven)

A custom Apache Maven Plugin to store dependencies on a database


Apache Maven Database Dependencies Plugin can be used to store in a easy way all the dependencies of all of your Maven projects in a database.

The goal is simplifying software development monitoring for business intelligence purpose.

The implementation is based on Spring Framework for transactions and queries.

How to install Maven Database Dependencies Plugin (dbdep for Maven)
This page is dedicated to show how to install Maven Db Dependencies Plugin.

Database creation
Download and execute with a SQL client setup_db.sql

Configure your own Maven project
Copy JAR and POM file in a directory and install Maven Database Dependencies Plugin on you local Maven repository in this way (version 0.2):

mvn install:install-file -DgroupId=com.sourcesense -DartifactId=maven-dbdep-plugin -Dversion=0.2 -Dpackaging=jar -Dfile=maven-dbdep-plugin-0.2.jar -DpomFile=maven-dbdep-plugin-0.2.pom
Now you can add this snippet in your own project POM file, in this example snippet you can see how to configure your DBMS:

      <plugin>
        <groupId>com.sourcesense</groupId>
        <artifactId>maven-dbdep-plugin</artifactId>
        <version>0.2</version>
        <executions>
          <execution>
            <id>dbdep</id>
            <goals>
              <goal>dbdep</goal>
            </goals>
            <configuration>
                <jdbcDriverClassName>driver.classname.package.dbms</jdbcDriverClassName>
                <jdbcUrl>jdbc:dbms://ip:port/dbdep</jdbcUrl>
                <jdbcUsername>dbdep</jdbcUsername>
                <jdbcPassword>dbdep</jdbcPassword>
            </configuration>
          </execution>
        </executions>
      </plugin>
