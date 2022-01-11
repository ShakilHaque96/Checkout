import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class CheckOutTest extends AnyFlatSpec with Matchers {

  behavior of "Checkout"

  val discountForA: SpecialPrice = SpecialPrice(A, 3, 130)
  val discountForB: SpecialPrice = SpecialPrice(B, 2, 45)
  val discounts: Set[SpecialPrice] = Set(discountForA, discountForB)

  it should "not apply special price if discount quantity of SKU is not met" in {
    val Customer1: CheckOut = CheckOut(List(A,B,C,D), discounts)
    Customer1.total should be (115)
  }

  it should "correctly apply discount if discount quantity of SKU is met" in {
    val Customer2: CheckOut = CheckOut(List(A,A,A,B,B,C,D), discounts)
    Customer2.total should be (210)
  }

  it should "not apply discount if no special prices are available" in {
    val Customer3: CheckOut = CheckOut(List(A,A,A,B,B,C,D), Set.empty)
    Customer3.total should be (245)
  }
  it should "accept items in any order and apply correct discount" in {
    val Customer4: CheckOut = CheckOut(List(B,C,B), discounts)
    Customer4.total should be (65)
  }

  it should "return 0 if no items in customers basket and no special prices" in {
    val Customer5: CheckOut = CheckOut(List.empty, Set.empty)
    Customer5.total should be (0)
  }
}
