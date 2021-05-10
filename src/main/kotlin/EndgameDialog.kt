import javafx.geometry.Pos
import javafx.scene.paint.Color
import tornadofx.*

class EndgameDialog(private val won: Boolean, continuePlayingCallback: (Boolean) -> Unit) : View("Kraj igre") {

    override val root = vbox {
        spacing=15.0
        paddingAll=12
        alignment=Pos.CENTER
        minHeight=100.0
        minWidth=200.0
        maxHeight=100.0
        maxWidth=200.0
        style {
            backgroundColor += c("#003366")
        }
        label(if (won) "Pobeda!!!" else "Izgubili ste.") {

                alignment= Pos.CENTER
            style {
                textFill = Color.WHITE
                fontFamily = "Monospace"
                fontSize = 20.px
            }
        }

        button("Igraj ponovo") {
            maxWidth = Double.MAX_VALUE
            setOnAction {
                continuePlayingCallback(true)
                close()
            }
        }

        button("Izlaz") {
            maxWidth = Double.MAX_VALUE

            setOnAction {
                continuePlayingCallback(false)
            }
        }
    }

}