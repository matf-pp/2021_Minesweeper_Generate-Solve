import kotlin.reflect.jvm.internal.impl.utils.DFS

class Solver (var level: Level){

    var tabla : Tabla = Tabla(level, true)
    var currentX : Int = 0
    var currentY : Int = 0

    val dx =  arrayOf( -1, 0, 1, -1, 0, 1, -1, 0, 1)
    val dy = arrayOf( 0, 0, 0, -1, -1, -1, 1, 1, 1 )

    private fun isDone(visited: Array<Array<Boolean>>): Boolean {

        for (i in 0..(tabla.boardEdge - 1)) {
            for (j in 0..(tabla.boardEdge - 1)) {
                if (!visited[i][j]) {
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

        if (!isValid(row,col))
            return false

        for (i in 0..8){
            if (isValid(row + dx[i], col + dy[i])){
                if( tabla.board[row + dx[i]][col + dy[i]] - 1 <0)
                    return false
            }
        }

        for(i in 0..8){
            if (isValid(row + dx[i], col + dy[i]))
                tabla.board[row + dx[i]][col + dy[i]]--
        }

        return true
    }

    private fun findUnvisited(visited: Array<Array<Boolean>>): Boolean{
        for (x in 0..(tabla.boardEdge-1))
            for (y in 0..(tabla.boardEdge-1))
                if(!visited[x][y]) {
                    currentX = x
                    currentY = y
                    return true
                }
        return false
    }

    private fun solveMinesweeper(hasMines : Array<Array<Boolean>>, visited: Array<Array<Boolean>>) : Boolean{
        if (isDone(visited)) {
            print("Gotovo!")
            return true
        }
        if(!findUnvisited(visited)){
            print("Ne moze se resiti")
            return false
        }

        visited[currentX][currentY] = true

        if(isMine(currentX, currentY)){
            hasMines[currentX][currentY] = true

            if(solveMinesweeper(hasMines, visited))
                return true

            hasMines[currentX][currentY] = false
            for(i in 0..8)
                if(isValid(currentX + dx[i] , currentY + dy[i]))
                    tabla.board[currentX + dx[i]][currentY + dy[i]]++

        }

        if(solveMinesweeper(hasMines, visited))
            return true

        visited[currentX][currentY] = false
        return false
    }

    fun play(){

        tabla.getStartCoords()
        currentX = tabla.startRow
        currentY = tabla.startCol


        var hasMines = arrayOf<Array<Boolean>>()
        var visited = arrayOf<Array<Boolean>>()

        for (i in 0..(tabla.boardEdge-1)){
            var array = arrayOf<Boolean>()
            for (j in (0..tabla.boardEdge-1)){
                array += false
            }
            hasMines+=array
        }
        for (i in 0..(tabla.boardEdge-1)){
            var array = arrayOf<Boolean>()
            for (j in (0..tabla.boardEdge-1)){
                array += false
            }
            visited += array
        }

        if(solveMinesweeper(hasMines, visited)){
             for (i in 0..(tabla.boardEdge-1)) {
                for (j in 0..(tabla.boardEdge - 1)){
                    if(hasMines[i][j]){
                     tabla.visibleBoard[i][j] = 'x'
                    }
                    else{
                        tabla.visibleBoard[i][j] = '_'
                    }
                }
            }
        }

        tabla.showBoard(1)
    }


}