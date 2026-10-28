rootProject.name = "loli-onebot"

addNetwork("forward-ws")
addNetwork("reverse-ws")
include(":format-array-message")
include(":kotlin-dsl")

fun addNetwork(name: String) {
    include(":network-$name")
    project(":network-$name").projectDir = file("network/$name")
}