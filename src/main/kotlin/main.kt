
import javafx.application.Application
import tornadofx.*

class MyApp : App(MyView::class) {
}

fun main(args:Array<String>) {


   Application.launch(MyApp::class.java, *args)

}