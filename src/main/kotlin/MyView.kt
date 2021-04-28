import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.ButtonBar
import javafx.scene.control.Label
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import tornadofx.*
import javax.swing.JButton

class MyView : View("Minesweeper_GenerateAndSolve") {

    override val root: HBox = hbox(20, alignment = Pos.CENTER) {
       style{
           backgroundColor+=c("#b3b3cc")

       }
        vbox(10) {

            label("Izabrati nacin igranja igre: ") {
                style {
                    fontSize = 25.px
                    alignment = Pos.CENTER


                }
            }
            hbox {
                spacing=10.0
                alignment=Pos.CENTER
                button("Igrac") {
                    action{

                    }
                }
                button("Kompjuter") {
                    action {

                    }
                }
            }

             }
        }
    }
