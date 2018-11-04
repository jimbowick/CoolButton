import javafx.animation.Interpolator
import javafx.event.EventHandler
import javafx.event.EventTarget
import javafx.scene.effect.DropShadow
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import javafx.scene.paint.Paint
import tornadofx.*

class MyApp : App(MyView::class, MyStyle::class) {
    init {
        reloadStylesheetsOnFocus()
    }
}

fun EventTarget.coolbut(labey: String, onclick: () -> Unit = {}): StackPane {
    return stackpane {
        opacity = 1.0
        val reccy = rectangle {
            arcHeight = 20.0
            arcWidth = 20.0
            stroke = Color.BLACK
            fill = Color.DARKBLUE
//            opacity = 1.0
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
            this@stackpane.opacityProperty().animate(
                endValue = 0.1,
                duration = 100.millis,
                op = {
                    isAutoReverse = true
                    cycleCount = 2
                },
                interpolator = Interpolator.EASE_BOTH
            )
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
        minWidth = 100.0
        minHeight = 600.0
        vbox {
            hbox {
                imageview("knightman.png")
                hbox {
                    text("This is where the stats will appear")
                    style {
                        backgroundColor += Color.YELLOW
                        opacity = 0.5
                    }
                }

            }
            vbox {
                spacing = 20.0
                coolbut("Button0")
                coolbut("Button1")
                coolbut("the original coolbut")
            }
        }
        coolbut("Button3", { print("we are best") })
    }
}












