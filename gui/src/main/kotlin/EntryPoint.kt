import tornadofx.App
import tornadofx.UIComponent
import tornadofx.launch
import views.MenuView
import views.projectChoser.ProjectChooser
import kotlin.reflect.KClass


class MyApp() : App() {
    override val primaryView: KClass<out UIComponent> = ProjectChooser::class
}

fun main(args: Array<String>) {
    launch<MyApp>(args = args)
}