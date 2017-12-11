/*
 * Compile and run: javac InstanceInit.java && java InstanceInit
 */
class Super{
    public Super(){
	System.out.println("Constructor in Super called!");
    }
}
public class InstanceInit extends Super{
    {
	System.out.println("Instance initializer called!");
    }
    public InstanceInit(){
	System.out.println("InstanceInit constructor called!");
    }

    public static void main(String[] args){
	new InstanceInit();
    }

}
