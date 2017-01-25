package org.yrgo.streams;
import java.util.Scanner;
import java.io.*;
import java.nio.file.*;
public class Jcat{
  public static void main(String[] args) throws Exception{
    /*
      Alternative:
      if(System.console() == null) -> use pipe
     */
    if(args.length != 0){
      for(int i = 0; i<args.length; i++){
        /*
        BufferedReader in = new BufferedReader
          (new FileReader(new File(args[i])));
        */
        BufferedReader in = Files.newBufferedReader(Paths.get(args[i]));
        String line;
        while( (line=in.readLine())!=null){
          System.out.println(line);
        }
      }
    }else{
      Scanner sc = new Scanner(System.in);
      while( sc.hasNext()){
        System.out.println(sc.nextLine());
      }
    }
  }
}
