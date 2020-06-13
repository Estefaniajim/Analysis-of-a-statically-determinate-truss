/*Class that uses the Lu descomposition method to decompose a matrix into 
lower and upper traingular matrix */
public class LuDescomposition{
	
	static int Max = 100;
	static String s = "";
	//Matrixes to store the 2 triangular matrix 
	public int [][]lower;
	public int [][]upper;
	
	/*Constructor
	 * Input: the matrix that we want so solve
	 * and the int of how many variables there are in the matrix 
	 */
	public LuDescomposition(int[][] mat, int n) {
		
		//inicialize the 2 matrixes with the nxn
		int [][]lower = new int[n][n]; 
	    int [][]upper = new int[n][n];
	    
	 // Decomposing matrix into Upper and Lower 
	    // triangular matrix 
	    for (int i = 0; i < n; i++) 
	    { 
	        // Upper Triangular 
	        for (int k = i; k < n; k++) 
	        { 
	            // Summation of L(i, j) * U(j, k) 
	            int sum = 0; 
	            for (int j = 0; j < i; j++) 
	                sum += (lower[i][j] * upper[j][k]); 
	            // Evaluating U(i, k) 
	            upper[i][k] = mat[i][k] - sum; 
	        } 
	  
	        // Lower Triangular 
	        for (int k = i; k < n; k++)  
	        { 
	            if (i == k) 
	                lower[i][i] = 1; // Diagonal as 1 
	            else 
	            { 
	                // Summation of L(k, j) * U(j, i) 
	                int sum = 0; 
	                for (int j = 0; j < i; j++) 
	                    sum += (lower[k][j] * upper[j][i]); 
	                // Evaluating L(k, i) 
	                lower[k][i] = (mat[k][i] - sum) / upper[i][i]; 
	            } 
	        } 
	    }
	    for (int i = 0; i < n; i++)  
	    { 
	        // Lower 
	        for (int j = 0; j < n; j++) 
	            System.out.print(" "+ lower[i][j] + "\t");  
	        System.out.print("\t"); 
	  
	        // Upper 
	        for (int j = 0; j < n; j++) 
	            System.out.print(" " + upper[i][j] + "\t"); 
	        System.out.print("\n"); 
	    }
	    this.lower=lower;
	    this.upper=upper;
	  }

}


