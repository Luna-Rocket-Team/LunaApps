
fun main(args: Array<String>) {
    println("Hello World!")

    var curva: CurvaEmpuxo = CurvaEmpuxo("texto.txt", 0.25, 0.002542)
    println(curva.refinaDados())
    println(curva.axis_y)
    println(curva.axis_y.size)

}


