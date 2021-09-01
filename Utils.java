import java.util.Random;
import java.io.Console;

public class Utils {
	public static final Random generator = new Random();
	public static final Console console = System.console();
	public static final String NEW_LINE = System.getProperty("line.separator");


    /**
     * This method rotates array of size lines * columns referenced
     * by transformedBoard by 90 degree clockwise. For example, the
     * 3*3 game
     *
     * 1 | 2 | 3
     * ----------
     * 4 | 5 | 6
     * ----------
     * 7 | 8 | 9
     *
     * for which lines = 3 and columns = 3 is represented by the array
     * transformedBoard = {1,2,3,4,5,6,7,8,9}
     *
     * the method rotates it into the game
     *
     * 7 | 4 | 1
     * ----------
     * 8 | 5 | 2
     * ----------
     * 9 | 6 | 3
     *
     * that is, after the method, transformedBoard = {7,4,1,8,5,2,9,6,3}
     *
     * @param lines
     *  the number of lines in the game
     * @param columns
     *  the number of columns in the game
     * @param transformedBoard
     *  Reference to a 1 dimentional array of size columns x lines
     */


    public static void rotate(int lines, int columns, int[] transformedBoard){


            // YOUR CODE HERE

        // Throw a NullPointerException if the transformedBoard integer array that was passed is null
        if(transformedBoard == null) {
            throw new NullPointerException("TransformedBoard is null");
        }
        
        // Throw an IllegalArgumentException if the user enters lines or columns values that are smaller than 1
        if (lines < 1 || columns < 1) {
            throw new IllegalArgumentException("Illegal arguments were inputted");
        }

        // Throw an IllegalArgumentException if the size of the array is not compatible with the parameters lines and columns
        if (lines * columns != transformedBoard.length) {
            throw new IllegalArgumentException("The size of the array must be compatible with the parameters lines and columns");
        }

        // Throw an IllegalArgumentException if the user enters lines and columns values that are not the same.
        // The rotation transformation should not be applied on a non-square board
        if (lines != columns) {
            throw new IllegalArgumentException("The board is not square: lines = " + lines + " and columns = " + columns + " are not the same"); // if the board is not square
        }

        // The idea of this method is to iterate through the board layer by layer, starting with the outermost
        // layer of lines and columns, to the innermost until we hit the center of the board
        for (int row = 0; row < lines; row++) {
            // For each layer, we iterate through the indexes and we do 4 swaps inside the layer.
            // We swap the entire layer so that the layer rotates 90 degrees clockwise
            for (int col = row; col < columns - row - 1; col++) {

                int rightCol = col * columns + (lines - row - 1); // Element from right column
                int bottomRow = (lines - row - 1) * columns + (columns - col - 1); // Element from bottom row
                int leftCol = (columns - col - 1) * columns + row; // Element from leftmost column

                int temp = transformedBoard[row * lines + col]; // Save the current element in the top row
                
                // Displace the elements by 90 degrees clockwise
                transformedBoard[row * lines + col] = transformedBoard[leftCol]; // Place the element from the leftmost column in the cell of the element of the top row
                transformedBoard[leftCol] = transformedBoard[bottomRow]; // Place the element from the bottom row in the cell of the element in the leftmost column
                transformedBoard[bottomRow] = transformedBoard[rightCol]; // Place the element from the right column in the cell of element in the bottom row
                transformedBoard[rightCol] = temp; // Place the saved element from the top row in the cell of the element in the right column
            }
        }

    }

    /**
     * This method does an horizontal symmetry on array of size lines * columns referenced
     * by transformedBoard. For example, the
     * 3*3 game
     *
     *
     * 1 | 2 | 3
     * ----------
     * 4 | 5 | 6
     * ----------
     * 7 | 8 | 9
     *
     * for which lines = 3 and columns = 3 is represented by the array
     * transformedBoard = {1,2,3,4,5,6,7,8,9}
     *
     * the method transforms it into the game   
     *
     * 7 | 8 | 9
     * ----------
     * 4 | 5 | 6
     * ----------
     * 1 | 2 | 3
     *
     * that is, after the method, transformedBoard = {7,8,9,4,5,6,1,2,3}
     *
     * @param lines
     *  the number of lines in the game
     * @param columns
     *  the number of columns in the game
     * @param transformedBoard
     *  Reference to a 1 dimentional array of size columns x lines
     */

    public static  void horizontalFlip(int lines, int columns, int[] transformedBoard){

        // YOUR CODE HERE
        // Throw a NullPointerException if the transformedBoard integer array that was passed is null
        if(transformedBoard == null) {
            throw new NullPointerException("TransformedBoard is null");
        }
        
        // Throw an IllegalArgumentException if the user enters lines or columns values that are smaller than 1
        if (lines < 1 || columns < 1) {
            throw new IllegalArgumentException("Illegal arguments were inputted");
        }

        // Throw an IllegalArgumentException if the size of the array is not compatible with the parameters lines and columns
        if (lines * columns != transformedBoard.length) {
            throw new IllegalArgumentException("The size of the array must be compatible with the parameters lines and columns");
        }

        // The idea of this method is to iterate from row to row while the cells with respect to the horizontal
        // symmetry axis are swapped. This process continues until the middle of the board has been reached (lines / 2).
        // Once this point has been reached, the entire board has been flipped horizontally

        // This for loop iterates from row to row
        for (int row = 0; row < lines / 2; row++) { 
            int oppositeRow = lines - row - 1; // Save the opposite row with respect to horizontal symmetry axis

            // This for loop iterates from column to column inside each row
            for (int col = 0; col < columns; col++) {

                // The next formula is taken from the midterm. From a specific row and column, we can obtain the value of the index
                // of a specific element inside the board

                // First we store the current element in a temporary variable
                int temp = transformedBoard[row * columns + col]; 

                // Then, we swap the elements
                transformedBoard[row * columns + col] = transformedBoard[oppositeRow * columns + col];
                transformedBoard[oppositeRow * columns + col] = temp;
            }
        }
    }

   /**
     * This method does an vertical symmetry on array of size lines * columns referenced
     * by transformedBoard. For example, the
     * 3*3 game
     *
     * 1 | 2 | 3
     * ----------
     * 4 | 5 | 6
     * ----------
     * 7 | 8 | 9
     *
     * for which lines = 3 and columns = 3 is represented by the array
     * transformedBoard = {1,2,3,4,5,6,7,8,9}
     *
     * the method transforms it into the game   
     *
     * 3 | 2 | 1
     * ----------
     * 6 | 5 | 4
     * ----------
     * 9 | 8 | 7
     *
     * that is, after the method, transformedBoard = {3,2,1,6,5,4,9,8,7}
     *
     * @param lines
     *  the number of lines in the game
     * @param columns
     *  the number of columns in the game
     * @param transformedBoard
     *  Reference to a 1 dimentional array of size columns x lines
     */

    public static  void verticalFlip(int lines, int columns, int[] transformedBoard){
 
        // YOUR CODE HERE
        // Throw a NullPointerException if the transformedBoard integer array that was passed is null
        if(transformedBoard == null) {
            throw new NullPointerException("TransformedBoard is null");
        }
        
        // Throw an IllegalArgumentException if the user enters lines or columns values that are smaller than 1
        if (lines < 1 || columns < 1) {
            throw new IllegalArgumentException("Illegal arguments were inputted");
        }

        // Throw an IllegalArgumentException if the size of the array is not compatible with the parameters lines and columns
        if (lines * columns != transformedBoard.length) {
            throw new IllegalArgumentException("The size of the array must be compatible with the parameters lines and columns");
        }

        // The idea of this method is to iterate from column to column while the cells with respect to the vertical
        // symmetry axis are swapped. This process continues until the middle of the board has been reached (columns / 2).
        // Once this point has been reached, the entire board has been flipped vertically

        // This for loop iterates from column to column
        for (int col = 0; col < columns / 2; col++) {
            int oppositeCol = columns - col - 1; // Save the opposite column with respect to vertical symmetry axis

            // This for loop iterates from row to row inside each column
            for (int row = 0; row < lines; row++) {
                
                // The next formula is taken from the midterm. From a specific row and column, we can obtain the value of the index
                // of a specific element inside the board

                // First we store the current element in a temporary variable
                int temp = transformedBoard[row * columns + col];

                // Then, we swap the elements
                transformedBoard[row * columns + col] = transformedBoard[row * columns + oppositeCol];
                transformedBoard[row * columns + oppositeCol] = temp;
            }
        }

    }

    private static void test(int lines, int columns){
    	int[] test;
    	test = new int[lines*columns];
    	for(int i = 0 ; i < test.length; i++){
    		test[i] = i;
    	}
    	System.out.println("testing " + lines + " lines and " + columns + " columns.");
    	System.out.println(java.util.Arrays.toString(test));
    	horizontalFlip(lines,columns,test);
    	System.out.println("HF => " + java.util.Arrays.toString(test));
    	horizontalFlip(lines,columns,test);
    	System.out.println("HF => " + java.util.Arrays.toString(test));
    	verticalFlip(lines,columns,test);
    	System.out.println("VF => " + java.util.Arrays.toString(test));
    	verticalFlip(lines,columns,test);
    	System.out.println("VF => " + java.util.Arrays.toString(test));
    	if(lines == columns){
    		for(int i = 0; i < 4; i++) {
		    	rotate(lines,columns,test);
		    	System.out.println("ROT => " + java.util.Arrays.toString(test));    			
    		}
    	}
    }

    public static void main(String[] args){
    	int[] test;
    	int lines, columns;

    	test(2,2);
    	test(2,3);
    	test(3,3);
    	test(4,3);
    	test(4,4);


    }
}