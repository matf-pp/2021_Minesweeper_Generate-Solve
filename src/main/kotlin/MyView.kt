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

class MyView : View("Minesweeper_GenerateAndSolve") {
   private val toggleGroup1 = ToggleGroup()
   private val toggleGroup2 = ToggleGroup()


    override val root: HBox = hbox(20, alignment = Pos.CENTER) {
        style {
            backgroundColor += c("#000000")

        }
        //Ne znam da postavim menubar skroz na vrh gde je naslov MyView klase
        menubar {
            alignment=Pos.BASELINE_LEFT
            menu("Igra"){
               // item("Nova igra", "Shortcut+P").action {  }
                item("Izlaz", "Shortcut+Q").action { Platform.exit() }

        }
        }

        vbox(10) {
            label("Minesweeper_GenerateAndSolve ") {
                style {
                    paddingAll=20
                    fontSize = 40.px
                    textFill = c("#ff0000")
                    alignment = Pos.CENTER
                    fontFamily = "Comic Sans MS"

                }
            }

            label("                  Izabrati nacin igranja igre: ") {
                style {
                    paddingAll=20
                    fontSize = 25.px
                    textFill = c("#ffffff")
                    alignment = Pos.TOP_CENTER
                    fontFamily = "Comic Sans MS"

                }
            }
            hbox {
                spacing = 40.0
                alignment = Pos.CENTER
                togglebutton("Igrac", toggleGroup1) {
                    style {
                        fontFamily = "Comic Sans MS"

                    }
                    action {  }
                }
                togglebutton ("Kompjuter", toggleGroup1) {
                    style{ fontFamily = "Comic Sans MS"}
                    action { }
                }
            }

            label("                         Izabrati tezinu igre: ") {
                style {
                    paddingAll=20
                    fontSize = 25.px
                    textFill = c("#ffffff")
                    alignment = Pos.TOP_CENTER
                    fontFamily = "Comic Sans MS"

                }
            }
            hbox {
                spacing = 40.0
                alignment = Pos.CENTER
                togglebutton ("Beginner", toggleGroup2) {
                    style {
                        fontFamily = "Comic Sans MS"
                    }
                    action { close()
                             find<IgraMaster1>().openWindow()
                    }
                }
                togglebutton ("Intermediate", toggleGroup2) {
                    style{ fontFamily = "Comic Sans MS"}
                    action {close()
                        find<Igra1>().openWindow() }
                }
                togglebutton ("Advanced", toggleGroup2) {
                    style{ fontFamily = "Comic Sans MS"}
                    action { close()
                        find<Igra2>().openWindow()  }
                }
            }
        /*  Ovo je izbaceno i dodat menubar umesto ovoga   vbox {
                spacing=40.0
                alignment=Pos.CENTER
                label("Izlaz: ") {
                    style {
                        paddingAll=20
                        fontSize = 25.px
                        textFill = c("#ff0000")
                        alignment = Pos.TOP_CENTER
                        fontFamily = "Comic Sans MS"

                    }
                }
                button("Izlaz") {
                    style{ fontFamily = "Comic Sans MS"
                           textFill=c("#ff0000")}
                    action { Platform.exit()}
                }
            }
           */

        }
        }

    }
