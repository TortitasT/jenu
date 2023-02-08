package jenu;

public class TestMenu extends Jenu {
  @Override
  public String getTitle() {
    return "Test Menu";
  }

  @JenuEntry(index = 0, name = "Hello", description = "Returns Hello")
  public String shouldReturnHello() {
    return "Hello";
  }

  @JenuEntry(index = 1, name = "World", description = "Returns false, thus exists query")
  public Boolean shouldReturnFalse() {
    return false;
  }
}
