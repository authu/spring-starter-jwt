# Authu Spring Jwt
Authu Jwt For Spring Cloud    
Provide unified handling auth through jwt   
![status](https://img.shields.io/badge/status-dev-blue.svg)
[ ![Download](https://api.bintray.com/packages/authu/maven-repo/spring-starter-jwt/images/download.svg) ](https://bintray.com/authu/maven-repo/spring-starter-jwt/_latestVersion)

## Note
This project is developing, please don't use it in production env.   

## Env
Require JDK8+

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
            <version>${authu-jwt.version}</version>
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
    <artifactId>spring-starter-jwt-security</artifactId>
</dependency>
```

## Semantic Versioning
MAJOR.MINOR.PATCH\[.BUILD\]     
1. MAJOR version when you make incompatible API changes,
2. MINOR version when you add functionality in a backwards-compatible manner, and
3. PATCH version when you make backwards-compatible bug fixes.
4. \[BUILD\] version nothing improve, the reason could be build fail, and rebuild.
