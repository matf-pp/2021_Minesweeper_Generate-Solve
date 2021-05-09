class Tabla (val nivo : Level, val automaticSolver : Boolean) {

    var boardEdge: Int = 0
    var numOfMines: Int = 0

    var startRow: Int = 0
    var startCol: Int = 0

    var isFirst : Boolean = true

    private var minesLeft : Int = 0
    var flagsLeft: Int = 0

    var isDone : Boolean = false

    private fun getSize() {
        when (nivo.name) {
            "BEGINNER" -> {
                boardEdge = 9; numOfMines = 10; minesLeft = 10; flagsLeft = 10
            }
            "INTERMEDIATE" -> {
                boardEdge = 16; numOfMines = 40; minesLeft = 40; flagsLeft = 40
            }
            "ADVANCED" -> {
                boardEdge = 24; numOfMines = 99; minesLeft = 99; flagsLeft = 99
            }
        }
    }

    fun getStartCoords() {
        if (automaticSolver) {
            startRow = (0..(boardEdge - 1)).random()
            startCol = (0..(boardEdge - 1)).random()
        }
    }

    var boardIgrac = arrayOf<Array<Int>>()
    var boardRacunar = arrayOf<Array<Int>>()
    var visibleBoard = arrayOf<Array<Char>>()
    var mines = arrayOf<Array<Boolean>>()

    fun showBoard(type: Int): String {
        val tekst = StringBuilder()
        val board = when (type) {
            0 -> boardIgrac
            1 -> visibleBoard
            2 -> boardRacunar
            else -> error("Slucaj nije pokriven")
        }

        for (array in board) {
            for (j in array) {
                tekst.append(if (j is Int && j >= 0) " " else "")
                tekst.append(j)
                tekst.append(" ")
            }
            tekst.append("\n")
        }
        return tekst.toString()
    }


    private fun placeMines(rowBegin: Int, colBegin: Int) {

        var minesLeft = numOfMines
        while (minesLeft > 0) {

            var x = (0..(boardEdge - 1)).random()
            var y = (0..(boardEdge - 1)).random()

            if (mines[x][y] == false && x != rowBegin && y != colBegin) {
                mines[x][y] = true
                boardIgrac[x][y] = -1
                minesLeft--
            }
        }
    }

    private fun isValid(row: Int, col: Int): Boolean {
        if (row >= 0 && row < boardEdge && col >= 0 && col < boardEdge)
            return true
        else return false
    }

    private fun setNumber(row: Int, col: Int) {

        var adjacentMines = 0

        if (isValid(row - 1, col) && boardIgrac[row - 1][col] == -1)
            adjacentMines++
        if (isValid(row + 1, col) && boardIgrac[row + 1][col] == -1)
            adjacentMines++
        if (isValid(row, col + 1) && boardIgrac[row][col + 1] == -1)
            adjacentMines++
        if (isValid(row, col - 1) && boardIgrac[row][col - 1] == -1)
            adjacentMines++
        if (isValid(row - 1, col + 1) && boardIgrac[row - 1][col + 1] == -1)
            adjacentMines++
        if (isValid(row - 1, col - 1) && boardIgrac[row - 1][col - 1] == -1)
            adjacentMines++
        if (isValid(row + 1, col + 1) && boardIgrac[row + 1][col + 1] == -1)
            adjacentMines++
        if (isValid(row + 1, col - 1) && boardIgrac[row + 1][col - 1] == -1)
            adjacentMines++

        boardIgrac[row][col] = adjacentMines
        boardRacunar[row][col] = adjacentMines
    }


    fun initializeBoard() {
        getSize()

        //sve nule u matrici
        for (i in 0..(boardEdge - 1)) {
            var array = arrayOf<Int>()
            for (j in 0..(boardEdge - 1)) {
                array += 0
            }
            boardIgrac += array
        }

        for (i in 0..(boardEdge - 1)) {
            var array = arrayOf<Int>()
            for (j in 0..(boardEdge - 1)) {
                array += 0
            }
            boardRacunar += array
        }

        //vidljivo
        for (i in 0..(boardEdge - 1)) {
            var array = arrayOf<Char>()
            for (j in 0..(boardEdge - 1)) {
                array += '-'
            }
            visibleBoard += array
        }

        for (i in 0..(boardEdge - 1)) {
            var array = arrayOf<Boolean>()
            for (j in 0..(boardEdge - 1)) {
                array += false
            }
            mines += array
        }

        placeMines(startRow, startCol)

        val dx = arrayOf(-1, 0, 1, -1, 0, 1, -1, 0, 1)
        val dy = arrayOf(0, 0, 0, -1, -1, -1, 1, 1, 1)

        for (i in 0..(boardEdge - 1)) {
            for (j in (0..boardEdge - 1)) {
                if (!mines[i][j])
                    setNumber(i, j)
            }
        }

        for (i in 0..(boardEdge - 1)) {
            for (j in (0..boardEdge - 1)) {
                if (mines[i][j]) {
                    var num: Int = 1
                    for (k in 0..8)
                        if (isValid(i + dx[k], j + dy[k]) && mines[i + dx[k]][j + dy[k]])
                            num++
                    boardRacunar[i][j] = num
                    boardIgrac[i][j] = -1
                }
            }
        }
    }

    fun playMove(row: Int, col: Int, isMine: Boolean): Boolean {

        if (isFirst){
            startRow = row
            startCol = col
            isFirst = false
        }

        if(!isValid(row,col))
            return false
        if (visibleBoard[row][col] != '-' && visibleBoard[row][col] != '?')
            return false

        if(!isMine){

            if(visibleBoard[row][col] == '?')
                flagsLeft ++

            if (boardIgrac[row][col] == -1) {
                visibleBoard[row][col] = '*'
                println("You lost!")
                isDone = true
                showBoard(1)
                return true
            }
            else {
                var adjNum = boardIgrac[row][col]
                visibleBoard[row][col] = adjNum.toChar() + 48
                if (adjNum == 0) {
                    if (isValid(row - 1, col) && boardIgrac[row - 1][col] != -1)
                        playMove(row - 1, col, isMine)
                    if (isValid(row + 1, col) && boardIgrac[row + 1][col] != -1)
                        playMove(row + 1, col, isMine)
                    if (isValid(row, col + 1) && boardIgrac[row][col + 1] != -1)
                        playMove(row, col + 1,isMine)
                    if (isValid(row, col - 1) && boardIgrac[row][col - 1] != -1)
                        playMove(row, col - 1,isMine)
                    if (isValid(row - 1, col + 1) && boardIgrac[row - 1][col + 1] != -1)
                        playMove(row - 1, col + 1,isMine)
                    if (isValid(row - 1, col - 1) && boardIgrac[row - 1][col - 1] != -1)
                        playMove(row - 1, col - 1, isMine)
                    if (isValid(row + 1, col + 1) && boardIgrac[row + 1][col + 1] != -1)
                        playMove(row + 1, col + 1, isMine)
                    if (isValid(row + 1, col - 1) && boardIgrac[row + 1][col - 1] != -1)
                        playMove(row + 1, col - 1, isMine)
                }

                return false
            }
        }

        else{
            if(visibleBoard[row][col] == '-') {
                visibleBoard[row][col] = '?'
                flagsLeft--
                if (mines[row][col])
                    minesLeft --
            }
            else if (visibleBoard[row][col] == '?') {
                visibleBoard[row][col] = '-'
                flagsLeft++
                if (mines[row][col])
                    minesLeft ++
            }

            if (minesLeft == 0)
                isDone = true

            return true
        }

    }
}