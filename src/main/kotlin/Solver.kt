class Solver (var level: Level){

    val tabla : Tabla = Tabla(level, true)

    private fun isDone(): Boolean {
        for (i in 0..(tabla.boardEdge - 1)) {
            for (j in 0..(tabla.boardEdge - 1)) {
                if (tabla.visibleBoard[i][j] == '-') {
                    return false
                }
            }
        }
        return true
    }

    private fun isValid(x: Int, y: Int): Boolean{
        return (x >= 0 && y >= 0 && x < tabla.boardEdge && y < tabla.boardEdge)
    }

    private fun isMine(row: Int, col: Int): Boolean{

        var dx =  arrayOf( -1, 0, 1, -1, 0, 1, -1, 0, 1)
        var dy = arrayOf( 0, 0, 0, -1, -1, -1, 1, 1, 1 )

        for (i in 0..9){
            if (isValid(row + dx[i], col + dy[i]) && tabla.board[row + dx[i]][col + dy[i]] - 1 <0)
                return false
        }

        for(i in 0..9){
            if (isValid(row + dx[i], col + dy[i]))
                tabla.board[row + dx[i]][col + dy[i]]--
        }

        return true
    }

    private fun play(){

        tabla.playMove(tabla.startRow, tabla.startCol)
        tabla.showBoard(1)

        var row: Int = tabla.startRow
        var col: Int = tabla.startCol

        while(!isDone()){
            isMine(row, col)

        }

    }


}