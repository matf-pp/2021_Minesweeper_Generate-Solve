
import javafx.application.Application
import tornadofx.*

class MyApp : App(MyView::class) {
}
fun main(args:Array<String>) {
    val tabla = Tabla(Level.BEGINNER, true)
    tabla.initializeBoard()
    val solver = Solver(Level.BEGINNER)
    solver.tabla = tabla
    solver.play()


    //Application.launch(MyApp::class.java, *args)

}