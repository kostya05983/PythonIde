import javafx.scene.Parent
import tornadofx.FXEvent
import tornadofx.View
import tornadofx.stackpane

class custom : View() {
    override val root: Parent = stackpane {
        fire(MyEvent)

        subscribe<MyEvent> {
            println("kek")
        }
    }

}

//fun main() {
//    custom
//}

object MyEvent : FXEvent()