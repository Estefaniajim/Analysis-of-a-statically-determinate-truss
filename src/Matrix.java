import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// Analisis of a statically determinate truss
/*Matrix: [f1,f2,f3,h2,v2,v3]   node1
		  [f1,f2,f3,h2,v2,v3]	node1
		  [f1,f2,f3,h2,v2,v3]	node2
		  [f1,f2,f3,h2,v2,v3]	node2
		  [f1,f2,f3,h2,v2,v3]	node3
		  [f1,f2,f3,h2,v2,v3]	node3
i= equations
j= variables



txt:
n
force 
node applied the force
node 1 equation 1
node 1 equation 2
node 2 equation 1
node 2 equation 2
node 3 equation 1
node 3 equation 2	

We need: 
1 class to create the metrix depending on the txt 
2 class LuDescompositionMethod to solve the system
*/
public class Matrix {
	public int force; //force applied to the structure
	public int nodeApplied; // which node was it applied the force
	public int n; //number of variables
	int [][]mat; //matrix for the luDescomposition method
	int []z;
	LuDescomposition lu; //variable to create an object of the class LuDescomposition
	
	/* Constructor of the matrix
	 * Input: String of the rute of the txt document
	 * ex: "C:/Documents/exFile.txt"
	 */
	public Matrix(String ruta){
		Scanner input;
		//We read the file using the rute
		try {
			input = new Scanner(new File(ruta));
			
		} catch (FileNotFoundException e) {
		      // TODO Auto-generated catch block
		      e.printStackTrace();
		      return;
		    }
		this.n = input.nextInt();
		this.force = input.nextInt();
		this.nodeApplied = input.nextInt();
		this.mat=new int[n][n];
		this.z=new int[n];
		//using n we construct the matrix
        for(int i=0; i<mat.length; i++) { //equations
        	for(int j=0; j<mat.length; j++) { //variables
        		mat[i][j] = input.nextInt(); // add each number to the matrix
        	}
        }
        lu = new LuDescomposition(mat,mat.length); //we initialize 
        getZ();
    	getNewZ();
    	getVariables();
	}
	/* Fuction to get the values of each variable of the structure
     * This only functions in case there are 3 nodes because the variables 
     * depend on the numbers of nodes
   */
    public void getZ() {
    	for(int i = 0; i <z.length; i++) { //iteration 
    		if(i != nodeApplied) { //first we inizialize the z with 0 unless we reach the node thta the forze was applied
    			z[i]=0;
    		}else {
    			z[i]=force;
    		}
    	}
    }
    
    public void getNewZ(){
    	//lower
    	int []newZ = new int [n]; //we create a new int list to save the values of the variables
      for (int i = 0; i < n-1; i++) {
        int decrement = 0; //all positions in the row before the diagonal substacted
        for (int j = 0; j <= i; j++) {
        	if (j == i) { //if it is a digonal
        		newZ[i] = (z[i] - decrement) / lu.lower[i][j]; 
        		} 
        	else { //if is not in the digonal
             decrement += lu.lower[i][j] * newZ[j];
           }
        }
      }
    	this.z=newZ;
    }
    public void getVariables() {
    	int []newZ = new int [n]; //we create a new int list to save the values of the variables
      for (int i = n - 1; i >= 0; i--) {
        int decrement = 0; //all positions in the row before the diagonal substacted
        for (int j = n - 1; j >= i; j--) {
        	 if (j == i) { //if it is a digonal
             newZ[i] = (z[i] - decrement) / lu.upper[i][j]; 
           } else { //if is not in the digonal
             decrement += lu.upper[i][j] * newZ[j];
           }
        }
      }
    	this.z=newZ;
    	for(int i=0; i<n; i++) {
    		int p=i+1;
    		System.out.println("Variable " + p + ": " + z[i]);
    	}
    }
    public static void main(String[] args){
    	
    	Matrix prueba = new Matrix("test.txt");
    	prueba.getVariables();
    }
}
