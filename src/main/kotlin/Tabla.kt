import kotlin.random.Random

class Tabla (val nivo : Level){

    var boardEdge : Int = 0
    var numOfMines : Int = 0

    private fun getSize(){
        when(nivo.name){
            "BEGINNER" -> {boardEdge = 9 ; numOfMines = 10}
            "INTERMEDIATE" -> {boardEdge = 16 ; numOfMines = 40}
            "ADVANCED" -> {boardEdge = 24 ; numOfMines = 99}
            else -> 0
        }
    }
    var board = arrayOf<Array<Int>>()

    private fun placeMines(){

        var mines = arrayOf<Array<Boolean>>()

        for (i in 0..(boardEdge-1)){
            var array = arrayOf<Boolean>()
            for (j in 0..(boardEdge-1)){
                array += false
            }
            mines += array
        }

        var minesLeft = numOfMines
        while (minesLeft > 0){

            var x = (0..(boardEdge-1)).random()
            var y = (0..(boardEdge-1)).random()


            if (mines[x][y] == false){
                mines[x][y] = true
                board[x][y] = -1
                minesLeft--

            }
        }
    }

    private fun isValid(row : Int, col:Int) : Boolean{
        if(row>=0 && row<boardEdge && col>=0 &&col<boardEdge)
            return true
        else return false
    }

    private fun setNumber(row : Int, col: Int){

        var adjacentMines = 0

        /*
       Count all the mines in the 8 adjacent
       cells

           N.W   N   N.E
             \   |   /
              \  |  /
           W----Cell----E
                / | \
              /   |  \
           S.W    S   S.E

       Cell-->Current Cell (row, col)
       N -->  North        (row-1, col)
       S -->  South        (row+1, col)
       E -->  East         (row, col+1)
       W -->  West         (row, col-1)
       N.E--> North-East   (row-1, col+1)
       N.W--> North-West   (row-1, col-1)
       S.E--> South-East   (row+1, col+1)
       S.W--> South-West   (row+1, col-1)
   */

        if(isValid(row-1,col) && board[row-1][col] == -1)
            adjacentMines++
        if(isValid(row+1,col) && board[row+1][col] == -1)
            adjacentMines++
        if(isValid(row,col+1) && board[row][col+1] == -1)
            adjacentMines++
        if(isValid(row,col-1) && board[row][col-1] == -1)
            adjacentMines++
        if(isValid(row-1,col+1) && board[row-1][col+1] == -1)
            adjacentMines++
        if(isValid(row-1,col-1) && board[row-1][col-1] == -1)
            adjacentMines++
        if(isValid(row+1,col+1) && board[row+1][col+1] == -1)
            adjacentMines++
        if(isValid(row+1,col-1) && board[row+1][col-1] == -1)
            adjacentMines++

        board[row][col] = adjacentMines

    }


    fun initializeBoard(){
        getSize()

        //sve nule u matrici
        for (i in 0..(boardEdge-1)){
            var array = arrayOf<Int>()
            for (j in 0..(boardEdge-1)){
                array += 0
            } 
            board += array
        }

        placeMines()

        for (i in 0..(boardEdge-1)){
            for (j in (0..boardEdge-1)){
                if(board[i][j] != -1)
                    setNumber(i, j)
            }
        }




    }



}