package dinamo

import org.revenj.{BytecodeLoader, CompareCompiler}
import test.Transaction
import scala.reflect.ClassTag

object Main extends App {
  def makeComparer[T, R <: Comparable[R]](property: String)(implicit ct: ClassTag[T], tr: ClassTag[R]) = {
    val bytecode = CompareCompiler.compile(ct.runtimeClass.asInstanceOf[Class[T]], property)
    val comparerClass = new BytecodeLoader().define(bytecode)
    comparerClass.newInstance().asInstanceOf[org.revenj.patterns.Query.Compare[T, R]]
  }

  val txn = new Transaction()
    .setIn(java.math.BigDecimal.TEN)
    .setDescription("This is a test")

  println(makeComparer[Transaction, java.lang.Long]("ID").compare(txn))
  println(makeComparer[Transaction, java.math.BigDecimal]("in").compare(txn))
  println(makeComparer[Transaction, java.lang.String]("description").compare(txn))

  sys.exit(0)
}
