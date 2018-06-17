plugins {
    java
    war
    id("org.springframework.boot") version "2.0.3.RELEASE"
    id("io.spring.dependency-management") version "1.0.5.RELEASE"
}

val springBootVersion = "2.0.3.RELEASE"

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

repositories {
    mavenCentral()
}

dependencies {
    compile("org.springframework.boot:spring-boot-starter-web")
    providedRuntime("org.springframework.boot:spring-boot-starter-jetty")
    testCompile("org.springframework.boot:spring-boot-starter-test")
}

dependencyManagement {
    dependencies {
        dependency("org.springframework.boot:spring-boot-starter-web:$springBootVersion") {
            exclude("org.springframework.boot:spring-boot-starter-tomcat")
        }
    }
}
