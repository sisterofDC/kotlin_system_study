package fourADTandPatternMatching

//在日常开发中，如果能合理利用ADT去对业务进行高度抽象，代码在实现诸多功能的前提会变得非常简洁
//两种常见的类型积类型与和类型
class AlgebraicDataType {

}
//积类型 在利用类进行组合时候
class IntProductDouble(x:Int,y:Double){

}
//和类型
sealed class Week{
    class Mon:Week()
    class Tue:Week()
    class Wed:Week()
    class Thu:Week()
    class Fri:Week()
    class Sat:Week()
    class Sun:Week()
}

fun mon(){
    println("this is monday")
}

fun notMon(){
    println("this is no monday")
}

fun schedule(week: Week):Unit=
    when(week){
        is Week.Mon -> mon()
//        这里如果不把其他补充完整，就直接用else。但是如果补充完整，就不需要用else
        else -> notMon()
    }


fun testFuction(x:String):Int{
        return 2
}


sealed class Model{
    class LinerRegression(val TheWholeFunction:String):Model()
    class Classification(val Coordinates:Array<Int>):Model()
}

fun getModel(model: Model):(String)->Int=when(model){
    is Model.Classification -> ::testFuction
    is Model.LinerRegression -> ::testFuction
}

sealed class DataStructure{
    data class VectorData(val samples:Array<Double>,val features:String)
    data class SequenceData(val samples: Array<Double>,val timesteps:Array<Int>,val features: String)
    data class PictureData(val samples: Array<Double>, val height:Int, val width:Int, val channels:String)
    data class VideoData(val samples: Array<Double>, val frames:Int, val height: Int, val width:Int, val channels: String)
    data class operate(val model:String,val input:DataStructure,val output: Array<Int>):DataStructure()
}

sealed class Expr(){

    abstract fun IsNum(expr: Expr):Boolean
    abstract fun IsOperate(expr: Expr):Boolean
    abstract fun left():Expr
    abstract fun right():Expr

    data class Num(val value:Int):Expr() {
        override fun IsNum(expr: Expr): Boolean= expr is Num
        override fun IsOperate(expr: Expr):Boolean{
            return false
        }

        override fun left(): Expr {
            throw Throwable("no element")
        }

        override fun right(): Expr {
            throw Throwable("no element")
        }
    }

    data class Operate(val opName:String,val left:Expr,val right:Expr):Expr() {
        override fun IsNum(expr: Expr): Boolean{
            return false
        }
        override fun IsOperate(expr: Expr):Boolean= expr is Operate
        override fun left(): Expr {
            return left
        }

        override fun right(): Expr {
            return right
        }
    }
}

//偽代碼 if(expr is "0 + x"||expr is "x + 0") x else expr

fun judge(expr: Expr):Expr= if (expr is Expr.Operate && expr.left is Expr.Num && expr.left.value == 0){
    expr
}else {
    expr
}

//kotlin模式匹配

fun judgeTwo(expr: Expr):Expr = when(expr){
    is Expr.Num -> expr
    is Expr.Operate -> when(expr){
        Expr.Operate("+",Expr.Num(0),expr.right) -> expr.right
        else -> expr
    }
}

fun judgeThree(expr: Expr):Expr =when(expr.IsNum(expr)){
    true -> expr
    false -> when(expr.left().IsNum(expr)){
        true -> expr.left()
        false -> expr.left().left()
    }

}



fun returnAll(expr: Expr):List<Expr> {
    val store = mutableListOf<Expr>()
    return store
}


//面向對象分解



fun main() {
    val exprOne = Expr.Operate("+",Expr.Num(1),Expr.Num(2))
    val exprTwo = Expr.Operate("+",Expr.Num(3),Expr.Num(4))
    val exprThree = Expr.Operate("-",exprOne,exprTwo)
//    現在要做的是把exprThree中的exprone的num提取出來

    val test = judgeThree(exprThree)
    println(test)

}