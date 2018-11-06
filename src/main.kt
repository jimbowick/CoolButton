import javafx.event.EventHandler
import javafx.event.EventTarget
import javafx.scene.effect.DropShadow
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.scene.shape.StrokeLineCap
import javafx.scene.shape.StrokeLineJoin
import javafx.scene.shape.StrokeType
import javafx.scene.text.Font
import javafx.scene.text.Text
import tornadofx.*

class MyApp : App(MyView::class, MyStyle::class) {
    init {
        reloadStylesheetsOnFocus()
    }
}

fun EventTarget.coolbut(coolString: String, onclick: () -> Unit = {}): StackPane {
    var animating = false
    lateinit var reccy: Rectangle
    lateinit var texty: Text
    val stackypane = stackpane {
        reccy = rectangle {
            strokeWidth = 2.0
            stroke = Color.DARKCYAN
            strokeType = StrokeType.INSIDE
            strokeLineJoin = StrokeLineJoin.ROUND
            strokeLineCap = StrokeLineCap.ROUND
            arcHeight = 20.0
            arcWidth = 20.0
            fill = Color.ALICEBLUE
            effect = DropShadow()
            width = 250.0
            height = 40.0
        }
        texty = text(coolString)
    }
    stackypane.onMousePressed = EventHandler {
        onclick()
        if (!animating) {
            animating = true
            stackypane.opacityProperty().animate(
                endValue = 0.3,
                duration = 50.millis,
                op = {
                    isAutoReverse = true
                    cycleCount = 2
                }
            )
            reccy.rotateProperty().animate(
                endValue = 3,
                duration = 40.millis,
                op = {
                    isAutoReverse = true
                    cycleCount = 4
                }
            )
            texty.rotateProperty().animate(
                endValue = 2,
                duration = 40.millis,
                op = {
                    isAutoReverse = true
                    cycleCount = 4
                }
            )
            stackypane.scaleXProperty().animate(
                endValue = 0.9,
                duration = 150.millis,
                op = {
                    isAutoReverse = true
                    cycleCount = 2
                    onFinished = EventHandler {
                        animating = false
                    }

                }
            )
            stackypane.scaleYProperty().animate(
                endValue = 0.9,
                duration = 150.millis,
                op = {
                    isAutoReverse = true
                    cycleCount = 2
                }
            )

        }
    }
    stackypane.onHover { on ->
        if (on) reccy.fill = Color.LIGHTBLUE
        else reccy.fill = Color.ALICEBLUE
        if (!animating) {
            animating = true
            reccy.rotateProperty().animate(
                endValue = 2,
                duration = 21.millis,
                op = {
                    isAutoReverse = true
                    cycleCount = 2
                    onFinished = EventHandler {
                        animating = false
                    }
                }
            )
            texty.rotateProperty().animate(
                endValue = 1,
                duration = 20.millis,
                op = {
                    isAutoReverse = true
                    cycleCount = 2
                }
            )
        }
    }
    return stackypane
}

class MyView : View() {
    override val root = hbox {
        minWidth = 100.0
        minHeight = 600.0
        vbox {
            hbox {
                imageview("knightman.png")
                hbox {
                    text("This is where the stats will appear") {
                        font = Font.font("Gabriola", 20.0)
                    }
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
        coolbut(
            coolString = "Button3",
            onclick = {
                print("we are best")
            }
        )
    }
}












