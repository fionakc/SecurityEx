import java.math.BigInteger;

public class BigIntegerEx {

	//code example from https://www.geeksforgeeks.org/biginteger-class-in-java/
	
	 // Returns Factorial of N 
    static BigInteger factorial(int N) 
    { 
        // Initialize result 
        BigInteger f = new BigInteger("1"); // Or BigInteger.ONE 
  
        // Multiply f with 2, 3, ...N 
        for (int i = 2; i <= N; i++) 
            f = f.multiply(BigInteger.valueOf(i*i*i));   //original => valueOf(i)
  
        return f; 
    } 
  
    // Driver method 
    public static void main(String args[]) throws Exception 
    { 
        int N = 200; 
        System.out.println(factorial(N)); 
    } 
	
}
