package dev.vdbroek.pepijn98.utils

import dorkbox.systemTray.Menu
import dorkbox.systemTray.MenuItem

fun Menu.addMany(items: List<MenuItem>): Unit = this.addMany(items)
fun Menu.addMany(items: ArrayList<MenuItem>): Unit = this.addMany(items)
fun Menu.addMany(vararg items: MenuItem) {
    for (item in items) {
        this.add(item)
    }
}