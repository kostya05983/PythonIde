import tornadofx.App
import tornadofx.UIComponent
import tornadofx.find
import tornadofx.launch
import views.EditorTabPane
import views.MenuView
import views.projectChoser.ProjectChooser
import views.projectStructure.ProjectStructure
import kotlin.reflect.KClass


class MyApp() : App() {
    override val primaryView: KClass<out UIComponent> = ProjectChooser::class

    init {

    }
}

fun main(args: Array<String>) {
    launch<MyApp>(args = args)
}