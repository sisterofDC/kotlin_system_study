package ThreeObjectOriented

class masterSlaveStructure {
//    多个构造方法的学习
}

//这里更多是android中的应用，其中很多是组件的构造中会出现

class Customer(val name:String,val age:Int,val Vip:Boolean){
    constructor(name: String):this(name,0,false)
    constructor(name: String,age: Int):this(name,age,false)
}

//不同的访问控制原则

fun main() {
    val wyx = Customer("wyx")
}