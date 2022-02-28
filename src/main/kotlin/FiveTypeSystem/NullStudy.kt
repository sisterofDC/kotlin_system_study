package FiveTypeSystem

import org.jetbrains.annotations.NotNull
import java.util.Optional

class NullStudy {

}
//map類的學習

fun mapStudy(map: Map<String,Int>):Map<String,Int>{
//    要从 Map 中检索值，必须提供其键作为 get() 函数的参数。
//    还支持简写 [key] 语法。 如果找不到给定的键，则返回 null 。 还有一个函数 getValue()
//    ，它的行为略有不同：如果在 Map 中找不到键，则抛出异常
    val one=map.get("One") //得到值
    val two=map.getOrDefault("two",0)
    val three=map.getValue<String,Int>("three")
    val four=map.keys
    val five=map.values
//過濾
//    可以使用 filter() 函数来过滤 map 或其他集合。 对 map 使用 filter() 函数时， Pair 将作为参数的谓词传递给它。 它将使用谓词同时过滤其中的键和值。
    val six = map.filter { (key,value)->value>10 }
//     filterKeys() 的谓词仅检查元素键， filterValues() 的谓词仅检查值。
    val seven = map.filterKeys { it.endsWith('e') }
    val eight = map.filterValues { it>5 }
    return six
}

fun mapOperation(map: Map<String, Int>):Map<String,Int>{
    val one = map+Pair<String,Int>("one",4)
    val two = map - mapOf<String,Int>("one" to 5 ,"two" to 10)
    return one
}

//情景再現，學生，座位，有沒有眼睛

data class Seat(val student: Student?)
data class Student(val glasses: Glasses?)
data class Glasses(val degree:Double)

//也可以仿照scala
sealed class Either<A,B>(){
    class Left<A,B>(val value: A):Either<A,B>()
    class Right<A,B>(val value: B):Either<A,B>()
}

fun getGlasses(seat: Seat){
//    安全的調用，在讀取數據的時候也有用
    println("${seat.student?.glasses?.degree}")
//    Elvis 操作符 ?:  這裡是如果是為空會直接返回默認值
    val result  = seat.student?.glasses?.degree?:-1
    println(result)
//    這裡也可以用傳統的
//    可以用@NutNull/Nullable進行註解,反正盡量都採用這個方法
    try {
        @NotNull
        val resulttwo = seat.student!!.glasses!!.degree
        println(resulttwo)
    }catch (e:NullPointerException){
        println(e)
    }

//    如果使用scala進行操作 這裡有點問題
    val resultThree:Either<Glasses?,Error>? = seat.student?.glasses?.let {
        Either.Left<Glasses?,Error>(it)?:Either.Right<Glasses?,Error>(Error("there is no glass"))
    }
}


// Any是 kotlin中所以非空類型的超類 當然也是可以Any?
//kotlin 中的Int類型等同於int
//kotlin 中Int? 等同于Integer

//泛型
//類型檢查
//更加語義化
//自動類型轉換，在kotlin中<T>表示泛型

//類的泛型申請在類名後面

class  Find<T>(val list: MutableList<T>){
    fun findMember(member:T):Int{
        var result= list.indexOf(member)
        return result
    }
    fun addMember(member: T):MutableList<T>{
        when(list.indexOf(member)){
            -1 -> list.add(member)
            else -> list
        }
        return list
    }
}
//方法的泛型申請在方法前面
fun <T> indexFunctionTwo(list: MutableList<T>,member: T):Int{
    var result:Int = -1
    var j =0
    for (i in list) {
        if (member == i) {
            result = j
        }
        j++
    }
    return result
}

fun indexFunction(list: MutableList<Int>,member:Int):Int{
    var result = -1
    var j =0
    for (i in list) {
        if (member == i) {
            result = j
        }
        j++
    }
    return result
}








fun main() {
//    val glasses =Glasses(5.1)
//    val student = Student(glasses)
//    val seat =Seat(student)
//    val studentTwo = Student(null)
//    val seatTwo = Seat(studentTwo)
//    getGlasses(seat)
//    getGlasses(seatTwo)

    var test = mutableListOf<Int>(1,2,3,4,5,6,7)
    val result = test.indexOf(8)
    val resultTwo = indexFunction(test,5)
    println("this is ${result},${resultTwo}")

    val find = Find<Int>(test)
    val resultThree = find.findMember(8)
    val resultFour = find.addMember(10)
    println("${resultThree} ${resultFour}")



}