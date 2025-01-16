import scala.language.experimental.modularity

trait T:
  type Self
  type X
  def foo: Self

class D[C](using wd: C is T)
class E(using we: Int is T)

def Test =
  given w: Int is T:
    def foo: Int = 42
    type X = Long
  val d = D(using w)
  summon[d.wd.X =:= Long] // error
  val e = E(using w)
  summon[e.we.X =:= Long] // error
