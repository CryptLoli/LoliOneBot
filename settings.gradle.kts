rootProject.name = "loli-onebot"

base("client") {
    network("forward-ws")
    network("reverse-ws")
}

base("server") {
    network("reverse-ws")
}

include(":format-array-message")
include(":kotlin-dsl")
include(":base")

fun base(name: String, block: BaseContext.() -> Unit) {
    include(":$name")
    BaseContext(name).apply(block)
}

class BaseContext(val base: String) {
    fun network(name: String) {
        include(":$base-network-$name")
        project(":$base-network-$name").projectDir = file("$base/$name")
    }
}