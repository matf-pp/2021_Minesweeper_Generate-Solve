class Tabla (val nivo : Level, val automaticSolver : Boolean) {

    var boardEdge: Int = 0
    var numOfMines: Int = 0

    var startRow: Int = 0
    var startCol: Int = 0

    private fun getSize() {
        when (nivo.name) {
            "BEGINNER" -> {
                boardEdge = 9; numOfMines = 10
            }
            "INTERMEDIATE" -> {
                boardEdge = 16; numOfMines = 40
            }
            "ADVANCED" -> {
                boardEdge = 24; numOfMines = 99
            }
            else -> 0
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

    fun showBoard(type: Int) {

        if (type == 0) {
            for (array in boardIgrac) {
                for (j in array) {
                    print(j)
                    print("    ")
                }
                println()
            }
        } else {
            for (array in visibleBoard) {
                for (j in array) {
                    print(j)
                    print("    ")
                }
                println()
            }
        }
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

    fun playMove(row: Int, col: Int): Boolean {

        if (visibleBoard[row][col] != '-')
            return false

        if (boardIgrac[row][col] == -1) {
            visibleBoard[row][col] = '*'
            println("You lost!")
            showBoard(1)
            return true
        } else {
            var adjNum = boardIgrac[row][col]
            visibleBoard[row][col] = adjNum.toChar()

            if (adjNum == 0) {
                if (isValid(row - 1, col) && boardIgrac[row - 1][col] == -1)
                    playMove(row - 1, col)
                if (isValid(row + 1, col) && boardIgrac[row + 1][col] == -1)
                    playMove(row + 1, col)
                if (isValid(row, col + 1) && boardIgrac[row][col + 1] == -1)
                    playMove(row, col + 1)
                if (isValid(row, col - 1) && boardIgrac[row][col - 1] == -1)
                    playMove(row, col - 1)
                if (isValid(row - 1, col + 1) && boardIgrac[row - 1][col + 1] == -1)
                    playMove(row - 1, col + 1)
                if (isValid(row - 1, col - 1) && boardIgrac[row - 1][col - 1] == -1)
                    playMove(row - 1, col - 1)
                if (isValid(row + 1, col + 1) && boardIgrac[row + 1][col + 1] == -1)
                    playMove(row + 1, col + 1)
                if (isValid(row + 1, col - 1) && boardIgrac[row + 1][col - 1] == -1)
                    playMove(row + 1, col - 1)
            }
            return false
        }


    }

}