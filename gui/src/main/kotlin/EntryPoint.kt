import tornadofx.App
import tornadofx.launch


class MyApp():App(MenuView::class)

fun main(args: Array<String>) {
    launch<MyApp>(args = args)
}