package `2_basic_language`

class basic_grammar(){
    fun menber(x:Int,y:Int):Int=x*y
}

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


fun main(){
    val one = 1
//    val 的声明是varible +final
    val two = 2
    var three = 4
    val functionQuote=basic_grammar()


    println(sum(one,two))
    println(reduce(one,two))
    three = sum(three,two)
    println(three)
    val four = divide(functionQuote::menber,one,two)
    println(four)

}
//优先使用val来避免副作用



//函数的类型
//(Int)-> Unit
//1.通过->符号来组织参数类型和返回值类型，左边是参数类型，右边是返回值类型
//2.必须拥有过括号包裹参数类型
//3.返回值即使是unit，也必须显示声明

//类::方法 可以用来构造引用变量
