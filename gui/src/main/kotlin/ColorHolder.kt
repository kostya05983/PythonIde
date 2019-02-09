import javafx.scene.paint.Color
import tornadofx.c

object ColorHolder {
    lateinit var primaryColor: Color
    lateinit var secondColor: Color
    lateinit var fontColor: Color
    lateinit var selectionColor: Color

    init {
        //DEFAULT
        primaryColor = c("#393939")
        secondColor = c("#45474a")
        fontColor = Color.WHITE
        selectionColor = c("#3B71AA")

    }
}