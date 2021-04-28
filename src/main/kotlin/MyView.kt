import javafx.application.Platform
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
        style {
            backgroundColor += c("#000000")

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
                button("Igrac") {
                    style {
                        fontFamily = "Comic Sans MS"

                    }
                    action { }
                }
                button("Kompjuter") {
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
                button("Beginner") {
                    style {
                        fontFamily = "Comic Sans MS"
                    }
                    action { }
                }
                button("Intermediate") {
                    style{ fontFamily = "Comic Sans MS"}
                    action { }
                }
                button("Advanced") {
                    style{ fontFamily = "Comic Sans MS"}
                    action { }
                }
            }
            vbox {
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
            }
        }

    }
