import tornadofx.View
import tornadofx.button
import tornadofx.label
import tornadofx.vbox

class EndgameDialog(private val won: Boolean, continuePlayingCallback: (Boolean) -> Unit) : View("Kraj igre") {

    override val root = vbox {

        label(if (won) "Pobeda!!!" else "Izgubili ste.") {

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