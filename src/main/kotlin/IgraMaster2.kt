import javafx.geometry.Pos
import javafx.scene.layout.HBox
import tornadofx.*

class IgraMaster2: View("Minesweeper_GenerateAndSolve") {
    override val root: HBox = hbox(20, alignment = Pos.CENTER) {
        style {
            backgroundColor += c("#003366")
        }
        vbox(20, alignment=Pos.CENTER){
            paddingAll=20
            button("Generate"){
              style{
                  fontFamily = "Monospace"
              }
            }
            textarea {
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
            style{
                fontFamily = "Monospace"
            }
            }
            textarea {
                minHeight=500.0
                minWidth=500.0
                maxWidth=500.0
                maxHeight=500.0
                alignment=Pos.CENTER

            }
        }
    }
}