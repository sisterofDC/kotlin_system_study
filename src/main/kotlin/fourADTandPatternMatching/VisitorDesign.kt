package fourADTandPatternMatching

//訪問者模式中，把方法重寫一個類出來

sealed class Tree{
    class Num(val value:Int):Tree()
    class Node(val name:String,val left:Tree,val right:Tree):Tree()
}

class Visitor{
    fun isNum(tree: Tree):Boolean= tree is Tree.Num
    fun isNode(tree: Tree):Boolean = tree is Tree.Node
}

class VisitorDesign {

}