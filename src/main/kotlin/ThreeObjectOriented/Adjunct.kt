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

//封装类
//封装类可以被继承，但是只有当前文件可以使用,其他文件不能使用
sealed class SealedClass(){
    open fun sealedFunction():String= "this is sealed function"
}

class InheritSealedClass():SealedClass(){
    override fun sealedFunction(): String {
        return "this is Inherit sealed function"
    }
}


//修饰符 internal :在kotlin 中的作用域可以被称作“模块内访问” 相当于一个IDEA的项目
//kotlin中则可以用private给单独的类修饰，作用域就是当前这个kotlin文件
//kotlin中protected修饰符作用域只有类及子类

private open class PrivateClass(val name:String){
    private fun showNameOne():String{
        return name
    }
    protected fun showNameTwo():String{
        return name
    }
    protected open fun showNameThree():String{
        return name
    }
    fun showNameFour():String{
        return name
    }
}


class AccessClass(val one: String){
    private val name:String="this is private class"
    private val nameTwo=PrivateClass(one)
    init {
//        这里只能访问方法四，因为private和protected都只能类可见
//        但是protected可以子类访问
        println(nameTwo.showNameFour())
    }
}

//只有private 类可以继承private父类
private class AccessClassTwo(name: String):PrivateClass(name){
    override fun showNameThree(): String {
        return super.showNameThree()
    }
    init {
//      protected可以作用于子类
        println(showNameTwo())
        println(showNameThree())
        println(showNameFour())
    }
}


fun main() {
    val classOne = AdjunctTwo("one")//父类
    classOne.initialization()
    //这里父类不能再使用openfunction()因为这里是子类直接复写了
//    里氏替换原则
//    1，子类可以实现父类的抽象方法，但不能覆盖父类的非抽象方法
//    2，子类可以增加自己特有的方法
//    3，当子类的方法实现父类的方法是，方法的前置条件要比父类方法更宽松
//    4，当子类的方法实现父类的抽象方法时，方法的后置条件（既方法的返回值，更严格）

    val classTwo = InheritClass("Two")//子类
    //子类可以调用父类的方法，可以重写父类open方法
    classTwo.initialization()
    classTwo.initializationInherit()
    classTwo.openFunction()
    val classThree = InheritAbstractClass("one",2)
    classThree.functionOne()

    val classFour = InheritSealedClass()
    println(classFour.sealedFunction())


}