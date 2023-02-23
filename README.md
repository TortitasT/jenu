# Jenu

CLI menu library for Java using Scanner.

## Installation

- Gradle
```bash
dependencies {
    implementation 'eu.tortitas.jenu:lib:1.0.1'
}
```

- Maven
```xml
<dependency>
    <groupId>eu.tortitas.jenu</groupId>
    <artifactId>lib</artifactId>
    <version>1.0.1</version>
</dependency>
```

## Usage

```java
// MyMenu.java

import jenu.Jenu;

public class MyMenu extends Jenu {
    @Override
    public String getTitle() {
        return "My menu";
    }

    @JenuEntry(index = 1, name = "Create something", description = "")
    public boolean createSomething() {
        System.out.println("Creating something...");
        return true;
    }

    @JenuEntry(index = 2, name = "Delete something", description = "")
    public boolean deleteSomething() {
        System.out.println("Deleting something...");
        return true;
    }

    @JenuEntry(index = 0, name = "Exit", description = "")
    public boolean exit() {
        System.out.println("Exiting...");
        return false;
    }
}

// App.java

import jenu.Jenu;

public class App {
    public static void main(String[] args) {
        Jenu menu = new MyMenu();
        menu.query();
    }
}
```

