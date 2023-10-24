public final class Viewport {
    private int row;
    public int getRow() {
        return this.row;
    }
    private int col;
    public int getCol() {
        return this.col;
    }
    private int numRows;
    public int getNumRows() {
        return this.numRows;
    }
    private int numCols;
    public int getNumCols() {
        return this.numCols;
    }

    public Viewport(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
    }
    public void shift(int col, int row) {
        this.col = col;
        this.row = row;
    }
    public Point viewportToWorld(int col, int row) {
        return new Point(col + this.col, row + this.row);
    }

    public boolean contains(Point p) {
        return p.y >= this.row && p.y < this.row + this.numRows && p.x >= this.col && p.x < this.col + this.numCols;
    }
    public Point worldToViewport(int col, int row) {
        return new Point(col - this.col, row - this.row);
    }
}


