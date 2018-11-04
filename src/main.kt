import javafx.animation.Interpolator
import javafx.animation.KeyFrame
import javafx.animation.KeyValue
import javafx.animation.Timeline
import javafx.event.EventDispatchChain
import javafx.event.EventHandler
import javafx.event.EventTarget
import javafx.scene.Parent
import javafx.scene.control.Button
import javafx.scene.control.Control
import javafx.util.Duration
import javafx.scene.control.TextField
import javafx.scene.effect.DropShadow
import javafx.scene.layout.Pane
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.scene.shape.Shape
import javafx.scene.text.Text
import tornadofx.*
import java.util.*

class MyApp : App(MyView::class, MyStyle::class) {
    init {
        reloadStylesheetsOnFocus()
    }
}

fun EventTarget.coolbut(labey: String,onclick:()->Unit={}): StackPane {
    return stackpane {
        val reccy = rectangle {
            arcHeight = 20.0
            arcWidth = 20.0
            stroke = Color.YELLOW
            fill = Color.BEIGE
            effect = DropShadow()
            width = 250.0
            height = 40.0

        }
        val texty = text {
            text = labey
        }
        onMousePressed = EventHandler {

            isDisable = true

            onclick()

            reccy.animateFill(
                500.millis,
                Color.GREEN,
                reccy.fill as Color,
                Interpolator.EASE_IN,
                op = {
                    onFinished = EventHandler { isDisable = false }
                }
            )
            reccy.rotateProperty().animate(
                endValue = 30,
                duration = 25.millis,
                op = {
                    isAutoReverse = true
                    cycleCount = 4
                },
                interpolator = Interpolator.EASE_BOTH
            )
            texty.rotateProperty().animate(
                endValue = 30,
                duration = 25.millis,
                op = {
                    isAutoReverse = true
                    cycleCount = 4
                },
                interpolator = Interpolator.EASE_BOTH
            )

            this@stackpane.scaleXProperty().animate(
                endValue = 1.1,
                duration = 100.millis,
                op = {
                    isAutoReverse = true
                    cycleCount = 4

                }
            )

            this@stackpane.scaleYProperty().animate(
                endValue = 1.1,
                duration = 100.millis,
                op = {
                    isAutoReverse = true
                    cycleCount = 4
                }
            )
        }
    }
}


class MyView : View() {

    override val root = hbox {
        vbox {
            hbox {
                imageview("knightman.png")
                text("This is where the stats will appear")
            }
            vbox {
                spacing = 20.0
                coolbut("Button0")
                coolbut("Button1")
                coolbut("the original coolbut")
            }
        }
        coolbut("Button3",{print("we are best")})
    }
}












