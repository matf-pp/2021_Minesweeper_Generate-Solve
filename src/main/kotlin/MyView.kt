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
        minHeight=500.0
        minWidth=500.0
        style {
            backgroundColor += c("#003366")


        }
        //Ne znam da postavim menubar skroz na vrh gde je naslov MyView klase
      /*  menubar {
            alignment=Pos.BASELINE_LEFT
            menu("Igra"){
               // item("Nova igra", "Shortcut+P").action {  }
                item("Izlaz", "Shortcut+Q").action { Platform.exit() }

        }
        }
    */
        vbox(10) {
            label("Minesweeper_GenerateAndSolve ") {
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

                        }
                    }
                }
                togglebutton ("Kompjuter", toggleGroup1) {
                    style{ fontFamily = "Monospace"}
                    action {close()
                        find<IgraMaster2>().openWindow() }
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
//                    action { close()
//                             find<IgraMaster1>().openWindow()
//                    }
                }
                radiobutton  ("Intermediate", toggleGroup2,Level.INTERMEDIATE) {
                    style{ fontFamily = "Monospace"
                        textFill = c("#ffffff")}
//                    action {close()
//                        find<IgraMaster2>().openWindow() }
                }
                radiobutton ("Advanced", toggleGroup2, Level.ADVANCED) {
                    style{ fontFamily = "Monospace"
                            textFill = c("#ffffff")}
//                    action { close()
//                        find<Igra2>().openWindow()  }
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
