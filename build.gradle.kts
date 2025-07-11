plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("org.telegram:telegrambots:6.9.7.1")
    implementation("org.jsoup:jsoup:1.21.1")
    implementation("com.google.code.gson:gson:2.13.1")
    // https://mvnrepository.com/artifact/org.projectlombok/lombok
    implementation("org.projectlombok:lombok:1.18.38")
    // https://mvnrepository.com/artifact/org.telegram/telegrambotsextensions
    implementation("org.telegram:telegrambotsextensions:6.9.7.1")
}

tasks.test {
    useJUnitPlatform()
}