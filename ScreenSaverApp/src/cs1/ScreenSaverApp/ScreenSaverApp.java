
package cs1.ScreenSaverApp;

import  cs1.app.*;

public class ScreenSaverApp
{
    void printArray( int[] array )
    {
        for (int i = 0; i < array.length; i++)
        {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
    
    void printTable( int[][] table )
    {
        for (int r = 0; r < table.length; r++)      
        {
            printArray(table[r]);                       
        }
    }
    
    int[][] mysteryScreenSaver( int width, int height, int startCol )
    {
        int[][] image = new int[ height ][ width ];
        image[ 0 ][ startCol ] = 1;
        
        //System.out.println("Row length: " + image.length );
        
        for( int row = 1; row < height; row++ )
        {
            for( int col = 1; col < width - 1 ; col++ )
            {
                //System.out.println( "Row: " + row + " " + "Col: " + col );
                if( image[ row - 1 ][ col - 1 ] == 1 && image[ row - 1 ][ col + 1 ] == 0 )
                {
                    image[ row ][ col ] = 1;
                }
                if(image[ row - 1 ][ col - 1 ] == 0 && image[ row - 1 ][ col + 1 ] == 1 )
                {
                    //System.out.println("Row: " + row + " Col: " + col );
                    image[ row ][ col ] = 1;
                }
            }
        }
        return image;
    }
    
    void showMysteryScreenSaver( int width, int height, int n )
    {
        int[][] image = new int[ height ][ width ];
        for( int cycles = 0; cycles < n; cycles++ )
        {
            for( int col = 0; col < width; col++ )
            {
                image = mysteryScreenSaver( width, height, col );
                canvas.drawImage( 150, 250, image );
                
                canvas.sleep( 0.1 );
                canvas.clear();
            }
        }
    }
    
    int countNeighbors( int[][] world, int row, int col )
    {
        int aliveNeighbors = 0;
        
        for( int r = row - 1; r <= row + 1; r++ )
        {
            for( int c = col - 1; c <= col + 1; c++ )
            {
                if( world[ r ][ c ] == 1 )
                {
                    aliveNeighbors++;
                }
            }
        }
        aliveNeighbors -= world[ row ][ col ]; 
        return aliveNeighbors;
    }
    
    int[][] evolveWorld( int[][] world )
    {
        int row = world.length;
        int col = world[ 0 ].length;
        int[][] newWorld = new int[ row ][ col ];
        
        for( int r = 1; r < row - 1; r++ )
        {
            for( int c = 1; c < col - 1; c++ )
            {
                int aliveNeighbors = countNeighbors( world, r, c );
                
                //System.out.println( "Row: " + r + " Col: " + c + " Value " + world[r][c] );
                
                if( world[ r ][c ] == 1 && ( aliveNeighbors == 2 || aliveNeighbors == 3 ) )
                {
                    //System.out.println( " Inside if 1" );
                    newWorld[ r ][ c ] = 1;
                }
                else if( world[ r ][ c ] == 0 && aliveNeighbors == 3 )
                {
                    //System.out.println( " Inside if 2 " );
                    newWorld[ r ][ c ] = 1;
                }
            }
        }
        
        return newWorld;
    }
    
    int[][] generateWorld( int width, int height, int population )
    {
        int[][] newWorld = new int[ height ][ width ];
        
        int row = height / 2;
        
        int colStart = ( width - population ) / 2;
        for( int col = colStart; col < colStart + population; col++ )
        {
            newWorld[ row ][ col ] = 1;
        }
        
        return newWorld;
    }
    
    void showLifeScreenSaver( int width, int height, int population, int n )
    {
        int[][] world = generateWorld( width, height, population );
        
        for( int i = 0; i < n; i++ )
        {
            world = evolveWorld( world );
            canvas.drawImage( canvas.getWidth() / 2, canvas.getHeight() / 2, world );
            
            canvas.sleep( 0.1 );
        }
    }
    
    public void run()
    {
        //int[][] image = mysteryScreenSaver(7, 7, 3);
        //printTable( image );             //  displays
                                 //  0 0 0 1 0 0 0
                                 //  0 0 1 0 1 0 0
                                 //  0 1 0 0 0 1 0
                                 //  0 0 1 0 1 0 0
                                 //  0 1 0 0 0 1 0
                                 //  0 0 1 0 1 0 0
                                 //  0 1 0 0 0 1 0
        
        //image = mysteryScreenSaver(201, 401, 100);
        //canvas.drawImage( 150, 250, image );
        
        showMysteryScreenSaver( 201, 401, 100 );
        int[][] world = {
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 1, 1, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0} };
        
//        int alive = countNeighbors(world, 2, 1);
//        System.out.println("live neighbors for cell [2][1] = " + alive);     // cell [2][1] has only 1 live neighbors
//
//        alive = countNeighbors(world, 2, 2);
//        System.out.println("live neighbors for cell [2][2] = " + alive);     // cell [2][2] has 2 live neighbors
        
//        int[][] world = {
//            {0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0},
//            {0, 1, 1, 1, 1, 1, 0},
//            {0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0} };
//
//        world = evolveWorld(world);    // evolve the world and replace it with new state
//        //printTable(world);             // displays    0 0 0 0 0 0 0
//                                             //0 0 1 1 1 0 0
//                                            //0 0 1 1 1 0 0
////                                              0 0 1 1 1 0 0
////                                              0 0 0 0 0 0 0
//        
//        world = evolveWorld(world);    // evolve the world again
//        printTable(world);             // displays    0 0 0 0 0 0 0
////                                              0 0 1 0 1 0 0
////                                              0 1 0 0 0 1 0
////                                              0 0 1 0 1 0 0
////                                              0 0 0 0 0 0 0
        
//        int[][] world = generateWorld( 7, 5, 3 );
//
//        printTable(world);             // displays    0 0 0 0 0 0 0
//                                             // 0 0 0 0 0 0 0
////                                              0 0 1 1 1 0 0
////                                              0 0 0 0 0 0 0
////                                              0 0 0 0 0 0 0
        
        //int[][] world = generateWorld( 7, 5, 5 );

        //printTable(world);             // displays    0 0 0 0 0 0 0
                                             // 0 0 0 0 0 0 0
                                              //0 1 1 1 1 1 0
                                              //0 0 0 0 0 0 0
                                              //0 0 0 0 0 0 0
        //showLifeScreenSaver( 200, 400, 100, 5 );
    }
}
