package tree

import kotlin.math.sqrt

data class Node(val key:Int){
    var top: Node? = null
    var left: Node?  = null
    var right: Node?  = null

    val value: String = sqrt(key.toDouble()).toBits().toString(2)

    fun deleteChild(node: Node?) {
        when {
            node == null -> return
            this.left == node -> this.left = null
            this.right == node -> this.right = null
        }
    }

    fun replaceChild(child: Node?, toReplace: Node?) = when {
        this.left == child -> {
            this.left = toReplace
            toReplace?.top = this
        }
        this.right == child -> {
            this.right = toReplace
            toReplace?.top = this
        }
        else -> {}
    }


}

class BinarySearchTree {
    var root: Node? = null

    fun add(key: Int) {
        if (root == null) {
            root = Node(key)
            return
        }
        var temp = root
        val node = Node(key)
        while (true) {
            if (temp!!.key < key) {
                if (temp.left != null) {
                    temp = temp.left
                } else {
                    temp.left = node
                    node.top = temp.left
                    return
                }
            } else if (temp.key == key)
                return
            else {
                if (temp.right != null) {
                    temp = temp.right
                } else {
                    temp.right = node
                    node.top = temp.right
                    return
                }
            }
        }
    }

    fun find(key: Int): Node? {
        if (root == null)
            return root
        var temp = root
        while (true) {
            when {
                temp == null -> return temp
                temp.key == key -> return temp
                temp.key < key -> temp = temp.left
                temp.key > key -> temp = temp.right
            }
        }
    }

    fun delete(key: Int) = delete(key, root)

    private fun delete(key: Int, node: Node?): Boolean = when {
        node == null -> false
        node.key < key -> delete(key, node.left)
        node.key > key -> delete(key, node.right)
        else -> {
            if (node == root) {
                root = null
            } else if (node.left == null && node.right == null) {
                node.top!!.deleteChild(node)
            } else if (node.left == null) {
                node.top!!.replaceChild(node, node.right)
            } else if (node.right == null) {
                node.top!!.replaceChild(node, node.left)
            } else {
                var temp = node.right
                if (temp!!.left == null){
                    temp.top = node.top
                    temp.left = node.left
                    val left = node.left
                    left!!.top = temp
                    val top = node.top
                    top!!.replaceChild(node, temp)
                }else {
                    while (temp!!.left != null) {
                        temp = temp.left
                    }
                    var top = temp.top
                    top!!.replaceChild(temp, temp.right)

                    temp.top = node.top
                    temp.left = node.left
                    temp.right = node.right

                    val left = node.left
                    left!!.top = temp
                    val right = node.right
                    right!!.top = temp
                    top = node.top
                    top!!.replaceChild(node, temp)
                }
            }
            true
        }
    }

}