import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
//import org.springframework.boot.gradle.tasks.run.BootRun    //추가

plugins {
    id("org.springframework.boot") version "2.6.6"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.asciidoctor.convert") version "1.5.8"
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.spring") version "1.6.10"    //코틀린 언어로 스프링을 사용
    kotlin("plugin.jpa") version "1.6.10"   //JPA 사용
    kotlin("kapt") version "1.6.10" //kapt annotation processing for kotlin (코틀린이 자바의 어노테이션을 처리할 때 kotlin 파일의 어노테이션 처리를 포함, JVM 기동 시 kotlin 의 어노테이션을 포함)
    idea
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

noArg{
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

extra["snippetsDir"] = file("build/generated-snippets")

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa") //jpa
    implementation("org.springframework.boot:spring-boot-starter-jdbc") //db connect
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.2.2") // Mybatis
    implementation("org.springframework.boot:spring-boot-starter-data-redis") // redis
    implementation("org.springframework.boot:spring-boot-starter-cache")    //redis
    implementation("org.apache.commons:commons-pool2")  //redis
    implementation("com.querydsl:querydsl-jpa") //querydsl
    implementation("com.querydsl:querydsl-apt")
    implementation("org.projectlombok:lombok:1.18.22") //querydsl
    kapt("com.querydsl:querydsl-apt:5.0.0:jpa") //querydsl

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    developmentOnly("org.springframework.boot:spring-boot-devtools")
//    runtimeOnly("com.h2database:h2")
    runtimeOnly("mysql:mysql-connector-java")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor(group = "com.querydsl", name = "querydsl-apt", classifier = "jpa") //entity 가 선언되어 있는 클래스를 QClass 로 생성, default 로 build 폴더 하위 생성)
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

idea{
    module{
        val kaptMain = file("build/generated/source/kapt/main")
        sourceDirs.add(kaptMain)
        generatedSourceDirs.add(kaptMain)
    }
}

//query dsl
//build 폴더 하위에 생성된 QClass 를 프로젝트 내부에서 impoort 할 수 있도록 도와주는 설정
tasks.register("generatedQueryDsl") {
    dependsOn(tasks.clean)
    dependsOn(tasks.compileKotlin)
}
