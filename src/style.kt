import javafx.util.Duration
import javafx.scene.paint.Color
import javafx.scene.text.Font
import tornadofx.*

class MyStyle : Stylesheet() {

    companion object {

    }


    init {
        s(root) {
            font = Font.font("Calibri Light", 20.0)
        }

    }
}