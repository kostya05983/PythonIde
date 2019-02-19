package systemdeterminer

import java.util.*

class InfoSystem {
    private lateinit var type: Systems

    fun getType(): Systems {
        val systemName = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
        when {
            systemName.indexOf("win") >= 0 -> {
                type = Systems.Windows
            }
            systemName.indexOf("nux") >= 0 -> {
                type = Systems.Linux
            }
            systemName.indexOf("mac") >= 0 -> {
                type = Systems.MacOs
            }
        }
        return type
    }
}