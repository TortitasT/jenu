package jenu;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Jenu is a simple menu system for Java that uses annotations to add menu
 * entries.
 */
public class Jenu {

  /**
   * Annotation for menu entries, add this to your menu methods to add them to the
   * menu.
   */
  @Retention(RetentionPolicy.RUNTIME)
  @Target({ ElementType.METHOD })
  public @interface JenuEntry {
    /**
     * The index of the menu entry, this is used to call the entry.
     * 
     * @return int the index of the menu entry
     */
    int index();

    /**
     * The name of the menu entry, this is used to display the entry.
     * 
     * @return String the name of the menu entry
     */
    String name();

    /**
     * NOT IMPLEMENTED. The description of the menu entry, this is used to
     * display the entry.
     * 
     * @return String the description of the menu entry
     */
    String description();
  }

  /**
   * Change this to return the title of your menu
   * 
   * @return String the title of the menu
   */
  public String getTitle() {
    return "Jenu";
  }

  /**
   * Print the title and options of the menu
   */
  public void show() {
    System.out.println("==" + "=".repeat(this.getTitle().length()) + "==");
    System.out.println("| " + this.getTitle() + " |");
    System.out.println("==" + "=".repeat(this.getTitle().length()) + "==");

    var orderedMethods = Arrays.stream(
        this.getClass().getDeclaredMethods()).filter(
            method -> method.getAnnotation(JenuEntry.class) != null)
        .sorted(
            (method1, method2) -> {
              JenuEntry annotation1 = method1.getAnnotation(JenuEntry.class);
              JenuEntry annotation2 = method2.getAnnotation(JenuEntry.class);

              return annotation1.index() - annotation2.index();
            })
        .toArray(Method[]::new);

    for (Method method : orderedMethods) {
      JenuEntry annotation = method.getAnnotation(JenuEntry.class);

      System.out.println(annotation.index() + ". " + annotation.name());
    }
  }

  /**
   * Run specific menu option by index and return the result as the specified
   * type.
   * 
   * @param index      the index of the menu option
   * @param returnType the type to return
   * @param <T>        the type to return
   * @return T the result of the menu option
   * @throws InvocationTargetException
   * @throws IllegalArgumentException
   * @throws IllegalAccessException
   */
  public <T> T run(int index, Class<T> returnType)
      throws Exception {
    for (Method method : this.getClass().getDeclaredMethods()) {
      JenuEntry annotation = method.getAnnotation(JenuEntry.class);
      if (annotation != null && annotation.index() == index) {
        this.before();

        return returnType.cast(method.invoke(this));
      }
    }
    return null;
  }

  /**
   * Run the menu and query the user for input, main method of the menu.
   */
  public void query() {
    while (true) {
      System.out.println("");

      this.show();
      System.out.print("> ");

      try {
        int choice = Integer.parseInt(
            new java.util.Scanner(System.in).nextLine());

        System.out.println("");

        if (!!!this.run(choice, Boolean.class)) {
          break;
        }

      } catch (Exception e) {
        if (e.getCause() == null) {
          System.out.println("Error in input:\n" + e.getMessage());
          continue;
        }

        System.out.println("Error in input:\n" + e.getCause().getMessage());
        continue;
      }
    }
  }

  /**
   * This will run before each entry is called. Override this to add custom.
   */
  public void before() {
    return;
  }
}
