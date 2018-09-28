# Authu Spring Jwt
Authu Jwt For Spring Cloud    
Provide unified handling auth through jwt   

## How to use ?
Resolving artifacts using Maven   
```xml
<repositories>
    <repository>
        <id>bintray-authu-maven-repo</id>
        <name>bintray</name>
        <url>https://dl.bintray.com/authu/maven-repo</url>
        <snapshots>
            <enabled>false</enabled>
        </snapshots>
    </repository>
</repositories>
<pluginRepositories>
    <pluginRepository>
        <id>bintray-authu-maven-repo</id>
        <name>bintray-plugins</name>
        <url>https://dl.bintray.com/authu/maven-repo</url>
        <snapshots>
            <enabled>false</enabled>
        </snapshots>
    </pluginRepository>
</pluginRepositories>
```

Import dependencyManagement
```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>io.authu</groupId>
            <artifactId>spring-starter-jwt</artifactId>
            <version>0.1.0</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

Add dependency
```xml
<dependency>
    <groupId>io.authu</groupId>
    <artifactId>spring-starter-jwt-core</artifactId>
</dependency>
```

Example    
<https://github.com/authu/spring-cloud-examples/tree/authu-jwt>

## Semantic Versioning
MAJOR.MINOR.PATCH    
In develop MAJOR.MINOR.PATCH.BUILD   
1. MAJOR version when you make incompatible API changes,
2. MINOR version when you add functionality in a backwards-compatible manner, and
3. PATCH version when you make backwards-compatible bug fixes.
4. BUILD version when you deploy, version should add one.
