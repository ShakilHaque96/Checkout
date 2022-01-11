case class CheckOut(basket: List[SKU], discount: Set[SpecialPrice]) {
  val total: Double = {
    basket
      .groupBy(identity)
      .transform { case (_, v) => v.size }
      .map { case (sku, quantity) => eachSKUtotal(sku, quantity) }
      .sum
  }

  private def eachSKUtotal(sku: SKU, quantity: Int): Double = {
    val offers = discount.find(_.item == sku)
    offers match {
      case Some(promo) => quantity / promo.amount * promo.price + quantity % promo.amount * sku.price
      case None        => sku.price * quantity
    }
  }
}
