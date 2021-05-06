
import javafx.application.Application
import tornadofx.*

class MyApp : App(MyView::class) {
}
fun main(args:Array<String>) {
   // val tabla1 = Tabla(Level.BEGINNER, true)
   // tabla1.initializeBoard()
   // tabla1.showBoard(0)
   // val solver = Solver(Level.BEGINNER)
    //solver.tabla = tabla1
    //solver.play()


   Application.launch(MyApp::class.java, *args)

}