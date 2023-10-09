
fun main(args: Array<String>) {

    var curva: CurvaEmpuxo = CurvaEmpuxo("texto.txt", 0.25, 0.002542)
    curva.refinaDados()
    println("Impulso Total: "+curva.impulsoTotal())
    println("Empuxo Máximo: "+curva.empuxoMax())
    println("Empuxo Médio: "+curva.empuxoMedio())

}


