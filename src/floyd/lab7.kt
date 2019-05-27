package floyd

import java.util.Arrays

fun main() {
    val weights = arrayOf(intArrayOf(1, 3, -2),
                          intArrayOf(2, 1, 4),
                          intArrayOf(2, 3, 3),
                          intArrayOf(3, 4, 2),
                          intArrayOf(4, 2, -1))
    val numVertices = 4

    Floyd_Warshall(weights, numVertices)
}

fun Floyd_Warshall(weights: Array<IntArray>, numVertices: Int) {
    val dist = Array(numVertices) { DoubleArray(numVertices) }
    for (row in dist)
        Arrays.fill(row, java.lang.Double.POSITIVE_INFINITY)

    for (w in weights)
        dist[w[0] - 1][w[1] - 1] = w[2].toDouble()

    val next = Array(numVertices) { IntArray(numVertices) }
    for (i in next.indices) {
        for (j in next.indices)
            if (i != j)
                next[i][j] = j + 1
    }

    for (k in 0 until numVertices)
        for (i in 0 until numVertices)
            for (j in 0 until numVertices)
                if (dist[i][k] + dist[k][j] < dist[i][j]) {
                    dist[i][j] = dist[i][k] + dist[k][j]
                    next[i][j] = next[i][k]
                }
    printResult(dist, next)
}

fun printResult(dist: Array<DoubleArray>, next: Array<IntArray>) {
    for (i in next.indices) {
        for (j in next.indices) {
            if (i != j) {
                var u = i + 1
                val v = j + 1
                var path = "$u -> $v  ${dist[i][j].toInt()}  $u"
                do {
                    u = next[u - 1][v - 1]
                    path += " -> $u"
                } while (u != v)
                println(path)
            }
        }
    }
}
