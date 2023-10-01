import java.io.BufferedReader
import java.io.File

class CurvaEmpuxo(var file: String, var min_empuxo: Double, var prop_massa: Double) {

    private val gravidade = 9.80665
    private var axis_x = mutableListOf<Double>()
    var axis_y = mutableListOf<Double>()

    fun refinaDados() {
        lerArquivo()
        var i: Int = 0
        while(this.axis_y.size > i) {
            if (this.axis_y[i] < 0.05) {
                this.axis_x.removeAt(i)
                this.axis_y.removeAt(i)
            }
            i++
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
}