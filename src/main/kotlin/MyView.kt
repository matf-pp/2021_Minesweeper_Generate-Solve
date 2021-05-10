import javafx.application.Platform
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.ButtonBar
import javafx.scene.control.Label
import javafx.scene.control.ToggleGroup
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import tornadofx.*
import javax.swing.JButton

class MyView : View("Minesweeper: Generate&Solve") {
    private val toggleGroup1 = ToggleGroup()
    private val toggleGroup2 = ToggleGroup().apply {
        selectedValueProperty<Level>().value = Level.BEGINNER
    }

    override val root: HBox = hbox(20, alignment = Pos.CENTER) {
        minHeight=500.0
        minWidth=500.0
        style {
            backgroundColor += c("#003366")
        }
        vbox(10) {
            label("Minesweeper: Generate & Solve ") {
                style {
                    paddingAll=20
                    fontSize = 40.px
                    textFill = c("6699CC")
                    alignment = Pos.CENTER
                    fontFamily = "Monospace"

                }
            }

            label("         Izabrati nacin igranja igre: ") {
                style {
                    paddingAll=20
                    fontSize = 25.px
                    textFill = c("#ffffff")
                    alignment = Pos.TOP_CENTER
                    fontFamily = "Monospace"

                }
            }
            hbox {
                spacing = 40.0
                alignment = Pos.CENTER
                togglebutton("Igrac", toggleGroup1) {
                    style {
                        fontFamily = "Monospace"
                    }
                    action {
                        val selectedLevel: Level?=toggleGroup2.selectedValueProperty<Level>().value
                        if(selectedLevel!=null){
                            val tabla=Tabla(selectedLevel, false)
                            val igra=IgraMaster1(tabla)
                            igra.openWindow()
                        } else {
                            println("selectedLevel is null")
                        }
                    }
                }
                togglebutton ("Kompjuter", toggleGroup1) {
                    style{ fontFamily = "Monospace"}
                    action {
                        val selectedLevel: Level?=toggleGroup2.selectedValueProperty<Level>().value
                        if(selectedLevel!=null){
                            val igra=IgraMaster2(selectedLevel)
                            igra.openWindow() }
                    }
                }
            }

            label("            Izabrati tezinu igre: ") {
                style {
                    paddingAll=20
                    fontSize = 25.px
                    textFill = c("#ffffff")
                    alignment = Pos.TOP_CENTER
                    fontFamily = "Monospace"

                }
            }
            hbox {
                spacing = 40.0
                alignment = Pos.CENTER
                radiobutton  ("Beginner", toggleGroup2,Level.BEGINNER) {
                    style {
                        fontFamily = "Monospace"
                        textFill = c("#ffffff")
                    }
                    isSelected = true
                }
                radiobutton  ("Intermediate", toggleGroup2,Level.INTERMEDIATE) {
                    style{ fontFamily = "Monospace"
                        textFill = c("#ffffff")}
                }
                radiobutton ("Advanced", toggleGroup2, Level.ADVANCED) {
                    style{ fontFamily = "Monospace"
                        textFill = c("#ffffff")}
                }
            }


        }
    }

}
