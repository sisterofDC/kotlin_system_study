package ThreeObjectOriented

class Adjunct(val one:String) {
    private val classVal:String="this is private val"
    fun getClassVal():String{
        return classVal
    }
}

open class AdjunctTwo(val one:String){
    fun initialization(){
        println("this is open class $one")
    }
    open fun openFunction(){
        println("this is open function")
    }
}

abstract class AbstractClass(val one:String,val two:Int){
    open fun functionOne(){}
    fun functionTwo(){}
    fun functionThree(){}
}




//open 在kotlin中类加入修饰词open，允许类被继承或重写
class InheritClass(one: String):AdjunctTwo(one){
    fun initializationInherit(){
        println("this is inherit class")
    }

    override fun openFunction() {
        super.openFunction()
        println("this is inherit function")
    }
}

class InheritAbstractClass(one: String,two: Int):AbstractClass(one,two){
    override fun functionOne(){
        println("this is inherit abstract function")
    }
}


//
//

fun main() {
    val classOne = AdjunctTwo("one")//父类
    classOne.initialization()
    //这里父类不能再使用openfunction()因为这里是子类直接复写了
    val classTwo = InheritClass("Two")//子类
    //子类可以调用父类的方法，可以重写父类open方法
    classTwo.initialization()
    classTwo.initializationInherit()
    classTwo.openFunction()
    val classThree = InheritAbstractClass("one",2)
    classThree.functionOne()
}