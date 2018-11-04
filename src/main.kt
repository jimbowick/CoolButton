import javafx.animation.Interpolator
import javafx.event.EventHandler
import javafx.event.EventTarget
import javafx.scene.effect.DropShadow
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import javafx.scene.shape.StrokeLineCap
import javafx.scene.shape.StrokeLineJoin
import javafx.scene.shape.StrokeType
import tornadofx.*

class MyApp : App(MyView::class, MyStyle::class) {
    init {
        reloadStylesheetsOnFocus()
    }
}


fun EventTarget.coolbut(labey: String, onclick: () -> Unit = {}): StackPane {
    var anifin = true
    var stillIn = false
    return stackpane {
        val reccy = rectangle {
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
        val texty = text {
            text = labey
        }

        setOnMouseEntered {
            if (anifin && !stillIn) {
                anifin = false
                reccy.animateFill(
                    150.millis,
                    Color.LIGHTSKYBLUE,
                    reccy.fill as Color,
                    Interpolator.EASE_IN,
                    op = {
                        onFinished = EventHandler { anifin = true }
                        isAutoReverse = true
                    }
                )
            }
        }

        setOnMouseExited {
            stillIn = false
        }


        onMousePressed = EventHandler {
            isDisable = true
            reccy.fill = Color.ALICEBLUE
            onclick()
            this@stackpane.opacityProperty().animate(
                endValue = 0.1,
                duration = 200.millis,
                op = {
                    isAutoReverse = true
                    cycleCount = 2
                },
                interpolator = Interpolator.EASE_BOTH
            )
            reccy.animateFill(
                500.millis,
                Color.LIGHTBLUE,
                reccy.fill as Color,
                Interpolator.EASE_IN,
                op = {
                    onFinished = EventHandler {
                        isDisable = false
                        stillIn = true
                    }
                }
            )
            reccy.rotateProperty().animate(
                endValue = 3,
                duration = 40.millis,
                op = {
                    isAutoReverse = true
                    cycleCount = 4
                },
                interpolator = Interpolator.EASE_BOTH
            )
            texty.rotateProperty().animate(
                endValue = 3,
                duration = 40.millis,
                op = {
                    isAutoReverse = true
                    cycleCount = 2
                },
                interpolator = Interpolator.EASE_BOTH
            )

            this@stackpane.scaleXProperty().animate(
                endValue = 1.05,
                duration = 200.millis,
                op = {
                    isAutoReverse = true
                    cycleCount = 2

                },
                interpolator = Interpolator.EASE_BOTH
            )

            this@stackpane.scaleYProperty().animate(
                endValue = 1.1,
                duration = 200.millis,
                op = {
                    isAutoReverse = true
                    cycleCount = 2
                },
                interpolator = Interpolator.EASE_BOTH
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












