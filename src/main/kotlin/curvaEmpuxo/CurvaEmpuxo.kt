import java.io.BufferedReader
import java.io.File

class CurvaEmpuxo(var file: String, var min_empuxo: Double, var prop_massa: Double) {

    private val gravidade = 9.80665
    private var axis_x = mutableListOf<Double>()
    private var axis_y = mutableListOf<Double>()
    private var impulsoTotal: Double = 0.0

    fun refinaDados() {
        lerArquivo()
        var i: Int = 0
        while(this.axis_y.size > i) {
            if (this.axis_y[i] < this.min_empuxo) {
                this.axis_x.removeAt(i)
                this.axis_y.removeAt(i)
                i--
            }
            i++
        }
        if((this.axis_y.size/2) != 0){
            this.axis_x.removeAt(this.axis_x.size-1)
            this.axis_y.removeAt(this.axis_y.size-1)
        }
    }

    fun lerArquivo() {

            val arquivo = this.file // Substitua pelo caminho do seu arquivo de texto
            val coluna1 = mutableListOf<Double>()
            val coluna2 = mutableListOf<Double>()

            try {
                BufferedReader(File(arquivo).bufferedReader()).use { reader ->
                    var line: String?
                    while (reader.readLine().also { line = it } != null) {
                        val columns = line?.split("\t") // Use "\t" para separação por tabulação
                        if (columns != null && columns.size >= 2) {
                            coluna1.add(columns[0].toDouble())
                            coluna2.add(columns[1].toDouble())
                        }
                    }
                }
                this.axis_x = ArrayList(coluna1)
                this.axis_y = ArrayList(coluna2)

            } catch (e: Exception) {
                println("Ocorreu um erro ao ler o arquivo TXT: ${e.message}")
            }
        }

    fun impulsoTotal(): Double{

        var n: Int = 0
        this.impulsoTotal = 0.0
        val h: Double = (this.axis_x[this.axis_x.size-1] - this.axis_x[0]) / (this.axis_x.size-1)

        while(n < this.axis_x.size-3){
            this.impulsoTotal += (3*h/8 * (this.axis_y[n] + 3*this.axis_y[n+1] + 3*this.axis_y[n+2] + this.axis_y[n+3]))
            n+= 3
        }
        return this.impulsoTotal
    }

    fun empuxoMax(): Double{
        return this.axis_y.max()
    }

    fun empuxoMin(): Double{
        return this.axis_y.min()
    }

    fun empuxoMedio(): Double{
        return this.impulsoTotal/(this.axis_x[this.axis_x.size-1] - this.axis_x[0])
    }

    fun velMediaGases(): Double{
        return this.impulsoTotal/this.prop_massa
    }

    fun impulsoEpecificoMedio(): Double{
        return this.impulsoTotal/(this.prop_massa*this.gravidade)
    }

    fun fluxoMassaMedio(): Double{
        return this.prop_massa/(this.axis_x[this.axis_x.size-1] - this.axis_x[0])
    }

    fun tempoQueima(): Double{
        return this.axis_x[this.axis_x.size-1] - this.axis_x[0]
    }

    fun tempoEmpuxoMax(): Double{
        return this.axis_x[this.axis_y.indexOf(this.axis_y.max())]
    }

    fun tempoEmpuxoMaxCorrigido(): Double{
        return this.axis_x[this.axis_y.indexOf(this.axis_y.max())]-this.axis_x[0]
    }

    fun tempoInicioQueima(): Double{
        return this.axis_x[0]
    }

    fun tempoFinalQueima(): Double{
        return this.axis_x[this.axis_x.size-1]
    }

    fun relacaoEmpuxoMaxMed(): Double{
        return this.axis_y.max()/this.empuxoMedio()
    }

    fun relacaoEmpuxoMinMax(): Double{
        return this.axis_y.min()/this.axis_y.max()
    }
}