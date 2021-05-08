import javafx.geometry.Pos
import javafx.scene.control.TextArea
import javafx.scene.layout.HBox
import tornadofx.*

class IgraMaster2(private val level: Level): View("Minesweeper_GenerateAndSolve") {
    var prostor1: TextArea by singleAssign()
    var prostor2: TextArea by singleAssign()

    private var tabla: Tabla = Tabla(level, true)

    override val root: HBox = hbox(20, alignment = Pos.CENTER) {
        style {
            backgroundColor += c("#003366")
        }
        vbox(20, alignment=Pos.CENTER){
            paddingAll=20
            button("Generate"){
                action {

                    tabla = Tabla(level, true)
                    tabla.initializeBoard()
                    prostor1.text = tabla.showBoard(2)
                }
                    style{
                  fontFamily = "Monospace"
              }
            }
           prostor1= textarea() {
               text=tabla.showBoard(2)
                minHeight=500.0
                minWidth=500.0
                maxWidth=500.0
                maxHeight=500.0
                alignment=Pos.CENTER

            }
        }
        vbox(20, alignment=Pos.CENTER){
            paddingAll=20
            button("Solve"){
                action {
                  //  tabla = Tabla(level, true)
                 //   tabla.initializeBoard()
                     val solver = Solver(level)
                    solver.tabla = tabla
                    solver.play()
                    prostor2.text=tabla.showBoard(1)
                }
            style{
                fontFamily = "Monospace"
            }
            }
            prostor2=textarea {
                minHeight=500.0
                minWidth=500.0
                maxWidth=500.0
                maxHeight=500.0
                alignment=Pos.CENTER

            }
        }
    }
}