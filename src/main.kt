import javafx.animation.Interpolator
import javafx.event.EventHandler
import javafx.event.EventTarget
import javafx.scene.effect.DropShadow
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import javafx.scene.shape.StrokeLineCap
import javafx.scene.shape.StrokeLineJoin
import javafx.scene.shape.StrokeType
import javafx.scene.text.Font
import tornadofx.*

class MyApp : App(MyView::class, MyStyle::class) {
    init {
        reloadStylesheetsOnFocus()
    }
}

fun EventTarget.coolbut(coolString: String, onclick: () -> Unit = {}): StackPane {
    var stillIn = false
    var animating = false
    var wobbling = false
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

        val texty = text(coolString)

        this@stackpane.setOnMouseEntered {
            if (!stillIn && !animating && !wobbling) {
                wobbling = true
                reccy.animateFill(
                    time = 50.millis,
                    from = reccy.fill as Color,
                    to = Color.LIGHTSKYBLUE,
                    op = {
                        onFinished = EventHandler {
                            wobbling = false
                        }
                    }
                )
                reccy.rotateProperty().animate(
                    endValue = 2,
                    duration = 20.millis,
                    op = {
                        isAutoReverse = true
                        cycleCount = 2
                    },
                    interpolator = Interpolator.EASE_BOTH
                )
                texty.rotateProperty().animate(
                    endValue = 1,
                    duration = 20.millis,
                    op = {
                        isAutoReverse = true
                        cycleCount = 2
                    },
                    interpolator = Interpolator.EASE_BOTH
                )
            }
        }

        this@stackpane.setOnMouseExited {
            stillIn = false
            reccy.animateFill(
                200.millis,
                Color.SKYBLUE,
                Color.ALICEBLUE,
                op = {
                    cycleCount = 1
                    isAutoReverse = false
                }
            )
        }

        onMousePressed = EventHandler {
            if(!animating){
                stillIn = true
                onclick()
                if(!wobbling){
                    animating = true
                    this@stackpane.opacityProperty().animate(
                        endValue = 0.3,
                        duration = 40.millis,
                        op = {
                            isAutoReverse = true
                            cycleCount = 2
                        },
                        interpolator = Interpolator.EASE_BOTH
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
                        endValue = 0.9,
                        duration = 150.millis,
                        op = {
                            isAutoReverse = true
                            cycleCount = 2
                            onFinished = EventHandler{
                                animating = false
                            }

                        },
                        interpolator = Interpolator.EASE_BOTH
                    )

                    this@stackpane.scaleYProperty().animate(
                        endValue = 0.9,
                        duration = 150.millis,
                        op = {
                            isAutoReverse = true
                            cycleCount = 2
                        },
                        interpolator = Interpolator.EASE_BOTH
                    )
                }
            }
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












