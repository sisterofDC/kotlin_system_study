package ThreeObjectOriented

//在kotlin中，没有static。引入object
//什么是伴生对象

object ObjectLearn {
    val W = "this is W"
    val Y = "this is Y"
    val Z = "this is Z"
}

fun main() {
    println(ObjectLearn.W)
}