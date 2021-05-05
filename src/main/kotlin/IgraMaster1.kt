import javafx.geometry.Pos
import javafx.scene.layout.HBox
import javafx.scene.paint.Color
import tornadofx.*

class IgraMaster1: View("Minesweeper_GenerateAndSolve") {
    override val root: HBox = hbox(20, alignment = Pos.CENTER) {
        style {
            backgroundColor += c("#000000")
        }
        vbox(20, alignment=Pos.TOP_LEFT){
            textarea {
                minHeight=20.0
                minWidth=20.0
                alignment=Pos.CENTER_LEFT

            }
        }
        vbox(20, alignment=Pos.TOP_RIGHT){
            hbox{
                alignment=Pos.CENTER
                label("x: "){
                    style{
                        textFill=Color.WHITE
                    }
                }
                textfield {

                }
            }
            hbox{
                label("y: "){
                    style{
                        textFill=Color.WHITE
                    }
                }
                textfield {

                }
            }
            vbox{

                radiobutton("Mina"){
                    style{
                        textFill=Color.WHITE
                    }
                }
                radiobutton("Otvori") {
                    style{
                        textFill=Color.WHITE
                }

                }

                    button("Submit") {
                        style {}
                        action {}
                    }
                }
            }
        }
    }
