import javafx.beans.property.SimpleObjectProperty
import javafx.geometry.Pos
import javafx.scene.control.TextArea
import javafx.scene.layout.HBox
import javafx.scene.paint.Color
import tornadofx.*import kotlin.system.exitProcess

class IgraMaster1(var tabla: Tabla): View("Minesweeper: Generate&Solve") {
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
    private val isDoneProperty = SimpleObjectProperty<Boolean>(false)
//    private val isWonProperty = SimpleObjectProperty<Boolean>()

    private val flagsLeftProperty = SimpleObjectProperty<String>("Flags left: ${tabla.flagsLeft}")

    override val root: HBox = hbox(20, alignment = Pos.CENTER) {
        style {
            backgroundColor += c("#003366")
        }
        vbox(20, alignment=Pos.CENTER){
            paddingAll=20
            prostor= textarea {
                style {
                    fontFamily = "Monospace"
                }
                isEditable = false
                text=tabla.showBoard(1)
                minHeight = 500.0
                minWidth = 500.0
                maxWidth = 500.0
                maxHeight = 500.0
                alignment = Pos.CENTER
            }

            label(flagsLeftProperty) {
                style {
                    textFill=Color.WHITE
                    fontFamily = "Monospace"
                }
            }
        }
        vbox(20, alignment=Pos.CENTER){

            hbox{
                alignment=Pos.CENTER
                label("red:    "){
                    style{
                        textFill=Color.WHITE
                        fontFamily = "Monospace"
                    }
                }
                textfield(koordinatax)
            }
            hbox{
                alignment = Pos.BOTTOM_LEFT
                label("kolona: "){
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
                    disableWhen(isDoneProperty)
                    action {
                        val x=koordinatax.value.toIntOrNull()
                        val y=koordinatay.value.toIntOrNull()
                        if( x!= null && y!=null){
                            when(selectedIzborProperty.value){
                                Izbor.MINA -> {
                                    tabla.playMove(x,y,true)
                                    if (tabla.isDone) {
                                        showEndgameDialog(true)
                                    }
                                }
                                Izbor.OTVORI -> {
                                    val isMine = tabla.playMove(x,y, false)
                                    if (isMine) {
                                        showEndgameDialog(false)
                                    }
                                }
                                null-> {}
                            }
                            updateDisplay()
                        }
                    }
                }
            }
        }
    }

    private fun showEndgameDialog(won: Boolean) {
        EndgameDialog(won) { continuePlaying ->
            if (continuePlaying) {
                tabla = Tabla(tabla.nivo, false)
                tabla.initializeBoard()
                updateDisplay()
            } else {
                exitProcess(0)
            }
        }.openModal()
    }

    private fun updateDisplay() {
        isDoneProperty.value = tabla.isDone
        flagsLeftProperty.value = "Flags left: ${tabla.flagsLeft}"
        prostor.text=tabla.showBoard(1)
    }
}
