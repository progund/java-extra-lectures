public class TestCallStack {
  static CallStack cs = new CallStack();
  public static void main(String[] args) {
    cs.enterMethod("main");
    a();
    cs.backInMethod();
  }

  static void a() {
    cs.enterMethod("a()");
    try {
      b();
      cs.backInMethod();
    } catch (RuntimeException e) {
      cs.catchAfterMethodCall("a()");
    }
  }

  static void b() {
    cs.enterMethod("b()");
    try {
      c();
      cs.backInMethod();
    } catch (RuntimeException e) {
      cs.catchAfterMethodCall("b()");
    }
  }

  static void c() {
    cs.enterMethod("c()");
    d();
    throw new RuntimeException();
    //cs.backInMethod();
  }
  static void d() {
    cs.enterMethod("d()");
  }
}
