package exercises;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Random;

import static java.lang.Math.round;
import static java.lang.Math.sqrt;
import static java.lang.System.exit;
import static java.lang.System.out;

/*
 * Program for Conway's game of life.
 * See https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life
 *
 * This is a graphical program using JavaFX to draw on the screen.
 * There's a bit of "drawing" code to make this happen (far below).
 * You don't need to implement (or understand) any of it.
 * NOTE: To run tests must uncomment in init() method, see comment
 *
 * Use smallest step development + functional decomposition!
 *
 * See:
 * - UseEnum
 * - BasicJavaFX (don't need to understand, just if you're curious)
 */
public class Ex5GameOfLife extends Application {

    final Random rand = new Random();

    // Enum type for state of Cells
    enum Cell {
        DEAD, ALIVE
    }

    // This is the *only* accepted modifiable instance variable in program.
    Cell[][] world;

    @Override
    public void init() {
        // test();        // <--------------- Uncomment to test!
        int nLocations = 10000;
        double distribution = 0.15;   // % of locations holding a Cell
        // TODO Create and populate world
        Cell [] data = getCells(nLocations, distribution) ;
        shuffle(data);
        world = toMatrix(data);
    }

    // Implement this method (using functional decomposition)
    // Every involved method should be tested, see below, method test()
    // This method is automatically called by a JavaFX timer (don't need to call it)
    void update() {
        world= nextState (world);
        // TODO Update (logically) the world
    }
    Cell[][] nextState(Cell[][] world){
        int size = world.length;
        Cell[][] tmp = new Cell[size][size];
        for (int row= 0; row < size; row++){
            for (int col = 0; col < size; col++){
                int nAlive = getLivingNeighbours(world, row, col);
                if(world[row][col]== Cell.ALIVE) {
                    if (nAlive < 2 || 3 < nAlive) {
                        tmp[row][col] = Cell.DEAD;
                    } else{
                        tmp[row][col] = world[row][col];
                    }
                } else {
                    if (nAlive == 3) {
                        tmp[row][col] = Cell.ALIVE;
                    }
                }
            }
        }
        return tmp;
    }

    // -------- Write methods below this --------------
    Cell [] getCells(int nCells, double percentAlive){
        Cell[] cells = new Cell [nCells];
        int nAlive = (int) round(percentAlive*nCells);
        int i = 0;
        while (nAlive> 0) {
            cells[i] = Cell.ALIVE;
            nAlive--;
            i++;
        }
        while ( i< nCells){
            cells[i] = Cell.DEAD;
            i++;
        } return cells;
    }

void shuffle (Cell[] arr ) {
    for (int i = arr.length; i > 1; i--) {
        int j = rand.nextInt(arr.length);
        Cell tmp = arr[j];
        arr[j] = arr[i - 1];
        arr[i - 1] = tmp;
    }
}
    Cell[][] toMatrix (Cell[] arr) {
            int size = (int) round(sqrt(arr.length));
            Cell[][] matrix = new Cell[size][size];
            for (int i = 0; i < arr.length; i++) {
                matrix[i / size][i % size] = arr[i];
            }
        return matrix;

    }
    int getLivingNeighbours(Cell[][] world, int row, int col){
        int count = 0;
        for(int r = row -1; r <= row +1; r++){
            for (int c =col -1; c <= col +1; c++){
                if( ! (row == r && col== c) && isValidLocation(world.length, r, c)){
                    if(world[r][c]==Cell.ALIVE){
                        count++;
                    }

                }
            }
        } return count;
    }
boolean isValidLocation(int size, int row, int col){
        return 0<= row && row < size && 0<= col && col<size;
}


    // ---------- Testing -----------------
    // Here you run your tests i.e. call your logic methods
    // to see that they really work
        void test() {
        // Hard coded test world
        Cell[][] testWorld = {
                {Cell.ALIVE, Cell.ALIVE, Cell.DEAD},
                {Cell.ALIVE, Cell.DEAD, Cell.DEAD},
                {Cell.DEAD, Cell.DEAD, Cell.ALIVE},

        };
        int size = testWorld.length;

        // TODO tests!

        exit(0);
    }

    // -------- Below is JavaFX stuff, nothing to do --------------

    void render() {
        gc.clearRect(0, 0, width, height);
        int size = world.length;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                int x = 3 * col + 50;
                int y = 3 * row + 50;
                renderCell(x, y, world[row][col]);
            }
        }
    }

    void renderCell(int x, int y, Cell cell) {
        if (cell == Cell.ALIVE) {
            gc.setFill(Color.RED);
        } else {
            gc.setFill(Color.WHITE);
        }
        gc.fillOval(x, y, 3, 3);
    }

    final int width = 400;   // Size of window
    final int height = 400;
    GraphicsContext gc;

    // Must have public before more later.
    @Override
    public void start(Stage primaryStage) throws Exception {

        // JavaFX stuff
        Group root = new Group();
        Canvas canvas = new Canvas(width, height);
        root.getChildren().addAll(canvas);
        gc = canvas.getGraphicsContext2D();

        // Create a timer
        AnimationTimer timer = new AnimationTimer() {

            long timeLastUpdate;

            // This method called by FX at a certain rate, parameter is the current time
            public void handle(long now) {
                if (now - timeLastUpdate > 300_000_000) {
                    update();
                    render();
                    timeLastUpdate = now;
                }
            }
        };
        // Create a scene and connect to the stage
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Game of Life");
        primaryStage.show();
        timer.start();  // Start simulation
    }

    public static void main(String[] args) {
        launch(args);   // Launch JavaFX
    }
}