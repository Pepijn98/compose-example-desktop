package dev.vdbroek.pepijn98.utils

import java.util.Properties

data class BuildInfo(
    val name: String,
    val group: String,
    val version: String,
    val description: String,
    val copyright: String,
    val vendor: String
) {
    companion object {
        fun from(properties: Properties): BuildInfo {
            val name: String by properties
            val group: String by properties
            val version: String by properties
            val description: String by properties
            val copyright: String by properties
            val vendor: String by properties
            return BuildInfo(name, group, version, description, copyright, vendor)
        }
    }
}