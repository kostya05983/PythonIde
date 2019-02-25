package lab2automat

class OutputConsole: OutputStrategy {
    override fun print(s: String) {
        System.out.print(s)
    }
}