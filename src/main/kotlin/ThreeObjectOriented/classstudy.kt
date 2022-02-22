package ThreeObjectOriented
//不可变属性成员 kotlin 支持val 在类中声明引用不可变的属性成员
//属性默认值
//不同的可访问修饰符 kotlin类中的成员默认是全局可见

class classstudy(
    val sale:Int,
    val buyer:Int,
    val price:Double
){
    init {
        var i:Int=0
        print("this is buyer ${i}")
    }
}

interface fly{

}