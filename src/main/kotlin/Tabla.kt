import kotlin.random.Random

class Tabla (val nivo : Level, val automaticSolver : Boolean){

    var boardEdge : Int = 0
    var numOfMines : Int = 0

    var startRow : Int = 0
    var startCol : Int = 0

    private fun getSize(){
        when(nivo.name){
            "BEGINNER" -> {boardEdge = 9 ; numOfMines = 10}
            "INTERMEDIATE" -> {boardEdge = 16 ; numOfMines = 40}
            "ADVANCED" -> {boardEdge = 24 ; numOfMines = 99}
            else -> 0
        }
    }

    private fun getStartCoords(){
        if (automaticSolver){
            startRow = (0..(boardEdge-1)).random()
            startCol = (0..(boardEdge-1)).random()
        }
    }

    var board = arrayOf<Array<Int>>()
    var visibleBoard = arrayOf<Array<Char>>()

    fun showBoard(type : Int) {

        if(type == 0) {
            for (array in board) {
                for (j in array) {
                    print(j)
                    print("    ")
                }
                println()
            }
        }
        else{
            for (array in visibleBoard) {
                for (j in array) {
                    print(j)
                    print("    ")
                }
                println()
            }
        }
    }



    private fun placeMines(rowBegin: Int, colBegin: Int){

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

            if (mines[x][y] == false && x != rowBegin && y!=colBegin){
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

        //vidljivo
        for (i in 0..(boardEdge-1)){
            var array = arrayOf<Char>()
            for (j in 0..(boardEdge-1)){
                array += '-'
            }
            visibleBoard += array
        }

        placeMines(startRow, startCol)

        for (i in 0..(boardEdge-1)){
            for (j in (0..boardEdge-1)){
                if(board[i][j] != -1)
                    setNumber(i, j)
            }
        }
    }

    fun playMove(row: Int, col: Int) : Boolean{

        if(visibleBoard[row][col] != '-')
            return false

        if(board[row][col] == -1){
            visibleBoard[row][col] = '*'
            println("You lost!")
            showBoard(1)
            return true
        }

        else{
            var adjNum = board[row][col]
            visibleBoard[row][col] = adjNum.toChar()

            if(adjNum == 0){
                if(isValid(row-1,col) && board[row-1][col] == -1)
                    playMove(row-1,col)
                if(isValid(row+1,col) && board[row+1][col] == -1)
                    playMove(row+1,col)
                if(isValid(row,col+1) && board[row][col+1] == -1)
                    playMove(row,col+1)
                if(isValid(row,col-1) && board[row][col-1] == -1)
                    playMove(row,col-1)
                if(isValid(row-1,col+1) && board[row-1][col+1] == -1)
                    playMove(row-1,col+1)
                if(isValid(row-1,col-1) && board[row-1][col-1] == -1)
                    playMove(row-1,col-1)
                if(isValid(row+1,col+1) && board[row+1][col+1] == -1)
                    playMove(row+1,col+1)
                if(isValid(row+1,col-1) && board[row+1][col-1] == -1)
                    playMove(row+1,col-1)
            }
            return false
        }
    }

    



}