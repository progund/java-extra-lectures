import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class CallStack {
  private List<MethodCall> callStack;
  private static int index = -1;
  private Map<String,Integer> methodToNumber;
  
  public CallStack() {
    callStack = new ArrayList<>();
    methodToNumber = new HashMap<>();
  }

  /**
   * <p>Create a new entry in the history about an exception being caught.</p>
   * <p>This method has to reset the index to the catching method,
   *    and it does so, by looking up the catching method's index.</p>
   * <p>After this, it adds to the history that an exception was caught.</p>
   * @param method The method which catches the exception
   */
  public void catchAfterMethodCall(String method) {
    int catchingMethodIndex = methodToNumber.get(method);
    index = catchingMethodIndex;
    MethodCall currentMethod = callStack.get(index);
    callStack.add(new Catch(currentMethod.method(), index)); // Add this catch
  }
  
  public void backInMethod() {
    index--;
    callStack.add(callStack.get(index)); // Add this method again
    printStackHistory();
  }

  public void enterMethod(String method) {
    index++;
    methodToNumber.put(method, index);
    // Create a new methodCall and add it
    MethodCall call = new MethodCall(method, index);
    callStack.add(call);
    printStackHistory();
  }
  
  public void printStackHistory() {
    StringBuffer stack = new StringBuffer("<<current call history>>\n");
    for (int i = 0; i < callStack.size(); i++) {
      MethodCall mc = callStack.get(i);
      stack
        .append(mc)
        .append(i == callStack.size() - 1 ? " <-- current" : "")
        .append("\n");
    }
    stack.append("<<end current call history>>\n");
    System.out.println(stack);
    try {
      Thread.currentThread().sleep(600);
    } catch (InterruptedException ie) {}
  }
}
