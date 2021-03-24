class Tabla (val nivo : Level){

    var boardEdge : Int = 0
    var numOfMines : Int = 0

    private fun boardEdgeSize(){
        when(nivo.name){
            "BEGINNER" -> {boardEdge = 9 ; numOfMines = 10}
            "INTERMEDIATE" -> {boardEdge = 16 ; numOfMines = 40}
            "ADVANCED" -> {boardEdge = 24 ; numOfMines = 99}
            else -> 0
        }
    }




}