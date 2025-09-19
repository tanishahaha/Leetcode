class Spreadsheet {
    private int[][] grid; // 2D grid to store values of spreadsheet cells (rows x 26 columns for A-Z)

    // Constructor: initializes spreadsheet with given number of rows and 26 columns (A-Z)
    public Spreadsheet(int rows) {
        grid = new int[rows][26];
    }

    // Helper function: converts a cell string like "A1" into row and column indices
    private int[] parseCell(String cell) {
        char colChar = cell.charAt(0);          // First character is the column (e.g., 'A', 'B', 'C'...)
        int col = colChar - 'A';                // Convert column letter to index (A=0, B=1, ..., Z=25)

        int row = Integer.parseInt(cell.substring(1)) - 1; // Extract row number and convert to 0-based index
        return new int[]{row, col};             // Return position as [row, col]
    }

    // Sets the value of a specific cell (e.g., setCell("A1", 10))
    public void setCell(String cell, int value) {
        int[] pos = parseCell(cell);            // Get row and column index
        grid[pos[0]][pos[1]] = value;           // Store the value in grid
    }

    // Resets a specific cell back to 0
    public void resetCell(String cell) {
        int[] pos = parseCell(cell);            // Get row and column index
        grid[pos[0]][pos[1]] = 0;               // Reset value to 0
    }

    // Evaluates a formula like "=A1+5+B2"
    public int getValue(String formula) {
        formula = formula.substring(1);         // Remove leading '=' symbol
        String[] parts = formula.split("\\+");  // Split formula by '+' into tokens
        int sum = 0;

        for (String part : parts) {
            sum += getCellOrValue(part);        // Add either cell value or integer value
        }

        return sum;                             // Return the computed result
    }

    // Helper: checks if a token is a number or a cell reference
    private int getCellOrValue(String token) {
        if (Character.isDigit(token.charAt(0))) {
            // If token starts with a digit → it's a number
            return Integer.parseInt(token);
        } else {
            // Otherwise, it's a cell reference like "A1"
            int[] pos = parseCell(token);
            return grid[pos[0]][pos[1]];
        }
    }
}

/**
 * Example usage:
 * Spreadsheet obj = new Spreadsheet(3);       // 3 rows, columns A-Z
 * obj.setCell("A1", 10);                      // Set cell A1 = 10
 * obj.setCell("B2", 15);                      // Set cell B2 = 15
 * int val1 = obj.getValue("=A1+6");           // Evaluates 10+6 = 16
 * int val2 = obj.getValue("=A1+B2");          // Evaluates 10+15 = 25
 * obj.resetCell("A1");                        // Reset A1 = 0
 * int val3 = obj.getValue("=A1+B2");          // Evaluates 0+15 = 15
 */
