public class Catch extends MethodCall {
  public Catch(String catchingMethod, int level) {
    super(catchingMethod, level);
  }
  public String toString() {
    return new StringBuffer(indent(super.level()))
      .append(super.method())
      .append(" caught an exception")
      .toString();
  }
}
