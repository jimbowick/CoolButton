import javafx.util.Duration
import javafx.scene.paint.Color
import tornadofx.*

class MyStyle : Stylesheet() {

    companion object {
        val tackyButton by cssclass()
        var buttonColor = Color.DARKBLUE

        private val topColor = Color.RED
        private val rightColor = Color.DARKGREEN
        private val leftColor = Color.ORANGE
        private val bottomColor = Color.PURPLE
    }


    init {
        s(root) {
            maxWidth = 1000.px
            minWidth = maxWidth
            minHeight = maxWidth
        }
        s(tackyButton) {
            maxWidth = 500.px
            minWidth = maxWidth
            vgap = 10.px

        }
        tackyButton {
            vgap = 50.px
            backgroundColor += buttonColor
            borderColor += box(topColor, rightColor, bottomColor, leftColor)
            fontFamily = "Comic Sans MS"
            fontSize = 20.px
        }
    }
}