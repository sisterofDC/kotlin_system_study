package ThreeObjectOriented

//这里我们学习了kotlin中的多继承和data类和解构
//多继承中主要是两个方法，一个是使用接口，还有一个是使用委托
//在kotlin红声明一个数据类。必须满足以下几个条件
//数据类必须拥有一个构造方法，至少包含一个参数，构造方法参数强制使用var val 来声明
//data class中不能用abstract ,open , sealed ,inner来进行修饰


//在kotlin中，数据类只需要加上data
data class MultipleInherit(var mainThread: MainThread,var viewThread: ViewThread,var fragment: Fragment){
}

data class test(var one:Int,var two:String,var three:Double){
//    这里可以自己实现属性componentN的方法
    var four:Int = 1
    operator fun component4():Int{
        return this.four
    }
    constructor(one: Int,two: String,three: Double,four:Int):this(one, two, three){
        this.four =four
    }
}

//这里看到如果是java 要很多代码
//这里贴上JAVA转义的代码
//public final class test {
//    private int one;
//    @NotNull
//    private String two;
//    private double three;
//    public final int getOne() {
//        return this.one;
//    }
//    public final void setOne(int var1) {
//        this.one = var1;
//    }
//    @NotNull
//    public final String getTwo() {
//        return this.two;
//    }
//    public final void setTwo(@NotNull String var1) {
//        Intrinsics.checkNotNullParameter(var1, "<set-?>");
//        this.two = var1;
//    }
//    public final double getThree() {
//        return this.three;
//    }
//    public final void setThree(double var1) {
//        this.three = var1;
//    }
//    public test(int one, @NotNull String two, double three) {
//        Intrinsics.checkNotNullParameter(two, "two");
//        super();
//        this.one = one;
//        this.two = two;
//        this.three = three;
//    }

//这里还有copy,componentN 的解构


//这里componentN可以理解为类属性的值，其中N代表属性的顺序
//    public final int component1() {
//        return this.one;
//    }
//    @NotNull
//    public final String component2() {
//        return this.two;
//    }
//    public final double component3() {
//        return this.three;
//    }
//    @NotNull



//这里就是使用copy
//    public final test copy(int one, @NotNull String two, double three) {
//        Intrinsics.checkNotNullParameter(two, "two");
//        return new test(one, two, three);
//    }
//    // $FF: synthetic method

//    public static test copy$default(test var0, int var1, String var2, double var3, int var5, Object var6) {
//        if ((var5 & 1) != 0) {
//            var1 = var0.one;
//        }
//        if ((var5 & 2) != 0) {
//            var2 = var0.two;
//        }
//        if ((var5 & 4) != 0) {
//            var3 = var0.three;
//        }
//        return var0.copy(var1, var2, var3);
//    }



//    @NotNull
//    public String toString() {
//        return "test(one=" + this.one + ", two=" + this.two + ", three=" + this.three + ")";
//    }
//    public int hashCode() {
//        int var10000 = Integer.hashCode(this.one) * 31;
//        String var10001 = this.two;
//        return (var10000 + (var10001 != null ? var10001.hashCode() : 0)) * 31 + Double.hashCode(this.three);
//    }
//    public boolean equals(@Nullable Object var1) {
//        if (this != var1) {
//            if (var1 instanceof test) {
//                test var2 = (test)var1;
//                if (this.one == var2.one && Intrinsics.areEqual(this.two, var2.two) && Double.compare(this.three, var2.three) == 0) {
//                    return true;
//                }
//            }
//            return false;
//        } else {
//            return true;
//        }
//    }
//}


//这里有个简单的问题，View和Fragemtn都需要继承主线程和显示线程，但是fragemtn中也有view类

//接口实现多继承
interface MainThreadInterFace{
    fun name()="this is MainThread"
    fun calculate(x:Int):(Array<Double>)->Int
}

interface  ViewThreadInterFace{
    fun name()="ths is view thread"
    fun show()
}

class Sort(){
    fun sortfunction(x:Array<Double>):Int{
        return x.size
    }
}

open class MainThread():MainThreadInterFace{
    override fun name(): String {
        return "this Main thread class"
    }
    override fun calculate(x: Int): (Array<Double>) -> Int {
        val sort = Sort()
        println("this mainthread")
        return sort::sortfunction
    }
}


open class ViewThread():ViewThreadInterFace{
    override fun show() {
        println("this viewThread")
    }
    override fun name(): String="ths is view thread"

}


//这里采用委托的方法，代替多继承实现需求
class ViewOne(viewThread: ViewThread,mainThread: MainThread):MainThreadInterFace by MainThread(),ViewThreadInterFace by ViewThread(){
    override fun name(): String {
        return "this is view one"
    }

}
//直接用接口来实现多继承
class ViewTwo(val name:String):MainThreadInterFace,ViewThreadInterFace{
//    这里可以看到可以选择复写那个接口
    override fun name(): String {
        return super<MainThreadInterFace>.name()
    }

//        return "this is MainThreadInterface"
    override fun show() {
        TODO("Not yet implemented")
    }
    override fun calculate(x: Int): (Array<Double>) -> Int {
        TODO("Not yet implemented")
    }
}
//使用内部类来多继承
class Fragment(){
    private inner class Main:MainThread()
    private inner class View:ViewThread()
    fun mainName():String{
        return Main().name()
    }
    fun viewName():String{
        return View().name()
    }
}

fun main() {
    val mainThread = MainThread()
    val viewThread = ViewThread()
    val viewOne=ViewOne(viewThread,mainThread)
    viewOne.show()
    println(viewOne.name())
    val number= viewOne.calculate(1)
    val array = Array<Double>(size = 2, init = {it->0.0})
    array[0]=1.1
    array[1]=1.2
    println(number(array))
    val viewTwo = ViewTwo("two")
    println(viewTwo.name())
    val viewThree = Fragment()
    println(viewThree.mainName())
    println(viewThree.viewName())


//    浅拷贝，和copy
    val one=test(1,"one",1.0)
    val two=one
    two.one=2
    println(one.one)
    if (one===two)
        println("true")
    else
        println("false")
//    这里是ture

//    如果没有使用copy那么引用的是同一个类
    val three=test(2,"two",2.0)
    val four=three.copy(one = 3)
    println(three.one)
    if (three===four)
        println("true")
    else
        println("false")
//    这里是false


//这里使用的就是解构的方法，这样就可以避免写很多相同的代码,
    val five = test(2,"three",2.0,0)
    val (a,b,c,d) = five
    println("${a}${b}${c}${d}")
//同样还支持解构的还有Pair和triple
    val (e,f) = Pair("one","two")
    val (g,h,j) = Triple(1,2,3)
}





