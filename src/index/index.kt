package index

import Gtag.config
import app.*
import kotlinext.js.*
import react.dom.*
import kotlin.browser.*

fun main(args: Array<String>) {
    requireAll(require.context("src", true, js("/\\.css$/")))

    config()

    render(document.getElementById("root")) {
        app()
    }
}
