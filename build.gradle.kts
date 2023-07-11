plugins {
    `java-library`
    `maven-publish`
    signing
    id("io.github.gradle-nexus.publish-plugin") version ("1.1.0")
}

group = "com.focustech.aigc"
version = "0.0.2-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    withJavadocJar()
    withSourcesJar()
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    api("com.squareup.okhttp3:okhttp:4.10.0")
    api("com.squareup.okhttp3:okhttp-sse:4.10.0")

    implementation("org.slf4j:slf4j-api:2.0.7")
    implementation("org.slf4j:slf4j-simple:2.0.7")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.14.2")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.14.2")

    testImplementation(platform("org.junit:junit-bom:5.9.3"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("commons-io:commons-io:2.11.0")
    testImplementation("org.assertj:assertj-core:3.24.2")
    testImplementation("org.awaitility:awaitility:4.2.0")
}

nexusPublishing {
    repositories {
        sonatype {
            nexusUrl.set(uri("http://maven.mic.com/nexus/content/groups/public"))
            snapshotRepositoryUrl.set(uri("http://maven.mic.com/nexus/content/groups/public"))
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            versionMapping {
                usage("java-api") {
                    fromResolutionOf("runtimeClasspath")
                }
                usage("java-runtime") {
                    fromResolutionResult()
                }
            }
            pom {
                name.set("OpenAI Client")
                description.set("Java http client wrapped around the OkHttp3 library")
                url.set("https://github.com/liangalei-2580/openai-client.git")
//                licenses {
//                    license {
//                        name.set("The Apache License, Version 2.0")
//                        url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
//                    }
//                }
                developers {
                    developer {
                        id.set("lianghonglei")
                        name.set("lianghonglei")
                        email.set("lianghonglei@focuschina.com")
                    }
                }
//                scm {
//                    url.set("http://git.vemic.com/RDCWEB/APLAN/aigc/aigc-code-openai-client.git")
//                    connection.set("http://git.vemic.com/RDCWEB/APLAN/aigc/aigc-code-openai-client.git")
//                }
            }
        }
    }
}

signing {
    val signingKey = "cplan-manager"
    val signingPassword = "123456a"
    useInMemoryPgpKeys(signingKey, signingPassword)
    sign(publishing.publications["mavenJava"])
}

tasks {
    test {
        useJUnitPlatform()
        testLogging {
            events("passed", "skipped", "failed")
        }
    }
    jar {
        from(sourceSets["main"].output)
        from(sourceSets["test"].output) {
            include("**/http/**")
            include("**/util/**")
        }
    }
}