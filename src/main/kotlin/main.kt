
import javafx.application.Application
import tornadofx.*

class MyApp : App(MyView::class) {
}

fun main(args:Array<String>) {
//    val tabla1 = Tabla(Level.BEGINNER, true)
//    tabla1.initializeBoard()
//    print(tabla1.showBoard(1))
//   tabla1.playMove(0,0, false)
//   print(tabla1.showBoard(1))


   Application.launch(MyApp::class.java, *args)

}