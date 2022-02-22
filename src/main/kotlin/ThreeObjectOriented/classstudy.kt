package ThreeObjectOriented
//不可变属性成员 kotlin 支持val 在类中声明引用不可变的属性成员
//属性默认值
//不同的可访问修饰符 kotlin类中的成员默认是全局可见

class classStudy(
    val sale:Int,
    val buyer:Int,
    val price:Double,
    var priceName:String
){
    lateinit var version:String
    init {
        var i:Int=0
        print("this is buyer ${i}")
        this.priceName="wyx_pruduct"
    }
    val time:String by lazy {
//  该变量必须是引用不可变的，而不能通过var来声明
//  在被首次调用时，才会进行赋值操作。一旦被赋值，后续它不能被更改
        if (sale==0)
            "yes"
//        这里是直接lamdba表达式返回time的值
        else
            "no"
    }
}

//这里区分一下 by lazy和lateinit
class student(val name:String,val age:Int){
    lateinit var className:String
    val number:Int by lazy(LazyThreadSafetyMode.PUBLICATION) {
//并行模式
        if (age>20)
            2001
        else
            1999
    }
    val otherModeNumber:Int by lazy (LazyThreadSafetyMode.NONE){
//        这里是直接取消线程锁，这样不会用线程保证也没有线程开销
        if (name.length>4)
            1
        else
            2
    }
}


interface customer{
    var name:String
}

