import javafx.beans.property.SimpleObjectProperty
import javafx.geometry.Pos
import javafx.scene.control.TextArea
import javafx.scene.layout.HBox
import javafx.scene.paint.Color
import tornadofx.*
import javax.swing.Action

class IgraMaster1(val tabla: Tabla): View("Minesweeper_GenerateAndSolve") {
    var prostor: TextArea by singleAssign()

   init{
       tabla.initializeBoard()
   }


    enum class Izbor{
       MINA,OTVORI
   }
    private val koordinatax=SimpleObjectProperty<String>()
    private val koordinatay=SimpleObjectProperty<String>()
    private val selectedIzborProperty=SimpleObjectProperty<Izbor>()
    override val root: HBox = hbox(20, alignment = Pos.CENTER) {
        style {
            backgroundColor += c("#003366")
        }
        vbox(20, alignment=Pos.CENTER){
            paddingAll=20
           prostor= textarea() {
                    text=tabla.showBoard(1)
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
                textfield(koordinatax)
            }
            hbox{
                label("y: "){
                    style{
                        textFill=Color.WHITE
                        fontFamily = "Monospace"
                    }
                }
                textfield(koordinatay)
            }
            vbox{
                spacing=30.0
                togglegroup {
                    bind(selectedIzborProperty)
                radiobutton("Mina", value = Izbor.MINA){
                    style{
                        textFill=Color.WHITE
                        fontFamily = "Monospace"
                    }
                }
                radiobutton("Otvori", value = Izbor.OTVORI) {
                    style {
                        textFill = Color.WHITE
                        fontFamily = "Monospace"
                    }
                }
                }

                    button("Submit") {
                        style {
                            fontFamily = "Monospace"
                        }
                        action {
                            val x=koordinatax.value.toIntOrNull()
                            val y=koordinatay.value.toIntOrNull()
                            if( x!= null && y!=null){
                                when(selectedIzborProperty.value){
                                    Izbor.MINA -> { tabla.playMove(x,y,true) }
                                    Izbor.OTVORI -> { tabla.playMove(x,y, false)}
                                    null-> { }
                                }
                              prostor.text=tabla.showBoard(1)
                            }
                        }
                    }
                }
            }
        }
    }
