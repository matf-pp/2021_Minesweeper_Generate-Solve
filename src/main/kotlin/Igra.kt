import javafx.application.Platform
import javafx.geometry.Pos
import javafx.scene.layout.HBox
import javafx.scene.paint.Color
import tornadofx.*

class Igra : View ("Minesweeper_GenerateAndSolve") {
    val brojevi=(1..82).toList()
    override val root: HBox = hbox(20, alignment = Pos.CENTER) {
        style {
            backgroundColor += c("#000000")
        }
        menubar {
            alignment = Pos.BASELINE_LEFT
            menu("Igra") {
                item("Nova igra", "Shortcut+P").action { }
                item("Izlaz", "Shortcut+Q").action { Platform.exit() }

            }
        }

        vbox(10) {
            //zasto labela nije na sredini kada se uveca ??
            label("     Minesweeper_GenerateAndSolve ") {
                style {
                    paddingAll = 20
                    fontSize = 40.px
                    textFill = c("#ff0000")
                    alignment = Pos.CENTER
                    fontFamily = "Comic Sans MS"

                }
            }


        }
        
    }
}
