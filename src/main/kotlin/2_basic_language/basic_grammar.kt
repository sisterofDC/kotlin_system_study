package `2_basic_language`

class basic_grammar(){
    fun menber(x:Int,y:Int):Int=x*y}

fun sum (x:Int,y:Int):Int{
    return x+y
}
// Expression function body
fun reduce (x:Int,y:Int):Int = x-y
//对于函数的声明类型
//还是都去声明一下,为了更好的代码可读性和输出类型的可控性

fun divide(x:(Int,Int)->Int,y:Int,z:Int):Int{
    val result = x(y,z)
    return result
}

//如果是用lambda来写函数

fun printTest(x:Int) = {
    println(x)
}

fun printFuntion(x:MutableList<Int>){
    //lambda是语法糖
    val show :(MutableList<Int>,Int)->Int = { list:MutableList<Int>,position:Int->list.indexOf(position) }
//    如果没有直接声明，那默认最后一行就是返回值的类型
    val lenth = {
        list:MutableList<Int>->
        list.size
    }
    println(show(x,2))
    println(lenth(x))
    x.forEach {
        printTest(it)()
    }
}

//函数，lambda和闭包

fun <A,B> Array<A>.com(x:Array<A>,b:B):Int{
    val i = this.iterator()
    val j = x.iterator()
    val k = b
    return  this.size
}

//表达式比语句更安全

//这样就不用声明a为空了
fun initnumber(flag:Boolean):Int{
    val a = if(flag)
        1
    else
        2
    return a
}

//${}

//字符串判断
//==判断两个对象是否相等
//===判断引用对象是否相等，!== 是否

fun main(){
    val one = 1
//    val 的声明是varible +final
    val two = 2
    var three = 4
    val functionQuote=basic_grammar()
    val list = mutableListOf<Int>(1,2,3,4)
    printFuntion(list)
    println(sum(one,two))
    println(reduce(one,two))
    three = sum(three,two)
    println(three)
    val four = divide(functionQuote::menber,one,two)
    //类::方法 可以用来引用某个类中的成员变量
    println(four)




}
//优先使用val来避免副作用



//函数的类型
//(Int)-> Unit
//1.通过->符号来组织参数类型和返回值类型，左边是参数类型，右边是返回值类型
//2.必须拥有过括号包裹参数类型
//3.返回值即使是unit，也必须显示声明

