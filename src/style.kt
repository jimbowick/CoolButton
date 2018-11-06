import javafx.scene.text.Font
import tornadofx.*

class MyStyle : Stylesheet() {
    init {
        s(root) {
            font = Font.font("Calibri Light", 12.0)
        }

    }
}