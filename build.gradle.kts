plugins {
    java
}

group = "org.ansere.hi"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation ("net.sf.trove4j:trove4j:3.0.3")
    implementation("it.unimi.dsi:fastutil:8.5.8")
    implementation("org.scilab.forge:jlatexmath:1.0.7")
    implementation("org.apache.xmlgraphics:batik-dom:1.14")
    implementation("org.apache.xmlgraphics:batik-svggen:1.14")
    implementation("org.apache.xmlgraphics:batik-codec:1.14")
    implementation("org.apache.xmlgraphics:batik-transcoder:1.14")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}