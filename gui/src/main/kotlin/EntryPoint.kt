import tornadofx.App
import tornadofx.launch
import views.MenuView


class MyApp():App(MenuView::class)

fun main(args: Array<String>) {
    launch<MyApp>(args = args)
}