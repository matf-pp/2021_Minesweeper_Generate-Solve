fun main() {
    val tabla = Tabla(Level.BEGINNER)
    tabla.initializeBoard()

    for (array in tabla.board){
        for (j in array){
            print(j)
            print("    ")
        }
        println()
    }
}