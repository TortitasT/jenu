/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package jenu;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

public class Jenu {
  @Retention(RetentionPolicy.RUNTIME)
  @Target({ ElementType.METHOD })
  public @interface JenuEntry {
    int index();

    String name();

    String description();
  }

  public String getTitle() {
    return "Jenu";
  }

  public void show() {
    System.out.println("==" + "=".repeat(this.getTitle().length()) + "==");
    System.out.println("| " + this.getTitle() + " |");
    System.out.println("==" + "=".repeat(this.getTitle().length()) + "==");

    for (Method method : this.getClass().getDeclaredMethods()) {
      JenuEntry annotation = method.getAnnotation(JenuEntry.class);
      if (annotation != null) {
        System.out.println(annotation.index() + ". " + annotation.name());
      }
    }
  }

  public <T> T run(int index, Class<T> returnType) {
    for (Method method : this.getClass().getDeclaredMethods()) {
      JenuEntry annotation = method.getAnnotation(JenuEntry.class);
      if (annotation != null && annotation.index() == index) {
        try {
          return returnType.cast(method.invoke(this));
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
      }
    }
    return null;
  }

  public void query() {
    while (true) {
      this.show();
      System.out.print("> ");

      int choice = Integer.parseInt(
          new java.util.Scanner(System.in).nextLine());

      try {
        if (!!!this.run(choice, Boolean.class)) {
          break;
        }
      } catch (Exception e) {
        System.out.println("Invalid input");
        continue;
      }
    }
  }
}
