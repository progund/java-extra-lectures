public class MethodCall {
  private String method;
  private int level;
  public MethodCall(String method, int level) {
    this.method = method;
    this.level = level;
  }

  public int level() {
    return level;
  }

  public String method() {
    return method;
  }
  
  protected String indent(int spaces) {
    StringBuffer indent = new StringBuffer();
    for (int i = 0; i < spaces; i++) {
      indent.append(" ");
    }
    return indent.toString();
  }

  public String toString() {
    return new StringBuffer(indent(level))
      .append(method)
      .append(" #")
      .append(level)
      .toString();
  }
    
}

