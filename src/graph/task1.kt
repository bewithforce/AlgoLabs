package graph

data class Node(val name:String) {
    override fun toString() = name
}


data class Arc(val first: Node, val second: Node){
    override fun toString() = "{$first, $second}"
}

fun main() {
    val size = 5
    val list = init()
    val incidence  = Array(size){IntArray(list.size){0}}
    val adjacency  = Array(size){IntArray(size){0}}
    val setOfNodes = HashSet<Node>()

    for(arc in list){
        setOfNodes.add(arc.first)
        setOfNodes.add(arc.second)
    }

    for (node1 in setOfNodes){
        for (node2 in setOfNodes){
            if(list.contains(Arc(node1, node2))){
                val i = setOfNodes.indexOf(node1)
                val j = setOfNodes.indexOf(node2)
                val k = list.indexOf(Arc(node1, node2))
                adjacency[i][j] = 1
                adjacency[j][i] = 1

                incidence[i][k] = 1
                incidence[j][k] = 1
            }
        }
    }

    println("Список смежности")
    println(list)
    println("\nМатрица инцидентности")
    for(arr in incidence){
        for(c in arr){
            print("$c ")
        }
        println()
    }
    println("\nМатрица смежности")
    for(arr in adjacency){
        for(c in arr){
            print("$c ")
        }
        println()
    }
}

fun init(): List<Arc>{
    val list = ArrayList<Arc>()
    val a = Node("a")
    val b = Node("b")
    val c = Node("c")
    val d = Node("d")
    val e = Node("e")

    list += Arc(a, b)
    list += Arc(a, c)
    list += Arc(a, d)
    list += Arc(a, e)
    list += Arc(b, c)
    list += Arc(b, d)
    list += Arc(b, e)
    list += Arc(c, d)
    list += Arc(c, e)
    list += Arc(d, e)
    return list
}