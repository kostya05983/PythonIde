package models

import java.io.File

data class Project(val name: String, val path: File) {
    override fun toString(): String {
        return name
    }
}
