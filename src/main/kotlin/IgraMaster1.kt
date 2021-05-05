import javafx.geometry.Pos
import javafx.scene.layout.HBox
import javafx.scene.paint.Color
import tornadofx.*
import javax.swing.Action

class IgraMaster1(val tabla: Tabla): View("Minesweeper_GenerateAndSolve") {

    override val root: HBox = hbox(20, alignment = Pos.CENTER) {
        style {
            backgroundColor += c("#003366")
        }
        vbox(20, alignment=Pos.CENTER){
            paddingAll=20
            textarea() {
                    minHeight = 500.0
                    minWidth = 500.0
                    maxWidth = 500.0
                    maxHeight = 500.0
                    alignment = Pos.CENTER
            }

        }
        vbox(20, alignment=Pos.CENTER){

            hbox{
                alignment=Pos.CENTER
                label("x: "){
                    style{
                        textFill=Color.WHITE
                        fontFamily = "Monospace"
                    }
                }
                textfield {

                }
            }
            hbox{
                label("y: "){
                    style{
                        textFill=Color.WHITE
                        fontFamily = "Monospace"
                    }
                }
                textfield {

                }
            }
            vbox{
                spacing=30.0
                radiobutton("Mina"){

                    style{
                        textFill=Color.WHITE
                        fontFamily = "Monospace"
                    }
                }
                radiobutton("Otvori") {
                    style{
                        textFill=Color.WHITE
                        fontFamily = "Monospace"
                }

                }

                    button("Submit") {
                        style {
                            fontFamily = "Monospace"
                        }
                        action {}
                    }
                }
            }
        }
    }
