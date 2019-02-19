import tornadofx.App
import tornadofx.UIComponent
import tornadofx.launch
import tornadofx.reloadStylesheetsOnFocus
import views.projectChoser.ProjectChooser
import kotlin.reflect.KClass


class MyApp() : App() {
    override val primaryView: KClass<out UIComponent> = ProjectChooser::class

    init {
        reloadStylesheetsOnFocus()
    }
}

fun main(args: Array<String>) {
    launch<MyApp>(args = args)
}