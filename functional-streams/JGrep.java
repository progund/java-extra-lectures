import java.io.*;          // IOException, InputStreamReader, BufferedReader, File
import java.nio.file.*;    // Files, Paths
import java.util.*;        // Arrays, List, ArrayList, Set, HashSet
import java.util.regex.*;
import java.util.stream.*; // Stream;
public class JGrep {
  public static void main(String[] args) {
    final Env env = new Env(args);
    if (!env.isValidArguments()) {
      usage();
    }
    Pattern pattern = env.pattern();
    if (!env.readFromFiles()) {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      in.lines()
        .filter(env.pattern().asPredicate())
        .forEach(s -> {System.out.println(s); env.incMatches();});
    } else {
      for(String file : env.files()) {
        try (Stream<String> stream = Files.lines(Paths.get(file))) {
          stream.filter(env.pattern().asPredicate())
            // if there are more than one file in args, prefix each match with the file name:
            .map(s -> {String prefix=env.files().size()==1?"":file.toString() + " "; s=prefix+s; return s;})
            .forEach(s -> {System.out.println(s); env.incMatches();});
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    System.exit(env.exitStatus());
  }
  static class Env {
    private String[] args;
    private Set<String> badFiles = new HashSet<>();
    private int matches;
    public void incMatches(){ matches++; }
    public int numMatches() { return matches; }
    public int exitStatus() {
      return badFiles.size()!=0?2:matches==0?1:0;
    }
    public Env(String[] args) {
      this.args = args;
    }
    public boolean isValidArguments() {
      return args.length > 0;
    }
    public boolean isCaseInsensitive() {
      return Arrays.asList(args).contains("-i");
    }
    public Pattern pattern() {
      if (isCaseInsensitive()) {
        return Pattern.compile(regexp(), Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
      } else {
        return Pattern.compile(regexp());
      }
    }
    public String regexp() {
      String arg;
      String regexp=null;
      for(int i=0;i<args.length;i++) {
        arg = args[i];
        if(arg.equals("-i")) {
          continue;
        }
        // take the first non-option argument
        regexp = arg;
        break;
      }
      return regexp;
    }
    public List<String>files() {
      ArrayList<String>files = new ArrayList<>();
      String arg;
      String regexp=null;
      for(int i=0;i<args.length;i++) {
        arg = args[i];
        if (arg.equals("-i")) {
          continue;
        }
        if (regexp==null) {
          regexp=arg;
        } else {
          if (new File(arg).exists()) {
            files.add(arg);
          } else {
            if (!badFiles.contains(arg)) {
              System.err.println("JGrep: " + arg + ": No such file or directory");
              badFiles.add(arg);
            }
          }
        }
      }
      return files;
    }
    private boolean readFromFiles() {
      return files().size() != 0;
    }
  }
  private static void usage() {
    System.err.println("Usage: java JGrep [-i] PATTERN [FILE]...");
    System.err.println("where: ");
    System.err.println(" -i stands for case insensitive");
    System.err.println("If no files are given as argument, JGrep will read from standard in.");
    System.exit(2);
  }    
}
