# Jenu

Menu system for the terminal.

## Usage

- Gradle
```bash
repositories {
  maven {
      name = "GitHubPackages"
      url = uri("https://maven.pkg.github.com/TortitasT/jenu")
      credentials {
          username = "TortitasT" ?: System.getenv("USERNAME")
          password = "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" ?: System.getenv("TOKEN")
      }
  }
}

dependencies {
  implementation 'jenu:lib:0.1.6'
}
```